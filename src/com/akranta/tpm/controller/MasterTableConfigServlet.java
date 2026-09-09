package com.akranta.tpm.controller;
/*
 * Author:Prasanth
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.commons.io.IOUtils;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlMmcdtl;
import com.akranta.tpm.model.GenTlMmcmst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.service.MasterTableConfigService;
import com.akranta.tpm.service.impl.MasterTableConfigServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.upload.UploadException;

public class MasterTableConfigServlet extends HttpServlet {
	
	 
	/**
	 * 
	 */

	//private static final long serialVersionUID = -8070953412956046800L;
	//FunctionalLocnServices functionalLocnServices;
	private static final String AdmUploadExcelServlet_filename = "FunctionalLocnServletfilename";
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static String realPath;
	
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        boolean s = new File(realPath).mkdirs();
	}	
	private static final long serialVersionUID = 1L;
	private MasterTableConfigService masterTableConfigService = null;
	private static final String session_ident_masterTblConfColModel = "masterTblConfColModel";
	
	private static final String session_ident_menuId = "MasterTableConfigServletMenuName";
	
	public MasterTableConfigServlet(){
		
		/*try {
			
			masterTableConfigService = new MasterTableConfigServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		if(! UIUtils.checkUserSession(request, response))
			return;
		
		//HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
	
		try {
			masterTableConfigService = (MasterTableConfigServiceImpl)UIUtils.getServiceObject(request,"MasterTableConfigServiceImpl");
		
		
			if( action.equals("mmc_input.mastertblconfig")){
				mmcInputMastertbl(request,response);
				
			}
			else if( action.equals("master_getCol.mastertblconfig")){
				getMmcColModel(request,response);
			}
			/*else if( action.equals("mmc_getCol.mastertblconfig")){
				String  menuId = (String)httpSession.getAttribute("MasterTableConfigServletMenuName");
				
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MasterTableIntegrate", menuId);
				response.setContentType("text/html");
				response.setContentLength(colModel.length());
				ServletOutputStream out = response.getOutputStream();
				out.print(colModel);
				out.close();
			}*/
			else if( action.equals("master_getData.mastertblconfig")){
				
				getMmcTblData(request,response);
				
			}
			else if( action.equals("master_getExcel.mastertblconfig")){
				exportToExcel(request,response);
			}
			else if( action.equals("loadmast_input.mastertblconfig")){
				
				loadInputScreen(request,response);
			}	
			else if(action.equals("master_input.mastertblconfig")){
				loadInputScreen(request,response);
			
			}else if( action.equals("master_recall.mastertblconfig")){
				
				recallMastTblData(request,response);
				
			}else if(action.equals("master_save.mastertblconfig")){
				
				saveMasterTable(request,response);
			}
		
			else if(action.equals("master_delete.mastertblconfig")){
			
				deleteMasterTable(request,response);
			}
			else if(action.equals("fillCombo.mastertblconfig") ){
				
				loadCombobox(request,response);
			}
			else if(action.equals("file_upload.mastertblconfig")){
				
				   PrintWriter out = response.getWriter();
				   uploadFile(request,response,out);
			}
			else if(action.equals("file_save.mastertblconfig")){
				
				saveFile(request,response);
			}	
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}	
	}
	
