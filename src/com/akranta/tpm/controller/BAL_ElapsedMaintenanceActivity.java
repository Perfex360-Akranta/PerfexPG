
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
//import com.akranta.tpm.model.BAL_MaintActivity;
import com.akranta.tpm.service.BAL_ElapsedActivityService;
import com.akranta.tpm.service.BAL_MaintActivityService;
import com.akranta.tpm.service.impl.DocManagerServiceImpl;
import com.akranta.tpm.service.impl.BAL_ElapsedActivityServiceImpl;
import com.akranta.tpm.service.impl.BAL_MaintActivityServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_ElapsedMaintenanceActivity extends HttpServlet {
	
	/**
	 *   @ Author : Suresh.K
	 *   
	 *   Modified : Karthick.T
	 *   Date : 25-Nov-2011
	 *   
	 */
	private static final long serialVersionUID = 1L;
	
	
	BAL_ElapsedActivityService elapsedActivityService;
	public BAL_ElapsedMaintenanceActivity() throws Exception{
		super();
	
		//elapsedActivityService  = new ElapsedActivityServiceImpl();
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
		
		String dispatchUrl =null;
		try {
			elapsedActivityService  = (BAL_ElapsedActivityServiceImpl)UIUtils.getServiceObject(request,"BAL_ElapsedActivityServiceImpl");
			
			HttpSession h = request.getSession(false);

			elapsedActivityService.BAL_ElapsedActivityServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
		
		} catch (ServiceObjectCreationException e) {
			com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
		}
		
		if( action.equals("filterXmlElapsedMaintActvity_input.elapMaintAct")){
			response.setContentType("xml"); 
			System.out.println("action "+ action); 			
			UIUtils.forwardRequest(request, response, "/tiles/xml/ElapsedMaintaenceAct.xml");
			System.out.println("Exit xml");
		 }	
		
		else if(action.equals("ElapsedMaintActvity_input.elapMaintAct")) 
		{
			String factory=request.getParameter("factId");
			String sectionId=request.getParameter("sectionId");
			String cellId=request.getParameter("cellId");
			String machineId=request.getParameter("machId");
			request.setAttribute("machId", machineId);
			request.setAttribute("factId", factory);
			request.setAttribute("cellId", cellId);
			request.setAttribute("sectionId", sectionId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/ElapsedMaintAct.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("ElapsedMaintActvity_getCol.elapMaintAct") )
		{
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"ElapsedMaintActCommonFilter",true);
			commonFilter.setSectionId(request.getParameter("sectionId"));
			commonFilter.setCellId(request.getParameter("cellId"));
			commonFilter.setFactoryId(request.getParameter("factId"));
			commonFilter.setMachineId(request.getParameter("machId"));
			System.out.println(request.getParameter("sectionId")+" SEC "+request.getParameter("factId"));
			commonFilter.setIsGetCol("Y");
			List<String[]> maintActivity  = elapsedActivityService.getAllElapsedMaintActivity(commonFilter);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField("groupByWhat");
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = maintActivity.get(1);			
			String [] colHeaderCond = maintActivity.get(0);
			
			//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
			List<String[]> headers = new ArrayList<String[]>();
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
	      	out.println(jsonObject);
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ElapsedMaintActivity", "ElapsedMaintActivity"));
		}
		else if( action.equals("ElapsedMaintActvity_getData.elapMaintAct") )
		{
			try
			{	
				CommonFilter commonFilter = new CommonFilter();
				PrintWriter out = response.getWriter();
				System.out.println("Inside Elapsed Activity Servlet action");
				HttpSession httpSession = request.getSession(false);				
				commonFilter = (CommonFilter) httpSession.getAttribute("ElapsedMaintActCommonFilter");
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setSectionId(request.getParameter("sectionId"));
				commonFilter.setCellId(request.getParameter("cellId"));
				commonFilter.setFactoryId(request.getParameter("factId"));
				commonFilter.setMachineId(request.getParameter("machId"));
				commonFilter.setIsGetCol("N");
				List<String[]> maintActivity  = elapsedActivityService.getAllElapsedMaintActivity(commonFilter);
				JSONObject maintActivityData = UIUtils.convertToJqGridTableObject(maintActivity,request,2,0,commonFilter.getTotalRecordCnt()); 
  				out.println(maintActivityData);
  				
  				httpSession.removeAttribute("MaintActivityCommonFilter");
  			 	httpSession.setAttribute("MaintActivityCommonFilter", commonFilter);
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		else if( action.equals("ElapsedMaintActvity_getExcel.elapMaintAct") )
		{
			try
			{	
				HttpSession httpSession = request.getSession(false);		
				CommonFilter commonFilter = populateCommonFilter(request,"ElapsedMaintActCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);			
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ElapsedMaintActivity", "ElapsedMaintActivity");
				JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				tblJSONObj.put("title", "Elapsed Activity Count Report");
				String format = ExcelUtils.getFormat(request);			
				Workbook wb = elapsedActivityService.elapsedMaintReportExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);			
				ExcelUtils.writeToResponse(response, wb, "ElapsedActivityReport", format);
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if( action.equals("MaintActivityReport_getExcel.map"))
		{			
			
			HttpSession httpSession = request.getSession(false);				
			String fileName= "maintActivityRpt.xls";				
			CommonFilter commonFilter = populateCommonFilter(request,"ElapsedMaintActCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);			
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			tblJSONObj.put("title", "AbnormalityGeneral Report");
			String format = ExcelUtils.getFormat(request);			
			Workbook wb = elapsedActivityService.elapsedMaintReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);			
			ExcelUtils.writeToResponse(response, wb, "AbnormalityGeneralReport", format);
						
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
		System.out.println( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
}
