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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AdminPanelService;
import com.akranta.tpm.service.impl.AdminPanelServiceImpl;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.JhactivitychartService;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.JhactivitychartServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class AdminPanelServlet
 */
//@WebServlet("/AdminPanelServlet")
public class AdminPanelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService  dashboardService= null;
	private AdminPanelService  adminPanelService= null;
	private JhactivitychartService jhactivitychartService= null;
	private static final String commonFilterIden = "impVsCompcommonFilter";
	 private static final String gendrillcommonfilter = "susagendrillfilter";
	   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPanelServlet() {
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
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			adminPanelService = (AdminPanelServiceImpl)UIUtils.getServiceObject(request,"AdminPanelServiceImpl");
			
			jhactivitychartService = (JhactivitychartServiceImpl)UIUtils.getServiceObject(request,"JhactivitychartServiceImpl");
		
		if(action.equals("adminpanel_input.apdb")){
			System.out.println("action  :  "+action);
			displayNewDashboardPage(request,response);
		}
		else if(action.equals("momattendancemonthwisereport_input.apdb")){
			String Type=request.getParameter("meetingtype");
			System.out.println("The Type is"+Type);
			String DMTflid=request.getParameter("DMTflid");
			System.out.println("The DMTflid"+DMTflid);
			request.setAttribute("Type",Type);
			request.setAttribute("DMTflid",DMTflid);
			UIUtils.forwardRequest(request, response, "/pages/MomAttendancemonthwiseReport.jsp");
	    }
		
		else if(action.equals("momattendancemonthwisereport_getCol.apdb")){
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",true);
			String Flid =request.getParameter("flid");
			String Type=request.getParameter("meetingtype");
			System.out.println("The Type is"+Type);
			String DMTflid=request.getParameter("DMTflid");
			System.out.println("DMTflid"+DMTflid);
			String DmtOriginalId=adminPanelService.getDmtFlid(DMTflid);
			System.out.println("OriginalID::"+DmtOriginalId);
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
		//	String type =request.getParameter("type");
			
			String pillarId =request.getParameter("pillarId");
			  String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="APR-"+PreviousYear;
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				// String CurrentYear=CommonFunctions.getCurrentYear();
				 String FYearEnd="MAR-"+(NextYear)+1;
			
				 String FromDate=request.getParameter("FromDate");
				    String ToDate=request.getParameter("ToDate");
				    String Finance=request.getParameter("Finance");
		            System.out.println("Finance::::"+Finance);

				    String FromMonth=null;
				    String ToMonth=null;
				    if(FromDate.length()>1&&ToDate.length()>1){
				     FromMonth=FromDate.substring(3,11);
				    System.out.println("FromMonth::::"+FromMonth);
					
				     ToMonth=ToDate.substring(3,11);
				    
				    System.out.println("ToMonth::::"+ToMonth);
				    }
	 
			
			List<String[]> momReportList;
			PrintWriter out = response.getWriter();
			 if(Finance.equals("Y")){

			commonFilter.setFlid(Flid);
			commonFilter.setAbnDetect(DmtOriginalId);
			 commonFilter.setAbnormalityType(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
			 }
			 else{
				 commonFilter.setFlid(Flid);
					commonFilter.setAbnDetect(DmtOriginalId);
					 commonFilter.setAbnormalityType(FromMonth);
				     commonFilter.setAbnAllch(ToMonth); 
			 }
		/*	 if(Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if(Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } */
			try {
				
				System.out.println(" Checking for getCol :: "+Type);
				
				if(UIUtils.isValidKeyId(Type))
					   commonFilter.setMaintMode(Type);
				
				if(UIUtils.isValidKeyId(pillarId))
					   commonFilter.setPillarWise(pillarId);
				
			momReportList = adminPanelService.getAttendancemonthwise(commonFilter,Flid);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			String [] colHeader = momReportList.get(2);			
			String [] colHeaderCond = momReportList.get(1);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			//gridColModel.setFormatter("setattendanceReport");
			//gridColModel.setFormattorFromCol("11");
			//gridColModel.setFormattorToCol(""+(colHeader.length));
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("MomAttReportMonthwiseColmodel");
			httpSession.setAttribute("MomAttReportMonthwiseColmodel", jsonObject);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "100%%");
			CommonFunctions.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	    }
		else if(action.equals("momattendancemonthwisereport_getData.apdb")){
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String DMTflid=request.getParameter("DMTflid");
			System.out.println("DMTflid"+DMTflid);
			String DmtOriginalId=adminPanelService.getDmtFlid(DMTflid);
			System.out.println("OriginalID::"+DmtOriginalId);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String NextYear=CurrentYear.substring(0,3);
			 System.out.println("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear)+1;
			 String FromDate=request.getParameter("FromDate");
			    String ToDate=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	            System.out.println("Finance::::"+Finance);

			    String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(3,11);
			    System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
		/*	    
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			  */
			  String type =request.getParameter("type");
			  String pillarId =request.getParameter("pillarId");
			  
			  if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			  
				if(UIUtils.isValidKeyId(type)){
					 if(Finance.equals("Y")){

				
					   commonFilter.setMaintMode(type);
				commonFilter.setAbnDetect(DmtOriginalId);
				 commonFilter.setAbnormalityType(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
					 }
					 else{
						   commonFilter.setMaintMode(type);
							commonFilter.setAbnDetect(DmtOriginalId);
							 commonFilter.setAbnormalityType(FromMonth);
						     commonFilter.setAbnAllch(ToMonth); 
					 }
				}
				CommonFunctions.debugMsg(" Checking for getData :: "+type);
				
			List<String[]> MachineGrid = adminPanelService.getAttendancemonthwise(commonFilter,Flid);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 3, 0,commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//System.out.println(e.getMessage());
	}
  }
	else if(action.equals("momattendancemonthwisereport_getExcel.apdb")){

			try
			{   
				
				CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
				
                String type =request.getParameter("type");
				
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);

				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				httpSession = request.getSession(false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomAttReportMonthwiseColmodel");
				colmodel.put("title"," Mom Attendance Report-Month Wise");
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = adminPanelService.MomeetingMonthwiseExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingMonthWiseReport", format);

		    }catch(Exception e)
			{
				//System.out.println(e.getMessage());
			}

	    }

		
		else if(action.equals("JhAuditReport_input.apdb")||action.equals("JhauditActionPlanScore_input.apdb"))
		{
			System.out.println("Brfore jsp"+action);	
			String maintype= request.getParameter("maintype");
			request.setAttribute("GraphMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","Forgraph"));
			request.setAttribute("maintype",maintype);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/JhAuditRpt.jsp");
			rd.forward(request, response); 
		}
		else if(action.equals("JhAuditReport_getCol.apdb")||action.equals("JhauditActionPlanScore_getCol.apdb"))
		{	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"JhAuditRptCommonFilter",true);
			commonFilter.setMonwise("Y");
			commonFilter.setChkseveritychkbox("1");
			commonFilter.setChkfrequencychkbox("0");
			
			System.out.println("Brfore jsp"+action);
			
			
		     String maintype=request.getParameter("maintype");
		     String flid=request.getParameter("flid");
		     CommonFunctions.debugMsg(" maintype :: GetData ::  "+maintype);
			 String QuarterFirst=CommonFunctions.getFirstDateofMonth(-2).substring(3,11);
			 System.out.println("QuarterFirst"+QuarterFirst);
			 String QuarterFirst1=QuarterFirst;
			 System.out.println("QuarterFirst"+QuarterFirst1);
		     
			 String CurrentMonth=CommonFunctions.getCurrentMonth();
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 String QuarterEnd=CurrentMonth+"-"+CurrentYear;
			 System.out.println("QuarterEnd"+QuarterEnd);
			 String FromDate=request.getParameter("FromDate");
			    String ToDate=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	                             System.out.println("Finance::::"+Finance);

			    String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(3,11);
			    System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }

			 
			 
			 if(UIUtils.isValidKeyId(maintype))
				 commonFilter.setMaintMode(maintype);
		
			 
			 if(UIUtils.isValidKeyId(flid)){
				 if(Finance.equals("Y")){
 
			 
				 commonFilter.setFlid(flid); 
			     commonFilter.setAbnIsHSE(QuarterFirst1);
			     commonFilter.setAbnViewType(QuarterEnd);
				 }
				 else{
					 commonFilter.setFlid(flid); 
				     commonFilter.setAbnIsHSE(FromMonth);
				     commonFilter.setAbnViewType(ToMonth);	 
				 }
			 }
			if(action.equals("JhauditActionPlanScore_getCol.apdb"))
				commonFilter.setType("Actionplan");
			System.out.println("Get Col"+action);
			List<String[]> JhAuditRpt  = adminPanelService.getJhAuditRpt(commonFilter);
			CommonFunctions.debugMsg("JhAuditRpt"+JhAuditRpt.size());
			//JSONObject abnData = UIUtils.convertToJqGridTableObject(JhAuditRpt,request,3,0,commonFilter.getTotalRecordCnt()); 
			//CommonFunctions.debugMsg(abnData);
			//httpSession.setAttribute("JhAuditReportdata", abnData);
			
			JSONObject jsonObject = getTableModel(JhAuditRpt,commonFilter, action);
			jsonObject.set("tableHeight", "77%%");
			jsonObject.set("tableWidth", "100%%");
			//JSONObject jsonObject = getTableModel1(hseAccidentRpt);
			CommonFunctions.debugMsg(jsonObject);
			httpSession.removeAttribute("JhAuditReportColModel");
			httpSession.setAttribute("JhAuditReportColModel", jsonObject);
			out.println(jsonObject);		

		}
		 else if(action.equals("functionalLoc.apdb"))
			{
				
		
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				//functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setPbu("cmbsusmpbuid");
				functLocFieldNameBean.setSection("cmbsusmSectionid");
			
				functLocFieldNameBean.setCell("cmbsusmCellid");
				functLocFieldNameBean.setMachine("cmbsusmMachineid");
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);
				
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
				
				UIUtils.setFunctionalLocationPopupVal(request,response,functLocFieldNameBean,formModes);
			}
		   
		else if( action.equals("JhAuditReport_getData.apdb")||action.equals("JhauditActionPlanScore_getData.apdb") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				// UIUtils.displayRequestParamsValue(request);
				System.out.println("Data"+action);
				 String page = request.getParameter("page");	
				 CommonFunctions.debugMsg("page......"+page);
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"JhAuditRptCommonFilter",false);
				
				 JSONObject jsonObject = new JSONObject();
				// if(page.equals("1"))
	        		// jsonObject = (JSONObject) httpSession.getAttribute("BDFailureReportServletdata");
	        	// else
	        	 //{	
				 
				     if(action.equals("JhauditActionPlanScore_getData.apdb"))
						commonFilter.setType("Actionplan");
				     
				     String maintype=request.getParameter("maintype");
				     
				     CommonFunctions.debugMsg(" maintype :: GetData ::  "+maintype);
					 String QuarterFirst=CommonFunctions.getFirstDateofMonth(-2).substring(3,11);
					 System.out.println("QuarterFirst"+QuarterFirst);
					 String QuarterFirst1=QuarterFirst;
					 System.out.println("QuarterFirst"+QuarterFirst1);
				     
					 String CurrentMonth=CommonFunctions.getCurrentMonth();
					 String CurrentYear=CommonFunctions.getCurrentYear();
					 System.out.println("CurrentYear"+CurrentYear);
					 System.out.println("CurrentMonth"+CurrentMonth);
					 String QuarterEnd=CurrentMonth+"-"+CurrentYear;
					
					 
					 System.out.println("QuarterEnd"+QuarterEnd);
					 String FromDate=request.getParameter("FromDate");
					    String ToDate=request.getParameter("ToDate");
					    String Finance=request.getParameter("Finance");
			                             System.out.println("Finance::::"+Finance);

					    String FromMonth=null;
					    String ToMonth=null;
					    if(FromDate.length()>1&&ToDate.length()>1){
					     FromMonth=FromDate.substring(3,11);
					    System.out.println("FromMonth::::"+FromMonth);
						
					     ToMonth=ToDate.substring(3,11);
					    
					    System.out.println("ToMonth::::"+ToMonth);
					    }

					 
					 
					 if(UIUtils.isValidKeyId(maintype)){
						 
						 if(Finance.equals("Y")){

						 commonFilter.setMaintMode(maintype);
					     commonFilter.setAbnIsHSE(QuarterFirst1);
					     commonFilter.setAbnViewType(QuarterEnd);
					 }
						 else{
							 commonFilter.setMaintMode(maintype);
						     commonFilter.setAbnIsHSE(FromMonth);
						     commonFilter.setAbnViewType(ToMonth);	 
						 }
					 }
					 System.out.println("Data"+action);
	        		 List<String[]> JhAuditRpt  = adminPanelService.getJhAuditRpt(commonFilter);
	        		 jsonObject = UIUtils.convertToJqGridTableObject(JhAuditRpt,request,2,0,commonFilter.getTotalRecordCnt()); 
	        	 //}
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("JhAuditRptCommonFilter");
	  			 httpSession.setAttribute("JhAuditRptCommonFilter", commonFilter);
			
		    }
			catch(Exception e)
			{
				//CommonFunctions.debugMsg(e.getMessage());
			}
		}
		
		else if(action.equals("KaizenGraphicalSumm_input.apdb")){
			 RequestDispatcher rd = request.getRequestDispatcher("/pages/SHE/HSEModeWiseRpt.jsp");
			rd.forward(request, response); 
		}
        
		else if(action.equals("KaizenGraphicalSumm_getCol.apdb"))
		{	
			
			 String firstClick =request.getParameter("firstClick");
			 String flid=request.getParameter("flid");
			 CommonFilter  commonFilter  ;
			 commonFilter = new CommonFilter(); 
			
				System.out.println("getcol:" +firstClick);
			 
			 if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y")))
			 {
				 commonFilter =(CommonFilter) httpSession.getAttribute("HseModeWiseRptReportCommonFilter");
			 }	
			if(commonFilter==null)
			 commonFilter = new CommonFilter(); 
			 
			 commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			 commonFilter= 	FilterValues.getSafty(request, commonFilter);
			// populateCommonFilter(request,"HseAccTrendRptCommonFilter",true);
			 
		/*	if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			 else if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
		 		      commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
					  commonFilter.setToDate(CommonFunctions.getDate());
			 }*/
			     String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="APR-"+PreviousYear;
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				// String CurrentYear=CommonFunctions.getCurrentYear();
				 String FYearEnd="MAR-"+(NextYear)+1;
				 String FromDate=request.getParameter("FromDate");
				    String ToDate=request.getParameter("ToDate");
				    String Finance=request.getParameter("Finance");
		                             System.out.println("Finance::::"+Finance);

				    String FromMonth=null;
				    String ToMonth=null;
				    if(FromDate.length()>1&&ToDate.length()>1){
				     FromMonth=FromDate.substring(3,11);
				    System.out.println("FromMonth::::"+FromMonth);
					
				     ToMonth=ToDate.substring(3,11);
				    
				    System.out.println("ToMonth::::"+ToMonth);
				    }

				    if(Finance.equals("Y")){

				 commonFilter.setAbnDetect(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
			     commonFilter.setFlid(flid);
				    }
				    else{
				    	 commonFilter.setAbnDetect(FromMonth);
					     commonFilter.setAbnAllch(ToMonth);
					     commonFilter.setFlid(flid);
				    }
			 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
			 httpSession.setAttribute("HseModeWiseRptReportCommonFilter",commonFilter);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 List<String[]> hseAccidentRpt  =adminPanelService.getAllGraphicalSumm(commonFilter);
					 // hseAccidentRptService.getModeWiseReport(commonFilter);
			 //	List<String[]> impVscomList = adminPanelService.getAllGraphicalSumm(commonFilter);
				
			 JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,0,0,hseAccidentRpt.size());
		
			 CommonFunctions.debugMsg(HSEData);
				httpSession.setAttribute("HseBodyVsRptReportdata", HSEData);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				jqGridTableModel.setSortable(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = hseAccidentRpt.get(1);			
				String [] colHeaderCond = hseAccidentRpt.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject colModel = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				colModel.put("data", HSEData);
				CommonFunctions.debugMsg("colModel   "+colModel);
			 
			//JSONObject colModel = getTableModel_BODYVSACC(hseAccidentRpt,commonFilter);
			 colModel.set("tableHeight", "70%%");
			 colModel.set("tableWidth", "97%%");
			 
			 
			 httpSession.removeAttribute("HseModeWiseRptReportColModel");
			 httpSession.setAttribute("HseModeWiseRptReportColModel", colModel);
			 CommonFunctions.debugMsg(colModel);
			 out.println(colModel);
			
			 out.close();
		}
		
		else if( action.equals("KaizenGraphicalSumm_getData.apdb") )
		{
			PrintWriter out = response.getWriter();
			try
			{
				 UIUtils.displayRequestParamsValue(request);
				 String page = request.getParameter("page");	
				 CommonFunctions.debugMsg("page......"+page);
				 String flid=request.getParameter("flid");
				 httpSession = request.getSession();
				 CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
				 String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="APR-"+PreviousYear;
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				// String CurrentYear=CommonFunctions.getCurrentYear();
				 String FYearEnd="MAR-"+(NextYear)+1;
				 String FromDate=request.getParameter("FromDate");
				    String ToDate=request.getParameter("ToDate");
				    String Finance=request.getParameter("Finance");
		                             System.out.println("Finance::::"+Finance);

				    String FromMonth=null;
				    String ToMonth=null;
				    if(FromDate.length()>1&&ToDate.length()>1){
				     FromMonth=FromDate.substring(3,11);
				    System.out.println("FromMonth::::"+FromMonth);
					
				     ToMonth=ToDate.substring(3,11);
				    
				    System.out.println("ToMonth::::"+ToMonth);
				    }

					 if(Finance.equals("Y")){
				 commonFilter.setAbnDetect(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
			     commonFilter.setFlid(flid);
					 }
					 else{
						 commonFilter.setAbnDetect(FromMonth);
					     commonFilter.setAbnAllch(ToMonth);
					     commonFilter.setFlid(flid);	 
					 }
				     JSONObject jsonObject = new JSONObject();			
	        		 List<String[]> hseAccidentRpt  = adminPanelService.getAllGraphicalSumm(commonFilter);
	        		 if(hseAccidentRpt.size() > 3)
						{
	        		 jsonObject = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,3,0,hseAccidentRpt.size()); 
	        	 
				
				 out.println(jsonObject);
				 commonFilter.setViewClick('Y');	  			 	
	  			 httpSession.removeAttribute("HseModeWiseRptReportCommonFilter");
	  			 httpSession.setAttribute("HseModeWiseRptReportCommonFilter", commonFilter);
						}
	        		 else
	        		 {
	        			 
	        		 }

		    }
			catch(Exception e)
			{
			}
		}else if( action.equals("KaizenGraphicalSumm_getExcel.apdb")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"HseModeWiseRptReportCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("HseModeWiseRptReportColModel");
			
			tblJSONObj.put("title", "Mode wise Report Table");
			String format = ExcelUtils.getFormat(request);
			commonFilter.setFromRow(tmpFromRow);
			Workbook wb = adminPanelService.KaizenGraphicalSummExportExcel(commonFilter,tblJSONObj,format);
			//adminPanelService.KaizenGraphicalSummExportExcel(commonFilter,tableModel,format);
		
			
			ExcelUtils.writeToResponse(response, wb, "ModeWiseDataTableReport", format);	
		}
	
	
		  /*if(action.equals("EHSMetrics_input.apdb")){
		    	String type=request.getParameter("type");
		    	request.setAttribute("type",type);
		    	UIUtils.forwardRequest(request, response,"pages/NewSUSA/SusaSafeUnsafeCount.jsp");
		    }*/
		    else if (action.equals("EHSMetrics_getCol.apdb")){
		    	 buildTableCountColModel( request,response);
		     }
			else if( action.equals("EHSMetrics_getData.apdb")){
				
				EHScountGetData(request , response);
				
			}
		
		else if(action.equals("AbnCumulative_getCol.apdb"))
			
		{
			//HttpSession httpsession = request.getSession(false);
			PrintWriter out = response.getWriter();
			CommonFilter  commonFilter = null;		
			String firstClick =request.getParameter("firstClick");
			String flid=request.getParameter("flid");
		 System.out.println("ssss"+firstClick);	
			if(commonFilter==null)
				commonFilter = new CommonFilter();
			
			FilterValues.getAbnRelatedFilters(request, commonFilter);
			
			if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 					
				commonFilter =(CommonFilter) httpSession.getAttribute("AbnCumulativeReportCommonFilter");
			}	
			commonFilter.setRowTotal('N');
			//CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeReportCommonFilter","Y".equals(firstClick));
			
			FilterValues.getCommonFilters(request,commonFilter);
			  System.out.println("remove Blank....."+commonFilter.getRemoveBlank());
			  if((commonFilter.getRemoveBlank()) == null)
				  commonFilter.setRemoveBlank("Y");
			 // populateCommoAbnModify_getExcelnFilter(request,"AbnCommonFilter",true);	
			httpSession.removeAttribute("AbnCumulativeReportCommonFilter");
			httpSession.setAttribute("AbnCumulativeReportCommonFilter",commonFilter);
	      	response.setContentType("text/html"); 
	      	
	      	//---------------------headers-------------      	
	        //	try{
	      	 System.out.println("drill level populate commonFilter................."+commonFilter.getDrillLevel());
	   	    
	  	 String CurrentYear=CommonFunctions.getCurrentYear();
			 String QuarterFirst=CommonFunctions.getFirstDateofMonth(-2).substring(3,11);
			 System.out.println("QuarterFirst"+QuarterFirst);
			// String QuarterFirst1="01-"+QuarterFirst;
			// System.out.println("QuarterFirst"+QuarterFirst1);
			 String CurrentMonth=CommonFunctions.getCurrentMonth();
			// String QuarterEnd="30-"+CurrentMonth+"-"+CurrentYear;
			 String QuarterEnd=CurrentMonth+"-"+CurrentYear;
			 System.out.println("CurrentMonth"+QuarterEnd);
			
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String NextYear=CurrentYear.substring(0,3);
			 System.out.println("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear)+1;
			// commonFilter.setAbnDetect(FYearStart);
		    // commonFilter.setAbnAllch(FYearEnd);
		     
			 String JanYearStart="01-Jan-"+PreviousYear;
			 String MarYearStart="31-Mar-"+PreviousYear;
			 
			 String FromDate=request.getParameter("FromDate");
			    String ToDate=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	                             System.out.println("Finance::::"+Finance);

			    String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(2,10);
			    System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }
			    if(Finance.equals("Y")){

             commonFilter.setStartDate(JanYearStart);
		     commonFilter.setEndDate(MarYearStart);
			 commonFilter.setFromMonth(FYearStart);
             commonFilter.setToMonth(FYearEnd);
             commonFilter.setFlid(flid);
			    }
			    else{
			        commonFilter.setStartDate(FromDate);
				     commonFilter.setEndDate(ToDate);
					 commonFilter.setFromMonth(FromMonth);
		             commonFilter.setToMonth(ToMonth);
		             commonFilter.setFlid(flid);	
			    }
	      	 List< String[]> abndrillList  = adminPanelService.getAbnCumulative(commonFilter);	
		    //JSONObject abndrillData = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
	     	//jsonObject =UIUtils.convertToJqGridTableObject(abndrillList,request,4,0,commonFilter.getTotalRecordCnt()+2);
			
        	
	   	    JSONObject abndrillData =UIUtils.convertToJqGridTableObject(abndrillList,request,3,0,commonFilter.getTotalRecordCnt());
			httpSession.setAttribute("abncumulative", abndrillData);
			CommonFunctions.debugMsg("abndrillList:"+abndrillList);	
			CommonFunctions.debugMsg("abndrillData:"+abndrillData);	
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			gridColModel.setHeaderNum(2);		
			
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			
			
			
			List<String[]> headers = new ArrayList<String[]>();				
			//headers.add(colHeader);
			String [] colHeader2 = abndrillList.get(2);
			String [] colHeader3 = abndrillList.get(3);
			String [] colHeaderHead = abndrillList.get(1);
			headers.add(colHeader2);	
			headers.add(colHeader3);	
			CommonFunctions.debugMsg(colHeaderHead[0]+"  colHeaderHead[0]  "+colHeaderHead[1]+" colHeaderHead[0] "+colHeaderHead[2]);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			CommonFunctions.debugMsg("jsonObject:"+jsonObject);
	   	    jsonObject.set("tableWidth", "100%%");
	     	jsonObject.set("tableHeight", "72%%");
	     	jsonObject.put("data", abndrillData);
	   	    httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel",jsonObject);			
			out.println(jsonObject);
		      	
		}
	      
		
		
		else if( action.equals("AbnCumulative_getData.apdb"))
		{
			try
			{	  	
				HttpSession httpsession = request.getSession(false);
				PrintWriter out = response.getWriter();
				//UIUtils.displayRequestParamsValue(request);
				//String page = request.getParameter("page");	
				String flid= request.getParameter("flid");
				if(!UIUtils.isValidKeyId(flid)){
					flid=(String) httpSession.getAttribute("loginFlid");
				}
				//String drillflag= request.getParameter("drillFlag");
				//CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("AbnormalitySmryReportCommonFilter");
				//CommonFilter  commonFilter = (CommonFilter ) httpSession.getAttribute("AbnCommonFilter");
				CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeReportCommonFilter",false);
				commonFilter.setRowTotal('N');
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				System.out.println("COmmonFilter FromDate"+commonFilter.getdFromDate());
				/*if(UIUtils.isValidKeyId(drillflag)){
					CommonFunctions.debugMsg("  (drillflag.trim()).charAt(0)   "+(drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}*/
				String isHSE = request.getParameter("isHSE");			
				CommonFunctions.debugMsg("isHSE"+isHSE);
				commonFilter.setAbnIsHSE(isHSE);	
				 String CurrentYear=CommonFunctions.getCurrentYear();
				 String CurrentMonth=CommonFunctions.getCurrentMonth();
					// String QuarterEnd="30-"+CurrentMonth+"-"+CurrentYear;
					// String QuarterEnd=CurrentMonth+"-"+CurrentYear;
					// System.out.println("CurrentMonth"+QuarterEnd);
					
					 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
					// System.out.println("PreviousYear"+PreviousYear);
					 String FYearStart="APR-"+PreviousYear;
					// System.out.println("FYearStart"+FYearStart);
					 String NextYear=CurrentYear.substring(0,3);
					// System.out.println("FYearEnd"+NextYear);
					// String CurrentYear=CommonFunctions.getCurrentYear();
					 String FYearEnd="MAR-"+(NextYear)+1;
					 commonFilter.setFromMonth(FYearStart);
		             commonFilter.setToMonth(FYearEnd);
		             String JanYearStart="01-Jan-"+PreviousYear;
					 String MarYearStart="31-Mar-"+PreviousYear;
					 String FromDate=request.getParameter("FromDate");
					    String ToDate=request.getParameter("ToDate");
					    String Finance=request.getParameter("Finance");
			                             System.out.println("Finance::::"+Finance);

					    String FromMonth=null;
					    String ToMonth=null;
					    if(FromDate.length()>1&&ToDate.length()>1){
					     FromMonth=FromDate.substring(2,10);
					    System.out.println("FromMonth::::"+FromMonth);
						
					     ToMonth=ToDate.substring(3,11);
					    
					    System.out.println("ToMonth::::"+ToMonth);
					    }

					    if(Finance.equals("Y")){

					 commonFilter.setStartDate(JanYearStart);
				     commonFilter.setEndDate(MarYearStart);
				     commonFilter.setFlid(flid);
					    }
					    else{
					   	 commonFilter.setStartDate(FromDate);
					     commonFilter.setEndDate(ToDate);
					     commonFilter.setFlid(flid);	
					    }
				//CommonFunctions.debugMsg("drill level getdata................."+commonFilter.getDrillLevel());
				JSONObject jsonObject = new JSONObject();
        		List<String[]> abndrillList  =  adminPanelService.getAbnCumulative(commonFilter);
        		//jsonObject = UIUtils.convertToJqGridTableObject(abndrillList,request,2,0,commonFilter.getTotalRecordCnt());
        		
        		// JSONObject HSEData = UIUtils.convertToJqGridTableObject(hseAccidentRpt,request,0,0,hseAccidentRpt.size());
        			
        		jsonObject =UIUtils.convertToJqGridTableObject(abndrillList,request,4,0,commonFilter.getTotalRecordCnt()+2);
			
        		out.println(jsonObject);
				// commonFilter.setViewClick('N');	  		
				 httpSession.removeAttribute("AbnCumulativeReportCommonFilter");
				 httpSession.setAttribute("AbnCumulativeReportCommonFilter", commonFilter);
			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("error " +e.getMessage());
			}
		}
		  
		else if (action.equals("transactionSummary_input.apdb")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("pages/Reports/transactionSummary.jsp");
			System.out.println("Inside Page");
			rd.forward(request, response);

		}else if(action.equals("transactionSummary_getCol.apdb")){			
			  PrintWriter out = response.getWriter();			
			  CommonFilter commonFilter = populateCommonFilter(request,"transactionSummaryCommonFilter",true);
			  String flid=request.getParameter("flid");
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="01-APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String NextYear=CurrentYear.substring(0,3);
			 System.out.println("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="31-MAR-"+(NextYear)+1;
			 System.out.println("FYearEnd"+FYearEnd);
			 String FromDate=request.getParameter("FromDate");
			    String ToDate=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	                             System.out.println("Finance::::"+Finance);

			    String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(2,10);
			    System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }
			    if(Finance.equals("Y")){

			 commonFilter.setAbnDetect(FYearStart);
		     commonFilter.setAbnAllch(FYearEnd);
		     commonFilter.setFlid(flid);
			    }
			    else{
			    	
					 commonFilter.setAbnDetect(FromDate);
				     commonFilter.setAbnAllch(ToDate);
				     commonFilter.setFlid(flid);	
			    }
			List<String[]> transactionGridData  = adminPanelService.getTransactionSummaryGridData(commonFilter);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = transactionGridData.get(2);
			String[] colHeaderCond = transactionGridData.get(1);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			
			
			
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "70%%");
			colModel.set("tableWidth", "98%%");
			httpSession.removeAttribute("transactionSummaryColModel");
			httpSession.setAttribute("transactionSummaryColModel", colModel);
			
			httpSession.removeAttribute("transactionSummaryCommonFilter");
			httpSession.setAttribute("transactionSummaryCommonFilter", commonFilter);
			
			out.println(colModel);
			
			
			
		}else if(action.equals("transactionSummary_getData.apdb")){			
			PrintWriter out = response.getWriter();
	
			 String flid=request.getParameter("flid");
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="01-APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String NextYear=CurrentYear.substring(0,3);
			 System.out.println("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="31-MAR-"+(NextYear)+1;
			 System.out.println("FYearEnd"+FYearEnd);
			 String FromDate=request.getParameter("FromDate");
			    String ToDate=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	                             System.out.println("Finance::::"+Finance);

			    String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(2,10);
			    System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }

			
			CommonFilter commonFilter=populateCommonFilter(request, "transactionSummaryCommonFilter", false);
			 if(Finance.equals("Y")){

			commonFilter.setAbnDetect(FYearStart);
			commonFilter.setAbnAllch(FYearEnd);
			 commonFilter.setFlid(flid);
			 }
			 else{
					commonFilter.setAbnDetect(FromDate);
					commonFilter.setAbnAllch(ToDate);
					 commonFilter.setFlid(flid); 
			 }
			List<String[]> transactionGridData = adminPanelService.getTransactionSummaryGridData(commonFilter);
			
			CommonFunctions.debugMsg("transactionGridData.size==="+transactionGridData.size());
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0,commonFilter.getTotalRecordCnt() );
			//JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0,transactionGridData.size() );
			//JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request, 3, 0);
			out.print(dataJson);
			httpSession.removeAttribute("transactionSummaryCommonFilter");
			httpSession.setAttribute("transactionSummaryCommonFilter", commonFilter);
			
		}else if( action.equals("transactionSummary_getExcel.apdb")){
			CommonFilter commonFilter = populateCommonFilter(request,"transactionSummaryCommonFilter",false);
			//CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("transactionSummaryCommonFilter");
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("transactionSummaryColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String dateVal="";
			CommonFunctions.debugMsg("commonFilter.getMonwise"+commonFilter.getMonwise());
			if(commonFilter.getMonwise().equals("Y")){
				dateVal = commonFilter.getFromMonth() + " to " + commonFilter.getToMonth();
			}else {
				dateVal = commonFilter.getFromDate() + " to " + commonFilter.getToDate();
			}
			if(dateVal.equals("Jan-1801 to Dec-2100")){
				tblJSONObj.put("title", "Transaction Summary Report " );
			}else{
				tblJSONObj.put("title", "Transaction Summary Report - " + dateVal);
			}
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = adminPanelService.getTransactionSummaryGridDataExportExcel(commonFilter,tblJSONObj,format);
			//Workbook wb = productionLossService.getLossExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "TransactionSummaryReport", format);
			
			
		}

		
		else if(action.equals("momattendancemonthwisereportNew_getCol.apdb")){
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",true);
			String Flid =request.getParameter("flid");
			/*String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");*/
			//String type =request.getParameter("type");
			String Type=request.getParameter("meetingtype");
			System.out.println("The Type is"+Type);
            String DMTflid=request.getParameter("DMTflid");
			System.out.println("DMTflid"+DMTflid);
			String DmtOriginalId=adminPanelService.getDmtFlid(DMTflid);
			List<String[]> momReportList;
			PrintWriter out = response.getWriter();
			   String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="APR-"+PreviousYear;
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				 String FYearEnd="MAR-"+(NextYear)+1;
				 String FromMonth=request.getParameter("FromDate");
				 String ToMonth=request.getParameter("ToDate");
				 System.out.println("FromMonth:"+FromMonth);
				 System.out.println("ToMonth:"+ToMonth);
				 String Finance=request.getParameter("Finance");
		         System.out.println("Finance::::"+Finance);
				 commonFilter.setAbnDetect(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
			     commonFilter.setFlid(Flid);
			     commonFilter.setAbnDetectBy(DmtOriginalId);
			     commonFilter.setFirstLevel(Finance);
				 commonFilter.setAbnImp(FromMonth);
				 commonFilter.setAbnIsHSE(ToMonth);
				 commonFilter.setFlid(Flid);
				 commonFilter.setAbnDetectBy(DmtOriginalId); 
			    
			try {
								
				if(UIUtils.isValidKeyId(Type))
				commonFilter.setMaintMode(Type);
			momReportList = adminPanelService.getAttendancemonthwiseNewReport(commonFilter,Flid);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			String [] colHeader = momReportList.get(2);			
			String [] colHeaderCond = momReportList.get(1);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			//gridColModel.setFormatter("setattendanceReport");
			//gridColModel.setFormattorFromCol("11");
			//gridColModel.setFormattorToCol(""+(colHeader.length));	
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("MomAttReportMonthwiseColmodel");
			httpSession.setAttribute("MomAttReportMonthwiseColmodel", jsonObject);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			CommonFunctions.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}  
			
	    }
		
		else if(action.equals("momattendancemonthwisereportNew_getData.apdb")){
			String Flid =request.getParameter("flid");
			/*String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");*/
			
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
			    
	/*		if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }*/
			  
			String Type=request.getParameter("meetingtype");
			//  String pillarId =request.getParameter("pillarId");
			String DMTflid=request.getParameter("DMTflid");
			System.out.println("DMTflid"+DMTflid);
			String DmtOriginalId=adminPanelService.getDmtFlid(DMTflid);
			   String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="APR-"+PreviousYear;
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				// String CurrentYear=CommonFunctions.getCurrentYear();
				 String FYearEnd="MAR-"+(NextYear)+1;
			
			    String FromMonth=request.getParameter("FromDate");
			    String ToMonth=request.getParameter("ToDate");
			    String Finance=request.getParameter("Finance");
	            System.out.println("Finance::::"+Finance);
			
	            
	             commonFilter.setAbnDetect(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
			     commonFilter.setFlid(Flid);
			     commonFilter.setAbnDetectBy(DmtOriginalId);
			     commonFilter.setFirstLevel(Finance);
				 commonFilter.setAbnImp(FromMonth);
				 commonFilter.setAbnIsHSE(ToMonth);
				 commonFilter.setFlid(Flid);
				 commonFilter.setAbnDetectBy(DmtOriginalId); 
	            
	            
	            
			List<String[]> MachineGrid = adminPanelService.getAttendancemonthwiseNewReport(commonFilter,Flid);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 3, 0,commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//System.out.println(e.getMessage());
	}
  }
	
		else if(action.equals("AbnormalityAegingReport_getCol.apdb")){	
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"JhAuditRptCommonFilter",true);
			commonFilter.setMonwise("Y");
		
			
			System.out.println("Brfore jsp"+action);
			
			
		     String maintype=request.getParameter("maintype");
		     String flid=request.getParameter("flid");
		     String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String NextYear=CurrentYear.substring(0,3);
			 System.out.println("FYearEnd"+NextYear);
			// String CurrentYear=CommonFunctions.getCurrentYear();
			 String FYearEnd="MAR-"+(NextYear)+1;
			 String FromDate=request.getParameter("FromDate");
			 String ToDate=request.getParameter("ToDate");
			 String Finance=request.getParameter("Finance");
	         System.out.println("Finance::::"+Finance);
	         System.out.println("maintype:"+maintype);

			 /*   String FromMonth=null;
			    String ToMonth=null;
			    if(FromDate.length()>1&&ToDate.length()>1){
			     FromMonth=FromDate.substring(3,11);
			     System.out.println("FromMonth::::"+FromMonth);
				
			     ToMonth=ToDate.substring(3,11);
			    
			    System.out.println("ToMonth::::"+ToMonth);
			    }
*/
			 
			if(Finance.equals("Y")){
			   commonFilter.setAbnDetect(FYearStart);
		       commonFilter.setAbnAllch(FYearEnd);
		       commonFilter.setFlid(flid);
		      }
			    else{
					  commonFilter.setAbnDetect(FromDate);
				      commonFilter.setAbnAllch(ToDate);
				      commonFilter.setFlid(flid);
				      }	
			commonFilter.setType(maintype);
            GridParams gridParams=new GridParams();
			FilterValues.populateGridParams(request,gridParams);	
		if(UIUtils.isValidKeyId(maintype)){
			
			  out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewDashboardProperty", "AbnormalityAging"));
		
			 }
			 else{
				 System.out.println("Inside else");
	             out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewDashboardProperty", "AbnormalityAgingType"));	 
			 }
		}
		
		else if( action.equals("AbnormalityAegingReport_getData.apdb") )
		{
			 CommonFilter commonFilter = populateCommonFilter(request,"EmployeeDelCommonFilter", false);
				PrintWriter out = response.getWriter();
				 GridParams gridParams=new GridParams();
				 String maintype=request.getParameter("maintype");
			     String flid=request.getParameter("flid");
			     String CurrentYear=CommonFunctions.getCurrentYear();
				 System.out.println("CurrentYear"+CurrentYear);
				 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
				 System.out.println("PreviousYear"+PreviousYear);
				 String FYearStart="01-APR-"+PreviousYear;
				
				 System.out.println("FYearStart"+FYearStart);
				 String NextYear=CurrentYear.substring(0,3);
				 System.out.println("FYearEnd"+NextYear);
				// String CurrentYear=CommonFunctions.getCurrentYear();
				 String FYearEnd="31-MAR-"+(NextYear)+1;
				 String FromDate=request.getParameter("FromDate");
				 String ToDate=request.getParameter("ToDate");
				 String Finance=request.getParameter("Finance");
		         System.out.println("Finance::::"+Finance);

				 /*   String FromMonth=null;
				    String ToMonth=null;
				    if(FromDate.length()>1&&ToDate.length()>1){
				     FromMonth=FromDate.substring(3,11);
				    System.out.println("FromMonth::::"+FromMonth);
					
				     ToMonth=ToDate.substring(3,11);
				    
				    System.out.println("ToMonth::::"+ToMonth);
				    }*/

				 if(Finance.equals("Y")){
				 commonFilter.setAbnDetect(FYearStart);
			     commonFilter.setAbnAllch(FYearEnd);
			      commonFilter.setFlid(flid);
			      
			      }
				    else{
						  commonFilter.setAbnDetect(FromDate);
					      commonFilter.setAbnAllch(ToDate);
					      commonFilter.setFlid(flid);
					      }	
				 commonFilter.setType(maintype);
				FilterValues.populateGridParams(request,gridParams);
				List<String[]> PendingAbnlist = adminPanelService.PendingAbnlist(commonFilter,gridParams,Finance);
				JSONObject PAbn = UIUtils.convertToJqGridTableObject(PendingAbnlist, request,0,1,gridParams.getTotalRecordCnt()+1);
				out.println(PAbn);
				httpSession.removeAttribute("EmployeeDelCommonFilter");
				httpSession.setAttribute("EmployeeDelCommonFilter",commonFilter);
		}		  
		
		  
		else if(action.equals("TeamPerformance_input.apdb"))
		{
			System.out.println("Brfore jsp"+action);	
			String Flid=request.getParameter("Flid");
			System.out.println("Flid Is:::::::"+Flid);
			request.setAttribute("Flid", Flid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Gen/TeamPerformance.jsp");
			rd.forward(request, response); 
		}
else if(action.equals("TeamMember_getCol.apdb")){
			 
			
			
			/*System.out.println("Inside Action "+action);
				
			PrintWriter out = response.getWriter();
					
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.TeamPerformance","TeamPerformance");
					JSONObject colmodel = JSONObject.fromString(tableModel);
					
					out.println(colmodel);*/
			  System.out.println("Inside the GetCol AccImage:::");
			   	PrintWriter out = response.getWriter();
			 		JSONObject jsonObject = new JSONObject();
			 		HttpSession httpsession = request.getSession(false);
			 		List<String[]> visualSopGrid = null;
			 		String fileName=UIUtils.getImagePath(request);
			 		System.out.println("visualsopDetailReportGetCol fileName" + fileName);
			 		String keyId = request.getParameter("keyId");
			 		System.out.println("The Keyid Is:::"+keyId);
			 		try {
					   
			 			
					    visualSopGrid = adminPanelService.getEmployeeList(keyId,fileName);
			 			
			 		} catch (Exception e) {
			 			e.printStackTrace();
			 		}
			 		jsonObject = getTableModelForEmployeeImage(visualSopGrid);
			 		httpSession.removeAttribute("IncdntColModel");
			 		httpSession.setAttribute("IncdntColModel", jsonObject);
			 		httpSession.setAttribute("keyId", keyId);
			 		out.println(jsonObject);
		}
		else if (action.equals("TeamMember_getData.apdb")) {
			//	System.out.println("get data method");
				String Flid=request.getParameter("flid");
				
				System.out.println("Flid"+Flid);
				request.setAttribute("Flid", Flid);
				String fileName=UIUtils.TPM_TEMPIMG_DIR;
				
				
				try {
					
						List<String[]> MachineGrid = adminPanelService.getEmployeeList(Flid,fileName);
						//System.out.println("Data is enter or not" + MachineGrid.get(0));
						PrintWriter out = response.getWriter();
						//System.out.println("get data method1");
						JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 1, 0);
						out.println(machinegrid);
					
					
				} catch (Exception e) {
					//System.out.println(e.getMessage());
				}	
			}
		  
		  
		
		else if(action.equals("AbnormalityTeamPer_input.apdb")){
			String empKeyId=request.getParameter("empKeyId");
			System.out.println("empKeyId"+empKeyId);
			String EmployeeName=request.getParameter("EmployeeName");
			System.out.println("EmployeeName"+EmployeeName);
			String EmployeeLogin=request.getParameter("EmployeeLogin");
			System.out.println("EmployeeLogin"+EmployeeLogin);
			String Flid=request.getParameter("Flid");
			String JH=request.getParameter("JH");
			String DMT=request.getParameter("DMT");
			String TotalAbnormalityCount=adminPanelService.getTotalAbnormalityCount(empKeyId,Flid);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="01-APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String TillDate=CommonFunctions.getDate();
			 System.out.println("TillDate"+TillDate);
			System.out.println("Flid::::::::");
			System.out.println("DMT::::::::"+DMT);
			System.out.println("JH::::::::"+JH);
			request.setAttribute("TillDate", TillDate);
			
			request.setAttribute("FYearStart", FYearStart);
			request.setAttribute("TotalAbnormalityCount", TotalAbnormalityCount);
			request.setAttribute("JH",JH);
			request.setAttribute("DMT",DMT);
			request.setAttribute("empKeyId",empKeyId);
			request.setAttribute("EmployeeName",EmployeeName);
			request.setAttribute("EmployeeLogin",EmployeeLogin);
			RequestDispatcher rd=request.getRequestDispatcher("pages/AbnormalityTeamPerformance.jsp");
	    	rd.forward(request, response); 
		}
		else if (action.equals("AbnormalityTeamPer_getCol.apdb")) {//first page
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityCommonFilter",true);
			PrintWriter out = response.getWriter();
			String empKeyId =request.getParameter("empKeyId");
			System.out.println("empKeyId"+empKeyId);
			commonFilter.setEmpch(empKeyId);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="01-APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String TillDate=CommonFunctions.getDate();
			 System.out.println("TillDate"+TillDate);
			 commonFilter.setIsGetCol("Y");
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				riskListGrid= adminPanelService.getAbnTeamPerData(commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}	
			
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "70%%");
	   	     httpSession.removeAttribute("AbnormalityColData");
			 out.println(jsonObject);
		}
	else if (action.equals("AbnormalityTeamPer_getData.apdb")) {			
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"AbnormalityCommonFilter",false);
			FilterValues.getCommonFilters(request, commonFilter);
			String empKeyId =request.getParameter("empKeyId");
			System.out.println("empKeyId"+empKeyId);
			 String CurrentYear=CommonFunctions.getCurrentYear();
			 System.out.println("CurrentYear"+CurrentYear);
			 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
			 System.out.println("PreviousYear"+PreviousYear);
			 String FYearStart="01-APR-"+PreviousYear;
			 System.out.println("FYearStart"+FYearStart);
			 String TillDate=CommonFunctions.getDate();
			 System.out.println("TillDate"+TillDate);
			commonFilter.setEmpch(empKeyId);
			commonFilter.setIsGetCol("N");
			List<String[]> MasterGrid = adminPanelService.getAbnTeamPerData(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonFunctions.debugMsg(e.getMessage());
		}	
	}
	else if(action.equals("SuggestionTeamPer_input.apdb")){
		String empKeyId=request.getParameter("empKeyId");
		System.out.println("empKeyId"+empKeyId);
		String EmployeeName=request.getParameter("EmployeeName");
		String EmployeeLogin=request.getParameter("EmployeeLogin");
		String JH=request.getParameter("JH");
		String DMT=request.getParameter("DMT");
		String Flid=request.getParameter("Flid");
		String TotalSuggestionCount=adminPanelService.getTotalSuggestionCount(empKeyId,Flid);
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 System.out.println("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 System.out.println("PreviousYear"+PreviousYear);
		 String FYearStart="01-APR-"+PreviousYear;
		 System.out.println("FYearStart"+FYearStart);
		 String TillDate=CommonFunctions.getDate();
		 System.out.println("TillDate"+TillDate);
		System.out.println("Flid::::::::"+Flid);
		System.out.println("DMT::::::::"+DMT);
		System.out.println("JH::::::::"+JH);request.setAttribute("TillDate", TillDate);
		
		request.setAttribute("FYearStart", FYearStart);
		
		request.setAttribute("TotalSuggestionCount", TotalSuggestionCount);
		request.setAttribute("JH",JH);
		request.setAttribute("DMT",DMT);
		request.setAttribute("empKeyId",empKeyId);
		request.setAttribute("EmployeeName",EmployeeName);
		request.setAttribute("EmployeeLogin",EmployeeLogin);
		RequestDispatcher rd=request.getRequestDispatcher("pages/SuggestionTeamPerformance.jsp");
    	rd.forward(request, response); 
	}
	else if (action.equals("SuggestionTeamPer_getCol.apdb")) {//first page
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		PrintWriter out = response.getWriter();
		String empKeyId =request.getParameter("empKeyId");
		System.out.println("empKeyId"+empKeyId);
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 System.out.println("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 System.out.println("PreviousYear"+PreviousYear);
		 String FYearStart="01-APR-"+PreviousYear;
		 System.out.println("FYearStart"+FYearStart);
		 String TillDate=CommonFunctions.getDate();
		 System.out.println("TillDate"+TillDate);
		commonFilter.setEmpch(empKeyId);
		commonFilter.setFromMonth(FYearStart);
		commonFilter.setToMonth(TillDate);
		commonFilter.setIsGetCol("Y");
		long TotalCount=commonFilter.getTotalRecordCnt();
		System.out.println("TotalCount::::::::"+TotalCount);
		
		JSONObject jsonObject = new JSONObject();
		List<String[]>riskListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			riskListGrid= adminPanelService.getSuggTeamPerData(commonFilter);
			System.out.println("Size"+riskListGrid.size());
		} catch (Exception e) {
			CommonFunctions.debugMsg(e.getMessage());
		}	
		
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(true);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);	
		 
		 String [] colHeaderHead = riskListGrid.get(0);
		 String [] colHeader = riskListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);			
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "108%%");
     	 jsonObject.set("tableHeight", "70%%");
   	     httpSession.removeAttribute("ImprovementColData");
		 System.out.println("jsonObject"+jsonObject);
		 out.println(jsonObject);
	}
