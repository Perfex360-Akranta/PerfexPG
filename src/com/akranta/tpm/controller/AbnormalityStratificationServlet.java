package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AbnStraficationService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.AbnStraficationServiceImpl;
//import com.akranta.tpm.service.impl.BreakdownServiceImpl;*/
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
//import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
//import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

public class AbnormalityStratificationServlet extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	//CommonFilter commonFilter;
	AbnStraficationService abnStraficationService; 
	DashboardService dashboardService;
	public AbnormalityStratificationServlet() throws Exception
	{
		super();		
		//commonFilter=new CommonFilter();
		//abnStraficationService = new AbnStraficationServiceImpl();
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);		
		
		try {
			
			abnStraficationService = (AbnStraficationServiceImpl)UIUtils.getServiceObject(request,"AbnStraficationServiceImpl");
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			abnStraficationService.AbnStraficationServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
	if( action.equals("filterXmlAbnStratificationRpt_input.abnStrRpt") || action.equals("filterXmlHSE_AbnStratificationRpt_input.abnStrRpt") 
				|| (action.equals("filterXmlAbnHTAStartification_input.abnStrRpt")) || (action.equals("filterXmlAbnSocStartification_getCol.abnStrRpt"))
				|| (action.equals("filterXmlAbnUNSStartification_input.abnStrRpt"))){
			 response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/AbnStartfication.xml");
		 }
		
		else if(action.equals("AbnStratificationRpt_input.abnStrRpt") 
			||action.equals("HSE_AbnStratificationRpt_input.abnStrRpt")
				|| (action.equals("AbnHTAStartification_input.abnStrRpt"))  || (action.equals("AbnSocStartification_input.abnStrRpt"))
				|| (action.equals("AbnUNSStartification_input.abnStrRpt"))) {
		
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/AbnStratificationRpt.jsp");
			
		if (action.equals("HSE_AbnStratificationRpt_input.abnStrRpt")) 
				request.setAttribute("AbnType", "SHE");
			else if(action.equals("AbnHTAStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "HTA");
			else if(action.equals("AbnSocStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "SOC");
			else if(action.equals("AbnUNSStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "UNS");     
			
			
			rd.forward(request, response); 
		}
	//AbnStratificationRptType_input.abnStrRpt
	
		/***/   //***********/
		else if (action.equals("StratificationvsCom_input.abnStrRpt"))
	/*		||action.equals("HSE_AbnStratificationRpt_input.abnStrRpt")
				|| (action.equals("AbnHTAStartification_input.abnStrRpt"))  || (action.equals("AbnSocStartification_input.abnStrRpt"))
				|| (action.equals("AbnUNSStartification_input.abnStrRpt")))*/
				{
			RequestDispatcher rd = request.getRequestDispatcher("pages/StratificationvsCom.jsp");
			
			if (action.equals("HSE_AbnStratificationRpt_input.abnStrRpt")) 
				request.setAttribute("AbnType", "SHE");
			else if(action.equals("AbnHTAStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "HTA");
			else if(action.equals("AbnSocStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "SOC");
			else if(action.equals("AbnUNSStartification_input.abnStrRpt"))
				request.setAttribute("AbnType", "UNS");           
			
			rd.forward(request, response);
			CommonMessage.debugMsg("input");
		}
		/***/
	
		else if(action.equals("AbnStratificationRptType_input.abnStrRpt")) {
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/AbnStratificationRptType.jsp");
			rd.forward(request, response);
		}
	//http://localhost:8080/perfexitc/AbnStratificationRptImpact_input.abnStrRpt
		else if(action.equals("AbnStratificationRptImpact_input.abnStrRpt")) {
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/AbnStratificationRptImpact.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("StratificationvsCom_getCol.abnStrRpt")){	
				PrintWriter out = response.getWriter();
				JSONObject abnStratification = new JSONObject();
				CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",true);
				 String type= request.getParameter("abntype");
				 commonFilter.setStatus(type);
				 commonFilter.setIsGetCol("Y");
				List<String[]> abnStrafication  = (List<String[]>)abnStraficationService.getAlStrafication(commonFilter);
				if(abnStrafication.size()>3)
				abnStratification = UIUtils.convertToJqGridTableObject(abnStrafication,request,4,0,commonFilter.getTotalRecordCnt());
				httpSession.setAttribute("abnStrfcnReportServletdata", abnStratification);
			    JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);	
				
				String abn = commonFilter.getChkAssm();
				String groupByCircle = commonFilter.getGroupByCircle();
				if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
				{
					jqGridTableModel.setRowNumbers(true);
				}
				else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
				{
					jqGridTableModel.setGroupBy(true);
					jqGridTableModel.setGroupByField("Circle");		 
					jqGridTableModel.setGroupSummary(false);	
				}
				else{
				jqGridTableModel.setGroupBy(false);
				jqGridTableModel.setGroupByField("TITLE");		 
				jqGridTableModel.setGroupSummary(false);	
				}
				gridColModel.setHeaderNum(3);
				String [] colHeaderCond = abnStrafication.get(0);			
				String [] colHeader= abnStrafication.get(1);
				String [] colHeader1 = abnStrafication.get(2);
				String [] colHeader2 = abnStrafication.get(3);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				headers.add(colHeader1);
				headers.add(colHeader2);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.put("data", abnStrafication);
				jsonObject.set("tableHeight", "66%%");
		      	jsonObject.set("tableWidth", "106%%");
		   		httpSession.removeAttribute("AbnColModel");
				httpSession.setAttribute("AbnColModel",jsonObject);
				CommonMessage.debugMsg("Table :"+jsonObject);
				out.println(jsonObject);		
		}
		else if( action.equals("StratificationvsCom_getData.abnStrRpt")){
		   
			try
			{ 
				 UIUtils.displayRequestParamsValue(request);
				 PrintWriter out = response.getWriter();	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
				 JSONObject jsonObject = null;
				 String type= request.getParameter("abntype");
				 commonFilter.setStatus(type);
				 commonFilter.setIsGetCol("N");
				     List<String[]> abnStratification  =  abnStraficationService.getAlStrafication(commonFilter);
				     jsonObject = UIUtils.convertToJqGridTableObject(abnStratification,request,4,0,commonFilter.getTotalRecordCnt()+4);
				 CommonMessage.debugMsg("Table :"+jsonObject);
				 out.println(jsonObject);
				 CommonMessage.debugMsg("#AbnType value#::" + commonFilter.getStatus()  +", End  relased:"+ CommonFunctions.dateTimeNow());
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("AbnormalityStratificationCommonFilter");
	  			 httpSession.setAttribute("AbnormalityStratificationCommonFilter", commonFilter);
		    }
			catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	
		else if(action.equals("StratificationvsCom_getExcel.abnStrRpt")){
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject colModel = (JSONObject)httpSession.getAttribute("AbnColModel");
			colModel.put("title", "Stratification Identified Vs Completed");
			String format = ExcelUtils.getFormat(request);
		//	JSONArray header2 = (JSONArray)colModel.getJSONArray("rowHeaders").get(1);
		//	header2.put(1,"");
		//	header2.put(2,"");
		//	header2.put(3,"");
		//	tblJSONObj.getJSONArray("rowHeaders").put(1,header2);
			Workbook wb = abnStraficationService.StartificationIdeVsComExportExcel(commonFilter,colModel,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "AbnormalityStartificationReport", format);
			
			/* 	httpSession = request.getSession(false);
					CommonFilter commonFilter1 = populateCommonFilter(request,"NearmissReportCommonFilter",false);
					String tmpFromRow = commonFilter1.getFromRow();
					JSONObject colmodel = UIUtils.getXlColModel(request,response);
					colmodel.put("title","Near Miss Cumulative Count Report");
		            String format = ExcelUtils.getFormat(request);
					Workbook wb = NewNearMissreportservice.getNewNearCumCountExportToExcel(colmodel,format,commonFilter1);
					commonFilter1.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "NearMissCumulativeCountReport", format); */
		}
		
		else if(action.equals("HSE_AbnStratificationRpt_getExcel.abnStrRpt") 
					|| action.equals("AbnHTAStartification_getExcel.abnStrRpt")  || (action.equals("AbnSocStartification_getExcel.abnStrRpt"))
					|| (action.equals("AbnUNSStartification_getExcel.abnStrRpt")))
					{
				
				//HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
				
				tblJSONObj.put("title", "Abnormality Stratification Report");
				String format = ExcelUtils.getFormat(request);
				/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
					  CommonMessage.debugMsg("passNullDate");
			 	  }*/
				 
				JSONArray header2 = (JSONArray)tblJSONObj.getJSONArray("rowHeaders").get(1);
				header2.put(1,"");
				header2.put(2,"");
				header2.put(3,"");
				tblJSONObj.getJSONArray("rowHeaders").put(1,header2);
				Workbook wb = abnStraficationService.StartificationReportExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "AbnormalityStartificationReport", format);
				
			}
		/***/  ///*****************///
		
		
		else if(action.equals("AbnStratificationRpt_getCol.abnStrRpt")
				|| action.equals("HSE_AbnStratificationRpt_getCol.abnStrRpt")
				|| (action.equals("AbnHTAStartification_getCol.abnStrRpt")) || (action.equals("AbnSocStartification_getCol.abnStrRpt"))
				|| (action.equals("AbnUNSStartification_getCol.abnStrRpt"))) 
		{	
			PrintWriter out = response.getWriter();
			//populateCommonFilter(request,"AbnCommonFilter",true);	
			JSONObject abnStratification = new JSONObject();
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",true);
			CommonMessage.debugMsg("1step");
			commonFilter.setIsGetCol("Y");
			List<String[]> abnStrafication  = (List<String[]>)abnStraficationService.getAllAbnStrafication(commonFilter);
			if(abnStrafication.size()>3)
			abnStratification = UIUtils.convertToJqGridTableObject(abnStrafication,request,4,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abnStrfcnReportServletdata", abnStratification);
			//JSONObject jsonObject = getTableModel(abnStrafication,commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);	
			
			String abn = commonFilter.getChkAssm();
			String groupByCircle = commonFilter.getGroupByCircle();
			if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
			{
				jqGridTableModel.setRowNumbers(true);
			}
			else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
			{
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("Circle");		 
				jqGridTableModel.setGroupSummary(false);	
			}
			else{
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("TITLE");		 
			jqGridTableModel.setGroupSummary(false);	
			}
			
			
			gridColModel.setHeaderNum(2);
			
			String [] colHeaderCond = abnStrafication.get(0);			
			String [] colHeader= abnStrafication.get(1);
			String [] colHeader1 = abnStrafication.get(2);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			
			jsonObject.put("data", abnStratification);
			jsonObject.set("tableHeight", "66%%");
	      	jsonObject.set("tableWidth", "106%%");
			
	      	CommonMessage.debugMsg("jsonObject  "+jsonObject);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);
			//jsonObject.put("tableButton", true);			
			
			out.println(jsonObject);
		
		}
		
		else if(action.equals("AbnStratificationRptType_getCol.abnStrRpt"))
	
		{	
			PrintWriter out = response.getWriter();
			//populateCommonFilter(request,"AbnCommonFilter",true);	
			JSONObject abnStratification = new JSONObject();
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",true);
			CommonMessage.debugMsg("1step");
			commonFilter.setIsGetCol("Y");
			List<String[]> abnStrafication  = (List<String[]>)abnStraficationService.getAllAbnStraficationType(commonFilter);
			if(abnStrafication.size()>3)
			abnStratification = UIUtils.convertToJqGridTableObject(abnStrafication,request,4,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abnStrfcnReportServletdata", abnStratification);
			//JSONObject jsonObject = getTableModel(abnStrafication,commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);	
			
			String abn = commonFilter.getChkAssm();
			String groupByCircle = commonFilter.getGroupByCircle();
			if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
			{
				jqGridTableModel.setRowNumbers(true);
			}
			else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
			{
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("Circle");		 
				jqGridTableModel.setGroupSummary(false);	
			}
			else{
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("TITLE");		 
			jqGridTableModel.setGroupSummary(false);	
			}
			
			
			gridColModel.setHeaderNum(2);
			
			String [] colHeaderCond = abnStrafication.get(0);			
			String [] colHeader= abnStrafication.get(1);
			String [] colHeader1 = abnStrafication.get(2);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			
			jsonObject.put("data", abnStratification);
			jsonObject.set("tableHeight", "66%%");
	      	jsonObject.set("tableWidth", "106%%");
			
	      	CommonMessage.debugMsg("jsonObject  "+jsonObject);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);
			//jsonObject.put("tableButton", true);			
			
			out.println(jsonObject);
		
		}
		
		else if(action.equals("AbnStratificationRptImpact_getCol.abnStrRpt"))
			
		{	
			PrintWriter out = response.getWriter();
			//populateCommonFilter(request,"AbnCommonFilter",true);	
			JSONObject abnStratification = new JSONObject();
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",true);
			CommonMessage.debugMsg("1step");
			commonFilter.setIsGetCol("Y");
			List<String[]> abnStrafication  = (List<String[]>)abnStraficationService.getAllAbnStraficationImpact(commonFilter);
			CommonMessage.debugMsg("result :"+ abnStrafication);
			if(abnStrafication.size()>3)
			abnStratification = UIUtils.convertToJqGridTableObject(abnStrafication,request,5,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abnStrfcnReportServletdata", abnStratification);
			//JSONObject jsonObject = getTableModel(abnStrafication,commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);	
			
			String abn = commonFilter.getChkAssm();
			String groupByCircle = commonFilter.getGroupByCircle();
			if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
			{
				jqGridTableModel.setRowNumbers(true);
			}
			else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
			{
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("Circle");		 
				jqGridTableModel.setGroupSummary(false);	
			}
			else{
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("TITLE");		 
			jqGridTableModel.setGroupSummary(false);	
			}
			
			
			gridColModel.setHeaderNum(2);
			
			String [] colHeaderCond = abnStrafication.get(0);			
			String [] colHeader= abnStrafication.get(1);
			String [] colHeader1 = abnStrafication.get(2);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeader1);
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			
			jsonObject.put("data", abnStratification);
			jsonObject.set("tableHeight", "66%%");
	      	jsonObject.set("tableWidth", "106%%");
			
	      	CommonMessage.debugMsg("jsonObject  "+jsonObject);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);
			//jsonObject.put("tableButton", true);			
			
			out.println(jsonObject);
		
		}
		

		else if( action.equals("AbnStratificationRpt_getData.abnStrRpt") 
				||	action.equals("HSE_AbnStratificationRpt_getData.abnStrRpt") 
				|| action.equals("AbnHTAStartification_getData.abnStrRpt") ||   (action.equals("AbnSocStartification_getData.abnStrRpt"))
				|| (action.equals("AbnUNSStartification_getData.abnStrRpt")))
			
		{
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 PrintWriter out = response.getWriter();
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 //CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("AbnCommonFilter");
				 
				/* String isHSE = request.getParameter("isHSE");			
				CommonMessage.debugMsg("isHSE"+isHSE);
				commonFilter.setAbnIsHSE(isHSE);*/
				 CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
				 JSONObject jsonObject = null;
				 /*if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("abnStrfcnReportServletdata");
	        	 else
	        	 {*/
				 commonFilter.setIsGetCol("N");
				     List<String[]> abnStratification  =  abnStraficationService.getAllAbnStrafication(commonFilter);
				     jsonObject = UIUtils.convertToJqGridTableObject(abnStratification,request,3,0,commonFilter.getTotalRecordCnt()+3);
				     //jsonObject = convertToJqGridTable(abnStratification,request,2,0,commonFilter.getTotalRecordCnt());
	        	 //}
				     CommonMessage.debugMsg("passNullDatecheck-in-getData-2");
				  CommonMessage.debugMsg(jsonObject);
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("AbnormalityStratificationCommonFilter");
	  			 httpSession.setAttribute("AbnormalityStratificationCommonFilter", commonFilter);
		    }
			catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	
		else if( action.equals("AbnStratificationRptType_getData.abnStrRpt"))
			
		{
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 PrintWriter out = response.getWriter();
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 //CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("AbnCommonFilter");
				 
				/* String isHSE = request.getParameter("isHSE");			
				CommonMessage.debugMsg("isHSE"+isHSE);
				commonFilter.setAbnIsHSE(isHSE);*/
				 CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
				 JSONObject jsonObject = null;
				 /*if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("abnStrfcnReportServletdata");
	        	 else
	        	 {*/
				 commonFilter.setIsGetCol("N");
				     List<String[]> abnStratification  =  abnStraficationService.getAllAbnStraficationType(commonFilter);
				     jsonObject = UIUtils.convertToJqGridTableObject(abnStratification,request,3,0,commonFilter.getTotalRecordCnt()+3);
				     //jsonObject = convertToJqGridTable(abnStratification,request,2,0,commonFilter.getTotalRecordCnt());
	        	 //}
				     CommonMessage.debugMsg("passNullDatecheck-in-getData-2");
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("AbnormalityStratificationCommonFilter");
	  			 httpSession.setAttribute("AbnormalityStratificationCommonFilter", commonFilter);
		    }
			catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	
	else if( action.equals("AbnStratificationRptImpact_getData.abnStrRpt"))
			
		{
			try
			{
				CommonMessage.debugMsg("Chk the get data");
				 UIUtils.displayRequestParamsValue(request);
				 PrintWriter out = response.getWriter();
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 //CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("AbnCommonFilter");
				 
				/* String isHSE = request.getParameter("isHSE");			
				CommonMessage.debugMsg("isHSE"+isHSE);
				commonFilter.setAbnIsHSE(isHSE);*/
				 CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
				 JSONObject jsonObject = null;
				 /*if(page.equals("1"))
	        		 jsonObject = (JSONObject) httpSession.getAttribute("abnStrfcnReportServletdata");
	        	 else
	        	 {*/
				 commonFilter.setIsGetCol("N");
				     List<String[]> abnStratification  =  abnStraficationService.getAllAbnStraficationImpact(commonFilter);
				     jsonObject = UIUtils.convertToJqGridTableObject(abnStratification,request,3,0,commonFilter.getTotalRecordCnt()+4);
				     //jsonObject = convertToJqGridTable(abnStratification,request,2,0,commonFilter.getTotalRecordCnt());
	        	 //}
				 //    CommonMessage.debugMsg("passNullDatecheck-in-getData-2");
				     CommonMessage.debugMsg(jsonObject);
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("AbnormalityStratificationCommonFilter");
	  			 httpSession.setAttribute("AbnormalityStratificationCommonFilter", commonFilter);
		    }
			catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	
	
	
		else if( action.equals("AbnStratificationRpt_getExcel.abnStrRpt") || (action.equals("AbnStratificationRptType_getExcel.abnStrRpt"))
				|| (action.equals("AbnStratificationRptImpact_getExcel.abnStrRpt")) || action.equals("HSE_AbnStratificationRpt_getExcel.abnStrRpt") 
				|| action.equals("AbnHTAStartification_getExcel.abnStrRpt")  || (action.equals("AbnSocStartification_getExcel.abnStrRpt"))
				|| (action.equals("AbnUNSStartification_getExcel.abnStrRpt")))
				{
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
			
			if(action.equals("AbnStratificationRptType_getExcel.abnStrRpt")) {
				commonFilter.setStatus("AbnStratificationRptType");
			}else if(action.equals("AbnStratificationRptImpact_getExcel.abnStrRpt")) {
				commonFilter.setStatus("AbnStratificationRptImpact");
			}
			
			tblJSONObj.put("title", "Abnormality Stratification Report");
			String format = ExcelUtils.getFormat(request);
			/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
				  CommonMessage.debugMsg("passNullDate");
		 	  }*/
			 
			JSONArray header2 = (JSONArray)tblJSONObj.getJSONArray("rowHeaders").get(1);
			header2.put(1,"");
			header2.put(2,"");
			header2.put(3,"");
			tblJSONObj.getJSONArray("rowHeaders").put(1,header2);
			Workbook wb = abnStraficationService.AbnStartificationReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "AbnormalityStartificationReport", format);
			
		}
	
		
	else if( action.equals("chart.abnStrRpt")){
			
		CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		
			
		}
		
		
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		
		List<String[]> graphData  =  abnStraficationService.getAllAbnStrafication(commonFilter);
		
		PrintWriter out = response.getWriter();
		
		
		String[] header = graphData.get(1);
		String[] header2 = graphData.get(2);
		String[] total = graphData.get(3);
		
		int centerVal = 140;
		String prevPillar = null;
		String chkVal = request.getParameter("checkZero");
		String chkPercentage = request.getParameter("chkPercentage");
		JSONArray chartData = new JSONArray();
		List<Object[]> dataList = new ArrayList<Object[]>();
		/*int categoryTotalFlag = total.length-3;
		int impactTotalFlag = total.length-3;
		int typeTotalFlag = total.length-3;
		String headerFlag = header[4];
		int flag = 0;
		for(int i=4;i<header.length-1;i++){
			if(!headerFlag.equals(header[i]))
			{
				flag++;
				if(flag == 1)
					categoryTotalFlag = i-1;
				else if(flag == 2)
					impactTotalFlag = i-1;
				else if(flag == 3)
					typeTotalFlag = i-1;
			}
			headerFlag = header[i];
		}*/
		
			for(int j=5;j<total.length;j++){
			if( j< total.length && (prevPillar == null || (header[j].equals(prevPillar))&& !header2[j].equals("TOTAL") )){
				if(chkVal.equals("N")){
					if(!total[j].equals("0") )
					{	
						
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								/*int arrVal = total.length-3;
								if(header[j].indexOf("CATEGORY")>0)
									arrVal = categoryTotalFlag;
								if(header[j].indexOf("IMPACT")>0)
									arrVal = impactTotalFlag;
								if(header[j].indexOf("TYPE")>0)
									arrVal = typeTotalFlag;*/
								CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
								totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								//totalVal = (double) Math.round(totalVal);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
								CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
							}
						}
								
						data[ 1 ] = totalVal;	
						
						dataList.add(data);
					}}
					else
					{
						//if(!total[j].equals("0") )
						//{	
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						CommonMessage.debugMsg("totalVal..: "+totalVal);
						CommonMessage.debugMsg("chkPercentage : "+chkPercentage);
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								
								
								if(Integer.parseInt(total[totalCol(header,j)])>0)
									totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
							}
						}
								
						data[ 1 ] = totalVal;					
						dataList.add(data);
					//	}
					}
			}	
			else{
				ChartPie pieChart =  new   ChartPie();
				List<Integer> center = new ArrayList<Integer>();
				ChartSeries chartSeries = new ChartSeries();
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeries.setSize(200);
				
				center.add(520);
				center.add(centerVal);
				//centerVal = 450;
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				chartSeries.setCenter(center);
				chartSeriesList.add(chartSeries);
				dataList = new ArrayList<Object[]>();	
				
				//pieChart.setHeight(1400);
				pieChart.setWidth(1100);
				JSONObject pieData = pieChart.drawChart(chartSeriesList, prevPillar, "");
				chartData.put(pieData);
				UIUtils.dashBoardSetChartObject(request,pieData);
				dataList = new ArrayList<Object[]>();
				CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
				
			}
			if( j< total.length-1 )
			{
				prevPillar = header[j];
				if(header2[j].equals("TOTAL"))
					prevPillar = header[j+1];
			}
			CommonMessage.debugMsg("prevPillar.."+prevPillar);
		}
		
			
		out.println(chartData);
		out.close();
	  }
	
	
	else if( action.equals("chartAbnStratificationRptType.abnStrRpt")){
		
		CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		String flids=CommonFunctions.getLoginFlid(request);
		String lcnname=dashboardService.Functionallocn(flids);
		String forDashboard = request.getParameter("dashboard");
		
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		
			
		}
		
		
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		
		List<String[]> graphData  =  abnStraficationService.getAllAbnStraficationType(commonFilter);
		
		PrintWriter out = response.getWriter();
		
		
		String[] header = graphData.get(1);
		String[] header2 = graphData.get(2);
		String[] total = graphData.get(3);
		
		int centerVal = 140;
		String prevPillar = null;
		String chkVal = request.getParameter("Y");
		String chkPercentage = request.getParameter("Y");
		JSONArray chartData = new JSONArray();
		List<Object[]> dataList = new ArrayList<Object[]>();
		/*int categoryTotalFlag = total.length-3;
		int impactTotalFlag = total.length-3;
		int typeTotalFlag = total.length-3;
		String headerFlag = header[4];
		int flag = 0;
		for(int i=4;i<header.length-1;i++){
			if(!headerFlag.equals(header[i]))
			{
				flag++;
				if(flag == 1)
					categoryTotalFlag = i-1;
				else if(flag == 2)
					impactTotalFlag = i-1;
				else if(flag == 3)
					typeTotalFlag = i-1;
			}
			headerFlag = header[i];
		}*/
		
			for(int j=5;j<total.length;j++){
			if( j< total.length && !header2[j].equals("TOTAL") ){
				//if(chkVal.equals("N")){
					if(!total[j].equals("0") )
					{	
						
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								/*int arrVal = total.length-3;
								if(header[j].indexOf("CATEGORY")>0)
									arrVal = categoryTotalFlag;
								if(header[j].indexOf("IMPACT")>0)
									arrVal = impactTotalFlag;
								if(header[j].indexOf("TYPE")>0)
									arrVal = typeTotalFlag;*/
								CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
								totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								//totalVal = (double) Math.round(totalVal);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
								CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
							}
						}
								
						data[ 1 ] = totalVal;	
						
						dataList.add(data);
					}
					//}
					else
					{
						//if(!total[j].equals("0") )
						//{	
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						CommonMessage.debugMsg("totalVal..: "+totalVal);
						CommonMessage.debugMsg("chkPercentage : "+chkPercentage);
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								
								
								if(Integer.parseInt(total[totalCol(header,j)])>0)
									totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
							}
						}
								
						data[ 1 ] = totalVal;					
						dataList.add(data);
					//	}
					}
			}	
			else{
				ChartPie pieChart =  new   ChartPie();
				List<Integer> center = new ArrayList<Integer>();
				ChartSeries chartSeries = new ChartSeries();
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeries.setSize(200);
				
			//	center.add(520);
				//center.add(centerVal);
				//centerVal = 450;
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				chartSeries.setCenter(center);
				chartSeriesList.add(chartSeries);
				dataList = new ArrayList<Object[]>();	
				
				//pieChart.setHeight(1400);
			// 	pieChart.setWidth(1100);
				JSONObject pieData = pieChart.drawChart(chartSeriesList, lcnname+"-Abnormality Stratification Type","");
				UIUtils.setDashBoardIdentifier(request,pieData);
				
				out.println(pieData);
				out.close();
			}
				/*chartData.put(pieData);
				UIUtils.dashBoardSetChartObject(request,pieData);
				dataList = new ArrayList<Object[]>();
				CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
				
			}
			if( j< total.length-1 )
			{
				prevPillar = header[j];
				if(header2[j].equals("TOTAL"))
					prevPillar = header[j+1];
			}
			CommonMessage.debugMsg("prevPillar.."+prevPillar);
		}
		
		//	}
		out.println(chartData);
		out.close();*/
	  }
	}
	
