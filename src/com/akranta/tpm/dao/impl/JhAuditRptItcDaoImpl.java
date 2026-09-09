package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.JhAuditRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class JhAuditRptItcDaoImpl implements JhAuditRptDao {

private DBActionTemplate dbActionTemplate;
FunctionCallApi fnCallApi;
	
	public JhAuditRptItcDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void JhAuditRptDaoImplJwt(String JwtToken) 
	{
		try{
			//momServiceApi = new MomServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String []> getJhAuditRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("JHN_PC_AUDIT.JHN_FN_JHAUDIT", paramValues);
			CommonMessage.debugMsg("Length...."+dataList.size());
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
	
	
	
	public Workbook getJhAuditReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getJhAuditReport(commonFilter);
			CommonMessage.debugMsg(rs);
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
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	public Map<Integer, List<String[]>> getAllJHAuditRptExl(String rowId,String format) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(rowId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("JHN_PC_AUDIT.JHN_FN_JHAUDITREPORTEXL", paramValues,2);
			
			
			for(int i = 0; i <  whyReport.size();i++ )
			{
				List<String[]> lists =  whyReport.get(i);
				CommonMessage.debugMsg("List values......"+whyReport.get(i));
			}
			
			CommonMessage.debugMsg("Inside v: " +whyReport.toString());
			CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
		//	return whyReport;
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}		
	@Override
	public List<String[]> getHseAccTrendRptgraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonMessage.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_HSEINJURYCNT", paramValues);			
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

	private ResultSet getJhAuditReport(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("JHN_PC_AUDIT.JHN_FN_JHAUDIT", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}

	@Override
	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getAllAuditScoreGraph(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workbook jhAuditGraphExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
