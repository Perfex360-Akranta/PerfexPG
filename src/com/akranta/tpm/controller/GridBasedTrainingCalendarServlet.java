package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.TrainingBean;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.GridBasedTrainingCalendarService;
import com.akranta.tpm.service.impl.GridBasedTrainingCalendarServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class GridBasedTrainingCalendar
 */
//@WebServlet("/GridBasedTrainingCalendarServlet")
public class GridBasedTrainingCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private GridBasedTrainingCalendarService gridBasedTrgCalService= null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GridBasedTrainingCalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		try{
			process(request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			process(request,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	   @SuppressWarnings("null")
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException,Exception{
		    HttpSession httpSession=request.getSession(false);
		   String action = UIUtils.getActionPart(request);
		   
		   gridBasedTrgCalService=(GridBasedTrainingCalendarServiceImpl)UIUtils.getServiceObject(request,"GridBasedTrainingCalendarServiceImpl");
		   
			CommonMessage.debugMsg("  gridBasedTrgCalService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
			//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			gridBasedTrgCalService.GridBasedTrainingCalendarServiceImplJwt(
						   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
						);
		   String loginflid=CommonFunctions.getLoginFlid(request);
		
			 String loginlevel=CommonFunctions.getLoginLevel(request);
			  String loginElementid = (String) httpSession.getAttribute("loginElementid");
			  AdmTlUsermst user=UIUtils.getLoginUser(request);
		   String empId=user.getUsrm_ccno();
			List<String []> getUserLoginDtl= gridBasedTrgCalService.getElementId(loginflid,loginlevel, loginElementid,empId);
			String elementid = getUserLoginDtl.get(0)[0];
			String flidnew = getUserLoginDtl.get(0)[1];
			String level = getUserLoginDtl.get(0)[2];
			String rolename=getUserLoginDtl.get(0)[3];
			String roledetail=rolename;
		   String rolekeyid=getUserLoginDtl.get(0)[4];
	  
		   if(action.equals("trngcalendarGridEntry_input.gbtc")){
			   
			   CommonMessage.debugMsg(" In sid the Grid based trainig calendar   "+rolename);
				String KeyId=request.getParameter("trgCalId");

		 	    String empattn=gridBasedTrgCalService.getempattn(KeyId); 
		 	    String date=CommonFunctions.dateTimeNow();
				String currentdate=UIUtils.getActualDateForm(date);
				String loginUser=user.getUsrm_ccno();
				request.setAttribute("loginUser",loginUser);
			   request.setAttribute("rolename", rolename);
			   request.setAttribute("mode", "create");
			   request.setAttribute("empattn",empattn);
			   request.setAttribute("currentdate",currentdate.substring(0,11));

			   
			   RequestDispatcher rd=  request.getRequestDispatcher("/pages/newENT/GridBasedTrgCalCreate.jsp");
			   rd.forward(request,response);
		   }
		   
		   else if(action.equals("trngcalendarGridEntry_getCol.gbtc"))
			{
			   try{
			   CommonMessage.debugMsg(" IN side the get Col Method");
			   
			   PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", true);
				String MasterTrgCalId=request.getParameter("trgCalId");
				CommonMessage.debugMsg("In sid eth Servellet Master Id"+MasterTrgCalId);
				commonFilter.setKey(MasterTrgCalId); 
				CommonMessage.debugMsg("The commonFilter"+commonFilter);
			//	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntryNew"));
			//	String abnReportColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntryNew");
				String abnReportColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntry1");

				CommonMessage.debugMsg("The commonFilter abnReportColModel "+abnReportColModel);
				//CommonMessage.debugMsg("viewModel.."+viewModel);
				JSONObject tblJSONObj = JSONObject.fromString(abnReportColModel);
				tblJSONObj.put("tableHeight", "70%%");
				tblJSONObj.put("tableWidth", "116%%");
				//List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
				httpSession.removeAttribute("ColModel");
				httpSession.setAttribute("ColModel", tblJSONObj);
				//JSONObject abnormalityData = UIUtils.convertToJqGridTableObject(abnormalityReportList,request,0,1,commonFilter.getTotalRecordCnt());
				//httpSession.removeAttribute("abnReportGenServletdata");
				//httpSession.setAttribute("abnReportGenServletdata", abnormalityData);						
				out.println(abnReportColModel);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
			}	   	
		   
		   else if(action.equals("trngcalendarGridEntry_getData.gbtc"))
			{
			   CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);
				PrintWriter out = response.getWriter();
				String MasterTrgCalId=request.getParameter("trgCalId");
		//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
				String SeectionId=request.getParameter("sectionId");
				String cellID=request.getParameter("cellId");
				String fromDate=request.getParameter("fromdate");
				String toDate=request.getParameter("todate");
				String UniquePos=request.getParameter("uniqPostn");
				String TradeId=request.getParameter("tradeId");
				
				CommonMessage.debugMsg("In sid eth Servellet Master Id"+MasterTrgCalId);
				commonFilter.setKey(MasterTrgCalId);
				commonFilter.setSectionId(SeectionId);
				commonFilter.setCellId(cellID);
			 //	commonFilter.setFromDate(fromDate);
            //	commonFilter.setToDate(toDate);//(MasterTrgCalId);
				commonFilter.setUniquePos(UniquePos);
				commonFilter.setTrarId(TradeId);
				if(fromDate==null||fromDate==" "){
					
					
					 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
					 commonFilter.setToDate(CommonFunctions.getDate());
				 }
				else{
					commonFilter.setFromDate(fromDate);
					commonFilter.setToDate(toDate);
				}
				
				GridParams gridParams=new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				List<String[]> trainingList = gridBasedTrgCalService.getListTrgCalendar(commonFilter,gridParams); // 0 to 1
				JSONObject trainingListData = UIUtils.convertToJqGridTableObject(trainingList, request,1, 1,gridParams.getTotalRecordCnt());
				out.println(trainingListData);
				httpSession.removeAttribute("GridBasedTrgCalCommonFilter");
				httpSession.setAttribute("GridBasedTrgCalCommonFilter",commonFilter);	
			   
			}
		   
		   
 if(action.equals("trngcalendarGridModify_input.gbtc")){
			   
			   CommonMessage.debugMsg(" In sid the Grid based trainig calendar"+empId);
			   request.setAttribute("mode", "modify");
			   String loginUser=user.getUsrm_ccno();
			   request.setAttribute("loginUser",empId);
			   CommonMessage.debugMsg(" In sid the Grid based trainig calendar"+loginUser);
			   RequestDispatcher rd=  request.getRequestDispatcher("/pages/newENT/GridBasedTrainingCalendar.jsp");
			   rd.forward(request,response);
		   }
		   
		   else if(action.equals("trngcalendarGridModify_getCol.gbtc"))
			{
			   CommonMessage.debugMsg(" IN side the get Col Method");
			   
			   PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", true);
				String MasterTrgCalId=request.getParameter("trgCalId");
				CommonMessage.debugMsg("In sid eth Servellet Master Id"+MasterTrgCalId);
				commonFilter.setKey(MasterTrgCalId); 
				CommonMessage.debugMsg("The commonFilter"+commonFilter);
				//CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntry"));
				String abnReportColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntry1");

				
				//CommonMessage.debugMsg("viewModel.."+viewModel);
				JSONObject tblJSONObj = JSONObject.fromString(abnReportColModel);
				tblJSONObj.put("tableHeight", "70%%");
				tblJSONObj.put("tableWidth", "116%%");
				//List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
				httpSession.removeAttribute("ColModel");
				httpSession.setAttribute("ColModelModify", tblJSONObj);
				//JSONObject abnormalityData = UIUtils.convertToJqGridTableObject(abnormalityReportList,request,0,1,commonFilter.getTotalRecordCnt());
				//httpSession.removeAttribute("abnReportGenServletdata");
				//httpSession.setAttribute("abnReportGenServletdata", abnormalityData);						
				out.println(abnReportColModel);
			}	   	
		   
		   else if(action.equals("trngcalendarGridModify_getData.gbtc"))
			{
			   CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);
				PrintWriter out = response.getWriter();
				String MasterTrgCalId=request.getParameter("trgCalId");
		//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
				String SeectionId=request.getParameter("sectionId");
				String cellID=request.getParameter("cellId");
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("todate"); 
				String flid=request.getParameter("flid");
				String UniquePos=request.getParameter("uniqPostn");
				String TradeId=request.getParameter("tradeId");
				String mode=request.getParameter("mode");
				if(UIUtils.isValidKeyId(flid)){
					commonFilter.setFlid(flid); 
				}
				CommonMessage.debugMsg(commonFilter.getFlid() +"    "+toDate+" In sid eth Servellet Master Id  "+fromDate);
				commonFilter.setKey(MasterTrgCalId);
				commonFilter.setSectionId(SeectionId);
				commonFilter.setCellId(cellID);
				
			 //	commonFilter.setFromDate(fromDate);
            //	commonFilter.setToDate(toDate);//(MasterTrgCalId);
				commonFilter.setUniquePos(UniquePos);
				commonFilter.setTrarId(TradeId);
				if(fromDate==null||fromDate=="undefined" ||fromDate.equals("")){
					
					
					 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-2));
					 commonFilter.setToDate(CommonFunctions.getDate());
				 }
				else{
					commonFilter.setFromDate(fromDate);
					commonFilter.setToDate(toDate);
				}
				
				GridParams gridParams=new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				List<String[]> trainingList = gridBasedTrgCalService.getListTrgCalendarModify(commonFilter,gridParams);
				JSONObject trainingListData = UIUtils.convertToJqGridTableObject(trainingList, request,1, 0,commonFilter.getTotalRecordCnt());// --changed gridparams to commonfilter vignesh
				out.println(trainingListData);
				httpSession.removeAttribute("GridBasedTrgCalCommonFilter");
				httpSession.setAttribute("GridBasedTrgCalCommonFilter",commonFilter);	
			   
			}
 
 if(action.equals("trngcalendarGridView_input.gbtc")){
	   
	   CommonMessage.debugMsg(" In sid the Grid based trainig calendar");
	   
	   request.setAttribute("mode", "view");

	   RequestDispatcher rd=  request.getRequestDispatcher("/pages/newENT/GridBasedTrgCalView.jsp");
	   rd.forward(request,response);
 }
 
 else if(action.equals("trngcalendarGridView_getCol.gbtc"))
	{
	   CommonMessage.debugMsg(" IN side the get Col Method");	   
	   PrintWriter out = response.getWriter();
		CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", true);
		String MasterTrgCalId=request.getParameter("trgCalId");
		String flid=request.getParameter("flid"); 
//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
		String SeectionId=request.getParameter("sectionId");
		String cellID=request.getParameter("cellId");
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("todate");
		String UniquePos=request.getParameter("uniqPostn");
		String TradeId=request.getParameter("tradeId");
		if(UIUtils.isValidKeyId(flid)){
			commonFilter.setFlid(flid); 
			
		}
		if(fromDate==null||fromDate.equals("")||fromDate.equals("undefined")){
			 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-2));
			 commonFilter.setToDate(CommonFunctions.getDate());
		}
		CommonMessage.debugMsg(CommonFunctions.getFirstDateofMonth(-2) +"In sid eth Servellet Master Id"+MasterTrgCalId);
		commonFilter.setKey(MasterTrgCalId); 
		CommonMessage.debugMsg(commonFilter.getToDate() +"  The commonFilter"+commonFilter.getFromDate());
		//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedEntry"));
		String abnReportColModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "GridBasedView");

		
		//CommonMessage.debugMsg("viewModel.."+viewModel);
		JSONObject tblJSONObj = JSONObject.fromString(abnReportColModel);
		tblJSONObj.put("tableHeight", "70%%");
		tblJSONObj.put("tableWidth", "116%%");
		//List<String []> abnormalityReportList  = abnormalityReportService.getAllAbnormalityGeneral(commonFilter);
		httpSession.removeAttribute("ColModel");
		httpSession.setAttribute("ColModel", tblJSONObj);
		//JSONObject abnormalityData = UIUtils.convertToJqGridTableObject(abnormalityReportList,request,0,1,commonFilter.getTotalRecordCnt());
		//httpSession.removeAttribute("abnReportGenServletdata");
		//httpSession.setAttribute("abnReportGenServletdata", abnormalityData);						
		out.println(abnReportColModel);
	}	   	
 
 
 else if(action.equals("trngcalendarGridView_getData.gbtc"))
	{
	   CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);
		PrintWriter out = response.getWriter();
		String MasterTrgCalId=request.getParameter("trgCalId"); 
		String flid=request.getParameter("flid"); 
