

package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;


import org.apache.commons.io.IOUtils;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.GenTlFilemanager;
import com.akranta.tpm.service.FileManagerService;
import com.akranta.tpm.service.impl.FileManagerServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class FileManagerServlet extends HttpServlet {
	/**
	 * 
	 */
		private static final long serialVersionUID = -5039306634871924348L;

		FileManagerService   fileManagerService ; 
	
  

		private static final String DESTINATION_DIR_PATH = "tmpFiles";
		
	    private static String realPath;
	    private static String APP_ROOT_PATH;
	    private static final String FILEMANAGER_PATH_SAVE = "filemanager";
	    private static  String APP_FILEMANAGER_PATH ;
	    private static final String FileManagerServlet_documentNo = "FileManagerServletdocumentNo";
		private static final String FileManagerServlet_documentType = "FileManagerServletdocumentType";
	    private static final String FileManagerServlet_colmodel = "FileManagerServletcolmodel";
		private static final String FileManagerServlet_filename = "FileManagerServletfilename";
	    private static final String FileManagerServlet_createdon = "FileManagerServletcreatedon";
	    /**
	     * {@inheritDoc}
	     * @param config
	     * @throws ServletException
	     */
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
	        
	    /*    try{
	        	fileManagerService = new FileManagerServiceImpl();
	        }catch(Exception e)
	        {
	        }	
	        //CommonMessage.debugMsg("tmpFiles " + realPath + " Folder Created " + s);
	         * 
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
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
			
		   String action = UIUtils.getActionPart(request);
			try {
					fileManagerService = (FileManagerServiceImpl)UIUtils.getServiceObject(request,"FileManagerServiceImpl");
	 
			   if( action.equals("file_input.file") ){	
				    HttpSession httpSession = request.getSession(false);
				    String documentNo = request.getParameter(ReqtParamNameConst.DOCNO);
				    String documentType = request.getParameter(ReqtParamNameConst.DOCTYPE);
				    String documentTitle = request.getParameter("title");
				    String description = request.getParameter("description");
				    String keywords = request.getParameter("keywords");
				    CommonMessage.debugMsg("-----Document  no :"+documentNo);
				   // String subjectArea = request.getParameter("subjectArea");
				    
				    if( UIUtils.isValidKeyId(documentTitle) ){
				    	request.setAttribute("title", documentTitle);
				    }
				    if( UIUtils.isValidKeyId(description) ){
				    	request.setAttribute("description", description);
				    }
				    if( UIUtils.isValidKeyId(keywords) ){
				    	request.setAttribute("keywords", keywords);
				    }
				    String buttonId = (String) httpSession.getAttribute("buttonId");
				    String formName = (String) httpSession.getAttribute("formName");

				    
				    
				    String fmgMode = request.getParameter("fmgMode");
				    
					try {
						String docLayoutid = fileManagerService.getDocLayoutId(documentType);
						String typeMstid = fileManagerService.getTypemstid(documentType);
						if(UIUtils.isValidKeyId("docLayoutid"))
							request.setAttribute("docLayoutid", docLayoutid);
						if(UIUtils.isValidKeyId("typeMstid"))
						request.setAttribute("typeMstid", typeMstid);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					AdmTlUsermst user = UIUtils.getLoginUser(request);
					request.setAttribute("loginUserid", user.getUsrm_ccno());
					
					
					
					
					request.setAttribute("formName", formName);
				    request.setAttribute("buttonId", buttonId);
				    request.setAttribute("documentNo", documentNo);
				    request.setAttribute("documentType", documentType);
				    request.setAttribute("fmgMode", fmgMode);
				    RequestDispatcher rd;
				    if("TEMP".equals(documentType)) {
				    	 rd = request.getRequestDispatcher("/pages/filemanagertemp.jsp");
				    }else {
				    	//RequestDispatcher rd = request.getRequestDispatcher("/pages/FileManager.jsp");filemanagerNew.jsp
					     rd = request.getRequestDispatcher("/pages/filemanagerNew.jsp");
				    }
					
					rd.forward(request, response);
					
					httpSession.removeAttribute(FileManagerServlet_documentNo);
					httpSession.setAttribute(FileManagerServlet_documentNo,documentNo);
					httpSession.removeAttribute(FileManagerServlet_documentType);
					httpSession.setAttribute(FileManagerServlet_documentType,documentType);
			   }
			   
				 else if( action.equals("openFile.file")){
					   
					   UIUtils.openFile(request) ;
				   }
			   else if(action.equals("file_getCol.file"))
			   {	
				    HttpSession httpSession = request.getSession(false);
					PrintWriter out = response.getWriter();
					String colmodel = UIUtils.getPropertyValue("com.akranta.tpm.resources.FileManager", "fileMagr");
					JSONObject colModelObj = JSONObject.fromString(colmodel );
					httpSession.removeAttribute(FileManagerServlet_colmodel);
					httpSession.setAttribute(FileManagerServlet_colmodel,colModelObj);
					out.print(colModelObj);
			   }
			   else if(action.equals("file_getData.file"))
			   {	
				   try
					{	
					   // String documentNo = request.getParameter(ReqtParamNameConst.DOCNO);
					    //String documentType = request.getParameter(ReqtParamNameConst.DOCTYPE);
					    HttpSession httpSession = request.getSession(false);
					    JSONObject jsonObject = new JSONObject();
					    
					    String documentNo = (String) httpSession.getAttribute(FileManagerServlet_documentNo);
					    
					    String documentType = (String) httpSession.getAttribute(FileManagerServlet_documentType);
					   // String colmodel = (String) httpSession.getAttribute(FileManagerServlet_colmodel);
					    JSONObject colModel = (JSONObject) httpSession.getAttribute(FileManagerServlet_colmodel);
						PrintWriter out = response.getWriter();
						
						List<GenTlFilemanager> commList  = fileManagerService.getFileText(documentNo,documentType);
					
						jsonObject   = UIUtils.convertToJqGridTableObject(commList, request, colModel,0 ); 
						out.println(jsonObject);
					}catch(Exception e)
					{
						CommonMessage.debugMsg(e.getMessage());
					}
			   }
			   else if(action.equals("getFileCount.file"))
			   {
				   HttpSession httpSession = request.getSession(false);
				   String docNo = request.getParameter("docNo");
				   String docType = request.getParameter("docType");
				   String buttonId = request.getParameter(ReqtParamNameConst.BUTTONID);
				   String formName = request.getParameter(ReqtParamNameConst.FRMNAME);
				   CommonMessage.debugMsg("buttonId  "+buttonId +" formName  "+formName);
					httpSession.setAttribute("buttonId",buttonId);
					httpSession.setAttribute("formName",formName);
					try {
						String docCount  = fileManagerService.getFileCount(docNo,docType);
						JSONObject returnData = new JSONObject();
						CommonMessage.debugMsg(docCount );
						returnData.put("docCount",docCount);
						returnData.put("buttonId",buttonId);
						returnData.put("formName",formName);
						PrintWriter out = response.getWriter();
						out.print(returnData.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
			   else if( action.equals("file_upload.file")){
				   CommonMessage.debugMsg("inParamas  "+request.getParameter("id"));
				   downloadFile(request,response);
			   }	
				/*
				 * else if( action.equals("file_download.file")){ try{ String fileName =
				 * request.getParameter("filename"); // String path =
				 * getFileManagerFolderName(request);
				 * 
				 * //fileName = fileName.substring(0,
				 * 5)+"....."+fileName.substring(fileName.length()-5 );
				 * UIUtils.downloadFile(response , fileName,path);
				 * 
				 * }catch(FileNotFoundException e) { ServletOutputStream out =
				 * response.getOutputStream(); CommonMessage.debugMsg("gete. " + e.getMessage());
				 * JSONObject err = new JSONObject(); //err.put("tpmException",
				 * "Data Not Saved"); err.put("tpmException","File is not Downloaded");
				 * out.print(err.toString()); }catch(IOException e) { ServletOutputStream out =
				 * response.getOutputStream(); CommonMessage.debugMsg("gete. " + e.getMessage());
				 * 
				 * JSONObject err = new JSONObject(); //err.put("tpmException",
				 * "Data Not Saved"); err.put("tpmException",UIUtils.getPropertyValue(
				 * "com.akranta.tpm.resources.CommonMessages","err-save"));
				 * out.print(err.toString()); }
				 * 
				 * }
				 */
			   else if( action.equals("commfile_save.file")){
				   
				   try {
					   
						CommonMessage.debugMsg("che  come on11111");
						savefile(request,response);
				       
				       
			        }catch (Exception e)  {
				// TODO Auto-generated catch block
					e.printStackTrace();
				 }
			      }
			  
			   
			   
			   else if( action.equals("commfile_delete.file")){
				  
					
					deletefile(request,response);
					
			   }
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}

		}

		private void savefile(HttpServletRequest request, HttpServletResponse response) throws IOException{
			HttpSession httpSession = request.getSession(false);
			String documentNo = (String) httpSession.getAttribute(FileManagerServlet_documentNo);
			    
			String documentType = (String) httpSession.getAttribute(FileManagerServlet_documentType);
			//String cretedon = (String) httpSession.getAttribute(FileManagerServlet_createdon);
			
			String fileName = (String) httpSession.getAttribute(FileManagerServlet_filename);
			if(! UIUtils.isValidKeyId(fileName))
				fileName = request.getParameter("filename");
			
	    	//ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String fileType = request.getParameter("fileType");
			String description = request.getParameter("description");
			String keyid = request.getParameter("keyid");
			
			String msgPropertyIdnt;
			String imagePath = null;//getFileManagerFolderName(request) + fileName;
	    	 
        	File newFile = new File(realPath +fileName);
        	newFile.renameTo(new File(imagePath));
            
            response.setStatus(response.SC_OK);
		    if( httpSession != null && user != null)
		    {
		     	GenTlFilemanager newGenTlFilemanager = new GenTlFilemanager();
		    	newGenTlFilemanager.setFlmnCreatedby(user.getUsrm_ccno());
				newGenTlFilemanager.setFlmnDoctype(fileType);
				newGenTlFilemanager.setFlmnDescription(description.toUpperCase());
				newGenTlFilemanager.setFlmnKeyid(keyid);
				newGenTlFilemanager.setFlmnRefdoctype(documentType);
				newGenTlFilemanager.setFlmnRefdocno(documentNo);
				newGenTlFilemanager.setFlmnFilename(fileName);
				String createdon =request.getParameter("createdon");
				httpSession.removeAttribute(FileManagerServlet_createdon);
			
				newGenTlFilemanager =(GenTlFilemanager)UIUtils.setBeanProperties((Object)newGenTlFilemanager,request);
			    GenTlFilemanager existGenTlFilemanager = (GenTlFilemanager) httpSession.getAttribute(FileManagerServlet_createdon);
		    
				
				try{
	                if(UIUtils.isValidKeyId(fileName)){
						boolean insert = true;
						if( ! UIUtils.isValidKeyId( newGenTlFilemanager.getFlmnKeyid()))
						{
							newGenTlFilemanager = fileManagerService.create(newGenTlFilemanager,existGenTlFilemanager);
							httpSession.removeAttribute(FileManagerServlet_filename);
						}
						else{
							insert = false;
							 newGenTlFilemanager.setFlmnCreatedon(createdon);
							newGenTlFilemanager = fileManagerService.update(newGenTlFilemanager,existGenTlFilemanager);
							
							httpSession.setAttribute(FileManagerServlet_filename,fileName);
							httpSession.removeAttribute(FileManagerServlet_filename);
						}
						
						JSONObject successData = new JSONObject();
						 if( insert){
								msgPropertyIdnt = "success-save";
							 }else
								msgPropertyIdnt = "success-update";
							 
							successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
							
							
						ServletOutputStream out = response.getOutputStream();
						//JSONObject successData = new JSONObject();
						//successData.put("msg","Data has been Inserted");
						successData.put("keyId", newGenTlFilemanager.getFlmnKeyid());
						JSONObject returnData = new JSONObject();
						
						returnData.put("successData", successData);				
						
						out.print(returnData.toString());
					}
		             else{
	            	    ServletOutputStream out = response.getOutputStream(); 
		                JSONObject successData = new JSONObject();
		  				successData.put("msg","Select Upload File");
		  				successData.put("filename", newGenTlFilemanager.getFlmnFilename());
		  				JSONObject returnData = new JSONObject();
		 				httpSession.removeAttribute(FileManagerServlet_filename);
		  				returnData.put("successData", successData);				
		  				
		  				out.print(returnData.toString());
		             }  	  
		
				}catch(ValidationExceptions e)
				{
					ServletOutputStream out = response.getOutputStream();
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "");
					//errMessage.put("formMode",genTlFactorymstBean.getFormActionMode());
					out.print(errMessage.toString());
				}catch(BusinessApplicationExceptions e)
				{ 
					ServletOutputStream out = response.getOutputStream();
					CommonMessage.debugMsg("BusinessApplicationExcepions"  );
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "");
					out.print(errMessage.toString());
						
					
				}catch(Exception e)
				{
					ServletOutputStream out = response.getOutputStream();
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
					out.print(err.toString());
				}
		    }	
		  
		}	
		
		/*
		 * private String getFileManagerFolderName(HttpServletRequest request ){ //
		 * AdmTlUsermst user = UIUtils.getLoginUser(request); String refType =
		 * (String)request.getSession(false).getAttribute(
		 * FileManagerServlet_documentType); String dbUser=
		 * fileManagerService.getFileManagerDao().getDbActionTemplate().getDataSource().
		 * getUser(); String userFileManagerFolder = APP_FILEMANAGER_PATH + "/"+dbUser +
		 * "/" +FILEMANAGER_PATH_SAVE + "/" + refType +"/" ;//+
		 * user.getUsrm_keyid().replace("/", "").replace("\\", ""); try{ new
		 * File(userFileManagerFolder).mkdirs(); }catch(SecurityException e){
		 * CommonMessage.debugMsg(e.getMessage()); } CommonMessage.debugMsg(
		 * " getFileManagerFolderName " + userFileManagerFolder); return
		 * userFileManagerFolder +"/"; }
		 */
		private void deletefile(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String fileType = request.getParameter("filename");;
			String description = request.getParameter("description");
			String keyid = request.getParameter("keyid");
		    String fileName = (String) httpSession.getAttribute(FileManagerServlet_filename);
		  
			String imagePath = null;//getFileManagerFolderName(request) + fileType;
			 
			File newFile = new File(imagePath);
			//newFile.renameTo(new File(imagePath));
        	boolean success = newFile.delete();
        	CommonMessage.debugMsg(imagePath + " success " + success);
        	if( httpSession != null && user != null && success)
        	{	
		    	GenTlFilemanager newGenTlFilemanager = new GenTlFilemanager();
		    	newGenTlFilemanager.setFlmnKeyid(keyid);
				 
				try{
					
					newGenTlFilemanager = fileManagerService.delete(newGenTlFilemanager);
					
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("FileManagerKeyid",newGenTlFilemanager.getFlmnKeyid());
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					successData.put("approvalMsg", fileName);
					JSONObject returnData = new JSONObject();
					
					response.setStatus(response.SC_OK);
					returnData.put("successData", successData);		
					out.print(returnData.toString());
					
			
				}catch(Exception e)
				{
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Deleted");
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
					out.print(err.toString());
				}
        	}
        	  else{
		    		GenTlFilemanager newGenTlFilemanager = new GenTlFilemanager();
				   	JSONObject successData = new JSONObject();
					successData.put("msg","Data is not Deleted");
					successData.put("keyId", newGenTlFilemanager.getFlmnKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					
					out.print(returnData.toString());
		    	}
        	
		}
		private void downloadFile(HttpServletRequest request, HttpServletResponse response){
			PrintWriter writer = null;
	        InputStream is = null;
	        FileOutputStream fos = null;
	        HttpSession httpSession = request.getSession(false);
	        try {
	            writer = response.getWriter();
	        } catch (IOException ex) {
	            
	        }
	        /**Added By Manikandan for IE**/
	        String imgPath = UIUtils.getImagePath(request);
			File folder=new File(imgPath);
			if( ! folder.exists())
				folder.mkdirs();

		
	        String filename = request.getHeader("X-File-Name");
	        CommonMessage.debugMsg("X-File-Name  : >> "+filename);
	        if(filename !=null){
	        	/*Pattern pattern = Pattern.compile("\\s+");
	            Matcher matcher = pattern.matcher(filename);
	            boolean check = matcher.find();
	            String fn = matcher.replaceAll(" ");
	            CommonMessage.debugMsg("filename    filename  "+fn);*/
	        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
	        	
	        }/**Added By Manikandan for IE**/
	        else{
	        	filename = UIUtils.imageUpload(request,imgPath);
		        CommonMessage.debugMsg("inParamas  "+filename);
	        }
	      
	        httpSession.removeAttribute(FileManagerServlet_filename);
			httpSession.setAttribute(FileManagerServlet_filename,filename);
	        
	        try {
	            is = request.getInputStream();
	            CommonMessage.debugMsg(realPath +" : sdf :"+ filename);
	            fos = new FileOutputStream(new File(realPath + filename));
	            IOUtils.copy(is, fos);
	            response.setStatus(response.SC_OK);
	            writer.print("{success: true}");
	        } catch (FileNotFoundException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            writer.print("{success: false}");
	           // log(OctetStreamReader.class.getName() + "has thrown an exception: " + ex.getMessage());
	        } catch (IOException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            writer.print("{success: false}");
	           // log(OctetStreamReader.class.getName() + "has thrown an exception: " + ex.getMessage());
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
	 
		 
}

