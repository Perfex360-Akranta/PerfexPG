package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.akranta.tpm.Exceptions.PlanConfigExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.BAL_PlanConfigurationBean;
import com.akranta.tpm.bean.BAL_WOResponsibilityBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlProductionplan;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.BAL_PlmTlWorespdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.service.BAL_PlmTlPlanconfigurationService;
import com.akranta.tpm.service.BAL_PlmTlPlanconfigurationService;
import com.akranta.tpm.service.BAL_PlmTlWorespmstService;
import com.akranta.tpm.service.impl.BAL_PlmTlPlanconfigurationServiceImpl;
import com.akranta.tpm.service.impl.BAL_PlmTlWorespmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

/**
 * Servlet implementation class PlanConfigurationServlet
 */

public class BAL_PlanConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	BAL_PlmTlPlanconfigurationService plmTlPlanconfigurationService;
	BAL_PlmTlWorespmstService plmTlWorespmstService;

	// PlanConfigurationBean planConfigurationBean = new PlanConfigurationBean();
	public BAL_PlanConfigurationServlet() {
		super();
		// TODO Auto-generated constructor stub
		/*
		 * try { plmTlPlanconfigurationService = new
		 * PlmTlPlanconfigurationServiceImpl(); plmTlWorespmstService = new
		 * PlmTlWorespmstServiceImpl(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initilizeInputMode(FormModes mode, HttpServletRequest request, HttpSession httpSession)
			throws Exception {

		System.out.println("Find Mode  " + mode);

		BAL_PlanConfigurationBean planConfigurationServletbean = new BAL_PlanConfigurationBean(mode);

		String docno = request.getParameter("docno");

		BAL_PlmTlPlanconfiguration plmTlPlanconfiguration = new BAL_PlmTlPlanconfiguration();
		if (mode.equals(FormModes.create)) {
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			// user.getUsrm_ccno();
			System.out.println("input called" + user.getUsrm_ccno());
			plmTlPlanconfiguration.setPplcCreatedby(user.getUsrm_ccno());
			System.out.println("c called" + plmTlPlanconfiguration.getPplcCreatedon());

			request.setAttribute("plmTlPlanconfiguration", plmTlPlanconfiguration);
		}
		httpSession.removeAttribute("plmTlPlanconfiguration_Servlet");
		if (UIUtils.isValidKeyId(docno)) {
			try {

				// plmTlPlanconfiguration = plmTlPlanconfigurationService.getFillValue(docno);
				System.out.println("Createdby :" + plmTlPlanconfiguration.getPplcCreatedby());
				// PlanConfigurationBean planConfigurationBean= new
				// PlanConfigurationBean(mode);*/

				httpSession.setAttribute("plmTlPlanconfiguration_Servlet", plmTlPlanconfiguration);

				request.setAttribute("plmTlPlanconfiguration", plmTlPlanconfiguration);
				// request.setAttribute("planConfigurationBean",planConfigurationBean);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		httpSession.removeAttribute("planConfigurationBean");
		httpSession.setAttribute("planConfigurationBean", planConfigurationServletbean);
		// httpSession.removeAttribute("actionpassed");//clear session of mode stored
		// System.out.println("chk disableForm status
		// "+planConfigurationServlet.getDisableForm());
		request.setAttribute("planConfigurationBean", planConfigurationServletbean);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = UIUtils.getActionPart(request);

		if (action == null) {
			System.out.println(
					"action is null - request did not carry expected parameters (check upstream forward from subForm_input.mselect)");
			return;
		}

		ComboFilter comboFilter = new ComboFilter();
		try {
			plmTlPlanconfigurationService = (BAL_PlmTlPlanconfigurationServiceImpl) UIUtils.getServiceObject(request,
					"BAL_PlmTlPlanconfigurationServiceImpl");
			plmTlWorespmstService = (BAL_PlmTlWorespmstServiceImpl) UIUtils.getServiceObject(request,
					"BAL_PlmTlWorespmstServiceImpl");

			HttpSession httpSession = request.getSession(false);
			
			plmTlWorespmstService.BAL_PlmTlWorespmstServiceImplJwt(
				    (String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
				);
			plmTlPlanconfigurationService.BAL_PlmTlPlanconfigurationImplJwt(
				    (String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
				);

			System.out.println(" action " + action);

			String dispatchUrl = null;
			System.out.println("action " + action);
			response.setContentType("text/html");
			response.setContentType("text/json");

			if (action.equals("planConfig_input.plnconfig")) {
				// FormModes mode = (FormModes) httpSession.getAttribute("PlanConfigFormMode");

				String modeStr = request.getParameter(ReqtParamNameConst.FORM_MODE);
				System.out.println("dsd" + modeStr);
				httpSession.removeAttribute("PlanConfigFormMode");

				FormModes mode = FormModes.create;

				if (modeStr == null || (modeStr != null && modeStr.equals(FormModeConsts.create)))
					mode = FormModes.create;
				else if (modeStr.equals(FormModeConsts.modify))
					mode = FormModes.modify;
				else
					mode = FormModes.view;

				httpSession.setAttribute("PlanConfigFormMode", mode);
				initilizeInputMode(mode, request, httpSession);

				request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);

				System.out.println("workhrsinpt :" + request.getAttribute(ReqtParamNameConst.FORM_MODE));

				dispatchUrl = "/pages/PlanConfiguration.jsp";

			} else if (action.equals("planConfig_modify.plnconfig")) {
				httpSession.setAttribute("PlanConfigFormMode", FormModes.modify);

				dispatchUrl = "/pages/PlanConfiguration.jsp";
			} else if (action.equals("planConfig_view.plnconfig")) {
				httpSession.setAttribute("PlanConfigFormMode", FormModes.view);

				dispatchUrl = "/pages/PlanConfiguration.jsp";
			} else if (action.equals("prodplanentry_input.plnconfig")) {
				PcsTlProductionplan pcsTlProductionplan = new PcsTlProductionplan();
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				pcsTlProductionplan.setPrplPlannedby(user.getUsrm_ccno());
				request.setAttribute("pcsTlProductionplan", pcsTlProductionplan);
				dispatchUrl = "/pages/pcs/ProdPlanningEntry.jsp";

			} else if (action.equals("functionalLoc.plnconfig")) {
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				// functLocFieldNameBean.setFactory("cmbPplcFactoryid");
				functLocFieldNameBean.setSbu("cmbPplcFactoryid");// sbu
				functLocFieldNameBean.setSection("cmbPplcSectionid");
				functLocFieldNameBean.setCell("cmbPplcCellid");
				functLocFieldNameBean.setMachine("cmbPplcMachineid");
				functLocFieldNameBean.setFunctionalLocId("cmbprplFlid");
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);

				FormModes formModes = (FormModes) httpSession.getAttribute("formMode");
				if (formModes == FormModes.completion)
					formModes = FormModes.view;
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
				dispatchUrl = null;
			} else if (action.equals("planEntry_getCol.plnconfig")) {
				PrintWriter out = response.getWriter();
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanEntryColmodel", "colModel");
				out.println(colModel);
			} else if (action.equals("planEntry_getData.plnconfig")) {
				String machineId = request.getParameter("machineId");
				String productId = request.getParameter("productId");
				String entryDate = request.getParameter("planDate");
				CommonFilter commonFilter = populateCommonFilter(request, "PlanEntryCommonFilter", true);
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				List<String[]> planEntryList = plmTlPlanconfigurationService.getPlanEntry(machineId, entryDate,
						productId, commonFilter);
				CommonFunctions.debugMsg(planEntryList.size());
				jsonObject = UIUtils.convertToJqGridTableObject(planEntryList, request, 0, 1,
						commonFilter.getTotalRecordCnt());
				out.println(jsonObject);
			} else if (action.equals("prodplanentry_save.plnconfig")) {
				saveProductionPlan(request, response);
			} else if (action.equals("delPmExist_input.plnconfig")) {
				httpSession.removeAttribute("delConfirm");
				String delConfirm = request.getParameter("del");
				httpSession.setAttribute("delConfirm", delConfirm);
				BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration = (BAL_PlmTlPlanconfiguration) httpSession
						.getAttribute("existplanData");
				BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration = (BAL_PlmTlPlanconfiguration) httpSession
						.getAttribute("newplanData");
				BAL_PlanConfigurationBean planConfigurationBean = (BAL_PlanConfigurationBean) httpSession
						.getAttribute("planConfigurationBean");

				createUpdate(newPlmTlPlanconfiguration, existPlmTlPlanconfiguration, planConfigurationBean, request,
						response);
			} else if (action.equals("year_pop.plnconfig")) {

				String datastr = request.getParameter("datstr");
				String planConfigKey = request.getParameter("keyId");
				CommonFunctions.debugMsg("RAW keyId param: [" + planConfigKey + "]");
				request.setAttribute("datastr", datastr);
				request.setAttribute("rowmachId", request.getParameter("rowmachId"));
				request.setAttribute("keyId", request.getParameter("keyId"));
				request.setAttribute("assmId", request.getParameter("assmId"));

				BAL_PlmTlPlanconfiguration plmTlPlanconfiguration = new BAL_PlmTlPlanconfiguration();
				if (UIUtils.isValidKeyId(planConfigKey)) {
					plmTlPlanconfiguration = plmTlPlanconfigurationService.getplanConfigdata(planConfigKey);
					plmTlPlanconfiguration.setPplcMonthly(plmTlPlanconfiguration.getPplcMonthly().substring(0, 11));
					plmTlPlanconfiguration.setPplcQuarterly(plmTlPlanconfiguration.getPplcQuarterly().substring(0, 11));
					plmTlPlanconfiguration
							.setPplcHalfyearly(plmTlPlanconfiguration.getPplcHalfyearly().substring(0, 11));
					if (plmTlPlanconfiguration.getPplcYearly().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly(" ");
					else
						plmTlPlanconfiguration.setPplcYearly(plmTlPlanconfiguration.getPplcYearly().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly2().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly2(" ");
					else
						plmTlPlanconfiguration.setPplcYearly2(plmTlPlanconfiguration.getPplcYearly2().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly3().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly3(" ");
					else
						plmTlPlanconfiguration.setPplcYearly3(plmTlPlanconfiguration.getPplcYearly3().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly4().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly4(" ");
					else
						plmTlPlanconfiguration.setPplcYearly4(plmTlPlanconfiguration.getPplcYearly4().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly5().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly5(" ");
					else
						plmTlPlanconfiguration.setPplcYearly5(plmTlPlanconfiguration.getPplcYearly5().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly6().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly6(" ");
					else
						plmTlPlanconfiguration.setPplcYearly6(plmTlPlanconfiguration.getPplcYearly6().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly7().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly7(" ");
					else
						plmTlPlanconfiguration.setPplcYearly7(plmTlPlanconfiguration.getPplcYearly7().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly8().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly8(" ");
					else
						plmTlPlanconfiguration.setPplcYearly8(plmTlPlanconfiguration.getPplcYearly8().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly9().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly9(" ");
					else
						plmTlPlanconfiguration.setPplcYearly9(plmTlPlanconfiguration.getPplcYearly9().substring(3, 11));
					if (plmTlPlanconfiguration.getPplcYearly10().equals("31-Dec-2100 00:00:00"))
						plmTlPlanconfiguration.setPplcYearly10(" ");
					else
						plmTlPlanconfiguration
								.setPplcYearly10(plmTlPlanconfiguration.getPplcYearly10().substring(3, 11));

				}
				request.setAttribute("plmTlPlanconfiguration", plmTlPlanconfiguration);
				dispatchUrl = "/pages/planConfigPop.jsp";
			}

			else if (action.equals("wrkOdrResp_input.plnconfig")) {

				/*
				 * String planKeyId =request.getParameter("plankeyId");
				 * request.setAttribute("planKeyId",planKeyId);
				 * httpSession.setAttribute("planKeyId",planKeyId);
				 */

				String factId = request.getParameter("fctid");
				String machId = request.getParameter("machId");
				String cellId = request.getParameter("cellid");
				String sectid = request.getParameter("sectid");
				String grdmachId = request.getParameter("grdmachId");
				String workMstKeyId = request.getParameter("workMstKeyId");   // <-- ADD THIS LINE
				httpSession.setAttribute("factId", factId);
				httpSession.setAttribute("machId", machId);
				httpSession.setAttribute("cellId", cellId);
				httpSession.setAttribute("sectid", sectid);
				CommonFunctions.debugMsg(" machId  ----------- " + machId);
				CommonFunctions.debugMsg(" workMstKeyId  ----------- " + workMstKeyId);   // <-- ADD THIS LINE (for debugging)
				request.setAttribute("machId", machId);
				//httpSession.setAttribute("WorRespmach", request.getParameter("grdmachId"));
				  httpSession.setAttribute("woResrowmachId", request.getParameter("grdmachId"));
				  httpSession.setAttribute("mstKeyId", workMstKeyId);   // <-- ADD THIS LINE (the actual fix)
				  
				dispatchUrl = "/pages/workRespPop.jsp";
			} /*
				 * else if (action.equals("wrkOdrResp_getCol.plnconfig")) { PrintWriter out =
				 * response.getWriter(); String woRespkeyId =
				 * request.getParameter("workMstKeyId"); String wORESmachineId =
				 * request.getParameter("parentId"); String WorRespmach = (String)
				 * httpSession.getAttribute("WorRespmach");
				 * UIUtils.displayRequestParamsValue(request); request.setAttribute("rowmachId",
				 * WorRespmach); request.setAttribute("keyId",
				 * request.getParameter("workMstKeyId")); httpSession.setAttribute("woMstkeyId",
				 * request.getParameter("workMstKeyId"));
				 * httpSession.setAttribute("woResrowmachId", WorRespmach);
				 * httpSession.setAttribute("mstKeyId", woRespkeyId);
				 * out.println(UIUtils.getPropertyValue(
				 * "com.akranta.tpm.resources.PlanConfigurationcolmodel",
				 * "colModelWorkOrderResponibility")); }
				 */
			
			
		/*	else if (action.equals("wrkOdrResp_getCol.plnconfig")) {
				PrintWriter out = response.getWriter();
				String woRespkeyId = request.getParameter("workMstKeyId");
				String wORESmachineId = request.getParameter("parentId");
				UIUtils.displayRequestParamsValue(request);

				// Only overwrite session values if this request actually carries them
				if (woRespkeyId != null && !woRespkeyId.trim().isEmpty() && !"undefined".equals(woRespkeyId)) {
					httpSession.setAttribute("woMstkeyId", woRespkeyId);
					httpSession.setAttribute("mstKeyId", woRespkeyId);
					request.setAttribute("keyId", woRespkeyId);
				}

				// Remove the WorRespmach read/overwrite entirely - that attribute is never set elsewhere
				// (previously this null value was wiping out woResrowmachId set by wrkOdrResp_input.plnconfig)

				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
						"colModelWorkOrderResponibility"));
			}*/
			
			else if (action.equals("wrkOdrResp_getCol.plnconfig")) {
			    PrintWriter out = response.getWriter();
			    String woRespkeyId = request.getParameter("workMstKeyId");
			    UIUtils.displayRequestParamsValue(request);

			    if (UIUtils.isValidKeyId(woRespkeyId)) {
			        httpSession.setAttribute("woMstkeyId", woRespkeyId);
			        httpSession.setAttribute("mstKeyId", woRespkeyId);
			        request.setAttribute("keyId", woRespkeyId);
			    }

			    out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
			            "colModelWorkOrderResponibility"));
			}
			/*
			 * else if(action.equals("wrkOdrResp_getData.plnconfig")){ PrintWriter out =
			 * response.getWriter(); String workrepmstkeyid =
			 * (String)httpSession.getAttribute("mstKeyId"); String workrepmachineId =
			 * (String)httpSession.getAttribute("woResrowmachId"); String factId =
			 * (String)httpSession.getAttribute("factidwo"); String sectId =
			 * (String)httpSession.getAttribute("sectid"); String cellid =
			 * (String)httpSession.getAttribute("cellid"); String machId =
			 * (String)httpSession.getAttribute("machId"); String costcenterid =
			 * (String)httpSession.getAttribute("costcenterid"); String optedValue =
			 * (String)httpSession.getAttribute("pwrmlevel");
			 * CommonFunctions.debugMsg("wORESmachineId    ---"+workrepmachineId);
			 * CommonFunctions.debugMsg("workrepmstkeyid    ---"+workrepmstkeyid);
			 * //if(!UIUtils.isValidKeyId(workrepmstkeyid)){
			 * CommonFunctions.debugMsg("IF    ---"+workrepmstkeyid); List<String []>
			 * getwoRespGriddata =
			 * plmTlPlanconfigurationService.getwoRespGriddata(workrepmstkeyid); JSONObject
			 * getGriddataPlan =
			 * UIUtils.convertToJqGridTableObject(getwoRespGriddata,request,0,0);
			 * out.println(getGriddataPlan); System.out.println(getGriddataPlan); } else {
			 * CommonFunctions.debugMsg("ELSE    ---"+workrepmstkeyid); List<String []>
			 * getwoRespGriddata =
			 * plmTlPlanconfigurationService.getwoRespGridadd(workrepmstkeyid); JSONObject
			 * getGriddataPlan =
			 * UIUtils.convertToJqGridTableObject(getwoRespGriddata,request,0,0);
			 * out.println(getGriddataPlan); System.out.println(getGriddataPlan); }
			 * 
			 * 
			 * }
			 */
			
			
			else if(action.equals("wrkOdrResp_getData.plnconfig")){
			    PrintWriter out = response.getWriter();
			    CommonFunctions.debugMsg("SESSION ID for action [" + action + "] ---> " + httpSession.getId());
			    String workrepmstkeyid = (String)httpSession.getAttribute("mstKeyId");
			    String workrepmachineId = (String)httpSession.getAttribute("woResrowmachId");
			    CommonFunctions.debugMsg("wORESmachineId    ---"+workrepmachineId);
			    CommonFunctions.debugMsg("workrepmstkeyid    ---"+workrepmstkeyid);

			    if (!UIUtils.isValidKeyId(workrepmstkeyid)) {
			        CommonFunctions.debugMsg("workrepmstkeyid invalid/null - returning empty grid, skipping DB call");
			        JSONObject emptyGrid = UIUtils.convertToJqGridTableObject(new ArrayList<String[]>(), request, 0, 0);
			        out.println(emptyGrid);
			        return;
			    }

			    List<String []> getwoRespGriddata  = plmTlPlanconfigurationService.getwoRespGriddata(workrepmstkeyid);
			    JSONObject getGriddataPlan = UIUtils.convertToJqGridTableObject(getwoRespGriddata,request,0,0);
			    out.println(getGriddataPlan);
			    System.out.println(getGriddataPlan);
			}
			else if (action.equals("workorderRespon_save.plnconfig")) {

				// comTxt =
				// request.getParameter("comTxt");tradeidresponsibilitycmbPwrdtradeidcmbPwrdempid
				// comBdNo = request.getParameter("comBy");
				// comEnteredBy = request.getParameter("bdNo");
				String tradeId = request.getParameter("cmbPwrdtradeid");
				String assemIdd = request.getParameter("cmbPwrdempid");
				// String womKeyId = request.getParameter("txtPwrmkeyid");

				httpSession.setAttribute("womKeyId", request.getParameter("txtPwrmkeyid"));
				httpSession.setAttribute("tradeid", request.getParameter("cmbPwrdtradeid"));
				httpSession.setAttribute("responsibility", request.getParameter("cmbPwrdempid"));

				// CommonFunctions.debugMsg("save form WorkResp :"+womKeyId);
				// if(UIUtils.isValidKeyId(tradeId) ){
				// if(UIUtils.isValidKeyId(assemIdd)){
				BAL_WOResponsibilityBean wOResponsibilityBean = new BAL_WOResponsibilityBean();
				saveWorkResp(request, response, wOResponsibilityBean);
				// }
				// }
			}

			else if (action.equals("planConfig_getCol.plnconfig")) {
				System.out.println("inside getcol action of PlanConfiguration");
				String modeCald = request.getParameter("hdnMode");
				String chkvalue = request.getParameter("chkValue");
				CommonFunctions.debugMsg("chkvalue  " + chkvalue);
				httpSession.setAttribute("actionpassed", modeCald);

				PrintWriter out = response.getWriter();

				if (!UIUtils.isValidKeyId(chkvalue) || chkvalue.equals("MCHM")) {
					CommonFunctions.debugMsg("chkvalue--if  " + chkvalue);
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
							"colModelPlanConfigEqp"));
				} else {
					CommonFunctions.debugMsg("chkvalue--else  " + chkvalue);
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
							"colModelPlanConfigAssm"));
				}
			} else if (action.equals("planConfig_getData.plnconfig")) {

				System.out.println("mak   " + request.getParameter("fctid"));
				String chkvalue = request.getParameter("chkValue");
				CommonFunctions.debugMsg("chkvalue_getdata  " + chkvalue);
				String factId = request.getParameter("fctid");
				String machId = request.getParameter("machId");
				String cellId = request.getParameter("cellid");
				String chkValue = request.getParameter("chkValue");
				PrintWriter out = response.getWriter();
				List<String[]> getGriddata = plmTlPlanconfigurationService.getdataPlanConfig(factId, machId, cellId,
						chkValue);
				// CommonFunctions.debugMsg("GD DATA SIZE "+getGriddata.size());
				JSONObject getGriddataPlan = UIUtils.convertToJqGridTableObject(getGriddata, request, 0, 0);
				out.println(getGriddataPlan);
				System.out.println(getGriddataPlan);
			}

			/* for Functional Location */
			/*
			 * else if(action.equals("functionalLoc.plnconfig")){
			 * 
			 * String selectedChk = request.getParameter("chk");
			 * //CommonFunctions.debugMsg("selectedChk    :"+ selectedChk);
			 * FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			 * functLocFieldNameBean.setFactory("cmbPplcFactoryid");
			 * functLocFieldNameBean.setSection("cmbPplcSectionid");
			 * functLocFieldNameBean.setCell("cmbPplcCellid");
			 * functLocFieldNameBean.setMachine("cmbPplcMachineid");
			 * 
			 * if(UIUtils.isValidKeyId(selectedChk)){ CommonFunctions.debugMsg("inside if");
			 * if(selectedChk.equals("mchm") ){ CommonFunctions.debugMsg("eqp");
			 * functLocFieldNameBean.setCellMandatory(true);
			 * //functLocFieldNameBean.setMachMandatory(false); } else {
			 * CommonFunctions.debugMsg("assm");
			 * functLocFieldNameBean.setCellMandatory(true);
			 * functLocFieldNameBean.setMachMandatory(true); }
			 * 
			 * } FormModes formModes =
			 * (FormModes)httpSession.getAttribute("PlanConfigFormMode");
			 * UIUtils.setFunctionalLocationPopupVal(request, response,
			 * functLocFieldNameBean, formModes );
			 * 
			 * }
			 */
			/* for filling combobox in workorder responsibility popup */
			else if (action.equals("combo_plnconfigTrade.plnconfig")) {
				try {
					comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> planconfigTradeid = plmTlPlanconfigurationService.getplnconfigTradeCombo("",
							comboFilter);
					UIUtils.writeComboBox(response, planconfigTradeid, comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (action.equals("combo_plnconfigDesignation.plnconfig")) {
				try {
					comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> planconfigDesigid = plmTlPlanconfigurationService.getplnconfigDesignationCombo("",
							comboFilter);
					UIUtils.writeComboBox(response, planconfigDesigid, comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (action.equals("combo_options.plnconfig")) {
				System.out.println("inside combo_Options");
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
						"selectOptions"));
				System.out.println("relTo SERLET   :" + UIUtils
						.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel", "selectOptions"));
			}

			else if (action.equals("combo_Worespoptions.plnconfig")) {
				System.out.println("inside combo_WORESPCOMBO");
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlanConfigurationcolmodel",
						"selectWORespOptions"));
				System.out.println("relTo SERLET   :" + UIUtils.getPropertyValue(
						"com.akranta.tpm.resources.PlanConfigurationcolmodel", "selectWORespOptions"));
			} else if (action.equals("fillYear.plnconfig")) {
				PrintWriter out = response.getWriter();
				String dateTime = CommonFunctions.dateTimeNow();
				String curMnthYr = dateTime.substring(3, 11);

				List<ComboBox> yearGenrate = generateMonth(curMnthYr);

				JSONArray jsonObject = JSONArray.fromObject(yearGenrate);
				System.out.println(jsonObject);
				out.print(jsonObject);
				out.flush();
				out.close();
				CommonFunctions.debugMsg("dateTime  " + dateTime.substring(3, 11));
			} else if (action.equals("fillcmbMnthQrtHy.plnconfig")) {
				String selYear = request.getParameter("selYear");
				HttpSession session = request.getSession(false);
				session.setAttribute("yearSelected", selYear);
				List<String[]> yearHirerachy = plmTlPlanconfigurationService.getYearHirerachy(selYear);
				System.out.println(" yearHirerachy size " + yearHirerachy.size());
				UIUtils.writeYearHirerachy(response, yearHirerachy);
			} else if (action.equals("fill2Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String yearSelctd = (String) session.getAttribute("yearSelected");
				System.out.println("2yr fill  " + yearSelctd);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(yearSelctd, 2);

				JSONArray yr2json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr2json);
				out.print(yr2json);
				out.flush();
				out.close();
			} else if (action.equals("fill3Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String yearSelctd = (String) session.getAttribute("yearSelected");
				System.out.println("3yr fill  " + yearSelctd);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(yearSelctd, 3);

				JSONArray yr3json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr3json);
				out.print(yr3json);
				out.flush();
				out.close();
			} else if (action.equals("fill4Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("4yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 4);

				JSONArray yr4json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr4json);
				out.print(yr4json);
				out.flush();
				out.close();
			} else if (action.equals("fill5Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("5yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 5);

				JSONArray yr5json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr5json);
				out.print(yr5json);
				out.flush();
				out.close();
			} else if (action.equals("fill6Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("6yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 6);
				JSONArray yr6json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr6json);
				out.print(yr6json);
				out.flush();
				out.close();
			} else if (action.equals("fill7Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("7yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 7);
				JSONArray yr7json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr7json);
				out.print(yr7json);
				out.flush();
				out.close();
			} else if (action.equals("fill8Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("8yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 8);
				JSONArray yr8json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr8json);
				out.print(yr8json);
				out.flush();
				out.close();
			} else if (action.equals("fill9Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("9yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 9);
				JSONArray yr9json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr9json);
				out.print(yr9json);
				out.flush();
				out.close();
			} else if (action.equals("fill10Year.plnconfig")) {
				HttpSession session = request.getSession(false);
				String selYear = (String) session.getAttribute("yearSelected");
				System.out.println("10yr fill  " + selYear);
				PrintWriter out = response.getWriter();
				List<ComboBox> yearGenrate = generateYearMonths(selYear, 10);
				JSONArray yr10json = JSONArray.fromObject(yearGenrate);
				System.out.println(yr10json);
				out.print(yr10json);
				out.flush();
				out.close();
			} else if (action.equals("workorderRespon_del.plnconfig")) {

				String worespdtlId = request.getParameter("worespKeyId");
				CommonFunctions.debugMsg("" + worespdtlId);
				String pmsdTradeid = plmTlWorespmstService.delSelectedTrade(worespdtlId);
			} else if (action.equals("planConfig_save.plnconfig")) {

				BAL_PlanConfigurationBean planConfigurationBean = (BAL_PlanConfigurationBean) httpSession
						.getAttribute("planConfigurationBean");
				String keyId = request.getParameter("keyId");
				String mchId = request.getParameter("machineChkd");
				String assmId = request.getParameter("assmId");
				String applytoall = request.getParameter("applytoall");
				httpSession.setAttribute("TblmachineId", mchId);
				httpSession.setAttribute("TblkeyId", keyId);
				httpSession.setAttribute("TblassmId", assmId);
				CommonFunctions.debugMsg("keyidnewwwww  :" + keyId);
				CommonFunctions.debugMsg("mchidnewwwww  :" + mchId);
				CommonFunctions.debugMsg("applytoall  :" + applytoall);
				httpSession.removeAttribute("delConfirm");
				CommonFunctions.debugMsg("Save action called");
				planConfigurationBean.setChkApplytoall(applytoall);
				savePlanConfig(request, response, planConfigurationBean);
				//
			}

			else if (action.equals("PlanConfig_delete.plnconfig")) {

				System.out.println("inside delete action");
				BAL_PlanConfigurationBean planConfigurationBean = new BAL_PlanConfigurationBean();
				// GenerateMonth(request,response,planConfigurationBean);
			}
			// for Combo box fill
			if (action.equals("combo_GmntTrade.plnconfig")) {
				System.out.println("GmntTrade combo");
				try {
					/*
					 * List<ComboBox> GmntTradeid =
					 * plmTlPlanconfigurationService.getGmntTradeCombo("");
					 * UIUtils.writeComboBox(response, GmntTradeid);
					 */
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dispatchUrl != null) {

				RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
				rd.forward(request, response);
				System.out.println(" response " + response + " " + dispatchUrl);
			}
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
	}

	private void saveWorkResp(HttpServletRequest request, HttpServletResponse response,
			BAL_WOResponsibilityBean wOResponsibilityBean) throws IOException {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		String factId = (String) httpSession.getAttribute("factId");
		String sectId = (String) httpSession.getAttribute("sectid");
		String cellid = (String) httpSession.getAttribute("cellId");
		String machId = (String) httpSession.getAttribute("machId");
		if (!UIUtils.isValidKeyId(machId)) {
			machId = request.getParameter("mchId");
		}
		String costcenterid = (String) httpSession.getAttribute("costcenterid");
		String optedValue = (String) httpSession.getAttribute("optedValue");
		String gridrowId = (String) httpSession.getAttribute("woResrowmachId");// for woresp machine ID
		if (!UIUtils.isValidKeyId(gridrowId)) {
			gridrowId = request.getParameter("woResrowmachId");
		}
		String tradeid = (String) httpSession.getAttribute("tradeid");
		String responsibility = (String) httpSession.getAttribute("responsibility");
		String overallResponsibility = (String) httpSession.getAttribute("ovrallResp");
		//String wMstkeyId = (String) httpSession.getAttribute("woMstkeyId");
		String wMstkeyId = (String) httpSession.getAttribute("mstKeyId");
		if (!UIUtils.isValidKeyId(wMstkeyId)) {
		    wMstkeyId = (String) httpSession.getAttribute("woMstkeyId"); // fallback for safety
		}
		// String womKeyId = (String)httpSession.getAttribute("womKeyId");//from
		// woresponsibility pop up grid

		String overallResp = request.getParameter("overallResp");
		httpSession.setAttribute("ovrallResp", request.getParameter("overallResp"));
		// WOResponsibilityBean wOResponsibilityBean = new WOResponsibilityBean();
		if (httpSession != null && user != null) {
			BAL_PlmTlWorespmst newplmTlWorespmst = new BAL_PlmTlWorespmst();
			BAL_PlmTlWorespdtl newplmTlWorespdtl = new BAL_PlmTlWorespdtl();

			BAL_PlmTlWorespmst existplmTlWorespmst = (BAL_PlmTlWorespmst) httpSession.getAttribute("newplmTlWorespmst");
//	    		PlmTlWorespmst existplmTlWorespdtl =(PlmTlWorespmst)httpSession.getAttribute("newplmTlWorespdtl");
			newplmTlWorespmst.setPwrmcreatedby(user.getUsrm_ccno());
			newplmTlWorespmst.setPwrmkeyid(wMstkeyId);
			newplmTlWorespmst.setPwrmfactoryid(factId);
			newplmTlWorespmst.setPwrmsectionid(sectId);
			newplmTlWorespmst.setPwrmcellid(cellid);
			newplmTlWorespmst.setPwrmmachineid(gridrowId);
			newplmTlWorespmst.setPwrmlevel(optedValue);

			newplmTlWorespdtl.setPwrdcreatedby(user.getUsrm_ccno());
			// newplmTlWorespdtl.setPwrdmasterid(pplcKeyid);
			newplmTlWorespmst = (BAL_PlmTlWorespmst) UIUtils.setBeanProperties((Object) newplmTlWorespmst, request);// bean
																													// for
																													// worespMst
			newplmTlWorespdtl = (BAL_PlmTlWorespdtl) UIUtils.setBeanProperties((Object) newplmTlWorespdtl, request);// bean
																													// for
																													// worespDtl
			// CommonFunctions.debugMsg("wrkmst key ID "+newplmTlWorespmst.getPwrmkeyid()+"
			// exist "+existplmTlWorespmst.getPwrmtradewise());
			// CommonFunctions.debugMsg("wrkmstoverall "+overallResponsibility);
			if (UIUtils.isValidKeyId(overallResponsibility)) {

				newplmTlWorespdtl.setPwrdtradeid("{}");
				newplmTlWorespmst.setPwrmgeneral("Y");
				newplmTlWorespmst.setPwrmtradewise("N");
			} else {
				newplmTlWorespmst.setPwrmgeneral("N");
				newplmTlWorespmst.setPwrmtradewise("Y");
			}
			/*** Save WORK RESP DTL grid ****/
			List<BAL_PlmTlWorespdtl> workRespdtlList = null;
			String workRespdtlStr = "[{ \"cmbPwrdtradeid\"" + ":" + "\"" + tradeid + "\"" + ",";
			workRespdtlStr += "\"cmbPwrdempid\"" + ":" + "\"" + responsibility + "\"" + "}]";
			// workRespdtlStr+="\"txtPwrdmasterid\""+":"+"\""+wMstkeyId+"\""+"}]";

			JSONArray workRespdtljson = null;
			if (UIUtils.isValidKeyId(workRespdtlStr)) {
				workRespdtljson = JSONArray.fromString(workRespdtlStr);
				workRespdtlList = (List<BAL_PlmTlWorespdtl>) UIUtils.convertJSONArrToList(newplmTlWorespdtl,
						workRespdtljson);
				if (workRespdtlList != null)
					newplmTlWorespmst.setWoRespDetail(workRespdtlList);

			}
			try {
				
				if (!UIUtils.isValidKeyId(wMstkeyId)) {

					existplmTlWorespmst = plmTlWorespmstService.saveWorkresp(newplmTlWorespmst, existplmTlWorespmst,
							wOResponsibilityBean);
					
				} else {

					existplmTlWorespmst = plmTlWorespmstService.update(newplmTlWorespmst, existplmTlWorespmst,
							wOResponsibilityBean);
					
					/*** end of save WORK RESP DTL **/
					// newplmTlWorespdtl =
					// plmTlWorespmstService.saveWorkresp(newplmTlWorespdtl,existplmTlWorespdtl);
				}

				JSONObject successData = new JSONObject();
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
				// successData.put("keyId", existplmTlWorespmst.getPwrmkeyid());
				successData.put("keyId", existplmTlWorespmst.getPwrmkeyid());
				JSONObject returnData = new JSONObject();

				returnData.put("successData", successData);

				out.print(returnData.toString());
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

	private void savePlanConfig(HttpServletRequest request, HttpServletResponse response,
			BAL_PlanConfigurationBean planConfigurationBean)
			throws IOException, PlanConfigExceptions, SQLException, WoResponsibilityExpection {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String machineId = (String) httpSession.getAttribute("TblmachineId");
		String keyId = (String) httpSession.getAttribute("TblkeyId");
		String assmId = (String) httpSession.getAttribute("TblassmId");
		/*
		 * String factId = (String)httpSession.getAttribute("factid)"); String sectId =
		 * (String)httpSession.getAttribute("sectid"); String cellid =
		 * (String)httpSession.getAttribute("cellid"); String machId =
		 * (String)httpSession.getAttribute("machId"); String costcenterid =
		 * (String)httpSession.getAttribute("costcenterid"); String optedValue =
		 * (String)httpSession.getAttribute("optedValue"); String gridrowId =
		 * (String)httpSession.getAttribute("rowmachId"); String tradeid=
		 * (String)httpSession.getAttribute("tradeid"); String
		 * responsibility=(String)httpSession.getAttribute("responsibility");
		 */

		if (httpSession != null && user != null) {
			BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration = (BAL_PlmTlPlanconfiguration) httpSession
					.getAttribute("plmTlPlanconfiguration_Servlet");
			/*
			 * PlmTlWorespmst existplmTlWorespmst
			 * =(PlmTlWorespmst)httpSession.getAttribute("newplmTlWorespmst");
			 * PlmTlWorespdtl existplmTlWorespdtl
			 * =(PlmTlWorespdtl)httpSession.getAttribute("newplmTlWorespdtl");
			 */
			// PlmTlPlanconfiguration newPlmTlPlanconfiguration =
			// (PlmTlPlanconfiguration)httpSession.getAttribute("plmTlPlanconfiguration_Servlet");
			BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration = new BAL_PlmTlPlanconfiguration();
			newPlmTlPlanconfiguration.setPplcCreatedby(user.getUsrm_ccno());

			/*
			 * PlmTlWorespmst newplmTlWorespmst = new PlmTlWorespmst(); PlmTlWorespdtl
			 * newplmTlWorespdtl = new PlmTlWorespdtl();
			 * 
			 * newplmTlWorespmst.setPwrmfactoryid(factId);
			 * newplmTlWorespmst.setPwrmsectionid(sectId);
			 * newplmTlWorespmst.setPwrmcellid(cellid);
			 * newplmTlWorespmst.setPwrmmachineid(machineId);
			 * newplmTlWorespmst.setPwrmlevel(optedValue);
			 */
			planConfigurationBean = (BAL_PlanConfigurationBean) UIUtils
					.setBeanProperties((Object) planConfigurationBean, request);
			newPlmTlPlanconfiguration = (BAL_PlmTlPlanconfiguration) UIUtils
					.setBeanProperties((Object) newPlmTlPlanconfiguration, request);
			if (UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcMachineid())) {
				newPlmTlPlanconfiguration.setPplcMachineid(newPlmTlPlanconfiguration.getPplcMachineid());
			} else {
				newPlmTlPlanconfiguration.setPplcMachineid(machineId);
			}
			if (UIUtils.isValidKeyId(assmId)) {
				newPlmTlPlanconfiguration.setPplcAssemblyid(assmId);
			}
			httpSession.removeAttribute("newplanData");
			httpSession.removeAttribute("existplanData");
			httpSession.setAttribute("newplanData", newPlmTlPlanconfiguration);
			httpSession.setAttribute("existplanData", existPlmTlPlanconfiguration);
			createUpdate(newPlmTlPlanconfiguration, existPlmTlPlanconfiguration, planConfigurationBean, request,
					response);
		}

	}

	private void createUpdate(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,
			BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration, BAL_PlanConfigurationBean planConfigurationBean,
			HttpServletRequest request, HttpServletResponse response)
			throws PlanConfigExceptions, SQLException, IOException, WoResponsibilityExpection {
		ServletOutputStream out = response.getOutputStream();
		HttpSession httpSession = request.getSession(false);
		String keyId = (String) httpSession.getAttribute("TblkeyId");
		String usrConfirmation = (String) httpSession.getAttribute("delConfirm");

		// TODO Auto-generated method stub
		// CommonFunctions.debugMsg("Inside create update function" + usrConfirmation);
		// CommonFunctions.debugMsg( keyId+"cut key "
		// +newPlmTlPlanconfiguration.getPplcKeyid());
		try {
			String saveMsg;

			/*
			 * if(!UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcKeyid())) {
			 */
			// System.out.println("key id is not available");
			existPlmTlPlanconfiguration = plmTlPlanconfigurationService.create(newPlmTlPlanconfiguration,
					existPlmTlPlanconfiguration, planConfigurationBean, usrConfirmation);

			saveMsg = "Data Saved Successfully";
//				}	
			/*
			 * else{
			 * 
			 * System.out.println("update mpdeedljdfjujdf");
			 * newPlmTlPlanconfiguration.setPplcKeyid(keyId); //
			 * System.out.println("key id is available" +
			 * existPlmTlPlanconfiguration.getPplcKeyid() );
			 * planConfigurationBean.setFormMode("update"); existPlmTlPlanconfiguration =
			 * plmTlPlanconfigurationService.update(newPlmTlPlanconfiguration,
			 * existPlmTlPlanconfiguration,planConfigurationBean); saveMsg =
			 * "Data Updated Successfully"; }
			 */
			// System.out.println("Target date :"+newPlmTlPlanconfiguration.getPplcKeyid());

			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", saveMsg);
			successData.put("mchId", newPlmTlPlanconfiguration.getPplcMachineid());
			returnData.put("successData", successData);
			out.print(returnData.toString());
			// out.print()
			System.out.println("end of save");

		} catch (WoResponsibilityExpection e) {
			System.out.println("WoResponsibilityExpection. " + e.getMessage());
			CommonFunctions.debugMsg("usrConfirmation  " + usrConfirmation);
			JSONObject confirmusr = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", "Data Saved SuccessFully");
			confirmusr.put("successData", successData);
			confirmusr.put("woException", e.getMessage());
			out.print(confirmusr.toString());

		} catch (PlanConfigExceptions e) {

			System.out.println("PlanConfigExceptions. " + e.getMessage());
			JSONObject confirm = new JSONObject();
			if (!UIUtils.isValidKeyId(usrConfirmation)) {
				confirm.put("tpmException", e.getMessage());
				out.print(confirm.toString());
				httpSession.removeAttribute("delConfirm");
			}
		} catch (Exception e) {
			System.out.println("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", e.getMessage());
			out.print(err.toString());
		}

	}

	private List<ComboBox> generateMonth(String curMonthYear) {
		// TODO Auto-generated method stub
		String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		if (UIUtils.isValidKeyId(curMonthYear)) {
			CommonFunctions.debugMsg("curMonthYear  " + curMonthYear);
			String[] curMonthArr = curMonthYear.split("-");
			String passedmnth = curMonthArr[0];// curMonthYear.substring(0, 3);
			String passedyear = curMonthArr[1];// curMonthYear.substring(3,8);
			List<ComboBox> populatemnth = new ArrayList<ComboBox>();
			int k = 0;
			int arrCnt = 0;
			// String[] datas = new String[ 22];
			while (k <= 1) {
				for (int i = 0; i < 12; i++) {
					if (passedmnth.equals(monthArray[i])) {
						// datas[arrCnt] = monthArray[i]+"-"+passedyear;
						ComboBox yrData = new ComboBox();
						yrData.setId(monthArray[i] + "-" + passedyear);
						yrData.setText(monthArray[i] + "-" + passedyear);
						populatemnth.add(yrData);
						arrCnt++;
						System.out.println(arrCnt);
						if (i != 11)
							passedmnth = monthArray[i + 1];
					}

				}
				k++;
				int year = Integer.parseInt(passedyear);
				year = year + 1;
				passedyear = Integer.toString(year);
				passedmnth = "Jan";
			}
			return populatemnth;
		}
		return null;
	}

	// for filling other combo boxs years
	private List<ComboBox> generateYearMonths(String curMonthYear, int noYrs) {

		String[] curMonthArr = curMonthYear.split("-");
		List<ComboBox> populatemnth = new ArrayList<ComboBox>();
		String passedyear = curMonthArr[1];
		CommonFunctions.debugMsg("curMonthArr[1]   " + curMonthArr[1]);
		int k = 0;
		int arrCnt = 0;
		while (k < noYrs) {
			// datas[arrCnt] = monthArray[i]+"-"+passedyear;
			ComboBox yrData = new ComboBox();
			yrData.setId(curMonthArr[0] + "-" + passedyear);
			yrData.setText(curMonthArr[0] + "-" + passedyear);
			populatemnth.add(yrData);
			arrCnt++;
			CommonFunctions.debugMsg("mnth yr  " + curMonthArr[0] + "-" + passedyear);
			k++;
			int year = Integer.parseInt(passedyear);
			year = year + 1;
			passedyear = Integer.toString(year);
			System.out.println("Year : " + year);

		}

		return populatemnth;

	}

	private void saveProductionPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if (httpSession != null && user != null) {
			PcsTlProductionplan existPcsTlProductionplan = (PcsTlProductionplan) httpSession
					.getAttribute("productionPlanPCS");
			PcsTlProductionplan newPcsTlProductionplan = new PcsTlProductionplan();
			newPcsTlProductionplan.setPrplCreatedby(user.getUsrm_ccno());
			newPcsTlProductionplan = (PcsTlProductionplan) UIUtils.setBeanProperties((Object) newPcsTlProductionplan,
					request);

			String prodPlanGrid = request.getParameter("prodPlanGrid");
			if (UIUtils.isValidKeyId(prodPlanGrid)) {
				JSONArray jsonArray = JSONArray.fromString(prodPlanGrid);
				PcsTlProductionplan pcsTlProductionplan = new PcsTlProductionplan();
				List<PcsTlProductionplan> prodPlan = (List<PcsTlProductionplan>) UIUtils
						.convertJSONArrToList(pcsTlProductionplan, jsonArray);
				if (pcsTlProductionplan != null)
					newPcsTlProductionplan.setProdPlan(prodPlan);

				try {
					existPcsTlProductionplan = plmTlPlanconfigurationService.create(newPcsTlProductionplan,
							existPcsTlProductionplan);
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("msg",
							UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					CommonFunctions.debugMsg(returnData.toString());
					out.print(returnData.toString());
				} catch (ValidationExceptions e) {
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "PlanEntryColmodel");
					out.print(errMessage.toString());
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
					e.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
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
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}

		return commonFilter;
	}
}