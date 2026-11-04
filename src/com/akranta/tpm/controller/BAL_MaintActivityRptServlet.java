package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_MaintActivityService;
import com.akranta.tpm.service.impl.BAL_MaintActivityServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_MaintActivityRptServlet extends HttpServlet {
	
	/**
	 *   @ Author : Suresh.K
	 *   
	 *   Modified : Karthick.T
	 *   Date : 25-Nov-2011
	 *   
	 */
	private static final long serialVersionUID = 1L;
	
	
	BAL_MaintActivityService maintActivityService;
	public BAL_MaintActivityRptServlet() throws Exception{
		super();
	/*
		maintActivityService  = new MaintActivityServiceImpl();
		*/
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
		String action = UIUtils.getActionPart(request);
		try {
			maintActivityService  = (BAL_MaintActivityServiceImpl)UIUtils.getServiceObject(request,"BAL_MaintActivityServiceImpl");
			
			HttpSession h = request.getSession(false);

			maintActivityService.BAL_PmStandardServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
		
			if( action.equals("filterXmlMaintActivityReport_input.map")){			
				response.setContentType("xml"); 		
				UIUtils.forwardRequest(request, response, "/tiles/xml/ActivityWiseRpt.xml");			
			 }	
			/*
			else if(action.equals("MaintActivityReport_input.map")) 
			{
				String empwise = request.getParameter("empwise");
				request.setAttribute("employeewise", empwise);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/maintactivityrpt.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("MaintActivityReport_getCol.map") )
			{
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(true);
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilter",true);	
				
				
				String mchWise = commonFilter.getMachineWise();
				String EquiWise = commonFilter.getEquipmentWise();
				
				String colModel = null;//= "MaintainActivityWiseRpt";
				String empwise = request.getParameter("empwise");
				if(! "Y".equals(empwise)){
					if(mchWise != null && mchWise.equals("1")){
						colModel = "MaintainActivityWiseRpt";
						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
					}else{ 
						colModel = "MaintainWorkOrderWiseRpt";
					}
					
				}
				commonFilter.setEmpWise(empwise);
				List<String []> maintActivity  = maintActivityService.getAllMaintActivity(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(false);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setGroupBy(true);
				String groupByFld = "WoNo";
				if("Y".equalsIgnoreCase(empwise) )
					groupByFld ="Employee";
				jqGridTableModel.setGroupByField(groupByFld);
				jqGridTableModel.setGroupSummary(true);
				gridColModel.setHeaderNum(1);
				gridColModel.setSummaryTpl("<font size='2'><b>Total Duration</b></font>");
				gridColModel.setSummaryType("sum");
				String [] colHeader = maintActivity.get(1);			
				String [] colHeaderCond = maintActivity.get(0);
				
				//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "89%%");
				jsonObject.put("tableWidth", "106%%");
		      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
		      	out.println(jsonObject);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
			
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				httpSession.removeAttribute("ActivityWiseCommonFilter");
				httpSession.setAttribute("ActivityWiseCommonFilter", commonFilter);
			}
			else if( action.equals("MaintActivityReport_getData.map") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					 
					HttpSession httpSession = request.getSession(true);
				
					CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilter",false);		
						
					List<String[]> maintActivity  = maintActivityService.getAllMaintActivity(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(maintActivity,request,2,0,commonFilter.getTotalRecordCnt());
					
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("ActivityWiseCommonFilter");
	  			 	httpSession.setAttribute("ActivityWiseCommonFilter", commonFilter);
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}*/
			
			
			
				
			else if( action.equals("filterXmlElapsedActivityReport_input.map")){			
				response.setContentType("xml"); 		
				UIUtils.forwardRequest(request, response, "/tiles/xml/ActivityWiseRpt.xml");			
			 }	
			else if(action.equals("ElapsedActivityReport_input.map")) 
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/maintactivityrpt.jsp"); 
				rd.forward(request, response); 
			}
			else if( action.equals("ElapsedActivityReport_getCol.map") )
			{
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(true);
				CommonFilter commonFilter = new CommonFilter();
				
				commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilter",true);	
				
				commonFilter.setPmstatus("X");
				
				String mchWise = commonFilter.getMachineWise();
				String EquiWise = commonFilter.getEquipmentWise();
				CommonFunctions.debugMsg("mchWise..."+mchWise+"  -- "+EquiWise);
				String colModel = null;//= "MaintainActivityWiseRpt";
				if(mchWise != null && mchWise.equals("1"))
					colModel = "MaintainActivityWiseRpt";
				else 
					colModel = "MaintainWorkOrderWiseRpt";
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
			
				if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null ) ){
					  commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-1).substring(0,11));
					  commonFilter.setToDate(CommonFunctions.getDate().substring(0,11));
					  commonFilter.setMonwise("N");
			 	  }
				httpSession.removeAttribute("ActivityWiseCommonFilter");
				httpSession.setAttribute("ActivityWiseCommonFilter", commonFilter);
			}
			else if( action.equals("ElapsedActivityReport_getData.map") )
			{
				try
				{	
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter = new CommonFilter();
					HttpSession httpSession = request.getSession(true);
				
					commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilter",false);		
					commonFilter.setStatus("X");	
					List<String[]> maintActivity  = maintActivityService.getAllMaintActivity(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(maintActivity,request,0,1,commonFilter.getTotalRecordCnt());
					
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("ActivityWiseCommonFilter");
	  			 	httpSession.setAttribute("ActivityWiseCommonFilter", commonFilter);
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			
		/*	else if( action.equals("ElapsedActivityReport_getExcel.map"))
			{						
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				tblJSONObj.put("title", "Elapsed Activity Report");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = maintActivityService.ActivityWiseExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "ElapsedActivity", format);
				
			}*/
			else if(action.equals("MaintActivityReport_input.map"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/employeewiseactivityrpt.jsp"); 
				rd.forward(request, response);
			}
			else if(action.equals("MaintActivityReport_getCol.map"))
			{
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(true);
				CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter",true);	
				
				
				String mchWise = commonFilter.getMachineWise();
				String EquiWise = commonFilter.getEquipmentWise();
				
				String colModel = null;//= "MaintainActivityWiseRpt";
				String empwise = request.getParameter("empwise");
				CommonFunctions.debugMsg("empwise IN SIDE THE sERVLET " +empwise);
				if(! "Y".equals(empwise)){
					if(mchWise != null && mchWise.equals("1")){
						colModel = "MaintainActivityWiseRpt";
						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
					}else{ 
						colModel = "MaintainWorkOrderWiseRpt";
					}
					
				}
				commonFilter.setEmpWise(empwise);
				commonFilter.setIsGetCol("Y");
				List<String []> employeeActivity  = maintActivityService.getAllEmployeeActivity(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				/*jqGridTableModel.setGroupBy(true);
				String groupByFld = "WoNo";
				if("Y".equalsIgnoreCase(empwise) )
					groupByFld ="Employee";
				jqGridTableModel.setGroupByField(groupByFld);
				jqGridTableModel.setGroupSummary(true);
				gridColModel.setHeaderNum(1);
				gridColModel.setSummaryTpl("<font size='2'><b>Total Duration</b></font>");
				gridColModel.setSummaryType("sum");*/
				gridColModel.setHeaderNum(1);
				String [] colHeader = employeeActivity.get(1);			
				String [] colHeaderCond = employeeActivity.get(0);
				
				CommonFunctions.debugMsg("   TABLEMODEL     "+colHeader.length);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "80%%");
				jsonObject.put("tableWidth", "106%%");
		      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
		      	out.println(jsonObject);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
			
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				httpSession.removeAttribute("EmployeeActivityWiseCommonFilter");
				httpSession.setAttribute("EmployeeActivityWiseCommonFilter", commonFilter);
			}
			else if(action.equals("MaintActivityReport_getData.map"))
			{

				try
				{	
					PrintWriter out = response.getWriter();
					 
					HttpSession httpSession = request.getSession(true);
				
					CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter",false);		
					commonFilter.setIsGetCol("N");
					List<String[]> employeeActivity  = maintActivityService.getAllEmployeeActivity(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
					
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("EmployeeActivityWiseCommonFilter");
	  			 	httpSession.setAttribute("EmployeeActivityWiseCommonFilter", commonFilter);
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else if( action.equals("MaintActivityReport_getExcel.map"))
			{						
				CommonFilter commonFilter = populateCommonFilter(request,"EmployeeActivityWiseCommonFilter" +
						"",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tableModel = UIUtils.getXlColModel(request,response); //UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
				//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				CommonFunctions.debugMsg(" tableModel " + tableModel);
				String title = "Employee Wise Activity Report";
				//if("Y".equals(commonFilter.getEmpWise() ))
					//title = "Employee Wise Activity Report";*/
				
					tableModel.put("title",title );
				String format = ExcelUtils.getFormat(request);
				
				//tableModel.getJSONArray("colModel").getJSONObject(2).set("hidden", false);
				
				Workbook wb = maintActivityService.EmployeeWiseActivityRptExportExcel(commonFilter,tableModel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
				
			}
			else if(action.equals("employeeWiseExl_view.map"))
			{
				String employeeNumber=request.getParameter("empkeyId");
				String WONO=request.getParameter("WONo");
				String imagePath = UIUtils.getImagePath(request);
				String format = ExcelUtils.getFormat(request);
				format = "xlsx";
				String path = UIUtils.getExcelTemplatePath(request);  	
				Workbook wb = maintActivityService.getEmployeeWiseActivityExlView(employeeNumber,format,path,imagePath); 
				ExcelUtils.writeToResponse(response, wb, "EmployeeWiseActivity_"+employeeNumber, format);
			}
			
			
			else if(action.equals("activitywiserpt_input.map"))
			{
				CommonFunctions.debugMsg("Debug Statement");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/activityWiserpt.jsp"); 
				rd.forward(request, response);
			}
			else if(action.equals("activitywiserpt_getCol.map")){
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(true);
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilterTemp",true);	
				
				List<String []> employeeActivity  = maintActivityService.getActivityWiserpt(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				
				gridColModel.setHeaderNum(1);
				String [] colHeader = employeeActivity.get(1);			
				String [] colHeaderCond = employeeActivity.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("tableHeight", "80%%");
				jsonObject.put("tableWidth", "106%%");
		      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
		      	out.println(jsonObject);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", colModel));
			
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null ) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				httpSession.removeAttribute("ActivityWiseCommonFilterTemp");
				httpSession.setAttribute("ActivityWiseCommonFilterTemp", commonFilter);
			}
			else if(action.equals("activitywiserpt_getData.map")){

				try
				{	
					PrintWriter out = response.getWriter();
					 
					HttpSession httpSession = request.getSession(true);
				
					CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilterTemp",false);		
						
					List<String[]> employeeActivity  = maintActivityService.getActivityWiserpt(commonFilter);
					JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(employeeActivity,request,2,0,commonFilter.getTotalRecordCnt());
					
	  				out.println(maintActivityData);
	  				
	  				httpSession.removeAttribute("ActivityWiseCommonFilterTemp");
	  			 	httpSession.setAttribute("ActivityWiseCommonFilterTemp", commonFilter);
					
				}catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			else if( action.equals("activitywiserpt_getExcel.map"))
			{	
				//ServletResponse response;
				//PrintWriter out = response.getWriter();
				//HttpServletRequest request;
				//HttpSession httpSession = request.getSession(true);
				CommonFilter commonFilter = populateCommonFilter(request,"ActivityWiseCommonFilterTemp",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject tableModel = UIUtils.getXlColModel(request,response); //UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
				//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				CommonFunctions.debugMsg(" tableModel " + tableModel);
				String title = "ActivityWise Report";
				/*if("Y".equals(commonFilter.getEmpWise() ))
					title = "Employee Wise Activity Report";*/
				tableModel.put("title",title );
				String format = ExcelUtils.getFormat(request);
				
				//tableModel.getJSONArray("colModel").getJSONObject(2).set("hidden", false);
				
				Workbook wb = maintActivityService.activityWiseRptExportExcel(commonFilter,tableModel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, title.replaceAll(" ", ""), format);
				
			}	
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
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


