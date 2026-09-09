package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EmpmailreportModel;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.service.EmployeemailreportService;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.google.gson.JsonObject;
/**
 * Servlet implementation class EmployeemailreportServlet
 */
@WebServlet("/EmployeemailreportServlet")
public class EmployeemailreportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeemailreportService employeemailreportservice;
	private static final String AdmUploadExcelServlet_filename = "FunctionalLocnServletfilename";
	private static final String FileManagerServlet_filename = "FileManagerServletfilename";
	private static final String DESTINATION_DIR_PATH = "images/loginslide";	
    private static String realPath;
    private static String APP_ROOT_PATH;
    private static final String FILEMANAGER_PATH_SAVE = "filemanager";
    private static  String APP_FILEMANAGER_PATH ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
                	
        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        boolean s = new File(realPath).mkdirs();
        APP_ROOT_PATH = getServletContext().getRealPath("FileManagerServlet") ;
        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
        
        String parentFolderName = APP_ROOT_PATH.substring(APP_ROOT_PATH.lastIndexOf("\\")+1);
        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
        APP_ROOT_PATH = new File(APP_ROOT_PATH).getParent();
        
        APP_FILEMANAGER_PATH = APP_ROOT_PATH +"/" +parentFolderName;
        
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        if( basePath != null && new File( basePath).exists()){
        	APP_FILEMANAGER_PATH = basePath +"\\" +parentFolderName ;
        	
        }
        CommonMessage.debugMsg("APP_FILEMANAGER_PATH " + APP_FILEMANAGER_PATH);
      }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeemailreportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   try{
		   process(request,response); 
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
		// TsODO Auto-generated method stub
		   try{
			   process(request,response); 
		   }
		   catch(Exception e){
			  e.printStackTrace();
		   }
	}
	
	private void process(HttpServletRequest request,HttpServletResponse response)throws ServletException,Exception{
		
		employeemailreportservice=(EmployeemailreportService)UIUtils.getServiceObject(request, "EmployeemailreportServiceImpl");
		String action=UIUtils.getActionPart(request);
		if(action.equals("employemmailreport_input.emr")){
			
		   UIUtils.forwardRequest(request, response,"/pages/employeenableemail.jsp");
	}
		
		else if(action.equals("employemmailreport_getCol.emr")){
			PrintWriter out=response.getWriter();
			HttpSession httpSession=request.getSession(false);
			String tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.Employeemailreport","empreport");
			net.sf.json.JSONObject jsonob=net.sf.json.JSONObject.fromString(tableModel);
			httpSession.removeAttribute("empmailreport");
			httpSession.setAttribute("empmailreport",jsonob);
			out.println(jsonob);
		}
		 else if(action.equals("employemmailreport_getData.emr")){
	     PrintWriter out=null;
	     HttpSession httpSession=request.getSession(false);
		 CommonFilter commonFilter=populateCommonFilter(request,"empmailreportView",true);
		 GridParams gridParams=new GridParams();
		 FilterValues.populateGridParams(request,gridParams);
		 List<String[]> empreports=employeemailreportservice.empreport(commonFilter, gridParams);
		 net.sf.json.JSONObject reportdata=UIUtils.convertToJqGridTableObject(empreports, request, 0, 1,gridParams.getTotalRecordCnt());		
		 httpSession.removeAttribute("empmailreports");
	     httpSession.setAttribute("empmailreports",commonFilter);
	     out=response.getWriter();
		 out.println(reportdata);
		}
		else if(action.equals("employemmailreport_save.emr")){
			updatedata(request,response);
		}	
		else if(action.equals("Tpmcellimgprivillege_input.emr")){
			   
               UIUtils.forwardRequest(request, response,"pages/Imageprivillegetpmcell.jsp");			
 		}
		else if(action.equals("CorporateAud_input.emr")){
			CommonMessage.debugMsg("Inside the Corporate");
            UIUtils.forwardRequest(request, response,"pages/corporateAudit.jsp");			
		}
		else if(action.equals("CorporateAud_getCol.emr")){
			CommonMessage.debugMsg("Inside the Corporate GetCol");
			PrintWriter out=response.getWriter();
			HttpSession httpSession=request.getSession(false);
			 String corporateaudit=request.getParameter("Auditscore");
			 CommonMessage.debugMsg("CorporateAudit"+corporateaudit);
			 String tableModel;
			 if(corporateaudit.equals("CLOSE"))
			 {
				 tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.CorporateAudits","CorporateAudit"); 

			 }
			 else
			 {
				  tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.CorporateAuditScore","AuditScore");

			 }
			net.sf.json.JSONObject jsonob=net.sf.json.JSONObject.fromString(tableModel);
			httpSession.removeAttribute("corporate");
			httpSession.setAttribute("corporate",jsonob);
			out.println(jsonob);
		}
		 else if( action.equals("functionalLoc.emr"))
			{
			    HttpSession httpSession=request.getSession(false);
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");			
				//functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setLocnMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
		 else if( action.equals("functionalLocpbuadd.emr"))
			{
			    HttpSession httpSession=request.getSession(false);
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");
				functLocFieldNameBean.setSbu("cmbTraSbu");
				functLocFieldNameBean.setPbu("cmbTraPbu");
				//functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
		 else if( action.equals("functionalLocsbu.emr"))
			{
			    HttpSession httpSession=request.getSession(false);
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
				functLocFieldNameBean.setSection("cmbbdmsSectionid");
				functLocFieldNameBean.setCell("cmbbdmsCellid");
				functLocFieldNameBean.setMachine("cmbbdmsMachineid");
				functLocFieldNameBean.setSbu("cmbTraSbu");
				functLocFieldNameBean.setPbu("cmbTraPbu");
				//functLocFieldNameBean.setFactMandatory(true);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setLocnMandatory(true);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setSbuMandatory(false);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				functLocFieldNameBean.setSbuDisable(true);
				//functLocFieldNameBean.setMachMandatory(true);			
		        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");			
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );				
			}
		
		/*else if(action.equals("file_save.emr")){
			 CommonMessage.debugMsg("*****File  Upload***********");
			PrintWriter out=response.getWriter();
			//uploadFile(request,response,out);	
			saveFile(request,response,out);
		}*/
		else if(action.equals("file_upload.emr")){
			 CommonMessage.debugMsg("*****File  Save***********");
				PrintWriter out=response.getWriter();

			// saveFile(request,response,employeeBean);
		   //  saveFile(request,response);
		uploadFile(request,response,out);	

		}
		else if(action.equals("employemmailreport_getExcel.emr")){
			try{
		    HttpSession httpSession=request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"empmailreports",false);
			net.sf.json.JSONObject jsonobj =(net.sf.json.JSONObject)httpSession.getAttribute("empmailreport");
			jsonobj.put("title", "Employee Mail Report");
			String formats = ExcelUtils.getFormat(request);
			Workbook wb=employeemailreportservice.getExcelreport(commonFilter,jsonobj,formats);
			ExcelUtils.writeToResponse(response, wb, "Employee Mail Report",formats);	
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		}		
	
private void updatedata(HttpServletRequest request,HttpServletResponse response)throws Exception{
	HttpSession httpsession=request.getSession(false);
    ServletOutputStream out=response.getOutputStream();
    AdmTlUsermst user=UIUtils.getLoginUser(request);
    String updateMsg=null;
    try{
    	if(httpsession!=null && user!=null){
    		String maildetails=request.getParameter("paramJsonArr");
    		CommonMessage.debugMsg("maildetails"+maildetails);
    		EmpmailreportModel empmodel=new EmpmailreportModel();
    		JSONArray emilenable=null;
    		if(UIUtils.isValidKeyId(maildetails)){
    		  emilenable=JSONArray.fromString(maildetails);
       		  List<EmpmailreportModel> empMailEnableList =(List<EmpmailreportModel>)UIUtils.convertJSONArrToList(empmodel,emilenable);	
       		  empMailEnableList=employeemailreportservice.updateEmail(empMailEnableList);
              updateMsg="Data Updated Successfully";
    		}
    		JSONObject SuccessData=new JSONObject();
	 	    SuccessData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
	    	SuccessData.put("msg",updateMsg);
	    	SuccessData.put("formClear",false);
	    	JSONObject returnData=new JSONObject();
	    	returnData.put("successData", SuccessData);		
	    	out.print(returnData.toString());
	    	out.close();
    	}
    }
    catch(ValidationException e){	   
 	   JSONObject errMessage=UIUtils.validationExceptions(e.toString(),"Empmailreport");
 	   out.print("Please Give the Mail ID");
    }
    catch(Exception e){
 	   JSONObject errMessage=UIUtils.validationExceptions(e.toString(),"Empmailreport");
 	   out.print("Please Give the Mail ID");
    }
}

 private void uploadFile(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
	PrintWriter writer = null;
   InputStream is = null;
   FileOutputStream fos = null;
   HttpSession httpSession = request.getSession(false);
   try {
       writer = response.getWriter();
   } catch (IOException ex) {
       
   }
    String imgPath = UIUtils.getImagePath(request);
	File folder=new File(imgPath);
//	if( ! folder.exists())
//	folder.mkdirs();

   String filename = request.getHeader("X-File-Name");
   CommonMessage.debugMsg("X-File-Name  : >> "+filename);
   if(filename !=null){
   	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
   	
   }
   else{
   	filename = UIUtils.imageUpload(request,imgPath);
   // CommonMessage.debugMsg("inParamas  "+filename);
   }
 
   httpSession.removeAttribute(FileManagerServlet_filename);
	httpSession.setAttribute(FileManagerServlet_filename,filename);
   
   try {
       is = request.getInputStream();
       CommonMessage.debugMsg(realPath +":"+ filename);
       fos = new FileOutputStream(new File(realPath + filename));
       IOUtils.copy(is, fos);
       response.setStatus(response.SC_OK);
       writer.print("{success: true}");
   } catch (FileNotFoundException ex) {
       response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
       writer.print("{success: false}");
   } catch (IOException ex) {
       response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
       writer.print("{success: false}");
   } finally {
       try {
           fos.close();
           is.close();
       } catch (IOException ignored) {
       }
   }

   writer.flush();
   writer.close();
}
 
private CommonFilter populateCommonFilter(HttpServletRequest request,
			String string, boolean b){
	       
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

