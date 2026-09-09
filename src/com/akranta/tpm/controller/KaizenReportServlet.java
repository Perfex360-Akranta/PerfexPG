package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.KaizenReportService;
import com.akranta.tpm.service.impl.KaizenReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class KaizenReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	KaizenReportService kaizenReportService;
	DashboardService dashboardService;
	private static final String commonFilterIden = "impVsCompcommonFilter";

	public KaizenReportServlet() {
	/*	try {
			KaizenReportService = new KaizenReportServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
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

	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (!UIUtils.checkUserSession(request, response))
			return;
		String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		try {
			
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			HttpSession httpSession=request.getSession(false);
			kaizenReportService = (KaizenReportServiceImpl)UIUtils.getServiceObject(request,"KaizenReportServiceImpl");
			kaizenReportService.KaizenReportServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		
		
		if (action.equals("filterXmlkznPerPersonPerMonth_input.kaz")) {
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response,"/tiles/xml/ImprovementVsCompleted.xml");
		} 
		else if(action.equals("chartKaizenPerPerson.kaz"))
		{
			processChartKaizenPerPerson(request,response);
			
		} 
		else if(action.equals("chartKaizenNosPerPerson.kaz"))
		{
			processChartKaizenNosPerPerson(request,response);
			
		} 
		
			
		else if (action.equals("kznPerPersonPerMonth_input.kaz")||action.equals("TrnhrsPerPersonPerMonth_input.kaz")) {
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");
			String type =request.getParameter("type");
			CommonMessage.debugMsg("Type in input"+type);
			String types=request.getParameter("types");
			CommonMessage.debugMsg("Types is input"+types);
			request.setAttribute("hiddenCompId",comp);
			
		    String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			request.setAttribute("type",type);
			request.setAttribute("types",types);
			UIUtils.forwardRequest(request, response,"/pages/Reports/kaizenPerPersons.jsp");
		} 
		
		else if (action.equals("TrnNosPerPersonPerMonth_input.kaz")){
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");
			//String type =request.getParameter("type");
			//CommonMessage.debugMsg("Type in input"+type);
			//String types=request.getParameter("types");
			//CommonMessage.debugMsg("Types is input"+types);
			request.setAttribute("hiddenCompId",comp);
			
		    String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			//request.setAttribute("type",type);
			//request.setAttribute("types",types);
			UIUtils.forwardRequest(request, response,"/pages/Reports/kaizenNosPerPersons.jsp");
		}
		
else if(action.equals("kaizenSummaryReport_input.kaz")){
			
			CommonMessage.debugMsg("kaizenSummaryReport_input.kaz");
			UIUtils.forwardRequest(request, response,"/pages/KaizenSummaryReport.jsp");
			
		}else if(action.equals("kaizenSummaryReport_getCol.kaz")){
			
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSummaryCommonFilter",true);
			commonFilter.setIsGetCol("Y");
			List<String[]> KaizenGridData  = kaizenReportService.getKaizenSummaryGridData(commonFilter);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = KaizenGridData.get(1);
			String[] colHeaderCond = KaizenGridData.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "108%%");
			httpSession.removeAttribute("KaizenSummaryColModel");
			httpSession.setAttribute("KaizenSummaryColModel", colModel);
			
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			
			out.println(colModel);
			
			
		}else if(action.equals("kaizenSummaryReport_getData.kaz")){
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter=populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
			commonFilter.setIsGetCol("N");
			List<String[]> KaizenGridData = kaizenReportService.getKaizenSummaryGridData(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt() ); 
			out.print(dataJson);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			
		}else if(action.equals("kaizenSummaryReport_getExcel.kaz")){
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSummaryCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Kaizen Summary Report - ");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = kaizenReportService.getKaizenSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
			
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenSummaryReport", format);
		}
		
		
		/*****************Suggestion Accepted & Kaizen Implememented************************************************/
		else if(action.equals("SuggestionAccKaizenImp_input.kaz")){
		    	CommonMessage.debugMsg("Suggestion Accepted");
		    	String comp = request.getParameter("cmbCompid");
				String locn=request.getParameter("cmbLocnid");
				String fact = request.getParameter("cmbFactid");
				String sect = request.getParameter("cmbSectid");
				String fromMonth=request.getParameter("dtFromMonth");
				String toMonth=request.getParameter("dtToMonth");
				String toDate = request.getParameter("dtToDate");
				String fromDate = request.getParameter("dtFromDate");
				CommonMessage.debugMsg("The fromDate"+fromDate);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				request.setAttribute("hiddenCompId",comp);
				request.setAttribute("hiddenLocnId",locn);
				request.setAttribute("hiddenFactId",fact);
				request.setAttribute("hiddenSectId",sect);
				request.setAttribute("hiddenFromMonth",fromMonth);
				request.setAttribute("hiddenToMonth",toMonth);
				request.setAttribute("hdnfromdate", fromDate);
				request.setAttribute("hdntodate", toDate);
		    	UIUtils.forwardRequest(request,response,"/pages/SuggAccKaizenImp.jsp");
		}
		else if(action.equals("SuggestionAccKaizenImp_getCol.kaz")){
			CommonMessage.debugMsg("Suggestion Accepted Imp getCol");
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}
			
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			ComboFilter jhKaizenCategoryObj=new ComboFilter();
			if( jhKaizenCategoryObj != null)
				jhKaizenCategoryObj.setId(jhKaizenCategory);
			commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
			populateCommonFilter(request,commonFilterIden,true);
			CommonMessage.debugMsg(" Inside Getcol Action commonFilter " +commonFilter+" "+request.getParameter("cmbKaizenCategory")+"jhKaizenCategoryObj.getId(jhKaizenCategory)"+commonFilter.getJHKaizenCategory().getId());
			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getOPLandKaizen(request, commonFilter);
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			commonFilter.setIsGetCol("Y");
			List<String[]> SuggAccVsKznCom= kaizenReportService.getSuggVsKaizenData(commonFilter);
					
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(SuggAccVsKznCom, request, 1, 2);
			JSONObject jsonObject = getTableModel(SuggAccVsKznCom,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}
		else if (action.equals("SuggestionAccKaizenImp_getData.kaz")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				ComboFilter jhKaizenCategoryObj=new ComboFilter();
				if( jhKaizenCategoryObj != null)
					jhKaizenCategoryObj.setId(jhKaizenCategory);
				commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
				CommonMessage.debugMsg(" Inside GetData Action commonFilter " +commonFilter.getFlid()  );
				response.setContentType("text/html");
				httpSession.getAttribute("commonFilterIden");
				commonFilter.setIsGetCol("N");
				List<String[]> SuggAccVsKznCom= kaizenReportService.getSuggVsKaizenData(commonFilter);				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+SuggAccVsKznCom.size());
				if (SuggAccVsKznCom != null && SuggAccVsKznCom.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(SuggAccVsKznCom, request, 2, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}
			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		else if(action.equals("SuggestionAccKaizenImp_getExcel.kaz")){
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow("1");
			JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
			
			
			tableModel.put("title", "Suggestion Accepted Kaizen  Report-"+commonFilter.getDrillCaption()+" Wide ");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = kaizenReportService.getSuggestionAccKaizenExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "SuggestionAcceptedVsKaizenImplemented", format);
		}
		else if(action.equals("chart.kaz"))
		{
			processChart1(request,response);
			
		}
		
		else if(action.equals("barChart.kaz"))
		{
			CommonMessage.debugMsg("barchart");
			processBarChart1(request, response);
			
		}
	
		/*******************kaizen Trends Report************************************/
		else if(action.equals("kaizenBenefittrendReport_input.kaz")){
		 	
		  UIUtils.forwardRequest(request, response,"pages/KaizenBenefitTrendReport.jsp");	
		}
		else if(action.equals("kaizenBenefittrendReport_getCol.kaz")){
		   HttpSession httpSession=request.getSession(false);
			String firstClick =request.getParameter("firstClick");
			CommonFilter  commonFilter  ;
			commonFilter = new CommonFilter(); 
			
				CommonMessage.debugMsg("getcol:" +firstClick);
			 
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				 commonFilter =(CommonFilter) httpSession.getAttribute("KaizenSummaryCommonFilter");
			 }	
			if(commonFilter==null)
			 commonFilter = new CommonFilter(); 
			 
			 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			 commonFilter= 	FilterValues.getSafty(request, commonFilter);
			 
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 }
			 httpSession.removeAttribute("KaizenSummaryCommonFilter");
			 httpSession.setAttribute("KaizenSummaryCommonFilter",commonFilter);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 commonFilter.setIsGetCol("Y");
				List<String[]> KaizenBenefitTrdData=kaizenReportService.getKaizenBenefitData(commonFilter);
			 
			
			 JSONObject colModel = getTableModel_KAIZEN(KaizenBenefitTrdData,commonFilter);
			 colModel.set("tableHeight", "71%%");
			 colModel.set("tableWidth", "107%%");
			 
			 httpSession.removeAttribute("KaizenSummaryColModel");
			 httpSession.setAttribute("KaizenSummaryColModel", colModel);
			 CommonMessage.debugMsg(colModel);
			 out.println(colModel);
			 out.close();  

			
		}
		else if(action.equals("kaizenBenefittrendReport_getData.kaz")){
			PrintWriter out = response.getWriter();
			  HttpSession httpSession=request.getSession(false);
			try
			{
				UIUtils.displayRequestParamsValue(request);
				 
			   CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("KaizenSummaryCommonFilter");
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getSafty(request, commonFilter);
				 JSONObject jsonObject = new JSONObject();
				 commonFilter.setIsGetCol("N");
				 List<String[]> KaizenBenefitTrdData=kaizenReportService.getKaizenBenefitData(commonFilter);	
	     		 CommonMessage.debugMsg("customerComplaintTrendList.size("+KaizenBenefitTrdData.size()+")");
	     		 CommonMessage.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
	     		 //	if (KaizenBenefitTrdData.size() >1) 
	     		 	jsonObject = UIUtils.convertToJqGridTableObject(KaizenBenefitTrdData,request,2,0,KaizenBenefitTrdData.size());
	     		 	
	     		 	 CommonMessage.debugMsg(jsonObject);
	     		 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			
		   }
			catch(Exception e)
			{
				
			}
			
		}
		else if(action.equals("kaizenBenefittrendReport_getExcel.kaz")){
			
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSummaryCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Kaizen Benefit Trend");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = kaizenReportService.getKaizenBenefitExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenBenefitTrend", format);
		}
		else if(action.equals("KaizenBenefitCountchart.kaz")){
			
			ProcessBenefitBarChart(request,response);
		}
		
		/**************DMT wise Kaizen Synopsis Report************/
		
		else if(action.equals("kaizenSynopsisReport_input.kaz")){
			
			CommonMessage.debugMsg("kaizenSynopsisReport_input.kaz");
			UIUtils.forwardRequest(request, response,"/pages/KaizenSynopsisReport.jsp");
			
		}else if(action.equals("kaizenSynopsisReport_getCol.kaz")){
			
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSummaryCommonFilter",true);
			
			List<String[]> KaizenGridData  = kaizenReportService.getKaizenSynopsisData(commonFilter);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = KaizenGridData.get(1);
			String[] colHeaderCond = KaizenGridData.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("KaizenSummaryColModel");
			httpSession.setAttribute("KaizenSummaryColModel", colModel);
			
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			
			out.println(colModel);
			
			
		}else if(action.equals("kaizenSynopsisReport_getData.kaz")){
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter=populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
			List<String[]> KaizenGridData = kaizenReportService.getKaizenSynopsisData(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt() );
			out.print(dataJson);
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			
		}else if(action.equals("kaizenSynopsisReport_getExcel.kaz")){
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSummaryCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Kaizen Summary Report - ");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = kaizenReportService.getKaizenSynopsisDataExportExcel(commonFilter,tblJSONObj,format);
			
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "kaizenSynopsisReport", format);
			
		}

		/*************************************/
		
		else if(action.equals("kaizenSuggestionSummaryReport_input.kaz")){
			
			CommonMessage.debugMsg("kaizenSuggestionSummaryReport_input.kaz");
			UIUtils.forwardRequest(request, response,"/pages/KaizenSuggestionSummaryReport.jsp");
			
		}else if(action.equals("kaizenSuggestionSummaryReport_getCol.kaz")){			
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",true);			
			List<String[]> kaizenSuggestionGridData  = kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			gridColModel.setHeaderNum(1);
			String[] colHeader = kaizenSuggestionGridData.get(1); //sriram
			String[] colHeaderCond = kaizenSuggestionGridData.get(0); //sriram
			List<String[]> headers = new ArrayList<String[]>();			
			headers.add(colHeader);			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("KaizenSuggestionSummaryColModel");
			httpSession.setAttribute("KaizenSuggestionSummaryColModel", colModel);			
			httpSession.removeAttribute("KaizenSuggestionSummaryCommonFilter");
			httpSession.setAttribute("KaizenSuggestionSummaryCommonFilter", commonFilter);			
			out.println(colModel);			
		}else if(action.equals("kaizenSuggestionSummaryReport_getData.kaz")){
			PrintWriter out = response.getWriter();	
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter=populateCommonFilter(request, "KaizenSuggestionSummaryCommonFilter", false);
			List<String[]> KaizenGridData = kaizenReportService.getKaizenSuggestionSummaryGridData(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData,request, 2, 0,commonFilter.getTotalRecordCnt() ); //sriram
			out.print(dataJson);
			httpSession.removeAttribute("KaizenSuggestionSummaryCommonFilter");
			httpSession.setAttribute("KaizenSuggestionSummaryCommonFilter", commonFilter);			
		}else if(action.equals("kaizenSuggestionSummaryReport_getExcel.kaz")){
			HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"KaizenSuggestionSummaryCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenSuggestionSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Kaizen Suggestion Summary Report  ");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = kaizenReportService.getKaizenSuggestionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);			
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenSuggestionSummaryReport", format);		
		}
		else if(action.equals("TrnNosPerPersonPerMonth_getCol.kaz"))
		{
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
				
			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}
						
			if (commonFilter == null)
				commonFilter = new CommonFilter();
			commonFilter=populateCommonFilter(request,commonFilterIden,true);
			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getSafty(request, commonFilter);
			
			if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
		
			//String type=request.getParameter("type");
			//CommonMessage.debugMsg("The Type:::::"+type);
			//String types=request.getParameter("types");
			//CommonMessage.debugMsg("Types is input"+types);

			//if(UIUtils.isValidKeyId(types))
			//	commonFilter.setRepeatedAbn(types);
			/*if(UIUtils.isValidKeyId(types))
				commonFilter.setTrarId(types);
			*/
           
			List<String[]> impVscomList = kaizenReportService.getAllKaizenNosPerPerson(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}
		else if(action.equals("TrnNosPerPersonPerMonth_getData.kaz"))
		{
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());				
				//String type=request.getParameter("type");
				//String types=request.getParameter("types");
			  //  CommonMessage.debugMsg("Types is input"+types);


				//if(UIUtils.isValidKeyId(types))
				//	commonFilter.setAbnViewType(types);
				/*if(UIUtils.isValidKeyId(types))
					commonFilter.setTrarId(types);
				*/
				List<String[]> impVscomList = kaizenReportService.getAllKaizenNosPerPerson(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 3, 0);
				httpSession.removeAttribute(commonFilterIden);
				httpSession.setAttribute(commonFilterIden, commonFilter);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
			
		}
		
		else if (action.equals("kznPerPersonPerMonth_getCol.kaz")||action.equals("TrnhrsPerPersonPerMonth_getCol.kaz")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = populateCommonFilter(request, commonFilterIden, false);
		    if (commonFilter == null) {
		        commonFilter = populateCommonFilter(request, commonFilterIden, true);
		    }

		    FilterValues.getCommonFilters(request, commonFilter);
		    FilterValues.getSafty(request, commonFilter);
//
//			CommonFilter commonFilter = new CommonFilter();
//			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
//				 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
//			}
//						
//			if (commonFilter == null)
//				commonFilter = new CommonFilter();
//			commonFilter=populateCommonFilter(request,commonFilterIden,true);
//			httpSession.removeAttribute(commonFilterIden);
//			httpSession.setAttribute(commonFilterIden, commonFilter);
//			FilterValues.getCommonFilters(request, commonFilter);
//			FilterValues.getSafty(request, commonFilter);
			
			if (Constants.pgPassNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
			{
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
			commonFilter.setFlid(flid);
		
			String type=request.getParameter("type");
			CommonMessage.debugMsg("The Type:::::"+type);
			String types=request.getParameter("types");
			CommonMessage.debugMsg("Types is input"+types);

			if(UIUtils.isValidKeyId(type))
				commonFilter.setAbnViewType(type);
			/*if(UIUtils.isValidKeyId(types))
				commonFilter.setTrarId(types);
			*/
			commonFilter.setIsGetCol("Y");
			List<String[]> impVscomList = kaizenReportService.getAllKaizenPerPerson(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			CommonMessage.debugMsg(commonFilter.getDrillCaption() +" commonFilter.getDrillCaption()");
			commonFilter.setDrillCaption("COMP");
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("kznPerPersonPerMonth_getData.kaz")||action.equals("TrnhrsPerPersonPerMonth_getData.kaz")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				response.setContentType("text/html");
				//httpSession.getAttribute(commonFilterIden);
				if (commonFilter == null) {
		            commonFilter = populateCommonFilter(request, commonFilterIden, true);
		        }
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());				
				String type=request.getParameter("type");
				String types=request.getParameter("types");
				String flid=request.getParameter("flid");
				commonFilter.setFlid(flid);
			    CommonMessage.debugMsg(flid+"Types is input"+types);


				if(UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);
				/*if(UIUtils.isValidKeyId(types))
					commonFilter.setTrarId(types);
				*/
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = kaizenReportService.getAllKaizenPerPerson(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				httpSession.removeAttribute(commonFilterIden);
				httpSession.setAttribute(commonFilterIden, commonFilter);
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("kznPerPersonPerMonth_getExcel.kaz")||action.equals("TrnhrsPerPersonPerMonth_getExcel.kaz"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		
		String type=request.getParameter("type");
		
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
		
		String title=null;
		
		if(UIUtils.isValidKeyId(type))
			commonFilter.setAbnViewType(type);
		
		if(UIUtils.isValidKeyId(type)){
			tableModel.put("title", " Training hrs Per Person Report");
		     title="TraininghrsPerPersonPerMonth";
		}	
		else
		{
		    tableModel.put("title", " Kaizen Per Person Report");
		    title="ImprovementVsCompleted";
		}
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = kaizenReportService.perPersonKaizenExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, title, format);
		
		}
		else if(action.equals("Incidentchart.kaz"))
		{
			processChart(request,response);
			
		}
	}
	private JSONObject getTableModel(List<String[]> headers,String caption) {
		CommonMessage.debugMsg("Enter get col model...."+caption);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(0);  //sriram
		String[] colHeader1 = headers.get(1);  //sriram
		colHeader[3] = "DATE";
		if(caption.equals("Company")){
			caption="COMP";
		}
		if(caption.equals("Location")){
			caption="LOCN";
		}
		if(caption.equals("Factory")){
			caption="PBU";
		}
		if(caption.equals("Factory")){
			caption="PBU";
		}else if(caption.equals("Section")){
			caption="DMT";
		}else if(caption.equals("Line")){
			caption="JH";
		}
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonMessage.debugMsg("test..."+colHeader.length);
		for (int i = 2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;  
		String headerSql = "'SELECT ";
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 3) {
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}

			else if (i == 16) {
				CommonMessage.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		tableModel.set("tableHeight", "67%%");
		
		CommonMessage.debugMsg("colmodel:" + tableModel);
		return tableModel;
	}
	
	private void ProcessBenefitBarChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("KaizenSummaryCommonFilter");
		
		String forDashboard = request.getParameter("dashboard");
		String keyid=request.getParameter("rowid");
		String rowId=request.getParameter("rownum");
		CommonFilter chartCommonFilter = new CommonFilter(); 

		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("KaizenSummaryCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	
			
		}
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		if(chartCommonFilter.getRowTotal()==null)
		chartCommonFilter.setRowTotal('N');
		List<String[]> KaizenBenefitList=kaizenReportService.KaizenBenefitListGraph(chartCommonFilter,rowId);
		List<String[]> counts=transposeListArr1(KaizenBenefitList);
		JSONObject chartObj = null;
		if(KaizenBenefitList != null && KaizenBenefitList.size() > 0)
		{
			


String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChartKaizenBenefit(lcnname,KaizenBenefitList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}			
	}

	private JSONObject processBarChartKaizenBenefit(String titlename,List<String[]> kaizenBenefitList,CommonFilter commonFilter) throws  Exception{

		if( kaizenBenefitList == null || kaizenBenefitList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
         
		   StringBuilder date=new StringBuilder();	
					if(commonFilter.getMonwise().equals("Y"))
					{
						date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
					}
					else
					date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		String[] header =  kaizenBenefitList.get(3);
		String[] month =  kaizenBenefitList.get(2);
		String[] data =  kaizenBenefitList.get(4);
     
		 
		//String drillLevel = FilterValues.getDrillHeader(data[0]);
		//CommonMessage.debugMsg("The DrillLevel"+drillLevel);
         
		StringBuilder title=new StringBuilder(titlename+"-Kaizen Benefit Amt Vs Approved Amt Report - ").append(date);
		//String title = "Kaizen Benefit Amt Vs Verified Amt Report";
		
		
		int rSize = kaizenBenefitList.size()-3; //3
		int cSize = header.length -2;   //2
	//	CommonMessage.debugMsg("rSize"+rSize);
		//CommonMessage.debugMsg("cSize"+cSize);

		String subTitle="";
		Double [][] grData = new Double[rSize][cSize];
	//	CommonMessage.debugMsg("grData"+grData);
		for( int i =2;i <header.length;i++ ){
			for (int j = 3; j< kaizenBenefitList.size();j++) {
				data  =  kaizenBenefitList.get(j);
			//	CommonMessage.debugMsg("The Data IS:::"+data);
					grData[j-3][i-2] = Double.parseDouble(data[i]);
				//	CommonMessage.debugMsg("The GRData Length"+grData.length);
			}
		
				xAxisCategory.add(month[i]);
		}
		for(int k=0;k<grData.length;k++){
			 ChartSeries timeSeries = new ChartSeries();
			 timeSeries.setData(Arrays.asList(grData[k]));
			 timeSeries.setType(ChartTypes.COLUMN);
			 timeSeries.setName(kaizenBenefitList.get(k+3)[1]);  //3
			// CommonMessage.debugMsg("The Kaizen Benefit List::::::"+kaizenBenefitList.get(k+3)[1]);
			 chartSeriesList.add(timeSeries);			
		}
		 ChartYAxis yAxis = new ChartYAxis(); 
		 yAxis.setMin(0);
		 chartYAxis.add(yAxis);
		 yAxis.getTitle().setText("Rs");
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}			
	
   private List<String[]> transposeListArr1(List<String[]> dataList)
		{
			CommonMessage.debugMsg("INSIDE transposeListArr1"+dataList.size());
			if( dataList.size() <=0 ) return null;
			CommonMessage.debugMsg("INSIDE transposeListArr2"+dataList.size());
			List<String[]> transposeList = new ArrayList<String[]>();		
			for( int i =0; i<dataList.size(); i++)
			{	
				String [] tRow = new String [ dataList.get(0).length];
				CommonMessage.debugMsg("tRow [ j ] : "+tRow.length);
				for (int j=0; j<dataList.get(0).length;j++)
				{
					tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
					CommonMessage.debugMsg("The TRow:::"+tRow);
				}
				transposeList.add(tRow);
			}
			return transposeList;
		}
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		//FilterValues.getCommonFilters(request, commonFilter) ;
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			
		}
		CommonFilter chartCommonFilter = new CommonFilter(); 
		
		CommonMessage.debugMsg(" chartCommonFilter :: chartCommonFilter ");
		CommonMessage.debugMsg(" commonFilter :: commonFilter ");
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('N');
		
		List<String[]> improvementVsCompletedList  = kaizenReportService.getAllKaizenPerPerson (chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcn=dashboardService.Functionallocn(flids);
			
			chartObj = processLineChart(lcn,improvementVsCompletedList,chartCommonFilter);
			//UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processLineChart(String tn,List<String[]> improvementVsCompletedList,CommonFilter commonFilter){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		CommonMessage.debugMsg("check on it");
		String title = tn+"-Kaizen Per Persons Per Month ";
		int index=0;
		if(improvementVsCompletedList!=null)
			index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(2);
		String[] month =  improvementVsCompletedList.get(1);
		String[] data =  improvementVsCompletedList.get(improvementVsCompletedList.size()-2);
		String prevMonth = null;
		
		String subTitle = data[2];
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			CommonMessage.debugMsg("data[i] "+data[i]);
			if(header[i].contains("Implemented") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("COMPLETED") ){
				completedData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		
		}
		
		
		if(identifiedData.size() > 0 )
		{
			timeSeries.setData(identifiedData);
			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);

			timeSeries.setName("Implemented");
			chartSeriesList.add(timeSeries);			
			//yAxis.getTitle().setText("Implemented");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);
			
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Implemented");
			chartSeriesList.add(intstanceSeries);			
			/*ChartYAxis yAxis = new ChartYAxis(); 			
			if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}
			
			chartYAxis.add(yAxis);*/
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}
	private void processChartKaizenPerPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		
		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type=request.getParameter("type");

		CommonMessage.debugMsg(commonFilterIden+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterIden,true);
		}

		CommonMessage.debugMsg(commonFilter.getFlid()+" commonFilter.getFlid()  "+commonFilter.getFlid());
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		}
		else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel)) 
			chartCommonFilter.setFirstLevel(FirstLevel);

		
		if(UIUtils.isValidKeyId(type)){
			commonFilter.setAbnViewType(type);
			chartCommonFilter.setAbnViewType(type);
		}
		

		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);
		
		
		//commonFilter.setDrillFlag('f');
		List<String[]> improvementVsCompletedList  = kaizenReportService.getAllKaizenPerPerson(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);	
			
			chartObj = processLineChartIncident(lcnname,improvementVsCompletedList,chartCommonFilter,type);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}

	
	private JSONObject processLineChartIncident(String titlename,List<String[]> improvementVsCompletedList,CommonFilter commonFilter,String type){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		//chartCommonFilter.setAbnViewType(type);
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title=null;
		
		CommonMessage.debugMsg(" Inside Checking :: type"+type);
		 
        StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());	
		
		if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			title=titlename+ "-Training Hrs Per Person Per Month";
		else
			title=( titlename+"-Kaizen Per Person Per Month"+"-"+date);
		
		int index=0;
		if(improvementVsCompletedList!=null)
			index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(1); //elumalai
		String[] month =  improvementVsCompletedList.get(0); //elumalai
		String[] data =  improvementVsCompletedList.get(2); //elumalai
		String prevMonth = null;
		
		String subTitle = data[3];
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("Implemented") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}else if(header[i].contains("Trai") && UIUtils.isValidKeyId(commonFilter.getAbnViewType())){ 
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("COMPLETED") ){
				completedData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		
		}
		
		
		if(identifiedData.size() > 0 )
		{
			timeSeries.setData(identifiedData);
			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType())){
			timeSeries.setName("Training Hours Per Person Per Month");
			chartSeriesList.add(timeSeries);
			}
			else{
				timeSeries.setName("Implemented");
				chartSeriesList.add(timeSeries);
			}
			
			//yAxis.getTitle().setText("Implemented");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			yAxis.getTitle().setText("Training hrs. per Person per Month");
			else
			yAxis.getTitle().setText("Kaizen per Person per Month");	
			chartYAxis.add(yAxis);
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			if (commonFilter.getType().equals("BAR"))
				timeSeries.setType(ChartTypes.BAR);
			else
				timeSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Completed");
			chartSeriesList.add(intstanceSeries);			
			//ChartYAxis yAxis = new ChartYAxis(); 			
			/*if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}*/
			
			//chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}
	
	
	private void processChartKaizenNosPerPerson(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		String flids=CommonFunctions.getLoginFlid(request);
		String lcnname=dashboardService.Functionallocn(flids);
		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type=request.getParameter("type");

		CommonMessage.debugMsg(commonFilterIden+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterIden,true);
		}

		CommonMessage.debugMsg(commonFilter.getFlid()+" commonFilter.getFlid()  "+commonFilter.getFlid());
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		}
		else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel)) 
			chartCommonFilter.setFirstLevel(FirstLevel);

		
		if(UIUtils.isValidKeyId(type)){
			commonFilter.setAbnViewType(type);
			chartCommonFilter.setAbnViewType(type);
		}
		

		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);
		
		
		//commonFilter.setDrillFlag('f');
		List<String[]> improvementVsCompletedList  = kaizenReportService.getAllKaizenNosPerPerson(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	chartObj = processLineChartIncident1(lcnname,improvementVsCompletedList,chartCommonFilter,type);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}

	
	private JSONObject processLineChartIncident1(String titlename,List<String[]> improvementVsCompletedList,CommonFilter commonFilter,String type){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		//chartCommonFilter.setAbnViewType(type);
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title=null;
		
		CommonMessage.debugMsg(" Inside Checking :: type"+type);
		 
        StringBuilder date = new StringBuilder();
		if(commonFilter.getRowTotal()==null)
			commonFilter.setRowTotal('N');
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());	
		
		if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			title= titlename+"-Training Hours Per Person Per Month";
		else
			title= titlename+"-Training Nos Per Person Per Month"+"-"+date;
		
		int index=0;
		if(improvementVsCompletedList!=null)
			index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(2);
		String[] month =  improvementVsCompletedList.get(1);
		String[] data =  improvementVsCompletedList.get(3);
		String prevMonth = null;
		
		String subTitle = data[3];
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("Implemented") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}else if(header[i].contains("Trai") && UIUtils.isValidKeyId(commonFilter.getAbnViewType())){ 
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("COMPLETED") ){
				completedData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		
		}
		
		
		if(identifiedData.size() > 0 )
		{
			timeSeries.setData(identifiedData);
			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);
		 	 
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			{
				timeSeries.setName("Training hours per person per month");
				chartSeriesList.add(timeSeries);
			}
			else{
				timeSeries.setName("Training Nos per person per month");
				chartSeriesList.add(timeSeries);
			}
			
				
			//yAxis.getTitle().setText("Implemented");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			yAxis.getTitle().setText("Training hours per Person per Month");
			else
			yAxis.getTitle().setText("Numbers");	
			chartYAxis.add(yAxis);
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			if (commonFilter.getType().equals("BAR"))
				timeSeries.setType(ChartTypes.BAR);
			else
				timeSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Completed");
			chartSeriesList.add(intstanceSeries);			
			//ChartYAxis yAxis = new ChartYAxis(); 			
			/*if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}*/
			
			//chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		
	}
	
	private void processChart1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
		//CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		//FilterValues.getCommonFilters(request, commonFilter) ;
		CommonFilter commonFilter = null;
		String forDashboard = request.getParameter("dashboard");
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = new  CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			
		}
		
		
		String dashboardtype = request.getParameter("EMPLILLAR");
		
		CommonMessage.debugMsg(" :: dashboardtype :: Servlet ::"+dashboardtype);
		
		if(UIUtils.isValidKeyId(dashboardtype))
			commonFilter.setType(dashboardtype);
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		CommonMessage.debugMsg(" flid " + commonFilter.getFlid() );
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> SuggAccVsKznCom= kaizenReportService.getSuggVsKaizenData(chartCommonFilter);				

		JSONObject chartObj = null;
		if(SuggAccVsKznCom != null && SuggAccVsKznCom.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			
			chartObj = processLineChart1(lcnname,SuggAccVsKznCom,chartCommonFilter);
			//UIUtils.dashBoardSetChartObject(request,chartObj);
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	
	
	private JSONObject processLineChart1(String titlename,List<String[]> SuggAccVsKznComList,CommonFilter commonFilter){
		if( SuggAccVsKznComList == null || SuggAccVsKznComList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String[] data =  SuggAccVsKznComList.get(2);
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		String drillLevel =  FilterValues.getDrillHeader(data[2]);

		StringBuilder title = new StringBuilder( titlename+"-Suggestion Accepted Vs Kaizen Implemented Cumulative ").append( drillLevel).append( " Wide From ").append(date);
				
		
		
		//int index=0;
	//	if(improvementVsCompletedList!=null)
	//		index=improvementVsCompletedList.size()-1;
	
		//String[] header =  SuggAccVsKznComList.get(2);
		//String[] month =  SuggAccVsKznComList.get(1);
		
		String[] header =  SuggAccVsKznComList.get(1);
		String[] month =  SuggAccVsKznComList.get(0);
		
		String prevMonth = null;
		
		String subTitle = "";//data[2];
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("Suggestion Accepted") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("Kaizen Implemented") ){
				completedData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		
		}
		
		
		if(identifiedData.size() > 0 )
		{
			timeSeries.setData(identifiedData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Suggestion Accepted");
			chartSeriesList.add(timeSeries);			
			//yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
			
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Kaizen Implemented");
			chartSeriesList.add(intstanceSeries);			
			/*ChartYAxis yAxis = new ChartYAxis(); 			
			if( chartYAxis.size() > 0)
			{
				yAxis.setOpposite(true);
				intstanceSeries.setyAxis(1);
			}
			
			chartYAxis.add(yAxis);*/
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	private JSONObject getTableModel_KAIZEN(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);
		String[] emptyrow = new String[colHeader.length]; 	
	
		emptyrow [0] ="";
		//emptyrow [1] ="";
		//hgjknmllkjg
				
		for( int i = 1; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		jqGridTableModel.getRowHeaders().add(colHeader);			
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);			 
		  for(int i =0; i < colHeader.length; i++)
		{

			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			//jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			//if(i==0 || i == 1 || i == 2)
			if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i == 1 || i == 2)
			{
				jqGridColModel.setHidden(false);
				if(i==0)
					jqGridColModel.setKey(true);
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			}
			if(i == 3)
			{
				
				
				jqGridColModel.setWidth(125);
				jqGridColModel.setAlign("left");
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			}
			if(i>3)
			{
				jqGridColModel.setWidth(125);
				
				jqGridColModel.setAlign("right");
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+i);
			}		
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
   
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		/*String drFlg = request.getParameter("drillDown");
		char drillFlag = '-';
		if (UIUtils.isValidKeyId(drFlg))
			drillFlag = drFlg.trim().charAt(0);
		*/
		//commonFilter.setDrillFlag(drillFlag);
		//CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	private void processBarChart1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
		//CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		//FilterValues.getCommonFilters(request, commonFilter) ;
		CommonFilter commonFilter = null;
		String forDashboard = request.getParameter("dashboard");
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = new  CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			
		}
		
		
		String dashboardtype = request.getParameter("EMPLILLAR");
		
		CommonMessage.debugMsg(" :: dashboardtype :: Servlet ::"+dashboardtype);
		
		if(UIUtils.isValidKeyId(dashboardtype))
			commonFilter.setType(dashboardtype);
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		CommonMessage.debugMsg(" flid " + commonFilter.getFlid() );
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> SuggAccVsKznCom= kaizenReportService.getSuggVsKaizenData(chartCommonFilter);				

		JSONObject chartObj = null;
		if(SuggAccVsKznCom != null && SuggAccVsKznCom.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			
			//chartObj = processLineChart1(lcnname,SuggAccVsKznCom,chartCommonFilter);
		
			chartObj = processBarChartSuggAccVsKaizeImpl(lcnname,SuggAccVsKznCom,chartCommonFilter);
			//chartObj = processBarChartSuggAccVsKaizeImpl(lcnname,SuggAccVsKznCom,chartCommonFilter);
			//UIUtils.dashBoardSetChartObject(request,chartObj);
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processBarChartSuggAccVsKaizeImpl(String titlename,List<String[]> SuggAccVsKznCom,CommonFilter commonFilter) throws  Exception{

		 if(SuggAccVsKznCom == null || SuggAccVsKznCom.size() <= 2)
		        return null;

		    ChartOptionBean barChart = new ChartOptionBean();
		    List<String> xAxisCategory = new ArrayList<String>();
		    List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		    List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		    String[] data = SuggAccVsKznCom.get(2);

		    StringBuilder date = new StringBuilder();

		    if(commonFilter.getMonwise().equals("Y"))
		    {
		        date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		    }
		    else
		        date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());

		    String drillLevel = FilterValues.getDrillHeader(data[2]);

		    StringBuilder title = new StringBuilder(titlename + "-Suggestion Accepted Vs Kaizen Implemented ").append(drillLevel).append(" Wide From ").append(date);

		    //String[] header = SuggAccVsKznCom.get(2);
		    //String[] month = SuggAccVsKznCom.get(1);
		    
		    String[] header = SuggAccVsKznCom.get(1);
		    String[] month = SuggAccVsKznCom.get(0);//changed here


		    String prevMonth = null;

		    String subTitle = "";

		    ChartSeries suggestedSeries = new ChartSeries();
		    List<Double> identifiedData = new ArrayList<Double>();

		    ChartSeries kaizenSeries = new ChartSeries();
		    List<Double> completedData = new ArrayList<Double>();

		    for(int i = 4; i < header.length; i++){
		        if(header[i].contains("Suggestion Accepted")){
		            identifiedData.add(Double.parseDouble(data[i]));
		        }
		        else if(header[i].contains("Kaizen Implemented")){
		            completedData.add(Double.parseDouble(data[i]));
		        }
		        if(prevMonth == null || !month[i].equals(prevMonth)){
		            xAxisCategory.add(month[i]);
		        }
		        prevMonth = month[i];
		    }

		    if(identifiedData.size() > 0)
		    {
		        suggestedSeries.setData(identifiedData);
		        suggestedSeries.setType(ChartTypes.COLUMN); // Changed from SPLINE to COLUMN
		        suggestedSeries.setName("Suggestion Accepted");
		        chartSeriesList.add(suggestedSeries);
		        
		        ChartYAxis yAxis = new ChartYAxis();
		        yAxis.setMin(0);
		        yAxis.getTitle().setText("Numbers");
		        chartYAxis.add(yAxis);
		    }
		    
		    if(completedData.size() > 0){
		        kaizenSeries.setData(completedData);
		        kaizenSeries.setType(ChartTypes.COLUMN); // Changed from SPLINE to COLUMN
		        kaizenSeries.setName("Kaizen Implemented");
		        chartSeriesList.add(kaizenSeries);
		    }
		    
		    ChartXAxis xaxis = new ChartXAxis();
		    if(commonFilter.getMonwise().equals("Y"))
		        xaxis.getTitle().setText("Month");
		    else
		        xaxis.getTitle().setText("Date");
		    barChart.getxAxis().setTitle(xaxis.getTitle());
		    
		    return barChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}
}
