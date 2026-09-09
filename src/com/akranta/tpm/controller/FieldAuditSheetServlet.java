package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
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
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetdtl;
import com.akranta.tpm.model.FieldAuditSheetmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.FieldAuditSheetService;
import com.akranta.tpm.service.impl.FieldAuditSheetServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class FieldAuditSheetServlet
 */
@WebServlet("/FieldAuditSheetServlet")
public class FieldAuditSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private FieldAuditSheetService fieldAuditSheetService;
	private FieldAuditSheetServiceApi fieldauditsheetApi;
    public FieldAuditSheetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			process(request, response);
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
		//doGet(request, response);
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    HttpSession httpSession=request.getSession(false);
	    String action=UIUtils.getActionPart(request);
	    ComboFilter comboFilter = new ComboFilter();
    
	    fieldAuditSheetService=(FieldAuditSheetServiceImpl)UIUtils.getServiceObject(request,"FieldAuditSheetServiceImpl");
	    fieldAuditSheetService.FieldAuditSheetServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	    
	    

	  /*if(action.equals("FieldAuditSheet_input.fass")){
		    String mode = request.getParameter("mode");   
		    CommonMessage.debugMsg("mode"+mode);
			String keyid =request.getParameter("keyid");
			CommonMessage.debugMsg("keyid"+keyid);
			FieldAuditSheetmst fieldAuditSheetmst=new FieldAuditSheetmst();
			AdmTlUsermst user = UIUtils.getLoginUser(request);	
			fieldAuditSheetmst.setFasmCreatedby(user.getUsrm_ccno());
			fieldAuditSheetmst.setFasmDate(CommonFunctions.dateTimeNow());
			if("modify".equals(mode)|| "view".equals(mode) ){
				CommonMessage.debugMsg("keyid:::"+keyid);
				fieldAuditSheetmst=fieldAuditSheetService.select(keyid);
		       	request.setAttribute("keyid",keyid);
		       	String Date=fieldAuditSheetmst.getFasmDate().substring(0,11);
		    	CommonMessage.debugMsg("The Date"+Date);
		    	request.setAttribute("Date",Date);
		    	request.setAttribute("fieldAuditSheetmst",fieldAuditSheetmst);
			 	httpSession.setAttribute("fieldAuditSheetmst", fieldAuditSheetmst);
		    	}
			request.setAttribute(mode,"mode");
			UIUtils.forwardRequest(request, response,"/pages/FieldAuditSheet.jsp");  
	  }*/
	     if(action.equals("FieldAuditSheet_input.fass")){
	        String mode = request.getParameter("mode");   
	        CommonMessage.debugMsg("mode: " + mode);
	        String keyid = request.getParameter("keyid");
	        CommonMessage.debugMsg("keyid: " + keyid);
	        
	        FieldAuditSheetmst fieldAuditSheetmst = new FieldAuditSheetmst();
	        AdmTlUsermst user = UIUtils.getLoginUser(request);	
	        fieldAuditSheetmst.setFasmCreatedby(user.getUsrm_ccno());
	        fieldAuditSheetmst.setFasmDate(CommonFunctions.pg_dateTimeNow());
	        
	        if("modify".equals(mode) || "view".equals(mode)){
	            CommonMessage.debugMsg("keyid:::" + keyid);
	            fieldAuditSheetmst = fieldAuditSheetService.select(keyid);
	            request.setAttribute("keyid", keyid);
	            String Date = fieldAuditSheetmst.getFasmDate().substring(0, 11);
	            
	            
String date = fieldAuditSheetmst.getFasmDate();
		 		
fieldAuditSheetmst.setFasmDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
		 		
	            CommonMessage.debugMsg("The Date: " + Date);
	            request.setAttribute("Date", Date);
	            request.setAttribute("fieldAuditSheetmst", fieldAuditSheetmst);
	            httpSession.setAttribute("fieldAuditSheetmst", fieldAuditSheetmst);
	        }
	        
	        // Set mode attribute
	        request.setAttribute(mode, "mode");
	        UIUtils.forwardRequest(request, response, "/pages/FieldAuditSheet.jsp");  
	    }
	  else if(action.equals("ServiceProvider.fass")){
		  try {
       	      comboFilter=UIUtils.fillComboFilter(request);	
			   List<ComboBox> course = fieldAuditSheetService.getServiceProvider(comboFilter);
			   UIUtils.writeComboBox(response, course, comboFilter);
				} catch (Exception e) {
					e.printStackTrace();
				}
	  }
	  
		/*
		 * else if( action.equals("FieldAuditSheet_getCol.fass")){ try{ PrintWriter out
		 * = response.getWriter();
		 * CommonMessage.debugMsg("FieldAuditSheet_getCol.fass.............");
		 * out.print(UIUtils.getPropertyValue(
		 * "com.akranta.tpm.resources.FieldAuditSheet","FieldAuditSheetdtls")); }
		 * catch(Exception e){ e.printStackTrace(); } }
		 */
	  else if(action.equals("FieldAuditSheet_getCol.fass")){
		    try{
		        PrintWriter out = response.getWriter();
		        String mode = request.getParameter("frmActionViewMode");
		        CommonMessage.debugMsg("mano mode"+mode);
		        CommonMessage.debugMsg("FieldAuditSheet_getCol.fass with mode: " + mode);
		        
		        // Use different property based on mode
		        String propertyKey = "FieldAuditSheetdtls"; // default for modify
		        if("view".equals(mode)){
		            propertyKey = "FieldAuditSheetdtls2"; // for view mode
		        }
		        
		        out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.FieldAuditSheet", propertyKey));				
		    }
		    catch(Exception e){	
		        e.printStackTrace();
		    }			 
		}
		else if( action.equals("FieldAuditSheet_getData.fass")){
			try {	
				CommonFilter commonFilter = new CommonFilter();
				String keyid =request.getParameter("keyid");
				CommonMessage.debugMsg("keyid"+keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> AuditSheetList = fieldAuditSheetService.FieldAuditSheetDetail(commonFilter);
				JSONObject AuditSheetData = UIUtils.convertToJqGridTableObject(AuditSheetList, request,2,0);
				out.println(AuditSheetData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("FieldAuditSheetModify_input.fass")){
			String mode = request.getParameter("mode");
			   CommonMessage.debugMsg("The Mode"+mode);
			   request.setAttribute("mode", mode);
			RequestDispatcher rd=request.getRequestDispatcher("pages/FieldAuditSheetModify.jsp");
	    	rd.forward(request, response); 
		}
	  
		else if(action.equals("FieldAuditSheetReport_input.fass")){
			RequestDispatcher rd=request.getRequestDispatcher("pages/FieldAuditSheetReport.jsp");
	    	rd.forward(request, response); 
		}
	else if (action.equals("FieldAuditSheetReport_getCol.fass")){
			
			PrintWriter out = response.getWriter();
			String keyid =request.getParameter("keyid");
			CommonMessage.debugMsg("keyid"+keyid);
			CommonFilter commonFilter = new CommonFilter();
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("Y");
				riskListGrid= fieldAuditSheetService.getFieldAuditSheetReport(commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}	
			
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "62%%");
	   	     httpSession.removeAttribute("FieldAuditSheetListColModel");
			 httpSession.setAttribute("FieldAuditSheetListColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		}
	else if (action.equals("FieldAuditSheetReport_getData.fass")) {			
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"FieldAuditSheetListCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String keyid =request.getParameter("keyid");
			CommonMessage.debugMsg("keyid"+keyid);
			commonFilter.setIsGetCol("N");
			List<String[]> MasterGrid = fieldAuditSheetService.getFieldAuditSheetReport(commonFilter);
			PrintWriter out = response.getWriter();
			//JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request, 2, 0, commonFilter.getTotalRecordCnt());
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}	
	}

	  else if(action.equals("FieldAuditSheetReport_getExcel.fass")){
			CommonFilter commonFilter = populateCommonFilter(request,"FieldAuditSheetListCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("FieldAuditSheetListColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "AuditSheetReport");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = fieldAuditSheetService.getFieldAuditSheetReportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "AuditSheet", format);
		}	
	  
	  
	  
	else if (action.equals("FieldAuditSheetModify_getCol.fass")) {//first page
			
			PrintWriter out = response.getWriter();
			String keyid =request.getParameter("keyid");
			CommonMessage.debugMsg("keyid"+keyid);
			CommonFilter commonFilter = new CommonFilter();
			JSONObject jsonObject = new JSONObject();
			List<String[]>riskListGrid = null;		
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				commonFilter.setIsGetCol("Y");
				riskListGrid= fieldAuditSheetService.getFieldAuditSheetList(commonFilter);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}	
			
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(false);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);	
			 
			 String [] colHeaderHead = riskListGrid.get(0);
			 String [] colHeader = riskListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "108%%");
	     	 jsonObject.set("tableHeight", "62%%");
	   	     httpSession.removeAttribute("FieldAuditSheetListColModel");
			 httpSession.setAttribute("FieldAuditSheetListColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
		}
		/*
		 * else if (action.equals("FieldAuditSheetModify_getData.fass")) { try {
		 * CommonFilter commonFilter =
		 * populateCommonFilter(request,"FieldAuditSheetListCommonFilter",true);
		 * FilterValues.getCommonFilters(request, commonFilter); String keyid
		 * =request.getParameter("keyid"); CommonMessage.debugMsg("keyid"+keyid);
		 * commonFilter.setIsGetCol("N"); List<String[]> MasterGrid =
		 * fieldAuditSheetService.getFieldAuditSheetList(commonFilter); PrintWriter out
		 * = response.getWriter(); JSONObject ResourceGridmod =
		 * UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
		 * out.println(ResourceGridmod); } catch (Exception e) {
		 * CommonMessage.debugMsg(e.getMessage()); } }
		 */
	else if (action.equals("FieldAuditSheetModify_getData.fass")) {			
	    try {
	        CommonFilter commonFilter = populateCommonFilter(request,"FieldAuditSheetListCommonFilter",true);
	        FilterValues.getCommonFilters(request, commonFilter);
	        String keyid = request.getParameter("keyid");
	        CommonMessage.debugMsg("keyid"+keyid);
	        commonFilter.setIsGetCol("N");
	        List<String[]> MasterGrid = fieldAuditSheetService.getFieldAuditSheetList(commonFilter);
	        PrintWriter out = response.getWriter();
	        // FIXED: pass commonFilter.getTotalRecordCnt() like ComplaintGallery does
	        JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject( MasterGrid, request, 2, 0, commonFilter.getTotalRecordCnt() );
	        out.println(ResourceGridmod);
	    } catch (Exception e) {
	        CommonMessage.debugMsg(e.getMessage());
	    }	
	}
	  
	  else if(action.equals("FieldAuditSheetModify_getExcel.fass")){
			CommonFilter commonFilter = populateCommonFilter(request,"FieldAuditSheetListCommonFilter",false);			
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("FieldAuditSheetListColModel");
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			tblJSONObj.put("title", "AuditSheet");			
			String format = ExcelUtils.getFormat(request);	
			Workbook wb = fieldAuditSheetService.getFieldAuditModificationGridDataExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "AuditSheet", format);
		}	
	  else if(action.equals("functionalLoc.fass"))
		{
			
	
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			//functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
			functLocFieldNameBean.setSection("cmbsusmSectionid");
			functLocFieldNameBean.setCell("cmbsusmCellid");
			functLocFieldNameBean.setMachine("cmbsusmMachineid");
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
	        FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
	  else if(action.equals("getjhvalue.fass")){
		  PrintWriter out=response.getWriter();
		  String originalid = request.getParameter("originalval");
		  CommonMessage.debugMsg("The Original ID::"+originalid);
		  List<String[]> keyids= fieldAuditSheetService.getflid(originalid);
		  JSONObject returnData = new JSONObject();
		  JSONObject successData = new JSONObject();
		  successData.put("locn", keyids.get(0)[0]);
		  successData.put("sect", keyids.get(0)[1]);
		  successData.put("flid", keyids.get(0)[2]);
		  successData.put("cellid", keyids.get(0)[3]);
		  returnData.put("successData",successData);
		  out.print(returnData);   
	   }
	  
		else if(action.equals("FieldAuditSheet_save.fass")){
			SaveFieldAuditSheet(request,response);
		}
		else if(action.equals("ppetype.fass")){
	           try {
	        	   comboFilter=UIUtils.fillComboFilter(request);	
				   List<ComboBox> ppetype = fieldAuditSheetService.PPEType(comboFilter);
				   UIUtils.writeComboBox(response, ppetype, comboFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
	     }
	}
private void SaveFieldAuditSheet(HttpServletRequest request,
		HttpServletResponse response) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	try{
		if( httpSession != null && user != null){
			FieldAuditSheetmst newFieldAuditSheetmst=new FieldAuditSheetmst();
			FieldAuditSheetdtl fieldAuditSheetdtl=new FieldAuditSheetdtl();
			String FieldAuditsheetdetail = request.getParameter("FieldAuditsheetdetail");
			CommonMessage.debugMsg("FieldAuditsheetdetail"+FieldAuditsheetdetail);
			newFieldAuditSheetmst=(FieldAuditSheetmst)UIUtils.setBeanProperties((Object)newFieldAuditSheetmst,request);
			FieldAuditSheetmst existFieldAuditSheetmst=(FieldAuditSheetmst)httpSession.getAttribute("newFieldAuditSheetmst");
			newFieldAuditSheetmst.setFasmCreatedby(user.getUsrm_ccno());
			List<FieldAuditSheetdtl> fieldAuditSheetdtlList=null;
    		JSONArray fieldAuditSheetdtljson = null;	    			
    		if(UIUtils.isValidKeyId(FieldAuditsheetdetail)){
    			fieldAuditSheetdtljson=JSONArray.fromString(FieldAuditsheetdetail);
    			fieldAuditSheetdtlList=(List<FieldAuditSheetdtl>)UIUtils.convertJSONArrToList(fieldAuditSheetdtl, fieldAuditSheetdtljson);
			    if(fieldAuditSheetdtlList!= null)
			    {
			        for(int i=0 ;i<=fieldAuditSheetdtlList.size()-1;i++){
			        }
			        newFieldAuditSheetmst.setAuditDtl(fieldAuditSheetdtlList);
			       
			    }
    		}
    		
		 	String savemsg;					
			boolean insert = true;	
			if( ! UIUtils.isValidKeyId (newFieldAuditSheetmst.getFasmKeyid())){	
				existFieldAuditSheetmst=fieldAuditSheetService.create(newFieldAuditSheetmst, existFieldAuditSheetmst);
				savemsg="Data Saved Successfully";
			}
			
			else{
				insert = false;
				CommonMessage.debugMsg("update");
				existFieldAuditSheetmst =fieldAuditSheetService.update(newFieldAuditSheetmst,existFieldAuditSheetmst);				
				savemsg="Data Updated Successfully";					
			}
			JSONObject successData = new JSONObject();
			successData.put("msg",savemsg);
			JSONObject returnData = new JSONObject();//
			returnData.put("successData", successData);
			returnData.put("GenKeyid",existFieldAuditSheetmst.getFasmKeyid());
			successData.put("keyId",newFieldAuditSheetmst.getFasmKeyid());
            returnData.put("formClear", false);
			out.print(returnData.toString());
			//out.close();
			}
		
		}catch (ValidationExceptions e) 
		{
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FieldAuditSheet");
			out.print(errMessage.toString());							    	
		}
							
		catch(BusinessApplicationExceptions e)
		{
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "FieldAuditSheet");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage );
		}
		catch(Exception e)
		{   
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
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
