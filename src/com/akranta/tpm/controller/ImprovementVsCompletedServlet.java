package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

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
import com.akranta.tpm.service.ImprovementVsCompletedService;
import com.akranta.tpm.service.impl.ImprovementVsCompletedServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class ImprovementVsCompletedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ImprovementVsCompletedService improvementVsCompletedService;
	DashboardService dashboardService;
	private static final String commonFilterIden = "impVsCompcommonFilter";

	public ImprovementVsCompletedServlet() {
	/*	try {
			improvementVsCompletedService = new ImprovementVsCompletedServiceImpl();
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
			HttpSession httpSession = request.getSession(false);
			improvementVsCompletedService = (ImprovementVsCompletedServiceImpl)UIUtils.getServiceObject(request,"ImprovementVsCompletedServiceImpl");
			
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			
			CommonMessage.debugMsg("  improvementVsCompletedService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			improvementVsCompletedService.ImprovementVsCompletedServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		
		
		if (action.equals("filterXmlImproveVsComp_input.imvscom")) {
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response,"/tiles/xml/ImprovementVsCompleted.xml");
		} 
		else if (action.equals("ImproveVsComp_input.imvscom")) {
			String comp = request.getParameter("cmbCompid");
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			UIUtils.forwardRequest(request, response,"/pages/Reports/ImprovementVsCompleted.jsp");
		} 
		else if (action.equals("ImproveVsComp_getCol.imvscom")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			//CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",true);
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
			List<String[]> impVscomList = improvementVsCompletedService.getAllImpVsComp(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("ImproveVsComp_getData.imvscom")) {
			try {
				HttpSession httpSession = request.getSession(false);
				//String flid=request.getParameter("flid");
				//String drillflag=request.getParameter("drillFlag");
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				ComboFilter jhKaizenCategoryObj=new ComboFilter();
				if( jhKaizenCategoryObj != null)
					jhKaizenCategoryObj.setId(jhKaizenCategory);
				commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
			/*	if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(drillflag)){
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}*/
				CommonMessage.debugMsg(" Inside GetData Action commonFilter " +commonFilter.getFlid()  );
				response.setContentType("text/html");
				httpSession.getAttribute("commonFilterIden");
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = improvementVsCompletedService.getAllImpVsComp(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2) // --vignesh 3 to 2
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("ImproveVsComp_getExcel.imvscom"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
		
		
		tableModel.put("title", "Kaizen  Report-"+commonFilter.getDrillCaption()+" Wide ");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = improvementVsCompletedService.ImprovementVsCompleteExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "ImprovementVsCompleted", format);
		}
		
		   /*Employee DMT Wise Kaizen*/
		
		else if (action.equals("EmpdmtKaizen_input.imvscom")) {
			String comp = request.getParameter("cmbCompid");
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			UIUtils.forwardRequest(request, response,"/pages/Reports/EmpDmtKaizen.jsp");
		} 
		else if (action.equals("EmpdmtKaizen_getCol.imvscom")) {
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
		
			List<String[]> EmpDmtList = improvementVsCompletedService.getEmpDmtKaizen(commonFilter);
			JSONObject EmpDmtData = UIUtils.convertToJqGridTableObject(EmpDmtList, request, 1, 2);
			JSONObject jsonObject = getTableModel(EmpDmtList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("EmpDmtKaizenColData");
			httpSession.setAttribute("EmpDmtKaizenColData", jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("EmpdmtKaizen_getData.imvscom")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				ComboFilter jhKaizenCategoryObj=new ComboFilter();
				if( jhKaizenCategoryObj != null)
					jhKaizenCategoryObj.setId(jhKaizenCategory);
				commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
				response.setContentType("text/html");
				httpSession.getAttribute("commonFilterIden");
				List<String[]> EmpDmtList = improvementVsCompletedService.getEmpDmtKaizen(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
			       if (EmpDmtList != null && EmpDmtList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(EmpDmtList, request, 3, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("EmpdmtKaizen_getExcel.imvscom"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("EmpDmtKaizenColData");
		
		
		tableModel.put("title", "Employee Dmt Wise Kaizen-"+commonFilter.getDrillCaption()+" Wide ");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = improvementVsCompletedService.EmpDmtWiseKaizenExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "DmtWiseKaizen", format);
		}
		
		
		
		
		
		
		
		
		else if(action.equals("chart.imvscom"))
		{
			processChart(request,response);
			
		}
		else if (action.equals("kaizenimplemented_input.imvscom")) {
		    CommonMessage.debugMsg("Inside Input Servlet::::");
		    
		    String comp = request.getParameter("cmbCompid");
		    CommonMessage.debugMsg("comp::::"+comp);
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		    
		   UIUtils.forwardRequest(request, response,"/pages/Reports/KaizenImplementedCount.jsp");
		}
		else if (action.equals("kaizenimplemented_getCol.imvscom")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,true);
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
			CommonMessage.debugMsg("kznSgnCount::Before::");
			commonFilter.setIsGetCol("Y");
			List<String[]> kznSgnCount = improvementVsCompletedService.getKznImplCount(commonFilter);
			CommonMessage.debugMsg("kznSgnCount::::"+kznSgnCount);
			JSONObject kznSgnCountData = UIUtils.convertToJqGridTableObject(kznSgnCount, request, 1, 2);
			JSONObject jsonObject = getTableModel(kznSgnCount,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("SuggestionColData");
			httpSession.setAttribute("SuggestionColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
	
		else if (action.equals("kaizenimplemented_getData.imvscom")) {
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
				httpSession.getAttribute(commonFilterIden);
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = improvementVsCompletedService.getKznImplCount(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
	
		else if( action.equals("kaizenimplemented_getExcel.imvscom"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		//CommonMessage.debugMsg("gridFilter "+commonFilter.getGridFilter());
		JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
		tblJSONObj.put("title","Kaizen Implemented Count");
        String format = ExcelUtils.getFormat(request);				
		Workbook wb = improvementVsCompletedService.KznImplCountExportExcel(commonFilter,tblJSONObj,format);
		ExcelUtils.writeToResponse(response, wb, "KaizenImplementedCount", format);
	}
		else if( action.equals("kaizenimplemented_getchart.imvscom"))
		{
			CommonMessage.debugMsg("kaizenimplemented_getchart.imvscom ");
			processChart2(request,response);
		}
		else if(action.equals("kaizenimplemented_getchartLine.imvscom")){
			  processChart3(request,response);
		}
		else if(action.equals("chartIncidentimpVsCom.imvscom"))
		{
			processChartIncidentImpVsComp(request,response);
			
		}
		
		if (action.equals("filterXmlIncidentImproveVsComp_input.imvscom")) {
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response,"/tiles/xml/ImprovementVsCompleted.xml");
		} 
		else if (action.equals("IncidentImproveVsComp_input.imvscom")) {
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");
			
			request.setAttribute("hiddenCompId",comp);
		
			String cellId = request.getParameter("cellId");
			if(!UIUtils.isValidKeyId(cellId)){
				cellId = request.getParameter("cmbCellid");
			}
			String factId = request.getParameter("factId");
			if(!UIUtils.isValidKeyId(factId)){
				factId = request.getParameter("cmbFactid");
			}
			String sectId = request.getParameter("sectId");
			if(!UIUtils.isValidKeyId(sectId)){
				sectId = request.getParameter("cmbSectid");
			}
			String toDate = request.getParameter("toDate");
			if(!UIUtils.isValidKeyId(toDate)){
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if(!UIUtils.isValidKeyId(fromDate)){
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdnfactId", factId);
			request.setAttribute("hdnshftId", shift);
			request.setAttribute("hdncellId", cellId);
			request.setAttribute("hdnsectId", sectId);
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		
			String fromdate = CommonFunctions.getDate().substring(3,11);
			String todate = CommonFunctions.getDate().substring(3,11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			UIUtils.forwardRequest(request, response,"/pages/Reports/IncedentImprovementVsCompleted.jsp");
		} 
		else if (action.equals("IncidentImproveVsComp_getCol.imvscom")) {
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
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				commonFilter.setMonwise("Y");
			}
		
			List<String[]> impVscomList = improvementVsCompletedService.getAllIncedentImpVsComp(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}
		else if (action.equals("suggestionCount_input.imvscom")) {
		    CommonMessage.debugMsg("Inside Input Servlet::::");
		    
		    String comp = request.getParameter("cmbCompid");
		    CommonMessage.debugMsg("comp::::"+comp);
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
		    
		   UIUtils.forwardRequest(request, response,"/pages/Reports/KaizenSuggestionCount.jsp");
	}
		 else if (action.equals("suggestionCount_getCol.imvscom")) {
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				String firstClick = request.getParameter("firstClick");
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,true);
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
				CommonMessage.debugMsg("kznSgnCount::Before::");
				commonFilter.setIsGetCol("Y");
				List<String[]> kznSgnCount = improvementVsCompletedService.getKznSgnCount(commonFilter);
				CommonMessage.debugMsg("kznSgnCount::::"+kznSgnCount);
				JSONObject kznSgnCountData = UIUtils.convertToJqGridTableObject(kznSgnCount, request, 1, 2);
				JSONObject jsonObject = getTableModel_SUGGESTION(kznSgnCount,FilterValues.getHeader(commonFilter.getDrillCaption()));
				jsonObject.set("tableHeight", "80%%");
			    jsonObject.set("tableWidth", "104%%");
				httpSession.removeAttribute("SuggestionColData");
				httpSession.setAttribute("SuggestionColData", jsonObject);
				CommonMessage.debugMsg("Table Model:" + jsonObject);
				out.println(jsonObject);
			} 
		 else if (action.equals("suggestionCount_getData.imvscom")) {
				try {
					CommonMessage.debugMsg("Sugg Count GetData ::::");
					HttpSession httpSession = request.getSession(false);
					//String flid=request.getParameter("flid");
					//String drillflag=request.getParameter("drillFlag");
					CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
				/*	if(UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					if(UIUtils.isValidKeyId(drillflag)){
						CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
						commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
					}*/
					CommonMessage.debugMsg(" Inside GetData Action commonFilter " +commonFilter.getFlid()  );
					response.setContentType("text/html");
					httpSession.getAttribute(commonFilterIden);
					commonFilter.setIsGetCol("N");
					List<String[]> impVscomList = improvementVsCompletedService.getKznSgnCount(commonFilter);
					
					JSONObject listToJsonObject = new JSONObject();
					
					CommonMessage.debugMsg("size of dash..."+impVscomList.size());
					if (impVscomList != null && impVscomList.size() > 2)
						//listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
						listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0);
					
					PrintWriter out = response.getWriter();
					out.println(listToJsonObject);
				}

				catch (Exception e) {
					CommonMessage.debugMsg("error " + e.getMessage());
				}
			}
		 else if( action.equals("suggestionCount_getExcel.imvscom"))
			{			
				
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
			//CommonMessage.debugMsg("gridFilter "+commonFilter.getGridFilter());
			JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
			tblJSONObj.put("title","Kaizen Suggestion Count");
	        String format = ExcelUtils.getFormat(request);				
			Workbook wb = improvementVsCompletedService.KznSgnCountExportExcel(commonFilter,tblJSONObj,format);
			ExcelUtils.writeToResponse(response, wb, "KaizenSuggestionCount", format);
		}
		 else if( action.equals("kznsgncount_getchart.imvscom"))
			{
				processChart1(request,response);
				
			}
			
		
		else if (action.equals("IncidentImproveVsComp_getData.imvscom")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());
				List<String[]> impVscomList = improvementVsCompletedService.getAllIncedentImpVsComp(commonFilter);
				
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
		
		else if( action.equals("IncidentImproveVsComp_getExcel.imvscom"))
		{			
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
			
			
			tableModel.put("title", "Kaizen Report-"+commonFilter.getDrillCaption()+" Wide ");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = improvementVsCompletedService.IncedentVsCompleteExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "ImprovementVsCompleted", format);
		}
		else if(action.equals("kaizenImplementedCumulative_input.imvscom")){
			String comp = request.getParameter("cmbCompid");
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			UIUtils.forwardRequest(request, response,"/pages/Reports/KaizenCumulative.jsp");
		}		
		else if (action.equals("kaizenImplementedCumulative_getCol.imvscom")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			//CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",true);
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
			List<String[]> impVscomList = improvementVsCompletedService.getKaizenCululative(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("kaizenImplementedCumulative_getData.imvscom")) {
			try {
				HttpSession httpSession = request.getSession(false);
				//String flid=request.getParameter("flid");
				//String drillflag=request.getParameter("drillFlag");
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				ComboFilter jhKaizenCategoryObj=new ComboFilter();
				if( jhKaizenCategoryObj != null)
					jhKaizenCategoryObj.setId(jhKaizenCategory);
				commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
			/*	if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(drillflag)){
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}*/
				CommonMessage.debugMsg(" Inside GetData Action commonFilter " +commonFilter.getFlid()  );
				response.setContentType("text/html");
				httpSession.getAttribute("commonFilterIden");
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = improvementVsCompletedService.getKaizenCululative(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					//listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 3, 0);
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		else if(action.equals("kaizenImplementedCumulative_getExcel.imvscom"))
		{
		    HttpSession httpSession = request.getSession(false);
		    CommonFilter commonFilter = populateCommonFilter(request, commonFilterIden, false);
		    JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		    tblJSONObj.put("title", "Kaizen Implemented Cumulative");
		    String format = ExcelUtils.getFormat(request);
		    Workbook wb = improvementVsCompletedService.KaizenCumulativeExportExcel(commonFilter, tblJSONObj, format);
		    ExcelUtils.writeToResponse(response, wb, "KaizenImplementedCumulative", format);
		}
		else if( action.equals("kznCumulative_getchart.imvscom"))
		{
			processChart1(request,response);
			
		}
		
		
		else if(action.equals("Incidentchart.imvscom"))
		{
			processChart(request,response);
			
		}
		else if (action.equals("filterXmlSugesVsImple_input.imvscom")) {
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response,"/tiles/xml/suggestnVsImpl.xml");
		} 
		else if (action.equals("SugesVsImple_input.imvscom")) {
			CommonMessage.debugMsg("jsp " );	
			UIUtils.forwardRequest(request, response,"/pages/Reports/KznSuggestnVsImplemntd.jsp");
		}
		else if (action.equals("SugesVsImple_getCol.imvscom")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			
			
			CommonFilter commonFilter = populateCommonFilter(request,"SuggestedVsImple",true);
			commonFilter= FilterValues.getCommonFilters(request,commonFilter);
			 commonFilter= FilterValues.getOPLandKaizen(request,commonFilter);
			 
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) )
			{
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  
			}
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) )
			 {
	 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(0));
				  commonFilter.setToDate(CommonFunctions.getDate());
		         }
		
			List<String[]> abnfrmGrid = improvementVsCompletedService.getsuggestdimple(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false)	;
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			gridColModel.setHeaderNum(2);
	
			String [] colHeader = abnfrmGrid.get(2);
			String [] colHeader1 = abnfrmGrid.get(3);
			String [] colHeaderCond = abnfrmGrid.get(1);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			//jsonObject.put("multiSelect", true);
			jsonObject.put("tableWidth", "110%%");
			jsonObject.put("tableHeight", "70%%");
			httpSession.removeAttribute("SuggestedVsImplem");
			out.println(jsonObject);
 
		}
		else if (action.equals("SugesVsImple_getData.imvscom")) {
			CommonFilter commonFilter = populateCommonFilter(request,"SuggestedVsImple",false);	
			 
			List<String[]> MachineGrid = improvementVsCompletedService.getsuggestdimple(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 4, 0, 0, commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
				
		}
		else if( action.equals("SugesVsImple_getExcel.imvscom"))
		{			
			
//		HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"SuggestedVsImple",false);	
			JSONObject tableModel = UIUtils.getXlColModel(request, response);
			tableModel.put("title", "Suggestion Vs Implemented Report");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = improvementVsCompletedService.SuggestnVsImpl(commonFilter,tableModel,format);
			ExcelUtils.writeToResponse(response, wb, "SuggestionVsImplemented", format);
		}
		 
		else if( action.equals("sugstvsimp_getChart.imvscom"))
		{
			CommonMessage.debugMsg("Chart ");
			barChart(request,response);
			
		}
		//-----------------****KaizenGraphicalSummary****-----------vijay
		
		else if (action.equals("KaizenGraphicalSumm_input.imvscom")) {
			String comp = request.getParameter("cmbCompid");
			String locn=request.getParameter("cmbLocnid");
			String fact = request.getParameter("cmbFactid");
			String sect = request.getParameter("cmbSectid");
			String fromMonth=request.getParameter("dtFromMonth");
			String toMonth=request.getParameter("dtToMonth");
			String toDate = request.getParameter("dtToDate");
			String fromDate = request.getParameter("dtFromDate");
			String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
			request.setAttribute("hiddenCompId",comp);
			request.setAttribute("hiddenLocnId",locn);
			request.setAttribute("hiddenFactId",fact);
			request.setAttribute("hiddenSectId",sect);
			request.setAttribute("hiddenFromMonth",fromMonth);
			request.setAttribute("hiddenToMonth",toMonth);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);
			UIUtils.forwardRequest(request, response,"/pages/Reports/KaizenGraphicalSummary.jsp");
		} 
		else if (action.equals("KaizenGraphicalSumm_getCol.imvscom")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");
			//CommonFilter commonFilter = populateCommonFilter(request,"FSAuditReportCommonFilter",true);
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
			List<String[]> impVscomList = improvementVsCompletedService.getAllGraphicalSumm(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModel(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "104%%");
			httpSession.removeAttribute("ImprovementColData");
			httpSession.setAttribute("ImprovementColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		} 
		
		else if (action.equals("KaizenGraphicalSumm_getData.imvscom")) {
			try {
				HttpSession httpSession = request.getSession(false);
				//String flid=request.getParameter("flid");
				//String drillflag=request.getParameter("drillFlag");
				CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				ComboFilter jhKaizenCategoryObj=new ComboFilter();
				if( jhKaizenCategoryObj != null)
					jhKaizenCategoryObj.setId(jhKaizenCategory);
				commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
			/*	if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(drillflag)){
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}*/
				CommonMessage.debugMsg(" Inside GetData Action commonFilter " +commonFilter.getFlid()  );
				response.setContentType("text/html");
				httpSession.getAttribute("commonFilterIden");
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = improvementVsCompletedService.getAllGraphicalSumm(commonFilter);
				
				JSONObject listToJsonObject = new JSONObject();
				
				CommonMessage.debugMsg("size of dash..."+impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				
				PrintWriter out = response.getWriter();
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		
		else if( action.equals("KaizenGraphicalSumm_getExcel.imvscom"))
		{			
			
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tableModel = (JSONObject)httpSession.getAttribute("ImprovementColData");
		
		
		tableModel.put("title", "Kaizen  Report-"+commonFilter.getDrillCaption()+" Wide ");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = improvementVsCompletedService.KaizenGraphicalSummExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "ImprovementVsCompleted", format);
		}
		
		else if( action.equals("suggChart.imvscom"))
		{
			CommonMessage.debugMsg("Chart ");
			processChart4(request,response);
			
		}
		else if(action.equals("KznsugglineChart.imvscom")){
			  processlinechart(request,response);
		}
		
		//-----------------****KaizenGraphicalSummary****-----------
	}	
		private void barChart(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		String forDashboard = request.getParameter("dashboard");
		String rowid= request.getParameter("rowid");
		CommonMessage.debugMsg(rowid + " rowid");
		if( ! "true".equals(forDashboard))
		{
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("SuggestedVsImple");
		}
		else
		{
			commonFilter= populateCommonFilter(request,"SuggestedVsImple",true);
			CommonMessage.debugMsg("to check dash board true ");
				
		}
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) )
		{
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 } 
	
		List<String[]> suggstnList  = improvementVsCompletedService.getchartforimpl(commonFilter);;
		
		JSONObject chartObj = null;
		
		chartObj = processLineChart(suggstnList,commonFilter,rowid );
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();	
	}
		private void processlinechart(HttpServletRequest request, HttpServletResponse response) throws Exception{
			CommonMessage.debugMsg("processChart1");
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
			
			String forDashboard = request.getParameter("dashboard");
			CommonFilter chartCommonFilter = new CommonFilter(); 
			CommonMessage.debugMsg("forDashboard::::"+forDashboard);
			if( ! "true".equals(forDashboard)){
				commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter IN PROCESS CHART1::::::"+commonFilter);
				
			}
			else{
				
				commonFilter= populateCommonFilter(request,commonFilterIden,false);
		
				CommonMessage.debugMsg("commonFilter IN PROCESS CHART2::::::"+commonFilter);
				
			}
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
			chartCommonFilter.setRowTotal('Y');
			List<String[]> impVscomList = improvementVsCompletedService.getKaizenCululative(commonFilter);
			CommonMessage.debugMsg("impVscomList"+impVscomList);
			JSONObject chartObj = null;
			if(impVscomList != null && impVscomList.size() > 0)
			{
				String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
				
				chartObj = processLinechart6(lcnname,impVscomList,chartCommonFilter);
			
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request,chartObj);
				out.print(chartObj);
				out.close();
			}
		}
		
		
//		private JSONObject processLinechart6(String titlename,List<String[]> impVscomList,CommonFilter commonFilter) throws  Exception{
//
//			if( impVscomList == null || impVscomList.size() <= 1  )
//				return null;
//			
//			ChartOptionBean lineChart =  new ChartOptionBean();
//			List<String> xAxisCategory = new ArrayList<String>();
//			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
//			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
//        
//		     StringBuilder date=new StringBuilder();	
//			if(commonFilter.getMonwise().equals("Y"))
//			{
//				date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
//			}
//			else
//				date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
//            
//			 //String title = "Kaizen Implemented Cumulative"+date;
//			for(int m=0;m<impVscomList.size();m++){
//				//String chk1=impVscomList.get(1)[4];
//			//	String chk2=impVscomList.get(4)[3];
//			//	String chk3=impVscomList.get(5)[3];
//				//String chk4=impVscomList.get(6)[2];
//				//CommonMessage.debugMsg("chk1"+chk1+chk3);
//			//	 CommonMessage.debugMsg("chk2"+chk2);
//			}
//				
//			String[] header =  impVscomList.get(3);
//			String[] month =  impVscomList.get(1);
//			String[] data =  impVscomList.get(4);
//			//String drillLevel = FilterValues.getDrillHeader(data[1]);
//			//CommonMessage.debugMsg("The DrillLevel"+drillLevel);
//			
//			  StringBuilder title=new StringBuilder(titlename+"-Kaizen Implemented Cumulative").append(" - ").append(date);
//			String prevMonth = null;
//			CommonMessage.debugMsg("header"+header);
//			CommonMessage.debugMsg("month"+month);
//			CommonMessage.debugMsg("data"+data);
//			int rSize = impVscomList.size()-3;
//			int cSize = header.length -4;
//			CommonMessage.debugMsg("rSize"+rSize);
//			CommonMessage.debugMsg("cSize"+cSize);
//			//String subTitle = data[2];
//			//String subTitle="Incident Category";	
//			String subTitle="";
//		//	int rno=-1;
//			Double [][] grData = new Double[rSize][cSize];
//			CommonMessage.debugMsg("grData"+grData);
//			for( int i =4;i <header.length;i++ ){
//			//	rno = rno +1;
//			//	CommonMessage.debugMsg("The rno IS:Content::"+rno);
//				for (int j = 3; j< impVscomList.size();j++) {
//					data  =  impVscomList.get(j);
//					CommonMessage.debugMsg("The Data IS:::"+data);
//						grData[j-3][i-4] = Double.parseDouble(data[i]);
//						CommonMessage.debugMsg("The GRData Length"+grData.length);
//				}
//				
//					xAxisCategory.add(month[i]);
//			}
//			for(int k=1;k<grData.length;k++) {
//				 
//				 ChartSeries timeSeries = new ChartSeries();
//				 timeSeries.setData(Arrays.asList(grData[k]));
//				 timeSeries.setType(ChartTypes.LINE);
//				 timeSeries.setName(impVscomList.get(k+3)[3]);
//				 CommonMessage.debugMsg("impVscomList:::::"+impVscomList.get(k+3)[3]);
//				 chartSeriesList.add(timeSeries);			
//			}
//			 ChartYAxis yAxis = new ChartYAxis(); 
//			 yAxis.setMin(0);
//			 chartYAxis.add(yAxis);
//			 yAxis.getTitle().setText("Numbers");
//			ChartXAxis xaxis = new ChartXAxis();
//			if(commonFilter.getMonwise().equals("Y"))
//				xaxis.getTitle().setText("Month");
//			else
//				xaxis.getTitle().setText("Date");
//			lineChart.getxAxis().setTitle(xaxis.getTitle());
//			return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
//		}
		
		private JSONObject processLinechart6(String titlename, List<String[]> impVscomList, CommonFilter commonFilter) throws Exception {
		    if (impVscomList == null || impVscomList.size() <= 1)
		        return null;

		    ChartOptionBean lineChart = new ChartOptionBean();
		    List<String> xAxisCategory = new ArrayList<String>();
		    List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		    List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		    StringBuilder date = new StringBuilder();
		    if (commonFilter.getMonwise().equals("Y")) {
		        date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		    } else {
		        date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		    }

		    
		    CommonMessage.debugMsg("mano order");
		    for (String[] arr : impVscomList) {
		        CommonMessage.debugMsg(Arrays.toString(arr));
		    }

		    if (impVscomList.size() < 4) {
		        return null; // Need at least header, month, and 1 data row
		    }

		    String[] header = impVscomList.get(1);  
		    String[] month = impVscomList.get(0);  
		    
		    // Calculate actual data dimensions
		    int dataStartRow = 3;  // First data row index
		    int dataEndRow = impVscomList.size(); 
		    int numDataRows = dataEndRow - dataStartRow;
		    
		    // Find max columns in data rows for safety
		    int maxDataCols = 0;
		    for (int j = dataStartRow; j < dataEndRow; j++) {
		        maxDataCols = Math.max(maxDataCols, impVscomList.get(j).length);
		    }
		    
		    // Data columns start from index 4 (MAR-2026, APR-2026 positions)
		    int numDataCols = Math.min(month.length - 4, maxDataCols - 4);
		    
		    CommonMessage.debugMsg("rSize: " + numDataRows + ", cSize: " + numDataCols);
		    
		    // Initialize data matrix
		    Double[][] grData = new Double[numDataRows][numDataCols];
		    
		    // Fill data matrix - Fixed loop logic
		    for (int col = 0; col < numDataCols; col++) {
		        int dataColIndex = 4 + col;  // Column index in data arrays (4,5 for MAR-2026, APR-2026)
		        
		        for (int row = 0; row < numDataRows; row++) {
		            String[] dataRow = impVscomList.get(dataStartRow + row);
		            if (dataColIndex < dataRow.length) {
		                try {
		                    grData[row][col] = Double.parseDouble(dataRow[dataColIndex]);
		                } catch (NumberFormatException e) {
		                    grData[row][col] = 0.0; // Default for invalid numbers
		                }
		            } else {
		                grData[row][col] = 0.0; // Default for missing data
		            }
		        }
		        xAxisCategory.add(month[dataColIndex]); // Add X-axis label
		    }

		    // Create series for each data row (excluding first 3 rows: month, header, ?)
		    for (int k = 0; k < numDataRows; k++) {
		        ChartSeries timeSeries = new ChartSeries();
		        timeSeries.setData(Arrays.asList(grData[k]));
		        timeSeries.setType(ChartTypes.LINE);
		        
		        // Get series name from row data (index 3 typically contains name)
		        String[] seriesRow = impVscomList.get(dataStartRow + k);
		        String seriesName = (seriesRow.length > 3) ? seriesRow[3] : "Series " + k;
		        timeSeries.setName(seriesName);
		        
		        CommonMessage.debugMsg("Series name: " + seriesName);
		        chartSeriesList.add(timeSeries);
		    }

		    // Setup Y-axis
		    ChartYAxis yAxis = new ChartYAxis();
		    yAxis.setMin(0);
		    yAxis.getTitle().setText("Numbers");
		    chartYAxis.add(yAxis);

		    // Setup X-axis
		    ChartXAxis xaxis = new ChartXAxis();
		    if (commonFilter.getMonwise().equals("Y"))
		        xaxis.getTitle().setText("Month");
		    else
		        xaxis.getTitle().setText("Date");
		    lineChart.getxAxis().setTitle(xaxis.getTitle());

		    String title = new StringBuilder(titlename + "-Kaizen Implemented Cumulative")
		            .append(" - ").append(date).toString();
		    String subTitle = "";

		    return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		}

		
		private void processChart4(HttpServletRequest request, HttpServletResponse response) throws Exception{
			CommonMessage.debugMsg("processChart1");
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
			
			String forDashboard = request.getParameter("dashboard");
			CommonFilter chartCommonFilter = new CommonFilter(); 
			CommonMessage.debugMsg("forDashboard::::"+forDashboard);
			if( ! "true".equals(forDashboard)){
				commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
				CommonMessage.debugMsg("commonFilter IN PROCESS CHART1::::::"+commonFilter);
				
			}
			else{
				//commonFilter = new  CommonFilter();
				commonFilter= populateCommonFilter(request,commonFilterIden,false);
			//	FilterValues.getCommonFilters(request, commonFilter);
				CommonMessage.debugMsg("commonFilter IN PROCESS CHART2::::::"+commonFilter);
				//FilterValues.getOPLandKaizen(request, commonFilter);		
				//CommonMessage.debugMsg("commonFilter IN PROCESS CHART3::::::"+commonFilter);
			}
				//FilterValues.getCommonFilters(request, chartCommonFilter) ;
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
			chartCommonFilter.setRowTotal('Y');
			List<String[]> impVscomList = improvementVsCompletedService.getsuggchartforimpl(commonFilter);
			//List<String[]> kznImplCountList  = improvementVsCompletedService.getKznImplCount(chartCommonFilter);
			
			CommonMessage.debugMsg("impVscomList"+impVscomList);
			JSONObject chartObj = null;
			if(impVscomList != null && impVscomList.size() > 0)
			{	chartObj = processBarChart5(impVscomList,chartCommonFilter);
			
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request,chartObj);
				out.print(chartObj);
				out.close();
			}
		}
		
		private JSONObject processBarChart5(List<String[]> impVscomList,CommonFilter commonFilter) throws  Exception{

			if( impVscomList == null || impVscomList.size() <= 1  )
				return null;
			
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();

//			String title =  commonFilter.getAbnViewType() +  " Incident Category ";
			String title = "Kaizen Graphical Summary";
			/*int index=0;
			if(impVscomList!=null)
				index=impVscomList.size()-2;*/
			for(int m=0;m<impVscomList.size();m++){
				String chk1=impVscomList.get(0)[4];   // 0
				String chk2=impVscomList.get(3)[3];   //    3
				String chk3=impVscomList.get(4)[3];    // 4
				//String chk4=impVscomList.get(6)[2];
				//CommonMessage.debugMsg("chk1"+chk1+chk3);
				CommonMessage.debugMsg("chk2"+chk2);
			}
				
			String[] header =  impVscomList.get(2);  //
			String[] month =  impVscomList.get(0);   //
			String[] data =  impVscomList.get(3);    //
			//String drillLevel = FilterValues.getDrillHeader(data[1]);
			//String prevMonth = null;
			CommonMessage.debugMsg("header"+header);
			CommonMessage.debugMsg("month"+month);
			CommonMessage.debugMsg("data"+data);
			int rSize = impVscomList.size()-3;
			int cSize = header.length -4;
			CommonMessage.debugMsg("rSize"+rSize);
			CommonMessage.debugMsg("cSize"+cSize);
			//String subTitle = data[2];
			//String subTitle="Incident Category";	
			String subTitle="";
		//	int rno=-1;
			Double [][] grData = new Double[rSize][cSize];
			CommonMessage.debugMsg("grData"+grData);
			for( int i =4;i <header.length;i++ ){
			//	rno = rno +1;
			//	CommonMessage.debugMsg("The rno IS:Content::"+rno);
				for (int j = 3; j< impVscomList.size();j++) {
					data  =  impVscomList.get(j);
					CommonMessage.debugMsg("The Data IS:::"+data);
						grData[j-3][i-4] = Double.parseDouble(data[i]);
						CommonMessage.debugMsg("The GRData Length"+grData.length);
				}
				
					xAxisCategory.add(month[i]);
			}
		  /*for(int r=0;r<grData.size();r++){
			  String check1=grData.get(i)[1];
		  }*/
			for(int k=2;k<grData.length;k++) {
				 
				 ChartSeries timeSeries = new ChartSeries();
				 timeSeries.setData(Arrays.asList(grData[k]));
				 timeSeries.setType(ChartTypes.COLUMN);
				 timeSeries.setName(impVscomList.get(k+3)[3]);
				 CommonMessage.debugMsg("impVscomList:::::"+impVscomList.get(k+3)[3]);
				 chartSeriesList.add(timeSeries);			
			//	 ChartYAxis yAxis = new ChartYAxis(); 
				// yAxis.setMin(0);
				 //yAxis.getTitle().setText("Total Incident Category");
				// chartYAxis.add(yAxis);
			}
			 ChartYAxis yAxis = new ChartYAxis(); 
			 yAxis.setMin(0);
			 chartYAxis.add(yAxis);
			 yAxis.getTitle().setText("Kaizen Implemented Count");
			ChartXAxis xaxis = new ChartXAxis();
			if(commonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());
			return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);
		}			/*if( impVscomList == null || impVscomList.size() <= 2  )
				return null;
			
			ChartOptionBean lineChart =  new ChartOptionBean();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
			
			String abnType = commonFilter.getAbnormalityType();
			
			if(abnType==null)
				abnType="";
			
			StringBuilder date = new StringBuilder();
			
			if(commonFilter.getMonwise().equals("Y"))
			{
				date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
			}
			else
				date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
			
			String [] headerData =  impVscomList.get(4);
			String drillLevel = FilterValues.getDrillHeader(headerData[0]);
			
			StringBuilder title = new StringBuilder( abnType).append(" Abnormality Cumulative Report - ").append( drillLevel ).append(" Wide From ").append(date);
			
			String [] header =  impVscomList.get(3);
			String [] month =  impVscomList.get(2);
			String [] data =  impVscomList.get(impVscomList.size()-1);
			
			String prevMonth = null;
			
			String subTitle = "";
			
			ChartSeries timeSeries = new ChartSeries();
			List<Double> timeData = new ArrayList<Double>();
			ChartSeries intstanceSeries = new ChartSeries();
			List<Double> instanceData = new ArrayList<Double>();
			
			for( int i = 2;i < header.length-1;i++ ){	
				if(header[i].contains("IDENTIFIED") ){
					timeData.add(Double.parseDouble(data[i]));
				}
				else if(header[i].contains("RECTIFIED") ){
					instanceData.add(Double.parseDouble(data[i]));
				}
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
					xAxisCategory.add(month[i]);
				}
				prevMonth = month[i];
			}
			if("B".equals(chartType))
				chartType=ChartTypes.COLUMN;
			else
				chartType=ChartTypes.SPLINE;
			if(timeData.size() > 0 ){
				timeSeries.setData(timeData);
				timeSeries.setType(chartType);
				timeSeries.setName("Identified");
				chartSeriesList.add(timeSeries);
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("No.Of Tags");
				chartYAxis.add(yAxis);
			}
			if( instanceData.size() > 0){
				intstanceSeries.setData(instanceData);
				intstanceSeries.setType(chartType);
				intstanceSeries.setName("Rectified");
				chartSeriesList.add(intstanceSeries);
				
			}
//			CommonMessage.debugMsg("chartSeriesList "+chartSeriesList.size());
			ChartXAxis xaxis = new ChartXAxis();
			if(commonFilter.getMonwise().equals("Y"))
				xaxis.getTitle().setText("Month");
			else
				xaxis.getTitle().setText("Date");
			lineChart.getxAxis().setTitle(xaxis.getTitle());
			return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	*/	
		
		private void processChart1(HttpServletRequest request, HttpServletResponse response) throws Exception{
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIden);
			
			String forDashboard = request.getParameter("dashboard");
			
			if( ! "true".equals(forDashboard)){
				commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}
			else{
				commonFilter = new  CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter) ;
				FilterValues.getBDRelated(request, commonFilter);		
			}
				//FilterValues.getCommonFilters(request, chartCommonFilter) ;
			

			CommonFilter chartCommonFilter = new CommonFilter(); 
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
			
			//FilterValues.getCommonFilters(request, chartCommonFilter) ;
			
			chartCommonFilter.setRowTotal('Y');
			List<String[]> kznCountList  = improvementVsCompletedService.getKznSgnCount(chartCommonFilter);
			
			CommonMessage.debugMsg("kznCountList"+kznCountList);
			JSONObject chartObj = null;
			if(kznCountList != null && kznCountList.size() > 0)
			{
				String flids=CommonFunctions.getLoginFlid(request);
				CommonMessage.debugMsg("flid fff is :::"+flids);
				String lcnname=dashboardService.Functionallocn(flids);
				chartObj = processBarChart2(lcnname,kznCountList,chartCommonFilter);
			
				PrintWriter out = response.getWriter();
				UIUtils.dashBoardSetChartObject(request,chartObj);
				out.print(chartObj);
				out.close();
			}
		}

private JSONObject processBarChart2(String titlenme,List<String[]> kznCountList,CommonFilter commonFilter){

		if( kznCountList == null || kznCountList.size() <= 1  )
			return null;
		CommonMessage.debugMsg("kznCountList"+kznCountList);
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		//String drillLevel = FilterValues.getHeader(commonFilter.getDrillCaption());
		
	
		//String[] header =  whywhyList.get(0);
		String[] month =  kznCountList.get(0);
		CommonMessage.debugMsg("month"+month);
		String[] data =  kznCountList.get(kznCountList.size()-2);
		CommonMessage.debugMsg("data"+data);
		String prevMonth = null;
		
		//String subTitle = data[0];
		 String subTitle="";
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else{
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		}
		//String drillLevel =  FilterValues.getDrillHeader(data[2]);
		//CommonMessage.debugMsg("drillLevel"+drillLevel);
		StringBuilder title = new StringBuilder(titlenme+" - Suggestion Count- ").append("-"+date);
		//title.append("Suggestion Count ").append("-"+date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i = 3;i < month.length;i++ ){ // changed month.length - 1 to month.length
			countData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 1 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Suggestion Count");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);		
	}
	private JSONObject getTableModel(List<String[]> headers,String caption) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(0); // 1 to 0 vignesh
		String[] colHeader1 = headers.get(1); //  2 to 1 vignesh
		colHeader[3] = "DATE";
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
		for (int i = 1; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;  
		for (int i = 0; i <= colHeader.length-1; i++) {  // length -1 to length vignesh
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2 ) {  
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
		tableModel.set("tableHeight", "67%%");
		return tableModel;
	}
	
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		
		List<String[]> improvementVsCompletedList  = improvementVsCompletedService.getAllImpVsComp(chartCommonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{
			
			String flids=CommonFunctions.getLoginFlid(request);
						String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processLineChart(lcnname,improvementVsCompletedList,chartCommonFilter);
			//UIUtils.dashBoardSetChartObject(request,chartObj);
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	
	private JSONObject processLineChart(String titlename,List<String[]> improvementVsCompletedList,CommonFilter commonFilter){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String[] data =  improvementVsCompletedList.get(improvementVsCompletedList.size()-1);
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		String drillLevel =  FilterValues.getDrillHeader(data[2]);

		StringBuilder title = new StringBuilder( titlename+"-Kaizen Identified Vs Implemented Cumulative ").append( drillLevel).append( " Wide From ").append(date);
		
		//String title = "Improvement Identified Vs Completed Cumulative";
		
		
		
		//int index=0;
	//	if(improvementVsCompletedList!=null)
	//		index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(2);
		String[] month =  improvementVsCompletedList.get(1);
		
		String prevMonth = null;
		
		String subTitle = "";//data[2];
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("Identified") ){
				identifiedData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("Implemented") ){
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
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);			
			//yAxis.getTitle().setText("Identified");
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
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	private void processChartIncidentImpVsComp(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =null;
		
		String forDashboard = request.getParameter("dashboard");
		//CommonMessage.debugMsg(commonFilterIden+" forDashboard.....forDashboard "+forDashboard);
		if( ! "true".equals(forDashboard)){
		//	CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter)httpSession.getAttribute(commonFilterIden);
		}
		else{
			commonFilter = populateCommonFilter(request,commonFilterIden,false);
			FilterValues.getCommonFilters(request, commonFilter) ;
		}
		
		commonFilter.setRowTotal('Y');
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		
		
		
		//commonFilter.setDrillFlag('f');
		List<String[]> improvementVsCompletedList  = improvementVsCompletedService.getAllIncedentImpVsComp(commonFilter);
		
		JSONObject chartObj = null;
		if(improvementVsCompletedList != null && improvementVsCompletedList.size() > 0)
		{	chartObj = processLineChartIncident(improvementVsCompletedList,chartCommonFilter);
			UIUtils.dashBoardSetChartObject(request,chartObj);
			//CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}
	private JSONObject processLineChartIncident(List<String[]> improvementVsCompletedList,CommonFilter commonFilter){
		if( improvementVsCompletedList == null || improvementVsCompletedList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title = "Improvement Identified Vs Completed";
		int index=0;
		if(improvementVsCompletedList!=null)
			index=improvementVsCompletedList.size()-1;
	
		String[] header =  improvementVsCompletedList.get(2);
		String[] month =  improvementVsCompletedList.get(1);
		String[] data =  improvementVsCompletedList.get(3);
		String prevMonth = null;
		
		String subTitle = data[2];
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> identifiedData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();
		
		for( int i = 4;i < header.length;i++ ){
			if(header[i].contains("IDENTIFIED") ){
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
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);			
			//yAxis.getTitle().setText("Identified");
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);
			
		}
		if( completedData.size() > 0){
			intstanceSeries.setData(completedData);
			intstanceSeries.setType(ChartTypes.SPLINE);
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
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setHidden(true);			
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		return jqGridColModel;
	}

	/*
	 * private JSONObject getTableModel_SUGGESTION(List<String[]> headers,String
	 * caption) { JqGridTableModel jqGridTableModel = new JqGridTableModel(); //
	 * String [] colHeader1 = new String[ headers.get(0).length + 1 ] ;
	 * CommonMessage.debugMsg("Inside the GetTable Model"); String [] colHeader =
	 * headers.get(0) ; int header = colHeader.length-1; String [] headerArr = new
	 * String[header]; for(int k=0;k<headerArr.length;k++)
	 * 
	 * 
	 * //CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
	 * colHeader[2] = caption;
	 * CommonMessage.debugMsg("colHeader[1] ="+colHeader[1]); //colHeader[1] =
	 * caption;
	 * 
	 * jqGridTableModel.setTableButton(true); JqGridColModel jqGridColModel
	 * =getColModel("keyid1",50,"left"); jqGridColModel.setHidden(true);
	 * 
	 * jqGridTableModel.getColModel().add(jqGridColModel);
	 * jqGridTableModel.setRowNumbers(true); jqGridColModel =
	 * getColModel("keyid2",100,"left");
	 * jqGridTableModel.getColModel().add(jqGridColModel); //colHeader1[ 0 ] =
	 * colHeader[0]; //colHeader1[ 1 ] = colHeader[1]; headerArr[0] = "keyid";
	 * headerArr[1] = "keyid"; String headerSql = "'SELECT "; for(int i =2; i <
	 * header; i++) { headerArr[i] = colHeader[i].replaceAll(" ", ""); //colHeader1[
	 * i ] = colHeader[i]; jqGridColModel = new JqGridColModel();
	 * jqGridColModel.setIndex((colHeader[i]+i).replaceAll(" ", ""));
	 * jqGridColModel.setName((colHeader[i]+i).replaceAll(" ", ""));
	 * 
	 * if(i==2 ) { jqGridColModel.setHidden(false); jqGridColModel.setAlign("left");
	 * jqGridColModel.setWidth(300); } if(i>2) { jqGridColModel.setWidth(100);
	 * jqGridColModel.setHidden(false); jqGridColModel.setAlign("right"); }
	 * if(i==colHeader.length-1) { //jqGridColModel.setHidden(true); }
	 * jqGridTableModel.getColModel().add(jqGridColModel); // headerSql = headerSql
	 * + UIUtils.getTablemodelSql(jqGridColModel); }
	 * jqGridTableModel.getRowHeaders().add(headerArr); JSONObject tableModel =
	 * UIUtils.getJqGridTableModel(jqGridTableModel); headerSql =
	 * headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
	 * CommonMessage.debugMsg("headerSql.....123..."+headerSql);
	 * 
	 * 
	 * tableModel.set("tableHeight", "72%"); return tableModel; }
	 */
	private JSONObject getTableModel_SUGGESTION(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	//	String [] colHeader1 = new String[  headers.get(0).length + 1 ] ;
		CommonMessage.debugMsg("Inside the GetTable Model");
		String [] colHeader = headers.get(0) ;
		/*
		 * int header = colHeader.length-1;
		 *  String [] headerArr = new String[header];
		 */
		String [] headerArr = new String[colHeader.length];
		for(int k=0;k<headerArr.length;k++)
			

		//CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
		colHeader[2] = caption;  
		CommonMessage.debugMsg("colHeader[1] ="+colHeader[1]);
		//colHeader[1] = caption;  
	
		jqGridTableModel.setTableButton(true);	
		JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
		jqGridColModel.setHidden(true);

		jqGridTableModel.getColModel().add(jqGridColModel);
		jqGridTableModel.setRowNumbers(true);
		jqGridColModel = getColModel("keyid2",100,"left");
		jqGridTableModel.getColModel().add(jqGridColModel);
		//colHeader1[ 0 ] = colHeader[0];
		//colHeader1[ 1 ] = colHeader[1];
		headerArr[0] = "keyid";
		headerArr[1] = "keyid";
		String headerSql = "'SELECT ";
		for(int i =2; i < colHeader.length; i++)
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			//colHeader1[ i ] = colHeader[i];
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex((colHeader[i]).replaceAll(" ", ""));
			jqGridColModel.setName((colHeader[i]).replaceAll(" ", ""));
					
			if(i==2 )
			{
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(300);	
			}
			if(i>2)
			{
				jqGridColModel.setWidth(100);	
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("right");
			}
			if(i==colHeader.length-1)
			{
				//jqGridColModel.setHidden(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		//	headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(headerArr);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		CommonMessage.debugMsg("tableModel JSON: " + tableModel.toString());
		 	
			tableModel.set("tableHeight", "72%");
			return tableModel;
      }

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getSafty(request, commonFilter);
		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		//CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
   private void processChart3(HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession httpSession = request.getSession(false);
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
		BeanUtils.copyProperties(commonFilter, commonFilter);
		commonFilter.setRowTotal('Y');
		List<String[]> kznImplCountList  = improvementVsCompletedService.getKznImplCount(commonFilter);
		JSONObject chartObj = null;
		if(kznImplCountList != null && kznImplCountList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processLineChart2(lcnname,kznImplCountList,commonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
   } 
   
   private JSONObject processLineChart2(String titlename,List<String[]> kznImplCountList,CommonFilter commonFilter){

		if( kznImplCountList == null || kznImplCountList.size() <= 1  )
			return null;
		CommonMessage.debugMsg("kznImplCountList"+kznImplCountList);
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String[] month =  kznImplCountList.get(0);
		CommonMessage.debugMsg("month"+month);
		String[] data =  kznImplCountList.get(kznImplCountList.size()-1);
		CommonMessage.debugMsg("data"+data);
		String prevMonth = null;
		
		String subTitle="";
		//String subTitle = data[1];
		CommonMessage.debugMsg("subTitle"+subTitle);
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		String drillLevel =  FilterValues.getDrillHeader(data[2]);
		CommonMessage.debugMsg("drillLevel"+drillLevel);
		StringBuilder title = new StringBuilder();
		title.append(titlename+"-Kaizen Implemented Count-").append( drillLevel).append(" Wide From ").append(date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i = 4;i < month.length;i++ ){
			countData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 0 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.LINE);
			timeSeries.setName("Kaizens Implemented Count");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
   
	
	private void processChart2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
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
		BeanUtils.copyProperties(commonFilter, commonFilter);
		commonFilter.setRowTotal('Y');
		List<String[]> kznImplCountList  = improvementVsCompletedService.getKznImplCount(commonFilter);
		JSONObject chartObj = null;
		if(kznImplCountList != null && kznImplCountList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChart3(lcnname,kznImplCountList,commonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
	}
	private JSONObject processBarChart3(String titlename,List<String[]> kznImplCountList,CommonFilter commonFilter){

		if( kznImplCountList == null || kznImplCountList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String[] month =  kznImplCountList.get(0);
		String[] data =  kznImplCountList.get(kznImplCountList.size()-1);
		String prevMonth = null;
		
		String subTitle="";
		//String subTitle = data[1];
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		String drillLevel =  FilterValues.getDrillHeader(data[2]);
		CommonMessage.debugMsg("drillLevel"+drillLevel);
		StringBuilder title = new StringBuilder();
		title.append(titlename+"-Kaizen Implemented Count - ").append( drillLevel).append(" Wide From ").append(date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i = 4;i < month.length;i++ ){
			countData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 0 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Kaizen Implemented Count");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	private JSONObject processLineChart(List<String[]> suggstnList,
			CommonFilter commonFilter, String rowid ) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("abnTagTrendList size:"+suggstnList.size());
		CommonMessage.debugMsg(rowid + " rowid");
		
		if( suggstnList == null || suggstnList.size() <= 2 )
			return null;
		ChartOptionBean barChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String title =" Suggestion  Given Vs Implemented  Report " ;
		String date;
		
		
		String[] header =  suggstnList.get(2);
		String[] month =  suggstnList.get(1);
		
		int rowno = 3;
		/*if(UIUtils.isValidKeyId(rowid)){
			rowno=rowno+Integer.parseInt(rowid);
		}else
			rowno=suggstnList.size()-1;
		*/
		
		String[] data =  suggstnList.get(rowno);
		String [] headerData =  suggstnList.get(2);
		String prevMonth = null;
		String subTitle = data[3];
		CommonMessage.debugMsg("subTitle "+subTitle );
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> suggestnData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> implementdData = new ArrayList<Double>();
		for( int i = 4;i < header.length;i++ )
		{			
			CommonMessage.debugMsg(header[i]  +  " header");
			CommonMessage.debugMsg(month[i]+ " month");
			CommonMessage.debugMsg(data[i] + " data" + headerData[i]);
			if(header[i].contains("Suggestion Given") )
				suggestnData.add(Double.parseDouble(data[i]));			
			else if(header[i].contains("Implemented") )
				implementdData.add(Double.parseDouble(data[i]));
			if( prevMonth == null || ! month[i].equals(prevMonth) )
				xAxisCategory.add(month[i]);
				
			prevMonth = month[i];
		}
		if(suggestnData.size() > 0 )
		{
			CommonMessage.debugMsg("graph time...........................");
			timeSeries.setData(suggestnData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Suggestion Given");
			chartSeriesList.add(timeSeries);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);
		}
		if( implementdData.size() > 0)
		{
			intstanceSeries.setData(implementdData);
			intstanceSeries.setType(ChartTypes.COLUMN);
			intstanceSeries.setName("Implemented");
			chartSeriesList.add(intstanceSeries);
		}
		ChartXAxis xaxis = new ChartXAxis();
		CommonMessage.debugMsg(commonFilter.getMonwise() + "  month wise");
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		barChart.getxAxis().setTitle(xaxis.getTitle());
		return barChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);	
	}
}
