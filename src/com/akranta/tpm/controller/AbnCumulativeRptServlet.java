package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//import org.apache.catalina.connector.Request;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AbnormalityService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.AbnormalityServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
public class AbnCumulativeRptServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	DashboardService dashboardService;
	AbnormalityService abnormalityService;
	public AbnCumulativeRptServlet(){
		/*try {
			abnormalityService = new AbnormalityServiceImpl();
		} catch (Exception e) {
			
		//	e.printStackTrace();
		}
		*/
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
		if( ! UIUtils.checkUserSession( request,response))
			return; 		
		
		HttpSession s = request.getSession(false);
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			abnormalityService = (AbnormalityServiceImpl)UIUtils.getServiceObject(request,"AbnormalityServiceImpl");
			abnormalityService.AbnormalityServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		String action = UIUtils.getActionPart(request);
		
		
		
		if( action.equals("filterXmlAbnCumulative_input.abnCumulative") || action.equals("filterXmlHSE_AbnCumulative_input.abnCumulative")
				|| (action.equals("filterXmlAbnSocCumulative_input.abnCumulative"))|| (action.equals("filterXmlAbnHTCCumulative_input.abnCumulative"))
				|| (action.equals("filterXmlAbnUNSCumulative_input.abnCumulative"))){
			response.setContentType("xml");		
			UIUtils.forwardRequest(request, response, "/tiles/xml/CumulativeReport.xml");
		 }
		else if(action.equals("AbnCumulative_input.abnCumulative") || action.equals("HSE_AbnCumulative_input.abnCumulative")
				|| (action.equals("AbnHTCCumulative_input.abnCumulative"))|| (action.equals("AbnSocCumulative_input.abnCumulative"))
				|| (action.equals("AbnUNSCumulative_input.abnCumulative"))) 
		{					
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityCumulativeRpt.jsp");
			
			/*if (action.equals("AbnCumulative_input.abnCumulative")) 
				request.setAttribute("HSE", "N");
			else
				request.setAttribute("HSE", "Y");*/
			
			if (action.equals("HSE_AbnCumulative_input.abnCumulative")) 
				request.setAttribute("AbnType", "SHE");
			else if(action.equals("AbnHTCCumulative_input.abnCumulative"))
				request.setAttribute("AbnType", "HTA");
			else if(action.equals("AbnSocCumulative_input.abnCumulative"))
				request.setAttribute("AbnType", "SOC");
			else if(action.equals("AbnUNSCumulative_input.abnCumulative"))
				request.setAttribute("AbnType", "UNS");
						
				
			rd.forward(request, response); 
		}
		else if(action.equals("AbnormalityIdentifiedvscompleted_input.abnCumulative"))
		{
			CommonMessage.debugMsg("Identified vs completed");
			RequestDispatcher rd=request.getRequestDispatcher("/pages/AbnormalityIdvscmptd.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("Abnormalityjh_input.abnCumulative"))
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnormalityJHDetails.jsp"); 
			rd.forward(request, response); 
		}
		
		else if(action.equals("AbnormalityIdentifiedvscompleted_getCol.abnCumulative")) 
		{
			PrintWriter out = response.getWriter();	
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeRCommonFilter",true);
			CommonMessage.debugMsg("From  Month :" +commonFilter.getFromMonth() +" From Date:"+ commonFilter.getFromDate());
			commonFilter.setIsGetCol("Y");
			List< String[]> abndrillList  = abnormalityService.getAbnIdentifiedCompleted(commonFilter);	
			JSONObject jsonObject = getTableModelEmployeeWiseKaizen(abndrillList);
			jsonObject.set("tableHeight", "80%%");
		    jsonObject.set("tableWidth", "110%%");
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel", jsonObject);
			out.println(jsonObject);
	
		}else if (action.equals("AbnormalityIdentifiedvscompleted_getData.abnCumulative")) 
		{
			PrintWriter out = response.getWriter();	
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter=populateCommonFilter(request, "AbnCumulativeRCommonFilter", true);
			JSONObject jsonObject = new JSONObject();
			commonFilter.setIsGetCol("N");
			List< String[]> abndrillList  = abnormalityService.getAbnIdentifiedCompleted(commonFilter);	
			jsonObject = UIUtils.convertToJqGridTableObject(abndrillList,request,3,0,commonFilter.getTotalRecordCnt()+4);
			out.print(jsonObject);
			httpSession.removeAttribute("AbnCumulativeRCommonFilter");
			httpSession.setAttribute("AbnCumulativeRCommonFilter", commonFilter);	
		}
		else if(action.equals("AbnormalityIdentifiedvscompleted_getExcel.abnCumulative")){
			
			
			CommonMessage.debugMsg("action"+action);
            HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeRCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
			
			String isHSE = request.getParameter("isHSE");			
			
			commonFilter.setAbnIsHSE(isHSE);	
			
			
			tblJSONObj.put("title", "Abnormality Employeewise Identified vs Completed Report");
			String format = ExcelUtils.getFormat(request);
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			Workbook wb = abnormalityService.AbnormalityIdentifiedExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "AbnormalityCumulativeReport", format);
		
		}
		
		
		
		else if(action.equals("AbnCumulative_getCol.abnCumulative") || action.equals("HSE_AbnCumulative_getCol.abnCumulative")
				|| (action.equals("AbnSocCumulative_getCol.abnCumulative"))|| (action.equals("AbnHTCCumulative_getCol.abnCumulative"))
				|| (action.equals("AbnUNSCumulative_getCol.abnCumulative")))
			
		{
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			CommonFilter  commonFilter = null;		
			String firstClick =request.getParameter("firstClick");
		 CommonMessage.debugMsg("ssss");	
			if(commonFilter==null)
				commonFilter = new CommonFilter();
			
			FilterValues.getAbnRelatedFilters(request, commonFilter);
			
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
				commonFilter =(CommonFilter) httpSession.getAttribute("AbnCumulativeReportCommonFilter");
			}	
			commonFilter.setRowTotal('N');
			//CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeReportCommonFilter","Y".equals(firstClick));
			
			FilterValues.getCommonFilters(request,commonFilter);
		
			/* if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			  CommonMessage.debugMsg("remove Blank....."+commonFilter.getRemoveBlank());
			  if((commonFilter.getRemoveBlank()) == null)
				  commonFilter.setRemoveBlank("Y");
			 // populateCommoAbnModify_getExcelnFilter(request,"AbnCommonFilter",true);	
			httpSession.removeAttribute("AbnCumulativeReportCommonFilter");
			httpSession.setAttribute("AbnCumulativeReportCommonFilter",commonFilter);
	      	response.setContentType("text/html"); 
	      	
	      	//---------------------headers-------------      	
	        //	try{
      	    CommonMessage.debugMsg("drill level populate commonFilter................."+commonFilter.getDrillLevel());
      	    
      	    commonFilter.setIsGetCol("Y");
	   	    List< String[]> abndrillList  = abnormalityService.getAbnCumulative(commonFilter);	
		    //JSONObject abndrillData = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
	   	    
	   	    JSONObject abndrillData =fillJqGrid(abndrillList,request,3,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abncumulative", abndrillData);
			CommonMessage.debugMsg("abndrillList:"+abndrillList);	
			CommonMessage.debugMsg("abndrillData:"+abndrillData);	
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			gridColModel.setHeaderNum(2);		
			
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			
			/*jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("TITLE");
			
			String caption=FilterValues.getHeader(commonFilter.getDrillCaption());
			String[] row =abndrillList.get(1);
			String[] row2 =abndrillList.get(2);
			String [] colHeaderHead1 = abndrillList.get(0);
			int colFlag = row.length -1;
			CommonMessage.debugMsg(colFlag);			
			String [] colHeader = new String[ colFlag+2];
			String [] colHeader2 = new String[ colFlag+2];
			String [] colHeader3 = new String[ colFlag+2];
			String [] colHeaderHead = new String[ colFlag+2];
			if(caption.equals("Unit")){
				caption="PBU";
			}else if(caption.equals("Section")){
				caption="DMT";
			}else if(caption.equals("Line")){
				caption="JH";
			}
			if(!caption.equals("Assembly"))
				colHeader2[1] = "Select "+caption+" to Drilldown";
			else
				colHeader2[1] = caption;//"";
			
			//colHeader2[1] = caption;  
			colHeader3[1] = caption;  
			colHeader3[0]=colHeader[0]="";		
			colHeader2[0]="keyid";
			//colHeader2[1]="code";
			colHeaderHead[0]=colHeaderHead1[0];
			colHeaderHead[1]=colHeaderHead1[1];
			for(int i=2;i< colFlag;i++ ){	
				colHeader[i] = "";
				colHeader3[i] = row2[i];
				colHeader2[i] = row[i];
				colHeaderHead[i]=colHeaderHead1[i];
				CommonMessage.debugMsg("colHeaderHead1:"+colHeaderHead[i]);	
			}*/
			//colHeaderHead[colFlag]="HD=T#KE=F#AL=L#WI=50#IND=1";
			/*
				 Col Model Parameters
				 =====================
				 * HD: Hidden
				 * KE: Key Field
				 * AL: Align
				 * WI: Width
				 * IND: Col Name
				 * F:False
				 * T:True
			*/
			/*colHeaderHead[colFlag]="HD=F#KE=F#AL=C#WI=130#IND=totalIden"; //HD
			colHeaderHead[colFlag+1]="HD=F#KE=F#AL=C#WI=130#IND=totalRem";
			
			CommonMessage.debugMsg("colHeaderHead1:"+colHeaderHead[colFlag]);	
			CommonMessage.debugMsg("colHeaderHead1:"+colHeaderHead[colFlag+1]);	
			
			colHeader3[colFlag] =row2[colFlag-2];	
			colHeader3[colFlag+1] =row2[colFlag-1];		
			colHeader2[colFlag] ="Total";
			colHeader2[colFlag+1] ="Total";
			colHeader[colFlag] ="";
			colHeader[colFlag+1] ="";*/
			
			List<String[]> headers = new ArrayList<String[]>();				
			//headers.add(colHeader);
			String [] colHeader2 = abndrillList.get(1);
			String [] colHeader3 = abndrillList.get(2);
			String [] colHeaderHead = abndrillList.get(0);
			headers.add(colHeader2);	
			headers.add(colHeader3);	
			CommonMessage.debugMsg(colHeaderHead[0]+"  colHeaderHead[0]  "+colHeaderHead[1]+" colHeaderHead[0] "+colHeaderHead[2]);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			CommonMessage.debugMsg("jsonObject:"+jsonObject);
	   	    jsonObject.set("tableWidth", "106%%");
	     	jsonObject.set("tableHeight", "72%%");
	     	jsonObject.put("data", abndrillData);
	   	    httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);			
			out.println(jsonObject);
		     /* 	}
	      	catch(Exception e)
	      	{
	      		
	      	}*/
		}
		
		else if (action.equals("Abnormalityjh_getCol.abnCumulative"))
		{
			HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			CommonFilter  commonFilter=	null;
			String firstClick =request.getParameter("firstClick");
			
			if(commonFilter==null)
				commonFilter = new CommonFilter();
			
			FilterValues.getAbnRelatedFilters(request, commonFilter);
			
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
				commonFilter =(CommonFilter) httpSession.getAttribute("AbnJhReportCommonFilter");
			}	
			commonFilter.setRowTotal('N');
			CommonFilter commonFilter1 = populateCommonFilter(request,"AbnJhReportCommonFilter","Y".equals(firstClick));
			
			FilterValues.getCommonFilters(request,commonFilter1);
			  CommonMessage.debugMsg("remove Blank....."+commonFilter1.getRemoveBlank());
			  if((commonFilter1.getRemoveBlank()) == null)
				  commonFilter1.setRemoveBlank("Y");
			 // populateCommoAbnModify_getExcelnFilter(request,"AbnCommonFilter",true);	
			httpSession.removeAttribute("AbnJhReportCommonFilter");
			httpSession.setAttribute("AbnJhReportCommonFilter",commonFilter1);
	      	response.setContentType("text/html"); 
	      	
	      	//---------------------headers-------------      	
	        //	try{
	      	 String Flid=request.getParameter("flid");
	         if(UIUtils.isValidKeyId(Flid))
	 		 commonFilter1.setCellId(Flid);
	         CommonMessage.debugMsg("getcol:::="+Flid);
	         commonFilter.setIsGetCol("Y");
      	    CommonMessage.debugMsg("drill level populate commonFilter................."+commonFilter1.getDrillLevel());
	   	    List< String[]> abndrillList  = abnormalityService.getAbnjh(commonFilter1);	
		    //JSONObject abndrillData = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
	   	    
	   	    JSONObject abndrillData =fillJqGrid(abndrillList,request,3,0,commonFilter1.getTotalRecordCnt());
			httpSession.setAttribute("abncumulative", abndrillData);
			CommonMessage.debugMsg("abndrillList:"+abndrillList);	
			CommonMessage.debugMsg("abndrillData:"+abndrillData);	
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			gridColModel.setHeaderNum(2);		
			
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			
		
			List<String[]> headers = new ArrayList<String[]>();				
			//headers.add(colHeader);
			String [] colHeader2 = abndrillList.get(1);
			String [] colHeader3 = abndrillList.get(2);
			String [] colHeaderHead = abndrillList.get(0);
			headers.add(colHeader2);	
			headers.add(colHeader3);	
			CommonMessage.debugMsg(colHeaderHead[0]+"  colHeaderHead[0]  "+colHeaderHead[1]+" colHeaderHead[0] "+colHeaderHead[2]);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			CommonMessage.debugMsg("jsonObject:"+jsonObject);
	   	    jsonObject.set("tableWidth", "106%%");
	     	jsonObject.set("tableHeight", "63%%");
	     	jsonObject.put("data", abndrillData);
	   	    httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);			
			out.println(jsonObject);	
		}
		
		
		else if( action.equals("Abnormalityjh_getData.abnCumulative"))
		{
			try
			{	  	
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				
			    CommonFilter commonFilter = populateCommonFilter(request,"AbnJhReportCommonFilter",false);
				commonFilter.setRowTotal('N');
				
				String Flid=request.getParameter("flid");
		        if(UIUtils.isValidKeyId(Flid))
		 		commonFilter.setCellId(Flid);
		        CommonMessage.debugMsg("getdata:::="+Flid);
		        commonFilter.setIsGetCol("N");
				JSONObject jsonObject = new JSONObject();
        		List<String[]> abndrillList  =  abnormalityService.getAbnjh(commonFilter);
        		//jsonObject = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
        		jsonObject =fillJqGrid(abndrillList,request,3,0,commonFilter.getTotalRecordCnt()+2);
				 out.println(jsonObject);	
				 httpSession.removeAttribute("AbnJhReportCommonFilter");
				 httpSession.setAttribute("AbnJhReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("AbnCumulative_getData.abnCumulative") || action.equals("HSE_AbnCumulative_getData.abnCumulative")
				|| (action.equals("AbnSocCumulative_getData.abnCumulative"))|| (action.equals("AbnHTCCumulative_getData.abnCumulative"))
				|| (action.equals("AbnUNSCumulative_getData.abnCumulative")))
		{
			try
			{	  	
				HttpSession httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				//UIUtils.displayRequestParamsValue(request);
				//String page = request.getParameter("page");	
				/*String flid= request.getParameter("flid");
				if(!UIUtils.isValidKeyId(flid)){
					flid=(String) httpSession.getAttribute("loginFlid");
				}*/
				//String drillflag= request.getParameter("drillFlag");
				//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("AbnormalitySmryReportCommonFilter");
				//CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("AbnCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeReportCommonFilter",false);
				commonFilter.setRowTotal('N');
				/*if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(drillflag)){
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}*/
				/*String isHSE = request.getParameter("isHSE");			
				CommonMessage.debugMsg("isHSE"+isHSE);
				commonFilter.setAbnIsHSE(isHSE);*/	
				//CommonMessage.debugMsg("drill level getdata................."+commonFilter.getDrillLevel());
				JSONObject jsonObject = new JSONObject();
				commonFilter.setIsGetCol("N");
        		List<String[]> abndrillList  =  abnormalityService.getAbnCumulative(commonFilter);
        		//jsonObject = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
        		jsonObject =fillJqGrid(abndrillList,request,3,0,commonFilter.getTotalRecordCnt()+2);
				 out.println(jsonObject);
				// commonFilter.setViewClick('N');	  		
				 httpSession.removeAttribute("AbnCumulativeReportCommonFilter");
				 httpSession.setAttribute("AbnCumulativeReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("Abnormalityjh_getExcel.abnCumulative"))
		{
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnJhReportCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			commonFilter.setFromRow(null);
			commonFilter.setToRow(null);
			HttpSession httpSession = request.getSession(false);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
			//String format = ExcelUtils.getFormat(request);
			String isHSE = request.getParameter("isHSE");			
			
			commonFilter.setAbnIsHSE(isHSE);	
			
			
			tblJSONObj.put("title", "Abnormality JH Details  Report");
			String format = ExcelUtils.getFormat(request);
			 /*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			
			Workbook wb = abnormalityService.AbnjhReportExportExcel(commonFilter,tblJSONObj,format);
			ExcelUtils.writeToResponse(response, wb, "AbnJhReportCommonFilter", format);
			
		}
		
		else if( action.equals("AbnCumulative_getExcel.abnCumulative")|| action.equals("HSE_AbnCumulative_getExcel.abnCumulative")
				|| (action.equals("AbnSocCumulative_getExcel.abnCumulative"))|| (action.equals("AbnHTCCumulative_getExcel.abnCumulative"))
				|| (action.equals("AbnUNSCumulative_getExcel.abnCumulative"))){
			
			HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeReportCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
			
			String isHSE = request.getParameter("isHSE");			
			
			commonFilter.setAbnIsHSE(isHSE);	
			
			
			tblJSONObj.put("title", "Abnormality Details Cumulative Report");
			String format = ExcelUtils.getFormat(request);
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			Workbook wb = abnormalityService.AbnormalityReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "AbnormalityCumulativeReport", format);
			
		}
		
		else if( action.equals("chart.abnCumulative")){  //chart.abncumulativejh
			
			//CommonMessage.debugMsg("Chart ");
			processChart(request,response);
			
		}
else if( action.equals("chart1.abnCumulative")){
			
			//CommonMessage.debugMsg("Chart ");
			processChartjh(request,response);
			
		}
	}
	
	
	private void processChartjh(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("AbnCumulativeReportCommonFilter");
		CommonFilter commonFilter;
		//populateCommonFilter(request,"AbnCumulativeReportCommonFilter",false);
		//FilterValues.getCommonFilters(request, commonFilter) ;
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			//CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnJhReportCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		}
		//commonFilter.setRowTotal('Y');
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 } 
		
		List<String[]> abnCumulativeList  = abnormalityService.getAbnjhgraph(commonFilter);
		
		String [] curRow =(String[] )abnCumulativeList.get(abnCumulativeList.size()-1);   
		String [] cumRow = new String[ curRow.length ];
    	int cnt = 0;
		int total =0;
		int remTotal = 0;
		cumRow[0]=cumRow[1]="Cumulative";
    	for( int k = 2; k<curRow.length; k++)
		{	
    		
    		cnt = Integer.parseInt(curRow[k]);	
    			if(k%2 == 0)
    			{
    			   total += cnt;    			
    			   cumRow[k] = Integer.toString(total);
    			}
    			else
    			{
    				remTotal += cnt;	
	    			cumRow[k] = Integer.toString(remTotal);
    			}
			
		}
    	abnCumulativeList.add(cumRow);
		JSONObject chartObj = null;
		String chartType = request.getParameter("chType");
		//CommonMessage.debugMsg("chartType ="+chartType);
		chartObj = processLineChartjh(abnCumulativeList,commonFilter,chartType);
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
			
	}
	 private JSONObject getTableModelEmployeeWiseKaizen(List<String[]> headers) {
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			
			String[] colHeader = headers.get(1);
			String[] colHeader1 = headers.get(2);
		/*	colHeader[3] = "DATE";
			if(caption.equals("Factory")){
				caption="PBU";
			}else if(caption.equals("Section")){
				caption="DMT";
			}else if(caption.equals("Line")){
				caption="JH";
			}*/
			String[] emptyrow = new String[colHeader.length];
			emptyrow[0] = "";
			emptyrow[1] = "";
			CommonMessage.debugMsg("test..."+colHeader.length);
			for (int i = 2; i <colHeader.length; i++) {
				emptyrow[i] = "";
			}
			jqGridTableModel.getRowHeaders().add(emptyrow);
			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.getRowHeaders().add(colHeader1);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			CommonMessage.debugMsg("Headers count:"+colHeader.length);
			//colHeader1[3] = caption;  
			for (int i = 0; i <= colHeader.length-1; i++) {
				CommonMessage.debugMsg("Headers :"+i);
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
				jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
				jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
				jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);

				if (i == 0 || i == 1) {
					jqGridColModel.setHidden(true);
					jqGridColModel.setKey(true);
				}
				else if(i==2){
					jqGridColModel.setHidden(false);
					jqGridColModel.setKey(true);
				}
				else if (i == colHeader.length || i == colHeader.length-1) { 
					  CommonMessage.debugMsg("Length of col:" +
				  colHeader.length); jqGridColModel.setHidden(true);
				  jqGridColModel.setKey(true); 
				  }
				 

				else if (i > 2) {
					// jqGridColModel.setHidden(false);
					jqGridColModel.setKey(true);
					jqGridColModel.setAlign("right");
					jqGridColModel.setWidth(74);
				}

				/*
				 * else if (i == 16) { CommonMessage.debugMsg("Length of col:" +
				 * colHeader.length); jqGridColModel.setHidden(true);
				 * jqGridColModel.setKey(true); }
				 */
				
				jqGridTableModel.getColModel().add(jqGridColModel);
				
				//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			}
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "67%%");
			return tableModel;
		}
	
	private JSONObject processLineChartjh(List<String[]> abnCumulativeList,CommonFilter commonFilter,String chartType){
		if( abnCumulativeList == null || abnCumulativeList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String abnType = commonFilter.getAbnormalityType();
		
		if(abnType==null)
			abnType="";
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		
		String [] headerData =  abnCumulativeList.get(4);
		String drillLevel = FilterValues.getDrillHeader(headerData[0]);
		
		StringBuilder title = new StringBuilder( abnType).append(" Abnormality Cumulative JH Report - ").append( drillLevel ).append(" Wide From ").append(date);
		
		String [] header =  abnCumulativeList.get(3);
		String [] month =  abnCumulativeList.get(2);
		String [] data =  abnCumulativeList.get(abnCumulativeList.size()-1);
		
		String prevMonth = null;
		
		String subTitle = "";
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> instanceData = new ArrayList<Double>();
		
		for( int i = 2;i < header.length-1;i++ ){	
			if(header[i].contains("IDENTIFIED") ){
				timeData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("RECTIFIED") ){
				instanceData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if("B".equals(chartType))
			chartType=ChartTypes.COLUMN;
		else
			chartType=ChartTypes.SPLINE;
		if(timeData.size() > 0 ){
			timeSeries.setData(timeData);
			timeSeries.setType(chartType);
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("No.Of Tags");
			chartYAxis.add(yAxis);
		}
		if( instanceData.size() > 0){
			intstanceSeries.setData(instanceData);
			intstanceSeries.setType(chartType);
			intstanceSeries.setName("Rectified");
			chartSeriesList.add(intstanceSeries);
			
		}
//		CommonMessage.debugMsg("chartSeriesList "+chartSeriesList.size());
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}

	
	private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		
		//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("AbnCumulativeReportCommonFilter");
		CommonFilter commonFilter;
		//populateCommonFilter(request,"AbnCumulativeReportCommonFilter",false);
		//FilterValues.getCommonFilters(request, commonFilter) ;
		
		String forDashboard = request.getParameter("dashboard");
		CommonMessage.debugMsg("forDashboard....."+forDashboard);
		if( ! "true".equals(forDashboard)){
			//CommonMessage.debugMsg("to check dash board");
			commonFilter = (CommonFilter) httpSession.getAttribute("AbnCumulativeReportCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			CommonMessage.debugMsg("to check dash board true ");
			FilterValues.getCommonFilters(request,commonFilter);
			FilterValues.getAbnRelatedFilters(request, commonFilter);
		}
		//commonFilter.setRowTotal('Y');
		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	 } 
		
		List<String[]> abnCumulativeList  = abnormalityService.getAbnCumulative(commonFilter);
		
		String [] curRow =(String[] )abnCumulativeList.get(abnCumulativeList.size()-1);   
		String [] cumRow = new String[ curRow.length ];
    	int cnt = 0;
		int total =0;
		int remTotal = 0;
		cumRow[0]=cumRow[1]="Cumulative";
    	for( int k = 2; k<curRow.length; k++)
		{	
    		
    		cnt = Integer.parseInt(curRow[k]);	
    			if(k%2 == 0)
    			{
    			   total += cnt;    			
    			   cumRow[k] = Integer.toString(total);
    			}
    			else
    			{
    				remTotal += cnt;	
	    			cumRow[k] = Integer.toString(remTotal);
    			}
    			CommonMessage.debugMsg("row["+k+"] :"+ curRow[k]);
    			CommonMessage.debugMsg("cumRow["+k+"] :"+ cumRow[k]);
			
		}
    	abnCumulativeList.add(cumRow);
		JSONObject chartObj = null;
		String flids=CommonFunctions.getLoginFlid(request);
		String lcnname=dashboardService.Functionallocn(flids);
		String chartType = request.getParameter("chType");
		//CommonMessage.debugMsg("chartType ="+chartType);
		chartObj = processLineChart(lcnname,abnCumulativeList,commonFilter,chartType);
		UIUtils.dashBoardSetChartObject(request,chartObj);
		PrintWriter out = response.getWriter();
		out.print(chartObj);
		out.close();
			
	}
	

	
	private JSONObject processLineChart(String titlename,List<String[]> abnCumulativeList,CommonFilter commonFilter,String chartType){
		if( abnCumulativeList == null || abnCumulativeList.size() <= 2  )
			return null;
		
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		
		String abnType = commonFilter.getAbnormalityType();
		
		if(abnType==null)
			abnType="";
		
		StringBuilder date = new StringBuilder();
		
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
	
		
		String [] headerData =  abnCumulativeList.get(3);
		String drillLevel = FilterValues.getDrillHeader(headerData[0]);
		
		StringBuilder title = new StringBuilder( abnType).append(titlename+"-Abnormality Cumulative Report - ").append( drillLevel ).append(" Wide From ").append(date);
		
		String [] header =  abnCumulativeList.get(2);
		String [] month =  abnCumulativeList.get(1);
		String [] data =  abnCumulativeList.get(abnCumulativeList.size()-1);
		
		String prevMonth = null;
		
		String subTitle = "";
		
		ChartSeries timeSeries = new ChartSeries();
		List<Double> timeData = new ArrayList<Double>();
		ChartSeries intstanceSeries = new ChartSeries();
		List<Double> instanceData = new ArrayList<Double>();
		
		for( int i = 2;i < header.length;i++ ){	
			if(header[i].contains("IDENTIFIED") ){
				timeData.add(Double.parseDouble(data[i]));
			}
			else if(header[i].contains("RECTIFIED") ){
				instanceData.add(Double.parseDouble(data[i]));
			}
			if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		//if("B".equals(chartType))
			//chartType=ChartTypes.COLUMN;
		//else
			//chartType=ChartTypes.SPLINE;
		if(timeData.size() > 0 ){
			timeSeries.setData(timeData);
			timeSeries.setType(chartType);
			timeSeries.setName("Identified");
			chartSeriesList.add(timeSeries);
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("No.Of Tags");
			chartYAxis.add(yAxis);
		}
		if( instanceData.size() > 0){
			intstanceSeries.setData(instanceData);
			intstanceSeries.setType(chartType);
			intstanceSeries.setName("Rectified");
			chartSeriesList.add(intstanceSeries);
			
		}
//		CommonMessage.debugMsg("chartSeriesList "+chartSeriesList.size());
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
	}

	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		
		return jqGridColModel;
		
	}
	private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		
		String[] row =headers.get(0);
		int colFlag = row.length -1;
		CommonMessage.debugMsg(colFlag);
		String [] colHeader = new String[ colFlag+2];
		String [] colHeader2 = new String[ colFlag+2];
		String [] colHeader3 = new String[ colFlag+2];
		
		if(caption.equals("Unit")){
			caption="PBU";
		}else if(caption.equals("Section")){
			caption="DMT";
		}else if(caption.equals("Line")){
			caption="JH";
		}
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setPaginate(true);	
		String prevKeyid = null;
		
		
		
		List<JqGridColModel>jqGridColModelList =new ArrayList<JqGridColModel>();
		JqGridColModel jqGridColModel =getColModel("keyid",50,"left");
		jqGridColModel.setHidden(true);
		jqGridColModelList.add(jqGridColModel);
		jqGridColModel = getColModel("code",350,"left");
		jqGridColModelList.add(jqGridColModel);	
			CommonMessage.debugMsg("colFlag   "+colFlag+"   caption    "+caption);
		
		String[] row2 =headers.get(1);
		if(!caption.equals("Assembly"))
			colHeader[1] = "Select "+caption+" to Drilldown";
		else
			colHeader[1] = "";
		colHeader2[1] = caption;  
		colHeader3[1] = caption;  
		colHeader3[0]=colHeader[0]="";		
		colHeader2[0]="keyid";
		//colHeader2[1]="code";
		for(int i=2;i< colFlag;i++ ){	
		
			jqGridColModelList.add(getColModel(row[i]+i,120,"center"));	
			colHeader[i] = "";
			colHeader3[i] = row2[i];
			colHeader2[i] = row[i];
		}
		
		colHeader3[colFlag] =row2[colFlag-2];	
		colHeader3[colFlag+1] =row2[colFlag-1];		
		colHeader2[colFlag] ="Total";
		colHeader2[colFlag+1] ="Total";
		colHeader[colFlag] ="";
		colHeader[colFlag+1] ="";
		jqGridColModel = getColModel("totalIden",120,"center");
		jqGridColModelList.add(jqGridColModel);	
		jqGridColModel = getColModel("totalRem",120,"center");
		jqGridColModelList.add(jqGridColModel);	
		jqGridTableModel.setColModel(jqGridColModelList);		
		
		jqGridTableModel.getRowHeaders().add(colHeader);	
		jqGridTableModel.getRowHeaders().add(colHeader2);	
		jqGridTableModel.getRowHeaders().add(colHeader3);	
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	
	private  JSONObject fillJqGrid(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, long totalRecords)
	{
		JSONObject tableDataObject = new JSONObject();
		if(dataArrayList.size()>3){
			String rowsStr = request.getParameter("rows");
			String pageStr = request.getParameter("page");
			int rows = 300;
			if( rowsStr != null)
				rows = Integer.parseInt(rowsStr);
			
			int page = 1;
			if( pageStr != null)
				page = Integer.parseInt(pageStr);
			CommonMessage.debugMsg("totalRecords" +totalRecords);
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
			tableDataObject.put("records", totalRecords); //total records
			
			JSONArray rowArr = new JSONArray(); 
		
	      
			int rowId = 0;		
			
	        for( String [] row : dataArrayList)
			{
	        	
	        	if( rowId >= rowStart )
	        	{	
	        		JSONObject rowObj =new JSONObject();
	            	JSONArray cell=new JSONArray();
	        		
	        		rowObj.put("id",rowId -rowStart +1);
	        		CommonMessage.debugMsg("len"+row.length);
	        		for( int i = colStart ;i < row.length; i++)
		            {	 
		            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") );
		            }	
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	        
	       }
	        JSONObject rowObj =new JSONObject();
	    	JSONArray cell=null;
	    	rowObj.put("id",rowId -rowStart +1);    
	    	if (dataArrayList.size() > 2 )
	    	{
//		    	String [] curRow1 =(String[] )dataArrayList.get(dataArrayList.size()-2);     	
		    	String [] curRow =(String[] )dataArrayList.get(dataArrayList.size()-1);
		    	CommonMessage.debugMsg("curRow :"+curRow);
//		    	CommonMessage.debugMsg("curRow1 :"+curRow1);
				List<Integer> cumulative = new ArrayList<Integer>();    	
		    	int cnt = 0;
				int total =0;
				int remTotal = 0;
				cumulative.add(0,0);
				cumulative.add(0,1);
		    	for( int k =2; k<curRow.length; k++)
				{	
		    		CommonMessage.debugMsg("cur Row : "+curRow[k]);
		    		if(curRow[k]!=null && curRow[k].length()>0)
		    			cnt = Integer.parseInt(curRow[k]);	
		    		
		    			if(k%2 == 0)
		    			{
		    			   total += cnt;	
		    			   cumulative.add(total);
		    			}
		    			else
		    			{
		    				remTotal += cnt;	
			    			cumulative.add(remTotal);
		    			}
					
				}
		    	 CommonMessage.debugMsg("Outside for loop stmt");
		    	 CommonMessage.debugMsg("Outside for loop stmt" +cumulative);
		    	 cell = JSONArray.fromCollection(cumulative);
		    	 cell.put(1, "CUMULATIVE");    	
		    	 rowObj.put("cell", cell );
			     rowArr.put(rowObj);    	
	    	}
	        CommonMessage.debugMsg(".... "+rowId);
	        tableDataObject.put("rows", rowArr);
	        CommonMessage.debugMsg("Fill Grid : "+tableDataObject);
	        
	       
		}
		 return tableDataObject;
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			FilterValues.setPaginationParams(request,commonFilter);
			//commonFilter.setFirstLevel("N");
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter.setFirstLevel("Y");
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getToRow()+commonFilter.getFromRow()+"----"+commonFilter.getTotalRecordCnt());
		return commonFilter;
		
	}
	

}
