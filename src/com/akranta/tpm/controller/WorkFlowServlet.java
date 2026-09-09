package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GenTlWorkflowdtl;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.WorkFlowmst;
import com.akranta.tpm.service.WorkFlowService;
import com.akranta.tpm.service.impl.WorkFlowServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/**
 * Servlet implementation class WorkFlowServlet
 * Created By Roopa
 */
//@WebServlet("/WorkFlowServlet")
public class WorkFlowServlet extends HttpServlet {
	WorkFlowService workflowService;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public WorkFlowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception  {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		workflowService= (WorkFlowServiceImpl)UIUtils.getServiceObject(request, "WorkFlowServiceImpl");
		workflowService.WorkFlowServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		String action = UIUtils.getActionPart(request);
		CommonFilter commonFilter = null;// new CommonFilter();
		
		CommonMessage.debugMsg(" response " + action);
		if( action.startsWith("wrktrn_")){
			workflowTrans(action,request,response);
		}
		else if (action.equals("WorkFlow_input.workflow"))  
		{			
			try
			{  			
				UIUtils.displayRequestParamsValue(request);	
				String mstkeyid=request.getParameter("keyid");	
				//commonFilter.setKey(mstkeyid);
				PrintWriter out = response.getWriter();
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}	
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WorkFlowGrid.jsp");
			rd.forward(request, response);
		}
		
		else if (action.equals("WorkFlow_getCol.workflow")) {
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg(" form getCol");
			String masterkeyid = request.getParameter("keyid");
			commonFilter = new CommonFilter();
			commonFilter.setKey(masterkeyid);		
			JSONObject jsonObject = new JSONObject();
			List<String[]>roleMapGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				roleMapGrid =  workflowService.getMasterGrid(commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
					
			JSONObject roleMapGridData =UIUtils.convertToJqGridTableObject(roleMapGrid,request,0,0,commonFilter.getTotalRecordCnt());
			 //CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);	
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);			 
			 String [] colHeaderHead = roleMapGrid.get(0);
			 String [] colHeader = roleMapGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 jsonObject.put("data", roleMapGridData);	
	   	     jsonObject.set("tableWidth", "60%%");
	     	 jsonObject.set("tableHeight", "62%%");
	   	     httpSession.removeAttribute("roleMapColModel");
			 httpSession.setAttribute("roleMapColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);		
		}
		else if (action.equals("WorkFlow_getData.workflow")) {
			try {

				commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				String masterkeyid = request.getParameter("keyid");
				commonFilter.setKey(masterkeyid);
				List<String[]> MasterGrid = workflowService.getMasterGrid(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request, 2,0);
				out.println(ResourceGridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}		
		else if(action.equals("WorkFlowEntry_save.workflow")){			
			saveData(request,response);	
		}
		
		else if(action.equals("WorkFlowEntry_input.workflow")){
			
			String keyid =request.getParameter("keyid");
			//CommonMessage.debugMsg("keyid"+keyid);			
			WorkFlowmst  workflowmst=null;//model		
			if(UIUtils.isValidKeyId(keyid)){
				//CommonMessage.debugMsg("keyid"+keyid);
				workflowmst = workflowService.select(keyid);				
				request.setAttribute("workflowmst" , workflowmst);
			}			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WorkFlowMaster.jsp");
			rd.forward(request, response);
			//CommonMessage.debugMsg(" response  " + response);
			
		}
		else if(action.equals("WorkFlowEntry_getCol.workflow")){
			//CommonMessage.debugMsg(" form getCol");
			PrintWriter out = response.getWriter();
			String  form = request.getParameter("form");
			String keyid =request.getParameter("keyid");
        	//CommonMessage.debugMsg(" form getCol::::: " + form);			
			JSONObject jsonObject = new JSONObject();
			List<String[]>masterGrid = null;					
			try {
				commonFilter = new CommonFilter();
				commonFilter.setKey(keyid);							
				FilterValues.getCommonFilters(request, commonFilter);
				masterGrid =  workflowService.getDetail(commonFilter);	
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
					
			JSONObject masterGridData =UIUtils.convertToJqGridTableObject(masterGrid,request,0,0,commonFilter.getTotalRecordCnt());
			 //CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			 			 
			 gridColModel.setHeaderNum(1);	
			 gridColModel.setFormatter("formatterChkWorkFlow");
			 gridColModel.setFormattorFromCol("2");
			 gridColModel.setFormattorToCol("2");		 
			 
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	 
			 
			 String[] formatterval  = {"cmbRole#6","cmbType#8"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 
			 String [] colHeaderHead = masterGrid.get(0);
			 String [] colHeader = masterGrid.get(1);	
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 jsonObject.put("data", masterGridData);	
	   	     jsonObject.set("tableWidth", "43%%");
	     	 jsonObject.set("tableHeight", "56%%");
	   	     httpSession.removeAttribute("roleMapColModel");
			 httpSession.setAttribute("roleMapColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);					
		}		
		else if(action.equals("WorkFlowEntry_getData.workflow")){ /*for inner grid*/			
			try {
				commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				//String mstkeyid=request.getParameter("mstkeyid");	
				String keyid =request.getParameter("keyid");
				commonFilter.setKey(keyid);
				List<String[]> ResourceGrid = workflowService.getDetail(commonFilter);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);	
				JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(ResourceGrid, request, 2,0);
				out.println(ResourceGridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}			
		}
		
		else if( action.equals("WorkFlowEntry_delete.workflow"))
		{	
			HttpSession httpSession1= request.getSession(false);
			CommonMessage.debugMsg("keyid");			
			deletemaster(request,response);			
		}
		
		else if (action.equals("WorkFlowEntry_save.workflow"))
		{			
			buttonsave(request,response);
		}
		
		else if (action.equals("detaildelete_remove.workflow"))
		{
			detaildelete(request,response);
		}
		else if( action.equals("typeCombo.workflow"))
		{	
			//CommonMessage.debugMsg("inside jhAuditLevelCombo.jhAuditItc");
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "comboType"));
	 	    CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "comboType"));
   
		}
		else if( action.equals("RoleCombo.workflow"))
		{	
			ComboFilter roleFilterComboFilter = new ComboFilter();
			StringBuffer cond = new StringBuffer();	
			try{
				roleFilterComboFilter=UIUtils.fillComboFilter(request);
				//roleFilterComboFilter.setCondSql(cond.toString());
				List<ComboBox> rolefilcomboList = workflowService.getRoleComboList(roleFilterComboFilter);
				UIUtils.writeComboBox(response, rolefilcomboList ,roleFilterComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
		}
		else if( action.equals("approvalList_input.workflow"))
		{	
			try
			{  			
				UIUtils.displayRequestParamsValue(request);	
				String mstkeyid=request.getParameter("keyid");
				commonFilter = new CommonFilter();
				commonFilter.setKey(mstkeyid);
				PrintWriter out = response.getWriter();
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}	
			RequestDispatcher rd = request.getRequestDispatcher("/pages/WorkFlowApproval.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("approvalList_getCol.workflow")){
			//CommonMessage.debugMsg(" form getCol");
			PrintWriter out = response.getWriter();
			String  form = request.getParameter("form");
			String keyid =request.getParameter("keyid");
        	//CommonMessage.debugMsg(" form getCol::::: " + form);
			commonFilter = new CommonFilter();
			JSONObject jsonObject = new JSONObject();
			List<String[]>masterGrid = null;					
			try {				
				commonFilter.setKey(keyid);							
				FilterValues.getCommonFilters(request, commonFilter);
				masterGrid =  workflowService.getDetail(commonFilter);	
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
					
			JSONObject masterGridData =UIUtils.convertToJqGridTableObject(masterGrid,request,0,0,commonFilter.getTotalRecordCnt());
			 //CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			 			 
			 gridColModel.setHeaderNum(1);	
			 gridColModel.setFormatter("formatterChkWorkFlow");
			 gridColModel.setFormattorFromCol("2");
			 gridColModel.setFormattorToCol("2");		 
			 
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	 
			 
			 String[] formatterval  = {"cmbRole#6","cmbType#8"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 
			 String [] colHeaderHead = masterGrid.get(0);
			 String [] colHeader = masterGrid.get(1);	
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 jsonObject.put("data", masterGridData);	
	   	     jsonObject.set("tableWidth", "43%%");
	     	 jsonObject.set("tableHeight", "56%%");
	   	     httpSession.removeAttribute("roleMapColModel");
			 httpSession.setAttribute("roleMapColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);					
		}		
		else if(action.equals("approvalList_getData.workflow")){ /*for inner grid*/			
			try {
				commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				//String mstkeyid=request.getParameter("mstkeyid");	
				String keyid =request.getParameter("keyid");
				commonFilter.setKey(keyid);
				List<String[]> ResourceGrid = workflowService.getDetail(commonFilter);
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);	
				JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(ResourceGrid, request, 2,0);
				out.println(ResourceGridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}			
		}
		
		else if(action.equals("dmcwrktrn_workflowapp_input.workflow")){
			CommonMessage.debugMsg("dmc input work flow");
			String user = UIUtils.getLoginUser(request).getUsrm_ccno();
			CommonMessage.debugMsg("login empid="+user);
			request.setAttribute("loginempid", user);
			request.setAttribute("empId", request.getParameter("empId"));
			CommonMessage.debugMsg("dmc input work flow empId="+ request.getParameter("empId"));
			request.setAttribute("refId", request.getParameter("refId"));
			CommonMessage.debugMsg("dmc input work flow refId="+ request.getParameter("refId"));
			request.setAttribute("refType", request.getParameter("refType"));
			CommonMessage.debugMsg("dmc input work flow refType="+ request.getParameter("refType"));
			request.setAttribute("refRoleId", request.getParameter("refRoleId"));
			CommonMessage.debugMsg("dmc input work flow refRoleId="+ request.getParameter("refRoleId"));
			request.setAttribute("transCode", request.getParameter("transCode"));
			request.setAttribute("flId", request.getParameter("flId"));
			request.setAttribute("minDate", request.getParameter("minDate"));
			request.setAttribute("maxDate", request.getParameter("maxDate"));
			request.setAttribute("enable", request.getParameter("enable"));
			
			CommonMessage.debugMsg("dmc input work flow enable="+request.getParameter("enable"));
			UIUtils.forwardRequest(request, response, "/pages/WorkFlow/dmcworkflowapprovaltrans.jsp");
		}
		else if(action.equals("dmcwrktrn_workflowapp_getCol.workflow")){
			
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "dmcworkflow_app_grid");
			response.getWriter().print(colModel);
			response.getWriter().close();
		}
		else if(action.equals("dmcwrktrn_workflowapp_getData.workflow")){
			
			String transCode   = request.getParameter("transCode");
			String empId   = request.getParameter("empId");
			if(! UIUtils.isValidKeyId(empId) ){
				empId = UIUtils.getLoginUser(request).getUsrm_ccno();
			}
			String refId   =  request.getParameter("refId");
			String flId   =  request.getParameter("flId");
			String refType =  request.getParameter("refType");
			String roleId =  request.getParameter("refRoleId");
			String enable =  request.getParameter("enable");
			
			GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo();
			genTlWorkflowInfo.setWrinEmployeeId(empId);
			genTlWorkflowInfo.setWrinRefId(refId);
			genTlWorkflowInfo.setWrinRefType(refType);
			if( UIUtils.isValidKeyId(enable) )
				genTlWorkflowInfo.setEnable(enable.trim().charAt(0));
			
			if(!UIUtils.isValidKeyId(roleId)){
				roleId="role_keyid";
			}
			CommonMessage.debugMsg(" refroleId  "+ roleId);
			try {
				List<String[]> dataList = workflowService.getdmcWorkFlowTransData(genTlWorkflowInfo, transCode,flId,roleId);
				response.getWriter().print(UIUtils.convertToJqGridTableObject(dataList, request, 0, 0, 0, dataList.size()));
				response.getWriter().close();
				dataList =null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			genTlWorkflowInfo = null;
			
		}
		else if(action.equals("dmcwrktrn_workflowapp_save.workflow")){
			savedmcWorkFlowTrans(request,response);
		}
		commonFilter = null;
	}
	
	private void workflowTrans(String action , HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		CommonMessage.debugMsg(" action  "+ action);
		if(action.equals("wrktrn_workflowapp_input.workflow")){
			
			request.setAttribute("empId", request.getParameter("empId"));
			request.setAttribute("refId", request.getParameter("refId"));
			request.setAttribute("refType", request.getParameter("refType"));
			request.setAttribute("refRoleId", request.getParameter("refRoleId"));
			request.setAttribute("transCode", request.getParameter("transCode"));
			request.setAttribute("flId", request.getParameter("flId"));
			request.setAttribute("minDate", request.getParameter("minDate"));
			request.setAttribute("maxDate", request.getParameter("maxDate"));
			request.setAttribute("enable", request.getParameter("enable"));
			CommonMessage.debugMsg(" In side the work flow grid");
			UIUtils.forwardRequest(request, response, "/pages/WorkFlow/workflowapprovaltrans.jsp");
		}
		else if(action.equals("wrktrn_workflowapp_getCol.workflow")){
			
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "workflow_app_grid");
			response.getWriter().print(colModel);
			CommonMessage.debugMsg(colModel +" colModelcolModelcolModelcolModel");
			response.getWriter().close();
		}
		else if(action.equals("wrktrn_workflowapp_getData.workflow")){
			
			String transCode   = request.getParameter("transCode");
			String empId   = request.getParameter("empId");
			CommonMessage.debugMsg("The EmpId::::"+empId);
			if(! UIUtils.isValidKeyId(empId) ){
				empId = UIUtils.getLoginUser(request).getUsrm_ccno();
			}
			String refId   =  request.getParameter("refId");
			CommonMessage.debugMsg("The RefID:::"+refId);
			String flId   =  request.getParameter("flId");
			CommonMessage.debugMsg("The flId:::"+flId);
			String refType =  request.getParameter("refType");
			CommonMessage.debugMsg("The RefType:::"+refType);
			String roleId =  request.getParameter("refRoleId");
			CommonMessage.debugMsg("The roleId:::"+roleId);
			String enable =  request.getParameter("enable");
			
			CommonMessage.debugMsg("The enable -----:::"+enable);

			GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo();
			genTlWorkflowInfo.setWrinEmployeeId(empId);
			genTlWorkflowInfo.setWrinRefId(refId);
			genTlWorkflowInfo.setWrinRefType(refType);
			if( UIUtils.isValidKeyId(enable) )
				genTlWorkflowInfo.setEnable(enable.trim().charAt(0));
			
			if(!UIUtils.isValidKeyId(roleId)){
				roleId="role_keyid";
			}
			CommonMessage.debugMsg(" refroleId  "+ roleId);
			try {
				List<String[]> dataList = workflowService.getWorkFlowTransData(genTlWorkflowInfo, transCode,flId,roleId);
				response.getWriter().print(UIUtils.convertToJqGridTableObject(dataList, request, 0, 0, 0, dataList.size()));
				response.getWriter().close();
				dataList =null;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			genTlWorkflowInfo = null;
			
		}
		else if(action.equals("wrktrn_workflowapp_save.workflow")){
			saveWorkFlowTrans(request,response);
		}
		
		
	}
	private void saveWorkFlowTrans(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out= null; 
		try{

			out = response.getWriter();
			String workFlowInfoStr = request.getParameter("workFlowData");
			CommonMessage.debugMsg(workFlowInfoStr);
			GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo(); 
			if(UIUtils.isValidKeyId(workFlowInfoStr) )
    		{
		    	JSONArray genWorkFlowInfoJson = JSONArray.fromString(workFlowInfoStr);
		    	CommonMessage.debugMsg(genWorkFlowInfoJson);
		    	List<GenTlWorkflowInfo> genTlWorkflowInfoList=(List<GenTlWorkflowInfo>)UIUtils.convertJSONArrToList(genTlWorkflowInfo, genWorkFlowInfoJson);
		    	
		    	genTlWorkflowInfo =genTlWorkflowInfoList.get(0);
		    	
		    	CommonMessage.debugMsg(" Role Id "+genTlWorkflowInfo.getWrinRoleId());
		    	
		    	String refId   =  request.getParameter("hdnRefId");
				String refType =  request.getParameter("hdnRefType");
				genTlWorkflowInfo.setWrinRefId(refId);
				genTlWorkflowInfo.setWrinRefType(refType); 
				String createdBy = UIUtils.getLoginUser(request).getUsrm_ccno(); 
				genTlWorkflowInfo.setWrinCreatedby(createdBy);
				
				CommonMessage.debugMsg("refType=="+refType);
				
    		}
			
			//genTlWorkflowInfo = (GenTlWorkflowInfo)UIUtils.setBeanProperties(genTlWorkflowInfo ,request);
			String lastLevel =  request.getParameter("lastLevel");
			String nextRoleName =  request.getParameter("nextRoleName");
			String nextRoleId =  request.getParameter("nextRoleId");
			String nextEmpId =  request.getParameter("nextEmp");
			genTlWorkflowInfo.setNextEmpId(nextEmpId);
			
			CommonMessage.debugMsg("lastLevellastLevel "+lastLevel+" nextRoleNamenextRoleName "+nextRoleName);
			
			workflowService.saveGenTlWorkFlowInfoMst(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId);
			
			
			JSONObject successData = new JSONObject();
			String transCode = request.getParameter("hdnTransCode");
			successData.put("msg","Data Saved Successfully");
			JSONObject returnData = new JSONObject();//
			returnData.put("wfStatus", genTlWorkflowInfo.getWrinStatus());
			returnData.put("transCode", transCode);
			returnData.put("lastLevel", lastLevel);
			returnData.put("nextRoleName", nextRoleName);
			returnData.put("nextRoleId", nextRoleId);
			returnData.put("successData", successData);
			
			returnData.put("formClear", false);
			
			out.print(returnData.toString());//
			//out.close();

		}catch(ValidationExceptions e ){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "WorkFlowCreation");
			out.print(errMessage.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void savedmcWorkFlowTrans(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out= null; 
		try{

			out = response.getWriter();
			String workFlowInfoStr = request.getParameter("workFlowData");
			CommonMessage.debugMsg(workFlowInfoStr);
			GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo(); 
			if(UIUtils.isValidKeyId(workFlowInfoStr) )
    		{
		    	JSONArray genWorkFlowInfoJson = JSONArray.fromString(workFlowInfoStr);
		    	CommonMessage.debugMsg(genWorkFlowInfoJson);
		    	List<GenTlWorkflowInfo> genTlWorkflowInfoList=(List<GenTlWorkflowInfo>)UIUtils.convertJSONArrToList(genTlWorkflowInfo, genWorkFlowInfoJson);
		    	
		    	genTlWorkflowInfo =genTlWorkflowInfoList.get(0);
		    	
		    	String refId   =  request.getParameter("hdnRefId");
				String refType =  request.getParameter("hdnRefType");
				genTlWorkflowInfo.setWrinRefId(refId);
				genTlWorkflowInfo.setWrinRefType(refType); 
				String createdBy = UIUtils.getLoginUser(request).getUsrm_ccno(); 
				genTlWorkflowInfo.setWrinCreatedby(createdBy);
				
				CommonMessage.debugMsg("refType=="+refType);
				
    		}
			
			//genTlWorkflowInfo = (GenTlWorkflowInfo)UIUtils.setBeanProperties(genTlWorkflowInfo ,request);
			String lastLevel =  request.getParameter("lastLevel");
			String nextRoleName =  request.getParameter("nextRoleName");
			String nextRoleId =  request.getParameter("nextRoleId");
			String nextEmpId =  request.getParameter("nextEmp");
			genTlWorkflowInfo.setNextEmpId(nextEmpId);
			workflowService.savedmcGenTlWorkFlowInfoMst(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId);
			
			
			JSONObject successData = new JSONObject();
			String transCode = request.getParameter("hdnTransCode");
			successData.put("msg","Data Saved Successfully");
			JSONObject returnData = new JSONObject();//
			returnData.put("wfStatus", genTlWorkflowInfo.getWrinStatus());
			returnData.put("transCode", transCode);
			returnData.put("lastLevel", lastLevel);
			returnData.put("nextRoleName", nextRoleName);
			returnData.put("nextRoleId", nextRoleId);
			returnData.put("successData", successData);
			
			returnData.put("formClear", false);
			
			out.print(returnData.toString());//
			out.close();

		}catch(ValidationExceptions e ){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "WorkFlowCreation");
			out.print(errMessage.toString());
		}catch(Exception e){
			
		}
	}
	private void detaildelete(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
//				keyid="'" + keyid.replace(",", "','") + "'" ;
				workflowService.Deletelist(keyid);
				String msgPropertyIdnt = "success-delete";
				CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				err.put("formClear",true);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
		    CommonMessage.debugMsg("Delete End");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception: "+e);
			JSONObject err = new JSONObject();
			String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}	
				
	}

	private void saveData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null)
			{				
				WorkFlowmst existworkflowmst = (WorkFlowmst)httpSession.getAttribute("wrk");			 
				String workFlowDtls = request.getParameter("wrkdtl");
				
				WorkFlowmst newGenT1Workflowmst = new WorkFlowmst();
				GenTlWorkflowdtl newGenTlWorkflowdtl = new GenTlWorkflowdtl();
				newGenT1Workflowmst.setWrkmCreatedby(user.getUsrm_ccno());
				//newGenTlWorkflowdtl.setWrkdCreatedby(user.getUsrm_ccno());
				newGenT1Workflowmst =(WorkFlowmst)UIUtils.setBeanProperties((Object)newGenT1Workflowmst,request);
			    newGenTlWorkflowdtl =(GenTlWorkflowdtl)UIUtils.setBeanProperties((Object)newGenTlWorkflowdtl,request);		
			    
			    List<GenTlWorkflowdtl> genTlWorkflowdtlList = null;
	    		JSONArray genTlWorkflowdtlListjson = null;
			    if(UIUtils.isValidKeyId(workFlowDtls) )
	    		{
			    	genTlWorkflowdtlListjson = JSONArray.fromString(workFlowDtls);
			    	genTlWorkflowdtlList=(List<GenTlWorkflowdtl>)UIUtils.convertJSONArrToList(newGenTlWorkflowdtl, genTlWorkflowdtlListjson);
			    	newGenT1Workflowmst.setWorkFlowDtls(genTlWorkflowdtlList);
	    		}
				String saveMsg ;
				//CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()" +  newQtmTlDockaudit.getQaudkeyid());
				if( newGenT1Workflowmst.getWrkmKeyid()== null )
				{	
					CommonMessage.debugMsg("key id is not available ");				
					existworkflowmst =	 workflowService.create(newGenT1Workflowmst,existworkflowmst);					
					saveMsg = "Data Saved Successfully";
				}	
				else{
					CommonMessage.debugMsg("key id is available:" + newGenT1Workflowmst.getWrkmKeyid() );				
					existworkflowmst =	 workflowService.update(newGenT1Workflowmst,existworkflowmst);
					saveMsg = "Data Saved Successfully";
				}		
				JSONObject successData = new JSONObject();
				successData.put("msg",saveMsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", successData);
				returnData.put("keyid",  existworkflowmst.getWrkmKeyid());
				returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();
			}	
		}
		 catch (ValidationExceptions e) 
         {
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "WorkFlowCreation");
				out.print(errMessage.toString());							    	
         }
									
         catch(BusinessApplicationExceptions e)
          {
    	    e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "WorkFlowCreation");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage );
	      }
         catch(Exception e)
          {   
	         e.printStackTrace();
	         JSONObject err = new JSONObject();
	         err.put("tpmException", "Data Not Saved");
	         out.print(err.toString());
        }
	}		
	
	private void deletemaster(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
			
		if( httpSession != null && user != null)
		  {	
			WorkFlowmst existWorkFlowmst = (WorkFlowmst)httpSession.getAttribute("workflowmst");	    	
				//GenTlEmployeedtl existGenTlEmployeedtl = (GenTlEmployeedtl)httpSession.getAttribute("genTl"); 	    		
				WorkFlowmst GenT1Workflowmst = new WorkFlowmst();
				GenTlWorkflowdtl newGenTlWorkflowdtl = new GenTlWorkflowdtl();
				GenT1Workflowmst.setWrkmCreatedby(user.getUsrm_ccno());
				newGenTlWorkflowdtl.setWrkdCreatedby(user.getUsrm_ccno());	      		
				GenT1Workflowmst =(WorkFlowmst)UIUtils.setBeanProperties((Object)GenT1Workflowmst,request);	   
				 
			try {				
				//CommonMessage.debugMsg("   newGenTlMommst.getMomsKeyid() " +  newEntTlSkillReviewpointmst.getSirmKeyid());
				//CommonMessage.debugMsg("Delete function");						
				existWorkFlowmst = workflowService.delete(GenT1Workflowmst);										
				CommonMessage.debugMsg("After the IF Loop");
				httpSession.setAttribute(existWorkFlowmst.getWrkmKeyid(), existWorkFlowmst);		
				JSONObject mode = new JSONObject();
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("WrkmKeyid",existWorkFlowmst.getWrkmKeyid());	
				JSONObject forwardData = new JSONObject();					
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("SuccessData");
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("keyId", existWorkFlowmst.getWrkmKeyid());
				JSONObject returnData = new JSONObject();						
				returnData.put("successData", successData);
				returnData.put("formClear", true);	
				CommonMessage.debugMsg("successData"+successData);				
				out.print(returnData.toString());				
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Business EXC "+e);
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Momeeting");
				out.print(errMessage.toString());				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", " Data Not Delete ");
				out.print(err.toString());
			}
		}	
	}

	private JSONObject getTableModelForMasterGrid(List<String[]> masterGrid) {
		// TODO Auto-generated method stub	

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader =masterGrid.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(500);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));			
			jqGridColModel.setWidth(145);
			CommonMessage.debugMsg("inside ["+i+"]"+colHeader[i]);
			if(i==0){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "45%%");
		tableModel.set("tableWidth", "100%%");
		return tableModel;
	}
	private void buttonsave(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if( httpSession != null && user != null){				
				WorkFlowmst newWorkflowmst = new WorkFlowmst();
				WorkFlowmst existworkflowmst = (WorkFlowmst)httpSession.getAttribute("WorkFlowmst");	    		
				newWorkflowmst =(WorkFlowmst)UIUtils.setBeanProperties((Object)newWorkflowmst,request);
				newWorkflowmst.setWrkmCreatedby(user.getUsrm_ccno());
				CommonMessage.debugMsg("studentsds code~~ "+newWorkflowmst.getWrkmCreatedby());
                CommonMessage.debugMsg("key id:"+newWorkflowmst.getWrkmKeyid());
                CommonMessage.debugMsg("Name:"+newWorkflowmst.getWrkmName());
                CommonMessage.debugMsg("No Of Stage:"+newWorkflowmst.getWrkmNoofstage());
                String savemsg=null;
				boolean insert = true;
				
				if(newWorkflowmst.getWrkmKeyid() == null)
				{	CommonMessage.debugMsg("key id");
				    existworkflowmst =workflowService.create(newWorkflowmst ,existworkflowmst);	
				    savemsg="Data Saved Successfully";
				}					 
				else{
					CommonMessage.debugMsg("update");
					insert = false;
					existworkflowmst= workflowService.update(newWorkflowmst, existworkflowmst);
					savemsg="Data Updated Successfully";
				}
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();//
				returnData.put("successData", successData);
				returnData.put("keyid", existworkflowmst.getWrkmKeyid());
				returnData.put("formClear", false);
				out.print(returnData.toString());//
				out.close();
		}		
		}catch(ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"QPMValidation");
			//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}
	}

	private JSONObject getTableModelForResources(List<String[]> ResourceGrid) {
		// TODO Auto-generated method stub
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = ResourceGrid.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(500);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(145);
			CommonMessage.debugMsg("inside ["+i+"]"+colHeader[i]);
			
			if(i==0)                                       
			{				
				jqGridColModel.setWidth(40);				
			}
			if(i==1)
			{
				jqGridColModel.setFormatter("formatterCheckbox");
				jqGridColModel.setHidden(false); 
			}
		    if(i==2)
		    {
		    	jqGridColModel.setWidth(550);			
		    }
		    
		    if(i==3)
		    {
		    	jqGridColModel.setWidth(80);	
		    	jqGridColModel.setAlign("right");
		    }
		    if(i==4)
		    {
		    	jqGridColModel.setWidth(120);			
		    }
		    jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "45%%");
		tableModel.set("tableWidth", "100%%");
		return tableModel;		
	}
}