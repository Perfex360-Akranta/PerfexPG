package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.PillarMomReportService;
import com.akranta.tpm.service.impl.PillarMomReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PillarMomReportServlet
 */
@WebServlet("/.pmrpt")
public class PillarMomReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	PillarMomReportService pillarMomReportService;
    public PillarMomReportServlet() {
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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String action = UIUtils.getActionPart(request);
		try {
			HttpSession h =request.getSession(false) ;
			pillarMomReportService  = (PillarMomReportServiceImpl)UIUtils.getServiceObject(request,"PillarMomReportServiceImpl");
			pillarMomReportService.PillarMomReportServiceImplJwt((String) (h.getAttribute("tpmjwttoken") == null ? "" : h.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		 if(action.equals("momattendancepillarmom_input.plrmom")) 
		{
			 CommonMessage.debugMsg(" In sdie rgb====");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/PillarMomAttendanceReport.jsp"); 
			rd.forward(request, response); 
			 CommonMessage.debugMsg(" In sdie rgb====");

		}
		else if( action.equals("momattendancepillarmom_getCol.plrmom") )
		{
			
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",true);
		String Flid =request.getParameter("flid");
		String dtFromDate =request.getParameter("dtFromDate");
		String dtToDate =request.getParameter("dtToDate");
		String dtFromMonth =request.getParameter("dtFromMonth");
		String dtToMonth =request.getParameter("dtToMonth");
		String type =request.getParameter("rptType");
		String pillarId =request.getParameter("pillarId");
		
		CommonMessage.debugMsg(type +"  typetypetypetypetypetype ");
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
			
			CommonMessage.debugMsg(pillarId+" Checking for getCol :: "+type);
			
			if(UIUtils.isValidKeyId(type))
				   commonFilter.setMaintMode(type);
			
			if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			
		momReportList = pillarMomReportService.getPillarMomAttd(commonFilter);//moMeetingService.getAttendancemonthwise(commonFilter,Flid);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		gridColModel.setHeaderNum(1);
		String [] colHeader = momReportList.get(1);			
		String [] colHeaderCond = momReportList.get(0);
		List<String[]> headers = new ArrayList<String[]>();
		HttpSession httpSession =request.getSession(true) ;

		headers.add(colHeader);
		
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
		
		else if( action.equals("momattendancepillarmom_getData.plrmom") )
		{
			
			String Flid =request.getParameter("flid");
			String dtFromDate =request.getParameter("dtFromDate");
			String dtToDate =request.getParameter("dtToDate");
			String dtFromMonth =request.getParameter("dtFromMonth");
			String dtToMonth =request.getParameter("dtToMonth");
			String type =request.getParameter("rptType");
			String pillarId =request.getParameter("pillarId");
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
		
			  
			  if(UIUtils.isValidKeyId(pillarId))
				   commonFilter.setPillarWise(pillarId);
			  
				if(UIUtils.isValidKeyId(type))
					   commonFilter.setMaintMode(type);
			
				CommonMessage.debugMsg(pillarId +" Checking for getData :: "+type);
				
			List<String[]> MachineGrid = pillarMomReportService.getPillarMomAttd(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
			out.println(machinegrid);
		
	} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
	}

	}
	
		
		else if( action.equals("momattendancepillarmom_getExcel.plrmom"))
		{	HttpSession httpSession = request.getSession(false);			
			CommonFilter commonFilter = populateCommonFilter(request,"MomAttReportMonthwiseCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MaintainActivityWiseRpt", "MaintainActivityWiseRpt");
			//JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("MomAttReportMonthwiseColmodel");
			
			tblJSONObj.put("title", "Pillar Mom Report");
			String format = ExcelUtils.getFormat(request);
			commonFilter.setIsGetCol("N");
			//Workbook wb = moMeetingService.getmomAttReportExcel(colmodel,format,commonFilter);
			Workbook wb = pillarMomReportService.getPillarMomAttdExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "Pillar Mom Report", format);
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
