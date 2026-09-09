package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.AccessReviewrptService;
import com.akranta.tpm.service.AppAccessReviewrptService;
import com.akranta.tpm.service.ApplicationMaintainanceService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AppAccessReviewrptServlet
 */
@WebServlet("/ApplicationMaintainanceServlet")
public class ApplicationMaintainanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ApplicationMaintainanceService applicationMaintainanceService;
    public ApplicationMaintainanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String mode;

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
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		applicationMaintainanceService=(ApplicationMaintainanceService)UIUtils.getServiceObject(request,"ApplicationMaintainanceServiceImpl");
		applicationMaintainanceService.ApplicationMaintainanceServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		String action = UIUtils.getActionPart(request);
		//CommonMessage.debugMsg(" action " + action);
		
		
		
		if(action.equals("ApplicationMaintainance_input.appm")){
/*			RequestDispatcher rd = request.getRequestDispatcher("/pages/AppcnAccessReviewrpt.jsp");
			rd.forward(request, response);*/
			CommonMessage.debugMsg("------userRealease_input.creat");
			 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/ApplicationMaintainance.jsp");
		     rd.forward(request, response); 
		}
		
		if(action.equals("UserInactive_input.appm")){
			/*			RequestDispatcher rd = request.getRequestDispatcher("/pages/AppcnAccessReviewrpt.jsp");
						rd.forward(request, response);*/
						CommonMessage.debugMsg("------userRealease_input.creat");
						 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/UserInactive.jsp");
					     rd.forward(request, response); 
					}
		
		
		else if (action.equals("EmployeeInactive_input.appm")) {
			/*			RequestDispatcher rd = request.getRequestDispatcher("/pages/AppcnAccessReviewrpt.jsp");
						rd.forward(request, response);*/
					//CommonMessage.debugMsg("Inside Location Transfer");
						 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/UserInactive.jsp");
					     rd.forward(request, response); 
					}
		
		//added by ramu
		
		else if (action.equals("UserAupInactive_input.appm")) {
			/*			RequestDispatcher rd = request.getRequestDispatcher("/pages/AppcnAccessReviewrpt.jsp");
						rd.forward(request, response);*/
					
						 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/UserAupInactive.jsp");
					     rd.forward(request, response); 
					}
			
		
		else if (action.equals("UserAupInactive_getCol.appm")) {
			try{
				PrintWriter out = response.getWriter();
				
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","UserAupInactiveDetails"));
				//CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","UserAupInactiveDetails")); 
			
			}catch(Exception e){
				
				e.printStackTrace();
			}
					}
		
