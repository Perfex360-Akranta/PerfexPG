package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PMActivitySummaryRptDao;
//import com.akranta.tpm.dao.PMActivitySummaryRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PMActivitySummaryRptDaoImpl implements BAL_PMActivitySummaryRptDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	
	public BAL_PMActivitySummaryRptDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BAL_PMActivitySummaryRptDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public List<String[]> getSummary(CommonFilter commonFilter) {
		try
		{
			
			String strSect=null;
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			if(("Y").equals(commonFilter.getActwise()))
				condParms+="ISACTWISE=Y;";
			if(UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
					condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";
			CommonFunctions.debugMsg("Sub Group"+commonFilter.getEqpSubGroup().getId());
			
			strSect=getSect(commonFilter.getFlid());
			CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
			if(strSect.substring(0,3).equals("SEC")){
				CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
				condParms+="SECTIONID="+strSect;
			}
			else if(strSect.substring(0,3).equals("FCT")){
				CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
				condParms+="FACTORYID="+strSect;
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("After ff.........."+ condParms);
			List<String[]> dataList;
			/*if(("Y").equals(commonFilter.getBdActivity()))
				dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PLM_FN_GETSCHEDULE", paramValues);
			else*/
			System.out.println("After ff..........////////" + paramValues);
				dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_ACTIVITYCOMPLETED", paramValues);////PLM_FN_GETSCHEDULE", paramValues);
			System.out.println("..........After ff    " +commonFilter.getViewClick());
			String totalCnt = paramValues.get(0);
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnty = paramValues.get(0); 
				CommonFunctions.debugMsg("total"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
				if( isInteger ){
					CommonFunctions.debugMsg("total 1  "+totalCnty);
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
					CommonFunctions.debugMsg("total 2"+commonFilter.getTotalRecordCnt());
					
				}
			}
			return dataList; 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
public String  getSect(String flid) throws Exception{
		
		String sql="Select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_KEYID='"+flid+"'";
		String sectid=dbActionTemplate.getSingleValue(sql);
		
		return sectid;
	}
public Workbook PMCompSummaryReportExportExcel(CommonFilter commonFilter,
		JSONObject tableModel, String rptFormat,String jobType) throws Exception{
	ResultSet rs = null;
	CommonFunctions.debugMsg(" inside the get Excel 055");
	try {
		rs = getActvityCompSummaryReportResultSet(commonFilter,jobType);
		ExcelUtils excelUtils = new ExcelUtils(tableModel);  
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

		XLConditionalFormats condFormat = new XLConditionalFormats();
		
		condFormat.setFontColor(new RGB(254, 0, 0)); // red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short) 14);
		condFormat.setFontBoldWeight((short) 20);
		condFormat.setFromCol(13);
		condFormat.setToCol(-1);
		condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue((char) 252 + ""); // Tick
		condFormat.setIdentfier("tick");
		condFormats.add(condFormat);
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs, rptFormat, 4, 0, 0);

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DBActionTemplate.closeConnection(rs, null, null, null, rs
				.getStatement().getConnection());
	}
	return null;
}

@Override
public List<String[]> getPMComplianceSummary(CommonFilter commonFilter,String jobType) throws Exception {
	String strSect=null;
	List<String> paramValues = new ArrayList<String>();
	
	String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	if(("Y").equals(commonFilter.getActwise()))
		condParms+="ISACTWISE=Y;";
	if(UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
			condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";
	
	if(jobType!="ALL"){
		condParms+="JOBTYPE="+jobType+";";
	}
	else{
		condParms+="JOBTYPE=ALL;";
	}
	CommonFunctions.debugMsg("Sub Group"+commonFilter.getEqpSubGroup().getId());
	
	strSect=getSect(commonFilter.getFlid());
	CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
	if(strSect.substring(0,3).equals("SEC")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="SECTIONID="+strSect;
	}
	else if(strSect.substring(0,3).equals("FCT")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="FACTORYID="+strSect;
	}
	paramValues.add(condParms);  
	paramValues.add(commonParams);
	System.out.println(paramValues+" After ff.........."+ condParms);
	List<String[]> dataList;
	
		//dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_PMCOMPLIANCESUMMARYRPTGRAPH", paramValues);////PLM_FN_GETSCHEDULE", paramValues);
	dataList =  fnCallApi.callFunction("PLM_FN_PMCOMPLIANCESUMMARYRPTGRAPH_SB", paramValues,3,false);////PLM_FN_GETSCHEDULE", paramValues);
	String totalCnt = paramValues.get(0);
	commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnty = paramValues.get(0); 
		CommonFunctions.debugMsg("total"+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
		if( isInteger ){
			CommonFunctions.debugMsg("total 1  "+totalCnty);
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
			CommonFunctions.debugMsg("total 2"+commonFilter.getTotalRecordCnt());
			
		}
	}
	return dataList; 
}
@Override
public String getReportName(CommonFilter commonFilter) throws Exception {
	String flid=commonFilter.getFlid();
	String sql="select FNLN_DESCRIPTION || ' - '|| FNLN_DISPLAYCODE FROM GEN_TL_FUNCTIONALLOCN where FNLN_KEYID='"+flid+"'";
	String rptName=dbActionTemplate.getSingleValue(sql);
	
	return rptName;
}
@Override
public Workbook PMCompSummaryExportExcel(CommonFilter commonFilter,
		JSONObject tableModel, String rptFormat) throws Exception {
		
	ResultSet rs = null;
	CommonFunctions.debugMsg(" inside the get Excel 066"+tableModel.length());
/*
	JSONObject json = (JSONObject) tableModel;
	
	
   net.sf.json.JSONArray names = json.names();
    
    for (int i = 0; i < names.length(); ++i) {
    	
        String key = names.getString(i);
        names.getString(11);
    	//CommonFunctions.debugMsg("in side bb daoimpl 026 ");
        
    	String keyid=json.getString("colModel");
   
    	CommonFunctions.debugMsg(keyid.compareTo("name") +"in side bb daoimpl 026 "+key);
        } 
    */
		
			try{
			rs =   getActivityCompletedReportResultSet(commonFilter);
			
			CommonFunctions.debugMsg(tableModel+ "in side colModelName daoimpl ...");
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
						
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(9,5,247)); //red font
			//condFormatTick.setBgColor(new RGB(245,123,61));
			condFormatTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			//condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("1"); //Tick
			condFormatTick.setSymbolStr("A");
			condFormatTick.setIdentfier("1");
			//condFormats.add(condFormatTick);
			
		 	XLConditionalFormats condFormatBGTick = new XLConditionalFormats();
		 	condFormatBGTick.setFontColor(new RGB(9,5,247)); //red font
		 	//condFormatBGTick.setBgColor(new RGB(245,123,61));
		 	condFormatBGTick.setFontName(XLConditionalFormats.FONT_DEFAULT);
		 	condFormatBGTick.setFontHeightPoint((short)9);
		 	condFormatBGTick.setFontBoldWeight((short)13);
		 	condFormatBGTick.setFromCol(3);
		 	condFormatBGTick.setToCol(-1);
		 	condFormatBGTick.setOperator(ComparisonOperator.EQUAL);
		 	condFormatBGTick.setCondValue("2"); //Tick
		 	condFormatBGTick.setSymbolStr("A");
		 	condFormatBGTick.setIdentfier("2");
			//condFormats.add(condFormatBGTick);
			
			XLConditionalFormats condFormatBG = new XLConditionalFormats();
			condFormatBG.setFontColor(new RGB(9,5,247)); //red font
			//condFormatBG.setBgColor(new RGB(245,123,61));
			condFormatBG.setFontName(XLConditionalFormats.FONT_DEFAULT );
			condFormatBG.setFontHeightPoint((short)9);
			condFormatBG.setFontBoldWeight((short)13);
			condFormatBG.setFromCol(3);
			condFormatBG.setToCol(-1);
			condFormatBG.setOperator(ComparisonOperator.EQUAL);
			condFormatBG.setCondValue("3"); //Tick
			condFormatBG.setSymbolStr("A");
			condFormatBG.setIdentfier("3");
			//condFormats.add(condFormatBG);
			
			XLConditionalFormats condFormatBGClr = new XLConditionalFormats();
			condFormatBGClr.setFontColor(new RGB(9,5,247)); //red font
			//condFormatBGClr.setBgColor(new RGB(245,123,61));
			condFormatBGClr.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatBGClr.setFontHeightPoint((short)10);
			condFormatBGClr.setFontBoldWeight((short)15);
			condFormatBGClr.setFromCol(3);
			condFormatBGClr.setToCol(-1);
			condFormatBGClr.setOperator(ComparisonOperator.EQUAL);
			condFormatBGClr.setCondValue("4"); //Tick
			condFormatBGClr.setSymbolStr(" ");
			condFormatBGClr.setIdentfier("4");
			//condFormats.add(condFormatBGClr);
			
		 /*
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("0"); //Tick
			condFormat.setIdentfier("0");
			condFormat.setSymbolStr(" ");
			
			condFormats.add(condFormat);*/
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 5,0,0 );
	}
	catch(Exception e){
		e.printStackTrace();
	}
		   finally{
			   if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	return null;
	}

private ResultSet getActvityCompSummaryReportResultSet(CommonFilter commonFilter,String jobType)
throws Exception {
	CommonFunctions.debugMsg("Inside the resultset method of summery complieance");
	
	
List<String> paramValues = getFilterParamValues(commonFilter,jobType);

ResultSet rs = null;
rs = dbActionTemplate.NewdbFunctionCall2("PLM_FN_PMCOMPLIANCESUMMARYRPTGRAPH_SB", paramValues);
return rs;
}

private ResultSet getActivityCompletedReportResultSet(CommonFilter commonFilter) throws Exception
{	
	CommonFunctions.debugMsg(" Inside the getActivityCompletedReportResultSet ");
	List<String> paramValues = getFilterParamValues(commonFilter);
	
	return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_ACTIVITYCOMPLETED", paramValues);
}
private ResultSet getActvityPlanandActualResultSet(CommonFilter commonFilter)
throws Exception {
	CommonFunctions.debugMsg("Inside the resultset method of summery complieance");
List<String> paramValues = getFilterParamValues(commonFilter);
ResultSet rs = null;
rs = dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_PMPLANANDACTUAL", paramValues);
return rs;
}

private List<String> getFilterParamValues(CommonFilter commonFilter , String jobType){
	List<String> paramValues = new ArrayList<String>();
	
	String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	String weekno = commonFilter.getWeekno();
	String woId = commonFilter.getWodetailid();
	String tradeId=commonFilter.getTrarId();
	
	CommonFunctions.debugMsg("woId  --"+woId);
	if(UIUtils.isValidKeyId(weekno))
		condParms += ";WEEKNO="+weekno;
	if(UIUtils.isValidKeyId(woId))
		condParms += ";WODETAILID="+woId;
	if(UIUtils.isValidKeyId(tradeId))
		condParms +=";TRADEID="+tradeId;
	if(jobType!="ALL"){
		condParms+="JOBTYPE="+jobType+";";
	}
	else{
		condParms+="JOBTYPE=ALL;";
	}
	
	String strSect=null;
	try {
		strSect = getSect(commonFilter.getFlid());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+commonFilter.getFlid());
	if(strSect.substring(0,3).equals("SEC")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+=";SECTIONID="+strSect;
	}
	else if(strSect.substring(0,3).equals("FCT")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+=";FACTORYID="+strSect;
	}
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	return paramValues;
}

private List<String> getFilterParamValues(CommonFilter commonFilter){
	List<String> paramValues = new ArrayList<String>();
	
	String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	String weekno = commonFilter.getWeekno();
	String woId = commonFilter.getWodetailid();
	String tradeId=commonFilter.getTrarId();
	String jobType=commonFilter.getJobtype().getId();
	CommonFunctions.debugMsg("woId  --"+woId);
	if(UIUtils.isValidKeyId(weekno))
		condParms += ";WEEKNO="+weekno;
	if(UIUtils.isValidKeyId(woId))
		condParms += ";WODETAILID="+woId;
	if(UIUtils.isValidKeyId(tradeId))
		condParms +=";TRADEID="+tradeId;
	if(UIUtils.isValidKeyId(jobType))
		condParms += ";JOBTYPE="+jobType;
	
	String strSect=null;
	try {
		strSect = getSect(commonFilter.getFlid());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+commonFilter.getFlid());
	if(strSect.substring(0,3).equals("SEC")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+=";SECTIONID="+strSect;
	}
	else if(strSect.substring(0,3).equals("FCT")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+=";FACTORYID="+strSect;
	}
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	return paramValues;
}
@Override
public List<String[]> getActivityGraph(CommonFilter chrtCommonFilter,String jobType)throws Exception 
{
	// TODO Auto-generated method stub
	String strSect=null;
	
	List<String> paramValues = new ArrayList<String>();
	
	
	CommonFunctions.debugMsg(" jobtype  jobtype jobtype"+jobType);
	String condParms = FilterCondSql.getPMRelatedCondStr(chrtCommonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(chrtCommonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	if(("Y").equals(chrtCommonFilter.getActwise()))
		condParms+="ISACTWISE=Y;";
	if(UIUtils.isValidKeyId(chrtCommonFilter.getEqpSubGroup().getId()))
			condParms+="EQPSUBGRPID="+chrtCommonFilter.getEqpSubGroup().getId()+";";
	if(jobType!="ALL"){
		condParms+="JOBTYPE="+jobType+";";
	}
	CommonFunctions.debugMsg("Sub Group"+chrtCommonFilter.getEqpSubGroup().getId());
	
	try {
		strSect=getSect(chrtCommonFilter.getFlid());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
	if(strSect.substring(0,3).equals("SEC")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="SECTIONID="+strSect;
	}
	else if(strSect.substring(0,3).equals("FCT")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="FACTORYID="+strSect;
	}
	paramValues.add(condParms);
	paramValues.add(commonParams);
	System.out.println("After ff.........."+ condParms);
	List<String[]> dataList = null;
	
		try {
			dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_PMCOMPLIANCESUMMARYRPTGRAPH", paramValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}////PLM_FN_GETSCHEDULE", paramValues);
	String totalCnt = paramValues.get(0);
	chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	if( chrtCommonFilter.getViewClick() == 'Y'){
		String totalCnty = paramValues.get(0); 
		CommonFunctions.debugMsg("total"+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
		if( isInteger ){
			CommonFunctions.debugMsg("total 1  "+totalCnty);
			chrtCommonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
			CommonFunctions.debugMsg("total 2"+chrtCommonFilter.getTotalRecordCnt());
			
		}
	}
	return dataList; 

}
@Override
public List<String[]> PMplanvsactualSummaryReport(CommonFilter commonFilter)throws Exception{
String strSect=null;

List<String> paramValues = new ArrayList<String>();


String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
if(("Y").equals(commonFilter.getActwise()))
	condParms+="ISACTWISE=Y;";
if(UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
		condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";


try {
	strSect=getSect(commonFilter.getFlid());
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
if(strSect.substring(0,3).equals("SEC")){
	CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
	condParms+="SECTIONID="+strSect;
}
else if(strSect.substring(0,3).equals("FCT")){
	CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
	condParms+="FACTORYID="+strSect;
}
paramValues.add(condParms);
paramValues.add(commonParams);
System.out.println("After ff.........."+ condParms);
List<String[]> dataList = null;

	try {
		dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PMPLANANDACTUAL", paramValues);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}////PLM_FN_GETSCHEDULE", paramValues);
String totalCnt = paramValues.get(0);
commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
if( commonFilter.getViewClick() == 'Y'){
	String totalCnty = paramValues.get(0); 
	CommonFunctions.debugMsg("total"+totalCnt);
	boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
	if( isInteger ){
		CommonFunctions.debugMsg("total 1  "+totalCnty);
		commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
		CommonFunctions.debugMsg("total 2"+commonFilter.getTotalRecordCnt());
		
	}
}
return dataList; 

}
@Override
public Workbook PMplanvsactualSummaryReportExportExcel(
		CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception{
ResultSet rs = null;
CommonFunctions.debugMsg(" inside the get Excel 055");
try {
	rs = getActvityPlanandActualResultSet(commonFilter);
	ExcelUtils excelUtils = new ExcelUtils(tableModel);
	List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

	XLConditionalFormats condFormat = new XLConditionalFormats();
	condFormat.setFontColor(new RGB(254, 0, 0)); // red font
	condFormat.setFontName("Wingdings");
	condFormat.setFontHeightPoint((short) 14);
	condFormat.setFontBoldWeight((short) 20);
	condFormat.setFromCol(13);
	condFormat.setToCol(-1);
	condFormat.setOperator(ComparisonOperator.EQUAL);
	condFormat.setCondValue((char) 252 + ""); // Tick
	condFormat.setIdentfier("tick");
	condFormats.add(condFormat);
	excelUtils.setCondFormats(condFormats);
	return excelUtils.writeToExcel(rs, format, 3, 0, 0);

} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} finally {
	DBActionTemplate.closeConnection(rs, null, null, null, rs
			.getStatement().getConnection());
}
return null;
}
@Override
public List<String[]> getPlanVsActualActivityGraph(CommonFilter commonFilter)
		throws Exception {
	String strSect=null;
	
	List<String> paramValues = new ArrayList<String>();
	
	
	String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	if(("Y").equals(commonFilter.getActwise()))
		condParms+="ISACTWISE=Y;";
	if(UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
			condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";
	
	
	try {
		strSect=getSect(commonFilter.getFlid());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
	if(strSect.substring(0,3).equals("SEC")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="SECTIONID="+strSect;
	}
	else if(strSect.substring(0,3).equals("FCT")){
		CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
		condParms+="FACTORYID="+strSect;
	}
	paramValues.add(condParms);
	paramValues.add(commonParams);
	System.out.println("After ff.........."+ condParms);
	List<String[]> dataList = null;
	
		try {
			dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PMPLANANDACTUAL", paramValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}////PLM_FN_GETSCHEDULE", paramValues);
	String totalCnt = paramValues.get(0);
	commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnty = paramValues.get(0); 
		CommonFunctions.debugMsg("total"+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
		if( isInteger ){
			CommonFunctions.debugMsg("total 1  "+totalCnty);
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
			
		}
	}
	return dataList; 

}
@Override
public List<String[]> getTimeSummary(CommonFilter commonFilter)
		throws Exception {
	try
	{
		
		String strSect=null;
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if(("Y").equals(commonFilter.getActwise()))
			condParms+="ISACTWISE=Y;";
		if(UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
				condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";
		CommonFunctions.debugMsg("Sub Group"+commonFilter.getEqpSubGroup().getId());
		
		strSect=getSect(commonFilter.getFlid());
		CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
		if(strSect.substring(0,3).equals("SEC")){
			CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
			condParms+="SECTIONID="+strSect;
		}
		else if(strSect.substring(0,3).equals("FCT")){
			CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
			condParms+="FACTORYID="+strSect;
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		System.out.println("After ff.........."+ condParms);
		List<String[]> dataList;
		/*if(("Y").equals(commonFilter.getBdActivity()))
			dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PLM_FN_GETSCHEDULE", paramValues);
		else*/
		System.out.println("After ff..........////////" + paramValues);
			dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PMTIMESUMMARY.PLM_FN_PMTIMESUMMARY", paramValues);////PLM_FN_GETSCHEDULE", paramValues);
		System.out.println("..........After ff    " +commonFilter.getViewClick());
		String totalCnt = paramValues.get(0);
		commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnty = paramValues.get(0); 
			CommonFunctions.debugMsg("total"+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
			if( isInteger ){
				CommonFunctions.debugMsg("total 1  "+totalCnty);
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
				CommonFunctions.debugMsg("total 2"+commonFilter.getTotalRecordCnt());
				
			}
		}
		return dataList; 
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;

}
@Override
public Workbook PMTimeSummaryExportExcel(CommonFilter commonFilter,
		JSONObject tblJSONObj, String format) throws Exception {
	// TODO Auto-generated method stub
	return null;
}
}


