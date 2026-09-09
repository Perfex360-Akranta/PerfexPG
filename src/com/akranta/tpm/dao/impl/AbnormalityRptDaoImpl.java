package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnormalityRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class AbnormalityRptDaoImpl implements AbnormalityRptDao {

private DBActionTemplate dbActionTemplate;
FunctionCallApi fnCallApi;
	
	public AbnormalityRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void AbnTlAbnormalityDaoImplJwt(String JwtToken) 
	{
		try{
//		abnServiceApi = new AbnormalityServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String []> getAbnormality(CommonFilter commonFilter,String type) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Chk in Dao impl"+type);
			List<String> paramValues = new ArrayList<String>();
			CommonMessage.debugMsg(commonFilter.getToRow());
			String torow = commonFilter.getToRow();
			//commonFilter.setToRow(Integer.toString(torowVal));	
			//commonFilter.getChkActwise();
			//String includeinshutdown = commonFilter.getChkActwise();
			//String checkbox=" ";
		/*	if(includeinshutdown == "Y")
			
			{
				checkbox=includeinshutdown;
				
			}*/
			CommonMessage.debugMsg("result");
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		    //condParms +="includeinshutdown="+includeinshutdown;
			condParms +="type="+type;
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
/*			if (commonFilter.getAbnIsHSE().equals("Y"))
				condParms+="REFDOCTYPE=SFT;";
*/			
			
			String afeem=commonFilter.getAbnImp();
			if("AFEEM".equals(afeem))
				condParms += "AFEEM=AFEEM;";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =null;
	        CommonMessage.debugMsg("commonFilter.getChkActwise::"+commonFilter.getChkActwise());
//			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNABNORMALITY", paramValues);
			dataList =  fnCallApi.callFunction("ABN_FN_ABNABNORMALITY_SB", paramValues,4,true);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt+"--"+paramValues.get(0));
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
	//Ageing Report
	
	public List<String []> getAgeAbnormalityDao(CommonFilter commonFilter,String type) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("inside dao impl abnrmality aging");
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues:"+paramValues);
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_PC_ABNORMALITY.ABN_FN_ABNAGEINGREPORT", paramValues);
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
	public Workbook getAbnormalityReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat,String type) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			   
			rs =getAbnormalityReportResultSet(commonFilter, type);
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
			return excelUtils.writeToExcel(rs,rptFormat, 4,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getAbnormalityReportResultSet(CommonFilter commonFilter,String type) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		condParms +="type="+type;
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = null;
		CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
		//String condParms ="type="+type;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		rs = dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNABNORMALITY_SB", paramValues);
		
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	//getAbnormalityAgeReport
	public Workbook getAbnormalityAgeReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getAbnormalityAgeReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(254,0,0)); //red font
			condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(7);
			condFormatTick.setToCol(15);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("-1"); //Tick			
			condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");			
			condFormats.add(condFormatTick);
			
			
			
			XLConditionalFormats condFormatEmpty = new XLConditionalFormats();
			condFormatEmpty.setFontColor(new RGB(254,0,0)); //red font
			condFormatEmpty.setFontName("Wingdings");
			condFormatEmpty.setFontHeightPoint((short)14);
			condFormatEmpty.setFontBoldWeight((short)20);
			condFormatEmpty.setFromCol(7);
			condFormatEmpty.setToCol(15);
			condFormatEmpty.setOperator(ComparisonOperator.EQUAL);
			condFormatEmpty.setCondValue("0"); //NULL		
			condFormatEmpty.setSymbolStr("");
			condFormats.add(condFormatEmpty);
			
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getAbnormalityAgeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.ABN_FN_ABNAGEINGREPORT", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		FilterCondSql.getGridCommonParams(commonFilter);
		
//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";
		
		paramValues.add(condParms);
		//paramValues.add(commonParams);
		CommonMessage.debugMsg("paramValues............"+paramValues);
		return paramValues;
	}

	@Override
	public List<String[]> getAbnTagTrend(CommonFilter commonFilter)
			throws Exception 
	{
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
//		return dbActionTemplate.processFunctionCalls("ABN_FN_ABNTAGTREND", paramValues);
		return fnCallApi.callFunction("ABN_FN_ABNTAGTREND_SB", paramValues,4,true);
	}

	@Override
	public Workbook getAbnTagTrendExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		 try
		 {
			 rs =   getAbnTagTrendReportResultSet(commonFilter);
			 ExcelUtils excelUtils = new ExcelUtils(tableModel);
			 return excelUtils.writeToExcel(rs,format,3,0,0);
		 }
		 finally
		 {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		 }
	
	}
	private ResultSet getAbnTagTrendReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues1(commonFilter);
		
		return  dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNTAGTREND", paramValues);
	}
	private List<String> getFilterParamValues1(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		paramValues.add(condParms);
		paramValues.add(FilterCondSql.getGridCommonParams(commonFilter));
		CommonMessage.debugMsg("paramValues............"+paramValues);
		return paramValues;
	}
	@Override
	public Workbook getExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1)  throws Exception {
		ResultSet rs = null;
		 int colVal = 0;
		   try{
		
			rs =   getAbnDetailsResultSet(commonFilter1);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			/*List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);*/
			return excelUtils.writeToExcel(rs,format, 2,0,0);
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } 		
	}

	private ResultSet getAbnDetailsResultSet(CommonFilter commonFilter1) throws Exception {
		
		List<String> paramValues = new ArrayList<String>();
       String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter1);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter1);
		condParam += ";COLUMN="+commonFilter1.getColumnId();
		condParam += ";CHKTRADEWISE= "+commonFilter1.getChktradewise();
		condParam +=";COLINDEXNAME="+ commonFilter1.getColIndexName();
		condParam += ";HEADER="+commonFilter1.getHeader2();
		condParam += ";";
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		return dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.ABN_FN_VIEWABNORMALITY", paramValues);
	}

	
	

	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,String keyid, String columnId,String colIndexName) throws Exception {
		List<String[]> dataList=new ArrayList<String[]>();
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			
			
			String condParams = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParams += ";COLUMN="+columnId;
			condParams += ";CHKTRADEWISE= "+commonFilter.getChktradewise();
			condParams +=";COLINDEXNAME="+ colIndexName;
			condParams += ";HEADER="+commonFilter.getHeader2();
			condParams += ";";
			paramValues.add(condParams);			
			paramValues.add(commonParams);
			CommonMessage.debugMsg("chkTradewise " + commonFilter.getChktradewise());
			CommonMessage.debugMsg("condParams " + condParams);
			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_PC_ABNORMALITY.ABN_FN_VIEWABNORMALITY", paramValues);
			CommonMessage.debugMsg("After " +commonFilter.getViewClick());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return dataList;	
	
}
	
	@Override
	public List<String[]> getAbnSummaryGridData(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
	
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

//			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_SUMMARY_RPT_SB",	paramValues);
			List<String[]> dataList = fnCallApi.callFunction("ABN_FN_SUMMARY_RPT_SB",	paramValues,3,true);

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getAbnSummaryGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("ABN_FN_SUMMARY_RPT_SB", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,3,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}

	@Override
	public List<String[]> getAgeAbnormalityDao(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
