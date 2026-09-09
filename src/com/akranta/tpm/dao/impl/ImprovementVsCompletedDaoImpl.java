package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ImprovementVsCompletedDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ImprovementVsCompletedDaoImpl implements ImprovementVsCompletedDao {

	
private DBActionTemplate dbActionTemplate; 
FunctionCallApi fnCallApi;
	
	public ImprovementVsCompletedDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void ImprovementVsCompletedDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public List<String[]> getAllImpVsComp(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Improvement Vs Completed DAO Impl");
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl 11::"+commonFilter.getType()+"  "+commonFilter.getJHKaizenCategory().getId());
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +=";EMPILLAR="+commonFilter.getType()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
			      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Improvement Vs Completed DAO Impl " + paramValues);
			
		//	List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KZNIDENCOMP", paramValues);
			List<String[]> impVsCompList = fnCallApi.callFunction("JHN_FN_KZNIDENCOMP_SB", paramValues,3,false);
			
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
	
	public Workbook ImprovementVsCompleteExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getImprovementVsCompleteResultSet(commonFilter);
			//CommonMessage.debugMsg("rs.........");
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
			return excelUtils.writeToExcel(rs,rptFormat,3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getImprovementVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KZNIDENCOMP_SB", paramValues);
	}
	
	//-------#####Kaizen Graphical Summ######-----
	public List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Improvement Vs Completed DAO Impl");
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl 11::"+commonFilter.getType()+"  "+commonFilter.getJHKaizenCategory().getId());
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +=";EMPILLAR="+commonFilter.getType()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
			      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Improvement Vs Completed DAO Impl " + paramValues);
			
	//		List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KZNGRAPHICALSUMM", paramValues);
			
			List<String[]> impVsCompList = fnCallApi.callFunction("JHN_FN_KZNGRAPHICALSUMM_SB", paramValues,3,false);
			
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
	
	public Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getKaizenGraphicalSummResultSet(commonFilter);
			//CommonMessage.debugMsg("rs.........");
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
			return excelUtils.writeToExcel(rs,rptFormat,3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getKaizenGraphicalSummResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KZNGRAPHICALSUMM_SB", paramValues);
	}
	//-------#####Kaizen Graphical Summ######-----
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	@Override
	public List<String[]> getAllIncedentImpVsComp(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Improvement Vs Completed DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Improvement Vs Completed DAO Impl " + paramValues);
			
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TESTPACKAGE.HSE_FN_DRILLDOWN", paramValues);
			List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_INCEDENTIDENVSCOMP", paramValues);
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
	public Workbook IncedentVsCompleteExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getIncedentVsCompleteResultSet(commonFilter);
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

	private ResultSet getIncedentVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getIncedentFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_INCEDENTIDENVSCOMP", paramValues);
	}
	
	private List<String> getIncedentFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	@Override
	public List<String[]> getsuggestdimple(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.KZN_FN_KZNSUGGVSIMP", paramValues);
			
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
	public List<String[]> getchartforimpl(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_KZNSUGGVSIMP", paramValues);
	}
	@Override
	public Workbook SuggestnVsImpl(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception, Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		rs =   getsuggestnResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(tableModel);
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
}
	private ResultSet getsuggestnResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.KZN_FN_KZNSUGGVSIMP", paramValues);
	}
	public List<String[]> getKznImplCount(CommonFilter commonFilter)throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Improvement Vs Completed DAO Impl");
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg("condParms::::::::::::::"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonParams::::::::::::::::::::"+commonParams);
		 // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl 11::"+commonFilter.getType()+"  "+commonFilter.getJHKaizenCategory().getId());
			
			/*if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +=";EMPILLAR="+commonFilter.getType()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
			      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";*/
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Improvement Vs Completed DAO Impl " + paramValues);
			
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KZNIMPLCOUNT", paramValues);
			
			List<String[]> impVsCompList = fnCallApi.callFunction("JHN_FN_KZNIMPLCOUNT_SB", paramValues,3,false);
			
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

		public Workbook KznImplCountExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
				ResultSet rs = null;
				   try{
					
					rs =   getKznImplCountExportExcelResultSet(commonFilter);
					//CommonMessage.debugMsg("rs.........");
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
					return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
			}

			private ResultSet getKznImplCountExportExcelResultSet(CommonFilter commonFilter) throws Exception
			{
				List<String> paramValues = getFilterParamValues(commonFilter);
				
				return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KZNIMPLCOUNT", paramValues);
			}
			public List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception {
				
				CommonMessage.debugMsg("Kaizen Suggestion Count");
				List<String> paramValues = getFilterParamValues(commonFilter);
				List<String[]> dataList =  null;
					
				//dataList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUGGCOUNTRPT", paramValues);
				dataList = fnCallApi.callFunction("KZN_FN_SUGGCOUNTRPT_sb", paramValues,2,true);
					
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 

						boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
						if(  isInteger ){
							commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
						}
					}
					return dataList;
				}

				public Workbook KznSgnCountExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
						ResultSet rs = null;
						   try{
							     CommonMessage.debugMsg("Sugg Count Excel");
							rs =   getKznSgnCountExportExcelResultSet(commonFilter);
							//CommonMessage.debugMsg("rs.........");
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
							return excelUtils.writeToExcel(rs,rptFormat,1,0,0 ); //elumalai
							
						   }finally{
							   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
						   }
					}

					private ResultSet getKznSgnCountExportExcelResultSet(CommonFilter commonFilter) throws Exception
					{
						List<String> paramValues = getFilterParamValues(commonFilter);
					
						return dbActionTemplate.NewdbFunctionCall2("KZN_FN_SUGGCOUNTRPT", paramValues);
						//return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_KZNSGNCOUNT", paramValues);
					}
					public List<String[]> getsuggchartforimpl(CommonFilter commonFilter) throws Exception {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						List<String> paramValues = getFilterParamValues(commonFilter);
						List<String[]> dataList =  null;
							
						dataList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KZNGRAPHICALSUMM_SB", paramValues);
//						dataList = fnCallApi.callFunction("JHN_FN_KZNGRAPHICALSUMM_SB", paramValues,3,false);	
							if( commonFilter.getViewClick() == 'Y'){
								String totalCnt = paramValues.get(0); 

								boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
								if(  isInteger ){
									commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
								}
							}
							return dataList;
						
					}
					public List<String[]> getKaizenCululative(CommonFilter commonFilter)
							throws Exception {
						try
						{
							
							List<String > paramValues = new ArrayList<String>();
							String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
							String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
							
							CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl 11::"+commonFilter.getType()+"  "+commonFilter.getJHKaizenCategory().getId());
							
							if(UIUtils.isValidKeyId(commonFilter.getType()))
							      condParms +=";EMPILLAR="+commonFilter.getType()+";";
							
							if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
							      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
							
							paramValues.add(condParms);
							paramValues.add(commonParams);
							CommonMessage.debugMsg("Inside Improvement Vs Completed DAO Impl " + paramValues);
							
							//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KZNIMPCUMULATIVE", paramValues);
							List<String[]> impVsCompList = fnCallApi.callFunction("JHN_FN_KZNIMPCUMULATIVE_sb", paramValues,3,false);
							
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
					//mano
					public Workbook KaizenCumulativeExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception {
					    ResultSet rs = null;
					    try {
					        rs = getKaizenCumulativeResultSet(commonFilter);
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
					        condFormat.setCondValue((char)252+""); //Tick
					        condFormat.setIdentfier("tick");
					        condFormats.add(condFormat);
					        excelUtils.setCondFormats(condFormats);
					        return excelUtils.writeToExcel(rs, rptFormat, 3, 0, 0);
					        
					    } finally {
					        DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					    }
					}

					private ResultSet getKaizenCumulativeResultSet(CommonFilter commonFilter) throws Exception {
					    List<String> paramValues = getFilterParamValues(commonFilter);
					    return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KZNIMPCUMULATIVE_SB", paramValues);
					}
		public List<String[]> getEmpDmtKaizen(CommonFilter commonFilter)
							throws Exception {
						try
						{
							
							List<String > paramValues = new ArrayList<String>();
							String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
							String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
														
							if(UIUtils.isValidKeyId(commonFilter.getType()))
							      condParms +=";EMPILLAR="+commonFilter.getType()+";";
							
							if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
							      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
							
							paramValues.add(condParms);
							paramValues.add(commonParams);							
							List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.JHN_FN_EMPLOYEEDMTKAIZEN", paramValues);
							
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
		public Workbook EmpDmtWiseKaizenExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
			ResultSet rs = null;
			   try{
				rs =getEmpDmtKznResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );	
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
		private ResultSet getEmpDmtKznResultSet(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_EMPLOYEEDMTKAIZEN", paramValues);
		}	
}