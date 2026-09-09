
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.akranta.tpm.bean.GenTlLocationmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.GenTlLocationmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.GenTlLocationmstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.GenTlLocationmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;


		public class GenTlLocationmstServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static int count;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
		GenTlLocationmstService  genTlLocationmstService ;
		CommonFilterService commonFilterService;
	    public GenTlLocationmstServlet() {
	        super();
/*	        
        	CommonMessage.debugMsg(" initialising servlet ....");
	        try {
	        	CommonMessage.debugMsg("Factorymstservlet");
	        	genTlLocationmstService = new GenTlLocationmstServiceImpl();
				commonFilterService = new CommonFilterServiceImpl();
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
				String dispatchUrl = null; 
				String action = UIUtils.getActionPart(request);
				try {
					genTlLocationmstService = (GenTlLocationmstServiceImpl)UIUtils.getServiceObject(request,"GenTlLocationmstServiceImpl");
					commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				boolean delete = false;
				if (action.equals("location_input.locn")) 
				{ 
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					String compId = request.getParameter(ReqtParamNameConst.COMPID);
					
					CommonMessage.debugMsg(" userEvent  " + userEvent);
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					GenTlLocationmst genTlLocationmst = null;
					
					httpSession.removeAttribute("genTlLocationmstServlet");
					
					if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						/*else {
						
						 user = UIUtils.getLoginUser(request);
						 JSONObject successData = new JSONObject();
						 successData.put("msg","Deletion is Restricted");
						 //successData.put("mode",genTlFactorymstBean.getFormMode() );
						 //successData.put("keyId", existGenTlFactorymst.getFactKeyid());
						 JSONObject returnData = new JSONObject();					 		
						 returnData.put("successData", successData);
						 returnData.put("formClear",  false);
						 PrintWriter  out = response.getWriter();
						 out.print(returnData.toString());
					}*/
						genTlLocationmst = genTlLocationmstService.select(keyid);
						httpSession.setAttribute("genTlLocationmstServlet" , genTlLocationmst);
						request.setAttribute("genTlLocationmst", genTlLocationmst);
					}
					
					if(UIUtils.isValidKeyId(compId ))
					{
						
						genTlLocationmst = new GenTlLocationmst();
						genTlLocationmst.setLocnCompanyid(compId);
						request.setAttribute("genTlLocationmst", genTlLocationmst);
					}
					
					//mode=FormModes.view;
					
					GenTlLocationmstBean genTlLocationmstBean = new GenTlLocationmstBean(mode,lockFields);
					
					httpSession.setAttribute("genTlLocationmstBean", genTlLocationmstBean);
					request.setAttribute("genTlLocationmstBean", genTlLocationmstBean);
					request.setAttribute("genTlLocationmstServlet", genTlLocationmst);
	
				
					dispatchUrl = "/pages/GenTlLocationmst.jsp";
					
				}
		/*		else if( action.equals("getModelocation_input.locn")){
					response.setContentType("text/html");
					//response.setContentType("text/json");
					
					PrintWriter out = response.getWriter();
					JSONObject result = new JSONObject();
					
					result.put("mode","create");
					result.put("url","location_input.locn");
					result.put("formHeader","Location Master");
					
					out.println(result);
					out.close();
				}
		*/		
				else if(action.equals("location_save.locn"))
				{	
					GenTlLocationmstBean genTlLocationmstBean = (GenTlLocationmstBean)httpSession.getAttribute("genTlLocationmstBean");
			
			        savelocn(request,response,genTlLocationmstBean);
				}
				else if( action.equals("location_delete.locn"))
				{
						ServletOutputStream out = response.getOutputStream();
						delete = false;
					if(delete ){	
						CommonMessage.debugMsg("Inside the DELETE");
						GenTlLocationmstBean genTlLocationmstBean = (GenTlLocationmstBean)httpSession.getAttribute("genTlLocationmstBean");
					
						deletelocn(request,response,genTlLocationmstBean);
					
				} else 
					
					 user = UIUtils.getLoginUser(request);
					 JSONObject successData = new JSONObject();
					 successData.put("msg","Deletion is Restricted");
					 //successData.put("mode",genTlFactorymstBean.getFormMode() );
					 //successData.put("keyId", existGenTlFactorymst.getFactKeyid());
					 JSONObject returnData = new JSONObject();					 		
					 returnData.put("successData", successData);
					 returnData.put("formClear",  false);
					 out.print(returnData.toString());
				}
				  else if(action.equals("location_recall.locn"))
				{	
				
					 ServletOutputStream out = response.getOutputStream();
					 String location =request.getParameter(ReqtParamNameConst.KEYID);
					
					 GenTlLocationmstBean genTlLocationmstBean = new GenTlLocationmstBean(FormModes.modify);
					
					 httpSession.removeAttribute("genTlLocationmstBean");
					 httpSession.setAttribute("genTlLocationmstBean", genTlLocationmstBean);
					
					 httpSession.removeAttribute("genTlLocationmstServlet");
					 GenTlLocationmst genTlLocationmst = genTlLocationmstService.select(location);
					 httpSession.setAttribute("genTlLocationmstServlet",genTlLocationmst);
					 JSONObject  locn =  UIUtils.fromTpmModel(genTlLocationmst);
					 CommonMessage.debugMsg("inside action" + locn );
					 JSONObject returndata = new JSONObject();
					 returndata.put("locn", locn);
					 out.print(returndata.toString());
								
					
				}	
			
			/*	else if( action.equals("combo_genTlFactorymst.fact"))
				{
				try {
					
					List<ComboBox>  factKeyid = genTlLocationmstService.getGenTlLocationmstcombo("");
					
					UIUtils.writeComboBox(response, factKeyid);
					} catch (Exception e) 
					 {
					// TODO Auto-generated catch block
						e.printStackTrace();
					 }
			
				}*/
				if (dispatchUrl != null)
				{
					UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	    	}
		}	
	    private void savelocn(HttpServletRequest request, HttpServletResponse response,GenTlLocationmstBean genTlLocationmstBean  ) throws IOException{
			HttpSession httpSession = request.getSession(false);
	    	PrintWriter  out = response.getWriter();
	        AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	if( httpSession != null && user != null)
	    	{	
				GenTlLocationmst newGenTlLocationmst = new GenTlLocationmst();
				newGenTlLocationmst.setLocnCreatedby(user.getUsrm_ccno());
				if(genTlLocationmstBean == null)
					genTlLocationmstBean = new GenTlLocationmstBean(FormModes.create);	
				newGenTlLocationmst =(GenTlLocationmst)UIUtils.setBeanProperties((Object)newGenTlLocationmst,request);
				//genTlLocationmstBean =(GenTlLocationmstBean) UIUtils.setBeanProperties((Object)genTlLocationmstBean,request);
				GenTlLocationmst existGenTlLocationmst = (GenTlLocationmst)httpSession.getAttribute("genTlLocationmstServlet");
				try{
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( newGenTlLocationmst.getLocnKeyid() ))
					{	
						existGenTlLocationmst = genTlLocationmstService.create(newGenTlLocationmst,existGenTlLocationmst,genTlLocationmstBean);
					}
					else{
						insert = false;
						existGenTlLocationmst = genTlLocationmstService.update(newGenTlLocationmst,existGenTlLocationmst,genTlLocationmstBean);
					}
				    JSONObject mode = new JSONObject();
				//	mode.put("formMode",genTlLocationmstBean.getFormActionMode());
				    JSONObject persistentData = new JSONObject(); 
					persistentData.put("LocnKeyid",existGenTlLocationmst.getLocnKeyid() );
				    JSONObject successData = new JSONObject();
				    String msgPropertyIdnt;
					if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						msgPropertyIdnt = "success-update";
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					//successData.put("formActionMode",genTlLocationmstBean.getFormActionMode() );
					successData.put("LocnKeyid", existGenTlLocationmst.getLocnKeyid());
					successData.put("displayCode", existGenTlLocationmst.getLocnCode());
					successData.put("description", existGenTlLocationmst.getLocnName());
					successData.put("description", existGenTlLocationmst.getLocnFlid());
					
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);
					httpSession.removeAttribute("genTlLocationmstServlet");
					//httpSession.setAttribute( "genTlLocationmstServlet", existGenTlLocationmst);
					//httpSession.removeAttribute("genTlLocationmstBean");
					out.print(returnData.toString());
					out.close();
					
				}catch(ValidationExceptions e)
				{
					
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GenTlLocationmst");
					errMessage.put("formActionMode",genTlLocationmstBean.getFormActionMode());
					out.print(errMessage.toString());
				}
				catch(BusinessApplicationExceptions e)
				{ 
				    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "GenTlLocationmst");
					out.print(errMessage.toString());
			    }
				catch(Exception e)
				{
				    JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
					out.print(err.toString());
				}
	    	}	
    }				
	  private void deletelocn(HttpServletRequest request,HttpServletResponse response, GenTlLocationmstBean genTlLocationmstBean) throws IOException
		{
		 
			
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();		    			  
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	   						
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlLocationmst existGenTlLocationmst = (GenTlLocationmst)httpSession.getAttribute("genTlLocationServlet"); 
	    	    		    		
	    		GenTlLocationmst newGenTlLocationmst = new GenTlLocationmst();	    		    			    		    		
	    		newGenTlLocationmst.setLocnCreatedby(user.getUsrm_ccno());	    		    			    		    		
	    		CommonMessage.debugMsg("old id::::"+existGenTlLocationmst);
	    		
	    		newGenTlLocationmst =(GenTlLocationmst)UIUtils.setBeanProperties((Object)newGenTlLocationmst,request);
	    		genTlLocationmstBean =(GenTlLocationmstBean)UIUtils.setBeanProperties((Object)genTlLocationmstBean,request);    		    		   		    		
	    		
	    		genTlLocationmstBean =(GenTlLocationmstBean) UIUtils.setBeanProperties((Object)genTlLocationmstBean,request);    		    		
				
			try{    					
				
			
				
				CommonMessage.debugMsg("Delete function");						
				existGenTlLocationmst = genTlLocationmstService.delete(newGenTlLocationmst);			
					    						
				CommonMessage.debugMsg("After the IF Loop");
				httpSession.setAttribute(existGenTlLocationmst.getLocnKeyid(), existGenTlLocationmst);
				httpSession.setAttribute("genTlLocationmst", existGenTlLocationmst);
				String formBeanIdentifier = "GenTlLocationmstBean"+genTlLocationmstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlLocationmstBean);
							
				JSONObject mode = new JSONObject();
				mode.put("formMode",genTlLocationmstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("locnKeyid",existGenTlLocationmst.getLocnKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
			
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				//successData.put("msg","Data Deleted Successfully");
				successData.put("formMode",genTlLocationmstBean.getFormActionMode() );
				successData.put("locnKeyid", existGenTlLocationmst.getLocnKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);		
				out.print(returnData.toString());
			
					
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GenTlLocationmst");
				errMessage.put("formMode",genTlLocationmstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
					CommonMessage.debugMsg("BusinessApplicationExcepions"  );
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "GenTlLocationmst");
					out.print(errMessage.toString());
						
			
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Deleted");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    }
	}
}