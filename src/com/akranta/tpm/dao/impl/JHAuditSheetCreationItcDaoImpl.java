package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;

import com.akranta.tpm.dao.JHAuditSheetCreationItcDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class JHAuditSheetCreationItcDaoImpl implements JHAuditSheetCreationItcDao {	
	
	private DBActionTemplate dbActionTemplate; 
	private JHAuditSheetCreationItcServiApi jhauditSheetCreationItcServiApi;
	FunctionCallApi fnCallApi;
	
	public JHAuditSheetCreationItcDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void JhaTlAuditmstItcDaoImplJwt(String jwtToken) {
		
		try{
			jhauditSheetCreationItcServiApi = new JHAuditSheetCreationItcServiApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String[]> getjhAuditSheetfillGrid(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="PILLAR="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="AUDITTYPE="+commonFilter.getType()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			//List<String[]> rootRptList=dbActionTemplate.processFunctionCalls("JHN_FN_JHAUDITSHEET", paramValues);
			List<String[]> rootRptList=fnCallApi.callFunction("JHN_FN_JHAUDITSHEET_SB", paramValues,2,true);
			
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
		
			return rootRptList;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	public Workbook jhAuditSheetExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   jhAuditSheetReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet jhAuditSheetReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="PILLAR="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="AUDITTYPE="+commonFilter.getType()+";";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("JHN_FN_JHAUDITSHEET", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}


	
		
	}

	

