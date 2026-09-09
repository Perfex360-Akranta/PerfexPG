package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlActplnNonemployee;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlResponsibilitylink;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.ActionPlanRptService;
import com.akranta.tpm.service.impl.ActionPlanRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ActionPlanRptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionPlanRptService actionPlanRptService;

	public ActionPlanRptServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 HttpSession s = request.getSession(false);

		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action " + action);
       
		actionPlanRptService = (ActionPlanRptServiceImpl) UIUtils.getServiceObject(request, "ActionPlanRptServiceImpl");
		
		actionPlanRptService.ActionPlanRptServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
		
		
		

		if (action.equals("multiEmpSelect_input.api")) {
			String refKeyId = request.getParameter("refKeyId");
			String flid = request.getParameter("flid");
			String refdoctype = request.getParameter("refdoctype");

			request.setAttribute("refKeyId", refKeyId);
			request.setAttribute("flid", flid);
			request.setAttribute("refdoctype", refdoctype);
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/MultiEmployeeSelect.jsp");
			rd.forward(request, response);

		}

		else if (action.equals("Combo_Status.api")) {

			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.ActionPlanColModel",
					"colModelActionPlanStatus"));
		} else if (action.equals("others_getCol.api")) {
			PrintWriter out = response.getWriter();
			String refKeyId = request.getParameter("refKeyId");
			CommonMessage.debugMsg("refKeyId::::::::::" + refKeyId);
			HttpSession httpSession = request.getSession(false);
			JSONObject jsonObject = new JSONObject();
			List<String[]> MultiselectEmp = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setActionKeyId(refKeyId);
				MultiselectEmp = actionPlanRptService.getOthers(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonObject = getTableModelForOthers(MultiselectEmp);
			out.println(jsonObject);

		} else if (action.equals("others_getData.api")) {

			String refKeyId = request.getParameter("refKeyId");

			CommonMessage.debugMsg("refKeyId::::::::::" + refKeyId);
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();
			try {

				commonFilter.setActionKeyId(refKeyId);
				List<String[]> multiselectEmp = actionPlanRptService
						.getOthers(commonFilter);
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(
						multiselectEmp, request, 1, 0);
				out.println(Achievements);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("multiEmpSelect_getCol.api")) {
			PrintWriter out = response.getWriter();
			String refKeyId = request.getParameter("refKeyId");
			String flid = request.getParameter("flid");
			String refdoctype = request.getParameter("refdoctype");
			CommonMessage.debugMsg("refKeyId::::::::::" + refKeyId);
			CommonMessage.debugMsg("refdoctype::::::::::" + refdoctype);
			HttpSession httpSession = request.getSession(false);
			JSONObject jsonObject = new JSONObject();
			List<String[]> MultiselectEmp = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				// commonFilter.setFlid(flid);
				commonFilter.setActionKeyId(refKeyId);
				commonFilter.setType(refdoctype);
				commonFilter.setFlid(flid);
				MultiselectEmp = actionPlanRptService
						.getMultiSelectEmp(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonObject = getTableModelMultiSelectEmp(MultiselectEmp);
			httpSession.removeAttribute("ActionPlanColModel");
			httpSession.setAttribute("ActionPlanColModel", jsonObject);
			out.println(jsonObject);

		}

		else if (action.equals("multiEmpSelect_getData.api")) {
			List<String[]> MultiselectEmp = null;
			String refKeyId = request.getParameter("refKeyId");
			String refdoctype = request.getParameter("refdoctype");
			String jh = request.getParameter("jh");
			String dmt = request.getParameter("dmt");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg("refKeyId::::::::::" + refKeyId);
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setDmt(dmt);
				commonFilter.setJH(jh);
				commonFilter.setFlid(flid);
				commonFilter.setActionKeyId(refKeyId);
				commonFilter.setType(refdoctype);
				List<String[]> multiselectEmp = actionPlanRptService
						.getMultiSelectEmp(commonFilter);
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(
						multiselectEmp, request, 1, 0);
				out.println(Achievements);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (action.equals("ActionPlanGrid_input.api")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/ActionPlanMasterGrid.jsp");
			rd.forward(request, response);
		} else if (action.equals("ActionPlanGrid_getCol.api")) {
			PrintWriter out = response.getWriter();
			HttpSession httpSession = request.getSession(false);
			JSONObject jsonObject = new JSONObject();
			List<String[]> MasterGrid = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter = populateCommonFilter(request,"ActioPlanCommonFilter", true);
				MasterGrid = actionPlanRptService.getActionPlanReport(commonFilter);
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);

				gridColModel.setHeaderNum(1);

				String[] colHeader = MasterGrid.get(2);
				String[] colHeaderCond = MasterGrid.get(1);
				List<String[]> headers = new ArrayList<String[]>();
				// headers.add(colHeaderCond);
				headers.add(colHeader);

				jsonObject = UIUtils.getTableModel(headers, colHeaderCond,
						jqGridTableModel, gridColModel);
				jsonObject.set("tableHeight", "86%%");
				jsonObject.set("tableWidth", "108%%");
				httpSession.removeAttribute("ActionPlanColModel");
				httpSession.setAttribute("ActionPlanColModel", jsonObject);
				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} else if (action.equals("ActionPlanGrid_getData.api")) {

			try {
				CommonFilter commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter = populateCommonFilter(request,
						"ActioPlanCommonFilter", false);
				List<String[]> MasterGrid = actionPlanRptService
						.getActionPlanReport(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject masteReportGridmod = UIUtils
						.convertToJqGridTableObject(MasterGrid, request, 3, 0);
				out.println(masteReportGridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("ActionPlanmodify_getExcel.api")) {

			String KeyId = request.getParameter("KeyId");
			CommonFilter commonFilter = populateCommonFilter(request,
					"ActioPlanCommonFilter", false);
			try {
				HttpSession httpSession = request.getSession(false);
				String aplmRefdocid = (String) httpSession
						.getAttribute("aplmRefdocid");
				if (UIUtils.isValidKeyId(aplmRefdocid)) {
					commonFilter.setRefdocid(aplmRefdocid);
					String tmpFromRow = commonFilter.getFromRow();
					httpSession = request.getSession(false);
					JSONObject colmodel = (JSONObject) httpSession
							.getAttribute("ActionPlanDetailColModel");
					colmodel.put("title", "Action Plan Detail Report");
					String format = ExcelUtils.getFormat(request);
					Workbook wb = actionPlanRptService
							.actionPlanDetailExportExcel(commonFilter,
									colmodel, format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb,
							"ActionPlanDetailReport", format);

				}

				else {
					commonFilter.setActionKeyId(KeyId);
					FilterValues.getCommonFilters(request, commonFilter);
					String tmpFromRow = commonFilter.getFromRow();
					httpSession = request.getSession(false);
					JSONObject colmodel = (JSONObject) httpSession
							.getAttribute("ActionPlanDetailColModel");
					colmodel.put("title", "Action Plan Detail Report");
					String format = ExcelUtils.getFormat(request);
					Workbook wb = actionPlanRptService
							.actionPlanDetailExportExcel(commonFilter,
									colmodel, format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb,
							"ActionPlanDetailReport", format);
				}
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

			// ///

		} else if (action.equals("ActionPlanGrid_getExcel.api")) {
			// HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,
					"ActioPlanCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			// httpSession = request.getSession(false);
			JSONObject colmodel = UIUtils.getXlColModel(request, response);// (JSONObject)
																			// httpSession.getAttribute("ActionPlanColModel");
			colmodel.put("title", "Action Plan Report");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = actionPlanRptService.actionPlanExportExcel(
					commonFilter, colmodel, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils
					.writeToResponse(response, wb, "ActionPlanReport", format);
		}
		/******** For Disabling the form field**********/
		
		else if (action.equals("ActionPlan_input.api")) {
			
			HttpSession httpSession = request.getSession(false);
			String actPlanKeyId = request.getParameter("actPlanKeyId");
			CommonMessage.debugMsg("actPlanKeyId::::::"+actPlanKeyId);
			String allotedBy=request.getParameter("allotedBy");
	        CommonMessage.debugMsg("allotedBy::::::"+allotedBy);
	        String CheckYN=request.getParameter("checkYNo");
	        CommonMessage.debugMsg("The Check Y & N"+CheckYN);
			String actPlanRefMasId = request.getParameter("actPlanRefMasId");
			String actPlanRefDocType = request.getParameter("actPlanRefDocType");
			String aplmFlid = request.getParameter("flid");
			String actPlanMainTask = request.getParameter("actPlanMainTask");
			String actPlanRefDtlId = request.getParameter("actPlanRefDtlId");
			String taskid = request.getParameter("taskid");
			String keyId = request.getParameter("keyId");
			String grid = request.getParameter("grid");
			String type = request.getParameter("type");
			String pillarid = request.getParameter("pillarid");
			String fromMode=request.getParameter("fromMode");
			String actPlanRefDate = request.getParameter("actPlanRefDate");
			String apMode = request.getParameter("apMode");
		
			GenTlActionplanmst newGenTlActionplanmst = new GenTlActionplanmst();
			if(actPlanRefDocType!=null) {
			actPlanRefDocType=actPlanRefDocType.toUpperCase();
			}
			
			if (UIUtils.isValidKeyId(actPlanKeyId)|| UIUtils.isValidKeyId(actPlanRefDtlId)|| UIUtils.isValidKeyId(actPlanRefDocType)
				|| UIUtils.isValidKeyId(aplmFlid)|| UIUtils.isValidKeyId(actPlanMainTask)|| UIUtils.isValidKeyId(actPlanRefMasId)
				|| UIUtils.isValidKeyId(actPlanRefDate) ) {//&& UIUtils.isValidKeyId(fromMode)
				CommonMessage.debugMsg("actPlanRefDocType......"+ actPlanRefDocType);
				CommonMessage.debugMsg("actPlanMainTask....."+ actPlanMainTask);
				CommonMessage.debugMsg("actPlanRefMasId........"+ actPlanRefMasId);
				CommonMessage.debugMsg("actPlanRefDtlId........"+ actPlanRefDtlId);
				CommonMessage.debugMsg("actPlanKeyId........" + actPlanKeyId);
				newGenTlActionplanmst.setAplmFlid(aplmFlid);
				newGenTlActionplanmst.setAplmKeyid(actPlanKeyId);
				newGenTlActionplanmst.setAplmDetailrefid(actPlanRefDtlId);
				newGenTlActionplanmst.setAplmRefdoctype(actPlanRefDocType);
				newGenTlActionplanmst.setAplmMasterrefid(actPlanRefMasId);
				newGenTlActionplanmst.setAplmMaintask(actPlanMainTask);
				newGenTlActionplanmst.setAplmPlandate(actPlanRefDate);
				newGenTlActionplanmst.setAplmCreatedby(allotedBy);
			}
			try {
				if(UIUtils.isValidKeyId(fromMode) && "reminder".equals(fromMode))
					newGenTlActionplanmst = actionPlanRptService.getAllFillRemainerControl(actPlanKeyId);
				else
				    newGenTlActionplanmst = actionPlanRptService.getAllFillControl(newGenTlActionplanmst);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("newGenTlActionplanmst", newGenTlActionplanmst);// Set
			httpSession.setAttribute("newGenTlActionplanmst",newGenTlActionplanmst);
			
			httpSession.setAttribute("aplmRefdocid", actPlanRefMasId);
			request.setAttribute("taskid", taskid);
			request.setAttribute("apMode", apMode);
			request.setAttribute("actPlanRefDate", actPlanRefDate);
			request.setAttribute("actPlanMainTask", actPlanMainTask);
			request.setAttribute("type", type);
			request.setAttribute("pillarid", pillarid);
			request.setAttribute("CheckYN",CheckYN);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ActionPlanRpt.jsp");
			rd.forward(request, response);
			// CommonMessage.debugMsg(" response " + response);
		} else if (action.equals("actionPlanDetail_modify.api")) {
			CommonMessage.debugMsg(" action " + action);

			String keyId = request.getParameter("keyId");
			CommonMessage.debugMsg(" keyId " + keyId);
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			try {
				GenTlActionplanmst newGenTlActionplanmst = new GenTlActionplanmst();
				newGenTlActionplanmst = actionPlanRptService
						.getActionPlanDetailAdd(keyId);
				jsonObject.put("detailData", newGenTlActionplanmst);
				out.println(jsonObject);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (action.equals("ActionPlanresponsibility_getCol.api")) {
			
			String KeyId = request.getParameter("KeyId");
			String refid = request.getParameter("refid");
			String location = request.getParameter("location");
			String others = request.getParameter("others");
			String flid = request.getParameter("flid");
			String role = request.getParameter("role");
			String roleId = request.getParameter("roleId");
			String cellid =request.getParameter("cellId");
			String trade =request.getParameter("trade");
			String tradeid =request.getParameter("tradeid");
			String pillarid =request.getParameter("pillarid");
			String type =request.getParameter("type");
			String untick = request.getParameter("untick");
			
			CommonMessage.debugMsg(" action " + action);
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			List<String[]> detailGrid = null;
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,
					"ActioPlanCommonFilterDetail", true);
			try {
				//commonFilter.setActionKeyId(KeyId);

				ComboFilter locationId=new  ComboFilter();
				
				CommonMessage.debugMsg(" getCol :: roleId "+roleId+" tradeid :: "+tradeid+" type :: "+type);
				
				if (UIUtils.isValidKeyId(flid)) 
					commonFilter.setFlid(flid);
			
				if(UIUtils.isValidKeyId(roleId)&& UIUtils.isValidKeyId(tradeid)){
					CommonMessage.debugMsg(" getCol :: roleId :: 11 ");
					commonFilter.setTrarId(tradeid);
					commonFilter.setKey(roleId);            //setting roleId 
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
					
				}else if(UIUtils.isValidKeyId(tradeid)){
					CommonMessage.debugMsg(" getCol :: roleId :: 22 ");
					commonFilter.setTrarId(tradeid);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
					
				}else if(UIUtils.isValidKeyId(roleId)){
					CommonMessage.debugMsg(" getCol :: roleId :: 33 ");
					commonFilter.setKey(roleId);   //setting roleId
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}else if (!UIUtils.isValidKeyId(roleId) && !UIUtils.isValidKeyId(tradeid)){
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}
					
				
				if(("JH".equals(type)||"Production".equals(type)||"Dmt".equals(type)||"Others".equals(type)) && UIUtils.isValidKeyId(type)){
					commonFilter.setType(type);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}else if("Pillar".equals(type) && UIUtils.isValidKeyId(type)){
					commonFilter.setType(type);
					commonFilter.setPillarWise(pillarid);
					commonFilter.setRefdocid(refid);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}
					
				
				/*if(UIUtils.isValidKeyId(others)){
					locationId.setId(location);
					commonFilter.setLocation(locationId);
					commonFilter.setOther(others);
				}
				
				if ("Trade".equals(role)||"otherTrd".equals(role)){ 
					commonFilter.setCellId(cellid);
					commonFilter.setTrarId(tradeid);
					locationId.setId(location);
					commonFilter.setLocation(locationId);
					commonFilter.setActionKeyId(roleId);
					commonFilter.setFlid(flid);
				}
				
				//ComboFilter roleIds=new  ComboFilter();
				if(UIUtils.isValidKeyId(role)){
					locationId.setId(location);
					//roleIds.setId(roleId);
					commonFilter.setAp(role);
					commonFilter.setLocation(locationId);
					commonFilter.setRoleLevel(role);
					commonFilter.setActionKeyId(roleId);
					commonFilter.setCellId(cellid);
					commonFilter.setTrarId(tradeid);
					commonFilter.setFlid(flid);
				}
				
				CommonMessage.debugMsg(roleId+" roleId :: "+untick);
				
				if(UIUtils.isValidKeyId(untick))
					commonFilter.setAbnImp(roleId);
				
				CommonMessage.debugMsg(" getAbnImp :: "+commonFilter.getAbnImp());
				*/
				commonFilter.setIsGetCol("Y");
				detailGrid = actionPlanRptService
						.getActionPlanresponsibilitygrid(commonFilter);
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setSortable(false);			 
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);	
				jqGridTableModel.setGridEdit(true);
				 
				gridColModel.setHeaderNum(1);
				String[] colHeader = detailGrid.get(1);
				String[] colHeaderCond = detailGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				jsonObject = UIUtils.getTableModel(headers, colHeaderCond,
						jqGridTableModel, gridColModel);
				jsonObject.set("tableHeight", "30%%");
				jsonObject.set("tableWidth", "34%%");
				CommonMessage.debugMsg(" action end table ");
				httpSession.removeAttribute("ActioPlanCommonFilterDetail");
				httpSession
						.setAttribute("ActionPlanDetailColModel", jsonObject);

				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} 
		
		else if (action.equals("ActionPlanresponsibility_getData.api")) {
			
			CommonFilter commonFilter = populateCommonFilter(request,"ActioPlanCommonFilterDetail", false);
			try {
				HttpSession httpSession = request.getSession(false);
				String KeyId = request.getParameter("KeyId");
				String refid = request.getParameter("refid");
				String location = request.getParameter("location");
				String others = request.getParameter("others");
				String flid = request.getParameter("flid");
				String role = request.getParameter("role");
				String roleId = request.getParameter("roleId");
				String cellid =request.getParameter("cellId");
				String tradeid =request.getParameter("tradeid");
				String pillarid =request.getParameter("pillarid");
				String type =request.getParameter("type");
				String untick = request.getParameter("untick");
				ComboFilter locationId=new  ComboFilter();
				
				if (UIUtils.isValidKeyId(flid)) 
					commonFilter.setFlid(flid);
				
				CommonMessage.debugMsg(" getData :: roleId "+roleId+" tradeid :: "+tradeid+" type :: "+type);
				
				if(UIUtils.isValidKeyId(roleId)&& UIUtils.isValidKeyId(tradeid)){
					CommonMessage.debugMsg(" getCol :: roleId :: 11 ");
					commonFilter.setTrarId(tradeid);
					commonFilter.setKey(roleId);      //setting roleId
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
					
				}else if(UIUtils.isValidKeyId(tradeid)){
					CommonMessage.debugMsg(" getCol :: roleId :: 22 ");
					commonFilter.setTrarId(tradeid);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
					
				}else if(UIUtils.isValidKeyId(roleId)){
					CommonMessage.debugMsg(" getCol :: roleId :: 33 ");
					commonFilter.setKey(roleId);    //setting roleId
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}else if (!UIUtils.isValidKeyId(roleId) && !UIUtils.isValidKeyId(tradeid)){
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}
				
				
				if(("JH".equals(type)||"Production".equals(type)||"Dmt".equals(type)||"Others".equals(type)) && UIUtils.isValidKeyId(type)){
					commonFilter.setType(type);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}else if("Pillar".equals(type) && UIUtils.isValidKeyId(type)){
					commonFilter.setType(type);
					commonFilter.setPillarWise(pillarid);
					commonFilter.setRefdocid(refid);
					if(UIUtils.isValidKeyId(others)){
					     commonFilter.setOther(others);
					     locationId.setId(location);
					     commonFilter.setLocation(locationId);
					}
				}

                 
				
				/*if(UIUtils.isValidKeyId(others)){
					locationId.setId(location);
					commonFilter.setLocation(locationId);
					commonFilter.setOther(others);
				}
				
				if ("Trade".equals(role)||"otherTrd".equals(role)){ 
					commonFilter.setCellId(cellid);
					commonFilter.setTrarId(tradeid);
					locationId.setId(location);
					commonFilter.setLocation(locationId);
					commonFilter.setActionKeyId(roleId);
					commonFilter.setFlid(flid);
				}
				
				ComboFilter roleIds=new  ComboFilter();
				if(UIUtils.isValidKeyId(role)){
					locationId.setId(location);
					roleIds.setId(roleId);
					commonFilter.setLocation(locationId);
					commonFilter.setAp(role);
					//commonFilter.setRoleId(roleIds);
					commonFilter.setCellId(cellid);
					commonFilter.setActionKeyId(roleId);
					commonFilter.setTrarId(tradeid);
					commonFilter.setFlid(flid);
				}
				
                CommonMessage.debugMsg(roleId+" roleId :: "+untick);
				
				if(UIUtils.isValidKeyId(untick))
					commonFilter.setAbnImp(roleId);
				
				CommonMessage.debugMsg(" getAbnImp :: "+commonFilter.getAbnImp());
				*/
				
				FilterValues.getCommonFilters(request, commonFilter);
				
				commonFilter.setIsGetCol("N");
				List<String[]> actionPlanGrid = actionPlanRptService
						.getActionPlanresponsibilitygrid(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject deptreportgridDetail = UIUtils.convertToJqGridTableObject(actionPlanGrid,request,2,0,commonFilter.getTotalRecordCnt());
				out.println(deptreportgridDetail);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if (action.equals("ActionPlanmodify_getCol.api")) {
			String KeyId = request.getParameter("KeyId");
			CommonMessage.debugMsg(" action " + action);
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			List<String[]> detailGrid = null;
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,
					"ActioPlanCommonFilterDetail", true);
			try {
				commonFilter.setActionKeyId(KeyId);

				detailGrid = actionPlanRptService
						.getActionPlanDetail(commonFilter);
				JqGridTableModel jqGridTableModel = new JqGridTableModel();
				GridColModel gridColModel = new GridColModel();

				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false);

				gridColModel.setHeaderNum(1);

				String[] colHeader = detailGrid.get(1);
				String[] colHeaderCond = detailGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				jsonObject = UIUtils.getTableModel(headers, colHeaderCond,
						jqGridTableModel, gridColModel);
				jsonObject.set("tableHeight", "20%%");
				jsonObject.set("tableWidth", "94%%");
				CommonMessage.debugMsg(" action end table ");
				httpSession.removeAttribute("ActionPlanDetailColModel");
				httpSession
						.setAttribute("ActionPlanDetailColModel", jsonObject);

				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (action.equals("ActionPlanmodify_getData.api")) {
			CommonMessage.debugMsg(" action " + action);
			String KeyId = request.getParameter("KeyId");
			CommonFilter commonFilter = populateCommonFilter(request,
					"ActioPlanCommonFilterDetail", false);
			try {
				CommonMessage.debugMsg("in get Data method");
				HttpSession httpSession = request.getSession(false);

				String aplmRefdocid = request.getParameter("aplmRefdocid");
				String aplmRefDtlid = request.getParameter("aplmRefDtlid");

				CommonMessage.debugMsg("aplmRefdocid...." + aplmRefdocid);
				CommonMessage.debugMsg("KeyId...." + KeyId);
				CommonMessage.debugMsg("aplmRefDtlid...." + aplmRefDtlid);

				if (UIUtils.isValidKeyId(aplmRefDtlid)) {
					commonFilter.setKey(aplmRefDtlid);
				} 
				else
					commonFilter.setKey("");
				
					if (UIUtils.isValidKeyId(aplmRefdocid))
						commonFilter.setRefdocid(aplmRefdocid);
					
					if (UIUtils.isValidKeyId(KeyId))
						commonFilter.setActionKeyId(KeyId);
				
					FilterValues.getCommonFilters(request, commonFilter);
				CommonMessage.debugMsg("in get Data method");
				List<String[]> actionPlanGrid = actionPlanRptService
						.getActionPlanDetail(commonFilter);
				CommonMessage.debugMsg("in get Data method eeeeeee"
						+ actionPlanGrid.get(0)[0]);
				PrintWriter out = response.getWriter();
				JSONObject deptreportgridDetail = UIUtils.convertToJqGridTableObject(actionPlanGrid, request, 2,0);
				httpSession.removeAttribute("ActioPlanCommonFilterDetail");
				out.println(deptreportgridDetail);

			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		
		 else if(action.equals("momgroupbasedemployee.api"))
			{				
			 try{
					String refid=request.getParameter("refid");
					CommonMessage.debugMsg(" flid :: "+refid);
					String reftype=request.getParameter("reftype");
					StringBuffer cond = new StringBuffer();
					
					CommonMessage.debugMsg(" reftype "+reftype+" refid "+refid);
					
					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

					cond.append(" AND MOMA_EMPLOYEEID=EMPM_KEYID AND MOMA_MOMS_KEYID='"+refid+"' ");
					
					empComboFilter.setCondSql( cond.toString());
					
					List<ComboBox> probfilcomboList = actionPlanRptService.getmomgroupbasedemployee(empComboFilter,"");
					UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
					
				
			   }
				catch (Exception e) {				
					e.printStackTrace();
				}	
			}
		

		
		
		else if(action.equals("momjhmemberemployee.api"))
		{				
		 try{
		
			    StringBuffer cond = new StringBuffer();
				String flid=request.getParameter("flid");
				
				cond.append(" AND empm_keyid=frt_empm_keyid AND flid=frt_fnln_keyid and FRT_ROLE_KEYID= ROLE_KEYID");
				cond.append("  AND ROLE_NAME IN ( 'JH MEMBER', 'JH LEADER') ");
				cond.append(" AND(INSTR (parentflids || '-' || flid, '"+flid+"' ) >0) AND EMPM_ACTIVE='Y' ");
                
                ComboFilter empComboFilter = UIUtils.fillComboFilter(request);
				empComboFilter.setCondSql( cond.toString());
				
				ComboFilter comboFilter = new ComboFilter();
				
				List<ComboBox> probfilcomboList = actionPlanRptService.getmomjhmemberemployee(empComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
				
				
				
			
		   }
			catch (Exception e) {				
				e.printStackTrace();
			}	
		}
//		 else if(action.equals("mompillargroupbasedemployee.api"))
//			{				
//			 try{
//					String refid=request.getParameter("refid");
//					CommonMessage.debugMsg(" flid :: "+refid);
//					String reftype=request.getParameter("reftype");
//					StringBuffer cond = new StringBuffer();
//					String pillarid=request.getParameter("pillarid");
//					String type=request.getParameter("type");
//					String flid=request.getParameter("flid");
//					
//                    if("JH".equals(type) || "Others".equals(type)){
//						cond.append(" AND empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID = ROLE_KEYID(+) ");
//						cond.append(" AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID ");
//						cond.append(" AND MOMA_MOMS_KEYID = MOMS_KEYID (+) AND EMPM_ACTIVE='Y' ");
//						if("Others".equals(type))
//							cond.append(" AND FLID = '"+flid+"' ");
//						else
//							cond.append(" AND ( INSTR (parentflids || '-' || flid, '"+flid+"' ) >0) ");
//						//cond.append(" group by empm_keyid,empm_name ");
//						cond.append(" AND MOMA_MOMS_KEYID(+)='' ");
//						 
//					}else{
//						cond.append(" AND empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID = ROLE_KEYID(+) ");
//						cond.append(" AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID ");
//						cond.append(" AND MOMA_MOMS_KEYID = MOMS_KEYID (+) " );
//						if (!type.equals("Production"))		
//						     cond.append("AND ( INSTR (parentflids || '-' || flid, '"+flid+"' ) >0)");
//						
//						cond.append(" AND MOMA_MOMS_KEYID(+)=''");
//						cond.append(" AND ROLE_KEYID IN ( select mrmp_role_keyid from  GEN_TL_MEETINGTYPE_ROLE_MAP where mrmp_meeting_type = upper('"+type+"')");
//						cond.append(" AND EMPM_ACTIVE='Y' and mrmp_pillar_id='"+pillarid+"') ");
//					}
//                    
//                    ComboFilter empComboFilter = UIUtils.fillComboFilter(request);
//					empComboFilter.setCondSql( cond.toString());
//					
//					ComboFilter comboFilter = new ComboFilter();
//					
//					List<ComboBox> probfilcomboList = actionPlanRptService.getmompillargroupbasedemployee(empComboFilter,"");
//					UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
//					
//					//List<ComboBox> probfilcomboList = actionPlanRptService.getmompillargroupbasedemployee(empComboFilter,"",pillarid,type,flid,refid);
//					//UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
//					//UIUtils.writeComboBox(response, probfilcomboList,comboFilter);
//					
//				
//			   }
//				catch (Exception e) {				
//					e.printStackTrace();
//				}	
//			}
		
		else if(action.equals("mompillargroupbasedemployee.api"))
		{				
		 try{
				String refid=request.getParameter("refid");
				CommonMessage.debugMsg(" flid :: "+refid);
				String reftype=request.getParameter("reftype");
				StringBuffer cond = new StringBuffer();
				String pillarid=request.getParameter("pillarid");
				String type=request.getParameter("type");
				String flid=request.getParameter("flid");
				

                if("JH".equals(type) || "Others".equals(type)){
//                	   cond.append(" AND e.empm_active = 'Y' ");
                	   cond.append(" AND empm_active = 'Y' ");
                       //cond.append(" AND ma.moma_moms_keyid IS NULL "); // Oracle MOMA_MOMS_KEYID(+) = ''

                       if("Others".equals(type)) {
                           cond.append(" AND flid = '" + flid + "' ");
                       } else {
                    	   cond.append(" AND POSITION('" + flid + "' IN (parentflids || '-' || flid)) > 0 ");

                       }
					 
				}else{
//					 cond.append(" AND e.empm_active = 'Y' ");
					 cond.append(" AND empm_active = 'Y' ");
			            //cond.append(" AND ma.moma_moms_keyid IS NULL ");

			            if(!"Production".equals(type)) {
			            	cond.append(" AND POSITION('" + flid + "' IN (parentflids || '-' || flid)) > 0 ");

			            }
					

			            cond.append(" AND role_keyid IN ( " +
			                        "SELECT mrmp_role_keyid FROM gen_tl_meetingtype_role_map " +
			                        "WHERE UPPER(mrmp_meeting_type) = UPPER('" + type + "') " +
			                        "AND empm_active = 'Y' " +
			                        "AND mrmp_pillar_id = '" + pillarid + "' " +
			                        ") ");
				}
                
                ComboFilter empComboFilter = UIUtils.fillComboFilter(request);
				empComboFilter.setCondSql( cond.toString());
				
				ComboFilter comboFilter = new ComboFilter();
				
				List<ComboBox> probfilcomboList = actionPlanRptService.getmompillargroupbasedemployee(empComboFilter,"");
				UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
				
				//List<ComboBox> probfilcomboList = actionPlanRptService.getmompillargroupbasedemployee(empComboFilter,"",pillarid,type,flid,refid);
				//UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
				//UIUtils.writeComboBox(response, probfilcomboList,comboFilter);
				
			
		   }
			catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		
		
		

		else if (action.equals("ActionPlan_save.api")) {
			saveActionPlan(request, response);
		} else if (action.equals("MultiSelectEmp_save.api")) {
			saveResponsibilityLink(request, response);
		}

		else if (action.equals("ActionPlan_delete.api")) {
			deleteAllActionPlan(request, response);
		} else if (action.equals("ActionPlandetail_delete.api")) {
			deleteActionPlan(request, response);
		} else if (action.startsWith("actionplanCompletion")) {
			actionPlanCompletion(request, response);
		}//---------------------------
		else if( action.equals("filterXmlactionplanCompletion_view.api")|| action.equals("filterXmlactionplanCompletion_input.api"))
		{
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response, "/tiles/xml/GeneralActionPlanFilter.xml");
		 }
	}

	private void saveResponsibilityLink(HttpServletRequest request,
			HttpServletResponse response) throws Exception,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			JSONObject successData = new JSONObject();
			JSONObject Successmsg = new JSONObject();
			String savemsg = "";
			GenTlResponsibilitylink genTlResponsibilitylink = new GenTlResponsibilitylink();
			GenTlResponsibilitylink existGenTlResponsibilitylink = (GenTlResponsibilitylink) httpSession
					.getAttribute("genTlResponsibilitylink");
			genTlResponsibilitylink = (GenTlResponsibilitylink) UIUtils
					.setBeanProperties((Object) genTlResponsibilitylink,
							request);
			String gridData = request.getParameter("employeeData");
			String name = request.getParameter("txtNactName");
			String refDocId = request.getParameter("txtRsplRefdocid");
			CommonMessage.debugMsg("gridData Json Array: " + gridData);
			List<GenTlResponsibilitylink> lstGenTlResponsibilitylink = null;
			JSONArray jsonResponLinkReport = null;
			if (UIUtils.isValidKeyId(gridData)) {
				if (gridData != null && !gridData.isEmpty()) {
					jsonResponLinkReport = JSONArray.fromString(gridData);
					lstGenTlResponsibilitylink = (List<GenTlResponsibilitylink>) UIUtils
							.convertJSONArrToList(genTlResponsibilitylink,
									jsonResponLinkReport);
				}
				if (genTlResponsibilitylink != null) {
					CommonFunctions
							.debugMsg("size lstGenTlNearmissreportdtl..."
									+ lstGenTlResponsibilitylink.size());
					genTlResponsibilitylink
							.setResposiablelinkDtl(lstGenTlResponsibilitylink);

					if (name != null) {
						GenTlActplnNonemployee actplnNonemployee = new GenTlActplnNonemployee();
						actplnNonemployee.setNactName(name);
						actplnNonemployee.setNactRefdocid(refDocId);
						genTlResponsibilitylink
								.setGenTlActplnNonemployee(actplnNonemployee);
					}
					CommonFunctions
							.debugMsg("lstGenTlNearmissreportdtl.size(): "
									+ lstGenTlResponsibilitylink.size());
					for (int i = 0; i < lstGenTlResponsibilitylink.size(); i++) {
						CommonMessage.debugMsg("txtEmployeeId::["
								+ i
								+ "]"
								+ lstGenTlResponsibilitylink.get(i)
										.getRsplEmployeeid());
						CommonMessage.debugMsg(lstGenTlResponsibilitylink
								.get(i).getFlag()
								+ " ~~~~~:::::"
								+ lstGenTlResponsibilitylink.get(i)
										.getRsplKeyid());
						// CommonMessage.debugMsg("txtNmuakeyid:::["+i+"]"+lstGenTlResponsibilitylink.get(i).getNmuaKeyid());
						// newGenTlNearmissreportdtl.setNmuaNearkeyid(lstGenTlNearmissreportdtl.get(i).getNmuaNearkeyid());
						// newGenTlNearmissreportdtl.setNmuaCode(lstGenTlNearmissreportdtl.get(i).getNmuaCode());
						// newGenTlNearmissreportdtl.setNmuaNmrtkeyid(lstGenTlNearmissreportdtl.get(i).getNmuaNmrtkeyid());
						// newGenTlNearmissreportdtl.getNmuaNmrtkeyid();
						// lstGenTlNearmissreportdtl.get(i).setNmuaCreatedby(user.getUsrm_ccno());
					}
				}
			} else if (!UIUtils.isValidKeyId(gridData) && name != null) {
				GenTlActplnNonemployee actplnNonemployee = new GenTlActplnNonemployee();
				GenTlActplnNonemployee existactplnNonemployee = new GenTlActplnNonemployee();
				actplnNonemployee.setNactName(name);
				actplnNonemployee.setNactRefdocid(refDocId);
				if (actplnNonemployee.getNactKeyid() == null) {
					try {
						existactplnNonemployee = actionPlanRptService.create(
								actplnNonemployee, existactplnNonemployee);
						successData.put("msg", savemsg);
						Successmsg.put("successData", successData);
						out.print(Successmsg.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			try {
				if (genTlResponsibilitylink.getRsplKeyid() == null) {
					CommonFunctions
							.debugMsg("genTlNearmissreportmst.getNmrtKeyid(): "
									+ genTlResponsibilitylink.getRsplKeyid());
					CommonFunctions
							.debugMsg("genTlNearmissreportmst.gettargetdate(): "
									+ genTlResponsibilitylink
											.getRsplTargetdate());
					existGenTlResponsibilitylink = actionPlanRptService.create(
							genTlResponsibilitylink,
							existGenTlResponsibilitylink);
					savemsg = " Data Saved Succesfully";
				}
				successData.put("msg", savemsg);
				Successmsg.put("successData", successData);
				out.print(Successmsg.toString());

			} catch (BusinessApplicationExceptions e) {
				JSONObject errMessage = UIUtils.businessValidationExceptions(
						e.toString(), "MultiSelectEmployees");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage);
			} catch (ValidationExceptions e) {

				JSONObject errMessage = UIUtils.validationExceptions(
						e.toString(), "MultiSelectEmployees");
				out.print(errMessage.toString());

			} catch (Exception e) {
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

	private JSONObject getTableModelMultiSelectEmp(List<String[]> headers) {
		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			CommonMessage.debugMsg("colHeader[" + i + "]..." + colHeader[i]);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("center");
			jqGridColModel.setEditable(false);
			if (i == 0) {
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
			}
			if (i == 2) {
				// jqGridColModel.setFormatter("DateFormattor");
				jqGridColModel.setWidth(100);

			}
			if (i == 1 || i == 3 || i == 4 || i == 5 || i == 6) {
				// jqGridColModel.setWidth(100);
				jqGridColModel.setHidden(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "60%%");
		tableModel.set("tableWidth", "50%%");
		tableModel.set("multiSelect", true);
		return tableModel;

	}

	private JSONObject getTableModelForOthers(List<String[]> headers) {
		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			CommonMessage.debugMsg("colHeader[" + i + "]..." + colHeader[i]);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("center");
			jqGridColModel.setEditable(false);
			if (i == 0) {
				jqGridColModel.setHidden(true);
			}
			if (i == 1) {
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
			}
			if (i == 2) {
				jqGridColModel.setHidden(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "60%%");
		tableModel.set("tableWidth", "50%%");
		return tableModel;

	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession
				.getAttribute(beanIdentifier);
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

	private void saveActionPlan(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		CommonFunctions
				.debugMsg("detail key id for master update servlet:::: got action");
		String Type = request.getParameter("Type");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if (httpSession != null && user != null) {
			CommonFunctions
					.debugMsg("detail key id for master update servlet::::");
			GenTlActionplanmst newGenTlActionplanmst = new GenTlActionplanmst();
			GenTlActionplandtl newGenTlActionplandtl = new GenTlActionplandtl();
			//newGenTlActionplanmst.setAplmCreatedby(user.getUsrm_keyid());
			newGenTlActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
			GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession
					.getAttribute("newGenTlActionplanmst");
			newGenTlActionplanmst = (GenTlActionplanmst) UIUtils
					.setBeanProperties((Object) newGenTlActionplanmst, request);

			String actnpln = request.getParameter("actplnDetails");
			String filemanger = request.getParameter("filemanger");
			//String Actionplan = request.getParameter("Actionplan");
			//CommonMessage.debugMsg(" Actionplan 12 "+Actionplan);
			//CommonMessage.debugMsg(" actnpln 12 "+actnpln);
		
			//if(UIUtils.isValidKeyId(Actionplan))
				//newGenTlActionplandtl.setActionplanidenfr(Actionplan);
				
			 List<GenTlActionplandtl> ActGridList1 = null;
	 		 JSONArray ActGridjson1 = null;
	 		 
	 		 if(UIUtils.isValidKeyId(actnpln))
		      {
			     ActGridjson1 = JSONArray.fromString(actnpln);
				 ActGridList1=(List<GenTlActionplandtl>)UIUtils.convertJSONArrToList(newGenTlActionplandtl, ActGridjson1);
				 	if(ActGridList1!= null)
				 	{
				 		newGenTlActionplandtl.setActionplanlist(ActGridList1);
				 	}	
	           }
	 		 
	 		
			GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
					.getAttribute("newGenTlActionplandtl");
			newGenTlActionplandtl = (GenTlActionplandtl) UIUtils
					.setBeanProperties((Object) newGenTlActionplandtl, request);

			CommonMessage.debugMsg("detail key id for master update servlet::::"+ newGenTlActionplanmst.getAplmKeyid());

			CommonMessage.debugMsg("detail key::::"	+ newGenTlActionplandtl.getApldKeyid());
			CommonMessage.debugMsg("detail key:completed::::"+ newGenTlActionplandtl.getApldCompletedby());
			
			String hdnrespn = request.getParameter("hdnrespn");
			String othrsChck = request.getParameter("othrsChck");
	 		   
	 		if(UIUtils.isValidKeyId(hdnrespn))
	 			newGenTlActionplandtl.setApldResponsibility(hdnrespn);

	 		if("Y".equals(othrsChck))
	 			newGenTlActionplandtl.setApldOthers("Y");
	 		else
	 			newGenTlActionplandtl.setApldOthers("N");
	 			
	 			
			JSONObject successData = new JSONObject();
			JSONObject Successmsg = new JSONObject();
			String savemsg;
			try {
				if (newGenTlActionplanmst.getAplmKeyid() == null) {

					existGenTlActionplanmst = actionPlanRptService.create(
							newGenTlActionplanmst, newGenTlActionplandtl);
					savemsg = " Data Saved Succesfully";
				} else {
		
		CommonMessage.debugMsg(" Master update servlet::::  In Servlet :: "+ newGenTlActionplanmst.getAplmKeyid());
		CommonMessage.debugMsg(" MasterRefid :: In Servlet :: "+ newGenTlActionplanmst.getAplmMasterrefid());
		CommonMessage.debugMsg(" DetailRefid :: In Servlet :: "+ newGenTlActionplanmst.getAplmDetailrefid());
		CommonMessage.debugMsg(" Refdoctype() :: In Servlet :: "+ newGenTlActionplanmst.getAplmRefdoctype());			
		
					existGenTlActionplanmst = actionPlanRptService.update(
							existGenTlActionplanmst, newGenTlActionplanmst,
							newGenTlActionplandtl);
					savemsg = "Data Updated Succesfully";
				}
				successData.put("msg", savemsg);
				Successmsg.put("formClear", false);
				Successmsg.put("Type", Type);
				Successmsg.put("keyid", existGenTlActionplanmst.getAplmKeyid());
				Successmsg
						.put("detKeyId", newGenTlActionplandtl.getApldKeyid());
				Successmsg.put("flid", existGenTlActionplanmst.getAplmFlid());
				
				if(UIUtils.isValidKeyId(filemanger)){
				    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
				    Successmsg.put("filemanger",true);
					Successmsg.put("formClear",false);
				}

				Successmsg.put("successData", successData);
				out.print(Successmsg.toString());

			} catch (ValidationExceptions e) {
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils
						.validationExceptions(e.toString(), "ActionPlanRpt");
				out.print(errMessage.toString());
			}  catch(BusinessApplicationExceptions e)
	          {
	    	    CommonMessage.debugMsg(" Inside BussinessApplicationException ");
				e.printStackTrace();
	    	    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ActionPlanRpt");
				//errMessage.put("tpmException","Duplicate Entry");
				//errMessage.put("displyMsg", true);			
				out.print(errMessage.toString());
				//CommonMessage.debugMsg(" e " + errMessage );
			
				}catch (Exception e) {
			}

		}
	}

	private void deleteAllActionPlan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
		try {
			if (httpSession != null && user != null) {
				GenTlActionplanmst oldGenTlActionplanmst = new GenTlActionplanmst();
				oldGenTlActionplanmst = (GenTlActionplanmst) UIUtils
						.setBeanProperties((Object) oldGenTlActionplanmst,
								request);
				JSONObject successData = new JSONObject();
				JSONObject returnData = new JSONObject();
				String savemsg;
				if (UIUtils.isValidKeyId(oldGenTlActionplanmst.getAplmKeyid())) {
					oldGenTlActionplanmst = actionPlanRptService
							.delete(oldGenTlActionplanmst);
					savemsg = " Data Deleted Succesfully";
				} else {
					savemsg = "Data Not Deleted  ";
				}
				successData.put("msg", savemsg);
				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (Exception e) {

		}

	}

	private void deleteActionPlan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
		try {
			if (httpSession != null && user != null) {
				String keyid = request.getParameter("keyid");
				GenTlActionplandtl oldGenTlActionplandtl = new GenTlActionplandtl();
				oldGenTlActionplandtl = (GenTlActionplandtl) UIUtils
						.setBeanProperties((Object) oldGenTlActionplandtl,
								request);
				oldGenTlActionplandtl.setApldKeyid(keyid);
				JSONObject successData = new JSONObject();
				JSONObject returnData = new JSONObject();
				String savemsg;
				if (UIUtils.isValidKeyId(oldGenTlActionplandtl.getApldKeyid())) {
					oldGenTlActionplandtl = actionPlanRptService
							.delete(oldGenTlActionplandtl);
					savemsg = " Data Deleted Succesfully";
				} else {
					savemsg = "Data Not Deleted  ";
					returnData.put("formClear", false);

				}
				successData.put("msg", savemsg);

				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (Exception e) {

		}
	}

	private void actionPlanCompletion(HttpServletRequest request,
			HttpServletResponse response) throws NoDataFoundException,
			Exception {
		String action = UIUtils.getActionPart(request);

		if (action.equals("actionplanCompletion_input.api")) {
			displayForm(request, response, "completion");
		} else if (action.equals("actionplanCompletion_getCol.api")) {
			PrintWriter out = response.getWriter();
			String mode = request.getParameter("mode");
			
			//if(mode.equals("completion")){
				String flid=request.getParameter("flid");
				CommonMessage.debugMsg("The Flid"+flid);
				String TeamChk=request.getParameter("Teamchk");
				CommonMessage.debugMsg("The TeamCheck"+TeamChk);
			//}
			String colModelIdent = "actionplanApproval";
			if ("view".equals(mode))
				colModelIdent = "actionplanApprovalView";
			out.println(UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.ActionPlanColModel",
					colModelIdent));
		} else if (action.equals("actionplanCompletion_getData.api")) {
			fetchGridData(request, response);
		} else if (action.equals("actionplanCompletion_getExcel.api")) {
			
			CommonMessage.debugMsg("actionplan excel1");
			exportActionPlanCompletion(request, response);
		} else if (action.equals("actionplanCompletion_save.api")) {
			saveActionPlanCompletion(request, response);
		} else if (action.equals("actionplanCompletion_view.api")) {
			displayForm(request, response, "view");
		}
		
	}

	private void exportActionPlanCompletion(HttpServletRequest request,
			HttpServletResponse response) throws NoDataFoundException,
			Exception {
		JSONObject colmodel = UIUtils.getXlColModel(request, response);// (JSONObject)
																		// httpSession.getAttribute("ActionPlanColModel");
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		//System.out.print("actionplan excel");
		String mode = request.getParameter("mode");
		if (!"view".equals(mode))
			colmodel.put("title",
					"Pending Action Plan Points " + user.getUsrm_username());
		else
			colmodel.put("title", "Action Plans Employeewise");
		ActionPlanParams actionPlanParams = getActionplanFilterParms(request);

		actionPlanParams.setToRow("-1");
		String format = ExcelUtils.getFormat(request);

		Workbook wb = actionPlanRptService.employeWiseActionPlanExportExcel(
				actionPlanParams, colmodel, format);

		ExcelUtils.writeToResponse(response, wb,
				"ActionPlanPendingReportEmployeewise", format);
	}

	private void displayForm(HttpServletRequest request,
			HttpServletResponse response, String mode) throws Exception {
		     HttpSession httpsession=request.getSession();
		     String loginflid=CommonFunctions.getLoginFlid(request);
			  String loginlevel=CommonFunctions.getLoginLevel(request);
			  String loginElementid = (String) httpsession.getAttribute("loginElementid");
			  AdmTlUsermst user=UIUtils.getLoginUser(request);
		     String empId=user.getUsrm_ccno();
			List<String []> getUserLoginDtl= actionPlanRptService.getElementId(loginflid,loginlevel, loginElementid,empId);
			String elementid = getUserLoginDtl.get(0)[0];
			String flidnew = getUserLoginDtl.get(0)[1];
			String level = getUserLoginDtl.get(0)[2];
			String rolename=getUserLoginDtl.get(0)[3];
			String roledetail=rolename;
		   String rolekeyid=getUserLoginDtl.get(0)[4];
		request.setAttribute("mode", mode);
		request.setAttribute("rolename",rolename);
		request.setAttribute("flid", CommonFunctions.getLoginFlid(request));
		UIUtils.forwardRequest(request, response,
				"/pages/ActionPlanCompletion.jsp");
	}

	private void fetchGridData(HttpServletRequest request,
			HttpServletResponse response) throws NoDataFoundException,
			Exception {
		PrintWriter out = response.getWriter();
		ActionPlanParams actionPlanParams = getActionplanFilterParms(request);
		String mode = request.getParameter("mode");
		if(actionPlanParams.getMode()=="completion"){
			String flid=request.getParameter("flid");
		//	CommonMessage.debugMsg("The Flid"+flid);
			String TeamChk=request.getParameter("Teamchk");
		//	CommonMessage.debugMsg("The TeamCheck"+TeamChk);
			actionPlanParams.setFlid(flid);
			actionPlanParams.setKeyid(TeamChk);
			
			
		}
	
		CommonMessage.debugMsg("ActionPlanRptServlet From Month----------->" +actionPlanParams.getDtFromMonth());
		CommonMessage.debugMsg("ActionPlanRptServlet To Month----------->" +actionPlanParams.getDtToMonth());
		
		List<String[]> dataL = actionPlanRptService.getAllEmployeActionPlans(actionPlanParams);
		//List<String[]> dataL = actionPlanRptService
			//		.getAllEmployeActionPlans(actionPlanParams,flid,TeamChk);

		JSONObject dataJson = UIUtils.convertToJqGridTableObject(dataL,
				request, 0, 1, actionPlanParams.getTotalRecordCnt());
		out.print(dataJson);
	}

	private ActionPlanParams getActionplanFilterParms(HttpServletRequest request)
			throws IllegalAccessException, InvocationTargetException {
	//	CommonFilter commonFilter  = new CommonFilter();
		ActionPlanParams actionPlanParams = new ActionPlanParams();
		String mode = request.getParameter("mode");
	//	CommonMessage.debugMsg("The Mode is::"+mode);
		if(actionPlanParams.getMode()=="completion"){
			String flid=request.getParameter("flid");
			//CommonMessage.debugMsg("The Flid"+flid);
			String TeamChk=request.getParameter("Teamchk");
			actionPlanParams.setFlid(flid);
			actionPlanParams.setKeyid(TeamChk);	
			
		}
		BeanUtils.populate(actionPlanParams, request.getParameterMap());
		FilterValues.populateGridParams(request, actionPlanParams);
		//FilterValues.getCommonFilters(request, commonFilter);

		actionPlanParams.setEmployeeId(UIUtils.getLoginUser(request)
				.getUsrm_ccno());
		//--------------------------
		
		if (!UIUtils.isValidKeyId(actionPlanParams.getFlid()))
			actionPlanParams.setFlid(CommonFunctions.getLoginFlid(request));
		if ("view".equals(mode)) {
			CommonMessage.debugMsg("actionPlanParams::keyid:"+actionPlanParams.getEmployeeId());
			actionPlanParams.setEmployeeId(null);
			actionPlanParams.setMode(mode);
		} else
			actionPlanParams.setMode("completion");
		//madhan for default 3 months
		if( actionPlanParams.getDtFromDate() == null && actionPlanParams.getDtToDate() == null && actionPlanParams.getDtFromMonth() == null && actionPlanParams.getDtToMonth() == null  ){
			actionPlanParams.setDtFromMonth(CommonFunctions.getFirstDateofMonth(-3).substring(3,11));
			actionPlanParams.setDtToMonth(CommonFunctions.getDate().substring(3,11));
	 	  }

		return actionPlanParams;
	}

	private void saveActionPlanCompletion(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			String mode = request.getParameter("mode");
			CommonMessage.debugMsg("Mode : "+mode);
			if ("view".equals(mode))
				return;
			String actionPlanData = request.getParameter("apcompdt");
			CommonMessage.debugMsg("actionPlanData : "+actionPlanData);
			String msg = "No Record to Save";
			if (UIUtils.isValidKeyId(actionPlanData)) {
				CommonMessage.debugMsg("Valid actionPlanData : "+actionPlanData);
				String completdBy = UIUtils.getLoginUser(request).getUsrm_ccno();
				JSONArray actionPlanDataJson = JSONArray.fromString(actionPlanData);
				GenTlActionplandtl genTlActionplandtl = new GenTlActionplandtl();
				List<GenTlActionplandtl> genTlActionplandtlList = (List<GenTlActionplandtl>) UIUtils.convertJSONArrToList(genTlActionplandtl,actionPlanDataJson);
				for (GenTlActionplandtl genTlActionplan : genTlActionplandtlList) {
					genTlActionplan.setApldCompletedby(completdBy);

				}

				actionPlanRptService.saveActionPlanCompletion(genTlActionplandtlList);
				msg = UIUtils.getPropertyValue(
						"com.akranta.tpm.resources.CommonMessages",
						"success-save");
			}

			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", msg);
			returnData.put("formClear", false);
			returnData.put("successData", successData);
			out.print(returnData.toString());

		} catch (Exception e) {

			String msg = UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.CommonMessages", "err-save");
			msg += " - " + e.getMessage();
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", msg);
			returnData.put("formClear", false);
			returnData.put("successData", successData);
			out.print(returnData.toString());

		}

	}

	/*
	 * private JSONObject getTableModelActionPlan(List<String[]> headers) {
	 * 
	 * JqGridTableModel jqGridTableModel = new JqGridTableModel(); String[]
	 * colHeader = headers.get(1);
	 * jqGridTableModel.getRowHeaders().add(colHeader);
	 * jqGridTableModel.setRowNumbers(true);
	 * jqGridTableModel.setTableHeight(100);
	 * jqGridTableModel.setTableWidth(500);
	 * jqGridTableModel.setTableButton(true);
	 * jqGridTableModel.setEnableFilter(true); String headerSql = "'SELECT ";
	 * String header = "'SELECT "; String[] colIndex = headers.get(0); for (int
	 * i = 0; i < colHeader.length; i++) { JqGridColModel jqGridColModel = new
	 * JqGridColModel(); jqGridColModel.setIndex(colHeader[i].replaceAll(" ",
	 * " ")); jqGridColModel.setName(colHeader[i].replaceAll(" ", " "));
	 * jqGridColModel.setWidth(145); if(i==0||i==1
	 * ||i==12||i==13||i==14||i==15){ jqGridColModel.setHidden(true); }
	 * jqGridColModel.setAlign("left"); jqGridColModel.setEditable(false);
	 * CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]); headerSql =
	 * headerSql + UIUtils.getTablemodelSql(jqGridColModel); header =header+
	 * "''"
	 * +jqGridColModel.getName()+"'' as "+jqGridColModel.getName().toUpperCase
	 * ()+","; } headerSql = headerSql.substring(0, headerSql.length()-1) +
	 * " FROM DUAL "; header =header+ " FROM DUAL ";
	 * CommonMessage.debugMsg("headerSql....."+headerSql);
	 * CommonMessage.debugMsg("header......."+header);
	 * 
	 * JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 * tableModel.set("tableHeight", "30%%"); tableModel.set("tableWidth",
	 * "100%%"); return tableModel; }
	 * 
	 * private JSONObject getTableModelActionPlanMainReport(List<String[]>
	 * headers) { JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 * String[] colHeader = headers.get(1);
	 * jqGridTableModel.getRowHeaders().add(colHeader);
	 * jqGridTableModel.setRowNumbers(true);
	 * jqGridTableModel.setTableHeight(100);
	 * jqGridTableModel.setTableWidth(500);
	 * jqGridTableModel.setTableButton(true);
	 * jqGridTableModel.setEnableFilter(true); String[] colIndex =
	 * headers.get(0); String headerSql = "'SELECT "; String header =
	 * "'SELECT "; for (int i = 0; i < colHeader.length; i++) { JqGridColModel
	 * jqGridColModel = new JqGridColModel();
	 * jqGridColModel.setIndex(colHeader[i].replaceAll(" ", " "));
	 * jqGridColModel.setName(colHeader[i].replaceAll(" ", " "));
	 * jqGridColModel.setWidth(145); if(i==0||i==5){
	 * jqGridColModel.setHidden(true); } if(i==1){ jqGridColModel.setWidth(600);
	 * } jqGridColModel.setAlign("left"); jqGridColModel.setEditable(false);
	 * CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
	 * jqGridTableModel.getColModel().add(jqGridColModel); headerSql = headerSql
	 * + UIUtils.getTablemodelSql(jqGridColModel); header =header+
	 * "''"+jqGridColModel
	 * .getName()+"'' as "+jqGridColModel.getName().toUpperCase()+","; }
	 * headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
	 * header =header+ " FROM DUAL ";
	 * CommonMessage.debugMsg("headerSql....."+headerSql);
	 * CommonMessage.debugMsg("header......."+header);
	 * 
	 * 
	 * JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 * tableModel.set("tableHeight", "70%%"); tableModel.set("tableWidth",
	 * "108%%"); return tableModel;
	 * 
	 * }
	 */

}
