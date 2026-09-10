package com.akranta.tpm.controller;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlCompanymstBean;
import com.akranta.tpm.bean.GenTlCompanymstBean;
import com.akranta.tpm.dao.impl.CommonFunctions;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCompanymst;


import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenTlCompanymstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlCompanymstServiceImpl;
import com.akranta.tpm.service.impl.PcsTlCycletimemstServiceImpl;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;


		public class BAL_CompanyMstServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
			BAL_GenTlCompanymstService  genTlCompanymstService ;
			
		
	    public BAL_CompanyMstServlet() {
	        super();
	/*        	System.out.println(" initialising servlet ....");
	        try {
	        	genTlCompanymstService = new GenTlCompanymstServiceImpl();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	  */      // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
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
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
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
	    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
	    	try {
	    		genTlCompanymstService = (BAL_GenTlCompanymstServiceImpl)BAL_UIUtils.getServiceObject(request,"GenTlCompanymstServiceImpl");
			} catch (ServiceObjectCreationException e) {
				com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
			}
			if( httpSession != null && user != null)
	    	{
				String uri = request.getRequestURI();
	
				int lastIndex = uri.lastIndexOf("/"); 	
				String action = uri.substring(lastIndex + 1);
				
				response.setContentType("text/html");
				response.setContentType("text/json");
			
				boolean delete = false;	
				String dispatchUrl = null; 
					
				if (action.equals("company_input.comp")) 
				{
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					BAL_GenTlCompanymst genTlCompanymst = null;
					
					httpSession.removeAttribute("genTlCompanymstServlet");
					
					if( ( BAL_UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						
						genTlCompanymst = genTlCompanymstService.select(keyid);
						httpSession.setAttribute("genTlCompanymstServlet" , genTlCompanymst);
						request.setAttribute("genTlCompanymst", genTlCompanymst);
					}
					//mode=FormModes.view;
					
					GenTlCompanymstBean genTlCompanymstBean = new GenTlCompanymstBean(mode,lockFields);
					
					httpSession.setAttribute("genTlCompanymstBean", genTlCompanymstBean);
					request.setAttribute("genTlCompanymstBean", genTlCompanymstBean);
					request.setAttribute("genTlCompanymstServlet", genTlCompanymst);

					dispatchUrl = "/pages/GenTlCompanymst.jsp";
				}
		/*		else if( action.equals("getModecompany_input.comp")){
					response.setContentType("text/html");
					//response.setContentType("text/json");
					
					PrintWriter out = response.getWriter();
					JSONObject result = new JSONObject();
					
					result.put("mode","create");
					result.put("url","company_input.comp");
					result.put("formHeader","Company Master");
					
					out.println(result);
					out.close();
				}
		*/		else if(action.equals("company_save.comp"))
				{	
					
			BAL_GenTlCompanymstBean genTlCompanymstBean = (BAL_GenTlCompanymstBean)httpSession.getAttribute("genTlCompanymstBean");
				    saveComp(request,response,genTlCompanymstBean);
				}
						
				else if( action.equals("company_delete.comp"))
				{	
					 
					ServletOutputStream out = response.getOutputStream();
					delete = false;
					if(delete ){	
					 
						GenTlCompanymstBean genTlCompanymstBean = (GenTlCompanymstBean)httpSession.getAttribute("genTlCompanymstBean");
						deleteComp(request,response,genTlCompanymstBean);
					
					}else{ 
						 
						 user = BAL_UIUtils.getLoginUser(request);
						 
						 JSONObject successData = new JSONObject();
						 successData.put("msg","Deletion is Restricted");
					 
						 //successData.put("mode",genTlFactorymstBean.getFormMode() );
						 //successData.put("keyId", existGenTlFactorymst.getFactKeyid());
						 JSONObject returnData = new JSONObject();					 		
						 returnData.put("successData", successData);
						 returnData.put("formClear",  false);
						 out.print(returnData.toString());
					}	 
				}
			   else if(action.equals("company_recall.comp"))
				{	
					
					ServletOutputStream out = response.getOutputStream();
					String company =request.getParameter(ReqtParamNameConst.KEYID);
					
					GenTlCompanymstBean genTlCompanymstBean = new GenTlCompanymstBean(FormModes.modify);
					
					httpSession.removeAttribute("genTlCompanymstBean");
					httpSession.setAttribute("genTlCompanymstBean", genTlCompanymstBean);
					
					httpSession.removeAttribute("genTlCompanymstServlet");
					BAL_GenTlCompanymst genTlCompanymst = genTlCompanymstService.select(company);
					
					httpSession.setAttribute("genTlCompanymstServlet" ,genTlCompanymst);
					
					JSONObject  compdata =  BAL_UIUtils.fromTpmModel(genTlCompanymst);
					JSONObject returndata = new JSONObject();
					returndata.put("compdata", compdata);
					out.print(returndata.toString());
					
				}	
		
			
				if (dispatchUrl != null)
				{
					BAL_UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	    	}
		}	
	    private void saveComp(HttpServletRequest request, HttpServletResponse response,BAL_GenTlCompanymstBean genTlCompanymstBean  ) throws IOException{
			
	    	
	    	HttpSession httpSession = request.getSession(false);
	
	    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
				BAL_GenTlCompanymst newGenTlCompanymst = new BAL_GenTlCompanymst();
				newGenTlCompanymst.setCompCreatedby(user.getUsrm_ccno());
				if( genTlCompanymstBean == null)
					genTlCompanymstBean = new BAL_GenTlCompanymstBean(FormModes.create);
			
				
				newGenTlCompanymst =(BAL_GenTlCompanymst)BAL_UIUtils.setBeanProperties((Object)newGenTlCompanymst,request);
				//genTlCompanymstBean =(GenTlCompanymstBean) UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
				BAL_GenTlCompanymst existGenTlCompanymst = (BAL_GenTlCompanymst)httpSession.getAttribute("genTlCompanymstServlet");
				
				try{
					boolean insert = true;
					if( ! BAL_UIUtils.isValidKeyId( newGenTlCompanymst.getCompKeyid() ))
					{	
						existGenTlCompanymst = genTlCompanymstService.create(newGenTlCompanymst,existGenTlCompanymst,genTlCompanymstBean);
					}
					else{
						insert = false;
						existGenTlCompanymst = genTlCompanymstService.update(newGenTlCompanymst,existGenTlCompanymst,genTlCompanymstBean);
					}
					
					
					
					
					
				
					JSONObject mode = new JSONObject();
				//	mode.put("formMode",genTlCompanymstBean.getFormActionMode());
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("CompKeyid",existGenTlCompanymst.getCompKeyid() );
					 
					JSONObject successData = new JSONObject();
				    String msgPropertyIdnt;
					
				 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						msgPropertyIdnt = "success-update";
				    successData.put("msg",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",genTlCompanymstBean.getFormActionMode() );
					successData.put("keyId", existGenTlCompanymst.getCompKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);
						
					httpSession.removeAttribute("genTlCompanymstServlet");
					//httpSession.setAttribute( "genTlCompanymstServlet", existGenTlCompanymst);
					//httpSession.removeAttribute("genTlCompanymstBean");
					
			    	PrintWriter  out = response.getWriter();
					out.print(returnData.toString());
					out.close();
					 
				}catch(ValidationExceptions e)
				{
					PrintWriter  out = response.getWriter();
					JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),"companycreation");
					errMessage.put("formActionMode",genTlCompanymstBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}catch(BusinessApplicationExceptions e)
				{ 
					PrintWriter  out = response.getWriter();
					System.out.println("BusinessApplicationExcepions"  );
					JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(e.toString(),  "companycreation");
					out.print(errMessage.toString());
					
				}	
				catch(Exception e)
				{
					PrintWriter  out = response.getWriter();
					System.out.println("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					err.put("tpmException",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
					out.print(err.toString());
				}
	    	}	
	    }
	    private void deleteComp(HttpServletRequest request,HttpServletResponse response, GenTlCompanymstBean genTlCompanymstBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		BAL_GenTlCompanymst existGenTlCompanymst = (BAL_GenTlCompanymst)httpSession.getAttribute("genTlCompanymstServlet"); 
	    		BAL_GenTlCompanymst newGenTlCompanymst = new BAL_GenTlCompanymst();
	    		newGenTlCompanymst.setCompCreatedby(user.getUsrm_ccno());
	    		System.out.println("old id::::"+existGenTlCompanymst);
	    		newGenTlCompanymst =(BAL_GenTlCompanymst)BAL_UIUtils.setBeanProperties((Object)newGenTlCompanymst,request);
	    		genTlCompanymstBean =(GenTlCompanymstBean)BAL_UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
	    		genTlCompanymstBean =(GenTlCompanymstBean) BAL_UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
	    
			try{
								
				existGenTlCompanymst = genTlCompanymstService.delete(newGenTlCompanymst);						
				
				httpSession.setAttribute(existGenTlCompanymst.getCompKeyid(), existGenTlCompanymst);
				httpSession.setAttribute("GenTlCompanymst", existGenTlCompanymst);
				String formBeanIdentifier = "GenTlCompanymstBean"+genTlCompanymstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlCompanymstBean);
							
				JSONObject mode = new JSONObject();
				mode.put("formMode",genTlCompanymstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("CompKeyid",existGenTlCompanymst.getCompKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject successData = new JSONObject();
				successData.put("msg",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				//successData.put("msg","Data Deleted Successfully");
				successData.put("mode",genTlCompanymstBean.getFormActionMode() );
				successData.put("keyId", existGenTlCompanymst.getCompKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);		
				out.print(returnData.toString());
					
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),"companycreation");
				errMessage.put("fromMode",genTlCompanymstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{
				System.out.println("Error Servler e -"+e.toString());
				JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(e.toString(),  "companycreation");
				out.print(errMessage.toString());
				System.out.println(" e " + errMessage );
					
			}catch(Exception e)
			{
				System.out.println("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Deleted");
				err.put("tpmException",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    }
    	
	}
}