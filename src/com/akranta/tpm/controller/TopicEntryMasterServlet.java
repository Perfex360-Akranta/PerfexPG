package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlTopicmstBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.service.TopicEntryMasterService;
import com.akranta.tpm.service.impl.MoMeetingServiceImpl;
import com.akranta.tpm.service.impl.TopicEntryMasterServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class TopicEntryMaster
 */
@WebServlet("/TopicEntryMaster")
public class TopicEntryMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TopicEntryMasterService topicEntryMasterService;
        
    public TopicEntryMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void process(HttpServletRequest request,HttpServletResponse response) throws ValidationExceptions, BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			CommonMessage.debugMsg(" service............. ");
			topicEntryMasterService = (TopicEntryMasterService) UIUtils.getServiceObject(request, "TopicEntryMasterServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		String dispatchUrl = null;
	   if(action.equals("TopicEntryMaster_input.topmst")){
		    String Rolemstkeyid=request.getParameter("Rolemstkeyid");
		    CommonMessage.debugMsg("Rolemstkeyid:::::"+Rolemstkeyid );
		    String loginlocnId = (String) httpSession.getAttribute("loginLocnId");
	    	UIUtils.forwardRequest(request, response, "/pages/ENT/TopicEntryMaster.jsp");
	    	//CommonMessage.debugMsg("loginlocnId"+loginlocnId);
	    	CommonMessage.debugMsg("The LoginId:::"+loginlocnId);
	    	request.setAttribute("locnId", loginlocnId);
	    	request.setAttribute("Rolemstkeyid", Rolemstkeyid);
	    }
	   else if (action.equals("TopicEntryMaster_getCol.topmst"))
	   {
		   TopicEntryDetailgetCol(request, response);
	   }
	   else if (action.equals("TopicEntryMaster_getData.topmst"))
	   {
		   TopicEntryDetailgetData(request, response);
	   }
		else if(action.equals("TopicEntryMaster_getExcel.topmst"))
		{	
			String TopicKeyid=request.getParameter("TopicKeyid");
			httpSession.removeAttribute("TopicDetailsCommonFilter");
		    CommonFilter commonFilter = populateCommonFilter(request,"TopicDetailsCommonFilter",false);
			String tmpFromRow = commonFilter.getFromRow();
			JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Topic Master");
		    String format = ExcelUtils.getFormat(request);
		    commonFilter.setKey(TopicKeyid);
			Workbook wb = topicEntryMasterService.TopicExportExcel(commonFilter,colmodel,format);
			commonFilter.setFromRow(tmpFromRow);
			//ExcelUtils.writeToResponse(response,wb,"TaskKsaReport", format);
			ExcelUtils.writeToResponse(response,wb,"TopicMaster", format);
		}	

	   else if( action.equals("topi_save.topmst"))
		{	
			//CommonMessage.debugMsg("inside sace action");
			EntTlTopicmstBean entTlTopicmstBean = new EntTlTopicmstBean();
			saveTopic(request,response,entTlTopicmstBean);
	    }
	   else if( action.equals("TopicEntryMaster_save.topmst"))
		{	
			//CommonMessage.debugMsg("inside sace action");
			EntTlTopicmstBean entTlTopicmstBean = new EntTlTopicmstBean();
			saveTopic(request,response,entTlTopicmstBean);
	    }
	 
	   else if(action.equals("TopicListDetl_remove.topmst"))
	   {
		  DeleteRecordstopicList(request, response);
	   }
	   else if(action.equals("TopicEntryMaster_delete.topmst"))
		{
			deleteSkil(request,response);
		}
	   else if(action.equals("TopicRecall_input.topmst"))
	   {
		    String Keyid = request.getParameter("keyId");
			PrintWriter out = response.getWriter();
			List<String[]> details = topicEntryMasterService.getSql(Keyid);
			String createdon = details.get(0)[16];
			httpSession.setAttribute("exist", createdon);
			CommonMessage.debugMsg("flid"+details.get(0)[1]);
			out.print( JSONArray.fromCollection(details));
	   }
	   else if(action.equals("deliveryMode_Combo.topmst"))
	   {
		    ComboFilter comboFilter = new ComboFilter();
			comboFilter = UIUtils.fillComboFilter(request);
			List<ComboBox>  getSkillKey = topicEntryMasterService.getDeliveryModeCombo(comboFilter);
			UIUtils.writeComboBox(response, getSkillKey,comboFilter);			
	   }
	   else if(action.equals("functionalLoc.topmst"))
		{
		   FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			//fact// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setLocnMandatory(true);	
			functLocFieldNameBean.setFactMandatory(false);			
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			/*functLocFieldNameBean.setPbuDisable(true);
			functLocFieldNameBean.setSbuDisable(true);
			functLocFieldNameBean.setFactDisable(true);
			functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);*/
			FormModes formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}
	}
	private void deleteSkil(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		//String elementId = request.getParameter("txtTopiKeyid");			
	    	
		PrintWriter out = response.getWriter();
		EntTlTopicmst entTlTopicmst = new EntTlTopicmst();
		entTlTopicmst=(EntTlTopicmst)UIUtils.setBeanProperties((Object)entTlTopicmst,request);
		
		try {
			entTlTopicmst = topicEntryMasterService.delete(entTlTopicmst);			
			JSONObject successData = new JSONObject();
			successData.put("msg","Data Deleted Successfully ");
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());			
		}
		catch(ValidationExceptions e)
		{
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "EntTlTopicmst");			
			out.print(errMessage.toString());			
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("BusinessApplicationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"EntTlTopicmst");			
			errMessage.put("tpmException", "Topic Name Already Referred");
			errMessage.put("displyMsg", false);			
			out.print(errMessage.toString());
		}
		catch(Exception e)
		{				
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());			
		}
		
	}

	private void DeleteRecordstopicList(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonMessage.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				topicEntryMasterService.DeleteTopiclist(keyid);
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
		}		
	}

	private void saveTopic(HttpServletRequest request, HttpServletResponse response,EntTlTopicmstBean entTlTopicmstBean  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		//CommonMessage.debugMsg("inside saveSkil");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		try{
	    	if( httpSession != null && user != null)
	    	{	
	    		EntTlTopicmst existEntTlTopicmst = (EntTlTopicmst)httpSession.getAttribute("entTlTopicmst");	
	    		
	    		EntTlTopicmst newEntTlTopicmst = new EntTlTopicmst();	 
	    		String elementid=CommonFunctions.getLoginElementId(request);
			      //  CommonMessage.debugMsg("login element id"+elementid);
			        String location=null;
			        if(elementid.length()>10){
			        location=elementid.substring(11,21);
			        //CommonMessage.debugMsg("locationid"+locationid);
			        }
	    	   // String location=CommonFunctions.getLoginLocaton(request);
	    	   CommonMessage.debugMsg("The LocationId:::"+location);
	    	   
	    	   String locationFlid=request.getParameter("cmbTopiLocationid");
	    	    String locationId=topicEntryMasterService.getLocationid(location);
	    	    CommonMessage.debugMsg(locationFlid+ "  The LocationId"+locationId);
	    		newEntTlTopicmst.setTopiCreatedby(user.getUsrm_ccno());
	    		newEntTlTopicmst =(EntTlTopicmst)UIUtils.setBeanProperties((Object)newEntTlTopicmst,request);
				//String KeyID = (String) httpSession.getAttribute("TopicKeyId");
				String trRoleid = request.getParameter("cmbRoleKeyid");
				//CommonMessage.debugMsg("trRoleid   "+trRoleid);
				//if( !UIUtils.isValidKeyId(newEntTlTopicmst.getTopiKeyid()))
				//newEntTlTopicmst.setTopiKeyid(KeyID);//KeyId
				//CommonMessage.debugMsg("  KeyID   "+KeyID);
				newEntTlTopicmst.setTrRoleid(trRoleid);
				newEntTlTopicmst.setTopiLocationid(locationFlid);
				CommonMessage.debugMsg("trRoleid   "+newEntTlTopicmst.getTrRoleid());
				entTlTopicmstBean =(EntTlTopicmstBean) UIUtils.setBeanProperties((Object)entTlTopicmstBean,request);			
				String saveMsg ;
				//CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()" +  newQtmTlDockaudit.getQaudkeyid());
				if( newEntTlTopicmst.getTopiKeyid()== null )
				{	
					CommonMessage.debugMsg("key id is not available");
					//entTlTopicmstBean.setFormMode(FormModes.create);
					existEntTlTopicmst =	 topicEntryMasterService.create(newEntTlTopicmst,existEntTlTopicmst,trRoleid);					
					saveMsg = "Data Saved Successfully";
				}	
				else{
					CommonMessage.debugMsg("key id is available:" + newEntTlTopicmst.getTopiKeyid() );
					entTlTopicmstBean.setFormMode(FormModes.modify);
					existEntTlTopicmst =	 topicEntryMasterService.update(newEntTlTopicmst,existEntTlTopicmst,trRoleid);
					saveMsg = "Data Updated Successfully";
				}
				
				httpSession.setAttribute(existEntTlTopicmst.getTopiKeyid(), existEntTlTopicmst);
				httpSession.setAttribute("entTlTopicmst", existEntTlTopicmst);
				String formBeanIdentifier = "entTlTopicmstBean"+entTlTopicmstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,entTlTopicmstBean);
				
				JSONObject returnData = new JSONObject();			
				JSONObject successData = new JSONObject();		
				CommonMessage.debugMsg("Save completed");
				String openFileMgr = request.getParameter("openFileManager");
				if(UIUtils.isValidKeyId(openFileMgr))
					returnData.put("openFileMgr","Y");	
				returnData.put("keyId",existEntTlTopicmst.getTopiKeyid());
				successData.put("msg", saveMsg);
				returnData.put("formClear",true);	
				returnData.put("successData",successData);
				out.print(returnData.toString());
	    	 }		    	
		}
		catch(ValidationExceptions e)
		{
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "TopicEntrymst");
			out.print(errMessage.toString());
			
		}
		catch(BusinessApplicationExceptions e)
		{   
			
			CommonMessage.debugMsg("Error Servler e -"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "TopicEntrymst");
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

	private void TopicEntryDetailgetCol(HttpServletRequest request,	HttpServletResponse response) throws IOException
	{
		
		try{
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg("test input action meeting form.............");			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","TopicEntryDtl"));
			CommonMessage.debugMsg("MoMeetingMom_getCol.mom  end ");
			HttpSession httpSession = request.getSession(false);
			httpSession.removeAttribute("TopicReport");
			httpSession.setAttribute("TopicReport", UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting","TopicEntryDtl"));
			

			}catch(Exception e){
				CommonMessage.debugMsg(e.getMessage());
			}
		
	}
	private void TopicEntryDetailgetData(HttpServletRequest request,HttpServletResponse response) throws IOException {
			
		try
		{  
			HttpSession httpSession = request.getSession(false);
			UIUtils.displayRequestParamsValue(request);	
			String TopicKeyid=request.getParameter("TopicKeyid");
			CommonMessage.debugMsg("The TopicID:"+TopicKeyid);
			String search = request.getParameter("_search");
			httpSession.removeAttribute("TopicDetailsCommonFilter");
		    CommonFilter commonFilter = populateCommonFilter(request,"TopicDetailsCommonFilter",false);
		    int rowCount = topicEntryMasterService.selectCount(commonFilter);
			CommonMessage.debugMsg("count...."+rowCount);
			Long totalCnt = (long)rowCount;
			commonFilter.setTotalRecordCnt(totalCnt);
			
			List<String[]> TopicList  = topicEntryMasterService.getTopicDet(commonFilter,TopicKeyid);
			if(search.equals("true"))
			{
				rowCount = TopicList.size();
				totalCnt = (long)rowCount;
				commonFilter.setTotalRecordCnt(totalCnt);
			}
			
			CommonMessage.debugMsg(totalCnt+" totalCnt " + TopicList.size()); 			 	
			JSONObject TopicReqData = UIUtils.convertToJqGridTableObject(TopicList,request,0,1,commonFilter.getTotalRecordCnt());
			PrintWriter out = response.getWriter();
			out.println(TopicReqData);  			 	 			 	
			httpSession.removeAttribute("TopicDetailsCommonFilter");
			httpSession.setAttribute("TopicDetailsCommonFilter", TopicReqData);

	    }catch(Exception e)
		{
			CommonMessage.debugMsg(e.getMessage());
		}
		
	}
	
	
	

		private JSONObject getTableModel(List<String[]> headers ,HttpServletRequest request) 
		{
			
			
				CommonMessage.debugMsg("getTableModel");
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	         	String [] colHeader = headers.get(0);
				jqGridTableModel.setTableButton(true);		
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setTableHeight(100);
				jqGridTableModel.setTableWidth(1000);
				jqGridTableModel.setSortable(false);
				jqGridTableModel.setEnableFilter(true);
				
			for(int i =0; i < colHeader.length; i++)
			{			
				JqGridColModel jqGridColModel = new JqGridColModel();	
				jqGridColModel.setWidth( 250);
				jqGridColModel.setIndex("");
				jqGridColModel.setName("");
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(true);
				if(i==0)                                       
				{
					jqGridColModel.setHidden(false);				
				}
				if(i==1)
				{
					jqGridColModel.setHidden(false); 
				}
			    if(i==2)
			    {
			    	jqGridColModel.setWidth(550);			
			    }			    
			    if(i==3)
			    {
			    	jqGridColModel.setWidth(80);	
			    	jqGridColModel.setAlign("right");
			    }
				if(i==4)
				{
					jqGridColModel.setHidden(false);
				}
				
				jqGridTableModel.getColModel().add(jqGridColModel);
						
		}
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 tableModel.set("tableHeight", "30%%");
			 tableModel.set("tableWidth", "107%%");
			 return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
				
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(true);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		
		if(groupbyfield)
		{
			jqGridColModel.setSummaryType("sum");
			jqGridColModel.setSummaryTpl("<b><font >  {0} </font> </b>");
		}
		return jqGridColModel;
	}

		

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {

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
