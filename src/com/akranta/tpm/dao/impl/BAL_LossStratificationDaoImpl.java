package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.DrillLevelConstants;
//import com.akranta.tpm.dao.EquipmentDownTimeDao;
import com.akranta.tpm.dao.BAL_LossStratificationDao;
import com.akranta.tpm.dao.sql.BreakDownSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.service.api.FunctionCallApi;


import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class BAL_LossStratificationDaoImpl implements BAL_LossStratificationDao {
	


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;

	
	public BAL_LossStratificationDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BAL_LossStratificationDaoImplJwt(String JwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<String[]> getAllLossStratification(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
					
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			// paramValues.add(condParms);
			// paramValues.add(commonParams);
			 
			 String section=null;
			 section= getSection(commonFilter.getFlid());
			 
			 if(section.substring(0,3).equals("SEC")){
					CommonFunctions.debugMsg("strSect ***** in side if" +section);
					condParms+="SECTIONID="+section+";";
					commonFilter.setDrillLevel("SECT");
					
					//condParms+="DRILLLEVEL=SECT;";
				}
				else if(section.substring(0,3).equals("FCT")){
					CommonFunctions.debugMsg("strSect ***** in side if" +section);
					condParms+="FACTORYID="+section+";";
					commonFilter.setDrillLevel("FACT");
				}
				paramValues.add(condParms);
				paramValues.add(commonParams);
			// List<String[]> rootRptList;
		//	 System.out.println("paramvalues1111111111111" + commonFilter.getDrillLevel());
			 /*if(DrillLevelConstants.ASSEMBLY.equals(commonFilter.getDrillLevel()  ))	
			 {
			 	rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORASSM", paramValues);
			 	CommonFunctions.debugMsg("assssssembly"+rootRptList);
			 }
			
			 else if(DrillLevelConstants.PHENOMENA.equals(commonFilter.getDrillLevel()  ))	{
				rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORPHEN", paramValues);			
			 }
			  else
			 {*/
				 System.out.println("paramvalues" + paramValues);	
				 //List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_LOSSSTRAT1", paramValues);	
				 //List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("pcs_fn_lossstrat1", paramValues);	
				 List<String[]>  rootRptList = fnCallApi.callFunction("pcs_fn_lossstrat1_sb", paramValues,3, true);	



				 
		//	 }
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
public String  getSection(String flid) throws Exception{
		
		String sql="Select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_KEYID='"+flid+"'";
		String sectid=dbActionTemplate.getSingleValue(sql);
		
		return sectid;
	}
 
	public List<String[]> getAllLossStratificationSubGrid(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			// String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 //paramValues.add(commonParams);
			 CommonFunctions.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETSUBLOSSES", paramValues);
			
		return pcsList;
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}
	@Override
	public List<String[]> getAllLossGraph(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			System.out.println("Inside  DAO Impl DrillValue graph level:1111111111111111111111111" + commonFilter.getDrillLevel());
			
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
		
				 System.out.println("paramvalues" + paramValues);	
				 //List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_LOSSSTRATGRAPH", paramValues);
				 //List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("pcs_fn_lossstratgraph", paramValues);		
				 List<String[]>  rootRptList = fnCallApi.callFunction("pcs_fn_lossstratgraph_sb", paramValues,3, true);		

				//topFailList = fnCallApi.callFunction("pcs_fn_monthwiselossforparato_sb", paramValues,3, true);
              
				 System.out.println(" ::: 99- " + rootRptList.size());
		
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
	public Workbook getAllLossExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
				rs =   getdownTimeReportResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,2,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		//return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_LOSSSTRAT1", paramValues);
		return dbActionTemplate.NewdbFunctionCall2("pcs_fn_lossstrat1", paramValues);

	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	private ResultSet getdownTimeReportResultSetShift(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		//return dbActionTemplate.dbFunctionCall("PCS_PC_TEST.PCS_FN_LOSSSTRATSHIFT", paramValues);
		return dbActionTemplate.NewdbFunctionCall2("PCS_FN_LOSSSTRATSHIFT", paramValues);

	}
	@Override
	public Workbook getAllLossExlShift(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception {
	    ResultSet rs = null;
	    try {
	        rs = getdownTimeReportResultSetShift(commonFilter);

	        // colModel-ah ResultSet column count-ku pad pannum
	        int rsCols = rs.getMetaData().getColumnCount();
	        net.sf.json.JSONArray cm = tblJSONObj.getJSONArray("colModel");
	        System.out.println("EXCEL rsCols=" + rsCols + " colModel=" + cm.length());
	        for (int i = cm.length(); i < rsCols; i++) {
	            net.sf.json.JSONObject extra = new net.sf.json.JSONObject();
	            extra.put("name", "EXTRA" + i);
	            extra.put("index", "EXTRA" + i);
	            extra.put("width", 10);
	            extra.put("align", "left");
	            extra.put("hidden", true);
	            extra.put("editable", false);
	            extra.put("key", false);
	            cm.put(extra);	        }

	        ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
	        return excelUtils.writeToExcel(rs, format, 0, 2, 0);

	    } finally {
	        DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	    }
	}
	@Override
	public List<String[]> getAllLossStratificationShift(
			CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);		
				 System.out.println("paramvalues" + paramValues);	
				// List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_LOSSSTRATSHIFT", paramValues);	
				 //List<String[]>  rootRptList = dbActionTemplate.processFunctionCalls("PCS_FN_LOSSSTRATSHIFT", paramValues);	
				 List<String[]>  rootRptList = fnCallApi.callFunction("pcs_fn_lossstratshift_sb", paramValues, 3, true);

		
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
	public List<String[]> getAllLossStratificationSubGridShift(
			CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			// String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 //paramValues.add(commonParams);
			 CommonFunctions.debugMsg("relCondStr="+paramValues);
			 List<String[]> pcsList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETSUBLOSSES", paramValues);
			
		return pcsList;
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
	}	

		
}

