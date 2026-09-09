package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ETProgCalenderRptDao;
import com.akranta.tpm.dao.sql.EntReportRelatedSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ETProgCalenderRptDaoImpl implements ETProgCalenderRptDao {

private DBActionTemplate dbActionTemplate;
FunctionCallApi fnCallApi;
	
	public ETProgCalenderRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void ETProgCalenderRptDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<String []> getTrnPrgCal(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams =FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("condParms"+condParms);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGLOGX",paramValues);
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
	
	

	public Workbook getTrnPrgCalReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getTrnPrgCalReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getTrnPrgCalReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGLOGX", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("paramValues............"+paramValues);
		return paramValues;
	}
	
	//Before and After Skill Analysis Report
	
	
	
	public List<String []> getSkillAnalysis(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams =FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("condParms"+condParms);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_BEFAFTSKILANALYSIS",paramValues);
			
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
	
	public List<String []> getBefAftSkill(CommonFilter commonFilter,String empId) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams =FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			if(UIUtils.isValidKeyId(empId)){
				condParms+="EMPLOYEEID="+empId+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("EMPLOYEEID"+empId);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_BEFOREAFTERSKILANALYSIS",paramValues);
			
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
	public List<String[]> getEmpBefAftTopicRatings(String assessmentId,String date,String fromSpoke) throws NoDataFoundException, Exception{
		Object [] args = {assessmentId };
		String sql = EntReportRelatedSqls.getEmpAssessmentBefAftRatingSql(assessmentId,date,fromSpoke);
		
		return dbActionTemplate.getDataList(sql);
	}
	
	public Workbook SkillAnalysisReport(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getSkillAnalysisReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getSkillAnalysisReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_BEFAFTSKILANALYSIS", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	
	//***********Plan VS Complete
	
	
	public List<String []> getPlanVsCompCal(CommonFilter commonFilter,String custmrtype) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams =FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
            String type=commonFilter.getType();
            //String custmrtype=commonFilter.getMachineId();
			
            
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg(" Inside Dao impl :: Changes :: MachineId ::  "+commonFilter.getType());
		
			CommonMessage.debugMsg("condParms :: New :: "+custmrtype);
		
			List<String[]> dataList=null;
			CommonMessage.debugMsg("condParms :: New :: 1");
			if(UIUtils.isValidKeyId(type)&& ("TRADHRPT".equals(type)||"TRADHMEMBERS".equals(type))){CommonMessage.debugMsg("condParms :: New :: 2");
				if("TRADHRPT".equals(type)&& !UIUtils.isValidKeyId(custmrtype))
			        dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.ENT_FN_TRAININGADHERENCEREPORT",paramValues);
				else if("TRADHMEMBERS".equals(type)&& !UIUtils.isValidKeyId(custmrtype))
			        dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.ENT_FN_TRNMEMBERSADHERENCE",paramValues);
				
			}else if(UIUtils.isValidKeyId(custmrtype)&& "ComplaintGallery".equals(custmrtype)){CommonMessage.debugMsg("condParms :: New :: 3");
				   //dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.QTM_FN_COMPLAINTGALLERY",paramValues);
					//dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_COMPLAINTGALLERY",paramValues);
			dataList = fnCallApi.callFunction("QTM_FN_COMPLAINTGALLERY_SB",paramValues,3,true);
		
			}else if(UIUtils.isValidKeyId(custmrtype)&& "CRM".equals(custmrtype)){
				   dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			}
			else{CommonMessage.debugMsg("condParms :: New :: 6");
			       dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGPLANVSCOMP",paramValues);
			}
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
	
	
	public Workbook PlanVsCompReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat,String custmrtype) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("customer type one "+custmrtype);
			rs =   getPlanVsCompReportResultSet(commonFilter,custmrtype);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			/*List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			//condFormat.setFontName("Wingdings");
			//condFormat.setFontHeightPoint((short)24);
			
			condFormat.setFontBoldWeight((short)20);
			
			
			//condFormat.setFromCol(13);
			//condFormat.setToCol(-1);
			//condFormat.setOperator(ComparisonOperator.EQUAL);
			//condFormat.setCondValue( (char)252+""); //Tick
			//condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			
			*/	
			String type = commonFilter.getType();
			 if("ComplaintGallery".equals(custmrtype))
			{
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			}
			
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getPlanVsCompReportResultSet(CommonFilter commonFilter,String custmrtype) throws Exception
	{   
		List<String > paramValues = new ArrayList<String>();
		 String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("cutomer type "+custmrtype);
		 String type = commonFilter.getType();
		 
		 ResultSet rs=null;
		 if(UIUtils.isValidKeyId(type)){
			 
			if("ComplaintGallery".equals(type) || "ComplaintGallery".equals(custmrtype))
			{
				rs =  dbActionTemplate.NewdbFunctionCall2("QTM_FN_COMPLAINTGALLERY",paramValues);
			}
			else if("CRM".equals(type)|| "CRM".equals(custmrtype))
			{
				rs =  dbActionTemplate.NewdbFunctionCall2("QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			}
			else if("TRADHRPT".equals(type)){
				rs =   dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.ENT_FN_TRAININGADHERENCEREPORT",paramValues);
			}
			else if("TRADHMEMBERS".equals(type))
				rs =  dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.ENT_FN_TRNMEMBERSADHERENCE",paramValues);
			
		 }
		 else	
		    {
			 if("ComplaintGallery".equals(custmrtype))
				{
					rs =  dbActionTemplate.NewdbFunctionCall2("QTM_FN_COMPLAINTGALLERY",paramValues);
				}
				else if("CRM".equals(custmrtype))
				{
					rs =  dbActionTemplate.NewdbFunctionCall2("QTM_FN_CUSTOMERCOMPLAINT",paramValues);
				}
				else {
				rs = dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGPLANVSCOMP", paramValues);}
		    }
		return rs;
	}	
	
	@Override
	public List<String[]> PlanVsCompReportgraph(CommonFilter commonFilter,String keyId,String custype) throws Exception
	{
		
		try
		{
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 CommonMessage.debugMsg("INSIDE DAO IMPL" + commonFilter.getTotal());
			 if(UIUtils.isValidKeyId(keyId)){
				 condParms+="KEYID="+keyId;
				 
				 CommonMessage.debugMsg(condParms +  " if condition");
				 } 
			 if(UIUtils.isValidKeyId(commonFilter.getTotal())){
			      condParms+="TOTAL="+commonFilter.getTotal();
			 }
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList=null;
			 CommonMessage.debugMsg(" paramValues :: custype "+custype);
			 
			 
			 String type=commonFilter.getType();
			
			 if(UIUtils.isValidKeyId(type)){
				   if("TRADHRPT".equals(type))
						rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.ENT_FN_TRAININGADHERENCEREPORT",paramValues);
				   else if("TRADHMEMBERS".equals(type))
						rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.ENT_FN_TRNMEMBERSADHERENCE",paramValues);
				   
			 }else if(UIUtils.isValidKeyId(custype)){
				if("ComplaintGallery".equals(custype))
					rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_COMPLAINTGALLERY",paramValues);
				else if("CRM".equals(custype))
					rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			 }else	
					rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGPLANVSCOMP",paramValues);
			
			 //rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGPROGPLANVSCOMP", paramValues);			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> ProgCalPlanVsActReportgraph(CommonFilter commonFilter,String keyId) throws Exception
	{
		
		try
		{
			
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 CommonMessage.debugMsg("INSIDE DAO IMPL");
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			 List<String[]> rootRptList;
			 CommonMessage.debugMsg("paramValues"+paramValues);
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_PROGCALPLANVSACTUAL", paramValues);			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public Workbook ProgCalPlanVsActReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   ProgCalPlanVsCompReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet ProgCalPlanVsCompReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonMessage.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_PROGCALPLANVSACTUAL", paramValues);
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	public List<String []> getProgCalPlanVsCompCal(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams =FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("condParms"+condParms);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_PROGCALPLANVSACTUAL",paramValues);
			CommonMessage.debugMsg("Length....1111"+dataList.size());
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
	public Workbook etBefAftReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getBefAftSkillAnalysisReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 0,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getBefAftSkillAnalysisReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);	
		ResultSet rs = dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_BEFOREAFTERSKILANALYSIS", paramValues);
		return rs;
	}
	
	
}
