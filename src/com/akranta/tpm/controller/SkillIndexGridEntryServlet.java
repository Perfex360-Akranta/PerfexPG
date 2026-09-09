package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.SkillIndexGridEntryService;
import com.akranta.tpm.service.SkillIndexReportService;
import com.akranta.tpm.service.SkillindexRpService;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.SkillIndexGridEntryServiceImpl;
import com.akranta.tpm.service.impl.SkillIndexReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class SkillIndexGridEntryServlet
 */
@WebServlet("/SkillIndexGridEntryServlet")
public class SkillIndexGridEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	SkillIndexGridEntryService skillIndexGridEntryService;
	SkillindexRpService skillindexRpservice;
	DashboardService dashboardService;
	private static final String commonFilterScore = "SkillIndexScorecommonFilter";

	public SkillIndexGridEntryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);

		try {
			skillIndexGridEntryService = (SkillIndexGridEntryServiceImpl) UIUtils.getServiceObject(request,
					"SkillIndexGridEntryServiceImpl");

			skillindexRpservice = (SkillindexRpService) UIUtils.getServiceObject(request, "SkillindexRpServiceImpl");
			skillIndexGridEntryService
					.SkillIndexGridEntryServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
							: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CommonMessage.debugMsg("action..." + action);
		String dispatchUrl = null;

		if (action.equals("multipleSkillIndex_input.grdenty")) {

			String flId = request.getParameter("flId");
			String uniqPosid = request.getParameter("uniqPosid");
			String creteriaId = request.getParameter("creteriaId");
			String reviewDate = request.getParameter("reviewDate");

			request.setAttribute("flId", flId);
			request.setAttribute("uniqPosid", uniqPosid);
			request.setAttribute("creteriaId", creteriaId);
			request.setAttribute("reviewDate", reviewDate);

			UIUtils.forwardRequest(request, response, "/pages/ENT/SkillIndexAssessmentMultipleEntry.jsp");
			// UIUtils.forwardRequest(request, response,
			// "/pages/ENT/SkillIndexGridEntry.jsp");

		}

		else if (action.equals("multipleSkillIndex_getCol.grdenty")) {
			PrintWriter out = response.getWriter();

			String flnid = request.getParameter("flnid");
			CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
			String errlid = request.getParameter("errlid");
			CommonMessage.debugMsg("refKeyId::::::::::" + errlid);
			String empId = request.getParameter("empId");
			String reviewDate = request.getParameter("reviewDate");
			JSONObject jsonObject = new JSONObject();
			List<String[]> skillasslst = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setEmpch(empId);
				commonFilter.setStartDate(reviewDate);
				skillasslst = skillindexRpservice.getSkillAssessmentReport(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonObject = getTableModelSkillAssessment(skillasslst);
			httpSession.removeAttribute("SkillAssessmentColmodel");
			httpSession.setAttribute("SkillAssessmentColmodel", jsonObject);
			out.println(jsonObject);

		} else if (action.equals("multipleSkillIndex_getData.grdenty")) {
			List<String[]> skillDatalst = null;

			PrintWriter out = response.getWriter();

			String flnid = request.getParameter("flnid");
			CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
			String errlid = request.getParameter("errlid");
			String empId = request.getParameter("empId");
			String reviewDate = request.getParameter("reviewDate");
			CommonMessage.debugMsg("reviewDate::::::::::" + reviewDate);
			List<String[]> skillasslst = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setStartDate(reviewDate);
				commonFilter.setEmpch(empId);
				skillDatalst = skillindexRpservice.getSkillAssessmentReport(commonFilter);
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(skillDatalst, request, 3, 0);
				out.println(Achievements);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("UniquePositionform_getCol.grdenty")) {
			PrintWriter out = response.getWriter();

			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList", "empMulListGrid"));
			CommonMessage
					.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList", "empMulListGrid"));
		} else if (action.equals("UniquePositionform_getData.grdenty")) {
			try {
				PrintWriter out = response.getWriter();

				CommonFilter commonFilter = populateCommonFilter(request, "uniqueCommonFilter", true);
				
				GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
				if (gridParams == null)
					gridParams = new GridParams();
				FilterValues.populateGridParams(request, gridParams);
				
				String flnid = request.getParameter("flnid");
				CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
				String errlid = request.getParameter("errlid");
				String empId = request.getParameter("empId");
				String reviewDate = request.getParameter("reviewDate");
				String halfYear = request.getParameter("halfYear");
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setStartDate(reviewDate);
				commonFilter.setEmpch(empId);
				commonFilter.setYear(halfYear);
				UIUtils.displayRequestParamsValue(request);
				//List<String[]> uniquelist = skillIndexGridEntryService.getEmpList(commonFilter);
				
				List<String[]> uniquelist = skillIndexGridEntryService.getEmpListFunction(commonFilter,gridParams);
				net.sf.json.JSONObject unique = UIUtils.convertToJqGridTableObject(uniquelist, request, 1, 1);
				out.println(unique);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		/*
		 * else if (action.equals("UniquePositionform_getData.grdenty")) { try {
		 * PrintWriter out = response.getWriter();
		 * 
		 * CommonFilter commonFilter = populateCommonFilter(request,
		 * "uniqueCommonFilter", true);
		 * 
		 * String flnid = request.getParameter("flnid");
		 * CommonMessage.debugMsg("refKeyId::::::::::" + flnid); String errlid =
		 * request.getParameter("errlid"); String empId = request.getParameter("empId");
		 * String reviewDate = request.getParameter("reviewDate");
		 * commonFilter.setFlid(flnid); commonFilter.setEmmLinkKeyId(errlid);
		 * commonFilter.setStartDate(reviewDate); commonFilter.setEmpch(empId);
		 * UIUtils.displayRequestParamsValue(request); List<String[]> uniquelist =
		 * skillIndexGridEntryService.getEmpList(commonFilter);
		 * 
		 * net.sf.json.JSONObject unique =
		 * UIUtils.convertToJqGridTableObject(uniquelist, request, 0, 0);
		 * out.println(unique); } catch (Exception e) {
		 * CommonMessage.debugMsg(e.getMessage()); } }
		 */

		else if (action.equals("multipleSkillIndexEntry_input.grdenty")) {
			/*
			 * CommonMessage.debugMsg(" insid ethe Input method *******");
			 * flid="+flid+"&reviewDate="+reviewDate+"&errlid="+errlid;
			 */
			String filter = request.getParameter("filter");
			CommonMessage.debugMsg("this is this....." + filter);
			String reviewdate = request.getParameter("reviewDate");
			String uniquePosition = request.getParameter("errlid");
			String flid = request.getParameter("flid");
			String empKeyid = request.getParameter("empKeyid");

			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/SkillIndexGridEntry.jsp");
			request.setAttribute("flid", flid);
			request.setAttribute("uniquePosition", uniquePosition);
			request.setAttribute("reviewdate", reviewdate);
			request.setAttribute("empKeyid", empKeyid);
			rd.forward(request, response);
		} else if (action.equals("multipleSkillIndexEntry_getCol.grdenty")) {
			try {
				// out.println(getEquipmentColumnModel());
				PrintWriter out = response.getWriter();

				String reviewdate = request.getParameter("reviewDate");
				String uniquePosition = request.getParameter("errlid");
				String flid = request.getParameter("flid");
				String empKeyid = request.getParameter("empKeyid");

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
				List<String[]> skillIndexList;
				commonFilter.setFlid(flid);
//			commonFilter.setFlid(flnid);
				commonFilter.setUniquePos(uniquePosition);
				commonFilter.setFromDate(reviewdate);

				if (UIUtils.isValidKeyId(empKeyid)) {
					empKeyid = "" + empKeyid.replace(",", "','") + "";

					commonFilter.setEmpch(empKeyid);
				}
				skillIndexList = skillIndexGridEntryService.getTopicTask(commonFilter);

				JSONObject jsonObject = new JSONObject();
				// String[] header = {"Topic"};
				jsonObject = getTableModelSkillAssessment(skillIndexList);

				CommonMessage.debugMsg("colModel   " + jsonObject);
				CommonMessage.debugMsg(empKeyid + "  empKeyid after Replace");
				// JSONObject jsonObject = getTableModel(yyQtyList);
				jsonObject.put("tableHeight", "70%%");
				jsonObject.put("tableWidth", "110%%");
				// jsonObject.put("multiSelect", true);
				httpSession.removeAttribute("SkillAssessmentGridColmodel");
				httpSession.setAttribute("SkillAssessmentGridColmodel", jsonObject);
				out.println(jsonObject);
				CommonMessage.debugMsg(" after populate col ");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (action.equals("multipleSkillIndexEntry_getData.grdenty")) {
			try {
				UIUtils.displayRequestParamsValue(request);

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);

				String reviewdate = request.getParameter("reviewDate");
				String uniquePosition = request.getParameter("errlid");
				String flid = request.getParameter("flid");
				String empKeyid = request.getParameter("empKeyid");

				commonFilter.setFlid(flid);
//				commonFilter.setFlid(flnid);
				commonFilter.setUniquePos(uniquePosition);
				commonFilter.setFromDate(reviewdate);

				if (UIUtils.isValidKeyId(empKeyid)) {
					empKeyid = "" + empKeyid.replace(",", "','") + "";

					commonFilter.setEmpch(empKeyid);
				}
				CommonMessage.debugMsg(flid + " flid  " + uniquePosition + " roleId  " + reviewdate);

				List<String[]> skillIndexList = skillIndexGridEntryService.getTopicTask(commonFilter);
				CommonMessage.debugMsg("equipmentQueryList " + skillIndexList.size());
				PrintWriter out = response.getWriter();
				JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(skillIndexList, request, 3, 0,
						commonFilter.getTotalRecordCnt());
				out.println(equipmentQueryData);

				commonFilter.setViewClick('N');

				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		// Swetha - Excel - Created for Multiple Skill Index entry excel 14th November
		else if (action.equals("multipleSkillIndexEntry_getExcel.grdenty")) {

			// HttpSession httpSession = request.getSession(false);
			CommonMessage.debugMsg("Excel Servlet");
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);

			String reviewdate = request.getParameter("reviewDate");
			String uniquePosition = request.getParameter("errlid");
			String flid = request.getParameter("flid");
			String empKeyid = request.getParameter("empKeyid");

			commonFilter.setFlid(flid);
			commonFilter.setUniquePos(uniquePosition);
			commonFilter.setFromDate(reviewdate);

			CommonMessage.debugMsg("flid" + flid);
			CommonMessage.debugMsg("uniquePosition" + uniquePosition);

			if (UIUtils.isValidKeyId(empKeyid)) {
				empKeyid = "" + empKeyid.replace(",", "','") + "";

				commonFilter.setEmpch(empKeyid);
			}
			CommonMessage.debugMsg(flid + " flid  " + uniquePosition + " roleId  " + reviewdate);

			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			// String tableModel =
			// UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
			// "EqpQuery");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("SkillAssessmentGridColmodel");

			// FIX: Normalize headers before Excel generation

			tblJSONObj.put("title", "Multiple Skill Index Report - " + reviewdate);
			String format = ExcelUtils.getFormat(request);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {

//				String flidd = request.getParameter("flid");
//				String uniPosID = request.getParameter("uniPosID");
//				//String reviewDate = request.getParameter("reviewDate");
//				CommonMessage.debugMsg("uniPosID"+uniPosID);
//				UIUtils.displayRequestParamsValue(request);			
//				CommonMessage.debugMsg("uniPosID=="+uniPosID);
//				commonFilter.setFlid(flidd);
//				commonFilter.setUniquePos(uniPosID);
//				commonFilter.setFromDate(reviewdate);
//				commonFilter.setToDate(reviewdate);
				CommonMessage.debugMsg("entered");
			}

			// List<String[]> skillIndexList =
			// skillIndexReportService.getSkillIndex(commonFilter);

			Workbook wb = skillIndexGridEntryService.multipleSkillIndexReportExportExcel(commonFilter, tblJSONObj,
					format);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "MultipleSkillIndexReport", format);

		}
		// Swetha - Excel - Created for Multiple Skill Index entry excel 14th November

		else if (action.equals("multipleSkillIndexEntry_save.grdenty")) {
			try {
				saveSkillIndexAssement(request, response);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		// ----------

		if (action.equals("multipleSkillIndexMainGrd_input.grdenty")) {
			String mstkeyid = request.getParameter("keyId");

			String reportName = "";
			reportName = "ASSEMENT";
			request.setAttribute("mstkeyid", mstkeyid);
			request.setAttribute("reportName", reportName);
			request.setAttribute("mode", FormModes.create);

			UIUtils.forwardRequest(request, response, "/pages/ENT/SkillIndexGrdEntryMainGrid.jsp");

		}

		/*
		 * else if(action.equals("multipleSkillIndexMainGrd_getCol.grdenty") ) {
		 * PrintWriter out = response.getWriter(); List<String[]> skReqList = null;
		 * CommonFilter commonFilter =
		 * populateCommonFilter(request,"SkillIndexCommonFilter", true);
		 * CommonMessage.debugMsg("getColEntry:::::: "+commonFilter.getFromDate());
		 * 
		 * String reportName = request.getParameter("reportName"); String flid =
		 * request.getParameter("flid"); if (UIUtils.isValidKeyId(flid))
		 * commonFilter.setFlid(flid); if (!UIUtils.isValidKeyId(flid)) { String
		 * loginFlid = CommonFunctions.getLoginFlid(request);
		 * commonFilter.setFlid(loginFlid); } commonFilter.setHrschkbox("HEADER"); if
		 * (Constants.passNullDate.contains(commonFilter.getFromMonth()) &&
		 * (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")))
		 * {
		 * commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-6).substring(
		 * 3, 11)); commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
		 * commonFilter.setMonwise("Y"); }
		 * 
		 * try { commonFilter.setIsGetCol("Y"); skReqList =
		 * skillIndexGridEntryService.getSIMainGrid(commonFilter, reportName); } catch
		 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * JSONObject SkillData= UIUtils.convertToJqGridTableObject(skReqList,request,
		 * 3, 0, commonFilter.getTotalRecordCnt());
		 * CommonMessage.debugMsg("UOTSIDEskillindexRpservice ");
		 * httpSession.setAttribute("SkillData", SkillData); JqGridTableModel
		 * jqGridTableModel = new JqGridTableModel(); GridColModel gridColModel = new
		 * GridColModel();
		 * 
		 * jqGridTableModel.setRowNumbers(true); jqGridTableModel.setEnableFilter(true);
		 * jqGridTableModel.setTableButton(true);
		 * 
		 * gridColModel.setHeaderNum(1);
		 * 
		 * 
		 * gridColModel.setFormatter("btnShowFormmter"); if
		 * (reportName.equals("REVIEW")) { gridColModel.setFormattorFromCol("7");
		 * gridColModel.setFormattorToCol("7"); } else {
		 * gridColModel.setFormattorFromCol("8"); gridColModel.setFormattorToCol("8"); }
		 * 
		 * String [] colHeader = skReqList.get(1); String [] colHeaderHead =
		 * skReqList.get(0);
		 * 
		 * 
		 * List<String[]> headers = new ArrayList<String[]>(); headers.add(colHeader);
		 * headers.add(colHeaderHead);
		 * 
		 * 
		 * JSONObject jsonObject =
		 * UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
		 * jsonObject.set("tableWidth", "106%%"); jsonObject.set("tableHeight", "78%%");
		 * 
		 * jsonObject.put("isGroupBy", "false"); jsonObject.put("groupByField", false);
		 * jsonObject.put("rowNumbers", true); jsonObject.put("groupSummary", false);
		 * jsonObject.put("tableButton", true);
		 * 
		 * //jsonObject.put("tableHeight", "75%"); //jsonObject.put("data", SkillData);
		 * 
		 * CommonMessage.debugMsg(jsonObject);
		 * httpSession.removeAttribute("SkillReportColModel");
		 * httpSession.setAttribute("SkillReportColModel", jsonObject);
		 * out.println(jsonObject); }
		 */

		else if (action.equals("multipleSkillIndexMainGrd_getCol.grdenty")) {
			PrintWriter out = response.getWriter();
			List<String[]> skReqList = null;
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
			CommonMessage.debugMsg("getColEntry:::::: " + commonFilter.getFromDate());

			String reportName = request.getParameter("reportName");
			String flid = request.getParameter("flid");
			if (UIUtils.isValidKeyId(flid))
				commonFilter.setFlid(flid);
			if (!UIUtils.isValidKeyId(flid)) {
				String loginFlid = CommonFunctions.getLoginFlid(request);
				commonFilter.setFlid(loginFlid);
			}
			commonFilter.setHrschkbox("HEADER");
			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-6).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}

			try {
				commonFilter.setIsGetCol("Y");
				skReqList = skillIndexGridEntryService.getSIMainGrid(commonFilter, reportName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject SkillData = UIUtils.convertToJqGridTableObject(skReqList, request, 3, 0,
					commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("UOTSIDEskillindexRpservice ");
			httpSession.setAttribute("SkillData", SkillData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			/*
			 * jqGridTableModel.setRowNumbers(true); jqGridTableModel.setEnableFilter(true);
			 * jqGridTableModel.setTableButton(true);
			 */
			gridColModel.setHeaderNum(1);

			gridColModel.setFormatter("btnShowFormmter");
			if (reportName.equals("REVIEW")) {
				gridColModel.setFormattorFromCol("7");
				gridColModel.setFormattorToCol("7");
			} else {
				gridColModel.setFormattorFromCol("10");// change
				gridColModel.setFormattorToCol("10");// change
			}

			String[] colHeader = skReqList.get(1);
			String[] colHeaderHead = skReqList.get(0);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeaderHead);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "78%%");
			/*
			 * jsonObject.put("isGroupBy", "false"); jsonObject.put("groupByField", false);
			 * jsonObject.put("rowNumbers", true); jsonObject.put("groupSummary", false);
			 * jsonObject.put("tableButton", true);
			 */
			// jsonObject.put("tableHeight", "75%");
			// jsonObject.put("data", SkillData);

			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("SkillReportColModel");
			httpSession.setAttribute("SkillReportColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("multipleSkillIndexMainGrd_getData.grdenty")) {
			try {
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
				String reportName = request.getParameter("reportName");
				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("flid====" + flid);
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (!UIUtils.isValidKeyId(commonFilter.getFlid())) {
					String loginFlid = CommonFunctions.getLoginFlid(request);
					CommonMessage.debugMsg("flid====" + loginFlid);
					commonFilter.setFlid(loginFlid);
				}

//				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
//				{
//					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-2).substring(3, 11));
//					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
//					commonFilter.setMonwise("Y");
//				}
//				
				commonFilter.setIsGetCol("N");
				List<String[]> ShillDataList = skillIndexGridEntryService.getSIMainGrid(commonFilter, reportName);
				JSONObject SkillIndexData = UIUtils.convertToJqGridTableObject(ShillDataList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(SkillIndexData);
				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg("getdata Exception :" + e.getMessage());
				e.printStackTrace();
			}
		}

		else if (action.equals("multipleSkillIndexModfy_input.grdenty")) {
			CommonMessage.debugMsg(" insid ethe Input method *******");

			String flId = request.getParameter("flId");
			String uniqPosid = request.getParameter("uniqPosid");
			String creteriaId = request.getParameter("creteriaId");
			String reviewDate = request.getParameter("reviewDate");

			request.setAttribute("flId", flId);
			request.setAttribute("uniqPosid", uniqPosid);
			request.setAttribute("creteriaId", creteriaId);
			request.setAttribute("reviewDate", reviewDate);
			String filter = request.getParameter("filter");
			CommonMessage.debugMsg("this is this....." + filter);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/SkillIndexGridModify.jsp");
			request.setAttribute("filter", filter);
			rd.forward(request, response);
		} else if (action.equals("multipleSkillIndexModfy_getCol.grdenty")) {
			try {
				// out.println(getEquipmentColumnModel());
				PrintWriter out = response.getWriter();

				// var filterstr="?q=2&flnid="+flid+"&errlid="+errlid+"&reviewDate="+reviewDate;
				int condRow = 0;
				int headerRow = 1;
				String flid = request.getParameter("flnid");
				String roleId = request.getParameter("errlid");
				String reviewDate = request.getParameter("reviewDate");

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
				List<String[]> skillIndexList;
				commonFilter.setFlid(flid);
//			commonFilter.setFlid(flnid);
				commonFilter.setUniquePos(roleId);
				commonFilter.setFromDate(reviewDate);
				// commonFilter.setEmpch(empId);
				skillIndexList = skillIndexGridEntryService.getTopicTaskModify(commonFilter);

				JSONObject jsonObject = new JSONObject();
				// String[] header = {"Topic"};
				jsonObject = getTableModelSkillAssessment(skillIndexList);

				CommonMessage.debugMsg("colModel   " + jsonObject);

				// JSONObject jsonObject = getTableModel(yyQtyList);
				jsonObject.put("tableHeight", "70%%");
				jsonObject.put("tableWidth", "110%%");
				// jsonObject.put("multiSelect", true);
				httpSession.removeAttribute("skillIndexColModel");
				httpSession.setAttribute("skillIndexColModel", jsonObject);
				out.println(jsonObject);
				/*
				 * CommonMessage.debugMsg(" after populate col ");
				 * out.println(UIUtils.getPropertyValue(
				 * "com.akranta.tpm.resources.EquipmentRpt", "EqpQuery"));
				 */
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (action.equals("multipleSkillIndexModfy_getData.grdenty")) {
			try {
				httpSession = request.getSession(false);
				UIUtils.displayRequestParamsValue(request);

				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);

				String flid = request.getParameter("flnid");
				String roleId = request.getParameter("errlid");
				String reviewDate = request.getParameter("reviewDate");

				commonFilter.setFlid(flid);
//				commonFilter.setFlid(flnid);
				commonFilter.setUniquePos(roleId);
				commonFilter.setFromDate(reviewDate);

				CommonMessage.debugMsg(flid + " flid  " + roleId + " roleId  " + reviewDate);

				List<String[]> skillIndexList = skillIndexGridEntryService.getTopicTaskModify(commonFilter);
				CommonMessage.debugMsg("equipmentQueryList " + skillIndexList.size());
				PrintWriter out = response.getWriter();
				JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(skillIndexList, request, 3, 0,
						commonFilter.getTotalRecordCnt());
				out.println(equipmentQueryData);
				commonFilter.setViewClick('N');

				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("multipleSkillIndexModfy_getExcel.grdenty")) {

			CommonMessage.debugMsg("Excel Servlet");
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);

			String reviewdate = request.getParameter("reviewDate");
			String uniquePosition = request.getParameter("errlid");
			String flid = request.getParameter("flnid");
			// String empKeyid=request.getParameter("empKeyid") ;

			commonFilter.setFlid(flid);
			commonFilter.setUniquePos(uniquePosition);
			commonFilter.setFromDate(reviewdate);

			CommonMessage.debugMsg("flid" + flid);
			CommonMessage.debugMsg("uniquePosition" + uniquePosition);

			CommonMessage.debugMsg(flid + " flid  " + uniquePosition + " roleId  " + reviewdate);

			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("skillIndexColModel");
			CommonMessage.debugMsg("string " + tblJSONObj.toString(4));
			// String keyId = request.getParameter("KeyId");
			tblJSONObj.put("title", "Multipple Skill Index");
			String format = ExcelUtils.getFormat(request);
			commonFilter.setFromRow(tmpFromRow);

			Workbook wb = skillIndexGridEntryService.getExportExcel(commonFilter, tblJSONObj, format);

			ExcelUtils.writeToResponse(response, wb, "Multipple Skill Index", format);

		}

		else if (action.equals("multipleSkillIndexModfy_save.grdenty")) {
			try {
				saveSkillIndexAssement(request, response);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

	}

	/*
	 * private void saveSkillIndexAssement(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { HttpSession httpSession =
	 * request.getSession(false); ServletOutputStream out =
	 * response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request); try { if (httpSession != null && user != null)
	 * {
	 * 
	 * EntTlSkillindexassessmst existEntTlSkillindexassessmst =
	 * (EntTlSkillindexassessmst) httpSession
	 * .getAttribute("newEntTlAssessmentmst"); EntTlSkillindexassessmst
	 * newEntTlSkillindexassessmst = new EntTlSkillindexassessmst();// mas
	 * 
	 * EntTlSkillindexassessdtl newEntTlSkillindexassessdtl = new
	 * EntTlSkillindexassessdtl();
	 * 
	 * String MasterProject = request.getParameter("masterData"); String
	 * DetailProject = request.getParameter("detailData");
	 * CommonMessage.debugMsg("TrainingDetails: " + MasterProject);
	 * CommonMessage.debugMsg(MasterProject + "MASTERDATA: " + DetailProject); //
	 * master List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl = null;
	 * 
	 * List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst = null;
	 * JSONArray jsonmasterArray = null; JSONArray jsondetailArray = null; if
	 * (UIUtils.isValidKeyId(MasterProject)) { // master json
	 * 
	 * if (MasterProject != null && !MasterProject.isEmpty()) { jsonmasterArray =
	 * JSONArray.fromString(MasterProject); lstEntTlSkillindexassessmstmst =
	 * (List<EntTlSkillindexassessmst>) UIUtils
	 * .convertJSONArrToList(newEntTlSkillindexassessmst, jsonmasterArray);
	 * 
	 * } if (lstEntTlSkillindexassessmstmst != null) {
	 * newEntTlSkillindexassessmst.setSiamCreatedby(user.getUsrm_keyid()); } } List
	 * for detail * if (UIUtils.isValidKeyId(DetailProject)) { // detail json if
	 * (DetailProject != null && !DetailProject.isEmpty()) { jsondetailArray =
	 * JSONArray.fromString(DetailProject); lstEntTlSkillindexassessmstdtl =
	 * (List<EntTlSkillindexassessdtl>) UIUtils
	 * .convertJSONArrToList(newEntTlSkillindexassessdtl, jsondetailArray); }
	 * 
	 * }
	 * 
	 * JSONObject successData = new JSONObject(); JSONObject
	 * trainingAttEffSuccessMsg = new JSONObject(); String savemsg; //
	 * newEntTlAssessmentmst.setAsmmCreatedby(user.getUsrm_ccno());
	 * CommonMessage.debugMsg("lstEntTlSkillindexassessmstmst   ass(): " +
	 * lstEntTlSkillindexassessmstmst.size());
	 * CommonMessage.debugMsg("lstEntTlSkillindexassessmstdtl   ==(): " +
	 * lstEntTlSkillindexassessmstdtl.size());
	 * 
	 * // existKznTlKkprojectprioritymst=kkProjecPriorityService.create(
	 * newEntTlSkillindexassessmst, // existKznTlKkprojectprioritymst);
	 * skillIndexGridEntryService.updateSkillAssement(
	 * lstEntTlSkillindexassessmstmst, lstEntTlSkillindexassessmstdtl,
	 * user.getUsrm_keyid());
	 * 
	 * savemsg =
	 * UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",
	 * "success-update");
	 * 
	 * successData.put("msg", savemsg); trainingAttEffSuccessMsg.put("successData",
	 * successData); trainingAttEffSuccessMsg.put("formClear", false);
	 * 
	 * out.print(trainingAttEffSuccessMsg.toString());
	 * 
	 * } // newEntTlAssessmentmst //
	 * =entTlAssessmentmstService.create(newEntTlAssessmentmst,
	 * existEntTlAssessmentmst,entTlAssessmentmstBean);
	 * 
	 * } catch (ValidationExceptions e) {
	 * CommonMessage.debugMsg("validations exception"); JSONObject errMessage =
	 * UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
	 * CommonMessage.debugMsg(errMessage.toString());
	 * out.print(errMessage.toString());
	 * 
	 * } catch (BusinessApplicationExceptions e) {
	 * CommonMessage.debugMsg("DNFKHDJFDK" + e.toString()); JSONObject errMessage =
	 * UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
	 * out.print(errMessage.toString());
	 * 
	 * } catch (Exception e) {
	 * 
	 * JSONObject err = new JSONObject(); // err.put("tpmException",
	 * "Data Not Saved"); err.put("tpmException", e.getMessage());
	 * out.print(err.toString()); }
	 * 
	 * }
	 */
	private void saveSkillIndexAssement(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		//String newSiamKeyId = request.getParameter("siamKeyidNew");//-CHANGED HERE SWETHA
		try {
			if (httpSession != null && user != null) {

				EntTlSkillindexassessmst existEntTlSkillindexassessmst = (EntTlSkillindexassessmst) httpSession
						.getAttribute("newEntTlAssessmentmst");
				EntTlSkillindexassessmst newEntTlSkillindexassessmst = new EntTlSkillindexassessmst();// mas

				EntTlSkillindexassessdtl newEntTlSkillindexassessdtl = new EntTlSkillindexassessdtl();

				String MasterProject = request.getParameter("masterData");
				String DetailProject = request.getParameter("detailData");
				System.out.println("TrainingDetails: " + MasterProject);
				System.out.println(MasterProject + "MASTERDATA: " + DetailProject); // master
				List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl = null;

				List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst = null;
				JSONArray jsonmasterArray = null;
				JSONArray jsondetailArray = null;
				if (UIUtils.isValidKeyId(MasterProject)) { // master json

					if (MasterProject != null && !MasterProject.isEmpty()) {
						jsonmasterArray = JSONArray.fromString(MasterProject);
						lstEntTlSkillindexassessmstmst = (List<EntTlSkillindexassessmst>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessmst, jsonmasterArray);

					}
					if (lstEntTlSkillindexassessmstmst != null) {
						newEntTlSkillindexassessmst.setSiamCreatedby(user.getUsrm_keyid());
					}
				}
				/* List for detail **/
				if (UIUtils.isValidKeyId(DetailProject)) { // detail json
					if (DetailProject != null && !DetailProject.isEmpty()) {
						jsondetailArray = JSONArray.fromString(DetailProject);
						lstEntTlSkillindexassessmstdtl = (List<EntTlSkillindexassessdtl>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessdtl, jsondetailArray);
					}

				}

				JSONObject successData = new JSONObject();
				JSONObject trainingAttEffSuccessMsg = new JSONObject();
				String savemsg;
				// newEntTlAssessmentmst.setAsmmCreatedby(user.getUsrm_ccno());
				System.out.println("lstEntTlSkillindexassessmstmst   ass(): " + lstEntTlSkillindexassessmstmst.size());
				System.out.println("lstEntTlSkillindexassessmstdtl   ==(): " + lstEntTlSkillindexassessmstdtl.size());

				// existKznTlKkprojectprioritymst=kkProjecPriorityService.create(newEntTlSkillindexassessmst,
				// existKznTlKkprojectprioritymst);
				skillIndexGridEntryService.updateSkillAssement(lstEntTlSkillindexassessmstmst,
						lstEntTlSkillindexassessmstdtl, user.getUsrm_keyid());//-CHANGED HERE SWETHA

				savemsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update");

				successData.put("msg", savemsg);
				trainingAttEffSuccessMsg.put("successData", successData);
				trainingAttEffSuccessMsg.put("formClear", false);

				out.print(trainingAttEffSuccessMsg.toString());

			} // newEntTlAssessmentmst
				// =entTlAssessmentmstService.create(newEntTlAssessmentmst,existEntTlAssessmentmst,entTlAssessmentmstBean);

		} catch (ValidationExceptions e) {
			System.out.println("validations exception");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
			CommonFunctions.debugMsg(errMessage.toString());
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {
			System.out.println("DNFKHDJFDK" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());

		} catch (Exception e) {

			JSONObject err = new JSONObject();
			// err.put("tpmException", "Data Not Saved");
			err.put("tpmException", e.getMessage());
			out.print(err.toString());
		}

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

	private JSONObject getTableModelSkillAssessment(List<String[]> headers) {
		CommonMessage.debugMsg("Printing in the Table Model");

		for (String[] arr : headers) {
			CommonMessage.debugMsg(Arrays.toString(arr));
		}
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		// jqGridTableModel.setC
		// jqGridTableModel.setHeaderNum(4 );
		String[] row = headers.get(0);
		String[] colHeader = headers.get(1);
		String[] colHeader2 = headers.get(2);
		// String[] colHeader3 = headers.get(3);
		String[] tempCol = new String[row.length];
		jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		// jqGridTableModel.getRowHeaders().add(colHeader3);

		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableButton(true);

		String headerSql = "'SELECT ";
		String header = "'SELECT ";

		for (int i = 0; i < colHeader.length; i++) {

			JqGridColModel jqGridColModel = new JqGridColModel();

			jqGridColModel.setIndex(tempCol[i] = "");
			jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));

			/*
			 * jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			 * jqGridColModel.setName(row[i].replaceAll(" ", ""));
			 */
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(row[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(row[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(row[i].replaceAll(" ", "") + i);
			jqGridColModel.setWidth(60);

			if (i == 0 || i == 1 || i == 5 || i == 6) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setFrozen(true);
			}
			if (i == 2) {
				jqGridColModel.setWidth(150);
				jqGridColModel.setFrozen(true);
			}
			if (i == 3) {
				jqGridColModel.setWidth(45);
				jqGridColModel.setFrozen(true);
			}
			if (i == 4) {
				jqGridColModel.setWidth(300);
				jqGridColModel.setFrozen(true);
			}
			// if ( jqGridColModel.getName().contains("EMP")) {

			/*
			 * if ( i == 7 ) { jqGridColModel.setWidth(90);
			 * jqGridColModel.setAlign("center"); jqGridColModel.setHidden(false);
			 * jqGridColModel.setFrozen(true); }
			 */
			CommonMessage.debugMsg(" In side the DaoImpl " + jqGridColModel.getName());
			if (i >= 7) {
				if (jqGridColModel.getName().substring(0, 3).equals("LAD")) {
					jqGridColModel.setWidth(40);
					jqGridColModel.setAlign("center");
				} else if (jqGridColModel.getName().substring(0, 3).equals("CHK")) {
					jqGridColModel.setWidth(40);
					jqGridColModel.setAlign("center");
				} else {
					// for(int j=1;j<row.length;j++){
					jqGridColModel.setFormatter("formatterCheckbox");
					jqGridColModel.setWidth(40);
					jqGridColModel.setAlign("center");
				}
				// j+=3;
				// }
			}
			// i=+3;

			/*
			 * if ( i >8 && (i+3)<colHeader.length-1) { jqGridColModel.setWidth(100);
			 * jqGridColModel.setAlign("center");
			 * jqGridColModel.setFormatter("formatterCheckbox");
			 * 
			 * }
			 */
			/*
			 * if ( i ==9) {
			 * 
			 * //for(int j=1;j<row.length;j++){
			 * //jqGridColModel.setFormatter("formatterCheckbox");
			 * jqGridColModel.setWidth(40); jqGridColModel.setAlign("center");
			 */
			// j+=3;
			// }
			// }
			if (i == colHeader.length) {
				jqGridColModel.setHidden(true);

			}

			CommonMessage.debugMsg("column[" + i + "]::::" + jqGridColModel.getName());

			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			header = header + "''" + jqGridColModel.getName() + "'' as " + jqGridColModel.getName().toUpperCase() + ",";

		}
		headerSql = headerSql.substring(0, headerSql.length() - 1) + " FROM DUAL ";
		header = header + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql....." + headerSql);
		CommonMessage.debugMsg("header......." + header);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "100%%");
		return tableModel;

	}

}