private void mmcInputMastertbl(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		
		HttpSession httpSession = request.getSession(false);
		String menuId = request.getParameter("menuName");
		CommonMessage.debugMsg("The Selected MenuId"+menuId);
		String menuIdLink = (String)request.getAttribute("menuName");
		CommonMessage.debugMsg("The Selected menuIdLink"+menuIdLink);

		String masterForm =(String )request.getAttribute("masterForm");
		CommonMessage.debugMsg("The Selected masterForm"+masterForm);

		String menuCaption = (String) request.getAttribute("menuCaption");
		CommonMessage.debugMsg("The Selected menuCaption"+menuCaption);
		String tableName=(String)request.getParameter("tableName");
		CommonMessage.debugMsg("The Selected TableName"+tableName);
		
		GenTlMmcmst genTlMmcmst  = null;		
			
		if(UIUtils.isValidKeyId(menuIdLink))
			 menuId = menuIdLink;
		 
		//CommonMessage.debugMsg(" masterForm " + masterForm ); 
		try{
			httpSession.removeAttribute(menuId+"gridParams");
			genTlMmcmst  =(GenTlMmcmst) httpSession.getAttribute("genTlMmcmst"+menuId);
			 
			if( genTlMmcmst == null )
			{
				genTlMmcmst = masterTableConfigService.getMasterTableConfigDetails(menuId);
				httpSession.setAttribute("genTlMmcmst"+menuId,genTlMmcmst);
			}
			
			request.setAttribute(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID, menuId);
			request.setAttribute("masterIntUrl", "master_input.mastertblconfig?masterForm="+masterForm);
			request.setAttribute("masterForm", masterForm);
			request.setAttribute("tableName", tableName);			
			
			if(UIUtils.isValidKeyId(menuCaption))
				request.setAttribute("menuCaption", menuCaption);
			
			UIUtils.forwardRequest(request, response, "/pages/Gen/MasterMainTblConfig.jsp");	
		}catch(NoDataFoundException e){
			CommonMessage.debugMsg("Servlet - No Data found");
		}

	}
	
	private void getMmcColModel(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{

		String menuId = request.getParameter(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		CommonMessage.debugMsg("The GetCol Function MenuId"+menuId);
		GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
		String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename(); 
		CommonMessage.debugMsg("The GetCol Function TableName"+tableName);
		MastTblConfigTableMeta  mastTblConfigTableMeta  = getMastTblConfigTableMeta (genTlMmcmst,request, tableName);
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		CommonMessage.debugMsg("group by Field..."+genTlMmcmst.getMmcnGroupByField());
		HttpSession httpSession = request.getSession(false);
		
		JSONObject colModel	 = (JSONObject)httpSession.getAttribute(session_ident_masterTblConfColModel+menuId);
		
		if( colModel == null)
		{	
			colModel = getMMCColModel(genTlMmcmst,mastTblConfigTableMeta);
			colModel.set("tableHeight", "73%%");
			colModel.set("tableWidth", "65%%");
			//colModel.set("tableWidth", "710");
			colModel.set("enableFilter", true);
			//httpSession.setAttribute(session_ident_masterTblConfColModel+menuId, colModel);
		}
		//httpSession.removeAttribute(menuId+"gridParams");
		out.print(colModel.toString());
		out.close();
	}
	
	private void getMmcTblData(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		
		
		GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
		CommonMessage.debugMsg("The GetData Function "+genTlMmcmst);
		String menuId = request.getParameter(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		CommonMessage.debugMsg("The GetData Function MenuId"+menuId);
		/*
		String jqGridPage = request.getParameter("page");
		 
		String selectRowCount = request.getParameter("rows");
		
		int fromRow = 1,toRow =PrjConstants.JQGRID_PAGINATION_ROWCOUNT;
		if( UIUtils.isValidKeyId(jqGridPage) && UIUtils.isValidKeyId(selectRowCount) && UIUtils.isNumericString(jqGridPage)){
			toRow = Integer.parseInt(jqGridPage)*Integer.parseInt(selectRowCount);
			fromRow = toRow -  (Integer.parseInt(selectRowCount)-1);
		}
		*/
		HttpSession httpSession = request.getSession(false);
		
		//GridParams gridParams =(GridParams) httpSession.getAttribute(menuId+"gridParams");
		CommonParams commonParams = (CommonParams) httpSession.getAttribute(menuId+"commonParams");
		//CommonParams commonParams = new CommonParams(); 
		if( commonParams == null )
			commonParams = new CommonParams();
		
		//JSONObject tableModel = (JSONObject)httpSession.getAttribute(session_ident_masterTblConfColModel+menuId);
		
		BeanUtils.populate(commonParams, request.getParameterMap());
		
		FilterValues.populateGridParams(request, commonParams );
		

		if (!UIUtils.isValidKeyId(commonParams.getFlid()))
			commonParams.setFlid(CommonFunctions.getLoginFlid(request));
		
		List<String[]> mastTblData = masterTableConfigService.getMasterTableData(genTlMmcmst,  commonParams);
		
		CommonMessage.debugMsg("The List MasterTableData"+mastTblData);
		
		int totalCount = masterTableConfigService.getMasterTableCount(genTlMmcmst,commonParams) ;
		CommonMessage.debugMsg("The Total Count"+totalCount);
		
		httpSession.removeAttribute(menuId+"commonParams");
		httpSession.setAttribute(menuId+"commonParams" ,commonParams);
		
		JSONObject masterData =  UIUtils.convertListToJqGridTableObject(mastTblData, request, 0, 0, totalCount);
		
		//response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//ServletOutputStream out = response.getOutputStream();
		PrintWriter out = response.getWriter();
		out.print(masterData.toString());
		out.close();

	}
	
	private void loadInputScreen(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		HttpSession httpSession = request.getSession(false);
		GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
		
		String menuId = request.getParameter(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		CommonMessage.debugMsg("The MenuId"+menuId);
		String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename(); 
		CommonMessage.debugMsg("The TableName"+tableName);
		
		MastTblConfigTableMeta  mastTblConfigTableMeta  = getMastTblConfigTableMeta(genTlMmcmst,request, tableName);
		CommonMessage.debugMsg("MasterTblConfigTAbleData"+mastTblConfigTableMeta);
		
		String tblMasterForm = genMasterTblForm(genTlMmcmst,mastTblConfigTableMeta,menuId);
		
		CommonMessage.debugMsg("Table Master Form"+tblMasterForm);
		
		httpSession.removeAttribute("masterTableMeta"+tableName);
		httpSession.setAttribute("masterTableMeta"+tableName, mastTblConfigTableMeta);
		request.setAttribute("mode", FormModes.create);
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();

		out.print(tblMasterForm);
		out.flush();
		out.close();

	}
	private void recallMastTblData(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		HttpSession httpSession = request.getSession(false);
	//	UIUtils.displayRequestParamsValue(request);
		String keyId = request.getParameter("keyid");
		CommonMessage.debugMsg("keyid inside servlet for recall " + keyId );
		
		if(UIUtils.isValidKeyId(keyId)){
			GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
			String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename();
			MastTblConfigTableMeta  mastTblConfigTableMeta  = getMastTblConfigTableMeta (genTlMmcmst,request, tableName);
			masterTableConfigService.populateMasterTableData(genTlMmcmst, mastTblConfigTableMeta, keyId);
			JSONObject returnData = convertMastDataToJSON(mastTblConfigTableMeta,genTlMmcmst);
			httpSession.removeAttribute("masterTableMeta"+tableName);
			httpSession.setAttribute("masterTableMeta"+tableName, mastTblConfigTableMeta);
			
			response.setContentType("text/html");
			ServletOutputStream out = response.getOutputStream();
			out.print(returnData.toString());
			out.flush();
			out.close();
		}
	}
	
	private void saveMasterTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		MastTblConfigTableMeta  mastTblConfigTableMeta = null;
		String loginLocnId = CommonFunctions.getLoginLocaton(request);
		String loginelementid=CommonFunctions.getLoginElementId(request);
		String loginflid=CommonFunctions.getLoginFlid(request);
		String loginlevel=CommonFunctions.getLoginLevel(request);
		CommonMessage.debugMsg("loginelementid::::>>>>"+loginelementid);
		CommonMessage.debugMsg("loginLocnId::::>>>>"+loginLocnId);
		CommonMessage.debugMsg("loginflid::::>>>>"+loginflid);
		CommonMessage.debugMsg("loginlevel::::>>>>"+loginlevel);
		
		//String locnflid=masterTableConfigService.locnflid(loginLocnId);
		//CommonMessage.debugMsg("locnflid:::"+locnflid);
		//EntTlFacultymst entlfacultymst=new EntTlFacultymst();
		try{
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			//String loginLocnId = CommonFunctions.getLoginLocaton(request);
		//	CommonMessage.debugMsg("loginLocnId::::"+loginLocnId);
			if( user != null ){
				GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
				String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename();
				if(tableName.equals("ENT_TL_FACULTYMST"))
				{ 
					String locnflid=masterTableConfigService.locnflid(loginLocnId);
				    CommonMessage.debugMsg("locnflid:::"+locnflid);
					CommonMessage.debugMsg("INSIDE THE IF FOR CHECK ENT ENTERENCE");
					//entlfacultymst.setFtymFactKeyid(locnflid);
					//CommonMessage.debugMsg(" Getting First "+entlfacultymst.getFtymFactKeyid());
				}
				CommonMessage.debugMsg("tableName:::"+tableName);
				mastTblConfigTableMeta  = getMastTblConfigTableMeta (genTlMmcmst,request, tableName);
				CommonMessage.debugMsg("mastTblConfigTableMeta::::"+mastTblConfigTableMeta);
				populateNewValues(mastTblConfigTableMeta,request);
				mastTblConfigTableMeta.setCreatedBy(user.getUsrm_ccno());
				mastTblConfigTableMeta = masterTableConfigService.saveMasterTableData(mastTblConfigTableMeta);
				JSONObject successData = new JSONObject();
				
				String msgPropertyIdnt = "success-save";
				if( ! mastTblConfigTableMeta.isInsert()  )
					msgPropertyIdnt = "success-update";
				
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData);
			}
			
		}catch(ValidationExceptions e){
			
			JSONObject err = new JSONObject();
			err.put("exception",true);
			err.put("messages" ,e.getMessage());
			CommonMessage.debugMsg(" e " + e.getMessage());
			out.print(err);
			out.close();
		}catch(BusinessApplicationExceptions e)
		{
			String errMsg = e.getMessage();
			String colName = errMsg.substring(errMsg.indexOf('_')+1);
			if( colName.indexOf(',') > -1 ) 
				colName = colName.substring(0,colName.indexOf(',') );
			CommonMessage.debugMsg(" colName " + colName);
			List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
			
			String dispName = getDisplyName(mastTblConfigCols, colName);
			String msg =   UIUtils.getOracleConstraintMessages(errMsg);
			if( msg != null )
				msg = dispName + " " + msg; 
			else
				msg = dispName + " " + errMsg;
				
			CommonMessage.debugMsg(" e " + e.getMessage());
			out.print(msg);
			out.close();
			
		}catch(Exception e)
		{
			JSONObject err = new JSONObject();
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
			out.print(err.toString());
		}
		
	}
	
	private void uploadFile(HttpServletRequest request,
			HttpServletResponse response, PrintWriter out) throws UploadException,Exception {
		// TODO Auto-generated method stub
		InputStream is = null;
	    FileOutputStream fos = null;
	    HttpSession httpSession = request.getSession(false);
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
	        out.print("{success: false}");
	        CommonMessage.debugMsg(" : io exception :");
	    }  finally {
	        try {
	            fos.close();
	            is.close();
	            fos =null;
	            is =null;
	        } catch (IOException ignored) {
	        }
	    }
	}
	
 private void saveFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	 
	 PrintWriter out = null;
	 HttpSession httpSession = request.getSession(false);   
	 String file;
	 String excelFileName1= (String)httpSession.getAttribute(AdmUploadExcelServlet_filename);
	 String xlFile=excelFileName1;
	 String excelFileName=(realPath+""+excelFileName1);
	try{
		AdmTlUsermst userid = UIUtils.getLoginUser(request);
		String loginId=userid.getUsrm_ccno();
		CommonMessage.debugMsg(excelFileName);
		String savemsg;
		  
		 GenTlMmcmst genTlMmcmst=null;		
         genTlMmcmst =getGenTlMmcmst(request);
		 String tableName =genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename();
		 masterTableConfigService.uploadmasterexcel(excelFileName,tableName,loginId);
		 savemsg="File Uploaded succesfully";
		JSONObject successData=new JSONObject();
		JSONObject returnData=new JSONObject();
    	successData.put("msg", savemsg);
        returnData.put("formClear",false);
    	returnData.put("successData", successData);
    	out=response.getWriter();
		out.print(returnData.toString());
	}catch(BusinessApplicationExceptions e)
	{
		String msg="";
		if(e.toString().equals("EXISTS"))
			msg="Data Exists";
		out=response.getWriter();
		out.print(msg);
		
	}
	catch (UploadException e) {
		
		if( e.getErrorNumber() == -2 || e.getErrorNumber() == -3 ){ 
        	CommonMessage.debugMsg(" xlFile " + xlFile);
        	ExcelUtils.writeToResponse(response, e.getUploadExcel(), xlFile.substring(0, xlFile.lastIndexOf(".")), xlFile.substring(xlFile.lastIndexOf(".")+1));
			// TODO Auto-generated catch block
		}else{
			response.getWriter().print("Data Not Uploaded " + e.getMessage());
		}
		e.printStackTrace();
	}
	catch(Exception e){
		CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
		JSONObject err = new JSONObject();
		String msg="File Not Uploaded";
		if(e.toString().contains("UK_EXCELDATA"))
			msg="Data Already Exists";
		else if(e.toString().contains("non-numeric character was found where a numeric was expected"))
			msg="Unsupported Date Format";
		err.put("tpmException",msg);
		out=response.getWriter();
		out.print(err.toString());
		e.printStackTrace();
	}
}

	
	private void deleteMasterTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		try{
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			if( user != null ){
				GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
				String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename();
				MastTblConfigTableMeta  mastTblConfigTableMeta  = getMastTblConfigTableMeta (genTlMmcmst,request, tableName);
				populateNewValues(mastTblConfigTableMeta,request);
				mastTblConfigTableMeta.setCreatedBy(user.getUsrm_ccno());
				mastTblConfigTableMeta = masterTableConfigService.deleteMasterTableData(mastTblConfigTableMeta);
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt = "success-delete";
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData);
				out.close();
			}
			
		}catch(ValidationExceptions e){
			
			JSONObject err = new JSONObject();
			err.put("tpmException",e.getMessage());
			//CommonMessage.debugMsg(" e " + e.getMessage());
			out.print(err);
			out.close();
		}catch(BusinessApplicationExceptions e)
		{
			//CommonMessage.debugMsg(" e " + e.getMessage());
			String msg =e!= null ? e.getMessage() : null;
			if(msg!= null && msg.contains("ORA-02292") && msg.contains("FK_") )
			{	
				//msg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm");
				msg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
				JSONObject err = new JSONObject();
				
				
				JSONObject confirm = new JSONObject();
				confirm.put("confirm",msg);
				err.put("tpmException",confirm);
				err.put("displyMsg",false);
				out.print(err);
			}
			else{
				out.print(e.getMessage());
			
			}
			out.close();
		}catch(Exception e)
		{
			JSONObject err = new JSONObject();
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
			out.print(err.toString());
		}

	}
	
	private void loadCombobox(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception{ //T

		ComboFilter comboFilter =UIUtils.fillComboFilter(request);
		GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
		String colName = request.getParameter("controlId");
		String value =  request.getParameter("");
		ComboFilter mastTblCombo = getComboDetails(genTlMmcmst,colName,value,comboFilter);
		
		List<ComboBox> comboList = masterTableConfigService.getMasterTblComboList(mastTblCombo) ;
		
		UIUtils.writeComboBox(response,comboList,comboFilter);

	}
	private MastTblConfigTableMeta getMastTblConfigTableMeta(GenTlMmcmst genTlMmcmst, HttpServletRequest request, String tableName) throws NoDataFoundException, Exception
	{
		CommonMessage.debugMsg("Inside the MastTblConfigTableMeta Function");
		CommonMessage.debugMsg("print table name " + tableName);
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg("Inside the MastTblConfigTableMeta Function 2222");
		CommonMessage.debugMsg(tableName);
		MastTblConfigTableMeta  mastTblConfigTableMeta = (MastTblConfigTableMeta)httpSession.getAttribute("masterTableMeta"+tableName);
		CommonMessage.debugMsg("printing the mast table daat a  "  + mastTblConfigTableMeta);
		//List<String[]>tableContent=masterTableConfigService.tableMasterData(tableName);
		//CommonMessage.debugMsg("The TableContent"+tableContent);
		if( mastTblConfigTableMeta == null){
			mastTblConfigTableMeta = masterTableConfigService.getMasterTableMeta(tableName);
			mastTblConfigTableMeta.setTableName(tableName);
			setControlIds(genTlMmcmst,mastTblConfigTableMeta);
			CommonMessage.debugMsg(" tableName " + tableName); 
			httpSession.setAttribute("masterTableMeta"+tableName, mastTblConfigTableMeta);
		}
		return mastTblConfigTableMeta;
	}
	
	private GenTlMmcmst getGenTlMmcmst(HttpServletRequest request) throws NoDataFoundException, SQLException, Exception{
		
		HttpSession httpSession = request.getSession(false);
		String menuId = request.getParameter(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		CommonMessage.debugMsg("The MenuID DaoImpl Data " + ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		GenTlMmcmst genTlMmcmst = null;
//		GenTlMmcmst genTlMmcmst  =(GenTlMmcmst) httpSession.getAttribute("genTlMmcmst"+menuId);
		
		if( genTlMmcmst == null ){			
			genTlMmcmst = masterTableConfigService.getMasterTableConfigDetails(menuId);
			httpSession.setAttribute("genTlMmcmst"+menuId,genTlMmcmst);
		}
		return genTlMmcmst;
	}
	private JSONObject getMMCColModel(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta){
		
		List<GenTlMmcdtl> genTlMmcdtlList =  genTlMmcmst.getGenTlMmcdtl();
		
		List<String> header = new ArrayList<String>();
		
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		
		//jqGridTableModel.setTableWidth(700);
		//jqGridTableModel.setTableHeight(370);
		jqGridTableModel.setTableButton(true);
		//JqGridColModel slnNoModel = new  JqGridColModel();
		
		//slnNoModel.setName("SlNo");
		//slnNoModel.setIndex("SlNo");
		//slnNoModel.setWidth(40);
		
		//CommonMessage.debugMsg("genTlMmcmst.getMmcnFormname() " + genTlMmcmst.getMmcnFormname());
		jqGridTableModel.setTableCaption(genTlMmcmst.getMmcnFormname());
		if( UIUtils.isValidKeyId(genTlMmcmst.getMmcnGroupByField() )){
			CommonMessage.debugMsg("Grooup By Field....."+genTlMmcmst.getMmcnGroupByField());
			jqGridTableModel.setGroupBy(true);
			jqGridTableModel.setGroupByField(genTlMmcmst.getMmcnGroupByField().replace("_", "").toLowerCase());
		}
		//jqGridTableModel.getColModel().add(slnNoModel);
		jqGridTableModel.setRowNumbers(true);
		//header.add("SLNo");
		int colwidth, type;
		for( GenTlMmcdtl genTlMmcdtl : genTlMmcdtlList ){
			if( ! genTlMmcdtl.getMscnColumndisplayorder().equals("-1") && genTlMmcdtl.getMscnColumntobedisplayed().equals("H") )
				continue;
				
			header.add(genTlMmcdtl.getMscnDisplayname());
			String colName = genTlMmcdtl.getMscnColumnname().replace("_", "").toLowerCase();
			
			JqGridColModel jqGridColModel = new  JqGridColModel();
			jqGridColModel.setName(colName);
			jqGridColModel.setIndex(colName);
			
			if( genTlMmcdtl.getMscnColumndisplayorder().equals("-1") )
				jqGridColModel.setKey(true);
				
			colwidth = getFieldSize(mastTblConfigTableMeta,genTlMmcdtl.getMscnColumnname());
			if( colwidth < 50 && genTlMmcdtl.getMscnSelectiontype().endsWith("P"))
				colwidth = 250;
			else if( colwidth > 200 )
				colwidth = 200;
			else if ( colwidth < 12)
				colwidth = 150;
			else if ( colwidth < 30)
				colwidth = 150;
			else
				colwidth = 220;
			
			if(genTlMmcdtl.getMscnSelectiontype().endsWith("F"))
				colwidth = 450;
			jqGridColModel.setWidth(colwidth);
			
			type = getFieldType(mastTblConfigTableMeta,genTlMmcdtl.getMscnColumnname());
			jqGridColModel.setAlign(getAlignment(type));

			if( genTlMmcdtl.getMscnColumntobedisplayed().equals("H"))
				jqGridColModel.setHidden(true);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		//CommonMessage.debugMsg(" header " + header.size());
		String [] colHeaders  = new String[ header.size() ];
		int i=0;
		for(String str : header )
			colHeaders[ i++ ] 	=  str;
		
		jqGridTableModel.getRowHeaders().add(colHeaders);
		return UIUtils.getJqGridTableModel(jqGridTableModel);
		
	}
	
	
	private JSONObject convertMastDataToJSON(MastTblConfigTableMeta mastTblConfigTableMeta,GenTlMmcmst genTlMmcmst){
		JSONArray returnDataArr = new JSONArray();
		List<GenTlMmcdtl> genTlMmcdtls = genTlMmcmst.getGenTlMmcdtl();
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		String fieldValue=null, fieldId = null;
		for( GenTlMmcdtl genTlMmcdtl : genTlMmcdtls){
			
			if( ! genTlMmcdtl.getMscnColumndisplayorder().equals("-1") && genTlMmcdtl.getMscnColumntobedisplayed().equals("H") )
				continue;
			
			JSONObject field = new JSONObject();
			
			fieldId = getFieldId(mastTblConfigCols, genTlMmcdtl.getMscnColumnname());
			fieldValue = getFieldValue(mastTblConfigCols, genTlMmcdtl.getMscnColumnname());
			
			if( "D".equals(genTlMmcdtl.getMscnSelectiontype() ) && fieldValue != null && fieldValue.contains(Constants.passNullDate) )
			{
				fieldValue ="";
			}
			//else if( "F".equals(genTlMmcdtl.getMscnSelectiontype() )){
				
			//}
			field.put(fieldId, fieldValue);
			field.put("type", genTlMmcdtl.getMscnSelectiontype());
			
			returnDataArr.put(field);
		}
		JSONObject returnData = new JSONObject(); 
		returnData.put("mastTblValues", returnDataArr);
		return returnData;
	}
	
	private String getFieldValue(List<MastTblConfigColMeta> mastTblConfigCols,String colName){

		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName.toLowerCase())){
				return mastTblConfigColMeta.getValue();
			}
		}
		
		return null;
	}

	private String getFieldId(List<MastTblConfigColMeta> mastTblConfigCols,String colName){
		CommonMessage.debugMsg("controlId ColumnName :"+colName);
		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			CommonMessage.debugMsg("mastTblConfigColMeta ColumnName :"+mastTblConfigColMeta.getColName());
			if( mastTblConfigColMeta.getColName().equals(colName.toLowerCase())){
				CommonMessage.debugMsg("mastTblConfigColMeta Column FieldId :"+mastTblConfigColMeta.getFieldId());
				return mastTblConfigColMeta.getFieldId();
			}
		}
		
		return null;
	}

	private void setMandatory(List<MastTblConfigColMeta> mastTblConfigCols,String colName){
		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName)){
				mastTblConfigColMeta.setMandatory(true);
				break ;
			}
		}
	}

	private void setDisplyName(List<MastTblConfigColMeta> mastTblConfigCols,String colName,String displayName){

		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName)){
				mastTblConfigColMeta.setDisplayName(displayName);
				break ;
			}
		}
	}

	private String getDisplyName(List<MastTblConfigColMeta> mastTblConfigCols,String colName){

		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName)){
				if( mastTblConfigColMeta.getColName().equals(colName))
					return mastTblConfigColMeta.getDisplayName();
			}
		}
		
		return null;
	}

	
	private int getFieldSize(MastTblConfigTableMeta mastTblConfigTableMeta,String colName){
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName.toLowerCase())){
				return mastTblConfigColMeta.getSize();
			}
		}
		
		return 0;
	}
	
	private int getFieldType(MastTblConfigTableMeta mastTblConfigTableMeta,String colName){
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			if( mastTblConfigColMeta.getColName().equals(colName.toLowerCase())){
				return mastTblConfigColMeta.getType();
			}
		}
		
		return 0;
	}
		
	private void setControlIds(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta){
		List<GenTlMmcdtl> genTlMmcdtlList = genTlMmcmst.getGenTlMmcdtl();
		String controlId = null;
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		for( GenTlMmcdtl genTlMmcdtl:genTlMmcdtlList){
			if( ! genTlMmcdtl.getMscnColumndisplayorder().equals("-1") && genTlMmcdtl.getMscnColumntobedisplayed().equals("H")  )
				continue;
			
			String colName = genTlMmcdtl.getMscnColumnname().replace("_", "").toLowerCase();
			
			if( genTlMmcdtl.getMscnColumndisplayorder().equals("-1")){
				controlId ="txt"+colName;
			}
			else if( genTlMmcdtl.getMscnSelectiontype().equals("P") || genTlMmcdtl.getMscnSelectiontype().equals("S") ){
				controlId="cmb"+colName;
			}
			else if( genTlMmcdtl.getMscnSelectiontype().equals("F")  ){
				controlId="hdn"+colName;
			}
			else if(genTlMmcdtl.getMscnIscomboselection().equals("Y") ){
				controlId = "comb"+colName;
			}	
			else if(genTlMmcdtl.getMscnSelectiontype().equals("D")  ){
				controlId ="dte"+colName;
			}
			else if(genTlMmcdtl.getMscnSelectiontype().equals("C")  ){
				controlId ="chk"+colName;
			}
			else {
				controlId ="txt"+colName;
			}
			CommonMessage.debugMsg("Controllid :"+controlId);
			setControlId(mastTblConfigCols,genTlMmcdtl.getMscnColumnname(),controlId);
		}	
		mastTblConfigTableMeta.setMastTblConfigCols(mastTblConfigCols);
	}
	
	private void setControlId(List<MastTblConfigColMeta> mastTblConfigCols,String colName, String controlId){
		CommonMessage.debugMsg("colName controllid :"+colName);
		for(int i=0;i< mastTblConfigCols.size();i++ ){
			if(mastTblConfigCols.get(i).getColName().equals(colName.toLowerCase())){
				CommonMessage.debugMsg("colName mastTblConfigCols :"+mastTblConfigCols.get(i).getColName());
				mastTblConfigCols.get(i).setFieldId(controlId);
				break;
			}
		}
	}
	private void setDefaultValue(List<MastTblConfigColMeta> mastTblConfigCols,String colName, String defaultValue){
		for(int i=0;i< mastTblConfigCols.size();i++ ){
			if(mastTblConfigCols.get(i).getColName().equals(colName.toLowerCase())){
				mastTblConfigCols.get(i).setDefaultValue(defaultValue);
				break;
			}
		}
	}
	private void setPrimaryKeyColumn(List<MastTblConfigColMeta> mastTblConfigCols,String colName){
		
		for(int i=0;i< mastTblConfigCols.size();i++ ){
			if(mastTblConfigCols.get(i).getColName().equals(colName.toLowerCase())){
				mastTblConfigCols.get(i).setPrimaryKey(true);
				break;
			}
		}
	}
	// --- Commented By Vignesh 17Oct2025 -----------//
