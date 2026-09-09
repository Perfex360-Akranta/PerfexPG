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
import com.akranta.tpm.service.UserAccessReviewService;
import com.akranta.tpm.service.impl.UserAccessReviewServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class UserAccessReviewReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserAccessReviewService userAccessReviewService;

	public UserAccessReviewReportServlet() throws Exception {
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
			userAccessReviewService = (UserAccessReviewServiceImpl) UIUtils.getServiceObject(request, "UserAccessReviewServiceImpl");
			userAccessReviewService.UserAccessReviewServiceImplJwt(
			    (String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
			);
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		// Load the input page
		if (action.equals("UsrAccessRpt_input.uarr")) {
			CommonFunctions.debugMsg("Loading UserAccessReview input page");
			CommonFilter commonFilter = populateCommonFilter(request, "UserAccessReviewCommonFilter", true);
			request.setAttribute("excelView", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "excelView"));
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/UserAccessReviewReport.jsp");
			rd.forward(request, response);
		}

		// Get column configuration
		else if (action.equals("UsrAccessRpt_getCol.uarr")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();

			CommonFilter commonFilter = populateCommonFilter(request, "UserAccessReviewCommonFilter", true);

			commonFilter.setViewClick('Y');
			commonFilter.setIsGetCol("Y");
			List<String[]> userAccessReviewList = userAccessReviewService.getUserAccessReviewRept(commonFilter);

			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);

			String[] colHeader = userAccessReviewList.get(1);
			String[] colHeaderCond = userAccessReviewList.get(0);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "100%%");

			httpSession.removeAttribute("UserAccessReviewColModel");
			httpSession.setAttribute("UserAccessReviewColModel", jsonObject);

			CommonFunctions.debugMsg("jsonObject = " + jsonObject);
			out.println(jsonObject);
		}

		// Get report data
		else if (action.equals("UsrAccessRpt_getData.uarr")) {
			try {
				UIUtils.displayRequestParamsValue(request);
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession();

				CommonFilter commonFilter = populateCommonFilter(request, "UserAccessReviewCommonFilter", false);

				FilterValues.getCommonFilters(request, commonFilter);

				commonFilter.setIsGetCol("N");
				List<String[]> userAccessReviewQueryList = userAccessReviewService.getUserAccessReviewRept(commonFilter);
				JSONObject userAccessReviewData = UIUtils.convertToJqGridTableObject(userAccessReviewQueryList, request, 2, 0, commonFilter.getTotalRecordCnt());

				out.println(userAccessReviewData);
				commonFilter.setViewClick('N');

				httpSession.removeAttribute("UserAccessReviewCommonFilter");
				httpSession.setAttribute("UserAccessReviewCommonFilter", commonFilter);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

		// Export to Excel
		else if (action.equals("UsrAccessRpt_getExcel.uarr")) {
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "UserAccessReviewCommonFilter", false);

			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);

			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);

			String fromdate = commonFilter.getFromDate();
			String todate = commonFilter.getToDate();
			String date = fromdate + "  -  " + todate;

			String format = ExcelUtils.getFormat(request);

			Workbook wb = userAccessReviewService.UserAccessReviewExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "UserAccessReviewReport", format);
		}
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);

		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);

		if (commonFilter != null && !createNew) {
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
