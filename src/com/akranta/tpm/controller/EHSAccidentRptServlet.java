package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.SheAccBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;
import com.akranta.tpm.model.SheTlIncidentmstnew;
import com.akranta.tpm.service.ActionPlanRptService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.EHSAccidentIncidentRptingService;
import com.akranta.tpm.service.NewNearMissService;
import com.akranta.tpm.service.impl.ActionPlanRptServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.EHSAccidentIncidentRptingServiceImpl;
import com.akranta.tpm.service.impl.NewNearMissServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import net.sf.json.JSONObject;


public class EHSAccidentRptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EHSAccidentIncidentRptingService  ehsAccidentIncidentRptingService ;
	private ActionPlanRptService actionPlanRptService;
	 private CommonFilterService commonFilterService;
	NewNearMissService nearMissService;
	String glbLocation;
	String  filePath = null;
	String glblmsg=null;
	private static String DOC_ROOT_PATH;
	private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
    public EHSAccidentRptServlet() {
        super();
        }
    
    
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
       // CommonFunctions.debugMsg( " parentFolderName "  + parentFolderName);
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        CommonFunctions.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        CommonFunctions.debugMsg(" FileManagerBasePath " + basePath );
        
        boolean pathExist = new File( basePath).exists();
        CommonFunctions.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
        if( basePath != null && pathExist ){
        	DOC_ROOT_PATH = basePath;
        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
        	
        }
        CommonFunctions.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
        CommonFunctions.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
    	}catch(Exception e ){
    		e.printStackTrace();
    		CommonFunctions.debugMsg(" File Manager exception " + e.getMessage());
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  HttpSession httpSession = request.getSession(false);
		  AdmTlUsermst users=UIUtils.getLoginUser(request);
		  String action = UIUtils.getActionPart(request) ;
	      ComboFilter comboFilter = new ComboFilter();
	      String location=UIUtils.getlocation(request);
			 String loginflid=CommonFunctions.getLoginFlid(request);
	  		 String loginlevel=CommonFunctions.getLoginLevel(request);
	  		 String loginElementid = (String) httpSession.getAttribute("loginElementid");
	  		String locn=CommonFunctions.getLoginLocaton(request);
			glbLocation=locn;
	//  System.out.println("loginElementid:::"+loginElementid);
	  
	 
	      try {
	    	 // ehsAccidentIncidentRptingService = (EHSAccidentIncidentRptingServiceImpl)UIUtils.getServiceObject(request,"EHSAccidentIncidentRptingServiceImpl");
	    	  ehsAccidentIncidentRptingService=(EHSAccidentIncidentRptingServiceImpl)UIUtils.getServiceObject(request,"EHSAccidentIncidentRptingServiceImpl");
	    	  actionPlanRptService = (ActionPlanRptServiceImpl) UIUtils.getServiceObject(request, "ActionPlanRptServiceImpl");
	    	  nearMissService=(NewNearMissServiceImpl)UIUtils.getServiceObject(request,"NewNearMissServiceImpl");
	    	  commonFilterService =(CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
	    	  ehsAccidentIncidentRptingService.EHSAccidentRptingServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	    	  actionPlanRptService.ActionPlanRptServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	    		
	      } catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}
	      
	      List<String []> getUserLoginDtl= nearMissService.getElementId(loginflid,loginlevel, loginElementid,users.getUsrm_ccno());
			String elementid = getUserLoginDtl.get(0)[0];
			String flidnew = getUserLoginDtl.get(0)[1];
			String level = getUserLoginDtl.get(0)[2];
			String rolename=getUserLoginDtl.get(0)[3];
			 String rolekeyid=getUserLoginDtl.get(0)[4];
			   
			   
			
	      if( action.equals("safteyincident_input.ehsaccIncRpt") ){					
	    	   
	    	  String keyid = request.getParameter(ReqtParamNameConst.KEYID);
			   String bookingMode = request.getParameter("bookingMode");
			   String status = request.getParameter("pending");
			   
			   String mode = request.getParameter("mode");
			   if(UIUtils.isValidKeyId(bookingMode))
			   {
				   request.setAttribute("bookingMode", bookingMode);
			   }
			   String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			   FormModes modes = FormModes.create;
			   SheTlIncidentmstnew sheTlIncidentmst = null;
			   httpSession.removeAttribute("sheTlIncidentMst");
			   if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					CommonFunctions.debugMsg(formMode + "...........");
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						modes =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						CommonFunctions.debugMsg( "INSIDE VIEW MODE");
						modes=FormModes.view;
					}
					sheTlIncidentmst = ehsAccidentIncidentRptingService.select(keyid);
										System.out.println("Status sinam_status"+sheTlIncidentmst.getSinmStatus());
					if( UIUtils.isValidKeyId(status ))
					{
						if(status.equals("actionPlan"))
						{
							request.setAttribute("openPop","actionPlan");
						}
					}
					String occurdate=sheTlIncidentmst.getSinmOccurredDate();
					String preparedate=sheTlIncidentmst.getSinmReportedDate();
					
					String occurtime=occurdate.substring(11,16);
					String prepretime=preparedate.substring(11,16);
					System.out.println("before Occured Date"+sheTlIncidentmst.getSinmOccurredDate()+" Reported "+sheTlIncidentmst.getSinmReportedDate());
					System.out.println("before Occured Time"+occurtime+" Reported "+occurtime);
					//sheTlIncidentmst.setSinmOccurredDate(CommonFunctions.pg_getDateFromPGTimeStamp1(sheTlIncidentmst.getSinmOccurredDate()));
					//sheTlIncidentmst.setSinmReportedDate(CommonFunctions.pg_getDateFromPGTimeStamp1(sheTlIncidentmst.getSinmReportedDate()));
					
					sheTlIncidentmst.setSinmOccurredDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(occurdate));
					sheTlIncidentmst.setSinmReportedDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(preparedate));
					System.out.println("after Occured Date"+sheTlIncidentmst.getSinmOccurredDate()+" Reported "+sheTlIncidentmst.getSinmReportedDate());
					httpSession.setAttribute("sheTlIncidentMst" , sheTlIncidentmst);
					 request.setAttribute("occurtime", occurtime);
					 request.setAttribute("prepretime", prepretime);
			   }
			   else
			   {
				   sheTlIncidentmst = new SheTlIncidentmstnew();
				   String date=CommonFunctions.dateTimeNow();
				    String time=date.substring(11, 17);
				 	System.out.println("date::::"+date+":::time"+time);
				 	System.out.println("Date Condition else"+date);
				   AdmTlUsermst user = UIUtils.getLoginUser(request);
				   SheAccBean sheAccIncBeanCreate  = new SheAccBean();
					/*
					 * sheAccIncBeanCreate.setSinmOccurredtime(time);
					 * sheAccIncBeanCreate.setSinmReportedtime(time);
					 * sheTlIncidentmst.setSinmReportedBy(user.getUsrm_ccno());
					 * request.setAttribute("sheAccIncBean", sheAccIncBeanCreate);
					 */
				   
				   
			   }
			   SheAccBean sheAccIncBean = new SheAccBean(modes);
			   
			   String  filePath= UIUtils.getImagePath(request);					
				try
				{	 
					 System.out.println("Inside image service");
					 String fileName=UIUtils.TPM_TEMPIMG_DIR;
					 sheAccIncBean = ehsAccidentIncidentRptingService.getImage(fileName,filePath,sheAccIncBean,sheTlIncidentmst);
					 
			    }
				catch(Exception e)
				{
					//System.out.println("Exception in while Selecting Image");   
				}
				
			    
					
					
	    	  httpSession.removeAttribute("formMode");
				httpSession.setAttribute("formMode",mode);
			    request.setAttribute("formMode", mode);
			   // request.setAttribute("sheAccIncBean", sheAccIncBean);
			    request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/EHSaccidentIncidentReporting.jsp"); 
				rd.forward(request, response); 
	      }
	      else if( action.equals("SheEmployeeAdd_input.ehsaccIncRpt") ){	
			    String incidentKey = request.getParameter("incdtKeyId");
			    CommonFunctions.debugMsg("Key : "+incidentKey);
			    if(UIUtils.isValidKeyId(incidentKey))
			    	request.setAttribute("incidentId", incidentKey);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/SHEEmployeepopUp.jsp"); 
				rd.forward(request, response); 
				 
		   }
	      else if(action.equals("SheEmployeeAdd_getCol.ehsaccIncRpt")){
			    CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.EHSAccidentIncidentReporting", "colModelEmployeeDtls"));
			    String gridType = request.getParameter("gridType");
			    String popup= request.getParameter("POPUP");
			   // System.out.println("gridType"+gridType);
			  //  System.out.println("popup"+popup);
				PrintWriter out = response.getWriter();
				if(popup!=null)
				{
				if(popup.equals("Y"))
				{
					//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EHSAccidentIncidentReporting", "colModelEmployeeDtls")); 
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EHSAccidentIncidentReporting", gridType)); 
					
				}
				
				}
				else
				{
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EHSAccidentIncidentReporting", gridType)); 
				}
				
	      }
		   else if(action.equals("SheEmployeeAdd_getData.ehsaccIncRpt")){
			   try
				{	
				   PrintWriter out = response.getWriter();
				   SheTlIncidentEmployeenew sheTlIncidentEmployee =new SheTlIncidentEmployeenew();
				   String incidentKey = request.getParameter("incidentKey");
				   String siemEmpmKeyid=request.getParameter("siemEmpmKeyid");
				   String siemInjuryModeid=request.getParameter("siemInjuryModeid");
				   CommonFunctions.debugMsg("Incident Key : "+incidentKey);
				   sheTlIncidentEmployee.setSineSincKeyid(incidentKey);
				   sheTlIncidentEmployee.setSineEmpmKeyid(siemEmpmKeyid);
				   sheTlIncidentEmployee.setSineInjuryModeid(siemInjuryModeid);
				  // sheTlIncidentEmployee.setBflkBachKeyid(bflkBachKeyid);
				   List<String []> employeeList  = new ArrayList<String[]>();
				 //  if(UIUtils.isValidKeyId(sheTlIncidentEmployee.getSineSincKeyid()))
				   if(UIUtils.isValidKeyId(incidentKey))
					   employeeList =  ehsAccidentIncidentRptingService.getIncidentEmployee(sheTlIncidentEmployee);
				   if(employeeList.size()>0)
				   {
					   JSONObject employeesData = UIUtils.convertToJqGridTableObject(employeeList,request,0,0);
					   CommonFunctions.debugMsg(employeesData);
					   out.println(employeesData);
				   }
				
				}catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
		   }
		   else if(action.equals("SheEmployeeAdd_reCall.ehsaccIncRpt"))
		   {
			   String keyId = request.getParameter("keyId");
			   CommonFunctions.debugMsg("KeyId...."+keyId);
			  // System.out.println("Recall function");
				PrintWriter out = response.getWriter();
				SheTlIncidentEmployeenew sheTlIncidentEmployee = ehsAccidentIncidentRptingService.recallEmployee(keyId);
				
			
				//httpSession.setAttribute("newsheTlIncidentEmployee", sheTlIncidentEmployee);
				
				JSONObject employeeData = UIUtils.fromTpmModel(sheTlIncidentEmployee);
				//System.out.println("Recall value:"+employeeData);			
				
				
				JSONObject returndata = new JSONObject();
				returndata.put("employeeData", employeeData);					
				//System.out.println("Data:"+returndata);
				out.print(returndata);
		   }
		   else if( action.equals("safteyincidentanalysis_input.ehsaccIncRpt") ){					
	    	
			   String keyid = request.getParameter(ReqtParamNameConst.KEYID);
			   String bookingMode = request.getParameter("bookingMode");
			   String Inicedentkeyid=request.getParameter("keyid");
			   String status = request.getParameter("pending");
			   SheTlIncidentmstnew newSheTlIncidentmstnew=null;
			   if(UIUtils.isValidKeyId(bookingMode))
			   {
				   request.setAttribute("bookingMode", bookingMode);
			   }
			   String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			   FormModes mode = FormModes.create;
			   SheTlIncidentmstnew sheTlIncidentmst = null;
			   httpSession.removeAttribute("sheTlIncidentMst");
			   if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					CommonFunctions.debugMsg(formMode + "...........");
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						mode =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						CommonFunctions.debugMsg( "INSIDE VIEW MODE");
						mode=FormModes.view;
					}
					sheTlIncidentmst = ehsAccidentIncidentRptingService.select(keyid);
					if( UIUtils.isValidKeyId(status ))
					{
						if(status.equals("actionPlan"))
						{
							request.setAttribute("openPop","actionPlan");
						}
					}
					httpSession.setAttribute("sheTlIncidentMst" , sheTlIncidentmst);
			   }
			   else
			   {
				   sheTlIncidentmst = new SheTlIncidentmstnew();
				   AdmTlUsermst user = UIUtils.getLoginUser(request);
				   sheTlIncidentmst.setSinmReportedBy(user.getUsrm_ccno());
			   }
			   SheAccBean sheAccIncBean = new SheAccBean(mode);
			   
			   String filePath = UIUtils.getImagePath(request);					
				try
				{	 
					 //System.out.println("Inside image service");
					 String fileName=UIUtils.TPM_TEMPIMG_DIR;
					 sheAccIncBean = ehsAccidentIncidentRptingService.getImage(fileName,filePath,sheAccIncBean,sheTlIncidentmst);
					 
			    }
				catch(Exception e){
					//System.out.println("Exception in while Selecting Image");   
				}
				
				if(Inicedentkeyid!=null)
				{
					   newSheTlIncidentmstnew=ehsAccidentIncidentRptingService.select(Inicedentkeyid);
					  
						
				}
				String time=null;
				String date = null;
				
				//newSheTlIncidentmstnew.setSinmAnalysedDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(newSheTlIncidentmstnew.getSinmAnalysedDate()));
				
				if(newSheTlIncidentmstnew.getSinmAnalysedDate().equals("01-Jan-1801"))
				{
					time=newSheTlIncidentmstnew.getSinmAnalysedDate().substring(11,17);
					System.out.println("Date Condition if"+newSheTlIncidentmstnew.getSinmAnalysedDate());
				}
				else{
					
				
				 date=CommonFunctions.dateTimeNow();
			    time=date.substring(11, 17);
			 	System.out.println("date::::"+date+":::time"+time);
			 	System.out.println("Date Condition else"+date);
				}
			
				//String analysedate= newSheTlIncidentmstnew.getSinmAnalysedDate();
				String analysedate= date;
				if(analysedate!=null)
				{
					System.out.println("Date Condition 1"+analysedate);
				String substranalysedate=analysedate.substring(0,11);
				if(!substranalysedate.equals("01-Jan-1801"))
				{
					System.out.println("Date Condition 2"+substranalysedate);
					
					  request.setAttribute("analysedate", substranalysedate);
				}	
				}
				
				System.out.println("Analayzed Date"+newSheTlIncidentmstnew.getSinmAnalysedDate());
				httpSession.removeAttribute("formMode");
				httpSession.setAttribute("formMode",mode);
			    request.setAttribute("formMode", mode);
			    request.setAttribute("time", time);
			   // request.setAttribute("sheAccIncBean", sheAccIncBean);
			    request.setAttribute("KEYID", Inicedentkeyid);
			    request.setAttribute("exctype", newSheTlIncidentmstnew.getSinmExactType());
			    request.setAttribute("extdesc", newSheTlIncidentmstnew.getSinmExactDesc());
			    request.setAttribute("analyseby", newSheTlIncidentmstnew.getSinmAnalysedBy());
			  
			    request.setAttribute("analyseshift", newSheTlIncidentmstnew.getSinmAnalysedShift());
			    request.setAttribute("invest", newSheTlIncidentmstnew.getSinmInvestigation());
			    request.setAttribute("recom", newSheTlIncidentmstnew.getSinmRptrecommendations());
			    request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
			    request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
			    request.setAttribute("area",newSheTlIncidentmstnew.getSinmArea());
			    request.setAttribute("reporteddate",newSheTlIncidentmstnew.getSinmReportedDate());
			    request.setAttribute("accddesc",newSheTlIncidentmstnew.getSinmAccidentDesc());
			    request.setAttribute("flid",newSheTlIncidentmstnew.getSinmFlid());
			    request.setAttribute("rootcause",newSheTlIncidentmstnew.getSinmRootcausedueto());
			    request.setAttribute("rolename",rolename);
			    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/EHSaccidentIncidentAnalysis.jsp"); 
				rd.forward(request, response); 
	      }
	      
   else if( action.equals("IvestigationRpt_input.ehsaccIncRpt") ){
		   
			   
		   }
	      
   else if( action.equals("IvestigationRpt_getCol.ehsaccIncRpt") ){
	   
 	  System.out.println("Inside the GetCol AccImage:::");
   	PrintWriter out = response.getWriter();
 		JSONObject jsonObject = new JSONObject();
 		HttpSession httpsession = request.getSession(false);
 		List<String[]> visualSopGrid = null;
 		String fileName=UIUtils.getImagePath(request);
 		System.out.println("visualsopDetailReportGetCol fileName" + fileName);
 		String keyId = request.getParameter("keyId");
 		System.out.println("The Keyid Is:::"+keyId);
 		try {
		    CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",true);
 			
		    visualSopGrid = ehsAccidentIncidentRptingService.getAllImageDetail(keyId,fileName,commonFilter);
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		jsonObject = getTableModelForAccidentImage(visualSopGrid);
 		httpSession.removeAttribute("IncdntColModel");
 		httpSession.setAttribute("IncdntColModel", jsonObject);
 		httpSession.setAttribute("keyId", keyId);
 		out.println(jsonObject);
	   }
	      
   else if( action.equals("IvestigationRpt_getData.ehsaccIncRpt") ){
	   
 	  try {
 			String fileName=UIUtils.TPM_TEMPIMG_DIR;
 			String keyId = request.getParameter("keyId");
 			//CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",true);
 			List<String[]> visualSopGrid = null;
 			
			    CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",true);
			    FilterValues.getCommonFilters(request, commonFilter);
			    visualSopGrid = ehsAccidentIncidentRptingService.getAllImageDetail(keyId,fileName,commonFilter);
 			PrintWriter out = response.getWriter();
 			JSONObject sopReportGridmod = UIUtils.convertToJqGridTableObject(visualSopGrid, request, 1,0);
 			out.println(sopReportGridmod);
 			} catch (Exception e) {
 				System.out.println(e.getMessage());
 			}
   }



	else if (action.equals("IvestigationRpt_save.ehsaccIncRpt")){
				
			   	saveInvestImage(request,response);	
		
		       
			}
	  else if(action.equals("IvestigationRpt_delete.ehsaccIncRpt")){
		   deleteImage(request,response);
	   }
	      
	      else if( action.equals("safteyincidentInvesReport_input.ehsaccIncRpt") ){					
	    	 
			   String keyid = request.getParameter(ReqtParamNameConst.KEYID);
			   String bookingMode = request.getParameter("bookingMode");
			   String Inicedentkeyid=request.getParameter("keyid");
			   String status = request.getParameter("pending");
			   SheTlIncidentmstnew newSheTlIncidentmstnew=null;
			   if(UIUtils.isValidKeyId(bookingMode))
			   {
				   request.setAttribute("bookingMode", bookingMode);
			   }
			   String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			   FormModes mode = FormModes.create;
			   SheTlIncidentmstnew sheTlIncidentmst = null;
			   httpSession.removeAttribute("sheTlIncidentMst");
			   if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					CommonFunctions.debugMsg(formMode + "...........");
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						mode =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						CommonFunctions.debugMsg( "INSIDE VIEW MODE");
						mode=FormModes.view;
					}
					sheTlIncidentmst = ehsAccidentIncidentRptingService.select(keyid);
					if( UIUtils.isValidKeyId(status ))
					{
						if(status.equals("actionPlan"))
						{
							request.setAttribute("openPop","actionPlan");
						}
					}
					httpSession.setAttribute("sheTlIncidentMst" , sheTlIncidentmst);
			   }
			   else
			   {
				   sheTlIncidentmst = new SheTlIncidentmstnew();
				   AdmTlUsermst user = UIUtils.getLoginUser(request);
				   sheTlIncidentmst.setSinmReportedBy(user.getUsrm_ccno());
			   }
			   SheAccBean sheAccIncBean = new SheAccBean(mode);
			   
			   String filePath = UIUtils.getImagePath(request);					
				try
				{	 
					 System.out.println("Inside image service");
					 String fileName=UIUtils.TPM_TEMPIMG_DIR;
					 sheAccIncBean = ehsAccidentIncidentRptingService.getImage(fileName,filePath,sheAccIncBean,sheTlIncidentmst);
					 
			    }
				catch(Exception e){
					System.out.println("Exception in while Selecting Image");   
				}
				
				if(Inicedentkeyid!=null)
				{
					   newSheTlIncidentmstnew=ehsAccidentIncidentRptingService.select(Inicedentkeyid);
					  
						
				}
				
				String time=null;
				System.out.println("For Investigation Report");
				System.out.println("Analysed date 1"+newSheTlIncidentmstnew.getSinmAnalysedDate());
