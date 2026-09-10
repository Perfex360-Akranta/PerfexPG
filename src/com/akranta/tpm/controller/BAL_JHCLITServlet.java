/*Author : Manikandan*/
package com.akranta.tpm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CliTlStandardFormBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlYycountermeasurelink;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.BAL_GenVwToolcategory;
import com.akranta.tpm.model.BAL_PlmTlMultiplemethodsmst;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.service.BAL_CliTlStandardsService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenVwToolcategoryService;
import com.akranta.tpm.service.impl.BAL_CliTlStandardServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenVwToolcategoryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.google.common.base.Objects;
import com.akranta.tpm.service.api.CliTlStandardsServiceApi;

/**
 * Servlet implementation class JH_CLIT
 */
//
public class BAL_JHCLITServlet extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	 BAL_CliTlStandardsService cliTlStandardsService;
	 CommonFilterService commonFilterService;
	 BAL_GenVwToolcategoryService genVwToolcategoryService ;
	  BAL_CliTlStandardFormBean actionpassed;
	   CliTlStandardsServiceApi cltiapi;
    public BAL_JHCLITServlet() {
        super();
	 /*       try {
	        	cliTlStandardsService = new CliTlStandardServiceImpl();
				commonFilterService = new CommonFilterServiceImpl();
				genVwToolcategoryService = new GenVwToolcategoryServiceImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // TODO Auto-generated constructor stub
	         * 
	         */
	    }
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	 private void initilizeInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
	 {    	
			CommonFunctions.debugMsg("Find Mode  "+mode);
			//CliTlStandardFormBean cliTlStandardFormBean = new CliTlStandardFormBean(mode);
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
	    	String jhclitId = request.getParameter("stdId");
	    	BAL_CliTlStandards  cliTlStandards  = new BAL_CliTlStandards();
		 	/*if(mode.equals(FormModes.create)){
				request.setAttribute("plmTlGenmaintenance",plmTlGenmaintenance );
			}*/
	    	String mchId = request.getParameter("mchId");
	    	cliTlStandards.setClisMachineid(mchId);
		 	httpSession.removeAttribute("jhClitStandard_Servlet");
		 	if( UIUtils.isValidKeyId(jhclitId)){
				try {
					//String jhclitId = (String) httpSession.getAttribute("jhclitId");
					//CommonFunctions.debugMsg(jhclitId+"jhclitId");
					 cliTlStandards = cliTlStandardsService.jhclitformfill(jhclitId);
					 cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
					 cliTlStandardFormBean.setJhinputkeyid(jhclitId);
		             httpSession.setAttribute("jhClitStandard_Servlet", cliTlStandards); 
		    		 request.setAttribute("cliTlStandards", cliTlStandards);
		    		 
					//request.setAttribute("generalMaintainanceBean",generalMaintainanceBean);
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		 	}
		 	httpSession.removeAttribute("cliTlStandardFormBean");
			httpSession.setAttribute("cliTlStandardFormBean", cliTlStandardFormBean);
			// httpSession.removeAttribute("actionpassed");//clear session of mode stored
			//CommonFunctions.debugMsg("chk disableForm status  "+generalMaintainance.getDisableForm());
			request.setAttribute("cliTlStandardFormBean", cliTlStandardFormBean);
	 }
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		HttpSession httpSession = request.getSession(false);
		ComboFilter currentFilter = new ComboFilter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String action = UIUtils.getActionPart(request);
		//CommonFunctions.debugMsg("action " + action);
		ComboFilter comboFilter = new ComboFilter();
		try {
			cliTlStandardsService = (BAL_CliTlStandardServiceImpl)UIUtils.getServiceObject(request,"BAL_CliTlStandardServiceImpl");
			genVwToolcategoryService = (BAL_GenVwToolcategoryServiceImpl)UIUtils.getServiceObject(request,"BAL_GenVwToolcategoryServiceImpl");
			commonFilterService =  (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			cliTlStandardsService.BAL_CliTlStandardsServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		
		if (action.equals("jhClit_input.baljhclit")||action.equals("jhClitModification_input.baljhclit")||action.equals("jhClitview_input.baljhclit")) 
		{ 
			String fromPage  =  request.getParameter("fromPage");
			request.setAttribute("fromPage", fromPage);
			if (action.equals("jhClitview_input.baljhclit")){
				
				httpSession.setAttribute("clitFormMode", FormModes.view);
				String vwMode ="view";
				request.setAttribute("formMode",vwMode);
				CommonFunctions.debugMsg("makkk   "+request.getAttribute("formMode"));
			}
			else if (action.equals("jhClitModification_input.baljhclit")){
				FormModes mode = FormModes.modify; 
				initilizeInputMode(mode, request, httpSession);	
			}
			else if (action.equals("jhClit_input.baljhclit")){
				FormModes mode = FormModes.create; 	
			
			}
			//CommonFunctions.debugMsg("input the jsp");
			// there is nothing to be done
			//CommonFunctions.debugMsg(" clitKeyid  inside " );
			String clitKeyid = request.getParameter("clitKeyid");
			BAL_CliTlStandards cliTlStandards =null;
			//CommonFunctions.debugMsg(" clitKeyid " + clitKeyid);
			  
			if( clitKeyid != null)
				cliTlStandards = (BAL_CliTlStandards)httpSession.getAttribute(clitKeyid);
			
			if( cliTlStandards == null)	
				cliTlStandards = new  BAL_CliTlStandards();
			else
				CommonFunctions.debugMsg(" cliTlStandards " + cliTlStandards.getClisKeyid());
			
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
			cliTlStandards.setClisCreatedby(user.getUsrm_ccno());
			cliTlStandardFormBean.setResponsibility(user.getUsrm_ccno());
			
			UIUtils.displayRequestParamsValue(request);
			String bdmmode = request.getParameter("bdmmode");
			String mchId = request.getParameter("machineID");
			String dockKey = request.getParameter("docId");
			String yyId = request.getParameter("yyId");
			String grid = request.getParameter("grid");
			httpSession.setAttribute("yyId",yyId);
			CommonFunctions.debugMsg("dockKey....."+dockKey);
			httpSession.setAttribute("dockKey",dockKey);
			if(UIUtils.isValidKeyId(mchId)){
				CommonFunctions.debugMsg(bdmmode+"mchId  :"+mchId);
				request.setAttribute("BdmMachineId",mchId);
				request.setAttribute("bdmmode",bdmmode);
				httpSession.setAttribute("bdmmode",bdmmode);
			}
			request.setAttribute("cliTlStandards", cliTlStandards);
			request.setAttribute("cliTlStandardFormBean", cliTlStandardFormBean);
			CommonFunctions.debugMsg("test.....");
		}
		
		else if( action.equals("jhClit_save.baljhclit"))
		{	
			//CommonFunctions.debugMsg("inside sace action");
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
			saveClit(request,response,cliTlStandardFormBean);
			
	    }
		 //for update jhClitModification_save.jhclit
		else if( action.equals("jhClitModification_save.baljhclit"))
		{	
			//CommonFunctions.debugMsg("inside modification action");
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
			saveClit(request,response,cliTlStandardFormBean);
			
	    }
		//MultipleCLTI
		else if (action.equals("MultipleClitStd_save.baljhclit")) {
			saveMultipleClit(request, response);
		}
		else if (action.equals("MultipleClitStd_remove.baljhclit")) {
		    removeMultipleClit(request, response);
		}
		else if (action.equals("deleteClitBlockDiagImg.baljhclit")) {
		    deleteClitBlockDiagImg(request, response);
		}
		else if (action.equals("saveClitEqpImage.baljhclit")) {
		    saveClitEqpImage(request, response);
		}
		
		else if( action.equals("jhClitview_save.baljhclit"))
		{	
			CommonFunctions.debugMsg("inside View action");
			PrintWriter out = response.getWriter();
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
			JSONObject err = new JSONObject();
			err.put("tpmException","Cannot Save in view Mode ");
			out.print(err.toString());
	    }
		else if( action.equals("jhClitModification_delete.baljhclit"))/*modified on 03.05.12*/
		{	
		//CommonFunctions.debugMsg("Inside the DELETE");
			String inactivedate = request.getParameter("inactivedate");
		BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
		cliTlStandardFormBean.setInactiveDate(inactivedate);
			deleteClit(request,response,cliTlStandardFormBean);
			
	    }
		else if( action.equals("combo_clitTrade.baljhclit"))
		{
			try {
				comboFilter=UIUtils.fillComboFilter(request);
					CommonFilter commonFilter=new CommonFilter();
				   	//currentFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					List<ComboBox>  clisTradeid = cliTlStandardsService.getClitTradeCombo(commonFilter,comboFilter);
					//UIUtils.writeComboBox(response, clisTradeid);
					UIUtils.writeComboBox(response, clisTradeid ,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if(action.equals("combo_clitwhtfreq.baljhclit"))
	    {
	    	CommonFunctions.debugMsg("inside clitwhtfreq jh clit standarad");
	 	    PrintWriter out = response.getWriter();
	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "selectwhatfreq"));
	    	CommonFunctions.debugMsg("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "selectwhatfreq"));
	    }
		else if(action.equals("combo_clitclassification.baljhclit"))
	    {
	    	CommonFunctions.debugMsg("inside clitclassification jh clit standarad");
	 	    PrintWriter out = response.getWriter();
	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "selectclassification"));
	    	CommonFunctions.debugMsg("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "selectclassification"));
	    }
		else if( action.equals("combo_clitShift.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
				   	//currentFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					comboFilter=UIUtils.fillComboFilter(request);
					String factoryId=request.getParameter("factId");
					CommonFunctions.debugMsg(" fact ID  "+factoryId);
					List<ComboBox>  clisShiftid = cliTlStandardsService.getClitShiftCombo(commonFilter,factoryId,comboFilter);
					//UIUtils.writeComboBox(response, clisShiftid);
					UIUtils.writeComboBox(response, clisShiftid ,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if( action.equals("combo_clitDesignation.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
				   	//currentFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					comboFilter=UIUtils.fillComboFilter(request);
					String desgId = request.getParameter("desgId");
					CommonFunctions.debugMsg("desgId    :>"+desgId);
					List<ComboBox>  clisDesigid = cliTlStandardsService.getclitDesignationCombo(commonFilter,desgId,comboFilter);
					//UIUtils.writeComboBox(response, clisDesigid);
					UIUtils.writeComboBox(response, clisDesigid ,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		else if( action.equals("combo_clitResponsibility.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
					comboFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					//CommonFunctions.debugMsg("desgId    :>"+desgId);
					List<ComboBox>  clisrespid = cliTlStandardsService.getclitResponsibilityCombo(commonFilter,"",comboFilter);
					UIUtils.writeComboBox(response,clisrespid,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if( action.equals("combo_clitpreparedby.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
				    comboFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					List<ComboBox>  clisprpbyid = cliTlStandardsService.getclitpreparedbyCombo(commonFilter,"");
					UIUtils.writeComboBox(response, clisprpbyid,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//for filling machine area combo in additional info 
		else if( action.equals("combo_clitMachineArea.baljhclit"))
		{
			try {

				    comboFilter=UIUtils.fillComboFilter(request);
					CommonFilter commonFilter=new CommonFilter();
				   
					List<ComboBox>  clismachineareaid = cliTlStandardsService.getclitmachineAreaCombo(commonFilter,comboFilter);
					
					UIUtils.writeComboBox(response, clismachineareaid ,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
//for dept mgr combo_clitDeptmgr.jhclit
		else if( action.equals("combo_clitDeptmgr.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
					comboFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					List<ComboBox>  clismachineareaid = cliTlStandardsService.getclitDeptmgrCombo(commonFilter,"",comboFilter);
					UIUtils.writeComboBox(response, clismachineareaid,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//for Sect mgr		combo_clitSectMgr.jhclit
		else if( action.equals("combo_clitSectMgr.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
				   //	currentFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					List<ComboBox>  clismachineareaid = cliTlStandardsService.getclitSectMgrCombo(commonFilter,"");
					UIUtils.writeComboBox(response, clismachineareaid,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//for Group Lead combo_clitGrouplead.jhclit
		else if( action.equals("combo_clitGrouplead.baljhclit"))
		{
			try {
					CommonFilter commonFilter=new CommonFilter();
				   	//currentFilter=UIUtils.fillComboFilter(request);
					//commonFilter.setComboFilter(currentFilter);
					List<ComboBox>  clismachineareaid = cliTlStandardsService.getclitGroupleadCombo(commonFilter,"");
					UIUtils.writeComboBox(response, clismachineareaid,comboFilter);
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		String dispatchUrl = null;
		if (action.equals("jhClit_input.baljhclit")||action.equals("jhClitModification_input.baljhclit")||action.equals("jhClitview_input.baljhclit"))
		 	{ 
				String mode = null;
				
				httpSession.setAttribute("machineIDmain",request.getParameter("machineID"));
				String machineIDmain = (String) httpSession.getAttribute("machineIDmain");
				
				if (UIUtils.isValidKeyId(machineIDmain)) {
				    try {
				    	String fileDir = getServletContext().getRealPath("/tmp/images/") + File.separator;
				        String imagePath = UIUtils.getImagePath(request);
				        List<GenTlAllmoduleimgfile> clitEqpImages = cliTlStandardsService.getClitEqpImages(machineIDmain, fileDir, imagePath);
				        request.setAttribute("clitEqpImages", clitEqpImages);
				        request.setAttribute("imagePath", imagePath);
				    } catch (Exception e) {
				        CommonMessage.debugMsg("getClitEqpImages inline failed: " + e.getMessage());
				    }
				}
				//CommonFunctions.debugMsg("Input called  :"+action);
				
				//CommonFunctions.debugMsg("--------------------------~~~"+machineIDmain);
				if(action.equals("jhClit_input.baljhclit")){
					//CommonFunctions.debugMsg("Input/view called");
					mode = "create";
					String bdmMod = (String)httpSession.getAttribute("bdmmode");
					if(UIUtils.isValidKeyId(bdmMod))
					{
						CommonFunctions.debugMsg("bdmMod MAK     "+bdmMod);
						mode = "modify";
					}
					else{
						CommonFunctions.debugMsg("bdmMod elese     "+bdmMod);
						mode = "create";
					}
					//CommonFunctions.debugMsg("~~~~~~~~~~"+ mode);
			    }else if(action.equals("jhClitModification_input.baljhclit")){
					mode = "modify";
			    }else{
			    	mode = "view";
				}
				  request.setAttribute("fMode",mode);
 //ActionmodeBean
			  BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean(mode);
			  httpSession = request.getSession(false);
			  String yyId = request.getParameter("yyId");
			  httpSession.setAttribute("yyId",yyId);
			  httpSession.setAttribute("JHCLITServlet_cliTlStandardFormBean",cliTlStandardFormBean);
			//  httpSession.setAttribute("cliTlStdFrmBn",cliTlStandardFormBean.getDisableForm());
			 // RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/ClitStandardsform.jsp");
			  CommonFunctions.debugMsg("before going jsp page....     ");
			  String flid = request.getParameter("flid");
			  CommonFunctions.debugMsg("before going jsp page....     "+flid);
			  request.setAttribute("flid",flid);
			  request.setAttribute("machineID",request.getParameter("machineID"));
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/JHCLITStandardMainGrid.jsp"); 
			  rd.forward(request, response);
			  
		 	}
		else if(action.equals("multipleEntry_input.baljhclit"))
		{
		    String machId = request.getParameter("machId");
		    String flid   = request.getParameter("flid");
		    request.setAttribute("machId",   machId);
		    request.setAttribute("flid",     flid);
		    request.setAttribute("currentDate", CommonFunctions.getDate());
		    
		    request.setAttribute("saveUrl", "MultipleClitStd_save.baljhclit");

		    RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/MultipleEntry.jsp");
		    rd.forward(request, response);
		}
		//Hari-18/06/2026
		else if( action.equals("multipleClitStd_getCol.baljhclit") )
		{	
			CommonFunctions.debugMsg("inside getcol");
			
			PrintWriter out = response.getWriter();
			String flid =request.getParameter("flid");
			String mchId =request.getParameter("machineID");
			String grid = request.getParameter("grid");
			CommonFunctions.debugMsg("flid : "+flid);
			CommonFunctions.debugMsg("grid : "+grid);
			       
					CommonFunctions.debugMsg("grid : "+grid);
					
					if(UIUtils.isValidKeyId(grid) && "clit".equals(grid))
					{
					 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "MultipleClitStd"));
					 populateCommonFilter(request,"clitStandardCommonFilter",true);
					}
					else
					{
						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "MultipleClitStd"));
					}
					
			}
		else if (action.equals("multipleClitStd_getData.baljhclit")) {
		    try {
		        String machId = request.getParameter("machId");
		        String flId   = request.getParameter("flid");

		        if (!UIUtils.isValidKeyId(machId))
		            machId = request.getParameter("cmbMulClisMachineid");

		        CommonFunctions.debugMsg("multipleClitStd_getData machId=" + machId + " flId=" + flId);

		        CommonFilter commonFilter = new CommonFilter();
		        commonFilter.setMachineId(machId);
		        commonFilter.setFlid(flId);
		        commonFilter.setViewClick('Y');
		        String fileName = UIUtils.TPM_TEMPIMG_DIR;

		        PrintWriter out = response.getWriter();
		        List<String[]> dataList = cliTlStandardsService.getMultipleClitList(commonFilter,fileName);
		        JSONObject gridData = UIUtils.convertToJqGridTableObject(dataList, request, 1, 2);
//		        JSONObject gridData = UIUtils.convertToJqGridTableObject(dataList, request, 3, 1);
		        
		        out.println(gridData);

		    } catch (Exception e) {
		        CommonFunctions.debugMsg("multipleClitStd_getData error: " + e.getMessage());
		    }
		}
		
		 else if( action.equals("jhClit_mcharea.baljhclit"))
			{	
			 httpSession.removeAttribute("jhclitmachineID");
			  //CommonFunctions.debugMsg("Out the jsp");
			  CommonFunctions.debugMsg("hidden------------------------------------"+request.getParameter("mchId"));
			  String machineID=request.getParameter("mchId");
			  httpSession.setAttribute("jhclitmachineID", machineID);
			  request.setAttribute("machineID", machineID);
			 
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/JH CLIT_Standard1.jsp"); 
			  rd.forward(request, response); 
			  
		    }
//for loading third grid
		 else if( action.equals("jhClit_grid3.baljhclit"))
			{	
			 CommonFunctions.debugMsg("Out the jsp");
			
			 String assemblyID=request.getParameter("cmbAssmbid");
			 String machAreaName = request.getParameter("machAreaName");
			 String mchId = request.getParameter("machineId");
			 String grid = request.getParameter("grid");
			 String flid = request.getParameter("flid");
			 CommonFilter commonFilter = new CommonFilter();
			 
			 CommonFunctions.debugMsg("machAreaName  :"+mchId );
			 httpSession.setAttribute("jhclitassemblyID", assemblyID);
		     request.setAttribute("assemblyID", assemblyID);
		     request.setAttribute("machAreaName", machAreaName);
		     request.setAttribute("machineId", mchId);
		     request.setAttribute("grid", grid);
		     request.setAttribute("flid", flid);
		     httpSession.setAttribute("machAreaName", machAreaName);
		     String AssId= (String) httpSession.getAttribute("jhclitassemblyID");
		     CommonFunctions.debugMsg("test assm iD  :" +flid);
		     if (UIUtils.isValidKeyId(mchId)) {
//		         String fileDir = UIUtils.TPM_TEMPIMG_DIR;
		    	 String fileDir = getServletContext().getRealPath("/tmp/images/") + File.separator;
		         String imagePath = UIUtils.getImagePath(request);
		         List<GenTlAllmoduleimgfile> clitEqpImages = cliTlStandardsService.getClitEqpImages(mchId, fileDir, imagePath);
		         request.setAttribute("clitEqpImages", clitEqpImages);
		         request.setAttribute("imagePath", imagePath);
		     }
		     RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/JH_CLIT_Standard3.jsp"); 
			 rd.forward(request, response); 
			// request.removeAttribute("machAreaName");
			 
			 }
		//excel for third grid
		/*	else if( action.equals("jhClit_getExcel.jhclit")){
					CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("clitStandardCommonFilter");
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModeljhstandard");
					JSONObject colModel = JSONObject.fromString(tableModel);
					colModel.put("title", "JHCLISTANDARD");
					String rptFormat = ExcelUtils.getFormat(request);
					Workbook wb;
					try {
						wb = cliTlStandardsService.clistdrptExportExcel(commonFilter,colModel,rptFormat);
						commonFilter.setFromRow(tmpFromRow);
						ExcelUtils.writeToResponse(response, wb, "JHCLISTANDARD", rptFormat);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			*/
		
		 else if( action.equals("jhClit_getExcel.baljhclit") )
			{	
		    	httpSession = request.getSession(false);
		    	String flid = request.getParameter("flid");
				String mchID =  request.getParameter("mchID");
		        CommonFilter commonFilter = populateCommonFilter(request,"clitStandardCommonFilter",false);
				commonFilter.setFlid(flid);
				commonFilter.setMachineId(mchID);
	            CommonFunctions.debugMsg("gridFilter "+commonFilter.getGridFilter());
				JSONObject colmodel = UIUtils.getXlColModel( request, response);
				colmodel.put("title","Clit");
	            String format = ExcelUtils.getFormat(request);				
				Workbook wb = cliTlStandardsService.getClitExcel(colmodel,format,commonFilter);
	            ExcelUtils.writeToResponse(response, wb, "JHCLISTANDARD", format);
		    	
			}
		 
		
		
//for loading first grid
		    else if( action.equals("grid_jhclitcount.baljhclit"))
			{	
			  //CommonFunctions.debugMsg("Out the jhclitcount");
			  RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/jhclitcountgrid.jsp"); 
			  rd.forward(request, response); 
		    }
//for loading form page
		    else if( action.equals("jhClit_frm.baljhclit"))
			{	
				httpSession.setAttribute("jhclitId",request.getParameter("stdId"));
			    CommonFunctions.debugMsg("jhclitId "+request.getParameter("stdId"));
			    String machArea=(String)httpSession.getAttribute("machAreaName");
			    request.setAttribute("machAreaName", machArea);
				BAL_CliTlStandardFormBean cliTlStandardFormBean = (BAL_CliTlStandardFormBean) httpSession.getAttribute("JHCLITServlet_cliTlStandardFormBean");
				String jhclitId = (String) httpSession.getAttribute("jhclitId");
				FormModes mode = FormModes.modify;
				initilizeInputMode(mode, request, httpSession);	
				if(cliTlStandardFormBean.getActionmode().equals("create")&& UIUtils.isValidKeyId(jhclitId)){
					CommonFunctions.debugMsg("jhclitId  "+jhclitId);
					cliTlStandardFormBean.setDisableForm("true");
				}
				else if(cliTlStandardFormBean.getActionmode().equals("create")&& !UIUtils.isValidKeyId(jhclitId)){
					CommonFunctions.debugMsg("jhclitId  "+jhclitId);
					cliTlStandardFormBean.setDisableForm("false");
				}
				
				request.setAttribute("cliTlStandardFormBean", cliTlStandardFormBean) ;
				CommonFunctions.debugMsg(cliTlStandardFormBean.getActionmode()+"~~~~"+cliTlStandardFormBean.getDisableForm());
			    RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/ClitStandardsform.jsp"); 
			    rd.forward(request, response); 
		    }
 
//for recall form components
		    else if( action.equals("jhClit_frmload.baljhclit"))
			{
		    	 //CommonFunctions.debugMsg("Inside form fill action");
				 ServletOutputStream out = response.getOutputStream();
				 String jhclitId = (String) httpSession.getAttribute("jhclitId");
				 //CommonFunctions.debugMsg(jhclitId+"jhclitId");
				 BAL_CliTlStandards  cliTlStandards = cliTlStandardsService.jhclitformfill(jhclitId);
				 BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
				 cliTlStandardFormBean.setJhinputkeyid(jhclitId);
				/*cliTlStandards.getMethodDetail();*/
					response.setContentType("text/html");		
					httpSession.setAttribute("cliTlStandards",cliTlStandards);
					JSONObject  jhclitJSONObj =  UIUtils.fromTpmModel(cliTlStandards);
					JSONObject returndata = new JSONObject();
					returndata.put("jhclitsstdid", jhclitJSONObj);	
					//CommonFunctions.debugMsg("this is the return data"+returndata.toString());
					out.print(returndata.toString());
			}

		
//for filling fact sect cell on select machine
		    else if( action.equals("jhClit_fillcombo.baljhclit"))
			{
		    	//CommonFunctions.debugMsg("Inside form fill action"+request.getParameter("eqpId"));
				httpSession.setAttribute("machineHirerachyId",request.getParameter("eqpId"));
			    //CommonFunctions.debugMsg("machineHirerachyId"+request.getParameter("eqpId"));
				String machineHirerachyId = (String) httpSession.getAttribute("machineHirerachyId");
				//CommonFunctions.debugMsg(machineHirerachyId+"machineHirerachyId");
				
				try {
					List<String[]> eqpautofill = commonFilterService.getMachineHierarchy(machineHirerachyId);
					UIUtils.writeMachineHirearchy(response,eqpautofill);
					
				} 
			catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
//for adding Machine Area
		   /* else if( action.equals("addmachine_input.jhclit")){
		    	RequestDispatcher rd = request.getRequestDispatcher("/pages/jhclit/addMachine.jsp"); 
				rd.forward(request, response); 
		    }
		    else if(action.equals("addmachine_getCol.jhclit"))
		    {
		    	//CommonFunctions.debugMsg("inside getcol action of add machine new");
		 	    PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelAddMach"));
		    }
		    else if(action.equals("addmachine_getData.jhclit"))
		    {
		 	   //CommonFunctions.debugMsg("inside get data");
		 	   String MachineID = request.getParameter("machineID");
		 	   String  getEquipmentId  = cliTlStandardsService.geteqpGroup(MachineID);
		 	   //CommonFunctions.debugMsg("getEquipmentId  "+	getEquipmentId);
		 	   PrintWriter out = response.getWriter();
		 	   List<String []> addmachine  = cliTlStandardsService.getAddMachine(MachineID,getEquipmentId);
		 	   //CommonFunctions.debugMsg(addmachine.size());
		 	   JSONObject addmachinedata = UIUtils.convertToJqGridTableObject(addmachine,request,0,0);
		 	   out.println(addmachinedata);
		 	 	//CommonFunctions.debugMsg(addmachinedata);
		    }*/
		    	
//for clicking view button 
		    else if( action.equals("jhClit_clickview.baljhclit"))
			{
		    	CommonFunctions.debugMsg("Inside form fill---View  action"+request.getParameter("machineID"));
		    	httpSession.setAttribute("machineIDview",request.getParameter("machineID"));
				String machineIDview = (String) httpSession.getAttribute("machineIDview");
				String flid = request.getParameter("flid");
				request.setAttribute("selctMachineId",machineIDview);//for sending to jsp of 1st grid to load data based on machine
			    //CommonFunctions.debugMsg("button click   :-"+machineIDview);
				request.setAttribute("flid", flid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/BAL_jhclit/jhclitcountgrid.jsp"); 
				rd.forward(request, response); 
				
			}
	    //for tool method grid
		    else if(action.equals("tool_input.baljhclit"))
		    {
		    	//colModeltools
		    	//CommonFunctions.debugMsg("tools input called");
		    	//CommonFunctions.debugMsg("selectedElement  ---"+request.getParameter("selectedElement"));
		    }
		    else if(action.equals("tool_getCol.baljhclit"))
		    {
		    	//colModeltools
		    	String getValue=request.getParameter("selectedElement");
		    	httpSession.setAttribute("ToolTreevalue", getValue);
		    	String getvaluefrmTree=(String) httpSession.getAttribute("ToolTreevalue");
		    	//CommonFunctions.debugMsg("getvaluefrmTree  --"+getvaluefrmTree);
		    	//CommonFunctions.debugMsg("selectedElement  ---"+getValue);
		    	PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModeltools"));
		    	
		    }
		    else if(action.equals("tool_getData.baljhclit"))
		    {
		    	try
				{	
		    		String getvaluefrmTree=(String) httpSession.getAttribute("ToolTreevalue");
			    	//CommonFunctions.debugMsg("getvaluefrmTree  --"+getvaluefrmTree);
		    		//CommonFunctions.debugMsg("1ss");
					List<Object> toolTreeList;
					toolTreeList =(List<Object>) httpSession.getAttribute("ToolTreevalue");
					//CommonFunctions.debugMsg("2df");
					PrintWriter out = response.getWriter();
					
					httpSession.setAttribute("JHClitServlet", toolTreeList);
					//CommonFunctions.debugMsg("3df");
				    JSONObject tooltreeData = convertToToolTreeTblObject(toolTreeList);
				    //CommonFunctions.debugMsg("tooltreeData getData="+tooltreeData);
				    out.println(tooltreeData);
				}
				catch(Exception e)
				{
					//CommonFunctions.debugMsg("Multi Method  Exception"+e.getMessage());
				}
		    
		    }
		/*for filling tool grid in modification mode toolmod_input.jhclit*/
		    else if(action.equals("toolmod_input.baljhclit"))
		    {
		    	
		    }
		    else if(action.equals("toolmod_getCol.baljhclit"))
		    {
		    	String toolClisKeyid = request.getParameter("toolClisId");
		    	httpSession.setAttribute("toolclisid", toolClisKeyid);
		    	PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModeltools"));
		    }
		    else if(action.equals("toolmod_getData.baljhclit"))
		    {
		    	String toolclisid = (String)httpSession.getAttribute("toolclisid");
		    	PrintWriter out = response.getWriter();
		    	List<String []> jhclittoolret  = cliTlStandardsService.getAlljhclittools(toolclisid);
 			 	JSONObject jhclittooldata = UIUtils.convertToJqGridTableObject(jhclittoolret,request,0,0);
 			 	out.println(jhclittooldata);
		    }
		/*tree*/
		
		    else if( action.equals("tool_tree.baljhclit") )
			{	 
		    	CommonFunctions.debugMsg("inside tool_tree action");
		    	String toolRowid = request.getParameter("rowIds");
	        	CommonFunctions.debugMsg("toolRowid --"+toolRowid);
		    /*	Enumeration<String> params = request.getParameterNames() ;
				while(params.hasMoreElements() )
				{
					//CommonFunctions.debugMsg("parms  " + params.nextElement());
				}
			*/	
				String parentid = request.getParameter("id");
				
				//CommonFunctions.debugMsg( " menuID " + parentid);
				parentid  = parentid.equals("0") ? "0" :parentid;
				
		    	response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        try {
		        	//MenuTree menuTree = new MenuTree();
		        	////CommonFunctions.debugMsg(toolpickupList.size());
		        	JSONArray jSONArray = new JSONArray();
		        	if( (parentid.equals("0") )){
		        		
		        		jSONArray =getJstreeNodeObject("1",parentid,"TOOLS","R");
		        		CommonFunctions.debugMsg("jSONArray  :"+jSONArray);
		        			
		        	}else{
		        		BAL_GenVwToolcategory genVwToolcategory = new BAL_GenVwToolcategory();
			        	if(parentid.equals("1")){
			        		//CommonFunctions.debugMsg("parentid = one");
			        		genVwToolcategory.setElementtype("C");
			        		//CommonFunctions.debugMsg("parentid ");
			        	}
			        	else{
			        		//CommonFunctions.debugMsg("parentid = 0");
			        		genVwToolcategory.setElementtype("T");
			        		
			        		genVwToolcategory.setParentid(parentid);
			        	}
		        		//genVwToolcategory.setParentid(parentid);
			        	
		        		List <BAL_GenVwToolcategory> toolpickupList = genVwToolcategoryService.getAllTool(genVwToolcategory);
		        		jSONArray  = getToolsTreeArray(toolpickupList,toolRowid);
		        	}
		        		
		        		
		            out.print(jSONArray);
	        	    jSONArray=null;
		            
		        }catch(Exception e){
		            CommonFunctions.debugMsg("exceptuion  :"+e);
		        }
		        finally {
		            out.close();
		        }
			}
	//END
		/*for Functional Location*/
 			else if(action.equals("functionalLoc.baljhclit")){
 				
 				
 				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
// 				functLocFieldNameBean.setFactory("cmbClisFactory");
 				functLocFieldNameBean.setSbu("cmbClisFactory");
 				functLocFieldNameBean.setPbu("cmbClisPbu");
 				functLocFieldNameBean.setSection("cmbClisSectionid");
 				functLocFieldNameBean.setCell("cmbClisClisid");
 				functLocFieldNameBean.setMachine("cmbClisEquipmentid");
 				functLocFieldNameBean.setFunctionalLocId("cmbClisFlid");
// 				functLocFieldNameBean.setFactMandatory(true);
// 				functLocFieldNameBean.setSectMandatory(true);
// 				functLocFieldNameBean.setCellMandatory(true);
// 				functLocFieldNameBean.setMachMandatory(true);
 				
 				FormModes formModes = (FormModes)httpSession.getAttribute("clitFormMode");
 				String bdMode = (String)httpSession.getAttribute("bdmmode");
 				CommonFunctions.debugMsg("bdmodeFuncloc**   :"+bdMode);
 				if(UIUtils.isValidKeyId(bdMode)){
 					CommonFunctions.debugMsg("bdmodeFuncloc--   :"+bdMode);
 					formModes = FormModes.view;
 				}
 				
 				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
 				
 			}
	//for how method submit multiMethod_input.jhclit
		    else if( action.equals("howmthd_input.baljhclit"))
			{	
			    UIUtils.displayRequestParamsValue(request);
			    if(httpSession != null)
			    {
			    	String jhclitkeyID = request.getParameter("jhclitkeyID");
			    	 
			    	BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst = new BAL_PlmTlMultiplemethodsmst();
			    	
			    	String mlmmKeyid =  request.getParameter("txtMlmmKeyid");
			    	
			    	List<BAL_PlmTlMultiplemethodsmst> plmTlMultiplemethodsmstList = (List<BAL_PlmTlMultiplemethodsmst>)httpSession.getAttribute("plmTlMultiplemethodsmstList"+jhclitkeyID);
			    	
			    	
			    	if( plmTlMultiplemethodsmstList == null)
			    	{
			    		plmTlMultiplemethodsmstList = new ArrayList<BAL_PlmTlMultiplemethodsmst>();
			    	}
			    	
			    	//CommonFunctions.debugMsg("mlmmKeyid " + mlmmKeyid);
			    	if( mlmmKeyid == null || mlmmKeyid.trim().length() <= 0 || mlmmKeyid.trim().equals("null")){
			    		UIUtils.setBeanProperties(plmTlMultiplemethodsmst, request);
			    		plmTlMultiplemethodsmst.setMlmmKeyid(""+ (plmTlMultiplemethodsmstList.size()+1));
			    	}	
			    	else{
			    		plmTlMultiplemethodsmst = getPlmTlMultiplemethodsmst(plmTlMultiplemethodsmstList,mlmmKeyid);
			    		UIUtils.setBeanProperties(plmTlMultiplemethodsmst, request);
			    	}	
					    	
			    	plmTlMultiplemethodsmstList.add(plmTlMultiplemethodsmst);
			    	
			    	httpSession.setAttribute("plmTlMultiplemethodsmstList"+jhclitkeyID,plmTlMultiplemethodsmstList);
			    	//CommonFunctions.debugMsg("plmTlMultiplemethodsmst keyid " + plmTlMultiplemethodsmst.getMlmmKeyid());
			    }	
			    
			  
		    }
		   
//for delete how method grid row howmethod_deleteData.jhclit
		    else if( action.equals("howmethod_deleteData .baljhclit"))
			{	
		    	PrintWriter out=response.getWriter();
		    	String howmethodsata = (String)httpSession.getAttribute("multimethod");
			    //CommonFunctions.debugMsg("multimethod "+request.getParameter("hmID"));
				JSONObject howmethdData = new JSONObject();// UIUtils.fromTpmModel(oplTlCategorymst);
				howmethdData.put("multimethod", howmethodsata);
				//CommonFunctions.debugMsg("multimethod="+howmethdData);
	    		out.println(howmethdData);
			  
		    }
//grid_howmethod.jhclit
		  
		    else if( action.equals("howmthd_getCol.baljhclit") )
			{
		    	//getmethodlist
		    	//CommonFunctions.debugMsg("multimethod "+request.getParameter("hmID"));
			    //CommonFunctions.debugMsg("CLIT KEY ID "+request.getParameter("jhclitkeyID"));
			    httpSession.setAttribute("JhclitServletId",request.getParameter("jhclitkeyID"));
			    String jhclitkeyID = request.getParameter("jhclitkeyID");
			    
				List<Object> plmTlMultiplemethodsmstList = null;
				plmTlMultiplemethodsmstList =(List<Object>)  httpSession.getAttribute("plmTlMultiplemethodsmstList"+jhclitkeyID);
				
				if((jhclitkeyID != null &&  plmTlMultiplemethodsmstList == null ) )
				{	
					try {
						plmTlMultiplemethodsmstList = cliTlStandardsService.getmethodlist(jhclitkeyID);
						
						//CommonFunctions.debugMsg("plmTlMultiplemethodsmstList"+plmTlMultiplemethodsmstList.size());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				httpSession.setAttribute("plmTlMultiplemethodsmstList"+jhclitkeyID, plmTlMultiplemethodsmstList);
			 
		    	PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelhowMethod"));
			}
		    else if( action.equals("howmthd_getData.baljhclit"))
			{
		    	try
				{	
		    		String jhclitkeyID = request.getParameter("jhclitkeyID");
		    		//CommonFunctions.debugMsg("1ss");
					List<Object> plmTlMultiplemethodsmstList;
					plmTlMultiplemethodsmstList =(List<Object>) httpSession.getAttribute("plmTlMultiplemethodsmstList"+jhclitkeyID); 
					//CommonFunctions.debugMsg("2df");
					PrintWriter out = response.getWriter();
					
					httpSession.setAttribute("JHClitServlet"+jhclitkeyID, plmTlMultiplemethodsmstList);
					//CommonFunctions.debugMsg("3df");
				    JSONObject methodmultiData = convertToMultiMethodTblObject(plmTlMultiplemethodsmstList);
				    //CommonFunctions.debugMsg("methodmultiData getData="+methodmultiData);
				    out.println(methodmultiData);
				}
				catch(Exception e)
				{
					//CommonFunctions.debugMsg("Multi Method  Exception"+e.getMessage());
				}
				
			}
			else if( action.equals("jhClit_getCol.baljhclit") )
			{	
				CommonFunctions.debugMsg("inside getcol");
				
				PrintWriter out = response.getWriter();
				//String mchId =request.getParameter("cmbMchid");
				String flid =request.getParameter("flid");
				String mchId =request.getParameter("machineID");
				String grid = request.getParameter("grid");
				CommonFunctions.debugMsg("flid : "+flid);
				CommonFunctions.debugMsg("grid : "+grid);
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelcount"));
//				if(UIUtils.isValidKeyId(flid)){
				        String assmId = request.getParameter("cmbAssmbid");
				       
						CommonFunctions.debugMsg("grid : "+grid);
						
						if(UIUtils.isValidKeyId(grid) && "clit".equals(grid))
						{
						 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModeljhstandard"));
						 populateCommonFilter(request,"clitStandardCommonFilter",true);
						}
						else
						{
//						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelmcharea"));
							out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelcount"));
						//CommonFunctions.debugMsg("mchId........... : "+mchId);
						//CommonFunctions.debugMsg("UIUTILS DATA DEO");
						}
						
				}
//				else				
//					{
//					
//					//CommonFunctions.debugMsg("data  :"+(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelcount")));	
//					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Jhgetclitstandards", "colModelcount"));
//			
//					}
//			
//			}
		else if( action.equals("jhClit_getData.baljhclit") )
		{
			try
			{	
				//CommonFunctions.debugMsg("Inside JHCLIT Data action");
				PrintWriter out = response.getWriter();
				String mchId =request.getParameter("cmbMchid");
				String asmId =request.getParameter("cmbAssmbid");
				CommonFunctions.debugMsg("manjjjj   "+mchId+"-----"+asmId);
				String flid = request.getParameter("flid");
				String mchID =  request.getParameter("mchID");
				httpSession = request.getSession(false);
				//String machineIDview = (String) httpSession.getAttribute("machineIDview");
				String machineIDview =request.getParameter("machineID");//getting value from view clicked
				String grid = request.getParameter("grid");
				CommonFunctions.debugMsg("machineIDview--!!__"+machineIDview+"!!!--"+flid);
				//CommonFunctions.debugMsg("servlet machine idm    :"+mchId);
				if(UIUtils.isValidKeyId(flid))
				{ 
					//CommonFunctions.debugMsg("Inside if");
					
					if(UIUtils.isValidKeyId(grid) && "clit".equals(grid))
					{
						
						//code for third grid
					//	CommonFilter commonFilter = new CommonFilter(); 
						String jhasmId = (String) httpSession.getAttribute("jhclitassemblyID");
				        //CommonFunctions.debugMsg("asmvnid----------------------------"+asmId);
						//CommonFunctions.debugMsg("asmIdasmId : "+jhasmId);
						List<String []> jhclitstandards  = cliTlStandardsService.getAlljhclitstandards(flid,mchID);
						//CommonFunctions.debugMsg(jhclitstandards.size());
		 			 	JSONObject jhclitstandardsdata = UIUtils.convertToJqGridTableObject(jhclitstandards,request,1,2);
		 			 	out.println(jhclitstandardsdata);
		 			// 	commonFilter.getAssembly().setId(jhasmId);
					//	commonFilter.getMachine().setId(mchId);
					//	commonFilter.setIsForRowTotalRow(null);
						//CommonFunctions.debugMsg(jhclitstandards.size());
						CommonFilter commonFilter  = populateCommonFilter(request,"clitStandardCommonFilter",false);
						httpSession.removeAttribute("clitStandardCommonFilter");
		  			 	httpSession.setAttribute("clitStandardCommonFilter", commonFilter);
		 			 	//CommonFunctions.debugMsg(jhclitstandardsdata);
					}
					else{
						    //code for second grid
						    CommonFunctions.debugMsg("servlet machine idm    :"+mchId);
							//List<String []> jhclitmachinearea  = cliTlStandardsService.getAlljhclitmachinearea(flid);
							
						    List<String > paramValues = new ArrayList<String>();
//							paramValues.add(flid);
						    paramValues.add( machineIDview);
							paramValues.add("0");
			        		paramValues.add("0");
							paramValues.add("100");
							List<String []> jhclitmachinearea  = cliTlStandardsService.getAlljhclitcountmodifyList(paramValues);
							
							
							//CommonFunctions.debugMsg("size"+jhclitmachinearea.toString());
			 			 	JSONObject jhclitmchineareadata = UIUtils.convertToJqGridTableObject(jhclitmachinearea,request,0,0);
			 			 	out.println(jhclitmchineareadata);
			 			 	//CommonFunctions.debugMsg("jhclitmchineareadata  :"+jhclitmchineareadata);
					     }
				
					}
			
 			   else{
//code for first grid 
//bean for identifying the action which is passed 
 				   actionpassed= (BAL_CliTlStandardFormBean) httpSession.getAttribute("JHCLITServlet_cliTlStandardFormBean");
 				   actionpassed.getActionmode();
 				  	//CommonFunctions.debugMsg("+++++++++++"+ actionpassed.getActionmode());
//for pagination
 				  		  String rows = request.getParameter("rows");
 				  		  String page = request.getParameter("page");	        	  
 				  		  String count = null;
 				  		  //CommonFunctions.debugMsg( "rows : "+rows+" page : "+page+"count  :"+count);
	 					  int rowStart = 1;
	 		       		  int rowEnd = 100;
	 		       		  String start = Integer.toString(rowStart);
	 		       		  String end = Integer.toString(rowEnd);
	 		       		
	 					//end
					if("modify" .equals(actionpassed.getActionmode())   || "view".equals(actionpassed.getActionmode()) ){
							//CommonFunctions.debugMsg("modification");
							String flagm = "0";
							String flag ="0";//for count 
							
//check machineIDview for checking view button clicked if clicked Id will be passed if not {} will be passed
							if(UIUtils.isValidKeyId(flid)){
							//CommonFunctions.debugMsg("machineID VIEW THAT IS CALLED  :_"+flid);
							List<String []> jhclitcount = cliTlStandardsService.getAllcountmodifyList(flag,flid);
							String[] countas = jhclitcount.get(0);
							count =countas[0];
							//CommonFunctions.debugMsg("converted array object as string is  :"+count);
//parameters to be passed to DAOImpl
							List<String > paramValues = new ArrayList<String>();
							paramValues.add(flid);
							paramValues.add(flagm);
			        		paramValues.add(start);
							paramValues.add(end);
							List<String []> jhclitcountmodifyList  = cliTlStandardsService.getAlljhclitcountmodifyList(paramValues);
							//CommonFunctions.debugMsg("COUNT  :"+jhclitcountmodifyList.size());
							//JSONObject jhclitcountmodifyData = UIUtils.convertToJqGridTableObject(jhclitcountmodifyList,request,0,1);
							JSONObject jhclitcountmodifyData = UIUtils.convertListToJqGridTableObject(jhclitcountmodifyList,request,0,1,Integer.parseInt(count));
							out.println(jhclitcountmodifyData);
							/*httpSession.removeAttribute("machineIDview");
							httpSession.setAttribute("machineIDview","{}");*/
							}
							else{
								CommonFunctions.debugMsg("machineIDview--else__!!!--"+machineIDview);
								flid = "{}";
									//CommonFunctions.debugMsg("machineIDview``~~~~modify~~()~"+machineIDview);
									List<String []> jhclitcount = cliTlStandardsService.getAllcountmodifyList(flag,flid);
									String[] countas = jhclitcount.get(0);
									count = countas[0];
									//CommonFunctions.debugMsg("converted array object as string is  :"+count);
/*added for lazy loading continue!!*/
									List<String > paramValues = new ArrayList<String>();
									  if(page.equals("1")){
										//parameters to be passed to DAOImpl
										paramValues.add(flid);
										paramValues.add(flagm);
							        	paramValues.add(start);
										paramValues.add(end);
										//CommonFunctions.debugMsg("modification----paramValues :"+paramValues);
									  }
									  else{
									   rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
					        		   rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
					        		   start = Integer.toString(rowStart);
					        		   end = Integer.toString(rowEnd);
					        		   paramValues.add(flid);
									   paramValues.add(flagm);
							           paramValues.add(start);
									   paramValues.add(end);
									   //CommonFunctions.debugMsg("modification----Else--paramValues :"+paramValues);
									  }
									List<String []> jhclitcountmodifyList  = cliTlStandardsService.getAlljhclitcountmodifyList(paramValues);
									//CommonFunctions.debugMsg("Size : "+jhclitcountmodifyList.size());
									//JSONObject jhclitcountmodifyData = UIUtils.convertToJqGridTableObject(jhclitcountmodifyList,request,0,1);
									JSONObject jhclitcountmodifyData = UIUtils.convertListToJqGridTableObject(jhclitcountmodifyList,request,0,1,Integer.parseInt(count));
									out.println(jhclitcountmodifyData);
									//CommonFunctions.debugMsg(jhclitcountmodifyData);
							 }
							
					}else{
						CommonFunctions.debugMsg("Creation........123...........345........");
						String flag = "-1";
						
//check machineIDview for checking view button clicked if clicked Id will be passed if not {} will be passed
						if(UIUtils.isValidKeyId(flid)){
							//CommonFunctions.debugMsg("machineID creation THAT IS CALLED  :_"+machineIDview);
							/*List<String []> jhclitcount = cliTlStandardsService.getAllcountmodifyList(flag,flid);
							httpSession.removeAttribute("machineIDview");//to clear session
							String[] countas = jhclitcount.get(0);
							count = countas[0];*/
							//CommonFunctions.debugMsg("converted array object as string is  :"+count);
							List<String > paramValues = new ArrayList<String>();
//parameters to be passed to DAOImpl
								paramValues.add(flid);
								paramValues.add(flag);
					        	paramValues.add(start);
								paramValues.add(end);
								//CommonFunctions.debugMsg("paramValues :"+paramValues);
							
							List<String []> jhclitcountList  = cliTlStandardsService.getAlljhclitcountList(paramValues);
							//CommonFunctions.debugMsg(jhclitcountList.size());
							JSONObject jhclitcountData = UIUtils.convertListToJqGridTableObject(jhclitcountList,request,0,1,jhclitcountList.size());
							out.println(jhclitcountData);
						}
						/*else{
							flid = "{}";
							 //CommonFunctions.debugMsg("machineIDview``~~~~creation~~()~"+machineIDview);
							List<String []> jhclitcount = cliTlStandardsService.getAllcountmodifyList(flag,flid);
								String[] countas = jhclitcount.get(0);
								count = countas[0];
								//CommonFunctions.debugMsg("converted array object as string is  :"+count);

							 List<String > paramValues = new ArrayList<String>();
							  if(page.equals("1")){
//parameters to be passed to DAOImpl
								paramValues.add(flid);
								paramValues.add(flag);
					        	paramValues.add(start);
								paramValues.add(end);
								//CommonFunctions.debugMsg("paramValues :"+paramValues);
							  }
							  else{
							   rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
			        		   rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
			        		   start = Integer.toString(rowStart);
			        		   end = Integer.toString(rowEnd);
			        		   paramValues.add(flid);
							   paramValues.add(flag);
					           paramValues.add(start);
							   paramValues.add(end);
							   //CommonFunctions.debugMsg("Else--paramValues :"+paramValues);
							  }
							List<String []> jhclitcountList  = cliTlStandardsService.getAlljhclitcountList(paramValues);
							//CommonFunctions.debugMsg(jhclitcountList.size());
							//JSONObject jhclitcountData = UIUtils.convertToJqGridTableObject(jhclitcountList,request,0,0);
							JSONObject jhclitcountData = UIUtils.convertListToJqGridTableObject(jhclitcountList,request,0,1,Integer.parseInt(count));
							out.println(jhclitcountData);
							
							
					}*/
				 }
			  }
 			 	
			}catch(Exception e)
			{
				//CommonFunctions.debugMsg(e.getMessage());
			}
		}
			
	}
    //for export to excel in third grid
    private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getEquipmentRelated(request, commonFilter);
		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		//CommonFunctions.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
 //tool tree get value fill grid
    private JSONObject convertToToolTreeTblObject(List<Object> genVwToolcategorytList)
  	{
   		//CommonFunctions.debugMsg("inside the convert tool function");
  		JSONObject toolTreeDataObject = new JSONObject();
 		
  		toolTreeDataObject.put("page", 1); //current page
  		toolTreeDataObject.put("total",10); // total page
  		toolTreeDataObject.put("records", (genVwToolcategorytList.size())); //total records
		
		JSONArray rowArr = new JSONArray(); 
		int  rowid =0;
  		for(Object tpmModel: genVwToolcategorytList)
  		{
  			
  			BAL_GenVwToolcategory genVwToolcategory = (BAL_GenVwToolcategory)tpmModel;
	  		JSONObject rowObj =new JSONObject();
		    rowObj.put("id",++rowid);
	        
	        JSONArray cell = new  JSONArray();
	        cell.put(genVwToolcategory.getElementid());
	        cell.put(genVwToolcategory.getDisplaycode());
	       
	        rowObj.put("cell",cell);
	        
	        rowArr.put(rowObj);
		}
  		
  		toolTreeDataObject.put("rows", rowArr);
	      
  			//CommonFunctions.debugMsg("TOOLDataObject="+toolTreeDataObject);
	        return toolTreeDataObject;
   	}
//how method convert to object
    private JSONObject convertToMultiMethodTblObject(List<Object> plmTlMultiplemethodsmstList)
  	{
   		
  		JSONObject multiplemethodDataObject = new JSONObject();
 		
  		multiplemethodDataObject.put("page", 1); //current page
  		multiplemethodDataObject.put("total",10); // total page
  		multiplemethodDataObject.put("records", (plmTlMultiplemethodsmstList.size())); //total records
		
		JSONArray rowArr = new JSONArray(); 
		int  rowid =0;
  		for(Object tpmModel: plmTlMultiplemethodsmstList)
  		{
  			
  			BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst = (BAL_PlmTlMultiplemethodsmst)tpmModel;
	  		JSONObject rowObj =new JSONObject();
		    rowObj.put("id",++rowid);
	        
	        JSONArray cell = new  JSONArray();
	        cell.put(plmTlMultiplemethodsmst.getMlmmKeyid());
	        cell.put(plmTlMultiplemethodsmst.getMlmmMethoddescription());
	        cell.put(plmTlMultiplemethodsmst.getMlmmDuration());
	        rowObj.put("cell",cell);
	        
	        rowArr.put(rowObj);
		}
  		
  		multiplemethodDataObject.put("rows", rowArr);
	      
  			//CommonFunctions.debugMsg("multiplemethodDataObject="+multiplemethodDataObject);
	        return multiplemethodDataObject;
   	}

//end
	//for filling form value 
       /*  private void formopenClit(HttpServletRequest request, HttpServletResponse response,CliTlStandardFormBean cliTlStandardFormBean  ) throws IOException{
        	HttpSession httpSession = request.getSession(false);
		    ServletOutputStream out = response.getOutputStream();
		    String jhclitId = request.getParameter("stdId");
			httpSession.setAttribute("jhclitId", jhclitId);
			
			
		    if( httpSession != null && jhclitId != null)
	    	{
		    	
	    	}
         }*/
         //end of filling form values
		 private void saveClit(HttpServletRequest request, HttpServletResponse response,BAL_CliTlStandardFormBean cliTlStandardFormBean  ) throws IOException{
				//assmid
		    	//CommonFunctions.debugMsg("inside saveClit");
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	try{
			    	if( httpSession != null && user != null)
			    	{	
			    		
			    		//String AssemblyId= (String) httpSession.getAttribute("jhclitassemblyID");
			    		
			    		BAL_CliTlStandards existCliTlStandards = (BAL_CliTlStandards)httpSession.getAttribute("cliTlStandards"); 
			    		BAL_CliTlStandards newCliTlStandards = new BAL_CliTlStandards();
			    		BAL_PlmTlMultiplemethodsmst newPlmTlMultiplemethodsmst = new BAL_PlmTlMultiplemethodsmst();
			    		BAL_PlmTlToolsdtl newPlmTlToolsdtl = new BAL_PlmTlToolsdtl();
			    		
						newCliTlStandards.setClisCreatedby(user.getUsrm_ccno());
						String docId = (String)httpSession.getAttribute("dockKey");
						CommonFunctions.debugMsg("kajsdk   "+docId);
						String yyId = (String)httpSession.getAttribute("yyId");
						//newCliTlStandards.setClisAssemblyid(AssemblyId);//machine area id
						if(UIUtils.isValidKeyId(docId))
							cliTlStandardFormBean.setBdmDockKey(docId);
						
						
						if(UIUtils.isValidKeyId(yyId)){
							newCliTlStandards.setClisRefdocno(yyId);
							newCliTlStandards.setClisRefdoctype("YY");
							BAL_BdmTlYycountermeasurelink newbdmTlYycountermeasurelink = new BAL_BdmTlYycountermeasurelink();
							newbdmTlYycountermeasurelink.setYycmYyid(yyId);
							newbdmTlYycountermeasurelink.setYycmRefdoctype("JHN");
							CommonFunctions.debugMsg("countrerMeasyere    "+newbdmTlYycountermeasurelink.getYycmYyid());
							newCliTlStandards.setCountermeasureLink(newbdmTlYycountermeasurelink);
						}
						
						
						newCliTlStandards =(BAL_CliTlStandards)UIUtils.setBeanProperties((Object)newCliTlStandards,request);
						
						/*For updating the Docid sent from BDForm in docupdates*/
						
						CommonFunctions.debugMsg("bean doc "+cliTlStandardFormBean.getBdmDockKey());
						
						//CommonFunctions.debugMsg("machine BEAN :"+newCliTlStandards.getClisAssemblyid());
						//if((newCliTlStandards.getClisKeyid())!= null && "input".equals(actionpassed.getActionmode()))
					/*	if(UIUtils.isValidKeyId(newCliTlStandards.getClisKeyid()))
						{
							CommonFunctions.debugMsg("keyid there  :"+newCliTlStandards.getClisKeyid());
							JSONObject err = new JSONObject();
							err.put("tpmException","Cannot save in this Mode");
							out.print(err.toString());
						}
						if{*/
							CommonFunctions.debugMsg("keyid not there  :"+newCliTlStandards.getClisKeyid());	
						
						newPlmTlMultiplemethodsmst =(BAL_PlmTlMultiplemethodsmst)UIUtils.setBeanProperties((Object)newPlmTlMultiplemethodsmst, request);
						newPlmTlToolsdtl =(BAL_PlmTlToolsdtl)UIUtils.setBeanProperties((Object)newPlmTlToolsdtl, request);
						
						String KeyID = (String) httpSession.getAttribute("jhclitId");
						//CommonFunctions.debugMsg("KEY ID   :-"+KeyID);
						newCliTlStandards.setClisKeyid(KeyID);//KeyId
						
						String mulitMethodStr =  request.getParameter("multiplemethods");
						String toolsGridStr   =  request.getParameter("toolsGrid");
						
						//CommonFunctions.debugMsg(" mulitMethodStr " + mulitMethodStr);
						//CommonFunctions.debugMsg(" toolsGridStr " + toolsGridStr);
						/***Save tool grid ****/
						List<BAL_PlmTlToolsdtl> toolGridList = null;
						JSONArray toolGridjson = null;
						if(UIUtils.isValidKeyId(toolsGridStr)){
							toolGridjson = JSONArray.fromString(toolsGridStr);
							toolGridList=(List<BAL_PlmTlToolsdtl>)UIUtils.convertJSONArrToList(newPlmTlToolsdtl, toolGridjson);
						
							if(toolGridList!= null)
								newCliTlStandards.setToolsDetail(toolGridList);
						}
					//	existCliTlStandards = (CliTlStandards)httpSession.getAttribute("cliTlStandards"+newCliTlStandards.getClisKeyid());
						/***end of save tool**/
							/*JSONArray counterMeasurejson = null;
							String counterMeasureStr = null;
							if(UIUtils.isValidKeyId(counterMeasureStr)){
								//CommonFunctions.debugMsg(" mulitMethodStr " + mulitMethodStr);
								CommonFunctions.debugMsg(" countermeasure" + counterMeasureStr);
								counterMeasurejson = JSONArray.fromString(counterMeasureStr);
								counterMeasureList=(List<BdmTlYycountermeasurelink>)UIUtils.convertJSONArrToList(newbdmTlYycountermeasurelink, counterMeasurejson);
							if(counterMeasureList!= null){
								newPlmTlStandards.setCountermeasureLink(counterMeasureList);
								//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
								}*/
							
						/***end of save countermeasure**/
						/***Save multiMethod ****/
						List<BAL_PlmTlMultiplemethodsmst> mulitMethodList = null;
						JSONArray multiMethodJson = null;
						if(UIUtils.isValidKeyId(mulitMethodStr)){
							multiMethodJson = JSONArray.fromString(mulitMethodStr);
							mulitMethodList = (List<BAL_PlmTlMultiplemethodsmst>)UIUtils.convertJSONArrToList(newPlmTlMultiplemethodsmst,multiMethodJson);
						
							if(mulitMethodList!= null)
								newCliTlStandards.setMethodDetail(mulitMethodList);
						}
						//existCliTlStandards = (CliTlStandards)httpSession.getAttribute("cliTlStandards"+newCliTlStandards.getClisKeyid());
						/***end of save MultiMethod**/
						
				
						cliTlStandardFormBean =(BAL_CliTlStandardFormBean) UIUtils.setBeanProperties((Object)cliTlStandardFormBean,request);
						
						//CommonFunctions.debugMsg("MACHINE ID :"+newCliTlStandards.getClisMachineid());
						//CommonFunctions.debugMsg("classification ID :"+newCliTlStandards.getClisActivitytype());
						//CommonFunctions.debugMsg("freq ID :"+newCliTlStandards.getClisFrequencyunit());
						//CommonFunctions.debugMsg("freq ID :"+newCliTlStandards.getClisFrequency());
						//CommonFunctions.debugMsg("how :"+newCliTlStandards.getClisHowmuchduration());
						//CommonFunctions.debugMsg("how :"+newCliTlStandards.getClisWherelocation());
						//CommonFunctions.debugMsg("tool Pickup :"+newCliTlStandards.getClisIstoolsreq());
						//CommonFunctions.debugMsg("machine Area :"+newCliTlStandards.getClisAssemblyid());
						//CommonFunctions.debugMsg("machine Area :"+newCliTlStandards.getClisDepartmentmgr());
						//CommonFunctions.debugMsg("machine Area :"+newCliTlStandards.getClisSectionmgr());
						//CommonFunctions.debugMsg("machine Area :"+newCliTlStandards.getClisGroupleader());
							String saveMsg ;
							//CommonFunctions.debugMsg("  newCliTlStandards.getClisKeyid() " +  newCliTlStandards.getClisKeyid());
							if( newCliTlStandards.getClisKeyid() == null )
							{	
								//CommonFunctions.debugMsg("key id is not available");
								existCliTlStandards =	cliTlStandardsService.create(newCliTlStandards,existCliTlStandards,cliTlStandardFormBean);
								saveMsg = "Data Saved Successfully";
							}	
							else{
								CommonFunctions.debugMsg("key id is available" + newCliTlStandards.getClisKeyid() );
								cliTlStandardFormBean.setFormMode("UPDATE");
								existCliTlStandards = cliTlStandardsService.update(newCliTlStandards,existCliTlStandards,cliTlStandardFormBean);
								saveMsg = "Data Updated Successfully";
							}
							
							httpSession.setAttribute(existCliTlStandards.getClisKeyid(), existCliTlStandards);
							httpSession.setAttribute("CliTlStandards", existCliTlStandards);
							String formBeanIdentifier = "cliTlStandardFormBean"+cliTlStandardFormBean.getFormActionMode();
							httpSession.setAttribute(formBeanIdentifier,cliTlStandardFormBean);
							
							JSONObject returnData = new JSONObject();
							
							JSONObject successData = new JSONObject();
							String openfilemgr = request.getParameter("openfilemgr");
							String modeReturn =(String)httpSession.getAttribute("bdmmode");
							successData.put("successData", modeReturn);
							
							successData.put("clisKeyid",existCliTlStandards.getClisKeyid());
							if(UIUtils.isValidKeyId(modeReturn )){
								CommonFunctions.debugMsg("modeReturn  :"+modeReturn);
								returnData.put("formClear",false);	
								returnData.put("successData",successData);
								httpSession.removeAttribute("bdmmode");
							}
							if(UIUtils.isValidKeyId(openfilemgr))
							{
								returnData.put("formClear",false);	
								successData.put("openfilemgr", true);
							}
							successData.put("msg", saveMsg);
							returnData.put("formClear",true);	
							returnData.put("successData",successData);
							out.print(returnData.toString());
							//out.print()	
							//CommonFunctions.debugMsg("end of save");
			    	 }	
			    	//}  	
			}catch(ValidationExceptions e)
			{
				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "ClitcreationException");
				errMessage.put("fromMode",cliTlStandardFormBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    
	}


//		 private void saveMultipleClit(HttpServletRequest request, HttpServletResponse response) throws IOException {
//			    HttpSession httpSession = request.getSession(false);
//			    ServletOutputStream out = response.getOutputStream();
//			    AdmTlUsermst user = UIUtils.getLoginUser(request);
//
//			    try {
//			        String clitStdDetailsJson = request.getParameter("clitStdDetails");
//			        String flId       = request.getParameter("flId");
//			        String sectionId  = request.getParameter("sectionId");
//			        String machId     = request.getParameter("machId");
//			        String openFileMgr = request.getParameter("openfilemgr");
//			        String elementId = request.getParameter("elementId");
//			        if (elementId == null || elementId.trim().isEmpty())
//			            elementId = request.getParameter("cmbClisElementid");
//			        if (elementId == null || elementId.trim().isEmpty())
//			            elementId = (String) httpSession.getAttribute("clisElementid");
//
//			        String docId   = (String) httpSession.getAttribute("dockKey");
//			        String yyId    = (String) httpSession.getAttribute("yyId");
//			        String factory = request.getParameter("cmbClisFactoryid");
//			        String cellId  = request.getParameter("cmbClisCellid");
//			        System.out.println("cellId = " + cellId);
//
//			        // NEW: image upload folder path for this request
//			        String imagePath = UIUtils.getImagePath(request);
//			        CommonFunctions.debugMsg("imagePath (CLTI):::::=" + imagePath);
//
//			        JSONArray rowsArray = JSONArray.fromString(clitStdDetailsJson);
//
//			        String lastSavedKeyId = null;
//
//			        if (elementId == null || elementId.trim().isEmpty()) {
//			            JSONObject err = new JSONObject();
//			            err.put("tpmException", "Element ID not found. Please reopen the form.");
//			            out.print(err.toString());
//			            return;
//			        }
//			        
//			       
//
//			        for (int i = 0; i < rowsArray.length(); i++) {
//			            JSONObject row = rowsArray.getJSONObject(i);
//			            CommonFunctions.debugMsg("ROW " + i + " FULL JSON = " + row.toString());
//
//			            BAL_CliTlStandards bean = new BAL_CliTlStandards();
//
//			            // Header/location fields
//			            bean.setClisFactoryid(factory);
//			            bean.setClisSectionid(sectionId);
//			            bean.setClisCellid(cellId);
//			            bean.setClisMachineid(machId);
//			            bean.setClisFlid(flId);
//			            bean.setClisCreatedby(user.getUsrm_ccno());
//			            bean.setClisActive("Y");
//			            bean.setClisWogenflag("N");
//			            bean.setClisElementid(elementId);
//
//			            // Trade
//			            String tradeId = nullSafe(row, "hdnMulClisTradeid");
//			            if (tradeId.isEmpty()) tradeId = nullSafe(row, "cmbMulClisTradeid");
//			            bean.setClisTradeid(tradeId);
//
//			            // Activity type
//			            String actType = nullSafe(row, "hdnMulClisActivitytype");
//			            if (actType.isEmpty()) actType = nullSafe(row, "cmbMulClisActivitytype");
//			            bean.setClisActivitytype(actType);
//
//			            // Frequency unit - on update, combo value takes priority
//			            String freqUnit = nullSafe(row, "cmbMulClisFrequencyunit");
//			            if (freqUnit.isEmpty()) freqUnit = nullSafe(row, "hdnMulClisFrequencyunit");
//			            bean.setClisFrequencyunit(freqUnit);
//
//			            // Shift
//			            String shiftId = nullSafe(row, "hdnMulClisShiftid");
//			            if (shiftId.isEmpty()) shiftId = nullSafe(row, "cmbMulClisShiftid");
//			            bean.setClisShiftid(shiftId);
//
//			            // Designation
//			            String desgId = nullSafe(row, "hdnMulClisDesigid");
//			            if (desgId.isEmpty()) desgId = nullSafe(row, "cmbMulClisResponsibilitydesgid");
//			            bean.setClisResponsibilitydesgid(desgId);
//
//			            // Responsibility
//			            String respId = nullSafe(row, "hdnMulClisRespid");
//			            if (respId.isEmpty()) respId = nullSafe(row, "cmbMulClisResponsibilityid");
//			            bean.setClisResponsibilityid(respId);
//
//			            // Prepared by
//			            String prepId = nullSafe(row, "hdnMulClisPreparid");
//			            if (prepId.isEmpty()) prepId = nullSafe(row, "cmbMulClisPreparedbyid");
//			            bean.setClisPreparedbyid(prepId);
//
//			            // Plain text fields
//			            bean.setClisStandard(        nullSafe(row, "txtMulClisStandard"));
//			            bean.setClisWhatactivity(    nullSafe(row, "txtMulClisWhatactivity"));
//			            bean.setClisHowmethod(       nullSafe(row, "txtMulClisHowmethod"));
//			            bean.setClisWherelocation(   nullSafe(row, "txtMulClisWherelocation"));
//			            bean.setClisWhyifnotdone(    nullSafe(row, "txtMulClisWhyifnotdone"));
//			            bean.setClisCorrectiveaction(nullSafe(row, "txtMulClisCorrectiveaction"));
//
//			            // Tools required
//			            String toolsReq = nullSafe(row, "chkMulClisIstoolsreq");
//			            bean.setClisIstoolsreq(
//			                "on".equals(toolsReq) || "Y".equals(toolsReq) ? "Y" : "N");
//
//			            // Time (minutes)
//			            String timeStr = nullSafe(row, "txtMulClisTime");
//			            String durationVal = "0";
//			            try {
//			                durationVal = timeStr.isEmpty() ? "0"
//			                        : String.valueOf(Integer.parseInt(timeStr.trim()));
//			            } catch (NumberFormatException e) {
//			                durationVal = "0";
//			            }
//			            bean.setClisHowmuchduration(durationVal);
//
//			            // Next Due Date
//			            String nextDue = nullSafe(row, "dteClisNextduedate");
//			            if (!nextDue.isEmpty()) {
//			                bean.setClisNextduedate(nextDue);
//			            }
//
//			            // YY countermeasure link
//			            if (UIUtils.isValidKeyId(yyId)) {
//			                bean.setClisRefdocno(yyId);
//			                bean.setClisRefdoctype("YY");
//			                BAL_BdmTlYycountermeasurelink cm = new BAL_BdmTlYycountermeasurelink();
//			                cm.setYycmYyid(yyId);
//			                cm.setYycmRefdoctype("JHN");
//			                bean.setCountermeasureLink(cm);
//			            }
//
//			            BAL_CliTlStandardFormBean formBean = new BAL_CliTlStandardFormBean();
//			            if (UIUtils.isValidKeyId(docId))
//			                formBean.setBdmDockKey(docId);
//
//			            // NEW: block-diagram image temp filename for this row (set by JS when row was checked + image uploaded)
//			            String rowImgFileName = nullSafe(row, "hdnClisBlockDiagImg").trim(); //hdnClisBlockDiagImg
//			            System.out.println("RowImage=============="+rowImgFileName);
//			            String existingKeyId = nullSafe(row, "hdnClisKeyid").trim();
//                        System.out.println("keyid=============="+existingKeyId);
//			            if (existingKeyId.isEmpty()) {	
//			                BAL_CliTlStandards saved = cliTlStandardsService.create(bean, null, formBean);
//			                lastSavedKeyId = saved.getClisKeyid();
//
//			                // NEW: save block-diagram image against this row's newly generated key
//			                if (UIUtils.isValidKeyId(rowImgFileName)) {
//			                    List<GenTlAllmoduleimgfile> imgfileList = new ArrayList<GenTlAllmoduleimgfile>();
//			                    GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
//			                    genTlAllmoduleimgfile.setImflBlobimage(imagePath);			     
//			                    genTlAllmoduleimgfile.setImflFilename(rowImgFileName);
//			                    genTlAllmoduleimgfile.setImflImagetype("BLK");
//			                    genTlAllmoduleimgfile.setImflRefdoctype("CLI");
//			                    imgfileList.add(genTlAllmoduleimgfile);
//
//			                    commonFilterService.saveImg(imgfileList, saved.getClisKeyid(), genTlAllmoduleimgfile.getImflRefdoctype());
//			                }
//
//			            } else {
//			                bean.setClisKeyid(existingKeyId);
//			                BAL_CliTlStandards oldBean = (BAL_CliTlStandards) httpSession.getAttribute(existingKeyId);
//			                if (oldBean == null) oldBean = new BAL_CliTlStandards();
//
//			                // Set createdon directly on bean to prevent <* *> placeholder
//			                String createdOn = (String) httpSession.getAttribute("clisCreatedon_" + existingKeyId);
//			                if (createdOn != null && !createdOn.isEmpty()) {
//			                    bean.setClisCreatedon(createdOn);
//			                    oldBean.setClisCreatedon(createdOn);
//			                }
//			                // Preserve old freq/activity for calendar regeneration check
//			                String oldFreq = (String) httpSession.getAttribute("clisFreq_" + existingKeyId);
//			                String oldAct  = (String) httpSession.getAttribute("clisAct_"  + existingKeyId);
//			                if (oldFreq != null) oldBean.setClisFrequencyunit(oldFreq);
//			                if (oldAct  != null) oldBean.setClisActivitytype(oldAct);
//
//			                cliTlStandardsService.update(bean, oldBean, formBean);
//			                lastSavedKeyId = existingKeyId;
//
//			                // NEW: same delete-then-reinsert pattern as Complaint Gallery's update branch
//			                if (!UIUtils.isValidKeyId(rowImgFileName)) {
//			                	//cliTlStandardsService.deleteImage(existingKeyId, "BLK", "BLK");//BLK
//			                } else {
//			                	//cliTlStandardsService.deleteImage(existingKeyId, "BLK", "CLI");//BLKCLI
//
//			                    List<GenTlAllmoduleimgfile> imgfileList = new ArrayList<GenTlAllmoduleimgfile>();
//			                    GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
//			                    genTlAllmoduleimgfile.setImflBlobimage(imagePath);			                   
//			                    genTlAllmoduleimgfile.setImflFilename(rowImgFileName);
//			                    genTlAllmoduleimgfile.setImflImagetype("BLK");
//			                    genTlAllmoduleimgfile.setImflRefdoctype("CLI");
//			                    imgfileList.add(genTlAllmoduleimgfile);
//
//			                    commonFilterService.saveImg(imgfileList, existingKeyId, genTlAllmoduleimgfile.getImflRefdoctype());
//			                }
//			            }
//			        
//			        }
//
//			        JSONObject returnData = new JSONObject();
//			        returnData.put("clitSaveResult", lastSavedKeyId != null ? lastSavedKeyId : "");
//			        if (UIUtils.isValidKeyId(openFileMgr) && lastSavedKeyId != null)
//			            returnData.put("openfilemgr", true);
//			        out.print(returnData.toString());
//
//			    } catch (ValidationExceptions e) {
//			        JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ClitcreationException");
//			        out.print(errMessage.toString());
//			    } catch (Exception e) {
//			        CommonFunctions.debugMsg("saveMultipleClit error: " + e.getMessage());
//			        JSONObject err = new JSONObject();
//			        err.put("tpmException", "Data Not Saved: " + e.getMessage());
//			        out.print(err.toString());
//			    }
//			}
		 private void saveMultipleClit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			    HttpSession httpSession = request.getSession(false);
			    ServletOutputStream out = response.getOutputStream();
			    AdmTlUsermst user = UIUtils.getLoginUser(request);

			    try {
			        String clitStdDetailsJson = request.getParameter("clitStdDetails");
			        String flId       = request.getParameter("flId");
			        String sectionId  = request.getParameter("sectionId");
			        String machId     = request.getParameter("machId");
			        String openFileMgr = request.getParameter("openfilemgr");
			        String elementId = request.getParameter("elementId");
			        if (elementId == null || elementId.trim().isEmpty())
			            elementId = request.getParameter("cmbClisElementid");
			        if (elementId == null || elementId.trim().isEmpty())
			            elementId = (String) httpSession.getAttribute("clisElementid");

			        String docId   = (String) httpSession.getAttribute("dockKey");
			        String yyId    = (String) httpSession.getAttribute("yyId");
			        String factory = request.getParameter("cmbClisFactoryid");
			        String cellId  = request.getParameter("cmbClisCellid");
			        System.out.println("cellId = " + cellId);

			        // image upload folder path for this request
			        String imagePath = UIUtils.getImagePath(request);
			        CommonFunctions.debugMsg("imagePath (CLTI):::::=" + imagePath);

			        JSONArray rowsArray = JSONArray.fromString(clitStdDetailsJson);

			        String lastSavedKeyId = null;

			        if (elementId == null || elementId.trim().isEmpty()) {
			            JSONObject err = new JSONObject();
			            err.put("tpmException", "Element ID not found. Please reopen the form.");
			            out.print(err.toString());
			            return;
			        }

			        // ---- FIX: use a SESSION-backed byte cache, not just a request-local one.
			        // This survives even if the physical temp file on disk disappears between
			        // "image uploaded" and "user clicks Save" (e.g. server restart clearing
			        // the WTP tmp0 folder, or any cleanup job touching tmp/images).
			        @SuppressWarnings("unchecked")
			        Map<String, byte[]> imageBytesCache =
			                (Map<String, byte[]>) httpSession.getAttribute("clitImageByteCache");
			        if (imageBytesCache == null) {
			            imageBytesCache = new HashMap<String, byte[]>();
			        }

			        for (int i = 0; i < rowsArray.length(); i++) {
			            String fname = nullSafe(rowsArray.getJSONObject(i), "hdnClisBlockDiagImg").trim();
			            if (UIUtils.isValidKeyId(fname) && !imageBytesCache.containsKey(fname)) {
			                File f = new File(imagePath, fname);
			                if (f.exists()) {
			                    try {
			                        byte[] bytes = java.nio.file.Files.readAllBytes(f.toPath());
			                        imageBytesCache.put(fname, bytes);
			                    } catch (IOException ioe) {
			                        CommonFunctions.debugMsg("Could not read image " + fname + ": " + ioe.getMessage());
			                    }
			                } else {
			                    CommonFunctions.debugMsg("Image file missing on disk, skipping: " + fname);
			                }
			            }
			        }

			        // persist back to session so future save attempts (even after the disk file
			        // is gone) can still restore bytes for the same filename
			        httpSession.setAttribute("clitImageByteCache", imageBytesCache);

			        for (int i = 0; i < rowsArray.length(); i++) {
			            JSONObject row = rowsArray.getJSONObject(i);
			            CommonFunctions.debugMsg("ROW " + i + " FULL JSON = " + row.toString());

			            BAL_CliTlStandards bean = new BAL_CliTlStandards();

			            // Header/location fields
			            bean.setClisFactoryid(factory);
			            bean.setClisSectionid(sectionId);
			            bean.setClisCellid(cellId);
			            bean.setClisMachineid(machId);
			            bean.setClisFlid(flId);
			            bean.setClisCreatedby(user.getUsrm_ccno());
			            bean.setClisActive("Y");
			            bean.setClisWogenflag("N");
			            bean.setClisElementid(elementId);

			            // Trade
			            String tradeId = nullSafe(row, "hdnMulClisTradeid");
			            if (tradeId.isEmpty()) tradeId = nullSafe(row, "cmbMulClisTradeid");
			            bean.setClisTradeid(tradeId);

			            // Activity type
			            String actType = nullSafe(row, "hdnMulClisActivitytype");
			            if (actType.isEmpty()) actType = nullSafe(row, "cmbMulClisActivitytype");
			            bean.setClisActivitytype(actType);

			            // Frequency unit - on update, combo value takes priority
			            String freqUnit = nullSafe(row, "cmbMulClisFrequencyunit");
			            if (freqUnit.isEmpty()) freqUnit = nullSafe(row, "hdnMulClisFrequencyunit");
			            bean.setClisFrequencyunit(freqUnit);

			            // Shift
			            String shiftId = nullSafe(row, "hdnMulClisShiftid");
			            if (shiftId.isEmpty()) shiftId = nullSafe(row, "cmbMulClisShiftid");
			            bean.setClisShiftid(shiftId);

			            // Designation
			            String desgId = nullSafe(row, "hdnMulClisDesigid");
			            if (desgId.isEmpty()) desgId = nullSafe(row, "cmbMulClisResponsibilitydesgid");
			            bean.setClisResponsibilitydesgid(desgId);

			            // Responsibility
			            String respId = nullSafe(row, "hdnMulClisRespid");
			            if (respId.isEmpty()) respId = nullSafe(row, "cmbMulClisResponsibilityid");
			            bean.setClisResponsibilityid(respId);

			            // Prepared by
			            String prepId = nullSafe(row, "hdnMulClisPreparid");
			            if (prepId.isEmpty()) prepId = nullSafe(row, "cmbMulClisPreparedbyid");
			            bean.setClisPreparedbyid(prepId);

			            // Plain text fields
			            bean.setClisStandard(        nullSafe(row, "txtMulClisStandard"));
			            bean.setClisWhatactivity(    nullSafe(row, "txtMulClisWhatactivity"));
			            bean.setClisHowmethod(       nullSafe(row, "txtMulClisHowmethod"));
			            bean.setClisWherelocation(   nullSafe(row, "txtMulClisWherelocation"));
			            bean.setClisWhyifnotdone(    nullSafe(row, "txtMulClisWhyifnotdone"));
			            bean.setClisCorrectiveaction(nullSafe(row, "txtMulClisCorrectiveaction"));

			            // Tools required
			            String toolsReq = nullSafe(row, "chkMulClisIstoolsreq");
			            bean.setClisIstoolsreq(
			                "on".equals(toolsReq) || "Y".equals(toolsReq) ? "Y" : "N");

			            // Time (minutes)
			            String timeStr = nullSafe(row, "txtMulClisTime");
			            String durationVal = "0";
			            try {
			                durationVal = timeStr.isEmpty() ? "0"
			                        : String.valueOf(Integer.parseInt(timeStr.trim()));
			            } catch (NumberFormatException e) {
			                durationVal = "0";
			            }
			            bean.setClisHowmuchduration(durationVal);

			            // Next Due Date
			            String nextDue = nullSafe(row, "dteClisNextduedate");
			            if (!nextDue.isEmpty()) {
			                bean.setClisNextduedate(nextDue);
			            }

			            // YY countermeasure link
			            if (UIUtils.isValidKeyId(yyId)) {
			                bean.setClisRefdocno(yyId);
			                bean.setClisRefdoctype("YY");
			                BAL_BdmTlYycountermeasurelink cm = new BAL_BdmTlYycountermeasurelink();
			                cm.setYycmYyid(yyId);
			                cm.setYycmRefdoctype("JHN");
			                bean.setCountermeasureLink(cm);
			            }

			            BAL_CliTlStandardFormBean formBean = new BAL_CliTlStandardFormBean();
			            if (UIUtils.isValidKeyId(docId))
			                formBean.setBdmDockKey(docId);

			            // block-diagram image temp filename for this row
			            String rowImgFileName = nullSafe(row, "hdnClisBlockDiagImg").trim();
			            System.out.println("RowImage=============="+rowImgFileName);
			            String existingKeyId = nullSafe(row, "hdnClisKeyid").trim();
			            System.out.println("keyid=============="+existingKeyId);

			            if (existingKeyId.isEmpty()) {
			                BAL_CliTlStandards saved = cliTlStandardsService.create(bean, null, formBean);
			                lastSavedKeyId = saved.getClisKeyid();

			                // save block-diagram image against this row's newly generated key
			                if (UIUtils.isValidKeyId(rowImgFileName)) {
			                    ensureImageFileExists(imagePath, rowImgFileName, imageBytesCache); // FIX

			                    List<GenTlAllmoduleimgfile> imgfileList = new ArrayList<GenTlAllmoduleimgfile>();
			                    GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			                    genTlAllmoduleimgfile.setImflBlobimage(imagePath);
			                    genTlAllmoduleimgfile.setImflFilename(rowImgFileName);
			                    genTlAllmoduleimgfile.setImflImagetype("BLK");
			                    genTlAllmoduleimgfile.setImflRefdoctype("CLI");
			                    imgfileList.add(genTlAllmoduleimgfile);

			                    commonFilterService.saveImg(imgfileList, saved.getClisKeyid(), genTlAllmoduleimgfile.getImflRefdoctype());
			                }

			            } else {
			                bean.setClisKeyid(existingKeyId);
			                BAL_CliTlStandards oldBean = (BAL_CliTlStandards) httpSession.getAttribute(existingKeyId);
			                if (oldBean == null) oldBean = new BAL_CliTlStandards();

			                // Set createdon directly on bean to prevent <* *> placeholder
			                String createdOn = (String) httpSession.getAttribute("clisCreatedon_" + existingKeyId);
			                if (createdOn != null && !createdOn.isEmpty()) {
			                    bean.setClisCreatedon(createdOn);
			                    oldBean.setClisCreatedon(createdOn);
			                }
			                // Preserve old freq/activity for calendar regeneration check
			                String oldFreq = (String) httpSession.getAttribute("clisFreq_" + existingKeyId);
			                String oldAct  = (String) httpSession.getAttribute("clisAct_"  + existingKeyId);
			                if (oldFreq != null) oldBean.setClisFrequencyunit(oldFreq);
			                if (oldAct  != null) oldBean.setClisActivitytype(oldAct);

			                cliTlStandardsService.update(bean, oldBean, formBean);
			                lastSavedKeyId = existingKeyId;

			                // same delete-then-reinsert pattern as Complaint Gallery's update branch
			                if (!UIUtils.isValidKeyId(rowImgFileName)) {
			                    //cliTlStandardsService.deleteImage(existingKeyId, "BLK", "BLK");//BLK
			                } else {
			                    ensureImageFileExists(imagePath, rowImgFileName, imageBytesCache); // FIX

			                    //cliTlStandardsService.deleteImage(existingKeyId, "BLK", "CLI");//BLKCLI

			                    List<GenTlAllmoduleimgfile> imgfileList = new ArrayList<GenTlAllmoduleimgfile>();
			                    GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			                    genTlAllmoduleimgfile.setImflBlobimage(imagePath);
			                    genTlAllmoduleimgfile.setImflFilename(rowImgFileName);
			                    genTlAllmoduleimgfile.setImflImagetype("BLK");
			                    genTlAllmoduleimgfile.setImflRefdoctype("CLI");
			                    imgfileList.add(genTlAllmoduleimgfile);

			                    commonFilterService.saveImg(imgfileList, existingKeyId, genTlAllmoduleimgfile.getImflRefdoctype());
			                }
			            }

			        }

			        JSONObject returnData = new JSONObject();
			        returnData.put("clitSaveResult", lastSavedKeyId != null ? lastSavedKeyId : "");
			        if (UIUtils.isValidKeyId(openFileMgr) && lastSavedKeyId != null)
			            returnData.put("openfilemgr", true);
			        out.print(returnData.toString());

			    } catch (ValidationExceptions e) {
			        JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ClitcreationException");
			        out.print(errMessage.toString());
			    } catch (Exception e) {
			        CommonFunctions.debugMsg("saveMultipleClit error: " + e.getMessage());
			        JSONObject err = new JSONObject();
			        err.put("tpmException", "Data Not Saved: " + e.getMessage());
			        out.print(err.toString());
			    }
			}

			// Ensures the temp image file exists on disk before saveImg() reads it.
			// Restores from the session-backed byte cache if the physical file is missing
			// (covers both "consumed mid-loop by an earlier row" and "already gone before
			// this request even started" cases). Servlet-only — no bean/DAO/CommonFunctions changes.
			private void ensureImageFileExists(String imagePath, String fname, Map<String, byte[]> cache) {
			    if (!UIUtils.isValidKeyId(fname) || !cache.containsKey(fname)) return;

			    File f = new File(imagePath, fname);
			    if (!f.exists()) {
			        try {
			            java.nio.file.Files.write(f.toPath(), cache.get(fname));
			            CommonFunctions.debugMsg("Restored missing temp image before save: " + fname);
			        } catch (IOException ioe) {
			            CommonFunctions.debugMsg("Could not restore image " + fname + ": " + ioe.getMessage());
			        }
			    }
			}	 
		 private void removeMultipleClit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			    ServletOutputStream out = response.getOutputStream();
			    try {
			        String keyid = request.getParameter("keyid");
			        if (!UIUtils.isValidKeyId(keyid)) {
			            JSONObject err = new JSONObject();
			            err.put("tpmException", "Invalid Key ID");
			            out.print(err.toString());
			            return;
			        }
			        BAL_CliTlStandards bean = new BAL_CliTlStandards();
			        bean.setClisKeyid(keyid);
			        cliTlStandardsService.delete(bean);

			        JSONObject result = new JSONObject();
			        result.put("formClear", true);
			        result.put("tpmException", "Deleted Successfully");
			        out.print(result.toString());

			    } catch (Exception e) {
			        CommonFunctions.debugMsg("removeMultipleClit error: " + e.getMessage());
			        JSONObject err = new JSONObject();
			        err.put("tpmException", "Delete Failed: " + e.getMessage());
			        out.print(err.toString());
			    }
			}
		 private String nullSafe(net.sf.json.JSONObject obj, String key) {
			    String val = obj.optString(key, "").trim();
			    return (val.equals("null") || val.equals(" ")) ? "" : val;
			}
		 
		 //deleteimage
		 private void deleteClitBlockDiagImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
			    String clisKeyid = request.getParameter("clisKeyid");
			    String imgType   = request.getParameter("imgType");
			    String refDoc    = request.getParameter("refDoc");

			    JSONObject result = new JSONObject();
			    try {
			        cliTlStandardsService.deleteImage(clisKeyid, imgType, refDoc);
			        result.put("success", true);
			    } catch (Exception e) {
			        result.put("success", false);
			        result.put("error", e.getMessage());
			    }

			    response.setContentType("application/json");
			    PrintWriter out = response.getWriter();
			    out.write(result.toString());
			    out.flush();
			}
		
		 private void saveClitEqpImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
			    String clisKeyid = request.getParameter("clisKeyid");
			    String imagePath = UIUtils.getImagePath(request);

			    String[] refDocTypes = {"EQ1", "EQ2", "EQ3"};

			    JSONObject result = new JSONObject();
			    try {
			        if (clisKeyid == null || clisKeyid.trim().isEmpty()) {
			            result.put("success", false);
			            result.put("error", "Equipment not selected.");
			        } else {
			            boolean savedAny = false;

			            for (int i = 1; i <= 3; i++) {
			                String fileName = request.getParameter("img" + i + "Path");
			                if (fileName == null || fileName.trim().isEmpty()) continue;
			                fileName = fileName.trim();

			                
			                if (fileName.contains("/")) {
			                    fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
			                }
			                int len = fileName.length();
			                if (len % 2 == 0) {
			                    String firstHalf = fileName.substring(0, len / 2);
			                    String secondHalf = fileName.substring(len / 2);
			                    if (firstHalf.equals(secondHalf)) {
			                        fileName = firstHalf;
			                    }
			                }

			                CommonFunctions.debugMsg("saveClitEqpImage slot " + i + " cleaned fileName=" + fileName);

			                GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
			                genTlAllmoduleimgfile.setImflBlobimage(imagePath);
			                genTlAllmoduleimgfile.setImflFilename(fileName);	      
			                genTlAllmoduleimgfile.setImflImagetype("EQP");
			                genTlAllmoduleimgfile.setImflRefdoctype(refDocTypes[i - 1]);

			                List<GenTlAllmoduleimgfile> imgfileList = new ArrayList<GenTlAllmoduleimgfile>();
			                imgfileList.add(genTlAllmoduleimgfile);

			                commonFilterService.saveImg(imgfileList, clisKeyid, genTlAllmoduleimgfile.getImflRefdoctype());
			                savedAny = true;
			            }

			            if (savedAny) {
			                result.put("success", true);
			            } else {
			                result.put("success", false);
			                result.put("error", "No images to save.");
			            }
			        }
			    } catch (Exception e) {
			        result.put("success", false);
			        result.put("error", e.getMessage());
			    }

			    response.setContentType("application/json");
			    PrintWriter out = response.getWriter();
			    out.write(result.toString());
			    out.flush();	
			}
		 
	
		 
		 //for delete
		 
		 private void deleteClit(HttpServletRequest request, HttpServletResponse response,BAL_CliTlStandardFormBean cliTlStandardFormBean  ) throws IOException{
				//assmid
		    	//CommonFunctions.debugMsg("inside deleteClit");
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		//CommonFunctions.debugMsg("inside saveClit");
		    		String AssemblyId= (String) httpSession.getAttribute("jhclitassemblyID");
		    		BAL_CliTlStandards existCliTlStandards = (BAL_CliTlStandards)httpSession.getAttribute("cliTlStandards"); 
		    		BAL_CliTlStandards newCliTlStandards = new BAL_CliTlStandards();
		    		BAL_PlmTlMultiplemethodsmst newPlmTlMultiplemethodsmst = new BAL_PlmTlMultiplemethodsmst();
		    		BAL_PlmTlToolsdtl newPlmTlToolsdtl = new BAL_PlmTlToolsdtl();
					newCliTlStandards.setClisCreatedby(user.getUsrm_ccno());
					//CommonFunctions.debugMsg("AssmID  :"+AssemblyId);
					newCliTlStandards.setClisAssemblyid(AssemblyId);//machine area id
					newCliTlStandards.setClisInactivateddate(cliTlStandardFormBean.getInactiveDate());
					String KeyID = (String) httpSession.getAttribute("jhclitId");
					//CommonFunctions.debugMsg("KEY ID   :-"+KeyID);
					newCliTlStandards.setClisKeyid(KeyID);//KeyId
					
					newCliTlStandards =(BAL_CliTlStandards)UIUtils.setBeanProperties((Object)newCliTlStandards,request);
					newPlmTlMultiplemethodsmst =(BAL_PlmTlMultiplemethodsmst)UIUtils.setBeanProperties((Object)newPlmTlMultiplemethodsmst, request);
					newPlmTlToolsdtl = (BAL_PlmTlToolsdtl)UIUtils.setBeanProperties((Object)newPlmTlToolsdtl, request);
					//newPlmTlMultiplemethodsmst = getMlmmMethoddescription();
					if( newPlmTlMultiplemethodsmst != null)
						newCliTlStandards.getMethodDetail().add(newPlmTlMultiplemethodsmst);
					if( newPlmTlToolsdtl != null)
					newCliTlStandards.getToolsDetail().add(newPlmTlToolsdtl);
					
					cliTlStandardFormBean =(BAL_CliTlStandardFormBean) UIUtils.setBeanProperties((Object)cliTlStandardFormBean,request);
					
					try{
						//CommonFunctions.debugMsg("  newCliTlStandards.getClisKeyid() " +  newCliTlStandards.getClisKeyid());
						//CommonFunctions.debugMsg("Delete Function called");
						existCliTlStandards =	cliTlStandardsService.delete(newCliTlStandards);
						httpSession.setAttribute(existCliTlStandards.getClisKeyid(), existCliTlStandards);
						httpSession.setAttribute("CliTlStandards", existCliTlStandards);
						String formBeanIdentifier = "cliTlStandardFormBean"+cliTlStandardFormBean.getFormActionMode();
						httpSession.setAttribute(formBeanIdentifier,cliTlStandardFormBean);
								
						JSONObject mode = new JSONObject();
						mode.put("formMode",cliTlStandardFormBean.getFormActionMode());
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("clitKeyids",existCliTlStandards.getClisKeyid() );
						persistentData.put("fromBean", formBeanIdentifier);
						JSONObject forwardData = new JSONObject();
						forwardData.put("clitKeyids",existCliTlStandards.getClisKeyid() );
						mode.put("forwardData",forwardData);
						mode.put("persistentData", persistentData);
						JSONObject err = new JSONObject();
						err.put("formClear",true);	
						err.put("tpmException","Data Deleted Successfully");
						out.print(err.toString());
						//out.print()	
						//CommonFunctions.debugMsg("end of delete");
						
					}catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "ClitcreationException");
						errMessage.put("fromMode",cliTlStandardFormBean.getFormActionMode());
						out.print(errMessage.toString());
						
					}catch(Exception e)
					{
						//CommonFunctions.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
					}

	    	}
	 }

     private BAL_PlmTlMultiplemethodsmst getPlmTlMultiplemethodsmst(List<BAL_PlmTlMultiplemethodsmst> plmTlMultiplemethodsmstList, String mlmmKeyid)
     {
    	 int index = 0;
    	 for( BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst:plmTlMultiplemethodsmstList)
    	 {
    		 if( plmTlMultiplemethodsmst.getMlmmKeyid().equals(mlmmKeyid) ){
    			 plmTlMultiplemethodsmstList.remove(index);
    			 return plmTlMultiplemethodsmst;
    		 }	 
    		 index++;
    	 }
    	 return null; 
     }
	//for tools 
     private JSONArray getToolsTreeArray(List <BAL_GenVwToolcategory> toolpickupList, String selectdElementIds){
    	 JSONArray jSONArray = new JSONArray();
    	 
     	for(int i=0; i<toolpickupList.size(); i++){
         	
     	//	if(menuList.get(i).getMenuLevel().equals("1"))
     	//	{	
     			JSONObject jSONObject = new JSONObject();

     			jSONObject.put("data",toolpickupList.get(i).getDisplaycode());
	                jSONObject.put("state","close");
	                String elementId = toolpickupList.get(i).getElementid().trim();
	               // //CommonFunctions.debugMsg("elementIdbefore --"+elementId);
	                JSONObject jsonAttr = new JSONObject();
	                jsonAttr.put("id", elementId);
	                jsonAttr.put("txtPtldToolid",elementId);
	                jsonAttr.put("parentId", toolpickupList.get(i).getParentid());
	                jsonAttr.put("elementType", toolpickupList.get(i).getElementtype());
	                jsonAttr.put("displayCode", toolpickupList.get(i).getDisplaycode());
	                if( selectdElementIds != null && selectdElementIds.indexOf(elementId)>= 0 ){
	                	CommonFunctions.debugMsg("elementId --"+elementId+"----"+selectdElementIds );
	                	jsonAttr.put( "class", "jstree-checked");
	                }
	                jSONObject.put("attr", jsonAttr);
	                jsonAttr = null;
	                jSONObject.put("children","[{}]");
	                
	                jSONArray.put(jSONObject);
	                jSONObject=null;
     	}         
     	return jSONArray;
     }
    private JSONArray getJstreeNodeObject (String id,String parentId,String displayCode,String elementType){
    	JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("data",displayCode);
	    jSONObject.put("state","close");
	    JSONObject jsonAttr = new JSONObject();
	    jsonAttr.put("id",id);
	    jsonAttr.put("txtPtldToolid",id);
	    jsonAttr.put("parentId", parentId);
	    jsonAttr.put("elementType",elementType);
	    jsonAttr.put("displayCode",displayCode);
	    jSONObject.put("attr", jsonAttr);
	    jsonAttr = null;
	    jSONObject.put("children","[{}]");
	    jSONArray.put(jSONObject);
	    jSONObject=null;
		return jSONArray;
     	
    }
 
}
