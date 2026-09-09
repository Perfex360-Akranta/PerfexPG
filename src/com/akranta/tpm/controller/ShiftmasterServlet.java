
package com.akranta.tpm.controller;

import java.io.IOException;
import java.util.List;

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
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlShiftmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlShiftmst;
import com.akranta.tpm.service.GenTlShiftmstService;
import com.akranta.tpm.service.impl.GenTlShiftmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;


		public class ShiftmasterServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
				
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
			GenTlShiftmstService genTlShiftmstService ;
			
		
	    public ShiftmasterServlet() {
	        super();
	        /*try {
	        	genTlShiftmstService = new GenTlShiftmstServiceImpl();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			*/
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
				try {
					genTlShiftmstService = (GenTlShiftmstServiceImpl)UIUtils.getServiceObject(request,"GenTlShiftmstServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				response.setContentType("text/html");
				response.setContentType("text/json");
			
					
				String dispatchUrl = null; 
				
					if (action.equals("shift_input.sftm")) 
					{
						String keyid = request.getParameter("keyId");
						String userEvent = request.getParameter("userEvent");
						CommonMessage.debugMsg(" userEvent  " + userEvent);
						response.setContentType("text/html");
						
						if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
							CommonMessage.debugMsg("KEY ID INPUT ACTION:"+keyid);
							GenTlShiftmst genTlShiftmst = genTlShiftmstService.select(keyid);
							httpSession.setAttribute("genTlShiftmst", genTlShiftmst);
							request.setAttribute("genTlShiftmst", genTlShiftmst);
						}
						request.setAttribute("mode", FormModes.create);
						dispatchUrl = "/pages/Shiftmaster.jsp";
						
					}
					
					else if(action.equals("functionalLoc.sftm"))
					{
					    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				
							functLocFieldNameBean.setCompany("cmbComp");
					        functLocFieldNameBean.setSbu("cmbSbu");
							functLocFieldNameBean.setPbu("cmbPbu");
							functLocFieldNameBean.setSection("cmbSect");
							functLocFieldNameBean.setCell("cmbCell");
							functLocFieldNameBean.setMachine("cmbMachine");
							functLocFieldNameBean.setSectMandatory(false);
							functLocFieldNameBean.setFactMandatory(false);
							functLocFieldNameBean.setSectMandatory(false);
							functLocFieldNameBean.setCellMandatory(false);
						    functLocFieldNameBean.setMachMandatory(false);
						    FormModes formModes = FormModes.create;
						UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
					}
				
					else if(action.equals("shift_save.sftm"))
					{	
						CommonMessage.debugMsg("inside action");
						GenTlShiftmstBean genTlShiftmstBean = new GenTlShiftmstBean();
				
				        savesftm(request,response,genTlShiftmstBean);
				        
					}
					else if( action.equals("shift_delete.sftm"))
					{	
						CommonMessage.debugMsg("Inside the DELETE");
						GenTlShiftmstBean genTlShiftmstBean = new GenTlShiftmstBean();
						
						DeleteSftm(request,response,genTlShiftmstBean);
						
				    }
				
					else if(action.equals("shift_recall.sftm"))
					{	
						
						ServletOutputStream out = response.getOutputStream();
						GenTlShiftmst genTlShiftmst = genTlShiftmstService.select(request.getParameter("Sftm"));
						CommonMessage.debugMsg("shiftmstData");
						response.setContentType("text/html");
						httpSession.setAttribute("genTlShiftmst",genTlShiftmst);
						JSONObject  shift =  UIUtils.fromTpmModel(genTlShiftmst);
						CommonMessage.debugMsg("inside action" + shift );
						JSONObject returndata = new JSONObject();
						returndata.put("shift", shift);
						out.print(returndata.toString());
					
				    	
					}
					else if(action.equals("combo_Shiftorder.sftm"))
					{
						try 
						{
							ComboFilter comboFilter = null;
							comboFilter=UIUtils.fillComboFilter(request);
							//String tagno = request.getParameter("tagno");
							List<ComboBox>  shiftOrder = genTlShiftmstService.getdepartmentcombo(comboFilter);
							UIUtils.writeComboBox(response, shiftOrder,comboFilter);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
					
				/*	else if( action.equals("combo_Shiftorder.sftm"))
					{
					try {
						    ComboFilter comboFilter = null;
							List<ComboBox>  sftmShiftorder = genTlShiftmstService.getGenTlShiftmstShiftorder("");
							CommonMessage.debugMsg("action shift order  " +sftmShiftorder);
							UIUtils.writeComboBox(response, sftmShiftorder,comboFilter);
						} catch (Exception e) {
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
	    private void savesftm(HttpServletRequest request, HttpServletResponse response,GenTlShiftmstBean genTlShiftmstBean  ) throws IOException{
		
	    	
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		GenTlShiftmst existGenTlShiftmst = (GenTlShiftmst)httpSession.getAttribute("genTlShiftmst"); 
	    		GenTlShiftmst newGenTlShiftmst = new GenTlShiftmst();
				newGenTlShiftmst.setSftmCreatedby(user.getUsrm_ccno());
				
				newGenTlShiftmst =(GenTlShiftmst)UIUtils.setBeanProperties((Object)newGenTlShiftmst,request);
				genTlShiftmstBean =(GenTlShiftmstBean) UIUtils.setBeanProperties((Object)genTlShiftmstBean,request);
				CommonMessage.debugMsg("MAK name  :"+newGenTlShiftmst.getSftmName());
				CommonMessage.debugMsg("MAK name  :"+newGenTlShiftmst.getSftmCode());
				CommonMessage.debugMsg("MAK name  :"+newGenTlShiftmst.getSftmKeyid());
				CommonMessage.debugMsg("MAK name  :"+newGenTlShiftmst.getSftmBreaktime());
				CommonMessage.debugMsg("MAK name  :"+newGenTlShiftmst.getSftmShiftorder());
				try{
					CommonMessage.debugMsg("  newgenTlShiftmst.getSftmKeyid() " +  newGenTlShiftmst.getSftmKeyid());
					boolean insert = true;
					if( newGenTlShiftmst.getSftmKeyid() == null )
					{	CommonMessage.debugMsg("Save called create");
						existGenTlShiftmst = genTlShiftmstService.create(newGenTlShiftmst,existGenTlShiftmst,genTlShiftmstBean);
					}	
					else{
						
						existGenTlShiftmst = genTlShiftmstService.update(newGenTlShiftmst,existGenTlShiftmst,genTlShiftmstBean);
						 insert = false;
					}
					
					httpSession.setAttribute(existGenTlShiftmst.getSftmKeyid(), existGenTlShiftmst);
					httpSession.setAttribute("genTlShiftmst", existGenTlShiftmst);
					String formBeanIdentifier = "genTlShiftmstBean"+genTlShiftmstBean.getFormActionMode();
					httpSession.setAttribute(formBeanIdentifier,genTlShiftmstBean);
							
					JSONObject mode = new JSONObject();
					mode.put("formMode",genTlShiftmstBean.getFormActionMode());
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("SftmKeyid",existGenTlShiftmst.getSftmKeyid() );
					persistentData.put("fromBean", formBeanIdentifier);
				/*	JSONObject forwardData = new JSONObject();
					forwardData.put("SftmKeyid",existGenTlShiftmst.getSftmKeyid() );
					mode.put("forwardData",forwardData);
					mode.put("persistentData", persistentData);
					
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Saved Successfully");
					out.print(err.toString());
					*/
					JSONObject successData = new JSONObject();
					String msgPropertyIdnt;
					 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						msgPropertyIdnt = "success-update";
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
					//successData.put("msg","Data Saved Successfully");
					successData.put("mode",genTlShiftmstBean.getFormMode() );
					successData.put("keyId", existGenTlShiftmst.getSftmKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);		
					out.print(returnData.toString());
				}catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "ShiftCreationException");
					errMessage.put("fromMode",genTlShiftmstBean.getFormActionMode());
					out.print(errMessage.toString());
				}catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("Error Servler e -"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ShiftCreationException");
					out.print(errMessage.toString());
					CommonMessage.debugMsg(" e " + errMessage );
							
				}catch(Exception e)
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
					out.print(err.toString());
				}
	    	}	
	    }
	    private void DeleteSftm(HttpServletRequest request,HttpServletResponse response, GenTlShiftmstBean genTlShiftmstBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlShiftmst existGenTlShiftmst = (GenTlShiftmst)httpSession.getAttribute("newSession"); 
	    		 
	    		
	    		GenTlShiftmst newGenTlShiftmst = new GenTlShiftmst();
	    		
	    		
	    		newGenTlShiftmst.setSftmCreatedby(user.getUsrm_ccno());
	    		
	    		
	    		CommonMessage.debugMsg("old id::::"+existGenTlShiftmst);
	    		
	    		newGenTlShiftmst =(GenTlShiftmst)UIUtils.setBeanProperties((Object)newGenTlShiftmst,request);
	    		genTlShiftmstBean =(GenTlShiftmstBean)UIUtils.setBeanProperties((Object)genTlShiftmstBean,request);
	    		
	    		genTlShiftmstBean =(GenTlShiftmstBean) UIUtils.setBeanProperties((Object)genTlShiftmstBean,request);
	    		

			try{

					
				CommonMessage.debugMsg("Delete function");						
				existGenTlShiftmst = genTlShiftmstService.delete(newGenTlShiftmst);						
				
					
				CommonMessage.debugMsg("After the IF Loop");
				httpSession.setAttribute(existGenTlShiftmst.getSftmKeyid(), existGenTlShiftmst);
				httpSession.setAttribute("genTlShiftmst", existGenTlShiftmst);
				String formBeanIdentifier = "GenTlShiftmstBean"+genTlShiftmstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlShiftmstBean);
							
			/*	JSONObject mode = new JSONObject();
				mode.put("formMode",genTlShiftmstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("SftmKeyid",existGenTlShiftmst.getSftmKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				forwardData.put("SftmKeyid",existGenTlShiftmst.getSftmKeyid());
				CommonMessage.debugMsg("servlet out put:"+forwardData);
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);				
				
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Deleted Successfully");
				out.print(err.toString());*/
					
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				//successData.put("msg","Data Deleted Successfully");
				successData.put("mode",genTlShiftmstBean.getFormMode() );
				successData.put("keyId", existGenTlShiftmst.getSftmKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);		
				out.print(returnData.toString());
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "ShiftCreationException");
				errMessage.put("fromMode",genTlShiftmstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Error Servler e -"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "ShiftCreationException");
				out.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage );
							
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
