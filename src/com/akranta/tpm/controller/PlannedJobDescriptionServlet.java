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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.PlannedJobDescBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.SheTlPlannedjobobservation;
import com.akranta.tpm.model.SheTlPlnjobemplink;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.PlannedJobDescriptionService;
import com.akranta.tpm.service.impl.PlannedJobDescriptionServiceImpl;
import com.akranta.tpm.service.impl.KaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

public class PlannedJobDescriptionServlet extends HttpServlet {
	PlannedJobDescriptionService plannedJobDescriptionService;
	KaizenServices  kaizenServices ;
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlannedJobDescriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
	    String action = UIUtils.getActionPart(request);
	    AdmTlUsermst user = UIUtils.getLoginUser(request);
	    try {
	    	plannedJobDescriptionService= (PlannedJobDescriptionServiceImpl) UIUtils.getServiceObject(request,"PlannedJobDescriptionServiceImpl");
	    	plannedJobDescriptionService.PlannedJobDescriptionServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	    	kaizenServices= (KaizenServiceImpl) UIUtils.getServiceObject(request,"KaizenServiceImpl");

	    	if(action.equals("PlannedJobObservation_view.plnJobD"))
			{ 
	    	 String loginElementid = (String) httpSession.getAttribute("loginElementid");
			 String empId = null;
			AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
			empId =  usersdetails.getUsrm_ccno();			
			 String loginflid=CommonFunctions.getLoginFlid(request);
	  		 String loginlevel=CommonFunctions.getLoginLevel(request);
			List<String []> getUserLoginDtl= kaizenServices.getElementId(loginflid,loginlevel, loginElementid,empId);
			String elementid = getUserLoginDtl.get(0)[0];				
     		//String location=elementid.substring(11, 21);
     		 String location=null;
 			if(elementid.length()>10){
 				location=elementid.substring(11, 21);
 			System.out.println(location+" Locationid"); 
 			
  		}
			System.out.println(location+" Locationid"); 
			request.setAttribute("location", location);

				RequestDispatcher rd = request.getRequestDispatcher("/pages/PlannedJobDescGrid.jsp");					
				rd.forward(request, response);
			}else if(action.equals("PlannedJobObservation_getCol.plnJobD") ){   
	    		try {
					CommonFilter commonFilter = populateCommonFilter(request,"PlannedJobObserGridFilter",true);
						
					PrintWriter out = response.getWriter();
					httpSession.removeAttribute("PlannedJobObser");
					List<String []> getPlnJobList  = plannedJobDescriptionService.getmaingrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					String [] colHeader = getPlnJobList.get(1);			
					String [] colHeaderCond = getPlnJobList.get(0);
					
					gridColModel.setHeaderNum(1);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.set("tableWidth", "106%%");
					jsonObject.set("tableHeight", "75%%");
					httpSession.removeAttribute("PlannedJobObser");
					httpSession.setAttribute("PlannedJobObser", jsonObject);
					out.println(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
			}
			else if(action.equals("PlannedJobObservation_getData.plnJobD")){   
				CommonFilter commonFilter = populateCommonFilter(request,"PlannedJobObserGridFilter",false);
				List<String []> getmaingridList  = plannedJobDescriptionService.getmaingrid(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject getmaingriddata = UIUtils.convertToJqGridTableObject(getmaingridList,request,2,0,commonFilter.getTotalRecordCnt()+3); 
				out.println(getmaingriddata);
			}else if(action.equals("PlannedJobObservation_getExcel.plnJobD") ){
				CommonFilter commonFilter = populateCommonFilter(request,"PlannedJobObserGridFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);	
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("PlannedJobObser");
				colmodel.put("title","Planned Job Observation");
				String format = ExcelUtils.getFormat(request);
				Workbook wb = plannedJobDescriptionService.getPlJobMainExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				
				ExcelUtils.writeToResponse(response, wb, "PlannedJobObser", format);
				
			}else if( action.equals("getModePlannedJobObservation_view.plnJobD")){
				CommonFunctions.debugMsg("Action ::: Mode "+action);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				JSONObject result = new JSONObject();
				result.put("mode","create");
				result.put("url","PlannedJobObservationEntry_input.plnJobD");
				result.put("formHeader","Planned Job Observation");
				out.println(result);
			}
			else if(action.equals("PlannedJobObservationEntry_input.plnJobD"))
			{
			    String keyId = request.getParameter("Keyid");
			    SheTlPlannedjobobservation sheTlPlannedjobobservation = new SheTlPlannedjobobservation();
			    AdmTlUsermst user1 = UIUtils.getLoginUser(request);
			    HttpSession httpSession2 = request.getSession(false);

			    if(UIUtils.isValidKeyId(keyId)){
			        sheTlPlannedjobobservation = plannedJobDescriptionService.getSelectedData(keyId);

			        String date = sheTlPlannedjobobservation.getPjobDate();
			        sheTlPlannedjobobservation.setPjobDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));

			        String followUpDate = sheTlPlannedjobobservation.getPjobFollowUpDate();
			        sheTlPlannedjobobservation.setPjobFollowUpDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(followUpDate));

			        sheTlPlannedjobobservation.setPjobDate(UIUtils.getActualDateForm(sheTlPlannedjobobservation.getPjobDate()));
			        sheTlPlannedjobobservation.setPjobFollowUpDate(UIUtils.getActualDateForm(sheTlPlannedjobobservation.getPjobFollowUpDate()));
			        
			        if(sheTlPlannedjobobservation.getPjobFollowUpDate().contains(Constants.futureNullDate)){
			            sheTlPlannedjobobservation.setPjobFollowUpDate("");
			        }
			        
			        if(UIUtils.isValidKeyId(sheTlPlannedjobobservation.getPjobPracticecond())){
			            String[] practiceCond = (sheTlPlannedjobobservation.getPjobPracticecond()).split(",");
			            for(int i = 0; i < practiceCond.length; i++){
			                if(practiceCond[i].contains("PPE"))
			                    sheTlPlannedjobobservation.setPjobAppPPE("Y");
			                else if(practiceCond[i].contains("CKF"))
			                    sheTlPlannedjobobservation.setPjobChecklist("Y");
			                else if(practiceCond[i].contains("CKW"))
			                    sheTlPlannedjobobservation.setPjobCheckWorker("Y");
			                else if(practiceCond[i].contains("POW"))
			                    sheTlPlannedjobobservation.setPjobPositioning("Y");
			            }
			        }
			    } else {
			        // New record - set logged-in user as default employee
			        if(user != null && UIUtils.isValidKeyId(user.getUsrm_ccno())){
			            sheTlPlannedjobobservation.setPjobEmployeeid(user.getUsrm_ccno());
			            System.out.println("------Login EmpShow-------------->" + user.getUsrm_ccno());
			        }
			    }

			    httpSession.setAttribute("sheTlPlannedjobobservation", sheTlPlannedjobobservation);
			    request.setAttribute("sheTlPlannedjobobservation", sheTlPlannedjobobservation);
			    RequestDispatcher rd = request.getRequestDispatcher("/pages/PlannedJobDescription.jsp");
			    rd.forward(request, response);
			}
			
			else if(action.equals("PjobType.plnJobD")){
				try{
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlnJobDescProp","Type"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(action.equals("PjobNotification.plnJobD")){
				try{
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PlnJobDescProp","Notification"));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
	    	
			else if(action.equals("PJOPopupUpdate_input.plnJobD")){
				
				String empkeyid=request.getParameter("empkeyid");
				System.out.println("empKeyid Input:"+empkeyid);
				request.setAttribute("empkeyid",empkeyid);
				UIUtils.forwardRequest(request, response,"/pages/PlannedJobPopup.jsp");
			    }
	    	
			else if (action.equals("PJOPopupUpdate_getCol.plnJobD")) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				response.setContentType("text/json");
				CommonFilter commonFilter=new CommonFilter();
				String EmpKeyid=request.getParameter("EmpKeyid");
				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PlnJobDescProp", "PJOPopup");
				JSONObject colmodel = JSONObject.fromString(tableModel);
				httpSession.removeAttribute("PJOPopupmodel");
				httpSession.setAttribute("PJOPopupmodel", colmodel);
				out.println(colmodel);
				out.close();
			}
	    	
			else if (action.equals("PJOPopupUpdate_getData.plnJobD")){
				
				PrintWriter out = response.getWriter();
				GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
				httpSession.setAttribute("gridParams",gridParams);
				if (gridParams == null)
					gridParams = new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				CommonFilter commonFilter=new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				String EmpKeyid=request.getParameter("EmpKeyid");
				System.out.println("employeeId"+EmpKeyid);
				commonFilter.setActionKeyId(EmpKeyid);

				List<String[]>AbnClosureList;
				try {
					AbnClosureList=plannedJobDescriptionService.getPJOEmployeeData(gridParams,commonFilter);
					httpSession.removeAttribute("gridParams");			
					JSONObject AbnClosure=UIUtils.convertToJqGridTableObject(AbnClosureList,request, 2, 1,gridParams.getTotalRecordCnt());
					out.println(AbnClosure);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    
	    	
			else if(action.equals("PlannedJobObservationEntry_save.plnJobD")){
				savePjob(request,response,httpSession);
			}else if(action.equals("PlannedJobObservationEntry_delete.plnJobD")){
				deletePjob(request,response,httpSession);
			}else if (action.equals("PlannedJobObserEntryDep_recall.plnJobD")) {
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter= new CommonFilter();
				String EmpId = request.getParameter("EmpId");
				String [] empIdsArr=EmpId.split(",");
				String Empid="";
				for(int i=0;i<empIdsArr.length;i++){
					Empid+="'"+empIdsArr[i]+"',";
				}
				
				Empid=Empid.substring(0, Empid.length()-1);
				commonFilter.setEmpch(Empid);
				CommonFunctions.debugMsg("in servlet  "+commonFilter.getEmpch());
				List<String []> depRecal  = plannedJobDescriptionService.selectData( commonFilter);
				CommonFunctions.debugMsg("in servlet  "+depRecal.get(0));
				out.print( JSONArray.fromCollection(depRecal));				
			}else if(action.equals("functionalLoc.plnJobD"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("factory");
				functLocFieldNameBean.setSection("section");
				functLocFieldNameBean.setCell("cell");
				functLocFieldNameBean.setMachine("machine");
				functLocFieldNameBean.setFunctionalLocId("cmbPjobFlid");
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
			    FormModes formModes = FormModes.create;
		        UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			}
			else if( action.equals("PlannedJobObser_ExcelView.plnJobD") )/*Created By Sugunadevi.S*/ 
			{
		
				try{
					CommonFilter commonFilter = new CommonFilter();					
					String keyid = request.getParameter("keyid");
					System.out.println("keyid::::::"+keyid);
					String format = ExcelUtils.getFormat(request);
					commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter = FilterValues.getSafty(request, commonFilter);
					
					String path = UIUtils.getExcelTemplatePath(request);  	
					String imagePath = UIUtils.getImagePath(request);
					format = "xlsx";
					Workbook wb = plannedJobDescriptionService.plannedJobExcel(keyid,format,path,imagePath,commonFilter);
					
		 
					ExcelUtils.writeToResponse(response, wb, "PlannedJobObs_"+keyid, format);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();		
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}
			}	
	    	
			 else if(action.equals("pojEmployeeLink_getCol.plnJobD")){
				 CommonFunctions.debugMsg("pojEmployeeLink_getCol.plnJobD" );
				 String pjobKeyId=request.getParameter("pjobKeyid");
				 CommonFilter commonFilter=populateCommonFilter(request, "emp", true);
				 try {
						
						List<String[]> employeeList = plannedJobDescriptionService.getPojEmployee(pjobKeyId);
						JSONObject jsonObject = getEmployeeTableModel(employeeList);
						System.out.println("Table model in pojEmployeeLink_getCol.plnJobD");
						System.out.println("jsonObject "+jsonObject);
						PrintWriter  out = response.getWriter();
						out.println(jsonObject);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			
			 else if(action.equals("pojEmployeeLink_getData.plnJobD")){
				 CommonFunctions.debugMsg("pojEmployeeLink_getData.plnJobD-------------->" );
				 String  pjobKeyId=request.getParameter("pjobKeyid");
				 CommonFilter commonFilter=populateCommonFilter(request, "emp", false);
				 PrintWriter out = response.getWriter();
				 List<String[]> employeeList =  plannedJobDescriptionService.getPojEmployee(pjobKeyId);
					
				 JSONObject employeedata = UIUtils.convertToJqGridTableObject( employeeList, request, 2, 0);
				  out.println(employeedata);
			 }
			 else if(action.equals("deleteEmployee.plnJobD")){
			        deleteDetail(request,response);			
			 }

			 else if(action.equals("POJPopup_update.plnJobD")){
				 SheTlPlannedjobobservation newsheTlPlannedjobobservation=new SheTlPlannedjobobservation();
				 String pojKeyid=request.getParameter("pjoKeyid");
				 String aplmKeyid=request.getParameter("Aplmkeyid");
				 newsheTlPlannedjobobservation=plannedJobDescriptionService.UpdateActionPlanId(pojKeyid,aplmKeyid);
			 }

    	}catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
		}
	}

	private void deleteDetail(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
    	PrintWriter out = response.getWriter();
    	 String pjelPjobEmployeeid = request.getParameter("pjelPjobEmployeeid");
		 String gridId = request.getParameter("gridId");
		 //String topicId = request.getParameter("progId");
		try{
		 String delSuc = plannedJobDescriptionService.deleteDetail(pjelPjobEmployeeid,gridId);
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
	
	
	private void deletePjob(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession) throws Exception {
		PrintWriter out = response.getWriter();
		SheTlPlannedjobobservation sheTlPlannedjobobservation = new SheTlPlannedjobobservation();
		SheTlPlannedjobobservation existSheTlPlannedjobobservation = new SheTlPlannedjobobservation();
		sheTlPlannedjobobservation=(SheTlPlannedjobobservation)UIUtils.setBeanProperties((Object)sheTlPlannedjobobservation,request);
		existSheTlPlannedjobobservation = plannedJobDescriptionService.delete(sheTlPlannedjobobservation);
    	JSONObject successData = new JSONObject();
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		JSONObject returnData = new JSONObject();
		returnData.put("formClear",true);
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}

	private JSONObject getEmployeeTableModel(List<String[]> employeeList) {
		// TODO Auto-generated method stub
    	JqGridTableModel jqGridTableModel = new JqGridTableModel(); 
		
		String[] colHeader = employeeList.get(0);		
		 
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
			jqGridColModel.setWidth(250);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);	 
			
			if (i <=2) {
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("left");
			}
			CommonFunctions.debugMsg("Length--->: " +(colHeader.length-1) + "  "+ colHeader[colHeader.length-1] ) ;
			
			
						
			if(i==colHeader.length-1){
				jqGridColModel.setFormatter("BtnFormatterDelete");
				jqGridColModel.setWidth(70);
				jqGridColModel.setAlign("center");
			} 
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "40%%");
		tableModel.set("tableWidth", "35%%");
		return tableModel;

	}
 
	/*
	 * private void savePjob(HttpServletRequest request, HttpServletResponse
	 * response,HttpSession httpSession) throws
	 * IOException,ValidationExceptions,BusinessApplicationExceptions { PrintWriter
	 * out= response.getWriter(); AdmTlUsermst user = UIUtils.getLoginUser(request);
	 * String empIds=request.getParameter("empIds"); String
	 * openactnpln=request.getParameter("openactnpln"); if( httpSession != null &&
	 * user != null) { PlannedJobDescBean plannedJobDescBean= new
	 * PlannedJobDescBean(); SheTlPlannedjobobservation
	 * newSheTlPlannedjobobservation = new SheTlPlannedjobobservation ();
	 * SheTlPlannedjobobservation existSheTlPlannedjobobservation =
	 * (SheTlPlannedjobobservation)
	 * httpSession.getAttribute("PlannedJobObservation"); plannedJobDescBean
	 * =(PlannedJobDescBean)
	 * UIUtils.setBeanProperties((Object)plannedJobDescBean,request); Boolean
	 * clrVal=true; List<SheTlPlnjobemplink> sheTlPlnjobemplink =new
	 * ArrayList<SheTlPlnjobemplink>(); System.out.println("empId"+empIds);
	 * sheTlPlnjobemplink=fillEmpLink(empIds, request);
	 * CommonFunctions.debugMsg("empIds " + empIds);
	 * 
	 * 
	 * try{ newSheTlPlannedjobobservation=(SheTlPlannedjobobservation)UIUtils.
	 * setBeanProperties((Object)newSheTlPlannedjobobservation,request);
	 * newSheTlPlannedjobobservation.setPjobCreatedby(user.getUsrm_ccno());
	 * newSheTlPlannedjobobservation.setPjobEmployeeid(empIds);
	 * 
	 * String mainEmpId = null;
	 * 
	 * if (empIds != null && !empIds.trim().isEmpty()) { for (String id :
	 * empIds.split(",")) { if (id != null && !id.trim().isEmpty() &&
	 * !"undefined".equalsIgnoreCase(id)) { mainEmpId = id.trim(); break; } } }
	 * 
	 * CommonFunctions.debugMsg("empIds "+empIds);
	 * CommonFunctions.debugMsg("empIds "+sheTlPlnjobemplink.get(0).
	 * getPjelPjobEmployeeid());
	 * CommonFunctions.debugMsg("empIds "+sheTlPlnjobemplink.size());
	 * if(sheTlPlnjobemplink!=null)
	 * newSheTlPlannedjobobservation.setSheTlPlnjobemplink(sheTlPlnjobemplink);
	 * String saveMsg ; if(
	 * !UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobKeyid())) {
	 * existSheTlPlannedjobobservation =
	 * plannedJobDescriptionService.create(newSheTlPlannedjobobservation,
	 * existSheTlPlannedjobobservation,plannedJobDescBean); saveMsg =
	 * UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",
	 * "success-save"); } else{ existSheTlPlannedjobobservation =
	 * plannedJobDescriptionService.update(newSheTlPlannedjobobservation,
	 * existSheTlPlannedjobobservation,plannedJobDescBean); saveMsg =
	 * UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",
	 * "success-update"); clrVal=false; }
	 * 
	 * JSONObject returnData = new JSONObject(); JSONObject successData = new
	 * JSONObject(); successData.put("msg",
	 * saveMsg+"-PJO NO."+existSheTlPlannedjobobservation.getPjobKeyid());
	 * successData.put("Keyid",existSheTlPlannedjobobservation.getPjobKeyid());
	 * successData.put("flid",existSheTlPlannedjobobservation.getPjobFlid());
	 * returnData.put("successData",successData); returnData.put("formClear",false);
	 * if(UIUtils.isValidKeyId(openactnpln)){ successData.put("openactnpln",true);
	 * successData.put("flid",newSheTlPlannedjobobservation.getPjobFlid()); } else{
	 * successData.put("openactnpln", false); } out.print(returnData.toString());
	 * }catch (BusinessApplicationExceptions e){ JSONObject errMessage =
	 * UIUtils.businessValidationExceptions(e.toString(), "Plannedjobobservation");
	 * out.print(errMessage.toString()); CommonFunctions.debugMsg(" e " + errMessage
	 * ); }catch (ValidationExceptions e) {
	 * CommonFunctions.debugMsg("ValidationExceptions"); net.sf.json.JSONObject
	 * errMessage = UIUtils.validationExceptions(e.toString(),
	 * "Plannedjobobservation"); out.print(errMessage.toString()); } catch(Exception
	 * e) { e.printStackTrace(); JSONObject err = new JSONObject();
	 * err.put("tpmException", "Data Not Saved"); out.print(err.toString()); } } }
	 */
	private void savePjob(HttpServletRequest request,
	        HttpServletResponse response, HttpSession httpSession) throws IOException, ValidationExceptions, BusinessApplicationExceptions {
	    PrintWriter out = response.getWriter();
	    AdmTlUsermst user = UIUtils.getLoginUser(request);
	    String empIds = request.getParameter("empIds");
	    String openactnpln = request.getParameter("openactnpln");

	    if (httpSession != null && user != null) {
	        PlannedJobDescBean plannedJobDescBean = new PlannedJobDescBean();
	        SheTlPlannedjobobservation newSheTlPlannedjobobservation = new SheTlPlannedjobobservation();
	        SheTlPlannedjobobservation existSheTlPlannedjobobservation = (SheTlPlannedjobobservation) httpSession.getAttribute("PlannedJobObservation");
	        plannedJobDescBean = (PlannedJobDescBean) UIUtils.setBeanProperties((Object) plannedJobDescBean, request);
	        Boolean clrVal = true;

	        List<SheTlPlnjobemplink> sheTlPlnjobemplink = new ArrayList<SheTlPlnjobemplink>();
	        System.out.println("empId" + empIds);

	        // ✅ FIX 1: Guard against null empIds before calling fillEmpLink
	        if (empIds != null && !empIds.trim().isEmpty()) {
	            sheTlPlnjobemplink = fillEmpLink(empIds, request);
	        }

	        CommonFunctions.debugMsg("empIds " + empIds);

	        // ✅ FIX 2: Guard get(0) — only log if list is non-empty
	        if (sheTlPlnjobemplink != null && !sheTlPlnjobemplink.isEmpty()) {
	            CommonFunctions.debugMsg("First empId: " + sheTlPlnjobemplink.get(0).getPjelPjobEmployeeid());
	            CommonFunctions.debugMsg("empLink size: " + sheTlPlnjobemplink.size());
	        } else {
	            CommonFunctions.debugMsg("No valid employee links after fillEmpLink for empIds: " + empIds);
	        }

	        try {
	            newSheTlPlannedjobobservation = (SheTlPlannedjobobservation) UIUtils.setBeanProperties((Object) newSheTlPlannedjobobservation, request);
	            newSheTlPlannedjobobservation.setPjobCreatedby(user.getUsrm_ccno());
	            newSheTlPlannedjobobservation.setPjobEmployeeid(empIds);

	            // ✅ FIX 3: Extract mainEmpId safely (was unused before, kept for future use)
	            String mainEmpId = null;
	            if (empIds != null && !empIds.trim().isEmpty()) {
	                for (String id : empIds.split(",")) {
	                    if (id != null && !id.trim().isEmpty()
	                            && !"undefined".equalsIgnoreCase(id.trim())
	                            && !"null".equalsIgnoreCase(id.trim())) {
	                        mainEmpId = id.trim();
	                        break;
	                    }
	                }
	            }
	            CommonFunctions.debugMsg("mainEmpId resolved: " + mainEmpId);

	            // ✅ FIX 4: Only set emp link if list is non-empty
	            if (sheTlPlnjobemplink != null && !sheTlPlnjobemplink.isEmpty()) {
	                newSheTlPlannedjobobservation.setSheTlPlnjobemplink(sheTlPlnjobemplink);
	            }

	            String saveMsg;
	            if (!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobKeyid())) {
	                existSheTlPlannedjobobservation = plannedJobDescriptionService.create(newSheTlPlannedjobobservation, existSheTlPlannedjobobservation, plannedJobDescBean);
	                saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
	            } else {
	                existSheTlPlannedjobobservation = plannedJobDescriptionService.update(newSheTlPlannedjobobservation, existSheTlPlannedjobobservation, plannedJobDescBean);
	                saveMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-update");
	                clrVal = false;
	            }

	            JSONObject returnData = new JSONObject();
	            JSONObject successData = new JSONObject();
	            successData.put("msg", saveMsg + "-PJO NO." + existSheTlPlannedjobobservation.getPjobKeyid());
	            successData.put("Keyid", existSheTlPlannedjobobservation.getPjobKeyid());
	            successData.put("flid", existSheTlPlannedjobobservation.getPjobFlid());
	            returnData.put("successData", successData);
	            returnData.put("formClear", false);

	            if (UIUtils.isValidKeyId(openactnpln)) {
	                successData.put("openactnpln", true);
	                successData.put("flid", newSheTlPlannedjobobservation.getPjobFlid());
	            } else {
	                successData.put("openactnpln", false);
	            }
	            out.print(returnData.toString());

	        } catch (BusinessApplicationExceptions e) {
	            JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "Plannedjobobservation");
	            out.print(errMessage.toString());
	            CommonFunctions.debugMsg(" e " + errMessage);
	        } catch (ValidationExceptions e) {
	            CommonFunctions.debugMsg("ValidationExceptions");
	            net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "Plannedjobobservation");
	            out.print(errMessage.toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	            JSONObject err = new JSONObject();
	            err.put("tpmException", "Data Not Saved");
	            out.print(err.toString());
	        }
	    }
	}
	
	private List<SheTlPlnjobemplink> fillEmpLink(String empIds,HttpServletRequest request) {
		List<SheTlPlnjobemplink> sheTlPlnjobemplinkList=new ArrayList<SheTlPlnjobemplink>();
		
		String [] empIdsArry=null;
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String dateTime = CommonFunctions.dateTimeNow();
		if(empIds.length()>0){
			empIdsArry=empIds.split(",");
			for(int i=0;i<empIdsArry.length;i++){
				
				// **ADD THIS VALIDATION**
	            if(empIdsArry[i] == null || empIdsArry[i].trim().isEmpty() || 
	               empIdsArry[i].trim().equalsIgnoreCase("undefined") || 
	               empIdsArry[i].trim().equalsIgnoreCase("null")) {
	                continue; // Skip invalid employee IDs
	            }
				
				SheTlPlnjobemplink sheTlPlnjobemplink = new SheTlPlnjobemplink();
				sheTlPlnjobemplink.setPjelPjobEmployeeid(empIdsArry[i]);
				CommonFunctions.debugMsg(empIdsArry[i]+""+sheTlPlnjobemplink.getPjelPjobEmployeeid());
				sheTlPlnjobemplink.setPjelActive("Y");
				sheTlPlnjobemplink.setPjelTempfield1("-");
				sheTlPlnjobemplink.setPjelTempfield2("-");
				sheTlPlnjobemplink.setPjelTempfield3("-");
				sheTlPlnjobemplink.setPjelTempfield4("-");
				sheTlPlnjobemplink.setPjelCreatedby(user.getUsrm_ccno());
				sheTlPlnjobemplink.setPjelModifiedon(dateTime);
				sheTlPlnjobemplink.setPjelCreatedon(dateTime);
				sheTlPlnjobemplinkList.add(sheTlPlnjobemplink);
				
			}
		}
		return sheTlPlnjobemplinkList;
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
  			commonFilter = 	FilterValues.getSafty(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		
  		return commonFilter;
  	}
}
