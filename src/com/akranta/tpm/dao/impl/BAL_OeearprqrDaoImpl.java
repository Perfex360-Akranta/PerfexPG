package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.Request;

import com.akranta.tpm.controller.DrillLevelConstants;
import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.EquipmentDownTimeDao;
import com.akranta.tpm.dao.BAL_OeearprqrDao;
import com.akranta.tpm.dao.sql.BreakDownSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.service.api.FunctionCallApi;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class BAL_OeearprqrDaoImpl implements BAL_OeearprqrDao {
	


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public BAL_OeearprqrDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BAL_OeearprqrDaoImplJwt(String JwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<String[]> getAllOeearprqr(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
		

			CommonFunctions.debugMsg("Total:"+commonFilter.getRowTotal());
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 /**added to show pagination  proper*
			 int torow = Integer.parseInt(commonFilter.getToRow());
			 torow = torow+2;
			 commonFilter.setToRow(Integer.toString(torow));*/
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> rootRptList;
		
				 System.out.println("paramvalues" + paramValues);	
				//  rootRptList = dbActionTemplate.processFunctionCalls("TST_PC_PCSTESTPACK1.PCS_FN_OEEARPRQR", paramValues);			
				  //rootRptList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_OEEARPRQR", paramValues);
				  //rootRptList = dbActionTemplate.processFunctionCalls("pcs_fn_oeearprqr", paramValues);
				  rootRptList =fnCallApi.callFunction("pcs_fn_oeearprqr_sb", paramValues, 3, true);

				  
				// rootRptList = dbActionTemplate.processFunctionCalls("TST_PC_OEETESTPACK.PCS_FN_OEEARPRQR", paramValues);
			// }
			 /*if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("Total:"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				CommonFunctions.debugMsg("TotalisInteger:"+isInteger);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					CommonFunctions.debugMsg("11111111111111111"+commonFilter.getTotalRecordCnt());
					
				}
			 }*/
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getAllOeearprqrExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		//return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_OEEARPRQR", paramValues);
		return dbActionTemplate.NewdbFunctionCall2("pcs_fn_oeearprqr", paramValues);

	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	public List<String[]> getAllChartType(CommonFilter commonFilter)throws Exception {
		
		try
		{
			List<String > paramValues = new ArrayList<String>();
		
			String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			String chartType = commonFilter.getChartType();
			if(UIUtils.isValidKeyId(chartType))
				condParms += "CHARTTYPE="+chartType;
			else
				condParms += "CHARTTYPE=OEE";
				
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			
			 List<String[]> rootRptList;
		     rootRptList = dbActionTemplate.processFunctionCalls("TEST_PC_TEST.BDM_FN_OEETEST", paramValues);
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}	

			
		
}

