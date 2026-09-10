
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.BAL_SapQueueBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BAL_BreakdownService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.WhyWhyAnalysisService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.BAL_BreakdownServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.WOConstants;
import com.akranta.tpm.service.api.BdmServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;

//Created By Suresh.K On Dec 3 2011
public class BAL_BreakdownServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAPQUEUE_LIST_IDENT = null;
	BAL_BreakdownService breakDownService;
	WhyWhyAnalysisService yyService;
	WorkOrderService workOrderService;
	CommonFilterService commonFilterService;
	private BdmServiceApi BdmServiceApi;
	// CommonFilter commonFilter;
	// private String comTxt = null;
	// private String comBdNo = null;
	// private String comEnteredBy = null;

	public BAL_BreakdownServlet() {
		super();
		/*
		 * try { breakDownService = new BreakdownServiceImpl(); yyService = new
		 * WhyWhyAnalysisServiceImpl(); workOrderService = new WorkOrderServiceImpl();
		 * commonFilterService = new CommonFilterServiceImpl(); //commonFilter = new
		 * CommonFilter(); } catch (Exception e) {
		 * CommonFunctions.debugMsg("Constructor : "+e.toString()); }
		 */
		// Constructor
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);

		/*
		 * uri is in this form: /contextName/resourceName, * for example:
		 * /app01a/Product_input.action. * However, in the case of a default context,
		 * the * context name is empty, and uri has this form * /resourceName, e.g.:
		 * /Product_input.action
		 */

		String action = UIUtils.getActionPart(request);
		// ComboFilter comboFilter = new ComboFilter();
