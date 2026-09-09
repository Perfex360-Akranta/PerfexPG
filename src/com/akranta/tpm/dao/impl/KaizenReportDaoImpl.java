package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KaizenReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KaizenReportSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class KaizenReportDaoImpl implements KaizenReportDao {
	
	
	//private static final String SELECT = null;
	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	
	public KaizenReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void KaizenReportDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
/*	private List<ComboBox> getComboValues(String sql, Object[]condArgs  ) throws Exception
	{
		ResultSet rs = null;
		try{
			rs = dbActionTemplate.getData(sql, condArgs) ;
			
			CommonMessage.debugMsg("sql " + sql);
			List<ComboBox> companyList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				companyList.add(compComb);
			}
			return companyList;
		}finally{
			if( rs != null)
				rs.close();
			rs = null;
			this.dbActionTemplate.closeConnection();
		}
	}
*/	
	public List<String []> getKaizenReport() throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			
			
			
			CommonMessage.debugMsg("paramValues "+paramValues);
			
			String sql = KaizenReportSql.getKaizenReportModificationSql();
			
			List<String[]> kaizenReport = dbActionTemplate.getDataList(sql);
			
			
			return kaizenReport;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getJHKaizenMonthwise(CommonFilter commonFilter,String deptWise,String rptNmae) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("commonFilter.getCell():"+commonFilter.getCell());
			List<String> paramValues = new ArrayList<String>();
			String condParams=FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams=FilterCondSql.getGridCommonParams(commonFilter);
			if(UIUtils.isValidKeyId(deptWise))
				condParams+="DEPTWISE="+deptWise+";";
			CommonMessage.debugMsg("condParams:"+condParams);
			paramValues.add(condParams);
			paramValues.add(commonParams);
			List<String[]> topFailList=null;
			if(rptNmae.equals("Monthwise"))
			topFailList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_PC_KAIZEN.JH_FN_KAIZENMONTHWISE", paramValues);
			else if(rptNmae.equals("Categorywise"))
			topFailList = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.JH_FN_KAIZENCATEGORYWISE", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
		  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return topFailList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook jhKaizenMonwiseExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format, String deptWise,String rptName)
			throws Exception {
		 ResultSet rs = null;
		 ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		 rs =   getdownTimeReportResultSet(commonFilter,deptWise,rptName);
		 return excelUtils.writeToExcel(rs,format, 1,0,0 );
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter,String deptWise,String rptName) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter,deptWise);
		if(rptName.equals("Monthwise"))
		return dbActionTemplate.dbFunctionCall("JHN_PC_KAIZEN.JH_FN_KAIZENMONTHWISE", paramValues);
		else if(rptName.equals("Categorywise"))
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.JH_FN_KAIZENCATEGORYWISE", paramValues);
		else
		return null;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter,String deptWise){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if(UIUtils.isValidKeyId(deptWise))
			condParms+="DEPTWISE="+deptWise+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	
//kaizen per persons
	
	
	@Override
	public List<String[]> getAllKaizenPerPerson(CommonFilter commonFilter) throws Exception {
		List<String[]> impVsCompList=null;
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Kaizen Per Person Per Month DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Kaizen Per Person Per Month DAO Impl " + paramValues);
			
			
			//changes by sriram 
			String Countsql="SELECT MWEC_empcounts  FROM ADM_TL_MNTHWSEEMPCNT where MWEC_fnln_KEYid='"+commonFilter.getFlid()+"' and MWEC_CURNTMONTH =to_char(NOW(),'MON-YYYY')";
			  String cnt1=dbActionTemplate.getSingleValue(Countsql);
			
			String Countsql1="Select count(*) from  GEN_TL_FNLNROLETEAM where FRT_FNLN_KEYID='"+commonFilter.getFlid()+"'";
		
			CommonMessage.debugMsg( Countsql1 +"Countsql1");
			String cnt2=dbActionTemplate.getSingleValue(Countsql1);
		CommonMessage.debugMsg("1");
			//added 2 if condition by sriram 
			if (cnt1 == null) cnt1 = "0";
			if (cnt2 == null) cnt2 = "0";

		
			if(!cnt1.equals(cnt2))
			{
				CommonMessage.debugMsg("2");
				String update="update ADM_TL_MNTHWSEEMPCNT set MWEC_empcounts='"+cnt2+"' where MWEC_fnln_KEYid='"+commonFilter.getFlid()+"' and MWEC_CURNTMONTH =to_char(NOW(),'MON-YYYY')";
				dbActionTemplate.executeStatement(update);
			}
			CommonMessage.debugMsg("2");
			String Type=commonFilter.getAbnViewType();
			CommonMessage.debugMsg("Type====="+Type);
			
			if(UIUtils.isValidKeyId(Type))
			{
				CommonMessage.debugMsg("2");
				
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TESTPACKAGE.HSE_FN_DRILLDOWN", paramValues);
				impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.ENT_FN_TRAININGPERSONMONTH", paramValues);
			}
			else
			{
				CommonMessage.debugMsg("3");
				//impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENPERPERSONPERMONTH", paramValues);	
				impVsCompList = fnCallApi.callFunction("KZN_FN_KAIZENPERPERSONPERMONTH_SB",paramValues,3,false);
			}
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
			e.printStackTrace();
			//throw new Exception(e.getMessage()); 
		}
		return impVsCompList;	
	}
	
	public List<String[]> getAllKaizenNosPerPerson(CommonFilter commonFilter) throws Exception {
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
				impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST3.ENT_FN_TRNGPERSONSPERMONTH", paramValues);
			
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getIncedentVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getIncedentFilterParamValues(commonFilter);
		
		String Type=commonFilter.getAbnViewType();
		if(UIUtils.isValidKeyId(Type))
			return dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.ENT_FN_TRAININGPERSONMONTH", paramValues);	
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
	
	@Override
	public List<String[]> getKaizenSummaryGridData(CommonFilter commonFilter)
			throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg("The condParms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

	//		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_KAIZEN_SUMMARY_REPORT",	paramValues);


			List<String[]> dataList = fnCallApi.callFunction("GEN_FN_KAIZEN_SUMMARY_REPORT_SB",paramValues,3,true);//elumalai

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getKaizenSummaryGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_KAIZEN_SUMMARY_REPORT_SB", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,3,1,0 );//elumalai
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}


	@Override
	public List<String[]> getKaizenSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
            CommonMessage.debugMsg("The condParms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

		//	List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_KAIZEN_SUGGESTION",	paramValues);


			List<String[]> dataList = fnCallApi.callFunction("GEN_FN_KAIZEN_SUGGESTION_SB",	paramValues,3,true);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getKaizenSuggestionSummaryGridDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_KAIZEN_SUGGESTION", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,1,0 ); //elumalai
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}
	
	
	@Override
	public List<String[]> getKaizenSynopsisData(CommonFilter commonFilter)
			throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg("The condParms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			CommonMessage.debugMsg("The commonParams"+commonParams);

			//List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_KAIZEN_Synopsis",	paramValues);
			List<String[]> dataList = fnCallApi.callFunction("GEN_FN_KAIZEN_Synopsis_sb",	paramValues,3,true);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getKaizenSynopsisDataExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_KAIZEN_Synopsis", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,1,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}
	
	public List<String[]> getSuggVsKaizenData(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  Suggestion Acc Vs Kaizen Imp DAO Impl");
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
			
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_SUGGACCVSKZNIMP", paramValues);
			
			List<String[]> impVsCompList = fnCallApi.callFunction("JHN_FN_SUGGACCVSKZNIMP_SB", paramValues,3,false);
			
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
	public Workbook getSuggestionAccKaizenExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHN_FN_SUGGACCVSKZNIMP_SB", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,3,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}
	
	@Override
	public List<String[]> getKaizenBenefitData(CommonFilter commonFilter)
			throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

			List<String[]> dataList = fnCallApi.callFunction("KZN_FN_KAIZENBENEFITTREND_SB",paramValues,3,false);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<String[]> KaizenBenefitListGraph(CommonFilter commonFilter,String rowid)
			throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENBENEFITTREND",	paramValues);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public Workbook getKaizenBenefitExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENBENEFITTREND", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}
	
	
}