else if( action.equals("chartAbnStratificationRptImpact.abnStrRpt")){
		
	CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
	
	String forDashboard = request.getParameter("dashboard");
	String flids=CommonFunctions.getLoginFlid(request);
	String lcnname=dashboardService.Functionallocn(flids);
	CommonMessage.debugMsg("forDashboard....."+forDashboard);
	if( ! "true".equals(forDashboard)){
		CommonMessage.debugMsg("to check dash board");
		commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
	}
	else{
		commonFilter = new  CommonFilter();
		CommonMessage.debugMsg("to check dash board true ");
		FilterValues.getCommonFilters(request,commonFilter);
		FilterValues.getAbnRelatedFilters(request, commonFilter);
	
		
	}
	
	CommonFilter chrtCommonFilter = new CommonFilter(); 
	String toMonth = chrtCommonFilter.getToMonth();
	CommonMessage.debugMsg("The ToMonth"+toMonth);
	String todate = chrtCommonFilter.getToDate();
	CommonMessage.debugMsg("The ToDate"+todate);
	String Title=toMonth+todate;
	
	
	if(commonFilter.getRowTotal() == null )
		commonFilter.setRowTotal('Y');
	
	List<String[]> graphData  =  abnStraficationService.getAllAbnStraficationImpact(commonFilter);
	
	PrintWriter out = response.getWriter();
	
	
	String[] header = graphData.get(1);
	String[] header2 = graphData.get(2);
	String[] total = graphData.get(3);
	
	int centerVal = 140;
	String prevPillar = null;
	String chkVal = request.getParameter("Y");
	String chkPercentage = request.getParameter("Y");
	JSONArray chartData = new JSONArray();
	List<Object[]> dataList = new ArrayList<Object[]>();
	/*int categoryTotalFlag = total.length-3;
	int impactTotalFlag = total.length-3;
	int typeTotalFlag = total.length-3;
	String headerFlag = header[4];
	int flag = 0;
	for(int i=4;i<header.length-1;i++){
		if(!headerFlag.equals(header[i]))
		{
			flag++;
			if(flag == 1)
				categoryTotalFlag = i-1;
			else if(flag == 2)
				impactTotalFlag = i-1;
			else if(flag == 3)
				typeTotalFlag = i-1;
		}
		headerFlag = header[i];
	}*/
	
		for(int j=5;j<total.length;j++){
		if( j< total.length && !header2[j].equals("TOTAL") ){
			//if(chkVal.equals("N")){
				if(!total[j].equals("0") )
				{	
					
					Object []  data = new Object[2];
					data[ 0 ] = header2[j];
					Double totalVal =  Double.parseDouble(total[j]);	
					
					if(UIUtils.isValidKeyId(chkPercentage))
					{
						if(chkPercentage.equals("Y")){
							totalVal = totalVal*100;
							CommonMessage.debugMsg("totalVal*100 : "+totalVal);
							/*int arrVal = total.length-3;
							if(header[j].indexOf("CATEGORY")>0)
								arrVal = categoryTotalFlag;
							if(header[j].indexOf("IMPACT")>0)
								arrVal = impactTotalFlag;
							if(header[j].indexOf("TYPE")>0)
								arrVal = typeTotalFlag;*/
							CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
							totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
							//totalVal = (double) Math.round(totalVal);
							totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
							CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
						}
					}
							
					data[ 1 ] = totalVal;	
					
					dataList.add(data);
				}
				//}
				else
				{
					//if(!total[j].equals("0") )
					//{	
					Object []  data = new Object[2];
					data[ 0 ] = header2[j];
					Double totalVal =  Double.parseDouble(total[j]);	
					CommonMessage.debugMsg("totalVal..: "+totalVal);
					CommonMessage.debugMsg("chkPercentage : "+chkPercentage);
					if(UIUtils.isValidKeyId(chkPercentage))
					{
						if(chkPercentage.equals("Y")){
							totalVal = totalVal*100;
							CommonMessage.debugMsg("totalVal*100 : "+totalVal);
							
							
							if(Integer.parseInt(total[totalCol(header,j)])>0)
								totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
							totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
						}
					}
							
					data[ 1 ] = totalVal;					
					dataList.add(data);
				//	}
				}
		}	
		else{
			ChartPie pieChart =  new   ChartPie();
			List<Integer> center = new ArrayList<Integer>();
			ChartSeries chartSeries = new ChartSeries();
			chartSeries.setType(ChartTypes.PIE);
			chartSeries.setData(dataList);
			chartSeries.setSize(200);
			
		//	center.add(520);
			//center.add(centerVal);
			//centerVal = 450;
			List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
			chartSeries.setCenter(center);
			chartSeriesList.add(chartSeries);
			dataList = new ArrayList<Object[]>();	
			
			//pieChart.setHeight(1400);
		// 	pieChart.setWidth(1100);
			
			
			JSONObject pieData = pieChart.drawChart(chartSeriesList,lcnname+ "-Abnormality Stratification Impact","");
			UIUtils.setDashBoardIdentifier(request,pieData);
			
			out.println(pieData);
			out.close();
		}
			/*chartData.put(pieData);
			UIUtils.dashBoardSetChartObject(request,pieData);
			dataList = new ArrayList<Object[]>();
			CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
			
		}
		if( j< total.length-1 )
		{
			prevPillar = header[j];
			if(header2[j].equals("TOTAL"))
				prevPillar = header[j+1];
		}
		CommonMessage.debugMsg("prevPillar.."+prevPillar);
	}
	
	//	}
	out.println(chartData);
	out.close();*/
  }
	}
	
    /*******************************************************************************/
	else if( action.equals("chart.abnStrRpt")){
		
		CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		
			
		}
		
		
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		
		List<String[]> graphData  =  abnStraficationService.getAlStrafication(commonFilter);
		
		PrintWriter out = response.getWriter();
		
		
		String[] header = graphData.get(2);
		String[] header2 = graphData.get(3);
		String[] total = graphData.get(4);
		
		int centerVal = 140;
		String prevPillar = null;
		String chkVal = request.getParameter("checkZero");
		String chkPercentage = request.getParameter("chkPercentage");
		JSONArray chartData = new JSONArray();
		List<Object[]> dataList = new ArrayList<Object[]>();
		/*int categoryTotalFlag = total.length-3;
		int impactTotalFlag = total.length-3;
		int typeTotalFlag = total.length-3;
		String headerFlag = header[4];
		int flag = 0;
		for(int i=4;i<header.length-1;i++){
			if(!headerFlag.equals(header[i]))
			{
				flag++;
				if(flag == 1)
					categoryTotalFlag = i-1;
				else if(flag == 2)
					impactTotalFlag = i-1;
				else if(flag == 3)
					typeTotalFlag = i-1;
			}
			headerFlag = header[i];
		}*/
		
			for(int j=6;j<total.length;j++){
			if( j< total.length && (prevPillar == null || (header[j].equals(prevPillar))&& !header2[j].equals("TOTAL") )){
				if(chkVal.equals("N")){
					if(!total[j].equals("0") )
					{	
						
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								/*int arrVal = total.length-3;
								if(header[j].indexOf("CATEGORY")>0)
									arrVal = categoryTotalFlag;
								if(header[j].indexOf("IMPACT")>0)
									arrVal = impactTotalFlag;
								if(header[j].indexOf("TYPE")>0)
									arrVal = typeTotalFlag;*/
								CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
								totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								//totalVal = (double) Math.round(totalVal);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
								CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
							}
						}
								
						data[ 1 ] = totalVal;	
						
						dataList.add(data);
					}}
					else
					{
						//if(!total[j].equals("0") )
						//{	
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						CommonMessage.debugMsg("totalVal..: "+totalVal);
						CommonMessage.debugMsg("chkPercentage : "+chkPercentage);
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								
								
								if(Integer.parseInt(total[totalCol(header,j)])>0)
									totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
							}
						}
								
						data[ 1 ] = totalVal;					
						dataList.add(data);
					//	}
					}
			}	
			else{
				ChartPie pieChart =  new   ChartPie();
			//	List<Integer> center = new ArrayList<Integer>();
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				ChartSeries chartSeries = new ChartSeries();
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeries.setSize(200);
				chartSeriesList.add(chartSeries);
				
				//center.add(520);
			//	center.add(centerVal);
				//centerVal = 450;
				
			//	chartSeries.setCenter(center);
				chartSeriesList.add(chartSeries);
				dataList = new ArrayList<Object[]>();	
				
				//pieChart.setHeight(1400);
			//	pieChart.setWidth(1000);
				JSONObject pieData = pieChart.drawChart(chartSeriesList,"ABN TYPE", "");
				chartData.put(pieData);
				UIUtils.setDashBoardIdentifier(request,pieData);
				/*dataList = new ArrayList<Object[]>();
				CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
				
			}
			if( j< total.length-1 )
			{
				prevPillar = header[j];
				if(header2[j].equals("TOTAL"))
					prevPillar = header[j+1];
			}
			CommonMessage.debugMsg("prevPillar.."+prevPillar);
		}*/
		
			
		out.println(chartData);
		out.close();
	  }
			}
	}
			/******/
			
	/*****************************************************************/
		
		else if( action.equals("barchart.abnStrRpt")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
			
			String forDashboard = request.getParameter("dashboard");
			CommonMessage.debugMsg("forDashboard....."+forDashboard);
			if( ! "true".equals(forDashboard)){
				CommonMessage.debugMsg("to check dash board");
				commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
			}
			else{
				commonFilter = new  CommonFilter();
				CommonMessage.debugMsg("to check dash board true ");
				FilterValues.getCommonFilters(request,commonFilter);
				FilterValues.getAbnRelatedFilters(request, commonFilter);
			}
			if(commonFilter.getRowTotal() == null )
				commonFilter.setRowTotal('Y');
			
				CommonMessage.debugMsg(commonFilter.getFromMonth() + "month");
				
			//List<String[]> graphData  =  abnStraficationService.getAllAbnStrafication(commonFilter);
				List<String[]> graphData  =  abnStraficationService.getAlStrafication(commonFilter);
			PrintWriter out = response.getWriter();
			

			
			String[] header = graphData.get(2);
			String[] header2 = graphData.get(3);
			String[] total = graphData.get(4);
			
			int centerVal = 140;
			String prevPillar = null;
			String chkVal = request.getParameter("checkZero");
			String chkPercentage = request.getParameter("chkPercentage");
			JSONArray chartData = new JSONArray();
			List<Object[]> dataList = new ArrayList<Object[]>();
			List<String> xAxisCategory = new ArrayList<String>();
			List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
			
			for(int j=6;j<total.length;j++){
				if( j< total.length && (prevPillar == null || (header[j].equals(prevPillar))&& !header2[j].equals("TOTAL") )){
				
					if(chkVal.equals("N")){
						if(!total[j].equals("0") )
						{	
							
							Object []  data = new Object[2];
							data[ 0 ] = header2[j];
							Double totalVal =  Double.parseDouble(total[j]);	
							
							if(UIUtils.isValidKeyId(chkPercentage))
							{
								if(chkPercentage.equals("Y")){
									totalVal = totalVal*100;
									CommonMessage.debugMsg("totalVal*100 : "+totalVal);
									CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
									totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
									totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
									CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
								}
							}
							
							data[ 1 ] = totalVal;	
							CommonMessage.debugMsg(data[1]+" header2[j] data if  " + header2[j] );
							dataList.add(data);
							xAxisCategory.add(header2[j]);
						}}
						else
						{
							Object []  data = new Object[2];
							data[ 0 ] = header2[j];
							Double totalVal =  Double.parseDouble(total[j]);	
							if(UIUtils.isValidKeyId(chkPercentage))
							{
								if(chkPercentage.equals("Y")){
									totalVal = totalVal*100;
									if(Integer.parseInt(total[totalCol(header,j)])>0)
										totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
									totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
								}
							}
							data[ 1 ] = totalVal;					
							dataList.add(data);
							xAxisCategory.add(header2[j]);
						}
				}	
				else{
					ChartOptionBean pieChart =  new   ChartOptionBean();
					//String xaxis=dataList.get(0)[0];
					List<Integer> center = new ArrayList<Integer>();
					ChartSeries chartSeries = new ChartSeries();
					chartSeries.setType(ChartTypes.COLUMN);
					chartSeries.setData(dataList);
					chartSeries.setSize(200);
					//CommonMessage.debugMsg(dataList.get(0)[0]+ " data    " );
					center.add(520);
					center.add(centerVal);
					xAxisCategory.add(header2[j]);
					//centerVal = 450;
					CommonMessage.debugMsg(header2[j] + " graphdata");
					
					ChartYAxis yAxis = new ChartYAxis(); 
					yAxis.setMin(0);
					yAxis.getTitle().setText("");
					chartYAxis.add(yAxis);
					
					List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
					chartSeries.setCenter(center);
					chartSeriesList.add(chartSeries);
					//pieChart.setHeight(1400);
					pieChart.setWidth(1100);
					JSONObject pieData = pieChart.drawChart(xAxisCategory,chartSeriesList, prevPillar, "",chartYAxis);
					//JSONObject pieData = pieChart.drawChart(chartSeriesList, prevPillar, "");
					chartData.put(pieData);
					UIUtils.dashBoardSetChartObject(request,pieData);
					dataList = new ArrayList<Object[]>();
					xAxisCategory = new ArrayList<String>();
					CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
					CommonMessage.debugMsg("arr length......"+chartData.length());
				}
				if( j< total.length-1 )
				{
					prevPillar = header[j];
					if(header2[j].equals("TOTAL"))
						prevPillar = header[j+1];
				}
				CommonMessage.debugMsg("prevPillar.."+prevPillar);
			}
			
				
			out.println(chartData);
			out.close();
		  }
	}
	/*****************/
	
	
	/***************************/

	private int totalCol(String[] header,int colFlag)throws Exception
	{
		String headerFlag = header[colFlag];
		
		int flag = 0;
		for(int i=colFlag;i<header.length-1;i++){
			if(!headerFlag.equals(header[i]))
			{
				flag++;
				if(flag == 1)
					return i-1;
			}
				
			headerFlag = header[i];
		}
		return flag;
	}
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
		FilterValues.getCommonFilters(request, commonFilter) ;
		CommonMessage.debugMsg("After Com Filter ");
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		
		List<String[]> abnStratificationGraph  =  abnStraficationService.getAllAbnStrafication(commonFilter);
		
		
    	
		JSONObject chartObj = null;
		String chartType = request.getParameter("chType");
		String flids=CommonFunctions.getLoginFlid(request);
		CommonMessage.debugMsg("flid fff is :::"+flids);
		String lcnname=dashboardService.Functionallocn(flids);
		if( chartType == null){
			chartObj = processLineChart(lcnname,abnStratificationGraph,commonFilter);
		}
		
		
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
			
	}
	

	
	private JSONObject processLineChart(String titlenme,List<String[]> abnStratificationGraph,CommonFilter commonFilter){
		if( abnStratificationGraph == null || abnStratificationGraph.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String title = titlenme+"Cumulative Tag Summary Identified VS Removed";
		
		
		String [] header =  abnStratificationGraph.get(1);
		String [] month =  abnStratificationGraph.get(0);
		String [] data =  abnStratificationGraph.get(2);
		
		String prevMonth = null;
		
		String subTitle = "";
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> instanceData = new ArrayList<Double>();
		
		
		for( int i = 3;i < header.length;i++ ){			
			if(header[i].contains("IDENTIFIED") ){
				timeData.add(Double.parseDouble(data[i]));
				CommonMessage.debugMsg("cur Row : "+data[i]);
			}
			else if(header[i].contains("REMOVED") ){
				CommonMessage.debugMsg("cur Row : "+data[i]);
				instanceData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(timeData.size() > 0 ){
			timeSeries.setData(timeData);
			timeSeries.setType(ChartTypes.SPLINE);
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);
			
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Count");
			chartYAxis.add(yAxis);
		}
		if( instanceData.size() > 0){
			intstanceSeries.setData(instanceData);
			intstanceSeries.setType(ChartTypes.SPLINE);
			intstanceSeries.setName("Removed");
			chartSeriesList.add(intstanceSeries);
		}
		String title1 = "Cumulative Tag Summary Identified VS Removed";
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title1, subTitle, chartYAxis);
		
	}
	
	/*
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();	
		String [] row =headers.get(1);
		String [] tempCol = new String[row.length];
		String [] colHeader = headers.get(0);
		String [] colHeader2 = headers.get(1);
		String [] colHeader3 = headers.get(2);
		String abn = commonFilter.getChkAssm();
		String groupByCircle = commonFilter.getGroupByCircle();
		if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
		{
			jqGridTableModel.setRowNumbers(true);
		}
		else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
		{
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("Circle_0");		 
			jqGridTableModel.setGroupSummary(false);	
			jqGridTableModel.setTableHeight(300);
		}
		else{
		jqGridTableModel.setGroupBy(true);
		jqGridTableModel.setGroupByField("Title_0");		 
		jqGridTableModel.setGroupSummary(false);	
		jqGridTableModel.setTableHeight(300);
		}
		
		
		List<Integer> rotationRows = new ArrayList<Integer>();
		rotationRows.add(4);
		jqGridTableModel.setRotationRows(rotationRows);
		String headerSql = "'SELECT ";
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+"_"+i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+"_"+i);
			tempCol[i] ="";	
			//jqGridColModel.setAlign("left");
			//CommonMessage.debugMsg(colHeader[i]);
			/*if(colHeader[i].length()>=15)
				jqGridColModel.setWidth(300);
			else if(colHeader[i].length()>=20)
				jqGridColModel.setWidth(350);
			else
				jqGridColModel.setWidth(150);
			*/
	
	
	/*
	 
			if( i==0 || i == 2  || i == colHeader.length-1 || i == colHeader.length-2){
				jqGridColModel.setHidden(true);
				if(i==2)
					jqGridColModel.setKey(true);
			}
			else if(i==3)
				jqGridColModel.setAlign("left");
			else if(i==1){
				if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
					jqGridColModel.setHidden(true);
				else
					jqGridColModel.setWidth(150);
			}
			else{
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
				jqGridColModel.setHeaderRotation("90");
			}	
			if(colHeader[i].equals("-"))
			{
				jqGridColModel.setHidden(true);
			}
			jqGridColModel.setEditable(false);
			
			if("Y".equals(commonFilter.getGroupByCircle()))
			{	if(i==3){
					jqGridColModel.setHeaderRotation("90");
					jqGridColModel.setAlign("right");
				}
			}
		    
		    jqGridTableModel.getColModel().add(jqGridColModel);
		    headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ' ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		jqGridTableModel.getRowHeaders().add(tempCol);	
		jqGridTableModel.getRowHeaders().add(colHeader2);	
		jqGridTableModel.getRowHeaders().add(colHeader3);	
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	
	
	*/
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			
		/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		return commonFilter;
	}
	public static JSONObject convertToJqGridTable(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, long totalRecords)
	{
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		CommonMessage.debugMsg(" totalRecords " + totalRecords);
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		tableDataObject.put("records", totalRecords); //total records
		
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = 0;
        for( String [] row : dataArrayList)
		{
        	if( rowId >= rowStart )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -rowStart +1);
	            
	            JSONArray cell=new JSONArray();
	            
	            for( int i = colStart ;i < row.length-1 ; i++)
	            {	 
	            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}
	


/***/  /***********************/   /**********/
	/*else if( action.equals("barchart.abnStrRpt")){
		
		CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		}
		if(commonFilter.getRowTotal() == null )
			commonFilter.setRowTotal('Y');
		
			CommonMessage.debugMsg(commonFilter.getFromMonth() + "month");
			
		List<String[]> graphData  =  abnStraficationService.getAllAbnStrafication(commonFilter);
		
		PrintWriter out = response.getWriter();
		

		
		String[] header = graphData.get(2);
		String[] header2 = graphData.get(3);
		String[] total = graphData.get(4);
		
		int centerVal = 140;
		String prevPillar = null;
		String chkVal = request.getParameter("checkZero");
		String chkPercentage = request.getParameter("chkPercentage");
		JSONArray chartData = new JSONArray();
		List<Object[]> dataList = new ArrayList<Object[]>();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		for(int j=6;j<total.length;j++){
			if( j< total.length && (prevPillar == null || (header[j].equals(prevPillar))&& !header2[j].equals("TOTAL") )){
			
				if(chkVal.equals("N")){
					if(!total[j].equals("0") )
					{	
						
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								CommonMessage.debugMsg("totalVal*100 : "+totalVal);
								CommonMessage.debugMsg("Double.parseDouble(total[total.length-1]): "+Double.parseDouble(total[totalCol(header,j)]));	
								totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));
								CommonMessage.debugMsg("totalVal per123 : "+totalVal);		
							}
						}
						
						data[ 1 ] = totalVal;	
						CommonMessage.debugMsg(data[1]+" header2[j] data if  " + header2[j] );
						dataList.add(data);
						xAxisCategory.add(header2[j]);
					}}
					else
					{
						Object []  data = new Object[2];
						data[ 0 ] = header2[j];
						Double totalVal =  Double.parseDouble(total[j]);	
						if(UIUtils.isValidKeyId(chkPercentage))
						{
							if(chkPercentage.equals("Y")){
								totalVal = totalVal*100;
								if(Integer.parseInt(total[totalCol(header,j)])>0)
									totalVal = totalVal /  Double.parseDouble(total[totalCol(header,j)]);
								totalVal = Double.parseDouble(new DecimalFormat("#.##").format(totalVal));								
							}
						}
						data[ 1 ] = totalVal;					
						dataList.add(data);
						xAxisCategory.add(header2[j]);
					}
			}	
			else{
				ChartOptionBean pieChart =  new   ChartOptionBean();
				//String xaxis=dataList.get(0)[0];
				List<Integer> center = new ArrayList<Integer>();
				ChartSeries chartSeries = new ChartSeries();
				chartSeries.setType(ChartTypes.COLUMN);
				chartSeries.setData(dataList);
				chartSeries.setSize(200);
				//CommonMessage.debugMsg(dataList.get(0)[0]+ " data    " );
				center.add(520);
				center.add(centerVal);
				xAxisCategory.add(header2[j]);
				//centerVal = 450;
				CommonMessage.debugMsg(header2[j] + " graphdata");
				
				ChartYAxis yAxis = new ChartYAxis(); 
				yAxis.setMin(0);
				yAxis.getTitle().setText("");
				chartYAxis.add(yAxis);
				
				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();
				chartSeries.setCenter(center);
				chartSeriesList.add(chartSeries);
				//pieChart.setHeight(1400);
				pieChart.setWidth(1100);
				JSONObject pieData = pieChart.drawChart(xAxisCategory,chartSeriesList, prevPillar, "",chartYAxis);
				//JSONObject pieData = pieChart.drawChart(chartSeriesList, prevPillar, "");
				chartData.put(pieData);
				UIUtils.dashBoardSetChartObject(request,pieData);
				dataList = new ArrayList<Object[]>();
				xAxisCategory = new ArrayList<String>();
				CommonMessage.debugMsg("pie length......"+chartData.length()+" : "+pieData.toString());
				CommonMessage.debugMsg("arr length......"+chartData.length());
			}
			if( j< total.length-1 )
			{
				prevPillar = header[j];
				if(header2[j].equals("TOTAL"))
					prevPillar = header[j+1];
			}
			CommonMessage.debugMsg("prevPillar.."+prevPillar);
		}
		
			
		out.println(chartData);
		out.close();
	  }
}*/
/*****************/


/***************************/
/*
private int totalCol1(String[] header,int colFlag)throws Exception
{
	String headerFlag = header[colFlag];
	
	int flag = 0;
	for(int i=colFlag;i<header.length-1;i++){
		if(!headerFlag.equals(header[i]))
		{
			flag++;
			if(flag == 1)
				return i-1;
		}
			
		headerFlag = header[i];
	}
	return flag;
}
private void processChart1(HttpServletRequest request, HttpServletResponse response) throws Exception{
	HttpSession httpSession = request.getSession(false);
	
	CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
	FilterValues.getCommonFilters(request, commonFilter) ;
	CommonMessage.debugMsg("After Com Filter ");
	if(commonFilter.getRowTotal() == null )
		commonFilter.setRowTotal('Y');
	
	List<String[]> abnStratificationGraph  =  abnStraficationService.getAlStrafication(commonFilter);
	
	
	
	JSONObject chartObj = null;
	String chartType = request.getParameter("chType");
	if( chartType == null){
		chartObj = processLineChart(abnStratificationGraph,commonFilter);
	}
	
	
	PrintWriter out = response.getWriter();
	out.print(chartObj);
	out.close();
		
}



private JSONObject processLineChart1(List<String[]> abnStratificationGraph,CommonFilter commonFilter){
	if( abnStratificationGraph == null || abnStratificationGraph.size() <= 2  )
		return null;
	
	ChartOptionBean lineChart =  new ChartOptionBean();
	List<String> xAxisCategory = new ArrayList<String>();
	List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
	List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
	
	String title = "Cumulative Tag Summary Identified VS Removed";
	
	
	String [] header =  abnStratificationGraph.get(1);
	String [] month =  abnStratificationGraph.get(0);
	String [] data =  abnStratificationGraph.get(2);
	
	String prevMonth = null;
	
	String subTitle = "";
	
	ChartSeries timeSeries = new ChartSeries();
	List<Double> timeData = new ArrayList<Double>();
	
	ChartSeries intstanceSeries = new ChartSeries();
	List<Double> instanceData = new ArrayList<Double>();
	
	
	for( int i = 3;i < header.length;i++ ){			
		if(header[i].contains("IDENTIFIED") ){
			timeData.add(Double.parseDouble(data[i]));
			CommonMessage.debugMsg("cur Row : "+data[i]);
		}
		else if(header[i].contains("REMOVED") ){
			CommonMessage.debugMsg("cur Row : "+data[i]);
			instanceData.add(Double.parseDouble(data[i]));
		}
		if( prevMonth == null || ! month[i].equals(prevMonth) ){
			xAxisCategory.add(month[i]);
		}
		prevMonth = month[i];
	}
	if(timeData.size() > 0 ){
		timeSeries.setData(timeData);
		timeSeries.setType(ChartTypes.SPLINE);
		timeSeries.setName("Identified");
		chartSeriesList.add(timeSeries);
		
		
		ChartYAxis yAxis = new ChartYAxis(); 
		yAxis.setMin(0);
		yAxis.getTitle().setText("Count");
		chartYAxis.add(yAxis);
	}
	if( instanceData.size() > 0){
		intstanceSeries.setData(instanceData);
		intstanceSeries.setType(ChartTypes.SPLINE);
		intstanceSeries.setName("Removed");
		chartSeriesList.add(intstanceSeries);
	}
	String title1 = "Cumulative Tag Summary Identified VS Removed";
	return lineChart.drawChart(xAxisCategory, chartSeriesList, title1, subTitle, chartYAxis);
	
}

private JSONObject getTableModel1(List<String[]> headers,CommonFilter commonFilter)
{
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();	
	String [] row =headers.get(1);
	String [] tempCol = new String[row.length];
	String [] colHeader = headers.get(0);
	String [] colHeader2 = headers.get(1);
	String [] colHeader3 = headers.get(2);
	String abn = commonFilter.getChkAssm();
	String groupByCircle = commonFilter.getGroupByCircle();
	if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
	{
		jqGridTableModel.setRowNumbers(true);
	}
	else if(UIUtils.isValidKeyId(groupByCircle) && groupByCircle.equals("Y"))
	{
		jqGridTableModel.setGroupBy(true);
		jqGridTableModel.setGroupByField("Circle_0");		 
		jqGridTableModel.setGroupSummary(false);	
		jqGridTableModel.setTableHeight(300);
	}
	else{
	jqGridTableModel.setGroupBy(true);
	jqGridTableModel.setGroupByField("Title_0");		 
	jqGridTableModel.setGroupSummary(false);	
	jqGridTableModel.setTableHeight(300);
	}
	
	
	List<Integer> rotationRows = new ArrayList<Integer>();
	rotationRows.add(4);
	jqGridTableModel.setRotationRows(rotationRows);
	String headerSql = "'SELECT ";
	for(int i =0; i < colHeader.length; i++)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+"_"+i);
		jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+"_"+i);
		tempCol[i] ="";	
		//jqGridColModel.setAlign("left");
		//CommonMessage.debugMsg(colHeader[i]);
		/*if(colHeader[i].length()>=15)
			jqGridColModel.setWidth(300);
		else if(colHeader[i].length()>=20)
			jqGridColModel.setWidth(350);
		else
			jqGridColModel.setWidth(150);
		*/
	
	
	
	
	//THISS PLACE U HIDE
	/*
		if( i==0 || i == 2  || i == colHeader.length-1 || i == colHeader.length-2){
			jqGridColModel.setHidden(true);
			if(i==2)
				jqGridColModel.setKey(true);
		}
		else if(i==3)
			jqGridColModel.setAlign("left");
		else if(i==1){
			if(UIUtils.isValidKeyId(abn) && abn.equals("Y"))
				jqGridColModel.setHidden(true);
			else
				jqGridColModel.setWidth(150);
		}
		else{
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("right");
			jqGridColModel.setHeaderRotation("90");
		}	
		if(colHeader[i].equals("-"))
		{
			jqGridColModel.setHidden(true);
		}
		jqGridColModel.setEditable(false);
		
		if("Y".equals(commonFilter.getGroupByCircle()))
		{	if(i==3){
				jqGridColModel.setHeaderRotation("90");
				jqGridColModel.setAlign("right");
			}
		}
	    
	    jqGridTableModel.getColModel().add(jqGridColModel);
	    headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
	}
	headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ' ";
	CommonMessage.debugMsg("headerSql.....123..."+headerSql);
	jqGridTableModel.getRowHeaders().add(tempCol);	
	jqGridTableModel.getRowHeaders().add(colHeader2);	
	jqGridTableModel.getRowHeaders().add(colHeader3);	
	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
}

private CommonFilter populateCommonFilter1(HttpServletRequest request, String beanIdentifier, boolean createNew){
	HttpSession httpSession = request.getSession(false);
	
	
	CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
	if( commonFilter != null && ! createNew ){
		FilterValues.setPaginationParams(request,commonFilter);
	}	
	else{
		commonFilter =  new CommonFilter();
		
		commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
		commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
		
	/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } */
	 
	
	
	
	
	/*
		commonFilter.setViewClick('Y');
		httpSession.removeAttribute(beanIdentifier);
		httpSession.setAttribute(beanIdentifier, commonFilter);
	}
	return commonFilter;
}                               THIS PLACE  *//*
public static JSONObject convertToJqGridTable1(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, long totalRecords)
{
	String rowsStr = request.getParameter("rows");
	String pageStr = request.getParameter("page");
	int rows = 100;
	if( rowsStr != null)
		rows = Integer.parseInt(rowsStr);
	
	int page = 1;
	if( pageStr != null)
		page = Integer.parseInt(pageStr);
	
	JSONObject tableDataObject = new JSONObject();
	
	CommonMessage.debugMsg(" totalRecords " + totalRecords);
	
	tableDataObject.put("page", page); //current page
	tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
	//if( page == 1)
	tableDataObject.put("records", totalRecords); //total records
	
	JSONArray rowArr = new JSONArray(); 
   
  
	int rowId = 0;
    for( String [] row : dataArrayList)
	{
    	if( rowId >= rowStart )
    	{	
    	    JSONObject rowObj =new JSONObject();
    	    	
    	    rowObj.put("id",rowId -rowStart +1);
            
            JSONArray cell=new JSONArray();
            
            for( int i = colStart ;i < row.length-1 ; i++)
            {	 
            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
            }	
            rowObj.put("cell",cell);
            
            rowArr.put(rowObj);
    	}
    	rowId++;
   }

    tableDataObject.put("rows", rowArr);
    
    return tableDataObject;

}
*/


	/*          */
private void processChart1(HttpServletRequest request, HttpServletResponse response) throws Exception{
	HttpSession httpSession = request.getSession(false);
	
	CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalityStratificationCommonFilter");
	FilterValues.getCommonFilters(request, commonFilter) ;
	CommonMessage.debugMsg("After Com Filter ");
	if(commonFilter.getRowTotal() == null )
		commonFilter.setRowTotal('Y');
	
	List<String[]> abnStratificationGraph  =  abnStraficationService.getAlStrafication(commonFilter);
	
	String flids=CommonFunctions.getLoginFlid(request);
	String lcnname=dashboardService.Functionallocn(flids);
	
	JSONObject chartObj = null;
	String chartType = request.getParameter("chType");
	if( chartType == null){
		chartObj = processLineChart(lcnname,abnStratificationGraph,commonFilter);
	}
	
	
	PrintWriter out = response.getWriter();
	out.print(chartObj);
	out.close();
		
}

}