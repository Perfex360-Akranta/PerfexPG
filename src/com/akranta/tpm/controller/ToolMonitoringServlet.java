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

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.utils.CommonFunctions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.TlmTlAlertBean;
import com.akranta.tpm.bean.ToolChangeDetailsBean;
import com.akranta.tpm.bean.ToolTlMachineLinkBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.model.TlmTLAlertDetails;
import com.akranta.tpm.model.ToolChangeTlDetails;
import com.akranta.tpm.model.ToolTlMachineLink;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.ToolMonitoringService;
import com.akranta.tpm.service.impl.ToolMonitoringServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.utils.WOConstants;


/**
 * Servlet implementation class ToolMonitoringServlet
 */
//@WebServlet("/ToolMonitoringServlet")
public class ToolMonitoringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	ToolMonitoringService toolMoniService;
	CommonFilterService commonFilterService;
	ToolChangeDetailsBean toolDtlBean;
    public ToolMonitoringServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String dispatchUrl = null;
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		ComboFilter currentFilter = new ComboFilter();
		ComboFilter comboFilter = new ComboFilter();
		
		try {
			toolMoniService=(ToolMonitoringServiceImpl) UIUtils.getServiceObject(request,"ToolMonitoringServiceImpl");
		} catch (ServiceObjectCreationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if( user == null)
			return ;
		String action = UIUtils.getActionPart(request);
		
		if (action.equals("toolChange_input.tlmn")){
			
			com.akranta.tpm.utils.CommonFunctions.debugMsg(" in side tlmst" +request.getParameter("mode"));
			 request.getParameter("toolKeyId");
			request.setAttribute("mode",request.getParameter("mode"));
			String modeStr = request.getParameter(ReqtParamNameConst.FORM_MODE);
			request.getParameter("orderType");
			System.out.println("dsd _________0  "+modeStr);
			request.getParameter("sapsts");
			
			//CommonFunctions.debugMsg("sap status"+sapSts);
			httpSession.removeAttribute("ToolChangeFromMode");
			request.getParameter("menumode");
			FormModes mode=null;
			if(modeStr.equals("modify")){
				mode=FormModes.modify;
			}
			else if (modeStr.equals("view")){
				mode=FormModes.view;
			}
			else
				mode=FormModes.create;
			//FormModes mode = FormModes.create; 
			/*if(modeStr == null || ( modeStr != null && modeStr.equals(FormModeConsts.create ) )) 
				mode=FormModes.create;
			else if(modeStr.equals(FormModeConsts.modify) )
				mode=FormModes.modify;
			else */
				//mode=FormModes.view;
			
    		initilizeInputMode(mode, request, httpSession);	
			new ToolChangeTlDetails();			
			request.setAttribute("url", action);
			
			dispatchUrl = "/pages/ToolMon/ToolChangeDetails.jsp";
			UIUtils.forwardRequest(request, response, dispatchUrl);
       }
		
if (action.equals("toolChangeSummary_input.tlmn")){
//sectid=SEC0000009&cellid=CEL0000042&mchid=MCH0000287&toolid=TLM000000030&lastdate=11-11-16&stdcotm=0&activity=B&mode=
			com.akranta.tpm.utils.CommonFunctions.debugMsg(" in side tlmst");
			String sectId= request.getParameter("sectid");
			String cellId= request.getParameter("cellid");
			String mchId= request.getParameter("mchid");
			String toolId= request.getParameter("toolid");
			String lastDate= request.getParameter("lastdate");
			String stdCoTm= request.getParameter("stdcotm");
			String estshrp=request.getParameter("estshrp");
			String keyId=request.getParameter("keyId");
			String shrpNo=request.getParameter("shrpNo");
			String srlNo=request.getParameter("srlNo");
			String modeStr = request.getParameter(ReqtParamNameConst.FORM_MODE);
			
			request.setAttribute("Section", sectId);
			request.setAttribute("Cell", cellId);
			request.setAttribute("Machine", mchId);
			request.setAttribute("ToolId", toolId);
			request.setAttribute("LastDate", lastDate);
			request.setAttribute("StdCoTm", stdCoTm);
			request.setAttribute("EstShrp", estshrp);
			request.setAttribute("keyId", keyId);
			request.setAttribute("shrpNo", shrpNo);
			request.setAttribute("srlNo", srlNo);
			CommonFunctions.debugMsg("hdnEstSharp "+estshrp);
			request.setAttribute("reqType", "Summary");
			
			
			//CommonFunctions.debugMsg("sap status"+sapSts);
			httpSession.removeAttribute("ToolChangeFromMode");
			request.getParameter("menumode");
			
			FormModes mode = FormModes.create; 
			if(modeStr == null || ( modeStr != null && modeStr.equals(FormModeConsts.create ) )) 
				mode=FormModes.create;
			else if(modeStr.equals(FormModeConsts.modify) )
				mode=FormModes.modify;
			else 
				mode=FormModes.create;
			
			httpSession.setAttribute("GeneralMainFormMode",mode);
			initilizeInputMode(mode, request, httpSession);	
			new ToolChangeTlDetails();
			
			request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);
			request.setAttribute("url", action);
			
			dispatchUrl = "/pages/ToolMon/ToolChangeDetails.jsp";
			UIUtils.forwardRequest(request, response, dispatchUrl);
       }
		if (action.equals("toolChange_save.tlmn")){
			try {
		    	new ToolChangeDetailsBean();

				save(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block toolChange_yy.tlmn
				e.printStackTrace();
			}
		}
		if (action.equals("toolChange_yy.tlmn")){
			try {
		    	new ToolChangeDetailsBean();

				save(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block toolChange_yy.tlmn
				e.printStackTrace();
			}
		}
		else if (action.equals("toolChangeSummary_save.tlmn")){
			try {
		    	new ToolChangeDetailsBean();

				save(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (action.equals("toolModify_input.tlmn")){
			com.akranta.tpm.utils.CommonFunctions.debugMsg(" in side toolModify_input.tlmn ****"); 
			dispatchUrl = "/pages/ToolMon/ToolChangeModify.jsp";
			UIUtils.forwardRequest(request, response, dispatchUrl);

       }
		
		else if (action.equals("toolModify_getCol.tlmn")){
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"ToolCommonFilter",true);			
			List<String[]> toolList  = toolMoniService.getAllDetailsList( commonFilter);
			//JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,2,0,commonFilter.getTotalRecordCnt()); 
			//httpSession.setAttribute("bdDataServlet", bdData);				
			//String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
			//httpSession.removeAttribute("BdColModel");
			//httpSession.setAttribute("BdColModel", colModel);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);			
			gridColModel.setHeaderNum(1);			
			
			String [] colHeader = toolList.get(0);			
			String [] colHeaderCond = toolList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("TlColModel", jsonObject);
			out.println(jsonObject);
			
        }
		else if (action.equals("toolModify_getData.tlmn")){
			try
			{	
				 PrintWriter out = response.getWriter();
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolCommonFilter",true);
				 com.akranta.tpm.utils.CommonFunctions.debugMsg("Total Record Count : "+commonFilter.getTotalRecordCnt());
				 JSONObject jsonObject = new JSONObject();
				 //if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
	        	// else
	        	 //{				
	        		 List<String []> bdMasterList  = toolMoniService.getAllDetailsList(commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 //}
				
				 out.println(jsonObject);
				 httpSession.removeAttribute("toolCommonFilter");
	  			 httpSession.setAttribute("toolCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				com.akranta.tpm.utils.CommonFunctions.debugMsg(e.getMessage());
			}
		}
		
		else if (action.equals("toolView_input.tlmn")){
			com.akranta.tpm.utils.CommonFunctions.debugMsg(" in side toolView_input.tlmn ****"); 
			request.setAttribute("mode", "View");
			dispatchUrl = "/pages/ToolMon/ToolChangeModify.jsp";
			UIUtils.forwardRequest(request, response, dispatchUrl);

       }
		
		else if (action.equals("toolView_getCol.tlmn")){
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"toolCommonFilter",true);			
			List<String[]> toolList  = toolMoniService.getAllDetailsList( commonFilter);
			JSONObject bdData = UIUtils.convertToJqGridTableObject(toolList,request,2,0,commonFilter.getTotalRecordCnt()); 
			httpSession.setAttribute("bdDataServlet", bdData);				
			//String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
			//httpSession.removeAttribute("BdColModel");
			//httpSession.setAttribute("BdColModel", colModel);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = toolList.get(0);			
			String [] colHeaderCond = toolList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("TlColModel", jsonObject);
			out.println(jsonObject);
			
        }
		else if (action.equals("toolView_getData.tlmn")){
			try
			{	
				 PrintWriter out = response.getWriter();
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolCommonFilter",true);
				 com.akranta.tpm.utils.CommonFunctions.debugMsg("Total Record Count : "+commonFilter.getTotalRecordCnt());
				 JSONObject jsonObject = new JSONObject();
				 //if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
	        	// else
	        	 //{				
	        		 List<String []> bdMasterList  = toolMoniService.getAllDetailsList(commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 //}
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("toolCommonFilter");
	  			 httpSession.setAttribute("toolCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				com.akranta.tpm.utils.CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if(action.equals("toolChange_delete.tlmn")){
			System.out.println("inside delete");
			delete(request,response);
		}
		
		else if(action.equals("tool_Combo.tlmn")){
			try {
				String machId= request.getParameter("machId");
				comboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox>  toolList = toolMoniService.getToolList(machId,comboFilter);
				UIUtils.writeComboBox(response, toolList,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("text_stdCoTime.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				 //currentFilter = new ComboFilter();
				List<String []>  stdTime = toolMoniService.getStdTime(toolId,currentFilter);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size());
				//if(assembly.size()<=1){
				if(stdTime.size()>=0){
				result.put("id", stdTime.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(action.equals("text_estimatedSharpNo.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				 //currentFilter = new ComboFilter();
				List<String []>  estSharp = toolMoniService.getEstSharp(toolId,currentFilter);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size()); getLastChangeDate
				CommonFunctions.debugMsg("estSharpestSharp "+estSharp.size());
				//if(assembly.size()<=1){
				if(estSharp.size()>=0){
				result.put("id", estSharp.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(action.equals("text_lastchangeDate.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				String cellId= request.getParameter("cellId");
				String mchId= request.getParameter("machineId");
				CommonFunctions.debugMsg("machineId  "+mchId);
				 //currentFilter = new ComboFilter();
				List<String []>  changeDate = toolMoniService.getLastChangeDate(toolId,cellId,mchId);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size()); getLastChangeDate
				//if(assembly.size()<=1){
				if(changeDate.size()>=0){
				result.put("id", changeDate.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(action.equals("text_productionlstdate.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				String cellId= request.getParameter("cellId");
				String mchId= request.getParameter("machineId");
				 //currentFilter = new ComboFilter();
				List<String []>  prodLastChange = toolMoniService.getProdLastChange(toolId,cellId,mchId);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size()); getLastChangeDate
				CommonFunctions.debugMsg(" SIZE IN SIDE THE SERVELET"+prodLastChange.size());
				if(prodLastChange.size()>=0){
				result.put("id", prodLastChange.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block 
				e.printStackTrace();
			}
		}
		else if(action.equals("text_stdLife.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				//String cellId= request.getParameter("cellId");
				String mchId= request.getParameter("machineId");
				 //currentFilter = new ComboFilter();
				List<String []>  prodLastChange = toolMoniService.getStdLife(toolId,mchId);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size()); getLastChangeDate
				//if(assembly.size()<=1){
				if(prodLastChange.size()>=0){
				result.put("id", prodLastChange.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block text_stdLife.tlmnsaveToolSrSrpNumber.tlmn
				e.printStackTrace();
			}
		}
		else if(action.equals("text_prevsharp.tlmn")){
			try {
				PrintWriter out= response.getWriter();
				String toolId= request.getParameter("toolId");
				//String cellId= request.getParameter("cellId");
				String mchId= request.getParameter("machineId");
				 //currentFilter = new ComboFilter();
				List<String []>  prvSharp = toolMoniService.getSharpening(toolId,mchId);
				JSONObject result = new JSONObject();
				//com.akranta.tpm.utils.CommonFunctions.debugMsg()("assembly "+stdTime+"  size.. "+stdTime.size()); getLastChangeDate
				//if(assembly.size()<=1){
				if(prvSharp.size()>=0){
				result.put("id", prvSharp.get(0)[0]);
				//result.put("text", stdTime.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block text_stdLife.tlmnsaveToolSrSrpNumber.tlmn
				e.printStackTrace();
			}
		}
		else if(action.equals("changeReason_Combo.tlmn")){
			try {
				request.getParameter("MachineId");
				comboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox>  reasonsList = toolMoniService.getReasons(comboFilter);
				UIUtils.writeComboBox(response, reasonsList,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("functionalLoc.tlmn"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			//functLocFieldNameBean.setFactory("cmbfactory");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setSection("cmbsection");
			functLocFieldNameBean.setCell("cmbcell");
			functLocFieldNameBean.setMachine("cmbmachines");
		//	functLocFieldNameBean.setFunctionalLocId("cmbbdmsFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("ToolChangeFormMode");			
			//if( formModes == FormModes.completion)
			formModes = FormModes.create;
			
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		}
		else if(action.equals("toolSummaryReport_input.tlmn")){

			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String tlView =request.getParameter("selid");	
			httpSession.removeAttribute("tlSumaryRpt");
  			httpSession.setAttribute("tlSumaryRpt", tlView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ToolMon/ToolChangeSummaryRpt.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("toolSummaryReport_getCol.tlmn") )
		{
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter = populateCommonFilter(request,"toolSummaryReport",true);	
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
			commonFilter = FilterValues.getToolsRelated(request, commonFilter);			  
			List<String[]> bdList  = toolMoniService.getAllSumaryRpt( commonFilter);	  
			JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,2,0,commonFilter.getTotalRecordCnt()); 
			httpSession.setAttribute("bdDataServlet", bdData);				
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = bdList.get(0);			
			String [] colHeaderCond = bdList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.setAttribute("TLRptSummaryColModel", jsonObject);
			out.println(jsonObject);
		}
		else if( action.equals("toolSummaryReport_getData.tlmn") )
		{
			try
			{	
				PrintWriter out = response.getWriter();
				
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolSummaryReport",false);
				 JSONObject jsonObject = new JSONObject();
					 List<String []> bdMasterList  = toolMoniService.getAllSumaryRpt(commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("tlSumaryRpt");
	  			 httpSession.setAttribute("tlSumaryRpt", commonFilter);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}
		
		///
		else if( action.equals("toolSummaryReport_getExcel.tlmn"))
		{			
			String filter=request.getParameter("select");
			CommonFunctions.debugMsg(" In side get Exel" +filter);
						
		 httpSession = request.getSession(true);
		//CommonFilter commonFilter = populateCommonFilter(request,"PmComplaince",false);
		 CommonFilter commonFilter = populateCommonFilter(request,"ToolCommonFilter",true);
		String tmpFromRow = "0";//commonFilter.getFromRow();
		//commonFilter.setFromRow("0");
		JSONObject tableModel = JSONObject.fromString(httpSession.getAttribute("TLRptSummaryColModel").toString());
	/*	if(filter=="ToolLife"){
			//JSONObject tableModel = (JSONObject)httpSession.getAttribute("ToolLifeRptColModel");
			 tableModel = JSONObject.fromString(httpSession.getAttribute("ToolMonitoringLf").toString());

		// tableModel = (JSONObject)httpSession.getAttribute("ToolMonitoringLf");
		}
		else{
			 tableModel = JSONObject.fromString(httpSession.getAttribute("ToolMonitoringLf").toString());

			// tableModel = (JSONObject)httpSession.getAttribute("ToolMonitoringSrp");
		} */
		tableModel.put("title", "Tool Summary Report ");
		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = toolMoniService.getAllSumaryRptExportExcel(commonFilter,tableModel,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "ToolSummary", format);
		}
		///
		
		
		else if(action.equals("summaryRpt_input.tlmn")){
			CommonFunctions.debugMsg("In side the summaryRpt_input");
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String tlView =request.getParameter("selid");	
			httpSession.removeAttribute("tlSumaryRpt");
  			httpSession.setAttribute("tlSumaryRpt", tlView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ToolMon/Summary.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("summaryRpt_getCol.tlmn")){
			CommonFunctions.debugMsg("In side the summaryRpt_getCol");
			String type=request.getParameter("type");
			PrintWriter out = response.getWriter();	
		//	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ToolMonitoring", "ToolChangeSummary"));
			
			CommonFilter commonFilter = populateCommonFilter(request,"toolSummary",true);			
			List<String[]> summaryList  = toolMoniService.getSumary(commonFilter,type);	  
			JSONObject bdData = UIUtils.convertToJqGridTableObject(summaryList,request,2,0,commonFilter.getTotalRecordCnt()); 
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			gridColModel.setFormatter("ToolFormater");
			gridColModel.setFormattorFromCol("1");
			gridColModel.setFormattorToCol("1");
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(false);
			
			gridColModel.setHeaderNum(1);			
			String [] colHeader = summaryList.get(0);			
			String [] colHeaderCond = summaryList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "88%%");
			jsonObject.put("tableWidth", "100%%");
			httpSession.setAttribute("TLSummaryColModel", jsonObject);
			out.println(jsonObject);
			
		}
		else if(action.equals("summaryRpt_getData.tlmn")){
			try
			{	
				String type=request.getParameter("type");
				PrintWriter out = response.getWriter();
				
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolSummaryReport",false);
				 JSONObject jsonObject = new JSONObject();
					 List<String []> bdMasterList  = toolMoniService.getSumary(commonFilter,type);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("tlSumaryRpt");
	  			 httpSession.setAttribute("tlSumaryRpt", commonFilter);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		
		}
		
		/////
		else if(action.equals("summarydtls_input.tlmn")){
			CommonFunctions.debugMsg("In side the summaryRpt_input");
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			String tlView =request.getParameter("selid");	
			httpSession.removeAttribute("tlSumaryRpt");
  			httpSession.setAttribute("tlSumaryRpt", tlView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ToolMon/SummaryDetails.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("summarydtls_getCol.tlmn")){
			CommonFunctions.debugMsg("In side the summaryRpt_getCol");
			String type=request.getParameter("type");
			PrintWriter out = response.getWriter();	
		//	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ToolMonitoring", "ToolChangeSummary"));
			
			CommonFilter commonFilter = populateCommonFilter(request,"toolSummary",true);			
			List<String[]> summaryList  = toolMoniService.getSumaryDetails(commonFilter);	  
			JSONObject bdData = UIUtils.convertToJqGridTableObject(summaryList,request,2,0,commonFilter.getTotalRecordCnt()); 
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			//gridColModel.setFormatter("ToolFormater");
			//gridColModel.setFormattorFromCol("1");
			//gridColModel.setFormattorToCol("1");
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(false);
			
			gridColModel.setHeaderNum(1);			
			String [] colHeader = summaryList.get(0);			
			String [] colHeaderCond = summaryList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "88%%");
			jsonObject.put("tableWidth", "100%%");
			httpSession.setAttribute("TLSummaryColModel", jsonObject);
			out.println(jsonObject);
			
		}
		else if(action.equals("summarydtls_getData.tlmn")){
			try
			{	
				String type=request.getParameter("type");
				PrintWriter out = response.getWriter();
				
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolSummaryReport",false);
				 JSONObject jsonObject = new JSONObject();
					 List<String []> bdMasterList  = toolMoniService.getSumaryDetails(commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("tlSumaryRpt");
	  			 httpSession.setAttribute("tlSumaryRpt", commonFilter);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		
		}
		
		/////
		else if(action.equals("summary_input.tlmn")){
			CommonFunctions.debugMsg("In side the summaryRpt_input");
			String filterStr = request.getParameter("filterString");
			String alertType= request.getParameter("alertType");
			request.setAttribute("filterStr", filterStr);
			request.setAttribute("alertType", alertType);
			String tlView =request.getParameter("selid");	
			httpSession.removeAttribute("tlSumaryRpt");
  			httpSession.setAttribute("tlSumaryRpt", tlView);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ToolMon/SummaryGrid.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("summary_getCol.tlmn")){
			CommonFunctions.debugMsg("In side the summary_getCol");
			String type=request.getParameter("type");
			String alertType= request.getParameter("alertType");

			PrintWriter out = response.getWriter();	
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ToolMonitoring", "ToolChangeSummaryGrid"));
			
			CommonFilter commonFilter = populateCommonFilter(request,"toolSummary",true);			
			List<String[]> summaryList  = toolMoniService.getSumaryGrid(commonFilter,type,alertType);	  
			JSONObject bdData = UIUtils.convertToJqGridTableObject(summaryList,request,2,0,commonFilter.getTotalRecordCnt()); 
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
            List<String> formattorList =  new ArrayList<String>();
			
			formattorList.add("ToolFormater");
			formattorList.add("ToolShrpNoFormater");
			formattorList.add("ToolSrlNoFormater");
			formattorList.add("button_SaveBatch");
			List<String> formattorFromList =  new ArrayList<String>();

			formattorFromList.add("1");
			formattorFromList.add("14");
			formattorFromList.add("15");
			formattorFromList.add("16");
			List<String> formattorToList =  new ArrayList<String>();
			formattorToList.add("1");
			formattorToList.add("14");
			formattorToList.add("15");
			formattorToList.add("16");
			
			gridColModel.setMultiformatter(formattorList);
			gridColModel.setMultiformattorFromCol(formattorFromList);
			gridColModel.setMultiformattorToCol(formattorToList);
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(false);
			
			gridColModel.setHeaderNum(1);			
			String [] colHeader = summaryList.get(0);			
			String [] colHeaderCond = summaryList.get(1);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "88%%");
			jsonObject.put("tableWidth", "125%%");
			httpSession.setAttribute("TLSummaryColModel", jsonObject);
			out.println(jsonObject); 
			
		}
		else if(action.equals("summary_getData.tlmn")){
			try
			{	
				CommonFunctions.debugMsg("In side the summary_getdATA");
				String type=request.getParameter("type");
				String alertType= request.getParameter("alertType");

				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg(" type in sdie the sumary"+ type);
				 UIUtils.displayRequestParamsValue(request);
				 request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"toolSummaryReport",false);
				 JSONObject jsonObject = new JSONObject();
					 List<String []> bdMasterList  = toolMoniService.getSumaryGrid(commonFilter,type,alertType);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 out.println(jsonObject);
				 commonFilter.setViewClick('N');	  			 	
	  			 httpSession.removeAttribute("tlSumaryRpt");
	  			 httpSession.setAttribute("tlSumaryRpt", commonFilter);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		
		}
		else if(action.equals("saveToolSrSrpNumber.tlmn")){
			
			saveToolSrAndSrpNo(request,response);
		}
	}

	private void saveToolSrAndSrpNo(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		

		try{
			String toolDetails =request.getParameter("toolDataList");
			//////////
			List<TlmTlAlertBean> toolTlAlertList=null;
			
			TlmTlAlertBean toolTlmTlAlertBean = new TlmTlAlertBean();
			TlmTLAlertDetails extlmTlAlertdetails = new TlmTLAlertDetails();
			TlmTLAlertDetails newTlmTlAlertdetails = new TlmTLAlertDetails();
			//kznTlTeammembersbean =(KznTlTeamMembersBean)UIUtils.setBeanProperties((Object)kznTlTeammembersbean,request);
			extlmTlAlertdetails = (TlmTLAlertDetails)httpSession.getAttribute("newTlmTlAlertdetails");
       
			toolTlmTlAlertBean =(TlmTlAlertBean)UIUtils.setBeanProperties((Object)toolTlmTlAlertBean,request);
       
			JSONArray machineListjson = null;
			boolean insert=false;				
			
		  if(UIUtils.isValidKeyId(toolDetails)){
			CommonFunctions.debugMsg("in if employee List " + toolDetails);				
			machineListjson = JSONArray.fromString(toolDetails);
			CommonFunctions.debugMsg("test ine servelet"+machineListjson+   "     ");
			
			toolTlAlertList=(List<TlmTlAlertBean>)UIUtils.convertJSONArrToList(toolTlmTlAlertBean, machineListjson);
			CommonFunctions.debugMsg("eployee.size()s "+toolTlAlertList.size()+"  "+toolTlAlertList);
			
			if(toolTlAlertList.size()>=0){
			CommonFunctions.debugMsg("insidesapsapres");
			
			toolTlmTlAlertBean.setToolAlert(toolTlAlertList);
			
			} 
		}
		  else{
			  toolTlmTlAlertBean.setToolAlert(null);
		} 
		 insert=true;
		 
		 extlmTlAlertdetails  = toolMoniService.createSerialNo(newTlmTlAlertdetails,extlmTlAlertdetails,toolTlmTlAlertBean);
		 String keyid1=null;
		
		// httpSession.setAttribute("newKznTlTeammembers", existToolTlMachineLink);
		// CommonFunctions.debugMsg(toolKeyid+" keyid " +toolTlMachineLinkBean.getToolId()+" gh "+ httpSession.getAttribute("newKznTlTeammembers").toString() );
		    String msg;
			msg="Data Saved Successfully";
		/*else
			msg="Data Updated Successfully";*/
		JSONObject successData = new JSONObject();
		successData.put("msg",msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		out.print(returnData.toString());
		
}catch(Exception e){
	e.printStackTrace();
	out.print(e.getMessage());
 }
			
			
			
			
			
			
			
		/*	////////////////
			JSONArray toolDetailjson = null;
			List<TlmTLAlertDetails> tlmTLAlertDetailsList  = null;
			CommonFunctions.debugMsg("test =="+toolDetails);

			if(UIUtils.isValidKeyId(toolDetails)){
			
				    toolDetailjson = JSONArray.fromString(toolDetails);
				    tlmTLAlertDetailsList=(List<TlmTLAlertDetails>)UIUtils.convertJSONArrToList(newTlmTlAlertdetails, toolDetailjson);
				
					CommonFunctions.debugMsg("sapTlSparesreplacedList.size=="+tlmTLAlertDetailsList.size());
					
					String updateStatus  = toolMoniService.createSerialNo(tlmTLAlertDetailsList);
					
					  String msg;
						msg="Data Saved Successfully";					
					JSONObject successData = new JSONObject();
					successData.put("msg",msg);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					
					out.print(returnData.toString());
			
			

			}
		}catch(Exception e){
			e.printStackTrace();
			out.print(e.getMessage());
		}
		*/
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
 	    HttpSession httpSession = request.getSession(false);
     	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
 	   	if( httpSession != null && user != null)
 	   	{	
 	     	ToolChangeTlDetails toolTlDtl = new ToolChangeTlDetails();
 	     	toolTlDtl =(ToolChangeTlDetails)UIUtils.setBeanProperties((Object)toolTlDtl,request);
 	   		httpSession.getAttribute("toolFormBean");
		
 	   	    ToolChangeTlDetails existToolTlDtl = (ToolChangeTlDetails)httpSession.getAttribute("toolTlMst"+toolTlDtl.getToolKeyid()); 
 	   		
 	   
 			try
 			{		
 				if(UIUtils.isValidKeyId(toolTlDtl.getToolKeyid()))
 				{
 					    existToolTlDtl =toolMoniService.delete(toolTlDtl);
 						httpSession.removeAttribute("kznTlMst"+existToolTlDtl.getToolKeyid());
 					
 				
 					String delMsg="success-delete"; 					
 					
 					JSONObject successData = new JSONObject();
 					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",delMsg));
 					successData.put("keyid", toolTlDtl.getToolKeyid());
 					//successData.put("frmMode",frmMode);
 					JSONObject returnData = new JSONObject();
 					successData.put("formClear",true);   
 					returnData.put("displyMsg", true);
 					returnData.put("successData", successData);
 					
 					out.print(returnData.toString());
 				}
 			}
 			catch(Exception e)
 			{
 				System.out.println("Error Msg:" + e.getMessage());
 				JSONObject err = new JSONObject();
 				err.put("tpmException", "Data Not Deleted");
 				out.print(err.toString());
 			}
 	   	}	
     } 

	private void initilizeInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
	 {    	
		 
			System.out.println("Find Mode  "+mode);
	        toolDtlBean = new ToolChangeDetailsBean(mode);

	    	ToolChangeTlDetails tooltlDtl = new ToolChangeTlDetails();
	    
	    	httpSession.removeAttribute("fromWO");
	    	httpSession.removeAttribute("WorkOrderGM");
	    	String keyId = request.getParameter("toolKeyid");

	    	String mode1 = request.getParameter("mode");
	    	System.out.println(keyId + "  keyId ");
	    	String fromWO = request.getParameter("backToWO");
	    	String backTo = request.getParameter(WOConstants.backTo);
	    	httpSession.removeAttribute(WOConstants.backTo);
			if(UIUtils.isValidKeyId(backTo))
				httpSession.setAttribute(WOConstants.backTo,backTo);
			String delActivity = "Y";				
			if(UIUtils.isValidKeyId(delActivity))
				request.setAttribute("delActivity","Y");
	    	if(UIUtils.isValidKeyId(fromWO))
	    	{
	    		
	    		httpSession.setAttribute("fromWO", fromWO);
	    	}
	    
		 	
		 	if(mode.equals(FormModes.create)){
		 		AdmTlUsermst user = UIUtils.getLoginUser(request);
				
				System.out.println("input called"+user.getUsrm_ccno());
				tooltlDtl.setCreatedBy(user.getUsrm_ccno());
				
				System.out.println("c called"+tooltlDtl.getCreatedBy());
				request.setAttribute("toolTlDtl",tooltlDtl);
			}
		 	httpSession.removeAttribute("tooltlDtl_Servlet");
		 	if( UIUtils.isValidKeyId(keyId)){
				try {
					
					tooltlDtl = toolMoniService.getFillValue(keyId);
			             
			             httpSession.setAttribute("tooltlDtl_Servlet", tooltlDtl); 
			    		request.setAttribute("toolTlDtl", tooltlDtl);
						
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		 	}
					
		 	httpSession.removeAttribute("tooltlDtlBean");
			httpSession.setAttribute("tooltllBean", toolDtlBean);
			// httpSession.removeAttribute("actionpassed");//clear session of mode stored
			//System.out.println("chk disableForm status  "+generalMaintainance.getDisableForm());
			request.setAttribute("tooltllBean", toolDtlBean);
			
	 }

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			if( commonFilter != null && ! createNew ){
				FilterValues.setPaginationParams(request,commonFilter);
			}	
			else{
				commonFilter =  new CommonFilter();
				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
	

	private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String alertkeyid=request.getParameter("keyId");
    	CommonFunctions.debugMsg(" alertkeyid "+alertkeyid);
    	if( httpSession != null && user != null)
    	{	
    		ToolChangeTlDetails existToolTlDtl = (ToolChangeTlDetails)httpSession.getAttribute("toolTlDtl"); 
    	//	 CommonFunctions.debugMsg("Exist Datas : "+existBdmTlMst.getBdmsKeyid());
    		ToolChangeTlDetails newToolTlDtl = new ToolChangeTlDetails();
	    	ToolChangeDetailsBean toolDtlBean= new ToolChangeDetailsBean();

    		newToolTlDtl.setCreatedBy(user.getUsrm_ccno());
    	
    		newToolTlDtl =(ToolChangeTlDetails)UIUtils.setBeanProperties((Object)newToolTlDtl,request);
    		String saveMsg=null;
    		JSONObject returnData = new JSONObject();
    		try{
    			if(  newToolTlDtl.getToolKeyid() == null )
				{	if(alertkeyid!=null){
					existToolTlDtl =	toolMoniService.create(newToolTlDtl,existToolTlDtl,alertkeyid);
					saveMsg = "Data Saved Successfully";
					returnData.put("formClear",false);
				}
				else
					existToolTlDtl =	toolMoniService.create(newToolTlDtl,existToolTlDtl);
					saveMsg = "Data Saved Successfully";
					returnData.put("formClear",false);
				}	
				else
				{
					existToolTlDtl = toolMoniService.update(newToolTlDtl,existToolTlDtl,toolDtlBean);
					saveMsg = "Data Updated Successfully";
					returnData.put("formClear",false);
				}
				
			//	httpSession.setAttribute(existToolTlDtl.getToolKeyid(), existToolTlDtl);
				httpSession.setAttribute("ToolChangeTlDetails", existToolTlDtl);
				
				//String formBeanIdentifier = "BDFormBean"+toolMstBean.getFormActionMode();					
				//httpSession.setAttribute(formBeanIdentifier,toolMstBean);

				JSONObject successData = new JSONObject(); 
						
				
				//successData.put("keyId", existToolTlDtl.getToolKeyid());
				successData.put("keyId", existToolTlDtl.getToolKeyid());
				successData.put("msg", saveMsg);
				returnData.put("successData",successData);
				
				
				out.print(returnData.toString());
				//out.print()	
				System.out.println("end of save");
					}
    		catch(BusinessApplicationExceptions e){
    			e.printStackTrace();
    			
    		}
    	 catch(ValidationExceptions e)
		{
			
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ToolDtlException");
			errMessage.put("fromMode",toolDtlBean.getFormActionMode());
			out.print(errMessage.toString());
    	  }
    	}
		
	}
		
	
}
