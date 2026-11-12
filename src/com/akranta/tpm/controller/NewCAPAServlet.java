package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;
import com.akranta.tpm.service.NewCAPAService;
import com.akranta.tpm.service.impl.NewCAPAServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NewCAPAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewCAPAService newCAPAService;

    public NewCAPAServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
  private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  String action=UIUtils.getActionPart(request);
	   HttpSession httpSession=request.getSession(false);
	   ComboFilter comboFilter = new ComboFilter();
	  newCAPAService=(NewCAPAServiceImpl)UIUtils.getServiceObject(request, "NewCAPAServiceImpl");
	  newCAPAService.NewCAPAServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		
	  if(action.equals("CustomerComplaint_input.ncapa")){  
		
		  String CapaId=request.getParameter("CapaId");
		  String mode=request.getParameter("mode");
		  System.out.println("String Mode::"+mode);
		  AdmTlUsermst user = UIUtils.getLoginUser(request);
		  String LoginUser=user.getUsrm_ccno();
		 String LoginDMTUser=request.getParameter("LoginDMTUser");
		 String Status=request.getParameter("Status");
		 String TargetJH=request.getParameter("TargetJH");
		 String TargetFlid="";
		 if(UIUtils.isValidKeyId(TargetJH)){
			 TargetFlid=newCAPAService.getTargetFlid(TargetJH); 
		 }
	  		QtmTlCapa qtmTlCapa=new QtmTlCapa();
	  		if(UIUtils.isValidKeyId(CapaId))
			{
	  			System.out.println("inside If");
	  			qtmTlCapa=newCAPAService.getCapaData(CapaId);
			
				
			}
	  		request.setAttribute("TargetFlid",TargetFlid);
	  		request.setAttribute("mode", mode);
	  		request.setAttribute("LoginUser", LoginUser);
	  		request.setAttribute("Status",Status);
	  		request.setAttribute("LoginDMTUser", LoginDMTUser);
	  		request.setAttribute("qtmTlCapa", qtmTlCapa);
				  RequestDispatcher rd=request.getRequestDispatcher("pages/CAPA.jsp");
		 rd.forward(request, response);
	  }
	  //-- vignesh --
	  if (action.equals("CustomerComplaintReport_input.ncapa")) {

		    String capaId = request.getParameter("CapaId");
		    String popup  = request.getParameter("popup");
		    String mode   = request.getParameter("mode");

		    // ✅ force VIEW for popup always
		    if ("Y".equalsIgnoreCase(popup)) {
		        mode = "VIEW";
		    }
		    if (mode == null) mode = "VIEW";

		    AdmTlUsermst user = UIUtils.getLoginUser(request);
		    String loginUser = (user == null) ? "" : user.getUsrm_ccno();

		    String loginDMTUser = request.getParameter("LoginDMTUser");
		    String status       = request.getParameter("Status");
		    String targetJH     = request.getParameter("TargetJH");

		    String targetFlid = "";
		    if (UIUtils.isValidKeyId(targetJH)) {
		        targetFlid = newCAPAService.getTargetFlid(targetJH);
		    }

		    QtmTlCapa qtmTlCapa = new QtmTlCapa();
		    if (UIUtils.isValidKeyId(capaId)) {
		        qtmTlCapa = newCAPAService.getCapaData(capaId);
		    }

		    request.setAttribute("CapaId", capaId);
		    request.setAttribute("popup", popup);
		    request.setAttribute("mode", mode);

		    request.setAttribute("TargetFlid", targetFlid);
		    request.setAttribute("LoginUser", loginUser);
		    request.setAttribute("Status", status);
		    request.setAttribute("LoginDMTUser", loginDMTUser);
		    request.setAttribute("qtmTlCapa", qtmTlCapa);

		    RequestDispatcher rd = request.getRequestDispatcher("pages/CapaPopUp.jsp");
		    rd.forward(request, response);
		}

	  
	  
	  else if(action.equals("CustomerComplaint_getCol.ncapa")){
		    String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.CAPA", "CAPADetails");
			response.getWriter().print(colModel);
			response.getWriter().close();
	  }
	  else if(action.equals("CustomerComplaint_getData.ncapa")){
		  try{
			  CommonFilter commonFilter = new CommonFilter();
		  
			String keyid =request.getParameter("CapaId");
			System.out.println("keyid"+keyid);
			commonFilter.setKey(keyid);
			PrintWriter out = response.getWriter();
			List<String[]> CAPAList = newCAPAService.getCapaDetails(commonFilter);
			JSONObject WhatifData = UIUtils.convertToJqGridTableObject(CAPAList, request, 0, 0);
			out.println(WhatifData);
		} 
		  catch (Exception e) {
			e.printStackTrace(); 
	  }
	  }
	  
	  else if(action.equals("CustomerComplaint_save.ncapa")){
		  NewCAPASave(request,response);
	  }
	
	  else if(action.equals("DefectCombo.ncapa")){
		  try{
			  System.out.println("In type combobox Col"+action);
			    ComboFilter combofilter=UIUtils.fillComboFilter(request);
			    String Sect=request.getParameter("sectId");
			    List<ComboBox>projectList=newCAPAService.getDefectComboList(combofilter,Sect);
			    UIUtils.writeComboBox(response,projectList,combofilter);			    
			  }
			 catch(Exception e){
				 e.printStackTrace();
			 }  
	  }
	  else if(action.equals("CAPAWorkFlow_getCol.ncapa")){
		    String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.CAPA", "CAPAApproval");
			response.getWriter().print(colModel);
			response.getWriter().close();
	  }
	  
	  else if(action.equals("CAPAWorkFlow_getData.ncapa")){
			String CapaId=request.getParameter("CapaId");
			System.out.println("CapaId KeyID"+CapaId);
		 CommonFilter commonFilter = populateCommonFilter(request,"MOCTeam",false);
				try {
					
						List<String[]> MachineGrid = newCAPAService.getDmtLeaderApproval(commonFilter,CapaId);
						//System.out.println("Data is enter or not" + MachineGrid.get(0));
						PrintWriter out = response.getWriter();
						//System.out.println("get data method1");
						JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
						out.println(machinegrid);
					
					
				} catch (Exception e) {
					//System.out.println(e.getMessage());
				}  
		  
	  }
	  else if (action.equals("ComplainWorkFlow_save.ncapa")){
			
			 SaveComplainWorkFlow(request,response);

		 }
	  else if (action.equals("CapaGridActionPlan_save.ncapa")){
			
			 SaveCapaActionPlan(request,response);

		 }
	  else if(action.equals("ComplaintType.ncapa")){
		  System.out.println("In type get Col");
		  PrintWriter out = response.getWriter();
			 out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CAPA", "ComplaintType"));
	  }
	  
	  else if(action.equals("comboDefect.ncapa")){
		  try {
       	      comboFilter=UIUtils.fillComboFilter(request);
       	      String flid=request.getParameter("flid");
			  List<ComboBox> course = newCAPAService.getDefect(comboFilter,flid);
			   UIUtils.writeComboBox(response, course, comboFilter);
				} catch (Exception e) {
					e.printStackTrace();
				}
	  }
	  
	  //elumalai new remove row
	  else if (action.equals("CapaGridActionPlan_remove.ncapa")) {
		    try {
		        QtmTlCapaActionmst qtmTlCapaActionmst = new QtmTlCapaActionmst();
		        deleteCapaActionPlanDetail(request, response, qtmTlCapaActionmst);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	  
	
	  
	   else if(action.equals("functionalLoc.ncapa"))
				{
			    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbTraFactoryid");
				functLocFieldNameBean.setSection("cmbTraSectionid");
				functLocFieldNameBean.setCell("cmbTraCellid");
				functLocFieldNameBean.setMachine("cmbTraMachineid");
				functLocFieldNameBean.setFunctionalLocId("cmbTraFlid");
				functLocFieldNameBean.setSbu("cmbTraSbu");
				functLocFieldNameBean.setPbu("cmbTraPbu");
				
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);

	           FormModes formModes = FormModes.create;
	           UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);	 
				}
	  // ----- Vignesh 23Dec2025 CAPA REPORT -------------------------------------------------------------------------//
	  // ----- Vignesh 23Dec2025 CAPA REPORT -------------------------------------------------------------------------//
	  
	   else if(action.equals("ComplaintApproval_input.ncapa")){
			System.out.println("Inside the Input method");
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			  String LoginUser=user.getUsrm_ccno();
             String mode=request.getParameter("mode");
             System.out.println("MODE::::"+mode);
             request.setAttribute("mode", mode);
             request.setAttribute("LoginUser", LoginUser);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/CapaApprovalGrid.jsp");
			rd.forward(request, response);
		}
	   else if(action.equals("ComplaintApproval_getCol.ncapa")){
			System.out.println("in  GetCol"+action);
			PrintWriter out = response.getWriter();	
			CommonFilter commonFilter = populateCommonFilter(request,"MocCommonFilter",true);
			commonFilter.setIsGetCol("Y");
			 List<String[]> saralViewData  = newCAPAService.getCAPARelatedData(commonFilter);
			System.out.println("in  GetCol"+action);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			gridColModel.setHeaderNum(1);
			String[] colHeader = saralViewData.get(1);
			String[] colHeaderCond = saralViewData.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "108%%");
			httpSession.removeAttribute("MOCColModel");
			httpSession.setAttribute("MOCColModel", colModel);
			httpSession.removeAttribute("MOCCommonFilter");
			httpSession.setAttribute("MOCCommonFilter", commonFilter);
		    out.println(colModel);
		}
	  
		// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//
		
		 else if (action.equals("ComplaintApproval_getExcel.ncapa")) {
				//HttpSession httpSession = request.getSession(false);
			
				

				CommonFilter commonFilter = populateCommonFilter(request,"MocCommonFilter",false);

	JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
	tblJSONObj.put("title","Complaint Approval");
      String format = ExcelUtils.getFormat(request);	
							
	Workbook wb = newCAPAService.getExcelreport(commonFilter,tblJSONObj,format);
	ExcelUtils.writeToResponse(response, wb, "CapaApprovalGrid", format);


			} 
	
	// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//

			    	
		else if(action.equals("ComplaintApproval_getData.ncapa")){
			
			PrintWriter out = response.getWriter();	
			CommonFilter commonFilter=populateCommonFilter(request, "MocCommonFilter", false);
			System.out.println("in  Data"+action);
			commonFilter.setIsGetCol("N");
			List<String[]> saralViewData = newCAPAService.getCAPARelatedData(commonFilter);
			    JSONObject dataJson = UIUtils.convertToJqGridTableObject(saralViewData,request,2,0,commonFilter.getTotalRecordCnt()+3);
			out.print(dataJson);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute("MocCommonFilter");
			httpSession.setAttribute("MocCommonFilter", commonFilter);
		}	
	  
	  
	  //2 ,0------------------------------------- Vignesh 23Dec2025 CAPA REPORT -----------------------------------------------//
	  // ------------------------------------- Vignesh 23Dec2025 CAPA REPORT----------------------------------------------------//
	  
	   else if(action.equals("CapaReport_input.ncapa")){
			System.out.println("Inside the Input method");
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			  String LoginUser=user.getUsrm_ccno();
             String mode=request.getParameter("mode");
             System.out.println("MODE::::"+mode);
             request.setAttribute("mode", mode);
             request.setAttribute("LoginUser", LoginUser);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/CapaReportGrid.jsp");
			rd.forward(request, response);
		}
	  
	   else if(action.equals("CapaReport_getCol.ncapa")){
			System.out.println("in  GetCol"+action);
			PrintWriter out = response.getWriter();	
			
			CommonFilter commonFilter = populateCommonFilter(request,"MocCommonFilter",true);
		//	String rp = "Report";
			 commonFilter.settitle("Report");
			 
			 commonFilter.setIsGetCol("Y");
			 List<String[]> saralViewData  = newCAPAService.getCAPARelatedData(commonFilter);
			System.out.println("in  GetCol"+action);
			
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			
            GridColModel gridColModel = new GridColModel();
			
			List<String> formattorList =  new ArrayList<String>();
			formattorList.add("btnformatter");
			
			List<String> formattorFromList =  new ArrayList<String>();
			formattorFromList.add("25");
			
			List<String> formattorToList =  new ArrayList<String>();
			formattorToList.add("25");
			
			gridColModel.setMultiformatter(formattorList);
			gridColModel.setMultiformattorFromCol(formattorFromList);
			gridColModel.setMultiformattorToCol(formattorToList);
			gridColModel.setHeaderNum(1);
		//1	
			String[] colHeader = saralViewData.get(1);
			String[] colHeaderCond = saralViewData.get(0);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			JSONObject colModel =new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "108%%");
			httpSession.removeAttribute("MOCColModel");
			httpSession.setAttribute("MOCColModel", colModel);
			httpSession.removeAttribute("MOCCommonFilter");
			httpSession.setAttribute("MOCCommonFilter", commonFilter);
		    out.println(colModel);
		}
	  
		// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//
		
		 else if (action.equals("CapaReport_getExcel.ncapa")) {
				//HttpSession httpSession = request.getSession(false);
			
				
			
				CommonFilter commonFilter = populateCommonFilter(request,"MocCommonFilter",false);
				 commonFilter.settitle("Report");
	JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
	tblJSONObj.put("title","Complaint Approval");
      String format = ExcelUtils.getFormat(request);	
							
	Workbook wb = newCAPAService.getExcelreport(commonFilter,tblJSONObj,format);
	ExcelUtils.writeToResponse(response, wb, "CapaReport", format);


			} 
	
	// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//

			    	
		 else if(action.equals("CapaReport_getData.ncapa")) {

			    PrintWriter out = response.getWriter();
			    CommonFilter commonFilter = populateCommonFilter(request, "MocCommonFilter", false);

			    commonFilter.settitle("Report");

			    // ✅ set before calling service/dao if total count depends on it
			    commonFilter.setViewClick('Y');

			    System.out.println("in Data " + action);
			    
			    commonFilter.setIsGetCol("N");
			    List<String[]> saralViewData = newCAPAService.getCAPARelatedData(commonFilter);

			    JSONObject dataJson = UIUtils.convertToJqGridTableObject(
			            saralViewData, request, 2, 0, commonFilter.getTotalRecordCnt() + 3
			    );

			    out.print(dataJson);

			    httpSession.removeAttribute("MocCommonFilter");
			    httpSession.setAttribute("MocCommonFilter", commonFilter);
			}
   
	  
	
	  
  }
  
