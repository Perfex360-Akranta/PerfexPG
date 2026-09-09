package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.EntempAttendenceRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class EntEmpAttendenceRptDaoImpl implements EntempAttendenceRptDao {

	private DBActionTemplate dbActionTemplate; 
	public EntEmpAttendenceRptDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getEmpAttendence(CommonFilter commonFilter,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Dao Impl");
		List<String > paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("paramvalues in dao=" + paramValues);
		CommonMessage.debugMsg("inside try in dao");
		List<String[]> result=dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_EMPLOYEEATTENDENCE", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt:"+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(isInteger )
		{
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
					
	}
			
			return result;
		
		
	}
	@Override
	public Workbook getEmployeeAttendceExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside dao:");
		 ResultSet rs = null;
		   try{
			
				rs =   getEAReportResultSet(commonFilter);
				CommonMessage.debugMsg("rs:"+rs);
				ExcelUtils excelUtils = null; 
				
				
			
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
					/*XLConditionalFormats condFormat = new XLConditionalFormats();
					condFormat.setFontColor(new RGB(0,0,254)); //red font
					condFormat.setCondValue("0"); //Tick
					condFormat.setIdentfier("0");
					condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
					condFormats.add(condFormat);*/
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(0,0,254)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condFormat.setFontHeightPoint((short)14);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(0);
				condFormat.setToCol(-1);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("0"); //Tick
				condFormat.setIdentfier("0");
				condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				condFormats.add(condFormat);
				
				XLConditionalFormats condFormatTick = new XLConditionalFormats();
				condFormatTick.setFontColor(new RGB(254,0,0)); //red font
				condFormatTick.setFontName("Wingdings");
				condFormatTick.setFontHeightPoint((short)14);
				condFormatTick.setFontBoldWeight((short)20);
				condFormatTick.setFromCol(0);
				condFormatTick.setToCol(-1);
				condFormatTick.setOperator(ComparisonOperator.EQUAL);
				condFormatTick.setCondValue("1"); //Tick
				condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_CROSS+"");
				condFormatTick.setIdentfier("1");
				condFormats.add(condFormatTick);
					
					
					
					
					
					excelUtils = new ExcelUtils(tableModel,condFormats);
					excelUtils.setCondFormats(condFormats);
		
					return excelUtils.writeToExcel(rs,format,0,0,0 );
					
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getEAReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("paramValues:"+paramValues);
		ResultSet rs=dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_EMPLOYEEATTENDENCE", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt:"+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				//commonFilter.setFromRow("2");
			}}
		return rs;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("paramValues:"+paramValues);
		return paramValues;
	}
	
}
