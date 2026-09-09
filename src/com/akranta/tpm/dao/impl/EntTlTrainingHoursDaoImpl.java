package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;
//import com.akranta.tpm.dao.CustomerComplaintStmtRptDao;
import com.akranta.tpm.dao.EntTlTrainingHoursDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class EntTlTrainingHoursDaoImpl implements  EntTlTrainingHoursDao {	
	
	private DBActionTemplate dbActionTemplate; 
	
	public EntTlTrainingHoursDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getTrainingHoursPgm(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String> paramValues = new ArrayList<String>();		
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			List<String[]> rootRptList;
			 rootRptList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGHOUR", paramValues);	
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return rootRptList	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	public List<String[]> getTrainingHoursPgmGraph(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGHOUR", paramValues);			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public Workbook getTrainingHoursExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getTrainingHoursResultSet(commonFilter);
			List<XLConditionalFormats> groupByFormulas = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats groupByFormula = new XLConditionalFormats();
			groupByFormula.setFontName(XLConditionalFormats.FONT_DEFAULT); 
			groupByFormula.setFromCol(6);
			groupByFormula.setToCol(6);
			groupByFormula.setFontBoldWeight(XLConditionalFormats.FONT_BOLD);
			groupByFormula.setIdentfier("summaryTpl");
			groupByFormula.setCondFormulaStr("SUM(?)");
			
			//groupByFormula.setCondValue((char)252+"");
			groupByFormula.setBgColor(new RGB(237,231,211));
			groupByFormulas.add(groupByFormula);			
			ExcelUtils excelUtils = new ExcelUtils(colmodel,groupByFormulas);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	}
	private ResultSet getTrainingHoursResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGHOUR", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		
		List<String> paramValues = new ArrayList<String>();			
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		
		//paramValues.add(commonParams);
		paramValues.add(commonParams);
		
		return paramValues;
	}	
	
	
	
}