//elumalai new remove row
  private void deleteCapaActionPlanDetail(HttpServletRequest request,
	        HttpServletResponse response, QtmTlCapaActionmst qtmTlCapaActionmst) throws IOException {

	    HttpSession httpSession = request.getSession(false);
	    AdmTlUsermst user = UIUtils.getLoginUser(request);
	    ServletOutputStream out = response.getOutputStream();

	    try {
	        if (httpSession != null && user != null) {

	            QtmTlCapaActionmst newQtmTlCapaActionmst = new QtmTlCapaActionmst();

	            newQtmTlCapaActionmst = (QtmTlCapaActionmst) UIUtils.setBeanProperties(
	                    (Object) newQtmTlCapaActionmst, request);

	            String keyid = request.getParameter("dtlKeyid");
	            System.out.println("CAPA Delete Keyid :: " + keyid);

	            newQtmTlCapaActionmst.setQcpaKeyid(keyid);

	            if (UIUtils.isValidKeyId(newQtmTlCapaActionmst.getQcpaKeyid())) {
	                newQtmTlCapaActionmst = newCAPAService.deleteCapaActionPlanDetail(newQtmTlCapaActionmst);
	                httpSession.removeAttribute("QtmTlCapaActionmst" + keyid);
	            }

	            JSONObject successData = new JSONObject();
	            JSONObject returnData  = new JSONObject();
	            successData.put("msg", "Data Deleted Successfully");
	            successData.put("formClear", true);
	            returnData.put("successData", successData);
	            out.print(returnData.toString());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JSONObject err = new JSONObject();
	        err.put("tpmException", "Data Not Deleted");
	        out.print(err.toString());
	    }
	}
  
  
  private void SaveCapaActionPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
	// TODO Auto-generated method stub
	  HttpSession httpSession = request.getSession(false);
	  	ServletOutputStream out = response.getOutputStream();
	  	AdmTlUsermst user = UIUtils.getLoginUser(request);
	  	String saveMsg="";
	  	if( httpSession != null && user != null)
	  	{	
	  		try{
	  			 String keyid=request.getParameter("Keyid");
	  			 System.out.println("CapaId"+keyid);
	  			String CAPA=request.getParameter("CAPA");
	  			
	  			 String CorrectiveAction=request.getParameter("CorrectiveAction");
	  	 		 String PreventiveAction=request.getParameter("PreventiveAction");
	  	 		 String RootCause=request.getParameter("RootCause");
	  	 		 String TargetDate=request.getParameter("TargetDate");
	  	 		 String Status=request.getParameter("Status");
	  	 		 String MType=request.getParameter("MType");
	  	 		 String Defect=request.getParameter("Defect");
	  	 		 String Responsibility=request.getParameter("Responsibility");
	  	 		 String ButtonType=request.getParameter("ButtonType");
	  	 		 System.out.println("PreventiveAction="+PreventiveAction+"RootCause="+RootCause+"TargetDate="+TargetDate+"Status="+Status+"MType"+MType+"Defect"+"Responsibility="+Responsibility);
		  	 		
	  	 		 System.out.println("CapaId Id"+keyid);
	  	 		String CreatedBy=user.getUsrm_ccno();
	  	 		QtmTlCapaActionmst qtmTlCapaActionmst=new QtmTlCapaActionmst();
	  	 		QtmTlCapaActionmst existQtmTlCapaActionmst=(QtmTlCapaActionmst)httpSession.getAttribute("QtmTlCapaWorkflow");
	  	 		qtmTlCapaActionmst =(QtmTlCapaActionmst)UIUtils.setBeanProperties((Object)qtmTlCapaActionmst,request);
	  		
	  			if(CAPA.length()<=0){
	  				System.out.println("Inside the Create");
	  				existQtmTlCapaActionmst=newCAPAService.getCapaActionPlanSave(qtmTlCapaActionmst,existQtmTlCapaActionmst,keyid,PreventiveAction,RootCause,TargetDate,Status,MType,Defect,CreatedBy,CorrectiveAction,Responsibility);
	  				saveMsg="Data Created Successfully";
	  			}
	  			else{
	  			
	  				System.out.println("Inside the Create");
	  				existQtmTlCapaActionmst=newCAPAService.getCapaActionPlanUpdate(qtmTlCapaActionmst,existQtmTlCapaActionmst,keyid,PreventiveAction,RootCause,TargetDate,Status,MType,Defect,CreatedBy,CorrectiveAction,Responsibility,CAPA);
	  				saveMsg="Data Updated Successfully";
	  			}
	  			
	  			JSONObject persistentData = new JSONObject(); 
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg",saveMsg);
				successData.put("QcapkeyId", keyid);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("CapaDetId", existQtmTlCapaActionmst.getQcpaKeyid());
				returnData.put("ButtonType", ButtonType);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);	
				returnData.put("QcapkeyId", keyid);	
				out.print(returnData.toString());
			
				
	  		}
				catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ProjectValidations");
					out.print(errMessage.toString());					
				}
			    catch(BusinessApplicationExceptions e){
			    	JSONObject err=new JSONObject();
			    
			    	out.print(err.toString());
			    }
				catch(Exception e){
					e.printStackTrace();
					JSONObject err = new JSONObject();
				
					err.put("tpmException", "Data Not Saved");
					
					out.print(err.toString());
				}			
		    }			
}