//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
		String SeectionId=request.getParameter("sectionId");
		String cellID=request.getParameter("cellId");
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("todate");
		String UniquePos=request.getParameter("uniqPostn");
		String TradeId=request.getParameter("tradeId");
		if(UIUtils.isValidKeyId(flid)){
			commonFilter.setFlid(flid); 

		}
		CommonMessage.debugMsg(fromDate+"In sid eth Servellet Master Id"+MasterTrgCalId);
		commonFilter.setKey(MasterTrgCalId);
		commonFilter.setSectionId(SeectionId);
		commonFilter.setCellId(cellID); 
		//commonFilter.setFlid(flid); 
	 //	commonFilter.setFromDate(fromDate);
  //	commonFilter.setToDate(toDate);//(MasterTrgCalId);
		commonFilter.setUniquePos(UniquePos);
		commonFilter.setTrarId(TradeId);
		if(fromDate==null||fromDate=="undefined "||fromDate.equals("")){
			
			
			 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-2));
			 commonFilter.setToDate(CommonFunctions.getDate());
		 }
		else{
			commonFilter.setFromDate(fromDate);
			commonFilter.setToDate(toDate);
		}
		
		GridParams gridParams=new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		List<String[]> trainingList = gridBasedTrgCalService.getListTrgCalendarView(commonFilter,gridParams);
		JSONObject trainingListData = UIUtils.convertToJqGridTableObject(trainingList, request,1, 0,commonFilter.getTotalRecordCnt());// --changed gridparams to commonfilter vignesh
		out.println(trainingListData);
		httpSession.removeAttribute("GridBasedTrgCalCommonFilter");
		httpSession.setAttribute("GridBasedTrgCalCommonFilter",commonFilter);	
	   
	}
 
 // -- ADDING EXCEL FOR MODIFY  VIGNESH 05DEC2025 --------------------------------------------------------//
    if (action.equals("trngcalendarGridView_getExcel.gbtc")){
    	
    	/////
    	
    	   CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);
			//PrintWriter out = response.getWriter();
			String MasterTrgCalId=request.getParameter("trgCalId");
	//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
			String SeectionId=request.getParameter("sectionId");
			String cellID=request.getParameter("cellId");
			String fromDate=request.getParameter("fromDate");
			String toDate=request.getParameter("todate"); 
			String flid=request.getParameter("flid");
			String UniquePos=request.getParameter("uniqPostn");
			String TradeId=request.getParameter("tradeId");
			String mode=request.getParameter("mode");
			String tmpFromRow="1";
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFlid(flid); 
			}
			CommonMessage.debugMsg(commonFilter.getFlid() +"    "+toDate+" In sid eth Servellet Master Id  "+fromDate);
			commonFilter.setKey(MasterTrgCalId);
			commonFilter.setSectionId(SeectionId);
			commonFilter.setCellId(cellID);
			
		 //	commonFilter.setFromDate(fromDate);
       //	commonFilter.setToDate(toDate);//(MasterTrgCalId);
			commonFilter.setUniquePos(UniquePos);
			commonFilter.setTrarId(TradeId);
			if(fromDate==null||fromDate.equals("")||fromDate=="undefined"){				
				
				 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-2));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }
			else{
				commonFilter.setFromDate(fromDate);
				commonFilter.setToDate(toDate);
			}
			
			GridParams gridParams=new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			
			// GridParams gridparams = new GridParams();
             //FilterValues.populateGridParams(request, gridparams);
             FilterValues.getCommonFilters(request, commonFilter);
				GridColModel gridColModel = new GridColModel();			

		 JSONObject colmodel = (JSONObject) httpSession.getAttribute("ColModel");
		// colmodel.put("title","TrainingCalendar Report");
        String format = ExcelUtils.getFormat(request);
        Workbook wb = gridBasedTrgCalService.getTrainingCalendarListExcel(colmodel,format,commonFilter);
        commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);	
		 
    	
    	/*
    					//httpSession = request.getSession(false);
				CommonFilter commonFilter = new CommonFilter();
				 commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",false);
				 String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					UIUtils.displayRequestParamsValue(request);   
	                  String flid=request.getParameter("flid");
	                   String mode = request.getParameter("frmMode");
	                   commonFilter.setType(mode);
	                  commonFilter.setFlid(flid);
	                  String keyid=request.getParameter("keyid");
	                  commonFilter.setKey(keyid);
	                 
	                  //FilterValues.getCommonFilters(request, commonFilter);
	                  GridParams gridparams = new GridParams();
	                  //FilterValues.populateGridParams(request, gridparams);
	                  FilterValues.getCommonFilters(request, commonFilter);
	               
				 JSONObject colmodel = (JSONObject) httpSession.getAttribute("TrainingColmodel");
				 colmodel.put("title","TrainingCalendar Report");
	             String format = ExcelUtils.getFormat(request);
				Workbook wb = gridBasedTrgCalService.getTrainingCalendarListExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
				*/
		
    }
    
 if (action.equals("trngcalendarGridModify_getExcel.gbtc")){
    	
    	/////
    	
    	   CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);
			//PrintWriter out = response.getWriter();
			String MasterTrgCalId=request.getParameter("trgCalId");
	//		var ds="q=2&sectionId="+sectionId+"&cellId="+cellId+"&fromDate="+fromdate+"todate="+todate;
			String SeectionId=request.getParameter("sectionId");
			String cellID=request.getParameter("cellId");
			String fromDate=request.getParameter("fromDate");
			String toDate=request.getParameter("todate"); 
			String flid=request.getParameter("flid");
			String UniquePos=request.getParameter("uniqPostn");
			String TradeId=request.getParameter("tradeId");
			String mode=request.getParameter("mode");
			String tmpFromRow="1";
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFlid(flid); 
			}
			CommonMessage.debugMsg(commonFilter.getFlid() +"    "+toDate+" In sid eth Servellet Master Id  "+fromDate);
			commonFilter.setKey(MasterTrgCalId);
			commonFilter.setSectionId(SeectionId);
			commonFilter.setCellId(cellID);
			
		 //	commonFilter.setFromDate(fromDate);
       //	commonFilter.setToDate(toDate);//(MasterTrgCalId);
			commonFilter.setUniquePos(UniquePos);
			commonFilter.setTrarId(TradeId);
			if(fromDate==null||fromDate.equals("")||fromDate=="undefined"){				
				
				 commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-2));
				 commonFilter.setToDate(CommonFunctions.getDate());
			 }
			else{
				commonFilter.setFromDate(fromDate);
				commonFilter.setToDate(toDate);
			}
			
			GridParams gridParams=new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			
			// GridParams gridparams = new GridParams();
             //FilterValues.populateGridParams(request, gridparams);
             FilterValues.getCommonFilters(request, commonFilter);
				GridColModel gridColModel = new GridColModel();			

		 JSONObject colmodel = (JSONObject) httpSession.getAttribute("ColModelModify");
		// colmodel.put("title","TrainingCalendar Report");
        String format = ExcelUtils.getFormat(request);
        Workbook wb = gridBasedTrgCalService.getTrainingCalendarListExcel(colmodel,format,commonFilter);
        commonFilter.setFromRow(tmpFromRow);
		ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);	
		 
		
    }
    
    
    
    
    
    // -- ADDING EXCEL FOR MODIFY  VIGNESH 05DEC2025 --------------------------------------------------------//
    
 
		   
            if(action.equals("trngCalUniquePosition_input.gbtc")){
			   
				httpSession = request.getSession(false);

			   String KeyId=request.getParameter("trgCalId");
			   String flid=request.getParameter("fliId");
			   String locationId=request.getParameter("locationId");
			   String sectionId=request.getParameter("sectionId");
			   String cellId=request.getParameter("cellId");
			   String mode=request.getParameter("mode"); 
			   String trngType=request.getParameter("trngType");
			   String trngDate=request.getParameter("trngDate");
			   String trngDuration=request.getParameter("trngDuration");
			   CommonMessage.debugMsg(KeyId+" In sid the Grid based trainig calendar"+trngType); 
			   String trngSesDate=gridBasedTrgCalService.getTrgDateData(KeyId);


			   request.setAttribute("trgCalId",KeyId);
			   request.setAttribute("flid",flid);
			   request.setAttribute("locationId",locationId);
			   request.setAttribute("sectionId",sectionId);
			   request.setAttribute("cellId",cellId);
			   request.setAttribute("mode",mode);
			   request.setAttribute("trngType",trngType);
			   request.setAttribute("rolename",rolename);
			   request.setAttribute("trngDate",trngSesDate); 
			   request.setAttribute("trngDuration",trngDuration);

			   UIUtils.forwardRequest(request, response,"pages/newENT/GridBasedUniquePosition.jsp");
			  // RequestDispatcher rd=  request.getRequestDispatcher("/pages/newENT/GridBasedUniquePosition.jsp");
			  //rd.forward(request,response);
		   }
            if(action.equals("trngCalUniquePosition_getCol.gbtc")){
            	
            	 try {
            		 PrintWriter out = response.getWriter();
     			//	CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", true);

					    String TrainingKeyid = request.getParameter("trgCalId");
						CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
						List<String[]> UniquPosList = gridBasedTrgCalService.getNewUniqPosData(TrainingKeyid);
						JSONObject jsonObject = getUniquePositionTableModel(UniquPosList);
						CommonMessage.debugMsg("Table model");
						CommonMessage.debugMsg("jsonObject "+jsonObject);
						out.println(jsonObject);
				/*		httpSession.removeAttribute("GridBasedTrgCalCommonFilter");
						httpSession.setAttribute("GridBasedTrgCalCommonFilter", commonFilter);
				*/	} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
            }
            if(action.equals("trngCalUniquePosition_getData.gbtc")){
            	try{ 
            	PrintWriter out = response.getWriter();
 				//CommonFilter commonFilter = populateCommonFilter(request,"GridBasedTrgCalCommonFilter", false);

				    String TrainingKeyid = request.getParameter("trgCalId");
					CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
					List<String[]> uniqposList = gridBasedTrgCalService.getNewUniqPosData(TrainingKeyid);
				    JSONObject uniqposData = UIUtils.convertToJqGridTableObject( uniqposList, request, 1, 0);
				    out.println(uniqposData);
				 //   httpSession.removeAttribute("GridBasedTrgCalCommonFilter");
				//	httpSession.setAttribute("GridBasedTrgCalCommonFilter", commonFilter);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            }
            }
		   else if(action.equals("EmployeeAdd_input.gbtc")){
				
			  	//var ds="&flid="+flid+"&SectionId="+sectionId+"&refDocId="+refDocId+"&LocationId="+locaioniId+"&cellId="+cellId;

			   httpSession = request.getSession(false);
			   httpSession.getAttribute("CalKeyId");
			   
			   CommonMessage.debugMsg(httpSession.getAttribute("CalKeyId")+"  httpSession.getAttribute");
				String flid=request.getParameter("flid");
				String locnid=request.getParameter("LocationId");
				String sectionId=request.getParameter("SectionId");
				Object Calendarkeyid= httpSession.getAttribute("CalKeyId");//request.getParameter("refDocId");
				String cellId=request.getParameter("cellId");
				String TrainingId=request.getParameter("refDocId");
				String tradeId=request.getParameter("tradeId");
				String trainigType=request.getParameter("trngType");
				String mode=request.getParameter("mode");
				CommonMessage.debugMsg("The Calendarkeyid:::"+flid+"   "+sectionId);
				request.setAttribute("flid",flid);
				request.setAttribute("cellId",cellId);
				request.setAttribute("sectionId",sectionId);
				request.setAttribute("locnid",locnid);
				request.setAttribute("keyid",Calendarkeyid);
				request.setAttribute("hdnkeyid",TrainingId);
				request.setAttribute("tradeId", tradeId);
				request.setAttribute("trainigType", trainigType);
				UIUtils.forwardRequest(request, response,"pages/newENT/GridBasedTrgCalEmployeeAdd.jsp");
			}
		   
			else if (action.equals("EmployeeAdd_getCol.gbtc")) {
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", true);
			  //	var ds="&flid="+flid+"&SectionId="+sectionId+"&refDocId="+refDocId+"&LocationId="+locaioniId+"&cellId="+cellId;

				
				String flid=request.getParameter("flid");
				String dmtId=request.getParameter("SectionId");
				CommonMessage.debugMsg("The Flid"+flid);
				String jhId=request.getParameter("cellId");
				CommonMessage.debugMsg("The Role keyid"+jhId);
				//String role=request.getParameter("role");
				String UniqPosId=request.getParameter("UniqPos");
				CommonMessage.debugMsg("The pillarrole:::"+UniqPosId);
				String TrainingId=request.getParameter("refDocId");
				CommonMessage.debugMsg("the TrainingId"+TrainingId);
				String FunctId=request.getParameter("FunctId");
				String tradeId=request.getParameter("tradeId");
				String trainigType=request.getParameter("trngType");
			   
				
				commonFilter.setFlid(flid);
				commonFilter.setCellId(jhId);
				commonFilter.setSectionId(dmtId);
				commonFilter.setUniquePos(UniqPosId);
				commonFilter.setKey(TrainingId);
				commonFilter.setTrarId(FunctId);
				CommonMessage.debugMsg("The commonFilter..."+commonFilter.getKey());
				
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "selEmpPos"));
				httpSession.removeAttribute("NewuniqueGridCommonFilter");
				httpSession.setAttribute("NewuniqueGridCommonFilter", commonFilter);
			}
		   
			else if (action.equals("EmployeeAdd_getData.gbtc")) {
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
					PrintWriter out = response.getWriter();
					String flid=request.getParameter("flid");
					String dmtId=request.getParameter("SectionId");
					CommonMessage.debugMsg("The Flid"+flid);
					String jhId=request.getParameter("cellId");
					CommonMessage.debugMsg("The Role in  side the data keyid"+jhId);
					//String role=request.getParameter("role");
					String UniqPosId=request.getParameter("UniqPos");
					CommonMessage.debugMsg("The pillarrole:::"+UniqPosId);
					String TrainingId=request.getParameter("refDocId");
					CommonMessage.debugMsg("the TrainingId"+TrainingId); 
					String FunctId=null;
					FunctId=request.getParameter("FunctId");
					String tradeId=request.getParameter("tradeId");
					String trainigType=request.getParameter("trngType");
					CommonMessage.debugMsg(trainigType+" tradeId in servlet   "+tradeId);
					commonFilter.setType(trainigType);
					if(FunctId==null){
						commonFilter.setTrarId(tradeId);


					}
					else {
						commonFilter.setTrarId(FunctId);

					}
					
				//	commonFilter.setTrarId(tradeId);

					commonFilter.setFlid(flid);
					commonFilter.setCellId(jhId);
					commonFilter.setSectionId(dmtId);
					commonFilter.setUniquePos(UniqPosId);
					commonFilter.setKey(TrainingId);
					CommonMessage.debugMsg(commonFilter.getTrarId()+"The commonFilter..."+commonFilter.getKey());
					List<String[]> uniqueEmplist = gridBasedTrgCalService.getAllUniqueEmployeePopup(commonFilter);
					JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request, 1, 1,commonFilter.getTotalRecordCnt());
					out.println(uniquePOSEmp);
					httpSession.removeAttribute("NewuniqueGridCommonFilter");
					httpSession.setAttribute("NewuniqueGridCommonFilter",commonFilter);

				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			
