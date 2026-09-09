package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.dao.impl.Constants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.DocManagerService;
import com.akranta.tpm.service.MoMeetingService;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.impl.DocManagerServiceImpl;
import com.akranta.tpm.service.impl.MoMeetingServiceImpl;
import com.akranta.tpm.service.impl.AbnormalityFormServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.controller.UIUtils;

public class MoMettingServlet extends HttpServlet {
	
	/**
	 * created by SATHISH KUMAR.V
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	
	MoMeetingService moMeetingService;
	DocManagerService docManagerService;
    DashboardService dashboardService;
     MomServiceApi momServiceApi;
    
	String  filePath = null;
	private static String DOC_ROOT_PATH;
	private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
	
	//private ServletRequest httpSession;
	/*public void init(ServletConfig config) throws ServletException{
		filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
	}*/
	
	
	public void init(ServletConfig config) throws ServletException {
    	try{
        super.init(config);
        
        filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
		
        docRealPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        
        boolean s = new File(docRealPath).mkdirs();
        
        DOC_ROOT_PATH = getServletContext().getRealPath("DocumentManagerServlet") ;
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        
        String parentFolderName = DOC_ROOT_PATH.substring(DOC_ROOT_PATH.lastIndexOf("\\")+1);
       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        CommonMessage.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        CommonMessage.debugMsg(" FileManagerBasePath " + basePath );
        
        boolean pathExist = new File( basePath).exists();
        CommonMessage.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
        if( basePath != null && pathExist ){
        	DOC_ROOT_PATH = basePath;
        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
        	
        }
        CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
        CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
    	}catch(Exception e ){
    		e.printStackTrace();
    		CommonMessage.debugMsg(" File Manager exception " + e.getMessage());
    	}
    }

	
	
