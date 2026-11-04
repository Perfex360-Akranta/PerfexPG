package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_PMActivitySummaryRptService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_PMActivitySummaryRptServiceImpl;
/**
 * Servlet implementation class PmActivitySummaryReport
 */
//@WebServlet("/PmActivitySummaryReportServlet")
public class BAL_PmActivitySummaryReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    


	BAL_PMActivitySummaryRptService pMActivityRptService;
	CommonFilterService commonFilterService;
	//private static final String commonFilterIden = "impVsCompcommonFilter";

	public BAL_PmActivitySummaryReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		try {
			pMActivityRptService = (BAL_PMActivitySummaryRptServiceImpl)UIUtils.getServiceObject(request,"BAL_PMActivitySummaryRptServiceImpl");
			commonFilterService =  (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			pMActivityRptService.BAL_PMActivitySummaryRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

	if( action.equals("pmsummaryReport_input.pmsmrRpt") ){	
		CommonFunctions.debugMsg("In side Entry Servlet");
		   String mchId = request.getParameter("cmbMchid");
		   String cellId = request.getParameter("cmbCellid");
		   String sectId = request.getParameter("cmbSectid");
		   String factId = request.getParameter("cmbFactid");
		   String fromMonth = request.getParameter("dtFromMonth");
		   String toMonth = request.getParameter("dtToMonth");
		   String relTo = request.getParameter("cboRelatedTo");
		   String filterString = request.getParameter("filterString");
		   String asseqpWise = request.getParameter("asseqpWise");
		   String tradeId=request.getParameter("hdncmbTradeId");
		   String jobType=request.getParameter("hdncmbJobType");
		   			
		   if( fromMonth == null ){
			   String currentDate = CommonFunctions.getDate();
			   fromMonth = CommonFunctions.addMonth(currentDate,-3);
			   toMonth = CommonFunctions.addMonth(currentDate,0);
			   
			   fromMonth = fromMonth.substring(fromMonth.indexOf("-")+1);
			   toMonth = toMonth.substring(toMonth.indexOf("-")+1);
			  // CommonFunctions.debugMsg("Tomonth "+ CommonFunctions.getCurrentMonth()+CommonFunctions.getCurrentYear()+" from month"+CommonFunctions.addMonth(currentDate,-3));
			   //int year = Integer.parseInt(CommonFunctions.getCurrentYear());
			   //fromMonth =   CommonFunctions.getCurrentMonth() +"-"+(year-1);
			   //toMonth =   CommonFunctions.getCurrentMonth() +"-"+(year);
		   }
		   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/allocationUpdtCancel.jsp");
		   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/updatecancelWo.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PMActivitySummaryRpt.jsp");
			request.setAttribute("machineId", mchId);
			request.setAttribute("cellId", cellId);
			request.setAttribute("sectionId", sectId);
			request.setAttribute("factId", factId);
			request.setAttribute("fromMonth", fromMonth);
			request.setAttribute("toMonth", toMonth);
			request.setAttribute("relatedTo", relTo);
			request.setAttribute("filterString", filterString);
			request.setAttribute("asseqpWise", asseqpWise);
			request.setAttribute("hdncmbTradeId", tradeId);
			request.setAttribute("hdncmbJobType", jobType);
			//String sql = "select CNFM_KEYID  from "+TableNames.TBL_ADM_TL_CONFIGURATIONMST +" where CNFM_CODE = 'ENTCODETRUE'";
			rd.forward(request, response); 
	   }
	else if( action.equals("pmsummaryReport_getCol.pmsmrRpt") )	   {
		   //String type= request.getParameter("type");
		   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
		   commonFilter.setEqpmnt("EQP");
		   if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setMonwise("Y");				  
		   }
		   else{
		 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 	  }
		   }

		   List<String []> monthlyPlanList  = pMActivityRptService.getSummary(commonFilter);
		  
		   JSONObject monthlyPlanData = fillJqGrid(monthlyPlanList,request,4,0,commonFilter);
		   httpSession.setAttribute("monthlyPlanData", monthlyPlanData);						
		   PrintWriter out = response.getWriter();
		   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter.getEqpmnt());				 

		   httpSession.removeAttribute("monthCalColModel");
		   httpSession.setAttribute("monthCalColModel", jsonObject);
		   out.println(jsonObject);
	   }
	   else if( action.equals("pmsummaryReport_getData.pmsmrRpt") )
	   {
		   try
			{
			   	 PrintWriter out = response.getWriter();
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");
				 String type = request.getParameter("type");
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(1).substring(3,11));
					  commonFilter.setMonwise("Y");
			 	 }
				 JSONObject jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData"); 
				 if(page.equals("1") && jsonObject != null){
	        		 
				 }else
	        	 {				
					 CommonFunctions.debugMsg("Type==="+type);
	        		 List<String[]> monPlan  = pMActivityRptService.getSummary(commonFilter);
	        		 jsonObject = fillJqGrid(monPlan,request,4,0,commonFilter); 
	        		
	        	 }				
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("MonthlyPlanCommonFilter");
	  			 httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
	  			httpSession.removeAttribute("monthlyPlanData");
		    }
			catch(Exception e)
			{
				 CommonFunctions.debugMsg(e.getMessage());
			}
	   }
	   else if( action.equals("pmsummaryReport_getExcel.pmsmrRpt") )
	   {
		   CommonFunctions.debugMsg("In side 01 the Get Excel");
		   try
			{			   
				CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//commonFilter.setToRow(null);
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("monthCalColModel");
				
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = pMActivityRptService.PMCompSummaryExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "PMActivityCompleted", format);

				
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   }
	////
	
	if( action.equals("pmtimesummaryReport_input.pmsmrRpt") ){	
		CommonFunctions.debugMsg("In side Entry Servlet");
		   String mchId = request.getParameter("cmbMchid");
		   String cellId = request.getParameter("cmbCellid");
		   String sectId = request.getParameter("cmbSectid");
		   String factId = request.getParameter("cmbFactid");
		   String fromMonth = request.getParameter("dtFromMonth");
		   String toMonth = request.getParameter("dtToMonth");
		   String relTo = request.getParameter("cboRelatedTo");
		   String filterString = request.getParameter("filterString");
		   String asseqpWise = request.getParameter("asseqpWise");
		   String tradeId=request.getParameter("hdncmbTradeId");
		   String jobType=request.getParameter("hdncmbJobType");
		   			
		   if( fromMonth == null ){
			   String currentDate = CommonFunctions.getDate();
			   fromMonth = CommonFunctions.addMonth(currentDate,-3);
			   toMonth = CommonFunctions.addMonth(currentDate,0);
			   
			   fromMonth = fromMonth.substring(fromMonth.indexOf("-")+1);
			   toMonth = toMonth.substring(toMonth.indexOf("-")+1);
			  // CommonFunctions.debugMsg("Tomonth "+ CommonFunctions.getCurrentMonth()+CommonFunctions.getCurrentYear()+" from month"+CommonFunctions.addMonth(currentDate,-3));
			   //int year = Integer.parseInt(CommonFunctions.getCurrentYear());
			   //fromMonth =   CommonFunctions.getCurrentMonth() +"-"+(year-1);
			   //toMonth =   CommonFunctions.getCurrentMonth() +"-"+(year);
		   }
		   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/allocationUpdtCancel.jsp");
		   //RequestDispatcher rd = request.getRequestDispatcher("/pages/pmworkOrder/updatecancelWo.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PMTimeSummaryReport.jsp");
			request.setAttribute("machineId", mchId);
			request.setAttribute("cellId", cellId);
			request.setAttribute("sectionId", sectId);
			request.setAttribute("factId", factId);
			request.setAttribute("fromMonth", fromMonth);
			request.setAttribute("toMonth", toMonth);
			request.setAttribute("relatedTo", relTo);
			request.setAttribute("filterString", filterString);
			request.setAttribute("asseqpWise", asseqpWise);
			request.setAttribute("hdncmbTradeId", tradeId);
			request.setAttribute("hdncmbJobType", jobType);
			//String sql = "select CNFM_KEYID  from "+TableNames.TBL_ADM_TL_CONFIGURATIONMST +" where CNFM_CODE = 'ENTCODETRUE'";
			rd.forward(request, response); 
	   }
	else if( action.equals("pmtimesummaryReport_getCol.pmsmrRpt") )	   {
		   //String type= request.getParameter("type");
		   CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",true);	
		   commonFilter.setEqpmnt("EQP");
		   if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setMonwise("Y");				  
		   }
		   else{
		 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 	  }
		   }

		   List<String []> monthlyPlanList  = pMActivityRptService.getTimeSummary(commonFilter);
		  
		   JSONObject monthlyPlanData = fillJqGrid(monthlyPlanList,request,0,0,commonFilter);
		   httpSession.setAttribute("monthlyPlanData", monthlyPlanData);						
		   PrintWriter out = response.getWriter();
		   JSONObject jsonObject = getTableModel(monthlyPlanList,commonFilter.getEqpmnt());				 

		   httpSession.removeAttribute("monthCalColModel");
		   httpSession.setAttribute("monthCalColModel", jsonObject);
		   out.println(jsonObject);
	   }
	   else if( action.equals("pmtimesummaryReport_getData.pmsmrRpt") )
	   {
		   try
			{
			   	 PrintWriter out = response.getWriter();
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");
				 String type = request.getParameter("type");
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-3).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(1).substring(3,11));
					  commonFilter.setMonwise("Y");
			 	 }
				 JSONObject jsonObject = (JSONObject) httpSession.getAttribute("monthlyPlanData"); 
				 if(page.equals("1") && jsonObject != null){
	        		 
				 }else
	        	 {				
					 CommonFunctions.debugMsg("Type==="+type);
	        		 List<String[]> monPlan  = pMActivityRptService.getTimeSummary(commonFilter);
	        		 jsonObject = fillJqGrid(monPlan,request,0,0,commonFilter); 
	        		
	        	 }				
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("MonthlyPlanCommonFilter");
	  			 httpSession.setAttribute("MonthlyPlanCommonFilter", commonFilter);
	  			httpSession.removeAttribute("monthlyPlanData");
		    }
			catch(Exception e)
			{
				 CommonFunctions.debugMsg(e.getMessage());
			}
	   }
	   else if( action.equals("pmtimesummaryReport_getExcel.pmsmrRpt") )
	   {
		   CommonFunctions.debugMsg("In side 01 the Get Excel");
		   try
			{			   
				CommonFilter commonFilter = populateCommonFilter(request,"MonthlyPlanCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//commonFilter.setToRow(null);
				String fromMonth = commonFilter.getFromMonth();
				String toMonth = commonFilter.getToMonth();				
				String month1   = fromMonth +"  -  "+ toMonth ;
				String fromdate = commonFilter.getFromDate();
				String todate = commonFilter.getToDate();
				String date   = fromdate +"  -  "+ todate ;				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("monthCalColModel");
				
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = pMActivityRptService.PMTimeSummaryExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "PMActivityCompleted", format);

				
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   }
	
	////
	
	   else if (action.equals("PMComplianceSummary_input.pmsmrRpt")) {	
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ActivitySummaryReport.jsp"); 
			rd.forward(request, response); 
		} 			
		else if (action.equals("PMComplianceSummary_getCol.pmsmrRpt")) {
	PrintWriter out = response.getWriter();
	String jobType=request.getParameter("jobType");
	String fromMonth=request.getParameter("fromMonth");
	String toMonth=request.getParameter("toMonth");
	CommonFunctions.debugMsg(toMonth+"in side get col jobType "+fromMonth);
	if((jobType.equals( "PRM" ))|| (jobType.equals( "CBM" ))|| (jobType.equals("TBM" ))){
		
		 jobType=jobType;
  	 
    }
 else {
	 jobType="ALL";
 }
	CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",true);
	//CommonFilter commonFilter = new CommonFilter();
	if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
			&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals("Y")))) 
	{
		commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-4)).substring(3, 11));
		commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));		
		commonFilter.setMonwise("Y");
	} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
			&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals(" ")))) 
	{
		commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-3)));
		commonFilter.setToDate(CommonFunctions.getDate());
	}
	if(fromMonth!=null && toMonth !=null){
	commonFilter.setFromMonth(fromMonth);
	commonFilter.setToMonth(toMonth);
	}
	CommonFunctions.debugMsg(toMonth+"..... fromMonth,, " +fromMonth);
	String weekly = null;
	List<String[]> impVscomList = pMActivityRptService.getPMComplianceSummary(commonFilter,jobType);
	for(String[] arr:impVscomList) 
	{
		CommonFunctions.debugMsg("Array "+Arrays.toString(arr));
	}
	JSONObject impVscomListData = UIUtils.convertToJqGridTableObject(impVscomList,request,0,0,commonFilter.getTotalRecordCnt()+2);
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
	GridColModel gridColModel = new GridColModel();			 
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setTableHeight(250);
	
	String [] colHeaderHead = impVscomList.get(0);	
	String [] colHeader = impVscomList.get(1);	
	String [] colHeader1 = impVscomList.get(2);
	String [] colHeader2 = impVscomList.get(3);
	List<String[]> headers = new ArrayList<String[]>();
	//String caption=FilterValues.getHeader( commonFilter.getDrillCaption());		
	
	
	if(!"Y" .equals(commonFilter.getMonthly()))
		   weekly = "Y";			
		
	if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
		CommonFunctions.debugMsg("weekly.."+weekly);
		gridColModel.setHeaderNum(1);
		gridColModel.setHeaderNum(2);
		gridColModel.setHeaderNum(3);
		headers.add(colHeader);
		headers.add(colHeader1);
		headers.add(colHeader2);
	}
	else{
		gridColModel.setHeaderNum(1);
		headers.add(colHeader);	
	}
	
	JSONObject jsonObject= UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			 
	jsonObject.set("tableHeight", "70%%");
	jsonObject.set("tableWidth", "108%%");
	jsonObject.put("data", impVscomListData);
	httpSession.removeAttribute("SummaryCommonfilter");
	httpSession.setAttribute("SummaryCommonfilter",commonFilter);///PmComplainceSummaryColModel
	httpSession.setAttribute("PmComplainceSummaryColModel",jsonObject);
	request.setAttribute("fromMonth", commonFilter.getFromMonth());
	request.setAttribute("toMonth", commonFilter.getToMonth());
	System.out.println("Table Model:" + jsonObject);
	out.println(jsonObject);
} 

