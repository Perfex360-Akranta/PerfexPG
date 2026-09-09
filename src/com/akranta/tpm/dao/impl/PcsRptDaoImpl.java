package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class PcsRptDaoImpl implements PcsRptDao {
	
	private DBActionTemplate dbActionTemplate; 
	
	public PcsRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public DBActionTemplate getDbActionTemplate() {
		return dbActionTemplate;
	}
	
	public List<String[]> getPlanVsRegData(CommonFilter commonFilter) throws Exception {
		try
		{ 	
			 List<String> paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PRODVSLINEREJ", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return pcsList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<String []> getpcsRpt(CommonFilter commonFilter) throws Exception
	{
	 
		try
		{ 	
			 List<String> paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PCSREPORT", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return pcsList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	public List<String []> getcellRpt(CommonFilter commonFilter) throws Exception
	{
	 
		try
		{ 	
			 List<String> paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 if(UIUtils.isValidKeyId(commonFilter.getWostatus() ))
				 condParms += "SHIFTCODE="+commonFilter.getWostatus();//for filter based on shift
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_CELLEFFECIENCY", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return pcsList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	public List<String[]> getAllPcsRptdtl(String rowId, String date,String sectId,String prlmid,String shiftId ,CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("Inside export daoimpl");
		List<String> paramValues = new ArrayList<String>();
		//paramValues.add("CELLID="+ rowId+";");
		
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		 paramValues.add("CELLID="+rowId+ ";"+"ENTRYDATE=" +date +";"+"SECTIONID="+sectId+";"+"PLMASTERID="+prlmid+";"+"SHIFTID="+shiftId+";"); //TST_PC_PCSREPORTXL.PCS_FN_PCSREPORTXL1
		 //paramValues.add(commonParams);
		// paramValues.add(condParms);
	
		
		//paramValues.add("BDKEYID="+ rowId+";");
		 CommonMessage.debugMsg("Inside v: " +paramValues);
		//CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		List<String[]> whyReport = dbActionTemplate.processFunctionCalls("PCS_PC_PCSREPORTXL.PCS_FN_PCSREPORTXL1", paramValues);
		
		CommonMessage.debugMsg("Inside v: " +whyReport);
		return whyReport;
	}

	
	public List<String[]> getAllPcsRptExl1(String rowId, String date,String sectId,String prlmid,String shift,CommonFilter commonFilter, String locn)throws Exception {
		 List<String> paramValues = new ArrayList<String>();
		 //String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		
		 String condParms ="CELLID="+rowId+ ";"+"ENTRYDATE=" +date +";"+"SECTIONID="+sectId+";"+"PLMASTERID="+prlmid+";"+"SHIFTID="+shift+";" ;//+"CHKCELL=;CHKMACHINE=;";
		 //String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		/*  	paramValues.add(sectId);
		 	paramValues.add(rowId);
			paramValues.add(shift);
			paramValues.add(date);*/
		/*     paramValues.add("CELLID="+rowId+ ";");
		    paramValues.add("ENTRYDATE=" +date +";");
		    paramValues.add("SECTIONID="+sectId+";");
		    paramValues.add("SHIFTID="+shift+";"); */ //TST_PC_PCSREPORTXL.PCS_FN_PCSREPORTXL1---"PLMASTERID="+prlmid+";"+
		// String locn=UIUtils.getlocation(request);
		 CommonMessage.debugMsg("SCHEMA CON  :"+locn);
		 CommonMessage.debugMsg("Inside v: " +paramValues);
		 List<String[]> pcsReport = null;
		 if(locn.equals("VASAI")){
			 CommonMessage.debugMsg("insidevasai");
			 pcsReport = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PCSENTRYEXL_RRLVAS", paramValues);//PCS_PC_PCSREPORTXL2.PCS_FN_GETPLDATAFROMVW1
		 }else{
			 CommonMessage.debugMsg("inside other schema");
			 pcsReport = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PCSENTRYEXL", paramValues);//PCS_PC_PCSREPORTXL2.PCS_FN_GETPLDATAFROMVW1
		 }
		 CommonMessage.debugMsg("Inside v: " +pcsReport);
		return pcsReport;
	}
	
	
	@Override
	public Workbook getCellEfficiencyExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
			rs =   getResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_CELLEFFECIENCY", paramValues);
	}
	
	

	@Override
	public Workbook getPcsRptExl(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PCSREPORT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
			 if(commonFilter.getWostatus().equals("M"))
				  condParms += "RPTTYPE=MACHINEWISE;";
			 else
				 condParms += "RPTTYPE=LOSSWISE;";
			 }
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getAllPcsRptExl(String rowId,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, List<String[]>> getAllPcsRptdtlExl(String rowId, String date,String sectId, String prlmid,String shiftId, String format, String path,String imagePath) throws Exception {
		try{
			List<String> paramValues = new ArrayList<String>();				
		
		    paramValues.add("CELLID="+rowId+ ";"+"ENTRYDATE=" +date +";"+"SECTIONID="+sectId+";"+"PLMASTERID="+prlmid+";"+"SHIFTID="+shiftId+";"); //TST_PC_PCSREPORTXL.PCS_FN_PCSREPORTXL1
		 
		    CommonMessage.debugMsg("Inside v: " +paramValues);
		    Map<Integer, List<String[]>> pcsReport = dbActionTemplate.processDbFunCallMultCursor("PCS_PC_PCSREPORTXL.PCS_FN_PCSREPORTXL1", paramValues,8);
			for(int i = 0; i <  pcsReport.size();i++ )
			{
				List<String[]> lists =  pcsReport.get(i);
			}
			CommonMessage.debugMsg("11111111111111");
			return pcsReport;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public List<String[]> getlossBreakup(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{ 	
			 List<String> paramValues = new ArrayList<String>();
			 
			 //String twoRow = commonFilter.getToRow();
			 //int twoRo = Integer.parseInt(twoRow);
			 //commonFilter.setToRow(Integer.toString(twoRo));
			 
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
			 if(commonFilter.getWostatus().equals("M"))
				  condParms += "RPTTYPE=MACHINEWISE;";
			 else
				 condParms += "RPTTYPE=LOSSWISE;";
			 }
			 String FLID = commonFilter.getFlid();
			 paramValues.add(condParms);
			 paramValues.add(commonParams);			
			 paramValues.add(FLID);
			 
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> lossBreakUpList = dbActionTemplate.processFunctionCallsWithColHeaders ("PCS_PC_PRODLOG.PCS_FN_PCSLOSSBREAKUP", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return lossBreakUpList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getlossBreakupExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try{
			
			rs =   getResultSetLossBreakup(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getResultSetLossBreakup(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PCSLOSSBREAKUP", paramValues);
	}

	@Override
	public List<String[]> getSectAndMechWiseDefect(CommonFilter commonFilter) throws Exception {
		
		try
		{ 	
			List<String> paramValues = getDefectWiseFilterParamValues(commonFilter);
			 
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_DEFECTSUMMARY", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return pcsList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	private List<String> getDefectWiseFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		 //condParms += "REPORTTYPE=SECT";
		/* if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
			 if("M".equals(commonFilter.getWostatus()))
				  condParms += "REPORTTYPE=MCHM;";
			 else if("C".equals(commonFilter.getWostatus()))
				 condParms += "REPORTTYPE=CELL;";
			 else if("S".equals(commonFilter.getWostatus()))
				 condParms += "REPORTTYPE=SECT;";
			 }*/
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		return paramValues;
	}
	public List<String []> getPlanVsActual(CommonFilter commonFilter) throws Exception
	{
		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("relCondStr="+paramValues);
			CommonMessage.debugMsg("relCondStr="+paramValues);
			List<String[]> PlanVsActualList=dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PLANVSACTUAL", paramValues);
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
		  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					CommonMessage.debugMsg("Count "+totalCnt);
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		    return PlanVsActualList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getSectAndMechWiseDefectExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		
		 ResultSet rs = null;
		   try{
			
			rs =   getResultSetDefectWise(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getResultSetDefectWise(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getDefectWiseFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_DEFECTSUMMARY", paramValues);
	}

	@Override
	public List<String[]> getLossTimeBreakupSmry(CommonFilter commonFilter)	throws Exception {
		try
		{ 	
			List<String> paramValues = getDefectWiseFilterParamValues(commonFilter);
			 
			 CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_LOSSBREAKSUMMARY", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return pcsList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getLossTimeBreakupSmryExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getLossTimeBreakupSmryResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getLossTimeBreakupSmryResultSet(CommonFilter commonFilter) throws Exception 
	{
		List<String> paramValues = getDefectWiseFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_LOSSBREAKSUMMARY", paramValues);
	}
	
	public Workbook pcsPlanVsActualExport(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getpcsPlanVsActualResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getpcsPlanVsActualResultSet(CommonFilter commonFilter) throws Exception 
	{
		List<String> paramValues = new ArrayList<String>();
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PLANVSACTUAL", paramValues);
	}

	@Override
	public List<String[]> getPlanVsRejData(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		 
		List<String[]> PlanVsActualList=dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_PRODVSLINEREJ", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
	  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				CommonMessage.debugMsg("Count "+totalCnt);
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	    return PlanVsActualList;
	}
	
	public Workbook PlanVsRejExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getPlanVsRejReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getPlanVsRejReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		//CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PRODVSLINEREJ", paramValues);
	}
}
