package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.commons.io.IOUtils;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemstUpl;
import com.akranta.tpm.model.GridColModel;
//import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.service.EmployeeUploadService;
import com.akranta.tpm.service.FileManagerService;
import com.akranta.tpm.service.impl.EmployeeUploadServiceImpl;
import com.akranta.tpm.service.impl.FieldAuditSheetServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class EmployeeUploadServlet
 */
@WebServlet("/EmployeeUploadServlet")
public class EmployeeUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeUploadService employeeUploadService;
	private static final String AdmUploadExcelServlet_filename = "EmployeeUploadServletfilename";
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static String realPath;
	private FileManagerService fileManagerService;
	private static String APP_ROOT_PATH;
	private static final String FILEMANAGER_PATH_SAVE = "filemanager";
	private static  String APP_FILEMANAGER_PATH ;
	private static final String FileManagerServlet_documentNo = "FileManagerServletdocumentNo";
    private static final String FileManagerServlet_documentType = "FileManagerServletdocumentType";
	    
		@Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config); 	
	        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
	        boolean s = new File(realPath).mkdirs();
	        APP_ROOT_PATH = getServletContext().getRealPath("FileManagerServlet") ;
	        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
	        String parentFolderName = APP_ROOT_PATH.substring(APP_ROOT_PATH.lastIndexOf("\\")+1);
	       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
	        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
	        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
	        APP_FILEMANAGER_PATH = APP_ROOT_PATH +"/" +parentFolderName ; 
	        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
	        if( basePath != null && new File( basePath).exists()){
	        	APP_FILEMANAGER_PATH = basePath +"\\" +parentFolderName ;
	        	
	        }
	        CommonMessage.debugMsg("APP_FILEMANAGER_PATH " + APP_FILEMANAGER_PATH);
	    }
		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try{
			Process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			Process(request,response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
 
	private void Process(HttpServletRequest request,HttpServletResponse response) throws Exception,IOException{
		
		    HttpSession httpSession=request.getSession(false);
		    String action=UIUtils.getActionPart(request);
		    ComboFilter comboFilter = new ComboFilter();
	    
		    employeeUploadService=(EmployeeUploadServiceImpl)UIUtils.getServiceObject(request,"EmployeeUploadServiceImpl");
       
		    
		    
		    if(action.equals("NewMenuRightsAssign_input.eupl")){
		    	 String RoleId=request.getParameter("RoleId");
				 CommonMessage.debugMsg("RoleId"+RoleId);
				 String RootId=request.getParameter("RootId");
				 String MenuId=request.getParameter("MenuId");
				 request.setAttribute("RoleId", RoleId);
				 request.setAttribute("RootId", RootId);
				 request.setAttribute("MenuId", MenuId);
			    RequestDispatcher rd=request.getRequestDispatcher("pages/NewMenuRightsAssign.jsp");
		    	rd.forward(request, response);         
		   }
		    
		    else if(action.equals("NewMenuRightsAssign_getCol.eupl")){
			PrintWriter out = response.getWriter();
            GridParams gridParams=new GridParams();
			FilterValues.populateGridParams(request,gridParams);				  
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeDelPrv", "MenuRightsAssign"));
              	
		}
		    else if(action.equals("MenuList.eupl")){
				   try{
					    ComboFilter combofilter=UIUtils.fillComboFilter(request);
					    List<ComboBox>MenuList=employeeUploadService.getMenuList(combofilter);
					    UIUtils.writeComboBox(response,MenuList,combofilter);			    
					  }
					 catch(Exception e){
						 e.printStackTrace();
					 }
			   }    
		
		    else if(action.equals("TPMMenuPillar.eupl")){
				   try{
					    ComboFilter combofilter=UIUtils.fillComboFilter(request);
					    List<ComboBox>MenuList=employeeUploadService.getTPMMenuPillar(combofilter);
					    UIUtils.writeComboBox(response,MenuList,combofilter);			    
					  }
					 catch(Exception e){
						 e.printStackTrace();
					 }
			   }    
		else if(action.equals("NewMenuRightsAssign_getData.eupl")){
           CommonFilter commonFilter = populateCommonFilter(request,"EmployeeDelCommonFilter", false);
			PrintWriter out = response.getWriter();
			 GridParams gridParams=new GridParams();
			 String RoleId=request.getParameter("RoleId");
			 String RootId=request.getParameter("RootId");
			 String MenuId=request.getParameter("MenuId");
			 CommonMessage.debugMsg("RoleId"+RoleId);
			 commonFilter.setKey(RoleId);
			 commonFilter.setRefdocid(RootId);
			 commonFilter.setEmpch(MenuId);
			 FilterValues.populateGridParams(request,gridParams);
				List<String[]> uniqueEmplist = employeeUploadService.ActiveMenuList(commonFilter,gridParams);
			JSONObject uniquePOSEmp = UIUtils.convertToJqGridTableObject(uniqueEmplist, request,0,1,gridParams.getTotalRecordCnt()+1);
			out.println(uniquePOSEmp);
			httpSession.removeAttribute("EmployeeDelCommonFilter");
			httpSession.setAttribute("EmployeeDelCommonFilter",commonFilter);
		}
		    if(action.equals("DocManagerView_input.eupl")){
			    RequestDispatcher rd=request.getRequestDispatcher("pages/DocManagerView.jsp");
		    	rd.forward(request, response);         
		   }
		    
		    
		 else if(action.equals("DocManagerView_getCol.eupl")){
			   PrintWriter out = response.getWriter();
			   String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.FileManager", "DocManagerView");
			   out.println(colModel);
			   CommonMessage.debugMsg("colModel....."+colModel);
			   
		   }
		   else if(action.equals("DocManagerView_getData.eupl")){
			   PrintWriter out = response.getWriter();	
			   httpSession = request.getSession(false);
			   net.sf.json.JSONObject DocData = null;
			   JSONObject jsonObject = null;
	         //  CommonFilter commonFilter = populateCommonFilter(request,"EmployeeDelCommonFilter", false);

			    //String documentNo = request.getParameter("docNo");
			    String RefDocType = request.getParameter("RefDocType");
			    CommonMessage.debugMsg("RefDocType"+RefDocType);
				List< String[]> DocList  = employeeUploadService.getDocData(RefDocType);
				DocData = UIUtils.convertToJqGridTableObject(DocList,request,0,0,DocList.size());
				CommonMessage.debugMsg("DocData....."+DocData);
				httpSession.removeAttribute("EmployeeDelCommonFilter");
				httpSession.setAttribute("EmployeeDelCommonFilter", DocData);
				out.println(DocData);
		   }
		    
		   else if(action.equals("RefDocType.eupl")){
			   try {
		       	      comboFilter=UIUtils.fillComboFilter(request);	
					   List<ComboBox> course = employeeUploadService.getRefDocType(comboFilter);
					   UIUtils.writeComboBox(response, course, comboFilter);
						} catch (Exception e) {
							e.printStackTrace();
						}
		   }
		   else if(action.equals("RefDocNo.eupl")){
			   try {
		       	      comboFilter=UIUtils.fillComboFilter(request);	
					   List<ComboBox> course = employeeUploadService.getRefDocNo(comboFilter);
					   UIUtils.writeComboBox(response, course, comboFilter);
						} catch (Exception e) {
							e.printStackTrace();
						}
		   }
		   else if(action.equals("Keywords.eupl")){
			   try {
		       	      comboFilter=UIUtils.fillComboFilter(request);	
					   List<ComboBox> course = employeeUploadService.getKeyWords(comboFilter);
					   UIUtils.writeComboBox(response, course, comboFilter);
						} catch (Exception e) {
							e.printStackTrace();
						}
		   }
		   else if(action.equals("Description.eupl")){
			   try {
		       	      comboFilter=UIUtils.fillComboFilter(request);	
					   List<ComboBox> course = employeeUploadService.getDescription(comboFilter);
					   UIUtils.writeComboBox(response, course, comboFilter);
						} catch (Exception e) {
							e.printStackTrace();
						}
		   }
		   else if( action.equals("file_download.eupl")){
				  try{
						 String fileName = request.getParameter("filename");
						 CommonMessage.debugMsg("FileName"+fileName);
					     String path = getFileManagerFolderName(request);
					    
					    //fileName = fileName.substring(0, 5)+"....."+fileName.substring(fileName.length()-5 ); 
					    UIUtils.downloadFile(response,fileName,path);
				
				  }catch(FileNotFoundException e)
				  {
					    ServletOutputStream out = response.getOutputStream();
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						//err.put("tpmException", "Data Not Saved");
						err.put("tpmException","File is not Downloaded");
						out.print(err.toString());
				  }catch(IOException e)
				  {
						ServletOutputStream out = response.getOutputStream();
						CommonMessage.debugMsg("gete. " + e.getMessage());
						 
						JSONObject err = new JSONObject();
						//err.put("tpmException", "Data Not Saved");
						err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
						out.print(err.toString());
				  }
				 
			   }
		    
		   if(action.equals("EmployeeMstUpload_input.eupl")){
			    RequestDispatcher rd=request.getRequestDispatcher("pages/EmployeeMasterUpload.jsp");
		    	rd.forward(request, response);         
		   }
		   else if(action.equals("NewMenuRightsAssign_save.eupl")){
			   saveMenuRoleRights(request,response);
		   }
		   
		   else if(action.equals("EmployeeMstUpload_getCol.eupl")){
			   try{
					PrintWriter out = response.getWriter();
					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeMstUpload","EmployeeUpl"));				
				}
				catch(Exception e){	
					e.printStackTrace();
				} 
		   }
		   
		   else if(action.equals("EmployeeMstUpload_getData.eupl")){
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"EmployeeUploadCommonFilter",true);
					FilterValues.getCommonFilters(request, commonFilter);
					String flid = request.getParameter("flid");
					//commonFilter.setFlid(flid);
					List<String[]> MasterGrid = employeeUploadService.getEmployeeList(commonFilter);
					PrintWriter out = response.getWriter();
					JSONObject EmpUplGrid = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,1);	
					out.println(EmpUplGrid);
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
		   }
		   else if (action.equals("EmployeeActiveInactive_input.eupl")) {
				CommonMessage.debugMsg("------userRealease_input.creat");
				 RequestDispatcher rd=request.getRequestDispatcher("pages/EmpActiveInactive.jsp");
			     rd.forward(request, response); 

			}
		   else if (action.equals("EmployeeActiveInactive_getCol.eupl")) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				response.setContentType("text/json");

				String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeMstUpload", "EmpActiveInactive");

				JSONObject colmodel = JSONObject.fromString(tableModel);

				httpSession.removeAttribute("EmpColmodel");
				httpSession.setAttribute("EmpColmodel", colmodel);
				out.println(colmodel);
				out.close();
			} else if (action.equals("EmployeeActiveInactive_getData.eupl")) {
				
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
				List<String[]> EmpList;
				try {
					
					EmpList = employeeUploadService.getEmpData(gridParams,location);

					httpSession.removeAttribute("gridParams");			
					
					JSONObject UserData = UIUtils.convertToJqGridTableObject(EmpList, request, 0, 1,gridParams.getTotalRecordCnt());

					out.println(UserData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		   
			else if (action.equals("employeeActive_save.eupl")) {				
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				try {
					if (httpSession != null && user != null) {
						
						String jsonArrO=request.getParameter("jsonArrO");
						String ids[]=jsonArrO.split(";");
						List<String> empIds=Arrays.asList(ids);
					
						if (!empIds.isEmpty()) {
							employeeUploadService.EmpActive(empIds);
							JSONObject successData = new JSONObject();
							successData.put("msg", "Active Success");
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
				
				
			}
		   
			else if (action.equals("employeeInActive_save.eupl")) {				
				httpSession = request.getSession(false);
				PrintWriter out = response.getWriter();
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				try {
					if (httpSession != null && user != null) {
						
						String jsonArrO=request.getParameter("jsonArrO");
						String ValidTillDate=request.getParameter("ValidTillDate");
						CommonMessage.debugMsg("ValidTillDate:"+ValidTillDate);
						String ids[]=jsonArrO.split(";");
						List<String> empIds=Arrays.asList(ids);
					
						if (!empIds.isEmpty()) {
							employeeUploadService.EmpInActive(empIds,ValidTillDate);
							JSONObject successData = new JSONObject();
							successData.put("msg", "InActive Success");
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
				
				
			}
		
			
		   
		   
		   
		   else if(action.equals("file_save.eupl")){
			   
			   SaveEmployeeFile(request,response);
		   }
		   

		   else if(action.equals("EmpoyeeUplValidateUpdate.eupl")){
		 		   EmployeeUploadValidate(request,response);
		 	   }
		   else if(action.equals("Employee_save.eupl")){
			   SaveEmployee(request,response);
			   
		   }
		   else if(action.equals("EmpoyeeUplDelete.eupl")){
			   EmployeeUploadDelete(request,response);
		   }


		   else if(action.equals("file_upload.eupl")){
			   PrintWriter out = response.getWriter();
				InputStream is = null;
		        FileOutputStream fos = null;	
		        String filename = request.getHeader("X-File-Name");
		        if(filename !=null){	        	
		        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
		        	CommonMessage.debugMsg("fileename: "+filename);
		        }
		        httpSession.removeAttribute(AdmUploadExcelServlet_filename);
				httpSession.setAttribute(AdmUploadExcelServlet_filename,filename);
		        try {
		            is = request.getInputStream();	
		            CommonMessage.debugMsg(realPath + " : file name :" + filename);
		            fos = new FileOutputStream(new File(realPath + filename));
		            IOUtils.copy(is, fos);   
		        } catch (FileNotFoundException ex) {
		            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		            out.print("{success: false}");
		            CommonMessage.debugMsg(" : file exception :");
		        } catch (IOException ex) {
		            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
		            CommonMessage.debugMsg(" : io exception :");
		            out.print("{success: false}");
		        }  finally{
		            try {
		                fos.close();
		                is.close();
		                fos =null;
		                is =null;
		            } catch (IOException ignored) {
		            }
		        }
		   }
	}
	
	private void saveMenuRoleRights(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
      
		try {
		if (httpSession != null && user != null) {
			AdmTlRoleMenuLink existAdmTlRoleMenuLink=(AdmTlRoleMenuLink)httpSession.getAttribute("admTlRoleMenuLink");
			AdmTlRoleMenuLink admTlRoleMenuLink = new AdmTlRoleMenuLink();
			List<AdmTlRoleMenuLink> menuList = new ArrayList<AdmTlRoleMenuLink>();
			String ConvertJSon = request.getParameter("paramJsonArr");
			CommonMessage.debugMsg("selMenu"+ConvertJSon);
			String RoleId = request.getParameter("RoleId");
		    		JSONArray EmployeeList=null; 
		    		List<AdmTlRoleMenuLink> EmployeeAddList = null;	
		    		if(UIUtils.isValidKeyId(ConvertJSon)){
		    			EmployeeList=JSONArray.fromString(ConvertJSon);
		    		  CommonMessage.debugMsg("EmployeeList"+EmployeeList);
		    		   EmployeeAddList=(List<AdmTlRoleMenuLink>)UIUtils.convertJSONArrToList(admTlRoleMenuLink,EmployeeList);		    		   
		    		
		    			EmployeeAddList=employeeUploadService.createBasis(EmployeeAddList,RoleId);
		    		}	
			
				//existAdmTlRoleMenuLink = menuTreeServices.create(admTlRoleMenuLink, existAdmTlRoleMenuLink);
				httpSession.setAttribute("admTlRoleMenuLink",
						existAdmTlRoleMenuLink);

				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();

				successData.put("msg", "Role Assigned Successfully");
				 successData.put("RoleId",RoleId);
				// existAdmTlRoleMenuLink.getArmlMenuid());
				returnData.put("formClear", false);
				returnData.put("successData", successData);

				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());

			}
		}catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		    		
		
	}

	 private void SaveEmployeeFile(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = response.getWriter();
			String file;
			try{
				String fileSave=request.getParameter("fileSave");
				HttpSession httpSession = request.getSession(false);
				AdmTlUsermst userid = UIUtils.getLoginUser(request);
				String excelFileName1= (String) httpSession.getAttribute(AdmUploadExcelServlet_filename);
				String excelFileName=(realPath+""+excelFileName1);
				CommonMessage.debugMsg("excelFileName   "+excelFileName);
				
				GenTlEmployeemstUpl genTlEmployeemstUpl=new GenTlEmployeemstUpl();
				
				genTlEmployeemstUpl=(GenTlEmployeemstUpl)UIUtils.setBeanProperties((Object)genTlEmployeemstUpl, request);
				
		
				if("Y".equals(fileSave)){
					String name=genTlEmployeemstUpl.getExcelName();
				    CommonMessage.debugMsg("File"+name);
					genTlEmployeemstUpl.setExcelName(excelFileName1);
				}
				
				else
				{
					CommonMessage.debugMsg("Else");
					genTlEmployeemstUpl.setExcelName("");
				}
					String savemsg;	
				
				genTlEmployeemstUpl.setEmpuCreatedby(userid.getUsrm_ccno());
				file=employeeUploadService.populateTempTable(excelFileName, genTlEmployeemstUpl);
				if(file.equals("No"))
					savemsg=" File Not Supported";
				else
					savemsg=" Data Uploaded succesfully";
				httpSession.removeAttribute(AdmUploadExcelServlet_filename);
				JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
		    	successData.put("msg", savemsg);
	    		returnData.put("formClear",false);
		    	returnData.put("successData", successData);
				out.print(returnData.toString());
			}catch(ValidationExceptions e){
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsOtherLoss");
				e.printStackTrace();
				out.print(errMessage.toString());
			}catch(Exception e){
				CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
				JSONObject err = new JSONObject();
				String msg="Data Not Supported";
				if(e.toString().contains("CODE_UK"))
					msg="Code Already Exists";
				err.put("tpmException",msg);
				out.print(err.toString());
				e.printStackTrace();
			}
		}
	 private String getFileManagerFolderName(HttpServletRequest request){
			String refType = (String)request.getSession(false).getAttribute(FileManagerServlet_documentType);
			CommonMessage.debugMsg("RefType"+refType);
			//String dbUser= fileManagerService.getFileManagerDao().getDbActionTemplate().getDataSource().getUser();
			//sriram 18-10-2025 make dbUser=null
			String dbUser= null; 
			CommonMessage.debugMsg("DB User"+dbUser);
			String userFileManagerFolder = APP_FILEMANAGER_PATH + "/"+dbUser + "/" +FILEMANAGER_PATH_SAVE  + "/";// + refType +"/"  ;//+ user.getUsrm_keyid().replace("/", "").replace("\\", "");
			try{
				new File(userFileManagerFolder).mkdirs();
			}catch(SecurityException e){
				CommonMessage.debugMsg(e.getMessage());
			}
			CommonMessage.debugMsg(" getFileManagerFolderName " + userFileManagerFolder);
			return userFileManagerFolder +"/";
		}

	 
		private void EmployeeUploadValidate(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
		     PrintWriter out = response.getWriter();
		     String errflag = request.getParameter("errflag");
		     CommonMessage.debugMsg("The errflag"+errflag);
			 try{
			 String Updflag=employeeUploadService.EmployeeUploadValidate(errflag);
	         CommonMessage.debugMsg("Updflag"+Updflag);
			 String count=employeeUploadService.ErrorFlagCount();
	         CommonMessage.debugMsg("count"+count);
			 JSONObject json = new JSONObject();
			 json.put("msg",Updflag);
			 json.put("count",count);
			 out.print(json.toString());
			}catch(BusinessApplicationExceptions e){	
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",false);
					returnData.put("msg"," Record Can't be Deleted Reference Found");
					out.print(returnData.toString());
				}
		}
		
	  	
		private void EmployeeUploadDelete(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
		     PrintWriter out = response.getWriter();
		     String errorflag = request.getParameter("errorflag");
		     CommonMessage.debugMsg("The errflag"+errorflag);
		     String uploadmsg="";
			 try{
			 String Updflag=employeeUploadService.EmployeeUploadDelete(errorflag);
	         CommonMessage.debugMsg("Updflag"+Updflag);
			 String count=employeeUploadService.ErrorFlagCount();
	         CommonMessage.debugMsg("count"+count);
	          uploadmsg="Data Deleted Successfully";
			    JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
		    	successData.put("msg", uploadmsg);
		    	successData.put("count", count);
	    		returnData.put("formClear",false);
		    	returnData.put("successData", successData);
		    	 out.print(returnData.toString());
			}catch(BusinessApplicationExceptions e){	
					JSONObject successData = new JSONObject();
					successData.put("msg",uploadmsg);
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",false);
					returnData.put("msg"," Record Can't be Deleted Reference Found");
					out.print(returnData.toString());
				}
		}
		

		private void SaveEmployee(HttpServletRequest request,HttpServletResponse response) throws Exception,BusinessApplicationExceptions {
		     PrintWriter out = response.getWriter();
		     String errflag = request.getParameter("errorflag");
		     CommonMessage.debugMsg("The errflag"+errflag);
		     String jsonArrO=request.getParameter("jsonArrO");
				String ids[]=jsonArrO.split(";");
				List<String> loginIds=Arrays.asList(ids);
				CommonMessage.debugMsg("loginIds:::::"+loginIds);
				CommonMessage.debugMsg("jsonArrO:::::"+jsonArrO);
		     String uploadmsg="";
			 try{
			 String Updflag=employeeUploadService.EmployeeCreate(errflag);
	         CommonMessage.debugMsg("Updflag"+Updflag);
	         employeeUploadService.resetpwd(loginIds);
	         
			 String count=employeeUploadService.ErrorFlagCount();
	         CommonMessage.debugMsg("count"+count);
	          uploadmsg="Data Uploaded Successfully";
			    JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
		    	successData.put("msg", uploadmsg);
		    	successData.put("count", count);
	    		returnData.put("formClear",false);
		    	returnData.put("successData", successData);
		    	 out.print(returnData.toString());
			}catch(BusinessApplicationExceptions e){	
					JSONObject successData = new JSONObject();
					successData.put("msg",uploadmsg);
					JSONObject returnData = new JSONObject();
					returnData.put("formClear",false);
					returnData.put("msg"," Record Can't be Deleted Reference Found");
					out.print(returnData.toString());
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
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}		
		return commonFilter;
	}
	
	
}