else if (action.equals("PMComplianceSummary_getData.pmsmrRpt")) {
	try {
		 httpSession = request.getSession(false);
		//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
		String jobType=request.getParameter("jobType");
	   if(jobType.equals("PRM")||jobType.equals("CBM") || jobType.equals("TBM") ){
	
		   
		   jobType=jobType;
	    	 
	      }
	   else {
		   jobType="ALL";
	   }
		CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
		
		List<String[]> impVscomList = pMActivityRptService.getPMComplianceSummary(commonFilter,jobType);
		
		JSONObject listToJsonObject = new JSONObject();
		
		CommonFunctions.debugMsg("size of dash..."+impVscomList.size());
		if (impVscomList != null && impVscomList.size() > 3){
		if(!"Y" .equals(commonFilter.getMonthly()))
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,4,0);
		else
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,4,0);
		}
		/*httpSession.removeAttribute("PmComplaince");
		httpSession.setAttribute("PmComplaince",commonFilter);*/
		
		PrintWriter out = response.getWriter();
		out.println(listToJsonObject.toString());
	}

	catch (Exception e) {
		System.out.println("error " + e.getMessage());
	}
}
		else if( action.equals("PMComplianceSummary_getExcel.pmsmrRpt"))
		{					
			  
		 httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
		//CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
		String jobType=request.getParameter("jobType");
		   if(jobType.equals("PRM")||jobType.equals("CBM") || jobType.equals("TBM") ){
		
			   
			   jobType=jobType;
		    	 
		      }
		   else {
			   jobType="ALL";
		   }
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("PmComplainceSummaryColModel");
		String rptName =pMActivityRptService.getReportName(commonFilter);
		//tableModel.put("title", "PM Compliance Report Of - "+rptName);
		String format = ExcelUtils.getFormat(request);
		Workbook wb = pMActivityRptService.PMCompSummaryReportExportExcel(commonFilter,tableModel,format,jobType);
		commonFilter.setFromRow(tmpFromRow);

		ExcelUtils.writeToResponse(response, wb, "PMComplianceSummary", format);
		}
		else if(action.equals("chart.pmsmrRpt")){
			CommonFunctions.debugMsg("inside the chart action" +action);
			String jobType=request.getParameter("jobType");
			String graphType=request.getParameter("graphType");
			String rowid = request.getParameter("rowId");
			CommonFilter chrtCommonFilter = new  CommonFilter();
			
CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
FilterValues.getPmActivityRelated(request, chrtCommonFilter);//(request, chrtCommonFilter);


			
			FilterValues.getCommonFilters(request, chrtCommonFilter) ;
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			
			String month1   = fromMonth +" - "+ toMonth ;
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date   = fromdate +"  -  "+ todate ;
			String title = null;
			
			if(rowid.equals("1")){
				title="PM Activity Summary Trend Week 1 of "+month1;
			}
			else if(rowid.equals("2")){
				title="PM Activity Summary Trend Week 2 of "+month1;
			}
			else if(rowid.equals("3")){
				title="PM Activity Summary Trend Week 3 of "+month1;
			}else if(rowid.equals("4")){
				title="PM Activity Summary Trend Week 4 of "+month1;
			}
			else {
				title="PM Activity Summary Trend "+month1;
			}
String forDashboard = request.getParameter("dashboard");

if(!UIUtils.isValidKeyId(rowid))

FilterValues.getCommonFilters(request, chrtCommonFilter) ;
List<String[]> graphData  =  pMActivityRptService.getActivityGraph(commonFilter,jobType);

//List<String[]> pmbdList  = pMVsBDService.getBDVSPMVSABNGridReport(commonFilter);
JSONObject chartObj = null;
String chartType = request.getParameter("chType");
if( chartType == null){
	chartObj = processActivityLineChart(graphData,commonFilter,rowid,title,jobType,graphType);
	String dashboardIdnt = request.getParameter("dashboardIdent");
	if( UIUtils.isValidKeyId(dashboardIdnt));
	//	chartObj.set("dashboardIdent", dashboardIdnt);
}
PrintWriter out = response.getWriter();
out.print(chartObj);
out.close();
	
}
	
	//-------------------------------------------------
	else if (action.equals("PMplanAndActual_input.pmsmrRpt")) {	
		RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PMPlanAndActualActivity.jsp"); 
		rd.forward(request, response); 
	} 			
	else if (action.equals("PMplanAndActual_getCol.pmsmrRpt")) {
PrintWriter out = response.getWriter();

CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",true);
//CommonFilter commonFilter = new CommonFilter();
if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
		&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals("Y")))) 
{
	commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-5)).substring(3, 11));
	commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));		
	commonFilter.setMonwise("Y");
} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
		&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals(" ")))) 
{
	commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-3)));
	commonFilter.setToDate(CommonFunctions.getDate());
}
String weekly = null;
List<String[]> impVscomList = pMActivityRptService.PMplanvsactualSummaryReport(commonFilter);
JSONObject impVscomListData = UIUtils.convertToJqGridTableObject(impVscomList,request,0,0,commonFilter.getTotalRecordCnt()+2);
JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
GridColModel gridColModel = new GridColModel();			 
jqGridTableModel.setTableButton(true);
jqGridTableModel.setRowNumbers(true);
jqGridTableModel.setTableHeight(250);

