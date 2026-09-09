package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.InternalRejectionRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class InternalRejectionRptDaoImpl implements InternalRejectionRptDao{

	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public InternalRejectionRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void InternalRejectionRptDaoImplJwt(String JwtToken) 
	{
		try{
	
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

	public List<String[]> getInternalRejection(CommonFilter commonFilter)throws Exception {
		
		try
		{			
			List<String > paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms += "ISFORPERCENT=N;";
			
			//paramValues.add("C1");
			paramValues.add(condParms);
			paramValues.add(commonParams);
		
			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC1", paramValues);
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )				
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));				
			}
			
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<String[]> getInternalRejectionRpt(CommonFilter commonFilter)throws Exception {
		
		try
		{	 
			CommonMessage.debugMsg("Inside DAo Impl");
			
			List<String > paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms += "ISFORPERCENT=N;";
			
			//paramValues.add("C1");
			paramValues.add(condParms);
			paramValues.add(commonParams);
		
			//List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_MONTHWISEREJQTY", paramValues);//swetha
			List<String[]> dataList = fnCallApi.callFunction("QTM_FN_MONTHWISEREJQTY_SB", paramValues,2,false);
			CommonMessage.debugMsg("Printing in Dao Impl");
			for(String[] arr : dataList) 
			{
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )				
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));				
			}
			
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	
	public Workbook getAllExcelDataNew(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("test to export excel--new");
			
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String > paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms += "ISFORPERCENT=N;";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues:"+paramValues);
			//QTM_PC_QUALITY.QTM_FN_MONTHWISEREJQTY
			
			rs= dbActionTemplate.NewdbFunctionCall2("QTM_FN_MONTHWISEREJQTY",paramValues);
			//rs= dbActionTemplate.dbFunctionCall("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC2",paramValues);		
			
			
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
	public Map<Integer, List<String[]>> getAllRejection(CommonFilter commonFilter) throws Exception {
		
		try
		{			
			List<String > paramValues = new ArrayList<String>();
				
			
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			//paramValues.add("C1");
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("ParamValues:"+paramValues);
			
			Map<Integer, List<String[]>> dataList = dbActionTemplate.processDbFunCallMultCursor("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC1",paramValues,2);
		
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
	
	public Workbook getAllExcelData(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("test to export excel");
			rs =   getInternalResultSet(commonFilter);
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
	 private ResultSet getInternalResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);			
			return dbActionTemplate.dbFunctionCall("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC2",paramValues);
		}
		
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();			
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}
		@Override
		public Map<Integer, List<String[]>> getExportExcel(String format,JSONObject tblJSONObj, CommonFilter commonFilter)throws Exception {

			try
			{
				CommonMessage.debugMsg("Inside export daoimpl Excel");
				List<String > paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				CommonMessage.debugMsg("paramValues:"+paramValues);
				Map<Integer, List<String[]>> dataList = dbActionTemplate.processDbFunCallMultCursor("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC1",paramValues,2);
			
				for(int i = 0; i <  dataList.size();i++ )
				{
					List<String[]> lists =  dataList.get(i);
				}
				
				CommonMessage.debugMsg("Inside v: " +dataList.toString());
				CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
				
				//return ImproprojshtReport;
				return dataList;
				
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
				
			}
		
		}
}
