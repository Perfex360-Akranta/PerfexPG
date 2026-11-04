
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.service.api.BdmServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.Request;

import com.akranta.tpm.dao.BAL_FailurePhnMatrixDao;
import com.akranta.tpm.dao.sql.BreakDownSql;
import com.akranta.tpm.dao.sql.FilterCondSql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class BAL_FailurePhnMatrixDaoImpl implements BAL_FailurePhnMatrixDao {
	


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public BAL_FailurePhnMatrixDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void BAL_FailurePhnMatrixDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<String[]> getAllFailurePhnMatrix(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			 List<String > paramValues = new ArrayList<String>();
			 
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
		
			 System.out.println("paramvalues" + paramValues);
			// List<String[]> rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_PPMATRIX.BDM_FN_PPMATRIXRPT", paramValues);	//(functionName, paramValues)  
			// List<String[]> rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_PPMATRIX.BDM_FN_PPMATRIXRPT", paramValues);	//(functionName, paramValues)
			 List<String[]> rootRptList = fnCallApi.callFunction("BDM_FN_PPMATRIXRPT", paramValues,4,false);	//(functionName, paramValues)
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			System.out.println(" ::: 99- " + rootRptList.size());
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
 
	public Workbook getppmatrix(CommonFilter commonFilter, JSONObject colmodel,String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 4,0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("BDM_FN_PPMATRIXRPT", paramValues);
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