String [] colHeaderHead = impVscomList.get(0);	
String [] colHeader = impVscomList.get(1);	
String [] colHeader1 = impVscomList.get(2);
//String [] colHeader2 = impVscomList.get(3);
List<String[]> headers = new ArrayList<String[]>();
//String caption=FilterValues.getHeader( commonFilter.getDrillCaption());		


if(!"Y" .equals(commonFilter.getMonthly()))
	   weekly = "Y";			
	
if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
	CommonFunctions.debugMsg("weekly.."+weekly);
	gridColModel.setHeaderNum(1);
	gridColModel.setHeaderNum(2);
	///gridColModel.setHeaderNum(3);
	headers.add(colHeader);
	headers.add(colHeader1);
	//headers.add(colHeader2);
}
else{
	gridColModel.setHeaderNum(1);
	headers.add(colHeader);	
}

JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			 
jsonObject.set("tableHeight", "70%%");
jsonObject.set("tableWidth", "108%%");
jsonObject.put("data", impVscomListData);
httpSession.removeAttribute("PmComplainceSummaryColModel");
httpSession.setAttribute("PmComplainceSummaryColModel",jsonObject);
request.setAttribute("fromMonth", commonFilter.getFromMonth());
request.setAttribute("toMonth", commonFilter.getToMonth());
System.out.println("Table Model:" + jsonObject);
out.println(jsonObject);  
} 