//	private void setPrimaryKeyColumn(List<MastTblConfigColMeta> mastTblConfigCols, String colName) {
//	    // handle null safely and compare case-insensitively
//	    if (colName == null) return;
//	    for (int i = 0; i < mastTblConfigCols.size(); i++) {
//	        String metaCol = mastTblConfigCols.get(i).getColName();
//	        if (metaCol != null && metaCol.equalsIgnoreCase(colName)) {
//	            mastTblConfigCols.get(i).setPrimaryKey(true);
//	            break;
//	        }
//	    }
//	}

	private String getAlignment(int type){
		switch(type)
		{
			case Types.INTEGER:
							return "right";
			case Types.CHAR :
			case Types.DATE :	
							return "center";
			default :
					return "left";
		}
	}
	private String genMasterTblForm(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta, String menuId){
		CommonMessage.debugMsg("genMasterTblForm");
		List<GenTlMmcdtl> genTlMmcdtlList =  genTlMmcmst.getGenTlMmcdtl();
		StringBuffer form = new StringBuffer();
		StringBuffer onReady = new StringBuffer();
		StringBuffer script = new StringBuffer();
		
		String elementHtml ="";
		String colName ="";
		String label ="";
		if(UIUtils.isValidKeyId(genTlMmcmst.getMmcnRemarks())&&genTlMmcmst.getMmcnRemarks().endsWith(".js")){
			onReady.append(" <script type='text/javascript' src='js/"+genTlMmcmst.getMmcnRemarks()+"'  ></script>");
		}
		onReady.append("<script type='text/javascript'>");
		onReady.append(" jQuery(document).ready(function(){");
		onReady.append(" initialiseForm('frmMasterTbl');");
		onReady.append(" initialiseDateFields('frmMasterTbl');");
		onReady.append(" jQuery('#submitForm').val('frmMasterTbl');");
		onReady.append(" jQuery('#frmMasterTbl .easyui-text').css('text-transform', 'uppercase');");
		onReady.append(" jQuery('#frmMasterTbl textarea').css('text-transform', 'uppercase'); ");
		
		String controlId=null;
		String actualColName = null , selectionType = null;
		form.append("<div class='sub-header' style='width:345px;margin-top:-35px;padding-bottom:0px;' >" +genTlMmcmst.getMmcnFormname() + "      </div>" );
	
		for( GenTlMmcdtl genTlMmcdtl : genTlMmcdtlList ){
			
			actualColName = genTlMmcdtl.getMscnColumnname();
			
			if( genTlMmcdtl.getMscnIsmandatory().equals("N"))
				setDefaultValue(mastTblConfigTableMeta.getMastTblConfigCols(),actualColName,genTlMmcdtl.getMscnNonmandatoryvalue());
			
			if( genTlMmcdtl.getMscnColumndisplayorder().equals("-1"))
				setPrimaryKeyColumn(mastTblConfigTableMeta.getMastTblConfigCols(), actualColName);
			
			if( ! genTlMmcdtl.getMscnColumndisplayorder().equals("-1") && genTlMmcdtl.getMscnColumntobedisplayed().equals("H") )
				continue;
			
			selectionType = genTlMmcdtl.getMscnSelectiontype();
			
			controlId = getFieldId(mastTblConfigTableMeta.getMastTblConfigCols(), actualColName) ;
			CommonMessage.debugMsg(genTlMmcdtl.getMscnColumndisplayorder() +"    controlId :"+controlId);
			if(genTlMmcdtl.getMscnColumndisplayorder().equals("-1")){
				elementHtml = UIUtils.getHiddenTextHtml(controlId);
				form.append(elementHtml);
				continue;
			}
			else if(  selectionType.equals("P") || selectionType.equals("S") ){
				
				elementHtml = UIUtils.getEasyUIComboboxHtml(controlId,selectionType.equals("P"));
				colName = actualColName.replace("_", "").toLowerCase();
				script.append("fillComboBox('frmMasterTbl','"+ controlId +"','fillCombo.mastertblconfig?controlId="+colName+"&"+ ReqtParamNameConst.GEN_TBL_CONFIG_MENUID +"="+ menuId+"');");
			}	
			else if(  selectionType.equals("F")){
				CommonMessage.debugMsg("controlId  "+controlId);
				elementHtml = UIUtils.getFunctionalLocation(controlId);
				//form.append(elementHtml);
				//CommonMessage.debugMsg("elementHtml  "+elementHtml);
				colName = actualColName.replace("_", "").toLowerCase();
				onReady.append(" var flid = jQuery(\"#frmMasterTbl input[id='flid']\").val();");
				onReady.append(" var dataStr = '&flid='+flid;");
				onReady.append(" loadFunctionalLocation('"+controlId+"funLocation','functionalLoc.commonFilter','"+controlId+"funLocation','frmMasterTbl',dataStr);");
			}	
			else if(genTlMmcdtl.getMscnIscomboselection().equals("Y") ){
				elementHtml = UIUtils.getSelectHtml(controlId, genTlMmcdtl.getMscnCombodisplayname(), genTlMmcdtl.getMscnCombosaveinfo());
			}	
			else if(selectionType.equals("D")  ){
				elementHtml = UIUtils.getEasyUIDateBoxHtml(controlId);
			}	
			else {
				int coltype = getFieldType(mastTblConfigTableMeta,actualColName );
				
				CommonMessage.debugMsg(  actualColName + " = " + coltype);
				int colwidth = getFieldSize(mastTblConfigTableMeta,actualColName);
				
				if( colwidth < 200 ){
					elementHtml = UIUtils.getEasyUITextBoxHtml(controlId, colwidth,false) ;
					if((coltype==Types.INTEGER ) || (coltype==Types.NUMERIC))
						onReady.append("numericTextBox('"+ controlId +"');");
				}	
				else 
					elementHtml = UIUtils.getEasyUITextAreaHtml(controlId, colwidth-4) ;
			}
			if( genTlMmcdtl.getMscnIsmandatory().equals("Y") )
				setMandatory(mastTblConfigTableMeta.getMastTblConfigCols(), actualColName);
			
			setDisplyName(mastTblConfigTableMeta.getMastTblConfigCols(),actualColName,genTlMmcdtl.getMscnDisplayname());
			 if(!selectionType.equals("F")){
				label = UIUtils.getEasyUILabelHtml(genTlMmcdtl.getMscnDisplayname(), genTlMmcdtl.getMscnIsmandatory().equals("Y")  );
				form.append("<div>");
				form.append(label);
				form.append("</div>");
			 }
				form.append("<div class='easyui-paddingbfpx'>");
				form.append(elementHtml);
				form.append("</div>");
			
			}
		onReady.append(" });");
		onReady.append(script);
		onReady.append("</script> ");
		onReady.append(form);
		//if(  selectionType.equals("F"))
		//	CommonMessage.debugMsg("FUNC SCRIPT  "+script.toString());
		//else
			CommonMessage.debugMsg( onReady.toString());
		return onReady.toString();
	}
	
		
	private ComboFilter getComboDetails(GenTlMmcmst genTlMmcmst,String comboName, String valueEnterd,ComboFilter comboFilter ){
		
		List<GenTlMmcdtl> genTlMmcdtlList = genTlMmcmst.getGenTlMmcdtl();
		//ComboFilter comboFilter = new ComboFilter();
		String colName = null;
		for( GenTlMmcdtl genTlMmcdtl : genTlMmcdtlList ){
			colName = genTlMmcdtl.getMscnColumnname().replace("_", "").toLowerCase();
			if( ! colName.equals(comboName))
				continue;
			if(  genTlMmcdtl.getMscnColumndisplayorder().equals("-1") ){
				comboFilter.setNameField(genTlMmcdtl.getMscnColumnname());
				comboFilter.setIdField(genTlMmcdtl.getMscnColumnname());
				comboFilter.setTableName(genTlMmcdtl.getMscnTablename());
			}
			else if( genTlMmcdtl.getMscnSelectiontype().equals("P") ){ 
				comboFilter.setNameField(genTlMmcdtl.getMscnPopupcolumnname());
				comboFilter.setIdField(genTlMmcdtl.getMscnPopuptablekeyid());
				comboFilter.setTableName(genTlMmcdtl.getMscnPopuptablename());
				if( UIUtils.isValidKeyId(genTlMmcdtl.getMscnConditionfield()) && genTlMmcdtl.getMscnConditionfield().trim().startsWith("AND"))
					comboFilter.setCondSql(genTlMmcdtl.getMscnConditionfield());
				
			}
			else if( genTlMmcdtl.getMscnSelectiontype().equals("S") ){ 
				comboFilter.setNameField(genTlMmcdtl.getMscnColumnname());
				comboFilter.setIdField(genTlMmcdtl.getMscnColumnname());
				comboFilter.setTableName(genTlMmcdtl.getMscnTablename());
			}
			break;
		}	
		
		return comboFilter;
	}

	private void populateNewValues(MastTblConfigTableMeta mastTblConfigTableMeta, HttpServletRequest request){
		Enumeration<String> paramNames = request.getParameterNames(); 
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		String fieldId  = null;

		for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
			mastTblConfigColMeta.setNewValue(null);
		}
		while(paramNames.hasMoreElements() ){
			fieldId = paramNames.nextElement();
			
			for(MastTblConfigColMeta mastTblConfigColMeta : mastTblConfigCols ){
					
				if( fieldId.equals(mastTblConfigColMeta.getFieldId())){
					String value = request.getParameter(fieldId);
					if( value != null )
					{
						value = value.trim();
						value = value.toUpperCase();
					}
		//			CommonMessage.debugMsg("value" + value);
					mastTblConfigColMeta.setNewValue(value);
					break;
				}
			}
		}
		
		mastTblConfigTableMeta.setMastTblConfigCols(mastTblConfigCols);
	}
	
	private void exportToExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		GenTlMmcmst genTlMmcmst = getGenTlMmcmst(request);
		
		HttpSession httpSession = request.getSession(false);
		String menuId = request.getParameter(ReqtParamNameConst.GEN_TBL_CONFIG_MENUID);
		//CommonMessage.debugMsg( " menuId " + menuId );
		//JSONObject colModel =(JSONObject)  httpSession.getAttribute(session_ident_masterTblConfColModel+menuId)
		JSONObject colModel = UIUtils.getXlColModel(request, response);
		colModel.put("title", genTlMmcmst.getMmcnFormname());
		String format = ExcelUtils.getFormat(request);

		 GridParams gridParams =new GridParams();
		 httpSession.getAttribute(menuId+"gridParams");
		CommonMessage.debugMsg( " gridParams " + gridParams);
		Workbook wb = masterTableConfigService.getMasrerTblMasterExcel(genTlMmcmst, colModel, format,gridParams);
		
		ExcelUtils.writeToResponse(response, wb, genTlMmcmst.getMmcnFormname(), format);
	}
	
	
/*	private StringBuffer getJavascriptTofillCombo(String id){
		StringBuffer script = new StringBuffer();
		
		
	}
*/	

}
