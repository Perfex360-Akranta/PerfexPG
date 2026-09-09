package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.MasterMenuReptService;
import com.akranta.tpm.service.impl.MasterMenuReptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class MasterMenuReportServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MasterMenuReptService masterMenuReptService;
	
	public MasterMenuReportServlet() throws Exception {
		super();
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
	
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		String action = UIUtils.getActionPart(request);
		
		try {
			HttpSession httpSession = request.getSession(false);
			masterMenuReptService = (MasterMenuReptServiceImpl) UIUtils.getServiceObject(request, "MasterMenuReptServiceImpl");
			masterMenuReptService.MasterMenuReptServiceImplJwt(
			    (String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
			);
		//	masterMenuReptService = (MasterMenuReptServiceImpl) UIUtils.getServiceObject(request, "MasterMenuReptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		// Load the input page
		if(action.equals("MstAccessRpt_input.marr")) {
			CommonFunctions.debugMsg("Loading MasterMenuReport input page");
			CommonFilter commonFilter = populateCommonFilter(request, "MasterMenuReportCommonFilter", true);
			request.setAttribute("excelView", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "excelView"));
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/MasterMenuReport.jsp");
			rd.forward(request, response);
		}
		
		// Get column configuration
		else if(action.equals("MstAccessRpt_getCol.marr")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter = populateCommonFilter(request, "MasterMenuReportCommonFilter", true);
			
			// Set default dates if not provided
			/*
			 * if(Constants.passNullDate.contains(commonFilter.getFromDate())) {
			 * commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),
			 * -1)); commonFilter.setToDate(CommonFunctions.getDate()); }
			 */
			
			commonFilter.setViewClick('Y');
			commonFilter.setIsGetCol("Y");
			List<String[]> masterMenuList = masterMenuReptService.getMasterMenuRept(commonFilter);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			
			String[] colHeader = masterMenuList.get(1);
			String[] colHeaderCond = masterMenuList.get(0);
			 
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "80%%");
			
			httpSession.removeAttribute("MasterMenuReportColModel");
			httpSession.setAttribute("MasterMenuReportColModel", jsonObject);
			
			CommonFunctions.debugMsg("jsonObject = " + jsonObject);
			out.println(jsonObject);
		}
		
		// Get report data
		else if(action.equals("MstAccessRpt_getData.marr")) {
			try {
				UIUtils.displayRequestParamsValue(request);
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession();
				
				CommonFilter commonFilter = populateCommonFilter(request, "MasterMenuReportCommonFilter", false);
				
				FilterValues.getCommonFilters(request, commonFilter);
				
				/*
				 * // Set default dates if not provided
				 * if(Constants.passNullDate.contains(commonFilter.getFromDate())) {
				 * commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),
				 * -1)); commonFilter.setToDate(CommonFunctions.getDate()); }
				 */
				commonFilter.setIsGetCol("N");
				List<String[]> masterMenuQueryList = masterMenuReptService.getMasterMenuRept(commonFilter);
				JSONObject masterMenuData = UIUtils.convertToJqGridTableObject(masterMenuQueryList, request, 2, 0, commonFilter.getTotalRecordCnt());
				
				out.println(masterMenuData);
				commonFilter.setViewClick('N');
				
				httpSession.removeAttribute("MasterMenuReportCommonFilter");
				httpSession.setAttribute("MasterMenuReportCommonFilter", commonFilter);
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		// Export to Excel
		else if(action.equals("MstAccessRpt_getExcel.marr")) {
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "MasterMenuReportCommonFilter", false);
			
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			//JSONObject tblJSONObj = UIUtils.getXlColModel(request, httpSession);
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			
			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date = fromdate + "  -  " + todate;
			
			/*
			 * if(!Constants.passNullDate.contains(commonFilter.getFromDate()) ||
			 * !Constants.passNullDate.contains(commonFilter.getToDate())) {
			 * tblJSONObj.put("title", "Master Menu Report - " + date); } else {
			 * tblJSONObj.put("title", "Master Menu Report"); }
			 */
			
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = masterMenuReptService.MasterMenuReportExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "MasterMenuReport", format);
		}
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		
		if(commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
}
