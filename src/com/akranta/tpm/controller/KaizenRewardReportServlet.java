package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.KaizenRewardReportService;
import com.akranta.tpm.service.impl.KaizenRewardReportServiceImpl;
import com.akranta.tpm.service.impl.MenuTreeServicesImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class KaizenRewardReportServlet
 */
@WebServlet("/KaizenRewardReportServlet")
public class KaizenRewardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String commonFilterIden = "KaizenRewardCommonFilter";
	private static final String commonFilterpendg = "commonFilterpending";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	KaizenRewardReportService kaizenRewardReportService;

	public KaizenRewardReportServlet() {
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

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);

		String action = UIUtils.getActionPart(request);
		try {
			kaizenRewardReportService = (KaizenRewardReportServiceImpl) UIUtils.getServiceObject(request,
					"KaizenRewardReportServiceImpl");
			kaizenRewardReportService
					.KaizenRewardReportServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
							: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (action.equals("kaizenrewardreport_input.krsr")) {
			CommonMessage.debugMsg("In side the url " + action);
			UIUtils.forwardRequest(request, response, "/pages/KaizenRewardScheme.jsp");
		}

		else if (action.equals("kaizenrewardreport_getCol.krsr")) {

			PrintWriter out = response.getWriter();
			httpSession = request.getSession(false);
			CommonMessage.debugMsg(" In side the getttttttttttttttttt");
			CommonFilter commonFilter = populateCommonFilter(request, "KaizenRewardCommonFilter", true);
			httpSession.removeAttribute(commonFilterIden);
			httpSession.setAttribute(commonFilterIden, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getOPLandKaizen(request, commonFilter);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-2).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}
			commonFilter.setIsGetCol("Y");
			List<String[]> KaizenGridData = null;
			try {
				KaizenGridData = kaizenRewardReportService.getKaizenRewardGridData(commonFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

			JSONObject colModel = new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "108%%");
			httpSession.removeAttribute("KaizenRewardSchemColModel");
			httpSession.setAttribute("KaizenRewardSchemColModel", colModel);

			httpSession.removeAttribute("KaizenRewardCommonFilter");
			httpSession.setAttribute("KaizenRewardCommonFilter", commonFilter);

			out.println(colModel);
		} else if (action.equals("kaizenrewardreport_getData.krsr")) {
			PrintWriter out = response.getWriter();
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "KaizenRewardCommonFilter", false);
			commonFilter.setIsGetCol("N");
			List<String[]> KaizenGridData = null;
			try {
				KaizenGridData = kaizenRewardReportService.getKaizenRewardGridData(commonFilter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData, request, 2, 0,
					commonFilter.getTotalRecordCnt());
			out.print(dataJson);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute("KaizenRewardCommonFilter");
			httpSession.setAttribute("KaizenRewardCommonFilter", commonFilter);
		} else if (action.equals("kaizenrewardreport_getExcel.krsr")) {
			CommonFilter commonFilter = populateCommonFilter(request, "KaizenRewardCommonFilter", false);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenRewardSchemColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title",
					"Kaizen Reward Scheme Report - " + commonFilter.getFromMonth() + " - " + commonFilter.getToMonth());
			String format = ExcelUtils.getFormat(request);
			Workbook wb = null;
			try {
				wb = kaizenRewardReportService.getKaizenRewardGridDataExportExcel(commonFilter, tblJSONObj, format);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenSummaryReport", format);
		}

		if (action.equals("kaizenpendingreport_input.krsr")) {
			CommonMessage.debugMsg("In side the url " + action);

			UIUtils.forwardRequest(request, response, "/pages/KaizenPendingSummaryReport.jsp");
		}

		else if (action.equals("kaizenpendingreport_getCol.krsr")) {

			// ---------------------------
			PrintWriter out = response.getWriter();
			httpSession = request.getSession(false);

			CommonFilter commonFilter = populateCommonFilter(request, "commonFilterDtl", true);
			String firstClick = request.getParameter("firstClick");
			String type = request.getParameter("type");
			String keyId = request.getParameter("Keyid");
			String drill = request.getParameter("filt");
			String dtl = request.getParameter("dtl");

			String cmbCmpnId = request.getParameter("cmbCompid");
			String cmbLocnId = request.getParameter("cmbLocnid");
			String cmbFactId = request.getParameter("cmbFactid");
			String cmbSbuId = request.getParameter("cmbSbuid");
			String cmbPbuId = request.getParameter("cmbPbuid");
			String fnlnKeyid = request.getParameter("flid");
			String flid = request.getParameter("flids");
			if (flid == null || flid == "" || flid == "undefined") {
				commonFilter.setFlid(fnlnKeyid);

			} else {
				commonFilter.setFlid(flid);
			}

			CommonMessage.debugMsg(cmbCmpnId + "  .......... " + cmbLocnId + " oppppppppp  " + cmbFactId + "  " + cmbSbuId
					+ "  " + cmbPbuId);
			String drillLevel = null;
			if (keyId == null) {
				drillLevel = "CMP";
			}
			if (keyId != null) {
				drillLevel = keyId.substring(0, 3);
			}
			if (drillLevel.equals("CMP")) {
				drillLevel = "SEC";
			} else if (drillLevel.equals("LCN")) {
				drillLevel = "SEC";
			}

			else if (drillLevel.equals("SBU")) {
				drillLevel = "SEC";
			}

			else if (drillLevel.equals("PBU")) {
				drillLevel = "SEC";
			} else if (drillLevel.equals("SEC")) {
				drillLevel = "CEL";
			} else if (drillLevel.equals("CEL")) {
				drillLevel = "CEL";
			} else if (drillLevel.equals("CEL")) {
				drillLevel = "DTL";
			}

			commonFilter.setDrillLevel(drillLevel);
			httpSession.setAttribute("fromMonth", commonFilter.getFromMonth());
			httpSession.setAttribute("toMonth", commonFilter.getToMonth());
			// httpSession = request.getSession(false);

			CommonMessage.debugMsg(commonFilter.getMonwise() + " ........... " + commonFilter.getFromMonth() + "   "
					+ commonFilter.getToMonth() + " In side the getttttttttttttttttt inside getCol "
					+ commonFilter.getFlid() + "...........///" + commonFilter.getFromMonth());

			httpSession.removeAttribute(commonFilterpendg);
			httpSession.setAttribute(commonFilterpendg, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setIsGetCol("Y");
			List<String[]> KaizenGridData = null;
			try {
				KaizenGridData = kaizenRewardReportService.getKaizenPendingGridData(commonFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject jsonObject;
			if (dtl != null) {
				CommonMessage.debugMsg(" In side the DTL    " + commonFilter.getDrillLevel());
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();
				gridColModel.setHeaderNum(1);

				String[] colHeader = KaizenGridData.get(0);
				String[] colHeaderCond = KaizenGridData.get(0);

				// CommonMessage.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				// headers.add(colHeaderCond);
				headers.add(colHeader);

				jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
				JSONObject colModel = new JSONObject();
				jsonObject.set("tableHeight", "80%%");
				jsonObject.set("tableWidth", "108%%");
				httpSession.removeAttribute("KaizenPendingSmryColModel");
				httpSession.setAttribute("KaizenPendingSmryColModel", jsonObject);

			} else {
				jsonObject = getTableModelGraph(KaizenGridData, FilterValues.getHeader(commonFilter.getDrillCaption()));
				String[] colHeader = KaizenGridData.get(0);
				String[] colHeaderCond = KaizenGridData.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				JSONObject colModel = new JSONObject();
//			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);

				jsonObject.set("tableHeight", "80%%");
				jsonObject.set("tableWidth", "108%%");

				httpSession.removeAttribute("KaizenPendingSmryColModel");
				httpSession.setAttribute("KaizenPendingSmryColModel", jsonObject);
			}
			out.println(jsonObject);

			httpSession.removeAttribute("commonFilterDtl");
			httpSession.setAttribute("commonFilterDtl", commonFilter);
			commonFilter.setViewClick('Y');

		} else if (action.equals("kaizenpendingreport_getData.krsr")) {

			PrintWriter out = response.getWriter();
			httpSession = request.getSession(false);

			String firstClick = request.getParameter("firstClick");

			String type = request.getParameter("type");

			String keyId = request.getParameter("Keyid");
			String drill = request.getParameter("filt");

			String dtl = request.getParameter("dtl");
			String totalScore = request.getParameter("totalsr");
			String fnlnKeyid = request.getParameter("flid");
			String flid = request.getParameter("flids");

			CommonMessage.debugMsg(keyId + " keyIdkeyIdkeyIdkeyId");
			CommonMessage.debugMsg("keyIdkeyId" + keyId);
			String drillLevel = null;

			if (keyId == null) {
				drillLevel = "CMP";
			}
			if (keyId != null) {
				drillLevel = keyId.substring(0, 3);
			}
			if (drillLevel.equals("CMP")) {
				drillLevel = "SEC";
			} else if (drillLevel.equals("LCN")) {
				drillLevel = "SEC";
			}

			else if (drillLevel.equals("SBU")) {
				drillLevel = "SEC";
			}

			else if (drillLevel.equals("PBU")) {
				drillLevel = "SEC";
			} else if (drillLevel.equals("SEC")) {
				drillLevel = "CEL";
			} else if (drillLevel.equals("CEL")) {
				drillLevel = "DTL";
			}

			CommonMessage.debugMsg(" Inside getCol " + type);
			CommonMessage.debugMsg(" Inside getdAATA  " + drillLevel);

			CommonFilter commonFilter = populateCommonFilter(request, "commonFilterDtl", false);
			if (flid == null || flid == "" || flid == "undefined") {
				commonFilter.setFlid(fnlnKeyid);

			} else {
				commonFilter.setFlid(flid);
			}
			// httpSession.getAttribute("commonFilterDtl");
			CommonMessage.debugMsg(commonFilter.getFromMonth() + "   " + commonFilter.getToMonth()
					+ " In side the getttttttttttttttttt inside getData " + commonFilter.getFlid() + "...........///"
					+ commonFilter.getFromMonth());

			/*
			 * if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null &&
			 * !firstClick.equals("Y"))) { commonFilter = (CommonFilter)
			 * httpSession.getAttribute("commonFilterPending"); }
			 */
			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}

			commonFilter.setDrillLevel(drillLevel);
			commonFilter.setIsGetCol("N");
			List<String[]> KaizenGridData = null;
			try {
				KaizenGridData = kaizenRewardReportService.getKaizenPendingGridData(commonFilter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject dataJson = null;
			if (dtl != null) {
				dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData, request, 3, 0,
						commonFilter.getTotalRecordCnt());

			} else {
				dataJson = UIUtils.convertToJqGridTableObject(KaizenGridData, request, 1, 0,
						commonFilter.getTotalRecordCnt());
			}

			out.print(dataJson);
			httpSession.removeAttribute("commonFilterDtl");
			httpSession.setAttribute("commonFilterDtl", commonFilter);

		}

		else if (action.equals("kaizenpendingreport_getExcel.krsr")) {
			// CommonFilter commonFilter =
			// populateCommonFilter(request,"KaizenRewardCommonFilter",false);

			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("commonFilterDtl");// populateCommonFilter(request,"KaizenRewardCommonFilter",false);

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenPendingSmryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Pending Kaizen Summary Report - ");
			CommonMessage.debugMsg(commonFilter.getDrillLevel() + "  commonFilter commonFilter ");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = null;
			try {
				wb = kaizenRewardReportService.getKaizenPendingGridDataExportExcel(commonFilter, tblJSONObj, format);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenSummaryReport", format);
		}

		else if (action.equals("kaizenpendingreportDtl_getCol.krsr"))

		{
			PrintWriter out = response.getWriter();
			httpSession = request.getSession(false);

			try {
				String flid = request.getParameter("flids");
				// CommonFilter commonFilter = new CommonFilter();
				CommonFilter commonFilter = populateCommonFilter(request, "commonFilterPending", true);
				commonFilter.setFlid(flid);
				/*
				 * httpSession.setAttribute("fromMonth", commonFilter.getFromMonth());
				 * httpSession.setAttribute("toMonth", commonFilter.getToMonth());
				 * httpSession.removeAttribute(commonFilterpendg);
				 * httpSession.setAttribute(commonFilterpendg, commonFilter);
				 * FilterValues.getCommonFilters(request, commonFilter);
				 * FilterValues.getOPLandKaizen(request, commonFilter);
				 */

				List<String[]> kaizenReportList = kaizenRewardReportService.getPendingKaizenReport(commonFilter);
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setPaginate(true);

				gridColModel.setHeaderNum(1);

				String[] colHeader = kaizenReportList.get(1);
				String[] colHeaderCond = kaizenReportList.get(0);

				// CommonMessage.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				// headers.add(colHeaderCond);
				headers.add(colHeader);

				JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);

				jsonObject.put("tableHeight", "90%%");
				jsonObject.put("tableWidth", "108%%");
				httpSession.setAttribute("KaizenColModel", jsonObject);
				out.println(jsonObject);
				/*
				 * String kznImprProjectColM =
				 * UIUtils.getPropertyValue("com.akranta.tpm.resources.KZNRelatedColHeaders",
				 * "kznModification"); out.print(kznImprProjectColM);
				 */
				httpSession.removeAttribute("commonFilterPending");
				httpSession.setAttribute("commonFilterPending", commonFilter);
				commonFilter.setViewClick('Y');
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("kaizenpendingreportDtl_getData.krsr")) {
			try {
				PrintWriter out = response.getWriter();
				// CommonFilter commonFilter =
				// (CommonFilter)httpSession.getAttribute("kaizenCommonFilterVal");
				CommonFilter commonFilter = populateCommonFilter(request, "commonFilterPending", false);
				/*
				 * httpSession.setAttribute("fromMonth", commonFilter.getFromMonth());
				 * httpSession.setAttribute("toMonth", commonFilter.getToMonth());
				 * httpSession.removeAttribute(commonFilterpendg);
				 * httpSession.setAttribute(commonFilterpendg, commonFilter);
				 */
				FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.getOPLandKaizen(request, commonFilter);
				String flid = request.getParameter("flids");
				commonFilter.setFlid(flid);
				/// -----------------
				if (Constants.passNullDate.contains(commonFilter.getFromMonth())
						&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
					commonFilter.setMonwise("Y");
				}
				List<String[]> kaizenReportList = kaizenRewardReportService.getPendingKaizenReport(commonFilter);
				JSONObject kaizenReportData = UIUtils.convertToJqGridTableObject(kaizenReportList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(kaizenReportData);
				httpSession.removeAttribute("commonFilterPending");
				httpSession.setAttribute("commonFilterPending", commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		// ----------------------------

		else if (action.equals("kaizenpendingreportDtl_getExcel.krsr")) {
			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("commonFilterPending");// populateCommonFilter(request,"KaizenRewardCommonFilter",false);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("KaizenColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "Pending Kaizen Detail Summary Report - ");
			CommonMessage.debugMsg(commonFilter.getDrillLevel() + "  commonFilter commonFilter ");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = null;
			try {
				wb = kaizenRewardReportService.getDtlKaizenPendingGridDataExportExcel(commonFilter, tblJSONObj, format);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "KaizenSummaryReport", format);
		}

	}
	/*
	 * private JSONObject getTableModelGraph(List<String[]> kaizenGridData, String
	 * caption) { JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 * 
	 * String[] colHeader = kaizenGridData.get(0); //String[] colHeader1 =
	 * kaizenGridData.get(2);
	 * 
	 * if(caption.equals("Factory")){ caption="PBU"; }else
	 * if(caption.equals("Section")){ caption="DMT"; }else
	 * if(caption.equals("Line")){ caption="JH"; } //caption="Flid"; String[]
	 * emptyrow = new String[colHeader.length]; emptyrow[0] = ""; emptyrow[1] = "";
	 * CommonMessage.debugMsg("test..."+colHeader.toString()); for (int i = 2; i
	 * <colHeader.length; i++) { emptyrow[i] = ""; }
	 * jqGridTableModel.getRowHeaders().add(emptyrow);
	 * jqGridTableModel.getRowHeaders().add(colHeader);
	 * //jqGridTableModel.getRowHeaders().add(colHeader1);
	 * jqGridTableModel.setTableButton(true); jqGridTableModel.setRowNumbers(true);
	 * 
	 * //colHeader1[3] = caption; colHeader[3] = caption; //String headerSql =
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
	 * if (i == 0 ||i == 1||i ==2||i == 13||i == 3 ) {
	 * jqGridColModel.setHidden(true); //jqGridColModel.setKey(true); } else if (i
	 * == 4) { // jqGridColModel.setHidden(false); //jqGridColModel.setKey(true);
	 * jqGridColModel.setAlign("left"); jqGridColModel.setWidth(90); } else if (i >
	 * 4) { // jqGridColModel.setHidden(false); //jqGridColModel.setKey(true);
	 * jqGridColModel.setAlign("center"); jqGridColModel.setWidth(90); }
	 * 
	 * else if (i == 15) { CommonMessage.debugMsg("Length of col:" + colHeader.length);
	 * jqGridColModel.setHidden(true); //jqGridColModel.setKey(true); }
	 * 
	 * jqGridTableModel.getColModel().add(jqGridColModel);
	 * 
	 * //headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel); }
	 * JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 * //headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL' ";
	 * //CommonMessage.debugMsg("headerSql.....123..."+headerSql);
	 * tableModel.set("tableHeight", "67%%");
	 * 
	 * CommonMessage.debugMsg("colmodel:" + tableModel); return tableModel; }
	 */
	
	/*
	 * private JSONObject getTableModelGraph(List<String[]> kaizenGridData, String
	 * caption) { JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 * String[] colHeader = kaizenGridData.get(0);
	 * 
	 * if(caption.equals("Factory")){ caption="PBU"; }else
	 * if(caption.equals("Section")){ caption="DMT"; }else
	 * if(caption.equals("Line")){ caption="JH"; }
	 * 
	 * String[] emptyrow = new String[colHeader.length]; for (int i = 0; i <
	 * colHeader.length; i++) { emptyrow[i] = ""; }
	 * jqGridTableModel.getRowHeaders().add(emptyrow);
	 * 
	 * // Create a copy of colHeader to avoid modifying the original String[]
	 * displayHeader = colHeader.clone(); displayHeader[3] = caption;
	 * jqGridTableModel.getRowHeaders().add(displayHeader);
	 * 
	 * jqGridTableModel.setTableButton(true); jqGridTableModel.setRowNumbers(true);
	 * 
	 * for (int i = 0; i <= colHeader.length-1; i++) { JqGridColModel jqGridColModel
	 * = new JqGridColModel(); jqGridColModel.setIndex(colHeader[i].replaceAll(" ",
	 * "") + i); jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
	 * 
	 * jqGridColModel.setWidth(200); jqGridColModel.setAlign("left");
	 * jqGridColModel.setEditable(false);
	 * 
	 * // Based on actual column order from console: // 0="0", 1=DISPKEYID,
	 * 2=KEYIDFIELD, 3=Code, 4=Flid, // 5=JH Leader Pending, 6=DMT Leader Pending,
	 * 7=PBU Head Pending, // 8=SBU Head Pending, 9=PLANT Head Pending, 10=FINANCE
	 * Head Pending, // 11=Benefit Amount, 12=Total
	 * 
	 * // Hide: 0 (row number), 1 (DISPKEYID), 2 (KEYIDFIELD), 4 (Flid), 12 (Total)
	 * if (i == 0 || i == 1 || i == 2 || i == 4 || i == 12) {
	 * jqGridColModel.setHidden(true); } // Show Code column (index 3) with left
	 * alignment else if (i == 3) { jqGridColModel.setHidden(false);
	 * jqGridColModel.setAlign("left"); jqGridColModel.setWidth(120); } // Show all
	 * pending columns (5-11) - center aligned else if (i >= 5 && i <= 11) {
	 * jqGridColModel.setHidden(false); jqGridColModel.setAlign("center");
	 * jqGridColModel.setWidth(90); }
	 * 
	 * jqGridTableModel.getColModel().add(jqGridColModel); }
	 * 
	 * JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 * tableModel.set("tableHeight", "67%%"); CommonMessage.debugMsg("colmodel:" +
	 * tableModel); return tableModel; }
	 */
	
	private JSONObject getTableModelGraph(List<String[]> kaizenGridData,
	        String caption) {
	    JqGridTableModel jqGridTableModel = new JqGridTableModel();
	    String[] colHeader = kaizenGridData.get(0);
	    
	    if(caption.equals("Factory")){
	        caption="PBU";
	    }else if(caption.equals("Section")){
	        caption="DMT";
	    }else if(caption.equals("Line")){
	        caption="JH";
	    }
	    
	    String[] emptyrow = new String[colHeader.length];
	    for (int i = 0; i < colHeader.length; i++) {
	        emptyrow[i] = "";
	    }
	    jqGridTableModel.getRowHeaders().add(emptyrow);
	    
	    // Create a copy of colHeader to avoid modifying the original
	    String[] displayHeader = colHeader.clone();
	    displayHeader[3] = caption;
	    jqGridTableModel.getRowHeaders().add(displayHeader);
	    
	    jqGridTableModel.setTableButton(true);
	    jqGridTableModel.setRowNumbers(true);
	    
	    for (int i = 0; i <= colHeader.length-1; i++) {
	        JqGridColModel jqGridColModel = new JqGridColModel();
	        jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
	        jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
	        
	        jqGridColModel.setWidth(200);
	        jqGridColModel.setAlign("left");
	        jqGridColModel.setEditable(false);
	        
	        // Based on actual column order from console:
	        // 0="0", 1=DISPKEYID, 2=KEYIDFIELD, 3=Code, 4=Flid, 
	        // 5=JH Leader Pending, 6=DMT Leader Pending, 7=PBU Head Pending, 
	        // 8=SBU Head Pending, 9=PLANT Head Pending, 10=FINANCE Head Pending, 
	        // 11=Benefit Amount, 12=Total
	        
	        // Hide: 0 (row number), 1 (DISPKEYID), 2 (KEYIDFIELD), 4 (Flid)
	        if (i == 0 || i == 1 || i == 2 || i == 4) {
	            jqGridColModel.setHidden(true);
	        } 
	        // Show Code column (index 3) with left alignment
	        else if (i == 3) {
	            jqGridColModel.setHidden(false);
	            jqGridColModel.setAlign("left");
	            jqGridColModel.setWidth(120);
	        }
	        // Show all pending columns (5-11) - center aligned
	        else if (i >= 5 && i <= 11) {
	            jqGridColModel.setHidden(false);
	            jqGridColModel.setAlign("center");
	            jqGridColModel.setWidth(90);
	        }
	        // Show Total column (index 12) - center aligned
	        else if (i == 12) {
	            jqGridColModel.setHidden(false);
	            jqGridColModel.setAlign("center");
	            jqGridColModel.setWidth(90);
	        }
	        
	        jqGridTableModel.getColModel().add(jqGridColModel);
	    }
	    
	    JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	    tableModel.set("tableHeight", "67%%");
	    CommonMessage.debugMsg("colmodel:" + tableModel);
	    return tableModel;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			CommonMessage.debugMsg(" In side the if Common filter");
			FilterValues.setPaginationParams(request, commonFilter);
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
		} else {
			CommonMessage.debugMsg(" In side the else Common filter");
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}
}