//			else if(action.equals("EmpTrainingAtt_input.gbtc")){
//				httpSession = request.getSession(false);
//				   httpSession.getAttribute("CalKeyId");
//				   
//				   CommonMessage.debugMsg(httpSession.getAttribute("CalKeyId")+"  httpSession.getAttribute"); 
//				   //EmpTrainingAtt_input.gbtc
//					//var ds = "&TraKeyid=" + refDocId+"&markReqrd="+markReqrd+"&assmCompl="+assmCompl+"&flid="+flid+"&locaioniId="+locaioniId+"&sectionId="+sectionId+"&cellId="+cellId;
//
//					String flid=request.getParameter("flid");
//					String locationId=request.getParameter("locationId");
//					String sectionId=request.getParameter("sectionId");
//					// adding vignesh to  normalise sectionid
//					sectionId = (sectionId == null) ? "" : sectionId.trim();
//					if (sectionId.length() == 0 || "null".equalsIgnoreCase(sectionId)) sectionId = "";
//
//					CommonMessage.debugMsg(sectionId +"..... section id in The input..."+  sectionId);
//					String Calendarkeyid= request.getParameter("TraKeyid");//request.getParameter("refDocId");
//					String cellId=request.getParameter("cellId");
//					String topicId=request.getParameter("topicId"); 
//					String markReqrd=request.getParameter("markReqrd");
//					String assmCompl=request.getParameter("assmCompl");
//					String mode=request.getParameter("mode");
//					
//					//-------------------------- Vignesh 02Dec2025 ---------------------------------------------//
//				
//					// If TraKeyid wasn’t passed, fall back to session value
//					if (Calendarkeyid == null || Calendarkeyid.trim().isEmpty()) {
//					    Object s = (httpSession != null) ? httpSession.getAttribute("CalKeyId") : null;
//					    if (s != null) Calendarkeyid = String.valueOf(s);
//					}
//
//					// Backfill section/cell from the saved calendar master when they’re missing (company level)
//					if ((sectionId == null || sectionId.trim().isEmpty()) ||
//						    (cellId    == null || cellId.trim().isEmpty())) {
//						
//						if (Calendarkeyid == null || Calendarkeyid.trim().isEmpty()) {
//						    Object s = (httpSession != null) ? httpSession.getAttribute("CalKeyId") : null;
//						    if (s != null) Calendarkeyid = String.valueOf(s);
//						}
//						  try {
//						    EntTlTragcalmst mst = gridBasedTrgCalService.getselectdata(Calendarkeyid);
//						    if (mst != null) {
//						      String dmt = null, jh = null;
//						      try { dmt = mst.getEtcmDmt(); } catch (Exception ignore) {}
//						      try { jh  = mst.getEtcmJh();  } catch (Exception ignore) {}
//
//						      // Accept "{}" too — the JSP just needs a non-null token to proceed.
//						      if ((sectionId == null || sectionId.trim().isEmpty()) && dmt != null) {
//						        sectionId = dmt;   // may be "{}", that's OK for the front-end
//						      }
//						      if ((cellId == null || cellId.trim().isEmpty()) && jh != null) {
//						        cellId = jh;       // may be "{}", also OK
//						      }
//						    }
//						  } catch (Exception ignore) { /* keep going */ }
//						}
//
//
//
//					// (No changes below this point)
//					
//					//-------------------------- Vignesh 02Dec2025 ---------------------------------------------//
//
//					CommonMessage.debugMsg(mode+"   "+topicId+"  "+locationId+"The Calendarkeyid:::"+flid+"   "+sectionId+"  "+markReqrd);
//					
//					request.setAttribute("flid",flid);
//					request.setAttribute("cellId",cellId);
//					 if (sectionId.isEmpty()) sectionId = "{}";
//					request.setAttribute("sectionId",sectionId);
//					request.setAttribute("locnid",locationId);
//					request.setAttribute("keyid",Calendarkeyid);
//					request.setAttribute("markReqrd",markReqrd);
//					request.setAttribute("assmCompl",assmCompl);
//					request.setAttribute("topicId",topicId);
//					request.setAttribute("mode",mode);
//
//				 	EntTlTragcalmst entTlTragcalmst = new EntTlTragcalmst();
//
//					if("modify".equals(mode)||("view".equals(mode))){
//						
//			 	    entTlTragcalmst = gridBasedTrgCalService.getselectdata(Calendarkeyid);
//			 	   //////////////////////////////////////////
//			 	    String empcount=gridBasedTrgCalService.getempdata(Calendarkeyid);
//			 	    
//			 	    String empattn=gridBasedTrgCalService.getempattn(Calendarkeyid);
//			 	    
//			 	   CommonFilter commonFilter=new CommonFilter();
//			 	   String maxmarks = null;
//			 	  String Cuttoff = null;
//			 	 String cnt=null;
//				 String assesType=null;
//			 	    if(!empattn.equals("0"))
//			 	    {
//			 	    	commonFilter.setKey(Calendarkeyid);
//			 	    	commonFilter.setMaintMode("modify");
//			 	    	maxmarks=gridBasedTrgCalService.getMaxmarks(Calendarkeyid);
//			 	    	Cuttoff=gridBasedTrgCalService.getCutoff(Calendarkeyid);
//			 	    	
//			 	    	  assesType = gridBasedTrgCalService.getAssesType(Calendarkeyid);
//			 	    	  CommonMessage.debugMsg("servlet after api GETASSESMENT-------- " + assesType);
//			 	    	  
//			 	    	cnt = gridBasedTrgCalService.chkAssesmentComplted(commonFilter);
//			 	    	request.setAttribute("asscnt", Integer.parseInt(cnt));
//			 	    }
//			 	    request.setAttribute("empcount", Integer.parseInt(empcount));
//			 	    //////////////////////////////////////////////
//				 	request.setAttribute("entTlTragcalmst", entTlTragcalmst);
//				 	request.setAttribute("maxmarks" , maxmarks);
//				 	request.setAttribute("Cuttoff" , Cuttoff);
//				 	request.setAttribute("empattn", Integer.parseInt(empattn));
//				 	httpSession.setAttribute("entTlTragcalmst_Servlet", entTlTragcalmst);
//					}
//			 	CommonMessage.debugMsg("Entering jsp ");
//					UIUtils.forwardRequest(request, response,"pages/newENT/GridBasedTrgCalEmployeeAttendance.jsp");
//			}
            
            
            
			else if (action.equals("EmpTrainingAtt_input.gbtc")) {

			    httpSession = request.getSession(false);

			    // ✅ avoid NPE (blank page) if session expired
			    Object calKeyObj = (httpSession != null) ? httpSession.getAttribute("CalKeyId") : null;
			    

			    String flid       = request.getParameter("flid");
			    String locationId = request.getParameter("locationId");
			    String topicId    = request.getParameter("topicId");
			    String markReqrd  = request.getParameter("markReqrd");
			    String assmCompl  = request.getParameter("assmCompl");
			    String mode       = request.getParameter("mode");
			    String calDate = request.getParameter("calDate");
  
			    CommonMessage.debugMsg(calDate + " ---------+++++++++ caldate");
			    // ✅ read both names (some callers use SectionId)
			    String sectionId = request.getParameter("sectionId");
			    if (sectionId == null) sectionId = request.getParameter("SectionId");

			    String cellId = request.getParameter("cellId");

			    // ✅ normalize blanks / "null"
			    sectionId = (sectionId == null) ? "" : sectionId.trim();
			    if (sectionId.length() == 0 || "null".equalsIgnoreCase(sectionId)) sectionId = "";

			    cellId = (cellId == null) ? "" : cellId.trim();
			    if (cellId.length() == 0 || "null".equalsIgnoreCase(cellId)) cellId = "";

			    String Calendarkeyid = request.getParameter("TraKeyid");

			    // ✅ fallback TraKeyid from session
			    if (Calendarkeyid == null || Calendarkeyid.trim().isEmpty()) {
			        if (calKeyObj != null) Calendarkeyid = String.valueOf(calKeyObj);
			    }

			    // ✅ backfill from master when missing
			    if ((sectionId.isEmpty() || cellId.isEmpty()) && Calendarkeyid != null && !Calendarkeyid.trim().isEmpty()) {
			        try {
			            EntTlTragcalmst mst = gridBasedTrgCalService.getselectdata(Calendarkeyid);
			         
			            // --- adding new for date --//
			            
			          
			            
			            // --- adding new for date --//
			            
			            if (mst != null) {
			                if (sectionId.isEmpty()) {
			                    String dmt = mst.getEtcmDmt();
			                    if (dmt != null) sectionId = dmt.trim();
			                }
			                if (cellId.isEmpty()) {
			                    String jh = mst.getEtcmJh();
			                    if (jh != null) cellId = jh.trim();
			                }
			            }
			        } catch (Exception ignore) { }
			    }

			    // ✅ FINAL SAFETY RULE (THIS is what you want)
			    // sectionId must never be blank -> "{}"
			    if (sectionId.isEmpty()) sectionId = "{}";

			    // cellId must never be "{}" -> "" (attendance grid treats cell optional)
			    if ("{}".equals(cellId)) cellId = "";

			    CommonMessage.debugMsg("FINAL mode=" + mode + " keyid=" + Calendarkeyid
			            + " sectionId=" + sectionId + " cellId=" + cellId);

			    // ✅ set attributes (set both keys to avoid JSP mismatch)
			    request.setAttribute("flid", flid);
			    request.setAttribute("cellId", cellId);
			    request.setAttribute("sectionId", sectionId);
			    request.setAttribute("SectionId", sectionId); // extra safety
			    request.setAttribute("locnid", locationId);
			    request.setAttribute("keyid", Calendarkeyid);
			    request.setAttribute("markReqrd", markReqrd);
			    request.setAttribute("assmCompl", assmCompl);
			    request.setAttribute("topicId", topicId);
			    request.setAttribute("mode", mode);

			    	EntTlTragcalmst entTlTragcalmst = new EntTlTragcalmst();

								if("modify".equals(mode)||("view".equals(mode))){
									
						 	    entTlTragcalmst = gridBasedTrgCalService.getselectdata(Calendarkeyid);
						 	   //////////////////////////////////////////
						 	    String empcount=gridBasedTrgCalService.getempdata(Calendarkeyid);
						 	    
						 	    String empattn=gridBasedTrgCalService.getempattn(Calendarkeyid);
						 	    
						 	   CommonFilter commonFilter=new CommonFilter();
						 	   String maxmarks = null;
						 	  String Cuttoff = null;
						 	 String cnt=null;
							 String assesType=null;
						 	    if(!empattn.equals("0"))
						 	    {
						 	    	commonFilter.setKey(Calendarkeyid);
						 	    	commonFilter.setMaintMode("modify");
						 	    	maxmarks=gridBasedTrgCalService.getMaxmarks(Calendarkeyid);
						 	    	Cuttoff=gridBasedTrgCalService.getCutoff(Calendarkeyid);
						 	    	
						 	    	  assesType = gridBasedTrgCalService.getAssesType(Calendarkeyid);
						 	    	  CommonMessage.debugMsg("servlet after api GETASSESMENT-------- " + assesType);
						 	    	  
						 	    	cnt = gridBasedTrgCalService.chkAssesmentComplted(commonFilter);
						 	    	request.setAttribute("asscnt", Integer.parseInt(cnt));
						 	    }
						 	    request.setAttribute("empcount", Integer.parseInt(empcount));
						 	    
							 	request.setAttribute("entTlTragcalmst", entTlTragcalmst);
							 	request.setAttribute("maxmarks" , maxmarks);
							 	request.setAttribute("Cuttoff" , Cuttoff);
							 	request.setAttribute("assesType", assesType);
							 	
							 	request.setAttribute("empattn", Integer.parseInt(empattn));
							 	request.setAttribute("calDate", calDate);
							 	httpSession.setAttribute("entTlTragcalmst_Servlet", entTlTragcalmst);
								}
						 	CommonMessage.debugMsg("Entering jsp ");
								UIUtils.forwardRequest(request, response,"pages/newENT/GridBasedTrgCalEmployeeAttendance.jsp");
			}

			
			else if(action.equals("EmpTrainingAtt_getCol.gbtc")){
			    PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",true);
				String MasterId=request.getParameter("TraKeyid");
				String Mark=request.getParameter("Mark");
				String Assess=request.getParameter("Asse");
				CommonMessage.debugMsg("Mark"+Mark+"Assess"+Assess);
				String TrainingId=request.getParameter("refDocId");

				GridParams gridParams=new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				commonFilter.setKey(MasterId);
				Mark="Y";
				Assess="Y";
				if(Mark.equals("Y") && Assess.equals("Y"))
				{
			    CommonMessage.debugMsg("Inside if");
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "EmpAttendance"));			
				}
				else{
				    CommonMessage.debugMsg("Inside Else");
			        out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "EmpAttendanceMarks"));
				}
			//	List<String[]> emplistData = newTrainingcalendarService.getAllEmployee(commonFilter,gridParams);
			//	JSONObject jsonObject = getTableModel(emplistData);
				httpSession.removeAttribute("NewEmployeeCommonFilter");
				httpSession.setAttribute("NewEmployeeCommonFilter", commonFilter);
				httpSession.removeAttribute("NewEmployeeColmodel");
			//	httpSession.setAttribute("NewEmployeeColmodel", jsonObject);
		 }
			else if (action.equals("EmpTrainingAtt_getData.gbtc")) {
				try {
					CommonFilter commonFilter = new CommonFilter();
					PrintWriter out = response.getWriter();
					String MasterId = request.getParameter("TraKeyid");
					String TrainingId=request.getParameter("refDocId");
					String caldate = request.getParameter("calDate");
					/*String mode=request.getParameter("mode");
					commonFilter.setSect(mode);*/
					CommonMessage.debugMsg("The MasterId:::"+MasterId);
					commonFilter.setKey(MasterId);
				 commonFilter = populateCommonFilter(request,"NewEmployeeCommonFilter",false);
				 GridParams gridParams=new GridParams();
				 FilterValues.populateGridParams(request,gridParams);
				 List<String[]> emplistData = gridBasedTrgCalService.getAllEmployee(commonFilter,gridParams);
			     JSONObject emplist = UIUtils.convertToJqGridTableObject(emplistData, request,1,0,gridParams.getTotalRecordCnt());			 
				 out.println(emplist);
				}
			 catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			 else if(action.equals("GrdBsdsession_getCol.gbtc")){
				 try {
						String TrgcalKeyid = request.getParameter("trgCalId");
						String mode = request.getParameter("mode");
						CommonMessage.debugMsg("The TrgcalKeyid::::"+TrgcalKeyid);
						List<String[]> sessionList = gridBasedTrgCalService.getsession(TrgcalKeyid);
						JSONObject jsonObject = getsessionTableModel(sessionList);
						CommonMessage.debugMsg("Table model");
						CommonMessage.debugMsg("jsonObject "+jsonObject);
						PrintWriter  out = response.getWriter();
						out.println(jsonObject);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 else if(action.equals("GrdBsdsession_getData.gbtc")){
				 PrintWriter out = response.getWriter();
				 String TrgcalKeyid = request.getParameter("trgCalId");
				 CommonMessage.debugMsg("The TrgcalKeyid"+TrgcalKeyid);
				 List<String[]> sessionList = gridBasedTrgCalService.getsession(TrgcalKeyid);
				 JSONObject sessiondata = UIUtils.convertToJqGridTableObject( sessionList, request, 1, 0);
				 out.println(sessiondata);
			 }

			 else if(action.equals("uniquePositionLink_getCol.gbtc")){
				 try {
					    String TrainingKeyid = request.getParameter("trgCalId");
						CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
						List<String[]> facultyList = gridBasedTrgCalService.getNewUniqPosData(TrainingKeyid);
						JSONObject jsonObject = getUniquePositionTableModel(facultyList);
						CommonMessage.debugMsg("Table model");
						CommonMessage.debugMsg("jsonObject "+jsonObject);
						PrintWriter  out = response.getWriter();
						out.println(jsonObject);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 } else if(action.equals("uniquePositionLink_getData.gbtc")){
				    PrintWriter out = response.getWriter();
				    String TrainingKeyid = request.getParameter("trgCalId");
					CommonMessage.debugMsg("The TrainingKeyid"+TrainingKeyid);
					List<String[]> uniqposList = gridBasedTrgCalService.getNewUniqPosData(TrainingKeyid);
				    JSONObject uniqposData = UIUtils.convertToJqGridTableObject( uniqposList, request, 1, 0);
				    out.println(uniqposData);
			 }
			 else if(action.equals("Faculty_getCol.gbtc")){
				   try {
					    String progKeyid = request.getParameter("trgCalId");
					    CommonMessage.debugMsg("The progKeyid"+progKeyid);
						List<String[]> facultyList = gridBasedTrgCalService.getFaculty(progKeyid);
						JSONObject jsonObject = getfacultyTableModel(facultyList);
						CommonMessage.debugMsg("Table model");
						CommonMessage.debugMsg("jsonObject "+jsonObject);
						PrintWriter  out = response.getWriter();
						out.println(jsonObject);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
			   
			       else if(action.equals("Faculty_getData.gbtc")){
				 try{
				    PrintWriter out = response.getWriter();
				    String progKeyid = request.getParameter("trgCalId");
				    CommonMessage.debugMsg("the progKeyid"+progKeyid);
					 List<String[]> facultyList = gridBasedTrgCalService.getFaculty(progKeyid); // vignesh colstart to 1
					 JSONObject facultydata = UIUtils.convertToJqGridTableObject( facultyList, request, 1, 1);
					 out.println(facultydata);
			       } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			 else if(action.equals("gridBasedCalendar_save.gbtc")){
				 CommonMessage.debugMsg(" In side the save method"); 
				 TrainingCalSave( request, response) ;
			 }
	  
			 else if(action.equals("GridBasedCalSession_save.gbtc")){
				 CommonMessage.debugMsg(" In side the save method"); 
				 TrainingCalSessionSave( request, response) ;
				 
				
			 }
            
			 else if(action.equals("chkSessionDate.gbtc")){
				 PrintWriter out = response.getWriter();
				 String keyid=request.getParameter("keyid");
				 String sessiondate=request.getParameter("sedte");
				 CommonMessage.debugMsg("The sessiondate"+sessiondate);
				 String frmtime=request.getParameter("frmtme");
				 CommonMessage.debugMsg("The frmtime"+frmtime);
				 String totime=request.getParameter("totme"); 
				 CommonMessage.debugMsg("The totime"+totime);
				 String sessionid=request.getParameter("sesid");
				 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
				 commonFilter.setKey(keyid);
				 commonFilter.setDteOccuredto(sessiondate);
				 commonFilter.setDteAllotedfrm(frmtime);
				 commonFilter.setDteAllotedto(totime);
				 
				 String session  = gridBasedTrgCalService.chkSessionDate(commonFilter);
				 JSONObject json=new JSONObject();
				json.put("sessioncnt", Integer.parseInt(session));
				json.put("sessiondate", sessiondate);
				json.put("keyid", keyid);
				json.put("frmtime", frmtime);
				json.put("totime", totime);
				json.put("sessionid", sessionid);
				out.println(json);
			 }

			 else if(action.equals("chkUniQupostion.gbtc")){
				 PrintWriter out = response.getWriter();
				 String keyid=request.getParameter("keyid");
				 String Upid=request.getParameter("Upid");
				 String uniqukeyid=request.getParameter("uniqukeyid");
				 
				 String chkuniq=request.getParameter("chkuni");
				 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
				 
				 CommonMessage.debugMsg(" IN side the servlet");
				 commonFilter.setKey(keyid);
				 commonFilter.setUtil(Upid);
				
				 commonFilter.setKK(uniqukeyid);
				 commonFilter.setCellch(chkuniq);
				 
				 
				 String uniqueposition = gridBasedTrgCalService.chkUniqueposition(commonFilter);
				 JSONObject json=new JSONObject();
				json.put("uniquecnt", Integer.parseInt(uniqueposition));
				json.put("chkuniq", chkuniq);
				json.put("keyid", keyid);
				json.put("Upid", Upid);
				json.put("uniqukeyid", uniqukeyid);
			
				out.println(json);
				
			 }  
			 else if(action.equals("FacultyCheck.gbtc")){
       	 PrintWriter out = response.getWriter();
		 String keyid=request.getParameter("keyid");
		 String faucltyid=request.getParameter("faclid");
		 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
		 commonFilter.setKey(keyid);
		 commonFilter.setEmpch(faucltyid);
		 String faculty  = gridBasedTrgCalService.FacultyCheck(commonFilter);
		 JSONObject json=new JSONObject();
		json.put("fcltycnt", Integer.parseInt(faculty));
		json.put("fcltyid", faucltyid);
		json.put("keyid", keyid);
		out.println(json);
	   }
			 else if(action.equals("chkcompleted.gbtc")){
				 PrintWriter out = response.getWriter();
				 String keyid=request.getParameter("keyid");
				 CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
				 commonFilter.setKey(keyid);
				 String TrainingData  = gridBasedTrgCalService.IsTrainingCompleted(commonFilter);
				 JSONObject json=new JSONObject();
				json.put("iscomplete", TrainingData);
				out.println(json);
				
			 }
			 else if(action.equals("GridBasedUniquePosition_save.gbtc")){
				 
				 
				// TODO Auto-generated method stub
					 httpSession = request.getSession(false);
				    ServletOutputStream out = response.getOutputStream();
				  //  ds= "updmt="+updmt+"&upJh="+upJh+"&uniqid="+uniqkeyid;

				    String keyid=request.getParameter("keyid");
					 String Upid=request.getParameter("Upid");
					 String uniqukeyid=request.getParameter("uniqukeyid");
					 String chkuniq=request.getParameter("chkuni");
					 String roleDmt=request.getParameter("updmt");
					 String roleJh=request.getParameter("upJh");
					 String uniqPosId=request.getParameter("uniqid");
				  String sesId=request.getParameter("sesId");
				//String GridList=request.getParameter("paramJsonArrConvert");
					String dateTime = CommonFunctions.pg_dateTimeNow();
					CommonMessage.debugMsg(roleJh+"  roleDmtroleDmt 1 keyid "+keyid);
				String savemsg=null;
				String updateMsg=null;
				//EntTlTragcalmst newentTlTragcalmst = (EntTlTragcalmst)httpSession.getAttribute("entTlTragcalmst");

				try{
					
				if( httpSession != null && user != null)
				{	
					
					EntTlTrgCalUnqp newentTlTrgCalUnqp=new EntTlTrgCalUnqp();	
					EntTlTrgCalUnqp existntTlTrgCalUnqp=new EntTlTrgCalUnqp();						
					CommonMessage.debugMsg(roleJh+"  roleDmtroleDmt 2 keyid "+keyid);

					if(keyid!=null)
					  	    {
						newentTlTrgCalUnqp.setEtcuEtcmKeyid(keyid);
					  	    }
					//EntTlTrgCalUnqp existentTlTrgCalUnqp = (EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
											
						//newentTlTrgCalUnqp = (EntTlTrgCalUnqp)UIUtils.setBeanProperties((Object)newentTlTrgCalUnqp,request);
						
						
						newentTlTrgCalUnqp.setEtcuCreatedby(user.getUsrm_ccno());
						EntTlTrgCalUnqp existentTlTrgCalUnqp=(EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
					
					//	newentTlTragcalmst.setEtcmCreatedBy(user.getUsrm_ccno());
						//newentTlTragcalmst =(EntTlTragcalmst)UIUtils.setBeanProperties((Object)newentTlTragcalmst,request);
						//  CommonMessage.debugMsg(fromdate +" 2 In side the servelet "+todate+"   "+calendardate);
					 		 newentTlTrgCalUnqp.setEtcuRoleDmt(roleDmt);
					 		 newentTlTrgCalUnqp.setEtcuRoleJh(roleJh);
					 		newentTlTrgCalUnqp.setEtcuDateAdd(dateTime);
					 		newentTlTrgCalUnqp.setEtcuRoleKeyid(uniqPosId);
					 		CommonMessage.debugMsg(newentTlTrgCalUnqp.getEtcuEtcmKeyid()+"   In side the Servlet "+newentTlTrgCalUnqp.getEtcuRoleDmt());
						    			     
					 	  if(sesId==null){
					 		 newentTlTrgCalUnqp =	gridBasedTrgCalService.createUniquePostion(newentTlTrgCalUnqp,existntTlTrgCalUnqp);
								 savemsg="Data Saved Successfully";
						}
						else{  
							     
							existntTlTrgCalUnqp= gridBasedTrgCalService.updateUniquePostion(newentTlTrgCalUnqp,existntTlTrgCalUnqp);
								 savemsg="Data Updated Successfully";	
						}
						   httpSession.setAttribute("TraKeyAfterSave", newentTlTrgCalUnqp.getEtcuEtcmKeyid());
				
				
				JSONObject successData = new JSONObject();
				successData.put("msg",savemsg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
			//	returnData.put("TraCalId",newentTlTrgCalSession.getEtcsEtcmKeyid());
			//	returnData.put("TraCreateTime",existEntTlTragcalmst.getEtcmCreatedDateTime());
				//returnData.put("savemode",savemode);
				returnData.put("formClear", false);
			//	httpSession.setAttribute("entTlTragcalmst", existEntTlTragcalmst);
				out.print(returnData.toString());
				
											
			  }
				}
				catch (ValidationExceptions e) {
					net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewTrainingCalendar");
					out.print(errMessage.toString());
				}
				catch(Exception e){
					e.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
			}
			}
            
            
			 else if(action.equals("GridbasedFaculty_save.gbtc")){
				
					// TODO Auto-generated method stub
						 httpSession = request.getSession(false);
					    ServletOutputStream out = response.getOutputStream();
					  //  ds= "updmt="+updmt+"&upJh="+upJh+"&uniqid="+uniqkeyid;

					    String keyid=request.getParameter("MasterKeyid");
						 String facultyId=request.getParameter("Facultyid");
						System.out.print("keyidkeyidkeyid"+keyid);
					//String GridList=request.getParameter("paramJsonArrConvert");
						String dateTime = CommonFunctions.dateTimeNow();
					String savemsg=null;
					String updateMsg=null;
					//EntTlTragcalmst newentTlTragcalmst = (EntTlTragcalmst)httpSession.getAttribute("entTlTragcalmst");

					try{
						EntTlTrgFaculty newEntTlTrgFaculty=new EntTlTrgFaculty();	
						EntTlTrgFaculty existEntTlTrgFaculty=new EntTlTrgFaculty();						

					if( httpSession != null && user != null)
					{	
						
					
						if(keyid!=null)
						  	    {
							newEntTlTrgFaculty.setEtcfEtcmKeyid(keyid);
						  	    }
						//EntTlTrgCalUnqp existentTlTrgCalUnqp = (EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
												
						newEntTlTrgFaculty = (EntTlTrgFaculty)UIUtils.setBeanProperties((Object)newEntTlTrgFaculty,request);
							
							
						newEntTlTrgFaculty.setEtcfCreatedby(user.getUsrm_ccno());
						newEntTlTrgFaculty.setEtcfFacultyId(facultyId);
							 existEntTlTrgFaculty=(EntTlTrgFaculty)httpSession.getAttribute("entTlTrgFaculty");
							
						//	newentTlTragcalmst.setEtcmCreatedBy(user.getUsrm_ccno());
							//newentTlTragcalmst =(EntTlTragcalmst)UIUtils.setBeanProperties((Object)newentTlTragcalmst,request);
							//  CommonMessage.debugMsg(fromdate +" 2 In side the servelet "+todate+"   "+calendardate);
						 		 
						 	  
							 newEntTlTrgFaculty =	gridBasedTrgCalService.createFaculty(newEntTlTrgFaculty,existEntTlTrgFaculty);
									 savemsg="Data Saved Successfully";
							}
							else{  
								     
								newEntTlTrgFaculty= gridBasedTrgCalService.updateFaculty(newEntTlTrgFaculty,existEntTlTrgFaculty);
									 savemsg="Data Updated Successfully";	
							}
							  
					
					
					JSONObject successData = new JSONObject();
					successData.put("msg",savemsg);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
				//	returnData.put("TraCalId",newentTlTrgCalSession.getEtcsEtcmKeyid());
				//	returnData.put("TraCreateTime",existEntTlTragcalmst.getEtcmCreatedDateTime());
					//returnData.put("savemode",savemode);
					returnData.put("formClear", false);
				//	httpSession.setAttribute("entTlTragcalmst", existEntTlTragcalmst);
					out.print(returnData.toString());
					
												
				  }
					
					catch (ValidationExceptions e) {
						net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewTrainingCalendar");
						out.print(errMessage.toString());
					}
					catch(Exception e){
						e.printStackTrace();
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
				}
				}
            
			 else if(action.equals("uniquePostn.gbtc"))
				{
					
				 CommonFilter  commonFilter=new CommonFilter();
				 ComboFilter currentFilter = new ComboFilter();//UIUtils.fillComboFilter(request);
					commonFilter.setRoleId(currentFilter);				
				//	comboList = gridBasedTrgCalService.getRoleComboList(commonFilter);*/
					String sectionId = request.getParameter("sectionid");
					String cellId = request.getParameter("cellId");
					String flid = request.getParameter("flid");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
			
					CommonMessage.debugMsg(" flid :: Checking 1234 :: CommonFilterServlet :: "+flid);
					if (UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					String childFlids = request.getParameter("childFlids");
					commonFilter.setType("Y");
					if (UIUtils.isValidKeyId(childFlids))
						commonFilter.setType(childFlids);
					
					//commonFilter.setKey(keyId);
					commonFilter.setSectionId(sectionId);
					commonFilter.setKK(loginLocnId);
					commonFilter.setCellId(cellId);
					
					CommonMessage.debugMsg("flidRoles:::"+commonFilter.getFlid());
					currentFilter=UIUtils.fillComboFilter(request);
					commonFilter.setRoleId(currentFilter);
					List<ComboBox> comboList = new ArrayList<ComboBox>();

					comboList = gridBasedTrgCalService.getRoleComboList(commonFilter);
					currentFilter=UIUtils.fillComboFilter(request);
					UIUtils.writeComboBox(response,comboList,currentFilter);//writeCombo(response, comboList);
			
		
				}
			 else if(action.equals("MultipleUniqueAdd_input.gbtc")){
				 String Calendarflid=request.getParameter("Calendarflid");
				 CommonMessage.debugMsg("Functional Location"+Calendarflid);
				 String CalendarId=request.getParameter("CalendarId");
				 CommonMessage.debugMsg("CalendarId"+CalendarId);
				 String SectionId=request.getParameter("sectionId");
				 CommonMessage.debugMsg("SectionId"+SectionId);
				 String cellId=request.getParameter("cellId");
				 CommonMessage.debugMsg("cellId"+cellId);
				// String JHFlid=newTrainingcalendarService.getJHFlid(Calendarflid);
				// CommonMessage.debugMsg("JHFlid"+JHFlid);
	            // request.setAttribute("JHFlid",JHFlid);
	             request.setAttribute("CalendarId",CalendarId);
	             request.setAttribute("Calendarflid",Calendarflid);
	             request.setAttribute("SectionId",SectionId);
	             request.setAttribute("cellId",cellId);
				 UIUtils.forwardRequest(request, response,"pages/newENT/GridBasedMultipleUniqAdd.jsp");
			 }
		   
			 else if (action.equals("MultipleUniqueAdd_getCol.gbtc")) {
					PrintWriter out = response.getWriter();
					CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", true);
					String Calendarflid=request.getParameter("Calendarflid");
					CommonMessage.debugMsg("The Flid"+Calendarflid);
					String CalendarId=request.getParameter("CalendarId");
					String sectionId=request.getParameter("SectionId");
					String cellId=request.getParameter("cellId");

					CommonMessage.debugMsg("CalendarId"+CalendarId);
					commonFilter.setFlid(Calendarflid);
					commonFilter.setSectionId(sectionId);
					commonFilter.setCellId(cellId);
					commonFilter.setKey(CalendarId);
					CommonMessage.debugMsg("The commonFilter"+commonFilter);
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GridBasedTRGCalendar", "AddMultipleUniquePos"));
					httpSession.removeAttribute("NewuniqueGridCommonFilter");
					httpSession.setAttribute("NewuniqueGridCommonFilter", commonFilter);
				}
		   
			 else if (action.equals("MultipleUniqueAdd_getData.gbtc")) {
					try {
						CommonFilter commonFilter = populateCommonFilter(request,"NewuniqueGridCommonFilter", false);
						PrintWriter out = response.getWriter();
						String Calendarflid=request.getParameter("Calendarflid");
						CommonMessage.debugMsg("The Flid"+Calendarflid);
						String CalendarId=request.getParameter("CalendarId");
						String filters= request.getQueryString().toString();  //getParameter("filters");
						String sectionId=request.getParameter("SectionId");
						String cellId=request.getParameter("cellId");

						CommonMessage.debugMsg("CalendarId"+CalendarId);
						//commonFilter.setFlid(Calendarflid);
						commonFilter.setSectionId(sectionId);
						commonFilter.setCellId(cellId);
						//Object object= JSONValue.parse(filters);
						
						//JSONObject jobject=(JSONObject)object;
						//String filterData=(String)jobject.get("data");
						CommonMessage.debugMsg(filters +"   CalendarId"+CalendarId);
						commonFilter.setFlid(Calendarflid);
						commonFilter.setKey(CalendarId);
						commonFilter.getGridFilter();
						List<String[]> uniqueEmplist = gridBasedTrgCalService.gwtJHRoleUniquePos(commonFilter);
						JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request,0,0,commonFilter.getTotalRecordCnt());
						out.println(uniquePOSEmp);
						httpSession.removeAttribute("NewuniqueGridCommonFilter");
						httpSession.setAttribute("NewuniqueGridCommonFilter",commonFilter);

					} catch (Exception e) {
						CommonMessage.debugMsg(e.getMessage());
					}
				}
			 else if(action.equals("MultipleUniquePosition_save.gbtc")){
				 
				 HttpSession httpsession=request.getSession(false);
				    ServletOutputStream out=response.getOutputStream();
				    user=UIUtils.getLoginUser(request);
				    String saveMsg=null;
				    try{
				    	    if(httpsession!=null && user!=null){
				    	    	
				    		String paramJsonArr=request.getParameter("paramconvertArr");
				    		CommonMessage.debugMsg("paramJsonArr"+paramJsonArr);
				    		String CalendarId=request.getParameter("CalendarId");
				    		String SectionId=request.getParameter("SectionId"); 
				    		CommonMessage.debugMsg("SectionId"+SectionId);
				    		String CellId=request.getParameter("CellId");
				    		CommonMessage.debugMsg("CellId"+CellId);
				    		
				    		EntTlTrgCalUnqp entTlTrgCalUnqp=new EntTlTrgCalUnqp();
				    		
				    		JSONArray UniqueList=null; 
				    		List<EntTlTrgCalUnqp> UniqueAddList = null;	
				    		if(UIUtils.isValidKeyId(paramJsonArr)){
				    			UniqueList=JSONArray.fromString(paramJsonArr);
				    		    CommonMessage.debugMsg("UniqueList"+UniqueList);
				    		    UniqueAddList=(List<EntTlTrgCalUnqp>)UIUtils.convertJSONArrToList(entTlTrgCalUnqp,UniqueList);		    		   
				    			int j;
				    			for(j=0;j<UniqueAddList.size();j++)
				    			{
				    				
				    				if(UniqueAddList.get(j).getEtcuEtcmKeyid()==null)
					    			{
				    					UniqueAddList.get(j).setEtcuEtcmKeyid(CalendarId);
					    			}
				    			/*	if(UniqueAddList.get(j).getEtcuRoleDmt()==null)
					    			{
				    					UniqueAddList.get(j).setEtcuRoleDmt(SectionId);
					    			}
				    				if(UniqueAddList.get(j).getEtcuRoleJh()==null){
				    					UniqueAddList.get(j).setEtcuRoleJh(CellId);	
				    				}*/
				    				
				    				UniqueAddList.get(j).setEtcuCreatedby(user.getUsrm_ccno());
				    			}
				    			UniqueAddList=gridBasedTrgCalService.CreateMultipleUnique(UniqueAddList);
				    			saveMsg="Data Saved Successfully";
				    		}
				    	    }
				    	    JSONObject SuccessData=new JSONObject();
					    	SuccessData.put("msg",saveMsg);
					    	SuccessData.put("formClear",false);
					    	JSONObject returnData=new JSONObject();
					    	returnData.put("successData", SuccessData);	
					    	returnData.put("formClear",false);
					    	out.print(returnData.toString());
					    	out.close();
				    	}
				    
				    catch (Exception e) {
						e.printStackTrace();
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
					}   
				}
			 
            
			 else if (action.equals("EmployeeAttendance_save.gbtc")) {
				 
				try{
					
						String cutoff=request.getParameter("cutOffMrk");
						String max=request.getParameter("maxMark");
						String typ=request.getParameter("type");
						String masterid=request.getParameter("keyId");
						String topicid=request.getParameter("topicId");
						String locnid=request.getParameter("locationid");
						String flid=request.getParameter("flid");
						String assess=request.getParameter("assCom");
						String GridList=request.getParameter("paramconvertArr");
						String updateMsg="";
						
						CommonMessage.debugMsg(GridList +"GridListGridList");
						PrintWriter out= response.getWriter();
						if(assess==null)
						{
							assess="N";
						}
						CommonFilter commonfilter=new CommonFilter();
						commonfilter.setKey(masterid);
						commonfilter.setFlid(flid);
						commonfilter.setLossId(locnid);
						commonfilter.setTopicid(topicid);//user.getUsrm_ccno();
						commonfilter.setChkExternal(user.getUsrm_ccno());
						EntTlTtgCalEmpatScore EmployeeAttendancelink= new EntTlTtgCalEmpatScore();
						EmployeeAttendancelink.setEtcaCreatedby(user.getUsrm_ccno());
						JSONArray EmployeeAttendanceList=null;
						if(UIUtils.isValidKeyId(GridList)){
						  EmployeeAttendanceList=JSONArray.fromString(GridList);
						   List<EntTlTtgCalEmpatScore> EmployeeAddList=(List<EntTlTtgCalEmpatScore>)UIUtils.convertJSONArrToList(EmployeeAttendancelink,EmployeeAttendanceList);		    		   
						   
						   int i=0;
						   for(i=0;i<EmployeeAddList.size();i++)
						   {
							   EmployeeAddList.get(i).setEtcaCutOff(cutoff);
							   EmployeeAddList.get(i).setEtcaMaxMarks(max);
							   EmployeeAddList.get(i).setEtcaType(typ);
							   EmployeeAddList.get(i).setEtcaAssessmentCom(assess);
							   EmployeeAddList.get(i).setEtcaCreatedby(user.getUsrm_ccno());			  
						   }
						 
						   EmployeeAddList=gridBasedTrgCalService.createEmployeeAttendance(EmployeeAddList,commonfilter);  
						   String empattn=gridBasedTrgCalService.getempattn(masterid);
						   updateMsg="Data Saved ...Successfully";
						   
						   
						    JSONObject persistentData=new JSONObject();
						   
						    JSONObject SuccessData=new JSONObject();
					    	SuccessData.put("msg",updateMsg);
					    	JSONObject forwardData=new JSONObject();
					    	forwardData.put("formClear", false);
					    	forwardData.put("persistentData", persistentData);

					    	forwardData.put("successData", SuccessData);
					    	forwardData.put("TraCalId",masterid);
					    	forwardData.put("savemode","Employee Attendance");
					    	forwardData.put("TraCalAttId",empattn);
					    	out.print(forwardData.toString());
						}
				}catch(Exception e){
							e.printStackTrace();
						}
						
		}
            
			 else if(action.equals("detailsFacUniSess_Delete.gbtc")){
				 
				 PrintWriter out = response.getWriter();
			     String keyId = request.getParameter("keyid");
			     CommonMessage.debugMsg("The keyId"+keyId);
				 String gridId = request.getParameter("gridId");
				 CommonMessage.debugMsg("The gridId"+gridId);
				 String TrainingId = request.getParameter("TraKeyid");
				 CommonMessage.debugMsg("The TrainingId"+TrainingId);
				 try{
				 String delSuc =gridBasedTrgCalService.deleteDetailRecord(keyId,gridId,TrainingId);
				 JSONObject json = new JSONObject();
				 json.put("msg",delSuc);
				 json.put("gridid",gridId);
					 out.print(json.toString());
				}catch(BusinessApplicationExceptions e)
					{	
						JSONObject successData = new JSONObject();
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
						successData.put("gridId",gridId);
						JSONObject returnData = new JSONObject();
						returnData.put("formClear",false);
						returnData.put("msg"," Record Can't be Deleted Reference Found");
						out.print(returnData.toString());
					}
					}
			
          
			 else if(action.equals("calendar_Delete.gbtc")){

					CommonMessage.debugMsg("Servlet Remove:");
					PrintWriter out = response.getWriter();
					String keyid = request.getParameter("keyid");
					CommonMessage.debugMsg("KEYID: "+keyid);
					try{
						if(UIUtils.isValidKeyId(keyid)){
							gridBasedTrgCalService.DeleteCal(keyid);
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
			 else if(action.equals("topic_fillcombo.gbtc")){
				 try {
						CommonMessage.debugMsg("TOPIC---mmmmmas");
						ComboFilter	comboFilter = UIUtils.fillComboFilter(request);
						String roleid = request.getParameter("roleId");
						String flid = request.getParameter("flId");
						String relTo = request.getParameter("relTo");
						String topicType = request.getParameter("topicType");
						//String location=CommonFunctions.getLoginLocaton(request);
						String location=request.getParameter("locnid");
						CommonMessage.debugMsg("location::::"+location);
						//relTo="GT";
						CommonMessage.debugMsg("relTo " +relTo);
						CommonFilter commonFilter = new CommonFilter();
						commonFilter.setFlid(flid);
						commonFilter.setLoss(location);
						commonFilter.setRange(roleid);//for Sending role id in commonfilter
						commonFilter.setRelatedToMchMld(topicType);//for Sending relatedtoTopic id in commonfilter
						List<ComboBox> topicData = gridBasedTrgCalService.getTopic(commonFilter,comboFilter);
						UIUtils.writeComboBox(response,topicData,comboFilter);
						CommonMessage.debugMsg("mTOPIC");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			 }
            
			 else if(action.equals("uniquePos_fillcombo.gbtc")){
				 try {
						CommonMessage.debugMsg("TOPIC---mmmmmas");
						ComboFilter	comboFilter = UIUtils.fillComboFilter(request);
						String calKeyid = request.getParameter("calId");
								
						CommonFilter commonFilter = new CommonFilter();
						commonFilter.setKey(calKeyid);
						
						List<ComboBox> uniquePost = gridBasedTrgCalService.getUniquePosition(commonFilter,comboFilter);
						UIUtils.writeComboBox(response,uniquePost,comboFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			 }
            
			 else if(action.equals("faculty_fillcombo.gbtc")){
				 try {
						CommonMessage.debugMsg("TOPIC---mmmmmas");
						ComboFilter	comboFilter = UIUtils.fillComboFilter(request);
						String calKeyid = request.getParameter("calId");
								
						CommonFilter commonFilter = new CommonFilter();
						commonFilter.setKey(calKeyid);
						
						List<ComboBox> uniquePost = gridBasedTrgCalService.getFaculty(commonFilter,comboFilter);
						UIUtils.writeComboBox(response,uniquePost,comboFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			 }
				 
			 }

		
		   private CommonFilter populateCommonFilter(HttpServletRequest request,
					String string, boolean b){
			       
			  HttpSession httpSession = request.getSession(false);

				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(string);
				if( commonFilter != null && ! b ){
					FilterValues.setPaginationParams(request,commonFilter);
				}	
				else{
					commonFilter =  new CommonFilter();
					commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
					commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
					commonFilter.setViewClick('Y');
					httpSession.removeAttribute(string);
					httpSession.setAttribute(string, commonFilter);
				}
				return commonFilter;
			}
		   
		   private JSONObject getsessionTableModel(List<String[]> batchList) {
				// TODO Auto-generated method stub
		       JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
				
				String[] colHeader = batchList.get(0);		
				 
				jqGridTableModel.getRowHeaders().add(colHeader);
				jqGridTableModel.setRowNumbers(false);
				jqGridTableModel.setTableHeight(350);  
				jqGridTableModel.setTableWidth(300);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setRowNumbers(true);
				for (int i = 0; i < colHeader.length; i++) {
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
					jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
					jqGridColModel.setWidth(110);
					jqGridColModel.setAlign("left");
					jqGridColModel.setEditable(false);	 
					
					if (i <=3 ) {
						jqGridColModel.setHidden(true);
						jqGridColModel.setWidth(100);
						jqGridColModel.setAlign("left");
					}
					
					if(i == 1)  {
						jqGridColModel.setHidden(true);
						jqGridColModel.setWidth(100);
						jqGridColModel.setAlign("left");
					}
					if(i == 2)  {
						jqGridColModel.setHidden(false);
						jqGridColModel.setWidth(80);
						jqGridColModel.setAlign("left");
					}
					
					if(i == 3)  {
						jqGridColModel.setHidden(false);
						jqGridColModel.setWidth(60);
						jqGridColModel.setAlign("left");
					}

					if(i == 4)  {
						jqGridColModel.setHidden(false);
						jqGridColModel.setWidth(60);
						jqGridColModel.setAlign("left");
					}
					if(i == 5 )  {
						jqGridColModel.setHidden(false);
						jqGridColModel.setWidth(50);
						jqGridColModel.setAlign("left");
					}
					
					/*if(i==colHeader.length-2){
						jqGridColModel.setFormatter("btnEmpFormater");
						jqGridColModel.setWidth(100);
						jqGridColModel.setAlign("center");
					}*/
					if(i==colHeader.length-1){
						jqGridColModel.setFormatter("BtnFormatterDelete");
						jqGridColModel.setWidth(50);
						jqGridColModel.setAlign("center");
					}
					 
					jqGridTableModel.getColModel().add(jqGridColModel);
				}

				JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
				tableModel.set("tableHeight", "40%%");
				tableModel.set("tableWidth", "30%%");
				return tableModel;
			}
		   private JSONObject getfacultyTableModel(List<String[]> facultyList) {
				// TODO Auto-generated method stub
		   	    JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
				String[] colHeader = facultyList.get(0);		
				 
				jqGridTableModel.getRowHeaders().add(colHeader);
				jqGridTableModel.setRowNumbers(false);
				jqGridTableModel.setTableHeight(500);  
			    jqGridTableModel.setTableWidth(650);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setRowNumbers(true);
				for (int i = 0; i < colHeader.length; i++) {
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
					jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
					jqGridColModel.setWidth(200);
					jqGridColModel.setAlign("left");
					jqGridColModel.setEditable(false);	 
					
					if (i <=2) {
						jqGridColModel.setHidden(true);
						jqGridColModel.setWidth(40);
						jqGridColModel.setAlign("left");
					}
				
					
					if(i==colHeader.length-1){
						jqGridColModel.setFormatter("BtnFormatterDelete");
						jqGridColModel.setWidth(100);
						jqGridColModel.setAlign("center");
					} 
					jqGridTableModel.getColModel().add(jqGridColModel);
				}

				JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
				tableModel.set("tableHeight", "40%%");
				tableModel.set("tableWidth", "30%%");
				return tableModel;

			}

		   private JSONObject getUniquePositionTableModel(List<String[]> facultyList) {
		 		// TODO Auto-generated method stub
		    	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		 		
		 		String[] colHeader = facultyList.get(0);		
		 		 
		 		jqGridTableModel.getRowHeaders().add(colHeader);
		 		jqGridTableModel.setRowNumbers(false);
		 		jqGridTableModel.setTableHeight(400);  
		 		jqGridTableModel.setTableWidth(450);
		 		jqGridTableModel.setTableButton(false);
		 		jqGridTableModel.setRowNumbers(true);
		 		for (int i = 0; i < colHeader.length; i++) {
		 			JqGridColModel jqGridColModel = new JqGridColModel();
		 			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		 			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
		 			jqGridColModel.setWidth(150);
		 			jqGridColModel.setAlign("left");
		 			jqGridColModel.setEditable(false);	 
		 			
		 			 if(i==0){
		 				    jqGridColModel.setHidden(true);
						    jqGridColModel.setAlign("left");	
							jqGridColModel.setWidth(80);
						 }
		 			 if(i==1){
		 				    jqGridColModel.setHidden(true);
						    jqGridColModel.setAlign("left");	
							jqGridColModel.setWidth(80);
						 }
		 			 if(i==2){
		 				    jqGridColModel.setHidden(false);
						    jqGridColModel.setAlign("left");	
							jqGridColModel.setWidth(150);
						 }
		 			 if(i==3){
		 				   jqGridColModel.setHidden(false);
						    jqGridColModel.setAlign("left");	
							jqGridColModel.setWidth(120);
						 }
		 			if(i==4){
		 				jqGridColModel.setHidden(false);
					    jqGridColModel.setAlign("left");	
						jqGridColModel.setWidth(100);
					 }
		 			if(i==colHeader.length-1){
		 				jqGridColModel.setFormatter("BtnFormatterDelete");
		 				jqGridColModel.setWidth(60);
		 				jqGridColModel.setAlign("center");
		 			}
		 			jqGridTableModel.getColModel().add(jqGridColModel);
		 		}

		 		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 		tableModel.set("tableHeight", "40%%");
		 		tableModel.set("tableWidth", "39%%");
		 		return tableModel;

		 	}
		    //--  vignesh chnaging for save  02Dec2025  --------------------------------------------//
		   
		 //--  vignesh chnaging for save 
		   @SuppressWarnings("unchecked")
		   private void TrainingCalSave(HttpServletRequest request,
		                                HttpServletResponse response) throws IOException {

		       HttpSession httpSession = request.getSession(false);
		       ServletOutputStream out = response.getOutputStream();
		       AdmTlUsermst user = UIUtils.getLoginUser(request);
		       user.getUsrm_ccno();

		       try {
		           TrainingBean abnormalityFormBean = new TrainingBean();

		           String calendarDetails = request.getParameter("calendarDetails");
		           String flid          = request.getParameter("fliId");
		           String locaionId     = request.getParameter("locaionId");
		           String sectionId     = request.getParameter("sectionId");
		           String cellId        = request.getParameter("cellId");
		           String facultyid     = request.getParameter("facultyId");
		           String uniqPost      = request.getParameter("uniquePosition");
		           String TrnCalId      = request.getParameter("TrnCalId");

		           // 🔑 FIX: decode inner %20, %3A etc in the JSON so dates become normal again
		           if (calendarDetails != null) {
		               try {
		                   calendarDetails = java.net.URLDecoder.decode(calendarDetails, "UTF-8");
		               } catch (Exception e) {
		                   e.printStackTrace();
		               }
		           }

		           //saveForm('frmGrdBsdTrgCal','gridBasedCalendar_save.gbtc?calendarDetails='+
		           // calendarDetails+"&uniquePosition="+uniquePosition+"&facultyId="+facultyId+
		           // "&fliId="+flid+"&locaionId="+locaionId+"sectionId="+sectionId+"cellId="+cellId);

		           EntTlTragcalmst newEntTlTragcalmst      = new EntTlTragcalmst();
		           EntTlTragcalmst existingEntTlTragcalmst = new EntTlTragcalmst();

		           newEntTlTragcalmst.setEtcmCreatedBy(user.getUsrm_ccno());
		           newEntTlTragcalmst.setEtcmLocation(locaionId);
		           newEntTlTragcalmst.setEtcmDmt(sectionId);
		           newEntTlTragcalmst.setEtcmJh(cellId);
		           newEntTlTragcalmst.setEtcmFlid(flid);
		           //newEntTlTragcalmst.setFaculty(facultyid);

		           CommonMessage.debugMsg(TrnCalId + " abnormalitydetails abnormalitydetails " + calendarDetails);
		           newEntTlTragcalmst.setEtcmKeyid(TrnCalId);

		           EntTlTrgFaculty newentTlTrgFaculty = new EntTlTrgFaculty();

		           newEntTlTragcalmst =
		                   (EntTlTragcalmst) UIUtils.setBeanProperties((Object) newEntTlTragcalmst, request);

		           EntTlTragcalmst existAbnTlAbnormality =
		                   (EntTlTragcalmst) httpSession.getAttribute("newEntTlTragcalmstServlet");

		           CommonMessage.debugMsg(newEntTlTragcalmst.getEtcmDmt()
		                   + " In side the Servlet dmt list "
		                   + newEntTlTragcalmst.getEtcmJh());

		           List<EntTlTragcalmst> AbnormalityList = null;
		           JSONArray abnList = null;
		           String abnmKeyid = "";
		           JSONObject returnData = new JSONObject();
		           Boolean clrVal = true;
		           String updateMsg = null;

		           //JSONObject jsonObject= new JSONObject(calendarDetails);
		           if (UIUtils.isValidKeyId(calendarDetails)) {
		               abnList = JSONArray.fromString(calendarDetails);
		               String Unique = null; // (String)jsonObject.get("cmbEtcuRoleKeyid");
		               /*
		                * Commented on 05May2023 by Kiran
		                * JSONObject jsonObject = abnList.getJSONObject(0);
		                *
		                * Unique=jsonObject.getString("cmbEtcuRoleKeyid");
		                * String faculty=jsonObject.getString("cmbEtcfFacultyId");
		                * String trngDate=jsonObject.getString("dteEtcmCalendarDate");
		                * String trngDuration=jsonObject.getString("txtEtcmMaxDuration");
		                * if(UIUtils.isValidKeyId(facultyid)){
		                *     newentTlTrgFaculty.setEtcfFacultyId(faculty);
		                *     newentTlTrgFaculty.setEtcfCreatedby(user.getUsrm_ccno());
		                *     newEntTlTragcalmst.setFaculty(newentTlTrgFaculty);
		                * }
		                *
		                * EntTlTrgCalUnqp newentTlTrgCalUnqp=new EntTlTrgCalUnqp();
		                * EntTlTrgCalUnqp existentTlTrgCalUnqp=
		                *     (EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
		                * if(UIUtils.isValidKeyId(Unique)){
		                *     newentTlTrgCalUnqp.setEtcuRoleKeyid(Unique);
		                *     newentTlTrgCalUnqp.setEtcuCreatedby(user.getUsrm_ccno());
		                *     newEntTlTragcalmst.setRoleLink(newentTlTrgCalUnqp);
		                * }
		                * EntTlTrgCalSession newentTlTrgCalSession = new EntTlTrgCalSession();
		                *
		                * if(UIUtils.isValidDate(trngDate)){
		                *     SimpleDateFormat formatter=new SimpleDateFormat("DD-MMM-YYYY HH:mm:ss");
		                *     newentTlTrgCalSession.setEtcsSessionDate(trngDate);
		                *     newentTlTrgCalSession.setEtcsFromDate(trngDate);
		                *     newentTlTrgCalSession.setEtcsTillDate(trngDate);
		                *     newentTlTrgCalSession.setEtcsName("Session 1");
		                *     newEntTlTragcalmst.setsessionMaster(newentTlTrgCalSession);
		                *     CommonMessage.debugMsg(Unique+"   Faculty Id and  Uniques "+trngDate +"   "+trngDuration);
		                * }
		                */

		               AbnormalityList = (List<EntTlTragcalmst>)
		                       UIUtils.convertJSONArrToList(newEntTlTragcalmst, abnList);

		               AbnormalityList.get(0).setEtcmDmt(sectionId);
		               AbnormalityList.get(0).setEtcmLocation(locaionId);
		               AbnormalityList.get(0).setEtcmJh(cellId);
		               AbnormalityList.get(0).setEtcmFlid(flid);
		               AbnormalityList.get(0).setEtcmKeyid(TrnCalId); //(flid);
		               
		               
//		               for (EntTlTragcalmst r : AbnormalityList) {
//		            	    if (!UIUtils.isValidKeyId(r.getEtcmKeyid()) && UIUtils.isValidKeyId(TrnCalId)) {
//		            	        r.setEtcmKeyid(TrnCalId);
//		            	    }
//		            	}


		               /*AbnormalityList.get(0).setFaculty(newentTlTrgFaculty);
		               AbnormalityList.get(0).setRoleLink(newentTlTrgCalUnqp);
		               AbnormalityList.get(0).setsessionMaster(newentTlTrgCalSession); Commented On 05May2023*/

		               CommonMessage.debugMsg("AbnormalityListAbnormalityList"
		                       + AbnormalityList.get(0).getEtcmCalendarDate() + "  "
		                       + AbnormalityList.get(0).getEtcmDmt());

		               if (!UIUtils.isValidKeyId(TrnCalId)) {
		                   AbnormalityList = gridBasedTrgCalService.create(AbnormalityList, abnormalityFormBean);
		                   updateMsg = "Data Saved Successfully";
		               } else {
		                   AbnormalityList = gridBasedTrgCalService.update(AbnormalityList, abnormalityFormBean);
		                   CommonMessage.debugMsg("Inside the Update");
		                   updateMsg = "Data Updated Successfully";
		               }

		               CommonMessage.debugMsg("GET:" + AbnormalityList.get(0).getEtcmKeyid());
		               abnmKeyid = AbnormalityList.get(0).getEtcmKeyid();
		               CommonMessage.debugMsg("abnmKeyid:" + abnmKeyid);
		               httpSession.setAttribute("CalKeyId", AbnormalityList.get(0).getEtcmKeyid());
		               request.setAttribute("keyid", AbnormalityList.get(0).getEtcmKeyid());

		               JSONObject successData = new JSONObject();
		               successData.put("msg", updateMsg);

		               if (UIUtils.isValidKeyId(TrnCalId)) {
		                   successData.put("keyId", TrnCalId);
		               } else {
		                   returnData.put("TraCalId", AbnormalityList.get(0).getEtcmKeyid());
		                   successData.put("keyId", AbnormalityList.get(0).getEtcmKeyid());
		               }

		               returnData.put("TraCalId", AbnormalityList.get(0).getEtcmKeyid());
		               returnData.put("TraCreateTime", AbnormalityList.get(0).getEtcmCreatedDateTime());

		               successData.put("msg", updateMsg);
		               returnData.put("successData", successData);
		               returnData.put("formClear", false);

		               httpSession.setAttribute("entTlTragcalmst", AbnormalityList);
		               out.print(returnData.toString());
		               //CommonMessage.debugMsg(AbnormalityList.get(0).getEtcmKeyid()+" In Servlet calendar keyid");
		           }

		       } catch (Exception e) {
		           e.printStackTrace();
		       }
		   }

		   
//		   @SuppressWarnings("unchecked")
//		private void TrainingCalSave(HttpServletRequest request,
//					HttpServletResponse response) throws IOException {
//			   
//			   HttpSession httpSession = request.getSession(false);
//			    ServletOutputStream out = response.getOutputStream();
//			    AdmTlUsermst user = UIUtils.getLoginUser(request);
//			    user.getUsrm_ccno();
//			 	try{
//			 		TrainingBean abnormalityFormBean = new TrainingBean();
//	       		String calendarDetails=request.getParameter("calendarDetails");	       		
//	       		String flid=request.getParameter("fliId");
//	       		String locaionId=request.getParameter("locaionId");
//	       		String sectionId=request.getParameter("sectionId");
//	       		String cellId=request.getParameter("cellId");
//	       		String facultyid=request.getParameter("facultyId");
//	       		String uniqPost=request.getParameter("uniquePosition");
//	       		String TrnCalId=request.getParameter("TrnCalId");
//       	       //saveForm('frmGrdBsdTrgCal','gridBasedCalendar_save.gbtc?calendarDetails='+calendarDetails+"&uniquePosition="+uniquePosition+"&facultyId="+facultyId+"&fliId="+flid+"&locaionId="+locaionId+"sectionId="+sectionId+"cellId="+cellId);
//
//	       		
//				EntTlTragcalmst newEntTlTragcalmst= new EntTlTragcalmst();
//				EntTlTragcalmst existingEntTlTragcalmst= new EntTlTragcalmst();
//
//				newEntTlTragcalmst.setEtcmCreatedBy(user.getUsrm_ccno());
//				newEntTlTragcalmst.setEtcmLocation(locaionId);
//				newEntTlTragcalmst.setEtcmDmt(sectionId);
//				newEntTlTragcalmst.setEtcmJh(cellId);
//				newEntTlTragcalmst.setEtcmFlid(flid);
//				//newEntTlTragcalmst.setFaculty(facultyid);
//	       		CommonMessage.debugMsg(TrnCalId +" abnormalitydetails abnormalitydetails "+calendarDetails);
//	       		newEntTlTragcalmst.setEtcmKeyid(TrnCalId);	
//				EntTlTrgFaculty newentTlTrgFaculty= new EntTlTrgFaculty();
//	       		
//	       		newEntTlTragcalmst =(EntTlTragcalmst)UIUtils.setBeanProperties((Object)newEntTlTragcalmst,request);
//	       		EntTlTragcalmst existAbnTlAbnormality = (EntTlTragcalmst)httpSession.getAttribute("newEntTlTragcalmstServlet");    		 		    		
//	    		
//	       		CommonMessage.debugMsg(newEntTlTragcalmst.getEtcmDmt()+" In side the Servlet dmt list "+newEntTlTragcalmst.getEtcmJh());
//				List<EntTlTragcalmst> AbnormalityList=null;    			
//	    		JSONArray abnList= null;	
//	    		  String abnmKeyid="";
//	    		JSONObject returnData=new JSONObject();
//	    		Boolean clrVal=true;
//	    		String updateMsg=null;
//	    	//JSONObject jsonObject= new JSONObject(calendarDetails);
//		 		if(UIUtils.isValidKeyId(calendarDetails)){
//	    			abnList=JSONArray.fromString(calendarDetails);
//	    			String Unique=null;//(String)jsonObject.get("cmbEtcuRoleKeyid");
//	    			/*
//	    			 * Commented on 05May2023 by Kiran
//	    			 * JSONObject jsonObject = abnList.getJSONObject(0);
//	    			 
//	    			Unique=jsonObject.getString("cmbEtcuRoleKeyid");
//	    			String faculty=jsonObject.getString("cmbEtcfFacultyId");
//	    			String trngDate=jsonObject.getString("dteEtcmCalendarDate");
//	    			String trngDuration=jsonObject.getString("txtEtcmMaxDuration");
//	    			if(UIUtils.isValidKeyId(facultyid)){
//	    				newentTlTrgFaculty.setEtcfFacultyId(faculty);
//		    			newentTlTrgFaculty.setEtcfCreatedby(user.getUsrm_ccno());
//		    			newEntTlTragcalmst.setFaculty(newentTlTrgFaculty);
//	    			}
//	    			
//	    			
//	    			
//	    			//*UnqpMst*//*		
//	    			EntTlTrgCalUnqp newentTlTrgCalUnqp=new EntTlTrgCalUnqp();
//	    			EntTlTrgCalUnqp existentTlTrgCalUnqp=(EntTlTrgCalUnqp)httpSession.getAttribute("entTlTrgCalUnqp");
//	    			if(UIUtils.isValidKeyId(Unique)){
//	    				newentTlTrgCalUnqp.setEtcuRoleKeyid(Unique);
//		    			newentTlTrgCalUnqp.setEtcuCreatedby(user.getUsrm_ccno());
//		    			newEntTlTragcalmst.setRoleLink(newentTlTrgCalUnqp);
//	    			}
//    				EntTlTrgCalSession newentTlTrgCalSession = new EntTlTrgCalSession();
//
//	    		
//	    			if(UIUtils.isValidDate(trngDate)){
//	    				 SimpleDateFormat formatter=new SimpleDateFormat("DD-MMM-YYYY HH:mm:ss"); 
//	    				// Date sesDate=(Date) formatter.parse(trngDate);
//	    				newentTlTrgCalSession.setEtcsSessionDate(trngDate);
//	    				newentTlTrgCalSession.setEtcsFromDate(trngDate);
//	    				newentTlTrgCalSession.setEtcsTillDate(trngDate);
//	    				newentTlTrgCalSession.setEtcsName("Session 1");
//	    				//newentTlTrgCalSession.setEtcsCreatedby(user.getUsrm_ccno());
//	    				newEntTlTragcalmst.setsessionMaster(newentTlTrgCalSession);
//	    				
//		    		//	CommonMessage.debugMsg(sesDate+"   Faculty Id and  Uniques "+trngDate +"   "+trngDuration);
//	    			CommonMessage.debugMsg(Unique+"   Faculty Id and  Uniques "+trngDate +"   "+trngDuration);
//
//	    			}*/
//	    			
//		 	   		AbnormalityList=(List<EntTlTragcalmst>)UIUtils.convertJSONArrToList(newEntTlTragcalmst, abnList);
//		 	   	AbnormalityList.get(0).setEtcmDmt(sectionId);
//		 	   	AbnormalityList.get(0).setEtcmLocation(locaionId);
//		 	   	AbnormalityList.get(0).setEtcmJh(cellId);
//		 		AbnormalityList.get(0).setEtcmFlid(flid);
//		 		AbnormalityList.get(0).setEtcmKeyid(TrnCalId);//(flid);
//		 		/*AbnormalityList.get(0).setFaculty(newentTlTrgFaculty);
//		 		AbnormalityList.get(0).setRoleLink(newentTlTrgCalUnqp);
//		 		AbnormalityList.get(0).setsessionMaster(newentTlTrgCalSession); Commented On 05May2023*/
//		 		
//		 		
//		 	   		CommonMessage.debugMsg("AbnormalityListAbnormalityList"+AbnormalityList.get(0).getEtcmCalendarDate() +"  "+AbnormalityList.get(0).getEtcmDmt() );
//		 			if(!UIUtils.isValidKeyId (TrnCalId)){ 
//		 			AbnormalityList =gridBasedTrgCalService.create(AbnormalityList,abnormalityFormBean);//,flid,sectionId,createdBy,Ism,docID,types,remarks);
//		 		   
//		 			updateMsg="Data Saved Successfully"; 
//		 		    
//		 		  
//		 			}
//		 			else{
//			 			AbnormalityList =gridBasedTrgCalService.update(AbnormalityList,abnormalityFormBean);//,flid,sectionId,createdBy,Ism,docID,types,remarks);
//
//		 				CommonMessage.debugMsg("Inside the Update");
//		 				updateMsg="Data Updated Successfully"; 
//		 			}
//		 		
//		 	    	
//		 			 CommonMessage.debugMsg("GET:"+AbnormalityList.get(0).getEtcmKeyid());
//		 		     abnmKeyid=AbnormalityList.get(0).getEtcmKeyid();
//		 		   CommonMessage.debugMsg("abnmKeyid:"+abnmKeyid);
//		 		     httpSession.setAttribute("CalKeyId", AbnormalityList.get(0).getEtcmKeyid());
//		 	    	 request.setAttribute("keyid", AbnormalityList.get(0).getEtcmKeyid());
//		 	    	JSONObject successData = new JSONObject();
//		 			successData.put("msg",updateMsg);
//		 		//	JSONObject returnData = new JSONObject();
//		 			//returnData.put("successData",successData);
//		 			if(UIUtils.isValidKeyId(TrnCalId)){
//		 			successData.put("keyId", TrnCalId);//AbnormalityList.get(0).getEtcmKeyid());
//		 			}
//		 			else{
//			 			returnData.put("TraCalId",AbnormalityList.get(0).getEtcmKeyid());
//			 			successData.put("keyId", AbnormalityList.get(0).getEtcmKeyid());
//
//		 			}
//		 			returnData.put("TraCalId",AbnormalityList.get(0).getEtcmKeyid());
//		 			returnData.put("TraCreateTime",AbnormalityList.get(0).getEtcmCreatedDateTime());
//		 			//returnData.put("savemode",savemode);
//		 			//httpSession.setAttribute("entTlTragcalmst", AbnormalityList);
//		 			//out.print(returnData.toString());
//		 			
//		 			//JSONObject successData = new JSONObject();
//		 			successData.put("msg",updateMsg);
//		 		//	JSONObject returnData = new JSONObject();
//		 			returnData.put("successData",successData);
//
//		 			returnData.put("formClear", false);
//		 			httpSession.setAttribute("entTlTragcalmst", AbnormalityList);
//		 			out.print(returnData.toString());
//		 	    //CommonMessage.debugMsg(AbnormalityList.get(0).getEtcmKeyid()+" In Servlet calendar keyid");
//		 		}
//		 		
//				
//		 		
//		 	
//		   } catch(Exception e){
//			   e.printStackTrace();
//		   }
//			   
//		   }
//		   
		   
		 //--  vignesh chnaging for save 02Dec2025  --------------------------//
		   //////////////
		   
		   private void TrainingCalSessionSave(HttpServletRequest request,
					HttpServletResponse response) throws IOException {
		   

			// TODO Auto-generated method stub
			HttpSession httpSession = request.getSession(false);
		    ServletOutputStream out = response.getOutputStream();
		    AdmTlUsermst user = UIUtils.getLoginUser(request);
		    String calendardate=request.getParameter("dteSessiondate");
		    String caldate=request.getParameter("dteEtcmCalendarDate");
		    String fromtime=request.getParameter("spnsessionFromTime");
		    String totime=request.getParameter("spnsessionTillTime");
		    String masterid=request.getParameter("txtEtcmKeyid");
		    String EtcmKeyid=request.getParameter("txtEtcmKeyid");
		  String sesId=request.getParameter("sesId");
		//String GridList=request.getParameter("paramJsonArrConvert");
		String savemsg=null;
		String updateMsg=null;
		//EntTlTragcalmst existEntTlTragcalmst = (EntTlTragcalmst)httpSession.getAttribute("entTlTragcalmst");

		try{
			//CommonMessage.debugMsg();
	
		    String fromdate=calendardate+" "+fromtime;
		    String todate=calendardate+" "+totime;


		if( httpSession != null && user != null)
		{	

					//*SessionMst*//
				EntTlTrgCalSession newentTlTrgCalSession = new EntTlTrgCalSession();
				 if(masterid!=null)
			  	    {
			  	    newentTlTrgCalSession.setEtcsEtcmKeyid(masterid);
			  	    }
				EntTlTrgCalSession existntTlTrgCalSession = (EntTlTrgCalSession)httpSession.getAttribute("entTlTrgCalSession");
									
				newentTlTrgCalSession = (EntTlTrgCalSession)UIUtils.setBeanProperties((Object)newentTlTrgCalSession,request);
				 if(masterid==null)
			 	  {  
			 	
			 	   newentTlTrgCalSession.setEtcsFromDate(fromdate);
			 	   newentTlTrgCalSession.setEtcsTillDate(todate);
			 	   newentTlTrgCalSession.setEtcsSessionDate(caldate);
			 	   
			 		  CommonMessage.debugMsg(fromdate +" 1 In side the servelet "+todate+"   "+calendardate);

			 	  }
			 	  else{
			 		  CommonMessage.debugMsg(fromdate +" 2 In side the servelet "+todate+"   "+calendardate);
			 		   newentTlTrgCalSession.setEtcsFromDate(fromdate);
				 	   newentTlTrgCalSession.setEtcsTillDate(todate);
				 	   newentTlTrgCalSession.setEtcsSessionDate(calendardate);
				 	   				 	   
			 	  }			 	   
				    	newentTlTrgCalSession.setEtcsName("Session");
				    			     
			 	  if(sesId==null){
				    	newentTlTrgCalSession =	gridBasedTrgCalService.createSession(newentTlTrgCalSession,existntTlTrgCalSession);
						 savemsg="Data Saved Successfully";
				}
				else{  
					     
					  existntTlTrgCalSession= gridBasedTrgCalService.updateSession(newentTlTrgCalSession,existntTlTrgCalSession);
						 savemsg="Data Updated Successfully";	
				}
				   httpSession.setAttribute("TraKeyAfterSave", newentTlTrgCalSession.getEtcsEtcmKeyid());
		
		
		JSONObject successData = new JSONObject();
		successData.put("msg",savemsg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
	//	returnData.put("TraCalId",newentTlTrgCalSession.getEtcsEtcmKeyid());
	//	returnData.put("TraCreateTime",existEntTlTragcalmst.getEtcmCreatedDateTime());
		//returnData.put("savemode",savemode);
		returnData.put("formClear", false);		
		out.print(returnData.toString());
		
		
					
	  }
		}
		catch (ValidationExceptions e) {
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewTrainingCalendar");
			out.print(errMessage.toString());
		}
		catch(Exception e){
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
	}
	} 
}
