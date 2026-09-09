package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
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
import com.akranta.tpm.bean.EntTlTopicmstBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.TargetGrp;
import com.akranta.tpm.bean.Uniquepositionbean;
//import com.akranta.tpm.bean.VisualConBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupdtl;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlRolemst;
//import com.akranta.tpm.model.SopTlVisualchecklistdtl;
//import com.akranta.tpm.model.SopTlVisualchecklistmst;
import com.akranta.tpm.service.EntTlTopicmstService;
import com.akranta.tpm.service.impl.EntTlTopicmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class EntTlTopicmstServlet extends HttpServlet {
	private static final long serialVersionUID = -8070953412956046800L;
	EntTlTopicmstService entTlTopicmstService;

	// FunctionalLocnServices functionalLocnServices;
	public EntTlTopicmstServlet() {
		super();
		/*
		 * try { entTlTopicmstService = new EntTlTopicmstServiceImpl(); } catch
		 * (Exception e) {
		 * 
		 * }
		 */
		// Constructor
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);
		ComboFilter currentFilter = new ComboFilter();

		// response.setContentType("application/json");

		String action = UIUtils.getActionPart(request);
		try {

			entTlTopicmstService = (EntTlTopicmstServiceImpl) UIUtils
					.getServiceObject(request, "EntTlTopicmstServiceImpl");
		} catch (ServiceObjectCreationException e) {
			// TODO Auto-generated catch block
			CommonMessage.debugMsg(e);
			// e.printStackTrace();
		}

		if (action.equals("loadval.skil")) {
			// LoadSkilTreeWithFnLcn(request,
			// response,out,entTlTopicmstServiceImpl);
			// LoadSkilTree(request, response,out,entTlTopicmstServiceImpl);
		} else if (action.equals("topi_input.topi")) {
			LoadTopicEntry(request, response, httpSession);
			String displayEntCode = UIUtils.displayENTCode(request);
			request.setAttribute("displayEntCode", displayEntCode);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/ENT/TopicMasterPopup.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("UniquePosition_view.topi")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/newENT/uniqueGrid.jsp");
			rd.forward(request, response);
		} else if (action.equals("UniquePosition_getCol.topi")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,
					"uniqueMainGridCommonFilter", true);
			httpSession.removeAttribute("uniqueMainGridCommonFilter");
			httpSession
					.setAttribute("uniqueMainGridCommonFilter", commonFilter);
			out.println(UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.UniquePosition", "uniqueGrid"));

		}

		else if (action.equals("UniquePosition_getData.topi")) {
			try {

				CommonFilter commonFilter = populateCommonFilter(request,
						"uniqueMainGridCommonFilter", false);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				List<String[]> uniquelist = entTlTopicmstService
						.getselectmain(commonFilter);

				net.sf.json.JSONObject unique = UIUtils
						.convertToJqGridTableObject(uniquelist, request, 0, 1,
								commonFilter.getTotalRecordCnt());
				out.println(unique);
				httpSession.removeAttribute("uniqueMainGridCommonFilter");
				httpSession.setAttribute("uniqueMainGridCommonFilter",
						commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		} else if (action.equals("UniquePosition_getExcel.topi")) {

			CommonFilter commonFilter = populateCommonFilter(request,
					"uniqueMainGridCommonFilter", false);
			String colModelIdent = "uniqueGrid";
			JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			tblJSONObj.put("title", "Unique Position Master");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = entTlTopicmstService.getUniquePositionExportExcel(
					commonFilter, tblJSONObj, format);
			ExcelUtils.writeToResponse(response, wb, "UniquePositionMaster",
					format);
			CommonMessage.debugMsg("U R in :" + action);

		} else if (action.equals("TargetGroup_view.topi")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/TargetGrid.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("TargetGroup_getCol.topi")) {
			JSONObject target = new JSONObject();
			PrintWriter out = response.getWriter();
			String trgt = UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.UniquePosition", "targetGrid");
			target = JSONObject.fromString(trgt);
			httpSession.setAttribute("TargetColmodel", target);
			out.println(target);
			//
		}

		else if (action.equals("TargetGroup_getData.topi")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,
						"targetCommonFilter", true);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				List<String[]> targetlist = entTlTopicmstService
						.getselectmaintarget(commonFilter);

				net.sf.json.JSONObject target = UIUtils
						.convertToJqGridTableObject(targetlist, request, 0, 0);
				out.println(target);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("getModeUniquePosition_view.topi")) {
			CommonMessage.debugMsg("msg");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode", "create");
			result.put("url", "UniquePositionform_input.topi");
			result.put("formHeader", "Unique Position");

			out.println(result);
		} else if (action.equals("getModeTargetGroup_view.topi")) {
			CommonMessage.debugMsg("msg");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode", "create");
			result.put("url", "TargetGroupform_input.topi");
			result.put("formHeader", "Target Group");

			out.println(result);
		}

		else if (action.equals("UniquePositionform_input.topi")) {
			String keyId = request.getParameter("keyId");
			String flid=request.getParameter("flid");
			CommonMessage.debugMsg("keyId ::: " + keyId);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			GenTlRolemst gentlrolemst = new GenTlRolemst();
			if (UIUtils.isValidKeyId(keyId)) {
				gentlrolemst = entTlTopicmstService.getrolemain(keyId);
				CommonMessage.debugMsg("gentlrolemst.getRoleKeyid() ::: " + gentlrolemst.getRoleKeyid());
			}
			request.setAttribute("gentlrolemst", gentlrolemst);
			request.setAttribute("flid", flid);

			RequestDispatcher rd = request.getRequestDispatcher("/pages/newENT/UniquePositionmaster.jsp");
			rd.forward(request, response);
		} else if (action.equals("UniquePositionSelectd_getCol.topi")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"uniqueLeftGridCommonFilter", true);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.UniquePosition", "selUniquePos"));
			httpSession.removeAttribute("uniqueLeftGridCommonFilter");
			httpSession.setAttribute("uniqueLeftGridCommonFilter", commonFilter);
			CommonMessage.debugMsg(" *****UniquePositionSelectd_getCol***");
		} else if (action.equals("UniquePositionSelectd_getData.topi")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"uniqueLeftGridCommonFilter", false);
				PrintWriter out = response.getWriter();
				String upid = request.getParameter("upid");
				commonFilter.setParamCode(upid);// for sending	// role/uniqueposition id
				CommonMessage.debugMsg("***upid :" +upid);
				//UIUtils.displayRequestParamsValue(request);
				List<String[]> uniqueEmplist = entTlTopicmstService.getselectdEmpRole(commonFilter);
				
				CommonMessage.debugMsg("**1************");
				JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request, 0, 0,commonFilter.getTotalRecordCnt());
				CommonMessage.debugMsg("**2************");

				out.println(uniquePOSEmp);
				CommonMessage.debugMsg("**3************");

				httpSession.removeAttribute("uniqueLeftGridCommonFilter");
				httpSession.setAttribute("uniqueLeftGridCommonFilter",	commonFilter);
				CommonMessage.debugMsg(" *****UniquePositionSelectd_getData***");

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("UniquePositionform_getCol.topi")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"uniqueRightGridCommonFilter", true);
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.UniquePosition", "unique"));
			httpSession.removeAttribute("uniqueRightGridCommonFilter");
			httpSession.setAttribute("uniqueRightGridCommonFilter",	commonFilter);
		} else if (action.equals("UniquePositionform_getData.topi")) {
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"uniqueRightGridCommonFilter", false);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				List<String[]> uniquelist = entTlTopicmstService.getselect(commonFilter);
				net.sf.json.JSONObject unique = UIUtils.convertToJqGridTableObject(uniquelist, request, 0, 1,commonFilter.getTotalRecordCnt());
				out.println(unique);
				httpSession.removeAttribute("uniqueRightGridCommonFilter");
				httpSession.setAttribute("uniqueRightGridCommonFilter",		commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		// else if(action.equals("UniquePositionform_save.topi") )
		else if (action.equals("upEmployee_save.topi")) {
			saveunique(request, response);
		} else if (action.equals("upEmployee_delete.topi")) {
			deleteUpEmployee(request, response);
		} else if (action.equals("UniquePositionform_delete.topi")) {
			deleteunique(request, response);
		} else if (action.equals("TargetGroupform_input.topi")) {
			String keyId = request.getParameter("keyId");
			CommonMessage.debugMsg(keyId + " keyid");
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			EntTlTargetgroupmst entTlTargetgroupmst = new EntTlTargetgroupmst();
			if (UIUtils.isValidKeyId(keyId)) {
				entTlTargetgroupmst = entTlTopicmstService.gettargetmain(keyId);
			}
			request.setAttribute("entTlTargetgroupmst", entTlTargetgroupmst);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/TargetGroup.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("TargetGroupform_getCol.topi")) {
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.UniquePosition", "target"));
		}

		else if (action.equals("TargetGroupform_getData.topi")) {
			try {
				String flid = request.getParameter("flId");
				CommonFilter commonFilter = populateCommonFilter(request,"targetCommonFilter", true);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(flid + " flid");
				UIUtils.displayRequestParamsValue(request);
				commonFilter.setFlid(flid);
				List<String[]> trgtlist = entTlTopicmstService.getselecttarget(commonFilter);

				net.sf.json.JSONObject trgt = UIUtils.convertToJqGridTableObject(trgtlist, request, 0, 0);
				out.println(trgt);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("TargetGroup_getExcel.topi")) {

			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"TarrgetCommonFilter", false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("TargetColmodel");
			colmodel.put("title", "Target Group Report");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = entTlTopicmstService.gettargetExcel(colmodel, format,	commonFilter);
			ExcelUtils.writeToResponse(response, wb, "TargetReports", format);

		}

		else if (action.equals("TargetGroupform_save.topi")) {
			savetarget(request, response);

		} else if (action.equals("TargetGroupform_delete.topi")) {
			deletetarget(request, response);

		} else if (action.equals("targetform_delete.topi")) {
			CommonMessage.debugMsg("00");
			deletetargetdtl(request, response);

		}

		/*
		 * else if(action.equals("combo_Type.skil")) {
		 * //CommonMessage.debugMsg("inside combo_relatedto general Maintainence");
		 * out.println(UIUtils.getPropertyValue(
		 * "com.akranta.tpm.resources.EntTlSkilMstProp", "selectType"));
		 * //CommonMessage.debugMsg("relTo SERLET   :"+UIUtils.getPropertyValue(
		 * "com.akranta.tpm.resources.EntTlSkilMstProp", "selectType")); }
		 */
		else if (action.equals("combo_Type.topi")) {
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntTlTopicMstProp","selectType"));

			/*
			 * commonFilter.setSkilName(currentFilter); comboList =
			 * entTlTopicmstService.getTopiTypeComboList(commonFilter);
			 * CommonMessage.debugMsg("comboList" + comboList); if( comboList
			 * != null && comboList.size() > 0 ) UIUtils.writeComboBox(response,
			 * comboList);//writeCombo(response, comboList);
			 */
		} else if (action.equals("combo_EvaluationType.topi")) {
			CommonFilter commonFilter = new CommonFilter();
			String filter = request.getParameter("q");
			currentFilter = UIUtils.fillComboFilter(request);
			currentFilter.setCode(filter);
			currentFilter.setName(filter);
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			commonFilter.setSkilName(currentFilter);
			comboList = entTlTopicmstService
					.getEvaluationTypeComboList(commonFilter);
			CommonMessage.debugMsg("comboList" + comboList);
			if (comboList != null && comboList.size() > 0)
				UIUtils.writeComboBox(response, comboList, currentFilter);// writeCombo(response,
																			// comboList);
		} 
		else if (action.equals("combo_Category.topi")) {
			CommonFilter commonFilter = new CommonFilter();
			String filter = request.getParameter("q");
			currentFilter = UIUtils.fillComboFilter(request);
			/*currentFilter.setCode(filter);
			currentFilter.setName(filter);*/
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			commonFilter.setSkilName(currentFilter);
			comboList = entTlTopicmstService.getCategoryComboList(commonFilter);
			CommonMessage.debugMsg("comboList" + comboList);
			if (comboList != null && comboList.size() > 0)
				UIUtils.writeComboBox(response, comboList, currentFilter);// writeCombo(response,
																			// comboList);
		}
		 else if(action.equals("deliveryMode_Combo.topi"))
		   {
			    ComboFilter comboFilter = new ComboFilter();
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  getSkillKey = entTlTopicmstService.getDeliveryModeCombo(comboFilter);
				UIUtils.writeComboBox(response, getSkillKey,comboFilter);			
		   }
		   
		
		
		else if (action.equals("combo_TopiParent.topi")) {
			CommonFilter commonFilter = new CommonFilter();
			String filter = request.getParameter("q");
			currentFilter = UIUtils.fillComboFilter(request);
			currentFilter.setCode(filter);
			currentFilter.setName(filter);
			List<ComboBox> comboList = new ArrayList<ComboBox>();

			commonFilter.setSkilName(currentFilter);
			comboList = entTlTopicmstService
					.getTopiParentComboList(commonFilter);
			CommonMessage.debugMsg("comboList" + comboList);
			if (comboList != null && comboList.size() > 0)
				UIUtils.writeComboBox(response, comboList, currentFilter);// writeCombo(response,
																			// comboList);
		} else if (action.equals("combo_location.topi")) {
			try {
				currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> deptModel = entTlTopicmstService
						.getLocationCombo();
				UIUtils.writeComboBox(response, deptModel, currentFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("combo_topic.topi")) {
			CommonFilter commonFilter = new CommonFilter();

			String filter = request.getParameter("q");
			// ComboFilter currentFilter = new ComboFilter();
			currentFilter = UIUtils.fillComboFilter(request);
			currentFilter.setCode(filter);
			currentFilter.setName(filter);
			List<ComboBox> comboList = new ArrayList<ComboBox>();

			comboList = entTlTopicmstService.getTopicComboList(commonFilter);
			CommonMessage.debugMsg("comboList" + comboList);
			if (comboList != null && comboList.size() > 0)
				UIUtils.writeComboBox(response, comboList, currentFilter);// writeCombo(response,
																			// comboList);
		} else if (action.equals("topi_save.topi")) {
			// CommonMessage.debugMsg("inside sace action");
			EntTlTopicmstBean entTlTopicmstBean = new EntTlTopicmstBean();
			saveTopic(request, response, entTlTopicmstBean);
		} else if (action.equals("topi_del.topi")) {
			PrintWriter out = response.getWriter();
			deleteSkil(request, response, out);
		} else if (action.equals("load_view.skil")) {
			UIUtils.removeCookie(response, "skillsearch_str");
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/ENT/SkillMasterWithFn.jsp");
			rd.forward(request, response);
		} else if (action.equals("functionalLocTopicMst.topi")) {
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			// fact// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setLocation("cmbLocation");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setLocnMandatory(true);

			functLocFieldNameBean.setSbuDisable(true);
			functLocFieldNameBean.setPbuDisable(true);
			functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);

			FormModes formModes = FormModes.create;

			UIUtils.setFunctionalLocationPopupVal(request, response,
					functLocFieldNameBean, formModes);

		}

		else if (action.equals("functionalLoc.topi")) {
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			// fact// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setLocation("cmbLocation");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);

			FormModes formModes = FormModes.create;

			UIUtils.setFunctionalLocationPopupVal(request, response,
					functLocFieldNameBean, formModes);

		}
		/*
		 * else if(action.equals("skilllevel_validate.skil")) {
		 * validateSkill(request,response,out); } else if(
		 * action.equals("searchnode.skil") ) {
		 * searchSkill(request,response,httpSession,out); } else
		 * if(action.equals("skilllevelDel_validate.skil")) {
		 * validateDelSkill(request,response,out); }
		 */
	}

	private void deletetargetdtl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String griddata = request.getParameter("datastring");
		CommonMessage.debugMsg(griddata + " data");
		JSONArray jsontarget = null;
		String deletemsg;
		if (httpSession != null && user != null) {
			EntTlTargetgroupdtl entTlTargetgroupdtl = new EntTlTargetgroupdtl();
			EntTlTargetgroupdtl existentTlTargetgroupdtl = new EntTlTargetgroupdtl();
			entTlTargetgroupdtl = (EntTlTargetgroupdtl) UIUtils
					.setBeanProperties((Object) entTlTargetgroupdtl, request);
			TargetGrp TargetGrpbean = new TargetGrp();

			List<EntTlTargetgroupdtl> newentTlTargetgroupdtl = null;
			;

			if (UIUtils.isValidKeyId(griddata)) {
				CommonMessage.debugMsg("newresr: " + griddata);
				if (griddata != null && !griddata.isEmpty()) {
					jsontarget = JSONArray.fromString(griddata);
					CommonMessage.debugMsg("size newGenTlEmployeemst...222  "
							+ jsontarget);
					newentTlTargetgroupdtl = (List<EntTlTargetgroupdtl>) UIUtils
							.convertJSONArrToList(entTlTargetgroupdtl,
									jsontarget);
					CommonMessage.debugMsg("size newGenTlEmployeemst...222  "
							+ newentTlTargetgroupdtl.size());
				}

			}

			existentTlTargetgroupdtl = entTlTopicmstService
					.deletetargetdtl(newentTlTargetgroupdtl);
			deletemsg = "Data Deleted Successfully";

			JSONObject successData = new JSONObject();
			successData.put("msg", deletemsg);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			returnData.put("formClear", true);
			// CommonMessage.debugMsg(returnData.toString());
			out.print(returnData.toString());//
			// TODO Auto-generated method stub
		}
	}

	private void deletetarget(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		CommonMessage.debugMsg("ddd");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			EntTlTargetgroupmst existEntTlTargetgroupmst = (EntTlTargetgroupmst) httpSession
					.getAttribute("voiceChecklist");
			EntTlTargetgroupmst entTlTargetgroupmst = new EntTlTargetgroupmst();
			EntTlTargetgroupdtl entTlTargetgroupdtl = new EntTlTargetgroupdtl();
			String deletemsg;
			TargetGrp TargetGrpbean = new TargetGrp();
			entTlTargetgroupmst.setTgtmCreatedby(user.getUsrm_ccno());
			entTlTargetgroupdtl.setTgtdCreatedby(user.getUsrm_ccno());
			CommonMessage.debugMsg("servlet"
					+ entTlTargetgroupmst.getTgtmTitle());
			entTlTargetgroupmst = (EntTlTargetgroupmst) UIUtils
					.setBeanProperties((Object) entTlTargetgroupmst, request);
			entTlTargetgroupdtl = (EntTlTargetgroupdtl) UIUtils
					.setBeanProperties((Object) entTlTargetgroupdtl, request);
			existEntTlTargetgroupmst = entTlTopicmstService.deletetarget(
					entTlTargetgroupmst, existEntTlTargetgroupmst,
					TargetGrpbean);
			deletemsg = "Data Deleted Successfully";

			JSONObject successData = new JSONObject();
			successData.put("msg", deletemsg);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			returnData.put("formClear", true);
			// CommonMessage.debugMsg(returnData.toString());
			out.print(returnData.toString());//
			// TODO Auto-generated method stub
		}
	}

	private void savetarget(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg("inside save2");
		String gridData = request.getParameter("employee");
		CommonMessage.debugMsg(gridData + " griddata");
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		JSONArray jsontarget = null;
		try {
			if (httpSession != null && user != null)

			{
				EntTlTargetgroupmst newEntTlTargetGroupmst = new EntTlTargetgroupmst();
				EntTlTargetgroupdtl newEntTlTargetgroupdtl = new EntTlTargetgroupdtl();
				TargetGrp TargetGrpbean = new TargetGrp();
				newEntTlTargetGroupmst = (EntTlTargetgroupmst) UIUtils
						.setBeanProperties((Object) newEntTlTargetGroupmst,
								request);// mast
				EntTlTargetgroupmst existEntTlTargetgroupmst = (EntTlTargetgroupmst) httpSession
						.getAttribute("newEntTlTargetGroupmst");
				String savemsg;
				boolean insert = true;
				newEntTlTargetGroupmst.setTgtmCreatedby(user.getUsrm_ccno());

				CommonMessage.debugMsg("000");
				List<EntTlTargetgroupdtl> newentTlTargetgroupdtl = null;
				;
				EntTlTargetgroupdtl entTlTargetgroupdtl = new EntTlTargetgroupdtl();

				if (UIUtils.isValidKeyId(gridData)) {
					CommonMessage.debugMsg("newresr: " + gridData);
					if (gridData != null && !gridData.isEmpty()) {
						jsontarget = JSONArray.fromString(gridData);
						newentTlTargetgroupdtl = (List<EntTlTargetgroupdtl>) UIUtils
								.convertJSONArrToList(entTlTargetgroupdtl,
										jsontarget);
						CommonFunctions
								.debugMsg("size newGenTlEmployeemst...222  "
										+ newentTlTargetgroupdtl.size());
					}
					if (newEntTlTargetGroupmst != null) {
						CommonMessage.debugMsg("size newGenTlEmployeemst..."
								+ newentTlTargetgroupdtl.size());
						newEntTlTargetGroupmst
								.setTargetgroupdtl(newentTlTargetgroupdtl);
						CommonMessage.debugMsg("newGenTlEmployeemst.size(): "
								+ newentTlTargetgroupdtl.get(0).getTgtdKeyid());
					}
				}

				JSONObject successData = new JSONObject();
				JSONObject empSuccessMsg = new JSONObject();
				if (!UIUtils
						.isValidKeyId(newEntTlTargetGroupmst.getTgtmKeyid()))// NOT
																				// NULL
																				// CRETTE
				{
					newEntTlTargetGroupmst = entTlTopicmstService.createtarget(
							newEntTlTargetGroupmst, existEntTlTargetgroupmst,
							TargetGrpbean);
					savemsg = UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-save");
				} else {
					insert = false;

					existEntTlTargetgroupmst = entTlTopicmstService
							.updatetarget(newEntTlTargetGroupmst,
									existEntTlTargetgroupmst, TargetGrpbean);
					savemsg = UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-update");
				}

				// JSONObject successData = new JSONObject();
				empSuccessMsg.put("msg", savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", empSuccessMsg);
				returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();

			}
		} catch (ValidationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),
					"Uniquepostion");
			CommonMessage.debugMsg("e.toString()" + e.toString());
			out.print(errMessage.toString());
		} catch (Exception e) {
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}

	private void deleteUpEmployee(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		try {
			GenTlRolemst newgentlrolemst = new GenTlRolemst();
			String getEmployee = request.getParameter("remEmployee");
			newgentlrolemst = (GenTlRolemst) UIUtils.setBeanProperties(
					(Object) newgentlrolemst, request);// master
			List<GenTlEmployeemst> newGenTlEmployeemst = null;
			GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
			if (UIUtils.isValidKeyId(getEmployee)) {
				CommonMessage.debugMsg("newresr: " + getEmployee);
				JSONArray jsonUique = new JSONArray();
				if (getEmployee != null && !getEmployee.isEmpty()) {
					jsonUique = JSONArray.fromString(getEmployee);
					newGenTlEmployeemst = (List<GenTlEmployeemst>) UIUtils
							.convertJSONArrToList(genTlEmployeemst, jsonUique);
				}
				if (newgentlrolemst != null) {
					CommonMessage.debugMsg("size newGenTlEmployeemst..."
							+ newGenTlEmployeemst.size());
					newgentlrolemst.setEmployeedetails(newGenTlEmployeemst);
					CommonMessage.debugMsg("newGenTlEmployeemst.size(): "
							+ newGenTlEmployeemst.get(0).getEmpmKeyid());
				}
				newgentlrolemst = entTlTopicmstService
						.deleteUPEmployee(newgentlrolemst);
				JSONObject successData = new JSONObject();
				// successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("msg", "Role Removed SuccessFully");
				JSONObject returnData = new JSONObject();
				returnData.put("formClear", false);
				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (Exception e) {
			CommonMessage.debugMsg("data");
			e.printStackTrace();
			JSONObject err = new JSONObject();
			CommonMessage.debugMsg("in Exception    " + e.getMessage());
			err.put("tpmException", "Data Not Deleted");
			if (UIUtils.isValidKeyId(e.getMessage())) {
				CommonMessage.debugMsg("in Exception    " + e.getMessage());

				if (e.getMessage().contains("FK")) {
					err.put("tpmException",
							"Reference Data Found ,Data Cannot Be Deleted");
					out.print(err.toString());
				}
			}
		}
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String string, boolean b) {
		// TODO Auto-generated method stub

		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(string);
		if (commonFilter != null && !b) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();

			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getTraning(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(string);
			httpSession.setAttribute(string, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"
				+ commonFilter.getFromRow() + "----" + commonFilter.getToRow());
		return commonFilter;
	}

	private void deleteunique(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		try {
			GenTlRolemst newgentlrolemst = new GenTlRolemst();
			newgentlrolemst = (GenTlRolemst) UIUtils.setBeanProperties(
					(Object) newgentlrolemst, request);// master
			newgentlrolemst = entTlTopicmstService
					.deleteunique(newgentlrolemst);
			JSONObject successData = new JSONObject();
			successData.put("msg", UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages",
					"success-delete"));
			JSONObject returnData = new JSONObject();
			returnData.put("formClear", true);
			returnData.put("successData", successData);
			out.print(returnData.toString());
		} catch (Exception e) {
			CommonMessage.debugMsg("data");
			e.printStackTrace();
			JSONObject err = new JSONObject();
			CommonMessage.debugMsg("in Exception    " + e.getMessage());
			err.put("tpmException", "Data Not Deleted");
			if (UIUtils.isValidKeyId(e.getMessage())) {
				CommonMessage.debugMsg("in Exception    " + e.getMessage());

				if (e.getMessage().contains("FK")) {
					err.put("tpmException",
							"Reference Data Found ,Data Cannot Be Deleted");
					out.print(err.toString());
				}
			}
		}
	}

	private void saveunique(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg("inside save2");
		String gridData = request.getParameter("employee");
		String elementType = request.getParameter("elementType");
		CommonMessage.debugMsg(gridData + " griddata");
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		JSONArray jsonUnique = null;
		try {
			if (httpSession != null && user != null)

			{
				GenTlRolemst newgentlrolemst = new GenTlRolemst();
				GenTlEmployeemst newgenTlEmployeemst = new GenTlEmployeemst();
				Uniquepositionbean uniquebean = new Uniquepositionbean();
				newgentlrolemst = (GenTlRolemst) UIUtils.setBeanProperties(
						(Object) newgentlrolemst, request);// master
				GenTlRolemst existGenTlRolemst = (GenTlRolemst) httpSession
						.getAttribute("newgentlrolemst");
				String savemsg;
				boolean insert = true;
				newgentlrolemst.setRoleCreatedby(user.getUsrm_ccno());
				CommonMessage.debugMsg("000");
				// List<GenTlEmployeemst> employeelist = null;
				List<GenTlEmployeemst> newGenTlEmployeemst = null;
				GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
				if (UIUtils.isValidKeyId(gridData)) {
					CommonMessage.debugMsg("newresr: " + gridData);
					if (gridData != null && !gridData.isEmpty()) {
						jsonUnique = JSONArray.fromString(gridData);
						newGenTlEmployeemst = (List<GenTlEmployeemst>) UIUtils
								.convertJSONArrToList(genTlEmployeemst,
										jsonUnique);
					}
					if (newgentlrolemst != null) {
						CommonMessage.debugMsg("size newGenTlEmployeemst..."
								+ newGenTlEmployeemst.size());
						newgentlrolemst.setEmployeedetails(newGenTlEmployeemst);
						CommonMessage.debugMsg("newGenTlEmployeemst.size(): "
								+ newGenTlEmployeemst.get(0).getEmpmKeyid());
					}
				}

				JSONObject successData = new JSONObject();
				JSONObject empSuccessMsg = new JSONObject();
				uniquebean.setRoleelementType(elementType);
				CommonMessage.debugMsg(elementType + "  ELEMENTTYPE "
						+ uniquebean.getRoleelementType());
				if (!UIUtils.isValidKeyId(newgentlrolemst.getRoleKeyid()))// NOT
																			// NULL
																			// CRETTE
				{
					existGenTlRolemst = entTlTopicmstService.createunique(
							newgentlrolemst, existGenTlRolemst, uniquebean);
					savemsg = UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-save");
				} else {
					CommonMessage.debugMsg("inside eo;le upadare  "
							+ newgentlrolemst.getRoleKeyid());
					insert = false;
					// elementType

					existGenTlRolemst = entTlTopicmstService.updateunique(
							newgentlrolemst, existGenTlRolemst, uniquebean);
					savemsg = UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-update");
				}

				// JSONObject successData = new JSONObject();
				empSuccessMsg.put("msg", savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", empSuccessMsg);
				returnData.put("formClear", false);
				returnData.put("roleKeyid", existGenTlRolemst.getRoleKeyid());
				returnData.put("flid", existGenTlRolemst.getRoleFlid());
				out.print(returnData.toString());//
				out.close();

			}
		} catch (ValidationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),
					"Uniquepostion");
			CommonMessage.debugMsg("e.toString()" + e.toString());
			out.print(errMessage.toString());
		} catch (BusinessApplicationExceptions e) {
			JSONObject err = new JSONObject();
			if (e.getMessage().contains("UK_ROLE_FLID"))
				err.put("tpmException",
						"Unique Position Already Exists for the Function Location");
			else
				err.put("tpmException", e.getMessage());

			out.print(err.toString());

		} catch (Exception e) {
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}

	/*
	 * private void searchSkill(HttpServletRequest request, HttpServletResponse
	 * response,HttpSession httpSession,PrintWriter out ) throws
	 * ValidationExceptions,BusinessApplicationExceptions, Exception{
	 * Enumeration<String> params = request.getParameterNames() ; String[]
	 * searchArray; String elementType=null; String searchString=null; String
	 * originalId=null; EntTlTopicmst entTlTopicmst=new EntTlTopicmst(); String
	 * parentIdOriginal=null; String prevSearchstr =
	 * UIUtils.getCookieValue(request,"skillsearch_str"); String
	 * currentSearchStr = request.getParameter("search_str");
	 * //CommonMessage.debugMsg("currentSearchStr:" + currentSearchStr);
	 * String countStr = "0"; if(currentSearchStr.indexOf("-")>0){ searchArray =
	 * currentSearchStr.split("-"); elementType=searchArray[1].toString();
	 * searchString=searchArray[0].toString();
	 * //CommonMessage.debugMsg("elementType:"+ elementType + ",searchString:"
	 * + searchString); } List<String[]> searchList = null; List<String[]>
	 * skillParentList = null; if(currentSearchStr != null && prevSearchstr !=
	 * null && currentSearchStr.equals(prevSearchstr)) { countStr =
	 * UIUtils.getCookieValue(request,"skillsearch_str_cnt"); searchList
	 * =(List<String[]>) httpSession.getAttribute("skillSearchList"); } else{ if
	 * (CommonFunctions.isValidKeyId(elementType)) searchList=
	 * entTlTopicmstService.getSearchSkillLevel(searchString); else searchList =
	 * entTlTopicmstService.getSearchNode(currentSearchStr,"");
	 * httpSession.setAttribute("skillSearchList", searchList); } int searchCnt
	 * = Integer.parseInt(countStr); JSONArray jSONArray =null; if( searchCnt <
	 * searchList.size() ){ String parentId = searchList.get(searchCnt)[0];
	 * //CommonMessage.debugMsg(parentId); parentId=parentId.replaceAll("/",
	 * "_") ; parentId=parentId.replaceAll("_", "-") ; parentId =
	 * "#node_1-SL001-" + parentId; //parentId = "#node_1-SL001-" +
	 * parentId.replaceAll("/", "_") ; parentId = parentId.replaceAll("-",
	 * "-#"); //CommonMessage.debugMsg(parentId); String[] searchNode =
	 * parentId.split("-"); CommonMessage.debugMsg(parentId);
	 * //CommonMessage.debugMsg(searchNode); jSONArray =
	 * JSONArray.fromArray(searchNode); } else{ searchCnt =-1; }
	 * 
	 * Cookie searchStrCookie = new Cookie("skillsearch_str",currentSearchStr);
	 * searchStrCookie.setMaxAge(60*60); response.addCookie(searchStrCookie);
	 * Cookie searchCntCookie = new
	 * Cookie("skillsearch_str_cnt",(searchCnt+1)+"");
	 * searchCntCookie.setMaxAge(60*60); response.addCookie(searchCntCookie);
	 * 
	 * out.println(jSONArray); }
	 */
	private void saveTopic(HttpServletRequest request,
			HttpServletResponse response, EntTlTopicmstBean entTlTopicmstBean)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception {
		// CommonMessage.debugMsg("inside saveSkil");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				EntTlTopicmst existEntTlTopicmst = (EntTlTopicmst) httpSession
						.getAttribute("entTlTopicmst");
				EntTlTopicmst newEntTlTopicmst = new EntTlTopicmst();

				newEntTlTopicmst.setTopiCreatedby(user.getUsrm_ccno());
				newEntTlTopicmst = (EntTlTopicmst) UIUtils.setBeanProperties(
						(Object) newEntTlTopicmst, request);
				String KeyID = (String) httpSession.getAttribute("TopicKeyId");
				String trParentId = request.getParameter("trParentId");
				if (!UIUtils.isValidKeyId(newEntTlTopicmst.getTopiKeyid()))
					newEntTlTopicmst.setTopiKeyid(KeyID);// KeyId
				entTlTopicmstBean = (EntTlTopicmstBean) UIUtils
						.setBeanProperties((Object) entTlTopicmstBean, request);
				String saveMsg;
				// CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()"
				// + newQtmTlDockaudit.getQaudkeyid());
				if (newEntTlTopicmst.getTopiKeyid() == null) {
					CommonMessage.debugMsg("key id is not available");
					entTlTopicmstBean.setFormMode(FormModes.create);
					existEntTlTopicmst = entTlTopicmstService.create(
							newEntTlTopicmst, existEntTlTopicmst, trParentId);
					saveMsg = "Data Saved Successfully";
				} else {
					CommonMessage.debugMsg("key id is available:"
							+ newEntTlTopicmst.getTopiKeyid());
					entTlTopicmstBean.setFormMode(FormModes.modify);
					existEntTlTopicmst = entTlTopicmstService.update(
							newEntTlTopicmst, existEntTlTopicmst);
					saveMsg = "Data Updated Successfully";
				}

				httpSession.setAttribute(existEntTlTopicmst.getTopiKeyid(),
						existEntTlTopicmst);
				httpSession.setAttribute("entTlTopicmst", existEntTlTopicmst);
				String formBeanIdentifier = "entTlTopicmstBean"
						+ entTlTopicmstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier, entTlTopicmstBean);

				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("Save completed");
				String openFileMgr = request.getParameter("openFileManager");
				if (UIUtils.isValidKeyId(openFileMgr))
					returnData.put("openFileMgr", "Y");
				returnData.put("keyId", existEntTlTopicmst.getTopiKeyid());
				successData.put("msg", saveMsg);
				returnData.put("formClear", false);
				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "EntTlTopicmst");
			errMessage.put("fromMode", entTlTopicmstBean.getFormActionMode());
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {
			net.sf.json.JSONObject errMessage = UIUtils
					.businessValidationExceptions(e.toString(), "EntTlTopicmst");
			errMessage.put("tpmException", "");
			out.print(errMessage.toString());
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}

	}

	/*
	 * private void validateSkill(HttpServletRequest request,
	 * HttpServletResponse response,PrintWriter out ) throws IOException{ String
	 * elementId = request.getParameter("keyId"); String validate=null;
	 * //CommonMessage.debugMsg("Element Id : "+elementId); EntTlTopicmst
	 * entTlTopicmst = new EntTlTopicmst();
	 * entTlTopicmst.setTopiKeyid(elementId); try { validate =
	 * entTlTopicmstService.validateSkillLevel(entTlTopicmst);
	 * CommonMessage.debugMsg("validate : "+validate); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } JSONObject
	 * successData = new JSONObject(); successData.put("msg",validate);
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData); out.print(returnData.toString()); } private void
	 * validateDelSkill(HttpServletRequest request, HttpServletResponse
	 * response,PrintWriter out ) throws IOException{ String elementId =
	 * request.getParameter("keyId"); String validate=null;
	 * //CommonMessage.debugMsg("Element Id : "+elementId); EntTlTopicmst
	 * entTlTopicmst = new EntTlTopicmst();
	 * entTlTopicmst.setTopiParentid(elementId); try { validate =
	 * entTlTopicmstService.validateDelSkillLevel(entTlTopicmst);
	 * CommonMessage.debugMsg("validate : "+validate); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } JSONObject
	 * successData = new JSONObject(); successData.put("msg",validate);
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData); out.print(returnData.toString()); }
	 */
	private void deleteSkil(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out) throws IOException {
		String elementId = request.getParameter("keyId");
		// CommonMessage.debugMsg("Element Id : "+elementId);
		EntTlTopicmst entTlTopicmst = new EntTlTopicmst();
		entTlTopicmst.setTopiKeyid(elementId);
		try {
			entTlTopicmst = entTlTopicmstService.delete(entTlTopicmst);
			JSONObject successData = new JSONObject();
			successData.put("msg", "Deleted");
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.print(returnData.toString());
		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(
					e.toString(), "EntTlTopicmst");
			out.print(errMessage.toString());
		} catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("BusinessApplicationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils
					.businessValidationExceptions(e.toString(), "EntTlTopicmst");
			errMessage.put("tpmException", "Topic Name Already Referred");
			errMessage.put("displyMsg", false);
			out.print(errMessage.toString());
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}

	private void LoadTopicEntry(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		String userEvent = request.getParameter("userEvent");
		String parentId = request.getParameter("parentId");
		String locationId = request.getParameter("locationId");
		String elementType = request.getParameter("elementType");
		String keyid = request.getParameter("keyId");
		String trParentId = request.getParameter("trParentId");
		String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
		FormModes mode = FormModes.create;
		String TopicMode = request.getParameter("mode");
		CommonMessage.debugMsg("TopicMode................." + TopicMode);
		CommonMessage.debugMsg("locationId................." + locationId);
		CommonMessage.debugMsg("elementType................." + elementType);
		if (formMode != null && formMode.equalsIgnoreCase(FormModeConsts.view))
			mode = FormModes.view;
		else if (formMode != null
				&& formMode.equalsIgnoreCase(FormModeConsts.modify))
			mode = FormModes.modify;

		// CommonMessage.debugMsg("parentId:" + parentId);
		// CommonMessage.debugMsg("keyid:" + keyid);
		response.setContentType("text/html");
		EntTlTopicmstBean entTlTopicmstBean = new EntTlTopicmstBean(mode);

		httpSession.removeAttribute("TopicKeyId");
		httpSession.removeAttribute("entTlTopicmstBean");
		httpSession.removeAttribute("entTlTopicmst");
		httpSession.removeAttribute("TopicMode");

		EntTlTopicmst entTlTopicmst = new EntTlTopicmst();
		try {

			if ((UIUtils.isValidKeyId(keyid) && userEvent == null)
					|| (userEvent != null && !userEvent.equals("new"))) {
				entTlTopicmst.setTopiKeyid(keyid);
				entTlTopicmst.setTopiParentid("{}");
				entTlTopicmst = entTlTopicmstService.select(entTlTopicmst);
				httpSession.setAttribute("TopicKeyId", keyid);
				// CommonMessage.debugMsg("SkilFactKeyid..."+entTlTopicmst.getSkilFactKeyid());
			} else {
				entTlTopicmst = (EntTlTopicmst) UIUtils.setBeanProperties(
						entTlTopicmst, request);
				entTlTopicmst.setTopiLocationid(locationId);
				entTlTopicmst.setTopiParentid(parentId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("entTlTopicmst", entTlTopicmst);
		request.setAttribute("entTlTopicmstBean", entTlTopicmstBean);
		request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);
		request.setAttribute("mode", TopicMode);
		request.setAttribute("trainAreaParentId", trParentId);
		request.setAttribute("inactMsg", UIUtils.getPropertyValue(
				"com.akranta.tpm.resources.CommonMessages", "inactive-confirm"));
		httpSession.setAttribute("entTlTopicmst", entTlTopicmst);
		httpSession.setAttribute("entTlTopicmstBean", entTlTopicmstBean);
		httpSession.setAttribute("TopicMode", mode);
	}

}
