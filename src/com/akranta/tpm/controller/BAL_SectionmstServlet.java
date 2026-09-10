package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


import com.akranta.tpm.bean.BAL_FunctLocFieldNameBean;
import com.akranta.tpm.bean.BAL_GenTlFactorymstBean;
import com.akranta.tpm.bean.BAL_GenTlSectionmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlPbumst;



import com.akranta.tpm.model.BAL_GenTlSectionmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenTlSectionmstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlSectionmstServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlShiftmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;

	public class BAL_SectionmstServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int count;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
		BAL_GenTlSectionmstService  genTlSectionmstService ;
		CommonFilterService commonFilterService;
	
    public BAL_SectionmstServlet() {
        super();
        //	System.out.println(" initialising servlet ....");
        /*try {
        	genTlSectionmstService = new GenTlSectionmstServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
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
				genTlSectionmstService = (BAL_GenTlSectionmstServiceImpl)UIUtils.getServiceObject(request,"BAL_GenTlSectionmstServiceImpl");
				commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			} catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}
			
			response.setContentType("text/html");
			response.setContentType("text/json");
				
			String dispatchUrl = null; 
			boolean delete = false;
			if (action.equals("section_input.sect")) 
			{
				String keyid = request.getParameter(ReqtParamNameConst.KEYID);
				String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
				String compId = request.getParameter(ReqtParamNameConst.COMPID);
				String factId = request.getParameter(ReqtParamNameConst.FACTID);
				String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
				response.setContentType("text/html");
				FormModes mode = FormModes.create;	
			
				BAL_GenTlSectionmst genTlSectionmst = null;
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				
				if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						mode =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						mode=FormModes.view;
					}
					
					genTlSectionmst = genTlSectionmstService.select(keyid);
					httpSession.setAttribute("genTlSectionmstServlet" , genTlSectionmst);
					request.setAttribute("genTlSectionmst", genTlSectionmst);
				}
				if(UIUtils.isValidKeyId(compId )&& UIUtils.isValidKeyId(factId ))
				{
					
					genTlSectionmst = new BAL_GenTlSectionmst();
					genTlSectionmst.setSectCompanyid(compId);
					genTlSectionmst.setSectFactoryid(factId);
					request.setAttribute("genTlSectionmst", genTlSectionmst);
				}
				//mode=FormModes.view;
				
				BAL_GenTlSectionmstBean genTlSectionmstBean = new BAL_GenTlSectionmstBean(mode,lockFields);
				
				httpSession.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				request.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				request.setAttribute("genTlSectionmstServlet", genTlSectionmst);

				dispatchUrl = "/pages/sectionmaster.jsp";
			}
			else if(action.equals("section_save.sect"))
			{	
				BAL_GenTlSectionmstBean genTlSectionmstBean = (BAL_GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
		        savesect(request,response,genTlSectionmstBean);
			}
			else if( action.equals("section_delete.sect"))
			{	
				//GenTlSectionmstBean genTlSectionmstBean = (GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
				
				//deleteSect(request,response,genTlSectionmstBean);
				ServletOutputStream out = response.getOutputStream();
			//	delete = false;
		//	if(delete ){	
				
				BAL_GenTlSectionmstBean genTlSectionmstBean = (BAL_GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
			
				deleteSect(request,response,genTlSectionmstBean);
				
			/*} else 
				
				 user = UIUtils.getLoginUser(request);
				 JSONObject successData = new JSONObject();
				 successData.put("msg","Deletion is Restricted");
				 //successData.put("mode",genTlFactorymstBean.getFormMode() );
				 //successData.put("keyId", existGenTlFactorymst.getFactKeyid());
				 JSONObject returnData = new JSONObject();					 		
				 returnData.put("successData", successData);
				 returnData.put("formClear",  false);
				 out.print(returnData.toString());*/
			}
			
			else if( action.equals("DMT_recall.sect") )
			{
				PrintWriter out = response.getWriter();
				
				String DMTkeyid =request.getParameter(ReqtParamNameConst.KEYID);
				CommonFunctions.debugMsg("KKKKKK   "+DMTkeyid);
				
				BAL_GenTlSectionmst genTlDmtmst=genTlSectionmstService.filldmtcontrol(DMTkeyid);
				
				System.out.println(" Code :: "+genTlDmtmst.getSectCode()+" Name :: "+genTlDmtmst.getSectName() );
				
			     JSONObject  dmtmst =  UIUtils.fromTpmModel(genTlDmtmst);
				 System.out.println("inside action" + genTlDmtmst );
				 JSONObject returndata = new JSONObject();
			
				 returndata.put("dmtmst", dmtmst);
				 out.print(returndata.toString());
			}
			
			
			
			else if(action.equals("functionalLoc.sect"))
			{
			    BAL_FunctLocFieldNameBean functLocFieldNameBean = new BAL_FunctLocFieldNameBean();
		
					functLocFieldNameBean.setCompany("cmbComp");
			        functLocFieldNameBean.setFactory("cmbSectFactoryid");
					//functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSectKeyid");
					//functLocFieldNameBean.setCell("cmbCell");
					//functLocFieldNameBean.setMachine("cmbMachine");
					functLocFieldNameBean.setFactMandatory(true);
					//functLocFieldNameBean.setSbuDisable(true);
					//functLocFieldNameBean.setPbuDisable(false);
			        functLocFieldNameBean.setSectDisable(false);
					functLocFieldNameBean.setCellDisable(true);
					functLocFieldNameBean.setMachDisable(true);
					
			
				
	            FormModes formModes = FormModes.create;
				BAL_UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
			}
		    
			
			else if(action.equals("section_recall.sect"))
			{	
				
				ServletOutputStream out = response.getOutputStream();
				String sect =request.getParameter(ReqtParamNameConst.KEYID);
				
				BAL_GenTlSectionmstBean genTlSectionmstBean = new BAL_GenTlSectionmstBean(FormModes.modify);
				
				httpSession.removeAttribute("genTlSectionmstBean");
				httpSession.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				BAL_GenTlSectionmst genTlSectionmst = genTlSectionmstService.select(sect);
				httpSession.setAttribute("genTlSectionmstServlet",genTlSectionmst);
				JSONObject  section =  UIUtils.fromTpmModel(genTlSectionmst);
				
				JSONObject returndata = new JSONObject();
				returndata.put("section", section);
				out.print(returndata.toString());
				
		    	
			}
	
		
			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
		}
	}
    private void savesect(HttpServletRequest request, HttpServletResponse response,BAL_GenTlSectionmstBean genTlSectionmstBean  ) throws IOException{
		
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		CommonFunctions.debugMsg("secccc");
			BAL_GenTlSectionmst newGenTlSectionmst = new BAL_GenTlSectionmst();
			newGenTlSectionmst.setSectCreatedby(user.getUsrm_ccno());
			if( genTlSectionmstBean == null)
				genTlSectionmstBean = new BAL_GenTlSectionmstBean(FormModes.create);
			
			newGenTlSectionmst =(BAL_GenTlSectionmst)UIUtils.setBeanProperties((Object)newGenTlSectionmst,request);
			//genTlSectionmstBean =(GenTlSectionmstBean) UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
			BAL_GenTlSectionmst existGenTlSectionmst = (BAL_GenTlSectionmst)httpSession.getAttribute("genTlSectionmstServlet");
			
			try{
				boolean insert = true;
				if( ! UIUtils.isValidKeyId( newGenTlSectionmst.getSectKeyid() ))
				{	
					existGenTlSectionmst = genTlSectionmstService.create(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
				}
				else{
					insert = false;
					existGenTlSectionmst = genTlSectionmstService.update(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
				}
				
							
			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("SectKeyid",existGenTlSectionmst.getSectKeyid() );
				
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
				 
				 if( insert){
					msgPropertyIdnt = "success-save";
				 }else
					msgPropertyIdnt = "success-update";
				 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			
				//successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode() );
				successData.put("SectKeyid", existGenTlSectionmst.getSectKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				httpSession.removeAttribute("genTlSectionmstBean");
				
				out.print(returnData.toString());
				out.close();
			}catch(ValidationExceptions e)
			{
				//PrintWriter  out = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"sectionCreationException");
				errMessage.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				//	PrintWriter  out = response.getWriter();
					System.out.println("BusinessApplicationExcepions"  );
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"sectionCreationException");
					out.print(errMessage.toString());
					
					
			}catch(Exception e)
			{
				//PrintWriter  out = response.getWriter();
				System.out.println("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
				out.print(err.toString());
			}
    	}	
    }
    private void deleteSect(HttpServletRequest request,HttpServletResponse response, BAL_GenTlSectionmstBean genTlSectionmstBean) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		BAL_GenTlSectionmst existGenTlSectionmst = (BAL_GenTlSectionmst)httpSession.getAttribute("genTlSectionmstServlet"); 
    	 
    		
    		BAL_GenTlSectionmst newGenTlSectionmst = new BAL_GenTlSectionmst();
    
    		
    		newGenTlSectionmst.setSectCreatedby(user.getUsrm_ccno());
    
    		
    		System.out.println("old id::::"+ existGenTlSectionmst);
    		
    		newGenTlSectionmst =(BAL_GenTlSectionmst)UIUtils.setBeanProperties((Object)newGenTlSectionmst,request);
    		genTlSectionmstBean =(BAL_GenTlSectionmstBean)UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
    		
    		genTlSectionmstBean =(BAL_GenTlSectionmstBean) UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
    		
	
		try{
			String inactMode =request.getParameter("hdnInactive");
			System.out.println(inactMode);
			String toInactiveMsg =null;
			if ( inactMode.equals("Inactive") ){
				
				existGenTlSectionmst = genTlSectionmstService.delete("I",newGenTlSectionmst);
				toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactivated");
			}
			else{
				existGenTlSectionmst = genTlSectionmstService.delete("D",newGenTlSectionmst);
				toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");
			}
			
			
			//httpSession.setAttribute(existGenTlSectionmst.getSectKeyid(), existGenTlSectionmst);
			//httpSession.setAttribute("genTlSectionmst", existGenTlSectionmst);
			//String formBeanIdentifier = "GenTlSectionmstBean"+genTlSectionmstBean.getFormActionMode();
			//httpSession.setAttribute(formBeanIdentifier,genTlSectionmstBean);
			
			existGenTlSectionmst.setSectKeyid("N");
			
			JSONObject mode = new JSONObject();
			//mode.put("formMode",genTlSectionmstBean.getFormActionMode());
			JSONObject persistentData = new JSONObject(); 
			persistentData.put("SectKeyid",existGenTlSectionmst.getSectKeyid());
			//persistentData.put("fromBean", formBeanIdentifier);
			
			JSONObject forwardData = new JSONObject();
			forwardData.put("SectKeyid",existGenTlSectionmst.getSectKeyid());

			mode.put("forwardData", forwardData);
			mode.put("persistentData", persistentData);
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg",toInactiveMsg);
					//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			//successData.put("msg","Data Deleted Successfully");
			successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode() );
			successData.put("SectKeyid", existGenTlSectionmst.getSectKeyid());
			JSONObject returnData = new JSONObject();
			
			returnData.put("successData", successData);		
			out.print(returnData.toString());
			
	
		}catch(ValidationExceptions e)
		{
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"sectionCreationException");
			errMessage.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
			out.print(errMessage.toString());
		}catch(BusinessApplicationExceptions e)
		{ 
			System.out.println("BusinessApplicationExcepions");
			JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),"sectionCreationException");
			//successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
			//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			successData.put("msg", "Original Id Exists");
			JSONObject returnData = new JSONObject();	
			returnData.put("successData", successData);	
			returnData.put("formClear",false);
			
			//out.print(successData.toString());
			
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

}
