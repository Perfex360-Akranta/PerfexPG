package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.QtmTlPhenomenaMappingDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.QtmTlPhenomenaMappingSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlPhenomenaMapping;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonMessage;

public class QtmTlPhenomenaMappingDaoImpl implements QtmTlPhenomenaMappingDao {

    private DBActionTemplate dbActionTemplate;
    FunctionCallApi fnCallApi;

    public QtmTlPhenomenaMappingDaoImpl(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
    }

    public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
    }

    @Override
    public void QtmTlPhenomenaMappingDaoImplJwt(String jwtToken) {
        fnCallApi = new FunctionCallApi(jwtToken);
    }

    // ------------------------------------------------------------------ create
    @Override
    public QtmTlPhenomenaMapping create(QtmTlPhenomenaMapping phenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception {

        List<String> sqls = new ArrayList<String>();
        QtmTlPhenomenaMappingSql sql = new QtmTlPhenomenaMappingSql();

        try {
            phenomenaMapping.setPhnmKeyid(
                    dbActionTemplate.getSequenceNumber(
                            QtmTlPhenomenaMappingSql.TBL_QTM_TL_PHENOMENA_MAPPING,
                            12, "PHNM", "", ""));

            sqls.add(QtmTlPhenomenaMappingSql.getInsertSql(
                    sql.getPhnmDbFields(), phenomenaMapping.getSaveArray()));

            dbActionTemplate.executeStatements(sqls);

        } catch (ValidationExceptions e) {
            throw new ValidationExceptions(e.getMessage());
        } catch (BusinessApplicationExceptions e) {
            throw new BusinessApplicationExceptions(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return phenomenaMapping;
    }

    // ------------------------------------------------------------------ update
    @Override
    public QtmTlPhenomenaMapping update(QtmTlPhenomenaMapping phenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception {

        List<String> sqls = new ArrayList<String>();
        QtmTlPhenomenaMappingSql sql = new QtmTlPhenomenaMappingSql();

        try {
            sqls.add(QtmTlPhenomenaMappingSql.getUpdateSql(
                    sql.getPhnmDbFields(), phenomenaMapping.getSaveArray()));

            dbActionTemplate.executeStatements(sqls);

        } catch (ValidationExceptions e) {
            throw new ValidationExceptions(e.getMessage());
        } catch (BusinessApplicationExceptions e) {
            throw new BusinessApplicationExceptions(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return phenomenaMapping;
    }

    // -------------------------------------------------- findByQphmAndSect (NEW)
 // -------------------------------------------------- findByQphmAndSect (NEW)
    @Override
    public QtmTlPhenomenaMapping findByQphmAndSect(String qphmKeyid, String sectKeyid)
            throws Exception {

        String sql = "SELECT phnm_keyid, phnm_active "
                   + "FROM qtm_tl_phenomena_mapping "
                   + "WHERE phnm_qphm_keyid = '" + qphmKeyid.replace("'", "''") + "' "
                   + "  AND phnm_sect_flid  = '" + sectKeyid.replace("'", "''") + "' "
                   + "LIMIT 1";

        CommonMessage.debugMsg("findByQphmAndSect SQL: " + sql);

        java.sql.Connection        conn = null;
        java.sql.Statement         stmt = null;
        java.sql.ResultSet         rs   = null;

        try {
            conn = dbActionTemplate.getNewConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery(sql);

            if (rs.next()) {
                QtmTlPhenomenaMapping result = new QtmTlPhenomenaMapping();
                result.setPhnmKeyid(rs.getString("phnm_keyid"));
                result.setPhnmActive(rs.getString("phnm_active"));
                result.setPhnmQphmKeyid(qphmKeyid);
                result.setPhnmSectFlid(sectKeyid);

                CommonMessage.debugMsg("findByQphmAndSect: found keyId=["
                        + result.getPhnmKeyid() + "] active=[" + result.getPhnmActive() + "]");
                return result;
            }

            CommonMessage.debugMsg("findByQphmAndSect: no record for "
                    + "qphm=[" + qphmKeyid + "] sect=[" + sectKeyid + "]");
            return null;

        } catch (Exception e) {
            CommonMessage.debugMsg("findByQphmAndSect error: " + e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            // Use your framework's closeConnection signature:
            // closeConnection(ResultSet, Statement, CallableStatement, PreparedStatement, Connection)
            if (rs != null || stmt != null || conn != null) {
                try {
                    dbActionTemplate.closeConnection(rs, stmt, null, null, conn);
                } catch (Exception ignored) {}
            }
        }
    }
    // -------------------------------------------------- updateActiveStatus (NEW)
    @Override
    public void updateActiveStatus(QtmTlPhenomenaMapping mapping) throws Exception {

        String sql = "UPDATE qtm_tl_phenomena_mapping "
                   + "SET phnm_active     = '" + mapping.getPhnmActive()     + "', "
                   + "    phnm_modifiedon = '" + mapping.getPhnmModifiedon() + "' "
                   + "WHERE phnm_keyid    = '" 
                   + mapping.getPhnmKeyid().replace("'", "''") + "'";

        CommonMessage.debugMsg("updateActiveStatus SQL: " + sql);

        try {
            List<String> sqls = new ArrayList<>();
            sqls.add(sql);
            dbActionTemplate.executeStatements(sqls);
            CommonMessage.debugMsg("updateActiveStatus done: keyId=["
                    + mapping.getPhnmKeyid() + "] active=[" + mapping.getPhnmActive() + "]");

        } catch (Exception e) {
            CommonMessage.debugMsg("updateActiveStatus error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    // ------------------------------------------------------------------ list
    @Override
    public List<String[]> getPhenomenaDmtList(CommonFilter commonFilter) throws Exception {
        try {
            List<String> paramValues = new ArrayList<>();

            String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
            String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

            String flidRaw   = commonFilter.getFlid();
            String flid      = "";
            String qphmKeyid = "";

            if (flidRaw != null && !flidRaw.trim().isEmpty()) {
                String[] parts = flidRaw.trim().split("\\|", 2);
                flid = parts[0].trim();
                if (parts.length > 1) {
                    qphmKeyid = parts[1].trim();
                }
            }

            CommonMessage.debugMsg("getPhenomenaDmtList flid=["
                    + flid + "] qphmKeyid=[" + qphmKeyid + "]");

            // Replace corrupted FLID=flidRaw with clean FLID=flid
            if (flidRaw != null && !flidRaw.isEmpty()) {
                condParms = condParms.replace("FLID=" + flidRaw, "FLID=" + flid);
            }

            if (!flid.isEmpty()) {
                condParms = condParms + ";FLID=" + flid;
            }

            if (!qphmKeyid.isEmpty()) {
                condParms = condParms + ";QPHMKEYID=" + qphmKeyid;
            }

            CommonMessage.debugMsg("getPhenomenaDmtList condParms=[" + condParms + "]");

            paramValues.add(condParms);
            paramValues.add(commonParams);

            List<String[]> dataList = fnCallApi.callFunction(
                    "QTM_TL_PHENOMENAMAPPINGLIST_SB", paramValues, 3, true);

            if (commonFilter.getViewClick() == 'Y') {
                String totalCnt = paramValues.get(0);
                CommonMessage.debugMsg("PhenomenaDmt totalCnt: " + totalCnt);
                boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
                if (isInteger) {
                    commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
                }
            }

            return dataList;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}