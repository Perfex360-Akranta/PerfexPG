package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.dao.MocItemDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MocItem;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonMessage;

public class MocItemDaoImpl implements MocItemDao {

    // Inject your dbActionTemplate the same way other DaoImpls do
     DBActionTemplate dbActionTemplate;
     FunctionCallApi fnCallApi;

    public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
    }
    
    public MocItemDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
    
    public void MocItemDaoImplJwt(String JwtToken) {
        try {
            fnCallApi = new FunctionCallApi(JwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MocItem create(MocItem mocItem) throws Exception, BusinessApplicationExceptions {
        try {
            
            String sortSql = "SELECT COALESCE(MAX(CAST(moc_itm_sortorder AS INTEGER)), 0) + 1 "
                           + "FROM moc_tl_item "
                           + "WHERE moc_itm_active = 'Y'";
            String nextSortOrder = dbActionTemplate.getSingleValue(sortSql);
            if (nextSortOrder == null || nextSortOrder.trim().isEmpty()) {
                nextSortOrder = "1";
            }
            mocItem.setMocItmSorton(nextSortOrder);
            CommonMessage.debugMsg("Next sort order: " + nextSortOrder);

            
            String newKeyid = dbActionTemplate.getSequenceNumber(
                    "moc_tl_item", 10, "MOCI", "", "");
            mocItem.setMocItmKeyid(newKeyid);
            CommonMessage.debugMsg("Generated keyid: " + newKeyid);

            
            String flidValue = (mocItem.getMocItmFlid() == null || mocItem.getMocItmFlid().trim().isEmpty())
                    ? "NULL"
                    : "'" + mocItem.getMocItmFlid() + "'";

            
            String sectionValue = (mocItem.getMocItmSection() == null || mocItem.getMocItmSection().trim().isEmpty())
                    ? "NULL"
                    : "'" + mocItem.getMocItmSection() + "'";

            // BUILD INSERT
            String insertSql = "INSERT INTO moc_tl_item "
                    + "(moc_itm_keyid, moc_itm_item, moc_itm_flid, "
                    + " moc_itm_sectionid, moc_itm_sortorder, "
                    + " moc_itm_createdby, moc_itm_active, moc_itm_createdon) "
                    + "VALUES ("
                    + "'" + mocItem.getMocItmKeyid()     + "', "
                    + "'" + mocItem.getMocItmItem()      + "', "
                    +        flidValue                   + ", "   // NULL or 'value'
                    +        sectionValue                + ", "   // NULL or 'value'
                    + "'" + mocItem.getMocItmSorton()    + "', "
                    + "'" + mocItem.getMocItmCreatedby() + "', "
                    + "'Y', "
                    + "CURRENT_TIMESTAMP)";

            CommonMessage.debugMsg("Insert SQL: " + insertSql);

            java.util.List<String> sqls = new java.util.ArrayList<>();
            sqls.add(insertSql);
            dbActionTemplate.executeStatements(sqls);

        } catch (BusinessApplicationExceptions e) {
            CommonMessage.debugMsg("Business exception: " + e.getMessage());
            throw new BusinessApplicationExceptions(e.getMessage());
        } catch (Exception e) {
            CommonMessage.debugMsg("MocItem create error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return mocItem;
    }
    
    
    @Override
    public MocItem update(MocItem mocItem) throws Exception, BusinessApplicationExceptions {
        try {
            String updateSql = "UPDATE moc_tl_item SET "
                    + "moc_itm_item       = '" + mocItem.getMocItmItem()    + "', "
                    + "moc_itm_flid       = '" + mocItem.getMocItmFlid()    + "', "
                    + "moc_itm_sectionid  = '" + mocItem.getMocItmSection() + "', "  // ✅ fixed
                    + "moc_itm_modifiedon = CURRENT_TIMESTAMP "
                    + "WHERE moc_itm_keyid = '" + mocItem.getMocItmKeyid() + "'";	

            java.util.List<String> sqls = new java.util.ArrayList<>();
            sqls.add(updateSql);
            dbActionTemplate.executeStatements(sqls);

        } catch (Exception e) {
            CommonMessage.debugMsg("MocItem update error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return mocItem;
    }
    
    
    @Override
    public List<String[]> getMocItemList(CommonFilter commonFilter) throws Exception {
        try {

            if (fnCallApi == null) {
                CommonMessage.debugMsg("fnCallApi is null in getMocItemList");
                throw new Exception("FunctionCallApi not initialized. Call MocItemDaoImplJwt first.");
            }

            List<String> paramValues = new ArrayList<>();

            // ── 1. Split the pipe-carrier BEFORE building condParms ─────────────
            String flidRaw   = commonFilter.getFlid();
            String flid      = "";
            String sectionid = "";
            
            

            if (flidRaw != null && !flidRaw.trim().isEmpty()) {
                String[] parts = flidRaw.trim().split("\\|", 2);
                flid = parts[0].trim();
                if (parts.length > 1) {
                    sectionid = parts[1].trim();
                }
            }

            CommonMessage.debugMsg("getMocItemList flid=["
                    + flid + "] sectionid=[" + sectionid + "]");

            // ── 2. Temporarily override flid in commonFilter with the clean value
            //       so FilterCondSql.getAbnRelatedConditionStr() never sees the pipe ──
            commonFilter.setFlid(flid);
            String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
            String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
            // Restore original (optional, but keeps caller state intact)
            commonFilter.setFlid(flidRaw);

            // ── 3. Append FLID param only when a real flid exists ───────────────
            if (!flid.isEmpty()) {
                condParms = condParms + ";FLID=" + flid;
            }

            // ── 4. Append SECTIONID only when selected; omit = all rows returned ─
            if (!sectionid.isEmpty()) {
                condParms = condParms + ";SECTIONID=" + sectionid;
            }

            CommonMessage.debugMsg("getMocItemList condParms=[" + condParms + "]");

            paramValues.add(condParms);
            paramValues.add(commonParams);

            List<String[]> dataList = fnCallApi.callFunction(
                    "MOC_TL_ITEMLIST_SB", paramValues, 3, true);

            if (commonFilter.getViewClick() == 'Y') {
                String totalCnt = paramValues.get(0);
                CommonMessage.debugMsg("MocItem totalCnt: " + totalCnt);
                boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
                if (isInteger) {
                    commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
                }
            }

            return dataList;

        } catch (Exception e) {
            CommonMessage.debugMsg("getMocItemList error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

//    @Override
//    public MocItem getById(String keyid) throws Exception {
//        String sql = "SELECT moc_itm_keyid, moc_itm_item, moc_itm_sectionid, moc_itm_flid "
//                   + "FROM moc_tl_item WHERE moc_itm_keyid = '" + keyid + "'"
//                   + "AND moc_itm_active = 'Y'";
//        List<String[]> rows = dbActionTemplate.getDataList(sql);
//        if (rows != null && !rows.isEmpty()) {
//            String[] row = rows.get(0);
//            MocItem item = new MocItem();
//            item.setMocItmKeyid(row[0]);
//            item.setMocItmItem(row[1]);
//            item.setMocItmSection(row[2]);
//            item.setMocItmFlid(row[3]);
//            return item;
//        }
//        return null;
//    }
    
    @Override
    public MocItem getById(String keyid) throws Exception {

        // ✅ Use exact column names from your DB table
        String sql = "SELECT moc_itm_keyid, "
                   + "       moc_itm_item, "
                   + "       moc_itm_sectionid, "
                   + "       moc_itm_flid "
                   + "FROM   moc_tl_item "
                   + "WHERE  moc_itm_keyid = '" + keyid + "' "
                   + "AND    moc_itm_active = 'Y'";

        CommonMessage.debugMsg("MocItem getById SQL: " + sql);

        List<String[]> rows = dbActionTemplate.getDataList(sql);

        if (rows != null && !rows.isEmpty()) {
            String[] row = rows.get(0);
            
            CommonMessage.debugMsg("MocItem getById row[0]=" + row[0] 
                + " row[1]=" + row[1] 
                + " row[2]=" + row[2] 
                + " row[3]=" + (row.length > 3 ? row[3] : "null"));

            MocItem item = new MocItem();
            item.setMocItmKeyid(row[0]);     // moc_itm_keyid
            item.setMocItmItem(row[1]);      // moc_itm_item
            item.setMocItmSection(row[2]);   // moc_itm_sectionid
            item.setMocItmFlid(row.length > 3 ? row[3] : ""); // moc_itm_flid
            return item;
        }

        CommonMessage.debugMsg("MocItem getById — no record found for keyid=[" + keyid + "]");
        return null;
    }
    

//    @Override
//    public void delete(String keyid) throws Exception {
//        String sql = "UPDATE moc_tl_item SET moc_itm_active = 'N' "
//        		+ "moc_itm_modifiedon = CURRENT_TIMESTAMP "    
//        		+ "WHERE moc_itm_keyid = '" + keyid + "'";
//        List<String> sqls = new ArrayList<>();
//        sqls.add(sql);
//        dbActionTemplate.executeStatements(sqls);
//        CommonMessage.debugMsg("MocItem soft-deleted keyid=[" + keyid + "]");
//    }
    
    @Override
    public void delete(String keyid) throws Exception {

        // ✅ Soft delete with timestamp — matches your table structure
        String sql = "UPDATE moc_tl_item "
                   + "SET    moc_itm_active     = 'N', "
                   + "       moc_itm_modifiedon = CURRENT_TIMESTAMP "
                   + "WHERE  moc_itm_keyid = '" + keyid + "'";

        CommonMessage.debugMsg("MocItem delete SQL: " + sql);

        List<String> sqls = new ArrayList<>();
        sqls.add(sql);
        dbActionTemplate.executeStatements(sqls);

        CommonMessage.debugMsg("MocItem soft-deleted keyid=[" + keyid + "]");
    }
    
	
}