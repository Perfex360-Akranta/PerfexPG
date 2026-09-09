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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
//import com.akranta.tpm.bean.CliTlStandardFormBean;
import com.akranta.tpm.bean.GenTlMchrankskillmstBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
//import com.akranta.tpm.model.GenTlCoversionmatrixdtl;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PlmTlCriteriamst;
//import com.akranta.tpm.model.CliTlStandards;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlMchrankskillmst;
//import com.akranta.tpm.model.PlmTlMultiplemethodsmst;
//import com.akranta.tpm.model.PlmTlToolsdtl;
//import com.akranta.tpm.service.EquipmentReportService;
import com.akranta.tpm.service.GenTlMchrankskillmstService;
import com.akranta.tpm.service.impl.GenTlMchrankskillmstSeviceImpl;
//import com.akranta.tpm.service.impl.EquipmentReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MachineRnkSkllServlet  extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
	GenTlMchrankskillmstService genTlMchrankskillmstService;
	
	public MachineRnkSkllServlet()
	{
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		try {
			genTlMchrankskillmstService= (GenTlMchrankskillmstSeviceImpl)UIUtils.getServiceObject(request,"GenTlMchrankskillmstSeviceImpl");
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		String dispatchUrl =null;
		
		if (action.equals("mchrnkskill_input.mchrnkskl")) 
		{ 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/MachineRnkSkill.jsp"); 				
			rd.forward(request, response);
		}
		else if (action.equals("criteriamaster_input.mchrnkskl")) 
		{ 
			String flId=request.getParameter("flid");
			request.setAttribute("flid", flId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/CriteriaMaster.jsp"); 				
			rd.forward(request, response);
		}
		else if (action.equals("criteriamaster_getCol.mchrnkskl")){
			getColCriteriaList(request, response, httpSession);
		}
		
		else if (action.equals("criteriamaster_getData.mchrnkskl")){
			getDataCriteriaList(request, response, httpSession);
		}
		else if(action.equals("criteriamasterPopup_input.mchrnkskl")){
			String flId=request.getParameter("flid");
			request.setAttribute("flid", flId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/CriteriaMasterPopup.jsp"); 				
			rd.forward(request, response);
		}
		else if (action.equals("criteriamasterPopup_getCol.mchrnkskl")){
			getColCriteriaList(request, response, httpSession);
		}
		
		else if (action.equals("criteriamasterPopup_getData.mchrnkskl")){
			getDataCriteriaList(request, response, httpSession);
		}
		
		else if( action.equals("criteriamaster_save.mchrnkskl") ){				
			saveCriteria(request,response);         		
		}
		else if( action.equals("criteriamaster_delete.mchrnkskl")){				
			deleteCriteriaMst(request, response);       		
		}
		else if( action.equals("mchrnkskill_fillcombo.mchrnkskl")){
			try 
			{
				ComboFilter comboFilter = UIUtils.fillComboFilter(request);
			   	CommonFilter commonFilter=new CommonFilter();
				List<ComboBox> mchrnkskilllist = genTlMchrankskillmstService.getIncidentTypeComboList(commonFilter);
				UIUtils.writeComboBox(response, mchrnkskilllist,comboFilter);
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
		}
		
		else if (action.equals("mchrnkskill_getCol.mchrnkskl")){
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GenTlMchrankskillmstprpty", "colModel"));
		}
		
		else if (action.equals("mchrnkskill_getData.mchrnkskl")){
			try
			{
				PrintWriter out = response.getWriter();
				List<String[]> mchrnkskilllist;				
				mchrnkskilllist  = genTlMchrankskillmstService.getAllMchrankskill();				
				JSONObject mchrnkskillData = UIUtils.convertToJqGridTableObject(mchrnkskilllist,request,0,0); 
				out.println(mchrnkskillData);
  		
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		else if (action.equals("mchrnkskill_save.mchrnkskl")){			
			GenTlMchrankskillmstBean genTlMchrankskillmstBean = (GenTlMchrankskillmstBean) httpSession.getAttribute("phenBean");
			saveMchrnkskill(request,response,genTlMchrankskillmstBean);
		}
		
		else if (action.equals("mchrnkskill_delete.mchrnkskl")){
			GenTlMchrankskillmstBean genTlMchrankskillmstBean = (GenTlMchrankskillmstBean) httpSession.getAttribute("phenBean");
			deleteMchrnkskill(request,response,genTlMchrankskillmstBean);
		}
	}
	
	private void saveCriteria(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession httpSession = request.getSession(false);
    	PrintWriter out = response.getWriter();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	CommonMessage.debugMsg("create saveCriteria");
    	try{    		
    		if( httpSession != null && user != null){
	    		PlmTlCriteriamst existPlmTlCriteriamst = (PlmTlCriteriamst)httpSession.getAttribute("plmTlCriteriamst");
	    		PlmTlCriteriamst newePlmTlCriteriamst= new PlmTlCriteriamst();	
	    		newePlmTlCriteriamst=(PlmTlCriteriamst)UIUtils.setBeanProperties((Object)newePlmTlCriteriamst,request);	
	    	    newePlmTlCriteriamst.setCriaCreatedby(user.getUsrm_ccno());	
	    		String flid =  (String)httpSession.getAttribute("flid");
	    		
	    		List<PlmTlCriteriamst> CrtieriaGridList1 = null;	
				JSONArray criticalJson = null;
				String saveMsg = null ;
				if(!UIUtils.isValidKeyId(newePlmTlCriteriamst.getCriaKeyid())){
					 existPlmTlCriteriamst= genTlMchrankskillmstService.create( newePlmTlCriteriamst,existPlmTlCriteriamst);
				     saveMsg = "Data Saved Successfully";
			    }
				else {
					 existPlmTlCriteriamst= genTlMchrankskillmstService.update( newePlmTlCriteriamst,existPlmTlCriteriamst);
					 saveMsg = "Data Updated Successfully";
				}
				JSONObject returnData = new JSONObject();				
				JSONObject successData = new JSONObject();	
				successData.put("msg", saveMsg);
				returnData.put("formClear",false);	
				returnData.put("successData",successData);
				out.print(returnData.toString());	
				CommonMessage.debugMsg("create end");
			}		
    	}catch(Exception e){
    	}		
	}
	
	private void saveMchrnkskill(HttpServletRequest request,HttpServletResponse response, GenTlMchrankskillmstBean genTlMchrankskillmstBean) throws Exception{
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	if(httpSession != null && user != null)
    	{
    		GenTlMchrankskillmst newGenTlMchrankskillmst = new GenTlMchrankskillmst();
	    	newGenTlMchrankskillmst=(GenTlMchrankskillmst)UIUtils.setBeanProperties((Object)newGenTlMchrankskillmst,request);
	    	GenTlMchrankskillmst existGenTlMchrankskillmst = new GenTlMchrankskillmst();
			
	    	newGenTlMchrankskillmst.setMrskCreatedby(user.getUsrm_ccno());
	    	
			httpSession.getAttribute("existmachinerankskill");
			
			CommonMessage.debugMsg("Save Employee 2 " +newGenTlMchrankskillmst.getMrskKeyid()); 
			
	    	 try
	    	 {
	    		if( newGenTlMchrankskillmst.getMrskKeyid()==null )
				{		
	    			CommonMessage.debugMsg(newGenTlMchrankskillmst.getMrskKeyid());
	    			existGenTlMchrankskillmst= genTlMchrankskillmstService.create(newGenTlMchrankskillmst,existGenTlMchrankskillmst,genTlMchrankskillmstBean);
	    			CommonMessage.debugMsg("create complete");
				}
	    		
	    		else
				{
					
	    			existGenTlMchrankskillmst= genTlMchrankskillmstService.update(newGenTlMchrankskillmst,existGenTlMchrankskillmst,genTlMchrankskillmstBean);
				
				}
	    		
	    		JSONObject successData=new JSONObject();
	    		JSONObject mchrankskillDataSave=new JSONObject();
	    		String savemsg;
	    	   if( newGenTlMchrankskillmst.getMrskKeyid()==null )
				{
	    		   
					savemsg= " Data Not saved ";
					
				}
				else
				{
					
					savemsg= "Data saved succesfully";
					
				}
	    		successData.put("msg", savemsg);
	    		mchrankskillDataSave.put("successData", successData);
	    		out.print(mchrankskillDataSave.toString());
	    		
	    	}catch(ValidationExceptions e)
			{
	            
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"GenTlMchrankskillmstprpty");
				CommonMessage.debugMsg("e.toString()"+e.toString());
				out.print(errMessage.toString());
	    	}
    		catch(Exception e)
    		{				
			
				
    		}
    	}
	}
	
	private void deleteMchrnkskill(HttpServletRequest request,HttpServletResponse response, GenTlMchrankskillmstBean genTlMchrankskillmstBean) throws BusinessApplicationExceptions, Exception{
		CommonMessage.debugMsg("delete");
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try{
    		if(httpSession !=null && user !=null)
    		{
        		GenTlMchrankskillmst newGenTlMchrankskillmst= new GenTlMchrankskillmst();
        		newGenTlMchrankskillmst=(GenTlMchrankskillmst)UIUtils.setBeanProperties((Object)newGenTlMchrankskillmst,request);
        		CommonMessage.debugMsg(newGenTlMchrankskillmst.getMrskKeyid());
    			if(UIUtils.isValidKeyId(newGenTlMchrankskillmst.getMrskKeyid())){
    				
					newGenTlMchrankskillmst=genTlMchrankskillmstService.delete(newGenTlMchrankskillmst);
					httpSession.removeAttribute("GenTlMchrankskillmst"+newGenTlMchrankskillmst.getMrskKeyid());
								
					JSONObject err = new JSONObject();
					JSONObject successData = new JSONObject();
					err.put("formClear",true);	
					
					err.put("successData","Data Deleted Successfully");
					out.print(err.toString());
   
    	    	}
    		}
    	}
    	
    	/*catch(ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"GenTlMchrankskillmst");
			errMessage.put("fromMode",genTlMchrankskillmstBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}*/
    	catch(BusinessApplicationExceptions e)
		{
			//JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"GenTlMchrankskillmstprpty");
			String msg=e.toString();
			CommonMessage.debugMsg(msg);
			if(msg!= null && msg.contains("ORA-02292") && msg.contains("FK_") )
			{	
				//msg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm");
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.GenTlMchrankskillmstprpty","FKRefError");
				CommonMessage.debugMsg(mesg);
				JSONObject err = new JSONObject();
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}	
		}
		catch(Exception e){
			JSONObject err = new JSONObject();
			err.put("successData", "Data Not Deleted");
			out.print(err.toString());
		}
    	
	}
	private void getColCriteriaList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		PrintWriter out = response.getWriter();
		String flid =request.getParameter("flid");
		CommonFilter commonFilter = new CommonFilter();
		CommonMessage.debugMsg("flid"+flid);
		commonFilter.setFlid(flid);
		JSONObject jsonObject = new JSONObject();
		List<String[]> projectListGrid = null;		
		try {						
			FilterValues.getCommonFilters(request, commonFilter);
			projectListGrid =  genTlMchrankskillmstService.getCriteriaMstList(commonFilter);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}			 
		 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		 GridColModel gridColModel = new GridColModel();			
		 gridColModel.setHeaderNum(1);
		 
		 jqGridTableModel.setSortable(false);			 
		 jqGridTableModel.setTableButton(false);
		 jqGridTableModel.setEnableFilter(true);
		 jqGridTableModel.setRowNumbers(true);
		 String [] colHeaderHead = projectListGrid.get(0);
		 String [] colHeader = projectListGrid.get(1);
		 List<String[]> headers = new ArrayList<String[]>();	
		 headers.add(colHeader);
		 
		 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
   	     jsonObject.set("tableWidth", "65%%");
     	 jsonObject.set("tableHeight", "50%%");
   	     httpSession.removeAttribute("criteriaMstColModel");
		 httpSession.setAttribute("criteriaMstColModel",jsonObject);	
		 CommonMessage.debugMsg("jsonObject " + jsonObject);
		 out.println(jsonObject);
	}
	
	private void getDataCriteriaList(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws IOException{
		CommonMessage.debugMsg("getDataFmeaMstList");	
		try {
			CommonFilter commonFilter = populateCommonFilter(request,"CriteriaMstCommonFilter",true);
			FilterValues.getCommonFilters(request, commonFilter);
			String flid =request.getParameter("flid");
			CommonMessage.debugMsg("flid"+flid);
			commonFilter.setFlid(flid);
			List<String[]> MasterGrid = genTlMchrankskillmstService.getCriteriaMstList(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request,2,0);
			out.println(ResourceGridmod);
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
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
  			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
  		return commonFilter;
	}
	private void deleteCriteriaMst(HttpServletRequest request,HttpServletResponse response) throws Exception {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		PlmTlCriteriamst oldPlmTlCriteriamst=new PlmTlCriteriamst();

    		oldPlmTlCriteriamst=(PlmTlCriteriamst)UIUtils.setBeanProperties((Object)oldPlmTlCriteriamst,request);
    		JSONObject successData=new JSONObject();
    		JSONObject returnData=new JSONObject();
    		String savemsg;
			
			if(UIUtils.isValidKeyId(oldPlmTlCriteriamst.getCriaKeyid())){
				oldPlmTlCriteriamst=genTlMchrankskillmstService.delete(oldPlmTlCriteriamst);
				savemsg=" Data Deleted succesfully";
			}
		
			else
			{
				savemsg= "Data Not Deleted  ";
				
			}
			successData.put("msg", savemsg);
        	returnData.put("successData",successData);
    		out.print(returnData.toString());
		}
		}
		catch(Exception e)
		{
			
		}
	}
}

		