private void SaveComplainWorkFlow(HttpServletRequest request, HttpServletResponse response) throws IOException {
	// TODO Auto-generated method stub
	  HttpSession httpSession = request.getSession(false);
	  	ServletOutputStream out = response.getOutputStream();
	  	AdmTlUsermst user = UIUtils.getLoginUser(request);
	  	String saveMsg="";
	  	if( httpSession != null && user != null)
	  	{	
	  		try{
	  			 String keyid=request.getParameter("keyid");
	  	 		 String EmpId=request.getParameter("EmpId");
	  	 		 String status=request.getParameter("status");
	  	 		 String date=request.getParameter("date");
	  	 		 String remarks=request.getParameter("remarks");
	  	 		 String CapaId=request.getParameter("CapaId");
	  	 		 System.out.println("CapaId Id"+CapaId);
	  	 		QtmTlCapaWorkflow qtmTlCapaWorkflow=new QtmTlCapaWorkflow();
	  	 		QtmTlCapaWorkflow existQtmTlCapaWorkflow=(QtmTlCapaWorkflow)httpSession.getAttribute("QtmTlCapaWorkflow");
	  	 		qtmTlCapaWorkflow =(QtmTlCapaWorkflow)UIUtils.setBeanProperties((Object)qtmTlCapaWorkflow,request);
	  		
	  			if(keyid.length()>0){
	  				System.out.println("Inside the Update");
	  				existQtmTlCapaWorkflow=newCAPAService.getComplaintApproval(qtmTlCapaWorkflow,existQtmTlCapaWorkflow,keyid,EmpId,status,date,remarks,CapaId);
	  				saveMsg="Data Updated Successfully";
	  				if(status.equals("C")){
	  					try{
							System.out.println("Inside   Approval Mail Trigger");
							String DmtLeader=request.getParameter("TargetDmtLeader");
					  		System.out.println("TargetDmtLeader"+DmtLeader);
					  		String AreaManager=request.getParameter("TargetAreaManager");
					  		String RaisedBy=request.getParameter("ComplaintRaisedBy");
					  		
					  		String Description=request.getParameter("Description");
					  		String glbmsg="";
					  		List<String[]> empMailIds = newCAPAService.getCAPAEmpMailIds(CapaId) ;
	                        System.out.println("emailId>>>>>>>>>>>>>>>>>>>>>>"+empMailIds);
	                        String mailIds = buildToMailIds(empMailIds);
	                        if( mailIds.isEmpty())
	            			{
	            				JSONObject succssMsg= new JSONObject();
	            				glbmsg="No Valid MailId Found ";				
	            				return ;
	            			}
	                        System.out.println("mailIds.>>>>>......."+mailIds);
							SendMailToAreaInchargeLeader(request,response,mailIds,CapaId,Description);
						}
						catch(Exception e){
						   System.out.println("Mail"+e);
						}
	  				}
	  			}
	  			
	  			JSONObject persistentData = new JSONObject(); 
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg",saveMsg);
				successData.put("QcapkeyId", CapaId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear",false);
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);	
				returnData.put("QcapkeyId", CapaId);	
				out.print(returnData.toString());
			
				
	  		}
				catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ProjectValidations");
					out.print(errMessage.toString());					
				}
			    catch(BusinessApplicationExceptions e){
			    	JSONObject err=new JSONObject();
			    
			    	out.print(err.toString());
			    }
				catch(Exception e){
					e.printStackTrace();
					JSONObject err = new JSONObject();
				
					err.put("tpmException", "Data Not Saved");
					
					out.print(err.toString());
				}			
		    }		
}

