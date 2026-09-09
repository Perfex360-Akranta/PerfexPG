package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.IntRejEntryBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlChecklistcalanderreview;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.JhnTlSladtl;
import com.akranta.tpm.model.QtmTlInternalrejectionhourly;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.QtmTlIntrejectiondtl;
//import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.model.QtmTlTestscrapbrkup;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.service.IntRejEntryService;
import com.akranta.tpm.service.PcsEntryService;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.service.impl.IntRejEntryServiceImpl;
import com.akranta.tpm.service.impl.InternalRejectionRptServiceImpl;
import com.akranta.tpm.service.impl.PcsEntryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.PCSConstants;
import com.akranta.tpm.utils.PCSRownumConstants;

public class InternalRejectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static int count;
	private PcsEntryService pcsEntryService;     
	private IntRejEntryService intRejEntryService;
	InternalRejectionSerivceApi irServiceApi;
    
	public InternalRejectionServlet() {
    
        super();
    	/*try {
    		pcsEntryService = new PcsEntryServiceImpl();
    		intRejEntryService = new IntRejEntryServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		String action = UIUtils.getActionPart(request);
	
		ComboFilter comboFilter = new ComboFilter();
		ComboFilter currentFilter = new ComboFilter();
		comboFilter=UIUtils.fillComboFilter(request);

		try {
			pcsEntryService = (PcsEntryServiceImpl)UIUtils.getServiceObject(request,"PcsEntryServiceImpl");
			intRejEntryService = (IntRejEntryServiceImpl)UIUtils.getServiceObject(request,"IntRejEntryServiceImpl");
			intRejEntryService.IntRejEntryImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		
		CommonMessage.debugMsg("action :  "+action);
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		if (action.equals("InternalRejEntry_input.ire") && user != null) 
		{			
			CommonMessage.debugMsg("internalRejEntry_input.ire");
			// there is nothing to be done
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			request.setAttribute("pcsEnableDisableFormBean", pcsEnableDisableFormBean);
		}

		
		
		String dispatchUrl = null; 
		
		if (action.equals("internalRejection_input.ire") || action.equals("internalRejectionView_input.ire")) 
		{
			
			CommonMessage.debugMsg("internalRejection_input.ire");
			CommonMessage.debugMsg("machine id "+ request.getParameter("machId"));
			CommonMessage.debugMsg("date "+ request.getParameter("date"));
			CommonMessage.debugMsg("shift "+ request.getParameter("shift"));
			
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String machId = request.getParameter("machId");
			String cellId = request.getParameter("cellId");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			
		/*	entryDate="26-NOV-2013";
			shift="SFT001";
			sectId="LIN0000026";
			cellId="CEL0000066";
			machId="MCH0002057";*/
			
			dispatchUrl = "/pages/InternalRejection/internalRejectionCalendar.jsp";
			//dispatchUrl = "/pages/InternalRejection/intRejView.jsp";
			
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) 	 request.setAttribute("shift", shift);
			if (UIUtils.isValidKeyId(machId))	request.setAttribute("machId", machId);
			if (UIUtils.isValidKeyId(factId))	request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))	request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(cellId))	request.setAttribute("cellId", cellId);
			/*
			if (action.equals("internalRejectionView_input.ire"))
				request.setAttribute("mode", "view");
			else*/
				request.setAttribute("mode", "create");
		}
		
		if( action.equals("setFormActionMode.ire")) {

			CommonMessage.debugMsg("shift"+ request.getParameter("mode"));
			String mode = request.getParameter("mode");

    		IntRejEntryBean intRejEntryBean = (IntRejEntryBean)httpSession.getAttribute("InternalRejectionServletIntRejEntryBean");        	
			if (intRejEntryBean  == null )	
				intRejEntryBean  = new IntRejEntryBean();
			intRejEntryBean.setFormActionMode(mode);
			httpSession.removeAttribute("InternalRejectionServletIntRejEntryBean");
			httpSession.setAttribute("InternalRejectionServletIntRejEntryBean",intRejEntryBean);
			CommonMessage.debugMsg("intRejEntryBean.setFormActionMode()"+ intRejEntryBean.getFormActionMode());

		}
		if (action.equals("intRejView_input.ire")) 
		{
			CommonMessage.debugMsg("intRejView_input.ire");
			CommonMessage.debugMsg("machine id "+ request.getParameter("machId"));
			CommonMessage.debugMsg("cellid "+ request.getParameter("cellId"));
			CommonMessage.debugMsg("date "+ request.getParameter("date"));
			CommonMessage.debugMsg("shift"+ request.getParameter("shift"));

			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String machId = request.getParameter("machId");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String cellId = request.getParameter("cellId");
			String mode = request.getParameter("mode");
			
			dispatchUrl = "/pages/InternalRejection/intRejView.jsp";	
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) 	 request.setAttribute("shift", shift);
			if (UIUtils.isValidKeyId(machId))	request.setAttribute("machId", machId);
			if (UIUtils.isValidKeyId(factId))	request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))	request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(cellId))	request.setAttribute("cellId", cellId);
			if (UIUtils.isValidKeyId(mode))	request.setAttribute("mode", mode);
			
		}
		else if( action.equals("testingscrap_input.ire") ){	
			String rejectionId = request.getParameter("rejectionId");				
			String referenceId = request.getParameter("referenceId");	
			String inspectedQty = request.getParameter("inspectedQty");				
			String QAHoldQty = request.getParameter("QAHoldQty");	
			request.setAttribute("rejectionId", rejectionId);
			request.setAttribute("referenceId", referenceId);
			request.setAttribute("inspectedQty", inspectedQty);
			request.setAttribute("QAHoldQty", QAHoldQty);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/InternalRejection/TestingScrapBreakup.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("testingscrap_getCol.ire") )
		{
			CommonMessage.debugMsg("testingscrap_getCol.ire");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "testScrapcolModel"));
		}
		else if( action.equals("testingscrap_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("testingscrap_getData.ire");			
				String rejectionId = request.getParameter("rejectionId");				
				String referenceId = request.getParameter("referenceId");				
				PrintWriter out = response.getWriter();				
				List<String[]> scrapBreakupList = intRejEntryService.getScrapBreakup(rejectionId,referenceId);
				CommonMessage.debugMsg(scrapBreakupList.size());
  			 	JSONObject scrapBreakupData = UIUtils.convertToJqGridTableObject(scrapBreakupList,request,0,0);
  			 	CommonMessage.debugMsg(scrapBreakupData);
  			 	out.println(scrapBreakupData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("testingscrap_save.ire") ){	
			saveBreakup(request,response);			
		}
		else if (action.equals("pcsEntryView_input.ire")) 
		{
			CommonMessage.debugMsg("pcsEntryView_input.ire");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");			
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			
			dispatchUrl = "/pages/InternalRejection/internalRejectionView.jsp";	
			
			request.setAttribute("factId", factId);
			request.setAttribute("sectId", sectId);
			request.setAttribute("cellId", cellId);
			request.setAttribute("machId", machId);
			
		}
		
		else if (action.equals("pcsEntry_input.ire")) 
		{
			CommonMessage.debugMsg("pcsEntry_input.ire");	
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			String prdId = request.getParameter("prdId");
						
			dispatchUrl = "/pages/InternalRejection/internalRejectionCalendar.jsp";	
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("machId", machId);
			request.setAttribute("PrdId", prdId);
			
		}
		
		//for test 
		else if (action.equals("intRejEntryTest_input.ire")) 
		{  
			String masterid = request.getParameter("masterid");
			CommonMessage.debugMsg("inside the jsp - Test");
			String type = request.getParameter("types");
			CommonMessage.debugMsg("type"+type);
			//CommonMessage.debugMsg("masterid" + masterid);
			QtmTlIntrejectionmst qtmTlIntrejectionmst  = new QtmTlIntrejectionmst();
			
			if (UIUtils.isValidKeyId(masterid))
				qtmTlIntrejectionmst = intRejEntryService.getInternalRejectionMstdata(masterid);
				CommonMessage.debugMsg("equipment Id "+qtmTlIntrejectionmst.getQirmMachineid());
				
				if(qtmTlIntrejectionmst.getQirmMachineid() == null || qtmTlIntrejectionmst.getQirmMachineid().equalsIgnoreCase("{}")) 
				{
					qtmTlIntrejectionmst.setQirmMachineid("");
				}
				
				if(qtmTlIntrejectionmst.getQirmRemarks() == null || qtmTlIntrejectionmst.getQirmRemarks().equalsIgnoreCase("{}")) 
				{
					qtmTlIntrejectionmst.setQirmMachineid("");
				}
				String date = qtmTlIntrejectionmst.getQirmShiftdate();
				String inspectionDate = qtmTlIntrejectionmst.getQirmInspectiondate();
				qtmTlIntrejectionmst.setQirmInspectiondate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(inspectionDate));
				qtmTlIntrejectionmst.setQirmShiftdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
			//CommonMessage.debugMsg("masterkeyid" + qtmTlIntrejectionmst.getQirmKeyid());
			request.setAttribute("types",type);
			request.setAttribute("QtmTlIntrejectionmst",qtmTlIntrejectionmst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/InternalRejection/intRejEntryTest.jsp");
			rd.forward(request, response);
		}
		else if (action.equals("intRejEntryMst_input.ire")) 
		{
			String type = request.getParameter("types");
			request.setAttribute("types",type);
			CommonMessage.debugMsg("inside the jsp - Test");
			dispatchUrl = "/pages/InternalRejection/InternalRejectionMst.jsp";
			
		}
		else if (action.equals("intRejEntryMstGrid_getCol.ire")) 
		{
			CommonMessage.debugMsg("inside intRejEntryMstGrid_getCol.ire ");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = new JSONObject();
			
			CommonFilter commonFilter = new CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter);
			
			
			String flid = CommonFunctions.getLoginFlid(request);
			
			if (commonFilter.getFlid() != null)
			{
				commonFilter.setFlid(flid);
			}else
			{  
				commonFilter.setFlid(flid);
			}			
			
			
			
			List<String []> MstList  = intRejEntryService.getInternalRejectionMstGrid(commonFilter);
			
			
			//jsonObject = UIUtils.convertToJqGridTableObject(MstList, request, 0, 0);
			jsonObject = getTableModelForMstGrid(MstList);
			
			httpSession.removeAttribute("internalRejectionMst");
			httpSession.setAttribute("internalRejectionMst",jsonObject);
			CommonMessage.debugMsg("End  tablemodel jsonObject  "+jsonObject.toString());
			out.println(jsonObject);
			
			
		}
		else if (action.equals("intRejEntryMstGrid_getData.ire")) 
		{
			try
			{	
						
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				
				
				String flid = CommonFunctions.getLoginFlid(request);
				
				if (commonFilter.getFlid() != null)
				{
					commonFilter.setFlid(flid);
				}else
				{  
					commonFilter.setFlid(flid);
				}			
				
				
				List<String []> MstList  = intRejEntryService.getInternalRejectionMstGrid(commonFilter);
				CommonMessage.debugMsg(MstList.size());
  			 	JSONObject MstData = UIUtils.convertToJqGridTableObject(MstList,request,1,0);
  			 	//httpSession.removeAttribute("internalRejectionMst");
  				//httpSession.setAttribute("internalRejectionMst",MstData);
  			 	out.println(MstData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
			
		}	
		else if (action.equals("intRejEntryMstGrid_getExcel.ire")) 
		{					
				CommonFilter commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				
				
				String flid = CommonFunctions.getLoginFlid(request);
				
				if (commonFilter.getFlid() != null)
				{
					commonFilter.setFlid(flid);
				}else
				{  
					commonFilter.setFlid(flid);
				}		
				
				httpSession = request.getSession(false);
				JSONObject colModel = (JSONObject) httpSession.getAttribute("internalRejectionMst");
				colModel.put("title", "Internal Rejection Entry");
				
				String rptFormat = ExcelUtils.getFormat(request);
				
				CommonMessage.debugMsg("Colmodel :" +colModel + " " + rptFormat);
				
				Workbook wb = intRejEntryService.getMstGridExcelData(commonFilter, colModel, rptFormat);
					
				CommonMessage.debugMsg("Action :" +action + " " + wb);
				ExcelUtils.writeToResponse(response, wb,"InternalRejectionEntry", rptFormat);
				
			
		}
		
		else if (action.equals("intRejEntry_input.ire")) 
		{
			CommonMessage.debugMsg("inside the jsp");	
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			String prdId = request.getParameter("prdId");
			String sectId = request.getParameter("sectId");
			String factId = request.getParameter("factId");
			String totalProduced = request.getParameter("totalProduced");
			String Plmasterid = request.getParameter("Plmasterid");
			String Pldetailsid = request.getParameter("Pldetailsid");
			String QirmKeyid = request.getParameter("QirmKeyid");	
			String QihbKeyid = request.getParameter("QihbKeyid");
			
			String acceptedQty = request.getParameter("acceptedQty");			
			String rejectedQty = request.getParameter("rejectedQty");
			String inspectedBy = request.getParameter("inspectedBy");
			String inspectedDate = request.getParameter("inspectedDate");
			String inspectedShift = request.getParameter("inspectedShift");
			String inspectedQty = request.getParameter("inspectedQty");
			String expansionQty = request.getParameter("expansionQty");
			
			String parentId = request.getParameter("parentId");
			String linkId = request.getParameter("linkId");
			
			String reportLocation = request.getParameter("reportLocation");
			
			CommonMessage.debugMsg("QirmKeyid:"+QirmKeyid);
			CommonMessage.debugMsg("inspectedQty:"+inspectedQty);
					
			if (reportLocation.equals("HALOL")) {
				dispatchUrl = "/pages/InternalRejection/intRejHourBreak.jsp";
			}
			else {
				dispatchUrl = "/pages/InternalRejection/intRejEntry.jsp";
				request.setAttribute("reportType", "NORMAL");
			}
			
			String reportType = request.getParameter("reportType");
			
			request.setAttribute("reportType", reportType);			
			request.setAttribute("reportLocation", reportLocation);
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("cellId", cellId);
			request.setAttribute("machId", machId);
			request.setAttribute("PrdId", prdId);
			request.setAttribute("sectId", sectId);
			request.setAttribute("factId", factId);
			request.setAttribute("totalProduced", totalProduced);
			request.setAttribute("Plmasterid", Plmasterid);
			request.setAttribute("Pldetailsid", Pldetailsid);
			request.setAttribute("QirmKeyid", QirmKeyid);
			request.setAttribute("QihbKeyid", QihbKeyid);
			
			request.setAttribute("acceptedQty", acceptedQty);
			request.setAttribute("rejectedQty", rejectedQty);
			request.setAttribute("inspectedBy", inspectedBy);
			request.setAttribute("inspectedDate", inspectedDate);
			request.setAttribute("inspectedShift", inspectedShift);
			request.setAttribute("inspectedQty", inspectedQty);
			request.setAttribute("parentId", parentId);
			request.setAttribute("linkId", linkId);
			
			if (UIUtils.isValidKeyId(request.getParameter("expansionQty")))			
				request.setAttribute("expansionQty", request.getParameter("expansionQty"));
			
			if (UIUtils.isValidKeyId(request.getParameter("processId")))
				request.setAttribute("processId", request.getParameter("processId"));
			if (UIUtils.isValidKeyId(request.getParameter("phenomenaId")))
				request.setAttribute("phenomenaId", request.getParameter("phenomenaId"));
			
		}
		
		else if (action.equals("pcsLossEntry_input.ire")) 
		{
			CommonMessage.debugMsg("inside the  pcsLossEntry_input.pcs");	
			String entryDate = request.getParameter("date");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			String prdId = request.getParameter("prdId");
			String lossParentId = request.getParameter("lossParentId");
			String lossId = request.getParameter("lossId");
			String isQtyLoss = request.getParameter("isQtyLoss");
			
			String Pldetailsid = request.getParameter("Pldetailsid");
						
			dispatchUrl = "/pages/InternalRejection/internalRejectionLossEntry.jsp";	
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("SectId", sectId);
			request.setAttribute("FactId", factId);
			request.setAttribute("machId", machId);
			request.setAttribute("PrdId", prdId);
			request.setAttribute("Pldetailsid", Pldetailsid);
			request.setAttribute("lossParentId", lossParentId);
			request.setAttribute("lossId", lossId);
			request.setAttribute("isQtyLoss", isQtyLoss);			

		}
		
		else if( action.equals("getDateOeeValue.ire"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String detailTable = request.getParameter("detailTable");
				String sectId = request.getParameter("sectId");		
				String cellId = request.getParameter("cellId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				List<String[]> oeeDatesList = pcsEntryService.getDateOeeValue(detailTable,sectId,cellId,mchId,entryDate);
				
				JSONObject oeeObj = new JSONObject();
				
				JSONArray rowArr = new JSONArray();
				for (int i = 0; i < oeeDatesList.size(); i++) {
					JSONObject rowObj = new JSONObject();					
					rowObj.put("oeeDate", oeeDatesList.get(i)[0]);
					rowObj.put("oeeShift", oeeDatesList.get(i)[1]);
					rowObj.put("oeeValue", oeeDatesList.get(i)[2]);
					rowObj.put("qrValue", oeeDatesList.get(i)[3]);
					rowArr.put(rowObj);
				}
				oeeObj.put("oeeData", rowArr);
				CommonMessage.debugMsg("JSON : "+oeeObj);
				out.print(oeeObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		
		else if (action.equals("pcsResult_input.ire")) 
		{
			CommonMessage.debugMsg("inside the pcsResult_input.pcs");	
			String dataStr = request.getParameter("dataStr");
						
			dispatchUrl = "/pages/pcs/pcsResult.jsp";	
			
			CommonMessage.debugMsg("dataStr:"+dataStr);
			request.setAttribute("dataStr", dataStr);
		}	
		
		else if(action.equals("functionalLoc_Calendar.ire")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("IntRejEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}				

		
		else if(action.equals("functionalLoc_Entry.ire")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbPrlmFactoryid");
			functLocFieldNameBean.setSection("cmbPrlmSectionid");
			functLocFieldNameBean.setCell("cmbPrlmCellid");
			functLocFieldNameBean.setMachine("cmbPrlmMachineid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("IntRejEntryFormMode");			
			//if( formModes == FormModes.completion)
			formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}	
		
		else if( action.equals("getIsPcsEnabled.ire"))
		{
			try {
				PrintWriter out = response.getWriter();
				String condParam = request.getParameter("condParam");
				CommonMessage.debugMsg("condParam"+condParam);				
				List<String[]> pcsEnabled = pcsEntryService.getIsPcsEnabled(condParam);
				CommonMessage.debugMsg(pcsEnabled.size());
				
  			 	JSONObject pcsData =new JSONObject();
  			 	if (pcsEnabled.size()>0) {
	  			 	CommonMessage.debugMsg("pcsEnabled.get(0)"+pcsEnabled.get(0)[0]);
	  			 	pcsData.put("isPcsEnabled", pcsEnabled.get(0)[0]);
	  			 	pcsData.put("qtyOrTimeBased", pcsEnabled.get(0)[1]);
	  			 	pcsData.put("isGroupBased", pcsEnabled.get(0)[2]);
	  			 	pcsData.put("isShtLog", pcsEnabled.get(0)[3]);
	  			 	pcsData.put("type", pcsEnabled.get(0)[4]);
	  			 	pcsData.put("option", pcsEnabled.get(0)[6]);
	  			 	pcsData.put("sftDayWeek", pcsEnabled.get(0)[7]);
  			 	}
  			 	else
  			 		pcsData.put("isPcsEnabled", "N");
				out.print(pcsData);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}		
		
		else if( action.equals("checkEquipmentFailure.ire"))
		{
			try {
				PrintWriter out = response.getWriter();
				String lossId = request.getParameter("lossId");
				CommonMessage.debugMsg("lossId"+lossId);				
				List<String[]> isEF = pcsEntryService.checkEquipmentFailure(lossId);
				CommonMessage.debugMsg(isEF.size());
				
  			 	JSONObject efData =new JSONObject();
  			 	if (isEF.size()>0) {
	  			 	CommonMessage.debugMsg("isEF.get(0)"+isEF.get(0)[0]);
	  			 	efData.put("isEF", isEF.get(0)[0]);
  			 	}
  			 	else
  			 		efData.put("isEF", "");
  			 	out.print(efData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("combo_productModel.ire"))
		{
			try {						
				String factId = request.getParameter("factId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				CommonMessage.debugMsg("factId"+factId);				
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> prdModel = pcsEntryService.getproductModelCombo(comboFilter, factId,mchId,entryDate);
				UIUtils.writeComboBox(response,prdModel, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		else if( action.equals("combo_shift.ire"))
		{
			try {						
				String factId = request.getParameter("factId");
				CommonMessage.debugMsg("factId"+factId);			
				currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> prdModel = pcsEntryService.getShift(currentFilter, factId);
				UIUtils.writeComboBox(response,prdModel, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_product.ire"))
		{
			try {						
				String factId = request.getParameter("factId");
				String prdModelId = request.getParameter("prdModelId");
				String machId = request.getParameter("machId");
				String entryDate = request.getParameter("entryDate");
				
				CommonMessage.debugMsg("factIdfactId"+factId);
				comboFilter = UIUtils.fillComboFilter(request);
	
				List<ComboBox>  assembly = intRejEntryService.getproductCombo(comboFilter,factId, machId,  prdModelId,entryDate,"");
				UIUtils.writeComboBox(response, assembly, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_Loss.ire"))
		{
			try {						

				String isQtyLoss = request.getParameter("isQtyLoss");
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getLossCombo(comboFilter, isQtyLoss,"");
				UIUtils.writeComboBox(response, assembly, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		
		else if( action.equals("combo_Wno.ire"))
		{
			try {						
				String Pldetailsid = request.getParameter("Pldetailsid");
				String entryDate = request.getParameter("entryDate");
				String sectId="";
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  wno = pcsEntryService.getWno(comboFilter, sectId, Pldetailsid,entryDate);
				UIUtils.writeComboBox(response, wno, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("combo_process.ire"))
		{
			try {
				List<ComboBox>  process = intRejEntryService.getProcessCombo(comboFilter);
				UIUtils.writeComboBox(response, process, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("combo_Phenomena.ire"))
		{
			try {
				String processId = request.getParameter("processId");
				List<ComboBox>  qtyPhen = intRejEntryService.getPhenomena(processId, comboFilter);
				UIUtils.writeComboBox(response, qtyPhen, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
	
		else if( action.equals("combo_cause.ire"))
		{
			try {
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  cause = pcsEntryService.getCause(comboFilter, "Y","");
				UIUtils.writeComboBox(response, cause, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("combo_rootcause.ire"))
		{
			try {
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  rootcause = pcsEntryService.getRootCause(comboFilter);
				UIUtils.writeComboBox(response, rootcause, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getEntryExistsDates.ire"))
		{
			try {	
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("entryDate");
				String cellId = request.getParameter("cellId");
				String machId = request.getParameter("machId");				
				String detailTable = request.getParameter("detailTable");
				
				List<String[]> entryDatesList = new ArrayList<String[]>();
				//entryDatesList=pcsEntryService.getEntryExistsDates(detailTable,entryDate, machId);
				entryDatesList=intRejEntryService.getEntryExistsDates(detailTable,entryDate, cellId, machId);
				JSONObject eedObj = new JSONObject();
				
				JSONArray rowArr = new JSONArray();
				for (int i = 0; i < entryDatesList.size(); i++) {
					JSONObject rowObj = new JSONObject();
					rowObj.put("compFlg", entryDatesList.get(i)[0]);
					rowObj.put("shiftKeyId", entryDatesList.get(i)[1]);
					rowObj.put("shiftOrder", entryDatesList.get(i)[2]);
					rowObj.put("entryDate", entryDatesList.get(i)[3]);
					rowObj.put("rejExists", entryDatesList.get(i)[4]);
					rowObj.put("intRejExists", entryDatesList.get(i)[5]);
					rowArr.put(rowObj);
				}
				//JSONObject entryDatesData = UIUtils.convertToJqGridTableObject(entryDatesList,request,0,0);
				//plmData.put("entryDates",entryDatesList );
				eedObj.put("eedData", rowArr);
				CommonMessage.debugMsg("JSON : "+eedObj);
				out.print(eedObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}				
		}			
		else if( action.equals("getPendingColor.ire"))
		{
			try {	
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("entryDate");
				String cellId = request.getParameter("cellId");
				String machId = request.getParameter("mchId");		
				String detailTable = request.getParameter("detailTable");	
				List<String[]> pendingColList = intRejEntryService.getPendingColor(entryDate,cellId,machId,detailTable);
				
				JSONObject pendColObj = new JSONObject();
				JSONArray rowArr = new JSONArray();
				
				for (int i = 0; i < pendingColList.size(); i++) {
					JSONObject rowObj = new JSONObject();
					rowObj.put("entryDate", pendingColList.get(i)[0]);
					rowObj.put("shiftKeyId", pendingColList.get(i)[1]);
					rowObj.put("balQty", pendingColList.get(i)[2]);
					rowArr.put(rowObj);
				}
				
				pendColObj.put("pendColData", rowArr);
				CommonMessage.debugMsg("JSON : "+pendColObj);
				out.print(pendColObj);				
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}			
		else if( action.equals("getHolidayDates.ire"))
		{
			try {				
				
				PrintWriter out = response.getWriter();				
				String factId = request.getParameter("factId");		
				String entryDate = request.getParameter("entryDate");
				List<String[]> holidayDatesList = pcsEntryService.getHolidayDates(factId,entryDate);
				
				JSONObject holiObj = new JSONObject();
				
				JSONArray rowArr = new JSONArray();
				for (int i = 0; i < holidayDatesList.size(); i++) {
					JSONObject rowObj = new JSONObject();					
					rowObj.put("holidayDate", holidayDatesList.get(i)[0]);
					rowObj.put("holidayFlag", holidayDatesList.get(i)[1]);
					rowArr.put(rowObj);
				}
				holiObj.put("holiData", rowArr);
				CommonMessage.debugMsg("JSON : "+holiObj);
				out.print(holiObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		
		
		else if( action.equals("getMasterKeyid.ire"))
		{
			try {						
				
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");				
				String machId = request.getParameter("machId");
				String Plmasterid   = pcsEntryService.getMasterKeyid(entryDate, shift, machId);
				JSONObject plmData =new JSONObject();
				plmData.put("Plmasterid", Plmasterid);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("getShiftKeyid.ire"))
		{
			try {					
				
				PrintWriter out = response.getWriter();				
				String factId = request.getParameter("factId");
				String shiftCode = request.getParameter("shiftCode");
				
				String shiftId = pcsEntryService.getShiftKeyid(factId, shiftCode);
				JSONObject shiftData =new JSONObject();
				if (UIUtils.isValidKeyId(shiftId))
					shiftData.put("shift", shiftId);
				else
					shiftData.put("shift", "");
				
				out.print(shiftData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
					
		else if( action.equals("getCalendarTime.ire"))
		{
			try {						
				PrintWriter out = response.getWriter();
				
				String machineId = request.getParameter("machineId");
				String plmasterId = request.getParameter("plmasterId");
				String detailTable = request.getParameter("detailTable");	
				String entryDate = request.getParameter("entryDate");
								
				String calendarTime   = pcsEntryService.getCalendarTime(machineId, plmasterId, detailTable,entryDate);
				JSONObject plmData =new JSONObject();
				plmData.put("calendarTime", calendarTime);
				out.print(plmData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
				

		else if( action.equals("getDetailTableName.ire"))
		{
			try {
				PrintWriter out = response.getWriter();				
				String sectId = request.getParameter("sectId");				
				String entryDate = request.getParameter("entryDate");
				String detailTableName  = pcsEntryService.getDetailTableName(sectId,entryDate);
				JSONObject plmData =new JSONObject();
				plmData.put("detailTableName", detailTableName);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("getIsHourlyEntry.ire"))
		{
			try {
				CommonMessage.debugMsg("getIsHourlyEntry serv;et");
				PrintWriter out = response.getWriter();				
				String hourlyEntry  = intRejEntryService.getIsHourlyEntry();
				CommonMessage.debugMsg("hourlyEntry servt: " +hourlyEntry);
				JSONObject plmData =new JSONObject();
				plmData.put("value", hourlyEntry);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		
		else if( action.equals("combo_Phenomena.ire"))
		{
			try {						
				String lossId = request.getParameter("lossId");
				String cellid = request.getParameter("getCellid");
				String flid = request.getParameter("flid");
				currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getPhenomenaCombo(currentFilter, lossId,cellid, flid);
				UIUtils.writeComboBox(response, assembly, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
	
		else if( action.equals("combo_Cycletime.ire"))
		{
			try {						
				
				String productId = request.getParameter("productId");
				String machineId = request.getParameter("machineId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("date");
				String sectionId = request.getParameter("sectId");
				comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getCycletimeCombo(comboFilter, cellId, machineId, productId, entryDate,sectionId);
				UIUtils.writeComboBox(response, assembly, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		
		else if( action.equals("updatePldRemarks.ire"))
		{
			try {
				PrintWriter out = response.getWriter();			
				
				String detailTable = request.getParameter("detailTable");
				String plDetailsid = request.getParameter("plDetailsid");
				String remarks = request.getParameter("remarks");
				
				String updtFlg = pcsEntryService.updatePldRemarks(detailTable, plDetailsid, remarks);
				JSONObject shiftData =new JSONObject();
				shiftData.put("updtFlg", updtFlg);
				out.print(shiftData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("checkQirm.ire"))
		{
			try {
				PrintWriter out = response.getWriter();			
				
				String QirmPldetailsid = request.getParameter("QirmPldetailsid");
				String QirmProductid = request.getParameter("QirmProductid");
				
				String qirmKeyid = intRejEntryService.checkQirm(QirmPldetailsid,QirmProductid);
				JSONObject qirmData =new JSONObject();
				qirmData.put("QirmKeyid", qirmKeyid);
				out.print(qirmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		
		CommonMessage.debugMsg(" dispatchUrl " + dispatchUrl);
		if (dispatchUrl != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			CommonMessage.debugMsg(" response " + response); 
		}	
		
				

		/*else if(action.equals("functionalLoc.pcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("IntRejEntryFormMode");			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}	*/
		
		
		else if( action.equals("intRejViewGrid_getCol.ire") )
		{
			//CommonMessage.debugMsg("intRejGrid_getCol.ire");
			//PrintWriter out = response.getWriter();
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "intRejViewcolModel"));
			
			PrintWriter out = response.getWriter();
			JSONObject jsonObjectd = null;
			
			String condParam = request.getParameter("condParam");
			String sectId = request.getParameter("sectId");
			String entryDate = request.getParameter("entryDate");
			String detailTableName = pcsEntryService.getDetailTableName(sectId,entryDate);
			CommonMessage.debugMsg("condParam"+condParam);
			CommonMessage.debugMsg("detailTableName"+detailTableName);
			
			List<String []> calendarList  = intRejEntryService.getInternalRejectionViewGrid(condParam,detailTableName);
			jsonObjectd = UIUtils.convertToJqGridTableObject(calendarList,request,1,0);

			JSONObject jsonObject = getTableModel(calendarList);
			httpSession.removeAttribute("IncdntColModel");
	 	    httpSession.setAttribute("IncdntColModel",jsonObject);
	 	   CommonMessage.debugMsg("jsonObject"+jsonObject);
	 	   out.println(jsonObject);
			
		}
		else if( action.equals("intRejViewGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("intRejGrid_getData.ire");
				
				String condParam = request.getParameter("condParam");
				String sectId = request.getParameter("sectId");
				String entryDate = request.getParameter("entryDate");				
				String detailTableName = pcsEntryService.getDetailTableName(sectId,entryDate);
				CommonMessage.debugMsg("condParam"+condParam);
				CommonMessage.debugMsg("detailTableName"+detailTableName);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("outttt");
				//List<String []> bdMasterList  = pcsEntryService.getParameters(factId);
				List<String []> calendarList  = intRejEntryService.getInternalRejectionViewGrid(condParam,detailTableName);
				CommonMessage.debugMsg(calendarList.size());
  			 	JSONObject calendarData = UIUtils.convertToJqGridTableObject(calendarList,request,0,0);
  			 	CommonMessage.debugMsg(calendarData);
  			 	out.println(calendarData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		
		else if( action.equals("intRejHourBreakGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("intRejHourBreakGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "intRejHourBreakcolModel"));
		}
		else if( action.equals("intRejHourBreakGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("intRejHourBreakGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String qirmKeyid = request.getParameter("qirmKeyid");
				CommonMessage.debugMsg("qirmKeyid ="+qirmKeyid );

				List<String []> hourList  = intRejEntryService.getIntRejHourBreakGrid(qirmKeyid);
				CommonMessage.debugMsg(hourList.size());
  			 	JSONObject hourData = UIUtils.convertToJqGridTableObject(hourList,request,0,0);
  			 	CommonMessage.debugMsg(hourData);
  			 	out.println(hourData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		
		
		else if( action.equals("intRejEntryGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("intRejEntryGrid_getCol.ire");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "intRejEntrycolModel"));
		}
		else if( action.equals("intRejEntryGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("intRejEntryGrid_getData.ire");
				String condParam = request.getParameter("condParam");
				//String sectId = request.getParameter("sectId");
				//String detailTableName = pcsEntryService.getDetailTableName(sectId);
				CommonMessage.debugMsg("condParam"+condParam);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("outttt");
				List<String []> ppcList  = intRejEntryService.getInternalRejectionEntryGrid(condParam);
				CommonMessage.debugMsg(ppcList.size());
  			 	JSONObject ppcData = UIUtils.convertToJqGridTableObject(ppcList,request,0,0);
  			 	CommonMessage.debugMsg(ppcData);
  			 	out.println(ppcData);
  			 	
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}				
	
		
		else if( action.equals("intRejEntryTestGrid_getCol.ire") )
		{
			/*CommonMessage.debugMsg("intRejEntryGrid_getCol.ire");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "intRejEntryTestcolModel"));*/
		
			
			CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeRCommonFilter",true);
			String condParam = request.getParameter("condParam");	
			CommonMessage.debugMsg("condParam" + condParam);
			PrintWriter out = response.getWriter();
			
			
			List<String[]> checklist = intRejEntryService.getInternalRejectionEntryTestGrid(condParam,commonFilter);
			
		
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			GridColModel gridColModel = new GridColModel();
			
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setMultiSelect(true);
			jqGridTableModel.setGridEdit(true);
			
			gridColModel.setHeaderNum(1);
			
			String [] colHeader = checklist.get(1);			
			
			String [] colHeaderCond = checklist.get(0);
			
			List<String[]> headers = new ArrayList<String[]>();
			
			headers.add(colHeader);
		
			JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
			
			httpSession.setAttribute("intRejTestGridColModel", jsonObject);
			
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "58%%");
			
			out.println(jsonObject);
		}
		
	/*	else if(action.equals("intRejEntryTest_save.ire"))
		{
			
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst loginuser = UIUtils.getLoginUser(request);
			if( httpSession != null && user != null){ 
				
				String griddata =  request.getParameter("griddata");
				//CommonMessage.debugMsg("griddata" + griddata );
				String savemsg="";
				JSONObject successData=new JSONObject();
				JSONObject returnData=new JSONObject();
				QtmTlIntrejectionmst newQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
				List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl = null;
    			List<QtmTlIntrejectiondtl> exitQtmTlIntrejectiondtl = null;
    			
    			
	    		QtmTlIntrejectiondtl qtmTlIntrejectiondtl = new QtmTlIntrejectiondtl();
	    		
	    		try
	    		{
	    			if( griddata != null && ! griddata.isEmpty())
		    		{     //CommonMessage.debugMsg("Inside 1 ");
	    				
	    	    		//QtmTlIntrejectiondtl newQtmTlIntrejectiondtl = new QtmTlIntrejectiondtl();
	    				
	    				newQtmTlIntrejectionmst =(QtmTlIntrejectionmst)UIUtils.setBeanProperties((Object)newQtmTlIntrejectionmst,request);
	    				
	    	    		JSONArray qtmTlIntrejectiondtlJson = null;
		    			
	    	    		if(UIUtils.isValidKeyId(griddata))
		    			{	    		 
	    	    			
	    	    			qtmTlIntrejectiondtlJson = JSONArray.fromString(griddata);						
	    	    			newQtmTlIntrejectiondtl=(List<QtmTlIntrejectiondtl>)UIUtils.convertJSONArrToList(qtmTlIntrejectiondtl, qtmTlIntrejectiondtlJson);
		    						
			    		}	
		    		}
	    			
	    			 //CommonMessage.debugMsg("Inside 2 ");
	    			 
	    			 if ( newQtmTlIntrejectiondtl == null)
	    				 throw new BusinessApplicationExceptions("sel_Details"+",");
	    			 
	    			 //CommonMessage.debugMsg("Qirm Keyid =  "+ newQtmTlIntrejectionmst.getQirmKeyid());
	    			 ////CommonMessage.debugMsg("Qirm Keyid =  "+ newQtmTlIntrejectiondtl.get(0).getQirdKeyid());
	    					 
	    	    		if (newQtmTlIntrejectiondtl!=null && newQtmTlIntrejectionmst != null )
			    		{ 
			    				for(QtmTlIntrejectiondtl newqtmTlIntrejectiondtl : newQtmTlIntrejectiondtl)
			    				{
			    					newqtmTlIntrejectiondtl.setQirdCreatedby(user.getUsrm_ccno());
			    				}
			    				
			    				//CommonMessage.debugMsg("Keyid" + newQtmTlIntrejectionmst.getQirmKeyid());
			    				newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
			    				
			    				/*if (UIUtils.isValidKeyId(newQtmTlIntrejectionmst.getQirmKeyid()))
			    				{  
			    					//CommonMessage.debugMsg("Inside Update");
			    					newQtmTlIntrejectionmst = intRejEntryService.update(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl);
			    					savemsg ="Data Updated successfully";
			    				}
			    				else
			    				{ 	
			    					 //CommonMessage.debugMsg("Inside Create");
				    				 newQtmTlIntrejectionmst = intRejEntryService.create(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl);
				    				 savemsg ="Data saved  successfully";
			    					
			    				//}
			    				 successData.put("msg", savemsg);
					        	 returnData.put("successData",successData);
					        	 returnData.put("formClear",false);
					    		 out.println(returnData.toString());
			    		}
	    	    	}
	    	    		
	    	
			catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"intRejEntryCreation");
				out.print(errMessage.toString());
	
			}
	    		catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		else if( action.equals("intRejEntryTestGrid_getData.ire") )
		{
			try
			{	
				
			/*	//CommonMessage.debugMsg("master keyid" + keyid);
				//FilterValues.getCommonFilters(request, commonFilter1);
				String filepath = UIUtils.getImagePath(request);
				List<String[]> checklist = auditCheckListService.getChecklistpopup(keyid);
				PrintWriter out = response.getWriter();
				JSONObject Gridmod = UIUtils.convertToJqGridTableObject(checklist, request, 2,0);
				out.println(Gridmod);*/
				CommonFilter commonFilter = populateCommonFilter(request,"AbnCumulativeRCommonFilter",true);
				
				CommonMessage.debugMsg("intRejEntryGrid_getData.ire");
				String condParam = request.getParameter("condParam");
				//String sectId = request.getParameter("sectId");
				//String detailTableName = pcsEntryService.getDetailTableName(sectId);
				CommonMessage.debugMsg("condParam"+condParam);
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("outttt");
				List<String []> ppcList  = intRejEntryService.getInternalRejectionEntryTestGrid(condParam,commonFilter);
				CommonMessage.debugMsg(ppcList.size());
  			 	JSONObject ppcData = UIUtils.convertToJqGridTableObject(ppcList,request,2,0);
  			 	CommonMessage.debugMsg(ppcData);
  			 	out.println(ppcData);
  			 	
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}				
	
		
		else if(action.equals("intRejEntryTestGrid_getExcel.ire"))
		{
			//HttpSession httpSession = request.getSession(false);
			httpSession = request.getSession(false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("intRejTestGridColModel");
			colmodel.put("title","InternalRejection");
	        String format = ExcelUtils.getFormat(request);
	        String condParam = request.getParameter("condParam");
			Workbook wb = intRejEntryService.intRejExportExcel(condParam, colmodel, format);
			String Header = "Internal Rejection " ;
			ExcelUtils.writeToResponse(response,wb,Header, format);
		}
		
		else if( action.equals("monthGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("monthGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "monthcolModel"));
		}
		else if( action.equals("monthGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("monthGrid_getdata.ire");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				String factId = request.getParameter("factId");				
				String date = request.getParameter("date");				
				String userId = user.getUsrm_ccno();
				PrintWriter out = response.getWriter();
				//List<String []> bdMasterList  = pcsEntryService.getParameters(factId);
				List<String []> calendarList  = pcsEntryService.getCalendar(factId,date,userId);
				CommonMessage.debugMsg(calendarList.size());
  			 	JSONObject calendarData = UIUtils.convertToJqGridTableObject(calendarList,request,0,0);
  			 	CommonMessage.debugMsg(calendarData);
  			 	out.println(calendarData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("productionGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("monthGrid_getdata.ire");
			CommonFilter commonFilter  = new CommonFilter();
			commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			PrintWriter out = response.getWriter();
			String factId = request.getParameter("factId");
			String shiftId = request.getParameter("shiftId");
			String sectId = request.getParameter("sectId");
			String entryDate = request.getParameter("entryDate");
			String cellId = request.getParameter("cellId");
			String machId = request.getParameter("machId");
			CommonMessage.debugMsg(factId +"-"+shiftId+"-"+"-"+entryDate+"-"+cellId+"--"+sectId);
/*			
			List<String []> pcsMasterList  = pcsEntryService.getParameters(factId);
			List<String []> pcsMasterList2  = pcsEntryService.getProdLoss(shiftId,entryDate,sectId, cellId);
			
			//JSONObject pcsMasterData = UIUtils.convertToJqGridTableObject(pcsMasterList,request,0,0);
			//JSONObject pcsMasterData = fillPCSGrid(pcsMasterList,pcsMasterList2,request);
			JSONObject pcsMasterData = fillPCS(pcsMasterList,pcsMasterList2,request);

		    CommonMessage.debugMsg(pcsMasterData);
			out.println(pcsMasterData);*/
			//PrintWriter out = response.getWriter();
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "productioncolModel"));
		}
		else if( action.equals("productionGrid_getData.ire") )
		{
			try
			{	
				 PrintWriter out = response.getWriter();
				 JSONObject jsonObject = new JSONObject();
				 jsonObject = (JSONObject) httpSession.getAttribute("pcsEntryDataServlet");
				 CommonMessage.debugMsg(jsonObject);
	        	
				 out.println(jsonObject);
				

			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("pcsEntryGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("pcsEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "IntRejViewcolModel"));
		}
		else if( action.equals("pcsEntryGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsentryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String sectId = request.getParameter("sectId");
				CommonMessage.debugMsg("sectId123="+sectId);
				String machId = request.getParameter("machId");
				CommonMessage.debugMsg("machId"+ machId);
				List<String []> sublossList  = pcsEntryService.getEntryGrid(entryDate,shift,sectId, machId);
				CommonMessage.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonMessage.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		

		
	
			
		else if( action.equals("pcsLossEntryGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "IntRejLossEntrycolModel"));
		}
		else if( action.equals("pcsLossEntryGrid_getData.ire") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsLossEntryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");
				CommonMessage.debugMsg("pldetailsid"+ pldetailsid);
				List<String []> sublossList  = pcsEntryService.getSubLossGrid(pldetailsid);
				CommonMessage.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonMessage.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		

		else if( action.equals("pcsResultGrid_getCol.ire") )
		{
			CommonMessage.debugMsg("pcsResultGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.IntRejColModel", "IntRejResultcolModel"));
		}
		else if( action.equals("pcsResultGrid_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsResultGrid_getData.ire");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				String machId = request.getParameter("machId");	
				CommonMessage.debugMsg("entryDate"+ entryDate);
				List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, machId);
				CommonMessage.debugMsg(pcsResultList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsResultList,request,0,0);
  			 	CommonMessage.debugMsg(pcsResultData);
  			 	out.println(pcsResultData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}	
		else if( action.equals("internalRejection_save.ire")|| action.equals("intRejEntryTest_save.ire")) { 
			CommonMessage.debugMsg(" inside save " );
			saveIntRejEntry(request,response);					
		}
		else if( action.equals("intRejEntry_save.ire")) { 
			CommonMessage.debugMsg(" inside save " );
			saveIntRejectionEntry(request,response);					
		}



		else if( action.equals("internalHourlyBreak_save.ire")) { 
			CommonMessage.debugMsg(" inside save " );
			saveIntHourlyBreak(request,response);					
		}

		else if( action.equals("pcsEntry_delete.ire"))
		{
			CommonMessage.debugMsg(" inside delete pcsentry delete " );			
			deletePcsEntry(request,response);
		}		

		

	}
    
	private void deletePcsEntry(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String plmasterId = request.getParameter("plmasterId");
    	String pldetailsId = request.getParameter("pldetailsId");
    	String machKeyId = request.getParameter("machId");
    	String entryDate = request.getParameter("entryDate");	
    	String tableName = request.getParameter("tableName");
    	
    	if( httpSession != null && user != null)
    	{

    		IntRejEntryBean intRejEntryBean = (IntRejEntryBean)httpSession.getAttribute("InternalRejectionServletIntRejEntryBean");    		

	    	CommonMessage.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			successData.put("keyId", pldetailsId);
			JSONObject returnData = new JSONObject();
			try{

	    		if (UIUtils.isValidKeyId(intRejEntryBean.getFormActionMode()) && ! intRejEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
	    		

				String deleteResult;
				deleteResult = pcsEntryService.deletePcsEntry(tableName, plmasterId, pldetailsId, "",machKeyId,entryDate);					
				//httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);			
				httpSession.setAttribute("deleteResult", deleteResult);		
				
				if (deleteResult.equals("Exists"))
						successData.put("msg","Loss Link Exists Can not Delete");
								
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreationException");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
    	}		
	}

	
	private JSONObject getMStTableModel(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(0);
		jqGridTableModel.getRowHeaders().add(row);	
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setEditable(false);
			CommonMessage.debugMsg(i + " : "+row[i]);			
			jqGridColModel.setWidth(100);			
			jqGridColModel.setAlign("center");
			
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
	
	private JSONObject getTableModelForMstGrid(List<String[]>  headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		//CommonMessage.debugMsg("headers.size()" + headers.size());
		String[] colHeader = headers.get(0);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(300);
		jqGridTableModel.setTableWidth(600);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setEnableFilter(true);
		String[] colIndex = headers.get(0);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colIndex[i].replaceAll(" ", " "));
			jqGridColModel.setName(colIndex[i].replaceAll(" ", " "));
		   	jqGridColModel.setWidth(160);				
			jqGridColModel.setAlign("left");
			jqGridColModel.setEditable(false);
			//CommonMessage.debugMsg("column["+i+"]::::"+colHeader[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
			if(i==0  )
			{
				jqGridColModel.setHidden(true);
			}
			
			if(i == 1  )
			{
				jqGridColModel.setWidth(560);
			}
			
			
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "108%%");
		CommonMessage.debugMsg("End  tablemodel");
		return tableModel;
	}
	
	private JSONObject getTableModel(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(0);
		jqGridTableModel.getRowHeaders().add(row);	
		
		jqGridTableModel.setTableButton(true);		 
		jqGridTableModel.setTableHeight(290);
		jqGridTableModel.setRowNumbers(true);
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setEditable(false);
			CommonMessage.debugMsg(i + " : "+row[i]);
			
			if((i>=0 && i<=9) || i==13 )
				jqGridColModel.setHidden(true);
			else if(i==10 || i==11 || i==14 || i==20 || i==23)
				jqGridColModel.setWidth(100);
			else if(i>=15 && i<=19  || i==22 ) {
				jqGridColModel.setWidth(55);
				jqGridColModel.setAlign("center");
			}
			else if(i==row.length-1)
				jqGridColModel.setHidden(true);
			else
				jqGridColModel.setWidth(50);
			/*if(i==1 || i==26)
				jqGridColModel.setWidth(100);				
			else if(i==3 || i==4 || i==20 || i==24)
			{
				 jqGridColModel.setWidth(120);
				 jqGridColModel.setAlign("center");
			}
			else if(i==9 || i==25)
				jqGridColModel.setWidth(300);
			else if(i==10 || i==11)
			{
				jqGridColModel.setWidth(80);
			}
			else if(i==12 || i==22 || i==23)
				jqGridColModel.setWidth(200);
			else if(i>16 && i<20)
			{
				if(i==17)
					jqGridColModel.setWidth(500);
				else 
					jqGridColModel.setWidth(350);
			}				
			else if(i==21 || (i>26 && i<29) || i==30)
			{
				if(i==21)
					jqGridColModel.setWidth(500);
				else
					jqGridColModel.setWidth(350);
			}			
			*/
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}

    
/*	public static JSONObject fillPCSGrid(List<String[]> dataArrayList,List<String[]> dataArrayList2,HttpServletRequest request)
	{
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		int totalRecords=0;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		CommonMessage.debugMsg(" totalRecords " + totalRecords);
		
		tableDataObject.put("page", page); //current page
		tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		//if( page == 1)
		tableDataObject.put("records", totalRecords); //total records
		
		JSONArray rowArr = new JSONArray(); 
       
      
		int rowId = 0;
        for( String [] row : dataArrayList)
		{
        	if( rowId >= 1 )
        	{	
	    	    JSONObject rowObj =new JSONObject();
	    	    	
	    	    rowObj.put("id",rowId -1 +1);
	            
	            JSONArray cell=new JSONArray();
	            
	            for( int i = 0 ;i < row.length ; i++)
	            {	 
	            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", ""):" ") ); 
	            }	
	            rowObj.put("cell",cell);
	            
	            rowArr.put(rowObj);
        	}
        	rowId++;
       }
      

        tableDataObject.put("rows", rowArr);
        
        return tableDataObject;

	}

*/
	private void saveIntRejectionEntry(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
	{
		CommonMessage.debugMsg("Pcl MAIN");	
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
		String rejGrid = request.getParameter("rejGrid");
		CommonMessage.debugMsg(rejGrid);
		if( httpSession != null && user != null)
    	{    		
    		IntRejEntryBean intRejEntryBean = (IntRejEntryBean)httpSession.getAttribute("InternalRejectionServletIntRejEntryBean");
    		
    		CommonMessage.debugMsg("intRejEntryBean.getFormActionMode()"+ intRejEntryBean.getFormActionMode());
    		String saveFrom = request.getParameter("saveFrom");
    	
    			CommonMessage.debugMsg("saveFrom : "+saveFrom);
			if (intRejEntryBean  == null )	
				intRejEntryBean  = new IntRejEntryBean();
			
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
			if(UIUtils.isValidKeyId(saveFrom))
				intRejEntryBean.setSaveFrom(saveFrom);
			else
				intRejEntryBean.setSaveFrom("");
			if ("Update".equals(request.getParameter("saveMode")))
				intRejEntryBean.setFormMode("Update");
			else 
				intRejEntryBean.setFormMode("Save");
			
			CommonMessage.debugMsg("formBean Mode"+intRejEntryBean.getFormMode());
			
			try{
				

				if (UIUtils.isValidKeyId(intRejEntryBean.getFormActionMode()) && ! intRejEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				QtmTlIntrejectionmst existQtmTlIntrejectionmst = (QtmTlIntrejectionmst)httpSession.getAttribute("QtmTlIntrejectionmst");
								
	    		QtmTlIntrejectionmst newQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
	    		QtmTlIntrejectiondtl newQtmTlIntrejectiondtl = new QtmTlIntrejectiondtl();
	    		
	    		QtmTlIntrejectionmst oldQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
	    		
				if ("Update".equals(request.getParameter("saveMode")))
					intRejEntryBean.setFormMode("Update");
				else 
					intRejEntryBean.setFormMode("Save");
				
				
				newQtmTlIntrejectionmst =(QtmTlIntrejectionmst)UIUtils.setBeanProperties((Object)newQtmTlIntrejectionmst,request);
	    		
	    		CommonMessage.debugMsg("Second");
	    		newQtmTlIntrejectiondtl =(QtmTlIntrejectiondtl)UIUtils.setBeanProperties((Object)newQtmTlIntrejectiondtl,request);
	    		
	    		newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
	    		newQtmTlIntrejectionmst.setQirmEntryby(user.getUsrm_ccno());
	    		
	    		newQtmTlIntrejectiondtl.setQirdCreatedby(user.getUsrm_ccno());
	    		String masterId = request.getParameter("hdnQirmKeyidEntry");
	    		newQtmTlIntrejectionmst.setQirmKeyid(masterId);
	    		intRejEntryBean.setReportType(request.getParameter("reportType"));
	    		intRejEntryBean.setQihbKeyid(request.getParameter("qihbKeyid"));
	    		if(UIUtils.isValidKeyId(rejGrid))
				{
					JSONArray jsonArray = JSONArray.fromString(rejGrid);	
					QtmTlIntrejectiondtl qtmTlIntrejectiondtl = new QtmTlIntrejectiondtl();
					List<QtmTlIntrejectiondtl> intRej = (List<QtmTlIntrejectiondtl>) UIUtils.convertJSONArrToList(qtmTlIntrejectiondtl, jsonArray);
					if( qtmTlIntrejectiondtl != null)
						newQtmTlIntrejectiondtl.setQtmTlIntrejectiondtl(intRej);
				}
	    		
				if( intRejEntryBean.getFormMode().equals("Save"))
				{
		    		newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
		    		
		    		newQtmTlIntrejectiondtl = intRejEntryService.create(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl,intRejEntryBean);					
				}	
				else{
					newQtmTlIntrejectiondtl = intRejEntryService.create(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl,intRejEntryBean);
					//existQtmTlIntrejectionmst = pcsEntryService.update(newQtmTlIntrejectionmst, oldQtmTlIntrejectionmst, intRejEntryBean);
				}
				
				String keyId =newQtmTlIntrejectiondtl.getQirdMasterid();
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
				httpSession.setAttribute("RejectionDetailMstId", existQtmTlIntrejectionmst);
				String formBeanIdentifier = "IntRejEntryBean"+intRejEntryBean.getFormMode();
				CommonMessage.debugMsg(" formBeanIdentifier " +  formBeanIdentifier);		
				httpSession.setAttribute(formBeanIdentifier,intRejEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",intRejEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("prlmKeyid",keyId);
				persistentData.put("QirmKeyid",keyId);
				
				if(UIUtils.isValidKeyId(request.getParameter("filterProcessId")))
					persistentData.put("processId",request.getParameter("filterProcessId"));
				if(UIUtils.isValidKeyId(request.getParameter("filterPhenomenaId")))
					persistentData.put("phenomenaId",request.getParameter("filterPhenomenaId"));
		

				//persistentData.put("fromBean", formTypeIdentifier);
				JSONObject forwardData = new JSONObject();
				JSONObject returnData = new JSONObject();
				
				if(UIUtils.isValidKeyId(request.getParameter("filterProcessId")))
					forwardData.put("processId",request.getParameter("filterProcessId"));
				if(UIUtils.isValidKeyId(request.getParameter("filterPhenomenaId")))
					forwardData.put("phenomenaId",request.getParameter("filterPhenomenaId"));				
				
				
				if(UIUtils.isValidKeyId(intRejEntryBean.getSaveFrom()))
				{
					if(intRejEntryBean.getSaveFrom().equals("yy"))
					{
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdKeyid()))
							forwardData.put("cmbwwmsRefdocno",newQtmTlIntrejectiondtl.getQirdKeyid());
					
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdPhenomenaid()))
							forwardData.put("cmbwwmsPhenomenaid",newQtmTlIntrejectiondtl.getQirdPhenomenaid());
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdCauseid()))
							forwardData.put("cmbwwmsCauseid",newQtmTlIntrejectiondtl.getQirdCauseid());
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQird4mtype()))
							forwardData.put("cmbwwmsFormtype",newQtmTlIntrejectiondtl.getQird4mtype());
						CommonMessage.debugMsg("Machine Id : " +newQtmTlIntrejectiondtl.getQirdTempfield5());
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdTempfield5()))
							forwardData.put("cmbwwmsMachineid",newQtmTlIntrejectiondtl.getQirdTempfield5());
						
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdWwmasterid()))
							forwardData.put("txtwwmsKeyid",newQtmTlIntrejectiondtl.getQirdWwmasterid());
						}
						
						forwardData.put("txtformType","IMT");
						returnData.put("openForm","YY");
					}
				JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");
				successData.put("formClear",false);
				successData.put("mode",intRejEntryBean.getFormMode() );
				successData.put("keyId", keyId); 				
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);	
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				
				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());	
			}
			catch(ValidationExceptions e)
			{
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreation");
				errMessage.put("fromMode",intRejEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "intRejEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				e.printStackTrace();
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}
		
		
	}
    
	private void saveIntRejEntry(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
	{
		CommonMessage.debugMsg("Pcl MAIN");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		IntRejEntryBean intRejEntryBean = (IntRejEntryBean)httpSession.getAttribute("InternalRejectionServletIntRejEntryBean");
    		
    		intRejEntryBean.setFormActionMode("create");
    		CommonMessage.debugMsg("intRejEntryBean.getFormActionMode()"+ intRejEntryBean.getFormActionMode());
    		String saveFrom = request.getParameter("saveFrom");
    	
    			CommonMessage.debugMsg("saveFrom : "+saveFrom);
			if (intRejEntryBean  == null )	
				intRejEntryBean  = new IntRejEntryBean();
			
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
			if(UIUtils.isValidKeyId(saveFrom))
				intRejEntryBean.setSaveFrom(saveFrom);
			/*else
				intRejEntryBean.setSaveFrom("");
			CommonMessage.debugMsg("kkkkk");
			if (request.getParameter("saveMode").equals("Update"))
				
				intRejEntryBean.setFormMode("Update");
			*/
			else 
				CommonMessage.debugMsg("kkkk22222222222222k");
				intRejEntryBean.setFormMode("Save");
			
			CommonMessage.debugMsg("formBean Mode"+intRejEntryBean.getFormMode());
			
			try{
				CommonMessage.debugMsg("k");
				if (UIUtils.isValidKeyId(intRejEntryBean.getFormActionMode()) && ! intRejEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				QtmTlIntrejectionmst existQtmTlIntrejectionmst = (QtmTlIntrejectionmst)httpSession.getAttribute("QtmTlIntrejectionmst");
								
	    		QtmTlIntrejectionmst newQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
	    		QtmTlIntrejectiondtl newQtmTlIntrejectiondtl = new QtmTlIntrejectiondtl();
	    		
	    		QtmTlIntrejectionmst oldQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
	    		CommonMessage.debugMsg("kkkk");
			/*	if (request.getParameter("saveMode").equals("Update"))
					intRejEntryBean.setFormMode("Update");
				else 
					intRejEntryBean.setFormMode("Save");
				*/
	    		intRejEntryBean.setQirmKeyid(request.getParameter("QirmKeyid"));
	    		intRejEntryBean.setQirdKeyid(request.getParameter("QirdKeyid"));
	    		intRejEntryBean.setPlrkKeyid(request.getParameter("PlrkKeyid"));
	    		
	    		intRejEntryBean.setQird4mType(request.getParameter("Qird4mtype"));
	    		intRejEntryBean.setQirdType(request.getParameter("QirdType"));
	    		intRejEntryBean.setQirdRemarks(request.getParameter("QirdRemarks"));
	    		
	    		intRejEntryBean.setProcessId(request.getParameter("processId"));
	    		intRejEntryBean.setPhenId(request.getParameter("phenId"));
	    		intRejEntryBean.setCauseId(request.getParameter("causeId"));
	    		intRejEntryBean.setInspectedQty(request.getParameter("inspectedQty"));
	    		
	    		intRejEntryBean.setReportType(request.getParameter("reportType"));
	    		intRejEntryBean.setQihbKeyid(request.getParameter("qihbKeyid"));
	    		
	    		String yy = request.getParameter("QirdWwmasterid");
	    		
	    		if(UIUtils.isValidKeyId(yy))
	    			newQtmTlIntrejectiondtl.setQirdWwmasterid(yy);
	    		
	    		CommonMessage.debugMsg("Plmasterid=="+request.getParameter("Plmasterid"));	    		 
	    		CommonMessage.debugMsg("QirdKeyid==="+request.getParameter("QirdKeyid"));
	    		
	    		newQtmTlIntrejectionmst =(QtmTlIntrejectionmst)UIUtils.setBeanProperties((Object)newQtmTlIntrejectionmst,request);
	    		
	    		CommonMessage.debugMsg("Second");
	    		newQtmTlIntrejectiondtl =(QtmTlIntrejectiondtl)UIUtils.setBeanProperties((Object)newQtmTlIntrejectiondtl,request);
	    		
	    		newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
	    		newQtmTlIntrejectionmst.setQirmEntryby(user.getUsrm_ccno());
	    		
	    		newQtmTlIntrejectiondtl.setQirdCreatedby(user.getUsrm_ccno());
	    		
	    	/*	if( newQtmTlIntrejectiondtl != null)
	    			newQtmTlIntrejectionmst.getPcsDetail().add(newQtmTlIntrejectiondtl);	    		
	    		if( newPcsTlLossreasonlink != null)
	    			newQtmTlIntrejectionmst.getPcslosslink().add(newPcsTlLossreasonlink);
	    	*/
	    		
				if( intRejEntryBean.getFormMode().equals("Save"))
				{
		    		newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
		    		newQtmTlIntrejectiondtl = intRejEntryService.createYY(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl,intRejEntryBean);					
				}	
				else{
					newQtmTlIntrejectiondtl = intRejEntryService.createYY(newQtmTlIntrejectionmst, newQtmTlIntrejectiondtl,intRejEntryBean);
					//existQtmTlIntrejectionmst = pcsEntryService.update(newQtmTlIntrejectionmst, oldQtmTlIntrejectionmst, intRejEntryBean);
				}
				
				String keyId =newQtmTlIntrejectiondtl.getQirdMasterid();
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
				httpSession.setAttribute("RejectionDetailMstId", existQtmTlIntrejectionmst);
				String formBeanIdentifier = "IntRejEntryBean"+intRejEntryBean.getFormMode();
				CommonMessage.debugMsg(" formBeanIdentifier " +  formBeanIdentifier);		
				httpSession.setAttribute(formBeanIdentifier,intRejEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",intRejEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("prlmKeyid",keyId);
				persistentData.put("QirmKeyid",keyId);
				
				if(UIUtils.isValidKeyId(request.getParameter("filterProcessId")))
					persistentData.put("processId",request.getParameter("filterProcessId"));
				if(UIUtils.isValidKeyId(request.getParameter("filterPhenomenaId")))
					persistentData.put("phenomenaId",request.getParameter("filterPhenomenaId"));
		

				//persistentData.put("fromBean", formTypeIdentifier);
				JSONObject forwardData = new JSONObject();
				JSONObject returnData = new JSONObject();
				
				if(UIUtils.isValidKeyId(request.getParameter("filterProcessId")))
					forwardData.put("processId",request.getParameter("filterProcessId"));
				if(UIUtils.isValidKeyId(request.getParameter("filterPhenomenaId")))
					forwardData.put("phenomenaId",request.getParameter("filterPhenomenaId"));				
				
				
				if(UIUtils.isValidKeyId(intRejEntryBean.getSaveFrom()))
				{
					if(intRejEntryBean.getSaveFrom().equals("yy"))
					{
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdKeyid()))
							forwardData.put("cmbwwmsRefdocno",newQtmTlIntrejectiondtl.getQirdKeyid());
					
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdPhenomenaid()))
							forwardData.put("cmbwwmsPhenomenaid",newQtmTlIntrejectiondtl.getQirdPhenomenaid());
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdCauseid()))
							forwardData.put("cmbwwmsCauseid",newQtmTlIntrejectiondtl.getQirdCauseid());
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQird4mtype()))
							forwardData.put("cmbwwmsFormtype",newQtmTlIntrejectiondtl.getQird4mtype());
						CommonMessage.debugMsg("Machine Id : " +newQtmTlIntrejectiondtl.getQirdTempfield5());
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdTempfield5()))
							forwardData.put("cmbwwmsMachineid",newQtmTlIntrejectiondtl.getQirdTempfield5());
						
						
						if(UIUtils.isValidKeyId(newQtmTlIntrejectiondtl.getQirdWwmasterid()))
							forwardData.put("txtwwmsKeyid",newQtmTlIntrejectiondtl.getQirdWwmasterid());
						}
						
						forwardData.put("txtformType","IMT");
						returnData.put("openForm","YY");
					}
				JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");
				successData.put("formClear",false);
				successData.put("mode",intRejEntryBean.getFormMode() );
				successData.put("keyId", keyId); 				
				returnData.put("forwardData",forwardData);
				returnData.put("persistentData", persistentData);	
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				returnData.put("openForm","DIR");
				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreation");
				errMessage.put("fromMode",intRejEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "intRejEntryCreation");
				out.print(errMessage.toString());

			}catch(Exception e)
			{
				e.printStackTrace();
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}
	}
	    
	    
	    private void saveIntHourlyBreak(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
		{
			CommonMessage.debugMsg("save int hourly break");		
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	UIUtils.displayRequestParamsValue(request);
	    	if( httpSession != null && user != null)
	    	{    		
	    		IntRejEntryBean intRejEntryBean = (IntRejEntryBean)httpSession.getAttribute("InternalRejectionServletIntRejEntryBean");
	    		
	    		CommonMessage.debugMsg("intRejEntryBean.getFormActionMode()"+ intRejEntryBean.getFormActionMode());
	    		String saveFrom = request.getParameter("saveFrom");
	    	
	    			CommonMessage.debugMsg("saveFrom : "+saveFrom);
				if (intRejEntryBean  == null )	
					intRejEntryBean  = new IntRejEntryBean();
				
				CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
				if(UIUtils.isValidKeyId(saveFrom))
					intRejEntryBean.setSaveFrom(saveFrom);
				else
					intRejEntryBean.setSaveFrom("");
				if (request.getParameter("saveMode").equals("Update"))
					intRejEntryBean.setFormMode("Update");
				else 
					intRejEntryBean.setFormMode("Save");
				
				CommonMessage.debugMsg("formBean Mode"+intRejEntryBean.getFormMode());
				
				try{

					if (UIUtils.isValidKeyId(intRejEntryBean.getFormActionMode()) && ! intRejEntryBean.getFormActionMode().equals("create"))
						throw new BusinessApplicationExceptions("view_Mode"+",");
					
					QtmTlInternalrejectionhourly existQtmTlInternalrejectionhourly = (QtmTlInternalrejectionhourly)httpSession.getAttribute("QtmTlInternalrejectionhourly");									
					QtmTlInternalrejectionhourly newQtmTlInternalrejectionhourly = new QtmTlInternalrejectionhourly();
					QtmTlInternalrejectionhourly oldQtmTlInternalrejectionhourly = new QtmTlInternalrejectionhourly();
		    		
					QtmTlIntrejectionmst newQtmTlIntrejectionmst = new QtmTlIntrejectionmst();
		    		newQtmTlIntrejectionmst =(QtmTlIntrejectionmst)UIUtils.setBeanProperties((Object)newQtmTlIntrejectionmst,request);
		    		
		    		//newQtmTlIntrejectionmst =(QtmTlIntrejectionmst)UIUtils.setBeanProperties((Object)newQtmTlIntrejectionmst,request);
		    		CommonMessage.debugMsg("Second");
		    		newQtmTlIntrejectionmst.setQirmCreatedby(user.getUsrm_ccno());
		    		newQtmTlIntrejectionmst.setQirmEntryby(user.getUsrm_ccno());
		    		
					if (request.getParameter("saveMode").equals("Update"))
						intRejEntryBean.setFormMode("Update");
					else 
						intRejEntryBean.setFormMode("Save");
					
					//newQtmTlIntrejectionmst.setQirmPlmasterid(request.getParameter("Plmasterid"));
					//newQtmTlIntrejectionmst.setQirmPlmasterid(request.getParameter("phenId"));
		    		intRejEntryBean.setCauseId(request.getParameter("causeId"));
		    		intRejEntryBean.setInspectedQty(request.getParameter("inspectedQty"));		    		
		    		intRejEntryBean.setReportType(request.getParameter("reportType"));
		    		intRejEntryBean.setQihbKeyid(request.getParameter("QihbKeyid"));		    		
		    		CommonMessage.debugMsg("Plmasterid=="+request.getParameter("Plmasterid"));		    		
		    		CommonMessage.debugMsg("QirdKeyid==="+request.getParameter("QirdKeyid"));
		    		
		    		intRejEntryBean.setQirmKeyid(request.getParameter("QirmKeyid"));
		    		
					intRejEntryBean.setQihbKeyid(request.getParameter("QihbKeyid"));
		    		intRejEntryBean.setQihbQtmKeyid(request.getParameter("QihbQtmKeyid"));
		    		intRejEntryBean.setQihbShifthour(request.getParameter("QihbShifthour"));
		    		//intRejEntryBean.setPlrkKeyid(request.getParameter("QihbShifttiming"));		    		
		    		intRejEntryBean.setQihbInpsectedqty(request.getParameter("QihbInpsectedqty"));
		    		intRejEntryBean.setQihbAcceptedqty(request.getParameter("QihbAcceptedqty"));
		    		intRejEntryBean.setQihbBalanceqty(request.getParameter("QihbBalanceqty"));
		    		intRejEntryBean.setQihbRejectedqty(request.getParameter("QihbRejectedqty"));
		    		intRejEntryBean.setQihbTestingqty(request.getParameter("QihbTestingqty"));
		    		intRejEntryBean.setQihbMrbqty(request.getParameter("QihbMrbqty"));
		    		intRejEntryBean.setQihbQahold(request.getParameter("QihbQahold"));
		    		
	    		
		    		newQtmTlInternalrejectionhourly =(QtmTlInternalrejectionhourly)UIUtils.setBeanProperties((Object)newQtmTlInternalrejectionhourly,request);
		    		
		    		CommonMessage.debugMsg("getQihbAcceptedqty " + intRejEntryBean.getQihbAcceptedqty());
		    		CommonMessage.debugMsg("getQihbAcceptedqty " + intRejEntryBean.getQihbRejectedqty());
		    		CommonMessage.debugMsg(" Parent Id" +  newQtmTlIntrejectionmst.getQirmParentmasterid());	
		    		CommonMessage.debugMsg(" Link Id" +  newQtmTlIntrejectionmst.getQirmLinkmasterid());	
		    		newQtmTlInternalrejectionhourly.setQihbUserindex(user.getUsrm_ccno());
		    		
		    		if( intRejEntryBean.getFormMode().equals("Save"))
					{	
		    			newQtmTlInternalrejectionhourly = intRejEntryService.createHourly(newQtmTlInternalrejectionhourly,newQtmTlIntrejectionmst, intRejEntryBean);					
					}	
					else{
						newQtmTlInternalrejectionhourly = intRejEntryService.createHourly(newQtmTlInternalrejectionhourly,newQtmTlIntrejectionmst, intRejEntryBean);
						//existQtmTlInternalrejectionhourly = pcsEntryService.update(newQtmTlInternalrejectionhourly, oldQtmTlInternalrejectionhourly, intRejEntryBean);
					}
					
					String qihbId =newQtmTlInternalrejectionhourly.getQihbKeyid();
					String qirmId =newQtmTlInternalrejectionhourly.getQihbQtmKeyid();
					String inspectedQty =newQtmTlInternalrejectionhourly.getQihbInspectedqty();
					String QAHoldQty =newQtmTlInternalrejectionhourly.getQihbQahold();
					CommonMessage.debugMsg(" qirmId KEYID() " +  qirmId);					
					
					httpSession.setAttribute(newQtmTlInternalrejectionhourly.getQihbKeyid(), existQtmTlInternalrejectionhourly);
					String formBeanIdentifier = "IntRejEntryBean"+intRejEntryBean.getFormMode();
					httpSession.setAttribute(formBeanIdentifier,intRejEntryBean);
					
					JSONObject mode = new JSONObject();
					mode.put("formMode",intRejEntryBean.getFormMode());
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("prlmKeyid",qihbId);
					persistentData.put("QihbKeyid",qihbId);
					//persistentData.put("fromBean", formTypeIdentifier);
					JSONObject forwardData = new JSONObject();
					forwardData.put("QihbKeyid",qihbId);
					
					CommonMessage.debugMsg("qihbKeyid"+qihbId);
					
					JSONObject returnData = new JSONObject();	

					JSONObject successData = new JSONObject();
					successData.put("msg","Data Saved Successfully");
					successData.put("formClear",false);
					successData.put("mode",intRejEntryBean.getFormMode() );
					//successData.put("qihbId", qihbId); 				
					//successData.put("qirmId", qirmId);
					returnData.put("forwardData",forwardData);
					returnData.put("persistentData", persistentData);	
					returnData.put("successData", successData);
					returnData.put("formClear", false);
					returnData.put("qihbId", qihbId);
					returnData.put("qirmId", qirmId);
					returnData.put("inspectedQty", inspectedQty);
					returnData.put("QAHoldQty", QAHoldQty);
					
					
					CommonMessage.debugMsg(returnData.toString());
					out.print(returnData.toString());				
				}
		    	catch(ValidationExceptions e)
				{
		    		CommonMessage.debugMsg("validations exception");
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreation");
					errMessage.put("fromMode",intRejEntryBean.getFormMode());
					CommonMessage.debugMsg(errMessage.toString());
					out.print(errMessage.toString());
					
				}catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "intRejEntryCreation");
					out.print(errMessage.toString());

				}catch(Exception e)
				{
					e.printStackTrace();
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
	    	}
		}	    

	    private void saveBreakup(HttpServletRequest request, HttpServletResponse response ) throws IOException{
			
	    	
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);	    
	    	if( httpSession != null && user != null)
	    	{	
	    		QtmTlTestscrapbrkup newQtmTlTestscrapbrkup =new QtmTlTestscrapbrkup();	    	
				newQtmTlTestscrapbrkup =(QtmTlTestscrapbrkup)UIUtils.setBeanProperties((Object)newQtmTlTestscrapbrkup,request);
				newQtmTlTestscrapbrkup.setQsbrCreatedby(user.getUsrm_ccno());
				QtmTlTestscrapbrkup existQtmTlTestscrapbrkup = (QtmTlTestscrapbrkup)httpSession.getAttribute("QtmTlTestscrapbrkupHourly");
				String scrapBreakup = request.getParameter("scrapBreakup");		
				String insQty = request.getParameter("hdnBreakupInsQty");		
				String qaHold = request.getParameter("hdnBreakupQAHold");		
				CommonMessage.debugMsg("BraekUp : "+scrapBreakup);
				if(UIUtils.isValidKeyId(scrapBreakup))
				{
					JSONArray jsonArray = JSONArray.fromString(scrapBreakup);		    	
					QtmTlTestscrapbrkup scrapBreakUp = new QtmTlTestscrapbrkup();
			    	List<QtmTlTestscrapbrkup> scrapBreakUpList = (List<QtmTlTestscrapbrkup>) UIUtils.convertJSONArrToList(scrapBreakUp, jsonArray);
			    	if( scrapBreakUpList != null)
			    		newQtmTlTestscrapbrkup.setQtmTlTestscrapbrkup(scrapBreakUpList);
				}
				
				try{
						
						existQtmTlTestscrapbrkup = intRejEntryService.createBreakup(newQtmTlTestscrapbrkup,existQtmTlTestscrapbrkup,insQty,qaHold);
					
						String msgPropertyIdnt = "success-save";				
					    JSONObject successData = new JSONObject(); 
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						
						successData.put("keyId", existQtmTlTestscrapbrkup.getQsbrRejectionid());
						JSONObject returnData = new JSONObject();							
						returnData.put("formClear",false);
						returnData.put("successData", successData);
							
						httpSession.removeAttribute("QtmTlTestscrapbrkupHourly");
						httpSession.setAttribute( "QtmTlTestscrapbrkupHourly", existQtmTlTestscrapbrkup);						
						out.print(returnData.toString());
						out.close();					
				}catch(ValidationExceptions e)
				{
					CommonMessage.debugMsg("validations exception");
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"intRejEntryCreation");					
					CommonMessage.debugMsg(errMessage.toString());
					out.print(errMessage.toString());
						
				}catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "intRejEntryCreation");
					out.print(errMessage.toString());

				}			
				catch(Exception e)
				{
					//CommonMessage.debugMsg("gete. " + e.getMessage());
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
				commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
				commonFilter.setViewClick('Y');
				httpSession.removeAttribute(beanIdentifier);
				httpSession.setAttribute(beanIdentifier, commonFilter);
			}
			
			return commonFilter;
		}
}