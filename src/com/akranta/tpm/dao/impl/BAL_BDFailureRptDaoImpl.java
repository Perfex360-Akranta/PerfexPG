package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_BDFailureRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_BDFailureRptDaoImpl implements BAL_BDFailureRptDao {

private DBActionTemplate dbActionTemplate;
	
	public BAL_BDFailureRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String []> getFrequency(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_FAILURESEVERITY", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	public List<String []> getBDSplit(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_BDSHIFTBREAKUP", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	public Workbook getfailureRateExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getfailureRateExportExcelResultSet(commonFilter);
			CommonFunctions.debugMsg("TE.."+rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			CommonFunctions.debugMsg("rptFormat..."+rptFormat);
			
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getfailureRateExportExcelResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_FAILURESEVERITY", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	
	public Workbook splitBreakupExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getSplitBreakupExportExcelResultSet(commonFilter);
			CommonFunctions.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getSplitBreakupExportExcelResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_BDSHIFTBREAKUP", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	

}
