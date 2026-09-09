package com.akranta.tpm.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.util.SystemOutLogger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.model.EntTlFacultymst;
import com.akranta.tpm.model.EntTlFacultytopiclink;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlRoleTopicLink;
import com.akranta.tpm.model.EntTlTaskmst;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CompanyBean;
import com.akranta.tpm.bean.EntTlProgTargetSkillsBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.model.Company;
import com.akranta.tpm.service.EntTlProgrammstService;
import com.akranta.tpm.service.TrainingCalendarService;
import com.akranta.tpm.service.impl.EntTlProgrammstServiceImpl;
import com.akranta.tpm.service.impl.TrainingCalendarServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class TrainingcalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;
	TrainingCalendarService trainingCalendarService ;
	EntTlProgrammstService  entTlProgrammstService ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainingcalendarServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
    private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		ComboFilter comboFilter = new ComboFilter();
		String loginFlid = CommonFunctions.getLoginFlid(request);
		String locn=CommonFunctions.getLoginLocaton(request);
		CommonMessage.debugMsg("loginFlid"+loginFlid);
		CommonMessage.debugMsg("locn"+locn);
		CommonMessage.debugMsg(" action " + action);		
		trainingCalendarService=(TrainingCalendarServiceImpl)UIUtils.getServiceObject(request, "TrainingCalendarServiceImpl");
		entTlProgrammstService = ( EntTlProgrammstServiceImpl)UIUtils.getServiceObject(request,"EntTlProgrammstServiceImpl");
		
		String filter = request.getParameter("q");
		CommonMessage.debugMsg("filter"+filter);
		
		//ComboFilter currentFilter = new ComboFilter();
		comboFilter.setCode(filter!=null?filter.toUpperCase():filter);
		CommonMessage.debugMsg("currentFilter"+comboFilter);
		comboFilter.setName(filter!=null?filter.toUpperCase():filter);
		CommonMessage.debugMsg("currentFilter"+comboFilter);
		
		
		if(action.equals("trngcalendar_input.tcl")){
			 
			 //trngcalendar_input.tcl?type=repeat  RepeatTrainingcalendarr.jsp
				String frmMode=request.getParameter("frmMode");
				
				 //CommonMessage.debugMsg("achuievenen::::" );
				/*if(UIUtils.isValidKeyId(type)){
					if(type.equals("repeat")){
						CommonMessage.debugMsg("achuievenen dispatch if::::"+type);	
						UIUtils.forwardRequest(request, response, "/pages/RepeatTrainingcalendarr.jsp");
					}
				}else{
				RequestDispatcher rd= request.getRequestDispatcher("/pages/Trainingcalendarr.jsp"); 
				rd.forward(request, response); 
				}*/
				request.setAttribute("frmMode", frmMode);
				RequestDispatcher rd= request.getRequestDispatcher("/pages/newENT/Trainingcalendarr.jsp"); 
				rd.forward(request, response);
				
			}	
		 	else if(action.equals("topic_fillcombo.tcl")){
			 try {
					CommonMessage.debugMsg("TOPIC---mmmmmas");
					comboFilter = UIUtils.fillComboFilter(request);
					String roleid = request.getParameter("roleId");
					String flid = request.getParameter("flId");
					String relTo = request.getParameter("relTo");
					String topicType = request.getParameter("topicType");
					//relTo="GT";
					CommonMessage.debugMsg("relTo " +relTo);
					CommonFilter commonFilter = new CommonFilter();
					commonFilter.setFlid(flid);
					commonFilter.setRange(roleid);//for Sending role id in commonfilter
					commonFilter.setRelatedToMchMld(topicType);//for Sending relatedtoTopic id in commonfilter
					List<ComboBox> topicData = trainingCalendarService.getTopic(commonFilter,comboFilter);
					UIUtils.writeComboBox(response,topicData,comboFilter);
					CommonMessage.debugMsg("mTOPIC");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		 }
		
		 if(action.equals("trngcalendarEntry_input.tcl")){
			 	String mode = request.getParameter("mode");
			 	String Freqmode = request.getParameter("Freqmode");
			 	String progid = request.getParameter("progid");
			 	String frmMode = request.getParameter("frmMode");
			 	String flid = request.getParameter("flid");
			 	request.setAttribute("frmMode", frmMode);
			 	request.setAttribute("mode", mode);
				CommonMessage.debugMsg(mode+"    Freqmode:::::: "+Freqmode);
			 	 
			 	EntTlProgrammst entTlProgramMst = new EntTlProgrammst();
			 	if("modify".equals(mode)){
				 	entTlProgramMst = trainingCalendarService.getprogdata("","","",progid,flid);
				 	request.setAttribute("entTlProgramMst", entTlProgramMst);
				 	httpSession.setAttribute("entTlProgrammst_Servlet", entTlProgramMst);
			 	}
			    if("Frequecychange".equals(Freqmode)){
			 		CommonMessage.debugMsg("Freqmode:::::: "+Freqmode);
			 		Boolean Frequency = trainingCalendarService.getFrequecyData(progid);
			 		CommonMessage.debugMsg("Frequency :::::::::::"+Frequency);
				 	 request.setAttribute("FrequeEnable", Frequency);
			 	}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/newENT/TrainingCalendar.jsp"); 
				rd.forward(request, response); 				
			}
//		 else if(action.equals("facultyCombo.tcl"))
// 			 
//			{
////	String loginFlid = CommonFunctions.getLoginFlid(request);
//
//			CommonFilter  commonFilter = new CommonFilter();
//			//String locnid;
//		//	List<ComboBox> comboList = new ArrayList<ComboBox>();
//
//			
//						if( UIUtils.isValidKeyId(loginFlid)){
//					commonFilter.setFlid(loginFlid);
//			}
//				comboFilter=UIUtils.fillComboFilter(request);		
//				String location=request.getParameter("locnid");
//			 CommonMessage.debugMsg("locnid"+location);
//				if( UIUtils.isValidKeyId(location))
//				{
//					commonFilter.setAbnAllch(location);
//				}else{
//					
//					String	locnid=CommonFunctions.getLoginLocaton(request);
//						commonFilter.setAbnAllch(locnid);
//					
//					
//				}
//				
//				List<ComboBox>	comboList = trainingCalendarService.getFacultyComboList(commonFilter,comboFilter);	
//				UIUtils.writeComboBox(response,comboList,comboFilter);
//				CommonMessage.debugMsg("mTOPIC");
//			}
		 
		 else if(action.equals("facultyCombo.tcl")) {
			    CommonFilter commonFilter = new CommonFilter();

			    comboFilter = UIUtils.fillComboFilter(request);

			    // Scenario 1: User changed functional location in JSP
			    // → frmNewTraCal_FuntLocHierarchy_SuccessCallBack sends sectId directly (e.g. SEC0000016)
			    String sectId = request.getParameter("sectId");
			    CommonMessage.debugMsg("sectId from JSP: " + sectId);

			    // Scenario 2: Page load — JSP sends flId (fnln_keyid like FNL000000029)
			    String selectedFlid = request.getParameter("flId");
			    CommonMessage.debugMsg("flId from JSP: " + selectedFlid);

			    // Location — for fallback
			    String location = request.getParameter("locnid");
			    if (UIUtils.isValidKeyId(location)) {
			        commonFilter.setAbnAllch(location);
			    } else {
			        String locnid = CommonFunctions.getLoginLocaton(request);
			        commonFilter.setAbnAllch(locnid);
			    }

			    // Priority: sectId (from JSP loc change) > flId (from JSP load) > loginFlid (session)
			    if (UIUtils.isValidKeyId(sectId)) {
			        // sectId is already SEC originalid — pass directly, skip resolveSectId
			        commonFilter.setSect(sectId); // reuse sect field to carry SEC originalid
			    } else if (UIUtils.isValidKeyId(selectedFlid)) {
			        commonFilter.setFlid(selectedFlid);
			    } else if (UIUtils.isValidKeyId(loginFlid)) {
			        commonFilter.setFlid(loginFlid);
			    }

			    // Future: "other faculty" checkbox
			    String facultyFilterType = request.getParameter("others"); // JSP sends "N" or "Y"
		//	    commonFilter.setRemarks("Y".equals(facultyFilterType) ? "other" : "dmt");
			    
			    commonFilter.setIsFacultyLevel("Y".equals(facultyFilterType) ? "other" : "dmt");

			    List<ComboBox> comboList = trainingCalendarService.getFacultyComboList(commonFilter, comboFilter);
			    UIUtils.writeComboBox(response, comboList, comboFilter);
			    CommonMessage.debugMsg("mTOPIC");
			}
		 else if(action.equals("uniquePositionLink_getCol.tcl")){
			 try {
					String progKeyid = request.getParameter("topicId");
					 
					List<String[]> facultyList = trainingCalendarService.getUniqPosData(progKeyid);
					JSONObject jsonObject = getfacultyTableModel(facultyList);
					CommonMessage.debugMsg("Table model");
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 } else if(action.equals("uniquePositionLink_getData.tcl")){
			 PrintWriter out = response.getWriter();
			 String progKeyid = request.getParameter("topicId");
			 
				List<String[]> uniqposList = trainingCalendarService.getUniqPosData(progKeyid);
				
			 JSONObject facultydata = UIUtils.convertToJqGridTableObject( uniqposList, request, 1, 0);
			  out.println(facultydata);
		 }
		 else if(action.equals("facultyLink_getCol.tcl")){
			 try {
					String progKeyid = request.getParameter("topicId");
					String progMonth = request.getParameter("progMonth");
					String uniqPos = request.getParameter("uniqPos");
					List<String[]> facultyList = trainingCalendarService.getFaculty(progKeyid, progMonth,uniqPos);
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
		
		 else if(action.equals("facultyLink_getData.tcl")){
			 PrintWriter out = response.getWriter();
			 String progKeyid = request.getParameter("topicId");
			 String progMonth = request.getParameter("progMonth");
			 String uniqPos = request.getParameter("uniqPos");
			 List<String[]> facultyList = trainingCalendarService.getFaculty(progKeyid, progMonth,uniqPos);
				
			 JSONObject facultydata = UIUtils.convertToJqGridTableObject( facultyList, request, 1, 0);
			 out.println(facultydata);
		 }
		 else if(action.equals("trngProgSelect_Select.tcl"))
		 {
			 PrintWriter out =response.getWriter();
			 String topicid=request.getParameter("toicpid");
			 CommonMessage.debugMsg("progId" +topicid);	
			List<String[]> Progid = trainingCalendarService.selectProgId(topicid);
			 CommonMessage.debugMsg("newEntTlProgrammst ::"+Progid);
			 out.print( JSONArray.fromCollection(Progid));
		 }
		 else if(action.equals("venuExistsValidation_input.tcl")){ 
			 PrintWriter out = response.getWriter();
			 String venuId = request.getParameter("venuId");
			 String fromTime = request.getParameter("fromTime");
			 String tillTime = request.getParameter("tillTime");
			 
			 try{
			
			 String bachKeyid = trainingCalendarService.getVenuAlloated(venuId, fromTime, tillTime);
			 
			 
			 if(UIUtils.isValidKeyId( bachKeyid ))
				 out.print(bachKeyid);
			 
			 }catch(Exception e){ 
				 CommonMessage.debugMsg("existsMst NUNuuuul");	
				 out.print("No Record");
			 }
				 			 
		 
		 }
		 
		 else if(action.equals("progExistData_input.tcl")){
			 PrintWriter out =response.getWriter();
			 String flid=request.getParameter("flid");
			 String topicid=request.getParameter("topicid");
			 String porkeyid=request.getParameter("porkeyid");
			 String progMonth= request.getParameter("progMonth");
			 String uniquePos= request.getParameter("uniquePos");
			 
			 EntTlProgrammst entTlProgramMst = new EntTlProgrammst();
			 try{
			
			 entTlProgramMst = trainingCalendarService.getprogdata(topicid,progMonth,uniquePos,porkeyid,flid);
			 
			 
			 if(entTlProgramMst!=null )
				 out.print(JSONArray.fromObject( entTlProgramMst));
			 }catch(Exception e){ 
				 CommonMessage.debugMsg("entTlProgramMstentTlProgramMst NUNuuuul");	
				 out.print("No Record");
			 }
				 
		 	 httpSession.setAttribute("entTlProgrammst_Servlet", entTlProgramMst);
			 
			  
		 }
		 else if(action.equals("trngcalendar_getCol.tcl")){
			/*
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal");			 
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal"));
			*/
			CommonMessage.debugMsg("achuievenen");		
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",true);
			String formMode = request.getParameter("frmMode");
			commonFilter.setType(formMode);
			CommonMessage.debugMsg(formMode+" FromMode  "+commonFilter.getType());
			CommonMessage.debugMsg(formMode+" FLIFLFIFLFIF  "+commonFilter.getFlid());
			try {
				
				List<String[]> trReport = trainingCalendarService.getTrainingCalendarList(commonFilter);
				/**New ColModel*/
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setGroupBy(false);
				//jqGridTableModel.setGroupByField("MONTHS");
				
				gridColModel.setHeaderNum(1);
				
				String [] colHeader = trReport.get(1);			
				String[] colHeaderCond = trReport.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "106%%");
				jsonObject.set("tableHeight", "78%%");
				jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
				/***/
				//JSONObject jsonObject = getTableModel(trReport);
				httpSession.removeAttribute("trngCalReportColModel");
				httpSession.setAttribute("trngCalReportColModel", jsonObject);
				CommonMessage.debugMsg("Table model");
				CommonMessage.debugMsg("jsonObject "+jsonObject);
				out.println(jsonObject);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("trngcalendar_getData.tcl")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",false);
			String formMode = request.getParameter("frmMode");
			commonFilter.setType(formMode);
			CommonMessage.debugMsg(formMode+" FromModeGetData  "+commonFilter.getType());
			try {
				
				List<String[]> trReport = trainingCalendarService.getTrainingCalendarList(commonFilter);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				JSONObject QMreports = UIUtils.convertToJqGridTableObject(trReport, request, 2, 0,commonFilter.getTotalRecordCnt());
				out.println(QMreports);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else if(action.equals("trngcalendar_getExcel.tcl")){							
				CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",true);
				commonFilter.setViewClick('Y');
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("trngCalReportColModel");
				tblJSONObj.put("title", "Training Calendar");
				String format = ExcelUtils.getFormat(request);
				
				Workbook wb = trainingCalendarService.getTrngCalExportExcel(commonFilter,tblJSONObj,format);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
		}
		 else if(action.equals("batchMst_getCol.tcl")){
			 try {
					String progKeyid = request.getParameter("topicId");
					String progMonth = request.getParameter("progMonth");
					String uniqPos = request.getParameter("uniqPos");
					
					List<String[]> batchList = trainingCalendarService.getbatch(progKeyid, progMonth, uniqPos);
					JSONObject jsonObject = getBatchTableModel(batchList);
					CommonMessage.debugMsg("Table model");
					CommonMessage.debugMsg("jsonObject "+jsonObject);
					PrintWriter  out = response.getWriter();
					out.println(jsonObject);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		 else if(action.equals("batchMst_getData.tcl")){
			 PrintWriter out = response.getWriter();
			 String progKeyid = request.getParameter("topicId");
				String progMonth = request.getParameter("progMonth");
				String uniqPos = request.getParameter("uniqPos");
				
				List<String[]> batchList = trainingCalendarService.getbatch(progKeyid, progMonth, uniqPos);
			 JSONObject facultydata = UIUtils.convertToJqGridTableObject( batchList, request, 1, 0);
			  out.println(facultydata);
		 } 
		else if(action.equals("trngcal_getCol.tcl")){
			PrintWriter out = response.getWriter();
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal");
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal"));
		}
		else if(action.equals("trngcal_getData.tcl")){
			//PrintWriter out = response.getWriter();
			//String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal");
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal"));
		}
		else if (action.equals("trngcal_input.tcl")) 
		{
			CommonMessage.debugMsg("report the jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/newENT/Trainingcalendarr_rpt.jsp"); 
			//rd.forward(request, response); 
		}
		/**BY BABU**/
		 if(action.equals("trngcalendarRpt_input.tcl")){
			 	String mode = request.getParameter("mode");
			 	request.setAttribute("mode", mode);
			 	RequestDispatcher rd = request.getRequestDispatcher("/pages/newENT/Trainingcalendarr_rpt.jsp"); 
				rd.forward(request, response); 				
			}
		 /**Added For Report**/
		 else if(action.equals("trngcalendarRpt_getCol.tcl")){
			/*
			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal");			 
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.TrainingCalendar", "TrainingCal"));
			*/
			CommonMessage.debugMsg("achuievenen");		
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",true);
			try {
				
				List<String[]> trReport = trainingCalendarService.getTrainingCalendarRPT(commonFilter);
				/*
				JSONObject jsonObject = getTableModel(trReport);
				CommonMessage.debugMsg("Table model");
				CommonMessage.debugMsg("jsonObject "+jsonObject);
				out.println(jsonObject);
				*/
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);			
				gridColModel.setHeaderNum(2);
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("MONTHS");
				String [] colHeader = trReport.get(1);
				String [] colHeader1 = trReport.get(2);			
				String [] colHeaderCond = trReport.get(0);
				
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				headers.add(colHeader1);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "106%%");
				jsonObject.set("tableHeight", "78%%");
				jsonObject.put("data", trReport);
				
				CommonMessage.debugMsg(jsonObject);
				
				httpSession.removeAttribute("trngCalReportColModel");
				httpSession.setAttribute("trngCalReportColModel", jsonObject);

				out.println(jsonObject);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action.equals("trngcalendarRpt_getData.tcl")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",true);
			try {
				
				List<String[]> trReport = trainingCalendarService.getTrainingCalendarList(commonFilter);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("get data method1");
				JSONObject QMreports = UIUtils.convertToJqGridTableObject(
						trReport, request, 3, 0);
				out.println(QMreports);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//EXCEL
		else if( action.equals("trngcalendarRpt_getExcel.tcl") ){
			
			CommonFilter commonFilter = populateCommonFilter(request,"TrainingCalendarFilter",true);
			commonFilter.setViewClick('Y');
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			
			JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("trngCalReportColModel");
			tblJSONObj.put("title", "Training Calendar");
			String format = ExcelUtils.getFormat(request);
			
			Workbook wb = trainingCalendarService.getTrngCalReportExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "TrainingCalendarReport", format);
			
		}

		/**END*/
		else  if(action.equals("trngcalendarEntry_save.tcl")){
			ProgrammstBean programmstBean = new ProgrammstBean(null);
			CommonMessage.debugMsg("Out the save");
			savetrnCalendar(request,response,programmstBean);
			CommonMessage.debugMsg("after the save");
		}
		else if(action.equals("trngcalendarEntry_delete.tcl")){
			deleteTrnCalendar(request,response);
		}
		else if(action.equals("deleteDetail.tcl")){
			deleteDetail(request,response);
			 /*PrintWriter out = response.getWriter();
			try{
		    
			 String keyId = request.getParameter("keyid");
			 String gridId = request.getParameter("gridId");
			 String topicId = request.getParameter("progId");
			 String delSuc = trainingCalendarService.deleteDetail(keyId,gridId,topicId);
			 JSONObject json = new JSONObject();
			 json.put("msg",delSuc);
			 json.put("gridid",gridId);
 			 out.print(json.toString());
			}catch(BusinessApplicationExceptions e)
 			{
 				CommonMessage.debugMsg("Business EXC "+e);
 				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Reviewpoint");
 				out.print(errMessage.toString());		
 			}*/
		}
		else if(action.equals("AddbatchEmp_input.tcl")){
			try{
				CommonMessage.debugMsg("AddbatchEmp_input.tcl ");
				String batchId = request.getParameter("batchId");
				String progKeyid = request.getParameter("progKeyid");
			//	CommonMessage.debugMsg("2 ");
				String bachDate = request.getParameter("bachDate");
			//	CommonMessage.debugMsg("3 ");
				String type = request.getParameter("type");
			//	CommonMessage.debugMsg("4 ");
				String flid = request.getParameter("flid");
			//	CommonMessage.debugMsg("flid:" +flid);
				String newFlid = request.getParameter("newFlid");
			//	CommonMessage.debugMsg("newFlid " +newFlid);
				String roleId = request.getParameter("roleId");
			//	CommonMessage.debugMsg("roleId " +roleId);
				
				request.setAttribute("batchId", batchId);
				request.setAttribute("bachDate", bachDate);
				request.setAttribute("progKeyid",progKeyid);
				request.setAttribute("flid",flid);
				request.setAttribute("type",type);
				RequestDispatcher rd= request.getRequestDispatcher("/pages/newENT/addBatchEmployee.jsp"); 
				//CommonMessage.debugMsg("6 ");
				rd.forward(request, response);
			}catch(Exception e){
				//CommonMessage.debugMsg("AddbatchEmp_input.tcl  ex" +e);
			}
		
		}
		else if(action.equals("AddBachEmployee_getCol.tcl")){
			PrintWriter out = response.getWriter();
			 String getCol = UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelBachEmployee");
			 httpSession.removeAttribute("UserColmodel");
			 httpSession.setAttribute("UserColmodel", getCol);
			 out.print(getCol);
			 out.close();
		}
		else if(action.equals("AddBachEmployee_getData.tcl")||action.equals("AddBachEHS_getData.tcl")){
			 CommonMessage.debugMsg("AddbatchEmp_getData.tcl ");
			// String search = request.getParameter("_search");
			 httpSession.removeAttribute("BatchEmployeeFilter");
			 CommonFilter commonFilter=populateCommonFilter(request, "BatchEmployeeFilter", false);
			 httpSession.setAttribute("BatchEmployeeFilter",commonFilter);
			 CommonMessage.debugMsg("commonFilter:" +commonFilter);
			 String bachId = request.getParameter("bachId");
			 String flid = request.getParameter("flid");			 
			 String progKeyid = request.getParameter("progKeyid");
			 String type = request.getParameter("type");
			 String roleId=request.getParameter("roleId");
			 String newFlid=request.getParameter("newFlid");		 
			
			// int rowCount =trainingCalendarService.selectCount(commonFilter);
			// int rowCount =Integer.parseInt(trainingCalendarService.getUpbasedEmployeeCount( commonFilter,progKeyid,bachId,flid,type,roleId,newFlid));
			//	CommonMessage.debugMsg("count...."+rowCount);
				//CommonMessage.debugMsg("Count from Servlet:" +rowCount);
				//Long totalCnt = (long)rowCount;
				//commonFilter.setTotalRecordCnt(totalCnt);
			 List<String[]> getEmploye = trainingCalendarService.getUpbasedEmployee( commonFilter,progKeyid,bachId,flid,type,roleId,newFlid);
			/* if(search.equals("true"))
				{
					rowCount = getEmploye.size();
					totalCnt = (long)rowCount;
					commonFilter.setTotalRecordCnt(totalCnt);
				}
				*/
				//CommonMessage.debugMsg(totalCnt+" totalCnt " + getEmploye.size()); 	
			 httpSession.removeAttribute("BatchEmployeeFilter");	
			 JSONObject empdata = UIUtils.convertToJqGridTableObject( getEmploye, request, 0, 0,commonFilter.getTotalRecordCnt());
			 PrintWriter out = response.getWriter();
			 out.println(empdata);
			 httpSession.removeAttribute("BatchEmployeeData");
			 httpSession.setAttribute("BatchEmployeeData", empdata);
		}
		else if(action.equals("bachEMployee_save.tcl")){
			
		}
		else if(action.equals("getPermitedStrength_Select.tcl")){
			 PrintWriter out =response.getWriter();
			 String venuId=request.getParameter("venuId");
			 CommonMessage.debugMsg("venuId" +venuId);	
			 String permStrength = trainingCalendarService.getPermStrength(venuId);
			 CommonMessage.debugMsg("Strength  ::"+permStrength);
			 out.print(permStrength);
		}
	}
    private void deleteDetail(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
    	PrintWriter out = response.getWriter();
    	 String keyId = request.getParameter("keyid");
		 String gridId = request.getParameter("gridId");
		 String topicId = request.getParameter("progId");
		try{
		 String delSuc = trainingCalendarService.deleteDetail(keyId,gridId,topicId);
		 JSONObject json = new JSONObject();
		 json.put("msg",delSuc);
		 json.put("gridid",gridId);
			 out.print(json.toString());
		}catch(BusinessApplicationExceptions e)
			{
				//CommonMessage.debugMsg("Business EXC "+e.getMessage());
				//JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Reviewpoint");
				//CommonMessage.debugMsg("Bus error  :   "+errMessage.toString());
				/*JSONObject tpmException = new JSONObject();
				JSONObject err = new JSONObject();
				tpmException.put("msg", "Data Not Deleted");
				tpmException.put("errMsg" , e.getMessage());
				err.put("msg",errMessage);
				out.print(err.toString());*/
				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				successData.put("gridId",gridId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("msg"," Record Can't be Deleted Reference Found");
				out.print(returnData.toString());
			}
		
	}
	private JSONObject getBatchTableModel(List<String[]> batchList) {
		// TODO Auto-generated method stub
JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = batchList.get(0);		
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);  
		jqGridTableModel.setTableWidth(600);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "")); 
			jqGridColModel.setWidth(220);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);	 
			
			if (i <=3 ) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			
			if(i ==4 )  {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}

			if(i == 5   )  {
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i == 6  )  {
				jqGridColModel.setWidth(60);
				jqGridColModel.setAlign("left");
			}
			
			if(i==colHeader.length-2){
				jqGridColModel.setFormatter("btnEmpFormater");
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("center");
			}
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}
			 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "45%%");
		return tableModel;
	}

	private JSONObject getfacultyTableModel(List<String[]> facultyList) {
		// TODO Auto-generated method stub
    	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = facultyList.get(0);		
		 
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);  
		jqGridTableModel.setTableWidth(600);
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
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			} 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "30%%");
		return tableModel;

	}

	private void deleteTrnCalendar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
    	CommonMessage.debugMsg("inside Program Mst delete");
    	HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		
		    	if( httpSession != null && user != null)
		    	{	EntTlProgrammst existEntTlProgrammst = (EntTlProgrammst)httpSession.getAttribute("entTlProgrammst");
		    		EntTlProgrammst newEntTlProgrammst = new EntTlProgrammst();
		    		newEntTlProgrammst = (EntTlProgrammst)UIUtils.setBeanProperties((Object)newEntTlProgrammst,request);
		    	try{
		    		existEntTlProgrammst = trainingCalendarService.deleteRec( newEntTlProgrammst );

					/*JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("msg", existEntTlProgrammst);
					returnData.put("formClear",true);	
					returnData.put("successData",successData);
					out.print(returnData.toString());*/
		    		httpSession.setAttribute(existEntTlProgrammst.getProgKeyid(), existEntTlProgrammst);		
					JSONObject mode = new JSONObject();
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("TmkmKeyid",newEntTlProgrammst.getProgKeyid());					
					JSONObject successData = new JSONObject();
					successData.put("errMsg", false);
					successData.put("TmkmKeyid",newEntTlProgrammst.getProgKeyid());
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					successData.put("keyId", existEntTlProgrammst.getProgKeyid());
					JSONObject returnData = new JSONObject();						
					returnData.put("successData", successData);
					returnData.put("formClear", true);	
					CommonMessage.debugMsg("successData"+successData);		
					out.print(returnData.toString());
					//out.print()	
					CommonMessage.debugMsg("end of program save");
		    	}
			catch(Exception e){
				CommonMessage.debugMsg("Exception: master "+newEntTlProgrammst.getProgKeyid());
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");			
				JSONObject successData = new JSONObject();
				successData.put("TKeyid",newEntTlProgrammst.getProgKeyid());	
				successData.put("errMsg", true);
				successData.put("msg"," Record Can't be Deleted Reference Found");
				err.put("successData", successData);		
				e.printStackTrace();
				out.print(err.toString());
			}
		  }    		
	}

	private void savetrnCalendar(HttpServletRequest request,
			HttpServletResponse response, ProgrammstBean programmstBean) throws IOException {
		// TODO Auto-generated method stub
    	CommonMessage.debugMsg("inside Program Mst Save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String type=request.getParameter("type");
		CommonMessage.debugMsg("type"+type);

			try{
		    	if( httpSession != null && user != null)
		    	{	
		    		EntTlProgrammst newEntTlProgrammst = new EntTlProgrammst();
		    		EntTlProgrammst existEntTlProgrammst = (EntTlProgrammst)httpSession.getAttribute("entTlProgrammst_Servlet");
		    		/**BatchMst**/
		    		EntBatchMst newEntBatchMst = new EntBatchMst();
		    		EntBatchMst existEntBatchMst = (EntBatchMst)httpSession.getAttribute("entBatchMst_Servlet");
		    		/**TopicFaculty**/
		    		EntTlFacultytopiclink newEntTlFacultytopiclink = new EntTlFacultytopiclink();
		    		EntTlFacultytopiclink existEntTlFacultytopiclink=(EntTlFacultytopiclink)httpSession.getAttribute("EntTlFacultytopiclink_s");
		    		/**RoleTopicLink**/
		    		EntTlRoleTopicLink newEntTlRoleTopicLink = new EntTlRoleTopicLink();
		    		EntTlRoleTopicLink existEntTlRoleTopicLink=(EntTlRoleTopicLink)httpSession.getAttribute("EntTlRoleTopicLink_s");
		    		/*ProgTargetSkills**/
		    		EntTlProgTargetSkills newEntTlProgTargetSkills = new EntTlProgTargetSkills();
		    		EntTlProgTargetSkills existEntTlProgTargetSkills = (EntTlProgTargetSkills)httpSession.getAttribute("EntTlProgTargetSkills_s");
		    		//EntTlProgTargetSkillsBean entTlProgTargetSkillsBean = new EntTlProgTargetSkillsBean();
		    		EntTlBatchSchedule newEntTlBatchSchedule = new EntTlBatchSchedule();
		    		
		    		newEntTlBatchSchedule.setBsdlCreatedby(user.getUsrm_ccno());
		    		newEntTlProgrammst.setProgCreatedby(user.getUsrm_ccno());
		    		newEntBatchMst.setBachCreatedby(user.getUsrm_ccno()); 
		    		
		    		newEntTlProgrammst = (EntTlProgrammst)UIUtils.setBeanProperties((Object)newEntTlProgrammst,request);
		    		newEntBatchMst = (EntBatchMst)UIUtils.setBeanProperties((Object)newEntBatchMst,request);
		    		newEntTlFacultytopiclink = (EntTlFacultytopiclink)UIUtils.setBeanProperties((Object)newEntTlFacultytopiclink,request);
		    		newEntTlProgTargetSkills = (EntTlProgTargetSkills)UIUtils.setBeanProperties((Object)newEntTlProgTargetSkills,request);
		    		newEntTlRoleTopicLink  = (EntTlRoleTopicLink)UIUtils.setBeanProperties((Object)newEntTlRoleTopicLink,request);
		    		/** setting values to progObject when batch or faculty is added**/
		    		if(UIUtils.isValidKeyId(newEntBatchMst.getIsbatch())){
		    			newEntBatchMst.setBachFromdate(newEntBatchMst.getBachTilldate()+" "+newEntBatchMst.getBachFromTime());
		    			newEntBatchMst.setBachTilldate(newEntBatchMst.getBachTilldate()+" "+newEntBatchMst.getBachTillTime());
		    			CommonMessage.debugMsg("get batch from time "+newEntBatchMst.getBachFromdate());
		    			newEntTlProgrammst.setBatchmaster(newEntBatchMst);
		    			
		    			/**fill progTargetSkills**/
			    		newEntTlProgTargetSkills.setPrtsProgKeyid(newEntTlProgrammst.getProgKeyid() );
			    		newEntTlProgTargetSkills.setPrtsTopiKeyid(newEntTlProgrammst.getProgCode() );
			    		newEntTlProgTargetSkills.setPrtsEffFromDate(newEntTlProgrammst.getBatchmaster().getBachTilldate());
			    		newEntTlProgTargetSkills.setPrtsEffTillDate(newEntTlProgrammst.getBatchmaster().getBachTilldate());
			    		newEntTlProgTargetSkills.setPrtsSkilEvaluvationtype("EVL03");
			    		newEntTlProgTargetSkills.setPrtsSkilDeliverymode("PDM02");
			    		newEntTlProgTargetSkills.setPrtsRatingType("A");
			    		newEntTlProgrammst.setProgTargetSkills( newEntTlProgTargetSkills);
			    		newEntTlProgrammst.setEntTlBatchSchedule(newEntTlBatchSchedule);
			    		CommonMessage.debugMsg("testDta  "+newEntTlProgrammst.getProgTargetSkills().getPrtsProgKeyid());
			    		
			    		CommonMessage.debugMsg("BachKeyid=== "+newEntBatchMst.getBachKeyid());
			    		/*END*/
		    		}
		    		if(UIUtils.isValidKeyId(newEntTlFacultytopiclink.getIsFacultyLink()))
		    			newEntTlProgrammst.setFacultyTopicLink(newEntTlFacultytopiclink);
		    		/** setting values to progObject when Uniqueposition is added**/
		    		if(UIUtils.isValidKeyId(newEntTlRoleTopicLink.getIsUniquePositionLink())){
		    			//CommonMessage.debugMsg("getIsUniquePositionLink():" +newEntTlRoleTopicLink.getIsUniquePositionLink());
		    			newEntTlProgrammst.setRoleTopicLink(newEntTlRoleTopicLink);
		    			//CommonMessage.debugMsg("getIsUniquePositionLink():" +newEntTlProgrammst.getRoleTopicLink().getIsUniquePositionLink());
		    		}
		    			
		    		String saveMsg ;
		    		CommonMessage.debugMsg("Test 1");
		    		String allUnique = request.getParameter("allUniquePosition");
		    		CommonMessage.debugMsg("allUnique=="+allUnique);
		    		if (UIUtils.isValidKeyId(allUnique))
		    			programmstBean.setAllUniquePosition(allUnique);
					
					//saveMsg = "Data Saved Successfully";
					if( ! UIUtils.isValidKeyId(newEntTlProgrammst.getProgKeyid()) )
					{	
						CommonMessage.debugMsg("key id is not available");
						existEntTlProgrammst =	trainingCalendarService.create(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
						 
						saveMsg = "Data Saved Successfully";
					}	
					else{
						CommonMessage.debugMsg("key id is  available");
						existEntTlProgrammst =	trainingCalendarService.update(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
						saveMsg = "Data Updated  Successfully";
					}
					httpSession.setAttribute("progkeyAfterSave", existEntTlProgrammst.getProgKeyid());
					//newEntTlProgrammst.setProgKeyid(existEntTlProgrammst.getProgKeyid());
					
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("msg", saveMsg);
					successData.put("keyId",existEntTlProgrammst.getProgKeyid());
					successData.put("calKeyid",existEntTlProgrammst.getEcalkeyid());
					successData.put("prtrKeyid",existEntTlProgrammst.getPrtrkeyid());
					returnData.put("formClear",false);	
					returnData.put("type",type);
					returnData.put("successData",successData);
					out.print(returnData.toString());
					//out.print()	
					CommonMessage.debugMsg("end of program save");
					CommonMessage.debugMsg("existEntTlProgrammst "+existEntTlProgrammst.getProgCreatedon());
					httpSession.setAttribute("entTlProgrammst_Servlet", existEntTlProgrammst);
		    	}
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "EntProgramException");
				out.print(errMessage.toString());
			}
			
			catch(BusinessApplicationExceptions e)
			{
				JSONObject err = new JSONObject();
				if(e.getMessage().contains("UK_FROMTIME"))
				{
					err.put("tpmException", "Session Time  Already Exists");
				}
				else if(e.getMessage().contains("UK_ROLE_TOPIC"))
				{
					err.put("tpmException", "Unique Position Already Exists");
				}
				else if(e.getMessage().contains("CK_PROG_MAX_DURATION"))
				{
					err.put("tpmException", "Enter Duration");
				}
				
				
				else if(e.getMessage().contains("CK_BACH_FROMDATE"))
				{
					err.put("tpmException", "Session Time  Already Exists");
				}
				
				else if(e.getMessage().contains("FK_FTLK_FACULTYID"))
				{
					err.put("tpmException", "Faculty Already Exists");
				}
				else if(e.getMessage().contains("UN_FTLK_FACULTYID"))
				{
					err.put("tpmException", "Faculty Already Exists");
				}
				
				else
				{
				    err.put("tpmException", "Data Not Saved");
				}
				out.print(err.toString());			
	
			}	
			catch(Exception e){				
			    e.printStackTrace();
				JSONObject err = new JSONObject();
				if(e.getMessage().contains("UN_FTLK_FACULTYID"))
				{
					err.put("tpmException", "Faculty Already Exists");
				}
				else if(e.getMessage().contains("CK_BACH_FROMDATE"))
				{
					err.put("tpmException", "Session Time  Already Exists");
				}
				
				else
				{
				    err.put("tpmException", "Data Not Saved");
				}
				out.print(err.toString());			
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
			commonFilter = 	FilterValues.getTraning(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
	
    private JSONObject getTableModel(List<String[]> headers) {

		CommonMessage.debugMsg("getTableModel");
		CommonMessage.debugMsg("headers.size()"+headers.size());
		CommonMessage.debugMsg("headers.get(1).length"+headers.get(1).length);
		CommonMessage.debugMsg("headers.0"+headers.get(0)[0]);
		
		JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = headers.get(1);		
		String[] colIndex = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(500);  
		jqGridTableModel.setTableWidth(800);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setGroupBy(true);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setGroupByField("PROG_MONTH");
		
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", "")); 
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			
			jqGridColModel.setEditable(false);	 
			
			
			/*if (i == 0) {//||(i==1)){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}*/
			
			if (i == 7 || i == 9||i == 11||i == 13) {
				jqGridColModel.setAlign("center");
			}
			if (i == 8 || i == 10||i == 12||i == 14) {
				jqGridColModel.setWidth(150);
				jqGridColModel.setAlign("right");
			}
			if (i == 5 ) {
				jqGridColModel.setAlign("right");
			}
			if (i == 1 ) {
				jqGridColModel.setWidth(200);
			}
			if (i == 15 ||i==16) {
				jqGridColModel.setHidden(true);
			}
			CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "84%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}

}

