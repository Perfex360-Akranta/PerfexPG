package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlConditionalappraisalBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlConditionalappraisal;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalEntry;
import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;
import com.akranta.tpm.service.ConditionalAppraisalService;
import com.akranta.tpm.service.impl.ConditionalAppraisalServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import com.akranta.tpm.service.api.ConditionalAppraisalServiceApi;

/**
 * Servlet implementation class ConditionalAppraisalServlet
 */

public class ConditionalAppraisalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ConditionalAppraisalService conditionalAppraisalService;
	ConditionalAppraisalServiceApi conditionalappraisalserviceapi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConditionalAppraisalServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		conditionalAppraisalService = (ConditionalAppraisalServiceImpl)UIUtils.getServiceObject(request,"ConditionalAppraisalServiceImpl");
		HttpSession httpSession = request.getSession(false);
		conditionalAppraisalService.ConditionalAppraisalServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		String action = UIUtils.getActionPart(request);
		if(action.equals("ConditionalAppraisal_view.condapp")) {
			request.setAttribute("mode", "Mst");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalGrid.jsp");
			rd.forward(request, response);
		}else if(action.equals("ConditionalAppraisalEntry_view.condapp")) {
			request.setAttribute("mode", "Entry");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalGrid.jsp");
			rd.forward(request, response);
			
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalEntry.jsp");
			//rd.forward(request, response);
		}
		else if (action.equals("ConditionalAppraisal_getCol.condapp")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
			List<String[]> conditionalGrid = null;
			try {
				conditionalGrid = conditionalAppraisalService.getConditionalAppMainGrid(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			
			String [] colHeader = conditionalGrid.get(1);			
			String [] colHeaderCond = conditionalGrid.get(0);
			gridColModel.setHeaderNum(1);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "70%%");
			httpSession.removeAttribute("ConditionalAppraisal");
			httpSession.setAttribute("ConditionalAppraisal", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("ConditionalAppraisal_getData.condapp")) {
			try {
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",false);
				List<String[]> conditionalGrid = conditionalAppraisalService.getConditionalAppMainGrid(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 2, 0);
				out.println(conditionaljson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (action.equals("ConditionalAppraisalReport_input.condapp")) {
			String isFormat=request.getParameter("isFormat");
			
			CommonMessage.debugMsg("ConditionalAppraisalReport_input.condapp isFormat: "+isFormat);
			request.setAttribute("isFormat",isFormat);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalReport.jsp");
			rd.forward(request, response);
		}
		else if (action.equals("ConditionalAppraisalReport_getCol.condapp")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
			String isFormat=request.getParameter("isFormat");
			//commonFilter.setAbnViewType ("REPORT");
			List<String[]> conditionalGrid = null;
			try {
				conditionalGrid = conditionalAppraisalService.getCondApReport(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			
			String [] colHeader = conditionalGrid.get(1);			
			String [] colHeaderCond = conditionalGrid.get(0);
			gridColModel.setHeaderNum(1);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "70%%");
			httpSession.removeAttribute("ConditionalAppraisal");
			httpSession.setAttribute("ConditionalAppraisal", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("ConditionalAppraisalReport_getData.condapp")) {
			try {
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",false);
				//commonFilter.setAbnViewType ("REPORT");
				List<String[]> conditionalGrid = conditionalAppraisalService.getCondApReport(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 2, 0);
				out.println(conditionaljson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("component_combo.condapp")){
			try{
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ConditionalAppraisalform","component"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(action.equals("refurbishment_combo.condapp")){
			try{
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ConditionalAppraisalform","refurbishment"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(action.equals("idealType_combo.condapp")){
			try{
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ConditionalAppraisalform","idealType"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(action.equals("actualOknotok_combo.condapp")){
			try{
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ConditionalAppraisalform","actualType"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(action.equals("status_combo.condapp")){
			try{
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.ConditionalAppraisalform","status"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if( action.equals("getModeConditionalAppraisal_view.condapp")){
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode","create");
			result.put("url","ConditionalAppraisalForm_input.condapp");
			result.put("formHeader","Conditional Appraisal Entry");
			out.println(result);
		}
		/*
		 * else if (action.equals("ConditionalAppraisalForm_input.condapp")) { String
		 * keyId = request.getParameter("keyId"); String
		 * flid=request.getParameter("flid"); CommonMessage.debugMsg("mflid"+flid);
		 * CommonMessage.debugMsg("mkeyId"+keyId);
		 * 
		 * String newFrm=request.getParameter("new"); String
		 * date=request.getParameter("date"); String mode=request.getParameter("mode");
		 * CommonMessage.debugMsg("mmode"+ mode);
		 * 
		 * if("Entry".equals(mode)) { PlmTlConditionalappraisalmstentry
		 * plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmstentry();
		 * plmTlConditionalappraisalmst.setCdamFlid(flid);
		 * plmTlConditionalappraisalmst.setCdamDate(date);
		 * request.setAttribute("cdamUpdtDate", date);
		 * CommonMessage.debugMsg("date===="+date);
		 * request.setAttribute("PlmTlConditionalappraisalmstentry",
		 * plmTlConditionalappraisalmst); RequestDispatcher rd =
		 * request.getRequestDispatcher("/pages/ConditionalAppraisalEntry.jsp");
		 * rd.forward(request, response);
		 * 
		 * } else { AdmTlUsermst user = UIUtils.getLoginUser(request);
		 * PlmTlConditionalappraisalmst plmTlConditionalappraisalmst = new
		 * PlmTlConditionalappraisalmst(); if (UIUtils.isValidKeyId(keyId)) {
		 * plmTlConditionalappraisalmst=
		 * conditionalAppraisalService.getAllFillControl(keyId);
		 * plmTlConditionalappraisalmst.setCdamCreatedon(UIUtils.getActualDateForm(
		 * plmTlConditionalappraisalmst.getCdamCreatedon()));
		 * if(plmTlConditionalappraisalmst.getCdamCreatedon().equals(Constants.
		 * futureNullDate)) plmTlConditionalappraisalmst.setCdamCreatedon("");
		 * plmTlConditionalappraisalmst.setCdamDate(UIUtils.getActualDateForm(
		 * plmTlConditionalappraisalmst.getCdamDate()));
		 * if(plmTlConditionalappraisalmst.getCdamDate().equals(Constants.futureNullDate
		 * )) plmTlConditionalappraisalmst.setCdamDate(""); }else{
		 * plmTlConditionalappraisalmst.setCdamFlid(flid);
		 * plmTlConditionalappraisalmst.setCdamDate(date); }
		 * request.setAttribute("plmTlConditionalappraisalmst",
		 * plmTlConditionalappraisalmst); request.setAttribute("newFrm", newFrm);
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/pages/ConditionalAppraisalNew.jsp");
		 * rd.forward(request, response); }
		 * 
		 * }
		 */
		else if (action.equals("ConditionalAppraisalForm_input.condapp")) {
		    try {
		        String keyId = request.getParameter("keyId");
		        String flid = request.getParameter("flid");
		        String newFrm = request.getParameter("new");
		        String date = request.getParameter("date");
		        String mode = request.getParameter("mode");
		        
		        CommonMessage.debugMsg("=== Conditional Appraisal Role Check ===");
		        CommonMessage.debugMsg("flid: " + flid);
		        CommonMessage.debugMsg("keyId: " + keyId);
		        CommonMessage.debugMsg("mode: " + mode);
		        
		        // Get login user details
		        AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
		        String empId = usersdetails.getUsrm_ccno();
		        String loginflid = CommonFunctions.getLoginFlid(request);
		        String loginLevel = CommonFunctions.getLoginLevel(request);
		        String loginElementid = (String)httpSession.getAttribute("loginElementid");
		        
		        CommonMessage.debugMsg("empId: " + empId);
		        CommonMessage.debugMsg("loginflid: " + loginflid);
		        CommonMessage.debugMsg("loginLevel: " + loginLevel);
		        CommonMessage.debugMsg("loginElementid: " + loginElementid);
		        
		        // Get user role details
		        List<String[]> getUserLoginDtl = this.conditionalAppraisalService.getUserRoleDetails(
		            loginflid, loginLevel, loginElementid, empId);
		        
		        CommonMessage.debugMsg("Query Result Size: " + (getUserLoginDtl != null ? getUserLoginDtl.size() : "NULL"));
		        
		        // Set role attributes - ALWAYS set these so JavaScript can check them
		        if(getUserLoginDtl != null && getUserLoginDtl.size() > 0) {
		            String elementid = ((String[])getUserLoginDtl.get(0))[0];
		            String fnln = ((String[])getUserLoginDtl.get(0))[1];
		            String level = ((String[])getUserLoginDtl.get(0))[2];
		            String rolename = ((String[])getUserLoginDtl.get(0))[3];
		            String rolekeyid = ((String[])getUserLoginDtl.get(0))[4];
		            
		            CommonMessage.debugMsg("Role Name: '" + rolename + "'");
		            CommonMessage.debugMsg("Role KeyId: '" + rolekeyid + "'");
		            CommonMessage.debugMsg("Expected: 'PM PILLAR MEMBER' (AROL0052)");
		            
		            request.setAttribute("rolekeyid", rolekeyid);
		            request.setAttribute("rolename", rolename);
		        } else {
		            CommonMessage.debugMsg("WARNING: No role data found!");
		            request.setAttribute("rolekeyid", "");
		            request.setAttribute("rolename", "");
		        }
		        
		        // Continue with normal form logic
		        if("Entry".equals(mode)) {
		            PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmstentry();
		            plmTlConditionalappraisalmst.setCdamFlid(flid);
		            plmTlConditionalappraisalmst.setCdamDate(date);
		            request.setAttribute("cdamUpdtDate", date);
		            CommonMessage.debugMsg("date====" + date);
		            request.setAttribute("PlmTlConditionalappraisalmstentry", plmTlConditionalappraisalmst);
		            
		            RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalEntry.jsp");
		            rd.forward(request, response);
		        } else {
		            PlmTlConditionalappraisalmst plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmst();
		            
		            if (UIUtils.isValidKeyId(keyId)) {
		                plmTlConditionalappraisalmst = conditionalAppraisalService.getAllFillControl(keyId);
		                
String date2 = plmTlConditionalappraisalmst.getCdamCreatedon();
				 		
		                plmTlConditionalappraisalmst.setCdamCreatedon(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date2));
		                
		                
		               // plmTlConditionalappraisalmst.setCdamCreatedon(UIUtils.getActualDateForm(plmTlConditionalappraisalmst.getCdamCreatedon()));
		                if(plmTlConditionalappraisalmst.getCdamCreatedon().equals(Constants.pgFutureNullDateTime))
		                    plmTlConditionalappraisalmst.setCdamCreatedon("");
		                    
		               // plmTlConditionalappraisalmst.setCdamDate(UIUtils.getActualDateForm(plmTlConditionalappraisalmst.getCdamDate()));
		                
		                String date1 = plmTlConditionalappraisalmst.getCdamDate();
				 		
		                plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
		                if(plmTlConditionalappraisalmst.getCdamDate().equals(Constants.pgFutureNullDateTime))
		                    plmTlConditionalappraisalmst.setCdamDate("");
		            } else {
		                plmTlConditionalappraisalmst.setCdamFlid(flid);
		                plmTlConditionalappraisalmst.setCdamDate(date);
		            }
		            
		            request.setAttribute("plmTlConditionalappraisalmst", plmTlConditionalappraisalmst);
		            request.setAttribute("newFrm", newFrm);
		            
		            RequestDispatcher rd = request.getRequestDispatcher("/pages/ConditionalAppraisalNew.jsp");
		            rd.forward(request, response);
		        }
		        
		        CommonMessage.debugMsg("=== Conditional Appraisal Role Check Complete ===");
		        
		    } catch (Exception e) {
		        CommonMessage.debugMsg("ERROR in ConditionalAppraisalForm_input: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
		else if (action.equals("ConditionalAppraisalEntryForm_getCol.condapp")) {
        	PrintWriter out = response.getWriter();
			CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
			List<String[]> conditionalGrid = null;
			try {
				conditionalGrid = conditionalAppraisalService.getConditionalAppraisalEntryGrid(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(false);
			
			String [] colHeader = conditionalGrid.get(1);			
			String [] colHeaderCond = conditionalGrid.get(0);
			gridColModel.setHeaderNum(1);
			gridColModel.setFormatter("btnActPlan");
			gridColModel.setFormattorFromCol("14");
			gridColModel.setFormattorToCol("14");
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "100%%");
			jsonObject.set("tableHeight", "35%%");
			httpSession.removeAttribute("ConditionalAppraisal");
			httpSession.setAttribute("ConditionalAppraisal", jsonObject);
			out.println(jsonObject);
       }else if (action.equals("ConditionalAppraisalEntryForm_getData.condapp")) {
    	   try {
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",false);
				String flid=request.getParameter("flid");
				String date=request.getParameter("date");
				String keyId=request.getParameter("keyId");
				
				
				PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmstentry();
				plmTlConditionalappraisalmst.setCdamKeyid(keyId);
				plmTlConditionalappraisalmst.setCdamDate(date);
				plmTlConditionalappraisalmst.setCdamFlid(flid);
				
				
String date1 = plmTlConditionalappraisalmst.getCdamDate();
CommonMessage.debugMsg(CommonFunctions.pg_getDateTimeFromDate(date1)+" Changed Date");		 		
plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.pg_getDateTimeFromDate(date1));
		 		
				//String keyId=request.getParameter("keyId");
				
				 String cdamkeyid = conditionalAppraisalService.checkdata(plmTlConditionalappraisalmst,"Y");
				 CommonMessage.debugMsg(" cdamkeyid :  "+cdamkeyid);
				commonFilter.setFlid(flid);
				commonFilter.setDteend(date);
				commonFilter.setKey(keyId);
				List<String[]> conditionalGrid ;
				if( Integer.parseInt(cdamkeyid) > 0 )
				{
					conditionalGrid = conditionalAppraisalService.getConditionalAppraisalEntryGrid(commonFilter);
				}
				else
				{
					//conditionalGrid = conditionalAppraisalService.getConditionalAppraisalGrid(commonFilter);
					conditionalGrid = conditionalAppraisalService.getConditionalAppraisalEntryGrid(commonFilter);
				}
				
				PrintWriter out = response.getWriter();
				JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 2, 0);
				out.println(conditionaljson);
			} catch (Exception e) {
				e.printStackTrace();
			}
       }
        else if (action.equals("ConditionalAppraisalForm_getCol.condapp")) {
        	PrintWriter out = response.getWriter();
			CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
			List<String[]> conditionalGrid = null;
			try {
				conditionalGrid = conditionalAppraisalService.getConditionalAppraisalGrid(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(false);
			
			String [] colHeader = conditionalGrid.get(1);			
			String [] colHeaderCond = conditionalGrid.get(0);
			gridColModel.setHeaderNum(1);
			gridColModel.setFormatter("btnActPlan");
			gridColModel.setFormattorFromCol("14");
			gridColModel.setFormattorToCol("14");
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			jsonObject.set("tableWidth", "100%%");
			jsonObject.set("tableHeight", "35%%");
			httpSession.removeAttribute("ConditionalAppraisal");
			httpSession.setAttribute("ConditionalAppraisal", jsonObject);
			out.println(jsonObject);
       }else if (action.equals("ConditionalAppraisalForm_getData.condapp")) {
    	   try {
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",false);
				String flid=request.getParameter("flid");
				String date=request.getParameter("date");
				String keyId=request.getParameter("keyId");
				
				//String keyId=request.getParameter("keyId");
				
				
				commonFilter.setFlid(flid);
				commonFilter.setDteend(date);
				commonFilter.setKey(keyId);
				List<String[]> conditionalGrid = conditionalAppraisalService.getConditionalAppraisalGrid(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject conditionaljson = UIUtils.convertToJqGridTableObject(conditionalGrid, request, 2, 0);
				out.println(conditionaljson);
			} catch (Exception e) {
				e.printStackTrace();
			}
       }else if (action.equals("ConditionalAppraisalForm_recall.condapp")) {
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			
			
			List<String []> condReclData  = conditionalAppraisalService.recallData( keyid);
			
			
			
			
			
			out.print( JSONArray.fromCollection(condReclData));				
		}
       else if (action.equals("ConditionalAppraisalEntryForm_recall.condapp")) {
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			List<String []> condReclData  = conditionalAppraisalService.recallentryData( keyid);
			out.print( JSONArray.fromCollection(condReclData));				
		}
       else if (action.equals("ConditionalAppraisalEntry_save.condapp")) {
			 saveConditionlApprisalEntry(request,response);
			}
       else if (action.equals("ConditionalAppraisalEntryForm_save.condapp")) {
    	   	saveConditionlApprisalEntry(request,response);
			}		
		 else if (action.equals("ConditionalAppraisalForm_save.condapp")) {
			 saveConditionlApprisal(request,response);
			}
		 else if (action.equals("ConditionalAppraisalDetail_save.condapp")) {
			 saveConditionlApprisal(request,response);
			}
		 else if( action.equals("ConditionalAppraisalForm_delete.condapp"))
			{
			 deleteConditionlApprisal(request,response);
			}
		 else if( action.equals("ConditionalAppraisalDetail_delete.condapp"))
			{
			 deleteConditionlApprisalDetail(request,response);
			}
		 else if( action.equals("ConditionalAppraisal_getExcel.condapp") || action.equals("ConditionalAppraisalReport_getExcel.condapp") )
			{
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				JSONObject colmodel = UIUtils.getXlColModel(request, response);
				colmodel.put("title","Conditional Appraisal");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = conditionalAppraisalService.getCondAppExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "ConditionalAppraisal", format);
			}
		 else if( action.equals("newcomponent_add.condapp"))
			{
			   PrintWriter out = response.getWriter();
				
				
			 	String compname=request.getParameter("compname");
			 	String mechid = request.getParameter("mechid");			 	
			    String compid = conditionalAppraisalService.addnewcomponent(compname,mechid);			    
			    out.print(compid);
			}	
		 else if( action.equals("ConditionalAppraisalReport_ExcelView.condapp"))
			{
				CommonFilter commonFilter=populateCommonFilter(request, "ConditionalAppraisalCommonFilter",true);
				String flid=request.getParameter("flid");
				String date=request.getParameter("date");
				String keyId=request.getParameter("keyId");
				String title=request.getParameter("title");
				String isFormat=request.getParameter("isFormat");
				commonFilter.setFlid(flid);
				commonFilter.setDteend(date);
				commonFilter.setKey(keyId);
				commonFilter.setFreq(isFormat);
				//commonFilter.setAbnViewType ("REPORT");
				List<String []> conditionalGrid  =conditionalAppraisalService.getConditionalAppraisalGrid(commonFilter);
				CommonMessage.debugMsg("Printing the conditional");
				
				for(String[] arr:conditionalGrid) 
				{
					CommonMessage.debugMsg(Arrays.toString(arr));
				}
			
				String clfunction ="";
				String cldate ="";
				
				if(conditionalGrid.size() > 3)
				{
					String [] datarow = conditionalGrid.get(3);
					 clfunction =  datarow[17];
					 cldate  =  datarow[18];
				}	  
			    
				JSONObject colmodel = getTableModelExcel(conditionalGrid,isFormat);
				colmodel.put("title","Conditional Appraisal:  " + title);
				String format = "xlsx";
				Workbook wb = conditionalAppraisalService.getViewExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "ConditionalAppraisal", format);
			}
		
		else if( action.equals("checkingtool_combo.condapp"))
		{   
		   ComboFilter comboFilter = UIUtils.fillComboFilter(request);
		   CommonFilter commonFilter = new CommonFilter();
			//comboList = new ArrayList<ComboBox>();
			
			//commonFilter.set(currentFilter);
			//String assmId=request.getParameter("cmbAssmbid");
			/*if(UIUtils.isValidKeyId(assmId))
			{
				ComboFilter assembly=new ComboFilter();
				assembly.setId(assmId);
				commonFilter.setAssembly(assembly);
			}	*/
			List<ComboBox>  comboList = conditionalAppraisalService.getcheckingtool(commonFilter,comboFilter);
			UIUtils.writeComboBox(response,comboList,comboFilter);
		}
		 else if( action.equals("spareCombo.condapp"))
			{   
			   ComboFilter currentFilter = new ComboFilter();
			   CommonFilter commonFilter = new CommonFilter();
				List<ComboBox> comboList = new ArrayList<ComboBox>();
				
				commonFilter.setSpare(currentFilter);
				String assmId=request.getParameter("cmbAssmbid");
				if(UIUtils.isValidKeyId(assmId))
				{
					ComboFilter assembly=new ComboFilter();
					assembly.setId(assmId);
					commonFilter.setAssembly(assembly);
				}	
				comboList = conditionalAppraisalService.getSpareComboList(commonFilter);
			}
		 else if(action.equals("functionalLoc.condapp"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setCompany("cmbComp");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSectionid");
				functLocFieldNameBean.setCell("cmbCellid");
				functLocFieldNameBean.setMachine("cmbMachineid");
				functLocFieldNameBean.setFunctionalLocId("cmbCdapFlid");
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(true);
				FormModes formModes = FormModes.create;
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			}
	}
	private void deleteConditionlApprisal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String clear=request.getParameter("clear");
		boolean frmClr=true;
		if(UIUtils.isValidKeyId(clear))
			frmClr=false;
    	try
		{
	    	if(httpSession !=null && user !=null)
			{
	    		PlmTlConditionalappraisal plmTlConditionalappraisal=new PlmTlConditionalappraisal();
	    		plmTlConditionalappraisal=(PlmTlConditionalappraisal)UIUtils.setBeanProperties((Object)plmTlConditionalappraisal,request);
	    		
	    		PlmTlConditionalappraisalmst plmTlConditionalappraisalmst=new PlmTlConditionalappraisalmst();
	    		plmTlConditionalappraisalmst=(PlmTlConditionalappraisalmst)UIUtils.setBeanProperties((Object)plmTlConditionalappraisalmst,request);
	    		JSONObject successData=new JSONObject();
	    		JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
	    		String savemsg;
	    		plmTlConditionalappraisal.setCdapCreatedby(user.getUsrm_ccno());
	    		CommonMessage.debugMsg("plmTlConditionalappraisalmst.getCdamKeyid()" + plmTlConditionalappraisalmst.getCdamKeyid() );
	    		
				if(UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamKeyid())){
					plmTlConditionalappraisalmst=conditionalAppraisalService.delete(plmTlConditionalappraisalmst);
					savemsg=" Data Deleted succesfully";
				}
				else
				{
					savemsg= "Data Not Deleted  ";
				}
	    		successData.put("msg", savemsg);
	    		if(frmClr){
					ConditionalappraisalSuccessmsg.put("flid", "");
					ConditionalappraisalSuccessmsg.put("date", "");
					ConditionalappraisalSuccessmsg.put("keyId","");
				}else{
					ConditionalappraisalSuccessmsg.put("flid", plmTlConditionalappraisalmst.getCdamFlid());
					ConditionalappraisalSuccessmsg.put("date", plmTlConditionalappraisalmst.getCdamDate());
					ConditionalappraisalSuccessmsg.put("keyId", plmTlConditionalappraisalmst.getCdamKeyid());
				}
	    		ConditionalappraisalSuccessmsg.put("formClear",false);
	    		ConditionalappraisalSuccessmsg.put("successData", successData);
	    		CommonMessage.debugMsg("ConditionalappraisalSuccessmsg   "+ConditionalappraisalSuccessmsg.toString());
	    		out.print(ConditionalappraisalSuccessmsg.toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void saveConditionlApprisal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String elementid=request.getParameter("hdnelementId");
		String clear=request.getParameter("clear");
		String newFrm=request.getParameter("new");
		boolean frmClr=true;
		String filemanger=request.getParameter("filemanger");


		
		if(!UIUtils.isValidKeyId(newFrm))
			newFrm="T";
		if(UIUtils.isValidKeyId(clear) || newFrm.equals("F"))
			frmClr=false;
		CommonMessage.debugMsg("clear:   "+clear);
		if( httpSession != null && user != null)
		{  
			PlmTlConditionalappraisal plmTlConditionalappraisal=new PlmTlConditionalappraisal();
			PlmTlConditionalappraisalmst plmTlConditionalappraisalmst=new PlmTlConditionalappraisalmst();
			
			plmTlConditionalappraisal=(PlmTlConditionalappraisal)UIUtils.setBeanProperties((Object)plmTlConditionalappraisal,request);
			plmTlConditionalappraisalmst=(PlmTlConditionalappraisalmst)UIUtils.setBeanProperties((Object)plmTlConditionalappraisalmst,request);
			PlmTlConditionalappraisalmst  existPlmTlConditionalappraisalmst =(PlmTlConditionalappraisalmst)httpSession.getAttribute("plmTlConditionalappraisalmst");
			JSONObject successData=new JSONObject();
			JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
			CommonMessage.debugMsg("elementId: "+elementid);
			plmTlConditionalappraisalmst.setCdamCreatedby(user.getUsrm_ccno());
			plmTlConditionalappraisalmst.setCdamElementid(elementid);
			if(plmTlConditionalappraisal!=null){
				plmTlConditionalappraisal.setCdapCreatedby(user.getUsrm_ccno());
				plmTlConditionalappraisalmst.setPlmTlConditionalappraisal(plmTlConditionalappraisal);
			}
			
			String savemsg;		
			try
			{
				if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamKeyid()))
			 	{
					existPlmTlConditionalappraisalmst=conditionalAppraisalService.create(plmTlConditionalappraisalmst, existPlmTlConditionalappraisalmst);
					 savemsg=" Data Saved Succesfully";
				 }
				 else{
					 existPlmTlConditionalappraisalmst=conditionalAppraisalService.update(plmTlConditionalappraisalmst, existPlmTlConditionalappraisalmst);
					 if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapKeyid()))
						 savemsg= "Data Saved Succesfully";
					 else
						 savemsg= "Data Updated Succesfully";
				 }
				
				if(frmClr){
					ConditionalappraisalSuccessmsg.put("flid", "");
					ConditionalappraisalSuccessmsg.put("date", "");
					ConditionalappraisalSuccessmsg.put("keyId","");
					ConditionalappraisalSuccessmsg.put("formClear", true);
				}else{
					ConditionalappraisalSuccessmsg.put("flid", plmTlConditionalappraisalmst.getCdamFlid());
					ConditionalappraisalSuccessmsg.put("date", plmTlConditionalappraisalmst.getCdamDate());
					ConditionalappraisalSuccessmsg.put("keyId", existPlmTlConditionalappraisalmst.getCdamKeyid());
					ConditionalappraisalSuccessmsg.put("formClear", false);
				}
				if(UIUtils.isValidKeyId(filemanger)){
				    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
				    ConditionalappraisalSuccessmsg.put("filemanger",true);
				    ConditionalappraisalSuccessmsg.put("formClear",false);
				    ConditionalappraisalSuccessmsg.put("keyId", existPlmTlConditionalappraisalmst.getCdamKeyid());
				}
	        	successData.put("msg", savemsg);
	    		ConditionalappraisalSuccessmsg.put("successData", successData);
	    		CommonMessage.debugMsg("ConditionalappraisalSuccessmsg: "+ConditionalappraisalSuccessmsg.toString());
	    		out.print(ConditionalappraisalSuccessmsg.toString());
			}catch (ValidationExceptions e) {
				CommonMessage.debugMsg("Inside ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "plmTlConditionalappValidation");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
	    		CommonMessage.debugMsg("Inside Exceptions");
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
				e.printStackTrace();
			}
		}
	}

	private void saveConditionlApprisalEntry(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String elementid=request.getParameter("hdnelementId");
		String clear=request.getParameter("clear");
		String newFrm=request.getParameter("new");
		boolean frmClr=true;
		String filemanger=request.getParameter("filemanger");


		
		if(!UIUtils.isValidKeyId(newFrm))
			newFrm="T";
		if(UIUtils.isValidKeyId(clear) || newFrm.equals("F"))
			frmClr=false;
		CommonMessage.debugMsg("clear:   "+clear);
		if( httpSession != null && user != null)
		{  
			PlmTlConditionalappraisalEntry plmTlConditionalappraisal= new PlmTlConditionalappraisalEntry();
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmstentry();
			
			plmTlConditionalappraisal=(PlmTlConditionalappraisalEntry)UIUtils.setBeanProperties((Object)plmTlConditionalappraisal,request);
			plmTlConditionalappraisalmst=(PlmTlConditionalappraisalmstentry)UIUtils.setBeanProperties((Object)plmTlConditionalappraisalmst,request);
			PlmTlConditionalappraisalmstentry  existPlmTlConditionalappraisalmst =(PlmTlConditionalappraisalmstentry)httpSession.getAttribute("plmTlConditionalappraisalmst");
			JSONObject successData=new JSONObject();
			JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
			CommonMessage.debugMsg("elementId: "+elementid);
			plmTlConditionalappraisalmst.setCdamCreatedby(user.getUsrm_ccno());
			plmTlConditionalappraisalmst.setCdamElementid(elementid);
			if(plmTlConditionalappraisal!=null){
				plmTlConditionalappraisal.setCdapCreatedby(user.getUsrm_ccno());
				plmTlConditionalappraisalmst.setPlmTlConditionalappraisal(plmTlConditionalappraisal);
			}
			
			String savemsg;		
			try
			{
				if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamKeyid()))
			 	{
					CommonMessage.debugMsg("manocreate");
					existPlmTlConditionalappraisalmst=conditionalAppraisalService.createMstEntry(plmTlConditionalappraisalmst, existPlmTlConditionalappraisalmst);
					 savemsg=" Data Saved Succesfully";
				}
				else
				{   String date = plmTlConditionalappraisalmst.getCdamDate();
				CommonMessage.debugMsg(CommonFunctions.pg_getDateTimeFromDate(date)+" Changed Date");		 		
				plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.pg_getDateTimeFromDate(date));
				
					String cdamkeyid = conditionalAppraisalService.checkdata(plmTlConditionalappraisalmst,"Y");
				
				     if(Integer.parseInt(cdamkeyid)> 0)
				     {
				    	 
				    	 
				    	 existPlmTlConditionalappraisalmst=conditionalAppraisalService.updateMstEntry(plmTlConditionalappraisalmst, existPlmTlConditionalappraisalmst);
				    	 
						 if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapKeyid()))
							 savemsg= "Data Saved Succesfully";
						 else
							 savemsg= "Data Updated Succesfully";
				     }					 
					 else
					 {
						 existPlmTlConditionalappraisalmst=conditionalAppraisalService.createMstEntry(plmTlConditionalappraisalmst, existPlmTlConditionalappraisalmst);
						 savemsg=" Data Saved Succesfully";
					 }
				}
				
				if(frmClr){
					ConditionalappraisalSuccessmsg.put("flid", "");
					ConditionalappraisalSuccessmsg.put("date", "");
					ConditionalappraisalSuccessmsg.put("keyId","");
					ConditionalappraisalSuccessmsg.put("formClear", true);
				}else{
					ConditionalappraisalSuccessmsg.put("flid", plmTlConditionalappraisalmst.getCdamFlid());
					ConditionalappraisalSuccessmsg.put("date", plmTlConditionalappraisalmst.getCdamDate());
					ConditionalappraisalSuccessmsg.put("keyId", existPlmTlConditionalappraisalmst.getCdamKeyid());
					ConditionalappraisalSuccessmsg.put("formClear", false);
				}
				if(UIUtils.isValidKeyId(filemanger)){
				    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
				    ConditionalappraisalSuccessmsg.put("filemanger",true);
				    ConditionalappraisalSuccessmsg.put("formClear",false);
				    ConditionalappraisalSuccessmsg.put("keyId", existPlmTlConditionalappraisalmst.getCdamKeyid());
				}
	        	successData.put("msg", savemsg);
	    		ConditionalappraisalSuccessmsg.put("successData", successData);
	    		CommonMessage.debugMsg("ConditionalappraisalSuccessmsg: "+ConditionalappraisalSuccessmsg.toString());
	    		out.print(ConditionalappraisalSuccessmsg.toString());
			}catch (ValidationExceptions e) {
				CommonMessage.debugMsg("Inside ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "plmTlConditionalappValidation");
				e.printStackTrace();
				out.print(errMessage.toString());
	    	}
	    	catch(Exception e)
			{
	    		CommonMessage.debugMsg("Inside Exceptions");
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
				e.printStackTrace();
			}
		}
	}
	private void deleteConditionlApprisalDetail(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String clear=request.getParameter("clear");
		boolean frmClr=true;
		if(UIUtils.isValidKeyId(clear))
			frmClr=false;
    	try
		{
	    	if(httpSession !=null && user !=null)
			{
	    		PlmTlConditionalappraisal plmTlConditionalappraisal=new PlmTlConditionalappraisal();
	    		PlmTlConditionalappraisalmst plmTlConditionalappraisalmst=new PlmTlConditionalappraisalmst();
	    		plmTlConditionalappraisal=(PlmTlConditionalappraisal)UIUtils.setBeanProperties((Object)plmTlConditionalappraisal,request);
	    		plmTlConditionalappraisalmst=(PlmTlConditionalappraisalmst)UIUtils.setBeanProperties((Object)plmTlConditionalappraisalmst,request);
	    		JSONObject successData=new JSONObject();
	    		JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
	    		String savemsg;
	    		plmTlConditionalappraisal.setCdapCreatedby(user.getUsrm_ccno());
	    		
				if(UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapKeyid())){
					plmTlConditionalappraisal=conditionalAppraisalService.delete(plmTlConditionalappraisal);
					savemsg=" Data Deleted succesfully";
				}
				else
				{
					savemsg= "Data Not Deleted  ";
				}
	    		successData.put("msg", savemsg);
	    		if(frmClr){
					ConditionalappraisalSuccessmsg.put("flid", "");
					ConditionalappraisalSuccessmsg.put("date", "");
					ConditionalappraisalSuccessmsg.put("keyId","");
				}else{
					ConditionalappraisalSuccessmsg.put("flid", plmTlConditionalappraisalmst.getCdamFlid());
					ConditionalappraisalSuccessmsg.put("date", plmTlConditionalappraisalmst.getCdamDate());
					ConditionalappraisalSuccessmsg.put("keyId", plmTlConditionalappraisalmst.getCdamKeyid());
				}
	    		ConditionalappraisalSuccessmsg.put("formClear",true);
	    		ConditionalappraisalSuccessmsg.put("successData", successData);
	    		CommonMessage.debugMsg("ConditionalappraisalSuccessmsg   "+ConditionalappraisalSuccessmsg.toString());
	    		out.print(ConditionalappraisalSuccessmsg.toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*private void saveConditionlApprisal(HttpServletRequest request,HttpServletResponse response,GenTlConditionalappraisalBean genTlConditionalappraisalBean) throws IOException 
	{
		CommonMessage.debugMsg("saveConditionlApprisal........." );
		
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null)
		{  
		GenTlConditionalappraisal genTlConditionalappraisal=new GenTlConditionalappraisal();
		GenTlConditionalappraisal  existGenTlConditionalappraisal =(GenTlConditionalappraisal)httpSession.getAttribute("genTlConditionalappraisal");
			
		genTlConditionalappraisal=(GenTlConditionalappraisal)UIUtils.setBeanProperties((Object)genTlConditionalappraisal,request);
		CommonMessage.debugMsg("genTlConditionalappraisal.getCdapKeyid().............."+genTlConditionalappraisal.getCdapKeyid());
		JSONObject successData=new JSONObject();
		JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
		String savemsg;		
			try
			{
			 if(genTlConditionalappraisal.getCdapKeyid()==null)
				 {
					 
				
				 existGenTlConditionalappraisal=conditionalAppraisalService.create(genTlConditionalappraisal, existGenTlConditionalappraisal, genTlConditionalappraisalBean);
				 savemsg=" Data saved succesfully";
				 }
				 else{
					 CommonMessage.debugMsg("genTlConditionalappraisal.getCdapKeyid() for update:");
					
					 existGenTlConditionalappraisal=conditionalAppraisalService.update(genTlConditionalappraisal, existGenTlConditionalappraisal, genTlConditionalappraisalBean);
					 savemsg= "Data Updated succesfully";
				 }
        	successData.put("msg", savemsg);
    		ConditionalappraisalSuccessmsg.put("successData", successData);
    		out.print(ConditionalappraisalSuccessmsg.toString());

		}catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ConditionalAppraisal");
			out.print(errMessage.toString());
    	}catch(Exception e){
		}
		
		}
	}
	private void deleteConditionlApprisal(HttpServletRequest request,
			HttpServletResponse response, GenTlConditionalappraisalBean genTlConditionalappraisalBea) throws BusinessApplicationExceptions, Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		GenTlConditionalappraisal genTlConditionalappraisal=new GenTlConditionalappraisal();

    		genTlConditionalappraisal=(GenTlConditionalappraisal)UIUtils.setBeanProperties((Object)genTlConditionalappraisal,request);
    		JSONObject successData=new JSONObject();
    		JSONObject ConditionalappraisalSuccessmsg=new JSONObject();
    		String savemsg;
			
			if(UIUtils.isValidKeyId(genTlConditionalappraisal.getCdapKeyid())){
				genTlConditionalappraisal=conditionalAppraisalService.delete(genTlConditionalappraisal);
				savemsg=" Data Deleted succesfully";
			}
		
			else
			{
				savemsg= "Data Not Deleted  ";
				
			}
    		successData.put("msg", savemsg);
    		ConditionalappraisalSuccessmsg.put("successData", successData);
    		out.print(ConditionalappraisalSuccessmsg.toString());
    		
		}
		}
		catch(Exception e)
		{
			
		}
	}*/
	private JSONObject getTableModelExcel(List<String[]> headers, String isFormat) {

		//CommonMessage.debugMsg("getTableModelExcel");
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);

		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		jqGridTableModel.getRowHeaders().add(colHeader);

		jqGridTableModel.setRowNumbers(true);

		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(800);

		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(800);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			CommonMessage.debugMsg("isFormat==="+isFormat);
			
			if (isFormat.equals("Y") && ( i == 0 || i == 1 || i == 2 || i == 5 || i == 7 
					//|| i == 9 
					|| i == 12 || i == 13 || i == 14 ||  (i>= 16))
					) {
				jqGridColModel.setHidden(true);
			}
			else if (( i == 0 || i == 1 || i == 2 || i == 5 || i == 7 
					|| i == 12 || i == 13 || i == 14 || i == 17 || i == 18 ||  i == 24 ||  i == 25 ) ) {
				jqGridColModel.setHidden(true);
			}
			else if (i == 3) {

				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
			}
			else if (i >= 0) {

				jqGridColModel.setWidth(140);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "93%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = 	FilterValues.getPMRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		String type = request.getParameter("type");
		if(UIUtils.isValidKeyId(type))
			commonFilter.setAbnViewType(type);
		
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	} 
}
