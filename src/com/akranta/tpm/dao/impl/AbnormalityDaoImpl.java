package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AbnormalityDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class AbnormalityDaoImpl implements AbnormalityDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	
	public AbnormalityDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}
	
	public void AbnormalityDaoImplJwt(String JwtToken) 
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
	
	public List<String []> getAbnormality(CommonFilter commonFilter) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			paramValues.add(commonFilter.getAssembly()!= null ? commonFilter.getAssembly().getId():"{}");
			paramValues.add(commonFilter.getMachine()!= null ? commonFilter.getMachine().getId():"{}");
			paramValues.add(commonFilter.getCircle()!= null ? commonFilter.getCircle().getId():"{}");
			paramValues.add(commonFilter.getEqpGroup()!= null ? commonFilter.getEqpGroup().getId():"{}");
			paramValues.add(commonFilter.getProduction()!= null ? commonFilter.getProduction().getId():"{}");
			paramValues.add(commonFilter.getSection()!= null ? commonFilter.getSection().getId():"{}");
			paramValues.add(commonFilter.getCell() != null ? commonFilter.getCell().getId():"{}");
			paramValues.add(commonFilter.getFactory()!= null ?commonFilter.getFactory().getId():"{}");
			paramValues.add(commonFilter.getCostCenter()!= null ? commonFilter.getCostCenter().getId():"{}");;
			paramValues.add(commonFilter.getJhStep()!= null ? commonFilter.getJhStep().getId():"{}");
			paramValues.add(commonFilter.getMachineRank()!= null ? commonFilter.getMachineRank().getId():"{}");
			paramValues.add(commonFilter.getAbnStatus() != null ? commonFilter.getAbnStatus().getId():"P,C,W");
			
			CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
			List<String[]> AbnHeader =  null;

			AbnHeader = dbActionTemplate.processFunctionCalls("ABN_PC_ABNORMALITY.ABN_FN_ABNABNORMALITY", paramValues);
			
			//List<String[]> masterModuleGroup = reportProcedures.execute();
				
			//fillAbnormality(PmstandardModel);
			return AbnHeader;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	@Override
	public List<String[]> getAbnjh(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			condParms=condParms+"FLID="+commonFilter.getFlid();
			//condParms="FLID="+commonFilter.getFlid();
		/*	if (commonFilter.getAbnIsHSE().equals("Y"))
				condParms+="REFDOCTYPE=SFT;";
		*/	
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//ABN_PC_ABNORMALITY.ABN_FN_ABNCUMULATIVERPT
//			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNJHREPORT_SB", paramValues);
			dataList =  fnCallApi.callFunction("ABN_FN_ABNJHREPORT_SB", paramValues,4,true);
			
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
	
	public List<String[]> getAbnjhgraph(CommonFilter commonFilter) throws Exception {
		{
			try
			{
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
			/*	if (commonFilter.getAbnIsHSE().equals("Y"))
					condParms+="REFDOCTYPE=SFT;";
			*/	
				paramValues.add(condParms);
				paramValues.add(commonParams);
				List<String[]> dataList =  null;
	//ABN_PC_ABNORMALITY.ABN_FN_ABNCUMULATIVERPT
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNJHREPORT", paramValues);
				
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
	}
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
		/*	if (commonFilter.getAbnIsHSE().equals("Y"))
				condParms+="REFDOCTYPE=SFT;";
		*/	
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//ABN_PC_ABNORMALITY.ABN_FN_ABNCUMULATIVERPT
//			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNCUMULATIVERPT", paramValues);
			dataList =  fnCallApi.callFunction("ABN_FN_ABNCUMULATIVERPT_SB", paramValues,4,true);
			
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

	@Override
	public Workbook AbnormalityReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getAbnormalityReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getAbnormalityReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
		
		return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNCUMULATIVERPT", paramValues);
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}


	@Override
	public Workbook AbnjhReportExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getAbnjhReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
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
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getAbnjhReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
		
		
		return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNJHREPORT", paramValues);
		
	}


	@Override
	public List<String[]> getAbnIdentifiedCompleted(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
		/*	if (commonFilter.getAbnIsHSE().equals("Y"))
				condParms+="REFDOCTYPE=SFT;";
		*/	
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//ABN_PC_ABNORMALITY.ABN_FN_ABNCUMULATIVERPT
//			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNIDENTIFIED", paramValues);
			dataList =  fnCallApi.callFunction("ABN_FN_ABNIDENTIFIED_SB", paramValues,4,true);
			
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


	@Override
	public Workbook AbnormalityIdentifiedExportExcel(CommonFilter commonFilter, JSONObject colModel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getAbnormalityIdentifiedResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,format, 4,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getAbnormalityIdentifiedResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValuesIDf(commonFilter);
		CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
		
		return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNIDENTIFIED_SB", paramValues);
	}
	
	private List<String> getFilterParamValuesIDf(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

}
