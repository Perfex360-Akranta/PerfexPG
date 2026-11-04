package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_MaintActivityDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.BAL_MaintActivity;
import com.akranta.tpm.model.MasterModuleGroup;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class BAL_MaintActivityDaoImpl implements BAL_MaintActivityDao {
	

	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;

	
	public BAL_MaintActivityDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BAL_MaintActivityDaoImplJwt(String JwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		List<String> paramValues  = new ArrayList<String>();
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		 
		return paramValues; 
	}
	
	public List<String[]> getAllMaintActivity(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls(getFunctionName(commonFilter), paramValues);
			
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
	
	private String getFunctionName(CommonFilter commonFilter){
		String functionName = "BDM_PC_BREAKDOWN.BDM_FN_ACTWISERPT";
		if( "Y".equals( commonFilter.getEmpWise()) )
			functionName = "BDM_PC_BREAKDOWN.BDM_FN_EMPWISEACTIVITYRPT";
		return functionName;
	}
	
	
	/*public Workbook activityWiseRptExportExcel(CommonFilter commonFilter,JSONObject tablemodel,String format) throws Exception{
		  
		  // ResultSet rs = null;
		   try{
			List<String> paramValues = getFilterParamValues(commonFilter);
			rs =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_EMPWISEACTIVITYRPTTEMP", paramValues);
			CommonFunctions.debugMsg("rs"+rs);
			ExcelUtils excelUtils = new ExcelUtils(tablemodel);
			return excelUtils.writeToExcel(rs,"xls",  2, 0,2 );
			
		   }
		   catch(Exception e)
		   {
			   System.out.println("activityWiseRptExportExcel Exception :"+e.getMessage());
		   }
		   
		   finally{
			   if( rs != null){
				   Connection con  = rs.getStatement().getConnection();
				   rs.close();
				   rs = null;
				   if( con != null )
				   {
					   con.close();
				   }
				   
			   }	   
		   }
		return null;
	}*/
	@Override
	public Workbook activityWiseRptExportExcel(CommonFilter commonFilter,JSONObject tablemodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getActivityWiseReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tablemodel);
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
			CommonFunctions.debugMsg("ActivityWiseRpt");
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	
	private ResultSet getActivityWiseReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rs = null;
		rs = dbActionTemplate.dbFunctionCall(getFunctionName(commonFilter), paramValues);//dbActionTemplate.dbFunctionCall(
			//	"BDM_PC_BREAKDOWN.BDM_FN_ACTWISERPTTEMP", paramValues);
		return rs;
		//return dbActionTemplate.dbFunctionCall(getFunctionName(commonFilter), paramValues);
	}
	
	@Override
	public Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,JSONObject tablemodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getEmployeeWiseReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tablemodel);
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
			return excelUtils.writeToExcel(rs,format,2,-1,0  );
			
		   }finally{
			  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	
	private ResultSet getEmployeeWiseReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rs = null;
		//rs = dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_EMPWISEACTIVITYRPTTEMP", paramValues);
		
		rs = dbActionTemplate.NewdbFunctionCall2("BDM_FN_EMPWISEACTIVITYRPTTEMP", paramValues);

		return rs;
		//return dbActionTemplate.dbFunctionCall(getFunctionName(commonFilter), paramValues);
	}
	
	/*public Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,JSONObject tableModel ,String format) throws Exception{
		  ResultSet rs = null;
		   try{
			List<String> paramValues = getFilterParamValues(commonFilter);
			rs =  dbActionTemplate.dbFunctionCall(getFunctionName(commonFilter), paramValues);
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			return excelUtils.writeToExcel(rs,"xls",  2, 0,2 );
			
		   }
		   catch(Exception e)
		   {
			   System.out.println("EmployeeWiseActivityRptExportExcel Exception :"+e.getMessage());
		   }
		   
		   finally{
			   if( rs != null){
				   Connection con  = rs.getStatement().getConnection();
				   rs.close();
				   rs = null;
				   if( con != null )
				   {
					   con.close();
				   }
				   
			   }	   
		   }
		return null;
	}*/
	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_EMPWISEACTIVITYRPTTEMP", paramValues);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("bdm_fn_empwiseactivityrpttemp", paramValues);

	        List<String[]> dataList = fnCallApi.callFunction("BDM_FN_EMPWISEACTIVITYRPTTEMP_SB", paramValues, 3, true);
			
	        if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....123"+totalCnt);
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
	@Override
	public Map<Integer, List<String[]>> getEmployeeWiseActivityExlView(
			String empKeyID, String format) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(empKeyID);
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("BDM_PC_BREAKDOWN.BDM_FN_EMPLOYEEWISEACTIVITYEXL", paramValues,2);
			
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public List<String[]> getActivityWiserpt(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ACTWISERPTTEMP", paramValues);
			
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
	
	
}
