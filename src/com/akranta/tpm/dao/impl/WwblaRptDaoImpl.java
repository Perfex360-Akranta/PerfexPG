package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.WwblaRptDao;
import com.akranta.tpm.dao.sql.BdmTlWwblamstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFishbonemstSql;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class WwblaRptDaoImpl implements WwblaRptDao{
private DBActionTemplate dbActionTemplate;
	
	public WwblaRptDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;		
	}
	public List<String[]> getAllWwblaGrid(CommonFilter commonFilter) throws Exception {
	       
		/* StringBuffer sql = new StringBuffer();
		
		sql.append("Select 'Title' as TITLE,'Effect'  AS PROBLEM");
		sql.append(" From Dual UNION ");
		sql.append("Select 'Title1' as TITLE,'Problem Statement - 20% scrap on Paper Mill due to poor registration'  AS PROBLEM ");
		sql.append(" From Dual UNION ");
		sql.append("Select 'Title2' as TITLE,'Problem' AS PROBLEM ");
		sql.append(" From Dual ");
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;*/
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.GEN_FN_WWBLAMAINGRID", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
		
	}
	public Workbook getWwblaExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
	     ResultSet rs = null;
		   try{
			
			rs =   getWwblaResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		   
	}	
	
	private ResultSet getWwblaResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.GEN_FN_WWBLAMAINGRID", paramValues);
	
	}
	
	/*public BdmTlWwblamst getFillControl(String wwblaKeyId)
	throws Exception {
// TODO Auto-generated method stub
		BdmTlWwblamst newBdmTlWwblamst= new BdmTlWwblamst();
String sql = BdmTlWwblamstSql.getWwbladata();
Object args[] = new Object[] {wwblaKeyId};
newBdmTlWwblamst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
return newBdmTlWwblamst;
}*/
	
	
	/*
	 * public List<String[]> getWwblaDetail(String keyid) throws Exception {
	 * 
	 * 
	 * 
	 * 
	 * StringBuilder sql = new StringBuilder();
	 * 
	 * sql.append(" SELECT ");
	 * sql.append("  DECODE(level, 1, WWBD_PHENOMENA_FACTOR) level_1 , ");
	 * sql.append( " DECODE(level, 2, WWBD_PHENOMENA_FACTOR) level_2 , ");
	 * sql.append( " DECODE(level, 3, WWBD_PHENOMENA_FACTOR) level_3 , ");
	 * sql.append( " DECODE(level, 4, WWBD_PHENOMENA_FACTOR) level_4 , ");
	 * sql.append( " DECODE(level, 5, WWBD_PHENOMENA_FACTOR) level_5 , ");
	 * sql.append( " DECODE(level, 6, WWBD_PHENOMENA_FACTOR) level_6 , ");
	 * sql.append( " DECODE(level, 7, WWBD_PHENOMENA_FACTOR) level_7 , ");
	 * sql.append( " DECODE(level, 8, WWBD_PHENOMENA_FACTOR) level_8 , ");
	 * sql.append("  DECODE(level, 9, WWBD_PHENOMENA_FACTOR) level_9 , ");
	 * sql.append( " DECODE(level, 10, WWBD_PHENOMENA_FACTOR) level_10, ");
	 * 
	 * sql.append("  DECODE(level, 11, WWBD_PHENOMENA_FACTOR) level_11 , ");
	 * sql.append( " DECODE(level, 12, WWBD_PHENOMENA_FACTOR) level_12 , ");
	 * sql.append( " DECODE(level, 13, WWBD_PHENOMENA_FACTOR) level_13 , ");
	 * sql.append( " DECODE(level, 14, WWBD_PHENOMENA_FACTOR) level_14 , ");
	 * sql.append( " DECODE(level, 15, WWBD_PHENOMENA_FACTOR) level_15 , ");
	 * sql.append( " DECODE(level, 16, WWBD_PHENOMENA_FACTOR) level_16 , ");
	 * sql.append( " DECODE(level, 17, WWBD_PHENOMENA_FACTOR) level_17 , ");
	 * sql.append( " DECODE(level, 18, WWBD_PHENOMENA_FACTOR) level_18 , ");
	 * sql.append("  DECODE(level, 19, WWBD_PHENOMENA_FACTOR) level_19 , ");
	 * sql.append( " DECODE(level, 20, WWBD_PHENOMENA_FACTOR) level_20, ");
	 * 
	 * sql.append("  DECODE(level, 21, WWBD_PHENOMENA_FACTOR) level_21 , ");
	 * sql.append( " DECODE(level, 22, WWBD_PHENOMENA_FACTOR) level_22 , ");
	 * sql.append( " DECODE(level, 23, WWBD_PHENOMENA_FACTOR) level_23 , ");
	 * sql.append( " DECODE(level, 24, WWBD_PHENOMENA_FACTOR) level_24 , ");
	 * sql.append( " DECODE(level, 25, WWBD_PHENOMENA_FACTOR) level_25 , ");
	 * sql.append( " DECODE(level, 26, WWBD_PHENOMENA_FACTOR) level_26 , ");
	 * sql.append( " DECODE(level, 27, WWBD_PHENOMENA_FACTOR) level_27 , ");
	 * sql.append( " DECODE(level, 28, WWBD_PHENOMENA_FACTOR) level_28 , ");
	 * sql.append("  DECODE(level, 29, WWBD_PHENOMENA_FACTOR) level_29 , ");
	 * sql.append( " DECODE(level, 30, WWBD_PHENOMENA_FACTOR) level_30  ");
	 * 
	 * sql.append( " FROM BDM_TL_WWBLADTL " );
	 * 
	 * if (CommonFunctions.isValidKeyId(keyid)) { // String keys="FSH0515006";
	 * sql.append( " where WWBD_WWBL_KEYID ='" + keyid +"'" ); //sql.append(
	 * " where FISD_FISM_KEYID ='" + keys +"'" ); } sql.append(
	 * " START  WITH WWBD_PARENTID='WWBLA001'	" ) ; sql.append(
	 * " CONNECT  BY prior WWBD_KEYID = WWBD_PARENTID " ) ;
	 * 
	 * 
	 * 
	 * CommonMessage.debugMsg(" sql " + sql.toString()); List<String []>
	 * resultList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
	 * CommonMessage.debugMsg("resultList::"+resultList);
	 * CommonMessage.debugMsg(" resultList size " + resultList.size());
	 * 
	 * return resultList; }
	 */
	
	//mano 
	public List<String[]> getWwblaDetail(String keyid) throws Exception {

	    StringBuilder sql = new StringBuilder();

	    sql.append(" WITH RECURSIVE hierarchy AS ( ");
	    sql.append("   SELECT ");
	    sql.append("     WWBD_KEYID, ");
	    sql.append("     WWBD_PARENTID, ");
	    sql.append("     WWBD_PHENOMENA_FACTOR, ");
	    sql.append("     1 as level ");
	    sql.append("   FROM BDM_TL_WWBLADTL ");
	    sql.append("   WHERE WWBD_PARENTID = 'WWBLA001' ");
	    
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        sql.append("     AND WWBD_WWBL_KEYID = '").append(keyid).append("' ");
	    }
	    
	    sql.append("   UNION ALL ");
	    sql.append("   SELECT ");
	    sql.append("     t.WWBD_KEYID, ");
	    sql.append("     t.WWBD_PARENTID, ");
	    sql.append("     t.WWBD_PHENOMENA_FACTOR, ");
	    sql.append("     h.level + 1 ");
	    sql.append("   FROM BDM_TL_WWBLADTL t ");
	    sql.append("   INNER JOIN hierarchy h ON t.WWBD_PARENTID = h.WWBD_KEYID ");
	    
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        sql.append("   WHERE t.WWBD_WWBL_KEYID = '").append(keyid).append("' ");
	    }
	    
	    sql.append(" ) ");
	    sql.append(" SELECT ");
	    sql.append("   CASE WHEN level = 1 THEN WWBD_PHENOMENA_FACTOR END as level_1, ");
	    sql.append("   CASE WHEN level = 2 THEN WWBD_PHENOMENA_FACTOR END as level_2, ");
	    sql.append("   CASE WHEN level = 3 THEN WWBD_PHENOMENA_FACTOR END as level_3, ");
	    sql.append("   CASE WHEN level = 4 THEN WWBD_PHENOMENA_FACTOR END as level_4, ");
	    sql.append("   CASE WHEN level = 5 THEN WWBD_PHENOMENA_FACTOR END as level_5, ");
	    sql.append("   CASE WHEN level = 6 THEN WWBD_PHENOMENA_FACTOR END as level_6, ");
	    sql.append("   CASE WHEN level = 7 THEN WWBD_PHENOMENA_FACTOR END as level_7, ");
	    sql.append("   CASE WHEN level = 8 THEN WWBD_PHENOMENA_FACTOR END as level_8, ");
	    sql.append("   CASE WHEN level = 9 THEN WWBD_PHENOMENA_FACTOR END as level_9, ");
	    sql.append("   CASE WHEN level = 10 THEN WWBD_PHENOMENA_FACTOR END as level_10, ");
	    sql.append("   CASE WHEN level = 11 THEN WWBD_PHENOMENA_FACTOR END as level_11, ");
	    sql.append("   CASE WHEN level = 12 THEN WWBD_PHENOMENA_FACTOR END as level_12, ");
	    sql.append("   CASE WHEN level = 13 THEN WWBD_PHENOMENA_FACTOR END as level_13, ");
	    sql.append("   CASE WHEN level = 14 THEN WWBD_PHENOMENA_FACTOR END as level_14, ");
	    sql.append("   CASE WHEN level = 15 THEN WWBD_PHENOMENA_FACTOR END as level_15, ");
	    sql.append("   CASE WHEN level = 16 THEN WWBD_PHENOMENA_FACTOR END as level_16, ");
	    sql.append("   CASE WHEN level = 17 THEN WWBD_PHENOMENA_FACTOR END as level_17, ");
	    sql.append("   CASE WHEN level = 18 THEN WWBD_PHENOMENA_FACTOR END as level_18, ");
	    sql.append("   CASE WHEN level = 19 THEN WWBD_PHENOMENA_FACTOR END as level_19, ");
	    sql.append("   CASE WHEN level = 20 THEN WWBD_PHENOMENA_FACTOR END as level_20, ");
	    sql.append("   CASE WHEN level = 21 THEN WWBD_PHENOMENA_FACTOR END as level_21, ");
	    sql.append("   CASE WHEN level = 22 THEN WWBD_PHENOMENA_FACTOR END as level_22, ");
	    sql.append("   CASE WHEN level = 23 THEN WWBD_PHENOMENA_FACTOR END as level_23, ");
	    sql.append("   CASE WHEN level = 24 THEN WWBD_PHENOMENA_FACTOR END as level_24, ");
	    sql.append("   CASE WHEN level = 25 THEN WWBD_PHENOMENA_FACTOR END as level_25, ");
	    sql.append("   CASE WHEN level = 26 THEN WWBD_PHENOMENA_FACTOR END as level_26, ");
	    sql.append("   CASE WHEN level = 27 THEN WWBD_PHENOMENA_FACTOR END as level_27, ");
	    sql.append("   CASE WHEN level = 28 THEN WWBD_PHENOMENA_FACTOR END as level_28, ");
	    sql.append("   CASE WHEN level = 29 THEN WWBD_PHENOMENA_FACTOR END as level_29, ");
	    sql.append("   CASE WHEN level = 30 THEN WWBD_PHENOMENA_FACTOR END as level_30 ");
	    sql.append(" FROM hierarchy ");
	    sql.append(" ORDER BY level ");

	    CommonMessage.debugMsg(" sql " + sql.toString());
	    List<String[]> resultList = dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	    CommonMessage.debugMsg("resultList::" + resultList);
	    CommonMessage.debugMsg(" resultList size " + resultList.size());

	    return resultList;
	}

	/*
	 * private ResultSet getWwblaValueResultSet(String keyid) throws Exception { //
	 * TODO Auto-generated method stub StringBuilder sql = new StringBuilder();
	 * 
	 * sql.append(" SELECT ");
	 * sql.append("  DECODE(level, 1, WWBD_PHENOMENA_FACTOR) level_1 , ");
	 * sql.append( " DECODE(level, 2, WWBD_PHENOMENA_FACTOR) level_2 , ");
	 * sql.append( " DECODE(level, 3, WWBD_PHENOMENA_FACTOR) level_3 , ");
	 * sql.append( " DECODE(level, 4, WWBD_PHENOMENA_FACTOR) level_4 , ");
	 * sql.append( " DECODE(level, 5, WWBD_PHENOMENA_FACTOR) level_5 , ");
	 * sql.append( " DECODE(level, 6, WWBD_PHENOMENA_FACTOR) level_6 , ");
	 * sql.append( " DECODE(level, 7, WWBD_PHENOMENA_FACTOR) level_7 , ");
	 * sql.append( " DECODE(level, 8, WWBD_PHENOMENA_FACTOR) level_8 , ");
	 * sql.append("  DECODE(level, 9, WWBD_PHENOMENA_FACTOR) level_9 , ");
	 * sql.append( " DECODE(level, 10, WWBD_PHENOMENA_FACTOR) level_10, ");
	 * 
	 * sql.append("  DECODE(level, 11, WWBD_PHENOMENA_FACTOR) level_11 , ");
	 * sql.append( " DECODE(level, 12, WWBD_PHENOMENA_FACTOR) level_12 , ");
	 * sql.append( " DECODE(level, 13, WWBD_PHENOMENA_FACTOR) level_13 , ");
	 * sql.append( " DECODE(level, 14, WWBD_PHENOMENA_FACTOR) level_14 , ");
	 * sql.append( " DECODE(level, 15, WWBD_PHENOMENA_FACTOR) level_15 , ");
	 * sql.append( " DECODE(level, 16, WWBD_PHENOMENA_FACTOR) level_16 , ");
	 * sql.append( " DECODE(level, 17, WWBD_PHENOMENA_FACTOR) level_17 , ");
	 * sql.append( " DECODE(level, 18, WWBD_PHENOMENA_FACTOR) level_18 , ");
	 * sql.append("  DECODE(level, 19, WWBD_PHENOMENA_FACTOR) level_19 , ");
	 * sql.append( " DECODE(level, 20, WWBD_PHENOMENA_FACTOR) level_20, ");
	 * 
	 * sql.append("  DECODE(level, 21, WWBD_PHENOMENA_FACTOR) level_21 , ");
	 * sql.append( " DECODE(level, 22, WWBD_PHENOMENA_FACTOR) level_22 , ");
	 * sql.append( " DECODE(level, 23, WWBD_PHENOMENA_FACTOR) level_23 , ");
	 * sql.append( " DECODE(level, 24, WWBD_PHENOMENA_FACTOR) level_24 , ");
	 * sql.append( " DECODE(level, 25, WWBD_PHENOMENA_FACTOR) level_25 , ");
	 * sql.append( " DECODE(level, 26, WWBD_PHENOMENA_FACTOR) level_26 , ");
	 * sql.append( " DECODE(level, 27, WWBD_PHENOMENA_FACTOR) level_27 , ");
	 * sql.append( " DECODE(level, 28, WWBD_PHENOMENA_FACTOR) level_28 , ");
	 * sql.append("  DECODE(level, 29, WWBD_PHENOMENA_FACTOR) level_29 , ");
	 * sql.append( " DECODE(level, 30, WWBD_PHENOMENA_FACTOR) level_30  ");
	 * 
	 * sql.append( " FROM BDM_TL_WWBLADTL " );
	 * 
	 * if (CommonFunctions.isValidKeyId(keyid)) { // String keys="FSH0515006";
	 * sql.append( " where WWBD_WWBL_KEYID ='" + keyid +"'" ); //sql.append(
	 * " where FISD_FISM_KEYID ='" + keys +"'" ); } sql.append(
	 * " START  WITH WWBD_PARENTID='WWBLA001'	" ) ; sql.append(
	 * " CONNECT  BY prior WWBD_KEYID = WWBD_PARENTID " ) ;
	 * 
	 * return dbActionTemplate.getData(sql.toString()); }
	 */
	
	private ResultSet getWwblaValueResultSet(String keyid) throws Exception {
	    // TODO Auto-generated method stub
	    StringBuilder sql = new StringBuilder();

	    sql.append(" WITH RECURSIVE hierarchy AS ( ");
	    sql.append("   SELECT ");
	    sql.append("     WWBD_KEYID, ");
	    sql.append("     WWBD_PARENTID, ");
	    sql.append("     WWBD_PHENOMENA_FACTOR, ");
	    sql.append("     WWBD_WWBL_KEYID, ");
	    sql.append("     1 as level ");
	    sql.append("   FROM BDM_TL_WWBLADTL ");
	    sql.append("   WHERE WWBD_PARENTID = 'WWBLA001' ");
	    
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        sql.append("     AND WWBD_WWBL_KEYID = '").append(keyid).append("' ");
	    }
	    
	    sql.append("   UNION ALL ");
	    sql.append("   SELECT ");
	    sql.append("     t.WWBD_KEYID, ");
	    sql.append("     t.WWBD_PARENTID, ");
	    sql.append("     t.WWBD_PHENOMENA_FACTOR, ");
	    sql.append("     t.WWBD_WWBL_KEYID, ");
	    sql.append("     h.level + 1 ");
	    sql.append("   FROM BDM_TL_WWBLADTL t ");
	    sql.append("   INNER JOIN hierarchy h ON t.WWBD_PARENTID = h.WWBD_KEYID ");
	    
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        sql.append("   WHERE t.WWBD_WWBL_KEYID = '").append(keyid).append("' ");
	    }
	    
	    sql.append(" ) ");
	    sql.append(" SELECT ");
	    sql.append("   CASE WHEN level = 1 THEN WWBD_PHENOMENA_FACTOR END as level_1, ");
	    sql.append("   CASE WHEN level = 2 THEN WWBD_PHENOMENA_FACTOR END as level_2, ");
	    sql.append("   CASE WHEN level = 3 THEN WWBD_PHENOMENA_FACTOR END as level_3, ");
	    sql.append("   CASE WHEN level = 4 THEN WWBD_PHENOMENA_FACTOR END as level_4, ");
	    sql.append("   CASE WHEN level = 5 THEN WWBD_PHENOMENA_FACTOR END as level_5, ");
	    sql.append("   CASE WHEN level = 6 THEN WWBD_PHENOMENA_FACTOR END as level_6, ");
	    sql.append("   CASE WHEN level = 7 THEN WWBD_PHENOMENA_FACTOR END as level_7, ");
	    sql.append("   CASE WHEN level = 8 THEN WWBD_PHENOMENA_FACTOR END as level_8, ");
	    sql.append("   CASE WHEN level = 9 THEN WWBD_PHENOMENA_FACTOR END as level_9, ");
	    sql.append("   CASE WHEN level = 10 THEN WWBD_PHENOMENA_FACTOR END as level_10, ");
	    sql.append("   CASE WHEN level = 11 THEN WWBD_PHENOMENA_FACTOR END as level_11, ");
	    sql.append("   CASE WHEN level = 12 THEN WWBD_PHENOMENA_FACTOR END as level_12, ");
	    sql.append("   CASE WHEN level = 13 THEN WWBD_PHENOMENA_FACTOR END as level_13, ");
	    sql.append("   CASE WHEN level = 14 THEN WWBD_PHENOMENA_FACTOR END as level_14, ");
	    sql.append("   CASE WHEN level = 15 THEN WWBD_PHENOMENA_FACTOR END as level_15, ");
	    sql.append("   CASE WHEN level = 16 THEN WWBD_PHENOMENA_FACTOR END as level_16, ");
	    sql.append("   CASE WHEN level = 17 THEN WWBD_PHENOMENA_FACTOR END as level_17, ");
	    sql.append("   CASE WHEN level = 18 THEN WWBD_PHENOMENA_FACTOR END as level_18, ");
	    sql.append("   CASE WHEN level = 19 THEN WWBD_PHENOMENA_FACTOR END as level_19, ");
	    sql.append("   CASE WHEN level = 20 THEN WWBD_PHENOMENA_FACTOR END as level_20, ");
	    sql.append("   CASE WHEN level = 21 THEN WWBD_PHENOMENA_FACTOR END as level_21, ");
	    sql.append("   CASE WHEN level = 22 THEN WWBD_PHENOMENA_FACTOR END as level_22, ");
	    sql.append("   CASE WHEN level = 23 THEN WWBD_PHENOMENA_FACTOR END as level_23, ");
	    sql.append("   CASE WHEN level = 24 THEN WWBD_PHENOMENA_FACTOR END as level_24, ");
	    sql.append("   CASE WHEN level = 25 THEN WWBD_PHENOMENA_FACTOR END as level_25, ");
	    sql.append("   CASE WHEN level = 26 THEN WWBD_PHENOMENA_FACTOR END as level_26, ");
	    sql.append("   CASE WHEN level = 27 THEN WWBD_PHENOMENA_FACTOR END as level_27, ");
	    sql.append("   CASE WHEN level = 28 THEN WWBD_PHENOMENA_FACTOR END as level_28, ");
	    sql.append("   CASE WHEN level = 29 THEN WWBD_PHENOMENA_FACTOR END as level_29, ");
	    sql.append("   CASE WHEN level = 30 THEN WWBD_PHENOMENA_FACTOR END as level_30 ");
	    sql.append(" FROM hierarchy ");
	    sql.append(" ORDER BY level ");

	    return dbActionTemplate.getData(sql.toString());
	}
	@Override
	public Workbook getWwblaDetailForExcel(String keyid,JSONObject tblJSONObj,String format) throws Exception {
		ResultSet rs = null;		
		rs =   getWwblaValueResultSet(keyid);
		
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		CommonMessage.debugMsg(rs);
		return excelUtils.writeToExcel(rs,format,0 ,0,0 );
	}

}
