package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SmryAbnDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class SmryAbnDaoImpl implements SmryAbnDao {
	
private DBActionTemplate dbActionTemplate; 
FunctionCallApi fnCallApi;
	
	public SmryAbnDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void SmryAbnDaoImplJwt(String JwtToken) 
	{
		try{
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String []> getAllSmryAbns(CommonFilter commonFilter,String checkField) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
		/*	if ("Y".equals(commonFilter.getAbnIsHSE()))
				condParms+="REFDOCTYPE=SFT;";			
		*/	
			CommonMessage.debugMsg("type..."+commonFilter.getDrillFlag());
			if("Y".equals(commonFilter.getType()))
				condParms+="TYPE=TEAM;";
			if(UIUtils.isValidKeyId(commonFilter.getTeamLevelNo()))
				condParms+="TEAMLEVELNO="+commonFilter.getTeamLevelNo()+";";
			if("b".equals(commonFilter.getDrillFlag()))
				condParms+="BACK=Y;";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =null;
			if(checkField.equals("HSE"))
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_ABNSUMMARY", paramValues);
			else
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSUMMARY", paramValues);
			
			if (dataList.size()>0)
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
		/*try
		{
		
			List<String> paramValues = new ArrayList<String>();
			
			CommonMessage.debugMsg("Factory ID : "+commonFilter.getFactoryId());
			CommonMessage.debugMsg("Section ID : "+commonFilter.getSectionId());
			CommonMessage.debugMsg("Cell ID : "+commonFilter.getCellId());
			CommonMessage.debugMsg("Machine ID : "+commonFilter.getMachineId());
			CommonMessage.debugMsg("DrillLevel : "+commonFilter.getDrillLevel());
			CommonMessage.debugMsg(commonFilter.getFromDate());
			CommonMessage.debugMsg(commonFilter.getToDate());
	
					
			paramValues.add(commonFilter.getDrillLevel());
			paramValues.add(commonFilter.getCompany()!= null ? commonFilter.getCompany().getId():"{}");	
			paramValues.add(commonFilter.getFactory()!= null ? commonFilter.getFactory().getId():"{}");		
			paramValues.add(commonFilter.getSection() != null ? commonFilter.getSection().getId():"{}");
			paramValues.add(commonFilter.getCell() != null ? commonFilter.getCell().getId():"{}");
			paramValues.add(commonFilter.getMachine() != null ? commonFilter.getMachine().getId():"{}");
			paramValues.add(commonFilter.getCostCenter() != null ? commonFilter.getCostCenter().getId():"{}");
			paramValues.add(commonFilter.getEqpGroup() != null ? commonFilter.getEqpGroup().getId():"{}");
			paramValues.add(commonFilter.getJhStep() != null ? commonFilter.getJhStep().getId():"{}");			
			paramValues.add(commonFilter.getCircle() != null ? commonFilter.getCircle().getId():"{}");
			paramValues.add(commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId():"{}");
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
		
			CommonMessage.debugMsg("ParamValues:"+paramValues);
			return dbActionTemplate.processFunctionCalls("ABN_PC_ABNORMALITY.ABN_FN_ABNSUMMARY", paramValues);
			

		
		}
		*/
	}

	
	public List<String []> getAllSmryAbnEmp(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("  emptype 1"+commonFilter.getEmpwiseType());
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
		/*	if ("Y".equals(commonFilter.getAbnIsHSE()))
				condParms+="REFDOCTYPE=SFT;";			
		*/
			condParms+="DETECTEDBY="+commonFilter.getAllotedDtTo();
			CommonMessage.debugMsg("  emptype 2"+commonFilter.getEmpwiseType());
			paramValues.add(condParms +";EMPTYPE="+commonFilter.getEmpwiseType()+";");
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg(condParms +"  commonParams in side the Dao Impl    "+commonParams );
			List<String[]> dataList =null;
			if (commonFilter.getEmpwiseType().equals("KZN") || commonFilter.getEmpwiseType().equals("OPL"))
				dataList =  fnCallApi.callFunction("KZN_FN_EmployeeWise_SB", paramValues,2,true);//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_EmployeeWise", paramValues);

			else
				dataList = fnCallApi.callFunction("ABN_FN_EMPWISEKZNOPLABN_SB", paramValues,4, true);
//				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_EMPWISEKZNOPLABN", paramValues);
			
			
			CommonMessage.debugMsg(condParms +"  commonParams in side the Dao Impl 123    "+commonParams +"   commonFilter.getEmpwiseType() "+commonFilter.getEmpwiseType());
			
			/*if (dataList.size()>0)
				CommonMessage.debugMsg("Length...."+dataList.size());				
			*/		
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}

	public List<String []> getAllManager(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("  emptype 1"+commonFilter.getEmpwiseType());
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
		/*	if ("Y".equals(commonFilter.getAbnIsHSE()))
				condParms+="REFDOCTYPE=SFT;";			
		*/
			condParms+="DETECTEDBY="+commonFilter.getAllotedDtTo();
			CommonMessage.debugMsg("  emptype 2"+commonFilter.getEmpwiseType());
			paramValues.add(condParms +";EMPTYPE="+commonFilter.getEmpwiseType()+";");
			paramValues.add(commonParams);
			List<String[]> dataList =null;
			if (commonFilter.getEmpwiseType().equals("KZN") )
			//	dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_MANAGERWISE", paramValues);
			dataList =  fnCallApi.callFunction("KZN_FN_MANAGERWISE_SB", paramValues,4,true);
			else
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_EMPWISEKZNOPLABN", paramValues);
			
			
			/*if (dataList.size()>0)
				CommonMessage.debugMsg("Length...."+dataList.size());				
			*/		
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	public Workbook smryAbnormalityReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat,String checkField) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getAbnormalityReportResultSet(commonFilter,checkField);
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
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getAbnormalityReportResultSet(CommonFilter commonFilter,String checkField) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("checkField:"+checkField);
		if(checkField.equals("HSE"))
			return dbActionTemplate.NewdbFunctionCall2("HSE_FN_ABNSUMMARY", paramValues);
		else
			return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSUMMARY", paramValues);
			
		
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		CommonMessage.debugMsg("Condition PArameters"+condParms);
		CommonMessage.debugMsg("Common PArameters"+commonParams);
		paramValues.add(condParms+"EMPTYPE="+commonFilter.getEmpwiseType()+";");
		paramValues.add(commonParams);
		
		return paramValues;
	}
	private ResultSet getAbnReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		String functionName = "";
		if (commonFilter.getEmpwiseType().equals("KZN") || commonFilter.getEmpwiseType().equals("OPL"))
			functionName = "KZN_FN_EmployeeWise";
		else
			functionName = "ABN_FN_EMPWISEKZNOPLABN";
		
		
		return dbActionTemplate.NewdbFunctionCall2(functionName, paramValues);
	}
	
	private ResultSet getManagerResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		String functionName = "";
		if (commonFilter.getEmpwiseType().equals("KZN"))
			functionName = "KZN_FN_MANAGERWISE";
		else
			functionName = "ABN_FN_EMPWISEKZNOPLABN";
		
		
		return dbActionTemplate.NewdbFunctionCall2(functionName, paramValues);
	}
	public Workbook AbnsmryEmpReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
			ResultSet rs = null;
		int startRow = 3;
			rs =   getAbnReportResultSet(commonFilter);
			if (commonFilter.getEmpwiseType().equals("KZN") || commonFilter.getEmpwiseType().equals("OPL"))
				startRow = 2;
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat, startRow,1,0 );
	}
	
	public Workbook ManagerExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
		ResultSet rs = null;
	
		rs =   getManagerResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colModel);
		return excelUtils.writeToExcel(rs,rptFormat, 3,1,0 );
}

	@Override
	public List<String[]> getpiechart(CommonFilter piechrtCommonFilter, String flag) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues =  getFilterParamValues(piechrtCommonFilter);  
			List<String[]> dataList =  null;
			if(flag.equals("Y")){
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSUMMARYREMOVED", paramValues);
			}
			else{
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSUMMARYIDENTIFIED", paramValues);
			}
			if( piechrtCommonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					piechrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)+1);
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
