package com.akranta.tpm.controller;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.bean.GridParams;


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

import com.akranta.tpm.bean.BAL_GenTlAssemblymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KznTlMst;

import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenTlAssemblymstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlAssemblymstServiceImpl;
import com.akranta.tpm.service.impl.MonPlanServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;
import org.apache.commons.beanutils.BeanUtils;
import com.akranta.tpm.bean.CommonParams;
//import com.akranta.tpm.utils.FilterValues;


		public class Bal_AssemblyServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
			BAL_GenTlAssemblymstService  genTlAssemblymstService ;
			
		
	    public Bal_AssemblyServlet() {
	        super();
	        /*	System.out.println(" initialising servlet ....");
	        try {
	        	genTlAssemblymstService = new GenTlAssemblymstServiceImpl();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}*/
	        // TODO Auto-generated constructor stub
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
				
				response.setContentType("text/html");
				response.setContentType("text/json");
			
				try {
					genTlAssemblymstService = (BAL_GenTlAssemblymstServiceImpl)UIUtils.getServiceObject(request,"BAL_GenTlAssemblymstServiceImpl");
					genTlAssemblymstService.BAL_GenTlAssemblymstServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
				} catch (ServiceObjectCreationException e) {
					CommonFunctions.debugMsg(e);
				}
					
				String dispatchUrl = null; 
					
				if (action.equals("assembly_input.asb")) 
				{
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					
					System.out.println(" userEvent  " + userEvent);
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					GenTlAssemblymst genTlAssemblymst = null;
					
					httpSession.removeAttribute("genTlAssemblymstServlet");
					
					if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						
						genTlAssemblymst = genTlAssemblymstService.select(keyid);
						httpSession.setAttribute("genTlAssemblymstServlet" , genTlAssemblymst);
						request.setAttribute("genTlAssemblymst", genTlAssemblymst);
					}
					//mode=FormModes.view;
					
					BAL_GenTlAssemblymstBean genTlAssemblymstBean = new BAL_GenTlAssemblymstBean(mode,lockFields);
					String machId = request.getParameter("machId");
					String refreshFlag = request.getParameter("refreshFlag");
					if(UIUtils.isValidKeyId(machId))
						genTlAssemblymstBean.setMachineId(machId);
					if(UIUtils.isValidKeyId(refreshFlag))
						genTlAssemblymstBean.setRefreshFlag(refreshFlag);
					httpSession.setAttribute("genTlAssemblymstBean", genTlAssemblymstBean);
					request.setAttribute("genTlAssemblymstBean", genTlAssemblymstBean);
					request.setAttribute("genTlAssemblymstServlet", genTlAssemblymst);

				
					dispatchUrl = "/pages/Assembly.jsp";
				}
			
				else if(action.equals("assembly_save.asb"))
				{	
	
					BAL_GenTlAssemblymstBean genTlAssemblymstBean = (BAL_GenTlAssemblymstBean)httpSession.getAttribute("bal_genTlAssemblymstBean");
					//System.out.println(genTlAssemblymstBean.toString());
				    saveAssm(request,response,genTlAssemblymstBean);
				}
						
				else if( action.equals("assembly_delete.asb"))
				{	
					BAL_GenTlAssemblymstBean genTlAssemblymstBean = (BAL_GenTlAssemblymstBean)httpSession.getAttribute("bal_genTlAssemblymstBean");
					deleteAssm(request,response,genTlAssemblymstBean);
					
			    }
			
			   else if(action.equals("assembly_recall.asb"))
				{	
				    ServletOutputStream out = response.getOutputStream();
					String assembly =request.getParameter(ReqtParamNameConst.KEYID);
					
					BAL_GenTlAssemblymstBean genTlAssemblymstBean = new BAL_GenTlAssemblymstBean(FormModes.modify);
					
					httpSession.removeAttribute("genTlAssemblymstBean");
					httpSession.setAttribute("genTlAssemblymstBean", genTlAssemblymstBean);
					
					httpSession.removeAttribute("genTlAssemblymstServlet");
					GenTlAssemblymst genTlAssemblymst = genTlAssemblymstService.select(assembly);
					httpSession.setAttribute("genTlAssemblymstServlet" ,genTlAssemblymst);
					JSONObject  assmdata =  UIUtils.fromTpmModel(genTlAssemblymst);
					System.out.println("inside action" + assmdata );
					JSONObject returndata = new JSONObject();
					returndata.put("assmdata", assmdata);
					out.print(returndata.toString());
					
				}
				
				//added here by priyanka on 16/06/2026
				
			   else if (action.equals("assembly_getCol.asb")) {
				    getAssemblyColModel(request, response);
				}

			   else if (action.equals("assembly_getData.asb")) {
				    getAssemblyTblData(request, response);
				}
				// end here
				if (dispatchUrl != null)
				{
					UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	    	}
		}
				
				// added here by priyanka on 17/06/2026
	    private void getAssemblyColModel(HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        PrintWriter out = response.getWriter();
	        HttpSession httpSession = request.getSession(false);

	        String machineId = request.getParameter("machineId");
	        try {
	            CommonFilter commonFilter = populateCommonFilter(request, "assmCommonFilter", true);
	            commonFilter.setIsGetCol("Y");
	            commonFilter.setMachineId(machineId);
	            List<String[]> assmList = genTlAssemblymstService.getAssemblyGridData(commonFilter);

	            JqGridTableModel jqGridTableModel = new JqGridTableModel();
	            GridColModel gridColModel = new GridColModel();

	            jqGridTableModel.setRowNumbers(true);
	            jqGridTableModel.setEnableFilter(true);

	            gridColModel.setHeaderNum(1);

	            String[] colHeader     = assmList.get(1);
	            String[] colHeaderCond = assmList.get(0);

	            List<String[]> headers = new ArrayList<String[]>();
	            headers.add(colHeader);

	            JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
	            jsonObject.put("tableHeight", "45%%");
	            jsonObject.put("tableWidth",  "55%%");

	            httpSession.setAttribute("AssmListColModel", jsonObject);
	            httpSession.setAttribute("assmCommonFilter", commonFilter);

	            out.println(jsonObject);

	        } catch (Exception e) {
	            CommonMessage.debugMsg("assembly_getCol ERROR: " + e.getMessage());
	            e.printStackTrace();
	            out.println("{}");
	        }
	    }

	    private void getAssemblyTblData(HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        PrintWriter out = response.getWriter();
	        HttpSession httpSession = request.getSession(false);

	        try {
	            CommonFilter commonFilter = populateCommonFilter(request, "assmCommonFilter", false);
	            commonFilter.setIsGetCol("N");

	            List<String[]> assmList = genTlAssemblymstService.getAssemblyGridData(commonFilter);
	            
	            CommonMessage.debugMsg("Rows returned = " + assmList.size());
	            CommonMessage.debugMsg("TotalRecordCnt = " + commonFilter.getTotalRecordCnt());
	            
	            System.out.println("assmList size = " + assmList.size());
	            System.out.println("TotalRecordCnt = " + commonFilter.getTotalRecordCnt());

	            JSONObject assmData = UIUtils.convertToJqGridTableObject(
	                    assmList, request, 2, 0, commonFilter.getTotalRecordCnt());
	            
	            //JSONObject assmData = UIUtils.convertToJqGridTableObject(
	                    //assmList, request, 2, 0, assmList.size());

	            httpSession.removeAttribute("assmCommonFilter");
	            httpSession.setAttribute("assmCommonFilter", commonFilter);

	            out.println(assmData);

	        } catch (Exception e) {
	            CommonMessage.debugMsg("assembly_getData ERROR: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
				
				//end here
				
			
	    private void saveAssm(HttpServletRequest request, HttpServletResponse response,BAL_GenTlAssemblymstBean genTlAssemblymstBean  ) throws IOException{
			
	    	
	    	HttpSession httpSession = request.getSession(false);
	    	
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		if( genTlAssemblymstBean == null)
	    			genTlAssemblymstBean = new BAL_GenTlAssemblymstBean(FormModes.create);
	    		
				GenTlAssemblymst newGenTlAssemblymst = new GenTlAssemblymst();
				newGenTlAssemblymst.setAssmCreatedby(user.getUsrm_ccno());
				
				//genTlAssemblymstBean = (GenTlAssemblymstBean)httpSession.getAttribute("genTlAssemblymstBean");
				
				newGenTlAssemblymst =(GenTlAssemblymst)UIUtils.setBeanProperties((Object)newGenTlAssemblymst,request);
				
				genTlAssemblymstBean =(BAL_GenTlAssemblymstBean) UIUtils.setBeanProperties((Object)genTlAssemblymstBean,request);
				String  refreshFlag = request.getParameter("refreshFlag");
				if(UIUtils.isValidKeyId(refreshFlag))
					genTlAssemblymstBean.setRefreshFlag(refreshFlag);
				GenTlAssemblymst existGenTlAssemblymst = (GenTlAssemblymst)httpSession.getAttribute("bal_genTlAssemblymstServlet");
				
				try{
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( newGenTlAssemblymst.getAssmKeyid() ))
					{	CommonFunctions.debugMsg("Save");
						existGenTlAssemblymst = genTlAssemblymstService.create(newGenTlAssemblymst,existGenTlAssemblymst,genTlAssemblymstBean);
					}
					else{
						CommonFunctions.debugMsg("Update");
						insert = false;
						existGenTlAssemblymst = genTlAssemblymstService.update(newGenTlAssemblymst,existGenTlAssemblymst,genTlAssemblymstBean);
					}
					
				
				//	JSONObject mode = new JSONObject();
				//	mode.put("formMode",genTlAssemblymstBean.getFormActionMode());
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("AssmKeyid",existGenTlAssemblymst.getAssmKeyid() );
					System.out.println(" after save  11111");
				
					System.out.println(" after save  2");
					JSONObject successData = new JSONObject();
				    String msgPropertyIdnt;
				 
				 if( insert){
					msgPropertyIdnt = "success-save";
				 }else
					msgPropertyIdnt = "success-update";
				 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			
				//successData.put("formMode",genTlAssemblymstBean.getFormActionMode() );
				successData.put("AssmKeyid", existGenTlAssemblymst.getAssmKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);		

				httpSession.removeAttribute("bal_genTlAssemblymstServlet");
				//httpSession.setAttribute( "genTlAssemblymstServlet", existGenTlAssemblymst);
				httpSession.removeAttribute("bal_genTlAssemblymstBean");
				
		    	PrintWriter  out = response.getWriter();
				out.print(returnData.toString());
				out.close();
				
				
			}catch(ValidationExceptions e)
			{
				PrintWriter  out = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"assemblyCreation");
				errMessage.put("formActionMode",genTlAssemblymstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				PrintWriter  out = response.getWriter();
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"assemblyCreation");
				out.print(errMessage.toString());
					
						
			}catch(Exception e)
			{
				PrintWriter  out = response.getWriter();
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
				out.print(err.toString());
			}
	    }	
	 }
	    private void deleteAssm(HttpServletRequest request,HttpServletResponse response, BAL_GenTlAssemblymstBean genTlAssemblymstBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		if( genTlAssemblymstBean == null)
	    			genTlAssemblymstBean = new BAL_GenTlAssemblymstBean(FormModes.create);
	    		
	    		GenTlAssemblymst existGenTlAssemblymst = (GenTlAssemblymst)httpSession.getAttribute("genTlAssemblyServlet"); 
	    		GenTlAssemblymst newGenTlAssemblymst = new GenTlAssemblymst();
	    		newGenTlAssemblymst.setAssmCreatedby(user.getUsrm_ccno());
	    		System.out.println("old id::::"+existGenTlAssemblymst);
	    		newGenTlAssemblymst =(GenTlAssemblymst)UIUtils.setBeanProperties((Object)newGenTlAssemblymst,request);
	    		genTlAssemblymstBean =(BAL_GenTlAssemblymstBean)UIUtils.setBeanProperties((Object)genTlAssemblymstBean,request);
	    		genTlAssemblymstBean =(BAL_GenTlAssemblymstBean) UIUtils.setBeanProperties((Object)genTlAssemblymstBean,request);
	    
			try{
				System.out.println("  Equipment "+ newGenTlAssemblymst.getAssmKeyid());
				System.out.println(request.getParameter("hdnInactive"));				
				String inactMode =request.getParameter("hdnInactive");
				System.out.println(inactMode);
				
				if ( inactMode.equals("Inactive") )
					existGenTlAssemblymst = genTlAssemblymstService.delete("I",newGenTlAssemblymst);				
				else
					existGenTlAssemblymst = genTlAssemblymstService.delete("D",newGenTlAssemblymst);
				
				
				//httpSession.setAttribute(existGenTlAssemblymst.getAssmKeyid(), existGenTlAssemblymst);
				//httpSession.setAttribute("genTlAssemblymst", existGenTlAssemblymst);
				//String formBeanIdentifier = "GenTlAssemblymstBean"+genTlAssemblymstBean.getFormActionMode();
				//httpSession.setAttribute(formBeanIdentifier,genTlAssemblymstBean);
				
				existGenTlAssemblymst.setAssmKeyid("N");
				
				JSONObject mode = new JSONObject();
				//mode.put("formMode",genTlAssemblymstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("AssmKeyid",existGenTlAssemblymst.getAssmKeyid());
				//persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject forwardData = new JSONObject();
				forwardData.put("AssmKeyid",existGenTlAssemblymst.getAssmKeyid());
				//System.out.println("servlet out put:" + forwardData);
				mode.put("forwardData", forwardData);
				mode.put("persistentData", persistentData);
				
				
			
				JSONObject successData = new JSONObject();
				successData.put("msg",  UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactivated"));
						//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));

				successData.put("formActionMode",genTlAssemblymstBean.getFormActionMode() );
				successData.put("AssmKeyid", existGenTlAssemblymst.getAssmKeyid());
				JSONObject returnData = new JSONObject();		
				
				httpSession.removeAttribute("genTlAssemblymstServlet");
				httpSession.removeAttribute("genTlAssemblymstBean");
				
				
				returnData.put("successData", successData);		
				out.print(returnData.toString());
					
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"assemblyCreation");
				errMessage.put("formActionMode",genTlAssemblymstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				System.out.println("BusinessApplicationExcepions"  );
				JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),"assemblyCreation");
				//out.print(errMessage.toString());
				successData.put("msg", "Original Id Exists");
				JSONObject returnData = new JSONObject();	
				
				returnData.put("formClear",false);
				returnData.put("successData", successData);	
				
				out.print(returnData.toString());			
			}catch(Exception e)
			{
				System.out.println("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Deleted");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    }
    	
	}
	    private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
			HttpSession httpSession = request.getSession(false);
			
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			if( commonFilter != null && ! createNew ){
				FilterValues.setPaginationParams(request,commonFilter);
				FilterValues.getCommonFilters(request, commonFilter);
			}	
			else{
				commonFilter =  new CommonFilter();
				
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
				if(beanIdentifier.equals("whywhyQtyCommonFilter"))
					commonFilter = 	FilterValues.getQuality(request, commonFilter);
				else			
					commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			 if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
				  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
				  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
				  commonFilter.setMonwise("Y");
		 	  } 

			
			return commonFilter;
		}

}