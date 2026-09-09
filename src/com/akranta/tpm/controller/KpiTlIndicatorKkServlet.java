package com.akranta.tpm.controller;
/* Modified By :Dhanalakshmi.R
 * For:KPIProductionDeptLink
 * Date :8.12.12
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.util.SystemOutLogger;
import org.json.simple.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.KpiTlIndicatorKkBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.service.KpiTlIndicatorKkService;
import com.akranta.tpm.service.impl.KpiTlIndicatorKkServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

public class KpiTlIndicatorKkServlet extends HttpServlet {	
	private static final long serialVersionUID = -8070953412956046800L;
	KpiTlIndicatorKkService kpiTlIndicatorKkService;
	String kpid;
	String flId;
	String loginElementid;
	public KpiTlIndicatorKkServlet()
	{
		super();	       
        /*try {
        	kpiTlIndicatorKkService = new KpiTlIndicatorKkServiceImpl();
        }catch (Exception e) {			
		}
		//Constructor
		 * 
		 */
	}	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception {        
    	HttpSession httpSession = request.getSession(false);
		response.setContentType("application/json");		
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("request.getSession():"+request.getMethod());
		CommonMessage.debugMsg("action:"+action);
		ComboFilter comboFilter = new ComboFilter();
		loginElementid=CommonFunctions.getLoginElementId(request);
		CommonMessage.debugMsg("loginElementid::"+loginElementid);
		CommonMessage.debugMsg("loginElementid::"+loginElementid.length());
		try {
			kpiTlIndicatorKkService = (KpiTlIndicatorKkServiceImpl)UIUtils.getServiceObject(request,"KpiTlIndicatorKkServiceImpl");
			kpiTlIndicatorKkService.KpiTlIndicatorKkServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		if(action.equals("load_view.keyPerInd"))
		{		
			loadIndicator(request, response,"KK");
		}
		else if(action.equals("newIndicatorPM_view.keyPerInd"))
		{		
			UIUtils.forwardRequest(request, response, "/pages/KpiNewIndicatorReport.jsp");
		}
		else if(action.equals("newIndicatorPM_getCol.keyPerInd"))
		{		
			kpiReportGetCol(request,response);
		}
		else if(action.equals("newIndicatorPM_getData.keyPerInd"))
		{		
			kpiReportGetData(request,response);
		}
		else if(action.equals("newIndicatorPMModify_input.keyPerInd"))
		{		
			UIUtils.forwardRequest(request, response, "/pages/KPI/NewIndicator.jsp");
		}
		///
		else if (action.equals("combo_impact.keyPerInd")){
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "Impact"));
		}
		else if (action.equals("combo_Datatype.keyPerInd")){
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "Datatype"));	
		}	
		else if (action.equals("combo_SourceofKPI.keyPerInd")){
	 	    PrintWriter outl = response.getWriter();
	 	    
	 	    //outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "SourceofKPI"));
//	    	//CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "SourceofKPI"));		
		}	
		else if (action.equals("combo_Frequency1.keyPerInd")){
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "Frequency1"));
	 	    CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "Frequency1"));		
		}	
		else if (action.equals("combo_TypofInput.keyPerInd")){
			//CommonMessage.debugMsg("inside combo_TypofInput.keyPerInd");
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "TypofInput"));
	    	//CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "TypofInput"));		
		}
		else if (action.equals("combo_CalcType.keyPerInd")){
			//CommonMessage.debugMsg("inside combo_TypofInput.keyPerInd");
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "CalcType"));
	    	//CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "TypofInput"));		
		}
		else if (action.equals("combo_indicatorType.keyPerInd")){
			//CommonMessage.debugMsg("inside combo_indicatorType.keyPerInd");
	 	    PrintWriter outl = response.getWriter();
	 	    outl.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "IndicatorType"));
	    	//CommonMessage.debugMsg("SELECT   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.NewIndicator", "IndicatorType"));		
		}	
		/////
		else if(action.equals("loadDm_view.keyPerInd")|| action.equals("IndicatorGridDM_view.keyPerInd"))
		{		
			loadIndicator(request, response,"DM");
		}
		
		else if(action.equals("loadEt_view.keyPerInd")|| action.equals("IndicatorGridET_view.keyPerInd"))
		{		
			loadIndicator(request, response,"ET");
		}
		
		else if(action.equals("loadJh_view.keyPerInd")|| action.equals("IndicatorGridJH_view.keyPerInd"))
		{		
			loadIndicator(request, response,"JH");
		}
		
		else if(action.equals("loadKk_view.keyPerInd") || action.equals("IndicatorGridKK_view.keyPerInd"))
		{		
			loadIndicator(request, response,"KK");
		}
		
		else if(action.equals("loadPm_view.keyPerInd")|| action.equals("IndicatorGridPM_view.keyPerInd"))
		{		
			loadIndicator(request, response,"PM");
		}
				
		else if(action.equals("loadQm_view.keyPerInd")|| action.equals("IndicatorGridQM_view.keyPerInd"))
		{		
			loadIndicator(request, response,"QM");
		}
		
		else if(action.equals("loadShe_view.keyPerInd")|| action.equals("IndicatorGridSHE_view.keyPerInd"))
		{		
			loadIndicator(request, response,"SHE");
		}
		
		else if(action.equals("loadOtpm_view.keyPerInd") || action.equals("IndicatorGridOTPM_view.keyPerInd"))
		{		
			loadIndicator(request, response,"OTPM");
		}
		
		else if( action.equals("loadval.keyPerInd") )
		{		
			PrintWriter out1 = response.getWriter();
			LoadkeyIndTree(request, response,out1);
		}
		
		else if(action.equals("keyInd_input.keyPerInd")) 
		{		
			String Loadpopup = request.getParameter("Loadpopup");
		
			request.setAttribute("Loadpopup", Loadpopup);
			
			CommonMessage.debugMsg("Loadpopup Now Testing ..........."+Loadpopup);
			LoadkeyIndEntry(request, response,httpSession);			
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/KPI/KeyPerIndicatorKkPopup.jsp"); 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/KPI/NewIndicator.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("combo_InputType.keyPerInd"))
	    {	
			PrintWriter out1 = response.getWriter();
	    	out1.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "INPUTTYPE"));
	    }
		else if(action.equals("combo_InputEntry.keyPerInd"))
	    {	
			PrintWriter out1 = response.getWriter();
	    	out1.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "INPUTENTRY"));
	    }
		else if(action.equals("combo_Frequency.keyPerInd"))
	    {	
			PrintWriter out1 = response.getWriter();
			CommonMessage.debugMsg("FREQUENCY" +UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "FREQUENCY"));
	    	out1.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "FREQUENCY"));
	    }	
		else if(action.equals("combo_ManualCaltype.keyPerInd"))
	    {	
			PrintWriter out1 = response.getWriter();
	    	out1.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "MANUALCALTYPE"));
	    }
		else if(action.equals("combo_Excelname.keyPerInd"))
	    {	
			PrintWriter out1 = response.getWriter();
	    	out1.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.KpiTlIndicatorKkProp", "EXCELNAME"));
	    }
		else if( action.equals("combo_keyPerformParent.keyPerInd"))
		{	
			String pillarId=request.getParameter("pillarId");
			String locationId=request.getParameter("locationId");
			CommonFilter commonFilter = new CommonFilter();
			comboFilter = UIUtils.fillComboFilter(request);
			commonFilter.setPillarWise(pillarId);
			commonFilter.setLossId(locationId);
			List<ComboBox> deptModel = kpiTlIndicatorKkService.getParentComboList(commonFilter,comboFilter);
			UIUtils.writeComboBox(response,deptModel,comboFilter);
		}
		else if( action.equals("combo_Uom.keyPerInd"))
		{	
			comboFilter = UIUtils.fillComboFilter(request);
			List<ComboBox> deptModel = kpiTlIndicatorKkService.getUomComboList(comboFilter);
			UIUtils.writeComboBox(response,deptModel,comboFilter);
		}
		
		else if( action.equals("combo_Costarea.keyPerInd"))
		{	
			comboFilter = UIUtils.fillComboFilter(request);
			List<ComboBox> deptModel = kpiTlIndicatorKkService.getCostAreaComboList(comboFilter);
			UIUtils.writeComboBox(response,deptModel,comboFilter);
		}	
		
		else if( action.equals("combo_Dept.keyPerInd"))
		{	
			comboFilter = UIUtils.fillComboFilter(request);
			List<ComboBox> deptModel = kpiTlIndicatorKkService.getDeptComboList(comboFilter);
			UIUtils.writeComboBox(response,deptModel,comboFilter);
		}
		
		else if( action.equals("keyInd_save.keyPerInd"))
		{	
			KpiTlIndicatorKkBean KpiTlIndicatorKkBean = new KpiTlIndicatorKkBean();
			//savekeyInd(request,response,KpiTlIndicatorKkBean);
			saveIndicator(request,response);
	    }
		else if(action.equals("newIndicatorPMModify_save.keyPerInd"))
		{
			//KpiTlIndicatorKkBean KpiTlIndicatorKkBean = new KpiTlIndicatorKkBean();
			saveIndicator(request,response);
		}
		
		else if(action.equals("keyInd_delete.keyPerInd"))
		{
			PrintWriter out1 = response.getWriter();
			deletekeyInd(request,response,out1);
		}
		else if(action.equals("KPIProdflid_delete.keyPerInd"))
		{
			PrintWriter out1 = response.getWriter();
			deleteKPIProdflid(request,response,out1);
		}
		
		else if(action.equals("keyIndLevel_validate.keyPerInd"))
		{
			PrintWriter out1 = response.getWriter();
			validatekeyInd(request,response,out1);			
		}
		
		else if( action.equals("searchnode.keyPerInd") )
		{	
			PrintWriter out1 = response.getWriter();
			searchkeyInd(request,response,httpSession,out1);
		}
		
		else if(action.equals("keyIndllevelDel_validate.keyPerInd"))
		{
			PrintWriter out1 = response.getWriter();
			validateDelkeyInd(request,response,out1);			
		}
		else if(action.equals("KPIProdFact_input.keyPerInd")||action.equals("KPIMaintenanceFact_input.keyPerInd")||action.equals("KPISafetyFact_input.keyPerInd")||
				action.equals("KPITrainingFact_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		else if(action.equals("KPIQualityFact_input.keyPerInd")||action.equals("KPIOfficeFact_input.keyPerInd")||
				action.equals("KPIResearchDevelopmentFact_input.keyPerInd")||action.equals("KPIJHFact_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		else if(action.equals("KPIProdSect_input.keyPerInd")||action.equals("KPIMaintenanceSect_input.keyPerInd")||action.equals("KPISafetySect_input.keyPerInd")||
				action.equals("KPITrainingSect_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		else if(action.equals("KPIQualitySect_input.keyPerInd")||action.equals("KPIOfficeSect_input.keyPerInd")||
				action.equals("KPIResearchDevelopmentSect_input.keyPerInd")||action.equals("KPIJHSect_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		/*else if(action.equals("KPIProdActiveInactive_input.keyPerInd")){
			 CommonMessage.debugMsg("Inside the Input");
			 ActiveInactiveKPIAction(request,response,action,httpSession);
		}*/
	/*	else if(action.equals("KPIProdActiveInactive_getCol.keyPerInd")){
			getActInacColAction(request,response,httpSession);
		}*/
	
		else if(action.equals("KPIProdCell_input.keyPerInd")||action.equals("KPIProdflid_input.keyPerInd")||action.equals("KPIMaintenanceCell_input.keyPerInd")||action.equals("KPISafetyCell_input.keyPerInd")||
				action.equals("KPITrainingCell_input.keyPerInd")||action.equals("KPIProdActiveInactive_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		else if(action.equals("KPIQualityCell_input.keyPerInd")||action.equals("KPIOfficeCell_input.keyPerInd")||
				action.equals("KPIResearchDevelopmentCell_input.keyPerInd")||action.equals("KPIJHCell_input.keyPerInd"))
		{
			inputAction(request,response,action,httpSession);
		}
		else if(action.equals("KPIProdFact_getCol.keyPerInd")||action.equals("KPIMaintenanceFact_getCol.keyPerInd")||action.equals("KPISafetyFact_getCol.keyPerInd")||
				action.equals("KPITrainingFact_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualityFact_getCol.keyPerInd")||action.equals("KPIOfficeFact_getCol.keyPerInd")||
		action.equals("KPIResearchDevelopmentFact_getCol.keyPerInd")||action.equals("KPIJHFact_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdSect_getCol.keyPerInd")||action.equals("KPIMaintenanceSect_getCol.keyPerInd")||action.equals("KPISafetySect_getCol.keyPerInd")||
				action.equals("KPITrainingSect_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualitySect_getCol.keyPerInd")||action.equals("KPIOfficeSect_getCol.keyPerInd")||
				action.equals("KPIResearchDevelopmentSect_getCol.keyPerInd")||action.equals("KPIJHSect_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdCell_getCol.keyPerInd")||action.equals("KPIMaintenanceCell_getCol.keyPerInd")||action.equals("KPISafetyCell_getCol.keyPerInd")||
				action.equals("KPITrainingCell_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
			//getColforkk(request,response,httpSession);
		}
		else if(action.equals("KPIProdflid_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}			
		else if(action.equals("KPIQualityCell_getCol.keyPerInd")||action.equals("KPIOfficeCell_getCol.keyPerInd")||
		action.equals("KPIResearchDevelopmentCell_getCol.keyPerInd")||action.equals("KPIJHCell_getCol.keyPerInd"))
		{
			getColAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdFact_getData.keyPerInd")||action.equals("KPIMaintenanceFact_getData.keyPerInd")||action.equals("KPISafetyFact_getData.keyPerInd")||
				action.equals("KPITrainingFact_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualityFact_getData.keyPerInd")||action.equals("KPIOfficeFact_getData.keyPerInd")||
				action.equals("KPIResearchDevelopmentFact_getData.keyPerInd")||action.equals("KPIJHFact_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdSect_getData.keyPerInd")||action.equals("KPIMaintenanceSect_getData.keyPerInd")||action.equals("KPISafetySect_getData.keyPerInd")||
				action.equals("KPITrainingSect_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualitySect_getData.keyPerInd")||action.equals("KPIOfficeSect_getData.keyPerInd")||
				action.equals("KPIResearchDevelopmentSect_getData.keyPerInd")||action.equals("KPIJHSect_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdCell_getData.keyPerInd")||action.equals("KPIMaintenanceCell_getData.keyPerInd")||action.equals("KPISafetyCell_getData.keyPerInd")||
				action.equals("KPITrainingCell_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}else if(action.equals("KPIProdflid_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualityCell_getData.keyPerInd")||action.equals("KPIOfficeCell_getData.keyPerInd")||
				action.equals("KPIResearchDevelopmentCell_getData.keyPerInd")||action.equals("KPIJHCell_getData.keyPerInd"))
		{
			getDataAction(request,response,httpSession);
		}
		
		//sriram 18-Nov-2025
		else if (action.equals("KPIProdflid_getExcel.keyPerInd")) {
			CommonMessage.debugMsg("KPIProdflid_getExcel.keyPerInd");
			getExcelAction(request, response, httpSession);
		}
		else if(action.equals("functionalLoc.keyPerInd"))
		{

			String drillLevel=request.getParameter("drillLevel");
			CommonMessage.debugMsg("drillLevel"+drillLevel);
			//CommonMessage.debugMsg("functionalLoc.keyPerInd drillLevel" + drillLevel);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setLocation("cmbKPILocationid");
			functLocFieldNameBean.setFactory("cmbKPIFactoryid");
			functLocFieldNameBean.setSection("cmbKPISectionid");
			functLocFieldNameBean.setCell("cmbKPICellid");
			functLocFieldNameBean.setMachine("cmbKPIMachine");
			//functLocFieldNameBean.setCompMandatory(true);
			if(UIUtils.isValidKeyId(drillLevel)&& drillLevel.equals("FCT"))
			{
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setFactMandatory(true);
			}
			else if(UIUtils.isValidKeyId(drillLevel)&& drillLevel.equals("LIN"))
			{
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setSectMandatory(true);
			}
			else if(UIUtils.isValidKeyId(drillLevel)&& drillLevel.equals("CEL"))
			{
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setCellMandatory(true);
			}
			else if(UIUtils.isValidKeyId(drillLevel)&& drillLevel.equals("flid"))
			{
				//CommonMessage.debugMsg("functionalLoc.keyPerInd drillLevel 123 " + drillLevel);
				functLocFieldNameBean.setSectDisable(false);
				functLocFieldNameBean.setCellDisable(false);
				functLocFieldNameBean.setMachDisable(false);
				functLocFieldNameBean.setSectMandatory(true);
			}			
			else
			{
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				//functLocFieldNameBean.setSectMandatory(true);
			}
		
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, FormModes.create  );
		}
		else if(action.equals("kpiIndicatorcmb.keyPerInd"))
		{
			try 
			{				
				String type=request.getParameter("type");
				String pillarId=request.getParameter("pillarId");
				CommonFilter commonFilter = new CommonFilter();
				commonFilter.setType(type);
				commonFilter.setPillarWise(pillarId);
				CommonMessage.debugMsg("pillarId:" + pillarId);
				ComboFilter comboFilter1=UIUtils.fillComboFilter(request);
				String flId = CommonFunctions.getLoginFlid(request);
				List<ComboBox> DetectedBy = kpiTlIndicatorKkService.getIndicatorComboList(commonFilter,flId,comboFilter1);
				UIUtils.writeComboBox(response, DetectedBy,comboFilter);
			}
		catch (Exception e)
			{
			}
		}
		else if(action.equals("KPIProdFact_getExcel.keyPerInd")||action.equals("KPIMaintenanceFact_getExcel.keyPerInd")||action.equals("KPISafetyFact_getExcel.keyPerInd")||
				action.equals("KPITrainingFact_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualityFact_getExcel.keyPerInd")||action.equals("KPIOfficeFact_getExcel.keyPerInd")||
				action.equals("KPIResearchDevelopmentFact_getExcel.keyPerInd")||action.equals("KPIJHFact_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		
		else if(action.equals("KPIProdSect_getExcel.keyPerInd")||action.equals("KPIMaintenanceSect_getExcel.keyPerInd")||
				action.equals("KPISafetySect_getExcel.keyPerInd")||action.equals("KPITrainingSect_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualitySect_getExcel.keyPerInd")||action.equals("KPIOfficeSect_getExcel.keyPerInd")||
				action.equals("KPIResearchDevelopmentSect_getExcel.keyPerInd")||action.equals("KPIJHSect_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdCell_getExcel.keyPerInd")||action.equals("KPIMaintenanceCell_getExcel.keyPerInd")||action.equals("KPISafetyCell_getExcel.keyPerInd")||
				action.equals("KPITrainingCell_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		else if(action.equals("KPIQualityCell_getExcel.keyPerInd")||action.equals("KPIOfficeCell_getExcel.keyPerInd")||
				action.equals("KPIResearchDevelopmentCell_getExcel.keyPerInd")||action.equals("KPIJHCell_getExcel.keyPerInd"))
		{
			getExcelAction(request,response,httpSession);
		}
		else if(action.equals("KPIProdFact_save.keyPerInd")||action.equals("KPIMaintenanceFact_save.keyPerInd")||action.equals("KPISafetyFact_save.keyPerInd")||
				action.equals("KPITrainingFact_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIQualityFact_save.keyPerInd")||action.equals("KPIOfficeFact_save.keyPerInd")||
				action.equals("KPIResearchDevelopmentFact_save.keyPerInd")||action.equals("KPIJHFact_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIProdSect_save.keyPerInd")||action.equals("KPIMaintenanceSect_save.keyPerInd")||action.equals("KPISafetySect_save.keyPerInd")||
				action.equals("KPITrainingSect_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIQualitySect_save.keyPerInd")||action.equals("KPIOfficeSect_save.keyPerInd")||
				action.equals("KPIResearchDevelopmentSect_save.keyPerInd")||action.equals("KPIJHSect_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIProdCell_save.keyPerInd")||action.equals("KPIMaintenanceCell_save.keyPerInd")||action.equals("KPISafetyCell_save.keyPerInd")||
				action.equals("KPITrainingCell_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIProdflid_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("KPIQualityCell_save.keyPerInd")||action.equals("KPIOfficeCell_save.keyPerInd")||
				action.equals("KPIResearchDevelopmentCell_save.keyPerInd")||action.equals("KPIJHCell_save.keyPerInd"))
		{
			SaveKPIIndicatorDeptLink(request, response);
		}
		else if(action.equals("keyIndLink_validate.keyPerInd"))
		{
			PrintWriter out1 = response.getWriter();
			validatekeyIndLink(request,response,out1);			
		}
		else if(action.equals("keyInActiveLink_validate.keyPerInd")){
			PrintWriter out3=response.getWriter();
			validateKeyInActiveLink(request,response,out3);
		}
		else if(action.equals("keyActiveLink_validate.keyPerInd")){
			PrintWriter out2=response.getWriter();
			validatekeyActiveLink(request,response,out2);
		}
    }
  
	private void saveIndicator(HttpServletRequest request,HttpServletResponse response) throws IOException {
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
		
    	if( httpSession != null && user != null)
    	{	    		
    		KpiTlIndicator newKpiTlIndicator = new KpiTlIndicator();
    		newKpiTlIndicator.setKinkCreatedby(user.getUsrm_ccno());			
    		newKpiTlIndicator =(KpiTlIndicator)UIUtils.setBeanProperties((Object)newKpiTlIndicator,request);    			
    		KpiTlIndicator existKpiTlIndicator = (KpiTlIndicator)httpSession.getAttribute("kpiTlIndicatorServlet");
    		String parentId = (String) httpSession.getAttribute("cmbKinkParentid ");
    		CommonMessage.debugMsg("parentId......"+parentId);
    		if(UIUtils.isValidKeyId(parentId)){
    			newKpiTlIndicator.setKinkParentid(parentId);    			
    		}
    		CommonMessage.debugMsg("parentId......"+newKpiTlIndicator.getKinkParentid());
			try{
				boolean insert = true;
				if( ! UIUtils.isValidKeyId( newKpiTlIndicator.getKinkKeyid() ))
				{						
					existKpiTlIndicator = kpiTlIndicatorKkService.createKPI(newKpiTlIndicator,existKpiTlIndicator);
				}
				else{
					CommonMessage.debugMsg("Update............");
					insert = false;
					existKpiTlIndicator = kpiTlIndicatorKkService.updateKPI(newKpiTlIndicator,existKpiTlIndicator);
				}				
			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("KinkKeyid",existKpiTlIndicator.getKinkKeyid() );
				
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
			 
			 if( insert){
				msgPropertyIdnt = "success-save";
			 }else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
		
			
			successData.put("KinkKeyid", existKpiTlIndicator.getKinkKeyid());
			JSONObject returnData = new JSONObject();
			returnData.put("formClear",false);
			returnData.put("successData", successData);		
			//httpSession.removeAttribute("genTlHolidaymstServlet");
			//httpSession.removeAttribute("genTlHolidaymstBean");
			
	    	PrintWriter  out = response.getWriter();
			out.print(returnData.toString());
			out.close();
			
			
		}catch(ValidationExceptions e)
		{			
			PrintWriter  out = response.getWriter();
			e.printStackTrace();
			//JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"KpiTlIndicatorKk");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "KpiTlIndicatorKk");
			//errMessage.put("formActionMode",genTlHolidaymstBean.getFormActionMode());
			out.print(errMessage.toString());
		
			
		}catch(BusinessApplicationExceptions e)
		{
			PrintWriter  out = response.getWriter();
			e.printStackTrace();
			CommonMessage.debugMsg(e);
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "KpiTlIndicatorKk");
			//errMessage.put("formActionMode",genTlHolidaymstBean.getFormActionMode());
			out.print(errMessage.toString());
		}
			catch(Exception e)
		{	
			PrintWriter  out = response.getWriter();
			
			JSONObject err = new JSONObject();
			if(e.toString().contains("UK_PILLARID_NAME")){
				
				
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "KpiTlIndicatorKk");
				out.print(errMessage.toString());
			}
			else{
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
			//err.put("tpmException","Holiday already Exists For Selected Date And Factory");
			/*JSONObject returnData = new JSONObject();
			returnData.put("successData", "alerady Exists");
			returnData.put("successData", returnData);*/
		}
    }	

		
	}
	private void kpiReportGetCol(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter=new  CommonFilter();
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		List<String[]> testGrid = null;
		try {
			//commonFilter = populateCommonFilter(request,"onlineTestCommonFilter",true);
			testGrid = kpiTlIndicatorKkService.getKpiReport(commonFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject = getTableModelForNewindicator(testGrid);
		httpSession.removeAttribute("onlinetestColModel");
		httpSession.setAttribute("onlinetestColModel", jsonObject);
		out.println(jsonObject);
		
	}
	  private void kpiReportGetData(HttpServletRequest request,HttpServletResponse response) {
	  try {
		CommonFilter commonFilter = new CommonFilter();
		FilterValues.getCommonFilters(request, commonFilter);
		//commonFilter = populateCommonFilter(request,"onlineTestCommonFilter",false);
		List<String[]> testGrid =  kpiTlIndicatorKkService.getKpiReport(commonFilter);
		PrintWriter out = response.getWriter();
		net.sf.json.JSONObject testReportGridmod = UIUtils.convertToJqGridTableObject(testGrid, request, 2,0);
		out.println(testReportGridmod);
		} catch (Exception e) {
		//CommonMessage.debugMsg(e.getMessage());
		}
			
		}
	@SuppressWarnings("unchecked")
	private void SaveKPIIndicatorDeptLink(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		UIUtils.displayRequestParamsValue(request);
		if (httpSession != null && user != null) 
			{
				try 
				{
					KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink =new KpiTlIndicatorDeptLink();
					kpiTlIndicatorDeptLink = (KpiTlIndicatorDeptLink) UIUtils.setBeanProperties((Object) kpiTlIndicatorDeptLink,request);
					String pillFactlist = request.getParameter("selectedPillFactIDs");
					String deleteLink = request.getParameter("deleteLinkList");
					//String indicatorId=request.getParameter("indicatorId");
					String indicatorId=kpid;
					String factId=request.getParameter("cmbKPIFactoryid");
					String sectId=request.getParameter("cmbKPISectionid");
					String cellId=request.getParameter("cmbKPICellid");
					String deptId="";
					
					if(UIUtils.isValidDate(factId)&&!UIUtils.isValidKeyId(sectId)&&!UIUtils.isValidKeyId(cellId))
						deptId=factId;
					else if(UIUtils.isValidKeyId(sectId)&&!UIUtils.isValidKeyId(cellId))
						deptId=sectId;
					else if(UIUtils.isValidKeyId(cellId))
						deptId=cellId;
						
					List<KpiTlIndicatorDeptLink> pillarFactLinkList = null;					
					JSONArray pillFactlinkJson = null;
					//CommonMessage.debugMsg("pillFactlist" + pillFactlist);
					if (UIUtils.isValidKeyId(pillFactlist)) 
					{
						
						pillFactlinkJson = JSONArray.fromString(pillFactlist);
						pillarFactLinkList = (List<KpiTlIndicatorDeptLink>) UIUtils.convertJSONArrToList(kpiTlIndicatorDeptLink,pillFactlinkJson);
						//CommonMessage.debugMsg( "size:" +pillarFactLinkList.size());
						if (pillarFactLinkList != null)
						{
							for( KpiTlIndicatorDeptLink genKpiTlIndicatorDeptLink1 : pillarFactLinkList)
							{
								genKpiTlIndicatorDeptLink1.setIsDelete("N");
								//CommonMessage.debugMsg( "save getKidlDeptiddddd:" +genKpiTlIndicatorDeptLink1.getKidlDeptid());
								//CommonMessage.debugMsg( "save getKidlIndicatoriddddd:" +genKpiTlIndicatorDeptLink1.getKidlIndicatorid());
							}
						}
					}
					List<KpiTlIndicatorDeptLink> deleteFactLinkList = null;
					JSONArray deleteFactLinkJson = null;
					//CommonMessage.debugMsg( "deleteLink"+deleteLink);
					if (UIUtils.isValidKeyId(deleteLink)) 
					{
						deleteFactLinkJson = JSONArray.fromString(deleteLink);
						deleteFactLinkList = (List<KpiTlIndicatorDeptLink>) UIUtils.convertJSONArrToList(kpiTlIndicatorDeptLink,deleteFactLinkJson);
						if (deleteFactLinkList != null)
						{							
							if(pillarFactLinkList==null){
								//CommonMessage.debugMsg( "fdfd resize pillarFactLinkList");
								pillarFactLinkList=new ArrayList<KpiTlIndicatorDeptLink>();
							}
							for( KpiTlIndicatorDeptLink genKpiTlIndicatorDeptLink : deleteFactLinkList)
							{	
								//CommonMessage.debugMsg( "getIsDelete getKidlDeptiddddd:" +genKpiTlIndicatorDeptLink.getKidlDeptid());
								//CommonMessage.debugMsg( "getIsDelete getKidlIndicatoriddddd:" +genKpiTlIndicatorDeptLink.getKidlIndicatorid());
								genKpiTlIndicatorDeptLink.setIsDelete("Y");
								pillarFactLinkList.add(genKpiTlIndicatorDeptLink);
							}
						}
					}
					//CommonMessage.debugMsg( "dfd size:" +pillarFactLinkList.size());
					if (pillarFactLinkList != null){
						CommonMessage.debugMsg( "set method pillarFactLinkList");
						kpiTlIndicatorDeptLink.setmethodPillarFactlink(pillarFactLinkList);
					}
					/*String pillCode=(String)httpSession.getAttribute("pillarcode");
					String drillLevel=(String)httpSession.getAttribute("drillLevel");
					
					String flid  =(String)httpSession.getAttribute("flid");
					String elemtype =(String)httpSession.getAttribute("elemtype"); */
					
					String pillCode = request.getParameter("pillarcode");
					String drillLevel=request.getParameter("drillLevel");
					
					String flid  = request.getParameter("flid");
					String elemtype =request.getParameter("elemtype");
					
					deptId = flid;
					drillLevel = elemtype;
					
					//CommonMessage.debugMsg( "flidelemtype" + flid + elemtype);
					
					kpiTlIndicatorDeptLink.setKidlDeptid(flid);
					kpiTlIndicatorDeptLink.setKidlDepttype(elemtype);
					
					
					String isIndicatorFactory="";
					if(UIUtils.isValidKeyId(pillFactlist))
						isIndicatorFactory="true";
					else
						isIndicatorFactory="false";
					CommonMessage.debugMsg("kpiTlIndicatorDeptLink,user.getUsrm_ccno(),pillCode,drillLevel,indicatorId,deptId,isIndicatorFactory");
					CommonMessage.debugMsg("pillCode"+pillCode);
					CommonMessage.debugMsg("drillLevel"+drillLevel);
					CommonMessage.debugMsg("indicatorId"+indicatorId);
					CommonMessage.debugMsg("deptId"+deptId);
					CommonMessage.debugMsg("isIndicatorFactory"+isIndicatorFactory);
					
					
					int n=loginElementid.length();
					//System.out.p
					int sect=54;
					int cell=65;
					if(n==sect)
					{
						kpiTlIndicatorDeptLink.setKidlDepttype("SECT");
						CommonMessage.debugMsg("inside sect");
					}
				     if(n==cell)
				     {
							kpiTlIndicatorDeptLink.setKidlDepttype("CELL");
							CommonMessage.debugMsg("inside cell");
				     }
				   
				     CommonMessage.debugMsg("kpiTlIndicatorDeptLink.setKidlDepttype>>>"+kpiTlIndicatorDeptLink.getKidlDepttype());
					kpiTlIndicatorDeptLink = kpiTlIndicatorKkService.createPillFactLink(kpiTlIndicatorDeptLink,user.getUsrm_ccno(),pillCode,drillLevel,indicatorId,deptId,isIndicatorFactory);
					String msgPropertyIdnt;
					msgPropertyIdnt = "success-save";
					JSONObject successData = new JSONObject();
					successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("pillCode",pillCode);
					JSONObject result = new JSONObject();
					result.put("formClear", false);
					result.put("displyMsg", true);
					result.put("successData", successData);
					out.print(result.toString());
					out.close();
				}
				catch (BusinessApplicationExceptions e) 
				{
					//CommonMessage.debugMsg("gete. " + e.getMessage());
					e.printStackTrace();
					CommonMessage.debugMsg(e);
					JSONObject err = new JSONObject();
					err.put("tpmException", e.getMessage());//"Data Not Saved");
					out.print(err.toString());
				}
				catch (Exception e) 
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException","Data Not Saved");
					out.print(err.toString());
				}
			}
    }
    private void loadIndicator(HttpServletRequest request, HttpServletResponse response,String pillar) throws IOException, ServletException{
    	String type = request.getParameter("type");
    	String pillarId="";		
    	String location="";	
    	String flId = "";
		try {
			if (!UIUtils.isValidKeyId(type))
				type="KPI";
				
			pillarId=kpiTlIndicatorKkService.getPillarKeyId(pillar);
			flId=CommonFunctions.getLoginFlid(request);
			if (UIUtils.isValidKeyId(flId))				
				location=kpiTlIndicatorKkService.getLocation(flId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("pillarid", pillarId);	
		request.setAttribute("location", location);	
		request.setAttribute("type", type);
		//RequestDispatcher rd = request.getRequestDispatcher("/pages/KPI/NewIndicator.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("/pages/KPI/KeyPerIndicatorKk.jsp");
		
		rd.forward(request, response);

    }
    public static JSONObject convertToJqGridTableObject(List<String[]> dataArrayList,HttpServletRequest request,int rowStart,int colStart, int colSub, long totalRecords){
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		//CommonMessage.debugMsg(" totalRecords " + totalRecords);
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		if(totalRecords>1)
		tableDataObject.put("records", totalRecords - rowStart); //total records
		else
			tableDataObject.put("records", totalRecords ); 	
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = rows * (page-1);
		int slno = 0;
        for( String [] row : dataArrayList)
		{
        	if( slno++ >= rowStart )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -rowStart +1);
	            
	            JSONArray cell=new JSONArray();
	            
	            for( int i = colStart ;i < (row.length - colSub); i++)
	            {	 
	            	cell.put( ( row[i] != null ?row[i].isEmpty() ?" ":  row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}
	
    private void validatekeyInd(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		String elementId = request.getParameter("keyId");	
		String pillarId=request.getParameter("pillarId");	
		
		CommonMessage.debugMsg("===== validatekeyInd() called =====");
		CommonMessage.debugMsg("Received keyId (elementId): " + elementId);
		CommonMessage.debugMsg("Received pillarId: " + pillarId);
		
		String validate=null;
		CommonMessage.debugMsg("Element Id : "+elementId);	
		KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
		kpiTlIndicatorKk.setKinkKeyid(elementId);
		if(CommonFunctions.isValidKeyId(pillarId))
			kpiTlIndicatorKk.setKinkPillarid(pillarId);
		try {
			validate = kpiTlIndicatorKkService.validatekeyIndLevel(kpiTlIndicatorKk);
			CommonMessage.debugMsg("validate : "+validate);	
		} 
		catch(Exception e)
		{				
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());			
		}
		
		// sriram 11-nov-2025  
		//if (validate.equals("Valid")) {
		if ("Valid".equals(validate)) {
			JSONObject successData = new JSONObject();
			successData.put("msg",validate);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());
		}
		else
		{
			JSONObject successData = new JSONObject();
			successData.put("msg",validate);
			JSONObject returnData = new JSONObject();	
			returnData.put("successData", successData);	
			returnData.put("tpmException", "Training Area Already Referred ");	
			out.print(returnData.toString());
		}	
	}
    
	private void searchkeyInd(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,PrintWriter out  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{		
		Enumeration<String> params = request.getParameterNames() ;
		String prevSearchstr = UIUtils.getCookieValue(request,"qtCostsearch_str");
		String currentSearchStr = request.getParameter("search_str");
		//if(currentSearchStr.indexOf(":")>0)
			//currentSearchStr = functionalLocnServicesImpl.getNameForId(currentSearchStr);
		String countStr  = "0"; 
		List<String[]> searchList = null;
		if(currentSearchStr != null && prevSearchstr != null &&  currentSearchStr.equals(prevSearchstr))
		{
			countStr = UIUtils.getCookieValue(request,"qtCostsearch_str_cnt");
			searchList =(List<String[]>) httpSession.getAttribute("qtCostSearchList");
		}
		else{
			searchList = kpiTlIndicatorKkService.getSearchNode(currentSearchStr,"");
			httpSession.setAttribute("qtCostSearchList", searchList);
		}
		int searchCnt = Integer.parseInt(countStr);
		JSONArray jSONArray =null;
		if( searchCnt < searchList.size() ){
			String parentId = searchList.get(searchCnt)[0];
			CommonMessage.debugMsg(parentId);
			parentId=parentId.replaceAll("/", "_");
			parentId = "#node_1-QC001" + parentId.replaceAll("_", "-");
			//parentId = "-" + parentId.replaceAll("/", "_");
			CommonMessage.debugMsg(parentId);
			parentId = parentId.replaceAll("-", "-#");
			//parentId = "#node_1-#node_2" + parentId;
			CommonMessage.debugMsg(parentId);
			String[] searchNode = parentId.split("-");
			jSONArray = JSONArray.fromArray(searchNode);
		}
		else{
			searchCnt =-1;
		}
		Cookie searchStrCookie =  new Cookie("qtCostsearch_str",currentSearchStr);
		searchStrCookie.setHttpOnly(true);
		searchStrCookie.setPath("/");
		searchStrCookie.setMaxAge(60*60);
		response.addCookie(searchStrCookie);
		Cookie searchCntCookie =  new Cookie("qtCostsearch_str_cnt",(searchCnt+1)+"");
		searchCntCookie.setHttpOnly(true);
		searchCntCookie.setPath("/");
		searchCntCookie.setMaxAge(60*60);
		response.addCookie(searchCntCookie);
		out.println(jSONArray);
	}
	
	private void savekeyInd(HttpServletRequest request, HttpServletResponse response,KpiTlIndicatorKkBean KpiTlIndicatorKkBean  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		//CommonMessage.debugMsg("inside savekeyInd");
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		try{
	    	if( httpSession != null && user != null)
	    	{		    		
	    		String saveMsg ;		
	    		KpiTlIndicatorKk existKpiTlIndicatorKk = (KpiTlIndicatorKk)httpSession.getAttribute("kpiTlIndicatorKk");
	    		
				KpiTlIndicatorKk newKpiTlIndicatorKk = new KpiTlIndicatorKk();	 
	    		newKpiTlIndicatorKk.setKinkCreatedby(user.getUsrm_ccno());
	    		newKpiTlIndicatorKk =(KpiTlIndicatorKk)UIUtils.setBeanProperties((Object)newKpiTlIndicatorKk,request);
				String KeyID = (String) httpSession.getAttribute("kinkKeyId");
				newKpiTlIndicatorKk.setKinkKeyid(KeyID);//KeyId			
				KpiTlIndicatorKkBean =(KpiTlIndicatorKkBean) UIUtils.setBeanProperties((Object)KpiTlIndicatorKkBean,request);			
				
				CommonMessage.debugMsg(" newKpiTlIndicatorKk.getkinkKeyId()" +  newKpiTlIndicatorKk.getKinkKeyid());			
				if( newKpiTlIndicatorKk.getKinkKeyid()== null )
				{	
					CommonMessage.debugMsg("key id is not available");
					KpiTlIndicatorKkBean.setFormMode(FormModes.create);
					existKpiTlIndicatorKk =	 kpiTlIndicatorKkService.create(newKpiTlIndicatorKk,existKpiTlIndicatorKk);
					saveMsg = "Data Saved Successfully";
				}	
				else{
					CommonMessage.debugMsg("key id is available:" + newKpiTlIndicatorKk.getKinkKeyid() );
					KpiTlIndicatorKkBean.setFormMode(FormModes.modify);
					existKpiTlIndicatorKk =	 kpiTlIndicatorKkService.update(newKpiTlIndicatorKk,existKpiTlIndicatorKk);
					saveMsg = "Data Updated Successfully";
				}
				httpSession.setAttribute(existKpiTlIndicatorKk.getKinkKeyid(), existKpiTlIndicatorKk);
				httpSession.setAttribute("KpiTlIndicatorKk", existKpiTlIndicatorKk);
				String formBeanIdentifier = "KpiTlIndicatorKkBean"+KpiTlIndicatorKkBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,KpiTlIndicatorKkBean);
				JSONObject returnData = new JSONObject();			
				JSONObject successData = new JSONObject();	
				successData.put("msg", saveMsg);
				returnData.put("formClear",false);	
				returnData.put("successData",successData);
				out.print(returnData.toString());
	    	}	    	
		}
		catch(ValidationExceptions e)
		{
			CommonMessage.debugMsg("ValidationExceptions");
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "KpiTlIndicatorKk");
			errMessage.put("fromMode",KpiTlIndicatorKkBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("BusinessApplicationExceptions Exception");
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"KpiTlIndicatorKk");
			errMessage.put("tpmException", "This Identifier Already Exists");
			out.print(errMessage.toString());
		}
		catch(Exception e)
		{				
			e.printStackTrace();
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());			
		}	
	}	
	
	private void validateDelkeyInd(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws IOException{
		String elementId = request.getParameter("keyId");	
		String validate=null;
		CommonMessage.debugMsg("Element Id : "+elementId);	
		KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
		kpiTlIndicatorKk.setKinkParentid(elementId);
		try {
			validate = kpiTlIndicatorKkService.validateDelkeyIndLevel(kpiTlIndicatorKk);
			CommonMessage.debugMsg("validate : "+validate);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject successData = new JSONObject();
		successData.put("msg",validate);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);				
		out.print(returnData.toString());
	}	
	
	private void deleteKPIProdflid(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws BusinessApplicationExceptions,Exception{
		String KidlKeyid = request.getParameter("KidlKeyid");
		String KidlKeyid2 = request.getParameter("cmbKidlIndicatorid");
		String indicatorId = request.getParameter("indicatorId");
		request.setAttribute("KidlKeyid", KidlKeyid);
		//String flid = request.getParameter("cmbPlosFlid");
		
		CommonMessage.debugMsg("KidlKeyidvijay"+flId);
		CommonMessage.debugMsg("KidlKeyidvijay"+indicatorId);
		CommonMessage.debugMsg("KidlKeyidvijay"+KidlKeyid2);
		CommonMessage.debugMsg("KidlKeyidvijay"+KidlKeyid);
		KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink = new KpiTlIndicatorDeptLink();
		CommonMessage.debugMsg(kpid);
		 String kidlkeyid=kpiTlIndicatorKkService.deptid(kpid,flId);
		 CommonMessage.debugMsg("kidlkeyid:::"+kidlkeyid);
	 //CommonMessage.debugMsg();
		 if(kidlkeyid!=null)
		 {
			//String deptid=kpiTlIndicatorKkService.dapartmentid(flId);
			 //deptid=flId;
			if(flId!=null)
			{
				kpiTlIndicatorDeptLink.setKidlDeptid(flId);
			}
		 }
		 //CommonMessage.debugMsg("deptid:::"+deptid);
	
		//CommonMessage.debugMsg("Element Id : "+elementId);	
		
		kpiTlIndicatorDeptLink.setKidlKeyid(kidlkeyid);
		try {
			kpiTlIndicatorDeptLink = kpiTlIndicatorKkService.deleteDeptLink(kpiTlIndicatorDeptLink);			
			JSONObject successData = new JSONObject();
			successData.put("msg","Data Deleted Successfully");
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());			
		}
		catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("BusinessApplicationExceptions");
			CommonMessage.debugMsg("BusinessApplicationExceptions:"+e.toString());
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "KpiTlIndicatorKk");
			out.print(errMessage.toString());
    	}
		catch(Exception e)
		{				
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());			
		}
	}
	private void deletekeyInd(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws BusinessApplicationExceptions,Exception{
		String keyId = request.getParameter("keyId");			
		//CommonMessage.debugMsg("Element Id : "+elementId);	
		KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
		kpiTlIndicatorKk.setKinkKeyid(keyId);
		try {
			kpiTlIndicatorKk = kpiTlIndicatorKkService.delete(kpiTlIndicatorKk);			
			JSONObject successData = new JSONObject();
			successData.put("msg","Data Deleted Successfully");
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());			
		}
		catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("BusinessApplicationExceptions");
			CommonMessage.debugMsg("BusinessApplicationExceptions:"+e.toString());
			net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "KpiTlIndicatorKk");
			out.print(errMessage.toString());
    	}
		catch(Exception e)
		{				
			CommonMessage.debugMsg("Exception");
			CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Deleted");
			out.print(err.toString());			
		}
	}
	private void LoadkeyIndTree(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws IOException{
		String parentNumber = null;
		String parentId = null;		
		String id=null;
		String pillarId=null;
		String locationId=null;
		parentNumber=request.getParameter("elementId");
		parentId = request.getParameter("parentId");
		pillarId = request.getParameter("pillarId");
		locationId = request.getParameter("locationId");
		String type = request.getParameter("type");
		
		CommonMessage.debugMsg("PillarId : "+pillarId);
		CommonMessage.debugMsg("locationId : "+locationId);
		id=request.getParameter("id");
		if(CommonFunctions.isValidKeyId(parentNumber))
			parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
		CommonMessage.debugMsg("ID : "+id);
    	response.setContentType("text/html;charset=UTF-8");
    	try {
    		JSONArray jSONArray = new JSONArray();	    		
	    	if(request.getParameter("id").equals("0"))
	    	{
		    	   JSONObject jSONObject = new JSONObject();
	    		   JSONObject data = new JSONObject();
	    		   JSONObject jsonAttr = new JSONObject();
	               JSONObject metadata = new JSONObject();
	    		   jsonAttr.put("id", "QC001");
	    		   jsonAttr.put("originalId", "1");
	               jsonAttr.put("elementId", "1");
	               jsonAttr.put("elementType", "QC");
	               jsonAttr.put("parentId", "1");
	               jsonAttr.put("displayCode", "Indicators");
	               jsonAttr.put("LevelNo", "0");
	               jsonAttr.put("imgUrl",getImageUrl("QC"));
	               jsonAttr.put("href", "#");
	               data.put("title", "Indicators");	        			
        		   data.put("icon", "");
        		   jSONObject.put("data",data);
        		   jSONObject.put("attr", jsonAttr);
        		   
        		   if(request.getParameter("search_str") != null)
        			   jSONObject.put("state","open");
        		   else
        			   jSONObject.put("state","closed");
        		   
        		   metadata.put("id", "1");
        		   jSONObject.put("metadata",metadata);
	               jSONObject.put("icon",getIconImage("QC"));
	               jsonAttr = null;
	               jSONObject.put("children","[{}]");
	               jSONArray.put(jSONObject);
	               jSONObject=null;		    		
	    	}
	    	else
	    	{
    			KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
    			List <KpiTlIndicatorKk> locnList=null;
    			kpiTlIndicatorKk.setKinkLocation(locationId);
    			kpiTlIndicatorKk.setKinkTempfield3(type);
    			if(CommonFunctions.isValidKeyId(pillarId))
					kpiTlIndicatorKk.setKinkPillarid(pillarId);
    			
    			if(!parentId.equals("1") && CommonFunctions.isValidKeyId(parentId)){
    				CommonMessage.debugMsg("parentId" + parentId);
    				kpiTlIndicatorKk.setKinkParentid(id);    				
    				locnList = kpiTlIndicatorKkService.getAllkeyInd(kpiTlIndicatorKk);
    			}
    			else{
    				locnList = kpiTlIndicatorKkService.getKpiTlIndicatorKkValues(kpiTlIndicatorKk);
    			}
	        	String elementId=null;
	        	for(int i=0; i<locnList.size(); i++){
	        		JSONObject jSONObject = new JSONObject();
        			JSONObject data = new JSONObject();
        			JSONObject jsonAttr = new JSONObject();
	                JSONObject metadata = new JSONObject();
	                elementId=locnList.get(i).getKinkTempfield3().replaceAll("/", "-");
	                elementId=elementId.substring(1, elementId.length());
	        		//CommonMessage.debugMsg(locnList.get(i).getOriginalId());
	        		//CommonMessage.debugMsg(locnList.get(i).getElementId());
	        		//CommonMessage.debugMsg(locnList.get(i).getParentId());
	        		//CommonMessage.debugMsg(locnList.get(i).getElementType());
	        		jsonAttr.put("id", locnList.get(i).getKinkKeyid().replace("/", "_"));
	                jsonAttr.put("originalId", locnList.get(i).getKinkKeyid());
	                jsonAttr.put("elementId", elementId);
	                jsonAttr.put("elementType", "K");
	                jsonAttr.put("parentId", locnList.get(i).getKinkParentid());
	                jsonAttr.put("displayCode", locnList.get(i).getKinkIndicatorname());
	                jsonAttr.put("LevelNo",locnList.get(i).getKinkLevelno());                
                	jsonAttr.put("title","Indicators");		                	
	                jsonAttr.put("href", "#");
        			data.put("title", locnList.get(i).getKinkIndicatorname());
        			//data.put("attr", jsonAttr);
        			data.put("icon", "");
        			jSONObject.put("data",data);
        			jSONObject.put("attr", jsonAttr);	
        			if(request.getParameter("search_str") != null)
        			{
        				jSONObject.put("state","open");
        			}
        			else
        				jSONObject.put("state","closed");
        			
	                metadata.put("id", i);
	                jSONObject.put("metadata",metadata);
	                jSONObject.put("icon",getIconImage("K"));			           
	                jsonAttr = null;
	                jSONObject.put("children","[{}]");
	                jSONArray.put(jSONObject);
	                jSONObject=null;
	        	}			        
        	}   
           out.print(jSONArray);
    	   jSONArray=null;
    	}catch(Exception e){
           // CommonMessage.debugMsg(e);
            e.printStackTrace();
        }
        finally {
            out.close();
        }
     
	}	
	private void LoadkeyIndEntry(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession ) throws IOException{
		String userEvent = request.getParameter("userEvent");
		String parentId = request.getParameter("parentId");		
		String elementType = request.getParameter("elementType");
		String keyid = request.getParameter("keyId");	
		String levelNo = request.getParameter("levelNo");
		String pillarId= request.getParameter("pillarId");
		String locationId= request.getParameter("locationId");
		String type= request.getParameter("type");
		String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
		FormModes mode = FormModes.create;
		String trarMode = request.getParameter("mode");
		/*CommonMessage.debugMsg("trarMode................."+trarMode);			
		CommonMessage.debugMsg("elementType................."+elementType);*/			
		if(formMode != null && formMode.equalsIgnoreCase(FormModeConsts.view) )
			mode = FormModes.view;
		else if(formMode != null && formMode.equalsIgnoreCase(FormModeConsts.modify) )
			mode = FormModes.modify;
		else if(formMode != null && formMode.equalsIgnoreCase(FormModeConsts.create) )
			mode = FormModes.create;
		//CommonMessage.debugMsg("parentId:" + parentId);
		/*//CommonMessage.debugMsg("keyid:" + keyid);
		//CommonMessage.debugMsg("locationId:" + locationId);
		//CommonMessage.debugMsg("levelNo:" + levelNo);
		//CommonMessage.debugMsg("elementType:" + elementType);
		response.setContentType("text/html");*/
		KpiTlIndicatorKkBean KpiTlIndicatorKkBean =new KpiTlIndicatorKkBean(mode);
		
		httpSession.removeAttribute("kinkKeyId");
		httpSession.removeAttribute("KpiTlIndicatorKkBean");
		httpSession.removeAttribute("KpiTlIndicatorKk");
		httpSession.removeAttribute("keyIndlMode");
		
		KpiTlIndicator kpiTlIndicatorKk= new  KpiTlIndicator();
		if(CommonFunctions.isValidKeyId(type))
			kpiTlIndicatorKk.setKinkType(type);
		if(CommonFunctions.isValidKeyId(pillarId))
			kpiTlIndicatorKk.setKinkPillarid(pillarId);
		if(CommonFunctions.isValidKeyId(locationId))
			kpiTlIndicatorKk.setKinkLocation(locationId);
		try {			
			if( (UIUtils.isValidKeyId(keyid) && userEvent == null )|| (userEvent != null && ! userEvent.equals("new")))
			{											
				kpiTlIndicatorKk.setKinkKeyid(keyid);
				kpiTlIndicatorKk = kpiTlIndicatorKkService.select(kpiTlIndicatorKk);
				
				String date1 = kpiTlIndicatorKk.getKinkStartdate();
				kpiTlIndicatorKk.setKinkStartdate (CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
				kpiTlIndicatorKk.setKinkStartdate(UIUtils.getActualDateForm(kpiTlIndicatorKk.getKinkStartdate()));
				
				kpiTlIndicatorKk.setKinkEnddate (CommonFunctions.pg_getDateTimeFromPGTimeStamp(date1));
				kpiTlIndicatorKk.setKinkEnddate(UIUtils.getActualDateForm(kpiTlIndicatorKk.getKinkEnddate()));
				
				
				httpSession.setAttribute("kinkKeyId",keyid);
				CommonMessage.debugMsg("kinkKeyId..."+kpiTlIndicatorKk.getKinkKeyid());
				CommonMessage.debugMsg("KinkTargetneed..."+kpiTlIndicatorKk.getKinkTargetneed());
			}		
			else{	
				CommonMessage.debugMsg("INSERT MODE");
				if (!parentId.equals("1") && CommonFunctions.isValidKeyId(parentId))
					kpiTlIndicatorKk.setKinkParentid(parentId);
				CommonMessage.debugMsg("setKinkParentid..."+kpiTlIndicatorKk.getKinkParentid());
				//CommonMessage.debugMsg("setKinkParentid..."+kpiTlIndicatorKk.getKinkParentid());
				int ileveno=0;
				ileveno=Integer.parseInt(levelNo);
				ileveno=ileveno+1;
				kpiTlIndicatorKk.setKinkLevelno(Integer.toString(ileveno));					
				kpiTlIndicatorKk.setKinkTargetneed("N");
				String sortNo="";
				sortNo=kpiTlIndicatorKkService.getSortNo(kpiTlIndicatorKk);
				CommonMessage.debugMsg("sortNo:"+sortNo);
				kpiTlIndicatorKk.setKinkSortno(sortNo);
				kpiTlIndicatorKk = (KpiTlIndicator)UIUtils.setBeanProperties(kpiTlIndicatorKk, request);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("kpiTlIndicator", kpiTlIndicatorKk);
		request.setAttribute("kpiTlIndicatorKkBean", KpiTlIndicatorKkBean);
		request.setAttribute(ReqtParamNameConst.FORM_MODE,mode);		
		request.setAttribute("mode", trarMode);
		request.setAttribute("refType", elementType);
		request.setAttribute("parentId",parentId);
		request.setAttribute("keyId",keyid);
		request.setAttribute("kpiTlIndicatorKk",kpiTlIndicatorKk);
		request.setAttribute("inactMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
		httpSession.setAttribute("kpiTlIndicatorKk", kpiTlIndicatorKk);			
		httpSession.setAttribute("kpiTlIndicatorKkBean", KpiTlIndicatorKkBean);
		httpSession.setAttribute("keyIndMode",mode);
		httpSession.setAttribute("parentId",parentId);
	}
	
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public String getIconImage(String elementType)
    {
    	String imgUrl = null;
    	if(elementType.equals("QC"))
    		imgUrl =  "images/FnLocn/company.jpg";
    	else if(elementType.equals("K"))
    		imgUrl =  "images/FnLocn/location.jpg";    	
    	return imgUrl;
    	
    }
    public String getImageUrl(String elementType)
    {
    	//CommonMessage.debugMsg("Inside getImage " +elementType);
    	String imgUrl = null;
    	if(elementType.equals("QC"))
    		imgUrl =  "images/companyy.jpg";
    	return imgUrl;    	
    }
    private net.sf.json.JSONObject getTableModelnew(List<String[]> headers, String fromExcel)
	{
    	JqGridTableModel jqGridTableModel = new JqGridTableModel();
    	/*String[] colHeader = headers.get(1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setTableButton(true);
		*/
		String [] colHeaderTemp=headers.get(1);
		int size=0;		
		size=colHeaderTemp.length-1;
		String[] colHeader= new String[size];
		String [] colHeader2=new String[size];
		//CommonMessage.debugMsg(size);
		int j=2;int k=2;
		for(int i=0;i<size;i++)
		{
			
			if(i==0)
			{colHeader[i]="IndicatorsID";
			if(fromExcel.equals("false"))
			colHeader2[i]="IndicatorsID";
			}
			else if(i==1)
			{
				colHeader[i]="Indicators";
				if(fromExcel.equals("false"))
				colHeader2[i]="Indicators";
			}
			else if((i%2==0)&& fromExcel.equals("false"))
			{
				if(k<=colHeaderTemp.length+2)
				{
					colHeader[j] = headers.get(0)[k];
					if(fromExcel.equals("false"))
					colHeader2[j] = headers.get(1)[k];
				}
				j++;
				k++;
			}
			else if((i%2==1)&& fromExcel.equals("false"))
			{
				colHeader[j]="check"+i;
				colHeader2[j] ="check"+i;
				j++;
			}
			else if(fromExcel.equals("true"))
			{
				colHeader[j] = headers.get(1)[i];
				j++;	
			}
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		if(fromExcel.equals("false"))
		jqGridTableModel.getRowHeaders().add(colHeader2);
		
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setAlign("left");
		   	jqGridColModel.setWidth(145);
			jqGridColModel.setHidden(false);
			jqGridColModel.setKey(false);
			if(i==0 ||  i==colHeader.length-3 || i==colHeader.length-2 || i==colHeader.length-1 ){
				jqGridColModel.setHidden(true);
			}
			if(i==2){
				jqGridColModel.setHidden(false);
				jqGridColModel.setIndex("factoryPillar_checkbox"+i);
				jqGridColModel.setName("factoryPillar_checkbox"+i);				
				jqGridColModel.setFormatter("chkbox_factoryPillar");				
			}
			if(i==3){
				jqGridColModel.setHidden(true);
				jqGridColModel.setIndex("checkPillarvalue"+i);
				jqGridColModel.setName("checkPillarvalue"+i);					
			}
			jqGridColModel.setEditable(false);
			//CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		net.sf.json.JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "81%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
    
    private net.sf.json.JSONObject getTableModel1(List<String[]> headers, String fromExcel)
	{
    	CommonMessage.debugMsg("tablemode1");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeaderTemp=headers.get(1);
		int size=0;
		size=colHeaderTemp.length-1;
		String[] colHeader= new String[size];
		String [] colHeader2=new String[size];
		CommonMessage.debugMsg("size:"+size);
		for(int i=0;i<size;i++)
		{
			
			if(i==0)
			{colHeader[i]="IndicatorsID";
			if(fromExcel.equals("false"))
			colHeader2[i]="IndicatorsID";
			}
			else if(i==1)
			{
				colHeader[i]="Indicators";
				if(fromExcel.equals("false"))
				colHeader2[i]="Indicators";
			}
			else
			{
				colHeader[i] = headers.get(0)[i];
				if(fromExcel.equals("false"))
				colHeader2[i] =headers.get(1)[i];
				//j++;	
				//k++;
			}
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		if(fromExcel.equals("false"))
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		for(int i=0;i<size;i++)
		{
			if(i==0)
			{
				jqGridTableModel.getColModel().add(getColModel("indicatorID", 148,"left",true,false,false));
			}
			else if(i==1)
				jqGridTableModel.getColModel().add(getColModel("indicator", 220,"left",false,false,false));
			else if(i==size-3){
				jqGridTableModel.getColModel().add(getColModel("sortNo", 160,"left",true,false,false));
			}
			/*else if(i==size-3){
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}*/
			else if(i==size-2){
				jqGridTableModel.getColModel().add(getColModel("levelNo", 160,"left",true,false,false));
			}
			else if(i==size-1){
				jqGridTableModel.getColModel().add(getColModel("RNo", 0,"left",true,false,false));
			}
			/*else if(i==size-1){				
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}			
			else if((i%2==0)&&fromExcel.equals("false"))
			{
			jqGridTableModel.getColModel().add(getColModel("factoryPillar_checkbox"+i,170,"center",false,false,false));
			}
			else if((i%2==1)&& fromExcel.equals("false"))
			{
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}
			else if(fromExcel.equals("true"))
				jqGridTableModel.getColModel().add(getColModel("factoryPillar_checkbox"+i,110,"center",false,false,false));
				*/	
			else{
				jqGridTableModel.getColModel().add(getColModel1("factoryPillarActiveInactive_checkbox"+i,170,"center",false,false,false));
				jqGridTableModel.getColModel().add(getColModel1("checkPillarvalue1"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel1("isDelete"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel1("isKeyid"+(i+1),60,"left",true,false,false));
				i=i+3;
			}
		}
		net.sf.json.JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		//CommonMessage.debugMsg("tableModel" + tableModel);
		return tableModel;
	}
	private JqGridColModel getColModel1 (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		if(!(colIndex.equals("indicator")||colIndex.equals("indicatorID")||colIndex.equals("levelNo")||colIndex.equals("sortNo"))
				   &&!(colIndex.contains("checkPillarvalue1")||colIndex.contains("isDelete")||colIndex.contains("isKeyid")) )
		{
			jqGridColModel.setFormatter("chkbox_factoryPillarActiveInactive");
		}
		else
			jqGridColModel.setEditable(false);
		return jqGridColModel;
	}
	
	
	
	
	/*private net.sf.json.JSONObject getTableModel2(List<String[]> headers, String fromExcel)
	{
    	CommonMessage.debugMsg("getTableModel2");
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeaderTemp=headers.get(1);
		int size=0;
		size=colHeaderTemp.length-1;
		String[] colHeader= new String[size];
		String [] colHeader2=new String[size];
		CommonMessage.debugMsg("size:"+size);
		for(int i=0;i<size;i++)
		{
			
			if(i==0)
			{colHeader[i]="IndicatorsID";
			if(fromExcel.equals("false"))
			colHeader2[i]="IndicatorsID";
			}
			else if(i==1)
			{
				colHeader[i]="Indicators";
				if(fromExcel.equals("false"))
				colHeader2[i]="Indicators";
			}
			else
			{
				colHeader[i] = headers.get(0)[i];
				if(fromExcel.equals("false"))
				colHeader2[i] =headers.get(1)[i];
				//j++;	
				//k++;
			}
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		if(fromExcel.equals("false"))
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		for(int i=0;i<size;i++)
		{
			if(i==0)
			{
				jqGridTableModel.getColModel().add(getColModel("indicatorID", 148,"left",true,false,false));
			}
			else if(i==1)
				jqGridTableModel.getColModel().add(getColModel("indicator", 220,"left",false,false,false));
			else if(i==size-3){
				jqGridTableModel.getColModel().add(getColModel("sortNo", 160,"left",true,false,false));
			}
	
			else if(i==size-2){
				jqGridTableModel.getColModel().add(getColModel("levelNo", 160,"left",true,false,false));
			}
			else if(i==size-1){
				jqGridTableModel.getColModel().add(getColModel("RNo", 0,"left",true,false,false));
			}
	
			else{
				jqGridTableModel.getColModel().add(getColModel2("factoryPillarInactive_checkbox"+i,170,"center",false,false,false));
				jqGridTableModel.getColModel().add(getColModel2("checkPillarvalue2"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel2("isDelete"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel2("isKeyid"+(i+1),60,"left",true,false,false));
				i=i+3;
			}
		}
		net.sf.json.JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		return tableModel;
	}*/
	/*private JqGridColModel getColModel2(String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		if(!(colIndex.equals("indicator")||colIndex.equals("indicatorID")||colIndex.equals("levelNo")||colIndex.equals("sortNo"))
				   &&!(colIndex.contains("checkPillarvalue2")||colIndex.contains("isDelete")||colIndex.contains("isKeyid")) )
		{
			jqGridColModel.setFormatter("chkbox_factoryPillarInactive");
		}
		else
			jqGridColModel.setEditable(false);
		return jqGridColModel;
	}*/
    
    
    
    
    
    
    
    private net.sf.json.JSONObject getTableModel(List<String[]> headers, String fromExcel)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeaderTemp=headers.get(1);
		int size=0;
		/*if(fromExcel.equals("false"))
		size=(colHeaderTemp.length-3)*2;
		else if(fromExcel.equals("true"))*/
		size=colHeaderTemp.length-1;
		String[] colHeader= new String[size];
		String [] colHeader2=new String[size];
		CommonMessage.debugMsg("size:"+size);
		for(int i=0;i<size;i++)
		{
			
			if(i==0)
			{colHeader[i]="IndicatorsID";
			if(fromExcel.equals("false"))
			colHeader2[i]="IndicatorsID";
			}
			else if(i==1)
			{
				colHeader[i]="Indicators";
				if(fromExcel.equals("false"))
				colHeader2[i]="Indicators";
			}
			/*else if((i%2==0)&& fromExcel.equals("false"))
			{
				if(k<=colHeaderTemp.length+2)
				{
					colHeader[j] = headers.get(0)[k];
					if(fromExcel.equals("false"))
					colHeader2[j] = headers.get(1)[k];
				}
				j++;
				k++;
			}
			else if((i%2==1)&& fromExcel.equals("false"))
			{
				colHeader[j]="check"+i;
				colHeader2[j] ="check"+i;
				j++;
			}
			else if(fromExcel.equals("true"))
			{
				colHeader[j] = headers.get(1)[i];
				j++;	
			}
			*/
			else
			{
				colHeader[i] = headers.get(0)[i];
				if(fromExcel.equals("false"))
				colHeader2[i] =headers.get(1)[i];
				//j++;	
				//k++;
			}
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		if(fromExcel.equals("false"))
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		for(int i=0;i<size;i++)
		{
			if(i==0)
			{
				jqGridTableModel.getColModel().add(getColModel("indicatorID", 148,"left",true,false,false));
			}
			else if(i==1)
				jqGridTableModel.getColModel().add(getColModel("indicator", 220,"left",false,false,false));
			else if(i==size-3){
				jqGridTableModel.getColModel().add(getColModel("sortNo", 160,"left",true,false,false));
			}
			/*else if(i==size-3){
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}*/
			else if(i==size-2){
				jqGridTableModel.getColModel().add(getColModel("levelNo", 160,"left",true,false,false));
			}
			else if(i==size-1){
				jqGridTableModel.getColModel().add(getColModel("RNo", 0,"left",true,false,false));
			}
			/*else if(i==size-1){				
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}			
			else if((i%2==0)&&fromExcel.equals("false"))
			{
			jqGridTableModel.getColModel().add(getColModel("factoryPillar_checkbox"+i,170,"center",false,false,false));
			}
			else if((i%2==1)&& fromExcel.equals("false"))
			{
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+i,60,"left",true,false,false));
			}
			else if(fromExcel.equals("true"))
				jqGridTableModel.getColModel().add(getColModel("factoryPillar_checkbox"+i,110,"center",false,false,false));
				*/	
			else{
				jqGridTableModel.getColModel().add(getColModel("factoryPillar_checkbox"+i,170,"center",false,false,false));
				CommonMessage.debugMsg("i value "+i);
				jqGridTableModel.getColModel().add(getColModel("checkPillarvalue"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel("isDelete"+(i+1),60,"left",true,false,false));
				jqGridTableModel.getColModel().add(getColModel("isKeyid"+(i+1),60,"left",true,false,false));
				i=i+3;
			}
		}
		net.sf.json.JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		
		//CommonMessage.debugMsg("tableModel" + tableModel);
		return tableModel;
	}
	private JqGridColModel getColModel (String colIndex, int width,String allign,boolean hidden,boolean groupbyfield,boolean key)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth(width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setHidden(hidden);
		jqGridColModel.setKey(key);
		if(!(colIndex.equals("indicator")||colIndex.equals("indicatorID")||colIndex.equals("levelNo")||colIndex.equals("sortNo"))
				   &&!(colIndex.contains("checkPillarvalue")||colIndex.contains("isDelete")||colIndex.contains("isKeyid")) )
		{
			jqGridColModel.setFormatter("chkbox_factoryPillar");
		}
		else
			jqGridColModel.setEditable(false);
		return jqGridColModel;
	}
	
	
	
	/**
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	private  List<String[]> getDataforColmodel(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception
	{
		String CompId=request.getParameter("compId");
		String factId=request.getParameter("factId");
		String indicatorId=request.getParameter("indicatorId");
		String pillCode=request.getParameter("pillCode");
		String sectId=request.getParameter("sectId");
		String cellId=request.getParameter("cellId");
		String drillLevel=request.getParameter("drillLevel");
		String flid=request.getParameter("flid");
		String type = request.getParameter("type");
		String types=request.getParameter("types");
		String typesall=request.getParameter("typesall");
		CommonMessage.debugMsg("typeall:::"+typesall);
		CommonMessage.debugMsg("types"+types);
	//	String kpiinactive=request.getParameter("kpiinactive");
		//CommonMessage.debugMsg("The kpiinactive IS::::"+kpiinactive);
		//String drillLevel=(String)httpSession.getAttribute("drillLevel");
		response.setContentType("text/html");
		response.setContentType("text/json");
		GridParams gridParams = (GridParams) httpSession.getAttribute("KPIProdgridParams");
		httpSession.setAttribute("KPIProdgridParams", gridParams);
		if (gridParams == null)
		gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		//CommonMessage.debugMsg("CompId" + CompId + "factId" +  factId + "sectId" +  sectId + "cellId" + cellId + "drillLevel" + drillLevel);
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		
		commonFilter = populateCommonFilter(request,"kpiIndicatorCommonFilter",true);
		//CommonFilter commonFilter=new CommonFilter();
		commonFilter.setFlid(flid);
		commonFilter.setComp(CompId);
		commonFilter.setFactoryId(factId);
		commonFilter.setSectionId(sectId);
		commonFilter.setCellId(cellId);
		commonFilter.setDrillLevel(drillLevel);
		commonFilter.setIndicator(indicatorId);
		commonFilter.setPillarWise(pillCode);
		commonFilter.setType(type);
		
		 if("KPIALL".equals(typesall))
				commonFilter.setActionKeyId(typesall);
		if("MAPPEDKPI".equals(types))
			commonFilter.setAbnViewType(types);	
		// if("INACTIVE".equals(kpiinactive))
		  //     commonFilter.setAbnImp(kpiinactive);
		commonFilter.setIsGetCol("Y");
		List<String[]> KPIList  = kpiTlIndicatorKkService.getKPIProd(gridParams,commonFilter);
		String totlarowcount = String.valueOf(KPIList.size());
		
		long totalRwCnt= Long.parseLong(totlarowcount);
		gridParams.setTotalRecordCnt(totalRwCnt);
		httpSession.removeAttribute("KPIProdgridParams");
		//CommonMessage.debugMsg("gridParams3=>"+gridParams);
		httpSession.setAttribute("KPIProdgridParams" ,gridParams);
		return KPIList;		
	}
	
	private String[] findPillarCode(String action)
	{
		String[] variables=new String[3];
		if(action.equals("KPIProdFact_input.keyPerInd")||action.equals("KPIProdSect_input.keyPerInd")||action.equals("KPIProdCell_input.keyPerInd")||action.equals("KPIProdflid_input.keyPerInd")||action.equals("KPIProdActiveInactive_input.keyPerInd"))
			variables[0]="KK";
		else if(action.equals("KPIMaintenanceFact_input.keyPerInd")||action.equals("KPIMaintenanceSect_input.keyPerInd")||action.equals("KPIMaintenanceCell_input.keyPerInd"))
			variables[0]="PM";
		else if(action.equals("KPISafetyFact_input.keyPerInd")||action.equals("KPISafetySect_input.keyPerInd")||action.equals("KPISafetyCell_input.keyPerInd"))
			variables[0]="SHE";
		else if(action.equals("KPITrainingFact_input.keyPerInd")||action.equals("KPITrainingSect_input.keyPerInd")||action.equals("KPITrainingCell_input.keyPerInd"))
			variables[0]="ET";
		else if(action.equals("KPIQualityFact_input.keyPerInd")||action.equals("KPIQualitySect_input.keyPerInd")||action.equals("KPIQualityCell_input.keyPerInd"))
			variables[0]="QM";
		else if(action.equals("KPIOfficeFact_input.keyPerInd")||action.equals("KPIOfficeSect_input.keyPerInd")||action.equals("KPIOfficeCell_input.keyPerInd"))
			variables[0]="OTPM";
		else if(action.equals("KPIResearchDevelopmentFact_input.keyPerInd")||action.equals("KPIResearchDevelopmentSect_input.keyPerInd")||action.equals("KPIResearchDevelopmentCell_input.keyPerInd"))
			variables[0]="DM";
		else if(action.equals("KPIJHFact_input.keyPerInd")||action.equals("KPIJHSect_input.keyPerInd")||action.equals("KPIJHCell_input.keyPerInd"))
			variables[0]="JH";
		if(action.contains("Fact"))
			variables[1]="FCT";
		else if(action.contains("Sect"))
			variables[1]="LIN";
		else if(action.contains("Cell"))
			variables[1]="CEL";
		else if(action.contains("flid"))
			variables[1]="flid";
		CommonMessage.debugMsg("variables[1]:"+variables[1]);
			return variables;
	}
	
	private void inputAction(HttpServletRequest request, HttpServletResponse response,String action,HttpSession httpSession ) throws Exception
	{
		String type = request.getParameter("type");
		CommonMessage.debugMsg("::type:::"+type+"The Action is::"+action);
		String types=request.getParameter("types");
	    CommonMessage.debugMsg("types::Inactive:::"+types);
		String pillarId = request.getParameter("pillarId");
		String typesall=request.getParameter("typesall");
		CommonMessage.debugMsg("typeall:::"+typesall);
		//String kpiinactive=request.getParameter("kpiinactive");
		//CommonMessage.debugMsg("The kpiinactive"+kpiinactive);
		if (!UIUtils.isValidKeyId(type))
			type="KPI";	
		
		if(!UIUtils.isValidKeyId(types))
			types="MAPPEDKPI";
			
	      
		if(!UIUtils.isValidKeyId(typesall))
			typesall="KPIALL";
		// if(!UIUtils.isValidKeyId(kpiinactive))
			// kpiinactive="INACTIVE";
			
		String[] variables=findPillarCode(action);
		String pillarCode=variables[0];
		CommonMessage.debugMsg("pillar ocde"+pillarCode+"pillarId"+pillarId);
		String drillLevel=variables[1];				
		if (!UIUtils.isValidKeyId(pillarId))
			pillarId=kpiTlIndicatorKkService.getPillarKeyId(pillarCode);
		if (UIUtils.isValidKeyId(pillarId))
			request.setAttribute("pillarid", pillarId);
		CommonMessage.debugMsg("variables[1]:"+variables[1]);
		request.setAttribute("pillarcode", pillarCode);
		httpSession.removeAttribute("pillarcode");
		httpSession.setAttribute("pillarcode", pillarCode);
		request.setAttribute("drillLevel", drillLevel);
		httpSession.removeAttribute("drillLevel");
		httpSession.setAttribute("drillLevel", drillLevel);		
		httpSession.removeAttribute("type");
		request.setAttribute("type", type);
		httpSession.setAttribute("type", type);
		request.setAttribute("types",types);
		httpSession.setAttribute("types",types);
		request.setAttribute("typesall",typesall);
		httpSession.setAttribute("typesall",typesall);
	//	request.setAttribute("kpiinactive",kpiinactive);
		//httpSession.setAttribute("kpiinactive",kpiinactive);
	//	httpSession.removeAttribute("KidlKeyid");
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/KPI/KPIIndiactorLink.jsp"); 
		rd.forward(request, response);
	}
	
	
	
	private void getColAction(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession ) throws Exception
	{
		CommonMessage.debugMsg("Inside GetCol");
		PrintWriter out1 = response.getWriter();
		List<String[]> KPIList= getDataforColmodel(request,response,httpSession);
		httpSession.removeAttribute("KPIProdgridParams");
		String fromExcel="false";
		String types=request.getParameter("types");
		String type=request.getParameter("type");
		CommonMessage.debugMsg("The Types::getColAction:"+types);
		CommonMessage.debugMsg("The type:::"+type);
		String typesall=request.getParameter("typesall");
		CommonMessage.debugMsg("typeall:is getColAction::"+typesall);
	//	String kpiinactive=request.getParameter("kpiinactive");
		//CommonMessage.debugMsg("The kpiinactive IS::getColAction::"+kpiinactive);
		net.sf.json.JSONObject jsonObject=null;
		if(types!=null){
			CommonMessage.debugMsg("Inside MappedKPI");
			jsonObject=getTableModel1(KPIList,fromExcel);
		}
	/*	else if(kpiinactive!=null){
			CommonMessage.debugMsg("Inside InactiveKpi");
			jsonObject=getTableModel2(KPIList,fromExcel);
		}*/
		else{
			CommonMessage.debugMsg("Inside ELSE KPI");
			jsonObject=getTableModel(KPIList,fromExcel);
		}
		
			
		jsonObject.set("tableHeight", "65%%");
		jsonObject.set("tableWidth", "95%%");
		jsonObject.set("rowNumbers", true);
		httpSession.removeAttribute("KPIProdColModel");
		httpSession.setAttribute("KPIProdColModel", jsonObject);
		out1.println(jsonObject);
		out1.close();
	}

	

	
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');
		//CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
	
	private void getColforkk(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession ) throws Exception
	{
		String keyid = request.getParameter("keyid");
		//CommonFilter commonFilter = populateCommonFilter(request,"SafetyauditFilter",true);
		PrintWriter out = response.getWriter();
		//CommonMessage.debugMsg("master keyid" + keyid);
		
		List<String[]> checklist =getDataforColmodel(request,response,httpSession);
		
	
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
		GridColModel gridColModel = new GridColModel();
		
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableButton(true);
		
		
		gridColModel.setHeaderNum(1);
		
		String [] colHeader = checklist.get(2);			
		
		String [] colHeaderCond = checklist.get(0);
		
		List<String[]> headers = new ArrayList<String[]>();
		
		headers.add(colHeader);
	
		net.sf.json.JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		jsonObject.set("tableWidth", "106%%");
		jsonObject.set("tableHeight", "78%%");
		
		out.println(jsonObject);
	}
	private void getDataAction(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws Exception
	{
		CommonMessage.debugMsg("The getDataAction");
		PrintWriter out1 = response.getWriter();
		String indicatorId=request.getParameter("indicatorId");
		String pillCode=request.getParameter("pillCode");
		String pillarId=request.getParameter("pillarId");
		String CompId=request.getParameter("compId");
		String factId=request.getParameter("factId");
		String sectId=request.getParameter("sectId");
		String cellId=request.getParameter("cellId");
		String drillLevel=request.getParameter("drillLevel");
		String flid=request.getParameter("flid");
		String type = request.getParameter("type");
		String types=request.getParameter("types");
		CommonMessage.debugMsg("The Types:::"+types);
		String typesall=request.getParameter("typesall");
		CommonMessage.debugMsg("typeall:::"+typesall);
		//String kpiinactive=request.getParameter("kpiinactive");
		//CommonMessage.debugMsg("The kpiinactive IS:::"+kpiinactive);
		//String drillLevel=(String)httpSession.getAttribute("drillLevel");
		//CommonMessage.debugMsg("drillLevel" + drillLevel);
		httpSession.removeAttribute("indicatorId");
		httpSession.setAttribute("indicatorId", indicatorId);
		httpSession.removeAttribute("pillCode");
		httpSession.setAttribute("pillCode", pillCode);
		httpSession.removeAttribute("CompId");
		httpSession.setAttribute("CompId", CompId);
		httpSession.removeAttribute("factId");
		httpSession.setAttribute("factId", factId);
		httpSession.removeAttribute("sectId");
		httpSession.setAttribute("sectId", sectId);
		httpSession.removeAttribute("cellId");
		httpSession.setAttribute("cellId", cellId);
		CommonMessage.debugMsg("indicatorId:"+indicatorId);
		GridParams gridParams = (GridParams) httpSession.getAttribute("KPIProdgridParams");
		httpSession.setAttribute("KPIProdgridParams", gridParams);
		if (gridParams == null)
			gridParams = new GridParams();
		FilterValues.populateGridParams(request, gridParams);
		CommonFilter commonFilter=new CommonFilter();
		commonFilter = populateCommonFilter(request,"kpiIndicatorCommonFilter",true);
		commonFilter.setFlid(flid);
		commonFilter.setComp(CompId);
		commonFilter.setFactoryId(factId);
		commonFilter.setSectionId(sectId);
		commonFilter.setCellId(cellId);
		commonFilter.setDrillLevel(drillLevel);
		commonFilter.setIndicator(indicatorId);
		commonFilter.setPillarWise(pillarId);
		commonFilter.setDrillLevel(drillLevel);
		commonFilter.setType(type);
		
	
		 if("MAPPEDKPI".equals(types))
			 commonFilter.setAbnViewType(types);
		 else if("KPIALL".equals(typesall))
			 commonFilter.setActionKeyId(typesall);
		// else if("INACTIVE".equals(kpiinactive))
        	// commonFilter.setAbnImp(kpiinactive);
		 commonFilter.setIsGetCol("N"); 
		List<String[]> pcsComplianceList  = kpiTlIndicatorKkService.getKPIProd(gridParams,commonFilter);
		JSONObject listToJsonObject = new JSONObject();
		long  recrdCnt=gridParams.getTotalRecordCnt();
		if(recrdCnt>1)
			 recrdCnt+=2;
		listToJsonObject =convertToJqGridTableObject(pcsComplianceList,request,2,0,0,commonFilter.getTotalRecordCnt());
		httpSession.removeAttribute("KPIProdgridParams");
		out1.println(listToJsonObject);
	}
	private void getExcelAction(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws Exception
	{
		 CommonMessage.debugMsg("------ inside getExcelAction ------");
		 
		String format = ExcelUtils.getFormat(request);
		String CompId=(String)httpSession.getAttribute("CompId");
		String factId=(String)httpSession.getAttribute("factId");
		String sectId=(String)httpSession.getAttribute("sectId");
		String cellId=(String)httpSession.getAttribute("cellId");
		String drillLevel=(String)httpSession.getAttribute("drillLevel");
		String indicatorId=(String)httpSession.getAttribute("indicatorId");
		String pillCode=(String)httpSession.getAttribute("pillCode");
		
		String pillarId=(String)httpSession.getAttribute("pillarId");
		
		CommonMessage.debugMsg("+================================================================");
		CommonMessage.debugMsg("CompId=" + CompId);
		CommonMessage.debugMsg("factId=" + factId);
		CommonMessage.debugMsg("sectId=" + sectId);
		CommonMessage.debugMsg("cellId=" + cellId);
		CommonMessage.debugMsg("drillLevel=" + drillLevel);
		CommonMessage.debugMsg("indicatorId=" + indicatorId);
		CommonMessage.debugMsg("pillCode=" + pillCode);
		CommonMessage.debugMsg("pillarId=" + pillarId);

		CommonMessage.debugMsg("xxxxxxx================================================================");
		
		List<String[]> KPIList  =getDataforColmodel(request,response,httpSession);
		httpSession.getAttribute("KPIProdgridParams");
		GridParams gridparams= (GridParams) httpSession.getAttribute("KPIProdgridParams");
				
		String fromExcel="true";
		net.sf.json.JSONObject jsonObject=null;
		jsonObject=getTableModel(KPIList,fromExcel);
		
		Workbook wb = kpiTlIndicatorKkService.KPIProdFactExportExcel(jsonObject,format,CompId,factId,sectId,cellId,drillLevel,indicatorId,pillCode,pillarId,gridparams);
		String title="KPIProductionDepartmentLinkReport";
		ExcelUtils.writeToResponse(response, wb, title, format);
	}
	private net.sf.json.JSONObject getTableModelForNewindicator(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(1);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setAlign("left");
		   	jqGridColModel.setWidth(145);
			
			if(i==10 ||i==11){
				jqGridColModel.setAlign("center");
			}
			if(i==9){
				jqGridColModel.setAlign("right");
			}
			
			jqGridColModel.setEditable(false);
			//CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		net.sf.json.JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "81%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}
	private void validatekeyIndLink(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		String indicatorId = request.getParameter("indicatorId");
		kpid=indicatorId;
		
		String kpindiid[]=new String[20];
		 flId=request.getParameter("flId");
		String rowNo=request.getParameter("rowNo");
		String position=request.getParameter("position");
		Boolean isValidate=true;
		String msg="";
		CommonMessage.debugMsg("indicatorId: "+indicatorId+"flId"+flId+"rowNo"+rowNo+"position"+position);	
		KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink = new KpiTlIndicatorDeptLink();
		if(CommonFunctions.isValidKeyId(indicatorId))
			kpiTlIndicatorDeptLink.setKidlIndicatorid(indicatorId);
		if(CommonFunctions.isValidKeyId(flId))
			kpiTlIndicatorDeptLink.setKidlDeptid(flId);
		try {
			msg = kpiTlIndicatorKkService.validatekeyIndLink(kpiTlIndicatorDeptLink);
			
			
			//CommonMessage.debugMsg("isValidate : "+isValidate);	
			if (UIUtils.isValidKeyId(msg))
				isValidate=false;
		} 
		
		catch(Exception e)
		{				
			e.printStackTrace();
		}
		JSONObject successData = new JSONObject();
		//successData.put("isValidate",validate);
		JSONObject returnData = new JSONObject();
		returnData.put("isValidate", isValidate);
		returnData.put("rowNo", rowNo);
		returnData.put("position", position);
		returnData.put("msg", msg);
		//returnData.put("tpmException", "Training Area Already Referred ");	
		out.print(returnData.toString());
	}
	
	@SuppressWarnings("unchecked")
	private void validateKeyInActiveLink(HttpServletRequest request,HttpServletResponse response,PrintWriter out) throws Exception{
		String indicatorId=request.getParameter("indicatorId");
        kpid=indicatorId;
		String kpindiid[]=new String[20];
		flId=request.getParameter("flId");
		String rowNo=request.getParameter("rowNo");
		String position=request.getParameter("position");
		Boolean isValidate=true;
		String msg="";
		KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink = new KpiTlIndicatorDeptLink();
		if(CommonFunctions.isValidKeyId(indicatorId))
			kpiTlIndicatorDeptLink.setKidlIndicatorid(indicatorId);
		if(CommonFunctions.isValidKeyId(flId))
			kpiTlIndicatorDeptLink.setKidlDeptid(flId);
		try {
			msg = kpiTlIndicatorKkService.validateKeyInactiveListLink(kpiTlIndicatorDeptLink);
			if (UIUtils.isValidKeyId(msg))
				isValidate=false;
		} 
		
		catch(Exception e)
		{				
			e.printStackTrace();
		}
		JSONObject successData = new JSONObject();
		//successData.put("isValidate",validate);
		JSONObject returnData = new JSONObject();
		returnData.put("isValidate", isValidate);
		returnData.put("rowNo", rowNo);
		returnData.put("position", position);
		returnData.put("msg", msg);
		out.print(returnData.toString());
	
	}
	
	private void validatekeyActiveLink(HttpServletRequest request,HttpServletResponse response,PrintWriter out){
		String InactiveData="";
		String indicatorId=request.getParameter("indicatorId");
		CommonMessage.debugMsg("The indicatorId"+indicatorId);
		String flId=request.getParameter("Flid");
		CommonMessage.debugMsg("The flId"+flId);
		KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink = new KpiTlIndicatorDeptLink();
        
		if(UIUtils.isValidKeyId(indicatorId))
			kpiTlIndicatorDeptLink.setKidlIndicatorid(indicatorId);
         if(UIUtils.isValidKeyId(flId))		
        	 kpiTlIndicatorDeptLink.setKidlDeptid(flId);
        try
        {
        	InactiveData=kpiTlIndicatorKkService.validatekeyActiveLink(kpiTlIndicatorDeptLink);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
          
	}
}



