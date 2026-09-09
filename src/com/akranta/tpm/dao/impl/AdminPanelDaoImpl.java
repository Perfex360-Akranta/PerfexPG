package com.akranta.tpm.dao.impl;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.formula.functions.Finance;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdminPanelDao;
import com.akranta.tpm.dao.DashboardDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.DashboardSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemstUpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class AdminPanelDaoImpl implements AdminPanelDao {
	private DBActionTemplate dbActionTemplate;
	
	public AdminPanelDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate =dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getDashboardPillars() throws Exception{
		
		return dbActionTemplate.getDataList(DashboardSqls.getDashboardPillars());
		
	}
	
	public List<DashboardDispbean> getDashboardRptDetails(String pillarCode,String userid) throws Exception{
		DashboardDispbean dashboardDispbean = new DashboardDispbean();
		
		return (List<DashboardDispbean>) dbActionTemplate.getDataList(DashboardSqls.getDashboardRptDetailsSql(pillarCode,userid),dashboardDispbean);
	}

	
	public List<String []> getJhAuditRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String maintype=commonFilter.getMaintMode();
			
			String Flid=commonFilter.getFlid();
			
			CommonFunctions.debugMsg(" maintype Dao Impl :: "+maintype);
			
			if(UIUtils.isValidKeyId(commonFilter.getMaintMode())){
				condParms +="MAINTYPE="+commonFilter.getMaintMode()+";";
			}
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid())){
				condParms +="FLID="+commonFilter.getMaintMode()+";";
			}
			if(UIUtils.isValidKeyId(commonFilter.getAbnIsHSE())){
					condParms +="QUARDERFIRST="+commonFilter.getAbnIsHSE()+";";
			}
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType())){
					condParms +="QUARDEREND="+commonFilter.getAbnViewType()+";";
		    }
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList = null;
			
			
			if(UIUtils.isValidKeyId(commonFilter.getType())|| UIUtils.isValidKeyId(commonFilter.getMaintMode())){
				System.out.println("Inside Dao Impl If");
				dataList =  dbActionTemplate.processFunctionCalls("ADM_PC_NEWDASHBOARD.GEN_FN_JHAUDITACTIONPLANSCORE", paramValues);
			}else{
			     dataList =  dbActionTemplate.processFunctionCalls("JHN_PC_AUDIT.JHN_FN_JHAUDIT", paramValues);
			}
			
			CommonFunctions.debugMsg("Length...."+dataList.size());
			
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
	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getJhAuditActionReport(commonFilter);
			System.out.println("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);

	        
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	}

	private ResultSet getJhAuditActionReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";"; 
		
		
        String maintype=commonFilter.getMaintMode();
		
		CommonFunctions.debugMsg(" maintype Dao Impl :: "+maintype);
		
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode())){
			condParms +="MAINTYPE="+commonFilter.getMaintMode()+";EXCELIDEN=EXCEL";
	     }
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST1.GEN_FN_JHAUDITACTIONPLANSCORE", paramValues);
		
}
	
	@Override
	public List<String[]> getAttendancemonthwise(CommonFilter commonFilter,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dataList=null;
		System.out.println(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		System.out.println(" Checking 1 ");
		//String pillarId=commonFilter.getPillarWise();
		//if(!UIUtils.isValidKeyId(flid))
			//flid="";
		
		System.out.println(" Checking for Momtype"+commonFilter.getMaintMode());
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
			condParms+="DMTORIGINALID="+commonFilter.getAbnDetect()+";";
	 	
		if(UIUtils.isValidKeyId(commonFilter.getAbnormalityType()))
			   condParms+="FYEARFIRST="+commonFilter.getAbnormalityType()+";";
		if(UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
			   condParms+="FYEAREND="+commonFilter.getAbnAllch()+";";
				
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		System.out.println(" Checking 2 ");
		
		if(commonFilter.getMaintMode().equals("J")){
			System.out.println("Inside JH");
			
		    dataList= dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_NEWDASHBOARD.GEN_FN_ATTDNCMONTHWISERPTNEW", paramValues);
		}
		else{
			System.out.println("Inside DMT");
		    dataList= dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_NEWDASHBOARD.GEN_FN_ATTDNCMONTHWISERPTNEW1", paramValues);
		}
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		System.out.println(" Checking 3 ");
		return dataList;

	}
	@Override
	public Workbook MomeetingMonthwiseExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		   ResultSet rs = null;
		   try{
			
			rs =   getMomeetingReportMonthwiseResultSet(commonFilter);
			System.out.println("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getMomeetingReportMonthwiseResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("ADM_PC_NEWDASHBOARD.GEN_FN_ATTDNCMONTHWISERPTNEW", paramValues);
	}
	public List<String []> getAbnCumulative(CommonFilter commonFilter)  throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			


            if(UIUtils.isValidKeyId(commonFilter.getFlid()))
				 condParms+="FLID="+commonFilter.getFlid()+";";
			condParms+="FROMMONTH="+commonFilter.getFromMonth()+";";
			condParms+="TOMONTH="+commonFilter.getToMonth()+";";
			condParms+="FROMN="+commonFilter.getStartDate()+";";
			condParms+="TOMN="+commonFilter.getEndDate()+";";
			System.out.println("TOMONTH"+paramValues);
			
		//	System.out.println("TOMONTH"+paramValues);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//ABN_PC_ABNORMALITY.ABN_FN_ABNCUMULATIVERPT
			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_NEWDASHBOARD.ABN_FN_ABNCUMULATIVERPT", paramValues);
		  //dataList =  dbActionTemplate.processFunctionCalls("ADM_PC_NEWDASHBOARD.ADM_FN_KAIZENSTATUS", paramValues);
			
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
	public List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		 	
				if(UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
					   condParms+="FYEARFIRST="+commonFilter.getAbnDetect()+";";
				if(UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
					   condParms+="FYEAREND="+commonFilter.getAbnAllch()+";";
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					 condParms+="FLID="+commonFilter.getFlid()+";";
		     
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("BEFORE FUNC CALL");
		//
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ADM_PC_NEWDASHBOARD.ADM_FN_KAIZENSTATUS", paramValues);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACCRPT", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
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
	
	public Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getModeWiseAccExportExcel(commonFilter);
			CommonFunctions.debugMsg(rs);
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
	private ResultSet getModeWiseAccExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		System.out.println("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("ADM_PC_NEWDASHBOARD.ADM_FN_KAIZENSTATUS", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
	

	

	@Override
	public List<String[]> getEHSMetricsCountData(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		//List<String> paramValues = getFilterParamValues(commonFilter);
	 	List<String[]> dataList =  null;
	 	List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";

		if(UIUtils.isValidKeyId(commonFilter.getWostatus()))
			   condParms+="FYEARFIRST="+commonFilter.getWostatus()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getWrkEndTo()))
			   condParms+="FYEAREND="+commonFilter.getWrkEndTo()+";";
		if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			 condParms+="FLID="+commonFilter.getFlid()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
	 	String Types="SAFE";
	 	System.out.println("The Types"+Types);
	 	
	 	
	 	if(Types.equals("SAFE")){
	 		dataList = dbActionTemplate.processFunctionCalls("ADM_PC_NEWDASHBOARD.GEN_FN_EHSMETRICS", paramValues);
	 	}
	 	
	 	
	 	if( commonFilter.getViewClick() == 'Y'){
	 		String totalCnt = paramValues.get(0); 

	 		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	 		if(  isInteger ){
	 			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	 		}
	 	}
	 	return dataList;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		 List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}

	@Override
	public List<String[]> getTransactionSummaryGridData(
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			System.out.println("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			System.out.println("Inside daoimpl");
			
			String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			if(UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
				   condParms+="FYEARFIRST="+commonFilter.getAbnDetect()+";";
			if(UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
				   condParms+="FYEAREND="+commonFilter.getAbnAllch()+";";
			System.out.println("Month"+commonFilter.getAbnAllch());
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
				 condParms+="FLID="+commonFilter.getFlid()+";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("Inside daoimpl 4");
						
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_NEWDASHBOARD.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
			
			System.out.println("Inside daoimpl 5: "+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}			}
			CommonFunctions.debugMsg("Test --->" +dataList.size());
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getTransactionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {

			 List<String> paramValues = new ArrayList<String>();				
			 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
			 paramValues.add(condParms);	 
			 paramValues.add(commonParams);
			 CommonFunctions.debugMsg("U are in Dao------------");
			 ResultSet rs = null;
			 try{
			 	rs=dbActionTemplate.dbFunctionCall("ADM_PC_NEWDASHBOARD.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
			 	//dbActionTemplate.processFunctionCallsWithColHeaders("GEN_ROLEVIEW_TEST.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
			 	CommonFunctions.debugMsg("Result set from DAO Impl = "+rs);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
			 		  
			 }finally{
				   if( rs != null)
			 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				     
			 }
			
			
		
	}

	@Override
	public List<String[]> getAllAbnormalitySumm(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";

             if(UIUtils.isValidKeyId(commonFilter.getAbnormalityType()))
				   condParms+="QUARDERFIRST="+commonFilter.getAbnormalityType()+";";
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
				   condParms+="QUARDEREND="+commonFilter.getAbnViewType()+";";
			System.out.println("QUARDEREND"+commonFilter.getAbnViewType());
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("BEFORE FUNC Abnormality CALL");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ADM_PC_NEWDASHBOARD.HSE_FN_ABNORMALITY", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
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
	public String getDmtFlid(String Flid)throws Exception{
		    String sql="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+Flid+"' ";
			System.out.println("Sql:"+sql);
			return dbActionTemplate.getSingleValue(sql);
		
	}

	@Override
	public List<String[]> getAttendancemonthwiseNewReport(
			CommonFilter commonFilter, String flid) throws Exception {
		System.out.println(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		String Finance=commonFilter.getFirstLevel();
		System.out.println("Finance:::"+Finance);
		
		if(Finance.equals("Y")){
			           System.out.println("yEAR eND"+commonFilter.getAbnAllch());
						if(UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
							   condParms+="FYEARFIRST="+commonFilter.getAbnDetect()+";";
						if(UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
							   condParms+="FYEAREND="+commonFilter.getAbnAllch()+";";
						if(UIUtils.isValidKeyId(commonFilter.getFlid()))
							 condParms+="FLID="+commonFilter.getFlid()+";";
						CommonFunctions.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
						if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
							   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
						if(UIUtils.isValidKeyId(commonFilter.getAbnDetectBy()))
							condParms+="DMTORIGINALID="+commonFilter.getAbnDetectBy()+";";
						if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
							condParms+="PILLARID="+commonFilter.getPillarWise()+";";
						if(UIUtils.isValidKeyId(commonFilter.getFirstLevel()))
							condParms+="FINANCE="+commonFilter.getFirstLevel()+";";	
		}
		else{
			    System.out.println("Else Called");
				if(UIUtils.isValidKeyId(commonFilter.getAbnImp()))
					   condParms+="FMONTH="+commonFilter.getAbnImp()+";";
				if(UIUtils.isValidKeyId(commonFilter.getAbnIsHSE()))
					   condParms+="TMONTH="+commonFilter.getAbnIsHSE()+";";
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					 condParms+="FLID="+commonFilter.getFlid()+";";
				if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
					   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
				if(UIUtils.isValidKeyId(commonFilter.getAbnDetectBy()))
					condParms+="DMTORIGINALID="+commonFilter.getAbnDetectBy()+";";
				if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
					condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		System.out.println(" Checking 2 ");
		
		List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_NEWDASHBOARD.GEN_FN_MOMMNTHRPT", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		System.out.println(" Checking 3 ");
		return dataList;
	}

	@Override
	public List<String[]> PendingAbnlist(CommonFilter commonFilter,
			GridParams gridParams,String Finance) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer();
		String FromDate=commonFilter.getAbnDetect();
		String ToDate=commonFilter.getAbnAllch();
		String Flid=commonFilter.getFlid();
		String Type=commonFilter.getType();
		System.out.println("Type Is:::"+Type);
		System.out.println("Finance:"+Finance);
		
		if(Finance.equals("Y")){
			if(UIUtils.isValidKeyId(Type)){
				System.out.println("Inside Type");
				sql.append("select ABNM_FLID,DMT,JH,sum(day0),sum(day1),sum(day2),sum(day3) from ( SELECT ABNM_FLID ,DMT,JH,");
				sql.append("TO_CHAR(CASE  WHEN (ElapsedDays <30 ) THEN 1 ELSE 0 END)day0,TO_CHAR(CASE  WHEN (ElapsedDays BETWEEN 30  AND 60) THEN 1 ELSE 0 END)Day1, ");
				sql.append("TO_CHAR( CASE WHEN (ElapsedDays BETWEEN 60 AND 90) THEN 1 ELSE 0 END)Day2,"); 
				sql.append("TO_CHAR (CASE WHEN (ElapsedDays > 90) THEN 1 ELSE 0 END) Day3 "); 
				sql.append("FROM (  SELECT  abnm_flid,DMT,JH,ABNM_DETECTIONDATE,abnm_keyid,ABTM_NAME,"); 
				sql.append(" ROUND (SYSDATE - TO_DATE (ABNM_DETECTIONDATE))AS ElapsedDays ");
				sql.append(" FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
				sql.append("WHERE ABNM_STATUS='P' AND ABNM_TYPEID = ABTM_KEYID  ");
				sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'");
				sql.append("AND INSTR (PARENTFLIDS || FLID, '"+Flid+"') > 0 AND ABNM_FLID = FLID(+)  GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,JH,DMT,ABNM_KEYID ");
				sql.append(" ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid, DMT,JH");
				
			    System.out.println("Finace SQL in IF is::::"+sql);
			}
			else{
				sql.append("select ABNM_FLID,ABTM_NAME,sum(day0),sum(day1),sum(day2),sum(day3) from ( SELECT ABNM_FLID ,");
				sql.append("ABTM_NAME,ABTM_KEYID,TO_CHAR(CASE  WHEN (ElapsedDays <30 ) THEN 1 ELSE 0 END)day0,TO_CHAR(CASE  WHEN (ElapsedDays BETWEEN 30  AND 60) THEN 1 ELSE 0 END)Day1, ");
				sql.append("TO_CHAR( CASE WHEN (ElapsedDays BETWEEN 60 AND 90) THEN 1 ELSE 0 END)Day2,"); 
				sql.append("TO_CHAR (CASE WHEN (ElapsedDays > 90) THEN 1 ELSE 0 END) Day3 "); 
				sql.append("FROM (  Select abnm_flid,ABNM_DETECTIONDATE,abnm_keyid,ABTM_NAME,ABTM_KEYID"); 
				sql.append(" ,ROUND (SYSDATE - TO_DATE (ABNM_DETECTIONDATE))AS ElapsedDays ");
				sql.append(" FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
				sql.append("WHERE ABNM_STATUS='P' AND ABNM_TYPEID = ABTM_KEYID ");
				sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
				sql.append("AND INSTR (PARENTFLIDS || FLID, '"+Flid+"') > 0 AND ABNM_FLID = FLID(+) GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,ABTM_KEYID,ABNM_KEYID ");
				sql.append(" ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid,abtm_name,ABTM_KEYID ORDER BY ABTM_KEYID");
				
			    System.out.println("Finace SQL in Else is::::"+sql);
			}
			
			
		}
		else{
			 System.out.println("Else");
			if(UIUtils.isValidKeyId(Type)){
				sql.append("select ABNM_FLID,DMT,JH,sum(day0),sum(day1),sum(day2),sum(day3) from ( SELECT ABNM_FLID ,DMT,JH,");
				sql.append("TO_CHAR(CASE  WHEN (ElapsedDays <30 ) THEN 1 ELSE 0 END)day0,TO_CHAR(CASE  WHEN (ElapsedDays BETWEEN 30  AND 60) THEN 1 ELSE 0 END)Day1, ");
				sql.append("TO_CHAR( CASE WHEN (ElapsedDays BETWEEN 60 AND 90) THEN 1 ELSE 0 END)Day2,"); 
				sql.append("TO_CHAR (CASE WHEN (ElapsedDays > 90) THEN 1 ELSE 0 END) Day3 "); 
				sql.append("FROM (  SELECT  abnm_flid,DMT,JH,ABNM_DETECTIONDATE,abnm_keyid,ABTM_NAME,"); 
				sql.append(" ROUND (SYSDATE - TO_DATE (ABNM_DETECTIONDATE))AS ElapsedDays ");
				sql.append(" FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
				sql.append("WHERE ABNM_STATUS='P' AND ABNM_TYPEID = ABTM_KEYID  ");
				sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'");
				sql.append("AND INSTR (PARENTFLIDS || FLID, '"+Flid+"') > 0 AND ABNM_FLID = FLID(+)  GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,JH,DMT,ABNM_KEYID ");
				sql.append(" ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid, DMT,JH");
			    System.out.println("SQL in IF is::::"+sql);
			}
			else{
				sql.append("select ABNM_FLID,ABTM_NAME,sum(day0),sum(day1),sum(day2),sum(day3) from ( SELECT ABNM_FLID ,");
				sql.append("ABTM_NAME,ABTM_KEYID,TO_CHAR(CASE  WHEN (ElapsedDays <30 ) THEN 1 ELSE 0 END)day0,TO_CHAR(CASE  WHEN (ElapsedDays BETWEEN 30  AND 60) THEN 1 ELSE 0 END)Day1, ");
				sql.append("TO_CHAR( CASE WHEN (ElapsedDays BETWEEN 60 AND 90) THEN 1 ELSE 0 END)Day2,"); 
				sql.append("TO_CHAR (CASE WHEN (ElapsedDays > 90) THEN 1 ELSE 0 END) Day3 "); 
				sql.append("FROM (  Select abnm_flid,ABNM_DETECTIONDATE,abnm_keyid,ABTM_NAME,ABTM_KEYID"); 
				sql.append(" ,ROUND (SYSDATE - TO_DATE (ABNM_DETECTIONDATE))AS ElapsedDays ");
				sql.append(" FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
				sql.append("WHERE ABNM_STATUS='P' AND ABNM_TYPEID = ABTM_KEYID ");
				sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
				sql.append("AND INSTR (PARENTFLIDS || FLID, '"+Flid+"') > 0 AND ABNM_FLID = FLID(+) GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,ABTM_KEYID,ABNM_KEYID ");
				sql.append(" ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid,abtm_name,ABTM_KEYID ORDER BY ABTM_KEYID");
			    System.out.println(" SQL in Else is::::"+sql);
			}

		}
		
		
		
		
		
		List<String> paramValues = new ArrayList<String>();
			
		String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(conditionalparam);
	    paramValues.add(commonparam);
	    String countSql=CommonFilterSqls.countSql(sql.toString(),gridParams.getGridFilters());
		String viewinfo=dbActionTemplate.getSingleValue(countSql);
	    long counts=Long.parseLong(viewinfo);
	    if( counts >0){
	    	String sb = CommonFilterSqls.addPaginationParams(sql.toString(),gridParams);
	    	gridParams.setTotalRecordCnt(counts);
	    	System.out.println("The sql Data"+sql);
		    List<String[]> datacon=dbActionTemplate.getDataList(sb);
	    	return datacon;
	    }
	    throw new NoDataFoundException("No Data Found");
	}
	/*  public List<String[]> getTeamPerCount(CommonFilter commonFilter,String Flid)throws Exception{
	    	try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				
				System.out.println("Inside Dao Impl Team Performance");
				paramValues.add(condParms);
				paramValues.add(commonParams);
				List<String[]> dataList =  null;
				dataList =  dbActionTemplate.processFunctionCalls("GEN_ROLEVIEW_TEST.GEN_FN_TEAMPER_COUNT", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					CommonFunctions.debugMsg("totalCnt....."+totalCnt);
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
	    }*/
	  public List<String[]> getTeamPerCount(CommonFilter commonFilter,String Flid,String FileName)throws Exception{
	    	try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				List<String[]> dataList =  null;
				/*if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
					condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";*/
				if(UIUtils.isValidKeyId(commonFilter.getFromDate())){
				if(UIUtils.isValidKeyId(commonFilter.getFromDate()))
					condParms += "STARTDATE="+commonFilter.getFromDate()+";";
				if(UIUtils.isValidKeyId(commonFilter.getFromDate()))
					condParms += "ENDDATE="+commonFilter.getToDate()+";";
				System.out.println("FLID..."+Flid);
				
					condParms += "DEPT="+Flid+";";
				if(UIUtils.isValidKeyId(commonFilter.getType())){
					condParms += "EMPTYPE="+commonFilter.getType()+";";
				}
				paramValues.add(condParms);
				paramValues.add(commonParams);
				System.out.println("INSIDE IF");
				//List<String[]> dataList =  null;
			//	dataList =  dbActionTemplate.processFunctionCalls("GEN_ROLEVIEW_TEST.GEN_FN_TEAMPER_COUNTADDITION", paramValues);
				dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_TEAMPER_COUNTADDITION", paramValues);
				}
				else{
					condParms += "FLID="+commonFilter.getFlid()+";";
				paramValues.add(condParms);
				paramValues.add(commonParams);
				//List<String[]> dataList =  null;
				System.out.println("pARAMS"+commonFilter.getFlid());
			//	dataList =  dbActionTemplate.processFunctionCalls("GEN_ROLEVIEW_TEST.GEN_FN_TEAMPER_COUNT", paramValues);
				dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_TEAMPER_COUNT", paramValues);
				
				}
				
				int rIndex=0;
				for (String[] row : dataList) {
					if( rIndex  >1){
						
						String fileName =  FileName+row[3];
						if(row[3] != null && ! row[3].isEmpty()){
							String condSql1 = " AND EMPI_EMPLOYEEID = '" + row[0] + "'  "  ;
		                 try{
		                	
							dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_EMPLOYEEIMG, "EMPI_BLOBIMAGE", condSql1, fileName);
							String CountSql = "select count(*) from GEN_TL_EMPLOYEEIMG where 1=1 " + condSql1 ;
							System.out.println("cOUNT "+CountSql);
							String Filecount = dbActionTemplate.getSingleValue(CountSql);
							System.out.println("Filecount" + CountSql);
							if (Integer.parseInt(Filecount) > 0 )
							{
								dataList.get(rIndex)[1] = fileName;
							}
							else
							{
								dataList.get(rIndex)[1] = "no-image";
							}
		                 }
		                 catch(Exception e){
		                	 if("NO-DATA".equals( e.getMessage())){
		                		  
		                		 dataList.get(rIndex)[1] =  "no-image";
		                	 }
		                 }
						}
					}
				}
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					CommonFunctions.debugMsg("totalCnt....."+totalCnt);
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
		public Workbook getTeamPerformanceCountExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String Flid)
				throws Exception{
			// TODO Auto-generated method stub
				 List<String> paramValues = new ArrayList<String>();
				 ResultSet rs = null;
				 ExcelUtils excelUtils=null;
				 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
				 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				 try{
				 if(UIUtils.isValidKeyId(commonFilter.getKey())){
					 System.out.println("Click Not Null"+commonFilter.getKey());
						if(UIUtils.isValidKeyId(commonFilter.getFromDate()))
							condParms += "STARTDATE="+commonFilter.getFromDate()+";";
						if(UIUtils.isValidKeyId(commonFilter.getFromDate()))
							condParms += "ENDDATE="+commonFilter.getToDate()+";";
						System.out.println("FLID..."+Flid);
						
							condParms += "DEPT="+Flid+";";
						if(UIUtils.isValidKeyId(commonFilter.getType()))
							condParms += "EMPTYPE="+commonFilter.getType()+";";
						System.out.println("Emp Type"+commonFilter.getType());
							 paramValues.add(condParms);	 
							 paramValues.add(commonParams);
							// rs=dbActionTemplate.dbFunctionCall("GEN_ROLEVIEW_TEST.GEN_FN_TEAMPER_COUNTADDITION", paramValues);
							 rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_TEAMPER_COUNTADDITION", paramValues);
							 excelUtils = new ExcelUtils(tblJSONObj);
				 }
				 else{
					 System.out.println("Else Called");
					 condParms += "FLID="+commonFilter.getFlid()+";"; 
					 paramValues.add(condParms);	 
					 paramValues.add(commonParams);
					 rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_TEAMPER_COUNT", paramValues);
					 System.out.println("Result set from DAO Impl = "+rs);
				     excelUtils = new ExcelUtils(tblJSONObj);
				 }
				 	/*rs=dbActionTemplate.dbFunctionCall("GEN_ROLEVIEW_TEST.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
				 	//dbActionTemplate.processFunctionCallsWithColHeaders("GEN_ROLEVIEW_TEST.GEN_FN_TRANS_SUMMARY_REPORT", paramValues);
				 	CommonFunctions.debugMsg("Result set from DAO Impl = "+rs);
					ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);*/
					return excelUtils.writeToExcel(rs,format,2,0,0 );
				 }
				 finally{
					   if( rs != null)
				 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					     
				 }
		}

	  
	  @Override
	  public List<String[]> getEmployeeList(String flid, String filePath) throws Exception {

	      String safeFlid = "";
	      if (flid != null) {
	          safeFlid = flid.trim();
	      }

	      if ("null".equalsIgnoreCase(safeFlid) || "{}".equals(safeFlid)) {
	          safeFlid = "";
	      }

	      StringBuffer sql = new StringBuffer();

	      sql.append(" SELECT DISTINCT ");
	      sql.append(" emp.EMPM_KEYID AS EMPKEYID, ");
	      sql.append(" '1'::text AS COL1, ");
	      sql.append(" '2'::text AS COL2, ");
	      sql.append(" COALESCE(img.EMPI_FILENAME, '') AS Image, ");
	      sql.append(" emp.EMPM_NAME AS EmployeeName, ");
	      sql.append(" emp.EMPM_CODE AS EmployeeCode, ");
	      sql.append(" COALESCE(rolemst.ROLE_NAME, '') AS RoleName, ");
	      sql.append(" ''::text AS Abnormality, ");
	      sql.append(" ''::text AS Suggestion ");

	      sql.append(" FROM GEN_TL_FNLNROLETEAM frt ");
	      sql.append(" JOIN GEN_TL_EMPLOYEEMST emp ");
	      sql.append("      ON emp.EMPM_KEYID = frt.FRT_EMPM_KEYID ");
	      sql.append("     AND emp.EMPM_ACTIVE = 'Y' ");

	      sql.append(" LEFT JOIN ADM_TL_ROLEMST rolemst ");
	      sql.append("      ON rolemst.ROLE_KEYID = frt.FRT_ROLE_KEYID ");

	      sql.append(" LEFT JOIN GEN_TL_EMPLOYEEIMG img ");
	      sql.append("      ON img.EMPI_EMPLOYEEID = emp.EMPM_KEYID ");

	      sql.append(" JOIN GEN_MV_FLIDHIERARCHY h ");
	      sql.append("      ON h.FLID = frt.FRT_FNLN_KEYID ");

	      sql.append(" WHERE 1 = 1 ");

	      if (safeFlid.length() > 0) {
	          sql.append(" AND h.FLID = '").append(safeFlid.replace("'", "''")).append("' ");
	      } else {
	          // Important: getCol may call this with null FLID.
	          // This keeps SQL valid and prevents loading all employees accidentally.
	          sql.append(" AND 1 = 2 ");
	      }

	      sql.append(" ORDER BY COALESCE(rolemst.ROLE_NAME, ''), emp.EMPM_NAME ");

	      System.out.println("SQL is::::" + sql);

	      List<String> params = null;
	      List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);

	      if (gridData == null) {
	          gridData = new ArrayList<String[]>();
	          return gridData;
	      }

	      String imageBasePath = filePath;
	      if (imageBasePath == null) {
	          imageBasePath = "";
	      }

	      imageBasePath = imageBasePath.replace("\\", "/");

	      if (imageBasePath.length() > 0 && !imageBasePath.endsWith("/")) {
	          imageBasePath = imageBasePath + "/";
	      }

	      if (imageBasePath.length() > 0) {
	          File folder = new File(imageBasePath);
	          if (!folder.exists()) {
	              folder.mkdirs();
	          }
	      }

	      int rIndex = 0;

	      for (String[] row : gridData) {

	          // Skip getDataListWithColHeader header rows
	          if (rIndex > 1 && row != null && row.length > 3) {

	              String empKeyid = row[0];
	              String imageName = row[3];

	              if (empKeyid != null && imageName != null && imageName.trim().length() > 0 && !"{}".equals(imageName.trim())) {

	                  String cleanImageName = imageName.trim().replace("\\", "/");

	                  if (cleanImageName.lastIndexOf("/") >= 0) {
	                      cleanImageName = cleanImageName.substring(cleanImageName.lastIndexOf("/") + 1);
	                  }

	                  String finalFileName = imageBasePath + cleanImageName;

	                  String condSql1 = " AND EMPI_EMPLOYEEID = '" + empKeyid.replace("'", "''") + "' ";

	                  try {
	                      System.out.println("EMP IMG RESTORE -> " + finalFileName);

	                      dbActionTemplate.restoreFile1(
	                              TableNames.TBL_GEN_TL_EMPLOYEEIMG,
	                              "EMPI_BLOBIMAGE",
	                              condSql1,
	                              finalFileName
	                      );

	                      gridData.get(rIndex)[3] = finalFileName;
	                  }
	                  catch (Exception e) {
	                      System.out.println("EMP IMG RESTORE FAILED for employee " + empKeyid + " : " + e.getMessage());
	                      gridData.get(rIndex)[3] = "no-image";
	                  }
	              }
	              else {
	                  gridData.get(rIndex)[3] = "no-image";
	              }
	          }

	          rIndex++;
	      }

	      return gridData;
	  }

	    
//		@Override
//		public List<String[]> getEmployeeList(String flid,String filePath) throws Exception {
//			// TODO Auto-generated method stub
//			
//		StringBuffer sql = new StringBuffer();
//			sql.append(" SELECT "); 
//			sql.append("distinct  EMPM_KEYID EMPKEYID,'1','2',EMPI_FILENAME as Image,empm_name as EmployeeName,empm_code as  EmployeeCode,ROLE_NAME as RoleName,'' as Abnormality,'' as Suggestion ");  
//			sql.append(" FROM ");
//			sql.append("gen_tl_employeemst, adm_tl_rolemst,gen_tl_employeeimg,gen_mv_flidhierarchy, gen_tl_momattendance, GEN_TL_FNLNROLETEAM"); 
//			sql.append(" WHERE ");
//			sql.append("  empm_keyid(+) = FRT_EMPM_KEYID and empm_keyid=EMPI_EMPLOYEEID(+)AND FRT_ROLE_KEYID = ROLE_KEYID(+)AND FLID = FRT_FNLN_KEYID AND EMPM_ACTIVE = 'Y'");
//			
//				    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//					sql.append(" AND FLID = '"+flid+"' GROUP BY ROLE_NAME, empm_keyid, empm_name,empm_code,EMPI_FILENAME ORDER BY ROLE_NAME");
//				   
//
//		    System.out.println("SQL is::::"+sql);
//		    List<String> params = null;
//			List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
//			/*String refdoctype="VSP";
//			String imagetype="TOL";
//			String condSql = " IMFL_IMAGETYPE  = '" + imagetype+ "' AND IMFL_REFDOCTYPE = '" + refdoctype+"'";*/
//				int rIndex=0;
//				
//			for (String[] row : gridData) {
//				if( rIndex  >1){
//					
//					String fileName =  filePath+row[3];
//					if(row[3] != null && ! row[3].isEmpty()){
//						String condSql1 = " AND EMPI_EMPLOYEEID = '" + row[0] + "'  "  ;
//	                 try{
//	                	
//						dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_EMPLOYEEIMG, "EMPI_BLOBIMAGE", condSql1, fileName);
//						String CountSql = "select count(*) from GEN_TL_EMPLOYEEIMG where 1=1 " + condSql1 ;
//						System.out.println("cOUNT "+CountSql);
//						String Filecount = dbActionTemplate.getSingleValue(CountSql);
//						System.out.println("Filecount" + CountSql);
//						if (Integer.parseInt(Filecount) > 0 )
//						{
//							gridData.get(rIndex)[3] = fileName;
//						}
//						else
//						{
//							gridData.get(rIndex)[3] = "no-image";
//						}
//	                 }
//	                 catch(Exception e){
//	                	 if("NO-DATA".equals( e.getMessage())){
//	                		  
//	                		 gridData.get(rIndex)[3] =  "no-image";
//	                	 } 
//	                 }
//					}
//				}
//				rIndex++;
//			}
//			return gridData;
//			//return null;
//		}
		
		
		
		 public List<String[]> getAbnTeamPerData(CommonFilter commonFilter)throws Exception{
		    	try
				{
					List<String> paramValues = new ArrayList<String>();
					String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					
					if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
						condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
					
					paramValues.add(condParms);
					paramValues.add(commonParams);
					List<String[]> dataList =  null;
					
				//	dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.ABNORMALITYTEAMPER", paramValues);
					dataList =  dbActionTemplate.processFunctionCalls("ABNORMALITYTEAMPER", paramValues);
					
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
						CommonFunctions.debugMsg("totalCnt....."+totalCnt);
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
		  public List<String[]> getSuggTeamPerData(CommonFilter commonFilter)throws Exception{
		    	try
				{
					List<String> paramValues = new ArrayList<String>();
					String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					
					if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
						condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
					if(UIUtils.isValidKeyId(commonFilter.getFromMonth()))
						condParms += "FROMMONTH="+commonFilter.getFromMonth()+";";
					if(UIUtils.isValidKeyId(commonFilter.getToMonth()))
						condParms += "TOMONTH="+commonFilter.getToMonth()+";";
					System.out.println("condParms"+condParms);
					paramValues.add(condParms);
					paramValues.add(commonParams);
					List<String[]> dataList =  null;
					dataList =  dbActionTemplate.processFunctionCalls("SUGGESTIONTEAMPER", paramValues);
					//dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.SUGGESTIONTEAMPER", paramValues);
					if( commonFilter.getViewClick() == 'Y'){
						String totalCnt = paramValues.get(0); 
						System.out.println("totalCnt....."+totalCnt);
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
		  public String getTotalSuggestionCount(String empKeyId, String flid) throws Exception {

		      System.out.println("empKeyId" + empKeyId + " flid" + flid);

		      String safeEmpKeyId = empKeyId == null ? "" : empKeyId.trim().replace("'", "''");
		      String safeFlid = flid == null ? "" : flid.trim().replace("'", "''");

		      if (safeEmpKeyId.length() == 0 || safeFlid.length() == 0 || "null".equalsIgnoreCase(safeFlid)) {
		          System.out.println("TotalSuggestionCount skipped because empKeyId/flid is empty");
		          return "0";
		      }

		      StringBuffer sql = new StringBuffer();

		      sql.append(" SELECT COUNT(*) ");
		      sql.append(" FROM KZN_TL_KAIZENBANKMST kb ");

		      sql.append(" JOIN GEN_TL_EMPLOYEEMST A ");
		      sql.append("      ON A.EMPM_KEYID = kb.KZBN_SUGGESTEDBY ");

		      sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST B ");
		      sql.append("      ON B.EMPM_KEYID = kb.KZBN_ACREJBY ");

		      sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST C ");
		      sql.append("      ON C.EMPM_KEYID = kb.KZBN_RESPONSIBILITY ");

		      sql.append(" JOIN GEN_MV_FLIDHIERARCHY h ");
		      sql.append("      ON h.FLID = kb.KZBN_FLID ");

		      sql.append(" LEFT JOIN KZN_TL_MST kzn ");
		      sql.append("      ON kb.KZBN_KEYID = kzn.KZNM_KZBNKEYID ");

		      sql.append(" WHERE 1 = 1 ");
		      sql.append(" AND kb.KZBN_EHSRELATED = 'N' ");
		      sql.append(" AND POSITION('").append(safeFlid).append("' IN COALESCE(h.PARENTFLIDS, '') || '-' || COALESCE(h.FLID, '')) > 0 ");
		      sql.append(" AND kb.KZBN_SUGGESTEDBY = '").append(safeEmpKeyId).append("' ");

		      System.out.println("Total Suggestion SQL ::: " + sql);

		      String totalCount = dbActionTemplate.getSingleValue(sql.toString());

		      System.out.println("TOTAL TotalCount " + totalCount);

		      return totalCount;
		  }
/*
		  @Override
			public String getTotalSuggestionCount(String empKeyId, String flid) throws Exception {
				// TODO Auto-generated method stub
				StringBuffer sql = new StringBuffer();
				String TotalCount=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM KZN_TL_KAIZENBANKMST,GEN_TL_EMPLOYEEMST A, GEN_TL_EMPLOYEEMST B,GEN_TL_EMPLOYEEMST C,gen_mv_flidhierarchy,KZN_TL_MST WHERE  1 = 1 AND A.EMPM_KEYID = KZBN_SUGGESTEDBY AND KZBN_EHSRELATED = 'N' AND B.EMPM_KEYID(+) = Kzbn_Acrejby AND C.EMPM_KEYID(+) = KZBN_RESPONSIBILITY AND FLID = KZBN_FLID AND KZBN_KEYID = KZNM_KZBNKEYID(+) AND INSTR (parentflids || '-' || flid, '"+flid+"') > 0 AND KZBN_SUGGESTEDBY = '"+empKeyId+"'");
				
			    System.out.println("TOTAL TotalCount"+TotalCount);	
				return TotalCount;
				
			}
		  
		  */
		   /*
			public String getTotalAbnormalityCount(String empKeyId, String flid) throws Exception{
				System.out.println("Id"+empKeyId+"flid"+flid);
				StringBuffer sql=new StringBuffer("SELECT COUNT(*) FROM ABN_TL_ABNORMALITY,GEN_TL_EMPLOYEEMST A, ");
				sql.append("GEN_TL_EMPLOYEEMST C,GEN_TL_EMPLOYEEMST R,ABN_TL_TYPEMST,ABN_TL_CATEGORYMST,GEN_TL_TRADEMST,ABN_TL_IMPACTMST,");
				sql.append("ABN_TL_AFEEMMST,ABN_TL_TAGMST,gen_mv_flidhierarchy  WHERE 1 = 1");
		        sql.append(" AND ABNM_DETECTEDBY = A.EMPM_KEYID(+) AND ABNM_RESPONSIBLEID = C.EMPM_KEYID(+) AND TRDM_KEYID(+) = ABNM_TRADEID");
		        sql.append(" AND R.EMPM_KEYID(+) = ABNM_RESPONSIBLEID AND ABNM_FLID = FLID AND TAGM_KEYID = ABNM_TAGCLASSID ");
		        sql.append(" AND ABNM_ACTIVE = 'Y' AND ABNM_TYPEID = ABTM_KEYID(+) AND ABNM_CATEGORYID = ABCM_KEYID(+) AND ABNM_IMPACTID = ABIM_KEYID(+)");
		        sql.append("AND AFEM_KEYID(+) = ABNM_AFEEMID AND ABNM_REFDOCTYPE <> 'SHE' ");
		        sql.append(" AND INSTR (parentflids || '-' || flid, '"+flid+"') > 0 AND ABNM_DETECTEDBY = '"+empKeyId+"'");
		        String Total=dbActionTemplate.getSingleValue(sql.toString());
		     //   System.out.println("Total"+sql );
		        System.out.println("totall  "  + Total );
		        System.out.println("totall  "  + sql );
		        CommonFunctions.debugMsg("Final sql in teamperform " + sql);
		        return Total;	
			}
			*/
			
			public String getTotalAbnormalityCount(String empKeyId, String flid) throws Exception {

			    System.out.println("Id" + empKeyId + "flid" + flid);

			    String safeEmpKeyId = empKeyId == null ? "" : empKeyId.trim().replace("'", "''");
			    String safeFlid = flid == null ? "" : flid.trim().replace("'", "''");

			    if (safeEmpKeyId.length() == 0 || safeFlid.length() == 0 || "null".equalsIgnoreCase(safeFlid)) {
			        System.out.println("TotalAbnormalityCount skipped because empKeyId/flid is empty");
			        return "0";
			    }

			    StringBuffer sql = new StringBuffer();

			    sql.append(" SELECT COUNT(*) ");
			    sql.append(" FROM ABN_TL_ABNORMALITY abn ");

			    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST A ");
			    sql.append("        ON abn.ABNM_DETECTEDBY = A.EMPM_KEYID ");

			    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST C ");
			    sql.append("        ON abn.ABNM_RESPONSIBLEID = C.EMPM_KEYID ");

			    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST R ");
			    sql.append("        ON R.EMPM_KEYID = abn.ABNM_RESPONSIBLEID ");

			    sql.append(" LEFT JOIN ABN_TL_TYPEMST typ ");
			    sql.append("        ON abn.ABNM_TYPEID = typ.ABTM_KEYID ");

			    sql.append(" LEFT JOIN ABN_TL_CATEGORYMST cat ");
			    sql.append("        ON abn.ABNM_CATEGORYID = cat.ABCM_KEYID ");

			    sql.append(" LEFT JOIN GEN_TL_TRADEMST trd ");
			    sql.append("        ON trd.TRDM_KEYID = abn.ABNM_TRADEID ");

			    sql.append(" LEFT JOIN ABN_TL_IMPACTMST imp ");
			    sql.append("        ON abn.ABNM_IMPACTID = imp.ABIM_KEYID ");

			    sql.append(" LEFT JOIN ABN_TL_AFEEMMST afe ");
			    sql.append("        ON afe.AFEM_KEYID = abn.ABNM_AFEEMID ");

			    sql.append(" JOIN ABN_TL_TAGMST tag ");
			    sql.append("        ON tag.TAGM_KEYID = abn.ABNM_TAGCLASSID ");

			    sql.append(" JOIN GEN_MV_FLIDHIERARCHY h ");
			    sql.append("        ON abn.ABNM_FLID = h.FLID ");

			    sql.append(" WHERE 1 = 1 ");
			    sql.append(" AND abn.ABNM_ACTIVE = 'Y' ");
			    sql.append(" AND abn.ABNM_REFDOCTYPE <> 'SHE' ");
			    sql.append(" AND POSITION('").append(safeFlid).append("' IN COALESCE(h.PARENTFLIDS, '') || '-' || COALESCE(h.FLID, '')) > 0 ");
			    sql.append(" AND abn.ABNM_DETECTEDBY = '").append(safeEmpKeyId).append("' ");

			    System.out.println("Total Abnormality SQL ::: " + sql);

			    String total = dbActionTemplate.getSingleValue(sql.toString());

			    System.out.println("Total Abnormality Count ::: " + total);

			    return total;
			}
			
}