private void SendMailToAreaInchargeLeader(HttpServletRequest request, HttpServletResponse response, String mailIds,
		String capaId, String description) {
	// TODO Auto-generated method stub
 System.out.println("---------Sending Mail To AreaIncharge for Complaint Approval----------");
		 System.out.println("description:" +description);
		 System.out.println("emailId :" +mailIds);
		 System.out.println("complaintNo :" +capaId);
		
			try {
					//System.out.println("E-Mail Id  :" +UIUtils.isValidEmail(emailId) + "  " + emailId);
					if(UIUtils.isValidEmail(mailIds)){	
						
						String disclaimerNote = "--";			
						StringBuilder subject = new StringBuilder("");
						subject.append(" Complaint Wating for CAPA ");						
						StringBuilder totalContent = new StringBuilder();					
						totalContent.append("\r\n");
						totalContent.append("Complaint No: " +capaId).append("\r\n");
						totalContent.append("\r\n");
						totalContent.append("Complaint Description : " +description).append("\r\n");
						totalContent.append("\r\n");
					   
						totalContent.append(disclaimerNote);
						totalContent.append("\r\n");
						//totalContent.append("Regards,");
						totalContent.append("\r\n");
						totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");							
						System.out.println(" Before sendLotusNotesMail to JH leader" );
						System.out.println("Suggestion:"+description+"CAPAId"+capaId);
						UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),null);
						System.out.println("Content :\n" +totalContent.toString());
						System.out.println(" After sendLotusNotesMail to jh leader" );
					}
				//}
				else{
					System.out.println(" Mail not send to AreaIncharge for Complaint Approval no mail id" );
				}
			}
			catch( ValidationExceptions e){				
				System.out.println("ValidationExceptions from  sendMailToJhLeader()" + e);
			
			}catch (BusinessApplicationExceptions e) {
				System.out.println("BusinessApplicationExceptions from  sendMailToJhLeader()" + e);
			
			} /*
				 * catch(NotesException e) {
				 * System.out.println("NotesException from  sendMailToJhLeader()" + e); }
				 */catch(Exception e){
				
				System.out.println("Exception from  sendMailToJhLeader()" + e);
			} catch(Throwable e){
				
			}
			
			System.out.println("--------- Mail Send To Area Incharge for Suggestion Approvel----------");

}

