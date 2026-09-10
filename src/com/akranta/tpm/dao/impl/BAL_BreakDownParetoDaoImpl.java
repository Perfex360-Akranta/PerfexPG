package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_BreakDownParetoDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_BreakDownParetoDaoImpl implements BAL_BreakDownParetoDao {
private DBActionTemplate dbActionTemplate; 
	
	public BAL_BreakDownParetoDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub

		try
		{

			List<String > paramValues = new ArrayList<String>();
			System.out.println("Inside  BreakDown Pareto DAO Impl");
			
			
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);		
			
			//CommonFunctions.debugMsg("condParmsBB  :"+condParms+commonParams);
			List<String[]> brkdParetolist = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_BDPARETO", paramValues);
			//List<String[]> losslist = dbActionTemplate.processFunctionCalls("TST_PC_TESTPACK1.KZN_FN_LOSSSUMMARY", paramValues);
			
			CommonFunctions.debugMsg("brkdParetolist          "+brkdParetolist.size());
			return brkdParetolist;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getparetoExcel(CommonFilter commonFilter,JSONObject colmodel, String rptformat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptformat, 0,0,0 );
			
		   }finally{
			   if(rs != null)
			      DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_BDPARETO", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	
	}

}
