
package com.akranta.tpm.controller;
/*	Created By:Siddharth.A
 * 
 * */

import java.io.IOException;
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
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.OplTlUploadService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.OplUploadServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;


public class OplUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	OplTlUploadService oplTlUploadService;
	CommonFilterService commonFilterService;
    public OplUploadServlet() {
        super();
       /* CommonMessage.debugMsg(" initialising servlet ....");
        try
        {
        	oplService = new OplTlMstServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
		} 
        catch (Exception e)
        {
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
		response.setContentType("text/html");
		response.setContentType("text/json");
		String action = UIUtils.getActionPart(request);
		try {
			oplTlUploadService = (OplUploadServiceImpl)UIUtils.getServiceObject(request,"OplUploadServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

				
      if(action.equals("OplUpload_input.oplUpd")){
		    String oplkeyid=request.getParameter("oplKeyid");
			String oplflid=request.getParameter("oplflid");
			String opldate=request.getParameter("opldate");
			String oplpreparedId=request.getParameter("oplpreparedId");
			String opltheme=request.getParameter("opltheme");
			String opldesc=request.getParameter("opldesc");
			String classificationB=request.getParameter("ClassificationB");
			String classificationI=request.getParameter("ClassificationI");
			String classificationT=request.getParameter("ClassificationT");
			String Mode=request.getParameter("Mode");
			request.setAttribute("oplkeyid",oplkeyid);
			request.setAttribute("oplflid",oplflid);
			request.setAttribute("opldate",opldate);
			request.setAttribute("oplpreparedId",oplpreparedId);
			request.setAttribute("opltheme",opltheme);
			request.setAttribute("opldesc",opldesc);
			request.setAttribute("classificationB",classificationB);
			request.setAttribute("classificationI",classificationI);
			request.setAttribute("classificationT",classificationT);
			request.setAttribute("Mode",Mode);
			RequestDispatcher rd=request.getRequestDispatcher("/pages/OplUpload.jsp");
			rd.forward(request, response);
			
		}
		
		else if( action.equals("OplUpload_save.oplUpd"))
		{
			OplFormBean oplFormBean = new  OplFormBean();
			SaveOplUpload(request,response,oplFormBean);
	    }
		
		else if(action.equals("functionalLoc.oplUpd")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmboplmFactoryid");
			functLocFieldNameBean.setSection("cmboplmSectionid");
			functLocFieldNameBean.setCell("cmboplmCellid");
			functLocFieldNameBean.setMachine("cmboplmMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmboplmFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
			OplFormBean oplFormBean=(OplFormBean)httpSession.getAttribute("oplFormBean");
			FormModes formModes = FormModes.create;//(FormModes)httpSession.getAttribute("AbnormalityFormMode");
			boolean funcLocnView=false;
			String bdmmode=(String)httpSession.getAttribute("bdmmode");
			
			if(UIUtils.isValidKeyId(bdmmode))
				funcLocnView=true;
			
			if(( formModes == FormModes.completion)||funcLocnView==true)
				{formModes = FormModes.view;}
			else if(oplFormBean!=null && oplFormBean.getFormActionMode().equalsIgnoreCase("view"))
				{formModes = FormModes.view;}
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		/*if (action.equals("OplUpload_input.oplUpd") && user!=null) 
		{
			String mode = "Create";
			String Formmode=request.getParameter("frm");
			String oplKeyid = request.getParameter("oplKeyId");
			CommonMessage.debugMsg("Formmode::1"+Formmode);
		//	String mode=request.getParameter("Mode");
		//	String refDocNo = request.getParameter("refDocNo");
			String refDocType = request.getParameter("refDocType");
			CommonMessage.debugMsg("refDocType::1"+refDocType);
			String whywhymod=request.getParameter("whywhymod");
			String Emppillar= request.getParameter("Emppillar");
			//String frmmode = request.getParameter("mod");
			OplTlMst oplTlMst =null;
			OplFormBean oplFormBean = null;
			httpSession.removeAttribute("OplServletFormActionMode");
			//httpSession.setAttribute("OplServletFormActionMode",oplFormBean.getFormActionMode());
			//CommonMessage.debugMsg(" oplFormBean.getFormActionMode "+oplFormBean.getFormActionMode());
			request.setAttribute("Emppillar", Emppillar);
			request.setAttribute("Formmode", Formmode);
			request.setAttribute("oplFormBean", oplFormBean);
			request.setAttribute("oplTlMst", oplTlMst);
			request.setAttribute("oplFormBean", oplFormBean);
			request.setAttribute("oplKeyid", oplKeyid);
			request.setAttribute("User",user.getUsrm_ccno());
			request.setAttribute("whywhymod", whywhymod);
		}*/
	}
    @SuppressWarnings("unchecked")
    private void SaveOplUpload(HttpServletRequest request, HttpServletResponse response,OplFormBean oplFormBean  ) throws BusinessApplicationExceptions,IOException
    {
    	String ResultArea="";
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String oplstatus=request.getParameter("hdnOplmStatus");
    	String oplapprov=request.getParameter("hdnOplmAprovLevel");
    	String empillar=request.getParameter("hdnEmppillar");
    	String formmode=request.getParameter("Oplformmode");
    	CommonMessage.debugMsg(" Inside Save Action Mode :: "+oplstatus);
    	CommonMessage.debugMsg(" Inside Save Action Mode :: "+oplapprov);

    	String status=request.getParameter("status");
    	String ResultAreaB=request.getParameter("classificationB");
    	String ResultAreaT=request.getParameter("classificationT");
    	String ResultAreaI=request.getParameter("classificationI");
    	
    	if( httpSession != null && user != null)
    	{	
    		OplTlMst newOplTlMst = new OplTlMst();
    		newOplTlMst =(OplTlMst)UIUtils.setBeanProperties((Object)newOplTlMst,request);
    		oplFormBean =(OplFormBean) UIUtils.setBeanProperties((Object)oplFormBean,request);
    		
    		newOplTlMst.setOplmCreatedby(user.getUsrm_ccno());
    		UIUtils.displayRequestParamsValue(request);
			CommonMessage.debugMsg("newOplTlMst:flid "+newOplTlMst.getOplmFlid());
    		String openactnpln = request.getParameter("openactnpln");  
    		String filemanager = request.getParameter("filemanager");
    		String Mode =request.getParameter("Mode");
    		String frmMode = request.getParameter("mod");
        	oplFormBean.setFormActionMode(frmMode);
        	oplFormBean.setFormMode(Mode);
                      
        	newOplTlMst.setOplmAprovLevel("-");
        	newOplTlMst.setOplmStatus("-");

			if( ResultAreaB != null )
				ResultArea = ResultAreaB;
		     if( ResultAreaI != null )
				ResultArea +=ResultAreaI;
			 if( ResultAreaT != null )
				ResultArea += ResultAreaT;
		//	CommonMessage.debugMsg("The resultArea:::"+ResultArea);
			//CommonMessage.debugMsg("OPLMClassification"+newOplTlMst.getOplmClassification());
			newOplTlMst.setOplmClassification(ResultArea);

    		OplTlMst existOplTlMst = (OplTlMst)httpSession.getAttribute("oplTlMst"+newOplTlMst.getOplmKeyid());
    		CommonMessage.debugMsg(" Inside Save OPl "+frmMode);
			try
			{
				String docId=(String)httpSession.getAttribute("docOPLId");
				String yyId=(String)httpSession.getAttribute("yyIdOPL");
				if(UIUtils.isValidKeyId(yyId))
					oplFormBean.setYyId(yyId);	
				
				if(UIUtils.isValidKeyId(docId))
					oplFormBean.setDocId(docId);	
				
				 String saveMsg = null ;
				    
					if( newOplTlMst.getOplmKeyid() == null )
					{	
						CommonMessage.debugMsg(" Inside :: If create 1");
						existOplTlMst =	oplTlUploadService.createOPlUpload(newOplTlMst,existOplTlMst,oplFormBean);
						saveMsg = "success-save";
						CommonMessage.debugMsg(" Inside :: ");
					}	
					else
					{   CommonMessage.debugMsg(" Inside :: Else update 1");
					  if(formmode.equals("Approval"))
					  {
					   newOplTlMst.setOplmStatus(oplstatus);
					   newOplTlMst.setOplmAprovLevel(oplapprov);
					  }
					  else{
						  if(oplstatus.equals("R"))
							{
							  newOplTlMst.setOplmStatus("P");
							  newOplTlMst.setOplmAprovLevel("-");
						     }
					  }
						existOplTlMst = oplTlUploadService.updateOPlUpload(newOplTlMst,existOplTlMst,oplFormBean);
						saveMsg = "success-update";
					}
					
				//}
				httpSession.setAttribute("oplTlMst"+existOplTlMst.getOplmKeyid(), existOplTlMst);
				
				CommonMessage.debugMsg("frmMode ="+frmMode);	
				CommonMessage.debugMsg("existOplTlMst.getOplmKeyid()-"+existOplTlMst.getOplmKeyid());
				String successIden;
				JSONObject mode = new JSONObject();
				JSONObject forwardData = new JSONObject();
				forwardData.put("oplKeyId",existOplTlMst.getOplmKeyid());
				CommonMessage.debugMsg("getOPLId"+existOplTlMst.getOplmKeyid());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("oplKeyId",existOplTlMst.getOplmKeyid());
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);
				mode.put("frmMode",frmMode);
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",saveMsg) + 
						" - OPL. No. " + existOplTlMst.getOplmKeyid()) ;
				
				
				  if(UIUtils.isValidKeyId(filemanager)){
					successData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
					successData.put("flid", newOplTlMst.getOplmFlid());
					//CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
					CommonMessage.debugMsg("Inside Flid servlet "+newOplTlMst.getOplmFlid());
					successData.put("filemanager", true);
					successData.put("formClear",false);
				}else
				{
					CommonMessage.debugMsg("Else filemanager");
					successData.put("filemanager", false);
					successData.put("formClear",false);
				}
				
				if(UIUtils.isValidKeyId(openactnpln)){
					successData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
					successData.put("flid", newOplTlMst.getOplmFlid());
					CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
					successData.put("openactnpln", true);
					successData.put("formClear",false);
				}else
				{
					successData.put("openactnpln", false);
					successData.put("formClear",false);
				}
				
				String modeReturn =(String)httpSession.getAttribute("bdmmode");
				CommonMessage.debugMsg("The modeReturn:::::"+modeReturn);
				if(UIUtils.isValidKeyId(modeReturn))
			    successData.put("successData", modeReturn);
				JSONObject returnData = new JSONObject();
				returnData.put("formMode",oplFormBean.getFormActionMode());
				returnData.put("mode", mode);
				returnData.put("formClear",false);
				returnData.put("successData",successData);
				successData.put("oplKeyId",existOplTlMst.getOplmKeyid());
				CommonMessage.debugMsg("The OPLKEYID:::"+existOplTlMst.getOplmKeyid());
				CommonMessage.debugMsg("Mode :"+returnData.toString());
				out.print(returnData.toString());
				out.close();
				
			}
			
			catch(ValidationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplUploadCreationExceptions");
				errMessage.put("formMode",oplFormBean.getFormActionMode());
				out.print(errMessage.toString());
			}
			catch(BusinessApplicationExceptions e)
	          {
	    	    e.printStackTrace();
	    	    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "OplUploadCreationExceptions");
				errMessage.put("tpmException","This Record is already exist");
				errMessage.put("displyMsg", false);			
				out.print(errMessage.toString());
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				CommonMessage.debugMsg("ERR"+e.getStackTrace());
				CommonMessage.debugMsg("ERR"+e.getLocalizedMessage());
				CommonMessage.debugMsg("ERR"+e.getCause());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
			
				out.print(err.toString());
			}
    	}	
    }
}
   
   
   
   
   
   
