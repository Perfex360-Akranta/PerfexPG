package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.SheTlPlantSafetyBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;
import com.akranta.tpm.service.impl.ActionPlanRptServiceImpl;
import com.akranta.tpm.service.impl.EHSPlantSafetyServiceImpl;
import com.akranta.tpm.service.impl.UserServiceImpl;
import com.akranta.tpm.service.ActionPlanRptService;
import com.akranta.tpm.service.EHSPlantSafetyService;
import com.akranta.tpm.service.UserServices;
import com.akranta.tpm.service.api.SheTlPlantSafetyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class PlantSafetyInspectionServlet
 */

public class PlantSafetyInspectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EHSPlantSafetyService ehsPlantSafetyService;;
	private ActionPlanRptService actionPlanRptService;
	UserServices userServices;
	AdmTlUsermst  uservalues;
	SheTlPlantSafetyServiceApi sheTlPlantSafetyServiceApi;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlantSafetyInspectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
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
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		String action = UIUtils.getActionPart(request);
		String location=UIUtils.getlocation(request);
		
		 AdmTlUsermst user=UIUtils.getLoginUser(request);
		 uservalues=user;
		 HttpSession httpSession=request.getSession(false);
		 
  		 String loginflid=CommonFunctions.getLoginFlid(request);
  		 String loginlevel=CommonFunctions.getLoginLevel(request);
  		 String loginElementid = (String) httpSession.getAttribute("loginElementid");

		   System.out.println("action:::"+action);
		   try{
			 //  workOrderService = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl"); 
			   ehsPlantSafetyService= (EHSPlantSafetyServiceImpl) UIUtils.getServiceObject(request, "EHSPlantSafetyServiceImpl");
			   userServices = (UserServiceImpl) UIUtils.getServiceObject(request,"UserServiceImpl");
	  			actionPlanRptService = (ActionPlanRptServiceImpl) UIUtils.getServiceObject(request, "ActionPlanRptServiceImpl");
	  			ehsPlantSafetyService.EHSPlantSafetyServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	  			actionPlanRptService.ActionPlanRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	  			userServices.UserServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	  			
	  			
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   String empId = null;
				AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
			empId =  usersdetails.getUsrm_ccno();
			AdmTlUserRoleLink newAdmTlUserRoleLink = new AdmTlUserRoleLink();
			newAdmTlUserRoleLink.setArulCreatedby(user.getUsrm_ccno());
			String newRole=newAdmTlUserRoleLink.getArulRoleid();
			ComboFilter currentFilter =null;
			List<String []> getUserLoginDtl= ehsPlantSafetyService.getElementId(loginflid,loginlevel, loginElementid,empId);
			String elementid = getUserLoginDtl.get(0)[0];
			String fnln = getUserLoginDtl.get(0)[1];
			String level = getUserLoginDtl.get(0)[2];
			String rolename=getUserLoginDtl.get(0)[3];
			String rolekeyid=getUserLoginDtl.get(0)[4];
			System.out.println("rolename:"+rolename);
		   if(action.equals("safetyinspection_input.safetyinspect"))
		   {
			    String keyid =request.getParameter("keyid");
				System.out.println("KEYID in Main: " +keyid);
			  	String frmtype=request.getParameter("TYPE");
			  	System.out.println("Type in Main: " +frmtype);
			  	String selflid = request.getParameter("auditflid");
			  	System.out.println("Type in auditflid: " +selflid);
			  	String mode=request.getParameter("mode");
			  	System.out.println("mode in Modify: " +mode);
				String audittype =request.getParameter("audittype");
				System.out.println("audittype in Modify: " +audittype);
				String seldate =request.getParameter("auditdate");
				System.out.println("audittype in Modify: " +seldate);
				String seltype =request.getParameter("TYPE");
				System.out.println("audittype in Modify: " +seldate);
				String filterButton = request.getParameter("filterButton");
				if(filterButton == null) filterButton = "";
				request.setAttribute("filterButton", filterButton);
				
				
				if(rolename.equals("DMT MEMBER"))
			    {
			    	request.setAttribute("rolename", rolename);
			    	request.setAttribute("userid", empId);
			    }else
			    {
			    	request.setAttribute("rolename",rolename);
			    	request.setAttribute("userid", empId);
			    }
				SheTlPlantSafetymst sheTlPlantSafetymst= new SheTlPlantSafetymst(); 	
				SheTlPlantSafetydtl sheTlPlantSafetydtl= new SheTlPlantSafetydtl();
				//SheTlPlantSafetydtl sheTlPlantSafetydtlnew= new SheTlPlantSafetydtl();
				
				if(UIUtils.isValidKeyId(keyid)){
					System.out.println("KEYID inside Modify: " +keyid);
					
					String auditflid = request.getParameter("auditflid");
					String auditdate = request.getParameter("auditdate");
					String type = request.getParameter("TYPE");
					String modtype = request.getParameter("audittype");
					System.out.println("Detail when Save-Navigate:##" +"mode:"+mode+"auditflid:"+auditflid+" auditdate:"+auditdate+" type:"+type);
				/*	List<String[]> check = ehsPlantSafetyService.getCheck(keyid,auditflid,auditdate,type);
					System.out.println("1.value:"+check.get(0)[0]);
					String count = check.get(0)[0];
					*/
					
					sheTlPlantSafetymst = ehsPlantSafetyService.getvaluesmst(keyid);
					String date2 = sheTlPlantSafetymst.getSpsmDate();
					sheTlPlantSafetymst.setSpsmDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date2));
				String	mstcount=ehsPlantSafetyService.getdetailvalue(keyid);
				String date = sheTlPlantSafetymst.getSpsmDate();
				sheTlPlantSafetymst.setSpsmDate(CommonFunctions.pg_getDateTimeFromDate(date));
				if(mstcount!=null)
				{
					sheTlPlantSafetydtl = ehsPlantSafetyService.getvaluesdtl(keyid);
					String date1 = sheTlPlantSafetymst.getSpsmDate();
					sheTlPlantSafetymst.setSpsmDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
				}
					 sheTlPlantSafetymst.setSpsmDate(UIUtils.getActualDateForm(sheTlPlantSafetymst.getSpsmDate()));
					 System.out.println("This is for modify doneby::"+sheTlPlantSafetymst.getSpsmDoneby());
					httpSession.setAttribute("sheTlPlantSafetymst", sheTlPlantSafetymst);
					request.setAttribute("sheTlPlantSafetymst",sheTlPlantSafetymst);
					
					httpSession.setAttribute("sheTlPlantSafetydtl", sheTlPlantSafetydtl);
					request.setAttribute("sheTlPlantSafetydtl",sheTlPlantSafetydtl);
					
					request.setAttribute("mode", mode);
					request.setAttribute("audittype",type);
					request.setAttribute("auditflid",auditflid);
					request.setAttribute("modtype",modtype);
					request.setAttribute("frmtype", frmtype);
				
				}else
				{   
					String loginuserid = UIUtils.getLoginUser(request).getUsrm_ccno();
					sheTlPlantSafetymst.setSpsmCreatedby(loginuserid);
					request.setAttribute("plansafemastervalue",sheTlPlantSafetymst);
					
					sheTlPlantSafetydtl.setSpsdCreatedby(loginuserid);
					request.setAttribute("plansafedetailvalue",sheTlPlantSafetydtl);
					request.setAttribute("mode", mode);
					request.setAttribute("frmtype", frmtype);
					//request.setAttribute("seldate", seldate);
				}
				String date=CommonFunctions.dateTimeNow();
				String substrdate=date.substring(0,11);
				System.out.println("substrdate"+substrdate);
				request.setAttribute("currdate" ,substrdate); 
				request.setAttribute("seltype", seltype);
				request.setAttribute("seldate", seldate);
				request.setAttribute("selflid", selflid);
				request.setAttribute("formmodes", mode);
			
				//request.setAttribute("frmtype", frmtype);
				request.setAttribute("audittype",audittype);
				UIUtils.forwardRequest(request, response,"pages/PlantSafety/EHSSafetyInspection.jsp");
				
	        }
		   if(action.equals("safetyPopup_input.safetyinspect"))
		   {
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/SheTlPalntPopUpResponsibility.jsp");
				rd.forward(request, response); 
		   }
		   else if(action.equals("PlantsafetyExist_select.safetyinspect"))
		  	{
			   
				PrintWriter out=response.getWriter();
				 String combotype=request.getParameter("combotype");
				 String flId=request.getParameter("flId");
				 String datevalue=request.getParameter("datevalue");
				 System.out.println("combotype:::"+combotype);
				 System.out.println("flId:::"+flId);
				 System.out.println("datevalue:::"+datevalue);
					List<String[]> exitsdata=ehsPlantSafetyService.ExitsAuditData(combotype,flId,datevalue);
					JSONObject returndata = new JSONObject();
					JSONObject successData = new JSONObject();
					successData.put("datapresent", exitsdata);
					successData.put("flId", flId);
					successData.put("datevalue", datevalue);
					successData.put("combotype", combotype);
					returndata.put("successData",successData);
					out.println(returndata);
		  	}

		   else if(action.equals("safetyPopup_getCol.safetyinspect"))
		   {
			   PrintWriter out = response.getWriter();
			   JSONObject jsonObject = new JSONObject();
			   String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.SafetyInspection", "empall");
				jsonObject.set("multiSelect", true);
				httpSession.removeAttribute("colModelLevel");
				httpSession.setAttribute("colModelLevel",colModel);
				out.println(colModel);	
		   }
		   else if(action.equals("safetyPopup_getData.safetyinspect"))
		   {
			    PrintWriter out = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				
				CommonFilter commonFilter = populateCommonFilter(request,"ehsAuditParamter",true);
				List< String[]> safetyPopup  = ehsPlantSafetyService.safetyPopup(commonFilter);			
				jsonObject.set("multiSelect", true);
				jsonObject = UIUtils.convertToJqGridTableObject(safetyPopup,request,0,0);
				
				commonFilter.setViewClick('N');	 
				httpSession.removeAttribute("colModelsafetyPopup");
				out.println(jsonObject);
		   }
		   else if(action.equals("safetyPopupall_input.safetyinspect"))
		   {
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/SheTlPalntPopUpResponsibility.jsp");
				rd.forward(request, response); 
		   }
		   else if(action.equals("safetyPopupall_getCol.safetyinspect"))
		   {
			   PrintWriter out = response.getWriter();
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.SafetyInspection", "selectempall");
				httpSession.removeAttribute("colModelLevel");
				httpSession.setAttribute("colModelLevel",colModel);
				out.println(colModel);	
		   }
		   else  if(action.equals("safetyPopupall_getData.safetyinspect"))
		   {
			   
		   }
		   else if( action.equals("functionalLocDM.safetyinspect"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
				//functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
		   else if( action.equals("functionalLocEM.safetyinspect"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
				functLocFieldNameBean.setLocnMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
			else if( action.equals("functionalLocUH.safetyinspect"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
				//functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setLocnMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
		   else if( action.equals("safetyinspection_getCol.safetyinspect") )
			{
			/*   		PrintWriter out = response.getWriter();
			   		CommonFilter commonFilter = new CommonFilter();
					JSONObject jsonObject = new JSONObject();
					List<String[]> projectListGrid = null;		
					try {						
						FilterValues.getCommonFilters(request, commonFilter);
						projectListGrid =  ehsPlantSafetyService.getSafetygridDetail(commonFilter);
					} catch (Exception e) {
						CommonFunctions.debugMsg(e.getMessage());
						System.out.println(e.getMessage());
					}			 
					 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					 GridColModel gridColModel = new GridColModel();			
					 gridColModel.setHeaderNum(1);
					 
					 jqGridTableModel.setSortable(false);			 
					 jqGridTableModel.setTableButton(false);
					 jqGridTableModel.setEnableFilter(true);
					 jqGridTableModel.setRowNumbers(true);	
					 jqGridTableModel.setGridEdit(true);
					 
					 String [] colHeaderHead = projectListGrid.get(0);
					 String [] colHeader = projectListGrid.get(1);
					 List<String[]> headers = new ArrayList<String[]>();	
					 headers.add(colHeader);
					 
					 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
			   	     jsonObject.set("tableWidth", "104%%");
			     	 jsonObject.set("tableHeight", "49%%");
			   	     httpSession.removeAttribute("plantDtlsColModel");
					 httpSession.setAttribute("plantDtlsColModel",jsonObject);	
					 out.println(jsonObject);*/
			try{
					PrintWriter out = response.getWriter();
					//CommonFunctions.debugMsg("RiskAssesment_getCol.risk.............");
					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.SafetyInspection","SafetyDetail"));
					//CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DailyAudit","AuditDetail")); 
					CommonFunctions.debugMsg("Management Entry coldetails end"); 
				}catch(Exception e){
					//CommonFunctions.debugMsg("RiskAssesment_getCol.risk Exception" + e.getMessage());
					e.printStackTrace(); 
				} 			 
			   
			}
		   else if( action.equals("safetyinspection_getData.safetyinspect") )
			{
				
			   try {
					//CommonFunctions.debugMsg("RiskAssesmentEntry_getData.risk");
					CommonFilter commonFilter = new CommonFilter();
					String keyid =request.getParameter("keyid");
					String auditflid =request.getParameter("auditflid");
					String auditdate =request.getParameter("auditdate"); 
					String frmtype=request.getParameter("TYPE");
				  	String mode=request.getParameter("mode");
				  	System.out.println("all param 1:<->: "+"keyid :"+keyid+" auditflid:"+auditflid);
				  	System.out.println("all param 2:<->: "+"auditdate :"+auditdate+" frmtype:"+frmtype);
				  	System.out.println("mode :"+mode);
					CommonFunctions.debugMsg("keyid"+keyid);
					commonFilter.setKey(keyid);
					commonFilter.setFlid(auditflid);
					commonFilter.setAuditFromDate(auditdate);
					commonFilter.setType(frmtype);
					commonFilter.setSafetyMode(mode);
					PrintWriter out = response.getWriter();
					List<String[]> RskAssActList = ehsPlantSafetyService.getSafetygridDetail(commonFilter);
					JSONObject RskAssActData = UIUtils.convertToJqGridTableObject(RskAssActList, request, 2, 0);
					out.println(RskAssActData);
				} catch (Exception e) {
					CommonFunctions.debugMsg("PLANTSAFETY Exception" + e.getMessage());
					e.printStackTrace();
				}
			  /* try {
					CommonFilter commonFilter = populateCommonFilter(request,"plantDtlsCommonFilter",true);
					String keyid =request.getParameter("keyid");
					System.out.println(keyid);
					commonFilter.setKey(keyid);
					FilterValues.getCommonFilters(request, commonFilter);
					List<String[]> MasterGrid = ehsPlantSafetyService.getSafetygridDetail(commonFilter);
					PrintWriter out = response.getWriter();
					JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
					out.println(ResourceGridmod);
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
				} */
			}	
		   else if(action.equals("safetyinspection_update.safetyinspect"))
		   {
			   PrintWriter out = response.getWriter();
			   JSONObject returnData = new JSONObject();
		    	JSONObject successData = new JSONObject(); 
			   String msg =null;
			   String dtlid = request.getParameter("dtlid");
			   String Resid = request.getParameter("Resid");
			   String mode = request.getParameter("mode");
			   String targetdate = request.getParameter("targetdate");
			   
			 
			  try{
			   List< String[]> resupdate  = ehsPlantSafetyService.resupdate(dtlid,Resid,targetdate);	
			   msg = "Responsibility and TargetDate Assigned..!";
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  
			   	successData.put("msg", msg);
				returnData.put("successData",successData);
				out.print(returnData.toString());
				out.close();
		   }
		   else if(action.equals("safetyinspection_verify.safetyinspect"))
		   {
			   PrintWriter out = response.getWriter();
			   JSONObject returnData = new JSONObject();
		    	JSONObject successData = new JSONObject(); 
			   String msg =null;
			   String dtlid = request.getParameter("dtlid");
			   String Verid = request.getParameter("Verid");
			   String mode = request.getParameter("mode");
			   String verifydate = request.getParameter("verifydate");
			   
			 
			  try{
			   List< String[]> resupdate  = ehsPlantSafetyService.verifyupdate(dtlid,Verid,verifydate);	
			   msg = "Responsibility and TargetDate Assigned..!";
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  
			   	successData.put("msg", msg);
				returnData.put("successData",successData);
				out.print(returnData.toString());
				out.close();
		   }
		   
			 else if(action.equals("InspectionType.safetyinspect")){
					
				 System.out.println("In type get Col");
				 PrintWriter out = response.getWriter(); 
				 out .println(UIUtils.getPropertyValue("com.akranta.tpm.resources.SafetyInspection", "PsiType"));

			   }
		   
		   else if(action.equals("safetyinspection_save.safetyinspect")){
			   SheTlPlantSafetyBean sheTlPlantSafetyBean = new SheTlPlantSafetyBean();
			   saveSafetyDetail(request,response,sheTlPlantSafetyBean);		    
			}
		   else if(action.equals("plantsafetypopup_save.safetyinspect"))
	  	   {
	  		 SaveResponsibilty(request,response);
	  	   }
		   else if(action.equals("plantsafetyActionClosure_save.safetyinspect"))
		   {
			 SaveActionClosure(request,response);
		   }
		   else if(action.equals("PlantsafetyVerify_save.safetyinspect"))
		   {
			 SaveActionVerification(request,response);
		   }
		   else if(action.equals("SafetymainDelete_delete.safetyinspect"))
		  	{
 		        SafetyMainDeleteGrid(request,response);
		  	}
		   else if (action.equals("AuditDetailsgridpopup_input.safetyinspect")) {	
			   String Detailkeyid=request.getParameter("U");
			   System.out.println("Keyid::"+Detailkeyid);
			   String modiefiedid=Detailkeyid.replaceAll("\"", "");
			   System.out.println("Detailkeyid::::"+modiefiedid);
			   String res=ehsPlantSafetyService.getResponse(modiefiedid);
			   String obse=ehsPlantSafetyService.getObser(modiefiedid);
			   String Masid=ehsPlantSafetyService.getMaster(modiefiedid.replace(" ",""));
					   
			   String resporemarks = ehsPlantSafetyService.Response(modiefiedid);
				   String flid=request.getParameter("fl");
				   String aplmaster=request.getParameter("m");
				   String apldetail=request.getParameter("d");
				   String observe=request.getParameter("o");
				   String Response=request.getParameter("r");
				   request.setAttribute("Detailkeyid",modiefiedid);
				   request.setAttribute("flid",flid);
				   request.setAttribute("observe",obse);
				   request.setAttribute("aplmaster",aplmaster);
				   request.setAttribute("apldetail",apldetail);
				   request.setAttribute("Response",res);
				   request.setAttribute("modiefiedid",Masid);
				   request.setAttribute("Remarks",resporemarks);
				   String empid=uservalues.getUsrm_ccno();
				   request.setAttribute("Empid",empid);
				   System.out.println("Detailkeyid::::123"+modiefiedid);
				   System.out.println("Detailkeyid::::123"+obse);
				   System.out.println("Detailkeyid::::123"+res);
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/PlantSafetyResponsibiltyPopup.jsp");
					rd.forward(request, response);
				
				
				
		   }
		   else if (action.equals("AuditDetailsClosurepopup_input.safetyinspect")) {	
			   String Detailkeyid=request.getParameter("UpdateList");
			   
			   System.out.println("Detailkeyid::::"+Detailkeyid);
			   
	/*		
			   String modiefiedid=Detailkeyid.replaceAll("\"", " ");*/
			   System.out.println("Modified Id Isss"+Detailkeyid);
			   String flid=request.getParameter("flid");
			   String Masid=ehsPlantSafetyService.getMaster(Detailkeyid);
			   System.out.println("Masid"+Masid);
			   String Aplmmst=request.getParameter("Aplmmst");
			   String Aplmdtl=request.getParameter("AplmDtl");
			   String Abnormality=request.getParameter("abnormality");
			   String Suggestion=request.getParameter("Suggestion");
			   String rname=uservalues.getUsrm_ccno();
			   
			/*   if(UIUtils.isValidKeyId(Aplmmst))
			   {
				   Abnormality="null";  
			   }
			   else if(UIUtils.isValidKeyId(Abnormality)){
				   Aplmmst="null";
				   Aplmdtl="null";
			   }*/
			   
	      	System.out.println("rname::::123"+Suggestion);
			   request.setAttribute("Detailkeyid",Detailkeyid);
			   request.setAttribute("Abnormality",Abnormality);
			   request.setAttribute("flid",flid);
			   request.setAttribute("Aplmmst",Aplmmst);
			   request.setAttribute("Aplmdtl",Aplmdtl);
			   request.setAttribute("modiefiedid",Masid);
			   request.setAttribute("Empid",rname);
			   request.setAttribute("Suggestion", Suggestion);
			   
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/PlantSafetyClosurePopup.jsp");
				rd.forward(request, response);
			}
		   else if (action.equals("plantsafetyVerifypopup_input.safetyinspect")) {	
			   String Detailkeyid=request.getParameter("UpdateList");
			   System.out.println("Keyid::"+Detailkeyid);
			   String modiefiedid=Detailkeyid.replaceAll("\"", " ");
			   System.out.println("Detailkeyid::::"+modiefiedid);
			   String flid=request.getParameter("flid");
			   String empid=uservalues.getUsrm_ccno();
			   request.setAttribute("flid",flid);
			   request.setAttribute("Empid",empid);
			   request.setAttribute("Detailkeyid",modiefiedid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/PlantSafetyVerify.jsp");
				rd.forward(request, response);
				
		   }
		   else if (action.equals("safetyinspectiondetails_input.safetyinspect")) {		
				
				String type=request.getParameter("mode");
				String modtype=request.getParameter("TYPE");
				System.out.println("The Type is"+type);
				System.out.println("The modtype is"+modtype);
				String rname=uservalues.getUsrm_ccno();
				String role=rolename;
				System.out.println("rname:::"+rname);
				System.out.println("safetyinspectiondetails_input -->Role in getData"+rolename);
				request.setAttribute("type",type);
				request.setAttribute("audittype",modtype);
				request.setAttribute("rolename",rname);
				request.setAttribute("role",role);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/SafetyInspectionGrid.jsp");
				rd.forward(request, response);
			
				CommonFunctions.debugMsg(" response " + response);		
			}
		   else if (action.equals("safetyinspectiondetails_getCol.safetyinspect")) {		
			 
			   PrintWriter out = response.getWriter();
			   String role=request.getParameter("role");
			   JSONObject jsonObject = new JSONObject();
			   CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter", true); 
			   //commonFilter = populateCommonFilter(request,"DivisionalAuditmodify",true);
			   commonFilter.setRoleLevel(role);
			   String mode = request.getParameter("mode");
			   String modtype = request.getParameter("TYPE");
			   commonFilter.setSafetyMode(mode);
			   commonFilter.setStatus(modtype);
			   commonFilter.setShe(user.getUsrm_ccno());
			   commonFilter.setEmpch(rolename);
				List<String[]> divaudit =null;	
				try {
						
					FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setIsGetCol("Y");
					 divaudit = ehsPlantSafetyService.getSafetyDetailsList(commonFilter);
				System.out.println(divaudit.get(1)+"divauditdivaudit "+divaudit.get(0));
				}catch (Exception e) {
						CommonFunctions.debugMsg(e.getMessage());
					}
					/*		JSONObject jsonObject = getTableModel(divaudit);
							jsonObject.set("multiSelect", true);
							
							httpSession.removeAttribute("PlanSafetyColmodel");
							httpSession.setAttribute("PlanSafetyColmodel", jsonObject);
							httpSession.removeAttribute("PlanSafetyFilter");
							httpSession.setAttribute("PlanSafetyFilter", commonFilter);
							
							out.println(jsonObject);
					} catch (Exception e) {
						e.printStackTrace();
					}
			   */
						JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						 GridColModel gridColModel = new GridColModel();			
						 
						 
						 jqGridTableModel.setSortable(false);			 
						 jqGridTableModel.setTableButton(true);
						 jqGridTableModel.setEnableFilter(true);
						 jqGridTableModel.setRowNumbers(true);
						 jqGridTableModel.setPaginate(false);
						 gridColModel.setHeaderNum(1);
						 String [] colHeader = divaudit.get(1);
						 String [] colHeaderHead = divaudit.get(0);

						 List<String[]> headers = new ArrayList<String[]>();	
						 headers.add(colHeader);			
						 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
				   	     jsonObject.set("tableWidth", "108%%");
				     	 jsonObject.set("tableHeight", "80%%");
				     	 if(mode.equals("modify") || mode.equals("delete") || mode.equals("view"))
				     	 jsonObject.set("multiSelect", false);
				     	 else
				     		jsonObject.set("multiSelect", true);
				     	 
				     	httpSession.setAttribute("mode", mode);
				     	httpSession.setAttribute("type", modtype);
				     	
				     	httpSession.removeAttribute("PlanSafetyColmodel");
						httpSession.setAttribute("PlanSafetyColmodel", jsonObject);
						httpSession.removeAttribute("PlanSafetyFilter");
						httpSession.setAttribute("PlanSafetyFilter", commonFilter);
						out.println(jsonObject); 
						
			}
		   else if (action.equals("safetyinspectiondetails_getData.safetyinspect")) {		
			   try {
				   String role=request.getParameter("role");
					CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter",false);
					FilterValues.getCommonFilters(request, commonFilter);
					String flid = request.getParameter("flid");
					commonFilter.setRoleLevel(role);
					//commonFilter.setFlid(flid);
					String mode = request.getParameter("mode");
					commonFilter.setSafetyMode(mode);
					commonFilter.setShe(user.getUsrm_ccno());
					commonFilter.setEmpch(rolename);
					commonFilter.setIsGetCol("N");
					List<String[]> MasterGrid = ehsPlantSafetyService.getSafetyDetailsList(commonFilter);
					PrintWriter out = response.getWriter();
					JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0,commonFilter.getTotalRecordCnt());
					CommonFunctions.debugMsg("ResourceGridmod:"+ResourceGridmod);	
					out.println(ResourceGridmod);
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
				}	
		   
		  }
		   else if(action.equals("safetyinspectiondetails_getExcel.safetyinspect"))
		  	{
		  		 
				// String type=(String)httpSession.getAttribute("type");
			     String type= "DM";
				 String formmode=(String)httpSession.getAttribute("mode");
				// String audit=(String)httpSession.getAttribute("audit");
				 System.out.println("type"+type+"::formmode:::"+formmode);
				 String empid=uservalues.getUsrm_ccno();
				 CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter",false);
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("PlanSafetyColmodel");
					
					commonFilter.setEmpch(empid);
					
					CommonFunctions.debugMsg("tblJSONObj"+tblJSONObj);
					String Titlename=null;
					if(type.equals("UH")){
					Titlename="Unit Head";
					}else if(type.equals("DM")){
					 Titlename="DMT Member";
					}else if(type.equals("EM")){
					 Titlename="EHS Manager";
					}
					tblJSONObj.put("title",   Titlename );
					
					String format = ExcelUtils.getFormat(request);
					commonFilter.setFromRow(tmpFromRow);
					Workbook wb = ehsPlantSafetyService.AuditExportExcel(commonFilter,tblJSONObj,format,type,formmode);
				
					ExcelUtils.writeToResponse(response, wb, Titlename, format);
		  	}
		   
		   else if(action.equals("safetyinspection_delete.safetyinspect")){
			   DeleteSafetyDetails(request,response);	
		   }  
		   else if(action.equals("safetyinspectiondtl_delete.safetyinspect")){
			   DeleteSafetyDtl(request,response);	
		   } 
		   else if(action.equals("functionalLoc.safetyinspect")){
			   
			   FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
				//functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		   }  
		   else if(action.equals("safetyinspectionReport_input.safetyinspect")){
			   request.setAttribute("role", rolename);
			   request.setAttribute("locnId", location);
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/PlantSafety/PlantSafetyReport.jsp");
				rd.forward(request, response);
		   
		   }
		   else if(action.equals("safetyinspectionReport_getCol.safetyinspect"))
			{
				PrintWriter out=response.getWriter();
				String type=request.getParameter("type");
				String status=request.getParameter("status");
				String auditfromdate=request.getParameter("fromdate");
				String audittodate=request.getParameter("todate");
				String reposne=request.getParameter("repons");
				String role=request.getParameter("role");
				
				
				CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter",false);
				commonFilter.setAbnDetectBy(reposne);
				commonFilter.setAftFromDt(auditfromdate);
				commonFilter.setAftToDt(audittodate);
				commonFilter.setAreatype(type);
				commonFilter.setAwise(status);
				commonFilter.setActionKeyId(loginflid);
				commonFilter.setAbnAllch(role);
				JSONObject jsonObject = new JSONObject();
				List<String[]> SafetyListGrid = null;		
				try {						
					//FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setIsGetCol("Y");
					SafetyListGrid =  ehsPlantSafetyService.getSafetyReportDetails(commonFilter);
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
				}	
				
			//	JSONObject criAssData= UIUtils.convertToJqGridTableObject(AuditListGrid,request, 2, 0, commonFilter.getTotalRecordCnt());
		         
				//httpSession.setAttribute("CricAssessmentReport", criAssData);	
				 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				 GridColModel gridColModel = new GridColModel();			
				 gridColModel.setHeaderNum(1);
				 
				 jqGridTableModel.setSortable(true);			 
				 jqGridTableModel.setTableButton(true);
				 jqGridTableModel.setEnableFilter(true);
				 jqGridTableModel.setRowNumbers(true);
				 jqGridTableModel.setPaginate(true);
				 
				 
				 String [] colHeaderHead = SafetyListGrid.get(0);
				 String [] colHeader = SafetyListGrid.get(1);
				 List<String[]> headers = new ArrayList<String[]>();	
				 headers.add(colHeader);			
				 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
		   	     jsonObject.set("tableWidth", "108%%");
		     	 jsonObject.set("tableHeight", "80%%");
		     	
		     	 //jsonObject.put("data",criAssData);
		   	     httpSession.removeAttribute("auditListColModel");
				
				 CommonFunctions.debugMsg("jsonObject " + jsonObject);
				 httpSession.setAttribute("status",status);	
				 httpSession.setAttribute("auditfromdate",auditfromdate);	
				 httpSession.setAttribute("audittodate",audittodate);	
				 httpSession.setAttribute("reposne",reposne);	
				 httpSession.setAttribute("role",role);	
				 
				 httpSession.setAttribute("auditListColModel",jsonObject);	
				 out.println(jsonObject);				   
				
				
			}
			else if(action.equals("safetyinspectionReport_getData.safetyinspect"))
			{
				try {
					String type=request.getParameter("type");
					String status=request.getParameter("status");
					String auditfromdate=request.getParameter("fromdate");
					String audittodate=request.getParameter("todate");
					String reposne=request.getParameter("repons");
					String role=request.getParameter("role");
					CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter",true);
					FilterValues.getCommonFilters(request, commonFilter);
					commonFilter.setAbnDetectBy(reposne);
					commonFilter.setAftFromDt(auditfromdate);
					commonFilter.setAftToDt(audittodate);
					commonFilter.setAreatype(type);
					commonFilter.setAwise(status);
					commonFilter.setActionKeyId(loginflid);
					commonFilter.setAbnAllch(role);
					commonFilter.setIsGetCol("N");
					List<String[]> MasterGrid = ehsPlantSafetyService.getSafetyReportDetails(commonFilter);
					PrintWriter out = response.getWriter();
					JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
					CommonFunctions.debugMsg("ResourceGridmod:"+ResourceGridmod);	
					out.println(ResourceGridmod);
				} catch (Exception e) {
					CommonFunctions.debugMsg(e.getMessage());
				}			   
				
			}
			  else if(action.equals("Plantsafety_Excelview.safetyinspect")){
				  
				   try{
				   String plansafKeyid=request.getParameter("plansafetyKeyid");
				   String flid=request.getParameter("flid");
				   String format = ExcelUtils.getFormat(request);
				   String path = UIUtils.getExcelTemplatePath(request);
				   Workbook wb = ehsPlantSafetyService.PlanSafetyExcelView(plansafKeyid,flid,format,path);
				   format = ".xlsx";
				  ExcelUtils.writeToResponse(response, wb, "PlanSafety_"+plansafKeyid, format);
				   }
				   catch(Exception ex){
					    PrintWriter out = response.getWriter();
						JSONObject err = new JSONObject();				
						err.put("message" ,"Data Not Found" );
						out.print(err.toString());
				   }
			   }
			else if(action.equals("safetyinspectionReport_getExcel.safetyinspect")){
				httpSession = request.getSession(false);
				 String role=(String)httpSession.getAttribute("role");
				 String status=(String)httpSession.getAttribute("status");
				 String auditfromdate=(String)httpSession.getAttribute("auditfromdate");
				 String audittodate=(String)httpSession.getAttribute("audittodate");
				 String reposne=(String)httpSession.getAttribute("reposne");
				CommonFilter commonFilter = populateCommonFilter(request,"plantSafetyCommonFilter",true);
				 String tmpFromRow = commonFilter.getFromRow();
				  commonFilter.setAbnDetectBy(reposne);
					commonFilter.setAftFromDt(auditfromdate);
					commonFilter.setAftToDt(audittodate);
				
					commonFilter.setAwise(status);
					commonFilter.setActionKeyId(loginflid);
					commonFilter.setAbnAllch(role);
					commonFilter.setFromRow(null);
				 JSONObject colmodel = (JSONObject) httpSession.getAttribute("auditListColModel");
				colmodel.put("title","PlantInspectionReport");
	           String format = ExcelUtils.getFormat(request);
				
				Workbook wb = ehsPlantSafetyService.getPlantSafetyExcel(colmodel,format,commonFilter);
				commonFilter.setFromRow(tmpFromRow);
				ExcelUtils.writeToResponse(response, wb, "PlantInspectionReport", format);
				
			}

		
	}	
	private void SafetyMainDeleteGrid(HttpServletRequest request, HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
  		 String Detailkeyid=request.getParameter("UpdateList");
  		 String apmdelete=request.getParameter("deletelist");
  		 System.out.println("apmdelete::::"+apmdelete);;
  	   List<String> deletelist=ehsPlantSafetyService.getCheckDeleteList(Detailkeyid,apmdelete);
  		System.out.println("deletelist");
	}
	private void SaveResponsibilty(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		
		String detailkeyid=request.getParameter("dtlkeyid");
		String responsevalue=request.getParameter("responsevalue");	
		String targetdate=request.getParameter("targetdate");
		String mstkeyid=request.getParameter("mstkeyid");
		String flid=request.getParameter("flid");
		String observe=request.getParameter("observe");
		String aplmkeyid=request.getParameter("aplmkeyid");
		String apldkeyid=request.getParameter("apldkeyid");
		String remarks=request.getParameter("rmrks");
		//System.out.println("aplmkeyid"+aplmkeyid);
	//	System.out.println("apldkeyid"+apldkeyid);
		//System.out.println("observe:::"+observe);
		
		SheTlPlantSafetymst newSheTlPlantSafetymst= new SheTlPlantSafetymst();
		newSheTlPlantSafetymst.setSpsmKeyid(mstkeyid);
		newSheTlPlantSafetymst.setSpsmFlid(flid);
		newSheTlPlantSafetymst.setSpsmTempfield1("-");
		
		SheTlPlantSafetydtl sheTlPlantSafetydtl= new SheTlPlantSafetydtl();
		sheTlPlantSafetydtl.setSpsdKeyid(detailkeyid);
		sheTlPlantSafetydtl.setSpsdResponRemarks(remarks);
		//sheTlPlantSafetydtl.setSpsdObserve(observe);
		//
		

		GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
		GenTlActionplanmst existgenTlActionplanmst =new GenTlActionplanmst();
		GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
		genTlActionplanmst.setAplmCreatedby(uservalues.getUsrm_ccno());
		genTlActionplanmst.setAplmDetailrefid(detailkeyid);
		genTlActionplanmst.setAplmKeyid(aplmkeyid);
		genTlActionplanmst.setAplmFlid(flid);
		genTlActionplanmst.setAplmMasterrefid(mstkeyid);
		genTlActionplanmst.setAplmMaintask(observe);
		genTlActionplanmst.setAplmRefdoctype("PSI");
		genTlActionplanmst.setAplmStatus("P");
		genTlActionplandtl.setApldResponsibility(responsevalue);
		genTlActionplandtl.setApldStatus("P");
		genTlActionplandtl.setApldKeyid(apldkeyid);
		genTlActionplandtl.setApldTargetdate(targetdate);
		genTlActionplandtl.setApldActionplan(observe);
		
		existgenTlActionplanmst=genTlActionplanmst;
		actionPlanRptService.update(genTlActionplanmst,existgenTlActionplanmst ,genTlActionplandtl);
		String resultsupdate = ehsPlantSafetyService.singleResponsiblty(detailkeyid,responsevalue,targetdate,newSheTlPlantSafetymst,sheTlPlantSafetydtl);
		System.out.println("resultsupdate::"+resultsupdate);
		
	}
private void SaveActionClosure(HttpServletRequest request, HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		PrintWriter out=response.getWriter();
	String detailkeyid=request.getParameter("dtlkeyid");
		
		String responsevalue=request.getParameter("responsevalue");	
		String targetdate=request.getParameter("targetdate");
		
System.out.println("m date "+ targetdate);
		String reviseddate=request.getParameter("reviseddate");
System.out.println("m2date " +reviseddate);
		String status=request.getParameter("status");
		String correctiveaction=request.getParameter("correctiveaction");
		String aplmst=request.getParameter("Aplmmst");
		String remarks=request.getParameter("rmrks");
		String aplmdtl=request.getParameter("Aplmdtl");
		String abnKeyid=request.getParameter("abnormality");
		String Suggestion=request.getParameter("Suggestion");
		System.out.println(" abnormality keyid isssss "+abnKeyid);
		if (abnKeyid.equals("X") && Suggestion.equals("X") ){
		if(targetdate!=null&&status.equals("C"))
		{	GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
		GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
		GenTlActionplanmst existgenTlActionplanmst =new GenTlActionplanmst();
		genTlActionplanmst.setAplmKeyid(aplmst);
		genTlActionplandtl.setApldKeyid(aplmdtl);
		genTlActionplandtl.setApldActionplan(correctiveaction);
		genTlActionplanmst.setAplmKeyid(aplmst);
		genTlActionplandtl.setApldAplmKeyid(aplmst);
		genTlActionplandtl.setApldKeyid(aplmdtl);
		genTlActionplandtl.setApldActionplan(correctiveaction);
		genTlActionplandtl.setApldRemarks(remarks);
		genTlActionplandtl.setApldCountermeasure(correctiveaction);
		
			//genTlActionplandtl.setApldTargetdate(targetdate);
			genTlActionplandtl.setApldStatus(status);
			genTlActionplanmst.setAplmStatus(status);
			genTlActionplandtl.setApldCompletedby(responsevalue);
			genTlActionplandtl.setApldCompleatedon(targetdate);
			genTlActionplandtl.setApldTargetdate(targetdate);
			genTlActionplanmst.setAplmPlandate(targetdate);
			
			//genTlActionplandtl.setApldCompleatedon(CommonFunctions.pg_getDateTimeFromDate(targetdate)); // "2026-03-06T00:00:00"
			System.out.println("DEBUG ApldTargetdate :: " + genTlActionplandtl.getApldTargetdate());
			System.out.println("DEBUG ApldCompleatedon :: " + genTlActionplandtl.getApldCompleatedon());
			System.out.println("DEBUG AplmPlandate :: " + genTlActionplanmst.getAplmPlandate());
			List<GenTlActionplandtl> actionPlanDetails = new ArrayList<GenTlActionplandtl>();
			actionPlanDetails.add(genTlActionplandtl);
			
			existgenTlActionplanmst=genTlActionplanmst;
			//actionPlanRptService.update(genTlActionplanmst,existgenTlActionplanmst ,genTlActionplandtl);
			actionPlanRptService.saveActionPlanCompletion(actionPlanDetails);
		}
		}
		
		String resultsupdate = ehsPlantSafetyService.updateActionCLosure(detailkeyid,responsevalue,targetdate,reviseddate,status,correctiveaction,remarks,abnKeyid);
		   
		JSONObject json=new JSONObject();
		   
		 json.put("resultsupdate", resultsupdate);
		 out.println(json);
		
}
private void SaveActionVerification(HttpServletRequest request, HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
		String detailkeyid=request.getParameter("dtlkeyid");
	String responsevalue=request.getParameter("responsevalue");	
	String targetdate=request.getParameter("targetdate");
	String verifystatus=request.getParameter("verifystatus");	
	String remarks=request.getParameter("rmrks");
	//System.out.println("detailkeyid::"+detailkeyid+"responsevalue::"+responsevalue+"targetdate::"+targetdate);
	String resultsupdate = ehsPlantSafetyService.UpdateVerfification(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
	System.out.println("resultsupdate::"+resultsupdate);
	
}
	private void DeleteSafetyDetails(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
			String deletemsg;
			PrintWriter out= response.getWriter();
			SheTlPlantSafetymst newsheTlPlantSafetymst= new SheTlPlantSafetymst();
			SheTlPlantSafetydtl sheTlPlantSafetydtl= new SheTlPlantSafetydtl();
			//SheTlRiskassessmentmst  newgenTlRiskassessment =new SheTlRiskassessmentmst();	
			newsheTlPlantSafetymst=(SheTlPlantSafetymst)UIUtils.setBeanProperties((Object) newsheTlPlantSafetymst,request);
			String  safetydetails = request.getParameter("safetydetails");
			String keyid = request.getParameter("keyid");
			String mode = request.getParameter("mode");
			try{
				if(UIUtils.isValidKeyId(keyid)){
				sheTlPlantSafetydtl.setSpsdKeyid(keyid);
				newsheTlPlantSafetymst =ehsPlantSafetyService.delete(newsheTlPlantSafetymst,sheTlPlantSafetydtl);
				deletemsg="Data Deleted Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg",deletemsg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);		
				//CommonFunctions.debugMsg(returnData.toString());		
				out.print(returnData.toString());
				}
			}catch (ValidationExceptions e) 
			{
				CommonFunctions.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "PlantSafetyCreation");
				out.print(errMessage.toString());							    	
			}
								
			catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("BusinessApplicationExceptions");
				e.printStackTrace();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "PlantSafetyCreation");
				out.print(errMessage.toString());
				CommonFunctions.debugMsg(" e " + errMessage );
			}
			catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
	}
	private void DeleteSafetyDtl(HttpServletRequest request,HttpServletResponse response) throws IOException {
			PrintWriter out= response.getWriter();
			String  dtlid = request.getParameter("dtlid");
			//String keyid = request.getParameter("keyid");
			String mode = request.getParameter("mode");
			try{
				if(UIUtils.isValidKeyId(dtlid)){
				List<String[]> Safetydtl =ehsPlantSafetyService.deletedtl(dtlid);
				if(Safetydtl!=null){
				String deletemsg="Data Deleted Successfully";
				JSONObject successData = new JSONObject();
				successData.put("msg",deletemsg);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);		
				out.print(returnData.toString());
				}
				}
			}catch(Exception e)
			{   
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
	}
	@SuppressWarnings("unchecked")
	private void saveSafetyDetail(HttpServletRequest request,HttpServletResponse response,SheTlPlantSafetyBean sheTlPlantSafetyBean) throws IOException {
		
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String Type=request.getParameter("TYPE");
			String mode=request.getParameter("mode");
			JSONObject returnData = new JSONObject();
	    	JSONObject successData = new JSONObject(); 
	    	request.setAttribute("savetype", Type);
	    	request.setAttribute("savemode", mode);
				if( httpSession != null && user != null){
					SheTlPlantSafetymst newSheTlPlantSafetymst = new SheTlPlantSafetymst();
					SheTlPlantSafetydtl newSheTlPlantSafetydtl = new SheTlPlantSafetydtl();
		    		String gridData = request.getParameter("safetydetails");
		    		newSheTlPlantSafetymst.setSpsmCreatedby(user.getUsrm_ccno());
		    		newSheTlPlantSafetydtl.setSpsdCreatedby(user.getUsrm_ccno());
		    		
		    		newSheTlPlantSafetymst =(SheTlPlantSafetymst)UIUtils.setBeanProperties((Object)newSheTlPlantSafetymst,request);
		    		newSheTlPlantSafetydtl =(SheTlPlantSafetydtl)UIUtils.setBeanProperties((Object)newSheTlPlantSafetydtl,request);
		    		sheTlPlantSafetyBean =(SheTlPlantSafetyBean) UIUtils.setBeanProperties((Object)sheTlPlantSafetyBean,request);
		    		SheTlPlantSafetymst existSheTlPlantSafetymst = (SheTlPlantSafetymst)httpSession.getAttribute("PlantSafetyServlet"); 
		    		SheTlPlantSafetydtl existSheTlPlantSafetydtl = (SheTlPlantSafetydtl)httpSession.getAttribute("PlantSafetyServlet"); 
					List<SheTlPlantSafetydtl> plansafetyDtlList = null;
			    	JSONArray plansafetyDtlListJson = null;
			    	if(UIUtils.isValidKeyId(gridData) )
		    		{
			    		plansafetyDtlListJson = JSONArray.fromString(gridData);
			    		plansafetyDtlList=(List<SheTlPlantSafetydtl>)UIUtils.convertJSONArrToList(newSheTlPlantSafetydtl, plansafetyDtlListJson);
					    if(plansafetyDtlList!= null)
					    {
					        for(int i=0 ;i<=plansafetyDtlList.size()-1;i++){
					        //sheTlRiskassessmentdtlList.get(i).setRasdCreatedby(user.getUsrm_ccno());
					        }
					        newSheTlPlantSafetymst.setSafetyDetails(plansafetyDtlList);
					    }
		    		}
			    	try{
			    		
					    boolean insert = true;
						if( UIUtils.isValidKeyId(newSheTlPlantSafetymst.getSpsmKeyid()))
						{
							existSheTlPlantSafetymst = ehsPlantSafetyService.update(newSheTlPlantSafetymst,existSheTlPlantSafetymst,sheTlPlantSafetyBean);
							insert = false;	
							/* action plan update --start--*/
							List<SheTlPlantSafetydtl> details=newSheTlPlantSafetymst.getSafetyDetails();
			        		String targetDate;
			        		String responsibilty;
							
							for (int i=0;i < details.size();i++)
							{
								String detailid=details.get(i).getSpsdKeyid();
								String apldkeyid=details.get(i).getSpsdTempfield1();
								System.out.println("details.get(i).getSpsdKeyid();"+apldkeyid);
								String observe=details.get(i).getSpsdObserve();
								String corrective=details.get(i).getSpsdCorrectAction();
								targetDate=details.get(i).getSpsdTargetDate();
								String PSIType=details.get(i).getSpsdTempfield2();
								String PSIABN=details.get(i).getSpsdTempfield3();
								
							//	System.out.println("PSIABN:::::::"+);
								responsibilty=details.get(i).getSpsdResponsibilty();
								String masterid=details.get(i).getSpsdSpsmKeyid();
								String status=details.get(i).getSpsdStatus();
								String actionplanno=details.get(i).getSpsdActiontaken();
							if(PSIType.equals("AP")){
								GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
								GenTlActionplanmst existgenTlActionplanmst =new GenTlActionplanmst();
								GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
								GenTlActionplandtl genTlActionplandtllist =new GenTlActionplandtl();
								genTlActionplanmst.setAplmCreatedby(uservalues.getUsrm_ccno());
								genTlActionplanmst.setAplmDetailrefid(detailid);
								genTlActionplanmst.setAplmFlid(newSheTlPlantSafetymst.getSpsmFlid());
								genTlActionplanmst.setAplmMasterrefid(masterid);
								genTlActionplanmst.setAplmMaintask(observe);
								genTlActionplanmst.setAplmRefdoctype("PSI");
								genTlActionplanmst.setAplmStatus(status);
								genTlActionplandtl.setApldResponsibility(responsibilty);
								genTlActionplandtl.setApldStatus(status);
								genTlActionplandtl.setApldTargetdate(targetDate);
								genTlActionplandtl.setApldActionplan(observe);
							  System.out.println("Action Plan No:::"+actionplanno);
							  System.out.println("targetDate"+targetDate);
							  System.out.println("responsibilty"+responsibilty);
							  if(actionplanno.length()==10)
								{
								if(targetDate!=null||responsibilty!=null)
								{  
									genTlActionplanmst.setAplmKeyid(actionplanno);
									genTlActionplandtl.setApldKeyid(apldkeyid);
									genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
									existgenTlActionplanmst=genTlActionplanmst;
									System.out.println("InISDE THE IF");
								genTlActionplandtllist.setApldActionplan(observe);
							 		genTlActionplandtllist.setApldCompletedby("{}");
							 		genTlActionplandtllist.setApldCompleatedon(newSheTlPlantSafetymst.getSpsmModifiedon());
							 		 actionPlanRptService.update(genTlActionplanmst,existgenTlActionplanmst ,genTlActionplandtl);
								}
								}
							  
							  if(actionplanno.length()!=10)
							  {
									System.out.println("InISDE THE SLE");
									genTlActionplandtllist.setApldActionplan(observe);
									genTlActionplandtllist.setApldResponsibility(responsibilty);
									genTlActionplandtllist.setApldTargetdate(targetDate);
									String date = genTlActionplandtllist.getApldTargetdate();
									genTlActionplandtllist.setApldTargetdate(CommonFunctions.pg_getDateFromPGTimeStamp(date));
									
								actionPlanRptService.create(genTlActionplanmst, genTlActionplandtllist);
								}
								
							}
							else if(PSIType.equals("AB")){
								System.out.println("Inside Abnormality Ser 1");
								AbnTlAbnormality abnTlAbnormality=new AbnTlAbnormality();
								abnTlAbnormality.setAbnmKeyid(PSIABN);
                                abnTlAbnormality.setAbnmDescription(observe);
								abnTlAbnormality.setAbnmStatus(status);
								abnTlAbnormality.setAbnmResponsibleid(responsibilty);
								abnTlAbnormality.setAbnmCountermeasure(corrective);
								abnTlAbnormality.setAbnmCreatedby(uservalues.getUsrm_ccno());
								abnTlAbnormality.setAbnmFlid(newSheTlPlantSafetymst.getSpsmFlid());
								abnTlAbnormality.setAbnmRefdocid(detailid);
							    abnTlAbnormality.setAbnmDate(newSheTlPlantSafetymst.getSpsmDate());
							    abnTlAbnormality.setAbnmCreatedby(uservalues.getUsrm_ccno());
							    abnTlAbnormality.setAbnmDetectedby(uservalues.getUsrm_ccno());
							    abnTlAbnormality.setAbnmTargetdate(targetDate);
							    abnTlAbnormality.setAbnmWoendtime(targetDate);
							    //abnTlAbnormality.setAbnmWostarttime(targetDate);
							    abnTlAbnormality.setAbnmRefdoctype("PSI");
							    abnTlAbnormality.setAbnmPillar("JH");
								abnTlAbnormality.setAbnmTagclassid("TAG000002");
								abnTlAbnormality.setAbnmImpactid("ABI00011");
								abnTlAbnormality.setAbnmTypeid("ABT0007");
								abnTlAbnormality.setAbnmCategoryid("ABC00080");
								System.out.println("Inside Abnormality Ser 1");
								if(UIUtils.isValidKeyId(PSIABN))
								{
									ehsPlantSafetyService.updateAbnormality(abnTlAbnormality);		
									System.out.println("Inside Abnormality Ser 3");
									
								}
								
								
								else
								{
									ehsPlantSafetyService.create(abnTlAbnormality);	
								
								}
								
								
								
							}
							
							else if(PSIType.equals("SUG")){
								
								
								System.out.println("SUGGESTION UPDATE");
								KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
							  	
							//	newKznTlKaizenbankmst.setKzbnKeyid();
								
								newKznTlKaizenbankmst.setKzbnBenefit("KCT0000023");
						    	newKznTlKaizenbankmst.setKzbnPqcdsme("S");
						    	
						    	newKznTlKaizenbankmst.setKzbnKaizen(observe);
						    	
						    	newKznTlKaizenbankmst.setKzbnFlid(newSheTlPlantSafetymst.getSpsmFlid());
						    	String Flid=newSheTlPlantSafetymst.getSpsmFlid();
						    	String ElementId=ehsPlantSafetyService.getElementId(Flid);
						    	
						    	newKznTlKaizenbankmst.setKzbnElementid(ElementId);
						    	newKznTlKaizenbankmst.setKzbnCreatedby(uservalues.getUsrm_ccno());
						    	newKznTlKaizenbankmst.setKzbnSuggestedby(uservalues.getUsrm_ccno());
						    	newKznTlKaizenbankmst.setKzbnDate(newSheTlPlantSafetymst.getSpsmDate());
								ehsPlantSafetyService.UpdateSuggestion(newKznTlKaizenbankmst);
								
								
							}
							
							
							}
							
							/*action plan update --end--*/
						}
						else{
							newSheTlPlantSafetymst = ehsPlantSafetyService.create(newSheTlPlantSafetymst,existSheTlPlantSafetymst,sheTlPlantSafetyBean);
						/*** for action plan create --start--**/
						
							List<SheTlPlantSafetydtl> details=newSheTlPlantSafetymst.getSafetyDetails();
							System.out.println("details "+details);
							for (int i=0;i < details.size();i++)
							{
								String detailid=details.get(i).getSpsdKeyid();
								System.out.println("Dateail id"+detailid);
								String observe=details.get(i).getSpsdObserve();
								String corrective=details.get(i).getSpsdCorrectAction();
								String targetDate=details.get(i).getSpsdTargetDate();
								
								String responsibilty=details.get(i).getSpsdResponsibilty();
								String masterid=details.get(i).getSpsdSpsmKeyid();
								String status=details.get(i).getSpsdStatus();
								String PSIType=details.get(i).getSpsdTempfield2();
								
								System.out.println("PSITYPE:::::"+PSIType);
								if(PSIType.equals("AP")){
								if(targetDate!=null||responsibilty!=null)
								{  
									GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
									GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
									GenTlActionplandtl genTlActionplandtllist =new GenTlActionplandtl();
									genTlActionplanmst.setAplmCreatedby(uservalues.getUsrm_ccno());
									genTlActionplanmst.setAplmDetailrefid(detailid);
									genTlActionplanmst.setAplmFlid(newSheTlPlantSafetymst.getSpsmFlid());
									genTlActionplanmst.setAplmMasterrefid(masterid);
									genTlActionplanmst.setAplmMaintask(observe);
									genTlActionplanmst.setAplmRefdoctype("PSI");
									genTlActionplanmst.setAplmStatus(status);
									genTlActionplandtl.setApldResponsibility(responsibilty);
									genTlActionplandtl.setApldStatus(status);
									genTlActionplandtl.setApldTargetdate(targetDate);
									System.out.println("Target Date for action plan ehd"+targetDate);
									genTlActionplandtl.setApldTargetdate(CommonFunctions.pg_getDateFromPGTimeStamp(targetDate));
									System.out.println("Target Date for action plan ehd after conversion"+genTlActionplandtl.getApldTargetdate());
									genTlActionplandtl.setApldActionplan(observe);
								
							 	actionPlanRptService.create(genTlActionplanmst, genTlActionplandtl);
								}
								}
								else if(PSIType.equals("AB")){
									AbnTlAbnormality abnTlAbnormality=new AbnTlAbnormality();
							
                                    abnTlAbnormality.setAbnmDescription(observe);
									abnTlAbnormality.setAbnmStatus(status);
									abnTlAbnormality.setAbnmResponsibleid(responsibilty);
									abnTlAbnormality.setAbnmCountermeasure(corrective);
									abnTlAbnormality.setAbnmCreatedby(uservalues.getUsrm_ccno());
									abnTlAbnormality.setAbnmFlid(newSheTlPlantSafetymst.getSpsmFlid());
									abnTlAbnormality.setAbnmRefdocid(detailid);///////////////////////////////
								    abnTlAbnormality.setAbnmDate(newSheTlPlantSafetymst.getSpsmDate());
								    abnTlAbnormality.setAbnmCreatedby(uservalues.getUsrm_ccno());
								    abnTlAbnormality.setAbnmDetectedby(uservalues.getUsrm_ccno());
								    abnTlAbnormality.setAbnmTargetdate(targetDate);
								    abnTlAbnormality.setAbnmWoendtime(targetDate);
								    abnTlAbnormality.setAbnmRefdoctype("PSI");
								    abnTlAbnormality.setAbnmPillar("JH");
									abnTlAbnormality.setAbnmTagclassid("TAG000002");
									abnTlAbnormality.setAbnmImpactid("ABI00011");
									abnTlAbnormality.setAbnmTypeid("ABT0007");
									abnTlAbnormality.setAbnmCategoryid("ABC00080");
									ehsPlantSafetyService.create(abnTlAbnormality);	
								}
								
								
								else if(PSIType.equals("SUG")){
									System.out.println("Suggeston");
									KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst ();
								  	
									newKznTlKaizenbankmst.setKzbnBenefit("KCT0000023");
							    	newKznTlKaizenbankmst.setKzbnPqcdsme("S");
							    	
							    	newKznTlKaizenbankmst.setKzbnKaizen(observe);
							    	
							    	newKznTlKaizenbankmst.setKzbnFlid(newSheTlPlantSafetymst.getSpsmFlid());
							    	String Flid=newSheTlPlantSafetymst.getSpsmFlid();
							    	String ElementId=ehsPlantSafetyService.getElementId(Flid);
							    	
							    	newKznTlKaizenbankmst.setKzbnElementid(ElementId);
							    	newKznTlKaizenbankmst.setKzbnCreatedby(uservalues.getUsrm_ccno());
							    	newKznTlKaizenbankmst.setKzbnSuggestedby(uservalues.getUsrm_ccno());
							    	newKznTlKaizenbankmst.setKzbnDate(newSheTlPlantSafetymst.getSpsmDate());
							    	
							    	
							    	newKznTlKaizenbankmst.setKzbnRefdocno(detailid);//detailid spsd
							    	newKznTlKaizenbankmst.setKzbnRefdoctype("PSI");
							    	
							    	ehsPlantSafetyService.createSuggestion(newKznTlKaizenbankmst);	
								
								
								}
									
								}
								
								
							}
							/*** for action plan create--end--**/
							
						
						String msgPropertyIdnt=null;				
					   if(insert){
							msgPropertyIdnt = "success-save";
						}else
						msgPropertyIdnt = "success-update";
						
					   
					   
					   
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mode",sheTlPlantSafetyBean.getFormActionMode() );
						successData.put("keyId", newSheTlPlantSafetymst.getSpsmKeyid());
						successData.put("spsmkeyid", newSheTlPlantSafetymst.getSpsmKeyid());
						successData.put("spsmflid", newSheTlPlantSafetymst.getSpsmFlid());
						successData.put("spsmdate", newSheTlPlantSafetymst.getSpsmDate());
						successData.put("spsmtype", newSheTlPlantSafetymst.getSpsmType());
						returnData.put("successData",successData);
						out.print(returnData.toString());
						out.close();
			    	}
						catch(ValidationExceptions e)
						{
							out.print(e.toString());
							System.out.println(e.toString());
						}catch(Exception e){
							e.printStackTrace();
							JSONObject err = new JSONObject();
							err.put("tpmException", "Data Not Saved");
							out.print(err.toString());
						}			
				    }	
				}
				
		private CommonFilter populateCommonFilter(HttpServletRequest request,
					String beanIdentifier, boolean createNew) {
				HttpSession httpSession = request.getSession(false);
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute(beanIdentifier);
				if (commonFilter != null && !createNew) {
					FilterValues.setPaginationParams(request, commonFilter);
				} else {
					commonFilter = new CommonFilter();
					commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
					commonFilter = FilterValues.getRoleViewFilters(request, commonFilter);
					commonFilter.setViewClick('Y');
					httpSession.removeAttribute(beanIdentifier);
					httpSession.setAttribute(beanIdentifier, commonFilter);
				}
				
				 if( Constants.passNullDate.contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("N")) ){
					 commonFilter.setFromDate(CommonFunctions.addMonth(CommonFunctions.getDate(),-1));
					 commonFilter.setToDate(CommonFunctions.getDate());
				 }	 
				  if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }

				  CommonFunctions.debugMsg("commonfileter.getTotla=="+commonFilter.getTotalRecordCnt());
				  
				return commonFilter;
			}
		private JSONObject getTableModel(List<String[]> headers) {
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			String[] colHeader = headers.get(1);
			String[] colHeaderindex = headers.get(1);

			jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setEnableFilter(true);
		//	jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setTableHeight(500);
			jqGridTableModel.setTableWidth(1000);
		//	jqGridTableModel.setRowHeight(false);
			jqGridTableModel.setGroupBy(false);
			//jqGridTableModel.setSortable(false);

			for (int i = 0; i < colHeader.length; i++) {
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeaderindex[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setEditable(false);
				if (i > 1) {
					jqGridColModel.setAlign("left");
				}
				if(i == 0)
				{
					jqGridColModel.setHidden(true);
				}
				if(i==2){
					jqGridColModel.setHidden(true);
				}
				if(i==3){
					jqGridColModel.setHidden(true);
				}
				if(i== colHeader.length-1){
					jqGridColModel.setHidden(true);
				}
				jqGridTableModel.getColModel().add(jqGridColModel);
			}

			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			tableModel.set("tableHeight", "85%%");
			tableModel.set("tableWidth", "108%%");
			return tableModel;
		}	

}