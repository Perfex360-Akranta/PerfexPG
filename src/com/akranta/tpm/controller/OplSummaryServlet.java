

package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.OplSummaryService;
import com.akranta.tpm.service.impl.OplSummaryServiceImpl;
//import com.akranta.tpm.service.impl.PcsComplianceRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class OplSummaryServlet extends HttpServlet {
	
	/**
	 * created by: T.Karthick
	 * modified by: N.Arun
	 * Date:18-oct-2011
	 * 
	 * modified by: Siddharth .A
	 */ 
	private static final long serialVersionUID = 1L;

	private static final ServletRequest httpSession = null;
	OplSummaryService oplsummaryService;
	public OplSummaryServlet() throws Exception{
		super();
		//oplsummaryService=new OplSummaryServiceImpl();
		
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
		
		
		String action = UIUtils.getActionPart(request);
		try {
			oplsummaryService=(OplSummaryServiceImpl)UIUtils.getServiceObject(request,"OplSummaryServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		if( action.equals("filterXmlOplSummary_input.OplSummaryRpt")){
			 response.setContentType("xml"); 
			CommonMessage.debugMsg("action "+ action); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/SummaryOfOPL.xml") ;
		 }

		else if(action.equals("OplSummary_input.OplSummaryRpt")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/OplSummary.jsp");  
			rd.forward(request, response); 
		}
		else if(action.equals("OplSummary_getCol.OplSummaryRpt"))
		{
			PrintWriter out = response.getWriter();
			
			CommonFilter commonFilter= new CommonFilter();
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			String groupByCellStr = commonFilter.getGrpByCellWise();
			String summarygrp ="summary";
			populateCommonFilter(request,"OPLSummaryCommonFilter",true);
			if( groupByCellStr!= null && groupByCellStr.equals("1"))
				summarygrp ="summaryGroupby";
			
			HttpSession httpSession = request.getSession(false);
			List<String []> oplSummaryList  = oplsummaryService.getAllOPLSummaryService(commonFilter);
			/*String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.OPLSummaryRpt",summarygrp);
			httpSession.removeAttribute("oplsummaryColModel");
			httpSession.setAttribute("oplsummaryColModel", colModel);
			out.println(colModel);
			*/
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setFormatter("actionFormatterimageC");
			gridColModel.setFormattorFromCol("3");
			gridColModel.setFormattorToCol("5");
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = oplSummaryList.get(1);			
			String [] colHeaderCond = oplSummaryList.get(0);
			
			//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "88%%");
			jsonObject.put("tableWidth", "106%%");
			httpSession.removeAttribute("oplsummaryColModel");
			//httpSession.setAttribute("oplsummaryColModel", jsonObject);
			out.println(jsonObject);
		}

		else if( action.equals("OplSummary_getData.OplSummaryRpt") )
		{
			PrintWriter out = response.getWriter();
			try
			{
					
				UIUtils.displayRequestParamsValue(request);
				
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"OPLSummaryCommonFilter",false);
				
				 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
					 commonFilter.setToDate(CommonFunctions.getDate());
				 }	 
				  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  } 
				  
  			 	List<String []> oplSummaryList  = oplsummaryService.getAllOPLSummaryService(commonFilter);
  			 	
  			 	JSONObject oplsummaryData = UIUtils.convertToJqGridTableObject(oplSummaryList,request,2,0,commonFilter.getTotalRecordCnt());
  			 	out.println(oplsummaryData);
  			 	
  				commonFilter.setViewClick('N');
  			 	
  			 	httpSession.removeAttribute("OPLSummaryCommonFilter");
  			 	httpSession.setAttribute("OPLSummaryCommonFilter", commonFilter);

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("OplSummary_getExcel.OplSummaryRpt")){
			
			exportToExcel(request, response);
			
		}
	}
	
	private void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		CommonFilter commonFilter = populateCommonFilter(request,"OPLSummaryCommonFilter",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		HttpSession httpSession = request.getSession(false);
		
		//String colModelStr = (String)httpSession.getAttribute("oplsummaryColModel");
		JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
		//JSONObject tblJSONObj =  JSONObject.fromString(colModelStr) ;
		tblJSONObj.put("title", "OPL Summary Report");

		String format = ExcelUtils.getFormat(request);
		
		Workbook wb = oplsummaryService.getOPLSummaryExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);
		
		ExcelUtils.writeToResponse(response, wb, "OPLSummary", format);
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
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	

}
				
				