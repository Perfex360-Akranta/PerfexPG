package com.akranta.tpm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.SAPExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SapQueueBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.SapTlPerfexMappingDtl;
import com.akranta.tpm.model.SapTlPerfexMappingMst;
import com.akranta.tpm.service.ERPServices;
import com.akranta.tpm.service.SOAPService;
import com.akranta.tpm.service.impl.ERPServiceImpl;
import com.akranta.tpm.service.impl.SOAPServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.SAPMappingMst;
import com.akranta.tpm.utils.SOAPClient;

/**
 * Servlet implementation class SOAPServlet
 */

public class SOAPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ERPServices   erpServices ;
	SOAPService	  soapService;	
    /**
     * @see HttpServlet#HttpServlet()
     */
	 private static final String SAPDATAEXG_DIR_PATH = "sapdataexchangexml";
	 private static final String SAPCONFIG_OBJ_IDENT = "perfexSapConfigObj";
	 private static final String SAPQUEUE_LIST_IDENT = "perfexSapQueListIdnt";
	 String  filePath =null;

    public void init(ServletConfig config){
    	filePath = config.getServletContext().getRealPath(SAPDATAEXG_DIR_PATH) + "\\";
        new File(filePath).mkdirs();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =UIUtils.getActionPart(request);
		
		 try {
			
			erpServices = (ERPServiceImpl)UIUtils.getServiceObject(request,"ERPServiceImpl");
			
			if( soapService == null){
				soapService = (SOAPServiceImpl)UIUtils.getServiceObject(request,"SOAPServiceImpl");
				soapService.getSOAPClient().setFilePath(filePath);
			}	 
			
			if(action.equals("getSapdata.soap") ){
				org.json.JSONObject retData = getPostSapData(request,response);
				if( retData != null ){
					response.getWriter().print(retData);
				}
			}
			else if(action.equals("postSapdata.soap") ){
				postSapData(request,response);
			}
			else if(action.equals("sapSubmitQ_input.soap") ){
				postSapData(request,response);
			}
			else if(action.equals("sapSubmitQ_getCol.soap") ){

				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "sap_submit_queue_colmodel");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
			else if(action.equals("sapSubmitQ_getData.soap") ){
					
				List<?>  sapQueueBeanList = ( List<?>) request.getSession().getAttribute(SAPQUEUE_LIST_IDENT);
				JSONObject colModel =JSONObject.fromString(UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "sap_submit_queue_colmodel"));
				JSONObject data =   UIUtils.convertToJqGridTableObject(sapQueueBeanList,request,colModel, 0);
				
				response.getWriter().print(data);
				response.getWriter().close();
			}
			else if(action.equals("SOAPMapping_input.soap") ){
				String masterId = request.getParameter("masterId");
				if( CommonFunctions.isValidKeyId(masterId)){
					SapTlPerfexMappingMst sapTlPerfexMappingMst = erpServices.getSapPerfexMappingMst(masterId);
					request.setAttribute("sapTlPerfexMappingMst", sapTlPerfexMappingMst);
				}
				
				UIUtils.forwardRequest(request, response, "/pages/Sap/sapfieldmapping.jsp") ;
			}
			else if( action.equals("MappingMaster_retrive.soap")){
				String masterId = request.getParameter("masterId");
				if( CommonFunctions.isValidKeyId(masterId)){
					SapTlPerfexMappingMst sapTlPerfexMappingMst = erpServices.getSapPerfexMappingMst(masterId);
					JSONObject sapTlPerfexMappingJson = JSONObject.fromBean(sapTlPerfexMappingMst);
					response.getWriter().print(sapTlPerfexMappingJson);
					response.getWriter().close();
				}
			}
			else if(action.equals("SOAPTableSelMapping_input.soap") ){
				UIUtils.forwardRequest(request, response, "/pages/Sap/tableSelectpopup.jsp") ;
			}
			else if(action.equals("getTableColumns_getCol.soap") ){

				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "tableFields");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
			else if(action.equals("getTableColumns_getData.soap") ){
				try 
				{
					String tableName = request.getParameter("tableName");
					List<String[]> tableList = erpServices.getTableDetails(tableName);
					response.getWriter().print(UIUtils.convertToJqGridTableObject(tableList, request, 0, 0, 0, tableList.size()));
					response.getWriter().close();
				}catch(Exception e){
					
				}
				
			}
			else if(action.equals("SAPResultColumns_getCol.soap") ){
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "SAPResult_colmodel");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
			else if(action.equals("SAPResultColumns_getData.soap") ){
				org.json.JSONObject retData = getPostSapData(request,response);
				if( retData != null ){
					response.getWriter().print(convertJSONObjectToJqGrid(retData));
				}
				
			}
			else if(action.equals("mappingTableDetails_getCol.soap") ){
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "mappingTableColModel");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
			else if(action.equals("mappingTableDetails_getData.soap") ){
				try 
				{
					String masterId = request.getParameter("masterId");
					String forRequest = request.getParameter("forRequest");
					String forResponse = request.getParameter("forResponse");
					List<String[]> tableList = erpServices.getDetailsData(masterId,forRequest,forResponse);
					response.getWriter().print(UIUtils.convertToJqGridTableObject(tableList, request, 0, 0, 0, tableList.size()));
					
				}catch(Exception e){
					//e.printStackTrace();
					
				}
				finally{
					response.getWriter().close();
				}
				
			}
			else if(action.equals("tableFields_combo.soap")){
				String tableName = request.getParameter("tableName");
				String gridId = request.getParameter("gridId");
				String rowId = request.getParameter("rowId");
				String pkFieldName = erpServices.getPrimaryKeyField(tableName);
				JSONObject tableDetails = new JSONObject();
				tableDetails.put("pkField",pkFieldName);
				
				List<ComboBox> tableFileds = erpServices.getTableFields(tableName);
				response.setContentType("text/html;charset=UTF-8");
				    PrintWriter out = response.getWriter(); 	
					
			    JSONArray jsonArray = JSONArray.fromObject(tableFileds);
			    tableDetails.put("tableFields", jsonArray);
			    tableDetails.put("gridId", gridId);
			    tableDetails.put("rowId", rowId);
		        out.print(tableDetails);
			}
			else if(action.equals("tableList_combo.soap")){
				
				try 
				{
					ComboFilter comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  tableList = erpServices.getAllTabTables(comboFilter);
					
					UIUtils.writeComboBox(response, tableList,comboFilter);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else if( action.equals("SOAPConfig_view.soap")){
				UIUtils.forwardRequest(request, response, "/pages/Sap/sapconfigview.jsp") ;
			}
			else if(action.equals("SOAPConfig_getCol.soap") ){
				String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.soapmapping", "soap_congif_view_colmodel");
				response.getWriter().print(colModel);
				response.getWriter().close();
			}
			else if( action.equals("genSOAPMessage_send.soap")){
				
				String processCode = request.getParameter("processCode");
				String transId = request.getParameter("transId");
				try{
					soapService.sumbitToSAP(processCode,transId);
				} catch (SAPExceptions e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					response.getWriter().print(" Error while sending.. " + e.getMessage());
				} catch (SOAPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.getWriter().print(" Error while sending.. " + e.getMessage());
				} catch (Exception e) {
					response.getWriter().print(" Error while sending.. " + e.getMessage());
				}
			}
			else if(action.equals("processName_combo.soap")){
				try 
				{
					ComboFilter comboFilter=UIUtils.fillComboFilter(request);
					List<ComboBox>  processNames = erpServices.getProcessName(comboFilter);
					
					UIUtils.writeComboBox(response, processNames,comboFilter);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else if(action.equals("SOAPConfig_getData.soap") ){
				
				List<String[]> tableList = erpServices.getAllProcess();
				response.getWriter().print(UIUtils.convertToJqGridTableObject(tableList, request, 0, 0, 0, tableList.size()));
				response.getWriter().close();
			}
			else if(action.equals("SOAPMapping_save.soap")){
				saveForm(request,response);
			}
			else if(action.equals("SOAPMappingdtl_delete.soap")){
				deleteDtl(request,response);
			}
			
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		//}catch(Exception e)cr
		//{
			
		} catch (SAPExceptions e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			response.getWriter().print(" Error while sending.. " + e.getMessage());
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().print(" Error while sending.. " + e.getMessage());
		} catch (Exception e) {
			
		}
	}
	
	private void saveForm(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter  out = response.getWriter();
		try{
			SapTlPerfexMappingMst sapTlPerfexMappingMst = populateModelObjects(request);
			
			erpServices.save(sapTlPerfexMappingMst);
			
			JSONObject successData = new JSONObject();
		    	 
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
		    successData.put("masterId", sapTlPerfexMappingMst.getSpmmKeyid());
			JSONObject returnData = new JSONObject();	
			returnData.put("formClear",false);
			returnData.put("successData", successData);
				
			
			out.print(returnData.toString());
			out.close();
			sapTlPerfexMappingMst =null;
			
		}catch(ValidationExceptions e)
		{
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"ERPMappingMessages");
			out.print(errMessage.toString());
		}
		catch(BusinessApplicationExceptions e)
		{ 
			 JSONObject errMessage = new JSONObject();
			 errMessage = UIUtils.businessValidationExceptions(e.getMessage(), "ERPMappingMessages");
			 out.print(errMessage.toString());
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private SapTlPerfexMappingMst populateModelObjects(HttpServletRequest request){
		SapTlPerfexMappingMst sapTlPerfexMappingMst = new SapTlPerfexMappingMst();
		
		sapTlPerfexMappingMst = (SapTlPerfexMappingMst)UIUtils.setBeanPropertiesNoConv(sapTlPerfexMappingMst,request);
		
		String mappingRequestListStr =  request.getParameter("mappingRequestList");
		String mappingResponseListStr =  request.getParameter("mappingResponseList");
		String createdBy = UIUtils.getLoginUser(request).getUsrm_ccno();
		sapTlPerfexMappingMst.setSpmmCreatedby(createdBy);
		sapTlPerfexMappingMst.setSpmmProcesstype("X");
		if( UIUtils.isValidKeyId(mappingRequestListStr) || UIUtils.isValidKeyId(mappingResponseListStr)  ){
			SapTlPerfexMappingDtl sapTlPerfexMappingDtl = new SapTlPerfexMappingDtl();
			if( UIUtils.isValidKeyId(mappingRequestListStr) ) {
				JSONArray sapTlPerfexMappingDtllistJson = JSONArray.fromString(mappingRequestListStr);
				List<SapTlPerfexMappingDtl> sapTlPerfexMappingDtllist =(List<SapTlPerfexMappingDtl>)UIUtils.convertJSONArrToListNoConv(sapTlPerfexMappingDtl, sapTlPerfexMappingDtllistJson);
				for( SapTlPerfexMappingDtl sapTlPerfexMappingD :sapTlPerfexMappingDtllist){
					sapTlPerfexMappingD.setSpmdCreatedby(createdBy);
					if( "Y".equals( sapTlPerfexMappingD.getSpmdIncludecondition()))
						sapTlPerfexMappingD.setSpmdIncluderequest("N");
					else
						sapTlPerfexMappingD.setSpmdIncluderequest("Y");
					
					sapTlPerfexMappingD.setSpmdIncluderesponse("N");
					sapTlPerfexMappingD.setSpmdIsforalert("N");
					if( ! UIUtils.isValidKeyId(sapTlPerfexMappingD.getSpmdPerfexRefTable()))
						sapTlPerfexMappingD.setSpmdPerfexRefTable("-");
					if( ! UIUtils.isValidKeyId(sapTlPerfexMappingD.getSpmdPerfexRefColumn()))
						sapTlPerfexMappingD.setSpmdPerfexRefColumn("-");
					if( ! UIUtils.isValidKeyId( sapTlPerfexMappingD.getSpmdPerfexRefMapcolumn() ))
						sapTlPerfexMappingD.setSpmdPerfexRefMapcolumn("-");
					
					if( ! UIUtils.isValidKeyId(sapTlPerfexMappingD.getSpmdIncludecondition() ))
						sapTlPerfexMappingD.setSpmdIncludecondition("N");
					sapTlPerfexMappingD.setSpmdConditionvalue("-");
					sapTlPerfexMappingD.setSpmdParentid("X");
					
				}
				sapTlPerfexMappingMst.setSapTlPerfexMappingDtlList(sapTlPerfexMappingDtllist);
			}
			if( UIUtils.isValidKeyId(mappingResponseListStr) ) {
				JSONArray sapTlPerfexMappingDtllistJson = JSONArray.fromString(mappingResponseListStr);
				List<SapTlPerfexMappingDtl> sapTlPerfexMappingDtllist =(List<SapTlPerfexMappingDtl>)UIUtils.convertJSONArrToList(sapTlPerfexMappingDtl, sapTlPerfexMappingDtllistJson);
				for( SapTlPerfexMappingDtl sapTlPerfexMappingD :sapTlPerfexMappingDtllist){
					sapTlPerfexMappingD.setSpmdCreatedby(createdBy);
					
					sapTlPerfexMappingD.setSpmdIncluderequest("N");
					sapTlPerfexMappingD.setSpmdIncluderesponse("Y");
					sapTlPerfexMappingD.setSpmdIsforalert("N");
					if(! UIUtils.isValidKeyId( sapTlPerfexMappingD.getSpmdIncludecondition() ))
						sapTlPerfexMappingD.setSpmdIncludecondition("N");
					
					if( ! UIUtils.isValidKeyId(sapTlPerfexMappingD.getSpmdPerfexRefTable() ))
						sapTlPerfexMappingD.setSpmdPerfexRefTable("-");
					if(! UIUtils.isValidKeyId( sapTlPerfexMappingD.getSpmdPerfexRefColumn() ))
						sapTlPerfexMappingD.setSpmdPerfexRefColumn("-");
					if( ! UIUtils.isValidKeyId(sapTlPerfexMappingD.getSpmdPerfexRefMapcolumn() ))
						sapTlPerfexMappingD.setSpmdPerfexRefMapcolumn("-");
					
					sapTlPerfexMappingD.setSpmdConditionvalue("-");
					sapTlPerfexMappingD.setSpmdParentid("X");
				}
				if( sapTlPerfexMappingMst.getSapTlPerfexMappingDtlList() == null) 	
					sapTlPerfexMappingMst.setSapTlPerfexMappingDtlList(sapTlPerfexMappingDtllist);
				else{
					 sapTlPerfexMappingMst.getSapTlPerfexMappingDtlList().addAll(sapTlPerfexMappingDtllist);
				}
			}
		}	
		return sapTlPerfexMappingMst;
	}
	
	private void deleteDtl(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String dtlIds = request.getParameter("dtlids");
		try{
			erpServices.deleteSapPerfexMappingDtl(dtlIds);
			
			JSONObject successData = new JSONObject();
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			JSONObject returnData = new JSONObject();	
			returnData.put("successData", successData);
			
			response.getWriter().print(returnData.toString());
			response.getWriter().close();
		}catch(Exception e){
			JSONObject successData = new JSONObject();
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
			JSONObject returnData = new JSONObject();	
			returnData.put("successData", successData);

			response.getWriter().print(returnData.toString());
			response.getWriter().close();
		}
		
	}

	public synchronized void  postSapData(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException{
		
		String transId = request.getParameter("transId");
		String processCode = request.getParameter("processCode");
		//String processName = request.getParameter("processName");
		
		CommonFunctions.debugMsg("TransId in servlet         "+transId);
		CommonFunctions.debugMsg("processCode in servlet         "+processCode);

		JSONObject retunObj = new JSONObject();
		retunObj.put("rowId",transId);
		retunObj.put("transId",transId);
		retunObj.put("transName",processCode);
		
		
		try {
				populateSapRequestData(request, retunObj);
				SapQueueBean sd =new SapQueueBean();
			    CommonFunctions.debugMsg(" Status value in Servlet"+sd.getTxtStatus());
				
				SAPMappingMst sapMappingMst = getSapConfigObj(request,processCode);
				//UIUtils.bindAppExcetionMsg(response,retunObj);
				CommonFunctions.debugMsg("Status......."+response+" object...."+retunObj);
				
				String sucessMsg = soapService.getSOAPClient().sumbitToSAPTest(sapMappingMst, transId,processCode);
				
				
				retunObj.put("msg",sucessMsg);//"Succesfully Order Created and techoed");
				CommonFunctions.debugMsg("After put to returnObj......."+response+" object...."+retunObj);
				response.getWriter().print(retunObj);				
				response.getWriter().close();
				
				updateSapPostMsg(request,processCode,true,retunObj);
				CommonFunctions.debugMsg("updateSapPostMsg"); 
				//response.getWriter().print(returnData);
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			CommonFunctions.debugMsg("No Data Found Excpetion before msg"+e.getLocalizedMessage());
			retunObj.put("msg","Service not configured! Please contact Administrator");
			CommonFunctions.debugMsg("No Data Found Excpetion After msg Before dtl ..."+e.getLocalizedMessage());
			retunObj.put("dtl",e.getLocalizedMessage());
			CommonFunctions.debugMsg("No Data Found Excpetion After dtl..."+e.getLocalizedMessage());

			updateSapPostMsg(request,processCode,true,retunObj);
			CommonFunctions.debugMsg("No Data Found Excpetion After calling...updateSapPostMsg   "+e.getLocalizedMessage());

			UIUtils.bindAppExcetionMsg(response,retunObj);
			e.printStackTrace();
		} catch (SAPExceptions e) {
			// TODO Auto-generated catch block
			CommonFunctions.debugMsg("SAPExceptions before msg"+e.getLocalizedMessage());

			retunObj.put("msg",e.getLocalizedMessage());
			CommonFunctions.debugMsg("SAPExceptions After msg"+e.getLocalizedMessage());

			retunObj.put("dtl",e.getMessage());
			CommonFunctions.debugMsg("SAPExceptions After dtl"+e.getLocalizedMessage());

			updateSapPostMsg(request,processCode,true,retunObj);
			CommonFunctions.debugMsg("SAPExceptions After calling.updateSapPostMsg  "+e.getLocalizedMessage());

			UIUtils.bindAppExcetionMsg(response,retunObj);
			
			
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			CommonFunctions.debugMsg("SOAPException before msg  "+e.getLocalizedMessage());

			retunObj.put("msg",e.getLocalizedMessage());
			CommonFunctions.debugMsg("SOAPException After msg  "+e.getLocalizedMessage());

			retunObj.put("dtl",e.getMessage());
			updateSapPostMsg(request,processCode,true,retunObj);
			CommonFunctions.debugMsg("SOAPException After calling.updateSapPostMsg  "+e.getLocalizedMessage());

			UIUtils.bindAppExcetionMsg(response,retunObj);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CommonFunctions.debugMsg("IOException before msg  "+e.getLocalizedMessage());
			retunObj.put("msg",e.getLocalizedMessage());
			CommonFunctions.debugMsg("IOException After msg  "+e.getLocalizedMessage());
			retunObj.put("dtl",e.getMessage());
			CommonFunctions.debugMsg("IOException After dtl  "+e.getMessage());
			updateSapPostMsg(request,processCode,true,retunObj);
			CommonFunctions.debugMsg("IOException After calling  "+e.getLocalizedMessage());
			UIUtils.bindAppExcetionMsg(response,retunObj);
			e.printStackTrace();
		/*} catch (JSONException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
	*/	}
	}

	private void populateSapRequestData(HttpServletRequest request, JSONObject retunObj){
		String resend = request.getParameter("resend");
		if( ! "Y".equals(resend) ){
			String transId = request.getParameter("transId");
			String transName = request.getParameter("transName");
			String processName = request.getParameter("processName");
			
			SapQueueBean sapQueueBean = new SapQueueBean();
			sapQueueBean.setTxtProcessName(processName);
			sapQueueBean.setTxtTransId(transId);
			sapQueueBean.setTxtProcessCode(transName);
			List<SapQueueBean>  sapQueueBeanList = (List<SapQueueBean>) request.getSession().getAttribute(SAPQUEUE_LIST_IDENT);
			if( sapQueueBeanList == null ){
				sapQueueBeanList = new ArrayList<SapQueueBean>();
				request.getSession().setAttribute(SAPQUEUE_LIST_IDENT,sapQueueBeanList);
			}
			sapQueueBean.setBtnAction("Submitting..");
			sapQueueBean.setTxtStatus("Submitting...");
			sapQueueBeanList.add(sapQueueBean);
			
		}
	}
	
	private void updateSapPostMsg(HttpServletRequest request, String transName,boolean success,JSONObject returnObj ){
			
		CommonFunctions.debugMsg("updateSapPostMsg"); 
			
			List<SapQueueBean>  sapQueueBeanList = (List<SapQueueBean>) request.getSession().getAttribute(SAPQUEUE_LIST_IDENT);
			if( sapQueueBeanList == null ){
				sapQueueBeanList = new ArrayList<SapQueueBean>();
				request.getSession().setAttribute(SAPQUEUE_LIST_IDENT,sapQueueBeanList);
			}
			
			for( SapQueueBean sapQueueBean : sapQueueBeanList ){
				if(transName.equals(sapQueueBean.getTxtProcessCode()) ){
					CommonFunctions.debugMsg("trnasaction Name.. in update....."+transName);
					CommonFunctions.debugMsg("Process Code...in update...."+sapQueueBean.getTxtProcessCode());

					if(success){
						CommonFunctions.debugMsg("Process Code...Success...."+sapQueueBean.getTxtProcessCode());

						sapQueueBean.setTxtStatus("Success");
						sapQueueBean.setBtnAction("Success");
						sapQueueBean.setTxtMessage("");
					}
					else {
						sapQueueBean.setBtnAction("Re-Submit");
						CommonFunctions.debugMsg("Process Code...false...."+sapQueueBean.getTxtProcessCode());

						sapQueueBean.setTxtStatus("Failed");
						sapQueueBean.setTxtMessage(returnObj.getString("errDtl"));
					}
					break;
				}
			}
			
			CommonFunctions.debugMsg("Completed updateSapPostMsg");
	}
	
	public org.json.JSONObject getPostSapData(HttpServletRequest request,HttpServletResponse response) throws JSONException, IOException{
		
		String processCode = request.getParameter("processCode");
		String sapPostData = request.getParameter("sapPostData");
		CommonFunctions.debugMsg(" processCode " +processCode +" sapPostData "+sapPostData);

		try {
				if( UIUtils.isValidKeyId( sapPostData )){
					org.json.JSONObject postData = new org.json.JSONObject(sapPostData) ;//.fromString(request.getParameter("sapPostData"));
					SAPMappingMst sapMappingMst = getSapConfigObj(request,processCode);
					CommonFunctions.debugMsg(" sapMappingMst " +sapMappingMst.toString() +" sapPostData "+sapPostData);

					return soapService.getSOAPClient().sumbitToSAPDirect(sapMappingMst, postData);
				}
				else{
					JSONObject err = new JSONObject();
					err.put("msg","No data to post");
					UIUtils.bindAppExcetionMsg(response,err);
				}
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,"Service not configured! Please contact Administrator");
			e.printStackTrace();
		} catch (SAPExceptions e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			
			e.printStackTrace();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			UIUtils.bindAppExcetionMsg(response,e,e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public void populateSapConfigObj(HttpServletRequest request){
		
		try{
			
			List<SAPMappingMst> sapConfigObjs = sapConfigObjs =(List<SAPMappingMst>) erpServices.getSapPerfexMappingConfig();
			

			request.getServletContext().setAttribute(SAPCONFIG_OBJ_IDENT, sapConfigObjs);
			
		}catch (Exception e){
			
		}

	}

	
	private SAPMappingMst getSapConfigObj(HttpServletRequest request,String processCode) throws NoDataFoundException{
		
		try{
			List<SAPMappingMst> sapConfigObjs =(List<SAPMappingMst>) request.getServletContext().getAttribute(SAPCONFIG_OBJ_IDENT);			
			if( sapConfigObjs == null ){
				//sapConfigObjs = erpServices.getSapPerfexMappingConfig();
				//request.getServletContext().setAttribute(SAPCONFIG_OBJ_IDENT, sapConfigObjs);
				sapConfigObjs = new ArrayList<SAPMappingMst>();
				request.getServletContext().setAttribute(SAPCONFIG_OBJ_IDENT, sapConfigObjs);
			}
			for( SAPMappingMst sapMappingMst : sapConfigObjs  ){
				if( sapMappingMst.getSpmmProcesscode().equals(processCode) ){
					CommonFunctions.debugMsg(" sapTlPerfexMappingMst.getSpmmProcesscode()  " + sapMappingMst.getSpmmProcesscode());
					return sapMappingMst;
				}
			}
			SAPMappingMst sapMappingMst = soapService.getSOAPClient().getConfigData(processCode);
			sapConfigObjs.add(sapMappingMst);
			return sapMappingMst;
			//throw new NoDataFoundException("SAP Configuration not found for the " + transId);
		}catch (SAPExceptions e){
			throw new NoDataFoundException("SAP Configuration not found for the " + processCode);
		}

	}
	
	private JSONObject convertJSONObjectToJqGrid(org.json.JSONObject jsonData){
		JSONObject tableDataObject = new JSONObject();
		
		org.json.JSONArray names = jsonData.names();
		
		
		JSONArray rowArr = new JSONArray(); 
       
      

		int slno = 0;
        for( int i = 0;i<names.length();i++)
		{
    	    JSONObject rowObj =new JSONObject();
    	    	
    	    rowObj.put("id",i +1);
    	    try{
	            String name = names.getString(i);
	    	    
	            JSONArray cell=new JSONArray();
	            cell.put(name);
	            cell.put(jsonData.getString(name));
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
    	    }catch(Exception e){}

       }

        tableDataObject.put("page", 1); //current page
		tableDataObject.put("total",names.length()); // total page
		//if( page == 1)
		tableDataObject.put("records", names.length()); //total records
		
        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}
	
}
