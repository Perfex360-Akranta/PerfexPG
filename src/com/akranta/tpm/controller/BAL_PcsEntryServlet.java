package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlHtDetails;
import com.akranta.tpm.model.BAL_PcsTlHtProductionHistory;
import com.akranta.tpm.model.BAL_PcsTlJhlossDetail;
import com.akranta.tpm.model.BAL_PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlLossreasonlink;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.model.BAL_PcsTlProductiondata;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.service.PcsComplianceRptService;
import com.akranta.tpm.service.BAL_PcsEntryService;
import com.akranta.tpm.service.impl.BAL_PcsEntryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.PCSConstants;
import com.akranta.tpm.utils.WOConstants;
//import com.sun.org.apache.xpath.internal.operations.NotEquals;

public class BAL_PcsEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static int count;
	private BAL_PcsEntryService pcsEntryService;   
	private PcsComplianceRptService pcsComplianceRptService;
	private static final String AdmUploadExcelServlet_filename = "PcsEntryServletfilename";
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static String realPath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
	        boolean s = new File(realPath).mkdirs();
	       
	    }
    
	public BAL_PcsEntryServlet() {
        super();
    	/*try {
    		pcsEntryService = new PcsEntryServiceImpl();
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
		ComboFilter comboFilter = new ComboFilter();
		comboFilter=UIUtils.fillComboFilter(request);
		
		String action = UIUtils.getActionPart(request);
		CommonFunctions.debugMsg("Action :"+action);
		try {
			pcsEntryService = (BAL_PcsEntryService)UIUtils.getServiceObject(request,"BAL_PcsEntryServiceImpl");
			pcsComplianceRptService=(PcsComplianceRptService)UIUtils.getServiceObject(request,"PcsComplianceRptServiceImpl");
			pcsEntryService.BAL_PcsEntryServiceImplJwt( 
					(String) (httpSession.getAttribute("tpmjwttoken") == null 
					? "" : httpSession.getAttribute("tpmjwttoken")) 
					);
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		if (action.equals("pcsEntry_input.balpcs") && user != null) 
		{
			CommonFunctions.debugMsg("input the jsp");
			// there is nothing to be done
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			request.setAttribute("pcsEnableDisableFormBean", pcsEnableDisableFormBean);
		}
		String dispatchUrl = null; 
		
		if (action.equals("pcsCalendar_input.balpcs") || action.equals("pcsCalendarTest_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the jsp");
			CommonFunctions.debugMsg("cell id "+ request.getParameter("cellId"));
			CommonFunctions.debugMsg("date "+ request.getParameter("date"));
			CommonFunctions.debugMsg("shift "+ request.getParameter("shift"));
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String mchId = request.getParameter("mchId");
			String flid = request.getParameter("flid");			
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) request.setAttribute("shift", shift);
			if (UIUtils.isValidKeyId(cellId))request.setAttribute("cellId", cellId);
			if (UIUtils.isValidKeyId(factId))request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(mchId))request.setAttribute("mchId", mchId);
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			request.setAttribute("mode", "create");
			if(action.equals("pcsCalendar_input.balpcs"))
				dispatchUrl = "/pages/pcs/pcsCalendar.jsp";
			else { 
				
				dispatchUrl = "/pages/pcs/pcsViewNew.jsp";
			}
		}
		
		if( action.equals("setFormActionMode.balpcs")) {

			CommonFunctions.debugMsg("shift"+ request.getParameter("mode"));
			String mode = request.getParameter("mode");

    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");        	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			pcsEntryBean.setFormActionMode(mode);
			httpSession.removeAttribute("PcsEntryServletPcsEntryBean");
			httpSession.setAttribute("PcsEntryServletPcsEntryBean",pcsEntryBean);
			CommonFunctions.debugMsg("pcsEntryBean.setFormActionMode()"+ pcsEntryBean.getFormActionMode());

		}
		
		if (action.equals("pcsView_input.balpcs") || action.equals("pcsViewTest_input.balpcs")) 
		{
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String mchId = request.getParameter("mchId");
			String flid = request.getParameter("flid");
			String cellId = request.getParameter("cellId");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String mode = request.getParameter("mode");
			String formLock = request.getParameter("formLock");
			String baseDataStr = request.getParameter("baseDataStr");
			String formAuto = request.getParameter("formAuto");			
            CommonFunctions.debugMsg("formAuto    :"+formAuto);
            CommonFunctions.debugMsg("shift==    :"+shift);
            CommonFunctions.debugMsg("entryDate    :"+entryDate);
            CommonFunctions.debugMsg("cellId    :"+cellId);            
			request.setAttribute("baseDataStr", baseDataStr);
			if(action.equals("pcsView_input.balpcs"))
				dispatchUrl = "/pages/pcs/pcsView.jsp";	
			else
				dispatchUrl = "/pages/pcs/pcsViewNew.jsp";	
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) request.setAttribute("shift", shift);			
			if (UIUtils.isValidKeyId(factId))request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(cellId))request.setAttribute("cellId", cellId);
			if (UIUtils.isValidKeyId(mchId))request.setAttribute("mchId", mchId);
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			if (UIUtils.isValidKeyId(mode))	request.setAttribute("mode", mode);
			if (UIUtils.isValidKeyId(formLock))	request.setAttribute("formLock", formLock);
			if (UIUtils.isValidKeyId(formAuto))	request.setAttribute("formAuto", formAuto);
			
		}
		else if (action.equals("pcsEntryView_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the VIEW jsp");		
			String cellId = request.getParameter("cellId");			
			dispatchUrl = "/pages/pcs/pcsEntryView.jsp";	
			request.setAttribute("cellId", cellId);
		}
		else if (action.equals("OtherLossEntry_input.balpcs")) 
		{
			String flid=request.getParameter("flid");
			String date=request.getParameter("date");
			request.setAttribute("flid", flid);
			request.setAttribute("date", date);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/pcs/OtherLossEntry.jsp");					
		//	RequestDispatcher rd = request.getRequestDispatcher("/pages/pcs/pcsLossCaptureNew.jsp");
			rd.forward(request, response);
			
		}
		else if (action.equals("file_clear.balpcs")) {
			httpSession.removeAttribute(AdmUploadExcelServlet_filename);
		}else if (action.equals("file_upload.balpcs")) 
		{
			PrintWriter out = response.getWriter();
			InputStream is = null;
	        FileOutputStream fos = null;	
	        String filename = request.getHeader("X-File-Name");
	        if(filename !=null){	        	
	        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
	        	CommonFunctions.debugMsg("fileename: "+filename);
	        }
	        httpSession.removeAttribute(AdmUploadExcelServlet_filename);
			httpSession.setAttribute(AdmUploadExcelServlet_filename,filename);
	        try {
	            is = request.getInputStream();	
	            CommonFunctions.debugMsg(realPath + " : file name :" + filename);
	            fos = new FileOutputStream(new File(realPath + filename));
	            IOUtils.copy(is, fos);   
	        } catch (FileNotFoundException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            out.print("{success: false}");
	            CommonFunctions.debugMsg(" : file exception :");
	        } catch (IOException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            CommonFunctions.debugMsg(" : io exception :");
	            out.print("{success: false}");
	        }  finally {
	            try {
	                fos.close();
	                is.close();
	                fos =null;
	                is =null;
	            } catch (IOException ignored) {
	            }
	        }
		}
		else if(action.equals("file_save.balpcs")){
			 saveOtherLossFile(request,response);
		 }
		else if (action.equals("OtherLossEntry_getCol.balpcs")) 
		{
			try{
				CommonFilter commonFilter = populateCommonFilter(request,"OtherLossEntry",true);
				
				PrintWriter out = response.getWriter();
				httpSession.removeAttribute("OtherLossEntry");
				List<String []> getOtherList  = pcsEntryService.getAllfillgriddata(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();
				
				jqGridTableModel.setRowNumbers(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setGridEdit(true);
				jqGridTableModel.setGroupBy(true);
				jqGridTableModel.setGroupByField("LOSSNAME");
				
				String [] colHeader = getOtherList.get(2);			
				String [] colHeaderCond = getOtherList.get(1);
				
				gridColModel.setHeaderNum(1);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "80%%");
				jsonObject.set("tableHeight", "70%%");
				httpSession.removeAttribute("OtherLossEntryobj");
				httpSession.setAttribute("OtherLossEntryobj", jsonObject);
				out.println(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (action.equals("OtherLossEntry_getData.balpcs")) 
		{
			try
			{
				PrintWriter out = response.getWriter();
				String flid=request.getParameter("flid");
				String date=request.getParameter("date");
				String lossId=request.getParameter("lossId");
				String month=request.getParameter("month");
				CommonFilter commonFilter = populateCommonFilter(request,"OtherLossEntry",false);
				if(UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if(UIUtils.isValidKeyId(date))
					commonFilter.setDtestart(date);
				if(UIUtils.isValidKeyId(lossId))
					commonFilter.setLossId(lossId);
				if(UIUtils.isValidKeyId(month))
					commonFilter.setDteEnd(month);
				
				List<String []> getOtherList  = pcsEntryService.getAllfillgriddata(commonFilter);
  			 	JSONObject OtherLossJson = UIUtils.convertToJqGridTableObject(getOtherList,request,3,0); 
  			 	out.println(OtherLossJson);
  			 	httpSession.removeAttribute("OtherLossEntry");
		    }catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}else if(action.equals("OtherLossEntry_save.balpcs")){
			updateOtherLoss(request,response);
		}
		if( action.equals("saveJhLossDetails.balpcs")) {
			saveJhDetailInfo(request,response);
			System.out.println("in side saveJhLossDetails.balpcs   "+ action);
		}
		else if(action.equals("functionalLoc.balpcs"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFunctionalLocId("cmbOlseFlid");
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachDisable(true);
			/*functLocFieldNameBean.setSbuDisable(true);
			functLocFieldNameBean.setPbuDisable(true);
			functLocFieldNameBean.setCompDisable(true);
			functLocFieldNameBean.setLconDisable(true);
			functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);*/
			FormModes formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		
		else if (action.equals("pcsEntry_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the jsp");	
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			String prdId = request.getParameter("prdId");
						
			dispatchUrl = "/pages/pcs/pcsEntry.jsp";	
			
			CommonFunctions.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("MchId", mchId);
			request.setAttribute("PrdId", prdId);
			
		}		
		else if (action.equals("pcsPopEntry_input.balpcs") || action.equals("pcsPopTestEntry_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the jsp");	
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			String prdId = request.getParameter("prdId");
			String sectId = request.getParameter("sectId");
			String factId = request.getParameter("factId");
			
			if(action.indexOf("Test")>=0){
			//	dispatchUrl = "/pages/pcs/pcsEntrynew.jsp";	
				dispatchUrl = "/pages/pcs/pcsEntrynew.jsp";
			}
			else{
				//dispatchUrl = "/pages/pcs/pcsPopup.jsp";	
				dispatchUrl = "/pages/pcs/pcsEntrynew.jsp";
			}
			
			CommonFunctions.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("MchId", mchId);
			request.setAttribute("PrdId", prdId);
			request.setAttribute("SectionId", sectId);
			request.setAttribute("FactoryId", factId);
			
		}
		
		else if (action.equals("pcsLossEntry_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the  pcsLossEntry_input.balpcs");	
			String entryDate = request.getParameter("date");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			String prdId = request.getParameter("prdId");
			String lossParentId = request.getParameter("lossParentId");
			String lossId = request.getParameter("lossId");
			String isQtyLoss = request.getParameter("isQtyLoss");
			
			String Pldetailsid = request.getParameter("Pldetailsid");
			String totalProduced = request.getParameter("totalProduced");
						
			dispatchUrl = "/pages/pcs/pcsLossEntry.jsp";	
			
			CommonFunctions.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("SectId", sectId);
			request.setAttribute("FactId", factId);
			request.setAttribute("MchId", mchId);
			request.setAttribute("PrdId", prdId);
			request.setAttribute("Pldetailsid", Pldetailsid);
			request.setAttribute("lossParentId", lossParentId);
			request.setAttribute("lossId", lossId);
			request.setAttribute("isQtyLoss", isQtyLoss);			
			if (UIUtils.isValidKeyId(totalProduced))	request.setAttribute("totalProduced", totalProduced);
			

		}		
			
		else if (action.equals("pcsResult_input.balpcs")) 
		{
			CommonFunctions.debugMsg("inside the pcsResult_input.balpcs");	
			String dataStr = request.getParameter("dataStr");
						
			dispatchUrl = "/pages/pcs/pcsResult.jsp";	
			
			CommonFunctions.debugMsg("dataStr:"+dataStr);
			request.setAttribute("dataStr", dataStr);
		}	
		else if (action.equals("pcsOperatordtls_input.balpcs")) 
		{
			dispatchUrl = "/pages/pcs/operatorDetails.jsp";	
		}	
		else if (action.equals("pcsOperatordtlsNew_input.balpcs")) 
		{
			dispatchUrl = "/pages/pcs/operatorDetailsNew.jsp";	
		}
		
		else if(action.equals("pcsLoss_input.balpcs")){
		
			BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");   
			String flid=request.getParameter("flid");
			CommonFunctions.debugMsg("flidflidflid  pcsLoss_input.balpcs"+flid);

			request.setAttribute("flid",flid);
			CommonFunctions.debugMsg("flidflidflid"+flid);
			/*String cellId=request.getParameter("cellId");	
			request.setAttribute("cellId",cellId);*/
			String date=request.getParameter("date");	
			request.setAttribute("date",date);
			String shiftId=request.getParameter("shiftId");	
			request.setAttribute("shiftId",shiftId);
			request.setAttribute("shiftwise", "N");
			
			UIUtils.forwardRequest(request, response, "/pages/pcs/BAL_pcsLossCaptureShift.jsp");
		}
		else if(action.equals("pcsLossMachineWise_input.balpcs")){
			
			BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");   
			String flid=request.getParameter("flid");
			CommonFunctions.debugMsg("flidflidflid  pcsLoss_input.balpcs"+flid);

			request.setAttribute("flid",flid);
			CommonFunctions.debugMsg("flidflidflid"+flid);
			/*String cellId=request.getParameter("cellId");	
			request.setAttribute("cellId",cellId);*/
			String date=request.getParameter("date");	
			request.setAttribute("date",date);
			String shiftId=request.getParameter("shiftId");	
			request.setAttribute("shiftId",shiftId);
			request.setAttribute("shiftwise", "N");
			
			UIUtils.forwardRequest(request, response, "/pages/pcs/pcsLossCaptureMachineWise.jsp");
		
		}
	//	-------------------------
		else if(action.equals("pcsLossShiftwise_input.balpcs")){
			
			BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");   
			String flid=request.getParameter("flid");
			CommonFunctions.debugMsg("flidflidflid  pcsLoss_input.balpcs"+flid);

			request.setAttribute("flid",flid);
			CommonFunctions.debugMsg("flidflidflid"+flid);
			/*String cellId=request.getParameter("cellId");	
			request.setAttribute("cellId",cellId);*/
			String date=request.getParameter("date");	
			request.setAttribute("date",date);
			String shiftId=request.getParameter("shiftId");	
			request.setAttribute("shiftId",shiftId);
			request.setAttribute("shiftwise", "Y");
			
			UIUtils.forwardRequest(request, response, "/pages/pcs/BAL_pcsLossCaptureShift.jsp");//pcsLossCaptureShift
		}
		
		else if(action.equals("pcsLossHT_input.balpcs")){
			CommonFunctions.debugMsg("In side pcsLossHT_input.balpcs");
			BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");   
			String flid=request.getParameter("flid");	
			request.setAttribute("flid",flid);
			/*String cellId=request.getParameter("cellId");	
			request.setAttribute("cellId",cellId);*/
			String date=request.getParameter("date");	
			request.setAttribute("date",date);
			String shiftId=request.getParameter("shiftId");	
			request.setAttribute("shiftId",shiftId);
			
			UIUtils.forwardRequest(request, response, "/pages/pcs/pcsLossCaptureHt.jsp");
		}
		else if(action.equals("functionalLoc_Calendar.balpcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}				

		else if(action.equals("functionalLoc_lossCapture.balpcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}				

		else if(action.equals("functionalLoc_NoEntry.balpcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}				

		
		else if(action.equals("functionalLoc_Entry.balpcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbPrlmFactoryid");
			functLocFieldNameBean.setSbu("cmbPrlmSbu");
			functLocFieldNameBean.setPbu("cmbPrlmPbu");
			functLocFieldNameBean.setSection("cmbPrlmSectionid");
			functLocFieldNameBean.setCell("cmbPrlmCellid");
			functLocFieldNameBean.setMachine("cmbPrlmMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbPrlmFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		else if(action.equals("functionalLocation_Entry.balpcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbPrlmFactoryid");
			functLocFieldNameBean.setSbu("cmbPrlmSbu");
			functLocFieldNameBean.setPbu("cmbPrlmPbu");
			functLocFieldNameBean.setSection("cmbPrlmSectionid");
			functLocFieldNameBean.setCell("cmbPrlmCellid");
			functLocFieldNameBean.setMachine("cmbPrlmMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbPrlmFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		else if(action.equals("functionalLoc_LossEntry.bal_pcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}		
		
		else if( action.equals("getIsPcsEnabled.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String condParam = request.getParameter("condParam");
				List<String[]> pcsEnabled  = new ArrayList<String[]>();
				CommonFunctions.debugMsg("condParam"+condParam);				
				 pcsEnabled = pcsEntryService.getIsPcsEnabled(condParam);
				CommonFunctions.debugMsg("pcsEnabled...."+pcsEnabled);
				JSONObject pcsData =new JSONObject();
				if(pcsEnabled!=null){
  			 	
				pcsData.put("enableShift", "Y");
  			 	if (pcsEnabled.size()>0) {
	  			 	CommonFunctions.debugMsg("pcsEnabled.get(0)"+pcsEnabled.get(0)[0]);
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
					}
				else
				{
					pcsData.put("enableShift", "N");
					out.print(pcsData);
				}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				PrintWriter out = response.getWriter();
				e.printStackTrace();
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}				
		}		
		
		else if( action.equals("checkEquipmentFailure.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String lossId = request.getParameter("lossId");
				CommonFunctions.debugMsg("lossId"+lossId);				
				List<String[]> isEF = pcsEntryService.checkEquipmentFailure(lossId);
				CommonFunctions.debugMsg(isEF.size());
				
  			 	JSONObject efData =new JSONObject();
  			 	if (isEF.size()>0) {
	  			 	CommonFunctions.debugMsg("isEF.get(0)"+isEF.get(0)[0]);
	  			 	efData.put("isEF", isEF.get(0)[0]);
  			 	}
  			 	else
  			 		efData.put("isEF", "");
  			 	out.print(efData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("checkIsQtyLoss.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String lossId = request.getParameter("lossId");
				CommonFunctions.debugMsg("lossId"+lossId);				
				List<String[]> isQtyLoss = pcsEntryService.checkIsQtyLoss(lossId);
				CommonFunctions.debugMsg(isQtyLoss.size());
				
  			 	JSONObject qtyData =new JSONObject();
  			 	if (isQtyLoss.size()>0) {
	  			 	CommonFunctions.debugMsg("isQtyLoss"+isQtyLoss.get(0)[0]);	  			 	
	  			 	qtyData.put("isQtyLoss", isQtyLoss.get(0)[0]);
  			 	}
  			 	else
  			 		qtyData.put("isQtyLoss", "N");
  			 	out.print(qtyData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getFieldsForPcs.balpcs"))
		{
			try {
				CommonFunctions.debugMsg("getFieldsForPcs  serv;et");
				PrintWriter out = response.getWriter();				
				String fieldsForPcs  = pcsEntryService.getFieldsForPcs();
				CommonFunctions.debugMsg("hourlyEntry servt: " +fieldsForPcs );
				JSONObject plmData =new JSONObject();
				plmData.put("value", fieldsForPcs );
				out.print(plmData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getPldRemarks.balpcs"))
		{
			try {
				CommonFunctions.debugMsg("getPldRemarks.balpcs");
				PrintWriter out = response.getWriter();			
				String pldetailsId = request.getParameter("Pldetailsid");
				String detailTable = request.getParameter("detailTable");
				String remarks = pcsEntryService.getPldRemarks(detailTable, pldetailsId);
				
  			 	JSONObject allowData =new JSONObject();
  			 	CommonFunctions.debugMsg("remarks"+remarks);
  			 	allowData.put("remarks", remarks.replace("<**>", ""));
  			 	allowData.put("pldetailsId", pldetailsId);
  			 	out.print(allowData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("getEntryAllowDates.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();			
							
				List<String[]> allowDays = pcsEntryService.getEntryAllowDates();
				
  			 	JSONObject allowData =new JSONObject();
  			 	if (allowDays.size()>0) {
	  			 	CommonFunctions.debugMsg("allowDays.get(0)"+allowDays.get(0)[0]);
	  			 	allowData.put("days", allowDays.get(0)[0]);
  			 	}
  			 	else
  			 		allowData.put("days", "1");
  			 	out.print(allowData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("combo_rawtype.balpcs"))
		{
			try {						
				String productId = request.getParameter("productId");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> rawModel = pcsEntryService.getRawType(currentFilter ,  productId);
				UIUtils.writeComboBox(response,rawModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_Operator.balpcs"))
		{
			try {						
				String sectId = request.getParameter("sectId");
				CommonFunctions.debugMsg("sectId"+sectId);				
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getOperatorCombo(currentFilter, sectId);
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_shift.balpcs"))
		{
			try {						
				String factId = request.getParameter("factId");
				CommonFunctions.debugMsg("factId"+factId);		
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getShift(currentFilter ,factId);
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		else if( action.equals("combo_productModel.balpcs"))
		{
			CommonFunctions.debugMsg("In productmodel name");
			try {						
				String factId = request.getParameter("factId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("entryDate");
				CommonFunctions.debugMsg("factId"+factId);
				CommonFunctions.debugMsg("cellId"+cellId);	
				CommonFunctions.debugMsg("factId"+entryDate);	
				
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getproductModelCombo(currentFilter, factId, cellId, entryDate);
				CommonFunctions.debugMsg("prdModel.size"+prdModel.size());
				if (prdModel.size()>1)
					CommonFunctions.debugMsg("prdModel.size"+prdModel.get(0));
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}
		
		
		else if( action.equals("combo_productModelMch.balpcs"))
		{
			CommonFunctions.debugMsg("In productmodel name");
			try {						
				String factId = request.getParameter("factId");
				String cellId = request.getParameter("cellId");
				String machineId=request.getParameter("machineId");
				String entryDate = request.getParameter("entryDate");
				CommonFunctions.debugMsg("factId"+factId);
				CommonFunctions.debugMsg("cellId"+cellId);	
				CommonFunctions.debugMsg("factId"+entryDate);	
				List<ComboBox> prdModel =null;
				ComboFilter currentFilter = new ComboFilter();
				if(machineId!=null){
					prdModel= pcsEntryService.getproductModelCombo(currentFilter, factId, cellId,machineId, entryDate);

				}
				prdModel= pcsEntryService.getproductModelCombo(currentFilter, factId, cellId, entryDate);
				CommonFunctions.debugMsg("prdModel.size"+prdModel.size());
				if (prdModel.size()>1)
					CommonFunctions.debugMsg("prdModel.size"+prdModel.get(0));
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonFunctions.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_product.balpcs"))
		{
			try {						
				String factId = request.getParameter("factId");
				String prdModelId = request.getParameter("prdModelId");
				String cellId = request.getParameter("cellId");
				String machineId=request.getParameter("machineId");
				String entryDate = request.getParameter("entryDate");
				String rawMaterial = request.getParameter("rawMaterial");
				
				
				CommonFunctions.debugMsg("factIdfactId "+factId+" entryDate "+entryDate+"cellId "+cellId);
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  productModel=null;
				if(machineId==null){
					productModel = pcsEntryService.getproductCombo(currentFilter, factId, cellId,  prdModelId, entryDate,rawMaterial);

				}
				else{
				  productModel = pcsEntryService.getproductCombo(currentFilter,  machineId,  prdModelId, entryDate,rawMaterial);
				}
				CommonFunctions.debugMsg("factIdfactId comboproductfatcoryid"+factId+" entryDate "+entryDate);
				CommonFunctions.debugMsg("assembly.size"+productModel.size());
				if (productModel.size()>1)
					CommonFunctions.debugMsg("assembly.size"+productModel.get(0));
				UIUtils.writeComboBox(response, productModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		else if( action.equals("combo_ProdArea.balpcs"))
		{
			CommonFunctions.debugMsg("In Side the Combo prd area");
			try {				
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  arealist = pcsEntryService.getAreaCombo(currentFilter);
				
				if (arealist.size()>1)
					CommonFunctions.debugMsg("assembly.size"+arealist.get(0));
				UIUtils.writeComboBox(response, arealist,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		else if(action.equals("combo_LossEntHourno.balpcs")){
			try{
				String cellId=request.getParameter("cellId"); 
				String shiftId=request.getParameter("shiftId");
				String entryDate=request.getParameter("entryDate");
				String hourId=request.getParameter("hourId");
				CommonFunctions.debugMsg(" in side the Hour drop down");
				
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox> lossHourNo=pcsEntryService.getLossHourNo(currentFilter, cellId,shiftId,entryDate,hourId);
				UIUtils.writeComboBox(response, lossHourNo,comboFilter);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(action.equals("getLossHourTime.balpcs")){
			try{
				PrintWriter out = response.getWriter();
				String shiftId=request.getParameter("shiftId");
				String hourId=request.getParameter("hourId");
				List<String[]> lossHourTimeStarEnd=pcsEntryService.getLossHourTime(shiftId,hourId);
				
				JSONObject shiftData =new JSONObject();
				
				if (lossHourTimeStarEnd.size()>0){
					String startdate=lossHourTimeStarEnd.get(0)[0];
					String enddate=lossHourTimeStarEnd.get(0)[1];
					//int breakTime=Integer.parseInt(lossHourTimeStarEnd.get(0)[2]);
					String starttime=lossHourTimeStarEnd.get(0)[2];
					String endtime=lossHourTimeStarEnd.get(0)[3];
					/*String shiftCode = lossHourTimeStarEnd.get(0)[5];
					String shiftOrder = lossHourTimeStarEnd.get(0)[6];*/
					CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[0]);
					CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[1]);
					CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[2]);
					CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[3]);
					/*CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[4]);
					CommonFunctions.debugMsg(lossHourTimeStarEnd.get(0)[5]);
					
					int totalShifttime=CommonFunctions.getTimeDiff(startdate,enddate);
					CommonFunctions.debugMsg(totalShifttime);
				
			//		SimpleDateFormat simpleDatetimeFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
				//	totalShifttime=totalShifttime-breakTime;
					CommonFunctions.debugMsg(totalShifttime);*/
			
					shiftData.put("startDate",startdate);
					shiftData.put("endDate", enddate);
					shiftData.put("startTime", starttime);
					shiftData.put("endTime", endtime);
					/*shiftData.put("shiftCode", shiftCode);
					shiftData.put("shiftOrder", shiftOrder);
					shiftData.put("totalShifttime",totalShifttime);*/
				}
				else {
					shiftData.put("startTime","");
					shiftData.put("endTime", "");
				}
				
				out.print(shiftData);
				
			} 
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if( action.equals("combo_Manpower.balpcs"))
		{
			try {						
			
				String productId = request.getParameter("productId");
				String machineId = request.getParameter("machineId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("date");
				String sectId = request.getParameter("sectId");
				CommonFunctions.debugMsg("Date in cycletime"+entryDate);
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				
				List<ComboBox>  assembly = pcsEntryService.getManpowerCombo(currentFilter,cellId, machineId, productId, entryDate,sectId);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		else if(action.equals("combo_ToolId.balpcs")){    	
	    	CommonFunctions.debugMsg("tool.size combo_Tool.balpcs"+action);
	    	String machineid=request.getParameter("machineId");
	    	CommonFunctions.debugMsg("tool.size "+machineid);
	    	try{
	    	ComboFilter currentFilter = new ComboFilter();
	    	CommonFunctions.debugMsg("tool.size 1");
			List<ComboBox> toolList = pcsEntryService.getToolCombo(currentFilter,machineid);
			CommonFunctions.debugMsg("tool.size"+toolList.size());
			if (toolList.size()>1)
				CommonFunctions.debugMsg("tool.size"+toolList.get(0));
			UIUtils.writeComboBox(response,toolList,comboFilter);
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//CommonFunctions.debugMsg("condSql................"+e.toString());
			e.printStackTrace();
		}
	    }
		else if( action.equals("combo_Loss.balpcs"))
		{
			try {						

				String isQtyLoss = request.getParameter("isQtyLoss");
				String sectId = request.getParameter("sectId");
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
			
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
				
				List<ComboBox>  assembly = pcsEntryService.getLossCombo(currentFilter, isQtyLoss, sectId );
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		else if( action.equals("ShutDownLoss_input.balpcs"))
		{
			String jhTime=request.getParameter("jhTime");
			String Flid= request.getParameter("Flid");				
			String cellId =request.getParameter("cellid");
			String shiftId =request.getParameter("shiftId");
			String shifttext =request.getParameter("shifttext");
			String hourno =request.getParameter("hourno");
			String lossdate =request.getParameter("lossdate");
			String availTime =request.getParameter("availtime");
			String lossName =request.getParameter("lossName");
			System.out.println("Shift Id        "+lossName);
			request.setAttribute("flid", Flid);
			request.setAttribute("shiftId", shiftId);
			request.setAttribute("cellId", cellId);
			request.setAttribute("shifttext", shifttext);
			request.setAttribute("hourno", hourno);
			request.setAttribute("lossdate", lossdate);
			request.setAttribute("availTime", availTime);
			request.setAttribute("jhTime", jhTime);
			request.setAttribute("shiftId", shiftId);
			request.setAttribute("lossName", lossName);
			String activity = request.getParameter("activity");				
			httpSession.removeAttribute(WOConstants.backTo);
			String delActivity = request.getParameter("delActivity");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/PcsShutDownLoss.jsp"); 
			rd.forward(request, response);
			
			
		}
		else if( action.equals("ShutDownLoss_getCol.balpcs")){
		try {
			
		/*	PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.JhDetails", "pcsJhLossDetails"));
			 */
			PrintWriter out = response.getWriter();	  			
			String cellid = request.getParameter("cellid");
			CommonFunctions.debugMsg("BdKey Id- Above CommonFilter---------"+cellid);
			CommonFilter commonFilter = populateCommonFilter(request,"BDPcsCommonFilter",true);
			CommonFunctions.debugMsg("CommonFilter- After CommonFilter---------"+commonFilter);
			commonFilter.setKey(cellid);
			CommonFunctions.debugMsg("cell Keyid- After Set---------"+cellid);
			List<String[]> Grid = pcsEntryService.getJhEquipment(commonFilter);
			JSONObject colModel = getTableModelPcsBd(Grid ,commonFilter);
			colModel.set("tableHeight", "60%%");
			colModel.set("tableWidth", "90%%");
			httpSession.setAttribute("ColModel", colModel);
			System.out.println("before" );
			out.println(colModel);
			System.out.println("colModelSAPMaintainorder  "+colModel ); 
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
else if( action.equals("ShutDownLoss_getData.balpcs")){
	CommonFilter commonFilter = populateCommonFilter(request,"BDPcsCommonFilter",true);
	try {
		String cellid = request.getParameter("cellid");
		CommonFunctions.debugMsg("Sap Info Type:"+cellid);
		commonFilter.setKey(cellid);
		List<String[]> PcsEquipmentlist = pcsEntryService.getJhEquipment(commonFilter);
		PrintWriter out = response.getWriter();
		CommonFunctions.debugMsg("get data method1");
		JSONObject pcsbdMaster = UIUtils.convertToJqGridTableObject(PcsEquipmentlist, request, 1, 0);
		out.println(pcsbdMaster);	
		//
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
}
		//
else if( action.equals("speedLoss_input.balpcs"))
{
	String jhTime=request.getParameter("jhTime");
	String Flid= request.getParameter("Flid");				
	String cellId =request.getParameter("cellid");
	String shiftId =request.getParameter("shiftId");
	String shifttext =request.getParameter("shifttext");
	String hourno =request.getParameter("hourno");
	String lossdate =request.getParameter("lossdate");
	String availTime =request.getParameter("availtime");
	String lossName =request.getParameter("lossName");
	System.out.println("Shift Id        "+jhTime);
	request.setAttribute("flid", Flid);
	request.setAttribute("shiftId", shiftId);
	request.setAttribute("cellId", cellId);
	request.setAttribute("shifttext", shifttext);
	request.setAttribute("hourno", hourno);
	request.setAttribute("lossdate", lossdate);
	request.setAttribute("availTime", availTime);
	request.setAttribute("jhTime", jhTime);
	request.setAttribute("shiftId", shiftId);
	request.setAttribute("lossName", lossName);
	String activity = request.getParameter("activity");				
	httpSession.removeAttribute(WOConstants.backTo);
	String delActivity = request.getParameter("delActivity");
	RequestDispatcher rd = request.getRequestDispatcher("/pages/PcsShutDownLoss.jsp"); 
	rd.forward(request, response);
	
}
else if( action.equals("speedLoss_getCol.balpcs")){
try {
	CommonFunctions.debugMsg("cell Keyid- After Set---------");
	PrintWriter out = response.getWriter();	  			
	String cellid = request.getParameter("cellid");
	CommonFunctions.debugMsg("BdKey Id- Above CommonFilter---------"+cellid);
	CommonFilter commonFilter = populateCommonFilter(request,"BDPcsCommonFilter",true);
	CommonFunctions.debugMsg("CommonFilter- After CommonFilter---------"+commonFilter);

	commonFilter.setKey(cellid);
	CommonFunctions.debugMsg("cell Keyid- After Set---------"+cellid);
	List<String[]> Grid = pcsEntryService.getJhEquipmentSpeed(commonFilter);
	
	JSONObject colModel = getTableModelSpeedLoss(Grid ,commonFilter);
	colModel.set("tableHeight", "60%%");
	colModel.set("tableWidth", "65%%");
	httpSession.setAttribute("ColModel", colModel);
	System.out.println("before" );
	out.println(colModel);
	System.out.println("colModelSAPMaintainorder  "+colModel );
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
else if( action.equals("speedLoss_getData.balpcs")){
CommonFilter commonFilter = populateCommonFilter(request,"BDPcsCommonFilter",true);
try {
String cellid = request.getParameter("cellid");
CommonFunctions.debugMsg("Sap Info Type:"+cellid);
commonFilter.setKey(cellid);
List<String[]> PcsEquipmentlist = pcsEntryService.getJhEquipmentSpeed(commonFilter);
PrintWriter out = response.getWriter();
CommonFunctions.debugMsg("get data method1");
JSONObject pcsbdMaster = UIUtils.convertToJqGridTableObject(PcsEquipmentlist, request, 1, 0);
out.println(pcsbdMaster);	
//
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}		
}	
		//
		
		else if( action.equals("combo_SubLoss.balpcs"))
		{
			try {						

				String isQtyLoss = request.getParameter("isQtyLoss");
				String sectId = request.getParameter("sectId");
				String mainLossId = request.getParameter("mainlossId");
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
				
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
			
				List<ComboBox>  assembly = pcsEntryService.getSubLossCombo(currentFilter,mainLossId);
				
				CommonFunctions.debugMsg("assembly.size"+assembly.size());
				if (assembly.size()>1)
					CommonFunctions.debugMsg("assembly.size"+assembly.get(0));
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		else if(action.equals("combo_Productname.balpcs"))
		{
			try {		
				CommonFunctions.debugMsg("combo_Productname.balpcs");

				String isQtyLoss = request.getParameter("isQtyLoss");
				String sectId = request.getParameter("sectId");
				String factId = request.getParameter("factId");
				String prdModelId = request.getParameter("prdModelId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				String rawMaterial = request.getParameter("rawMaterial");
				
				CommonFunctions.debugMsg("factIdfactId"+factId);
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
			
			//	List<ComboBox>  assembly = pcsEntryService.getSubLossCombo(comboFilter);
				List<ComboBox>  assembly = pcsEntryService.getproductCombo(currentFilter, factId, mchId, prdModelId, entryDate, rawMaterial);
				CommonFunctions.debugMsg("assembly.size"+assembly.size());
				if (assembly.size()>1)
					CommonFunctions.debugMsg("assembly.size"+assembly.get(0));
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
		
		
		else if( action.equals("combo_LossNo.balpcs"))
		{
			try {						

				String isQtyLoss = request.getParameter("isQtyLoss");
				String sectId = request.getParameter("sectId");
				
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  assembly = pcsEntryService.getLossNoCombo(currentFilter, isQtyLoss, sectId );
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("combo_Wno.balpcs"))
		{
			try {						
				String Pldetailsid = request.getParameter("Pldetailsid");
				String sectId = request.getParameter("sectId");
				String entryDate = request.getParameter("entryDate");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  wno = pcsEntryService.getWno(currentFilter,  sectId, Pldetailsid,entryDate);
				UIUtils.writeComboBox(response, wno,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		else if( action.equals("combo_QTYPhenomena.balpcs"))
		{
			try {
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  qtyPhen = pcsEntryService.getQTYPhenomena(currentFilter);
				UIUtils.writeComboBox(response, qtyPhen,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		else if( action.equals("combo_cause.balpcs"))
		{
			try {
				String isQtyLoss = request.getParameter("isQtyLoss");
				String parentId = request.getParameter("parentId");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  cause = pcsEntryService.getCause(currentFilter,isQtyLoss,parentId);
				UIUtils.writeComboBox(response, cause,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		else if( action.equals("combo_pcsLossCause.balpcs"))
		{
			try {
				String isQtyLoss = request.getParameter("isQtyLoss");
				String parentId = request.getParameter("parentId");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  cause = pcsEntryService.getPcsLossCause(currentFilter,isQtyLoss,parentId);
				UIUtils.writeComboBox(response, cause,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		else if( action.equals("combo_rootcause.balpcs"))
		{
			try {
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  rootcause = pcsEntryService.getRootCause(currentFilter);
				UIUtils.writeComboBox(response, rootcause,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getEntryExistsDates.balpcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("entryDate");								
				String cellId = request.getParameter("cellId");				
				String detailTable = request.getParameter("detailTable");
				
				List<String[]> entryDatesList = pcsEntryService.getEntryExistsDates(detailTable, entryDate, cellId);
				
				JSONObject eedObj = new JSONObject();
				
				JSONArray rowArr = new JSONArray();
				for (int i = 0; i < entryDatesList.size(); i++) {
					JSONObject rowObj = new JSONObject();
					rowObj.put("compFlg", entryDatesList.get(i)[0]);
					rowObj.put("shiftKeyId", entryDatesList.get(i)[1]);
					rowObj.put("shiftOrder", entryDatesList.get(i)[2]);
					rowObj.put("entryDate", entryDatesList.get(i)[3]);
					rowArr.put(rowObj);
				}
				//JSONObject entryDatesData = UIUtils.convertToJqGridTableObject(entryDatesList,request,0,0);
				//plmData.put("entryDates",entryDatesList );
				eedObj.put("eedData", rowArr);
				CommonFunctions.debugMsg("JSON : "+eedObj);
				out.print(eedObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		
		else if( action.equals("getHolidayDates.balpcs"))
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
				CommonFunctions.debugMsg("JSON : "+holiObj);
				out.print(holiObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		

		else if( action.equals("getDateOeeValue.balpcs"))
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
					rowObj.put("isPending", oeeDatesList.get(i)[4]);
					rowObj.put("QACompleted", oeeDatesList.get(i)[5]);
					rowObj.put("fullyNoplan", oeeDatesList.get(i)[6]);
					rowArr.put(rowObj);
				}
				oeeObj.put("oeeData", rowArr);
				CommonFunctions.debugMsg("JSON : "+oeeObj);
				out.print(oeeObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			

		else if( action.equals("getPendingTime.balpcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String detailTable = request.getParameter("detailTable");
				String pldetailsId = request.getParameter("pldetailsId");		

				List<String[]> pendingList = pcsEntryService.getPendingTime(detailTable,pldetailsId);				
				CommonFunctions.debugMsg(pendingList.size());

  			 	JSONObject pendingData =new JSONObject();
  			 	
  			 	if (pendingList.size()>0) {
	  			 	CommonFunctions.debugMsg("pendingList.get(0)"+pendingList.get(0)[0]);
	  			 	pendingData.put("pendingTime", pendingList.get(0)[0]);
  			 	}
  			 	else
  			 		pendingData.put("pendingTime", "");
  			 	out.print(pendingData);				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		else if( action.equals("selectedProductModel.balpcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String prdId = request.getParameter("prdId");
				String modelList = pcsEntryService.getSelectModel(prdId);			
				
  			 	JSONObject pendingData =new JSONObject();  			 	
  			 	if (UIUtils.isValidKeyId(modelList)) {
	  			 	CommonFunctions.debugMsg("modelList(0)"+modelList);
	  			 	pendingData.put("modelKeyid", modelList);
  			 	}
  			 	else
  			 		pendingData.put("modelKeyid", "");
  			 		out.print(pendingData);				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		else if( action.equals("getLossRelatedValues.balpcs"))
		{
			try {	
				PrintWriter out = response.getWriter();		
				String lossId = request.getParameter("lossId");
				String plDtlId = request.getParameter("plDetailId");
				List<String[]> lossValList = new ArrayList<String[]>();
				lossValList = pcsEntryService.selectLossRelatedValues(lossId,plDtlId);
				if(lossValList != null)
				{
					if(lossValList.size()>0)
					{
						JSONObject lossData =new JSONObject();
						lossData.put("lossReasonLinkId", lossValList.get(0)[0]);
						lossData.put("reasonId", lossValList.get(0)[1]);
						lossData.put("causeId", lossValList.get(0)[2]);
						lossData.put("rootcauseId", lossValList.get(0)[3]);
						lossData.put("minutes", lossValList.get(0)[4]);
						lossData.put("instance", lossValList.get(0)[5]);
						lossData.put("remarks", lossValList.get(0)[6]);	
						out.print(lossData);	
					}
				}
				
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		else if( action.equals("getDetailIdValues.balpcs"))
		{
			try {	
				PrintWriter out = response.getWriter();		
				String productId = request.getParameter("productId");
				String mchId = request.getParameter("mchId");
				String sectId = request.getParameter("sectId");
				String entryDate = request.getParameter("entryDate");
				String mstId = request.getParameter("mstId");
				String plDetailId = request.getParameter("plDetailId");
				List<String[]> detailList = new ArrayList<String[]>();
				detailList = pcsEntryService.selectDetailIdValues(productId, mchId, sectId, entryDate, mstId, plDetailId);
				if(detailList != null)
				{
					if(detailList.size()>0)
					{
						JSONObject detailData =new JSONObject();
						detailData.put("detailId", detailList.get(0)[0]);
						detailData.put("wno", detailList.get(0)[1]);
						detailData.put("trimQty", detailList.get(0)[2]);
						detailData.put("expQty", detailList.get(0)[3]);						
						detailData.put("backLogQty", detailList.get(0)[4]);
						detailData.put("rawMaterial", detailList.get(0)[5]);
						detailData.put("weight", detailList.get(0)[6]);						  
						out.print(detailData);	
					}
				}
				
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if(action.equals("pcsAncilliaryGrid_getCol.balpcs"))
		{	 
			CommonFunctions.debugMsg("pcsAncilliaryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsAncilliarycolModel"));

		}
		else if( action.equals("pcsAncilliaryGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsAncilliaryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();

				String prodMchId = request.getParameter("prodMchId");
				String condParam = request.getParameter("condParam");
				
				List<String []> closedMsrList  = pcsEntryService.getClosedMsr(prodMchId, condParam);
				CommonFunctions.debugMsg(closedMsrList.size());
  			 	JSONObject closedMsrData = UIUtils.convertToJqGridTableObject(closedMsrList,request,0,0);
  			 	CommonFunctions.debugMsg(closedMsrData);
  			 	out.println(closedMsrData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}		
	
		else if( action.equals("getMsrsDownTime.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("getMsrsDownTime");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();

				String msrNos = request.getParameter("msrNos");				
				
				List<String []> msrDownList  = pcsEntryService.getMsrsDownTime(msrNos);
				CommonFunctions.debugMsg(msrDownList.size());

  			 	JSONObject msrDownData =new JSONObject();
  			 	
  			 	if (msrDownList.size()>0) {
	  			 	CommonFunctions.debugMsg("isEF.get(0)"+msrDownList.get(0)[0]);
	  			 	msrDownData.put("downTime", msrDownList.get(0)[0]);
  			 	}
  			 	else
  			 		msrDownData.put("downTime", "");
  			 	out.print(msrDownData);
  			 	
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}
		
		
		else if( action.equals("checkIsOpenMsr.balpcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String mchId = request.getParameter("mchId");
				String date = request.getParameter("date");		
				String shift = request.getParameter("shift");
				if(!UIUtils.isValidKeyId(shift))
				{
					String factId = request.getParameter("factId");
					String shiftCode = request.getParameter("shiftCode");
					CommonFunctions.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
					shift = pcsComplianceRptService.getShiftKeyid(factId, shiftCode);
				}
				String isClear = request.getParameter("isClear");
				CommonFunctions.debugMsg("shift:"+shift); 
				List<String[]> isOpenMsr = pcsEntryService.checkIsOpenMsr(mchId, date, shift);
				JSONObject openMsrObj = new JSONObject();				
				JSONObject msrData =new JSONObject();
  			 	if (isOpenMsr.size()>0) {
	  			 	CommonFunctions.debugMsg("isEF.get(0)"+isOpenMsr.get(0)[0]);
	  			 	msrData.put("isOpenMsr", isOpenMsr.get(0)[0]);
	  			 	msrData.put("shift",shift);
  			 	}
  			 	else
  			 		msrData.put("isOpenMsr", "");
				
				openMsrObj.put("msrData", msrData);
				CommonFunctions.debugMsg("isClear"+isClear);
				if(UIUtils.isValidKeyId(isClear))
					openMsrObj.put("isClear", isClear);
				else
					openMsrObj.put("isClear", "Y");
				CommonFunctions.debugMsg("JSON : "+openMsrObj);
				out.print(openMsrObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		else if( action.equals("getMasterKeyid.balpcs"))
		{
			try {						
				
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");				
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				
				String Plmasterid   = pcsEntryService.getMasterKeyid(entryDate, shift, cellId);
				String[] expQtyMand = pcsEntryService.isExpansionQtyNotMandatory(sectId);
				String expQtyMandFlag = "Y";
				if(UIUtils.isValidKeyId(expQtyMand[1]))
				{
					expQtyMandFlag = expQtyMand[1];					
				}
						
				JSONObject plmData =new JSONObject();
				plmData.put("Plmasterid", Plmasterid);
				plmData.put("ExpQtyMandFlag", expQtyMandFlag);					
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("getLastShiftProduct.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String factId = request.getParameter("factId");
				String cellId = request.getParameter("cellId");
		//		String mchId =null;
				String flId= request.getParameter("flId");
				String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");
				List<String[]>  prevData = pcsEntryService.getLastShiftProduct(factId, cellId, flId,entryDate, shift);
				JSONObject plmData =new JSONObject();
				//CommonFunctions.debugMsg("size : "+prevData.size());
				if (prevData.size()>0 ) {		
					CommonFunctions.debugMsg("0 : "+prevData.get(0)[0]);
					CommonFunctions.debugMsg("1 : "+prevData.get(0)[1]);
					plmData.put("keyId", prevData.get(0)[0]);
					plmData.put("productId", prevData.get(0)[1]);
					plmData.put("productModelId", prevData.get(0)[2]);
					plmData.put("manPower", prevData.get(0)[3]);
					plmData.put("cycleTime", prevData.get(0)[4]);
				}
				CommonFunctions.debugMsg("plmData : "+plmData);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		

		else if( action.equals("getCurrentShift.balpcs"))
		{
			try {					
				
				PrintWriter out = response.getWriter();				
				String factId = request.getParameter("factId");
				
				String shiftId = pcsEntryService.getCurrentShift(factId);
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
		
		else if( action.equals("getShiftKeyid.balpcs"))
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
		
		else if (action.equals("getShiftStarEndTime.balpcs")) {
		try {					
				
				PrintWriter out = response.getWriter();				
				String shiftId = request.getParameter("shiftId");
				
				List<String[]> shiftStrEnd = pcsEntryService.getShiftStarEndTime(shiftId);
				JSONObject shiftData =new JSONObject();
				
				if (shiftStrEnd.size()>0){
					String startdate=shiftStrEnd.get(0)[0];
					String enddate=shiftStrEnd.get(0)[1];
					int breakTime=Integer.parseInt(shiftStrEnd.get(0)[2]);
					String starttime=shiftStrEnd.get(0)[3];
					String endtime=shiftStrEnd.get(0)[4];
					String shiftCode = shiftStrEnd.get(0)[5];
					String shiftOrder = shiftStrEnd.get(0)[6];
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[0]);
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[1]);
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[2]);
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[3]);
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[4]);
					CommonFunctions.debugMsg(shiftStrEnd.get(0)[5]);
					
					//int totalShifttime=CommonFunctions.getTimeDiff(startdate,enddate);
					int totalShifttime=pcsEntryService.getShiftTotaltime(startdate,enddate,shiftId);
					CommonFunctions.debugMsg(totalShifttime);
				
			//		SimpleDateFormat simpleDatetimeFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
				//	totalShifttime=totalShifttime-breakTime;
					CommonFunctions.debugMsg(totalShifttime);
			
					shiftData.put("startDate",startdate);
					shiftData.put("endDate", enddate);
					shiftData.put("startTime", starttime);
					shiftData.put("endTime", endtime);
					shiftData.put("shiftCode", shiftCode);
					shiftData.put("shiftOrder", shiftOrder);
					shiftData.put("totalShifttime",totalShifttime);
				}
				else {
					shiftData.put("startTime","");
					shiftData.put("endTime", "");
				}
				
				out.print(shiftData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("getAllTime.balpcs"))
		{
			try {						
				PrintWriter out = response.getWriter();
				
				String hourId = request.getParameter("hourId");
				String cellId = request.getParameter("cellId");
				String shiftId = request.getParameter("shiftId");
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("entryDate");
				String shiftWise = request.getParameter("shiftWise");
				
				JSONObject plmData =new JSONObject();
			//	plmData= getAllTimeForMachine(cellId, sectId, plmasterId, entryDate);
				List<String[]> getAllDetailtime = pcsEntryService.getAllTimeforHour(cellId, shiftId,  entryDate,hourId,shiftWise);
				  // NULL-CHECK before calling .size()
		        if (getAllDetailtime == null) {
		            getAllDetailtime = new ArrayList<String[]>();
		        }

				JSONObject alltime =new JSONObject();
				CommonFunctions.debugMsg("getAllDetailtime.size() :"+getAllDetailtime.size());
				if(getAllDetailtime.size() > 0)
				{
					alltime.put("availtime", getAllDetailtime.get(0)[0]);
					alltime.put("unaccountedtime", getAllDetailtime.get(0)[1]);
					alltime.put("productiontime",getAllDetailtime.get(0)[2] );
				}
				else
				{
					//alltime.put("unaccountedtime", "0");
					//if(shiftWise.equals("Y")){
					if ("Y".equals(shiftWise)) {
						alltime.put("availtime", "480");
					}
					else{
						alltime.put("availtime", "60");
					}
					
					alltime.put("unaccountedtime", "0");
					alltime.put("productiontime","0");
				}
			//	pldetailldata = 
				out.print(alltime);

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		else if (action.equals("getShiftEndTime.balpcs")) {
			try {					
					
					PrintWriter out = response.getWriter();				
					String shiftId = request.getParameter("shiftId");
					
					List<String[]> shiftStrEnd = pcsEntryService.getShiftEndTime();
					JSONObject shiftData =new JSONObject();
					
					if (shiftStrEnd.size()>0){ 
						shiftData.put("startTime", shiftStrEnd.get(0)[0]);
						shiftData.put("endTime", shiftStrEnd.get(0)[1]);
					}
					else {
						shiftData.put("startTime", "");
						shiftData.put("endTime", "");
					}
					
					out.print(shiftData);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		
		else if( action.equals("getReasonLossNo.balpcs"))
		{
			try {					
				
				PrintWriter out = response.getWriter();				
				String reasonId = request.getParameter("reasonId");
				
				List<String[]> lossIdDet = pcsEntryService.getReasonLossNo( reasonId);
				
				JSONObject lossIdData =new JSONObject();
				if ( lossIdDet.size()>0){ 
					lossIdData.put("lossId", lossIdDet.get(0)[0]);
					lossIdData.put("lossUom", lossIdDet.get(0)[1]);
				}
				else {
					lossIdData.put("lossId", "");
					lossIdData.put("lossUom", "");
				}
				
				out.print(lossIdData);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("getSubMainLossNo.balpcs"))
		{
			try {					
				
				PrintWriter out = response.getWriter();				
				String subLossid = request.getParameter("subLossid");
				
				List<String[]> lossIdDet = pcsEntryService.getSubMainLossNo( subLossid);
				
				JSONObject lossIdData =new JSONObject();
				if ( lossIdDet.size()>0){ 
					lossIdData.put("lossId", lossIdDet.get(0)[0]);
				}
				else {
					lossIdData.put("lossId", "");
				}
				
				out.print(lossIdData);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	

		
		else if( action.equals("getCalendarTime.balpcs"))
		{
			try {						
				PrintWriter out = response.getWriter();
				
				String machineId = request.getParameter("machineId");
				String sectId = request.getParameter("sectId");
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("entryDate");
				String shiftWise = request.getParameter("shiftWise");
				CommonFunctions.debugMsg(" Shift Wise in side get Calendar "+shiftWise);
				
				String calendarTime   = pcsEntryService.getCalendarTime(machineId, plmasterId, sectId,entryDate,shiftWise);
				if(UIUtils.isValidKeyId(calendarTime))
				{
					if(calendarTime.indexOf("0") == 0 || calendarTime.indexOf("-")== 0 )
						calendarTime = "0";
					else
					{
						NumberFormat df = DecimalFormat.getInstance();
			    		df.setMinimumFractionDigits(2);
			    		df.setMaximumFractionDigits(2);
			    		df.setRoundingMode(RoundingMode.UP);
			    		calendarTime = df.format(Float.parseFloat(calendarTime));
					}
				}
				JSONObject plmData =new JSONObject();
				plmData.put("calendarTime", calendarTime);
				out.print(plmData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
				
		
		
		else if( action.equals("getDetailTableName.balpcs"))
		{
			try {
				PrintWriter out = response.getWriter();				
				String sectId = request.getParameter("cellId");				
				String entryDate = request.getParameter("entryDate");
				String detailTableName  = pcsEntryService.getDetailTableName(sectId, entryDate);
				JSONObject plmData =new JSONObject();
				plmData.put("detailTableName", detailTableName);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		
		else if( action.equals("combo_Phenomena.balpcs"))
		{
			try {						
				String lossId = request.getParameter("lossId");
				String cellId = request.getParameter("cellId");
				ComboFilter currentFilter = new ComboFilter();
				currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getPhenomenaCombo(currentFilter, lossId,cellId);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		else if( action.equals("combo_rejectionPhenomena.balpcs"))
		{
			try {						
			//	String lossId = request.getParameter("lossId");
			//	String cellId = request.getParameter("cellId");
				ComboFilter currentFilter =UIUtils.fillComboFilter(request);
				//List<ComboBox>  assembly = pcsEntryService.getPhenomenaCombo(currentFilter, lossId,cellId);
				List<ComboBox>  assembly = pcsEntryService.getRejectionPhenomenaCombo(currentFilter);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		/*else if( action.equals("combo_Manpower.pcs"))
		{
			try {						
			
				String productId = request.getParameter("productId");
				String machineId = request.getParameter("machineId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("date");
				String sectId = request.getParameter("sectId");
				CommonFunctions.debugMsg("Date in cycletime"+entryDate);
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				
				List<ComboBox>  assembly = pcsEntryService.getManpowerCombo(currentFilter,cellId, machineId, productId, entryDate,sectId);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	*/
		else if( action.equals("text_Cycletime.balpcs"))
		{
			try {						
				PrintWriter out = response.getWriter();	
				String productId = request.getParameter("productId");
				String machineId = request.getParameter("machineId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("date");
				String sectId = request.getParameter("sectId");
				ComboFilter currentFilter = new ComboFilter();
				List<String []>  assembly = pcsEntryService.getCycletime(currentFilter,cellId, machineId, productId, entryDate,sectId);
				JSONObject result = new JSONObject();
				CommonFunctions.debugMsg("assembly "+assembly+"  size.. "+assembly.size());
				//if(assembly.size()<=1){
				//if(assembly.size()>=0){
				if(assembly.size()>0){
				result.put("id", assembly.get(0)[0]);
				result.put("text", assembly.get(0)[1]);
				}
				else
				{
					result.put("id","");
					result.put("text","");
				}
				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		
		else if( action.equals("updatePldRemarks.balpcs"))
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
		
		CommonFunctions.debugMsg(" dispatchUrl " + dispatchUrl);
		if (dispatchUrl != null)
		{
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			CommonFunctions.debugMsg(" response " + response); 
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
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}	*/
		else if( action.equals("monthGrid_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("monthGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "monthcolModel"));
		}
		else if( action.equals("monthGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("monthGrid_getdata.balpcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				String factId = request.getParameter("factId");				
				String date = request.getParameter("date");		
				String sectId = request.getParameter("sectId");						
				String cellId = request.getParameter("cellId");		
				String userId = user.getUsrm_ccno();
				PrintWriter out = response.getWriter();
				//List<String []> bdMasterList  = pcsEntryService.getParameters(factId);
				//List<String []> calendarList  = pcsEntryService.getCalendar(factId,date,userId);
				List<String []> calendarList  = pcsEntryService.getPCSCalendar(factId, date, userId, sectId, cellId);
				CommonFunctions.debugMsg(calendarList.size());
  			 	JSONObject calendarData = UIUtils.convertToJqGridTableObject(calendarList,request,0,0);
  			 	CommonFunctions.debugMsg(calendarData);
  			 	out.println(calendarData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("productionGrid_getCol.balpcs") )
		{
			try{
			CommonFunctions.debugMsg("productionGrid_getCol.balpcs");
			CommonFilter commonFilter  = new CommonFilter();
			commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			PrintWriter out = response.getWriter();
			String factId = request.getParameter("factId");
			String shiftId = request.getParameter("shiftId");
			String sectId = request.getParameter("sectId");
			String entryDate = request.getParameter("entryDate");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			CommonFunctions.debugMsg(factId +"-"+shiftId+"-"+"-"+entryDate+"-"+cellId+"--"+sectId);
			
			List<String []> pcsMasterList  = pcsEntryService.getParameters(sectId);
			List<String []> pcsMasterList2  = pcsEntryService.getProdLoss(shiftId,entryDate,sectId, cellId, mchId,factId);
			if(pcsMasterList2!=null){
				//JSONObject pcsMasterData = UIUtils.convertToJqGridTableObject(pcsMasterList,request,0,0);
				//JSONObject pcsMasterData = fillPCSGrid(pcsMasterList,pcsMasterList2,request);
				JSONObject pcsMasterData = fillPCS(pcsMasterList,pcsMasterList2,request);
				//httpSession.setAttribute("pcsMasterData", pcsMasterData);
				//CommonFunctions.debugMsg("productionGrid_getCol size"+pcsMasterList.size());
				//CommonFunctions.debugMsg("productionGrid_getCol size2"+pcsMasterList2.size());
				//JSONObject jsonObject = getTableModel(pcsMasterList,pcsMasterList2);
				//jsonObject.put("rowNumbers", false);
					//   jsonObject.put("pager", false);
				//   jsonObject.put("tableButton", true);
			    CommonFunctions.debugMsg(pcsMasterData);
				out.println(pcsMasterData);
				

				//PrintWriter out = response.getWriter();
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "productioncolModel"));
			
			}
			
			
			}
			catch(Exception e)
			{
				 PrintWriter out = response.getWriter();
				 e.printStackTrace();
				CommonFunctions.debugMsg("exception throws....");
				CommonFunctions.debugMsg(e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Cannot Enter for Future Shift");
				out.print(err.toString());
				
			}
		}
		else if( action.equals("productionGrid_getData.balpcs") )
		{
			try
			{	
				 PrintWriter out = response.getWriter();
				 JSONObject jsonObject = new JSONObject();
				 jsonObject = (JSONObject) httpSession.getAttribute("pcsEntryDataServlet");
				 CommonFunctions.debugMsg(jsonObject);
	        	
				 out.println(jsonObject);
				/*CommonFunctions.debugMsg("monthGrid_getdata.pcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String factId = request.getParameter("factId");
				CommonFunctions.debugMsg("factId"+ factId);
				List<String []> bdMasterList  = pcsEntryService.getParameters(factId);
				CommonFunctions.debugMsg(bdMasterList.size());
  			 	JSONObject bdMasterData = UIUtils.convertToJqGridTableObject(bdMasterList,request,0,0);
  			 	CommonFunctions.debugMsg(bdMasterData);
  			 	out.println(bdMasterData);*/

			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pcsEntryGridTest_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsEntrycolModelNew"));
		}
		else if( action.equals("pcsEntryGridTest_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsentryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String sectId = request.getParameter("sectId");
				CommonFunctions.debugMsg("sectId123="+sectId);
				String mchId = request.getParameter("mchId");
				CommonFunctions.debugMsg("machId"+ mchId);
				List<String []> sublossList  = pcsEntryService.getEntryGrid(entryDate,shift,sectId, mchId);
				CommonFunctions.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonFunctions.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}	
		else if( action.equals("pcsEntryGrid_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsEntrycolModel"));
		}
		else if( action.equals("pcsEntryGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsentryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String sectId = request.getParameter("sectId");
				CommonFunctions.debugMsg("sectId123="+sectId);
				String mchId = request.getParameter("mchId");
				CommonFunctions.debugMsg("machId"+ mchId);
				List<String []> sublossList  = pcsEntryService.getEntryGrid(entryDate,shift,sectId, mchId);
				CommonFunctions.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonFunctions.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}		

		
		else if( action.equals("pcsLossEntryGrid_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsLossEntrycolModelNew"));
		}
		else if( action.equals("pcsLossEntryGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsLossEntryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");
				CommonFunctions.debugMsg("pldetailsid"+ pldetailsid);
				List<String []> sublossList  = pcsEntryService.getSubLossGrid(pldetailsid);
				CommonFunctions.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonFunctions.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}		
		else if( action.equals("pcsLossEntryGridNew_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsLossEntrycolModelNewGrid"));
		}
		else if( action.equals("pcsLossEntryGridNew_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsLossEntryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");
				CommonFunctions.debugMsg("pldetailsid"+ pldetailsid);
				List<String []> sublossList  = pcsEntryService.getSubLossGridNew(pldetailsid);
				CommonFunctions.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonFunctions.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}		

		else if( action.equals("pcsLossEntryGridNew_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsLossEntrycolModelNewGrid"));
		}
		else if( action.equals("pcsLossEntryGridNew_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsLossEntryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");
				CommonFunctions.debugMsg("pldetailsid"+ pldetailsid);
				List<String []> sublossList  = pcsEntryService.getSubLossGrid(pldetailsid);
				CommonFunctions.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonFunctions.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}		

		else if( action.equals("pcsResultGrid_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsResultGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel","pcsResultcolModel"));
			/*
			CommonFilter commonFilter  = new CommonFilter();
			commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);			
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String sectId = request.getParameter("sectId");
			String mchId = request.getParameter("mchId");	
			CommonFunctions.debugMsg("entryDate"+ entryDate);
			List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
			CommonFunctions.debugMsg(pcsResultList.size());
			
			JSONObject jsonObject = getResultTableModel(pcsResultList);
			httpSession.removeAttribute("pcsResultColModel");
	 	    httpSession.setAttribute("pcsResultColModel",jsonObject);
	 	   CommonFunctions.debugMsg("jsonObject"+jsonObject);
	 	   out.println(jsonObject);
		*/
		}
		
		else if( action.equals("pcsResultGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsResultGrid_getData.balpcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				String mchId = request.getParameter("mchId");	
				CommonFunctions.debugMsg("entryDate"+ entryDate);
				List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
				CommonFunctions.debugMsg(pcsResultList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsResultList,request,0,1);
  			 	CommonFunctions.debugMsg(pcsResultData);
  			 	out.println(pcsResultData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}			

		else if(action.equals("pcsproductionEntry_getCol.balpcs")){
			CommonFunctions.debugMsg("In  pcs get call");
			CommonFunctions.debugMsg("pcsproduction_getCol.balpcs");
			PrintWriter out = response.getWriter();
			CommonFunctions.debugMsg("pcsproduction_getCol.balpcs");
			response.setContentType("text/html");
			CommonFunctions.debugMsg("pcsproduction_getCol.balpcs");
			String tableModel;
			CommonFunctions.debugMsg(request.getParameter("shiftWise") +" shift wis");
			if(request.getParameter("shiftWise").equals("Y"))
				tableModel= UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel","pcsProductionEntrycolModelShift");
	         else
			     tableModel= UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel","pcsProductionEntrycolModel");

				JSONObject colmodel =JSONObject.fromString(tableModel); 
	 		out.println(colmodel);
		
		}
		else if(action.equals("pcsproductionEntry_getData.balpcs")){
			CommonFunctions.debugMsg("In pcs producitions Entey get data");
			try {

			//	String flid = request.getParameter("flid");
				String cellid =  request.getParameter("cellId");
				String date = request.getParameter("fromdate");
				String Todate = request.getParameter("todate");
				String shiftid = request.getParameter("shiftid");
				//List<String[]> lossgrid = pcsEntryService.getPcsLossCaptureGrid(flid, date,Todate ,shiftid);
				List<String[]> productiongrid = pcsEntryService.getPcsProductionCaptureGrid(cellid, date,Todate ,shiftid);
				PrintWriter out = response.getWriter();
				JSONObject productiongridmod = UIUtils.convertToJqGridTableObject(productiongrid, request, 0,0);
					out.println(productiongridmod);
					BAL_PcsTlDtl newPcsTlDtl = new BAL_PcsTlDtl();
					CommonFunctions.debugMsg(newPcsTlDtl.getNoplaninmins()+"  no plan minutes.........");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else if(action.equals("pcsrejectionEntry_getCol.balpcs")){
			CommonFunctions.debugMsg("In  pcs Rejection call");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel","pcsRejectionEntrycolModel");
	 		JSONObject colmodel =JSONObject.fromString(tableModel); 
	 		out.println(colmodel);
		
		}
	else if(action.equals("pcsrejectionEntry_getData.balpcs")){
			CommonFunctions.debugMsg("In pcs producitions aunder get data");
		try{
			String mstKeyid = request.getParameter("mstKeyid");
			//
			//List<String[]> rejectionGrid = pcsEntryService.getPcsProductionCaptureGrid(flid, date,Todate ,shiftid);
			List<String[]> rejectionGrid = pcsEntryService.getPcsRejectionCaptureGrid(mstKeyid);
			PrintWriter out = response.getWriter();
			JSONObject productiongridmod = UIUtils.convertToJqGridTableObject(rejectionGrid, request, 0,0);
				out.println(productiongridmod);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
		
//		else if(action.equals("pcsLoss_getCol.balpcs")){
//			PrintWriter out = response.getWriter();
//			JSONObject jsonObject = null;
//			List<String[]> lossgrid = null;
//			
//			try {
//				String flid = request.getParameter("flid");
//				String date = request.getParameter("fromdate");
//				String todate = request.getParameter("todate");
//				String shiftid = request.getParameter("shiftid");
//				lossgrid = pcsEntryService.getPcsLossCaptureGrid(flid, date,todate ,shiftid);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			 jsonObject = getTableModel(lossgrid);
//		
//		
//			out.println(jsonObject);
//			
//		}
		
	else if(action.equals("pcsLoss_getCol.balpcs")){
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel","pcsLossEntrycolModel");
		JSONObject colmodel = JSONObject.fromString(tableModel);
		out.println(colmodel);
	}
		else if(action.equals("pcsLoss_getData.balpcs")){
			try {

				String flid = request.getParameter("flid");
				String date = request.getParameter("fromdate");
				String Todate = request.getParameter("todate");
				String shiftid = request.getParameter("shiftid");
				String shift = request.getParameter("shift");
				
				if(shift.equals("3")){
					CommonFunctions.debugMsg("Todate "+Todate);
					String dt="";
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					Calendar c = Calendar.getInstance();
					java.util.Date dd= sdf.parse(Todate);
					c.setTime(dd);
					c.add(Calendar.DAY_OF_MONTH, 1);
					dt=sdf.format(c.getTime());
					Todate=dt;
					CommonFunctions.debugMsg("Todate "+Todate);
				}
				List<String[]> lossgrid = pcsEntryService.getPcsLossCaptureGrid(flid, date,Todate ,shiftid);

				PrintWriter out = response.getWriter();

				JSONObject lossgridmod = UIUtils.convertToJqGridTableObject(lossgrid, request, 1,0);
					out.println(lossgridmod);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		

		
		
		else if( action.equals("getTotalResult.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("getTotalResult.balpcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				//String mchId = request.getParameter("mchId");	
				String mchId = "";
				CommonFunctions.debugMsg("entryDate"+ entryDate);
				List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
				CommonFunctions.debugMsg(pcsResultList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsResultList,request,0,0);
  			 	CommonFunctions.debugMsg("pcsResultData"+pcsResultData);
				httpSession.removeAttribute("PcsEntryServletPcsResultList");
				httpSession.setAttribute("PcsEntryServletPcsResultList",pcsResultList);
  			 	
  			 	out.println(pcsResultData);

			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}	
		else if( action.equals("getLineRejectionLoss.balpcs") )
		{
			PrintWriter out = response.getWriter();
			String lossId = pcsEntryService.getLineRejectionLoss();			
			JSONObject lossData =new JSONObject();
			if (UIUtils.isValidKeyId(lossId))
				lossData.put("loss", lossId);
			else
				lossData.put("loss", "");
			
			out.print(lossData);
		}
	
		
		else if( action.equals("pcsEmployeeGrid_getCol.balpcs") )
		{
			CommonFunctions.debugMsg("pcsEmployeeGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsColModel", "pcsEmployeecolModelNew"));
		}
			
		
		else if( action.equals("pcsEmployeeGrid_getData.balpcs") )
		{
			try
			{	
				CommonFunctions.debugMsg("pcsEmployeeGrid_getData.balpcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");	
				CommonFunctions.debugMsg("entryDate"+ pldetailsid);
				List<String []> pcsEmpList  = pcsEntryService.getEmployeeGrid (pldetailsid);
				CommonFunctions.debugMsg(pcsEmpList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsEmpList,request,0,0);
  			 	CommonFunctions.debugMsg(pcsResultData);
  			 	out.println(pcsResultData);
			}catch(Exception e)
			{
				CommonFunctions.debugMsg(e.getMessage());
			}
		}	
		
		
		else if( action.equals("pcsentry_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save " );
			savePcsEntry(request,response);					
		}
		else if( action.equals("pcsentrynew_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save " );
			savePcsEntryNew(request,response);					
		}
		
		else if( action.equals("noPlan_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside no plan save" );
			String from=request.getParameter("from");
			CommonFunctions.debugMsg("from view:"+from);
			httpSession.removeAttribute("from");
			httpSession.setAttribute("from", from);
			noPlanSave(request,response);					
		}
		

		else if( action.equals("pcsLossEntry_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save " );
			savePcsLossEntry(request,response);					
		}
		else if( action.equals("pcsLossEntryNew_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save " );
			savePcsLossEntryNew(request,response);					
		}
	
		else if( action.equals("frmPcsLoss_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save frmPcsLoss_save" );
			savePcsLossCapture(request,response);					
		}
		else if( action.equals("frmPcsLoss_productionsave.balpcs")) { 
			CommonFunctions.debugMsg(" inside save frmPcsLossProduction_save" );
			savePcsLossProductionCapture(request,response);					
		} 
		else if( action.equals("frmPcsLoss_rejectionsave.balpcs")) { 
			CommonFunctions.debugMsg(" inside save frmPcsLossrejection_save" );
			savePcsLossRejectiondata(request,response);					
		}
		else if( action.equals("frmPcsLoss_rejectionDelete.balpcs")) { 
			CommonFunctions.debugMsg(" inside Delete frmPcsLoss_rejectionDelete.balpcs" );
			deletePcsLossRejectiondata(request,response);					
		}
		else if( action.equals("frmPcsLoss_productionDelete.balpcs")) { 
			CommonFunctions.debugMsg(" inside Delete frmPcsLoss_productionDelete.balpcs" );
			deletePcsLossProductiondata(request,response);					
		}
		else if( action.equals("removeShiftNoplan.balpcs")) { 
			CommonFunctions.debugMsg(" inside Delete frmPcsLoss_productionDelete.balpcs" );
			removeShiftNoplan(request,response);					
		}

		else if( action.equals("pcsEntry_delete.balpcs"))
		{
			CommonFunctions.debugMsg(" inside delete pcsentry delete " );			
			deletePcsEntry(request,response);
		}		

		else if( action.equals("pcsEmployee_save.balpcs")) { 
			CommonFunctions.debugMsg(" inside save " );
			savePcsEmployee(request,response);					
		}

		else if( action.equals("pcsEmployee_delete.balpcs"))  
		{
			CommonFunctions.debugMsg(" inside delete pcsentry delete " );			
			deletePcsEmployee(request,response);
		}	
		else if( action.equals("pcsLossEntry_delete.balpcs"))
		{
			CommonFunctions.debugMsg(" inside delete pcs Loss entry delete " );			
			deletePcsLossEntry(request,response);
		}		
		
		else if( action.equals("pcsLossEntryBajaj_delete.balpcs"))
		{
			CommonFunctions.debugMsg(" inside pcsLossEntryBajaj_delete.balpcs " );			
			deletePcsLossEntryBajaj(request,response);
		}

		else if( action.equals("pcsLossEntryITC_delete.balpcs"))
		{
			CommonFunctions.debugMsg(" inside pcsLossEntryITC_delete " );			
			deletePcsLossEntryITC(request,response);
		}
    
	}
    
    private void removeShiftNoplan(HttpServletRequest request,
			HttpServletResponse response)  throws BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String mstkeyId = request.getParameter("mstkeyId");
		String dtlkeyId = request.getParameter("dtlkeyId");
		String cellId = request.getParameter("cellId");
		String entrydate = request.getParameter("entrydate");
		try{
			if(UIUtils.isValidKeyId(mstkeyId)){
				
				pcsEntryService.removeShiftNoplan(mstkeyId,dtlkeyId,cellId,entrydate);
				String msgPropertyIdnt = "success-delete";
			
				JSONObject err = new JSONObject();
				//String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData","Shift No plan removed successfully");
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonFunctions.debugMsg("Delete End");
			
		}
		 catch (BusinessApplicationExceptions b)
		  {
			 CommonFunctions.debugMsg("Business Exception: "+b);
			
			   CommonFunctions.debugMsg("Exception:---------------- "+b);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
		   
		  }
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Exception: "+e);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
		
    	
	}

	private void deletePcsLossProductiondata(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions,Exception {
		CommonFunctions.debugMsg("Servlet Remove:");
		// TODO Auto-generated method stub
		
		CommonFunctions.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		String dtlkeyId = request.getParameter("dtlkeyId");
		String cellId = request.getParameter("cellId");
		String entrydate = request.getParameter("entrydate");
		CommonFunctions.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				pcsEntryService.removeShiftNoplan(dtlkeyId,keyid,cellId,entrydate);
			//	pcsEntryService.deleteProductiondata(keyid);
				String msgPropertyIdnt = "success-delete";
				//System.out.println("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonFunctions.debugMsg("Delete End");
			
		}
		 catch (BusinessApplicationExceptions b)
		  {
			 CommonFunctions.debugMsg("Business Exception: "+b);
			
			   CommonFunctions.debugMsg("Exception:---------------- "+b);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
		   
		  }
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Exception: "+e);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
		
		
	}

    
	private void deletePcsLossRejectiondata(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions,Exception {
		CommonFunctions.debugMsg("Servlet Remove:");
		// TODO Auto-generated method stub
		
		CommonFunctions.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonFunctions.debugMsg("KEYID: "+keyid);
		try{
			if(UIUtils.isValidKeyId(keyid)){
				
				//paramConfigService.DeleteParamRow(keyid);
				pcsEntryService.deleteRejectiondata(keyid);
				String msgPropertyIdnt = "success-delete";
				//System.out.println("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonFunctions.debugMsg("Delete End");
			
		}
		 catch (BusinessApplicationExceptions b)
		  {
			 CommonFunctions.debugMsg("Business Exception: "+b);
			
			   CommonFunctions.debugMsg("Exception:---------------- "+b);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
		   
		  }
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Exception: "+e);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
		
		
	}
	///
	
	private void saveJhDetailInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*
		 *  HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	SapTlSparesreplaced sapTlSparesreplaced = new SapTlSparesreplaced();
		try{
			String spareSAPData =request.getParameter("spareSAPData");
			System.out.println("spare date"+ spareSAPData);
			String refDocId = request.getParameter("refDocId");
			CommonFunctions.debugMsg(refDocId+"Ref doc id in servlet");
			String existDocNumber = request.getParameter("existDocNumber");
			if(!UIUtils.isValidKeyId(refDocId)){
				sapTlSparesreplaced.setRefDocId("-");
			}
			else
				sapTlSparesreplaced.setRefDocId(refDocId);
			if(!UIUtils.isValidKeyId(existDocNumber)){
				sapTlSparesreplaced.setExistDocNumber("-");
				}
			else
				sapTlSparesreplaced.setExistDocNumber(existDocNumber);
			List<SapTlSparesreplaced> sapTlSparesreplacedList  = null;
			SapTlSparesreplaced newSapTlSparesreplaced = new SapTlSparesreplaced();
			SapTlSparesreplaced existSapTlSparesreplaced = new SapTlSparesreplaced();
			BDFormBean bdFormBean = new BDFormBean();
			JSONArray spareReplacedjson = null;
			//JSONObject spareSAPData1= new JSONObject(spareSAPData);
		    // String	spareSAPData = sparSAPData.toString();
			boolean insert=false;
			newSapTlSparesreplaced.setExistDocNumber(existDocNumber);
			if(!UIUtils.isValidKeyId(refDocId)){
				newSapTlSparesreplaced.setRefDocId("-"); 
			}
			else
				newSapTlSparesreplaced.setRefDocId(refDocId);
			newSapTlSparesreplaced.setSspmCreatedby(user.getUsrm_ccno());
		  if(UIUtils.isValidKeyId(spareSAPData)){
			CommonFunctions.debugMsg("in if spares Replaced");
			spareReplacedjson = JSONArray.fromString(spareSAPData);
			CommonFunctions.debugMsg("test ine servelet"+sapTlSparesreplaced.getSspmDocnumber()+   "     "+sapTlSparesreplacedList);
			sapTlSparesreplacedList=(List<SapTlSparesreplaced>)UIUtils.convertJSONArrToList(newSapTlSparesreplaced, spareReplacedjson);
			CommonFunctions.debugMsg("sapTlSparesreplacedList.size()s "+sapTlSparesreplacedList.size()+"  "+sapTlSparesreplacedList);
			if(sapTlSparesreplacedList.size()>=0){
			CommonFunctions.debugMsg("insidesapsapres");
			for(int i=0;i<sapTlSparesreplacedList.size();i++) {
				CommonFunctions.debugMsg("sapTlSparesreplaced.getRefDocId()"+sapTlSparesreplaced.getRefDocId());
				sapTlSparesreplacedList.get(i).setSspmDocnumber(sapTlSparesreplaced.getRefDocId());
				//sapTlSparesreplacedList.get(i).setSspmExistDocNumber(sapTlSparesreplaced.getExistDocNumber()));
			}
			newSapTlSparesreplaced.setSparesReplaced(sapTlSparesreplacedList);
			CommonFunctions.debugMsg(sapTlSparesreplacedList.size()+"  spareSAPData  "+spareSAPData);
			//existPlmTlStandards = (PlmTlStandards)httpSession.getAttribute("plmTlStandards"+newPlmTlStandards.getPmsdKeyid());
			} 
		}else{
			newSapTlSparesreplaced.setSparesReplaced(null);
		} 
		 insert=true;
		 
		 existSapTlSparesreplaced  = sapInfoService.createSapSpareReplaced(newSapTlSparesreplaced,existSapTlSparesreplaced);
	 
		    String msg;
			msg="Data Saved Successfully";
		/*else
			msg="Data Updated Successfully";*/
	/*	JSONObject successData = new JSONObject();
		successData.put("msg",msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		//returnData.put("Docnumber",existSapTlSparesreplaced.getExistDocNumber());
		out.print(returnData.toString());

}catch(Exception e){
	e.printStackTrace();
	out.print(e.getMessage());
 }
}

		 */
		 

		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	BAL_PcsTlJhlossDetail pcsTljhlossDetail = new BAL_PcsTlJhlossDetail();
		try{
			String jhDetails =request.getParameter("JhDetails");
			System.out.println("spare date"+ jhDetails);
			String refDocId ="MCh1000" ;//request.getParameter("refDocId");
			CommonFunctions.debugMsg(refDocId+"Ref doc id in servlet");
			//String existDocNumber = request.getParameter("existDocNumber");
			if(!UIUtils.isValidKeyId(refDocId)){
				pcsTljhlossDetail.setJhlossId("-");
			}
			else
				pcsTljhlossDetail.setJhlossId(refDocId);
		
			List<BAL_PcsTlJhlossDetail> pcsTljhlossDetailList  = null;
			BAL_PcsTlJhlossDetail newPcsTlJhlossDetail = new BAL_PcsTlJhlossDetail();
			BAL_PcsTlJhlossDetail existPcsTlJhlossDetail = new BAL_PcsTlJhlossDetail();
			JSONArray jhdetailsjson = null;
			
			boolean insert=false;
			if(!UIUtils.isValidKeyId(refDocId)){
				newPcsTlJhlossDetail.setJhlossId("-"); 
			}
			else
				newPcsTlJhlossDetail.setJhlossId(refDocId);
			
			newPcsTlJhlossDetail.setJhlossCreatedBy(user.getUsrm_ccno());
		  if(UIUtils.isValidKeyId(jhDetails)){
			CommonFunctions.debugMsg("in if  jh details "+jhDetails);
			
				jhdetailsjson = JSONArray.fromString(jhDetails);
				CommonFunctions.debugMsg("in if  jh details "+newPcsTlJhlossDetail);
			pcsTljhlossDetailList=(List<BAL_PcsTlJhlossDetail>)UIUtils.convertJSONArrToList(newPcsTlJhlossDetail, jhdetailsjson);
			CommonFunctions.debugMsg("size of Array   "+pcsTljhlossDetailList.size()  +"  " + pcsTljhlossDetailList );
			if(pcsTljhlossDetailList.size()>0){	
				CommonFunctions.debugMsg("size of Array in side if  "+pcsTljhlossDetailList.size());

			for(int i=0;i<pcsTljhlossDetailList.size();i++) {
				CommonFunctions.debugMsg("size of Array in side For  "+pcsTljhlossDetailList.size());
				//pcsTljhlossDetailList.get(i).setJhlossId(pcsTljhlossDetail.getlossId());
				
			}
			newPcsTlJhlossDetail.setjhDetails(pcsTljhlossDetailList);
				} 
		}else{
			CommonFunctions.debugMsg("size of Array in side else  "+pcsTljhlossDetailList.size());
			newPcsTlJhlossDetail.setjhDetails(pcsTljhlossDetailList);
		} 
		 insert=true;
		 
		 existPcsTlJhlossDetail  = pcsEntryService.createJhLossDetails(newPcsTlJhlossDetail,existPcsTlJhlossDetail);
	 
		    String msg;
		    CommonFunctions.debugMsg("......above the data saved "+newPcsTlJhlossDetail.getJhlossLubtime() +"    "+   existPcsTlJhlossDetail);
			msg="Data Saved Successfully";
		/*else
			msg="Data Updated Successfully";*/
		JSONObject successData = new JSONObject();
		successData.put("msg",msg);
		JSONObject returnData = new JSONObject();
		returnData.put("successData", successData);
		returnData.put("formClear", false);
		
		out.print(returnData.toString());

}catch(Exception e){
	e.printStackTrace();
	out.print(e.getMessage());
 }
}
	
	///

	private void updateOtherLoss(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		PrintWriter out = response.getWriter();
		try{
			PcsTlOtherlossentry pcsTlOtherlossentry= new PcsTlOtherlossentry();
			pcsTlOtherlossentry = (PcsTlOtherlossentry) UIUtils.setBeanProperties((Object) pcsTlOtherlossentry,request);
			String Otherloss = request.getParameter("selectedrowIDs");
			CommonFunctions.debugMsg("Otherlosslist : "+Otherloss);
			String update="";
			List<PcsTlOtherlossentry> otherLossList = null;   
			JSONArray otherLossJson = null;
			if (UIUtils.isValidKeyId(Otherloss)) 
			{
				otherLossJson  = JSONArray.fromString(Otherloss);
				otherLossList = (List<PcsTlOtherlossentry>) UIUtils.convertJSONArrToList(pcsTlOtherlossentry,otherLossJson );
				CommonFunctions.debugMsg("otherLossList.size() : "+otherLossList.size());
				CommonFunctions.debugMsg("getOlseLossvalue  "+otherLossList.get(0).getOlseLossvalue()+"getOlseKeyid : "+otherLossList.get(0).getOlseKeyid());
			}
			update=pcsEntryService.updateLossVal(otherLossList);
			
			update=" Data Updated succesfully";
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
	    	successData.put("msg", update);
    		returnData.put("formClear",false);
	    	returnData.put("successData", successData);
			out.print(returnData.toString());
		}catch(Exception e){
			JSONObject err = new JSONObject();
			err.put("tpmException","Data Not Updated");
			out.print(err.toString());
			e.printStackTrace();
		}
	}

	private void saveOtherLossFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String file;
		try{
			String elementId=request.getParameter("hdnelementId");
			String fileSave=request.getParameter("fileSave");
			HttpSession httpSession = request.getSession(false);
			AdmTlUsermst userid = UIUtils.getLoginUser(request);
			String excelFileName1= (String) httpSession.getAttribute(AdmUploadExcelServlet_filename);
			String excelFileName=(realPath+""+excelFileName1);
			CommonFunctions.debugMsg("excelFileName   "+excelFileName);
			PcsTlOtherlossentry pcsTlOtherlossentry= new PcsTlOtherlossentry();
			pcsTlOtherlossentry =(PcsTlOtherlossentry) UIUtils.setBeanProperties((Object)pcsTlOtherlossentry,request);
			pcsTlOtherlossentry.setOlseElementid(elementId);
			if("Y".equals(fileSave))
				pcsTlOtherlossentry.setExcelName(excelFileName1);
			else
				pcsTlOtherlossentry.setExcelName("");
			String savemsg;	
			CommonFunctions.debugMsg("pcsTlOtherlossentry  "+pcsTlOtherlossentry.getOlseFlid());
			pcsTlOtherlossentry.setOlseCreatedby(userid.getUsrm_ccno());
			file = pcsEntryService.populateTempTable(excelFileName,pcsTlOtherlossentry);
			if(file.equals("No"))
				savemsg=" File Not Supported";
			else
				savemsg=" Data Uploaded succesfully";
			httpSession.removeAttribute(AdmUploadExcelServlet_filename);
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
	    	successData.put("msg", savemsg);
    		returnData.put("formClear",false);
	    	returnData.put("successData", successData);
			out.print(returnData.toString());
		}catch(ValidationExceptions e){
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsOtherLoss");
			e.printStackTrace();
			out.print(errMessage.toString());
		}catch(Exception e){
			CommonFunctions.debugMsg("Inside Exceptions "+e.getMessage());
			JSONObject err = new JSONObject();
			String msg="Data Not Supported";
			if(e.toString().contains("UK_OTHERLOSSEXCELDATA"))
				msg="Data Already Exists";
			err.put("tpmException",msg);
			out.print(err.toString());
			e.printStackTrace();
		}
	}

	private void deletePcsEntry(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String Plmasterid = request.getParameter("Plmasterid");
    	String pldetailsId = request.getParameter("pldetailsId");
    	//String tableName = request.getParameter("tableName");
    	String machKeyId = request.getParameter("machId");
    	String entryDate = request.getParameter("entryDate");
    	String sectId = request.getParameter("sectId");
    	String workOrderNo = request.getParameter("workOrderNo");
    	CommonFunctions.debugMsg("machKeyId=="+machKeyId);
    	CommonFunctions.debugMsg("sectId=="+sectId);
    	
    	if( httpSession != null && user != null)
    	{

    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

	    	CommonFunctions.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			successData.put("keyId", pldetailsId);
			JSONObject returnData = new JSONObject();
			
			try{

	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_DelMode"+",");
	    		

				String deleteResult;
				deleteResult = pcsEntryService.deletePcsEntry(sectId, Plmasterid, pldetailsId,workOrderNo,machKeyId,entryDate);					
				//httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);
				
				CommonFunctions.debugMsg("deleteResult"+ deleteResult);
				if (deleteResult.equals("Exists"))
					throw new BusinessApplicationExceptions("view_LossExists"+",");
					//successData.put("msg","Loss Link Exists Can not Delete");
				

				httpSession.setAttribute("deleteResult", deleteResult);
								
				//returnData.put("successData", successData);
				returnData.put("formClear", false);
				returnData.put("keyId", pldetailsId);
				returnData.put("isClear", "Y");
				returnData.put("type", "D-P");
				returnData.put("msg","Data Deleted Successfully");
				returnData.put("status", deleteResult);
				

				//set report times							
				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(machKeyId, sectId, Plmasterid, entryDate);				
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));
				
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
		
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());
	
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
    	}		
	}
	
	private JSONObject getAllTimeForMachine(String cellId, String sectId, String plmasterId, String entryDate) throws Exception {
		
		
		List<String[]> allTime   = pcsEntryService.getAllTimes(cellId, plmasterId, sectId,entryDate);
		
		String asstTime = null;
		String prodTime= null;
		String lossTime= null;
		String unreportTime= null;
		
		//CommonFunctions.debugMsg("allTime.size"+allTime.size());
		
		if (allTime.size()>=0) {
			
			asstTime=allTime.get(0)[0];
			prodTime=allTime.get(0)[1];
			lossTime=allTime.get(0)[2];
			unreportTime=allTime.get(0)[3];
			
			asstTime = setFormatForTime(asstTime);
			prodTime =  setFormatForTime(prodTime);
			lossTime =setFormatForTime(lossTime);
			unreportTime = setFormatForTime(unreportTime);					
		}
		JSONObject plmData =new JSONObject();
		plmData.put("asstTime", asstTime);
		plmData.put("prodTime", prodTime);
		plmData.put("lossTime", lossTime);
		plmData.put("unreportTime", unreportTime);
		
		return plmData; 
	
	}

	private String setFormatForTime(String time) {
		if(UIUtils.isValidKeyId(time))
		{
			if(time.indexOf("0") == 0 || time.indexOf("-")== 0 )
				time = "0";
			else
			{
				NumberFormat df = DecimalFormat.getInstance();
	    		df.setMinimumFractionDigits(2);
	    		df.setMaximumFractionDigits(2);
	    		df.setRoundingMode(RoundingMode.UP);
	    		time = df.format(Float.parseFloat(time));
	    		CommonFunctions.debugMsg("time==="+time);
			}
		}
		return time;
	}
	
	private void deletePcsLossEntry(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String PlrkKeyid = request.getParameter("PlrkKeyid");    	
    	String Pldetailsid = request.getParameter("Pldetailsid");
    	String sectId = request.getParameter("cellId");
    	String lossId = request.getParameter("lossId");
    	String lossVal = request.getParameter("lossVal");
    	
    	if( httpSession != null && user != null)
    	{
    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

    		
	    	CommonFunctions.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			
			JSONObject returnData = new JSONObject();
			try{

	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_DelMode"+",");

				String deleteResult;
				deleteResult = pcsEntryService.deletePcsLossEntry(PlrkKeyid,Pldetailsid, sectId, lossId, lossVal);					
				//httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);			
				httpSession.setAttribute("deleteResult", deleteResult);		
				
				CommonFunctions.debugMsg("deleteResult"+deleteResult);
				
				if (deleteResult.equals("Exists"))
						successData.put("msg","Loss Link Exists Can not Delete");
				
				if (deleteResult.equals("LockDelete")) {
					successData.put("msg","Delete is Locked");					
				}
				
				successData.put("keyId", PlrkKeyid);
				returnData.put("keyId", PlrkKeyid);
				returnData.put("type", "D-L");
				returnData.put("successData", successData);								
				
				//set report times 
				String machineId =  request.getParameter("machineId");				
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("entryDate");

				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(machineId, sectId, plmasterId, entryDate);				
				
				CommonFunctions.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));				
				returnData.put("formClear", false);
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}
	
	private void deletePcsLossEntryBajaj(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	BAL_PcsEntryBean pcsEntryBean = new BAL_PcsEntryBean();
    	String PlrkKeyid = request.getParameter("PlrkKeyid");    	
    	String Pldetailsid = request.getParameter("Pldetailsid");
    	String cellId = request.getParameter("cellId");
    	String lossId = request.getParameter("lossId");
    	String lossVal = request.getParameter("lossVal");
    	String entryDate = request.getParameter("entryDate");
    	String PlosKeyid = request.getParameter("plosKeyid");
    	if( httpSession != null && user != null)
    	{
    		
    		//(PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		
    		pcsEntryBean =(BAL_PcsEntryBean) UIUtils.setBeanProperties((Object)pcsEntryBean,request);
    		
	    	CommonFunctions.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			
			JSONObject returnData = new JSONObject();
			try{

	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_DelMode"+",");

				String deleteResult;
				deleteResult = pcsEntryService.deletePcsLossEntryBajaj(PlosKeyid,PlrkKeyid,Pldetailsid, cellId, lossId, lossVal,entryDate);					
				//httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);			
				httpSession.setAttribute("deleteResult", deleteResult);		
				
				CommonFunctions.debugMsg("deleteResult"+deleteResult);
				
				if (deleteResult.equals("Exists"))
						successData.put("msg","Loss Link Exists Can not Delete");
				
				if (deleteResult.equals("LockDelete")) {
					successData.put("msg","Delete is Locked");					
				}
				
				
				String machineId = request.getParameter("machineId");
				String factId = request.getParameter("factId");
		//		String cellId = request.getParameter("cellId");
		//		String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");
				
				CommonFunctions.debugMsg("..factId="+factId+"..entryDate="+entryDate+"..entryDate="+entryDate
						+"..cellIdcellId="+entryDate+"..cellId="+cellId+"..machineId="+machineId+"..Pldetailsid="+Pldetailsid);
				/*String[] retVal = getColDatas(factId, shift, entryDate,cellId , cellId, machineId, Pldetailsid);

				returnData.put("colHeader",  retVal[1]);
				returnData.put("colValues", retVal[0]);
*/
				returnData.put("msg", successData.get("msg"));
				returnData.put("keyId", PlrkKeyid);
				returnData.put("detailId", Pldetailsid);
				returnData.put("lossId", lossId);
				returnData.put("type", "D-L");
				//returnData.put("successData", successData);								
				
				//set report times 
								
				String plmasterId = request.getParameter("plmasterId");
				

			/*	JSONObject plmData =new JSONObject();
			//	plmData= getAllTimeForMachine(machineId, sectId, plmasterId, entryDate);				
				
				CommonFunctions.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));				

				*/
				returnData.put("formClear", false);
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}
	

	private void deletePcsLossEntryITC(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String PlosKeyid = request.getParameter("PlosKeyid");
    	String PlrkKeyid = request.getParameter("PlrkKeyid");    	
    	String Pldetailsid = request.getParameter("Pldetailsid");
    	String sectId = request.getParameter("sectId");
    	String lossId = request.getParameter("lossId");
    	String lossVal = request.getParameter("lossVal");
    	
    	if( httpSession != null && user != null)
    	{
    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

    		
	    	CommonFunctions.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			
			JSONObject returnData = new JSONObject();
			try{

	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_DelMode"+",");

				String deleteResult;
				deleteResult = pcsEntryService.deletePcsLossEntryITC(PlosKeyid, PlrkKeyid,Pldetailsid, sectId, lossId, lossVal);					
				//httpSession.setAttribute(existWomTlOthercostactual.getOtcdWoid(), existWomTlOthercostactual);			
				httpSession.setAttribute("deleteResult", deleteResult);		
				
				CommonFunctions.debugMsg("deleteResult"+deleteResult);
				
				if (deleteResult.equals("Exists"))
						successData.put("msg","Loss Link Exists Can not Delete");
				
				if (deleteResult.equals("LockDelete")) {
					successData.put("msg","Delete is Locked");					
				}
				
				
				String machineId = request.getParameter("machineId");
				String factId = request.getParameter("factId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");
				
				CommonFunctions.debugMsg("..factId="+factId+"..entryDate="+entryDate+"..entryDate="+entryDate
						+"..cellIdcellId="+entryDate+"..sectId="+sectId+"..machineId="+machineId+"..Pldetailsid="+Pldetailsid);
				String[] retVal = getColDatas(factId, shift, entryDate,cellId , sectId, machineId, Pldetailsid);

				returnData.put("colHeader",  retVal[1]);
				returnData.put("colValues", retVal[0]);

				returnData.put("msg", successData.get("msg"));
				returnData.put("keyId", PlrkKeyid);
				returnData.put("detailId", Pldetailsid);
				returnData.put("lossId", lossId);
				returnData.put("type", "D-L");
				//returnData.put("successData", successData);								
				
				//set report times 
								
				String plmasterId = request.getParameter("plmasterId");
				

				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(machineId, sectId, plmasterId, entryDate);				
				
				CommonFunctions.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));				

				
				returnData.put("formClear", false);
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}

	
	private JSONObject getTableModel(List<String[]> headers,List<String[]> headers2)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = headers.get(0);		
		int headerFlag=0;
		String[] mchArr = new String[headers2.size()];
		
		
		for(String[] row : headers2)
		{
			mchArr[headerFlag] = row[3];			
			headerFlag++;
		}
		//CommonFunctions.debugMsg(colHeader.length+mchArr.length);
		String[] colHeader2 = new String[colHeader.length+mchArr.length];
		//CommonFunctions.debugMsg("lENGTH : "+colHeader2.length);
		int lossCount = 0;
		for(int k=0;k<colHeader2.length;k++)
		{
			if(k < colHeader.length)
				colHeader2[k] = colHeader[k];
			else
			{
				colHeader2[k] = mchArr[lossCount];
				lossCount++;
			}
			//CommonFunctions.debugMsg(k + "- "+colHeader2[k]);	
		}
		jqGridTableModel.getRowHeaders().add(colHeader2);
		for(int i =0; i < colHeader2.length; i++)
		{
			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader2[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(colHeader2[i].replaceAll(" ", "")+ i);
			jqGridColModel.setEditable(false);
			
			if(i==0 )
			{
				//jqGridColModel.setHidden(true);
				jqGridColModel.setKey(true);
			
			}
			if(i<10)
			{
				jqGridColModel.setHidden(true);
			}
			if(i>12 && i<colHeader.length)
			{
				jqGridColModel.setHidden(true);
			}
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			//jqGridTableModel.setTableHeight(0);
		}
		
		 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		 return tableModel;
	}
	public static JSONObject fillPCSGrid(List<String[]> dataArrayList,List<String[]> dataArrayList2,HttpServletRequest request)
	{
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 200;
		int totalRecords=0;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr);
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		
		CommonFunctions.debugMsg(" totalRecords " + totalRecords);
		
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
	public static JSONObject fillPCS(List<String[]> dataArrayList,List<String[]> dataArrayList2,HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession(false);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		//JqGridColModel jqGridColModel = new JqGridColModel();
		String [] colHeader = dataArrayList.get(0);
		CommonFunctions.debugMsg("colHeader size"+colHeader.length);
		String[] mchArr = new String[dataArrayList2.size()];		
		String[] colHeader2 = new String[colHeader.length+(mchArr.length+1)];
		CommonFunctions.debugMsg("colHeader2 size"+colHeader2.length);
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int totRow = 0;
		for(String[] dRow : dataArrayList)
		{
				if(dRow[19].equals("Y"))
				{
					if(!dRow[21].equals("S"))
						totRow +=1;
				}
			
		}
		totRow += 1;
		int rows = 200;
		int totalRecords=0;
		if( rowsStr != null)
			rows = Integer.parseInt(rowsStr)-totRow;
		
		int page = 1;
		if( pageStr != null)
			page = Integer.parseInt(pageStr);
		
		JSONObject tableDataObject = new JSONObject();
		int x = (int) (Math.ceil(dataArrayList.size()/rows)==0?1:Math.ceil(dataArrayList.size()/rows));
		
		//tableDataObject.put("page", page); //current page
		//tableDataObject.put("total",Math.ceil(dataArrayList.size()/rows)==0?1:Math.ceil(dataArrayList.size()/rows)); // total page
		//tableDataObject.put("records", (dataArrayList.size())); //total records
		//tableDataObject.put("total",Math.ceil(totalRecords/rows)==0?1:(Math.ceil(totalRecords/rows)+1)); // total page
		tableDataObject.put("total",1); // total page
		//if( page == 1)
		CommonFunctions.debugMsg("dataArrayList.size()"+dataArrayList.size());
		//tableDataObject.put("records", totalRecords); //total records
		tableDataObject.put("records", (dataArrayList.size()+4)); //total records
		tableDataObject.put("pageRowCount", 150); 
		
		JSONArray rowArr = new JSONArray(); 	    	
  	   
       
		int lossCount = colHeader.length;
		
		int rowId = 0;
		String[][] pcsDatas = populatePcsDispData(dataArrayList,dataArrayList2);
		int chkFlag = -1;
		//CommonFunctions.debugMsg("pcsDatas size"+pcsDatas.length);
		for (int headers=0; headers <= pcsDatas.length-1; headers++) {
			for (int h = 0; h <=pcsDatas[headers].length-1; h++) {
				if(headers==0)
				{
					if(pcsDatas[headers][h].equals(PCSConstants.MCHNO))
						chkFlag = 0;
					if(chkFlag >= 0 && !pcsDatas[headers][h].equals(PCSConstants.TOT))
					{
						//CommonFunctions.debugMsg(pcsDatas[headers][h] + " -> "+chkFlag);
						chkFlag=chkFlag+3;
					}
				}
			}
		}
		if(chkFlag >=0)
			chkFlag = chkFlag-3;
		//CommonFunctions.debugMsg(colHeader.length+(mchArr.length+1)+" : colHeader.length+(mchArr.length+1 ");
		//CommonFunctions.debugMsg((colHeader.length+(mchArr.length+1))+" : (colHeader.length+(mchArr.length+1)) ");
		String[] colHeader3 = new String[(colHeader.length+(mchArr.length+1))+chkFlag];
		CommonFunctions.debugMsg(colHeader3.length+" : Length ");
		for (int i=0; i <= pcsDatas.length-1; i++) {
			JSONObject rowObj =new JSONObject();
			JSONArray cell=new JSONArray();
			CommonFunctions.debugMsg("lENGTH OF ARR : "+pcsDatas[i].length);
			int colFlag = pcsDatas[i].length-1;
			if(chkFlag >=0)
				colFlag += chkFlag;
			for (int j = 0; j <=colFlag; j++) {
			    //CommonFunctions.debugMsg( i + " : " +j + " ---->"+pcsDatas[i][j]);
			    JqGridColModel jqGridColModel = new JqGridColModel();
			    if(i==0)
			    {		
			    	if(j<=pcsDatas[i].length-1)
			    		colHeader3[j] = pcsDatas[i][j];
			    	else
			    		colHeader3[j] = "";
				    //CommonFunctions.debugMsg( i + " : " +pcsDatas[i][j] + " ---->"+j);
				    CommonFunctions.debugMsg(colHeader3[j] + " : "+j);
				    jqGridColModel.setIndex(colHeader3[j].replaceAll(" ", "")+ j);
		        	jqGridColModel.setName(colHeader3[j].replaceAll(" ", "")+ j);
		        	jqGridColModel.setEditable(false);	
			    		
	        			if(j<10 || (j>12&&j<23) || j>pcsDatas[i].length-1)
	        				jqGridColModel.setHidden(true);
	        			
	        			else if(j>=23) {
	        				jqGridColModel.setWidth(90);
	        				jqGridColModel.setAlign("right");
	        				jqGridColModel.setEditable(false);
	        			}
	        			else if(j==10) {
	        				jqGridColModel.setWidth(25);
	        				jqGridColModel.setAlign("center");
	        				
	        			}	        			
	        			else if(j==11)
	        				jqGridColModel.setWidth(210);
	        			else if(j==12)
	        				jqGridColModel.setWidth(25);
	        			
	        			jqGridTableModel.getColModel().add(jqGridColModel);
	        				        			
	        	 	}			    	
			    	else 
			    	{	
				    	PCSConstants pcsConstants = new PCSConstants(dataArrayList, 9);
				    	if (i>=5 && i < pcsDatas.length && j == pcsDatas[i].length-1) {  
				    	//	CommonFunctions.debugMsg("Total Column dslksdllsd");
				    		Float total = (float) 0;			    		
				    		Float cnt = (float) 0;
				    		for (int k=23; k<=pcsDatas[i].length-2; k++) {
				    			cnt +=1;
				    			try
                                {
				    				if (UIUtils.isValidKeyId(pcsDatas[i][k]))
				    					total = total + Float.parseFloat( pcsDatas[i][k]);
				    				
                                }
				    			catch(NumberFormatException nme) {
				    				CommonFunctions.debugMsg(pcsDatas[i][k] + " is not a valid decimal number");
				    			}
				    		}
				    		
				    		List<String []> pcsResultList  = (List<String []>)httpSession.getAttribute("PcsEntryServletPcsResultList");
				    		
				    		/*CommonFunctions.debugMsg("pcsResultList.get(6)[2].toString()"+pcsResultList.get(6)[2].toString());
				    		CommonFunctions.debugMsg("pcsResultList.get(7)[2].toString()"+pcsResultList.get(7)[2].toString());
				    		CommonFunctions.debugMsg("pcsResultList.get(8)[2].toString()"+pcsResultList.get(8)[2].toString());
				    		CommonFunctions.debugMsg("pcsResultList.get(9)[2].toString()"+pcsResultList.get(9)[2].toString());
				    		
				    		CommonFunctions.debugMsg("i="+i);
				    		CommonFunctions.debugMsg("pcsConstants.rownum_OEE"+pcsConstants.rownum_OEE);
				    		*/
				    	/*	if (i == pcsConstants.rownum_OEE || i == pcsConstants.rownum_ROA 
				    				|| i == pcsConstants.rownum_ROP || i == pcsConstants.rownum_ROQ) { 
				    			total = total /cnt;				    			
				    		}
				        */
				    		if (i == pcsConstants.rownum_ROA) {
				    			CommonFunctions.debugMsg("rownum_ROA==="+pcsConstants.rownum_ROA);
				    			if (pcsResultList.get(6)[2] !=null &&  UIUtils.isValidKeyId(pcsResultList.get(6)[2].toString()))
				    				total  = Float.parseFloat(pcsResultList.get(6)[2].toString());
				    		}
				    		else if (i == pcsConstants.rownum_ROP) {
				    			if (pcsResultList.get(7)[2] !=null &&  UIUtils.isValidKeyId(pcsResultList.get(7)[2].toString()))
				    				total  = Float.parseFloat(pcsResultList.get(7)[2].toString());
				    		}
				    		else if (i == pcsConstants.rownum_ROQ) {
				    			if (pcsResultList.get(8)[2] !=null &&  UIUtils.isValidKeyId(pcsResultList.get(8)[2].toString()))
				    				total  = Float.parseFloat(pcsResultList.get(8)[2].toString());
				    		}
				    		else if (i == pcsConstants.rownum_OEE) {
				    			if (pcsResultList.get(9)[2] !=null &&  UIUtils.isValidKeyId(pcsResultList.get(9)[2].toString()))
				    				total  = Float.parseFloat(pcsResultList.get(9)[2].toString());
				    		}
				    		
				    		else if (i == pcsConstants.rownum_WNO) 
				    			total = Float.parseFloat("0.00");
				    		
				    		//total = Float.parseFloat(String.format("%.2g%n", total));
				    		//CommonFunctions.debugMsg("total==="+total.toString());
				    		
				    		NumberFormat df = DecimalFormat.getInstance();
				    		df.setMinimumFractionDigits(2);
				    		df.setMaximumFractionDigits(1);
				    		//df.setRoundingMode(RoundingMode.UP);
				    		
				    		//total = Float.parseFloat(String.format("%.2f", total));
				    		
				    		//CommonFunctions.debugMsg(df.format(total));				    		
				    		//CommonFunctions.debugMsg("Total values s"+ df.format(total));
				    		
				    		if (! df.format(total).equals("0.00") && ! df.format(total).equals("0.0"))
				    			pcsDatas[i][j] = df.format(total);
				    			//pcsDatas[i][j] = total.toString();
				    		else 
				    			pcsDatas[i][j] =" ";
				    	}
				    	if(j<=pcsDatas[i].length-1)
				    		cell.put( ( pcsDatas[i][j] != null ?pcsDatas[i][j].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", "").replace("(+)", "(-)"):" ") );
				    	else
				    		cell.put(" ");
			    		//cell.put( ( pcsDatas[i][j] != null ?pcsDatas[i][j].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("<*", "" ).replace("*>", "").replace("(+)", "(-)"):" ") );
			    	}
			    }
			    
			    
			 // JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			    if(i != 0)
			    {
			    	rowObj.put("id",i);
			    	rowObj.put("cell",cell);
			    	rowArr.put(rowObj);
			    }
		 }
		
		jqGridTableModel.getRowHeaders().add(colHeader3);		
      
		jqGridTableModel.setRowNumbers(false);
		
        tableDataObject.put("rows", rowArr);
        tableDataObject.put("records", (dataArrayList.size()+4));
        tableDataObject.put("total", 1);
        tableDataObject.put("page", 1);
        tableDataObject.put("pageRowCount", 150);
        
        //tableDataObject.set("tableHeight", "%%");	    
        
        httpSession.setAttribute("pcsEntryDataServlet", tableDataObject);
        //jqGridTableModel.setTableHeight(300);
        JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
        tableModel.put("rows", rowArr);
        tableModel.put("records", (dataArrayList.size()+4));
        tableModel.put("total", 1);
        tableModel.put("page", 1);        
        tableModel.put("pageRowCount", 150);
        tableModel.set("tableHeight", "35%");
       // tableModel.set("tableWidth", "60%%");
        tableModel.set("loadOnce", true);        
		// return tableModel;
        CommonFunctions.debugMsg("Table Model ::::::::: "+tableModel);
        return tableModel;
		
	}

	private JSONObject getResultTableModel(List<String[]> headers)
	{
		
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String[] row = headers.get(0);
		jqGridTableModel.getRowHeaders().add(row);	
		
		jqGridTableModel.setTableButton(false);		 
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setRowNumbers(false);
		
		for(int i=0;i<row.length;i++)
		{
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setName(row[i].replaceAll(" ", "")+ i);
			jqGridColModel.setEditable(false);
			CommonFunctions.debugMsg(i + " : "+row[i]);
			//jqGridColModel.setHidden(true);			
			
			if(i==0)
				jqGridColModel.setWidth(150);
			else if(i==1)
				jqGridColModel.setWidth(40);
			else
				jqGridColModel.setWidth(50);
				
			jqGridTableModel.getColModel().add(jqGridColModel);
		}	
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "55%%");
		tableModel.set("tableWidth", "65%%");
		
		
		CommonFunctions.debugMsg(tableModel   + "..................");
		return tableModel;
	}
 
	
	private static String[][] populatePcsDispData(List<String[]> configParamList, List<String[]> dataList){
		int  lossParmaCol = 9;
		
		PCSConstants pcsConstants = new PCSConstants(configParamList,lossParmaCol);
		
		String [][] pcsData = new String[ configParamList.size()+4 ][ (configParamList.get(0).length+1) + dataList.size() ];
		CommonFunctions.debugMsg("Length : "+configParamList.size());
		CommonFunctions.debugMsg("Length : "+dataList.size());
		int dataSize = dataList.size();
		int rowIndex = 0;
		int dataColIndex = 0;
		int subLossRow;
		
		for( String [] configRow : configParamList ){
			//CommonFunctions.debugMsg("len : "+configRow.length);
			if ( rowIndex == 1) rowIndex++; 
			if ( rowIndex == 2) rowIndex++;// || rowIndex == 2 || rowIndex == 3)
			if ( rowIndex == 3) rowIndex++;
			if ( rowIndex == 4) rowIndex++;
			for( int j = 0; j < configRow.length ;j++ ){
				//CommonFunctions.debugMsg("12345"+rowIndex +"-"+ j + " : "+ configRow[ j ]);					
					pcsData [ rowIndex ][ j ] =  configRow[ j ] ;
			}
			rowIndex++;
		}
		
		int colIndexStart = configParamList.get(0).length;
		int colIndex = 0;
		int dataRowIndex = 0;
		int headerColIndex = colIndexStart;
		String [] dataHeader = dataList.get(0);
		

		for( String [] dataRow : dataList ){
			//CommonFunctions.debugMsg("dataList.get(0).length"+dataList.get(0).length);
			
			for (int t = 0;t<dataList.get(0).length;t++)
			{
				//CommonFunctions.debugMsg("dataRow[t]"+dataRow[t]);
				if (UIUtils.isValidKeyId(dataRow[t]))
				 if (dataRow[t].equals("0"))
					 dataRow[t]=" ";
			}
		}
		
		for( String [] dataRow : dataList ){
		
			//CommonFunctions.debugMsg( headerColIndex + " : "+pcsData[ 0 ][  headerColIndex ] +" :"+dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ]);
			//CommonFunctions.debugMsg( "machine no"+ dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ]);
			
			//setPcsRowwiseRecords(pcsConstants,pcsData ,headerColIndex,colIndexStart,dataRow);
			pcsData[ 0 ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ];
			
			//CommonFunctions.debugMsg( headerColIndex + " : "+pcsData[ 0 ][  headerColIndex ]);
			pcsData[ pcsConstants.rownum_MACHINEID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_MACHINEID ];
			pcsData[ pcsConstants.rownum_PLDETAILSID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PLDETAILSID ];
			pcsData[ pcsConstants.rownum_PLMASTERID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PLMASTERID ];
			pcsData[ pcsConstants.rownum_PRDMKEY ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PRDM_KEYID ];
			headerColIndex++;
			//CommonFunctions.debugMsg( "pcsData11111"+pcsData[ 0 ][  headerColIndex++ ]);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CALENDARTIME ]) && pcsConstants.rownum_MCHAVLTIME >= 0 )
				pcsData[ pcsConstants.rownum_MCHAVLTIME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_CALENDARTIME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PLANNEDQTY ]) && pcsConstants.rownum_PLANNEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_PLANNEDQTY ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PLANNEDQTY ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONTIME ]) && pcsConstants.rownum_PRODTIME >= 0 )
				pcsData[ pcsConstants.rownum_PRODTIME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONTIME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_UNACCOUNTEDTIME ]) && pcsConstants.rownum_UNREPORTED >= 0 )
				pcsData[ pcsConstants.rownum_UNREPORTED][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_UNACCOUNTEDTIME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_NOPLANINMINS ]) && pcsConstants.rownum_NOPLAN >= 0 )
				pcsData[ pcsConstants.rownum_NOPLAN ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_NOPLANINMINS ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_NOOFPRODUCTS ]) && pcsConstants.rownum_NUMPRODUCTS >= 0 )
				pcsData[ pcsConstants.rownum_NUMPRODUCTS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_NOOFPRODUCTS ];
			
			//CommonFunctions.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]"+dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]) && pcsConstants.rownum_THEORITICALCYCLETIME >= 0 )
				pcsData[ pcsConstants.rownum_THEORITICALCYCLETIME][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ];

			//CommonFunctions.debugMsg("pcsConstants.rownum_THEORITICALCYCLETIME]"+pcsConstants.rownum_THEORITICALCYCLETIME);
			//CommonFunctions.debugMsg("colIndexStart]"+colIndexStart);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ACTUALCYCLETIME ]) && pcsConstants.rownum_ACTUALCYCLETIME >= 0 )
				pcsData[ pcsConstants.rownum_ACTUALCYCLETIME][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_ACTUALCYCLETIME ];

			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRMM_NAME]) && pcsConstants.rownum_PRODUCTMODEL >= 0 )
				pcsData[ pcsConstants.rownum_PRODUCTMODEL][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRMM_NAME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRDM_NAME]) && pcsConstants.rownum_PARTNAME >= 0 )
				pcsData[ pcsConstants.rownum_PARTNAME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRDM_NAME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRDM_CODE]) && pcsConstants.rownum_PARTNO >= 0 )
				pcsData[ pcsConstants.rownum_PARTNO ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRDM_CODE ];

			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_WNO ]) && pcsConstants.rownum_WNO >= 0 )
				pcsData[ pcsConstants.rownum_WNO][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_WNO ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ]) && pcsConstants.rownum_CAVITYAVAILABLE >= 0 )
				pcsData[ pcsConstants.rownum_CAVITYAVAILABLE][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CAVITYUSED ]) && pcsConstants.rownum_CAVITYUSED >= 0 )
				pcsData[ pcsConstants.rownum_CAVITYUSED][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_CAVITYUSED ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANDRELAVAILABLE ]) && pcsConstants.rownum_MANDRELAVAILABLE >= 0 )
					pcsData[ pcsConstants.rownum_MANDRELAVAILABLE][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MANDRELAVAILABLE ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANDRELUSED]) && pcsConstants.rownum_MANDRELUSED >= 0 )
				pcsData[ pcsConstants.rownum_MANDRELUSED][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MANDRELUSED ];			
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_INSPECTEDQTY]) && pcsConstants.rownum_INSPECTEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_INSPECTEDQTY][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_INSPECTEDQTY ];			
			
		
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY]) && pcsConstants.rownum_PRODUCEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_PRODUCEDQTY ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY ];
			
			//CommonFunctions.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY ]"+dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY]);
			//CommonFunctions.debugMsg("pcsConstants.rownum_PRODUCEDQTY]"+pcsConstants.rownum_PRODUCEDQTY);
			//CommonFunctions.debugMsg("colIndexStart]"+colIndexStart);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ]) && pcsConstants.rownum_DEFECTNREWORK >= 0 )
				pcsData[ pcsConstants.rownum_DEFECTNREWORK ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ]) && pcsConstants.rownum_REJECTEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_REJECTEDQTY][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ];
			
			//CommonFunctions.debugMsg("pcsConstants.rownum_REWORKQTY]"+pcsConstants.rownum_REWORKQTY);
			//CommonFunctions.debugMsg("colIndexStart]"+colIndexStart);
			//CommonFunctions.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ]"+dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ]);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY]) && pcsConstants.rownum_REWORKQTY >= 0 )
				pcsData[ pcsConstants.rownum_REWORKQTY ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ];
			
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_QAACCEPTEDQTY]) && pcsConstants.rownum_QAACCEPTEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_QAACCEPTEDQTY][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_QAACCEPTEDQTY ];			
			

			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DEFECTTIME_SL ]) && pcsConstants.rownum_DEFECTTIME >= 0 )
				pcsData[ pcsConstants.rownum_DEFECTTIME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_DEFECTTIME_SL ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REPROCESSING ]) && pcsConstants.rownum_REPROCESS >= 0 )
				pcsData[ pcsConstants.rownum_REPROCESS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_REPROCESSING ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONLOSSES]) && pcsConstants.rownum_PRODUCTIONLOSS >= 0 )
				pcsData[ pcsConstants.rownum_PRODUCTIONLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONLOSSES ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_EQUIPMENTFAILURE_ML ]) && pcsConstants.rownum_EF >= 0 )
				pcsData[ pcsConstants.rownum_EF][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_EQUIPMENTFAILURE_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SETUPANDADJUSTMENT_ML]) && pcsConstants.rownum_SETUPANDADJ >= 0 )
				pcsData[ pcsConstants.rownum_SETUPANDADJ ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_SETUPANDADJUSTMENT_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MODELCHANGEPART]) && pcsConstants.rownum_MS >= 0 )
				pcsData[ pcsConstants.rownum_MS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MODELCHANGEPART ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_TOOLCHANGELOSS_ML ]) && pcsConstants.rownum_TOOLCHANGELOSS >= 0 )
				pcsData[ pcsConstants.rownum_TOOLCHANGELOSS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_TOOLCHANGELOSS_ML ];
			
		    
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_STARTUPLOSS_ML]) && pcsConstants.rownum_STARTUPLOSS >= 0 )
				pcsData[ pcsConstants.rownum_STARTUPLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_STARTUPLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MINORSTOPPAGELOSS_ML ]) && pcsConstants.rownum_MS >= 0 )
				pcsData[ pcsConstants.rownum_MS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MINORSTOPPAGELOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SPEEDLOSS_ML]) && pcsConstants.rownum_SPEEDLOSS >= 0 )
				pcsData[ pcsConstants.rownum_SPEEDLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_SPEEDLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SHUTDOWNLOSS_ML]) && pcsConstants.rownum_SHUTDOWNLOSS >= 0 )
				pcsData[ pcsConstants.rownum_SHUTDOWNLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_SHUTDOWNLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANAGEMENTLOSS_ML]) && pcsConstants.rownum_MANAGEMENTLOSS >= 0 )
				pcsData[ pcsConstants.rownum_MANAGEMENTLOSS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MANAGEMENTLOSS_ML ];
		 
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_COMMONUTILITYLOSS_SL]) && pcsConstants.rownum_COMMONUTILITY >= 0 )
				pcsData[ pcsConstants.rownum_COMMONUTILITY ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_COMMONUTILITYLOSS_SL ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_OPERATINGMOTIONLOSS_ML]) && pcsConstants.rownum_MOTIONLOSS >= 0 )
				pcsData[ pcsConstants.rownum_MOTIONLOSS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_OPERATINGMOTIONLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LINEORGANISATIONLOSS_ML]) && pcsConstants.rownum_LINEORGLOSS >= 0 )
				pcsData[ pcsConstants.rownum_LINEORGLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_LINEORGANISATIONLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LOGISTICSLOSS_ML]) && pcsConstants.rownum_LOGISTICSLOSS >= 0 )
				pcsData[ pcsConstants.rownum_LOGISTICSLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_LOGISTICSLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MEASURINGANDADJLOSS_ML]) && pcsConstants.rownum_MEASUREMENTLOSS >= 0 )
				pcsData[ pcsConstants.rownum_MEASUREMENTLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_MEASURINGANDADJLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DIETOOLANDJIGLOSS_ML])  && pcsConstants.rownum_DIETOOLJIGLOSS >= 0)
				pcsData[ pcsConstants.rownum_DIETOOLJIGLOSS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_DIETOOLANDJIGLOSS_ML];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ENERGYLOSS_ML]) && pcsConstants.rownum_ENERGYLOSS >= 0 )
				pcsData[ pcsConstants.rownum_ENERGYLOSS ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_ENERGYLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_YIELDLOSS_ML]) && pcsConstants.rownum_YIELDLOSS >= 0 )
				pcsData[ pcsConstants.rownum_YIELDLOSS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_YIELDLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LOADINGTIME]) && pcsConstants.rownum_LOADINGTIME >= 0 )
				pcsData[ pcsConstants.rownum_LOADINGTIME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_LOADINGTIME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONAVLTIME]) && pcsConstants.rownum_PRODAVLTIME >= 0 )
				pcsData[ pcsConstants.rownum_PRODAVLTIME ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONAVLTIME ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_EFFECTIVEPRODMINS]) && pcsConstants.rownum_EFFPRODMINS >= 0 )
				pcsData[ pcsConstants.rownum_EFFPRODMINS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_EFFECTIVEPRODMINS];
		   
		 
	/*			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ROA])  && pcsConstants.rownum_ROA >= 0)
				pcsData[ pcsConstants.rownum_ROA][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_ROA ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ROQ])  && pcsConstants.rownum_ROQ >= 0 )
				pcsData[ pcsConstants.rownum_ROQ ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_ROQ ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ROP])  && pcsConstants.rownum_ROP >= 0 )
				pcsData[ pcsConstants.rownum_ROP ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_ROP ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_OEE]) && pcsConstants.rownum_OEE >= 0 )				
				pcsData[ pcsConstants.rownum_OEE][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_OEE];
	*/			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REMARKS])  && pcsConstants.rownum_REMARKS >= 0)
				pcsData[ pcsConstants.rownum_REMARKS][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_REMARKS];
			fillSubLoss(pcsData,configParamList,dataRow,dataHeader,colIndexStart);

			colIndexStart++;
		}
		pcsData[ 0 ][  headerColIndex ] = "TOTAL";
		for(int ij = 0;ij<pcsData.length;ij++)
		{
			for (int kl = 0;kl<pcsData[ij].length;kl++)
			{
				//CommonFunctions.debugMsg("["+ij+"]" +"["+ij+"]----------->"+pcsData[ij][kl]);
				if (pcsData[ij][kl] =="") 
					pcsData[ij][kl] = " ";
			}	
		}
		
		return pcsData;
	}
	
	private static void  setPcsRowwiseRecords(List<String[]> pcsMasterList,PCSConstants pcsConstants , String [] pcsData , String [] dataRow,String [] dataHeader) {
		
		pcsData[ pcsConstants.rownum_MACHINEID ] = dataRow [ PCSConstants.PLDTL_CONST_MACHINEID ];
		pcsData[ pcsConstants.rownum_PLDETAILSID ] = dataRow [ PCSConstants.PLDTL_CONST_PLDETAILSID ];
		pcsData[ pcsConstants.rownum_PLMASTERID ] = dataRow [ PCSConstants.PLDTL_CONST_PLMASTERID ];
		pcsData[ pcsConstants.rownum_PRDMKEY ] = dataRow [ PCSConstants.PLDTL_CONST_PRDM_KEYID ];
	
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CALENDARTIME ]) && pcsConstants.rownum_MCHAVLTIME >= 0 )
			pcsData[ pcsConstants.rownum_MCHAVLTIME ] = dataRow[ PCSConstants.PLDTL_CONST_CALENDARTIME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PLANNEDQTY ]) && pcsConstants.rownum_PLANNEDQTY >= 0 )
			pcsData[ pcsConstants.rownum_PLANNEDQTY ] = dataRow[ PCSConstants.PLDTL_CONST_PLANNEDQTY ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONTIME ]) && pcsConstants.rownum_PRODTIME >= 0 )
			pcsData[ pcsConstants.rownum_PRODTIME ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONTIME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_UNACCOUNTEDTIME ]) && pcsConstants.rownum_UNREPORTED >= 0 )
			pcsData[ pcsConstants.rownum_UNREPORTED] = dataRow[ PCSConstants.PLDTL_CONST_UNACCOUNTEDTIME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_NOPLANINMINS ]) && pcsConstants.rownum_NOPLAN >= 0 )
			pcsData[ pcsConstants.rownum_NOPLAN ] = dataRow[ PCSConstants.PLDTL_CONST_NOPLANINMINS ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_NOOFPRODUCTS ]) && pcsConstants.rownum_NUMPRODUCTS >= 0 )
			pcsData[ pcsConstants.rownum_NUMPRODUCTS ] = dataRow[ PCSConstants.PLDTL_CONST_NOOFPRODUCTS ];
		
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]) && pcsConstants.rownum_THEORITICALCYCLETIME >= 0 )
			pcsData[ pcsConstants.rownum_THEORITICALCYCLETIME] = dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ];

		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ACTUALCYCLETIME ]) && pcsConstants.rownum_ACTUALCYCLETIME >= 0 )
			pcsData[ pcsConstants.rownum_ACTUALCYCLETIME] = dataRow[ PCSConstants.PLDTL_CONST_ACTUALCYCLETIME ];

		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRMM_NAME]) && pcsConstants.rownum_PRODUCTMODEL >= 0 )
			pcsData[ pcsConstants.rownum_PRODUCTMODEL] = dataRow[ PCSConstants.PLDTL_CONST_PRMM_NAME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRDM_NAME]) && pcsConstants.rownum_PARTNAME >= 0 )
			pcsData[ pcsConstants.rownum_PARTNAME ] = dataRow[ PCSConstants.PLDTL_CONST_PRDM_NAME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRDM_CODE]) && pcsConstants.rownum_PARTNO >= 0 )
			pcsData[ pcsConstants.rownum_PARTNO ] = dataRow[ PCSConstants.PLDTL_CONST_PRDM_CODE ];

		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_WNO ]) && pcsConstants.rownum_WNO >= 0 )
			pcsData[ pcsConstants.rownum_WNO] = dataRow[ PCSConstants.PLDTL_CONST_WNO ];
		CommonFunctions.debugMsg(dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ]);
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ]) && pcsConstants.rownum_CAVITYAVAILABLE >= 0 )
			pcsData[ pcsConstants.rownum_CAVITYAVAILABLE] = dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_CAVITYUSED ]) && pcsConstants.rownum_CAVITYUSED >= 0 )
			pcsData[ pcsConstants.rownum_CAVITYUSED] = dataRow[ PCSConstants.PLDTL_CONST_CAVITYUSED ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANDRELAVAILABLE ]) && pcsConstants.rownum_MANDRELAVAILABLE >= 0 )
				pcsData[ pcsConstants.rownum_MANDRELAVAILABLE] = dataRow[ PCSConstants.PLDTL_CONST_MANDRELAVAILABLE ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANDRELUSED]) && pcsConstants.rownum_MANDRELUSED >= 0 )
			pcsData[ pcsConstants.rownum_MANDRELUSED] = dataRow[ PCSConstants.PLDTL_CONST_MANDRELUSED ];			
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_INSPECTEDQTY]) && pcsConstants.rownum_INSPECTEDQTY >= 0 )
			pcsData[ pcsConstants.rownum_INSPECTEDQTY] = dataRow[ PCSConstants.PLDTL_CONST_INSPECTEDQTY ];			
		
	
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY]) && pcsConstants.rownum_PRODUCEDQTY >= 0 )
			pcsData[ pcsConstants.rownum_PRODUCEDQTY ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ]) && pcsConstants.rownum_DEFECTNREWORK >= 0 )
			pcsData[ pcsConstants.rownum_DEFECTNREWORK ] = dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ]) && pcsConstants.rownum_REJECTEDQTY >= 0 )
			pcsData[ pcsConstants.rownum_REJECTEDQTY] = dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY]) && pcsConstants.rownum_REWORKQTY >= 0 )
			pcsData[ pcsConstants.rownum_REWORKQTY ] = dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ];
		
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_QAACCEPTEDQTY]) && pcsConstants.rownum_QAACCEPTEDQTY >= 0 )
			pcsData[ pcsConstants.rownum_QAACCEPTEDQTY] = dataRow[ PCSConstants.PLDTL_CONST_QAACCEPTEDQTY ];			
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DEFECTTIME_SL ]) && pcsConstants.rownum_DEFECTTIME >= 0 )
			pcsData[ pcsConstants.rownum_DEFECTTIME ] = dataRow[ PCSConstants.PLDTL_CONST_DEFECTTIME_SL ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REPROCESSING ]) && pcsConstants.rownum_REPROCESS >= 0 )
			pcsData[ pcsConstants.rownum_REPROCESS ] = dataRow[ PCSConstants.PLDTL_CONST_REPROCESSING ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONLOSSES]) && pcsConstants.rownum_PRODUCTIONLOSS >= 0 )
			pcsData[ pcsConstants.rownum_PRODUCTIONLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONLOSSES ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_EQUIPMENTFAILURE_ML ]) && pcsConstants.rownum_EF >= 0 )
			pcsData[ pcsConstants.rownum_EF] = dataRow[ PCSConstants.PLDTL_CONST_EQUIPMENTFAILURE_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SETUPANDADJUSTMENT_ML]) && pcsConstants.rownum_SETUPANDADJ >= 0 )
			pcsData[ pcsConstants.rownum_SETUPANDADJ ] = dataRow[ PCSConstants.PLDTL_CONST_SETUPANDADJUSTMENT_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MODELCHANGEPART]) && pcsConstants.rownum_MS >= 0 )
			pcsData[ pcsConstants.rownum_MS ] = dataRow[ PCSConstants.PLDTL_CONST_MODELCHANGEPART ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_TOOLCHANGELOSS_ML ]) && pcsConstants.rownum_TOOLCHANGELOSS >= 0 )
			pcsData[ pcsConstants.rownum_TOOLCHANGELOSS] = dataRow[ PCSConstants.PLDTL_CONST_TOOLCHANGELOSS_ML ];
		
	    
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_STARTUPLOSS_ML]) && pcsConstants.rownum_STARTUPLOSS >= 0 )
			pcsData[ pcsConstants.rownum_STARTUPLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_STARTUPLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MINORSTOPPAGELOSS_ML ]) && pcsConstants.rownum_MS >= 0 )
			pcsData[ pcsConstants.rownum_MS] = dataRow[ PCSConstants.PLDTL_CONST_MINORSTOPPAGELOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SPEEDLOSS_ML]) && pcsConstants.rownum_SPEEDLOSS >= 0 )
			pcsData[ pcsConstants.rownum_SPEEDLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_SPEEDLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_SHUTDOWNLOSS_ML]) && pcsConstants.rownum_SHUTDOWNLOSS >= 0 )
			pcsData[ pcsConstants.rownum_SHUTDOWNLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_SHUTDOWNLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MANAGEMENTLOSS_ML]) && pcsConstants.rownum_MANAGEMENTLOSS >= 0 )
			pcsData[ pcsConstants.rownum_MANAGEMENTLOSS] = dataRow[ PCSConstants.PLDTL_CONST_MANAGEMENTLOSS_ML ];
	 
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_COMMONUTILITYLOSS_SL]) && pcsConstants.rownum_COMMONUTILITY >= 0 )
			pcsData[ pcsConstants.rownum_COMMONUTILITY ] = dataRow[ PCSConstants.PLDTL_CONST_COMMONUTILITYLOSS_SL ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_OPERATINGMOTIONLOSS_ML]) && pcsConstants.rownum_MOTIONLOSS >= 0 )
			pcsData[ pcsConstants.rownum_MOTIONLOSS] = dataRow[ PCSConstants.PLDTL_CONST_OPERATINGMOTIONLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LINEORGANISATIONLOSS_ML]) && pcsConstants.rownum_LINEORGLOSS >= 0 )
			pcsData[ pcsConstants.rownum_LINEORGLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_LINEORGANISATIONLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LOGISTICSLOSS_ML]) && pcsConstants.rownum_LOGISTICSLOSS >= 0 )
			pcsData[ pcsConstants.rownum_LOGISTICSLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_LOGISTICSLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_MEASURINGANDADJLOSS_ML]) && pcsConstants.rownum_MEASUREMENTLOSS >= 0 )
			pcsData[ pcsConstants.rownum_MEASUREMENTLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_MEASURINGANDADJLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DIETOOLANDJIGLOSS_ML])  && pcsConstants.rownum_DIETOOLJIGLOSS >= 0)
			pcsData[ pcsConstants.rownum_DIETOOLJIGLOSS] = dataRow[ PCSConstants.PLDTL_CONST_DIETOOLANDJIGLOSS_ML];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_ENERGYLOSS_ML]) && pcsConstants.rownum_ENERGYLOSS >= 0 )
			pcsData[ pcsConstants.rownum_ENERGYLOSS ] = dataRow[ PCSConstants.PLDTL_CONST_ENERGYLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_YIELDLOSS_ML]) && pcsConstants.rownum_YIELDLOSS >= 0 )
			pcsData[ pcsConstants.rownum_YIELDLOSS] = dataRow[ PCSConstants.PLDTL_CONST_YIELDLOSS_ML ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_LOADINGTIME]) && pcsConstants.rownum_LOADINGTIME >= 0 )
			pcsData[ pcsConstants.rownum_LOADINGTIME ] = dataRow[ PCSConstants.PLDTL_CONST_LOADINGTIME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONAVLTIME]) && pcsConstants.rownum_PRODAVLTIME >= 0 )
			pcsData[ pcsConstants.rownum_PRODAVLTIME ] = dataRow[ PCSConstants.PLDTL_CONST_PRODUCTIONAVLTIME ];
		
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_EFFECTIVEPRODMINS]) && pcsConstants.rownum_EFFPRODMINS >= 0 )
			pcsData[ pcsConstants.rownum_EFFPRODMINS] = dataRow[ PCSConstants.PLDTL_CONST_EFFECTIVEPRODMINS];
	   
		if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REMARKS])  && pcsConstants.rownum_REMARKS >= 0)
			pcsData[ pcsConstants.rownum_REMARKS] = dataRow[ PCSConstants.PLDTL_CONST_REMARKS];
		fillSubLossValues(pcsData,pcsMasterList,dataRow,dataHeader);
		
	}
	private static void fillSubLoss(String[][] pcsData, List<String[]> configParamList, String [] dataRow, String[] dataHeader, int colIdex)
	{
		
		int rowNo = 0;
		for( String [] configRow : configParamList ){
			
			if( UIUtils.isValidKeyId(configRow[1])){
				int sublossCol = getSubLosssCol(dataHeader,configRow[1]);
				if( sublossCol > 0 && UIUtils.isValidKeyId(dataRow[sublossCol]))
					pcsData[rowNo+4][colIdex] =dataRow[sublossCol];
			}
			rowNo++;
		}
	}
	
	private static int getSubLosssCol(String [] dataHeader, String subLoss)
	{
		for(int i = PCSConstants.PLDTL_CONST_fromSubloss ; i < PCSConstants.PLDTL_CONST_toSubloss; i++ ){
			if( dataHeader[i] != null && subLoss != null && subLoss.equals(dataHeader[i]) ){
				return i;
			}
		}
		return -1;
	}
	
	
	private List<String[]> transposeListArr(List<String[]> dataList)
	{
		if( dataList.size() <=0 ) return null;
		//Object[] pcsRptArr = dataList.toArray();
		
		List<String[]> transposeList = new ArrayList<String[]>();
		
		for( int i =0; i<dataList.get(0).length; i++)
		{	
			String [] tRow = new String [ dataList.size()];
			for (int j=0; j<dataList.size();j++)
			{
				tRow [ j ]= dataList.get(j)[i];

			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
	
	
    private void savePcsEntry(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
	{
		CommonFunctions.debugMsg("Pcl MAIN");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	
    	String isOpenMst = request.getParameter("isOpenMst");
    	
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
    		CommonFunctions.debugMsg("isOpenMst"+isOpenMst);
			CommonFunctions.debugMsg("savemode"+request.getParameter("saveMode"));
			BAL_PcsTlMst existPcsTlMst = (BAL_PcsTlMst)httpSession.getAttribute("PcsTlMst");
			
			BAL_PcsTlMst newPcsTlMst = new BAL_PcsTlMst();
			BAL_PcsTlDtl newPcsTlDtl = new BAL_PcsTlDtl();
    		PcsTlWorkorderlink newPcsTlWorkorderlink = new PcsTlWorkorderlink();
    		BAL_PcsTlLossreasonlink newPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
    		
    		BAL_PcsTlMst oldPcsTlMst = new BAL_PcsTlMst();
    		
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			pcsEntryBean.setIsOpenMsr(isOpenMst);
				

			CommonFunctions.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
			try{

				if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPtwokeyid(request.getParameter("Ptwokeyid"));
	    		
	    		CommonFunctions.debugMsg("Plmasterid=="+request.getParameter("Plmasterid"));	    		 
	    		CommonFunctions.debugMsg("Pldetailsid==="+request.getParameter("Pldetailsid"));
	    		
	    		newPcsTlMst =(BAL_PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);
	    		
	    		newPcsTlWorkorderlink =(PcsTlWorkorderlink)UIUtils.setBeanProperties((Object)newPcsTlWorkorderlink,request);
	    		
	    		CommonFunctions.debugMsg("Second");
	    		newPcsTlDtl =(BAL_PcsTlDtl)UIUtils.setBeanProperties((Object)newPcsTlDtl,request);
	    		
	    		newPcsTlLossreasonlink =(BAL_PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		CommonFunctions.debugMsg("third");
	    		
	    		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmEntryby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
	    		
	    		newPcsTlDtl.setCreatedby(user.getUsrm_ccno());
	    		newPcsTlWorkorderlink.setPtwoCreatedby(user.getUsrm_ccno());
	    		
	    		CommonFunctions.debugMsg("entnjbjb n "+newPcsTlMst.getPrlmEntrydate());
	    		
	    	/*	if( newPcsTlDtl != null)
	    			newPcsTlMst.getPcsDetail().add(newPcsTlDtl);	    		
	    		if( newPcsTlLossreasonlink != null)
	    			newPcsTlMst.getPcslosslink().add(newPcsTlLossreasonlink);
	    	*/
	    		
	    		
	    		CommonFunctions.debugMsg("before check");
	    		String prodQty = newPcsTlDtl.getProducedqty();
	    		String backlog = newPcsTlDtl.getBacklogqty();
	    		if (!UIUtils.isValidKeyId(prodQty)) prodQty="0";	    		
	    		CommonFunctions.debugMsg("prodQty"+prodQty);
	    		if (!UIUtils.isValidKeyId(backlog)) backlog="0";	   
	    		CommonFunctions.debugMsg("backlog.............."+backlog);
	    		
	    		String cavityUsed = newPcsTlDtl.getCavityused();
	    		if (!UIUtils.isValidKeyId(cavityUsed)) cavityUsed="0";
	    		CommonFunctions.debugMsg("cavityUsed"+cavityUsed);
	    		String actTime = newPcsTlDtl.getActualcycletime();
	    		if (!UIUtils.isValidKeyId(actTime)) actTime="0";
	    		CommonFunctions.debugMsg("actTime"+actTime);
	    		
	    		String availTimeStr   = pcsEntryService.getCalendarTime(newPcsTlDtl.getMachineid(), request.getParameter("Plmasterid"), newPcsTlMst.getPrlmSectionid(),newPcsTlMst.getPrlmEntrydate(),request.getParameter("shiftWise"));
	    		CommonFunctions.debugMsg("availTime"+availTimeStr);
	    		
	    		Float availTime = Float.parseFloat(availTimeStr);
	    		if (Float.parseFloat(availTimeStr) < 0)
	    			availTime= (float) 0.0;
	    		
	    		String oldTime=request.getParameter("oldTime");	    		
	    		CommonFunctions.debugMsg("oldTime="+oldTime);
	    		
	    		if (request.getParameter("saveMode").equals("Update"))
	    			availTime = availTime + Float.parseFloat(oldTime);
	    		
	    		CommonFunctions.debugMsg("availTime="+availTime);
	    		//var planQty = (parseFloat(actTime) / parseFloat(cycleTime)) * parseFloat(cavityAvail) ;
	    		String cycleTime= newPcsTlDtl.getActualcycletime();
	    		String theoTime= newPcsTlDtl.getTheoriticalcycletime();
	    		if (!UIUtils.isValidKeyId(cycleTime)) cycleTime="0";
	    		String cavityAvail = newPcsTlDtl.getCavityavailable();
	    		if (!UIUtils.isValidKeyId(cavityAvail)) cavityAvail="0";
	    		
	    		Float planQty ;
	    		CommonFunctions.debugMsg("cycleTime="+cycleTime);
	    		if (cycleTime.equals("0"))
	    				planQty =(float) 0.0;
	    		else
	    			planQty = (availTime / Float.parseFloat(cycleTime) ) * Float.parseFloat(cavityAvail) ;	    		
	    		
	    		CommonFunctions.debugMsg("planQty="+planQty);
	    		
	    		newPcsTlDtl.setPlannedqty(planQty.toString());
	    		newPcsTlDtl.setCalendartime(availTime.toString());
	    		
	    		Float prodTime = ( Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
	    		CommonFunctions.debugMsg("prodTime"+prodTime);
	    			    		
	    		
	    		CommonFunctions.debugMsg("Math.floor(prodTime)====="+Math.floor(prodTime));
	    		CommonFunctions.debugMsg("Math.floor(availTime)===="+Math.floor(availTime));
	    			
	    		if (prodTime > availTime)
					throw new BusinessApplicationExceptions("productionTime_Greate");	    			
	    			
	    		
	    		if (Float.parseFloat(cavityUsed) > Float.parseFloat(cavityAvail))
	    			throw new Exception(" 'Cavity Used' Can not be greater than Cavity Available");
	    			
	    		if (Float.parseFloat(cavityUsed) < 1)
	    			throw new Exception(" 'Cavity Used' Should be atleast 1");
	    		
	    		String actTimePct = pcsEntryService.getActTimePct(); 
	    			//dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE", "ACTIMEPCT");
				int actPct = Integer.parseInt(actTimePct) ;
				CommonFunctions.debugMsg("(actPct %"+actPct );
				
	    		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
	    		CommonFunctions.debugMsg("(time25%"+timePct);
	    		Float timepctVal = (float)0;
	    		
	    		
	    		NumberFormat df = DecimalFormat.getInstance();
	    		df.setMinimumFractionDigits(2);
	    		df.setMaximumFractionDigits(2);
	    		df.setRoundingMode(RoundingMode.UP);
	    		
	    		
	    		timepctVal = (Float.parseFloat(theoTime) + timePct);
	    		CommonFunctions.debugMsg("(timepctVal"+timepctVal);
	    		timepctVal = Float.parseFloat( df.format(timepctVal) );
	    		
    		
	    		if (Float.parseFloat(cycleTime) > (Float.parseFloat(theoTime) + timePct) )
	    			throw new Exception(" Error in 'Actual Cycle Time' " + "<br>" + " (Exceeds " + actPct + "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " + timepctVal + ")");

	    		timepctVal = (Float.parseFloat(theoTime) - timePct);
	    		
	    		timepctVal = Float.parseFloat(df.format(timepctVal));	    		
	    		
	    		if (Float.parseFloat(cycleTime) <  (Float.parseFloat(theoTime) - timePct) )
	    			throw new Exception(" Error in 'Actual Cycle Time'  " + "<br>" + " (Less " + actPct + "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " +  timepctVal + ")");
	    		
	    		
				if( pcsEntryBean.getFormMode().equals("Save"))
				{
		    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
					existPcsTlMst = pcsEntryService.create(newPcsTlMst, newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);					
				}	
				else{
					existPcsTlMst = pcsEntryService.create(newPcsTlMst, newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
					//existPcsTlMst = pcsEntryService.update(newPcsTlMst, oldPcsTlMst, pcsEntryBean);
				}
				
				String keyId =existPcsTlMst.getPrlmKeyid();
				CommonFunctions.debugMsg(" KEYID() " +  keyId);					
			
				httpSession.setAttribute(existPcsTlMst.getPrlmKeyid(), existPcsTlMst);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("prlmKeyid",keyId);
				//persistentData.put("fromBean", formTypeIdentifier);				
				/*JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");
				successData.put("formClear",false);
				successData.put("mode",pcsEntryBean.getFormMode() );
				successData.put("keyId", keyId); */				
				JSONObject returnData = new JSONObject();					
				//returnData.put("successData", successData);
				/* add returnData.put("dtlKeyId", newPcsTlDtl.getPldetailsid()) */
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				returnData.put("dtlKeyId", newPcsTlDtl.getPldetailsid());
				String isClear = request.getParameter("isClear");
				returnData.put("isClear", isClear);
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}catch(Exception e)
			{
			
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException", e.getMessage());
				out.print(err.toString());
			}
    	}
	}


    private void savePcsEntryNew(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
	{
			
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	
    	String isOpenMst = request.getParameter("isOpenMst");
    	String saveMode = request.getParameter("saveMode");
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
			CommonFunctions.debugMsg("isOpenMst : "+isOpenMst);
			CommonFunctions.debugMsg("savemode : "+saveMode);
			
			BAL_PcsTlMst existPcsTlMst = (BAL_PcsTlMst)httpSession.getAttribute("PcsTlMst");
			
			BAL_PcsTlMst newPcsTlMst = new BAL_PcsTlMst();
			BAL_PcsTlDtl newPcsTlDtl = new BAL_PcsTlDtl();
    		PcsTlWorkorderlink newPcsTlWorkorderlink = new PcsTlWorkorderlink();
    		BAL_PcsTlLossreasonlink newPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();		
    		
    		
			if (saveMode.equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			pcsEntryBean.setIsOpenMsr(isOpenMst);
				

			CommonFunctions.debugMsg("formBean Mode : "+pcsEntryBean.getFormMode());
			
			try{

				if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPtwokeyid(request.getParameter("Ptwokeyid"));
	    		
	    		CommonFunctions.debugMsg("Plmasterid : "+request.getParameter("Plmasterid"));	    		 
	    		CommonFunctions.debugMsg("Pldetailsid : "+request.getParameter("Pldetailsid"));
	    		
	    		newPcsTlMst =(BAL_PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);	    		
	    		newPcsTlWorkorderlink =(PcsTlWorkorderlink)UIUtils.setBeanProperties((Object)newPcsTlWorkorderlink,request);	    		
	    		newPcsTlDtl =(BAL_PcsTlDtl)UIUtils.setBeanProperties((Object)newPcsTlDtl,request);	    		
	    		newPcsTlLossreasonlink =(BAL_PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		
	    		
	    		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmEntryby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
	    		
	    		newPcsTlDtl.setCreatedby(user.getUsrm_ccno());
	    		newPcsTlWorkorderlink.setPtwoCreatedby(user.getUsrm_ccno());
	    		
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() : "+newPcsTlMst.getPrlmEntrydate());
	    		
	    		
	    		
	    		/*String prodQty = newPcsTlDtl.getProducedqty();
	    		if (!UIUtils.isValidKeyId(prodQty)) prodQty="0";	    		
	    		CommonFunctions.debugMsg("prodQty : "+prodQty);
	    		
	    		String cavityUsed = newPcsTlDtl.getCavityused();
	    		if (!UIUtils.isValidKeyId(cavityUsed)) cavityUsed="0";
	    		CommonFunctions.debugMsg("cavityUsed : "+cavityUsed);
	    		
	    		String actTime = newPcsTlDtl.getActualcycletime();
	    		if (!UIUtils.isValidKeyId(actTime)) actTime="0";
	    		CommonFunctions.debugMsg("actTime : "+actTime);
	    		
	    		String availTimeStr   = pcsEntryService.getCalendarTime(newPcsTlDtl.getMachineid(), request.getParameter("Plmasterid"), newPcsTlMst.getPrlmSectionid(),newPcsTlMst.getPrlmEntrydate());
	    		CommonFunctions.debugMsg("availTime : "+availTimeStr);
	    		
	    		Float availTime = Float.parseFloat(availTimeStr);
	    		if (Float.parseFloat(availTimeStr) < 0)
	    			availTime= (float) 0.0;
	    		
	    		String oldTime=request.getParameter("oldTime");	    		
	    		CommonFunctions.debugMsg("oldTime : "+oldTime);
	    		
	    		if (request.getParameter("saveMode").equals("Update"))
	    			availTime = availTime + Float.parseFloat(oldTime);
	    		
	    		CommonFunctions.debugMsg("AvailTime After Mode Verifcn : "+availTime);
	    		//var planQty = (parseFloat(actTime) / parseFloat(cycleTime)) * parseFloat(cavityAvail) ;
	    		String cycleTime= newPcsTlDtl.getActualcycletime();
	    		String theoTime= newPcsTlDtl.getTheoriticalcycletime();
	    	
	    		
	    		if (!UIUtils.isValidKeyId(cycleTime)) cycleTime="0";
	    		
	    		String cavityAvail = newPcsTlDtl.getCavityavailable();
	    		if (!UIUtils.isValidKeyId(cavityAvail)) cavityAvail="0";
	    		
	    		Float planQty ;
	    		
	    		if (cycleTime.equals("0"))
	    			planQty =(float) 0.0;
	    		else
	    			planQty = (availTime / Float.parseFloat(cycleTime) ) * Float.parseFloat(cavityAvail) ;	    		
	    		
	    		CommonFunctions.debugMsg("planQty : "+planQty);
	    		
	    		newPcsTlDtl.setPlannedqty(planQty.toString());
	    		newPcsTlDtl.setCalendartime(availTime.toString());
	    		
	    		Float prodTime;
	    		if (cavityUsed.equals("0"))
	    			prodTime =Float.parseFloat(prodQty)* Float.parseFloat(actTime);
	    		else
	    			prodTime = ( Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
	    		 
	    		CommonFunctions.debugMsg("prodTime : "+prodTime);
	    			    		
	    			
	    		if (Math.floor(prodTime) > availTime)
					throw new BusinessApplicationExceptions("productionTime_Greate");	    			
	    			
	    		
	    		if (Float.parseFloat(cavityUsed) > Float.parseFloat(cavityAvail))
	    			throw new Exception(" 'Cavity Used' Can not be greater than Cavity Available");*/
	    			
	    		//if (Float.parseFloat(cavityUsed) < 1)
	    			//throw new Exception(" 'Cavity Used' Should be atleast 1");
	    		
	    		/*String actTimePct = pcsEntryService.getActTimePct(); 
	    			
				int actPct = Integer.parseInt(actTimePct) ;
				CommonFunctions.debugMsg("actPct % : "+actPct );
				
	    		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
	    		CommonFunctions.debugMsg("time25% : "+timePct);
	    		Float timepctVal = (float)0;
	    		
	    		
	    		NumberFormat df = DecimalFormat.getInstance();
	    		df.setMinimumFractionDigits(2);
	    		df.setMaximumFractionDigits(2);
	    		df.setRoundingMode(RoundingMode.UP);
	    		
	    		
	    		timepctVal = (Float.parseFloat(theoTime) + timePct);
	    		CommonFunctions.debugMsg("(timepctVal : "+timepctVal);
	    		timepctVal = Float.parseFloat( df.format(timepctVal) );
	    		
    		
	    		if (Float.parseFloat(cycleTime) > (Float.parseFloat(theoTime) + timePct) )
	    			throw new Exception(" Error in 'Actual Cycle Time' " + "<br>" + " (Exceeds " + actPct + "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " + timepctVal + ")");

	    		timepctVal = (Float.parseFloat(theoTime) - timePct);
	    		
	    		timepctVal = Float.parseFloat(df.format(timepctVal));	    		
	    		
	    		if (Float.parseFloat(cycleTime) <  (Float.parseFloat(theoTime) - timePct) )
	    			throw new Exception(" Error in 'Actual Cycle Time'  " + "<br>" + " (Less " + actPct + "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " +  timepctVal + ")");*/
	    	
	    		String insertNewCol = request.getParameter("insertNewCol");
	    		String plDtlId = request.getParameter("plDtlId");
	    		String plDetailsId = newPcsTlDtl.getPldetailsid();
				if( pcsEntryBean.getFormMode().equals("Save"))
				{
					newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
				}
				else
				{
					if(UIUtils.isValidKeyId(plDetailsId))
					{
						if(plDetailsId.equals(plDtlId))
							insertNewCol = "N";
					}
					
				}
				pcsEntryBean.setOldTime(request.getParameter("hdnOldTime"));
			//	newPcsTlDtl.setRejectedqty(request.getParameter("txtRejQty"));
				CommonFunctions.debugMsg("Rejected Qty : "+newPcsTlDtl.getRejectedqty());
				existPcsTlMst = pcsEntryService.createNew(newPcsTlMst, newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
				
				String keyId =existPcsTlMst.getPrlmKeyid();
				CommonFunctions.debugMsg(" KEYID :  " +  keyId);
				String firstEntry = request.getParameter("firstEntry");
				String[] retVal = getColDatas(newPcsTlMst.getPrlmFactoryid(), newPcsTlMst.getPrlmShiftid(), newPcsTlMst.getPrlmEntrydate(),newPcsTlMst.getPrlmCellid(), newPcsTlMst.getPrlmSectionid(), newPcsTlDtl.getMachineid(), newPcsTlDtl.getPldetailsid());
				/*List<String []> pcsMasterList  = pcsEntryService.getParameters(newPcsTlMst.getPrlmFactoryid());
				List<String []> pcsMstList  = pcsEntryService.getProductDetail(newPcsTlMst.getPrlmShiftid(),newPcsTlMst.getPrlmEntrydate(),newPcsTlMst.getPrlmCellid(),newPcsTlMst.getPrlmSectionid(),  newPcsTlDtl.getMachineid(),newPcsTlDtl.getPldetailsid());
				PCSConstants pcsConstants = new PCSConstants(pcsMasterList,9);
				String[] pcsData = new String[ pcsMasterList.size()+4 ];
				String [] dataHeader = pcsMstList.get(0);
				String colHeader = "";
				for(String[] dataRow : pcsMstList)
				{
					setPcsRowwiseRecords(pcsMasterList,pcsConstants , pcsData , dataRow,dataHeader); 
					colHeader = dataRow[PCSConstants.PLDTL_CONST_MACHINENO];
				}
				//CommonFunctions.debugMsg("Length : "+pcsData.length);
				String colValues = "";
				for(int i=0;i<pcsData.length;i++)
				{
					String datas = " ";
					if(UIUtils.isValidKeyId(pcsData[i]))
					{
						if(!pcsData[i].equals("0"))
							datas = pcsData[i];
					}
					if(i == 0)
						colValues = datas;
					else
						colValues = colValues + datas;
					if(i != pcsData.length-1)
						colValues = colValues+ ",";
				}*/
				//CommonFunctions.debugMsg("colValues : "+colValues);
				
				String PLDetailsID ="";
				if(UIUtils.isValidKeyId(existPcsTlMst.getPrlmActive()))
				{
					//if(existPcsTlMst.getPrlmActive().equals("U"))
						PLDetailsID = newPcsTlDtl.getPldetailsid();					
				}
				CommonFunctions.debugMsg(" KEYID :  " +  keyId);
				
				httpSession.setAttribute(existPcsTlMst.getPrlmKeyid(), existPcsTlMst);
				String formBeanIdentifier = "PcsEntryBean Form Mode : "+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("prlmKeyid",keyId);
				JSONObject returnData = new JSONObject();					
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				returnData.put("dtlKeyId", newPcsTlDtl.getPldetailsid());
				returnData.put("PLDetailsID", PLDetailsID);
				String isClear = request.getParameter("isClear");
				returnData.put("isClear", isClear);
				returnData.put("machineId", newPcsTlDtl.getMachineid());
				
				returnData.put("insertNewCol", insertNewCol);
				returnData.put("firstEntry", firstEntry);
				returnData.put("colHeader",  retVal[1]);
				returnData.put("colValues", retVal[0]);
				
				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(newPcsTlDtl.getMachineid(), newPcsTlMst.getPrlmSectionid(), newPcsTlDtl.getPlmasterid(), newPcsTlMst.getPrlmEntrydate());
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));				
				
				
				returnData.put("msg", "Data Saved Successfully");
				CommonFunctions.debugMsg(returnData.toString());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", e.getMessage());
				out.print(err.toString());
			}
    	}
	}
/*    private  String[] getColDatas(String factId,String shiftId,String entryDate,String cellId,String sectId,String mchId,String plDtlId) throws Exception
    {
    	List<String []> pcsMasterList  = pcsEntryService.getParameters(factId);
		List<String []> pcsMstList  = pcsEntryService.getProductDetail(shiftId,entryDate,cellId,sectId,  mchId,plDtlId);
		PCSConstants pcsConstants = new PCSConstants(pcsMasterList,9);
		String[] pcsData = new String[ pcsMasterList.size()+4 ];
		String [] dataHeader = pcsMstList.get(0);
		String colHeader = "";
		for(String[] dataRow : pcsMstList)
		{
			setPcsRowwiseRecords(pcsMasterList,pcsConstants , pcsData , dataRow,dataHeader); 
			colHeader = dataRow[PCSConstants.PLDTL_CONST_MACHINENO];
		}
		//CommonFunctions.debugMsg("Length : "+pcsData.length);
		String colValues = "";
		for(int i=0;i<pcsData.length;i++)
		{
			String datas = " ";
			if(UIUtils.isValidKeyId(pcsData[i]))
			{
				if(!pcsData[i].equals("0") && pcsData[i].indexOf("CAVITY")<0 && pcsData[i].indexOf("LOSS")<0 && pcsData[i].indexOf("TIME")<0 )
					datas = pcsData[i];
			}
			if(i == 0)
				colValues = datas;
			else
				colValues = colValues + datas;
			if(i != pcsData.length-1)
				colValues = colValues+ ",";
		}
		String[] retVal = new String[2];
		retVal[0] = colValues;
		retVal[1] = colHeader;
		return retVal;
    }
*/    
/*    private static void fillSubLossValues(String[] pcsData, List<String[]> configParamList, String [] dataRow, String[] dataHeader)
	{
		
		int rowNo = 0;
		for( String [] configRow : configParamList ){
			
			if( UIUtils.isValidKeyId(configRow[1])){
				int sublossCol = getSubLosssCol(dataHeader,configRow[1]);
				if( sublossCol > 0 && UIUtils.isValidKeyId(dataRow[sublossCol]))
					pcsData[rowNo+4] =dataRow[sublossCol];
			}
			rowNo++;
		}
	}
*/	
/*	private static int getSubLossesCol(String [] dataHeader, String subLoss)
	{
		for(int i = PCSConstants.PLDTL_CONST_fromSubloss ; i < PCSConstants.PLDTL_CONST_toSubloss; i++ ){
			if( dataHeader[i] != null && subLoss != null && subLoss.equals(dataHeader[i]) ){
				return i;
			}
		}
		return -1;
	}
*/
    private  String[] getColDatas(String factId,String shiftId,String entryDate,String cellId,String sectId,String mchId,String plDtlId) throws Exception
    {
    	List<String []> pcsMasterList  = pcsEntryService.getParameters(cellId);
		List<String []> pcsMstList  = pcsEntryService.getProductDetail(shiftId,entryDate,cellId,sectId,  mchId,plDtlId);
		PCSConstants pcsConstants = new PCSConstants(pcsMasterList,9);
		String[] pcsData = new String[ pcsMasterList.size()+4 ];
		String [] dataHeader = pcsMstList.get(0);
		String colHeader = "";
		for(String[] dataRow : pcsMstList)
		{
			setPcsRowwiseRecords(pcsMasterList,pcsConstants , pcsData , dataRow,dataHeader); 
			colHeader = dataRow[PCSConstants.PLDTL_CONST_MACHINENO];
		}
		CommonFunctions.debugMsg("pcsData.length=== "+pcsData.length);
		String colValues = "";
		for(int i=0;i<pcsData.length;i++)
		{
			String datas = " ";
			if(UIUtils.isValidKeyId(pcsData[i]))
			{
				if(!pcsData[i].equals("0") && pcsData[i].indexOf("CAVITY")<0 && pcsData[i].indexOf("LOSS")<0 && pcsData[i].indexOf("TIME")<0 )
					datas = pcsData[i];
			}
			if(i == 0)
				colValues = datas;
			else
				colValues = colValues + datas;
			if(i != pcsData.length-1)
				colValues = colValues+ ",";
		}
		String[] retVal = new String[2];
		retVal[0] = colValues;
		retVal[1] = colHeader;
		return retVal;
    }
    private static void fillSubLossValues(String[] pcsData, List<String[]> configParamList, String [] dataRow, String[] dataHeader)
	{
		
		int rowNo = 0;
		for( String [] configRow : configParamList ){
			
			if( UIUtils.isValidKeyId(configRow[1])){
				int sublossCol = getSubLosssCol(dataHeader,configRow[1]);
				if( sublossCol > 0 && UIUtils.isValidKeyId(dataRow[sublossCol]))
					pcsData[rowNo+4] =dataRow[sublossCol];
			}
			rowNo++;
		}
	}
	
	private static int getSubLossesCol(String [] dataHeader, String subLoss)
	{
		for(int i = PCSConstants.PLDTL_CONST_fromSubloss ; i < PCSConstants.PLDTL_CONST_toSubloss; i++ ){
			if( dataHeader[i] != null && subLoss != null && subLoss.equals(dataHeader[i]) ){
				return i;
			}
		}
		return -1;
	}

	private void noPlanSave(HttpServletRequest request, HttpServletResponse response ) throws IOException, BusinessApplicationExceptions
	{
		CommonFunctions.debugMsg("Pcl MAIN");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String from=(String)httpSession.getAttribute("from");
    	CommonFunctions.debugMsg("from view:"+from);
    	UIUtils.displayRequestParamsValue(request);
    	
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		//CommonFunctions.debugMsg("pcsEntryBean.getFormActionMode()"+ pcsEntryBean.getFormActionMode());
    		
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
			CommonFunctions.debugMsg("savemode"+request.getParameter("saveMode"));
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			CommonFunctions.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
			String mchId = request.getParameter("mchId");
			String date = request.getParameter("date");
			String shift = request.getParameter("shift");
			String type = "";
			String fromTme=request.getParameter("fromDate");
			String toTime=request.getParameter("toDate");
			String chkDeleteNoPlan=request.getParameter("chkDeleteNoPlan");
			if("1".equals(chkDeleteNoPlan))
				type="DELETE";
			else
				type="NOPLAN";
			//type is for auto or Noplan			
			String duration=request.getParameter("noplanDuration");
			if(!UIUtils.isValidKeyId(duration))
			{
				duration="480";
			}
			String userid = user.getUsrm_ccno();
			CommonFunctions.debugMsg("mchId"+mchId);
			CommonFunctions.debugMsg("date"+date);
			CommonFunctions.debugMsg("shift"+shift);
			
			//CommonFunctions.debugMsg("userid"+userid);
			
			try{
				if( from.equals("noPlanEntry"))		{
				}									
				else {				
					if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
						throw new BusinessApplicationExceptions("view_Mode"+",");
					
					String Plmasterid= request.getParameter("Plmasterid");
					String sectId = request.getParameter("sectId");
					String shiftWise =request.getParameter("shiftWise");
				
					String availTime   = pcsEntryService.getCalendarTime(mchId, Plmasterid, sectId,date,shiftWise);
					
					CommonFunctions.debugMsg("availTime"+availTime);
					
					if (!availTime.equals("480"))
						throw new BusinessApplicationExceptions("prd_Exists"+",");
				}
				
				//pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));	    		
	    		String noPlanResult="";
				if( pcsEntryBean.getFormMode().equals("Save")) {									
					noPlanResult = pcsEntryService.insertNoPlan(mchId,date,shift,userid,type,duration,fromTme,toTime);							
				}
		    	
				String keyId =noPlanResult;
				CommonFunctions.debugMsg("noPlanResult"+noPlanResult);
				CommonFunctions.debugMsg(" KEYID() " +  keyId);					
				
				//httpSession.setAttribute(existPcsTlMst.getPrlmKeyid(), existPcsTlMst);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				JSONObject successData = new JSONObject();
				successData.put("chkDeleteNoPlan",chkDeleteNoPlan );
				successData.put("mode",pcsEntryBean.getFormMode() );
				successData.put("status", noPlanResult);
				successData.put("duration", duration);
				successData.put("type", type);
				successData.put("msg","Data Saved Successfully");
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject returnData = new JSONObject();					
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				returnData.put("status", "success");
				returnData.put("keyId", keyId);
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}catch(Exception e)
			{
				e.printStackTrace();
				CommonFunctions.debugMsg("gete......... " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}
	}

    private void savePcsLossEntry(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	CommonFunctions.debugMsg("Pcs Loss Entry Save Servlete");		
    	CommonFunctions.debugMsg("Pcs Loss Entry Save Servlet2222");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
			CommonFunctions.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			CommonFunctions.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				
	    		BAL_PcsTlLossreasonlink existPcsTlLossreasonlink = (BAL_PcsTlLossreasonlink)httpSession.getAttribute("PcsTlLossreasonlink");
	    		BAL_PcsTlLossreasonlink newPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
	    		BAL_PcsTlLossreasonlink oldPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
	    		
	    		newPcsTlLossreasonlink =(BAL_PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		CommonFunctions.debugMsg("third");
	    		newPcsTlLossreasonlink.setPlrkCreatedby(user.getUsrm_ccno());	    		
	    		
				
				String msrGridVar = request.getParameter("MsrValues");	
	    		
	    		PcsTlAncilliarytime newPcsTlAncilliarytime = new PcsTlAncilliarytime();	    		
	    		newPcsTlAncilliarytime.setPtatCreatedby(user.getUsrm_ccno());	
	    		newPcsTlAncilliarytime =(PcsTlAncilliarytime)UIUtils.setBeanProperties((Object)newPcsTlAncilliarytime,request);
	    					
	    		if(!UIUtils.isValidKeyId(msrGridVar))	{
		    		CommonFunctions.debugMsg("MmsrGridVargrid: "+msrGridVar);
	    		}
	    		else {
	    			CommonFunctions.debugMsg("msrGridVargrid = "+msrGridVar);

		    		List<PcsTlAncilliarytime> AncilliaryList = null;
			    	JSONArray AncilliaryJson = null;
			    	if(UIUtils.isValidKeyId(msrGridVar))
			    	{
			    		AncilliaryJson = JSONArray.fromString(msrGridVar);
			    		CommonFunctions.debugMsg("AncilliaryJson"+AncilliaryJson);
			    		AncilliaryList = (List<PcsTlAncilliarytime>)UIUtils.convertJSONArrToList(newPcsTlAncilliarytime,AncilliaryJson);
			    		if(AncilliaryList!= null){
			    			CommonFunctions.debugMsg("AncilliaryList---"+AncilliaryList);
			    			newPcsTlLossreasonlink.setPcsTlAncilliarytimeList(AncilliaryList);
			    		}
	    		    }
	    		}
	    				    		
	    		pcsEntryBean.setLossId(request.getParameter("lossId"));
	    		pcsEntryBean.setLossValue(request.getParameter("lossValue"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPlrkKeyid(request.getParameter("PlrkKeyid"));
	    		
	    		CommonFunctions.debugMsg("LossId"+request.getParameter("lossId"));
	    		CommonFunctions.debugMsg("lossValue"+request.getParameter("lossValue"));
	    		CommonFunctions.debugMsg("Pldetailsid========"+request.getParameter("Pldetailsid"));
	    		CommonFunctions.debugMsg("PlrkKeyid========"+request.getParameter("PlrkKeyid"));
	    		
	    		
				if( pcsEntryBean.getFormMode().equals("Save"))
					existPcsTlLossreasonlink = pcsEntryService.createLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				else
					existPcsTlLossreasonlink = pcsEntryService.updateLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				
				String keyId =existPcsTlLossreasonlink.getPlrkKeyid();
				CommonFunctions.debugMsg(" KEYID() " +  keyId);					
				
				httpSession.setAttribute(existPcsTlLossreasonlink.getPlrkKeyid(), existPcsTlLossreasonlink);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("bphmKeyid",keyId);
				//persistentData.put("fromBean", formTypeIdentifier);
				
				/*JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");				
				successData.put("mode",pcsEntryBean.getFormMode() );
				successData.put("keyId", keyId); */
				
				JSONObject returnData = new JSONObject();					
				//returnData.put("successData", successData);			
				returnData.put("formClear", false);				
				returnData.put("keyId", keyId);
				out.print(returnData.toString());
				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("gete.,,,,,,,, " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", e.getMessage());
				out.print(err.toString());
			}
    	}
	}
    
    private void savePcsLossEntryNew(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
    		if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			try{
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
	    		BAL_PcsTlLossreasonlink existPcsTlLossreasonlink = (BAL_PcsTlLossreasonlink)httpSession.getAttribute("PcsTlLossreasonlink");
	    		BAL_PcsTlLossreasonlink newPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
	    		BAL_PcsTlLossreasonlink oldPcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
	    		
	    		newPcsTlLossreasonlink =(BAL_PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		newPcsTlLossreasonlink.setPlrkCreatedby(user.getUsrm_ccno());	    		
	    		
	    		String msrGridVar = request.getParameter("MsrValues");	
	    		
	    		PcsTlAncilliarytime newPcsTlAncilliarytime = new PcsTlAncilliarytime();	    		
	    		newPcsTlAncilliarytime.setPtatCreatedby(user.getUsrm_ccno());	
	    		newPcsTlAncilliarytime =(PcsTlAncilliarytime)UIUtils.setBeanProperties((Object)newPcsTlAncilliarytime,request);
	    					
	    		/*if(!UIUtils.isValidKeyId(msrGridVar))	{
		    		CommonFunctions.debugMsg("MmsrGridVargrid: "+msrGridVar);
	    		}
	    		else {
	    			CommonFunctions.debugMsg("msrGridVargrid = "+msrGridVar);

		    		List<PcsTlAncilliarytime> AncilliaryList = null;
			    	JSONArray AncilliaryJson = null;
			    	if(UIUtils.isValidKeyId(msrGridVar))
			    	{
			    		AncilliaryJson = JSONArray.fromString(msrGridVar);
			    		CommonFunctions.debugMsg("AncilliaryJson"+AncilliaryJson);
			    		AncilliaryList = (List<PcsTlAncilliarytime>)UIUtils.convertJSONArrToList(newPcsTlAncilliarytime,AncilliaryJson);
			    		if(AncilliaryList!= null){
			    			CommonFunctions.debugMsg("AncilliaryList---"+AncilliaryList);
			    			newPcsTlLossreasonlink.setPcsTlAncilliarytimeList(AncilliaryList);
			    		}
	    		    }
	    		}*/
	    				    		
	    		pcsEntryBean.setLossId(request.getParameter("lossId"));
	    		pcsEntryBean.setLossValue(request.getParameter("lossValue"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPlrkKeyid(request.getParameter("PlrkKeyid"));
	    		
	    		if( pcsEntryBean.getFormMode().equals("Save"))
					existPcsTlLossreasonlink = pcsEntryService.createLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				else
					existPcsTlLossreasonlink = pcsEntryService.updateLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				
				String keyId =existPcsTlLossreasonlink.getPlrkKeyid();
				String detailId =existPcsTlLossreasonlink.getPlrkPldetailid();
				String lossId =existPcsTlLossreasonlink.getPlrkLossid();
				String instance =existPcsTlLossreasonlink.getPlrkInstance();
				String minutes =existPcsTlLossreasonlink.getPlrkMinutes();
				CommonFunctions.debugMsg(" KEYID() " +  keyId);					
				String[] retVal = getColDatas(existPcsTlLossreasonlink.getPlrkFactoryid(), existPcsTlLossreasonlink.getPlrkShiftid(), existPcsTlLossreasonlink.getPlrkDate(),existPcsTlLossreasonlink.getPlrkCellid(), existPcsTlLossreasonlink.getPlrkSectionid(), existPcsTlLossreasonlink.getPlrkMachineid(), detailId);
				httpSession.setAttribute(existPcsTlLossreasonlink.getPlrkKeyid(), existPcsTlLossreasonlink);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("bphmKeyid",keyId);
				//persistentData.put("fromBean", formTypeIdentifier);
				
				/*JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");				
				successData.put("mode",pcsEntryBean.getFormMode() );
				successData.put("keyId", keyId); */
				
				JSONObject returnData = new JSONObject();					
				//returnData.put("successData", successData);	
				returnData.put("msg","Data Saved Successfully");
				returnData.put("formClear", false);
				returnData.put("detailId", detailId);
				returnData.put("lossId", lossId);
				returnData.put("instance", instance);
				returnData.put("minutes", minutes);
				returnData.put("keyId", keyId);
				returnData.put("colHeader",  retVal[1]);
				returnData.put("colValues", retVal[0]);
				returnData.put("type", "S-L");
				
				//set report times 
				String machineId =  request.getParameter("txtPlrkMachineid");
				String sectId = request.getParameter("txtPlrkSectionid");
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("txtPlrkDate");

				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(machineId, sectId, plmasterId, entryDate);				
				
				returnData.put("asstTime", plmData.get("asstTime"));
				returnData.put("prodTime", plmData.get("prodTime"));
				returnData.put("lossTime", plmData.get("lossTime"));
				returnData.put("unreportTime", plmData.get("unreportTime"));				

				// for lossNo				
				String lossNo = pcsEntryService.getLossNo(lossId) ; 
				returnData.put("lossNo", lossNo);
				
				CommonFunctions.debugMsg("returnData.toString() "+returnData.toString());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				e.printStackTrace();
				CommonFunctions.debugMsg("gete.////////// " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", e.getMessage());
				out.print(err.toString());
			}
    	}
	}
    
    
    private void savePcsLossCapture(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	String MachineId= request.getParameter("machineId");
    	String shiftWise= request.getParameter("shiftWise");
    	CommonFunctions.debugMsg("savePcsLossCapture Servlete");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	BAL_PcsTlLosscapture newPcsTlLosscapture = new BAL_PcsTlLosscapture();
    	if(MachineId != null || MachineId !=""){
			CommonFunctions.debugMsg(MachineId+" if  machine Id in Servlet");
		   newPcsTlLosscapture.setPlosMachineId(MachineId);
    	}
		else{
			CommonFunctions.debugMsg(MachineId+" else  machine Id in Servlet");
			newPcsTlLosscapture.setPlosMachineId("-");
		}
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			else
				CommonFunctions.debugMsg("avail Time=="+pcsEntryBean.getAvailtime());
			
			if (request.getParameter("saveMode").equals("update"))
				pcsEntryBean.setFormMode("update");
			else 
				pcsEntryBean.setFormMode("save");
		
			
			//CommonFunctions.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			try{
				pcsEntryBean =(BAL_PcsEntryBean) UIUtils.setBeanProperties((Object)pcsEntryBean,request);
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");

	    		BAL_PcsTlLosscapture existPcsTlLosscapture = (BAL_PcsTlLosscapture)httpSession.getAttribute("PcsTlLosscapture");
	    		//PcsTlLosscapture newPcsTlLosscapture = new PcsTlLosscapture();
	    		BAL_PcsTlDtl pcsTlDtl=new BAL_PcsTlDtl();
	    		

	    		newPcsTlLosscapture =(BAL_PcsTlLosscapture)UIUtils.setBeanProperties((Object)newPcsTlLosscapture,request);
	    		CommonFunctions.debugMsg(MachineId+" after else  machine Id in Servlet");
	    		CommonFunctions.debugMsg("third");
	    		newPcsTlLosscapture.setPlosCreatedby(user.getUsrm_ccno());
	    		CommonFunctions.debugMsg(request.getParameter("pldetailsId")+" after else  machine Id in Servlet 2"+request.getParameter("Plmasterid"));
	    		pcsEntryBean.setPlosKeyid(request.getParameter("plosKeyid"));	    		
	    		pcsEntryBean.setPlrkKeyid (request.getParameter("plrkKeyid"));
	    		pcsEntryBean.setPlmasterid (request.getParameter("Plmasterid"));
	    		pcsEntryBean.setAvailtime(request.getParameter("Availtime"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("pldetailsId"));
	    		pcsEntryBean.setShiftWise(shiftWise);
	    		


	    		newPcsTlLosscapture.setPlosSource(request.getParameter("lossSource"));
	    		newPcsTlLosscapture.setPlosSourcename(request.getParameter("sourceName"));
	    		
	    		newPcsTlLosscapture.setPlosToolId(request.getParameter("cmbPlosToolId"));
	    		String pldetailsId = request.getParameter("pldetailsId");
	    		
	    		CommonFunctions.debugMsg("LossSource.....qwerty...."+request.getParameter("lossSource")+pldetailsId );
	    		
	    		/*pcsEntryBean.setLosshrno(request.getParameter("cmbSelPrdHourL"));
	    		String losshrno=pcsEntryBean.getLosshrno();
	    		CommonFunctions.debugMsg( "lossHr-" +losshrno);*/
	    		newPcsTlLosscapture.setPlosPldetailsid(pldetailsId);
	    		
	    		//added on 09-jul-2015
	    		String  selPrdHourL = request.getParameter("cmbSelPrdHourL");
	    		String shiftId=request.getParameter("shiftId");
	    		String frmTime ="";
	    		String toTime =""; 
	    		String lossTime=  newPcsTlLosscapture.getPlosLosstime();
	    	/*	if(shiftWise.equals("Y")){
	    			
		    			frmTime = newPcsTlLosscapture.getPlosFromtime(); 
		    			toTime = newPcsTlLosscapture.getPlosTotime();
		    			newPcsTlLosscapture.setPlosFromtime(request.getParameter("fromdate") + " " + newPcsTlLosscapture.getPlosFromtime()  );
		    			newPcsTlLosscapture.setPlosTotime(request.getParameter("todate") + " " + newPcsTlLosscapture.getPlosTotime() );
		    			newPcsTlLosscapture.setPloslosshrno("-");

	    		}*/
	    		CommonFunctions.debugMsg("selPrdHourLselPrdHourL"+selPrdHourL+" ,,,  "+lossTime);
	    		if (UIUtils.isValidKeyId(lossTime)) {
	    			List<String[]> shiftStrEnd = pcsEntryService.getShiftStarEndTime(shiftId);
	    			
					if (shiftStrEnd.size()>0){
						String startdate= request.getParameter("fromdate");//shiftStrEnd.get(0)[0];
						//int breakTime=Integer.parseInt(shiftStrEnd.get(0)[2]);
						String starttime=shiftStrEnd.get(0)[3];
						CommonFunctions.debugMsg("starttime 345" +starttime);
						String date = startdate + " " + starttime + ":00" ;
						CommonFunctions.debugMsg("date 543" +date);
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH); 
						sdf.setLenient(false);

						java.util.Date dd	 = sdf.parse(date);
						CommonFunctions.debugMsg("dd=====" +dd); 
						Calendar cal=Calendar.getInstance();
						cal.setTime(dd);
						
						//get hour start time 
						cal.add(Calendar.HOUR, Integer.parseInt(selPrdHourL)-1);
						frmTime = sdf.format(cal.getTime());
						//get add loss minute to get end time 
						CommonFunctions.debugMsg("lossTime=="+lossTime);
						//String[] lossVal = lossTime.split(".");
						int dotPlace =  lossTime.indexOf(".");
						CommonFunctions.debugMsg("dotPlace=="+dotPlace);
						String mins = "0";
						String secnds = "0";
						
						if (dotPlace>=0) {
							mins = lossTime.substring(0,dotPlace);
							secnds = lossTime.substring(dotPlace+1,lossTime.length());
						}
						else {
							mins = lossTime;
						}
						
							CommonFunctions.debugMsg("minsmins="+mins+"secnds=="+secnds);
							
						if(UIUtils.isValidKeyId(mins))
							cal.add(Calendar.MINUTE, Integer.parseInt(mins));
						if(UIUtils.isValidKeyId(secnds))
							cal.add(Calendar.SECOND, Integer.parseInt(secnds));
						
						/*if(lossVal.length>=0)
							cal.add(Calendar.MINUTE, Integer.parseInt(lossVal[0]));
						if(lossVal.length>=1)
							cal.add(Calendar.SECOND, Integer.parseInt(lossVal[1]));
						*/
						toTime = sdf.format(cal.getTime());
					}
					
					CommonFunctions.debugMsg("frmTime===" +frmTime);
					CommonFunctions.debugMsg("toTime===toTime" +toTime);
	    			// toTime = newPcsTlLosscapture.getPlosTotime();
	    			newPcsTlLosscapture.setPlosFromtime( frmTime );
	    			newPcsTlLosscapture.setPlosTotime(toTime );
	    			newPcsTlLosscapture.setPloslosshrno(selPrdHourL);

	    		}
	    		else {
	    			frmTime = newPcsTlLosscapture.getPlosFromtime(); 
	    			 toTime = newPcsTlLosscapture.getPlosTotime();
	    			newPcsTlLosscapture.setPlosFromtime(request.getParameter("fromdate") + " " + newPcsTlLosscapture.getPlosFromtime()  );
	    			newPcsTlLosscapture.setPlosTotime(request.getParameter("todate") + " " + newPcsTlLosscapture.getPlosTotime() );
		    		if(pcsEntryBean.getShiftWise()=="Y"){
		    			newPcsTlLosscapture.setPloslosshrno("0");

		    		}
		    		else
	    			newPcsTlLosscapture.setPloslosshrno(selPrdHourL);

	    		}
	    		
	    		
	    		
	    		CommonFunctions.debugMsg("newPcsTlLosscapture.getPlosFromtime()" + newPcsTlLosscapture.getPlosFromtime()+ "newPcsTlLosscapture.getPlosTotime()" + newPcsTlLosscapture.getPlosTotime()+newPcsTlLosscapture.getPloslosshrno());
	    		//CommonFunctions.debugMsg("fliddd===="+newPcsTlLosscapture.getPlosFlid());
	    		//if( pcsEntryBean.getFormMode().equals("Save"))
	    		CommonFunctions.debugMsg("after pcsTlDtl.getProduavailabletime ....." + pcsTlDtl.getProductionavltime()+"pcs bean acvail"+pcsEntryBean.getAvailtime()+"pcs bean houravail"+pcsEntryBean.getAvailtimehourly());
	    		existPcsTlLosscapture = pcsEntryService.createLossCapture(newPcsTlLosscapture,pcsEntryBean);
				
				CommonFunctions.debugMsg("after pcsTlDtl.getProduavailabletime" + pcsTlDtl.getProductionavltime()+"pcs bean acvail"+pcsEntryBean.getAvailtime()+"pcs bean houravail"+pcsEntryBean.getAvailtimehourly());
				String keyId =existPcsTlLosscapture.getPlosKeyid();
				//CommonFunctions.debugMsg(" KEYID() " +  keyId);					
				
				httpSession.setAttribute(existPcsTlLosscapture.getPlosKeyid(), existPcsTlLosscapture);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));				
				
				JSONObject returnData = new JSONObject();					
				returnData.put("successData", successData);								
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				returnData.put("Mode","pcsLossCaptureSave");
				returnData.put("pldetailsId", existPcsTlLosscapture.getPlospldetailsid());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
			/*	JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());	
			*/	e.printStackTrace();
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","colnotmapped");
				err.put("successData",mesg);
				CommonFunctions.debugMsg(mesg);
					out.print(mesg.toString());
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				CommonFunctions.debugMsg("gete.;;;;;;;;;;;;; " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}			
    	}

	}
    private void savePcsLossRejectiondata(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	CommonFunctions.debugMsg("savePcsLossrejection Capture Servlete");
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	
    	if( httpSession != null && user != null)
    	{
    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
        	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
			if (request.getParameter("saveMode").equals("update"))
				pcsEntryBean.setFormMode("update");
			else 
				pcsEntryBean.setFormMode("save");
		
			BAL_PcsTlRejectionbreakupdata newpcsTlRejectionbreakupdata= new BAL_PcsTlRejectionbreakupdata();
			BAL_PcsTlRejectionbreakupdata existpcsTlRejectionbreakupdata=null;
    		
    		newpcsTlRejectionbreakupdata.setPrbdCreatedby(user.getUsrm_ccno());
    		String mstKeyid=request.getParameter("pprdKeyid");
    		newpcsTlRejectionbreakupdata.setPrbdPprdKeyid(mstKeyid);
    		String rejectiondetail=request.getParameter("rejectionGridDetails");
    		/** save JsonARR conversion For Detail **/
			List<BAL_PcsTlRejectionbreakupdata> RejectionGridList=new ArrayList<BAL_PcsTlRejectionbreakupdata>();
		    JSONArray RejectionGridjson = null;	    		
			System.out.println("Parameter Details"+ rejectiondetail);
			try{
				if(UIUtils.isValidKeyId(rejectiondetail) )
				{
					System.out.println("Parameter Size"+ rejectiondetail);
					RejectionGridjson = JSONArray.fromString(rejectiondetail);
					CommonFunctions.debugMsg(" ParamGrid Checking Now :: Json Problem " + RejectionGridjson);
					RejectionGridList=(List<BAL_PcsTlRejectionbreakupdata>) UIUtils.convertJSONArrToList(newpcsTlRejectionbreakupdata, RejectionGridjson);
					CommonFunctions.debugMsg(" After Converting Json To List :: "+RejectionGridjson);
					CommonFunctions.debugMsg("List"+RejectionGridList.size());
					boolean insert = true;
				
					  if( ! UIUtils.isValidKeyId(newpcsTlRejectionbreakupdata.getPrbdKeyid()))	
						{
						   System.out.println("KeyId id Null");
						   System.out.println("Keyid"+newpcsTlRejectionbreakupdata.getPrbdPprdKeyid());
						   existpcsTlRejectionbreakupdata = pcsEntryService.createRejectionbreakpupdataRecord(newpcsTlRejectionbreakupdata,existpcsTlRejectionbreakupdata,RejectionGridList);
						}
					
					  }
			
		
		
			CommonFunctions.debugMsg("keyid after save"+mstKeyid);
		//	httpSession.setAttribute(existpcsTlRejectionbreakupdata.getPrbdKeyid(), existpcsTlRejectionbreakupdata);
			String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
		//	httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
			
			JSONObject mode = new JSONObject();
			mode.put("formMode",pcsEntryBean.getFormMode());
			
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));				
			
			JSONObject returnData = new JSONObject();					
			returnData.put("successData", successData);								
			returnData.put("formClear", false);
			returnData.put("Mode","rejectionSave");
			returnData.put("pprdKeyid", mstKeyid);
			returnData.put("prbdKeyid", existpcsTlRejectionbreakupdata.getPrbdKeyid());
		//	returnData.put("rejection", existPcsTlProductiondata.getPprdRejectedqty());
		//	returnData.put("rework", existPcsTlProductiondata.getPprdReworkqty());
			
			out.print(returnData.toString());	
			}
			
			catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}
			
			catch(Exception e)
			{
				CommonFunctions.debugMsg("gete.?????????? " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}	
			
    	}
	}
    private void savePcsLossProductionCapture(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{

    	CommonFunctions.debugMsg("savePcsLossProduction Capture Servlete");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	String saveMode = request.getParameter("saveMode");
    	BAL_PcsEntryBean pcsEntryBean = new BAL_PcsEntryBean();
    	if( httpSession != null && user != null)
    	{    		
    	//	pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
    		BAL_PcsTlHtDetails newPcsTlHtDetails = new BAL_PcsTlHtDetails();
    		BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory =new BAL_PcsTlHtProductionHistory();
    		BAL_PcsTlMst newPcsTlMst = new BAL_PcsTlMst();
    		BAL_PcsTlDtl newPcsTlDtl = new BAL_PcsTlDtl();
    		BAL_PcsTlMst existPcsTlMst = (BAL_PcsTlMst)httpSession.getAttribute("BAL_PcsTlMst");
    	//	PcsTlMst existPcsTlMst = null;//(PcsTlMst)httpSession.getAttribute("PcsTlMst");
    		BAL_PcsTlProductiondata newPscTlProductiondata=new BAL_PcsTlProductiondata();
    		BAL_PcsTlProductiondata existPcsTlProductiondata=null;
    		BAL_PcsTlHtDetails exitPcsTlHtDetails =null;
    			
		/*	if (saveMode.equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
		
			CommonFunctions.debugMsg("formBean Mode : "+pcsEntryBean.getFormMode());
		*/
			try{
				pcsEntryBean =(BAL_PcsEntryBean) UIUtils.setBeanProperties((Object)pcsEntryBean,request);
				newPcsTlMst =(BAL_PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);	    		
	    		newPcsTlDtl =(BAL_PcsTlDtl)UIUtils.setBeanProperties((Object)newPcsTlDtl,request);	    		
	    		newPscTlProductiondata=(BAL_PcsTlProductiondata) UIUtils.setBeanProperties((Object)newPscTlProductiondata, request);

				
	    	/*	if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
				throw new BusinessApplicationExceptions("view_Mode"+",");
				*/
				pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));
	   		CommonFunctions.debugMsg("Plmasterid :"+request.getParameter("Plmasterid"));
	    		
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		CommonFunctions.debugMsg("Pldetailsid"+request.getParameter("Pldetailsid"));
	    		
	    		pcsEntryBean.setFormMode(request.getParameter("saveMode"));
	    		String shiftWise=request.getParameter("shiftWise");
	    		//CommonFunctions.debugMsg("saveMode : "+request.getParameter("saveMode"));
	    		
	    		//CommonFunctions.debugMsg("addrowgridid "+pcsEntryBean.getAddrowtoGrid());	    		
	    		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmEntryby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmEntrydate(request.getParameter("fromdate"));
	    		pcsEntryBean.setShiftWise(shiftWise);
//	    		if(shiftWise=="Y"){
//	    			newPcsTlMst.setPrlmLogtype("S");
//	    		}
//	    		else 
//	    			newPcsTlMst.setPrlmLogtype("H");
	    		if ("Y".equals(shiftWise)) {
	    		    newPcsTlMst.setPrlmLogtype("S");
	    		} else {
	    		    newPcsTlMst.setPrlmLogtype("H");
	    		}

	    		//newPcsTlMst.setPrlmDate(request.getParameter("fromdate"));
	    		newPcsTlMst.setPrlmFlid(request.getParameter("flId"));
	    		newPcsTlMst.setPrlmShiftid(request.getParameter("shiftid"));
	    		newPcsTlMst.setPrlmCellid(request.getParameter("cellId"));
	    		CommonFunctions.debugMsg("request.getParameter.."+request.getParameter("machineId"));
	    		newPcsTlMst.setPrlmMachineid(request.getParameter("machineId"));

	    		newPcsTlDtl.setCreatedby(user.getUsrm_ccno());
	    /*		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 1 :"+pcsEntryBean.getAvailtimehourly());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 2:"+pcsEntryBean.getProdtimehourly());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 3:"+pcsEntryBean.getUnreptimehourly());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 4:"+pcsEntryBean.getLosstimehourly());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 5: "+newPcsTlMst.getPrlmEntrydate());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 6: "+pcsEntryBean.getAvailtime());
	    		CommonFunctions.debugMsg("newPcsTlMst.getPrlmEntrydate() 7: "+pcsEntryBean.getSftDuration());
	    	*/	
	    		double fullhour = Integer.parseInt(pcsEntryBean.getSftDuration());
				double hourlength= Math.ceil(fullhour/60); 
				int avltime=(int)(fullhour%60);
	    		if(pcsEntryBean.getShiftWise().equals("Y")){
	    			pcsEntryBean.setAvailtimehourly(String.valueOf(fullhour));
	    			newPscTlProductiondata.setPprdHour("0");
	    		}
	    		
	    		else{
				
				if(newPscTlProductiondata.getPprdShiftnoplan() == null )
				{
					int hourno = Integer.parseInt(newPscTlProductiondata.getPprdHour());
					if(  hourno ==  hourlength)
					{
					if(avltime == 0)
						pcsEntryBean.setAvailtimehourly("60");
					else
						pcsEntryBean.setAvailtimehourly(String.valueOf(avltime));
					}
					else if (hourno <  hourlength)
					{
					pcsEntryBean.setAvailtimehourly("60");
					}
				}
	    		}
	    		if ("Y".equals(newPscTlProductiondata.getPprdShiftnoplan())) {
	    		    newPscTlProductiondata.setPprdHour("0");
	    		}
				String cellId=request.getParameter("cellId");
	    		String keyid=request.getParameter("pprdKeyid");
	    		String batchqty=request.getParameter("txtPprdBatchQty");
	    	//	CommonFunctions.debugMsg(batchqty+" ? "+newPscTlProductiondata.getPprdRemarks()+"-remarks "+newPscTlProductiondata.getPprdBatchQty()+"-btchQ "+newPscTlProductiondata.getPprdWeightpiece()+"-weight "+newPscTlProductiondata.getPprdArea()+"-area");
	    		if(cellId.equals("CEL00000890")){
	    			
	    			CommonFunctions.debugMsg("inside cell heat treatment"+newPcsTlHtProductionHistory.getCellid());
	    			newPcsTlHtProductionHistory.setCellid(cellId);
	    			exitPcsTlHtDetails = pcsEntryService.createProductiondataCapture(newPcsTlHtProductionHistory, newPcsTlHtDetails,exitPcsTlHtDetails);
	    			String keyId =exitPcsTlHtDetails.getKeyid();
					httpSession.setAttribute(existPcsTlProductiondata.getPprdKeyid(), existPcsTlProductiondata);
					String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
					httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);				
					JSONObject mode = new JSONObject();
					mode.put("formMode",pcsEntryBean.getFormMode());				
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));	
					JSONObject returnData = new JSONObject();					
				//	returnData.put("successData", successData);								
					returnData.put("formClear", false);
					returnData.put("Mode","productionSave");
					returnData.put("cellid",exitPcsTlHtDetails.getCellmaning());
					//returnData.put("flid",exitPcsTlHtDetails.getPprdFlid());
					returnData.put("date",exitPcsTlHtDetails.getTempField1());
					returnData.put("shiftid",exitPcsTlHtDetails.getTempField2());
					//returnData.put("pldetailsId",exitPcsTlHtDetails.getPprdTempfield1());
					returnData.put("plmasterId",exitPcsTlHtDetails.getMasterid());
					returnData.put("pprdKeyid", exitPcsTlHtDetails.getKeyid());
					//returnData.put("hourNo", exitPcsTlHtDetails.getPprdHour());
					returnData.put("rejection", exitPcsTlHtDetails.getRejectedQty());
					returnData.put("rework", exitPcsTlHtDetails.getReworkQty());
					out.print(returnData.toString());	
	    		}
	    		else
	    		existPcsTlProductiondata = pcsEntryService.createProductiondataCapture(newPscTlProductiondata,existPcsTlProductiondata,newPcsTlMst, newPcsTlDtl, pcsEntryBean);
	    	//	CommonFunctions.debugMsg(newPscTlProductiondata.getPprdRemarks()+"-remarks "+newPscTlProductiondata.getPprdWeightpiece()+"-weight "+newPscTlProductiondata.getPprdArea()+"-area");
	    	//	CommonFunctions.debugMsg(existPcsTlProductiondata.getPprdBatchQty()+"-btchQ existPcsTlProductiondata"+existPcsTlProductiondata.getPprdWeightpiece()+"-weight "+existPcsTlProductiondata.getPprdArea()+"-area");
				String keyId =existPcsTlProductiondata.getPprdKeyid();
				CommonFunctions.debugMsg("existPcsTlProductiondata.getPprdKeyid() "+existPcsTlProductiondata.getPprdKeyid()+"    "+keyId);
				httpSession.setAttribute(existPcsTlProductiondata.getPprdKeyid(), existPcsTlProductiondata);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));	
				JSONObject returnData = new JSONObject();					
				returnData.put("successData", successData);								
				returnData.put("formClear", false);
				returnData.put("Mode","productionSave");
				returnData.put("cellid",existPcsTlProductiondata.getPprdCellid());
				returnData.put("flid",existPcsTlProductiondata.getPprdFlid());
				returnData.put("date",existPcsTlProductiondata.getPprdDate());
				returnData.put("shiftid",existPcsTlProductiondata.getPprdShiftid());
				returnData.put("pldetailsId",existPcsTlProductiondata.getPprdTempfield1());
				returnData.put("plmasterId",existPcsTlProductiondata.getPprdTempfield2());
				returnData.put("pprdKeyid", existPcsTlProductiondata.getPprdKeyid());
				returnData.put("hourNo", existPcsTlProductiondata.getPprdHour());
				returnData.put("rejection", existPcsTlProductiondata.getPprdRejectedqty());
				returnData.put("rework", existPcsTlProductiondata.getPprdReworkqty());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Total utilized Time is more than Total available Time. (Unreported Time is in negative). ROP may go beyond 100%");
				//err.put("tpmException", e.toString());
				out.print(err.toString());
			}			
    	}

	}
    
    private void savePcsEmployee(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	CommonFunctions.debugMsg("savePcsEmployee Servlete");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new BAL_PcsEntryBean();
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
		
			
			CommonFunctions.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");

	    		PcsTlOperatordtl existPcsTlOperatordtl = (PcsTlOperatordtl)httpSession.getAttribute("PcsTlOperatordtl");
	    		PcsTlOperatordtl newPcsTlOperatordtl = new PcsTlOperatordtl();
	    		
	    		newPcsTlOperatordtl =(PcsTlOperatordtl)UIUtils.setBeanProperties((Object)newPcsTlOperatordtl,request);
	    		
	    		CommonFunctions.debugMsg("third");
	    		newPcsTlOperatordtl.setPopdCreatedby(user.getUsrm_ccno());
	    		
	    		pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));	    		
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		
	    		CommonFunctions.debugMsg("Plmasterid"+request.getParameter("Plmasterid"));

	    		if( pcsEntryBean.getFormMode().equals("Save"))
					existPcsTlOperatordtl = pcsEntryService.createPcsEmployee(newPcsTlOperatordtl,pcsEntryBean);
				
				String keyId =existPcsTlOperatordtl.getPopdPlemployeeid();
				//CommonFunctions.debugMsg(" KEYID() " +  keyId);					
				
				httpSession.setAttribute(existPcsTlOperatordtl.getPopdPldetailsid(), existPcsTlOperatordtl);
				String formBeanIdentifier = "PcsEntryBean"+pcsEntryBean.getFormMode();
				httpSession.setAttribute(formBeanIdentifier,pcsEntryBean);
				
				JSONObject mode = new JSONObject();
				mode.put("formMode",pcsEntryBean.getFormMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("bphmKeyid",keyId);
				
				JSONObject returnData = new JSONObject();					
				returnData.put("formClear", false);
				returnData.put("keyId", keyId);
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonFunctions.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonFunctions.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("gete.'''''''''' " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}			
    	}
	}

    private void deletePcsEmployee(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String pldetailsId = request.getParameter("pldetailsId");
    	String empId = request.getParameter("empId");
    	
    	CommonFunctions.debugMsg("pldetailsId=="+pldetailsId);
    	
    	if( httpSession != null && user != null)
    	{
    		BAL_PcsEntryBean pcsEntryBean = (BAL_PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

	    	CommonFunctions.debugMsg("delete query exe");
			JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));				
			successData.put("keyId", empId);
			JSONObject returnData = new JSONObject();
			try{

	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_DelMode"+",");
	    		
				String deleteResult;
				deleteResult = pcsEntryService.deletePcsEmployee(pldetailsId,empId);
								
				returnData.put("successData", successData);
				returnData.put("formClear", false);
				out.print(returnData.toString());
				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				//errMessage.put("fromMode",costInfoBean.getFormActionMode());
				out.print(errMessage.toString());
				
		
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());	
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}
    	}		
    	
	}

	private JSONObject getTableModel(List<String[]> lossgrid) {
		
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = lossgrid.get(0);
		
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(false);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(500);
		
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(145);
			System.out.println("inside ["+i+"]"+colHeader[i]);
			
		
			if(i==0 || i==1 || i==8  || i == 10  ||  i==14 ||   i==16  || i==17  ||  i==18 ||i==19|| i==20 || i==26  ){
		    	jqGridColModel.setHidden(true);
			}
		
		    if ( i==4 || i==6 || i==7 ) 	    {
		    	jqGridColModel.setWidth(50);
		    	jqGridColModel.setAlign("right");
		    }
		    if (i==2) 	    {
		    	jqGridColModel.setWidth(25);
		    	jqGridColModel.setAlign("right");
		    }

		    
		    if (i==3 || i==5 ) 	    {
		    	jqGridColModel.setWidth(70);
		    	jqGridColModel.setAlign("right");
		    }
		    
		    if (i==9  ) {
		    	jqGridColModel.setWidth(70);
		    	jqGridColModel.setAlign("right");
			}
		    if (i==11 || i==12 )	{
		    	jqGridColModel.setWidth(220);				
			}
		    if (i==14 )	    {
		    	jqGridColModel.setWidth(10);				
			}
		    if (  i==15)	    {
		    	jqGridColModel.setWidth(160);
		    	//jqGridColModel.setAlign("center");
			}
		    if (i==13)	    {
		    	jqGridColModel.setWidth(180);				
			}
		    
		    if (i==22) {
		    	jqGridColModel.setAlign("center");	
		    	jqGridColModel.setFormatter("whywhyBtn");
				jqGridColModel.setWidth(80);
			}
			
		    if (i==16 )	    {
		    	jqGridColModel.setWidth(150);
		  
			}
		    if (  i==21)	    {
		    	jqGridColModel.setWidth(180);
		    	//jqGridColModel.setAlign("right");
			}
		    
		    if (i==24) {
		    	jqGridColModel.setFormatter("kaizenBtn");
		    	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(80);
			}
		    
	    jqGridTableModel.getColModel().add(jqGridColModel);
	}
	
	
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "35%%");
	tableModel.set("tableWidth", "100%%");
	return tableModel;
	}
	
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		 
		HttpSession httpSession = request.getSession(false);
  		
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
  		if( commonFilter != null && ! createNew ){
  			FilterValues.setPaginationParams(request,commonFilter);
  		}	
  		else{
  			commonFilter =  new CommonFilter();
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
  			commonFilter = 	FilterValues.getPCS(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		
  		return commonFilter;
  	}
	private JSONObject getTableModelPcsBd(List<String[]> kpivReportList,
			CommonFilter commonFilter) {
		
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
					
			jqGridColModel.setWidth( 110);				
			jqGridColModel.setAlign("left");
			
			
			CommonFunctions.debugMsg("SAOMAintOrdercolheader  "+i+" : "+colHeader[i]);//if i==0
			if(i==0){
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("center");
				jqGridColModel.setHidden(true);
			}
			else if(i==1){					
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
				//jqGridColModel.setHidden(true);
				jqGridColModel.setFormatter("TxtJhResult");
			}
			else if(i==2){					
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(true);
			    

			}
			else if(i==3){					
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
				
			}
			
			else if(i==4 ){
				jqGridColModel.setWidth(350);				
				jqGridColModel.setAlign("left");
			}
			else if(i==7|| i==8||i==5 || i==6){					
			
				jqGridColModel.setWidth(90);	
				jqGridColModel.setFormatter("quantityFormatter");
				jqGridColModel.setAlign("left");
			}
			else if(i==9){					
				jqGridColModel.setWidth(100);   
			//	jqGridColModel.setFormatter("abnBtn");
		    	jqGridColModel.setAlign("center");	
				//jqGridColModel.setAlign("left");
			}
			else if(i==10 ){					
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(true);
			}
			else if(i==11 ){					
				jqGridColModel.setWidth(10);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setHidden(true);
			}
		jqGridTableModel.getColModel().add(jqGridColModel);
	}

	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
}
	private JSONObject getTableModelSpeedLoss(List<String[]> kpivReportList,
			CommonFilter commonFilter) {
		
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
					
			jqGridColModel.setWidth( 110);				
			jqGridColModel.setAlign("left");
			
			
			CommonFunctions.debugMsg("SAOMAintOrdercolheader  "+i+" : "+colHeader[i]);//if i==0
			if(i==0 ){
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("center");
				jqGridColModel.setHidden(true);
			}
			else if(i==1 ){					
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
			}
			else if(i==2){					
				jqGridColModel.setWidth(350);				
				jqGridColModel.setAlign("left");
			}
			
			else if(i==3 || i==4 ){					
				jqGridColModel.setWidth(90);	
				jqGridColModel.setFormatter("quantityFormatter");
				jqGridColModel.setAlign("left");
			}
			else if(i==5){
				jqGridColModel.setHidden(true);
				
			}
			
		jqGridTableModel.getColModel().add(jqGridColModel);
	}

	 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel;
}

}
