package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_PMActivityWorkOrderService;
import com.akranta.tpm.service.impl.BAL_PMActivityWorkOrderServiceImpl;
//import com.akranta.tpm.service.impl.BAL_ToolMonitoringRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class PMActivityWorkOrder
 */
//@WebServlet("/PMActivityWorkOrderServlet")
public class BAL_PMActivityWorkOrderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BAL_PMActivityWorkOrderService pMActivityWorkOrderService;

    public BAL_PMActivityWorkOrderServlet() throws Exception {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
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
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!UIUtils.checkUserSession(request, response))
			return;
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		try {
			pMActivityWorkOrderService = (BAL_PMActivityWorkOrderServiceImpl) UIUtils.getServiceObject(request,"BAL_PMActivityWorkOrderServiceImpl");
			pMActivityWorkOrderService.BAL_PMActivityWorkOrderServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			
			//toolMonitoringRptService=(ToolMonitoringRptServiceImpl) UIUtils.getServiceObject(request, "ToolMonitoringRptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
	
		String action = UIUtils.getActionPart(request);
		if(action.equals("PmActvityWo_input.actWo"))
		{
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			
			//CommonFunctions.debugMsg(" in side PmActvityWo_input.actWo   " +action);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PmActivityWorkOrder.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("PmActvityWo_getCol.actWo"))
		{	
			try{					
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter",true);	
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
			commonFilter = FilterValues.getPMRelated (request, commonFilter);
			
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));

				  commonFilter.setMonwise("Y");
				  
		   }
		   else{
		 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
					 // commonFilter.setMonwise(" ");
			 	  }
		   }
			commonFilter.setIsGetCol("Y");
			CommonFunctions.debugMsg(" inside get col above method call" +commonFilter);
			List<String[]> activityList  = pMActivityWorkOrderService.getAllWoActivity(commonFilter);	  
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setPaginate(true);
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("MACHINE");
		
			gridColModel.setHeaderNum(1);
			String [] colHeader = activityList.get(1);			
			String [] colHeaderCond = activityList.get(0); 
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
	      	CommonFunctions.debugMsg("jsonObject ="+colHeader.toString());
	      	out.println(jsonObject);
		
			/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			httpSession.removeAttribute("ActivityWoCommonFilter");
			httpSession.setAttribute("ActivityWoCommonFilter", commonFilter);
			CommonFunctions.debugMsg(" at the end of get col method");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
			
		else if(action.equals("PmActvityWo_getData.actWo"))
		{
			CommonFunctions.debugMsg(" inside get datadata above method call");
			try
			{	
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				// request.getParameter("page");
				 //httpSession = request.getSession(false);
				 httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter",false);		

				commonFilter.setIsGetCol("N");
				List<String[]> employeeActivity  = pMActivityWorkOrderService.getAllWoActivity(commonFilter);
				JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
				
  				out.println(maintActivityData);
  				
  				httpSession.removeAttribute("ActivityWoCommonFilter");
  			 	httpSession.setAttribute("ActivityWoCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		else if( action.equals("PmActvityWo_getExcel.actWo"))
		{		
			
			
			CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter" ,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = UIUtils.getXlColModel(request,response); 
			CommonFunctions.debugMsg(" tableModel " + tableModel);
			String title = "PM Check Sheet Report";
						
			tableModel.put("title",title );
			String format = ExcelUtils.getFormat(request);			
			
			Workbook wb = pMActivityWorkOrderService.getAllWoActivityExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
			
		}
		else if(action.equals("pendingActvityWo_input.actWo"))
		{
			String filterStr = request.getParameter("filterString");
			request.setAttribute("filterStr", filterStr);
			
			//CommonFunctions.debugMsg(" in side PmActvityWo_input.actWo   " +action);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/PendingActivityWorkOrder.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("pendingActvityWo_getCol.actWo"))
		{	
			try{					
			PrintWriter out = response.getWriter();			
			CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter",true);	
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
			commonFilter = FilterValues.getPMRelated (request, commonFilter);
			
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getFirstDateofMonth(0).substring(3,11));

				  commonFilter.setMonwise("Y");
				  
		   }
		   else{
		 		 if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
					 // commonFilter.setMonwise(" ");
			 	  }
		   }
			commonFilter.setIsGetCol("Y");
			CommonFunctions.debugMsg(" inside get col above method call" +commonFilter);
			List<String[]> activityList  = pMActivityWorkOrderService.getPendingActivity(commonFilter);	  
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setPaginate(true);
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("MACHINE");
		
			gridColModel.setHeaderNum(1);
			String [] colHeader = activityList.get(1);			
			String [] colHeaderCond = activityList.get(0); 
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "80%%");
			jsonObject.put("tableWidth", "106%%");
	      	CommonFunctions.debugMsg("jsonObject ="+colHeader.toString());
	      	out.println(jsonObject);
		
			/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			httpSession.removeAttribute("ActivityWoCommonFilter");
			httpSession.setAttribute("ActivityWoCommonFilter", commonFilter);
			CommonFunctions.debugMsg(" at the end of get col method");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
			
		else if(action.equals("pendingActvityWo_getData.actWo"))
		{
			CommonFunctions.debugMsg(" inside get datadata above method call");
			try
			{	
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				// request.getParameter("page");
				 //httpSession = request.getSession(false);
				 httpSession = request.getSession();
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter",false);		
				commonFilter.setIsGetCol("N");	
				List<String[]> employeeActivity  = pMActivityWorkOrderService.getPendingActivity(commonFilter);
				
				for(String[] arr:employeeActivity) 
				{
					CommonFunctions.debugMsg(Arrays.toString(arr));
				}
				JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
				
  				out.println(maintActivityData);
  				
  				httpSession.removeAttribute("ActivityWoCommonFilter");
  			 	httpSession.setAttribute("ActivityWoCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		else if( action.equals("pendingActvityWo_getExcel.actWo"))
		{		
			
			
			CommonFilter commonFilter = populateCommonFilter(request,"ActivityWoCommonFilter" ,false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tableModel = UIUtils.getXlColModel(request,response); 
			CommonFunctions.debugMsg(" tableModel " + tableModel);
			String title = "PM Pending Activities Report";
						
			tableModel.put("title",title );
			String format = ExcelUtils.getFormat(request);			
			
			Workbook wb = pMActivityWorkOrderService.getPendingActivityExportExcel(commonFilter,tableModel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
			
		}
		
		else if( action.equals("filterXmlPmActvityWo_input.actWo")){
			response.setContentType("xml"); 
			System.out.println("action "+ action); 
			//out.print("<fromDate>01-Jan-2012</fromDate>");
			UIUtils.forwardRequest(request, response, "/tiles/xml/ActivityDownload.xml");
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
			commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	
	}
}