else if (action.equals("UserAupInactive_getData.appm")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"ApplicationMaintainanceCommonFilter",true);
				try {
					   String EmpId=request.getParameter("EmpId");
					    commonFilter.setKey(EmpId);
						List<String[]> MachineGrid = applicationMaintainanceService.getUSerList(commonFilter);
						//CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
						PrintWriter out = response.getWriter();
						//CommonMessage.debugMsg("get data method1");
						JSONObject machinegrid = UIUtils.convertToJqGridTableObject(
								MachineGrid, request, 0, 0);
						out.println(machinegrid);
					
					
				} catch (Exception e) {
					//CommonMessage.debugMsg(e.getMessage());
				}
				
			}
		
		
		
		//added by ramu
		
		
		else if (action.equals("EmployeeInactive_getCol.appm")) {
			try{
				PrintWriter out = response.getWriter();
				
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","EmpInactiveDetails"));
			//	CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","EmpInactiveDetails")); 
			
			}catch(Exception e){
				
				e.printStackTrace();
			}
					}
		else if (action.equals("EmployeeInactive_getData.appm")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"ApplicationMaintainanceCommonFilter",true);
				try {
					   String EmpId=request.getParameter("EmpId");
					    commonFilter.setKey(EmpId);
						List<String[]> MachineGrid = applicationMaintainanceService.getEmployeeList(commonFilter);
						//CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
						PrintWriter out = response.getWriter();
						//CommonMessage.debugMsg("get data method1");
						JSONObject machinegrid = UIUtils.convertToJqGridTableObject(
								MachineGrid, request, 0, 0);
						out.println(machinegrid);
					
					
				} catch (Exception e) {
					//CommonMessage.debugMsg(e.getMessage());
				}
				
			}
			
		else if(action.equals("UserInactive_save.appm")){
			
			updateInactiveUser(request,response);
		}

	else if (action.equals("Locationtransfer_input.appm")) {
				//	CommonMessage.debugMsg("Inside Location Transfer");
						 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/LocationTransfer.jsp");
					     rd.forward(request, response); 
					}
				
	
		else if (action.equals("Locationtransfer_getCol.appm")) {
			try{
				PrintWriter out = response.getWriter();
				
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","EmpDetails"));
				//CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","EmpDetails")); 
			
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
	else if (action.equals("Locationtransfer_getData.appm")){
			
			CommonFilter commonFilter = populateCommonFilter(request,"ApplicationMaintainanceCommonFilter",true);
				try {
					String EmpId=request.getParameter("EmpId");
					String Location=request.getParameter("Location");
					commonFilter.setKey(EmpId);
					commonFilter.setType(Location);
						List<String[]> LocTransGrd = applicationMaintainanceService.getEmployeeLocation(commonFilter);
						//CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
						PrintWriter out = response.getWriter();
						//CommonMessage.debugMsg("get data method1");
						JSONObject LocGrid = UIUtils.convertToJqGridTableObject(
								LocTransGrd, request, 0, 0);
						out.println(LocGrid);
					
					
				} catch (Exception e) {
					//CommonMessage.debugMsg(e.getMessage());
				}
				
			}

	else if(action.equals("LocationTransfer_save.appm")){
		LocationTransfer(request,response);
	}
	
		else if(action.equals("EmployeeActive_input.appm")){
			
			 RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/EmployeeActive.jsp");
		     rd.forward(request, response);
			
		}
		else if (action.equals("EmployeeActive_getCol.appm")) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "EmpActiveGrid");

			JSONObject colmodel = JSONObject.fromString(tableModel);

			httpSession.removeAttribute("UserColmodel");
			httpSession.setAttribute("UserColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		
    else if (action.equals("EmployeeActive_getData.appm")) {
			
			PrintWriter out = response.getWriter();
			GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
			httpSession.setAttribute("gridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request, gridParams);
			CommonFilter commonFilter=new CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter);
			CommonMessage.debugMsg(" Checking first :: "+CommonFunctions.getLoginLocaton(request));
			//CommonMessage.debugMsg("commonFilter.getLocation().getId() " +commonFilter.getLocation().getId());
			//String location=commonFilter.getLocation().getId();
			String location = CommonFunctions.getLoginLocaton(request);
			CommonMessage.debugMsg("gridParams " +gridParams);
			List<String[]> UserList;
			try {
				UserList = applicationMaintainanceService.getEmpActiveData(gridParams,location);

				httpSession.removeAttribute("gridParams");			
				
				JSONObject UserData = UIUtils.convertToJqGridTableObject(UserList, request, 1, 1,gridParams.getTotalRecordCnt());

				out.println(UserData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
    else if(action.equals("AupUSerInActive_save.appm")) {	
		//CommonMessage.debugMsg("applicatio main servlet");
		httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				
				String EmployeeId=request.getParameter("EmployeeId");
				//String ValidTillDate=request.getParameter("ValidTillDate");
				//CommonMessage.debugMsg("ValidTillDate:"+ValidTillDate);
				//String ids[]=jsonArrO.split(";");
				//List<String> empIds=Arrays.asList(ids);
			
				//if (!empIds.isEmpty()) {
					
				//CommonMessage.debugMsg("applicatio main servlet" +EmployeeId);
				applicationMaintainanceService.AupUSerInActive(EmployeeId);
					JSONObject successData = new JSONObject();
					successData.put("msg", "InActive Success");
					//successData.put("title", title);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					returnData.put("formClear", true);
					out.print(returnData.toString());
				//}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
		
		
	}
   
		
		
		
		
    else if(action.equals("EmployeeActiveUpdate_save.appm")){
    	updateEmployeeActive(request,response);
    }
    else if(action.equals("KaizenDateChange_input.appm")){
    	
    	RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/KaizenDateChange.jsp");
	    rd.forward(request, response);
    }

	else if (action.equals("KaizenDateChange_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "KaizenDateChange");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Kaizenmodel");
		httpSession.setAttribute("Kaizenmodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("KaizenDateChange_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);

        String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
        String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
        commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		
		List<String[]> KaizenList;
		try {
			KaizenList = applicationMaintainanceService.getKaizenData(gridParams,commonFilter);

			httpSession.removeAttribute("gridParams");			
			
			JSONObject UserData = UIUtils.convertToJqGridTableObject(KaizenList, request, 1, 1,gridParams.getTotalRecordCnt());

			out.println(UserData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("KaizenDateChange_Update.appm")){
		
		UpdateKaizenDate(request,response);
	}
   
	else if(action.equals("SusaDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/SusaDelete.jsp");
	    rd.forward(request, response);
		
	}

	else if (action.equals("SusaDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "SusaDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Susamodel");
		httpSession.setAttribute("Susamodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("SusaDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		List<String[]>SusaDelList;
		try {
			SusaDelList = applicationMaintainanceService.getSusaData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject SusaData = UIUtils.convertToJqGridTableObject(SusaDelList, request, 0, 1,gridParams.getTotalRecordCnt());
			out.println(SusaData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("SusaDelete_Delete.appm")){
	  SusaDelete(request,response);
	}
		
	else if(action.equals("TrainingCalDelete_input.appm")){

		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/TrainingCalendarDelete.jsp");
	    rd.forward(request, response);
		
	}
		
	else if (action.equals("TrainingCalDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "TrainingDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Trainingmodel");
		httpSession.setAttribute("Trainingmodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("TrainingCalDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
			String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
			commonFilter.setFromDate(fromDate);
			commonFilter.setToDate(toDate);
			commonFilter.setFlid(DMTId);
	        commonFilter.setAwise(JHId);
		
		List<String[]>SusaDelList;
		try {
			SusaDelList = applicationMaintainanceService.getTrainingData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject SusaData = UIUtils.convertToJqGridTableObject(SusaDelList, request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(SusaData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	else if(action.equals("TrainingCalDelete_Delete.appm")){
		
		TrainingDelete(request,response);
	}
		
	else if(action.equals("SuggestionDelete_input.appm")){
		
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/SuggestionDelete.jsp");
	    rd.forward(request, response);
	}
		
	else if (action.equals("SuggestionDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "SuggestionDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Suggestionmodel");
		httpSession.setAttribute("Suggestionmodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("SuggestionDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);

		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
		commonFilter.setAwise(JHId);
		List<String[]>SuggestionDelList;
		try {
			SuggestionDelList=applicationMaintainanceService.getSuggestionData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject SuggestionData=UIUtils.convertToJqGridTableObject(SuggestionDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(SuggestionData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("SuggestionDelete_Delete.appm")){
		DeleteSuggestion(request,response);
	}
		
	else if(action.equals("KaizenDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/KaizenDelete.jsp");
	    rd.forward(request, response);
	}
	else if (action.equals("KaizenDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "KaizenDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Kaizenmodel");
		httpSession.setAttribute("Kaizenmodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("KaizenDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
        String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);

		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
		commonFilter.setAwise(JHId);
		List<String[]>KaizenDelList;
		try {
			KaizenDelList=applicationMaintainanceService.getKaizenDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject KaizenData=UIUtils.convertToJqGridTableObject(KaizenDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(KaizenData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	else if(action.equals("KaizenDelete_Delete.appm")){
	
		KaizenDelete(request,response);
	}
	else if(action.equals("WhyWhyDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/WhyWhyDelete.jsp");
	    rd.forward(request, response);
	}		
	
	else if (action.equals("WhyWhyDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "WhyWhyDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("WhyWhymodel");
		httpSession.setAttribute("WhyWhymodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("WhyWhyDelete_getData.appm")) {
		
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
	    String Dmt=request.getParameter("DMT");
	    String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		List<String[]>WhyWhyDelList;
		try {
			WhyWhyDelList=applicationMaintainanceService.getWhyWhyDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject WhyWhyData=UIUtils.convertToJqGridTableObject(WhyWhyDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(WhyWhyData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("WhyWhyDelete_Delete.appm")){
		WhyWhyDelete(request,response);
	}
	else if(action.equals("LossDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/LossDelete.jsp");
	    rd.forward(request, response);
	}		
	
	else if (action.equals("LossDelete_getCol.appm")) {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "LossDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("Lossmodel");
		httpSession.setAttribute("Lossmodel", colmodel);
		out.println(colmodel);
		out.close();
	}

	else if (action.equals("LossDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
			commonFilter.setFromDate(fromDate);
			commonFilter.setToDate(toDate);
			commonFilter.setFlid(DMTId);
	        commonFilter.setAwise(JHId);
		
		List<String[]>WhyWhyDelList;
		try {
			WhyWhyDelList=applicationMaintainanceService.getLossDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject WhyWhyData=UIUtils.convertToJqGridTableObject(WhyWhyDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(WhyWhyData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("LossDelete_Delete.appm")){
		LossDelete(request,response);
	}
		
	 
	else if (action.equals("NearMissDelete_input.appm")) {
			RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/NearMissDelete.jsp");
		    rd.forward(request, response); 
			}
	else if (action.equals("NearMissDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "NearMissDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("NearMissDeletemodel");
		httpSession.setAttribute("NearMissDeletemodel", colmodel);
		out.println(colmodel);
		out.close();
	}
	else if (action.equals("NearMissDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String ActionPlanType=request.getParameter("Type");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		commonFilter.setType(ActionPlanType);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		List<String[]>NearMissDelList;
		try {
			NearMissDelList=applicationMaintainanceService.getNearMissDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject NearMissDelete=UIUtils.convertToJqGridTableObject(NearMissDelList,request, 0, 1,gridParams.getTotalRecordCnt());
			out.println(NearMissDelete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else if(action.equals("NearMissDelete_Delete.appm")){
		NearMissDelete(request,response);
	}
	else if(action.equals("ActionPlanType_Combo.appm")){
		// CommonMessage.debugMsg("In type get Col"+action);
		 response.setContentType("text/html;charset=UTF-8");
		 response.setContentType("json");
		 PrintWriter out = response.getWriter(); 
		 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","ActionPlanType"));
	   }
	else if (action.equals("ActionPlanDelete_input.appm")) {
			RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/ActionPlanDelete.jsp");
		    rd.forward(request, response); 
			}
	else if (action.equals("ActionPlanDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "ActionPlanDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("ActionPlanDeletemodel");
		httpSession.setAttribute("ActionPlanDeletemodel", colmodel);
		out.println(colmodel);
		out.close();
	}
	else if (action.equals("ActionPlanDelete_getData.appm")){
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams",gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String ActionPlanType=request.getParameter("Type");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		CommonMessage.debugMsg("JH "+JHId);
		CommonMessage.debugMsg("DMT "+DMTId);
		commonFilter.setType(ActionPlanType);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		List<String[]>ActionPlanDelList;
		try {
			ActionPlanDelList=applicationMaintainanceService.getActionPlanDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject ActionPlanDelete=UIUtils.convertToJqGridTableObject(ActionPlanDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(ActionPlanDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

else if(action.equals("ApplicationMainteanceView_input.appm")){
		UIUtils.forwardRequest(request, response,"pages/AppMaintain/ApplicationMainteanceView.jsp");
	}
	
	else if (action.equals("ApplicationMainteanceView_getCol.appm")){
		PrintWriter out = response.getWriter();
        GridParams gridParams=new GridParams();
		FilterValues.populateGridParams(request,gridParams);				  
		out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","ApplicatoinMaintGrid"));
	}
		
	else if (action.equals("ApplicationMainteanceView_getData.appm")){
		PrintWriter out = response.getWriter();
		String Type=request.getParameter("Type");
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter.setTaskid(Type);
		List<String[]>AppGrdList;
		try {
			AppGrdList=applicationMaintainanceService.getAppMaintGrid(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AppGridView=UIUtils.convertToJqGridTableObject(AppGrdList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AppGridView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	else if(action.equals("Combo_AdminMenu.appm")){
		 try{
			 ComboFilter comboFilter=new ComboFilter();
    		 comboFilter=UIUtils.fillComboFilter(request); 
    		 List<ComboBox> AdminMenu=applicationMaintainanceService.getAdminMenu(comboFilter);
    		 UIUtils.writeComboBox(response,AdminMenu, comboFilter);
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 }
	}
	
	else if(action.equals("EmployeeTrade_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/EmployeeTrade.jsp");
	    rd.forward(request, response);
	}
	else if(action.equals("AreaTransfer_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/AreaTransfer.jsp");
	    rd.forward(request, response);
	}
		
	else if(action.equals("AreaTransfer_getCol.appm")){
		PrintWriter out = response.getWriter();
        GridParams gridParams=new GridParams();
		FilterValues.populateGridParams(request,gridParams);				  
		out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","EmployeeRoleMap"));
		
	}
	else if(action.equals("AreaTransfer_getData.appm")){		
		PrintWriter out = response.getWriter();
		String EmpId=request.getParameter("EmpId");
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams==null)
			gridParams=new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter.setEmpch(EmpId);
		List<String[]>AppGrdList;
		try {
			AppGrdList=applicationMaintainanceService.getEmployeeRole(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AppGridView=UIUtils.convertToJqGridTableObject(AppGrdList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AppGridView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	else if(action.equals("RoleList_getCol.appm")){
		
		PrintWriter out = response.getWriter();
        GridParams gridParams=new GridParams();
		FilterValues.populateGridParams(request,gridParams);				  
		out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain","RoleList"));
		
	}
	else if(action.equals("RoleList_getData.appm")){		
		PrintWriter out = response.getWriter();
		String flid = request.getParameter("flid");
	//	String roleid = request.getParameter("roleid");
	//	String frlkeyid = request.getParameter("frlkeyid");
		String level = request.getParameter("level");
		//CommonMessage.debugMsg("level"+level);
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams==null)
			gridParams=new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		commonFilter.setFlid(flid);
		commonFilter.setKey(level);
	//	commonFilter.setKK(frlkeyid);
		//commonFilter.setType(roleid);
		List<String[]>AppGrdList;
		try {
			AppGrdList=applicationMaintainanceService.getRoleList(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AppGridView=UIUtils.convertToJqGridTableObject(AppGrdList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AppGridView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	else if(action.equals("getEmprolelevel.appm")){
		
		getEmpRoleLevel(request,response);
	}
		
	else if(action.equals("ActionPlanDelete_Delete.appm")){
		ActionPlanDelete(request,response); 
	}
		
	else if(action.equals("AbnormalityDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/AbnormalityDelete.jsp");
	    rd.forward(request, response); 
	}
	
	else if(action.equals("AbnormalityDelete_getCol.appm")){
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String TagClassId=request.getParameter("TagClassId");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setAbnCause(TagClassId);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "AbnDelete");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("AbnDeletemodel");
		httpSession.setAttribute("AbnDeletemodel", colmodel);
		out.println(colmodel);
		out.close();
	}
else if (action.equals("AbnormalityDelete_getData.appm")){
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams",gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String TagClassId=request.getParameter("TagClassId");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");

		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setAbnCause(TagClassId);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);

		List<String[]>AbnClosureList;
		try {
			AbnClosureList=applicationMaintainanceService.getAbnDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AbnClosure=UIUtils.convertToJqGridTableObject(AbnClosureList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AbnClosure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	else if(action.equals("AbnormalityClosure_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/AbnormalityClosure.jsp");
	    rd.forward(request, response); 	
	} 
	else if(action.equals("AbnDelete_Delete.appm")){
		abnormalityDelete(request,response);
	}
	else if (action.equals("AbnormalityClosure_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String TagClassId=request.getParameter("TagClassId");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setAbnCause(TagClassId);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "AbnClosure");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("AbnClosuremodel");
		httpSession.setAttribute("AbnClosuremodel", colmodel);
		out.println(colmodel);
		out.close();
	}
		
	

	else if (action.equals("AbnormalityClosure_getData.appm")){
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams",gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String TagClassId=request.getParameter("TagClassId");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");

		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setAbnCause(TagClassId);
		commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);

		List<String[]>AbnClosureList;
		try {
			AbnClosureList=applicationMaintainanceService.getAbnClosureData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AbnClosure=UIUtils.convertToJqGridTableObject(AbnClosureList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AbnClosure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	else if(action.equals("AbnClosure_Update.appm")){
		
		AbnClosure(request,response);
	}		
		
		
	else if(action.equals("ActionPlanClosure_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/ActionPlanClosure.jsp");
	    rd.forward(request, response); 	
	}

	else if (action.equals("ActionPlanClosure_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Type=request.getParameter("Type");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
	    commonFilter.setActionKeyId(Type);
		commonFilter.setFlid(Dmt);
        commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "ActionPlanClosure");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("ActionPlanClosuremodel");
		httpSession.setAttribute("ActionPlanClosuremodel", colmodel);
		out.println(colmodel);
		out.close();
	}
	else if (action.equals("ActionPlanClosure_getData.appm")){
		
		PrintWriter out=response.getWriter();
		GridParams gridParams=(GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams",gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Type=request.getParameter("Type");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
	    commonFilter.setActionKeyId(Type);

        String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
        String JHId=applicationMaintainanceService.getJHOriginalId(Jh);
    	commonFilter.setFlid(DMTId);
        commonFilter.setAwise(JHId);
		List<String[]>AbnClosureList;
		try {
			AbnClosureList=applicationMaintainanceService.getActionPlanClosureData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AbnClosure=UIUtils.convertToJqGridTableObject(AbnClosureList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AbnClosure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	else if(action.equals("ActionPlanClosure_Update.appm")){
		
		ActionPlanClosure(request,response);
	}
	
	else if(action.equals("FIProjectDateChange_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/FIProjectDateChange.jsp");
	    rd.forward(request, response); 	
	}

	else if (action.equals("FIProjectDateChange_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String TagClassId=request.getParameter("TagClassId");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "FIDateChange");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("FIProjectmodel");
		httpSession.setAttribute("FIProjectmodel", colmodel);
		out.println(colmodel);
		out.close();
	}
	else if (action.equals("FIProjectDateChange_getData.appm")){
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams",gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request,gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
		List<String[]>FIProjectList;
		try {
			FIProjectList=applicationMaintainanceService.getFIProjectData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject AbnClosure=UIUtils.convertToJqGridTableObject(FIProjectList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(AbnClosure);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	else if(action.equals("FIProjectDateChange_Update.appm")){
		UpdateFIProjectDate(request,response);
	}		
		
	
	else if(action.equals("kaizenno_combo.appm")){
		 try{
			 ComboFilter comboFilter=new ComboFilter();
			 comboFilter=UIUtils.fillComboFilter(request); 
			 List<ComboBox> KaizenNoList=applicationMaintainanceService.getKaizenNoCombo(comboFilter);
			 UIUtils.writeComboBox(response,KaizenNoList, comboFilter);
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
		
	else if(action.equals("KaizenApprovalDelete_input.appm")){
		RequestDispatcher rd=request.getRequestDispatcher("pages/AppMaintain/KaizenApprovalDelete.jsp");
	    rd.forward(request, response);
	}
		
	else if (action.equals("KaizenApprovalDelete_getCol.appm")) {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		CommonFilter commonFilter=new CommonFilter();
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
		String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(Dmt);
		commonFilter.setAwise(Jh);
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationMaintain", "KaizenApprovalDeleteGrd");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		httpSession.removeAttribute("KaizenApprovalmodel");
		httpSession.setAttribute("KaizenApprovalmodel", colmodel);
		out.println(colmodel);
		out.close();
	}
		
	else if (action.equals("KaizenApprovalDelete_getData.appm")) {
		
		PrintWriter out = response.getWriter();
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		String fromDate=request.getParameter("fromDate");
		String toDate=request.getParameter("toDate");
	    String Dmt=request.getParameter("DMT");
		String Jh=request.getParameter("JH");
		String DMTId=applicationMaintainanceService.getDmtOriginalId(Dmt);
		String JHId=applicationMaintainanceService.getJHOriginalId(Jh);

		commonFilter.setFromDate(fromDate);
		commonFilter.setToDate(toDate);
		commonFilter.setFlid(DMTId);
		commonFilter.setAwise(JHId);
		List<String[]>KaizenApprovalDelList;
		try {
			KaizenApprovalDelList=applicationMaintainanceService.getKaizenApprovalDeleteData(gridParams,commonFilter);
			httpSession.removeAttribute("gridParams");			
			JSONObject KaizenApprovalData=UIUtils.convertToJqGridTableObject(KaizenApprovalDelList,request, 1, 1,gridParams.getTotalRecordCnt());
			out.println(KaizenApprovalData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		
	else if (action.equals("KaizenApprovalWorkFlowPopup_input.appm")) {

	    try {
	        String kznKeyid = request.getParameter("kznKeyid");

	        CommonMessage.debugMsg("KaizenApprovalWorkFlowPopup kznKeyid: " + kznKeyid);

	        if (kznKeyid == null || kznKeyid.trim().isEmpty()) {
	            throw new Exception("Kaizen key ID is empty");
	        }

	        String[] workflowInfo =applicationMaintainanceService.getKaizenBenefitTypeAndFlid(kznKeyid);

	        String benefitType = "";
	        String flId = "";

	        if (workflowInfo != null) {

	            if (workflowInfo.length > 0 &&
	                workflowInfo[0] != null) {

	                benefitType = workflowInfo[0].trim();
	            }

	            if (workflowInfo.length > 1 &&
	                workflowInfo[1] != null) {

	                flId = workflowInfo[1].trim();
	            }
	        }

	        String transCode = "";

	        if ("GE5".equalsIgnoreCase(benefitType)) {
	            transCode = "BTSG5L";

	        } else if ("GE1C".equalsIgnoreCase(benefitType)) {
	            transCode = "BTSG1C";

	        } else if ("NS".equalsIgnoreCase(benefitType)) {
	            transCode = "BTSNOSAVIN";

	        } else if ("S".equalsIgnoreCase(benefitType)) {
	            transCode = "BTSSAFETY";

	        } else if ("LE5".equalsIgnoreCase(benefitType)) {
	            transCode = "BTSL5L";
	        }

	        if (transCode.isEmpty()) {
	            throw new Exception("Workflow transaction code not found for benefit type: "+ benefitType);
	        }

	        /*
	         * Get logged-in employee ID.
	         */
	        AdmTlUsermst loginUser = UIUtils.getLoginUser(request);

	        String empId = "";

	        if (loginUser != null &&
	            loginUser.getUsrm_ccno() != null) {

	            empId = loginUser.getUsrm_ccno().trim();
	        }

	        /*
	         * Get role IDs for the logged-in employee.
	         */
	        String refRoleId = "";

	        if (!empId.isEmpty()) {
	            String roleIds =applicationMaintainanceService.getEmpRoleIds(empId);

	            if (roleIds != null) {
	                refRoleId = roleIds.trim();
	            }
	        
	        }
	        //String refRoleId =applicationMaintainanceService.getWorkflowRefRoleId(empId,flId,transCode);

	        	//if (refRoleId == null) {
	        	    //refRoleId = "";
	        	//} else {
	        	    //refRoleId = refRoleId.trim();
	        	//}

	        	CommonMessage.debugMsg("Resolved workflow refRoleId: " + refRoleId);
	        
	        CommonMessage.debugMsg("Workflow popup employee: " + empId);

	        CommonMessage.debugMsg("Workflow popup single role: " + refRoleId);

	        request.setAttribute("kznKeyid", kznKeyid);
	        request.setAttribute("refId", kznKeyid);
	        request.setAttribute("refType", "KZNBTS");
	        request.setAttribute("transCode", transCode);
	        request.setAttribute("flId", flId);
	        request.setAttribute("refRoleId", refRoleId);
	        request.setAttribute("empId", empId);
	        request.setAttribute("enable", "N");

	        CommonMessage.debugMsg(
	            "Popup workflow parameters => "
	            + "empId=" + empId
	            + ", refId=" + kznKeyid
	            + ", refType=KZNBTS"
	            + ", transCode=" + transCode
	            + ", flId=" + flId
	            + ", refRoleId=" + refRoleId
	            + ", enable=N"
	        );

	        RequestDispatcher rd =
	            request.getRequestDispatcher("pages/AppMaintain/"+ "KaizenApprovalWorkFlowPopup.jsp");

	        rd.forward(request, response);

	    } catch (Exception e) {

	        e.printStackTrace();

	        request.setAttribute(
	            "popupError",
	            "Unable to load workflow: "
	            + e.getMessage()
	        );

	        RequestDispatcher rd =
	            request.getRequestDispatcher("pages/AppMaintain/"+ "KaizenApprovalWorkFlowPopup.jsp");

	        rd.forward(request, response);
	    }
	}
		
		// end
		
		// added by priyanka - workflow grid colModel for kaizen approval delete popup
			else if(action.equals("KaizenApprovalWorkFlowInfo_getCol.appm")){
				//String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "workflow_app_grid");
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.WorkFlow", "KaizenApprovalWorkFlow_grid");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
		
			else if(action.equals("KaizenApprovalWorkFlowInfo_getData.appm")){
				
				String transCode   = request.getParameter("transCode");
				String empId   = request.getParameter("empId");
				CommonMessage.debugMsg("The EmpId::::"+empId);
				if(! UIUtils.isValidKeyId(empId) ){
					empId = UIUtils.getLoginUser(request).getUsrm_ccno();
				}
				String refId   =  request.getParameter("refId");
				CommonMessage.debugMsg("The RefID:::"+refId);
				String flId   =  request.getParameter("flId");
				CommonMessage.debugMsg("The flId:::"+flId);
				String refType =  request.getParameter("refType");
				CommonMessage.debugMsg("The RefType:::"+refType);
				String roleId =  request.getParameter("refRoleId");
				CommonMessage.debugMsg("The roleId:::"+roleId);
				String enable =  request.getParameter("enable");
				
				CommonMessage.debugMsg("The enable -----:::"+enable);

				GenTlWorkflowInfo genTlWorkflowInfo = new GenTlWorkflowInfo();
				genTlWorkflowInfo.setWrinEmployeeId(empId);
				genTlWorkflowInfo.setWrinRefId(refId);
				genTlWorkflowInfo.setWrinRefType(refType);
				if( UIUtils.isValidKeyId(enable) )
					genTlWorkflowInfo.setEnable(enable.trim().charAt(0));
				
				if(!UIUtils.isValidKeyId(roleId)){
					roleId="role_keyid";
				}
				CommonMessage.debugMsg(" refroleId  "+ roleId);
				try {
					List<String[]> dataList = applicationMaintainanceService.getWorkFlowTransData(genTlWorkflowInfo, transCode,flId,roleId);
					response.getWriter().print(UIUtils.convertToJqGridTableObject(dataList, request, 0, 0, 0, dataList.size()));
					response.getWriter().close();
					dataList =null;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				genTlWorkflowInfo = null;
			}
				
			else if (action.equals("KaizenApprovalWorkFlow_Delete.appm")) {
			    revokeKaizenApprovalWorkflow(request, response);
			}
	
   
	}
	
	
	private void revokeKaizenApprovalWorkflow(
	        HttpServletRequest request,
	        HttpServletResponse response) throws IOException {

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession(false);
	    AdmTlUsermst user = UIUtils.getLoginUser(request);

	    try {
	        if (session == null || user == null) {
	            throw new Exception("User session is invalid");
	        }

	        String refId = request.getParameter("refId");
	        String wrinKeyids = request.getParameter("wrinKeyids");

	        CommonMessage.debugMsg(
	            "Workflow revoke request => refId=" + refId +
	            ", wrinKeyids=" + wrinKeyids
	        );

	        if (refId == null || refId.trim().isEmpty()) {
	            throw new Exception("Kaizen reference ID is empty");
	        }

	        if (wrinKeyids == null || wrinKeyids.trim().isEmpty()) {
	            throw new Exception("Workflow key IDs are empty");
	        }

	        List<String> wrinKeyIdList = new ArrayList<String>();

	        String[] keyIds = wrinKeyids.split(",");

	        for (String keyId : keyIds) {
	            if (keyId != null && !keyId.trim().isEmpty()) {
	                wrinKeyIdList.add(keyId.trim());
	            }
	        }

	        if (wrinKeyIdList.isEmpty()) {
	            throw new Exception("No valid workflow rows selected");
	        }

	        CommonMessage.debugMsg(
	            "Revoke Kaizen workflow RefId: " + refId +
	            ", Workflow IDs: " + wrinKeyIdList
	        );

	        applicationMaintainanceService
	            .revokeKaizenApprovalWorkflow(
	                refId.trim(),
	                wrinKeyIdList
	            );

	        JSONObject successData = new JSONObject();
	        successData.put(
	            "msg",
	            "Kaizen workflow approval revoked successfully"
	        );

	        JSONObject returnData = new JSONObject();
	        returnData.put("successData", successData);
	        returnData.put("formClear", false);

	        out.print(returnData.toString());

	    } catch (Exception e) {
	        e.printStackTrace();

	        JSONObject err = new JSONObject();
	        err.put(
	            "tpmException",
	            "Unable to revoke Kaizen workflow: " +
	            e.getMessage()
	        );

	        out.print(err.toString());
	    } finally {
	        out.flush();
	        out.close();
	    }
	}
	
	
	
	private void updateInactiveUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
		/*String empId=request.getParameter("EmployeeId");
		String lwdDate=request.getParameter("EmployeeId");
		String remark=request.getParameter("EmployeeId");*/
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String EmpKeyid = request.getParameter("EmployeeId");
				String ValidTill = request.getParameter("LWDate");
				String remarks=request.getParameter("Remarks");
				//String EmpType=request.getParameter("EmpType");
			
				if (UIUtils.isValidKeyId(EmpKeyid)) {
					    applicationMaintainanceService.UserInActive(EmpKeyid,ValidTill,remarks);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		}
				catch(Exception e){
					e.printStackTrace();
				}
		
	}
	private void abnormalityDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String abnKeyid = request.getParameter("AbnmKeyid");
				if (UIUtils.isValidKeyId(abnKeyid)) {
					    applicationMaintainanceService.deleteAbnormality(abnKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
	private void LocationTransfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String EmpKeyid = request.getParameter("EmployeeId");
				
				String EmpLocation=request.getParameter("TargetLocation");
			
				if (UIUtils.isValidKeyId(EmpKeyid)&&UIUtils.isValidKeyId(EmpLocation)) {
					    applicationMaintainanceService.LocationTransfer(EmpKeyid,EmpLocation);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
		
	}

    private void updateEmployeeActive(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String EmpKeyid = request.getParameter("EmpKeyid");
				String ValidTill = request.getParameter("ValidTill");
				String remarks=request.getParameter("remarks");
				String EmpType=request.getParameter("EmpType");
			
				if (UIUtils.isValidKeyId(EmpKeyid)) {
					    applicationMaintainanceService.UpdateActive(EmpKeyid,ValidTill,remarks,EmpType);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
 

    private void SusaDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String SusaKeyid = request.getParameter("SusaKeyid");
				if (UIUtils.isValidKeyId(SusaKeyid)) {
					    applicationMaintainanceService.DeleteSusa(SusaKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
    
  private void UpdateKaizenDate(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
	if (httpSession != null && user != null) {
		String KaizenKeyid = request.getParameter("KaizenKeyid");
		String KaizenDate = request.getParameter("KaizenDate");
	
		if (UIUtils.isValidKeyId(KaizenKeyid)) {
			    applicationMaintainanceService.UpdateKaizenDate(KaizenKeyid,KaizenDate);
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				out.print(returnData.toString());
		}
	}
} 
catch(Exception e) {
	e.printStackTrace();
	JSONObject err = new JSONObject();
	err.put("tpmException", "Data Not Saved");
	out.print(err.toString());
}
}
  
  private void getEmpRoleLevel(HttpServletRequest request,HttpServletResponse response) throws IOException,ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
			if(httpSession != null && user != null) {
				String flid = request.getParameter("flid");
				//CommonMessage.debugMsg("flid" + flid);
				List<String[]> data = applicationMaintainanceService.getlevelrole(flid);
				if (data != null){
					JSONObject successData = new JSONObject();
					successData.put("level", data.get(0)[1]);
					//CommonMessage.debugMsg(data.get(0)[1]);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					out.print(returnData.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());
		}
	}
  
  private void TrainingDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String TrainingKeyid = request.getParameter("TrainingKeyid");
				if (UIUtils.isValidKeyId(TrainingKeyid)) {
					    applicationMaintainanceService.DeleteTraining(TrainingKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
  private void DeleteSuggestion(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String SuggKeyid = request.getParameter("SuggKeyid");
				if (UIUtils.isValidKeyId(SuggKeyid)) {
					    applicationMaintainanceService.DeleteSuggestion(SuggKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
  

  private void KaizenDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String KaizenKeyid = request.getParameter("KaizenKeyid");
				if (UIUtils.isValidKeyId(KaizenKeyid)) {
					    applicationMaintainanceService.DeleteKaizen(KaizenKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
  
  private void WhyWhyDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
	if (httpSession != null && user != null) {
		String WhyWhyKeyid = request.getParameter("WhyWhyKeyid");
		if (UIUtils.isValidKeyId(WhyWhyKeyid)) {
			    applicationMaintainanceService.DeleteWhyWhy(WhyWhyKeyid);
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				out.print(returnData.toString());
		}
	}
} 
catch(Exception e) {
	e.printStackTrace();
	JSONObject err = new JSONObject();
	err.put("tpmException", "Data Not Saved");
	out.print(err.toString());
}
}
  
  private void LossDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
	if (httpSession != null && user != null) {
		String LossKeyid = request.getParameter("LossKeyid");
		if (UIUtils.isValidKeyId(LossKeyid)) {
			    applicationMaintainanceService.DeleteLoss(LossKeyid);
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				out.print(returnData.toString());
		}
	}
} 
catch(Exception e) {
	e.printStackTrace();
	JSONObject err = new JSONObject();
	err.put("tpmException", "Data Not Saved");
	out.print(err.toString());
}
}
  

  private void NearMissDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String NearMissKeyid = request.getParameter("NearMissKeyid");
				if (UIUtils.isValidKeyId(NearMissKeyid)) {
					    applicationMaintainanceService.DeleteNearMiss(NearMissKeyid);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
    
  private void ActionPlanDelete(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
	if (httpSession != null && user != null) {
		String ActionPlanKeyid = request.getParameter("ActionPlanKeyid");
		if (UIUtils.isValidKeyId(ActionPlanKeyid)) {
			    applicationMaintainanceService.DeleteActionPlan(ActionPlanKeyid);
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-delete"));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				out.print(returnData.toString());
		}
	}
} 
catch(Exception e) {
	e.printStackTrace();
	JSONObject err = new JSONObject();
	err.put("tpmException", "Data Not Saved");
	out.print(err.toString());
}
}
  
//  private void AbnClosure(HttpServletRequest request,HttpServletResponse response) throws IOException,
//			ValidationExceptions, BusinessApplicationExceptions {
//		HttpSession httpSession = request.getSession(false);
//		PrintWriter out = response.getWriter();
//		AdmTlUsermst user = UIUtils.getLoginUser(request);
//		try {
//			if (httpSession != null && user != null) {
//				String AbnmKeyid = request.getParameter("AbnmKeyid");
//				String Status = request.getParameter("Status");
//				String CounterMeasure=request.getParameter("CounterMeasure");
//				String CompletedDate=request.getParameter("CompletedDate");
//				String CompletedBy=request.getParameter("CompletedBy");
//			
//				if (UIUtils.isValidKeyId(AbnmKeyid)) {
//					    applicationMaintainanceService.AbnClosure(AbnmKeyid,Status,CounterMeasure,CompletedDate,CompletedBy);
//						JSONObject successData = new JSONObject();
//						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
//						JSONObject returnData = new JSONObject();
//						returnData.put("successData", successData);
//						returnData.put("formClear", true);
//						out.print(returnData.toString());
//				}
//			}
//		} 
//
//		catch(Exception e) {
//			e.printStackTrace();
//			JSONObject err = new JSONObject();
//			err.put("tpmException", "Data Not Saved");
//			out.print(err.toString());
//		}
//	}
//  
  private void AbnClosure(HttpServletRequest request, HttpServletResponse response) throws IOException,
  ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
  if (httpSession != null && user != null) {

      String AbnClosureDetails = request.getParameter("AbnClosureDetails");
      CommonMessage.debugMsg("AbnClosureDetails::::" + AbnClosureDetails);

      if (UIUtils.isValidKeyId(AbnClosureDetails)) {

          List<AbnTlAbnormality> abnClosureList = AbnTlAbnormality.fromJsonList(AbnClosureDetails);

          applicationMaintainanceService.AbnClosure(abnClosureList);

          JSONObject successData = new JSONObject();
          successData.put("msg", UIUtils.getPropertyValue(
                  "com.akranta.tpm.resources.CommonMessages", "success-update"));
          JSONObject returnData = new JSONObject();
          returnData.put("successData", successData);
          returnData.put("formClear", true);
          out.print(returnData.toString());
      }
  }
} catch (Exception e) {
  e.printStackTrace();
  JSONObject err = new JSONObject();
  err.put("tpmException", "Data Not Saved");
  out.print(err.toString());
}
}	


  
  private void ActionPlanClosure(HttpServletRequest request,HttpServletResponse response) throws IOException,
			ValidationExceptions, BusinessApplicationExceptions {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String ActionPlanId=request.getParameter("ActionPlanId");
				String DetailId=request.getParameter("DetailId");
				String Status = request.getParameter("Status");
				String CompletedOn=request.getParameter("CompletedOn");
				String CompletedBy=request.getParameter("CompletedBy");
				String CounterMeasure=request.getParameter("CounterMeasure");
			
				if (UIUtils.isValidKeyId(ActionPlanId)) {
					    applicationMaintainanceService.ActionPlanClosure(ActionPlanId,DetailId,Status,CompletedOn,CompletedBy,CounterMeasure);
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("formClear", true);
						out.print(returnData.toString());
				}
			}
		} 

		catch(Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
  

  private void UpdateFIProjectDate(HttpServletRequest request,HttpServletResponse response) throws IOException,
	ValidationExceptions, BusinessApplicationExceptions {
HttpSession httpSession = request.getSession(false);
PrintWriter out = response.getWriter();
AdmTlUsermst user = UIUtils.getLoginUser(request);
try {
	if (httpSession != null && user != null) {
		String FIProjectId = request.getParameter("FIProjectId");
		String EndDate = request.getParameter("EndDate");
	
		if (UIUtils.isValidKeyId(FIProjectId)) {
			    applicationMaintainanceService.UpdateFIProjectDate(FIProjectId,EndDate);
				JSONObject successData = new JSONObject();
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-update"));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				out.print(returnData.toString());
		}
	}
} 
catch(Exception e) {
	e.printStackTrace();
	JSONObject err = new JSONObject();
	err.put("tpmException", "Data Not Saved");
	out.print(err.toString());
}
}
  

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');

		//String loginFlid = CommonFunctions.getLoginFlid(request);
	//	if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
		//	commonFilter.setFlid(loginFlid);

		return commonFilter;
	}
	
}
			
		



