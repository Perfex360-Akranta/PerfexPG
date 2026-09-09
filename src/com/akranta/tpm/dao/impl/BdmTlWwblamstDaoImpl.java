package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BdmTlWwblamstDao;
import com.akranta.tpm.dao.sql.BdmTlWwbladtlSql;
import com.akranta.tpm.dao.sql.BdmTlWwblamstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.FishboneSql;
//import com.akranta.tpm.dao.sql.GenTlFishbonedtlSql;
//import com.akranta.tpm.dao.sql.GenTlFishbonemstSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import java.util.regex.Pattern;


import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.WwblaServiceApi;


/* dao implementation */
public class BdmTlWwblamstDaoImpl implements BdmTlWwblamstDao {

	private DBActionTemplate dbActionTemplate;
	private WwblaServiceApi wwblaserviceapi;
	FunctionCallApi fnCallApi;


	public BdmTlWwblamstDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BdmTlWwblamstDaoImplJwt(String JwtToken) 
	{
		try{
			wwblaserviceapi = new WwblaServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BdmTlWwblamst create(BdmTlWwblamst newBdmTlWwblamst, BdmTlWwblamst existBdmTlWwblamst) throws Exception {
		CommonMessage.debugMsg("dao impl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql(); // contains dbtable,field names, Field types and
																	// related sqls of master table

		try {

			newBdmTlWwblamst.setWwblKeyid(
					dbActionTemplate.getSequenceNumber(BdmTlWwblamstSql.TBL_BDM_TL_WWBLAMST, 10, "WWBL", "", "")); // set
																													// the
																													// sequnce
																													// number
			sqls.add(
					BdmTlWwblamstSql.getInsertSql(bdmTlWwblamstSql.getWwblDbFields(), newBdmTlWwblamst.getSaveArray())); // add
																															// insert
																															// sql
																															// for
																															// master
																															// table
			CommonMessage.debugMsg("db action template chk in dao impl");

			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			CommonMessage.debugMsg("db action template chk in dao impl 22222");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return newBdmTlWwblamst;
	}

	public BdmTlWwblamst update(BdmTlWwblamst bdmTlWwblamst) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();
		try {

			sqls.add(BdmTlWwblamstSql.getUpdateSql(bdmTlWwblamstSql.getWwblDbFields(), bdmTlWwblamst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}

		return bdmTlWwblamst;
	}

	/*
	 * @Override public List<BdmTlWwbladtl> getWwblaValues(BdmTlWwbladtl
	 * bdmTlWwbladtl, BdmTlWwblamst bdmTlWwblamst, String id, String masterId)
	 * throws Exception { try { //bdmTlWwbladtlSql
	 * 
	 * //String sql1 = "";
	 * 
	 * CommonMessage.debugMsg(" Inside Dao Impl For Loadval ::");
	 * 
	 * StringBuffer sql = new StringBuffer(); String sql1 = "";
	 * 
	 * CommonMessage.debugMsg(" ID ::  Dao Impl "+
	 * id+" masterId :: Dao Impl "+masterId);
	 * CommonMessage.debugMsg("functionalLocn.getElementtype()"+
	 * bdmTlWwbladtl.getWwbdOrderno());
	 * CommonMessage.debugMsg("functionalLocn.getParentNumber()"+
	 * bdmTlWwbladtl.getWwbdLevelno());
	 * CommonMessage.debugMsg("functionalLocn.getParentId()"+
	 * bdmTlWwbladtl.getWwbdParentid());
	 * CommonMessage.debugMsg(" Master Id In Dao Impl :: "+
	 * bdmTlWwblamst.getWwblKeyid());
	 * 
	 * 
	 * if(id.equals("1")) {
	 * sql.append("select FISM_PROBLEM from gen_tl_fishbonemst where FISM_KEYID='"
	 * +masterId+"' "); } else{
	 * 
	 * CommonMessage.debugMsg(" Inside 1 ");
	 * 
	 * sql.
	 * append(" select wwbd_phenomena_factor,wwbd_parentid,wwbd_orderno,wwbd_levelno,wwbd_wwbl_keyid,wwbd_keyid "
	 * ); sql.append(" FROM BDM_TL_WWBLADTL,BDM_TL_WWBLAMST ");//fisd_parentid =
	 * '{}' ";\ sql.append(" where 1=1 and WWBL_KEYID=wwbd_wwbl_keyid(+) ");
	 * 
	 * CommonMessage.debugMsg(" Inside 2 "+bdmTlWwbladtl.getWwbdParentid()
	 * +" Inside 3 "+bdmTlWwblamst.getWwblKeyid());
	 * 
	 * if
	 * (CommonFunctions.isValidKeyId(bdmTlWwblamst.getWwblKeyid())||!CommonFunctions
	 * .isValidKeyId(bdmTlWwblamst.getWwblKeyid()))
	 * sql.append(" and WWBL_KEYID = '"+bdmTlWwblamst.getWwblKeyid()+"'");
	 * 
	 * if (CommonFunctions.isValidKeyId(bdmTlWwbladtl.getWwbdParentid()))
	 * sql.append(" and WWBD_PARENTID = '"+bdmTlWwbladtl.getWwbdParentid()+"'");
	 * 
	 * //AND fisd_parentid='FSD0314016'; if
	 * (CommonFunctions.isValidKeyId(bdmTlWwbladtl.getWwbdKeyid())){
	 * 
	 * sql.append(" and WWBD_KEYID<>WWBD_PARENTID ");
	 * 
	 * if(bdmTlWwbladtl.getFisdParentid().trim().equals(MspTlIndicatorsDtlSql.
	 * MasterPlanId)) { sql.append(" AND FISD_PARENTID=FISD_KEYID"); } else {
	 * 
	 * sql.append(" AND FISD_PARENTID='" + bdmTlWwbladtl.getFisdParentid() + "'");
	 * //} }
	 * 
	 * CommonMessage.debugMsg(" sql " + sql);
	 * 
	 * List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
	 * CommonMessage.debugMsg("   resultList size " + resultList.size()); return
	 * bdmTlWwbladtl(resultList);
	 * 
	 * } catch (Exception e) { throw new Exception(e.getMessage()); } }
	 */
	//mano 
	@Override
	public List<BdmTlWwbladtl> getWwblaValues(BdmTlWwbladtl bdmTlWwbladtl,
	        BdmTlWwblamst bdmTlWwblamst, String id, String masterId)
	        throws Exception {
	    try
	    {
	        CommonMessage.debugMsg(" Inside Dao Impl For Loadval ::");

	        StringBuffer sql = new StringBuffer();
	        String __sql1__ = "";

	        CommonMessage.debugMsg(" ID ::  Dao Impl "+ id+" masterId :: Dao Impl "+masterId);
	        CommonMessage.debugMsg("functionalLocn.getElementtype()"+ bdmTlWwbladtl.getWwbdOrderno());
	        CommonMessage.debugMsg("functionalLocn.getParentNumber()"+ bdmTlWwbladtl.getWwbdLevelno());
	        CommonMessage.debugMsg("functionalLocn.getParentId()"+ bdmTlWwbladtl.getWwbdParentid());
	        CommonMessage.debugMsg(" Master Id In Dao Impl :: "+ bdmTlWwblamst.getWwblKeyid());

	        CommonMessage.debugMsg(" Inside 1 ");

	        sql.append(" SELECT wwbd_phenomena_factor, wwbd_parentid, wwbd_orderno, wwbd_levelno, wwbd_wwbl_keyid, wwbd_keyid ");
	        sql.append(" FROM bdm_tl_wwbladtl ");
	        sql.append(" LEFT JOIN bdm_tl_wwblamst ON wwbl_keyid = wwbd_wwbl_keyid ");
	        sql.append(" WHERE 1=1 ");

	        CommonMessage.debugMsg(" Inside 2 "+bdmTlWwbladtl.getWwbdParentid()+" Inside 3 "+bdmTlWwblamst.getWwblKeyid());

	        if (CommonFunctions.isValidKeyId(bdmTlWwblamst.getWwblKeyid())||!CommonFunctions.isValidKeyId(bdmTlWwblamst.getWwblKeyid()))
	            sql.append(" AND wwbl_keyid = '"+bdmTlWwblamst.getWwblKeyid()+"'");

	        if (CommonFunctions.isValidKeyId(bdmTlWwbladtl.getWwbdParentid()))
	            sql.append(" AND wwbd_parentid = '"+bdmTlWwbladtl.getWwbdParentid()+"'");

	        if (CommonFunctions.isValidKeyId(bdmTlWwbladtl.getWwbdKeyid())){
	            sql.append(" AND wwbd_keyid <> wwbd_parentid ");
	        }

	        CommonMessage.debugMsg(" sql " + sql);

	        List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
	        CommonMessage.debugMsg("   resultList size " + resultList.size());
	        return bdmTlWwbladtl(resultList);

	    }
	    catch (Exception e)
	    {
	        throw new Exception(e.getMessage());
	    }
	}
	private List<BdmTlWwbladtl> bdmTlWwbladtl(List<String[]> resultList) throws SQLException {
		CommonMessage.debugMsg(" Inside Dao Impl result list :: ");
		List<BdmTlWwbladtl> menus = new ArrayList<BdmTlWwbladtl>();
		if (resultList.size() > 0) {
			CommonMessage.debugMsg(" Inside Dao Impl For11 :: ");
			for (String[] row : resultList) {
				BdmTlWwbladtl fl = new BdmTlWwbladtl();
				// fl.setFisdCause(row[0]);
				fl.setWwbdOrderno(row[3]);
				fl.setWwbdLevelno(row[2]);
				fl.setWwbdParentid(row[1]);
				fl.setWwbdCountermeasure(row[0]);
				fl.setWwbdWwblKeyid(row[4]);
				fl.setWwbdKeyid(row[5]);

				menus.add(fl);

			}
		}
		return menus;
	}

	@Override
	public List<String[]> getSearchNode(String searchNode, String originalId) throws Exception {
		try {
			BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();
			String sql = bdmTlWwblamstSql.getSearchNodeSql(searchNode, originalId);
			CommonMessage.debugMsg("Search SQL : " + sql);
			return dbActionTemplate.getDataList(sql);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public BdmTlWwbladtl createChildEntry(BdmTlWwbladtl newBdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl,
			String editval) throws Exception {
		// TODO Auto-generated method stub

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		BdmTlWwbladtlSql bdmTlWwbladtlSql = new BdmTlWwbladtlSql();// contains dbtable,field names, Field types and
																	// related sqls of master table
		try {
			if ("Editval".equals(editval)) {
				CommonMessage.debugMsg(" Editval :: IF " + newBdmTlWwbladtl.getWwblParent());
				sqls.add("UPDATE BDM_TL_WWBLADTL SET WWBD_PHENOMENA_FACTOR='" + newBdmTlWwbladtl.getWwblParent()
						+ "' WHERE WWBD_KEYID='" + newBdmTlWwbladtl.getWwbdParentid() + "'");
			} else {
				CommonMessage.debugMsg(" Editval :: ELSE ");
				newBdmTlWwbladtl.setWwbdKeyid(dbActionTemplate.getSequenceNumber(BdmTlWwbladtlSql.TBL_BDM_TL_WWBLADTL,
						10, "WWD", "MMYY", "Y"));
				sqls.add(BdmTlWwbladtlSql.getInsertSql(bdmTlWwbladtlSql.getWwbdDbFields(),
						newBdmTlWwbladtl.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return newBdmTlWwbladtl;
	}

	@Override
	public BdmTlWwbladtl updateChildEntry(BdmTlWwbladtl newBdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BdmTlWwbladtl deleteWWBLAChildEntry(BdmTlWwbladtl newBdmTlWwbladtl) throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {

			List<String> sqls = new ArrayList<String>();
			BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();// contains dbtable,field names, Field types and
																		// related sqls of master table
			BdmTlWwbladtlSql bdmTlWwbladtlSql = new BdmTlWwbladtlSql();

			sqls.add("Delete from " + bdmTlWwbladtlSql.TBL_BDM_TL_WWBLADTL + " where WWBD_KEYID='"
					+ newBdmTlWwbladtl.getWwbdKeyid() + "'");
			sqls.add("Delete from " + bdmTlWwbladtlSql.TBL_BDM_TL_WWBLADTL + " where  WWBD_PARENTID ='"
					+ newBdmTlWwbladtl.getWwbdKeyid() + "'");

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newBdmTlWwbladtl;
	}

	@Override
	public List<String[]> WwblachildData(String dtlId) throws Exception {
		// TODO Auto-generated method stub
		String sql = BdmTlWwbladtlSql.selectchildData(dtlId);
		CommonMessage.debugMsg("sql  " + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	public List<String[]> getAllWwblaGrid(CommonFilter commonFilter) throws Exception {

		try {
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("test to............");

			paramValues.add(condParms);
			paramValues.add(commonParams);

			List<String[]> dataList = dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.GEN_FN_WWBLAMAINGRID",
					paramValues);

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt..." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}

			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
//e.printStackTrace();
		}

	}

	@Override
	public Workbook getWwblaExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {

			rs = getWwblaResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);

			return excelUtils.writeToExcel(rs, format, 2, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}

	}

	public BdmTlWwblamst update(BdmTlWwblamst newBdmTlWwblamst, BdmTlWwblamst existBdmTlWwblamst) throws Exception {
		List<String> sqls = new ArrayList<String>();
		BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();
		try {

			sqls.add(
					BdmTlWwblamstSql.getUpdateSql(bdmTlWwblamstSql.getWwblDbFields(), newBdmTlWwblamst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return newBdmTlWwblamst;
	}

	public BdmTlWwblamst delete(BdmTlWwblamst newBdmTlWwblamst) throws Exception {

		try {

			List<String> sqls = new ArrayList<String>();
			BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();// contains dbtable,field names, Field types and
																		// related sqls of master table
			BdmTlWwbladtlSql bdmTlWwbladtlSql = new BdmTlWwbladtlSql();

			sqls.add("Delete from " + bdmTlWwbladtlSql.TBL_BDM_TL_WWBLADTL + " where  WWBD_WWBL_KEYID ='"
					+ newBdmTlWwblamst.getWwblKeyid() + "'");
			sqls.add("Delete from " + bdmTlWwblamstSql.TBL_BDM_TL_WWBLAMST + " where WWBL_KEYID='"
					+ newBdmTlWwblamst.getWwblKeyid() + "'");

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newBdmTlWwblamst;
	}

	public BdmTlWwblamst getWwbla(String keyid) throws Exception {
		try {

			BdmTlWwblamstSql bdmTlWwblamstSql = new BdmTlWwblamstSql();
			// CommonMessage.debugMsg("input5");
			BdmTlWwblamst newBdmTlWwblamst = new BdmTlWwblamst();
			CommonMessage.debugMsg("input6");
			String sql = bdmTlWwblamstSql.getselectsql();
			// CommonMessage.debugMsg("input7");
			Object[] args = new Object[] { keyid };
			// CommonMessage.debugMsg("keyid:::::"+keyid);
			newBdmTlWwblamst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			// CommonMessage.debugMsg("keyid:::::"+keyid);
			return newBdmTlWwblamst;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	private ResultSet getWwblaResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.GEN_FN_WWBLAMAINGRID", paramValues);

	}

}
