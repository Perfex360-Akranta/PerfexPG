package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;
import com.akranta.tpm.dao.BAL_PpMatrixRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PpMatrixRptDaoImpl implements BAL_PpMatrixRptDao {
private DBActionTemplate dbActionTemplate; 
	
	public BAL_PpMatrixRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public Workbook getPPMatrixMachineRptExcelDao(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getGenPPMatrixMachineRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);

			
			List<XLConditionalFormats> groupByFormulas = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats groupByFormula = new XLConditionalFormats();
			groupByFormula.setFontName(XLConditionalFormats.FONT_DEFAULT); 
			groupByFormula.setFontColor(new RGB(0,0,0)); //red font
			groupByFormula.setFromCol(3);
			groupByFormula.setToCol(-1);
			groupByFormula.setFontBoldWeight(XLConditionalFormats.FONT_BOLD);
			groupByFormula.setIdentfier("grp");
			groupByFormula.setCondFormulaStr("COUNTIF(?,\""+XLConditionalFormats.SYMBOL_TICK+"\")");
			//groupByFormula.setCondValue((char)252+"");
			//groupByFormula.setBgColor(new RGB(100,100,100));
			groupByFormulas.add(groupByFormula);
			excelUtils = new ExcelUtils(colmodel,groupByFormulas);
			
			
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			//condFormatTick.setFontColor(new RGB(254,0,0)); //red font
			condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
//			condFormatTick.setCondValue("0"); //Tick
//			condFormatTick.setSymbolStr("");
//			condFormatTick.setIdentfier("0");
			condFormatTick.setCondValue("0"); //Tick
			condFormatTick.setSymbolStr("");	
			condFormatTick.setIdentfier("0");
			//condFormatTick.setBgColor(new RGB(213,255,195));
			condFormats.add(condFormatTick);
			
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			//condFormat.setFontColor(new RGB(0,0,0)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short)8);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(60);
			condFormat.setOperator(ComparisonOperator.GE);			
			condFormat.setCondValue("0"); //Tick
			condFormat.setBgColor(new RGB(137,197,131));//condFormat.setSymbolStr("");	
			condFormat.setIdentfier("0");
		
			//condFormat.setBgColor(new RGB(213,255,195));
			condFormat.setIdentfier("0");
			
			condFormats.add(condFormat);
			
			
			excelUtils.setCondFormats(condFormats);
			
			
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	private ResultSet getGenPPMatrixMachineRptResultSet(CommonFilter commonFilter) throws Exception
	{
		
		List<String> paramValues = new ArrayList<String>();		
		//String condParms="FROMMONTH=OCT-2011;";
		//String condParms="FROMMONTH="+commonFilter.getFromMonth()+";";
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);			
		paramValues.add(condParms);   
		paramValues.add(commonParams);	
		System.out.println("ParmValues:"+paramValues);
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_PPMATRIXMACHINEWISE", paramValues);
	}
	public Workbook getPPMatrixMonthRptExcelDao(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getGenPPMatrixRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			List<XLConditionalFormats> groupByFormulas = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats groupByFormula = new XLConditionalFormats();
			groupByFormula.setFontName(XLConditionalFormats.FONT_DEFAULT); 
			groupByFormula.setFontColor(new RGB(0,0,0)); //red font
			groupByFormula.setFromCol(3);
			groupByFormula.setToCol(-1);
			groupByFormula.setFontBoldWeight(XLConditionalFormats.FONT_BOLD);
			groupByFormula.setIdentfier("grp");
			groupByFormula.setCondFormulaStr("COUNTIF(?,\""+XLConditionalFormats.SYMBOL_TICK+"\")");
			//groupByFormula.setCondValue((char)252+"");
			groupByFormula.setBgColor(new RGB(100,100,100));
			groupByFormulas.add(groupByFormula);
			excelUtils = new ExcelUtils(colmodel,groupByFormulas);
			
			
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(254,0,0)); //red font
			condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("0"); //Tick
			condFormatTick.setSymbolStr("");
			condFormatTick.setIdentfier("0");
			condFormats.add(condFormatTick);
			
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(0,0,0)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short)8);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(60);
			condFormat.setOperator(ComparisonOperator.GE);
			condFormat.setCondValue("0"); 
			condFormat.setBgColor(new RGB(137,197,131));
			condFormat.setIdentfier("0");
			
			condFormats.add(condFormat);
			
			
			excelUtils.setCondFormats(condFormats);
			
			
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	private ResultSet getGenPPMatrixRptResultSet(CommonFilter commonFilter) throws Exception
	{
		
		List<String> paramValues = new ArrayList<String>();		
		//String condParms="FROMMONTH=OCT-2011;";
		//String condParms="FROMMONTH="+commonFilter.getFromMonth()+";";
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);			
		paramValues.add(condParms);   
		paramValues.add(commonParams);	
		System.out.println("ParmValues:"+paramValues);
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_PPMATRIXMONTHWISE", paramValues);
	}
	
	public List<String[]> getPPMatrixMonthRptDao(CommonFilter commonFilter) throws Exception
	{
		try
		{
			System.out.println("Inside DAO Impl");
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			System.out.println("CONDPARAMS:"+condParms);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			
			//String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
			//String commonParams = FilterCondSql.getCommonRelatedCondStr(commonFilter); 		
						
		
		
		 List<String[]> test = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_PPMATRIXMONTHWISE", paramValues);
		 System.out.println("DAO IMPL PLSQL EXECUTED");
		 return test;
			
		}
		catch (Exception e)
		{
			System.out.println("EXECEPTION ParmValues:"+e.getMessage());
			throw new Exception(e.getMessage()); 
			
		}
	}
	//code for machine
	
	
	public List<String[]> getPPMatrixMachineRptDao(CommonFilter commonFilter) throws Exception
	{
		try
		{
			System.out.println("Inside DAO Impl");
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			System.out.println("CONDPARAMS:"+condParms);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			
			//String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
			//String commonParams = FilterCondSql.getCommonRelatedCondStr(commonFilter); 		
						
		
			
		 List<String[]> test = dbActionTemplate.processFunctionCalls("BDM_PC_PPMATRIX.BDM_FN_PPMATRIXMACHINEWISE", paramValues);
		 System.out.println("DAO IMPL PLSQL EXECUTED");
		 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
		 return test;
			
		}
		catch (Exception e)
		{ 
			
		e.printStackTrace();
			//System.out.println("EXECEPTION ParmValues:"+e.getMessage());
		//	throw new Exception(e.getMessage()); 
			
		}
		return null;
	}
}
