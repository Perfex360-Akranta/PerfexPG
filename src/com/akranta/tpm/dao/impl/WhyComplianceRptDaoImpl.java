
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.Request;
import com.akranta.tpm.dao.WhyComplianceRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;


public class WhyComplianceRptDaoImpl implements WhyComplianceRptDao {
	


	private DBActionTemplate dbActionTemplate; 
	
	public WhyComplianceRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getAllyydrill(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			CommonMessage.debugMsg("paramvalues" + paramValues);
			
			List<String[]> whyCompList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPLIANCE", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return whyCompList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	public List<String[]> getAllyycelldrill( String code,CommonFilter commonFilter) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 CommonMessage.debugMsg("Inside  yy DAO Impl cellllll");
			
			 CommonMessage.debugMsg("Inside  yy DAO Impl cellllll " +code);
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms+"COLVAL="+code+";");
			 paramValues.add(commonParams);
			
			
			
			CommonMessage.debugMsg("paramvalues" + paramValues);
			List<String[]> whyComprowList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPBDDETAILS", paramValues);
			CommonMessage.debugMsg("Inside  yy DAO Impl cellllll" +whyComprowList.size());
			if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return whyComprowList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
				

		
	public List<String[]> getAllmonth(CommonFilter commonFilter)throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside  yy DAO Implmonthhhhhhhhhhhhhhhhhhhh");
			
			List<String > paramValues = new ArrayList<String>();
				
			
			
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("paramvalues" + paramValues);
			 List<String[]> whyCompmonList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPMONTHWISE", paramValues);
		
			 CommonMessage.debugMsg("Inside  yy DAO Impl whyCompmonList" +whyCompmonList.size());
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			 return whyCompmonList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getWhycompmonthExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPLIANCE", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	@Override
	public Workbook getWhydetailExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdetailReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdetailReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPBDDETAILS", paramValues);
	}
	
	
	@Override
	public Workbook getWhymonthExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getmonthReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getmonthReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_YYCOMPMONTHWISE", paramValues);
	}
	
}