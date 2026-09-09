package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.ETProgCalenderRptDao;
import com.akranta.tpm.dao.NewETReportDao;
import com.akranta.tpm.dao.sql.EntTlTragcalquadSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlCriticalprocessSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.api.QuadrantAssessmentServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class NewETReportDaoImpl  implements NewETReportDao{
	private DBActionTemplate dbActionTemplate;
	
	FunctionCallApi fnCallApi;
	private QuadrantAssessmentServiceApi quadrantAssessmentServiceApi; 
	
	public NewETReportDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void NewETReportDaoImplJwt(String JwtToken) {
	    try {
	    	quadrantAssessmentServiceApi = new QuadrantAssessmentServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<String []> getPlanVsCompCal(CommonFilter commonFilter,String custmrtype) throws Exception
	{
	
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
			    //    dataList =  dbActionTemplate.processFunctionCalls("ENT_FN_TRAININGADHERENCEREPORT",paramValues);
				dataList = fnCallApi.callFunction("ENT_FN_TRAININGADHERENCEREPORT_SB",paramValues,2,true);
				else if("TRADHMEMBERS".equals(type)&& !UIUtils.isValidKeyId(custmrtype))
			     //   dataList =  dbActionTemplate.processFunctionCalls("ENT_FN_TRNMEMBERSADHERENCE",paramValues);
				dataList =  fnCallApi.callFunction("ENT_FN_TRNMEMBERSADHERENCE_SB",paramValues,2,true);
				
			}else if(UIUtils.isValidKeyId(custmrtype)&& "ComplaintGallery".equals(custmrtype)){CommonMessage.debugMsg("condParms :: New :: 3");
				   //dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.QTM_FN_COMPLAINTGALLERY",paramValues);
					dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.QTM_FN_COMPLAINTGALLERY",paramValues);
		
			}else if(UIUtils.isValidKeyId(custmrtype)&& "CRM".equals(custmrtype)){
				   dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			}
			else{
				CommonMessage.debugMsg("condParms :: New :: 6" +condParms);
			     //  dataList =  dbActionTemplate.processFunctionCalls("ENT_FN_TRAININGPROGPLANVSCOMP",paramValues);
			       dataList = fnCallApi.callFunction("ENT_FN_TRAININGPROGPLANVSCOMP_SB",paramValues,2,true);
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
	}
	
	public List<String[]> PlanVsCompReportgraph(CommonFilter commonFilter,String keyId,String custype) throws Exception
	{
		
		try
		{
			CommonMessage.debugMsg("chk grph in dao");
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
					//	rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_TRAININGADHERENCEREPORT",paramValues);
				   rootRptList =  fnCallApi.callFunction("ENT_FN_TRAININGADHERENCEREPORT_SB",paramValues,2,true);
				   else if("TRADHMEMBERS".equals(type))
					//	rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_TRNMEMBERSADHERENCE",paramValues);
				   rootRptList =  fnCallApi.callFunction("ENT_FN_TRNMEMBERSADHERENCE_SB",paramValues,2,true);
				   
			 }else if(UIUtils.isValidKeyId(custype)){
				if("ComplaintGallery".equals(custype))
					rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.QTM_FN_COMPLAINTGALLERY",paramValues);
				else if("CRM".equals(custype))
					rootRptList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			 }else	
					
					rootRptList =  fnCallApi.callFunction("ENT_FN_TRAININGPROGPLANVSCOMP_SB",paramValues,2,true);
			
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
	public Workbook PlanVsCompReportExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat,String custmrtype) throws Exception{
		  
		   ResultSet rs = null;
		   try{
			
			rs =   getPlanVsCompReportResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,rptFormat, 3,1,0 );
			}
			
			return excelUtils.writeToExcel(rs,rptFormat, 4,1,0 ); //elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getPlanVsCompReportResultSet(CommonFilter commonFilter) throws Exception
	{   
		List<String > paramValues = new ArrayList<String>();
		 String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		 
		 String type = commonFilter.getType();
		 ResultSet rs=null;
		 if(UIUtils.isValidKeyId(type)){
			 
			if("ComplaintGallery".equals(type))
			{
				rs =  dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.QTM_FN_COMPLAINTGALLERY",paramValues);
			}
			else if("CRM".equals(type))
			{
				rs =  dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.QTM_FN_CUSTOMERCOMPLAINT",paramValues);
			}
			else if("TRADHRPT".equals(type)){
				rs =   dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRAININGADHERENCEREPORT_SB",paramValues);
			}
			else if("TRADHMEMBERS".equals(type))
				rs =  dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRNMEMBERSADHERENCE_SB",paramValues);
			
		 }
		 else	
		    { 
				rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRAININGPROGPLANVSCOMP_SB", paramValues);
		    }
		return rs;
	}	
	public List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String type=commonFilter.getAbnViewType();
			
			CommonMessage.debugMsg(" Checking for type "+type);
			
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";
				
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Visual Month DAO Impl " + paramValues);
			
			List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
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
	public Workbook avgSkillGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception { 
		ResultSet rs = null;
		   try{
			
			rs =   getavgSkillResultSet(commonFilter);
			CommonMessage.debugMsg("rss=="+rs);
			
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			CommonMessage.debugMsg("colModel=="+colModel);
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
	private ResultSet getavgSkillResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getavgSkillFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
	}
	private List<String> getavgSkillFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		CommonMessage.debugMsg(" commonFilter" +commonFilter);
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg(" condPArams" + condParms);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	public List<String[]> getAllPerPerson(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Kaizen Per Person Per Month DAO Impl");
		//	String sysdate = CommonFunctions.dateTimeNow();
		//	CommonMessage.debugMsg("Inside Kaizen Per Person Per Month sysdate " + sysdate);
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Kaizen Per Person Per Month DAO Impl " + paramValues);
			
			List<String[]> impVsCompList=null;
			//-- changing sydate to current_date vignesh
			String Countsql="SELECT MWEC_empcounts  FROM ADM_TL_MNTHWSEEMPCNT where MWEC_fnln_KEYid='"+commonFilter.getFlid()+"' and MWEC_CURNTMONTH = to_char(current_date,'MON-YYYY')";
			
			
			
			CommonMessage.debugMsg(" Countsql  " + Countsql );
			
			String cnt1= dbActionTemplate.getSingleValue(Countsql);
			CommonMessage.debugMsg(" count 1 " + cnt1 );
			
			  String Countsql1="Select count(*) from  GEN_TL_FNLNROLETEAM where FRT_FNLN_KEYID='"+commonFilter.getFlid()+"'";
			  CommonMessage.debugMsg(" Countsql  1" + Countsql1 );
			  String cnt2=dbActionTemplate.getSingleValue(Countsql1);
			  CommonMessage.debugMsg("  count 2  " + cnt2 );
		//	CommonMessage.debugMsg("Countsql"+cnt1+"Countsql1"+cnt2);
		//	cnt2.equalsIgnoreCase(cnt1)
//			if(!cnt1.equals(cnt2))
//			{ //-- changing sydate to current_date vignesh
//				String update="update ADM_TL_MNTHWSEEMPCNT set MWEC_empcounts='"+cnt2+"' where MWEC_fnln_KEYid='"+commonFilter.getFlid()+"' and MWEC_CURNTMONTH =to_char(current_date,'MON-YYYY')";
//				CommonMessage.debugMsg("  update statement   " + update );
//				dbActionTemplate.executeStatement(update);
//			}
			
			  if (cnt2 != null && !cnt2.equals(cnt1)) {
				    // -- changing sydate to current_date vignesh
				    String update = "update ADM_TL_MNTHWSEEMPCNT set MWEC_empcounts='" + cnt2 + 
				        "' where MWEC_fnln_KEYid='" + commonFilter.getFlid() + 
				        "' and MWEC_CURNTMONTH = to_char(current_date,'MON-YYYY')";
				    CommonMessage.debugMsg("update statement " + update);
				    dbActionTemplate.executeStatement(update);
				}
			
			String Type=commonFilter.getAbnViewType();
			CommonMessage.debugMsg("Type====="+Type);
			if(UIUtils.isValidKeyId(Type))
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TESTPACKAGE.HSE_FN_DRILLDOWN", paramValues);
		//		impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_TRAININGPERSONMONTH", paramValues);
			impVsCompList = fnCallApi.callFunction("ENT_FN_TRAININGPERSONMONTH_SB", paramValues,3,true);
			else
				impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENPERPERSONPERMONTH", paramValues);	
			
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
	public Workbook perPersonKaizenExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
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
			String Type=commonFilter.getAbnViewType();
			if(UIUtils.isValidKeyId(Type)) 
			   return excelUtils.writeToExcel(rs,rptFormat, 3,1,0 );
			else
				return excelUtils.writeToExcel(rs,rptFormat, 2,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getIncedentVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getIncedentFilterParamValues(commonFilter);
		
		String Type=commonFilter.getAbnViewType();
		if(UIUtils.isValidKeyId(Type))
			return dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRAININGPERSONMONTH_SB", paramValues);	
		else
		    return dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENPERPERSONPERMONTH", paramValues);
	}
	
	private List<String> getIncedentFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	public List<String[]> getAllNosPerPerson(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Kaizen Per Person Per Month DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Kaizen Per Person Per Month DAO Impl " + paramValues);
			
			List<String[]> impVsCompList=null;
			
			//String Type=commonFilter.getAbnViewType();
			//CommonMessage.debugMsg("Type====="+Type);
			//if(UIUtils.isValidKeyId(Type))
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TESTPACKAGE.HSE_FN_DRILLDOWN", paramValues);
				impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_TRNGPERSONSPERMONTH", paramValues);
			
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
	public Workbook NosperPersonKaizenExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		
			ResultSet rs = null;
			   try{
				
				rs =   getIncedentVsCompleteResultSet1(commonFilter);
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
		private ResultSet getIncedentVsCompleteResultSet1(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getIncedentFilterParamValues1(commonFilter);
			
		//	String Type=commonFilter.getAbnViewType();
			
				return dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRNGPERSONSPERMONTH", paramValues);	
			}
		
		private List<String> getIncedentFilterParamValues1(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}
		@Override
		public List<String[]> getEmployeeLevel(CommonFilter commonFilter) throws Exception {
			
			CommonMessage.debugMsg("am in dao");
			List<String> paramValues = new ArrayList<String>();
			String  condParam= FilterCondSql.getETRelatedStr(commonFilter);
			String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonFilter.getAssType()   "+commonFilter.getAssType());
	    	condParam += "MODE="+commonFilter.getAssType()+";";
	    	condParam += "CHKLEVEL="+commonFilter.getType()+";";
	    	if(UIUtils.isValidKeyId(commonFilter.getRefdocid()));
	    	condParam += "UNIQPOS="+commonFilter.getRefdocid()+";";
	    	if(UIUtils.isValidKeyId(commonFilter.getTopicid() ));
	    	condParam += "TOPICID="+commonFilter.getTopicid()+";";
	    	if(UIUtils.isValidKeyId(commonFilter.getKey() ));
	    	condParam += "KEYID="+commonFilter.getKey()+";";
	    	CommonMessage.debugMsg("are"+condParam);
	    	
			paramValues.add(condParam);
			paramValues.add(commonParam);
			
		//	List<String[]> gridData = dbActionTemplate.processFunctionCalls("ENT_FN_ASSESSMENTLEVEL", paramValues);
			List<String[]> gridData = fnCallApi.callFunction("ENT_FN_ASSESSMENTLEVEL_SB", paramValues,3,true);
			CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			return gridData;

		}
		@Override
		public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception {
			List<String> paramValues = new ArrayList<String>();		
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonFilter.getType()=="+commonFilter.getType());
			condParms+="MODETYPE="+commonFilter.getType() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);                                
			
		//	List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_BATCHCOMPLETION", paramValues);
			List<String[]> dataList = fnCallApi.callFunction("ENT_FN_BATCHCOMPLETION_SB", paramValues,3,true);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		@Override
		public List<String[]> getSkillGapReportGrid(CommonFilter commonFilter) throws Exception {
			List<String> paramValues = new ArrayList<String>();	
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			

			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms +="EMPID="+commonFilter.getKey()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getUniquePos()))
				condParms +="ROLEID="+commonFilter.getUniquePos()+";";


			paramValues.add(condParms);
			paramValues.add(commonParams);
			
		//	List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_FN_SKILLGAP", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("ENT_FN_SKILLGAP_SB", paramValues,3,true);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		@Override
		public Workbook getSkillGapReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getSkillGapReport(commonFilter);
				CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		    
		}
		private ResultSet getSkillGapReport(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub

			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); 			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms +="EMPID="+commonFilter.getKey()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getUniquePos()))
				condParms +="ROLEID="+commonFilter.getUniquePos()+";";

			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.NewdbFunctionCall2("ENT_FN_SKILLGAP", paramValues);

		
		}
		@Override
		public List<String[]> FillControlData(String keyid, String type) throws Exception {
			String sql = GenTlCriticalprocessSql.selectData(keyid,type);
			CommonMessage.debugMsg("sql  "+sql);
			List<String []> gridData = dbActionTemplate.getDataList(sql);
			return gridData;
		}
		@Override
		public EntTlTragcalquad createAssmLevel(String UpdateList,String userid) throws Exception {
			
			EntTlTragcalquad entTlTragcalquad=new EntTlTragcalquad();
			String sysdate = CommonFunctions.dateTimeNow();
	     	CommonMessage.debugMsg("cjhk in dao impl"+UpdateList.length());
	    	CommonMessage.debugMsg("cjhk in dao impl"+UpdateList);
	    	String modiefiedid=UpdateList.replaceAll("\"", "'");
			String sql1=null;
			String sql = EntTlTragcalquadSql.selectData(modiefiedid);
			CommonMessage.debugMsg("sql"+sql);
			String level =  dbActionTemplate.getSingleValue(sql);
			if(Integer.parseInt(level)==2){
				
				CommonMessage.debugMsg("level 2 in dao impl");
				//sql1="update ENT_TL_TRGCALQUAD set ETCQ_CURRENTLEVEL='3',ETCQ_L3PASS='P',ETCQ_L3DATE=TO_CHAR(SYSDATE,'DD-MON-YYYY') ,ETCQ_CURRENTLEVELDATE=TO_CHAR(SYSDATE,'DD-MON-YYYY'),ETCQ_L3_UPDBY='" +userid+"'  WHERE ETCQ_KEYID IN(" + modiefiedid + ")" ;
				
				sql1="update ENT_TL_TRGCALQUAD set ETCQ_CURRENTLEVEL='3',ETCQ_L3PASS='P',ETCQ_L3DATE=TO_DATE(TO_CHAR(NOW(),'DD-MON-YYYY'), 'DD-MON-YYYY') ,ETCQ_CURRENTLEVELDATE=TO_DATE(TO_CHAR(NOW(),'DD-MON-YYYY'),'DD-MON-YYYY'),ETCQ_L3_UPDBY='" +userid+"'  WHERE ETCQ_KEYID IN(" + modiefiedid + ")" ;
			CommonMessage.debugMsg("sql1 in dao impl"+sql1);
			 dbActionTemplate.executeStatement(sql1);
			}
			if(Integer.parseInt(level)==3){
				CommonMessage.debugMsg("level 3 in dao impl");
				sql1="update ENT_TL_TRGCALQUAD set ETCQ_CURRENTLEVEL='4',ETCQ_L4PASS='P',ETCQ_L4DATE=TO_DATE(TO_CHAR(NOW(),'DD-MON-YYYY'), 'DD-MON-YYYY') ,ETCQ_CURRENTLEVELDATE=TO_DATE(TO_CHAR(NOW(),'DD-MON-YYYY'),'DD-MON-YYYY'),ETCQ_L4_UPDBY='" +userid+"' WHERE ETCQ_KEYID IN(" + modiefiedid + ")" ;
				CommonMessage.debugMsg("sql1 in dao impl"+sql1);
				dbActionTemplate.executeStatement(sql1);
			}
			return entTlTragcalquad;
		}
		@Override
		public Workbook getnomitnExportToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1)
				throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getEmpNomReportResultSet(commonFilter1);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);			
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }
			   finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}

		private ResultSet getEmpNomReportResultSet(CommonFilter commonFilter1) throws Exception {
			// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getETRelatedStr(commonFilter1);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			return dbActionTemplate.NewdbFunctionCall2("ENT_FN_NEWNOMINATIONREPORT", paramValues);
		}

		
		
		@Override
		public List<String[]> getnomitnrpt(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getETRelatedStr(commonFilter);
						
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				paramValues.add(condParms);			
				paramValues.add(commonParams);
				List<String[]> dataList =  null;
			//	dataList =  dbActionTemplate.processFunctionCalls("ENT_FN_NEWNOMINATIONREPORT", paramValues);
				dataList =  fnCallApi.callFunction("ENT_FN_NEWNOMINATIONREPORT_SB", paramValues,3,true);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
		public Workbook assemeExcel(JSONObject colmodel, String format, CommonFilter commonFilter1) throws Exception {
			// TODO Auto-generated method stub
			
				ResultSet rs = null;
				   try{		
					rs =   getAssementReport(commonFilter1);
					CommonMessage.debugMsg("rs value::::assemeExcel::::"+ rs.getConcurrency());
					ExcelUtils excelUtils = new ExcelUtils(colmodel);
					List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();			
					XLConditionalFormats condFormat = new XLConditionalFormats();			
					condFormat.setFontColor(new RGB(255,0,0)); //red font
					condFormat.setFontName("Wingdings");
					condFormat.setFontHeightPoint((short)14);
					condFormat.setFontBoldWeight((short)20);
					condFormat.setFromCol(2);
					condFormat.setToCol(colmodel.length());
					condFormat.setOperator(ComparisonOperator.EQUAL);
					condFormat.setCondValue("1"); //Tick			
					condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
					condFormats.add(condFormat);	
					
					XLConditionalFormats condFormatEmpty = new XLConditionalFormats();
					condFormatEmpty.setFontColor(new RGB(254,0,0)); //red font
					condFormatEmpty.setFontName("Wingdings");
					condFormatEmpty.setFontHeightPoint((short)14);
					condFormatEmpty.setFontBoldWeight((short)20);
					condFormatEmpty.setFromCol(2);
					condFormatEmpty.setToCol(colmodel.length());
					condFormatEmpty.setOperator(ComparisonOperator.EQUAL);
					condFormatEmpty.setCondValue("0"); //NULL		
					condFormatEmpty.setSymbolStr("");
					condFormats.add(condFormatEmpty);			
					excelUtils.setCondFormats(condFormats);
					return excelUtils.writeToExcel(rs,format,2,0,0 ); //elumalai
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }  

			}
			private ResultSet getAssementReport(CommonFilter commonFilter1) throws Exception {
				// TODO Auto-generated method stub
					List<String> paramValues = new ArrayList<String>();
					 
					String condParms =FilterCondSql.getETRelatedStr(commonFilter1);			
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					 
					CommonMessage.debugMsg("commonFilter.getAssType()   "+commonFilter1.getAssType());
					condParms += "MODE="+commonFilter1.getAssType()+";";
					condParms += "CHKLEVEL="+commonFilter1.getType()+";";
			    	if(UIUtils.isValidKeyId(commonFilter1.getRefdocid()));
			    	condParms += "UNIQPOS="+commonFilter1.getRefdocid();
					paramValues.add(condParms);
					paramValues.add(commonParams);
					return dbActionTemplate.NewdbFunctionCall2("ENT_FN_ASSESSMENTLEVEL", paramValues);
			}
			@Override
			public List<String[]> getfourquadrant(CommonFilter commonFilter) throws Exception {
				// TODO Auto-generated method stub
				try
				{
					CommonMessage.debugMsg("Inside daoimpl");
					List<String> paramValues = new ArrayList<String>();	
					CommonMessage.debugMsg("before ");
					String CellId=commonFilter.getCellch();
					CommonMessage.debugMsg("CellID Dao"+CellId);
					CommonMessage.debugMsg("CellID Dao"+CellId.length());
					
					String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
					
					paramValues.add(condParms);
					paramValues.add(commonParams);
					List<String[]> dataList =  null;			
					CommonMessage.debugMsg("ParamValues are:"+paramValues);	
				//	//dbActionTemplate.processFunctionCalls("TEST4_PC_TEST4.ADM_FN_ACCREVRPT", paramValues);
					//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("TEST4_PC_TEST4.ADM_FN_ACCREVRPT", paramValues);
					if(CellId.length()>0){
						CommonMessage.debugMsg("IF");
						condParms += "CELLID="+commonFilter.getCellch()+";";
			//		dataList = dbActionTemplate.processFunctionCalls("ENT_FN_FOURQUDRANTRPTNEW", paramValues);
					dataList = fnCallApi.callFunction("ENT_FN_FOURQUDRANTRPTNEW_SB", paramValues,1,true);
					}
					else{
						
						CommonMessage.debugMsg("ELSE");
					//	dataList = dbActionTemplate.processFunctionCalls("ENT_FN_FOURQUDRANTRPT", paramValues);	
						dataList = fnCallApi.callFunction("ENT_FN_FOURQUDRANTRPT_SB", paramValues,1,true);
					}
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
						CommonMessage.debugMsg("total count is"+totalCnt);
						CommonMessage.debugMsg("totalCnt...."+totalCnt);
						boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
						if(  isInteger ){
							commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
						}
					}
					CommonMessage.debugMsg("data is:::"+dataList);
					return dataList; 
					
				}
				catch (Exception e)
				{
					throw new Exception(e.getMessage()); 
				}
		
				
			
			}
			@Override
			public String getfunctionaldata(CommonFilter commonFilter) throws Exception {
				// TODO Auto-generated method stub
				String result=null;
				
				String sql="select count(*) from ENT_TL_TRGCALMST WHERE ETCM_FLID='"+commonFilter.getFlid()+"' AND TO_CHAR(ETCM_CALDATE) BETWEEN to_date('"+commonFilter.getFromDate()+"') and TO_DATE('"+commonFilter.getToDate()+"','MON-YYYY')";
				String dataListing=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALMST WHERE ETCM_FLID='"+commonFilter.getFlid()+"' AND TO_CHAR(ETCM_CALDATE) BETWEEN to_date('"+commonFilter.getFromDate()+"','MON-YYYY') and TO_DATE('"+commonFilter.getToDate()+"','MON-YYYY')");
				
				CommonMessage.debugMsg("datlisting is"+dataListing);
				return dataListing;
			
			}
			@Override
			public Workbook FourExcelReport(CommonFilter commonFilter,JSONObject colmodel, String format,
					String imagepath) throws Exception {
				ResultSet rs = null;
				try {
					rs = getEntFourQuarExcel(commonFilter);
					ExcelUtils excelUtils = new ExcelUtils(colmodel);
					List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
					XLConditionalFormats statusBooked = new XLConditionalFormats();
					statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
					statusBooked.setSymbolStr("-");
					statusBooked.setFromCol(2);
					statusBooked.setToCol(-1);
					statusBooked.setOperator(ComparisonOperator.EQUAL);
					statusBooked.setCondValue("0");
					statusBooked.setIdentfier("Quadrant0");
					statusBooked.setImgPathName(imagepath + "images/green0.jpg");
					com.akranta.tpm.utils.CommonMessage.debugMsg(statusBooked.getImgPathName()
							+ " image nmae");
					condFormats.add(statusBooked);

					XLConditionalFormats statusBooked2 = new XLConditionalFormats();
					statusBooked2.setFontName(XLConditionalFormats.FONT_DEFAULT);
					statusBooked2.setFromCol(2);
					statusBooked2.setToCol(-1);
					statusBooked2.setSymbolStr("-");
					statusBooked2.setOperator(ComparisonOperator.EQUAL);
					statusBooked2.setCondValue("1");
					statusBooked2.setIdentfier("Quadrant1");
					statusBooked2.setImgPathName(imagepath + "images/Green1.jpg");
					condFormats.add(statusBooked2);

					XLConditionalFormats statusBooked3 = new XLConditionalFormats();
					statusBooked3.setFontName(XLConditionalFormats.FONT_DEFAULT);
					statusBooked3.setFromCol(2);
					statusBooked3.setToCol(-1);
					statusBooked3.setSymbolStr("-");
					statusBooked3.setOperator(ComparisonOperator.EQUAL);
					statusBooked3.setCondValue("2");
					statusBooked3.setIdentfier("Quadrant2");
					statusBooked3.setImgPathName(imagepath + "images/green2.jpg");
					condFormats.add(statusBooked3);

					XLConditionalFormats statusBooked4 = new XLConditionalFormats();
					statusBooked4.setFontName(XLConditionalFormats.FONT_DEFAULT);
					statusBooked4.setFromCol(2);
					statusBooked4.setToCol(-1);
					statusBooked4.setSymbolStr("-");
					statusBooked4.setOperator(ComparisonOperator.EQUAL);
					statusBooked4.setCondValue("3");
					statusBooked4.setIdentfier("Quadrant3");
					statusBooked4.setImgPathName(imagepath + "images/green-3.jpg");
					condFormats.add(statusBooked4);

					XLConditionalFormats statusBooked5 = new XLConditionalFormats();
					statusBooked5.setFontName(XLConditionalFormats.FONT_DEFAULT);
					statusBooked5.setFromCol(2);
					statusBooked5.setToCol(-1);
					statusBooked5.setOperator(ComparisonOperator.EQUAL);
					statusBooked5.setCondValue("4");
					statusBooked5.setSymbolStr("-");
					statusBooked5.setIdentfier("Quadrant4");
					statusBooked5.setImgPathName(imagepath + "images/green4.jpg");
					condFormats.add(statusBooked5);
					excelUtils.setCondFormats(condFormats);

					return excelUtils.writeToExcel(rs, format,2, 0, 0);

				} finally {

					DBActionTemplate.closeConnection(rs, null, null, null, rs
							.getStatement().getConnection());
				}
			}

			/*private ResultSet getEntFourQuarExcel(CommonFilter commonFilter) throws Exception {
				List<String> paramValues = getFilterParamValue(commonFilter);
				return dbActionTemplate.NewdbFunctionCall2("ENT_FN_FOURQUDRANTRPT", paramValues);
			}*/
			
			private ResultSet getEntFourQuarExcel(CommonFilter commonFilter) throws Exception {
			    
			    CommonMessage.debugMsg("Inside getEntFourQuarExcel()");

			    // Prepare parameters exactly same as grid logic
			    List<String> paramValues = new ArrayList<>();

			    String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			    String cellId = commonFilter.getCellch();

			    CommonMessage.debugMsg("CellID = " + cellId);

			    // Append parameters
			    paramValues.add(condParms);
			    paramValues.add(commonParams);

			    String functionName = "";

			    // Apply same IF logic as the grid method
			    if (cellId != null && cellId.trim().length() > 0) {
			        CommonMessage.debugMsg("IF → Calling ENT_FN_FOURQUDRANTRPTNEW");
			        
			        // Add CELLID in condParms like your grid code does
			        condParms += "CELLID=" + cellId + ";";

			        // update parameter index because condParms changed
			        paramValues.set(0, condParms);

			        functionName = "ENT_FN_FOURQUDRANTRPTNEW";
			    } 
			    else {
			        CommonMessage.debugMsg("ELSE → Calling ENT_FN_FOURQUDRANTRPT");
			        functionName = "ENT_FN_FOURQUDRANTRPT";
			    }

			    CommonMessage.debugMsg("Executing Function : " + functionName);
			    CommonMessage.debugMsg("ParamValues : " + paramValues);

			    // Call database function
			    return dbActionTemplate.NewdbFunctionCall2(functionName, paramValues);
			}

			
			private List<String> getFilterParamValue(CommonFilter commonFilter) {
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				paramValues.add(condParms);
				paramValues.add(commonParams);
				return paramValues;
			   }	
			@Override
			public List<String[]> getTrainingSummRpt(CommonFilter commonFilter) throws Exception {
				// TODO Auto-generated method stub
				try
				{
					List<String> paramValues = new ArrayList<String>();
					String condParms = FilterCondSql.getETRelatedStr(commonFilter);
							
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					paramValues.add(condParms);			
					paramValues.add(commonParams);
					List<String[]> dataList =  null;
				//	dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_TRAININGSUMMRPT",paramValues);
					dataList =  fnCallApi.callFunction("GEN_FN_TRAININGSUMMRPT_SB",paramValues,3,false);
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
						CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
			public Workbook getTrainingSummaryExcel(JSONObject colmodel, String rptformat,
					CommonFilter commonFilter) throws Exception {
				  ResultSet rs = null;
				// TODO Auto-generated method stub
				 try{
                  	 rs =   getTrainingSummaryResultSet(commonFilter); 
					 ExcelUtils excelUtils = new ExcelUtils(colmodel);
						return excelUtils.writeToExcel(rs,rptformat,2,0,0 );
						
					   }finally{
						   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					   }
			}
			private ResultSet getTrainingSummaryResultSet(CommonFilter commonFilter) throws Exception {
				List<String> paramValues = getFilterParamValues1(commonFilter);
				return dbActionTemplate.NewdbFunctionCall2("GEN_FN_TRAININGSUMMRPT", paramValues);
			}
			private List<String> getFilterParamValues1(CommonFilter commonFilter) {
				List <String>  paramvalues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				paramvalues.add(condParms);
				paramvalues.add(commonParams);
				return paramvalues;
			}
	
			
			}