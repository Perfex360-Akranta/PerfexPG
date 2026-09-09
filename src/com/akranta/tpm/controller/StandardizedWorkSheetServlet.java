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

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
//import com.akranta.tpm.bean.EntTlAssessmentmstBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.SopBean;
import com.akranta.tpm.bean.StdWorSheetkBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.EntTlAssessmentdtl;
//import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.GridColModel;
//import com.akranta.tpm.model.QtmTlSopmst;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.StdTlStdworksheetmst;
//import com.akranta.tpm.model.VocTlChecklistmst;
import com.akranta.tpm.service.StandardizedWorkSheetService;
import com.akranta.tpm.service.impl.StandardizedWorkSheetServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.WorksheetServiceApi;
public class StandardizedWorkSheetServlet extends HttpServlet {


	/**
	 * 
	 */
	StandardizedWorkSheetService standardizedWorkSheetService;
	WorksheetServiceApi worksheetserviceapi; 
	public StandardizedWorkSheetServlet()
	{
		super();	       
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
	    response.setContentType("text/html");
		response.setContentType("text/json");
		try {
			standardizedWorkSheetService = (StandardizedWorkSheetServiceImpl)UIUtils.getServiceObject(request,"StandardizedWorkSheetServiceImpl");
			
			standardizedWorkSheetService.StandardizedWorkSheetServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
	if(action.equals("STDWorkSheet_view.stdwosh"))
		{
           String mainForm=request.getParameter("mainForm");
			if("true".equals(mainForm)){
				request.setAttribute("mainForm", mainForm);
}
            CommonMessage.debugMsg("STDWorkSheet_view.stdwosh");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/StdMasterGrid.jsp");
			rd.forward(request, response); 
		}
	 else if(action.equals("getModeSTDWorkSheet_view.stdwosh")){
			 CommonMessage.debugMsg("getModeSTDWorkSheet_View.stdwosh");
		         response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				JSONObject result = new JSONObject();
				result.put("mode","create");
				result.put("url","STDWorkSheetMain_input.stdwosh");
				result.put("formHeader","Std Work Sheet");
				out.println(result);
			}
			/*
			 * else if (action.equals("STDWorkSheetMain_input.stdwosh")) { String keyid
			 * =request.getParameter("keyId"); StdTlStdworksheetmst
			 * newStdTlStdworksheetmst=new StdTlStdworksheetmst();//model AdmTlUsermst user
			 * = UIUtils.getLoginUser(request); if(!UIUtils.isValidKeyId(keyid)){
			 * newStdTlStdworksheetmst.setStwsBy(user.getUsrm_ccno());
			 * newStdTlStdworksheetmst.setStwsApprovedby(user.getUsrm_ccno()); }
			 * if(UIUtils.isValidKeyId(keyid)){
			 * 
			 * newStdTlStdworksheetmst = standardizedWorkSheetService.selectmaster(keyid);
			 * httpSession.setAttribute("newStdTlStdworksheetmst", newStdTlStdworksheetmst);
			 * } request.setAttribute("newStdTlStdworksheetmst" ,newStdTlStdworksheetmst);
			 * UIUtils.forwardRequest(request, response, "/pages/StdWorkSheetEntry.jsp"); }
			 */
	 else if (action.equals("STDWorkSheetMain_input.stdwosh")) {
		    String keyid = request.getParameter("keyId");
		    String createdByParam = request.getParameter("createdBy");
		    
		    StdTlStdworksheetmst newStdTlStdworksheetmst = new StdTlStdworksheetmst();
		    AdmTlUsermst user = UIUtils.getLoginUser(request);

		    // Set current user
		    request.setAttribute("currentUserCcno", user.getUsrm_ccno());

		    if(!UIUtils.isValidKeyId(keyid)){
		        newStdTlStdworksheetmst.setStwsBy(user.getUsrm_ccno());
		        newStdTlStdworksheetmst.setStwsApprovedby(user.getUsrm_ccno());
		    }
		    
		    
		    

		    if(UIUtils.isValidKeyId(keyid)){
		    	

		        newStdTlStdworksheetmst = standardizedWorkSheetService.selectmaster(keyid);
		        
		        String date = newStdTlStdworksheetmst.getStwsDate();
			    CommonMessage.debugMsg(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date)+" Changed Date");
		 		
			    newStdTlStdworksheetmst.setStwsDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
		        httpSession.setAttribute("newStdTlStdworksheetmst", newStdTlStdworksheetmst);
		        
		        // If createdBy wasn't passed in URL, get it from DB
		        if(createdByParam == null || createdByParam.isEmpty()) {
		            createdByParam = newStdTlStdworksheetmst.getStwsCreatedby();
		        }
		    }
		    
		    // Set createdBy for easy access
		    request.setAttribute("recordCreatedBy", createdByParam);
		    request.setAttribute("newStdTlStdworksheetmst", newStdTlStdworksheetmst);
		    UIUtils.forwardRequest(request, response, "/pages/StdWorkSheetEntry.jsp");
		}
	 else if(action.equals("STDWorkSheetMain_recall.stdwosh")){
			
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			List<String []> kaizenData  = standardizedWorkSheetService.FillControlData(keyid);
			out.print( JSONArray.fromCollection(kaizenData));	
		}
	else if (action.equals("StdWork_remove.stdwosh")) 
        {
			DeleteStdRow(request, response);
		}
	  else if (action.equals("STDWorkSheetMain_save.stdwosh")) 
		{
			StdWorSheetkBean stdWorSheetkBean = new StdWorSheetkBean();
			saveWorkSheet(request,response,stdWorSheetkBean);
		}
	   else if( action.equals("STDWorkSheet_getCol.stdwosh") )    //master grid
		{
			List<String[]> STDWorkSheet = null;
			PrintWriter out = response.getWriter();
		try {                                                              
			    CommonFilter commonFilter = populateCommonFilter(request,"WorkSheetCommonFilter",true);
			    STDWorkSheet = standardizedWorkSheetService.getAllMaster(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setGridEdit(false);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = STDWorkSheet.get(1);
				String [] colHeaderHead = STDWorkSheet.get(0);
				String [] colHeaderHead1 = STDWorkSheet.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				headers.add(colHeaderHead1);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "110%%");
				colmodel.set("tableHeight", "75%%");
				httpSession.removeAttribute("StndWrkshtColModel");
				httpSession.setAttribute("StndWrkshtColModel", colmodel);
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}			
}
		
      else if( action.equals("STDWorkSheet_getData.stdwosh") )
     {
	  try
		{
			CommonFilter commonFilter = populateCommonFilter(request,"WorkSheetCommonFilter",false);
			List<String[]> STDWorkSheet =  standardizedWorkSheetService.getAllMaster(commonFilter);
			String flid=request.getParameter("flid");
	        CommonMessage.debugMsg(" Inside Get Data :: flid :: "+flid);
			PrintWriter out = response.getWriter();
			JSONObject fishGrid=UIUtils.convertToJqGridTableObject(STDWorkSheet, request,2, 0,commonFilter.getTotalRecordCnt());
			out.println(fishGrid);  			 	
		 	commonFilter.setViewClick('N');  
		    commonFilter.setFlid(flid);
		    String loginFlid = (String) httpSession.getAttribute("loginFlid");
			if (!UIUtils.isValidKeyId(flid) )
				commonFilter.setFlid(loginFlid);
		 	httpSession.removeAttribute("WorkSheetCommonFilter");
		 	httpSession.setAttribute("WorkSheetCommonFilter", commonFilter);
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
}
		
      else if (action.equals("STDWorkSheet_getExcel.stdwosh")) {
			
  	    httpSession = request.getSession(false);
  		CommonFilter commonFilter = populateCommonFilter(request,"WorkSheetCommonFilter",false);
  		String flid=request.getParameter("flid");
  		CommonMessage.debugMsg("flid===="+flid);
  		commonFilter.setFlid(flid);
  		String loginFlid = (String) httpSession.getAttribute("loginFlid");
  		
  		if (!UIUtils.isValidKeyId(flid) )
  			commonFilter.setFlid(loginFlid);
  		
  		String tmpFromRow = commonFilter.getFromRow();
  		commonFilter.setFromRow(null);//
  		CommonMessage.debugMsg("excellll  :: "+tmpFromRow);
  		JSONObject colmodel =(JSONObject) httpSession.getAttribute("StndWrkshtColModel");
  	    //JSONObject colmodel = UIUtils.getXlColModel( request, response);
  		CommonMessage.debugMsg("excellll888");
  		
  		colmodel.put("title","StdWorkSheet Report");
          String format = ExcelUtils.getFormat(request);				
  		Workbook wb = standardizedWorkSheetService.getStdWorkExcel(colmodel,format,commonFilter);
  		CommonMessage.debugMsg("excellll0000");
  		commonFilter.setFromRow(tmpFromRow);
  		CommonMessage.debugMsg("exce1234 :: "+commonFilter.getFromRow());
  		ExcelUtils.writeToResponse(response, wb, "StdWorkSheet", format);  		
  }		
	/*	else if( action.equals("STDWorkSheetMain_getCol.stdwosh") )
		{
			
			List<String[]> STDWorkSheetList = null;
			PrintWriter out = response.getWriter();
			try {
				String keyId=request.getParameter("keyid");
			    CommonFilter commonFilter = populateCommonFilter(request,"StdWorkSheetCommonFilter",true);
			    commonFilter.setKey(keyId);
			    STDWorkSheetList = standardizedWorkSheetService.getAllworkshtdtl(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = STDWorkSheetList.get(2);
				//String [] colHeaderHead = STDWorkSheetList.get(1);
				String [] colHeaderHead = STDWorkSheetList.get(1);
				String [] colHeaderHead1 = STDWorkSheetList.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				headers.add(colHeaderHead1);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "110%%");
				colmodel.set("tableHeight", "40%%");
				httpSession.removeAttribute("ColModel");
				httpSession.setAttribute("ColModel", colmodel);
				out.println(colmodel); 
			} catch (Exception e) {
				e.printStackTrace();
			}			
}
			
		else if( action.equals("STDWorkSheetMain_getData.stdwosh") )
		{
			
			try
			{
			  String keyId=request.getParameter("keyid");
			  CommonFilter commonFilter = populateCommonFilter(request,"StdWorkSheetCommonFilter",false);
			  List<String[]> STDWorkSheetList =  standardizedWorkSheetService.getAllworkshtdtl(commonFilter);
			  commonFilter.setKey(keyId);
			  PrintWriter out = response.getWriter();
			  JSONObject fishGrid=UIUtils.convertToJqGridTableObject(STDWorkSheetList, request,3, 0,commonFilter.getTotalRecordCnt());
			   out.println(fishGrid);  			 	
			   commonFilter.setViewClick('N');  			 	
			   httpSession.removeAttribute("StdWorkSheetCommonFilter");
			   httpSession.setAttribute("StdWorkSheetCommonFilter", commonFilter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
}*/
	
		else if(action.equals("STDWorkSheetForm_input.stdwosh")){
			String Vnew = request.getParameter("new");
			request.setAttribute("vnew", Vnew);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/StdWorkSheet.jsp");
			rd.forward(request, response); 
			CommonMessage.debugMsg("STDWorkSheetForm_input.stdwosh end");
		}
		
		
		else if (action.equals("STDWorkSheetMain_delete.stdwosh")) {
			deleteWorkSheet(request,response);
			
		}
		else if( action.equals("functionalLoc.stdwosh"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmborcFactoryid");
			functLocFieldNameBean.setSection("txtStwsFlid");
			functLocFieldNameBean.setCell("cmborcCellid");
			functLocFieldNameBean.setMachine("cmborcMachineid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
		
	}

	private void DeleteStdRow(HttpServletRequest request,           //delete Row
			HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		StdTlStdworksheetdtl  newStdTlStdworksheetdtl=new StdTlStdworksheetdtl();
		String stdDtl =  request.getParameter("stdWorkSheetdelete");
		CommonMessage.debugMsg("stdDtl   "+stdDtl);
		List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl = null;
		 JSONArray jsonmasterArray = null;
		 if(UIUtils.isValidKeyId(stdDtl)){ //master json
				if( stdDtl != null && ! stdDtl.isEmpty())
	    		{
					
					CommonMessage.debugMsg("stdWorkSheet");
					jsonmasterArray = JSONArray.fromString(stdDtl);
					CommonMessage.debugMsg("stdWorkSheet34345");
					lstStdTlStdworksheetdtl=(List<StdTlStdworksheetdtl>)UIUtils.convertJSONArrToList(newStdTlStdworksheetdtl,jsonmasterArray);
					CommonMessage.debugMsg("size StdWorkSheet567..."+lstStdTlStdworksheetdtl.size());
	    		}
		 }
    try
		{
		    standardizedWorkSheetService.DeleteStdWorkRow(lstStdTlStdworksheetdtl);
			String msgPropertyIdnt = "success-delete";
			CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
		    //CommonMessage.debugMsg("Delete End");
   catch(Exception e)
	{
		CommonMessage.debugMsg("Exception: "+e);
		JSONObject err = new JSONObject();
		String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
		err.put("successData",mesg);
		CommonMessage.debugMsg(err.toString());
		out.print(err.toString());
	}
}			
				/*CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		String gridvalue = request.getParameter("gridvalue");
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				standardizedWorkSheetService.DeleteStdRow(keyid);
				String msgPropertyIdnt = "success-delete";
				CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonMessage.debugMsg("Delete End");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception: "+e);
			JSONObject err = new JSONObject();
			String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
			err.put("successData",mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}*/

		
private void deleteWorkSheet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		   String deletemsg;
		   StdTlStdworksheetmst newStdTlStdworksheetmst=new StdTlStdworksheetmst();//model
		   StdWorSheetkBean stdWorSheetkBean = new StdWorSheetkBean();
		   
		   StdTlStdworksheetmst existStdTlStdworksheetmst=new StdTlStdworksheetmst();//model
		   newStdTlStdworksheetmst =(StdTlStdworksheetmst)UIUtils.setBeanProperties((Object)newStdTlStdworksheetmst,request);
		   existStdTlStdworksheetmst = standardizedWorkSheetService.delete(newStdTlStdworksheetmst,existStdTlStdworksheetmst,stdWorSheetkBean);
		   deletemsg="Data Deleted Successfully";

			JSONObject successData = new JSONObject();
			successData.put("msg",deletemsg);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);		
			//CommonMessage.debugMsg(returnData.toString());
			PrintWriter out= response.getWriter();
			out.print(returnData.toString());//
	}

	private void saveWorkSheet(HttpServletRequest request,
			HttpServletResponse response, StdWorSheetkBean stdWorSheetkBean) throws Exception {
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try{
			if( httpSession != null && user != null)
				{	
				StdTlStdworksheetmst newStdTlStdworksheetmst=new StdTlStdworksheetmst();//model
				StdTlStdworksheetdtl  newStdTlStdworksheetdtl=new StdTlStdworksheetdtl();
				//--------------Detail Grid------------------------------
				String stdDtl =  request.getParameter("stdWorkSheet");
				String filemanger =  request.getParameter("filemanger");
				CommonMessage.debugMsg("   filemanger :: "+filemanger);
				
				CommonMessage.debugMsg("stdDtl   "+stdDtl);
				List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl = null;
				 JSONArray jsonmasterArray = null;
				 StdTlStdworksheetmst existStdTlStdworksheetmst = (StdTlStdworksheetmst)httpSession.getAttribute("newStdTlStdworksheetmst");
				 newStdTlStdworksheetmst =(StdTlStdworksheetmst)UIUtils.setBeanProperties((Object)newStdTlStdworksheetmst,request);//master
				newStdTlStdworksheetdtl =(StdTlStdworksheetdtl)UIUtils.setBeanProperties(newStdTlStdworksheetdtl,request);
				newStdTlStdworksheetmst.setStwsCreatedby(user.getUsrm_ccno()); 
				 if(UIUtils.isValidKeyId(stdDtl)){ //master json
						if( stdDtl != null && ! stdDtl.isEmpty())
			    		{
							
							CommonMessage.debugMsg("stdWorkSheet");
							jsonmasterArray = JSONArray.fromString(stdDtl);
							CommonMessage.debugMsg("stdWorkSheet34345");
							lstStdTlStdworksheetdtl=(List<StdTlStdworksheetdtl>)UIUtils.convertJSONArrToList(newStdTlStdworksheetdtl,jsonmasterArray);
							CommonMessage.debugMsg("size StdWorkSheet567..."+lstStdTlStdworksheetdtl.size());
			    		}
						CommonMessage.debugMsg("StdWorkSheet "+lstStdTlStdworksheetdtl.size());
			     if(lstStdTlStdworksheetdtl!=null)
						   {
							newStdTlStdworksheetmst.setStdTlStdworksheetdtl(lstStdTlStdworksheetdtl);
							CommonMessage.debugMsg("StdWorkSheet345 "+lstStdTlStdworksheetdtl.size());
				 for(int i=0;i<lstStdTlStdworksheetdtl.size();i++)
						   {
							CommonMessage.debugMsg("StdWorkSheet123: "+newStdTlStdworksheetdtl.getStwdKeyid());
				}
		}
}
                String savemsg;
				boolean insert = true;
				if( ! UIUtils.isValidKeyId (newStdTlStdworksheetmst.getStwsKeyid() )  )//NOT NULL CRETTE
						{	
					       existStdTlStdworksheetmst   =standardizedWorkSheetService.create(newStdTlStdworksheetmst ,existStdTlStdworksheetmst,stdWorSheetkBean);
						    savemsg="Data Saved Successfully";
						}
						else{
							insert = false;
							existStdTlStdworksheetmst =standardizedWorkSheetService.update(newStdTlStdworksheetmst,existStdTlStdworksheetmst,stdWorSheetkBean);
						    savemsg="Data Updated Successfully";
						    }
		    JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			
			returnData.put("successData", successData);
			
			CommonMessage.debugMsg(" Inside :: filemanger "+filemanger);
			
			returnData.put("StwsKeyid", existStdTlStdworksheetmst.getStwsKeyid());
			returnData.put("formClear", false);
			
			if(UIUtils.isValidKeyId(filemanger)){
			    CommonMessage.debugMsg(" Inside filemanger132 "+filemanger);
				returnData.put("filemanger",true);
				returnData.put("formClear",false);
			}
			
			out.print(returnData.toString());//
//			/out.close();


        }
	}
		catch(ValidationExceptions e){
		JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"StdWorkSheet");
		//errMessage.put("formActionMode",StudentMasterBean.getFormActionMode());
		out.print(errMessage.toString());
	}
}

	private CommonFilter populateCommonFilter(HttpServletRequest request,
			String beanIdentifier, boolean createNew) {
		{
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
			commonFilter.setViewClick('Y');
			CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
			return commonFilter;
		}
}
}
