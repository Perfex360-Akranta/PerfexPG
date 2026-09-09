package com.akranta.tpm.controller;
/*
 * Author:Dhanalakshmi.R
 * Date:15-9-2012
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AdmTlAlertsService;
import com.akranta.tpm.service.impl.AddMachineServiceImpl;
import com.akranta.tpm.service.impl.AdmTlAlertsServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class AdmTlAppAlertServlet
 */

public class AdmTlAppAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	AdmTlAlertsService admTlAlertsService;
    public AdmTlAppAlertServlet() {
        super();
        try {
        	//admTlAlertsService = new AdmTlAlertsServiceImpl();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
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
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	   throws Exception{
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		
 	   try {
 		    admTlAlertsService = (AdmTlAlertsServiceImpl)UIUtils.getServiceObject(request,"AdmTlAlertsServiceImpl");
 		    
			CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			admTlAlertsService.AdmTlAlertsServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
 		} catch (ServiceObjectCreationException e) {
 			CommonMessage.debugMsg(e);
 		}
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		String dispatchUrl = null;
		if( action.equals("alerts_input.alerts"))
		{
			httpSession.removeAttribute("dashboard");
			String dashboard = request.getParameter("forDashboard");
			CommonMessage.debugMsg("forDashboard  "+dashboard);
			httpSession.setAttribute("dashboard", dashboard);
			   dispatchUrl="pages/AdmTlAppAlert.jsp"; 	
		}
		if( action.equals("inbox_input.alerts"))
		{
			   dispatchUrl="pages/Gen/approvalsInbox.jsp"; 	
		}
		else if(action.equals("inbox_getCol.alerts")){
			PrintWriter out = response.getWriter();
			 
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.alertsGrid","InboxColMod");
			//JSONObject colModelObj = JSONObject.fromString(colModel) ;
			
			out.println(colModel);
		}else if(action.equals("inbox_getData.alerts")){
			PrintWriter out = response.getWriter();
			List<String[]> reminderData ;
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String fMode = request.getParameter("fmode");
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"inboxCommonFilter",true);
				reminderData =  admTlAlertsService.getApprovalData(user.getUsrm_ccno(),commonFilter);
				JSONObject alertData = new JSONObject();
				if(!UIUtils.isValidKeyId(fMode)) // -- VIGNESH
					alertData =UIUtils.convertToJqGridTableObject(reminderData, request, 2, 1,commonFilter.getTotalRecordCnt());
				else
					alertData.put("tblLength", reminderData.size());
				
				CommonMessage.debugMsg(" reminderData "+reminderData.size());
				
				httpSession.setAttribute("loggedUser",user.getUsrm_ccno());
			 	out.println(alertData.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else if(action.equals("reminder_input.alerts")){
			/*List<String[]> reminderData =  admTlAlertsService.getReminderData();
			JSONArray  retReminderArr = JSONArray.fromCollection(reminderData);
			JSONObject retReminderData = new JSONObject();
			retReminderData.put( "retReminderData" ,retReminderArr);
			PrintWriter out =response.getWriter();
			out.print(retReminderData.toString());*/
			String type=request.getParameter("type");
			request.setAttribute("type",type);
			dispatchUrl="pages/Gen/reminder.jsp";
		}
		else if(action.equals("reminder_getCol.alerts")){
			PrintWriter out = response.getWriter();
			String type=request.getParameter("type");
			if(type==null)
	    	  {
	    		  type="VL";
	    	  }
			CommonMessage.debugMsg("type in get col is"+type);
			
			CommonFilter commonFilter=populateCommonFilter(request,"RemainderCommonFilter" ,true);
			commonFilter.setStatus(type);

			response.setContentType("text/html");
			response.setContentType("text/json");
			 
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.alertsGrid","remiderColMod");
			JSONObject colModelObj = JSONObject.fromString(colModel) ;
			     //colModelObj.getJSONArray("colModel").getJSONObject(1).set("width","310");
				//colModelObj.set("tableWidth","36%%");
			httpSession.setAttribute("reminderColMod",colModelObj);
		    out.println(colModelObj);
		}
		else if(action.equals("reminder_getData.alerts")){
			PrintWriter out = response.getWriter();
			List<String[]> reminderData ;
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			 
			String fMode = request.getParameter("fmode");
			CommonMessage.debugMsg("The fMode"+fMode);
			try {
				
				GridParams gridParams =(GridParams) httpSession.getAttribute(user.getUsrm_ccno()+"RemindergridParams");
				if( gridParams != null)
					CommonMessage.debugMsg("gridParams1=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
				if( gridParams == null )
					gridParams = new GridParams();
				FilterValues.populateGridParams(request,gridParams );
				String type=request.getParameter("type");
				
				if(type==null)
		    	  {
		    		  type="VL";
		    	  }
				CommonMessage.debugMsg("type gd" +type);
				CommonFilter commonFilter=populateCommonFilter(request,"RemainderCommonFilter",false);
				commonFilter.setStatus(type);
				//CommonFilter commonFilter=new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				CommonMessage.debugMsg("getcol"); 
				reminderData =  admTlAlertsService.getReminderData(user.getUsrm_ccno(),gridParams,commonFilter);
				/*int	rowCount = reminderData.size();
				long totalCnt = (long)rowCount;
				gridParams.setTotalRecordCnt(totalCnt);*/
				JSONObject alertData = new JSONObject();
				
				if(!UIUtils.isValidKeyId(fMode))
					
					alertData =UIUtils.convertToJqGridTableObject(reminderData, request,0, 0,gridParams.getTotalRecordCnt());
		     	else
				alertData.put("tblLength", reminderData.size());
				httpSession.setAttribute("loggedUser",user.getUsrm_ccno());
				httpSession.removeAttribute(user.getUsrm_ccno()+"RemindergridParams");
				httpSession.setAttribute(user.getUsrm_ccno()+"RemindergridParams" ,gridParams);
				httpSession.removeAttribute("EquipmentCommonFilter");
				httpSession.setAttribute("EquipmentCommonFilter" , gridParams);
				httpSession.removeAttribute("totalCnt");
			   // httpSession.setAttribute("totalCnt" , rowCount);
			 out.println(alertData.toString());  
			 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		else if(action.equals("reminder_getExcel.alerts")){
			CommonFilter commonFilter  = new CommonFilter();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String type=request.getParameter("type");
			if(type==null)
	    	  {
	    		  type="VL";
	    	  }
			commonFilter.setStatus(type);
			CommonMessage.debugMsg("type in eXCEL OF rEM  is"+type);
			GridParams gridParams =(GridParams) httpSession.getAttribute(user.getUsrm_ccno()+"RemindergridParams");
			if( gridParams != null)
				CommonMessage.debugMsg("gridParams1=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
			if( gridParams == null )
				gridParams = new GridParams();
			
			FilterValues.populateGridParams(request,gridParams );
			FilterValues.getCommonFilters(request, commonFilter);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("reminderColMod");
		//JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Reminder");
			String loggedUser = (String) httpSession.getAttribute("loggedUser");
			commonFilter.setEmpch(loggedUser);
			CommonMessage.debugMsg(" reminderData ");
            String format = ExcelUtils.getFormat(request);	
		
			Workbook wb = admTlAlertsService.getReminderExcel(colmodel,gridParams,format,commonFilter,loggedUser );
			ExcelUtils.writeToResponse(response, wb, "Reminder", format);
		}
		/*else if(action.equals("reminder_getExcel.alerts")){
			CommonFilter commonFilter  = new CommonFilter();
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("reminderColMod");
			colmodel.put("title","Reminder");
			String loggedUser = (String) httpSession.getAttribute("loggedUser");
			commonFilter.setEmpch(loggedUser);
            String format = ExcelUtils.getFormat(request);	
			Workbook wb = admTlAlertsService.getReminderExcel(colmodel,format,commonFilter );
			ExcelUtils.writeToResponse(response, wb, "Reminder", format);
		}*/
   else if(action.equals("alerts_getCol.alerts"))
{
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	response.setContentType("text/json");
	String dashboard =(String)httpSession.getAttribute("dashboard");
	CommonMessage.debugMsg("GetCol  dashboard  "+dashboard);
	String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.alertsGrid","alertGrid");
	JSONObject colModelObj = JSONObject.fromString(colModel) ;
	if("true".equals(dashboard)){
		CommonMessage.debugMsg("GetCol  Height");
		colModelObj.getJSONArray("colModel").getJSONObject(1).set("width","310");
		colModelObj.set("tableWidth","36%%");
	}
    out.println(colModelObj);
    
	
}
else if(action.equals("alerts_getData.alerts"))
{
	
	PrintWriter out = response.getWriter();
		
	
		List<String[]> alertList;
		try {
			alertList = admTlAlertsService.getAlertGrid();
			
			JSONObject alertData = UIUtils.convertToJqGridTableObject(alertList, request, 0, 0 );
			
		 	out.println(alertData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
		else if(action.equals("alertsRes_input.alerts"))	
	{
		
	}
 	
	 else if(action.equals("alertsRes_getCol.alerts"))
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			
			List<String[]> alertList;
			String keyId=request.getParameter("keyId");
			httpSession.removeAttribute("KeyId");
			httpSession.setAttribute("KeyId", keyId);
			
			
			try
			{
			GridParams gridParams ;
			
				gridParams = new GridParams();
			
			
			alertList = admTlAlertsService.getAlertResGrid(keyId,gridParams);
			
			CommonMessage.debugMsg("Count,......"+gridParams.getTotalRecordCnt());
			
			
			
			 
				 JSONObject colModel = getTableModel(alertList);
				 colModel.set ("tableWidth","80%%");
				
				 httpSession.removeAttribute("AlertsColmodel");
				   httpSession.setAttribute("AlertsColmodel",colModel);
				 out.println(colModel);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				if( e.getMessage() !=null )
	    	    {
					
	    	    	
					JSONObject successData = new JSONObject();
					
	    	    	
	    	    	 String sucessmsg = "Data not Available";
	    	    	
	    	    	 successData.put("noData",true );
	    	    	 successData.put("noDataMsg",sucessmsg );
	    	    	
	    	    	 out.println(successData.toString());	
	    	    	return;
	    	    }
			
				CommonMessage.debugMsg(e.getMessage());
			}
				
			/*catch(SQLSyntaxErrorException ssl)
			{
				
			}*/
		}
	 else if(action.equals("alertsRes_getData.alerts"))
		{
			CommonMessage.debugMsg("inside getdata");
			PrintWriter out = response.getWriter();
				
			String keyId=request.getParameter("keyId");
			CommonMessage.debugMsg("Name:=>"+keyId);
			
				List<String[]> alertResList;
				try {
					GridParams gridParams =(GridParams) httpSession.getAttribute(keyId+"gridParams");
					if( gridParams != null)
						CommonMessage.debugMsg("gridParams1=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
					if( gridParams == null )
						gridParams = new GridParams();
					
					//JSONObject tableModel = (JSONObject)httpSession.getAttribute(session_ident_masterTblConfColModel+menuId);
					
					FilterValues.populateGridParams(request,gridParams );
					alertResList = admTlAlertsService.getAlertResGrid(keyId,gridParams);
					
					CommonMessage.debugMsg("gridParams2=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
					httpSession.removeAttribute(keyId+"gridParams");
					//CommonMessage.debugMsg("gridParams3=>"+gridParams);
					httpSession.setAttribute(keyId+"gridParams" ,gridParams);
					//CommonMessage.debugMsg("gridParams4=>"+gridParams);
					CommonMessage.debugMsg("alertList:"+alertResList);
					JSONObject alertResData = UIUtils.convertToJqGridTableObject(alertResList, request, 1, 0,gridParams.getTotalRecordCnt()+1);
					
				 	out.println(alertResData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					CommonMessage.debugMsg(e.getMessage());
					JSONObject successData = new JSONObject();
					
	   	    	 CommonMessage.debugMsg("noData");
	   	    	 String sucessmsg = "noData";
	   	    	successData.put("msg",sucessmsg );
	   	    	 out.println(successData);	
	   	    	return;
				}
				}
	 else if( action.equals("alertsRes_getExcel.alerts")){
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("AlertsColmodel");
			CommonMessage.debugMsg("Inside get excel action:colmodel= "+colmodel);
			String format = ExcelUtils.getFormat(request);
			CommonMessage.debugMsg("format="+format);
			String keyId=(String)httpSession.getAttribute("KeyId");
			Workbook wb;
			try {
				GridParams gridParams =(GridParams) httpSession.getAttribute(keyId+"gridParams");
				if( gridParams != null)
					CommonMessage.debugMsg("gridParams1=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
				if( gridParams == null )
					gridParams = new GridParams();
				wb = admTlAlertsService.AlertsExportExcel(colmodel,format,gridParams,keyId);
				httpSession.removeAttribute(keyId+"gridParams");
				//CommonMessage.debugMsg("gridParams3=>"+gridParams);
				httpSession.setAttribute(keyId+"gridParams" ,gridParams);
				ExcelUtils.writeToResponse(response, wb, "AlertsReport", format);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if (dispatchUrl != null)
		{
		   CommonMessage.debugMsg("dispatch.........."+dispatchUrl);
			UIUtils.forwardRequest(request, response, dispatchUrl);
		}
	} 
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
  		
  		
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
  		if( commonFilter != null && ! createNew ){
  			FilterValues.setPaginationParams(request,commonFilter);
  		}	
  		else{
  			commonFilter =  new CommonFilter();
  			
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
  			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		
  		String loginFlid = CommonFunctions.getLoginFlid(request);
		if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
			commonFilter.setFlid(loginFlid);
		
  		return commonFilter;
	}
	private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);
		CommonMessage.debugMsg("dd");
		CommonMessage.debugMsg("klist s  :"+headers.get(0)[1]);
		CommonMessage.debugMsg(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);		
		CommonMessage.debugMsg("header..."+colHeader[2]);
		
		for(int i =0; i < colHeader.length; i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridTableModel.setTableButton(true);		
				
			jqGridColModel.setWidth(140);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			if(i==0|| i==1 || i==6|| i==7|| i==8 )			
				jqGridColModel.setHidden(true);			
			if(i==5)			
				jqGridColModel.setWidth(240);
			
			CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);		 
		 
		 tableModel.set("tableHeight", "80%%");
		 return tableModel;
	}
	
}
