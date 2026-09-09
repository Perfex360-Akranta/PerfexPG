package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AppAccessReviewrptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class AppAccessReviewrptDaoImpl implements AppAccessReviewrptDao {
	private DBActionTemplate dbActionTemplate;
	public AppAccessReviewrptDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate=dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate=dbActionTemplate;
	}

	@Override
	public List<String[]> getAppAccessReview(CommonFilter commonFilter) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			CommonMessage.debugMsg("before ");
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues are:"+paramValues);	
			//dbActionTemplate.processFunctionCalls("TEST4_PC_TEST4.ADM_FN_APPACCREVRPT", paramValues);
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls("TEST_PC_TEST1.ADM_FN_APPACCREVRPT", paramValues);
			//List<String[]> dataList = dbActionTemplate.processFunctionCalls("TEST4_PC_TEST4.ADM_FN_APPACCREVRPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("total count is"+totalCnt);
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("data is:::"+dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	

	@Override
	public Workbook AppAccessReviewReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format)throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getAppAccessReviewrptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getAppAccessReviewrptResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		//String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST1.ADM_FN_APPACCREVRPT", paramValues);
	}
	}
	


