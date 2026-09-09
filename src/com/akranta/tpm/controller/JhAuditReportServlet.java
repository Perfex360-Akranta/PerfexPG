
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

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
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.JhAuditRptService;
import com.akranta.tpm.service.impl.JhAuditRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

public class JhAuditReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CommonFilter commonFilter;
	JhAuditRptService jhAuditRptService;
	DashboardService dashboardService;
	private static final String commonFilterIden = "impVsCompcommonFilter";

	public JhAuditReportServlet() throws Exception {
		super();

		// commonFilter=new CommonFilter();
		// jhAuditRptService = new JhAuditRptServiceImpl();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		try {
			jhAuditRptService = (JhAuditRptServiceImpl) UIUtils.getServiceObject(request, "JhAuditRptServiceImpl");
			dashboardService = (DashboardServiceImpl) UIUtils.getServiceObject(request, "DashboardServiceImpl");

			jhAuditRptService.JhAuditRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
					: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		/*************** JH Audit Report **************/
		if (action.equals("filterXmlJhAuditReport_input.JhAuditRpt")) {
			response.setContentType("xml");
			CommonMessage.debugMsg("action " + action);
			UIUtils.forwardRequest(request, response, "/tiles/xml/Audit.xml");
		} else if (action.equals("JhAuditReport_input.JhAuditRpt")
				|| action.equals("JhauditActionPlanScore_input.JhAuditRpt")) {
			CommonMessage.debugMsg("Brfore jsp");
			String maintype = request.getParameter("maintype");
			request.setAttribute("GraphMsg",
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "Forgraph"));
			request.setAttribute("maintype", maintype);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/JhAuditRpt.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("selfAuditScoreGraph_input.JhAuditRpt")) {
			CommonMessage.debugMsg("JHSelfAuditScoreGraph_view.JhAuditRpt");
			String type = request.getParameter("type");
			request.setAttribute("GraphMsg",
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "Forgraph"));
			request.setAttribute("type", type);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/JhSelfAuditGraph.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("JhAuditReport_getCol.JhAuditRpt")
				|| action.equals("JhauditActionPlanScore_getCol.JhAuditRpt")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "JhAuditRptCommonFilter", true);
			// commonFilter.setMonwise("Y");
			commonFilter.setChkseveritychkbox("1");
			commonFilter.setChkfrequencychkbox("0");

			CommonMessage.debugMsg("action" + action);

			String maintype = request.getParameter("maintype");

			CommonMessage.debugMsg(" maintype :: GetData ::  " + maintype);
			if (UIUtils.isValidKeyId(maintype))
				commonFilter.setMaintMode(maintype);

			if (action.equals("JhauditActionPlanScore_getCol.JhAuditRpt"))
				commonFilter.setType("Actionplan");
			commonFilter.setIsGetCol("Y");
			List<String[]> JhAuditRpt = jhAuditRptService.getJhAuditRpt(commonFilter);
			CommonMessage.debugMsg("JhAuditRpt" + JhAuditRpt.size());
			// JSONObject abnData =
			// UIUtils.convertToJqGridTableObject(JhAuditRpt,request,3,0,commonFilter.getTotalRecordCnt());
			// CommonMessage.debugMsg(abnData);
			// httpSession.setAttribute("JhAuditReportdata", abnData);

			JSONObject jsonObject = getTableModel(JhAuditRpt, commonFilter, action);
			jsonObject.set("tableHeight", "77%%");
			jsonObject.set("tableWidth", "108%%");
			// JSONObject jsonObject = getTableModel1(hseAccidentRpt);
			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("JhAuditReportColModel");
			httpSession.setAttribute("JhAuditReportColModel", jsonObject);
			out.println(jsonObject);

		}

		else if (action.equals("JhAuditReport_getData.JhAuditRpt")
				|| action.equals("JhauditActionPlanScore_getData.JhAuditRpt")) {
			PrintWriter out = response.getWriter();
			try {
				// UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				CommonMessage.debugMsg("page......" + page);
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "JhAuditRptCommonFilter", false);

				JSONObject jsonObject = new JSONObject();
				// if(page.equals("1"))
				// jsonObject = (JSONObject)
				// httpSession.getAttribute("BDFailureReportServletdata");
				// else
				// {

				if (action.equals("JhauditActionPlanScore_getData.JhAuditRpt"))
					commonFilter.setType("Actionplan");

				String maintype = request.getParameter("maintype");

				CommonMessage.debugMsg(" maintype :: GetData ::  " + maintype);
				if (UIUtils.isValidKeyId(maintype))
					commonFilter.setMaintMode(maintype);

				commonFilter.setIsGetCol("N");
				List<String[]> JhAuditRpt = jhAuditRptService.getJhAuditRpt(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(JhAuditRpt, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				// }

				out.println(jsonObject);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute("JhAuditRptCommonFilter");
				httpSession.setAttribute("JhAuditRptCommonFilter", commonFilter);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("JhauditActionPlanScore_getExcel.JhAuditRpt")) {

			CommonFilter commonFilter = populateCommonFilter(request, "JhAuditRptCommonFilter", false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);

			if (action.equals("JhauditActionPlanScore_getData.JhAuditRpt"))
				commonFilter.setType("Actionplan");

			String maintype = request.getParameter("maintype");

			CommonMessage.debugMsg(" maintype :: GetData ::  " + maintype);
			if (UIUtils.isValidKeyId(maintype))
				commonFilter.setMaintMode(maintype);

			// String tableModel =
			// UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
			// "EqpQuery");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("JhAuditReportColModel");

			tblJSONObj.put("title", maintype + " Audit Report");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = jhAuditRptService.JhAuditActionReportExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, maintype + "AuditReport", format);

		} else if (action.equals("JhAuditReport_getExcel.JhAuditRpt")) {

			// HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "JhAuditRptCommonFilter", false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			// String tableModel =
			// UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
			// "EqpQuery");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("JhAuditReportColModel");

			tblJSONObj.put("title", "JH Audit Report");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = jhAuditRptService.JhAuditReportExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "JHAuditReport", format);

		}

		else if (action.equals("JhAuditReport_view.JhAuditRpt")) {
			try {
				String rowId = request.getParameter("rowId");
				String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);
				String imagePath = UIUtils.getImagePath(request);
				format = ".xlsx";
				Workbook wb = jhAuditRptService.getAllJHAuditRptExl(rowId, format, path, imagePath);

				ExcelUtils.writeToResponse(response, wb, "JhAuditReport", format);
			}

			catch (Exception e) {
				e.printStackTrace();
				CommonMessage.debugMsg("err:" + e.getMessage());
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();
				// err.put("exception",true);
				err.put("message", "Data Not Found");
				out.print(err.toString());
			}

		}
		// sself audit Score Graph

		else if (action.equals("filterXmljhnAuditScoreGraph_input.JhAuditRpt")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/jhAuditScore.xml");
		} else if (action.equals("chartAuditScoreGraph.JhAuditRpt")) {
			processChartAuditScoreGraph(request, response);

		} else if (action.equals("jhnAuditScoreGraph_input.JhAuditRpt")) {
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String comp = request.getParameter("cmbCompid");

			request.setAttribute("hiddenCompId", comp);

			String toDate = request.getParameter("toDate");
			if (!UIUtils.isValidKeyId(toDate)) {
				toDate = request.getParameter("dtToDate");
			}
			String fromDate = request.getParameter("fromDate");
			if (!UIUtils.isValidKeyId(fromDate)) {
				fromDate = request.getParameter("dtFromDate");
			}
			request.setAttribute("hdndateId", entryDate);
			request.setAttribute("hdnfromdate", fromDate);
			request.setAttribute("hdntodate", toDate);

			String fromdate = CommonFunctions.getDate().substring(3, 11);
			String todate = CommonFunctions.getDate().substring(3, 11);
			request.setAttribute("fromdate", fromdate);
			request.setAttribute("todate", todate);
			UIUtils.forwardRequest(request, response, "/pages/Reports/AuditScoreGraphs.jsp");
		} else if (action.equals("jhnAuditScoreGraph_getCol.JhAuditRpt")) {
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");

			String type = request.getParameter("type");

			CommonMessage.debugMsg(" Inside getCol " + type);

			CommonFilter commonFilter = new CommonFilter();
			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
			}

			if (commonFilter == null)
				commonFilter = new CommonFilter();
			commonFilter = populateCommonFilter(request, commonFilterIden, true);

			if (UIUtils.isValidKeyId(type))
				commonFilter.setAbnViewType(type);

			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getSafty(request, commonFilter);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}

			commonFilter.setIsGetCol("Y");
			List<String[]> impVscomList = jhAuditRptService.getAllAuditScoreGraph(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			JSONObject jsonObject = getTableModelGraph(impVscomList,
					FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("jhAuditColData");
			httpSession.setAttribute("jhAuditColData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("jhnAuditScoreGraph_getData.JhAuditRpt")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request, commonFilterIden, false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterIden);

				String type = request.getParameter("type");

				CommonMessage.debugMsg(" Inside getData " + type);

				if (UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);

				CommonMessage.debugMsg("commonFilter.getFlid()  " + commonFilter.getFlid());
				commonFilter.setIsGetCol("N");
				List<String[]> impVscomList = jhAuditRptService.getAllAuditScoreGraph(commonFilter);

				JSONObject listToJsonObject = new JSONObject();

				CommonMessage.debugMsg("size of dash..." + impVscomList.size());
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

		else if (action.equals("jhnAuditScoreGraph_getExcel.JhAuditRpt")) {

			CommonFilter commonFilter = populateCommonFilter(request, commonFilterIden, false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject) httpSession.getAttribute("jhAuditColData");

			String type = request.getParameter("type");

			// tableModel.put("title", type + " Self Audit Score -
			// "+commonFilter.getDrillCaption()+" Level");
			tableModel.put("title", type + " Self Audit Score ");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = jhAuditRptService.jhAuditGraphExportExcel(commonFilter, tableModel, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, type + "SelfAuditScore", format);
		}
	}

//	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter, String reportNmae)
//	{
//		JqGridTableModel jqGridTableModel = new JqGridTableModel();
//		 
//		String[] colHeader = headers.get(1);
//		//String[] colHeader1 = headers.get(1);
//		
//		String[] emptyrow = new String[colHeader.length]; 	
//		
//		emptyrow [0] ="";
//		emptyrow [1] ="";
//				
//		for( int i = 2; i < colHeader.length;i++ ){			
//			emptyrow [i] = "";
//		}		
//		
//		//jqGridTableModel.getRowHeaders().add(emptyrow);		
//		jqGridTableModel.getRowHeaders().add(colHeader);
//		//jqGridTableModel.getRowHeaders().add(colHeader1);
//		
//		jqGridTableModel.setSortable(false);
//		jqGridTableModel.setTableButton(true);
//		jqGridTableModel.setRowNumbers(true);
//		jqGridTableModel.setEnableFilter(true);
//		jqGridTableModel.setTableHeight(300);
//		jqGridTableModel.setPaginate(true);
//	//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
//		 
//		  		for(int i =0; i < colHeader.length; i++)
//		{
//			CommonMessage.debugMsg("Length : "+colHeader.length);
//			JqGridColModel jqGridColModel = new JqGridColModel();
//			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
//			CommonMessage.debugMsg("colHeader[i] "+colHeader[i]);
//			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
//			jqGridColModel.setWidth(100);				
//			//jqGridColModel.setAlin("left");
//			jqGridColModel.setEditable(false);
//			
//			if(i==0||i==1||i==12|| i == 14)
//			{
//				jqGridColModel.setHidden(true);
//				jqGridColModel.setKey(true);
//			
//			}else if(i == 7 || i == 8 || i == 10 || i == 11)
//			{
//				jqGridColModel.setWidth(115);
//				jqGridColModel.setAlign("left");
//			}else if(i == 9||i == 23||i == 15||i == 22)
//			{
//				jqGridColModel.setWidth(120);
//				jqGridColModel.setAlign("left");
//			}else if(i == 4||i == 3||i == 5||i == 6||i == 16||i == 17||i == 13){
//				jqGridColModel.setWidth(80);
//				jqGridColModel.setAlign("left");
//			}else if(i == 21){
//				jqGridColModel.setWidth(160);
//				jqGridColModel.setAlign("left");
//			}else if (i == 12){
//				jqGridColModel.setWidth(100);
//				jqGridColModel.setAlign("left");
//			}
//			
//			else
//			{
//				if(i==2)
//				{
//					jqGridColModel.setHidden(false);
//					jqGridColModel.setWidth(120);
//					jqGridColModel.setAlign("left");
//				}
//			}
//				/*	else
//				{
//					
//					if(i==4 || i == 5 ||i==10  )
//					{
//						jqGridColModel.setWidth(80);
//						jqGridColModel.setAlign("center");
//						
//					}
//					else if(i == 11 || i == 2 || i == 3 || i == 9)
//					{
//						jqGridColModel.setWidth(80);
//						jqGridColModel.setAlign("left");
//					}
//					else if(i == 6 )
//						jqGridColModel.setWidth(150);
//					
//				}
//					
//				
//			}
//			 	*/
//			if(reportNmae.equals("JhauditActionPlanScore_getCol.JhAuditRpt")) {
//				//jqGridTableModel.setGroupBy(true);
//				//jqGridTableModel.setGroupByField("GROUPINGCOLUMN1");
//			}
//				
//			jqGridTableModel.getColModel().add(jqGridColModel);
//		}
//		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
//		 return tableModel;
//	}

	/*
	 * private JSONObject getTableModel(List<String[]> headers,CommonFilter
	 * commonFilter, String reportNmae) { JqGridTableModel jqGridTableModel = new
	 * JqGridTableModel();
	 * 
	 * String[] colHeader = headers.get(1); //String[] colHeader1 = headers.get(1);
	 * 
	 * String[] emptyrow = new String[colHeader.length];
	 * 
	 * emptyrow [0] =""; emptyrow [1] ="";
	 * 
	 * for( int i = 2; i < colHeader.length;i++ ){ emptyrow [i] = ""; }
	 * 
	 * //jqGridTableModel.getRowHeaders().add(emptyrow);
	 * jqGridTableModel.getRowHeaders().add(colHeader);
	 * //jqGridTableModel.getRowHeaders().add(colHeader1);
	 * 
	 * jqGridTableModel.setSortable(false); jqGridTableModel.setTableButton(true);
	 * jqGridTableModel.setRowNumbers(true); jqGridTableModel.setEnableFilter(true);
	 * jqGridTableModel.setTableHeight(300); jqGridTableModel.setPaginate(true); //
	 * jqGridTableModel.getColModel().add(getColModel("keyid",
	 * 50,"left",true,false,true));
	 * 
	 * for(int i =0; i < colHeader.length; i++) {
	 * CommonMessage.debugMsg("Length : "+colHeader.length); JqGridColModel
	 * jqGridColModel = new JqGridColModel();
	 * //jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
	 * jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));//changed for
	 * filter - swetha -CHANGE CommonMessage.debugMsg("colHeader[i] "+colHeader[i]);
	 * //jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
	 * jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));//changed for filter
	 * - swetha-CHANGE jqGridColModel.setWidth(100);
	 * //jqGridColModel.setAlin("left"); jqGridColModel.setEditable(false);
	 * 
	 * if(i==0||i==1||i==12)//CHANGE (i==0||i==1||i==12||i==14) ->
	 * (i==0||i==1||i==12) { jqGridColModel.setHidden(true);
	 * jqGridColModel.setKey(true);
	 * 
	 * }else if(i == 7 || i == 8 || i == 10 || i == 11) {
	 * jqGridColModel.setWidth(115); jqGridColModel.setAlign("left"); }else if(i ==
	 * 9||i == 23||i == 15||i == 22) { jqGridColModel.setWidth(120);
	 * jqGridColModel.setAlign("left"); }else if(i == 4||i == 3||i == 5||i == 6||i
	 * == 16||i == 17||i == 13){ jqGridColModel.setWidth(80);
	 * jqGridColModel.setAlign("left"); }else if(i == 21){
	 * jqGridColModel.setWidth(160); jqGridColModel.setAlign("left"); }else if (i ==
	 * 12){ jqGridColModel.setWidth(100); jqGridColModel.setAlign("left"); }
	 * 
	 * 
	 * else { if(i==2) { jqGridColModel.setHidden(false);
	 * jqGridColModel.setWidth(120); jqGridColModel.setAlign("left"); } } else {
	 * 
	 * if(i==4 || i == 5 ||i==10 ) { jqGridColModel.setWidth(80);
	 * jqGridColModel.setAlign("center");
	 * 
	 * } else if(i == 11 || i == 2 || i == 3 || i == 9) {
	 * jqGridColModel.setWidth(80); jqGridColModel.setAlign("left"); } else if(i ==
	 * 6 ) jqGridColModel.setWidth(150);
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * if(reportNmae.equals("JhauditActionPlanScore_getCol.JhAuditRpt")) {
	 * //jqGridTableModel.setGroupBy(true);
	 * //jqGridTableModel.setGroupByField("GROUPINGCOLUMN1"); }
	 * 
	 * jqGridTableModel.getColModel().add(jqGridColModel); } JSONObject tableModel =
	 * UIUtils.getJqGridTableModel(jqGridTableModel); return tableModel; }
	 */
	
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter, String reportNmae)
	{
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(1);
		//String[] colHeader1 = headers.get(1);
		
		String[] emptyrow = new String[colHeader.length]; 	
		
		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 2; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		//jqGridTableModel.getRowHeaders().add(emptyrow);		
		jqGridTableModel.getRowHeaders().add(colHeader);
		//jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableHeight(300);
		jqGridTableModel.setPaginate(true);
	//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
		 
		  		for(int i =0; i < colHeader.length; i++)
		{
			CommonFunctions.debugMsg("Length : "+colHeader.length);
			JqGridColModel jqGridColModel = new JqGridColModel();
			//jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));//changed for filter - swetha -CHANGE
			CommonFunctions.debugMsg("colHeader[i] "+colHeader[i]);
			//jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));//changed for filter - swetha-CHANGE
			jqGridColModel.setWidth(100);				
			//jqGridColModel.setAlin("left");
			jqGridColModel.setEditable(false);
			
			if(i==0||i==1||i==13|| i == 11 )//CHANGE (i==0||i==1||i==12||i==14) -> (i==0||i==1||i==12)//CHANGE HERE 
			{//CHANGE HERE 5TH MAY - 12->13 ADDED  i == 11
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}else if(i == 7 || i == 8 )//REMOVED -> || i == 10 || i == 11 - 5TH MAY 
			{
				jqGridColModel.setWidth(115);
				jqGridColModel.setAlign("left");
			}else if(i == 9||i == 23||i == 15||i == 22)
			{
				jqGridColModel.setWidth(120);
				jqGridColModel.setAlign("left");
			}else if(i == 4||i == 3||i == 5||i == 6||i == 16||i == 17||i == 12)//CHANGED I = 13-> I=12 - 5TH MAY
			{
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("left");
			}else if(i == 21){
				jqGridColModel.setWidth(160);
				jqGridColModel.setAlign("left");
			}else if (i == 12){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			
			
			else
			{
				if(i==2)
				{
					jqGridColModel.setHidden(false);
					jqGridColModel.setWidth(120);
					jqGridColModel.setAlign("left");
				}
			}
				/*	else
				{
					
					if(i==4 || i == 5 ||i==10  )
					{
						jqGridColModel.setWidth(80);
						jqGridColModel.setAlign("center");
						
					}
					else if(i == 11 || i == 2 || i == 3 || i == 9)
					{
						jqGridColModel.setWidth(80);
						jqGridColModel.setAlign("left");
					}
					else if(i == 6 )
						jqGridColModel.setWidth(150);
					
				}
					
				
			}
			 	*/
			if(reportNmae.equals("JhauditActionPlanScore_getCol.JhAuditRpt")) {
				//jqGridTableModel.setGroupBy(true);
				//jqGridTableModel.setGroupByField("GROUPINGCOLUMN1");
			}
				
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}

	private JSONObject getTableModelGraph(List<String[]> headers, String caption) {
		CommonMessage.debugMsg("Enter get col model...." + caption);
		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		// String[] colHeader = headers.get(1);
		// String[] colHeader1 = headers.get(2);

		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);

		colHeader[3] = "DATE";
		if (caption.equals("Factory")) {
			caption = "PBU";
		} else if (caption.equals("Section")) {
			caption = "DMT";
		} else if (caption.equals("Line")) {
			caption = "JH";
		}
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonMessage.debugMsg("test..." + colHeader.length);
		for (int i = 2; i < colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;
		String headerSql = "'SELECT ";
		for (int i = 0; i <= colHeader.length - 1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i == 2) {
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

			// headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length() - 1) + " FROM DUAL' ";
		CommonMessage.debugMsg("headerSql.....123..." + headerSql);
		tableModel.set("tableHeight", "67%%");

		CommonMessage.debugMsg("colmodel:" + tableModel);
		return tableModel;
	}

	private void processChartAuditScoreGraph(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = null;

		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type = request.getParameter("type");

		CommonMessage.debugMsg(" Inside Servlet :: " + type);

		CommonMessage.debugMsg(commonFilterIden + " forDashboard.....forDashboard " + forDashboard);
		if (!"true".equals(forDashboard)) {
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterIden);
		} else {
			commonFilter = populateCommonFilter(request, commonFilterIden, false);
		}

		CommonMessage.debugMsg(commonFilter.getFlid() + " commonFilter.getFlid()  " + commonFilter.getFlid());

		CommonFilter chartCommonFilter = new CommonFilter();
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);

		if (UIUtils.isValidKeyId(flid)) {
			chartCommonFilter.setFlid(flid);
			chartCommonFilter.setRowTotal('N');
		} else
			chartCommonFilter.setRowTotal('Y');
		String FirstLevel = request.getParameter("FirstLevel");
		if (UIUtils.isValidKeyId(FirstLevel))
			chartCommonFilter.setFirstLevel(FirstLevel);
		if (UIUtils.isValidKeyId(type)) {
			chartCommonFilter.setAbnViewType(type);
			commonFilter.setAbnViewType(type);
		}

		FilterValues.getCommonFilters(request, chartCommonFilter);

		commonFilter.setType(graphType);
		chartCommonFilter.setType(graphType);

		// commonFilter.setDrillFlag('f');
		List<String[]> jhAuditScoreList = jhAuditRptService.getAllAuditScoreGraph(chartCommonFilter);

		JSONObject chartObj = null;
		if (jhAuditScoreList != null && jhAuditScoreList.size() > 0)

		{
			String flids = CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("flid is :::" + flids);
			String lcnname = dashboardService.Functionallocn(flids);
			chartObj = processLineChartIncident(lcnname, jhAuditScoreList, chartCommonFilter);
			UIUtils.dashBoardSetChartObject(request, chartObj);
			CommonMessage.debugMsg("chartObj =" + chartObj);
			PrintWriter out = response.getWriter();
			out.print(chartObj);
			out.close();
		}
	}

	private JSONObject processLineChartIncident(String titlename, List<String[]> jhAuditScoreList,
			CommonFilter commonFilter) {

		if (jhAuditScoreList == null || jhAuditScoreList.size() <= 2)
			return null;

		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		// String title = commonFilter.getAbnViewType() + " Self Audit Score ";
		String title = titlename + "- Self Audit Score ";

		int index = 0;
		if (jhAuditScoreList != null)
			index = jhAuditScoreList.size() - 1;

		String[] header = jhAuditScoreList.get(1);
		String[] month = jhAuditScoreList.get(0);
		String[] data = jhAuditScoreList.get(2);
		String prevMonth = null;

		int rSize = jhAuditScoreList.size() - 2;
		int cSize = header.length - 5;

		String subTitle = data[3];
		// CommonMessage.debugMsg("subTitle "+subTitle );

		List<Double> identifiedData = new ArrayList<Double>();

		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> completedData = new ArrayList<Double>();

		List<Double[]> graphData = new ArrayList<Double[]>();
		int rno = -1;
		Double[][] grData = new Double[rSize][cSize];
		for (int i = 5; i < header.length; i++) {
			rno = rno + 1;
			for (int j = 2; j < jhAuditScoreList.size(); j++) {
				data = jhAuditScoreList.get(j);
				if (header[i].contains("SCORE")) {
					grData[j - 2][i - 5] = Double.parseDouble(data[i]);
				}
				/*
				 * else if(header[i].contains("COMPLETED") ){
				 * completedData.add(Double.parseDouble(data[i])); }
				 */
				// prevMonth = month[i];
			}
			// if( prevMonth == null || ! month[i].equals(prevMonth) ){
			xAxisCategory.add(month[i]);
			// }
		}

		for (int k = 0; k < grData.length; k++) {

			ChartSeries timeSeries = new ChartSeries();
			timeSeries.setData(Arrays.asList(grData[k]));

			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);

			// timeSeries.setName("SCORE "+k);
			timeSeries.setName(jhAuditScoreList.get(k + 2)[4]);

			chartSeriesList.add(timeSeries);
			// yAxis.getTitle().setText("Implemented");
			/*
			 * ChartYAxis yAxis = new ChartYAxis(); yAxis.setMin(0);
			 * yAxis.getTitle().setText("Total Score"); chartYAxis.add(yAxis);
			 */
		}
		ChartYAxis yAxis = new ChartYAxis();
		yAxis.setMin(0);
		yAxis.getTitle().setText("Total Score");
		chartYAxis.add(yAxis);
		ChartXAxis xaxis = new ChartXAxis();
		if (commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, subTitle, chartYAxis);

	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);

		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();

			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getAudit(request, commonFilter);
			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {

				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				CommonMessage.debugMsg("CommonFunctions.getFirstDateofMonth(-5).substring(3,11)"
						+ CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}

			CommonMessage.debugMsg("SKIP" + commonFilter.getChkSkipLine());
			commonFilter.setViewClick('Y');

			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg(
				"test to be conducted................" + commonFilter.getFromRow() + "----" + commonFilter.getToRow());
		return commonFilter;
	}

}
