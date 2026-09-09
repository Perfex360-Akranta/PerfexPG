
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.SapTlMaintenanceorder;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BreakdownService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.WhyWhyAnalysisService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.AbnormalityFormServiceImpl;
import com.akranta.tpm.service.impl.BreakdownServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.WOConstants;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;

	//Created By Suresh.K On Dec 3 2011
	public class BreakdownServlet  extends HttpServlet  {

		
		private static final long serialVersionUID = 1L;
		BreakdownService breakDownService ;
		WhyWhyAnalysisService yyService;
		WorkOrderService workOrderService;
		CommonFilterService commonFilterService;
		//CommonFilter commonFilter;		
		//private String comTxt = null;
		//private String comBdNo = null;
		//private String comEnteredBy = null;
		
		public BreakdownServlet()
		{
			super();	       
	        /*try {
	        	breakDownService = new BreakdownServiceImpl();
	        	yyService = new WhyWhyAnalysisServiceImpl();
	        	workOrderService = new WorkOrderServiceImpl();
				commonFilterService = new CommonFilterServiceImpl();
				//commonFilter = new CommonFilter();
			} catch (Exception e) {
				CommonMessage.debugMsg("Constructor : "+e.toString());
			}
			*/
			//Constructor
		}
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
		{ 
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
		{
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			
			HttpSession httpSession = request.getSession(false);
			
			/*  uri is in this form: /contextName/resourceName, * for example: /app01a/Product_input.action. * However, in the case of a default context, the * context name is empty, and uri has this form * /resourceName, e.g.: /Product_input.action */
			 
			String action = UIUtils.getActionPart(request);
			CommonMessage.debugMsg("action:::in BreakDown::::::"+action);
			response.setContentType("text/html");
			response.setContentType("text/json");
			ComboFilter comboFilter = new ComboFilter();
//			String createdOn = null;
			
			try {
				breakDownService = (BreakdownServiceImpl)UIUtils.getServiceObject(request,"BreakdownServiceImpl");
				workOrderService = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
				commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			
				yyService = (WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl");
				
				CommonMessage.debugMsg("  breakDownService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
				//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
				breakDownService.BreakdownServiceImplJwt(
							   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
							);
				
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			
			if( action.equals("filterXmlBreakdown_view.brdn")){
				response.setContentType("xml");		
				UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
			}
			else if (action.equals("Breakdown_view.brdn")) 
			{ 
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("Breakdown_getCol.brdn") )
			{
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);			
				List<String[]> bdList  = breakDownService.getAllBD( commonFilter);
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonFilter.getTotalRecordCnt()); 
				httpSession.setAttribute("bdDataServlet", bdData);				
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
				httpSession.removeAttribute("BdColModel");
				//httpSession.setAttribute("BdColModel", colModel);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = bdList.get(0);			
				String [] colHeaderCond = bdList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "74%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("BdColModel", jsonObject);
				out.println(jsonObject);
				//out.println(colModel);
			}
			
	
			else if( action.equals("Breakdown_getData.brdn") )
			{
				try
				{	
					 PrintWriter out = response.getWriter();
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
					 CommonMessage.debugMsg("Total Record Count : "+commonFilter.getTotalRecordCnt());
					 JSONObject jsonObject = new JSONObject();
					 //if(page.equals("1"))
		        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
		        	// else
		        	 //{				
		        		 List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
		        	 //}
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownCommonFilter");
		  			 httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
					/* Updated 20-Mar-2012
					CommonFilter commonFilter  = new CommonFilter();				
					commonFilter= (CommonFilter) httpSession.getAttribute("comFil");					
					List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
					CommonMessage.debugMsg(bdMasterList.size());
	  			 	JSONObject bdMasterData = UIUtils.convertToJqGridTableObject(bdMasterList,request,0,0);
	  			 	CommonMessage.debugMsg(bdMasterData);
	  			 	out.println(bdMasterData);*/
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
		if( action.equals("filterXmlbrkdown_view.brdn")){
				response.setContentType("xml");		
				UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
			}
			else if (action.equals("brkdown_view.brdn")) 
			{ 
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				String bdView =request.getParameter("selid");	
				httpSession.removeAttribute("BrkDownDownTimeView");
	  			httpSession.setAttribute("BrkDownDownTimeView", bdView);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp"); 
				rd.forward(request, response); 
				
			}
			else if( action.equals("brkdown_getCol.brdn") )
			{
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
				String keyId = request.getParameter("keyId");
				String keyID = request.getParameter("keyID");							
				if(UIUtils.isValidKeyId(keyId)){	
					keyId = keyId.substring(0,10);	
					ComboFilter cmbRootCause = new ComboFilter();
					if( UIUtils.isValidKeyId(keyId))
						cmbRootCause.setId(keyId);
					if(cmbRootCause != null )
						commonFilter.setCmbbdRootCause(cmbRootCause);
				}
				
				if(UIUtils.isValidKeyId(keyID)){
					keyID = keyID.substring(0,keyID.length()-4);
					commonFilter.setMainkeyid(keyID);
				}
				
				String bdView =  (String) httpSession.getAttribute("BrkDownDownTimeView");
				
		 	if(UIUtils.isValidKeyId(bdView)){
			 	if ( !( bdView.length()>8)){
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						 commonFilter.setFromDate("01-" + bdView);
						 String lastDay = CommonFunctions.getLastDayOfMonth("27-"+bdView);
						 commonFilter.setToDate(lastDay);
				 	  }
			 	}else{
			 		if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(bdView);
						  commonFilter.setToDate(bdView);
				
				 	  }
			 	}
			} 
			     
				List<String[]> bdList  = breakDownService.getAllBD( commonFilter);	  
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonFilter.getTotalRecordCnt()); 

				httpSession.setAttribute("bdDataServlet", bdData);				
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
				httpSession.removeAttribute("BdColModel");
				//httpSession.setAttribute("BdColModel", colModel);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = bdList.get(0);			
				String [] colHeaderCond = bdList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "78%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("BdColModel", jsonObject);
				out.println(jsonObject);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
			}
			
			
		
			else if( action.equals("brkdown_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
					 JSONObject jsonObject = new JSONObject();
					 //if(page.equals("1"))
		        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
		        	// else
		        	// {				
		        		 List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
		        	// }
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownCommonFilter");
		  			 httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("brkdown_getExcel.brdn")){
				exportToExcel(request,response,"BrkDownCommonFilter","View");
			}
			
			else if( action.equals("filterXmlBDViewRpt_input.brdn")){
				response.setContentType("xml");		
				UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
			}
			
			else if (action.equals("BDViewRpt_input.brdn")) 
			{ 
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				String bdView =request.getParameter("selid");	
				httpSession.removeAttribute("BrkDownDownTimeView");
	  			httpSession.setAttribute("BrkDownDownTimeView", bdView);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp"); 
				rd.forward(request, response); 
				
			}
			else if (action.equals("bdpoorskill_input.brdn")) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakDownNew.jsp"); 
				rd.forward(request, response); 
			}
			
			else if( action.equals("BDViewRpt_getCol.brdn") )
			{
				PrintWriter out = response.getWriter();			
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);				
				String bdView =  (String) httpSession.getAttribute("BrkDownDownTimeView");				
		 	if(UIUtils.isValidKeyId(bdView)){
			 	if ( !( bdView.length()>8)){
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			  
			 			 commonFilter.setFromDate("01-" + bdView);
						 String lastDay = CommonFunctions.getLastDayOfMonth("27-"+bdView);
						 commonFilter.setToDate(lastDay);
				 	  }
			 	}else{
			 		if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(bdView);
						  commonFilter.setToDate(bdView);
						}
			 	}
			} 
			     
				List<String[]> bdList  = breakDownService.getAllBD( commonFilter);	  
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,2,0,commonFilter.getTotalRecordCnt()); 
				httpSession.setAttribute("bdDataServlet", bdData);				
				/*String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModelNew");
				httpSession.removeAttribute("BdColModel");
				httpSession.setAttribute("BdColModel", colModel);	
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));*/
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = bdList.get(0);			
				String [] colHeaderCond = bdList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "80%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("BdColModel", jsonObject);
				out.println(jsonObject);
			}
			
			else if (action.equals("bdpoorskill_getCol.brdn")) 
			{
				PrintWriter out = response.getWriter();
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModelNew");
				out.println(colModel);
			}
			else if( action.equals("BDViewRpt_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
					 JSONObject jsonObject = new JSONObject();
					 if(page.equals("1"))
		        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
		        	 else
		        	 {				
		        		 List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
		        	 }
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownCommonFilter");
		  			 httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if (action.equals("bdpoorskill_getData.brdn")) 
			{
				try
				{	
					CommonFilter commonFilter = populateCommonFilter(request,"SparesMasterCommonFilter",false);
					PrintWriter out = response.getWriter();
					List<String []> sprMasterList  = breakDownService.getAllBreakdownnew(commonFilter);	
					
	  			 	JSONObject sprMasterData = UIUtils.convertToJqGridTableObject(sprMasterList,request,0,0,commonFilter.getTotalRecordCnt());
	  			 	out.println(sprMasterData);
					
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("BDViewRpt_getExcel.brdn")){
				exportToExcel(request,response,"BrkDownCommonFilter","View");
			}
			
			
			
			else if (action.equals("Breakdown_getExcel.brdn")){
				exportToExcel(request,response,"BrkDownCommonFilter","Modification");
			}
			
			else if( action.equals("filterXmlUPM_view.brdn")){
				response.setContentType("xml");		
				UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
			}
			else if (action.equals("UPM_view.brdn")) 
			{ 
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("UPM_getCol.brdn") )
			{
				String maintMode = request.getParameter("maintMode");
				
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
				if(UIUtils.isValidKeyId(maintMode))
					commonFilter.setMaintMode(maintMode);
				CommonMessage.debugMsg(breakDownService +"BD Service");
				List<String[]> bdList  = breakDownService.getAllBD( commonFilter);
				CommonMessage.debugMsg("Size : "+bdList.size());
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonFilter.getTotalRecordCnt()); 
				
				CommonMessage.debugMsg(bdData);
				httpSession.setAttribute("bdDataServlet", bdData);				
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
				JSONObject colModelObj = JSONObject.fromString(colModel);
				colModelObj.getJSONArray("rowHeaders").getJSONArray(0).put(0, "UD NO");
				httpSession.removeAttribute("BdColModel");
				httpSession.setAttribute("BdColModel", colModel);	
				out.println(colModelObj);
			}
			else if( action.equals("UPM_getData.brdn") )
			{
				try
				{	
					String maintMode = request.getParameter("maintMode");
					 PrintWriter out = response.getWriter();
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
					 CommonMessage.debugMsg("Total Record Count : "+commonFilter.getTotalRecordCnt());
					 JSONObject jsonObject = new JSONObject();
					 if(page.equals("1"))
		        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
		        	 else
		        	 {		
		        		 if(UIUtils.isValidKeyId(maintMode))
		 					commonFilter.setMaintMode(maintMode);
		        		 List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,0,1,commonFilter.getTotalRecordCnt()); 
		        	 }
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownCommonFilter");
		  			 httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("filterXmlunplanned_view.brdn")){
				response.setContentType("xml");		
				UIUtils.forwardRequest(request, response, "/tiles/xml/bd.xml");
			}
			
			else if (action.equals("unplanned_view.brdn")) 
			{ 
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				String bdView =request.getParameter("selid");	
				CommonMessage.debugMsg("SbdViewbdViewbdViewbdView e : "+bdView);
				httpSession.removeAttribute("BrkDownDownTimeView");
	  			httpSession.setAttribute("BrkDownDownTimeView", bdView);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/breakdownmaster.jsp"); 
				rd.forward(request, response); 
				
			}
			else if( action.equals("unplanned_getCol.brdn") )
			{
				PrintWriter out = response.getWriter();
				String maintMode = request.getParameter("maintMode");
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
				if(UIUtils.isValidKeyId(maintMode))
					commonFilter.setMaintMode(maintMode);
				
				 String bdView =  (String) httpSession.getAttribute("BrkDownDownTimeView");
				
			if(UIUtils.isValidKeyId(bdView)){
			 	if ( !( bdView.length()>8)){
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			 			 commonFilter.setFromDate("01-" + bdView);
						 String lastDay = CommonFunctions.getLastDayOfMonth("27-"+bdView);
						 CommonMessage.debugMsg("lastDay :: "+lastDay );
						 commonFilter.setToDate(lastDay);
				 	  }
			 	}else{
			 		if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(bdView);
						  commonFilter.setToDate(bdView);
				 	  }
			 	}
			} 
			     
				List<String[]> bdList  = breakDownService.getAllBD( commonFilter);	  
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,0,1,commonFilter.getTotalRecordCnt()); 
				CommonMessage.debugMsg(bdData);
				httpSession.setAttribute("bdDataServlet", bdData);				
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel");
				httpSession.removeAttribute("BdColModel");
				httpSession.setAttribute("BdColModel", colModel);	
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel", "colModel"));
			}
			else if( action.equals("unplanned_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					String maintMode = request.getParameter("maintMode");
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",true);
					 JSONObject jsonObject = new JSONObject();
					 if(page.equals("1"))
		        		 jsonObject = (JSONObject) httpSession.getAttribute("bdDataServlet");
		        	 else
		        	 {
		        		 if(UIUtils.isValidKeyId(maintMode))
		 					commonFilter.setMaintMode(maintMode);
		        		 List<String []> bdMasterList  = breakDownService.getAllBD(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,0,1,commonFilter.getTotalRecordCnt()); 
		        	 }
					
					 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownCommonFilter");
		  			 httpSession.setAttribute("BrkDownCommonFilter", commonFilter);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("downtime_getCol.brdn") )
			{
				httpSession.setAttribute("factId", request.getParameter("factId"));
				httpSession.setAttribute("fromTime", request.getParameter("fromTime"));
				httpSession.setAttribute("toTime", request.getParameter("toTime"));
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DownTimeColModel", "colModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.DownTimeColModel", "colModel"));
			}
			else if( action.equals("downtime_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					List<String > paramValues = new ArrayList<String>();					
					paramValues.add((String) httpSession.getAttribute("fromTime"));
					paramValues.add((String) httpSession.getAttribute("toTime"));
					paramValues.add((String) httpSession.getAttribute("factId"));
					List<String []> downTimeList  = breakDownService.getDownTime(paramValues);
					JSONObject downTimeData = UIUtils.convertToJqGridTableObject(downTimeList,request,0,0);
	  			 	CommonMessage.debugMsg(downTimeData);
	  			 	out.println(downTimeData);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}	
			else if (action.equals("comm_Text.brdn")) 
			{
				String commMode = request.getParameter("mode");
				String formMode = request.getParameter("formMode");		
				String workorderNo = request.getParameter("workorderNo");
				String employeeId  = request.getParameter("employeeId");
				if(UIUtils.isValidKeyId(employeeId))
					request.setAttribute("employeeId", employeeId);
				if(UIUtils.isValidKeyId(workorderNo))
					request.setAttribute("workorderNo", workorderNo);
				if(UIUtils.isValidKeyId(formMode))
					request.setAttribute("formModeFlag", formMode);
				if(UIUtils.isValidKeyId(commMode))
					request.setAttribute("commMode", commMode);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/communicationtext.jsp"); 
				rd.forward(request, response);
			}
			else if( action.equals("comm_getCol.brdn") )
			{
				httpSession.setAttribute("bdId", request.getParameter("bdId"));	
				String formName = request.getParameter("formName");
				PrintWriter out = response.getWriter();
				if(UIUtils.isValidKeyId(formName))
				{
					if(formName.equals("WO"))
						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "woColModel"));
						
				}
				else
				{
					CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
				}
			
			}
			else if( action.equals("comm_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					List<String []> commList  = breakDownService.getCommText((String) httpSession.getAttribute("bdId"));
					JSONObject commData = UIUtils.convertToJqGridTableObject(commList,request,0,0);
					CommonMessage.debugMsg(commData);
					out.println(commData);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		
			else if( action.equals("comm_save.brdn") )
			{
				httpSession.setAttribute("comTxt", request.getParameter("comTxt"));
				httpSession.setAttribute("comBy", request.getParameter("comBy"));
				httpSession.setAttribute("comWoNo", request.getParameter("woNo"));
				//comTxt = request.getParameter("comTxt");
				//comBdNo = request.getParameter("comBy");
				//comEnteredBy = request.getParameter("bdNo");
				CommonMessage.debugMsg("inside CommunicAtion "+request.getParameter("comTxt"));
				BDFormBean bdFormBean = new  BDFormBean();
				saveCom(request,response,bdFormBean);
			}
			else if( action.equals("comm_delete.brdn") )
			{
				/*httpSession.setAttribute("comTxt", request.getParameter("comTxt"));
				httpSession.setAttribute("comBy", request.getParameter("comBy"));
				httpSession.setAttribute("comWoNo", request.getParameter("woNo"));
				*///comTxt = request.getParameter("comTxt");
				//comBdNo = request.getParameter("comBy");
				//comEnteredBy = request.getParameter("bdNo");
				CommonMessage.debugMsg("inside CommunicAtion "+request.getParameter("commKeyID"));
				
				deleteCom(request,response);
			}
			else if (action.equals("why_why.brdn")) 
			{
				String commMode = request.getParameter("mode");
				String bdKey = request.getParameter("bdKey");
				String formMode = request.getParameter("formMode");
				if(UIUtils.isValidKeyId(commMode))
					request.setAttribute("whywhyMode", commMode);
				if(UIUtils.isValidKeyId(formMode))
					request.setAttribute("formModeFlag", formMode);
				if(UIUtils.isValidKeyId(bdKey))
				{
					BdmTlDtl bdmTlDtl = new BdmTlDtl();
					if(bdKey.substring(0, 1).equals("U"))
					{
						PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = breakDownService.selectUPMDetail(bdKey);
						bdmTlDtl = (BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);
					}
					else
						bdmTlDtl = breakDownService.selectBd(bdKey);
						request.setAttribute("bdmTlDtl", bdmTlDtl);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/whywhyform.jsp"); 
				rd.forward(request, response);
			}
			else if( action.equals("yy_getCol.brdn") )
			{
				String wwNo = request.getParameter("wwNo");
				httpSession.removeAttribute("wwNo");
				if(UIUtils.isValidKeyId(wwNo))
				httpSession.setAttribute("wwNo", wwNo);				
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "bdYYColModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "bdYYColModel"));
			
			}
			else if( action.equals("yy_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					List<String []> yyList  = breakDownService.getYY((String) httpSession.getAttribute("wwNo"));
					JSONObject yyData = UIUtils.convertToJqGridTableObject(yyList,request,0,0);	  			 
	  			 	out.println(yyData);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("setrc_values.brdn") )
			{
				PrintWriter out = response.getWriter();
				List<String[]> rcInBdYYList = yyService.getSelectedRootCause((String) httpSession.getAttribute("wwNo"));
				
				JSONObject rcJSON = new JSONObject();
				rcJSON.put("rcId", rcInBdYYList.get(0)[0]);
				rcJSON.put("isJH", rcInBdYYList.get(0)[2]);
				rcJSON.put("isPM", rcInBdYYList.get(0)[3]);
				rcJSON.put("isCI", rcInBdYYList.get(0)[4]);
				rcJSON.put("isET", rcInBdYYList.get(0)[5]);
				out.println(rcJSON);
			}
			else if( action.equals("rootcause_getCol.brdn") )
			{
				//httpSession.setAttribute("wwNo", request.getParameter("wwNo"));				
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "RootcolModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "RootcolModel"));
			
			}
			else if( action.equals("rootcause_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					//List<String []> yyList  = WhywhyReportServlet.setRootCauseDatas();
					CommonMessage.debugMsg("Root Cause YY NO : "+(String) httpSession.getAttribute("wwNo"));
					List<String []> rootCauseList  = breakDownService.getRootCause((String) httpSession.getAttribute("wwNo"));
					//List<String[]> rcInBdYYList = yyService.getSelectedRootCause((String) httpSession.getAttribute("wwNo"));
					//setRootCauseValues(rcInBdYYList,request);
					/*request.setAttribute("rcId", rcInBdYYList.get(0)[0]);
					request.setAttribute("isJH", rcInBdYYList.get(0)[2]);
					request.setAttribute("isPM", rcInBdYYList.get(0)[3]);
					request.setAttribute("isCI", rcInBdYYList.get(0)[4]);
					request.setAttribute("isET", rcInBdYYList.get(0)[5]);
					CommonMessage.debugMsg(rcInBdYYList.get(0)[0]);
					CommonMessage.debugMsg(rcInBdYYList.get(0)[2]);
					CommonMessage.debugMsg(rcInBdYYList.get(0)[3]);
					CommonMessage.debugMsg(rcInBdYYList.get(0)[4]);
					CommonMessage.debugMsg(rcInBdYYList.get(0)[5]);*/
				
					JSONObject rootCauseData = UIUtils.convertToJqGridTableObject(rootCauseList,request,0,0);	 
					CommonMessage.debugMsg("rootCauseData : "+rootCauseData);
	  			 	out.println(rootCauseData);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("commtxt_view.brdn")){
				httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
				request.setAttribute("breakdownId", request.getParameter("bdId"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/communication.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("commtxt_getCol.brdn") )
			{
				
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModel"));
			
			}
			else if( action.equals("commtxt_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					List<String []> commList  = breakDownService.getCommText((String) httpSession.getAttribute("breakdownId"));
					JSONObject commData = UIUtils.convertToJqGridTableObject(commList,request,0,0);
					out.println(commData);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if (action.equals("check_BDclassfcn.brdn"))
			{
				String pillar = request.getParameter("pillar");
				String classificationId = request.getParameter("classificationId");
				String msg = "";
				PrintWriter out = response.getWriter();
				if(UIUtils.isValidKeyId(pillar))
				{
					msg = "Selected BD classification is not related to Pillar which is selected in Why-Why.";
					List<String []> commList  = breakDownService.getPillarClassfcn(pillar);
					for(int i=0;i<commList.size();i++)
					{
						CommonMessage.debugMsg(i + "...--------..."+commList.get(i)[0]);
						if(classificationId.equals(commList.get(i)[0]))
								msg = "";
					}
					JSONObject validMsg = new JSONObject();
					validMsg.put("msg", msg);
					out.println(validMsg);
				}
				
				
			}
			else if (action.equals("Breakdown_input.brdn")) 
			{ 
				
				String keyid = request.getParameter("BDKeyid");
				String activity = request.getParameter("activity");
				String woKey = request.getParameter("WOID");
				String backTo = request.getParameter(WOConstants.backTo);
				httpSession.removeAttribute(WOConstants.backTo);
				if(UIUtils.isValidKeyId(backTo))
					httpSession.setAttribute(WOConstants.backTo,backTo);
				
				String delActivity = request.getParameter("delActivity");				
				if(UIUtils.isValidKeyId(delActivity))
					request.setAttribute("delActivity","Y");
				FormModes mode = FormModes.create;	
				BdmTlMst bdmTlMst = new BdmTlMst();
				BdmTlDtl bdmTlDtl = new BdmTlDtl();
				if(UIUtils.isValidKeyId(woKey))
		    	{
		    		WomTlWomst womTlWomst = workOrderService.select(woKey);
		    		httpSession.setAttribute("WorkOrderBD", womTlWomst);
		    		request.setAttribute("WorkOrderBDID", woKey);
		    	}
				UIUtils.displayRequestParamsValue(request);
				response.setContentType("text/html");
				if(UIUtils.isValidKeyId(keyid))
				{
					if(!UIUtils.isValidKeyId(woKey))
			    	{
						mode=FormModes.view;
						
			    	}
					BDFormBean bdFormBean = new  BDFormBean(mode);
					PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();
					PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
				
					if(UIUtils.isValidKeyId(activity))
					{
						CommonMessage.debugMsg("activity "+activity);
						if(activity.equals("BD")||activity.equals("B"))
						{
							bdmTlMst = breakDownService.select(keyid);					
							bdmTlDtl = breakDownService.selectBd(keyid);
							CommonMessage.debugMsg("Machine Id "+bdmTlMst.getBdmsMachineid());
						}
						else if(activity.equals("U"))
						{
							plmTlUnplannedmaintmst = breakDownService.selectUPM(keyid);					
							plmTlUnplannedmaintdtl = breakDownService.selectUPMDetail(keyid);
							bdmTlMst = (BdmTlMst) UIUtils.copyObject(plmTlUnplannedmaintmst, bdmTlMst);
							bdmTlDtl = (BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);
						}
					
						bdFormBean.setDisablebdmsMachineid(true);
						bdFormBean.setDisablebdanCostcentre(true);
						httpSession.setAttribute("fromWorkorderDisableFunloc", "true");
					}
					if( "MLD".equals(bdmTlMst.getBdmsRelatedto()) && ! UIUtils.isValidKeyId(bdmTlMst.getBdmsMould()) )
					{
						CommonMessage.debugMsg(" bdFormBean.getDisablebdmsMould() " +  bdFormBean.isDisablebdmsMould() );
						bdFormBean.setDisablebdmsMould(false);
					}
					else
						bdFormBean.setDisablebdmsMould(true);
				
					setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);					
					request.setAttribute("bdmTlMst", bdmTlMst);					
					request.setAttribute("bdmTlDtl", bdmTlDtl);
					request.setAttribute("bdFormBean", bdFormBean);
					request.setAttribute("formMode", mode);
				///	createdOn = bdmTlMst.getBdmsCreatedon();
					httpSession.removeAttribute("bdmTlMst");
					httpSession.setAttribute("bdmTlMst",bdmTlMst);
				}
				else
				{
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					bdmTlMst.setBdmsBookedby(user.getUsrm_ccno());
					bdmTlDtl.setBdanCompletedby(user.getUsrm_ccno());
					request.setAttribute("bdmTlMst", bdmTlMst);
					request.setAttribute("bdmTlDtl", bdmTlDtl);
				}
				request.setAttribute("keyid", keyid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownAnalysis.jsp"); 
				rd.forward(request, response); 
			}
			
			else if (action.equals("brkdown_input.brdn")) 
			{ 
				String keyid = request.getParameter("BDKeyid");
				String activity = request.getParameter("activity");
				response.setContentType("text/html");
				CommonMessage.debugMsg("ID :"+keyid);
				FormModes mode = FormModes.view;	
				String woKey = request.getParameter("WOID");
				BdmTlMst bdmTlMst = new BdmTlMst();
				BdmTlDtl bdmTlDtl = new BdmTlDtl();
				if(UIUtils.isValidKeyId(woKey))
		    	{
		    		WomTlWomst womTlWomst = workOrderService.select(woKey);
		    		httpSession.setAttribute("WorkOrderBD", womTlWomst);
		    	}
				UIUtils.displayRequestParamsValue(request);
				response.setContentType("text/html");
				if(UIUtils.isValidKeyId(keyid))
				{
					
					BDFormBean bdFormBean = new  BDFormBean(mode);
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
							bdmTlMst = (BdmTlMst) UIUtils.copyObject(plmTlUnplannedmaintmst, bdmTlMst);
							bdmTlDtl = (BdmTlDtl) UIUtils.copyObject(plmTlUnplannedmaintdtl, bdmTlDtl);
						}
					}
						
					setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);					
					request.setAttribute("bdmTlMst", bdmTlMst);
					request.setAttribute("bdmTlDtl", bdmTlDtl);
					request.setAttribute("bdFormBean", bdFormBean);
					request.setAttribute("formMode", mode);
				//	createdOn = bdmTlMst.getBdmsCreatedon();
					httpSession.removeAttribute("bdmTlMst");
					httpSession.setAttribute("bdmTlMst",bdmTlMst);
				}
				/*if(keyid != null)
				{
					BDFormBean bdFormBean = new  BDFormBean(FormModes.view);
					BdmTlMst bdmTlMst = breakDownService.select(keyid);					
					BdmTlDtl bdmTlDtl = breakDownService.selectBd(keyid);
					setBdmMstvalues(bdmTlMst,bdmTlDtl,bdFormBean);
					request.setAttribute("bdmTlMst", bdmTlMst);
					request.setAttribute("bdmTlDtl", bdmTlDtl);
					request.setAttribute("bdFormBean", bdFormBean);
					request.setAttribute("formMode", "view");
				}*/
			
				RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownAnalysis.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("Breakdown_save.brdn"))
			{	
				CommonMessage.debugMsg("Saveeeeeee");
				BDFormBean bdFormBean = new  BDFormBean();
				saveBD(request,response,bdFormBean);
				
		    }
			else if( action.equals("Breakdown_delete.brdn"))
			{	
				BDFormBean bdFormBean = new  BDFormBean();
				delBD(request,response,bdFormBean);
				
		    }
			else if( action.equals("Breakdown_unphn.brdn"))
			{	
				BDFormBean bdFormBean = new  BDFormBean();
				bdFormBean.setFormActionMode("undefinedPhn");
				saveBD(request,response,bdFormBean);
			}
			else if( action.equals("Breakdown_yy.brdn"))
			{	
				BDFormBean bdFormBean = new  BDFormBean();
				bdFormBean.setFormActionMode("yy");
				//bdFormBean.setFinalAction(request.)finalAction
				saveBD(request,response,bdFormBean);
			}
			else if( action.equals("functionalLoc.brdn"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");
				functLocFieldNameBean.setFunctionalLocId("cmbbdmsFlid");
				functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(true);
				
				FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
				
				String funlocdisable = (String) httpSession.getAttribute("fromWorkorderDisableFunloc");
				if( funlocdisable != null && "true".equals(funlocdisable))
					formModes = FormModes.view;
				if( formModes == FormModes.completion)
					formModes = FormModes.view;
				
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
			}
			else if( action.equals("costinfo.brdn"))
			{	
				BDFormBean bdFormBean = new  BDFormBean();
				String repType=request.getParameter("repType");
				CommonMessage.debugMsg(repType);
				if (repType.equals("Estimate"))		
					bdFormBean.setFormActionMode("estimate");
				else
					bdFormBean.setFormActionMode("actual");
				
				saveBD(request,response,bdFormBean);
			}
			
			else if( action.equals("combo_shift.brdn"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  shift = breakDownService.getComboShift("",comboFilter);
					UIUtils.writeComboBox(response, shift,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if( action.equals("combo_mould.brdn"))
			{
				try {
					String mchId = "";
					if(UIUtils.isValidKeyId(request.getParameter("mchId")))
						mchId = request.getParameter("mchId");
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  mould = breakDownService.getMould(mchId,comboFilter);
					UIUtils.writeComboBox(response,mould,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_repeatedbdno.brdn"))
			{
				try {
					String assmId = request.getParameter("assmId");
					String eqpId = request.getParameter("eqpID");
					String idFlag = request.getParameter("idFlag");
					//String repDate = request.getParameter("repDate");
					//repDate = repDate.substring(0, 11) + " "+repDate.substring(11);
					String condSQL = "";
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  repeatedbdno = breakDownService.getRepeatedbdno(condSQL,assmId,eqpId,idFlag,comboFilter);
					UIUtils.writeComboBox(response, repeatedbdno,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_trade.brdn"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  repeatedbdno = breakDownService.getFinalTrade("",comboFilter);
					UIUtils.writeComboBox(response, repeatedbdno,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_alarm.brdn"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  repeatedbdno = breakDownService.getAlarm("",comboFilter);
					UIUtils.writeComboBox(response, repeatedbdno,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_failtype.brdn"))
			{
				try {
					String relatedTo = request.getParameter("relatedTo");
					CommonMessage.debugMsg("Fail : "+relatedTo);
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  failureType = breakDownService.getFailureType(relatedTo,comboFilter);
					UIUtils.writeComboBox(response, failureType,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_phenomena.brdn"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					String assmId = request.getParameter("assmId");
					String mchId = request.getParameter("mchId");
					String mldId = request.getParameter("mldId");
					if(UIUtils.isValidKeyId(assmId))
						mchId = mchId + "-"+assmId;
					if(UIUtils.isValidKeyId(mldId))
						mchId = "MLD-"+mldId;
					List<ComboBox>  phenomena = breakDownService.getPhenomena(mchId,comboFilter);
					UIUtils.writeComboBox(response, phenomena,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_cause.brdn"))
			{
				try {
					String condSql = "";
					String phenId = request.getParameter("phenId");
					String assmId = request.getParameter("assmId");
					/*if (request.getParameter("phenId") != null)
						condSql = " AND BCSM_PHENOMENAID  = '" + request.getParameter("phenId") +  "' ";
					else
						condSql = "";*/
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  cause = breakDownService.getCause(condSql,phenId,assmId,comboFilter);
					UIUtils.writeComboBox(response, cause,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_bdclfcn.brdn"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					String pillar = request.getParameter("pillar");
					String condSql = "";
					if(UIUtils.isValidKeyId(pillar))
						condSql = "AND BCLM_ACTIVE ='Y' AND TPMP_CODE = '"+pillar+"'";
				    List<ComboBox>  bdClassifcn = breakDownService.getBDClassification(condSql,comboFilter);
					UIUtils.writeComboBox(response, bdClassifcn,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_spare.brdn"))
			{
				try {
					String assmId = request.getParameter("assmId");
					String condSql = "";
					comboFilter=UIUtils.fillComboFilter(request);
				    List<ComboBox>  spare = breakDownService.getSpare(condSql,assmId,comboFilter);
					UIUtils.writeComboBox(response, spare,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if( action.equals("combo_ExternalServiceno.brdn"))
			{
				try {
					//String assmId = request.getParameter("assmId");
					//String condSql = "";
					comboFilter=UIUtils.fillComboFilter(request);
				    List<ComboBox>  serviceNo = breakDownService.getServiceNo(comboFilter);
					UIUtils.writeComboBox(response, serviceNo,comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if( action.equals("getSapServicetext.brdn"))
			{
				try {
					PrintWriter out = response.getWriter();	
					String servmId = request.getParameter("servmNo");
					//String condSql = "";
				    List<String[]>  shortText = breakDownService.getServiceText(servmId);
				    JSONObject shortTextData =new JSONObject();
	  			 	if (shortText.size()>0) {
		  			 	//CommonMessage.debugMsg("wbs element:"+shortText.get(0)[0]);
		  			 	shortTextData.put("shortText", shortText.get(0)[0]);
	  			 	}
	  			 	out.print(shortTextData);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("txt_shift.brdn"))
			{
				try {
					/*List<String > paramValues = new ArrayList<String>();
					paramValues.add(request.getParameter("factId"));
					paramValues.add(request.getParameter("sectId"));
					paramValues.add(request.getParameter("cellId"));
					paramValues.add(request.getParameter("fromTime"));*/
					CommonMessage.debugMsg("Shhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
					String fromTime = request.getParameter("fromTime");
					ShiftBean  shiftBean = new ShiftBean();
					shiftBean.setFactId(request.getParameter("flid"));
					shiftBean.setSectId(request.getParameter("mchId"));
					shiftBean.setCellId(request.getParameter("cellId"));
					shiftBean.setFromTime(fromTime);
					
					String occDate = request.getParameter("occurreddate");
					String shiftDate = occDate;
					
				
					
					if(UIUtils.isValidKeyId(occDate) && UIUtils.isValidKeyId(fromTime))
					{
						if(Integer.parseInt(fromTime.substring(0, 2)) < 7)
						{
							 final long MILLIS_IN_A_DAY = 1000*60*60*24;    
							 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
							 Date occuredDate = dateFormat.parse(occDate);
							 Date oneDayBefore = new Date(occuredDate.getTime() - MILLIS_IN_A_DAY);

						     shiftDate = dateFormat.format(oneDayBefore);
						     CommonMessage.debugMsg(shiftDate);
								
						}
						
					}
					String shift = breakDownService.getShift(shiftBean);
					//String shift = UIUtils.getShift(request.getParameter("factId"),request.getParameter("sectId"),request.getParameter("cellId"),request.getParameter("fromTime"));
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter(); 	
					
					 CommonMessage.debugMsg(shift+"------------");
					JSONObject shiftObj = new  JSONObject();
					shiftObj.put("shift",shift);
					CommonMessage.debugMsg(shiftDate + " : shiftDate");
					if(UIUtils.isValidKeyId(shiftDate))
					{
						shiftObj.put("shiftDate",shiftDate);
						shiftObj.put("ShiftId",request.getParameter("ShiftId"));
						shiftObj.put("ShiftDateId",request.getParameter("ShiftDateID"));
					}
				    CommonMessage.debugMsg(shiftObj);
			        out.print(shiftObj);					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			 //for filling fact sect cell on select machine
		    else if( action.equals("machine_fillcombo.brdn"))
			{
		    	try {
					List<String[]> eqpautofill = commonFilterService.getMachineHierarchy(request.getParameter("eqpId"));
					UIUtils.writeMachineHirearchy(response,eqpautofill);
					
				} 
			catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
		    else if( action.equals("costcenter_fillcombo.brdn"))
			{
		    	try {
					List<String[]> costcenterfill = commonFilterService.getCostCenterRelCell(request.getParameter("eqpId"));
					UIUtils.writecostCenterHierarchy(response,costcenterfill);					
		    	} 
				catch (Exception e) {
						e.printStackTrace();
				}
			}
		    else if(action.equals("sparesReplaced_input.brdn")){
		    	String refDocType = request.getParameter("refDocType");
		    	 String machineId = request.getParameter("bdMachine");
		    	 String priority = request.getParameter("priority");
		    	 String  refdocId    = request.getParameter("woId");
		    	 SapTlMaintenanceOrdermst newSapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
		    	 if(UIUtils.isValidKeyId(refdocId)){
		    		  newSapTlMaintenanceOrdermst=breakDownService.getSapSpareInFoData(refdocId);
		    		 if( newSapTlMaintenanceOrdermst == null){
		    			 CommonMessage.debugMsg("insede if null value in sap master");
		    		 }else{
		    			 CommonMessage.debugMsg("insede else no null value in sap master");
		    			 request.setAttribute("sapTlMaintenanceorder",newSapTlMaintenanceOrdermst);
		    			 request.setAttribute("existWoId",newSapTlMaintenanceOrdermst.getMomsRefdocid());
		    			// CommonMessage.debugMsg("estste   "+newSapTlMaintenanceOrdermst.getMomsOpno());
		    		 }
						 
		    	 }
		    	 
				request.setAttribute("refdocId",refdocId );
				request.setAttribute("refDocType",refDocType);
				request.setAttribute("machineId",machineId);
		    	RequestDispatcher rd = request.getRequestDispatcher("/pages/sapSparesReplaced.jsp"); 
		    	rd.forward(request, response);
		    }
		    else if( action.equals("sapInfo_input.brdn")){
				//httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
		    	 //String refdocId = request.getParameter("bdKeyid");
		    	 String refDocType = request.getParameter("refDocType");
		    	 String machineId = request.getParameter("bdMachine");
		    	 String priority = request.getParameter("priority");
		    	 String  refdocId    = request.getParameter("woId");
		    	 SapTlMaintenanceOrdermst newSapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
		    	 /*if(UIUtils.isValidKeyId(refdocId)){
		    		  newSapTlMaintenanceOrdermst=breakDownService.getSapSpareInFoData(refdocId);
		    		  CommonMessage.debugMsg("afterr....   ");
		    		 if( newSapTlMaintenanceOrdermst == null){
		    			 CommonMessage.debugMsg("inside If ");
		    		 }else{
		    			 CommonMessage.debugMsg("inside else ");
		    			 request.setAttribute("sapTlMaintenanceorder",newSapTlMaintenanceOrdermst);
		    			 request.setAttribute("existWoId",newSapTlMaintenanceOrdermst.getMomsRefdocid());
		    			// CommonMessage.debugMsg("estste   "+newSapTlMaintenanceOrdermst.getMomsOpno());
		    		 }
		    	 }*/
				request.setAttribute("refdocId",refdocId );
				request.setAttribute("refDocType",refDocType);
				request.setAttribute("machineId",machineId);
				request.setAttribute("priority",priority);
				CommonMessage.debugMsg("before Dispatch ");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SAPInfoBD.jsp"); 
				rd.forward(request, response); 
				CommonMessage.debugMsg("ay Dispatch ");
			}
		    else if( action.equals("sapInfo_view.brdn")){
			}
			else if( action.equals("sapInfo_getCol.brdn") )
			{
				CommonMessage.debugMsg("sapInfo_getCol.brdn" );
				PrintWriter out = response.getWriter();				
				String bdId = request.getParameter("bdId");
				CommonFilter commonFilter = populateCommonFilter(request,"SAPInfoCommonFilter",true);
				List<String[]> Grid = breakDownService.getSapInfoList(bdId);
				//List<String []> KpivReportList  =  kpivservice.getAllGeneral(commonFilter);
				JSONObject colModel = getTableModelSapInfo(Grid ,commonFilter);
				colModel.set("tableHeight", "40%%");
				colModel.set("tableWidth", "90%%");
				httpSession.setAttribute("ColModel", colModel);
				CommonMessage.debugMsg("before" );
				out.println(colModel);
				CommonMessage.debugMsg("colModelSAPMaintainorder  "+colModel );
			}
			else if( action.equals("sapInfo_getData.brdn") )
			{
				try
				{	
					CommonMessage.debugMsg("sapInfo_getData.brdn" );
					//FilterValues.getCommonFilters(request, commonFilter);
					String bdId = request.getParameter("bdId");
					CommonFilter commonFilter = populateCommonFilter(request,"SAPInfoCommonFilter",true);
					List<String[]> Grid = breakDownService.getSapInfoList(bdId);
					PrintWriter out = response.getWriter();					
					JSONObject sopmaster = UIUtils.convertToJqGridTableObject(Grid ,request, 1, 0);
					out.println(sopmaster );
					CommonMessage.debugMsg(sopmaster );
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if(action.equals("sapOrderType.brdn"))
			{
				CommonMessage.debugMsg("sapOrderType.brdn" );
				PrintWriter out = response.getWriter();			
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel","sapInfoColModel"));			
			}
			else if(action.equals("extServiceDtl_input.brdn")){
				String formMode =request.getParameter("formMode"); 
				request.setAttribute("formMode",formMode);
				String extMasterId =request.getParameter("extMasterId"); 
				String detailServiceKeyid = request.getParameter("extDtlKeyid");
				String detailRepairKeyid = request.getParameter("extRepDtlKeyid");
				request.setAttribute("extMasterId",extMasterId);
				String pages =" ";
				SapExternalServiceDtl newSapExternalServiceDtl = new SapExternalServiceDtl();
				SapExternalRepair newSapExternalRepair = new SapExternalRepair();
				if("ExtService".equals(formMode)){
					 if(UIUtils.isValidKeyId(detailServiceKeyid)){
						 newSapExternalServiceDtl=breakDownService.getserviceDtlData(detailServiceKeyid);
						 request.setAttribute("newSapExternalServiceDtl",newSapExternalServiceDtl);
						
					 }
					 httpSession.setAttribute("newSapExternalServiceDtl",newSapExternalServiceDtl);
					pages = "/pages/ExternalServiceBD_detail.jsp";
				}
				else{
					 if(UIUtils.isValidKeyId(detailRepairKeyid)){
						 newSapExternalRepair=breakDownService.getRepairDtlData(detailRepairKeyid);
						 request.setAttribute("newSapExternalRepair",newSapExternalRepair);
						 
					 }
					 httpSession.setAttribute("newSapExternalRepair",newSapExternalRepair);
					pages = "/pages/ExternalRepairBD_detail.jsp";
				}
				RequestDispatcher rd = request.getRequestDispatcher(pages); 
				rd.forward(request, response); 
			}
			else if( action.equals("externalService_input.brdn")){
				//httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
				//request.setAttribute("breakdownId", request.getParameter("bdId"));
				
				String formMode=request.getParameter("formMode");
				request.removeAttribute("formMode");
				request.setAttribute("formMode",formMode);
				String task=request.getParameter("task");
				request.setAttribute("task",task);
				CommonMessage.debugMsg(formMode+"  formModeEXT  "+task);
				String bdKeyid=request.getParameter("bdKeyid");
				request.setAttribute("bdKeyid",bdKeyid);
                String flid=request.getParameter("flid");
                request.setAttribute("bdflid",flid  );
                String shift=request.getParameter("shift");
                request.setAttribute("shift",shift  );
                String date=request.getParameter("date");
                request.setAttribute("date", date );
                String mchId= request.getParameter("mchId");
                request.setAttribute("mchId", mchId);
                String woId= request.getParameter("woId");
                request.setAttribute("refdocId", woId);
                String taskid=request.getParameter("taskid");
                request.setAttribute("taskid", taskid);
                
                String extKeyid = request.getParameter("extKeyid");
                String servmstId=breakDownService.getServicemstId(taskid);
                SapExternalServiceMst newSapExternalServiceMst = new SapExternalServiceMst();
                if(UIUtils.isValidKeyId(servmstId)){
                	
                	newSapExternalServiceMst = breakDownService.getExtServiceData(servmstId);
                	 
	                	request.setAttribute("sapExternalServiceMst", newSapExternalServiceMst );
	   
	                	CommonMessage.debugMsg(bdKeyid+" sapExternalServiceMst  "+newSapExternalServiceMst.getExtmPlanDeliverytime());
                	 
                }
                String noDate = "";
        		
        		CommonMessage.debugMsg("getWomsOccurredDate()"+newSapExternalServiceMst.getExtmPlanDeliverytime() );
        		
        		if(UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPlanDeliverytime()))
        		{
        			if(newSapExternalServiceMst.getExtmPlanDeliverytime().substring(0, 11).equals(Constants.futureNullDate))
        			{
        				
        				newSapExternalServiceMst.setExtmPlanDeliverytime(noDate);
        			}
        			else
        			{
        				newSapExternalServiceMst.setExtmPlanDeliverytime(newSapExternalServiceMst.getExtmPlanDeliverytime().substring(0, 11));
        			}
        		}
             	httpSession.setAttribute("sapExternalServiceMst", newSapExternalServiceMst );
				RequestDispatcher rd = request.getRequestDispatcher("/pages/ExternalServiceBD.jsp"); 
				rd.forward(request, response); 
				
			}
			else if( action.equals("externalService_view.brdn")){
			
			}
			else if(action.equals("externalserviceDtl_Delete.brdn")){
				String detailKeyid = request.getParameter("ExtdKeyid");
				CommonMessage.debugMsg("detailKeyid "+detailKeyid);
				String deleteRecord = breakDownService.delteExtDetail(detailKeyid);
				CommonMessage.debugMsg("deleteRecord "+deleteRecord);
				PrintWriter out = response.getWriter();
				JSONObject successData = new JSONObject();
				successData.put("msg",deleteRecord);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
			else if(action.equals("externalRepairDtl_Delete.brdn")){
				String rpeDetailKeyid = request.getParameter("ExtrKeyid");
				String deleteRecord = breakDownService.delteExtRprDetail(rpeDetailKeyid);
				CommonMessage.debugMsg("deleteRecord "+deleteRecord);
				PrintWriter out = response.getWriter();
				JSONObject successData = new JSONObject();
				successData.put("msg",deleteRecord);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
			else if (action.equals("externalService_getCol.brdn"))  
			{
				/*CommonMessage.debugMsg("hgfhfhf");
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"ExtSerCommonFilter",true);
				String ExtMasterId = request.getParameter("ExtMasterId");
				String taskId=request.getParameter("taskId");
				String womsId=request.getParameter("womsId");
				if(UIUtils.isValidKeyId(ExtMasterId)){
					List<String[]> Grid =  breakDownService.getExtServiceList(commonFilter,ExtMasterId);
					JSONObject colModel =  getTableModelExtSer(Grid,commonFilter);
					colModel.set("tableHeight", "40%%");
					colModel.set("tableWidth", "90%%");
					httpSession.setAttribute("ColModel", colModel);
					out.println(colModel);
					}
				 else{
					 List<String[]> Grid =  breakDownService.getExtServiceList(commonFilter,womsId,taskId); 
					//List<String []> KpovReportList  =  kpovservice.getAllGeneral(commonFilter);
					JSONObject colModel =  getTableModelExtSer(Grid,commonFilter);
					colModel.set("tableHeight", "40%%");
					colModel.set("tableWidth", "80%%");
					httpSession.setAttribute("ColModel", colModel);
					out.println(colModel);
				 }*/
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(" type :: ");
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.BdColModel","externalServColModel"));
				CommonMessage.debugMsg(" Done");
			}	
			else if (action.equals("externalService_getData.brdn"))  
			{
				try
				{				
					//FilterValues.getCommonFilters(request, commonFilter);
					CommonFilter commonFilter =  populateCommonFilter(request,"ExtSerCommonFilter",false);
					//String ExtMasterId = request.getParameter("ExtMasterId");
					//List<String[]> Grid =   breakDownService.getExtServiceList(ExtMasterId);
					
					String ExtMasterId = request.getParameter("ExtMasterId");
					String taskId=request.getParameter("taskId");
					String womsId=request.getParameter("womsId");
					List<String[]> Grid = null;
					if(UIUtils.isValidKeyId(ExtMasterId))
						Grid =  breakDownService.getExtServiceList(commonFilter,ExtMasterId);
					 else
						 Grid =  breakDownService.getExtServiceList(commonFilter,womsId,taskId); 
						
					PrintWriter out = response.getWriter();					
					JSONObject kpovmaster = UIUtils.convertToJqGridTableObject(Grid ,request, 0, 0);
					out.println(kpovmaster );
					CommonMessage.debugMsg(kpovmaster );
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("externalRepair_view.brdn")){
				
			}
			else if (action.equals("externalRepair_getCol.brdn")) 
			{
				 PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"ExtSubCommonFilter",true);
				String bdId = request.getParameter("ExtMasterId");
				List<String[]> Grid =  breakDownService.getExtSubList(bdId);
				//List<String []> KpivReportList  =  kpivservice.getAllGeneral(commonFilter);
				JSONObject colModel = getTableModelExeSub(Grid ,commonFilter);
				colModel.set("tableHeight", "40%%");
				colModel.set("tableWidth", "90%%");
				httpSession.setAttribute("ColModel", colModel);				
				CommonMessage.debugMsg(colModel);
				out.println(colModel);
			} 
			
			else if (action.equals("externalRepair_getData.brdn")) 
			{
				try
				{				
					//FilterValues.getCommonFilters(request, commonFilter);
					CommonFilter commonFilter = populateCommonFilter(request,"ExtSubCommonFilter",true);
					String bdId = request.getParameter("ExtMasterId");
					List<String[]> Grid =  breakDownService.getExtSubList(bdId);
					PrintWriter out = response.getWriter();
					
					JSONObject repairData = UIUtils.convertToJqGridTableObject(Grid ,request, 1, 0);
					out.println(repairData );
					CommonMessage.debugMsg(repairData );
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("externalRepair_input.brdn")){
				//httpSession.setAttribute("orderId", request.getParameter("womsKey"));
				String formMode=request.getParameter("formMode");
				request.removeAttribute("formMode");
				request.setAttribute("formMode",formMode);
				CommonMessage.debugMsg(formMode+"  formModeEXT  "+request.getAttribute("formMode"));
				request.setAttribute("orderId", request.getParameter("womsKey"));
				RequestDispatcher rd = request.getRequestDispatcher("/pages/ExternalRepairBD.jsp"); 
				rd.forward(request, response); 
				
			}
			else if(action.equals("externalservice_save.brdn")){
				saveExternalServices(request,response);
			}
			else if(action.equals("externalserviceDtl_save.brdn")){
				saveExternalServiceDetail(request,response);
			}
			else if(action.equals("externalRepairDtl_save.brdn")){
				saveExternalRepairDetail(request,response);
			}
			else if( action.equals("externalRepair_view.brdn")){
			
			}
			else if (action.equals("externalRepair_getCol.brdn"))  
			{
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"ExtRepairCommonFilter",true);
				String womsKey = request.getParameter("womsKey");
				List<String[]> Grid =  breakDownService.getExtRepairList(womsKey);
				//List<String []> KpovReportList  =  kpovservice.getAllGeneral(commonFilter);
				JSONObject colModel =  getTableModelExtRepair( Grid ,commonFilter);
				colModel.set("tableHeight", "40%%");
				colModel.set("tableWidth", "90%%");
				httpSession.setAttribute("ColModel", colModel);
				
				out.println(colModel);
				
			}	
			else if(action.equals("BDAnalysisRpt_input.brdn"))
			{
				String filterStr = request.getParameter("filterString");
				request.setAttribute("filterStr", filterStr);
				String bdView =request.getParameter("selid");	
				httpSession.removeAttribute("BrkDownDownTimeView");
	  			httpSession.setAttribute("BrkDownDownTimeView", bdView);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownAnalysisRpt.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("BDAnalysisRpt_getCol.brdn") )
			{
				PrintWriter out = response.getWriter();			
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownAnalysisRpt",true);				
				String bdView =  (String) httpSession.getAttribute("BrkDownDownTimeView");				
		 	if(UIUtils.isValidKeyId(bdView)){
			 	if ( !( bdView.length()>8)){
			 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
			  
			 			 commonFilter.setFromDate("01-" + bdView);
						 String lastDay = CommonFunctions.getLastDayOfMonth("27-"+bdView);
						 commonFilter.setToDate(lastDay);
				 	  }
			 	}else{
			 		if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
						  commonFilter.setFromDate(bdView);
						  commonFilter.setToDate(bdView);
						}
			 	}
			} 
			     
				List<String[]> bdList  = breakDownService.getBDAnalysisRpt( commonFilter);	  
				JSONObject bdData = UIUtils.convertToJqGridTableObject(bdList,request,2,0,commonFilter.getTotalRecordCnt()); 
				httpSession.setAttribute("bdDataServlet", bdData);				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = bdList.get(0);			
				String [] colHeaderCond = bdList.get(1);
				
				//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "80%%");
				jsonObject.put("tableWidth", "106%%");
				httpSession.setAttribute("BDRptSummaryColModel", jsonObject);
				out.println(jsonObject);
			}
			else if( action.equals("BDAnalysisRpt_getData.brdn") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					
					 UIUtils.displayRequestParamsValue(request);
					 String page = request.getParameter("page");	  
					 httpSession = request.getSession();
					 CommonFilter commonFilter = populateCommonFilter(request,"BrkDownAnalysisRpt",true);
					 JSONObject jsonObject = new JSONObject();
						 List<String []> bdMasterList  = breakDownService.getBDAnalysisRpt(commonFilter);
		        		 jsonObject = UIUtils.convertToJqGridTableObject(bdMasterList,request,2,0,commonFilter.getTotalRecordCnt()); 
		        	 out.println(jsonObject);
					 commonFilter.setViewClick('N');	  			 	
		  			 httpSession.removeAttribute("BrkDownAnalysisRpt");
		  			 httpSession.setAttribute("BrkDownAnalysisRpt", commonFilter);
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
			else if( action.equals("BDAnalysisRpt_getExcel.brdn"))
			{						
				/*CommonFilter commonFilter = populateCommonFilter(request,"BrkDownCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String tableModel = breakDownService.getBDAnalysisRpt( commonFilter);	
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				tblJSONObj.put("title", "ActivityWise Report");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = maintActivityService.ActivityWiseExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "ActivityWise", format);*/
				
				CommonMessage.debugMsg("INSIDE THE EXCEL REPORT");
				CommonFilter commonFilter = populateCommonFilter(request,"BrkDownAnalysisRpt",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("BDRptSummaryColModel");
				//String keyId = request.getParameter("KeyId");
				tblJSONObj.put("title", " BreakDown Summary Report");
				String format = ExcelUtils.getFormat(request);
				
					
				Workbook wb = breakDownService.getBreakdownAnalysisExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "BreakDown Summary", format);
				
				
				
			}
	
			else if (action.equals("externalRepair_getData.brdn"))  
			{
				try
				{				
					//FilterValues.getCommonFilters(request, commonFilter);
					CommonFilter commonFilter =  populateCommonFilter(request,"ExtRepairCommonFilter",false);
					String womsKey = request.getParameter("womsKey");
					List<String[]> Grid =   breakDownService.getExtRepairList(womsKey);
					PrintWriter out = response.getWriter();					
					JSONObject kpovmaster = UIUtils.convertToJqGridTableObject(Grid ,request, 1, 0);
					out.println(kpovmaster );
					CommonMessage.debugMsg(kpovmaster );
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}
		   /* else if(action.equals("Breakdown_modification.brdn"))
			{
					try
					{
	
						BreakdownService breakDownService = new BreakdownServiceImpl();
						
						String fromDate = request.getParameter("fromDate");
						String toDate = request.getParameter("toDate");
						
						CommonFilter commonFilter = new CommonFilter();
					    fromDate = UIUtils.convertToDisplayFormat(fromDate, "MM/dd/yyyy");
			      		toDate = UIUtils.convertToDisplayFormat(toDate, "MM/dd/yyyy");
			
						commonFilter.setFromDate(fromDate);
					    commonFilter.setToDate(toDate);					 
						
					
						List< String[]> breakdownList  = breakDownService.getAllBreakdown(commonFilter);						
			
						
						 JSONObject breakdownTbl = new JSONObject();
						 JSONObject genMastTbl = new JSONObject();
				         org.json.simple.JSONArray headerNames =new org.json.simple.JSONArray();
				         org.json.simple.JSONArray colModelName =new org.json.simple.JSONArray();
				         org.json.simple.JSONArray colModelIndex =new org.json.simple.JSONArray();
						 org.json.simple.JSONArray tableCol_Model =new org.json.simple.JSONArray();
						 JSONObject tableColModel =new JSONObject();
							
						 genMastTbl.put("page", 1);
						 CommonMessage.debugMsg("Breakdown.size()" + breakdownList.size());
						 genMastTbl.put("records",  breakdownList.size());
						 genMastTbl.put("total", breakdownList.size());
						 org.json.simple.JSONArray rows = new org.json.simple.JSONArray(); 
						 int id=1;
					     for( String [] brk : breakdownList)
						 {
					    	 
					    	if( id++ > 1)
				        	{	
					    			CommonMessage.debugMsg(" id " + id); 
					        	    org.json.simple.JSONObject cellobj=new org.json.simple.JSONObject();
					                cellobj.put("id",id++);
					                org.json.simple.JSONArray cell=new org.json.simple.JSONArray();
					                for(int i=1;i<brk.length;i++)
					                {
					                	cell.add( (brk[i] != null ? brk[i].replace("[","").replace("]",""):""));
					                }
					                 cellobj.put("cell",cell);
					                 rows.add(cellobj);					               
				        	} 
					    	 else{				    	 	
							    	
						       	   
				    			  headerNames.add("BD No");
				    			  tableColModel.put("name","BD No");
				    			  tableColModel.put("index","BD No");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Order No");
				    			  tableColModel.put("name","Order No");
				    			  tableColModel.put("index","Order No");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Shift");
				    			  tableColModel.put("name","Shift");
				    			  tableColModel.put("index","Shift");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Cell");
				    			  tableColModel.put("name","Cell");
				    			  tableColModel.put("index","Cell");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Equipment");
				    			  tableColModel.put("name","Equipment");
				    			  tableColModel.put("index","Equipment");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Problem");
				    			  tableColModel.put("name","Problem");
				    			  tableColModel.put("index","Problem");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Occured Date");
				    			  tableColModel.put("name","Occured Date");
				    			  tableColModel.put("index","Occured Date");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Allotted Date");
				    			  tableColModel.put("name","Allotted Date");
				    			  tableColModel.put("index","Allotted Date");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Work Start Time");
				    			  tableColModel.put("name","Work Start Time");
				    			  tableColModel.put("index","Work Start Time");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Work End Time");
				    			  tableColModel.put("name","Work End Time");
				    			  tableColModel.put("index","Work End Time");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Production Strat Date");
				    			  tableColModel.put("name","Production Strat Date");
				    			  tableColModel.put("index","Production Strat Date");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Down Time");
				    			  tableColModel.put("name","Down Time");
				    			  tableColModel.put("index","Down Time");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    			  headerNames.add("Maint. Section");
				    			  tableColModel.put("name","Maint. Section");
				    			  tableColModel.put("index","Maint. Section");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Assembly");
				    			  tableColModel.put("name","Assembly");
				    			  tableColModel.put("index","Assembly");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Phenomena");
				    			  tableColModel.put("name","Phenomena");
				    			  tableColModel.put("index","Phenomena");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Cause");
				    			  tableColModel.put("name","Cause");
				    			  tableColModel.put("index","Cause");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Final Action");
				    			  tableColModel.put("name","Final Action");
				    			  tableColModel.put("index","Final Action");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("YY");
				    			  tableColModel.put("name","YY");
				    			  tableColModel.put("index","YY");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	   
						       	  headerNames.add("Root Cause");
				    			  tableColModel.put("name","Root Cause");
				    			  tableColModel.put("index","Root Cause");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Counter Measure");
				    			  tableColModel.put("name","Counter Measure");
				    			  tableColModel.put("index","Counter Measure");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Communication Text");
				    			  tableColModel.put("name","Communication Text");
				    			  tableColModel.put("index","Communication Text");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Booked By");
				    			  tableColModel.put("name","Booked By");
				    			  tableColModel.put("index","Booked By");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Analysed By");
				    			  tableColModel.put("name","Analysed By");
				    			  tableColModel.put("index","Analysed By");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
						       	  headerNames.add("Status");
				    			  tableColModel.put("name","Status");
				    			  tableColModel.put("index","Status");
				    			  tableColModel.put("editable","false");	
						       	  tableColModel.put("width","120");
						       	  tableCol_Model.add(tableColModel);
						       	   
				    	}
				    		
				    }
				    breakdownTbl.put("colNames", headerNames);
				    breakdownTbl.put("colModel", tableCol_Model);
					genMastTbl.put("rows", rows);
					breakdownTbl.put("colData", genMastTbl);
						
					response.getWriter().println(breakdownTbl);
				// }
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}*/

			
			
		}	
		
		private void saveExternalRepairDetail(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String lineNo="";
	    	SapExternalRepair newSapExternalRepair = new SapExternalRepair();
	    	SapExternalRepair existSapExternalRepair = (SapExternalRepair)httpSession.getAttribute("newSapExternalRepair");
			
		
			String extmId="";
	    	if( httpSession != null && user != null)
	    	{
	    		newSapExternalRepair.setExtrCreatedby(user.getUsrm_ccno());
	    		newSapExternalRepair =(SapExternalRepair)UIUtils.setBeanProperties((Object)newSapExternalRepair,request);
	    		try{
	    				CommonMessage.debugMsg("ceatedBY  "+newSapExternalRepair.getExtrCreatedby());
		    			boolean insert = true;
						BDFormBean bdFormBean = new BDFormBean();
						if(  newSapExternalRepair.getExtrKeyid() == null )
						{
							extmId=newSapExternalRepair.getExtrExtmKeyid();
							CommonMessage.debugMsg("extmId  "+extmId);
							if(UIUtils.isValidKeyId(extmId)){
		    	    			lineNo=workOrderService.getLineNo("SAP_EXTERNAL_REPAIR", extmId, "EXTR_EXTM_KEYID");
		    	    			CommonMessage.debugMsg("LineNO:"+lineNo);
		    	    			if(UIUtils.isValidKeyId(lineNo))
		    	    				newSapExternalRepair.setExtrLineNo(lineNo);
		    	    		}
							existSapExternalRepair =	breakDownService.createExtRepairDtl(newSapExternalRepair,existSapExternalRepair,bdFormBean );
						}	
						else
						{
							insert = false;
							lineNo=workOrderService.getLineNo("SAP_EXTERNAL_REPAIR", extmId, "EXTR_EXTM_KEYID");
	    	    			CommonMessage.debugMsg("LineNO:"+lineNo);
	    	    			if(UIUtils.isValidKeyId(lineNo) && !UIUtils.isValidKeyId(newSapExternalRepair.getExtrLineNo()))
	    	    				newSapExternalRepair.setExtrLineNo(lineNo);
							existSapExternalRepair = breakDownService.updateExtRepairDtl(newSapExternalRepair,existSapExternalRepair,bdFormBean );				
						}
						String msg;
						if(insert)
							msg="Data Saved Successfully";
						else
							msg="Data Updated Successfully";
						
						JSONObject successData = new JSONObject();
						successData.put("msg",msg);
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);	
						returnData.put("formClear", false);	
						out.print(returnData.toString());

	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
		
	    	}

			
		}
		private void saveExternalServiceDetail(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	//WorkOrderServlet workOrderServlet= new WorkOrderServlet(); 
	    	SapExternalServiceDtl newSapExternalServiceDtl = new SapExternalServiceDtl();
	    	SapExternalServiceDtl existSapExternalServiceDtl = (SapExternalServiceDtl)httpSession.getAttribute("newSapExternalServiceDtl");
	    	String lineNo="";
			String extmId="";
	    	if( httpSession != null && user != null)
	    	{
	    		newSapExternalServiceDtl.setExtdCreatedby(user.getUsrm_ccno());
	    		newSapExternalServiceDtl =(SapExternalServiceDtl)UIUtils.setBeanProperties((Object)newSapExternalServiceDtl,request);
	    		try{
	    				CommonMessage.debugMsg("ceatedBY  "+newSapExternalServiceDtl.getExtdCreatedby());
	    				extmId=newSapExternalServiceDtl.getExtdExtmKeyid();
	    				CommonMessage.debugMsg("external id:  "+extmId);
	    				if(UIUtils.isValidKeyId(extmId)){
	    	    			lineNo=workOrderService.getLineNo("SAP_EXTERNAL_SERVICE_DTL", extmId, "EXTD_EXTM_KEYID");
	    	    			CommonMessage.debugMsg("LineNO:"+lineNo);
	    	    			if(UIUtils.isValidKeyId(lineNo) && !UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdLineNo()))
	    	    				newSapExternalServiceDtl.setExtdLineNo(lineNo);
	    	    		}
		    			boolean insert = true;
						BDFormBean bdFormBean = new BDFormBean();
						if(  newSapExternalServiceDtl.getExtdKeyid() == null )
						{	
							existSapExternalServiceDtl =	breakDownService.createExtServiceDtl(newSapExternalServiceDtl,existSapExternalServiceDtl,bdFormBean );
						}	
						else
						{
							insert = false;
							existSapExternalServiceDtl = breakDownService.updateExtServiceDtl(newSapExternalServiceDtl,existSapExternalServiceDtl,bdFormBean );				
						}
						String msg;
						if(insert)
							msg="Data Saved Successfully";
						else
							msg="Data Updated Successfully";
						
						JSONObject successData = new JSONObject();
						successData.put("msg",msg);
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						out.print(returnData.toString());

	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}
		
	    	}
			
		}
		private void saveExternalServices(HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	SapExternalServiceMst newSapExternalServiceMst = new SapExternalServiceMst();
			SapExternalServiceMst existSapExternalServiceMst = (SapExternalServiceMst)httpSession.getAttribute("sapExternalServiceMst");
			
	    	if( httpSession != null && user != null)
	    	{
	    		newSapExternalServiceMst.setExtmCreatedby(user.getUsrm_ccno());
	    		newSapExternalServiceMst =(SapExternalServiceMst)UIUtils.setBeanProperties((Object)newSapExternalServiceMst,request);
	    		try{
	    				CommonMessage.debugMsg("ceatedBY  "+newSapExternalServiceMst.getExtmCreatedby());
		    			boolean insert = true;
						BDFormBean bdFormBean = new BDFormBean();
						if(  newSapExternalServiceMst.getExtmKeyid() == null )
						{	
							existSapExternalServiceMst =	breakDownService.createExtService(newSapExternalServiceMst,existSapExternalServiceMst,bdFormBean );
						}	
						else
						{
							insert = false;
							existSapExternalServiceMst = breakDownService.updateExtService(newSapExternalServiceMst,existSapExternalServiceMst,bdFormBean);						
						}
						String msg;
						if(insert)
							msg="Data Saved Successfully";
						else
							msg="Data Updated Successfully";
						JSONObject successData = new JSONObject();
						successData.put("msg",msg);
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						returnData.put("extMstKeyid",existSapExternalServiceMst.getExtmKeyid());
						out.print(returnData.toString());

	    		}
	    		catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"BdCreationException");
					//errMessage.put("fromMode",bdFormBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}catch(BusinessApplicationExceptions e)
				{
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "BdCreationException");
					out.print(errMessage.toString());					
					
				}
	    		catch(Exception e){
	    			e.printStackTrace();
	    			out.print(e.getMessage());
	    		}
		
	    	}
		}
		public void setBdmMstvalues(BdmTlMst bdmTlMst,BdmTlDtl bdmTlDtl,BDFormBean bdFormBean) {
			//if(bdmTlMst.getBdmsBookedphenomena() != null)
			String noDate = "";
			CommonMessage.debugMsg("PHEN Name : ------------------"+bdmTlDtl.getBdanErppoststatus());
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsBookedphenomena()))
			{
				String bookedPhen = breakDownService.getPhenType(bdmTlMst.getBdmsBookedphenomena());
				CommonMessage.debugMsg("PHEN Name : ------------------"+bookedPhen);
				bdFormBean.setBdmsphenName(bookedPhen);
			}
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
			{
				String bookedPhen = breakDownService.getPhenType(bdmTlMst.getBdmsFinalphenomena());
				CommonMessage.debugMsg("PHEN Name : ------------------"+bookedPhen);
				bdFormBean.setBdmsphenName(bookedPhen);
			}
			//if(bdmTlMst.getBdmsFinalphenomena() != null && !(bdmTlMst.getBdmsFinalphenomena().equals("{}")))
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
			{
				bdmTlMst.setBdmsBookedphenomena(bdmTlMst.getBdmsFinalphenomena());	
			}
			//if(bdmTlMst.getBdmsFinalcause() != null && !(bdmTlMst.getBdmsFinalcause().equals("{}")))
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalcause()))
			{
				bdmTlMst.setBdmsBookedcause(bdmTlMst.getBdmsFinalcause());	
			}
			//if(bdmTlMst.getBdmsEntrydate() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsEntrydate()))
				bdmTlMst.setBdmsEntrydate(bdmTlMst.getBdmsEntrydate().substring(0, 11));
				
			//if(bdmTlMst.getBdmsReporteddate() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsReporteddate()))
			{
				bdFormBean.setBdmsReportedtime(bdmTlMst.getBdmsReporteddate().substring(12, 17));
		    	bdmTlMst.setBdmsReporteddate(bdmTlMst.getBdmsReporteddate().substring(0, 11));
			}
			//if(bdmTlMst.getBdmsReceiveddate() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsReceiveddate()))
			{
				if(bdmTlMst.getBdmsReceiveddate().substring(0, 11).equals(Constants.passNullDate))
				{
					bdFormBean.setBdmsReceivedtime(noDate);
					bdmTlMst.setBdmsReceiveddate(noDate);
				}
				else
				{
					bdFormBean.setBdmsReceivedtime(bdmTlMst.getBdmsReceiveddate().substring(12,17));
					bdmTlMst.setBdmsReceiveddate(bdmTlMst.getBdmsReceiveddate().substring(0, 11));
				}
			}			
			//if(bdmTlMst.getBdmsWostarttime() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWostarttime()))
			{
				if(bdmTlMst.getBdmsWostarttime().substring(0, 11).equals(Constants.passNullDate))
				{
					bdFormBean.setBdmsWostart(noDate);
					bdmTlMst.setBdmsWostarttime(noDate);
				}
				else
				{
					bdFormBean.setBdmsWostart(bdmTlMst.getBdmsWostarttime().substring(12,17));
					bdmTlMst.setBdmsWostarttime(bdmTlMst.getBdmsWostarttime().substring(0, 11));
				}
			}
			//if(bdmTlMst.getBdmsWoendtime() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendtime()))
			{
				if(bdmTlMst.getBdmsWoendtime().substring(0, 11).equals(Constants.futureNullDate))
				{
					bdFormBean.setBdmsWoend(noDate);
					bdmTlMst.setBdmsWoendtime(noDate);
				}
				else
				{
					bdFormBean.setBdmsWoend(bdmTlMst.getBdmsWoendtime().substring(12,17));
					bdmTlMst.setBdmsWoendtime(bdmTlMst.getBdmsWoendtime().substring(0, 11));
				}
			}
			//if(bdmTlMst.getBdmsProdaccepdate() != null)
			if(UIUtils.isValidKeyId(bdmTlMst.getBdmsProdaccepdate()))
			{
				if(bdmTlMst.getBdmsProdaccepdate().substring(0, 11).equals(Constants.futureNullDate))
				{
					bdFormBean.setBdmsProdacceptime(noDate);
					bdmTlMst.setBdmsProdaccepdate(noDate);
				}
				else if(bdmTlMst.getBdmsProdaccepdate().substring(0, 11).equals(Constants.passNullDate))
				{
					bdFormBean.setBdmsProdacceptime(noDate);
					bdmTlMst.setBdmsProdaccepdate(noDate);
				}
				else
				{
					bdFormBean.setBdmsProdacceptime(bdmTlMst.getBdmsProdaccepdate().substring(12,17));
					bdmTlMst.setBdmsProdaccepdate(bdmTlMst.getBdmsProdaccepdate().substring(0, 11));
				}
			}
				
			//if(bdmTlDtl.getBdanErppoststatus() != null)
			if(UIUtils.isValidKeyId(bdmTlDtl.getBdanErppoststatus()))
			{
				bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("C", "Completed"));
				bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("W", "Work In Progress"));
				bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("B", "Booked"));
				bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("A", "Allotted"));
				bdmTlDtl.setBdanErppoststatus(bdmTlDtl.getBdanErppoststatus().replace("X", "Booking"));
			}
				
		
		}
		public void setRootCauseValues(List<String[]> rootCause,HttpServletRequest request)
		{
			CommonMessage.debugMsg("Size of RC List : "+rootCause.size());
			for(String[] s : rootCause)
			{
				CommonMessage.debugMsg(s[0]);
				CommonMessage.debugMsg(s[2]);
				CommonMessage.debugMsg(s[3]);
				CommonMessage.debugMsg(s[4]);
				CommonMessage.debugMsg(s[5]);
				request.setAttribute("rcId", s[0]);
				request.setAttribute("isJH", s[2]);
				request.setAttribute("isPM", s[3]);
				request.setAttribute("isCI", s[4]);
				request.setAttribute("isET", s[5]);
			}
				
			
		}
		
		private void saveBD(HttpServletRequest request,HttpServletResponse response, BDFormBean bdFormBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		BdmTlMst existBdmTlMst = (BdmTlMst)httpSession.getAttribute("bdmTlMst"); 
	    	//	 CommonMessage.debugMsg("Exist Datas : "+existBdmTlMst.getBdmsKeyid());
	    		BdmTlMst newBdmTlMst = new BdmTlMst();
	    		BdmTlDtl newBdmTlDtl = new BdmTlDtl();
	    		WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderBD");
	    		newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());
	    			
	    		String bdRelatedTo = request.getParameter("relatedToCMB");
	    		newBdmTlMst =(BdmTlMst)UIUtils.setBeanProperties((Object)newBdmTlMst,request);
	    		if(UIUtils.isValidKeyId(bdRelatedTo))
	    			newBdmTlMst.setBdmsRelatedto(bdRelatedTo);
	    		CommonMessage.debugMsg("Second");
	    		newBdmTlDtl =(BdmTlDtl)UIUtils.setBeanProperties((Object)newBdmTlDtl,request);
	    		CommonMessage.debugMsg("newBdmTlDtl  "+newBdmTlDtl.getBdanBdms_keyid());
	    		CommonMessage.debugMsg("newBdmTlDtl  "+newBdmTlDtl.getBdanKeyid());
	    		CommonMessage.debugMsg("newBdmTlDtl  "+newBdmTlDtl.getBdanCountermeasure());
	    		if(request.getParameter("DwnTmeBrkupGrid")== null || request.getParameter("DwnTmeBrkupGrid").equals(""))
	    		{
		    		//CommonMessage.debugMsg("Down Time Break Up Grid : "+request.getParameter("DwnTmeBrkupGrid"));
	    		}
	    		else
	    		{
	    			CommonMessage.debugMsg("Down Time Break Up Grid = "+request.getParameter("DwnTmeBrkupGrid"));
		    		String dwnTimeGrid = request.getParameter("DwnTmeBrkupGrid");	    		
			    	JSONArray jsonArray = JSONArray.fromString(dwnTimeGrid);		    	
			    	BdmTlShiftwisesplit bdmTlShiftwisesplit = new BdmTlShiftwisesplit();
			    	List<BdmTlShiftwisesplit> shiftWise = (List<BdmTlShiftwisesplit>) UIUtils.convertJSONArrToList(bdmTlShiftwisesplit, jsonArray);
			    	if( bdmTlShiftwisesplit != null)
		    			newBdmTlMst.setBdmShiftwise(shiftWise);
	    		}
		    	 
	    		if( newBdmTlDtl != null){
	    			newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());
	    			newBdmTlMst.getBdmDetail().add(newBdmTlDtl);
	    		}
	    		CommonMessage.debugMsg("Before Form Bean");
	    		bdFormBean =(BDFormBean) UIUtils.setBeanProperties((Object)bdFormBean,request);
	    		CommonMessage.debugMsg("After Form Bean : "+bdFormBean.getIssparesY());
	    		
	    		try{
	    			boolean insert = true;
					if(  newBdmTlMst.getBdmsKeyid() == null || newBdmTlMst.getBdmsKeyid().substring(0,3).equals("SFT"))
					{	
						existBdmTlMst =	breakDownService.create(newBdmTlMst,existBdmTlMst,bdFormBean);
					}	
					else
					{
						insert = false;
						existBdmTlMst = breakDownService.update(newBdmTlMst,existBdmTlMst,bdFormBean,womTlWomst);						
					}
					
					httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
					httpSession.setAttribute("BdmTlMst", existBdmTlMst);
					
					String formBeanIdentifier = "BDFormBean"+bdFormBean.getFormActionMode();					
					httpSession.setAttribute(formBeanIdentifier,bdFormBean);
					CommonMessage.debugMsg("After Form Bean Iden");
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("BDKeyid",existBdmTlMst.getBdmsKeyid());
					persistentData.put("fromBean", formBeanIdentifier);
					CommonMessage.debugMsg("After Persisten Data");
					CommonMessage.debugMsg(existBdmTlMst.getBdmsWostarttime());
					JSONObject forwardData = new JSONObject();
					if(bdFormBean.getFormActionMode() != null)
					{
						if(bdFormBean.getFormActionMode().equals("yy"))
						{
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsKeyid()))
							{
								forwardData.put("cmbwwmsRefdocno",existBdmTlMst.getBdmsKeyid());
								//forwardData.put("whywhyRefDocID",existBdmTlMst.getBdmsKeyid());
							}
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
								forwardData.put("dtewwmsDate",existBdmTlMst.getBdmsEntrydate());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
								forwardData.put("cmbwwmsMachineid",existBdmTlMst.getBdmsMachineid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
								forwardData.put("cmbwwmsAssemblyid",existBdmTlMst.getBdmsAssemblyid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFactoryid()))
								forwardData.put("cmbwwmsFactoryid",existBdmTlMst.getBdmsFactoryid());
						
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
								forwardData.put("cmbwwmsCellid",existBdmTlMst.getBdmsCellid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
								forwardData.put("cmbwwmsSectionid",existBdmTlMst.getBdmsSectionid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
								forwardData.put("cmbwwmsPhenomenaid",existBdmTlMst.getBdmsFinalphenomena());
							
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
								forwardData.put("cmbwwmsCauseid",existBdmTlMst.getBdmsFinalcause());
							
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsBookedby()))
								forwardData.put("cmbwwmsMaintinchargeid",existBdmTlMst.getBdmsBookedby());
							
							if(existBdmTlMst.getBdmDetail()!= null && existBdmTlMst.getBdmDetail().size()>0) // check for detail table data
							{
								BdmTlDtl bdmDetail = (BdmTlDtl)existBdmTlMst.getBdmDetail().get(0);
								forwardData.put("chkwwmsSparesreplaced",bdmDetail.getBdanIssparesreplaced());
								
								
								if(UIUtils.isValidKeyId(bdmDetail.getBdanFinalaction()))
									forwardData.put("txtwwmsFinalaction",bdmDetail.getBdanFinalaction());
								if(UIUtils.isValidKeyId(bdmDetail.getBdanWwno()))
								{
									forwardData.put("txtwwmsKeyid",bdmDetail.getBdanWwno());
									
								}
							}
							
							forwardData.put("txtformType","BD");
							persistentData.put("OpenTab", "OpenYY");
						}
						else if(bdFormBean.getFormActionMode().equals("estimate") || bdFormBean.getFormActionMode().equals("actual")) 
						{
							
							
							
							if (bdFormBean.getFormActionMode().equals("estimate"))
								forwardData.put("formType","Estimate");
							else
								forwardData.put("formType","Actual");
										
							forwardData.put("DocType","BDM");
							forwardData.put("DocNo",existBdmTlMst.getBdmsWno());							
							forwardData.put("FactoryId",existBdmTlMst.getBdmsFactoryid());
							
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsSectionid()))
								forwardData.put("SectionId",existBdmTlMst.getBdmsSectionid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsCellid()))
								forwardData.put("CellId",existBdmTlMst.getBdmsCellid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsMachineid()))
								forwardData.put("MachineId",existBdmTlMst.getBdmsMachineid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsAssemblyid()))
								forwardData.put("AssemblyId",existBdmTlMst.getBdmsAssemblyid());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
								forwardData.put("PhenomenaId",existBdmTlMst.getBdmsFinalphenomena());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalcause()))
								forwardData.put("CauseId",existBdmTlMst.getBdmsFinalcause());
							else
								forwardData.put("CauseId","");
							
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinaltrade()))
								forwardData.put("TradeId",existBdmTlMst.getBdmsFinaltrade());
							else
								forwardData.put("TradeId","");

							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsProblemdescription()))
								forwardData.put("Problem",existBdmTlMst.getBdmsProblemdescription());
							else
								forwardData.put("Problem",".");
							
							if(existBdmTlMst.getBdmDetail()!= null && existBdmTlMst.getBdmDetail().size()>0) // check for detail table data
							{
								BdmTlDtl bdmDetail = (BdmTlDtl)existBdmTlMst.getBdmDetail().get(0);
								if(UIUtils.isValidKeyId(bdmDetail.getBdanCountermeasure()))
									forwardData.put("Measure",bdmDetail.getBdanCountermeasure());
								else
									forwardData.put("Measure",".");								
							}
							if (forwardData.get("Measure").equals(null))
								forwardData.put("Measure",".");
					
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime()))
								forwardData.put("StartTime",existBdmTlMst.getBdmsWostarttime().replace(" ", "-"));
							
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime()))
								forwardData.put("EndTime",existBdmTlMst.getBdmsWoendtime().replace(" ", "-"));
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsDowntime()))
								forwardData.put("DownTime",existBdmTlMst.getBdmsDowntime());
							if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsEntrydate()))
								forwardData.put("EntryDate",existBdmTlMst.getBdmsEntrydate());
						}
						else
						{
							CommonMessage.debugMsg("ELSE");
							forwardData.put("cmbbdmsKeyid",existBdmTlMst.getBdmsKeyid());
							forwardData.put("cmbbdmsFactoryid",existBdmTlMst.getBdmsFactoryid());
							forwardData.put("cmbbdmsCellid",existBdmTlMst.getBdmsCellid());
							forwardData.put("cmbbdmsSectionid",existBdmTlMst.getBdmsSectionid());
							forwardData.put("cmbbdmsMachineid",existBdmTlMst.getBdmsMachineid());
							forwardData.put("cmbbdmsAssemblyid",existBdmTlMst.getBdmsAssemblyid());
							forwardData.put("spnbdmsWostart",existBdmTlMst.getBdmsWostarttime().substring(12));
							forwardData.put("spnbdmsWoend",existBdmTlMst.getBdmsWoendtime().substring(12));
							forwardData.put("cmbdownTimeMins",existBdmTlMst.getBdmsDowntime());					
							forwardData.put("txtbdmsEntrydate",existBdmTlMst.getBdmsEntrydate());
							
						}
					}
					else
					{
						CommonMessage.debugMsg("ELSE when null");
						forwardData.put("cmbbdmsKeyid",existBdmTlMst.getBdmsKeyid());
						forwardData.put("cmbbdmsFactoryid",existBdmTlMst.getBdmsFactoryid());
						forwardData.put("cmbbdmsCellid",existBdmTlMst.getBdmsCellid());
						forwardData.put("cmbbdmsSectionid",existBdmTlMst.getBdmsSectionid());
						forwardData.put("cmbbdmsMachineid",existBdmTlMst.getBdmsMachineid());
						forwardData.put("cmbbdmsAssemblyid",existBdmTlMst.getBdmsAssemblyid());
						CommonMessage.debugMsg(existBdmTlMst.getBdmsWostarttime().length());
						CommonMessage.debugMsg(existBdmTlMst.getBdmsWoendtime().length());
						if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime()))
						{
							if(existBdmTlMst.getBdmsWostarttime().length()>11)
							{
								if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWostarttime().substring(12)))
									forwardData.put("spnbdmsWostart",existBdmTlMst.getBdmsWostarttime().substring(12));
							}
						}
						if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime()))
						{
							if(existBdmTlMst.getBdmsWoendtime().length()>11)
							{
								if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsWoendtime().substring(12)))
									forwardData.put("spnbdmsWoend",existBdmTlMst.getBdmsWoendtime().substring(12));
							}
						}
						forwardData.put("cmbdownTimeMins",existBdmTlMst.getBdmsDowntime());					
						forwardData.put("txtbdmsEntrydate",existBdmTlMst.getBdmsEntrydate());
					}
					
					CommonMessage.debugMsg("After Fwd Data");
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					 String msgPropertyIdnt;
					 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						msgPropertyIdnt = "success-update";
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",bdFormBean.getFormMode());
					successData.put("keyId", existBdmTlMst.getBdmsKeyid());
					
					if(UIUtils.isValidKeyId(existBdmTlMst.getBdmsFinalphenomena()))
					{
						String phen = breakDownService.getPhenType(newBdmTlMst.getBdmsFinalphenomena());
						if(UIUtils.isValidKeyId(phen))
							returnData.put("PhenomenaFlag",phen);
					}
					
					returnData.put("forwardData",forwardData);
					returnData.put("persistentData", persistentData);
					//CommonMessage.debugMsg("Message : "+!bdFormBean.getFormActionMode().equals("yy"));
					if(UIUtils.isValidKeyId(bdFormBean.getFormActionMode()))
					{
						returnData.put("formMode",bdFormBean.getFormActionMode());
						if(bdFormBean.getFormActionMode().equals("yy") || bdFormBean.getFormActionMode().equals("estimate") || bdFormBean.getFormActionMode().equals("actual")  || bdFormBean.getFormActionMode().equals("undefinedPhn"))
							returnData.put("displyMsg",false);
					}
					returnData.put("successData", successData);	
					returnData.put("formClear",false);
					String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
					if(UIUtils.isValidKeyId(backTo))
						returnData.put(WOConstants.backTo,backTo);
					CommonMessage.debugMsg(returnData.toString());
					out.print(returnData.toString());
				
	    		}catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "BdCreationException");
					errMessage.put("fromMode",bdFormBean.getFormActionMode());
					out.print(errMessage.toString());
				}catch(BusinessApplicationExceptions e)
				{
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "BdCreationException");
					out.print(errMessage.toString());					
					
				}catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
					e.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
	    	}	
		}
		
		private void delBD(HttpServletRequest request,
				HttpServletResponse response, BDFormBean bdFormBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		BdmTlMst existBdmTlMst = (BdmTlMst)httpSession.getAttribute("newSession"); 
	    		
	    		BdmTlMst newBdmTlMst = new BdmTlMst();
	    		BdmTlDtl newBdmTlDtl = new BdmTlDtl();
	    		
	    		newBdmTlMst.setBdmsCreatedby(user.getUsrm_ccno());
	    		newBdmTlDtl.setBdanCreatedby(user.getUsrm_ccno());
	    		
	    		newBdmTlMst =(BdmTlMst)UIUtils.setBeanProperties((Object)newBdmTlMst,request);
	    		newBdmTlDtl =(BdmTlDtl)UIUtils.setBeanProperties((Object)newBdmTlDtl,request);	    		
	    		newBdmTlMst.getBdmDetail().add(newBdmTlDtl);
	    		
	    		bdFormBean =(BDFormBean) UIUtils.setBeanProperties((Object)bdFormBean,request);
	    		
			
				try{
									
					if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag()))
					{
						if(newBdmTlMst.getBdmsWoprodaccepflag().equals("Y"))
						{
							throw new BusinessApplicationExceptions("prodAccept,");
						}
					}
					if(UIUtils.isValidKeyId(bdFormBean.getBdmsReportedtime()))
						newBdmTlMst.setBdmsReporteddate(newBdmTlMst.getBdmsReporteddate() + " "+bdFormBean.getBdmsReportedtime());
					
					
					existBdmTlMst = breakDownService.delete(newBdmTlMst);						
					
					httpSession.setAttribute(existBdmTlMst.getBdmsKeyid(), existBdmTlMst);
					httpSession.setAttribute("BdmTlMst", existBdmTlMst);
					String formBeanIdentifier = "BDForrmBean"+bdFormBean.getFormActionMode();
					httpSession.setAttribute(formBeanIdentifier,bdFormBean);
							
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					successData.put("mode",bdFormBean.getFormMode() );
					successData.put("keyId", existBdmTlMst.getBdmsKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					
					out.print(returnData.toString());
					
				}catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"BdCreationException");
					errMessage.put("fromMode",bdFormBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}catch(BusinessApplicationExceptions e)
				{
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "BdCreationException");
					out.print(errMessage.toString());					
					
				}catch(Exception e)
				{
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Deleted");
					out.print(err.toString());
				}
	    	}    	
	}
		
		private void saveCom(HttpServletRequest request,HttpServletResponse response, BDFormBean bdFormBean) throws IOException {
			// TODO Auto-generated method stub

			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);  	
	    	CommonMessage.debugMsg("inside saveCom  ");
	    	if( httpSession != null && user != null)
	    	{	
	    		WomTlCommunicationlog existWomTlCommunicationlog = (WomTlCommunicationlog)httpSession.getAttribute("womTlCommunicationlog"); 
	    		WomTlCommunicationlog newWomTlCommunicationlog = new WomTlCommunicationlog();
	    		newWomTlCommunicationlog.setWcmlCreatedby(user.getUsrm_ccno());
	    		String comTxt = request.getParameter("comTxt");
				String comEnteredBy= request.getParameter("comBy");
				String woNo  = request.getParameter("woNo");
				String commKeyID  = request.getParameter("commKeyID");
	    		newWomTlCommunicationlog.setWcmlCommunicationtext(comTxt);
	    		if (UIUtils.isValidKeyId(comEnteredBy))
	    			newWomTlCommunicationlog.setWcmlEnteredby( comEnteredBy);
	    		else
	    			newWomTlCommunicationlog.setWcmlEnteredby( user.getUsrm_ccno());
	    		newWomTlCommunicationlog.setWcmlWonumber(woNo);
	    		String departmentId = user.getUsrm_departmentid();
	    		if(UIUtils.isValidKeyId(departmentId))
	    			newWomTlCommunicationlog.setWcmlLevel(departmentId);
	    
	    		if (UIUtils.isValidKeyId(commKeyID))
	    			newWomTlCommunicationlog.setWcmlKeyid( commKeyID);
	    		try{
	    			existWomTlCommunicationlog =	breakDownService.saveCommTxt(newWomTlCommunicationlog,existWomTlCommunicationlog,bdFormBean);
	    			
	    			httpSession.setAttribute(existWomTlCommunicationlog.getWcmlKeyid(), existWomTlCommunicationlog);
					httpSession.setAttribute("WomTlCommunicationlog", existWomTlCommunicationlog);
					
					
					JSONObject successData = new JSONObject();
					successData.put("msg","Data Saved Successfully");
					successData.put("keyId", existWomTlCommunicationlog.getWcmlKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					
					out.print(returnData.toString());
					/*JSONObject mode = new JSONObject();
					mode.put("formMode",bdFormBean.getFormActionMode());
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("BDKeyid",existWomTlCommunicationlog.getWcmlKeyid());
					
					JSONObject forwardData = new JSONObject();
					forwardData.put("BDKeyid",existWomTlCommunicationlog.getWcmlKeyid());
					mode.put("forwardData",forwardData);
					mode.put("persistentData", persistentData);
					mode.put("tpmException", "Data  Saved");
					out.print(mode.toString());*/
	    		}
	    		catch(Exception e)
				{
	    			JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
	    	}
	    
		}
		private void deleteCom(HttpServletRequest request,HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
			HttpSession httpSession = request.getSession(false);	
			String commId=request.getParameter("commKeyID");
			String result="";
			try{
				result=breakDownService.deleteCommLog(commId);
				PrintWriter out = response.getWriter();
				JSONObject successData = new JSONObject();
				successData.put("msg",result);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
			catch(Exception e){
				e.getMessage();
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
				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
		
		private void exportToExcel(HttpServletRequest request,HttpServletResponse response,String Filter,String mode) throws Exception{
				HttpSession httpSession = request.getSession(false);
				CommonMessage.debugMsg("wo_getExcel.genmainRpt");
				CommonFilter commonFilter = populateCommonFilter(request,Filter,false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				String tableModel = (String) httpSession.getAttribute("BdColModel");
				CommonMessage.debugMsg("(String) httpSession.getAttribute(BdColModel)"+(String) httpSession.getAttribute("BdColModel"));
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("BdColModel");
				tblJSONObj.put("title", "Break Down "+ mode);
				String format = ExcelUtils.getFormat(request);
				Workbook wb = breakDownService.breakdownExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "BreakDown", format);
		}
		
		private JSONObject getTableModelSapInfo(List<String[]> kpivReportList,
				CommonFilter commonFilter) {
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = kpivReportList.get(0);	
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(false);
			jqGridTableModel.setRowNumbers(true);	
			//jqGridTableModel.setEnableFilter(false);
			
			for(int i =0; i <colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
				CommonMessage.debugMsg("SAOMAintOrdercolheader  "+i+" : "+colHeader[i]);//if i==0
				if(i==0){
					jqGridColModel.setWidth(250);				
					jqGridColModel.setAlign("left");
				}
				else if(i==1){
					jqGridColModel.setWidth(100);				
					jqGridColModel.setAlign("right");
				}							
				else if(i>=2)
				{
					jqGridColModel.setWidth(200);				
					jqGridColModel.setAlign("right");					
				}	
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
		private JSONObject getTableModelExeSub(List<String[]> kpivReportList,
				CommonFilter commonFilter) {
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = kpivReportList.get(0);	
			jqGridTableModel.setTableButton(false);			
			
			for(int i =0; i <colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
				CommonMessage.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
				if(i<=1){
					jqGridColModel.setHidden(true);
					jqGridColModel.setWidth(200);				
					jqGridColModel.setAlign("left");	
				}
				
				else if(i>=7){
					jqGridColModel.setWidth(200);				
					jqGridColModel.setAlign("left");
				}			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
		}


		private JSONObject getTableModelExtSer(List<String[]> grid, CommonFilter commonFilter) {

			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = grid.get(0);	
			jqGridTableModel.getRowHeaders().add(colHeader);
			
			jqGridTableModel.setTableButton(false);
			jqGridTableModel.setRowNumbers(true);	
			jqGridTableModel.setEnableFilter(true);
			for(int i =0; i <colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 50);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
				CommonMessage.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
				if(i<=2){
					jqGridColModel.setHidden(true);
					jqGridColModel.setWidth(200);				
					jqGridColModel.setAlign("left");
				
				}
				else if(i<=8){
					
					jqGridColModel.setWidth(100);				
					jqGridColModel.setAlign("left");				
				}
				if(i==colHeader.length-1) {
					jqGridColModel.setHidden(true);
				}
					
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}
	private JSONObject getTableModelExtRepair(List<String[]> grid, CommonFilter commonFilter) {

			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = grid.get(0);	
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);				
			
			for(int i =0; i <colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
						
				jqGridColModel.setWidth( 50);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
				CommonMessage.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
				if(i==0){
					jqGridColModel.setWidth(200);				
					jqGridColModel.setAlign("left");
				}
				else if(i<=19){
					jqGridColModel.setWidth(100);				
					jqGridColModel.setAlign("left");					
				}
				
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
}

			