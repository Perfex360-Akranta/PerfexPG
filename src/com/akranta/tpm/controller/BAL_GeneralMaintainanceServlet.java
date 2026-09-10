/*Author : Manikandan*/
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.BAL_GeneralMaintainanceBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlSetupadjsplit;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BAL_GeneralmaintService;
import com.akranta.tpm.service.BAL_PlmTlGenmaintenanceService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.api.BAL_GeneralMaintenanceServiceApi;
import com.akranta.tpm.service.impl.BAL_PlmTlGenmaintenanceServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.utils.WOConstants;
import com.akranta.tpm.service.api.BdmServiceApi;
/**
 * Servlet implementation class GeneralMaintainanceServlet
 */

public class BAL_GeneralMaintainanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	BAL_GeneralmaintService genralService;
	BAL_PlmTlGenmaintenanceService plmTlGenmaintenanceService;
	WorkOrderService workOrderService;
	BAL_GeneralMaintenanceServiceApi GeneralMaintenanceServiceApi;
	BdmServiceApi BdmServiceApi;

	//GeneralMaintainanceBean generalMaintainanceBean = new GeneralMaintainanceBean();
    public BAL_GeneralMaintainanceServlet() {
        super();
        // TODO Auto-generated constructor stub
       /* try {
        	plmTlGenmaintenanceService = new PlmTlGenmaintenanceServiceImpl();
        	workOrderService = new WorkOrderServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		 
		
			System.out.println("Find Mode  "+mode);
			
	    	BAL_GeneralMaintainanceBean generalMaintainancebean = new BAL_GeneralMaintainanceBean(mode);
	    	BAL_PlmTlGenmaintenance plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
	    	//WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute(WOConstants.gmInWOServlet);
	    	httpSession.removeAttribute("fromWO");
	    	httpSession.removeAttribute("WorkOrderGM");
	    	
	    	String docno = request.getParameter("docno");
	    	String fromWO = request.getParameter("backToWO");
	    	String woKeyId = request.getParameter("WOID");
	    	String backTo = request.getParameter(WOConstants.backTo);
	    	httpSession.removeAttribute(WOConstants.backTo);
			if(UIUtils.isValidKeyId(backTo))
				httpSession.setAttribute(WOConstants.backTo,backTo);
			String delActivity = request.getParameter("delActivity");				
			if(UIUtils.isValidKeyId(delActivity))
				request.setAttribute("delActivity","Y");
	    	if(UIUtils.isValidKeyId(fromWO))
	    	{
	    		//httpSession.removeAttribute("fromWO");
	    		httpSession.setAttribute("fromWO", fromWO);
	    	}
	    	if(UIUtils.isValidKeyId(woKeyId))
	    	{
	    		WomTlWomst womTlWomst = workOrderService.select(woKeyId);
	    		httpSession.setAttribute("WorkOrderGM", womTlWomst);
	    	}
		 	
		 	if(mode.equals(FormModes.create)){
		 		AdmTlUsermst user = UIUtils.getLoginUser(request);
				//user.getUsrm_ccno();
				System.out.println("input called"+user.getUsrm_ccno());
				plmTlGenmaintenance.setGmntReportedby(user.getUsrm_ccno());
				plmTlGenmaintenance.setGmntCompletedby(user.getUsrm_ccno());
				System.out.println("c called"+plmTlGenmaintenance.getGmntCompletedby());
				request.setAttribute("plmTlGenmaintenance",plmTlGenmaintenance );
			}
		 	httpSession.removeAttribute("plmTlGenmaintenance_Servlet");
		 	if( UIUtils.isValidKeyId(docno)){
				try {
					System.out.println("docno docno NEW NEW NEW:"+docno);
						plmTlGenmaintenance = plmTlGenmaintenanceService.getFillValue(docno);
						System.out.println("workhrs :"+plmTlGenmaintenance.getGmntWorkhours());
						
						System.out.println("Work Order NEW NEW NEW:"+plmTlGenmaintenance.getGmntOrderType());
						
						//GeneralMaintainanceBean generalMaintainanceBean= new GeneralMaintainanceBean(mode);*/
			             if((plmTlGenmaintenance.getGmntStatus()).equals("P"))
			             {
			            	 System.out.println("StatusIF :"+plmTlGenmaintenance.getGmntStatus());
			            	 plmTlGenmaintenance.setGmntWostartdate("");
			            	 plmTlGenmaintenance.setGmntWoenddate("");
			             }
			             System.out.println("bookedbefr  "+plmTlGenmaintenance.getGmntBookeddate());
			             plmTlGenmaintenance.setGmntBookeddate(plmTlGenmaintenance.getGmntBookeddate().substring(0,11));
			             plmTlGenmaintenance.setGmntShiftdate(plmTlGenmaintenance.getGmntShiftdate().substring(0,11));
			             plmTlGenmaintenance.setGmntTargetdate(plmTlGenmaintenance.getGmntTargetdate().substring(0,11));
			             System.out.println("bookedDate  "+plmTlGenmaintenance.getGmntBookeddate());
			             httpSession.setAttribute("plmTlGenmaintenance_Servlet", plmTlGenmaintenance); 
			    		 request.setAttribute("plmTlGenmaintenance", plmTlGenmaintenance);
						//request.setAttribute("generalMaintainanceBean",generalMaintainanceBean);
						
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		 	}
			setGmntBeanVal(plmTlGenmaintenance,generalMaintainancebean);
			
		 	httpSession.removeAttribute("generalMaintainanceMldBean");
			httpSession.setAttribute("generalMaintainanceBean", generalMaintainancebean);
			// httpSession.removeAttribute("actionpassed");//clear session of mode stored
			//System.out.println("chk disableForm status  "+generalMaintainance.getDisableForm());
			request.setAttribute("generalMaintainanceBean", generalMaintainancebean);
	 }
	    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
	    	 
	    	CommonFilter commonFilter = null; 
	 		 
	 		HttpSession httpSession = request.getSession(false);
	 		
	 		String dispatchUrl = null;
	 		
		    String action = UIUtils.getActionPart(request);
		    ComboFilter comboFilter = new ComboFilter();
			try {
				genralService= (BAL_GeneralmaintService) UIUtils.getServiceObject(request, "BAL_GeneralmaintServiceImpl");
				plmTlGenmaintenanceService = (BAL_PlmTlGenmaintenanceServiceImpl)UIUtils.getServiceObject(request,"BAL_PlmTlGenmaintenanceServiceImpl");
	        	workOrderService = (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
	        	plmTlGenmaintenanceService.BAL_PlmTlGenmaintenanceServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")));
	        	workOrderService.WorkOrderServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );

			} catch (ServiceObjectCreationException e) {
				CommonFunctions.debugMsg(e);
			}

	 		
	 		response.setContentType("text/html");
	 		response.setContentType("text/json");
	 		if( action.equals("filterXmlgeneralMaint_modify.balgenmain")||action.equals("filterXmlgeneralMaint_view.balgenmain")
	 				||action.equals("filterXmlgeneralMaintMould_modify.balgenmain")||action.equals("filterXmlgeneralSetAndAdj_modify.balgenmain")){
				 response.setContentType("xml"); 
				System.out.println("action "+ action); 
				UIUtils.forwardRequest(request, response, "/tiles/xml/generalMaintaince.xml") ;
			 }
	 		
	 		else if(action.equals("generalMaintcreat_input.balgenmain")||action.equals("generalMaintMould_input.balgenmain")) 
			{
				//FormModes mode = (FormModes) httpSession.getAttribute("GeneralMainFormMode");
				
				String modeStr = request.getParameter(ReqtParamNameConst.FORM_MODE);
				String orderType=request.getParameter("orderType");
				System.out.println("dsd"+modeStr);
				String sapSts=request.getParameter("sapsts");
				
				CommonFunctions.debugMsg("sap status"+sapSts);
				httpSession.removeAttribute("GeneralMainFormMode");
				String menumode = request.getParameter("menumode");
				if(UIUtils.isValidKeyId(menumode))
					request.setAttribute("menumode", "setupandadjustment");
				FormModes mode = FormModes.create; 			
				
				if(modeStr == null || ( modeStr != null && modeStr.equals(FormModeConsts.create ) )) 
					mode=FormModes.create;
				else if(modeStr.equals(FormModeConsts.modify) )
					mode=FormModes.modify;
				else 
					mode=FormModes.view;
				
				httpSession.setAttribute("GeneralMainFormMode",mode);
				initilizeInputMode(mode, request, httpSession);	
				BAL_PlmTlGenmaintenance plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
				//String statussap=plmTlGenmaintenance.getErrppostStatus();
				if("C".equals(sapSts) && "C".equals(plmTlGenmaintenance.getGmntStatus())){
					
					CommonFunctions.debugMsg("sap Status........"+plmTlGenmaintenance.getErrppostStatus());
					
					
					request.setAttribute("Status","Completed in SAP");
				}
				else if("P".equals(plmTlGenmaintenance.getGmntStatus()) && "X".equals(plmTlGenmaintenance.getErrppostStatus()))
					{
					CommonFunctions.debugMsg("sap Status........"+plmTlGenmaintenance.getErrppostStatus());
					request.setAttribute("Status","Completed in Perfex Not In SAP");
				}
				request.setAttribute(ReqtParamNameConst.FORM_MODE, mode);
				
				request.setAttribute("orderType",orderType);
				//request.setAttribute("url", action);
				request.setAttribute("url", "generalMaint_modify.balgenmain");
				request.setAttribute("stats", "P");
				dispatchUrl="/pages/GeneralMaintainence.jsp"; 
			
			}
			/*
			 * else if(action.equals("generalMaint_modify.balgenmain")
			 * ||action.equals("generalMaintMould_modify.balgenmain")||action.equals(
			 * "generalSetAndAdj_modify.balgenmain")) {
			 * httpSession.removeAttribute("GeneralMainFormMode");
			 * httpSession.setAttribute("GeneralMainFormMode", FormModes.modify);
			 * request.setAttribute("stats", "P"); //BAL_PlmTlGenmaintenance
			 * plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
			 * //request.setAttribute("orderType",plmTlGenmaintenance.getGmntOrderType());
			 * String docno = request.getParameter("docno"); BAL_PlmTlGenmaintenance
			 * plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
			 * 
			 * if(UIUtils.isValidKeyId(docno)){ try{ plmTlGenmaintenance =
			 * plmTlGenmaintenanceService.getFillValue(docno);
			 * System.out.println(plmTlGenmaintenance.getGmntOrderType()+"Testing order");
			 * }catch(Exception e){ e.printStackTrace(); } }
			 * 
			 * 
			 * request.setAttribute("orderType", plmTlGenmaintenance.getGmntOrderType());
			 * request.setAttribute("sapSts", plmTlGenmaintenance.getErrppostStatus());
			 * request.setAttribute("sts", plmTlGenmaintenance.getGmntStatus());
			 * //----------------------------------------------------------------------
			 * CommonFunctions.debugMsg(plmTlGenmaintenance.getGmntOrderType()
			 * +"      oreder Type........."); request.setAttribute("sapSts",
			 * plmTlGenmaintenance.getErrppostStatus()); request.setAttribute("sts",
			 * plmTlGenmaintenance.getGmntStatus());
			 * if(action.equals("generalSetAndAdj_modify.balgenmain"))
			 * request.setAttribute("setupandadjustment", "true");
			 * dispatchUrl="/pages/GenMaint_MainPage.jsp"; }
			 */
	 		
	 		else if(action.equals("generalMaint_modify.balgenmain") ||action.equals("generalMaintMould_modify.balgenmain")||action.equals("generalSetAndAdj_modify.balgenmain"))
	 		{
	 		    httpSession.removeAttribute("GeneralMainFormMode");
	 		    httpSession.setAttribute("GeneralMainFormMode", FormModes.modify);
	 		    request.setAttribute("stats", "P");

	 		    //request.setAttribute("url", action);   // <-- ADD THIS
	 		   request.setAttribute("url", "generalMaint_modify.balgenmain");

	 		    String docno = request.getParameter("docno");
	 		    BAL_PlmTlGenmaintenance plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();

	 		    if(UIUtils.isValidKeyId(docno)){
	 		        try{
	 		            plmTlGenmaintenance = plmTlGenmaintenanceService.getFillValue(docno);
	 		            System.out.println(plmTlGenmaintenance.getGmntOrderType()+"Testing order");
	 		        }catch(Exception e){
	 		            e.printStackTrace();
	 		        }
	 		    }

	 		    request.setAttribute("orderType", plmTlGenmaintenance.getGmntOrderType());
	 		    request.setAttribute("sapSts", plmTlGenmaintenance.getErrppostStatus());
	 		    request.setAttribute("sts", plmTlGenmaintenance.getGmntStatus());
	 		    CommonFunctions.debugMsg(plmTlGenmaintenance.getGmntOrderType()+"      oreder Type.........");
	 		    request.setAttribute("sapSts", plmTlGenmaintenance.getErrppostStatus());
	 		    request.setAttribute("sts", plmTlGenmaintenance.getGmntStatus());
	 		    if(action.equals("generalSetAndAdj_modify.balgenmain"))
	 		        request.setAttribute("setupandadjustment", "true");
	 		    dispatchUrl="/pages/GenMaint_MainPage.jsp";
	 		}
			/*
			 * else if(action.equals("generalMaint_modify.balgenmain")
			 * ||action.equals("generalMaintMould_modify.balgenmain")||action.equals(
			 * "generalSetAndAdj_modify.balgenmain")) {
			 * httpSession.removeAttribute("GeneralMainFormMode");
			 * httpSession.setAttribute("GeneralMainFormMode", FormModes.modify);
			 * request.setAttribute("stats", "P");
			 * 
			 * String docno = request.getParameter("docno"); // <-- Step 1-la confirm panna
			 * exact name podunga System.out.println("MODIFY action docno param : " +
			 * docno); // debug-ku add pannunga, log-la theriyum
			 * 
			 * BAL_PlmTlGenmaintenance plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
			 * 
			 * if(UIUtils.isValidKeyId(docno)){ try{ plmTlGenmaintenance =
			 * plmTlGenmaintenanceService.getFillValue(docno);
			 * 
			 * if((plmTlGenmaintenance.getGmntStatus()).equals("P")){
			 * plmTlGenmaintenance.setGmntWostartdate("");
			 * plmTlGenmaintenance.setGmntWoenddate(""); }
			 * plmTlGenmaintenance.setGmntBookeddate(plmTlGenmaintenance.getGmntBookeddate()
			 * .substring(0,11));
			 * plmTlGenmaintenance.setGmntShiftdate(plmTlGenmaintenance.getGmntShiftdate().
			 * substring(0,11));
			 * plmTlGenmaintenance.setGmntTargetdate(plmTlGenmaintenance.getGmntTargetdate()
			 * .substring(0,11));
			 * 
			 * httpSession.setAttribute("plmTlGenmaintenance_Servlet", plmTlGenmaintenance);
			 * request.setAttribute("plmTlGenmaintenance", plmTlGenmaintenance);
			 * }catch(Exception e){ e.printStackTrace(); } }
			 * 
			 * request.setAttribute("orderType",plmTlGenmaintenance.getGmntOrderType());
			 * request.setAttribute("sapSts", plmTlGenmaintenance.getErrppostStatus());
			 * request.setAttribute("sts", plmTlGenmaintenance.getGmntStatus());
			 * if(action.equals("generalSetAndAdj_modify.balgenmain"))
			 * request.setAttribute("setupandadjustment", "true");
			 * dispatchUrl="/pages/GenMaint_MainPage.jsp"; }
			 */	 		
			else if( action.equals("costinfo.balgenmain"))
			{	
				//BAL_BDFormBean bdFormBean = new  BAL_BDFormBean();
				 BAL_GeneralMaintainanceBean generalMaintainanceBean = new  BAL_GeneralMaintainanceBean();
				String repType=request.getParameter("repType");
				CommonFunctions.debugMsg(repType);
				if (repType.equals("Estimate"))		
					generalMaintainanceBean.setFormActionMode("estimate");
				else
					generalMaintainanceBean.setFormActionMode("actual");
				
				saveGenMain(request,response,generalMaintainanceBean);
			}
			else if( action.equals("sapInfo_input.balgenmain")){
				//httpSession.setAttribute("breakdownId", request.getParameter("bdId"));
		    	
		    	 String keyId = request.getParameter("keyId");
		    	 String spares = request.getParameter("isSpares");
		    	 String refDocType = request.getParameter("refDocType");
		    	 String machineId = request.getParameter("machine");
		    	 String flid =request.getParameter("flid"); 
		    	 String priority = request.getParameter("priority");
		    	 String  refdocId    = request.getParameter("keyId");
		    	 String  funLoc    = request.getParameter("functionalloc");
		    	 String machine = request.getParameter("machine");
		    	 String saptst=request.getParameter("sapsts");
		    	 
		         SapTlMaintenanceOrdermst newSapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
		    	
		        request.setAttribute("funLoc",funLoc);
		        request.setAttribute("keyId", keyId);
		        request.setAttribute("spares", spares);
				request.setAttribute("refdocId",refdocId );
				request.setAttribute("refDocType",refDocType);
				request.setAttribute("machineId",machineId);
				request.setAttribute("machine", machine);
				request.setAttribute("priority",priority);
				request.setAttribute("sapSts",saptst);
				CommonFunctions.debugMsg("before Dispatch "+keyId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/SapInfoGenMnt.jsp"); 
				rd.forward(request, response); 
				CommonFunctions.debugMsg("ay Dispatch ");
			}
			
			else if(action.equals("sapInfo_getCol.balgenmain")){			
			
				System.out.println("sapInfo_getCol.balgenmain" );
				PrintWriter out = response.getWriter();				
				String bdId = request.getParameter("keyId");
				 commonFilter = populateCommonFilter(request,"SAPInfoCommonFilter",true);
				 System.out.println("before seting keyid IN GETCOL METHOD"+bdId );
				 commonFilter.setKey(bdId);
				 System.out.println("after seting keyid"+bdId+"Common FIlter "+commonFilter );
				List<String[]> Grid = genralService.getSapInfoList(commonFilter);
				System.out.println("after commonFilter keyid       "+bdId+"Common FIlter "+commonFilter+"     grid ..."+Grid.size()+" "+ Grid );
				//List<String []> KpivReportList  =  kpivservice.getAllGeneral(commonFilter);
				JSONObject colModel = getTableModelSapInfo(Grid,commonFilter );
				CommonFunctions.debugMsg("test after colmodel"+bdId);
				colModel.set("tableHeight", "40%%");
				colModel.set("tableWidth", "100%%");
				
				httpSession.setAttribute("ColModel", colModel);
				System.out.println("before" );
				out.println(colModel);
				System.out.println("colModelSAPMaintainorder  "+colModel );
			}
			else if(action.equals("sapInfo_getData.balgenmain") )
			{
				
				try
				{	
					
					String bdId = request.getParameter("keyId");
					System.out.println("test for get Data"+bdId );										
					 commonFilter = populateCommonFilter(request,"SAPInfoCommonFilter",true);
					 commonFilter.setKey(bdId);
					 System.out.println("after seting get Data"+bdId );
					List<String[]> Grid = genralService.getSapInfoList(commonFilter);
					System.out.println("test for get ca"+bdId );
					PrintWriter out = response.getWriter();					
					JSONObject sopmaster = UIUtils.convertToJqGridTableObject(Grid ,request, 1, 0);
					out.println(sopmaster);
					System.out.println("test for get Data"+bdId );
				}catch(Exception e)
				{
					CommonFunctions.debugMsg(e.getMessage());
				}
			} 
			else if(action.equals("generalMaint_view.balgenmain")||action.equals("generalMaintMould_view.balgenmain")||action.equals("generalSetAndAdj_view.balgenmain"))
			{	
				httpSession.setAttribute("GeneralMainFormMode", FormModes.view);
				CommonFunctions.debugMsg("VIEW    :"+httpSession.getAttribute("GeneralMainFormMode"));
			 //	httpSession.setAttribute("cmpId",cmpId);
				//FormModes mode = (FormModes) httpSession.getAttribute("GeneralMainFormMode");
				//System.out.println("manik   "+mode);
				if(action.equals("generalSetAndAdj_view.genmain"))
					request.setAttribute("setupandadjustment", "true");
				dispatchUrl="/pages/GenMaint_MainPage.jsp";
				httpSession.removeAttribute("GeneralMainFormMode");
			}  
			
							
			   else if(action.equals("generalMaint_getCol.balgenmain")||action.equals("generalMaintMould_getCol.balgenmain")||action.equals("generalSetAndAdj_getCol.balgenmain"))
			    {
				  
			    	
			    	String modeCald = request.getParameter("hdnMode");
			    	httpSession.setAttribute("actionpassed", modeCald);
			    	
					String firstClick =request.getParameter("firstClick");
					String gmView =request.getParameter("selid");	
					commonFilter = populateCommonFilter(request,"GMCommonFilter",true);
					 /*if( ! UIUtils.isValidKeyId(firstClick)  || (firstClick!= null && ! firstClick.equals("Y"))){ 
						 commonFilter =(CommonFilter) httpSession.getAttribute("commonFilterIden");
					 }	
					 if(commonFilter==null)
						 commonFilter = new CommonFilter();*/
					
					// FilterValues.getCommonFilters(request,commonFilter);
					 //FilterValues.getBDRelated(request, commonFilter);
					 /*if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y")) ){
						  commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3,11));
						  commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						  commonFilter.setMonwise("Y");
						 
				 	  }*/
					
							 CommonFunctions.debugMsg("company  "+commonFilter.getCompany()); 
							 if(UIUtils.isValidKeyId(gmView)){
								 CommonFunctions.debugMsg("company  yrtu8888888");
								 	if ( !( gmView.length()>8)){
								 		CommonFunctions.debugMsg("companydghdrehr  ");
								 		if( Constants.passNullDate.contains(commonFilter.getFromMonth())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
											  commonFilter.setFromMonth(gmView);
											  commonFilter.setToMonth(gmView);
											  commonFilter.setMonwise("Y");
											  CommonFunctions.debugMsg("fromMOnth if    :"+commonFilter.getFromMonth()+"----"+commonFilter.getToMonth());
									 	  }
	
								 	}else
								 	{
								 		CommonFunctions.debugMsg("company111111111111  "+commonFilter.getFromDate());
								 		if( Constants.passNullDate.contains(commonFilter.getFromDate())&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" ")) ){
											  commonFilter.setFromDate(gmView);
											  commonFilter.setToDate(gmView);
											  // commonFilter.setMonwise(" ");
											  CommonFunctions.debugMsg("fromDate Else    :"+commonFilter.getFromDate()+"----"+commonFilter.getToDate());
									 	  }
								 	}
								 	
									
							}
							 
					 httpSession.removeAttribute("commonFilterIden");
					 httpSession.setAttribute("commonFilterIden",commonFilter);
			 	    PrintWriter out = response.getWriter();
			 	   String relto= "MCH";
				 	  String setupAdj = null;
				 	   if(action.equals("generalMaintMould_getCol.balgenmain"))
				 		  relto= "MLD";
				 	   
				 	  if(action.equals("generalSetAndAdj_getCol.balgenmain"))
				 		 setupAdj= "S";
			 	   //String colModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "colModelGenMain");
				 	 commonFilter.setIsGetCol("Y");

			 	 List<String []> getGriddata  = plmTlGenmaintenanceService.getdataGenMain(commonFilter,relto,setupAdj);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					//gridColModel.setFormatter("gen_main_formater");
					//gridColModel.setFormattorFromCol("2");
					//gridColModel.setFormattorToCol("2");
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					
					gridColModel.setHeaderNum(1);
					
					//String [] colHeader = getGriddata.get(1);			
					//String [] colHeaderCond = getGriddata.get(0);
					String [] colHeader = getGriddata.get(1);      // ["Doc.No","Work Order No",...]
					String [] colHeaderCond = getGriddata.get(0);  // ["2","2","Doc.No",...]
					
					//CommonFunctions.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "81%%");
					jsonObject.put("tableWidth", "106%%");
			      	CommonFunctions.debugMsg("jsonObject ="+jsonObject);
			      	out.println(jsonObject);
			 	    /*httpSession.removeAttribute("genMaintainanceColModel");
					httpSession.setAttribute("genMaintainanceColModel",colModel);
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "colModelGenMain"));*/
			    }
	 		  else if(action.equals("generalMaint_getData.balgenmain")||action.equals("generalMaintMould_getData.balgenmain")||action.equals("generalSetAndAdj_getData.balgenmain"))
			    {
	 			  commonFilter = populateCommonFilter(request,"GMCommonFilter",false);
	 			 String modeCald = request.getParameter("stats");
			 	   PrintWriter out = response.getWriter();
			 	  String relto= "MCH";
			 	  String setupAdj = null;
			 	  CommonFunctions.debugMsg("stats:"+modeCald);
			 	   if(action.equals("generalMaintMould_getData.balgenmain"))
			 		  relto= "MLD";
			 	   
			 	  if(action.equals("generalSetAndAdj_getData.balgenmain"))
			 		 setupAdj= "S";
			 	  if(modeCald=="P"){
			 		  ComboFilter stats=new ComboFilter();
			 		  stats.setId("P");
			 		  commonFilter.setBdstatus(stats);
			 	  }
			 	 commonFilter.setIsGetCol("N");


			 	   List<String []> getGriddata  = plmTlGenmaintenanceService.getdataGenMain(commonFilter,relto,setupAdj);
			 	   //JSONObject getGriddatadata = UIUtils.convertToJqGridTableObject(getGriddata,request,1,0);
			 	  JSONObject getGriddatadata = UIUtils.convertToJqGridTableObject(getGriddata, request, 2, 0);
                    out.println(getGriddatadata);
			    }
	 		 else if( action.equals("generalMaint_getExcel.balgenmain")||action.equals("generalMaintMould_getExcel.balgenmain")){
	  			
	  			//HttpSession httpSession = request.getSession(false);
	  			 commonFilter = populateCommonFilter(request,"commonFilterIden",false);
	  			String tmpFromRow = commonFilter.getFromRow();
	  			commonFilter.setFromRow(null);
	  			String relto= "MCH";
			 	   if(action.equals("generalMaintMould_getData.balgenmain"))
			 		  relto= "MLD";
	  			//String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentRpt", "EqpQuery");
	  			//String tableModel = (String) httpSession.getAttribute("genMaintainanceColModel");
	 			JSONObject tblJSONObj = UIUtils.getXlColModel(request,response); //JSONObject.fromString(tableModel);
	  			CommonFunctions.debugMsg("tblJSONObj"+tblJSONObj);
	  			String fromMonth = commonFilter.getFromMonth();
	  			String toMonth = commonFilter.getToMonth();
	  			
	  			
	  			String date   = "" ;
	  			tblJSONObj.put("title", "General Maintenance Report" + " - "+date);
	  			if( commonFilter.getMonwise() != null && commonFilter.getMonwise().equals("Y")){
	  				date   = fromMonth +"  -  "+ toMonth ;
	  			}
	  			else if(commonFilter.getMonwise() != null )
	  				date   = commonFilter.getFromDate() +"  -  "+ commonFilter.getToDate() ;
	  			
	  			tblJSONObj.put("title", "General Maintenance Report" + " - "+date);
	  			String format = ExcelUtils.getFormat(request);
	  			
	  			Workbook wb = plmTlGenmaintenanceService.genMaintainanaceExportExcel(commonFilter,tblJSONObj,format,relto);
	  			commonFilter.setFromRow(tmpFromRow);
	  			
	  			ExcelUtils.writeToResponse(response, wb, "GeneralMaintenanceReport", format);
	  			
	  		}
			/*for Functional Location*/
	 			else if(action.equals("functionalLoc.balgenmain")){
	 				
	 				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
	 				
	 				
	 				//functLocFieldNameBean.setFactory("cmbGmntFactory");
	 				functLocFieldNameBean.setSbu("cmbGmntFactoryid");
                    functLocFieldNameBean.setSection("cmbGmntSectionid");
	 				functLocFieldNameBean.setCell("cmbGmntLineid");
	 				functLocFieldNameBean.setMachine("cmbGmntMachineid");
	 				functLocFieldNameBean.setFunctionalLocId("cmbGmntFlid");
	 				
	 				//functLocFieldNameBean.setFactMandatory(true);
	 				//functLocFieldNameBean.setSectMandatory(true);
	 				functLocFieldNameBean.setCellMandatory(true);
	 				
	 				String relTo = request.getParameter("relTo");
	 				
	 				/*if("MLD".equals(relTo)){
	 					CommonFunctions.debugMsg("mld");
	 					functLocFieldNameBean.setMachMandatory(false);
	 				}
	 				else{
	 					CommonFunctions.debugMsg("else MACH");
	 					functLocFieldNameBean.setMachine("cmbGmntEquipmentid");
	 					functLocFieldNameBean.setMachMandatory(true);
	 				}
	 					*/
	 				FormModes formModes = (FormModes)httpSession.getAttribute("GeneralMainFormMode");
	 				
	 				//	formModes = FormModes.view;
	 				
	 				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
	 				
	 			}
				
	 		  else if( action.equals("generalMaintcreat_save.balgenmain")){
	 			// HttpSession httpSession = request.getSession(false);
	 			 BAL_GeneralMaintainanceBean generalMaintainanceBean = (BAL_GeneralMaintainanceBean) httpSession.getAttribute("generalMaintainanceBean");
	 			 
	 			   
	 			 if(generalMaintainanceBean.getFormMode().equals(FormModeConsts.view) ){
	 				 
	 				PrintWriter out = response.getWriter();
	 				JSONObject err = new JSONObject();
	 				err.put("tpmException","Cannot Save in view Mode ");
	 				out.print(err.toString());
	 			 }
	 			 else
	 				 saveGenMain(request,response,generalMaintainanceBean);
			     }
	 		
	 		 else if( action.equals("generalMaintcreat_delete.balgenmain")){
		 		    
	 			 System.out.println("inside delete action");
	 			
	 			 BAL_GeneralMaintainanceBean generalMaintainanceBean = (BAL_GeneralMaintainanceBean) httpSession.getAttribute("generalMaintainanceBean");
	 			System.out.println("wodelete function :"+ generalMaintainanceBean.getFormMode());
	 			if(generalMaintainanceBean.getFormMode().equals(FormModeConsts.view) ){
	  				PrintWriter out = response.getWriter();
	  				JSONObject err = new JSONObject();
	  				err.put("tpmException","Cannot Delete in view Mode ");
	  				out.print(err.toString());
	  			 }
	 			 else{
	 				 deleteGenMain(request,response,generalMaintainanceBean);
	 			 }
			    }
	 		
	 		 else if( action.equals("subloss_input.balgenmain") ){	
				  
				  CommonFunctions.debugMsg("INPUT ACTION");
				  RequestDispatcher rd = request.getRequestDispatcher("/pages/SetupAdjSublossGrid.jsp"); 
				  rd.forward(request, response); 					 
			   } 
	 		else if(action.equals("subloss_getCol.balgenmain")){
			    PrintWriter out = response.getWriter();
			    out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "colModelSubLoss")); 
		   }
	 		else if(action.equals("subloss_getData.balgenmain")){
	 			CommonFunctions.debugMsg("getData" +plmTlGenmaintenanceService);
	 			 PrintWriter out = response.getWriter();
	 			 String refDocId = request.getParameter("refDocId");
	 			 List<String []> subLoss  = plmTlGenmaintenanceService.getSetupAdjSubLoss(refDocId);
	 			 JSONObject subLossdata = UIUtils.convertToJqGridTableObject(subLoss,request,0,0);
			 	 out.println(subLossdata);
		    }
		    else if(action.equals("SubLoss_del.balgenmain")){
		    	String keyId = request.getParameter("keyId");	
		    	PrintWriter out = response.getWriter();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit = new BAL_BdmTlSetupadjsplit();
		    		if(UIUtils.isValidKeyId(keyId))
		    			bdmTlSetupadjsplit.setSupsKeyid(keyId);
		    		
		    		try{						
		    			bdmTlSetupadjsplit =plmTlGenmaintenanceService.deleteSubLoss(bdmTlSetupadjsplit);
		    			
		    			JSONObject successData = new JSONObject();
		    			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
						
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);	
						returnData.put("formClear",true);
						out.print(returnData.toString()); 
						
		    		}catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GeneralmaintenanceException");
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
	 		   //for Combo box fill 
	 		  if( action.equals("combo_GmntTrade.balgenmain")){
	 			  System.out.println("GmntTrade combo");
	 				try {
	 					comboFilter = UIUtils.fillComboFilter(request);
	 					List<ComboBox> GmntTradeid  = plmTlGenmaintenanceService.getGmntTradeCombo("",comboFilter);
	 					UIUtils.writeComboBox(response, GmntTradeid,comboFilter);
	 				} catch (Exception e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 		   }
	 		  	else if( action.equals("combo_Gmntcompletedby.balgenmain"))
	 			{
	 			  System.out.println("completed b y combo");
	 			  try {
	 					comboFilter = UIUtils.fillComboFilter(request);
	 					List<ComboBox>  Gmntcmpltdbybyid = plmTlGenmaintenanceService.getGmntcompletedbyCombo("",comboFilter);
	 					UIUtils.writeComboBox(response, Gmntcmpltdbybyid,comboFilter);
	 				  } 
	 			  catch (Exception e) 
	 				  {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				  }
	 			}
	 		 
	 		 else if( action.equals("combo_GmntReportedby.balgenmain"))
				{
	 			 String username=UIUtils.getLoginUser(request).getUsrm_ccno();
				  System.out.println("revised  combo");
				  try {
					  	comboFilter = UIUtils.fillComboFilter(request);
						List<ComboBox>  Gmntreptdbyid = plmTlGenmaintenanceService.getGmntreptdbyidCombo("",comboFilter);
						UIUtils.writeComboBox(response, Gmntreptdbyid,comboFilter);
					  } 
				  catch (Exception e) 
					  {
						// TODO Auto-generated catch block
						e.printStackTrace();
					  }
				}
			else if( action.equals("combo_GmntShift.balgenmain"))
			{
			  System.out.println("shift  combo");
			  try {
				  	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  gmntshift = plmTlGenmaintenanceService.getGmntshiftCombo("",comboFilter);
					UIUtils.writeComboBox(response, gmntshift,comboFilter);	
				  } 
			  catch (Exception e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			}
			else if( action.equals("combo_SubLossShift.balgenmain"))
			{
			  System.out.println("shift  combo");
			  try {
				  	String fromDate = request.getParameter("fromDate");
				  	String toDate = request.getParameter("toDate");
				  	String factId = request.getParameter("factId");
				  	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  gmntshift = plmTlGenmaintenanceService.getSubLossshiftCombo(fromDate,toDate,factId,comboFilter);
					UIUtils.writeComboBox(response, gmntshift,comboFilter);
				  } 
			  catch (Exception e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			}
			else if( action.equals("combo_SubLoss.balgenmain"))
			{
			  System.out.println("combo_SubLoss.balgenmain  combo");
			  try {
				  	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox>  SubLoss = plmTlGenmaintenanceService.getSubLossCombo("",comboFilter );
					UIUtils.writeComboBox(response, SubLoss,comboFilter );
				  } 
			  catch (Exception e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			}
			else if( action.equals("fill_shift.balgenmain"))
			{
				try {
					List<String > paramValues = new ArrayList<String>();
					paramValues.add(request.getParameter("flId"));
					paramValues.add(request.getParameter("sectId"));
					paramValues.add(request.getParameter("cellId"));
					String tim_e = request.getParameter("fromTime");
					System.out.println("tim_e  :"+tim_e);
					if(UIUtils.isValidKeyId(tim_e)){
					paramValues.add(request.getParameter("fromTime"));
					}
					else{
					String dateTime = CommonFunctions.dateTimeNow();
					System.out.println("dt timne "+dateTime.substring(12, 17));
					paramValues.add(dateTime.substring(12, 17));
					}
					
					//response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter(); 	
					String shift = plmTlGenmaintenanceService.getShift(paramValues);
					JSONObject shiftObj = new  JSONObject();
					shiftObj.put("shift",shift);
				    //System.out.println("shift  "+shiftObj);
			        out.print(shiftObj);					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if( action.equals("combo_GmntMouldid.balgenmain"))
			{
			  System.out.println("mould combo");
			  try {
				  	comboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> gmntmould = plmTlGenmaintenanceService.getGmntmouldCombo("",comboFilter);
					UIUtils.writeComboBox(response, gmntmould,comboFilter);
				  } 
			  catch (Exception e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			}
			else if(action.equals("combo_activitytype.balgenmain"))
			    {
			    	System.out.println("inside combo_relatedto general Maintainence");
			    	String from = request.getParameter("MldUnload");
			 	    PrintWriter out = response.getWriter();
			 	    if(UIUtils.isValidKeyId(from))
			 	    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectactivityTypeMldUnload"));
			 	    else
			 	    out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectactivityType"));
			    	System.out.println("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectactivityType"));
			    }
			else if(action.equals("combo_machinecondition.balgenmain"))
			    {
			    	System.out.println("inside combo_relatedto general Maintainence");
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectmachineCondition"));
			    	System.out.println("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectmachineCondition"));
			    }
			else if(action.equals("combo_GmntStatus.balgenmain"))
		    {
		    	System.out.println("inside combo_Status general Maintainence");
		 	    PrintWriter out = response.getWriter();
		    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectStatus"));
		    	System.out.println("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp", "selectStatus"));
		    }
 		    if (dispatchUrl != null)
			{
				
				RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
				rd.forward(request, response); 
				System.out.println(" response " + response + " " + dispatchUrl); 
			}		

	    }
	    private void setGmntBeanVal(BAL_PlmTlGenmaintenance plmTlGenmaintenance,BAL_GeneralMaintainanceBean generalMaintainanceBean) {
	    	System.out.println("setGmntBeanVal");
	    	if(plmTlGenmaintenance != null && UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntOccureddate() ))
			{
	    		System.out.println("bean occured date  "+plmTlGenmaintenance.getGmntOccureddate());
	    		System.out.println("bean workstart date  "+plmTlGenmaintenance.getGmntWostartdate());
	    		System.out.println("bean workend date  "+plmTlGenmaintenance.getGmntWoenddate());
	    		generalMaintainanceBean.setOccuredTime(plmTlGenmaintenance.getGmntOccureddate().substring(12, 17));
	    		plmTlGenmaintenance.setGmntOccureddate(plmTlGenmaintenance.getGmntOccureddate().substring(0, 11));
	    		System.out.println("bean occured date  "+plmTlGenmaintenance.getGmntOccureddate());
	    		System.out.println("bean occured Time  "+generalMaintainanceBean.getOccuredTime());
			}
	    	 if(plmTlGenmaintenance != null && UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWostartdate() ))
			{
	    		
	    		generalMaintainanceBean.setWorkstartTime(plmTlGenmaintenance.getGmntWostartdate().substring(12, 17));
	    		plmTlGenmaintenance.setGmntWostartdate(plmTlGenmaintenance.getGmntWostartdate().substring(0, 11));
	    		System.out.println("bean workstart date  "+plmTlGenmaintenance.getGmntWostartdate());
	    		System.out.println("bean workstart Time  "+generalMaintainanceBean.getWorkstartTime());
			}
	    	if(plmTlGenmaintenance != null && UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWoenddate() ))
			{
	    		
	    	
	    		generalMaintainanceBean.setWorkendTime(plmTlGenmaintenance.getGmntWoenddate().substring(12, 17));
	    		plmTlGenmaintenance.setGmntWoenddate(plmTlGenmaintenance.getGmntWoenddate().substring(0, 11));
	    		System.out.println("bean workend date  "+plmTlGenmaintenance.getGmntWoenddate());
	    		System.out.println("bean workend Time  "+generalMaintainanceBean.getWorkendTime());
			}
			
		}
	
		/*private void saveGenMain(HttpServletRequest request, HttpServletResponse response,GeneralMaintainanceBean generalMaintainanceBean  ) throws IOException{
			//assmid
			System.out.println("inside SAve GEnManitenance");
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
	
				try{
			    	if( httpSession != null && user != null)
			    	{	
			    		
			    		
			    		
			    		PlmTlGenmaintenance existPlmTlGenmaintenance = (PlmTlGenmaintenance)httpSession.getAttribute("plmTlGenmaintenance_Servlet"); 
			    		PlmTlGenmaintenance newPlmTlGenmaintenance = new PlmTlGenmaintenance();
			    		WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderGM");
			   
			    		newPlmTlGenmaintenance.setGmntCreatedby(user.getUsrm_ccno());
						newPlmTlGenmaintenance =(PlmTlGenmaintenance)UIUtils.setBeanProperties((Object)newPlmTlGenmaintenance,request);
						
						generalMaintainanceBean =(GeneralMaintainanceBean) UIUtils.setBeanProperties((Object)generalMaintainanceBean,request);
						String SetupandLoss = request.getParameter("SetupandLoss");
						if(UIUtils.isValidKeyId(SetupandLoss))
						{
							CommonFunctions.debugMsg(SetupandLoss);
							JSONArray jsonArray = JSONArray.fromString(SetupandLoss);		    	
					    	BdmTlSetupadjsplit bdmTlSetupadjsplit = new BdmTlSetupadjsplit();
					    	List<BdmTlSetupadjsplit> setupadj = (List<BdmTlSetupadjsplit>) UIUtils.convertJSONArrToList(bdmTlSetupadjsplit, jsonArray);
					    	if(setupadj != null)
					    	{
					    		newPlmTlGenmaintenance.setBdmTlSetupadjsplit(setupadj);					    		
					    	}					    	
						}
						/*if((newPlmTlGenmaintenance.getGmntKeyid())!= null  && actionpassed .equals("view"))
						{
							System.out.println("keyid there  :"+newPlmTlGenmaintenance.getGmntKeyid());
							JSONObject err = new JSONObject();
							err.put("tpmException","Cannot save in View Mode");
							out.print(err.toString());
						}*/
						String saveMsg ;
					//	generalMaintainanceBean =(GeneralMaintainanceBean) UIUtils.setBeanProperties((Object)generalMaintainanceBean,request);
			    		
			    		//try{
			    		/*	boolean insert = true;
							if(  newBdmTlMst.getBdmsKeyid() == null || newBdmTlMst.getBdmsKeyid().substring(0,3).equals("SFT"))
							{	
								existBdmTlMst =	breakDownService.create(newBdmTlMst,existBdmTlMst,bdFormBean);
							}	
							else
							{
								insert = false;
								existBdmTlMst = breakDownService.update(newBdmTlMst,existBdmTlMst,bdFormBean,womTlWomst);						
							}
							*/
			    			/*	if(!UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntKeyid()) )
						{	
							CommonFunctions.debugMsg("inside if create");
							existPlmTlGenmaintenance =	plmTlGenmaintenanceService.create(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean);
							saveMsg = "Data Saved Successfully";
						}	
						else{
							CommonFunctions.debugMsg("inside Update in servlet"+existPlmTlGenmaintenance.getGmntKeyid());
							generalMaintainanceBean.setFormMode("update");
							existPlmTlGenmaintenance = plmTlGenmaintenanceService.update(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean,womTlWomst);
							saveMsg = "Data Updated Successfully";/*changed on 8-jun*//*
						}
						System.out.println("Target date   :"+newPlmTlGenmaintenance.getGmntTargetdate());
						String fromWO = (String) httpSession.getAttribute("fromWO");
							
						JSONObject returnData = new JSONObject();
						JSONObject successData = new JSONObject();
						if(UIUtils.isValidKeyId(fromWO))
							successData.put("backTo", fromWO);
						successData.put("keyId", newPlmTlGenmaintenance.getGmntKeyid());
						successData.put("msg", saveMsg);
						returnData.put("successData",successData);
						returnData.put("formClear",false);
						
						/*
						String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
						if(UIUtils.isValidKeyId(backTo))
							returnData.put(WOConstants.backTo,backTo);*/
					/*	out.print(returnData.toString());
						//out.print()	
						System.out.println("end of save");
			    		
			    	 	
			}catch(ValidationExceptions e)
			{
				
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"GeneralmaintenanceException");
				errMessage.put("fromMode",generalMaintainanceBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "GeneralmaintenanceException");
				out.print(errMessage.toString());					
				
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("Testing........");
			    e.printStackTrace();	//System.out.println("gete. " + e.printStackTrace());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			
			}
	
				}*/
			    	private void saveGenMain(HttpServletRequest request, HttpServletResponse response,BAL_GeneralMaintainanceBean generalMaintainanceBean  ) throws IOException{
						//assmid
						System.out.println("inside SAve GEnManitenance");
						HttpSession httpSession = request.getSession(false);
						ServletOutputStream out = response.getOutputStream();
						AdmTlUsermst user = UIUtils.getLoginUser(request);
						String orderType=request.getParameter("orderType");
						CommonFunctions.debugMsg("Order Type......"+orderType);
				
							try{
						    	if( httpSession != null && user != null)
						    	{	
						    		
						    		
						    		
						    		BAL_PlmTlGenmaintenance existPlmTlGenmaintenance = (BAL_PlmTlGenmaintenance)httpSession.getAttribute("plmTlGenmaintenance_Servlet"); 
						    		BAL_PlmTlGenmaintenance newPlmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
						    		WomTlWomst womTlWomst = (WomTlWomst) httpSession.getAttribute("WorkOrderGM");
						    		newPlmTlGenmaintenance.setGmntCreatedby(user.getUsrm_ccno());
									newPlmTlGenmaintenance =(BAL_PlmTlGenmaintenance)UIUtils.setBeanProperties((Object)newPlmTlGenmaintenance,request);
									
									generalMaintainanceBean =(BAL_GeneralMaintainanceBean) UIUtils.setBeanProperties((Object)generalMaintainanceBean,request);
									System.out.println("RAW PARAM cmbGmntStatus = " + request.getParameter("cmbGmntStatus"));
									System.out.println("RAW PARAM hdngmntStatus = " + request.getParameter("hdngmntStatus"));
									String SetupandLoss = request.getParameter("SetupandLoss");
									if(UIUtils.isValidKeyId(SetupandLoss))
									{
										CommonFunctions.debugMsg(SetupandLoss);
										JSONArray jsonArray = JSONArray.fromString(SetupandLoss);		    	
								    	BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit = new BAL_BdmTlSetupadjsplit();
								    	List<BAL_BdmTlSetupadjsplit> setupadj = (List<BAL_BdmTlSetupadjsplit>) UIUtils.convertJSONArrToList(bdmTlSetupadjsplit, jsonArray);
								    	if(setupadj != null)
								    	{
								    		newPlmTlGenmaintenance.setBdmTlSetupadjsplit(setupadj);					    		
								    	}					    	
									}
									/*if((newPlmTlGenmaintenance.getGmntKeyid())!= null  && actionpassed .equals("view"))
									{
										System.out.println("keyid there  :"+newPlmTlGenmaintenance.getGmntKeyid());
										JSONObject err = new JSONObject();
										err.put("tpmException","Cannot save in View Mode");
										out.print(err.toString());
									}*/
									String saveMsg ;
									
									if( ! UIUtils.isValidKeyId(newPlmTlGenmaintenance.getGmntKeyid()) )
									{	
							    		newPlmTlGenmaintenance.setGmntOrderType(orderType);
							    		System.out.println("Status in servlet create***** "+newPlmTlGenmaintenance.getGmntStatus());
										existPlmTlGenmaintenance =	plmTlGenmaintenanceService.create(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean);
										saveMsg = "Data Saved Successfully";
									}	
									else{
										generalMaintainanceBean.setFormMode("update");
										System.out.println("Status in servlet create***** "+newPlmTlGenmaintenance.getGmntStatus());
										//CommonFunctions.debugMsg("order type in servlet...."+existPlmTlGenmaintenance.getGmntOrderType());
										
										if(orderType!=null){
										CommonFunctions.debugMsg("update.. in if"+orderType);

										newPlmTlGenmaintenance.setGmntOrderType(orderType);
										}
										else{
										newPlmTlGenmaintenance.setGmntOrderType(existPlmTlGenmaintenance.getGmntOrderType());
										
										CommonFunctions.debugMsg("update.."+existPlmTlGenmaintenance.getGmntOrderType());
										}
										//existPlmTlGenmaintenance.setGmntOrderType(orderType);
										existPlmTlGenmaintenance = plmTlGenmaintenanceService.update(newPlmTlGenmaintenance,existPlmTlGenmaintenance,generalMaintainanceBean,womTlWomst);
										saveMsg = "Data updated  Successfully";/*changed on 8-jun*/
									}
									System.out.println("Target date   :"+newPlmTlGenmaintenance.getGmntTargetdate());
									String fromWO = (String) httpSession.getAttribute("fromWO");
										
									JSONObject returnData = new JSONObject();
									JSONObject successData = new JSONObject();
									if(UIUtils.isValidKeyId(fromWO))
										successData.put("backTo", fromWO);
									successData.put("keyId", newPlmTlGenmaintenance.getGmntKeyid());
									successData.put("msg", saveMsg);
									returnData.put("successData",successData);
									returnData.put("formClear",false);
									
									/*
									String backTo = (String) httpSession.getAttribute(WOConstants.backTo);
									if(UIUtils.isValidKeyId(backTo))
										returnData.put(WOConstants.backTo,backTo);*/
									out.print(returnData.toString());
									//out.print()	
									System.out.println("end of save");
						    	}	
						    	 	
						}catch(ValidationExceptions e)
						{
							
							JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"GeneralmaintenanceException");
							errMessage.put("fromMode",generalMaintainanceBean.getFormActionMode());
							out.print(errMessage.toString());
							
						}catch(BusinessApplicationExceptions e)
						{
							JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "GeneralmaintenanceException");
							out.print(errMessage.toString());					
							
						}catch(Exception e)
						{
							e.printStackTrace();//System.out.println("gete. " + e.getMessage());
							JSONObject err = new JSONObject();
							err.put("tpmException", "Data Not Saved");
							out.print(err.toString());
						}
			    	}
	    //for delete
		 
		 private void deleteGenMain(HttpServletRequest request, HttpServletResponse response,BAL_GeneralMaintainanceBean generalMaintainanceBean  ) throws IOException{
				//assmid
		    	System.out.println("inside delete GenMain");
		    	HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	System.out.println("deltee mode  " );
		    	if( httpSession != null && user != null)
		    	{	
		    		System.out.println("inside delete Gmai");
		    		String AssemblyId= (String) httpSession.getAttribute("jhclitassemblyID");
		    		BAL_PlmTlGenmaintenance existPlmTlGenmaintenance = (BAL_PlmTlGenmaintenance)httpSession.getAttribute("cliTlStandards"); 
		    		BAL_PlmTlGenmaintenance newPlmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
		    		
		    		newPlmTlGenmaintenance.setGmntCreatedby(user.getUsrm_ccno());
					
					String KeyID = (String) httpSession.getAttribute("jhclitId");
					System.out.println("KEY ID   :-"+KeyID);
					newPlmTlGenmaintenance.setGmntKeyid(KeyID);//KeyId
					
					newPlmTlGenmaintenance =(BAL_PlmTlGenmaintenance)UIUtils.setBeanProperties((Object)newPlmTlGenmaintenance,request);
					
					generalMaintainanceBean =(BAL_GeneralMaintainanceBean) UIUtils.setBeanProperties((Object)generalMaintainanceBean,request);
					
					try{
						System.out.println("  KEYID " +  newPlmTlGenmaintenance.getGmntKeyid());
						System.out.println("Delete Function called");
						existPlmTlGenmaintenance =	plmTlGenmaintenanceService.delete(newPlmTlGenmaintenance);
						
						
						httpSession.setAttribute(existPlmTlGenmaintenance.getGmntKeyid(), existPlmTlGenmaintenance);
						httpSession.setAttribute("PlmTlGenmaintenance", existPlmTlGenmaintenance);
						String formBeanIdentifier = "generalMaintainanceBean"+generalMaintainanceBean.getFormActionMode();
						httpSession.setAttribute(formBeanIdentifier,generalMaintainanceBean);
								
						JSONObject mode = new JSONObject();
						mode.put("formMode",generalMaintainanceBean.getFormActionMode());
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("GmntKeyids",existPlmTlGenmaintenance.getGmntKeyid() );
						persistentData.put("fromBean", formBeanIdentifier);
						JSONObject forwardData = new JSONObject();
						forwardData.put("GmntKeyids",existPlmTlGenmaintenance.getGmntKeyid() );
						mode.put("forwardData",forwardData);
						mode.put("persistentData", persistentData);
						JSONObject successData = new JSONObject();
						System.out.println("After the IF Loop in mssg bos");
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
						//successData.put("msg","Data Deleted Successfully");
						successData.put("FormActionMode",generalMaintainanceBean.getFormActionMode() );
						successData.put("GmntKeyid", existPlmTlGenmaintenance.getGmntKeyid());
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);	
						returnData.put("formClear",true);
						out.print(returnData.toString()); 
						
						/*JSONObject err = new JSONObject(); 
						err.put("tpmException","Data Deleted Successfully");
						err.put("formClear",true);
						out.print(err.toString());*/
						//out.print()	
						System.out.println("end of delete");
						
					}catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GeneralmaintenanceException");
						errMessage.put("fromMode",generalMaintainanceBean.getFormActionMode());
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
		 private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
				HttpSession httpSession = request.getSession(false);
				
				
				CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
				if( commonFilter != null && ! createNew ){
					FilterValues.setPaginationParams(request,commonFilter);
				}	
				else{
					commonFilter =  new CommonFilter();
					
					commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
					commonFilter = 	FilterValues.getBDRelated(request, commonFilter);
					commonFilter.setViewClick('Y');
					httpSession.removeAttribute(beanIdentifier);
					httpSession.setAttribute(beanIdentifier, commonFilter);
				}
				
				return commonFilter;
			}
		 private JSONObject getTableModelSapInfo(List<String[]> kpivReportList,CommonFilter commonFilter
					) {
			 CommonFunctions.debugMsg("Inside gettable Model");
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();
				String [] colHeader = kpivReportList.get(0);	
				jqGridTableModel.getRowHeaders().add(colHeader);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setRowNumbers(true);	
				//jqGridTableModel.setEnableFilter(false);
				
				for(int i =0; i <colHeader.length; i++)
				{			
					JqGridColModel jqGridColModel = new JqGridColModel();
					jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
					jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					if(i==9){
						jqGridColModel.setHidden(true);
					}
				   if(i==10){
						jqGridColModel.setHidden(true);
					}
							
					jqGridColModel.setWidth( 30);				
					jqGridColModel.setAlign("left");
					if(i==6){
						//jqGridColModel.setEditable(false);
						jqGridColModel.setAlign("center");	
				    	jqGridColModel.setFormatter("quantityFormatter");
						jqGridColModel.setWidth(80);
					}
					
					CommonFunctions.debugMsg("SAOMAintOrdercolheader  "+i+" : "+colHeader[i]);//if i==0
					if(i==0){
						jqGridColModel.setWidth(40);				
						jqGridColModel.setAlign("left");
						jqGridColModel.setHidden(true);
					}
					
					else if(i==1){
						jqGridColModel.setWidth(50);				
						jqGridColModel.setAlign("center");
						jqGridColModel.setFormatter("txtSelect");
					}
					else if(i==2 || i==4 || i==5){					
						jqGridColModel.setWidth(100);				
						jqGridColModel.setAlign("left");
					}
					
					else if(i==3){
						jqGridColModel.setWidth(230);				
						jqGridColModel.setAlign("left");
					}							
					
					else if(i>6)
					{
						jqGridColModel.setWidth(80);				
						jqGridColModel.setAlign("right");					
					}
					
					
				jqGridTableModel.getColModel().add(jqGridColModel);
			}

			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 return tableModel;
		 }
	 }