//				if(newSheTlIncidentmstnew.getSinmAnalysedDate().startsWith("01-Jan-1801"))
//				{
//					time=newSheTlIncidentmstnew.getSinmAnalysedDate().substring(11,17);
//				}
//				else if (newSheTlIncidentmstnew.getSinmAnalysedDate() != null)
//				{
//					newSheTlIncidentmstnew.setSinmAnalysedDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(newSheTlIncidentmstnew.getSinmAnalysedDate()));
//				}
//				else {
//					
//				
//				String date=CommonFunctions.dateTimeNow();
//			    time=date.substring(11, 17);
//			 	System.out.println("date::::"+date+":::time"+time);
//				}
//				System.out.println("Analysed date 2"+newSheTlIncidentmstnew.getSinmAnalysedDate());
//				
//				String analysedate= newSheTlIncidentmstnew.getSinmAnalysedDate();
//				String substranalysedate=analysedate.substring(0,11);
//				if(substranalysedate.startsWith("01-Jan-1801"))
//				{
//					System.out.println("Analysed date 3"+substranalysedate);
//					String date=CommonFunctions.dateTimeNow();
//				    time=date.substring(11, 17);
//				 	System.out.println("date::::"+date+":::time"+time);
//				}	
//				String finalDate = newSheTlIncidentmstnew.getSinmAnalysedDate();
//				 time = finalDate.substring(11,17);
//				 String dateFinal = finalDate.substring(0,11);
//				sheAccIncBean.setSinmAnalysedtime(time);
				newSheTlIncidentmstnew.setSinmAnalysedDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(newSheTlIncidentmstnew.getSinmAnalysedDate()));
				String analysedate = newSheTlIncidentmstnew.getSinmAnalysedDate();
				
				if (analysedate == null || analysedate.startsWith("01-Jan-1801")) {
				    analysedate = CommonFunctions.dateTimeNow();  // current date and time
				}
				
				 time = analysedate.substring(11,17);
				String dateFinal = analysedate.substring(0,11);


				System.out.println("Analysed date 4"+newSheTlIncidentmstnew.getSinmAnalysedDate());
				httpSession.removeAttribute("formMode");
				httpSession.setAttribute("formMode",mode);
			    request.setAttribute("formMode", mode);
			    request.setAttribute("time", time);
			   request.setAttribute("sheAccIncBean", sheAccIncBean);
			    request.setAttribute("KEYID", Inicedentkeyid);
			    request.setAttribute("exctype", newSheTlIncidentmstnew.getSinmExactType());
			    request.setAttribute("extdesc", newSheTlIncidentmstnew.getSinmExactDesc());
			    request.setAttribute("analyseby", newSheTlIncidentmstnew.getSinmAnalysedBy());
                request.setAttribute("analyseshift", newSheTlIncidentmstnew.getSinmAnalysedShift());
			    request.setAttribute("invest", newSheTlIncidentmstnew.getSinmInvestigation());
			    request.setAttribute("recom", newSheTlIncidentmstnew.getSinmRptrecommendations());
			    request.setAttribute("sheTlIncidentMst", sheTlIncidentmst);
			    request.setAttribute("area",newSheTlIncidentmstnew.getSinmArea());
			    request.setAttribute("reporteddate",newSheTlIncidentmstnew.getSinmReportedDate());
			    request.setAttribute("analysedate",dateFinal);
			    request.setAttribute("accddesc",newSheTlIncidentmstnew.getSinmAccidentDesc());
			    request.setAttribute("flid",newSheTlIncidentmstnew.getSinmFlid());
			    request.setAttribute("rootcause",newSheTlIncidentmstnew.getSinmRootcausedueto());
			    request.setAttribute("rolename",rolename);
			    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/EHSAccidentInvestigation.jsp"); 
				rd.forward(request, response); 
	      }
		   
		  
	      else if( action.equals("Accincident_input.ehsaccIncRpt") ){	
			   	String filterStr = request.getParameter("filterString");
				String mode = request.getParameter("mode");
				request.setAttribute("filterStr", filterStr);
				request.setAttribute("formMode", mode);
				httpSession.setAttribute("formMode", mode);
				System.out.println("incidentservlet");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/EHSIncidentReportingMaster.jsp"); 
				rd.forward(request, response); 
		   }
		   else if(action.equals("Accincident_getCol.ehsaccIncRpt")){
			   
			   System.out.println("inside col data");
//				PrintWriter out=response.getWriter();
//				 CommonFilter commonFilter = populateCommonFilter(request,"NewActionPlanViewFilter",true);
//				 System.out.println("get col ");
//				 List<String[]> CommonData=ehsAccidentIncidentRptingService.getAllIncidents(commonFilter);
//				 
//				 JqGridTableModel jqGridTableModel = new JqGridTableModel();
//				 GridColModel gridColModel = new GridColModel();
//				 jqGridTableModel.setRowNumbers(true);
//				 jqGridTableModel.setTableButton(true);
//				 jqGridTableModel.setEnableFilter(true);
//				gridColModel.setHeaderNum(1);
//				String[] colHeader = CommonData.get(2);
//				String[] colHeaderCond = CommonData.get(1);
//				List<String[]> headers = new ArrayList<String[]>();
//				headers.add(colHeader);
//				headers.add(colHeader);
//				JSONObject colModel =new JSONObject();
//				colModel = UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
//				colModel.set("tableHeight", "80%%");
//				colModel.set("tableWidth", "108%%");
//				httpSession.removeAttribute("IncdntColModel");
//				httpSession.setAttribute("IncdntColModel", colModel);
//				httpSession.removeAttribute("NewActionPlanViewFilter");
//				httpSession.setAttribute("NewActionPlanViewFilter", commonFilter);
//			    out.println(colModel);
				PrintWriter out = response.getWriter();
				String mode = request.getParameter("mode");
				if(mode == null) 
				{
					 mode = (String) httpSession.getAttribute("formMode");
				}
				System.out.println(mode+"mode in servlet" );
				httpSession.setAttribute("formMode", mode);
				JSONObject jsonObjectd = null;
				//user
				CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",true);
				commonFilter.setDocType(mode);
				commonFilter.setActionKeyId(loginflid);
				commonFilter.setEmpch(users.getUsrm_ccno());//rolename
				commonFilter.setAcceptenceRequired(rolename);
				
				
				
				if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	  }
				
				commonFilter.setSafetyMode("Create");
				List<String[]> incidentList  = ehsAccidentIncidentRptingService.getAllIncidents( commonFilter);
				
				for (String[] row : incidentList) {
				    System.out.println(Arrays.toString(row));
				}
				
				jsonObjectd = UIUtils.convertToJqGridTableObject(incidentList,request,1,0,commonFilter.getTotalRecordCnt());
				System.out.println("Json table object "+jsonObjectd);
				JSONObject jsonObject = getTableModel(incidentList,mode);
				jsonObject.set("tableHeight", "80%%");
			    jsonObject.set("tableWidth", "106%%");
				httpSession.removeAttribute("IncdntColModel");
		 	    httpSession.setAttribute("IncdntColModel",jsonObject);
		 	   
				out.println(jsonObject);
			    /*CommonFunctions.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", "colModelEmployeeDtls"));
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.AccidentIncidentReporting", "colModelEmployeeDtls"));*/ 
		   }
		   else if(action.equals("Accincident_getData.ehsaccIncRpt")){
			   try
				{	
				   
				   
//				   PrintWriter out = response.getWriter();	
//					CommonFilter commonFilter=populateCommonFilter(request, "IncRptCommonFilter", false);
//					//System.out.println("in  Data"+action);
//					List<String[]> saralViewData =  ehsAccidentIncidentRptingService.getAllIncidents(commonFilter);
//					    JSONObject dataJson = UIUtils.convertToJqGridTableObject(saralViewData,request,3,0,commonFilter.getTotalRecordCnt()+3);
//					out.print(dataJson);
//					commonFilter.setViewClick('Y');
//					httpSession.removeAttribute("IncRptCommonFilter");
//					httpSession.setAttribute("IncRptCommonFilter", commonFilter);
			
				   System.out.println("Inside  Data action");
					PrintWriter out = response.getWriter();
					UIUtils.displayRequestParamsValue(request);
					String page = request.getParameter("page");
					String mode = request.getParameter("mode");
					if(mode == null) 
					{
						 mode = (String) httpSession.getAttribute("formMode");
					}
				System.out.println("mode:::"+mode);
					httpSession = request.getSession();
					CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",false);
					
					commonFilter.setActionKeyId(loginflid);
					commonFilter.setDocType(mode);
					commonFilter.setEmpch(users.getUsrm_ccno());
					commonFilter.setAcceptenceRequired(rolename);
					commonFilter.setSafetyMode("Create");
					JSONObject jsonObject = null;
					System.out.println("test local addr..."+ request.getLocalAddr());
	        		CommonFunctions.debugMsg("Total Count : "+commonFilter.getTotalRecordCnt());
	        		List<String[]> incList  = ehsAccidentIncidentRptingService.getAllIncidents( commonFilter);
	        		CommonFunctions.debugMsg("List Size......"+commonFilter.getTotalRecordCnt());
	        		 jsonObject = UIUtils.convertToJqGridTableObject(incList,request,2,0,commonFilter.getTotalRecordCnt()+1);
	        		 commonFilter.setViewClick('N');	  		
	        		 httpSession.removeAttribute("IncRptCommonFilter");
	 	  			httpSession.setAttribute("IncRptCommonFilter", commonFilter);
					 out.println(jsonObject);
						  			 	
				}
				catch(Exception e)
				{
					CommonFunctions.debugMsg("error " +e.getMessage());
				}
				
		   }
	     
		   else if(action.equals("Accincident_Excelview.ehsaccIncRpt")){
				  try{ 
					  CommonFilter commonFilter = new CommonFilter();	
				    String incidentKeyid=request.getParameter("incKeyid");
				 //  System.out.println("incidentKeyid:::"+incidentKeyid);
				    String flid=request.getParameter("flid");
				  // System.out.println("flid:::"+flid);
				//    String docno=request.getParameter("docno");
				  //  System.out.println("docno:::"+docno);
				    String format = ExcelUtils.getFormat(request);
				    String path = UIUtils.getExcelTemplatePath(request);
					commonFilter = FilterValues.getCommonFilters(request, commonFilter);
					commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
				//	String User=users.getUsrm_ccno();
				    String imagePath = UIUtils.getImagePath(request);
				    Workbook wb = ehsAccidentIncidentRptingService.AccidentExcelView(incidentKeyid,flid,format,path,imagePath,commonFilter);
				    format = ".xlsx";
				  ExcelUtils.writeToResponse(response, wb, "Accident_"+incidentKeyid, format);
					  
//					  
//					  
//					  CommonFilter commonFilter = populateCommonFilter(request,"NewActionPlanViewFilter",true);	
//					  String incidentKeyid=request.getParameter("incKeyid");
//					  String flid=request.getParameter("flid");
//					  
//					  String docno=request.getParameter("docno");
//					  //  System.out.println("docno:::"+docno);
//					    String format = ExcelUtils.getFormat(request);
//					    String path = UIUtils.getExcelTemplatePath(request);
//						commonFilter = FilterValues.getCommonFilters(request, commonFilter);
//						commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
//						commonFilter=populateCommonFilter(request, "", true);
//						String imagePath = UIUtils.getImagePath(request);
//						String tempformrow=commonFilter.getFromRow();
//						commonFilter.setFromRow(null);
//						JSONObject colmodel=(JSONObject)httpSession.getAttribute("NewActionPlanViewColModel");
//						colmodel.put("title", "");
//						String fromat=ExcelUtils.getFormat(request);
//						Workbook wb=ehsAccidentIncidentRptingService.AccidentExcelView(incidentKeyid,flid,format,path,imagePath,commonFilter);
//						commonFilter.setFromRow(tempformrow);
//						ExcelUtils.writeToExcelViewResponse(response, wb, "Daily Report", fromat);
				      
					  
					  
					  
					  
					 }
					catch(Exception e)
					{
						PrintWriter out = response.getWriter();
						JSONObject err = new JSONObject();				
						err.put("message" ,"Data Not Found" );
						out.print(err.toString());
					}
				   
			   }   
		    
	    
	      else if (action.equals("Accincident_getExcel.ehsaccIncRpt")) {
	    	 
	    		        String mode = request.getParameter("mode");
	    		        String empid=users.getUsrm_ccno();
					    CommonFilter commonFilter = populateCommonFilter(request,"IncRptCommonFilter",false);
					    commonFilter.setActionKeyId(loginflid);
						commonFilter.setEmpch(users.getUsrm_ccno());//rolename
						commonFilter.setAcceptenceRequired(rolename);
						String tmpFromRow = commonFilter.getFromRow();
						commonFilter.setFromRow(null);
						JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("IncdntColModel");
						commonFilter.setEmpch(empid);
						CommonFunctions.debugMsg("tblJSONObj"+tblJSONObj);
						//String Titlename="Accident Recording";
						tblJSONObj.put("title","AccidentRecording");
						String format = ExcelUtils.getFormat(request);
						commonFilter.setFromRow(tmpFromRow);
						Workbook wb = ehsAccidentIncidentRptingService.AccidentReportExportExcel(commonFilter,tblJSONObj,format,mode);
						ExcelUtils.writeToResponse(response,wb,"AccidentRecording",format);
	      }
		   else if (action.equals("AccResponsePOP_input.ehsaccIncRpt")) {	
			   String Keyid=request.getParameter("Keyid");
			   SheTlIncidentmstnew newSheTlIncidentmstnew=null;
			   newSheTlIncidentmstnew=ehsAccidentIncidentRptingService.select(Keyid);
			   //System.out.println("rolename:::"+rolename);
			  // System.out.println("Keyid:::"+Keyid);
			   //System.out.println("newSheTlIncidentmstnew.getSinmAccidentType():::"+newSheTlIncidentmstnew.getSinmAccidentType());
				  
				request.setAttribute("Keyid",Keyid);
				request.setAttribute("rolename",rolename);
			    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
				request.setAttribute("Empid",users.getUsrm_ccno());
				request.setAttribute("rolename",rolename);
			    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
				   
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/AccResponsePOP.jsp");
					rd.forward(request, response);
			}
		   else if (action.equals("AccActionPOP_input.ehsaccIncRpt")) {	
			   String Keyid=request.getParameter("Keyid");
			   SheTlIncidentmstnew newSheTlIncidentmstnew=null;
			   newSheTlIncidentmstnew=ehsAccidentIncidentRptingService.select(Keyid);
			   System.out.println("rolename:::"+rolename);
			   System.out.println("Keyid:::"+Keyid);
			   System.out.println("newSheTlIncidentmstnew.getSinmAccidentType():::"+newSheTlIncidentmstnew.getSinmAccidentType());
				  
				  
				request.setAttribute("Keyid",Keyid);
				request.setAttribute("rolename",rolename);
			    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
				request.setAttribute("Empid",users.getUsrm_ccno());
				
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/AccActionPOP.jsp");
					rd.forward(request, response);
			}
		   else if (action.equals("AccVerifyPOP_input.ehsaccIncRpt")) {	
			   String Keyid=request.getParameter("Keyid");
				  
				request.setAttribute("Keyid",Keyid);
				request.setAttribute("Empid",users.getUsrm_ccno());
				
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/AccVerifyPOP.jsp");
					rd.forward(request, response);
			}
		   else if (action.equals("AccClosurePOP_input.ehsaccIncRpt")) {	
			   String Keyid=request.getParameter("Keyid");
				  
				
				SheTlIncidentmstnew newSheTlIncidentmstnew=null;
				   newSheTlIncidentmstnew=ehsAccidentIncidentRptingService.select(Keyid);
					  
					request.setAttribute("Keyid",Keyid);
					request.setAttribute("rolename",rolename);
				    request.setAttribute("type",newSheTlIncidentmstnew.getSinmAccidentType());
					request.setAttribute("Empid",users.getUsrm_ccno());
			   RequestDispatcher rd = request.getRequestDispatcher("/pages/EHSAccident/AccClosurePOP.jsp");
					rd.forward(request, response);
			}
		   else if (action.equals("AccResponsePOP_save.ehsaccIncRpt")) {	
			 
			   SaveResponsibilty(request,response);
	  	   }
		   else if (action.equals("AccActionPOP_save.ehsaccIncRpt")) {	
			   
				 SaveActionClosure(request,response);
		  	   }
		   else if (action.equals("AccVerifyPOP_save.ehsaccIncRpt")) {	
				 SaveVerification(request,response);
		  	   }
		   else if (action.equals("AccClosurePOP_save.ehsaccIncRpt")) {	
				 SaveClosure(request,response);
		  	   }
		   else if(action.equals("SheEmployeeAdd_Delete.ehsaccIncRpt")){
			   SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean=new SheTlIncidentEmployeenewBean();
			   deleteincidentEmployee(request,response,sheTlIncidentEmployeetBean);
		   }
			
	      else if( action.equals("functionalLoc.ehsaccIncRpt"))
			{
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setLocation("cmbsincLocnkeyid");
				functLocFieldNameBean.setFactory("cmbsincFactkeyid");
				functLocFieldNameBean.setSection("cmbsincSectkeyid");
				functLocFieldNameBean.setCell("cmbsincCellkeyid");
				functLocFieldNameBean.setMachine("cmbsincMachkeyid");
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(false);
				
				//= (FormModes)httpSession.getAttribute("formMode");
				FormModes formModes = null ;
				
				/*if( formModes == FormModes.completion)
					formModes = FormModes.view;
				else if(formModes.equals(FormModes.modify))
					formModes = FormModes.view;
				*/
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
			}
	      else if(action.equals("IncidentTypeCombo.ehsaccIncRpt"))
		   {
			   try 
				{
				   	CommonFilter commonFilter=new CommonFilter();
				   	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  DetectedBy = ehsAccidentIncidentRptingService.getIncidentTypeComboList(commonFilter, comboFilter);
					UIUtils.writeComboBox(response, DetectedBy,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
		   } 
	      else if(action.equals("BodyPartCombo.ehsaccIncRpt"))
		   {
			   try 
				{
				   	CommonFilter commonFilter=new CommonFilter();
				   	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  DetectedBy = ehsAccidentIncidentRptingService.getBodyPartComboList(commonFilter, comboFilter);
					UIUtils.writeComboBox(response, DetectedBy,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
		   }
	      else if(action.equals("TypeCombo.ehsaccIncRpt"))
		   {
			   try 
				{
				   	CommonFilter commonFilter=new CommonFilter();
				   	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  DetectedBy = ehsAccidentIncidentRptingService.getTypeComboList(commonFilter, comboFilter);
					UIUtils.writeComboBox(response, DetectedBy,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
		   }
	      else if(action.equals("InjuryCombo.ehsaccIncRpt"))
		   {
			   try 
				{
				   	CommonFilter commonFilter=new CommonFilter();
				   	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  DetectedBy = ehsAccidentIncidentRptingService.getInjuryComboList(commonFilter, comboFilter);
					UIUtils.writeComboBox(response, DetectedBy,comboFilter);
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
		   }
	      
	      else if( action.equals("safteyincidentanalysis_save.ehsaccIncRpt") ){	
	    	   String keyid=request.getParameter("keyid");
	    	  String time=request.getParameter("time");
	    	  String analyse=request.getParameter("analyseby");
	    	  String extype=request.getParameter("extype");
	    	  String exdesc=request.getParameter("exdesc");
	    	  String inv=request.getParameter("inv");
	    	  String rec=request.getParameter("rec");
	    	  String shift=request.getParameter("shift");
	    	  String chkact=request.getParameter("chkact");
	    	  String chkcon=request.getParameter("chkcon");
	    	  String remarks=request.getParameter("rmrks");
	    	  String anlytime=request.getParameter("t1");
	    	  //String timeVal = request.getParameter("analysedTimeVal");
	    	  
	    	  
	    	   String modifytime=time+" "+anlytime;
	    	  
	    	  SheTlIncidentmstnew sheTlIncidentmst=new SheTlIncidentmstnew();
	    	  sheTlIncidentmst.setSinmAnalysedBy(analyse);
	    	  sheTlIncidentmst.setSinmAnalysedDate(modifytime);
	    	  sheTlIncidentmst.setSinmAnalysedShift(shift);
	    	  sheTlIncidentmst.setSinmExactType(extype);
	    	  sheTlIncidentmst.setSinmExactDesc(exdesc);
	    	  sheTlIncidentmst.setSinmInvestigation(inv);
	    	  sheTlIncidentmst.setSinmKeyid(keyid);
	    	  sheTlIncidentmst.setSinmRptrecommendations(rec);
	    	  sheTlIncidentmst.setSinmInvestigationRemarks(remarks);
	    	 // System.out.println("chkact"+chkact+"chkcon"+chkcon);
	    	 if(chkact.equals("N")&&chkcon.equals("N"))
	    	 {
	    		 sheTlIncidentmst.setSinmRootcausedueto("-");
	    	 }
	    	 else{
	    	  if(chkact.equals("Y"))
	    	  {
	    		  
	    		 	 sheTlIncidentmst.setSinmRootcausedueto("A");
	    	  }
	    	  if(chkcon.equals("Y"))
	    	  {
	    		 	 sheTlIncidentmst.setSinmRootcausedueto("C");
	    	  }
	    	 }
	    	 System.out.println("Analysed Date"+sheTlIncidentmst.getSinmAnalysedDate());
	    	  List<String> updatelist=ehsAccidentIncidentRptingService.getUpdateList(sheTlIncidentmst);
	    	  
	    	  
		   }
	      
	      else if( action.equals("safteyincident_save.ehsaccIncRpt"))
			{	
				CommonFunctions.debugMsg("Saveeeeeee");
				String saveMode = request.getParameter("saveMode");
				String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				
				
				
				System.out.println("formMode"+formMode);
				//System.out.println("saveMode"+saveMode);
				
				CommonFunctions.debugMsg("Saveeeeeee : "+formMode);
				SheAccBean sheAccIncBean = new  SheAccBean();
				if(UIUtils.isValidKeyId(saveMode))
					sheAccIncBean.setSaveMode(saveMode);
				if(UIUtils.isValidKeyId(formMode))
					sheAccIncBean.setFormMode(formMode);
				saveAccidentInc(request,response,sheAccIncBean);
				
		    }
	      else if(action.equals("SheEmployeeAdd_Save.ehsaccIncRpt")){
	    	  SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean=new SheTlIncidentEmployeenewBean();
			   CommonFunctions.debugMsg("SheEmployeeAdd_Save.ehsaccIncRpt");
			   saveincidentEmployee(request,response,sheTlIncidentEmployeetBean);
		   }
	      else if(action.equals("safteyincident_delete.ehsaccIncRpt")){
	    	  SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean=new SheTlIncidentEmployeenewBean();
			   Deleteincidentmst(request,response,sheTlIncidentEmployeetBean);
		   }
	      
	      else if(action.equals("safteyincident_email.ehsaccIncRpt")){
	    	  String Keyid=request.getParameter("keyid");
	    	  SheTlIncidentmstnew existSheTlIncidentmst=ehsAccidentIncidentRptingService.select(Keyid);
	    	  if(existSheTlIncidentmst!=null)
	    	  {
	    	  sendAccidentMail( request, response,existSheTlIncidentmst);
	    	  }
	      }
	    	  else if(action.equals(" safteyincident_empcheck.ehsaccIncRpt")){
		    	  String Keyid=request.getParameter("keyid");
		    	  String empcount =ehsAccidentIncidentRptingService.getempcount(Keyid);
		    	
	    	 
	    	  }
	      
		 
	}
	/*private void saveInvestImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List <GenTlAllmoduleimgfile> imagelist =new ArrayList<GenTlAllmoduleimgfile>();
	     String imgname=request.getParameter("imgname");
	     String keyid=request.getParameter("keyid");
		GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
		genTlAllmoduleimgfile =(GenTlAllmoduleimgfile)UIUtils.setBeanProperties((Object)genTlAllmoduleimgfile,request);
		
		
		System.out.println("genTlAllmoduleimgfile.getImflFilename();:::"+genTlAllmoduleimgfile.getImflFilename()+"imgname"+imgname);
		String fileDir=UIUtils.TPM_TEMPIMG_DIR;
		System.out.println("fileDir"+fileDir);
		String count=ehsAccidentIncidentRptingService.getImageCount(keyid);
		int countofImg=Integer.valueOf(count)+1;
		//String convertcount=(String)countofImg;
		System.out.println("countofImg::"+countofImg);
		String imgtype=null;
		if(count!=null)
		{
			imgtype="IN"+String.valueOf(countofImg);
			System.out.println();
		}
	 	String imagePath = UIUtils.getImagePath(request);
	 	genTlAllmoduleimgfile.setImflBlobimage(imagePath );
		genTlAllmoduleimgfile.setImflFilename(imgname);
		genTlAllmoduleimgfile.setImflImagetype(imgtype);
		genTlAllmoduleimgfile.setImflRefdoctype("INC");
		imagelist.add(genTlAllmoduleimgfile);
		commonFilterService.saveImg(imagelist, keyid,"INC" );

	}*/
	
	private void saveInvestImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List <GenTlAllmoduleimgfile> imagelist =new ArrayList<GenTlAllmoduleimgfile>();
	     String imgname=request.getParameter("imgname");
	     String keyid=request.getParameter("keyid");
	     String imagType=request.getParameter("type");
	     System.out.println("The imagType::::"+imagType);
		GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
		genTlAllmoduleimgfile =(GenTlAllmoduleimgfile)UIUtils.setBeanProperties((Object)genTlAllmoduleimgfile,request);
		
		JSONObject sucessData=new JSONObject();
		JSONObject returnData=new JSONObject();
		PrintWriter out=response.getWriter();
		System.out.println("genTlAllmoduleimgfile.getImflFilename();:::"+genTlAllmoduleimgfile.getImflFilename()+"imgname"+imgname);
		String fileDir=UIUtils.TPM_TEMPIMG_DIR;
		System.out.println("fileDir"+fileDir);
		String count=ehsAccidentIncidentRptingService.getImageCount(keyid);
		int countofImg=Integer.valueOf(count)+1;
		//String convertcount=(String)countofImg;
		System.out.println("countofImg::"+countofImg);
		String imgtype=null;
		String savemsg;	
		if(imagType==null||imagType.length()==0)
		{
		if(count!=null)
		{ 
			imgtype="IN"+String.valueOf(countofImg);
			System.out.println();
		}
		String imagePath = UIUtils.getImagePath(request);
	 	genTlAllmoduleimgfile.setImflBlobimage(imagePath );
		genTlAllmoduleimgfile.setImflFilename(imgname);
		genTlAllmoduleimgfile.setImflImagetype(imgtype);
		genTlAllmoduleimgfile.setImflRefdoctype("INC");
		imagelist.add(genTlAllmoduleimgfile);
		commonFilterService.saveImg(imagelist, keyid,"INC" );
		savemsg="Data saved successfully";
		}
		else
		{   
			//String imgdelete=ehsAccidentIncidentRptingService.deleteimage(keyid,imagType);
			//System.out.println("The Image Delete"+imgdelete);
			String imagePath = UIUtils.getImagePath(request);
		 	genTlAllmoduleimgfile.setImflBlobimage(imagePath );
			genTlAllmoduleimgfile.setImflFilename(imgname);
			genTlAllmoduleimgfile.setImflImagetype(imagType);
			genTlAllmoduleimgfile.setImflRefdoctype("INC");
			imagelist.add(genTlAllmoduleimgfile);
			commonFilterService.saveImg(imagelist, keyid,"INC" );
			 savemsg= "Data updated successfully";
		}
		 sucessData.put("msg", savemsg);
    	 returnData.put("successData",sucessData);
    	 returnData.put("formClear",false);
		 out.println(returnData.toString());
	}
	
	private void deleteImage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		 HttpSession httpSession = request.getSession(false);
		 PrintWriter out=response.getWriter();;
	     JSONObject successData=new JSONObject();
   	     JSONObject visualsuccess=new JSONObject();
   	     String savemsg;
		 String imagType=request.getParameter("type");
		 String imgname=request.getParameter("imgname");
		 String keyid=request.getParameter("keyid");
			 
			 String imgdelete=ehsAccidentIncidentRptingService.deleteimage(keyid,imagType);
			 System.out.println("Image Delete"+imgdelete);;
			 savemsg= "Data deleted successfully";
			 successData.put("msg", savemsg);
	    	 visualsuccess.put("successData", successData);
	    	 visualsuccess.put("formClear",false);
	    	 out.print(visualsuccess.toString());
	}
	


	private void Deleteincidentmst(HttpServletRequest request, HttpServletResponse response,
			SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		PrintWriter outdel = response.getWriter();
		String keyid=request.getParameter("keyid");
		if( httpSession != null && user != null)
    	{	
    		SheTlIncidentmstnew existSheTlIncidentmst = (SheTlIncidentmstnew)httpSession.getAttribute("sheTlIncidentmst");
    		SheTlIncidentmstnew newSheTlIncidentmst = new SheTlIncidentmstnew();
    		try{
				String keyId=request.getParameter("keyId");
				newSheTlIncidentmst.setSinmKeyid(keyId);
				System.out.println("keyid val "+keyId);
				SheTlIncidentmstnew delStaus = ehsAccidentIncidentRptingService.delete(newSheTlIncidentmst);
				JSONObject returnData = new JSONObject();					
				//returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
				returnData.put("formClear",true);
				outdel.print(returnData.toString());
				outdel.close();
		}
	
	
	catch (Exception e)
	{
		CommonFunctions.debugMsg("err...."+e.toString());
		PrintWriter  outexdel = response.getWriter();
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"AccidentIncidentReporting");					
						
		outexdel.print("Completed Data Should Not Delete");
		
	}
    	
    	}
		
	}
	private void saveAccidentInc(HttpServletRequest request,HttpServletResponse response, SheAccBean sheAccIncBean) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
    	//ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
    	String status = request.getParameter("status");
    	System.out.println("Status in methid save "+status);
    	SheTlIncidentmstnew  createSheTlIncidentmstnew = null;
    	
    	if( httpSession != null && user != null)
    	{	
    		//SheTlIncidentmstnew existSheTlIncidentmst = (SheTlIncidentmstnew)httpSession.getAttribute("sheTlIncidentmst");
    		SheTlIncidentmstnew existSheTlIncidentmst = new SheTlIncidentmstnew();
    	    
    		SheTlIncidentmstnew newSheTlIncidentmst = new SheTlIncidentmstnew();
    		
    		newSheTlIncidentmst.setSinmCreatedby(user.getUsrm_ccno());
    		
    		newSheTlIncidentmst =(SheTlIncidentmstnew)UIUtils.setBeanProperties((Object)newSheTlIncidentmst,request);
    		sheAccIncBean = (SheAccBean)UIUtils.setBeanProperties((Object)sheAccIncBean,request);
    
    		String imgOplPresentImgFilename= request.getParameter("imgPresentImgFilename");
			String imgOplAfterImgFilename =request.getParameter("imgAfterImgFilename");
			
			String imagePath = UIUtils.getImagePath(request);
			
			//System.out.println("inside the save");
			List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			
			if( imgOplPresentImgFilename != null){
				GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgOplPresentImgFilename);
				genTlAllmoduleimgfile.setImflImagetype("PRE");
				genTlAllmoduleimgfile.setImflRefdoctype("INC");
				imgfileList.add(genTlAllmoduleimgfile);
			//	System.out.println(" genTlAllmoduleimgfile.setToimBlobimage( " + genTlAllmoduleimgfile.getImflBlobimage());
			}
			
			if( imgOplAfterImgFilename != null){
				GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgOplAfterImgFilename);
				genTlAllmoduleimgfile.setImflImagetype("AFT");
				genTlAllmoduleimgfile.setImflRefdoctype("INC");
				imgfileList.add(genTlAllmoduleimgfile);
			}
			
			newSheTlIncidentmst.setAllmoduleimgfile(imgfileList) ;	
			
			
			
			
    		String bookingMode = request.getParameter("hdnBookingMode");
    		
    		try{
    			boolean insert = true;
				if(  newSheTlIncidentmst.getSinmKeyid() == null )
				{	
					CommonFunctions.debugMsg(".....Inside create mode ...");
					existSheTlIncidentmst =	ehsAccidentIncidentRptingService.create(newSheTlIncidentmst,existSheTlIncidentmst,sheAccIncBean);
					createSheTlIncidentmstnew= existSheTlIncidentmst;
					
				}	
				else
				{   CommonFunctions.debugMsg(".....Inside update mode ...");
					/*if(UIUtils.isValidKeyId(bookingMode) && bookingMode.equals("Booking"))
						insert = true;
					else*/
						insert = false;
					//System.out.println("INISDE THE UPDATE");
						if(status != null) {
						if(status.equals("OPEN")) 
						{
							newSheTlIncidentmst.setSinmStatus("P");
							existSheTlIncidentmst.setSinmStatus("P");
						}else 
						{
							newSheTlIncidentmst.setSinmStatus("C");
							existSheTlIncidentmst.setSinmStatus("P");
						}}
					existSheTlIncidentmst = ehsAccidentIncidentRptingService.update(newSheTlIncidentmst,existSheTlIncidentmst,sheAccIncBean);						
				}
			//	System.out.println("OUTSIDE OF THE SAVE");
				httpSession.setAttribute(existSheTlIncidentmst.getSinmKeyid(), existSheTlIncidentmst);
				httpSession.removeAttribute("sheTlIncidentmst");
				httpSession.setAttribute("sheTlIncidentmst", existSheTlIncidentmst);
				
				String formBeanIdentifier = "SheAccIncBean"+sheAccIncBean.getFormActionMode();					
				httpSession.setAttribute(formBeanIdentifier,sheAccIncBean);
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("AccKeyid",existSheTlIncidentmst.getSinmKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject forwardData = new JSONObject();
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				returnData.put("successData", successData);		
				successData.put("keyid",existSheTlIncidentmst.getSinmKeyid());
				CommonFunctions.debugMsg("Save Mode : "+ sheAccIncBean.getSaveMode());
				if(UIUtils.isValidKeyId(sheAccIncBean.getSaveMode()))
				{
					if(sheAccIncBean.getSaveMode().equals("ADDEMP"))
					{
						returnData.put("formClear",false);
						returnData.put("keyid",existSheTlIncidentmst.getSinmKeyid());
						successData.put("keyid",existSheTlIncidentmst.getSinmKeyid());
						returnData.put("displyMsg",false);
						returnData.put("afterSave","openPop");							
					}
					else if(sheAccIncBean.getSaveMode().equals("YY"))
					{
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmKeyid()))
							forwardData.put("cmbwwmsRefdocno",existSheTlIncidentmst.getSinmKeyid());
						//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincOccurredDate()))
							//forwardData.put("dtewwmsDate",existSheTlIncidentmst.getSincOccurredDate());
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmMachKeyid()))
							forwardData.put("cmbwwmsMachineid",existSheTlIncidentmst.getSinmMachKeyid());							
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmFactKeyid()))
							forwardData.put("cmbwwmsFactoryid",existSheTlIncidentmst.getSinmFactKeyid());						
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmCellKeyid()))
							forwardData.put("cmbwwmsCellid",existSheTlIncidentmst.getSinmCellKeyid());
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmSectKeyid()))
							forwardData.put("cmbwwmsSectionid",existSheTlIncidentmst.getSinmSectKeyid());
						
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmCountermeasure()))
							forwardData.put("txtwwmsCountermeasure",existSheTlIncidentmst.getSinmCountermeasure());
						//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincPhenomena()))
							//forwardData.put("cmbwwmsPhenomenaid",existSheTlIncidentmst.getSincPhenomena());							
						
						//if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSincReportedBy()))
							//forwardData.put("cmbwwmsMaintinchargeid",existSheTlIncidentmst.getSincReportedBy());
						
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmWhywhyno()))
							forwardData.put("txtwwmsKeyid",existSheTlIncidentmst.getSinmWhywhyno());
						
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmExactDesc()))
							forwardData.put("txtWwmsAccidentdesc",existSheTlIncidentmst.getSinmExactDesc());
						
						if(UIUtils.isValidKeyId(existSheTlIncidentmst.getSinmPhenomena()))
							forwardData.put("txtWwmsAccidentphen",existSheTlIncidentmst.getSinmPhenomena());
						
						//forwardData.put("txtformType","SFT");
						//persistentData.put("OpenTab", "OpenYY");
						returnData.put("afterSave",sheAccIncBean.getSaveMode());
						//returnData.put("formClear",false);
						successData.put("keyid",existSheTlIncidentmst.getSinmKeyid());
						//returnData.put("keyid",existSheTlIncidentmst.getSinmKeyid());
					}
				}
			
			
				String msgPropertyIdnt;
				 
				if( insert){
					msgPropertyIdnt = "success-save";
				
					if(createSheTlIncidentmstnew.getSinmKeyid()!=null)
	                {
						//Commented the Mail Part for now - 26 Dec 2025
						msgPropertyIdnt = "success-save";
	               	 //sendAccidentMail( request, response,existSheTlIncidentmst);
	                }
					
				}else
				{
					msgPropertyIdnt = "success-update";
				}
				
			//	System.out.println("existSheTlIncidentmst.getSinmKeyid()::::"+existSheTlIncidentmst.getSinmKeyid());
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				//successData.put("mode",sheAccIncBean.getFormMode());
				successData.put("keyid", existSheTlIncidentmst.getSinmKeyid());
				successData.put("glblmsg", glblmsg);
				
			
				//returnData.put("forwardData",forwardData);
				//returnData.put("persistentData", persistentData);
				
								
				CommonFunctions.debugMsg(successData.toString());
				//ServletOutputStream out1 = response.getOutputStream();
				PrintWriter out=response.getWriter();
				out.print(returnData.toString());
				out.close();
			//	System.out.println("OUTSIDE OF THE returndata");
			
			/*	if((formMode!=null)&&!(formMode.equals("View"))){
					ehsAccidentIncidentRptingService.saveImg(existSheTlIncidentmst);
				}
				*/
			
    		}catch(ValidationExceptions e)
			{
    			CommonFunctions.debugMsg(" 1 Catch ");
    			//System.out.println("1 st Catch");
			//	JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "EHSAccidentIncidentReporting");
			//	errMessage.put("fromMode",sheAccIncBean.getFormActionMode());
			//	out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg(" 2 Catch ");
			//	System.out.println("2 nd Catch");
				PrintWriter out=response.getWriter();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "EHSAccidentIncidentReporting");
				errMessage.put("keyid", existSheTlIncidentmst.getSinmKeyid());
				out.print(errMessage.toString());					
				
				
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(" 3 Catch ");
				CommonFunctions.debugMsg(e.getMessage());
			//	System.out.println("3 rd Catch");
				PrintWriter out=response.getWriter();
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("keyid", existSheTlIncidentmst.getSinmKeyid());
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    		
    	}
    }
	

	private void sendAccidentMail(HttpServletRequest request, HttpServletResponse response,
			SheTlIncidentmstnew existSheTlIncidentmst) throws IOException {
		
		// TODO Auto-generated method stub
		String fileName  = "";
		String glbType="";
		String rolekeyid="";
		File f = null;
		try{
			CommonFunctions.debugMsg(" 12 ");
			String momKeyId = existSheTlIncidentmst.getSinmKeyid();
			String flid=existSheTlIncidentmst.getSinmFlid();
			String combotype=existSheTlIncidentmst.getSinmAccidentType();
			String accidentdate=existSheTlIncidentmst.getSinmOccurredDate();
			if(combotype.equals("ATY00006"))
			{
				glbType="FIRST AID";
				//rolekeyid="AROL0006";
			}
			if(combotype.equals("ATY00007"))
			{
				glbType="LOST TIME ACCIDENT";
				//rolekeyid="AROL0077";
			}
			if(combotype.equals("ATY00008"))
			{
				glbType="SERIOUS ACCIDENT";
				//rolekeyid="AROL0077";
			}
			if(combotype.equals("ATY00011"))
			{
				glbType="ENVIRONMENT RELEASE";
				//rolekeyid="AROL0077";
			}
			
			if(combotype.equals("ATY00012"))
			{
				glbType="DANGEROUS OCCURRENCE";
				//rolekeyid="AROL0077";
			}
			if(combotype.equals("ATY00014"))
			{
				glbType="FIRE ACCIDENT MAJOR";
				//rolekeyid="AROL0077";
			}
			
			if(combotype.equals("ATY00010"))
			{
				glbType="FIRE ACCIDENT MINOR";
				//rolekeyid="AROL0077";
			}
			
			if(combotype.equals("ATY00013"))
			{
				glbType="FATAL ACCIDENT";
				//rolekeyid="AROL0077";
			}
			String mType=request.getParameter("mtype");
	
			String format = ExcelUtils.getFormat(request);
			
			
			
		   List<String[]> empMailIds = ehsAccidentIncidentRptingService.getNearMissEmpMailIds(glbType, flid,glbLocation) ;
		  //System.out.println("empMailIds::::"+empMailIds);
		String mailIds = buildToMailIds(empMailIds);
		// System.out.println("mailIds::::"+mailIds);
			CommonFunctions.debugMsg(" mailIds :: 12 "+mailIds);
			if( mailIds.isEmpty())
			{
				JSONObject succssMsg= new JSONObject();
				JSONObject succssData= new JSONObject();
				
				glblmsg="No Valid MailId Found";
				//succssMsg.put("msg", "No Valid MailId Found ");
				//succssMsg.put("keyid",existSheTlIncidentmst.getSinmKeyid());
			
		        ///response.getWriter().print(succssMsg.toString());
		       // response.getWriter().close();
				
				return ;
			}
			

		    format = ".xlsx";
		    CommonFilter commonFilter = new CommonFilter();	
		    String path = UIUtils.getExcelTemplatePath(request);
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
		    String imagePath = UIUtils.getImagePath(request);
		 
		    
		    fileName =this.filePath + "Accident_"+momKeyId + "_" + UIUtils.now() +format;

		Workbook wb = ehsAccidentIncidentRptingService.AccidentExcelView(existSheTlIncidentmst.getSinmKeyid(),existSheTlIncidentmst.getSinmFlid(),path,format,imagePath,commonFilter);

		//	Workbook wb = ehsAccidentIncidentRptingService.AccidentExcelView(existSheTlIncidentmst.getSinmKeyid(),existSheTlIncidentmst.getSinmFlid(),path);
			
			FileOutputStream out = new FileOutputStream( fileName );
		    wb.write(out); 
		    out.close();
		    out = null;
		    wb = null;

		    CommonFunctions.debugMsg(" File Write Completed " );
		    f = new File( fileName);
			
			
			String content = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailContent");
			String disclaimerNote = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailDisclaimer");
			
			StringBuilder subject = new StringBuilder("Accident");
			subject.append(" Accident Report -"+glbType+"-"+momKeyId);
			subject.append(accidentdate);
			
			StringBuilder totalContent = new StringBuilder();
			totalContent.append(content);
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append(disclaimerNote);
			CommonFunctions.debugMsg(" Before sendLotusNotesMail" );
			
			// System.out.println("sendLotusNotesMail::::"+"sendLotusNotesMail");
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			CommonFunctions.debugMsg(" attachmentFiles."+fileName );
			
			attachmentFiles = attachFileManagerFiles(attachmentFiles,momKeyId);
			
			//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,null,subject.toString(),totalContent.toString(),attachmentFiles);
			
			
			//System.out.println(" AFTER sendLotusNotesMail::::"+"sendLotusNotesMail");
			
			UIUtils.delete( f );
			
			
			JSONObject succssMsg= new JSONObject();
			//succssMsg.put("msg", "Mail Sent SuccessFully ");
			glblmsg="Mail Sent SuccessFully";
			//succssMsg.put("keyid",existSheTlIncidentmst.getSinmKeyid());
	      //  response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonFunctions.debugMsg(" sendMomMail Exception" );
			e.printStackTrace();
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			//succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			//succssMsg.put("keyid",existSheTlIncidentmst.getSinmKeyid());
			glblmsg="Mail Not Sent !";
	      // response.getWriter().print(succssMsg.toString());
		}
	}

	private List<String> attachFileManagerFiles(List<String> attachmentFiles, String momKeyId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> docMgrids = ehsAccidentIncidentRptingService.getIncidentReleatedFileManager(momKeyId);
		CommonFunctions.debugMsg(" size 1234 :: "+docMgrids.size());
		for (int i=0;i<docMgrids.size();i++) {
			String fileName = docMgrids.get(i)[0];
			//commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			String ext = fileName.substring(fileName.lastIndexOf('.'),fileName.length());
			CommonFunctions.debugMsg(" Checking for file extension to be correct "+ext); 
		   //DcmTlDocumentmanager dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);		
		   if(UIUtils.isValidKeyId(fileName))
		   {
			   //String fileName = dcmTlDocumentmanager.getDmdmFilename();
			   String path = DOC_ROOT_PATH + docMgrids.get(i)[1];
			   CommonFunctions.debugMsg(" Fourth New path "+path);
			   path = path.replace("/", "\\");
			   CommonFunctions.debugMsg(" filename extension "+path+ext);
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

	private void saveincidentEmployee(HttpServletRequest request, HttpServletResponse response,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean ) throws BusinessApplicationExceptions,IOException{
		
    	
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String incId=request.getParameter("incId");
    	String contractEmployee = request.getParameter("cmbSineContractEmpmKeyid");
    	String contractComp = request.getParameter("txtSineCompany");	  
    	CommonFunctions.debugMsg("incId"+incId);
    	if( httpSession != null && user != null)
    	{	
    		SheTlIncidentEmployeenew newsheTlIncidentEmployee =new SheTlIncidentEmployeenew();
    		
			
    		newsheTlIncidentEmployee =(SheTlIncidentEmployeenew)UIUtils.setBeanProperties((Object)newsheTlIncidentEmployee,request);
    		sheTlIncidentEmployeetBean =(SheTlIncidentEmployeenewBean) UIUtils.setBeanProperties((Object)sheTlIncidentEmployeetBean,request);
    		if(UIUtils.isValidKeyId(contractEmployee))
    			sheTlIncidentEmployeetBean.setContractEmp(contractEmployee.toUpperCase());	
    		if(UIUtils.isValidKeyId(contractComp))
    			sheTlIncidentEmployeetBean.setContractComp(contractComp.toUpperCase());	 
    		SheTlIncidentEmployeenew existSheTlIncidentEmployee = (SheTlIncidentEmployeenew)httpSession.getAttribute("EntBatchFacultyCreationServlet"); 
			
			try{
					boolean insert = true;
					if( newsheTlIncidentEmployee.getSineKeyid() == null )
					{
						newsheTlIncidentEmployee.setSineSincKeyid(incId);
						existSheTlIncidentEmployee = ehsAccidentIncidentRptingService.create(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
					}
					else{
						insert = false;
						existSheTlIncidentEmployee = ehsAccidentIncidentRptingService.update(newsheTlIncidentEmployee,existSheTlIncidentEmployee,sheTlIncidentEmployeetBean);
						
					}
					new JSONObject();
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("batchFacultyid",existSheTlIncidentEmployee.getSineKeyid());
					
					
				    String msgPropertyIdnt;				
				 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						 msgPropertyIdnt = "success-update";
					 JSONObject successData = new JSONObject(); 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",sheTlIncidentEmployeetBean.getFormActionMode() );
					successData.put("keyId", existSheTlIncidentEmployee.getSineKeyid());
					JSONObject returnData = new JSONObject();	
					if(UIUtils.isValidKeyId(existSheTlIncidentEmployee.getSineSincKeyid()))
						returnData.put("IncidentKey", existSheTlIncidentEmployee.getSineSincKeyid());
					returnData.put("formClear",false);
					returnData.put("successData", successData);
						
					//httpSession.removeAttribute("EntEmpMangerLinkServlet");
					httpSession.setAttribute( "EntBatchFacultyCreationServlet", existSheTlIncidentEmployee);
					//httpSession.removeAttribute("genTlCompanymstBean");
				//	System.out.println(" after save  3");
					out.print(returnData.toString());
					out.close();
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg(" Inside BusinessApplication Exception ");
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "EHSAccidentIncedientEmployee");
				out.print(errMessage.toString());					
			}			
			catch(ValidationExceptions e)
			{
			//	System.out.println("e.yguiyg " + e.getMessage());
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EHSAccidentIncedientEmployee");
				errMessage.put("fromMode",sheTlIncidentEmployeetBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch (Exception e)
			{
			    e.printStackTrace();   // ⭐ THIS IS CRITICAL

			    CommonFunctions.debugMsg("Exception in saveincidentEmployee : " + e.getMessage());

			    JSONObject err = new JSONObject();
			    err.put("tpmException", "Data Not Saved");
			    out.print(err.toString());
			}

			
	    }	
	}
	 private void deleteincidentEmployee(HttpServletRequest request, HttpServletResponse response,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws Exception{
			HttpSession httpSession = request.getSession(false);
			PrintWriter outdel = response.getWriter();
			//ServletOutputStream outdel = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);

			if( httpSession != null && user != null)
			{	
				
				//String BatchFacultyId = request.getParameter("BatchFacultyId");
				
				String rowId=request.getParameter("rowId");
				SheTlIncidentEmployeenew sheTlIncidentEmployee =new SheTlIncidentEmployeenew();
				sheTlIncidentEmployee =(SheTlIncidentEmployeenew)UIUtils.setBeanProperties((Object)sheTlIncidentEmployee,request);
				httpSession.getAttribute("EntBatchFacultyCreationServlet");
				
				try{
							String incEmpId=request.getParameter("incEmpId");
							sheTlIncidentEmployee.setSineKeyid(incEmpId);
							CommonFunctions.debugMsg("sheTlIncidentEmployee"+sheTlIncidentEmployee.getSineKeyid());
							SheTlIncidentEmployeenew delStaus = ehsAccidentIncidentRptingService.delete(sheTlIncidentEmployee);					
							JSONObject returnData = new JSONObject();					
							//returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
							returnData.put("formClear",true);
							returnData.put("rowId",request.getParameter("rowId"));
							outdel.print(returnData.toString());
							outdel.close();
					}
				
				
				catch(ValidationExceptions e)
				{
					PrintWriter  outexdel = response.getWriter();
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
					
					errMessage.put("formActionMode",sheTlIncidentEmployeetBean.getFormActionMode());				
					outexdel.print(errMessage.toString());
					
				}
				
			}
		}
private void SaveResponsibilty(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		
	ServletOutputStream out = response.getOutputStream();
		String keyid=request.getParameter("Keyid");
		String responsevalue=request.getParameter("responsevalue");	
		String targetdate=request.getParameter("targetdate");
		 String remarks=request.getParameter("rmrks");
		
		
		SheTlIncidentmstnew sheTlIncidentmstnew= new SheTlIncidentmstnew();
		sheTlIncidentmstnew.setSinmKeyid(keyid);
		sheTlIncidentmstnew.setSinmResponsibilityRemarks(remarks);
		//System.out.println("responsevalue::::"+responsevalue);
		String resultsupdate = ehsAccidentIncidentRptingService.singleResponsiblty(keyid,responsevalue,targetdate,sheTlIncidentmstnew);
	
		JSONObject resval = new JSONObject();
		resval.put("resval", resultsupdate);
		//out.print(resval.toString());
		SheTlIncidentmstnew sheTlIncidentmst = ehsAccidentIncidentRptingService.select(keyid);
	    GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
		GenTlActionplanmst existgenTlActionplanmst =new GenTlActionplanmst();
		GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
		genTlActionplanmst.setAplmCreatedby(sheTlIncidentmst.getSinmCreatedby());
		genTlActionplanmst.setAplmDetailrefid(keyid);
		//genTlActionplanmst.setAplmKeyid(aplmkeyid);
		genTlActionplanmst.setAplmFlid(sheTlIncidentmst.getSinmFlid());
		genTlActionplanmst.setAplmMasterrefid(keyid);
		genTlActionplanmst.setAplmMaintask(sheTlIncidentmst.getSinmAccidentDesc());
		genTlActionplanmst.setAplmRefdoctype("IR");
		genTlActionplanmst.setAplmStatus("P");
		genTlActionplandtl.setApldResponsibility(responsevalue);
		genTlActionplandtl.setApldStatus("P");
		//genTlActionplandtl.setApldKeyid(apldkeyid);
		genTlActionplandtl.setApldTargetdate(targetdate);
		genTlActionplandtl.setApldActionplan(sheTlIncidentmst.getSinmAccidentDesc());
		
		existgenTlActionplanmst=genTlActionplanmst;
		actionPlanRptService.create(genTlActionplanmst, genTlActionplandtl);
		
		
	}
private void SaveActionClosure(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
	
	ServletOutputStream out = response.getOutputStream();
	String keyid=request.getParameter("Keyid");
	String responsevalue=request.getParameter("responsevalue");	
	String targetdate=request.getParameter("targetdate");
	String reviseddate=request.getParameter("reviseddate");	
	String status=request.getParameter("status");
	String correctiveaction=request.getParameter("correctiveaction");
	 String remarks=request.getParameter("rmrks");
	
	SheTlIncidentmstnew sheTlIncidentmstnew= new SheTlIncidentmstnew();
	sheTlIncidentmstnew.setSinmKeyid(keyid);
	sheTlIncidentmstnew.setSinmCompletedRemarks(remarks);
	String resultsupdate = ehsAccidentIncidentRptingService.ActionClosure(keyid,responsevalue,targetdate,reviseddate,status,correctiveaction,sheTlIncidentmstnew);
	
	JSONObject resval = new JSONObject();
	resval.put("resval", resultsupdate);
	
	  List<String []> getActionkeyid= ehsAccidentIncidentRptingService.getActionPlankeyid(keyid);
	  String Actionplanmstid=null;
	  String Actionplandtlid =null;
	if(getActionkeyid!=null&&getActionkeyid.size()!=0)
	{
	   Actionplanmstid = getActionkeyid.get(0)[0];
		 Actionplandtlid = getActionkeyid.get(0)[1];
	}
	
	//if(Actionplanmstid==null&&Actionplandtlid==null)
	//  {
		if(responsevalue!=null&&targetdate!=null)
		{  
	  
	
	GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
	GenTlActionplanmst existgenTlActionplanmst =new GenTlActionplanmst();
	GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
	SheTlIncidentmstnew sheTlIncidentmst = ehsAccidentIncidentRptingService.select(keyid);
	genTlActionplanmst.setAplmCreatedby(sheTlIncidentmst.getSinmCreatedby());
	genTlActionplanmst.setAplmDetailrefid(keyid);
	genTlActionplanmst.setAplmKeyid(Actionplanmstid);
	genTlActionplanmst.setAplmFlid(sheTlIncidentmst.getSinmFlid());
	genTlActionplanmst.setAplmMasterrefid(keyid);
	genTlActionplanmst.setAplmMaintask(sheTlIncidentmst.getSinmAccidentDesc());
	genTlActionplanmst.setAplmRefdoctype("IR");
	genTlActionplanmst.setAplmStatus("C");
	genTlActionplandtl.setApldResponsibility(responsevalue);
	genTlActionplandtl.setApldStatus("C");
	genTlActionplandtl.setApldKeyid(Actionplandtlid);
	genTlActionplandtl.setApldTargetdate(targetdate);
	genTlActionplandtl.setApldActionplan(sheTlIncidentmst.getSinmAccidentDesc());
	genTlActionplandtl.setApldCompletedby(responsevalue);
	genTlActionplandtl.setApldCompleatedon(targetdate);
	
	
	existgenTlActionplanmst=genTlActionplanmst;
	actionPlanRptService.update(genTlActionplanmst,existgenTlActionplanmst ,genTlActionplandtl);
	///out.print(resval.toString());
	}
	
//	}
}
private void SaveVerification(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
	
	ServletOutputStream out = response.getOutputStream();
	String keyid=request.getParameter("Keyid");
	String responsevalue=request.getParameter("responsevalue");	
	String targetdate=request.getParameter("targetdate");
	//String reviseddate=request.getParameter("reviseddate");	
	String verifystatus=request.getParameter("verifystatus");
	String remarks=request.getParameter("rmrks");
	//String correctiveaction=request.getParameter("correctiveaction");
	//responsevalue="+responsevalue+"&targetdate="+targetdate+"&Keyid="+KEYID+"&verifystatus="+verifystatus);
	SheTlIncidentmstnew sheTlIncidentmstnew= new SheTlIncidentmstnew();
	sheTlIncidentmstnew.setSinmKeyid(keyid);
	sheTlIncidentmstnew.setSinmVerifiedRemarks(remarks);
	String resultsupdate = ehsAccidentIncidentRptingService.ActionVerification(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
	
	JSONObject resval = new JSONObject();
	resval.put("resval", resultsupdate);
//	out.print(resval.toString());
}

private void SaveClosure(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
	
	ServletOutputStream out = response.getOutputStream();
	String keyid=request.getParameter("Keyid");
	String responsevalue=request.getParameter("responsevalue");	
	String targetdate=request.getParameter("targetdate");
	//String reviseddate=request.getParameter("reviseddate");	
	String verifystatus=request.getParameter("verifystatus");
	String remarks=request.getParameter("rmrks");
	
	SheTlIncidentmstnew sheTlIncidentmstnew= new SheTlIncidentmstnew();
	sheTlIncidentmstnew.setSinmKeyid(keyid);
	sheTlIncidentmstnew.setSinmApprovalRemarks(remarks);
	String resultsupdate = ehsAccidentIncidentRptingService.saveClosure(keyid,responsevalue,targetdate,verifystatus,sheTlIncidentmstnew);
	
	//JSONObject resval = new JSONObject();
	//resval.put("resval", resultsupdate);
	//out.print(resval.toString());
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
			commonFilter = 	FilterValues.getSafty(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	
	private JSONObject getTableModel(List<String[]> headers, String mode)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(1);
		String[] row1 = headers.get(1);
		jqGridTableModel.getRowHeaders().add(row1);	
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
	//	System.out.println("MODE"+mode);
		if(mode.equals("Modify")||mode.equals("Delete")||mode.equals("View"))
		{
		jqGridTableModel.setMultiSelect(false);
		}
		else
		{
			jqGridTableModel.setMultiSelect(true);
		}
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(row[i].replaceAll(" ", ""));
			jqGridColModel.setEditable(false);
			//CommonFunctions.debugMsg(i + " : "+row[i]);
			if(i==0 || i==row.length-1)
				jqGridColModel.setHidden(true);
			/* else if(i==1 || i==25||i==2||i==6||i==7)
				jqGridColModel.setWidth(80);
			
			else if(i==3 || i==4 || i==19 || i==23)
			{
				 jqGridColModel.setWidth(100);
				 jqGridColModel.setAlign("center");
			}
			else if(i==9 || i==24)
				jqGridColModel.setWidth(300);
			else if(i==10 || i==11)
			{
				jqGridColModel.setWidth(80);
			}
			else if(i==11 || i==21 || i==22)
				jqGridColModel.setWidth(200);
			else if(i>15 && i<20)
			{
				if(i==16)
					jqGridColModel.setWidth(500);
				else 
					jqGridColModel.setWidth(350);
			}				
			else if(i==20 || (i>25 && i<28) || i==29)
			{
				if(i==20)
					jqGridColModel.setWidth(500);
				else
					jqGridColModel.setWidth(350);
			}*/			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		
		CommonFunctions.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	
	
	private JSONObject getTableModelForAccidentImage(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		System.out.println("column Header::::"+colHeader[0]);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(50);
		jqGridTableModel.setTableWidth(150);
	/*	jqGridTableModel.setTableButton(false);
		jqGridTableModel.setEnableFilter(false);*/
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
		   	jqGridColModel.setWidth(100);
		   	jqGridColModel.setEditable(false);
		   	
		   	if(i==0){
				jqGridColModel.setHidden(true);
			}
			if(i==1 ){
				jqGridColModel.setHidden(true);
				jqGridColModel.setWidth(300);
				//jqGridColModel.setFormatter("txtFormatterimg");
			}
		
			if(i==2){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(300);
				//jqGridColModel.setFormatter("txtFormatterimg");
				jqGridColModel.setFormatter("txtFormatterimg");
			}
			/*if(i==3){
				jqGridColModel.setHidden(false);
				jqGridColModel.setWidth(100);
				jqGridColModel.setFormatter("txtFormatterimg");
			}*/
			
			
			jqGridColModel.setAlign("center");
			//jqGridColModel.setEditable(false);
			System.out.println("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth", "60%%");
		return tableModel;
}

}
