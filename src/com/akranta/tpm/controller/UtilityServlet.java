package com.akranta.tpm.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.UtilityService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.UtilityServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.UtilityFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.GenTlToolsmst;

public class UtilityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UtilityService  utilityService ;
	CommonFilterService commonFilterService;
	
	String formTypeIdentifier [];
	
	public UtilityServlet()
	{
		super();
         //CommonMessage.debugMsg(" initialising servlet ....");
        /*try {
        	utilityService = new UtilityServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
		//Constructor
	}
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				processRequest(request, response);
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
			try {
				processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws Exception {
			

			   HttpSession httpSession = request.getSession(false);
			   String action = UIUtils.getActionPart(request);
			   
				
			   try {
				   utilityService = (UtilityServiceImpl)UIUtils.getServiceObject(request,"UtilityServiceImpl");
				   commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				
			   if( ! UIUtils.checkUserSession(request, response)){
				   return;
			   }
			   else if( action.equals("Utility_input.uty") ){
				   request.setAttribute("type", "U");
				   initialiseInputForm(request,response,"U");				   
				}
			   else if( action.equals("Tools_input.uty") ){	
				   request.setAttribute("type", "T");
				   initialiseInputForm(request,response,"T");				

			   }
			   else if( action.equals("Utility_newcategory.uty") ){	
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/causemaster.jsp"); 
					rd.forward(request, response); 
			   }
				else if( action.equals("combo_Utility.uty"))
				{
					try {
						ComboFilter currentFilter = new ComboFilter();
						currentFilter=UIUtils.fillComboFilter(request);
						List<ComboBox>  genUtility = utilityService.getUtilityIdCombo("",currentFilter); 
						UIUtils.writeComboBox(response, genUtility,currentFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			   
			   
				else if( action.equals("combo_Category.uty"))
				{
					try {
						ComboFilter currentFilter = new ComboFilter();
						currentFilter=UIUtils.fillComboFilter(request);
						List<ComboBox>  genCategory = utilityService.getCategoryCombo("",currentFilter); 
						UIUtils.writeComboBox(response, genCategory ,currentFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			   

				else if( action.equals("Utility_save.uty") || action.equals("Tools_save.uty") )
				{	
					UtilityFormBean utilityFormBean = (UtilityFormBean)httpSession.getAttribute("UtilityServletUtilityFormBean");
					
					if (utilityFormBean == null )	{
						 utilityFormBean = new  UtilityFormBean();					
					}
					saveUtility(request,response,utilityFormBean);
					
			    }
				else if( action.equals("Utility_del.uty"))
				{	
					UtilityFormBean utilityFormBean = new  UtilityFormBean();
					deleteUtility(request,response,utilityFormBean);				
			    }			   
				else if( action.equals("Utility_recall.uty"))
				{	
				
					ServletOutputStream out = response.getOutputStream();
					String keyid = request.getParameter("UtilField");
					GenTlToolsmst genTlToolsmst  = recallValues(request, keyid);
					response.setContentType("text/html");	
					if( genTlToolsmst != null ){
						JSONObject  utilJSONObj =  UIUtils.fromTpmModel(genTlToolsmst);
						if( genTlToolsmst.getGenTlToolsimg() != null)
							utilJSONObj.put("TolmFilename",genTlToolsmst.getGenTlToolsimg().getToimFilename());
						
						JSONObject returndata = new JSONObject();
						returndata.put("Utility", utilJSONObj);	
						CommonMessage.debugMsg(returndata.toString());
						out.print(returndata.toString());
					}	
				}	
			
		}
		private void initialiseInputForm(HttpServletRequest request, HttpServletResponse response,String type) throws ServletException, IOException, NoDataFoundException, Exception{
			   
			   HttpSession httpSession = request.getSession(false);
			   if( UIUtils.checkUserSession(request, response)){
				   
				   String keyid = request.getParameter("keyId");
				   CommonMessage.debugMsg(" keyid " + keyid );
				   recallValues(request,keyid);
				   
				   UtilityFormBean utilityFormBean = new  UtilityFormBean();
				   
				   utilityFormBean.setFormType(type);
				   if( UIUtils.isValidKeyId(keyid))
					   utilityFormBean.setFormMode("UPDATE");
				   else
					   utilityFormBean.setFormMode("INSERT");
				   
				   String formTypeIdentifier = "UtilityServletUtilityFormBean";			   
				   httpSession.setAttribute(formTypeIdentifier,utilityFormBean);
				   
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/Utility.jsp");				   
				   rd.forward(request, response); 	
			   }  
		}
		private GenTlToolsmst recallValues(HttpServletRequest request,String keyid) throws Exception{
				
			  HttpSession httpSession = request.getSession(false);
			  if( UIUtils.isValidKeyId(keyid) ){
				   GenTlToolsmst genTlToolsmst = null;
				   genTlToolsmst = utilityService.select(keyid);
				   
				   String filePath = UIUtils.getImagePath(request);
				   
				   genTlToolsmst.getGenTlToolsimg().setToimBlobimage(filePath);
				   genTlToolsmst.getGenTlToolsimg().setToimFilename(UIUtils.TPM_TEMPIMG_DIR);
				   GenTlToolsimg genTlToolsimg =null;
				   try{
					   genTlToolsimg =utilityService.getToolsImage(genTlToolsmst);
					   if( genTlToolsimg != null)
						   request.setAttribute("genTlToolsimg",genTlToolsimg);
				   }catch(Exception e){
					   
				   }
				   request.setAttribute("genTlToolsmst", genTlToolsmst);
				   
				   genTlToolsmst.setGenTlToolsimg(genTlToolsimg);
				   httpSession.setAttribute("genTlToolsmst", genTlToolsmst);
				   return genTlToolsmst;
			  }	
			  return null;
		}
		 private void saveUtility(HttpServletRequest request, HttpServletResponse response,UtilityFormBean utilityFormBean  ) throws IOException
		 {		
		    	
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		GenTlToolsmst existGenTlToolssmst = (GenTlToolsmst)httpSession.getAttribute("genTlToolsmst");
		    		utilityFormBean = (UtilityFormBean)httpSession.getAttribute("UtilityServletUtilityFormBean");  
		    		GenTlToolsmst newGenTlToolssmst = new GenTlToolsmst();
		    		GenTlToolsimg genTlToolsimg = new GenTlToolsimg();  
		    		newGenTlToolssmst.setTolmCreatedby(user.getUsrm_ccno());		    		
		    		
		    		
		    		newGenTlToolssmst =(GenTlToolsmst)UIUtils.setBeanProperties((Object)newGenTlToolssmst,request);
					//utilityFormBean =(UtilityFormBean) UIUtils.setBeanProperties((Object)utilityFormBean,request);
					genTlToolsimg = (GenTlToolsimg) UIUtils.setBeanProperties((Object)genTlToolsimg,request);
					
					if( genTlToolsimg != null){
						String imagePath = UIUtils.getImagePath(request);
						genTlToolsimg.setToimBlobimage(imagePath );
						CommonMessage.debugMsg(" genTlToolsimg.setToimBlobimage( " + genTlToolsimg.getToimBlobimage());
					}
					newGenTlToolssmst.setGenTlToolsimg(genTlToolsimg) ;
					
					CommonMessage.debugMsg("Tool Name :" +newGenTlToolssmst.getTolmName());
					
					//String frmtypeiden = "utilityFormBean"+utilityFormBean.getFormType();					
					//String frmType= (String) utilityFormBean.getFormType();
					//CommonMessage.debugMsg("FORM TYPE" + frmtypeiden );
					
					CommonMessage.debugMsg("session form type " + httpSession.getAttribute("formType"));
					 String msgPropertyIdnt;
					try{
						CommonMessage.debugMsg(" Tools KEYID() " +  newGenTlToolssmst.getTolmKeyid());
						if( newGenTlToolssmst.getTolmKeyid() == null )
						{	
							CommonMessage.debugMsg(" inside create ");
							existGenTlToolssmst =	utilityService.create(newGenTlToolssmst,existGenTlToolssmst,utilityFormBean);
							msgPropertyIdnt = "success-save";


						}	
						else{
							CommonMessage.debugMsg(" inside update ");
							existGenTlToolssmst = utilityService.update(newGenTlToolssmst,existGenTlToolssmst,utilityFormBean);
							msgPropertyIdnt = "success-update";

						}
						
						httpSession.setAttribute(existGenTlToolssmst.getTolmKeyid(), existGenTlToolssmst);
						httpSession.setAttribute("GenTlToolsmst", existGenTlToolssmst);
						String formBeanIdentifier = "utilityFormBean"+utilityFormBean.getFormActionMode();
						httpSession.setAttribute(formBeanIdentifier,utilityFormBean);
								
						JSONObject mode = new JSONObject();
						mode.put("formMode",utilityFormBean.getFormActionMode());
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("tolmKeyid",existGenTlToolssmst.getTolmKeyid() );
						persistentData.put("fromBean", formBeanIdentifier);
						
						persistentData.put("fromBean", formTypeIdentifier);
						
						JSONObject forwardData = new JSONObject();
						forwardData.put("tolmKeyid",existGenTlToolssmst.getTolmKeyid() );
						mode.put("forwardData",forwardData);
						mode.put("persistentData", persistentData);
						mode.put("tpmException", "Data  Saved");
						//mode.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));

						out.print(mode.toString());
						out.close();

						
						 //if( utilityFormBean.getFormMode().equals("INSERT") )
							utilityService.insertToolImg(existGenTlToolssmst);
						//else
						//	utilityService.updateToolImg(existGenTlToolssmst);
						
					}catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "UtilityCreationException");
						errMessage.put("fromMode",utilityFormBean.getFormActionMode());
						out.print(errMessage.toString());
						
					}catch(BusinessApplicationExceptions e)
					{
						JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "UtilityCreationException");
						out.print(errMessage.toString());
						
					}catch(Exception e)
					{
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
					}
		    	}	
		    }

		 private void deleteUtility(HttpServletRequest request, HttpServletResponse response,UtilityFormBean utilityFormBean  ) throws IOException{
				//KznTlMst kznTlMst = (KznTlMst)request.getAttribute("kznTlMst"); ;
		    	
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		GenTlToolsmst existGenTlToolsmst = (GenTlToolsmst)httpSession.getAttribute("GenTlToolsmst"); 
		    		GenTlToolsmst newGenTlToolsmst = new GenTlToolsmst();
		    		newGenTlToolsmst.setTolmCreatedby(user.getUsrm_ccno());
					
		    		newGenTlToolsmst =(GenTlToolsmst)UIUtils.setBeanProperties((Object)newGenTlToolsmst,request);
					utilityFormBean =(UtilityFormBean) UIUtils.setBeanProperties((Object)utilityFormBean,request);
					
					try{					
						if( newGenTlToolsmst.getTolmKeyid() != null )
							existGenTlToolsmst =	utilityService.delete(newGenTlToolsmst);
					}
					catch(Exception e)
					{
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
					}
		    	}	
		 }
}



