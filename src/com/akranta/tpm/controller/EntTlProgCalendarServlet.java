package com.akranta.tpm.controller;

/**
 * Author:Roopa
 * Created on : 26/07/2012
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlProgCalendarBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.QtmTlDockauditBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlSelfnominationmst;
import com.akranta.tpm.model.EntTlTaskdtl;
import com.akranta.tpm.model.EntTlTaskmst;
import com.akranta.tpm.model.EntTlTrainingneedmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.OplTlPillarlink;
//import com.akranta.tpm.model.QtmTlDockaudit;
import com.akranta.tpm.service.EntTlProgCalendarService;
import com.akranta.tpm.service.impl.EntTlAssessmentmstServiceImpl;
import com.akranta.tpm.service.impl.EntTlProgCalendarServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntTlProgCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
	private EntTlProgCalendarService EntTlProgCalendarService; 
    public EntTlProgCalendarServlet() throws Exception {
        super();
        // TODO Auto-generated constructor stub
      //  EntTlProgCalendarService = new EntTlProgCalendarServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
   	@SuppressWarnings("null")
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String action = UIUtils.getActionPart(request);
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		List<ComboBox> comboList = new ArrayList<ComboBox>();			
		CommonFilter  commonFilter = new CommonFilter();			

		ComboFilter comboFilter = UIUtils.fillComboFilter(request);

		CommonMessage.debugMsg("action:::::: "+action);
		String filter = request.getParameter("q");
		ComboFilter currentFilter = new ComboFilter();
		currentFilter.setCode(filter);
		currentFilter.setName(filter);
		//PrintWriter out = response.getWriter();
		if( user == null)
			return ;
		
		try {
			
			EntTlProgCalendarService = (EntTlProgCalendarServiceImpl)UIUtils.getServiceObject(request,"EntTlProgCalendarServiceImpl");
		} catch (ServiceObjectCreationException e) {
			// TODO Auto-generated catch block
			CommonMessage.debugMsg(e);
			//e.printStackTrace();
		}		
		if (action.equals("progCalender_view.progcal")) {
			httpSession.removeAttribute("frmType");
			httpSession.setAttribute("frmType","progCalendar");
			request.setAttribute("frmType","progCalendar");
			request.setAttribute("DoubleClick","Double Click on Plotted area to Drilldown");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/ProgCalendar.jsp"); 
			rd.forward(request, response); 	
			
		}
		else if (action.equals("SelfNomination_view.progcal")){
			request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));
			UIUtils.forwardRequest(request, response, "/pages/newENT/SelfNominationMainGrid.jsp");
		}
		else if( action.equals("getModeSelfNomination_view.progcal")){
			response.setContentType("text/html");
			//response.setContentType("text/json");
			
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			CommonMessage.debugMsg("Action ::: "+action);
			result.put("mode","create");
			result.put("url","SelfNominationEntry_input.progcal");
			result.put("formHeader","Self Nomination");
			
			out.println(result);
		}
		else if (action.equals("SelfNomination_getCol.progcal")){
			nominationMainGridgetCol(request ,response);
		}
		else if (action.equals("SelfNomination_getData.progcal")){
			nominationMainGridgetData(request ,response);
		}
		else if(action.equals("SelfNomination_getExcel.progcal"))
		{
			CommonMessage.debugMsg("Self Nomination :::432"+action);
			selfNominationMainGridExcel(request,response,httpSession);
		}
		else if(action.equals("SelfNominationEntry_input.progcal")){
			String Empid= request.getParameter("Empid");
			String ProgID= request.getParameter("ProgID");
			String BatchId= request.getParameter("BathId");
			String FLID= request.getParameter("FLID");
			request.setAttribute("Empid", Empid);
			request.setAttribute("ProgID", ProgID);
			request.setAttribute("BatchId", BatchId);
			request.setAttribute("FLID", FLID);
			UIUtils.forwardRequest(request, response, "/pages/ENT/SelfNominationReport.jsp");
		}
		else if (action.equals("SelfNominationEntry_getCol.progcal")){
			selfNominationReportGetCol(request,response);
		}else if (action.equals("SelfNominationEntry_getData.progcal")){
			selfNominationGetData(request,response);
		}
		else if(action.equals("SelfNominationEntry_save.progcal") || action.equals("SelfNominationPop_save.progcal")){
			selefNominationSave(request,response);
		}
		//pop up
		else if (action.equals("SelfNominationPop_view.progcal")){
			UIUtils.forwardRequest(request, response, "/pages/ENT/SelfNominationPopup.jsp");
		}
		else if (action.equals("SelfNominationPop_getCol.progcal")){
			selfNominationpopupGetCol(request,response);
		}else if (action.equals("SelfNominationPop_getData.progcal")){
			selfNominationpopupGetData(request,response);
		}
		else if (action.equals("SelfNominationEntry_delete.progcal")){
			selfNominationDelete(request,response);
		}
		else if (action.equals("batchSchedule_view.progcal")) {
			httpSession.removeAttribute("frmType");
			httpSession.setAttribute("frmType","batchSchedule");
			request.setAttribute("frmType","batchSchedule");
			request.setAttribute("DoubleClick","Double Click on Plotted Area to Add Batch Schedule");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/ProgCalendar.jsp"); 
			rd.forward(request, response);
		}
		
		else if (action.equals("batchCreation_view.progcal")) {
			httpSession.removeAttribute("frmType");
			httpSession.setAttribute("frmType","batchCreation");
			request.setAttribute("frmType","batchCreation");	
			request.setAttribute("DoubleClick","Double Click on Plotted Area to Add Batch");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/ProgCalendar.jsp"); 
			rd.forward(request, response);
		}
		else if( action.equals("progCalender_input.progcal")){
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/ProgCalendar.jsp"); 
			rd.forward(request, response); 		
		}
		else if( action.equals("progCalender_getCol.progcal")){
			PrintWriter out = response.getWriter();
			JSONObject jsonObject=new  JSONObject();
			jsonObject=getCalenderListGetCol(request,response,httpSession,commonFilter);
			out.println(jsonObject);
		}
		else if( action.equals("progCalender_getData.progcal"))
		{
			PrintWriter out = response.getWriter();
			List<String []> progCalenderGrid  = null;
			String year =  request.getParameter("year");	
			String progId=request.getParameter("progId");	
			String frmType = request.getParameter("frmType");
			if (!CommonFunctions.isValidKeyId(year))						  	
				year= CommonFunctions.getCurrentYear();
			if (!CommonFunctions.isValidKeyId(progId))	
				progId="";
			JSONObject progCalenderGriddata = null;
			progCalenderGrid  = EntTlProgCalendarService.getProgCalendergridData(year,12,progId,frmType);
			progCalenderGriddata = UIUtils.convertToJqGridTableObject(progCalenderGrid,request,1,0);			
			out.println(progCalenderGriddata);
			CommonMessage.debugMsg("progCalenderGriddata"+progCalenderGriddata);
	    }
		else if (action.equals("progCalenderGet_view.progcal")) {
			PrintWriter out = response.getWriter();
			JSONObject jSONObject=new JSONObject();
			JSONArray jSONArray=new JSONArray();
			String year =  request.getParameter("year");	
			String progId =  request.getParameter("progId");
			if (!CommonFunctions.isValidKeyId(progId))	
				progId="";
			
			if (!CommonFunctions.isValidKeyId(year))						  	
				year= CommonFunctions.getCurrentYear();
			List<String[]> dateList=new ArrayList<String[]>();
			dateList=EntTlProgCalendarService.getDatesList(year, 12,progId);
					
			if (dateList.size()>0)	{
				jSONArray=jSONArray.fromCollection(dateList);
				jSONObject.put("monthList",jSONArray);
			}			
			jSONObject.put("year",year);
			jSONObject.put("progId",progId);
			jSONObject.put("monthwise","Y");
			out.print(jSONObject.toString());			
		}
		
		else if( action.equals("progCalender_save.progcal"))
		{	
			//CommonMessage.debugMsg("inside save action");
			EntTlProgCalendarBean EntTlProgCalendarBean = new EntTlProgCalendarBean();
			saveProgCalendar(request,response,EntTlProgCalendarBean);
	    }
		
		else if(action.equals("comboProgram.progcal"))
		{
			CommonMessage.debugMsg("comboProgram.progcal");
			String spokeId =  request.getParameter("spokeId");	
			if (CommonFunctions.isValidKeyId(spokeId))
				commonFilter.setService(spokeId);
			CommonMessage.debugMsg("spokeId" + spokeId);
			commonFilter.setProgm(currentFilter);			
			comboList = EntTlProgCalendarService.getProgramComboList(commonFilter);	
			CommonMessage.debugMsg("comboList" + comboList);
			if( comboList != null && comboList.size() > 0 )
				currentFilter=UIUtils.fillComboFilter(request);
				UIUtils.writeComboBox(response,comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if(action.equals("comboSpoke.progcal"))
		{
			CommonMessage.debugMsg("comboSpoke.progcal");
			commonFilter.setProgm(currentFilter);
			comboList = EntTlProgCalendarService.getSpokeComboList(commonFilter);	
			CommonMessage.debugMsg("comboList" + comboList);
			if( comboList != null && comboList.size() > 0 )
				currentFilter=UIUtils.fillComboFilter(request);
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if(action.equals("comboYear.progcal"))
		{
			getDateCombo(request,response, comboFilter);
		}
		else if(action.equals("comboYearTest.progcal"))
		{
			List<ComboBox> comboLst = new ArrayList<ComboBox>();
	  		String year= CommonFunctions.getCurrentYear();
	  		year=CommonFunctions.addYear(year,2);
	  		comboLst.add(setCombo(year));
	  		CommonMessage.debugMsg(year);
			for(int i=4; i>0; i--) {
				year=CommonFunctions.addYear(year,-1);
				comboLst.add(setCombo(year));
				CommonMessage.debugMsg(i + " : "+year);
			}		
			//CommonMessage.debugMsg("comboList1" + comboList);
			if( comboLst != null && comboLst.size() > 0 )
				currentFilter=UIUtils.fillComboFilter(request);
				UIUtils.writeComboBox(response, comboLst,currentFilter);//writeCombo(response, comboList);
		}
		
	}

  
	private void selfNominationMainGridExcel(HttpServletRequest request,HttpServletResponse response, HttpSession httpSession) throws SQLException, Exception {
   		CommonMessage.debugMsg("SelfNomination Excel :::::123");		
   	try	{
   		CommonMessage.debugMsg("SelfNomination Excel :::::");
   		CommonFilter	commonFilter = populateCommonFilter(request,"SelfNominationCommonFilter",false);
   		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String empid =user.getUsrm_ccno();
		JSONObject colmodel = (JSONObject) httpSession.getAttribute("SelfNominationReport");
		colmodel.put("title","Self Nomination Report");
		String format = ExcelUtils.getFormat(request);
		Workbook wb = EntTlProgCalendarService.SelfNominationExcel(commonFilter,colmodel,format,empid);
		ExcelUtils.writeToResponse(response, wb, "SelfNominationReport", format);
   	  }
   	 catch(Exception e){
   		e.printStackTrace();
   	  }		    
	}

	private void nominationMainGridgetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("VisualSOP Report Getcol" );
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String empid =user.getUsrm_ccno();
		CommonMessage.debugMsg("USER ID ::::: "+empid);
		HttpSession httpSession = request.getSession(false);
		String flid =request.getParameter("flid");
		List<String[]> selfNominationGrid = null;
		try{
			CommonFilter commonFilter = populateCommonFilter(request,"SelfNominationCommonFilter", true);
			selfNominationGrid = EntTlProgCalendarService.getSelfNominationMainGrid(commonFilter,empid);
			JSONObject SkillData= UIUtils.convertToJqGridTableObject(selfNominationGrid,request, 3, 0, commonFilter.getTotalRecordCnt()+3);
			//httpSession.setAttribute("SelfNominationReport", SkillData);	
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);			
			gridColModel.setHeaderNum(1);
			String [] colHeader = selfNominationGrid.get(2);
			String [] colHeaderCond = selfNominationGrid.get(1);				
	
			List<String[]> headers = new ArrayList<String[]>();
			
			//headers.add(colHeaderCond);
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "83%%");
			jsonObject.put("data", SkillData);
			
			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("SelfNominationReport");
			httpSession.setAttribute("SelfNominationReport", jsonObject);
			out.println(jsonObject);
			CommonMessage.debugMsg("type :::::   774");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
   	private void nominationMainGridgetData(HttpServletRequest request,HttpServletResponse response) {
   		try {
   		    HttpSession httpSession = request.getSession(false);
			CommonMessage.debugMsg("type :::::   getData");
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String empid =user.getUsrm_ccno();
			CommonFilter commonFilter = populateCommonFilter(request,"SelfNominationCommonFilter", true);
			String flid =request.getParameter("flid"); 
			List<String[]> selfNominationGrid= EntTlProgCalendarService.getSelfNominationMainGrid(commonFilter,empid);
			PrintWriter out = response.getWriter();
			JSONObject sopReportGridmod= UIUtils.convertToJqGridTableObject(selfNominationGrid,request, 3, 0, commonFilter.getTotalRecordCnt()+3);
			out.println(sopReportGridmod);
            /*httpSession.removeAttribute("SelfNominationCommonFilter");
			httpSession.setAttribute("SelfNominationCommonFilter", commonFilter);*/
		 
		}catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
		}
		
	}
   	private void selfNominationReportGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String userid =user.getUsrm_ccno();
		HttpSession httpSession = request.getSession(false);
		String flid =request.getParameter("flid");
		String type =request.getParameter("typeId");
		String date =request.getParameter("DATE");
		String Empid =request.getParameter("empid");
		String ProgId =request.getParameter("progid");
		String BatchId =request.getParameter("batchid");
		String Status=request.getParameter("Status");
		List<String[]> selfNominationGrid = null;
		CommonFilter commonFilter=new CommonFilter();
		try {	
			if(UIUtils.isValidKeyId(flid)){
				commonFilter.setFlid(flid);				
				if (UIUtils.isValidKeyId(date)){
					 commonFilter.setFromMonth(date);}
				else{
					commonFilter.setFromMonth("");}
			    if(UIUtils.isValidKeyId(type))	{			    	
					 commonFilter.setAreatype(type);
					}
			    else{			    	
			    	commonFilter.setAreatype("");}
			}			
			selfNominationGrid = EntTlProgCalendarService.getSelfNominationReport(commonFilter,Empid,ProgId,BatchId,userid,Status);
			JSONObject SkillData= UIUtils.convertToJqGridTableObject(selfNominationGrid,request, 3, 0, commonFilter.getTotalRecordCnt()+3);
			httpSession.setAttribute("TaskTopicReport", SkillData);	
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(false);
			jqGridTableModel.setMultiSelect(true);
			gridColModel.setHeaderNum(1);
			String [] colHeader = selfNominationGrid.get(2);
			String [] colHeaderCond = selfNominationGrid.get(1);				
			List<String[]> headers = new ArrayList<String[]>();			
			//headers.add(colHeaderCond);
			headers.add(colHeader);			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "60%%");
			jsonObject.put("data", SkillData);
			
			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("TaskKsaReport");
			httpSession.setAttribute("TaskKsaReport", jsonObject);
			out.println(jsonObject);
		} catch (Exception e) {
		e.printStackTrace();
		}
		//jsonObject = getTableModelForSelfNomination(selfNominationGrid);
		//out.println(jsonObject);
}
private void selfNominationGetData(HttpServletRequest request,	HttpServletResponse response) throws IOException{
	try {	
		CommonFilter commonFilter = new CommonFilter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String userid =user.getUsrm_ccno();
		FilterValues.getCommonFilters(request, commonFilter);
		String flid =request.getParameter("flid");
		String type =request.getParameter("typeId");
		String date =request.getParameter("DATE");
		String Empid =request.getParameter("empid");
		String ProgId =request.getParameter("progid");
		String BatchId =request.getParameter("batchid");
		String Status=request.getParameter("Status");
		if(UIUtils.isValidKeyId(flid)){
			commonFilter.setFlid(flid);				
			if (UIUtils.isValidKeyId(date)){
				 commonFilter.setFromMonth(date);}
			else{
				commonFilter.setFromMonth("");}
		    if(UIUtils.isValidKeyId(type))	{
		    	CommonMessage.debugMsg("type  :::::::: DATA 77"+type);
				 commonFilter.setAreatype(type);
				}
		    else{
		    	CommonMessage.debugMsg("type  ::::::::DATA 88"+type);
		    	commonFilter.setAreatype("");}
		}
	    CommonMessage.debugMsg("type ::::: ::::SASASASAs"+type);
		List<String[]> selfNominationGrid= EntTlProgCalendarService.getSelfNominationReport(commonFilter,Empid,ProgId,BatchId,userid,Status);
		PrintWriter out = response.getWriter();
		JSONObject sopReportGridmod= UIUtils.convertToJqGridTableObject(selfNominationGrid,request, 3, 0, commonFilter.getTotalRecordCnt()+3);
		out.println(sopReportGridmod);	 
		}catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
		}
}	
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
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
		CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	

