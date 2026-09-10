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

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;

import com.akranta.tpm.bean.BAL_GenTlCompanymstBean;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCompanymst;


import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenTlCompanymstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlCompanymstServiceImpl;
//import com.akranta.tpm.service.impl.PlmTlGenmaintenanceServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;


		public class BAL_GenTlCompanymstServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
			BAL_GenTlCompanymstService  genTlCompanymstService ;
			
		
	    public BAL_GenTlCompanymstServlet() {
	        super();
/*	        	System.out.println(" initialising servlet ....");
	        try {
	        	genTlCompanymstService = new GenTlCompanymstServiceImpl();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	        // TODO Auto-generated constructor stub
	         * 
	         */
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
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
			if( httpSession != null && user != null)
	    	{
				String action = UIUtils.getActionPart(request);
				try {
					genTlCompanymstService = (BAL_GenTlCompanymstServiceImpl)UIUtils.getServiceObject(request,"GenTlCompanymstServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonFunctions.debugMsg(e);
				}
					
				String dispatchUrl = null; 
					
				if (action.equals("company_input.cmpy")) 
				{
					String keyid = request.getParameter("keyId");
					String userEvent = request.getParameter("userEvent");
					System.out.println(" userEvent  " + userEvent);
					response.setContentType("text/html");
					
					if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						BAL_GenTlCompanymst genTlCompanymst = genTlCompanymstService.select(keyid);
						httpSession.setAttribute("genTlCompanymst" + keyid, genTlCompanymst);
						request.setAttribute("genTlCompanymst", genTlCompanymst);
					}
				
					dispatchUrl = "/pages/GenTlCompanymst.jsp";
				}
			
				else if(action.equals("company_save.cmpy"))
				{	
							
					BAL_GenTlCompanymstBean genTlCompanymstBean = new BAL_GenTlCompanymstBean();
					
				    saveComp(request,response,genTlCompanymstBean);
				}
						
				else if( action.equals("company_del.cmpy"))
				{	
					System.out.println("Inside the DELETE");
					BAL_GenTlCompanymstBean genTlCompanymstBean = new BAL_GenTlCompanymstBean();
					
					DeleteComp(request,response,genTlCompanymstBean);
					
			    }
			
			   else if(action.equals("company_recall.cmpy"))
				{	
					System.out.println("the value of comp key id is   :"+request.getParameter("Comp"));
					ServletOutputStream out = response.getOutputStream();
					String company =request.getParameter("Comp");
					BAL_GenTlCompanymst genTlCompanymst = genTlCompanymstService.select(company);
					System.out.println("company (()()()())mstData    :="+request.getParameter("Comp"));
					httpSession.setAttribute("genTlCompanymst" + company ,genTlCompanymst);
					JSONObject  compdata =  UIUtils.fromTpmModel(genTlCompanymst);
					System.out.println("inside action" + compdata );
					JSONObject returndata = new JSONObject();
					returndata.put("compdata", compdata);
					out.print(returndata.toString());
					
				}	
		
			
				if (dispatchUrl != null)
				{
					UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	    	}
		}	
	    private void saveComp(HttpServletRequest request, HttpServletResponse response,BAL_GenTlCompanymstBean genTlCompanymstBean  ) throws IOException{
			
	    	
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		
	    		BAL_GenTlCompanymst newGenTlCompanymst = new BAL_GenTlCompanymst();
				newGenTlCompanymst.setCompCreatedby(user.getUsrm_ccno());
				
				System.out.println("request keydi = " + request.getParameter("txtCompName") );
				
				newGenTlCompanymst =(BAL_GenTlCompanymst)UIUtils.setBeanProperties((Object)newGenTlCompanymst,request);
				genTlCompanymstBean =(BAL_GenTlCompanymstBean) UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
				BAL_GenTlCompanymst existGenTlCompanymst = (BAL_GenTlCompanymst)httpSession.getAttribute("genTlCompanymst" + newGenTlCompanymst.getCompKeyid()); 
				System.out.println("MAK name  :"+newGenTlCompanymst.getCompName());
				
				System.out.println("MAK name  :"+newGenTlCompanymst.getCompCode());
				try{
					System.out.println("  newgenTlCompanymst.getcompKeyid() " +  newGenTlCompanymst.getCompKeyid());
				if( newGenTlCompanymst.getCompKeyid() == null )
				{	System.out.println("  servletsave " );
					existGenTlCompanymst = genTlCompanymstService.create(newGenTlCompanymst,existGenTlCompanymst,genTlCompanymstBean);
				}
				else{
					System.out.println("  servletupdatee " );	
					existGenTlCompanymst = genTlCompanymstService.update(newGenTlCompanymst,existGenTlCompanymst,genTlCompanymstBean);
				}
					
				httpSession.setAttribute( "genTlCompanymst" +  existGenTlCompanymst.getCompKeyid(), existGenTlCompanymst);
				httpSession.setAttribute("genTlCompanymst", existGenTlCompanymst);
				String formBeanIdentifier = "genTlCompanymstBean"+genTlCompanymstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlCompanymstBean);
							
				JSONObject mode = new JSONObject();
				mode.put("formMode",genTlCompanymstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("CompKeyid",existGenTlCompanymst.getCompKeyid() );
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				forwardData.put("CompKeyid",existGenTlCompanymst.getCompKeyid() );
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);
				
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Saved Successfully");
				out.print(err.toString());
					
			}catch(ValidationExceptions e)
			{
				System.out.println("e.yguiyg " + e.getMessage());
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"companyCreation");
				errMessage.put("fromMode",genTlCompanymstBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch(Exception e)
			{
				System.out.println("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
	    }	
	 }
	    private void DeleteComp(HttpServletRequest request,HttpServletResponse response, BAL_GenTlCompanymstBean genTlCompanymstBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		BAL_GenTlCompanymst existGenTlCompanymst = (BAL_GenTlCompanymst)httpSession.getAttribute("newSession"); 
	    		BAL_GenTlCompanymst newGenTlCompanymst = new BAL_GenTlCompanymst();
	    		newGenTlCompanymst.setCompCreatedby(user.getUsrm_ccno());
	    		System.out.println("old id::::"+existGenTlCompanymst);
	    		newGenTlCompanymst =(BAL_GenTlCompanymst)UIUtils.setBeanProperties((Object)newGenTlCompanymst,request);
	    		genTlCompanymstBean =(BAL_GenTlCompanymstBean)UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
	    		genTlCompanymstBean =(BAL_GenTlCompanymstBean) UIUtils.setBeanProperties((Object)genTlCompanymstBean,request);
	    
			try{
				System.out.println("Delete function");						
				existGenTlCompanymst = genTlCompanymstService.delete(newGenTlCompanymst);						
				System.out.println("After the IF Loop");
				httpSession.setAttribute(existGenTlCompanymst.getCompKeyid(), existGenTlCompanymst);
				httpSession.setAttribute("GenTlCompanymst", existGenTlCompanymst);
				String formBeanIdentifier = "GenTlCompanymstBean"+genTlCompanymstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlCompanymstBean);
							
				JSONObject mode = new JSONObject();
				mode.put("formMode",genTlCompanymstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("CompKeyid",existGenTlCompanymst.getCompKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				forwardData.put("CompKeyid",existGenTlCompanymst.getCompKeyid());
				System.out.println("servlet out put:"+forwardData);
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);				
			
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Deleted Successfully");
				out.print(err.toString());
					
					
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"companyCreation");
				errMessage.put("fromMode",genTlCompanymstBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch(Exception e)
			{
				System.out.println("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
	    }
    	
	}
}