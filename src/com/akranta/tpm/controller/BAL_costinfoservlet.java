package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CostInfoBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlManpowercostactual;
import com.akranta.tpm.model.BAL_WomTlManpowercostplan;
import com.akranta.tpm.model.BAL_WomTlOthercostactual;
import com.akranta.tpm.model.BAL_WomTlOthercostplan;
import com.akranta.tpm.model.BAL_WomTlServicecostactual;
import com.akranta.tpm.model.BAL_WomTlServicecostplan;
import com.akranta.tpm.model.BAL_WomTlSparecostactual;
import com.akranta.tpm.model.BAL_WomTlSparecostplan;
import com.akranta.tpm.model.BAL_WomTlUtilitycostactual;
import com.akranta.tpm.model.BAL_WomTlUtilitycostplan;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_CostInfoService;
import com.akranta.tpm.service.BAL_EmpCostService;
import com.akranta.tpm.service.impl.BAL_BreakdownServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_CostInfoServiceImpl;
import com.akranta.tpm.service.impl.BAL_EmpCostServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.BdmServiceApi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("unused")
public class BAL_costinfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
	private BdmServiceApi bdmServiceApi;

	BAL_CostInfoService costInfoService;
	BAL_EmpCostService empCostService;
	CommonFilterService commonFilterService;
	CommonFilter commonFilter;

	String formTypeIdentifier[];

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BAL_costinfoservlet() {
		super();
		/*
		 * try { costInfoService = new CostInfoServiceImpl(); empCostService = new
		 * EmpCostServiceImpl(); commonFilterService = new CommonFilterServiceImpl(); }
		 * catch (Exception e) {
		 * 
		 * }
		 */ // TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);

		ComboFilter comboFilter = new ComboFilter();
		comboFilter = UIUtils.fillComboFilter(request);

		try {
			costInfoService = (BAL_CostInfoServiceImpl) UIUtils.getServiceObject(request, "BAL_CostInfoServiceImpl");
			empCostService = (BAL_EmpCostServiceImpl) UIUtils.getServiceObject(request, "BAL_EmpCostServiceImpl");
			commonFilterService = (CommonFilterServiceImpl) UIUtils.getServiceObject(request,			
					"CommonFilterServiceImpl");
			empCostService.BAL_EmpCostServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		if (action.equals("CostRpt_input.crt")) {
			CommonFunctions.debugMsg("input the jsp");
			// there is nothing to be done

		} else if (action.equals("CostRpt_view.crt")) {
		}

		else if (action.equals("comb_setUser.crt")) {
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			// request.setAttribute("userNo", user);
			PrintWriter out = response.getWriter();
			JSONObject userData = new JSONObject();
			CommonFunctions.debugMsg("userId" + user.getUsrm_ccno());
			userData.put("userId", user.getUsrm_ccno());
			out.println(userData);

		}

		else if (action.equals("checkManpowerExists.crt")) {
			try {
				String woid = request.getParameter("woId");
				String formType = request.getParameter("formType");
				CommonFunctions.debugMsg("woid" + woid);
				PrintWriter out = response.getWriter();
				String count = empCostService.getCheckManpowerExists(formType, woid);
				CommonFunctions.debugMsg("count " + count);
				JSONObject actualData = new JSONObject();
				actualData.put("count", count);
				out.println(actualData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}

		}

		else if (action.equals("functionalLoc.crt")) {

			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbspcnFactoryid");
			functLocFieldNameBean.setSection("cmbspcnSectionid");
			functLocFieldNameBean.setCell("cmbspcnCellid");
			functLocFieldNameBean.setMachine("cmbspcnMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbspcnFlid");

			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);

			FormModes formModes = (FormModes) httpSession.getAttribute("CostInfoFormMode");
			// if( formModes == FormModes.completion)
			formModes = FormModes.view;

			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);

		}

		else if (action.equals("CostRpt_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			CommonFunctions.debugMsg("inside getcol.d...");
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "costInformation"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "costInformation"));
		} else if (action.equals("CostRpt_getData.crt")) {
			{
				try {
					// if(commonFilter==null)
					commonFilter = new CommonFilter();
					FilterValues.getCommonFilters(request, commonFilter);
					FilterValues.getCostInfo(request, commonFilter);
					CommonFunctions.debugMsg("FromDate Servlet" + commonFilter.getFromDate());
					CommonFunctions.debugMsg("ToDate servlet:" + commonFilter.getToDate());
					CommonFunctions.debugMsg(" commonFilter.getReptype " + commonFilter.getReptype());

					response.setContentType("text/html");

					PrintWriter out = response.getWriter();
					List<String[]> estimateList = empCostService.getCostInfoView(commonFilter);
					CommonFunctions.debugMsg(estimateList.size());
					JSONObject estimateData = UIUtils.convertToJqGridTableObject(estimateList, request, 0, 0);
					out.println(estimateData);
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
				}
			}
		}

		else if (action.equals("costInfo_getPageTotal.crt")) {
			try {
				String formName = request.getParameter("formName");
				String formType = request.getParameter("formType");
				String woId = request.getParameter("woId");

				CommonFunctions.debugMsg("woid:" + woId + " formName :" + formName + " formType :" + formType);
				PrintWriter out = response.getWriter();
				List<String[]> pageTotal = empCostService.getPageTotal(formName, formType, woId);
				CommonFunctions.debugMsg(pageTotal.size());
				writePageTotal(response, pageTotal);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		} else if (action.equals("costSummary_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions
					.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "costSummary"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "costSummary"));
		} else if (action.equals("costSummary_getData.crt")) {
			try {

				String formName = request.getParameter("formName");
				String woid = request.getParameter("woId");
				CommonFunctions.debugMsg("get data ---- woid" + woid);
				if (woid == null)
					return;
				PrintWriter out = response.getWriter();
				List<String[]> summaryList = empCostService.getGridSummaryQuery(woid);
				CommonFunctions.debugMsg(summaryList.size());
				JSONObject summaryData = UIUtils.convertToJqGridTableObject(summaryList, request, 0, 0);
				out.println(summaryData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		// employee Cost Grid
		else if (action.equals("empCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostEstimate"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostEstimate"));
		} else if (action.equals("empCostEstimation_getData.crt")
				|| action.equals("contracotCostEstimation_getData.crt")
				|| action.equals("spareCostEstimation_getData.crt")
				|| action.equals("serviceCostEstimation_getData.crt")
				|| action.equals("utilityCostEstimation_getData.crt")
				|| action.equals("otherCostEstimation_getData.crt")) {
			try {
				String formName = request.getParameter("formName");
				String woid = request.getParameter("woId");
				CommonFunctions.debugMsg("woid" + woid);
				PrintWriter out = response.getWriter();
				List<String[]> estimateList = empCostService.getGridEstQuery(formName, woid);
				CommonFunctions.debugMsg(estimateList.size());
				JSONObject estimateData = UIUtils.convertToJqGridTableObject(estimateList, request, 0, 0);
				out.println(estimateData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("empCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions
					.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostActual"));
		} else if (action.equals("empCostActual_getData.crt") || action.equals("contracotCostActual_getData.crt")
				|| action.equals("spareCostActual_getData.crt") || action.equals("serviceCostActual_getData.crt")
				|| action.equals("utilityCostActual_getData.crt") || action.equals("otherCostActual_getData.crt")) {
			try {
				String formName = request.getParameter("formName");
				String woid = request.getParameter("woId");
				CommonFunctions.debugMsg("woid" + woid);
				PrintWriter out = response.getWriter();
				List<String[]> actualList = empCostService.getGridActQuery(formName, woid);
				CommonFunctions.debugMsg(actualList.size());
				JSONObject actualData = UIUtils.convertToJqGridTableObject(actualList, request, 0, 0);
				out.println(actualData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("CostRpt_getExcel.crt")) {
			CommonFunctions.debugMsg("Inside export Excel");
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoRpt", "CostInfoRpt");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			String date = fromMonth + "  -  " + toMonth;
			CommonFunctions.debugMsg("Inside export Excel 1234");
			tblJSONObj.put("title", "Cost Info Report " + "  -  " + date);
			String format = ExcelUtils.getFormat(request);

			Workbook wb = empCostService.getAllCostInfoExcel(commonFilter, tblJSONObj, format);

			commonFilter.setFromRow(tmpFromRow);
			CommonFunctions.debugMsg("Inside export Excel ..........");
			ExcelUtils.writeToResponse(response, wb, "CostInformation", format);
		}

		else if (action.equals("empCostManPower_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostManPower"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "empCostManPower"));
		} else if (action.equals("empCostManPower_getData.crt") || action.equals("contracotCostManPower_getData.crt")) {
			try {
				String formName = request.getParameter("formName");
				String formType = request.getParameter("formType");
				String woid = request.getParameter("woId");
				CommonFunctions.debugMsg("woid" + woid);
				CommonFunctions.debugMsg("formtype" + formType);
				PrintWriter out = response.getWriter();
				List<String[]> actualList = empCostService.getGridManPowerQry(formName, formType, woid);
				CommonFunctions.debugMsg(actualList.size());
				JSONObject actualData = UIUtils.convertToJqGridTableObject(actualList, request, 0, 0);
				out.println(actualData);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		// Contractor Cost Grid
		else if (action.equals("contracotCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostEstimate"));
			out.println(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostEstimate"));
		}
		/*
		 * else if( action.equals("contracotCostEstimation_getData.crt") ) { }
		 */

		else if (action.equals("contracotCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostActual"));
		}
		/*
		 * else if( action.equals("contracotCostActual_getData.crt") ) { }
		 */

		else if (action.equals("contracotCostManPower_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostManPower"));
			out.println(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "contracotCostManPower"));
		}
//		else if( action.equals("contracotCostManPower_getData.crt") )
//		{
//		}	

		// Spare Cost Grid
		else if (action.equals("spareCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "spareCostEstimate"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "spareCostEstimate"));
		}
//		else if( action.equals("spareCostEstimation_getData.crt") )
//		{
//		}

		else if (action.equals("spareCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "spareCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "spareCostActual"));
		}
//		else if( action.equals("spareCostActual_getData.crt") )
//		{
//		}		
		// Service Cost Grid
		else if (action.equals("serviceCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "serviceCostEstimate"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "serviceCostEstimate"));
		} else if (action.equals("serviceCostEstimation_getData.crt")) {
		} else if (action.equals("serviceCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "serviceCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "serviceCostActual"));
		} else if (action.equals("serviceCostActual_getData.crt")) {
		}
		// Utilities Cost Grid
		else if (action.equals("utilityCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "utilityCostEstimate"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "utilityCostEstimate"));
		} else if (action.equals("utilityCostEstimation_getData.crt")) {
		}

		else if (action.equals("utilityCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "utilityCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "utilityCostActual"));
		} else if (action.equals("utilityCostActual_getData.crt")) {
		}

		// Other Cost Grid
		else if (action.equals("otherCostEstimation_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "otherCostEstimate"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "otherCostEstimate"));
		} else if (action.equals("otherCostEstimation_getData.crt")) {
		}

		else if (action.equals("otherCostActual_getCol.crt")) {
			// httpSession.setAttribute("factId", request.getParameter("factId"));
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg(
					UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "otherCostActual"));
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CostInfoColModel", "otherCostActual"));
		} else if (action.equals("otherCostActual_getData.crt")) {
		}

		String dispatchUrl = null;

		if (action.equals("CostRpt_input.crt")) {
			CommonFunctions.debugMsg("Out the jsp");
			dispatchUrl = "/pages/costinformation/costInfoView.jsp";
			request.setAttribute("reportType", "BDM");
		}

		else if (action.equals("empCostActual_Input.crt")) {
			dispatchUrl = "/pages/costinformation/empcostEst.jsp";
		}
		/*
		 * else if (action.equals("costSummary_input.crt")) {
		 * 
		 * CommonFunctions.debugMsg("cost summarty the jsp");
		 */
		// Enumeration<String> params = request.getParameterNames() ;
		// CommonFunctions.debugMsg("next "+params.nextElement());
		// while(params.hasMoreElements() )
		// {
		// CommonFunctions.debugMsg("params " + params.nextElement());
		// if(UIUtils.isValidKeyId( request.getParameter(params.nextElement())));
		// request.setAttribute(params.nextElement(),
		// request.getParameter(params.nextElement()));
		// }

		/*
		 * CommonFunctions.debugMsg(request.getParameter("formType"));
		 * CommonFunctions.debugMsg("formType"+ request.getParameter("formType"));
		 * CommonFunctions.debugMsg("DocType"+ request.getParameter("DocType"));
		 * CommonFunctions.debugMsg("DocNo"+ request.getParameter("DocNo"));
		 * CommonFunctions.debugMsg("FactoryId"+ request.getParameter("FactoryId"));
		 * CommonFunctions.debugMsg("SectionId"+ request.getParameter("SectionId"));
		 * CommonFunctions.debugMsg("CellId"+ request.getParameter("CellId"));
		 * CommonFunctions.debugMsg("MachineId"+ request.getParameter("MachineId"));
		 * CommonFunctions.debugMsg("AssemblyId"+ request.getParameter("AssemblyId"));
		 * CommonFunctions.debugMsg("PhenomenaId"+ request.getParameter("PhenomenaId"));
		 * CommonFunctions.debugMsg("CauseId"+ request.getParameter("CauseId"));
		 * CommonFunctions.debugMsg("TradeId"+ request.getParameter("TradeId"));
		 * CommonFunctions.debugMsg("Problem"+ request.getParameter("Problem"));
		 * CommonFunctions.debugMsg("Measure"+ request.getParameter("Measure"));
		 * CommonFunctions.debugMsg("StartDate"+
		 * request.getParameter("StartTime").substring(0, 11));
		 * CommonFunctions.debugMsg("StartTime"+
		 * request.getParameter("StartTime").substring(12, 17));
		 * CommonFunctions.debugMsg("EndDate"+
		 * request.getParameter("EndTime").substring(0, 11));
		 * CommonFunctions.debugMsg("EndTime"+
		 * request.getParameter("EndTime").substring(12, 17));
		 * CommonFunctions.debugMsg("DownTime"+ request.getParameter("DownTime"));
		 * CommonFunctions.debugMsg("EntryDate"+ request.getParameter("EntryDate"));
		 */
		/*
		 * if(UIUtils.isValidKeyId(request.getParameter("formType"))&&
		 * !UIUtils.isValidKeyId(request.getParameter("fromForm"))){
		 * request.setAttribute("formType", request.getParameter("formType"));
		 * request.setAttribute("DocType", request.getParameter("DocType"));
		 * request.setAttribute("DocNo", request.getParameter("DocNo"));
		 * request.setAttribute("FactoryId", request.getParameter("FactoryId"));
		 * request.setAttribute("SectionId", request.getParameter("SectionId"));
		 * request.setAttribute("CellId", request.getParameter("CellId"));
		 * request.setAttribute("MachineId", request.getParameter("MachineId"));
		 * request.setAttribute("AssemblyId", request.getParameter("AssemblyId"));
		 * request.setAttribute("PhenomenaId", request.getParameter("PhenomenaId"));
		 * request.setAttribute("CauseId", request.getParameter("CauseId"));
		 * request.setAttribute("TradeId", request.getParameter("TradeId"));
		 * request.setAttribute("Problem", request.getParameter("Problem"));
		 * request.setAttribute("Measure", request.getParameter("Measure")); String
		 * StartTime = request.getParameter("StartTime"); String modifiedStartDate =
		 * CommonFunctions.pg_getDateTimeFromPGTimeStamp(StartTime); String date2 =
		 * CommonFunctions.pg_getDateFromPGTimeStamp(StartTime); String[] parts =
		 * modifiedStartDate.split(" "); String resultDate = parts[0]; // 01-Jul-2026
		 * String resultTime = parts[1]; // 12:25:00 //String modifiedStartTime =
		 * CommonFunctions.pg_getDateTimeFromPGTimeStamp(StartTime); // mano has
		 * commented the below get parameter request.setAttribute("StartDate", date2);
		 * request.setAttribute("StartTime",resultTime); request.setAttribute("EndDate",
		 * request.getParameter("EndTime")); request.setAttribute("EndTime",
		 * request.getParameter("EndTime")); request.setAttribute("DownTime",
		 * request.getParameter("DownTime")); request.setAttribute("EntryDate",
		 * request.getParameter("EntryDate")); }
		 * if(UIUtils.isValidKeyId(request.getParameter("fromForm"))){
		 * if(UIUtils.isValidKeyId(request.getParameter("formType")))
		 * request.setAttribute("formType", request.getParameter("formType"));
		 * request.setAttribute("fromWorkOrder", request.getParameter("fromForm"));
		 * if(UIUtils.isValidKeyId(request.getParameter("mchid")))
		 * request.setAttribute("mchid", request.getParameter("mchid"));
		 * if(UIUtils.isValidKeyId(request.getParameter("assmId")))
		 * request.setAttribute("assmId", request.getParameter("assmId"));
		 * if(UIUtils.isValidKeyId(request.getParameter("tradeId")))
		 * request.setAttribute("tradeId", request.getParameter("tradeId"));
		 * request.setAttribute("DocType", request.getParameter("DocType"));
		 * request.setAttribute("DocNo", request.getParameter("DocNo")); } dispatchUrl =
		 * "/pages/costinformation/costinformation.jsp"; }
		 */
		/*
		 * else if (action.equals("costSummary_input.crt")) {
		 * 
		 * CommonFunctions.debugMsg("cost summarty the jsp"); //Enumeration<String>
		 * params = request.getParameterNames() ;
		 * //CommonFunctions.debugMsg("next "+params.nextElement());
		 * //while(params.hasMoreElements() ) //{ // CommonFunctions.debugMsg("params  "
		 * + params.nextElement()); //if(UIUtils.isValidKeyId(
		 * request.getParameter(params.nextElement())));
		 * //request.setAttribute(params.nextElement(),
		 * request.getParameter(params.nextElement())); //}
		 * 
		 * CommonFunctions.debugMsg(request.getParameter("formType"));
		 * CommonFunctions.debugMsg("formType"+ request.getParameter("formType"));
		 * CommonFunctions.debugMsg("DocType"+ request.getParameter("DocType"));
		 * CommonFunctions.debugMsg("DocNo"+ request.getParameter("DocNo"));
		 * CommonFunctions.debugMsg("FactoryId"+ request.getParameter("FactoryId"));
		 * CommonFunctions.debugMsg("SectionId"+ request.getParameter("SectionId"));
		 * CommonFunctions.debugMsg("CellId"+ request.getParameter("CellId"));
		 * CommonFunctions.debugMsg("MachineId"+ request.getParameter("MachineId"));
		 * CommonFunctions.debugMsg("AssemblyId"+ request.getParameter("AssemblyId"));
		 * CommonFunctions.debugMsg("PhenomenaId"+ request.getParameter("PhenomenaId"));
		 * CommonFunctions.debugMsg("CauseId"+ request.getParameter("CauseId"));
		 * CommonFunctions.debugMsg("TradeId"+ request.getParameter("TradeId"));
		 * CommonFunctions.debugMsg("Problem"+ request.getParameter("Problem"));
		 * CommonFunctions.debugMsg("Measure"+ request.getParameter("Measure"));
		 * CommonFunctions.debugMsg("StartDate"+
		 * request.getParameter("StartTime").substring(0, 11));
		 * CommonFunctions.debugMsg("StartTime"+
		 * request.getParameter("StartTime").substring(12, 17));
		 * CommonFunctions.debugMsg("EndDate"+
		 * request.getParameter("EndTime").substring(0, 11));
		 * CommonFunctions.debugMsg("EndTime"+
		 * request.getParameter("EndTime").substring(12, 17));
		 * CommonFunctions.debugMsg("DownTime"+ request.getParameter("DownTime"));
		 * CommonFunctions.debugMsg("EntryDate"+ request.getParameter("EntryDate"));
		 * if(UIUtils.isValidKeyId(request.getParameter("formType"))&&
		 * !UIUtils.isValidKeyId(request.getParameter("fromForm"))){
		 * request.setAttribute("formType", request.getParameter("formType"));
		 * request.setAttribute("DocType", request.getParameter("DocType"));
		 * request.setAttribute("DocNo", request.getParameter("DocNo"));
		 * request.setAttribute("FactoryId", request.getParameter("FactoryId"));
		 * request.setAttribute("SectionId", request.getParameter("SectionId"));
		 * request.setAttribute("CellId", request.getParameter("CellId"));
		 * request.setAttribute("MachineId", request.getParameter("MachineId"));
		 * request.setAttribute("AssemblyId", request.getParameter("AssemblyId"));
		 * request.setAttribute("PhenomenaId", request.getParameter("PhenomenaId"));
		 * request.setAttribute("CauseId", request.getParameter("CauseId"));
		 * request.setAttribute("TradeId", request.getParameter("TradeId"));
		 * request.setAttribute("Problem", request.getParameter("Problem"));
		 * request.setAttribute("Measure", request.getParameter("Measure"));
		 * request.setAttribute("StartDate",
		 * request.getParameter("StartTime").substring(0, 11));
		 * request.setAttribute("StartTime",
		 * request.getParameter("StartTime").substring(12, 17));
		 * request.setAttribute("EndDate", request.getParameter("EndTime").substring(0,
		 * 11)); request.setAttribute("EndTime",
		 * request.getParameter("EndTime").substring(12, 17));
		 * request.setAttribute("DownTime", request.getParameter("DownTime"));
		 * request.setAttribute("EntryDate", request.getParameter("EntryDate")); }
		 * if(UIUtils.isValidKeyId(request.getParameter("fromForm"))){
		 * if(UIUtils.isValidKeyId(request.getParameter("formType")))
		 * request.setAttribute("formType", request.getParameter("formType"));
		 * request.setAttribute("fromWorkOrder", request.getParameter("fromForm"));
		 * if(UIUtils.isValidKeyId(request.getParameter("mchid")))
		 * request.setAttribute("mchid", request.getParameter("mchid"));
		 * if(UIUtils.isValidKeyId(request.getParameter("assmId")))
		 * request.setAttribute("assmId", request.getParameter("assmId"));
		 * if(UIUtils.isValidKeyId(request.getParameter("tradeId")))
		 * request.setAttribute("tradeId", request.getParameter("tradeId"));
		 * request.setAttribute("DocType", request.getParameter("DocType"));
		 * request.setAttribute("DocNo", request.getParameter("DocNo")); } dispatchUrl =
		 * "/pages/costinformation/costinformation.jsp"; }
		 */
		else if (action.equals("costSummary_input.crt")) {

			CommonFunctions.debugMsg("cost summarty the jsp");

			if (UIUtils.isValidKeyId(request.getParameter("formType"))
					&& !UIUtils.isValidKeyId(request.getParameter("fromForm"))) {
				request.setAttribute("formType", request.getParameter("formType"));
				request.setAttribute("DocType", request.getParameter("DocType"));
				request.setAttribute("DocNo", request.getParameter("DocNo"));
				request.setAttribute("FactoryId", request.getParameter("FactoryId"));
				request.setAttribute("SectionId", request.getParameter("SectionId"));
				request.setAttribute("CellId", request.getParameter("CellId"));
				request.setAttribute("MachineId", request.getParameter("MachineId"));
				request.setAttribute("AssemblyId", request.getParameter("AssemblyId"));
				request.setAttribute("PhenomenaId", request.getParameter("PhenomenaId"));
				request.setAttribute("CauseId", request.getParameter("CauseId"));
				request.setAttribute("TradeId", request.getParameter("TradeId"));
				request.setAttribute("Problem", request.getParameter("Problem"));
				request.setAttribute("Measure", request.getParameter("Measure"));

				// ---- FIXED: use existing CommonFunctions helper instead of substring ----
				String startTimeParam = request.getParameter("StartTime");
				if (!startTimeParam.matches(".*:\\d{2}$")) {
					startTimeParam = startTimeParam + ":00"; // ensure seconds present, same convention used in
																// BdmServiceImpl
				}
				String startFormatted = CommonFunctions.pg_getDateTimeFromPGTimeStamp(startTimeParam); // ->
																										// "dd-MMM-yyyy
																										// HH:mm:ss"
				request.setAttribute("StartDate", startFormatted.substring(0, 11).trim()); // "01-Jul-2026"
				request.setAttribute("StartTime", startFormatted.substring(12, 17)); // "12:25"

				String endTimeParam = request.getParameter("EndTime");
				if (!endTimeParam.matches(".*:\\d{2}$")) {
					endTimeParam = endTimeParam + ":00";
				}
				String endFormatted = CommonFunctions.pg_getDateTimeFromPGTimeStamp(endTimeParam);
				request.setAttribute("EndDate", endFormatted.substring(0, 11).trim());
				request.setAttribute("EndTime", endFormatted.substring(12, 17));

				String entryDateParam = request.getParameter("EntryDate");
				if (entryDateParam != null && !entryDateParam.trim().isEmpty()) {
					if (!entryDateParam.matches(".*:\\d{2}$")) {
						entryDateParam = entryDateParam + ":00";
					}
					String entryFormatted = CommonFunctions.pg_getDateTimeFromPGTimeStamp(entryDateParam);
					request.setAttribute("EntryDate", entryFormatted.substring(0, 11).trim()); // "29-Jun-2026"
				} else {
					request.setAttribute("EntryDate", entryDateParam);
				}
				// ---------------------------------------------------------------------

				request.setAttribute("DownTime", request.getParameter("DownTime"));
				// request.setAttribute("EntryDate", request.getParameter("EntryDate"));
			}
			if (UIUtils.isValidKeyId(request.getParameter("fromForm"))) {
				if (UIUtils.isValidKeyId(request.getParameter("formType")))
					request.setAttribute("formType", request.getParameter("formType"));
				request.setAttribute("fromWorkOrder", request.getParameter("fromForm"));
				if (UIUtils.isValidKeyId(request.getParameter("mchid")))
					request.setAttribute("mchid", request.getParameter("mchid"));
				if (UIUtils.isValidKeyId(request.getParameter("assmId")))
					request.setAttribute("assmId", request.getParameter("assmId"));
				if (UIUtils.isValidKeyId(request.getParameter("tradeId")))
					request.setAttribute("tradeId", request.getParameter("tradeId"));
				request.setAttribute("DocType", request.getParameter("DocType"));
				request.setAttribute("DocNo", request.getParameter("DocNo"));
			}
			dispatchUrl = "/pages/costinformation/costinformation.jsp";
		} else if (action.equals("empCost_input.crt"))
			dispatchUrl = "/pages/costinformation/empcost.jsp";
		else if (action.equals("contractorCost_input.crt"))
			dispatchUrl = "/pages/costinformation/contractorcost.jsp";
		else if (action.equals("spareCost_input.crt"))
			dispatchUrl = "/pages/costinformation/sparecost.jsp";
		else if (action.equals("serviceCost_input.crt"))
			dispatchUrl = "/pages/costinformation/servicecost.jsp";
		else if (action.equals("utilityCost_input.crt"))
			dispatchUrl = "/pages/costinformation/utilitycost.jsp";
		else if (action.equals("otherCost_input.crt"))
			dispatchUrl = "/pages/costinformation/othercost.jsp";

		else if (action.equals("combo_RefDocNo.crt")) {
			try {
				List<ComboBox> refDocNo = costInfoService.getBD("");
				UIUtils.writeComboBox(response, refDocNo, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_phenomena.crt")) {
			try {
				List<ComboBox> phenomena = costInfoService.getPhenomena("",comboFilter);
				UIUtils.writeComboBox(response, phenomena, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_cause.crt")) {
			try {
				List<ComboBox> cause = costInfoService.getCause("",comboFilter);
				UIUtils.writeComboBox(response, cause, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_empgrade.crt")) {
			try {

				CommonMessage.debugMsg("*****************Grade Common Filter");
				List<ComboBox> contractor = costInfoService.getEmpgrade("", comboFilter);
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		  else if (action.equals("combo_spares.crt")) { try {
			  
			 // String factId =		  request.getParameter("factoryId"); //mano has commented part
		  String condSql = "";
		  //" AND SPRM_FACTORYID IN ('{}','" + factId + "')"
		  CommonFunctions.debugMsg(request.getParameter("factoryId") + condSql);
		  List<ComboBox> contractor = costInfoService.getSpares(condSql,comboFilter);
		  UIUtils.writeComboBox(response, contractor, comboFilter); } catch (Exception
		  e) { e.printStackTrace(); } }
		 
		/*
		 * else if (action.equals("combo_spares.crt")) { try { String factId =
		 * request.getParameter("factoryId"); String filter =
		 * request.getParameter("filter"); // <-- the missing piece //mano has commented
		 * part String condSql = ""; " AND SPRM_FACTORYID IN ('{}','" + factId + "')"
		 * CommonFunctions.debugMsg(request.getParameter("factoryId") + condSql);
		 * List<ComboBox> contractor = costInfoService.getSpares(condSql, filter);
		 * UIUtils.writeComboBox(response, contractor, comboFilter); } catch (Exception
		 * e) { e.printStackTrace(); } }
		 */
		else if (action.equals("combo_vendor.crt")) {
			try {
				List<ComboBox> vendor = costInfoService.getVendor("",comboFilter);
				UIUtils.writeComboBox(response, vendor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_contractor.crt")) {
			try {
				List<ComboBox> contractor = costInfoService.getContractor("",comboFilter);
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_service.crt")) {
			try {
				List<ComboBox> contractor = costInfoService.getService("",comboFilter);
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_utilities.crt")) {
			try {
				List<ComboBox> contractor = costInfoService.getUtilities("",comboFilter);
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("combo_expense.crt")) {
			try {
				List<ComboBox> contractor = costInfoService.getExpense("",comboFilter);
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("CostRpt_view.crt")) {

		}

		CommonFunctions.debugMsg(" dispatchUrl " + dispatchUrl);
		if (dispatchUrl != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
			rd.forward(request, response);
			CommonFunctions.debugMsg(" response " + response);
		}

		else if (action.equals("empCost_getEmpCost.crt")) {
			try {
				List<String[]> empDetils = empCostService.getEmpCost(request.getParameter("empId"));
				writeEmpDetails(response, empDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("contractorCost_getContractorCost.crt")) {
			try {
				List<String[]> empDetils = empCostService.getContractorCost(request.getParameter("empId"));
				writeEmpDetails(response, empDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("spareCost_getSpareCost.crt")) {
			try {
				List<String[]> spareDetils = empCostService.getSpareCost(request.getParameter("spareId"));
				writeSpareDetails(response, spareDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("serviceCost_getServiceCost.crt")) {
			CommonFunctions.debugMsg("service cost insider");
			try {
				List<String[]> serviceDetils = empCostService.getServiceCost(request.getParameter("serviceId"));
				writeServiceDetails(response, serviceDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("utilityCost_getUtilityCost.crt")) {
			CommonFunctions.debugMsg("Utility cost insider");
			try {
				List<String[]> utilityDetils = empCostService.getUtilityCost(request.getParameter("utilityId"),
						request.getParameter("effDate"));
				writeUtilityDetails(response, utilityDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("otherCost_getOtherCost.crt")) {
			CommonFunctions.debugMsg("Other cost insider");
			try {
				List<String[]> otherDetils = empCostService.getOtherCost(request.getParameter("otherId"));
				CommonFunctions.debugMsg(otherDetils.size());
				writeOtherDetails(response, otherDetils);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//delete
		else if (action.equals("empCostManPower_delete.crt")) {
			CommonFunctions.debugMsg(" inside delete ");
			deleteEmpCost(request, response);
		}

//SAVE			
//SAVE FOR EMPCOSE
		else if (action.equals("empCostManPower_save.crt")) {
			CommonFunctions.debugMsg(" inside save ");
			saveEmpCost(request, response);
		}

		// SAVE FOR ContractorSave
		else if (action.equals("contractorCostManPower_save.crt")) {
			CommonFunctions.debugMsg(" inside contractor save ");
			saveEmpCost(request, response);
		}

		// SAVE FOR SparesSave
		else if (action.equals("spareCostManPower_save.crt")) {
			CommonFunctions.debugMsg(" inside spares save ");
			saveSpareCost(request, response);
		}
	}

	private void saveEmpCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonFunctions.debugMsg("Pcl MAIN");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_CostInfoBean costInfoBean = (BAL_CostInfoBean) httpSession.getAttribute("CostInfoServletCostInfoBean");

			if (costInfoBean == null)
				costInfoBean = new BAL_CostInfoBean();

			CommonFunctions.debugMsg("savemode" + request.getParameter("saveMode"));
			CommonFunctions.debugMsg("woid" + request.getParameter("woId"));
			CommonFunctions.debugMsg("docType" + request.getParameter("docType"));

			if (request.getParameter("saveMode").equals("Update"))
				costInfoBean.setFormActionMode("Update");
			else
				costInfoBean.setFormActionMode("Save");

			// costInfoBean.setDocNo(request.getParameter("woId"));
			// costInfoBean.setDocType(request.getParameter("docType"));
			String docType = request.getParameter("docType");
			String docNo = request.getParameter("woId");
			CommonFunctions.debugMsg("formBean Mode" + costInfoBean.getFormActionMode());

			try {
				String keyId = "";
				CommonFunctions.debugMsg("Type=" + request.getParameter("Type"));
				if (request.getParameter("Type").equals("Estimate")) {
					BAL_WomTlManpowercostplan existWomTlManpowercostplan = (BAL_WomTlManpowercostplan) httpSession
							.getAttribute("BAL_womTlManpowercostplan");

					BAL_WomTlManpowercostplan newWomTlManpowercostplan = new BAL_WomTlManpowercostplan();
					BAL_WomTlManpowercostplan oldWomTlManpowercostplan = new BAL_WomTlManpowercostplan();
					newWomTlManpowercostplan.setMpcpCreatedby(user.getUsrm_ccno());

					newWomTlManpowercostplan.setMpcpDoctype(docType);
					newWomTlManpowercostplan.setMpcpWoid(docNo);

					newWomTlManpowercostplan = (BAL_WomTlManpowercostplan) UIUtils
							.setBeanProperties((Object) newWomTlManpowercostplan, request);

					if (request.getParameter("Skill").equals("H")) {
						CommonFunctions.debugMsg(request.getParameter("cmbmpcpManpoweridcc"));
						newWomTlManpowercostplan.setMpcpSkillflag("H");
						newWomTlManpowercostplan.setMpcpManpowerid(request.getParameter("cmbmpcpManpoweridcc"));
						newWomTlManpowercostplan.setMpcpSkillid(request.getParameter("cmbmpcpSkillidcc"));
						newWomTlManpowercostplan.setMpcpNormalmins(request.getParameter("txtmpcpNormalminscc"));
						newWomTlManpowercostplan.setMpcpNormalcost(request.getParameter("txtmpcpNormalcostcc"));
						newWomTlManpowercostplan.setMpcpTotalvalue(request.getParameter("txtmpcpTotalvaluecc"));
						CommonFunctions.debugMsg(newWomTlManpowercostplan.getMpcpManpowerid());
					}

					if (costInfoBean.getFormActionMode().equals("Save")) {
						existWomTlManpowercostplan = empCostService.createEmpEstimate(newWomTlManpowercostplan,
								oldWomTlManpowercostplan);
					} else {
						existWomTlManpowercostplan = empCostService.updateEmpEstimate(newWomTlManpowercostplan,
								oldWomTlManpowercostplan);
					}
					keyId = existWomTlManpowercostplan.getMpcpWoid();
					CommonFunctions.debugMsg(" WOM KEYID() " + keyId);

					httpSession.setAttribute(existWomTlManpowercostplan.getMpcpWoid(), existWomTlManpowercostplan);
				} else if (request.getParameter("Type").equals("Actual")) {
					CommonFunctions.debugMsg(" Inside Actual ");
					BAL_WomTlManpowercostactual existWomTlManpowercostactual = (BAL_WomTlManpowercostactual) httpSession
							.getAttribute("womTlManpowercostactual");

					BAL_WomTlManpowercostactual newWomTlManpowercostactual = new BAL_WomTlManpowercostactual();
					BAL_WomTlManpowercostactual oldWomTlManpowercostactual = new BAL_WomTlManpowercostactual();
					newWomTlManpowercostactual.setMpcsCreatedby(user.getUsrm_ccno());
					newWomTlManpowercostactual.setMpcsDoctype(docType);
					newWomTlManpowercostactual.setMpcsMaintwoid(docNo);

					newWomTlManpowercostactual = (BAL_WomTlManpowercostactual) UIUtils
							.setBeanProperties((Object) newWomTlManpowercostactual, request);

					CommonFunctions.debugMsg(request.getParameter("Skill"));

					costInfoBean.setMpcsDate(request.getParameter("dtempcsDate"));
					costInfoBean.setStartDate(request.getParameter("workStart"));
					costInfoBean.setEndDate(request.getParameter("workEnd"));
					CommonFunctions.debugMsg(costInfoBean.getMpcsDate());
					CommonFunctions.debugMsg(costInfoBean.getStartDate());
					CommonFunctions.debugMsg(costInfoBean.getEndDate());

					if (request.getParameter("Skill").equals("H")) {
						CommonFunctions.debugMsg(request.getParameter("cmbmpcpManpoweridcc"));

						costInfoBean.setMpcsDate(request.getParameter("dtempcsDatecc"));
						newWomTlManpowercostactual.setMpcsSkillflag("H");
						newWomTlManpowercostactual.setMpcsManpowerid(request.getParameter("cmbmpcsManpoweridcc"));
						newWomTlManpowercostactual.setMpcsSkillid(request.getParameter("cmbmpcsSkillidcc"));
						newWomTlManpowercostactual.setMpcsNormalwt(request.getParameter("txtmpcsNormalwtcc"));
						newWomTlManpowercostactual.setMpcsHolidaywt(request.getParameter("txtmpcsHolidaywtcc"));
						newWomTlManpowercostactual.setMpcsOtherwt(request.getParameter("txtmpcsOtherwtcc"));
						newWomTlManpowercostactual.setMpcsNormalrate(request.getParameter("txtmpcsNormalratecc"));
						newWomTlManpowercostactual.setMpcsHolidayrate(request.getParameter("txtmpcsHolidayratecc"));
						newWomTlManpowercostactual.setMpcsOtherrate(request.getParameter("txtmpcsOtherratecc"));
						newWomTlManpowercostactual.setMpcsTotalvalue(request.getParameter("txtmpcsTotalvaluecc"));
						// CommonFunctions.debugMsg(request.getParameter("dtempcsDatecc"));
						newWomTlManpowercostactual.setMpcsDate(request.getParameter("dtempcsDatecc"));
						newWomTlManpowercostactual.setMpcsActivity(request.getParameter("txampcsActivitycc"));
						newWomTlManpowercostactual.setMpcsRemarks(request.getParameter("txampcsRemarkscc"));

						CommonFunctions.debugMsg(newWomTlManpowercostactual.getMpcsManpowerid());
					}

					if (costInfoBean.getFormActionMode().equals("Save")) {
						existWomTlManpowercostactual = empCostService.createEmpActual(newWomTlManpowercostactual,
								oldWomTlManpowercostactual, costInfoBean);

					} else {
						existWomTlManpowercostactual = empCostService.updateEmpActual(newWomTlManpowercostactual,
								oldWomTlManpowercostactual, costInfoBean);
					}

					keyId = existWomTlManpowercostactual.getMpcsMaintwoid();
					CommonFunctions.debugMsg(" Maint WOM KEYID() " + keyId);
					httpSession.setAttribute(existWomTlManpowercostactual.getMpcsMaintwoid(),
							existWomTlManpowercostactual);
				}

				String formBeanIdentifier = "costInfoBean" + costInfoBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, costInfoBean);

				JSONObject mode = new JSONObject();
				mode.put("formMode", costInfoBean.getFormActionMode());
				JSONObject persistentData = new JSONObject();
				persistentData.put("bphmKeyid", keyId);
				persistentData.put("fromBean", formTypeIdentifier);

				JSONObject successData = new JSONObject();
				successData.put("msg", "Data Saved Successfully");
				successData.put("mode", costInfoBean.getFormMode());
				successData.put("keyId", keyId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				out.print(returnData.toString());

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "costInfoCreation");
				errMessage.put("fromMode", costInfoBean.getFormActionMode());
				out.print(errMessage.toString());

			} catch (BusinessApplicationExceptions e) {
				CommonFunctions.debugMsg("DNFKHDJFDK" + e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "costInfoCreation");
				out.print(errMessage.toString());
			} catch (Exception e) {
				CommonFunctions.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

	public static void writePageTotal(HttpServletResponse response, List<String[]> pageTotalDetail) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("inside write pagetotal");
		JSONObject jsonObject = new JSONObject();
		CommonFunctions.debugMsg("totalvalues" + pageTotalDetail.get(0)[0]);
		CommonFunctions.debugMsg("size" + pageTotalDetail.get(0).length);
		jsonObject.put("value", pageTotalDetail.get(0)[0]);

		if (pageTotalDetail.get(0).length == 2) {
			CommonFunctions.debugMsg("nofemp" + pageTotalDetail.get(0)[1]);
			jsonObject.put("noofemp", pageTotalDetail.get(0)[1]);
		}
		JSONObject empdetails = new JSONObject();
		empdetails.put("pageTotal", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(empdetails);
	}

	/*
	 * public static void writeEmpDetails(HttpServletResponse response,
	 * List<String[]> empDetails) throws IOException {
	 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
	 * response.getWriter(); CommonFunctions.debugMsg("inside write empdet"); //
	 * JSONArray jsonObject = JSONArray.fromObject(machineHirerachy); JSONObject
	 * jsonObject = new JSONObject(); jsonObject.put("empNo", empDetails.get(0)[0]);
	 * jsonObject.put("empName", empDetails.get(0)[1]); jsonObject.put("empNorRate",
	 * empDetails.get(0)[2]); jsonObject.put("empOtRate", empDetails.get(0)[3]);
	 * jsonObject.put("empCalloutRate", empDetails.get(0)[4]); JSONObject empdetails
	 * = new JSONObject(); empdetails.put("empDetails", jsonObject);
	 * CommonFunctions.debugMsg("completed"); out.print(empdetails); }
	 */

	public static void writeEmpDetails(HttpServletResponse response, List<String[]> empDetails) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("inside write empdet");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("empNo", empDetails.get(0)[0]);
		jsonObject.put("empName", empDetails.get(0)[1]);
		jsonObject.put("empCost", empDetails.get(0)[2] != null ? empDetails.get(0)[2] : "0");
		jsonObject.put("empNorRate", empDetails.get(0)[2] != null ? empDetails.get(0)[2] : "0");
		jsonObject.put("empOtRate", empDetails.get(0)[3] != null ? empDetails.get(0)[3] : "0");
		jsonObject.put("empCalloutRate", empDetails.get(0)[4] != null ? empDetails.get(0)[4] : "0");
		JSONObject empdetails = new JSONObject();
		empdetails.put("empDetails", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(empdetails);
	}

	public static void writeSpareDetails(HttpServletResponse response, List<String[]> empDetails) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("inside write empdet");
		// JSONArray jsonObject = JSONArray.fromObject(machineHirerachy);
		CommonFunctions.debugMsg(empDetails.get(0)[0]);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("spareNo", empDetails.get(0)[0]);
		jsonObject.put("spareName", empDetails.get(0)[1]);
		jsonObject.put("spareRate", empDetails.get(0)[2]);
		JSONObject empdetails = new JSONObject();
		empdetails.put("spareDetails", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(empdetails);
	}

	public static void writeServiceDetails(HttpServletResponse response, List<String[]> serviceDetails)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("inside write Service det");
		// JSONArray jsonObject = JSONArray.fromObject(machineHirerachy);
		CommonFunctions.debugMsg(serviceDetails.get(0)[0]);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("serviceCode", serviceDetails.get(0)[0]);
		jsonObject.put("serviceName", serviceDetails.get(0)[1]);
		JSONObject empdetails = new JSONObject();
		empdetails.put("serviceDetails", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(empdetails);
	}

	public static void writeUtilityDetails(HttpServletResponse response, List<String[]> serviceDetails)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg(serviceDetails.get(0)[0]);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("utilityCode", serviceDetails.get(0)[0]);
		jsonObject.put("utilityName", serviceDetails.get(0)[1]);
		jsonObject.put("utilityCost", serviceDetails.get(0)[2]);
		JSONObject empdetails = new JSONObject();
		empdetails.put("utilityDetails", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(empdetails);
	}

	public static void writeOtherDetails(HttpServletResponse response, List<String[]> otherDetails) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("other detsil" + otherDetails.get(0)[0]);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("otherCode", otherDetails.get(0)[0]);
		jsonObject.put("otherName", otherDetails.get(0)[1]);
		JSONObject otherdetails = new JSONObject();
		otherdetails.put("otherDetails", jsonObject);
		CommonFunctions.debugMsg("completed");
		out.print(otherdetails);
	}

	private void deleteEmpCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_CostInfoBean costInfoBean = (BAL_CostInfoBean) httpSession.getAttribute("CostInfoServletCostInfoBean");
			try {
				String keyid = "";
				CommonFunctions.debugMsg("delete emp cost");
				if (request.getParameter("Type").equals("Estimate")) {
					if (request.getParameter("FormName").equals("EmpCost")) {
						BAL_WomTlManpowercostplan existWomTlManpowercostplan = (BAL_WomTlManpowercostplan) httpSession
								.getAttribute("newSession");
						BAL_WomTlManpowercostplan newWomTlManpowercostplan = new BAL_WomTlManpowercostplan();
						newWomTlManpowercostplan.setMpcpCreatedby(user.getUsrm_ccno());
						newWomTlManpowercostplan = (BAL_WomTlManpowercostplan) UIUtils
								.setBeanProperties((Object) newWomTlManpowercostplan, request);
						newWomTlManpowercostplan.setMpcpWoid(request.getParameter("womid"));
						newWomTlManpowercostplan.setMpcpSkillid(request.getParameter("grdid"));
						newWomTlManpowercostplan.setMpcpManpowerid(request.getParameter("empid"));
						existWomTlManpowercostplan = empCostService.deleteEmpEstimate(newWomTlManpowercostplan);
						httpSession.setAttribute(existWomTlManpowercostplan.getMpcpWoid(), existWomTlManpowercostplan);
						httpSession.setAttribute("WomTlManpowercostplan", existWomTlManpowercostplan);
						keyid = existWomTlManpowercostplan.getMpcpWoid();
					} else if (request.getParameter("FormName").equals("SpareCost")) {
						BAL_WomTlSparecostplan existWomTlSparecostplan = (BAL_WomTlSparecostplan) httpSession
								.getAttribute("newSession");
						BAL_WomTlSparecostplan newWomTlSparecostplan = new BAL_WomTlSparecostplan();
						newWomTlSparecostplan.setWscpCreatedby(user.getUsrm_ccno());
						newWomTlSparecostplan = (BAL_WomTlSparecostplan) UIUtils
								.setBeanProperties((Object) newWomTlSparecostplan, request);
						newWomTlSparecostplan.setWscpWoid(request.getParameter("womid"));
						newWomTlSparecostplan.setWscpRequestedby(request.getParameter("reqid"));
						newWomTlSparecostplan.setWscpSparesid(request.getParameter("spareid"));
						existWomTlSparecostplan = empCostService.deleteSpareEstimate(newWomTlSparecostplan);
						httpSession.setAttribute(existWomTlSparecostplan.getWscpWoid(), existWomTlSparecostplan);
						httpSession.setAttribute("WomTlSparecostplan", existWomTlSparecostplan);
						keyid = existWomTlSparecostplan.getWscpWoid();
					} else if (request.getParameter("FormName").equals("ServiceCost")) {
						BAL_WomTlServicecostplan existWomTlServicecostplan = (BAL_WomTlServicecostplan) httpSession
								.getAttribute("newSession");
						BAL_WomTlServicecostplan newWomTlServicecostplan = new BAL_WomTlServicecostplan();
						newWomTlServicecostplan.setSvcpCreatedby(user.getUsrm_ccno());
						newWomTlServicecostplan = (BAL_WomTlServicecostplan) UIUtils
								.setBeanProperties((Object) newWomTlServicecostplan, request);
						newWomTlServicecostplan.setSvcpWoid(request.getParameter("womid"));
						newWomTlServicecostplan.setSvcpServiceid(request.getParameter("serviceid"));
						existWomTlServicecostplan = empCostService.deleteServiceEstimate(newWomTlServicecostplan);
						httpSession.setAttribute(existWomTlServicecostplan.getSvcpWoid(), existWomTlServicecostplan);
						httpSession.setAttribute("WomTlServicecostplan", existWomTlServicecostplan);
						keyid = existWomTlServicecostplan.getSvcpWoid();
					} else if (request.getParameter("FormName").equals("UtilityCost")) {
						BAL_WomTlUtilitycostplan existWomTlUtilitycostplan = (BAL_WomTlUtilitycostplan) httpSession
								.getAttribute("newSession");
						BAL_WomTlUtilitycostplan newWomTlUtilitycostplan = new BAL_WomTlUtilitycostplan();
						newWomTlUtilitycostplan.setUtcpCreatedby(user.getUsrm_ccno());
						newWomTlUtilitycostplan = (BAL_WomTlUtilitycostplan) UIUtils
								.setBeanProperties((Object) newWomTlUtilitycostplan, request);
						newWomTlUtilitycostplan.setUtcpWokeyid(request.getParameter("womid"));
						newWomTlUtilitycostplan.setUtcpRequestedby(request.getParameter("reqid"));
						newWomTlUtilitycostplan.setUtcpUtilitymstid(request.getParameter("utilityid"));
						existWomTlUtilitycostplan = empCostService.deleteUtilityEstimate(newWomTlUtilitycostplan);
						httpSession.setAttribute(existWomTlUtilitycostplan.getUtcpWokeyid(), existWomTlUtilitycostplan);
						httpSession.setAttribute("WomTlUtilitycostplan", existWomTlUtilitycostplan);
						keyid = existWomTlUtilitycostplan.getUtcpWokeyid();
					} else if (request.getParameter("FormName").equals("OtherCost")) {
						BAL_WomTlOthercostplan existWomTlOthercostplan = (BAL_WomTlOthercostplan) httpSession
								.getAttribute("newSession");
						BAL_WomTlOthercostplan newWomTlOthercostplan = new BAL_WomTlOthercostplan();
						newWomTlOthercostplan.setOtcpCreatedby(user.getUsrm_ccno());
						newWomTlOthercostplan = (BAL_WomTlOthercostplan) UIUtils
								.setBeanProperties((Object) newWomTlOthercostplan, request);
						newWomTlOthercostplan.setOtcpWoid(request.getParameter("womid"));
						newWomTlOthercostplan.setOtcpRequestedby(request.getParameter("reqid"));
						newWomTlOthercostplan.setOtcpOthercostmstid(request.getParameter("otherid"));
						existWomTlOthercostplan = empCostService.deleteOtherEstimate(newWomTlOthercostplan);
						httpSession.setAttribute(existWomTlOthercostplan.getOtcpWoid(), existWomTlOthercostplan);
						httpSession.setAttribute("WomTlOthercostplan", existWomTlOthercostplan);
						keyid = existWomTlOthercostplan.getOtcpWoid();
					}
				} else if (request.getParameter("Type").equals("Actual")) {
					if (request.getParameter("FormName").equals("EmpCost")) {
						BAL_WomTlManpowercostactual existWomTlManpowercostactual = (BAL_WomTlManpowercostactual) httpSession
								.getAttribute("newSession");
						BAL_WomTlManpowercostactual newWomTlManpowercostactual = new BAL_WomTlManpowercostactual();
						newWomTlManpowercostactual.setMpcsCreatedby(user.getUsrm_ccno());
						newWomTlManpowercostactual = (BAL_WomTlManpowercostactual) UIUtils
								.setBeanProperties((Object) newWomTlManpowercostactual, request);
						newWomTlManpowercostactual.setMpcsMaintwoid(request.getParameter("womid"));
						newWomTlManpowercostactual.setMpcsSkillid(request.getParameter("grdid"));
						newWomTlManpowercostactual.setMpcsManpowerid(request.getParameter("empid"));
						existWomTlManpowercostactual = empCostService.deleteEmpActual(newWomTlManpowercostactual);
						httpSession.setAttribute(existWomTlManpowercostactual.getMpcsMaintwoid(),
								existWomTlManpowercostactual);
						httpSession.setAttribute("WomTlManpowercostactual", existWomTlManpowercostactual);
						keyid = existWomTlManpowercostactual.getMpcsMaintwoid();
					} else if (request.getParameter("FormName").equals("SpareCost")) {
						BAL_WomTlSparecostactual existWomTlSparecostactual = (BAL_WomTlSparecostactual) httpSession
								.getAttribute("newSession");
						BAL_WomTlSparecostactual newWomTlSparecostactual = new BAL_WomTlSparecostactual();
						newWomTlSparecostactual.setWscaCreatedby(user.getUsrm_ccno());
						newWomTlSparecostactual = (BAL_WomTlSparecostactual) UIUtils
								.setBeanProperties((Object) newWomTlSparecostactual, request);
						newWomTlSparecostactual.setWscaWoid(request.getParameter("womid"));
						newWomTlSparecostactual.setWscaRequestedby(request.getParameter("reqid"));
						newWomTlSparecostactual.setWscaSparesid(request.getParameter("spareid"));
						existWomTlSparecostactual = empCostService.deleteSpareActual(newWomTlSparecostactual);
						httpSession.setAttribute(existWomTlSparecostactual.getWscaWoid(), existWomTlSparecostactual);
						httpSession.setAttribute("WomTlSparecostactual", existWomTlSparecostactual);
						keyid = existWomTlSparecostactual.getWscaWoid();
					} else if (request.getParameter("FormName").equals("ServiceCost")) {
						BAL_WomTlServicecostactual existWomTlServicecostactual = (BAL_WomTlServicecostactual) httpSession
								.getAttribute("newSession");
						BAL_WomTlServicecostactual newWomTlServicecostactual = new BAL_WomTlServicecostactual();
						newWomTlServicecostactual.setSvcaCreatedby(user.getUsrm_ccno());
						newWomTlServicecostactual = (BAL_WomTlServicecostactual) UIUtils
								.setBeanProperties((Object) newWomTlServicecostactual, request);
						newWomTlServicecostactual.setSvcaWoid(request.getParameter("womid"));
						CommonFunctions.debugMsg("serivceiddd" + request.getParameter("serviceid"));
						newWomTlServicecostactual.setSvcaServiceid(request.getParameter("serviceid"));
						existWomTlServicecostactual = empCostService.deleteServiceActual(newWomTlServicecostactual);
						httpSession.setAttribute(existWomTlServicecostactual.getSvcaWoid(),
								existWomTlServicecostactual);
						httpSession.setAttribute("WomTlServicecostactual", existWomTlServicecostactual);
						keyid = existWomTlServicecostactual.getSvcaWoid();
					} else if (request.getParameter("FormName").equals("UtilityCost")) {
						BAL_WomTlUtilitycostactual existWomTlUtilitycostactual = (BAL_WomTlUtilitycostactual) httpSession
								.getAttribute("newSession");
						BAL_WomTlUtilitycostactual newWomTlUtilitycostactual = new BAL_WomTlUtilitycostactual();
						newWomTlUtilitycostactual.setUtcaCreatedby(user.getUsrm_ccno());
						newWomTlUtilitycostactual = (BAL_WomTlUtilitycostactual) UIUtils
								.setBeanProperties((Object) newWomTlUtilitycostactual, request);
						newWomTlUtilitycostactual.setUtcaWokeyid(request.getParameter("womid"));
						newWomTlUtilitycostactual.setUtcaUtilitymstid(request.getParameter("utilityid"));
						newWomTlUtilitycostactual.setUtcaRequestedby(request.getParameter("reqid"));
						existWomTlUtilitycostactual = empCostService.deleteUtilityActual(newWomTlUtilitycostactual);
						httpSession.setAttribute(existWomTlUtilitycostactual.getUtcaWokeyid(),
								existWomTlUtilitycostactual);
						httpSession.setAttribute("WomTlUtilitycostactual", existWomTlUtilitycostactual);
						keyid = existWomTlUtilitycostactual.getUtcaWokeyid();
					} else if (request.getParameter("FormName").equals("OtherCost")) {
						BAL_WomTlOthercostactual existWomTlOthercostactual = (BAL_WomTlOthercostactual) httpSession
								.getAttribute("newSession");
						BAL_WomTlOthercostactual newWomTlOthercostactual = new BAL_WomTlOthercostactual();
						newWomTlOthercostactual.setOtcdCreatedby(user.getUsrm_ccno());
						newWomTlOthercostactual = (BAL_WomTlOthercostactual) UIUtils
								.setBeanProperties((Object) newWomTlOthercostactual, request);
						newWomTlOthercostactual.setOtcdWoid(request.getParameter("womid"));
						newWomTlOthercostactual.setOtcdRequestedby(request.getParameter("reqid"));
						newWomTlOthercostactual.setOtcdOthercostmstid(request.getParameter("otherid"));
						existWomTlOthercostactual = empCostService.deleteOtherActual(newWomTlOthercostactual);
						httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);
						httpSession.setAttribute("WomTlOthercostactual", existWomTlOthercostactual);
						keyid = existWomTlOthercostactual.getOtcdWoid();
					}
				}

				CommonFunctions.debugMsg("delete query exe");
				JSONObject successData = new JSONObject();
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
				successData.put("keyId", keyid);
				JSONObject returnData = new JSONObject();

				returnData.put("successData", successData);
				out.print(returnData.toString());

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "costInfoCreationException");
				errMessage.put("fromMode", costInfoBean.getFormActionMode());
				out.print(errMessage.toString());

			} catch (Exception e) {
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
		}
	}

	private void saveSpareCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonFunctions.debugMsg("Pcl Spares MAIN");

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			BAL_CostInfoBean costInfoBean = (BAL_CostInfoBean) httpSession.getAttribute("CostInfoServletCostInfoBean");
			if (costInfoBean == null)
				costInfoBean = new BAL_CostInfoBean();
			// CommonFunctions.debugMsg(request.getParameter("saveMode"));
			if (request.getParameter("saveMode").equals("Update"))
				costInfoBean.setFormActionMode("Update");
			else
				costInfoBean.setFormActionMode("Save");
			CommonFunctions.debugMsg("formBean Mode" + costInfoBean.getFormActionMode());

			String docType = request.getParameter("docType");
			String docNo = request.getParameter("woId");

			try {
				String keyId = "";
				CommonFunctions.debugMsg("Type=" + request.getParameter("Type"));

				if (request.getParameter("Type").equals("Estimate")) {
					if (request.getParameter("FormName").equals("SpareCost")) {
						BAL_WomTlSparecostplan existWomTlSparecostplan = (BAL_WomTlSparecostplan) httpSession
								.getAttribute("womTlSparecostplan");
						BAL_WomTlSparecostplan newWomTlSparecostplan = new BAL_WomTlSparecostplan();
						BAL_WomTlSparecostplan oldWomTlSparecostplan = new BAL_WomTlSparecostplan();
						newWomTlSparecostplan.setWscpCreatedby(user.getUsrm_ccno());

						newWomTlSparecostplan.setWscpDoctype(docType);
						newWomTlSparecostplan.setWscpWoid(docNo);

						newWomTlSparecostplan = (BAL_WomTlSparecostplan) UIUtils
								.setBeanProperties((Object) newWomTlSparecostplan, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlSparecostplan = empCostService.createSpareEstimate(newWomTlSparecostplan,
									oldWomTlSparecostplan);
						else
							existWomTlSparecostplan = empCostService.updateSpareEstimate(newWomTlSparecostplan,
									oldWomTlSparecostplan);

						keyId = existWomTlSparecostplan.getWscpWoid();
						httpSession.setAttribute(existWomTlSparecostplan.getWscpWoid(), existWomTlSparecostplan);
					} else if (request.getParameter("FormName").equals("ServiceCost")) {
						CommonFunctions.debugMsg("Insider Service Cost");
						BAL_WomTlServicecostplan existWomTlServicecostplan = (BAL_WomTlServicecostplan) httpSession
								.getAttribute("WomTlServicecostplan");
						BAL_WomTlServicecostplan newWomTlServicecostplan = new BAL_WomTlServicecostplan();
						BAL_WomTlServicecostplan oldWomTlServicecostplan = new BAL_WomTlServicecostplan();
						newWomTlServicecostplan.setSvcpCreatedby(user.getUsrm_ccno());

						newWomTlServicecostplan.setSvcpDoctype(docType);
						newWomTlServicecostplan.setSvcpWoid(docNo);

						newWomTlServicecostplan = (BAL_WomTlServicecostplan) UIUtils
								.setBeanProperties((Object) newWomTlServicecostplan, request);
						CommonFunctions.debugMsg("job des" + newWomTlServicecostplan.getSvcpJobdescription());

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlServicecostplan = empCostService.createServiceEstimate(newWomTlServicecostplan,
									oldWomTlServicecostplan);
						else
							existWomTlServicecostplan = empCostService.updateServiceEstimate(newWomTlServicecostplan,
									oldWomTlServicecostplan);

						keyId = existWomTlServicecostplan.getSvcpWoid();
						httpSession.setAttribute(existWomTlServicecostplan.getSvcpWoid(), existWomTlServicecostplan);
					} else if (request.getParameter("FormName").equals("UtilityCost")) {
						CommonFunctions.debugMsg("Insider Utility Cost");
						BAL_WomTlUtilitycostplan existWomTlUtilitycostplan = (BAL_WomTlUtilitycostplan) httpSession
								.getAttribute("WomTlUtilitycostplan");
						BAL_WomTlUtilitycostplan newWomTlUtilitycostplan = new BAL_WomTlUtilitycostplan();
						BAL_WomTlUtilitycostplan oldWomTlUtilitycostplan = new BAL_WomTlUtilitycostplan();
						newWomTlUtilitycostplan.setUtcpCreatedby(user.getUsrm_ccno());

						newWomTlUtilitycostplan.setUtcpDoctype(docType);
						newWomTlUtilitycostplan.setUtcpWokeyid(docNo);

						newWomTlUtilitycostplan = (BAL_WomTlUtilitycostplan) UIUtils
								.setBeanProperties((Object) newWomTlUtilitycostplan, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlUtilitycostplan = empCostService.createUtilityEstimate(newWomTlUtilitycostplan,
									oldWomTlUtilitycostplan);
						else
							existWomTlUtilitycostplan = empCostService.updateUtilityEstimate(newWomTlUtilitycostplan,
									oldWomTlUtilitycostplan);

						keyId = existWomTlUtilitycostplan.getUtcpWokeyid();
						httpSession.setAttribute(existWomTlUtilitycostplan.getUtcpWokeyid(), existWomTlUtilitycostplan);
					} else if (request.getParameter("FormName").equals("OtherCost")) {
						CommonFunctions.debugMsg("Insider Other Cost");
						BAL_WomTlOthercostplan existWomTlOthercostplan = (BAL_WomTlOthercostplan) httpSession
								.getAttribute("WomTlOthercostplan");
						BAL_WomTlOthercostplan newWomTlOthercostplan = new BAL_WomTlOthercostplan();
						BAL_WomTlOthercostplan oldWomTlOthercostplan = new BAL_WomTlOthercostplan();
						newWomTlOthercostplan.setOtcpCreatedby(user.getUsrm_ccno());

						newWomTlOthercostplan.setOtcpDoctype(docType);
						newWomTlOthercostplan.setOtcpWoid(docNo);

						newWomTlOthercostplan = (BAL_WomTlOthercostplan) UIUtils
								.setBeanProperties((Object) newWomTlOthercostplan, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlOthercostplan = empCostService.createOtherEstimate(newWomTlOthercostplan,
									oldWomTlOthercostplan);
						else
							existWomTlOthercostplan = empCostService.updateOtherEstimate(newWomTlOthercostplan,
									oldWomTlOthercostplan);

						keyId = existWomTlOthercostplan.getOtcpWoid();
						httpSession.setAttribute(existWomTlOthercostplan.getOtcpWoid(), existWomTlOthercostplan);
					}
				}

				else if (request.getParameter("Type").equals("Actual")) {
					if (request.getParameter("FormName").equals("SpareCost")) {
						BAL_WomTlSparecostactual existWomTlSparecostactual = (BAL_WomTlSparecostactual) httpSession
								.getAttribute("WomTlSparecostactual");
						BAL_WomTlSparecostactual newWomTlSparecostactual = new BAL_WomTlSparecostactual();
						BAL_WomTlSparecostactual oldWomTlSparecostactual = new BAL_WomTlSparecostactual();
						newWomTlSparecostactual.setWscaCreatedby(user.getUsrm_ccno());

						newWomTlSparecostactual.setWscaDoctype(docType);
						newWomTlSparecostactual.setWscaWoid(docNo);

						newWomTlSparecostactual = (BAL_WomTlSparecostactual) UIUtils
								.setBeanProperties((Object) newWomTlSparecostactual, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlSparecostactual = empCostService.createSpareActual(newWomTlSparecostactual,
									oldWomTlSparecostactual);
						else
							existWomTlSparecostactual = empCostService.updateSpareActual(newWomTlSparecostactual,
									oldWomTlSparecostactual);

						keyId = existWomTlSparecostactual.getWscaWoid();
						CommonFunctions.debugMsg(" WOM KEYID() " + keyId);
						httpSession.setAttribute(existWomTlSparecostactual.getWscaWoid(), existWomTlSparecostactual);
					} else if (request.getParameter("FormName").equals("ServiceCost")) {
						BAL_WomTlServicecostactual existWomTlServicecostactual = (BAL_WomTlServicecostactual) httpSession
								.getAttribute("WomTlServicecostactual");
						BAL_WomTlServicecostactual newWomTlServicecostactual = new BAL_WomTlServicecostactual();
						BAL_WomTlServicecostactual oldWomTlServicecostactual = new BAL_WomTlServicecostactual();
						newWomTlServicecostactual.setSvcaCreatedby(user.getUsrm_ccno());

						newWomTlServicecostactual.setSvcaDoctype(docType);
						newWomTlServicecostactual.setSvcaWoid(docNo);

						newWomTlServicecostactual = (BAL_WomTlServicecostactual) UIUtils
								.setBeanProperties((Object) newWomTlServicecostactual, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlServicecostactual = empCostService.createServiceActual(newWomTlServicecostactual,
									oldWomTlServicecostactual);
						else
							existWomTlServicecostactual = empCostService.updateServiceActual(newWomTlServicecostactual,
									oldWomTlServicecostactual);

						keyId = existWomTlServicecostactual.getSvcaWoid();
						CommonFunctions.debugMsg(" WOM KEYID() " + keyId);
						httpSession.setAttribute(existWomTlServicecostactual.getSvcaWoid(),
								existWomTlServicecostactual);
					} else if (request.getParameter("FormName").equals("UtilityCost")) {
						BAL_WomTlUtilitycostactual existWomTlUtilitycostactual = (BAL_WomTlUtilitycostactual) httpSession
								.getAttribute("WomTlUtilitycostactual");
						BAL_WomTlUtilitycostactual newWomTlUtilitycostactual = new BAL_WomTlUtilitycostactual();
						BAL_WomTlUtilitycostactual oldWomTlUtilitycostactual = new BAL_WomTlUtilitycostactual();
						newWomTlUtilitycostactual.setUtcaCreatedby(user.getUsrm_ccno());

						newWomTlUtilitycostactual.setUtcaDoctype(docType);
						newWomTlUtilitycostactual.setUtcaWokeyid(docNo);

						newWomTlUtilitycostactual = (BAL_WomTlUtilitycostactual) UIUtils
								.setBeanProperties((Object) newWomTlUtilitycostactual, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlUtilitycostactual = empCostService.createUtilityActual(newWomTlUtilitycostactual,
									oldWomTlUtilitycostactual);
						else
							existWomTlUtilitycostactual = empCostService.updateUtilityActual(newWomTlUtilitycostactual,
									oldWomTlUtilitycostactual);

						keyId = existWomTlUtilitycostactual.getUtcaWokeyid();
						httpSession.setAttribute(existWomTlUtilitycostactual.getUtcaWokeyid(),
								existWomTlUtilitycostactual);
					} else if (request.getParameter("FormName").equals("OtherCost")) {
						BAL_WomTlOthercostactual existWomTlOthercostactual = (BAL_WomTlOthercostactual) httpSession
								.getAttribute("WomTlOthercostactual");
						BAL_WomTlOthercostactual newWomTlOthercostactual = new BAL_WomTlOthercostactual();
						BAL_WomTlOthercostactual oldWomTlOthercostactual = new BAL_WomTlOthercostactual();
						newWomTlOthercostactual.setOtcdCreatedby(user.getUsrm_ccno());

						newWomTlOthercostactual.setOtcdDoctype(docType);
						newWomTlOthercostactual.setOtcdWoid(docNo);

						newWomTlOthercostactual = (BAL_WomTlOthercostactual) UIUtils
								.setBeanProperties((Object) newWomTlOthercostactual, request);

						if (costInfoBean.getFormActionMode().equals("Save"))
							existWomTlOthercostactual = empCostService.createOtherActual(newWomTlOthercostactual,
									oldWomTlOthercostactual);
						else
							existWomTlOthercostactual = empCostService.updateOtherActual(newWomTlOthercostactual,
									oldWomTlOthercostactual);

						keyId = existWomTlOthercostactual.getOtcdWoid();
						httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);
					}
				}

				String formBeanIdentifier = "costInfoBean" + costInfoBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, costInfoBean);

				JSONObject mode = new JSONObject();
				mode.put("formMode", costInfoBean.getFormActionMode());
				JSONObject persistentData = new JSONObject();
				persistentData.put("bphmKeyid", keyId);
				persistentData.put("fromBean", formTypeIdentifier);

				JSONObject successData = new JSONObject();
				successData.put("msg", "Data Saved Successfully");
				successData.put("mode", costInfoBean.getFormMode());
				successData.put("keyId", keyId);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				out.print(returnData.toString());

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "costInfoCreation");
				errMessage.put("fromMode", costInfoBean.getFormActionMode());
				out.print(errMessage.toString());

			} catch (BusinessApplicationExceptions e) {
				CommonFunctions.debugMsg("DNFKHDJFDK" + e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "costInfoCreation");
				CommonFunctions.debugMsg("after vakludakjsk" + errMessage.toString());
				out.print(errMessage.toString());
			} catch (Exception e) {
				CommonFunctions.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}
}