	/*public MoMettingServlet()
	{  
		super();	
	}*/
	
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
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg("action  " + action);
		try {
			//CommonMessage.debugMsg(" service............. ");
			moMeetingService = (MoMeetingServiceImpl) UIUtils.getServiceObject(request, "MoMeetingServiceImpl");
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			docManagerService = (DocManagerServiceImpl)UIUtils.getServiceObject(request, "DocManagerServiceImpl");
			CommonMessage.debugMsg("  Minutes of Meeting jwt token : "+httpSession.getAttribute("tpmjwttoken") );
			moMeetingService.MoMeetingServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
		
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		String dispatchUrl = null;
		
		
		
          /* if (action.equals("MeetingMin_view.mom")) {  This URL Need To Give In Db,MeetingMin_view.mom
			
			
			request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MoMeetingMainGrid.jsp"); 
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/EquipmentCatgForm.jsp"); 
			rd.forward(request, response); 
		}
     		else if( action.equals("getModeMeetingMin_view.mom")){
			response.setContentType("text/html");
			//response.setContentType("text/json");
			
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			
			result.put("mode","create");
			result.put("url","MoMeetingForm_input.mom");
			result.put("formHeader","Meeting Minutes");
			
			out.println(result);
		}
		
		else */
		if (action.equals("MeetingMin_input.mom"))
		{

			String mainForm = request.getParameter("mainForm");
			String momRefDocId = request.getParameter("momRefDocId");
			String momRefDocType = request.getParameter("momRefDocType");
			String type = request.getParameter("type");
			String mode = request.getParameter("mode");
			String stage = request.getParameter("stage");
			
			CommonMessage.debugMsg(" Inside ::  New :: type :: "+type);
			
			if ("true".equals(mainForm)) {
                 request.setAttribute("mainForm", "mainForm");
            }
			
			String DMT = request.getParameter("DMT");
			
			request.setAttribute("DMT", DMT);
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			request.setAttribute("type", type);
			request.setAttribute("mode", mode);
			request.setAttribute("stage", stage);
			CommonMessage.debugMsg("Mode value"+mode);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MoMeetingMainGrid.jsp");
			rd.forward(request, response);			
	    }else if( action.equals("MoMeetingFlid.mom"))
		{	
	 	
	 		String originalId = request.getParameter("originalId");
		
	    	String flid  = moMeetingService.RoleBasedFlid(originalId);
	    	 JSONObject json=new JSONObject();
			PrintWriter out=response.getWriter();
				json.put("flid", flid);
				out.println(json);
		}
		else if(action.equals("mom_view.mom")){
			String mainForm = request.getParameter("mainForm");
			String momRefDocId = request.getParameter("momRefDocId");
			String momRefDocType = request.getParameter("momRefDocType");
			String type = request.getParameter("type");
			String stage = request.getParameter("stage");
			
			request.setAttribute("mode", "view");
			request.setAttribute("stage", stage);
			request.setAttribute("type", type);
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MoMeetingMainGrid.jsp");
			rd.forward(request, response);	
		}else if(action.equals("pillargroup.mom")){
			try 
			{
				String pillarid = request.getParameter("pillarid");
				String flid = request.getParameter("flid");
				String pillargrp = request.getParameter("pillargrp");
				String locationId = request.getParameter("locationId");
				String condSql="";
				ComboFilter comboFilter = new ComboFilter();
				comboFilter=UIUtils.fillComboFilter(request);
					
//				if(pillarid != null)
//					condSql=" AND  MGRM_PILLARID = '"+pillarid+"' ";
					// condSql=" AND  MGRM_PILLARID = '"+pillarid+"' AND MGRM_FLID='"+flid+"' "; 
				if (pillarid != null) {
				    condSql = " AND MGRM_PILLARID = '" + pillarid + "' " +
				              " AND POSITION((" +
				              "SELECT FNLN_KEYID FROM gen_tl_functionallocn " +
				              "WHERE FNLN_ORIGINALID = '" + locationId + "'" +
				              ") IN (PARENTFLIDS || FLID)) > 0 ";
				}
				List<ComboBox>  TagClass = moMeetingService.getPillarGroupcombo(condSql,comboFilter);
			    UIUtils.writeComboBox(response, TagClass ,comboFilter);
			    
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
		}
		else if(action.equals("momattendancereport_input.mom")){
			String type=request.getParameter("type");
			String types=request.getParameter("types");
			String flid=request.getParameter("flid");
			String month=request.getParameter("month");
			request.setAttribute("month", month);
			request.setAttribute("type", type);
			request.setAttribute("types", types);
			request.setAttribute("flid", flid);
	    	UIUtils.forwardRequest(request, response, "/pages/MomAttendanceReport.jsp");
	    }
		//ADDED BY AZEEM
		
		else if(action.equals("momattendancereportDHQ_input.mom")){
			String type=request.getParameter("type");
			String types=request.getParameter("types");
			String flid=request.getParameter("flid");
			String month=request.getParameter("month");
			request.setAttribute("month", month);
			request.setAttribute("type", type);
			request.setAttribute("types", types);
			request.setAttribute("flid", flid);
	    	UIUtils.forwardRequest(request, response, "/pages/MomAttendanceReportDHQ.jsp");
	    }
		else if (action.equals("momattendancereportDHQ_getCol.mom")){
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",true);
			String Flid =request.getParameter("flid");
			String FromDte =request.getParameter("FromDte");
			String ToDte =request.getParameter("ToDte");
			String Frommonth =request.getParameter("Frommonth");
			String Tomonth =request.getParameter("Tomonth");
			String type =request.getParameter("type");
			String meetingtype =request.getParameter("meetingtype");
			String flid =request.getParameter("mthwseflid");
			
			List<String[]> momReportList;
			//CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			commonFilter.setFlid(Flid);
			
			if(UIUtils.isValidKeyId(meetingtype))
			   commonFilter.setMaintMode(meetingtype);
			
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFromMonth(Frommonth);
				commonFilter.setToMonth(Tomonth);
				commonFilter.setFlid(flid);
			}
			
			String PillarId =request.getParameter("PillarId");
			if(UIUtils.isValidKeyId(PillarId))
				   commonFilter.setPillarWise(PillarId);
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: "+commonFilter.getFromDate());
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());

			 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			
			
			try {
				commonFilter.setIsGetCol("Y");
			momReportList = moMeetingService.getAttendanceDHQ(commonFilter,Flid);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = momReportList.get(1);			
			String [] colHeaderCond = momReportList.get(0);
			
			//CommonMessage.debugMsg(" Inside colHeader "+colHeader.length);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			gridColModel.setFormatter("setattendanceReport");
			gridColModel.setFormattorFromCol("10");//CHANGED FROM 11 TO 10 -FOR SB 8TH JAN
			gridColModel.setFormattorToCol(""+(colHeader.length));
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("MomAttReportColmodel");
			httpSession.setAttribute("MomAttReportColmodel", jsonObject);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		else if (action.equals("momattendancereportDHQ_getData.mom")){
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String FromDte =request.getParameter("FromDte");
			String ToDte =request.getParameter("ToDte");
			String flid =request.getParameter("mthwseflid");
			
	        
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
			
			String type =request.getParameter("type");
			String meetingtype =request.getParameter("meetingtype");
			CommonMessage.debugMsg("MEETING TYPE "+meetingtype+" TYPE");
			if(UIUtils.isValidKeyId(type))
			   commonFilter.setType(type);
			
			if(UIUtils.isValidKeyId(meetingtype))
			   commonFilter.setMaintMode(meetingtype);
			
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFromMonth(dtFromMonth);
				commonFilter.setToMonth(dtToMonth);
				commonFilter.setFlid(flid);
			}
			
			String PillarId =request.getParameter("PillarId");
			if(UIUtils.isValidKeyId(PillarId))
				   commonFilter.setPillarWise(PillarId);
			
			
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			  commonFilter.setIsGetCol("N");
			  
			List<String[]> MachineGrid = moMeetingService.getAttendanceDHQ(commonFilter,Flid);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg(" Inside Servlet ACtion ::  "+commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
	}	
		}
		else if (action.equals("momattendancereportDHQ_getExcel.mom")){
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();//
			commonFilter.setFromRow(null);//
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomAttReportColmodel");
			colmodel.put("title","Mom Attendance Report");
            String format = ExcelUtils.getFormat(request);
			String type = request.getParameter("type");
			String meetingtype = request.getParameter("meetingtype");
			String PillarId = request.getParameter("PillarId");
			
			CommonMessage.debugMsg(" type :: "+type+" meetingtype :: "+meetingtype +" PillarId :: "+PillarId);
			
			commonFilter.setType(type);
			commonFilter.setMachineId(PillarId);
			commonFilter.setAbnViewType(meetingtype);
			
			Workbook wb = moMeetingService.getmomAttReportExcelDHQ(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "MomAttendanceReport", format);
		}
		
		else if( action.equals("momattendancemonthwisecount_input.mom")){
			UIUtils.forwardRequest(request, response, "/pages/MomAttendanceCount.jsp");
		}
		else if(action.equals("newmomattendancereport_input.mom")){
			String type=request.getParameter("type");
			
			String types=request.getParameter("types");
			
			String flid=request.getParameter("flid");
			String month=request.getParameter("month");
			request.setAttribute("month", month);
			request.setAttribute("type", type);
			request.setAttribute("types", types);
			request.setAttribute("flid", flid);
	    	UIUtils.forwardRequest(request, response, "/pages/NewMomAttendanceReport.jsp");
		}
		else if(action.equals("momattendancemonthwisecount_getCol.mom")){
			momAttendanceMonthwiseCountTableModel(request,response);
			
		}
		else if(action.equals("momattendancemonthwisecount_getData.mom")){
			momAttendanceMonthwiseCountData(request,response);
		}
		else if(action.equals("momattendancemonthwisecount_getExcel.mom")){
			momAttendanceMonthwiseCountExcel(request,response);
		}
		else if( action.equals("momattendancemonthwisecount_barchart.mom"))
		{
			processbarChart(request,response);
		}
		
		else if(action.equals("momattendancemonthwisereport_input.mom")){
			UIUtils.forwardRequest(request, response, "/pages/MomAttendancemonthwiseReport.jsp");
	    }
		else if(action.equals("momattendancemonthwisereport_getCol.mom")){
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",true);
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String type =request.getParameter("type");
			String pillarId =request.getParameter("pillarId");
			
			List<String[]> momReportList;
			PrintWriter out = response.getWriter();
			commonFilter.setFlid(Flid);
			 if(Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if(Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			try {
				
				CommonMessage.debugMsg(" Checking for getCol :: "+type);
				
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);
				
				if(UIUtils.isValidKeyId(pillarId))
					   commonFilter.setPillarWise(pillarId);
			commonFilter.setIsGetCol("Y");
			momReportList = moMeetingService.getAttendancemonthwise(commonFilter,Flid);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = momReportList.get(1);			
			String [] colHeaderCond = momReportList.get(0);
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
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	    }
		else if(action.equals("momattendancemonthwisereport_getData.mom")){
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
			    
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			  
			  String type =request.getParameter("type");
			  String pillarId =request.getParameter("pillarId");
			  
			  if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			  
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);
			
				CommonMessage.debugMsg(" Checking for getData :: "+type);
				commonFilter.setIsGetCol("N");
			List<String[]> MachineGrid = moMeetingService.getAttendancemonthwise(commonFilter,Flid);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
	}
  }
	else if(action.equals("momattendancemonthwisereport_getExcel.mom")){

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
				Workbook wb = moMeetingService.MomeetingMonthwiseExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingMonthWiseReport", format);

		    }catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}

	    }
		if (action.equals("MoMeetingForm_input.mom"))
		{

			 String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");
		
				CommonMessage.debugMsg(momRefDocId+" momRefDocType "+momRefDocType);
				
			String keyid = request.getParameter("keyId");

            String flid = request.getParameter("flid");
            String mstDate = request.getParameter("Date");
            String MEETHAPPEN = request.getParameter("MEETHAPPEN");
            String DMT = request.getParameter("DMT");
            String DMTDBLE = request.getParameter("DMTDBLE");
            String type = request.getParameter("type");
            String mode = request.getParameter("mode");
            String recall=request.getParameter("recall");
            String shift=request.getParameter("shift");
            CommonMessage.debugMsg("shift:::"+shift);
            CommonMessage.debugMsg("type>>>>"+type);
      
            String menumode=request.getParameter("menumode");
          
            httpSession.setAttribute("mstDate ",mstDate);
			if ((UIUtils.isValidKeyId(keyid))) {
				GenTlMommst genTlMommst = moMeetingService.select(keyid);
				CommonMessage.debugMsg("DATA FETCH FROM INPUT "+genTlMommst.getMomsKeyid());
				CommonMessage.debugMsg("DATA FETCH FROM INPUT "+genTlMommst.getMomsSafetytalk());
				httpSession.setAttribute("momInputDetails", genTlMommst);
				httpSession.setAttribute("momkeyid", genTlMommst.getMomsKeyid());
				genTlMommst.setMomsDate(genTlMommst.getMomsDate().substring(0, 15));
				
				//DMT 24-Feb-2014 10:24:43
				
				//GenTlMomdtl genTlMomdtl = moMeetingService.selectdtl(keyid);
				//GenTlMomattendance genTlMomattendance = moMeetingService.selectatt(keyid);
				
				GenTlMomattendance genTlMomattendance = new GenTlMomattendance();
              	request.setAttribute("mom", genTlMommst);
				request.setAttribute("mstkeyid", keyid);
				request.setAttribute("Flid", flid);
				request.setAttribute("moma", genTlMomattendance);
				request.setAttribute("mstDate", mstDate);
				request.setAttribute("MEETHAPPEN", MEETHAPPEN);
				request.setAttribute("menumode", menumode);
				String Date = UIUtils.removeDefaultDate(genTlMommst.getMomsDate(), " ");

				genTlMommst.setMomsDate(Date);

			}else if(UIUtils.isValidKeyId(recall)){
				//GenTlMommst genTlMommst = moMeetingService.selectRecall(shift,mstDate,flid,type);
				GenTlMommst genTlMommst = new GenTlMommst();
				
				List<String[]> minOfMeetingRecallList  = moMeetingService.selectRecalling(shift,mstDate,flid,type,"");
				CommonMessage.debugMsg(" Recalling Data :: size "+minOfMeetingRecallList.size());
				if(minOfMeetingRecallList.size()>0){
					CommonMessage.debugMsg(" Inside size  "+minOfMeetingRecallList.get(0)[6]);
					CommonMessage.debugMsg(" Inside size  "+minOfMeetingRecallList.get(0)[0]);
					request.setAttribute("Mno",minOfMeetingRecallList.get(0)[0]);
					request.setAttribute("Ismthpn",minOfMeetingRecallList.get(0)[1]);
					request.setAttribute("sfty",minOfMeetingRecallList.get(0)[2]);
					request.setAttribute("rmrk",minOfMeetingRecallList.get(0)[3]);
					request.setAttribute("title",minOfMeetingRecallList.get(0)[4]);
					request.setAttribute("mtype",minOfMeetingRecallList.get(0)[5]);
					request.setAttribute("agnda",minOfMeetingRecallList.get(0)[6]);
					request.setAttribute("pillarid",minOfMeetingRecallList.get(0)[7]);
					request.setAttribute("pillargrpid",minOfMeetingRecallList.get(0)[8]);
				}
			}

			//response.setContentType("xml");

			request.setAttribute("DMT", DMT);
			request.setAttribute("DMTDBLE", DMTDBLE);
			
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			request.setAttribute("type", type);
			request.setAttribute("mode", mode);
			request.setAttribute("shift", shift);
			request.setAttribute("recall",recall);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MoMeeting.jsp");
			rd.forward(request, response);

		}
		
		
		if (action.equals("MinMeetingRvw_input.mom"))
		{

			String mainForm = request.getParameter("mainForm");
			String momRefDocId = request.getParameter("momRefDocId");
			String momRefDocType = request.getParameter("momRefDocType");
			String type = request.getParameter("type");
			String mode = request.getParameter("mode");
			CommonMessage.debugMsg("Mode 12334567890"+mode);
			if (mode == null || mode.trim().isEmpty()) {
			    mode = "view";
			    CommonMessage.debugMsg("Mode 12334567890"+mode);
			} 
			String stage = request.getParameter("stage");
			
			CommonMessage.debugMsg(" Inside ::  New :: type :: "+type);
			
			if ("true".equals(mainForm)) {
                 request.setAttribute("mainForm", "mainForm");
            }
			
			String DMT = request.getParameter("DMT");
			
			request.setAttribute("DMT", DMT);
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			request.setAttribute("type", type);
			request.setAttribute("mode", mode);
			
			request.setAttribute("stage", stage);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MoMeetingReview.jsp");
			CommonMessage.debugMsg("Mode 12334567890"+mode);
			rd.forward(request, response);			
	    }
		
		else if(action.equals("momattendancemonthwisereportDHQ_input.mom")){
			UIUtils.forwardRequest(request, response, "/pages/MomAttendancemonthwiseReportDhq.jsp");
	    }
		else if(action.equals("momattendancemonthwisereportDHQ_getCol.mom")){
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",true);
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String type =request.getParameter("type");
			String pillarId =request.getParameter("pillarId");
			
			List<String[]> momReportList;
			PrintWriter out = response.getWriter();
			commonFilter.setFlid(Flid);
			 if(Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if(Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			try {
				
				CommonMessage.debugMsg(" Checking for getCol :: "+type);
				
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);
				
				if(UIUtils.isValidKeyId(pillarId))
					   commonFilter.setPillarWise(pillarId);
			commonFilter.setIsGetCol("Y");
			momReportList = moMeetingService.getAttendancemonthwiseDHQ(commonFilter,Flid);
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			String [] colHeader = momReportList.get(1);//changed to 1			
			String [] colHeaderCond = momReportList.get(0);//changed to 0
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
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
	    }
		else if(action.equals("momattendancemonthwisereportDHQ_getData.mom")){
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
			    
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  }
			  
			  String type =request.getParameter("type");
			  String pillarId =request.getParameter("pillarId");
			  
			  if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			  
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);
			
				CommonMessage.debugMsg(" Checking for getData :: "+type);
				commonFilter.setIsGetCol("N");
			List<String[]> MachineGrid = moMeetingService.getAttendancemonthwiseDHQ(commonFilter,Flid);
			PrintWriter out = response.getWriter();//cHANGE FOR SWETHA
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
	}
  }
		
		else if(action.equals("momattendancemonthwisereportDHQ_getExcel.mom")){

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
				Workbook wb = moMeetingService.MomeetingMonthwiseExportExcelDHQ(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingMonthWiseReport", format);

		    }catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}

	    }
		else if (action.equals("MinMeetingRvw_getCol.mom")) 
		{	
			MomeetingReviewGrid_getCol(request, response);
			
			
		}
		else if(action.equals("MinMeetingRvw_getData.mom"))
		{
			         
			try
			{   
				UIUtils.displayRequestParamsValue(request);
				String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");
				String flid=request.getParameter("flid");
				String type=request.getParameter("Type");
		        CommonMessage.debugMsg(" Inside Get Data :: Type :: "+type);

			    CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
			    
			    if(UIUtils.isValidKeyId(type))
					commonFilter.setTaskid(type);

			    
			    commonFilter.setRefdocid(momRefDocId);
			    commonFilter.setType(momRefDocType);
			    commonFilter.setFlid(flid);
			    commonFilter.setIsGetCol("N");
				List<String[]> minOfMeetingList  = moMeetingService.getMomeetingRvw( commonFilter);
  			 	CommonMessage.debugMsg("equipmentQueryList " + minOfMeetingList.size()); 			 	
  			 	JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
				PrintWriter out = response.getWriter();
  			 	out.println(MoReqData);  			 	
  			 	commonFilter.setViewClick('N');  			 	
  			 	httpSession.removeAttribute("MinOfMeetingCommonFilter");
  			 	httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);
               
		    }catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("MinMeetingRvw_getExcel.mom"))
		{
				//CommonMessage.debugMsg("For excel Template");
				//HttpSession httpSession = request.getSession(false);
				String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");			    

				CommonFilter	commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				commonFilter.setRefdocid(momRefDocId);
			    commonFilter.setType(momRefDocType);

			    String type=request.getParameter("Type");
			    
			    if(UIUtils.isValidKeyId(type))
					commonFilter.setTaskid(type);
			    
			    CommonMessage.debugMsg(" Checking Here For type Excel "+type);
			    
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				httpSession = request.getSession(false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomeetingColModel");
				colmodel.put("title"," Minutes Of Meeting Report");
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = moMeetingService.MomeetingRvwExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingReport", format);
		}
		else if (action.equals("mom_getCol.mom")) 
		{	
			MomeetingMainGridgetCol(request, response);
		}
		else if(action.equals("mom_getData.mom"))
		{
			         
			try
			{   
				UIUtils.displayRequestParamsValue(request);
				String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");
				String flid=request.getParameter("flid");
				String type=request.getParameter("Type");
		        CommonMessage.debugMsg(" Inside Get Data :: Type :: "+type);

			    CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
			    
			    if(UIUtils.isValidKeyId(type))
					commonFilter.setTaskid(type);

			    
			    commonFilter.setRefdocid(momRefDocId);
			    commonFilter.setType(momRefDocType);
			    commonFilter.setFlid(flid);
			    commonFilter.setIsGetCol("N");
				List<String[]> minOfMeetingList  = moMeetingService.getMomeeting( commonFilter);
  			 	CommonMessage.debugMsg("equipmentQueryList " + minOfMeetingList.size()); 			 	
  			 	JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
				PrintWriter out = response.getWriter();
  			 	out.println(MoReqData);  			 	
  			 	commonFilter.setViewClick('N');  			 	
  			 	httpSession.removeAttribute("MinOfMeetingCommonFilter");
  			 	httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);

		    }catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("mom_getExcel.mom"))
		{
				//CommonMessage.debugMsg("For excel Template");
				//HttpSession httpSession = request.getSession(false);
				String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");			    

				CommonFilter	commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				commonFilter.setRefdocid(momRefDocId);
			    commonFilter.setType(momRefDocType);

			    String type=request.getParameter("Type");
			    
			    if(UIUtils.isValidKeyId(type))
					commonFilter.setTaskid(type);
			    
			    CommonMessage.debugMsg(" Checking Here For type Excel "+type);
			    
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				httpSession = request.getSession(false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomeetingColModel");
				colmodel.put("title",type+" Minutes Of Meeting Report");
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = moMeetingService.MomeetingExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingReport", format);
		}
		
		else if(action.equals("MoMeetingMom_getCol.mom") ){
			MoMGridgetCol(request, response);	
		}
		
		else if(action.equals("MoMeetingMom_getData.mom") ){   	
			try{
                 UIUtils.displayRequestParamsValue(request);				
		         String KeyId=request.getParameter("keyid");
		         String flid=request.getParameter("flid");
		         String momdate=request.getParameter("momdate");
		         String shift=request.getParameter("shift");
		         String type=request.getParameter("type");
		         String pillarid=request.getParameter("pillarid");
		         
                 CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
                 
                 if(UIUtils.isValidKeyId(flid))
                     commonFilter.setFlid(flid);
                 
                 if(UIUtils.isValidKeyId(type))
                    commonFilter.setType(type);
                 
            
                 
			     if(UIUtils.isValidKeyId(KeyId)||!UIUtils.isValidKeyId(KeyId)) 
			     {
			    	 String date = CommonFunctions.pg_getDate(momdate);
			    	 CommonMessage.debugMsg("Mom date "+momdate);
			    	 CommonMessage.debugMsg("date "+date);
			    	
				     List<String[]> minOfMeetingList  = moMeetingService.getMomGrid(commonFilter,KeyId,date,shift,pillarid);
				     
				     
				     for (String[] row : minOfMeetingList) {
				    	    CommonMessage.debugMsg(Arrays.toString(row));
				    	}
				     PrintWriter out = response.getWriter();
				 	 JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,0,0,commonFilter.getTotalRecordCnt()); 
				 	 out.println(equipmentQueryData); 
			     }
				 	 commonFilter.setViewClick('N');  			 	
				 	 httpSession.removeAttribute("MinOfMeetingCommonFilter");
				 	 httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);
			}catch(Exception e){
				//CommonMessage.debugMsg(e.getMessage());
			}
			
		}
		
		else if(action.equals("MoMeetingAtt_input.mom")){     
			    
    	}
		else if(action.equals("MoMeetingAtt_getCol.mom")){     
			 AttendanceGridgetCol(request,response);   
			 
     	}
		
		else if(action.equals("MoMeetingAtt_getData.mom")){

			try
			{   
				UIUtils.displayRequestParamsValue(request);	
				String KeyId=request.getParameter("mkeyid");
			
				CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				
				//String KeyId=(String) httpSession.getAttribute("masterkeyid");
			
				String flid=request.getParameter("flid");
				
				String Momdate=request.getParameter("momdate");
				String location=request.getParameter("locationId");
			
				String cellId=request.getParameter("cellId");
				String pillarid=request.getParameter("pillarid");
				String pillargroup=request.getParameter("pillargroup");
				CommonMessage.debugMsg("pillargroup:::>>>"+pillargroup);
				String pillargrp=request.getParameter("pillargrp");
				CommonMessage.debugMsg("pillargrp:::>>>"+pillargrp);
				String type=request.getParameter("meetingType");
				String shift=request.getParameter("shift");
				String menutype=request.getParameter("pillarmode");
				String recall=request.getParameter("recall");
				
				//CommonMessage.debugMsg(" Inside getData "+pillarid+" pillargroup getData "+pillargroup+"pillargrp getData "+pillargrp);
				
				httpSession.setAttribute("cellId", cellId);
				httpSession.setAttribute("location", location);
				
				ComboFilter locationId=new  ComboFilter();
				String emply = request.getParameter("emp");
				String dept = request.getParameter("dept");
				String roleid=request.getParameter("roleid");
				String PILLAR=request.getParameter("PILLAR");
				String range=request.getParameter("pillarmode");
				CommonMessage.debugMsg(" PILLAR==== :: "+PILLAR);
				
				 if(UIUtils.isValidKeyId(PILLAR)){
					 commonFilter.setKey(roleid);
					 commonFilter.setActionKeyId(PILLAR);
					 commonFilter.setCellId(cellId);
					 commonFilter.setLocation(locationId);
					 commonFilter.setKK(pillargroup);
					 commonFilter.setRange(range);
					 //CHANGES - EHS MOM 14 JULY
					 if (!UIUtils.isValidKeyId( commonFilter.getKey())) {
						 if(UIUtils.isValidKeyId(pillarid)) {
						    commonFilter.setKey(pillarid);}
						}
					//CHANGES - EHS MOM 14 JULY
				 }/*else if(UIUtils.isValidKeyId(pillargrp)){
					 commonFilter.setKey(pillarid);
					 commonFilter.setActionKeyId(pillargroup);
					 commonFilter.setPillarWise(pillargrp);
				 }*/
				 
				//CommonMessage.debugMsg(" getData :: roleid :: "+roleid+" PILLAR :: "+PILLAR);
				//CommonMessage.debugMsg(" getData :: cellId :: "+cellId+" pillargroup ::"+pillargroup);
				
				 CommonMessage.debugMsg(" meetingtype :: "+type);
				 
				if(UIUtils.isValidKeyId(emply)){
				ComboFilter newComboFilter=new ComboFilter();
				newComboFilter.setId(emply);
			    commonFilter.setEmployee(newComboFilter);
				}
			    if(UIUtils.isValidKeyId(dept)) {
        		ComboFilter newComboFilter1=new ComboFilter();
				newComboFilter1.setId(dept);
			    commonFilter.setDept(newComboFilter1);
			    }
			    
				if(UIUtils.isValidKeyId(KeyId)||!UIUtils.isValidKeyId(KeyId))
			    {
					//CommonMessage.debugMsg(" Inside Servlet Inside If ");
					List<String[]> minOfMeetingList  = moMeetingService.getMomeetingAtt( commonFilter,KeyId,location,flid,Momdate,shift,recall);
	  			 	CommonMessage.debugMsg("minOfMeetingList " + minOfMeetingList.size());
					PrintWriter out = response.getWriter();
					JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(MoReqData);
	  			 	//CommonMessage.debugMsg("out.println(MoReqData);  "+MoReqData);
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("MinOfMeetingCommonFilter");
	  			 	httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);
			     }
		    }catch(Exception e)
			{
				//CommonMessage.debugMsg(e.getMessage());
			}
			
		}
		else if(action.equals("MoMeetingAtt_modify.mom"))
		{
		    PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			List<String []> momattData  = moMeetingService.FillMomAttGridData(keyid);
			out.print( JSONArray.fromCollection(momattData));
		}
		
		else if (action.equals("newmomattendancereport_getCol.mom")) {
			
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",true);
			String Flid =request.getParameter("flid");
			String FromDte =request.getParameter("FromDte");
			String ToDte =request.getParameter("ToDte");
			String Frommonth =request.getParameter("Frommonth");
			String Tomonth =request.getParameter("Tomonth");
			String type =request.getParameter("type");
		
			String meetingtype =request.getParameter("meetingtype");
			
			String flid =request.getParameter("mthwseflid");
			
			List<String[]> momReportList;
			//CommonMessage.debugMsg("wherther the data is enter or not");
			PrintWriter out = response.getWriter();
			commonFilter.setFlid(Flid);
			
			if(UIUtils.isValidKeyId(meetingtype))
			   commonFilter.setMaintMode(meetingtype);
			
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFromMonth(Frommonth);
				commonFilter.setToMonth(Tomonth);
				commonFilter.setFlid(flid);
			}
			
			String PillarId =request.getParameter("PillarId");
			if(UIUtils.isValidKeyId(PillarId))
				   commonFilter.setPillarWise(PillarId);
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: "+commonFilter.getFromDate());
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());
			
			CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());

			 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			
			
			
			try {
			momReportList = moMeetingService.getNewAttendance(commonFilter,Flid);
			
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = momReportList.get(2);			
			String [] colHeaderCond = momReportList.get(1);
			
			//CommonMessage.debugMsg(" Inside colHeader "+colHeader.length);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
			gridColModel.setFormatter("setattendanceReport");
			gridColModel.setFormattorFromCol("10");
			gridColModel.setFormattorToCol(""+(colHeader.length));
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			httpSession.removeAttribute("MomAttReportColmodel");
			httpSession.setAttribute("MomAttReportColmodel", jsonObject);
			jsonObject.put("tableHeight", "74%%");
			jsonObject.put("tableWidth", "106%%");
			CommonMessage.debugMsg("jsonObject ="+jsonObject);
	      	out.println(jsonObject);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
} 
		 else if (action.equals("newmomattendancereport_getData.mom")) {

			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String FromDte =request.getParameter("FromDte");
			String ToDte =request.getParameter("ToDte");
			String flid =request.getParameter("mthwseflid");
			
	        
	try {
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
			
			String type =request.getParameter("type");
			String meetingtype =request.getParameter("meetingtype");
			
			if(UIUtils.isValidKeyId(type))
			   commonFilter.setType(type);
			
			if(UIUtils.isValidKeyId(meetingtype))
			   commonFilter.setMaintMode(meetingtype);
			
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFromMonth(dtFromMonth);
				commonFilter.setToMonth(dtToMonth);
				commonFilter.setFlid(flid);
			}
			
			String PillarId =request.getParameter("PillarId");
			if(UIUtils.isValidKeyId(PillarId))
				   commonFilter.setPillarWise(PillarId);
			
			
			if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
				 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }	 
			  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 
			  
			List<String[]> MachineGrid = moMeetingService.getNewAttendance(commonFilter,Flid);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 3, 0,commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg(" Inside Servlet ACtion ::  "+commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
	}
}
		 else if(action.equals("newmomattendancereport_getExcel.mom")){

            httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();//
			commonFilter.setFromRow(null);//
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomAttReportColmodel");
			colmodel.put("title","New Mom Attendance Report");
            String format = ExcelUtils.getFormat(request);
			String type = request.getParameter("type");
			String meetingtype = request.getParameter("meetingtype");
			String PillarId = request.getParameter("PillarId");
			
			CommonMessage.debugMsg(" type :: "+type+" meetingtype :: "+meetingtype +" PillarId :: "+PillarId);
			
			commonFilter.setType(type);
			commonFilter.setMachineId(PillarId);
			commonFilter.setAbnViewType(meetingtype);
			
			Workbook wb = moMeetingService.getnewmomAttReportExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "NewMomAttendanceReport", format);
			
			 
		 }
		
		else if (action.equals("momattendancereport_getCol.mom")) {
			
					CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",true);
					String Flid =request.getParameter("flid");
					String FromDte =request.getParameter("FromDte");
					String ToDte =request.getParameter("ToDte");
					String Frommonth =request.getParameter("Frommonth");
					String Tomonth =request.getParameter("Tomonth");
					String type =request.getParameter("type");
					String meetingtype =request.getParameter("meetingtype");
					String flid =request.getParameter("mthwseflid");
					
					List<String[]> momReportList;
					//CommonMessage.debugMsg("wherther the data is enter or not");
					PrintWriter out = response.getWriter();
					commonFilter.setFlid(Flid);
					
					if(UIUtils.isValidKeyId(meetingtype))
					   commonFilter.setMaintMode(meetingtype);
					
					if(UIUtils.isValidKeyId(flid)){
						commonFilter.setFromMonth(Frommonth);
						commonFilter.setToMonth(Tomonth);
						commonFilter.setFlid(flid);
					}
					
					String PillarId =request.getParameter("PillarId");
					if(UIUtils.isValidKeyId(PillarId))
						   commonFilter.setPillarWise(PillarId);
					
					CommonMessage.debugMsg(" Inside CommonFunctions :: "+commonFilter.getFromDate());
					
					CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());
					
					CommonMessage.debugMsg(" Inside CommonFunctions :: 11 "+commonFilter.getMonwise());

					 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
						 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
						 commonFilter.setToDate(CommonFunctions.getDate());
					 }	 
					  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  } 
					
					
					
					try {
						commonFilter.setIsGetCol("Y");
					momReportList = moMeetingService.getAttendance(commonFilter,Flid);
					
					for(String[] arr : momReportList) 
					{
						CommonMessage.debugMsg("arrays printng"+Arrays.toString(arr));
					}
					
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = momReportList.get(1);			
					String [] colHeaderCond = momReportList.get(0);
					
					CommonMessage.debugMsg(" Inside colHeader  Cond"+Arrays.toString(colHeaderCond));
					CommonMessage.debugMsg(" Inside colHeader "+Arrays.toString(colHeader));
					
					List<String[]> headers = new ArrayList<String[]>();
					
					headers.add(colHeader);
					gridColModel.setFormatter("setattendanceReport");
					gridColModel.setFormattorFromCol("10");//changed from 11 to 10 for sb 8th jan
					gridColModel.setFormattorToCol(""+(colHeader.length));
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					httpSession.removeAttribute("MomAttReportColmodel");
					httpSession.setAttribute("MomAttReportColmodel", jsonObject);
					jsonObject.put("tableHeight", "74%%");
					jsonObject.put("tableWidth", "106%%");
					CommonMessage.debugMsg("jsonObject ="+jsonObject);
			      	out.println(jsonObject);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					
		} else if (action.equals("momattendancereport_getData.mom")) {
			
					//CommonFilter commonFilter = new CommonFilter();
			//,Flid,FromDte,ToDte,Frommonth,Tomonth
					String Flid =request.getParameter("flid");
					String dtFromDate =request.getParameter("dtFromDate");
					String dtToDate =request.getParameter("dtToDate");
					
					String dtFromMonth =request.getParameter("dtFromMonth");
					String dtToMonth =request.getParameter("dtToMonth");
					String FromDte =request.getParameter("FromDte");
					String ToDte =request.getParameter("ToDte");
					String flid =request.getParameter("mthwseflid");
					
			        
			try {
					CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
					
					String type =request.getParameter("type");
					String meetingtype =request.getParameter("meetingtype");
					
					if(UIUtils.isValidKeyId(type))
					   commonFilter.setType(type);
					
					if(UIUtils.isValidKeyId(meetingtype))
					   commonFilter.setMaintMode(meetingtype);
					
					if(UIUtils.isValidKeyId(flid)){
						commonFilter.setFromMonth(dtFromMonth);
						commonFilter.setToMonth(dtToMonth);
						commonFilter.setFlid(flid);
					}
					
					String PillarId =request.getParameter("PillarId");
					if(UIUtils.isValidKeyId(PillarId))
						   commonFilter.setPillarWise(PillarId);
					
					
					if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
						 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
						 commonFilter.setToDate(CommonFunctions.getDate());
					 }	 
					  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-11).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
				 	  } 
					
					  
					 commonFilter.setIsGetCol("N");
					List<String[]> MachineGrid = moMeetingService.getAttendance(commonFilter,Flid);
					PrintWriter out = response.getWriter();
					JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
					CommonMessage.debugMsg(" Inside Servlet ACtion ::  "+commonFilter.getTotalRecordCnt());
					out.println(machinegrid);
				
			} catch (Exception e) {
				//CommonMessage.debugMsg(e.getMessage());
			}
		
		}
		else if(action.equals("momattendancereport_getExcel.mom")){
			
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"momAttReportCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();//
			commonFilter.setFromRow(null);//
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomAttReportColmodel");
			colmodel.put("title","Mom Attendance Report");
            String format = ExcelUtils.getFormat(request);
			String type = request.getParameter("type");
			String meetingtype = request.getParameter("meetingtype");
			String PillarId = request.getParameter("PillarId");
			
			CommonMessage.debugMsg(" type :: "+type+" meetingtype :: "+meetingtype +" PillarId :: "+PillarId);
			
			commonFilter.setType(type);
			commonFilter.setMachineId(PillarId);
			commonFilter.setAbnViewType(meetingtype);
			
			Workbook wb = moMeetingService.getmomAttReportExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "MomAttendanceReport", format);
			
			
		}
		 
		 
		else if( action.equals("MoMeetingMom_view.mom"))
		{	
			
			try{
			

			String momKeyId = request.getParameter("momKeyId");
			String flid=request.getParameter("flid");
			String glbType=request.getParameter("glbType");
			String format = ExcelUtils.getFormat(request);
			String path = UIUtils.getExcelTemplatePath(request);
			
				
			Workbook wb = moMeetingService.momExcelView(glbType,momKeyId,flid,path);
			format = ".xlsx";
 
			ExcelUtils.writeToResponse(response, wb, "MinutesofMeeting_"+momKeyId, format);
			}
		
			catch(Exception e)
			{
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();				
				err.put("message" ,"Data Not Found" );
				out.print(err.toString());
			}
			
			
		}
		else if(action.equals("visitor_input.mom"))
		{
			String from = request.getParameter("from");
	    	String MasterKeyid =request.getParameter("keyid");
	    	request.setAttribute("from", from);
	    	request.setAttribute("MasterKeyid", MasterKeyid);
	    	UIUtils.forwardRequest(request, response, "/pages/AddVistors.jsp");
		}
		else if(action.equals("MoMeetingPillaridRecall.mom"))
		{
			String date = request.getParameter("date");
			String type = request.getParameter("type");//added
			CommonMessage.debugMsg("date:"+date);
			CommonMessage.debugMsg("date.length:"+date.length());
			
			if(date!=null)
			{
				 if(date.length()==10)
				 {
					 date="0"+date;
				 }
			}
	    	String flid =request.getParameter("flid");
	    	String pillarid=request.getParameter("shift");
	    	CommonFilter commonfilter=new CommonFilter();
	    	commonfilter.setActwise(date);
	    	commonfilter.setFlid(flid);
	    	commonfilter.setAbnDetect(pillarid);
	    	commonfilter.setType(type);
	    	PrintWriter out = response.getWriter();
	    	String momkeyid  = moMeetingService.PillarSelectedData(commonfilter);
	    	 JSONObject json=new JSONObject();
				//json.put("empcnt", Integer.parseInt(empcount));
				//json.put("chkuniq", chkuniq);
				json.put("momkeyid", momkeyid);
				json.put("pillarid", pillarid);
			
				
			
				out.println(json);
	    	
		}
		
		
		else if(action.equals("SelectVisitor_select.mom"))
		{
			String Visitorkey =request.getParameter("VisitorKey");
			if ((Visitorkey != null)) {
				GenTlVisitors newGenTlVisitors = moMeetingService.selectVisitor(Visitorkey);
				CommonMessage.debugMsg("vistor name:"+newGenTlVisitors.getVisiVisitorname());
				//GenTlMomdtl genTlMomdtl = moMeetingService.selectdtl(keyid);
				//GenTlMomattendance genTlMomattendance = moMeetingService.selectatt(keyid);
				request.setAttribute("visitor", newGenTlVisitors);
			}
			
		}
		else if(action.equals("visitor_getCol.mom"))
		{
			try{
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("test input action meeting form.............");	
		    	String from = request.getParameter("from");
		    	String frmProgrm = request.getParameter("frmProgrm");
				 
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","VistorsAdd");
				JSONObject jsonObject =  JSONObject.fromString(colModel);
				
		    	if("ENT".equals(from)){
		    		jsonObject.getJSONArray("rowHeaders").getJSONArray(0).put(1, "External Resource");
		    		jsonObject.getJSONArray("colModel").getJSONObject(2).set("width","100");
		    	}
		    	if("true".equals(frmProgrm)){
		    		jsonObject.set("tableHeight", "30%%");
		    		jsonObject.set("tableWidth", "30%%");
		    	}
		    	out.println(jsonObject);
				CommonMessage.debugMsg("colModel  "+UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","VistorsAdd")); 
				}catch(Exception e){
					//CommonMessage.debugMsg(e.getMessage());
				}
		}
		else if(action.equals("visitor_getData.mom"))
		{
			try{
                UIUtils.displayRequestParamsValue(request);				
		         String MasterKeyid =request.getParameter("keyid");
		         String shift =request.getParameter("shift");
		         String date =request.getParameter("date");
		         String flid =request.getParameter("flid");
		         String type =request.getParameter("type");
		         String pillarid =request.getParameter("pillarid");
		         String recall =request.getParameter("recall");
		         
                CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
			   //  if(UIUtils.isValidKeyId(KeyId)) 
			   //  {
			    	 //CommonMessage.debugMsg("key id call came ");
				     List<String[]> minOfMeetingList  = moMeetingService.getAttVistor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
				     CommonMessage.debugMsg("minOfMeetingList "+minOfMeetingList.size()); 
				     PrintWriter out = response.getWriter();
				 	 JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,0,0,commonFilter.getTotalRecordCnt()); 
				 	 out.println(equipmentQueryData); 
			    // }
				 	 commonFilter.setViewClick('N');  			 	
				 	 httpSession.removeAttribute("MinOfMeetingCommonFilter");
				 	 httpSession.setAttribute("MinOfMeetingCommonFilter", commonFilter);
			}catch(Exception e){
				//CommonMessage.debugMsg(e.getMessage());
			}
			
		}
		else if( action.equals("MoMeetingAtt_getExcel.mom"))
		{
				//CommonMessage.debugMsg("For excel Template");
				CommonFilter	commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);//
				httpSession = request.getSession(false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("AttendanceColModel");
				colmodel.put("title","Attendance Report");
	            String format = ExcelUtils.getFormat(request);
				Workbook wb = moMeetingService.MomeetingAttExportExcel(commonFilter,colmodel,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response,wb,"MeetingAttendanceReport", format);
		}
		
		else if (action.equals("MoMeetingForm_save.mom"))
		{
		  try {
				MOMeetingBean momeetingBean = new MOMeetingBean();
				saveMOMeeting(request, response, momeetingBean);
				//saveMomAttence(request, response);
   			} catch (Exception e) {

			}
		}
		else if (action.equals("MoMeetingForm_update.mom"))
		{
		  try {
			  GenTlMommst newGenTlMommst = new GenTlMommst();
				String dtlKeyid=request.getParameter("dtlKeyid");
				String aplKeyid=request.getParameter("aplKeyid");
				newGenTlMommst=moMeetingService.updateApl(dtlKeyid,aplKeyid);
				//saveMomAttence(request, response);
   			} catch (Exception e) {

			}
		}
		 
		 
		else if (action.equals("MoMeetingFillRole_modify.mom"))
		{
		  try {
			    PrintWriter out = response.getWriter();
				String keyid = request.getParameter("emplyid");
				CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
				List<String []> condReclData  = moMeetingService.FillRoleDatainGrid(keyid);
				out.print( JSONArray.fromCollection(condReclData));
   			} catch (Exception e) {

			}
		}
		 
		else if (action.equals("MoMeetingRecalling_input.mom"))
		{
		  try {
			    PrintWriter out = response.getWriter();
			    String shift = request.getParameter("shift");
			    String mstDate = request.getParameter("Date");
			    String flid = request.getParameter("flid");
			    String type = request.getParameter("type");
			    String pillarid = request.getParameter("pillarid");
			    List<String[]> minOfMeetingRecallList  = moMeetingService.selectRecalling(shift,mstDate,flid,type,pillarid);
			    CommonMessage.debugMsg("Printing to check the value");
			    for (String[] row : minOfMeetingRecallList) {
			        CommonMessage.debugMsg(Arrays.toString(row));
			    }
				//cmt removed by swetha
			    //CommonMessage.debugMsg(" Recalling Data :: size "+minOfMeetingRecallList.size());
				//CommonMessage.debugMsg(" Recalling Data :: size "+minOfMeetingRecallList.get(0).toString());
				/*if(minOfMeetingRecallList.size()>0){
					request.setAttribute("Mno",minOfMeetingRecallList.get(0)[0]);
					request.setAttribute("Ismthpn",minOfMeetingRecallList.get(0)[1]);
					request.setAttribute("sfty",minOfMeetingRecallList.get(0)[2]);
					request.setAttribute("rmrk",minOfMeetingRecallList.get(0)[3]);
					request.setAttribute("title",minOfMeetingRecallList.get(0)[4]);
					request.setAttribute("mtype",minOfMeetingRecallList.get(0)[5]);
					request.setAttribute("agnda",minOfMeetingRecallList.get(0)[6]);
					request.setAttribute("pillarid",minOfMeetingRecallList.get(0)[7]);
				}*/

				out.print( JSONArray.fromCollection(minOfMeetingRecallList));
   			} catch (Exception e) {

			}
		}
		 
		 
		/*else if (action.equals("MoMeetingForm_save.mom")) 
		{
			try {
				
				// saveMOMeeting(request, response,null);
				saveMomAttence(request, response);
			    
			    }
			catch (Exception e) {
				
			}
			    
		}*/
		 
		else if (action.equals("MoMeetingAgendafill_modify.mom")) 
        {
			try {
				    PrintWriter out = response.getWriter(); 
					String flid=request.getParameter("flid");
					String momdate=request.getParameter("momdate");
					List<String []> agendafillData  = moMeetingService.fillagendadata(flid,momdate);
					out.print( JSONArray.fromCollection(agendafillData));

	   			} catch (Exception e) {

				}
		}

		else if (action.equals("MoMVisitorATT_save.mom")) 
		{
			try {
				// saveMOMeeting(request, response,null);
				saveVisitors(request, response);
			    } catch (Exception e) { 
			    }
		}
	  else if (action.equals("MoMeetingForm_delete.mom"))
		{
			DeleteMOM(request, response);
		}
       else if (action.equals("MoMeeting_remove.mom")) 
        {
			DeleteMomRow(request, response);
		}
       else if (action.equals("AttendancesVisitor_remove.mom"))
       {
    	   DeleteATTVisitorRow(request, response);
	   }

	 else if(action.equals("MeetingType.mom"))
		{
			   PrintWriter out = response.getWriter();
			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MeetingType", "MeetingType"));	   
		}
		 
	 else if(action.equals("MeetingAttType.mom"))
		{
		       String type=request.getParameter("type");
		       String pillarcmb="";
			   PrintWriter out = response.getWriter();
			   if(UIUtils.isValidKeyId(type))
				   pillarcmb="MeetingMonthAttwise";
			   else
				   pillarcmb="MeetingAttType";
			   
			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MeetingType", pillarcmb));
		}
	
	 else if(action.equals("MeetingAttendance.mom"))
		{
			   PrintWriter out = response.getWriter();
			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MeetingType", "MeetingAttendance"));	   
		}
	 else if(action.equals("Rolecombo.mom"))
		{				
		 try{
				String flid=request.getParameter("flid");
				CommonMessage.debugMsg(" flid :: "+flid);
				ComboFilter roleComboComboFilter=UIUtils.fillComboFilter(request);				
				List<ComboBox> probfilcomboList = moMeetingService.getRoleComboComboList(roleComboComboFilter,flid,"");
				UIUtils.writeComboBox(response, probfilcomboList ,roleComboComboFilter);			
			}
			catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		 
	
		 
		 
	 else if(action.equals("rolebasedemployee.mom"))
	 {				
	    try {
	         String flid = request.getParameter("flid");
	         CommonMessage.debugMsg(" flid :: " + flid);
	         String roleId = request.getParameter("roleId");
	         String tradeid = request.getParameter("tradeid");
	         String Others = request.getParameter("Others");
	         String locnid  = request.getParameter("locnId");
	         
	         StringBuffer cond = new StringBuffer();
	         
	         ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

	         cond.append(" AND EMPM_KEYID IN ( SELECT DISTINCT e.empm_keyid FROM gen_tl_employeemst e JOIN gen_tl_fnlnroleteam frt ON frt.frt_empm_keyid = e.empm_keyid JOIN gen_mv_flidhierarchy h ON h.flid = frt.frt_fnln_keyid WHERE 1=1 ");

	         if (UIUtils.isValidKeyId(Others)) {
	             cond.append(" AND POSITION((SELECT fnln_keyid FROM gen_tl_functionallocn WHERE fnln_originalid='" + locnid + "') IN (h.parentflids || '/' || h.flid)) > 0 ");
	         }

	         if (UIUtils.isValidKeyId(flid)) {
	             cond.append(" AND frt.frt_fnln_keyid IN ( SELECT flid FROM gen_mv_flidhierarchy WHERE POSITION('" + flid + "' IN (parentflids || '-' || flid)) > 0 ) ");
	         }

	         if (UIUtils.isValidKeyId(roleId)) {
	             cond.append(" AND frt_role_keyid = '" + roleId + "' ");
	         }

	         cond.append(" ) "); 
	      
	         String loginLocnId = CommonFunctions.getLoginLocaton(request);
	         if (!UIUtils.isValidKeyId(locnid))
	             locnid = loginLocnId;

	         CommonMessage.debugMsg("locnid====" + locnid);

	         if (UIUtils.isValidKeyId(locnid)) {
	             cond.append(" AND empm_location = '" + locnid + "' AND empm_active = 'Y' ");
	         }

	         empComboFilter.setCondSql(cond.toString());
	         
	         List<ComboBox> probfilcomboList = moMeetingService.getrolebasedemployee(empComboFilter,flid,"");
	         UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
	         
	    } catch (Exception e) {				
	         e.printStackTrace();
	    }	
	 }

		 
		 
		 
		 
	 else if( action.equals("functionalLoc.mom"))
		{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				String type= "";
				type  = request.getParameter("Type");
				CommonMessage.debugMsg("TYPE "+type);
				functLocFieldNameBean.setFactory("cmbMomdFactoryid");
				functLocFieldNameBean.setSection("cmbMomdSectionid");
				functLocFieldNameBean.setCell("cmbMomdCellid");
				functLocFieldNameBean.setMachine("cmbMomdMachineid");
				functLocFieldNameBean.setFunctionalLocId("cmbMomdFlid");
				functLocFieldNameBean.setSbu("cmbMomdSbu");
				functLocFieldNameBean.setPbu("cmbMomdPbu");
				

			
				
				functLocFieldNameBean.setLocnMandatory(true);
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);

				if (UIUtils.isValidKeyId(type)) {
					
					if(  "JH".equals(type.replaceAll(" ","").toUpperCase()))
					{
						functLocFieldNameBean.setCellMandatory(true);//JH-Mandotory
						functLocFieldNameBean.setMachDisable(true);//Disable Equipment
					}
					else if("DMT".equals(type.replaceAll(" ","").toUpperCase()))
					{
						functLocFieldNameBean.setSectMandatory(true); //DMT - Mandatory
						functLocFieldNameBean.setMachDisable(true);//Disable Equipment
						functLocFieldNameBean.setCellDisable(true);//Disable JH
						
					}
//					else if("PILLAR".equals(type.replaceAll(" ","").toUpperCase()) ||
//							"Pillar".equals(type)) {
//						
//						functLocFieldNameBean.setCompDisable(true);
//						functLocFieldNameBean.setLconDisable(true);
//						
//						functLocFieldNameBean.setPbuDisable(true);//Disable PBU
//						functLocFieldNameBean.setSectDisable(true);//Disable DMT
//						functLocFieldNameBean.setMachDisable(true);//Disable Equipment
//						functLocFieldNameBean.setCellDisable(true);//Disable JH
//					}
					else if("PILLAR".equals(type.replaceAll(" ","").toUpperCase()) ||
							"Pillar".equals(type) || "CEC".equals(type.replaceAll(" ","").toUpperCase()) 
							|| "DEC".equals(type.replaceAll(" ","").toUpperCase()))//Added this line Mom - change-08-May 
						{
						
						functLocFieldNameBean.setCompDisable(true);
						functLocFieldNameBean.setLconDisable(true);
						
						
						functLocFieldNameBean.setLocnMandatory(false);//Added this line Mom - change-08-May
						functLocFieldNameBean.setSbuMandatory(true);//Added this line Mom - change-08-May
						functLocFieldNameBean.setPbuDisable(true);//Disable PBU
						functLocFieldNameBean.setSectDisable(true);//Disable DMT
						functLocFieldNameBean.setMachDisable(true);//Disable Equipment
						functLocFieldNameBean.setCellDisable(true);//Disable JH
					}
					
					if ("PRODUCTION".equals(type.replaceAll(" ", "").toUpperCase()) ||
						    "OGM".equals(type.replaceAll(" ", "").toUpperCase()) ||
						    "UMC".equals(type.replaceAll(" ", "").toUpperCase())) {
						
						functLocFieldNameBean.setSbuDisable(true);//Disable PBU
						functLocFieldNameBean.setPbuDisable(true);//Disable PBU
						functLocFieldNameBean.setSectDisable(true);//Disable DMT
						functLocFieldNameBean.setMachDisable(true);//Disable Equipment
						functLocFieldNameBean.setCellDisable(true);//Disable JH
						    
						}
				}
				
				//CommonMessage.debugMsg("functionlocation null ");
                FormModes formModes = FormModes.create;
                UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		 }
	 	else if( action.equals("MoMeetingMom_sendMail.mom"))
		{	
	 		sendMomMail( request, response);
	 		
		}
	 	else if( action.equals("MoMeetingMom_isMail.mom"))
		{	
	 		
	 		PrintWriter out=response.getWriter();
	 		String mailid=request.getParameter("momKeyId");
	 		JSONObject jsonObject=new JSONObject();
	 	String ismailid=moMeetingService.getmailidTrigger(mailid);
	 	String ismail="";
	 	String val="N";
	 	if(ismailid.length()!=0||ismailid!=null)
	 	  {
	 		jsonObject.put("ismail",ismailid);
	 	  }
	 	
	 	out.println(jsonObject);	
		}
		
		
		
	   }
	

	private void AttendanceGridgetCol(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		 
            HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			try{
				CommonMessage.debugMsg("AttendanceGridgetCol");
				CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",true);
				//String KeyId=request.getParameter("keyid");
				String emply = request.getParameter("emp");
				String dept = request.getParameter("dept");
				
			
			 if(UIUtils.isValidKeyId(emply))
			 {
				ComboFilter newComboFilter= new ComboFilter();
				newComboFilter.setId(emply);
				commonFilter.setEmployee(newComboFilter);
			 }
			 if(UIUtils.isValidKeyId(dept))
			 {			 
			    ComboFilter newComboFilter1= new ComboFilter();
				newComboFilter1.setId(dept);
				commonFilter.setDept(newComboFilter1);		
			 }
			 CommonMessage.debugMsg("AttendanceGridgetCol before list");
			 String flid=request.getParameter("flid");
			 String KeyId=request.getParameter("mkeyid");
            
			 String roleid=request.getParameter("roleid");
			 String PILLAR=request.getParameter("PILLAR");
			 String location=request.getParameter("locationId");
		
			 String cellId=request.getParameter("cellId");
			 
			 String pillarid=request.getParameter("pillarid");
			 String pillargroup=request.getParameter("pillargroup");
			 String pillargrp=request.getParameter("pillargrp");
			 
			 String type=request.getParameter("meetingType");
			 String shift=request.getParameter("shift");
			 String recall=request.getParameter("recall");
			// String range=request.getParameter("pillarmode");
			 CommonMessage.debugMsg(" :: PILLAR=====:: "+PILLAR);
			 CommonMessage.debugMsg(" :: meetingtype  GETCOL:: "+type);
			 
			 CommonMessage.debugMsg(" Inside getCol "+pillarid+" pillargroup getCol "+pillargroup+"pillargrp getData "+pillargrp);
			 ComboFilter locationId=new  ComboFilter();
			 if(UIUtils.isValidKeyId(PILLAR)){
				 commonFilter.setKey(roleid);
				 commonFilter.setActionKeyId(PILLAR);
				 commonFilter.setLocation(locationId);
				 commonFilter.setCellId(cellId);
				 commonFilter.setKK(pillargroup);
				// commonFilter.setRange(range);
			 }/*else if(UIUtils.isValidKeyId(pillargrp)){
				 commonFilter.setKey(pillarid);
				 commonFilter.setActionKeyId(pillargroup);
				 commonFilter.setPillarWise(pillargrp);
			 }*/
			 httpSession.setAttribute("cellId", cellId);
				httpSession.setAttribute("location", location);
				
			 CommonMessage.debugMsg(" getData :: roleid :: "+roleid+" PILLAR :: "+PILLAR);
			 CommonMessage.debugMsg(" getData :: cellId :: "+cellId+" pillargroup ::"+pillargroup);
			
			 //String KeyId=request.getParameter("keyid");
             ////CommonMessage.debugMsg(" flid In getCol :: "+flid+" AttgetCol "+momdate);
			 String Momdate=request.getParameter("momdate");
			 CommonMessage.debugMsg("DEBUG: Momdate value = '" + Momdate + "'");
			 CommonMessage.debugMsg("DEBUG: Momdate is null? " + (Momdate == null));
			 CommonMessage.debugMsg("DEBUG: Momdate is empty? " + (Momdate != null && Momdate.trim().isEmpty()));
			 if(Momdate == null || Momdate.trim().isEmpty()){
				 Momdate = CommonFunctions.getDate();
				}
        	 List<String[]> MoReqList  = moMeetingService.getMomeetingAtt(commonFilter,KeyId,location,flid,Momdate,shift,recall);  
        	 
             JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);

			 gridColModel.setHeaderNum(1);//9	
			 gridColModel.setFormatter("chkMomAttformatter");
			 gridColModel.setFormattorFromCol("0");
			 gridColModel.setFormattorToCol("0");
			 String[] formatterval  = {"cmbMomaAttandance#8"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 
			 String [] colHeader = MoReqList.get(1);
			 
			 String [] colHeaderHead = MoReqList.get(0);
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			 /*
			 for(int i=0;i<colHeader.length;i++){
				 //CommonMessage.debugMsg(colHeader[colHeader.length-2]+" Inside SErvlet "+colHeader[i]);
			 }*/
			 JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
			 jsonObject.set("tableWidth", "90%%");
			 jsonObject.set("tableHeight", "58%%");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("index","dataOrder");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("name","dataOrder");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
	   	     httpSession.removeAttribute("AttendanceColModel");
			 httpSession.setAttribute("AttendanceColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
			 
		     /*//JSONObject  MoReqData = UIUtils.convertToJqGridTableObject(MoReqList,request,1,0,commonFilter.getTotalRecordCnt());
			 //CommonMessage.debugMsg("MoReqData   "+MoReqData.length());
			 //httpSession.setAttribute("MomeetingServlet", MoReqData);
			JSONObject jsonObject = getTableModel1(MoReqList,request);
			//String[] formatterval  = {"cmbMomaAttandance#8"};
			//jsonObject.put("formatterIndex",formatterval);
			//out.print(jsonObject);
			httpSession.removeAttribute("AttendanceColModel");
	 	    httpSession.setAttribute("AttendanceColModel",jsonObject);
			out.println(jsonObject);	*/
			 
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}
	
	private void MoMGridgetCol(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			PrintWriter out = response.getWriter();
			//JSONObject json = JSONObject.fromString();
			//String[] formatterval  = {"cmbMomdDiscussionType#4","cmbMomdPillar#5","txtMomdDiscussionDetails#6","btnmomkpi#9","btnmomactionplan#11","txtMomdRemarks#12"};
			//json.put("formatterIndex",formatterval);
			
			String type = request.getParameter("type");
			String mode = request.getParameter("mode");
			 
			CommonMessage.debugMsg(" type :: Checking Jun 13 :: "+type);
			
			if (type.equals("Production") || type.equals("Others"))
				  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","MinOfMeetingDtlProducttion"));
			else if ((type.equals("JH") || type.equals("Dmt")|| type.equals("Pillar")||type.equals("FIP")||type.equals("UMC")||type.equals("OGM")||type.equals("CEC")||type.equals("DEC"))&& mode== null){
            	  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","MinOfMeetingDtl"));
			}else if(mode.equals("view") && mode !=null)
				  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","MinOfMeetingDtlView"));
			
			}catch(Exception e){
				//CommonMessage.debugMsg(e.getMessage());
			}
	}
	
	private void MomeetingMainGridgetCol(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		String momRefDocId = request.getParameter("momRefDocId");
		String momRefDocType = request.getParameter("momRefDocType");
		String mode = request.getParameter("mode");
		if (mode.equals("view")) 
		{
			mode = "view";
		}
		else 
		{
			mode = "modify";
		}
			
        String flid=request.getParameter("flid");
        String type=request.getParameter("Type");
        CommonMessage.debugMsg(" Inside Get Col ::flid :: "+type);
		
		CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter", true);
		commonFilter.setWostatus("1");
		commonFilter.setRefdocid(momRefDocId);
		commonFilter.setType(momRefDocType);

		String search = request.getParameter("_search");
		try{
			

		    if(UIUtils.isValidKeyId(type))
				commonFilter.setTaskid(type);
		 
		commonFilter.setIsGetCol("Y");
		commonFilter.setMainGroup(mode);
		
		List<String[]> moReqList = moMeetingService.getMomeeting(commonFilter);
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();			
		
		jqGridTableModel.setSortable(true);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);
		gridColModel.setHeaderNum(1);//9
		gridColModel.setFormattorFromCol("0");
		gridColModel.setFormattorToCol("0");
		
		String [] colHeader = moReqList.get(1);
		String [] colHeaderHead = moReqList.get(0);
		 
		List<String[]> headers = new ArrayList<String[]>();	
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
		CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
		jsonObject.set("tableWidth", "106%%");
    	jsonObject.set("tableHeight", "82%%");
    	httpSession.removeAttribute("MomeetingColModel");
		httpSession.setAttribute("MomeetingColModel", jsonObject);
		out.println(jsonObject);
		/*
		 *CommonMessage.debugMsg("1233" + moReqList.size());
		JSONObject MoReqData = UIUtils.convertToJqGridTableObject(moReqList,request, 2, 0, commonFilter.getTotalRecordCnt());
		CommonMessage.debugMsg("MoReqData   " + MoReqData.length());
		httpSession.setAttribute("MomeetingServlet", MoReqData);
        JSONObject jsonObject = getTableModel(moReqList, request);//
		httpSession.removeAttribute("MomeetingColModel");
		httpSession.setAttribute("MomeetingColModel", jsonObject);
		out.println(jsonObject);	
		
			try{
				
			 List<String[]> MoReqList  = moMeetingService.getMomeetingAtt(commonFilter,KeyId,flid);        	
			 
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);
			 gridColModel.setHeaderNum(1);//9	
			 gridColModel.setFormatter("chkMomAttformatter");
			 gridColModel.setFormattorFromCol("0");
			 gridColModel.setFormattorToCol("0");
			 String[] formatterval  = {"cmbMomaAttandance#10"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 
			 
			 String [] colHeader = MoReqList.get(1);
			 String [] colHeaderHead = MoReqList.get(0);
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
			 
			 JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
			 jsonObject.set("tableWidth", "100%%");
	     	 jsonObject.set("tableHeight", "52%%");
	     	 httpSession.removeAttribute("AttendanceColModel");
			 httpSession.setAttribute("AttendanceColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		 * 
		 */
		}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
	private void MomeetingReviewGrid_getCol(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
       
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		 
		String momRefDocId = request.getParameter("momRefDocId");
		String momRefDocType = request.getParameter("momRefDocType");
		  
        String flid=request.getParameter("flid");
        String type=request.getParameter("Type");
       
        CommonMessage.debugMsg(" Inside Get Col ::flid :: "+type);
		
		CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter", true);
		commonFilter.setWostatus("1");
		commonFilter.setRefdocid(momRefDocId);
		commonFilter.setType(momRefDocType);

		String search = request.getParameter("_search");
		try{
			

		    if(UIUtils.isValidKeyId(type))
				commonFilter.setTaskid(type);
			commonFilter.setIsGetCol("Y");
		List<String[]> moReqList = moMeetingService.getMomeetingRvw(commonFilter);
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();			
		jqGridTableModel.setPaginate(true);
		jqGridTableModel.setSortable(true);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setRowNumbers(true);
		gridColModel.setHeaderNum(1);//9
		gridColModel.setFormattorFromCol("0");
		gridColModel.setFormattorToCol("0");
		
		String [] colHeader = moReqList.get(1);
		String [] colHeaderHead = moReqList.get(0);
		 
		List<String[]> headers = new ArrayList<String[]>();	
		headers.add(colHeader);
		
		JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
		CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
		jsonObject.set("tableWidth", "106%%");
    	jsonObject.set("tableHeight", "82%%");
    	httpSession.removeAttribute("MomeetingColModel");
		httpSession.setAttribute("MomeetingColModel", jsonObject);
		out.println(jsonObject);

		}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
	
	private void DeleteATTVisitorRow(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				moMeetingService.DeleteATTVisitorRow(keyid);
				String msgPropertyIdnt = "success-delete";
				//CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonMessage.debugMsg("Delete End");
		}
       catch (Exception e) 
       {
			CommonMessage.debugMsg("Exception: " + e);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete");
			err.put("successData", mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
		
	}
	
	private void DeleteMomRow(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				moMeetingService.DeleteMomRow(keyid);
				String msgPropertyIdnt = "success-delete";
				//CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonMessage.debugMsg("Delete End");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception: "+e);
			JSONObject err = new JSONObject();
			String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
	}
		
	
	private void saveMomAttence(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{     
		
			HttpSession httpSession = request.getSession(false);
		    ServletOutputStream out = response.getOutputStream();
		    AdmTlUsermst user = UIUtils.getLoginUser(request);
		    //String imagePath = UIUtils.getImagePath(request);

        try
           {
	         if( httpSession != null && user != null)
	           {	
			
			    GenTlMommst existGenTlMommst=(GenTlMommst)httpSession.getAttribute("GenTlMOmmst");
			    GenTlMommst newGenTlMommst = new GenTlMommst();
				GenTlMomattendance newMomattendance=new GenTlMomattendance();
				newGenTlMommst.setMomsCreatedby(user.getUsrm_ccno());
				newMomattendance.setMomaCreatedby(user.getUsrm_ccno());
			    String MomAtt = request.getParameter("MomAtt");
			    newGenTlMommst =(GenTlMommst)UIUtils.setBeanProperties((Object)newGenTlMommst,request);
			
            if( newMomattendance != null)
 			     newGenTlMommst.getMomeetinMomattendances().add(newMomattendance);
        
/**save JsonARR conversion**/
           	
             //CommonMessage.debugMsg("If condition Insert  ATTT:::" );  
		  	 List<GenTlMomattendance> MomGridList1 = null;
      		 JSONArray MOmGridjson1 = null;
      		 CommonMessage.debugMsg("MomAtt::::"+MomAtt);
  		      if(UIUtils.isValidKeyId(MomAtt))
  		      {
		    		//CommonMessage.debugMsg(" mulitMethodStr " + mulitMethodStr);
				    CommonMessage.debugMsg(" momDetailstr " + MomAtt);
					MOmGridjson1 = JSONArray.fromString(MomAtt);
					MomGridList1=(List<GenTlMomattendance>)UIUtils.convertJSONArrToList(newMomattendance, MOmGridjson1);
					 if(MomGridList1!= null)
				     {
				    	newGenTlMommst.setMomeettingAttence(MomGridList1);
				    	CommonMessage.debugMsg(" Attendance " + newGenTlMommst.getMomeetinMomattendances().get(0).getMomaDate());
				    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaAttandance());
				    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaEmployeeid());
				    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaMomsKeyid());
				    		//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
                  }	
              }
		
/**conversion END**/	

				boolean insert = true;
				
				String saveMsg = null ;
				
				//CommonMessage.debugMsg("  newGenTlMommst.getMomsKeyId() " +  newGenTlMommst.getMomsKeyid());
				if( newGenTlMommst.getMomsKeyid() == null )
				  {							
					//CommonMessage.debugMsg("KeyId is Null" );
					existGenTlMommst =	moMeetingService.createatt(newGenTlMommst,existGenTlMommst);
					saveMsg = "Data Saved Successfully";
				  }	
				else
				  {
					//CommonMessage.debugMsg("Update function");						
					existGenTlMommst = moMeetingService.updateatt(newGenTlMommst,existGenTlMommst);
					insert = false;
					saveMsg = "Data Updated Successfully";
				  }					
					//CommonMessage.debugMsg("After the IF Loop");
					//CommonMessage.debugMsg("Ooooold KEyId"+existGenTlMommst.getMomsKeyid());
					//httpSession.setAttribute(existGenTlMommst.getMomsKeyid(),existGenTlMommst);
				    //CommonMessage.debugMsg("http sesiion 1");
					httpSession.setAttribute("GenTlMOmmst", existGenTlMommst);
					//CommonMessage.debugMsg(" DATA Sucessfuly saved ");
					JSONObject successData = new JSONObject();
					String msgPropertyIdnt;
				
                  if(insert)
				   {
					msgPropertyIdnt = "success-save";
				   }
				 else
					msgPropertyIdnt = "success-update";
			 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
					successData.put("MomMstkeyid", existGenTlMommst.getMomsKeyid());
					JSONObject returnData = new JSONObject();				
					returnData.put("successData", successData);	
					returnData.put("masterId", existGenTlMommst.getMomsKeyid());
					returnData.put("formClear",true);
					out.print(returnData.toString());
			
	     }
								
	}   
          catch (ValidationExceptions e) 
         {
	        e.printStackTrace();
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "Momeeting");
			out.print(errMessage.toString());
			    	
         }
											
		  catch(BusinessApplicationExceptions e)
          {
	        e.printStackTrace();
			//CommonMessage.debugMsg("Error Servler e -"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Momeeting");
			out.print(errMessage.toString());
			//CommonMessage.debugMsg(" e " + errMessage );
	      }
		  catch(Exception e)
          {   
	         e.printStackTrace();
	         //CommonMessage.debugMsg("Error Msg:" + e.getMessage());
	         JSONObject err = new JSONObject();
	         err.put("tpmException", "Data Not Saved");
	         out.print(err.toString());
          }
         			
 }
             	
	/**   Table Model ATTENDANCE  **/
	
	private JSONObject getTableModel1(List<String[]> headers,HttpServletRequest request) 
	{
		
			CommonMessage.debugMsg("getTableModel");
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			//PrintWriter out = response.getWriter();
			//GridColModel newGridColModel new GridColModel();
			
			String [] colHeader = headers.get(0);
			//CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
			//CommonMessage.debugMsg("headers Completed");
			
		
			jqGridTableModel.getRowHeaders().add(headers.get(1));
		
			jqGridTableModel.setTableButton(true);		
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(100);
			jqGridTableModel.setTableWidth(1000);
			jqGridTableModel.setPaginate(true);
			//jqGridTableModel.setSortable(false);
			jqGridTableModel.setEnableFilter(true);
			
			//String[] formatterval  = {"cmbMomdDiscussionType#4","cmbMomdPillar#5","txtMomdDiscussionDetails#6","btnmomkpi#9","btnmomactionplan#11","txtMomdRemarks#12"};

		for(int i =0; i < colHeader.length; i++)
		{			
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setWidth( 250);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0)                                       
			{
				jqGridColModel.setHidden(false);	
				jqGridColModel.setFormatter("chkMomAttformatter");
				jqGridColModel.setAlign("center");
				jqGridColModel.setWidth(60);	
				//jqGridTableModel.setSortable(false);
			}
			if(i==1)                                       
			{
				jqGridColModel.setHidden(false);	
				jqGridColModel.setWidth(75); 
				//jqGridTableModel.setSortable(false);
			}
			
			if(i==2)
			{
				jqGridColModel.setHidden(false);	
				jqGridColModel.setWidth(100); 
				//jqGridColModel.setFormatter("chkLoadFormatter");
				//jqGridColModel.setFormattorFromCol("2");
				//jqGridColModel.setFormattorToCol("2");
				//jqGridColModel.setFormattorFromCol("2");
				//jqGridColModel.setFormattorToCol("2");
				
				//jqGridTableModel.setSortable(false);
			}
		    if(i==3)
		    {
		    	jqGridColModel.setWidth(150); 
		    	jqGridColModel.setHidden(false);
		    	//jqGridColModel.setFormatter("chkLoadFormatter");
		    	//jqGridTableModel.setSortable(false);
		    		
		    }
		    if(i==4)
		    {
		    	jqGridColModel.setWidth(220);
		    	//jqGridTableModel.setSortable(false);
		    		
		    }
		    if(i==5)
		    { 	
		        //jqGridColModel.setHidden(false);
	    	    jqGridColModel.setWidth(200);
	    	   // jqGridColModel.setFormatter("txtMomAttformatter");
	    	    //jqGridTableModel.setSortable(false);
		    	
		    }
		    if(i==6)
		    { 
		    	    jqGridColModel.setHidden(false);
		    	    jqGridColModel.setWidth(100);
		    	  
		    	  	
		    }
		    if(i>=7)
		    {
		    	    jqGridColModel.setHidden(false);
		    	    jqGridColModel.setWidth(100);
		    	    jqGridColModel.setFormatter("txtMomAttformatter");	
		    	    jqGridTableModel.setSortable(false);
		    }
		   /* if(i==8)
		    {
		    	  jqGridColModel.setHidden(false);
		    	  jqGridColModel.setWidth(80);
		    	  jqGridColModel.setFormatter("txtMomAttDeleteformatter");	
		    	  jqGridColModel.setAlign("center");
		    }*/
		    if(i==(colHeader.length-1))
		    {
		    	 jqGridColModel.setHidden(true);
		    	// jqGridTableModel.setSortable(false);
		    }
		  
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
			 
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 String[] formatterval  = {"cmbMomaAttandance#9"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 tableModel.put("formatterIndex",formatterval);
			 tableModel.set("tableHeight", "43%%");
			 tableModel.set("tableWidth", "100%%");
			 return tableModel;
	
	}
	private JSONObject getTableModel(List<String[]> headers) 
	{
		
			CommonMessage.debugMsg("getTableModel");
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			
			String [] colHeader = headers.get(0);
			//CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
			//CommonMessage.debugMsg("headers Completed");
			
		
			jqGridTableModel.getRowHeaders().add(colHeader);
			
			//jqGridTableModel.setTableButton(true);		
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(100);
			jqGridTableModel.setTableWidth(1000);
			jqGridTableModel.setPaginate(true);
			//jqGridTableModel.setSortable(false);
			//jqGridTableModel.setEnableFilter(true);
			
		for(int i =0; i < colHeader.length; i++)
		{	
		
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex("C_"+colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setName("C_"+colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setWidth( 20);				
			jqGridColModel.setAlign("right");
			jqGridColModel.setEditable(false);
			if(i==0)                                       
			{
				jqGridColModel.setWidth(75); 	
				jqGridColModel.setAlign("left");
			}
			if(i>5)                                       
			{
				jqGridColModel.setAlign("center");
			}
			if(i==5||i==3)                                       
			{
				jqGridColModel.setWidth(57); 	
			}
			if(i==1)                                       
			{
				jqGridColModel.setWidth(75); 
			}
			if(i==4)                                       
			{
				jqGridColModel.setWidth(49); 
			}
			
		    if(i==2)
			{
				jqGridColModel.setWidth(70); 
			}
		  
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 tableModel.set("tableHeight", "80%%");
			 tableModel.set("tableWidth", "110%%");
			 return tableModel;
	
	}
	private void saveMOMeeting(HttpServletRequest request,HttpServletResponse response, MOMeetingBean momeetingBean) throws BusinessApplicationExceptions,Exception 
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	//String imagePath = UIUtils.getImagePath(request);
  
    	try
          {
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlMommst existGenTlMommst=(GenTlMommst)httpSession.getAttribute("momInputDetails");	 
	    		
	    		
	    		GenTlMommst newGenTlMommst = new GenTlMommst();
	    		GenTlMomdtl newGenTlMomdtl = new GenTlMomdtl();
	    		GenTlMomattendance newMomattendance=new GenTlMomattendance();
	    		GenTlMomKpiLink newGenTlMomKpiLink =new GenTlMomKpiLink();
	    		newGenTlMommst.setMomsCreatedby(user.getUsrm_ccno());
	    		newGenTlMomdtl.setMomdCreatedby(user.getUsrm_ccno());
	    		newMomattendance.setMomaCreatedby(user.getUsrm_ccno());
	    		newGenTlMomKpiLink.setMokpCreatedby(user.getUsrm_ccno());
	    		String momDetail = request.getParameter("momDetails");
	    		CommonMessage.debugMsg(" momDetail :: Now Checking :: "+momDetail);
	    		
	    		String MomKpiIndicator = request.getParameter("KpiIndicator");
	    		//CommonMessage.debugMsg(" Inside Servlet :: "+MomKpiIndicator);
	    		
	    		String Rowid = request.getParameter("rowid");
	    		//CommonMessage.debugMsg(" Inside Servlet :: Rowid "+Rowid);
	    		
	    		String momactnpln = request.getParameter("momactnpln");
	    		String filemanger = request.getParameter("filemanger");
	    		
	    		CommonMessage.debugMsg(" Outside filemanger "+filemanger);
	    		
	    		CommonMessage.debugMsg(" Inside Servlet :: momactnpln "+momactnpln);
	    		
	    		String MomAtt = request.getParameter("MomAtt");
	    		//CommonMessage.debugMsg(" Inside Servlet :: MomAtt "+MomAtt);
	    		
	    		String cellId=(String)httpSession.getAttribute("cellId");
	    		
	    		//CommonMessage.debugMsg(" Inside save Method :: cellId :: "+cellId);
	    		momeetingBean.setMomCellId(cellId);
	    		//CommonMessage.debugMsg(" Getting Value :: "+momeetingBean.getMomCellId());
	    		//String MomAtt = request.getParameter("MomAtt");
	    		
	    		newGenTlMommst =(GenTlMommst)UIUtils.setBeanProperties((Object)newGenTlMommst,request);
	    		//newGenTlMomdtl =(GenTlMomdtl)UIUtils.setBeanProperties((Object)newGenTlMomdtl,request);
	   	         
	    		CommonMessage.debugMsg(" Inside Save Action "+newGenTlMommst.getMomsFlid());
	    		
	    		newMomattendance.setMomaFlid(newGenTlMommst.getMomsFlid());
	    	
	    		/*  if( newMomattendance != null)
	     			newGenTlMommst.getMomeetinMomattendances().add(newMomattendance);*/
	            
	    		
		/** save JsonARR conversion For Detail **/
	             
	            //CommonMessage.debugMsg("IIf condition Insert  MOM  :::");
	    		List<GenTlMomdtl> MomGridList = new ArrayList<GenTlMomdtl>();
	    		List<GenTlMomKpiLink> MomKpiList = new ArrayList<GenTlMomKpiLink>();
	    		JSONArray MOmGridjson = null;	    		
	    		JSONArray MomKpijson = null;
	    		CommonMessage.debugMsg("MoMeeting Xml");	    		
	    		if(UIUtils.isValidKeyId(momDetail) )
	    		{
	    			if( newGenTlMomdtl != null)
		    			newGenTlMommst.getMomeetingDetail().add(newGenTlMomdtl);
	    			MOmGridjson = JSONArray.fromString(momDetail);
	    			CommonMessage.debugMsg(" MomGrid Checking Now :: Json Problem " + MOmGridjson);
						//CommonMessage.debugMsg(" mulitMethodStr " + mulitMethodStr);
	    			if( UIUtils.isValidKeyId(MomKpiIndicator)){
					    CommonMessage.debugMsg(" momDetailstr " + momDetail);
					    CommonMessage.debugMsg(" MomKpiIndicator " + MomKpiIndicator);
						
						
						MomKpijson = JSONArray.fromString(MomKpiIndicator);
						MomKpiList=(List<GenTlMomKpiLink>)UIUtils.convertJSONArrToList(newGenTlMomKpiLink, MomKpijson);
	    			}	
						MomGridList=(List<GenTlMomdtl>)UIUtils.convertJSONArrToList(newGenTlMomdtl, MOmGridjson);
					    
					    CommonMessage.debugMsg(" After Converting Json To List :: "+MomGridList);
					    CommonMessage.debugMsg(" After Converting Json To List :: 11 "+newGenTlMomdtl.getMomdDiscussionDetails());
					    CommonMessage.debugMsg(" After Converting Json To List :: 22 "+newGenTlMomdtl.getMomdDiscussionType());
					    CommonMessage.debugMsg(" After Converting Json To List :: 33 "+newGenTlMomdtl.getMomdRemarks());
					    CommonMessage.debugMsg(" After Converting Json To List :: 44 "+newGenTlMomdtl.getMomdPillar());
					    if(MomGridList!= null )
						{
						        CommonMessage.debugMsg(" Inside If Condition :: ");
						    	newGenTlMommst.setMomeetingDetail(MomGridList);
						    	if(MomKpiList!=null){
						    		CommonMessage.debugMsg(" Inside If Condition if :: ");
						    		newGenTlMommst.setMomeetingKPI(MomKpiList);	
						    		CommonMessage.debugMsg(" Inside If Condition :: "+newGenTlMommst.getMomeetingKPI());
						    		//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
						    	}
						 }	
	            }
	    		      
	    	                 
	                	    		      
		/**conversion END**/	
	    		
	    		
	    /*		if( newMomattendance != null)
	 			     newGenTlMommst.getMomeetinMomattendances().add(newMomattendance);
	     */   
	/** save JsonARR conversion For Attendance **/
	           	
	             //CommonMessage.debugMsg("If condition Insert  ATTT:::" );  
			  	 List<GenTlMomattendance> MomGridList1 = null;
	      		 JSONArray MOmGridjson1 = null;
	      		 CommonMessage.debugMsg("MomAtt::::"+MomAtt);
	  		      if(UIUtils.isValidKeyId(MomAtt))
	  		      {
			    		//CommonMessage.debugMsg(" mulitMethodStr " + mulitMethodStr);
					    CommonMessage.debugMsg(" momDetailstr " + MomAtt);
						MOmGridjson1 = JSONArray.fromString(MomAtt);
						MomGridList1=(List<GenTlMomattendance>)UIUtils.convertJSONArrToList(newMomattendance, MOmGridjson1);
						 if(MomGridList1!= null)
					     {
					    	newGenTlMommst.setMomeettingAttence(MomGridList1);
					    /*	CommonMessage.debugMsg(" Attendance " + newGenTlMommst.getMomeetinMomattendances().get(0).getMomaDate());
					    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaAttandance());
					    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaEmployeeid());
					    	CommonMessage.debugMsg(" In Json Conversion :: "+newGenTlMommst.getMomeetinMomattendances().get(0).getMomaMomsKeyid());
					    	*/	//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
	                  }	
	              }
			
	/**conversion END**/	
	  		      
	  	            
	  		      boolean insert = true;
					if( newGenTlMommst.getMomsKeyid() == null )
					 {							
						//CommonMessage.debugMsg("KeyId is Null" );
						existGenTlMommst =	moMeetingService.create(newGenTlMommst,existGenTlMommst,momeetingBean);
						
						CommonMessage.debugMsg("Keyid in create "+existGenTlMommst.getMomsKeyid());
						//existGenTlMommst =	moMeetingService.updateatt(newGenTlMommst,existGenTlMommst);
						
					 }	
					else
					  {
						//CommonMessage.debugMsg("Update function");
						
						existGenTlMommst = moMeetingService.update(newGenTlMommst,existGenTlMommst, momeetingBean);
						
						//existGenTlMommst =	moMeetingService.updateatt(newGenTlMommst,existGenTlMommst);
						
						insert = false;
					  }					
					//CommonMessage.debugMsg("After the IF Loop");
					//CommonMessage.debugMsg("Ooooold KEyId"+existGenTlMommst.getMomsKeyid());
					httpSession.setAttribute(existGenTlMommst.getMomsKeyid(),existGenTlMommst);
				    //CommonMessage.debugMsg("http sesiion 1");
					httpSession.setAttribute("GenTlMOmmst", existGenTlMommst);
					JSONObject successData = new JSONObject();
					String msgPropertyIdnt;					 
						 if( insert)
						   {
							msgPropertyIdnt = "success-save";
						   }
						 else
							msgPropertyIdnt = "success-update";
					 
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
						
					    //successData.put("msg","Data Saved Successfully");
						//successData.put("mode",momeetingBean.getMomdDisDtl() );
					    
						successData.put("keyId", existGenTlMommst.getMomsKeyid());
						JSONObject returnData = new JSONObject();
						
						returnData.put("successData", successData);
						returnData.put("MomMstkeyid",existGenTlMommst.getMomsKeyid());
						returnData.put("MomMstkeyid",existGenTlMommst.getMomsKeyid());
						returnData.put("MomMstNo",existGenTlMommst.getMomsMeetingno());

						if(UIUtils.isValidKeyId(filemanger)){
						    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
							returnData.put("filemanger",true);
							returnData.put("formClear",false);
						}
						
						
						if(UIUtils.isValidKeyId(momactnpln)){
							//returnData.put("MomDtlkeyid",existGenTlMommst.getMomeetingDetail().get(Integer.parseInt(Rowid)-1).getMomdKeyid());
							returnData.put("RowId",Rowid);
							//CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
							returnData.put("momactnpln",true);
							returnData.put("formClear",false);
							returnData.put("MomMstkeyid",existGenTlMommst.getMomsKeyid());	
						}else
						{ 
							returnData.put("MomDtlkeyid","");
							returnData.put("RowId","");
							returnData.put("formClear",false);
							returnData.put("momactnpln", false);
						}
						
						
						out.print(returnData.toString());
						
					
	    	     }
									
		}   
                     catch (ValidationExceptions e) 
                     {
							CommonMessage.debugMsg("ValidationExceptions");
							e.printStackTrace();
							net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "Momeeting");
							out.print(errMessage.toString());							    	
                     }
												
			       catch(BusinessApplicationExceptions e)
			          {
			    	    e.printStackTrace();
			    	    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Momeeting");
						errMessage.put("tpmException","Duplicate Entry");
						errMessage.put("displyMsg", false);			
						out.print(errMessage.toString());
						//CommonMessage.debugMsg(" e " + errMessage );
					
						}
			      catch(Exception e)
			         {   
				         e.printStackTrace();
				         JSONObject err = new JSONObject();
				         err.put("tpmException", "Data Not Saved");
				         //err.put("tpmException","Duplicate Entry");
				         out.print(err.toString());
				         
			        }
			
    	}
	
	/** save END**/


	//change in here for delete
	private void DeleteMOM(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{

				HttpSession httpSession = request.getSession(false);
				ServletOutputStream out = response.getOutputStream();
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				String momkeyid=request.getParameter("keyId");
	    	
    	if( httpSession != null && user != null)
    	  {	
	    		GenTlMommst existGenTlMommst = (GenTlMommst)httpSession.getAttribute("newSession"); 
	    		//GenTlEmployeedtl existGenTlEmployeedtl = (GenTlEmployeedtl)httpSession.getAttribute("genTl"); 	    		
	    		GenTlMommst newGenTlMommst = new GenTlMommst();
	    		GenTlMomdtl newGenTlMomdtl = new GenTlMomdtl();
	    	    GenTlMomattendance newGenTlMomattendance =new GenTlMomattendance();
	    		newGenTlMommst.setMomsCreatedby(user.getUsrm_ccno());
	    		newGenTlMomdtl.setMomdCreatedby(user.getUsrm_ccno());
	    		newGenTlMomattendance.setMomaCreatedby(user.getUsrm_ccno());	    	      		
	    		newGenTlMommst =(GenTlMommst)UIUtils.setBeanProperties((Object)newGenTlMommst,request);	
	    		if(newGenTlMommst.getMomsKeyid()==null||newGenTlMommst.getMomsKeyid().length()!=0)
	    		{
	    			newGenTlMommst.setMomsKeyid(momkeyid);
	    		}
      		 
	    	try {		
	    		
				CommonMessage.debugMsg("   newGenTlMommst.getMomsKeyid() " +  newGenTlMommst.getMomsKeyid());
				CommonMessage.debugMsg("Delete function");						
				existGenTlMommst = moMeetingService.delete(newGenTlMommst);										
				//CommonMessage.debugMsg("After the IF Loop");
				httpSession.setAttribute(existGenTlMommst.getMomsKeyid(), existGenTlMommst);
				httpSession.setAttribute("GenTlMomeeeting", existGenTlMommst);
			
						
				JSONObject mode = new JSONObject();
			    //mode.put("formMode",employeeBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("MomsKeyid",existGenTlMommst.getMomsKeyid());
				//persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject forwardData = new JSONObject();
				
				/*forwardData.put("EmpmKeyid",existGenTlEmployeemst.getEmpmKeyid());
				//CommonMessage.debugMsg("servlet out put:"+forwardData);
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);				
				//out.print(mode.toString());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Deleted Successfully");
				out.print(err.toString());*/
							
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("SuccessData");
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("keyId", existGenTlMommst.getMomsKeyid());
				JSONObject returnData = new JSONObject();						
				returnData.put("successData", successData);
				returnData.put("formClear", true);	
				CommonMessage.debugMsg("successData"+successData);
				
				out.print(returnData.toString());
				
				
			}
			catch(BusinessApplicationExceptions e)
			{
				//CommonMessage.debugMsg("Business EXC "+e);
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Momeeting");
				out.print(errMessage.toString());
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				//err.put("tpmException", " Data Not Delete ");
				out.print(err.toString());
			}
    	}
			
	}
	
    /** Delete MOM End   **/
	
	
	/** TABLE Model Grid MAIN method **/	
		
	private JSONObject getTableModel(List<String[]> headers ,HttpServletRequest request) 
	{
		
		
			CommonMessage.debugMsg("getTableModel");
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
         	String [] colHeader = headers.get(0);
			String[] emptyrow = new String[colHeader.length];
			emptyrow[0] = "";
			emptyrow[1] = "";
			jqGridTableModel.getRowHeaders().add(headers.get(1));
		
			jqGridTableModel.setTableButton(true);		
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(100);
			jqGridTableModel.setTableWidth(1000);
			jqGridTableModel.setSortable(false);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setPaginate(true);
			
		/*for(int i =0; i < colHeader.length; i++)
		{	
			CommonMessage.debugMsg("colHeader1()"+colHeader);
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setWidth( 250);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0)                                       
			{
				jqGridColModel.setHidden(true);				
			}
			if(i==1)
			{
				jqGridColModel.setHidden(true); 
			}
		    if(i==2)
		    {
		    	jqGridColModel.setWidth(550);			
		    }
		    
		    if(i==3)
		    {
		    	jqGridColModel.setWidth(80);	
		    	//jqGridColModel.setAlign("right");
		    }
		    if(i==4)
		    {
		    	jqGridColModel.setWidth(120);			
		    }
		    if(i==5)
		    {
		    	jqGridColModel.setWidth(120);	
		    	jqGridColModel.setAlign("left");
		    }
		    if(i==6)//date
		    {
		    	jqGridColModel.setWidth(100);	
		    	jqGridColModel.setAlign("center");
		    }
			if (i==7)
			{
				jqGridColModel.setWidth(150);
			}
			if(i==8)//Att
			{
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
			}
			if(i==9)//Action Plane
			{
				jqGridColModel.setWidth(100);
			}
			if(i==10)
			{
				jqGridColModel.setHidden(true);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}*/
		
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 tableModel.set("tableHeight", "66%%");
			 tableModel.set("tableWidth", "107%%");
			 return tableModel;
	}
	
	
	private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
				
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(true);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		
		if(groupbyfield)
		{
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
	}

		
		
	
	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');
		
		//CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	
	
	private void saveVisitors(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	  try
          {
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlVisitors existGenTlVisitors = (GenTlVisitors)httpSession.getAttribute("newSession");
	    		GenTlVisitors newGenTlVisitors = new GenTlVisitors();
	    		newGenTlVisitors.setVisiCreatedby(user.getUsrm_ccno());
	    		newGenTlVisitors =(GenTlVisitors)UIUtils.setBeanProperties((Object)newGenTlVisitors,request);
	    		
	    		boolean insert = true;
				
				//CommonMessage.debugMsg("  newGenTlMommst.getMomsKeyId() " +  newGenTlVisitors.getVisiMomsKeyid());
				if( newGenTlVisitors.getVisiKeyid() == null )
				 {							
					//CommonMessage.debugMsg("KeyId is Null" );
					existGenTlVisitors =	moMeetingService.createVisitor(newGenTlVisitors,existGenTlVisitors);
				 }	
				else
				  {
					//CommonMessage.debugMsg("Update function");						
					existGenTlVisitors = moMeetingService.updateVisitor(newGenTlVisitors,existGenTlVisitors);
					insert = false;
				  }					
				//CommonMessage.debugMsg("After the IF Loop");
				//CommonMessage.debugMsg("Ooooold KEyId"+existGenTlVisitors.getVisiKeyid());
				httpSession.setAttribute(existGenTlVisitors.getVisiKeyid(),existGenTlVisitors);
			    //CommonMessage.debugMsg("http sesiion 1");
			    
				//httpSession.setAttribute("GenTlMOmmst", existGenTlVisitors);
				//CommonMessage.debugMsg(" DATA Sucessfuly saved ");
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;					 
					 if( insert)
					   {
						msgPropertyIdnt = "success-save";
					   }
					 else
						msgPropertyIdnt = "success-update";
				 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
					//successData.put("msg","Data Saved Successfully");
					successData.put("keyId", existGenTlVisitors.getVisiKeyid());
					successData.put("MstKeyId", existGenTlVisitors.getVisiMomsKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					returnData.put("formClear",true);
					out.print(returnData.toString());
	    	}
          }
    	  catch (ValidationExceptions e) 
          {
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "AddVisitors");
				out.print(errMessage.toString());
				    	
          }
										
	  catch(BusinessApplicationExceptions e)
	          {
				//CommonMessage.debugMsg("Error Servler e -"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "AddVisitors");
				out.print(errMessage.toString());
				//CommonMessage.debugMsg(" e " + errMessage );
		      }
	  catch(Exception e)
	          {   
		         //CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		         JSONObject err = new JSONObject();
		         err.put("tpmException", "Data Not Saved");
		         out.print(err.toString());
	          }
  			

    	
	}
	
	
	@SuppressWarnings("null")
	private void sendMomMail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fileName  = "";
		String val="";
		File f = null;
		String momKeyId = request.getParameter("momKeyId");
		try{
			CommonMessage.debugMsg(" 12 ");
			
			String flid=request.getParameter("flid");
			String glbType=request.getParameter("glbType");
			String mType=request.getParameter("mtype");
			String momDate=request.getParameter("date");
			String format = ExcelUtils.getFormat(request);
			
			CommonMessage.debugMsg(" format "+format+" mType "+mType+" glbType "+glbType+" momKeyId "+momKeyId);
			
			List<String[]> empMailIds = moMeetingService.getMomAttendanceEmpMailIds(momKeyId, flid) ;
			String mailIds = buildToMailIds(empMailIds);
			CommonMessage.debugMsg(" mailIds :: 12 "+mailIds);
			
			
			if( mailIds.isEmpty())
			{
				JSONObject succssMsg= new JSONObject();
				succssMsg.put("msg", "No Valid MailId Found ");
		        response.getWriter().print(succssMsg.toString());
				
				return ;
			}
			
			format = ".xlsx";
			
			String path = UIUtils.getExcelTemplatePath(request);
			
			fileName =this.filePath + "MinutesofMeeting_"+momKeyId + "_" + UIUtils.now() +format;
			CommonMessage.debugMsg(" fileName :: Checking :: " + fileName);
			
			Workbook wb = moMeetingService.momExcelView(glbType,momKeyId,flid,path);
			
            
			CommonMessage.debugMsg(" after gen " + path);
			
			FileOutputStream out = new FileOutputStream( fileName );
		    wb.write(out); 
		    out.close();
		    out = null;
		    wb = null;

		    CommonMessage.debugMsg(" File Write Completed " );
		    f = new File( fileName);
			
			
			String content = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailContent");
			String disclaimerNote = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailDisclaimer");
			
			StringBuilder subject = new StringBuilder(mType);
			subject.append(" Meeting - ");
			subject.append(momDate);
			
			StringBuilder totalContent = new StringBuilder();
			totalContent.append(content);
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			attachmentFiles = attachFileManagerFiles(attachmentFiles,momKeyId);
			
			//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,null,subject.toString(),totalContent.toString(),attachmentFiles);
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Sent SuccessFully ");
			val="false";
			String mailid=moMeetingService.updateIsmailid(momKeyId,val);
	        response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" );
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			val="true";
			try {
				String mailid=moMeetingService.updateIsmailid(momKeyId,val);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        response.getWriter().print(succssMsg.toString());
		}
		
	}
	
	private List<String> attachFileManagerFiles(List<String> attachmentFiles, String momKeyId) throws Exception {
		
		List<String[]> docMgrids = moMeetingService.getMomReleatedFileManager(momKeyId);
		CommonMessage.debugMsg(" size 1234 :: "+docMgrids.size());
		for (int i=0;i<docMgrids.size();i++) {
			String fileName = docMgrids.get(i)[0];
			//commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			String ext = fileName.substring(fileName.lastIndexOf('.'),fileName.length());
			CommonMessage.debugMsg(" Checking for file extension to be correct "+ext); 
		   //DcmTlDocumentmanager dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);		
		   if(UIUtils.isValidKeyId(fileName))
		   {
			   //String fileName = dcmTlDocumentmanager.getDmdmFilename();
			   String path = DOC_ROOT_PATH + docMgrids.get(i)[1];
			   CommonMessage.debugMsg(" Fourth New path "+path);
			   path = path.replace("/", "\\");
			   CommonMessage.debugMsg(" filename extension "+path+ext);
			   //attachmentFiles.add(path+ext);
			   attachmentFiles.add(path);
		   }
		}
		
		return attachmentFiles;
	}
	
	private String buildToMailIds(List<String[]> mailIds){
		StringBuilder mailIdsStr = new StringBuilder();
		for(String [] mailid : mailIds){
			if(UIUtils.isValidEmail(mailid[1]) ){
				mailIdsStr.append(mailid[1]);
				mailIdsStr.append(',');
			}	
		}
		if(  mailIdsStr.length() > 0 )
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		
		return mailIdsStr.toString();
	}

	private void momAttendanceMonthwiseCountTableModel(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCountCommonFilter",true);
		String Flid =request.getParameter("flid");
		String dtFromDate =request.getParameter("dtFromDate");
		String dtToDate =request.getParameter("dtToDate");
		String dtFromMonth =request.getParameter("dtFromMonth");
		String dtToMonth =request.getParameter("dtToMonth");
		String type =request.getParameter("type");
		String pillarId =request.getParameter("pillarId");
		
		List<String[]> momReportList;
		PrintWriter out = response.getWriter();
		commonFilter.setFlid(Flid);
		 if(Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
			 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
			 commonFilter.setToDate(CommonFunctions.getDate());
		 }	 
		  if(Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } 
		try {
			
			CommonMessage.debugMsg(" Checking for getCol :: "+type);
			
			if(UIUtils.isValidKeyId(type))
				   commonFilter.setMaintMode(type);
			
			if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			
		momReportList = moMeetingService.getAttendancemonthwiseCount(commonFilter,Flid);
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
		httpSession.removeAttribute("MomAttReportMonthwiseCountColmodel");
		httpSession.setAttribute("MomAttReportMonthwiseCountColmodel", jsonObject);
		jsonObject.put("tableHeight", "74%%");
		jsonObject.put("tableWidth", "106%%");
		CommonMessage.debugMsg("jsonObject ="+jsonObject);
      	out.println(jsonObject);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		private void momAttendanceMonthwiseCountData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String Flid =request.getParameter("flid");
		String dtFromDate =request.getParameter("dtFromDate");
		String dtToDate =request.getParameter("dtToDate");
		String dtFromMonth =request.getParameter("dtFromMonth");
		String dtToMonth =request.getParameter("dtToMonth");
		
try {
		CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCountCommonFilter",false);
		    
		if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
			 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
			 commonFilter.setToDate(CommonFunctions.getDate());
		 }	 
		  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  }
		  
		  String type =request.getParameter("type");
		  String pillarId =request.getParameter("pillarId");
		  
		  if(UIUtils.isValidKeyId(pillarId))
			   commonFilter.setPillarWise(pillarId);
		  
			if(UIUtils.isValidKeyId(type))
				   commonFilter.setMaintMode(type);
		
			CommonMessage.debugMsg(" Checking for getData :: "+type);
			
		List<String[]> MachineGrid = moMeetingService.getAttendancemonthwiseCount(commonFilter,Flid);
		PrintWriter out = response.getWriter();
		JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 3, 0,commonFilter.getTotalRecordCnt());
	
} catch (Exception e) {
	//CommonMessage.debugMsg(e.getMessage());
}
		}

	private void momAttendanceMonthwiseCountExcel(HttpServletRequest request,HttpServletResponse response)throws Exception {
		try
		{   
			
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
			
            //String type =request.getParameter("type");
            //CommonMessage.debugMsg("ins servl imp"+type);
			//if(UIUtils.isValidKeyId(type))
				//   commonFilter.setMaintMode(type);

			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			HttpSession httpSession = request.getSession(false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("MomAttReportMonthwiseColmodel");
			colmodel.put("title"," Mom Attendance Report-Month Wise Count");
            String format = ExcelUtils.getFormat(request);
			Workbook wb = moMeetingService.getMomCountExcel(commonFilter,colmodel,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingMonthWiseCount", format);

	    }catch(Exception e)
		{
			//CommonMessage.debugMsg(e.getMessage());
		}
	}
		
		
		private void processbarChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter =(CommonFilter) httpSession.getAttribute("MomAttReportMonthwiseCountCommonFilter");
		
		String forDashboard = request.getParameter("dashboard");
		
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("MomAttReportMonthwiseCountCommonFilter");
		}
		else{
			commonFilter = new  CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getBDRelated(request, commonFilter);		
		}
		
		CommonFilter chartCommonFilter = new CommonFilter(); 
		BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		FilterValues.getCommonFilters(request, chartCommonFilter) ;
		
		chartCommonFilter.setRowTotal('Y');
		
		List<String[]> MomList  = moMeetingService.getMomAttendanceCountData(chartCommonFilter );
		
		JSONObject chartObj = null;
		if(MomList != null && MomList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			CommonMessage.debugMsg("flid fff is :::"+flids);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChart2(lcnname,MomList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
		
	}

	private JSONObject processBarChart2(String titlenme,List<String[]> MomList,CommonFilter chartCommonFilter) {
		
		if( MomList == null || MomList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		//String drillLevel = FilterValues.getHeader(commonFilter.getDrillCaption());
		
	
		//String[] header =  MomList.get(0);
		String[] month =  MomList.get(0);
		String[] data =  MomList.get(MomList.size()-1);
		String prevMonth = null;
		
		//String subTitle = data[1];
		String subTitle="";
		
		StringBuilder date= new StringBuilder();
		
		CommonFilter commonFilter =chartCommonFilter;
		if(commonFilter .getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		
	
		//String drillLevel =  FilterValues.getDrillHeader(data[0]);
		//CommonMessage.debugMsg("drillLevel"+drillLevel);
		StringBuilder title = new StringBuilder(titlenme+"-Member Wise Attendance").append("-").append(date);
		//title.append(titlenme+"Member Wise Attendance").append("-").append(date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
	
		for( int i = 10;i < month.length-1;i++ ){
			countData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 0 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Member Wise Attendance");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Percentage (%)");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
	
}
				
			