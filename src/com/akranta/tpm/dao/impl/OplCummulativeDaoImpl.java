
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.OplCummulativeDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;



public class OplCummulativeDaoImpl implements OplCummulativeDao {
	


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public OplCummulativeDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	//-- added by vignesh -- //
	public void OplCummulativeDaoImplJwt(String JwtToken) {
	    try {
	       
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //
	
	public List<String[]> getAlloplcumm(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside wertgwer DAO Impl");
				
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl ::"+commonFilter.getType());
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +="EMPILLAR="+commonFilter.getType()+";";
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside  opl DAO Impl Cummulative " + paramValues);
			
			//List<String[]>  oplCummulativeList = dbActionTemplate.processFunctionCallsWithColHeaders("OPL_FN_CUMULATIVERPT", paramValues);
			List<String[]> rootRptList;
		//	rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("OPL_FN_CUMULATIVERPT", paramValues);	
			
			rootRptList = fnCallApi.callFunction("OPL_FN_CUMULATIVERPT_SB", paramValues,2,true);	
			
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return rootRptList	;
			//return oplCummulativeList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getAlloplcummExl(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			 CommonMessage.debugMsg("FromtoRow:::::::::"+commonFilter.getFromRow());
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("OPL_FN_CUMULATIVERPT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	

	public List<String[]> getOplCountData(CommonFilter commonFilter)throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> dataList =  null;
		
	//	dataList = dbActionTemplate.processFunctionCalls("OPL_FN_OPLCOUNT", paramValues);
		dataList = fnCallApi.callFunction("OPL_FN_OPLCOUNT_SB", paramValues,2,true);
	
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 

			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	}	

public Workbook getOplCountExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		ResultSet rs = null;
		try{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			rs =  dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLCOUNT", paramValues);
			
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 1,0,0 );
		
		   }
		finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}	

}
