package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.PcsRptDao;
import com.akranta.tpm.dao.ProductionLossDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
//import com.akranta.tpm.model.TopnFailures;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ProductionLossDaoImpl implements ProductionLossDao {
	
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public ProductionLossDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//-- added by vignesh -- //
	public void ProductionLossDaoImplJwt(String JwtToken) {
	    try {
	       
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public List<String []> getAllproductionLoss(CommonFilter commonFilter) throws Exception
	{		
		try
		{			
			List<String> paramValues = new ArrayList<String>();		
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
			 String FLID = commonFilter.getFlid();
			 FLID = FLID.trim();
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 paramValues.add(FLID);
			 
		
			CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> topFailList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PRODLOSSANALYSIS", paramValues);
			
			
			if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getAllproductionLossSubGrid(CommonFilter commonFilter)	throws Exception {
		try
		{			
			List<String> paramValues = new ArrayList<String>();	
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 
			 paramValues.add(condParms);
			
			CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> topFailList = dbActionTemplate.processFunctionCalls ("PCS_PC_PRODLOG.PCS_FN_GETSUBLOSSANALYSIS", paramValues);
			 
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getproductionLossExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat,  0,2,2 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PRODLOSSANALYSIS", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getAllproductionLossforparatograph(
			CommonFilter chrtCommonFilter, String selectmonth) throws Exception {
		try
		{			
			List<String> paramValues = new ArrayList<String>();		
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(chrtCommonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
			 String FLID = chrtCommonFilter.getFlid();
			 FLID = FLID.trim();
			 paramValues.add(condParms);
			 commonParams = commonParams + "selectmonth="+selectmonth +";";
			 paramValues.add(commonParams);
			 paramValues.add(FLID);
			 
		
			CommonMessage.debugMsg("relCondStr="+paramValues);
			 List<String[]> topFailList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PRODLOSSANALYSIS", paramValues);
			
			
			if( chrtCommonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getdescorderlosses(String descsql) throws Exception {
		// TODO Auto-generated method stub
		return  dbActionTemplate.getDataList(descsql);
	}

	@Override
	public List<String[]> getAllproductionLossNewChart(CommonFilter chrtCommonFilter,String selmonth) throws Exception {

		try
		{			
			List<String> paramValues = new ArrayList<String>();		
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(chrtCommonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
			 String FLID = chrtCommonFilter.getFlid();
			 String pareto = chrtCommonFilter.getPareto().toString();
			 FLID = FLID.trim();
			 paramValues.add(condParms);
//			 commonParams = commonParams + "selectmonth="+selectmonth +";";
			 paramValues.add(commonParams);
			// paramValues.add(FLID);
			 List<String[]> topFailList;
			 if(pareto.equalsIgnoreCase("Y"))
			 {
				 paramValues.add(selmonth);
				 CommonMessage.debugMsg("relCondStr="+paramValues);
				 topFailList = dbActionTemplate.processFunctionCalls("MONTHWISELOSSFORPARATO", paramValues);
				
			 }
			 else
			 {
				 CommonMessage.debugMsg("relCondStr="+paramValues);
				// topFailList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_MONTHWISELOSS", paramValues);
				 topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_MONTHWISELOSSGRAPH", paramValues);
				
			 }
			 
		
			
			
			if( chrtCommonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
	
	@Override
	public List<String[]> getAllproductionLossDrill(CommonFilter chrtCommonFilter)
			throws Exception {

		try
		{			
			List<String> paramValues = new ArrayList<String>();		
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(chrtCommonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
			 String FLID = chrtCommonFilter.getFlid();
			 FLID = FLID.trim();
			 paramValues.add(condParms);
//			 commonParams = commonParams + "selectmonth="+selectmonth +";";
			 paramValues.add(commonParams);
			// paramValues.add(FLID);
			 
		
			CommonMessage.debugMsg("relCondStr="+paramValues);
			
		//	 List<String[]> topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_EMPLOYEEMONTHWISELOSS", paramValues);//PCS_FN_MONTHWISELOSS
			
			 List<String[]> topFailList = fnCallApi.callFunction("PCS_FN_EMPLOYEEMONTHWISELOSS_SB", paramValues,2,true);//PCS_FN_MONTHWISELOSS
				
			
			if( chrtCommonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}

	@Override
	public List<String[]> getLossByFunctionalLocation(CommonFilter commonFilter)
			throws Exception {
		
		List<String> paramValues = new ArrayList<String>();		
		
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 			 
		 
		 
		 paramValues.add(condParms);
	 
		 paramValues.add(commonParams);
		
		
	//	 List<String[]> lossList = dbActionTemplate.processFunctionCalls("GEN_FN_LOSS_REPORT", paramValues);
		 List<String[]> lossList = fnCallApi.callFunction("GEN_FN_LOSS_REPORT_SB", paramValues,1,true);
		 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
		  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
		}
	
			return lossList;
	}

	@Override
	public Workbook getLossExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {

		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_LOSS_REPORT", paramValues);	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
		}	
	
	public Workbook getLossAnalysisExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {

		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
			 // --Vignesh-- Changing  functionname from PCS_FN_MONTHWISELOSS to PCS_FN_EMPLOYEEMONTHWISELOSS
		 	rs=dbActionTemplate.NewdbFunctionCall2("PCS_FN_EMPLOYEEMONTHWISELOSS_SB", paramValues);	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,1,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
		}	

	@Override
	public List<String[]> getAllLossTimeDrill(CommonFilter chrtCommonFilter)
			throws Exception {

		try
		{			
			List<String> paramValues = new ArrayList<String>();		
				
			 String condParms = FilterCondSql.getPCSRelatedCondStr(chrtCommonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
			 String FLID = chrtCommonFilter.getFlid();
			 FLID = FLID.trim();
			 paramValues.add(condParms);
//			 commonParams = commonParams + "selectmonth="+selectmonth +";";
			 paramValues.add(commonParams);
			// paramValues.add(FLID);
			 
		
			CommonMessage.debugMsg("relCondStr="+paramValues);
			
		//	List<String[]> topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_EMPLOYEEMONTHWISELOSS", paramValues); //PCS_FN_MONTHWISELOSS
		

			List<String[]> topFailList = fnCallApi.callFunction("PCS_FN_EMPLOYEEMONTHWISELOSS_SB", paramValues,2,true); //PCS_FN_MONTHWISELOSS
			
			
			if( chrtCommonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
			  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			
			return topFailList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	    @Override
		public List<String[]> getAllLossTimeNewChart(CommonFilter chrtCommonFilter,String rowid)
				throws Exception {

			try
			{			
				List<String> paramValues = new ArrayList<String>();		
					
				 String condParms = FilterCondSql.getPCSRelatedCondStr(chrtCommonFilter);
				 String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			 
				 String FLID = chrtCommonFilter.getFlid();
				 FLID = FLID.trim();
				 paramValues.add(condParms);
//				 commonParams = commonParams + "selectmonth="+selectmonth +";";
				 paramValues.add(commonParams);
				// paramValues.add(FLID);
				 
			
				CommonMessage.debugMsg("relCondStr="+paramValues);
				 List<String[]> topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_MONTHWISELOSSGRAPH", paramValues);
				
				
				if( chrtCommonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
				  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
						if(  isInteger ){
							chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
						}
					}
				
				return topFailList;
			
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}		
}