@SuppressWarnings("unchecked")
private void NewCAPASave(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  	HttpSession httpSession = request.getSession(false);
	  	ServletOutputStream out = response.getOutputStream();
	  	AdmTlUsermst user = UIUtils.getLoginUser(request);
	  	
	  	JSONObject returnData = new JSONObject();
	  	JSONObject successData = new JSONObject(); 
		
	  	if( httpSession != null && user != null)
	  	{	
	  		QtmTlCapa newQtmTlCapa = new QtmTlCapa();
	  		
	  		QtmTlCapaWorkflow qtmTlCapaWorkflow = new QtmTlCapaWorkflow();
	  		String TargetDmtLeader=request.getParameter("TargetDmtLeader");
	  		System.out.println("TargetDmtLeader"+TargetDmtLeader);
	  		String TargetDMT=request.getParameter("TargetDMT");
	  		String TargetJH=request.getParameter("TargetJH");
	  		String TargetAreaManager=request.getParameter("TargetAreaManager");
	  		String ComplaintRaisedBy=request.getParameter("ComplaintRaisedBy");
	  		String Title=request.getParameter("title");
			System.out.println("Title is "+Title);
	  		//String TitleCapa=request.getParameter("Title");
	  		String CapaKeyid=request.getParameter("CapaKeyid");
			String CapaMasterKeyid=request.getParameter("CapaMasterkeyid");
			System.out.println("CapaKeyid"+CapaKeyid);
			System.out.println("CapaMasterId"+CapaMasterKeyid);
	  
	  		newQtmTlCapa.setQcapCreatedby(user.getUsrm_ccno());
	  		qtmTlCapaWorkflow.setQcwfCreatedby(user.getUsrm_ccno());
	  		qtmTlCapaWorkflow.setQcwfApproveby(TargetDmtLeader);
	  		qtmTlCapaWorkflow.setQcwfStatus("-");
	  		
	  		newQtmTlCapa=(QtmTlCapa)UIUtils.setBeanProperties((Object)newQtmTlCapa,request);
	  		qtmTlCapaWorkflow=(QtmTlCapaWorkflow)UIUtils.setBeanProperties((Object)qtmTlCapaWorkflow,request);
	  		
	  	// ✅ Force workflow date = complaint date (prevents '' / null date insert)
	  		qtmTlCapaWorkflow.setQcwfDate(newQtmTlCapa.getQcapDate());
	  		
	  		QtmTlCapa existQtmTlCapa=(QtmTlCapa)httpSession.getAttribute("NewCAPAServlet");
	  	//	QtmTlCapaWorkflow existQtmTlCapaWorkflow=(QtmTlCapaWorkflow)httpSession.getAttribute("NewCAPAServlet");
	  		
				try{
					
					boolean insert = true;
		//		if(Title.equals("WorkFlow"))
					
				if	("WorkFlow".equals(Title)){	
					if( UIUtils.isValidKeyId(newQtmTlCapa.getQcapKeyid()))
					{
						System.out.println("iNSIDE IF");
						existQtmTlCapa =newCAPAService.update(newQtmTlCapa, existQtmTlCapa);
						String CapaId=newQtmTlCapa.getQcapKeyid();
						String RoleId="ROLM0013";
						String Status="-";
						System.out.println("Inside CapaId"+CapaId);
						qtmTlCapaWorkflow.setQcwfCaparefid(CapaId);
						qtmTlCapaWorkflow.setQcwfRoleid("ROLM0013");
						qtmTlCapaWorkflow.setQcwfApproveby(TargetDmtLeader);
						
						qtmTlCapaWorkflow.setQcwfDate(newQtmTlCapa.getQcapDate());
						
						qtmTlCapaWorkflow=newCAPAService.UpdateWorkflow(qtmTlCapaWorkflow,CapaId,TargetDmtLeader,Status);
						insert = false;						
					}
					else{  
						System.out.println("iNSIDE ELSE");
						existQtmTlCapa=newCAPAService.create(newQtmTlCapa, existQtmTlCapa);
						//String CapaId=newQtmTlCapa.getQcapKeyid();
						String CapaId=existQtmTlCapa.getQcapKeyid();
						System.out.println("Inside CapaId"+CapaId);
						qtmTlCapaWorkflow.setQcwfCaparefid(CapaId);
						qtmTlCapaWorkflow.setQcwfRoleid("ROLM0013");
						qtmTlCapaWorkflow.setQcwfApproveby(TargetDmtLeader);
						
						qtmTlCapaWorkflow.setQcwfDate(newQtmTlCapa.getQcapDate());
						
						//QtmTlCapaWorkflow existQtmTlCapaWorkflow=(QtmTlCapaWorkflow)httpSession.getAttribute("NewCAPAServlet");
						qtmTlCapaWorkflow=newCAPAService.createWorkflow(qtmTlCapaWorkflow,CapaId);
					}
				}
					else if(Title.equals("CAPA")) {
						System.out.println("INside CAPA");
						String savemsg = "";
						String CapaGrid=request.getParameter("gridCapa");
						System.out.println("Capa Grid"+CapaGrid);
						
						//QtmTlCapaActionmst  qtmTlCapaActionmst=new QtmTlCapaActionmst();
						if(CapaGrid!=null){
							
							QtmTlCapaActionmst qtmTlCapaActionmst=new QtmTlCapaActionmst();
							
  							 String createdBy=user.getUsrm_ccno();
  							
  							 JSONArray CAPAGridList=null; 
  			   		    		List<QtmTlCapaActionmst> QtmTlCapaActionmst = null;	
  			   		    		if(UIUtils.isValidKeyId(CapaGrid)){
  			   		    		CAPAGridList=JSONArray.fromString(CapaGrid);
  			   		    		System.out.println("EmployeeList"+CapaGrid);
  			   		    	QtmTlCapaActionmst=(List<QtmTlCapaActionmst>)UIUtils.convertJSONArrToList(qtmTlCapaActionmst,CAPAGridList);	
  			   		    		System.out.println("QtmTlCapaActionmst ::::"+QtmTlCapaActionmst);
  			   		    		}
  			   		   	if(UIUtils.isValidKeyId(CapaGrid) && CapaKeyid==null)
 						 {  
  			   		 
  			   		   
  			   		   QtmTlCapaActionmst=newCAPAService.createCAPA(QtmTlCapaActionmst,createdBy,CapaMasterKeyid);	
  			   		savemsg = "Data Saved SuccessFully";
  			   		
 						 }
 						
 						 else if(CapaKeyid!=null ||UIUtils.isValidKeyId(CapaKeyid)){
 							 System.out.println("Inside the Create");
 							// existMocRfcmst=mocService.updateMOC(mocRfcmst,existMocRfcmst);
 							 insert = true;
 							  QtmTlCapaActionmst=newCAPAService.createCAPA(QtmTlCapaActionmst,createdBy,CapaMasterKeyid);	
  							  //savemsg= "Data Updated succesfully";
 					
 						 }
  			   		
  			   	
  			   		   	
			  		
						}
			  		
					}
			
					new JSONObject();			
					JSONObject persistentData = new JSONObject(); 
					if(Title=="WorkFlow"){
					persistentData.put("QcapkeyId",existQtmTlCapa.getQcapKeyid());
					}
					else if(Title=="CAPA"){
					persistentData.put("QcapkeyId",CapaMasterKeyid);
						
					}
					System.out.println("Flow Running"+Title);
					String msgPropertyIdnt;				
				 
					if( insert){
						msgPropertyIdnt = "success-save";
					}else
						msgPropertyIdnt = "success-update";
					if(Title.equals("WorkFlow")){
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt)
				    		+" -CAPA. No. "+existQtmTlCapa.getQcapKeyid());
					successData.put("QcapkeyId",newQtmTlCapa.getQcapKeyid());
					
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					returnData.put("QcapkeyId", CapaMasterKeyid);
					returnData.put("QcapkeyId",existQtmTlCapa.getQcapKeyid());
					
					httpSession.setAttribute( "NewCAPAServlet", existQtmTlCapa);					
					out.print(returnData.toString());
					out.close();	
					try{
						System.out.println("Inside   Approval Mail Trigger");
						String DmtLeader=request.getParameter("TargetDmtLeader");
				  		System.out.println("TargetDmtLeader"+TargetDmtLeader);
				  		String AreaManager=request.getParameter("TargetAreaManager");
				  		String RaisedBy=request.getParameter("ComplaintRaisedBy");
				  		String ComplaintNo=existQtmTlCapa.getQcapKeyid();
				  		String Description=request.getParameter("Description");
				  		String glbmsg="";
				  		List<String[]> empMailIds = newCAPAService.getCAPAEmpMailIds(ComplaintNo) ;
                        System.out.println("emailId>>>>>>>>>>>>>>>>>>>>>>"+empMailIds);
                        String mailIds = buildToMailIds(empMailIds);
                        if( mailIds.isEmpty())
            			{
            				JSONObject succssMsg= new JSONObject();
            				glbmsg="No Valid MailId Found ";				
            				return ;
            			}
                        System.out.println("mailIds.>>>>>......."+mailIds);
						SendMailToJHLeader(request,response,mailIds,ComplaintNo,Description);
					}
					catch(Exception e){
					   System.out.println("Mail"+e);
					}
					}
					
					else if(Title.equals("CAPA")){
						System.out.println("Inside Else Success Call Back");
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt)
					    		+" -CAPA. No. "+CapaMasterKeyid);
						successData.put("QcapkeyId",CapaMasterKeyid);
						
						returnData.put("successData", successData);
						returnData.put("formClear", false);
						returnData.put("QcapkeyId", CapaMasterKeyid);
						returnData.put("QcapkeyId",CapaMasterKeyid);
						
						httpSession.setAttribute( "NewCAPAServlet", existQtmTlCapa);					
						out.print(returnData.toString());
						out.close();	
						}
				
				}			
				catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"NewCAPAValidations");
					out.print(errMessage.toString());					
				}catch(Exception e){
					e.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}			
		    }	 
}
private void SendMailToJHLeader(HttpServletRequest request, HttpServletResponse response, String mailIds,
		String complaintNo, String description) throws Exception {
	 System.out.println("---------Sending Mail To DMT Leader for Complaint Approval----------");
	 System.out.println("description:" +description);
	 System.out.println("emailId :" +mailIds);
	 System.out.println("complaintNo :" +complaintNo);
	
		try {
				//System.out.println("E-Mail Id  :" +UIUtils.isValidEmail(emailId) + "  " + emailId);
				if(UIUtils.isValidEmail(mailIds)){	
					
					String disclaimerNote = "--";			
					StringBuilder subject = new StringBuilder("");
					subject.append(" Complaint Wating for Approval ");						
					StringBuilder totalContent = new StringBuilder();					
					totalContent.append("\r\n");
					totalContent.append("Complaint No: " +complaintNo).append("\r\n");
					totalContent.append("\r\n");
					totalContent.append("Complaint Description : " +description).append("\r\n");
					totalContent.append("\r\n");
				   
					totalContent.append(disclaimerNote);
					totalContent.append("\r\n");
					//totalContent.append("Regards,");
					totalContent.append("\r\n");
					totalContent.append("This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");							
					System.out.println(" Before sendLotusNotesMail to JH leader" );
					System.out.println("Suggestion:"+description+"CAPAId"+complaintNo);
					UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),null);
					System.out.println("Content :\n" +totalContent.toString());
					System.out.println(" After sendLotusNotesMail to jh leader" );
				}
			//}
			else{
				System.out.println(" Mail not send to Dmt leader for Complaint Approval no mail id" );
			}
		}
		catch( ValidationExceptions e){				
			System.out.println("ValidationExceptions from  sendMailToJhLeader()" + e);
		
		}catch (BusinessApplicationExceptions e) {
			System.out.println("BusinessApplicationExceptions from  sendMailToJhLeader()" + e);
		
		} /*
			 * catch(NotesException e) {
			 * System.out.println("NotesException from  sendMailToJhLeader()" + e); }
			 */catch(Exception e){
			
			System.out.println("Exception from  sendMailToJhLeader()" + e);
		} catch(Throwable e){
			
		}
		
		System.out.println("--------- Mail Send To Jh Leader for Suggestion Approvel----------");

	
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
	System.out.println("buildToMailIds"+mailIdsStr);
	return mailIdsStr.toString();
}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String string, boolean b) {
		// TODO Auto-generated method stub
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
}