//			String createdOn = null;

		try {
			breakDownService = (BAL_BreakdownServiceImpl) UIUtils.getServiceObject(request, "BAL_BreakdownServiceImpl");
			workOrderService = (WorkOrderServiceImpl) UIUtils.getServiceObject(request, "WorkOrderServiceImpl");
			commonFilterService = (CommonFilterServiceImpl) UIUtils.getServiceObject(request,
					"CommonFilterServiceImpl");
			yyService = (WhyWhyAnalysisServiceImpl) UIUtils.getServiceObject(request, "WhyWhyAnalysisServiceImpl");
			breakDownService.BAL_BreakdownServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
					: httpSession.getAttribute("tpmjwttoken")));
			workOrderService.WorkOrderServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
					: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		// http://localhost:8080/perfexitc/whywhyanalysismodify_save.why?whywhyRefDocID=BDM15030024&txtformType=BD&womsKey=BDM15030024&&flid=FNL000001393&flId=FNL000001393&refdocid=BDM15030024&problem=machine%20is%20not%20starting&finalAction=TO%20CHECH%20THE%20SR%20NO.&immediateAction=-&rptdte=24-Mar-2015&rpttime=10:44&countermeasure=test%20counter%20measure&srcLoc=LoadPopUp&srcDiv=divBDWhywhy&navigateNext=false
		if (action.equals("filterXmlBreakdown_view.Bbrdn")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
		} else if (action.equals("Breakdown_view.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_breakdownmaster.jsp");
			rd.forward(request, response);
		} else if (action.equals("Breakdown_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
			if (("01-Jan-1801".contains(commonFilter.getFromMonth()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals("Y")))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-1)).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				CommonFunctions.debugMsg(" In side the get Date from monthggggggg"
						+ CommonFunctions.getFirstDateofMonth(Integer.valueOf(-1)).substring(3, 11) + " 000000 "
						+ CommonFunctions.getDate().substring(3, 11));

				commonFilter.setMonwise("Y");
			} else if (("01-Jan-1801".contains(commonFilter.getFromDate()))
					&& ((commonFilter.getMonwise() == null) || (commonFilter.getMonwise().equals(" ")))) {
				commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(Integer.valueOf(-3)));
				commonFilter.setToDate(CommonFunctions.getDate());
			}
			commonFilter.setIsGetCol("Y");
			List<String[]> bdList = breakDownService.getAllBD(commonFilter);
			// JSONObject bdData =
			// UIUtils.convertToJqGridTableObject(bdList,request,2,0,commonFilter.getTotalRecordCnt());
			// httpSession.setAttribute("bdDataServlet", bdData);
			// String
			// colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel",
			// "colModel");
			httpSession.removeAttribute("BdColModel");
			// httpSession.setAttribute("BdColModel", colModel);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setFormatter("breakdownMstGrid_formater");
			gridColModel.setFormattorFromCol("1");
			gridColModel.setFormattorToCol("1");
			jqGridTableModel.setFormatterIndex(new String[] { "1" });

			gridColModel.setHeaderNum(1);

			String[] colHeader = bdList.get(1);
			String[] colHeaderCond = bdList.get(0);

			// CommonFunctions.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			// headers.add(colHeaderCond);
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("BdColModel", jsonObject);
			out.println(jsonObject);
			// out.println(colModel);
		} else if (action.equals("Breakdown_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", false);
				CommonFunctions.debugMsg("Total Record Count : " + commonFilter.getTotalRecordCnt());
				JSONObject jsonObject = new JSONObject();
				// if(page.equals("1"))
				jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
				// else
				// {
				commonFilter.setIsGetCol("N");
				List<String[]> bdMasterList = breakDownService.getAllBD(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				// }

				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownCommonFilter");
				httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
				/*
				 * Updated 20-Mar-2012 CommonFilter commonFilter = new CommonFilter();
				 * commonFilter= (CommonFilter) httpSession.getAttribute("comFil"); List<String
				 * []> bdMasterList = breakDownService.getAllBD(commonFilter);
				 * CommonFunctions.debugMsg(bdMasterList.size()); JSONObject bdMasterData =
				 * UIUtils.convertToJqGridTableObject(bdMasterList,request,0,0);
				 * CommonFunctions.debugMsg(bdMasterData); out.println(bdMasterData);
				 */
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("BreakdownMultipleResp_input.Bbrdn")) {
			String bdEmrNo = request.getParameter("emrNo");
			String bdmachId = request.getParameter("machId");
			request.setAttribute("bdemrNo", bdEmrNo);
			request.setAttribute("bdmachId", bdmachId);
			// COMMENT AND ADD BY PRIYANKA
			// RequestDispatcher rd =
			// request.getRequestDispatcher("/pages/breakdownMultipleResp.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_breakdownMultipleResp.jsp");
			// END
			rd.forward(request, response);
		} else if (action.equals("BreakdownMultipleResp_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_BdColModel", "empallgrid"));
		}
		/*
		 * else if(action.equals("BreakdownMultipleResp_getData.Bbrdn")) { String
		 * bdEmrNo =request.getParameter("emrNo"); String
		 * bdmachId=request.getParameter("machId"); String bdmsKeyId =
		 * request.getParameter("bdKeyid"); if(!UIUtils.isValidKeyId(bdEmrNo)) {
		 * bdEmrNo=bdmsKeyId; } CommonParams commonParams = new CommonParams();
		 * FilterValues.populateGridParams(request,commonParams);
		 * CommonFunctions.debugMsg("Machine Id"+bdmachId);
		 * commonParams.setFlid(bdmachId); List<String[]> bdList =
		 * breakDownService.getMultipleReposibility(bdEmrNo,commonParams); JSONObject
		 * repeatedBdTblObj =
		 * UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonParams.
		 * getTotalRecordCnt()); response.getWriter().print(repeatedBdTblObj);
		 * 
		 * }
		 */
		// PRIYANKA
		else if (action.equals("BreakdownMultipleResp_getData.Bbrdn")) {
			String bdEmrNo = request.getParameter("emrNo");
			String bdmachId = request.getParameter("machId");
			// added here by priyanka on 15/07/2026
			String bdmsKeyId = request.getParameter("bdKeyid");
			if (!UIUtils.isValidKeyId(bdEmrNo)) {
				bdEmrNo = bdmsKeyId;
			}
			// end
			CommonParams commonParams = new CommonParams();
			FilterValues.populateGridParams(request, commonParams);
			CommonFunctions.debugMsg("Machine Id" + bdmachId);
			commonParams.setFlid(bdmachId);
			List<String[]> bdList = breakDownService.getMultipleReposibility(bdEmrNo, commonParams);
			JSONObject repeatedBdTblObj = UIUtils.convertToJqGridTableObject(bdList, request, 0, 1,
					commonParams.getTotalRecordCnt());
			response.getWriter().print(repeatedBdTblObj);

		} else if (action.equals("filterXmlbrkdown_view.Bbrdn")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
		} else if (action.equals("brkdown_view.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String bdView = request.getParameter("selid");
			httpSession.removeAttribute("BrkDownDownTimeView");
			httpSession.setAttribute("BrkDownDownTimeView", bdView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_breakdownmaster.jsp");
			rd.forward(request, response);

		} else if (action.equals("brkdown_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
			String keyId = request.getParameter("keyId");
			String keyID = request.getParameter("keyID");
			if (UIUtils.isValidKeyId(keyId)) {
				keyId = keyId.substring(0, 10);
				ComboFilter cmbRootCause = new ComboFilter();
				if (UIUtils.isValidKeyId(keyId))
					cmbRootCause.setId(keyId);
				if (cmbRootCause != null)
					commonFilter.setCmbbdRootCause(cmbRootCause);
			}

			if (UIUtils.isValidKeyId(keyID)) {
				keyID = keyID.substring(0, keyID.length() - 4);
				commonFilter.setMainkeyid(keyID);
			}

			String bdView = (String) httpSession.getAttribute("BrkDownDownTimeView");

			if (UIUtils.isValidKeyId(bdView)) {
				if (!(bdView.length() > 8)) {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate("01-" + bdView);
						String lastDay = CommonFunctions.getLastDayOfMonth("27-" + bdView);
						commonFilter.setToDate(lastDay);
					}
				} else {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate(bdView);
						commonFilter.setToDate(bdView);

					}
				}
			}
			commonFilter.setIsGetCol("Y");
			List<String[]> bdList = breakDownService.getAllBD(commonFilter);
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList, request, 0, 1,
					commonFilter.getTotalRecordCnt());

			httpSession.setAttribute("bdDataServlet", bdData);
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
			httpSession.removeAttribute("BdColModel");
			// httpSession.setAttribute("BdColModel", colModel);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setFormatter("breakdownMstGrid_formater");
			gridColModel.setFormattorFromCol("1");
			gridColModel.setFormattorToCol("1");
			jqGridTableModel.setFormatterIndex(new String[] { "1" });

			gridColModel.setHeaderNum(1);

			String[] colHeader = bdList.get(1);
			String[] colHeaderCond = bdList.get(0);

			// CommonFunctions.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			// headers.add(colHeaderCond);
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "78%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("BdColModel", jsonObject);
			out.println(jsonObject);
			// out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel",
			// "colModel"));
		} else if (action.equals("brkdown_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();

				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
				JSONObject jsonObject = new JSONObject();
				// if(page.equals("1"))
				jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
				// else
				// {
				commonFilter.setIsGetCol("N");
				List<String[]> bdMasterList = breakDownService.getAllBD(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				// }

				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownCommonFilter");
				httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("brkdown_getExcel.Bbrdn")) {
			exportToExcel(request, response, "BrkDownCommonFilter", "View");
		} else if (action.equals("BDAnalysisRpt_getExcel.Bbrdn")) {
			exportToExcelBDAnalysisRpt(request, response, "BreakdownAnalysisRpt");
		} else if (action.equals("filterXmlBDViewRpt_input.Bbrdn")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
		}

		else if (action.equals("BDViewRpt_input.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String bdView = request.getParameter("selid");
			httpSession.removeAttribute("BrkDownDownTimeView");
			httpSession.setAttribute("BrkDownDownTimeView", bdView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp");
			rd.forward(request, response);

		} else if (action.equals("BDAnalysisRpt_input.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String bdView = request.getParameter("selid");
			httpSession.removeAttribute("BrkDownDownTimeView");
			httpSession.setAttribute("BrkDownDownTimeView", bdView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownAnalysisRpt.jsp");
			rd.forward(request, response);
		} else if (action.equals("bdpoorskill_input.Bbrdn")) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakDownNew.jsp");
			rd.forward(request, response);
		} else if (action.equals("repeatedBD_input.Bbrdn")) {
			String machineId = request.getParameter("machineId");
			request.setAttribute("machineId", machineId);
			UIUtils.forwardRequest(request, response, "/pages/breakdown/RepeatedBD.jsp");
		} else if (action.equals("repeatedBD_getCol.Bbrdn")) {
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "repeatedBdColModel");
			response.getWriter().print(colModel);
		} else if (action.equals("repeatedBD_getData.Bbrdn")) {

			String mchineId = request.getParameter("machineId");
			CommonParams commonParams = new CommonParams();
			FilterValues.populateGridParams(request, commonParams);
			commonParams.setFlid(mchineId);
			List<String[]> data = breakDownService.getRepeatedBreakDown(commonParams);
			JSONObject repeatedBdTblObj = UIUtils.convertToJqGridTableObject(data, request, 0, 1,
					commonParams.getTotalRecordCnt());
			response.getWriter().print(repeatedBdTblObj);
		} else if (action.equals("BDAnalysisRpt_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownAnalysisRpt", true);
			String bdView = (String) httpSession.getAttribute("BrkDownDownTimeView");
			if (UIUtils.isValidKeyId(bdView)) {
				if (!(bdView.length() > 8)) {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {

						commonFilter.setFromDate("01-" + bdView);
						String lastDay = CommonFunctions.getLastDayOfMonth("27-" + bdView);
						commonFilter.setToDate(lastDay);
					}
				} else {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate(bdView);
						commonFilter.setToDate(bdView);
					}
				}
			}

			List<String[]> bdList = breakDownService.getBDAnalysisRpt(commonFilter);
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList, request, 2, 0,
					commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("bdDataServlet", bdData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = bdList.get(0);
			String[] colHeaderCond = bdList.get(1);

			// CommonFunctions.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			// headers.add(colHeaderCond);
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("BDRptSummaryColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("BDAnalysisRpt_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();

				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownAnalysisRpt", true);
				JSONObject jsonObject = new JSONObject();
				List<String[]> bdMasterList = breakDownService.getBDAnalysisRpt(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownAnalysisRpt");
				httpSession.setAttribute("BrkDownAnalysisRpt", commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("BDViewRpt_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
			String bdView = (String) httpSession.getAttribute("BrkDownDownTimeView");
			if (UIUtils.isValidKeyId(bdView)) {
				if (!(bdView.length() > 8)) {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {

						commonFilter.setFromDate("01-" + bdView);
						String lastDay = CommonFunctions.getLastDayOfMonth("27-" + bdView);
						commonFilter.setToDate(lastDay);
					}
				} else {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate(bdView);
						commonFilter.setToDate(bdView);
					}
				}
			}

			List<String[]> bdList = breakDownService.getAllBD(commonFilter);
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList, request, 2, 0,
					commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("bdDataServlet", bdData);
			/*
			 * String
			 * colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel",
			 * "colModelNew"); httpSession.removeAttribute("BdColModel");
			 * httpSession.setAttribute("BdColModel", colModel);
			 * out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel",
			 * "colModel"));
			 */
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = bdList.get(0);
			String[] colHeaderCond = bdList.get(1);

			// CommonFunctions.debugMsg(" TABLEMODEL "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			// headers.add(colHeaderCond);
			headers.add(colHeader);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("BdColModel", jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("bdpoorskill_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModelNew");
			out.println(colModel);
		} else if (action.equals("BDViewRpt_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();

				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
				JSONObject jsonObject = new JSONObject();
				/*
				 * if(page.equals("1")) jsonObject = (JSONObject)
				 * httpSession.getAttribute("bdDataServlet"); else {
				 */
				List<String[]> bdMasterList = breakDownService.getAllBD(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				// }

				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownCommonFilter");
				httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("bdpoorskill_getData.Bbrdn")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request, "SparesMasterCommonFilter", false);
				PrintWriter out = response.getWriter();
				List<String[]> sprMasterList = breakDownService.getAllBreakdownnew(commonFilter);

				JSONObject sprMasterData = UIUtils.convertToJqGridTableObject(sprMasterList, request, 0, 0,
						commonFilter.getTotalRecordCnt());
				out.println(sprMasterData);

			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("BDViewRpt_getExcel.Bbrdn")) {
			exportToExcel(request, response, "BrkDownCommonFilter", "View");
		}

		else if (action.equals("Breakdown_getExcel.Bbrdn")) {
			exportToExcel(request, response, "BrkDownCommonFilter", "Modification");
		}

		else if (action.equals("filterXmlUPM_view.Bbrdn")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
		} else if (action.equals("UPM_view.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp");
			rd.forward(request, response);
		} else if (action.equals("UPM_getCol.Bbrdn")) {
			String maintMode = request.getParameter("maintMode");

			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
			if (UIUtils.isValidKeyId(maintMode))
				commonFilter.setMaintMode(maintMode);
			CommonFunctions.debugMsg(breakDownService + "BD Service");
			List<String[]> bdList = breakDownService.getAllBD(commonFilter);
			CommonFunctions.debugMsg("Size : " + bdList.size());
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList, request, 0, 1,
					commonFilter.getTotalRecordCnt());

			CommonFunctions.debugMsg(bdData);
			httpSession.setAttribute("bdDataServlet", bdData);
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
			JSONObject colModelObj = JSONObject.fromString(colModel);
			colModelObj.getJSONArray("rowHeaders").getJSONArray(0).put(0, "UD NO");
			httpSession.removeAttribute("BdColModel");
			httpSession.setAttribute("BdColModel", colModel);
			out.println(colModelObj);
		} else if (action.equals("UPM_getData.Bbrdn")) {
			try {
				String maintMode = request.getParameter("maintMode");
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
				CommonFunctions.debugMsg("Total Record Count : " + commonFilter.getTotalRecordCnt());
				JSONObject jsonObject = new JSONObject();
				if (page.equals("1"))
					jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
				else {
					if (UIUtils.isValidKeyId(maintMode))
						commonFilter.setMaintMode(maintMode);
					List<String[]> bdMasterList = breakDownService.getAllBD(commonFilter);
					jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 0, 1,
							commonFilter.getTotalRecordCnt());
				}

				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownCommonFilter");
				httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("filterXmlunplanned_view.Bbrdn")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
		}

		else if (action.equals("unplanned_view.Bbrdn")) {
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String bdView = request.getParameter("selid");
			CommonFunctions.debugMsg("SbdViewbdViewbdViewbdView e : " + bdView);
			httpSession.removeAttribute("BrkDownDownTimeView");
			httpSession.setAttribute("BrkDownDownTimeView", bdView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp");
			rd.forward(request, response);

		} else if (action.equals("unplanned_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			String maintMode = request.getParameter("maintMode");
			CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
			if (UIUtils.isValidKeyId(maintMode))
				commonFilter.setMaintMode(maintMode);

			String bdView = (String) httpSession.getAttribute("BrkDownDownTimeView");

			if (UIUtils.isValidKeyId(bdView)) {
				if (!(bdView.length() > 8)) {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate("01-" + bdView);
						String lastDay = CommonFunctions.getLastDayOfMonth("27-" + bdView);
						CommonFunctions.debugMsg("lastDay :: " + lastDay);
						commonFilter.setToDate(lastDay);
					}
				} else {
					if (Constants.passNullDate.contains(commonFilter.getFromDate())
							&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
						commonFilter.setFromDate(bdView);
						commonFilter.setToDate(bdView);
					}
				}
			}

			List<String[]> bdList = breakDownService.getAllBD(commonFilter);
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList, request, 0, 1,
					commonFilter.getTotalRecordCnt());
			CommonFunctions.debugMsg(bdData);
			httpSession.setAttribute("bdDataServlet", bdData);
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
			httpSession.removeAttribute("BdColModel");
			httpSession.setAttribute("BdColModel", colModel);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
		} else if (action.equals("unplanned_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				String maintMode = request.getParameter("maintMode");
				UIUtils.displayRequestParamsValue(request);
				String page = request.getParameter("page");
				httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request, "BrkDownCommonFilter", true);
				JSONObject jsonObject = new JSONObject();
				if (page.equals("1"))
					jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
				else {
					if (UIUtils.isValidKeyId(maintMode))
						commonFilter.setMaintMode(maintMode);
					List<String[]> bdMasterList = breakDownService.getAllBD(commonFilter);
					jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList, request, 0, 1,
							commonFilter.getTotalRecordCnt());
				}

				out.println(jsonObject);
				commonFilter.setViewClick('N');
				httpSession.removeAttribute("BrkDownCommonFilter");
				httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("downtime_getCol.Bbrdn")) {
			httpSession.setAttribute("factId", request.getParameter("factId"));
			httpSession.setAttribute("fromTime", request.getParameter("fromTime"));
			httpSession.setAttribute("toTime", request.getParameter("toTime"));
			PrintWriter out = response.getWriter();
			CommonFunctions
					.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DownTimeColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.DownTimeColModel", "colModel"));
		} else if (action.equals("downtime_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				List<String> paramValues = new ArrayList<String>();
				paramValues.add((String) httpSession.getAttribute("fromTime"));
				paramValues.add((String) httpSession.getAttribute("toTime"));
				paramValues.add((String) httpSession.getAttribute("factId"));
				List<String[]> downTimeList = breakDownService.getDownTime(paramValues);
				JSONObject downTimeData = UIUtils.convertToJqGridTableObject(downTimeList, request, 0, 0);
				CommonFunctions.debugMsg(downTimeData);
				out.println(downTimeData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("comm_Text.Bbrdn")) {
			String commMode = request.getParameter("mode");
			String formMode = request.getParameter("formMode");
			String workorderNo = request.getParameter("workorderNo");
			String employeeId = request.getParameter("employeeId");
			if (UIUtils.isValidKeyId(employeeId))
				request.setAttribute("employeeId", employeeId);
			if (UIUtils.isValidKeyId(workorderNo))
				request.setAttribute("workorderNo", workorderNo);
			if (UIUtils.isValidKeyId(formMode))
				request.setAttribute("formModeFlag", formMode);
			if (UIUtils.isValidKeyId(commMode))
				request.setAttribute("commMode", commMode);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/communicationtext.jsp");
			rd.forward(request, response);
		} else if (action.equals("comm_getCol.Bbrdn")) {
			httpSession.setAttribute("bdId", request.getParameter("bdId"));
			String formName = request.getParameter("formName");
			PrintWriter out = response.getWriter();
			if (UIUtils.isValidKeyId(formName)) {
				if (formName.equals("WO"))
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "woColModel"));

			} else {
				CommonFunctions
						.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
			}

		} else if (action.equals("comm_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				List<String[]> commList = breakDownService.getCommText((String) httpSession.getAttribute("bdId"));
				JSONObject commData = UIUtils.convertToJqGridTableObject(commList, request, 0, 0);
				CommonFunctions.debugMsg(commData);
				out.println(commData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("comm_save.Bbrdn")) {
			httpSession.setAttribute("comTxt", request.getParameter("comTxt"));
			httpSession.setAttribute("comBy", request.getParameter("comBy"));
			httpSession.setAttribute("comWoNo", request.getParameter("woNo"));
			// comTxt = request.getParameter("comTxt");
			// comBdNo = request.getParameter("comBy");
			// comEnteredBy = request.getParameter("bdNo");
			CommonFunctions.debugMsg("inside CommunicAtion " + request.getParameter("comTxt"));
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			saveCom(request, response, bdFormBean);
		} else if (action.equals("why_why.Bbrdn")) {
			String commMode = request.getParameter("mode");
			String bdKey = request.getParameter("bdKey");
			String formMode = request.getParameter("formMode");
			if (UIUtils.isValidKeyId(commMode))
				request.setAttribute("whywhyMode", commMode);
			if (UIUtils.isValidKeyId(formMode))
				request.setAttribute("formModeFlag", formMode);
			if (UIUtils.isValidKeyId(bdKey)) {
				BAL_BdmTlDtl bdmTlDtl = null;
				if (bdKey.substring(0, 1).equals("U")) {
					PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = breakDownService.selectUPMDetail(bdKey);
					bdmTlDtl = (BAL_BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);
				} else {
					BAL_BdmTlWhywhymst bdmTlWhywhymst = null;
					try {
						bdmTlWhywhymst = breakDownService.selectWhyWhy(bdKey);
					} catch (Exception e) {

					}
					request.setAttribute("bdmTlWhywhymst", bdmTlWhywhymst);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/pages/whywhyform.jsp");
			rd.forward(request, response);
		} else if (action.equals("yy_getCol.Bbrdn")) {
			String wwNo = request.getParameter("wwNo");
			httpSession.removeAttribute("wwNo");
			if (UIUtils.isValidKeyId(wwNo))
				httpSession.setAttribute("wwNo", wwNo);
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "bdYYColModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "bdYYColModel"));

		} else if (action.equals("yy_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				List<String[]> yyList = breakDownService.getYY((String) httpSession.getAttribute("wwNo"));
				JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList, request, 0, 0);
				out.println(yyData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("setrc_values.Bbrdn")) {
			PrintWriter out = response.getWriter();
			List<String[]> rcInBdYYList = yyService.getSelectedRootCause((String) httpSession.getAttribute("wwNo"));

			JSONObject rcJSON = new JSONObject();
			rcJSON.put("rcId", rcInBdYYList.get(0)[0]);
			rcJSON.put("isJH", rcInBdYYList.get(0)[2]);
			rcJSON.put("isPM", rcInBdYYList.get(0)[3]);
			rcJSON.put("isCI", rcInBdYYList.get(0)[4]);
			rcJSON.put("isET", rcInBdYYList.get(0)[5]);
			out.println(rcJSON);
		} else if (action.equals("rootcause_getCol.Bbrdn")) {
			// httpSession.setAttribute("wwNo", request.getParameter("wwNo"));
			PrintWriter out = response.getWriter();
			CommonFunctions
					.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "RootcolModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "RootcolModel"));

		} else if (action.equals("rootcause_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				// List<String []> yyList = WhywhyReportServlet.setRootCauseDatas();
				CommonFunctions.debugMsg("Root Cause YY NO : " + (String) httpSession.getAttribute("wwNo"));
				List<String[]> rootCauseList = breakDownService.getRootCause((String) httpSession.getAttribute("wwNo"));
				// List<String[]> rcInBdYYList = yyService.getSelectedRootCause((String)
				// httpSession.getAttribute("wwNo"));
				// setRootCauseValues(rcInBdYYList,request);
				/*
				 * request.setAttribute("rcId", rcInBdYYList.get(0)[0]);
				 * request.setAttribute("isJH", rcInBdYYList.get(0)[2]);
				 * request.setAttribute("isPM", rcInBdYYList.get(0)[3]);
				 * request.setAttribute("isCI", rcInBdYYList.get(0)[4]);
				 * request.setAttribute("isET", rcInBdYYList.get(0)[5]);
				 * CommonFunctions.debugMsg(rcInBdYYList.get(0)[0]);
				 * CommonFunctions.debugMsg(rcInBdYYList.get(0)[2]);
				 * CommonFunctions.debugMsg(rcInBdYYList.get(0)[3]);
				 * CommonFunctions.debugMsg(rcInBdYYList.get(0)[4]);
				 * CommonFunctions.debugMsg(rcInBdYYList.get(0)[5]);
				 */

				JSONObject rootCauseData = UIUtils.convertToJqGridTableObject(rootCauseList, request, 0, 0);
				CommonFunctions.debugMsg("rootCauseData : " + rootCauseData);
				out.println(rootCauseData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("commtxt_view.Bbrdn")) {
			httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
			request.setAttribute("breakdownId", request.getParameter("bdId"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/communication.jsp");
			rd.forward(request, response);
		} else if (action.equals("commtxt_getCol.Bbrdn")) {

			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));

		} else if (action.equals("commtxt_getData.Bbrdn")) {
			try {
				PrintWriter out = response.getWriter();
				List<String[]> commList = breakDownService
						.getCommText((String) httpSession.getAttribute("breakdownId"));
				JSONObject commData = UIUtils.convertToJqGridTableObject(commList, request, 0, 0);
				out.println(commData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("check_BDclassfcn.Bbrdn")) {
			String pillar = request.getParameter("pillar");
			String classificationId = request.getParameter("classificationId");
			String msg = "";
			PrintWriter out = response.getWriter();
			if (UIUtils.isValidKeyId(pillar)) {
				msg = "Selected BD classification is not related to Pillar which is selected in Why-Why.";
				List<String[]> commList = breakDownService.getPillarClassfcn(pillar);
				for (int i = 0; i < commList.size(); i++) {
					CommonFunctions.debugMsg(i + "...--------..." + commList.get(i)[0]);
					if (classificationId.equals(commList.get(i)[0]))
						msg = "";
				}
				JSONObject validMsg = new JSONObject();
				validMsg.put("msg", msg);
				out.println(validMsg);
			}

		} else if (action.equals("Breakdownpcs_input.Bbrdn")) {

			String Flid = request.getParameter("Flid");
			String cellId = request.getParameter("cellid");
			String shiftId = request.getParameter("shiftId");
			String shifttext = request.getParameter("shifttext");
			String hourno = request.getParameter("hourno");
			String lossdate = request.getParameter("lossdate");
			String availTime = request.getParameter("availtime");
			System.out.println("Shift Id        " + hourno);
			request.setAttribute("flid", Flid);
			request.setAttribute("shiftId", shiftId);
			request.setAttribute("cellId", cellId);
			request.setAttribute("shifttext", shifttext);
			request.setAttribute("hourno", hourno);
			request.setAttribute("lossdate", lossdate);
			request.setAttribute("availTime", availTime);

			String keyid = request.getParameter("BDKeyid");
			String activity = request.getParameter("activity");
			httpSession.removeAttribute(WOConstants.backTo);
			String delActivity = request.getParameter("delActivity");
			if (UIUtils.isValidKeyId(delActivity))
				request.setAttribute("delActivity", "Y");
			FormModes mode = FormModes.create;
			BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
			BAL_BdmTlDtl bdmTlDtl = new BAL_BdmTlDtl();

			UIUtils.displayRequestParamsValue(request);
			response.setContentType("text/html");
			if (UIUtils.isValidKeyId(keyid)) {

				if (UIUtils.isValidKeyId(activity)) {
					CommonFunctions.debugMsg("activity " + activity);
					if (activity.equals("BD") || activity.equals("B")) {
						CommonFunctions.debugMsg("key id in select " + keyid);
						bdmTlMst = breakDownService.select(keyid);
						bdmTlDtl = breakDownService.selectBd(keyid);
						CommonFunctions.debugMsg("Machine Id " + bdmTlMst.getBdmsMachineid());
					}

					httpSession.setAttribute("fromWorkorderDisableFunloc", "true");
				}

				BAL_BDFormBean bdFormBean = new BAL_BDFormBean(mode);

				setBdmMstvalues(bdmTlMst, bdmTlDtl, bdFormBean);

				request.setAttribute("bdmTlMst", bdmTlMst);
				request.setAttribute("bdmTlDtl", bdmTlDtl);
				request.setAttribute("bdFormBean", bdFormBean);
				request.setAttribute("formMode", mode);
				httpSession.removeAttribute("bdmTlMst");
				httpSession.setAttribute("bdmTlMst", bdmTlMst);
			} else {
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				bdmTlMst.setBdmsBookedby(user.getUsrm_ccno());
				bdmTlDtl.setBdanCompletedby(user.getUsrm_ccno());
				request.setAttribute("bdmTlMst", bdmTlMst);
				request.setAttribute("bdmTlDtl", bdmTlDtl);
			}
			request.setAttribute("keyid", keyid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/PcsBreakdown.jsp");
			rd.forward(request, response);
			// UIUtils.forwardRequest(request, response, "/pages/PcsBreakdown.jsp");

		} else if (action.equals("Breakdownpcs_getCol.Bbrdn")) {
			CommonFunctions.debugMsg("in Get Col MMethod ");
			// PrintWriter out = response.getWriter();

			// CommonFilter commonFilter = populateCommonFilter(request,"PcsBdFilter",true);
			try {
				PrintWriter out = response.getWriter();
				String cellid = request.getParameter("cellid");
				CommonFunctions.debugMsg("BdKey Id- Above CommonFilter---------" + cellid);
				CommonFilter commonFilter = populateCommonFilter(request, "BDPcsCommonFilter", true);
				CommonFunctions.debugMsg("CommonFilter- After CommonFilter---------" + commonFilter);

				commonFilter.setKey(cellid);
				CommonFunctions.debugMsg("cell Keyid- After Set---------" + cellid);
				List<String[]> Grid = breakDownService.getBreakDownList(commonFilter);

				JSONObject colModel = getTableModelPcsBd(Grid, commonFilter);
				colModel.set("tableHeight", "40%%");
				colModel.set("tableWidth", "100%%");
				httpSession.setAttribute("ColModel", colModel);
				System.out.println("before");
				out.println(colModel);
				System.out.println("colModelSAPMaintainorder  " + colModel);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("Breakdownpcs_getData.Bbrdn")) {
			CommonFilter commonFilter = populateCommonFilter(request, "BDPcsCommonFilter", true);
			try {
				String cellid = request.getParameter("cellid");
				CommonFunctions.debugMsg("Sap Info Type:" + cellid);
				commonFilter.setKey(cellid);
				List<String[]> PcsBdList = breakDownService.getBreakDownList(commonFilter);
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("get data method1");
				JSONObject pcsbdMaster = UIUtils.convertToJqGridTableObject(PcsBdList, request, 1, 0);
				out.println(pcsbdMaster);
				//

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("Breakdown_input.Bbrdn")) {
			System.out.println("sap Status.....1...");
			String keyid = request.getParameter("BDKeyid");
			String activity = request.getParameter("activity");
			String woKey = request.getParameter("WOID");
			String backTo = request.getParameter(WOConstants.backTo);
			httpSession.removeAttribute(WOConstants.backTo);
			System.out.println(woKey + " sap Status.....1..." + keyid);
			if (UIUtils.isValidKeyId(backTo))
				httpSession.setAttribute(WOConstants.backTo, backTo);

			String delActivity = request.getParameter("delActivity");
			if (UIUtils.isValidKeyId(delActivity))
				request.setAttribute("delActivity", "Y");
			FormModes mode = FormModes.create;
			BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
			BAL_BdmTlDtl bdmTlDtl = new BAL_BdmTlDtl();
			if (UIUtils.isValidKeyId(woKey)) {

				WomTlWomst womTlWomst = workOrderService.select(woKey);
				httpSession.setAttribute("WorkOrderBD", womTlWomst);
				request.setAttribute("WorkOrderBDID", woKey);
			}
			UIUtils.displayRequestParamsValue(request);
			response.setContentType("text/html");
			if (UIUtils.isValidKeyId(keyid)) {
				if (!UIUtils.isValidKeyId(woKey)) {
					// mode=FormModes.view;

				}

				PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();
				PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();

				if (UIUtils.isValidKeyId(activity)) {
					CommonFunctions.debugMsg("activity " + activity);
					if (activity.equals("BD") || activity.equals("B")) {

						String date = bdmTlMst.getBdmsEntrydate();
						bdmTlMst.setBdmsEntrydate(CommonFunctions.pg_getDateTimeFromDate(date));

						String date1 = bdmTlMst.getBdmsProdaccepdate();
						bdmTlMst.setBdmsProdaccepdate(CommonFunctions.pg_getDateTimeFromDate(date1));
						String date2 = bdmTlMst.getBdmsReporteddate();
						bdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDateTimeFromDate(date2));
						String date3 = bdmTlMst.getBdmsCompleteddate();
						bdmTlMst.setBdmsCompleteddate(CommonFunctions.pg_getDateTimeFromDate(date3));
						String date4 = bdmTlMst.getBdmsReporteddate();
						bdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDateTimeFromDate(date4));
						String date5 = bdmTlMst.getBdmsReceiveddate();
						bdmTlMst.setBdmsReceiveddate(CommonFunctions.pg_getDateTimeFromDate(date5));

						bdmTlMst = breakDownService.select(keyid);
						bdmTlDtl = breakDownService.selectBd(keyid);
						CommonFunctions.debugMsg("Machine Id " + bdmTlMst.getBdmsMachineid());
					} else if (activity.equals("U")) {
						plmTlUnplannedmaintmst = breakDownService.selectUPM(keyid);
						plmTlUnplannedmaintdtl = breakDownService.selectUPMDetail(keyid);
						bdmTlMst = (BAL_BdmTlMst) UIUtils.copyObject(plmTlUnplannedmaintmst, bdmTlMst);
						bdmTlDtl = (BAL_BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);

					}

					// bdFormBean.setDisablebdmsMachineid(true);
					// bdFormBean.setDisablebdanCostcentre(true);
					httpSession.setAttribute("fromWorkorderDisableFunloc", "true");
				}
				/*
				 * if( "MLD".equals(bdmTlMst.getBdmsRelatedto()) && !
				 * UIUtils.isValidKeyId(bdmTlMst.getBdmsMould()) ) {
				 * CommonFunctions.debugMsg(" bdFormBean.getDisablebdmsMould() " +
				 * bdFormBean.isDisablebdmsMould() ); //bdFormBean.setDisablebdmsMould(false); }
				 * //else //bdFormBean.setDisablebdmsMould(true);
				 */
				CommonFunctions.debugMsg("sap Status....2....");
				BAL_SapQueueBean sapQueueBean = new BAL_SapQueueBean();
				if ("C".equals(bdmTlMst.getBdmsStatus()) && "C".equals(bdmTlDtl.getBdanErppoststatus())) {

					CommonFunctions.debugMsg("sap Status........" + sapQueueBean.getTxtStatus());

					request.setAttribute("Status", "Completed in SAP");
				} else if ("C".equals(bdmTlMst.getBdmsStatus()) && "X".equals(bdmTlDtl.getBdanErppoststatus())) {
					CommonFunctions.debugMsg("sap Status........" + sapQueueBean.getTxtStatus());

					request.setAttribute("Status", "Completed in Perfex Not In SAP");
				}
				CommonFunctions.debugMsg("sap Status....3....");
				request.setAttribute("erpStatus", bdmTlDtl.getBdanErppoststatus());
				if ("C".equals(bdmTlMst.getBdmsStatus()))
					mode = FormModes.complete;

				BAL_BDFormBean bdFormBean = new BAL_BDFormBean(mode);

				setBdmMstvalues(bdmTlMst, bdmTlDtl, bdFormBean);

				request.setAttribute("bdmTlMst", bdmTlMst);
				request.setAttribute("bdmTlDtl", bdmTlDtl);
				request.setAttribute("bdFormBean", bdFormBean);
				request.setAttribute("formMode", mode);
				/// createdOn = bdmTlMst.getBdmsCreatedon();
				httpSession.removeAttribute("bdmTlMst");
				httpSession.setAttribute("bdmTlMst", bdmTlMst);
			} else {
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				bdmTlMst.setBdmsBookedby(user.getUsrm_ccno());
				bdmTlDtl.setBdanCompletedby(user.getUsrm_ccno());
				request.setAttribute("bdmTlMst", bdmTlMst);
				request.setAttribute("bdmTlDtl", bdmTlDtl);
			}
			request.setAttribute("keyid", keyid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_BreakdownAnalysis.jsp");
			rd.forward(request, response);
		} else if (action.equals("updateTransaction.Bbrdn")) {

			updateErpStatus(request, response);
		}

		/*
		 * else if (action.equals("brkdown_input.Bbrdn")) { String keyid =
		 * request.getParameter("BDKeyid"); String activity =
		 * request.getParameter("activity");
		 * 
		 * FormModes mode = FormModes.create; String woKey =
		 * request.getParameter("WOID"); BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
		 * BAL_BdmTlDtl bdmTlDtl = new BAL_BdmTlDtl(); if(UIUtils.isValidKeyId(woKey)) {
		 * WomTlWomst womTlWomst = workOrderService.select(woKey);
		 * httpSession.setAttribute("WorkOrderBD", womTlWomst); }
		 * //UIUtils.displayRequestParamsValue(request);
		 * //response.setContentType("text/html"); if(UIUtils.isValidKeyId(keyid)) {
		 * 
		 * 
		 * PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();
		 * PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
		 * 
		 * if(UIUtils.isValidKeyId(activity)) { if(activity.equals("B")) { bdmTlMst =
		 * breakDownService.select(keyid); bdmTlDtl = breakDownService.selectBd(keyid);
		 * } else if(activity.equals("U")) { plmTlUnplannedmaintmst =
		 * breakDownService.selectUPM(keyid); plmTlUnplannedmaintdtl =
		 * breakDownService.selectUPMDetail(keyid); bdmTlMst = (BAL_BdmTlMst)
		 * UIUtils.copyObject(plmTlUnplannedmaintmst, bdmTlMst); bdmTlDtl =
		 * (BAL_BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl); } }
		 * BAL_SapQueueBean sapQueueBean =new BAL_SapQueueBean();
		 * if("C".equals(bdmTlMst.getBdmsStatus()) &&
		 * "C".equals(bdmTlDtl.getBdanErppoststatus())){
		 * 
		 * CommonFunctions.debugMsg("sap Status........"+sapQueueBean.getTxtStatus());
		 * 
		 * 
		 * request.setAttribute("Status","Completed in SAP"); } else
		 * if("C".equals(bdmTlMst.getBdmsStatus()) &&
		 * "X".equals(bdmTlDtl.getBdanErppoststatus())) {
		 * CommonFunctions.debugMsg("sap Status........"+sapQueueBean.getTxtStatus());
		 * 
		 * request.setAttribute("Status","Completed in Perfex Not In SAP"); }
		 * CommonFunctions.debugMsg("sap Status....3....");
		 * request.setAttribute("erpStatus", bdmTlDtl.getBdanErppoststatus());
		 * if("C".equals(bdmTlMst.getBdmsStatus())) mode=FormModes.view;
		 * 
		 * BAL_BDFormBean bdFormBean = new BAL_BDFormBean(mode);
		 * 
		 * setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);
		 * 
		 * request.setAttribute("bdmTlMst", bdmTlMst); request.setAttribute("bdmTlDtl",
		 * bdmTlDtl); request.setAttribute("bdFormBean", bdFormBean);
		 * request.setAttribute("formMode", mode); // createdOn =
		 * bdmTlMst.getBdmsCreatedon(); httpSession.removeAttribute("bdmTlMst");
		 * httpSession.setAttribute("bdmTlMst",bdmTlMst); } if(keyid != null) {
		 * BDFormBean bdFormBean = new BDFormBean(FormModes.view); BdmTlMst bdmTlMst =
		 * breakDownService.select(keyid); BdmTlDtl bdmTlDtl =
		 * breakDownService.selectBd(keyid);
		 * setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);
		 * request.setAttribute("bdmTlMst", bdmTlMst); request.setAttribute("bdmTlDtl",
		 * bdmTlDtl); request.setAttribute("bdFormBean", bdFormBean);
		 * request.setAttribute("formMode", "view"); }
		 * 
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/pages/BAL_BreakdownAnalysis.jsp");
		 * rd.forward(request, response); }
		 */
		//mano
		else if (action.equals("brkdown_input.Bbrdn"))
		{
		    String keyid = request.getParameter("BDKeyid");
		    String activity = request.getParameter("activity");

		    // >>> CHANGED: this action is a read-only view of the breakdown,
		    // so force View mode unconditionally instead of deriving it from status.
		    FormModes mode = FormModes.view;
		    String woKey = request.getParameter("WOID");
		    BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
		    BAL_BdmTlDtl bdmTlDtl = new BAL_BdmTlDtl();
		    if(UIUtils.isValidKeyId(woKey))
		    {
		        WomTlWomst womTlWomst = workOrderService.select(woKey);
		        httpSession.setAttribute("WorkOrderBD", womTlWomst);
		    }

		    // >>> ADDED: set formMode unconditionally so the JSP/JS always sees "view",
		    // even in the (unlikely) case keyid turns out invalid for this action.
		    request.setAttribute("formMode", mode);

		    if(UIUtils.isValidKeyId(keyid))
		    {
		        PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();
		        PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();

		        if(UIUtils.isValidKeyId(activity))
		        {
		            if(activity.equals("B"))
		            {
		                bdmTlMst = breakDownService.select(keyid);
		                bdmTlDtl = breakDownService.selectBd(keyid);
		            }
		            else if(activity.equals("U"))
		            {
		                plmTlUnplannedmaintmst = breakDownService.selectUPM(keyid);
		                plmTlUnplannedmaintdtl = breakDownService.selectUPMDetail(keyid);
		                bdmTlMst = (BAL_BdmTlMst) UIUtils.copyObject(plmTlUnplannedmaintmst, bdmTlMst);
		                bdmTlDtl = (BAL_BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);
		            }
		        }
		        BAL_SapQueueBean sapQueueBean = new BAL_SapQueueBean();
		        if("C".equals(bdmTlMst.getBdmsStatus()) && "C".equals(bdmTlDtl.getBdanErppoststatus())){
		            CommonFunctions.debugMsg("sap Status........"+sapQueueBean.getTxtStatus());
		            request.setAttribute("Status","Completed in SAP");
		        }
		        else if("C".equals(bdmTlMst.getBdmsStatus()) && "X".equals(bdmTlDtl.getBdanErppoststatus()))
		        {
		            CommonFunctions.debugMsg("sap Status........"+sapQueueBean.getTxtStatus());
		            request.setAttribute("Status","Completed in Perfex Not In SAP");
		        }
		        CommonFunctions.debugMsg("sap Status....3....");
		        request.setAttribute("erpStatus", bdmTlDtl.getBdanErppoststatus());

		        // >>> REMOVED: no longer deriving mode from status — mode is always view here now
		        // if("C".equals(bdmTlMst.getBdmsStatus()))
		        //     mode=FormModes.view;

		        BAL_BDFormBean bdFormBean = new BAL_BDFormBean(mode);
		        setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);

		        request.setAttribute("bdmTlMst", bdmTlMst);
		        request.setAttribute("bdmTlDtl", bdmTlDtl);
		        request.setAttribute("bdFormBean", bdFormBean);
		        request.setAttribute("formMode", mode); // kept for consistency with rest of block; already set above too
		        httpSession.removeAttribute("bdmTlMst");
		        httpSession.setAttribute("bdmTlMst",bdmTlMst);
		    }

		    RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_BreakdownAnalysis.jsp");
		    rd.forward(request, response);
		}
		else if (action.equals("Breakdown_save.Bbrdn")) {
			CommonFunctions.debugMsg("Saveeeeeee");
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			saveBD(request, response, bdFormBean);

		} else if (action.equals("Breakdownpcs_save.Bbrdn")) {
			CommonFunctions.debugMsg("Saveeeeeee");
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			savePcsBD(request, response, bdFormBean);
		} else if (action.equals("Breakdown_unsap.Bbrdn")) {
			List<BAL_SapQueueBean> sapQueueBeanList = (List<BAL_SapQueueBean>) request.getSession()
					.getAttribute(SAPQUEUE_LIST_IDENT);
			if (sapQueueBeanList == null) {
				sapQueueBeanList = new ArrayList<BAL_SapQueueBean>();
				request.getSession().setAttribute(SAPQUEUE_LIST_IDENT, sapQueueBeanList);
				for (BAL_SapQueueBean sapQueueBean : sapQueueBeanList) {

					String status = sapQueueBean.getTxtStatus();
					String tranId = sapQueueBean.getTxtTransId();
					// sapQueueBean.getBtnAction();
					// sapQueueBean.setTxtMessage("");
					CommonFunctions.debugMsg(status + "    Status.....");
					CommonFunctions.debugMsg(tranId + "    tranId.....");
				}
			}

		} else if (action.equals("Breakdown_delete.Bbrdn")) {
			BDFormBean bdFormBean = new BDFormBean();
			String url = request.getParameter("url");
			System.out.println("..........Url      " + url);
			// PcsTlPcsBd newPcsBd= new PcsTlPcsBd();
			if (url.equals("pcsDel")) {
				System.out.println("..........Url 1     " + url);
				delPcsBD(request, response);
				System.out.println("..........Url 2     " + url);
			} else
				delBD(request, response, bdFormBean);

		}

		else if (action.equals("Breakdown_unphn.Bbrdn")) {
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			bdFormBean.setFormActionMode("undefinedPhn");
			saveBD(request, response, bdFormBean);
		} else if (action.equals("Breakdown_yy.Bbrdn")) {
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			bdFormBean.setFormActionMode("yy");
			// bdFormBean.setFinalAction(request.)finalAction
			saveBD(request, response, bdFormBean);
		} else if (action.equals("functionalLoc.Bbrdn")) {
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			// functLocFieldNameBean.setFactory("cmbbdmsFactoryid");

			functLocFieldNameBean.setSbu("cmbbdmsFactoryid");
			functLocFieldNameBean.setSection("cmbbdmsSectionid");
			functLocFieldNameBean.setCell("cmbbdmsCellid");
			functLocFieldNameBean.setMachine("cmbbdmsMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbbdmsFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(true);

			FormModes formModes = (FormModes) httpSession.getAttribute("formMode");

			String funlocdisable = (String) httpSession.getAttribute("fromWorkorderDisableFunloc");
			if (funlocdisable != null && "true".equals(funlocdisable))
				formModes = FormModes.view;
			if (formModes == FormModes.completion)
				formModes = FormModes.view;

			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);

		} else if (action.equals("costinfo.Bbrdn")) {
			BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
			String repType = request.getParameter("repType");
			CommonFunctions.debugMsg(repType);
			if (repType.equals("Estimate"))
				bdFormBean.setFormActionMode("estimate");
			else
				bdFormBean.setFormActionMode("actual");

			saveBD(request, response, bdFormBean);
		}

		else if (action.equals("combo_shift.Bbrdn")) {
			try {
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> shift = breakDownService.getComboShift("", comboFilter);
				UIUtils.writeComboBox(response, shift, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (action.equals("combo_mould.Bbrdn")) {
			try {
				String mchId = "";
				if (UIUtils.isValidKeyId(request.getParameter("mchId")))
					mchId = request.getParameter("mchId");
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> mould = breakDownService.getMould(mchId, comboFilter);
				UIUtils.writeComboBox(response, mould, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_repeatedbdno.Bbrdn")) {
			try {
				String assmId = request.getParameter("assmId");
				String eqpId = request.getParameter("eqpID");
				String idFlag = request.getParameter("idFlag");
				// String repDate = request.getParameter("repDate");
				// repDate = repDate.substring(0, 11) + " "+repDate.substring(11);
				String condSQL = "";
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> repeatedbdno = breakDownService.getRepeatedbdno(condSQL, assmId, eqpId, idFlag,
						comboFilter);
				UIUtils.writeComboBox(response, repeatedbdno, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_trade.Bbrdn")) {
			try {
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> repeatedbdno = breakDownService.getFinalTrade("", comboFilter);
				UIUtils.writeComboBox(response, repeatedbdno, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_alarm.Bbrdn")) {
			try {
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> repeatedbdno = breakDownService.getAlarm("", comboFilter);
				UIUtils.writeComboBox(response, repeatedbdno, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_failtype.Bbrdn")) {
			try {
				String relatedTo = request.getParameter("relatedTo");
				CommonFunctions.debugMsg("Fail : " + relatedTo);
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> failureType = breakDownService.getFailureType(relatedTo, comboFilter);
				UIUtils.writeComboBox(response, failureType, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_phenomena.Bbrdn")) {
			try {
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				String assmId = request.getParameter("assmId");
				String mchId = request.getParameter("mchId");
				String mldId = request.getParameter("mldId");
				if (UIUtils.isValidKeyId(assmId))
					mchId = mchId + "-" + assmId;
				if (UIUtils.isValidKeyId(mldId))
					mchId = "MLD-" + mldId;
				List<ComboBox> phenomena = breakDownService.getPhenomena(mchId, comboFilter);
				UIUtils.writeComboBox(response, phenomena, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_cause.Bbrdn")) {
			try {
				String condSql = "";
				String phenId = request.getParameter("phenId");
				String assmId = request.getParameter("assmId");
				/*
				 * if (request.getParameter("phenId") != null) condSql =
				 * " AND BCSM_PHENOMENAID  = '" + request.getParameter("phenId") + "' "; else
				 * condSql = "";
				 */
				CommonFunctions.debugMsg("CAuse ComboBox");
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> cause = breakDownService.getCause(condSql, phenId, assmId, comboFilter);
				UIUtils.writeComboBox(response, cause, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_bdclfcn.Bbrdn")) {
			try {
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				String pillar = request.getParameter("pillar");
				String condSql = "";
				if (UIUtils.isValidKeyId(pillar))
					condSql = "AND BCLM_ACTIVE ='Y' AND TPMP_CODE = '" + pillar + "'";
				List<ComboBox> bdClassifcn = breakDownService.getBDClassification(condSql, comboFilter);
				UIUtils.writeComboBox(response, bdClassifcn, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_spare.Bbrdn")) {
			try {
				String factId = request.getParameter("factId");
				CommonFunctions.debugMsg("Factory id in combo_spare.Bbrdn " + factId);
				String sapreId = request.getParameter("spareId");
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> spare = breakDownService.getSpare(factId, comboFilter);
				UIUtils.writeComboBox(response, spare, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_subassmbly.Bbrdn")) {
			try {
				String assmId = request.getParameter("assmId");
				String machineId = request.getParameter("machineId");
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> subAssembly = breakDownService.getSubAssembly(machineId, assmId, comboFilter);
				UIUtils.writeComboBox(response, subAssembly, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("txt_shift.Bbrdn")) {
			try {
				String fromTime = request.getParameter("fromTime");
				ShiftBean shiftBean = new ShiftBean();
				shiftBean.setFactId(request.getParameter("flid"));
				shiftBean.setSectId(request.getParameter("mchId"));
				shiftBean.setCellId(request.getParameter("cellId"));
				shiftBean.setFromTime(fromTime);

				String occDate = request.getParameter("occurreddate");
				String shiftDate = occDate;

				if (UIUtils.isValidKeyId(occDate) && UIUtils.isValidKeyId(fromTime)) {
					if (Integer.parseInt(fromTime.substring(0, 2)) < 7) {
						final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
						DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
						Date occuredDate = dateFormat.parse(occDate);
						Date oneDayBefore = new Date(occuredDate.getTime() - MILLIS_IN_A_DAY);

						shiftDate = dateFormat.format(oneDayBefore);
						CommonFunctions.debugMsg(shiftDate);

					}

				}
				String shift = breakDownService.getShift(shiftBean);
				// String shift =
				// UIUtils.getShift(request.getParameter("factId"),request.getParameter("sectId"),request.getParameter("cellId"),request.getParameter("fromTime"));
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				CommonFunctions.debugMsg(shift + "------------");
				JSONObject shiftObj = new JSONObject();
				shiftObj.put("shift", shift);
				CommonFunctions.debugMsg(shiftDate + " : shiftDate");
				if (UIUtils.isValidKeyId(shiftDate)) {
					shiftObj.put("shiftDate", shiftDate);
					shiftObj.put("ShiftId", request.getParameter("ShiftId"));
					shiftObj.put("ShiftDateId", request.getParameter("ShiftDateID"));
				}
				CommonFunctions.debugMsg(shiftObj);
				out.print(shiftObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// for filling fact sect cell on select machine
		else if (action.equals("machine_fillcombo.Bbrdn")) {
			try {
				List<String[]> eqpautofill = commonFilterService.getMachineHierarchy(request.getParameter("eqpId"));
				UIUtils.writeMachineHirearchy(response, eqpautofill);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("costcenter_fillcombo.Bbrdn")) {
			try {
				List<String[]> costcenterfill = commonFilterService.getCostCenterRelCell(request.getParameter("eqpId"));
				UIUtils.writecostCenterHierarchy(response, costcenterfill);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("sparesReplaced_input.Bbrdn")) {
			String refDocType = request.getParameter("refDocType");
			String machineId = request.getParameter("bdMachine");
			String priority = request.getParameter("priority");
			String refdocId = request.getParameter("woId");
			SapTlMaintenanceOrdermst newSapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
			/*
			 * if(UIUtils.isValidKeyId(refdocId)){
			 * newSapTlMaintenanceOrdermst=breakDownService.getSapSpareInFoData(refdocId);
			 * if( newSapTlMaintenanceOrdermst == null){
			 * CommonFunctions.debugMsg("insede if null value in sap master"); }else{
			 * CommonFunctions.debugMsg("insede else no null value in sap master");
			 * request.setAttribute("sapTlMaintenanceorder",newSapTlMaintenanceOrdermst);
			 * request.setAttribute("existWoId",newSapTlMaintenanceOrdermst.getMomsRefdocid(
			 * )); //
			 * CommonFunctions.debugMsg("estste   "+newSapTlMaintenanceOrdermst.getMomsOpno(
			 * )); }
			 * 
			 * }
			 */

			request.setAttribute("refdocId", refdocId);
			request.setAttribute("refDocType", refDocType);
			request.setAttribute("machineId", machineId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/sapSparesReplaced.jsp");
			rd.forward(request, response);
		} else if (action.equals("sapInfo_input.Bbrdn")) {
			// httpSession.setAttribute("breakdownId", request.getParameter("bdId"));

			// String refdocId = request.getParameter("bdKeyid");
			String spares = request.getParameter("isSpres");
			String refDocType = request.getParameter("refDocType");
			String bdkeyid = request.getParameter("bdkeyId");
			String flid = request.getParameter("flid");
			String priority = request.getParameter("priority");
			String refdocId = request.getParameter("woId");
			String funLoc = request.getParameter("functionalloc");
			String machine = request.getParameter("machine");
			SapTlMaintenanceOrdermst newSapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
			/*
			 * if(UIUtils.isValidKeyId(refdocId)){ request.setAttribute("refdocId",refdocId
			 * );
			 * 
			 * newSapTlMaintenanceOrdermst=breakDownService.getSapSpareInFoData(refdocId);
			 * CommonFunctions.debugMsg("afterr....   " +newSapTlMaintenanceOrdermst); if(
			 * newSapTlMaintenanceOrdermst == null){ CommonFunctions.debugMsg("inside If ");
			 * 
			 * }else{ CommonFunctions.debugMsg("inside else ");
			 * request.setAttribute("sapTlMaintenanceorder",newSapTlMaintenanceOrdermst);
			 * request.setAttribute("existWoId",newSapTlMaintenanceOrdermst.getMomsRefdocid(
			 * )); //
			 * CommonFunctions.debugMsg("estste   "+newSapTlMaintenanceOrdermst.getMomsOpno(
			 * )); } }
			 */
			request.setAttribute("machine", machine);
			request.setAttribute("funLoc", funLoc);
			request.setAttribute("flid", flid);
			request.setAttribute("spares", spares);
			request.setAttribute("refdocId", refdocId);
			request.setAttribute("refDocType", refDocType);
			CommonFunctions.debugMsg(refDocType + ";;;;;;;;;;;;DocType");
			if (refDocType.equals("BDM")) {
				String orderType = "BREAKDOWN MAINTENANCE ORDER-PM01";
				// request.setAttribute("orderType", orderType);
			} else if (refDocType.equals("PM")) {
				String orderType = "BREAKDOWN MAINTENANCE ORDER-PM02";
				// request.setAttribute("orderType", orderType);
			}

			request.setAttribute("bdkeyid", bdkeyid);
			request.setAttribute("priority", priority);
			CommonFunctions.debugMsg("before Dispatch " + spares);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SAPInfoDetails.jsp");
			rd.forward(request, response);
			CommonFunctions.debugMsg("ay Dispatch ");
		} else if (action.equals("sapInfo_view.Bbrdn")) {
		} else if (action.equals("sapInfo_getCol.Bbrdn")) {
			System.out.println("sapInfo_getCol.Bbrdn");
			PrintWriter out = response.getWriter();
			String bdId = request.getParameter("bdId");
			CommonFunctions.debugMsg("BdKey Id- Above CommonFilter---------" + bdId);
			CommonFilter commonFilter = populateCommonFilter(request, "SAPInfoCommonFilter", true);
			CommonFunctions.debugMsg("CommonFilter- After CommonFilter---------" + commonFilter);

			commonFilter.setKey(bdId);
			CommonFunctions.debugMsg("Bd Keyid- After Set---------" + bdId);

			List<String[]> Grid = breakDownService.getSapInfoList(commonFilter);
			// List<String []> KpivReportList = kpivservice.getAllGeneral(commonFilter);
			JSONObject colModel = getTableModelSapInfo(Grid, commonFilter);
			colModel.set("tableHeight", "40%%");
			colModel.set("tableWidth", "120%%");
			httpSession.setAttribute("ColModel", colModel);
			System.out.println("before");
			out.println(colModel);
			System.out.println("colModelSAPMaintainorder  " + colModel);
		} else if (action.equals("sapInfo_getData.Bbrdn")) {
			try {
				System.out.println("c");
				// FilterValues.getCommonFilters(request, commonFilter);
				String bdId = request.getParameter("bdId");
				CommonFunctions.debugMsg("getDataa==bdId" + bdId);
				CommonFilter commonFilter = populateCommonFilter(request, "SAPInfoCommonFilter", true);
				commonFilter.setKey(bdId);
				List<String[]> listData = breakDownService.getSapInfoList(commonFilter);
				CommonFunctions.debugMsg("listData.size" + listData.size());
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("out objvalu" + out);
				JSONObject sopmaster = new JSONObject();
				sopmaster = UIUtils.convertToJqGridTableObject(listData, request, 1, 0);
				out.println(sopmaster);
				// System.out.println(sopmaster );
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("sapOrderType.Bbrdn")) {
			System.out.println("sapOrderType.Bbrdn");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "sapInfoColModel"));
		} else if (action.equals("extServiceDtl_input.Bbrdn")) {
			String formMode = request.getParameter("formMode");
			request.setAttribute("formMode", formMode);
			String extMasterId = request.getParameter("extMasterId");
			String detailServiceKeyid = request.getParameter("extDtlKeyid");
			String detailRepairKeyid = request.getParameter("extRepDtlKeyid");
			request.setAttribute("extMasterId", extMasterId);
			String pages = " ";
			SapExternalServiceDtl newSapExternalServiceDtl = new SapExternalServiceDtl();
			SapExternalRepair newSapExternalRepair = new SapExternalRepair();
			if ("ExtService".equals(formMode)) {
				if (UIUtils.isValidKeyId(detailServiceKeyid)) {
					newSapExternalServiceDtl = breakDownService.getserviceDtlData(detailServiceKeyid);
					request.setAttribute("newSapExternalServiceDtl", newSapExternalServiceDtl);

				}
				httpSession.setAttribute("newSapExternalServiceDtl", newSapExternalServiceDtl);
				pages = "/pages/ExternalServiceBD_detail.jsp";
			} else {
				if (UIUtils.isValidKeyId(detailRepairKeyid)) {
					newSapExternalRepair = breakDownService.getRepairDtlData(detailRepairKeyid);
					request.setAttribute("newSapExternalRepair", newSapExternalRepair);

				}
				httpSession.setAttribute("newSapExternalRepair", newSapExternalRepair);
				pages = "/pages/ExternalRepairBD_detail.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(pages);
			rd.forward(request, response);
		} else if (action.equals("externalService_input.Bbrdn")) {
			// httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
			// request.setAttribute("breakdownId", request.getParameter("bdId"));

			String formMode = request.getParameter("formMode");
			request.removeAttribute("formMode");
			request.setAttribute("formMode", formMode);
			CommonFunctions.debugMsg(formMode + "  formModeEXT  " + request.getAttribute("formMode"));
			String bdKeyid = request.getParameter("bdKeyid");
			request.setAttribute("bdKeyid", bdKeyid);
			String flid = request.getParameter("flid");
			request.setAttribute("bdflid", flid);
			String shift = request.getParameter("shift");
			request.setAttribute("shift", shift);
			String date = request.getParameter("date");
			request.setAttribute("date", date);
			String mchId = request.getParameter("mchId");
			request.setAttribute("mchId", mchId);
			String woId = request.getParameter("woId");
			request.setAttribute("refdocId", woId);
			String extKeyid = request.getParameter("extKeyid");
			SapExternalServiceMst newSapExternalServiceMst = new SapExternalServiceMst();
			if (UIUtils.isValidKeyId(extKeyid)) {

				newSapExternalServiceMst = breakDownService.getExtServiceData(extKeyid);

				request.setAttribute("sapExternalServiceMst", newSapExternalServiceMst);

				CommonFunctions.debugMsg(
						bdKeyid + " sapExternalServiceMst  " + newSapExternalServiceMst.getExtmPlanDeliverytime());

			}
			httpSession.setAttribute("sapExternalServiceMst", newSapExternalServiceMst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ExternalServiceBD.jsp");
			rd.forward(request, response);

		} else if (action.equals("externalService_view.Bbrdn")) {

		} else if (action.equals("externalserviceDtl_Delete.Bbrdn")) {
			String detailKeyid = request.getParameter("ExtdKeyid");
			CommonFunctions.debugMsg("detailKeyid " + detailKeyid);
			String deleteRecord = breakDownService.delteExtDetail(detailKeyid);
			CommonFunctions.debugMsg("deleteRecord " + deleteRecord);
			PrintWriter out = response.getWriter();
			JSONObject successData = new JSONObject();
			successData.put("msg", deleteRecord);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.print(returnData.toString());
		} else if (action.equals("externalRepairDtl_Delete.Bbrdn")) {
			String rpeDetailKeyid = request.getParameter("ExtrKeyid");
			String deleteRecord = breakDownService.delteExtRprDetail(rpeDetailKeyid);
			CommonFunctions.debugMsg("deleteRecord " + deleteRecord);
			PrintWriter out = response.getWriter();
			JSONObject successData = new JSONObject();
			successData.put("msg", deleteRecord);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.print(returnData.toString());
		} else if (action.equals("externalService_getCol.Bbrdn")) {
			System.out.println("hgfhfhf");
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "ExtSerCommonFilter", true);
			String ExtMasterId = request.getParameter("ExtMasterId");
			List<String[]> Grid = breakDownService.getExtServiceList(ExtMasterId);
			// List<String []> KpovReportList = kpovservice.getAllGeneral(commonFilter);
			JSONObject colModel = getTableModelExtSer(Grid, commonFilter);
			colModel.set("tableHeight", "40%%");
			colModel.set("tableWidth", "90%%");
			httpSession.setAttribute("ColModel", colModel);
			out.println(colModel);
		} else if (action.equals("externalService_getData.Bbrdn")) {
			try {
				// FilterValues.getCommonFilters(request, commonFilter);
				CommonFilter commonFilter = populateCommonFilter(request, "ExtSerCommonFilter", false);
				String ExtMasterId = request.getParameter("ExtMasterId");
				List<String[]> Grid = breakDownService.getExtServiceList(ExtMasterId);
				PrintWriter out = response.getWriter();
				JSONObject kpovmaster = UIUtils.convertToJqGridTableObject(Grid, request, 1, 0);
				out.println(kpovmaster);
				System.out.println(kpovmaster);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (action.equals("externalRepair_view.Bbrdn")) {

		} else if (action.equals("externalRepair_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "ExtSubCommonFilter", true);
			String bdId = request.getParameter("bdId");
			List<String[]> Grid = breakDownService.getExtSubList(bdId);
			// List<String []> KpivReportList = kpivservice.getAllGeneral(commonFilter);
			JSONObject colModel = getTableModelExeSub(Grid, commonFilter);
			colModel.set("tableHeight", "40%%");
			colModel.set("tableWidth", "90%%");
			httpSession.setAttribute("ColModel", colModel);
			System.out.println(colModel);
			out.println(colModel);
		}

		else if (action.equals("externalRepair_getData.Bbrdn")) {
			try {
				// FilterValues.getCommonFilters(request, commonFilter);
				CommonFilter commonFilter = populateCommonFilter(request, "ExtSubCommonFilter", true);
				String bdId = request.getParameter("bdId");
				List<String[]> Grid = breakDownService.getExtSubList(bdId);
				PrintWriter out = response.getWriter();

				JSONObject repairData = UIUtils.convertToJqGridTableObject(Grid, request, 1, 0);
				out.println(repairData);
				System.out.println(repairData);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (action.equals("externalRepair_input.Bbrdn")) {
			// httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
			// request.setAttribute("breakdownId", request.getParameter("bdId"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ExternalRepairBD.jsp");
			rd.forward(request, response);

		} else if (action.equals("externalservice_save.Bbrdn")) {
			saveExternalServices(request, response);
		} else if (action.equals("externalserviceDtl_save.Bbrdn")) {
			saveExternalServiceDetail(request, response);
		} else if (action.equals("externalRepairDtl_save.Bbrdn")) {
			saveExternalRepairDetail(request, response);
		} else if (action.equals("externalRepair_view.Bbrdn")) {

		} else if (action.equals("externalRepair_getCol.Bbrdn")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "ExtRepairCommonFilter", true);
			String bdId = request.getParameter("bdId");
			List<String[]> Grid = breakDownService.getExtRepairList(bdId);
			// List<String []> KpovReportList = kpovservice.getAllGeneral(commonFilter);
			JSONObject colModel = getTableModelExtRepair(Grid, commonFilter);
			colModel.set("tableHeight", "40%%");
			colModel.set("tableWidth", "90%%");
			httpSession.setAttribute("ColModel", colModel);

			out.println(colModel);

		} else if (action.equals("externalRepair_getData.Bbrdn")) {
			try {
				// FilterValues.getCommonFilters(request, commonFilter);
				CommonFilter commonFilter = populateCommonFilter(request, "ExtRepairCommonFilter", false);
				String bdId = request.getParameter("bdId");
				List<String[]> Grid = breakDownService.getExtRepairList(bdId);
				PrintWriter out = response.getWriter();
				JSONObject kpovmaster = UIUtils.convertToJqGridTableObject(Grid, request, 1, 0);
				out.println(kpovmaster);
				System.out.println(kpovmaster);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		/*
		 * else if(action.equals("Breakdown_modification.Bbrdn")) { try {
		 * 
		 * BreakdownService breakDownService = new BreakdownServiceImpl();
		 * 
		 * String fromDate = request.getParameter("fromDate"); String toDate =
		 * request.getParameter("toDate");
		 * 
		 * CommonFilter commonFilter = new CommonFilter(); fromDate =
		 * UIUtils.convertToDisplayFormat(fromDate, "MM/dd/yyyy"); toDate =
		 * UIUtils.convertToDisplayFormat(toDate, "MM/dd/yyyy");
		 * 
		 * commonFilter.setFromDate(fromDate); commonFilter.setToDate(toDate);
		 * 
		 * 
		 * List< String[]> breakdownList =
		 * breakDownService.getAllBreakdown(commonFilter);
		 * 
		 * 
		 * JSONObject breakdownTbl = new JSONObject(); JSONObject genMastTbl = new
		 * JSONObject(); org.json.simple.JSONArray headerNames =new
		 * org.json.simple.JSONArray(); org.json.simple.JSONArray colModelName =new
		 * org.json.simple.JSONArray(); org.json.simple.JSONArray colModelIndex =new
		 * org.json.simple.JSONArray(); org.json.simple.JSONArray tableCol_Model =new
		 * org.json.simple.JSONArray(); JSONObject tableColModel =new JSONObject();
		 * 
		 * genMastTbl.put("page", 1); CommonFunctions.debugMsg("Breakdown.size()" +
		 * breakdownList.size()); genMastTbl.put("records", breakdownList.size());
		 * genMastTbl.put("total", breakdownList.size()); org.json.simple.JSONArray rows
		 * = new org.json.simple.JSONArray(); int id=1; for( String [] brk :
		 * breakdownList) {
		 * 
		 * if( id++ > 1) { CommonFunctions.debugMsg(" id " + id);
		 * org.json.simple.JSONObject cellobj=new org.json.simple.JSONObject();
		 * cellobj.put("id",id++); org.json.simple.JSONArray cell=new
		 * org.json.simple.JSONArray(); for(int i=1;i<brk.length;i++) { cell.add(
		 * (brk[i] != null ? brk[i].replace("[","").replace("]",""):"")); }
		 * cellobj.put("cell",cell); rows.add(cellobj); } else{
		 * 
		 * 
		 * headerNames.add("BD No"); tableColModel.put("name","BD No");
		 * tableColModel.put("index","BD No"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Order No"); tableColModel.put("name","Order No");
		 * tableColModel.put("index","Order No"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Shift"); tableColModel.put("name","Shift");
		 * tableColModel.put("index","Shift"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Cell"); tableColModel.put("name","Cell");
		 * tableColModel.put("index","Cell"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Equipment"); tableColModel.put("name","Equipment");
		 * tableColModel.put("index","Equipment");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Problem"); tableColModel.put("name","Problem");
		 * tableColModel.put("index","Problem"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Occured Date"); tableColModel.put("name","Occured Date");
		 * tableColModel.put("index","Occured Date");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Allotted Date"); tableColModel.put("name","Allotted Date");
		 * tableColModel.put("index","Allotted Date");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Work Start Time");
		 * tableColModel.put("name","Work Start Time");
		 * tableColModel.put("index","Work Start Time");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Work End Time"); tableColModel.put("name","Work End Time");
		 * tableColModel.put("index","Work End Time");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Production Strat Date");
		 * tableColModel.put("name","Production Strat Date");
		 * tableColModel.put("index","Production Strat Date");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Down Time"); tableColModel.put("name","Down Time");
		 * tableColModel.put("index","Down Time");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Maint. Section");
		 * tableColModel.put("name","Maint. Section");
		 * tableColModel.put("index","Maint. Section");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Assembly"); tableColModel.put("name","Assembly");
		 * tableColModel.put("index","Assembly"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Phenomena"); tableColModel.put("name","Phenomena");
		 * tableColModel.put("index","Phenomena");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Cause"); tableColModel.put("name","Cause");
		 * tableColModel.put("index","Cause"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Final Action"); tableColModel.put("name","Final Action");
		 * tableColModel.put("index","Final Action");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("YY"); tableColModel.put("name","YY");
		 * tableColModel.put("index","YY"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * 
		 * headerNames.add("Root Cause"); tableColModel.put("name","Root Cause");
		 * tableColModel.put("index","Root Cause");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Counter Measure");
		 * tableColModel.put("name","Counter Measure");
		 * tableColModel.put("index","Counter Measure");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Communication Text");
		 * tableColModel.put("name","Communication Text");
		 * tableColModel.put("index","Communication Text");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Booked By"); tableColModel.put("name","Booked By");
		 * tableColModel.put("index","Booked By");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Analysed By"); tableColModel.put("name","Analysed By");
		 * tableColModel.put("index","Analysed By");
		 * tableColModel.put("editable","false"); tableColModel.put("width","120");
		 * tableCol_Model.add(tableColModel);
		 * 
		 * headerNames.add("Status"); tableColModel.put("name","Status");
		 * tableColModel.put("index","Status"); tableColModel.put("editable","false");
		 * tableColModel.put("width","120"); tableCol_Model.add(tableColModel);
		 * 
		 * }
		 * 
		 * } breakdownTbl.put("colNames", headerNames); breakdownTbl.put("colModel",
		 * tableCol_Model); genMastTbl.put("rows", rows); breakdownTbl.put("colData",
		 * genMastTbl);
		 * 
		 * response.getWriter().println(breakdownTbl); // } }catch(Exception e) {
		 * CommonFunctions.debugMsg(e.getMessage()); } }
		 */

	}

	private JSONObject getTableModel(List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);

		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setRowHeight(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if (i == 0) {// ||(i==1)){
				jqGridColModel.setWidth(150);
			}
			if (i == 5 && i == 6) {// ||(i==1)){
				jqGridColModel.setWidth(250);
			}

			if (i == colHeader.length - 1) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(10);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "100%%");
		return tableModel;
	}

	private void updateErpStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		BAL_BdmTlDtl newBdmTlDtl = new BAL_BdmTlDtl();
		BAL_BdmTlDtl existBdmTlDtl = (BAL_BdmTlDtl) httpSession.getAttribute("newBdmTlDtl");
		String tranid = request.getParameter("tranId");
		String status = request.getParameter("status");
		CommonFunctions.debugMsg("Status  in bd servlet" + status);
		if (httpSession != null && user != null) {
			if (status.equals("Success") || status.equals("false")) {

				newBdmTlDtl.setBdanErppoststatus("C");
			} else
				newBdmTlDtl.setBdanErppoststatus("X");

			newBdmTlDtl.setBdanErpnumber(tranid);
			try {
				existBdmTlDtl = breakDownService.updateErpStatus(newBdmTlDtl, existBdmTlDtl);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		String msg;

		msg = "Data Updated Successfully";

		JSONObject successData = new JSONObject();
		successData.put("msg", msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		out.print(returnData.toString());
	}
	/*
	 * BreakdownService bdService=null;
	 * 
	 * String tranid=request.getParameter("tranId"); String
	 * status=request.getParameter("status"); BdmTlDtl bdmTlDtl = new BdmTlDtl();
	 * //Boolean b = Boolean.valueOf(status);
	 * CommonFunctions.debugMsg(status+"..........status.....in Update");
	 * CommonFunctions.debugMsg(tranid+"..........status.....in Update");
	 * if(status=="true" || status=="Success"){ bdmTlDtl.setBdanErppoststatus("C");
	 * 
	 * } else bdmTlDtl.setBdanErppoststatus("X");
	 * 
	 * bdmTlDtl.setBdanErpnumber(tranid);
	 * 
	 * if(UIUtils.isValidKeyId(tranid)){ bdService.updateErpStatus(status,tranid);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	private void saveExternalRepairDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		SapExternalRepair newSapExternalRepair = new SapExternalRepair();
		SapExternalRepair existSapExternalRepair = (SapExternalRepair) httpSession.getAttribute("newSapExternalRepair");

		if (httpSession != null && user != null) {
			newSapExternalRepair.setExtrCreatedby(user.getUsrm_ccno());
			newSapExternalRepair = (SapExternalRepair) UIUtils.setBeanProperties((Object) newSapExternalRepair,
					request);
			try {
				CommonFunctions.debugMsg("ceatedBY  " + newSapExternalRepair.getExtrCreatedby());
				boolean insert = true;
				BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
				if (newSapExternalRepair.getExtrKeyid() == null) {
					existSapExternalRepair = breakDownService.createExtRepairDtl(newSapExternalRepair,
							existSapExternalRepair, bdFormBean);
				} else {
					insert = false;
					existSapExternalRepair = breakDownService.updateExtRepairDtl(newSapExternalRepair,
							existSapExternalRepair, bdFormBean);
				}
				String msg;
				if (insert)
					msg = "Data Saved Successfully";
				else
					msg = "Data Updated Successfully";

				JSONObject successData = new JSONObject();
				successData.put("msg", msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				out.print(returnData.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void saveExternalServiceDetail(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		SapExternalServiceDtl newSapExternalServiceDtl = new SapExternalServiceDtl();
		SapExternalServiceDtl existSapExternalServiceDtl = (SapExternalServiceDtl) httpSession
				.getAttribute("newSapExternalServiceDtl");

		if (httpSession != null && user != null) {
			newSapExternalServiceDtl.setExtdCreatedby(user.getUsrm_ccno());
			newSapExternalServiceDtl = (SapExternalServiceDtl) UIUtils
					.setBeanProperties((Object) newSapExternalServiceDtl, request);
			try {
				CommonFunctions.debugMsg("ceatedBY  " + newSapExternalServiceDtl.getExtdCreatedby());
				boolean insert = true;
				BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
				if (newSapExternalServiceDtl.getExtdKeyid() == null) {
					existSapExternalServiceDtl = breakDownService.createExtServiceDtl(newSapExternalServiceDtl,
							existSapExternalServiceDtl, bdFormBean);
				} else {
					insert = false;
					existSapExternalServiceDtl = breakDownService.updateExtServiceDtl(newSapExternalServiceDtl,
							existSapExternalServiceDtl, bdFormBean);
				}
				String msg;
				if (insert)
					msg = "Data Saved Successfully";
				else
					msg = "Data Updated Successfully";

				JSONObject successData = new JSONObject();
				successData.put("msg", msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				out.print(returnData.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void saveExternalServices(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		SapExternalServiceMst newSapExternalServiceMst = new SapExternalServiceMst();
		SapExternalServiceMst existSapExternalServiceMst = (SapExternalServiceMst) httpSession
				.getAttribute("sapExternalServiceMst");

		if (httpSession != null && user != null) {
			newSapExternalServiceMst.setExtmCreatedby(user.getUsrm_ccno());
			newSapExternalServiceMst = (SapExternalServiceMst) UIUtils
					.setBeanProperties((Object) newSapExternalServiceMst, request);
			try {
				CommonFunctions.debugMsg("ceatedBY  " + newSapExternalServiceMst.getExtmCreatedby());
				boolean insert = true;
				BAL_BDFormBean bdFormBean = new BAL_BDFormBean();
				if (newSapExternalServiceMst.getExtmKeyid() == null) {
					existSapExternalServiceMst = breakDownService.createExtService(newSapExternalServiceMst,
							existSapExternalServiceMst, bdFormBean);
				} else {
					insert = false;
					existSapExternalServiceMst = breakDownService.updateExtService(newSapExternalServiceMst,
							existSapExternalServiceMst, bdFormBean);
				}
				String msg;
				if (insert)
					msg = "Data Saved Successfully";
				else
					msg = "Data Updated Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg", msg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				returnData.put("extMstKeyid", existSapExternalServiceMst.getExtmKeyid());
				out.print(returnData.toString());

			} catch (Exception e) {
				e.printStackTrace();
				out.print(e.getMessage());
			}

		}
	}

	public void setBdmMstvalues(BAL_BdmTlMst bdmTlMst, BAL_BdmTlDtl bdmTlDtl, BAL_BDFormBean bdFormBean) {
		// if(bdmTlMst.getBdmsBookedphenomena() != null)
		String noDate = "";
		CommonFunctions.debugMsg("PHEN Name : ------------------" + bdmTlDtl.getBdanErppoststatus());
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsBookedphenomena())) {
			String bookedPhen = breakDownService.getPhenType(bdmTlMst.getBdmsBookedphenomena());
			CommonFunctions.debugMsg("PHEN Name : ------------------" + bookedPhen);
			bdFormBean.setBdmsphenName(bookedPhen);
		}
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalphenomena())) {
			String bookedPhen = breakDownService.getPhenType(bdmTlMst.getBdmsFinalphenomena());
			CommonFunctions.debugMsg("PHEN Name : ------------------" + bookedPhen);
			bdFormBean.setBdmsphenName(bookedPhen);
		}
		// if(bdmTlMst.getBdmsFinalphenomena() != null &&
		// !(bdmTlMst.getBdmsFinalphenomena().equals("{}")))
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalphenomena())) {
			bdmTlMst.setBdmsBookedphenomena(bdmTlMst.getBdmsFinalphenomena());
		}
		// if(bdmTlMst.getBdmsFinalcause() != null &&
		// !(bdmTlMst.getBdmsFinalcause().equals("{}")))
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalcause())) {
			bdmTlMst.setBdmsBookedcause(bdmTlMst.getBdmsFinalcause());
		}
		// if(bdmTlMst.getBdmsEntrydate() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsEntrydate()))
			bdmTlMst.setBdmsEntrydate(bdmTlMst.getBdmsEntrydate().substring(0, 11));

		// if(bdmTlMst.getBdmsReporteddate() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsReporteddate())) {
			bdFormBean.setBdmsReportedtime(bdmTlMst.getBdmsReporteddate().substring(12, 17));
			bdmTlMst.setBdmsReporteddate(bdmTlMst.getBdmsReporteddate().substring(0, 11));
		}
		// if(bdmTlMst.getBdmsReceiveddate() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsReceiveddate())) {
			if (bdmTlMst.getBdmsReceiveddate().substring(0, 11).equals(Constants.passNullDate)) {
				bdFormBean.setBdmsReceivedtime(noDate);
				bdmTlMst.setBdmsReceiveddate(noDate);
			} else {
				bdFormBean.setBdmsReceivedtime(bdmTlMst.getBdmsReceiveddate().substring(12, 17));
				bdmTlMst.setBdmsReceiveddate(bdmTlMst.getBdmsReceiveddate().substring(0, 11));
			}
		}
		// if(bdmTlMst.getBdmsWostarttime() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsWostarttime())) {
			if (bdmTlMst.getBdmsWostarttime().substring(0, 11).equals(Constants.passNullDate)) {
				bdFormBean.setBdmsWostart(noDate);
				bdmTlMst.setBdmsWostarttime(noDate);
			} else {
				bdFormBean.setBdmsWostart(bdmTlMst.getBdmsWostarttime().substring(12, 17));
				bdmTlMst.setBdmsWostarttime(bdmTlMst.getBdmsWostarttime().substring(0, 11));
			}
		}
		// if(bdmTlMst.getBdmsWoendtime() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendtime())) {
			if (bdmTlMst.getBdmsWoendtime().substring(0, 11).equals(Constants.futureNullDate)) {
				bdFormBean.setBdmsWoend(noDate);
				bdmTlMst.setBdmsWoendtime(noDate);
			} else {
				bdFormBean.setBdmsWoend(bdmTlMst.getBdmsWoendtime().substring(12, 17));
				bdmTlMst.setBdmsWoendtime(bdmTlMst.getBdmsWoendtime().substring(0, 11));
			}
		}
		// if(bdmTlMst.getBdmsProdaccepdate() != null)
		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsProdaccepdate())) {
			CommonFunctions.debugMsg(bdmTlMst.getBdmsProdaccepdate() + "get produc acceptance");
			if (bdmTlMst.getBdmsProdaccepdate().substring(0, 11).equals(Constants.futureNullDate)) {
				bdFormBean.setBdmsProdacceptime(noDate);
				bdmTlMst.setBdmsProdaccepdate(noDate);
			} else if (bdmTlMst.getBdmsProdaccepdate().substring(0, 11).equals(Constants.passNullDate)) {
				bdFormBean.setBdmsProdacceptime(noDate);
				bdmTlMst.setBdmsProdaccepdate(noDate);
			} else {
				bdFormBean.setBdmsProdacceptime(bdmTlMst.getBdmsProdaccepdate().substring(12, 17));
				bdmTlMst.setBdmsProdaccepdate(bdmTlMst.getBdmsProdaccepdate().substring(0, 11));
			}
		}

		if (UIUtils.isValidKeyId(bdmTlMst.getBdmsCompleteddate())) {
			if (bdmTlMst.getBdmsCompleteddate().substring(0, 11).equals(Constants.futureNullDate)) {
				bdFormBean.setBdmsCompletedtime(noDate);
				bdmTlMst.setBdmsCompleteddate(noDate);
			} else if (bdmTlMst.getBdmsCompleteddate().substring(0, 11).equals(Constants.passNullDate)) {
				bdFormBean.setBdmsCompletedtime(noDate);
				bdmTlMst.setBdmsCompleteddate(noDate);
			} else {
				bdFormBean.setBdmsCompletedtime(bdmTlMst.getBdmsCompleteddate().substring(12, 17));
				bdmTlMst.setBdmsCompleteddate(bdmTlMst.getBdmsCompleteddate().substring(0, 11));
			}
		}

		// if(bdmTlDtl.getBdanErppoststatus() != null)
		if (UIUtils.isValidKeyId(bdmTlDtl.getBdanErppoststatus())) {
			bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("C", "Completed"));
			bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("W", "Work In Progress"));
			bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("B", "Booked"));
			bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("A", "Allotted"));
			bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("X", "Booking"));
		}

	}

	public void setRootCauseValues(List<String[]> rootCause, HttpServletRequest request) {
		CommonFunctions.debugMsg("Size of RC List : " + rootCause.size());
		for (String[] s : rootCause) {
			CommonFunctions.debugMsg(s[0]);
			CommonFunctions.debugMsg(s[2]);
			CommonFunctions.debugMsg(s[3]);
			CommonFunctions.debugMsg(s[4]);
			CommonFunctions.debugMsg(s[5]);
			request.setAttribute("rcId", s[0]);
			request.setAttribute("isJH", s[2]);
			request.setAttribute("isPM", s[3]);
			request.setAttribute("isCI", s[4]);
			request.setAttribute("isET", s[5]);
		}

	}

	private void savePcsBD(HttpServletRequest request, HttpServletResponse response, BAL_BDFormBean bdFormBean)
			throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_BdmTlMst existBdmTlMst = (BAL_BdmTlMst) httpSession.getAttribute("bdmTlMst");
			// CommonFunctions.debugMsg("Exist Datas : "+existBdmTlMst.getBdmsKeyid());

			BAL_PcsTlPcsBd newPcsBd = new BAL_PcsTlPcsBd();
			BAL_BdmTlMst newBdmTlMst = new BAL_BdmTlMst();
			WomTlWomst womTlwomst = new WomTlWomst();
			BAL_BdmTlDtl newBdmTlDtl = new BAL_BdmTlDtl();
			WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderBD");
			newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());
			newBdmTlMst.setBdmsBookedby(user.getUsrm_ccno());
			womTlwomst.setWomsReportedby(user.getUsrm_ccno());

			String bdRelatedTo = request.getParameter("relatedToCMB");
			newBdmTlMst = (BAL_BdmTlMst) UIUtils.setBeanProperties((Object) newBdmTlMst, request);
			if (UIUtils.isValidKeyId(bdRelatedTo))
				newBdmTlMst.setBdmsRelatedto(bdRelatedTo);
			newBdmTlDtl = (BAL_BdmTlDtl) UIUtils.setBeanProperties((Object) newBdmTlDtl, request);
			newBdmTlMst.setchkOtherPhenomena(request.getParameter("chkOtherPhenomena"));
			newBdmTlDtl.setchkFailure(request.getParameter("chkFailure"));
			if (request.getParameter("DwnTmeBrkupGrid") == null || request.getParameter("DwnTmeBrkupGrid").equals("")) {
				// CommonFunctions.debugMsg("Down Time Break Up Grid :
				// "+request.getParameter("DwnTmeBrkupGrid"));
			} else {
				String dwnTimeGrid = request.getParameter("DwnTmeBrkupGrid");
				JSONArray jsonArray = JSONArray.fromString(dwnTimeGrid);
				BAL_BdmTlShiftwisesplit bdmTlShiftwisesplit = new BAL_BdmTlShiftwisesplit();
				List<BAL_BdmTlShiftwisesplit> shiftWise = (List<BAL_BdmTlShiftwisesplit>) UIUtils
						.convertJSONArrToList(bdmTlShiftwisesplit, jsonArray);
				if (bdmTlShiftwisesplit != null)
					newBdmTlMst.setBdmShiftwise(shiftWise);
			}

			if (request.getParameter("hdnMultiresp") == null || request.getParameter("hdnMultiresp").equals("")) {
				// CommonFunctions.debugMsg("Down Time Break Up Grid :
				// "+request.getParameter("DwnTmeBrkupGrid"));
			} else {

				String multiresp = request.getParameter("hdnMultiresp");
				JSONArray jsonArray = JSONArray.fromString(multiresp);
				BAL_BdmTlMultipleResp bdmTlMultipleResp = new BAL_BdmTlMultipleResp();
				List<BAL_BdmTlMultipleResp> MultiResponse = (List<BAL_BdmTlMultipleResp>) UIUtils
						.convertJSONArrToList(bdmTlMultipleResp, jsonArray);
				if (bdmTlMultipleResp != null)
					newBdmTlMst.setbdmTlMultipleResp(MultiResponse);
			}
			if (newPcsBd != null) {
				newPcsBd.setCreatedBy(user.getUsrm_ccno());

				newBdmTlMst.getPcsbd().add(newPcsBd);
			}
			if (newBdmTlDtl != null) {
				newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());
				newBdmTlMst.getBdmDetail().add(newBdmTlDtl);
			}
			bdFormBean = (BAL_BDFormBean) UIUtils.setBeanProperties((Object) bdFormBean, request);

			try {
				boolean insert = true;
				if (newBdmTlMst.getBdmsKeyid() == null || newBdmTlMst.getBdmsKeyid().substring(0, 3).equals("SFT")) {
					existBdmTlMst = breakDownService.createpcsBd(newBdmTlMst, existBdmTlMst, bdFormBean);
				} else {
					insert = false;
					existBdmTlMst = breakDownService.updatepcsBd(newBdmTlMst, existBdmTlMst, bdFormBean, womTlWomst);
				}

				httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
				httpSession.setAttribute("BdmTlMst", existBdmTlMst);

				String formBeanIdentifier = "BDFormBean" + bdFormBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, bdFormBean);

				JSONObject persistentData = new JSONObject();
				persistentData.put("BDKeyid", existBdmTlMst.getBdmsKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				if (bdFormBean.getFormActionMode() != null) {
					if (bdFormBean.getFormActionMode().equals("yy")) {
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsKeyid())) {
							forwardData.put("cmbwwmsRefdocno", existBdmTlMst.getBdmsKeyid());
							// forwardData.put("whywhyRefDocID",existBdmTlMst.getBdmsKeyid());
						}
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
							forwardData.put("dtewwmsDate", existBdmTlMst.getBdmsEntrydate());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
							forwardData.put("cmbwwmsMachineid", existBdmTlMst.getBdmsMachineid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
							forwardData.put("cmbwwmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFactoryid()))
							forwardData.put("cmbwwmsFactoryid", existBdmTlMst.getBdmsFactoryid());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
							forwardData.put("cmbwwmsCellid", existBdmTlMst.getBdmsCellid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
							forwardData.put("cmbwwmsSectionid", existBdmTlMst.getBdmsSectionid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
							forwardData.put("cmbwwmsPhenomenaid", existBdmTlMst.getBdmsFinalphenomena());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
							forwardData.put("cmbwwmsCauseid", existBdmTlMst.getBdmsFinalcause());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsBookedby()))
							forwardData.put("cmbwwmsMaintinchargeid", existBdmTlMst.getBdmsBookedby());

						if (existBdmTlMst.getBdmDetail() != null && existBdmTlMst.getBdmDetail().size() > 0) // check
																												// for
																												// detail
																												// table
																												// data
						{
							BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl) existBdmTlMst.getBdmDetail().get(0);
							forwardData.put("chkwwmsSparesreplaced", bdmDetail.getBdanIssparesreplaced());

							if (UIUtils.isValidKeyId(bdmDetail.getBdanFinalaction()))
								forwardData.put("txtwwmsFinalaction", bdmDetail.getBdanFinalaction());
							if (UIUtils.isValidKeyId(bdmDetail.getBdanWwno())) {
								forwardData.put("txtwwmsKeyid", bdmDetail.getBdanWwno());

							}
						}

						forwardData.put("txtformType", "BD");
						persistentData.put("OpenTab", "OpenYY");
					} else if (bdFormBean.getFormActionMode().equals("estimate")
							|| bdFormBean.getFormActionMode().equals("actual")) {

						if (bdFormBean.getFormActionMode().equals("estimate"))
							forwardData.put("formType", "Estimate");
						else
							forwardData.put("formType", "Actual");

						forwardData.put("DocType", "BDM");
						forwardData.put("DocNo", existBdmTlMst.getBdmsWno());
						forwardData.put("FactoryId", existBdmTlMst.getBdmsFactoryid());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
							forwardData.put("SectionId", existBdmTlMst.getBdmsSectionid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
							forwardData.put("CellId", existBdmTlMst.getBdmsCellid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
							forwardData.put("MachineId", existBdmTlMst.getBdmsMachineid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
							forwardData.put("AssemblyId", existBdmTlMst.getBdmsAssemblyid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
							forwardData.put("PhenomenaId", existBdmTlMst.getBdmsFinalphenomena());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
							forwardData.put("CauseId", existBdmTlMst.getBdmsFinalcause());
						else
							forwardData.put("CauseId", "");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinaltrade()))
							forwardData.put("TradeId", existBdmTlMst.getBdmsFinaltrade());
						else
							forwardData.put("TradeId", "");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsProblemdescription()))
							forwardData.put("Problem", existBdmTlMst.getBdmsProblemdescription());
						else
							forwardData.put("Problem", ".");

						if (existBdmTlMst.getBdmDetail() != null && existBdmTlMst.getBdmDetail().size() > 0) // check
																												// for
																												// detail
																												// table
																												// data
						{
							BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl) existBdmTlMst.getBdmDetail().get(0);
							if (UIUtils.isValidKeyId(bdmDetail.getBdanCountermeasure()))
								forwardData.put("Measure", bdmDetail.getBdanCountermeasure());
							else
								forwardData.put("Measure", ".");
						}
						if (forwardData.get("Measure").equals(null))
							forwardData.put("Measure", ".");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime()))
							forwardData.put("StartTime", existBdmTlMst.getBdmsWostarttime().replace(" ", "-"));

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime()))
							forwardData.put("EndTime", existBdmTlMst.getBdmsWoendtime().replace(" ", "-"));
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsDowntime()))
							forwardData.put("DownTime", existBdmTlMst.getBdmsDowntime());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
							forwardData.put("EntryDate", existBdmTlMst.getBdmsEntrydate());
					} else {
						CommonFunctions.debugMsg("ELSE");
						forwardData.put("cmbbdmsKeyid", existBdmTlMst.getBdmsKeyid());
						forwardData.put("cmbbdmsFactoryid", existBdmTlMst.getBdmsFactoryid());
						forwardData.put("cmbbdmsCellid", existBdmTlMst.getBdmsCellid());
						forwardData.put("cmbbdmsSectionid", existBdmTlMst.getBdmsSectionid());
						forwardData.put("cmbbdmsMachineid", existBdmTlMst.getBdmsMachineid());
						forwardData.put("cmbbdmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
						forwardData.put("spnbdmsWostart", existBdmTlMst.getBdmsWostarttime().substring(12));
						forwardData.put("spnbdmsWoend", existBdmTlMst.getBdmsWoendtime().substring(12));
						forwardData.put("cmbdownTimeMins", existBdmTlMst.getBdmsDowntime());
						forwardData.put("txtbdmsEntrydate", existBdmTlMst.getBdmsEntrydate());

					}
				} else {
					CommonFunctions.debugMsg("ELSE when null");
					forwardData.put("cmbbdmsKeyid", existBdmTlMst.getBdmsKeyid());
					forwardData.put("cmbbdmsFactoryid", existBdmTlMst.getBdmsFactoryid());
					forwardData.put("cmbbdmsCellid", existBdmTlMst.getBdmsCellid());
					forwardData.put("cmbbdmsSectionid", existBdmTlMst.getBdmsSectionid());
					forwardData.put("cmbbdmsMachineid", existBdmTlMst.getBdmsMachineid());
					forwardData.put("cmbbdmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
					CommonFunctions.debugMsg(existBdmTlMst.getBdmsWostarttime().length());
					CommonFunctions.debugMsg(existBdmTlMst.getBdmsWoendtime().length());
					if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime())) {
						if (existBdmTlMst.getBdmsWostarttime().length() > 11) {
							if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime().substring(12)))
								forwardData.put("spnbdmsWostart", existBdmTlMst.getBdmsWostarttime().substring(12));
						}
					}
					if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime())) {
						if (existBdmTlMst.getBdmsWoendtime().length() > 11) {
							if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime().substring(12)))
								forwardData.put("spnbdmsWoend", existBdmTlMst.getBdmsWoendtime().substring(12));
						}
					}
					forwardData.put("cmbdownTimeMins", existBdmTlMst.getBdmsDowntime());
					forwardData.put("txtbdmsEntrydate", existBdmTlMst.getBdmsEntrydate());
				}

				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;

				if (insert) {
					msgPropertyIdnt = "success-save";
				} else
					msgPropertyIdnt = "success-update";

				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
				successData.put("mode", bdFormBean.getFormMode());
				successData.put("keyId", existBdmTlMst.getBdmsKeyid());
				successData.put("womsKeyid", existBdmTlMst.getBdmsWno());
				httpSession.setAttribute("WorkOrderBD", existBdmTlMst.getWomTlWomst());

				returnData.put("forwardData", forwardData);
				returnData.put("persistentData", persistentData);
				// CommonFunctions.debugMsg("Message :
				// "+!bdFormBean.getFormActionMode().equals("yy"));
				if (UIUtils.isValidKeyId(bdFormBean.getFormActionMode())) {
					returnData.put("formMode", bdFormBean.getFormActionMode());
					if (bdFormBean.getFormActionMode().equals("yy") || bdFormBean.getFormActionMode().equals("estimate")
							|| bdFormBean.getFormActionMode().equals("actual")
							|| bdFormBean.getFormActionMode().equals("undefinedPhn"))
						returnData.put("displyMsg", false);
				}
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
				if (UIUtils.isValidKeyId(backTo))
					returnData.put(WOConstants.backTo, backTo);
				CommonFunctions.debugMsg(returnData.toString());
				out.print(returnData.toString());

			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

	private void delPcsBD(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		System.out.println("in side del pcs");
		System.out.println("..........Url _2     ");
		if (httpSession != null && user != null) {
			BAL_BdmTlMst existBdmTlMst = (BAL_BdmTlMst) httpSession.getAttribute("newSession");

			BAL_BdmTlMst newBdmTlMst = new BAL_BdmTlMst();
			BAL_BdmTlDtl newBdmTlDtl = new BAL_BdmTlDtl();
			BDFormBean bdFormBean = new BDFormBean();

			newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());
			newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());

			newBdmTlMst = (BAL_BdmTlMst) UIUtils.setBeanProperties((Object) newBdmTlMst, request);
			newBdmTlDtl = (BAL_BdmTlDtl) UIUtils.setBeanProperties((Object) newBdmTlDtl, request);
			newBdmTlMst.getBdmDetail().add(newBdmTlDtl);

			bdFormBean = (BDFormBean) UIUtils.setBeanProperties((Object) bdFormBean, request);

			try {

				if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag())) {
					if (newBdmTlMst.getBdmsWoprodaccepflag().equals("Y")) {
						throw new BusinessApplicationExceptions("prodAccept,");
					}
				}
				if (UIUtils.isValidKeyId(bdFormBean.getBdmsReportedtime()))
					newBdmTlMst.setBdmsReporteddate(
							newBdmTlMst.getBdmsReporteddate() + " " + bdFormBean.getBdmsReportedtime());

				existBdmTlMst = breakDownService.deletePcsbd(newBdmTlMst);

				httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
				httpSession.setAttribute("BdmTlMst", existBdmTlMst);
				String formBeanIdentifier = "BDForrmBean" + bdFormBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, bdFormBean);

				JSONObject successData = new JSONObject();
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
				successData.put("mode", bdFormBean.getFormMode());
				successData.put("keyId", existBdmTlMst.getBdmsKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);

				out.print(returnData.toString());

			} catch (BusinessApplicationExceptions e) {
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "BdCreationException");
				out.print(errMessage.toString());

			} catch (Exception e) {
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Refference Is in use");
				out.print(err.toString());
			}
		}
	}

	private void saveBD(HttpServletRequest request, HttpServletResponse response, BAL_BDFormBean bdFormBean)
			throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_BdmTlMst existBdmTlMst = (BAL_BdmTlMst) httpSession.getAttribute("bdmTlMst");
			// CommonFunctions.debugMsg("Exist Datas : "+existBdmTlMst.getBdmsKeyid());
			BAL_BdmTlMst newBdmTlMst = new BAL_BdmTlMst();
			BAL_BdmTlDtl newBdmTlDtl = new BAL_BdmTlDtl();
			WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderBD");
			newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());

			String bdRelatedTo = request.getParameter("relatedToCMB");
			newBdmTlMst = (BAL_BdmTlMst) UIUtils.setBeanProperties((Object) newBdmTlMst, request);
			if (UIUtils.isValidKeyId(bdRelatedTo))
				newBdmTlMst.setBdmsRelatedto(bdRelatedTo);
			newBdmTlDtl = (BAL_BdmTlDtl) UIUtils.setBeanProperties((Object) newBdmTlDtl, request);
			newBdmTlMst.setchkOtherPhenomena(request.getParameter("chkOtherPhenomena"));
			newBdmTlDtl.setchkFailure(request.getParameter("chkFailure"));
			/*
			 * if(request.getParameter("DwnTmeBrkupGrid")== null ||
			 * request.getParameter("DwnTmeBrkupGrid").equals("")) {
			 * //CommonFunctions.debugMsg("Down Time Break Up Grid : "+request.getParameter(
			 * "DwnTmeBrkupGrid")); } else { String dwnTimeGrid =
			 * request.getParameter("DwnTmeBrkupGrid"); JSONArray jsonArray =
			 * JSONArray.fromString(dwnTimeGrid); BdmTlShiftwisesplit bdmTlShiftwisesplit =
			 * new BdmTlShiftwisesplit(); List<BdmTlShiftwisesplit> shiftWise =
			 * (List<BdmTlShiftwisesplit>) UIUtils.convertJSONArrToList(bdmTlShiftwisesplit,
			 * jsonArray); if( bdmTlShiftwisesplit != null)
			 * newBdmTlMst.setBdmShiftwise(shiftWise); }
			 */

			if (request.getParameter("hdnMultiresp") == null || request.getParameter("hdnMultiresp").equals("")) {
				// CommonFunctions.debugMsg("Down Time Break Up Grid :
				// "+request.getParameter("DwnTmeBrkupGrid"));
			} else {

				String multiresp = request.getParameter("hdnMultiresp");
				JSONArray jsonArray = JSONArray.fromString(multiresp);
				BAL_BdmTlMultipleResp bdmTlMultipleResp = new BAL_BdmTlMultipleResp();
				List<BAL_BdmTlMultipleResp> MultiResponse = (List<BAL_BdmTlMultipleResp>) UIUtils
						.convertJSONArrToList(bdmTlMultipleResp, jsonArray);
				if (bdmTlMultipleResp != null)
					newBdmTlMst.setbdmTlMultipleResp(MultiResponse);
			}

			if (newBdmTlDtl != null) {
				newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());
				newBdmTlMst.getBdmDetail().add(newBdmTlDtl);
			}
			/*
			 * CommonFunctions.debugMsg("BEFORE: " + bdFormBean.getFormActionMode());
			 * bdFormBean =(BAL_BDFormBean)
			 * UIUtils.setBeanProperties((Object)bdFormBean,request);
			 * CommonFunctions.debugMsg("AFTER: " + bdFormBean.getFormActionMode());
			 */
			String preservedFormActionMode = bdFormBean.getFormActionMode();
			bdFormBean = (BAL_BDFormBean) UIUtils.setBeanProperties((Object) bdFormBean, request);
			if (UIUtils.isValidKeyId(preservedFormActionMode))
				bdFormBean.setFormActionMode(preservedFormActionMode);
			try {
				boolean insert = true;
				if (newBdmTlMst.getBdmsKeyid() == null || newBdmTlMst.getBdmsKeyid().substring(0, 3).equals("SF")) {
					existBdmTlMst = breakDownService.create(newBdmTlMst, existBdmTlMst, bdFormBean);
				} else {
					insert = false;
					existBdmTlMst = breakDownService.update(newBdmTlMst, existBdmTlMst, bdFormBean, womTlWomst);
				}

				httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
				httpSession.setAttribute("BdmTlMst", existBdmTlMst);

				String formBeanIdentifier = "BDFormBean" + bdFormBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, bdFormBean);

				JSONObject persistentData = new JSONObject();
				persistentData.put("BDKeyid", existBdmTlMst.getBdmsKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				if (bdFormBean.getFormActionMode() != null) {
					if (bdFormBean.getFormActionMode().equals("yy")) {
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsKeyid())) {
							forwardData.put("cmbwwmsRefdocno", existBdmTlMst.getBdmsKeyid());
							// forwardData.put("whywhyRefDocID",existBdmTlMst.getBdmsKeyid());
						}
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
							forwardData.put("dtewwmsDate", existBdmTlMst.getBdmsEntrydate());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
							forwardData.put("cmbwwmsMachineid", existBdmTlMst.getBdmsMachineid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
							forwardData.put("cmbwwmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFactoryid()))
							forwardData.put("cmbwwmsFactoryid", existBdmTlMst.getBdmsFactoryid());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
							forwardData.put("cmbwwmsCellid", existBdmTlMst.getBdmsCellid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
							forwardData.put("cmbwwmsSectionid", existBdmTlMst.getBdmsSectionid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
							forwardData.put("cmbwwmsPhenomenaid", existBdmTlMst.getBdmsFinalphenomena());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
							forwardData.put("cmbwwmsCauseid", existBdmTlMst.getBdmsFinalcause());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsBookedby()))
							forwardData.put("cmbwwmsMaintinchargeid", existBdmTlMst.getBdmsBookedby());

						if (existBdmTlMst.getBdmDetail() != null && existBdmTlMst.getBdmDetail().size() > 0) // check
																												// for
																												// detail
																												// table
																												// data
						{
							BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl) existBdmTlMst.getBdmDetail().get(0);
							forwardData.put("chkwwmsSparesreplaced", bdmDetail.getBdanIssparesreplaced());

							if (UIUtils.isValidKeyId(bdmDetail.getBdanFinalaction()))
								forwardData.put("txtwwmsFinalaction", bdmDetail.getBdanFinalaction());
							if (UIUtils.isValidKeyId(bdmDetail.getBdanWwno())) {
								forwardData.put("txtwwmsKeyid", bdmDetail.getBdanWwno());

							}
						}

						forwardData.put("txtformType", "BD");
						persistentData.put("OpenTab", "OpenYY");
					} else if (bdFormBean.getFormActionMode().equals("estimate")
							|| bdFormBean.getFormActionMode().equals("actual")) {

						if (bdFormBean.getFormActionMode().equals("estimate"))
							forwardData.put("formType", "Estimate");
						else
							forwardData.put("formType", "Actual");

						forwardData.put("DocType", "BDM");
						forwardData.put("DocNo", existBdmTlMst.getBdmsWno());
						forwardData.put("FactoryId", existBdmTlMst.getBdmsFactoryid());

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
							forwardData.put("SectionId", existBdmTlMst.getBdmsSectionid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
							forwardData.put("CellId", existBdmTlMst.getBdmsCellid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
							forwardData.put("MachineId", existBdmTlMst.getBdmsMachineid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
							forwardData.put("AssemblyId", existBdmTlMst.getBdmsAssemblyid());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
							forwardData.put("PhenomenaId", existBdmTlMst.getBdmsFinalphenomena());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
							forwardData.put("CauseId", existBdmTlMst.getBdmsFinalcause());
						else
							forwardData.put("CauseId", "");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinaltrade()))
							forwardData.put("TradeId", existBdmTlMst.getBdmsFinaltrade());
						else
							forwardData.put("TradeId", "");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsProblemdescription()))
							forwardData.put("Problem", existBdmTlMst.getBdmsProblemdescription());
						else
							forwardData.put("Problem", ".");

						if (existBdmTlMst.getBdmDetail() != null && existBdmTlMst.getBdmDetail().size() > 0) // check
																												// for
																												// detail
																												// table
																												// data
						{
							BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl) existBdmTlMst.getBdmDetail().get(0);
							if (UIUtils.isValidKeyId(bdmDetail.getBdanCountermeasure()))
								forwardData.put("Measure", bdmDetail.getBdanCountermeasure());
							else
								forwardData.put("Measure", ".");
						}
						if (forwardData.get("Measure").equals(null))
							forwardData.put("Measure", ".");

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime()))
							forwardData.put("StartTime", existBdmTlMst.getBdmsWostarttime().replace(" ", "-"));

						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime()))
							forwardData.put("EndTime", existBdmTlMst.getBdmsWoendtime().replace(" ", "-"));
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsDowntime()))
							forwardData.put("DownTime", existBdmTlMst.getBdmsDowntime());
						if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
							forwardData.put("EntryDate", existBdmTlMst.getBdmsEntrydate());
					} else {
						CommonFunctions.debugMsg("ELSE");
						forwardData.put("cmbbdmsKeyid", existBdmTlMst.getBdmsKeyid());
						forwardData.put("cmbbdmsFactoryid", existBdmTlMst.getBdmsFactoryid());
						forwardData.put("cmbbdmsCellid", existBdmTlMst.getBdmsCellid());
						forwardData.put("cmbbdmsSectionid", existBdmTlMst.getBdmsSectionid());
						forwardData.put("cmbbdmsMachineid", existBdmTlMst.getBdmsMachineid());
						forwardData.put("cmbbdmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
						forwardData.put("spnbdmsWostart", existBdmTlMst.getBdmsWostarttime().substring(12));
						forwardData.put("spnbdmsWoend", existBdmTlMst.getBdmsWoendtime().substring(12));
						forwardData.put("cmbdownTimeMins", existBdmTlMst.getBdmsDowntime());
						forwardData.put("txtbdmsEntrydate", existBdmTlMst.getBdmsEntrydate());

					}
				} else {
					CommonFunctions.debugMsg("ELSE when null");
					forwardData.put("cmbbdmsKeyid", existBdmTlMst.getBdmsKeyid());
					forwardData.put("cmbbdmsFactoryid", existBdmTlMst.getBdmsFactoryid());
					forwardData.put("cmbbdmsCellid", existBdmTlMst.getBdmsCellid());
					forwardData.put("cmbbdmsSectionid", existBdmTlMst.getBdmsSectionid());
					forwardData.put("cmbbdmsMachineid", existBdmTlMst.getBdmsMachineid());
					forwardData.put("cmbbdmsAssemblyid", existBdmTlMst.getBdmsAssemblyid());
					CommonFunctions.debugMsg(existBdmTlMst.getBdmsWostarttime().length());
					CommonFunctions.debugMsg(existBdmTlMst.getBdmsWoendtime().length());
					if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime())) {
						if (existBdmTlMst.getBdmsWostarttime().length() > 11) {
							if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime().substring(12)))
								forwardData.put("spnbdmsWostart", existBdmTlMst.getBdmsWostarttime().substring(12));
						}
					}
					if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime())) {
						if (existBdmTlMst.getBdmsWoendtime().length() > 11) {
							if (UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime().substring(12)))
								forwardData.put("spnbdmsWoend", existBdmTlMst.getBdmsWoendtime().substring(12));
						}
					}
					forwardData.put("cmbdownTimeMins", existBdmTlMst.getBdmsDowntime());
					forwardData.put("txtbdmsEntrydate", existBdmTlMst.getBdmsEntrydate());
				}

				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;

				if (insert) {
					msgPropertyIdnt = "success-save";
				} else
					msgPropertyIdnt = "success-update";

				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
				successData.put("mode", bdFormBean.getFormMode());
				successData.put("keyId", existBdmTlMst.getBdmsKeyid());
				successData.put("womsKeyid", existBdmTlMst.getBdmsWno());
				httpSession.setAttribute("WorkOrderBD", existBdmTlMst.getWomTlWomst());

				String filemanager = request.getParameter("filemanager");
				if (UIUtils.isValidKeyId(filemanager)) {
					successData.put("filemanager", true);
				}
				returnData.put("forwardData", forwardData);
				returnData.put("persistentData", persistentData);
				// CommonFunctions.debugMsg("Message :
				// "+!bdFormBean.getFormActionMode().equals("yy"));
				if (UIUtils.isValidKeyId(bdFormBean.getFormActionMode())) {
					returnData.put("formMode", bdFormBean.getFormActionMode());
					if (bdFormBean.getFormActionMode().equals("yy") || bdFormBean.getFormActionMode().equals("estimate")
							|| bdFormBean.getFormActionMode().equals("actual")
							|| bdFormBean.getFormActionMode().equals("undefinedPhn"))
						returnData.put("displyMsg", false);
				}
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
				if (UIUtils.isValidKeyId(backTo))
					returnData.put(WOConstants.backTo, backTo);
				CommonFunctions.debugMsg(returnData.toString());
				out.print(returnData.toString());

			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

	private void delBD(HttpServletRequest request, HttpServletResponse response, BDFormBean bdFormBean)
			throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_BdmTlMst existBdmTlMst = (BAL_BdmTlMst) httpSession.getAttribute("newSession");

			BAL_BdmTlMst newBdmTlMst = new BAL_BdmTlMst();
			BAL_BdmTlDtl newBdmTlDtl = new BAL_BdmTlDtl();

			newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());
			newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());

			newBdmTlMst = (BAL_BdmTlMst) UIUtils.setBeanProperties((Object) newBdmTlMst, request);
			newBdmTlDtl = (BAL_BdmTlDtl) UIUtils.setBeanProperties((Object) newBdmTlDtl, request);
			newBdmTlMst.getBdmDetail().add(newBdmTlDtl);

			bdFormBean = (BDFormBean) UIUtils.setBeanProperties((Object) bdFormBean, request);

			try {

				if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag())) {
					if (newBdmTlMst.getBdmsWoprodaccepflag().equals("Y")) {
						throw new BusinessApplicationExceptions("prodAccept,");
					}
				}
				if (UIUtils.isValidKeyId(bdFormBean.getBdmsReportedtime()))
					newBdmTlMst.setBdmsReporteddate(
							newBdmTlMst.getBdmsReporteddate() + " " + bdFormBean.getBdmsReportedtime());

				existBdmTlMst = breakDownService.delete(newBdmTlMst);

				httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
				httpSession.setAttribute("BdmTlMst", existBdmTlMst);
				String formBeanIdentifier = "BDForrmBean" + bdFormBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, bdFormBean);

				JSONObject successData = new JSONObject();
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
				successData.put("mode", bdFormBean.getFormMode());
				successData.put("keyId", existBdmTlMst.getBdmsKeyid());
				JSONObject returnData = new JSONObject();

				returnData.put("successData", successData);

				out.print(returnData.toString());

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "BdCreationException");
				errMessage.put("fromMode", bdFormBean.getFormActionMode());
				out.print(errMessage.toString());

			} catch (BusinessApplicationExceptions e) {
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "BdCreationException");
				out.print(errMessage.toString());

			} catch (Exception e) {
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
		}
	}

	private void saveCom(HttpServletRequest request, HttpServletResponse response, BAL_BDFormBean bdFormBean)
			throws IOException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		CommonFunctions.debugMsg("inside saveCom  ");
		if (httpSession != null && user != null) {
			WomTlCommunicationlog existWomTlCommunicationlog = (WomTlCommunicationlog) httpSession
					.getAttribute("womTlCommunicationlog");
			WomTlCommunicationlog newWomTlCommunicationlog = new WomTlCommunicationlog();
			newWomTlCommunicationlog.setWcmlCreatedby(user.getUsrm_ccno());
			// comTxt = request.getParameter("comTxt");
			// comBdNo = request.getParameter("comBy");
			// comEnteredBy = request.getParameter("bdNo");
			newWomTlCommunicationlog.setWcmlCommunicationtext(request.getParameter("comTxt").toUpperCase());
			newWomTlCommunicationlog.setWcmlEnteredby(user.getUsrm_ccno());
			newWomTlCommunicationlog.setWcmlWonumber(request.getParameter("woNo"));
			String departmentId = user.getUsrm_departmentid();
			if (UIUtils.isValidKeyId(departmentId))
				newWomTlCommunicationlog.setWcmlLevel(departmentId);

			try {
				existWomTlCommunicationlog = breakDownService.saveCommTxt(newWomTlCommunicationlog,
						existWomTlCommunicationlog, bdFormBean);

				httpSession.setAttribute(existWomTlCommunicationlog.getWcmlKeyid(), existWomTlCommunicationlog);
				httpSession.setAttribute("WomTlCommunicationlog", existWomTlCommunicationlog);

				JSONObject successData = new JSONObject();
				successData.put("msg", "Communication Text has been Inserted");
				successData.put("keyId", existWomTlCommunicationlog.getWcmlKeyid());
				JSONObject returnData = new JSONObject();

				returnData.put("successData", successData);

				out.print(returnData.toString());
				/*
				 * JSONObject mode = new JSONObject();
				 * mode.put("formMode",bdFormBean.getFormActionMode()); JSONObject
				 * persistentData = new JSONObject();
				 * persistentData.put("BDKeyid",existWomTlCommunicationlog.getWcmlKeyid());
				 * 
				 * JSONObject forwardData = new JSONObject();
				 * forwardData.put("BDKeyid",existWomTlCommunicationlog.getWcmlKeyid());
				 * mode.put("forwardData",forwardData); mode.put("persistentData",
				 * persistentData); mode.put("tpmException", "Data  Saved");
				 * out.print(mode.toString());
				 */
			} catch (Exception e) {
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
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
			commonFilter = FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}

	private void exportToExcel(HttpServletRequest request, HttpServletResponse response, String Filter, String mode)
			throws Exception {
		/*
		 * HttpSession httpSession = request.getSession(false);
		 * CommonFunctions.debugMsg("wo_getExcel.genmainRpt"); CommonFilter commonFilter
		 * = populateCommonFilter(request,Filter,false); String tmpFromRow =
		 * commonFilter.getFromRow(); commonFilter.setFromRow(null); //String tableModel
		 * = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
		 * "EqpQuery"); String tableModel = (String)
		 * httpSession.getAttribute("BdColModel");
		 * CommonFunctions.debugMsg("(String) httpSession.getAttribute(BdColModel)"+(
		 * String) httpSession.getAttribute("BdColModel")); JSONObject tblJSONObj =
		 * JSONObject.fromString(tableModel); //JSONObject tblJSONObj =
		 * (JSONObject)httpSession.getAttribute("BdColModel"); tblJSONObj.put("title",
		 * "Break Down "+ mode); String format =
		 * ExcelUtils.getFormat(request);http://localhost:6060/perfexitc/comm_Text.Bbrdn
		 * ?mode=bd&formMode=
		 */
		JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		tblJSONObj.put("title", "Break Down " + mode);
		CommonFilter commonFilter = populateCommonFilter(request, Filter, false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		String format = ExcelUtils.getFormat(request);
		Workbook wb = breakDownService.breakdownExportExcel(commonFilter, tblJSONObj, format);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "BreakDownList", format);
	}

	private void exportToExcelBDAnalysisRpt(HttpServletRequest request, HttpServletResponse response, String filter)
			throws Exception {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request, filter, false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		// String tableModel =
		// UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt",
		// "EqpQuery");
		// String tableModel = (String)httpSession.getAttribute("BDRptSummaryColModel");
		JSONObject tblJSONObj = JSONObject.fromString(httpSession.getAttribute("BDRptSummaryColModel").toString());
		// JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("BdColModel");
		tblJSONObj.put("title", "Break Down Summary");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = breakDownService.bdRptSummaryExportExcel(commonFilter, tblJSONObj, format);
		commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "BreakDown", format);

	}

	private JSONObject getTableModelPcsBd(List<String[]> kpivReportList, CommonFilter commonFilter) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = kpivReportList.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		// jqGridTableModel.setEnableFilter(false);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));

			jqGridColModel.setWidth(110);
			jqGridColModel.setAlign("left");

			CommonFunctions.debugMsg("SAOMAintOrdercolheader  " + i + " : " + colHeader[i]);// if i==0
			if (i == 0) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
				jqGridColModel.setHidden(false);
			} else if (i == 1) {
				jqGridColModel.setWidth(170);
				jqGridColModel.setAlign("left");
			} else if (i == 2 || i == 3) {
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("left");
			}

			else if (i == 4 || i == 5) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i == 6 || i == 7) {
				jqGridColModel.setWidth(250);
				jqGridColModel.setAlign("left");
			} else if (i == 8 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	private JSONObject getTableModelSapInfo(List<String[]> kpivReportList, CommonFilter commonFilter) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = kpivReportList.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		// jqGridTableModel.setEnableFilter(false);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));

			jqGridColModel.setWidth(30);
			jqGridColModel.setAlign("left");
			if (i == 6) {
				// jqGridColModel.setEditable(false);
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("quantityFormatter");
				jqGridColModel.setWidth(80);
			}
			if (i == 9) {
				jqGridColModel.setHidden(true);
			}
			if (i == 10) {
				jqGridColModel.setHidden(true);
			}
			CommonFunctions.debugMsg("SAOMAintOrdercolheader  " + i + " : " + colHeader[i]);// if i==0
			if (i == 0) {
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(true);
			}

			else if (i == 1) {
				jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("txtSelect");

			} else if (i == 2 || i == 4 || i == 5) {
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("left");
			} else if (i == 3) {
				jqGridColModel.setWidth(230);
				jqGridColModel.setAlign("left");
			}

			else if (i > 5) {
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("right");
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	private JSONObject getTableModelExeSub(List<String[]> kpivReportList, CommonFilter commonFilter) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = kpivReportList.get(0);
		jqGridTableModel.setTableButton(false);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(80);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			CommonFunctions.debugMsg("colheader  " + i + " : " + colHeader[i]);// if i==0
			if (i <= 1) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i >= 7) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	private JSONObject getTableModelExtSer(List<String[]> grid, CommonFilter commonFilter) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = grid.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		// jqGridTableModel.setEnableFilter(false);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));

			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			CommonFunctions.debugMsg("colheader  " + i + " : " + colHeader[i]);// if i==0
			if (i <= 1) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("left");

			} else if (i <= 8) {

				jqGridColModel.setWidth(30);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	private JSONObject getTableModelExtRepair(List<String[]> grid, CommonFilter commonFilter) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = grid.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));

			jqGridColModel.setWidth(50);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			CommonFunctions.debugMsg("colheader  " + i + " : " + colHeader[i]);// if i==0
			if (i == 0) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			} else if (i <= 19) {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}
}
