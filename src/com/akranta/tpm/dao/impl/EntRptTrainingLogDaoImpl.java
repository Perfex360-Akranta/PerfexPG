package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.EntRptTrainingLogDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class EntRptTrainingLogDaoImpl implements EntRptTrainingLogDao
{
	private DBActionTemplate dbActionTemplate; 
	public EntRptTrainingLogDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<String[]> getRptTrainingLog(CommonFilter commonFilter) throws Exception
	{
		
		List<String > paramValues = new ArrayList<String>();

		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";

		
		 
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
	
		CommonMessage.debugMsg("paramvalues=" + paramValues);
		try
		{
			List<String[]> rootRptList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGLOG", paramValues);
			CommonMessage.debugMsg("rootRptList:"+rootRptList.get(0)[6]);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("rootRptList="+rootRptList);
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getTainingLogRpt(CommonFilter commonFilter,
			JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			CommonMessage.debugMsg("rs="+rs);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat,0,1,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGLOG", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	
	 
}
