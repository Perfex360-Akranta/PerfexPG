package com.akranta.tpm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartRadar;
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
import com.akranta.tpm.service.SkillIndexReportService;
import com.akranta.tpm.service.impl.SkillIndexReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SkillIndexReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	SkillIndexReportService skillIndexReportService;
	DashboardService dashboardService;
	private static final String commonFilterScore = "SkillIndexScorecommonFilter";

	public SkillIndexReportServlet() {
		super();

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

		String action = UIUtils.getActionPart(request);
		HttpSession h = request.getSession(false);
		skillIndexReportService = (SkillIndexReportServiceImpl) UIUtils.getServiceObject(request,
				"SkillIndexReportServiceImpl");
		dashboardService = (DashboardServiceImpl) UIUtils.getServiceObject(request, "DashboardServiceImpl");

		skillIndexReportService.SkillIndexReportServiceImplJwt(
				(String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")));
		CommonMessage.debugMsg("action..." + action);
		String dispatchUrl = null;

		if (action.equals("SkillTopicTask_input.skillIndex")) {
			String filter = request.getParameter("filter");
			CommonMessage.debugMsg("this is this.....");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/TopicTask.jsp");
			request.setAttribute("filter", filter);
			rd.forward(request, response);
		} else if (action.equals("SkillTopicTask_getCol.skillIndex")) {
			// out.println(getEquipmentColumnModel());
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
			List<String[]> skillIndexList = skillIndexReportService.getTopicTask(commonFilter);

			JSONObject jsonObject = new JSONObject();
			String[] header = { "Topic" };
			jsonObject = getTableModel(skillIndexList, header);
			CommonMessage.debugMsg("colModel   " + jsonObject);

			// JSONObject jsonObject = getTableModel(yyQtyList);
			jsonObject.put("tableHeight", "75%%");
			jsonObject.put("tableWidth", "50%%");
			jsonObject.put("multiSelect", true);
			out.println(jsonObject);
			/*
			 * CommonMessage.debugMsg(" after populate col ");
			 * out.println(UIUtils.getPropertyValue(
			 * "com.akranta.tpm.resources.EquipmentRpt", "EqpQuery"));
			 */
		}

		else if (action.equals("SkillTopicTask_getData.skillIndex")) {
			try {
				HttpSession httpSession = request.getSession(false);
				UIUtils.displayRequestParamsValue(request);

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
				List<String[]> skillIndexList = skillIndexReportService.getTopicTask(commonFilter);
				CommonMessage.debugMsg("equipmentQueryList " + skillIndexList.size());
				PrintWriter out = response.getWriter();
				JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(skillIndexList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(equipmentQueryData);

				commonFilter.setViewClick('N');

				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		/*
		 * else if(action.equals("callProcedureAverageSkillIndex.skillIndex")) { String
		 * flid = request.getParameter("flid"); String date =
		 * request.getParameter("date"); paramValues.add(commonFilter.getdFromDate());
		 * // P_REVIEW_HALF paramValues.add(commonFilter.getFlid()); CommonFilter
		 * commonFilter = new CommonFilter(); commonFilter.setdFromDate(date);
		 * commonFilter.setFlid(flid);
		 * skillIndexReportService.callProcedureAverageSkillIndex(commonFilter); }
		 */

		/*
		 * else if (action.equals("callProcedureAverageSkillIndex.skillIndex")) { String
		 * flid = request.getParameter("flid"); String date =
		 * request.getParameter("date"); String sectionId =
		 * request.getParameter("sectionId"); String cellId =
		 * request.getParameter("cellId");
		 * 
		 * paramValues.add(commonFilter.getdFromDate()); // P_REVIEW_HALF
		 * paramValues.add(commonFilter.getFlid());
		 * 
		 * CommonFilter commonFilter = new CommonFilter();
		 * commonFilter.setdFromDate(date); commonFilter.setFlid(flid);
		 * commonFilter.setSect(sectionId); commonFilter.setCellId(cellId);
		 * skillIndexReportService.callProcedureAverageSkillIndex(commonFilter); }
		 */

		else if (action.equals("callProcedureAverageSkillIndex.skillIndex")) {
			String flid = request.getParameter("flid");
			String date = request.getParameter("date");
			String sectionId = request.getParameter("sectionId");
			String cellId = request.getParameter("cellId");
			/*
			 * paramValues.add(commonFilter.getdFromDate()); // P_REVIEW_HALF
			 * paramValues.add(commonFilter.getFlid());
			 */
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setdFromDate(date);
			commonFilter.setFlid(flid);
			commonFilter.setSect(sectionId);
			commonFilter.setCellId(cellId);
			String result = skillIndexReportService.callProcedureAverageSkillIndex(commonFilter);
			response.setContentType("text/plain"); // ← add this
			response.setCharacterEncoding("UTF-8"); // ← add this
			response.getWriter().write(result);
			response.getWriter().flush();
		}

		else if (action.equals("SkillTask_getCol.skillIndex")) {
			// out.println(getEquipmentColumnModel());
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
			List<String[]> skillIndexList = skillIndexReportService.getTask(commonFilter);

			JSONObject jsonObject = new JSONObject();
			String[] header = { "Task", "KSA", "Target Skill Rate" };
			jsonObject = getTableModel(skillIndexList, header);
			CommonMessage.debugMsg("colModel   " + jsonObject);

			// JSONObject jsonObject = getTableModel(yyQtyList);
			jsonObject.put("tableHeight", "75%%");
			jsonObject.put("tableWidth", "50%%");
			jsonObject.put("multiSelect", true);
			out.println(jsonObject);
			/*
			 * CommonMessage.debugMsg(" after populate col ");
			 * out.println(UIUtils.getPropertyValue(
			 * "com.akranta.tpm.resources.EquipmentRpt", "EqpQuery"));
			 */
		}

		else if (action.equals("SkillTask_getData.skillIndex")) {
			try {
				HttpSession httpSession = request.getSession(false);
				UIUtils.displayRequestParamsValue(request);

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
				List<String[]> skillIndexList = skillIndexReportService.getTask(commonFilter);
				CommonMessage.debugMsg("equipmentQueryList " + skillIndexList.size());
				PrintWriter out = response.getWriter();
				JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(skillIndexList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(equipmentQueryData);

				commonFilter.setViewClick('N');

				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		if (action.equals("SkillIndexRpt_input.skillIndex")) {
			String filter = request.getParameter("filter");
			CommonMessage.debugMsg("this is this.....");
			String flid = request.getParameter("flid");
			String uniqPosid = request.getParameter("uniqPosid");
			String reviewDate = request.getParameter("reviewDate");
			String reviewType = request.getParameter("reviewType");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/SkillIndexReport.jsp");
			request.setAttribute("filter", filter);
			request.setAttribute("flid", flid);
			request.setAttribute("uniqPosid", uniqPosid);
			request.setAttribute("reviewDate", reviewDate);
			request.setAttribute("reviewType", reviewType);
			rd.forward(request, response);
		}

		else if (action.equals("SkillIndexRpt_getCol.skillIndex")) {
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			String uniPosID = request.getParameter("uniPosID");
			String reviewDate = request.getParameter("reviewDate");
			String reviewType = request.getParameter("reviewType");
			UIUtils.displayRequestParamsValue(request);
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
			commonFilter.setFlid(flid);
			commonFilter.setUniquePos(uniPosID);
			commonFilter.setFromDate(reviewDate);
			commonFilter.setToDate(reviewDate);
			commonFilter.setType(reviewType);
			List<String[]> skillIndexList = skillIndexReportService.getSkillIndex(commonFilter);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setSortable(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setRowNumbers(true);
			gridColModel.setHeaderNum(4);

			CommonMessage.debugMsg("Printing the value ");
			for (String[] arr : skillIndexList) {
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
			String[] colHeaderCond = skillIndexList.get(0);
			String[] colHeader1 = skillIndexList.get(3);
			String[] colHeader2 = skillIndexList.get(2);
			String[] colHeader3 = skillIndexList.get(1);
			String[] colHeader4 = skillIndexList.get(4);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			headers.add(colHeader2);
			headers.add(colHeader3);
			headers.add(colHeader4);
			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);

			CommonMessage.debugMsg("table model" + jsonObject.toString());
			jsonObject.put("tableHeight", "75%%");
			jsonObject.put("tableWidth", "108%%");

			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("skillIndexColModel");
			httpSession.setAttribute("skillIndexColModel", jsonObject);
			CommonMessage.debugMsg(jsonObject + "  jsonObject  " + skillIndexList);
			JSONObject modifiedJson = new JSONObject(jsonObject.toString());
			JSONArray rowHeaders = modifiedJson.getJSONArray("rowHeaders");

			// Only index 3 contains values like 2.29#BCM00303
			if (rowHeaders.length() > 3) {

				JSONArray scoreRow = rowHeaders.getJSONArray(3);

				for (int j = 0; j < scoreRow.length(); j++) {
					String value = scoreRow.optString(j);

					if (value.contains("#")) {
						value = value.substring(0, value.indexOf("#"));
						scoreRow.put(j, value);
					}
				}

				rowHeaders.put(3, scoreRow);
			}

			modifiedJson.put("rowHeaders", rowHeaders);
			CommonMessage.debugMsg("Modified JSON " + modifiedJson);

			out.println(modifiedJson);
		} else if (action.equals("SkillIndexRadarChart.skillIndex")) {
			skillIndexRadarChart(request, response);
		} else if (action.equals("exportRadarChart.skillIndex")) {
			String dataGrid = request.getParameter("dataGrid");
			String empNames = request.getParameter("empNames");
			String[] dataGridArr = dataGrid.split(";");
			String[] empNamesArr = empNames.split(";");

			List<String[]> dataList = new ArrayList<String[]>();
			List<String[]> nameList = new ArrayList<String[]>();
// Kiran Changed on 28May2026
			for (int i = 0; i < dataGridArr.length; i++) {

			    if (dataGridArr[i] == null ||
			        dataGridArr[i].trim().isEmpty()) {
			        continue;
			    }

			    String gridDatas = dataGridArr[i];

			    // remove starting and ending brackets safely
			    if (gridDatas.startsWith("[") && gridDatas.endsWith("]")) {
			        gridDatas = gridDatas.substring(1, gridDatas.length() - 1);
			    }

			    JSONObject jsonObject = JSONObject.fromString(gridDatas);

			    String dataStr = "";

			    if (jsonObject.has("data")) {
			        dataStr = jsonObject.getString("data");
			    }

			    if (dataStr.startsWith("[") && dataStr.endsWith("]")) {
			        dataStr = dataStr.substring(1, dataStr.length() - 1);
			    }

			    String[] dataArr = dataStr.split(",");

			    String[] nameArr = {
			        (i < empNamesArr.length ? empNamesArr[i] : "")
			    };

			    dataList.add(dataArr);
			    nameList.add(nameArr);

			    System.out.println("Added Graph : " + i);
			
			
			}
			String format = ExcelUtils.getFormat(request);
			String path = UIUtils.getExcelTemplatePath(request);
			try {
				Workbook wb = fillRadarExcelValues(request, dataList, nameList, path, format);
				ExcelUtils.writeToResponse(response, wb, "RadarChart", format);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (action.equals("SkillIndexRpt_getData.skillIndex")) {
			try {
				CommonMessage.debugMsg("GETDATA....   ");
				HttpSession httpSession = request.getSession(false);

				String flid = request.getParameter("flid");
				String uniPosID = request.getParameter("uniPosID");
				String reviewDate = request.getParameter("reviewDate");
				String reviewType = request.getParameter("reviewType");
				CommonMessage.debugMsg("uniPosID" + uniPosID);
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);

				CommonMessage.debugMsg("uniPosID==" + uniPosID);
				commonFilter.setFlid(flid);
				commonFilter.setUniquePos(uniPosID);
				commonFilter.setFromDate(reviewDate);
				commonFilter.setToDate(reviewDate);
				commonFilter.setType(reviewType);

				List<String[]> skillIndexList = skillIndexReportService.getSkillIndex(commonFilter);

				CommonMessage.debugMsg("equipmentQueryList " + skillIndexList.size());
				PrintWriter out = response.getWriter();
				JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(skillIndexList, request, 5, 0,
						commonFilter.getTotalRecordCnt());
				out.println(equipmentQueryData);

				commonFilter.setViewClick('N');

				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("SkillIndexRpt_getExcel.skillIndex")) {

			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
			String reviewDate = request.getParameter("reviewDate");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			// String tableModel =
			// UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
			// "EqpQuery");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("skillIndexColModel");

			tblJSONObj.put("title", "Skill Index Report - " + reviewDate);
			String format = ExcelUtils.getFormat(request);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {

				String flid = request.getParameter("flid");
				String uniPosID = request.getParameter("uniPosID");
				// String reviewDate = request.getParameter("reviewDate");
				CommonMessage.debugMsg("uniPosID" + uniPosID);
				UIUtils.displayRequestParamsValue(request);
				CommonMessage.debugMsg("uniPosID==" + uniPosID);
				commonFilter.setFlid(flid);
				commonFilter.setUniquePos(uniPosID);
				commonFilter.setFromDate(reviewDate);
				commonFilter.setToDate(reviewDate);
			}

			// List<String[]> skillIndexList =
			// skillIndexReportService.getSkillIndex(commonFilter);

			Workbook wb = skillIndexReportService.skillIndexReportExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "SkillIndexReport", format);

		}

//	else if(action.equals("AverageSkillIndexRpt_input.skillIndex"))
//	{
//		CommonMessage.debugMsg("AverageSkillIndexScore_input.skillIndex");
//		request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
//	/*	String fromMonth=request.getParameter("fromMnth");
//		String toMonth=request.getParameter("toMnth");*/
//		String flid1=request.getParameter("flid1");
//		String flid=request.getParameter("dtFlid");
//		String fromMonth=request.getParameter("dtFromMonth");
//		String toMonth=request.getParameter("dtToMonth");
//		CommonMessage.debugMsg(fromMonth+" In side the INput "+toMonth);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/avgSkillIndexScore.jsp");
//		request.setAttribute("toMonth", toMonth);
//		request.setAttribute("fromMonth", fromMonth);
//		rd.forward(request, response); 
//	}

		else if (action.equals("AverageSkillIndexRpt_input.skillIndex")) {
			CommonMessage.debugMsg("AverageSkillIndexScore_input.skillIndex");
			request.setAttribute("GraphMsg",
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "Forgraph"));
			/*
			 * String fromMonth=request.getParameter("fromMnth"); String
			 * toMonth=request.getParameter("toMnth");
			 */
			String flid1 = request.getParameter("flid1");
			String flid = request.getParameter("dtFlid");
			String fromMonth = request.getParameter("dtFromMonth");
			String toMonth = request.getParameter("dtToMonth");
			CommonMessage.debugMsg(fromMonth + " In side the INput " + toMonth);
			String halfyear = "";// Changed Here Swetha

			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/avgSkillIndexScore.jsp");
			request.setAttribute("toMonth", toMonth);
			request.setAttribute("fromMonth", fromMonth);
			request.setAttribute("halfYear", halfyear);// Changed Here Swetha

			rd.forward(request, response);
		}

		else if (action.equals("AverageSkillIndexScore_getCol.skillIndex")) {
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			String firstClick = request.getParameter("firstClick");

			String type = request.getParameter("type");

			String keyId = request.getParameter("Keyid");
			String drill = request.getParameter("filt");
			String totalScore = request.getParameter("totalsr");
			String fromMonth1 = request.getParameter("fromMnth");
			String toMonth1 = request.getParameter("toMnth");
			String fromMonth = request.getParameter("dtFromMonth");
			String toMonth = request.getParameter("dtToMonth");
			String flid1 = request.getParameter("flid1");
			String flid = request.getParameter("dtFlid");
			String cellId = request.getParameter("cmbCellid");
			String sectionId = request.getParameter("cmbSectid");

			CommonMessage.debugMsg("cell id  " + cellId + " Section " + sectionId);

			CommonMessage.debugMsg(toMonth + "  1  fromMnthfromMnthfromMnthfromMnth  " + fromMonth + " fromMonth1 "
					+ fromMonth1 + " toMonth1 " + toMonth1);

			CommonMessage.debugMsg(keyId + " keyIdkeyIdkeyIdkeyId");

			String halfyear = request.getParameter("halfyear"); // Changed Here Swetha

			CommonMessage.debugMsg("keyIdkeyId" + keyId);
			String drillLevel = "SECT";
			if (cellId != null && !cellId.trim().isEmpty() && sectionId != null && !sectionId.trim().isEmpty()) {

				drillLevel = "CELL";
			}

			if (keyId != null) {
				drillLevel = keyId.substring(0, 3);
			}
			if (drillLevel.equals("CEL")) {
				drillLevel = "EMP";
			} else if (drillLevel.equals("SEC")) {
				drillLevel = "CELL";
			}
			CommonMessage.debugMsg(" Inside getCol " + type);
			CommonMessage.debugMsg(" Inside getCol " + drillLevel);

			CommonFilter commonFilter = populateCommonFilter(request, commonFilterScore, true);
			commonFilter.setYear(halfyear);// Changed Here Swetha

			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterScore);
			}

			if (commonFilter == null) {
				commonFilter = new CommonFilter();
				commonFilter = populateCommonFilter(request, commonFilterScore, true);

			}
			if (UIUtils.isValidKeyId(type))
				commonFilter.setAbnViewType(type);

			commonFilter.setDrillLevel(drillLevel);
			// commonFilter.setTotal(totalScore);
			httpSession.removeAttribute(commonFilterScore);
			httpSession.setAttribute(commonFilterScore, commonFilter);
			// FilterValues.getCommonFilters(request, commonFilter);
			// FilterValues.getSafty(request, commonFilter);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}
			if (fromMonth1 == null || fromMonth1 == " ") {

				fromMonth1 = commonFilter.getFromMonth();
				toMonth1 = commonFilter.getToMonth();

			}

			commonFilter.setFromMonth(fromMonth);
			commonFilter.setToMonth(toMonth);
			if (flid1 != null) {
				if (!flid1.equals(flid)) {
					commonFilter.setFlid(flid1);
				}
			}

			CommonMessage.debugMsg(commonFilter.getFlid() + "  toMonthtoMonthtoMonthtoMonthtoMonth " + toMonth1
					+ "  ......From MOnth    " + commonFilter.getFromMonth());

			List<String[]> impVscomList = skillIndexReportService.getavgSkillScoreGraph(commonFilter);
			JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
			/*
			 * String [] colHeaderHead = impVscomList.get(1); String [] colHeader =
			 * impVscomList.get(2); String caption=FilterValues.getHeader(
			 * commonFilter.getDrillCaption());
			 */
			CommonMessage.debugMsg("drill " + commonFilter.getDrillCaption());
			commonFilter.setDrillCaption("COMP");
			JSONObject jsonObject = getTableModelGraph(impVscomList,
					FilterValues.getHeader(commonFilter.getDrillCaption()));
			jsonObject.set("tableHeight", "73%%");
			request.setAttribute("fromMonth", commonFilter.getFromMonth());
			request.setAttribute("toMonth", commonFilter.getToMonth());
			CommonMessage.debugMsg("Half Year value before attribute" + halfyear);
			request.setAttribute("halfYear", halfyear);// Changed Here Swetha
			jsonObject.set("tableWidth", "107%%");
			httpSession.removeAttribute("avgSkillScoreData");
			httpSession.setAttribute("avgSkillScoreData", jsonObject);
			CommonMessage.debugMsg("Table Model:" + jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("AverageSkillIndexScore_getData.skillIndex")) {
			try {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request, commonFilterScore, false);
				response.setContentType("text/html");
				httpSession.getAttribute(commonFilterScore);

				String type = request.getParameter("type");
				String totalScore = request.getParameter("totalsr");
				String keyId = request.getParameter("Keyid");
				String fromMonth = request.getParameter("dtFromMonth");
				String toMonth = request.getParameter("dtToMonth");
				// Swetha addition
				String cellId = request.getParameter("cmbCellid");
				String sectionId = request.getParameter("cmbSectid");

				String halfyear = request.getParameter("halfyear");// Changed Here Swetha
				commonFilter.setYear(halfyear);// Changed Here Swetha

				CommonMessage.debugMsg("cell id  " + cellId + " Section " + sectionId);

				CommonMessage.debugMsg(toMonth + "    fromMnthfromMnthfromMnthfromMnth  " + fromMonth);

				String drillLevel = "SECT";
				String drill = request.getParameter("filt");
				CommonMessage.debugMsg("dRILL " + drill);
				if (cellId != null && !cellId.trim().isEmpty() && sectionId != null && !sectionId.trim().isEmpty()) {

					drillLevel = "CELL";
				}

				if (keyId != null) {
					// drillLevel=keyId.substring(0,3);
					drillLevel = keyId.substring(0, 3);

				}
				if (keyId != null) {
					drillLevel = keyId.substring(0, 3);
				}
				if (drillLevel.equals("CEL")) {
					drillLevel = "EMP";
				} else if (drillLevel.equals("SEC")) {
					drillLevel = "CELL";
				}
				commonFilter.setDrillLevel(drillLevel);

				CommonMessage.debugMsg(keyId + " Inside getData " + commonFilter.getDrillLevel());

				if (UIUtils.isValidKeyId(type))
					commonFilter.setAbnViewType(type);
				// commonFilter.setTotal(totalScore);

				CommonMessage.debugMsg("commonFilter.getFlid()  " + commonFilter.getFlid());
				if (fromMonth == null || fromMonth == " ") {

					fromMonth = commonFilter.getFromMonth();
					toMonth = commonFilter.getToMonth();

				}
				if (halfyear == null)

				{
					halfyear = commonFilter.getYear();
				}

				commonFilter.setFromMonth(fromMonth);
				commonFilter.setToMonth(toMonth);

				List<String[]> impVscomList = skillIndexReportService.getavgSkillScoreGraph(commonFilter);

				JSONObject listToJsonObject = new JSONObject();

				CommonMessage.debugMsg("size of dash..." + impVscomList.size());
				if (impVscomList != null && impVscomList.size() > 2)
					listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
				httpSession.removeAttribute(commonFilterScore);
				httpSession.setAttribute(commonFilterScore, commonFilter);

				httpSession.setAttribute("flidTradeWise", commonFilter.getFlid());
				httpSession.setAttribute("fromMonth", fromMonth);
				httpSession.setAttribute("toMonth", toMonth);
				CommonMessage.debugMsg("Half Year value before attribute" + halfyear);
				httpSession.setAttribute("halfYear", halfyear);// Changed Here Swetha
				request.setAttribute("halfYear", halfyear);// Changed Here Swetha
				PrintWriter out = response.getWriter();
				/*
				 * request.setAttribute("fromMonth", fromMonth); request.setAttribute("toMonth",
				 * toMonth);
				 */
				out.println(listToJsonObject);
			}

			catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}

//	else if (action.equals("AverageSkillIndexScore_getCol.skillIndex")) {
//		HttpSession httpSession = request.getSession(false);
//		PrintWriter out = response.getWriter();
//		String firstClick = request.getParameter("firstClick");
//		
//		String type = request.getParameter("type");
//		
//		String keyId=request.getParameter("Keyid");
//		String drill=request.getParameter("filt");
//		String totalScore=request.getParameter("totalsr");
//		String fromMonth1=request.getParameter("fromMnth");
//		String toMonth1=request.getParameter("toMnth");
//		String fromMonth=request.getParameter("dtFromMonth");
//		String toMonth=request.getParameter("dtToMonth");
//		String flid1=request.getParameter("flid1");
//		String flid=request.getParameter("dtFlid");
//		String cellId = request.getParameter("cmbCellid");
//		String sectionId = request.getParameter("cmbSectid");
//		
//		CommonMessage.debugMsg("cell id  "+cellId+" Section "+sectionId);
//		
//		CommonMessage.debugMsg(toMonth +"  1  fromMnthfromMnthfromMnthfromMnth  "+fromMonth +" fromMonth1 "+fromMonth1 +" toMonth1 "+toMonth1);
//
//		CommonMessage.debugMsg(keyId +" keyIdkeyIdkeyIdkeyId");
//		
//		
//		CommonMessage.debugMsg("keyIdkeyId"+keyId);
//		String drillLevel="SECT";
//		if (cellId != null && !cellId.trim().isEmpty()
//		        && sectionId != null && !sectionId.trim().isEmpty()) {
//
//			drillLevel = "CELL";
//		}
//		
//		if(keyId!=null){
//		 drillLevel= keyId.substring(0,3);
//		}
//		if(drillLevel.equals("CEL")){
//			drillLevel="EMP";
//		}
//		else if(drillLevel.equals("SEC")){
//			drillLevel="CELL";
//		}
//		CommonMessage.debugMsg(" Inside getCol "+type);
//		CommonMessage.debugMsg(" Inside getCol "+drillLevel);
//
//		
//		CommonFilter commonFilter=populateCommonFilter(request,commonFilterScore,true);
//
//		if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
//			 commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterScore);
//		}
//		
//		if (commonFilter == null){
//			commonFilter = new CommonFilter();
//		commonFilter=populateCommonFilter(request,commonFilterScore,true);
//		
//
//		}
//		if(UIUtils.isValidKeyId(type))
//			commonFilter.setAbnViewType(type);
//			
//		commonFilter.setDrillLevel(drillLevel);
//		//commonFilter.setTotal(totalScore);
//		httpSession.removeAttribute(commonFilterScore);
//		httpSession.setAttribute(commonFilterScore, commonFilter);
//		//FilterValues.getCommonFilters(request, commonFilter);
//		//FilterValues.getSafty(request, commonFilter);
//		
//		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
//		{
//			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
//			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
//			commonFilter.setMonwise("Y");
//		}
//		if(fromMonth1==null || fromMonth1==" "){
//			
//			fromMonth1=commonFilter.getFromMonth();
//			toMonth1=commonFilter.getToMonth();
//	
//		}
//		
//			commonFilter.setFromMonth(fromMonth);
//			commonFilter.setToMonth(toMonth);
//			if(flid1!=null){
//			if(!flid1.equals(flid)){
//				commonFilter.setFlid(flid1);
//			}
//			}
//		
//			CommonMessage.debugMsg(commonFilter.getFlid() +"  toMonthtoMonthtoMonthtoMonthtoMonth "+toMonth1+"  ......From MOnth    "+commonFilter.getFromMonth());
//	
//		List<String[]> impVscomList = skillIndexReportService.getavgSkillScoreGraph(commonFilter);
//		JSONObject impVscomData = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 2);
//		/*String [] colHeaderHead = impVscomList.get(1);
//		 String [] colHeader = impVscomList.get(2);		
//		 String caption=FilterValues.getHeader( commonFilter.getDrillCaption());*/
//		CommonMessage.debugMsg("drill "+commonFilter.getDrillCaption());
//		commonFilter.setDrillCaption("COMP");
//		JSONObject jsonObject = getTableModelGraph(impVscomList,FilterValues.getHeader(commonFilter.getDrillCaption()));
//		jsonObject.set("tableHeight", "73%%");
//		request.setAttribute("fromMonth", commonFilter.getFromMonth());
//		request.setAttribute("toMonth", commonFilter.getToMonth());
//		jsonObject.set("tableWidth", "107%%");
//		httpSession.removeAttribute("avgSkillScoreData");
//		httpSession.setAttribute("avgSkillScoreData", jsonObject);
//		CommonMessage.debugMsg("Table Model:" + jsonObject);
//		out.println(jsonObject);
//	} 

//	else if (action.equals("AverageSkillIndexScore_getData.skillIndex")) {
//		try {
//			HttpSession httpSession = request.getSession(false);
//			CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
//			response.setContentType("text/html");
//			httpSession.getAttribute(commonFilterScore);
//			
//			String type = request.getParameter("type");
//			String totalScore=request.getParameter("totalsr");
//			String keyId=request.getParameter("Keyid");
//			String fromMonth=request.getParameter("dtFromMonth");
//			String toMonth=request.getParameter("dtToMonth");
//			//Swetha addition
//			String cellId = request.getParameter("cmbCellid");
//			String sectionId = request.getParameter("cmbSectid");
//			
//			CommonMessage.debugMsg("cell id  "+cellId+" Section "+sectionId);
//			
//			
//			
//			
//			CommonMessage.debugMsg(toMonth +"    fromMnthfromMnthfromMnthfromMnth  "+fromMonth);
//			
//			String drillLevel="SECT";
//			String drill=request.getParameter("filt");
//			CommonMessage.debugMsg("dRILL "+drill);
//			if (cellId != null && !cellId.trim().isEmpty()
//			        && sectionId != null && !sectionId.trim().isEmpty()) {
//
//				drillLevel = "CELL";
//			}
//			
//			if(keyId!=null){
//				 //drillLevel=keyId.substring(0,3);
//				 drillLevel= keyId.substring(0,3);
//
//				}
//			if(keyId!=null){
//				 drillLevel= keyId.substring(0,3);
//				}
//				if(drillLevel.equals("CEL")){
//					drillLevel="EMP";
//				}
//				else if(drillLevel.equals("SEC")){
//					drillLevel="CELL";
//				}
//			commonFilter.setDrillLevel(drillLevel);
//
//			CommonMessage.debugMsg(keyId+" Inside getData "+commonFilter.getDrillLevel());
//			
//			if(UIUtils.isValidKeyId(type))
//				commonFilter.setAbnViewType(type);
//			//commonFilter.setTotal(totalScore);
//
//			CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());
//			if( fromMonth==null || fromMonth==" "){
//				
//				fromMonth=commonFilter.getFromMonth();
//				toMonth=commonFilter.getToMonth();
//		
//			}
//			
//				commonFilter.setFromMonth(fromMonth);
//				commonFilter.setToMonth(toMonth);
//			
//			List<String[]> impVscomList = skillIndexReportService.getavgSkillScoreGraph(commonFilter);
//			
//			JSONObject listToJsonObject = new JSONObject();
//			
//			CommonMessage.debugMsg("size of dash..."+impVscomList.size());
//			if (impVscomList != null && impVscomList.size() > 2)
//				listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
//			httpSession.removeAttribute(commonFilterScore);
//			httpSession.setAttribute(commonFilterScore, commonFilter);
//			
//			httpSession.setAttribute("flidTradeWise", commonFilter.getFlid());
//			httpSession.setAttribute("fromMonth", fromMonth);
//			httpSession.setAttribute("toMonth", toMonth);
//			
//			
//			PrintWriter out = response.getWriter();
//			/*request.setAttribute("fromMonth", fromMonth);
//			request.setAttribute("toMonth", toMonth);*/
//			out.println(listToJsonObject);
//		}
//
//		catch (Exception e) {
//			CommonMessage.debugMsg("error " + e.getMessage());
//		}
//	}

//	else if (action.equals("AverageSkillIndexScore_getData.skillIndex")) {
//		try {
//			HttpSession httpSession = request.getSession(false);
//			CommonFilter commonFilter = populateCommonFilter(request,commonFilterScore,false);
//			response.setContentType("text/html");
//			httpSession.getAttribute(commonFilterScore);
//			
//			String type = request.getParameter("type");
//			String totalScore=request.getParameter("totalsr");
//			String keyId=request.getParameter("Keyid");
//			String fromMonth=request.getParameter("dtFromMonth");
//			String toMonth=request.getParameter("dtToMonth");
//			//Swetha addition
//			String cellId = request.getParameter("cmbCellid");
//			String sectionId = request.getParameter("cmbSectid");
//			
//			String halfyear = request.getParameter("halfyear");		//Swetha
//			commonFilter.setYear(halfyear);//Swetha
//			CommonMessage.debugMsg("cell id  "+cellId+" Section "+sectionId);
//			
//			
//			
//			
//			CommonMessage.debugMsg(toMonth +"    fromMnthfromMnthfromMnthfromMnth  "+fromMonth);
//			
//			String drillLevel="SECT";
//			String drill=request.getParameter("filt");
//			CommonMessage.debugMsg("dRILL "+drill);
//			if (cellId != null && !cellId.trim().isEmpty()
//			        && sectionId != null && !sectionId.trim().isEmpty()) {
//
//				drillLevel = "CELL";
//			}
//			
//			if(keyId!=null){
//				 //drillLevel=keyId.substring(0,3);
//				 drillLevel= keyId.substring(0,3);
//
//				}
//			if(keyId!=null){
//				 drillLevel= keyId.substring(0,3);
//				}
//				if(drillLevel.equals("CEL")){
//					drillLevel="EMP";
//				}
//				else if(drillLevel.equals("SEC")){
//					drillLevel="CELL";
//				}
//			commonFilter.setDrillLevel(drillLevel);
//
//			CommonMessage.debugMsg(keyId+" Inside getData "+commonFilter.getDrillLevel());
//			
//			if(UIUtils.isValidKeyId(type))
//				commonFilter.setAbnViewType(type);
//			//commonFilter.setTotal(totalScore);
//
//			CommonMessage.debugMsg("commonFilter.getFlid()  "+commonFilter.getFlid());
//			if( fromMonth==null || fromMonth==" "){
//				
//				fromMonth=commonFilter.getFromMonth();
//				toMonth=commonFilter.getToMonth();
//		
//			}
//			
//				commonFilter.setFromMonth(fromMonth);
//				commonFilter.setToMonth(toMonth);
//			
//			List<String[]> impVscomList = skillIndexReportService.getavgSkillScoreGraph(commonFilter);
//			
//			JSONObject listToJsonObject = new JSONObject();
//			
//			CommonMessage.debugMsg("size of dash..."+impVscomList.size());
//			if (impVscomList != null && impVscomList.size() > 2)
//				listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2, 0);
//			httpSession.removeAttribute(commonFilterScore);
//			httpSession.setAttribute(commonFilterScore, commonFilter);
//			
//			httpSession.setAttribute("flidTradeWise", commonFilter.getFlid());
//			httpSession.setAttribute("fromMonth", fromMonth);
//			httpSession.setAttribute("toMonth", toMonth);
//			
//			
//			PrintWriter out = response.getWriter();
//			/*request.setAttribute("fromMonth", fromMonth);
//			request.setAttribute("toMonth", toMonth);*/
//			out.println(listToJsonObject);
//		}
//
//		catch (Exception e) {
//			CommonMessage.debugMsg("error " + e.getMessage());
//		}
//	}

		else if (action.equals("AverageSkillIndexScore_getExcel.skillIndex")) {
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, commonFilterScore, false);

			String keyId = request.getParameter("Keyid");

			CommonMessage.debugMsg(keyId + " keyIdkeyIdkeyId in Servlet");

			String drillLevel = "SECT";
			String drill = request.getParameter("filt");
			String cellId = request.getParameter("cmbCellid");
			String sectionId = request.getParameter("cmbSectid");

			CommonMessage.debugMsg("cell id  " + cellId + " Section " + sectionId);
			if (cellId != null && !cellId.trim().isEmpty() && sectionId != null && !sectionId.trim().isEmpty()) {

				drillLevel = "CELL";
			}

			if (keyId != null) {
				// drillLevel=keyId.substring(0,3);
				drillLevel = keyId.substring(0, 3);

			}
			if (keyId != null) {
				drillLevel = keyId.substring(0, 3);
			}
			if (drillLevel.equals("CEL")) {
				drillLevel = "EMP";
			} else if (drillLevel.equals("SEC")) {
				drillLevel = "CELL";
			}
			CommonMessage.debugMsg(drillLevel + "drillLeveldrillLeveldrillLeveldrillLeveldrillLevel");
			commonFilter.setDrillLevel(drillLevel);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject) httpSession.getAttribute("avgSkillScoreData");

			// String type = request.getParameter("type");
			CommonMessage.debugMsg("commonFilter.getDrillCaption()" + commonFilter.getDrillCaption());

			// String reviewDate = request.getParameter("reviewDate");
			tableModel.put("title", " Average SkillIndex Score " + CommonFunctions.getDate());
			String format = ExcelUtils.getFormat(request);
			CommonMessage.debugMsg("Before EXCEL");
			Workbook wb = skillIndexReportService.avgSkillGraphExportExcel(commonFilter, tableModel, format);
			CommonMessage.debugMsg("AFTER EXCEL");
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "SkillIndexScore", format);
		}

		else if (action.equals("chartSkillIndexScoreGraph.skillIndex")) {
			processChartSkillIndexScoreGraph(request, response);

		} else if (action.equals("TradeWiseAverageSkillIndexRpt_input.skillIndex")) {
			CommonMessage.debugMsg("**************Get input*****************************");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/tradewisereport.jsp");
			String etpmCode = request.getParameter("etpmCode");
			// commonFilter.setEmpWise(etpmCode);
			String flid = request.getParameter("flid");
			String fnlnDescription = skillIndexReportService.getFnlnDescription(flid);
			CommonMessage.debugMsg("fnlnDescription" + fnlnDescription);
			request.setAttribute("fnlnDescription", fnlnDescription);
			request.setAttribute("etpmCode", etpmCode);

			rd.forward(request, response);
		} else if (action.equals("TradeWiseAverageSkillIndexRpt_getCol.skillIndex")) {
			CommonMessage.debugMsg("**************Get Col*****************************");
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);

			CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", true);

			String toMonth = (String) httpSession.getAttribute("toMonth");
			String fromMonth = (String) httpSession.getAttribute("fromMonth");
//		
//		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
//		{
//			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
//			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
//			commonFilter.setMonwise("Y");
//		}
			httpSession.getAttribute(commonFilterScore);

			String flid = (String) httpSession.getAttribute("flidTradeWise");
			CommonMessage.debugMsg("************FLID***********" + flid);
			commonFilter.setFlid(flid);
			String etpmCode = request.getParameter("etpmCode");
			commonFilter.setEmpWise(etpmCode);
			request.setAttribute("etpmCode", etpmCode);
			commonFilter.setFromMonth(fromMonth);
			commonFilter.setToMonth(toMonth);
			List<String[]> KaizenGridData = skillIndexReportService.getTradeWiseSkillIndex(commonFilter);
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

			JSONObject colModel = new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "76%%");
			colModel.set("tableWidth", "105%%");
			httpSession.removeAttribute("KaizenSummaryColModel");
			httpSession.setAttribute("KaizenSummaryColModel", colModel);
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			out.println(colModel);

		} else if (action.equals("TradeWiseAverageSkillIndexRpt_getData.skillIndex")) {

			CommonMessage.debugMsg("**************Get Data*****************************");
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
			String etpmCode = request.getParameter("etpmCode");
			String flid = (String) httpSession.getAttribute("flidTradeWise");
			String toMonth = (String) httpSession.getAttribute("toMonth");
			String fromMonth = (String) httpSession.getAttribute("fromMonth");
			commonFilter.setFlid(flid);
			commonFilter.setEmpWise(etpmCode);

			commonFilter.setFromMonth(fromMonth);
			commonFilter.setToMonth(toMonth);
			CommonMessage.debugMsg("From month *********" + fromMonth);
			CommonMessage.debugMsg("To month *********" + toMonth);

			List<String[]> KaizenGridData = skillIndexReportService.getTradeWiseSkillIndex(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData, request, 3, 0,
					commonFilter.getTotalRecordCnt());
			out.print(dataJson);
			httpSession.removeAttribute("KaizenSummaryCommonFilter");
			httpSession.setAttribute("KaizenSummaryCommonFilter", commonFilter);
			request.setAttribute("etpmCode", etpmCode);
		} else if (action.equals("TradeWiseAverageSkillIndexRpt_getExcel.skillIndex")) {

			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
			// String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = (JSONObject) httpSession.getAttribute("KaizenSummaryColModel");
			String format = ExcelUtils.getFormat(request);
			String etpmCode = request.getParameter("etpmCode");
			commonFilter.setEmpWise(etpmCode);
			tableModel.put("title", " Tradewise SkillIndex Score ");
			Workbook wb = skillIndexReportService.tradewiseSkillGraphExportExcel(commonFilter, tableModel, format);
			// commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "TradeWiseReport", format);

		}

		/************** Trade wise Line Chart **********************/
		else if (action.equals("chartTradeWiseSkillIndexScoreGraph.skillIndex")) {
			processChartTradeWiseGraph(request, response);

		} else if (action.equals("chartTradeWiseSkillIndexBarGraph.skillIndex")) {
			processChartTradeWiseBarGraph(request, response);

		} else if (action.equals("exportJhAvgRadarChart.skillIndex")) {
			CommonMessage.debugMsg("My Test Start");
			String dataGrid = request.getParameter("dataGrid");
			String etpmCode = request.getParameter("etpmCode");
			CommonMessage.debugMsg("etpmCode: " + etpmCode);
			// String [] dataGridArr=dataGrid.split(",");
			// String [] empNamesArr=empNames.split(";");
			List<String[]> dataList = new ArrayList<String[]>();
			List<String[]> nameList = new ArrayList<String[]>();
			/*
			 * for(int i=0;i<dataGridArr.length-1;i++){ String
			 * gridDatas=dataGridArr[i].substring(1, dataGridArr[i].length()-1); JSONObject
			 * jsonObject =JSONObject.fromString(gridDatas); String dataStr=""; if(
			 * jsonObject.has("data") ) dataStr = jsonObject.getString("data");
			 * dataStr=dataStr.substring(1, dataStr.length()-1); String []
			 * dataArr=dataStr.split(","); String [] nameArr={empNamesArr[i]};
			 * dataList.add(dataArr); nameList.add(nameArr); }
			 */

			String[] dataArr = dataGrid.split(",");

			double sum = 0;

			for (String string : dataArr) {
				sum = sum + Double.parseDouble(string);
			}
			double avg = sum / 7.0;
			String formatNumber = String.format("%.2f", avg);
			// String [] dataArr={"2.3", "1.5"};
			String trade = "";
			if (etpmCode.equals("P")) {
				trade = "PROCESS";
			} else if (etpmCode.equals("M")) {
				trade = "RESOURCE";
			} else if (etpmCode.equals("S")) {
				trade = "SERVICE";
			} else if (etpmCode.equals("T")) {
				trade = "TECHNICAL";
			}

			CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", false);

			CommonMessage.debugMsg("getdFromDate:" + commonFilter.getdFromDate());
			CommonMessage.debugMsg("getdToDate:" + commonFilter.getdToDate());
			CommonMessage.debugMsg("getMonwise" + commonFilter.getMonwise());

			CommonMessage.debugMsg("getFromDate" + commonFilter.getFromDate());
			CommonMessage.debugMsg("getToDate" + commonFilter.getToDate());
			CommonMessage.debugMsg("getFromMonth" + commonFilter.getFromMonth());
			CommonMessage.debugMsg("getToMonth" + commonFilter.getToMonth());

			CommonMessage.debugMsg("" + commonFilter.getdFromDate());

			String period = "";
			if (commonFilter.getMonwise().equals("Y")) {
				period = commonFilter.getFromMonth() + " - " + commonFilter.getToMonth();
			} else {
				period = commonFilter.getFromDate() + " - " + commonFilter.getToDate();
			}

			String flid = commonFilter.getFlid();
			CommonMessage.debugMsg("Flid**: " + flid);
			String fnlnDescription = skillIndexReportService.getFnlnDescription(flid);
			String title = fnlnDescription + " Avg Skill Index(" + trade + ") - " + period + " - Avg: " + formatNumber;
			String[] nameArr = { "" };
			dataList.add(dataArr);
			nameList.add(nameArr);
			dataList.add(dataArr);
			nameList.add(nameArr);
			String format = ExcelUtils.getFormat(request);
			String path = UIUtils.getExcelTemplatePath(request);
			try {
				Workbook wb = fillRadarExcelValuesJh(request, dataList, nameList, path, format, title);
				ExcelUtils.writeToResponse(response, wb, "JHAvgSkillIndexRadarChart", format);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void processChartTradeWiseGraph(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		ChartRadar skillIndexRadar = new ChartRadar();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();

		CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
		String etpmCode = request.getParameter("etpmCode");
		commonFilter.setEmpWise(etpmCode);
		request.setAttribute("etpmCode", etpmCode);
		List<String[]> KaizenGridData = skillIndexReportService.getTradeWiseSkillIndex(commonFilter);
		CommonMessage.debugMsg("KaizenGridData: " + KaizenGridData.size());
		// String[] header = {"BEHAVIOUR","JH PARTICIPATION","CONTINOUS
		// IMPROVEMENT","SAFETY","TECHNICAL CAPABILITY (TRAINING)","EQP, PROCESS
		// UNDERSTANDING","TROUBLE SHOOTING SKILLS"};
		// String[] month = {"BEHAVIOUR","JH PARTICIPATION","CONTINOUS
		// IMPROVEMENT","SAFETY","TECHNICAL CAPABILITY (TRAINING)","EQP, PROCESS
		// UNDERSTANDING","TROUBLE SHOOTING SKILLS"};
		// String[] data = {".1","1.5","3.5","1.5","2.6", "3.1" ,"4.5"};
		String[] header = null;
		String[] month = null;
		String[] data = null;
		System.out.println(KaizenGridData.size()+" ------");
		if (KaizenGridData.size() > 3) {
			header = KaizenGridData.get(2);
			month = KaizenGridData.get(2);
			data = KaizenGridData.get(KaizenGridData.size() - 1);

			ChartSeries timeSeries = new ChartSeries();
			List<Double> seriesData = new ArrayList<Double>();
			// swetha change // Kiran changed on 28-may-2026 
			for (int i = 4; i <= header.length - 5; i++) {
				if (!UIUtils.isValidKeyId(data[i]))
					data[i] = "0";
				seriesData.add(Double.parseDouble(data[i]));
				xAxisCategory.add(month[i]);

			}
			if (seriesData.size() > 0) {
				timeSeries.setData(seriesData);
				timeSeries.setName("Each criteria Avg In JH");
				chartSeriesList.add(timeSeries);

				ChartYAxis yAxis = new ChartYAxis();
				yAxis.setMin(0);
				chartYAxis.add(yAxis);
			}
			JSONObject chartDat = skillIndexRadar.drawChart(chartSeriesList, xAxisCategory, "");
			// JSONObject chartObj =skillIndexRadar.drawChart(xAxisCategory,
			// chartSeriesList, "", "", chartYAxis);
			JSONObject returnData = new JSONObject();
			// xAxisCategory.add(chartDat.toString());
			returnData.put("chartData", chartDat);
			returnData.put("seriesData", seriesData);
			returnData.put("successdata", xAxisCategory);

			String period = "";
			if (commonFilter.getMonwise().equals("Y")) {
				period = commonFilter.getFromMonth() + " - " + commonFilter.getToMonth();
			} else {
				period = commonFilter.getFromDate() + " - " + commonFilter.getToDate();
			}
			if (UIUtils.isValidKeyId(data[header.length - 3]))
				;
			String title = period + " - Avg - " + data[header.length - 3];

			returnData.put("title", title);
			request.removeAttribute("title");
			request.setAttribute("title", title);
			// CommonMessage.debugMsg("chartObj ="+chartObj);
			PrintWriter out = response.getWriter();
			// out.print(chartObj);
			out.print(returnData);
			out.close();

		}
	}

	private void processChartTradeWiseBarGraph(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChartOptionBean chartOptionBean = new ChartOptionBean();
		List<String> xAxis = new ArrayList<String>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		ChartSeries chartSeries = new ChartSeries();
		List<Double> chartData = new ArrayList<Double>();

		CommonFilter commonFilter = populateCommonFilter(request, "KaizenSummaryCommonFilter", false);
		String etpmCode = request.getParameter("etpmCode");
		commonFilter.setEmpWise(etpmCode);
		request.setAttribute("etpmCode", etpmCode);
		String data[] = null;
		String header[] = null;
		String month[] = null;

//	CommonFilter commonFilter=populateCommonFilter(request, "KaizenSummaryCommonFilter", false);

		CommonMessage.debugMsg("getdFromDate:" + commonFilter.getdFromDate());
		CommonMessage.debugMsg("getdToDate:" + commonFilter.getdToDate());
		CommonMessage.debugMsg("getMonwise" + commonFilter.getMonwise());

		CommonMessage.debugMsg("getFromDate" + commonFilter.getFromDate());
		CommonMessage.debugMsg("getToDate" + commonFilter.getToDate());
		CommonMessage.debugMsg("getFromMonth" + commonFilter.getFromMonth());
		CommonMessage.debugMsg("getToMonth" + commonFilter.getToMonth());

		String fromDateStr = commonFilter.getFromMonth(); // dynamic value
		String toDateStr = commonFilter.getToMonth(); // dynamic value

		// Extract the year
		String year = fromDateStr.substring(fromDateStr.length() - 4); // "2025"

		// Always set months as Jan & Dec
		String fromMonth = "Jan-" + year;
		String toMonth = "Dec-" + year;

		CommonMessage.debugMsg("From month " + fromMonth);
		CommonMessage.debugMsg("To month " + toMonth);

		commonFilter.setFromMonth(fromMonth);
		commonFilter.setToMonth(toMonth);
		CommonMessage.debugMsg("" + commonFilter.getdFromDate());

		String period = "";
		if (commonFilter.getMonwise().equals("Y")) {
			period = commonFilter.getFromMonth();// + " - " + commonFilter.getToMonth();
		} else {
			period = commonFilter.getFromDate();// + " - " + commonFilter.getToDate();
		}
		period = period.substring(period.length() - 4);

		String flid = commonFilter.getFlid();
		CommonMessage.debugMsg("Flid**: " + flid);
		String fnlnDescription = skillIndexReportService.getFnlnDescription(flid);

		List<String[]> graphData = skillIndexReportService.getTradeWiseColumnData(commonFilter);
		CommonMessage.debugMsg("************graphdata**************");
		for (String[] row : graphData) {
			CommonMessage.debugMsg(Arrays.toString(row));
		}

		CommonMessage.debugMsg("Size: " + graphData.size());
		header = graphData.get(0);
		month = graphData.get(0);
		data = graphData.get(1);

		/*
		 * 
		 * String data[] ={"10", "20","30","40","50"}; String header[]={"A","B"
		 * ,"C","D","E" }; String month[]={"01","02","03","04","05"};
		 */
		for (int i = 0; i < header.length - 1; i++) {
			if (!UIUtils.isValidKeyId(data[i]))
				data[i] = "0";
			chartData.add(Double.parseDouble(data[i]));
			xAxis.add(month[i]);
		}
		if (chartData.size() > 0) {
			chartSeries.setData(chartData);
			chartSeries.setType(ChartTypes.COLUMN);
			chartSeries.setName("JH Avg Skill Index");
			chartSeriesList.add(chartSeries);
			ChartYAxis yAxis = new ChartYAxis();
			yAxis.setMin(0);
			yAxis.getTitle().setText("Skill Index");
			chartYAxis.add(yAxis);
		}
		String title = fnlnDescription + " - Avg Skill Index".toString() + " - " + period;
		JSONObject chartObj = chartOptionBean.drawChart(xAxis, chartSeriesList, title, "", chartYAxis);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();

	}

	/*
	 * private JSONObject getTableModelGraph(List<String[]> headers,String caption)
	 * { CommonMessage.debugMsg("Enter get col model.......op"+caption);
	 * JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 * 
	 * //String[] colHeader = headers.get(1); //String[] colHeader1 =
	 * headers.get(2); String[] colHeader = headers.get(0); String[] colHeader1 =
	 * headers.get(1);
	 * 
	 * colHeader[3] = "DATE"; if(caption.equals("Factory")){ caption="PBU"; }else
	 * if(caption.equals("Section")){ caption="DMT"; }else
	 * if(caption.equals("Line")){ caption="JH"; } caption="Flid"; String[] emptyrow
	 * = new String[colHeader.length]; emptyrow[0] = ""; emptyrow[1] = "";
	 * CommonMessage.debugMsg("test..."+colHeader.length); for (int i = 2; i
	 * <colHeader.length; i++) { emptyrow[i] = ""; }
	 * jqGridTableModel.getRowHeaders().add(emptyrow);
	 * jqGridTableModel.getRowHeaders().add(colHeader);
	 * jqGridTableModel.getRowHeaders().add(colHeader1);
	 * jqGridTableModel.setTableButton(true); jqGridTableModel.setRowNumbers(true);
	 * colHeader1[3] = caption; colHeader[3] = caption; String headerSql =
	 * "'SELECT "; for (int i = 0; i <= colHeader.length-1; i++) { JqGridColModel
	 * jqGridColModel = new JqGridColModel();
	 * jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
	 * jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
	 * jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
	 * jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);
	 * 
	 * jqGridColModel.setWidth(200); jqGridColModel.setAlign("left");
	 * jqGridColModel.setEditable(false);
	 * 
	 * if (i == 0 || i == 1 || i==2 ||i == 3) { jqGridColModel.setHidden(true);
	 * jqGridColModel.setKey(true); } else if (i > 4) { //
	 * jqGridColModel.setHidden(false); jqGridColModel.setKey(true);
	 * jqGridColModel.setAlign("right"); jqGridColModel.setWidth(70); }
	 * 
	 * else if (i == 16) { CommonMessage.debugMsg("Length of col:" +
	 * colHeader.length); jqGridColModel.setHidden(true);
	 * jqGridColModel.setKey(true); }
	 * 
	 * jqGridTableModel.getColModel().add(jqGridColModel);
	 * 
	 * //headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel); }
	 * JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 * headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
	 * CommonMessage.debugMsg("headerSql.....123..."+headerSql);
	 * tableModel.set("tableHeight", "67%%");
	 * 
	 * CommonMessage.debugMsg("colmodel:" + tableModel); return tableModel; }
	 */

	private JSONObject getTableModelGraph(List<String[]> headers, String caption) {
		System.out.println("Enter get col model.......op" + caption);
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
		caption = "Flid";
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		System.out.println("test..." + colHeader.length);
		for (int i = 2; i < colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		colHeader1[3] = caption;
		colHeader[3] = caption;
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

//		if (i == 0 || i == 1 || i==2 ||i == 3) {
//			jqGridColModel.setHidden(true);
//			jqGridColModel.setKey(true);
//		} else if (i > 4) {
//			// jqGridColModel.setHidden(false);
//			jqGridColModel.setKey(true);
//			jqGridColModel.setAlign("right");
//			jqGridColModel.setWidth(70);
//		}
//
//		else if (i == 16) {
//			System.out.println("Length of col:" + colHeader.length);
//			jqGridColModel.setHidden(true);
//			jqGridColModel.setKey(true);
//		}
//****************CHANGES HERE****************************//
			if (i == 0 || i == 1 || i == 2 || i == 3) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(false); // ❌ remove key
			} else if (i == 4) { // CODEFIELD column
				jqGridColModel.setKey(true); // ✅ ONLY ONE KEY
			} else {
				jqGridColModel.setKey(false); // ❌ remove from others
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}
//****************CHANGES HERE****************************//

			jqGridTableModel.getColModel().add(jqGridColModel);

			// headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length() - 1) + " FROM DUAL' ";
		System.out.println("headerSql.....123..." + headerSql);
		tableModel.set("tableHeight", "67%%");

		System.out.println("colmodel:" + tableModel);
		return tableModel;
	}

	public Workbook fillRadarExcelValues(HttpServletRequest request, List<String[]> dataList, List<String[]> nameList,
			String path, String format) throws Exception {
		String excelPath = "/SKILLINDEXEXCEL";
		InputStream inp = new FileInputStream(path + excelPath + "." + format);
		CommonMessage.debugMsg(format + " path+excelPath  : " + path + excelPath + "." + format);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(inp) : new HSSFWorkbook(inp); // keep
																														// 100
																														// rows
																														// in
																														// memory,
																														// exceeding
																														// rows
																														// will
																														// be
																														// flushed
																														// to
																														// disknew
																														// XSSFWorkbook(inp);
		try {
			inp.close();
			Sheet sheet0 = wb.getSheetAt(0);
			Sheet sheet = wb.getSheetAt(1);
			String reviewDate = request.getParameter("reviewDate");

			int numOfChrt = 10;
			int start = 0;
			int end = 0;
			for (int i = 0; i < dataList.size(); i++) {
				sheet.createRow(end).createCell(0).setCellValue("Radar Chart-Skill Index Analysis");
				end = end + 1;
				String[] name = nameList.get(i);
				CommonMessage.debugMsg("name[0]" + name[0]);
				StringBuffer buf = new StringBuffer();
				String qots = buf.append('"').toString();

				// CommonMessage.debugMsg("qots=="+qots);
				// name[0] = name[0].replace("(", qots + " & CHAR(10) & " + qots + "(");
				// CommonMessage.debugMsg("name[0]"+name[0].toString());
				name[0] = name[0].replace(qots, "").toString();
				// CommonMessage.debugMsg("name===="+name[0].toString());
				String[] namess = name[0].split("-");
				// CommonMessage.debugMsg("name[0]"+name[0]);
				String frmla = "CONCATENATE(" + qots + namess[0] + "-" + namess[1] + qots + ",CHAR(10)," + qots
						+ namess[2] + qots + ")";
				// CommonMessage.debugMsg(frmla);
				// sheet.createRow(end).createCell(0).setCellValue(name[0]);
				sheet.createRow(end).createCell(0).setCellFormula(frmla);
				sheet.createRow(end).createCell(0).setCellFormula(frmla);

				start = end + 1;
				String[] dataArr = dataList.get(i);
				int lopEnd = start + 24;
				int count = 0;
				CommonMessage.debugMsg(start + "start" + lopEnd);
				for (int j = start; j < lopEnd; j++) {
					if (count < dataArr.length) {
						sheet.createRow(j).createCell(0).setCellValue(new Double(dataArr[count]));
					} else {
						sheet.createRow(j).createCell(0).setCellValue(new Double("0"));

						CellStyle hiddenstyle = wb.createCellStyle();
						hiddenstyle.setHidden(true);
						sheet.getRow(j).setRowStyle(hiddenstyle);
						sheet.getRow(j).setZeroHeight(true);
					}
					count++;
				}
				end = end + 24;
			}

			int totalRow = 25 * numOfChrt;
			int startRow = 25 * dataList.size();
			CommonMessage.debugMsg(totalRow + " =totalRow " + startRow + " numOfChrt " + numOfChrt
					+ "  dataList.size() " + dataList.size());
			for (int k = startRow; k < totalRow; k++) {
				sheet.createRow(k).createCell(0).setCellValue(new Double("0"));
				CellStyle hiddenstyle = wb.createCellStyle();
				hiddenstyle.setHidden(true);
				sheet.getRow(k).setRowStyle(hiddenstyle);
				sheet.getRow(k).setZeroHeight(true);
			}
			int rowHide = dataList.size() % 3;
			int endRow = 4 * 20;
			int rowNum = 0;
			if (rowHide == 0) {
				rowNum = 20 * (dataList.size() / 3);
			} else {
				rowNum = 20 * ((dataList.size() + (3 - rowHide)) / 3);
			}
			CommonMessage.debugMsg(rowHide + "  endRow  " + endRow + " rowNum " + rowNum);
			for (int k = rowNum; k < endRow; k++) {
				sheet0.createRow(k).createCell(0);
				CellStyle hiddenstyle = wb.createCellStyle();
				hiddenstyle.setHidden(true);
				sheet0.getRow(k).setRowStyle(hiddenstyle);
				sheet0.getRow(k).setZeroHeight(true);
			}
			if (!UIUtils.isValidKeyId(reviewDate))
				reviewDate = " ";
			sheet0.getRow(0).getCell(0).setCellValue("Radar Chart-Skill Index Analysis - " + reviewDate);

			sheet0.protectSheet("admin");
			sheet.protectSheet("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);

		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();

			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getTraning(request, commonFilter);

			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);

		}
		String Keyid = request.getParameter("Keyid");
		CommonMessage.debugMsg(Keyid + "KeyidKeyid");
		// Keyid=Keyid.substring(0,3);
		CommonMessage.debugMsg(Keyid + "KeyidKeyid");

		if (Keyid == null) {
			Keyid = "SECT";
		} else {
			Keyid = Keyid.substring(0, 3);

		}
		// commonFilter.setDrillLevel("SECT");

		if (Keyid.equals("SECT")) {
			commonFilter.setDrillLevel("SECT");
		} else if (Keyid.equals("SEC")) {
			commonFilter.setDrillLevel("CELL");
		} else if (Keyid.equals("CEL")) {
			commonFilter.setDrillLevel("EMP");
		}
		commonFilter.setViewClick('Y');
		CommonMessage.debugMsg("  populate commonFilter getMachineRank "
				+ (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}

	private JSONObject getTableModel(List<String[]> headers, String[] header) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();

		String[] colHeader = header;
		jqGridTableModel.setRowNumbers(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			// CommonMessage.debugMsg("colHeader1[i] "+colHeader1[i]+" colHeader[i]
			// "+colHeader[i]);
			jqGridColModel.setEditable(false);
			if (colHeader.length > 1) {
				if (i == 1) {
					jqGridColModel.setFormatter("txtKSA");
					jqGridColModel.setWidth(100);
				}
				if (i == 2) {
					jqGridColModel.setFormatter("dteSkillDate");
					jqGridColModel.setWidth(100);
				}
			}
			jqGridTableModel.getColModel().add(jqGridColModel);

		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "75%%");
		tableModel.set("tableWidth", "50%%");
		return tableModel;
	}

	private void skillIndexRadarChart(HttpServletRequest request, HttpServletResponse response) throws IOException {

		CommonMessage.debugMsg("inside fn radar chart");
		String assessmentId = request.getParameter("assessmentId");
		CommonMessage.debugMsg("assessmentId" + assessmentId);
		String flid = request.getParameter("flid");
		String uniPosID = request.getParameter("uniPosID");
		String reviewDate = request.getParameter("reviewDate");
		String forDashboard = request.getParameter("dashboard");
		String empIdsStr = request.getParameter("empIds");
		CommonMessage.debugMsg("empIdsStr          " + empIdsStr);
		String[] empIdsArr = null;
		String empIds = "";
		CommonMessage.debugMsg("reviewDate..." + reviewDate);
		if ("true".equals(forDashboard)) {

			if (!UIUtils.isValidKeyId(assessmentId))
				assessmentId = request.getParameter("assmId");
		}
		PrintWriter out = response.getWriter();
		try {
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
			if (UIUtils.isValidKeyId(empIdsStr)) {
				empIdsArr = empIdsStr.split(",");
				for (int i = 0; i < empIdsArr.length; i++) {
					empIds += "'" + empIdsArr[i] + "',";
				}
			}
			empIds = empIds.substring(0, empIds.length() - 1);
			commonFilter.setEmmLinkKeyId(empIds);
			commonFilter.setFlid(flid);
			commonFilter.setUniquePos(uniPosID);
			commonFilter.setFromDate(reviewDate);
			commonFilter.setToDate(reviewDate);
//		List<String[]> empAssesmentList = entSkillGapAnalysisService.getEmpAssesmentTopicRatings(assessmentId,fromSpoke);
			List<String[]> skillIndexList = skillIndexReportService.getSkillIndexRadarChart(commonFilter);
			List<String[]> skillIndexData = skillIndexReportService.getskillIndexChart(commonFilter);

			CommonMessage.debugMsg("Skill index list");
			for (String[] arr : skillIndexList) {
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
			List<String[]> updatedSkillIndexData = new ArrayList<String[]>();
			for (String[] row : skillIndexData) {
				String[] newRow = new String[row.length + 1];
				System.arraycopy(row, 0, newRow, 0, row.length); // Copy first 6 columns
				newRow[row.length] = "0"; // Add dummy value as column 6
				updatedSkillIndexData.add(newRow);
			}

			// Replace skillIndexData with the updated version
			skillIndexData = updatedSkillIndexData;

			CommonMessage.debugMsg("Skill Index Data");
			for (String[] arr : skillIndexData) {
				CommonMessage.debugMsg(Arrays.toString(arr));
			}
			// int listSize=skillIndexList.size();
			JSONObject returnData = new JSONObject();
			// JSONArray convertdData = new JSONArray();
			// JSONArray.fromCollection(skillIndexList);
			// convertdData = JSONArray.fromCollection(skillIndexList);
			// returnData.put("geiData",convertdData);

			returnData.put("fillData", skillIndexData);
			// returnData.put("fromSpoke",fromSpoke);
			ChartRadar skillIndexRadar = new ChartRadar();
			List<ChartSeries> chartSeries = new ArrayList<ChartSeries>();
			List<String> xCategories = new ArrayList<String>();
			// ChartSeries targetSeries = new ChartSeries();
			ChartSeries currentSeries = new ChartSeries();
			// List<Double> targetRateData = new ArrayList<Double>();
			List<Double> currentRateData = new ArrayList<Double>();
			List<String> xCategories1 = new ArrayList<String>();
			ChartSeries currentSeries1 = new ChartSeries();
			List<ChartSeries> chartSeries1 = new ArrayList<ChartSeries>();
			List<Double> currentRateData1 = new ArrayList<Double>();
			List<String> xCategoriesList = new ArrayList<String>();

			String prevEmp = skillIndexList.get(0)[0];
			int chrtNo = 4;
			for (String[] data : skillIndexList) {

				xCategories.add(data[1]); // Topic Names
				if (!prevEmp.equals(data[0])) {
					prevEmp = data[0];
					currentSeries.setData(currentRateData);
					currentSeries.setPointPlacement("on");
					chartSeries.add(currentSeries);
					chrtNo = chrtNo + 1;
					skillIndexRadar.setHeight(150);
					JSONObject chartDat = skillIndexRadar.drawChart(chartSeries, xCategories, "", 3);
					returnData.put("chartData_" + chrtNo, chartDat);

				}
				if (UIUtils.isValidKeyId(data[2]))
					currentRateData.add(Double.parseDouble(data[2]));// Current Rating
				else
					currentRateData.add(Double.parseDouble("0"));// Current Rating
			}

			prevEmp = skillIndexList.get(0)[0];
			for (int i = 0; i < skillIndexList.size(); i++) {
				String[] data = skillIndexList.get(i);
				xCategories1.add(data[1]);
				if (!prevEmp.equals(data[0])) {

					prevEmp = data[0];
					currentSeries1.setData(currentRateData1);
					currentSeries1.setPointPlacement("on");
					chartSeries1.add(currentSeries1);
					chrtNo = chrtNo + 1;
					skillIndexRadar.setHeight(150);
					JSONObject chartDat = skillIndexRadar.drawChart(chartSeries1, xCategories1, "", 3);
					xCategoriesList.add(chartDat.toString());

					currentRateData1 = new ArrayList<Double>();
					xCategories1 = new ArrayList<String>();
					currentSeries1 = new ChartSeries();
					xCategories1.add(data[1]);
					chartSeries1 = new ArrayList<ChartSeries>();
				}
				if (UIUtils.isValidKeyId(data[2]))
					currentRateData1.add(Double.parseDouble(data[2]));// Current Rating
				else
					currentRateData1.add(Double.parseDouble("0"));// Current Rating

				if (i == skillIndexList.size() - 1)// -1
				{

					xCategories1.add("1");
					currentSeries1.setData(currentRateData1);
					currentSeries1.setPointPlacement("on");
					chartSeries1.add(currentSeries1);
					chrtNo = chrtNo + 1;

					// skillIndexRadar.getyAxis().get(0).setMax(5);
					skillIndexRadar.setHeight(150);
					JSONObject chartDat = skillIndexRadar.drawChart(chartSeries1, xCategories1, "", 3);

					xCategoriesList.add(chartDat.toString());

					xCategories1 = new ArrayList<String>();
					xCategories1.add(data[1]);
				}

			}

			currentSeries.setData(currentRateData);
			currentSeries.setPointPlacement("on");
			chartSeries.add(currentSeries);
			chrtNo = chrtNo + 1;
			skillIndexRadar.setHeight(150);
			JSONObject chartDat = skillIndexRadar.drawChart(chartSeries, xCategories, "", 3);
			returnData.put("successdata", xCategoriesList);

			CommonMessage.debugMsg("returnData.toString()  : " + returnData.toString());
			UIUtils.dashBoardSetChartObject(request, chartDat);
			if ("true".equals(forDashboard)) {

				out.print(chartDat);
			} else
				out.print(returnData);

		} catch (NoDataFoundException e) {
			out.print("No Data Found");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processChartSkillIndexScoreGraph(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = null;

		String forDashboard = request.getParameter("dashboard");
		String graphType = request.getParameter("graphType");
		String flid = request.getParameter("flid");
		String type = request.getParameter("type");
		String empKeyId = request.getParameter("empId");
		String cellId = request.getParameter("cellId");

		CommonMessage.debugMsg("Cell id " + cellId);
		CommonMessage.debugMsg("empKeyId id " + empKeyId);

		CommonMessage.debugMsg(" Inside Servlet :: " + type);

		CommonMessage.debugMsg(commonFilterScore + " forDashboard.....forDashboard " + forDashboard);
		if (!"true".equals(forDashboard)) {
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute(commonFilterScore);
		} else {
			commonFilter = populateCommonFilter(request, commonFilterScore, false);
		}

		CommonMessage.debugMsg(commonFilter.getFlid() + " commonFilter.getFlid()  " + commonFilter.getFlid());

		CommonFilter chartCommonFilter = new CommonFilter();
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);

		chartCommonFilter.setFlid(commonFilter.getFlid());
		CommonMessage.debugMsg("FLid");

		if (UIUtils.isValidKeyId(flid)) {
			CommonMessage.debugMsg("FLid");
			// chartCommonFilter.setFlid(flid);
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

		chartCommonFilter.setFlid(commonFilter.getFlid());
		CommonMessage.debugMsg("FLID IN CHART " + chartCommonFilter.getFlid());
		List<String[]> jhSkillIndexScoreList = skillIndexReportService.getavgSkillScoreGraph(chartCommonFilter);
		CommonMessage.debugMsg("*********************Printing************************");
		for (String[] arr : jhSkillIndexScoreList) {

			CommonMessage.debugMsg(Arrays.toString(arr));
		}

		List<String[]> jhSkillIndexScoreFinalList = new ArrayList<>();

		if (cellId != null && !cellId.trim().isEmpty()) {

			// ⭐ Add first 3 rows always
			if (jhSkillIndexScoreList.size() >= 3) {
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(0)); // header row
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(1)); // row 1
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(2)); // row 2
			}

			// ⭐ Then filter and add only matching cellId rows
			for (int i = 3; i < jhSkillIndexScoreList.size(); i++) {
				String[] row = jhSkillIndexScoreList.get(i);
				if (row.length > 2 && cellId.equals(row[2])) { // displaycode = index 2
					jhSkillIndexScoreFinalList.add(row);
				}
			}
		} else if (empKeyId != null && !empKeyId.trim().isEmpty()) {
			if (jhSkillIndexScoreList.size() >= 3) {
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(0)); // header row
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(1)); // row 1
				jhSkillIndexScoreFinalList.add(jhSkillIndexScoreList.get(2)); // row 2
			}

			for (int i = 3; i < jhSkillIndexScoreList.size(); i++) { // skip first 3 rows
				String[] row = jhSkillIndexScoreList.get(i);

				if (row.length > 2 && empKeyId.equals(row[2])) { // displaycode = index 2
					jhSkillIndexScoreFinalList.add(row);
				}
			}

		} else {
			jhSkillIndexScoreFinalList = jhSkillIndexScoreList;
		}
		CommonMessage.debugMsg("*******************jhSkillIndexScoreFinalList*************");
		for (String[] arr : jhSkillIndexScoreFinalList) {

			CommonMessage.debugMsg(Arrays.toString(arr));
		}

		JSONObject chartObj = null;
		if (jhSkillIndexScoreFinalList != null && jhSkillIndexScoreFinalList.size() > 0) {

			String flids = CommonFunctions.getLoginFlid(request);
			String lcn = dashboardService.Functionallocn(flids);

			chartObj = processLineChartSkillIndex(lcn, jhSkillIndexScoreFinalList, chartCommonFilter);
			UIUtils.dashBoardSetChartObject(request, chartObj);
			CommonMessage.debugMsg("chartObj =" + chartObj);
			PrintWriter out = response.getWriter();

			// JSONObject chartObjInner =
			// chartObj.getJSONObject("chartData").getJSONObject("chartData");
			// out.print(chartObjInner);
			JSONObject realChart = chartObj.getJSONObject("chartData");
			CommonMessage.debugMsg("realChart " + realChart);
			out.print(chartObj);
			out.close();
		}
	}

	private JSONObject processLineChartSkillIndex(String tn, List<String[]> jhSkillIndexScoreList,
			CommonFilter commonFilter) {

		if (jhSkillIndexScoreList == null)
			return null;

		ChartOptionBean lineChart = new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis = new ArrayList<ChartYAxis>();
		CommonMessage.debugMsg("Title " + tn);
		String title = tn + "-Average SkillIndex Score ";

		// Get the correct rows based on your data structure
		// Row 1: [0, 1, KEYID, KEYFIELD, CODEFIELD, PROCESS, RESOURCE, SERVICE,
		// TECHNICAL, TOTAL]
		// Row 2: [0, 2, KEYID, KEYFIELD, CODEFIELD, SCORE, SCORE, SCORE, SCORE, SCORE]
		// Row 3+: Actual data rows

		// String[] columnHeaders = jhSkillIndexScoreList.get(1); // Column names
		// String[] scoreLabels = jhSkillIndexScoreList.get(2); // SCORE indicators
		CommonMessage.debugMsg("entered into this for graphh");
		for (String[] arr : jhSkillIndexScoreList) {
			CommonMessage.debugMsg(Arrays.toString(arr));
		}

		String[] columnHeaders = jhSkillIndexScoreList.get(0); // Column names
		String[] scoreLabels = jhSkillIndexScoreList.get(1); // SCORE indicators

		// Build xAxis categories from column headers (indices 4 onwards)
		for (int i = 5; i < columnHeaders.length; i++) {
			xAxisCategory.add(columnHeaders[i]);
		}

		// Only process ONE data row (row 3) for a single series
		if (jhSkillIndexScoreList.size() > 2) {// 3
			String[] dataRow = jhSkillIndexScoreList.get(2);// 3

			String subTitle = dataRow[3]; // KEYFIELD value

			// Extract score data from columns 4 onwards
			List<Double> scoreData = new ArrayList<Double>();
			for (int i = 5; i < dataRow.length; i++) {
				if (scoreLabels[i].contains("SCORE")) {
					if (UIUtils.isValidKeyId(dataRow[i])) {
						scoreData.add(Double.parseDouble(dataRow[i]));
					} else {
						scoreData.add(0.0);
					}
				}
			}

			CommonMessage.debugMsg("X-Axis Categories: " + xAxisCategory);
			CommonMessage.debugMsg("Score Data: " + scoreData);

			// Create single series with all the scores
			ChartSeries timeSeries = new ChartSeries();
			timeSeries.setData(scoreData);

			if (commonFilter.getType().equals("COLUMN"))
				timeSeries.setType(ChartTypes.COLUMN);
			else
				timeSeries.setType(ChartTypes.SPLINE);

			timeSeries.setName("AVERAGE");
			chartSeriesList.add(timeSeries);

			// Set subtitle
			// lineChart.setSubTitle(subTitle);
		}

		// Configure Y-Axis
		ChartYAxis yAxis = new ChartYAxis();
		yAxis.setMin(0);
		yAxis.getTitle().setText("Average Score");
		chartYAxis.add(yAxis);

		// Configure X-Axis
		ChartXAxis xaxis = new ChartXAxis();
		xaxis.getTitle().setText("Type");
		lineChart.getxAxis().setTitle(xaxis.getTitle());

		return lineChart.drawChart(xAxisCategory, chartSeriesList, title, "", chartYAxis);
	}

	public Workbook fillRadarExcelValuesJh(HttpServletRequest request, List<String[]> dataList, List<String[]> nameList,
			String path, String format, String title) throws Exception {
		CommonMessage.debugMsg("My Test 2");
		String excelPath = "/SKILLINDEXEXCEL";
		InputStream inp = new FileInputStream(path + excelPath + "." + format);
		CommonMessage.debugMsg(format + " path+excelPath  : " + path + excelPath + "." + format);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(inp) : new HSSFWorkbook(inp); // keep
																														// 100
																														// rows
																														// in
																														// memory,
																														// exceeding
																														// rows
																														// will
																														// be
																														// flushed
																														// to
																														// disknew
																														// XSSFWorkbook(inp);
		try {
			inp.close();
			Sheet sheet0 = wb.getSheetAt(0);
			Sheet sheet = wb.getSheetAt(1);
			String reviewDate = request.getParameter("reviewDate");
			reviewDate = title;
			int numOfChrt = 10;
			int start = 0;
			int end = 0;
			for (int i = 0; i < dataList.size(); i++) {
				sheet.createRow(end).createCell(0).setCellValue("Radar Chart-Skill Index Analysis");

				end = end + 1;
				String[] name = nameList.get(0);
				CommonMessage.debugMsg("name[0]" + name[0]);
				// StringBuffer buf = new StringBuffer();
				// String qots = buf.append('"').toString();

				// CommonMessage.debugMsg("qots=="+qots);
				// name[0] = name[0].replace("(", qots + " & CHAR(10) & " + qots + "(");
				// CommonMessage.debugMsg("name[0]"+name[0].toString());
				// name[0]= name[0].replace(qots, "").toString();
				// CommonMessage.debugMsg("name===="+name[0].toString());
				// String[] namess =name[0].split("-");
				// CommonMessage.debugMsg("name[0]"+name[0]);
				// String frmla = "CONCATENATE(" + qots + namess[0]+ "-" +namess[1]+ qots
				// +",CHAR(10)," + qots + namess[2] + qots +")";
				// CommonMessage.debugMsg(frmla);
				sheet.createRow(end).createCell(0).setCellValue(name[0]);
				// sheet.createRow(end).createCell(0).setCellType(Cell.CELL_TYPE_FORMULA);
				// sheet.createRow(end).createCell(0).setCellFormula(frmla);

				start = end + 1;
				String[] dataArr = dataList.get(0);
				int lopEnd = start + 24;
				int count = 0;
				CommonMessage.debugMsg(start + "start" + lopEnd);
				for (int j = start; j < lopEnd; j++) {
					if (count < dataArr.length) {
						sheet.createRow(j).createCell(0).setCellValue(new Double(dataArr[count]));
						CommonMessage.debugMsg("dataArr[count]: " + dataArr[count]);
					} else {
						CommonMessage.debugMsg("Else");
						sheet.createRow(j).createCell(0).setCellValue(new Double("0"));

						CellStyle hiddenstyle = wb.createCellStyle();
						hiddenstyle.setHidden(true);
						sheet.getRow(j).setRowStyle(hiddenstyle);
						sheet.getRow(j).setZeroHeight(true);
					}
					count++;
				}
				end = end + 24;
			}

			int totalRow = 25 * numOfChrt;
			int startRow = 25 * dataList.size();
			CommonMessage.debugMsg(totalRow + " =totalRow " + startRow + " numOfChrt " + numOfChrt
					+ "  dataList.size() " + dataList.size());
			for (int k = startRow; k < totalRow; k++) {
				sheet.createRow(k).createCell(0).setCellValue(new Double("0"));
				CellStyle hiddenstyle = wb.createCellStyle();
				hiddenstyle.setHidden(true);
				sheet.getRow(k).setRowStyle(hiddenstyle);
				sheet.getRow(k).setZeroHeight(true);
			}
			int rowHide = dataList.size() % 3;
			int endRow = 4 * 20;
			int rowNum = 0;
			if (rowHide == 0) {
				rowNum = 20 * (dataList.size() / 3);
			} else {
				rowNum = 20 * ((dataList.size() + (3 - rowHide)) / 3);
			}
			CommonMessage.debugMsg(rowHide + "  endRow  " + endRow + " rowNum " + rowNum);
			for (int k = rowNum; k < endRow; k++) {
				sheet0.createRow(k).createCell(0);
				CellStyle hiddenstyle = wb.createCellStyle();
				hiddenstyle.setHidden(true);
				sheet0.getRow(k).setRowStyle(hiddenstyle);
				sheet0.getRow(k).setZeroHeight(true);
			}
			if (!UIUtils.isValidKeyId(reviewDate))
				reviewDate = " ";
			sheet0.getRow(0).getCell(0).setCellValue("" + reviewDate);

			for (int k = 2; k < 27; k++) {
				// sheet.createRow(k).createCell(0).setCellValue(new Double("0"));
				CellStyle hiddenstyle = wb.createCellStyle();
				hiddenstyle.setHidden(true);
				sheet.getRow(k).setRowStyle(hiddenstyle);
				sheet.getRow(k).setZeroHeight(true);
			}

			sheet0.protectSheet("admin");
			sheet.protectSheet("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	private void printArray(String name, String[] arr) {
		CommonMessage.debugMsg(name + ":");
		if (arr == null) {
			CommonMessage.debugMsg("NULL");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			CommonMessage.debugMsg("  [" + i + "] " + arr[i]);
		}
		// CommonMessage.debugMsg();
	}

	private void cleanScoreHeaders(JSONObject json) {
		try {
			JSONArray rows = json.getJSONArray("rowHeaders");

			// Only row 3 has scores in your structure
			JSONArray scoreRow = rows.getJSONArray(3);

			for (int i = 0; i < scoreRow.length(); i++) {
				String v = scoreRow.optString(i);

				if (v != null && v.contains("#")) {
					// Show only score
					String cleaned = v.substring(0, v.indexOf("#"));
					scoreRow.put(i, cleaned);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
