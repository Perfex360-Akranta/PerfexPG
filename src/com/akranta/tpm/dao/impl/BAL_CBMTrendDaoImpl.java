package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_CBMTrendDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class BAL_CBMTrendDaoImpl implements BAL_CBMTrendDao{

	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public void BAL_CBMTrendDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public BAL_CBMTrendDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getAllCbmTrend(CommonFilter commonFilter)	throws Exception {
	
		try
		{
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);					
							
			//List<String[]>WorkOrderOverViewList = dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CBMREADINGTREND", paramValues);
			List<String[]>WorkOrderOverViewList = fnCallApi.callFunction("PLM_FN_CBMREADINGTREND_SB", paramValues,3,true);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return WorkOrderOverViewList;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
	
	public Workbook CBMExcelReport(CommonFilter commonFilter, JSONObject colModel, String rptFormat)	throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getCBMExcelReport(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 3,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	private ResultSet getCBMExcelReport(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_CBMREADINGTREND_SB", paramValues);
	}
	
	

}