else if (action.equals("SuggestionTeamPer_getData.apdb")) {			
	try {
		CommonFilter commonFilter = populateCommonFilter(request,commonFilterIden,false);
		FilterValues.getCommonFilters(request, commonFilter);
		String empKeyId =request.getParameter("empKeyId");
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 System.out.println("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 System.out.println("PreviousYear"+PreviousYear);
		 String FYearStart="01-APR-"+PreviousYear;
		 System.out.println("FYearStart"+FYearStart);
		 String TillDate=CommonFunctions.getDate();
		 System.out.println("TillDate"+TillDate);
		System.out.println("empKeyId"+empKeyId);
		commonFilter.setEmpch(empKeyId);
		commonFilter.setFromMonth(FYearStart);
		commonFilter.setToMonth(TillDate);
		commonFilter.setIsGetCol("N");
		List<String[]> MasterGrid = adminPanelService.getSuggTeamPerData(commonFilter);
		PrintWriter out = response.getWriter();
		JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
		out.println(ResourceGridmod);
	} catch (Exception e) {
		CommonFunctions.debugMsg(e.getMessage());
	}	
}

		
		else if(action.equals("TeamPerformanceCount_input.apdb")){
			System.out.println("Brfore jsp"+action);	
			String Flid=request.getParameter("Flid");
			System.out.println("Flid Is:::::::"+Flid);
			request.setAttribute("Flid", Flid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Gen/TeamPerformanceCount.jsp");
			rd.forward(request, response); 
		}
		
		else if(action.equals("TeamPerformanceCount_getCol.apdb")){			
			PrintWriter out = response.getWriter();	
			String Flid=request.getParameter("Flid");
			String Dept=request.getParameter("Functon");
			System.out.println("Flid::::::::::::::::::::::::::"+Flid);
			request.setAttribute("Flid", Flid);
			//&fromdate="+fromdate+"&todate="+todate+"&flid="+flid
			String FromDate=request.getParameter("fromdate");
			String ToDate=request.getParameter("todate");
			String ButtonClick=request.getParameter("Click");
			System.out.println("ButtonClick::::::::::::::::::::::::::"+ButtonClick);
			String EmployeeType=request.getParameter("EmployeeType");
			String fileName=UIUtils.getImagePath(request);
	 		System.out.println("Employee fileName" + fileName);
			CommonFilter commonFilter = populateCommonFilter(request,"TeamPerformanceCountCommonFilter",true);
			commonFilter.setFromDate(FromDate);
			commonFilter.setType(EmployeeType);
			commonFilter.setToDate(ToDate);
			commonFilter.setFlid(Flid);
			
			
			commonFilter.setKey(ButtonClick);
			commonFilter.setIsGetCol("Y");
			List<String[]> transactionGridData= adminPanelService.getTeamPerCount(commonFilter,Dept,fileName);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(1);

			String[] colHeader = transactionGridData.get(1);
			String[] colHeaderCond = transactionGridData.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			//gridColModel.setFormatter("txtFormatterimg");
		   /// gridColModel.setFormattorFromCol("6");
		   // gridColModel.setFormattorToCol("6");
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			//colModel = getTableModelForEmployeeCount(transactionGridData);
			colModel.set("tableHeight", "90%%");
			colModel.set("tableWidth", "110%%");
			httpSession.removeAttribute("TeamPerformanceCountColModel");
			httpSession.setAttribute("TeamPerformanceCountColModel", colModel);
			httpSession.removeAttribute("TeamPerformanceCountCommonFilter");
			httpSession.setAttribute("TeamPerformanceCountCommonFilter", commonFilter);
			out.println(colModel);	
			System.out.println("Exiting getcol TeamPerformanceCount_getcol");
		}
		
		else if(action.equals("TeamPerformanceCount_getData.apdb")){
			System.out.println("Entering getdata TeamPerformanceCount_getData");
			PrintWriter out = response.getWriter();	
			String Flid=request.getParameter("Flid");
			System.out.println("Flid"+Flid);
			request.setAttribute("Flid", Flid);
			String Dept=request.getParameter("Functon");
			System.out.println("Flid::::::::::::::::::::::::::"+Flid);
			request.setAttribute("Flid", Flid);
			
			//&fromdate="+fromdate+"&todate="+todate+"&flid="+flid
			String FromDate=request.getParameter("fromdate");
			String ToDate=request.getParameter("todate");
			String ButtonClick=request.getParameter("Click");
			String EmployeeType=request.getParameter("EmployeeType");
			System.out.println("dept::::::::::::::::::::::::::"+Dept);
			String fileName=UIUtils.TPM_TEMPIMG_DIR;
	 		System.out.println("Employee fileName" + fileName);
			CommonFilter commonFilter=populateCommonFilter(request, "TeamPerformanceCountCommonFilter", false);
			/*commonFilter.setFromDate(FromDate);
			commonFilter.setType(EmployeeType);
			commonFilter.setToDate(ToDate);
			commonFilter.setFlid(Flid);
			commonFilter.setKey(ButtonClick);
			commonFilter.setKey(ButtonClick);*/
			commonFilter.setIsGetCol("N");
			List<String[]> transactionGridData = adminPanelService.getTeamPerCount(commonFilter,Dept,fileName);
			CommonFunctions.debugMsg("transactionGridData.size==="+transactionGridData.size());
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(transactionGridData,request,2, 0,commonFilter.getTotalRecordCnt() );
			out.print(dataJson);
			httpSession.removeAttribute("TeamPerformanceCountCommonFilter");
			httpSession.setAttribute("TeamPerformanceCountCommonFilter", commonFilter);
		}
		else if(action.equals("Combo_UserType.apdb"))
		{
			try 
			{
				 response.setContentType("text/html;charset=UTF-8");
				 response.setContentType("json");
			 	 PrintWriter out = response.getWriter();
			     out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeDelPrv", "SelectType"));
				 out.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		  
		else if( action.equals("TeamPerformanceCount_getExcel.apdb")){
			CommonFilter commonFilter = populateCommonFilter(request,"TeamPerformanceCountCommonFilter",true);
			
			String Flid=request.getParameter("Flid");
			System.out.println("Flid"+Flid);
			request.setAttribute("Flid", Flid);
			String Dept=request.getParameter("Functon");
			System.out.println("Flid::::::::::::::::::::::::::"+Flid);
			request.setAttribute("Flid", Flid);
			//&fromdate="+fromdate+"&todate="+todate+"&flid="+flid
			String FromDate=request.getParameter("fromdate");
			String ToDate=request.getParameter("todate");
			String ButtonClick=request.getParameter("Click");
			System.out.println("ButtonClick::::::::::::::::::::::::::"+ButtonClick);
			String EmployeeType=request.getParameter("EmployeeType");
			commonFilter.setFromDate(FromDate);
			commonFilter.setType(EmployeeType);
			commonFilter.setToDate(ToDate);
			commonFilter.setFlid(Flid);
			commonFilter.setKey(ButtonClick);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("TeamPerformanceCountColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String dateVal="";
			CommonFunctions.debugMsg("commonFilter.getMonwise"+commonFilter.getMonwise());
			if(ButtonClick!=null){
				dateVal = FromDate + " to " + ToDate;
			}else {
				dateVal = FromDate + " to " + ToDate;
			}
			
				tblJSONObj.put("title", "Team Performance Dashboard Count - " + dateVal);
			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = adminPanelService.getTeamPerformanceCountExportExcel(commonFilter,tblJSONObj,format,Dept);
			//Workbook wb = productionLossService.getLossExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "TransactionSummaryReport", format);
		}
		  
		else if (action.equals("JhauditActionPlanScore_getExcel.apdb")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"JhAuditRptCommonFilter",false);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			 if(action.equals("JhauditActionPlanScore_getData.JhAuditRpt"))
					commonFilter.setType("Actionplan");
			     
			     String maintype=request.getParameter("maintype");
			     
			     CommonFunctions.debugMsg(" maintype :: GetData ::  "+maintype);
				 if(UIUtils.isValidKeyId(maintype))
					 commonFilter.setMaintMode(maintype);
			
			
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("JhAuditReportColModel");
			
			tblJSONObj.put("title", maintype + " Audit Report");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = adminPanelService.JhAuditActionReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			
			ExcelUtils.writeToResponse(response, wb, maintype+"AuditReport", format);

		}
		
		}
	catch(Exception e){
	  e.printStackTrace();	
	}
		
		
		
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
			System.out.println("totalRecords" +totalRecords);
			tableDataObject.put("page", page); //current page
			tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
			tableDataObject.put("records", totalRecords); //total records
			
			JSONArray rowArr = new JSONArray(); 
		
	      
			int rowId = 0;		
			
	        /*for( String [] row : dataArrayList)
			{
	        	
	        	if( rowId >= rowStart )
	        	{	
	        		JSONObject rowObj =new JSONObject();
	            	JSONArray cell=new JSONArray();
	        		
	        		rowObj.put("id",rowId -rowStart +1);
	        		CommonFunctions.debugMsg("len"+row.length);
	        		for( int i = colStart ;i < row.length; i++)
		            {	 
		            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") );
		            }	
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	        
	       }*/
	        JSONObject rowObj =new JSONObject();
	    	JSONArray cell=null;
	    	rowObj.put("id",rowId -rowStart +1);    
	    /*	if (dataArrayList.size() > 2 )
	    	{
		    	String [] curRow =(String[] )dataArrayList.get(dataArrayList.size()-2);     	
				List<Integer> cumulative = new ArrayList<Integer>();    	
		    	int cnt = 0;
				int total =0;
				int remTotal = 0;
				cumulative.add(0,0);
				cumulative.add(0,1);
		    	for( int k =3; k<curRow.length; k++)
				{	
		    		System.out.println("cur Row : "+curRow[k]);
		    		if(curRow[k]!=null && curRow[k].length()>0)
		    			cnt = Integer.parseInt(curRow[k]);	
		    		System.out.println("K%3"+k%3);
		    			if(k%2 == 0)
		    			{
		    			   total += cnt;	
		    			   System.out.println("cur Row :total in if  "+ total);
		    			   cumulative.add(total);
		    			   System.out.println("cur Row :cum  "+cumulative);
		    			}
		    			else
		    			{
		    				remTotal += cnt;
		    				  System.out.println("cur Row :else  "+ remTotal);
			    			cumulative.add(remTotal);
		    			}
					
				}
		    	 System.out.println("Outside for loop stmt");
		    	 cell = JSONArray.fromCollection(cumulative);
		    	 cell.put(1, "CUMULATIVE");    	
		    	 rowObj.put("cell", cell );
			     rowArr.put(rowObj);    	
	    	}*/
	    	System.out.println(".... "+rowId);
	        tableDataObject.put("rows", rowArr);
	        System.out.println("Fill Grid : "+tableDataObject);
	        
	       
		}
		 return tableDataObject;
	}
	
	private void displayNewDashboardPage(HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println("Inside the Admin Panel dashboard");
			String pillar = request.getParameter("pillar");
			System.out.println("The Pillar Is:::"+pillar);
			String fromPage = request.getParameter("fromPage");
			String type = request.getParameter("type");
			System.out.println("The Type Is"+type);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			request.setAttribute("FromMonth", commonFilter.getFromMonth());
			request.setAttribute("ToMonth", commonFilter.getToMonth());
			request.setAttribute("LstFromMonth", CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
			request.setAttribute("lastTheeMonth", CommonFunctions.getFirstDateofMonth(-2).substring(3,11));
			request.setAttribute("lastTwoyear", CommonFunctions.getFirstDateofMonth(-22).substring(3,11));	
			request.setAttribute("pillar", pillar);
			request.setAttribute("type", type);
			request.setAttribute("fromPage", fromPage);
			UIUtils.forwardRequest(request, response, "/pages/Gen/Newdashboard.jsp");
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
			commonFilter = 	FilterValues.getAudit(request, commonFilter);
			if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  CommonFunctions.debugMsg("CommonFunctions.getFirstDateofMonth(-5).substring(3,11)"+CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");				 
		 	  }
			
			CommonFunctions.debugMsg("SKIP"+commonFilter.getChkSkipLine());
			commonFilter.setViewClick('Y');
			
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonFunctions.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}
	
	private JSONObject getTableModel(List<String[]> headers,CommonFilter commonFilter, String reportNmae)
	{
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		 
		String[] colHeader = headers.get(1);
		//String[] colHeader1 = headers.get(1);
		
		String[] emptyrow = new String[colHeader.length]; 	
		
		emptyrow [0] ="";
		emptyrow [1] ="";
				
		for( int i = 2; i < colHeader.length;i++ ){			
			emptyrow [i] = "";
		}		
		
		//jqGridTableModel.getRowHeaders().add(emptyrow);		
		jqGridTableModel.getRowHeaders().add(colHeader);
		//jqGridTableModel.getRowHeaders().add(colHeader1);
		
		jqGridTableModel.setSortable(false);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableHeight(300);
		jqGridTableModel.setPaginate(true);
	//	jqGridTableModel.getColModel().add(getColModel("keyid", 50,"left",true,false,true));
		 
		  		for(int i =0; i < colHeader.length; i++)
		{
			System.out.println("Length : "+colHeader.length);
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+ i);
			CommonFunctions.debugMsg("colHeader[i] "+colHeader[i]);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+ i);
			jqGridColModel.setWidth(100);				
			//jqGridColModel.setAlin("left");
			jqGridColModel.setEditable(false);
			
			if(i==0||i==1||i==12|| i == 14)
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}else if(i == 6 )
			{
				System.out.println("6");
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
			}
			else if(i == 7)
			{
				System.out.println("Responsibility");
				jqGridColModel.setWidth(120);
				jqGridColModel.setAlign("center");
			}
			else if(i == 8 ||i==9 || i==10)
			{
				System.out.println("target");
				jqGridColModel.setWidth(120);
				jqGridColModel.setAlign("center");
			}
			else if(i == 9||i == 23||i == 15||i == 22)
			{
				jqGridColModel.setWidth(120);
				jqGridColModel.setAlign("left");
			}else if(i == 6||i == 16||i == 17||i == 13){
				jqGridColModel.setWidth(150);
				jqGridColModel.setAlign("left");
			}
			else if(i==3){
				jqGridColModel.setWidth(150);
				//jqGridColModel.setAlign("center");
			}
			else if(i==4|| i==5){
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
			else if(i == 21){
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			}else if (i == 12){
				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
			}
			else
			{
				if(i==2)
				{
					jqGridColModel.setHidden(true);
					jqGridColModel.setWidth(120);
					jqGridColModel.setAlign("left");
				}
			}
				/*	else
				{
					
					if(i==4 || i == 5 ||i==10  )
					{
						jqGridColModel.setWidth(80);
						jqGridColModel.setAlign("center");
						
					}
					else if(i == 11 || i == 2 || i == 3 || i == 9)
					{
						jqGridColModel.setWidth(80);
						jqGridColModel.setAlign("left");
					}
					else if(i == 6 )
						jqGridColModel.setWidth(150);
					
				}
					
				
			}
			 	*/
			if(reportNmae.equals("JhauditActionPlanScore_getCol.apdb")) {
				//jqGridTableModel.setGroupBy(true);
				//jqGridTableModel.setGroupByField("GROUPINGCOLUMN1");
			}
				
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	private JSONObject getKaizenTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(1);
		String[] colHeader1 = headers.get(2);
		colHeader[3] = "DATE";
	
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonFunctions.debugMsg("test..."+colHeader.length);
		for (int i = 2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		//colHeader1[3] = caption;  
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 3) {
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(70);
			}

			else if (i == 16) {
				CommonFunctions.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "67%%");
		return tableModel;
	}
	private JSONObject getTableModelEmployeeWiseIncident(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		String[] colHeader = headers.get(2);
		String[] colHeader1 = headers.get(3);
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		CommonFunctions.debugMsg("test..."+colHeader.length);
		System.out.println("col header lenght"+colHeader.length);
		for (int i =2; i <colHeader.length; i++) {
			emptyrow[i] = "";
		}
		jqGridTableModel.getRowHeaders().add(emptyrow);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		//colHeader1[3] = caption;  
		for (int i = 0; i <= colHeader.length-1; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + i);
			jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(colHeader1[i].replaceAll(" ", "") + i);

			jqGridColModel.setWidth(200);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);

			if (i == 0 || i == 1 || i==2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			} else if (i > 3) {
				// jqGridColModel.setHidden(false);
				jqGridColModel.setKey(true);
				jqGridColModel.setAlign("right");
				jqGridColModel.setWidth(74);
			}

			else if (i == 16) {
				CommonFunctions.debugMsg("Length of col:" + colHeader.length);
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			}

			jqGridTableModel.getColModel().add(jqGridColModel);
			
			//headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "67%%");
		return tableModel;
	}
	private void buildTableCountColModel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		String firstClick = request.getParameter("firstClick");
	    String type=request.getParameter("type");
	    String flid=request.getParameter("flid");
	    String FromDate=request.getParameter("FromDate");	    
	    String ToDate=request.getParameter("ToDate");
	    String FromMonth=null;
	    String ToMonth=null;
	    if(FromDate.length()>1&&ToDate.length()>1){
	    	System.out.println("Inside If");
	     FromMonth=FromDate.substring(3,11);
	  
		
	    ToMonth=ToDate.substring(3,11);
	   
	    }
	    String Finance=request.getParameter("Finance");
	    System.out.println("ToMonth::::;;+FromMonth"+Finance);
	    System.out.println("Flid"+flid+"FromDate"+FromDate+"ToDate"+ToDate+"Finance"+Finance);
		CommonFilter commonFilter = null;
		commonFilter = populateCommonFilter(request,gendrillcommonfilter,true);
		commonFilter.setAssType(type);
		if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
			 commonFilter = (CommonFilter) httpSession.getAttribute(gendrillcommonfilter);
		}
		
		if (commonFilter == null)
			commonFilter = new CommonFilter();
		httpSession.removeAttribute(gendrillcommonfilter);
		httpSession.setAttribute(gendrillcommonfilter, commonFilter);
		 System.out.println(" Constants.passNullDate " + Constants.passNullDate + " commonFilter.getFromMonth() " +  commonFilter.getFromMonth());
		 
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 System.out.println("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 System.out.println("PreviousYear"+PreviousYear);
		 String FYearStart="APR-"+PreviousYear;
		 System.out.println("FYearStart"+FYearStart);
		 String NextYear=CurrentYear.substring(0,3);
		 System.out.println("FYearEnd"+NextYear);
		// String CurrentYear=CommonFunctions.getCurrentYear();
		 String FYearEnd="MAR-"+(NextYear)+1;
		 System.out.println("FYearEnd"+FYearEnd);
		 
		 if(Finance.equals("Y")){
		 commonFilter.setWostatus(FYearStart);
		 commonFilter.setWrkEndTo(FYearEnd);
		 commonFilter.setFlid(flid);
		 }
		 else{
			 commonFilter.setWostatus(FromMonth);
			 commonFilter.setWrkEndTo(ToMonth);
			 commonFilter.setFlid(flid);	 
		 }
//		if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
//		{
//			commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
//			commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
//			commonFilter.setMonwise("Y");
//		}

		List<String[]> behaviorData = adminPanelService.getEHSMetricsCountData(commonFilter);
		JSONObject jsonObject = getEHSMetricsTableModel(behaviorData,commonFilter);
		jsonObject.set("tableHeight", "80%%");
	    jsonObject.set("tableWidth", "95%%");
		httpSession.removeAttribute("ImprovementColModel");
		httpSession.setAttribute("ImprovementColModel", jsonObject);
		out.println(jsonObject);
	}
	private void EHScountGetData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 CommonFilter commonFilter = populateCommonFilter(request,gendrillcommonfilter,false);
		 String type=request.getParameter("type");
		 String flid=request.getParameter("flid");
		 String FromDate=request.getParameter("FromDate");
		    String ToDate=request.getParameter("ToDate");
		    String Finance=request.getParameter("Finance");
		    String FromMonth=null;
		    String ToMonth=null;
		    if(FromDate.length()>1&&ToDate.length()>1){
		     FromMonth=FromDate.substring(3,11);
		    System.out.println("FromMonth::::;;+FromMonth"+FromMonth);
			
		     ToMonth=ToDate.substring(3,11);
		    
		    System.out.println("ToMonth::::;;+FromMonth"+ToMonth);
		    }
		    System.out.println("Flid"+flid+"FromDate"+FromDate+"ToDate"+ToDate+"Finance"+Finance);
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 System.out.println("CurrentYear"+CurrentYear);
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 System.out.println("PreviousYear"+PreviousYear);
		 String FYearStart="APR-"+PreviousYear;
		 System.out.println("FYearStart"+FYearStart);
		 String NextYear=CurrentYear.substring(0,3);
		 System.out.println("FYearEnd"+NextYear);
		// String CurrentYear=CommonFunctions.getCurrentYear();
		 String FYearEnd="MAR-"+(NextYear)+1;
		 System.out.println("FYearEnd"+FYearEnd);
		 if(Finance.equals("Y")){
		 
		 commonFilter.setWostatus(FYearStart);
		 commonFilter.setWrkEndTo(FYearEnd);
		 commonFilter.setFlid(flid);
		 }
		 else{
			 commonFilter.setWostatus(FromMonth);
			 commonFilter.setWrkEndTo(ToMonth);
			 commonFilter.setFlid(flid); 
		 }
		List<String[]> impVscomList = adminPanelService.getEHSMetricsCountData(commonFilter);
	
		JSONObject listToJsonObject = new JSONObject();
		commonFilter.setAssType(type);
		if (impVscomList != null && impVscomList.size() > 1)
			listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 2,0,commonFilter.getTotalRecordCnt()+2);
		
		PrintWriter out = response.getWriter();
		out.println(listToJsonObject);
	}
	
	private JSONObject getTableModelForEmployeeImage(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		System.out.println("column Header::::"+colHeader[0]);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		
		jqGridTableModel.setTableHeight(50);
		jqGridTableModel.setTableWidth(150);
	/*	jqGridTableModel.setTableButton(false);
		jqGridTableModel.setEnableFilter(false);*/
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
		   	jqGridColModel.setWidth(100);
		   	jqGridColModel.setEditable(false);
		   	
		   	if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i==1 ){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
		
			if(i==2){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
				//jqGridColModel.setFormatter("txtFormatterimg");
			
			}
			if(i==3){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(200);
				
				jqGridColModel.setFormatter("txtFormatterimg");
			}
			if(i==4 ){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(300);
				jqGridColModel.setAlign("left");
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
		
			if(i==5){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
				//jqGridColModel.setFormatter("txtFormatterimg");
			
			}
			if(i==6 ){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(220);
			    jqGridColModel.setAlign("center");
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
			if(i==7 ){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setFormatter("AbnormalityBtn");
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
			if(i==8 ){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setFormatter("SuggestionBtn");
				jqGridColModel.setAlign("center");
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
			
			jqGridColModel.setAlign("center");
			//jqGridColModel.setEditable(false);
			System.out.println("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "100%%");
		tableModel.set("tableWidth", "110%%");
		return tableModel;
}


	
	 private JSONObject getEHSMetricsTableModel(List<String[]> headers,CommonFilter commonFilter)
		{
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] colHeader = headers.get(1);
			String[] emptyrow = new String[colHeader.length]; 	
		
			emptyrow [0] ="";
			emptyrow [1] ="";
					
			for( int i = 2; i < colHeader.length;i++ ){			
				emptyrow [i] = "";
			}		
			
			jqGridTableModel.getRowHeaders().add(colHeader);			
			jqGridTableModel.setSortable(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);		
			jqGridTableModel.setTableHeight(300);			 
			  for(int i =0; i < colHeader.length; i++)
			{

				JqGridColModel jqGridColModel = new JqGridColModel();
			
				jqGridColModel.setWidth(100);				
				//jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				
				if(i==0 || i == 1 )
				{
					jqGridColModel.setHidden(true);
					if(i==0)
						jqGridColModel.setKey(true);
					
				
				}
				if(i==2)
				{
					jqGridColModel.setWidth(150);
					jqGridColModel.setAlign("left");
				
				}
				if(i>2)
				{
					jqGridColModel.setWidth(80);
					 System.out.println("Inside center");
					jqGridColModel.setAlign("center");
					
				}		
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			  System.out.println("Inside Table model");
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		}
	 
}

  
