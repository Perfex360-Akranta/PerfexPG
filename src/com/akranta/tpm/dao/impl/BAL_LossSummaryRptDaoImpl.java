package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.Request;
//import com.akranta.tpm.dao.BAL_LossSummaryRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_LossSummaryRptService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_LossSummaryRptDaoImpl implements BAL_LossSummaryRptService {
	
	private DBActionTemplate dbActionTemplate; 
	
	public BAL_LossSummaryRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getAllloss(CommonFilter commonFilter) throws Exception
	{
		try
		{

			List<String > paramValues = new ArrayList<String>();
			System.out.println("Inside  Loss Summary DAO Impl");
			
			
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);		
			
			CommonFunctions.debugMsg("condParms:"+condParms+commonParams);
			List<String[]> losslist = dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_LOSSSUMMARY", paramValues);
			//List<String[]> losslist = dbActionTemplate.processFunctionCalls("TST_PC_TESTPACK1.KZN_FN_LOSSSUMMARY", paramValues);
			
			return losslist;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<String[]> getAlllossdrill(CommonFilter commonFilter)throws Exception {
		
		List<String > paramValues = new ArrayList<String>();
		
		String condParms = "LOSSID="+commonFilter.getLossId();
				condParms += ";COLVAL="+commonFilter.getColVal();
				condParms +=";";	
				
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
		paramValues.add(condParms);
		paramValues.add(commonParams);		
		CommonFunctions.debugMsg("Datas in param values:"+condParms+commonParams);
		
		List<String[]> losslists = dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_LOSSSUMMARYDTL", paramValues);		
		
		return losslists;
	}
	//MANO COMMENT THE BELOW 
	//@Override
	public Workbook LossSmryReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat,String mchId) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getLossSummaryReportResultSet(commonFilter,mchId);
			CommonFunctions.debugMsg("rs value.."+rs);
			
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
			return excelUtils.writeToExcel(rs,rptFormat,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getLossSummaryReportResultSet(CommonFilter commonFilter,String mchId) throws Exception
	{
		
		
		if(mchId!=null){
			List<String> paramValues = getFilterDrillParamValues(commonFilter);				
			return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.KZN_FN_LOSSSUMMARYDTL", paramValues);
		}
		else{
			List<String> paramValues = getFilterParamValues(commonFilter);	
			return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.KZN_FN_LOSSSUMMARY", paramValues);
		}
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	private List<String> getFilterDrillParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		 condParms += "LOSSID="+commonFilter.getLossId();
				condParms += ";COLVAL="+commonFilter.getColVal();
				condParms +=";";	
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
}