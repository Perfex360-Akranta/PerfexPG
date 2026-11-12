package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.HSEAccTrendRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class HSEAccTrendRptDaoImpl implements HSEAccTrendRptDao {

private DBActionTemplate dbActionTemplate;
FunctionCallApi fnCallApi;
//NewAccidenttrendreportserviceApi serviceApi;
	
	public HSEAccTrendRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void HSEAccTrendRptDaoImplJwt(String JwtToken) 
	{
		try{
			//serviceApi = new NewAccidenttrendreportserviceApi (JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String []> getAccidentRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEINJURYCNT", paramValues);
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
	public List<String []> getNewAccidentRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_NEWHSEINJURYCNT", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("HSE_FN_NEWHSEINJURYCNT_SB", paramValues,3,false);
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
	
	
	public List<String []> getModeWiseRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACC", paramValues);
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
	
	

	public List<String []> getBodyVsAccRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEBODYPARTVSACC", paramValues);
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
	
	public List<String []> getNewBodyPartRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			 System.out.println("Inside the getNewBodyPartRpt");
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_HSENEWBODYPARTVSACC", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("HSE_FN_HSENEWBODYPARTVSACC_SB", paramValues,2,true);
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
	public List<String []> getYYAnalysisRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_YYSTANDARDRPT", paramValues);
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
	
	public Workbook AccidentTrendExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getAccidentTrendExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	public Workbook NewAccidentTrendExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getNewAccidentTrendExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	
	public Map<Integer, List<String[]>> getwhywhyQtyRptExl(String rowId,String format) throws Exception {
		try
		{
			CommonFunctions.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(rowId);
			
			CommonFunctions.debugMsg("param Values :-" +paramValues.get(0));
		
			
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("HSE_PC_SAFETY.HSE_FN_WHYWHYREPORTEXL", paramValues,2);
			
			
			for(int i = 0; i <  whyReport.size();i++ )
			{
				List<String[]> lists =  whyReport.get(i);
				CommonFunctions.debugMsg("List values......"+whyReport.get(i));
			}
			
			CommonFunctions.debugMsg("Inside v: " +whyReport.toString());
			CommonFunctions.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
		//	return whyReport;
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}		
	@Override
	public List<String[]> getHseAccTrendRptgraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_HSEINJURYCNT", paramValues);			
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
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getNewHseAccTrendRptgraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_NEWHSEINJURYCNT", paramValues);			
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
			throw new Exception(e.getMessage()); 
		}
	}

	private ResultSet getAccidentTrendExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_HSEINJURYCNT", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	
	private ResultSet getNewAccidentTrendExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("HSE_FN_NEWHSEINJURYCNT", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	
	public Workbook ModeWiseAccExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getModeWiseAccExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	public Workbook BodyWiseAccExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try
		   {
			
			rs =   getBodyWiseAccExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
			}finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	

	public Workbook NewBodyPortExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try
		   {
			
			rs =   getNewBodyPortWiseAccExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
			}finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	public Workbook YYAnalysisReportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getYYAnalysisReportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	public List<String[]> getModeWiseAccRptgraph(CommonFilter commonFilter,String rowId) throws Exception
	{
		try
		{
             CommonFunctions.debugMsg(" Inside Dao Imppl :: keyId :: "+rowId);
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 condParms=condParms+"KeyId="+rowId;
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 //paramValues.add(keyId);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACC", paramValues);			
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
			throw new Exception(e.getMessage()); 
		}
	}

	private ResultSet getModeWiseAccExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		//ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACC", paramValues);
		
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("HSE_FN_HSEMODEVSACCRPT", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	
	
	private ResultSet getNewBodyPortWiseAccExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("HSE_FN_HSENEWBODYPARTVSACC", paramValues);
		return rs;
	}
	
	private ResultSet getBodyWiseAccExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_HSEBODYPARTVSACC", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}

	public List<String[]> getBodyPartAccRptgraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_HSEBODYPARTVSACC", paramValues);			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg(totalCnt + " total cnt");
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String[]> getNewBodyPartRptGraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_HSENEWBODYPARTVSACC", paramValues);			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg(totalCnt + " total cnt");
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	private ResultSet getYYAnalysisReportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_YYSTANDARDRPT", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}

	@Override
	public List<String[]> getAccidentMajor() throws Exception 
	{
		
		String sql=" select 'Accident','Major','Minor','Total' FROM DUAL";
		sql+=" UNION  select 'Accident1','35','20','55' FROM DUAL";
		sql+=" UNION ALL select 'Accident2','20','15','35' FROM DUAL";
		sql+=" UNION ALL select 'Accident3','45','20','65' FROM DUAL";
		sql+=" UNION ALL select 'Accident4','10','20','35' FROM DUAL";
		sql+=" UNION ALL select 'Accident5','15','25','40' FROM DUAL";
		sql+=" UNION ALL select 'Accident6','20','25','45' FROM DUAL";
		sql+=" UNION ALL select 'Accident7','30','20','50' FROM DUAL";
		sql+=" UNION ALL select 'Accident8','35','25','60' FROM DUAL";
		sql+=" UNION ALL select 'Accident9','30','30','60' FROM DUAL";
		sql+=" UNION ALL select 'Accident10','45','20','65' FROM DUAL";
		sql+=" UNION ALL select 'Accident11','40','30','70' FROM DUAL";
		sql+=" UNION ALL select 'Accident12','45','30','75' FROM DUAL";
		sql+=" UNION ALL select 'Accident13','50','30','80' FROM DUAL";
		sql+=" UNION ALL select 'Accident14','50','35','85' FROM DUAL";
		sql+=" UNION ALL select 'Accident15','55','40','95' FROM DUAL";
		sql+=" UNION ALL select 'Accident16','60','20','80' FROM DUAL";
		sql+=" UNION ALL select 'Accident17','65','25','90' FROM DUAL";
		sql+=" UNION ALL select 'Accident18','25','50','75' FROM DUAL";
		sql+=" UNION ALL select 'Accident19','30','55','85' FROM DUAL";
		sql+=" UNION ALL select 'Accident20','35','60','95' FROM DUAL";
		
		// TODO Auto-generated method stub
		List<String[]> gridData = dbActionTemplate.getDataList(sql);
		System.out.println("Inside DAO IMpl "+sql);
		System.out.println("Inside DAO IMpl "+"size"+gridData.size());
		return gridData;
	}

	@Override
	public List<String[]> getInjuryVsAccRpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.EHS_FN_EHSINJURYTYPEVSACC", paramValues);
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

	@Override
	public List<String[]> getInjurytypeAccRptgraph(
			CommonFilter chrtCommonFilter, String keyId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(chrtCommonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.EHS_FN_EHSINJURYTYPEVSACC", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( chrtCommonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg(totalCnt + " total cnt");
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
					chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
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
	public Workbook injuryWiseAccExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
			  
			   ResultSet rs = null;
			   try
			   {
				
				rs =   getInjuryAccExportExcel(commonFilter);
				CommonFunctions.debugMsg(rs);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
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
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
				}finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}

	private ResultSet getInjuryAccExportExcel(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.EHS_FN_EHSINJURYTYPEVSACC", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	public List<String []> getModeWiseReport(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("BEFORE FUNC CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_HSEMODEVSACCRPT", paramValues);
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
	public Workbook ModeWiseReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getModeWiseAccExportExcel(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	public List<String[]> getModeWiseRptReportgraph(CommonFilter commonFilter,String rowId) throws Exception
	{
		try
		{
             CommonFunctions.debugMsg(" Inside Dao Imppl :: keyId :: "+rowId);
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 condParms=condParms+"KeyId="+rowId;
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 //paramValues.add(keyId);
			 
			 List<String[]> rootRptList;
			 CommonFunctions.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_HSEMODEVSACCRPT", paramValues);			
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
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String[]> getEmployeeIncident(CommonFilter commonFilter) throws Exception{
		try {

			System.out.println("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonFunctions.debugMsg("  emptype 2"+commonFilter.getEmpwiseType());
			paramValues.add(condParms);	
			System.out.println("The condParms:::"+condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =null;                              
				dataList=dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_EMPLOYEEINCIDENT",paramValues);	
			System.out.println("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonFunctions.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonFunctions.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public Workbook EmpAccidentExportExcel(CommonFilter commonFilter, JSONObject colModel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getEmployeeIdentifiedResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,format, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getEmployeeIdentifiedResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValuesIDf(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_EMPLOYEEINCIDENT", paramValues);
	}
	
	private List<String> getFilterParamValuesIDf(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);		
		return paramValues;
	}
}
