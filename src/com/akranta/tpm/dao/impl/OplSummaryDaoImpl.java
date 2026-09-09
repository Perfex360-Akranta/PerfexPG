package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.OplSummaryDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.ExcelUtils;

public class OplSummaryDaoImpl implements OplSummaryDao {
	
	
	private DBActionTemplate dbActionTemplate;
	
	public OplSummaryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getOplSummaryDao(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			List<String[]> summaryRpt = dbActionTemplate.processFunctionCalls("OPL_PC_ONEPOINTLESSION.OPL_FN_OPLSUMMARY", paramValues);	
			
			if( commonFilter.getViewClick() == 'Y'){
				
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return summaryRpt;	
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	public Workbook getOPLSummaryExcel(CommonFilter commonFilter,JSONObject colModel,String format) throws Exception
	{
		   ResultSet rs = null;
		   try{
			
				rs =   getOPLSummaryResultSet(commonFilter);
				ExcelUtils excelUtils = null; 
				
				
				//if( commonFilter.getGrpByCellWise() != null && commonFilter.getGrpByCellWise().equals("1") ){
					List<XLConditionalFormats> groupByFormulas = new ArrayList<XLConditionalFormats>();
					XLConditionalFormats groupByFormula = new XLConditionalFormats();
					groupByFormula.setFontName(XLConditionalFormats.FONT_DEFAULT); 
					groupByFormula.setFontColor(new RGB(0,0,0)); //red font
					groupByFormula.setFromCol(3);
					groupByFormula.setToCol(5);
					groupByFormula.setFontBoldWeight(XLConditionalFormats.FONT_BOLD);
					groupByFormula.setIdentfier("grp");
					groupByFormula.setCondFormulaStr("COUNTIF(?,\""+XLConditionalFormats.SYMBOL_TICK+"\")");
					//groupByFormula.setCondValue((char)252+"");
					groupByFormula.setBgColor(new RGB(100,100,100));
					groupByFormulas.add(groupByFormula);
					
					
					excelUtils = new ExcelUtils(colModel,groupByFormulas);
				//}
				//else
				//	excelUtils = new ExcelUtils(colModel);
				
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormatTick = new XLConditionalFormats();
				condFormatTick.setFontColor(new RGB(254,0,0)); //red font
				condFormatTick.setFontName("Wingdings");
				condFormatTick.setFontHeightPoint((short)14);
				condFormatTick.setFontBoldWeight((short)20);
				condFormatTick.setFromCol(3);
				condFormatTick.setToCol(5);
				condFormatTick.setOperator(ComparisonOperator.CONTAINS);
				condFormatTick.setCondValue("ITB"); //Tick
				condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				condFormatTick.setIdentfier("ITB");
				condFormats.add(condFormatTick);
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(0,0,254)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condFormat.setFontHeightPoint((short)14);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(3);
				condFormat.setToCol(5);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("X"); //Tick
				condFormat.setIdentfier("X");
				condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_CROSS+"");
				
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				
				return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getOPLSummaryResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("OPL_PC_ONEPOINTLESSION.OPL_FN_OPLSUMMARY", paramValues);
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
}
