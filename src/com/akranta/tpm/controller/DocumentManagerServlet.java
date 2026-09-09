package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DocMgrBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.DcmTlRevisionhistory;
import com.akranta.tpm.model.DocTlRoleRights;
import com.akranta.tpm.model.DocTlTemplateDefDtl;
import com.akranta.tpm.model.DocTlTemplateDefMst;
import com.akranta.tpm.model.DocTlTemplateDefvalDtl;
import com.akranta.tpm.service.DocManagerService;
import com.akranta.tpm.service.impl.DocManagerServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.DocMgrConstants;

//
//@MultipartConfig(
//	    fileSizeThreshold = 1024 * 1024,   // 1 MB
//	    maxFileSize = 1024 * 1024 * 10,    // 10 MB
//	    maxRequestSize = 1024 * 1024 * 50  // 50 MB
//	)
@MultipartConfig
public class DocumentManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	DocManagerService docManagerService;	
	
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	
    private static String docRealPath;
    private static String DOC_ROOT_PATH;
    private static final String DOCMANAGER_PATH_SAVE = "/docmanager";
    private static  String APP_DOCMANAGER_PATH ;
    
    private static final String DocManagerServlet_levelNo = "DocManagerServletLevelNo";
    private static final String DocManagerServlet_parentId = "DocManagerServletParentId";
    private static final String DocManagerServlet_Id = "DocManagerServletId";
	private static final String DocManagerServlet_dispOrder = "DocManagerServletDispOrder";
	private static final String DocManagerServlet_dispCode = "DocManagerServletDispCode";
    private static final String DocManagerServlet_folder = "DocManagerServletFolder";
    private static final String DocManagerServlet_filename = "DocManagerServletfilename";
    private static final String DocManagerLayout_Id = "DM001";
	
    
    public void init(ServletConfig config) throws ServletException {
    	try{
        super.init(config);
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
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH + DOCMANAGER_PATH_SAVE + "-" + parentFolderName;
        //CommonMessage.debugMsg("APP_DOCMANAGER_PATH : "+ APP_DOCMANAGER_PATH);
       // CommonMessage.debugMsg("APP_DOCMANAGER_PATH" + APP_DOCMANAGER_PATH + " Folder Created " + s);

        /*try{
        	docManagerService = new DocManagerServiceImpl();
        }catch(Exception e)
        {
        }
        */	
       // CommonMessage.debugMsg("tmpFiles " + docRealPath + " Folder Created " + s);
    }
	/*public DocumentManagerServlet() {
        super();       
        try {
        	docManagerService = new DocManagerServiceImpl();
        	 functionalLocnServices = new FunctionalLocnServicesImpl();
        }
        catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}*/
	
	
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
		
		   HttpSession httpSession = request.getSession(false);
			ComboFilter comboFilter = new ComboFilter();
			//comboFilter=UIUtils.fillComboFilter(request);

		   String action = UIUtils.getActionPart(request);
	    	try {
	    		docManagerService= (DocManagerServiceImpl)UIUtils.getServiceObject(request,"DocManagerServiceImpl");
			
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}

			
			//PrintWriter out = response.getWriter();
			if(action.equals("addDoc_input.dcm")){				   
				 
				  String addMode = request.getParameter("addMode");
				  String levelNo = request.getParameter("levelNo");
				  String displayOrder = request.getParameter("displayOrder");
				  String displayCode = request.getParameter("displayCode");
				  String parentId = request.getParameter("parentId");
				  String id = request.getParameter("nodeId");
				  String folderMode = request.getParameter("folderMode");
				  
				  String allowModify = request.getParameter(DocMgrConstants.mod);
				  String allowDel = request.getParameter(DocMgrConstants.del);
				  String allowDownload = request.getParameter(DocMgrConstants.dld);
				  String allowRights = request.getParameter(DocMgrConstants.rights);
				  
				  if(UIUtils.isValidKeyId(addMode))
				  {
					  if(addMode.equals("Folder"))
					  {
						  int level = Integer.parseInt(levelNo)+1;
						  String docManagerFolder = APP_DOCMANAGER_PATH + "/";// + refType +"/"  ;//+ user.getUsrm_keyid().replace("/", "").replace("\\", "");
						   
							if(level>3)
								docManagerFolder +=  docManagerService.getAllParent(parentId,level);
							if(!levelNo.equals("1"))
							 docManagerFolder += displayCode+ "/";
							  //CommonMessage.debugMsg("path 2 : "+docManagerFolder.substring(docManagerFolder.indexOf("/")));
							  if(docManagerFolder.indexOf("/")>0)
								  docManagerFolder = docManagerFolder.substring(docManagerFolder.indexOf("/"));
							  
						  if(UIUtils.isValidKeyId(folderMode))
							  request.setAttribute("folderMode", folderMode);
						  if(UIUtils.isValidKeyId(docManagerFolder))
							  request.setAttribute("folderPath", docManagerFolder);

						  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmgrOptions.jsp"); 
						  rd.forward(request, response);
					  }
					  else if(addMode.equals("File"))
					  {
						  AdmTlUsermst user = UIUtils.getLoginUser(request);
						  String fileId = request.getParameter("fileId");
						  DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
						  if(UIUtils.isValidKeyId(fileId))
						  {
							  dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);
							  httpSession.removeAttribute("docLayoutFileServlet");
							  httpSession.setAttribute("docLayoutFileServlet", dcmTlDocumentmanager);
						  }
						  else
							  dcmTlDocumentmanager.setDmdmApprovedby(user.getUsrm_ccno());
						  request.setAttribute("dcmTlDocumentmanager", dcmTlDocumentmanager);
						  RequestDispatcher rd = request.getRequestDispatcher("/pages/docMgrAddFile.jsp"); 
						  rd.forward(request, response);
					  }
					  else if(addMode.equals("RevHist"))
					  {
						  String fileId = request.getParameter("fileId");
						  DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
						  if(UIUtils.isValidKeyId(fileId))
						  {
							  dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);
							  httpSession.removeAttribute("docLayoutFileServlet");
							  httpSession.setAttribute("docLayoutFileServlet", dcmTlDocumentmanager);
						  }
						  request.setAttribute("dcmTlDocumentmanager", dcmTlDocumentmanager);
						
						  RequestDispatcher rd = request.getRequestDispatcher("/pages/docMgrRevisionHistory.jsp"); 
						  rd.forward(request, response);
					  }
					  
					  else if(addMode.equals("AdvSearch"))
					  {
						  String fileId = request.getParameter("fileId");
						  DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
						  if(UIUtils.isValidKeyId(fileId))
						  {
							  dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);
							  httpSession.removeAttribute("docLayoutFileServlet");
							  httpSession.setAttribute("docLayoutFileServlet", dcmTlDocumentmanager);
						  }
						  request.setAttribute("dcmTlDocumentmanager", dcmTlDocumentmanager);
						
						  RequestDispatcher rd = request.getRequestDispatcher("/pages/docAdvSearch.jsp"); 
						  rd.forward(request, response);
					  }					  
				  }
				  	httpSession.removeAttribute(DocManagerServlet_levelNo);
					httpSession.setAttribute(DocManagerServlet_levelNo,levelNo);
					httpSession.removeAttribute(DocManagerServlet_parentId);
					httpSession.setAttribute(DocManagerServlet_parentId,parentId);
					httpSession.removeAttribute(DocManagerServlet_dispOrder);
					httpSession.setAttribute(DocManagerServlet_dispOrder,displayOrder);
					httpSession.removeAttribute(DocManagerServlet_dispCode);
					httpSession.setAttribute(DocManagerServlet_dispCode,displayCode);
					httpSession.removeAttribute(DocManagerServlet_Id);
					httpSession.setAttribute(DocManagerServlet_Id,id);
					
					httpSession.removeAttribute(DocMgrConstants.modifyRights);
					httpSession.setAttribute(DocMgrConstants.modifyRights,allowModify);
					httpSession.removeAttribute(DocMgrConstants.deleteRights);
					httpSession.setAttribute(DocMgrConstants.deleteRights,allowDel);
					httpSession.removeAttribute(DocMgrConstants.downloadRights);
					httpSession.setAttribute(DocMgrConstants.downloadRights,allowDownload);
					httpSession.removeAttribute(DocMgrConstants.User_Rights);
					httpSession.setAttribute(DocMgrConstants.User_Rights,allowRights);
				//  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmgrOptions.jsp"); 
				 // rd.forward(request, response);
		   }
			else if(action.equals("revHist_getCol.dcm")){
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "colModel"));
			}
			else if(action.equals("revHist_getData.dcm")){
				 PrintWriter out = response.getWriter();
				 DcmTlDocumentmanager dcmTlDocumentmanager = (DcmTlDocumentmanager)httpSession.getAttribute("docLayoutFileServlet");
				 String fileId = dcmTlDocumentmanager.getDmdmKeyid();
				 try
				 {
					 List<String []> revHistoryList  = docManagerService.getRevisionHistory(fileId);
					 net.sf.json.JSONObject revHistoryData = UIUtils.convertToJqGridTableObject(revHistoryList,request,0,0,revHistoryList.size());
					  httpSession.removeAttribute("docLayoutFileServlet");
					 out.println(revHistoryData);
				 }
				 catch(Exception e)
				 {
					 
				 }
				
			}
			else if(action.equals("docTempTypeVal_view.dcm")){
				String typeId = request.getParameter("typeId");
				String fileId = request.getParameter("fileId");
				String showSearch = request.getParameter("showSearch");				
				request.setAttribute("typeId", typeId);
				request.setAttribute("fileId", fileId);
				String redirectTo = "/pages/DocTempAddValues.jsp";
				if(UIUtils.isValidKeyId(showSearch))
				{
					if(showSearch.equals("Y"))
						redirectTo = "/pages/docKeyValuesSrch.jsp";
				}
				RequestDispatcher rd = request.getRequestDispatcher(redirectTo); 
				rd.forward(request, response);
			}
			else if(action.equals("docTempTypeVal_getCol.dcm")){
				PrintWriter out = response.getWriter();
				String showSrch = request.getParameter("showSrch");	
				String colModel = "docTempTypeValModel";
				if(UIUtils.isValidKeyId(showSrch))
				{
					if(showSrch.equals("Y"))
						colModel = "docSearchTypeValModel";
				}
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", colModel));
			}
			else if(action.equals("docTempTypeVal_getData.dcm")){
				 PrintWriter out = response.getWriter();				
				 String typeId = request.getParameter("typeId");
				 String fileId = request.getParameter("fileId");
				 String showSrch = request.getParameter("showSrch");	 
				 try
				 {
					 List<String []> keywordsList  = docManagerService.getTypeKeywords(typeId,fileId,showSrch);
					 net.sf.json.JSONObject keywordsData = UIUtils.convertToJqGridTableObject(keywordsList,request,0,0,keywordsList.size());
					 out.println(keywordsData);
				 }
				 catch(Exception e)
				 {
					 
				 }
			}
			else if(action.equals("user_rights.dcm")){
				   String id = request.getParameter("keyId");
				   if(UIUtils.isValidKeyId(id))
					   request.setAttribute("folderId", id);
				  UIUtils.removeCookie(response,"fnlnsearch_str");
				  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmgrUserRights.jsp"); 
				  rd.forward(request, response);
			   }
			else if(action.equals("userRights_getCol.dcm")){
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "userRightsColModel"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "userRightsColModel"));
			}
			else if(action.equals("userRights_getData.dcm")){
				 PrintWriter out = response.getWriter();
				 
				 String folderId = request.getParameter("folderId");
				 CommonMessage.debugMsg(folderId);
				 try
				 {
					 List<String []> userRightsList  = docManagerService.getUserRights(folderId);
					 net.sf.json.JSONObject userRightsData = UIUtils.convertToJqGridTableObject(userRightsList,request,0,0,userRightsList.size());
					 
					 out.println(userRightsData);
				 }
				 catch(Exception e)
				 {
					 
				 }
				
			}
		   else if(action.equals("document_manager.dcm")){
			   
			  UIUtils.removeCookie(response,"fnlnsearch_str");
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmanager.jsp"); 
			  rd.forward(request, response);
		   }
		   else if(action.equals("document_download.dcm")){
			   
				  UIUtils.removeCookie(response,"fnlnsearch_str");
				  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmanager.jsp"); 
				  rd.forward(request, response);
			   }
		   else if(action.equals("document_view.dcm")){
			   
				  UIUtils.removeCookie(response,"fnlnsearch_str");
				  RequestDispatcher rd = request.getRequestDispatcher("/pages/docmanager.jsp"); 
				  rd.forward(request, response);
		   }
		   else if(action.equals("folder_save.dcm")){
			   saveFolder(request,response);
		   }
		   else if(action.equals("folder_del.dcm")){
			   CommonMessage.debugMsg("Delete File ");
			   delFolder(request,response);
		   }
		   else if(action.equals("docTempTypeVal_save.dcm")){
			   saveTemplateValues(request,response);
		   }
		   else if(action.equals("file_save.dcm")){
			   saveFile(request,response);
		   }
		   else if(action.equals("file_del.dcm")){
			   delFile(request,response);
		   }
		   else if( action.equals("file_upload.dcm")){
			   downloadFile(request,response);
		   }	
		   else if(action.equals("DocumentTemplate_save.dcm")){
				saveDocTempData(request,response);
		   }
		   else if(action.equals("DocumentTemplate_delete.dcm")){
			   delDocTemp(request,response);
			   
		   }
		   else if(action.equals("DocTemp_del.dcm")){
			   deleteDocTemp(request,response);
		   }
		   else if(action.equals("userRights_save.dcm")){
			   UIUtils.displayRequestParamsValue(request);
			   saveUserRights(request,response);
		   }
		   else if( action.equals("download_file.dcm")){
			   String fileId = request.getParameter("id");
			   DcmTlDocumentmanager dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);		
			   if(dcmTlDocumentmanager != null)
			   {
				   String fileName = dcmTlDocumentmanager.getDmdmFilename();
				   String path = DOC_ROOT_PATH + dcmTlDocumentmanager.getDmdmPath();
				   //String path = APP_DOCMANAGER_PATH + dcmTlDocumentmanager.getDmdmPath();
				   CommonMessage.debugMsg(" Path :: 1234 "+dcmTlDocumentmanager.getDmdmPath());
				   path = path.replace("/", "\\");
				   CommonMessage.debugMsg(path + fileName);
					
				   
				   //UIUtils.downloadFile(response , fileName,path);
				   downloadFile(response , fileName,path);
			   }
			  // downloadFile(request,response);
		   }				
			
		   else if( action.equals("getContent.dcm")){
			   PrintWriter out = response.getWriter();
			   String id = request.getParameter("id");
			   String levelNo = request.getParameter("elemType");
			   String folderName = request.getParameter("dispCode");
			   String elemId = request.getParameter("elemId");
			   String parentId = request.getParameter("parentId");
			   String viewsMode = request.getParameter("viewsMode");
			   
			   String keywords = request.getParameter("keywords");
			   if(UIUtils.isValidKeyId(keywords)) 
				   keywords=keywords.toUpperCase();
			   
			   //for adv search		   
			   String fromDate = request.getParameter("fromDate");
			   String toDate = request.getParameter("toDate");
			   String title = request.getParameter("title");
			   String subjectArea = request.getParameter("subjectArea");
			   String category = request.getParameter("category");
			   String owner = request.getParameter("owner");
			   String changes = request.getParameter("changes");
			   String type = request.getParameter("type");
			   String searchValues = request.getParameter("searchValues");
			   
			   if(UIUtils.isValidKeyId(changes)) 
				   changes=changes.toUpperCase();
			   
			   String description = request.getParameter("description");
			   if(UIUtils.isValidKeyId(description)) 
				   description=description.toUpperCase();
			   
			   String approvedBy = request.getParameter("approvedBy");
			   
			   
			   String allowModify = request.getParameter(DocMgrConstants.mod);
			   String allowDel = request.getParameter(DocMgrConstants.del);
			   String allowDownload = request.getParameter(DocMgrConstants.dld);
			   String allowRights = request.getParameter(DocMgrConstants.rights);
			   
			   boolean rightClkFlag = false;
			   boolean view = false;
			  
			   AdmTlUsermst user = UIUtils.getLoginUser(request);
			   DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
			 
			   if(UIUtils.isValidKeyId(keywords) || UIUtils.isValidKeyId(title) || UIUtils.isValidKeyId(subjectArea)
					   || UIUtils.isValidKeyId(category) || UIUtils.isValidKeyId(owner) || UIUtils.isValidKeyId(changes)
					   || UIUtils.isValidKeyId(description) || UIUtils.isValidKeyId(approvedBy) 
					   || UIUtils.isValidDate(fromDate) || UIUtils.isValidDate(toDate)|| UIUtils.isValidKeyId(type)|| UIUtils.isValidKeyId(searchValues))
				   CommonMessage.debugMsg("print");
			   else {				  
				   dcmTlDocumentmanager = docManagerService.getFolderPath(id);
				   
			   }
			   
			   
			   String path = dcmTlDocumentmanager.getDmdmPath();
			   if(UIUtils.isValidKeyId(path))
			   {
				   path = DOC_ROOT_PATH+dcmTlDocumentmanager.getDmdmPath();
				 
				 
					   File f = new File(path.replace("/", "\\"));	

					   //CommonMessage.debugMsg("f.list().: "+f.list().length);
					   if(f.list() != null)
					   {
						   JSONObject jSONObject = new JSONObject();
						   JSONObject otherDetails = new JSONObject();
						   JSONArray jSONArray = new JSONArray();	  
						   ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
						   
						   int i = 0;
						   for(String name : names)
						   {
							   File savedFile = new File(path.replace("/", "\\")+name);
							   Icon ico = FileSystemView.getFileSystemView().getSystemIcon(savedFile);
							   CommonMessage.debugMsg(ico + dcmTlDocumentmanager.getDmdmTitle());	
							   if(name.indexOf(".") > 0)
							   {
								   view = true;
								   DcmTlDocumentmanager dcmTlDocumentmgr = docManagerService.getFolderPath(name);
								   CommonMessage.debugMsg(dcmTlDocumentmgr.getDmdmIsodoctype());
								   if(UIUtils.isValidKeyId(dcmTlDocumentmgr.getDmdmTitle()))
									   otherDetails.put("fileName",dcmTlDocumentmgr.getDmdmTitle());
								   else
									   otherDetails.put("fileName",dcmTlDocumentmgr.getDmdmFilename());
								   
								   otherDetails.put("savedFileName",dcmTlDocumentmgr.getDmdmFilename());
								   otherDetails.put("keywords",dcmTlDocumentmgr.getDmdmKeywords());
								   otherDetails.put("fileId",dcmTlDocumentmgr.getDmdmKeyid());
								   otherDetails.put("icon",getImageUrl(ico.toString())!= null?getImageUrl(ico.toString()):"images/defaultIcons/txt.png");
								   								   
								   //otherDetails.put("Category", dcmTlDocumentmgr.getDmdmCategory());
								   //otherDetails.put("Subjectarea", dcmTlDocumentmgr.getDmdmSubjectarea());								   
								  
								   otherDetails.put("Category", UIUtils.isValidKeyId(dcmTlDocumentmgr.getDmdmCategory())?docManagerService.getCategory(dcmTlDocumentmgr.getDmdmCategory()):dcmTlDocumentmgr.getDmdmCategory());
								   otherDetails.put("Subjectarea", UIUtils.isValidKeyId(dcmTlDocumentmgr.getDmdmSubjectarea())?docManagerService.getSubjectArea(dcmTlDocumentmgr.getDmdmSubjectarea()):dcmTlDocumentmgr.getDmdmSubjectarea());
								   
								   otherDetails.put("createdOn", dcmTlDocumentmgr.getDmdmCreatedon());
								   otherDetails.put("modifiedOn", dcmTlDocumentmgr.getDmdmModifiedon());
								   otherDetails.put("createdBy", dcmTlDocumentmgr.getDmdmCreatedby());		
							   }
							   else
							   {
								   rightClkFlag = true;
								   view = false;
								   DcmTlDocumentlayout dcmTlDocumentlayout = docManagerService.getFolderList(name.toUpperCase());
								  
					               List<String []> userRightsList  = docManagerService.getUserRights(dcmTlDocumentlayout.getDmlyKeyid()+":"+user.getUsrm_keyid()+":"+user.getUsrm_ccno());
					               if(userRightsList.size()>0)
					               {
					                int row=0;
					                for(int k=0;k<userRightsList.size();k++)
					                {
					                	
					                	if(!UIUtils.isValidKeyId(userRightsList.get(k)[0]))
					                	row=k;
					                }
						            if(userRightsList.get(row)[3].equals("1"))
						            {
						               view = true;
						            }
					               }
								   CommonMessage.debugMsg(dcmTlDocumentlayout.getDmlyKeyid());
								   if(view)
								   {
									   otherDetails.put("fileName",dcmTlDocumentlayout.getDmlyName());
									   otherDetails.put("savedFileName",dcmTlDocumentlayout.getDmlyName());
									   otherDetails.put("keywords","");
									   otherDetails.put("fileId",dcmTlDocumentlayout.getDmlyKeyid());
									   otherDetails.put("icon",getImageUrl(ico.toString())!= null?getImageUrl(ico.toString()):"images/defaultIcons/txt.png");
									   otherDetails.put("Category","");
									   otherDetails.put("Subjectarea", "");
									   otherDetails.put("createdOn", dcmTlDocumentlayout.getDmlyCreatedon());
									   otherDetails.put("modifiedOn", dcmTlDocumentlayout.getDmlyModifiedon());
									   otherDetails.put("createdBy", dcmTlDocumentlayout.getDmlyCreatedby());
								   }
							   }
							   if(view)
								   jSONArray.put(otherDetails);
							   i++;
						   }					
					   
						   jSONObject.put("otherDetails", jSONArray);
						   CommonMessage.debugMsg(id+","+levelNo+","+folderName+","+elemId+","+parentId);
						   jSONObject.put("id", id);
						   jSONObject.put("levelNo", levelNo);
						   jSONObject.put("folderName", folderName);
						   jSONObject.put("elemId", elemId);
						   jSONObject.put("parentId", parentId);
						   
						   if(UIUtils.isValidKeyId(allowModify))
							   jSONObject.put(DocMgrConstants.mod, allowModify);
						   if(UIUtils.isValidKeyId(allowDel))
							   jSONObject.put(DocMgrConstants.del, allowDel);
						   if(UIUtils.isValidKeyId(allowDownload))
							   jSONObject.put(DocMgrConstants.dld, allowDownload);
						   if(UIUtils.isValidKeyId(allowRights))
							   jSONObject.put(DocMgrConstants.rights, allowRights);
						 
						   if(UIUtils.isValidKeyId(viewsMode))
							   jSONObject.put("viewsMode", viewsMode);

						   out.print(jSONObject.toString());
				   }
				 
			   }
			   else
			   {
				   if(UIUtils.isValidKeyId(keywords) || UIUtils.isValidKeyId(title) || UIUtils.isValidKeyId(subjectArea)
						   || UIUtils.isValidKeyId(category) || UIUtils.isValidKeyId(owner) || UIUtils.isValidKeyId(changes)
						   || UIUtils.isValidKeyId(description) || UIUtils.isValidKeyId(approvedBy) 
						   || UIUtils.isValidDate(fromDate) || UIUtils.isValidDate(toDate)|| UIUtils.isValidKeyId(type)|| UIUtils.isValidKeyId(searchValues))
				   {
					   String values = request.getParameter("searchValues");
					   String schCond = request.getParameter("schCond");
					   DocTlTemplateDefvalDtl newDocTlTemplateDefvalDtl = new DocTlTemplateDefvalDtl();
					   if(UIUtils.isValidKeyId(values))
					   {
							JSONArray jsonArray = JSONArray.fromString(values);	
							DocTlTemplateDefvalDtl docTlTemplateDefvalDtl = new DocTlTemplateDefvalDtl();
							List<DocTlTemplateDefvalDtl> tempValues = (List<DocTlTemplateDefvalDtl>) UIUtils.convertJSONArrToList(docTlTemplateDefvalDtl, jsonArray);
							if( newDocTlTemplateDefvalDtl != null)
								newDocTlTemplateDefvalDtl.setDocTlTemplateDefvalDtl(tempValues);
					   }
			    		 
					  List<DcmTlDocumentmanager> searchFileList = docManagerService.searchFile(keywords,  fromDate,  toDate,  
							  	title,  subjectArea,category,  owner,  changes, description,  approvedBy,type,schCond,newDocTlTemplateDefvalDtl);
					  
					  if(searchFileList != null)
					  {
						  view = false;
						  JSONObject jSONObject = new JSONObject();
						   JSONObject otherDetails = new JSONObject();
						   JSONArray jSONArray = new JSONArray();	
						    for(int i=0; i<searchFileList.size(); i++){
						    	
						    	   List<String []> userRightsList  = docManagerService.getUserRights(searchFileList.get(i).getDmdmIsodoctype()+":"+user.getUsrm_keyid()+":"+user.getUsrm_ccno());
					               if(userRightsList.size()>0)
					               {
					                int row=0;
					                for(int k=0;k<userRightsList.size();k++)
					                {
					                	
					                	if(!UIUtils.isValidKeyId(userRightsList.get(k)[0]))
					                	row=k;
					                }
						            if(userRightsList.get(row)[3].equals("1"))
						            {
						               view = true;
						            }
						            
						            otherDetails.put(DocMgrConstants.mod, "N");
						            otherDetails.put(DocMgrConstants.del, "N");
						            otherDetails.put(DocMgrConstants.dld, "N");
						            otherDetails.put(DocMgrConstants.rights, "N");
						            if(userRightsList.get(row)[4].equals(DocMgrConstants.modify))
					                {
						            	otherDetails.put(DocMgrConstants.mod, "Y");
					                }
					                
					                if(userRightsList.get(row)[5].equals(DocMgrConstants.delete))
					                {
					                	otherDetails.put(DocMgrConstants.del, "Y");
					                }
					                if(userRightsList.get(row)[6].equals(DocMgrConstants.download))
					                {
					                	otherDetails.put(DocMgrConstants.dld, "Y");
					                }
					                if(userRightsList.get(row)[7].equals(DocMgrConstants.userRights))
					                {
					                	otherDetails.put(DocMgrConstants.rights, "Y");
					                }       
					               }
					               if(view)
								   {
									   File savedFile = new File(DOC_ROOT_PATH+searchFileList.get(i).getDmdmPath().replace("/", "\\")+searchFileList.get(i).getDmdmFilename());
									   Icon ico = FileSystemView.getFileSystemView().getSystemIcon(savedFile);
									   if(UIUtils.isValidKeyId(searchFileList.get(i).getDmdmTitle()))
										   otherDetails.put("fileName",searchFileList.get(i).getDmdmTitle());
									   else
										   otherDetails.put("fileName",searchFileList.get(i).getDmdmFilename());
									   CommonMessage.debugMsg(searchFileList.get(i).getDmdmTitle() + " ---- > "+searchFileList.get(i).getDmdmFilename());
									//   otherDetails.put("fileName",searchFileList.get(i).getDmdmTitle());
									   otherDetails.put("savedFileName",searchFileList.get(i).getDmdmFilename());
									   otherDetails.put("keywords",searchFileList.get(i).getDmdmKeywords());
									   otherDetails.put("fileId",searchFileList.get(i).getDmdmKeyid());
									   if(ico != null)
									   otherDetails.put("icon",getImageUrl(ico.toString())!= null?getImageUrl(ico.toString()):"images/defaultIcons/txt.png");
									   otherDetails.put("Category", searchFileList.get(i).getDmdmCategory());
									   otherDetails.put("Subjectarea", searchFileList.get(i).getDmdmSubjectarea());
									   otherDetails.put("createdOn", searchFileList.get(i).getDmdmCreatedon());
									   otherDetails.put("modifiedOn", searchFileList.get(i).getDmdmModifiedon());
									   otherDetails.put("createdBy", searchFileList.get(i).getDmdmCreatedby());	
									   jSONArray.put(otherDetails);
								   }
					               view = false;
						    	}
						      
						       jSONObject.put("otherDetails", jSONArray);						
							   if(UIUtils.isValidKeyId(viewsMode))
								   jSONObject.put("viewsMode", viewsMode);
							   
							  /* if(UIUtils.isValidKeyId(allowModify))
								   jSONObject.put(DocMgrConstants.mod, allowModify);
							   if(UIUtils.isValidKeyId(allowDel))
								   jSONObject.put(DocMgrConstants.del, allowDel);
							   if(UIUtils.isValidKeyId(allowDownload))
								   jSONObject.put(DocMgrConstants.dld, allowDownload);
							   if(UIUtils.isValidKeyId(allowRights))
								   jSONObject.put(DocMgrConstants.rights, allowRights);*/
							 
							   out.print(jSONObject.toString());
					  }
				   }
				   else
				   {
					   int level = Integer.parseInt(levelNo) + 1;
					   String folderPath = getDocManagerFolderName(request,level,folderName,id);
					   rightClkFlag = true;
					   view = false;
					   File f = new File(folderPath.replace("/", "\\"));				  
					   ArrayList<String> folderNames = new ArrayList<String>(Arrays.asList(f.list()));
					   JSONObject jSONObject = new JSONObject();
					   JSONObject otherDetails = new JSONObject();
					   JSONArray jSONArray = new JSONArray();	  
					  
					   int i=0;
					   for(String fld : folderNames)
					   {
						   DcmTlDocumentlayout dcmTlDocumentlayout = docManagerService.getFolderList(fld.toUpperCase());
						   File savedFile = new File(folderPath.replace("/", "\\")+fld);
						   Icon ico = FileSystemView.getFileSystemView().getSystemIcon(savedFile);
						   List<String []> userRightsList  = docManagerService.getUserRights(dcmTlDocumentlayout.getDmlyKeyid()+":"+user.getUsrm_keyid()+":"+user.getUsrm_ccno());
						   if(UIUtils.isValidKeyId(dcmTlDocumentlayout.getDmlyKeyid()))
						   {
				               if(userRightsList.size()>0)
				               {
				                int row=0;
				                for(int k=0;k<userRightsList.size();k++)
				                {
				                	
				                	if(!UIUtils.isValidKeyId(userRightsList.get(k)[0]))
				                	row=k;
				                }
					            if(userRightsList.get(row)[3].equals("1"))
					            {
					               view = true;
					            }
				               }
							   if(view)
							   {
							   otherDetails.put("fileName",dcmTlDocumentlayout.getDmlyName());
							   otherDetails.put("savedFileName",dcmTlDocumentlayout.getDmlyName());
							   otherDetails.put("keywords","");
							   otherDetails.put("fileId",dcmTlDocumentlayout.getDmlyKeyid());
							   if(ico != null)
							   otherDetails.put("icon",getImageUrl(ico.toString())!= null?getImageUrl(ico.toString()):"images/defaultIcons/txt.png");
							   otherDetails.put("Category","");
							   otherDetails.put("Subjectarea", "");
							   otherDetails.put("createdOn", dcmTlDocumentlayout.getDmlyCreatedon());
							   otherDetails.put("modifiedOn", dcmTlDocumentlayout.getDmlyModifiedon());
							   otherDetails.put("createdBy", dcmTlDocumentlayout.getDmlyCreatedby());					 
							   jSONArray.put(otherDetails);
							   }
							   i++;
						   }
					   }
					   if(view)
						   jSONObject.put("otherDetails", jSONArray);
					   if(rightClkFlag)
						   jSONObject.put("rightClk", "disable");
					   else
						   jSONObject.put("rightClk", "enable");
					   CommonMessage.debugMsg(viewsMode + " Views Mode");
					   if(UIUtils.isValidKeyId(viewsMode))
						   jSONObject.put("viewsMode", viewsMode);
						   
					   out.print(jSONObject.toString());
				   }
			   }

		   }
		   else if( action.equals("loadval.dcm") )
			{	
			   PrintWriter out = response.getWriter();
			   AdmTlUsermst user = UIUtils.getLoginUser(request);
				String parentNumber = null;
				String parentId = null;
				String elementType = null;		
				String id = null;
				parentNumber=request.getParameter("elementId");
				parentId = request.getParameter("parentId");
				elementType = request.getParameter("elementType");
				id=request.getParameter("id");
				
				parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
		    	response.setContentType("text/html;charset=UTF-8");
		    	try {
		    		JSONArray jSONArray = new JSONArray();	    		
			    	if(request.getParameter("id").equals("0"))
			    	{
				    	   JSONObject jSONObject = new JSONObject();
			    		   JSONObject data = new JSONObject();
			    		   JSONObject jsonAttr = new JSONObject();
			               JSONObject metadata = new JSONObject();
			    		   jsonAttr.put("id", DocManagerLayout_Id);
			    		   jsonAttr.put("originalId", DocManagerLayout_Id);
			               jsonAttr.put("elementId", "1");
			               jsonAttr.put("parentId", "1");
			               jsonAttr.put("elementType", "1");
			               jsonAttr.put("displayCode", "Document Manager");
			               jsonAttr.put("imgUrl",getImageUrl("DM"));
			               jsonAttr.put("href", "#");
			               data.put("title", "Document Manager");	        			
		        		   data.put("icon", "");
		        		   jSONObject.put("data",data);
		        		   jSONObject.put("attr", jsonAttr);
		        		   
		        		   if(request.getParameter("search_str") != null)
		        			   jSONObject.put("state","open");
		        		   else
		        			   jSONObject.put("state","closed");
		        		   metadata.put("id", "1");
		        		   jSONObject.put("metadata",metadata);
			               jSONObject.put("icon",getIconImage("DM"));
			               jsonAttr = null;
			               jSONObject.put("children","[{}]");
			               jSONArray.put(jSONObject);
			               jSONObject=null;		    		
			    	}
			    	else
			    	{
			    		DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();	        	
			    		dcmTlDocumentlayout.setDmlyDisplayorder(parentNumber);
			    		dcmTlDocumentlayout.setDmlyParentid(parentId);
			    		dcmTlDocumentlayout.setDmlyLevelno(elementType);
			    		dcmTlDocumentlayout.setDmlyKeyid(id);
			        	List <DcmTlDocumentlayout> docList = docManagerService.getAllDocument(dcmTlDocumentlayout);
			        	
			        	for(int i=0; i<docList.size(); i++){
			        		JSONObject jSONObject = new JSONObject();
		        			JSONObject data = new JSONObject();
		        			JSONObject jsonAttr = new JSONObject();
			                JSONObject metadata = new JSONObject();	
			                boolean view = false;
			                List<String []> userRightsList  = docManagerService.getUserRights(docList.get(i).getDmlyKeyid()+":"+user.getUsrm_keyid()+":"+user.getUsrm_ccno());
			                if(userRightsList.size()>0)
			                {
			                	int row=0;
			                	for(int k=0;k<userRightsList.size();k++)
			                	{
			                		if(!UIUtils.isValidKeyId(userRightsList.get(k)[0]))
			                			row=k;
			                	}
				                if(userRightsList.get(row)[3].equals("1"))
				                {
				                	view = true;
				                }
				                jsonAttr.put(DocMgrConstants.mod, "N");
				                jsonAttr.put(DocMgrConstants.del, "N");
				                jsonAttr.put(DocMgrConstants.dld, "N");
				            	jsonAttr.put(DocMgrConstants.rights, "N");
				                if(userRightsList.get(row)[4].equals(DocMgrConstants.modify))
				                {
				                	jsonAttr.put(DocMgrConstants.mod, "Y");
				                }
				                
				                if(userRightsList.get(row)[5].equals(DocMgrConstants.delete))
				                {
				                	jsonAttr.put(DocMgrConstants.del, "Y");
				                }
				                if(userRightsList.get(row)[6].equals(DocMgrConstants.download))
				                {
				                	jsonAttr.put(DocMgrConstants.dld, "Y");
				                }
				                if(userRightsList.get(row)[7].equals(DocMgrConstants.userRights))
				                {
				                	jsonAttr.put(DocMgrConstants.rights, "Y");
				                }            
				            
			                }
			                if(view)
			                {
				        		jsonAttr.put("id", docList.get(i).getDmlyKeyid().replace("/", "_"));
				                jsonAttr.put("originalId", docList.get(i).getDmlyKeyid());
				                jsonAttr.put("elementId", docList.get(i).getDmlyDisplayorder());
				                jsonAttr.put("parentId", docList.get(i).getDmlyParentid());
				                jsonAttr.put("elementType", docList.get(i).getDmlyLevelno());
				                jsonAttr.put("displayCode", docList.get(i).getDmlyName());
				                jsonAttr.put("imgUrl",getImageUrl(docList.get(i).getDmlyLevelno()));
				                jsonAttr.put("title",UIUtils.getTitle(docList.get(i).getDmlyKeyid().substring(0,3)));
				                jsonAttr.put("href", "#");
				               
			        			data.put("title", docList.get(i).getDmlyName());
			        			//data.put("attr", jsonAttr);
			        			data.put("icon", "");
			        			jSONObject.put("data",data);
			        			jSONObject.put("attr", jsonAttr);	
			        			if(request.getParameter("search_str") != null)
			        			{
			        				jSONObject.put("state","open");
			        			}
			        			else
			        				jSONObject.put("state","closed");
			        				
			        			
				                metadata.put("id", i);
				                jSONObject.put("metadata",metadata);
				               // jSONObject.put("icon",getIconImage(locnList.get(i).getElementType()));
					            //jSONObject.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
					                
					            //jSONObject.put("icon","../images/fav.png");
				                jsonAttr = null;
				                jSONObject.put("children","[{}]");
				                jSONArray.put(jSONObject);
				                jSONObject=null;
			                }
			        	}   
			    	}
			    	//CommonMessage.debugMsg(jSONArray);
		           out.print(jSONArray);
	        	   jSONArray=null;
		    	}catch(Exception e){
		           // CommonMessage.debugMsg(e);
		            e.printStackTrace();
		        }
		        finally {
		            out.close();
		        }
		        
			}
		   else if( action.equals("subjectArea_combo.dcm"))
			{
				try {
					 comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  subjectArea = docManagerService.getsubjectAreaCombo("",comboFilter);
					UIUtils.writeComboBox(response, subjectArea, comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   else if( action.equals("type_combo.dcm"))
			{
				try {
					comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  subjectArea = docManagerService.getTypeCombo("",comboFilter);
					UIUtils.writeComboBox(response, subjectArea, comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   else if( action.equals("category_combo.dcm"))
			{
				try {
					
					List<ComboBox> category = docManagerService.getComboCategory("",comboFilter);
					UIUtils.writeComboBox(response, category, comboFilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   else if( action.equals("search_combo.dcm"))
			{
				try {
					String searchTxt = request.getParameter("searchText");
					if(UIUtils.isValidKeyId(searchTxt))
					{
						searchTxt = searchTxt.toUpperCase();
						comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> category = docManagerService.getComboSearch(searchTxt,comboFilter);
					UIUtils.writeComboBox(response, category, comboFilter);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   else if( action.equals("role_combo.dcm"))
			{
				try {
				     comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> category = docManagerService.getComboRole("",comboFilter);
					UIUtils.writeComboBox(response, category, comboFilter);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   else if(action.equals("DocTemplate_input.dcm")){
			   UIUtils.forwardRequest(request, response, "/pages/DocumentTemplateMain.jsp");
		   }
		   else if(action.equals("DocGrid_getCol.dcm")){
			   PrintWriter out = response.getWriter();
			   String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.FileManager", "fileManager");
			   out.println(colModel);
			   CommonMessage.debugMsg("colModel....."+colModel);
			   
		   }
		   else if(action.equals("DocGrid_getData.dcm")){
			   PrintWriter out = response.getWriter();	
			   httpSession = request.getSession(false);
			   net.sf.json.JSONObject DocData = null;
			   JSONObject jsonObject = null;
			    String documentNo = request.getParameter("docNo");
			    String documentType = request.getParameter("docType"); 
				List< String[]> DocList  = docManagerService.getDocData(documentNo,documentType);
				DocData = UIUtils.convertToJqGridTableObject(DocList,request,0,0,DocList.size());
				CommonMessage.debugMsg("DocData....."+DocData);
				httpSession.removeAttribute("DocumentTempCommonFilter");
				httpSession.setAttribute("DocumentTempCommonFilter", DocData);
				out.println(DocData);
		   }
		   else if(action.equals("DocumentTemplate_input.dcm")){
			   String DocTempGrid=request.getParameter("grid");
			   String keyId=request.getParameter("keyId");
			   CommonMessage.debugMsg("Key id.,:"+keyId +"  Grid"+DocTempGrid);
			   if("true".equals(DocTempGrid))
				{
			   DocTlTemplateDefMst newDocTlTemplateDefMst=new DocTlTemplateDefMst();
			   newDocTlTemplateDefMst=docManagerService.getdocTempGridList(keyId);
			   request.setAttribute("DocTlTemplateDefMst",newDocTlTemplateDefMst);
				httpSession.setAttribute("DocTlTemplateDefMst", newDocTlTemplateDefMst);
				}
			   UIUtils.forwardRequest(request, response, "/pages/DocTemplate.jsp");
			 	
			 }
		   else if(action.equals("DocTemplate_getCol.dcm")){
			   PrintWriter out = response.getWriter();
				// CommonFilter commonFilter = populateCommonFilter(request,"DocTlTemplateDefMst",true);
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "DocumentTemplate"));
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "DocumentTemplate");
				//JSONObject colModel = JSONObject.fromString(tablemodel);
				request.removeAttribute("DocTlTemplateDefMst");
				httpSession.setAttribute("DocTlTemplateDefMst", colModel);	
				out.println(colModel);
		   }
		   else if(action.equals("DocTemplate_getData.dcm")){
			   PrintWriter out = response.getWriter();	
			   httpSession = request.getSession(false);
			   CommonFilter commonFilter = populateCommonFilter(request,"DocumentTempCommonFilter",true);
			   String search = request.getParameter("_search");
			   String keyId=request.getParameter("keyId");
				CommonMessage.debugMsg("Search......."+search);
				int rowCount = docManagerService.selectCount(commonFilter);
				CommonMessage.debugMsg("count...."+rowCount);
				Long totalCnt = (long)rowCount;
				commonFilter.setTotalRecordCnt(totalCnt);
				
			   net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
			   List<String[]> docTempList=docManagerService.getdocumentTempList(commonFilter,keyId);
			   CommonMessage.debugMsg("List Size"+docTempList.size());
			   if(search.equals("false"))
				{
					rowCount = docTempList.size();
					totalCnt = (long)rowCount;
					commonFilter.setTotalRecordCnt(totalCnt);
				}
			   if(docTempList != null && docTempList.size()>0)
	        	   jsonObject = UIUtils.convertToJqGridTableObject(docTempList,request,0,0);
	        	   out.println(jsonObject);				 		
	        	   httpSession.removeAttribute("DocumentTempCommonFilter");
	        	   httpSession.setAttribute("DocumentTempCommonFilter", commonFilter);
		   }
		   else if(action.equals("DocumentTemplate_getCol.dcm")){
			   PrintWriter out = response.getWriter();
				 CommonFilter commonFilter = populateCommonFilter(request,"DocTempCommonFilter",true);
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "DocTemplate"));
				String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.DocMgr", "DocTemplate");
				httpSession.removeAttribute("DocTempColModel");
				httpSession.setAttribute("DocTempColModel", colModel);	
				out.println(colModel);
			
		   }
		   else if(action.equals("DocumentTemplate_getData.dcm")){
			  
			   PrintWriter out = response.getWriter();	
			    httpSession = request.getSession(false);
			    String Type=request.getParameter("type");
			   
				 CommonMessage.debugMsg(".,.,get data.,."+Type);
			   CommonFilter commonFilter = populateCommonFilter(request,"DocTempCommonFilter",true);
			   net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
			   List<String[]> docTempList=docManagerService.getdocTempList(commonFilter,Type);
			
			   CommonMessage.debugMsg("List Size"+docTempList.size());
			   if(docTempList != null && docTempList.size()>0)
	        	   jsonObject = UIUtils.convertToJqGridTableObject(docTempList,request,0,0,commonFilter.getTotalRecordCnt());
	        	   out.println(jsonObject);				 		
	        	   httpSession.removeAttribute("DocTempCommonFilter");
	        	   httpSession.setAttribute("DocTempCommonFilter", commonFilter);
		   }
	}
	
	 private void  downloadFile(HttpServletResponse response,String fileName, String path ) throws FileNotFoundException,IOException{
		 
		 	String extension = UIUtils.getFileExtension(fileName);
		    FileInputStream fileToDownload = new FileInputStream(path);
		    ServletOutputStream output = response.getOutputStream();
		    if(fileName.endsWith(".pdf")){
		    	response.setContentType("application/pdf");
		    	
		    	//response.setHeader("Content-Disposition", "inline; filename="+ fileName);
		    }
		    else
		    	response.setContentType("application/txt");
		    
		    response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		  
		    response.setContentLength(fileToDownload.available());
		    byte[] buf = new byte[1024];
		    int c;
		    while ((c = fileToDownload.read(buf)) >  0)
		    {
		    	output.write(buf, 0, c);
		    }
		    output.flush();
		    output.close();
		    fileToDownload.close();

	    }
	
	private void saveTemplateValues(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		try{
	    	if( httpSession != null && user != null)
	    	{		    		
	    		String values = request.getParameter("keywordDatas");
	    		CommonMessage.debugMsg("Values : "+values);
	    		DocTlTemplateDefvalDtl newDocTlTemplateDefvalDtl = new DocTlTemplateDefvalDtl();	 
	    		newDocTlTemplateDefvalDtl.setDtpvCreatedby(user.getUsrm_ccno());
	    		newDocTlTemplateDefvalDtl =(DocTlTemplateDefvalDtl)UIUtils.setBeanProperties((Object)newDocTlTemplateDefvalDtl,request);
	    		
	    		if(UIUtils.isValidKeyId(values))
				{
					JSONArray jsonArray = JSONArray.fromString(values);	
					DocTlTemplateDefvalDtl docTlTemplateDefvalDtl = new DocTlTemplateDefvalDtl();
					List<DocTlTemplateDefvalDtl> tempValues = (List<DocTlTemplateDefvalDtl>) UIUtils.convertJSONArrToList(docTlTemplateDefvalDtl, jsonArray);
					if( newDocTlTemplateDefvalDtl != null)
						newDocTlTemplateDefvalDtl.setDocTlTemplateDefvalDtl(tempValues);
				}
	    		newDocTlTemplateDefvalDtl =	 docManagerService.createValues(newDocTlTemplateDefvalDtl);	
				
				
				
				JSONObject returnData = new JSONObject();			
				JSONObject successData = new JSONObject();	
				
				successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
				returnData.put("formClear",false);	
				returnData.put("successData",successData);
				out.print(returnData.toString());
	    	}	    	
		}
		catch(ValidationExceptions e)
		{
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");	
			out.print(errMessage.toString());		
		}
		catch(BusinessApplicationExceptions e)
		{
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"DocMgr");				
			out.print(errMessage.toString());
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());			
		}	
	}
	private void saveFolder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		String levelNo = (String) httpSession.getAttribute(DocManagerServlet_levelNo);		    
		String parentId = (String) httpSession.getAttribute(DocManagerServlet_parentId);
		String id = (String) httpSession.getAttribute(DocManagerServlet_Id);
		String dispOrder = (String) httpSession.getAttribute(DocManagerServlet_dispOrder);
		String dispCode = (String) httpSession.getAttribute(DocManagerServlet_dispCode);
		String folderName = request.getParameter("folder").toUpperCase();	
		String dateTime = CommonFunctions.dateTimeNow();
		int level = Integer.parseInt(levelNo) + 1;
    	String folderMode = request.getParameter("folderMode");
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
		CommonMessage.debugMsg("FOLDER MODE : "+folderMode);
		String msgPropertyIdnt;
		CommonMessage.debugMsg("request : "+level+"-"+dispCode+"-"+id);
		String folderPath = getDocManagerFolderName(request,level,dispCode,id);
		/*if(UIUtils.isValidKeyId(folderMode))			
		{
			folderPath = folderPath.substring(0, folderPath.lastIndexOf("/"));
			folderPath = folderPath.substring(0, folderPath.lastIndexOf("/"));
			CommonMessage.debugMsg("FOLDER PATH : "+folderPath);
			File newFile = new File(folderPath);
			newFile.renameTo(new File(folderPath + "\\"+folderName));
        	
		}
    	 */
    	//File newFile = new File(realPath +fileName);
    	//newFile.renameTo(new File(imagePath));
		CommonMessage.debugMsg("folderMode : "+folderMode);
        response.setStatus(response.SC_OK);
	    if( httpSession != null && user != null)
	    {		
	    	//DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
	    	//dcmTlDocumentmanager.setDmdmRefdocno(dmdmRefdocno)
	    	DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();
	    	if(UIUtils.isValidKeyId(folderMode))
	    	{
	    		dcmTlDocumentlayout = docManagerService.getFolderList(dispCode.toUpperCase());
	    		dcmTlDocumentlayout.setDmlyName(folderName);
	    	}
	    	else
	    	{
		    	
		    	dcmTlDocumentlayout.setDmlyName(folderName);
		    	dcmTlDocumentlayout.setDmlyParentid(id);
		    	dcmTlDocumentlayout.setDmlyLevelno(Integer.toString(level));
		    	dcmTlDocumentlayout.setDmlyDisplayorder(Integer.toString(level));
		    	dcmTlDocumentlayout.setDmlyIsfileavl("N");
		    	dcmTlDocumentlayout.setDmlyIsparent("N");
		    	dcmTlDocumentlayout.setDmlyActive("Y");
		    	dcmTlDocumentlayout.setDmlyCreatedby(user.getUsrm_ccno());
		    	dcmTlDocumentlayout.setDmlyCreatedon(dateTime);
		    	dcmTlDocumentlayout.setDmlyModifiedon(dateTime);
	    	}
			
		
			
	    	DcmTlDocumentlayout existDcmTlDocumentlayout = (DcmTlDocumentlayout) httpSession.getAttribute("docLayoutServlet");
	    
			
			try{
                if(UIUtils.isValidKeyId(folderName)){
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( dcmTlDocumentlayout.getDmlyKeyid()))
					{
						dcmTlDocumentlayout = docManagerService.create(dcmTlDocumentlayout,existDcmTlDocumentlayout);
						httpSession.removeAttribute(DocManagerServlet_folder);
					}
					else{
						insert = false;						 
						dcmTlDocumentlayout = docManagerService.update(dcmTlDocumentlayout,existDcmTlDocumentlayout);
						
						httpSession.setAttribute(DocManagerServlet_folder,folderName);
						httpSession.removeAttribute(DocManagerServlet_folder);
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
					successData.put("keyId", dcmTlDocumentlayout.getDmlyKeyid());
					successData.put("name", dcmTlDocumentlayout.getDmlyName());
					if(dcmTlDocumentlayout.getDmlyKeyid().equals(dcmTlDocumentlayout.getDmlyParentid()))
						successData.put("parentId",DocManagerLayout_Id);						
					else
						successData.put("parentId", dcmTlDocumentlayout.getDmlyParentid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					
					out.print(returnData.toString());
				}
	             else{
            	    ServletOutputStream out = response.getOutputStream(); 
	                JSONObject successData = new JSONObject();
	  				successData.put("msg","Select Upload File");
	  				successData.put("folderName", dcmTlDocumentlayout.getDmlyName());
	  				JSONObject returnData = new JSONObject();
	 				httpSession.removeAttribute(DocManagerServlet_folder);
	  				returnData.put("successData", successData);				
	  				
	  				out.print(returnData.toString());
	             }  	  
	
			}catch(ValidationExceptions e)
			{
				ServletOutputStream out = response.getOutputStream();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "");
				//errMessage.put("formMode",genTlFactorymstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				ServletOutputStream out = response.getOutputStream();
				CommonMessage.debugMsg("BusinessApplicationExcepions"  );
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "");
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
	
	private void saveFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);		
		String levelNo = (String) httpSession.getAttribute(DocManagerServlet_levelNo);		    
		String parentId = (String) httpSession.getAttribute(DocManagerServlet_parentId);
		String id = (String) httpSession.getAttribute(DocManagerServlet_Id);
		String dispOrder = (String) httpSession.getAttribute(DocManagerServlet_dispOrder);
		String dispCode = (String) httpSession.getAttribute(DocManagerServlet_dispCode);
		String docNo =request.getParameter("docNo");
		String docType = request.getParameter("docType");
		//MADHAN
		String createdon = request.getParameter("hdncreatedon");
		 
		String viewsMode = request.getParameter("viewsMode");
		String saveMode = request.getParameter("saveMode");
		String changes;
		boolean revisionHistory = false;
		if(UIUtils.isValidKeyId(saveMode))
		{
			revisionHistory = true;			
		}
		String dateTime = CommonFunctions.dateTimeNow();
		int level =0;
		if(UIUtils.isValidKeyId(levelNo))
		 level = Integer.parseInt(levelNo) + 1;
		
		String fileName = (String) httpSession.getAttribute(DocManagerServlet_filename);
		if(fileName!=null){
			fileName=fileName.replace("'","");	
		}
		boolean fileFlag = true;
		if(! UIUtils.isValidKeyId(fileName))
		{
			fileFlag =false;
			fileName = request.getParameter("filename");
		}
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		String msgPropertyIdnt;
		
		if( httpSession != null && user != null)
		{	
			String modFlag = request.getParameter("notModify");
			//if(UIUtils.isValidKeyId("notModify"))
			if(UIUtils.isValidKeyId(modFlag))
			{
				if(modFlag.equals("Y"))
					httpSession.removeAttribute("docLayoutFileServlet");
			}
			//MADHAN
			String FipDescription = request.getParameter("dmdmTitle");
			
			DcmTlDocumentmanager existDcmTlDocumentmanager = (DcmTlDocumentmanager) httpSession.getAttribute("docLayoutFileServlet");
			DcmTlRevisionhistory dcmTlRevisionhistory = new DcmTlRevisionhistory();
			DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
			dcmTlDocumentmanager =(DcmTlDocumentmanager)UIUtils.setBeanProperties((Object)dcmTlDocumentmanager,request);
			dcmTlRevisionhistory =(DcmTlRevisionhistory)UIUtils.setBeanProperties((Object)dcmTlRevisionhistory,request);
			//madhan
			if (docNo != null ) {
			dcmTlDocumentmanager.setDmdmRefdocno(docNo);
			}
			if (docType != null ) {
			dcmTlDocumentmanager.setDmdmRefdoctype(docType);
			}
			
			if (FipDescription != null ) {
				dcmTlDocumentmanager.setDmdmDescription(FipDescription);
			}
			
			if(existDcmTlDocumentmanager != null)
			{
				//String docPath = existDcmTlDocumentmanager.getDmdmPath();	
			    String docPath = DOC_ROOT_PATH + existDcmTlDocumentmanager.getDmdmPath();		
				CommonMessage.debugMsg(" docPath " + docPath);
			    String fileInDocPath = docPath +existDcmTlDocumentmanager.getDmdmFilename();

				if(fileFlag)
				{
					File newFile = new File(fileInDocPath);
					boolean success = newFile.delete();
					if(success)
					{
						File replacedFile = new File(docRealPath +fileName);
						replacedFile.renameTo(new File(docPath+fileName));
					}
				
			    	 response.setStatus(response.SC_OK);
				}
		    	 if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmKeyid()))
						dcmTlDocumentmanager.setDmdmKeyid(existDcmTlDocumentmanager.getDmdmKeyid());
				if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmRefdocno()))
					dcmTlDocumentmanager.setDmdmRefdocno(existDcmTlDocumentmanager.getDmdmRefdocno());
				if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmRefdoctype()))
					dcmTlDocumentmanager.setDmdmRefdoctype(existDcmTlDocumentmanager.getDmdmRefdoctype());
				if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmIsodoctype()))
					dcmTlDocumentmanager.setDmdmIsodoctype(existDcmTlDocumentmanager.getDmdmIsodoctype());
				if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmSlno()))
					dcmTlDocumentmanager.setDmdmSlno(existDcmTlDocumentmanager.getDmdmSlno());
				dcmTlDocumentmanager.setDmdmFilename(fileName);
				dcmTlDocumentmanager.setDmdmPath(existDcmTlDocumentmanager.getDmdmPath());
				
				dcmTlDocumentmanager.setDmdmBloblength("0");
				dcmTlDocumentmanager.setDmdmBlobfile("EMPTY_BLOB()");
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmCategory()))
				{
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmCategory()))
						dcmTlDocumentmanager.setDmdmCategory(existDcmTlDocumentmanager.getDmdmCategory());
					else
						dcmTlDocumentmanager.setDmdmCategory("{}");
				}
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmOwner()))
				{
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmOwner()))
						dcmTlDocumentmanager.setDmdmOwner(existDcmTlDocumentmanager.getDmdmOwner());
					else
						dcmTlDocumentmanager.setDmdmOwner(user.getUsrm_ccno());
				}
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmApprovedby()))
				{
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmApprovedby()))
						dcmTlDocumentmanager.setDmdmApprovedby(existDcmTlDocumentmanager.getDmdmApprovedby());
					else
						dcmTlDocumentmanager.setDmdmApprovedby(user.getUsrm_ccno());
				}
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmSubjectarea()))
				{
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmSubjectarea()))
						dcmTlDocumentmanager.setDmdmSubjectarea(existDcmTlDocumentmanager.getDmdmSubjectarea());
					else
						dcmTlDocumentmanager.setDmdmSubjectarea("{}");
				}
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmType()))
				{
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmType()))
						dcmTlDocumentmanager.setDmdmType(existDcmTlDocumentmanager.getDmdmType());
					else
						dcmTlDocumentmanager.setDmdmType("{}");
				}
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmTitle()))
				{
					/*if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmTitle()))
						dcmTlDocumentmanager.setDmdmTitle(existDcmTlDocumentmanager.getDmdmTitle());
					else
						dcmTlDocumentmanager.setDmdmTitle(fileName);*/
					dcmTlDocumentmanager.setDmdmTitle("{}");
				}
				dcmTlDocumentmanager.setDmdmActive("Y");
				
				
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmCreatedby()))
						dcmTlDocumentmanager.setDmdmCreatedby(existDcmTlDocumentmanager.getDmdmCreatedby());
					else
						dcmTlDocumentmanager.setDmdmCreatedby(user.getUsrm_ccno());
				
				
					if(UIUtils.isValidKeyId(existDcmTlDocumentmanager.getDmdmCreatedon()))
						dcmTlDocumentmanager.setDmdmCreatedon(existDcmTlDocumentmanager.getDmdmCreatedon());
					else
						dcmTlDocumentmanager.setDmdmCreatedon(dateTime);
				
				
				dcmTlDocumentmanager.setDmdmModifiedon(dateTime);
				
				dcmTlRevisionhistory.setDmrhCreatedby(user.getUsrm_ccno());					
			}
			else
			{
				if( !UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmRefdocno()))
		    		dcmTlDocumentmanager.setDmdmRefdocno(parentId);
		    	if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmRefdoctype()))
		    		dcmTlDocumentmanager.setDmdmRefdoctype(levelNo);
		    	if( UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmIsodoctype()))
		    		dcmTlDocumentmanager.setDmdmIsodoctype(id);
		    	
				String docPath = getDocManagerFolderName(request,level,dispCode,id);
				CommonMessage.debugMsg(" docPath " + docPath);
				
				if("SRL".equals(dcmTlDocumentmanager.getDmdmRefdoctype())){
					String SrlProject="Saral Project";
					String ProTitle=request.getParameter("ProTitle");
					CommonMessage.debugMsg("The ProTitle"+ProTitle);
					CommonMessage.debugMsg("docNo"+docNo);
					String Keywords=SrlProject+","+"Project No.-"+docNo+","+ProTitle;
					String Description=SrlProject+","+"Project No.-"+docNo+","+ProTitle;
					dcmTlDocumentmanager.setDmdmRefdocno(docNo);
					dcmTlDocumentmanager.setDmdmSubjectarea("SARAL");
					dcmTlDocumentmanager.setDmdmType("SARAL");
					dcmTlDocumentmanager.setDmdmTitle("Saral Project");
					dcmTlDocumentmanager.setDmdmKeywords(Keywords);
					dcmTlDocumentmanager.setDmdmDescription(Description);
					dcmTlDocumentmanager.setDmdmApprovedby(user.getUsrm_ccno());
					dcmTlDocumentmanager.setDmdmOwner(user.getUsrm_ccno());
				}
				
				
				String fileInDocPath="";
				if("MOM".equals(dcmTlDocumentmanager.getDmdmRefdoctype()))
					fileInDocPath= docPath +dcmTlDocumentmanager.getDmdmRefdocno()+"_"+UIUtils.now()+fileName.substring(fileName.indexOf("."));
				else
					 fileInDocPath = docPath +dcmTlDocumentmanager.getDmdmRefdocno()+"_"+UIUtils.now();
				CommonMessage.debugMsg(" fileInDocPath " + fileInDocPath);
				String description = fileInDocPath.replace(DOC_ROOT_PATH, ""); // docPath.substring(fileInDocPath.indexOf("/"));
				CommonMessage.debugMsg(" docRealPath +fileName " + docRealPath +fileName);
				
				CommonMessage.debugMsg(" fileName.length 1234  " +fileName.substring(fileName.indexOf(".")));
				
				CommonMessage.debugMsg(" Refdoctype 1234  " +dcmTlDocumentmanager.getDmdmRefdoctype());
				
				
				File newFile = new File(docRealPath +fileName);
		    	newFile.renameTo(new File(fileInDocPath));
		    	//parentId=levelNo=id="{}";
		    		    	
		    	//dcmTlDocumentmanager.setDmdmCreatedby(user.getUsrm_ccno());
		    	response.setStatus(response.SC_OK);
		    	
				dcmTlDocumentmanager.setDmdmSlno("1");
				dcmTlDocumentmanager.setDmdmFilename(fileName);
				dcmTlDocumentmanager.setDmdmPath(description);
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmKeywords()))
					dcmTlDocumentmanager.setDmdmKeywords("{}");
				dcmTlDocumentmanager.setDmdmBloblength("0");
				dcmTlDocumentmanager.setDmdmBlobfile("EMPTY_BLOB()");
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmCategory()))
					dcmTlDocumentmanager.setDmdmCategory("{}");
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmOwner()))
					dcmTlDocumentmanager.setDmdmOwner(user.getUsrm_ccno());
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmApprovedby()))
					dcmTlDocumentmanager.setDmdmApprovedby(user.getUsrm_ccno());
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmSubjectarea()))
					dcmTlDocumentmanager.setDmdmSubjectarea("{}");
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmTitle()))
					dcmTlDocumentmanager.setDmdmTitle("{}");//dcmTlDocumentmanager.setDmdmTitle(fileName);
				if(!UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmType()))
					dcmTlDocumentmanager.setDmdmType("{}");
				dcmTlDocumentmanager.setDmdmActive("Y");
				dcmTlDocumentmanager.setDmdmCreatedby(user.getUsrm_ccno());
				dcmTlDocumentmanager.setDmdmCreatedon(dateTime);
				dcmTlDocumentmanager.setDmdmModifiedon(dateTime);
			}
		
			/*dcmTlDocumentmanager.setDmdmTemp1("{}");
			dcmTlDocumentmanager.setDmdmTemp2("{}");
			dcmTlDocumentmanager.setDmdmTemp3("{}");*/
			
			ServletOutputStream out1 = response.getOutputStream();
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
			List<String> allowed = Arrays.asList(
			    "jpg","jpeg","png","pdf","doc","docx","xls","xlsx","ppt","pptx","txt"
			);
			if (!"TEMP".equals(docType) ) {
				if (!allowed.contains(ext)) {
					out1.println("Invalid file type uploaded");

					throw new ServletException("Invalid file type uploaded");
				}
				}
			
			
			
			try{
              
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( dcmTlDocumentmanager.getDmdmKeyid()))
					{
						dcmTlDocumentmanager = docManagerService.createFile(dcmTlDocumentmanager,existDcmTlDocumentmanager);
						httpSession.removeAttribute(DocManagerServlet_folder);
					}
					else{
						insert = false;						 
						dcmTlDocumentmanager = docManagerService.updateFile(dcmTlDocumentmanager,existDcmTlDocumentmanager,dcmTlRevisionhistory);
						
						//httpSession.setAttribute(DocManagerServlet_folder,folderName);
						httpSession.removeAttribute(DocManagerServlet_folder);
					}
					
					JSONObject successData = new JSONObject();
					 if( insert){
							msgPropertyIdnt = "success-save";
						 }else
							msgPropertyIdnt = "success-update";
						 
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						
						successData.put("documentNo", docNo);
						successData.put("docType", docType);
					ServletOutputStream out = response.getOutputStream();
					//JSONObject successData = new JSONObject();
					//successData.put("msg","Data has been Inserted");
					successData.put("keyId", dcmTlDocumentmanager.getDmdmKeyid());
					//if(dcmTlDocumentlayout.getDmlyKeyid().equals(dcmTlDocumentlayout.getDmlyParentid()))
						//successData.put("parentId",DocManagerLayout_Id);						
				//	else
						//successData.put("parentId", dcmTlDocumentlayout.getDmlyParentid());
					httpSession.removeAttribute(DocManagerServlet_filename);
					httpSession.removeAttribute("docLayoutFileServlet");
					
					JSONObject returnData = new JSONObject();
					String folderId = request.getParameter("id");
					String folderLevel = request.getParameter("levelNo");
					String folderName = request.getParameter("folderName");
					
					String allowModify = request.getParameter(DocMgrConstants.mod);
					String allowDel = request.getParameter(DocMgrConstants.del);
					String allowDownload = request.getParameter(DocMgrConstants.dld);
					String allowRights = request.getParameter(DocMgrConstants.rights);
					
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocManagerServlet_Id)))
						returnData.put("nodeId", (String) httpSession.getAttribute(DocManagerServlet_Id));
					else
					{
						if(UIUtils.isValidKeyId(folderId))
							returnData.put("nodeId",folderId);
					}
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocManagerServlet_levelNo)))
						returnData.put("elemType", (String) httpSession.getAttribute(DocManagerServlet_levelNo));	
					else
					{
						if(UIUtils.isValidKeyId(folderName))
							returnData.put("elemType",folderLevel);
					}
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocManagerServlet_dispCode)))
						returnData.put("dispCode", (String) httpSession.getAttribute(DocManagerServlet_dispCode));	
					else
					{
						if(UIUtils.isValidKeyId(folderLevel))
							returnData.put("dispCode",folderName);
					}
					
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocMgrConstants.modifyRights)))
						returnData.put(DocMgrConstants.mod, (String) httpSession.getAttribute(DocMgrConstants.modifyRights));
					else
					{
						if(UIUtils.isValidKeyId(allowModify))
							returnData.put(DocMgrConstants.mod,allowModify);
					}
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocMgrConstants.deleteRights)))
						returnData.put(DocMgrConstants.del, (String) httpSession.getAttribute(DocMgrConstants.deleteRights));	
					else
					{
						if(UIUtils.isValidKeyId(allowDel))
							returnData.put(DocMgrConstants.del,allowDel);
					}
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocMgrConstants.downloadRights)))
						returnData.put(DocMgrConstants.dld, (String) httpSession.getAttribute(DocMgrConstants.downloadRights));	
					else
					{
						if(UIUtils.isValidKeyId(allowDownload))
							returnData.put(DocMgrConstants.dld,allowDownload);
					}
					if(UIUtils.isValidKeyId((String) httpSession.getAttribute(DocMgrConstants.User_Rights)))
						returnData.put(DocMgrConstants.rights, (String) httpSession.getAttribute(DocMgrConstants.User_Rights));	
					else
					{
						if(UIUtils.isValidKeyId(allowRights))
							returnData.put(DocMgrConstants.rights,allowRights);
					}
					if(UIUtils.isValidKeyId(viewsMode))
						returnData.put("viewsMode",viewsMode);
					returnData.put("successData", successData);
					if("SRL".equals(dcmTlDocumentmanager.getDmdmRefdoctype())){
						returnData.put("RefdocType",dcmTlDocumentmanager.getDmdmRefdoctype());
						returnData.put("formClear",false);
					}
					out.print(returnData.toString());
	
			}catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("E : "+e.toString());
				ServletOutputStream out = response.getOutputStream();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");	
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
			} catch(BusinessApplicationExceptions e)
			{ 
				ServletOutputStream out = response.getOutputStream();
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "");
				out.print(errMessage.toString());
					
				
			} catch(Exception e)
			{
				ServletOutputStream out = response.getOutputStream();
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
				out.print(err.toString());
			}
		}
		
	}
	
	private void saveUserRights(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		String msgPropertyIdnt;
		
		if( httpSession != null && user != null)
		{
			
			
			DocMgrBean docMgrBean =new DocMgrBean();
			DocTlRoleRights docTlRoleRights = new DocTlRoleRights();
			docTlRoleRights = (DocTlRoleRights)UIUtils.setBeanProperties((Object)docTlRoleRights,request);
			docMgrBean = (DocMgrBean)UIUtils.setBeanProperties((Object)docMgrBean,request);
			DocTlRoleRights existDocTlRoleRights = (DocTlRoleRights) httpSession.getAttribute("docUserRightsFolderServlet");
			docTlRoleRights.setRlriCreatedby(user.getUsrm_ccno());			
			
			try{
	            
				boolean insert = true;
				if( ! UIUtils.isValidKeyId( docTlRoleRights.getRlriKeyid()))
				{
					existDocTlRoleRights = docManagerService.createUserRights(docTlRoleRights,existDocTlRoleRights,docMgrBean);					
				}
				else{
					insert = false;						 
					existDocTlRoleRights = docManagerService.updateUserRights(docTlRoleRights,existDocTlRoleRights,docMgrBean);
				}
				ServletOutputStream out = response.getOutputStream();
				JSONObject successData = new JSONObject();
				
				 if( insert){
					msgPropertyIdnt = "success-save";
				}else
					msgPropertyIdnt = "success-update";
					 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				
				
				httpSession.removeAttribute("docUserRightsFolderServlet");
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);	
				returnData.put("folderId",existDocTlRoleRights.getRlriDocid());
				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());
				}
			catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("E : "+e.toString());
				ServletOutputStream out = response.getOutputStream();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");	
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				CommonMessage.debugMsg(e.toString());
				ServletOutputStream out = response.getOutputStream();				
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "DocMgr");
				out.print(errMessage.toString());
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Exc "+e.toString());
				ServletOutputStream out = response.getOutputStream();				
				JSONObject err = new JSONObject();				
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
				out.print(err.toString());
			}
		}
	}
	private void delFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		String fileId = request.getParameter("fileId");
		String viewsMode = request.getParameter("viewsMode");
		
		
		String allowModify = request.getParameter(DocMgrConstants.mod);
		String allowDel = request.getParameter(DocMgrConstants.del);
		String allowDownload = request.getParameter(DocMgrConstants.dld);
		String allowRights = request.getParameter(DocMgrConstants.rights);
		   
		   
		ServletOutputStream out = response.getOutputStream();
		 CommonMessage.debugMsg("Delete File Method ");
		if(httpSession != null)
		{
			
			DcmTlDocumentmanager dcmTlDocumentmanager = docManagerService.getDocMgr(fileId);
			if(dcmTlDocumentmanager != null)
			{
				String docPath = DOC_ROOT_PATH +dcmTlDocumentmanager.getDmdmPath();
				String fileInDocPath = docPath +dcmTlDocumentmanager.getDmdmFilename();
				CommonMessage.debugMsg("EXIST Path : "+fileInDocPath);
				File fileToDel = new File(fileInDocPath);
				boolean success = fileToDel.delete();
				response.setStatus(response.SC_OK);
				CommonMessage.debugMsg("success : "+success);
				if(success==true || success==false )
				{
					try{
						
						dcmTlDocumentmanager = docManagerService.deleteFile(dcmTlDocumentmanager);
						JSONObject successData = new JSONObject();
						successData.put("msg","File Deleted Successfully");
					
						JSONObject returnData = new JSONObject();
						
						returnData.put("successData", successData);				
						String folderId = request.getParameter("id");
						String folderLevel = request.getParameter("levelNo");
						String folderName = request.getParameter("folderName");
					
						if(UIUtils.isValidKeyId(folderId))
							returnData.put("nodeId",folderId);
						
						if(UIUtils.isValidKeyId(folderId))
							returnData.put("elemType",folderName);
						
						if(UIUtils.isValidKeyId(folderId))
							returnData.put("dispCode",folderLevel);
						if(UIUtils.isValidKeyId(viewsMode))
							returnData.put("viewsMode",viewsMode);
						
						if(UIUtils.isValidKeyId(allowModify))
							returnData.put(DocMgrConstants.mod, allowModify);
						if(UIUtils.isValidKeyId(allowDel))
							returnData.put(DocMgrConstants.del, allowDel);
						if(UIUtils.isValidKeyId(allowDownload))
							returnData.put(DocMgrConstants.dld, allowDownload);
						if(UIUtils.isValidKeyId(allowRights))
							returnData.put(DocMgrConstants.rights, allowRights);
						   
						out.print(returnData.toString());
					}
					catch(Exception e)
					{
						
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						//err.put("tpmException", "Data Not Saved");
						err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
						out.print(err.toString());
					}
				}
			
			}
		}
	}
	private void delFolder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		String folderId = request.getParameter("folderId");
		String levelNo=request.getParameter("levelNo");
		String dispCode=request.getParameter("dispCode");
		String parentId=request.getParameter("parentId");
		
		int level = Integer.parseInt(levelNo);
		
		ServletOutputStream out = response.getOutputStream();
		 CommonMessage.debugMsg("Delete Folder Method "+level);
		if(httpSession != null)
		{
			String dbUser= null;//docManagerService.getDocManagerDao().getDbActionTemplate().getDataSource().getUser(); 
			//String docManagerFolder = APP_DOCMANAGER_PATH + "/";
			String docManagerFolder = APP_DOCMANAGER_PATH + "/"+dbUser  + "/" +DOCMANAGER_PATH_SAVE  + "/";
			if(level>=3)
				docManagerFolder +=  docManagerService.getAllParent(parentId,level);
			
				
				docManagerFolder += dispCode+ "/";				
			
			 CommonMessage.debugMsg("Folder Path "+docManagerFolder);
			 File folderToDel = new File(docManagerFolder);			 
			 File[] listOfFiles = folderToDel.listFiles(); 
			 String files;
			 
			 if(listOfFiles != null)
			 {
				  for (int i = 0; i < listOfFiles.length; i++) 
				  {
				 
				   if (listOfFiles[i].isFile()) 
				   {
					   files = listOfFiles[i].getName();
					   CommonMessage.debugMsg(docManagerFolder+files);
					   File fileToDel = new File(docManagerFolder+files);
					   boolean fileDelSuccess = fileToDel.delete();
					   response.setStatus(response.SC_OK);
					   
				   }
				   else
					   new File(listOfFiles[i].toString()).delete();
				 }
			 }
			
			boolean success = new File(docManagerFolder).delete();
			response.setStatus(response.SC_OK);
			
			
			DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();
			dcmTlDocumentlayout.setDmlyKeyid(folderId);
			if(success)
			{
				try{
						
					dcmTlDocumentlayout = docManagerService.delete(dcmTlDocumentlayout);
					JSONObject successData = new JSONObject();
					successData.put("msg","Folder Deleted Successfully");
					
					JSONObject returnData = new JSONObject();
						
					returnData.put("successData", successData);				
						/*String folderId = request.getParameter("id");
						String folderLevel = request.getParameter("levelNo");
						String folderName = request.getParameter("folderName");*/
					
						if(UIUtils.isValidKeyId(parentId))
						{
							if(UIUtils.isValidKeyId(folderId))
							{
								if(folderId.equals(parentId))
									parentId = DocManagerLayout_Id;
							}
							returnData.put("nodeId",parentId);
						}
						
						if(UIUtils.isValidKeyId(dispCode))
							returnData.put("elemType",dispCode);
						
						if(UIUtils.isValidKeyId(levelNo))
						{
							level = level-1;
							returnData.put("dispCode",Integer.toString(level));
						}
						
						out.print(returnData.toString());
					}
					catch(Exception e)
					{
						
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						//err.put("tpmException", "Data Not Saved");
						err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
						out.print(err.toString());
					}
				}
			
			
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

        String filename = request.getHeader("X-File-Name");
        if(filename !=null){
        	/*Pattern pattern = Pattern.compile("\\s+");
            Matcher matcher = pattern.matcher(filename);
            boolean check = matcher.find();
            String fn = matcher.replaceAll(" ");
            CommonMessage.debugMsg("filename    filename  "+fn);*/
        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
        	
        }
        
      
        httpSession.removeAttribute(DocManagerServlet_filename);
		httpSession.setAttribute(DocManagerServlet_filename,filename);
        
        try {
            is = request.getInputStream();
            CommonMessage.debugMsg(docRealPath +" ---- "+ filename);
            fos = new FileOutputStream(new File(docRealPath + filename));
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
	private String getDocManagerFolderName(HttpServletRequest request ,int level,String folder,String parentId) throws Exception{
//		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String refType = request.getParameter("folder");
		if( ! UIUtils.isValidKeyId(refType))
			refType  = request.getParameter("docType");
	//	String refType = (String)request.getSession(false).getAttribute(FileManagerServlet_documentType);
		
		String folderMode = request.getParameter("folderMode");
		
		//String dbUser = "PERFEXPGPRD";// null;//docManagerService.getDocManagerDao().getDbActionTemplate().getDataSource().getUser(); 
		String dbUser = "";
	    try (Connection conn = docManagerService.getDocManagerDao()
	            .getDbActionTemplate()
	            .getDataSource()
	            .getConnection()) {

	        dbUser = conn.getMetaData().getUserName();
	        CommonMessage.debugMsg(dbUser + " dbUser dbUserdbUser ");


	    } catch (Exception e) {
	        e.printStackTrace();
	        dbUser = "PERFEXPGPRD_TEMP";
	    }
		String docManagerFolder = APP_DOCMANAGER_PATH + "/"+dbUser + DOCMANAGER_PATH_SAVE  + "/";//+ refType +"/"  ;//+ user.getUsrm_keyid().replace("/", "").replace("\\", "");
		
		 CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
	     CommonMessage.debugMsg(" APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
	     CommonMessage.debugMsg(" docManagerFolder " + docManagerFolder );
		//String docManagerFolder = APP_DOCMANAGER_PATH + "/";// + refType +"/"  ;//+ user.getUsrm_keyid().replace("/", "").replace("\\", "");

		if(level>3)
			docManagerFolder +=  docManagerService.getAllParent(parentId,level);
		else
		{
			
			
			 if(level==3)
				 docManagerFolder += folder+ "/";
			
		}
		if(UIUtils.isValidKeyId(refType))
		{
			refType = refType.toUpperCase();
			if(!UIUtils.isValidKeyId(folderMode))
			{
				docManagerFolder += refType+ "/";
			}
		}
		CommonMessage.debugMsg(" --- -- docManagerFolder " + docManagerFolder);
		try{
			if(UIUtils.isValidKeyId(folderMode))
			{
				String renameFolder = docManagerFolder.substring(0, docManagerFolder.lastIndexOf("/"));				
					   renameFolder = renameFolder.substring(0, renameFolder.lastIndexOf("/"));					  
					   renameFolder = renameFolder +"/"+refType+"/";
				File f = new File(renameFolder);			
				new File(docManagerFolder).renameTo(f);
			}
			else
			{
				new File(docManagerFolder).mkdirs();
			}
			CommonMessage.debugMsg(" --- -- docManagerFolder " + docManagerFolder);
		}catch(SecurityException e){
			e.printStackTrace();
			CommonMessage.debugMsg(e.getMessage());
		}
		return docManagerFolder;
	}
	  public String getIconImage(String elementType)
	    {
		  	String imgUrl = null;
	    	if(elementType.equals("DM"))
	    		imgUrl =  "images/FnLocn/fc.png";
	    	
	    	return imgUrl;
	    	
	    }
	    
	    public String getImageUrl(String elementType)
	    {
	    	CommonMessage.debugMsg(elementType);
	    	
	    	String imgUrl = null;
	    	if(elementType.equals("DM"))
	    		imgUrl =  "images/companyy.jpg";
	    	else if(elementType.indexOf("JAVA")>=0)
	    		imgUrl =  "images/defaultIcons/java.png";
	    	else if(elementType.indexOf("Text")>=0)
	    		imgUrl =  "images/defaultIcons/txt.png";
	    	else if(elementType.indexOf("JPEG")>=0)
	    		imgUrl =  "images/defaultIcons/jpg.png";
	    	else if(elementType.indexOf("Word")>=0)
		    	imgUrl =  "images/defaultIcons/doc.png";
		    else if(elementType.indexOf("GIF")>=0)
			    imgUrl =  "images/defaultIcons/gif.png";
			else if(elementType.indexOf("Jar")>=0)
				imgUrl =  "images/defaultIcons/jar.png";
			else if(elementType.indexOf("Adobe")>=0)
			    imgUrl =  "images/defaultIcons/pdf.png";
			else if(elementType.indexOf("PNG")>=0)
				imgUrl =  "images/defaultIcons/png.png";
			else if(elementType.indexOf("Excel")>=0)
			{
				if(elementType.indexOf("Comma")>=0)
					imgUrl =  "images/defaultIcons/csv.png";
				else
					imgUrl =  "images/defaultIcons/xls.png";
			}
			else if(elementType.indexOf("PowerPoint")>=0)
				imgUrl =  "images/defaultIcons/ppt.png";
			else if(elementType.indexOf("Folder")>=0 || elementType.indexOf("folder")>=0)
				imgUrl =  "images/defaultIcons/folder.png";
			else if(elementType.indexOf("Application Extension")>=0 || elementType.indexOf("Application extension")>=0)
				imgUrl =  "images/defaultIcons/dll.png";
			else if(elementType.indexOf("SQL")>=0)
				imgUrl =  "images/defaultIcons/sql.png";
			else if(elementType.indexOf("Bitmap")>=0)
				imgUrl =  "images/defaultIcons/bmp.png";
			else if(elementType.indexOf("HTML")>=0)
				imgUrl =  "images/defaultIcons/html.png";
			else if(elementType.indexOf("avi")>=0)
				imgUrl =  "images/defaultIcons/avi.png";
			else if(elementType.indexOf("Cascading")>=0)
				imgUrl =  "images/defaultIcons/css.png";
			else if(elementType.indexOf("JScript")>=0)
				imgUrl =  "images/defaultIcons/js.png";
			else if(elementType.indexOf("mp3")>=0)
				imgUrl =  "images/defaultIcons/mp3.png";
			else if(elementType.indexOf("WinRAR")>=0)
			{
				if(elementType.indexOf("ZIP")>=0)
					imgUrl =  "images/defaultIcons/zip.png";
				else
					imgUrl =  "images/defaultIcons/rar.png";
			}
	    	
	    	CommonMessage.debugMsg(imgUrl);
	    	return imgUrl;
	    	
	    }
	    private void deleteDocTemp(HttpServletRequest request,HttpServletResponse response) throws IOException {
			PrintWriter out = response.getWriter();		
			DocTlTemplateDefDtl doctempdtl=new DocTlTemplateDefDtl();
			try {
				doctempdtl =(DocTlTemplateDefDtl)UIUtils.setBeanProperties((Object)doctempdtl,request);
				doctempdtl=docManagerService.deleteDocTemp(doctempdtl);
				
				JSONObject successData = new JSONObject();
				successData.put("msg","Deleted Successfully");
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);		
				returnData.put("displyMsg", true);
				returnData.put("tpmException",false);
				out.print(returnData.toString());
				
			}
			catch(ValidationExceptions e)
			{
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");			
				out.print(errMessage.toString());	
				CommonMessage.debugMsg("Validation"+errMessage.toString());
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Business ValidationDTL"+e.toString());
				//net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");	
				net.sf.json.JSONObject errMessage = new net.sf.json.JSONObject();
				errMessage.put("tpmException",UIUtils.validationExceptions(e.toString(),  "DocMgr"));
				out.print(errMessage.toString());	
			}
			catch(Exception e){
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());	
				CommonMessage.debugMsg("Exception"+err.toString());
			}
			
		}
		private void delDocTemp(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
			PrintWriter out = response.getWriter();		
			DocTlTemplateDefDtl newDocTlTemplateDefdtl=new DocTlTemplateDefDtl();
			DocTlTemplateDefMst newDocTlTemplateDefMst=new DocTlTemplateDefMst();
			try
			{
				newDocTlTemplateDefMst =(DocTlTemplateDefMst)UIUtils.setBeanProperties((Object)newDocTlTemplateDefMst,request);
				
					newDocTlTemplateDefMst = docManagerService.deleteDocTempData(newDocTlTemplateDefMst,newDocTlTemplateDefdtl);
					
					JSONObject successData = new JSONObject();
					successData.put("msg","Deleted Successfully");
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);		
					returnData.put("displyMsg", true);
					out.print(returnData.toString());
					
					
			}
			catch(ValidationExceptions e)
			{
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");			
				out.print(errMessage.toString());	
				CommonMessage.debugMsg("Validation"+errMessage.toString());
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Business ValidationmST"+e.toString());
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocMgr");	
				//JSONObject err = new JSONObject();
				//err.put("tpmException",errMessage.toString());
				CommonMessage.debugMsg("Business Validation"+errMessage.toString());
				out.print(errMessage.toString());	
				
			}
			catch(Exception e){
				JSONObject err = new JSONObject();
				err.put("tpmException", "This Record Can Not Be Deleted");
				out.print(err.toString());	
				CommonMessage.debugMsg("Exception"+err.toString());
			}
		}
		private void saveDocTempData(HttpServletRequest request,HttpServletResponse response) throws IOException {
			CommonMessage.debugMsg("save"); 
		    UIUtils.displayRequestParamsValue(request);
	    	HttpSession httpSession = request.getSession(false);    	
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	ServletOutputStream out = response.getOutputStream();
	    	
	    	if(httpSession != null && user != null)
	    	{
	    		String doctempgrid=request.getParameter("doctempDatas");
	        	CommonMessage.debugMsg("get grid daata"+doctempgrid);
	        	boolean create = true;
	    		DocTlTemplateDefMst newDocTlTemplateDefMst=new DocTlTemplateDefMst();
	    		newDocTlTemplateDefMst=(DocTlTemplateDefMst)UIUtils.setBeanProperties((Object)newDocTlTemplateDefMst,request);
	    		newDocTlTemplateDefMst.setDtpmCreatedby(user.getUsrm_ccno());
	    		DocTlTemplateDefMst existDocTlTemplateDefMst=new DocTlTemplateDefMst();
	    		
	    		try{
	    			if(UIUtils.isValidKeyId(doctempgrid)){
	    				
	        			JSONArray jsonArray = JSONArray.fromString(doctempgrid);
	        			DocTlTemplateDefDtl newDocTlTemplateDefdtl=new DocTlTemplateDefDtl();
	        			newDocTlTemplateDefdtl.setDtpdCreatedby(user.getUsrm_ccno());
	        			List<DocTlTemplateDefDtl> docTemp = (List<DocTlTemplateDefDtl>) UIUtils.convertJSONArrToList(newDocTlTemplateDefdtl, jsonArray);
	        		//	CommonMessage.debugMsg(" list grid data"+ docTemp.size()+"---"+jsonArray.length());
	        			if(newDocTlTemplateDefMst !=null)
	        				newDocTlTemplateDefMst.setDocTempTlDtl(docTemp);
	        			 CommonMessage.debugMsg("SIZE : "+newDocTlTemplateDefMst.getDocTempTlDtl().size());
	        		}
	    			if(create){
	    				existDocTlTemplateDefMst=docManagerService.createDocTemplate(newDocTlTemplateDefMst,existDocTlTemplateDefMst);
	    			}
	    			//else {
	    			//existDocTlTemplateDefMst=docManagerService.updateDocTemplate(newDocTlTemplateDefMst,existDocTlTemplateDefMst);
	    			//}
	    			JSONObject successData=new JSONObject();
	        		JSONObject DocTemplate=new JSONObject();
	        		String savemsg;
	        	   if( newDocTlTemplateDefMst.getDtpmKeyid()==null )
	    			{
	        		  	savemsg= " Data Not saved ";
	    			}
	    			else
	    			{
	    				savemsg= "Data saved succesfully";
	    			}
	        		successData.put("msg", savemsg);
	        		DocTemplate.put("successData", successData);
	        		DocTemplate.put("Keyid", existDocTlTemplateDefMst.getDtpmKeyid());
	        		DocTemplate.put("TYPE", existDocTlTemplateDefMst.getDtpmDocumenttype());
	        		out.print(DocTemplate.toString());
	    		}catch(ValidationExceptions e){
	    			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "DocTemplate");				
					out.print(errMessage.toString());
	    		}
	    		
	    		catch(Exception e){
	    			e.printStackTrace();
	    			JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());	
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
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
				
			return commonFilter;
		}
	    
}
