package com.akranta.tpm.controller;

import java.io.File;
//import lotus.domino.NotesException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONObject;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChangePwdBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUsercustompages;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AdmTlUsersessions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.UserServices;
import com.akranta.tpm.service.impl.UserServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.SessionRegistry;

/**
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String browserNotSupprt = "/pages/Gen/ntspt.html";
	private String app_path =null;
	private String appDeployedTime = null;
	private static final String mz_browser_FileName = "/TPMDownLoads/Firefox_Setup_7.0.1.exe";
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String appFolder = null;
	private String dbUser = null;
	UserServices userServices ;
	ServletContext application = null;
//	private MenuTreeServices menuTreeServices ;
	List<String> loginSldImgs = new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
        super.init(config);

		app_path = getServletContext().getRealPath("UserServlet") ;
		app_path = new File(app_path).getParent();
		String tmpImgPath = app_path +"/"+ UIUtils.TPM_TEMPIMG_DIR;
		new File(tmpImgPath).mkdirs();
		app_path = new File(app_path).getParent();
		app_path = new File(app_path).getParent();
		app_path = new File(app_path).getParent();
		application = this.getServletContext();
		appFolder = application.getRealPath("/WEB-INF");
		appFolder = new File(appFolder).getParent();
		
		appDeployedTime =UIUtils.convertTime(new File(appFolder).lastModified());
		
		String loginSlideDirPath = appFolder+"\\images\\loginslide";
		File loginSlidFolder =  new File(loginSlideDirPath);
		if(loginSlidFolder.isDirectory()){
			for (final File fileEntry : loginSlidFolder.listFiles()) {
		        if(! fileEntry.isDirectory()) {
		        	loginSldImgs.add(fileEntry.getName());
		        } 
		    }
		}
	}
	
    public UserServlet()  {
        super();
        CommonMessage.debugMsg("app_path  super " );
        //userServices = new  UserServiceImpl();
        
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("userS serlvet " + action);
		CommonMessage.debugMsg(action);
	/*	HttpSession userSession = request.getSession();
		
		if( userSession != null )
		{	
			DBActionTemplate dbActionTemplate = null;
			try {
				dbActionTemplate = UIUtils.getDBActionTemplate(request);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			if( dbActionTemplate != null)
				userServices = new  UserServiceImpl(dbActionTemplate);
		}
	*/
		
		
		if(action.equals("perfex")  || action.trim().isEmpty()  )
		{	
			disaplyLoginPage(request,response);
		}
		else if(action.equals("ntspt") ){
			//UIUtils.setCookieValue(response, "ntspt","true");
			UIUtils.forwardRequest(request, response, this.browserNotSupprt);
		}
		else if( action.equals("browser.download")){
			
			UIUtils.downloadFile(response, mz_browser_FileName, app_path);
		}
		
		else{
			try {
				HttpSession s = request.getSession(true);
				CommonMessage.debugMsg("  userServices before  1" );
				userServices = (UserServiceImpl)UIUtils.getServiceObject(request,"UserServiceImpl");
				CommonMessage.debugMsg("  userServices jwt token : "+s.getAttribute("tpmjwttoken") );
//				userServices = new UserServiceImpl((String) s.getAttribute("tpmjwttoken"));
				userServices.UserServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
				CommonMessage.debugMsg("  userServices after  1 " );
				if(action.equals("validate.userLogin"))
				{
					CommonMessage.debugMsg("validateUserLogin");
					validateUserLogin(request,response);
					
				}
				
				
				else if(action.equals("forgotvalid.userLogin")) {
					CommonMessage.debugMsg("validateForgetPwd.userLogin");
					String loginId = request.getParameter("txtUserName");
					
					validateUserForMail(request,response,loginId);
				}
					
				else if(action.equals("forgotpwd.userLogin"))
				{
					CommonMessage.debugMsg("FORGETPASSWORD");
					String loginId = request.getParameter("txtUserName");
					//sendPwdMail(request,response,loginId);
					sendPwdMailNew(request,response,loginId);
				}
				
				
				
				else if(action.equals("logout.userLogin"))
				{
				//	CommonMessage.debugMsg("logout user");
					logoutUser(request,response);
				}
				/*  Created By Karthick.T */
				else if(action.equals("changePswd.userLogin"))
				{
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					
					
					
					
					String loginName = null;
					if(user != null)
					{	
						loginName = user.getUsrm_loginid();
					}
					else
						loginName = request.getParameter("txtUserName");
					String frmchngpwd = request.getParameter("frmchngpwd");
					String btnVisb = request.getParameter("button");
					CommonMessage.debugMsg("loginName : "+loginName); 
					if(UIUtils.isValidKeyId(loginName))
						request.setAttribute("loginId", loginName);
					request.setAttribute("btnVisb", btnVisb);
					if("Y".equals(frmchngpwd))
					{
						if (checkpwdchangegap(loginName) == true )
						{   AdmTlLoginframework admTlLoginframework = userServices.getloginframeworkdata();
							
							String msg1 = "Last Password changed on " + UIUtils.getActualDateForm(user.getUsrm_lastpwdchanged()) ;
							request.setAttribute("pwdmsg1", msg1);
						
							String msg = "Password change gap should be Minimum " + admTlLoginframework.getLgfrminpwdchangedays() + " days ";
							request.setAttribute("pwdmsg", msg);
							UIUtils.forwardRequest(request, response,"/pages/pwdchangegaperrormsg.jsp"); 
						}
						else
						{
							UIUtils.forwardRequest(request, response,"/pages/ChangePassword.jsp"); 
						}
					}else
					{
						UIUtils.forwardRequest(request, response,"/pages/ChangePassword.jsp"); 
					}
					
					
					//validateUserLogin(request,response);
				}
				/*  Created By Karthick.T */
				else if(action.equals("intimation.userLogin"))
				{
					UIUtils.forwardRequest(request, response,"/pages/Intimation.jsp");
				}
				/*  Created By Karthick.T */
				else if(action.equals("savenew_pwd.userLogin"))
				{
					ChangePwdBean changePwdBean = new ChangePwdBean();
					saveNewPassword(request,response,changePwdBean);
				}
				/*  Created By Karthick.T */
				else if(action.equals("setHomePage_save.userLogin"))
				{
					saveSetHomePage(request,response);
				}
				/*  Created By Karthick.T */
				else if(action.equals("recallHomePage.userLogin"))
				{
					recallHomePage(request,response);
				}
				/*else if(action.equals("getElementId.userLogin"))
				{
					getElementId(request,response);
				}*/
				/*  Created By Karthick.T */
				else if(action.equals("CheckPwdExpires.userLogin"))
				{
				
					checkPasswordExpires(request,response);
					
				}
				else if(action.equals("loginframwork_input.userLogin"))
				{
					UIUtils.forwardRequest(request, response,"/pages/LoginFramWork.jsp");
				}else if(action.equals("loginframwork_getCol.userLogin"))
				{
					HttpSession httpSession = request.getSession(false);
					
					PrintWriter out = response.getWriter();
					httpSession = request.getSession(false);
					JSONObject jsonObject = new JSONObject();
					List<String[]> loginframework = null;
					try {
					
						loginframework = userServices.getloginframeworkgrid();
					} catch (Exception e) {
					e.printStackTrace();
					}
					CommonMessage.debugMsg("SIZE" + loginframework.size());
					jsonObject = getTableModel(loginframework);
					httpSession.removeAttribute("");
					httpSession.setAttribute("", jsonObject);
					out.println(jsonObject);
				} 
				else if(action.equals("getElementId.userLogin"))
				{
					getElementId(request,response);
				}
				else if(action.equals("combo_lgpsexp.userLogin"))
			    {
			    	
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.lgfrmwrk", "selectpsdexp"));
			    	
			    }	
				else if(action.equals("combo_lgadt.userLogin"))
			    {
			    	
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.lgfrmwrk", "selectlgadt"));
			    	
			    }
				else if(action.equals("combo_pasadt.userLogin"))
			    {
			    	
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.lgfrmwrk", "selectpasadt"));
			    	
			    }	
				else if(action.equals("combo_prvadt.userLogin"))
			    {
			    	
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.lgfrmwrk", "selectprvadt"));
			    	
			    }	
				else if(action.equals("loginframwork_getData.userLogin"))
				{
					HttpSession httpSession = request.getSession(false);
			    	PrintWriter out = response.getWriter();
			    	
					List<String[]> loginframework = userServices.getloginframeworkgrid();
					httpSession.removeAttribute("loginframeGrid");
					
					JSONObject safetyAuditData = UIUtils.convertToJqGridTableObject(loginframework, request, 1,0 );
					out.println(safetyAuditData);
				}
				else if(action.equals("UserLoginDetails_input.userLogin"))
				{
					UIUtils.forwardRequest(request, response,"/pages/UserLoginDetails.jsp");
				}else if(action.equals("UserLoginDetails_getCol.userLogin"))
				{
					List<String[]> userGrid = null;
					PrintWriter out = response.getWriter();
				
					
					try {
						HttpSession httpSession = request.getSession(false);
					    CommonFilter commonFilter = populateCommonFilter(request,"UserDetailsCommonFilter",true);
					    String flid=request.getParameter("flid");
					 //   CommonMessage.debugMsg("The Flid::"+flid);
					    commonFilter.setFlid(flid);
					    commonFilter.setIsGetCol("Y");
					    userGrid = userServices.getUserLoginDetailsFillGrid(commonFilter);
						JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
						GridColModel gridColModel = new GridColModel();			
						
						jqGridTableModel.setSortable(true);
						jqGridTableModel.setTableButton(true);
						jqGridTableModel.setEnableFilter(true);
						jqGridTableModel.setRowNumbers(true);
						gridColModel.setHeaderNum(1);//9
						String [] colHeader = userGrid.get(1);
						String [] colHeaderHead = userGrid.get(0);
						List<String[]> headers = new ArrayList<String[]>();
						headers.add(colHeader);
						JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
						httpSession.setAttribute("UserDataColmodel", colmodel);
						CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
						colmodel.set("tableWidth", "106%%");
						colmodel.set("tableHeight", "80%%");
						
						out.println(colmodel);

					} catch (Exception e) {
						e.printStackTrace();
					}	

				}
				else if(action.equals("UserLoginDetails_getData.userLogin"))
				{
					try
					{   
                        
						UIUtils.displayRequestParamsValue(request);	
						CommonFilter commonFilter = populateCommonFilter(request,"UserDetailsCommonFilter",false);
						String flid=request.getParameter("flid");
					  //  CommonMessage.debugMsg("The Flid::"+flid);
					    commonFilter.setFlid(flid); 
					    commonFilter.setIsGetCol("N");
		                List<String[]> userGrid   = userServices.getUserLoginDetailsFillGrid(commonFilter);
						
		  			 	CommonMessage.debugMsg("userGrid " + userGrid.size());
						PrintWriter out = response.getWriter();
		  			 	//JSONObject HrmsGridData = UIUtils.convertToJqGridTableObject(userGrid,request,2,0,commonFilter.getTotalRecordCnt()-2); 
						JSONObject HrmsGridData = UIUtils.convertToJqGridTableObject(userGrid, request, 2, 0,commonFilter.getTotalRecordCnt());
						out.println(HrmsGridData);  

			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}
			}

				else if(action.equals("UserLoginDetails_getExcel.userLogin"))
				{
					HttpSession httpSession = request.getSession(false);			
					httpSession = request.getSession(false);
					CommonFilter commonFilter = populateCommonFilter(request,"UserDetailsCommonFilter",false);
					JSONObject colmodel = (JSONObject) httpSession.getAttribute("UserDataColmodel");
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					
					colmodel.put("title","Login Details");
		         			   String format = ExcelUtils.getFormat(request);
					
					Workbook wb = userServices.getuserDetailsReportExcel(colmodel,format,commonFilter);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response, wb, "LoginDetailsReport", format);
				
				
				}				

				
				else if(action.equals("loginframwork_save.userLogin"))
				{  
					HttpSession httpSession = request.getSession(false);
					httpSession = request.getSession(false);
					ServletOutputStream out = response.getOutputStream();
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					String savemsg;
					if( httpSession != null && user != null){ 
						AdmTlLoginframework newadmTlLoginframework = new AdmTlLoginframework();
						AdmTlLoginframework oldadmTlLoginframework = (AdmTlLoginframework)httpSession.getAttribute("newadmTlLoginframework");
						newadmTlLoginframework = (AdmTlLoginframework)UIUtils.setBeanProperties((Object)newadmTlLoginframework,request);
						newadmTlLoginframework.setLgfrCreatedby(user.getUsrm_ccno());
						CommonMessage.debugMsg("N keyid" + newadmTlLoginframework.getLgfrKeyid());
						
						JSONObject successData=new JSONObject();
						JSONObject returnData=new JSONObject();	
						try
						{
						/*if(UIUtils.isValidKeyId(newadmTlLoginframework.getLgfrKeyid()))
						{
							oldadmTlLoginframework = userServices.updatelgfrm(newadmTlLoginframework);
							savemsg= "Data updated successfully";
						}
						else
						{*/
							newadmTlLoginframework.setLgfrPassneverexpires("N");
							newadmTlLoginframework.setLgfrIsloginaudit("N");
							newadmTlLoginframework.setLgfrIspassaudit("N");
							newadmTlLoginframework.setLgfrIsprivaudit("N");
							if (newadmTlLoginframework.getLgfrIspassautogen().equals("Y"))
							{
								newadmTlLoginframework.setLgfrDefsyspassword("ABC123");
							}
							oldadmTlLoginframework = userServices.Creatlgfrm(newadmTlLoginframework);
							savemsg="Data saved successfully";							
							
						//}
						 successData.put("msg", savemsg);
			        	 returnData.put("successData",successData);
			        	 returnData.put("formClear",true);
			    		 out.println(returnData.toString());
						}catch (ValidationExceptions e){
							if(e.getObject() == null )
							{
								
								net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "lgfrmwrk");
								out.print(errMessage.toString());
							}
							else
							{
								
								net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "lgfrmwrk");
								
								AdmTlLoginframework admTlLoginframework = (AdmTlLoginframework) e.getObject();
								
								String errormsg = errMessage.toString();
								
								if(errormsg.indexOf("<Minnumber>")>0)
								{
									errormsg = errormsg.replace("<Minnumber>", admTlLoginframework.getLgfrMaxpasslength());
								}
								if(errormsg.indexOf("<Maxnumber>")>0)
								{
									errormsg = errormsg.replace("<Maxnumber>", admTlLoginframework.getLgfrMinpasslength());
								}
								if(errormsg.indexOf("<Minaph>")>0)
								{
									errormsg = errormsg.replace("<Minaph>", admTlLoginframework.getLgfrAlphabets());
								}
								if(errormsg.indexOf("<Minnum>")>0)
								{
									errormsg = errormsg.replace("<Minnum>", admTlLoginframework.getLgfrNumerals());
								}
								
								out.print(errormsg);
							}
			    	    }catch (BusinessApplicationExceptions e){
			    			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "lgfrmwrk");
			    			out.print(errMessage.toString());
			    			CommonMessage.debugMsg(" e " + errMessage );
			    	    }catch(Exception e){
					    }						
						
					}
				}
			}
			catch (ServiceObjectCreationException e) {
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("e "+e);
				request.getSession().removeAttribute("admDbEnvironment");
				request.getSession().removeAttribute("admDbLocation");
				request.getSession(false).setMaxInactiveInterval(1);
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
				out.print(errMessage);
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
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
			// getFilterValues(request);
			
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		return commonFilter;
	}

	
	private void getElementId(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession  = request.getSession(true);
		String userId = user.getUsrm_ccno();
		String roleId = request.getParameter("roleId");
		String currentFlid = request.getParameter("flid");
		//String elementId = userServices.getElementId(userId,roleId);
		
		CommonMessage.debugMsg(" In side the get Element id  "+roleId+"  "+ userId);
		List<String []> getUserLoginDtl = userServices.getElementId(userId,roleId, currentFlid);
		String InActive = userServices.checkFlidActive(currentFlid);
		JSONObject returnData = new JSONObject();
		
		CommonMessage.debugMsg(" In side the get Element id "+getUserLoginDtl.size());
		String elementid = getUserLoginDtl.get(0)[0];
		String flid = getUserLoginDtl.get(0)[1];
		String level = getUserLoginDtl.get(0)[2];
		returnData.put("elementId", elementid);
		returnData.put("flid", flid);
		returnData.put("levelNo", level);
		returnData.put("inActive", InActive);
		
		httpSession.removeAttribute("loginElementid");
		httpSession.removeAttribute("loginFlid");
		httpSession.removeAttribute("loginRoleLevelNo");
		httpSession.removeAttribute("loginRoleLevelNo");
		
		httpSession.setAttribute("loginElementid", elementid);			
		httpSession.setAttribute("loginFlid", flid);
		httpSession.setAttribute("loginRoleLevelNo", level);
		if( elementid != null && elementid.length() > 20)
			httpSession.setAttribute("loginLocnId", elementid.substring(11, 21));
		else
			httpSession.setAttribute("loginLocnId", "");
		out.print(returnData.toString());
		
	}
	/*  Created By Karthick.T */
	private void checkPasswordExpires(HttpServletRequest request,HttpServletResponse response) throws IOException  {
		
		HttpSession httpSession  = request.getSession(false);
		
		PrintWriter out =response.getWriter();
		CommonMessage.debugMsg("checkPasswordExpires....");
		try {
			AdmTlLoginframework admTlLoginframework = userServices.getloginframeworkdata();
			if(admTlLoginframework.getLgfrPassneverexpires().equals("N"))
			{
				String user = (String) httpSession.getAttribute("txtUserName");
				CommonMessage.debugMsg("user...."+user);
				//CommonMessage.debugMsg("user............."+user);
				userServices.getCheckPasswordExpires(user);
				
			}
			
			
		} 
		catch( BusinessApplicationExceptions e){
			
			CommonMessage.debugMsg("BusinessApplicationExceptions......");
			CommonMessage.debugMsg("BusinessApplicationExceptions......");
		e.printStackTrace();
			JSONObject errData = new JSONObject();		
			String[] err = e.getMessage().split(":");
			
			
			errData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.LoginExceptions",err[0]));
			
			CommonMessage.debugMsg("err[1]" + err[1]);
			errData.put("limit",err[1]);
			
			
			
			
			
			JSONObject returnData = new JSONObject();
			returnData.put("successData", errData);	
			out.print(returnData.toString());
			
			
			
			/*JSONObject errMessage = UIUtils.validationExceptions(err[0],  "LoginExceptions");
			//CommonMessage.debugMsg("validateUserLogin err " + e.getMessage());
			out.print(errMessage);*/
		
		}
		catch (Exception e) {
		
			
		}
		
	}
	private AdmTlUsermst getLoginDetails(HttpServletRequest request)
	{
		AdmTlUsermst admTlUsermst = new AdmTlUsermst();
		HttpSession httpSession  = request.getSession(false);
		String username = request.getParameter("txtUserName");
		String password = request.getParameter("txtPswd");
		admTlUsermst.setUsrm_loginid(username);
		admTlUsermst.setUsrm_password(password);
		CommonMessage.debugMsg("validateUserLogin 3 ");
		httpSession.setAttribute("txtUserName", username);
		return admTlUsermst;
	}
	
	private AdmTlUsersessions setUserSessionDetails(HttpServletRequest request){
		
		
		HttpSession httpSession  = request.getSession(false);
		if( httpSession != null ){
			AdmTlUsersessions admTlUsersessions = new AdmTlUsersessions();
			
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			admTlUsersessions.setUsseUserid(user.getUsrm_keyid());
			admTlUsersessions.setUsseIpaddress(request.getRemoteAddr());
			admTlUsersessions.setUssePcname(request.getRemoteHost());
			admTlUsersessions.setUsseSessionid(httpSession.getId());
			return admTlUsersessions;
		}
		return null;
	}
	private void sendPwdMail(HttpServletRequest request, HttpServletResponse response,String userName) throws ServletException, IOException{
		PrintWriter out =response.getWriter();
		try {
			//userServices = (UserServiceImpl)UIUtils.getServiceObject(request,"UserServiceImpl");
			String mailSend =  	userServices.sendPWDToMail(userName);
			
			mailSend = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "mailSend")+" <b>"+mailSend+"</b>";
			JSONObject msg =new JSONObject();
			msg.put("succ", mailSend);
		 	out.print(msg);
		}
		catch( ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
			CommonMessage.debugMsg("validateUserLogin err " + e.getMessage());
			out.print(errMessage);
		
		} catch (BusinessApplicationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		
		}catch(Exception e){
			
			/*CommonMessage.debugMsg(e.getMessage());
			JSONObject err = new JSONObject();
			err.put("msg", e.getMessage());
			out.print(err.toString());*/
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		} 
	}
	private void validateUserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		HttpSession s = request.getSession(true);
		
		CommonMessage.debugMsg("validateUserLogin 2 ");
		try {
			
			AdmTlUsermst  admTlUsermst = getLoginDetails(request);		
			CommonMessage.debugMsg("user login Id......"+admTlUsermst.getUsrm_loginid());
			
			String strusername = request.getParameter("txtUserName");
			String confirmForce = request.getParameter("confirmForce");
			String Loginfailcount = userServices.getloginattemptcount(strusername);
			CommonMessage.debugMsg(strusername +"Loginfailcount...."+Loginfailcount);
			
			if(UIUtils.isValidKeyId(Loginfailcount))
			{
				if (Integer.parseInt(Loginfailcount) >= 3)
				{   
					//String strusername = request.getParameter("txtUserName");
					userServices.lockuser(strusername);
				}
			}
			
			AdmTlUsermst usermst = userServices.validateLogin(admTlUsermst);
			
//			SessionRegistry.SessionInfo existing =
//	                SessionRegistry.getExistingSession(usermst.getUsrm_keyid());
//
//	        if (existing != null && !existing.session.getId().equals(s.getId())) {
//
//	            if (!"Y".equals(confirmForce)) {
//	                // Send conflict info back to UI — do NOT proceed with login yet
//	                JSONObject conflict = new JSONObject();
//	                conflict.put("concurrentLogin", true);
//	                conflict.put("ipAddress",   existing.ipAddress);
//	                conflict.put("machineName", existing.machineName);
//	                out.println(conflict.toString());
//	                return; // stop here, wait for user confirmation
//	            }
//
//	            // User confirmed — force logout old session
//	            SessionRegistry.forceRegisterSession(
//	                usermst.getUsrm_keyid(), s,
//	                request.getRemoteAddr(), request.getRemoteHost()
//	            );
//
//	        } else {
//	            // No conflict — normal registration
//	            SessionRegistry.registerSession(
//	                usermst.getUsrm_keyid(), s,
//	                request.getRemoteAddr(), request.getRemoteHost()
//	            );
//	        }
			UserLoginDetailsBean userDetails = userServices.getLoginUserDeatils(usermst.getUsrm_keyid());			 
			userDetails.setLoginTime(CommonFunctions.dateTimeNow());
			
			s.setAttribute("user", usermst);
			s.setAttribute("userLogin",userDetails);
			
			response.setContentType("text/json");
			response.setContentType("text/html"); 
			
			String jwtToken = userServices.getJwtToken(usermst.getUsrm_keyid());
			CommonMessage.debugMsg("Jwt Token : "+jwtToken);
			CommonMessage.debugMsg("validateUserLogin 6 ");
			AdmTlUsersessions admTlUsersessions =  setUserSessionDetails(request);
			
			JSONObject tpmUser = new JSONObject();
			tpmUser.put("tpmuser", s.getId());
			s.setAttribute("tpmuser", s.getId());
			s.setAttribute("tpmjwttoken", jwtToken);
			Cookie cookie = new Cookie("tpmuser", s.getId());
			cookie.setHttpOnly(true);
			cookie.setPath("/"); 
		    response.addCookie(cookie);
		    CommonMessage.debugMsg(" s.getId() : "+ s.getId());
		    CommonMessage.debugMsg("tpmUser : "+tpmUser);
//			out.println("tpmUser : "+tpmUser);
		    userServices.UserServiceImplJwt(jwtToken);
		    out.println(tpmUser);
			dbUser = getSchema();
			s.setAttribute("createdDate", this.appDeployedTime);
			s.setAttribute("dbUser", this.dbUser);
			userServices.insertUserSession(admTlUsersessions);
			CommonMessage.debugMsg("validateUserLogin 7 ");
			
			String logintime=admTlUsersessions.getUsseLogintime();
			String userid=admTlUsersessions.getUsseUserid();
			   if(logintime.length()!=0&&logintime!=null&&userid!=null)
			   {   AdmTlUsermst admtluersmt=new AdmTlUsermst();
			        admtluersmt.setUsrm_lastlogindate(logintime);
			        admtluersmt.setUsrm_keyid(userid);
				String output=userServices.updateUserLoginTime(admtluersmt);
				CommonMessage.debugMsg("output"+output);
				   
			   }

			//	MenuTree menuTree = new MenuTree();        	
			//	menuTree.setParentNumber("-1");
        	
	        /*	List <MenuTree> menuList = menuTreeServices.getAllMneus(menuTree,usermst.getUsrm_keyid());
	        	s.removeAttribute("userMenuRights");
				s.setAttribute("userMenuRights", menuList);
	        */	
			s.setMaxInactiveInterval(1*60*10);
        	//s.setMaxInactiveInterval(10);

			
		}catch ( ValidationExceptions e){
			
			CommonMessage.debugMsg("e.toString()"+e.toString());
			CommonMessage.debugMsg("e.toString()"+e.getMessage());
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
			
			
			if(e.getMessage().contains("Usrm_password-invalid"))
			{	
					//JSONObject errMsg = checkPwdCountLockUser(request,response);
				    try {
						Addedloginattempt(request,response);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//errMessage.put("errMsg", errMsg);
					//errMessage.put("invalidPwd", true);
			} 
			
      
		
			out.print(errMessage);
			s.setMaxInactiveInterval(1);
			//throw new IOException(errMessage.toString());
		} catch (BusinessApplicationExceptions e) {
			s.setMaxInactiveInterval(1);
			throw new IOException(e.getMessage() );
		}catch(Exception e){
			s.setMaxInactiveInterval(1);
			CommonMessage.debugMsg(e.getMessage());
		} 
	}
	private void Addedloginattempt(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String username = request.getParameter("txtUserName");
		userServices.updateloginattempt(username);
		String Logincount =  userServices.getloginattemptcount(username);
		CommonMessage.debugMsg("Logincount" + Logincount);
		if (Integer.parseInt(Logincount) > 2)
		{
			userServices.lockuser(username);
		}
		
		
			
		
		
	}
	/*  Created By Karthick.T */
	private JSONObject checkPwdCountLockUser(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession s = request.getSession(true);
		
		try{
			int failAttms = 0;
			failAttms = loginFailsAttempts(request,response);			
			JSONObject errMsg = new JSONObject();
			s.setAttribute("loginAttms",failAttms);
				
			String loginAttmpts = request.getParameter("hdnDecriptVal");
			
			if(loginAttmpts.trim().length() == 0 || loginAttmpts.equals(null))
			{
				loginAttmpts = setEncryptCount(request,response,"0");
			}			
				String userName = request.getParameter("hdnUserName");
				String loginUserId = request.getParameter("txtUserName");
				String decptCnt = userServices.decriptCount(loginAttmpts,9);
		
					int decriptCnt = Integer.parseInt(decptCnt);
					decptCnt = Integer.toString(decriptCnt+1);
					s.setAttribute("currentAttms",decptCnt);
					String encptCnt = userServices.encryptCount(decptCnt,9);
					
					AdmTlUsermst  admTlUsermst = getLoginDetails(request);	
					errMsg.put("encriptCount",encptCnt);
						
					 if(!loginUserId.equals(userName) && !userName.trim().equals(""))
					{
						String encptVal = setEncryptCount(request,response,"1");
		    			 decptCnt = userServices.decriptCount(encptVal,9);
						 
						userName = loginUserId;
						errMsg.put("encriptCount",encptVal);
						errMsg.put("userName",userName);
						
						String err = UIUtils.getPropertyValue("com.akranta.tpm.resources.LoginExceptions", "User_LockBy_InvalidPwd");
						errMsg.put("errMsg", err+decptCnt+" of "+failAttms+" times");
					}	
					else
					{
						userName = loginUserId;						
						errMsg.put("userName",userName);
						errMsg.put("encriptCount",encptCnt);
						String err = UIUtils.getPropertyValue("com.akranta.tpm.resources.LoginExceptions", "User_LockBy_InvalidPwd");
						errMsg.put("errMsg", err+decptCnt+" of "+failAttms+" times");
						 if(failAttms-1 == decriptCnt)
							{
								 err = UIUtils.getPropertyValue("com.akranta.tpm.resources.LoginExceptions", "Usrm_inactive");
								String encptVal = setEncryptCount(request,response,"0");
								errMsg.put("encriptCount",encptVal);
								errMsg.put("errMsg", err);
								userServices.lockUserAcc(admTlUsermst);
							}
					}
					return errMsg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private void disaplyLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try{
			String reqUser = request.getParameter("tpmuser");
			HttpSession userSession = request.getSession();
			String curUser =(String)userSession.getAttribute("tpmuser"); 
			AdmTlUsermst userDetails = (AdmTlUsermst )userSession.getAttribute("user");
		
			if( reqUser == null || reqUser.isEmpty())
				reqUser = UIUtils.getCookieValue(request, "tpmuser");
			
			if( reqUser != null && userDetails != null && curUser != null && curUser.equals(reqUser) ){
				
				Calendar cal = Calendar.getInstance();
				response.addHeader("Expires","Mon, 14 Oct 2002 05:00:00 GMT");              // Date in the past
				response.addHeader("Last-Modified",cal.getTimeZone()+""); // always modified
				response.addHeader("Cache-Control", "no-store, no-cache, must-revalidate");  // HTTP 1.1
				response.addHeader("Cache-Control"," post-check=0, pre-check=0, false");
				response.addHeader("Pragma", "no-cache");     
				request.setAttribute("loggedUserId", userDetails.getUsrm_ccno());
				
				request.setAttribute("dirOpenMenuId", request.getParameter("menuId"));
				request.setAttribute("dirOpenMenuName", request.getParameter("menuName"));
				
				String userPassword = userDetails.getUsrm_password();
				String defaultPassword = userDetails.getUsrm_defaultpassword();
				
				//request.setAttribute("userPassword", userPassword);
				//request.setAttribute("defaultPassword", defaultPassword);
				CommonMessage.debugMsg(defaultPassword+" In side the defaultPassword "+userPassword);
				AdmTlLoginframework AdmTlLoginframework = userServices.getloginframeworkdata();
				if (AdmTlLoginframework.getLgfrPassneverexpires().equals("N"))
				{
					if(userPassword.equals(defaultPassword))
					{  
						CommonMessage.debugMsg("inside ShowHOME N");
						request.setAttribute("shwhome", "N");
					}
				}
				else
				{
					request.setAttribute("shwhome", "Y");
				}
				
				userServices.setloginattemptzero(userDetails.getUsrm_loginid());
//				UIUtils.forwardRequest(request, response, "/layouts/classic.jsp");
				UIUtils.forwardRequest(request, response, "/WEB-INF/jsp/home.jsp");
				return;
			}
			else{
	
				String encptCnt = setEncryptCount(request,response,"0");
				request.setAttribute("encriptCount", encptCnt);
				request.setAttribute("loginSlideImgs",loginSldImgs);
				UIUtils.forwardRequest(request, response, "/pages/login.jsp");
          
			    if( userSession != null )
					 userSession.setMaxInactiveInterval(30);
			}
			
			
		}catch(Exception e)
		{
	
			String encptCnt = setEncryptCount(request,response,"0");
			request.setAttribute("encriptCount", encptCnt);
	        CommonMessage.debugMsg("Inside the Login Exception");
	        e.printStackTrace();
			request.getSession().setMaxInactiveInterval(60);
			request.setAttribute("loginSlideImgs",loginSldImgs);
			UIUtils.forwardRequest(request, response, "/pages/login.jsp");
			return;
		}
	}
	
	/*  Created By Karthick.T */
	private String setEncryptCount(HttpServletRequest request,HttpServletResponse response,String val)
	{
		try {
	
			String encptCnt = userServices.encryptCount(val,9);
			return encptCnt;
		} catch ( BusinessApplicationExceptions e) {
				//e.printStackTrace();			
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		return null;
	}
	private void logoutUser(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException{
		HttpSession userSession = request.getSession(true);

		try{
			Cookie tpmUserCookie = UIUtils.getCookie(request, "tpmuser");
			Cookie delCookie = new Cookie(tpmUserCookie.getName(), tpmUserCookie.getValue());
			
			//delCookie.setDomain(tpmUserCookie.getDomain());
			//delCookie.setPath(tpmUserCookie.getPath());
			delCookie.setMaxAge(0);
			
			response.setContentType("text/html");
			response.addCookie(delCookie);
			userSession.removeAttribute("admDbEnvironment");
			userSession.removeAttribute("admDbLocation");
			userSession.invalidate();
			userSession.setMaxInactiveInterval(1);
			
		}catch(Exception e)
		{	
			userSession.removeAttribute("admDbEnvironment");
			userSession.removeAttribute("admDbLocation");
		}
		request.setAttribute("loginSlideImgs",loginSldImgs);
		UIUtils.forwardRequest(request, response, "/pages/login.jsp");
		return;
		//response.sendRedirect("/perfex");
	}
	
	/*  Created By Karthick.T  HomePage Set*/
	private void saveSetHomePage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String url = request.getParameter("UscpPageuri");
		String header = request.getParameter("header");
		
		if(url.contains("?"))
			url = url.substring(0,url.indexOf("?"));
		
		String userLoginId = user.getUsrm_keyid();
		AdmTlUsercustompages newAdmTlUsercustompages = new AdmTlUsercustompages();
		
		newAdmTlUsercustompages.setUscpUsrmKeyid(userLoginId);
		newAdmTlUsercustompages.setUscpPageuri(url);
		newAdmTlUsercustompages.setUscpFormheader(header);
		
		try {
			userServices.saveSetHomePage(newAdmTlUsercustompages);
			
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();			
			successData.put("msg"," Home Page SuccessFully set!");
			returnData.put("successData", successData);	
			out.print(returnData.toString());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/*  Created By Karthick.T */
	private void recallHomePage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String userId = user.getUsrm_keyid();
		
		try {
			//AdmTlUsercustompages admTlUsercustompages = userServices.recallHomePage(userId);
			//CommonMessage.debugMsg("homepage url...."+admTlUsercustompages.getUscpPageuri());
			List<String []> recallHomePage = userServices.getMenuMasterData(userId);
			JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			if(recallHomePage != null && recallHomePage.size() > 0){
				successData.put("isMaster",recallHomePage.get(0)[0]);
				successData.put("isFilter",recallHomePage.get(0)[1]);
				successData.put("relatedFilter",recallHomePage.get(0)[2]);
				successData.put("formName",recallHomePage.get(0)[3].trim());
				successData.put("url",recallHomePage.get(0)[4].trim());				
				returnData.put("successData", successData);
			}
			CommonMessage.debugMsg(" getMenuMasterData getMenuMasterData" +returnData.toString());
			out.print(returnData.toString());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	private void saveNewPassword(HttpServletRequest request, HttpServletResponse response,ChangePwdBean changePwdBean) throws IOException
	{
		//HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try {
    		
	    	if(user == null){
	    	
	    		user = new AdmTlUsermst();
	    		String userName = request.getParameter("txtcpwdUsername");
	    		String oldpwd = request.getParameter("txtcpwdOldpwd");
	    		if(UIUtils.isValidKeyId(userName))
	    			user.setUsrm_loginid(userName);
	    		if(UIUtils.isValidKeyId(oldpwd))
	    			user.setUsrm_password(oldpwd);
	    		//user = userServices.validateLogin(user);
	    		//AdmTlUsermstDaoImpl  admTlUsermstDaoImpl = null ; 
	    		user = userServices.getUserByLogin(userName);
	    		
	    	
	    	}
	    	
    		if(  changePwdBean == null)
    			changePwdBean = new ChangePwdBean();
    		
    		String oldPwd = request.getParameter("txtcpwdOldpwd");
    		String newPwd = request.getParameter("txtcpwdNewpwd");
    		String confPwd = request.getParameter("txtcpwdConfpwd");
    		String userName = request.getParameter("txtcpwdUsername");
    		
    		
    		changePwdBean.setCpwdOldpwd(oldPwd); 
    		changePwdBean.setCpwdNewpwd(newPwd);
    		changePwdBean.setCpwdConfpwd(confPwd);
    		changePwdBean.setCpwdUsername(userName);
    		user.setUsrm_loginatempt("0");
	    	//changePwdBean =(ChangePwdBean)UIUtils.setBeanProperties((Object)changePwdBean,request);
	    	userServices.saveNewPassword(user,changePwdBean);
	    	
	    	JSONObject returnData = new JSONObject();
			JSONObject successData = new JSONObject();
			successData.put("msg","Password Changed Successfully");
			returnData.put("successData", successData);				
			CommonMessage.debugMsg(returnData.toString());
			out.print(returnData.toString());
		
    	}
		catch ( ValidationExceptions e){
			if(e.getObject() == null )
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
				out.print(errMessage.toString());
			}
			else
			{ 	CommonMessage.debugMsg("e.toString() 123 " + e.toString());
				AdmTlLoginframework admTlLoginframework = (AdmTlLoginframework) e.getObject();				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
				
				String errormsg = errMessage.toString();
				
				CommonMessage.debugMsg("errormsg" + errormsg);
				
				if(errormsg.indexOf("<Minnumber>")>0)
				{
					errormsg = errormsg.replace("<Minnumber>", admTlLoginframework.getLgfrMaxpasslength());
				}
				if(errormsg.indexOf("<Maxnumber>")>0)
				{
					errormsg = errormsg.replace("<Maxnumber>", admTlLoginframework.getLgfrMinpasslength());
				}
				if(errormsg.indexOf("<Minaph>")>0)
				{
					errormsg = errormsg.replace("<Minaph>", admTlLoginframework.getLgfrAlphabets());
				}
				if(errormsg.indexOf("<Minnum>")>0)
				{
					errormsg = errormsg.replace("<Minnum>", admTlLoginframework.getLgfrNumerals());
				}
				if(errormsg.indexOf("<Minnum>")>0)
				{
					errormsg = errormsg.replace("<Minnum>", admTlLoginframework.getLgfrNumerals());
				}
				if(errormsg.indexOf("<Minnum>")>0)
				{
					errormsg = errormsg.replace("<Minnum>", admTlLoginframework.getLgfrNumerals());
				}
				if(errormsg.indexOf("Password Should have at least One  Character")>0)
				{   CommonMessage.debugMsg(" Inside <special>");
					//errormsg = errormsg.replace("\\", "");
				}
				
				
				CommonMessage.debugMsg("errormsg at last" + errormsg);
				out.print(errormsg);			
				
			}
		} catch (BusinessApplicationExceptions e) {
			throw new IOException(e.getMessage() );
		}catch(Exception e){
			JSONObject err = new JSONObject();
			err.put("tpmException",e.getMessage());
			out.print(err.toString());
		} 
	}
	
//	private String getSchema(){
//		String dbUser ="";
//		try{
//			//dbUser = ConnectDb.getDataBaseUser();
//			dbUser = userServices.getAdmTlUsermstDao().getDbActionTemplate().getDataSource().getConnection().getMetaData().getUserName();//  getUser();
//			CommonMessage.debugMsg(dbUser + " dbUser dbUserdbUser ");
//			if(dbUser.endsWith("trn"))
//				dbUser = "TRAINING";
//			else if (dbUser.endsWith("dev"))
//				dbUser = "DEVELOPMENT";
//			else if (dbUser.endsWith("prd"))
//				dbUser = "PRODUCTION";
//			
//			
//		}catch(Exception e) {
//			
//			dbUser ="";
//		}
//		return dbUser;
//	}
	private String getSchema() {
	    String dbUser = "";
	    try (Connection conn = userServices.getAdmTlUsermstDao()
	            .getDbActionTemplate()
	            .getDataSource()
	            .getConnection()) {

	        dbUser = conn.getMetaData().getUserName();
	        CommonMessage.debugMsg(dbUser + " dbUser dbUserdbUser ");

	        if (dbUser.endsWith("trn"))
	            dbUser = "TRAINING";
	        else if (dbUser.endsWith("dev"))
	            dbUser = "DEVELOPMENT";
	        else if (dbUser.endsWith("prd"))
	            dbUser = "PGPRD";

	    } catch (Exception e) {
	        e.printStackTrace();
	        dbUser = "";
	    }
	    return dbUser;
	}
	
	public int  loginFailsAttempts(HttpServletRequest request, HttpServletResponse response)
	{
		
		return 3;
	}
	
	private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setEnableFilter(false);
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", ""));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			
			if(i == 0 || i == 2 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17 )
			{
				jqGridColModel.setHidden(true);
			}
			
			
			
			CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "30%%");
		tableModel.set("tableWidth",  "95%%");
		return tableModel;
	}
	
/*	private void sendTestMail(){
		final String username = "prasanth1729@yahoo.co.in";
		final String password = "";
 
		String d_email = "prasanth1729@yahoo.co.in",
		
		d_host =  "localhost",//"smtp.mail.yahoo.com",
		d_port = "465",
		m_to = "prasanth1729@gmail.com",
		m_subject = "Testing",
		m_text = "Hey, this is the testing email.";
		
		Properties props = new Properties();
		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class", "jajakartaet.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallb", "false");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		
		try
		{
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(m_text);
			msg.setSubject(m_subject);
			msg.setFrom(new InternetAddress(d_email));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
			Transport.send(msg);
		}
		catch (Exception mex)
		{
			mex.printStackTrace();
		}
	}
	*/
	
/*	private Object getServiceObject(HttpServletRequest request,){
		
	}
*/

	private boolean checkpwdchangegap(String loginid) throws Exception  
	{  
			AdmTlLoginframework admTlLoginframework = userServices.getloginframeworkdata();
			if(admTlLoginframework.getLgfrPassneverexpires().equals("N"))
			{
									
				String pwdchangegap =  userServices.checkallowtochangepwd(loginid);
				
				int minchnggape =  Integer.parseInt(admTlLoginframework.getLgfrminpwdchangedays());
				if ( Integer.parseInt(pwdchangegap) <= minchnggape)
				{
					return true;
				}
				
				
				
			}
		
	return false;
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
	
	private void validateUserForMail(HttpServletRequest request, HttpServletResponse response,String loginId) throws ServletException, IOException{
		PrintWriter out =response.getWriter();
		
		try {
			
			CommonMessage.debugMsg("From User Servlet");
			if(!UIUtils.isValidKeyId(loginId)){
				CommonMessage.debugMsg("From User Servlet");
				throw new ValidationExceptions("Usrm_loginid-required,");
			}
			String mailIds= userServices.getForgetValidation(loginId);
			CommonMessage.debugMsg("MailIds :" +mailIds);			
				
			JSONObject msg =new JSONObject();
			msg.put("succ", "true");
		    msg.put("ref", mailIds);
		 	out.print(msg);			
		}
		catch( ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
			CommonMessage.debugMsg("validateUserLogin err " + e.getMessage());
			out.print(errMessage);
		
		}catch (BusinessApplicationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		
		} /*
			 * catch(NotesException e) {
			 * CommonMessage.debugMsg(" errorr while sending ... NotesException" );
			 * CommonMessage.debugMsg(" Notes Error 1 :  " + e);
			 * CommonMessage.debugMsg(" Notes Error 2 :  " + e.id);
			 * CommonMessage.debugMsg(" Notes Error 3 :  " + e.text);
			 * //CommonMessage.debugMsg(" Notes Error 4:  " + e.getMessage());
			 * out.print(e.toString()); }
			 */catch(Exception e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		} 
	}
	
	private void sendPwdMailNew(HttpServletRequest request, HttpServletResponse response,String loginId) throws ServletException, IOException{
		PrintWriter out =response.getWriter();
		
		try {
			
			/*if(!UIUtils.isValidKeyId(loginId)){
				CommonMessage.debugMsg("From User Servlet");
				throw new ValidationExceptions("Usrm_loginid-required,");
			}*/
			List<String[]> empData = userServices.getForgetEmpMailIds(loginId);
			String password="";
			if(!empData.isEmpty()){
				password=empData.get(0)[0];
				CommonMessage.debugMsg("Password: " +password);
			}
			
			String mailIds = buildToMailIds(empData);
			CommonMessage.debugMsg("MailIds :" +mailIds);			
				
			/*if( mailIds.isEmpty())
			{	throw new Exception("EmailId-required");
				//throw new BusinessApplicationExceptions("INVALID_EMAIL");
			}*/
			
//			String content = "Login Id: " + loginId +", ";
//			String disclaimerNote = "--";			
			StringBuilder subject = new StringBuilder("");
			subject.append(" Reset Password ");
			subject.append(password);			
			StringBuilder totalContent = new StringBuilder();
//			totalContent.append(content);
//			totalContent.append("\r\n");
//			
//			totalContent.append("Dear " + loginId +",");
//			totalContent.append("\r\n");
//			totalContent.append("\r\n");	
//			totalContent.append("Your perfex Password is :  "+password);
//			//totalContent.append("Password: " +password);
//			totalContent.append("\r\n");
//			totalContent.append("\r\n");
//			totalContent.append("\r\n");
//			
//			totalContent.append(disclaimerNote);
//			totalContent.append("\r\n");
//			totalContent.append("Regards,");
//			totalContent.append("\r\n");
//			totalContent.append("Perfex 360 Support Team");
			totalContent.append("Login Id: ").append(loginId).append("<br><br>");

			totalContent.append("Dear ").append(loginId).append(",<br><br>");

			totalContent.append("Your Perfex Password is: <b>")
			            .append(password)
			            .append("</b><br><br><br>");

			totalContent.append("--<br>");
			totalContent.append("Regards,<br>");
			totalContent.append("Perfex 360 Support Team");
			
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),null);
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			JSONObject msg =new JSONObject();
			msg.put("succ", "New password is sent to your registered E-mail : " + mailIds);
		 	out.print(msg);
			
		}
		catch( ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "LoginExceptions");
			CommonMessage.debugMsg("validateUserLogin err " + e.getMessage());
			out.print(errMessage);
		
		}catch (BusinessApplicationExceptions e) {
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		
		} /*
			 * catch(NotesException e) {
			 * CommonMessage.debugMsg(" errorr while sending ... NotesException" );
			 * CommonMessage.debugMsg(" Notes Error 1 :  " + e);
			 * CommonMessage.debugMsg(" Notes Error 2 :  " + e.id);
			 * CommonMessage.debugMsg(" Notes Error 3 :  " + e.text);
			 * //CommonMessage.debugMsg(" Notes Error 4:  " + e.getMessage());
			 * out.print(e.toString()); }
			 */catch(Exception e){
			
			/*CommonMessage.debugMsg(e.getMessage());
			JSONObject err = new JSONObject();
			err.put("msg", e.getMessage());
			out.print(err.toString());*/
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "LoginExceptions");
			out.print(errMessage);
		} 
		
		
	}


}


