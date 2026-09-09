package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlNominationmst;
import com.akranta.tpm.model.GenTlDmcfipworkflow;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlDmcfipcreationmst;
import com.akranta.tpm.model.KznTlProjectChecklistLink;
import com.akranta.tpm.model.KznTlProjectKaizenLink;
import com.akranta.tpm.model.KznTlProjectKpiLink;
import com.akranta.tpm.model.KznTlProjectResourceLink;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.KznTlProjectdmaicstatus;
import com.akranta.tpm.model.KznTlProjectmaicMileDtl;
import com.akranta.tpm.model.KznTlProjectmaicMileMst;

import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.NewNearMissService;
import com.akranta.tpm.service.ProjectService;
import com.akranta.tpm.service.impl.NewNearMissServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.ProjectServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class ProjectServlet
 */

public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectService projectService;
	NewNearMissService nearMissService;
	DashboardService dashboardService;
    public ProjectServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 String rolenames=null;
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		projectService = (ProjectServiceImpl)UIUtils.getServiceObject(request, "ProjectServiceImpl");
		nearMissService=(NewNearMissServiceImpl)UIUtils.getServiceObject(request,"NewNearMissServiceImpl");
		dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
		projectService.ProjectServiceImplJwt((String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		 AdmTlUsermst usersno=UIUtils.getLoginUser(request);
		 String loginemploye=usersno.getUsrm_ccno();
		 String loginflid=CommonFunctions.getLoginFlid(request);
		 String loginlevel=CommonFunctions.getLoginLevel(request);
 		  String loginElementid = (String) httpSession.getAttribute("loginElementid");
 		 List<String []> getUserLoginDtl= nearMissService.getElementId(loginflid,loginlevel, loginElementid,loginemploye);
 		rolenames=getUserLoginDtl.get(0)[3];
 		 CommonFilter commonFilter =   new CommonFilter(); //null;
		
		if (action.equals("projectsproto_input.prpo")||action.equals("projectsteam_input.prpo")||action.equals("projectsfinance_input.prpo")||action.equals("projectssbu_input.prpo")||action.equals("projectschampion_input.prpo")||action.equals("projectsdmt_input.prpo")) {
			String type = request.getParameter("type");
			String stage = request.getParameter("stage");
			String approval = request.getParameter("approval");
			String isClosure= request.getParameter("isClosure");
			String isCheckList= request.getParameter("checkList");
			String fiptype= request.getParameter("fiptype");

			//CommonMessage.debugMsg("type::::="+type);
			request.setAttribute("type",type);
			request.setAttribute("stage",stage);
			request.setAttribute("approval",approval);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("checkList",isCheckList);
			request.setAttribute("fiptype",fiptype);
			if ("DMC".equals(fiptype)) {
				UIUtils.forwardRequest(request, response, "/pages/KK/DmcProjectKaizenGridNew.jsp");
			}else {
				UIUtils.forwardRequest(request, response, "/pages/KK/ProjectKaizenGrid.jsp");
			}

//			UIUtils.forwardRequest(request, response, "/pages/KK/ProjectKaizenGrid.jsp");
		}
		else if(action.equals("fiprojectviewdelete_input.prpo")){
			CommonMessage.debugMsg("Inside the FiProject Input");
			String type = request.getParameter("type");
			CommonMessage.debugMsg("type"+type);
			String stage = request.getParameter("stage");
			CommonMessage.debugMsg("The Stage"+stage);
			String approval = request.getParameter("approval");
			String isClosure= request.getParameter("isClosure");
			String isCheckList= request.getParameter("checkList");
			request.setAttribute("type",type);
			request.setAttribute("stage",stage);
			request.setAttribute("approval",approval);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("checkList",isCheckList);
			UIUtils.forwardRequest(request, response, "/pages/FipViewdelete.jsp");		
		}
		
		else if (action.equals("fiprojectviewdelete_getCol.prpo")){
			CommonMessage.debugMsg("Inside the fiprojectviewdelete getCol");
			PrintWriter out = response.getWriter();
			String isClosure= request.getParameter("isClosure");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",true);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
	
					List<String[]> Resource = projectService.getProject(commonFilter);
					 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setTableButton(true);
						
						gridColModel.setHeaderNum(1);
						gridColModel.setFormatter("chkBoxFormatter");
						gridColModel.setFormattorFromCol("10");
						gridColModel.setFormattorToCol("15");
						String [] colHeader = Resource.get(2);			
						String [] colHeaderCond = Resource.get(1);
						
				
						List<String[]> headers = new ArrayList<String[]>();
			
						headers.add(colHeader);
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "78%%");
						jsonObject.put("tableWidth", "108%%");
						httpSession.setAttribute("projectcreation", jsonObject);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} 
		
		 else if (action.equals("fiprojectviewdelete_getData.prpo")) {
			CommonMessage.debugMsg("Fi Project view delete getdata");
			String isClosure= request.getParameter("isClosure");			
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			PrintWriter out = response.getWriter();			
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",false);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);

						List<String[]> Resource = projectService.getProject(commonFilter);
						JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,3, 0,commonFilter.getTotalRecordCnt());
						out.println(resource);
				} catch (Exception e) {
				}
		}
		else if(action.equals("fiproject_view.prpo")){
			String type = request.getParameter("type");
			String checkList = request.getParameter("checkList");
			String isClosure= request.getParameter("isClosure");
			String fiptype = request.getParameter("fiptype");
			request.setAttribute("type",type);
			request.setAttribute("mode","view");
			request.setAttribute("checkList",checkList);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("fiptype",fiptype);
			UIUtils.forwardRequest(request, response, "/pages/KK/ProjectKaizenView.jsp");
			
		}
		else if( action.equals("dmcfiproject_view.prpo")){
			String type = request.getParameter("type");
			String checkList = request.getParameter("checkList");
			String isClosure= request.getParameter("isClosure");
			String fiptype = request.getParameter("fiptype");
			request.setAttribute("type",type);
			request.setAttribute("mode","view");
			request.setAttribute("checkList",checkList);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("fiptype",fiptype);
			UIUtils.forwardRequest(request, response, "/pages/KK/dmcProjectKaizenGrid.jsp");
		}
		else if (action.equals("projectCreationList_input.prpo")) {
			String type = request.getParameter("type");			
			String checkList = request.getParameter("checkList");
			request.setAttribute("type",type);
			request.setAttribute("mode","creation");
			request.setAttribute("checkList",checkList);
			UIUtils.forwardRequest(request, response, "/pages/KK/ProjecCreationGrid.jsp");
        }
		else if (action.equals("dmcprojectCreationList_input.prpo")) {
			String type = request.getParameter("type");			
			String checkList = request.getParameter("checkList");
			String dmc = request.getParameter("dmc");
			request.setAttribute("type",type);
			request.setAttribute("mode","creation");
			request.setAttribute("checkList",checkList);
			UIUtils.forwardRequest(request, response, "/pages/KK/dmcProjectionCreationGrid.jsp");	
			}
		else if(action.equals("projectCreationList_getCol.prpo")){
			getColProjectCreationList(request, response, httpSession);
		}
		else if(action.equals("projectCreationList_getData.prpo")){
			getDataProjectCreationList(request, response, httpSession);
		}
		else if(action.equals("dmcprojectCreationList_getCol.prpo")){
			getColdmcProjectCreationList(request, response, httpSession);
		}
		else if(action.equals("dmcprojectCreationList_getData.prpo")){
			getDatadmcProjectCreationList(request, response, httpSession);
		}
		else if (action.equals("projectsprotoview_input.prpo")||action.equals("Product_input.prpo") || action.equals("Equipment_input.prpo")) {
			String isCheckList= request.getParameter("checkList");
			
			String type = request.getParameter("type");
			String Keyid = request.getParameter("keyid");
			String flid = request.getParameter("flid");
			String approvedby = request.getParameter("approvedby");
			String kkeyid= request.getParameter("kkeyid");
			String isClosure= request.getParameter("isClosure");
			String isDefineStage= request.getParameter("isDefineStage");
			String stage = request.getParameter("stage");
			String approval = request.getParameter("approval");
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			
			//CommonMessage.debugMsg("type::::="+flid);
			KznTlProjectcreationmst newKznTlProjectcreationmst = new KznTlProjectcreationmst();
			newKznTlProjectcreationmst.setKzpmCreatedby(user.getUsrm_ccno());
		/*	if(UIUtils.isValidKeyId(isDefineStage)){
			//	String defineWOStatus = projectService.getWorkFlowStaus(Keyid,"PRODE","FIPRODEF",true,"-");
			}
		*/	
			if(UIUtils.isValidKeyId(Keyid)){				
				newKznTlProjectcreationmst = projectService.getRecall(Keyid);	    		
				request.setAttribute("kkeyid",kkeyid);
			}
			String mode= request.getParameter("mode");
			
			httpSession.setAttribute("project", newKznTlProjectcreationmst);
			request.setAttribute("Project",newKznTlProjectcreationmst);
			if("insert".equals(mode)){
				request.setAttribute("mode","create");
			}
			else if("view".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if( ("MAIC".equals(stage)  || "maic".equals(stage) ) && ("N".equals(approval) || "n".equals(approval)) )
			{
				if( newKznTlProjectcreationmst.getKzpmCreatedby().equals(user.getUsrm_ccno()) )
				{
					request.setAttribute("mode","define");
				}
				else
					request.setAttribute("mode","view");
			}
			else if("modifycreate".equals(mode) && newKznTlProjectcreationmst.getKzpmCreatedby().equals(user.getUsrm_ccno())){
				
				request.setAttribute("mode","modifycreate");
			}
			else if("modifycreate".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if("modify".equals(mode) && newKznTlProjectcreationmst.getKzpmCreatedby().equals(user.getUsrm_ccno())){
				request.setAttribute("mode","modify");
			}
			else if("modify".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if(newKznTlProjectcreationmst.getKzpmCreatedby().equals(user.getUsrm_ccno())){
				if( ("MAIC".equals(stage)  || "maic".equals(stage) ) && ("Y".equals(approval) || "y".equals(approval)) )
					request.setAttribute("mode","view");
				else
					request.setAttribute("mode","define");
			}
			else{
				request.setAttribute("mode","approval");
			}
			//CommonMessage.debugMsg("type::::="+type);
			
			request.setAttribute("stage",stage);
			request.setAttribute("type",type);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("defineStage",isDefineStage);
			request.setAttribute("approval",approval);
			
			if( isCheckList == null)
				isCheckList = "N";
			request.setAttribute("checkList",isCheckList);
			
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			if (UIUtils.isValidKeyId(approvedby))	request.setAttribute("approved", approvedby);
			UIUtils.forwardRequest(request, response, "/pages/KK/ProjectKaizen.jsp");
		}
		else if (action.equals("dmcprojectsprotoview_input.prpo")||action.equals("dmcProduct_input.prpo") || action.equals("dmcEquipment_input.prpo")) {
			String isCheckList= request.getParameter("checkList");
			
			String DfiwkeyId = request.getParameter("dfiwkeyId");
			CommonMessage.debugMsg("DfiwkeyId"+DfiwkeyId);
			
			String type = request.getParameter("type");
			String Keyid = request.getParameter("keyid");
			CommonMessage.debugMsg("Keyid"+Keyid);
			String flid = request.getParameter("flid");
			String approvedby = request.getParameter("approvedby");
			String kkeyid= request.getParameter("kkeyid");
			String isClosure= request.getParameter("isClosure");
			String isDefineStage= request.getParameter("isDefineStage");
			String stage = request.getParameter("stage");
			String approval = request.getParameter("approval");
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			
			//CommonMessage.debugMsg("type::::="+flid);
			KznTlProjectcreationmst newKznTlProjectcreationmst = new KznTlProjectcreationmst();
			GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
			KznTlDmcfipcreationmst newKznTlDmcfipcreationmst = new KznTlDmcfipcreationmst();
			newKznTlProjectcreationmst.setKzpmCreatedby(user.getUsrm_ccno());
			newKznTlDmcfipcreationmst.setDmcmCreatedby(user.getUsrm_ccno());
			//newGenTlDmcfipworkflow.setDfiwKeyid(DfiwkeyId);
			//newGenTlDmcfipworkflow.setDfiwFipno(Keyid);
		/*	if(UIUtils.isValidKeyId(isDefineStage)){
			//	String defineWOStatus = projectService.getWorkFlowStaus(Keyid,"PRODE","FIPRODEF",true,"-");
			}
		*/	
			if(UIUtils.isValidKeyId(Keyid)){				
				newKznTlDmcfipcreationmst = projectService.getdmcRecall(Keyid);	    		
				request.setAttribute("kkeyid",kkeyid);
			}
			
			if(UIUtils.isValidKeyId(Keyid)){	
				newGenTlDmcfipworkflow = projectService.getdmcwrkflpRecall(Keyid);	
				CommonMessage.debugMsg("dfiwKeyid="+Keyid);
				request.setAttribute("hdnDelKeyid",DfiwkeyId);
				
			}
			/*else{
				newGenTlDmcfipworkflow = projectService.getwrkflpRecall(DfiwkeyId);	
				CommonMessage.debugMsg("Keyid="+DfiwkeyId);
				request.setAttribute("hdnDelKeyid",DfiwkeyId);
			}*/
			String mode= request.getParameter("mode");
						
			httpSession.setAttribute("dmcproject", newGenTlDmcfipworkflow);
			request.setAttribute("DmcProject",newGenTlDmcfipworkflow);
			
			httpSession.setAttribute("project", newKznTlDmcfipcreationmst);
			request.setAttribute("Project",newKznTlDmcfipcreationmst);
			
			if("insert".equals(mode)){
				request.setAttribute("mode","create");
			}
			else if("view".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if( ("MAIC".equals(stage)  || "maic".equals(stage) ) && ("N".equals(approval) || "n".equals(approval)) )
			{
				if( newKznTlDmcfipcreationmst.getDmcmCreatedby().equals(user.getUsrm_ccno()) )
				{
					request.setAttribute("mode","define");
				}
				else
					request.setAttribute("mode","view");
			}
			else if("modifycreate".equals(mode) && newKznTlDmcfipcreationmst.getDmcmCreatedby().equals(user.getUsrm_ccno())){
				
				request.setAttribute("mode","modifycreate");
			}
			else if("modifycreate".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if("modify".equals(mode) && newKznTlDmcfipcreationmst.getDmcmCreatedby().equals(user.getUsrm_ccno())){
				request.setAttribute("mode","modify");
			}
			else if("modify".equals(mode)){
				request.setAttribute("mode","view");
			}
			else if(newKznTlDmcfipcreationmst.getDmcmCreatedby().equals(user.getUsrm_ccno())){
				if( ("MAIC".equals(stage)  || "maic".equals(stage) ) && ("Y".equals(approval) || "y".equals(approval)) )
					request.setAttribute("mode","view");
				else
					request.setAttribute("mode","define");
			}
			else{
				request.setAttribute("mode","approval");
			}
			//CommonMessage.debugMsg("type::::="+type);
			
			String location1=UIUtils.getlocation(request);
			String location=location1.substring(0,3);
			CommonMessage.debugMsg("location in input="+location);
			request.setAttribute("location",location);
			request.setAttribute("stage",stage);
			request.setAttribute("type",type);
			request.setAttribute("isClosure",isClosure);
			request.setAttribute("isDefineStage",isDefineStage);
			request.setAttribute("approval",approval);
			
			if( isCheckList == null)
				isCheckList = "N";
			request.setAttribute("checkList",isCheckList);
			
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			if (UIUtils.isValidKeyId(approvedby))	request.setAttribute("approved", approvedby);
			UIUtils.forwardRequest(request, response, "/pages/KK/dmcProjectKaizen.jsp");
		}
		else if(action.equals("kaizenList_view.prpo")){		
			
			
			UIUtils.forwardRequest(request, response, "/pages/KK/KaizenList.jsp");
		}
		else if(action.equals("closurestage_input.prpo")){	
			String isCheckList= request.getParameter("checkList");
			
			String type = request.getParameter("type");
			//CommonMessage.debugMsg("type::::="+type);
			request.setAttribute("type",type);
			request.setAttribute("isClosure","Y");
			request.setAttribute("checkList",isCheckList);
			UIUtils.forwardRequest(request, response, "/pages/KK/ProjectKaizenGrid.jsp");
		}
		else if(action.equals("resource_view.prpo")){
			String keyid = request.getParameter("resourceKeyid");
			if(UIUtils.isValidKeyId(keyid)){
				KznTlProjectResourceLink newKznTlProjectResourceLink = new KznTlProjectResourceLink();
				newKznTlProjectResourceLink = projectService.getRecallResource(keyid);
				httpSession.setAttribute("resource", newKznTlProjectResourceLink);
				request.setAttribute("resource",newKznTlProjectResourceLink);
				
			}
			UIUtils.forwardRequest(request, response, "/pages/KK/ResourceMapping.jsp");
		}
		else if(action.equals("KaizenList_getCol.prpo")){
			PrintWriter out = response.getWriter();
			commonFilter = populateCommonFilter(request,"KaizenCommonFilter",true);
			//String flid = request.getParameter("flid");
			String masterkeyid = request.getParameter("master");
			commonFilter.setKey(masterkeyid);
			//commonFilter.setFlid(flid);
			List<String[]> kpiListIndicatorLst  = projectService.getListOfKaizen(commonFilter);
			JSONObject kpiListIndicator = getTableModelKaizenList(kpiListIndicatorLst);	
			//CommonMessage.debugMsg("getData ;;"+kpiListIndicatorLst.size());
			out.println(kpiListIndicator);
		}
		else if(action.equals("KaizenList_getData.prpo")){
			PrintWriter out = response.getWriter();
			commonFilter = populateCommonFilter(request,"KaizenCommonFilter",false);
			String flid = request.getParameter("flid");
			String masterkeyid = request.getParameter("master");
			String search = request.getParameter("_search");
			int i;
			if("true".equals(search)){
				//CommonMessage.debugMsg("search"+search);
				i=1;
			}else{
				i=2;
			}
			commonFilter.setKey(masterkeyid);
			commonFilter.setFlid(flid);
			List<String[]> kpiListIndicatorLst  = projectService.getListOfKaizen(commonFilter);
			JSONObject kpiListIndicator = UIUtils.convertToJqGridTableObject(kpiListIndicatorLst,request,1,0,commonFilter.getTotalRecordCnt());	
			//CommonMessage.debugMsg("getData ;;"+kpiListIndicatorLst.size());
			out.println(kpiListIndicator);
		}
		else if(action.equals("kpiIndicatorgrd_getCol.prpo")){
			getColKpiLink(request, response, httpSession);
			/*PrintWriter out = response.getWriter();
			//CommonMessage.debugMsg("getCol");
			String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "colModelKpiProjectIndicator");
			JSONObject jsonObject = JSONObject.fromString(colmodel );
			jsonObject.set("multiSelect",true);
			 out.println(jsonObject);*/
		}
		else if(action.equals("kpiIndicatorgrd_getData.prpo")){
			getDataKpiLink(request, response, httpSession);
			/*JSONObject kpiListIndicator = null;
			List<String []> kpiListIndicatorLst  = null;
			//CommonMessage.debugMsg("getData");
			commonFilter = populateCommonFilter(request,"KpiCommonFilter",false);
			PrintWriter out = response.getWriter();
			String masterkeyid = request.getParameter("master");
			commonFilter.setKey(masterkeyid);
			kpiListIndicatorLst  = projectService.getListOfIndicators(commonFilter);
			kpiListIndicator = UIUtils.convertToJqGridTableObject(kpiListIndicatorLst,request,0,0);	
			//CommonMessage.debugMsg("getData ;;"+kpiListIndicatorLst.size());
			out.println(kpiListIndicator);*/
		}
		else if(action.equals("dmckpiIndicatorgrd_getCol.prpo")){
			getColKpiLink(request, response, httpSession);
			/*PrintWriter out = response.getWriter();
			//CommonMessage.debugMsg("getCol");
			String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "colModelKpiProjectIndicator");
			JSONObject jsonObject = JSONObject.fromString(colmodel );
			jsonObject.set("multiSelect",true);
			 out.println(jsonObject);*/
		}
		else if(action.equals("dmckpiIndicatorgrd_getData.prpo")){
			getDataKpiLink(request, response, httpSession);
			/*JSONObject kpiListIndicator = null;
			List<String []> kpiListIndicatorLst  = null;
			//CommonMessage.debugMsg("getData");
			commonFilter = populateCommonFilter(request,"KpiCommonFilter",false);
			PrintWriter out = response.getWriter();
			String masterkeyid = request.getParameter("master");
			commonFilter.setKey(masterkeyid);
			kpiListIndicatorLst  = projectService.getListOfIndicators(commonFilter);
			kpiListIndicator = UIUtils.convertToJqGridTableObject(kpiListIndicatorLst,request,0,0);	
			//CommonMessage.debugMsg("getData ;;"+kpiListIndicatorLst.size());
			out.println(kpiListIndicator);*/
		}
		 else if(action.equals("kpiIndicatorList_view.prpo")){
			 	String pillar= request.getParameter("pillar");
			 	String KpiKeyid=request.getParameter("kpikeyid");

			 	String flid=request.getParameter("flid");
			 	//CommonMessage.debugMsg("pillar  "+pillar);
			 	String from= request.getParameter("from");
			 	request.setAttribute("pilarref", pillar);
			 	request.setAttribute("from",from);
			 	request.setAttribute("Kpikeyid",KpiKeyid);
			 	request.setAttribute("flid",flid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KK/KpiIndicatorList.jsp"); 
				rd.forward(request, response);
			}
		 else if(action.equals("dmckpiIndicatorList_view.prpo")){
			 	String pillar= request.getParameter("pillar");
			 	String KpiKeyid=request.getParameter("kpikeyid");
                CommonMessage.debugMsg("KpiKeyid="+KpiKeyid);
                
                String masterkeyid = request.getParameter("masterkeyid");
                CommonMessage.debugMsg("masterkeyid="+masterkeyid);
                
			 	String flid=request.getParameter("flid");
			 	//CommonMessage.debugMsg("pillar  "+pillar);
			 	String from= request.getParameter("from");
			 	request.setAttribute("pilarref", pillar);
			 	request.setAttribute("from",from);
			 	request.setAttribute("Kpikeyid",KpiKeyid);
			 	request.setAttribute("flid",flid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/KK/dmcKpiIndicatorList.jsp"); 
				rd.forward(request, response);
			}
		    else if(action.equals("kaizenBelt.prpo")){
					 try{
					    ComboFilter combofilter=UIUtils.fillComboFilter(request);
					    List<ComboBox>beltList=projectService.getJHKaizenBeltComboList(combofilter);
					    UIUtils.writeComboBox(response,beltList,combofilter);			    
					  }
					 catch(Exception e){
						 e.printStackTrace();
					 }
					 }
		
		else if(action.equals("projectsprotoview_save.prpo")){
			saveProjectcreation(request,response);
		}
		else if(action.equals("dmcprojectsprotoview_save.prpo")){
			savedmcProjectcreation(request,response);
		}
		else if(action.equals("projectsprotomaicstatus_save.prpo")){
			HttpSession httpSession1 = request.getSession(false);
	    	ServletOutputStream outt = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	ServletOutputStream out = response.getOutputStream();
	    	String stage =  request.getParameter("stage");
	    	CommonMessage.debugMsg(" stage "+stage);
	    	if( httpSession1 != null && user != null)
	    	{	
	    		KznTlProjectcreationmst kznTlProjectcreationmst = new KznTlProjectcreationmst();//(KznTlProjectcreationmst) httpSession.getAttribute("project");
	    	try{
	    		
	    		kznTlProjectcreationmst = (KznTlProjectcreationmst) httpSession1.getAttribute("project");
				String keyId = request.getParameter("keyId");
				kznTlProjectcreationmst.setKzpmKeyid(keyId);	
					
				kznTlProjectcreationmst = projectService.updateMAICStatus(kznTlProjectcreationmst,stage);
				
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
			
				
				successData.put("mode", kznTlProjectcreationmst.getMode());
				successData.put("msg", "Updated SuccessFully");
				successData.put("kzpmKeyid", kznTlProjectcreationmst.getKzpmKeyid());
				returnData.put("successData",successData);
				returnData.put("formClear",false);
				out.print(returnData.toString());
				
	    	}catch (ValidationExceptions e) {
				//CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
				out.print(errMessage.toString());
	    	}
	    	catch (BusinessApplicationExceptions e) {
	    		//CommonMessage.debugMsg("BusinessApplicationExceptions dfg" + e.toString());
	    		e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ProjectCreationValidation");
				//CommonMessage.debugMsg(" e " + errMessage );
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				outt.print(err.toString());
			}
	    	}	
		}
		else if(action.equals("UpdateMaicStages.prpo")){
			//saveProjectcreation(request,response);
			CommonMessage.debugMsg("inside DMC Update");
			
			  	ServletOutputStream out = response.getOutputStream();
			  	AdmTlUsermst user = UIUtils.getLoginUser(request);
			  	String saveMsg="";
			  	if( httpSession != null && user != null)
			  	{	
			  		try{
			  			 String Projectleader=request.getParameter("Projectleader");
			  	 		 String KKChampion=request.getParameter("KKChampion");
			  	 		 String FIPNO=request.getParameter("FIPNO");
			  	 		 
			  	 	
			  	 		GenTlWorkflowInfo genTlWorkflowInfo=new GenTlWorkflowInfo();
			  	 		GenTlWorkflowInfo existGenTlWorkflowInfo=(GenTlWorkflowInfo)httpSession.getAttribute("GenTlWorkflowInfo");
			  	 		genTlWorkflowInfo =(GenTlWorkflowInfo)UIUtils.setBeanProperties((Object)genTlWorkflowInfo,request);
			  		
			  			if(FIPNO.length()>0){
			  				CommonMessage.debugMsg("Inside the Update");
			  				existGenTlWorkflowInfo=projectService.autoapproveDMAIC(genTlWorkflowInfo,existGenTlWorkflowInfo,Projectleader,KKChampion,FIPNO);
			  				saveMsg="Data Updated Successfully";
			  			}
			  			
			  			JSONObject persistentData = new JSONObject(); 
						JSONObject forwardData = new JSONObject();
						JSONObject successData = new JSONObject();
						successData.put("msg",saveMsg);
						successData.put("keyId", FIPNO);
						JSONObject returnData = new JSONObject();
						returnData.put("formClear",false);
						returnData.put("forwardData",forwardData);
						returnData.put("persistentData", persistentData);
						returnData.put("successData", successData);		
						out.print(returnData.toString());
						
						
			  		}
						catch(ValidationExceptions e)
						{
							JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ProjectValidations");
							out.print(errMessage.toString());					
						}
					    catch(BusinessApplicationExceptions e){
					    	JSONObject err=new JSONObject();
					    
					    	out.print(err.toString());
					    }
						catch(Exception e){
							e.printStackTrace();
							JSONObject err = new JSONObject();
						
							err.put("tpmException", "Data Not Saved");
							
							out.print(err.toString());
						}			
				    }
		}
		/*else if(action.equals("dmcworkflow_save.prpo")){
			savedmcworkflowcreation(request,response);
		}*/
		else if(action.equals("resource_save.prpo")){
			saveProjectResource(request,response);
		}else if(action.equals("resource_remove.prpo")){
			removeProjectResource(request,response);
		}
		else if(action.equals("resource_delete.prpo")){
			deleteProjectResource(request,response);
		}
		else if(action.equals("kpiLinkProject_save.prpo")){
			saveKpiLinkcreation(request,response);
		}
		else if(action.equals("kaizenLinkProject_save.prpo")){
			saveKaizenLinkcreation(request,response);
		}
		else if(action.equals("projectsprotoview_delete.prpo")){
			deleteProjectcreation(request,response);
		}
		else if(action.equals("dmcprojectsprotoview_delete.prpo")){
			deletedmcProjectcreation(request,response);
		}
		else if(action.equals("projectsKpiproto_input.prpo")){
			UIUtils.forwardRequest(request, response, "/pages/ProjectKpi.jsp");
		}
		/**/
		  else if( action.equals("ChangeProLead_input.prpo")){
				String fiptype = request.getParameter("fiptype");
				//String checkList = request.getParameter("checkList");
				String isClosure= request.getParameter("isClosure");
				request.setAttribute("fiptype",fiptype);
				request.setAttribute("type","team");
				request.setAttribute("mode","view");
				request.setAttribute("checkList","N");
				request.setAttribute("isClosure",isClosure);
				UIUtils.forwardRequest(request, response, "/pages/KK/ChangeProjectleader.jsp");

		}

			
			else if (action.equals("ChangeProLead_getCol.prpo")) {
				PrintWriter out = response.getWriter();
				String isClosure= request.getParameter("isClosure");
				String stage= request.getParameter("stage");
				String approval= request.getParameter("approval");
				String flid= request.getParameter("flid");
				String type= request.getParameter("type");
					try {
						//CommonFilter commonFilter = new CommonFilter();
						commonFilter = populateCommonFilter(request,"Projectleader",true);	
						commonFilter.setKey(isClosure);
						commonFilter.setType(stage);
						commonFilter.setAtype(approval);
						commonFilter.setMainkeyid(type);
						CommonMessage.debugMsg("getcolummnnnjihid ");
					commonFilter.setFlid(flid);
						List<String[]> Resource1 = projectService.getChangeprolead(commonFilter);
						//	JSONObject jsonObject = getTableModel(Resource);
							//out.println(jsonObject);
						 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
							GridColModel gridColModel = new GridColModel();
							
							jqGridTableModel.setRowNumbers(true);
							jqGridTableModel.setEnableFilter(true);
							jqGridTableModel.setTableButton(true);
							
							gridColModel.setHeaderNum(1);
							gridColModel.setFormatter("chkBoxFormatter");
							gridColModel.setFormattorFromCol("2");
							gridColModel.setFormattorFromCol("2");
							gridColModel.setFormattorFromCol("10");
							gridColModel.setFormattorToCol("15");
							String [] colHeader = Resource1.get(2);			
							String [] colHeaderCond = Resource1.get(1);
							
							////CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
							List<String[]> headers = new ArrayList<String[]>();
							//headers.add(colHeaderCond);
							headers.add(colHeader);
							
							JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
							jsonObject.put("tableHeight", "78%%");
							jsonObject.put("tableWidth", "108%%");
							jsonObject.set("multiSelect", true);
							
							httpSession.setAttribute("Changeprolead", jsonObject);
							out.println(jsonObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			

			else if (action.equals("ChangeProLead_getData.prpo")) {
				//CommonMessage.debugMsg("get data method");
				String isClosure= request.getParameter("isClosure");			
				String flid= request.getParameter("flid");
				String stage= request.getParameter("stage");
				String approval= request.getParameter("approval");
				PrintWriter out = response.getWriter();			

					try {
						commonFilter = populateCommonFilter(request,"Projectleader",false);	
						commonFilter.setKey(isClosure);
						commonFilter.setType(stage);
						commonFilter.setAtype(approval);
						commonFilter.setFlid(flid);
							List<String[]> Resource = projectService.getChangeprolead(commonFilter);
							JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,3, 0,commonFilter.getTotalRecordCnt());
							out.println(resource);
					} catch (Exception e) {
					//	e.printStackTrace();
					}
			}
		
			else if (action.equals("ChangeProLead_getExcel.prpo")) {
							
				
				String isClosure= request.getParameter("isClosure");			
				String flid= request.getParameter("flid");
				String stage= request.getParameter("stage");
				String approval= request.getParameter("approval");
				commonFilter = populateCommonFilter(request,"Projectleader",false);
				commonFilter.setKey(isClosure);
				commonFilter.setType(stage);
				commonFilter.setAtype(approval);
				commonFilter.setFlid(flid);
				 CommonMessage.debugMsg("excelling");
				JSONObject colmodel = UIUtils.getXlColModel(request, response) ;//(JSONObject) httpSession.getAttribute("projectcreation");
				colmodel.put("title","Change Project leader");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = projectService.getChangeproleadExcel(colmodel,format,commonFilter);
				//commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "Projectleader", format);
				CommonMessage.debugMsg("export to excel");
			}


	
       else if (action.equals("ChangeProLeadBankPopup_select.prpo")) {	
			
      	
			   String Projectid=request.getParameter("Projectid");
			   CommonMessage.debugMsg("Detailkeyid::::"+Projectid);
			   String oldemployeenameid=request.getParameter("oldemployeenameid");
			   CommonMessage.debugMsg("oldemployee::::"+oldemployeenameid);
			  // request.setAttribute("chkid",chkid);
			   request.setAttribute("Keyid",Projectid);
			   request.setAttribute("oldemployeeid",oldemployeenameid);
			  
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/ChangeprePopup.jsp");
		
		     rd.forward(request, response);
			 
					   }	   	   
	



  else if(action.equals("ChangeprePopup_save.prpo"))
	  	   {
			   SaveChangelead(request,response);
	  	   }



  else if( action.equals("changeprojectchamp_select.prpo"))
  {
		try
		{	
			String Keyid = request.getParameter("Keyid");
			//String formType = request.getParameter("formType");
			//String woId = request.getParameter("woId");		
			//CommonMessage.debugMsg("woid:"+woId+" formName :"+formName+" formType :"+formType);
			CommonMessage.debugMsg("Keyid:"+Keyid);
			PrintWriter out = response.getWriter();				
			List<String []> projectchamp  = projectService.getprojectchamp(Keyid);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Data", projectchamp);
			out.println(jsonObject);
			//CommonMessage.debugMsg(projectchamp.size());
			//writeprojectchamp(response,projectchamp);
		}catch(Exception e)
		{ CommonMessage.debugMsg(e.getMessage()); }			
	}	
		/**/
  else if( action.equals("ChangeProLeader_input.prpo")){
		//String type = request.getParameter("type");
		//String checkList = request.getParameter("checkList");
		String isClosure= request.getParameter("isClosure");
		request.setAttribute("type","team");
		request.setAttribute("mode","view");
		request.setAttribute("checkList","N");
		request.setAttribute("isClosure",isClosure);
		UIUtils.forwardRequest(request, response, "/pages/KK/ChangeproLeader.jsp");

}

	
	else if (action.equals("ChangeProLeader_getCol.prpo")) {
		PrintWriter out = response.getWriter();
		String isClosure= request.getParameter("isClosure");
		String stage= request.getParameter("stage");
		String approval= request.getParameter("approval");
		String flid= request.getParameter("flid");
			try {
				//CommonFilter commonFilter = new CommonFilter();
				commonFilter = populateCommonFilter(request,"Projectleader",true);	
				commonFilter.setKey(isClosure);
				commonFilter.setType(stage);
				commonFilter.setAtype(approval);
				CommonMessage.debugMsg("getcolummnnnjihid ");
			commonFilter.setFlid(flid);
				List<String[]> Resource1 = projectService.getChangeproleader(commonFilter);
				//	JSONObject jsonObject = getTableModel(Resource);
					//out.println(jsonObject);
				 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					gridColModel.setHeaderNum(1);
					gridColModel.setFormatter("chkBoxFormatter");
					gridColModel.setFormattorFromCol("2");
					gridColModel.setFormattorFromCol("2");
					gridColModel.setFormattorFromCol("10");
					gridColModel.setFormattorToCol("15");
					String [] colHeader = Resource1.get(2);			
					String [] colHeaderCond = Resource1.get(1);
					
					////CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "78%%");
					jsonObject.put("tableWidth", "108%%");
					jsonObject.set("multiSelect", true);
					
					httpSession.setAttribute("Changeprolead", jsonObject);
					out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	

	else if (action.equals("ChangeProLeader_getData.prpo")) {
		//CommonMessage.debugMsg("get data method");
		String isClosure= request.getParameter("isClosure");			
		String flid= request.getParameter("flid");
		String stage= request.getParameter("stage");
		String approval= request.getParameter("approval");
		PrintWriter out = response.getWriter();			

			try {
				commonFilter = populateCommonFilter(request,"Projectleader",false);	
				commonFilter.setKey(isClosure);
				commonFilter.setType(stage);
				commonFilter.setAtype(approval);
				commonFilter.setFlid(flid);
					List<String[]> Resource = projectService.getChangeproleader(commonFilter);
					JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,3, 0,commonFilter.getTotalRecordCnt());
					out.println(resource);
			} catch (Exception e) {
			//	e.printStackTrace();
			}
	}

	else if (action.equals("ChangeProLeader_getExcel.prpo")) {
					
		
		String isClosure= request.getParameter("isClosure");			
		String flid= request.getParameter("flid");
		String stage= request.getParameter("stage");
		String approval= request.getParameter("approval");
		commonFilter = populateCommonFilter(request,"Projectleader",false);
		commonFilter.setKey(isClosure);
		commonFilter.setType(stage);
		commonFilter.setAtype(approval);
		commonFilter.setFlid(flid);
		 CommonMessage.debugMsg("excelling");
		JSONObject colmodel = UIUtils.getXlColModel(request, response) ;//(JSONObject) httpSession.getAttribute("projectcreation");
		colmodel.put("title","Change Project leader");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = projectService.getChangeproleaderExcel(colmodel,format,commonFilter);
		//commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "Projectleader", format);
		CommonMessage.debugMsg("export to excel");
	}



else if (action.equals("ChangeProLeaderBankPopup_select.prpo")) {	
	
	
	   String Projectid=request.getParameter("Projectid");
	   CommonMessage.debugMsg("Detailkeyid::::"+Projectid);
	   String oldemployeenameid=request.getParameter("oldemployeenameid");
	   CommonMessage.debugMsg("oldemployee::::"+oldemployeenameid);
	  // request.setAttribute("chkid",chkid);
	   request.setAttribute("Keyid",Projectid);
	   request.setAttribute("oldemployeeid",oldemployeenameid);
	  
	  RequestDispatcher rd = request.getRequestDispatcher("/pages/ChangepreLeadPopup.jsp");

 rd.forward(request, response);
	 
			   }	   	   




else if(action.equals("ChangepreLeadPopup_save.prpo"))
 {
	   SaveChangeleader(request,response);
 }



else if( action.equals("changeprojectLeader_select.prpo"))
{
try
{	
	String Keyid = request.getParameter("Keyid");
	//String formType = request.getParameter("formType");
	//String woId = request.getParameter("woId");		
	//CommonMessage.debugMsg("woid:"+woId+" formName :"+formName+" formType :"+formType);
	CommonMessage.debugMsg("Keyid:"+Keyid);
	PrintWriter out = response.getWriter();				
	List<String []> projectchamp  = projectService.getprojectleader(Keyid);
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("Data", projectchamp);
	out.println(jsonObject);
	//CommonMessage.debugMsg(projectchamp.size());
	//writeprojectchamp(response,projectchamp);
}catch(Exception e)
{ CommonMessage.debugMsg(e.getMessage()); }			
}		

		
		
		/**/
		else if(action.equals("milestone_save.prpo")){
			//CommonMessage.debugMsg("save");
			saveMilestone(request,response);
		}
		else if(action.equals("milestone_delete.prpo")){
			deleteMilestone(request,response);
		}
		else if(action.equals("milestoneDetail_delete.prpo")){
			deleteMilestoneDetail(request,response);
		}
		else if(action.equals("getMAICStage.prpo")){
			getMileStoneStages(request,response);
		}
		else if(action.equals("getWorkFlowStatus.prpo")){
			getWorkFlowStatus(request,response);
		}
		else if(action.equals("getdmcWorkFlowStatus.prpo")){
			getdmcWorkFlowStatus(request,response);
		}
		else if(action.startsWith("prjCheckList")){
			checkList(request,response);
		}
		else if(action.equals("resource_functionalLoc.prpo")){
			loadPrjResourceFunctionalLocation(request,response);
		}
		else if(action.equals("comboleader.prpo"))
		   {
			   PrintWriter out = response.getWriter();
			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Resource", "Resources"));
			   
		   }
		 else if( action.equals("milestone_view.prpo") ){
			 	String keyid = request.getParameter("Keyid");
			 	String mode = request.getParameter("mode");
			 	if(UIUtils.isValidKeyId(keyid)){
			 		KznTlProjectmaicMileMst newKznTlProjectmaicMileMst = new KznTlProjectmaicMileMst();
			 		newKznTlProjectmaicMileMst = projectService.getRecallMile(keyid);
					httpSession.setAttribute("mile", newKznTlProjectmaicMileMst);
					request.setAttribute("mile",newKznTlProjectmaicMileMst);					
			 	}
			 	request.setAttribute("mode",mode);
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/KK/MstMilestone.jsp"); 
			   rd.forward(request, response); 
		   } else if( action.equals("history_view.prpo") ){
			   String dtlId = request.getParameter("dtlId");	
			   request.setAttribute("dtlId", dtlId);
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/KK/MilestoneHistory.jsp"); 
			   rd.forward(request, response); 
		   }
		   else if(action.equals("Fipwavebenefit_input.prpo")){
				CommonMessage.debugMsg("FIP Wave Benefit");
				UIUtils.forwardRequest(request, response,"/pages/KK/FIPWaveBenefitCount.jsp");
				
			}
			else if(action.equals("Fipwavebenefit_getCol.prpo")){
				    httpSession = request.getSession(false);
		  			String firstClick =request.getParameter("firstClick");
		  			commonFilter = new CommonFilter(); 		
		  		    CommonMessage.debugMsg("getcol:" +firstClick);
		  			 
		  			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
		  			 {
		  				 commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
		  			 }	
		  			if(commonFilter==null)
		  			 commonFilter = new CommonFilter(); 
		  			 
		  			 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
		  			 commonFilter= 	FilterValues.getSafty(request, commonFilter);
		  			 
		  			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
		  				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
		  				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
		  				  commonFilter.setMonwise("Y");
		  		 	  }
		  			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
		  		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
		  					  commonFilter.setToDate(CommonFunctions.getDate());
		  			 }
		  			 httpSession.removeAttribute("DmaicCommonFilter");
		  			 httpSession.setAttribute("DmaicCommonFilter",commonFilter);
		  			 response.setContentType("text/html");
		  			 PrintWriter out = response.getWriter();
				     List<String[]> fipwavebenefitcount=projectService.getFIpWaveBenefitCount(commonFilter);
		  			 JSONObject colModel = getTableModel_FIP(fipwavebenefitcount,commonFilter);
		  			 colModel.set("tableHeight", "71%%");
		  			 colModel.set("tableWidth", "107%%");
		  			 httpSession.removeAttribute("DmaiccolModel");
		  			 httpSession.setAttribute("DmaiccolModel", colModel);
		  			 CommonMessage.debugMsg(colModel);
		  			 out.println(colModel);
		  			 out.close();  
					
					
			}
			else if(action.equals("Fipwavebenefit_getData.prpo")){
				 
			  PrintWriter out = response.getWriter();
			  httpSession=request.getSession(false);
			try
			{
				UIUtils.displayRequestParamsValue(request);
				 
			    commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
				 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				 commonFilter= 	FilterValues.getSafty(request, commonFilter);
				 JSONObject jsonObject = new JSONObject();
				 
				List<String[]> fipwavebenefitcount=projectService.getFIpWaveBenefitCount(commonFilter);
	     		 CommonMessage.debugMsg("customerComplaintTrendList.size("+fipwavebenefitcount.size()+")");
	     		 CommonMessage.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
	     		 //	if (KaizenBenefitTrdData.size() >1) 
	     		 	jsonObject = UIUtils.convertToJqGridTableObject(fipwavebenefitcount,request,3,0,fipwavebenefitcount.size());
	     		 	 CommonMessage.debugMsg(jsonObject);
	     		 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			
		   }
			catch(Exception e)
			{
				
			}
			}
		
         else if(action.equals("Fipwavebenefit_getExcel.prpo")){
			 httpSession=request.getSession(false);
			 commonFilter = populateCommonFilter(request,"DmaicCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("DmaiccolModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "FIP Benefit");			
			String format = ExcelUtils.getFormat(request);
			Workbook wb = projectService.getFIpWaveCountExportToExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "FIPBEnefit", format);
		}   
		
			else if(action.equals("FIPBenefitCountchart.prpo")){
				
				processBarBenefitChart(request,response);
			}
		   else if(action.equals("FipwavenoStages_input.prpo")){
				UIUtils.forwardRequest(request, response,"/pages/KK/FIPWaveCount.jsp");
			}
		   else if(action.equals("FipwavenoStages_getCol.prpo")){
			   
			     httpSession = request.getSession(false);
	  			String firstClick =request.getParameter("firstClick");
	  			commonFilter = new CommonFilter(); 		
	  		    CommonMessage.debugMsg("getcol:" +firstClick);
	  			 
	  			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
	  			 {
	  				 commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
	  			 }	
	  			if(commonFilter==null)
	  			 commonFilter = new CommonFilter(); 
	  			 
	  			 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
	  			 commonFilter= 	FilterValues.getSafty(request, commonFilter);
	  			 
	  			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
	  				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
	  				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
	  				  commonFilter.setMonwise("Y");
	  		 	  }
	  			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
	  		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
	  					  commonFilter.setToDate(CommonFunctions.getDate());
	  			 }
	  			 httpSession.removeAttribute("DmaicCommonFilter");
	  			 httpSession.setAttribute("DmaicCommonFilter",commonFilter);
	  			 response.setContentType("text/html");
	  			 PrintWriter out = response.getWriter();
		         List<String[]> fipcountlist=projectService.getFIpCount(commonFilter);
	  			 JSONObject colModel = getTableModel_FIP(fipcountlist,commonFilter);
	  			 colModel.set("tableHeight", "71%%");
	  			 colModel.set("tableWidth", "107%%");
	  			 httpSession.removeAttribute("DmaiccolModel");
	  			 httpSession.setAttribute("DmaiccolModel", colModel);
	  			 CommonMessage.debugMsg(colModel);
	  			 out.println(colModel);
	  			 out.close();  
			   
			}
			else if(action.equals("FipwavenoStages_getData.prpo")){
				
				  PrintWriter out = response.getWriter();
				  httpSession=request.getSession(false);
				try
				{
					UIUtils.displayRequestParamsValue(request);
					 
				    commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
					 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
					 commonFilter= 	FilterValues.getSafty(request, commonFilter);
					 JSONObject jsonObject = new JSONObject();
			         List<String[]> fipcountlist=projectService.getFIpCount(commonFilter);
		     		 CommonMessage.debugMsg("customerComplaintTrendList.size("+fipcountlist.size()+")");
		     		 CommonMessage.debugMsg("getTotalRecordCnt"+commonFilter.getTotalRecordCnt());
		     		 jsonObject = UIUtils.convertToJqGridTableObject(fipcountlist,request,3,0,fipcountlist.size());
		     		 CommonMessage.debugMsg(jsonObject);
		     		 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			
			   }
				catch(Exception e)
				{
					
				}
				
			}
			else if(action.equals("FipwavenoStages_getExcel.prpo")){
				CommonMessage.debugMsg("Inside the Excel");
				
				httpSession = request.getSession(false);
				CommonFilter commonFilter1 = populateCommonFilter(request,
						"DmaicCommonFilter", false);
				JSONObject colmodel = UIUtils.getXlColModel(request, response);
				colmodel.put("title", "FIP Wave Count Report");
				String format = ExcelUtils.getFormat(request);

				Workbook wb = projectService.getFIpWaveCountExportToExcel(colmodel,
						format, commonFilter1);
				ExcelUtils.writeToResponse(response, wb, "FIP Wave Count Report", format);
				
			}
			else if(action.equals("FIPWaveCountchart.prpo")){
				processBarFIPWaveNoChart(request,response);
			}
		   else if( action.equals("history_getCol.prpo") ){	
			   	PrintWriter out = response.getWriter();
			   	String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.MspIndicators", "historyProjectColModel");
				httpSession.removeAttribute("milestoneColModel");
				httpSession.setAttribute("milestoneColModel", colModel);	
				out.println(colModel);
		   }
		   else if( action.equals("history_getData.prpo") ){
			   PrintWriter out = response.getWriter();
			   String dtlId = request.getParameter("dtlId");	
			   
			   net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
			   List<String[]> historyList  =  projectService.getAllHistory(dtlId);
			   if(historyList != null && historyList.size()>0)
	        	   jsonObject = UIUtils.convertToJqGridTableObject(historyList,request,0,0,historyList.size());
	           out.println(jsonObject);	        	 
		   }
		 else if( action.equals("milestone_getCol.prpo") ){	
			   	PrintWriter out = response.getWriter();
			    commonFilter = populateCommonFilter(request,"MilestoneCommonFilter",true);	
			    String keyid = request.getParameter("Keyid");
			    String stage = request.getParameter("stage");
				commonFilter.setKey(keyid);
				commonFilter.setStatus(stage);
				List<String[]> milestoneList  =  projectService.getAllMilestones(commonFilter);	
				JSONObject jsonObject = getTableModelMilestone(milestoneList);
				//String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.MspIndicators", colModelName);
			//	httpSession.removeAttribute("milestoneColModel");
		//		httpSession.setAttribute("milestoneColModel", jsonObject);	
				out.println(jsonObject);
		   }
		   else if( action.equals("milestone_getData.prpo") ){	
			 
			   PrintWriter out = response.getWriter();	
			   commonFilter = populateCommonFilter(request,"MilestoneCommonFilter",true);
			   String keyid = request.getParameter("Keyid");
			    String stage = request.getParameter("stage");
				commonFilter.setKey(keyid);
				commonFilter.setStatus(stage);
			   net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
			   List<String[]> milestoneList  =  projectService.getAllMilestones(commonFilter);
			   jsonObject = UIUtils.convertToJqGridTableObject(milestoneList,request,1,0);
     	       out.println(jsonObject);				 		
     	       httpSession.removeAttribute("MilestoneCommonFilter");
     	       httpSession.setAttribute("MilestoneCommonFilter", commonFilter);
				
		   }
		else if (action.equals("Product_getCol.prpo") || action.equals("Equipment_getCol.prpo")) {
			PrintWriter out = response.getWriter();
				try {
					List<String[]> Resource = projectService.getProject(commonFilter);
						JSONObject jsonObject = getTableModel(Resource);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}		
		}
		else if (action.equals("projectsprotomain_getCol.prpo")) {
			PrintWriter out = response.getWriter();
			String isClosure= request.getParameter("isClosure");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
		//	String flid= request.getParameter("flid");
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",true);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);//loginemploye
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
			//		commonFilter.setFlid(flid);
					List<String[]> Resource = projectService.getProject(commonFilter);
					/*	JSONObject jsonObject = getTableModel(Resource);
						out.println(jsonObject);*/
					 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setTableButton(true);
						
						gridColModel.setHeaderNum(1);
						if(fiptype.equals("DMC"))
						{
						gridColModel.setFormatter("chkBoxFormatter");
						gridColModel.setFormattorFromCol("11");
						gridColModel.setFormattorToCol("16");
						}
						else
						{
//							gridColModel.setFormatter("chkBoxFormatter");
//							gridColModel.setFormattorFromCol("10");
//							gridColModel.setFormattorToCol("15");	
						}
						String [] colHeader = Resource.get(1);			
						String [] colHeaderCond = Resource.get(0);
						
						////CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
						List<String[]> headers = new ArrayList<String[]>();
						//headers.add(colHeaderCond);
						headers.add(colHeader);
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "78%%");
						jsonObject.put("tableWidth", "108%%");
						httpSession.setAttribute("projectcreation", jsonObject);
						//httpSession.setAttribute("ProjectCreation", commonFilter);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} else if (action.equals("projectsprotomain_getData.prpo")) {
			//CommonMessage.debugMsg("get data method");
			String isClosure= request.getParameter("isClosure");			
			//String flid= request.getParameter("flid");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
			PrintWriter out = response.getWriter();			
		/*	if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
				//CommonMessage.debugMsg("flid "+flid);
			}
		*/	
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",false);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
				//	commonFilter.setFlid(flid);
						List<String[]> Resource = projectService.getProject(commonFilter);
						JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,2, 0,commonFilter.getTotalRecordCnt());
						out.println(resource);
				} catch (Exception e) {
				//	e.printStackTrace();
				}
		}
		
		else if (action.equals("projectsprotomainView_getCol.prpo")) {
			PrintWriter out = response.getWriter();
			String isClosure= request.getParameter("isClosure");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
		//	String flid= request.getParameter("flid");
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",true);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);//loginemploye
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
			//		commonFilter.setFlid(flid);
					List<String[]> Resource = projectService.getProjectnewview(commonFilter);
					/*	JSONObject jsonObject = getTableModel(Resource);
						out.println(jsonObject);*/
					 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setTableButton(true);
						
						gridColModel.setHeaderNum(1);
						if(fiptype.equals("DMC"))
						{
						gridColModel.setFormatter("chkBoxFormatter");
						gridColModel.setFormattorFromCol("12");
						gridColModel.setFormattorToCol("17");
						}
						else
						{
//							gridColModel.setFormatter("chkBoxFormatter");
//							gridColModel.setFormattorFromCol("11");
//							gridColModel.setFormattorToCol("16");	
						}
						String [] colHeader = Resource.get(1);			
						String [] colHeaderCond = Resource.get(0);
						
						////CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
						List<String[]> headers = new ArrayList<String[]>();
						//headers.add(colHeaderCond);
						headers.add(colHeader);
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "78%%");
						jsonObject.put("tableWidth", "108%%");
						httpSession.setAttribute("projectcreation", jsonObject);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} 
		else if (action.equals("projectsprotomainView_getData.prpo")) {
			//CommonMessage.debugMsg("get data method");
			String isClosure= request.getParameter("isClosure");			
			//String flid= request.getParameter("flid");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
			PrintWriter out = response.getWriter();			
		/*	if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
				//CommonMessage.debugMsg("flid "+flid);
			}
		*/	
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",false);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
				//	commonFilter.setFlid(flid);
						List<String[]> Resource = projectService.getProjectnewview(commonFilter);
						JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,2, 0,commonFilter.getTotalRecordCnt());
						out.println(resource);
				} catch (Exception e) {
				//	e.printStackTrace();
				}
		}
		else if (action.equals("dmcprojectsprotomain_getCol.prpo")) {
			PrintWriter out = response.getWriter();
			String isClosure= request.getParameter("isClosure");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
		//	String flid= request.getParameter("flid");
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",true);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);//loginemploye
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
			//		commonFilter.setFlid(flid);
					List<String[]> Resource = projectService.getProjectview(commonFilter);
					/*	JSONObject jsonObject = getTableModel(Resource);
						out.println(jsonObject);*/
					 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setTableButton(true);
						
						gridColModel.setHeaderNum(1);
						if(fiptype.equals("DMC"))
						{
						gridColModel.setFormatter("chkBoxFormatter");
						gridColModel.setFormattorFromCol("13");
						gridColModel.setFormattorToCol("18");
						}
						else
						{
							gridColModel.setFormatter("chkBoxFormatter");
							gridColModel.setFormattorFromCol("10");
							gridColModel.setFormattorToCol("15");	
						}
						String [] colHeader = Resource.get(2);			
						String [] colHeaderCond = Resource.get(1);
						
						////CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
						List<String[]> headers = new ArrayList<String[]>();
						//headers.add(colHeaderCond);
						headers.add(colHeader);
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "78%%");
						jsonObject.put("tableWidth", "108%%");
						httpSession.setAttribute("projectcreation", jsonObject);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} 
		
		else if (action.equals("dmcprojectsprotomain_getData.prpo")) {
			//CommonMessage.debugMsg("get data method");
			String isClosure= request.getParameter("isClosure");			
			//String flid= request.getParameter("flid");
			String stage= request.getParameter("stage");
			String approval= request.getParameter("approval");
			String fiptype=request.getParameter("fiptype");
			PrintWriter out = response.getWriter();			
		/*	if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
				//CommonMessage.debugMsg("flid "+flid);
			}
		*/	
				try {
					commonFilter = populateCommonFilter(request,"ProjectCreation",false);	
					commonFilter.setKey(isClosure);
					commonFilter.setType(stage);
					commonFilter.setAtype(approval);
					commonFilter.setActwise(fiptype);
					commonFilter.setEmpch(loginemploye);
					commonFilter.setEmpWise(rolenames);
				//	commonFilter.setFlid(flid);
						List<String[]> Resource = projectService.getProjectview(commonFilter);
						JSONObject resource = UIUtils.convertToJqGridTableObject(Resource, request,3, 0,commonFilter.getTotalRecordCnt());
						out.println(resource);
				} catch (Exception e) {
				//	e.printStackTrace();
				}
		}
		else if (action.equals("dmcprojectsprotomain_getExcel.prpo")) {
			String isClosure= request.getParameter("isClosure");			
		//String flid= request.getParameter("flid");
		String type="view";
		String fitype=request.getParameter("fiptype");
		CommonMessage.debugMsg("fitype"+fitype);
		commonFilter = populateCommonFilter(request,"ProjectCreation",false);
		commonFilter.setKey(isClosure);
		commonFilter.setTC(type);
		//commonFilter.setFlid(flid);
		JSONObject colmodel = UIUtils.getXlColModel(request, response) ;
		//(JSONObject) httpSession.getAttribute("projectcreation");
		colmodel.put("title","Project Creation");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = projectService.getdmcProjectviewExcel(colmodel,format,commonFilter);
		ExcelUtils.writeToResponse(response, wb, "ProjectCreation", format);
		}
		else if (action.equals("projectsprotomain_getExcel.prpo")) {
			String isClosure= request.getParameter("isClosure");			
			//String flid= request.getParameter("flid");
			String fitype=request.getParameter("fiptype");
			CommonMessage.debugMsg("fitype"+fitype);
			commonFilter = populateCommonFilter(request,"ProjectCreation",false);
			commonFilter.setKey(isClosure);
			commonFilter.setAbnCatch(fitype);
			commonFilter.setFromRow(null);
			//commonFilter.setFlid(flid);
			JSONObject colmodel =(JSONObject) httpSession.getAttribute("projectcreation");// UIUtils.getXlColModel(request, response) ;
			colmodel.put("title","Project Creation");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = projectService.getProjectCreationExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "ProjectCreation", format);
		}
		else if (action.equals("projectsprotomainView_getExcel.prpo")) {
			String isClosure= request.getParameter("isClosure");			
			//String flid= request.getParameter("flid");
			String type="view";
			commonFilter = populateCommonFilter(request,"ProjectCreation",false);
			commonFilter.setKey(isClosure);
			commonFilter.setTC(type);
			commonFilter.setFromRow(null);
			//commonFilter.setFlid(flid);
			JSONObject colmodel = UIUtils.getXlColModel(request, response) ;//(JSONObject) httpSession.getAttribute("projectcreation");
			colmodel.put("title","Project Creation");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = projectService.getProjectCreationExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "ProjectCreation", format);
		}
		else if (action.equals("projectCreationList_getExcel.prpo"))
		{
		//httpSession = request.getSession(false);
		commonFilter = populateCommonFilter(request,"AchievementFilter",false);
		JSONObject colmodel = UIUtils.getXlColModel(request, response);
		colmodel.put("title","ProjectCreation  Report");
        String format = ExcelUtils.getFormat(request);	
        CommonMessage.debugMsg("common");
        commonFilter.setFromRow(null);
		Workbook wb = projectService.getprojectlistExcel(colmodel,format,commonFilter);
		ExcelUtils.writeToResponse(response, wb, "ProjectCreation Reports", format);
		

}
		else if (action.equals("dmcprojectCreationList_getExcel.prpo"))
		{
		//httpSession = request.getSession(false);
		commonFilter = populateCommonFilter(request,"AchievementFilter",false);
		JSONObject colmodel = UIUtils.getXlColModel(request, response);
		colmodel.put("title","ProjectCreation  Report");
        String format = ExcelUtils.getFormat(request);	
        CommonMessage.debugMsg("common");
		Workbook wb = projectService.getdmcprojectlistExcel(colmodel,format,commonFilter);
		ExcelUtils.writeToResponse(response, wb, "ProjectCreation Reports", format);
		

}
		else if (action.equals("projectsproto_getCol.prpo")) {
			getColResources(request, response, httpSession);
			
		} else if (action.equals("projectsproto_getData.prpo")) {
			getDataResources(request, response, httpSession);
		}
		else if (action.equals("projectsmile_getCol.prpo")) {
			PrintWriter out = response.getWriter();
				try {
					String keyid = request.getParameter("keyid");
					//commonFilter.setKey(keyid);
					List<String[]> milestone = projectService.getMilesStone(keyid);
						JSONObject jsonObject = getTableModelMile(milestone);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} else if (action.equals("projectsmile_getData.prpo")) {
			//CommonMessage.debugMsg("get data method");
			PrintWriter out = response.getWriter();
				try {
					String keyid = request.getParameter("keyid");
					//commonFilter.setKey(keyid);
						List<String[]> milestone = projectService.getMilesStone(keyid);
						JSONObject Milestone = UIUtils.convertToJqGridTableObject(
								milestone, request, 1, 0);
						out.println(Milestone);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		else if (action.equals("projectskaizen_getCol.prpo")) {
			PrintWriter out = response.getWriter();
				try {
					String flid = request.getParameter("flid");
					String masterkeyid = request.getParameter("master");
					//CommonMessage.debugMsg("pillarHdn "+flid);
					//commonFilter.setKey(masterkeyid);
					//commonFilter.setFlid(flid);
					List<String[]> kaizen = projectService.getKaizen(masterkeyid,flid);
						JSONObject jsonObject = getTableModelKaizen(kaizen);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} else if (action.equals("projectskaizen_getData.prpo")) {
			//CommonMessage.debugMsg("get data method");
			PrintWriter out = response.getWriter();
			try {
				String flid = request.getParameter("flid");
				String masterkeyid = request.getParameter("master");
				//CommonMessage.debugMsg("pillarHdn "+flid);
			//	commonFilter.setKey(masterkeyid);
			//	commonFilter.setFlid(flid);
					List<String[]> kaizen = projectService.getKaizen(masterkeyid,flid);
					JSONObject Kaizen = UIUtils.convertToJqGridTableObject(kaizen, request, 1, 0);
					out.println(Kaizen);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (action.equals("projectsdmaic_save.prpo")) {
			saveDMAICVerificationStatus(request, response);
		}
		else if (action.equals("projectsdmaic_getCol.prpo")) {
			getColDMAIC(request, response, httpSession);			
			
		} else if (action.equals("projectsdmaic_getData.prpo")) {
			getDataDMAIC(request, response, httpSession);		
		}		
		else if (action.equals("projectsKpi_getCol.prpo")) {
			PrintWriter out = response.getWriter();
			//String key = request.getParameter("masterkeyid");
				try {
					//List<String[]> kaizen =  projectService.getKpi(null);
					List<String[]> kaizen = new ArrayList<>();

					kaizen.add(new String[]{
							"1",
					    "Key Performance Indicator",
					    "Base Value",
					    "Target Value"
					});
						JSONObject jsonObject = getTableModelKpi(kaizen);
						out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
		} else if (action.equals("projectsKpi_getData.prpo")) {
			//CommonMessage.debugMsg("get data dmaic");
			PrintWriter out = response.getWriter();
			String key = request.getParameter("masterkeyid");
			try {
				//commonFilter.setKey(key);
					List<String[]> kaizen = projectService.getKpi(key);
					JSONObject Kaizen = UIUtils.convertToJqGridTableObject(kaizen, request, 1, 0);
					out.println(Kaizen);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("addResourcesList_input.prpo")){
			String kzpmKeyid = request.getParameter("kzpmKeyid");
			CommonMessage.debugMsg("kzpmKeyid1234="+kzpmKeyid);
			String flid = request.getParameter("flid");
			request.setAttribute("flid", flid);
			request.setAttribute("kzpmKeyid", kzpmKeyid);
			RequestDispatcher rd= request.getRequestDispatcher("/pages/KK/addResources.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("dmcaddResourcesList_input.prpo")){
			String kzpmKeyid = request.getParameter("kzpmKeyid");
			String flid = request.getParameter("flid");
			request.setAttribute("flid", flid);
			request.setAttribute("kzpmKeyid", kzpmKeyid);
			RequestDispatcher rd= request.getRequestDispatcher("/pages/KK/dmcaddResources.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("addResourcesList_getCol.prpo")){
			 /*PrintWriter out = response.getWriter();
			 String getCol = UIUtils.getPropertyValue("com.akranta.tpm.resources.Resource", "colModelEmployee");
			 out.print(getCol);*/
			 getColNewResources(request, response, httpSession);
		}
		else if(action.equals("addResourcesList_getData.prpo")){
			getDataNewResources(request, response, httpSession);
		}
		else if(action.equals("dmcaddResourcesList_getCol.prpo")){
			 /*PrintWriter out = response.getWriter();
			 String getCol = UIUtils.getPropertyValue("com.akranta.tpm.resources.Resource", "colModelEmployee");
			 out.print(getCol);*/
			 getColNewResources(request, response, httpSession);
		}
		else if(action.equals("dmcaddResourcesList_getData.prpo")){
			getDataNewResources(request, response, httpSession);
		}
		else if(action.equals("addjhmemberResourcesList_getCol.prpo")){
			CommonFilter commonFilter1 = populateCommonFilter(request,"addJhmembersCommonFilter",true);
			String flid =request.getParameter("flid");
			String role =request.getParameter("role");
			String jhmembers =request.getParameter("jhmembers");
			String kzpmKeyid =request.getParameter("kzpmKeyid");
			String saveKzpmKeyid =request.getParameter("saveKzpmKeyid");
			
			List<String[]> momReportList=null;
			PrintWriter out = response.getWriter();
			commonFilter1.setFlid(flid);
			commonFilter1.setRoleLevel(role);
			
			
			if(UIUtils.isValidKeyId(jhmembers)){
				commonFilter1.setJH(jhmembers);	
			    commonFilter1.setKey(kzpmKeyid);
			}
			if(UIUtils.isValidKeyId(saveKzpmKeyid))
				commonFilter1.setKK(saveKzpmKeyid);
			
			try {
				 FilterValues.getCommonFilters(request, commonFilter1);
				 momReportList = projectService.getJhmemberaddresources(commonFilter1,flid);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();
			 gridColModel.setHeaderNum(1);
			
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 jqGridTableModel.setGridEdit(true);
			
			 String [] colHeader = momReportList.get(1);			
			 String [] colHeaderCond = momReportList.get(0);
			 List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("AddJhmembersColmodel");
			httpSession.setAttribute("AddJhmembersColmodel", jsonObject);
			jsonObject.put("tableHeight", "70%%");
			jsonObject.put("tableWidth", "40%%");
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
		}
		else if(action.equals("addjhmemberResourcesList_getData.prpo")){
			
	     	try {
		     		String flid =request.getParameter("flid");
					String role =request.getParameter("role");
					String jhmembers =request.getParameter("jhmembers");
					String kzpmKeyid =request.getParameter("kzpmKeyid");
					String saveKzpmKeyid =request.getParameter("saveKzpmKeyid");
					
					CommonFilter commonFilter1 = populateCommonFilter(request,"addJhmembersCommonFilter",false);
					commonFilter1.setFlid(flid);
					commonFilter1.setRoleLevel(role);
					
					if(UIUtils.isValidKeyId(jhmembers)){
						commonFilter1.setJH(jhmembers);	
					    commonFilter1.setKey(kzpmKeyid);
					}
					
					if(UIUtils.isValidKeyId(saveKzpmKeyid))
						commonFilter1.setKK(saveKzpmKeyid);
					
					List<String[]> MachineGrid = projectService.getJhmemberaddresources(commonFilter1,flid);
					JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter1.getTotalRecordCnt());
					PrintWriter out = response.getWriter();
					out.println(machinegrid);
			
		        } catch (Exception e) {
		        	CommonMessage.debugMsg(e.getMessage());
		        }
			}
			
		else if(action.equals("dmcaddjhmemberResourcesList_getCol.prpo")){
			CommonFilter commonFilter1 = populateCommonFilter(request,"addJhmembersCommonFilter",true);
			String flid =request.getParameter("flid");
			String role =request.getParameter("role");
			String jhmembers =request.getParameter("jhmembers");
			String kzpmKeyid =request.getParameter("kzpmKeyid");
			String saveKzpmKeyid =request.getParameter("saveKzpmKeyid");
			String allemployee = request.getParameter("allepmloyes");
			CommonMessage.debugMsg("allemployee="+allemployee);
			commonFilter1.setAreatype(allemployee);
			
			List<String[]> momReportList=null;
			PrintWriter out = response.getWriter();
			commonFilter1.setFlid(flid);
			commonFilter1.setRoleLevel(role);
			
			
			if(UIUtils.isValidKeyId(jhmembers)){
				commonFilter1.setJH(jhmembers);	
			    commonFilter1.setKey(kzpmKeyid);
			}
			if(UIUtils.isValidKeyId(saveKzpmKeyid))
				commonFilter1.setKK(saveKzpmKeyid);
			
			try {
				 FilterValues.getCommonFilters(request, commonFilter1);
				 momReportList = projectService.getdmcJhmemberaddresources(commonFilter1,flid);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();
			 gridColModel.setHeaderNum(1);
			
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 jqGridTableModel.setGridEdit(true);
			
			 String [] colHeader = momReportList.get(1);			
			 String [] colHeaderCond = momReportList.get(0);
			 List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("AddJhmembersColmodel");
			httpSession.setAttribute("AddJhmembersColmodel", jsonObject);
			jsonObject.put("tableHeight", "70%%");
			jsonObject.put("tableWidth", "40%%");
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
		}
		else if(action.equals("dmcaddjhmemberResourcesList_getData.prpo")){
			
     	try {
	     		String flid =request.getParameter("flid");
				String role =request.getParameter("role");
				String jhmembers =request.getParameter("jhmembers");
				String kzpmKeyid =request.getParameter("kzpmKeyid");
				String saveKzpmKeyid =request.getParameter("saveKzpmKeyid");
				
				CommonFilter commonFilter1 = populateCommonFilter(request,"addJhmembersCommonFilter",false);
				commonFilter1.setFlid(flid);
				commonFilter1.setRoleLevel(role);
				
				String allemployee = request.getParameter("allepmloyes");
				CommonMessage.debugMsg("allemployee="+allemployee);
				commonFilter1.setAreatype(allemployee);
				
				if(UIUtils.isValidKeyId(jhmembers)){
					commonFilter1.setJH(jhmembers);	
				    commonFilter1.setKey(kzpmKeyid);
				}
				
				if(UIUtils.isValidKeyId(saveKzpmKeyid))
					commonFilter1.setKK(saveKzpmKeyid);
				
				List<String[]> MachineGrid = projectService.getdmcJhmemberaddresources(commonFilter1,flid);
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter1.getTotalRecordCnt());
				PrintWriter out = response.getWriter();
				out.println(machinegrid);
		
	        } catch (Exception e) {
	        	CommonMessage.debugMsg(e.getMessage());
	        }
		}
		
		else if(action.equals("combo_KPIIndicator.prpo")){
			ComboFilter comboFilter=UIUtils.fillComboFilter(request);
			String flid =request.getParameter("flid");
			String pillarCode = request.getParameter("pillarCode");
			List<ComboBox>  indicators = projectService.getProjectMetricsKpiIndicator(comboFilter,pillarCode,flid);
			UIUtils.writeComboBox(response, indicators,comboFilter);
		}
		else if(action.equals("combo_dmcproject.prpo")){
			ComboFilter comboFilter=UIUtils.fillComboFilter(request);
			String flid =request.getParameter("flid");
			CommonMessage.debugMsg("location in combo="+flid);
			//String pillarCode = request.getParameter("pillarCode");
			List<ComboBox>  indicators = projectService.getdmcemployeecombo(comboFilter,flid);
			UIUtils.writeComboBox(response, indicators,comboFilter);
		}
		else if(action.equals("comboWave.prpo")){
			try 
			{
				String pillarid = request.getParameter("pillarid");
				String flid = request.getParameter("flid");
				String pillargrp = request.getParameter("pillargrp");
				String condSql="";
				ComboFilter comboFilter = new ComboFilter();
				comboFilter=UIUtils.fillComboFilter(request);
					
				if(pillargrp != null)
					condSql=" AND  MGRM_PILLARID = '"+pillarid+"' AND MGRM_FLID='"+flid+"' "; 
				
				List<ComboBox>  TagClass = projectService.getcombowave(condSql,comboFilter);
			    UIUtils.writeComboBox(response, TagClass ,comboFilter);
			    
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
		}
		else if (action.equals("Dmaic_input.prpo")) {
			
			UIUtils.forwardRequest(request, response,"/pages/KK/Dmaiccountrpt.jsp");
		} else if (action.equals("Dmaic_getCol.prpo")) {

			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			// CommonFilter commonFilter = new CommonFilter();
			commonFilter = populateCommonFilter(request,
					"DmaicCommonFilter", true);
			
			String wave = request.getParameter("wave");
			commonFilter.setColVal(wave);
			CommonMessage.debugMsg("Ingetcol::::"+wave);

			
			FilterValues.getSafty(request, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			httpSession.removeAttribute("DmaicCommonFilter");
			httpSession.setAttribute("DmaicCommonFilter", commonFilter);
			List<String[]> neardrillList = projectService.getDmaiccunt(commonFilter);


			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(1);

			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			List<String[]> headers = new ArrayList<String[]>();
			String[] colHeader2 = neardrillList.get(1);
			//String[] colHeader3 = neardrillList.get(3);
			String[] colHeaderHead = neardrillList.get(0);
			headers.add(colHeader2);
			//headers.add(colHeader3);

			JSONObject jsonObject = UIUtils.getTableModel(headers,
					colHeaderHead, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "72%%");
			httpSession.removeAttribute("DmaicColModel");
			httpSession.setAttribute("DmaicColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("Dmaic_getData.prpo")) {
			try {
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();

				String flid = request.getParameter("flid");
				String drillflag = request.getParameter("drillFlag");
				commonFilter = populateCommonFilter(request,
						"DmaicCommonFilter", false);
				// commonFilter.setRowTotal('N');
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (UIUtils.isValidKeyId(drillflag)) {
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}
				
				String wave = request.getParameter("wave");
				commonFilter.setColVal(wave);
				CommonMessage.debugMsg("Ingetdata::::"+wave);
				
				
				List<String[]> abndrillList = projectService
						.getDmaiccunt(commonFilter);
				JSONObject jsonObject = UIUtils.convertToJqGridTableObject(
						abndrillList, request, 2, 0);
				out.println(jsonObject);
				httpSession.removeAttribute("DmaicCommonFilter");
				httpSession.setAttribute("DmaicCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		else if (action.equals("Dmaic_getExcel.prpo")) {
			httpSession = request.getSession(false);
			CommonFilter commonFilter1 = populateCommonFilter(request,
					"DmaicCommonFilter", false);
			JSONObject colmodel = UIUtils.getXlColModel(request, response);
			colmodel.put("title", "DMAIC Report");
			String format = ExcelUtils.getFormat(request);
			
			//String wave = request.getParameter("wave");
			//commonFilter.setColVal(wave);
			//CommonMessage.debugMsg("IngetEXcel::::"+wave);
			
			Workbook wb = projectService.getdmaicExportToExcel(colmodel,
					format, commonFilter1);
			ExcelUtils.writeToResponse(response, wb, "DMAIC Report", format);

		} 
		else if (action.equals("piechart_chrt.prpo")) {
			String forDashboard = request.getParameter("dashboard");
			String flag = request.getParameter("flag");
			
			String flids=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("functionlocation  is"+flids);
			String lcnname=dashboardService.Functionallocn(flids);
			String title = "";
			//CommonFilter commonFilter = null;
			CommonFilter chrtCommonFilter = new CommonFilter();

			commonFilter = (CommonFilter)httpSession.getAttribute("DmaicCommonFilter");
			//commonFilter = populateCommonFilter(request, "DmaicCommonFilter",false);

			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter);

			String fromMonth = chrtCommonFilter.getFromMonth();
			String toMonth = chrtCommonFilter.getToMonth();

			String month1 = fromMonth + " - " + toMonth;
			String fromdate = chrtCommonFilter.getFromDate();
			String todate = chrtCommonFilter.getToDate();
			String date = fromdate + " - " + todate;
		//	if (flag.equals("Y")) {
		//		title = "Pending Report" + " - " + date;
	//		} else {
				title = "DAMIC Count " ;// + " - " + date;
				
				//String title  =  titlename +  "- DAMIC Count  ";
	//		}

			// title = "DMAIC Report" + " - "+date;
			/*if (chrtCommonFilter.getMonwise().equals("Y")) {
				if (flag.equals("Y")) {
					title = "Pending Report" + " - " + month1;
				} else {
					title = "Completed Report" + " - " + month1;
				}
			}*/
			if (chrtCommonFilter.getRowTotal() == null)
				chrtCommonFilter.setRowTotal('N');

			chrtCommonFilter.setFirstLevel("Y");
			List<String[]> graphData = projectService
					.getpiechart(chrtCommonFilter);

			ChartPie pieChart = new ChartPie();

			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

			PrintWriter out = response.getWriter();
			//String[] header = graphData.get(1);
			String[] header1 = graphData.get(2);

			String[] data = graphData.get(3);

			String subTitle = "";// header[3]

			List<Object[]> dataList = new ArrayList<Object[]>();

			int startIndx = 5;
			if (!"Y".equals(flag))
				startIndx = 6;

			for (int i = startIndx; i < data.length; i++) {
				Object[] pieData = new Object[2];
				pieData[0] = header1[i];
				pieData[1] = Double.parseDouble(data[i]);
				dataList.add(pieData);
			}

			ChartSeries chartSeries = new ChartSeries();
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeriesList.add(chartSeries);
			List<Integer> center = new ArrayList<Integer>();
			center.add(350);
			center.add(200);
			chartSeries.setCenter(center);
			chartSeries.setSize(250);
			
			JSONObject chartObj = pieChart.drawChart(chartSeriesList, title,
					subTitle);
			UIUtils.setDashBoardIdentifier(request, chartObj);

			out.println(chartObj);
			out.close();
		}
		else if (action.equals("dmcDmaic_input.prpo")) {
			
			String fiptype = request.getParameter("fiptype");
			request.setAttribute("fiptype",fiptype);
			UIUtils.forwardRequest(request, response,"/pages/KK/dmcDmaiccountrpt.jsp");
		} else if (action.equals("dmcDmaic_getCol.prpo")) {

			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			// CommonFilter commonFilter = new CommonFilter();
			commonFilter = populateCommonFilter(request,
					"DmaicCommonFilter", true);
			
			String wave = request.getParameter("wave");
			commonFilter.setColVal(wave);
			CommonMessage.debugMsg("Ingetcol::::"+wave);

			
			FilterValues.getSafty(request, commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
			httpSession.removeAttribute("DmaicCommonFilter");
			httpSession.setAttribute("DmaicCommonFilter", commonFilter);
			List<String[]> neardrillList = projectService
					.getdmcDmaiccunt(commonFilter);


			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(1);

			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			List<String[]> headers = new ArrayList<String[]>();
			String[] colHeader2 = neardrillList.get(2);
			//String[] colHeader3 = neardrillList.get(3);
			String[] colHeaderHead = neardrillList.get(1);
			headers.add(colHeader2);
			//headers.add(colHeader3);

			JSONObject jsonObject = UIUtils.getTableModel(headers,
					colHeaderHead, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "72%%");
			httpSession.removeAttribute("DmaicColModel");
			httpSession.setAttribute("DmaicColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("dmcDmaic_getData.prpo")) {
			try {
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();

				String flid = request.getParameter("flid");
				String drillflag = request.getParameter("drillFlag");
				commonFilter = populateCommonFilter(request,
						"DmaicCommonFilter", false);
				// commonFilter.setRowTotal('N');
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (UIUtils.isValidKeyId(drillflag)) {
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}
				
				String wave = request.getParameter("wave");
				commonFilter.setColVal(wave);
				CommonMessage.debugMsg("Ingetdata::::"+wave);
				
				
				List<String[]> abndrillList = projectService
						.getdmcDmaiccunt(commonFilter);
				JSONObject jsonObject = UIUtils.convertToJqGridTableObject(
						abndrillList, request, 3, 0);
				out.println(jsonObject);
				httpSession.removeAttribute("DmaicCommonFilter");
				httpSession.setAttribute("DmaicCommonFilter", commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}
		else if (action.equals("dmcDmaic_getExcel.prpo")) {
			httpSession = request.getSession(false);
			CommonFilter commonFilter1 = populateCommonFilter(request,
					"DmaicCommonFilter", false);
			JSONObject colmodel = UIUtils.getXlColModel(request, response);
			colmodel.put("title", "DMAIC Report");
			String format = ExcelUtils.getFormat(request);
			
			//String wave = request.getParameter("wave");
			//commonFilter.setColVal(wave);
			//CommonMessage.debugMsg("IngetEXcel::::"+wave);
			
			Workbook wb = projectService.getdmcdmaicExportToExcel(colmodel,
					format, commonFilter1);
			ExcelUtils.writeToResponse(response, wb, "DMAIC Report", format);

		} 
		else if (action.equals("dmcpiechart_chrt.prpo")) {
			String forDashboard = request.getParameter("dashboard");
			String flag = request.getParameter("flag");
			String title = "";
			//CommonFilter commonFilter = null;
			CommonFilter chrtCommonFilter = new CommonFilter();

			commonFilter = (CommonFilter)httpSession.getAttribute("DmaicCommonFilter");
			//commonFilter = populateCommonFilter(request, "DmaicCommonFilter",false);

			BeanUtils.copyProperties(chrtCommonFilter, commonFilter);
			FilterValues.getCommonFilters(request, chrtCommonFilter);

			String fromMonth = chrtCommonFilter.getFromMonth();
			String toMonth = chrtCommonFilter.getToMonth();

			String month1 = fromMonth + " - " + toMonth;
			String fromdate = chrtCommonFilter.getFromDate();
			String todate = chrtCommonFilter.getToDate();
			String date = fromdate + " - " + todate;
		//	if (flag.equals("Y")) {
		//		title = "Pending Report" + " - " + date;
	//		} else {
				title = "DAMIC Count " ;// + " - " + date;
	//		}

			// title = "DMAIC Report" + " - "+date;
			/*if (chrtCommonFilter.getMonwise().equals("Y")) {
				if (flag.equals("Y")) {
					title = "Pending Report" + " - " + month1;
				} else {
					title = "Completed Report" + " - " + month1;
				}
			}*/
			if (chrtCommonFilter.getRowTotal() == null)
				chrtCommonFilter.setRowTotal('N');

			chrtCommonFilter.setFirstLevel("Y");
			List<String[]> graphData = projectService
					.getdmcpiechart(chrtCommonFilter);

			ChartPie pieChart = new ChartPie();

			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

			PrintWriter out = response.getWriter();
			//String[] header = graphData.get(1);
			String[] header1 = graphData.get(2);

			String[] data = graphData.get(3);

			String subTitle = "";// header[3]

			List<Object[]> dataList = new ArrayList<Object[]>();

			int startIndx = 5;
			if (!"Y".equals(flag))
				startIndx = 6;

			for (int i = startIndx; i < data.length; i++) {
				Object[] pieData = new Object[2];
				pieData[0] = header1[i];
				pieData[1] = Double.parseDouble(data[i]);
				dataList.add(pieData);
			}

			ChartSeries chartSeries = new ChartSeries();
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeriesList.add(chartSeries);
			List<Integer> center = new ArrayList<Integer>();
			center.add(350);
			center.add(200);
			chartSeries.setCenter(center);
			chartSeries.setSize(250);
			
			JSONObject chartObj = pieChart.drawChart(chartSeriesList, title,
					subTitle);
			UIUtils.setDashBoardIdentifier(request, chartObj);

			out.println(chartObj);
			out.close();
		}
		
		else if (action.equals("projectsprotoview_Approvals.prpo")) {
			String rolename=request.getParameter("roleName");
			String refId=request.getParameter("refId");
			String nxtrole=request.getParameter("nxtrole");
			String trnscode=request.getParameter("trns");
			String lstlvl=request.getParameter("lstlvl");
			String flId=request.getParameter("flId");
			String roleid=request.getParameter("roleid");
			String status=request.getParameter("status");
			String workFlowData=request.getParameter("workFlowData");
			CommonMessage.debugMsg(rolename+refId+nxtrole+trnscode+lstlvl+flId+roleid);
			CommonMessage.debugMsg("workFlowData In sidddddddddddddd "+workFlowData);
			List<String[]> insertquery= projectService.setInsertQuery(rolename,refId,nxtrole,trnscode,lstlvl,flId,roleid,status);
			
		
		}

	}
	private void removeProjectResource(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		try{
			if(UIUtils.isValidKeyId(keyid)){
				projectService.DeleteJhMemberRecord(keyid);
				String msgPropertyIdnt = "success-delete";
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
		}catch(Exception e)
			{
				CommonMessage.debugMsg("Exception: "+e);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
		
	}
	private void getMileStoneStages(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession httpSession = request.getSession(false);
    	String stages="";
    	try{
        	ServletOutputStream out = response.getOutputStream();
    		String kznKeyId =  request.getParameter("kznKeyId");    		 
    		stages = projectService.getMileStoneStages(kznKeyId);
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("stages", stages);
			returnData.put("successData",successData);
			out.print(returnData.toString());
    	}
    	catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
		}	
	}
	
	private void getWorkFlowStatus(HttpServletRequest request,
			HttpServletResponse response) {
	//	HttpSession httpSession = request.getSession(false); 
    	String stage =  request.getParameter("updateStage");
    	String wfStatus=request.getParameter("wfStatus");
    	CommonMessage.debugMsg(" IN side the Servlet "+ stage);
    	try{
        	ServletOutputStream out = response.getOutputStream();
    		String kznKeyId =  request.getParameter("kznKeyId");
    		JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();			
			/*String defineWOStatus="";
			//String maicStage="";			
			String measureWOStatus="";
			String analyseWOStatus="";
			String improveWOStatus="";
			String controlWOStatus="";
			String closureWOStatus="";
			//CommonMessage.debugMsg("getWorkFlowStatus");
			*/
			if(UIUtils.isValidKeyId(stage)){
	    		if(stage.equals("D"))
	    			projectService.getWorkFlowStaus(kznKeyId,"PRODE","FIPRODEF",true,wfStatus);		    	
	    		
	    		else if(stage.equals("M")){
	    			projectService.getWorkFlowStaus(kznKeyId,"PROME","FIPROMEA",true,wfStatus);
	    		}
	    		else if(stage.equals("A")){
	    			 projectService.getWorkFlowStaus(kznKeyId,"PROAN","FIPROANA",true,wfStatus);
	    		}
	    		else if(stage.equals("I")){
	    			 projectService.getWorkFlowStaus(kznKeyId,"PROIM","FIPROIMP",true,wfStatus);
	    		}
	    		else if(stage.equals("C")){
	    			 projectService.getWorkFlowStaus(kznKeyId,"PROCO","FIPROCON",true,wfStatus);
	    		}
	    		else if(stage.equals("X")){
	    			 projectService.getWorkFlowStaus(kznKeyId,"PROCL","FIPROCLO",true,wfStatus);
	    		}
    		}
			
			KznTlProjectcreationmst projectmst = projectService.getAllStageStatus(kznKeyId);
			successData.put("defineStage", projectmst.getKzpmDefinestage());
			successData.put("measureStage",projectmst.getKzpmMeasurestage());
			successData.put("analyseStage",projectmst.getKzpmAnalysestage());
			successData.put("improveStage", projectmst.getKzpmImprovestage());
			successData.put("controlStage",projectmst.getKzpmControlstage());
			successData.put("closureStage",projectmst.getKzpmClosurestage());
			/*maicStage=	projectService.getMaicStage(kznKeyId,"D");				
			successData.put("defineStage", maicStage);
			////CommonMessage.debugMsg("defineStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"M");				
			successData.put("measureStage", maicStage);
			////CommonMessage.debugMsg("measureStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"A");
			successData.put("analyseStage", maicStage);
			////CommonMessage.debugMsg("analyseStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"I");
			successData.put("improveStage", maicStage);
			////CommonMessage.debugMsg("improveStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"C");
			successData.put("controlStage", maicStage);
			////CommonMessage.debugMsg("controlStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"X");
			successData.put("closureStage", maicStage);
			////CommonMessage.debugMsg("closureStage"+maicStage);
    		////CommonMessage.debugMsg("getWorkFlowStatus1"+kznKeyId);*/
			
			returnData.put("successData",successData);
			out.print(returnData.toString());
    	}
    	catch(Exception e)
		{
			////CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		//	JSONObject err = new JSONObject();
		}	
	}
	
	private void getdmcWorkFlowStatus(HttpServletRequest request,
			HttpServletResponse response) {
	//	HttpSession httpSession = request.getSession(false); 
    	String stage =  request.getParameter("updateStage");
    	String wfStatus=request.getParameter("wfStatus");
    	try{
        	ServletOutputStream out = response.getOutputStream();
    		String kznKeyId =  request.getParameter("kznKeyId");
    		JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();			
			/*String defineWOStatus="";
			//String maicStage="";			
			String measureWOStatus="";
			String analyseWOStatus="";
			String improveWOStatus="";
			String controlWOStatus="";
			String closureWOStatus="";
			//CommonMessage.debugMsg("getWorkFlowStatus");
			*/
			if(UIUtils.isValidKeyId(stage)){
	    		if(stage.equals("D"))
	    			projectService.getdmcWorkFlowStaus(kznKeyId,"PRODE","FIPRODEF",true,wfStatus);		    	
	    		
	    		else if(stage.equals("M")){
	    			projectService.getdmcWorkFlowStaus(kznKeyId,"PROME","FIPROMEA",true,wfStatus);
	    		}
	    		else if(stage.equals("A")){
	    			 projectService.getdmcWorkFlowStaus(kznKeyId,"PROAN","FIPROANA",true,wfStatus);
	    		}
	    		else if(stage.equals("I")){
	    			 projectService.getdmcWorkFlowStaus(kznKeyId,"PROIM","FIPROIMP",true,wfStatus);
	    		}
	    		else if(stage.equals("C")){
	    			 projectService.getdmcWorkFlowStaus(kznKeyId,"PROCO","FIPROCON",true,wfStatus);
	    		}
	    		else if(stage.equals("X")){
	    			 projectService.getdmcWorkFlowStaus(kznKeyId,"PROCL","FIPROCLO",true,wfStatus);
	    		}
    		}
			
			String[] statusData = projectService.getdmcAllStageStatus(kznKeyId);
//			successData.put("defineStage", projectmst.getKzpmDefinestage());
//			successData.put("measureStage",projectmst.getKzpmMeasurestage());
//			successData.put("analyseStage",projectmst.getKzpmAnalysestage());
//			successData.put("improveStage", projectmst.getKzpmImprovestage());
//			successData.put("controlStage",projectmst.getKzpmControlstage());
//			successData.put("closureStage",projectmst.getKzpmClosurestage());
			successData.put("defineStage", statusData[0]);
			successData.put("measureStage",statusData[1]);
			successData.put("analyseStage", statusData[2]);
			successData.put("improveStage", statusData[3]);
			successData.put("controlStage", statusData[4]);
			successData.put("closureStage", statusData[5]);
			
			/*maicStage=	projectService.getMaicStage(kznKeyId,"D");				
			successData.put("defineStage", maicStage);
			////CommonMessage.debugMsg("defineStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"M");				
			successData.put("measureStage", maicStage);
			////CommonMessage.debugMsg("measureStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"A");
			successData.put("analyseStage", maicStage);
			////CommonMessage.debugMsg("analyseStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"I");
			successData.put("improveStage", maicStage);
			////CommonMessage.debugMsg("improveStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"C");
			successData.put("controlStage", maicStage);
			////CommonMessage.debugMsg("controlStage"+maicStage);
			maicStage=	projectService.getMaicStage(kznKeyId,"X");
			successData.put("closureStage", maicStage);
			////CommonMessage.debugMsg("closureStage"+maicStage);
    		////CommonMessage.debugMsg("getWorkFlowStatus1"+kznKeyId);*/
			
			returnData.put("successData",successData);
			out.print(returnData.toString());
    	}
    	catch(Exception e)
		{
			////CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		//	JSONObject err = new JSONObject();
		}	
	}
	
	private void saveKaizenLinkcreation(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	if( httpSession != null && user != null)
    	{	
    		KznTlProjectKaizenLink newKznTlProjectKaizenLink = new KznTlProjectKaizenLink();
    		KznTlProjectKaizenLink existKznTlProjectKaizenLink = new KznTlProjectKaizenLink();
    	try{
    		 String projectKaizen =  request.getParameter("projectKaizen");
    		 //CommonMessage.debugMsg("projectKaizen: "+projectKaizen);
    		 List<KznTlProjectKaizenLink> projectKaizenlink =null;
    		 JSONArray jsonKaizen = null;
   		  if(UIUtils.isValidKeyId(projectKaizen)){ //master json
   				if( projectKaizen != null && ! projectKaizen.isEmpty())
   	    		{
   					jsonKaizen = JSONArray.fromString(projectKaizen);
   					projectKaizenlink=(List<KznTlProjectKaizenLink>)UIUtils.convertJSONArrToList(newKznTlProjectKaizenLink, jsonKaizen);
   	    		}
   			   if(projectKaizenlink!=null)
   			   {
   				
   				   newKznTlProjectKaizenLink.setProjectKaizen(projectKaizenlink);
   					//CommonMessage.debugMsg("lstEntTlAssessmentmst.size(): "+user.getUsrm_keyid());
   				}
   		  }
   		newKznTlProjectKaizenLink.setKplkCreatedby(user.getUsrm_keyid());
   		 existKznTlProjectKaizenLink = projectService.create(newKznTlProjectKaizenLink,existKznTlProjectKaizenLink);
			String saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", saveMsg);
			returnData.put("successData",successData);
			returnData.put("formClear",true);
			out.print(returnData.toString());
    	}catch (ValidationExceptions e) {
			//CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
			out.print(errMessage.toString());
    	}
    	catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			outt.print(err.toString());
		}
    	}			
	}
	private void deleteMilestoneDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("keyid");
			try{
				if(UIUtils.isValidKeyId(keyid)){
					projectService.deleteMilestone(keyid);
					String msgPropertyIdnt = "success-delete";
					JSONObject err = new JSONObject();
					String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
					err.put("successData",mesg);
					//CommonMessage.debugMsg(err.toString());
					out.print(err.toString());
				}
			}catch(ValidationExceptions e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				out.print(err.toString());
			}catch(BusinessApplicationExceptions e)
			{
					JSONObject err = new JSONObject();
					out.print(err.toString());
					
			}catch(Exception e)
			{
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				out.print(err.toString());
			}
		
	}
	private void deleteMilestone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	KznTlProjectmaicMileMst existKznTlProjectmaicMileMst= (KznTlProjectmaicMileMst)httpSession.getAttribute("newSession");
    	KznTlProjectmaicMileMst newKznTlProjectmaicMileMst = new KznTlProjectmaicMileMst ();
    	newKznTlProjectmaicMileMst=(KznTlProjectmaicMileMst)UIUtils.setBeanProperties((Object)newKznTlProjectmaicMileMst,request);
    	existKznTlProjectmaicMileMst = projectService.delete(newKznTlProjectmaicMileMst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
		
	}
	private void saveMilestone(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String maicStage="";
    	if( httpSession != null && user != null)
    	{	
    		try{
    		String mileStone = request.getParameter("Milestone");
    		KznTlProjectmaicMileMst newKznTlProjectmaicMileMst = new KznTlProjectmaicMileMst();
    		KznTlProjectmaicMileDtl newKznTlProjectmaicMileDtl = new KznTlProjectmaicMileDtl();
    		KznTlProjectmaicMileMst existKznTlProjectmaicMileMst = (KznTlProjectmaicMileMst) httpSession.getAttribute("mile");
    	
    		newKznTlProjectmaicMileMst=(KznTlProjectmaicMileMst)UIUtils.setBeanProperties((Object)newKznTlProjectmaicMileMst,request);
    		  List<KznTlProjectmaicMileDtl> lstKznTlProjectmaicMileDtl = null;
              JSONArray jsonMilestoneArray = null;
   		  if(UIUtils.isValidKeyId(mileStone)){ //master json
   			   if( mileStone != null && ! mileStone.isEmpty())
   	    	   {
   					jsonMilestoneArray = JSONArray.fromString(mileStone);
   					lstKznTlProjectmaicMileDtl=(List<KznTlProjectmaicMileDtl>)UIUtils.convertJSONArrToList(newKznTlProjectmaicMileDtl, jsonMilestoneArray);
   	    	   }
   			   if(lstKznTlProjectmaicMileDtl!=null)
   			   { 
   				    newKznTlProjectmaicMileMst.setMilestonedetail(lstKznTlProjectmaicMileDtl);
   					//CommonMessage.debugMsg("lstEntTlAssessmentmst.size(): "+lstKznTlProjectmaicMileDtl.size());
   			   }
   		  }
    		
    		newKznTlProjectmaicMileMst.setKmmmCreatedby(user.getUsrm_keyid());
    		String saveMsg ;
				if( newKznTlProjectmaicMileMst.getKmmmKeyid() == null ){	
					existKznTlProjectmaicMileMst = projectService.create(newKznTlProjectmaicMileMst,existKznTlProjectmaicMileMst);
					 saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
				}	
				else{
					existKznTlProjectmaicMileMst = projectService.create(newKznTlProjectmaicMileMst,existKznTlProjectmaicMileMst);
					saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
				}
			KznTlProjectcreationmst	newKznTlProjectcreationmst = projectService.getRecall(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid());
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
//			maicStage=	projectService.getMaicStage(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid(),"M");				
			successData.put("measureStage", newKznTlProjectcreationmst.getKzpmMeasurestage());
//			maicStage=	projectService.getMaicStage(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid(),"A");
			successData.put("analyseStage", newKznTlProjectcreationmst.getKzpmAnalysestage());
//			maicStage=	projectService.getMaicStage(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid(),"I");
			successData.put("improveStage", newKznTlProjectcreationmst.getKzpmImprovestage());
//			maicStage=	projectService.getMaicStage(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid(),"C");
			successData.put("controlStage", newKznTlProjectcreationmst.getKzpmControlstage());
			successData.put("msg", saveMsg);			
			//CommonMessage.debugMsg("existKznTlProjectmaicMileMst.getKmmmKeyid(): "+existKznTlProjectmaicMileMst.getKmmmKeyid());
			successData.put("keyid", newKznTlProjectmaicMileMst.getKmmmKeyid());
			returnData.put("successData",successData);
			returnData.put("kznTlProjectmaicMileMst", JSONArray.fromObject(newKznTlProjectmaicMileMst));
			returnData.put("formClear",false);
			
			out.print(returnData.toString());
    	}catch (ValidationExceptions e) {
			//CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "MileStoneValidation");
			out.print(errMessage.toString());
    	}
    	catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			outt.print(err.toString());
		}
    	}	
		
	}
	private void deleteProjectcreation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	try{
    	String hdnkkeyid = request.getParameter("hdnkkeyid");
    	KznTlProjectcreationmst existKznTlProjectcreationmst = (KznTlProjectcreationmst)httpSession.getAttribute("newSession");
    	KznTlProjectcreationmst newKznTlProjectcreationmst = new KznTlProjectcreationmst ();
    	newKznTlProjectcreationmst=(KznTlProjectcreationmst)UIUtils.setBeanProperties((Object)newKznTlProjectcreationmst,request);
    	newKznTlProjectcreationmst.setKkeyid(hdnkkeyid);
    	existKznTlProjectcreationmst = projectService.delete(newKznTlProjectcreationmst);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}catch (ValidationExceptions e) {
		//CommonMessage.debugMsg("ValidationExceptions");
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
		out.print(errMessage.toString());
	}catch(BusinessApplicationExceptions e){
		//CommonMessage.debugMsg("BusinessApplicationExceptions");
		//CommonMessage.debugMsg("Error Msg:" + e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "ProjectCreationValidation");
		out.print(errMessage.toString());
		//CommonMessage.debugMsg("Error Msg:" + e.toString());
	}
	catch(Exception e)
	{
		//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not deleted");
		out.print(err.toString());
	}
		
	}
	private void deletedmcProjectcreation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	try{
    	String hdnkkeyid = request.getParameter("hdnkkeyid");
    	String hdnDelKeyid = request.getParameter("hdnDelKeyid");
    	KznTlDmcfipcreationmst existKznTlDmcfipcreationmst = (KznTlDmcfipcreationmst)httpSession.getAttribute("newSession");
    	KznTlDmcfipcreationmst newKznTlDmcfipcreationmst = new KznTlDmcfipcreationmst();
    	newKznTlDmcfipcreationmst=(KznTlDmcfipcreationmst)UIUtils.setBeanProperties((Object)newKznTlDmcfipcreationmst,request);
    	
    	GenTlDmcfipworkflow existGenTlDmcfipworkflow = (GenTlDmcfipworkflow)httpSession.getAttribute("newSession");
    	GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
    	newGenTlDmcfipworkflow=(GenTlDmcfipworkflow)UIUtils.setBeanProperties((Object)newGenTlDmcfipworkflow,request);
    	newGenTlDmcfipworkflow.setDelKeyid(hdnDelKeyid);
    	newKznTlDmcfipcreationmst.setKkeyid(hdnkkeyid);
    	existKznTlDmcfipcreationmst = projectService.dmcdelete(newKznTlDmcfipcreationmst,newGenTlDmcfipworkflow);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",false);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}catch (ValidationExceptions e) {
		//CommonMessage.debugMsg("ValidationExceptions");
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
		out.print(errMessage.toString());
	}catch(BusinessApplicationExceptions e){
		//CommonMessage.debugMsg("BusinessApplicationExceptions");
		//CommonMessage.debugMsg("Error Msg:" + e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "ProjectCreationValidation");
		out.print(errMessage.toString());
		//CommonMessage.debugMsg("Error Msg:" + e.toString());
	}
	catch(Exception e)
	{
		//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not deleted");
		out.print(err.toString());
	}
		
	}
	private void saveProjectcreation(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String Type =  request.getParameter("type");
    	CommonMessage.debugMsg(" TYpe type "+Type);
    	if( httpSession != null && user != null)
    	{	
    		KznTlProjectcreationmst newKznTlProjectcreationmst = new KznTlProjectcreationmst();
    		KznTlProjectcreationmst existKznTlProjectcreationmst = new KznTlProjectcreationmst();//(KznTlProjectcreationmst) httpSession.getAttribute("project");
    		List<KznTlProjectResourceLink> KznTlProjectResourceLinkList=new ArrayList<KznTlProjectResourceLink>();
    	try{
    		String chkListValid = "N";
    		newKznTlProjectcreationmst=(KznTlProjectcreationmst)UIUtils.setBeanProperties((Object)newKznTlProjectcreationmst,request);
    		
    		
    		String saveMsg ;
			if( newKznTlProjectcreationmst.getKzpmKeyid() == null ){
				newKznTlProjectcreationmst.setKzpmCreatedby(user.getUsrm_ccno());
				String curDateTime =CommonFunctions.pg_dateTimeNow();
				KznTlProjectResourceLink kznTlProjectResourceLink=new KznTlProjectResourceLink();
				kznTlProjectResourceLink.setKprlTempfield1("-");
				kznTlProjectResourceLink.setKprlTempfield2("-");
				kznTlProjectResourceLink.setKprlTempfield3("-");
				kznTlProjectResourceLink.setKprlTempfield4("-");
				kznTlProjectResourceLink.setKprlTempfield5("-");
				kznTlProjectResourceLink.setKprlHrsestimate("1");
				kznTlProjectResourceLink.setKprlRoleKeyid("Project Leader");
				kznTlProjectResourceLink.setKprlLeadMemb("L");
				kznTlProjectResourceLink.setKprlEmpmKeyid(newKznTlProjectcreationmst.getKzpmCreatedby());				
				kznTlProjectResourceLink.setKprlActive("Y");
				kznTlProjectResourceLink.setKprlCreatedon(curDateTime);
				kznTlProjectResourceLink.setKprlCreatedby(user.getUsrm_keyid());
				kznTlProjectResourceLink.setKprlModifiedon(curDateTime);				
				KznTlProjectResourceLinkList.add(kznTlProjectResourceLink);
				
				String dmtLeader=projectService.getDMTLeader(newKznTlProjectcreationmst);
				CommonMessage.debugMsg("dmtLeader : "+dmtLeader);
				if(UIUtils.isValidKeyId(dmtLeader)){
					kznTlProjectResourceLink=new KznTlProjectResourceLink();
					kznTlProjectResourceLink.setKprlTempfield1("-");
					kznTlProjectResourceLink.setKprlTempfield2("-");
					kznTlProjectResourceLink.setKprlTempfield3("-");
					kznTlProjectResourceLink.setKprlTempfield4("-");
					kznTlProjectResourceLink.setKprlTempfield5("-");
					kznTlProjectResourceLink.setKprlHrsestimate("1");					
					kznTlProjectResourceLink.setKprlRoleKeyid("DMT Leader");
					kznTlProjectResourceLink.setKprlLeadMemb("M");	
					kznTlProjectResourceLink.setKprlEmpmKeyid(dmtLeader);
					kznTlProjectResourceLink.setKprlActive("Y");
					kznTlProjectResourceLink.setKprlCreatedon(curDateTime);
					kznTlProjectResourceLink.setKprlCreatedby(user.getUsrm_keyid());
					kznTlProjectResourceLink.setKprlModifiedon(curDateTime);				
					KznTlProjectResourceLinkList.add(kznTlProjectResourceLink);
				}
				newKznTlProjectcreationmst.setProjectResourceList(KznTlProjectResourceLinkList);				
				
				GenTlWorkflowInfo genTlWorkflowInfo =new GenTlWorkflowInfo();
				String isTangible=newKznTlProjectcreationmst.getKzpmIsintangible();
				String benAmt=newKznTlProjectcreationmst.getKzpmBenefits();
				CommonMessage.debugMsg(benAmt +" Benefit amount");
				if(benAmt==null|| benAmt.equals(" ")||benAmt.equals("null")){
					benAmt="0";
				}
				
				
				int amnt=Integer.parseInt(benAmt);
				CommonMessage.debugMsg(" Hi by amntamntamnt" +amnt);

				if(amnt>=500000 && amnt<10000000){
					CommonMessage.debugMsg(" IN SIDE iF Hi by amntamntamnt" +amnt);

					genTlWorkflowInfo.setWrinRefType("PRODEGE5L");
				}
				else if(amnt>=10000000){
					CommonMessage.debugMsg(" IN SIDE ELSE iF Hi by amntamntamnt" +amnt);

					genTlWorkflowInfo.setWrinRefType("PRODEGE1C");

				}
				else {
				genTlWorkflowInfo.setWrinRefType("PRODE");
				}
				
				genTlWorkflowInfo.setWrinEmployeeId(user.getUsrm_ccno());
				genTlWorkflowInfo.setWrinDate(curDateTime);
				genTlWorkflowInfo.setWrinRemarks("-");
				genTlWorkflowInfo.setWrinRoleId("AROL0059");
				genTlWorkflowInfo.setWrinWrkdKeyid("{}");
				genTlWorkflowInfo.setWrinWrmlKeyid("{}");
				genTlWorkflowInfo.setWrinTempfield2("-");
				genTlWorkflowInfo.setWrinTempfield3("-");
				genTlWorkflowInfo.setWrinTempfield4("-");
				genTlWorkflowInfo.setWrinTempfield5("-");
				genTlWorkflowInfo.setWrinStatus("C");
				genTlWorkflowInfo.setWrinCreatedon(curDateTime);
				genTlWorkflowInfo.setWrinCreatedby(user.getUsrm_ccno());
				genTlWorkflowInfo.setWrinModifiedon(curDateTime);
				
				newKznTlProjectcreationmst.setWorkFlowApp(genTlWorkflowInfo);
				 existKznTlProjectcreationmst = projectService.create(newKznTlProjectcreationmst,existKznTlProjectcreationmst);
				 saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
			}	
			else{
				existKznTlProjectcreationmst = (KznTlProjectcreationmst) httpSession.getAttribute("project");
				String defineStage = request.getParameter("isDefineStage");
				String closureStage = request.getParameter("hdnIsClosure");
				if( defineStage == null )
					defineStage =  request.getParameter("hdnIsDefineStage");
				chkListValid =  request.getParameter("chkListValid");
				String isFinance = request.getParameter("hdnEnableVerfyAmnt");
				String switchMode = "";
				if("Y".equalsIgnoreCase(defineStage) && (  ! UIUtils.isValidKeyId(chkListValid)  ||  "Y".equalsIgnoreCase(chkListValid)  )){

					newKznTlProjectcreationmst.setMode("define");
					//newKznTlProjectcreationmst.setMode("");
				}	
				else if("Y".equalsIgnoreCase(isFinance))
				{
					newKznTlProjectcreationmst.setMode("finance");
					// String InsertPbuhead=projectService.InsertPbuHead(newKznTlProjectcreationmst.getKzpmKeyid(),newKznTlProjectcreationmst.getMode(),newKznTlProjectcreationmst.getKzpmFlid());
					
				}else if("y".equals(closureStage)) {
					switchMode = newKznTlProjectcreationmst.getMode();
					newKznTlProjectcreationmst.setMode("closure");
				}
				else{	
					
					newKznTlProjectcreationmst.setMode(request.getParameter("mode"));
					if("N".equalsIgnoreCase(chkListValid) && ! "modifycreate".equals(newKznTlProjectcreationmst.getMode()) )
						newKznTlProjectcreationmst.setMode("modify");
				}	
				
				existKznTlProjectcreationmst = projectService.update(newKznTlProjectcreationmst,existKznTlProjectcreationmst);
				if( "define".equals(newKznTlProjectcreationmst.getMode()) )
				{ // String InsertPbuhead=projectService.InsertPbuHead(newKznTlProjectcreationmst.getKzpmKeyid(),newKznTlProjectcreationmst.getMode(),newKznTlProjectcreationmst.getKzpmFlid());
					saveMsg = "Project has Submitted for Define Stage Approval";
				}else if("y".equals(closureStage)) {
					String InsertPbuheadClosure=projectService.InsertPbuHeadClosure(newKznTlProjectcreationmst.getKzpmKeyid(),"closure",newKznTlProjectcreationmst.getKzpmFlid());
					newKznTlProjectcreationmst.setMode(switchMode);
					saveMsg = "Project has Submitted for Closure Stage Approval";
				}
				else		
					saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
			}
			
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("Type", Type);
			if( "workflow".equals(Type)){
				JSONObject record = new JSONObject();
				record.put("gridId", request.getParameter("gridId"));
				record.put("rowId", request.getParameter("rowId"));
				successData.put("record",record);
				returnData.put("displyMsg",false);
			}
			
			successData.put("mode", newKznTlProjectcreationmst.getMode());
			successData.put("msg", saveMsg);
			successData.put("kzpmKeyid", existKznTlProjectcreationmst.getKzpmKeyid());
			successData.put("checkList", chkListValid);
			returnData.put("successData",successData);
			returnData.put("formClear",false);
			out.print(returnData.toString());
    	}catch (ValidationExceptions e) {
			//CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
			out.print(errMessage.toString());
    	}
    	catch (BusinessApplicationExceptions e) {
    		//CommonMessage.debugMsg("BusinessApplicationExceptions dfg" + e.toString());
    		e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ProjectCreationValidation");
			//CommonMessage.debugMsg(" e " + errMessage );
			out.print(errMessage.toString());
    	}
    	catch(Exception e)
		{
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			outt.print(err.toString());
		}
    	}	
		
	}
	private void savedmcProjectcreation(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String Type =  request.getParameter("type");
    	String curDateTime =CommonFunctions.dateTimeNow();
    	String formClear ="N";
    	if( httpSession != null && user != null)
    	{	
    		GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
    		GenTlDmcfipworkflow existGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
    		KznTlDmcfipcreationmst newKznTlDmcfipcreationmst = new KznTlDmcfipcreationmst();
    		KznTlDmcfipcreationmst existKznTlDmcfipcreationmst = new KznTlDmcfipcreationmst();//(KznTlProjectcreationmst) httpSession.getAttribute("project");
    		List<KznTlProjectResourceLink> KznTlProjectResourceLinkList=new ArrayList<KznTlProjectResourceLink>();
    	try{
    		String chkListValid = "N";
    		newKznTlDmcfipcreationmst=(KznTlDmcfipcreationmst)UIUtils.setBeanProperties((Object)newKznTlDmcfipcreationmst,request);
    		newGenTlDmcfipworkflow=(GenTlDmcfipworkflow)UIUtils.setBeanProperties((Object)newGenTlDmcfipworkflow,request);
    		
    		
    		String saveMsg ;
			if( newKznTlDmcfipcreationmst.getDmcmKeyid() == null ){
				newKznTlDmcfipcreationmst.setDmcmCreatedby(user.getUsrm_ccno());
				
				KznTlProjectResourceLink kznTlProjectResourceLink=new KznTlProjectResourceLink();
				kznTlProjectResourceLink.setKprlTempfield1("-");
				kznTlProjectResourceLink.setKprlTempfield2("-");
				kznTlProjectResourceLink.setKprlTempfield3("-");
				kznTlProjectResourceLink.setKprlTempfield4("-");
				kznTlProjectResourceLink.setKprlTempfield5("-");
				kznTlProjectResourceLink.setKprlHrsestimate("1");
				kznTlProjectResourceLink.setKprlRoleKeyid("Project Leader");
				kznTlProjectResourceLink.setKprlLeadMemb("L");
				kznTlProjectResourceLink.setKprlEmpmKeyid(newGenTlDmcfipworkflow.getDfiwProjectleader());				
				kznTlProjectResourceLink.setKprlActive("Y");
				kznTlProjectResourceLink.setKprlCreatedon(curDateTime);
				kznTlProjectResourceLink.setKprlCreatedby(user.getUsrm_keyid());
				kznTlProjectResourceLink.setKprlModifiedon(curDateTime);				
				KznTlProjectResourceLinkList.add(kznTlProjectResourceLink);
	    		
				newGenTlDmcfipworkflow.setDfiwTempfield1("-");
	    		newGenTlDmcfipworkflow.setDfiwTempfield2("-");
	    		newGenTlDmcfipworkflow.setDfiwTempfield3("-");
	    		newGenTlDmcfipworkflow.setDfiwTempfield4("-");
	    		newGenTlDmcfipworkflow.setDfiwActive("Y");
	    		newGenTlDmcfipworkflow.setDfiwCreatedon(curDateTime);
	    		newGenTlDmcfipworkflow.setDfiwCreatedby(user.getUsrm_keyid());
	    		newGenTlDmcfipworkflow.setDfiwModifiedon(curDateTime);
	    		newGenTlDmcfipworkflow.setDfiwModifiedby(user.getUsrm_keyid());
				
				String dmtLeader=projectService.getdmcDMTLeader(newKznTlDmcfipcreationmst);
				if(UIUtils.isValidKeyId(dmtLeader)){
					kznTlProjectResourceLink=new KznTlProjectResourceLink();
					kznTlProjectResourceLink.setKprlTempfield1("-");
					kznTlProjectResourceLink.setKprlTempfield2("-");
					kznTlProjectResourceLink.setKprlTempfield3("-");
					kznTlProjectResourceLink.setKprlTempfield4("-");
					kznTlProjectResourceLink.setKprlTempfield5("-");
					kznTlProjectResourceLink.setKprlHrsestimate("1");					
					kznTlProjectResourceLink.setKprlRoleKeyid("DMT Leader");
					kznTlProjectResourceLink.setKprlLeadMemb("M");	
					kznTlProjectResourceLink.setKprlEmpmKeyid(dmtLeader);
					kznTlProjectResourceLink.setKprlActive("Y");
					kznTlProjectResourceLink.setKprlCreatedon(curDateTime);
					kznTlProjectResourceLink.setKprlCreatedby(user.getUsrm_keyid());
					kznTlProjectResourceLink.setKprlModifiedon(curDateTime);				
					KznTlProjectResourceLinkList.add(kznTlProjectResourceLink);
				}
				newKznTlDmcfipcreationmst.setProjectResourceList(KznTlProjectResourceLinkList);				
				
				
				GenTlWorkflowInfo genTlWorkflowInfo =new GenTlWorkflowInfo();				
				genTlWorkflowInfo.setWrinRefType("PRODE");
				genTlWorkflowInfo.setWrinEmployeeId(user.getUsrm_ccno());
				genTlWorkflowInfo.setWrinDate(curDateTime);
				genTlWorkflowInfo.setWrinRemarks("-");
				genTlWorkflowInfo.setWrinRoleId("AROL0059");
				genTlWorkflowInfo.setWrinWrkdKeyid("{}");
				genTlWorkflowInfo.setWrinWrmlKeyid("{}");
				genTlWorkflowInfo.setWrinTempfield2("-");
				genTlWorkflowInfo.setWrinTempfield3("-");
				genTlWorkflowInfo.setWrinTempfield4("-");
				genTlWorkflowInfo.setWrinTempfield5("-");
				genTlWorkflowInfo.setWrinStatus("C");
				genTlWorkflowInfo.setWrinCreatedon(curDateTime);
				genTlWorkflowInfo.setWrinCreatedby(user.getUsrm_ccno());
				genTlWorkflowInfo.setWrinModifiedon(curDateTime);
				
				newKznTlDmcfipcreationmst.setWorkFlowApp(genTlWorkflowInfo);
				CommonMessage.debugMsg("Before insert in servlet");
				 existKznTlDmcfipcreationmst = projectService.createdmc(newKznTlDmcfipcreationmst,existKznTlDmcfipcreationmst,newGenTlDmcfipworkflow);
				 saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
				 formClear ="Y";
			}	
			else{
				existKznTlDmcfipcreationmst = (KznTlDmcfipcreationmst) httpSession.getAttribute("project");
				String defineStage = request.getParameter("isDefineStage");
				if( defineStage == null )
					defineStage =  request.getParameter("hdnIsDefineStage");
				chkListValid =  request.getParameter("chkListValid");
				String isFinance = request.getParameter("hdnEnableVerfyAmnt");

				if("Y".equalsIgnoreCase(defineStage) && (  ! UIUtils.isValidKeyId(chkListValid)  ||  "Y".equalsIgnoreCase(chkListValid)  )){

					newKznTlDmcfipcreationmst.setMode("define");
					//newKznTlProjectcreationmst.setMode("");
				}	
				else if("Y".equalsIgnoreCase(isFinance))
				{
					newKznTlDmcfipcreationmst.setMode("finance");
					
				}
				else{	
					
					newKznTlDmcfipcreationmst.setMode(request.getParameter("mode"));
					if("N".equalsIgnoreCase(chkListValid) && ! "modifycreate".equals(newKznTlDmcfipcreationmst.getMode()) )
						newKznTlDmcfipcreationmst.setMode("modify");
				}
				
	    		newGenTlDmcfipworkflow.setDfiwModifiedon(curDateTime);
	    		newGenTlDmcfipworkflow.setDfiwModifiedby(user.getUsrm_keyid());
				CommonMessage.debugMsg("dfiwkeyid"+newGenTlDmcfipworkflow.getDfiwKeyid());
				CommonMessage.debugMsg("dfiwfipno"+newGenTlDmcfipworkflow.getDfiwFipno());
				existKznTlDmcfipcreationmst = projectService.updatedmc(newKznTlDmcfipcreationmst,existKznTlDmcfipcreationmst,newGenTlDmcfipworkflow,existGenTlDmcfipworkflow);
				if( "define".equals(newKznTlDmcfipcreationmst.getMode()) )
					
					saveMsg = "Project has Submitted for Define Stage Approval";
				else		
					saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update");
			}
			
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("Type", Type);
			if( "workflow".equals(Type)){
				JSONObject record = new JSONObject();
				record.put("gridId", request.getParameter("gridId"));
				record.put("rowId", request.getParameter("rowId"));
				successData.put("record",record);
				returnData.put("displyMsg",false);
			}
			
			successData.put("mode", newKznTlDmcfipcreationmst.getMode());
			successData.put("msg", saveMsg);
			successData.put("dmcmKeyid", existKznTlDmcfipcreationmst.getDmcmKeyid());
			successData.put("wrinkeyid", existGenTlDmcfipworkflow.getDfiwKeyid());
			successData.put("checkList", chkListValid);
			returnData.put("successData",successData);
			if("Y".equals(formClear))
			  returnData.put("formClear",true);
			else 
			  returnData.put("formClear",false);
			out.print(returnData.toString());
    	}catch (ValidationExceptions e) {
			//CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
			out.print(errMessage.toString());
    	}
    	catch (BusinessApplicationExceptions e) {
    		//CommonMessage.debugMsg("BusinessApplicationExceptions dfg" + e.toString());
    		e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ProjectCreationValidation");
			//CommonMessage.debugMsg(" e " + errMessage );
			out.print(errMessage.toString());
    	}
    	catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			outt.print(err.toString());
		}
    	}	
		
	}
	/*private void savedmcworkflowcreation(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String Type =  request.getParameter("Type");
  	
    		GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
    		GenTlDmcfipworkflow existGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
    		
    		String Dfiwfipno =  request.getParameter("Dfiwfipno");
    		newGenTlDmcfipworkflow.setDfiwFipno(Dfiwfipno);
    		newGenTlDmcfipworkflow.setDfiwTempfield1("-");
    		newGenTlDmcfipworkflow.setDfiwTempfield2("-");
    		newGenTlDmcfipworkflow.setDfiwTempfield3("-");
    		newGenTlDmcfipworkflow.setDfiwTempfield4("-");
    		newGenTlDmcfipworkflow.setDfiwActive("Y");
    		
    		existGenTlDmcfipworkflow = projectService.createdmcworkflow(newGenTlDmcfipworkflow,existGenTlDmcfipworkflow);
	}*/
	private void saveKpiLinkcreation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	if( httpSession != null && user != null)
    	{	
    		KznTlProjectKpiLink newKznTlProjectKpiLink = new KznTlProjectKpiLink();
    		KznTlProjectKpiLink existKznTlProjectKpiLink = new KznTlProjectKpiLink();
    	try{
    		 String projectKpi =  request.getParameter("projectKpi");
    		 String hdnKzpmKeyid =  request.getParameter("hdnKzpmKeyid");
    		 
    		 CommonMessage.debugMsg("projectKpi: "+projectKpi);
    		 List<KznTlProjectKpiLink> projectKpilink =null;
    		 JSONArray jsonKpi = null;
   		  if(UIUtils.isValidKeyId(projectKpi)){ //master json
   				if( projectKpi != null && ! projectKpi.isEmpty())
   	    		{
   					jsonKpi = JSONArray.fromString(projectKpi);
   					projectKpilink=(List<KznTlProjectKpiLink>)UIUtils.convertJSONArrToList(newKznTlProjectKpiLink, jsonKpi);
   	    		}
   			   if(projectKpilink!=null)
   			   {   				
   				   //newKznTlProjectKpiLink.setProjectKpi(projectKpilink);
   				   
   				   for(int i=0;i<=projectKpilink.size()-1;i++){
   					   //CommonMessage.debugMsg("getIsDelete(): "+projectKpilink.get(i).getIsDelete());
   					   //CommonMessage.debugMsg("getKpklKinkKeyid(): "+projectKpilink.get(i).getKpklKinkKeyid());
   					   //CommonMessage.debugMsg("getKpklKzpmKeyid(): "+projectKpilink.get(i).getKpklKzpmKeyid());
   					   //projectKpilink.get(i).setKpklKzpmKeyid(hdnKzpmKeyid);
   					   projectKpilink.get(i).setKpklCreatedby(user.getUsrm_keyid());
   				   }
   			   }
   		  }
   		  
   		  existKznTlProjectKpiLink = projectService.createKpi(projectKpilink);
   		  String saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", saveMsg);
			returnData.put("successData",successData);
			returnData.put("formClear",true);
			out.print(returnData.toString());
    	}catch (ValidationExceptions e) {
			//CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
			out.print(errMessage.toString());
    	}
    	catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			outt.print(err.toString());
		}
    	}	
		
	}
	/**/

	private void SaveChangelead(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub--responsevalue--targetdate--dtlkeyid
		String Projectkeyid=request.getParameter("Projectkeyid");
		String oldemployeeid=request.getParameter("oldemployee");	
		String responsevalue=request.getParameter("responsevalue");	
		//String flid=request.getParameter("flid");
		//String mstkeyid=request.getParameter("mstkeyid");
	    CommonMessage.debugMsg("Projectkeyid"+Projectkeyid);
		CommonMessage.debugMsg("oldemployeeid"+oldemployeeid);
		CommonMessage.debugMsg("responsevalue"+responsevalue);
	
		KznTlProjectcreationmst kznTlProjectcreationmst = new KznTlProjectcreationmst();
		kznTlProjectcreationmst.setKzpmKeyid(Projectkeyid);
		kznTlProjectcreationmst.setKzpmProjectchamp(oldemployeeid);

		

		String resultsupdate = projectService.singleResponsiblty(Projectkeyid,responsevalue,oldemployeeid);
	
		
		CommonMessage.debugMsg("resultsupdate::"+resultsupdate);
	
	}
	private void SaveChangeleader(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub--responsevalue--targetdate--dtlkeyid
		String Projectkeyid=request.getParameter("Projectkeyid");
		String oldemployeeid=request.getParameter("oldemployee");	
		String responsevalue=request.getParameter("responsevalue");	
		//String flid=request.getParameter("flid");
		//String mstkeyid=request.getParameter("mstkeyid");
	    CommonMessage.debugMsg("Projectkeyid"+Projectkeyid);
		CommonMessage.debugMsg("oldemployeeid"+oldemployeeid);
		CommonMessage.debugMsg("responsevalue"+responsevalue);
	
		KznTlProjectcreationmst kznTlProjectcreationmst = new KznTlProjectcreationmst();
		kznTlProjectcreationmst.setKzpmKeyid(Projectkeyid);
		kznTlProjectcreationmst.setKzpmProjectchamp(oldemployeeid);

		

		String resultsupdate = projectService.singleleadResponsiblty(Projectkeyid,responsevalue,oldemployeeid);
		CommonMessage.debugMsg("resultsupdate::"+resultsupdate);
	
	}
	
	
	/**/
	private void saveDMAICVerificationStatus(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String maicStage="";
    	if( httpSession != null && user != null)
    	{	
    		KznTlProjectdmaicstatus newKznTlProjectdmaicstatus = new KznTlProjectdmaicstatus();
    		List<KznTlProjectdmaicstatus> existKznTlProjectdmaicstatusLst = new ArrayList<KznTlProjectdmaicstatus>();
	    	try{
	    		 String projectDmaic =  request.getParameter("projectDmaic");
	    		 String projectDmaiceDel = request.getParameter("projectDmaiceDel");
	    		 String hdnKzpmKeyid=request.getParameter("hdnKzpmKeyid");
	    		 //CommonMessage.debugMsg("projectDmaic: "+projectDmaic);
	    		 //CommonMessage.debugMsg("hdnKzpmKeyid: "+hdnKzpmKeyid);
	    		 List<KznTlProjectdmaicstatus> kznTlProjectdmaicstatusList =null;
	    		 JSONArray jsonKpi = null;
		   		  if(UIUtils.isValidKeyId(projectDmaic)){ //master json
		   				if( projectDmaic != null && ! projectDmaic.isEmpty())
		   	    		{
		   					jsonKpi = JSONArray.fromString(projectDmaic);
		   					kznTlProjectdmaicstatusList=(List<KznTlProjectdmaicstatus>)UIUtils.convertJSONArrToList(newKznTlProjectdmaicstatus, jsonKpi);
		   	    		}
		   			   if(kznTlProjectdmaicstatusList!=null)
		   			   {  
		   				   //CommonMessage.debugMsg("kznTlProjectdmaicstatusList.size(): "+user.getUsrm_keyid());
		   				   for(int i=0;i<kznTlProjectdmaicstatusList.size();i++){   					   
		   					   kznTlProjectdmaicstatusList.get(i).setKpdsCreatedby(user.getUsrm_ccno());
		   					   kznTlProjectdmaicstatusList.get(i).setKpdsKzpmKeyid(hdnKzpmKeyid);		   					
		   				   }
		   			   }
		   		  }		   		
				
		   		if(UIUtils.isValidKeyId(projectDmaiceDel))
	    		{
		   			projectDmaiceDel=projectDmaiceDel.replace("[","").replace("]", "").replace("\"","'");
	    			//CommonMessage.debugMsg("projectDmaiceDel:"+projectDmaiceDel);
	    			//String[] dtlIds=nomdtlsdel.split(",");	    			
    				if (kznTlProjectdmaicstatusList!=null){
    				}	 
    				else{
    					kznTlProjectdmaicstatusList=new ArrayList<KznTlProjectdmaicstatus>();
    				}
    				newKznTlProjectdmaicstatus.setKpdsKeyid(projectDmaiceDel);
    				newKznTlProjectdmaicstatus.setIsDelete("Y");
    				newKznTlProjectdmaicstatus.setKpdsKzpmKeyid(hdnKzpmKeyid);
    				kznTlProjectdmaicstatusList.add(newKznTlProjectdmaicstatus);
				}
	   			existKznTlProjectdmaicstatusLst = projectService.create(kznTlProjectdmaicstatusList,existKznTlProjectdmaicstatusLst);
	   							
				String saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
				
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("Type", "dmaicStatus");
				maicStage=	projectService.getMaicStage(hdnKzpmKeyid,"M");				
				successData.put("measureStage", maicStage);
				maicStage=	projectService.getMaicStage(hdnKzpmKeyid,"A");
				successData.put("analyseStage", maicStage);
				maicStage=	projectService.getMaicStage(hdnKzpmKeyid,"I");
				successData.put("improveStage", maicStage);
				maicStage=	projectService.getMaicStage(hdnKzpmKeyid,"C");
				successData.put("controlStage", maicStage);				
				returnData.put("successData",successData);
				//returnData.put("formClear",true);
				out.print(returnData.toString());
	    	}catch (ValidationExceptions e) {
				//CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectCreationValidation");
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
				//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				outt.print(err.toString());
			}
		}	
	
	}
	
	private void deleteProjectResource(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	if( httpSession != null && user != null)
    	{	
    	try
		{ 
       		
    		KznTlProjectResourceLink existKznTlProjectResourceLink= (KznTlProjectResourceLink)httpSession.getAttribute("newSession"); 
    		KznTlProjectResourceLink newKznTlProjectResourceLink = new KznTlProjectResourceLink();//mas
    		newKznTlProjectResourceLink=(KznTlProjectResourceLink)UIUtils.setBeanProperties((Object)newKznTlProjectResourceLink,request);
    		   		  
  			JSONObject successData=new JSONObject();
			JSONObject trainingAttEffSuccessMsg=new JSONObject();
			String savemsg;		
			existKznTlProjectResourceLink=projectService.delete(newKznTlProjectResourceLink, existKznTlProjectResourceLink);
			 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete");
			 successData.put("msg", savemsg);
			 trainingAttEffSuccessMsg.put("successData", successData);
    		 out.print(trainingAttEffSuccessMsg.toString());
  		  			
			}catch (ValidationExceptions e)
			{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"TrainingAtten");
	                 out.print(errMessage.toString());
    	  }
		catch(Exception e){
				//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not deleted");
				out.print(err.toString());
		}
	}
    		
    }
	
	private void deleteDmaicStatus(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		String deletemsg;
		PrintWriter out= response.getWriter();
		KznTlProjectdmaicstatus  newKznTlProjectdmaicstatus =new KznTlProjectdmaicstatus();	
		newKznTlProjectdmaicstatus=(KznTlProjectdmaicstatus)UIUtils.setBeanProperties((Object) newKznTlProjectdmaicstatus ,request);	
		String projectDmaiceDel = request.getParameter("projectDmaiceDel");
		try{
			if(UIUtils.isValidKeyId(projectDmaiceDel)){
				//projectDmaiceDel=projectDmaiceDel.replace("", "")
				projectDmaiceDel=projectDmaiceDel.replace("[","").replace("]", "").replace("\"","'");
				newKznTlProjectdmaicstatus.setKpdsKeyid(projectDmaiceDel);
				newKznTlProjectdmaicstatus =projectService.delete(newKznTlProjectdmaicstatus);
				deletemsg="Data Deleted Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg",deletemsg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);		
				////CommonMessage.debugMsg(returnData.toString());		
				out.print(returnData.toString());
			}
		}catch (ValidationExceptions e) 
		{
			//CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "EmpNominationCreation");
			out.print(errMessage.toString());							    	
		}
							
		catch(BusinessApplicationExceptions e)
		{
			//CommonMessage.debugMsg("BusinessApplicationExceptions");
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "EmpNominationCreation");
			out.print(errMessage.toString());
			//CommonMessage.debugMsg(" e " + errMessage );
		}
		catch(Exception e)
		{   
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}
	
	private void saveProjectResource(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream outt = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String resourcedtls = request.getParameter("resourcedtls");
		String resourcedtlsdel = request.getParameter("resourcedtlsdel");
		String hdnKzpmKeyid = request.getParameter("hdnKzpmKeyid");
		
		//CommonMessage.debugMsg("resourcedtls:"+resourcedtls);
		//CommonMessage.debugMsg("resourcedtlsdel:"+resourcedtlsdel);
		//CommonMessage.debugMsg("hdnKzpmKeyid:"+hdnKzpmKeyid);
		
		
    	if( httpSession != null && user != null)
    	{	
    		KznTlProjectResourceLink newKznTlProjectResourceLink = new KznTlProjectResourceLink();
    		KznTlProjectResourceLink existKznTlProjectResourceLink = (KznTlProjectResourceLink) httpSession.getAttribute("resource");
    	try{
    		newKznTlProjectResourceLink=(KznTlProjectResourceLink)UIUtils.setBeanProperties((Object)newKznTlProjectResourceLink,request);
    		EntTlNominationmst existEntTlNominationmst = (EntTlNominationmst)httpSession.getAttribute("newEntTlNominationmst");
    		newKznTlProjectResourceLink.setKprlCreatedby(user.getUsrm_ccno());
			List<KznTlProjectResourceLink> kznTlProjectResourceLinkList = null;
    		JSONArray kznTlProjectResourceLinkListjson = null;	    			
    		if(UIUtils.isValidKeyId(resourcedtls) )
    		{
    			kznTlProjectResourceLinkListjson = JSONArray.fromString(resourcedtls);
    			kznTlProjectResourceLinkList=(List<KznTlProjectResourceLink>)UIUtils.convertJSONArrToList(newKznTlProjectResourceLink, kznTlProjectResourceLinkListjson);
			    
			    if(kznTlProjectResourceLinkList!= null)
			    {
			        for(int i=0 ;i<=kznTlProjectResourceLinkList.size()-1;i++){			        	
		        		//CommonMessage.debugMsg("getKprlKzpmKeyid:"+kznTlProjectResourceLinkList.get(i).getKprlKzpmKeyid());
		        		kznTlProjectResourceLinkList.get(i).setKprlCreatedby(user.getUsrm_keyid());
		        		kznTlProjectResourceLinkList.get(i).setKprlKzpmKeyid(hdnKzpmKeyid);
			        }
			    	//CommonMessage.debugMsg("kznTlProjectResourceLinkList"+kznTlProjectResourceLinkList.size());			        
			    }
    		}
    		if(UIUtils.isValidKeyId(resourcedtlsdel))
    		{
    			resourcedtlsdel=resourcedtlsdel.replace("[","").replace("]", "").replace("\"","'");
    			//CommonMessage.debugMsg("resourcedtlsdel:"+resourcedtlsdel);
    			//String[] dtlIds=nomdtlsdel.split(",");	    			
    			if (kznTlProjectResourceLinkList==null){    				
    				kznTlProjectResourceLinkList=new ArrayList<KznTlProjectResourceLink>();
    			}
				newKznTlProjectResourceLink.setKprlKeyid(resourcedtlsdel);
				newKznTlProjectResourceLink.setIsDelete("Y");
				newKznTlProjectResourceLink.setKprlKzpmKeyid(hdnKzpmKeyid);
				kznTlProjectResourceLinkList.add(newKznTlProjectResourceLink);    			
    		}
		 	String saveMsg;
		 	existKznTlProjectResourceLink = projectService.create(kznTlProjectResourceLinkList);
		 	CommonMessage.debugMsg(" 12");
		 	CommonMessage.debugMsg(" 1234"+existKznTlProjectResourceLink.getKprlEmpmKeyid());
	  		saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");   		  	
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg", saveMsg);
			successData.put("KeyId",existKznTlProjectResourceLink.getKprlEmpmKeyid());
			returnData.put("successData",successData);
			returnData.put("formClear",false);
			out.print(returnData.toString());
    	}catch (ValidationExceptions e)	{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ProjectResourceValidation");
	                 out.print(errMessage.toString());
  	  	}catch(Exception e)
		{
			//CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			//JSONObject err = new JSONObject();
			//err.put("tpmException", "Data Not Saved");
			//outt.print(err.toString());
		}
    	}			
	}
	
	private JSONObject getTableModel(List<String[]> headers) {
		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		
		String headerSql = "'SELECT ";
		String header = "'SELECT'";				
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0){
				jqGridColModel.setWidth(500);
			}
			if(i==1){
				jqGridColModel.setWidth(100);
				//jqGridColModel.setFormatter("comboFormatter");
			}			
			if (i>=6 &&  i<=10) {
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("chkBoxFormatter");
				jqGridColModel.setWidth(30);
			}			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			header = header+"''"+jqGridColModel.getName()+"'' AS "+jqGridColModel.getName()+",";
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		header = header + " FROM DUAL ";
		//CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		//CommonMessage.debugMsg("headerSql.....123..."+header);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
	
	private JSONObject getTableModelMilestone(List<String[]> headers) {
		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(150);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i>2 && i<14){
			jqGridColModel.setFormatter("txtFormatterMilestone");
			}
			if(i==0||i==1||i==2||i==13 ||i==14 ||i==15 ||i==16 )
				jqGridColModel.setHidden(true);
			if(i==3||i==7||i==12){
				jqGridColModel.setWidth(34);
				jqGridColModel.setAlign("center");
			}
			if(i==4||i==5||i==9||i==11)
				jqGridColModel.setWidth(135);
			if(i==6||i==8||i==10||i==13)
				jqGridColModel.setWidth(100);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
	
	private JSONObject getTableModelMile(List<String[]> headers) {
		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setGroupBy(true);
		jqGridTableModel.setGroupByField("Stages");

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==3||i==7){
				jqGridColModel.setAlign("center");
			}
			if(i==3){
				jqGridColModel.setWidth(250);
			}
			if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i==5 ||i==7){
				jqGridColModel.setWidth(80);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "102%%");
		return tableModel;
	}
	
	private JSONObject getTableModelKaizen(List<String[]> headers) {

		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(330);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==1){
				jqGridColModel.setAlign("center");
			}
			if(i==1){
				jqGridColModel.setWidth(80);
			}
			if(i==3){
				jqGridColModel.setFormatter("txtFormatterKaizen");
				jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("center");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "102%%");
		return tableModel;
	}
	
	private JSONObject getTableModelKaizenList(List<String[]> headers) {

		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader1 = headers.get(0);
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setEnableFilter(true);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==4){
				jqGridColModel.setWidth(400);
			}
			if(i==0||i==1||i==2||i==3)
				jqGridColModel.setHidden(true);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "250px");
		tableModel.set("tableWidth", "800px");
		tableModel.set("multiSelect", true);
		return tableModel;
	}
	
	private JSONObject getTableModelKpi(List<String[]> headers) {
		//CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(500);
		jqGridTableModel.setTableWidth(800);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i==1){jqGridColModel.setWidth(503);}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "102%%");
		return tableModel;
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
	HttpSession httpSession = request.getSession(false);
  		
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
  		if( commonFilter != null && ! createNew ){
  			FilterValues.setPaginationParams(request,commonFilter);
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
  		}	
  		else{
  			commonFilter =  new CommonFilter();
  			
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
  			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		
  		return commonFilter; 
	}
  
    
private void processBarBenefitChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
		
		String forDashboard = request.getParameter("dashboard");
		String keyid=request.getParameter("rowid");
		String rowId=request.getParameter("rownum");
		CommonFilter chartCommonFilter = new CommonFilter(); 

		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	
			
		}
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		chartCommonFilter.setRowTotal('Y');
		List<String[]> fipwavebenefitList=projectService.getFIpWaveBenefitCount(commonFilter);
		List<String[]> counts=transposeListArr1(fipwavebenefitList);
		JSONObject chartObj = null;
		if(fipwavebenefitList != null && fipwavebenefitList.size() > 0)
		{	
			

String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChartFIPBenefit(lcnname,fipwavebenefitList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}			
	}

	private JSONObject processBarChartFIPBenefit(String titlename,List<String[]> FipBenefitList,CommonFilter commonFilter) throws  Exception{

		if( FipBenefitList == null || FipBenefitList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
         
		   StringBuilder date=new StringBuilder();	
					if(commonFilter.getMonwise().equals("Y"))
					{
						date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
					}
					else
					date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		String[] header =  FipBenefitList.get(3);
		String[] month =  FipBenefitList.get(2);
		String[] data =  FipBenefitList.get(4);

         
		StringBuilder title=new StringBuilder(titlename+" -Wave No. Wise FIP Benefit - ").append(date);
		
		int rSize = FipBenefitList.size()-3;
		int cSize = header.length -2;   

		String subTitle="";
		Double [][] grData = new Double[rSize][cSize];
		for( int i =2;i <header.length;i++ ){
			for (int j = 3; j< FipBenefitList.size();j++) {
				data  =  FipBenefitList.get(j);
			   grData[j-3][i-2] = Double.parseDouble(data[i]);
			}
		
				xAxisCategory.add(month[i]);
		}
		for(int k=0;k<grData.length;k++){
			 ChartSeries timeSeries = new ChartSeries();
			 timeSeries.setData(Arrays.asList(grData[k]));
			 timeSeries.setType(ChartTypes.COLUMN);
			 timeSeries.setName(FipBenefitList.get(k+3)[1]); 
			 chartSeriesList.add(timeSeries);			
		}
		 ChartYAxis yAxis = new ChartYAxis(); 
		 yAxis.setMin(0);
		 chartYAxis.add(yAxis);
		 yAxis.getTitle().setText("Rs");
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Wave No");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}
    

private void processBarFIPWaveNoChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
		
		String forDashboard = request.getParameter("dashboard");
		String keyid=request.getParameter("rowid");
		String rowId=request.getParameter("rownum");
		CommonFilter chartCommonFilter = new CommonFilter(); 

		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("DmaicCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);	
			
		}
		
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		chartCommonFilter.setRowTotal('Y');
		 List<String[]> FipWaveCountGraph=projectService.getFIpcountGraph(chartCommonFilter, rowId);
		List<String[]> counts=transposeListArr1(FipWaveCountGraph);
		JSONObject chartObj = null;
		if(FipWaveCountGraph != null && FipWaveCountGraph.size() > 0)
		{	
			
			
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			chartObj = processBarChartFIPWave(lcnname,FipWaveCountGraph,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}			
	}

	private JSONObject processBarChartFIPWave(String titlename,List<String[]> FipWavestageList,CommonFilter commonFilter) throws  Exception{

		if( FipWavestageList == null || FipWavestageList.size() <= 1  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
         
		   StringBuilder date=new StringBuilder();	
					if(commonFilter.getMonwise().equals("Y"))
					{
						date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
					}
					else
					date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		String[] header =  FipWavestageList.get(3);
		String[] month =  FipWavestageList.get(2);
		String[] data =  FipWavestageList.get(4);
         
		StringBuilder title=new StringBuilder(titlename+" -Wave No. Wise Count On FIP Stages - ").append(date);		
		
		int rSize = FipWavestageList.size()-3; 
		int cSize = header.length -2;  

		String subTitle="";
		Double [][] grData = new Double[rSize][cSize];
		for( int i =2;i <header.length;i++ ){
			for (int j = 3; j< FipWavestageList.size();j++) {
				data  =  FipWavestageList.get(j);
			    grData[j-3][i-2] = Double.parseDouble(data[i]);
				
			}
		
				xAxisCategory.add(month[i]);
		}
		for(int k=0;k<grData.length;k++){
			 ChartSeries timeSeries = new ChartSeries();
			 timeSeries.setData(Arrays.asList(grData[k]));
			 timeSeries.setType(ChartTypes.COLUMN);
			 timeSeries.setName(FipWavestageList.get(k+3)[1]);
			 chartSeriesList.add(timeSeries);			
		}
		 ChartYAxis yAxis = new ChartYAxis(); 
		 yAxis.setMin(0);
		 chartYAxis.add(yAxis);
		 yAxis.getTitle().setText("Stages");
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Wave No");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}
   
	private List<String[]> transposeListArr1(List<String[]> dataList)
	{
		CommonMessage.debugMsg("INSIDE transposeListArr1"+dataList.size());
		if( dataList.size() <=0 ) return null;
		CommonMessage.debugMsg("INSIDE transposeListArr2"+dataList.size());
		List<String[]> transposeList = new ArrayList<String[]>();		
		for( int i =0; i<dataList.size(); i++)
		{	
			String [] tRow = new String [ dataList.get(0).length];
			CommonMessage.debugMsg("tRow [ j ] : "+tRow.length);
			for (int j=0; j<dataList.get(0).length;j++)
			{
				tRow [ j ]= dataList.get(i)[j];//.equals("0")?dataList.get(i)[j].replace("0", "-"):dataList.get(i)[j];
				
			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
 	
	private void getColDMAIC(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		//CommonFilter commonFilter = new CommonFilter();
		//commonFilter.setKey(keyid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			//FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  projectService.getDmaic(keyid);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 //jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "102%%");
     	 jsonObject.set("tableHeight", "27%%");
   	   //  httpSession.removeAttribute("dmaicStatusColModel");
	//	 httpSession.setAttribute("dmaicStatusColModel",jsonObject);	
		 //CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}

	private void getDataDMAIC(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("getDataDMAIC.empnom");	
		try {
			//CommonFilter commonFilter = populateCommonFilter(request,"dmaicStatusCommonFilter",true);
			//FilterValues.getCommonFilters(request, commonFilter);
			String keyId =request.getParameter("keyid");
			//CommonMessage.debugMsg("keyid"+keyId);
		//	commonFilter.setKey(keyId);
			List<String[]> MasterGrid = projectService.getDmaic(keyId);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	private void getColResources(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setKey(keyid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  projectService.getResources(commonFilter);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "102%%");
     	 jsonObject.set("tableHeight", "36%%");
   	     httpSession.removeAttribute("resourcesColModel");
		 httpSession.setAttribute("resourcesColModel",jsonObject);	
		 //CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	private void getDataResources(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("getDataResources");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"resourcesCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String keyId =request.getParameter("keyid");
			//CommonMessage.debugMsg("keyid"+keyId);
			commonFilter.setKey(keyId);
			List<String[]> MasterGrid = projectService.getResources(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void getColKpiLink(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("master");
		CommonFilter commonFilter = new CommonFilter();
		commonFilter.setKey(keyid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  projectService.getListOfIndicators(commonFilter);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "56%%");
     	 jsonObject.set("tableHeight", "79%%");
   	     httpSession.removeAttribute("kpiLinkColModel");
		 httpSession.setAttribute("kpiLinkColModel",jsonObject);	
		 //CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	
	private void getDataKpiLink(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("get Data KPI Link");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"kpiLinkCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String keyId =request.getParameter("master");
			//CommonMessage.debugMsg("keyid"+keyId);
			commonFilter.setKey(keyId);
			List<String[]> MasterGrid = projectService.getListOfIndicators(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0,commonFilter.getTotalRecordCnt());
			out.println(ResourceGridmod);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	private void getColNewResources(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String flid= request.getParameter("flid");
		String kzpmKeyid =request.getParameter("kzpmKeyid");
		CommonFilter commonFilter = new CommonFilter();		
		//CommonMessage.debugMsg("kzpmKeyid"+kzpmKeyid+":flid"+flid);
		commonFilter.setKey(kzpmKeyid);
		commonFilter.setFlid(flid);

		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  projectService.getListNewResources(commonFilter);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 jqGridTableModel.setGridEdit(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "40%%");
     	 jsonObject.set("tableHeight", "70%%");
     	 httpSession.removeAttribute("resourcesNewColModel");
		 httpSession.setAttribute("resourcesNewColModel",jsonObject);	
		 //CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	private void getDataNewResources(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("get Data Resources");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"resourcesNewCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String flid= request.getParameter("flid");
			String kzpmKeyid =request.getParameter("kzpmKeyid");
			//CommonMessage.debugMsg("kzpmKeyid"+kzpmKeyid+":flid"+flid);
			commonFilter.setKey(kzpmKeyid);
			commonFilter.setFlid(flid);
			List<String[]> MasterGrid = projectService.getListNewResources(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0,commonFilter.getTotalRecordCnt());
			out.println(ResourceGridmod);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	private void getColProjectCreationList(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		//String keyid = request.getParameter("master");
		//CommonFilter commonFilter = new CommonFilter();
		//commonFilter.setKey(keyid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;
		CommonMessage.debugMsg("Start ");
		try {						
			 CommonFilter commonFilter = populateCommonFilter(request,"ProjectCreationListCommonFilter",true);
			 CommonMessage.debugMsg("Start commonfilter ");
			 projectListGrid =  projectService.getProjectCreationList(commonFilter);
					 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 jqGridTableModel.setGridEdit(false);
			 String [] colHeaderHead = projectListGrid.get(0);
			 String [] colHeader = projectListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			 
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "79%%");
	   	     //httpSession.removeAttribute("ProjectCreationListColModel");
			 //httpSession.setAttribute("ProjectCreationListColModel",jsonObject);	
			 //CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void getDataProjectCreationList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("get Data KPI Link");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"ProjectCreationListCommonFilter",true);
			//FilterValues.getCommonFilters(request, commonFilter);
			//String keyId =request.getParameter("master");
			//CommonMessage.debugMsg("keyid"+keyId);
			//commonFilter.setKey(keyId);
		/*	String flid= request.getParameter("flid");
			if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
				CommonMessage.debugMsg("flid "+flid);
			}
			commonFilter.setFlid(flid);
		*/	List<String[]> MasterGrid = projectService.getProjectCreationList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0,commonFilter.getTotalRecordCnt());
			out.println(ResourceGridmod);
			
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void getColdmcProjectCreationList(HttpServletRequest request,
			HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		//String keyid = request.getParameter("master");
		//CommonFilter commonFilter = new CommonFilter();
		//commonFilter.setKey(keyid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {
			 CommonFilter commonFilter = populateCommonFilter(request,"ProjectCreationListCommonFilter",true);
			 AdmTlUsermst user = UIUtils.getLoginUser(request);
			 String userid = user.getUsrm_ccno();
			 commonFilter.setET(userid);
			 CommonMessage.debugMsg("Ingetcol="+userid);
			 CommonMessage.debugMsg("Ingetcolcommonfilter="+commonFilter.getET());
			 projectListGrid =  projectService.getdmcProjectCreationList(commonFilter);
					 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 jqGridTableModel.setGridEdit(false);
			 String [] colHeaderHead = projectListGrid.get(0);
			 String [] colHeader = projectListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			 
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "79%%");
	   	     //httpSession.removeAttribute("ProjectCreationListColModel");
			 //httpSession.setAttribute("ProjectCreationListColModel",jsonObject);	
			 //CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	private void getDatadmcProjectCreationList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		//CommonMessage.debugMsg("get Data KPI Link");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"ProjectCreationListCommonFilter",true);
			//FilterValues.getCommonFilters(request, commonFilter);
			//String keyId =request.getParameter("master");
			//CommonMessage.debugMsg("keyid"+keyId);
			//commonFilter.setKey(keyId);
		/*	String flid= request.getParameter("flid");
			if(!UIUtils.isValidKeyId(flid)){
				flid=(String) httpSession.getAttribute("loginFlid");
				CommonMessage.debugMsg("flid "+flid);
			}
			commonFilter.setFlid(flid);
		*/	AdmTlUsermst user = UIUtils.getLoginUser(request);
			String userid = user.getUsrm_ccno();
			commonFilter.setET(userid);
			CommonMessage.debugMsg("Ingetdata="+userid);
			CommonMessage.debugMsg("in getdatacommonfilter="+commonFilter.getET());
			List<String[]> MasterGrid = projectService.getdmcProjectCreationList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0,commonFilter.getTotalRecordCnt());
			out.println(ResourceGridmod);
			
		} catch (Exception e) {
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	private void  checkList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String action = UIUtils.getActionPart(request);
		if( action.equals("prjCheckList_input.prpo")){
			displayForm(request,response);
		}
		else if( action.equals("prjCheckList_getCol.prpo")){
			String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.ProjectCreationValidation", "projCheckListColModel");
			response.getWriter().println(colModel);
		}
		else if( action.equals("prjCheckList_getData.prpo")){
			
			populateCheckListData(request,response);
			
		}
		else if( action.equals("prjCheckList_save.prpo")){
			saveForm(request,response);
		}
		
	}

	private synchronized void populateCheckListData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String stage = request.getParameter("stage") ;
		String projectId = request.getParameter("projectId") ;
		List<String[]> dataList =   projectService.getProjectCheckList(stage, projectId);
		PrintWriter out = response.getWriter();
		JSONObject checkList = UIUtils.convertToJqGridTableObject(dataList, request,0,1);
		out.println(checkList);
	}
	
	private void displayForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String loginUserId = UIUtils.getLoginUser(request).getUsrm_ccno();
		
		
		String stage = request.getParameter("clstage");
		String type = request.getParameter("type");
		
		if(! UIUtils.isValidKeyId(stage)   )
			stage = "X";
		String projectId = request.getParameter("projectId");
		String createdBy = request.getParameter("createdBy");
		String workFlowEnableEmps =  request.getParameter("workFlowEnableEmps");
		String mode = request.getParameter("mode");
		
		String flid = request.getParameter("flid");
		
		String role = request.getParameter("role");
		String loginUser = UIUtils.getLoginUser(request).getUsrm_ccno();
		char enableInlude = 'N';
		char enableOk = 'N';
		//char enableVerfyAmnt = 'N';
		if(loginUserId.equals(createdBy) )
			enableInlude ='Y';
		//else
		if(workFlowEnableEmps.contains(loginUser) && role!= null && "KKCHAMPION".equals(role.replace(" " , "").toUpperCase())){
			
			enableOk ='Y';
		}	
		/*else if(workFlowEnableEmps.contains(loginUser) && role!= null && "FINANCE".equals(role.replace(" " , "").toUpperCase())){
			
			enableVerfyAmnt ='Y';
		}*/	
		if("view".equals( mode)){
			enableInlude = 'N';
			enableOk = 'N';
		}
		
		request.setAttribute("currentStage", stage.charAt(0));
		request.setAttribute("projectId", projectId);
		request.setAttribute("flid", flid);
		request.setAttribute("enableInclude", enableInlude);
		request.setAttribute("enableOk", enableOk);
		request.setAttribute("verifiedBy",loginUserId);
		//request.setAttribute("enableVerfyAmnt",enableVerfyAmnt);
		if("dmc".equals(type))
			UIUtils.forwardRequest(request, response, "/pages/KK/dmcprjchecklist.jsp");
		else
		    UIUtils.forwardRequest(request, response, "/pages/KK/prjchecklist.jsp");
	}
	
	private void saveForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String checkListStrArr = request.getParameter("checkListArr") ;
		if( checkListStrArr != null ){
			
			JSONArray jsonCheckListArray = JSONArray.fromString(checkListStrArr);
			KznTlProjectChecklistLink kznTlProjectChecklistLink = new  KznTlProjectChecklistLink();
			
			List<KznTlProjectChecklistLink> projectChecklistLinkList = (List<KznTlProjectChecklistLink>)UIUtils.convertJSONArrToList(kznTlProjectChecklistLink, jsonCheckListArray);
			
			try {
				projectService.createCheckList(projectChecklistLinkList, UIUtils.getLoginUser(request).getUsrm_ccno());
				
				String saveMsg =  "CheckList Saved Successfully";//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save");
		
				JSONObject successData = new JSONObject();
				String status = request.getParameter("status") ;
				String smsg = request.getParameter("smsg") ;
				String isAllYes = request.getParameter("isAllYes") ;
				String type = request.getParameter("Type") ;
				String gridId = request.getParameter("gridId") ;
				String rowId = request.getParameter("rowId") ;
				String apprMode = request.getParameter("apprMode") ;
				if( smsg == null)
					smsg="";
				
				successData.put("msg", saveMsg);
				successData.put("status", status);
				successData.put("smsg", smsg);
				successData.put("isAllYes", isAllYes);
				successData.put("apprMode", apprMode);
				JSONObject record = new JSONObject();
				record.put("type", type);
				record.put("gridId", gridId);
				record.put("rowId", rowId);
				successData.put("record", record);
				response.getWriter().print(successData.toString());
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				response.getWriter().print(err.toString());
			}
		}	
	}
	
	private JSONObject getTableModel_FIP(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(2);
		String[] emptyrow = new String[colHeader.length]; 	
	
		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 1; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		jqGridTableModel.getRowHeaders().add(colHeader);			
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);			 
		  for(int i =0; i < colHeader.length; i++)
		{

			JqGridColModel jqGridColModel = new JqGridColModel();
		
			jqGridColModel.setWidth(100);				
			//jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			//if(i==0 || i == 1 || i == 2)
			if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i == 1 || i == 2)
			{
				jqGridColModel.setHidden(false);
				if(i==0)
					jqGridColModel.setKey(true);
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			}
			if(i == 3)
			{
				jqGridColModel.setWidth(125);
				jqGridColModel.setAlign("left");
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			}
			if(i>3)
			{
				jqGridColModel.setWidth(125);
				
				jqGridColModel.setAlign("right");
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+i);
			}		
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	
	private void loadPrjResourceFunctionalLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
		functLocFieldNameBean.setCompany("cmbComp");
		//fact// functLocFieldNameBean.setFactory("cmbFact");
		functLocFieldNameBean.setLocation("cmbLcn");
		functLocFieldNameBean.setSbu("cmbSbu");
		functLocFieldNameBean.setPbu("cmbPbu");
		functLocFieldNameBean.setSection("cmbSect");
		functLocFieldNameBean.setCell("cmbCell");
		functLocFieldNameBean.setMachine("cmbMachine");
		functLocFieldNameBean.setFactMandatory(false);
		functLocFieldNameBean.setSectMandatory(true);
		functLocFieldNameBean.setLocnMandatory(false);
		functLocFieldNameBean.setCellMandatory(false);
		functLocFieldNameBean.setMachMandatory(false);
		String disableFuncLoc = request.getParameter("disableFuncLoc");
		
		if("true".equals(disableFuncLoc)){
			functLocFieldNameBean.setCompany("cmbComp");
			//fact// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			//fact// functLocFieldNameBean.setFactDisable(true);
			functLocFieldNameBean.setSbuDisable(true);
			functLocFieldNameBean.setPbuDisable(true);
			//fact// functLocFieldNameBean.setFactDisable(true);
			functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);
		}
		
		functLocFieldNameBean.setLconDisable(true);
		functLocFieldNameBean.setSbuDisable(false);
		functLocFieldNameBean.setPbuDisable(false);
		
		functLocFieldNameBean.setSectDisable(false);
		functLocFieldNameBean.setCellDisable(false);
		functLocFieldNameBean.setMachDisable(false);
		
		
		
		FormModes formModes = FormModes.create;
		
		UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
	}

}
