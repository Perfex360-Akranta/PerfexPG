

package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.WhyComplianceRptService;
import com.akranta.tpm.service.impl.WhyComplianceRptServiceImpl;

/*import com.akranta.tpm.service.impl.BreakdownServiceImpl;
import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;*/
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class WhyComplianceRptServlet extends HttpServlet {
	
	/**
	 * Created By:Siddharth.A
	 * Modified By :N.Arun
	 */
	private static final long serialVersionUID = 1L;
	private static final String COMMONFILTER_DRILL_IDENT = "WhyComplianceRptCommonFilter";
	private static final String COMMONFILTER_DETAIL_IDENT = "WhyComplianceDetailCommonFilter";
	private static final String Y = null;
	
	WhyComplianceRptService whyComplianceRptService ;
	
	
	public WhyComplianceRptServlet(){
	/*	try {
			whyComplianceRptService = new WhyComplianceRptServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		String dispatchUrl =null;
		
		//PrintWriter out = response.getWriter();
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			whyComplianceRptService = (WhyComplianceRptServiceImpl)UIUtils.getServiceObject(request,"WhyComplianceRptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		
		if( action.equals("filterXmlWhyComplianceRpt_input.yyComRpt")){
			 response.setContentType("xml"); 
			 
			UIUtils.forwardRequest(request, response, "/tiles/xml/WhywhyCompliance.xml") ;
		 }
		else if(action.equals("WhyComplianceRpt_input.yyComRpt")) 
		{
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			request.setAttribute("compgraph", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","compgraph"));
			request.setAttribute("compcell", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","compcell"));
			UIUtils.forwardRequest(request, response, "/pages/Reports/WhyComplianceRpt.jsp");
		}
		else if(action.equals("WhyComplianceRpt_getCol.yyComRpt"))
		{
			CommonFilter  commonFilter =null;
			CommonFilter  drillCommFilter =null;
			CommonFilter  dtlCommonFilter =null;
			
			String commonFilterIdent = null;
			PrintWriter out = response.getWriter();
			String colIndex = request.getParameter("colIndex");
			
			String colmodel = null, propertyFileName =null;
			
			String fromDetail =request.getParameter("fromDetail");
			CommonMessage.debugMsg("2222222222  :" +fromDetail +"colIndex  ++"+colIndex);
			String firstClick =request.getParameter("firstClick");
		
		   
		
			if( ! UIUtils.isValidKeyId(colIndex)  || (fromDetail != null && ! fromDetail.equals("Y")))
		 	{
				propertyFileName ="whyCompDrillRpt";
				commonFilterIdent = COMMONFILTER_DRILL_IDENT;
		 	}
			else{
				propertyFileName = "whyCompDtlRpt";
				commonFilterIdent = COMMONFILTER_DETAIL_IDENT;
			}
			
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
				drillCommFilter =(CommonFilter) httpSession.getAttribute(COMMONFILTER_DRILL_IDENT);
			
			if( ! UIUtils.isValidKeyId(colIndex)  || (fromDetail != null && ! fromDetail.equals("Y"))){
				 
				 if(drillCommFilter==null)
					 drillCommFilter = new CommonFilter();
				 
				 commonFilter =   drillCommFilter;
			}
			else{
				  
				   commonFilter = populateCommonFilter(request,COMMONFILTER_DETAIL_IDENT,true);	
				   if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	   }
				
				  List<String[]> whyyComplianceLis  = whyComplianceRptService.getAllyycelldrill(colIndex,commonFilter);
				 
				   int colStart = 0;
					 if( colIndex.equals(2)) colStart = 1;
				  JSONObject whyData = UIUtils.convertToJqGridTableObject(whyyComplianceLis,request,0,colStart,commonFilter.getTotalRecordCnt() );  
				 
				  httpSession.setAttribute("whyDataServlet", whyData);	
				   dtlCommonFilter =(CommonFilter) httpSession.getAttribute(COMMONFILTER_DETAIL_IDENT);
				  if( dtlCommonFilter == null ) 
					  dtlCommonFilter = new CommonFilter();
				  
			     BeanUtils.copyProperties(dtlCommonFilter,drillCommFilter);
			      
				 commonFilter =   dtlCommonFilter;
				///  httpSession.setAttribute("whyDataServlet", commonFilter);
			}
				
			FilterValues.getCommonFilters(request,commonFilter);
		    FilterValues.getBDRelated(request,commonFilter);
					  
			// commonFilter.setViewClick('Y');
			
		    if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	   }
		     httpSession.removeAttribute("yyComplCommonFilterIdentifier");
		     httpSession.setAttribute("yyComplCommonFilterIdentifier",commonFilterIdent);
		     
		     httpSession.removeAttribute(commonFilterIdent);
			 httpSession.setAttribute(commonFilterIdent,commonFilter);
			 
	       	 response.setContentType("text/html");

	      
	       	
			colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt",propertyFileName);
			
			JSONObject colModelObj = JSONObject.fromString(colmodel);
			JSONArray colNames= (JSONArray) colModelObj.get("rowHeaders");
			
			JSONArray headers = (JSONArray)colNames.get(0);
			headers.put(1, FilterValues.getHeader(commonFilter.getDrillCaption()));
			
			colNames.put(0,headers);
			colModelObj.set("rowHeaders", colNames);
			colModelObj.set("tableHeight", "95%%");
			colModelObj.set("tableWidth", "108%%");
		   	if( ! UIUtils.isValidKeyId(colIndex)  || (fromDetail != null && ! fromDetail.equals("Y"))){
		   		httpSession.removeAttribute("whyReportColModel");
		   		httpSession.setAttribute("whyReportColModel", colModelObj);
		   	}
			else
			{
				httpSession.removeAttribute("whyReportdetColModel");
		   		httpSession.setAttribute("whyReportdetColModel", colModelObj);	
			}
			out.print(colModelObj);
				//out.flush();
				//out.close();
		}
		
		else if(action.equals("WhyComplianceMonthwise_input.yyComRpt")) 
		{
			request.setAttribute("drilldownMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","drilldownMsg"));
			UIUtils.forwardRequest(request, response, "/pages/Reports/WhyComplianceRpt.jsp");
		}
		else if(action.equals("WhyComplianceMonthwise_getCol.yyComRpt")) 
		{
		 
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute(COMMONFILTER_DRILL_IDENT);

			if(commonFilter==null)
				 commonFilter = new CommonFilter();
			
			CommonFilter monthWise = new  CommonFilter();
    		BeanUtils.copyProperties(monthWise,commonFilter );
 		    FilterValues.getCommonFilters(request,monthWise);
				 
		   if( Constants.passNullDate.contains(monthWise.getFromMonth())&& (monthWise.getMonwise() == null || monthWise.getMonwise().equals("Y")) ){
			   monthWise.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			   monthWise.setToMonth(CommonFunctions.getDate().substring(3,11));
			   monthWise.setMonwise("Y");
	 	   }
				
			 httpSession.removeAttribute("WhyComplianceMonCommonFilter");
			 httpSession.setAttribute("WhyComplianceMonCommonFilter",monthWise);
	       	 response.setContentType("text/html");
			String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt","whyCompMonthRpt");
	      
						
			JSONObject colModelObj = JSONObject.fromString(colmodel );
			httpSession.removeAttribute("monReportColModel");
			httpSession.setAttribute("monReportColModel", colModelObj);
			out.print(colModelObj);
			out.flush();
			out.close();
		}	
		else if(action.equals("WhyComplianceMonthwise_getData.yyComRpt"))
		{
			 PrintWriter out = response.getWriter();
			 CommonFilter commonFilter = populateCommonFilter(request,"WhyComplianceMonCommonFilter",false);
			// CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("WhyComplianceMonCommonFilter");
			 List< String[]> whyCompmnthlist  = whyComplianceRptService.getAllmonth(commonFilter);
			 JSONObject whyComplianceData = UIUtils.convertToJqGridTableObject(whyCompmnthlist,request,1,1);
			 out.println(whyComplianceData);
			
		}
		else if( action.equals("WhyComplianceMonthwise_getExcel.yyComRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"WhyComplianceMonCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			String date   = fromMonth +"  -  "+ toMonth ;
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("monReportColModel");
			
			tblJSONObj.put("title", "Why Why Compliance Monthwise Report" +"  -  "+date);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = whyComplianceRptService.whymonthExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "WhyWhyCompliancemonthwiseReport", format);
			
		}
		else if( action.equals("WhyComplianceRpt_getData.yyComRpt") )
		{
			try
			{   
				 PrintWriter out = response.getWriter();
				
				 //commonFilter = populateCommonFilter(request,"WhyComplianceRptCommonFilter",false);	
				 String commonFilterIdent =  (String) httpSession.getAttribute("yyComplCommonFilterIdentifier");
				 CommonFilter commonFilter =populateCommonFilter(request,commonFilterIdent,false);
				 response.setContentType("text/html");
				 String firstClick =request.getParameter("firstClick");
				 String fromDetail =request.getParameter("fromDetail");
				 //For Drill 
				 if( (commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag || commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag) || (fromDetail != null  &&   ! fromDetail.equals("Y") )) {
					 commonFilter =(CommonFilter) httpSession.getAttribute(commonFilterIdent);
					 List< String[]> whyComplianceList  = whyComplianceRptService.getAllyydrill(commonFilter);
					 JSONObject whyComplianceData =  UIUtils.convertToJqGridTableObject(whyComplianceList,request,1,1); 
					 out.println(whyComplianceData);
					// commonFilter.setViewClick('N');
				 } 
				 else { 
					 String page = request.getParameter("page");	 
					 String colIndex = request.getParameter("colIndex");	
					 commonFilter = populateCommonFilter(request,commonFilterIdent,false);
					 JSONObject jsonObject = new JSONObject();
					 if(page.equals("1")){
						
		        		 jsonObject = (JSONObject) httpSession.getAttribute("whyDataServlet");
					 }
		        	 else
		        	 {	
		        		
						 List< String[]> whyyComplianceList  = whyComplianceRptService.getAllyycelldrill(colIndex,commonFilter);
						
						 int colStart = 0;
						 if( colIndex.equals(2)) colStart = 1;
						 
						 jsonObject =  UIUtils.convertToJqGridTableObject(whyyComplianceList,request,0,colStart,commonFilter.getTotalRecordCnt() ); 
		        	 }
				 
					 out.println(jsonObject);
					 // commonFilter.setViewClick('N');
					 httpSession.removeAttribute(COMMONFILTER_DETAIL_IDENT);
					 httpSession.setAttribute(COMMONFILTER_DETAIL_IDENT, commonFilter);
				}
				
				
				
			}
		   catch(Exception e)
			{
				CommonMessage.debugMsg("error " +e.getMessage());
			}
		}
		else if( action.equals("WhyComplianceRpt_getExcel.yyComRpt")){
			
			//HttpSession httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"WhyComplianceRptCommonFilter",false);
			String firstClick =request.getParameter("firstClick");
			String fromDetail =request.getParameter("fromDetail");
			String fromMonth = commonFilter.getFromMonth();
			String toMonth = commonFilter.getToMonth();
			String date   = fromMonth +"  -  "+ toMonth ;
			
			CommonMessage.debugMsg(commonFilter + " flag " + commonFilter.getDrillFlag());
			if( (commonFilter.getDrillFlag() == DrillLevelConstants.forwardFlag || commonFilter.getDrillFlag() == DrillLevelConstants.backwardFlag) || (fromDetail != null  &&   ! fromDetail.equals("Y") )) {
				
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("whyReportColModel");
			
			tblJSONObj.put("title", "Why-Why Compliance Report"+" - "+date);
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = whyComplianceRptService.whycompExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, "Why-WhyComplianceReport", format);
			}
			else{
				
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("whyReportdetColModel");
				
				tblJSONObj.put("title", "Why-Why Compliance Report(Details)"+" - "+date);
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = whyComplianceRptService.whydetailExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "Why-WhyComplianceReport", format);
			}
				
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
}