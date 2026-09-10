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

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.BAL_PpMatrixRptService;
import com.akranta.tpm.service.impl.BAL_PpMatrixRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class BDppMatrixRptServlet
 */

public class BAL_BDppMatrixRptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BAL_PpMatrixRptService ppMatrixRptService; 

	CommonFilter commonFilter;
    public BAL_BDppMatrixRptServlet() {
        super();
       /* try{
        ppMatrixRptService = new  PpMatrixRptServiceImpl();
		
		}catch(Exception e)		{
			
		}
		*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String dispatchUrl =null;

		try {
			ppMatrixRptService = (BAL_PpMatrixRptServiceImpl)UIUtils.getServiceObject(request,"PpMatrixRptServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}

		// code for ppMatrixMonthRpt		
	
		 if( action.equals("filterXmlppMatrixMonthRpt_input.ppMatrixRpt")){
			response.setContentType("xml"); 
			System.out.println("action "+ action); 
			PrintWriter out = response.getWriter();			
			UIUtils.forwardRequest(request, response, "/tiles/xml/FailureMatrix.xml") ;
		 }
		else if(action.equals("ppMatrixMonthRpt_input.ppMatrixRpt")) //
		{
			CommonFunctions.debugMsg("Matrix Month wise input");
			System.out.println("Dispatch url:"+dispatchUrl);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownPPMatrixMonthwiseRpt.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("ppMatrixMonthRpt_getCol.ppMatrixRpt"))
		{	PrintWriter out = response.getWriter();
			try
			{
			
				
			CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",true);
			/*FilterValues.getCommonFilters(request,commonFilter);
			 FilterValues.getBDRelated(request,commonFilter);
			 commonFilter.setChkoccurchkbox("Y");
			 commonFilter.setChktimeChkBox("Y");*/
			 List<String[]> pcsRpt  = ppMatrixRptService.getPPMatrixMonthRpt(commonFilter);
			  
			 JSONObject pcsRptData= UIUtils.convertToJqGridTableObject(pcsRpt,request,0,0,commonFilter.getTotalRecordCnt());
				CommonFunctions.debugMsg("accidentInvestList   "+pcsRpt.size());
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(false);
				
				gridColModel.setHeaderNum(2);
				
				String [] colHeader1 = pcsRpt.get(1);
				String [] colHeader2 = pcsRpt.get(2);	
				String [] colHeaderCond = pcsRpt.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader1);
				headers.add(colHeader2);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.put("data", pcsRptData);
				CommonFunctions.debugMsg("colModel   "+colModel);
			
			//JSONObject colModel = getTableModel1(pcsRpt,"Month");
			colModel.set("tableWidth", "104%%");
			colModel.set("tableHeight", "92%%");
			CommonFunctions.debugMsg(colModel);
			httpSession.removeAttribute("PPMatrixHearderModel");
			httpSession.setAttribute("PPMatrixHearderModel", colModel);
			 
			out.println(colModel);
			out.close();
			}			
			catch(Exception e)
			{
				if( e.getMessage() == null  )
	    	    {
	    	    	
					JSONObject successData = new JSONObject();
					
	    	    	 CommonFunctions.debugMsg("No data");
	    	    	 String sucessmsg = "Data not Available";
	    	    	
	    	    	 successData.put("noData",true );
	    	    	 successData.put("noDataMsg",sucessmsg );
	    	    	
	    	    	 out.println(successData.toString());	
	    	    	return;
	    	    }
			
				System.out.println(e.getMessage());
			}
		
			
			
		}
		else if( action.equals("ppMatrixMonthRpt_getData.ppMatrixRpt") )
		{
			 PrintWriter out = response.getWriter();
			try
			{	
				 UIUtils.displayRequestParamsValue(request);
				
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",false);				 
				 JSONObject jsonObject = new JSONObject();				 
				
				CommonFunctions.debugMsg("Matrix Month wise getData");
				/*commonFilter= 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);			
				commonFilter= 	FilterValues.getBDRelated(request, commonFilter);
				commonFilter.setChkoccurchkbox("Y");*/
				List< String[]> ppMatMonList  = ppMatrixRptService.getPPMatrixMonthRpt(commonFilter);	
				 System.out.println("ppMatMonListList" + ppMatMonList.size() );
				 if(ppMatMonList.size() > 3 )				 
					 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMonList,request,3,0,ppMatMonList.size());	        	 
			
				 out.println(jsonObject);	
				 commonFilter.setViewClick('N');					 	
				 httpSession.removeAttribute("BDppMatrixRptServlet");				 
				 httpSession.setAttribute("BDppMatrixRptServlet", commonFilter);				 
				System.out.println("SESSION IN GET DATA" + httpSession.getAttribute("BDppMatrixRptServlet") );
		    }			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				JSONObject successData = new JSONObject();
				
   	    	 CommonFunctions.debugMsg("noData");
   	    	 String sucessmsg = "noData";
   	    	successData.put("msg",sucessmsg );
   	    	 out.println(successData);	
   	    	return;
			}
		}
		else if(action.equals("ppMatrixMonthRpt_getExcel.ppMatrixRpt"))
		{			
			System.out.println("SESSION" + httpSession.getAttribute("BDppMatrixRptServlet") );
			exportToExcel(request,response,"BDppMatrixRptServlet","View");
		}	
		 //code for Machine
		else if( action.equals("filterXmlppMatrixMachineRpt_input.ppMatrixRpt")){
				response.setContentType("xml"); 
				System.out.println("action "+ action); 
				PrintWriter out = response.getWriter();			
				UIUtils.forwardRequest(request, response, "/tiles/xml/FailureMatrix.xml") ;
			 }
		else if(action.equals("ppMatrixMachineRpt_input.ppMatrixRpt")) //
		{
			CommonFunctions.debugMsg("Matrix Machine wise input");
			System.out.println("Dispatch url:"+dispatchUrl);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/BreakdownPPMatrixMachinewiseRpt.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("ppMatrixMachineRpt_getCol.ppMatrixRpt"))
		{	PrintWriter out = response.getWriter();
			try
			{
				
			 System.out.println(" Inside GetCol action ");
			 CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",true);
			 /*FilterValues.getCommonFilters(request,commonFilter);
			  FilterValues.getBDRelated(request,commonFilter);*/
			 List<String[]> pcsRpt  = ppMatrixRptService.getPPMatrixMachineRpt(commonFilter);
			 JSONObject pcsRptData= UIUtils.convertToJqGridTableObject(pcsRpt,request,0,0,commonFilter.getTotalRecordCnt());
			CommonFunctions.debugMsg("accidentInvestList   "+pcsRpt.size());
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			
			gridColModel.setHeaderNum(2);
			
			String [] colHeader1 = pcsRpt.get(1);
			String [] colHeader2 = pcsRpt.get(2);	
			String [] colHeaderCond = pcsRpt.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader1);
			headers.add(colHeader2);
			
			JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			colModel.put("data", pcsRptData);
			CommonFunctions.debugMsg("colModel   "+colModel);
			
			//JSONObject colModel = getTableModel1(pcsRpt,"Machine");
			colModel.set("tableWidth", "104%%");
			colModel.set("tableHeight", "74%%");
			CommonFunctions.debugMsg(colModel);
			httpSession.removeAttribute("PPMatrixHearderModel");
			httpSession.setAttribute("PPMatrixHearderModel", colModel);
			 
			out.println(colModel);
			out.close();
			}			
			catch(Exception e)
			{
				e.printStackTrace();
				/*
				if( e.getMessage() == null  )
	    	    {
	    	    	
					JSONObject successData = new JSONObject();
					
	    	    	 CommonFunctions.debugMsg("No data");
	    	    	 String sucessmsg = "Data not available";
	    	    	
	    	    	 successData.put("noData",true );
	    	    	 successData.put("noDataMsg",sucessmsg );
	    	    	
	    	    	 out.println(successData.toString());	
	    	    	return;
	    	    }
			
				System.out.println(e.getMessage());*/
			}
		
			
			
		}
		else if( action.equals("ppMatrixMachineRpt_getData.ppMatrixRpt") )
		{
			 PrintWriter out = response.getWriter();
			try
			{	
				CommonFunctions.debugMsg("IN SIde the get DATA method");
				
				UIUtils.displayRequestParamsValue(request);
				
				 String page = request.getParameter("page");	  
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",false);				 
				 JSONObject jsonObject = new JSONObject();				 
				
				CommonFunctions.debugMsg("Matrix Machine wise getData");
				/*commonFilter= 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);			
				commonFilter= 	FilterValues.getBDRelated(request, commonFilter);*/
				List< String[]> ppMatMachList  = ppMatrixRptService.getPPMatrixMachineRpt(commonFilter);	
				 System.out.println("ppMatMachineList" + ppMatMachList.size() );
				 if(ppMatMachList.size() > 2 )				 
				 jsonObject = UIUtils.convertToJqGridTableObject(ppMatMachList,request,3,0,ppMatMachList.size());	        	 
			
				 out.println(jsonObject);	
				 commonFilter.setViewClick('N');					 	
				 httpSession.removeAttribute("BDppMatrixRptServlet");				 
				 httpSession.setAttribute("BDppMatrixRptServlet", commonFilter);				 
				System.out.println("SESSION IN GET DATA" + httpSession.getAttribute("BDppMatrixRptServlet") );
		    }			
			catch(Exception e)
			{
				e.printStackTrace();
				/*System.out.println(e.getMessage());
				JSONObject successData = new JSONObject();
				
   	    	 CommonFunctions.debugMsg("noData");
   	    	 String sucessmsg = "noData";
   	    	successData.put("msg",sucessmsg );
   	    	 out.println(successData);	
   	    	return;*/
			}
		}
		else if(action.equals("ppMatrixMachineRpt_getExcel.ppMatrixRpt"))
		{			
			System.out.println("SESSION" + httpSession.getAttribute("BDppMatrixRptServlet") );
			exportToExcelMachine(request,response,"BDppMatrixRptServlet","View");
		}	
	}
	
	private JSONObject getTableModel1(List<String[]> headers, String rptName) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		
		String[] emptyrow = new String[colHeader.length]; 	
		

		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 2; i < colHeader1.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		jqGridTableModel.getRowHeaders().add(emptyrow);		
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);		
		jqGridTableModel.setTableHeight(300);
	//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
		 
		String headerSql = "'SELECT "; 
		 CommonFunctions.debugMsg(rptName+"    colHeader1.length...................................."+colHeader1.length);
		for (int i = 0; i < colHeader.length; i++) 
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			if(i==1)
			{
				if(UIUtils.isValidKeyId(rptName))
				{
					if(rptName.equals("Machine"))
						colHeader[i] = "Machine";	
				}
			}
			CommonFunctions.debugMsg(colHeader[i]);
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") );
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") );
			
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);
			
			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");			
			jqGridColModel.setEditable(false);
			
			
			
			if(i==0 )
			{
				
				 jqGridColModel.setHidden(true);
				 jqGridColModel.setKey(true);
			}
			if(rptName.equals("Machine") && i==colHeader.length-1)
				jqGridColModel.setHidden(true);
				
			else if(i>1)
			{
				jqGridColModel.setWidth(75);
				jqGridColModel.setAlign("right");
				jqGridColModel.setEditable(false);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL '";
		CommonFunctions.debugMsg("headerSql.....123..."+headerSql);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight","80%%");
		//tableModel.put("sortable",false);
		return tableModel;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);			
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew )
		{
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");				 
		 	  }
			commonFilter.setViewClick('Y');
			commonFilter.setChkoccurchkbox("Y");
			commonFilter.setChktimeChkBox("Y");
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
	private void exportToExcel(HttpServletRequest request,HttpServletResponse response,String Filter,String mode) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("PPMatrixHearderModel");
			
		tblJSONObj.put("title", "PP Matrix Month Wise");
		String format = ExcelUtils.getFormat(request);		
		Workbook wb = ppMatrixRptService.getPPMatrixMonthRptExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);		
		ExcelUtils.writeToResponse(response, wb, "PPMatrixMonthWise", format);
		
	}
	private void exportToExcelMachine(HttpServletRequest request,HttpServletResponse response,String Filter,String mode) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"BDppMatrixRptServlet",false);
		String tmpFromRow = commonFilter.getFromRow();
		commonFilter.setFromRow(null);
		JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("PPMatrixHearderModel");
		//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.CustomerComplaintStmt", "CustomerComplaintPlotting");
		//JSONObject tblJSONObj = JSONObject.fromString();		
		tblJSONObj.put("title", "PP Matrix Machine Wise");
		String format = ExcelUtils.getFormat(request);		
		Workbook wb = ppMatrixRptService.getPPMatrixMachineRptExcel(commonFilter,tblJSONObj,format);
		commonFilter.setFromRow(tmpFromRow);		
		ExcelUtils.writeToResponse(response, wb, "PPMatrixMachineWise", format);
		
	}

}
