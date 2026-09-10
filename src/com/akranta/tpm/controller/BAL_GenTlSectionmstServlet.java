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
import com.akranta.tpm.bean.BAL_GenTlSectionmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenTlSectionmstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlLocationmstServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenTlSectionmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;

	public class BAL_GenTlSectionmstServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int count;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
		BAL_GenTlSectionmstService  genTlSectionmstService ;
		CommonFilterService commonFilterService;
	
    public BAL_GenTlSectionmstServlet() {
        super();
        /*	System.out.println(" initialising servlet ....");
        try {
        	genTlSectionmstService = new GenTlSectionmstServiceImpl();
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
	 
			
			String dispatchUrl = null; 
			
			if (action.equals("section_input.sect")) 
			{
				System.out.println("inside action");
				dispatchUrl = "/pages/sectionmaster.jsp";
			}
			else if(action.equals("section_save.sect"))
			{	
				System.out.println("inside action");
				BAL_GenTlSectionmstBean genTlSectionmstBean = new BAL_GenTlSectionmstBean();
		
		        savesect(request,response,genTlSectionmstBean);
			}
			else if(action.equals("section_recall.sect"))
			{	
				
				ServletOutputStream out = response.getOutputStream();
				BAL_GenTlSectionmst genTlSectionmst = genTlSectionmstService.select(request.getParameter("Sect"));
				
				System.out.println("SectionmstData");
				response.setContentType("text/html");
				httpSession.setAttribute("genTlSectionmst",genTlSectionmst);
				JSONObject  section =  UIUtils.fromTpmModel(genTlSectionmst);
				
				JSONObject returndata = new JSONObject();
				returndata.put("section", section);
				out.print(returndata.toString());
				
		    	
			}
	
			/*else if( action.equals("combo_genTlSectionmst.sect"))
			{
			try {
				
				List<ComboBox>  sectKeyid = genTlSectionmstService.getGenTlSectionmstcombo("");
				
				UIUtils.writeComboBox(response, sectKeyid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//commonFilterService.getEmployeeComboList(employee);
			}*/
			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
		}
	}
    private void savesect(HttpServletRequest request, HttpServletResponse response,BAL_GenTlSectionmstBean genTlSectionmstBean  ) throws IOException{
		
    	System.out.println("Save caled");
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		BAL_GenTlSectionmst existGenTlSectionmst = (BAL_GenTlSectionmst)httpSession.getAttribute("genTlSectionmst"); 
    		BAL_GenTlSectionmst newGenTlSectionmst = new BAL_GenTlSectionmst();
			newGenTlSectionmst.setSectCreatedby(user.getUsrm_ccno());
			
			newGenTlSectionmst =(BAL_GenTlSectionmst)UIUtils.setBeanProperties((Object)newGenTlSectionmst,request);
			genTlSectionmstBean =(BAL_GenTlSectionmstBean) UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
			System.out.println("MAK name  :"+newGenTlSectionmst.getSectName());
			System.out.println("MAK name  :"+newGenTlSectionmst.getSectCode());
			System.out.println("MAK name  :"+newGenTlSectionmst.getSectFactoryid());
			System.out.println("MAK name  :"+newGenTlSectionmst.getSectCompanyid());
			try{
				System.out.println("  newgenTlSectionmst.getSectKeyid() " +  newGenTlSectionmst.getSectKeyid());
				if( 
					newGenTlSectionmst.getSectKeyid() == null )
				{	
					System.out.println("Save caled inside");
					existGenTlSectionmst = genTlSectionmstService.create(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
				}	
				else{
					existGenTlSectionmst = genTlSectionmstService.update(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
				}
				
				httpSession.setAttribute(existGenTlSectionmst.getSectKeyid(), existGenTlSectionmst);
				httpSession.setAttribute("GenTlSectionmst", existGenTlSectionmst);
				String formBeanIdentifier = "genTlSectionmstBean"+genTlSectionmstBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,genTlSectionmstBean);
						
				JSONObject mode = new JSONObject();
				mode.put("formMode",genTlSectionmstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("SectKeyid",existGenTlSectionmst.getSectKeyid() );
				persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				forwardData.put("SectKeyid",existGenTlSectionmst.getSectKeyid() );
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);
				out.print(mode.toString());
				//out.print()	
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "sectionCreationException");
				errMessage.put("fromMode",genTlSectionmstBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				
				CommonFunctions.debugMsg("validationbusiness   "+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "sectionCreationException");
				CommonFunctions.debugMsg(" e " + errMessage );
				out.print(errMessage.toString());
			}
			
			catch(Exception e)
			{
				
				System.out.println("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}	
    }

}
