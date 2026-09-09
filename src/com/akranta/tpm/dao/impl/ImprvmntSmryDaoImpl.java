package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.DrillLevelConstants;
import com.akranta.tpm.dao.ImprvmntSmryDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ImprvmntSmryDaoImpl implements ImprvmntSmryDao {
	
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
		public ImprvmntSmryDaoImpl(DBActionTemplate dbActionTemplate) 
		{
			this.dbActionTemplate = dbActionTemplate;
		}
		public void ImprvmntSmryDaoImplJwt(String JwtToken) 
		{
			try{
		
			fnCallApi = new FunctionCallApi(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public void setDbActionTemplate(DBActionTemplate dbActionTemplate) 
		{
			this.dbActionTemplate = dbActionTemplate;
		}
		
		public List<String []> getAllImprovmntSmry(CommonFilter commonFilter) throws Exception
		{
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);							
								
			//	List<String[]> impVsCompList = dbActionTemplate.processFunctionCalls("KZN_FN_KZNSUMMDRILLDOWN", paramValues);
				List<String[]> impVsCompList = fnCallApi.callFunction("KZN_FN_KZNSUMMDRILLDOWN_SB", paramValues,2,true);	
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return impVsCompList;
			
		}

		@Override
		public List<String[]> getAllImprovementSubGrp(CommonFilter commonFilter) throws Exception {
			try
			{
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);							
								
		//		List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPRESULT", paramValues);
				
		List<String[]> impVsCompList = fnCallApi.callFunction("KZN_FN_SUBGRPRESULT_SB", paramValues,2,true);
		
				
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return impVsCompList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}

		@Override
		public List<String[]> getAllImprovementSubGrpLoss(CommonFilter commonFilter) throws Exception {
			try
			{
				List<String> paramValues = new ArrayList<String>();				
				String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);							
								
				List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPLOSS", paramValues);
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return impVsCompList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}


		public List<String[]> getAllImprovementSubGrpPiller(CommonFilter commonFilter) throws Exception {
			try
			{
				List<String> paramValues = new ArrayList<String>();				
				String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);							
								
		//		List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPPILLAR", paramValues);
				
				List<String[]> impVsCompList = fnCallApi.callFunction("KZN_FN_SUBGRPPILLAR_SB", paramValues,2,false);
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return impVsCompList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}

		@Override
		public List<String[]> getAllImprovementSubGrpEqp(CommonFilter commonFilter) throws Exception {
			try
			{
				List<String> paramValues = new ArrayList<String>();				
				String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				paramValues.add(condParms);
				paramValues.add(commonParams);							
								
				List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPEQP", paramValues);
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return impVsCompList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}


		public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)	throws Exception {
			 ResultSet rs = null;
			   try{
				
				rs =   getImprovementSmryReportResultSet(commonFilter);
				CommonMessage.debugMsg("After Data....");
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
				return excelUtils.writeToExcel(rs,rptFormat,1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}

		private ResultSet getImprovementSmryReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_KZNSUMMDRILLDOWN", paramValues);
		}
		
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}

		@Override
		public Workbook improvementSmrySubReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception 
		{
			 ResultSet rs = null;
			   try{
				
				rs =   getImprovementSmrySubReportResultSet(commonFilter);
				CommonMessage.debugMsg("After Data....");
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
		
		private ResultSet getImprovementSmrySubReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_SUBGRPRESULT", paramValues);
		}

		@Override
		public Workbook improvementSmrySubLossReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getImprovementSmrySubLossReportResultSet(commonFilter);
				CommonMessage.debugMsg("After Data....");
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
		private ResultSet getImprovementSmrySubLossReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_SUBGRPLOSS", paramValues);
		}

		@Override
		public Workbook improvementSmrySubPillerReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getImprovementSmrySubPillerReportResultSet(commonFilter);
				CommonMessage.debugMsg("After Data....");
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
		
		private ResultSet getImprovementSmrySubPillerReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_SUBGRPPILLAR", paramValues);
		}

		@Override
		public Workbook improvementSmrySubEquipReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getImprovementSmrySubEquipReportResultSet(commonFilter);
				CommonMessage.debugMsg("After Data....");
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
		
		private ResultSet getImprovementSmrySubEquipReportResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_SUBGRPEQP", paramValues);
		}

		@Override
		public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception {
			// TODO Auto-generated method stub
			try
			{
				List<String> paramValues =  getFilterParamValues(chrtCommonFilter); /* new ArrayList<String>();
				
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				//if (commonFilter.getAbnIsHSE().equals("Y"))
					//condParms+="REFDOCTYPE=SFT;";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				*/
				List<String[]> dataList =  null;
				String subGrp=chrtCommonFilter.getStatus();
				if(subGrp.equals("R")){
					dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPRESULT", paramValues);
				}else if(subGrp.equals("P")){
					dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUBGRPPILLAR", paramValues);
				}
				if( chrtCommonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)+1);
					}
				}
				return dataList; 
			
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}
}