else if (action.equals("PMplanAndActual_getData.pmsmrRpt")) {
try {
	 httpSession = request.getSession(false);
	CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
	String jobType=request.getParameter("jobType");
   /*if(jobType.equals("PRM")||jobType.equals("CBM") || jobType.equals("TBM") ){

	   
	   jobType=jobType;
    	 
      }
   else {
	   jobType="ALL";
   }*/
	//CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
	
	List<String[]> impVscomList = pMActivityRptService.PMplanvsactualSummaryReport(commonFilter);
	
	JSONObject listToJsonObject = new JSONObject();
	
	CommonFunctions.debugMsg("size of dash..."+impVscomList.size());
	if (impVscomList != null && impVscomList.size() > 3){
	if(!"Y" .equals(commonFilter.getMonthly()))
		listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,3,0);
	else
		listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request,3,0);
	}
	httpSession.removeAttribute("PmComplaince");
	httpSession.setAttribute("PmComplaince",commonFilter);
	
	PrintWriter out = response.getWriter();
	out.println(listToJsonObject.toString());
}

catch (Exception e) {
	System.out.println("error " + e.getMessage());
}
}
	else if( action.equals("PMplanAndActual_getExcel.pmsmrRpt"))
	{					
		
	 httpSession = request.getSession(true);
	//CommonFilter commonFilter = new CommonFilter();//populateCommonFilter(request,"PmComplaince",false);
	CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
	String tmpFromRow = commonFilter.getFromRow();
	commonFilter.setFromRow(null);
	JSONObject tableModel = (JSONObject)httpSession.getAttribute("PmComplainceSummaryColModel");
	String rptName =pMActivityRptService.getReportName(commonFilter);
	CommonFunctions.debugMsg(tableModel+"commonFilter.getFromMonth()" +commonFilter.getFromMonth());
	tableModel.put("title", "PM Planed vs Actual Report Of - "+commonFilter.getFromMonth()+" - "+commonFilter.getToMonth());
	String format = ExcelUtils.getFormat(request);
	Workbook wb = pMActivityRptService.PMplanvsactualSummaryReportExportExcel(commonFilter,tableModel,format);
	commonFilter.setFromRow(tmpFromRow);

	ExcelUtils.writeToResponse(response, wb, "PMComplianceSummary", format);
	}
	else if(action.equals("chartplanvsactual.pmsmrRpt")){
		CommonFunctions.debugMsg("inside the chart action");
		String graphType=request.getParameter("graphType");
		String rowid = request.getParameter("rowId");
		CommonFilter chrtCommonFilter = new  CommonFilter();
		
CommonFilter commonFilter = populateCommonFilter(request,"SummaryCommonfilter",false);
FilterValues.getPmActivityRelated(request, chrtCommonFilter);//(request, chrtCommonFilter);


		
		FilterValues.getCommonFilters(request, chrtCommonFilter) ;
		String fromMonth = commonFilter.getFromMonth();
		String toMonth = commonFilter.getToMonth();
		
		String month1   = fromMonth +" - "+ toMonth ;
		String fromdate = commonFilter.getFromDate();
		String todate = commonFilter.getToDate();
		String date   = fromdate +"  -  "+ todate ;
		String title = null;
		
		if(rowid.equals("1")){
			title="PM Activity Summary Trend Week 1 of "+month1;
		}
		else if(rowid.equals("2")){
			title="PM Activity Summary Trend Week 2 of "+month1;
		}
		else if(rowid.equals("3")){
			title="PM Activity Summary Trend Week 3 of "+month1;
		}else if(rowid.equals("4")){
			title="PM Activity Summary Trend Week 4 of "+month1;
		}
		else {
			title="PM Activity Summary Trend "+month1;
		}
String forDashboard = request.getParameter("dashboard");

if(!UIUtils.isValidKeyId(rowid))

FilterValues.getCommonFilters(request, chrtCommonFilter) ;
List<String[]> graphData  =  pMActivityRptService.getPlanVsActualActivityGraph(commonFilter);

//List<String[]> pmbdList  = pMVsBDService.getBDVSPMVSABNGridReport(commonFilter);
JSONObject chartObj = null;
String chartType = request.getParameter("chType");
if( chartType == null){
chartObj = processplanvsactualActivityLineChart(graphData,commonFilter,rowid,title,graphType);
String dashboardIdnt = request.getParameter("dashboardIdent");
if( UIUtils.isValidKeyId(dashboardIdnt));
//	chartObj.set("dashboardIdent", dashboardIdnt);
}
PrintWriter out = response.getWriter();
out.print(chartObj);
out.close();

}
	
	
		
}
	private  JSONObject fillJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, CommonFilter comFilter)
	{
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		long totalRecords = comFilter.getTotalRecordCnt();
		
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
	
		JSONObject tableDataObject = new JSONObject();
		
		 CommonFunctions.debugMsg(" ****Assembly Wise "+comFilter.getEqpmnt());
		 int flgAE = 0;
		 if("ASS".equals(comFilter.getEqpmnt()))
			 flgAE=13;
		 else if("EQP".equals(comFilter.getEqpmnt()))
			 flgAE=11;
			 
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		tableDataObject.put("records", totalRecords); //total records
		
		JSONArray rowArr = new JSONArray(); 
	
		int rowId = rows*(page-1);
		int rowStartFlag = rowId + 3; //rowId + 2
		
        for( String [] datasRow : dataArrayList)
		{
        	JSONObject rowObj =new JSONObject();
			JSONArray cell=new JSONArray();
			if( rowId >= rowStartFlag )
	        {	
	        	rowObj.put("id",rowId -rowStart +1);
        		for( int i = colStart ;i < datasRow.length-1; i++)
	        	{	
        			if(datasRow[i].equals("1")){
        			}
        			if(i>flgAE)
	        		 datasRow[i] = datasRow[i];//.replace("2","2").replace("3","3").replace("4","4").replace("0"," ");	
        			 cell.put(  ( datasRow[i] != null ? datasRow[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", "").replace("(", "").replace(")", ""):" ") );
	        	
	        	}
        		rowObj.put("cell",cell);
    	    	rowArr.put(rowObj);
	        }
        	rowId++;
		} 
        tableDataObject.put("rows", rowArr);
        return tableDataObject;
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonFunctions.debugMsg(commonFilter.getEqpmnt());
		return commonFilter;
	}
	private JSONObject getTableModel(List<String[]> headers, String assEqpWise)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] monthArr = headers.get(0);
		String [] weekArr = headers.get(1);
		String [] actArr = headers.get(2);
		int colFlag = monthArr.length -1;
		int colLength=0;
		CommonFunctions.debugMsg("Count Array "+colFlag);
		String [] colHeader = new String[ colFlag];
		String [] colHeader2 = new String[ colFlag];
		String [] colHeader3 = new String[ colFlag];
		//String [] colHeader4 = new String[ colFlag];
		String [] colHeader5 = new String[ colFlag];
		if("ASS".equals(assEqpWise)){
		colHeader3[0] = colHeader2[0] = " ";
		colHeader3[1] = colHeader2[1] = "Location_id";
		colHeader3[2] = colHeader2[2] = "Unit_id";
		colHeader3[3] = colHeader2[3] = "Section_id";
		colHeader3[4] = colHeader2[4] = "Line_id";
		colHeader3[5] = colHeader2[5] = "Equipment_id";
		colHeader3[6] = colHeader2[6] = "Assembly_id";
		colHeader3[7] = colHeader2[7] = "Location";
		colHeader3[8] = colHeader2[8] = "Factory";
		colHeader3[9] = colHeader2[9] = "Section";
		colHeader3[10] = colHeader2[10] = "Line";
		colHeader3[11] = colHeader2[11] = "Equipment Name";
		colHeader3[12] = colHeader2[12] = "Equipment No.";
		colHeader3[13] = colHeader2[13] = "Assembly";
		colHeader[14]="";
		}
		else if("EQP".equals(assEqpWise))
		{
			colHeader5[0] = colHeader3[0] = " ";
			colHeader5[1] = colHeader3[1] = "Location_id";
			colHeader5[2] = colHeader3[2] = "Unit_id";
			colHeader5[3] = colHeader3[3] = "Section_id";
			colHeader5[4] = colHeader3[4] = "Line_id";
			colHeader5[5] = colHeader3[5] = "Equipment_id";
			colHeader5[6] = colHeader3[6] = "Location";
			colHeader5[7] = colHeader3[7] = "Factory";
			colHeader5[8] = colHeader3[8] = "Section";
			colHeader5[9] = colHeader3[9] = "Line";
			colHeader5[10] = colHeader3[10] = "Equipment Name";
			colHeader5[11] = colHeader3[11] = "Equipment No.";
		}
		colHeader[0] = colHeader[1]=colHeader[2] = colHeader[3]=colHeader[4] =colHeader[5]=colHeader[6] = colHeader[7]=colHeader[8] = colHeader[9]=colHeader[10] = colHeader[11]= colHeader[12]= "";//colHeader[13]= colHeader[14]="";
		List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
		JqGridColModel jqGridColModel =getColModel("sno",40,"left");
		if("ASS".equals(assEqpWise))
		{
		//JqGridColModel jqGridColModel =getColModel("sno",40,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);
		jqGridColModel =getColModel("location_id",80,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("factory_id",60,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("line_id",150,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("cell_id",150,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("machine_id",150,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("assembly_id",80,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("Location",80,"left");
		//jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("factory",60,"left");
		//jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("line",150,"left");
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("cell",150,"left");
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("machinename",150,"left");
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel =getColModel("machineno",80,"left");
		jqGridColModelList.add(jqGridColModel);
		jqGridColModel =getColModel("assembly",80,"left");
		jqGridColModelList.add(jqGridColModel);
		colLength=14;
		}
		else if("EQP".equals(assEqpWise))
		{
			
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);
			jqGridColModel =getColModel("location_id",80,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("factory_id",60,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("line_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("cell_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("machine_id",150,"left");
			jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("Location",80,"left");
			//jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("factory",60,"left");
			//jqGridColModel.setHidden(true);
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("line",150,"left");
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("cell",150,"left");
			jqGridColModelList.add(jqGridColModel);	
			jqGridColModel =getColModel("machinename",150,"left");
			jqGridColModelList.add(jqGridColModel);
			jqGridColModel =getColModel("machineno",80,"left");
			jqGridColModelList.add(jqGridColModel);
			colLength=12;
		}
	//	jqGridTableModel.getRowHeaders().add(colHeader);
	
		for(int i =colLength; i < monthArr.length-1; i++)
		{
			

			if(i!= monthArr.length-2 || i != monthArr.length-3)
				jqGridColModelList.add(getColModel(weekArr[i]+monthArr[i],40,"center"));
			else
			{
				jqGridColModel =getColModel("Key"+i,80,"left");
				jqGridColModel.setHidden(true);
			}
			colHeader[i] = " ";//monthArr[i];
			colHeader2[i] = weekArr[i];
			colHeader3[i] = actArr[i];				
		}
		
		jqGridTableModel.setTableButton(true);		 
		//jqGridTableModel.setTableHeight(290);
		//jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowNumbers(true);
	
		jqGridTableModel.setColModel(jqGridColModelList);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader3);		
	
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableWidth","110%%");
		tableModel.set("tableHeight","79%%");

		return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		//jqGridColModel.setFormatter("actionformatter_"+colIndex);			
		return jqGridColModel;		
	}


private JSONObject getTableModel(List<String[]> headers, String weekly,String caption) {
System.out.println("Enter get col model"+headers.size());
CommonFilter commonFilter = new CommonFilter();
CommonFunctions.debugMsg("caption...."+commonFilter.getDrillCaption());
String[] colHeader = headers.get(0);	
String[] colHeaders = headers.get(1);	
//String[] colHeader1 = headers.get(2);
List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
JqGridTableModel jqGridTableModel = new JqGridTableModel();
String[] emptyrow = new String[colHeader.length]; 
colHeaders[2]=caption;
colHeader[2]=caption;
for(int i=0;i<=colHeader.length-1;i++)
{
	emptyrow[i]="";
	System.out.println(colHeader[i]);
	System.out.println(colHeaders[i]);
	//System.out.println(colHeader1[i]);
}
CommonFunctions.debugMsg("test..."+colHeader.toString());	
if(UIUtils.isValidKeyId(weekly) && weekly.equals("Y")){
	CommonFunctions.debugMsg("weekly.."+weekly);
	jqGridTableModel.getRowHeaders().add(emptyrow);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.getRowHeaders().add(colHeaders);
}
else{
	jqGridTableModel.getRowHeaders().add(colHeader);	
}
//jqGridTableModel.getRowHeaders().add(emptyrow);
		
//jqGridTableModel.getRowHeaders().add(colHeaders);
jqGridTableModel.setTableButton(true);
jqGridTableModel.setRowNumbers(true);
jqGridTableModel.setTableHeight(250);

for (int i = 0; i <= colHeader.length-1; i++) {
	JqGridColModel jqGridColModel = new JqGridColModel();
	
	if(UIUtils.isValidKeyId(colHeader[i]))
	{
		if(i>=0 && i<=1)
		{
			
			CommonFunctions.debugMsg( i +" !!!! "+colHeaders[i]);
			jqGridColModelList.add(getColModel(colHeaders[i],200,"left"));
			jqGridColModel.setHidden(true);
		}
		if(UIUtils.isValidKeyId(weekly)){
			if(i==2 ){
				CommonFunctions.debugMsg("colHeader["+i+"] "+colHeader[i]+"~~~"+colHeaders[i]+"~~~"+emptyrow[i]);
				
				jqGridColModelList.add(getColModel(emptyrow[i],200,"center"));
				jqGridColModelList.add(getColModel(colHeader[i],200,"center"));
				jqGridColModelList.add(getColModel(colHeaders[i],200,"center"));
				//jqGridColModel.setWidth(120);
				jqGridColModel.setIndex(emptyrow[i].replaceAll(" ", "") + i);
			    jqGridColModel.setName(emptyrow[i].replaceAll(" ", "") + i);
			    
			}
			if(i>4 && i < colHeader.length-1){
				jqGridColModelList.add(getColModel(colHeaders[i]+colHeader[i],20,"center"));
			   
			}
			if(i>2 && i < colHeader.length-1){
				CommonFunctions.debugMsg( i +" !!!! "+colHeaders[i]);
				jqGridColModel.setWidth(40);
			}
			CommonFunctions.debugMsg("inside weekly   :"+weekly);
			if(i ==  colHeader.length-1){
				jqGridColModel.setHidden(true);
			}
		}
		/*if(i>2 && i < colHeader.length-2)
			jqGridColModelList.add(getColModel(colHeaders[i]+colHeader[i],40,"center"));*/
		//jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
		//jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
	}
	
	if (i == 0 || i == 1 ) {
		jqGridColModel.setIndex("rnum"+i );
		jqGridColModel.setName("rnum"+i );
		jqGridColModel.setKey(true);
		jqGridColModel.setHidden(true);
		if(i==1){
			jqGridColModel.setIndex("keyId" );
			jqGridColModel.setName("keyId" );
			
		}
	}
	
	//jqGridColModel.setWidth(400);
	jqGridColModel.setAlign("left");
	jqGridColModel.setEditable(false);

	if (i == 0 || i == 1 ) {
		jqGridColModel.setHidden(true);
		jqGridColModel.setKey(true);
	} else if (i > 2) {
		// jqGridColModel.setHidden(false);
		//jqGridColModel.setKey(true);
		jqGridColModel.setAlign("right");
		//jqGridColModel.setWidth(100);
	}

/*	else if (i == 16) {
		CommonFunctions.debugMsg("Length of col:" + colHeader.length);
		jqGridColModel.setHidden(true);
		jqGridColModel.setKey(true);
	}*/

	jqGridTableModel.getColModel().add(jqGridColModel);
}
JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
CommonFunctions.debugMsg("colmodel:" + tableModel);
return tableModel;
 }
private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew,boolean drilFlag){
	/*HttpSession httpSession = request.getSession(true);		
	CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
	if( commonFilter != null && ! createNew ){
		FilterValues.setPaginationParams(request,commonFilter);
	}	
	else{
		commonFilter =  new CommonFilter();
		commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
		commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
	
		commonFilter.setViewClick('Y');
		httpSession.removeAttribute(beanIdentifier);
		httpSession.setAttribute(beanIdentifier, commonFilter);
	}
	
	return commonFilter;*/
	
	HttpSession httpSession = request.getSession(false);
	
	CommonFunctions.debugMsg("createNew...."+drilFlag);
	CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
	if( commonFilter != null && ! createNew ){
		FilterValues.setPaginationParams(request,commonFilter);
		//if(drilFlag )
			FilterValues.getCommonFilters(request, commonFilter);
	}	
	else{
		
		commonFilter =  new CommonFilter();
		commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
		commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
		commonFilter.setViewClick('Y');
		httpSession.removeAttribute(beanIdentifier);
		httpSession.setAttribute(beanIdentifier, commonFilter);
	}
	
	return commonFilter;
}
private JSONObject processActivityLineChart(List<String[]> pmbdList,CommonFilter commonFilter,String rowid, String title,String jobType,String graphType){
CommonFunctions.debugMsg("222222222222adfsrfa"+rowid);
if( pmbdList == null || pmbdList.size() <= 1  )
	return null;

ChartOptionBean lineChart =  new ChartOptionBean();
List<String> xAxisCategory = new ArrayList<String>();
List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

//String title = "Preventive Maintenance Vs Breakdown Vs Abnormality ";

int rowno = Integer.parseInt(rowid);
String [] header =  pmbdList.get(2);
String [] header2 =  pmbdList.get(3);

String [] month =  pmbdList.get(1);
String [] data =  pmbdList.get(rowno+3);
CommonFunctions.debugMsg("rowId................"+rowno+3);
CommonFunctions.debugMsg("header 0................"+header[0]);
CommonFunctions.debugMsg("header 1................"+header[1]);
CommonFunctions.debugMsg("header 2................"+header[2]);
CommonFunctions.debugMsg("header 2................"+month[1]);
String prevMonth = null;

String subTitle = ""; 
ChartSeries timeSeriescbmact = new ChartSeries();
ChartSeries timeSeriescbm = new ChartSeries();
List<Double> planCbmData = new ArrayList<Double>();
List<Double> actualCbmData = new ArrayList<Double>();

ChartSeries timeSeriestbmact = new ChartSeries();
ChartSeries timeSeriestbm = new ChartSeries();
List<Double> planTbmData = new ArrayList<Double>();
List<Double> actualTbmData = new ArrayList<Double>();

ChartSeries timeSeriesprmact = new ChartSeries();
ChartSeries timeSeriesprm = new ChartSeries();
List<Double> planPrmData = new ArrayList<Double>();
List<Double> actualPrmData = new ArrayList<Double>();



if(graphType.equals("bar")){
if (jobType.equals("ALL")){
for( int i = 1;i < header.length-1;i++ ){  
	CommonFunctions.debugMsg("header in side if " +header[i]);
	if(header[i].contains("PRM") ){
		if(header2[i].contains("Plan") ){
			//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
	
			planPrmData.add(Double.parseDouble(data[i]));
		}
		else if(header2[i].contains("Actual") ){
		
			actualPrmData.add(Double.parseDouble(data[i]));
			}
	}
		if(header[i].contains("CBM") ){
			if(header2[i].contains("Plan") ){
				//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
		
				planCbmData.add(Double.parseDouble(data[i]));
			}
			else if(header2[i].contains("Actual") ){
			
			actualCbmData.add(Double.parseDouble(data[i]));
				}
		}
			if(header[i].contains("TBM") ){
				if(header2[i].contains("Plan") ){
					//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
			
					planTbmData.add(Double.parseDouble(data[i]));
				}
				else if(header2[i].contains("Actual") ){
				
					actualTbmData.add(Double.parseDouble(data[i]));
					}
	if( prevMonth == null || ! month[i].equals(prevMonth) ){
		xAxisCategory.add(month[i]);
		
		}	prevMonth = month[i];
	 }
   }
	
}
else if(jobType.equals("PRM")){
	for( int i = 1;i < header.length-1;i++ ){
		CommonFunctions.debugMsg("header in side if " +header[i]);
		if(header[i].contains("PRM") ){
			if(header2[i].contains("Plan") ){
				//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
				planTbmData.add(Double.parseDouble(data[i]));
			}
			else if(header2[i].contains("Actual") ){
				actualPrmData.add(Double.parseDouble(data[i]));
				}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
				
				}	prevMonth = month[i];
			
		}
	}
}
else if(jobType.equals("CBM")){
	for( int i = 1;i < header.length-1;i++ ){
		CommonFunctions.debugMsg("header in side if " +header[i]);
		if(header[i].contains("CBM") ){
			if(header2[i].contains("Plan") ){
				//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
				planCbmData.add(Double.parseDouble(data[i]));
			}
			else if(header2[i].contains("Actual") ){
				actualCbmData.add(Double.parseDouble(data[i]));
				}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
				
				}	prevMonth = month[i];
			
		}
	}
}
else if(jobType.equals("TBM")){
	for( int i = 1;i < header.length-1;i++ ){
		CommonFunctions.debugMsg("header in side if " +header[i]);
		if(header[i].contains("TBM") ){
			if(header2[i].contains("Plan") ){
				//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
				planTbmData.add(Double.parseDouble(data[i]));
			}
			else if(header2[i].contains("Actual") ){
				actualTbmData.add(Double.parseDouble(data[i]));
				}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
				
				}	prevMonth = month[i];
			
		}
	}
}
if(planCbmData.size() > 0 ){
	timeSeriescbm.setData(planCbmData);
	timeSeriescbm.setType(ChartTypes.COLUMN);
	timeSeriescbm.setName("Cbm Plan");
	chartSeriesList.add(timeSeriescbm);
	
	
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Completed Activity");
	chartYAxis.add(yAxis);
}
if(actualCbmData.size() > 0 ){
	timeSeriescbmact.setData(actualCbmData);
	timeSeriescbmact.setType(ChartTypes.COLUMN);
	timeSeriescbmact.setName("Cbm Actual");
	chartSeriesList.add(timeSeriescbmact);
	
	
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	yAxis.getTitle().setText("Completed Activity");
	chartYAxis.add(yAxis);
}



if( planPrmData.size() > 0){
	timeSeriesprm.setData(planPrmData);
	timeSeriesprm.setType(ChartTypes.COLUMN);
	timeSeriesprm.setName("Prm Plan");

	chartSeriesList.add(timeSeriesprm);
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Actual");
	chartYAxis.add(yAxis);

}

if( actualPrmData.size() > 0){
	timeSeriesprmact.setData(actualPrmData);
	timeSeriesprmact.setType(ChartTypes.COLUMN);
	timeSeriesprmact.setName("Prm Actual");

	chartSeriesList.add(timeSeriesprmact);
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Actual");
	chartYAxis.add(yAxis);

}

if( planTbmData.size() > 0){
	timeSeriestbm.setData(planTbmData);
	timeSeriestbm.setType(ChartTypes.COLUMN);
	timeSeriestbm.setName("Tbm Plan");

	chartSeriesList.add(timeSeriestbm);
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Actual");
	chartYAxis.add(yAxis);

}

if( actualTbmData.size() > 0){
	timeSeriestbmact.setData(actualTbmData);
	timeSeriestbmact.setType(ChartTypes.COLUMN);
	timeSeriestbmact.setName("Tbm Actual");

	chartSeriesList.add(timeSeriestbmact);
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Actual");
	chartYAxis.add(yAxis);

}

}
else{
	
	if (jobType.equals("ALL")){
		for( int i = 1;i < header.length-1;i++ ){  
			CommonFunctions.debugMsg("header in side if " +header[i]);
			if(header[i].contains("PRM") ){
				if(header2[i].contains("Percentage") ){
			
					planPrmData.add(Double.parseDouble(data[i]));
				}
				
			}
				if(header[i].contains("CBM") ){
					if(header2[i].contains("Percentage") ){
						//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
						if(data[i].isEmpty()){
							planCbmData.add(Double.parseDouble("0"));

						}
						else
						planCbmData.add(Double.parseDouble(data[i]));
						//planCbmData.add(Double.parseDouble(data[i]));
					}
					
				}
					if(header[i].contains("TBM") ){
						if(header2[i].contains("Percentage") ){
							//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
							if(data[i].isEmpty()){
								planCbmData.add(Double.parseDouble("0"));

							}
							else
							planCbmData.add(Double.parseDouble(data[i]));
						//}
							//planTbmData.add(Double.parseDouble(data[i]));
						}
					}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
				
				}	prevMonth = month[i];
			 }
		   }
			
		
		else if(jobType.equals("PRM")){
			for( int i = 1;i < header.length-1;i++ ){
				CommonFunctions.debugMsg("header in side if " +header[i]);
				if(header[i].contains("PRM") ){
					if(header2[i].contains("Percentage") ){
						//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
						planPrmData.add(Double.parseDouble(data[i]));
					}
					
					if( prevMonth == null || ! month[i].equals(prevMonth) ){
						xAxisCategory.add(month[i]);
						
						}	prevMonth = month[i];
					
				}
			}
		}
		else if(jobType.equals("CBM")){
			for( int i = 1;i < header.length-1;i++ ){
				CommonFunctions.debugMsg(data[i]+"header in side if " +planCbmData.size());
				if(header[i].contains("CBM") ){
					if(header2[i].contains("Percentage") ){
						//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
						if(data[i].isEmpty()){
							planCbmData.add(Double.parseDouble("0"));

						}
						else
						planCbmData.add(Double.parseDouble(data[i]));
					}
					
					if( prevMonth == null || ! month[i].equals(prevMonth) ){
						xAxisCategory.add(month[i]);
						
						}	prevMonth = month[i];
					
				}
			}
		}
		else if(jobType.equals("TBM")){
			for( int i = 1;i < header.length-1;i++ ){
				CommonFunctions.debugMsg("header in side if " +header[i]);
				if(header[i].contains("TBM") ){
					if(header2[i].contains("Percentage") ){
						//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
						planTbmData.add(Double.parseDouble(data[i]));
					}
					
					if( prevMonth == null || ! month[i].equals(prevMonth) ){
						xAxisCategory.add(month[i]);
						
						}	prevMonth = month[i];
				}
			}
		}
		
		if(planCbmData.size() > 0 ){
			timeSeriescbm.setData(planCbmData);
			timeSeriescbm.setType(ChartTypes.SPLINE);
			timeSeriescbm.setName("CBM");
			chartSeriesList.add(timeSeriescbm);
			
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			//yAxis.getTitle().setText("Completed Activity");
			chartYAxis.add(yAxis);
		}


		if( planPrmData.size() > 0){
			timeSeriesprm.setData(planPrmData);
			timeSeriesprm.setType(ChartTypes.SPLINE);
			timeSeriesprm.setName("PRM");
			chartSeriesList.add(timeSeriesprm);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			//yAxis.getTitle().setText("Actual");
			chartYAxis.add(yAxis);

		}

		

		if( planTbmData.size() > 0){
			timeSeriestbm.setData(planTbmData);
			timeSeriestbm.setType(ChartTypes.SPLINE);
			timeSeriestbm.setName("TBM");

			chartSeriesList.add(timeSeriestbm);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			//yAxis.getTitle().setText("Actual");
			chartYAxis.add(yAxis);

		}

		

	
	
}
	

ChartXAxis xaxis = new ChartXAxis();
if(commonFilter.getMonwise().equals("Y"))
	xaxis.getTitle().setText("Month");
else
	xaxis.getTitle().setText("Date");
lineChart.getxAxis().setTitle(xaxis.getTitle());
return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);

}
//-------------------------
private JSONObject processplanvsactualActivityLineChart(List<String[]> pmbdList,CommonFilter commonFilter,String rowid, String title,String graphType){
CommonFunctions.debugMsg("222222222222adfsrfa"+rowid);
if( pmbdList == null || pmbdList.size() <= 1  )
	return null;

ChartOptionBean lineChart =  new ChartOptionBean();
List<String> xAxisCategory = new ArrayList<String>();
List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

//String title = "Preventive Maintenance Vs Breakdown Vs Abnormality ";

int rowno = Integer.parseInt(rowid);
String [] header =  pmbdList.get(1);
String [] header2 =  pmbdList.get(2);

String [] month =  pmbdList.get(1);
String [] data =  pmbdList.get(rowno+2);
CommonFunctions.debugMsg("rowId................"+rowno+3);
CommonFunctions.debugMsg("header 0................"+header[0]);
CommonFunctions.debugMsg("header 1................"+header[1]);
CommonFunctions.debugMsg("header 2................"+header[2]);
CommonFunctions.debugMsg("header 2................"+month[1]);
String prevMonth = null;

String subTitle = ""; 
ChartSeries timeSeriesact = new ChartSeries();
ChartSeries timeSeriespln = new ChartSeries();
List<Double> planData = new ArrayList<Double>();
List<Double> actualData = new ArrayList<Double>();



if(graphType.equals("bar")){

for( int i = 1;i < header.length-1;i++ ){  
	CommonFunctions.debugMsg("header in side if " +header[i]);

		if(header2[i].contains("Plan") ){
			//xAxisCategory.add(data[i].concat(" ").concat(month[i]));
	
			planData.add(Double.parseDouble(data[i]));
		}
		else if(header2[i].contains("Actual") ){
		
			actualData.add(Double.parseDouble(data[i]));
			}
	
		
	if( prevMonth == null || ! month[i].equals(prevMonth) ){
		xAxisCategory.add(month[i]);
		
		}	prevMonth = month[i];
	 }
  

if(planData.size() > 0 ){
	timeSeriespln.setData(planData);
	timeSeriespln.setType(ChartTypes.COLUMN);
	timeSeriespln.setName("Plan");
	chartSeriesList.add(timeSeriespln);
	
	
	ChartYAxis yAxis = new ChartYAxis(); 
	yAxis.setMin(0);
	//yAxis.getTitle().setText("Completed Activity");
	chartYAxis.add(yAxis);
}
if(actualData.size() > 0 ){
	timeSeriesact.setData(actualData);
	timeSeriesact.setType(ChartTypes.COLUMN);
	timeSeriesact.setName("Actual");
	chartSeriesList.add(timeSeriesact);
}

}
else{
	
	
		for( int i = 1;i < header.length-1;i++ ){  
			CommonFunctions.debugMsg("header in side if " +header[i]);
			
				if(header2[i].contains("Percentage") ){
			
					planData.add(Double.parseDouble(data[i]));
				}
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
					xAxisCategory.add(month[i]);
					
					}	prevMonth = month[i];
			
		}
		
		if(planData.size() > 0 ){
			timeSeriespln.setData(planData);
			timeSeriespln.setType(ChartTypes.SPLINE);
			timeSeriespln.setName("Percentage");
			chartSeriesList.add(timeSeriespln);
			
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			//yAxis.getTitle().setText("Completed Activity");
			chartYAxis.add(yAxis);
		}


	
	
}
	

ChartXAxis xaxis = new ChartXAxis();
if(commonFilter.getMonwise().equals("Y"))
	xaxis.getTitle().setText("Month");
else
	xaxis.getTitle().setText("Date");
lineChart.getxAxis().setTitle(xaxis.getTitle());
return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);

}

}



