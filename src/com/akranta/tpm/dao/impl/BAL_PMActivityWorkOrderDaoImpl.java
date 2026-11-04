package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PMActivityWorkOrderDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PMActivityWorkOrderDaoImpl implements BAL_PMActivityWorkOrderDao{

private DBActionTemplate dbActionTemplate; 

FunctionCallApi fnCallApi;
	
public BAL_PMActivityWorkOrderDaoImpl(DBActionTemplate dbActionTemplate) 
{
	try{
	this.dbActionTemplate = dbActionTemplate;
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
}
public void BAL_PMActivityWorkOrderDaoImplJwt(String JwtToken) 
{
	try{
		
	fnCallApi = new FunctionCallApi(JwtToken);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	@Override
	public Workbook getAllWoActivityExportExcel(CommonFilter commonFilter,JSONObject tableModel, String rptFormat) throws IOException, SQLException {
		ResultSet rs = null;
		try {
			rs = getPMChecksheetReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 2, 0, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
		return null;
	}

	private ResultSet getPMChecksheetReportResultSet(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rs = null;
		//rs = dbActionTemplate.dbFunctionCall("PLM_PC_PMWORKORDRER.PLM_FN_PMACTIVITYWO", paramValues);
		rs = dbActionTemplate.NewdbFunctionCall2("PLM_FN_PMACTIVITYWO", paramValues);
		return rs;
	}
	
	public Workbook getPendingActivityExportExcel(CommonFilter commonFilter,JSONObject tableModel, String rptFormat) throws IOException, SQLException {
		ResultSet rs = null;
		try {
			rs = getPendingActivityResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 2, 1, 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
		return null;
	}

	private ResultSet getPendingActivityResultSet(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rs = null;
		rs = dbActionTemplate.NewdbFunctionCall2("PLM_FN_PMPENDINGACTIVITIES", paramValues);
		return rs;
	}
	
	@Override
	public List<String[]> getAllWoActivity(CommonFilter commonFilter) throws Exception {
		
		
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_PMACTIVITYWO", paramValues);
			List<String[]> dataList = fnCallApi.callFunction("PLM_FN_PMACTIVITYWO_SB", paramValues, 3, true);
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
public List<String[]> getPendingActivity(CommonFilter commonFilter) throws Exception {
		
		
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_PMPENDINGACTIVITIES", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("PLM_FN_PMPENDINGACTIVITIES_SB", paramValues,3,true);
			
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
		
		
		/*
		try{
	      List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
		paramValues.add(condParms);
		paramValues.add(commonParams);
			//commonFilter.setFromDate("01-Oct-2018");
			//commonFilter.setToDate("04-Oct-2018");
			//commonFilter.setSectionId("SEC0000017");
			CommonFunctions.debugMsg("msg: dataList 01" + paramValues);
			
			//PLM_FN_GETACTIVITYFORWO
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_PMACTIVITYWO", paramValues);
		CommonFunctions.debugMsg("msg: dataList 05" +dataList.toString() );
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....123"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){ 
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}

			return dataList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
			
		
	}
	*/
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		List<String> paramValues  = new ArrayList<String>();
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		CommonFunctions.debugMsg(condParms +" condParms " + commonParams);
		 
		return paramValues; 
	}
}
