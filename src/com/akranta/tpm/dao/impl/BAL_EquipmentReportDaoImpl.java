package com.akranta.tpm.dao.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import com.akranta.tpm.dao.BAL_EquipmentReportDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class BAL_EquipmentReportDaoImpl implements BAL_EquipmentReportDao {
	
	
	
	
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public BAL_EquipmentReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_EquipmentReportDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	private List<String> getFilterParamValues(CommonFilter commonFilter){
		
		String dateNow = CommonFunctions.getDate();
		
		String tempStr = commonFilter.getAmcRenewal();
		boolean isInteger = false;
		if( CommonFunctions.isValidKeyId(tempStr)){
			isInteger = Pattern.matches("^\\d*$", tempStr);
			if( isInteger  ){
				commonFilter.setAmcrnewFrom(dateNow);
				commonFilter.setAmcrenewTo(CommonFunctions.addMonth(dateNow,Integer.parseInt(tempStr)));
			}
		}
		tempStr = commonFilter.getWarrantyExpries();
		
		if(  CommonFunctions.isValidKeyId(tempStr)){
			isInteger = Pattern.matches("^\\d*$", tempStr);
			if( isInteger  ){
				commonFilter.setWarrantyFrom(dateNow);
				commonFilter.setWarrantyTo(CommonFunctions.addMonth(dateNow,Integer.parseInt(tempStr)));
			}	
		}
		

		if( commonFilter.getBdCategorized() == null || "ALL".equals(commonFilter.getBdCategorized()) )
			commonFilter.setBdCategorized("{}" );
		
		
		
		String condParms = FilterCondSql.getEquipmentRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		List<String> paramValues  = new ArrayList<String>();
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues; 
	}
	public List<String[]> getEquipmentQuery(CommonFilter commonFilter) throws Exception
	{
		try
		{
			boolean isInteger = false;
			
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_EQUIPMENTQUERY", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_EQUIPMENTQUERY_SB", paramValues, 3, true);
			
			
			if( commonFilter.getViewClick() == 'Y'){

				String totalCnt = paramValues.get(0); 

				isInteger = Pattern.matches("^\\d*$", totalCnt);
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
	
	public List<String[]> getEquipmentControlPanelDao(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add( commonFilter.getMachine() != null ? "%"+commonFilter.getMachine().getCode() + "%":"%%");
		
			return dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_EQPCONTROLPANEL", paramValues);
			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	public List<String[]> getEqmpthistry(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			//String dateNow = CommonFunctions.getDate();
			
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getEquipmentRelatedCondStr(commonFilter);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_MACHINEHISTORY", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_MACHINEHISTORY_SB", paramValues, 3, true);
			CommonFunctions.debugMsg("Out of daao Impl.....");
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			System.out.println(dataList);
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
			
	}
	
		
		public static String getEquipmentRelatedConditionStr(CommonFilter commonFilter)
		{
			String relCondtionStr = "1=1";
			
			System.out.println(commonFilter.getJhStep().getId());		
			System.out.println("rcccc+"+commonFilter.getMachineRank());
			System.out.println("rccccSS+"+commonFilter.getActType());
			
			
			if (commonFilter.getJhStep() != null && commonFilter.getJhStep().getId() != null && commonFilter.getJhStep().getId() != "{}")
				relCondtionStr = relCondtionStr + ";JHSTEP="+ commonFilter.getJhStep().getId();
			if (commonFilter.getMachineRank() != null && commonFilter.getMachineRank().getId()!=null  && commonFilter.getMachineRank().getId() != "{}")
				relCondtionStr = relCondtionStr + ";RANK="+ commonFilter.getMachineRank().getId();
			if (commonFilter.getShift() != null && commonFilter.getShift().getId()!=null  && commonFilter.getShift().getId() != "{}")
				relCondtionStr = relCondtionStr + ";SHIFTID="+ commonFilter.getShift().getId();
			
			if (commonFilter.getSupervisor() != null && commonFilter.getSupervisor().getId()!=null  && commonFilter.getSupervisor().getId() != "{}")
				relCondtionStr = relCondtionStr + ";SUPERVISOR="+ commonFilter.getSupervisor().getId();
			if (commonFilter.getPurpose() != null && commonFilter.getPurpose().getId()!=null  && commonFilter.getPurpose().getId() != "{}")
				relCondtionStr = relCondtionStr + ";PURPOSE="+ commonFilter.getPurpose().getId();
			if (commonFilter.getCategory() != null && commonFilter.getCategory().getId()!=null  && commonFilter.getCategory().getId() != "{}")
				relCondtionStr = relCondtionStr + ";CATEGORY="+ commonFilter.getCategory().getId();
			if (commonFilter.getSubCategory() != null && commonFilter.getSubCategory().getId()!=null  && commonFilter.getSubCategory().getId() != "{}")
				relCondtionStr = relCondtionStr + ";SUBCATEGORY="+ commonFilter.getSubCategory().getId();
			if (commonFilter.getIncdntFromDate() != null  && commonFilter.getIncdntFromDate() != "{}")
				relCondtionStr = relCondtionStr + ";INSTALLFROMDATE="+ commonFilter.getIncdntFromDate();
			if (commonFilter.getIncdntdToDate()!= null && commonFilter.getIncdntdToDate()!= "{}")
				relCondtionStr = relCondtionStr + ";INSTALLTODATE="+ commonFilter.getIncdntdToDate();
			if (commonFilter.getAmcFrom() != null && commonFilter.getAmcFrom()!=null  && commonFilter.getAmcFrom() != "{}")
				relCondtionStr = relCondtionStr + ";AMCFROMDATE="+ commonFilter.getAmcFrom();
			if (commonFilter.getAmcTo() != null && commonFilter.getAmcTo()!=null  && commonFilter.getAmcTo() != "{}")
				relCondtionStr = relCondtionStr + ";AMCTODATE="+ commonFilter.getAmcTo();
			
			if (commonFilter.getAmcrnewFrom() != null && commonFilter.getAmcrnewFrom()!="{}")
				relCondtionStr = relCondtionStr + ";AMCRENEWFROM="+ commonFilter.getAmcrnewFrom(); 

			if (commonFilter.getAmcrenewTo() != null && commonFilter.getAmcrenewTo()!="{}")
				relCondtionStr = relCondtionStr + ";AMCRENEWTO="+ commonFilter.getAmcrenewTo(); 
			if (commonFilter.getWarrantyExpries() != null && commonFilter.getWarrantyExpries()!="{}")
				relCondtionStr = relCondtionStr + ";WARRANTYEXPRY="+ commonFilter.getWarrantyExpries();
			if (commonFilter.getWarrantyFrom() != null && commonFilter.getWarrantyFrom()!="{}")
				relCondtionStr = relCondtionStr + ";WARRANTYFROM="+ commonFilter.getWarrantyFrom();
			if (commonFilter.getWarrantyTo() != null && commonFilter.getWarrantyTo()!="{}")
				relCondtionStr = relCondtionStr + ";WARRANTYFROM="+ commonFilter.getWarrantyTo();
			if (commonFilter.getActType() != null)
				relCondtionStr = relCondtionStr + ";ACTTYPE="+ commonFilter.getActType();
		
			System.out.println("relCondtionStr="+relCondtionStr);

			
	// missing  (Root Cause Classification, Included Root Cause, Included Why-Why,)		
			
			return relCondtionStr;
			
		}	
	
		public Workbook equipmentQueryExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
			 ResultSet rs = null;
			   try{
				
				rs =   getEquipmentQueryResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
			
			
		}
	
		private ResultSet getEquipmentHistoryResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MACHINEHISTORY", paramValues);
		}
		
		private ResultSet getEquipmentQueryResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_EQUIPMENTQUERY", paramValues);
		}

		@Override
		public Workbook equipmentHistoryExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getEquipmentHistoryResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,rptFormat,3,2,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

		@Override
		public List<String[]> getAllMouldHistory(CommonFilter commonFilter) throws Exception {
			try
			{
				
				//String dateNow = CommonFunctions.getDate();
				
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getEquipmentRelatedCondStr(commonFilter);

				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				
				List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_MOULDHISTORY", paramValues);
				CommonFunctions.debugMsg("Out of daao Impl.....");
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				System.out.println(dataList);
				return dataList; 
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}

		@Override
		public Workbook getAllMouldHistoryExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)	throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getMouldHistoryResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,rptFormat,0,1,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}
		private ResultSet getMouldHistoryResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_MOULDHISTORY", paramValues);
		}
		
		
}