private void selefNominationSave(HttpServletRequest request,HttpServletResponse response) throws IOException {
   		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String Updatemsg=request.getParameter("Updateval");
		CommonMessage.debugMsg("upadet msg::::::"+Updatemsg);
	    try{
    	   if( httpSession != null && user != null){	
    		   List<EntTlSelfnominationmst> existEntTlSelfnominationmsts = (List<EntTlSelfnominationmst>)httpSession.getAttribute("entTlSelfnominationmst");	    		
    		   EntTlSelfnominationmst neweEntTlSelfnominationmst=new EntTlSelfnominationmst();	
    		
    		   neweEntTlSelfnominationmst.setSnomCreatedby(user.getUsrm_ccno());
    		   String SelfNomination = request.getParameter("SelfNomination");
    		   String NominatonDelete = request.getParameter("SelfNominationUncheck");
               
 /**save JsonARR conversion**/
            	
             CommonMessage.debugMsg("If condition Insert  ATTT:::" );  
 		  	 List<EntTlSelfnominationmst> NominationList1 = null;
       		 JSONArray Gridjson1 = null;
       		 CommonMessage.debugMsg("SelfNomination::::"+SelfNomination);
       		 CommonMessage.debugMsg("If condition Insert  ATTT:::" ); 	        
       		      if(UIUtils.isValidKeyId(SelfNomination)){
   			    		//CommonMessage.debugMsg(" mulitMethodStr " + mulitMethodStr);
   					    CommonMessage.debugMsg("Detailstr " + SelfNomination);
   					    Gridjson1 = JSONArray.fromString(SelfNomination); 
   						NominationList1=(List<EntTlSelfnominationmst>)UIUtils.convertJSONArrToList(neweEntTlSelfnominationmst, Gridjson1);
   						//CommonMessage.debugMsg("TaskGridList1 "+TaskGridList1.get(0));
   						CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()" +  NominationList1);
   						for(int i=0;i<NominationList1.size();i++){
   							NominationList1.get(i).setSnomEmpid(user.getUsrm_ccno());
   							NominationList1.get(i).setSnomCreatedby(user.getUsrm_ccno());
   							NominationList1.get(i).setSnomApprovedby(user.getUsrm_ccno());
                        }
       		      }


 /**conversion END**/
       		   String saveMsg = null ;      		   			
    		   existEntTlSelfnominationmsts =EntTlProgCalendarService.createNomination(NominationList1,NominatonDelete);
    		   saveMsg = "Data Saved Successfully";
    		   if(UIUtils.isValidKeyId(Updatemsg)){
	    		   if(Updatemsg.equals("Y"))
						saveMsg = "Data Updated Successfully";
    		      }
			JSONObject returnData = new JSONObject();			
			JSONObject successData = new JSONObject();		
			CommonMessage.debugMsg("Save completed");
			String openFileMgr = request.getParameter("openFileManager");
			if(UIUtils.isValidKeyId(openFileMgr))
				returnData.put("openFileMgr","Y");	
			successData.put("msg", saveMsg);
			returnData.put("formClear",false);	
			returnData.put("successData",successData);
			out.print(returnData.toString());
    	 }		    	
	}
	catch(ValidationExceptions e){
		e.printStackTrace();
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "TrainingNeed");
		out.print(errMessage.toString());
		
	}
	catch(BusinessApplicationExceptions e){   
		
		CommonMessage.debugMsg("Error Servler e -"+e.toString());
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "TrainingNeed");
		out.print(errMessage.toString());
		CommonMessage.debugMsg(" e " + errMessage );
	}
	catch(Exception e){				
	
	     e.printStackTrace();
		JSONObject err = new JSONObject();
		err.put("tpmException", "Data Not Saved");
		out.print(err.toString());			
	}
}

	private void saveProgCalendar(HttpServletRequest request, HttpServletResponse response,EntTlProgCalendarBean EntTlProgCalendarBean  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		CommonMessage.debugMsg("Inside Save Program Calendar");
    	HttpSession httpSession = request.getSession(false);
    	PrintWriter out = response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try{
	    	if( httpSession != null && user != null)
	    	{	
	    		List<EntTlProgCalendar> existEntTlProgCalendar = (List<EntTlProgCalendar>)httpSession.getAttribute("entTlProgCalendar");
	    		EntTlProgCalendarBean entTlProgCalendarBean =(EntTlProgCalendarBean) UIUtils.setBeanProperties((Object)EntTlProgCalendarBean,request);
	    		String mulitMethodStr =  request.getParameter("multiplemethods");
	    		//CommonMessage.debugMsg("mulitMethodStr" + mulitMethodStr);
	    		String EcalMonthwise =  request.getParameter("EcalMonthwise");	
	    		//CommonMessage.debugMsg("EcalMonthwise" + EcalMonthwise);
				List<EntTlProgCalendar> newEntTlProgCalendar = null;
				JSONArray entTlProgCalendarJson = null;
				if(UIUtils.isValidKeyId(mulitMethodStr)){
					
					//List<EntTlProgCalendar> entTlProgCalendarList =null;
					EntTlProgCalendar entTlProgCalendar = new EntTlProgCalendar();
		    		if( mulitMethodStr != null && ! mulitMethodStr.isEmpty())
		    		{
		    			entTlProgCalendarJson = JSONArray.fromString(mulitMethodStr);
		    			newEntTlProgCalendar=(List<EntTlProgCalendar>)UIUtils.convertJSONArrToList(entTlProgCalendar, entTlProgCalendarJson);
		    		}
					String firstDate=null;
					String lastDate=null;
					String saveMsg = null ;
					if(newEntTlProgCalendar!= null){
						String valid=IsValidToDelete(newEntTlProgCalendar);						
						if(!CommonFunctions.isValidKeyId(valid)){						
							for(int i=0;i<=newEntTlProgCalendar.size()-1;i++)
							{
								//CommonMessage.debugMsg("EcalPlanAudiencecount:"+newEntTlProgCalendar.get(i).getEcalPlanAudiencecount());
								firstDate="01-" + newEntTlProgCalendar.get(i).getEcalPlanMonth();
								lastDate=CommonFunctions.getLastDayOfMonth(firstDate);
								//CommonMessage.debugMsg("firstDate:"+firstDate + "LastDate:" +lastDate);
								newEntTlProgCalendar.get(i).setEcalMonthwise(EcalMonthwise);							
								newEntTlProgCalendar.get(i).setEcalPlanFromdate(firstDate);
								newEntTlProgCalendar.get(i).setEcalPlanTilldate(lastDate);							
								newEntTlProgCalendar.get(i).setEcalCreatedby(user.getUsrm_ccno());
								//CommonMessage.debugMsg("EcalKeyid:" + newEntTlProgCalendar.get(i).getEcalKeyid());
								
								if(CommonFunctions.isValidKeyId(newEntTlProgCalendar.get(i).getEcalKeyid())){
									EntTlProgCalendar oldEntTlProgCalendar=new EntTlProgCalendar();
									oldEntTlProgCalendar.setEcalKeyid(newEntTlProgCalendar.get(i).getEcalKeyid());
									oldEntTlProgCalendar=EntTlProgCalendarService.select(oldEntTlProgCalendar);
									newEntTlProgCalendar.get(i).setEcalCreatedby(oldEntTlProgCalendar.getEcalCreatedby());
									newEntTlProgCalendar.get(i).setEcalCreatedon(oldEntTlProgCalendar.getEcalCreatedon());								
								}
							}
													
							CommonMessage.debugMsg("key id available");						
							existEntTlProgCalendar =EntTlProgCalendarService.create(newEntTlProgCalendar,existEntTlProgCalendar,EntTlProgCalendarBean);
							saveMsg = "Data Saved Successfully";
							JSONObject returnData = new JSONObject();				
							JSONObject successData = new JSONObject();	
							successData.put("msg", saveMsg);
							returnData.put("formClear",false);	
							returnData.put("successData",successData);
							out.print(returnData.toString());		
						}
						else{
							CommonMessage.debugMsg("Referred By Another Table");							
							JSONObject err = new JSONObject();
							err.put("tpmException", valid);//"This Calendar Referred Batch Schedule");
							out.print(err.toString());	
						}
					}
				}	
	    	 }	
	    	
		}catch(ValidationExceptions e)
		{
			CommonMessage.debugMsg("ValidationExceptions");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "EntTlProgCalendar");
			errMessage.put("fromMode",EntTlProgCalendarBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}catch(BusinessApplicationExceptions e)
		{
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"EntTlSkillmst");
			errMessage.put("tpmException", "Calendar Already Referred In Batch Schedule");
			out.print(errMessage.toString());
			
		}catch(Exception e)
		{				
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());			
		}
   	}	
   	
   	private String IsValidToDelete(List<EntTlProgCalendar> newEntTlProgCalendar) throws Exception{
   		String valid=null;
   		for(int i=0;i<=newEntTlProgCalendar.size()-1;i++)
		{
   			CommonMessage.debugMsg("ACTIVE:"+ newEntTlProgCalendar.get(i).getEcalActive());
			if(newEntTlProgCalendar.get(i).getEcalActive().equals("N")){
				EntTlProgCalendar oldEntTlProgCalendar=new EntTlProgCalendar();
				oldEntTlProgCalendar.setEcalKeyid(newEntTlProgCalendar.get(i).getEcalKeyid());
				List<String []> resultList = new ArrayList<String[]>();
				resultList=EntTlProgCalendarService.selectSchedule(oldEntTlProgCalendar);
				if(resultList.size()>0){					
					valid=newEntTlProgCalendar.get(i).getEcalPlanMonth() + " already referred in Batch Schedule";
					break;
				}
			}
		}
   		return valid;
   	}
   	
   	private void getDateCombo(HttpServletRequest request, HttpServletResponse response, ComboFilter comboFilter ) throws IOException{
   		List<ComboBox> comboList = new ArrayList<ComboBox>();
  		String year= CommonFunctions.getCurrentYear();
  		ComboFilter currentFilter = new ComboFilter();
  		year=CommonFunctions.addYear(year,10);		
		for(int i=20; i>0; i--) {
			year=CommonFunctions.addYear(year,-1);
			comboList.add(setCombo(year));
		}		
		//CommonMessage.debugMsg("comboList1" + comboList);
		if( comboList != null && comboList.size() > 0 )
			currentFilter=UIUtils.fillComboFilter(request);
			UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
   	}
   	
   	private ComboBox setCombo(String year){
   		ComboBox compComb = new ComboBox();
		compComb.setId(year);
		compComb.setText(year);	
		return compComb;
   	} 
   	
   	private JSONObject getCalenderListGetCol(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,CommonFilter commonFilter) throws IOException{
   		
		List<String []> progCalenderGrid  = null;			  
		String year =  request.getParameter("year");
		String progId=request.getParameter("progId");
		String formType = request.getParameter("frmType");
		String frmType=null;
		if (!CommonFunctions.isValidKeyId(year))						  	
			year= CommonFunctions.getCurrentYear();		
		if (!CommonFunctions.isValidKeyId(progId))	
			progId="";
		JSONObject progCalenderGriddata = null;
		try {
			progCalenderGrid  = EntTlProgCalendarService.getProgCalendergridData(year,12,progId,formType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		progCalenderGriddata = UIUtils.convertToJqGridTableObject(progCalenderGrid,request,0,0);
		httpSession.setAttribute("progCalenderGriddata", progCalenderGriddata);
		JSONObject jsonObject = getTableModel(progCalenderGrid);
		if(progCalenderGrid.size()>0){
			frmType=(String)httpSession.getAttribute("frmType");
			if(frmType.equals("progCalendar")){
				jsonObject.set("tableHeight", "53%%");
				jsonObject.set("tableWidth", "100%%");
			}
			else{
				jsonObject.set("tableHeight", "82%%");
				jsonObject.set("tableWidth", "100%%");
			}
		}
		return jsonObject;
   	}


//For pop up
private void selfNominationpopupGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
	CommonMessage.debugMsg("VisualSOP Report Getcol" );
	PrintWriter out = response.getWriter();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	String empid =user.getUsrm_ccno();
	HttpSession httpSession = request.getSession(false);
	JSONObject jsonObject = new JSONObject();
	List<String[]> selfNominationGrid = null;
	CommonFilter commonFilter = populateCommonFilter(request,"SelfNominationApprovedCommonFilter", true);
	try {	
	
		selfNominationGrid = EntTlProgCalendarService.getSelfNominationPopUp(commonFilter,empid);
		JSONObject SkillData= UIUtils.convertToJqGridTableObject(selfNominationGrid,request, 3, 0, commonFilter.getTotalRecordCnt()+3);
		httpSession.setAttribute("TaskTopicReport", SkillData);	
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();			
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);	
		jqGridTableModel.setMultiSelect(true);
		gridColModel.setHeaderNum(1);
		String [] colHeader = selfNominationGrid.get(2);
		String [] colHeaderCond = selfNominationGrid.get(1);				
		List<String[]> headers = new ArrayList<String[]>();			
		//headers.add(colHeaderCond);
		headers.add(colHeader);			
		jsonObject= UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.set("tableWidth", "90%%");
		jsonObject.set("tableHeight", "60%%");
		jsonObject.put("data", SkillData);
		
		CommonMessage.debugMsg(jsonObject);
		httpSession.removeAttribute("TaskKsaReport");
		httpSession.setAttribute("TaskKsaReport", jsonObject);
		out.println(jsonObject);
	} catch (Exception e) {
	e.printStackTrace();
	}
}
private void selfNominationpopupGetData(HttpServletRequest request,	HttpServletResponse response) throws IOException{
	try {
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String empid =user.getUsrm_ccno();
		CommonFilter commonFilter = populateCommonFilter(request,"SelfNominationApprovedCommonFilter", true);
		FilterValues.getCommonFilters(request, commonFilter);
		List<String[]> selfNominationGrid= EntTlProgCalendarService.getSelfNominationPopUp(commonFilter,empid);
		PrintWriter out = response.getWriter();
		JSONObject sopReportGridmod = UIUtils.convertToJqGridTableObject(selfNominationGrid, request, 3,0);
		out.println(sopReportGridmod);
		} catch (Exception e) {
		CommonMessage.debugMsg(e.getMessage());
		}
}
private JSONObject getTableModelForSelfNomination(List<String[]> headers) {
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(1);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setTableHeight(100);
	jqGridTableModel.setTableWidth(500);
	jqGridTableModel.setTableButton(true);
	String[] colIndex = headers.get(0);
	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
		jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
	   	jqGridColModel.setWidth(250);
	   	jqGridColModel.setAlign("left");

		if(i==0 ){
			jqGridColModel.setFormatter("txtFormatter");
			jqGridColModel.setAlign("center");
			jqGridColModel.setWidth(60);
		}
		if(i==3 ){
			jqGridColModel.setWidth(477);
			jqGridColModel.setFormatter("txtFormatter");
		}
		
		jqGridColModel.setEditable(false);
		CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "77%%");
	tableModel.set("tableWidth", "108%%");
	return tableModel;
}
private JSONObject getTableModelForSelfNominationPopUP(List<String[]> headers) {
	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(1);
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setRowNumbers(true);
	jqGridTableModel.setTableHeight(100);
	jqGridTableModel.setTableWidth(500);
	//jqGridTableModel.setTableButton(true);
	String[] colIndex = headers.get(0);
	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
		jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
	   	jqGridColModel.setWidth(200);
	   	jqGridColModel.setAlign("left");
		if(i==0 ){
			
			jqGridColModel.setAlign("center");
			jqGridColModel.setWidth(60);
			jqGridColModel.setFormatter("txtFormatter");
		}
		
		
		jqGridColModel.setEditable(false);
		CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "40%%");
	tableModel.set("tableWidth", "55%%");
	return tableModel;
}
   	private JSONObject getTableModel(List<String[]> headers)
	{
		CommonMessage.debugMsg("getTableModel");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);
		//CommonMessage.debugMsg("headers.get(0):" + headers.get(0));
		//CommonMessage.debugMsg("headers Completed");
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);		
		//jqGridTableModel.setTableButton(true);		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(1000);
		for(int i =0; i < colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));	
			jqGridColModel.setWidth( 299);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if( i<=1){
				jqGridColModel.setHidden(true);				
			}
			else if(i>2) {
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("right");
			}
			if (i==1){
				jqGridColModel.setKey(true);
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 tableModel.set("tableHeight", "45%%");
		 tableModel.set("tableWidth", "100%%");
		 return tableModel;
	}
 private void selfNominationDelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
 		HttpSession httpSession = request.getSession(false);
 		PrintWriter out1 = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	    if( httpSession != null && user != null){	
		   List<EntTlSelfnominationmst> existEntTlSelfnominationmsts = (List<EntTlSelfnominationmst>)httpSession.getAttribute("entTlSelfnominationmst");	    		
		   EntTlSelfnominationmst neweEntTlSelfnominationmst=new EntTlSelfnominationmst();			
		   neweEntTlSelfnominationmst.setSnomCreatedby(user.getUsrm_ccno());
		   String SelfNomination = request.getParameter("SelfNomination");	    	   			 
		try {
	 		  	 List<EntTlSelfnominationmst> NominationList1 = null;
	       		 JSONArray Gridjson1 = null;
	            
	       		      if(UIUtils.isValidKeyId(SelfNomination)){
	   					    Gridjson1 = JSONArray.fromString(SelfNomination); 
	   						NominationList1=(List<EntTlSelfnominationmst>)UIUtils.convertJSONArrToList(neweEntTlSelfnominationmst, Gridjson1);
	   						for(int i=0;i<NominationList1.size();i++){
	   							NominationList1.get(i).setSnomEmpid(user.getUsrm_ccno());
	   							NominationList1.get(i).setSnomCreatedby(user.getUsrm_ccno());
	   							NominationList1.get(i).setSnomApprovedby(user.getUsrm_ccno());
	                  }
	       		}
	       	existEntTlSelfnominationmsts = EntTlProgCalendarService.deleteSelf(NominationList1);																
			JSONObject successData = new JSONObject();
			CommonMessage.debugMsg("SuccessData");
			successData.put("errMsg", false);
			successData.put("msg","Record Can't be Deleted Reference Found");
			JSONObject returnData = new JSONObject();						
			returnData.put("successData", successData);
			returnData.put("formClear", true);	
			CommonMessage.debugMsg("successData"+successData);		
			//return returnData.toString();						
		}
	catch(Exception e)
	{				
		if(e.getMessage()==null || e.getMessage()=="" ||e.getMessage()==" " ){
			JSONObject successData = new JSONObject();
			successData.put("msg"," Record Can't be Deleted Reference Found");
		}
		JSONObject err = new JSONObject();
		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");			
		JSONObject successData = new JSONObject();
		successData.put("errMsg", true);
		successData.put("msg"," Record Can't be Deleted Reference Found");
		err.put("successData", successData);		
		e.printStackTrace();
		out1.print(err.toString());
	  }
   }	
		
}

   
}