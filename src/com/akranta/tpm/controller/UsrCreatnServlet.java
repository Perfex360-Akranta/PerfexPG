package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

//import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AdmTlUserRollBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.UserBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.UserServices;
import com.akranta.tpm.service.impl.UserServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class UsrCreatnServlet extends HttpServlet {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserServices userServices;
	public UsrCreatnServlet() {
		super();
        // TODO Auto-generated constructor stub
        /*try {
        	userServices = new UserServiceImpl();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
	 }
	    
	    
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}
		
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action:"+action);
		try {
			userServices = (UserServiceImpl) UIUtils.getServiceObject(request,
					"UserServiceImpl");
			
			userServices.UserServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );

		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		response.setContentType("text/html");
		response.setContentType("text/json");
		String dispatchUrl = null;

		if (action.equals("user_view.creat")) {

			dispatchUrl = "/pages/UserGrid.jsp";

		}
		else if (action.equals("switchUser_input.creat")) {
			dispatchUrl = "/tiles/switchUser.jsp";
		
		}
		
		else if (action.equals("userform_input.creat")) {

				String keyid = request.getParameter("keyId");

				String loginId = request.getParameter("loginId");
				CommonMessage.debugMsg("loginId "+loginId);
				String Mode = request.getParameter("mode");
				String username=request.getParameter("employeename");
				String remarks=request.getParameter("remarks");
				
				String department=request.getParameter("department"); 
				
				FormModes mode = FormModes.create;
				
				String loc=request.getParameter("loc");
				CommonMessage.debugMsg("Loc"+loc);
				CommonMessage.debugMsg("Mode "+Mode);
				if (Mode != null && Mode.equals(FormModeConsts.modify)) {
					mode = FormModes.modify;
				}
				UserBean userBean = new UserBean(mode);
				request.setAttribute("userBean", userBean);
				if (UIUtils.isValidKeyId(keyid)) // && userEvent == null) ||(
													// userEvent != null && !
													// "new".equals(userEvent))
				{

					try {

						AdmTlUsermst admTlUsermst = userServices.select(loginId);
						admTlUsermst.setUsrm_validfrom(CommonFunctions.pg_getDateFromPGTimeStamp(admTlUsermst.getUsrm_validfrom()));
						admTlUsermst.setUsrm_validtill(CommonFunctions.pg_getDateFromPGTimeStamp(admTlUsermst.getUsrm_validtill()));
						CommonMessage.debugMsg(admTlUsermst.getUsrm_remarks()+"Testing");
						admTlUsermst.setUsrm_username(username);
						admTlUsermst.setUsrm_remarks(remarks);
						String phNo = admTlUsermst.getUsrm_extensionphone();
						if (phNo.equals("-99")) {
							admTlUsermst.setUsrm_extensionphone(null);
						}
						httpSession.setAttribute("admTlUsermst", admTlUsermst);
						httpSession.removeAttribute("userId");
						httpSession.setAttribute("userId", keyid);
						
						request.setAttribute("admTlUsermst", admTlUsermst);
						request.removeAttribute("userBean");
						request.setAttribute("userBean", userBean);
						
						request.removeAttribute("department");
						request.removeAttribute("loc");
						request.setAttribute("loc", loc);
						request.setAttribute("department", department);
					}

					catch (Exception e) {

						e.printStackTrace();
					}

				}

				dispatchUrl = "/pages/usercreation.jsp";
			} else if (action.equals("getModeuser_view.creat")) {
			response.setContentType("text/html");
			// response.setContentType("text/json");

			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();

			result.put("mode", "create");
			result.put("url", "userform_input.creat");
			result.put("formHeader", "User Master");

			out.println(result);

		} else if (action.equals("user_getCol.creat")) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");

			String tableModel = UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.UserGridCreation", "UserGrid");

			JSONObject colmodel = JSONObject.fromString(tableModel);

			httpSession.removeAttribute("UserColmodel");
			httpSession.setAttribute("UserColmodel", colmodel);
			out.println(colmodel);
			out.close();
		} else if (action.equals("user_getData.creat")) {

			PrintWriter out = response.getWriter();
			GridParams gridParams = (GridParams) httpSession
					.getAttribute("gridParams");
			httpSession.setAttribute("gridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request, gridParams);

			List<String[]> UserList;
			try {
				UserList = userServices.getUser(gridParams);

				httpSession.removeAttribute("gridParams");
				// CommonMessage.debugMsg("gridParamsss=>"+gridParams.getFromRow()+"--"+gridParams.getToRow()+"--"+gridParams.getTotalRecordCnt());
				
				JSONObject UserData = UIUtils
						.convertToJqGridTableObject(UserList, request, 1, 1,
								gridParams.getTotalRecordCnt());

				out.println(UserData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (action.equals("AddRoll_input.creat")) {
			String userId = request.getParameter("userId");
			CommonMessage.debugMsg("userId in fn call:"+userId);
			httpSession.removeAttribute("userId");
			httpSession.setAttribute("userId", userId);
			request.setAttribute("userId", userId);

			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/addRoll.jsp");
			rd.forward(request, response);
		} else if (action.equals("AddRoll_getCol.creat")) {
			String userId = request.getParameter("userId");
			String fromUserform = null;

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");

			String tableModel = UIUtils.getPropertyValue(
					"com.akranta.tpm.resources.UserGridCreation",
					"colModelAddRoll");
			// CommonMessage.debugMsg(tableModel);
			JSONObject colmodel = JSONObject.fromString(tableModel);

			fromUserform = request.getParameter("fromUserform");

			if (fromUserform.equals("true")) {
				colmodel.set("tableWidth", "32.7%%");
				colmodel.set("tableHeight", "50%%");

				colmodel.getJSONArray("colModel").getJSONObject(2)
						.set("hidden", true);
				colmodel.getJSONArray("colModel").getJSONObject(1)
						.set("width", 215);

			}

			httpSession.removeAttribute("UserRollColmodel");
			httpSession.setAttribute("UserRollColmodel", colmodel);
			httpSession.removeAttribute("keyID");
			httpSession.setAttribute("keyID", userId);
			out.println(colmodel);
			out.close();
		} else if (action.equals("AddRoll_getData.creat")) {

			PrintWriter out = response.getWriter();

			String userId = (String) httpSession.getAttribute("keyID");

			List<String[]> UserList;
			List<String[]> Userdata;
			try {
				UserList = userServices.getUserRoll(userId);
				Userdata = userServices.getUserRollS(userId);
				
				for(String[] arr:Userdata) 
				{
					CommonMessage.debugMsg(Arrays.toString(arr));
				}
				String RollId = null;
				String createdOn = null;
				if (Userdata.size() > 0) {
					RollId = Userdata.get(0)[1];
					CommonMessage.debugMsg("RollId in get data" + RollId);
					createdOn = Userdata.get(0)[4];
				}

				httpSession.removeAttribute("RollId");
				httpSession.setAttribute("RollId", RollId);
				httpSession.removeAttribute("createdOn");
				httpSession.setAttribute("createdOn", createdOn);
				JSONObject UserData = UIUtils.convertToJqGridTableObject(
						UserList, request, 0, 0);

				out.println(UserData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (action.equals("combo_ccno.creat")) {

			String ccno = request.getParameter("ccno");

			try {
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> DetectedBy = userServices.getCcnoComboList(ccno,
						currentFilter);
				UIUtils.writeComboBox(response, DetectedBy,currentFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else if (action.equals("Combo_UserRoll.creat")) {
			//CommonMessage.debugMsg("action=>" + action);
			try {
				String filter = request.getParameter("loginUserRole");
				//CommonMessage.debugMsg("loginUserRolefilter" + filter );
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				String empId = null;
				if( "Y".equals( filter) ){
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					empId =  user.getUsrm_ccno();
				}
				//CommonMessage.debugMsg("loginUserRolefilter empId" + empId );
				List<ComboBox> userRoles = userServices.getUserRollComboList(currentFilter,empId);
				UIUtils.writeComboBox(response, userRoles,currentFilter);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}		

		else if (action.equals("combo_profid.creat")) {
			try {
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> DetectedBy = userServices
						.getProfidComboList(currentFilter);
				UIUtils.writeComboBox(response, DetectedBy,currentFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		else if (action.equals("combo_userid.creat")) {
			try {
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> DetectedBy = userServices
						.getUseridComboList(currentFilter);
				UIUtils.writeComboBox(response, DetectedBy,currentFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else if (action.equals("userform_save.creat")) {

			String from = request.getParameter("from");
			httpSession.setAttribute("from", from);

			// request.setAttribute("from", from);
			UserBean userBean = new UserBean();
			saveUser(request, response, userBean);

		} else if (action.equals("userform_delete.creat")) {

			UserBean userBean = new UserBean();
			DeleteUser(request, response, userBean);

		} else if (action.equals("userRoll_save.creat")) {

			AdmTlUserRollBean admTlUserRollBean = new AdmTlUserRollBean();
			saveUserRoll(request, response, admTlUserRollBean);
		} else if (action.equals("userRoll_delete.creat")) {

			try {
				String status = null;
				PrintWriter outdel = response.getWriter();
				String rollId = request.getParameter("Id");
				String userId = request.getParameter("userId");	
				status = userServices.DeleteUserRoll(rollId,userId);

				if (status.equals("success")) {
					JSONObject successData = new JSONObject();

					successData.put("msg", UIUtils.getPropertyValue(
							"com.akranta.tpm.resources.CommonMessages",
							"success-delete"));
					JSONObject returnData = new JSONObject();

					returnData.put("successData", successData);
					returnData.put("formClear", false);
					// CommonMessage.debugMsg("rowId"+rowId);
					// returnData.put("rowId",request.getParameter("rowId"));
					outdel.print(returnData.toString());
					outdel.close();
				}
				// DeleteUserRoll(request,response,admTlUserRollBean);
			} catch (Exception e) {
				CommonMessage.debugMsg("gete. " + e.getMessage());
				PrintWriter out = response.getWriter();
				JSONObject err = new JSONObject();

				err.put("tpmException", "Data Not Deleted");

				out.print(err.toString());
			}

		} else if (action.equals("user_recall.creat")) {

			ServletOutputStream out = response.getOutputStream();
			String Userkeyid = request.getParameter(ReqtParamNameConst.KEYID);

			// CommonMessage.debugMsg("BachKeyid="+Bachkeyid);
			UserBean userBean = new UserBean();

			httpSession.removeAttribute("userBean");
			httpSession.setAttribute("userBean", userBean);

			AdmTlUsermst admTlUsermst;
			try {
				admTlUsermst = userServices.selectRecall(Userkeyid);
				String dept = admTlUsermst.getUsrm_departmentid();

				httpSession.removeAttribute("admTlUsermst");
				httpSession.setAttribute("admTlUsermst", admTlUsermst);

				JSONObject Userdata = UIUtils.fromTpmModel(admTlUsermst);
				JSONObject returndata = new JSONObject();
				returndata.put("Userdata", Userdata);
				out.print(returndata.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
		else if(action.equals("resetpwd_save.creat"))
		{
			String userid = request.getParameter("userid");
			ServletOutputStream out = response.getOutputStream();
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();	
			try {
				
				 userServices.resetpwd(userid);
				
				 successData.put("msg", "Account lock released");
	        	 returnData.put("successData",successData);
	        	 returnData.put("formClear",false);
	    		 out.println(returnData.toString());
	    		 
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		else if (action.equals("userfnlnempount_input.creat")) {
			

			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/FnlnEmpCount.jsp");
			rd.forward(request, response);
		}
        else if (action.equals("userfnlnempount_getCol.creat")) {
			CommonMessage.debugMsg("userfnlnempount_getCol.creat");
        	 PrintWriter out1 = response.getWriter();	
			  //httpSession=request.getSession(false);
	
		
			 
				CommonFilter commonFilter = populateCommonFilter(request,"EMPCountCommonFilter",true);		  
				commonFilter.setIsGetCol("Y");
				List<String[]> employeeWiseCount;
				try {
					employeeWiseCount = userServices.getEmployeeTotal(commonFilter);
			
				CommonMessage.debugMsg("employeeWiseCount"+employeeWiseCount);
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(true);
				gridColModel.setHeaderNum(1);
				String [] colHeader = employeeWiseCount.get(2);	
				//CommonMessage.debugMsg("The colHeader"+colHeader);
				String [] colHeaderCond = employeeWiseCount.get(1);
				//CommonMessage.debugMsg("The colHeaderCond"+colHeaderCond);
				
				
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				//CommonMessage.debugMsg("The jsonObject:::::"+jsonObject);
				//jsonObject.set("tableWidth", "110%%");
				jsonObject.set("tableWidth", "93%%");
				jsonObject.set("tableHeight", "75%%");
				
				httpSession.removeAttribute("employeeWiseCountColModel");
				httpSession.setAttribute("employeeWiseCountColModel", jsonObject);
				out1.print(jsonObject);
				}
    	 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
		}               
        else if (action.equals("userfnlnempount_getData.creat")) {
			CommonMessage.debugMsg("userfnlnempount_getData.creat");
        	PrintWriter out1 = response.getWriter();
        	//httpSession=request.getSession(false);
        	CommonFilter commonFilter = populateCommonFilter(request,"EMPCountCommonFilter",false);	
			try 
			{
				//KaizenFormBean kaizenFormBean=(KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				//String kaizenId=kaizenFormBean.getKaizenId();
				//CommonFilter commonFilter = populateCommonFilter(request,"KaizenBTSGrid",true);
				commonFilter.setIsGetCol("N");	  
				List<String []> empList  = userServices.getEmployeeTotal(commonFilter);
				JSONObject EmpData = UIUtils.convertToJqGridTableObject(empList,request,3,0);
				out1.println(EmpData);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
        else if (action.equals("userfnlnempount_getExcel.creat")) {
        	CommonFilter commonFilter = populateCommonFilter(request,"EMPCountCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);//
			httpSession = request.getSession(false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("employeeWiseCountColModel");
			colmodel.put("title","Employee Count Report");
            String format = ExcelUtils.getFormat(request);
			Workbook wb;
			try {
				wb = userServices.EmpCountExportExcel(commonFilter,colmodel,format);
			
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response,wb,"EmployeeCountReport", format);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if (action.equals("userccno_recall.creat")) {

			ServletOutputStream out = response.getOutputStream();
			String ccNo = request.getParameter(ReqtParamNameConst.KEYID);

			// CommonMessage.debugMsg("BachKeyid="+Bachkeyid);
			UserBean userBean = new UserBean();
			
			httpSession.removeAttribute("userBean");
			httpSession.setAttribute("userBean", userBean);

			List<String[]> UserList;
			List<String[]> usernamelst;
			try {
				UserList = userServices.selectCcNoRecall(ccNo);
				String UserDept = UserList.get(0)[1];
				CommonMessage.debugMsg("Dept is: "+UserDept);
				String UserDesc = UserList.get(0)[2];
				CommonMessage.debugMsg("UserDesc" + UserDesc);
				String Loginid=UserList.get(0)[3];
				CommonMessage.debugMsg("id:"+Loginid);
				String UserName = UserList.get(0)[0];
				CommonMessage.debugMsg("Dept is: "+UserName);
				JSONObject ccNodata = new JSONObject();
				JSONObject returnData = new JSONObject();
				if (UIUtils.isValidKeyId(UserDept) ) {

					ccNodata.put("UserDept", UserDept);
					
					// ccNodata.put("UserName", UserName);\
				}
				else {
					ccNodata.put("UserDept", "-");
				}
				if(UIUtils.isValidKeyId(UserDesc)){
					ccNodata.put("UserDesc", UserDesc);
				}
				else{
					ccNodata.put("UserDesc", "-");
				}
				if (UIUtils.isValidKeyId(UserName)){
					ccNodata.put("UserName", UserName);
					ccNodata.put("Loginid", Loginid);
				}
				returnData.put("ccNodata", ccNodata);
				out.print(returnData.toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (action.equals("user_getExcel.creat")) {
			JSONObject colmodel = (JSONObject) httpSession
					.getAttribute("UserColmodel");
			CommonMessage.debugMsg("Inside get excel action:colmodel= " + colmodel);
			colmodel.put("title","User Master");//title created by vijay
			String format = ExcelUtils.getFormat(request);

			Workbook wb;
			try {
				
				GridParams gridParams = (GridParams) httpSession
						.getAttribute("gridParams");
				httpSession.setAttribute("gridParams", gridParams);
				if (gridParams == null)
					gridParams = new GridParams();
				FilterValues.populateGridParams(request, gridParams);
				

				wb = userServices.UserExportExcel(colmodel, format, gridParams);
				ExcelUtils.writeToResponse(response, wb, "UserCreationReport",
						
						format);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//*******************
		if (action.equals("userRealease_input.creat")) {
			CommonMessage.debugMsg("------userRealease_input.creat");
			dispatchUrl = "/pages/LocUserGrd.jsp";

		}else if (action.equals("userRealeaseMannul_input.creat")) {
			CommonMessage.debugMsg("------userRealeaseMannul_input.creat");
			dispatchUrl = "/pages/LocUserGrdMannul.jsp";

		}else if (action.equals("userLocRealease_save.creat")) {
			CommonMessage.debugMsg("-----userRealease_save-----------");
			
			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			try {
				if (httpSession != null && user != null) {
					
					String jsonArrO=request.getParameter("jsonArrO");
					String ids[]=jsonArrO.split(";");
					List<String> loginIds=Arrays.asList(ids);
				
					if (!loginIds.isEmpty()) {
						userServices.resetpwd(loginIds);
						JSONObject successData = new JSONObject();
						//successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-save"));
						successData.put("msg", "Set To Default Password Success");
						//successData.put("title", title);
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
			
			
		}else if (action.equals("userLocRealeaseOnly_save.creat")) {
			CommonMessage.debugMsg("-----userRealease_save-----------");
			
			httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			try {
				if (httpSession != null && user != null) {
					
					String jsonArrO=request.getParameter("jsonArrO");
					String ids[]=jsonArrO.split(";");
					List<String> loginIds=Arrays.asList(ids);
				
					if (!loginIds.isEmpty()) {
						//userServices.resetpwd(loginIds);
						for(String userid : loginIds){
							CommonMessage.debugMsg("userid:" +userid);
							userServices.resetpwd(userid);
						}
						JSONObject successData = new JSONObject();
						//successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",	"success-save"));
						successData.put("msg", "Account lock released");
						//successData.put("title", title);
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
			
			
		}else if (action.equals("userRealease_getCol.creat")) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.UserGridCreation", "LocUserGrid");

			JSONObject colmodel = JSONObject.fromString(tableModel);

			httpSession.removeAttribute("UserColmodel");
			httpSession.setAttribute("UserColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		else if (action.equals("userRealease_getData.creat")) {
			
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
				UserList = userServices.getLocUser(gridParams,location);

				httpSession.removeAttribute("gridParams");			
				
				JSONObject UserData = UIUtils.convertToJqGridTableObject(UserList, request, 1, 1,gridParams.getTotalRecordCnt());//changed here

				out.println(UserData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else if (action.equals("userRealeaseMannul_getCol.creat")) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.UserGridCreation", "LocUserGrid");

			JSONObject colmodel = JSONObject.fromString(tableModel);

			httpSession.removeAttribute("UserColmodel");
			httpSession.setAttribute("UserColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}else if (action.equals("userRealeaseMannul_getData.creat")) {
			
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
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg("gridParams " +gridParams);
			List<String[]> UserList;
			try {
				UserList = userServices.getLocUserMannul(gridParams,flid);

				httpSession.removeAttribute("gridParams");			
				
				JSONObject UserData = UIUtils.convertToJqGridTableObject(UserList, request, 1, 1,gridParams.getTotalRecordCnt());//changed here

				out.println(UserData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (action.equals("userRealease_getExcel.creat")) {
			
			//JSONObject colmodel = (JSONObject) httpSession.getAttribute("UserColmodel");

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.UserGridCreation", "LocUserGrid");

			JSONObject colmodel = JSONObject.fromString(tableModel);

			colmodel.put("title", "Lock User Report");
			CommonMessage.debugMsg("Inside get excel action:colmodel= " + colmodel);
			String format = ExcelUtils.getFormat(request);

	Workbook wb;
	try {
		
		GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
		httpSession.setAttribute("gridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		CommonMessage.debugMsg("commonFilter.getLocation().getId() " +commonFilter.getLocation().getId());
		String location=commonFilter.getLocation().getId();

		wb = userServices.LocUserExportExcel(colmodel, format, gridParams,location);
		ExcelUtils.writeToResponse(response, wb, "RealeaseLogReport",format);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
		
		//*******
		if (dispatchUrl != null) {

			UIUtils.forwardRequest(request, response, dispatchUrl);
		}
	}
		private void saveUser(HttpServletRequest request,
				HttpServletResponse response, UserBean userBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			if( httpSession != null && user != null)
	    	{
				AdmTlUsermst newAdmTlUsermst=new AdmTlUsermst();
				AdmTlUsermst existAdmTlUsermst = (AdmTlUsermst)httpSession.getAttribute("admTlUsermst");
				
				//CommonMessage.debugMsg("existAdmTlUsermst keyid:"+existAdmTlUsermst.getUsrm_keyid());				
				newAdmTlUsermst.setUsrm_createdby(user.getUsrm_ccno());
				String confPwd=request.getParameter("confPwd");
				String mode=request.getParameter("mode");
				String chkIsdefPwd = request.getParameter("chkIsdefPwd");
				CommonMessage.debugMsg("confPwd:"+confPwd);
				String pwd=request.getParameter("txtUsrm_password");
				
				newAdmTlUsermst=(AdmTlUsermst)UIUtils.setBeanProperties((Object)newAdmTlUsermst,request);
				userBean =(UserBean) UIUtils.setBeanProperties((Object)userBean,request);
				
				newAdmTlUsermst.setUsrm_password(pwd);
				
				newAdmTlUsermst.setUsrm_defaultpassword(request.getParameter("txtUsrm_loginid"));
				
				
				userBean.setChkIsdefPwd(chkIsdefPwd);
				CommonMessage.debugMsg("newAdmTlUsermst.getUsrm_defaultpassword():"+newAdmTlUsermst.getUsrm_defaultpassword());
				String comp=newAdmTlUsermst.getUsrm_keyid();
				try
				{   
					
					if(newAdmTlUsermst.getUsrm_keyid()==null)
					{
						CommonMessage.debugMsg("save");
						existAdmTlUsermst=userServices.create(newAdmTlUsermst,existAdmTlUsermst,userBean,confPwd);
					}
					else
					{
						CommonMessage.debugMsg("update");
						String defpass = request.getParameter("txtUsrm_defaultpassword");
						CommonMessage.debugMsg("defpass" + defpass);
						//newAdmTlUsermst.setUsrm_defaultpassword(request.getParameter("txtUsrm_defaultpassword"));
						existAdmTlUsermst=userServices.update(newAdmTlUsermst,existAdmTlUsermst,userBean,confPwd);
					}
					httpSession.setAttribute( "admTlUsermst" +  newAdmTlUsermst.getUsrm_keyid(), existAdmTlUsermst);
					httpSession.setAttribute("admTlUsermst", existAdmTlUsermst);
					String formBeanIdentifier = "userBean"+userBean.getFormActionMode();
					//CommonMessage.debugMsg(" formBeanIdentifier in save"+formBeanIdentifier);
					httpSession.setAttribute(formBeanIdentifier,userBean);
					String msgPropertyIdnt;	
					if(comp==null)
					{
						msgPropertyIdnt="success-save";
					}
					else
					{
						msgPropertyIdnt="success-update";
					}
					
					//JSONObject mode = new JSONObject(); 
					//mode.put("formMode",batchBean.getFormActionMode());
					JSONObject successData = new JSONObject(); 
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("UserId",existAdmTlUsermst.getUsrm_keyid() );
					String from=(String) httpSession.getAttribute("from");
					
					if(from.equals("btnClick"))
						successData.put("from","btnClick" );	
					JSONObject result = new JSONObject();
					result.put("formClear",false);
					result.put("successData",successData );
					//httpSession.removeAttribute("admTlUsermst");
					//PrintWriter  out1 = response.getWriter();
					out.print(result.toString());
					out.close();
					//mode.put("forwardData",forwardData);
					//mode.put("persistentData", persistentData);
					
				}
				catch(ValidationExceptions e)
				{
					CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"UserCreation");
					errMessage.put("fromMode",userBean.getFormActionMode());
					out.print(errMessage.toString());
						
				}catch(Exception e)
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
	    	}
			
		}
		private void DeleteUser(HttpServletRequest request,HttpServletResponse response, UserBean userBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		CommonMessage.debugMsg("delete");
	    		AdmTlUsermst newAdmTlUsermst = new AdmTlUsermst();
	    		AdmTlUsermst existAdmTlUsermst = (AdmTlUsermst)httpSession.getAttribute("admTlUsermst");
	    		newAdmTlUsermst.setUsrm_createdby(user.getUsrm_ccno());
	    		
	    		newAdmTlUsermst =(AdmTlUsermst)UIUtils.setBeanProperties((Object)newAdmTlUsermst,request);
	    		userBean =(UserBean)UIUtils.setBeanProperties((Object)userBean,request);
	    		userBean =(UserBean) UIUtils.setBeanProperties((Object)userBean,request);
	    
				try{
								
					existAdmTlUsermst = userServices.delete(newAdmTlUsermst);						
					httpSession.setAttribute(existAdmTlUsermst.getUsrm_keyid(), existAdmTlUsermst);
					httpSession.setAttribute("admTlUsermst", existAdmTlUsermst);
					String formBeanIdentifier = "userBean"+userBean.getFormActionMode();
					httpSession.setAttribute(formBeanIdentifier,userBean);
								
					JSONObject mode = new JSONObject();
					mode.put("formMode",userBean.getFormActionMode());
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("UserKeyid",existAdmTlUsermst.getUsrm_keyid());
					persistentData.put("fromBean", formBeanIdentifier);
					
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					//successData.put("msg","Data Deleted Successfully");
					successData.put("mode",userBean.getFormActionMode() );
					successData.put("keyId", existAdmTlUsermst.getUsrm_keyid());
					
					JSONObject result = new JSONObject();
					result.put("displyMsg", true);
					result.put("formClear",false);
					result.put("successData", successData);	
					httpSession.removeAttribute("admTlUsermst");
					String msg=result.toString();
					
					out.print(result.toString());
				}catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"UserCreation");//properties file name
					errMessage.put("fromMode",userBean.getFormActionMode());
					out.print(errMessage.toString());
						
				}
				catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("Error Servler e -"+e.getMessage());
					String msg =e!= null ? e.getMessage() : null;
					if(msg!= null && msg.contains("ORA-02292") && msg.contains("FK_") )
					{
						msg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
						JSONObject err = new JSONObject();
					//	JSONObject confirm = new JSONObject();
						//confirm.put("confirm",msg);
						err.put("tpmException",msg);
					//	err.put("displyMsg",true);
						out.print(err.toString());
						out.close();
					//JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"BatchCreation");
					//out.print(errMessage.toString());
					//CommonMessage.debugMsg(" error in buisness validation ");
					}
						
				}
				catch(Exception e)
				{
					JSONObject err = new JSONObject();
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
					out.print(err.toString());
				}
		    }
			
		}
		private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
			HttpSession httpSession = request.getSession(true);
			
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			
			if( commonFilter != null && ! createNew ){
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.setPaginationParams(request,commonFilter);
			}	
			else{
				commonFilter =  new CommonFilter();
				commonFilter = 	FilterValues.getCommonFilters(request,commonFilter); // getFilterValues(request);
				commonFilter = 	FilterValues.getOPLandKaizen(request,commonFilter);
				commonFilter.setFirstLevel("Y");
				//commonFilter.setViewClick('Y');
				//CommonMessage.debugMsg(" Month Checking "+commonFilter.getFromMonth());
				/*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
					  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
					  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					  commonFilter.setMonwise("Y");
			 	}*/
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
  			AdmTlUsermst user = UIUtils.getLoginUser(request);
			commonFilter.setAbnDetectBy(user.getUsrm_ccno());

			return commonFilter;
		}
		
		
		private void saveUserRoll(HttpServletRequest request, HttpServletResponse response,AdmTlUserRollBean admTlUserRollBean ) throws IOException{
			
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	//String RollId=(String) httpSession.getAttribute("RollId");
    	
    	String RollId=request.getParameter("cmbArulRoleid");//Changed this line
    	
    	String userId =(String) httpSession.getAttribute("userId");
    	CommonMessage.debugMsg("userId in role:"+userId);
    	if( httpSession != null && user != null)
    	{	
    		AdmTlUserRoleLink newAdmTlUserRoleLink = new AdmTlUserRoleLink();
    		newAdmTlUserRoleLink.setArulCreatedby(user.getUsrm_ccno());
    		  String newRole=newAdmTlUserRoleLink.getArulRoleid();
     		   
    		newAdmTlUserRoleLink =(AdmTlUserRoleLink)UIUtils.setBeanProperties((Object)newAdmTlUserRoleLink,request);
    		newAdmTlUserRoleLink.setArulUserid(userId);
    		
    		admTlUserRollBean =(AdmTlUserRollBean) UIUtils.setBeanProperties((Object)admTlUserRollBean,request);
    		  newRole=newAdmTlUserRoleLink.getArulRoleid();
     		   
    		AdmTlUserRoleLink existAdmTlUserRoleLink=new AdmTlUserRoleLink();
    		existAdmTlUserRoleLink.setArulCreatedon((String)httpSession.getAttribute("createdOn"));
			String createdOn=existAdmTlUserRoleLink.getArulCreatedon();
			
    		 
    		
			
			try{
					boolean insert = true;
					
					
					if( RollId == null ||!(RollId.equals(newRole)) )
					{
						
						existAdmTlUserRoleLink = userServices.CreateUserRoll(newAdmTlUserRoleLink,existAdmTlUserRoleLink,admTlUserRollBean);
						
					}
					
					else{
						CommonMessage.debugMsg("Inside update");
						existAdmTlUserRoleLink = userServices.UpdateUserRoll(newAdmTlUserRoleLink,existAdmTlUserRoleLink,admTlUserRollBean);
						insert = false;
					}//Uncommented this line
					new JSONObject();
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("UserRollId",existAdmTlUserRoleLink.getArulRoleid());
					
					
				    String msgPropertyIdnt;				
				 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						 msgPropertyIdnt = "success-update";
					 JSONObject successData = new JSONObject(); 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",admTlUserRollBean.getFormActionMode() );
					successData.put("keyId", existAdmTlUserRoleLink.getArulUserid());
					JSONObject returnData = new JSONObject();	
					
					returnData.put("successData", successData);
						
					//httpSession.removeAttribute("EntEmpMangerLinkServlet");
					httpSession.setAttribute( "UsrerRollCreation", existAdmTlUserRoleLink );
					//httpSession.removeAttribute("genTlCompanymstBean");
					
					out.print(returnData.toString());
					out.close();
				
			}catch(BusinessApplicationExceptions e)
			{ 
				
				JSONObject errMessage = new JSONObject();
				errMessage.put("tpmException", "User Role Already Exists For This User");
				out.print(errMessage.toString());
			}			
			catch(ValidationExceptions e)
			{
				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"UseRollCreation");
				errMessage.put("fromMode",admTlUserRollBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch(Exception e)
			{
				
				JSONObject err = new JSONObject();
				if(e.getMessage() == null)
					err.put("tpmException", "User Roll Already Exist For This User");
				else{
					err.put("tpmException", "Data Not Saved");
				}
				out.print(err.toString());
			}
			
	    }	
    	
    	
    	
    	}
		/*private void DeleteUserRoll(HttpServletRequest request,
				HttpServletResponse response, AdmTlUserRollBean admTlUserRollBean) throws Exception
		{
			HttpSession httpSession = request.getSession(false);
			PrintWriter outdel = response.getWriter();
			//ServletOutputStream outdel = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String rowId=request.getParameter("rowId");
			CommonMessage.debugMsg("rowId during delete=>"+rowId);
			if( httpSession != null && user != null)
			{	
				AdmTlUserRoleLink admTlUserRoleLink = new AdmTlUserRoleLink();
				admTlUserRoleLink.setArulCreatedby(user.getUsrm_ccno());
				admTlUserRoleLink =(AdmTlUserRoleLink)UIUtils.setBeanProperties((Object)admTlUserRoleLink,request);
				httpSession.getAttribute("EntBatchFacultyCreationServlet");
				
				try{
							String RollId = request.getParameter("Id");
							admTlUserRoleLink.setArulRoleid(RollId);
							AdmTlUserRoleLink delStaus = userServices.DeleteUserRoll(admTlUserRoleLink);
							JSONObject successData = new JSONObject();
							//successData.put("msg",delStaus);					
							//successData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
							JSONObject returnData = new JSONObject();	
							
							returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
							returnData.put("formClear",true);
							CommonMessage.debugMsg("rowId"+rowId);
							returnData.put("rowId",request.getParameter("rowId"));
							outdel.print(returnData.toString());
							outdel.close();
					}
				
				catch(Exception e)
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					PrintWriter  out = response.getWriter();
					JSONObject err = new JSONObject();
					
						err.put("tpmException", "Data Not Deleted");
					
					out.print(err.toString());
				}
				
				
				
			}
		}*/
		/*private String decryptPassword(String password,int pin) throws BusinessApplicationExceptions
		{
			int tempUserPin =  getFormatedUserPin(pin);

			String decriptPass ="";
			for(int i=0; i< password.length(); i++ )
				decriptPass  +=    (char)(password.charAt(i) - tempUserPin);

			return decriptPass;
		}
		private int getFormatedUserPin(int userPin) throws BusinessApplicationExceptions 
		{
			NumberFormat formatter = new DecimalFormat("0000");
			String pin = formatter.format(userPin); 
			CommonMessage.debugMsg("pin:"+pin);
			
			if( Integer.parseInt(pin) == 0)
				throw new BusinessApplicationExceptions("tpmusr-000005"); // invalid user pin
			
			int digit = 0, sum = 0;

			for( int i = 0 ; i < pin.length(); i++ )
			{	
				digit = (int)pin.charAt(i) ;
				sum +=  digit;
			}
			String sumStr = Integer.toString(sum);

			do{
				sum = 0;
				for( int i = 0; i < sumStr.length(); i++ )
				{
					digit = (int)(sumStr.charAt(i)-'0');
					sum += digit;
				}
				sumStr = Integer.toString(sum);
			}while( sum > 9 );	

			return sum; 
		}*/
		
		
		}



