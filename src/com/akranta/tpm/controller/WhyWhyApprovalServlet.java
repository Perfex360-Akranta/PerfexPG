package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
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
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.KaizenServices; 
import com.akranta.tpm.service.WhyWhyAnalysisService;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.impl.KaizenServiceImpl;
import com.akranta.tpm.service.WhyWhyApprovalService;
import com.akranta.tpm.service.impl.WhyWhyApprovalServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.service.api.WhywhyServiceApi;

import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;

/**
 * Servlet implementation class WhyWhyApprovalServlet
 */

public class WhyWhyApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	WhyWhyApprovalService whywhyAppService;
	WhyWhyAnalysisService yyService;
	WhywhyReportService whywhyService;
	WhywhyServiceApi whywhyServiceApi;


	KaizenServices  kaizenServices ;

    public WhyWhyApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    String  filePath = null;
	private static String DOC_ROOT_PATH;
	private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
	
	//private ServletRequest httpSession;
	/*public void init(ServletConfig config) throws ServletException{
		filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
	}*/
	
	
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
       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        CommonMessage.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        CommonMessage.debugMsg(" FileManagerBasePath " + basePath );
        
        boolean pathExist = new File( basePath).exists();
        CommonMessage.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
        if( basePath != null && pathExist ){
        	DOC_ROOT_PATH = basePath;
        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
        	
        }
        CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
        CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
    	}catch(Exception e ){
    		e.printStackTrace();
    		CommonMessage.debugMsg(" File Manager exception " + e.getMessage());
    	}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void process(HttpServletRequest request, HttpServletResponse response) throws NoDataFoundException, Exception 
	{
		
			String action = UIUtils.getActionPart(request);
			AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);

			try {
				HttpSession httpSession = request.getSession(false);

				whywhyAppService = (WhyWhyApprovalServiceImpl)UIUtils.getServiceObject(request,"WhyWhyApprovalServiceImpl");
				kaizenServices =(KaizenServiceImpl)UIUtils.getServiceObject(request,"KaizenServiceImpl"); ;
				whywhyService =(WhywhyReportServiceImpl)UIUtils.getServiceObject(request,"WhywhyReportServiceImpl"); ;
				yyService =(WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request,"WhyWhyAnalysisServiceImpl"); ; 
				whywhyAppService.WhywhyApprovalServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
				

				CommonMessage.debugMsg("workOrderService completed ");
			} 
			catch (ServiceObjectCreationException e) {
				//CommonMessage.debugMsg(e);
			}
			if(action.equals("whywhyanalysisApproval_input.whyApr")){
				CommonMessage.debugMsg("action "+ action); 
				HttpSession httpSession= request.getSession(false);
				String loginuser = UIUtils.getLoginUser(request).getUsrm_username();
				 String loginflid=CommonFunctions.getLoginFlid(request);
		  		 String loginlevel=CommonFunctions.getLoginLevel(request);
		  		 String loginElementid = (String) httpSession.getAttribute("loginElementid");
				 String empId = null;
			//	AdmTlUsermst usersdetails = UIUtils.getLoginUser(request);
				empId =  usersdetails.getUsrm_ccno();
				AdmTlUserRoleLink newAdmTlUserRoleLink = new AdmTlUserRoleLink();
				String newRole=newAdmTlUserRoleLink.getArulRoleid();
				//ComboFilter currentFilter =null;
				List<String []> getUserLoginDtl= kaizenServices.getElementId(loginflid,loginlevel, loginElementid,empId);
				String elementid = getUserLoginDtl.get(0)[0];
				String fnln = getUserLoginDtl.get(0)[1];
				String level = getUserLoginDtl.get(0)[2];
				String rolename=getUserLoginDtl.get(0)[3];
				String rolekeyid=getUserLoginDtl.get(0)[4];
				CommonMessage.debugMsg("rolename:"+rolename);					
				request.setAttribute(" empId", request.getParameter("empId"));
				CommonMessage.debugMsg(" dmc input work flow empId="+ request.getParameter("empId"));
				request.setAttribute("refId", request.getParameter("refId"));
				CommonMessage.debugMsg("dmc input work flow refId="+ request.getParameter("refId"));
				request.setAttribute("refType", request.getParameter("refType"));
				CommonMessage.debugMsg("dmc input work flow refType="+ request.getParameter("refType"));
				request.setAttribute("refRoleId", request.getParameter("refRoleId"));
				CommonMessage.debugMsg("dmc input work flow refRoleId="+ request.getParameter("refRoleId"));
				request.setAttribute("transCode", request.getParameter("transCode"));
				CommonMessage.debugMsg(elementid+" dmc input work flow transCode="+ request.getParameter("transCode")); 
	    	   
				String filterString = request.getParameter("filterString");
				//CommonMessage.debugMsg("KaizenApprovalGrid.........."+filterString);
				//String location=elementid.substring(11, 21);
			//	CommonMessage.debugMsg(location+" dmc input work flow transCode="+ request.getParameter("transCode")); 

				request.setAttribute("mode", "APPROVAL");
				request.setAttribute("filterStr", filterString);
				request.setAttribute("loginuser",loginuser);
				request.setAttribute("rolename",rolename);
				request.setAttribute("rolekeyid",rolekeyid);
				UIUtils.forwardRequest(request, response, "/pages/WhyWhyApprovalMainForm.jsp");
			}
			else if(action.equals("whywhyanalysisApproval_getCol.whyApr")){
				PrintWriter out = response.getWriter();
				HttpSession httpSession = request.getSession(false);

				try {   
					   String type = request.getParameter("type");
					   String	empId =  usersdetails.getUsrm_ccno();

					   String roleId=request.getParameter("roleKeyid");
					  // JSONObject jsonObject = new JSONObject();
					   CommonFilter commonFilter = populateCommonFilter(request,"whywhyanalysisCommonFilter",true);
					   commonFilter.setIsGetCol("Y");
					   
					   List<String[]> approvalList =  whywhyAppService.getAllWhywhyApproval(commonFilter,roleId,empId);									

					    /*commonFilter.setEmpch(empId); 
					    commonFilter.setRoleLevel(roleId);*/
						//jsonObject = getTableModelApprovalList(approvalList);
						
						JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();
						
						jqGridTableModel.setRowNumbers(true);
						jqGridTableModel.setEnableFilter(true);
					//	jqGridTableModel.setGridEdit(true);
						List<String> formattorFromList =  new ArrayList<String>();
						List<String> formattorToList =  new ArrayList<String>();
						List<String> formattorList =  new ArrayList<String>();
						
//						formattorList.add("chkFormatter");
//						formattorList.add("btnSaveFormatter");
//						formattorList.add("cmbStsFormatter");
//						formattorList.add("txtRmrkFormatter");
//						formattorList.add("btnShowFormatter");
						
						if("AI".equals(commonFilter.getStatus())) {
							formattorList.add("chkFormatter");
							formattorList.add("btnSaveAIFormatter");
							formattorList.add("cmbStsFormatter");
							formattorList.add("txtRmrkFormatter");
							formattorList.add("cmbCobdFormatter");
							
							formattorList.add("txtValueFormatter");
							formattorList.add("txtHoursFormatter");
							
						}else {
							formattorList.add("chkFormatter");
							formattorList.add("btnSavePCFormatter");
							formattorList.add("cmbStsFormatter");
							formattorList.add("txtRmrkFormatter");
						}
						
						formattorList.add("btnShowFormatter");
						
//						formattorFromList.add("0");
//						formattorFromList.add("3"); 
//						formattorFromList.add("4"); 
//						formattorFromList.add("5"); 
//						formattorFromList.add("6");
//						formattorFromList.add("7");
//						formattorFromList.add("8");
//						formattorFromList.add("10"); 
						//formattorFromList.add(String.valueOf(colHeader1.length-1));
						
						if("AI".equals(commonFilter.getStatus())) {
							formattorFromList.add("0");
							formattorFromList.add("3"); 
							formattorFromList.add("4"); 
							formattorFromList.add("5"); 
							formattorFromList.add("6");
							formattorFromList.add("7");
							formattorFromList.add("8");
							formattorFromList.add("10");
							
						}else {
							formattorFromList.add("0");
							formattorFromList.add("3"); 
							formattorFromList.add("4"); 
							formattorFromList.add("5"); 
						
							formattorFromList.add("7");
							
						}
						
//						formattorToList.add(String.valueOf("0"));
//						formattorToList.add(String.valueOf("3"));
//						formattorToList.add(String.valueOf("4"));
//						formattorToList.add(String.valueOf("5"));
//						formattorToList.add(String.valueOf("6"));
//						formattorToList.add(String.valueOf("7"));
//						formattorToList.add(String.valueOf("8"));
//						formattorToList.add(String.valueOf("10"));
						if("AI".equals(commonFilter.getStatus())) {
							formattorToList.add(String.valueOf("0"));
							formattorToList.add(String.valueOf("3"));
							formattorToList.add(String.valueOf("4"));
							formattorToList.add(String.valueOf("5"));
							formattorToList.add(String.valueOf("6"));
							formattorToList.add(String.valueOf("7"));
							formattorToList.add(String.valueOf("8"));
							formattorToList.add(String.valueOf("10"));
							
						}else {
							formattorToList.add(String.valueOf("0"));
							formattorToList.add(String.valueOf("3"));
							formattorToList.add(String.valueOf("4"));
							formattorToList.add(String.valueOf("5"));
							
							formattorToList.add(String.valueOf("7"));
							
							
						}
						
						
						gridColModel.setMultiformatter(formattorList);
						gridColModel.setMultiformattorFromCol(formattorFromList);
						gridColModel.setMultiformattorToCol(formattorToList);
				
						gridColModel.setHeaderNum(1);
						
						String [] colHeader = approvalList.get(1);			
						String [] colHeaderCond = approvalList.get(0);
						List<String[]> headers = new ArrayList<String[]>();
						//headers.add(colHeaderCond);

						headers.add(colHeader);
						
						JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
						jsonObject.put("tableHeight", "90%%");
						jsonObject.put("tableWidth", "109%%");
						httpSession.setAttribute("ColModel", jsonObject);
						httpSession.setAttribute("whywhyanalysisCommonFilter", commonFilter);
						out.println(jsonObject);	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		/*	//	String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.WhyWhyApproval", "WhyWhy_app_grid");
				PrintWriter out= response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.WhyWhyApproval", "WhyWhy_app_grid"));
			
				//response.getWriter().print(colModel);
			//	CommonMessage.debugMsg(colModel +" colModelcolModelcolModelcolModel");
				//response.getWriter().close();
*/						}
			else if(action.equals("whywhyanalysisApproval_getData.whyApr")){
				try {
					CommonMessage.debugMsg(" In Side the getData ");
					 String roleId=request.getParameter("roleKeyid");
					HttpSession httpSession = request.getSession(false);
					String recDocId = request.getParameter("refdocid");	
					CommonFilter commonFilter =  populateCommonFilter(request,"whywhyanalysisCommonFilter",false);
					String	empId =  usersdetails.getUsrm_ccno();

				/*    commonFilter.setEmpch(empId);
				    commonFilter.setRoleLevel(roleId);*/

					if(UIUtils.isValidKeyId(recDocId))
						commonFilter.setRefdocid(recDocId);
					commonFilter.setIsGetCol("N");
					
					  List<String []> WhyReportList  =  whywhyAppService.getAllWhywhyApproval(commonFilter,roleId,empId);
					PrintWriter out = response.getWriter();
					
					JSONObject Whymaster = UIUtils.convertToJqGridTableObject(WhyReportList, request, 2, 0,commonFilter.getTotalRecordCnt());
					httpSession.removeAttribute("whywhyanalysisCommonFilter");
					out.println(Whymaster );
					CommonMessage.debugMsg(Whymaster);
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			/*if(action.equals("whyWhyApproval_save.whyApr")){
				
			
				String keyId=request.getParameter("keyId");
				String status=request.getParameter("status");
				String remark=request.getParameter("remark");
				String role=request.getParameter("userRole");
				String roleKeyid=request.getParameter("roleKeyid");
				String approvedBy = UIUtils.getLoginUser(request).getUsrm_ccno(); 
				String dateTime = CommonFunctions.dateTimeNow();
					HttpSession httpSession = request.getSession(false);
			    	ServletOutputStream out = response.getOutputStream();
			    	AdmTlUsermst user = UIUtils.getLoginUser(request);
			    	JSONObject successData = new JSONObject();
			       	if( httpSession != null && user != null)
			       	{
			       		BdmTlWhywhymst existBdmTlWhywhymst = new BdmTlWhywhymst();

			    		BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();     		
			    		newBdmTlWhywhymst.setWwmsApprovedBy(user.getUsrm_ccno()); 
			    		newBdmTlWhywhymst.setWwmsApprRoleid(roleKeyid);
			    		newBdmTlWhywhymst.setWwmsAppRemarks(remark);
			    		newBdmTlWhywhymst.setWwmsAppStatus(status);
			    		newBdmTlWhywhymst.setWwmsApprvedOn(dateTime);
			    		newBdmTlWhywhymst =(BdmTlWhywhymst)UIUtils.setBeanProperties((Object)newBdmTlWhywhymst,request);
			    			
			    		try{				    
			    			if(UIUtils.isValidKeyId (newBdmTlWhywhymst.getWwmsKeyid() )  )
							{	
								
								CommonMessage.debugMsg("update");
								existBdmTlWhywhymst =	whywhyAppService.update(newBdmTlWhywhymst,existBdmTlWhywhymst);						
							
							}
			    	
							JSONObject persistentData = new JSONObject();  
							persistentData.put("keyId",existBdmTlWhywhymst.getWwmsKeyid());
							//existBdmTlWhywhymst.getWwmsFormtype()
							JSONObject forwardData = new JSONObject();
							
						
							CommonMessage.debugMsg(" Form Mode");
							//httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
							successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
							successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
							JSONObject returnData = new JSONObject();
							returnData.put("formClear",false);
							returnData.put("forwardData",forwardData);
							returnData.put("persistentData", persistentData);
							returnData.put("successData", successData);		
							CommonMessage.debugMsg(" returnData.toString() "+returnData.toString());
							out.print(returnData.toString());
			       	
			    		}
			       	}  	
			       	
			
		 catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			CommonMessage.debugMsg(" In sid ethe Finally");
		}
	}*/
			else	if (action.equals("whyWhyApprovalAI_save.whyApr")) {
			    HttpSession httpSession = request.getSession(false);
			    ServletOutputStream out = null;
			    try {
			        response.setContentType("application/json");
			        
			        String keyId = request.getParameter("keyId");
			        String status = request.getParameter("status");
			        String remark = request.getParameter("remark");
			        String isCobd = request.getParameter("isCobd");
			        String cobdValue = request.getParameter("cobdValue");
			        String cobdHours = request.getParameter("cobdHours");
			        String role = request.getParameter("userRole");
			        String roleKeyid = request.getParameter("roleKeyid");
			        CommonMessage.debugMsg(" keyId "+keyId +" status "+status+" remark "+remark +"  role"+role+" roleKeyid "+roleKeyid);
			        AdmTlUsermst user = UIUtils.getLoginUser(request);
			        String dateTime = CommonFunctions.pg_getDate();
			        
			        JSONObject returnData = new JSONObject(); // Declare returnData early
			        
			        if (httpSession != null && user != null) {
			            BdmTlWhywhymst existBdmTlWhywhymst = new BdmTlWhywhymst();
			            BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();
			            newBdmTlWhywhymst.setWwmsKeyid(keyId);
			            newBdmTlWhywhymst.setWwmsApprovedBy(user.getUsrm_ccno());
			            newBdmTlWhywhymst.setWwmsApprRoleid(roleKeyid);
			            newBdmTlWhywhymst.setWwmsAppRemarks(remark);
			            newBdmTlWhywhymst.setWwmsAppStatus(status);
			            newBdmTlWhywhymst.setWwmsApprvedOn(dateTime);
			            newBdmTlWhywhymst.setWwmsIscobd(isCobd);
			            newBdmTlWhywhymst.setWwmsCobdvalue(cobdValue);
			            newBdmTlWhywhymst.setWwmsCobdhours(cobdHours);
			            newBdmTlWhywhymst = (BdmTlWhywhymst) UIUtils.setBeanProperties(newBdmTlWhywhymst, request);
			            
			            if (UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid())) {
			                CommonMessage.debugMsg("Updating existing WhyWhy record");
			                existBdmTlWhywhymst = whywhyAppService.updateApprovalAI(newBdmTlWhywhymst, existBdmTlWhywhymst);
			                String Status=existBdmTlWhywhymst.getWwmsAppStatus();
							String roleId=existBdmTlWhywhymst.getWwmsApprRoleid();
							
							List<String[]> mailDetails=whywhyAppService.getSpentTime(keyId);
							String spentTime=null;
							String area=null;
							String problem=null;						

	
							problem=mailDetails.get(0)[0];
							area=mailDetails.get(0)[1];
							spentTime=mailDetails.get(0)[2];
							
							CommonMessage.debugMsg(problem.toString()+"  .........    "+area.toString()+" .........    "+spentTime.toString());
/*
							String area=newBdmTlWhywhymst.getWwmsArea();
							String problem=newBdmTlWhywhymst.getWwmsProblem();*/
							
							float spentTime1 = Float.parseFloat(spentTime.toString());
							CommonMessage.debugMsg(problem +"  "+area +" IN sid the Update Why Why  " +spentTime1);
							if(spentTime1>=4.0){
							if(Status.equals("A") && (roleId.equals("AROL0131") ||roleId.equals("AROL0132")|| roleId.equals("AROL0133")||roleId.equals("AROL0134")||roleId.equals("AROL0135"))){
						    	sendApprvalMail(request, response,  keyId,problem, spentTime,area);

							}
							}
			            
			            }
			            
			            JSONObject persistentData = new JSONObject();
			            persistentData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
			            
			            JSONObject forwardData = new JSONObject();
			            
			            JSONObject successData = new JSONObject();
			            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
			            successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
			            
			            returnData.put("formClear", false);
			            returnData.put("forwardData", forwardData);
			            returnData.put("persistentData", persistentData);
			            returnData.put("successData", successData);
			        } else {
			            // In case session or user is null, send error JSON
			            returnData.put("error", "Session expired or invalid user.");
			        }
			        
			        out = response.getOutputStream(); // Move this AFTER processing
			        out.print(returnData.toString());
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			        try {
			            if (out == null) {
			                out = response.getOutputStream();
			            }
			            JSONObject error = new JSONObject();
			            error.put("error", "An unexpected error occurred.");
			            out.print(error.toString());
			        } catch (Exception innerEx) {
			            innerEx.printStackTrace();
			        }
			    } finally {
			        if (out != null) {
			            try {
			                out.flush();
			                out.close();
			            } catch (IOException e) {
			                e.printStackTrace();
			            }
			        }
			        CommonMessage.debugMsg("Inside the Finally block");
			    }
			}
			else	if (action.equals("whyWhyApproval_save.whyApr")) {
			    HttpSession httpSession = request.getSession(false);
			    ServletOutputStream out = null;
			    try {
			        response.setContentType("application/json");
			        
			        String keyId = request.getParameter("keyId");
			        String status = request.getParameter("status");
			        String remark = request.getParameter("remark");
			        String role = request.getParameter("userRole");
			        String roleKeyid = request.getParameter("roleKeyid");
			        CommonMessage.debugMsg(" keyId "+keyId +" status "+status+" remark "+remark +"  role"+role+" roleKeyid "+roleKeyid);
			        AdmTlUsermst user = UIUtils.getLoginUser(request);
			        String dateTime = CommonFunctions.pg_getDate();
			        
			        JSONObject returnData = new JSONObject(); // Declare returnData early
			        
			        if (httpSession != null && user != null) {
			            BdmTlWhywhymst existBdmTlWhywhymst = new BdmTlWhywhymst();
			            BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();
			            newBdmTlWhywhymst.setWwmsKeyid(keyId);
			            newBdmTlWhywhymst.setWwmsApprovedBy(user.getUsrm_ccno());
			            newBdmTlWhywhymst.setWwmsApprRoleid(roleKeyid);
			            newBdmTlWhywhymst.setWwmsAppRemarks(remark);
			            newBdmTlWhywhymst.setWwmsAppStatus(status);
			            newBdmTlWhywhymst.setWwmsApprvedOn(dateTime);
			            newBdmTlWhywhymst = (BdmTlWhywhymst) UIUtils.setBeanProperties(newBdmTlWhywhymst, request);
			            
			            if (UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid())) {
			                CommonMessage.debugMsg("Updating existing WhyWhy record");
			                existBdmTlWhywhymst = whywhyAppService.update(newBdmTlWhywhymst, existBdmTlWhywhymst);
			                String Status=existBdmTlWhywhymst.getWwmsAppStatus();
							String roleId=existBdmTlWhywhymst.getWwmsApprRoleid();
							
							List<String[]> mailDetails=whywhyAppService.getSpentTime(keyId);
							String spentTime=null;
							String area=null;
							String problem=null;						

	
							problem=mailDetails.get(0)[0];
							area=mailDetails.get(0)[1];
							spentTime=mailDetails.get(0)[2];
							
							CommonMessage.debugMsg(problem.toString()+"  .........    "+area.toString()+" .........    "+spentTime.toString());
/*
							String area=newBdmTlWhywhymst.getWwmsArea();
							String problem=newBdmTlWhywhymst.getWwmsProblem();*/
							
							float spentTime1 = Float.parseFloat(spentTime.toString());
							CommonMessage.debugMsg(problem +"  "+area +" IN sid the Update Why Why  " +spentTime1);
							if(spentTime1>=4.0){
							if(Status.equals("A") && (roleId.equals("AROL0131") ||roleId.equals("AROL0132")|| roleId.equals("AROL0133")||roleId.equals("AROL0134")||roleId.equals("AROL0135"))){
						    	sendApprvalMail(request, response,  keyId,problem, spentTime,area);

							}
							}
			            
			            }
			            
			            JSONObject persistentData = new JSONObject();
			            persistentData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
			            
			            JSONObject forwardData = new JSONObject();
			            
			            JSONObject successData = new JSONObject();
			            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
			            successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
			            
			            returnData.put("formClear", false);
			            returnData.put("forwardData", forwardData);
			            returnData.put("persistentData", persistentData);
			            returnData.put("successData", successData);
			        } else {
			            // In case session or user is null, send error JSON
			            returnData.put("error", "Session expired or invalid user.");
			        }
			        
			        out = response.getOutputStream(); // Move this AFTER processing
			        out.print(returnData.toString());
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			        try {
			            if (out == null) {
			                out = response.getOutputStream();
			            }
			            JSONObject error = new JSONObject();
			            error.put("error", "An unexpected error occurred.");
			            out.print(error.toString());
			        } catch (Exception innerEx) {
			            innerEx.printStackTrace();
			        }
			    } finally {
			        if (out != null) {
			            try {
			                out.flush();
			                out.close();
			            } catch (IOException e) {
			                e.printStackTrace();
			            }
			        }
			        CommonMessage.debugMsg("Inside the Finally block");
			    }
			}	
			
		}	
	private JSONObject getTableModelApprovalList(List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(1);
		String[] colHeader2 = headers.get(2);

		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		
		jqGridTableModel.setRowNumbers(true);
	    jqGridTableModel.setEnableFilter(true);
	    jqGridTableModel.setTableButton(false);
	   
		for (int i = 0; i < colHeader2.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader2[i].replaceAll(" ", "")); 
			jqGridColModel.setName(colHeader2[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(110);
			jqGridColModel.setAlign("left");
			//CommonMessage.debugMsg("colHeader1[i]    "+colHeader1[i]+"    colHeader[i]  "+colHeader[i]);
			jqGridColModel.setEditable(false);
			if(i==0 || i==2)
			{
				jqGridColModel.setHidden(true);
			}
			if(i==3 || i==4)
			{
				jqGridColModel.setWidth(50);
				
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			CommonMessage.debugMsg("colHeader1["+i+"]"+colHeader1[i]);
		}
		jqGridTableModel.getRowHeaders().add(colHeader2);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "75%%");
		tableModel.set("tableWidth", "107%%");
		return tableModel;
	}
	private void sendApprvalMail(HttpServletRequest request, HttpServletResponse response,String whywhyNo, String problem,String SpentTime,String area) throws IOException{
		String fileName  = "";
		String val="";
		File f = null;
	//	String whywhyNo = request.getParameter("momKeyId");
		try{
			CommonMessage.debugMsg(" 12 " +whywhyNo);
		
			List<String[]> empMailIds = yyService.getPcEmailIds(whywhyNo) ;
			//String ccEmailId=yyService.getCCEmailId(empmKeyId) ;
		    String mailIds =  buildToMailIds(empMailIds);
			CommonMessage.debugMsg(" mailIds :: 12 "+empMailIds.size());
					
			//String empmKeyId=user.getUsrm_ccno();
			
			String format = ".xlsx";
					
			CommonMessage.debugMsg(" fileName :: Checking :: " + fileName);
					
			String imagePath = UIUtils.getImagePath(request);
			CommonMessage.debugMsg("keyid::::::"+whywhyNo);
			//String format = ExcelUtils.getFormat(request);
		
			String paths = UIUtils.getExcelTemplatePath(request);  	
			Workbook wb = whywhyService.getwhywhyExlView(whywhyNo,format,paths,imagePath); 
		//	ExcelUtils.writeToResponse(response, wb, "WhyWhy_"+whywhyNo, format);
			fileName =this.filePath + "WhyWhyAnalysis_"+whywhyNo + "_" + UIUtils.now() +format;
			FileOutputStream out = new FileOutputStream( fileName );

			  wb.write(out); 
			    out.close();
			    out = null;
			    wb = null;
			
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
					
			CommonMessage.debugMsg(" fileNamefileNamefileNamefileName "+fileName);
			
			
			String content=" ";
			StringBuilder subject = new StringBuilder();
			subject.append(" Why Why Analysis - ");
			subject.append(whywhyNo);
			
			StringBuilder totalContent = new StringBuilder();
			//totalContent.append(content+"</BR>");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("Dear Sir, </BR>");
			totalContent.append(" Following Why Why is Entered In System.   </BR>");
			totalContent.append("</BR>");
			totalContent.append("\r\n");
			totalContent.append(" Why Why No : "+ whywhyNo +" </BR>");
			//CommonMessage.debugMsg();
			totalContent.append(" \r\n ");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append(" Area : "+ area +" \r\n");
			totalContent.append("&nbsp;");
			totalContent.append(System.getProperty("line.separator"));
			totalContent.append("  \r\n Problem :  "+ problem +" </BR>");
			totalContent.append(System.getProperty("line.separator"));

			totalContent.append("\r\n");
			totalContent.append(" Time Spent  : "+ SpentTime +" Hrs.</BR>");
			totalContent.append(System.getProperty("line.separator"));

			
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append("</BR>");
			totalContent.append("To check the Details Please Find the Attachment " +" </BR>");
			
			totalContent.append("Regards </BR> ");
			totalContent.append("Perfex 360 Team. "+" </BR>");
			
			//totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			
/*			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);*/
			CommonMessage.debugMsg(" attachmentFiles."+fileName );
			
			//attachmentFiles = attachFileManagerFiles(attachmentFiles,whywhyNo);
			
			//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,mailIds,subject.toString(),totalContent.toString(),attachmentFiles);
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			//UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Sent SuccessFully ");
			val="false";
			//String mailid=moMeetingService.updateIsmailid(momKeyId,val);
	    //    response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" +e);
			e.printStackTrace();
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			val="true";
			try {
				//String mailid=moMeetingService.updateIsmailid(whywhyNo,val);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// response.getWriter().print(succssMsg.toString());
		}
		
	}
	
	private String buildToMailIds(List<String[]> mailIds){
		StringBuilder mailIdsStr = new StringBuilder();
		CommonMessage.debugMsg(" In side he Build mails "+mailIds.size());
		for(String [] mailid : mailIds){
			if(UIUtils.isValidEmail(mailid[0]) ){
				mailIdsStr.append(mailid[0]);
				mailIdsStr.append(',');
			}	
		}
		if(  mailIdsStr.length() > 0 )
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		CommonMessage.debugMsg(" Mail Ids to send the mail "+ mailIdsStr.toString());
		return mailIdsStr.toString();
	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
			FilterValues.getCommonFilters(request, commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
			if(beanIdentifier.equals("whywhyQtyCommonFilter"))
				commonFilter = 	FilterValues.getQuality(request, commonFilter);
			else			
				commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
			  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
			  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
			  commonFilter.setMonwise("Y");
	 	  } 

		
		return commonFilter;
	}
}
