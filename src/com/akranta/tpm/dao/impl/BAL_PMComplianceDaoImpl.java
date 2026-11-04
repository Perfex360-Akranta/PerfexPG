package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PMComplianceDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PMComplianceDaoImpl implements BAL_PMComplianceDao {

	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
		
		public BAL_PMComplianceDaoImpl(DBActionTemplate dbActionTemplate) 
		{
			this.dbActionTemplate = dbActionTemplate;
		}
		public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
			this.dbActionTemplate = dbActionTemplate;
		}
		
		public void BAL_PMComplianceDaoImplJwt(String JwtToken) 
		{
			try{
				
			fnCallApi = new FunctionCallApi(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public List<String[]> getAllPMCompliance(CommonFilter commonFilter)
				throws Exception {
			try
			{
				
				List<String > paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				System.out.println("Inside PM Compliance DAO Impl  " + paramValues);
				
				//List<String[]> impVsCompList = dbActionTemplate.processFunctionCalls("PLM_FN_PMCOMPLIANCERPT", paramValues);
				
				
				List<String[]> impVsCompList = fnCallApi.callFunction("PLM_FN_PMCOMPLIANCERPT_SB", paramValues, 4, true);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				
				return impVsCompList;			
			
		}
		
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
	}
		public List<String[]> getAllPMComplianceW(CommonFilter commonFilter)
		throws Exception {
	try
	{
		
		List<String > paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		System.out.println("Inside PM Compliance DAO Impl " + paramValues);
		
		List<String[]> impVsCompList = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PMCOMPLIANCERPTW", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		
		return impVsCompList;			
	
}

	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}
		public Workbook PMComplainceReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)	throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getPMComplainceReportResultSet(commonFilter);
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
		
		private ResultSet getPMComplainceReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("PLM_FN_PMCOMPLIANCERPT", paramValues);
		}
		
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}
		@Override
		public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter)
				throws Exception {
			try
			{
				List<String > paramValues = getFilterParamValues(commonFilter);
				
				
				List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_ACTWISERPT", paramValues);
				
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
		public Workbook EmployeeWiseActivityRptExportExcel(
				CommonFilter commonFilter, JSONObject tableModel, String format) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Workbook newPMComplainceReportExportExcel(CommonFilter commonFilter, JSONObject colModel,
				String format) throws InvalidFormatException, IOException, SQLException {
			ResultSet rs = null;
			   try{
				
				try {
					rs =   getnEWPMComplainceReportResultSet(commonFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
		private ResultSet getnEWPMComplainceReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			System.out.println(" In side the get Excel Report Dao 1");

			return dbActionTemplate.dbFunctionCall("PLM_PC_NEWREPORT.PLM_FN_NEWPMCOMPLIANCERPT", paramValues);

		}
		
		@Override
		public List<String[]> getAllPMComplianceNew(CommonFilter commonFilter) throws Exception {
		
				try
				{
					
					List<String > paramValues = new ArrayList<String>();
					
					String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					
					paramValues.add(condParms);
					paramValues.add(commonParams);
					System.out.println("Inside PM Compliance DAO Impl  " + paramValues);
					
					List<String[]> impVsCompList = dbActionTemplate.processFunctionCalls("PLM_PC_NEWREPORT.PLM_FN_NEWPMCOMPLIANCERPT", paramValues);
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
						boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
						if(  isInteger ){
							commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
						}
					}
					
					return impVsCompList;			
				
			}
			
				catch (Exception e)
				{
					e.printStackTrace();
					throw new Exception(e.getMessage()); 
				}
		}
		}
		

