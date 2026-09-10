package com.akranta.tpm.controller;


 import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.BAL_PlmTlStandardsFormBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenVwToolcategory;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl;
import com.akranta.tpm.model.BAL_PlmTlMethodsmst;
import com.akranta.tpm.model.BAL_PlmTlMethodtasklist;
import com.akranta.tpm.model.BAL_PlmTlPmsftpermitlink;
import com.akranta.tpm.model.BAL_PlmTlShutdowncal;
import com.akranta.tpm.model.BAL_PlmTlStandards;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_GenVwToolcategoryService;
import com.akranta.tpm.service.BAL_PlmTlShutdowncalservice;
import com.akranta.tpm.service.BAL_PlmTlStandardsService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_GenVwToolcategoryServiceImpl;
import com.akranta.tpm.service.impl.BAL_PlmTlStandardsServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.service.api.BAL_PlmStandardsServiceApi;



public class BAL_preventivemaintenanceservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 CommonFilterService commonFilterService;
	 BAL_GenVwToolcategoryService genVwToolcategoryService ;
	 BAL_PlmTlStandardsService plmTlStandardsService;
	 BAL_PlmTlShutdowncalservice plmTlShutdowncalservice;
	 BAL_PlmStandardsServiceApi balpmstandards;
	private static int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BAL_preventivemaintenanceservlet() {
        super();
        // TODO Auto-generated constructor stub
/*        try {
			commonFilterService = new CommonFilterServiceImpl();
			plmTlStandardsService = new PlmTlStandardsServiceImpl();
			genVwToolcategoryService = new GenVwToolcategoryServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
			process(request, response);
		} catch (ValidationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (ValidationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
    private void initilizeInputMode(FormModes mode,HttpServletRequest request, HttpSession httpSession)throws Exception
	 {    	
    	CommonFunctions.debugMsg("Find Mode  "+mode);
    	
    		Enumeration<String> parameterNames = request.getParameterNames();
    		boolean firstTime = false;
    		if(! parameterNames.hasMoreElements()){
    			firstTime = true;
    		}
    	
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean = new BAL_PlmTlStandardsFormBean(mode);
	    	
	    	String pmstdKeyid =request.getParameter("pmstdKeyid");
	    	httpSession.setAttribute("pmstdKeyid", pmstdKeyid);
	    	CommonFunctions.debugMsg("pmstdKeyid intilise  "+pmstdKeyid);
	    	BAL_PlmTlStandards plmTlStandards= new BAL_PlmTlStandards ();
	    	String pmsdMachineID= request.getParameter("pmsdMachineID");

			String pmsdActivityType= request.getParameter("activitytype");
			String assmId =request.getParameter("cmbAssmbid");
			String tradeId = request.getParameter("tradeId");
			plmTlStandards.setPmsdMachineid(pmsdMachineID);
			plmTlStandards.setPmsdTradeid(tradeId);
			plmTlStandards.setPmsdActivitytype(pmsdActivityType);
			
		 	if(mode.equals(FormModes.create)){
		 		AdmTlUsermst user = UIUtils.getLoginUser(request);
				//user.getUsrm_ccno();
		 		//String activitytype = (String)httpSession.getAttribute("activitytype");
		 		String activitytype = request.getParameter("activitytype");
		 		//String tradeId   = (String)httpSession.getAttribute("tradeId");
		 		//String assemblyId   = (String)httpSession.getAttribute("PMAssmId");
		 		String assemblyId   = request.getParameter("PMAssmId");
				CommonFunctions.debugMsg("input called"+user.getUsrm_ccno());
				plmTlStandards.setPmsdPreparedbyid(user.getUsrm_ccno());
				plmTlStandards.setPmsdActivitytype(activitytype);
				plmTlStandards.setPmsdTradeid(tradeId);
				CommonFunctions.debugMsg("assemblyId "+assemblyId);
				if(UIUtils.isValidKeyId(assemblyId))
					plmTlStandards.setPmsdAssemblyid(assemblyId);
				JSONObject returnData = new JSONObject();
				JSONArray permitLinkData = new JSONArray();
				permitLinkData=JSONArray.fromCollection(plmTlStandards.getPermitlinkDetail());
				returnData.put("permitLinkData",permitLinkData);
				request.setAttribute("addInfoData",returnData );
				request.setAttribute("plmTlStandards",plmTlStandards );
				
			}
		 	httpSession.removeAttribute("plmTlStandards_Servlet");
		 	if( UIUtils.isValidKeyId(pmstdKeyid)){

				try {
					 CommonFunctions.debugMsg("inside brfore request setattribute");
					    plmTlStandards = plmTlStandardsService.getFillValue(pmstdKeyid);
					     httpSession.setAttribute("plmTlStandards_Servlet", plmTlStandards); 
			           //  CommonFunctions.debugMsg("Activitysubtype called"+plmTlStandards.getPmsdActivitysubtype().replace(",","','"));
			             String replActSub = plmTlStandards.getPmsdActivitysubtype().replace(",","','");
			            List<String[]> activitySub = plmTlStandardsService.getactSubValue(replActSub);
			            String actSub = "";

			            if(activitySub.size()>0){
			            	
				           for(int i = 0; i<activitySub.size();i++){
				        	    actSub += (activitySub.get(i))[0].concat(",");
				           }
				           actSub = actSub.substring(0,actSub.length()-1);
				            request.setAttribute("activitySub",actSub );
			            }
			            
			            
			            
			            request.setAttribute("plmTlStandards", plmTlStandards);
						 request.setAttribute("plmTlStandardsFormBean",plmTlStandardsFormBean);
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		 	}
		 	if(! firstTime){
			 	request.setAttribute("pmMaingridCmbFactid", httpSession.getAttribute("pmMaingridCmbFactid"));
				request.setAttribute("pmMaingridCmbSectid",httpSession.getAttribute("pmMaingridCmbSectid"));
				request.setAttribute("pmMaingridCmbCellid",httpSession.getAttribute("pmMaingridCmbCellid"));
				request.setAttribute("pmMaingridCmbMchid",httpSession.getAttribute("pmMaingridCmbMchid"));
				request.setAttribute("pmMaingridCmbAssmbid",httpSession.getAttribute("pmMaingridCmbAssmbid"));
				request.setAttribute("pmMaingridCmbjobtype",httpSession.getAttribute("pmMaingridCmbjobtype"));
				request.setAttribute("pmMaingridCmbTradeid",httpSession.getAttribute("pmMaingridCmbTradeid"));
		 	
				String flid =(String) httpSession.getAttribute("pmMaingridFlid");
				
				if( UIUtils.isValidKeyId(flid)){
					plmTlStandards.setPmsdFlid(flid);
				}
				request.setAttribute("pmMaingridCmbCostcenter",httpSession.getAttribute("pmMaingridCmbCostcenter"));
		 	}
			
			
			
			httpSession.removeAttribute("pmMaingridCmbFactid");
			httpSession.removeAttribute("pmMaingridCmbSectid");
			httpSession.removeAttribute("pmMaingridCmbCellid");
			httpSession.removeAttribute("pmMaingridCmbMchid");
			httpSession.removeAttribute("pmMaingridCmbAssmbid");
			httpSession.removeAttribute("pmMaingridCmbjobtype");
			httpSession.removeAttribute("pmMaingridCmbTradeid");
			httpSession.removeAttribute("pmMaingridFlid");
			httpSession.removeAttribute("pmMaingridCmbCostcenter");
            
			
			
		 	httpSession.removeAttribute("plmTlStandardsFormBean");
			httpSession.setAttribute("plmTlStandardsFormBean", plmTlStandardsFormBean);
		
			request.setAttribute("plmTlStandardsFormBean", plmTlStandardsFormBean);
	 }
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		
		try {
			plmTlStandardsService = (BAL_PlmTlStandardsServiceImpl)UIUtils.getServiceObject(request,"BAL_PlmTlStandardsServiceImpl");
			genVwToolcategoryService = (BAL_GenVwToolcategoryServiceImpl)UIUtils.getServiceObject(request,"BAL_GenVwToolcategoryServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			plmTlShutdowncalservice =(BAL_PlmTlShutdowncalservice) UIUtils.getServiceObject(request,"BAL_PlmTlShutdowncalserviceImpl");
			plmTlStandardsService.BAL_PlmTlStandardsServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String dispatchUrl = null;
		CommonFunctions.debugMsg("action " + action);
		//response.setContentType("text/html");
		//response.setContentType("text/json");
		/* * uri is in this form: /contextName/resourceName, * for example: /app01a/Product_input.action. * However, in the case of a default context, the * context name is empty, and uri has this form * /resourceName, e.g.: /Product_input.action */
		
		/*if (action.equals("prvnt_mntncform_input.prv")) 
		{ 
			CommonFunctions.debugMsg("input the jsp");
			// there is nothing to be done
			
			
			String pmsdKeyid = request.getParameter("pmsdKeyid");
			PlmTlStandards plmTlStandards =null;
			CommonFunctions.debugMsg(" pmsdKeyid " + pmsdKeyid);
			  
			if(pmsdKeyid != null)
				plmTlStandards = (PlmTlStandards)httpSession.getAttribute(pmsdKeyid);
			
			if( plmTlStandards == null)	
				plmTlStandards = new  PlmTlStandards();
			else
				CommonFunctions.debugMsg(" cliTlStandards " + plmTlStandards.getPmsdKeyid());
			
			String machineId = request.getParameter("pmsdMachineID");
			if( UIUtils.isValidKeyId(machineId)){
				plmTlStandards.setPmsdMachineid(machineId);
			}
			
			PlmTlStandardsFormBean plmTlStandardsFormBean = new PlmTlStandardsFormBean();
			plmTlStandards.setPmsdCreatedby(user.getUsrm_ccno());
			plmTlStandardsFormBean.setResponsibility(user.getUsrm_ccno());
			
			request.setAttribute("plmTlStandards", plmTlStandards);
			request.setAttribute("plmTlStandardsFormBean", plmTlStandardsFormBean);
		
		}
		else*/ if( action.equals("preventive_view.prv"))
		{	
			
	    }
		else if( action.equals("prvnt_mntncform_save.prv"))
		{	
			CommonFunctions.debugMsg("inside save action");
			String sprSave = request.getParameter("spares");
			CommonFunctions.debugMsg("insidesprSave  "+sprSave);
			httpSession.setAttribute("sprSave",sprSave );
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean = new BAL_PlmTlStandardsFormBean();
			savePmsd(request,response,plmTlStandardsFormBean);
	   }
		//mano
		else if (action.equals("prvnt_mntncform_multiple_save.prv"))
		{
		    CommonFunctions.debugMsg("inside multiple save action");
		    saveMultiplePmsd(request, response);
		}
		else if (action.equals("MultiplePmStd_remove.prv")) {
		    removeMultiplePmsd(request, response);
		}

		
		 
		
		else if (action.equals("preventive_input.prv")||action.equals("Mouldpreventive_input.prv")) 
		{ 
			CommonFunctions.debugMsg("Out the jsp"); 
			String modeStr = request.getParameter(ReqtParamNameConst.FORM_MODE);
			String bdmmode = request.getParameter("bdmmode");
			String processid = request.getParameter("processid");
			String flid = request.getParameter("flid");
			String tflid=request.getParameter("hdnTenStpFlid");
			httpSession.setAttribute("bdmmode",bdmmode);  
			String yyId = request.getParameter("yyId");
			httpSession.setAttribute("yyId",yyId);
			String whyMachineId = request.getParameter("machineID"); 
			httpSession.setAttribute("whyMachineId",whyMachineId);
			request.setAttribute("whyMachineId",whyMachineId);
			String assemblyId = request.getParameter("assemblyId");
			httpSession.removeAttribute("PMAssmId");
			CommonFunctions.debugMsg("flid  Print "+tflid);
			if(UIUtils.isValidKeyId(flid)){
				CommonFunctions.debugMsg("flid  "+flid);
				CommonFunctions.debugMsg("processid  "+processid);
				//request.setAttribute("tFlid", flid);
			}
			
			
			
			if(UIUtils.isValidKeyId(assemblyId))
			{
				httpSession.setAttribute("PMAssmId",assemblyId);
			}
			String dockKey = request.getParameter("docId");
			if(UIUtils.isValidKeyId(processid))
				dockKey = processid;
			httpSession.setAttribute("dockKey",dockKey);
			httpSession.removeAttribute("PmStandardFormMode");
			FormModes mode = FormModes.create; 
			if(modeStr == null || ( modeStr != null && modeStr.equals(FormModeConsts.create ) )) 
				mode=FormModes.create;
			else if(  modeStr.equals(FormModeConsts.modify) ){
				mode=FormModes.modify;
			}
			else 
				mode=FormModes.view;
			
			httpSession.setAttribute("PmStandardFormMode",mode);
			initilizeInputMode(mode, request, httpSession);	
			
			if(UIUtils.isValidKeyId(tflid))
			{
				request.setAttribute("tFlid", tflid);
				request.setAttribute("whyMachineId","");
				request.setAttribute("pmMaingridCmbMchid","");
			}
			else {
				String loginFlid = (String) httpSession.getAttribute("loginFlid");
				request.setAttribute("tFlid", loginFlid);
				request.setAttribute("whyMachineId","");
				request.setAttribute("pmMaingridCmbMchid","");
				
			}
				//dispatchUrl = "/pages/pmworkOrder/genWO.jsp";
			    dispatchUrl = "/pages/PMStandards/pmStd_frm.jsp";
			// dispatchUrl = "/pages/PMStandards/pmFormInfo.jsp";
		}
		
		else if(action.equals("preventive_addinfo_input.prv"))
		{	
			String bdmmode = (String)httpSession.getAttribute("bdmmode");
			request.setAttribute("bdmmode",bdmmode);
			dispatchUrl = "/pages/PMStandards/prvnt-mntnc-add-info.jsp";
		}
		
		///Pm Report
		else if(action.equals("PMReport_input.prv")){
			UIUtils.forwardRequest(request, response, "pages/PMStandards/PMReport.jsp");
		}
		else if(action.equals("PMReport_getCol.prv")){
			PmReportGetCol(request,response);
		}
		else if(action.equals("PMReport_getData.prv")){
			PmReportGetData(request,response);
		}
		
		else if(action.equals("prvnt_mntnc_actv_input.prv"))
		{	
			
			dispatchUrl = "/pages/PMStandards/prvnt-mntnc-actv-ws.jsp";
		}
		else if(action.equals("prvnt_mntncform_input.prv"))
		{	
			httpSession.setAttribute("PmStandardFormMode", FormModes.create);
			String pmsdMachineID= request.getParameter("pmsdMachineID");

			String pmsdActivityType= request.getParameter("activitytype");
			String assmId =request.getParameter("cmbAssmbid");
			String tradeId = request.getParameter("tradeId");
			String flid= request.getParameter("pmsdflid");
			String elementid = request.getParameter("pmsdelementid");
			String costcenter = request.getParameter("cmbCostcenter");
			if(UIUtils.isValidKeyId(assmId)){

				request.setAttribute("assmId", assmId );
			}
			BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
			newPlmTlStandards.setPmsdMachineid(pmsdMachineID);
			newPlmTlStandards.setPmsdTradeid(tradeId);
			newPlmTlStandards.setPmsdActivitytype(pmsdActivityType);
			newPlmTlStandards.setPmsdFlid(flid);
			newPlmTlStandards.setPmsdElementid(elementid);
			newPlmTlStandards.setPmsdPreparedbyid(user.getUsrm_ccno());
			
			request.setAttribute("costcenter", costcenter);
			request.setAttribute("machineId", pmsdMachineID);
			/*request.setAttribute("tradeId ", tradeId );
			request.setAttribute("machineId", pmsdMachineID);
			request.setAttribute("activitytype", pmsdActivityType);*/
			request.setAttribute("plmTlStandards", newPlmTlStandards);
			 
			httpSession.removeAttribute("pmsdKeyID");
			
			
		//	String 
			if(UIUtils.isValidKeyId(pmsdActivityType)){
				httpSession.removeAttribute("activitytype");
				httpSession.removeAttribute("tradeId");
				CommonFunctions.debugMsg("mode create");
				FormModes mode = FormModes.create;
				httpSession.setAttribute("activitytype", pmsdActivityType);
				httpSession.setAttribute("tradeId", tradeId);
				initilizeInputMode(mode, request, httpSession);
				
			}
			httpSession.setAttribute("pmMaingridCmbMchid", pmsdMachineID);
		    httpSession.setAttribute("pmMaingridCmbAssmbid", assmId);
		    httpSession.setAttribute("pmMaingridCmbjobtype", pmsdActivityType);
		    httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
		    httpSession.setAttribute("pmMaingridFlid", flid);
		    httpSession.setAttribute("pmMaingridCmbCostcenter", costcenter);
		    
			dispatchUrl = "/pages/PMStandards/pmFormInfo.jsp";
		}
		
		/*
		 * else if(action.equals("prvnt_mntncform_multiple_input.prv")) {
		 * httpSession.setAttribute("PmStandardFormMode", FormModes.create); String
		 * pmsdMachineID= request.getParameter("pmsdMachineID");
		 * 
		 * String pmsdActivityType= request.getParameter("activitytype"); String assmId
		 * =request.getParameter("cmbAssmbid"); String tradeId =
		 * request.getParameter("tradeId"); String flid=
		 * request.getParameter("pmsdflid"); String elementid =
		 * request.getParameter("pmsdelementid"); String costcenter =
		 * request.getParameter("cmbCostcenter"); if(UIUtils.isValidKeyId(assmId)){
		 * 
		 * request.setAttribute("assmId", assmId ); } BAL_PlmTlStandards
		 * newPlmTlStandards = new BAL_PlmTlStandards();
		 * newPlmTlStandards.setPmsdMachineid(pmsdMachineID);
		 * newPlmTlStandards.setPmsdTradeid(tradeId);
		 * newPlmTlStandards.setPmsdActivitytype(pmsdActivityType);
		 * newPlmTlStandards.setPmsdFlid(flid);
		 * newPlmTlStandards.setPmsdElementid(elementid);
		 * newPlmTlStandards.setPmsdPreparedbyid(user.getUsrm_ccno());
		 * 
		 * 
		 * request.setAttribute("costcenter", costcenter);
		 * request.setAttribute("machineId", pmsdMachineID);
		 * request.setAttribute("tradeId ", tradeId); request.setAttribute("machineId",
		 * pmsdMachineID); request.setAttribute("activitytype", pmsdActivityType);
		 * request.setAttribute("plmTlStandards", newPlmTlStandards);
		 * 
		 * httpSession.removeAttribute("pmsdKeyID");
		 * 
		 * 
		 * // String if(UIUtils.isValidKeyId(pmsdActivityType)){
		 * httpSession.removeAttribute("activitytype");
		 * httpSession.removeAttribute("tradeId");
		 * CommonFunctions.debugMsg("mode create"); FormModes mode = FormModes.create;
		 * httpSession.setAttribute("activitytype", pmsdActivityType);
		 * httpSession.setAttribute("tradeId", tradeId); initilizeInputMode(mode,
		 * request, httpSession);
		 * 
		 * } httpSession.setAttribute("pmMaingridCmbMchid", pmsdMachineID);
		 * httpSession.setAttribute("pmMaingridCmbAssmbid", assmId);
		 * httpSession.setAttribute("pmMaingridCmbjobtype", pmsdActivityType);
		 * httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
		 * httpSession.setAttribute("pmMaingridFlid", flid);
		 * httpSession.setAttribute("pmMaingridCmbCostcenter", costcenter);
		 * 
		 * dispatchUrl = "/pages/PMStandards/pmFormInfo_multiple.jsp"; }
		 */
		else if(action.equals("prvnt_mntncform_multiple_input.prv"))
		{
		    httpSession.setAttribute("PmStandardFormMode", FormModes.create);
		    String pmsdMachineID = request.getParameter("pmsdMachineID");

		    String pmsdActivityType = request.getParameter("activitytype");
		    String assmId = request.getParameter("cmbAssmbid");
		    String tradeId = request.getParameter("tradeId");
		    String flid = request.getParameter("pmsdflid");
		    String elementid = request.getParameter("pmsdelementid");
		    String costcenter = request.getParameter("cmbCostcenter");
		    if(UIUtils.isValidKeyId(assmId)){
		        request.setAttribute("assmId", assmId);
		    }
		    BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
		    newPlmTlStandards.setPmsdMachineid(pmsdMachineID);
		    newPlmTlStandards.setPmsdTradeid(tradeId);
		    newPlmTlStandards.setPmsdActivitytype(pmsdActivityType);
		    newPlmTlStandards.setPmsdFlid(flid);
		    newPlmTlStandards.setPmsdElementid(elementid);
		    newPlmTlStandards.setPmsdPreparedbyid(user.getUsrm_ccno());

		    request.setAttribute("costcenter", costcenter);
		    request.setAttribute("machineId", pmsdMachineID);
		    request.setAttribute("tradeId", tradeId);   // fixed: removed trailing space in key
		    request.setAttribute("activitytype", pmsdActivityType);
		    request.setAttribute("plmTlStandards", newPlmTlStandards);

		    httpSession.removeAttribute("pmsdKeyID");

		    if(UIUtils.isValidKeyId(pmsdActivityType)){
		        httpSession.removeAttribute("activitytype");
		        httpSession.removeAttribute("tradeId");
		        CommonFunctions.debugMsg("mode create");
		        FormModes mode = FormModes.create;
		        httpSession.setAttribute("activitytype", pmsdActivityType);
		        httpSession.setAttribute("tradeId", tradeId);
		        initilizeInputMode(mode, request, httpSession);
		    }
		    httpSession.setAttribute("pmMaingridCmbMchid", pmsdMachineID);
		    httpSession.setAttribute("pmMaingridCmbAssmbid", assmId);
		    httpSession.setAttribute("pmMaingridCmbjobtype", pmsdActivityType);
		    httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
		    httpSession.setAttribute("pmMaingridFlid", flid);
		    httpSession.setAttribute("pmMaingridCmbCostcenter", costcenter);

		    dispatchUrl = "/pages/PMStandards/pmFormInfo_multiple.jsp";
		}
		else if (action.equals("prvnt_mntncform_multiple_getCol.prv"))
		{
		    CommonFunctions.debugMsg("inside getcol - PM Standards multiple entry");

		    PrintWriter out = response.getWriter();
		    String flid  = request.getParameter("flid");
		    String mchId = request.getParameter("machineID");
		    String grid  = request.getParameter("grid");
		    CommonFunctions.debugMsg("flid : " + flid);
		    CommonFunctions.debugMsg("grid : " + grid);

		    if (UIUtils.isValidKeyId(grid) && "pmsd".equals(grid))
		    {
		        out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "MultiplePmStd"));
		        populateCommonFilter(request, "pmsdStandardCommonFilter", true);
		    }
		    else
		    {
		        out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "MultiplePmStd"));
		    }
		}
		else if (action.equals("prvnt_mntncform_multiple_getData.prv")) {
		    try {
		        String machId  = request.getParameter("machId");
		        String flId    = request.getParameter("flid");
		        String tradeId = request.getParameter("tradeId");

		        if (!UIUtils.isValidKeyId(machId))
		            machId = request.getParameter("cmbPmsdMulMachineid");

		        CommonFunctions.debugMsg("multiplePmStd_getData machId=" + machId
		                + " flId=" + flId + " tradeId=" + tradeId);

		        CommonFilter commonFilter = new CommonFilter();
		        commonFilter.setMachineId(machId);
		        commonFilter.setFlid(flId);
		        ComboFilter tradeFilter = new ComboFilter();
		        tradeFilter.setId(tradeId);
		        commonFilter.setTrade(tradeFilter);
		        commonFilter.setViewClick('Y');

		        PrintWriter out = response.getWriter();
		        List<String[]> dataList = plmTlStandardsService.getMultiplePmsdList(commonFilter);
		        JSONObject gridData = UIUtils.convertToJqGridTableObject(dataList, request, 1, 2);

		        out.println(gridData);

		    } catch (Exception e) {
		        CommonFunctions.debugMsg("multiplePmStd_getData error: " + e.getMessage());
		    }
		}
		else if(action.equals("prvnt_mntncform_modify.prv"))
		{	
			httpSession.removeAttribute("pmsdKeyID");
			FormModes mode = FormModes.create;
			String pmsdKey = request.getParameter("pmstdKeyid");
			String pmsdMachineID= request.getParameter("pmsdMachineID");
			String rptMode = request.getParameter("mode");
			String pmcalStatus=request.getParameter("pmcalStatus");
			request.setAttribute("pmcalStatus",pmcalStatus);
			request.setAttribute("machineId", pmsdMachineID);
			request.setAttribute("rptMode", rptMode);
			initilizeInputMode(mode, request, httpSession);
			httpSession.setAttribute("pmsdKeyID",pmsdKey);
			httpSession.setAttribute("PmStandardFormMode", FormModes.create);
			dispatchUrl = "/pages/PMStandards/pmFormInfo.jsp";
		}  
		else if(action.equals("prvnt_mntnc_ginfo_input.prv"))
		{	dispatchUrl = "/pages/PMStandards/prvnt-mntnc-general-info.jsp";
			/*FormModes mode = FormModes.create; 
			httpSession.setAttribute("pmstdKeyid", request.getParameter("pmstdKeyid"));
			httpSession.setAttribute("PmStandardFormMode",mode);
			String pmstdKeyid =request.getParameter("pmstdKeyid");
	    	CommonFunctions.debugMsg("pmstdKeyid intilise  "+pmstdKeyid);
	    	PlmTlStandards plmTlStandards= new PlmTlStandards ();
	    	PlmTlStandardsFormBean plmTlStandardsFormBean = new PlmTlStandardsFormBean(mode);
		 	if( UIUtils.isValidKeyId(pmstdKeyid)){
				try {
					 CommonFunctions.debugMsg("inside brfore request setattribute");
					    plmTlStandards = plmTlStandardsService.getFillValue(pmstdKeyid);
			             httpSession.setAttribute("plmTlStandards_Servlet", plmTlStandards); 
			    		 request.setAttribute("plmTlStandards", plmTlStandards);
						request.setAttribute("plmTlStandardsFormBean",plmTlStandardsFormBean);
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		 	}*/
			
		}
		
		else if(action.equals("prvnt-mntnc-rsrc_input.prv"))
		{	
			dispatchUrl = "/pages/PMStandards/prvnt-mntnc-resource.jsp";
		}
		
		else if(action.equals("prvntmntnc_fndfrm_input.prv"))
		{	
			dispatchUrl = "/pages/Standards/prvnt-mntnc-fnd-frm.jsp";
		}
		

		CommonFunctions.debugMsg(" dispatchUrl " + dispatchUrl);
		if (dispatchUrl != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			CommonFunctions.debugMsg(" response " + response); 
		}
		//
		/*for Functional Location*/
		else if(action.equals("functionalLocSDM.prv")){
			
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setLocation("cmbPmsdLocation");
			//functLocFieldNameBean.setFactory("cmbPmsdFactory");
			//mano
			functLocFieldNameBean.setSbu("cmbPmsdFactoryid");
			functLocFieldNameBean.setSection("cmbPmsdSectionid");
			functLocFieldNameBean.setCell("cmbPmsdCellid");
			functLocFieldNameBean.setMachine("cmbPmsdEquipmentid");
			functLocFieldNameBean.setFunctionalLocId("cmbPmsdFlid");
			functLocFieldNameBean.setLocnMandatory(true);
			
			/*functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);*/
			
			FormModes formModes = (FormModes)httpSession.getAttribute("sdmFormMode");
		
				//formModes = FormModes.view;
			
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
			else if(action.equals("functionalLoc.prv")){
				
				String pmsdActivityType = (String)httpSession.getAttribute("activitytype");
				String activityType = request.getParameter("actType");//frm monthlycal
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				//functLocFieldNameBean.setLocation("cmbPmsdLocation");
				//functLocFieldNameBean.setFactory("cmbPmsdFactory");
				//mano
				//functLocFieldNameBean.setSbu("cmbPmsdFactory");cmbPmsdFactoryid
				functLocFieldNameBean.setSbu("cmbPmsdFactoryid");
				functLocFieldNameBean.setSection("cmbPmsdSectionid");
				functLocFieldNameBean.setCell("cmbPmsdCellid");
				functLocFieldNameBean.setMachine("cmbPmsdEquipmentid");
				functLocFieldNameBean.setFunctionalLocId("cmbPmsdFlid");
			//	CommonFunctions.debugMsg("pmsdActivityType     "+pmsdActivityType+"  SDMactType   "+activityType);
				if("SDM".equals(pmsdActivityType) ){
					functLocFieldNameBean.setLocation("cmbPmsdLocation");
					functLocFieldNameBean.setLocnMandatory(true);
				}else if("SDM".equals(activityType) ){
					functLocFieldNameBean.setLocation("cmbsdmLocationid");
					functLocFieldNameBean.setLocnMandatory(true);
				}
				else{
					//functLocFieldNameBean.setFactMandatory(false);
					//mano
					functLocFieldNameBean.setSbuMandatory(false);
					functLocFieldNameBean.setSectMandatory(true);
				}
				/*functLocFieldNameBean.setCellMandatory(true);
				functLocFieldNameBean.setMachMandatory(true);*/
				
				FormModes formModes = (FormModes)httpSession.getAttribute("pmsdFormMode");
			
					//formModes = FormModes.view;
				
				
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
				
			}
  //for filling fact sect cell on select machine
    else if( action.equals("pmsMachine_fillcombo.prv"))
	{
    	CommonFunctions.debugMsg("Inside form fill action"+request.getParameter("eqpId"));
		httpSession.setAttribute("machineHirerachyId",request.getParameter("eqpId"));
	    CommonFunctions.debugMsg("machineHirerachyId"+request.getParameter("eqpId"));
		String machineHirerachyId = (String) httpSession.getAttribute("machineHirerachyId");
		CommonFunctions.debugMsg(machineHirerachyId+"machineHirerachyId");
		
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
	//for combo box
   else if( action.equals("combo_pmsdTrade.prv"))
	{
		try {//public List<ComboBox> getClitTradeCombo(String condSql) throws Exception;
			ComboFilter comboFilter =UIUtils.fillComboFilter(request);
				List<ComboBox> pmsdTradeid = plmTlStandardsService.getPmsdTradeCombo(comboFilter);
				UIUtils.writeComboBox(response, pmsdTradeid, comboFilter);
			} 
		catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
   else if( action.equals("combo_pmsdpreparedby.prv"))
	{
		try {
			ComboFilter comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  pmsdprpbyid = plmTlStandardsService.getpmsdpreparedbyCombo("",comboFilter);
				UIUtils.writeComboBox(response, pmsdprpbyid, comboFilter);
			} 
		catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//Pmsd_assemblyId.prv 
   else if( action.equals("Pmsd_assemblyId.prv"))
	{
		try {
			ComboFilter comboFilter =UIUtils.fillComboFilter(request);
				List<ComboBox>  pmsdassemblyId = plmTlStandardsService.getpmsdassemblyIdCombo("",comboFilter);
				UIUtils.writeComboBox(response, pmsdassemblyId, comboFilter);
			} 
		catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//insection combobox 
   else if( action.equals("inspectionCombo.prv"))
	{
		try {
				ComboFilter comboFilter =UIUtils.fillComboFilter(request); 
				List<ComboBox>  cbmInspectionId = plmTlStandardsService.getcbmInspectionIdCombo(comboFilter);
				UIUtils.writeComboBox(response, cbmInspectionId, comboFilter);
			} 
		catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
///Pmsd_SubassemblyId.prv
	/*
	 * else if( action.equals("Pmsd_SubassemblyId.prv")) { try { ComboFilter
	 * comboFilter =UIUtils.fillComboFilter(request); String
	 * assmId=request.getParameter("assmId"); List<ComboBox> pmsdsubassemblyId =
	 * plmTlStandardsService.getpmsdsubassemblyIdCombo(comboFilter,assmId);
	 * UIUtils.writeComboBox(response, pmsdsubassemblyId, comboFilter); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */
   else if( action.equals("Pmsd_SubassemblyId.prv"))
   {
       try {
               String assmId = request.getParameter("assmId");
               String machineId = request.getParameter("machineId");
               ComboFilter comboFilter = UIUtils.fillComboFilter(request);
               List<ComboBox> pmsdsubassemblyId = plmTlStandardsService.getpmsdsubassemblyIdCombo(comboFilter, assmId, machineId);
               UIUtils.writeComboBox(response, pmsdsubassemblyId, comboFilter);
           }
       catch (Exception e)
           {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
   }
// Pmsd_Jobtype.prv
   			   else if(action.equals("sdmActivity_input.prv")){
   				   
   			   }
   			   else if(action.equals("sdmActivity_getCol.prv"))
			   {
				   PrintWriter out = response.getWriter();
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelSdmActivity"));
			   }
			   else if(action.equals("sdmActivity_getData.prv"))
			   {
				   CommonFunctions.debugMsg("inside getdata");
				  try{
					  PrintWriter out = response.getWriter();
				   List<String[]>  activityList = plmTlStandardsService.getfillActivity();
				   JSONObject activityListgrd = UIUtils.convertToJqGridTableObject(activityList, request, 0, 0);
				   out.println(activityListgrd);
				  }
				  catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
   					 
			   else if(action.equals("fillJobtype_input.prv"))
			   {
				   dispatchUrl = "/tiles/filter/PMRelated.jsp";
			   }
			   else if(action.equals("fillJobtype_getCol.prv"))
			   {
				   PrintWriter out = response.getWriter();
				   
				   CommonFunctions.debugMsg("exit Getcol" +UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelJobType"));
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelJobType"));
				   CommonFunctions.debugMsg("exit Getcol");
			 
			   }
			   else if(action.equals("fillJobtype_getData.prv"))
			   {
				   CommonFunctions.debugMsg("inside getdata");
				  try{
					  PrintWriter out = response.getWriter();
				   List<String[]>  pjobtype = plmTlStandardsService.getfillJobType();
				   JSONObject masterGrid = UIUtils.convertToJqGridTableObject(pjobtype, request, 0, 0);
				   out.println(masterGrid);
				  }
				  catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
		   else if( action.equals("Pmsd_Jobtype.prv"))
			{
				try {
					    CommonFunctions.debugMsg("test jobtype load1");
					    ComboFilter comboFilter = UIUtils.fillComboFilter(request);
						//comboFilter=UIUtils.fillComboFilter(request);	
						List<ComboBox>  pmsdjobtype = plmTlStandardsService.getpmsdjobtypeCombo("",comboFilter);
						UIUtils.writeComboBox(response, pmsdjobtype, comboFilter);
					} 
				catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.getMessage();	
						e.printStackTrace();
						
					}
			}
		///combo_pmsdPhenomenaid.prv
		   else if( action.equals("combo_pmsdPhenomenaid.prv"))
			{
				try {
					    CommonFunctions.debugMsg("inside combo_pmsdPhenomenaid.prv ");
					    ComboFilter comboFilter =UIUtils.fillComboFilter(request);
						List<ComboBox>  pmsdPhenomenaid = plmTlStandardsService.getpmsdphenomenaIdCombo(comboFilter);
						CommonFunctions.debugMsg("pmsdPhenomenaid.siz    "+pmsdPhenomenaid.size());
						UIUtils.writeComboBox(response, pmsdPhenomenaid, comboFilter);
					} 
				catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		///combo_pmsdCauseid.prv
		   else if( action.equals("combo_pmsdCauseid.prv"))
			{
				try {
						ComboFilter comboFilter = UIUtils.fillComboFilter(request);

						List<ComboBox>  pmsdCauseid = plmTlStandardsService.getpmsdcauseIdCombo(comboFilter);
						UIUtils.writeComboBox(response, pmsdCauseid, comboFilter);
					} 
				catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		   else if(action.equals("combo_pmsdwhtfreq.prv"))
		    {
		        PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "selectwhatfreq"));
		    
		    }
		
		else if(action.equals("combo_pmsdMScondition.prv"))
	    {
	 	    PrintWriter out = response.getWriter();
	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "selectmcscondition"));
	    }
		   else if(action.equals("addInfo_input.prv")){
			   String hdnPmsdPhenomenaid = request.getParameter("hdnPmsdPhenomenaid");
			   String hdnPmsdCauseid = request.getParameter("hdnPmsdCauseid");
			   String hdnPmsdResultifnotdone = request.getParameter("hdnPmsdResultifnotdone");
			   String hdnPmsdCorrectiveaction = request.getParameter("hdnPmsdCorrectiveaction");
			   String hdnPmsdSafetyinstruction = request.getParameter("hdnPmsdSafetyinstruction");
			   String addGridData = request.getParameter("addGridData");
			   request.setAttribute("hdnPmsdPhenomenaid",hdnPmsdPhenomenaid);
			   request.setAttribute("hdnPmsdCauseid",hdnPmsdCauseid);
			   request.setAttribute("hdnPmsdResultifnotdone",hdnPmsdResultifnotdone);
			   request.setAttribute("hdnPmsdCorrectiveaction",hdnPmsdCorrectiveaction);
			   request.setAttribute("hdnPmsdSafetyinstruction",hdnPmsdSafetyinstruction);
			   request.setAttribute("addGridData",addGridData);
			   CommonFunctions.debugMsg("hdnPmsdPhenomenaid  "+ request.getAttribute("hdnPmsdSafetyinstruction"));
				UIUtils.forwardRequest(request, response, "/pages/PMStandards/AdditionalInformation.jsp");
		   }
		   else if(action.equals("addInfo_getCol.prv")){
			   PrintWriter out = response.getWriter();
			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelAddinfoData"));
		   }
		   else if(action.equals("addInfo_getData.prv")){
			   /*String[] row1 = {"H","","","Hot Work"};
			   String[] row2 ={"C","","","Confined Space Entry"};
			   String[] row3 ={"W","","","Working At Height"};
			   String[] row4 ={"L","","","Line Breaking Hazardous Material"};
			   String[] row5 ={"O","","","Lockout/Tagout"};
			   String[] row6 ={"P","","","Crane Work Permit"};
			   String[] row7 ={"E","","","Excavation Work Permit"};*/
			   PrintWriter out = response.getWriter();
			   CommonFunctions.debugMsg("addInfo_getData.prv ");
			   String pmStandardId = request.getParameter("pmStandardId");
			   List<String[]> addinfGrid  =  plmTlStandardsService.getPermitLinkData(pmStandardId);
			  /* addinfGrid.add(row1);
			   addinfGrid.add(row2);
			   addinfGrid.add(row3);
			   addinfGrid.add(row4);
			   addinfGrid.add(row5);
			   addinfGrid.add(row6);
			   addinfGrid.add(row7);
				 CommonFunctions.debugMsg(addinfGrid.size());*/
				 JSONObject addinfoGrid = UIUtils.convertToJqGridTableObject(addinfGrid,request,0,0);
				 out.println(addinfoGrid);
				 CommonFunctions.debugMsg(addinfoGrid); 
//			   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colDataAddInfoData"));
		   }
		   
		   else if(action.equals("openCBM_input.prv")){
			   String pmsdId = request.getParameter("pmsdId");
			   String assmId = request.getParameter("assmId");
			   String watActvity = request.getParameter("watActvity");
			   String hdnSaveVal = request.getParameter("hdnSaveVal");
			   request.setAttribute("assmId", assmId);
			   request.setAttribute("watActvity", watActvity);
			   request.setAttribute("pmsdId", pmsdId);
			   request.setAttribute("hdnSaveVal", hdnSaveVal);
			   BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = null ;
			   if(UIUtils.isValidKeyId(pmsdId)){
			    plmTlCbmstdcadtl = plmTlStandardsService.getFillValueCMB(pmsdId);
			   
				   request.setAttribute("plmTlCbmstdcadtl", plmTlCbmstdcadtl);
				   httpSession.setAttribute("plmTlCbmstdcadtl",plmTlCbmstdcadtl);
			   }
			   UIUtils.forwardRequest(request, response, "/pages/PMStandards/CBM.jsp");   
		   }
		
			else if(action.equals("cbm_input.prv")){
			   
		   }
			else if(action.equals("cbm_getCol.prv")){
				
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelCBM"));	   
			}
			else if(action.equals("cbm_getData.prv")){
				
				String pmStandardId = request.getParameter("pmstdId");
				String zoneId = request.getParameter("zoneId");
				String dataStr = request.getParameter("dataStr");
				PrintWriter out = response.getWriter();
				 List<String []> cbmGrid  = null;
				 CommonFunctions.debugMsg(zoneId+"  dataStr   :"+dataStr);
				 JSONObject cbmGriddata = null;	
				 if(UIUtils.isValidKeyId(dataStr))
				 convertToCBMObject(dataStr);
				 else{
					 cbmGrid  =  plmTlStandardsService.getCBM(pmStandardId);
					 //CommonFunctions.debugMsg(cbmGrid.size());
					  cbmGriddata = UIUtils.convertToJqGridTableObject(cbmGrid,request,0,0);
				 }
				 List<Object> plmTlCbmstdcadtlList;
				 plmTlCbmstdcadtlList =(List<Object>) httpSession.getAttribute("plmTlCbmstdcadtlList"+zoneId); 
					//CommonFunctions.debugMsg("2df");
					
					httpSession.setAttribute("plmTlCbmstdcadtlServlet"+zoneId, plmTlCbmstdcadtlList);
					//CommonFunctions.debugMsg("3df");
				    
				 out.println(cbmGriddata);
				 CommonFunctions.debugMsg(cbmGriddata);
				
			}
		   else if(action.equals("sprpopGrid_input.prv")){
			   
		   }
			else if(action.equals("sprpopGrid_getCol.prv")){
				CommonFunctions.debugMsg("sprPmstdkeyid   :"+request.getParameter("pmstdKeyid"));
				httpSession.setAttribute("pmsdkeyid", request.getParameter("pmstdKeyid"));
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelsprData"));	   
			}
			else if(action.equals("sprpopGrid_getData.prv")){
				 CommonFunctions.debugMsg("inside get data");
				 String pmsdkeyid =(String)httpSession.getAttribute("pmsdkeyid");
				 PrintWriter out = response.getWriter();
				 List<String []> pmsprGrid  =  plmTlStandardsService.getSprPickup(pmsdkeyid);
				 CommonFunctions.debugMsg(pmsprGrid.size());
				 JSONObject pmsprGriddata = UIUtils.convertToJqGridTableObject(pmsprGrid,request,0,0);
				 out.println(pmsprGriddata);
				 CommonFunctions.debugMsg(pmsprGriddata); 
			}
		  else if(action.equals("pmAssembly_input.prv")||action.equals("MouldpmAssembly_input.prv")){
			    request.removeAttribute("filterString");
			    
			    String loginElID = (String) request.getSession().getAttribute("loginElementid");
			    String [] elmentIdArr = null;
			    int n  = 0;
			    if( loginElID != null && loginElID.indexOf("-") > 1 )
			    {   
			    	elmentIdArr = loginElID.split("-");
					n = elmentIdArr.length;
			    }
			  
			  	String factId= request.getParameter("cmbFactid");
			  	if(n > 2 && ! UIUtils.isValidKeyId(factId))
			  		factId = elmentIdArr[2];
			    String sectId= request.getParameter("cmbSectid");
			    if(n > 3 && ! UIUtils.isValidKeyId(sectId))
			    	sectId = elmentIdArr[3];
				String cellId= request.getParameter("cmbCellid");
				if(n > 4 && ! UIUtils.isValidKeyId(cellId))
					cellId = elmentIdArr[4];
				String machId= request.getParameter("cmbMchid");
				if(n > 5 && ! UIUtils.isValidKeyId(machId))
					machId = elmentIdArr[5];

				String activityType= request.getParameter("cmbjobtype");
				String chkd = request.getParameter("chkd");
			    String filterString ="cmbFactid="+factId;
			    filterString +="&cmbSectid="+sectId;
			    filterString +="&cmbCellid="+cellId;
			    filterString +="&cmbMchid="+machId;
			    filterString +="&cmbjobtype="+activityType;
			    filterString +="&chkd"+chkd;
			    
			    CommonFunctions.debugMsg("Assem " +filterString);
			  request.setAttribute("filterString", filterString);
			  UIUtils.forwardRequest(request, response, "/pages/PMStandards/grids/assemblyGrid.jsp");
		  } 
		  else if(action.equals("pmAssembly_getCol.prv")||action.equals("MouldpmAssembly_getCol.prv")){
			    PrintWriter out = response.getWriter();
			   // populateCommonFilter(request,"pmStandardCommonFilter",true);
			    //out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelAssemblygridData"));
				String firstClick = request.getParameter("firstClick");
				CommonFilter commonFilter = new CommonFilter();
				if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
					 commonFilter = (CommonFilter) httpSession.getAttribute("pmStandardCommonFilter");
				}
				if (commonFilter == null)
					commonFilter = new CommonFilter();
				commonFilter =  populateCommonFilter(request,"pmStandardCommonFilter",true);
				 if(action.equals("MouldpmAssembly_getCol.prv"))
					  commonFilter.setRelatedToMchMld("MLD");
				  else
					  commonFilter.setRelatedToMchMld("MCH");
				httpSession.removeAttribute("pmStandardCommonFilter");
				httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
				FilterValues.getCommonFilters(request, commonFilter);
				FilterValues.getPMRelated(request, commonFilter);
				
				if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
				{
					commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
					commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
					commonFilter.setMonwise("Y");
				}
			
				List<String[]> impVscomList = plmTlStandardsService.getAllgridassmData(commonFilter);
				int totalCnt =  (int) commonFilter.getTotalRecordCnt() ;
				//totalCnt = totalCnt+2;
				CommonFunctions.debugMsg("totalCnt   "+totalCnt);
				JSONObject assmblyGrid = UIUtils.convertToJqGridTableObject(impVscomList, request, 0, 0,totalCnt);
				//CommonFunctions.debugMsg("assmblyGrid..."+assmblyGrid);
				JSONObject jsonObject = getTableModel(impVscomList);
				
				httpSession.removeAttribute("PmStdColData");
				httpSession.setAttribute("PmStdColData", jsonObject);
				System.out.println("Table Model:" + jsonObject);
				out.println(jsonObject);
			  } 
		  else if(action.equals("pmAssembly_getData.prv")||action.equals("MouldpmAssembly_getData.prv")){
			  CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
			  if(action.equals("MouldpmAssembly_getData.prv"))
				  commonFilter.setRelatedToMchMld("MLD");
			  else
				  commonFilter.setRelatedToMchMld("MCH");
			  httpSession.removeAttribute("pmStandardCommonFilter");
			  httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
			  List<String []> assmblyGrid  = null;
			  PrintWriter out = response.getWriter();
			  JSONObject assmblyGriddata = null;
			  assmblyGrid  = plmTlStandardsService.getAllgridassmData(commonFilter);
			  int totalCnt =  (int) commonFilter.getTotalRecordCnt() ;
				//totalCnt = totalCnt+2;
				CommonFunctions.debugMsg("totalCnt Data   "+totalCnt);
			  assmblyGriddata = UIUtils.convertToJqGridTableObject(assmblyGrid,request,2,1,totalCnt);
			  
			  out.println(assmblyGriddata);
			  
		  } 
		  else if(action.equals("pmAssembly_getExcel.prv")||action.equals("MouldpmAssembly_getExcel.prv")){
			  CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
			  if(action.equals("MouldpmAssembly_getData.prv"))
				  commonFilter.setRelatedToMchMld("MLD");
			  else
				  commonFilter.setRelatedToMchMld("MCH");
			    httpSession = request.getSession(false);	
				 commonFilter = populateCommonFilter(request,"pmStandardCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);						
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("PmStdColData");
				String format = ExcelUtils.getFormat(request);			
				Workbook wb = plmTlStandardsService.stdExportExcel(commonFilter,tblJSONObj,format);			  
				commonFilter.setFromRow(tmpFromRow);			
				ExcelUtils.writeToResponse(response, wb, "PMStandards", format);
				
			  
		  } 
		  else if(action.equals("methodTasklist_input.prv")){
			  String machineId = request.getParameter("machineId");
			  request.setAttribute("machineId", machineId);
			  String fromWO = request.getParameter("FromWO");
			  if( fromWO == null) fromWO="";
			  request.setAttribute("fromWO", fromWO);
			  UIUtils.forwardRequest(request, response, "/pages/PMStandards/methodTaskList.jsp");
		  }
		  else if(action.equals("methodTasklist_getCol.prv")){
			   PrintWriter out = response.getWriter();
			   /*JSONObject s = JSONObject.fromString(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelMethodTaskList"));
			   String fromWO = request.getParameter("FromWO");
			   if( fromWO !=  null && UIUtils.isValidKeyId(fromWO)){
				  JSONObject cModel =(JSONObject )s.getJSONArray("colModel").get(4);
				  cModel.set("hidden", false);
				  s.getJSONArray("colModel").put(4, cModel);
			   }*/ 
				try
				{
				CommonFilter commonFilter ;
				commonFilter =  populateCommonFilter(request,"pmStandardCommonFilter",true);
				String fromWO = request.getParameter("FromWO");
				String machineId = request.getParameter("machineId");
				commonFilter.setMachineId(machineId);
				commonFilter.setWostatus(fromWO);//for fromWo to send to DAOIMPL
				List<String[]> methodTaskList = plmTlStandardsService.getMethodTaskList(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(false);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setMultiSelect(true);
				jqGridTableModel.setGridEdit(true);
				gridColModel.setHeaderNum(2);
				String [] colHeader = methodTaskList.get(1);
				String [] colHeader1 = methodTaskList.get(2);	
				String [] colHeaderCond = methodTaskList.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				//headers.add(colHeaderCond);
				headers.add(colHeader);
				headers.add(colHeader1);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "94%%");
				jsonObject.set("tableHeight", "58%%");
				System.out.println(colHeader.length+"    Colmodel  "+jsonObject);
				httpSession.setAttribute("taskListColMod",jsonObject);
				out.println(jsonObject);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			  // out.println(s);
		  }
		  else if(action.equals("methodTasklist_getData.prv")){
			   String machineId = request.getParameter("machineId");
			   CommonFilter commonFilter ;
				commonFilter =  populateCommonFilter(request,"pmStandardCommonFilter",false);
				commonFilter.setMachineId(machineId);
			   List<String[]> methodTaskList = plmTlStandardsService.getMethodTaskList(commonFilter);
			   JSONObject methodTaskListData = UIUtils.convertToJqGridTableObject(methodTaskList,request,3,0,methodTaskList.size());
			   PrintWriter out = response.getWriter();
			   out.println(methodTaskListData);
			   out.close();
		  }
		  else if(action.equals("methodTasklist_save.prv")){
			  saveMethodTaskList(request,response);
		  }
		  else if(action.equals("assmWeekly_input.prv")||action.equals("MouldassmWeekly_input.prv")){
			  request.removeAttribute("filterString_assmweek");
			  	String factId= request.getParameter("cmbFactid");
				String sectId= request.getParameter("cmbSectid");
				String cellId= request.getParameter("cmbCellid");
				String machId= request.getParameter("cmbMchid");
				String tradeId = request.getParameter("cmbTradeid");
				String activitytype = request.getParameter("cmbjobtype");
				String flid = request.getParameter("flid");
				String costcenter= request.getParameter("cmbCostcenter");
				String filterString_assmweek ="cmbFactid="+factId;
			    filterString_assmweek +="&cmbSectid="+sectId;
			    filterString_assmweek +="&cmbCellid="+cellId;
			    filterString_assmweek +="&cmbMchid="+machId;
			    filterString_assmweek +="&cmbTradeid="+tradeId;
			    filterString_assmweek +="&cmbjobtype="+activitytype;
			    
			    httpSession.setAttribute("pmMaingridCmbFactid", factId);
			    httpSession.setAttribute("pmMaingridCmbSectid", sectId);
			    httpSession.setAttribute("pmMaingridCmbCellid", cellId);
			    httpSession.setAttribute("pmMaingridCmbMchid", machId);

			    httpSession.setAttribute("pmMaingridCmbjobtype", activitytype);
			    httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
			    httpSession.setAttribute("pmMaingridFlid", flid);
			    httpSession.setAttribute("pmMaingridCmbCostcenter", costcenter);
			 
			  request.setAttribute("filterString_assmweek", filterString_assmweek);
			  UIUtils.forwardRequest(request, response, "/pages/PMStandards/grids/assmWeekly.jsp");
		 }
		  else if(action.equals("assmWeekly_getCol.prv")||action.equals("MouldassmWeekly_getCol.prv")){
			  populateCommonFilter(request,"pmStandardCommonFilter",true);
			  PrintWriter out = response.getWriter();
			  out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelAssemblyClick"));
		 }
		  else if(action.equals("assmWeekly_getData.prv")||action.equals("MouldassmWeekly_getData.prv")){
			  CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
			  if(action.equals("MouldassmWeekly_getData.prv"))
				  commonFilter.setRelatedToMchMld("MLD");
			  else
				  commonFilter.setRelatedToMchMld("MCH");
			  httpSession.removeAttribute("pmStandardCommonFilter");
			  httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
			  PrintWriter out = response.getWriter();
			  List<String []> assmWeekGrid  = null;
			  JSONObject assmWeekGriddata = null;
			  assmWeekGrid  = plmTlStandardsService.getassmgridData(commonFilter);
			  assmWeekGriddata = UIUtils.convertToJqGridTableObject(assmWeekGrid,request,0,0);
			  CommonFunctions.debugMsg(assmWeekGrid.size());
			  out.println(assmWeekGriddata);
		 }
		  else if(action.equals("pmActivity_input.prv")||action.equals("MouldpmActivity_input.prv")){
			  request.removeAttribute("filtrstr");
			    String annualPlan = request.getParameter("Plan");
			    CommonFunctions.debugMsg("annualPlan  "+annualPlan);
			    String factId= request.getParameter("cmbFactid");
				String sectId= request.getParameter("cmbSectid");
				String cellId= request.getParameter("cmbCellid");
				String machId= request.getParameter("cmbMchid");
				String assmId= request.getParameter("cmbAssmbid");
				String tradeId= request.getParameter("cmbTradeid");
				String activityType= request.getParameter("cmbjobtype");
				String flid= request.getParameter("flid");
				String costcenter= request.getParameter("cmbCostcenter");
				String filterString_act = null;
				filterString_act +="&cmbFactid="+factId;
			    filterString_act +="&cmbSectid="+sectId;
			    filterString_act +="&cmbCellid="+cellId;
			    filterString_act +="&cmbMchid="+machId;
			    filterString_act +="&cmbTradeid="+tradeId;
			    filterString_act += "&cmbjobtype="+activityType;
			    filterString_act += "&flid="+flid;
			    if(UIUtils.isValidKeyId(assmId)){
			    	filterString_act += "&cmbjobtype="+activityType;
			    	filterString_act += "&cmbAssmbid="+assmId;
			    }
			    
			    httpSession.setAttribute("pmMaingridCmbFactid", factId);
			    httpSession.setAttribute("pmMaingridCmbSectid", sectId);
			    httpSession.setAttribute("pmMaingridCmbCellid", cellId);
			    httpSession.setAttribute("pmMaingridCmbMchid", machId);
			    httpSession.setAttribute("pmMaingridCmbAssmbid", assmId);
			    httpSession.setAttribute("pmMaingridCmbjobtype", activityType);
			    httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
			    httpSession.setAttribute("pmMaingridFlid", flid);
			    httpSession.setAttribute("pmMaingridCmbCostcenter", costcenter);
			    
			  request.setAttribute("activityType", activityType);
			  request.setAttribute("filtrstr", filterString_act);
			  CommonFunctions.debugMsg("Filter String" + filterString_act);
			  UIUtils.forwardRequest(request, response, "/pages/PMStandards/grids/activityGrid.jsp");
		  }
		  else if(action.equals("pmActivity_getCol.prv")||action.equals("MouldpmActivity_getCol.prv")){
			  populateCommonFilter(request,"pmStandardCommonFilter",true);
			  PrintWriter out = response.getWriter();	  
			  out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelActivitygridData"));
		  }
		  else if(action.equals("pmActivity_getData.prv")||action.equals("MouldpmActivity_getData.prv")){
			  CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
			  if(action.equals("MouldpmActivity_getData.prv"))
				  commonFilter.setRelatedToMchMld("MLD");
			  else
				  commonFilter.setRelatedToMchMld("MCH");
			  httpSession.removeAttribute("pmStandardCommonFilter");
			  httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
			  PrintWriter out = response.getWriter();
			  List<String []> activityGrid  = null;
			  JSONObject activityGriddata = null;
			  activityGrid  = plmTlStandardsService.getAllgridData(commonFilter);
			  activityGriddata = UIUtils.convertToJqGridTableObject(activityGrid,request,0,0);
			  CommonFunctions.debugMsg(activityGrid.size());
			  out.println(activityGriddata);
		  }
		  else if(action.equals("preventive_getCol.prv")||action.equals("Mouldpreventive_getCol.prv"))
		   {
			 CommonFunctions.debugMsg("getCol");
			/*PRINTWRITER OUT = RESPONSE.GETWRITER();
		   	OUT.PRINTLN(UIUTILS.GETPROPERTYVALUE("COM.AKRANTA.TPM.RESOURCES.PMSTANDARDPROP","COLMODELGRIDDATA"));*/
			 String factId= request.getParameter("cmbFactid");
				String sectId= request.getParameter("cmbSectid");
				String cellId= request.getParameter("cmbCellid");
				String machId= request.getParameter("cmbMchid");
				String chkd = request.getParameter("chkd");
				String tradeId = request.getParameter("cmbTradeid");
				String activitytype= request.getParameter("cmbjobtype");
				httpSession.setAttribute("factId",factId );
				httpSession.setAttribute("sectId",sectId );
				httpSession.setAttribute("cellId",cellId );
				httpSession.setAttribute("machId",machId );
				httpSession.setAttribute("chkd",chkd );
				httpSession.setAttribute("tradeId",tradeId );
				httpSession.setAttribute("activitytype",activitytype);
				populateCommonFilter(request,"pmStandardCommonFilter",true);
				CommonFunctions.debugMsg("filterString   :"+factId+sectId+cellId+machId+chkd);
			 PrintWriter out = response.getWriter();
			 if(chkd.equals("chkactWise")){
				 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelActivitygridData"));
			 }
			 else if(chkd.equals("chkassmWise"))
				 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelAssemblygridData"));
			 else
				 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelAssemblyClick"));
		   }
		   else if(action.equals("preventive_getData.prv")||action.equals("Mouldpreventive_getData.prv"))
		   {
			   CommonFunctions.debugMsg("inside get data");
			   CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
			   if(action.equals("Mouldpreventive_getData.prv"))
					  commonFilter.setRelatedToMchMld("MLD");
				  else
					  commonFilter.setRelatedToMchMld("MCH");
			   httpSession.removeAttribute("pmStandardCommonFilter");
 			 	httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
			   String factId =(String)httpSession.getAttribute("factId");
			   String sectId =(String)httpSession.getAttribute("sectId");
			   String cellId =(String)httpSession.getAttribute("cellId");
			   String machId =(String)httpSession.getAttribute("machId");
			   String chkd =(String)httpSession.getAttribute("chkd");
			   String tradeId =(String)httpSession.getAttribute("tradeId");
			   String activitytype =(String)httpSession.getAttribute("activitytype");
			   
			   /*commonFilter.getFactory().setId(factId);
			   commonFilter.getSection().setId(sectId);
			   commonFilter.getCell().setId(cellId);
			   commonFilter.getMachine().setId(machId);*/
			   CommonFunctions.debugMsg("factory id "+ commonFilter.getTrade().getId());
			   List<String []> pmstdGrid  = null;
			   PrintWriter out = response.getWriter();
			   JSONObject pmstdGriddata = null;
			   if(chkd.equals("chkactWise")){
				   pmstdGrid  = plmTlStandardsService.getAllgridData(commonFilter);
				   pmstdGriddata = UIUtils.convertToJqGridTableObject(pmstdGrid,request,0,0);
			   }
			   else if(chkd.equals("chkassmWise")){
				   pmstdGrid  = plmTlStandardsService.getAllgridassmData(commonFilter);
				   pmstdGriddata = UIUtils.convertToJqGridTableObject(pmstdGrid,request,0,1);
			   }
			   else{
				   pmstdGrid  = plmTlStandardsService.getassmgridData(commonFilter);
				   pmstdGriddata = UIUtils.convertToJqGridTableObject(pmstdGrid,request,1,0);
			   }
			   CommonFunctions.debugMsg(pmstdGrid.size());
			  
			   out.println(pmstdGriddata);
			   CommonFunctions.debugMsg("pmstdGriddata  "+pmstdGriddata);
			   CommonFunctions.debugMsg("filterString   :"+factId+sectId+cellId+machId);
		   }
		//for checking plan exists
		   else if(action.equals("chkPlanExists_input.prv")){
			   PrintWriter out = response.getWriter();
			   String machineId = request.getParameter("machineId");
			   String machorasswise = "M";
			   List<String []> chkplnexists = plmTlStandardsService.getchkplnexists(machorasswise,machineId);
			   CommonFunctions.debugMsg(chkplnexists.size());
			   JSONObject chkplanExist = new JSONObject();
			   chkplanExist.put("retChkpln", chkplnexists.size());
			   System.  out.print("chkpln    "+chkplanExist);
			   out.print(chkplanExist);
		   }
		
//for sub Type
	   else if(action.equals("subType_input.prv"))
	   {
	   	//colModeltools
	   	CommonFunctions.debugMsg("subType input called");
	   }
	   else if(action.equals("subType_getCol.prv"))
	   {
		PrintWriter out = response.getWriter();
	   	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelsubType"));
	   }
	   else if(action.equals("subType_getData.prv"))
	   {
		   CommonFunctions.debugMsg("inside get data");
		   PrintWriter out = response.getWriter();
		   List<String []> pmsubtype  = plmTlStandardsService.getAllsubtype();
		   CommonFunctions.debugMsg(pmsubtype.size());
		   JSONObject pmsubtypedata = UIUtils.convertToJqGridTableObject(pmsubtype,request,0,0);
		   out.println(pmsubtypedata);
		 	CommonFunctions.debugMsg(pmsubtypedata);
	   }
//for tool method grid
	    else if(action.equals("tool_input.prv"))
	    {
	    	//colModeltools
	    	CommonFunctions.debugMsg("tools input called");
	    	CommonFunctions.debugMsg("selectedElement  ---"+request.getParameter("selectedElement"));
	    }
	    else if(action.equals("tool_getCol.prv"))
	    {
	    	//colModeltools
	    	String getValue=request.getParameter("selectedElement");
	    	httpSession.setAttribute("ToolTreevalue", getValue);
	    	String getvaluefrmTree=(String) httpSession.getAttribute("ToolTreevalue");
	    	CommonFunctions.debugMsg("getvaluefrmTree  --"+getvaluefrmTree);
	    	CommonFunctions.debugMsg("selectedElement  ---"+getValue);
	    	PrintWriter out = response.getWriter();
	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModeltools"));
	    	
	    }
	    else if(action.equals("tool_getData.prv"))
	    {
	    	try
			{	
	    		String getvaluefrmTree=(String) httpSession.getAttribute("ToolTreevalue");
		    	CommonFunctions.debugMsg("getvaluefrmTree  --"+getvaluefrmTree);
	    		CommonFunctions.debugMsg("1ss");
				List<Object> toolTreeList;
				toolTreeList =(List<Object>) httpSession.getAttribute("ToolTreevalue");
				CommonFunctions.debugMsg("2df");
				PrintWriter out = response.getWriter();
				
				httpSession.setAttribute("prvServlet", toolTreeList);
				CommonFunctions.debugMsg("3df");
			    JSONObject tooltreeData = convertToToolTreeTblObject(toolTreeList);
			    CommonFunctions.debugMsg("tooltreeData getData="+tooltreeData);
			    out.println(tooltreeData);
			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Tool Method  Exception"+e.getMessage());
			}
	    
	    }
		/*for filling tool grid in modification mode toolmod_input.jhclit*/
	    else if(action.equals("toolmod_input.prv"))
	    {
	    	
	    }
	    else if(action.equals("toolmod_getCol.prv"))
	    {
	    	String toolPmsdId = request.getParameter("toolPmsdId");
	    	CommonFunctions.debugMsg("tool "+toolPmsdId);
	    	httpSession.setAttribute("toolPmsdId", toolPmsdId);
	    	PrintWriter out = response.getWriter();
	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModeltools"));
	    }
	    else if(action.equals("toolmod_getData.prv"))
	    {
	    	String toolpmsdid = (String)httpSession.getAttribute("toolPmsdId");
	    	PrintWriter out = response.getWriter();
	    	List<String []> jhclittoolret  = plmTlStandardsService.getAlljhclittools(toolpmsdid);
			 	JSONObject jhclittooldata = UIUtils.convertToJqGridTableObject(jhclittoolret,request,0,0);
			 	out.println(jhclittooldata);
	    }	
	    else if(action.equals("tool_delete.prv")){
	    	try{
	    		String pmstdId = request.getParameter("pmstdId");
	    		String deleteSpare = plmTlStandardsService.delTool(pmstdId);
	    		String sucData = null;
	    		PrintWriter out = response.getWriter();
	    		if(deleteSpare.equals("Success")){
	    			sucData = "Tool(s) Data Removed SuccessFully";
	    		}
	    		else{
	    			sucData = "Tool(s) Cannot Be Removed"; 
	    		}
	    		 JSONObject returnData = new JSONObject();
				   returnData.put("successData",sucData);
				   out.print(returnData.toString());
	    			
	    	}catch(Exception e){
	    		CommonFunctions.debugMsg("Spares deleteData Exception"+e.getMessage());
	    	}
	    	
	    }
/*Tool tree*/

    else if( action.equals("tool_tree.prv") )
	{	 
    	CommonFunctions.debugMsg("inside tool_tree action");
    	String toolRowid = request.getParameter("rowIds");
    	CommonFunctions.debugMsg("toolRowid --"+toolRowid);
    	Enumeration<String> params = request.getParameterNames() ;
		while(params.hasMoreElements() )
		{
			CommonFunctions.debugMsg("parms  " + params.nextElement());
		}
		String parentid = request.getParameter("id");
		CommonFunctions.debugMsg( " menuID " + parentid);
		parentid  = parentid.equals("0") ? "0" :parentid;
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
   		 
        	JSONArray jSONArray = new JSONArray();
    	    if( (parentid.equals("0") )){
        		
        		jSONArray =getJstreeNodeObject("1",parentid,"TOOLS","R");
        		CommonFunctions.debugMsg("jSONArray  :"+jSONArray);
        			
        	}else{
        		BAL_GenVwToolcategory genVwToolcategory = new BAL_GenVwToolcategory();
	        	if(parentid.equals("1")){
	        		CommonFunctions.debugMsg("parentid = one");
	        		genVwToolcategory.setElementtype("C");
	        		CommonFunctions.debugMsg("parentid ");
	        	}
	        	else{
	        		CommonFunctions.debugMsg("parentid = 0");
	        		genVwToolcategory.setElementtype("T");
	        		
	        		genVwToolcategory.setParentid(parentid);
	        	}
        		//genVwToolcategory.setParentid(parentid);
	        	CommonFunctions.debugMsg("before calling query  --");
        		List <BAL_GenVwToolcategory> toolpickupList = genVwToolcategoryService.getAllTool(genVwToolcategory);
        		jSONArray  = getToolsTreeArray(toolpickupList,toolRowid);
        	}
        		
        		
            out.print(jSONArray);
    	    jSONArray=null;
            
        }catch(Exception e){
            CommonFunctions.debugMsg(e);
        }
        finally {
            out.close();
        }
	  }
    else if( action.equals("howmthd_input.prv"))
	{	
    	  UIUtils.displayRequestParamsValue(request);
			    if(httpSession != null)
			    {
			    	
			    	String pmsdkeyID = request.getParameter("pmsdkeyID");
			    	 
			    	BAL_PlmTlMethodsmst plmTlMethodsmst = new BAL_PlmTlMethodsmst();
			    	
			    	String mlmmKeyid =  request.getParameter("txtPmmmKeyid");
			    	List<BAL_PlmTlMethodsmst> plmTlMultiplemethodsmstList = (List<BAL_PlmTlMethodsmst>)httpSession.getAttribute("plmTlMultiplemethodsmstList"+pmsdkeyID);
			    	
			    	if( plmTlMultiplemethodsmstList == null)
			    	{
			    		plmTlMultiplemethodsmstList = new ArrayList<BAL_PlmTlMethodsmst>();
			    	}
			    	
			    	CommonFunctions.debugMsg("mlmmKeyid " + mlmmKeyid);
			    	if( mlmmKeyid == null || mlmmKeyid.trim().length() <= 0 || mlmmKeyid.trim().equals("null")){
			    		UIUtils.setBeanProperties(plmTlMethodsmst, request);
			    		plmTlMethodsmst.setPmmsKeyid(""+ (plmTlMultiplemethodsmstList.size()+1));
			    	}	
			    	else{
			    		plmTlMethodsmst = getPlmTlMultiplemethodsmst(plmTlMultiplemethodsmstList,mlmmKeyid);
			    		UIUtils.setBeanProperties(plmTlMethodsmst, request);
			    	}	
					    	
			    	plmTlMultiplemethodsmstList.add(plmTlMethodsmst);
			    	
			    	httpSession.setAttribute("plmTlMultiplemethodsmstList"+pmsdkeyID,plmTlMultiplemethodsmstList);
			    	CommonFunctions.debugMsg("plmTlMethodsmst keyid " + plmTlMethodsmst.getPmmsKeyid());
			    }	
			    
    	
	}
    else if(action.equals("howmthd_getCol.prv")){
    	PrintWriter out = response.getWriter();
    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelhowMethod"));
    }
    else if( action.equals("howmthd_getData.prv"))
	{
    	try
		{	
    		String prevKeyId = request.getParameter("jhclitkeyID");
    		//CommonFunctions.debugMsg("1ss");
			List<Object> plmTlMultiplemethodsmstList;
			plmTlMultiplemethodsmstList =(List<Object>) httpSession.getAttribute("plmTlMultiplemethodsmstList"+prevKeyId); 
			//CommonFunctions.debugMsg("2df");
			PrintWriter out = response.getWriter();
			
			httpSession.setAttribute("preventivemaintainceServlet"+prevKeyId, plmTlMultiplemethodsmstList);
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
    else if( action.equals("spare_input.prv"))
	{	
    	CommonFunctions.debugMsg("inside input spares");
	}
    else if(action.equals("spare_getCol.prv")){
    	CommonFunctions.debugMsg("inside get col");
    	PrintWriter out = response.getWriter();
    	out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelSpares"));
    }
    else if(action.equals("spare_getData.prv"))
	{
	   try
		{	
			PrintWriter out = response.getWriter();
			String standardId =(String)httpSession.getAttribute("pmstdKeyid");
			CommonFunctions.debugMsg("inside standardId   "+standardId);
			List<String []> sparesDtlList  = plmTlStandardsService.getSprPickup(standardId);
			JSONObject sparesDtlData = UIUtils.convertToJqGridTableObject(sparesDtlList,request,0,0);
			
			out.println(sparesDtlData);
		}
		catch(Exception e)
		{
			CommonFunctions.debugMsg("Spares getData Exception"+e.getMessage());
		} 
	}
    else if(action.equals("spare_delete.prv")){
    	try{
    		String pmstdId = request.getParameter("pmstdId");
    		String deleteSpare = plmTlStandardsService.delSpares(pmstdId);
    		String sucData = null;
    		PrintWriter out = response.getWriter();
    		if(deleteSpare.equals("Success")){
    			sucData = "Spare Data Removed SuccessFully";
    		}
    		else{
    			sucData = "Spares Cannot Be Removed"; 
    		}
    		 JSONObject returnData = new JSONObject();
			   returnData.put("successData",sucData);
			   out.print(returnData.toString());
    			
    	}catch(Exception e){
    		CommonFunctions.debugMsg("Spares deleteData Exception"+e.getMessage());
    	}
    	
    }
    else if(action.equals("openCBM_save.prv")){
    	BAL_PlmTlStandardsFormBean plmTlStandardsFormBean = new BAL_PlmTlStandardsFormBean();
    	saveCbm(request,response,plmTlStandardsFormBean);
    	
    }
    else if(action.equals("sdm_save.prv")){
    	
    	String locationid=request.getParameter("locId");
    	String factoryid=request.getParameter("factId");
    	String sectionid=request.getParameter("sectionId");
    	String machineid=request.getParameter("machId");
    	String cellid=request.getParameter("cellId");
    	String effectivedate =request.getParameter("valFromDate");
    	String frequency=request.getParameter("valfreq");
    	BAL_PlmTlStandardsFormBean plmTlStandardsFormBean = new BAL_PlmTlStandardsFormBean();
    	saveSDM(request,response,plmTlStandardsFormBean);
    	//String genCalResult = plmTlStandardsService.sdmGenrateCal(effectivedate,factoryid,sectionid,cellid,machineid,"{}",frequency,locationid);
    }
		

    // To Copy The Standards from one machine to other From  sugumar// 
    else if(action.equals("pmActivitycopy_input.prv")||action.equals("MouldpmActivitycopy_input.prv")){
		  request.removeAttribute("filtrstr");
		    String annualPlan = request.getParameter("Plan");
		    CommonFunctions.debugMsg("annualPlan  "+annualPlan);
		    String factId= request.getParameter("cmbFactid");
			String sectId= request.getParameter("cmbSectid");
			String cellId= request.getParameter("cmbCellid");
			String cellvalue=request.getParameter("cmbCellid");
			String machId= request.getParameter("cmbMchid");
			String assmId= request.getParameter("cmbAssmbid");
			String elementId= request.getParameter("cmbPmsdElementid");
			System.out.println("elementId"+elementId);
			String activitylist=request.getParameter("actlist");
			 CommonFilter commonFilter=new CommonFilter();
			 commonFilter.setCellId(cellId);
			String cellid=plmTlStandardsService.getEquipflid(commonFilter);
		
			String tradeId= request.getParameter("cmbTradeid");
			String activityType= request.getParameter("cmbjobtype");
			String flid= request.getParameter("flid");
			String costcenter= request.getParameter("cmbCostcenter");
			String filterString_act = null;
			filterString_act +="&cmbFactid="+factId;
		    filterString_act +="&cmbSectid="+sectId;
		    filterString_act +="&cmbCellid="+cellId;
		    filterString_act +="&cmbMchid="+machId;
		    filterString_act +="&cmbTradeid="+tradeId;
		    filterString_act += "&cmbjobtype="+activityType;
		    filterString_act += "&flid="+flid;
		    if(UIUtils.isValidKeyId(assmId)){
		    	filterString_act += "&cmbjobtype="+activityType;
		    	filterString_act += "&cmbAssmbid="+assmId;
		    }
		   // updatekeyid=updatekeyid.replaceAll("\"", "'");
		    System.out.println("activity list"+activitylist.replaceAll("\"", "'"));
		    httpSession.setAttribute("pmMaingridCmbFactid", factId);
		    httpSession.setAttribute("pmMaingridCmbSectid", sectId);
		    httpSession.setAttribute("pmMaingridCmbCellid", cellId);
		    httpSession.setAttribute("pmMaingridCmbMchid", machId);
		    httpSession.setAttribute("pmMaingridCmbAssmbid", assmId);
		    httpSession.setAttribute("pmMaingridCmbjobtype", activityType);
		    httpSession.setAttribute("pmMaingridCmbTradeid", tradeId);
		    httpSession.setAttribute("pmMaingridFlid", flid);
		    httpSession.setAttribute("pmMaingridFlid", flid);
		    httpSession.setAttribute("pmactivity",activitylist.replaceAll("\"", ""));
		    
		  request.setAttribute("pmactivity", activitylist.replaceAll("\"", ""));
		  request.setAttribute("activityType", activityType);
		  request.setAttribute("cellid", cellvalue);
		  request.setAttribute("elementId", elementId);
		  request.setAttribute("tradeId", tradeId);
		  request.setAttribute("machId", machId);
		  request.setAttribute("filtrstr", filterString_act);
		  CommonFunctions.debugMsg("Filter String" + filterString_act);
		  UIUtils.forwardRequest(request, response, "/pages/PMStandards/grids/activityGridCopy.jsp");
	  }
	  else if(action.equals("pmActivitycopy_getCol.prv")||action.equals("MouldpmActivitycopy_getCol.prv")){
		  populateCommonFilter(request,"pmStandardCommonFilter",true);
		  PrintWriter out = response.getWriter();	  
		  out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "colModelActivitygridDataCopy"));
	  }
	  else if(action.equals("pmActivitycopy_getData.prv")||action.equals("MouldpmActivitycopy_getData.prv")){
		  CommonFilter commonFilter  = populateCommonFilter(request,"pmStandardCommonFilter",false);
		  if(action.equals("MouldpmActivitycopy_getData.prv"))
			  commonFilter.setRelatedToMchMld("MLD");
		  else
			  commonFilter.setRelatedToMchMld("MCH");
		  httpSession.removeAttribute("pmStandardCommonFilter");
		  httpSession.setAttribute("pmStandardCommonFilter", commonFilter);
		  PrintWriter out = response.getWriter();
		  List<String []> activityGrid  = null;
		  JSONObject activityGriddata = null;
		  activityGrid  = plmTlStandardsService.getAllgridData(commonFilter);
		  activityGriddata = UIUtils.convertToJqGridTableObject(activityGrid,request,0,0);
		  CommonFunctions.debugMsg(activityGrid.size());
		  out.println(activityGriddata);
	  }

            else if( action.equals("copystdfunctionalLoc.prv"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setLocation("cmbstdLocnkeyid");
			functLocFieldNameBean.setFactory("cmbstdFactkeyid");
			functLocFieldNameBean.setSection("cmbstdSectkeyid");
			functLocFieldNameBean.setCell("cmbstdCellkeyid");
			functLocFieldNameBean.setMachine("cmbstdMachkeyid");
			functLocFieldNameBean.setLocnMandatory(false);
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
			//= (FormModes)httpSession.getAttribute("formMode");
			FormModes formModes = null ;
			
			/*if( formModes == FormModes.completion)
				formModes = FormModes.view;
			else if(formModes.equals(FormModes.modify))
				formModes = FormModes.view;
			*/
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		
		}
		//To Delete the standards By kiran //
            else if(action.equals("pmActivity_Delete.prv")){
    			String detailKeyid=request.getParameter("persistdata");
    			String cellId=request.getParameter("cellId");
    			String machineId=request.getParameter("machineId");
    			System.out.println(detailKeyid +" persistdatapersistdatapersistdatapersistdata" +machineId+"    "+cellId);
    			CommonFunctions.debugMsg("detailKeyid "+detailKeyid);
    			String deleteRecord = plmTlStandardsService.delteStandards(detailKeyid,cellId,machineId);
    			CommonFunctions.debugMsg("deleteRecord "+deleteRecord);
    			PrintWriter out = response.getWriter();
    			JSONObject successData = new JSONObject();
    			successData.put("msg",deleteRecord);
    			JSONObject returnData = new JSONObject();
    			returnData.put("successData", successData);				
    			out.print(returnData.toString());
    		}
		
	  else if(action.equals("pmActivityequipement_input.prv"))
			  {
		  
		          
		  
			  }
	  else if(action.equals("pmActivityequipement_getCol.prv"))
	  {
	     httpSession.removeAttribute("equipmentListColModel");
		  JSONObject jsonObject = new JSONObject();
		  String cellid=request.getParameter("cellid");
		  String mchid=request.getParameter("mchid");
		  
			List<String[]> EquipListGrid = null;	
			PrintWriter out=response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request,"pmStandardCommonFilter", true);	
			
		
				
			try {						
				FilterValues.getCommonFilters(request, commonFilter);
				if(cellid!=null)
				{
					commonFilter.setCellId(cellid);
				}
				
				if(mchid!=null)
				{
					commonFilter.setMachineId(mchid);
				}
				EquipListGrid =  plmTlStandardsService.getEquipmentList(commonFilter);
			} catch (Exception e) {
				CommonFunctions.debugMsg(e.getMessage());
			}	
			
		
			 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			 gridColModel.setHeaderNum(1);
			 
			 jqGridTableModel.setSortable(true);			 
			 jqGridTableModel.setTableButton(true);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);
			 jqGridTableModel.setPaginate(false);
			 
			 
			 String [] colHeaderHead = EquipListGrid.get(0);
			 String [] colHeader = EquipListGrid.get(1);
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);			
			 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);			
	   	     jsonObject.set("tableWidth", "70%%");
	     	 jsonObject.set("tableHeight", "80%%");
	         jsonObject.set("multiSelect", true);
	    	  
	     	 
	     	 //jsonObject.put("data",criAssData);
	   	     httpSession.removeAttribute("equipmentListColModel");
			
			 CommonFunctions.debugMsg("jsonObject " + jsonObject);
			
			 httpSession.setAttribute("equipmentListColModel",jsonObject);	
		
			 out.println(jsonObject);				   

	  }
	
	  else if(action.equals("pmActivityequipement_getData.prv"))
	  {
		  try {   
			    String cellid=request.getParameter("cellid");
			    String mchid=request.getParameter("mchid");
			    CommonFilter commonFilter = populateCommonFilter(request,"pmStandardCommonFilter",true);
			    FilterValues.getCommonFilters(request, commonFilter);
			    if(cellid!=null)
				{
					commonFilter.setCellId(cellid);
				}
			    if(mchid!=null)
				{
					commonFilter.setMachineId(mchid);
				}
			    List<String[]> MachineGrid = plmTlStandardsService.getEquipmentList(commonFilter);
				PrintWriter out = response.getWriter();
			//	System.out.println("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request,2,0);
				out.println(machinegrid);
			
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	


	  }
	
	  else if(action.equals("pmActivityequipement_copystandard.prv"))
	  {
	      String activityid=request.getParameter("actvity");
	      String equipmentid=request.getParameter("equilist");
	      String actvty=activityid.replaceAll("\"", "");
	      String equipmnt=equipmentid.replaceAll("\"", "");
	      String elementid=request.getParameter("eid");
	      String tradeid=request.getParameter("tid");
	      String cellid=request.getParameter("cellid");
	      JSONObject standard=new JSONObject();
	      JSONObject stnd=new JSONObject();
	      
	      
	      String actarry[]=actvty.split(",");
	      String eqmaary[]=equipmnt.split(",");
	    
	      
	      //List<String> eqlist = new ArrayList<String>(Arrays.asList(equipmnt.split(",")));
	     List<String> eqlist = new ArrayList<String>();
	     List<String> actlst = new ArrayList<String>();
	     for(String actlist:actarry)
	     {
	    	 actlst.add(actlist);
	     }
	     
	     for(String eqmlist:eqmaary)
	     {
	    	 eqlist.add(eqmlist);
	     }
	    // List<String> actlst = new ArrayList<String>(Arrays.asList(actvty.split(",")));
	   
	      System.out.println("eqlist"+eqlist);
	      System.out.println("actlst"+actlst);
	      try{
	      List<String[]> stndrds= plmTlStandardsService.copyStandards(eqlist,actlst,elementid,tradeid);
	      stnd.set("result", "success");
	      }
	      catch(Exception e){
	    	 stnd.set("result", "error");
	      }
	    
	      
	      standard.set("stnd",stnd);
	      response.getWriter().println(standard);
	      
	  } 
	}
    //To Copy The Standards from one machine to other By Kiran// 
    
private void saveMethodTaskList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	CommonFunctions.debugMsg("inside methodTasklist");
	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	try{
			if( httpSession != null && user != null)
			{	
				String getMethodTaskList = request.getParameter("getMethodTaskList");  
				String MachineId = request.getParameter("MachineId");
				BAL_PlmTlMethodtasklist newPlmTlMethodtasklist = new BAL_PlmTlMethodtasklist();
				BAL_PlmTlMethodtasklist existPlmTlMethodtasklist = new BAL_PlmTlMethodtasklist();
				/***Save permitlink grid ****/
				List<BAL_PlmTlMethodtasklist> plmTlMethodtasklist  = null;
				JSONArray plmMthdTaskJson = null;
				if(UIUtils.isValidKeyId(getMethodTaskList)){
					CommonFunctions.debugMsg("in if mk");
					plmMthdTaskJson = JSONArray.fromString(getMethodTaskList);
					plmTlMethodtasklist=(List<BAL_PlmTlMethodtasklist>)UIUtils.convertJSONArrToList(newPlmTlMethodtasklist, plmMthdTaskJson);
				if(plmTlMethodtasklist!= null){
					CommonFunctions.debugMsg("List Size  "+plmTlMethodtasklist.size());
					newPlmTlMethodtasklist.setPlmTlMethodtasklist(plmTlMethodtasklist);
					newPlmTlMethodtasklist.setMtskMachineid(MachineId);
					//existPlmTlStandards = (PlmTlStansetPermitlinkDetaildards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
					existPlmTlMethodtasklist =	plmTlStandardsService.createMethodTask(newPlmTlMethodtasklist);
					}
				}
				/**END**/
			}
	}catch(Exception e){
		
	 }
	}

private void removeMultiplePmsd(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletOutputStream out = response.getOutputStream();
    try {
        String keyid = request.getParameter("keyid");
        if (!UIUtils.isValidKeyId(keyid)) {
            JSONObject err = new JSONObject();
            err.put("tpmException", "Invalid Key ID");
            out.print(err.toString());
            return;
        }
        BAL_PlmTlStandards bean = new BAL_PlmTlStandards();
        bean.setPmsdKeyid(keyid);
        plmTlStandardsService.delete(bean);

        JSONObject result = new JSONObject();
        result.put("formClear", true);
        result.put("tpmException", "Deleted Successfully");
        out.print(result.toString());

    } catch (Exception e) {
        CommonFunctions.debugMsg("removeMultiplePmsd error: " + e.getMessage());
        JSONObject err = new JSONObject();
        err.put("tpmException", "Delete Failed: " + e.getMessage());
        out.print(err.toString());
    }
}

private void saveSDM(HttpServletRequest request,
			HttpServletResponse response,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws IOException {
		// TODO Auto-generated method stub
	 CommonFunctions.debugMsg("inside saveSDM");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
				if( httpSession != null && user != null)
				{	
					String locationid=request.getParameter("locId");
					String factoryid=request.getParameter("factId");
					String sectionid=request.getParameter("sectionId");
					String machineid=request.getParameter("machId");
					String cellid=request.getParameter("cellId");
					String fromdate =request.getParameter("valFromDate");
					String todate =request.getParameter("valToDate");
					String frequency=request.getParameter("valfreq");
					BAL_PlmTlShutdowncal newPlmTlShutdowncal = new BAL_PlmTlShutdowncal();
					BAL_PlmTlShutdowncal existPlmTlShutdowncal = (BAL_PlmTlShutdowncal)httpSession.getAttribute("plmTlShutdowncal");
					newPlmTlShutdowncal.setSdclCreatedby(user.getUsrm_ccno());
					newPlmTlShutdowncal.setSdclCellid(cellid);
					newPlmTlShutdowncal.setSdclFactoryid(factoryid);
					newPlmTlShutdowncal.setSdclFrequnit(frequency);
					newPlmTlShutdowncal.setSdclFromdate(fromdate);
					newPlmTlShutdowncal.setSdclTilldate(todate);
					newPlmTlShutdowncal.setSdclLocationid(locationid);
					newPlmTlShutdowncal.setSdclSectionid(sectionid);
					newPlmTlShutdowncal.setSdclMachineid(machineid);
					existPlmTlShutdowncal =	plmTlStandardsService.createSDM(newPlmTlShutdowncal,existPlmTlShutdowncal,plmTlStandardsFormBean);
					JSONObject persistentData = new JSONObject(); 
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					 JSONObject forwardData = new JSONObject();
					CommonFunctions.debugMsg("jjasdjkf  :"+existPlmTlShutdowncal.getSdclKeyid());
					String sucessmsg ;
					//successData.put("err","err");
					if(UIUtils.isValidKeyId(existPlmTlShutdowncal.getSdclKeyid()))
						sucessmsg = "Data Saved Successfully";
					else
						sucessmsg = "Data Not Saved";
					successData.put("msg",sucessmsg );
					
					returnData.put("formClear",false);	
					returnData.put("successData",successData);
					out.print(returnData.toString());
					//out.print()	
					CommonFunctions.debugMsg("end of CBM save");
				}
		   }
		catch(Exception e){
			
		}
	}

private void saveCbm(HttpServletRequest request,
			HttpServletResponse response,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws IOException {
		// TODO Auto-generated method stub
	 CommonFunctions.debugMsg("inside savePmsd");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
				if( httpSession != null && user != null)
				{	
				  BAL_PlmTlCbmstdcadtl existPlmTlCbmstdcadtl = (BAL_PlmTlCbmstdcadtl)httpSession.getAttribute("plmTlCbmstdcadtl"); 
				  BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl();
				  String standardId = request.getParameter("pmstdId");
				  String zoneid = request.getParameter("zoneid");
				  newPlmTlCbmstdcadtl.setCmdtCreatedby(user.getUsrm_ccno());
				  newPlmTlCbmstdcadtl.setCmdtPmstandardid(standardId);
				///  CommonFunctions.debugMsg("Zone ID   " +zoneid.substring(0, 3));
				//  if(zoneid.substring(0, 3).equals("PCC")){
					  newPlmTlCbmstdcadtl.setCmdtKeyid(existPlmTlCbmstdcadtl.getCmdtKeyid());
					  newPlmTlCbmstdcadtl.setCmdtZoneid(existPlmTlCbmstdcadtl.getCmdtZoneid());
				 // }
				 // else
					 // newPlmTlCbmstdcadtl.setCmdtZoneid(zoneid);
				  newPlmTlCbmstdcadtl.setCmdtZonecolor("Green");
		    	  CommonFunctions.debugMsg("before set CBM");
		    	  newPlmTlCbmstdcadtl =(BAL_PlmTlCbmstdcadtl)UIUtils.setBeanProperties((Object)newPlmTlCbmstdcadtl,request);
					CommonFunctions.debugMsg("after set CBM");
				  String sucessmsg;
				  if( !UIUtils.isValidKeyId(existPlmTlCbmstdcadtl.getCmdtKeyid())  )
					{	
						CommonFunctions.debugMsg("key id is not available");
						CommonFunctions.debugMsg("SECTION ID   :"+newPlmTlCbmstdcadtl.getCmdtInspectionid());
						existPlmTlCbmstdcadtl =	plmTlStandardsService.createCBM(newPlmTlCbmstdcadtl,existPlmTlCbmstdcadtl,plmTlStandardsFormBean);
						sucessmsg = "Data Saved Successfully";
					}	
					else{
						CommonFunctions.debugMsg("key id is available" + newPlmTlCbmstdcadtl.getCmdtKeyid() );
						existPlmTlCbmstdcadtl = plmTlStandardsService.updateCBM(newPlmTlCbmstdcadtl,existPlmTlCbmstdcadtl,plmTlStandardsFormBean);
						sucessmsg = "Data Updated Successfully";
					}	
				  httpSession.setAttribute("plmTlCbmstdcadtl",existPlmTlCbmstdcadtl);
					JSONObject persistentData = new JSONObject(); 
					JSONObject returnData = new JSONObject();
					JSONObject successData = new JSONObject();
					persistentData.put("Cmdtkeyid  :",existPlmTlCbmstdcadtl.getCmdtKeyid());
					JSONObject forwardData = new JSONObject();
					CommonFunctions.debugMsg("jjasdjkf  :"+existPlmTlCbmstdcadtl.getCmdtKeyid());
					//successData.put("err","err");
					successData.put("msg",sucessmsg );
					
					returnData.put("formClear",false);	
					returnData.put("successData",successData);
					out.print(returnData.toString());
					//out.print()	
					CommonFunctions.debugMsg("end of CBM save");
				}
		}catch(ValidationExceptions e)
		{
			
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "PmstandardException");
			//errMessage.put("fromMode",plmTlStandardsFormBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}catch(Exception e)
		{
			
			CommonFunctions.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			JSONObject successData = new JSONObject();
			
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			
		}
	}

	//END
//save form function
    private void savePmsd(HttpServletRequest request, HttpServletResponse response,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws IOException, ValidationExceptions{
		 CommonFunctions.debugMsg("inside savePmsd");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try{
				if( httpSession != null && user != null)
					{	
						BAL_PlmTlStandards existPlmTlStandards = (BAL_PlmTlStandards)httpSession.getAttribute("plmTlStandards"); 
						BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
						BAL_PlmTlMethodsmst newPlmTlMethodsmst = new BAL_PlmTlMethodsmst();
			    		BAL_PlmTlToolsdtl newPlmTlToolsdtl = new BAL_PlmTlToolsdtl();
			    		BAL_PlmTlPmsftpermitlink newPlmTlPmsftpermitlink = new BAL_PlmTlPmsftpermitlink();
			    		BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl();
			    		newPlmTlStandards.setPmsdCreatedby(user.getUsrm_ccno());
			    		CommonFunctions.debugMsg("before set");
			    		newPlmTlStandards =(BAL_PlmTlStandards)UIUtils.setBeanProperties((Object)newPlmTlStandards,request);
						CommonFunctions.debugMsg("after set");
						newPlmTlMethodsmst =(BAL_PlmTlMethodsmst)UIUtils.setBeanProperties((Object)newPlmTlMethodsmst, request);
						newPlmTlToolsdtl =(BAL_PlmTlToolsdtl)UIUtils.setBeanProperties((Object)newPlmTlToolsdtl, request);
						newPlmTlPmsftpermitlink = (BAL_PlmTlPmsftpermitlink)UIUtils.setBeanProperties((Object)newPlmTlPmsftpermitlink, request);
						//newbdmTlYycountermeasurelink= (BdmTlYycountermeasurelink)UIUtils.setBeanProperties((Object)newbdmTlYycountermeasurelink, request);
						/*for tool data save and multi method data save got from before sumbit function*/
						String mulitMethodStr =  request.getParameter("multiplemethods");
						String toolsGridStr   =  request.getParameter("toolsGrid");
						String typeOfWorkStr  =  request.getParameter("typeOfWork");
						String cmbPmsdPhenomenaid 		 =  request.getParameter("cmbPmsdPhenomenaid") ;
						String cmbPmsdCauseid      		 =  request.getParameter("cmbPmsdCauseid");
						String txtPmmsWorkpermitrequired =  request.getParameter("txtPmmsWorkpermitrequired");
						String txtPmsdCorrectiveaction   =  request.getParameter("txtPmsdCorrectiveaction");
						String txtPmsdResultifnotdone    =  request.getParameter("txtPmsdResultifnotdone");
						String docId = (String)httpSession.getAttribute("dockKey");
						String bdmod = (String)httpSession.getAttribute("bdmmode");
						String yyId = (String)httpSession.getAttribute("yyId");
						String cbmData = request.getParameter("cbmData");
						if(UIUtils.isValidKeyId(yyId)){
							BdmTlYycountermeasurelink newbdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
							newbdmTlYycountermeasurelink.setYycmYyid(yyId);
							newbdmTlYycountermeasurelink.setYycmRefdoctype("PMC");
							CommonFunctions.debugMsg("countrerMeasyere    "+newbdmTlYycountermeasurelink.getYycmYyid());
							newPlmTlStandards.setCountermeasureLink(newbdmTlYycountermeasurelink);
						}
						
						//newCliTlStandards.setClisAssemblyid(AssemblyId);//machine area id
						if(UIUtils.isValidKeyId(bdmod))
							newPlmTlStandards.setPmsdRefdoctype("BD");
						if(UIUtils.isValidKeyId(docId))
							plmTlStandardsFormBean.setBdmDockKey(docId);
						if(UIUtils.isValidKeyId(cmbPmsdPhenomenaid))
						newPlmTlStandards.setPmsdPhenomenaid(cmbPmsdPhenomenaid);
						if(UIUtils.isValidKeyId(cmbPmsdCauseid))
						newPlmTlStandards.setPmsdCauseid(cmbPmsdCauseid);
						if(UIUtils.isValidKeyId(txtPmsdCorrectiveaction))
						newPlmTlStandards.setPmsdCorrectiveaction(txtPmsdCorrectiveaction);
						if(UIUtils.isValidKeyId(txtPmsdResultifnotdone))
						newPlmTlStandards.setPmsdResultifnotdone(txtPmsdResultifnotdone);
						if(UIUtils.isValidKeyId(txtPmmsWorkpermitrequired))
						newPlmTlMethodsmst.setPmmsWorkpermitrequired(txtPmmsWorkpermitrequired);
						CommonFunctions.debugMsg(" typeOfWorkStr " + typeOfWorkStr);
						/***Save permitlink grid ****/
						List<BAL_PlmTlPmsftpermitlink> plmTlPmsftpermitlinkList  = null;
						JSONArray permitLinkjson = null;
						if(UIUtils.isValidKeyId(typeOfWorkStr)){
							CommonFunctions.debugMsg("in if mk");
							permitLinkjson = JSONArray.fromString(typeOfWorkStr);
							plmTlPmsftpermitlinkList=(List<BAL_PlmTlPmsftpermitlink>)UIUtils.convertJSONArrToList(newPlmTlPmsftpermitlink, permitLinkjson);
						if(plmTlPmsftpermitlinkList!= null){
							newPlmTlStandards.setPermitlinkDetail(plmTlPmsftpermitlinkList);
							//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
							}
						}
						
						/*if(request.getParameter("hdnMultiresp")== null || request.getParameter("hdnMultiresp").equals(""))
			    		{
				    		//CommonFunctions.debugMsg("Down Time Break Up Grid : "+request.getParameter("DwnTmeBrkupGrid"));
			    		}
			    		else
			    		{
			    			
				    		String multiresp = request.getParameter("hdnMultiresp");
				    		CommonFunctions.debugMsg("Down Time Break Up Grid =  X"+multiresp);
					    	JSONArray jsonArray = JSONArray.fromString(multiresp);		    	
					    	PlmTlMultipleResp plmTlMultipleResp = new PlmTlMultipleResp();
					    	List<PlmTlMultipleResp> MultiResponse = (List<PlmTlMultipleResp>) UIUtils.convertJSONArrToList(plmTlMultipleResp, jsonArray);
					    	CommonFunctions.debugMsg("Down Time Break Up Grid = "+MultiResponse.size());
					    	if( plmTlMultipleResp != null)
					    		newPlmTlStandards.setplmTlMultipleResp(MultiResponse);
			    		}*/
				    	 
						
						/**END**/
						/***Save countermeasure ****/
						//List<BdmTlYycountermeasurelink> counterMeasureList =  newPlmTlStandards.getCountermeasureLink();
						///CommonFunctions.debugMsg("cm size   :"+counterMeasureList.size());
						/*if(counterMeasureList != null)
							newPlmTlStandards.setCountermeasureLink(counterMeasureList);*/
						
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
						/***Save tool grid ****/
						List<BAL_PlmTlToolsdtl> toolGridList = null;
						JSONArray toolGridjson = null;
						if(UIUtils.isValidKeyId(toolsGridStr)){
							//CommonFunctions.debugMsg(" mulitMethodStr " + mulitMethodStr);
							CommonFunctions.debugMsg(" toolsGridStr " + toolsGridStr);
							toolGridjson = JSONArray.fromString(toolsGridStr);
							toolGridList=(List<BAL_PlmTlToolsdtl>)UIUtils.convertJSONArrToList(newPlmTlToolsdtl, toolGridjson);
						if(toolGridList!= null){
							newPlmTlStandards.setToolsDetail(toolGridList);
							//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
							}
						}
						/***end of save tool**/
						/***Save multiMethod ****/
						List<BAL_PlmTlMethodsmst> mulitMethodList = null;
						JSONArray multiMethodJson = null;
						if(UIUtils.isValidKeyId(mulitMethodStr)){
							CommonFunctions.debugMsg(" mulitMethodStr " + mulitMethodStr);
							multiMethodJson = JSONArray.fromString(mulitMethodStr);
							mulitMethodList = (List<BAL_PlmTlMethodsmst>)UIUtils.convertJSONArrToList(newPlmTlMethodsmst,multiMethodJson);
						if(mulitMethodList!= null){
							newPlmTlStandards.setMethodDetail(mulitMethodList);
						  }
						}
						existPlmTlStandards = (BAL_PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
						/***end of save MultiMethod**/
						
						/***Save CBM grid ****/
						List<BAL_PlmTlCbmstdcadtl> listCBM = null;
						JSONArray Cbmjson = null;
						if(UIUtils.isValidKeyId(cbmData)){
							//CommonFunctions.debugMsg(" mulitMethodStr " + mulitMethodStr);
							CommonFunctions.debugMsg(" cbmData " + cbmData);
							toolGridjson = JSONArray.fromString(cbmData);
							listCBM=(List<BAL_PlmTlCbmstdcadtl>)UIUtils.convertJSONArrToList(newPlmTlCbmstdcadtl, toolGridjson);
						if(listCBM!= null){
							newPlmTlStandards.setCbmData(listCBM);
							//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
							}
						}
						/***end of save tool**/
						
						
						 String pmsdKeyId = (String)httpSession.getAttribute("pmsdKeyID");
						 newPlmTlStandards.setPmsdKeyid(pmsdKeyId);
						 CommonFunctions.debugMsg(pmsdKeyId+"##################"+newPlmTlStandards.getPmsdKeyid());
						plmTlStandardsFormBean =(BAL_PlmTlStandardsFormBean) UIUtils.setBeanProperties((Object)plmTlStandardsFormBean,request);
						String elementId = newPlmTlStandards.getPmsdFactoryid()+"-"+newPlmTlStandards.getPmsdSectionid()
						+"-"+newPlmTlStandards.getPmsdCellid() + "-" +newPlmTlStandards.getPmsdMachineid() +
						"-"+newPlmTlStandards.getPmsdAssemblyid();
						
						String sucessmsg;
						if( !UIUtils.isValidKeyId(newPlmTlStandards.getPmsdKeyid())  )
						{	
							CommonFunctions.debugMsg("key id is not available");
							CommonFunctions.debugMsg("SECTION ID   :"+newPlmTlStandards.getPmsdSectionid());
							existPlmTlStandards =	plmTlStandardsService.create(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean);
							sucessmsg = "Data Saved Successfully";
						}	
						else{
							CommonFunctions.debugMsg("key id is available" + newPlmTlStandards.getPmsdKeyid() );
							existPlmTlStandards = plmTlStandardsService.update(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean);
							sucessmsg = "Data Updated Successfully";
						}
						
						httpSession.setAttribute(existPlmTlStandards.getPmsdKeyid(), existPlmTlStandards);
						httpSession.setAttribute("PlmTlStandards", existPlmTlStandards);
						String formBeanIdentifier = "plmTlStandardFormBean"+plmTlStandardsFormBean.getFormActionMode();
						httpSession.setAttribute(formBeanIdentifier,plmTlStandardsFormBean);
						
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						JSONObject keyData = new JSONObject();
						JSONObject planData = new JSONObject();
						String redirectSpare = (String)httpSession.getAttribute("sprSave");
						//CommonFunctions.debugMsg("beforeredirectSpare     :"+redirectSpare );
						/*for navigating to spares page in jsp*/
						if(UIUtils.isValidKeyId(existPlmTlStandards.getPmsdKeyid() )){
							keyData.put("afterSveKey",existPlmTlStandards.getPmsdKeyid());
							keyData.put("redirectSpare",redirectSpare);
						}
						if(  "SpaRes".equals(redirectSpare)  )
						   returnData.put("displyMsg",false);
						if(existPlmTlStandards.getPmsdPlanconfigstatus().equals("N"))
						   planData.put("msg","No Plan Exists");
						 
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("pmstdKeyid",existPlmTlStandards.getPmsdKeyid());
					//	persistentData.put("pmsdKeyid", existPlmTlUnscheduledactmst.getPlmTlStandardsDetail().getPmsdKeyid());
						persistentData.put("formBean", formBeanIdentifier);
						JSONObject forwardData = new JSONObject();

						forwardData.put("punsKeyid",existPlmTlStandards.getPmsdKeyid() );
					//	forwardData.put("pmsdKeyid",existPlmTlUnscheduledactmst.getPlmTlStandardsDetail().getPmsdKeyid());
						forwardData.put("elementId",elementId);
						//successData.put("err","err");
						returnData.put("formClear",false);	
						successData.put("msg",sucessmsg );
						
						//successData.put("afterSveKey",existPlmTlStandards.getPmsdKeyid());
						//successData.put("redirectSpare",redirectSpare);
						String modeReturn =(String)httpSession.getAttribute("bdmmode");
						successData.put("successData", modeReturn);
						
						if(UIUtils.isValidKeyId(modeReturn )){
							CommonFunctions.debugMsg("modeReturn  :"+modeReturn);
							returnData.put("formClear",false);	
							returnData.put("successData",successData);
							httpSession.removeAttribute("bdmmode");
						}
						//CommonFunctions.debugMsg("planData  :"+planData.get("msg"));
						if(existPlmTlStandards.getPmsdPlanconfigstatus().equals("N"))
						returnData.put("successData",planData);
						else{
						returnData.put("successData",successData);
						}
						
						returnData.put("keyData",keyData);
						returnData.put("forwardData",forwardData);
						returnData.put("persistentData", persistentData);
						//planExistData.put("planData", planData);
						
						out.print(returnData.toString());
						//out.print()	
						CommonFunctions.debugMsg("end of save");
					}
		
			}
			catch(ValidationExceptions e)
			{
				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "PmstandardException");
				//errMessage.put("fromMode",plmTlStandardsFormBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(Exception e)
			{
				
				CommonFunctions.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				JSONObject successData = new JSONObject();
				if(e.getMessage()== "No Plan Exists"){
			  	   successData.put("PlnExistException", e.getMessage());
			  	 err.put("PlnExistException",successData);
			  	out.print(err.toString());
				}
				else{
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
			}
   	
}
    
    //mano
//    private void saveMultiplePmsd(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ValidationExceptions {
//
//        CommonFunctions.debugMsg("inside saveMultiplePmsd");
//        HttpSession httpSession = request.getSession(false);
//        ServletOutputStream out = response.getOutputStream();
//        AdmTlUsermst user = UIUtils.getLoginUser(request);
//
//        JSONObject returnData = new JSONObject();
//        JSONArray savedRows = new JSONArray();
//
//        try {
//            if (httpSession != null && user != null) {
//
//                String pmsdStdDetailsStr = request.getParameter("pmsdStdDetails");
//                String flId       = request.getParameter("flId");
//                String sectionId  = request.getParameter("sectionId");
//                String cellId     = request.getParameter("cellId");      // mano - added, was missing
//                String factoryId  = request.getParameter("factoryId");
//                String elementId  = request.getParameter("elementId");   // mano - added, was missing
//                String locationId = request.getParameter("locationId");
//                System.out.println(factoryId+"factoryid");
//                System.out.println(cellId+"cellid");
//                
//                // mano - added, was missing
//
//                if (!UIUtils.isValidKeyId(pmsdStdDetailsStr)) {
//                    JSONObject err = new JSONObject();
//                    err.put("tpmException", "No rows to save");
//                    out.print(err.toString());
//                    return;
//                }
//
//                JSONArray gridRows = JSONArray.fromString(pmsdStdDetailsStr);
//                BAL_PlmTlStandardsFormBean formBean = new BAL_PlmTlStandardsFormBean();
//
//                List<BAL_PlmTlStandards> createList = new ArrayList<>();
//                List<String> createRowIds = new ArrayList<>();
//
//                List<BAL_PlmTlStandards> updateNewList = new ArrayList<>();
//                List<BAL_PlmTlStandards> updateExistList = new ArrayList<>();
//                List<String> updateRowIds = new ArrayList<>();
//
//                for (int i = 0; i < gridRows.length(); i++) {
//                    JSONObject row = gridRows.getJSONObject(i);
//
//                    // confirm actual key name via console.log(gridval) before submit
//                    String rowId = row.optString("id", row.optString("rowid", ""));
//                    String existingKeyid = trimOrEmpty(row.optString("hdnPmsdKeyid"));
//
//                    BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
//                    newPlmTlStandards.setPmsdCreatedby(user.getUsrm_ccno());
//
//                    newPlmTlStandards.setPmsdFlid(flId);
//                    newPlmTlStandards.setPmsdSectionid(sectionId);
//                    newPlmTlStandards.setPmsdCellid(cellId);           // mano - added, was missing
//                    newPlmTlStandards.setPmsdFactoryid(factoryId); 
//                    newPlmTlStandards.setPmsdElementid(elementId);     // mano - added
//                    newPlmTlStandards.setPmsdLocationid(locationId);
//                    newPlmTlStandards.setPmsdMachineid(getVal(row, "cmbMulPmsdMachineid", "hdnMulPmsdMachineid"));
//                    newPlmTlStandards.setPmsdAssemblyid(getVal(row, "cmbMulPmsdAssemblyid", "hdnMulPmsdAssemblyid"));
//                    newPlmTlStandards.setPmsdSubassemblyid(getVal(row, "cmbMulPmsdSubassemblyid", "hdnMulPmsdSubassemblyid"));
//                    newPlmTlStandards.setPmsdSource(trimOrEmpty(row.optString("cmbMulPmsdSource")));
//                    newPlmTlStandards.setPmsdSupplierid(getVal(row, "cmbMulPmsdSupplierid", "hdnMulPmsdSupplierid"));
//                    newPlmTlStandards.setPmsdTradeid(trimOrEmpty(row.optString("hdnMulPmsdTradeid")));
//                    newPlmTlStandards.setPmsdActivitytype(trimOrEmpty(row.optString("hdnMulPmsdActivitytype")));
//                    newPlmTlStandards.setPmsdMachinecondition(getVal(row, "cmbMulPmsdMachinecondition", "hdnMulPmsdMachinecondition"));
//                    newPlmTlStandards.setPmsdActivitysubtype(trimOrEmpty(row.optString("txtMulPmsdActivitysub")));
//					/*
//					 * newPlmTlStandards.setPmsdFrequencyunit(getVal(row, "cmbMulPmsdFrequencyunit",
//					 * "hdnMulPmsdFrequencyunit"));
//					 * newPlmTlStandards.setPmsdFrequency(trimOrEmpty(row.optString(
//					 * "txtMulPmsdFrequency")));
//					 * newPlmTlStandards.setPmsdDuration(trimOrEmpty(row.optString(
//					 * "txtMulPmsdDuration")));
//					 * newPlmTlStandards.setPmsdLocation(trimOrEmpty(row.optString(
//					 * "txtMulPmsdLocation")));
//					 */
//                    
//                    newPlmTlStandards.setPmsdFrequencyunit(getVal(row, "cmbMulPmsdFrequencyunit", "hdnMulPmsdFrequencyunit"));
//
//                    // --- validate Frequency (numeric, required) ---
//                    String freqStr = trimOrEmpty(row.optString("txtMulPmsdFrequency"));
//                    if (freqStr.isEmpty() || !freqStr.matches("\\d+(\\.\\d+)?")) {
//                        JSONObject err = new JSONObject();
//                        err.put("tpmException", "Row " + (i + 1) + ": Frequency must be a valid number");
//                        out.print(err.toString());
//                        return;
//                    }
//                    newPlmTlStandards.setPmsdFrequency(freqStr);
//
//                    // --- validate Duration (numeric, optional -> defaults to 0) ---
//                    String durStr = trimOrEmpty(row.optString("txtMulPmsdDuration"));
//                    if (!durStr.isEmpty() && !durStr.matches("\\d+(\\.\\d+)?")) {
//                        JSONObject err = new JSONObject();
//                        err.put("tpmException", "Row " + (i + 1) + ": Duration must be a valid number");
//                        out.print(err.toString());
//                        return;
//                    }
//                    if (durStr.isEmpty()) {
//                        durStr = "0";
//                    }
//                    newPlmTlStandards.setPmsdDuration(durStr);
//
//                    newPlmTlStandards.setPmsdLocation(trimOrEmpty(row.optString("txtMulPmsdLocation")));
//                    
//                    
//                    newPlmTlStandards.setPmsdActivity(trimOrEmpty(row.optString("txtMulPmsdActivity")));
//                    newPlmTlStandards.setPmsdHowmethod(trimOrEmpty(row.optString("txtMulPmsdHowmethod")));
//                    newPlmTlStandards.setPmsdStandard(trimOrEmpty(row.optString("txtMulPmsdStandard")));
//                    newPlmTlStandards.setPmsdIssparesreq(trimOrEmpty(row.optString("chkMulPmsdIssparesreq")));
//                    newPlmTlStandards.setPmsdIstoolsreq(trimOrEmpty(row.optString("chkMulPmsdIstoolsreq")));
//                    newPlmTlStandards.setPmsdPreparedbyid(getVal(row, "cmbMulPmsdPreparedbyid", "hdnMulPmsdPreparedbyid"));
//
//                    if (!UIUtils.isValidKeyId(existingKeyid)) {
//                        createList.add(newPlmTlStandards);
//                        createRowIds.add(rowId);
//                    } else {
//                        newPlmTlStandards.setPmsdKeyid(existingKeyid);
//                        BAL_PlmTlStandards existPlmTlStandards =
//                                (BAL_PlmTlStandards) httpSession.getAttribute("plmTlStandards" + existingKeyid);
//
//                        updateNewList.add(newPlmTlStandards);
//                        updateExistList.add(existPlmTlStandards);
//                        updateRowIds.add(rowId);
//                    }
//                }
//
//                // ---- run creates ----
//                if (!createList.isEmpty()) {
//                    List<BAL_PlmTlStandards> createdList = plmTlStandardsService.createMultiple(createList, formBean);
//                    for (int i = 0; i < createdList.size(); i++) {
//                        BAL_PlmTlStandards saved = createdList.get(i);
//                        httpSession.setAttribute(saved.getPmsdKeyid(), saved);
//
//                        JSONObject savedRow = new JSONObject();
//                        savedRow.put("rowid", createRowIds.get(i));
//                        savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
//                        savedRows.put((Object) savedRow);
//                    }
//                }
//
//                // ---- run updates ----
//                if (!updateNewList.isEmpty()) {
//                    List<BAL_PlmTlStandards> updatedList =
//                            plmTlStandardsService.updateMultiple(updateNewList, updateExistList, formBean);
//                    for (int i = 0; i < updatedList.size(); i++) {
//                        BAL_PlmTlStandards saved = updatedList.get(i);
//                        httpSession.setAttribute(saved.getPmsdKeyid(), saved);
//
//                        JSONObject savedRow = new JSONObject();
//                        savedRow.put("rowid", updateRowIds.get(i));
//                        savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
//                        savedRows.put((Object) savedRow);
//                    }
//                }
//
//                returnData.put("savedRows", savedRows);
//                out.print(returnData.toString());
//                CommonFunctions.debugMsg("end of saveMultiplePmsd");
//            }
//        } catch (ValidationExceptions e) {
//            JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "PmstandardException");
//            out.print(errMessage.toString());
//        } catch (Exception e) {
//            CommonFunctions.debugMsg("saveMultiplePmsd error: " + e.getMessage());
//            e.printStackTrace();
//            JSONObject err = new JSONObject();
//            err.put("tpmException", "Data Not Saved");
//            out.print(err.toString());
//        }
//    }
	/*
	 * private void saveMultiplePmsd(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException, ValidationExceptions {
	 * 
	 * CommonFunctions.debugMsg("inside saveMultiplePmsd"); HttpSession httpSession
	 * = request.getSession(false); ServletOutputStream out =
	 * response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request);
	 * 
	 * JSONObject returnData = new JSONObject(); JSONArray savedRows = new
	 * JSONArray();
	 * 
	 * try { if (httpSession != null && user != null) {
	 * 
	 * String pmsdStdDetailsStr = request.getParameter("pmsdStdDetails"); String
	 * flId = request.getParameter("flId"); String sectionId =
	 * request.getParameter("sectionId"); String cellId =
	 * request.getParameter("cellId"); String factoryId =
	 * request.getParameter("factoryId"); String elementId =
	 * request.getParameter("elementId"); String locationId =
	 * request.getParameter("locationId");
	 * System.out.println(factoryId+"factoryid");
	 * System.out.println(cellId+"cellid");
	 * 
	 * if (!UIUtils.isValidKeyId(pmsdStdDetailsStr)) { JSONObject err = new
	 * JSONObject(); err.put("tpmException", "No rows to save");
	 * out.print(err.toString()); return; }
	 * 
	 * JSONArray gridRows = JSONArray.fromString(pmsdStdDetailsStr);
	 * BAL_PlmTlStandardsFormBean formBean = new BAL_PlmTlStandardsFormBean();
	 * 
	 * List<BAL_PlmTlStandards> createList = new ArrayList<>(); List<String>
	 * createRowIds = new ArrayList<>();
	 * 
	 * List<BAL_PlmTlStandards> updateNewList = new ArrayList<>();
	 * List<BAL_PlmTlStandards> updateExistList = new ArrayList<>(); List<String>
	 * updateRowIds = new ArrayList<>();
	 * 
	 * for (int i = 0; i < gridRows.length(); i++) { JSONObject row =
	 * gridRows.getJSONObject(i);
	 * 
	 * String rowId = row.optString("id", row.optString("rowid", "")); String
	 * existingKeyid = trimOrEmpty(row.optString("hdnPmsdKeyid"));
	 * 
	 * BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
	 * newPlmTlStandards.setPmsdCreatedby(user.getUsrm_ccno());
	 * 
	 * newPlmTlStandards.setPmsdFlid(flId);
	 * newPlmTlStandards.setPmsdSectionid(sectionId);
	 * newPlmTlStandards.setPmsdCellid(cellId);
	 * newPlmTlStandards.setPmsdFactoryid(factoryId);
	 * newPlmTlStandards.setPmsdElementid(elementId);
	 * newPlmTlStandards.setPmsdLocationid(locationId);
	 * newPlmTlStandards.setPmsdMachineid(getVal(row, "cmbMulPmsdMachineid",
	 * "hdnMulPmsdMachineid")); newPlmTlStandards.setPmsdAssemblyid(getVal(row,
	 * "cmbMulPmsdAssemblyid", "hdnMulPmsdAssemblyid"));
	 * newPlmTlStandards.setPmsdSubassemblyid(getVal(row, "cmbMulPmsdSubassemblyid",
	 * "hdnMulPmsdSubassemblyid"));
	 * newPlmTlStandards.setPmsdSource(trimOrEmpty(row.optString("cmbMulPmsdSource")
	 * )); newPlmTlStandards.setPmsdSupplierid(getVal(row, "cmbMulPmsdSupplierid",
	 * "hdnMulPmsdSupplierid"));
	 * newPlmTlStandards.setPmsdTradeid(trimOrEmpty(row.optString(
	 * "hdnMulPmsdTradeid")));
	 * newPlmTlStandards.setPmsdActivitytype(trimOrEmpty(row.optString(
	 * "hdnMulPmsdActivitytype")));
	 * newPlmTlStandards.setPmsdMachinecondition(getVal(row,
	 * "cmbMulPmsdMachinecondition", "hdnMulPmsdMachinecondition"));
	 * newPlmTlStandards.setPmsdActivitysubtype(trimOrEmpty(row.optString(
	 * "txtMulPmsdActivitysub")));
	 * 
	 * newPlmTlStandards.setPmsdFrequencyunit(getVal(row, "cmbMulPmsdFrequencyunit",
	 * "hdnMulPmsdFrequencyunit"));
	 * 
	 * // --- validate Frequency (numeric, required) --- String freqStr =
	 * trimOrEmpty(row.optString("txtMulPmsdFrequency")); if (freqStr.isEmpty() ||
	 * !freqStr.matches("\\d+(\\.\\d+)?")) { JSONObject err = new JSONObject();
	 * err.put("tpmException", "Row " + (i + 1) +
	 * ": Frequency must be a valid number"); out.print(err.toString()); return; }
	 * newPlmTlStandards.setPmsdFrequency(freqStr);
	 * 
	 * // --- validate Duration (numeric, optional -> defaults to 0) --- String
	 * durStr = trimOrEmpty(row.optString("txtMulPmsdDuration")); if
	 * (!durStr.isEmpty() && !durStr.matches("\\d+(\\.\\d+)?")) { JSONObject err =
	 * new JSONObject(); err.put("tpmException", "Row " + (i + 1) +
	 * ": Duration must be a valid number"); out.print(err.toString()); return; } if
	 * (durStr.isEmpty()) { durStr = "0"; }
	 * newPlmTlStandards.setPmsdDuration(durStr);
	 * 
	 * newPlmTlStandards.setPmsdLocation(trimOrEmpty(row.optString(
	 * "txtMulPmsdLocation")));
	 * 
	 * newPlmTlStandards.setPmsdActivity(trimOrEmpty(row.optString(
	 * "txtMulPmsdActivity")));
	 * newPlmTlStandards.setPmsdHowmethod(trimOrEmpty(row.optString(
	 * "txtMulPmsdHowmethod")));
	 * newPlmTlStandards.setPmsdStandard(trimOrEmpty(row.optString(
	 * "txtMulPmsdStandard")));
	 * 
	 * // mano - FIX: pmsd_issparesreq / pmsd_istoolsreq are NOT NULL character(1)
	 * // columns expecting exactly "Y" or "N". The grid sends "" / " " / anything
	 * // non-"Y" when the checkbox is unchecked (unlike the single-entry HTML //
	 * checkbox, whose omission gets defaulted to "N" inside fillValues() on // the
	 * create()/update() path). createMultiple()/updateMultiple() do not // run that
	 * same defaulting, so normalize here instead of relying on it. String
	 * issparesreq = trimOrEmpty(row.optString("chkMulPmsdIssparesreq"));
	 * newPlmTlStandards.setPmsdIssparesreq("Y".equalsIgnoreCase(issparesreq) ? "Y"
	 * : "N");
	 * 
	 * String istoolsreq = trimOrEmpty(row.optString("chkMulPmsdIstoolsreq"));
	 * newPlmTlStandards.setPmsdIstoolsreq("Y".equalsIgnoreCase(istoolsreq) ? "Y" :
	 * "N");
	 * 
	 * newPlmTlStandards.setPmsdPreparedbyid(getVal(row, "cmbMulPmsdPreparedbyid",
	 * "hdnMulPmsdPreparedbyid"));
	 * 
	 * if (!UIUtils.isValidKeyId(existingKeyid)) {
	 * createList.add(newPlmTlStandards); createRowIds.add(rowId); } else {
	 * newPlmTlStandards.setPmsdKeyid(existingKeyid); BAL_PlmTlStandards
	 * existPlmTlStandards = (BAL_PlmTlStandards)
	 * httpSession.getAttribute("plmTlStandards" + existingKeyid);
	 * 
	 * updateNewList.add(newPlmTlStandards);
	 * updateExistList.add(existPlmTlStandards); updateRowIds.add(rowId); } }
	 * 
	 * // ---- run creates ---- if (!createList.isEmpty()) {
	 * List<BAL_PlmTlStandards> createdList =
	 * plmTlStandardsService.createMultiple(createList, formBean); for (int i = 0; i
	 * < createdList.size(); i++) { BAL_PlmTlStandards saved = createdList.get(i);
	 * httpSession.setAttribute(saved.getPmsdKeyid(), saved);
	 * 
	 * JSONObject savedRow = new JSONObject(); savedRow.put("rowid",
	 * createRowIds.get(i)); savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
	 * savedRows.put((Object) savedRow); } }
	 * 
	 * // ---- run updates ---- if (!updateNewList.isEmpty()) {
	 * List<BAL_PlmTlStandards> updatedList =
	 * plmTlStandardsService.updateMultiple(updateNewList, updateExistList,
	 * formBean); for (int i = 0; i < updatedList.size(); i++) { BAL_PlmTlStandards
	 * saved = updatedList.get(i); httpSession.setAttribute(saved.getPmsdKeyid(),
	 * saved);
	 * 
	 * JSONObject savedRow = new JSONObject(); savedRow.put("rowid",
	 * updateRowIds.get(i)); savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
	 * savedRows.put((Object) savedRow); } }
	 * 
	 * returnData.put("savedRows", savedRows); out.print(returnData.toString());
	 * CommonFunctions.debugMsg("end of saveMultiplePmsd"); } } catch
	 * (ValidationExceptions e) { JSONObject errMessage =
	 * UIUtils.validationExceptions(e.toString(), "PmstandardException");
	 * out.print(errMessage.toString()); } catch (Exception e) {
	 * CommonFunctions.debugMsg("saveMultiplePmsd error: " + e.getMessage());
	 * e.printStackTrace(); JSONObject err = new JSONObject();
	 * err.put("tpmException", "Data Not Saved"); out.print(err.toString()); } }
	 */
    private void saveMultiplePmsd(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ValidationExceptions {

        CommonFunctions.debugMsg("inside saveMultiplePmsd");
        HttpSession httpSession = request.getSession(false);
        ServletOutputStream out = response.getOutputStream();
        AdmTlUsermst user = UIUtils.getLoginUser(request);

        JSONObject returnData = new JSONObject();
        JSONArray savedRows = new JSONArray();

        try {
            if (httpSession != null && user != null) {

                String pmsdStdDetailsStr = request.getParameter("pmsdStdDetails");
                String flId       = request.getParameter("flId");
                String sectionId  = request.getParameter("sectionId");
                String cellId     = request.getParameter("cellId");
                String factoryId  = request.getParameter("factoryId");
                String elementId  = request.getParameter("elementId");
                String locationId = request.getParameter("locationId");
                System.out.println(factoryId+"factoryid");
                System.out.println(cellId+"cellid");

                if (!UIUtils.isValidKeyId(pmsdStdDetailsStr)) {
                    JSONObject err = new JSONObject();
                    err.put("tpmException", "No rows to save");
                    out.print(err.toString());
                    return;
                }

                JSONArray gridRows = JSONArray.fromString(pmsdStdDetailsStr);
                BAL_PlmTlStandardsFormBean formBean = new BAL_PlmTlStandardsFormBean();

                List<BAL_PlmTlStandards> createList = new ArrayList<>();
                List<String> createRowIds = new ArrayList<>();

                List<BAL_PlmTlStandards> updateNewList = new ArrayList<>();
                List<BAL_PlmTlStandards> updateExistList = new ArrayList<>();
                List<String> updateRowIds = new ArrayList<>();

                for (int i = 0; i < gridRows.length(); i++) {
                    JSONObject row = gridRows.getJSONObject(i);

                    String rowId = row.optString("id", row.optString("rowid", ""));
                    String existingKeyid = trimOrEmpty(row.optString("hdnPmsdKeyid"));

                    BAL_PlmTlStandards newPlmTlStandards = new BAL_PlmTlStandards();
                    newPlmTlStandards.setPmsdCreatedby(user.getUsrm_ccno());

                    newPlmTlStandards.setPmsdFlid(flId);
                    newPlmTlStandards.setPmsdSectionid(sectionId);
                    newPlmTlStandards.setPmsdCellid(cellId);
                    newPlmTlStandards.setPmsdFactoryid(factoryId);
                    newPlmTlStandards.setPmsdElementid(elementId);
                    newPlmTlStandards.setPmsdLocationid(locationId);
                    newPlmTlStandards.setPmsdMachineid(getVal(row, "cmbMulPmsdMachineid", "hdnMulPmsdMachineid"));
                    newPlmTlStandards.setPmsdAssemblyid(getVal(row, "cmbMulPmsdAssemblyid", "hdnMulPmsdAssemblyid"));
                    newPlmTlStandards.setPmsdSubassemblyid(getVal(row, "cmbMulPmsdSubassemblyid", "hdnMulPmsdSubassemblyid"));
                    newPlmTlStandards.setPmsdSource(trimOrEmpty(row.optString("cmbMulPmsdSource")));
                    newPlmTlStandards.setPmsdSupplierid(getVal(row, "cmbMulPmsdSupplierid", "hdnMulPmsdSupplierid"));
                    newPlmTlStandards.setPmsdTradeid(trimOrEmpty(row.optString("hdnMulPmsdTradeid")));
                    newPlmTlStandards.setPmsdActivitytype(trimOrEmpty(row.optString("hdnMulPmsdActivitytype")));
                    newPlmTlStandards.setPmsdMachinecondition(getVal(row, "cmbMulPmsdMachinecondition", "hdnMulPmsdMachinecondition"));
                    newPlmTlStandards.setPmsdActivitysubtype(trimOrEmpty(row.optString("txtMulPmsdActivitysub")));

                    newPlmTlStandards.setPmsdFrequencyunit(getVal(row, "cmbMulPmsdFrequencyunit", "hdnMulPmsdFrequencyunit"));

                    // --- validate Frequency (numeric, required) ---
                    String freqStr = trimOrEmpty(row.optString("txtMulPmsdFrequency"));
                    if (freqStr.isEmpty() || !freqStr.matches("\\d+(\\.\\d+)?")) {
                        JSONObject err = new JSONObject();
                        err.put("tpmException", "Row " + (i + 1) + ": Frequency must be a valid number");
                        out.print(err.toString());
                        return;
                    }
                    newPlmTlStandards.setPmsdFrequency(freqStr);

                    // --- validate Duration (numeric, optional -> defaults to 0) ---
                    String durStr = trimOrEmpty(row.optString("txtMulPmsdDuration"));
                    if (!durStr.isEmpty() && !durStr.matches("\\d+(\\.\\d+)?")) {
                        JSONObject err = new JSONObject();
                        err.put("tpmException", "Row " + (i + 1) + ": Duration must be a valid number");
                        out.print(err.toString());
                        return;
                    }
                    if (durStr.isEmpty()) {
                        durStr = "0";
                    }
                    newPlmTlStandards.setPmsdDuration(durStr);

                    newPlmTlStandards.setPmsdLocation(trimOrEmpty(row.optString("txtMulPmsdLocation")));

                    newPlmTlStandards.setPmsdActivity(trimOrEmpty(row.optString("txtMulPmsdActivity")));
                    newPlmTlStandards.setPmsdHowmethod(trimOrEmpty(row.optString("txtMulPmsdHowmethod")));
                    newPlmTlStandards.setPmsdStandard(trimOrEmpty(row.optString("txtMulPmsdStandard")));

                    String issparesreq = trimOrEmpty(row.optString("chkMulPmsdIssparesreq"));
                    newPlmTlStandards.setPmsdIssparesreq("Y".equalsIgnoreCase(issparesreq) ? "Y" : "N");

                    String istoolsreq = trimOrEmpty(row.optString("chkMulPmsdIstoolsreq"));
                    newPlmTlStandards.setPmsdIstoolsreq("Y".equalsIgnoreCase(istoolsreq) ? "Y" : "N");

                    newPlmTlStandards.setPmsdPreparedbyid(getVal(row, "cmbMulPmsdPreparedbyid", "hdnMulPmsdPreparedbyid"));

                    if (!UIUtils.isValidKeyId(existingKeyid)) {
                        createList.add(newPlmTlStandards);
                        createRowIds.add(rowId);
                    } else {
                        newPlmTlStandards.setPmsdKeyid(existingKeyid);
                        BAL_PlmTlStandards existPlmTlStandards =
                                (BAL_PlmTlStandards) httpSession.getAttribute("plmTlStandards" + existingKeyid);

                        updateNewList.add(newPlmTlStandards);
                        updateExistList.add(existPlmTlStandards);
                        updateRowIds.add(rowId);
                    }
                }

                int createdCount = 0;
                int updatedCount = 0;

                // ---- run creates ----
                if (!createList.isEmpty()) {
                    List<BAL_PlmTlStandards> createdList = plmTlStandardsService.createMultiple(createList, formBean);
                    createdCount = createdList.size();
                    for (int i = 0; i < createdList.size(); i++) {
                        BAL_PlmTlStandards saved = createdList.get(i);
                        httpSession.setAttribute(saved.getPmsdKeyid(), saved);

                        JSONObject savedRow = new JSONObject();
                        savedRow.put("rowid", createRowIds.get(i));
                        savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
                        savedRows.put((Object) savedRow);
                    }
                }

                // ---- run updates ----
                if (!updateNewList.isEmpty()) {
                    List<BAL_PlmTlStandards> updatedList =
                            plmTlStandardsService.updateMultiple(updateNewList, updateExistList, formBean);
                    updatedCount = updatedList.size();
                    for (int i = 0; i < updatedList.size(); i++) {
                        BAL_PlmTlStandards saved = updatedList.get(i);
                        httpSession.setAttribute(saved.getPmsdKeyid(), saved);

                        JSONObject savedRow = new JSONObject();
                        savedRow.put("rowid", updateRowIds.get(i));
                        savedRow.put("hdnPmsdKeyid", saved.getPmsdKeyid());
                        savedRows.put((Object) savedRow);
                    }
                }

                // ---- build success message, same convention as TrainingCalSave ----
                String savemsg;
                if (createdCount > 0 && updatedCount > 0) {
                    savemsg = "Data Saved Successfully";
                } else if (updatedCount > 0 && createdCount == 0) {
                    savemsg = "Data Updated Successfully";
                } else {
                    savemsg = "Data Saved Successfully";
                }

                JSONObject successData = new JSONObject();
                successData.put("msg", savemsg);
                returnData.put("successData", successData);
                returnData.put("savedRows", savedRows);

                out.print(returnData.toString());
                CommonFunctions.debugMsg("end of saveMultiplePmsd");
            }
        } catch (ValidationExceptions e) {
            JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "PmstandardException");
            out.print(errMessage.toString());
        }
        catch (Exception e) {
            CommonFunctions.debugMsg("saveMultiplePmsd error: " + e.getMessage());
            e.printStackTrace();
            JSONObject err = new JSONObject();
            err.put("tpmException", "Data Not Saved");
            out.print(err.toString());
        }
    }
    private String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private String getVal(JSONObject row, String comboKey, String hiddenKey) {
        String v = trimOrEmpty(row.optString(comboKey));
        if (v.isEmpty() || v.equals(" ")) {
            v = trimOrEmpty(row.optString(hiddenKey));
        }
        return v;
    }
   //for filling headers 1st grid
    private JSONObject getTableModel(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader_0 = headers.get(0);	
		String [] colHeader = headers.get(1);		
		//String [] colHeader1 = headers.get(2);			
		jqGridTableModel.getRowHeaders().add(colHeader);
		
		for(int i =0; i < colHeader.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(colHeader_0[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader_0[i].replaceAll(" ", ""));
			jqGridTableModel.setTableButton(true);		
			jqGridTableModel.setRowNumbers(true);
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			if(i==0 || i==1 ||i==2)
			{
				jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			else if(i == 3)
			{
				jqGridColModel.setWidth(250);
			}
			/*else if(i == 5)
			{
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("right");
			}*/
			else if(i == 5 || i == 6 ||i == 7 ||i == 8 ||i == 9  )
			{
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("right");
			}
			else if(i == 10)
			{
				jqGridColModel.setWidth(100);
				jqGridColModel.setAlign("right");
			}
			else if(i == 11 ||i == 12  )
			{
				jqGridColModel.setWidth(68);
				jqGridColModel.setAlign("right");
			}
			
			CommonFunctions.debugMsg("colHeader["+i+"] "+colHeader[i]);
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
			JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);		 
			tableModel.set("tableHeight", "45%%");
			tableModel.set("tableWidth", "85%%");
		 return tableModel;
    }
   //end 
//for multi method
    
    
    
    private BAL_PlmTlMethodsmst getPlmTlMultiplemethodsmst(List<BAL_PlmTlMethodsmst> plmTlMultiplemethodsmstList, String mlmmKeyid)
    {
   	 int index = 0;
   	 for( BAL_PlmTlMethodsmst plmTlMethodsmst:plmTlMultiplemethodsmstList)
   	 {
   		 if( plmTlMethodsmst.getPmmsKeyid().equals(mlmmKeyid) ){
   			 plmTlMultiplemethodsmstList.remove(index);
   			 return plmTlMethodsmst;
   		 }	 
   		 index++;
   	 }
   	 return null; 
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
  			
  			BAL_PlmTlMethodsmst plmTlMethodsmst = (BAL_PlmTlMethodsmst)tpmModel;
	  		JSONObject rowObj =new JSONObject();
		    rowObj.put("id",++rowid);
	        JSONArray cell = new  JSONArray();
	        cell.put(plmTlMethodsmst.getPmmsKeyid());
	        cell.put(" ");
	        cell.put(plmTlMethodsmst.getPmmsActivity());
	        cell.put(" ");
	        cell.put(plmTlMethodsmst.getPmmsDuration());
	        
	        cell.put(plmTlMethodsmst.getPmmsInstructions());
	        cell.put("..");
	        rowObj.put("cell",cell);
	        
	        rowArr.put(rowObj);
		}
  		
  		multiplemethodDataObject.put("rows", rowArr);
	      
  			//CommonFunctions.debugMsg("multiplemethodDataObject="+multiplemethodDataObject);
	        return multiplemethodDataObject;
   	}

//tool tree get value fill grid
    private JSONObject convertToToolTreeTblObject(List<Object> genVwToolcategorytList)
  	{
   		CommonFunctions.debugMsg("inside the convert tool function");
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
	      
  			CommonFunctions.debugMsg("TOOLDataObject="+toolTreeDataObject);
	        return toolTreeDataObject;
   	}
  //for tools 

    private JSONArray getToolsTreeArray(List <BAL_GenVwToolcategory> toolpickupList, String selectdElementIds){
   	 JSONArray jSONArray = new JSONArray();
   	 CommonFunctions.debugMsg("getToolsTreeArray called....");
    	for(int i=0; i<toolpickupList.size(); i++){
    		CommonFunctions.debugMsg("tool size  :"+toolpickupList.size());
    	//	if(menuList.get(i).getMenuLevel().equals("1"))
    	//	{	
    			JSONObject jSONObject = new JSONObject();

    			jSONObject.put("data",toolpickupList.get(i).getDisplaycode());
	                jSONObject.put("state","close");
	                String elementId = toolpickupList.get(i).getElementid().trim();
	               // CommonFunctions.debugMsg("elementIdbefore --"+elementId);
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
	   CommonFunctions.debugMsg("inside JstreeNodeObject ");
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
   //CBM convert to object
   private JSONObject convertToCBMObject(String dataStr)
 	{
	   
 		JSONObject CBMDataObject = new JSONObject();
		
 		CBMDataObject.put("page", 1); //current page
 		CBMDataObject.put("total",10); // total page
 		CBMDataObject.put("records", (3)); //total records
		String[] datas = dataStr.split("#");
		
		CommonFunctions.debugMsg(datas[0]+" -- "+datas[1]+" -- "+datas[3]+" -- "+datas[4]);
		JSONArray rowArr = new JSONArray(); 
		int  rowid =0;
 		//for(Object tpmModel: dataStr)
 		///{
 			
 		 	BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl();
	  		JSONObject rowObj =new JSONObject();
		    rowObj.put("id",++rowid);
		   // CommonFunctions.debugMsg("plmTlEqpgrpmethodsmst  "+dataStr.size());
	        CommonFunctions.debugMsg("plmTlEqpgrpmethodsmst11  "+plmTlCbmstdcadtl.getCmdtKeyid());
	        CommonFunctions.debugMsg("plmTlEqpgrpmethodsmst22  "+plmTlCbmstdcadtl.getCmdtUpperlimit());
	        JSONArray cell = new  JSONArray();
	        cell.put(plmTlCbmstdcadtl.getCmdtKeyid());
	        cell.put(plmTlCbmstdcadtl.getCmdtLowerlimit());
	        cell.put(plmTlCbmstdcadtl.getCmdtUpperlimit());
	        cell.put(plmTlCbmstdcadtl.getCmdtDesirablereading());
	        cell.put(plmTlCbmstdcadtl.getCmdtCorrectiveaction());
	        rowObj.put("cell",cell);
	        
	        rowArr.put(rowObj);
		//}
 		
 		CBMDataObject.put("rows", rowArr);
	      
 			//CommonFunctions.debugMsg("multiplemethodDataObject="+multiplemethodDataObject);
	        return CBMDataObject;
  	}

   private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getPMRelated(request,commonFilter);
			CommonFunctions.debugMsg( "commonFilter ------"+commonFilter.getTrade());
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		//CommonFunctions.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
   ///Pm Report
   
   private void PmReportGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter=new  CommonFilter();
		JSONObject jsonObject = new JSONObject();
		List<String[]> testGrid = null;
		try {
		//commonFilter = populateCommonFilter(request,"onlineTestCommonFilter",true);
			testGrid = plmTlStandardsService.getPMReport(commonFilter);
		} catch (Exception e) {
		e.printStackTrace();
		}
		jsonObject = getTableModelForPMReport(testGrid);
		httpSession.removeAttribute("PmReportColModel");
		httpSession.setAttribute("PmReportColModel", jsonObject);
		out.println(jsonObject);
		
	}
   private void PmReportGetData(HttpServletRequest request,	HttpServletResponse response) {
		try {
			CommonFilter commonFilter = new CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter);
			//commonFilter = populateCommonFilter(request,"onlineTestCommonFilter",false);
			List<String[]> testGrid = plmTlStandardsService.getPMReport(commonFilter);
			PrintWriter out = response.getWriter();
			JSONObject testReportGridmod = UIUtils.convertToJqGridTableObject(testGrid, request, 2,0);
			out.println(testReportGridmod);
			} catch (Exception e) {
			System.out.println(e.getMessage());
			}
		
	}
	
	
	private JSONObject getTableModelForPMReport(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		jqGridTableModel.setTableButton(true);
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setAlign("left");
		   	jqGridColModel.setWidth(125);
			if(i==0){
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("center");
			}
			if(i==1){
				jqGridColModel.setWidth(200);
			}
			if(i==2){
				jqGridColModel.setWidth(250);
			}
			if(i==3){
				jqGridColModel.setWidth(150);
			}
			if(i==5){
				jqGridColModel.setWidth(70);
			}
			if(i==4){
				jqGridColModel.setWidth(70);
				jqGridColModel.setAlign("center");
				jqGridColModel.setFormatter("txtFormatter");
			}
			if(i==7){
				jqGridColModel.setWidth(130);
			}
			jqGridColModel.setEditable(false);
			System.out.println("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "92%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
}

}

