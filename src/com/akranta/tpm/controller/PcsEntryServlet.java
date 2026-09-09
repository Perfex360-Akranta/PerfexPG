package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.net.aso.a;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.bean.PlannedJobDescBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLctcostperminute;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.service.PcsComplianceRptService;
import com.akranta.tpm.service.PcsEntryService;
import com.akranta.tpm.service.impl.PcsEntryServiceImpl;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.PCSConstants;

public class PcsEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static int count;
	private PcsEntryService pcsEntryService; 
	private PcsComplianceRptService pcsComplianceRptService;
	private static final String AdmUploadExcelServlet_filename = "DocManagerServletfilename" ; // madhan // "PcsEntryServletfilename";
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
    
	public PcsEntryServlet() {
    
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
		try {
			pcsEntryService = (PcsEntryServiceImpl)UIUtils.getServiceObject(request,"PcsEntryServiceImpl");
			pcsComplianceRptService=(PcsComplianceRptService)UIUtils.getServiceObject(request,"PcsComplianceRptServiceImpl");
			
			CommonMessage.debugMsg("  pcsEntryServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			pcsEntryService.PcsEntryServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
			
		
			
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		if (action.equals("pcsEntry_input.pcs") && user != null) 
		{
			
			CommonMessage.debugMsg("input the jsp");
			// there is nothing to be done
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			request.setAttribute("pcsEnableDisableFormBean", pcsEnableDisableFormBean);
		}

		
		String dispatchUrl = null; 
		
		if (action.equals("pcsCalendar_input.pcs") || action.equals("pcsCalendarTest_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the jsp");
			CommonMessage.debugMsg("cell id "+ request.getParameter("cellId"));
			CommonMessage.debugMsg("date "+ request.getParameter("date"));
			CommonMessage.debugMsg("shift "+ request.getParameter("shift"));

			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String factId = request.getParameter("factId");
			String sectId = request.getParameter("sectId");
			String mchId = request.getParameter("mchId");
			String flid = request.getParameter("flid");
			
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) 	 request.setAttribute("shift", shift);
			if (UIUtils.isValidKeyId(cellId))	request.setAttribute("cellId", cellId);
			if (UIUtils.isValidKeyId(factId))	request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))	request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(mchId))	request.setAttribute("mchId", mchId);
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			request.setAttribute("mode", "create");
			if(action.equals("pcsCalendar_input.pcs"))
				dispatchUrl = "/pages/pcs/pcsCalendar.jsp";
			else { 
				//dispatchUrl = "/pages/pcs/pcsCalendarNew.jsp";
				
				/*request.setAttribute("cellId", "CEL0000066");
				//request.setAttribute("mchId", "MCH0002057");
				request.setAttribute("date", "01-Dec-2013");
				request.setAttribute("shift", "SFT001");
				*/
				dispatchUrl = "/pages/pcs/pcsViewNew.jsp";
			}
		}
		
		if( action.equals("setFormActionMode.pcs")) {

			CommonMessage.debugMsg("shift"+ request.getParameter("mode"));
			String mode = request.getParameter("mode");

    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");        	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			pcsEntryBean.setFormActionMode(mode);
			httpSession.removeAttribute("PcsEntryServletPcsEntryBean");
			httpSession.setAttribute("PcsEntryServletPcsEntryBean",pcsEntryBean);
			CommonMessage.debugMsg("pcsEntryBean.setFormActionMode()"+ pcsEntryBean.getFormActionMode());

		}
		
		if (action.equals("pcsView_input.pcs") || action.equals("pcsViewTest_input.pcs")) 
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
            CommonMessage.debugMsg("formAuto    :"+formAuto);
            CommonMessage.debugMsg("shift==    :"+shift);
            CommonMessage.debugMsg("entryDate    :"+entryDate);
            CommonMessage.debugMsg("cellId    :"+cellId);
            
			request.setAttribute("baseDataStr", baseDataStr);
			if(action.equals("pcsView_input.pcs"))
				dispatchUrl = "/pages/pcs/pcsView.jsp";	
			else
				dispatchUrl = "/pages/pcs/pcsViewNew.jsp";	
			//request.setAttribute("reportType", "BDM");
			if (UIUtils.isValidKeyId(entryDate)) request.setAttribute("date", entryDate);
			if (UIUtils.isValidKeyId(shift)) 	 request.setAttribute("shift", shift);			
			if (UIUtils.isValidKeyId(factId))	request.setAttribute("factId", factId);
			if (UIUtils.isValidKeyId(sectId))	request.setAttribute("sectId", sectId);
			if (UIUtils.isValidKeyId(cellId))	request.setAttribute("cellId", cellId);
			if (UIUtils.isValidKeyId(mchId))	request.setAttribute("mchId", mchId);
			if (UIUtils.isValidKeyId(flid))	request.setAttribute("flid", flid);
			if (UIUtils.isValidKeyId(mode))	request.setAttribute("mode", mode);
			if (UIUtils.isValidKeyId(formLock))	request.setAttribute("formLock", formLock);
			if (UIUtils.isValidKeyId(formAuto))	request.setAttribute("formAuto", formAuto);
			
		}

		else if (action.equals("pcsEntryView_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the VIEW jsp");		
			String cellId = request.getParameter("cellId");			
			dispatchUrl = "/pages/pcs/pcsEntryView.jsp";	
			request.setAttribute("cellId", cellId);
		}
		else if (action.equals("pcsLossEntryGridmain_input.pcs")) 
		{
			/*String flid=request.getParameter("flid");
			String date=request.getParameter("date");
			request.setAttribute("flid", flid);
			request.setAttribute("date", date);*/
			RequestDispatcher rd = request.getRequestDispatcher("/pages/pcs/pcsLossEntryGrid.jsp");					
			rd.forward(request, response);
			
		}
		else if (action.equals("pcsLossEntryGridmain_getCol.pcs")) 
		{
			CommonMessage.debugMsg("pcsLossEntryGridmain_getCol");
			CommonFilter commonFilter = populateCommonFilter(request,"LossEntryCommonFilter",true);
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntryGridcolModel"));
			
		}
		else if (action.equals("pcsLossEntryGridmain_getData.pcs")) 
		{
			try {
				PrintWriter outt=response.getWriter();
				/*String rows = request.getParameter("rows");
	        	String page = request.getParameter("page");	
       		    String start = "1";
       		    String end = "100";
       		    if(page.equals("1"))
       		    {
       		    	
       		    }
       		    else
       		    {
       		     int rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
        		 int rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
        		 start = Integer.toString(rowStart);
        		 end = Integer.toString(rowEnd);
       		    }*/
				
				String fliterstr=request.getParameter("fliterstr");
				
		   String flid=null;
		   if(UIUtils.isValidKeyId(fliterstr))
			   flid=request.getParameter("flid");
		   else
		       flid=CommonFunctions.getLoginFlid(request);
		     
		 //  PrintWriter out = response.getWriter();
		   CommonMessage.debugMsg("FLID IS:"+flid);
		 ///  String totalCount=pcsEntryService.getTotalLossEntryData(flid);
		 //  CommonMessage.debugMsg(totalCount);
		   CommonFilter commonFilter=populateCommonFilter(request,"LossEntryCommonFilter",true);
		   
			/*if( Integer.parseInt(totalCount) > 0 ){
				int totalRows = 200;*/
				 GridParams gridParams =(GridParams) httpSession.getAttribute("LossEntry");
				 if( gridParams == null )
      			  gridParams = new GridParams(); 	  
				 FilterValues.populateGridParams(request,gridParams );  
      		  	httpSession.removeAttribute("LossEntry");
      		  	httpSession.setAttribute("LossEntry" ,gridParams);
				 List<String []> getLossEntry  = pcsEntryService.getLossEntryData(gridParams,commonFilter,flid);
				/*if(UIUtils.isValidKeyId(totalCount))
					totalRows = Integer.parseInt(totalCount);*/ //0 to 1
				net.sf.json.JSONObject LossJson = UIUtils.convertToJqGridTableObject(getLossEntry,request,0,0,gridParams.getTotalRecordCnt());
				httpSession.removeAttribute("LossEntry");
				outt.println(LossJson);
				//httpSession.removeAttribute("OtherLossEntry");
		}
	    catch(Exception e){
	      e.printStackTrace();	
	    }
		}
		
		 else if(action.equals("lossviewdelete_input.pcs")){
				CommonMessage.debugMsg("Inside the Lossviewdelete");
				RequestDispatcher rdr=request.getRequestDispatcher("/pages/lossdelete.jsp");
				rdr.forward(request,response);
			}


else if (action.equals("lossviewdelete_getCol.pcs")) 
			{
				CommonMessage.debugMsg("pcsLossEntryGridmain_getCol");
				CommonFilter commonFilter = populateCommonFilter(request,"LossEntryCommonFilter",true);
				PrintWriter out = response.getWriter();
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntryGridcolModel"));
				
			}
			else if (action.equals("lossviewdelete_getData.pcs")) 
			{
				try {
					PrintWriter outt=response.getWriter();
					
					String fliterstr=request.getParameter("fliterstr");
					
			   String flid=null;
			   if(UIUtils.isValidKeyId(fliterstr))
				   flid=request.getParameter("flid");
			   else
			       flid=CommonFunctions.getLoginFlid(request);
			   CommonMessage.debugMsg("FLID IS:"+flid);
			   CommonFilter commonFilter=populateCommonFilter(request,"LossEntryCommonFilter",true);

					 GridParams gridParams =(GridParams) httpSession.getAttribute("LossEntry");
					 if( gridParams == null )
	      			  gridParams = new GridParams(); 	  
					 FilterValues.populateGridParams(request,gridParams );  
	      		  	httpSession.removeAttribute("LossEntry");
	      		  	httpSession.setAttribute("LossEntry" ,gridParams);
					 List<String []> getLossEntry  = pcsEntryService.getLossEntryData(gridParams,commonFilter,flid);

					net.sf.json.JSONObject LossJson = UIUtils.convertToJqGridTableObject(getLossEntry,request,0,1,gridParams.getTotalRecordCnt());
					httpSession.removeAttribute("LossEntry");
					outt.println(LossJson);
			}
		    catch(Exception e){
		      e.printStackTrace();	
		    }
			} 
		
		else if(action.equals("pcsLossEntryGridmain_getExcel.pcs")){
			try{
		    httpSession=request.getSession(false);
		    String flid=CommonFunctions.getLoginFlid(request);
			CommonFilter commonFilter = populateCommonFilter(request,"LossEntryReport",false);
			commonFilter.setFlid(flid);
			String tableModel =  UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntryGridcolModel");
			
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			CommonMessage.debugMsg("names to be identify........."+tblJSONObj);
			tblJSONObj.put("title", "LossEntryReport");
			
			String formats = ExcelUtils.getFormat(request);
			Workbook wb=pcsEntryService.getExcelreport(commonFilter,tblJSONObj,formats);
			ExcelUtils.writeToResponse(response, wb, "LossEntryReport",formats);	
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		else if (action.equals("OtherLossEntry_input.pcs")) 
		{
			String flid=request.getParameter("flid");
			String date=request.getParameter("date");
			request.setAttribute("flid", flid);
			request.setAttribute("date", date);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/pcs/OtherLossEntry.jsp");					
			rd.forward(request, response);
			
		}
		else if (action.equals("file_clear.pcs")) {
			httpSession.removeAttribute(AdmUploadExcelServlet_filename);
		}else if (action.equals("file_upload.pcs")) 
		{
			PrintWriter out = response.getWriter();
			InputStream is = null;
	        FileOutputStream fos = null;	
	        String filename = request.getHeader("X-File-Name");
	        if(filename !=null){	        	
	        	filename = filename.replaceAll(" ","_").replaceAll("%20","_");
	        	CommonMessage.debugMsg("fileename: "+filename);
	        }
	        httpSession.removeAttribute(AdmUploadExcelServlet_filename);
			httpSession.setAttribute(AdmUploadExcelServlet_filename,filename);
	        try {
	            is = request.getInputStream();	
	            CommonMessage.debugMsg(realPath + " : file name :" + filename);
	            fos = new FileOutputStream(new File(realPath + filename));
	            IOUtils.copy(is, fos);   
	        } catch (FileNotFoundException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            out.print("{success: false}");
	            CommonMessage.debugMsg(" : file exception :");
	        } catch (IOException ex) {
	            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
	            CommonMessage.debugMsg(" : io exception :");
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
		else if(action.equals("file_save.pcs")){
			 saveOtherLossFile(request,response);
		 }
		else if (action.equals("OtherLossEntry_getCol.pcs")) 
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
				
				String [] colHeader = getOtherList.get(1);		// -- changing 2 to 1 	
				String [] colHeaderCond = getOtherList.get(0);  // changing 1 to 0
				
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
		else if (action.equals("OtherLossEntry_getData.pcs")) 
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
  			 	JSONObject OtherLossJson = UIUtils.convertToJqGridTableObject(getOtherList,request,2,0); 
  			 	out.println(OtherLossJson);
  			 	httpSession.removeAttribute("OtherLossEntry");
		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}else if(action.equals("OtherLossEntry_save.pcs")){
			updateOtherLoss(request,response);
		}
		else if(action.equals("functionalLoc.pcs"))
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
		
		else if (action.equals("pcsEntry_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the jsp");	
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			String prdId = request.getParameter("prdId");
						
			dispatchUrl = "/pages/pcs/pcsEntry.jsp";	
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("MchId", mchId);
			request.setAttribute("PrdId", prdId);
			
		}		
		else if (action.equals("pcsPopEntry_input.pcs") || action.equals("pcsPopTestEntry_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the jsp");	
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
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
			request.setAttribute("EntryDate", entryDate);
			request.setAttribute("Shift", shift);
			request.setAttribute("CellId", cellId);
			request.setAttribute("MchId", mchId);
			request.setAttribute("PrdId", prdId);
			request.setAttribute("SectionId", sectId);
			request.setAttribute("FactoryId", factId);
			
		}
		
		else if (action.equals("pcsLossEntry_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the  pcsLossEntry_input.pcs");	
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
			
			CommonMessage.debugMsg("EntryDate:"+entryDate+";cellId="+cellId);
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
			
		else if (action.equals("pcsResult_input.pcs")) 
		{
			CommonMessage.debugMsg("inside the pcsResult_input.pcs");	
			String dataStr = request.getParameter("dataStr");
						
			dispatchUrl = "/pages/pcs/pcsResult.jsp";	
			
			CommonMessage.debugMsg("dataStr:"+dataStr);
			request.setAttribute("dataStr", dataStr);
		}	
		else if (action.equals("pcsOperatordtls_input.pcs")) 
		{
			dispatchUrl = "/pages/pcs/operatorDetails.jsp";	
		}	
		else if (action.equals("pcsOperatordtlsNew_input.pcs")) 
		{
			dispatchUrl = "/pages/pcs/operatorDetailsNew.jsp";     	
		}
		
		else if(action.equals("pcsLoss_input.pcs")){
		//	UIUtils.forwardRequest(request, response, "/pages/pcs/pcsLossCapture.jsp");
			String flid=request.getParameter("flid");
			String date=request.getParameter("date");
			String mode=request.getParameter("mode");
			CommonMessage.debugMsg("Flid is:"+flid);
			request.setAttribute("flid", flid);
			request.setAttribute("date", date); 
			request.setAttribute("mode", mode);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/pcs/pcsLossCapture.jsp");
			rd.forward(request, response);
		}

		
		else if(action.equals("functionalLoc_Calendar.pcs")){
			
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

		else if(action.equals("functionalLoc_lossCapture.pcs")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachDisable(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			//if( formModes == FormModes.completion)
			//formModes = FormModes.create;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}				

		else if(action.equals("functionalLoc_NoEntry.pcs")){
			
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

		
		else if(action.equals("functionalLoc_Entry.pcs")){
			
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
		else if(action.equals("functionalLocation_Entry.pcs")){
			
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
		else if(action.equals("functionalLoc_LossEntry.pcs")){
			
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
		
		else if( action.equals("getIsPcsEnabled.pcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String condParam = request.getParameter("condParam");
				List<String[]> pcsEnabled  = new ArrayList<String[]>();
				CommonMessage.debugMsg("condParam"+condParam);				
				 pcsEnabled = pcsEntryService.getIsPcsEnabled(condParam);
				CommonMessage.debugMsg("pcsEnabled...."+pcsEnabled);
				JSONObject pcsData =new JSONObject();
				if(pcsEnabled!=null){
  			 	
				pcsData.put("enableShift", "Y");
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
}
else
{
	pcsData.put("enableShift", "N");
	out.print(pcsData);
}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				PrintWriter out = response.getWriter();
				e.printStackTrace();
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}				
		}		
		
		else if( action.equals("checkEquipmentFailure.pcs"))
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

		else if( action.equals("checkIsQtyLoss.pcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String lossId = request.getParameter("lossId");
				CommonMessage.debugMsg("lossId"+lossId);				
				List<String[]> isQtyLoss = pcsEntryService.checkIsQtyLoss(lossId);
				CommonMessage.debugMsg(isQtyLoss.size());
				
  			 	JSONObject qtyData =new JSONObject();
  			 	if (isQtyLoss.size()>0) {
	  			 	CommonMessage.debugMsg("isQtyLoss"+isQtyLoss.get(0)[0]);	  			 	
	  			 	qtyData.put("isQtyLoss", isQtyLoss.get(0)[0]);
  			 	}
  			 	else
  			 		qtyData.put("isQtyLoss", "N");
  			 	out.print(qtyData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getFieldsForPcs.pcs"))
		{
			try {
				CommonMessage.debugMsg("getFieldsForPcs  serv;et");
				PrintWriter out = response.getWriter();				
				String fieldsForPcs  = pcsEntryService.getFieldsForPcs();
				CommonMessage.debugMsg("hourlyEntry servt: " +fieldsForPcs );
				JSONObject plmData =new JSONObject();
				plmData.put("value", fieldsForPcs );
				out.print(plmData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	

		else if( action.equals("getPldRemarks.pcs"))
		{
			try {
				CommonMessage.debugMsg("getPldRemarks.pcs");
				PrintWriter out = response.getWriter();			
				String pldetailsId = request.getParameter("Pldetailsid");
				String detailTable = request.getParameter("detailTable");
				String remarks = pcsEntryService.getPldRemarks(detailTable, pldetailsId);
				
  			 	JSONObject allowData =new JSONObject();
  			 	CommonMessage.debugMsg("remarks"+remarks);
  			 	allowData.put("remarks", remarks.replace("<**>", ""));
  			 	allowData.put("pldetailsId", pldetailsId);
  			 	out.print(allowData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("getEntryAllowDates.pcs"))
		{
			try {
				PrintWriter out = response.getWriter();			
							
				List<String[]> allowDays = pcsEntryService.getEntryAllowDates();
				
  			 	JSONObject allowData =new JSONObject();
  			 	if (allowDays.size()>0) {
	  			 	CommonMessage.debugMsg("allowDays.get(0)"+allowDays.get(0)[0]);
	  			 	allowData.put("days", allowDays.get(0)[0]);
  			 	}
  			 	else
  			 		allowData.put("days", "1");
  			 	out.print(allowData);

			} catch (Exception e) {
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("combo_rawtype.pcs"))
		{
			try {						
				String productId = request.getParameter("productId");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> rawModel = pcsEntryService.getRawType(currentFilter ,  productId);
				UIUtils.writeComboBox(response,rawModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_Operator.pcs"))
		{
			try {						
				String sectId = request.getParameter("sectId");
				CommonMessage.debugMsg("sectId"+sectId);				
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getOperatorCombo(currentFilter, sectId);
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_shift.pcs"))
		{
			try {						
				String factId = request.getParameter("factId");
				CommonMessage.debugMsg("factId"+factId);		
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getShift(currentFilter ,factId);
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}
		
		else if(action.equals("comboEquipment.pcs")){
			try{
				ComboFilter cmb=UIUtils.fillComboFilter(request);
				List<ComboBox> list=pcsEntryService.getEquipmentName(cmb);
				UIUtils.writeComboBox(response, list, cmb);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		else if( action.equals("combo_productModel.pcs"))
		{
			try {						
				String factId = request.getParameter("factId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				CommonMessage.debugMsg("factId"+factId);		
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox> prdModel = pcsEntryService.getproductModelCombo(currentFilter, factId, mchId, entryDate);
				UIUtils.writeComboBox(response,prdModel,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_product.pcs"))
		{
			try {						
				String factId = request.getParameter("factId");
				String prdModelId = request.getParameter("prdModelId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				String rawMaterial = request.getParameter("rawMaterial");
				
				CommonMessage.debugMsg("factIdfactId"+factId);
				ComboFilter currentFilter = new ComboFilter();
				currentFilter=UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getproductCombo(currentFilter, factId, mchId,  prdModelId, entryDate,rawMaterial);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}

		
		else if( action.equals("combo_Loss.pcs"))
		{
			try {						

				String isQtyLoss = request.getParameter("isQtyLoss");
				String sectId = request.getParameter("sectId");
				
				//if (UIUtils.isValidKeyId(isQtyLoss))  
				//	condSql += " AND PLCM_PARENTID ='"+parentId+"'";
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  assembly = pcsEntryService.getLossCombo(currentFilter, isQtyLoss, sectId );
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		
		else if( action.equals("combo_LossNo.pcs"))
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

		
		else if( action.equals("combo_Wno.pcs"))
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

		else if( action.equals("combo_QTYPhenomena.pcs"))
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

		else if( action.equals("combo_cause.pcs"))
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
		else if( action.equals("combo_pcsLossCause.pcs"))
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
		else if( action.equals("combo_rootcause.pcs"))
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

		else if( action.equals("getEntryExistsDates.pcs"))
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
				CommonMessage.debugMsg("JSON : "+eedObj);
				out.print(eedObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		
		else if( action.equals("getHolidayDates.pcs"))
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
		

		else if( action.equals("getDateOeeValue.pcs"))
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
				CommonMessage.debugMsg("JSON : "+oeeObj);
				out.print(oeeObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			

		else if( action.equals("getPendingTime.pcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String detailTable = request.getParameter("detailTable");
				String pldetailsId = request.getParameter("pldetailsId");		

				List<String[]> pendingList = pcsEntryService.getPendingTime(detailTable,pldetailsId);				
				CommonMessage.debugMsg(pendingList.size());

  			 	JSONObject pendingData =new JSONObject();
  			 	
  			 	if (pendingList.size()>0) {
	  			 	CommonMessage.debugMsg("pendingList.get(0)"+pendingList.get(0)[0]);
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
		
		else if( action.equals("selectedProductModel.pcs"))
		{
			try {				
				
				PrintWriter out = response.getWriter();			
				String prdId = request.getParameter("prdId");
				String modelList = pcsEntryService.getSelectModel(prdId);			
				
  			 	JSONObject pendingData =new JSONObject();  			 	
  			 	if (UIUtils.isValidKeyId(modelList)) {
	  			 	CommonMessage.debugMsg("modelList(0)"+modelList);
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
		else if( action.equals("getLossRelatedValues.pcs"))
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
		
		else if( action.equals("getDetailIdValues.pcs"))
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
		else if(action.equals("pcsAncilliaryGrid_getCol.pcs"))
		{	 
			CommonMessage.debugMsg("pcsAncilliaryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsAncilliarycolModel"));

		}
		else if( action.equals("pcsAncilliaryGrid_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsAncilliaryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();

				String prodMchId = request.getParameter("prodMchId");
				String condParam = request.getParameter("condParam");
				
				List<String []> closedMsrList  = pcsEntryService.getClosedMsr(prodMchId, condParam);
				CommonMessage.debugMsg(closedMsrList.size());
  			 	JSONObject closedMsrData = UIUtils.convertToJqGridTableObject(closedMsrList,request,0,0);
  			 	CommonMessage.debugMsg(closedMsrData);
  			 	out.println(closedMsrData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		
	
		else if( action.equals("getMsrsDownTime.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("getMsrsDownTime");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();

				String msrNos = request.getParameter("msrNos");				
				
				List<String []> msrDownList  = pcsEntryService.getMsrsDownTime(msrNos);
				CommonMessage.debugMsg(msrDownList.size());

  			 	JSONObject msrDownData =new JSONObject();
  			 	
  			 	if (msrDownList.size()>0) {
	  			 	CommonMessage.debugMsg("isEF.get(0)"+msrDownList.get(0)[0]);
	  			 	msrDownData.put("downTime", msrDownList.get(0)[0]);
  			 	}
  			 	else
  			 		msrDownData.put("downTime", "");
  			 	out.print(msrDownData);
  			 	
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		
		else if( action.equals("checkIsOpenMsr.pcs"))
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
					CommonMessage.debugMsg("factId "+ factId+"shiftCode "+shiftCode);
					shift = pcsComplianceRptService.getShiftKeyid(factId, shiftCode);
				}
				String isClear = request.getParameter("isClear");
				CommonMessage.debugMsg("shift:"+shift); 
				List<String[]> isOpenMsr = pcsEntryService.checkIsOpenMsr(mchId, date, shift);
				JSONObject openMsrObj = new JSONObject();				
				JSONObject msrData =new JSONObject();
  			 	if (isOpenMsr.size()>0) {
	  			 	CommonMessage.debugMsg("isEF.get(0)"+isOpenMsr.get(0)[0]);
	  			 	msrData.put("isOpenMsr", isOpenMsr.get(0)[0]);
	  			 	msrData.put("shift",shift);
  			 	}
  			 	else
  			 		msrData.put("isOpenMsr", "");
				
				openMsrObj.put("msrData", msrData);
				CommonMessage.debugMsg("isClear"+isClear);
				if(UIUtils.isValidKeyId(isClear))
					openMsrObj.put("isClear", isClear);
				else
					openMsrObj.put("isClear", "Y");
				CommonMessage.debugMsg("JSON : "+openMsrObj);
				out.print(openMsrObj);				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		else if( action.equals("getMasterKeyid.pcs"))
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

		
		else if( action.equals("getLastShiftProduct.pcs"))
		{
			try {
				PrintWriter out = response.getWriter();
				String sectId = request.getParameter("sectId");
				String cellId = request.getParameter("cellId");
				String mchId = request.getParameter("mchId");
				String entryDate = request.getParameter("entryDate");
				String shift = request.getParameter("shift");
				List<String[]>  prevData = pcsEntryService.getLastShiftProduct(sectId, cellId, mchId,entryDate, shift);
				JSONObject plmData =new JSONObject();
				CommonMessage.debugMsg("size : "+prevData.size());
				if (prevData.size()>0 ) {		
					CommonMessage.debugMsg("0 : "+prevData.get(0)[0]);
					CommonMessage.debugMsg("1 : "+prevData.get(0)[1]);
					plmData.put("productId", prevData.get(0)[0]);
					plmData.put("wno", prevData.get(0)[1]);					
				}
				CommonMessage.debugMsg("plmData : "+plmData);
				out.print(plmData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
		

		else if( action.equals("getCurrentShift.pcs"))
		{
			try {					
				
				PrintWriter out = response.getWriter();				
				String factId = request.getParameter("factId");
				
				CommonMessage.debugMsg("System out in shift"+factId);
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
		
		else if( action.equals("getShiftKeyid.pcs"))
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
		
		else if (action.equals("getShiftStarEndTime.pcs")) {
		try {					
				
				PrintWriter out = response.getWriter();				
				String shiftId = request.getParameter("shiftId");
				
				List<String[]> shiftStrEnd = pcsEntryService.getShiftStarEndTime(shiftId);
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
		
		else if (action.equals("getShiftEndTime.pcs")) {
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
					
		
		else if( action.equals("getReasonLossNo.pcs"))
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
		

		else if( action.equals("getCalendarTime.pcs"))
		{
			try {						
				PrintWriter out = response.getWriter();
				
				String machineId = request.getParameter("machineId");
				String sectId = request.getParameter("sectId");
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("entryDate");
				
				String calendarTime   = pcsEntryService.getCalendarTime(machineId, plmasterId, sectId,entryDate);
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
				
		else if( action.equals("getAllTime.pcs"))
		{
			try {						
				PrintWriter out = response.getWriter();
				
				String machineId = request.getParameter("machineId");
				String sectId = request.getParameter("sectId");
				String plmasterId = request.getParameter("plmasterId");
				String entryDate = request.getParameter("entryDate");

				JSONObject plmData =new JSONObject();
				plmData= getAllTimeForMachine(machineId, sectId, plmasterId, entryDate);				
				out.print(plmData);

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}			
		
		else if( action.equals("getDetailTableName.pcs"))
		{
			try {
				PrintWriter out = response.getWriter();				
				String sectId = request.getParameter("sectId");				
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
		
		
		else if( action.equals("combo_Phenomena.pcs"))
		{
			try {						
				String lossId = request.getParameter("lossId");
				String cellId = request.getParameter("cellId");
				String flid = request.getParameter("flid");
				ComboFilter currentFilter = new ComboFilter();
				currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsEntryService.getPhenomenaCombo(currentFilter, lossId,cellId,flid);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}	
	
		else if( action.equals("combo_Cycletime.pcs"))
		{
			try {						
				
				String productId = request.getParameter("productId");
				String machineId = request.getParameter("machineId");
				String cellId = request.getParameter("cellId");
				String entryDate = request.getParameter("date");
				String sectId = request.getParameter("sectId");
				ComboFilter currentFilter = new ComboFilter();
				List<ComboBox>  assembly = pcsEntryService.getCycletimeCombo(currentFilter,cellId, machineId, productId, entryDate,sectId);
				UIUtils.writeComboBox(response, assembly,comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}		
		
		else if( action.equals("updatePldRemarks.pcs"))
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
			
			FormModes formModes = (FormModes)httpSession.getAttribute("PcsEntryFormMode");			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}	*/
		else if( action.equals("monthGrid_getCol.pcs") )
		{
			CommonMessage.debugMsg("monthGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "monthcolModel"));
		}
		else if( action.equals("monthGrid_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("monthGrid_getdata.pcs");
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
				CommonMessage.debugMsg(calendarList.size());
  			 	JSONObject calendarData = UIUtils.convertToJqGridTableObject(calendarList,request,0,0);
  			 	CommonMessage.debugMsg(calendarData);
  			 	out.println(calendarData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if( action.equals("productionGrid_getCol.pcs") )
		{
			try{
			CommonMessage.debugMsg("monthGrid_getdata.pcs");
			CommonFilter commonFilter  = new CommonFilter();
			commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
			PrintWriter out = response.getWriter();
			String factId = request.getParameter("factId");
			String shiftId = request.getParameter("shiftId");
			String sectId = request.getParameter("sectId");
			String entryDate = request.getParameter("entryDate");
			String cellId = request.getParameter("cellId");
			String mchId = request.getParameter("mchId");
			CommonMessage.debugMsg(factId +"-"+shiftId+"-"+"-"+entryDate+"-"+cellId+"--"+sectId);
			
			List<String []> pcsMasterList  = pcsEntryService.getParameters(sectId);
			List<String []> pcsMasterList2  = pcsEntryService.getProdLoss(shiftId,entryDate,sectId, cellId, mchId,factId);
			if(pcsMasterList2!=null){
				//JSONObject pcsMasterData = UIUtils.convertToJqGridTableObject(pcsMasterList,request,0,0);
				//JSONObject pcsMasterData = fillPCSGrid(pcsMasterList,pcsMasterList2,request);
				JSONObject pcsMasterData = fillPCS(pcsMasterList,pcsMasterList2,request);
				//httpSession.setAttribute("pcsMasterData", pcsMasterData);
				//CommonMessage.debugMsg("productionGrid_getCol size"+pcsMasterList.size());
				//CommonMessage.debugMsg("productionGrid_getCol size2"+pcsMasterList2.size());
				//JSONObject jsonObject = getTableModel(pcsMasterList,pcsMasterList2);
				//jsonObject.put("rowNumbers", false);
					//   jsonObject.put("pager", false);
				//   jsonObject.put("tableButton", true);
			    CommonMessage.debugMsg(pcsMasterData);
				out.println(pcsMasterData);
				

				//PrintWriter out = response.getWriter();
				//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "productioncolModel"));
			
			}
			
			
			}
			catch(Exception e)
			{
				 PrintWriter out = response.getWriter();
				 e.printStackTrace();
				CommonMessage.debugMsg("exception throws....");
				CommonMessage.debugMsg(e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Cannot Enter for Future Shift");
				out.print(err.toString());
				
			}
		}
		else if( action.equals("productionGrid_getData.pcs") )
		{
			try
			{	
				 PrintWriter out = response.getWriter();
				 JSONObject jsonObject = new JSONObject();
				 jsonObject = (JSONObject) httpSession.getAttribute("pcsEntryDataServlet");
				 CommonMessage.debugMsg(jsonObject);
	        	
				 out.println(jsonObject);
				/*CommonMessage.debugMsg("monthGrid_getdata.pcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String factId = request.getParameter("factId");
				CommonMessage.debugMsg("factId"+ factId);
				List<String []> bdMasterList  = pcsEntryService.getParameters(factId);
				CommonMessage.debugMsg(bdMasterList.size());
  			 	JSONObject bdMasterData = UIUtils.convertToJqGridTableObject(bdMasterList,request,0,0);
  			 	CommonMessage.debugMsg(bdMasterData);
  			 	out.println(bdMasterData);*/

			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if( action.equals("pcsEntryGridTest_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsEntrycolModelNew"));
		}
		else if( action.equals("pcsEntryGridTest_getData.pcs") )
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
				String mchId = request.getParameter("mchId");
				CommonMessage.debugMsg("machId"+ mchId);
				List<String []> sublossList  = pcsEntryService.getEntryGrid(entryDate,shift,sectId, mchId);
				CommonMessage.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonMessage.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}	
		else if( action.equals("pcsEntryGrid_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsEntrycolModel"));
		}
		else if( action.equals("pcsEntryGrid_getData.pcs") )
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
				String mchId = request.getParameter("mchId");
				CommonMessage.debugMsg("machId"+ mchId);
				List<String []> sublossList  = pcsEntryService.getEntryGrid(entryDate,shift,sectId, mchId);
				CommonMessage.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonMessage.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		

		
		else if( action.equals("pcsLossEntryGrid_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntrycolModelNew"));
		}
		else if( action.equals("pcsLossEntryGrid_getData.pcs") )
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
		else if( action.equals("pcsLossEntryGridNew_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntrycolModelNewGrid"));
		}
		else if( action.equals("pcsLossEntryGridNew_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsLossEntryGrid_getdata");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");
				CommonMessage.debugMsg("pldetailsid"+ pldetailsid);
				List<String []> sublossList  = pcsEntryService.getSubLossGridNew(pldetailsid);
				CommonMessage.debugMsg(sublossList.size());
  			 	JSONObject sublossData = UIUtils.convertToJqGridTableObject(sublossList,request,0,0);
  			 	CommonMessage.debugMsg(sublossData);
  			 	out.println(sublossData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}		

		else if( action.equals("pcsLossEntryGridNew_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsLossEntryGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsLossEntrycolModelNewGrid"));
		}
		else if( action.equals("pcsLossEntryGridNew_getData.pcs") )
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

		else if( action.equals("pcsResultGrid_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsResultGrid_getCol");
			PrintWriter out = response.getWriter();
			//out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsResultcolModel"));
			
			CommonFilter commonFilter  = new CommonFilter();
			commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);			
			String entryDate = request.getParameter("date");
			String shift = request.getParameter("shift");
			String cellId = request.getParameter("cellId");
			String sectId = request.getParameter("sectId");
			String mchId = request.getParameter("mchId");	
			CommonMessage.debugMsg("entryDate"+ entryDate);
			List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
			CommonMessage.debugMsg(pcsResultList.size());
			
			JSONObject jsonObject = getResultTableModel(pcsResultList);
			httpSession.removeAttribute("pcsResultColModel");
	 	    httpSession.setAttribute("pcsResultColModel",jsonObject);
	 	   CommonMessage.debugMsg("jsonObject"+jsonObject);
	 	   out.println(jsonObject);
		}
		
		else if( action.equals("pcsResultGrid_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsResultGrid_getData.pcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				String mchId = request.getParameter("mchId");	
				CommonMessage.debugMsg("entryDate"+ entryDate);
				List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
				CommonMessage.debugMsg(pcsResultList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsResultList,request,0,0);
  			 	CommonMessage.debugMsg(pcsResultData);
  			 	out.println(pcsResultData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}			

		
		else if(action.equals("pcsLoss_getCol.pcs")){
			PrintWriter out = response.getWriter();
			JSONObject jsonObject = null;
			List<String[]> lossgrid = null;
			
			try {
				String flid = request.getParameter("flid");
				
				String date = CommonFunctions.pg_getDate(request.getParameter("fromdate"));
				String todate = CommonFunctions.pg_getDate(request.getParameter("todate"));
				
				String shiftid = request.getParameter("shiftid");
				
				lossgrid = pcsEntryService.getPcsLossCaptureGrid(flid, date,todate ,shiftid);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 jsonObject = getTableModel(lossgrid);
		
		
			out.println(jsonObject);
			
		}
		else if(action.equals("pcsLoss_getData.pcs")){
			try {

				String flid = request.getParameter("flid");
				String date = request.getParameter("fromdate");
				String Todate = request.getParameter("todate");
				String shiftid = request.getParameter("shiftid");
				
				List<String[]> lossgrid = pcsEntryService.getPcsLossCaptureGrid(flid, date,Todate ,shiftid);

				PrintWriter out = response.getWriter();

				JSONObject lossgridmod = UIUtils.convertToJqGridTableObject(lossgrid, request, 1,0);
					out.println(lossgridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		

		
		
		else if( action.equals("getTotalResult.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("getTotalResult.pcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();				
				String entryDate = request.getParameter("date");
				String shift = request.getParameter("shift");
				String cellId = request.getParameter("cellId");
				String sectId = request.getParameter("sectId");
				//String mchId = request.getParameter("mchId");	
				String mchId = "";
				CommonMessage.debugMsg("entryDate"+ entryDate);
				List<String []> pcsResultList  = pcsEntryService.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
				CommonMessage.debugMsg(pcsResultList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsResultList,request,0,0);
  			 	CommonMessage.debugMsg("pcsResultData"+pcsResultData);
				httpSession.removeAttribute("PcsEntryServletPcsResultList");
				httpSession.setAttribute("PcsEntryServletPcsResultList",pcsResultList);
  			 	
  			 	out.println(pcsResultData);

			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}	
		else if( action.equals("getLineRejectionLoss.pcs") )
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
	
		
		else if( action.equals("pcsEmployeeGrid_getCol.pcs") )
		{
			CommonMessage.debugMsg("pcsEmployeeGrid_getCol");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsColModel", "pcsEmployeecolModelNew"));
		}
			
		
		else if( action.equals("pcsEmployeeGrid_getData.pcs") )
		{
			try
			{	
				CommonMessage.debugMsg("pcsEmployeeGrid_getData.pcs");
				CommonFilter commonFilter  = new CommonFilter();
				commonFilter= 	FilterValues.getCommonFilters(request, commonFilter);
				PrintWriter out = response.getWriter();
				String pldetailsid = request.getParameter("pldetailsid");	
				CommonMessage.debugMsg("entryDate"+ pldetailsid);
				List<String []> pcsEmpList  = pcsEntryService.getEmployeeGrid (pldetailsid);
				CommonMessage.debugMsg(pcsEmpList.size());
  			 	JSONObject pcsResultData = UIUtils.convertToJqGridTableObject(pcsEmpList,request,0,0);
  			 	CommonMessage.debugMsg(pcsResultData);
  			 	out.println(pcsResultData);
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}	
		
		
		else if( action.equals("pcsentry_save.pcs")) { 
			CommonMessage.debugMsg(" inside save " );
			savePcsEntry(request,response);					
		}
		else if( action.equals("pcsentrynew_save.pcs")) { 
			CommonMessage.debugMsg(" inside save " );
			savePcsEntryNew(request,response);					
		}
		
		else if( action.equals("noPlan_save.pcs")) { 
			CommonMessage.debugMsg(" inside no plan save" );
			String from=request.getParameter("from");
			CommonMessage.debugMsg("from view:"+from);
			httpSession.removeAttribute("from");
			httpSession.setAttribute("from", from);
			noPlanSave(request,response);					
		}
		

		else if( action.equals("pcsLossEntry_save.pcs")) { 
			CommonMessage.debugMsg(" inside save " );
			savePcsLossEntry(request,response);					
		}
		else if( action.equals("pcsLossEntryNew_save.pcs")) { 
			CommonMessage.debugMsg(" inside save " );
			savePcsLossEntryNew(request,response);					
		}
		
		else if( action.equals("frmPcsLoss_save.pcs")) { 
			CommonMessage.debugMsg(" inside save frmPcsLoss_save" );
			savePcsLossCapture(request,response);					
		}
		

		else if( action.equals("pcsEntry_delete.pcs"))
		{
			CommonMessage.debugMsg(" inside delete pcsentry delete " );			
			deletePcsEntry(request,response);
		}		

		else if( action.equals("pcsEmployee_save.pcs")) { 
			CommonMessage.debugMsg(" inside save " );
			savePcsEmployee(request,response);					
		}

		else if( action.equals("pcsEmployee_delete.pcs"))
		{
			CommonMessage.debugMsg(" inside delete pcsentry delete " );			
			deletePcsEmployee(request,response);
		}		
		

		else if( action.equals("pcsLossEntry_delete.pcs"))
		{
			CommonMessage.debugMsg(" inside delete pcs Loss entry delete " );			
			deletePcsLossEntry(request,response);
		}		
		
		else if( action.equals("pcsLossEntryNew_delete.pcs"))
		{
			CommonMessage.debugMsg(" inside pcsLossEntryNew_delete " );			
			deletePcsLossEntryNew(request,response);
		}

		else if( action.equals("pcsLossEntryITC_delete.pcs"))
		{
			CommonMessage.debugMsg(" inside pcsLossEntryITC_delete " );			
			deletePcsLossEntryITC(request,response);
		}

	}
    
	private void updateOtherLoss(HttpServletRequest request,
			HttpServletResponse response) throws BusinessApplicationExceptions, Exception {
		PrintWriter out = response.getWriter();
		try{
			PcsTlOtherlossentry pcsTlOtherlossentry= new PcsTlOtherlossentry();
			pcsTlOtherlossentry = (PcsTlOtherlossentry) UIUtils.setBeanProperties((Object) pcsTlOtherlossentry,request);
			String Otherloss = request.getParameter("selectedrowIDs");
			CommonMessage.debugMsg("Otherlosslist : "+Otherloss);
			String update="";
			List<PcsTlOtherlossentry> otherLossList = null;   
			JSONArray otherLossJson = null;
			if (UIUtils.isValidKeyId(Otherloss)) 
			{
				otherLossJson  = JSONArray.fromString(Otherloss);
				otherLossList = (List<PcsTlOtherlossentry>) UIUtils.convertJSONArrToList(pcsTlOtherlossentry,otherLossJson );
				CommonMessage.debugMsg("otherLossList.size() : "+otherLossList.size());
				CommonMessage.debugMsg("getOlseLossvalue  "+otherLossList.get(0).getOlseLossvalue()+"getOlseKeyid : "+otherLossList.get(0).getOlseKeyid());
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
			CommonMessage.debugMsg("excelFileName   "+excelFileName);
			PcsTlOtherlossentry pcsTlOtherlossentry= new PcsTlOtherlossentry();
			pcsTlOtherlossentry =(PcsTlOtherlossentry) UIUtils.setBeanProperties((Object)pcsTlOtherlossentry,request);
			pcsTlOtherlossentry.setOlseElementid(elementId);
			if("Y".equals(fileSave))
				pcsTlOtherlossentry.setExcelName(excelFileName1);
			else
				pcsTlOtherlossentry.setExcelName("");
			String savemsg;	
			CommonMessage.debugMsg("pcsTlOtherlossentry  "+pcsTlOtherlossentry.getOlseFlid());
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
			CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
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
    	CommonMessage.debugMsg("machKeyId=="+machKeyId);
    	CommonMessage.debugMsg("sectId=="+sectId);
    	
    	if( httpSession != null && user != null)
    	{

    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

	    	CommonMessage.debugMsg("delete query exe");
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
				
				CommonMessage.debugMsg("deleteResult"+ deleteResult);
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
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
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
	
	private JSONObject getAllTimeForMachine(String machineId, String sectId, String plmasterId, String entryDate) throws Exception {
		
		
		List<String[]> allTime   = pcsEntryService.getAllTimes(machineId, plmasterId, sectId,entryDate);
		
		String asstTime = null;
		String prodTime= null;
		String lossTime= null;
		String unreportTime= null;
		
		//CommonMessage.debugMsg("allTime.size"+allTime.size());
		
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
	    		CommonMessage.debugMsg("time==="+time);
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
    	String sectId = request.getParameter("sectId");
    	String lossId = request.getParameter("lossId");
    	String lossVal = request.getParameter("lossVal");
    	
    	if( httpSession != null && user != null)
    	{
    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

    		
	    	CommonMessage.debugMsg("delete query exe");
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
				
				CommonMessage.debugMsg("deleteResult"+deleteResult);
				
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
				
				CommonMessage.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
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
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}
	
	private void deletePcsLossEntryNew(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String PlrkKeyid = request.getParameter("PlrkKeyid");    	
    	String Pldetailsid = request.getParameter("Pldetailsid");
    	String sectId = request.getParameter("sectId");
    	String lossId = request.getParameter("lossId");
    	String lossVal = request.getParameter("lossVal");
    	
    	if( httpSession != null && user != null)
    	{
    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

    		
	    	CommonMessage.debugMsg("delete query exe");
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
				
				CommonMessage.debugMsg("deleteResult"+deleteResult);
				
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
				
				CommonMessage.debugMsg("..factId="+factId+"..entryDate="+entryDate+"..entryDate="+entryDate
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
				
				CommonMessage.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
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
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}
	

/*	private void deletePcsLossEntryITC(HttpServletRequest request,
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
    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

    		
	    	CommonMessage.debugMsg("delete query exe");
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
				
				CommonMessage.debugMsg("deleteResult"+deleteResult);
				
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
				
				CommonMessage.debugMsg("..factId="+factId+"..entryDate="+entryDate+"..entryDate="+entryDate
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
				
				CommonMessage.debugMsg("plmData.get(unreportTime)==="+plmData.get("unreportTime"));
				
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
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());

			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Deleted");
				out.print(err.toString());
			}    	
    	}
	}*/
	
	private void deletePcsLossEntryITC(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	
if( httpSession != null && user != null)
  {	
		String PlrkKeyid = request.getParameter("PlrkKeyid");    	
		String Pldetailsid = request.getParameter("Pldetailsid");
		String sectId = request.getParameter("sectId");
		
		PcsTlMst existPcsTlMst = (PcsTlMst)httpSession.getAttribute("newSession");
		
		PcsTlMst newPcsTlMst = new PcsTlMst();
		PcsTlDtl newPcsTlDtl = new PcsTlDtl();
		PcsTlWorkorderlink newPcsTlWorkorderlink = new PcsTlWorkorderlink();
		PcsTlLossreasonlink newPcsTlLossreasonlink = new PcsTlLossreasonlink();
		
		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
		newPcsTlMst =(PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);	   
		 
	try {				
		existPcsTlMst = pcsEntryService.deletePcsLossEntryITCNew(PlrkKeyid,Pldetailsid, sectId);										
		//httpSession.setAttribute(existPcsTlMst.getPrlmKeyid(), existPcsTlMst);
		//httpSession.setAttribute("GenTlMomeeeting", existPcsTlMst);
	
				
		JSONObject mode = new JSONObject();
		JSONObject persistentData = new JSONObject(); 
		persistentData.put("PrlmKeyid",existPcsTlMst.getPrlmKeyid());
		
		JSONObject forwardData = new JSONObject();
					
		JSONObject successData = new JSONObject();
		CommonMessage.debugMsg("SuccessData");
		successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		successData.put("keyId", existPcsTlMst.getPrlmKeyid());
		JSONObject returnData = new JSONObject();						
		returnData.put("successData", successData);
		returnData.put("formClear", true);	
		CommonMessage.debugMsg("successData"+successData);
		
		out.print(returnData.toString());
	}
	catch(BusinessApplicationExceptions e)
	{
		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "Momeeting");
		out.print(errMessage.toString());
	}
	catch(Exception e)
	{
		e.printStackTrace();
		JSONObject err = new JSONObject();
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
		//CommonMessage.debugMsg(colHeader.length+mchArr.length);
		String[] colHeader2 = new String[colHeader.length+mchArr.length];
		//CommonMessage.debugMsg("lENGTH : "+colHeader2.length);
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
			//CommonMessage.debugMsg(k + "- "+colHeader2[k]);	
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
	public static JSONObject fillPCS(List<String[]> dataArrayList,List<String[]> dataArrayList2,HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession(false);
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		//JqGridColModel jqGridColModel = new JqGridColModel();
		String [] colHeader = dataArrayList.get(0);
		CommonMessage.debugMsg("colHeader size"+colHeader.length);
		String[] mchArr = new String[dataArrayList2.size()];		
		String[] colHeader2 = new String[colHeader.length+(mchArr.length+1)];
		CommonMessage.debugMsg("colHeader2 size"+colHeader2.length);
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
		CommonMessage.debugMsg("dataArrayList.size()"+dataArrayList.size());
		//tableDataObject.put("records", totalRecords); //total records
		tableDataObject.put("records", (dataArrayList.size()+4)); //total records
		tableDataObject.put("pageRowCount", 150); 
		
		JSONArray rowArr = new JSONArray(); 	    	
  	   
       
		int lossCount = colHeader.length;
		
		int rowId = 0;
		String[][] pcsDatas = populatePcsDispData(dataArrayList,dataArrayList2);
		int chkFlag = -1;
		//CommonMessage.debugMsg("pcsDatas size"+pcsDatas.length);
		for (int headers=0; headers <= pcsDatas.length-1; headers++) {
			for (int h = 0; h <=pcsDatas[headers].length-1; h++) {
				if(headers==0)
				{
					if(pcsDatas[headers][h].equals(PCSConstants.MCHNO))
						chkFlag = 0;
					if(chkFlag >= 0 && !pcsDatas[headers][h].equals(PCSConstants.TOT))
					{
						//CommonMessage.debugMsg(pcsDatas[headers][h] + " -> "+chkFlag);
						chkFlag=chkFlag+3;
					}
				}
			}
		}
		if(chkFlag >=0)
			chkFlag = chkFlag-3;
		//CommonMessage.debugMsg(colHeader.length+(mchArr.length+1)+" : colHeader.length+(mchArr.length+1 ");
		//CommonMessage.debugMsg((colHeader.length+(mchArr.length+1))+" : (colHeader.length+(mchArr.length+1)) ");
		String[] colHeader3 = new String[(colHeader.length+(mchArr.length+1))+chkFlag];
		CommonMessage.debugMsg(colHeader3.length+" : Length ");
		for (int i=0; i <= pcsDatas.length-1; i++) {
			JSONObject rowObj =new JSONObject();
			JSONArray cell=new JSONArray();
			CommonMessage.debugMsg("lENGTH OF ARR : "+pcsDatas[i].length);
			int colFlag = pcsDatas[i].length-1;
			if(chkFlag >=0)
				colFlag += chkFlag;
			for (int j = 0; j <=colFlag; j++) {
			    //CommonMessage.debugMsg( i + " : " +j + " ---->"+pcsDatas[i][j]);
			    JqGridColModel jqGridColModel = new JqGridColModel();
			    if(i==0)
			    {		
			    	if(j<=pcsDatas[i].length-1)
			    		colHeader3[j] = pcsDatas[i][j];
			    	else
			    		colHeader3[j] = "";
				    //CommonMessage.debugMsg( i + " : " +pcsDatas[i][j] + " ---->"+j);
				    CommonMessage.debugMsg(colHeader3[j] + " : "+j);
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
				    	//	CommonMessage.debugMsg("Total Column dslksdllsd");
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
				    				CommonMessage.debugMsg(pcsDatas[i][k] + " is not a valid decimal number");
				    			}
				    		}
				    		
				    		List<String []> pcsResultList  = (List<String []>)httpSession.getAttribute("PcsEntryServletPcsResultList");
				    		
				    		/*CommonMessage.debugMsg("pcsResultList.get(6)[2].toString()"+pcsResultList.get(6)[2].toString());
				    		CommonMessage.debugMsg("pcsResultList.get(7)[2].toString()"+pcsResultList.get(7)[2].toString());
				    		CommonMessage.debugMsg("pcsResultList.get(8)[2].toString()"+pcsResultList.get(8)[2].toString());
				    		CommonMessage.debugMsg("pcsResultList.get(9)[2].toString()"+pcsResultList.get(9)[2].toString());
				    		
				    		CommonMessage.debugMsg("i="+i);
				    		CommonMessage.debugMsg("pcsConstants.rownum_OEE"+pcsConstants.rownum_OEE);
				    		*/
				    	/*	if (i == pcsConstants.rownum_OEE || i == pcsConstants.rownum_ROA 
				    				|| i == pcsConstants.rownum_ROP || i == pcsConstants.rownum_ROQ) { 
				    			total = total /cnt;				    			
				    		}
				        */
				    		if (i == pcsConstants.rownum_ROA) {
				    			CommonMessage.debugMsg("rownum_ROA==="+pcsConstants.rownum_ROA);
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
				    		//CommonMessage.debugMsg("total==="+total.toString());
				    		
				    		NumberFormat df = DecimalFormat.getInstance();
				    		df.setMinimumFractionDigits(2);
				    		df.setMaximumFractionDigits(1);
				    		//df.setRoundingMode(RoundingMode.UP);
				    		
				    		//total = Float.parseFloat(String.format("%.2f", total));
				    		
				    		//CommonMessage.debugMsg(df.format(total));				    		
				    		//CommonMessage.debugMsg("Total values s"+ df.format(total));
				    		
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
        CommonMessage.debugMsg("Table Model ::::::::: "+tableModel);
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
			CommonMessage.debugMsg(i + " : "+row[i]);
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
		
		
		CommonMessage.debugMsg(tableModel   + "..................");
		return tableModel;
	}
 
	
	private static String[][] populatePcsDispData(List<String[]> configParamList, List<String[]> dataList){
		int  lossParmaCol = 9;
		
		PCSConstants pcsConstants = new PCSConstants(configParamList,lossParmaCol);
		
		String [][] pcsData = new String[ configParamList.size()+4 ][ (configParamList.get(0).length+1) + dataList.size() ];
		CommonMessage.debugMsg("Length : "+configParamList.size());
		CommonMessage.debugMsg("Length : "+dataList.size());
		int dataSize = dataList.size();
		int rowIndex = 0;
		int dataColIndex = 0;
		int subLossRow;
		
		for( String [] configRow : configParamList ){
			//CommonMessage.debugMsg("len : "+configRow.length);
			if ( rowIndex == 1) rowIndex++; 
			if ( rowIndex == 2) rowIndex++;// || rowIndex == 2 || rowIndex == 3)
			if ( rowIndex == 3) rowIndex++;
			if ( rowIndex == 4) rowIndex++;
			for( int j = 0; j < configRow.length ;j++ ){
				//CommonMessage.debugMsg("12345"+rowIndex +"-"+ j + " : "+ configRow[ j ]);					
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
			//CommonMessage.debugMsg("dataList.get(0).length"+dataList.get(0).length);
			
			for (int t = 0;t<dataList.get(0).length;t++)
			{
				//CommonMessage.debugMsg("dataRow[t]"+dataRow[t]);
				if (UIUtils.isValidKeyId(dataRow[t]))
				 if (dataRow[t].equals("0"))
					 dataRow[t]=" ";
			}
		}
		
		for( String [] dataRow : dataList ){
		
			//CommonMessage.debugMsg( headerColIndex + " : "+pcsData[ 0 ][  headerColIndex ] +" :"+dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ]);
			//CommonMessage.debugMsg( "machine no"+ dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ]);
			
			//setPcsRowwiseRecords(pcsConstants,pcsData ,headerColIndex,colIndexStart,dataRow);
			pcsData[ 0 ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_MACHINENO ];
			
			//CommonMessage.debugMsg( headerColIndex + " : "+pcsData[ 0 ][  headerColIndex ]);
			pcsData[ pcsConstants.rownum_MACHINEID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_MACHINEID ];
			pcsData[ pcsConstants.rownum_PLDETAILSID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PLDETAILSID ];
			pcsData[ pcsConstants.rownum_PLMASTERID ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PLMASTERID ];
			pcsData[ pcsConstants.rownum_PRDMKEY ][  headerColIndex ] = dataRow [ PCSConstants.PLDTL_CONST_PRDM_KEYID ];
			headerColIndex++;
			//CommonMessage.debugMsg( "pcsData11111"+pcsData[ 0 ][  headerColIndex++ ]);
			
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
			
			//CommonMessage.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]"+dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ]) && pcsConstants.rownum_THEORITICALCYCLETIME >= 0 )
				pcsData[ pcsConstants.rownum_THEORITICALCYCLETIME][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_THEORITICALCYCLETIME ];

			//CommonMessage.debugMsg("pcsConstants.rownum_THEORITICALCYCLETIME]"+pcsConstants.rownum_THEORITICALCYCLETIME);
			//CommonMessage.debugMsg("colIndexStart]"+colIndexStart);
			
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
			
			//CommonMessage.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY ]"+dataRow[ PCSConstants.PLDTL_CONST_PRODUCEDQTY]);
			//CommonMessage.debugMsg("pcsConstants.rownum_PRODUCEDQTY]"+pcsConstants.rownum_PRODUCEDQTY);
			//CommonMessage.debugMsg("colIndexStart]"+colIndexStart);
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ]) && pcsConstants.rownum_DEFECTNREWORK >= 0 )
				pcsData[ pcsConstants.rownum_DEFECTNREWORK ][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_DEFECTSANDREWORKLOSS_ML ];
			
			if( UIUtils.isValidKeyId(dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ]) && pcsConstants.rownum_REJECTEDQTY >= 0 )
				pcsData[ pcsConstants.rownum_REJECTEDQTY][ colIndexStart ] = dataRow[ PCSConstants.PLDTL_CONST_REJECTEDQTY ];
			
			//CommonMessage.debugMsg("pcsConstants.rownum_REWORKQTY]"+pcsConstants.rownum_REWORKQTY);
			//CommonMessage.debugMsg("colIndexStart]"+colIndexStart);
			//CommonMessage.debugMsg("dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ]"+dataRow[ PCSConstants.PLDTL_CONST_REWORKQTY ]);
			
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
				//CommonMessage.debugMsg("["+ij+"]" +"["+ij+"]----------->"+pcsData[ij][kl]);
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
		CommonMessage.debugMsg(dataRow[ PCSConstants.PLDTL_CONST_CAVITYAVAILABLE ]);
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
		CommonMessage.debugMsg("Pcl MAIN");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	
    	String isOpenMst = request.getParameter("isOpenMst");
    	
    	if( httpSession != null && user != null)
    	{    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
    		CommonMessage.debugMsg("isOpenMst"+isOpenMst);
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));
			PcsTlMst existPcsTlMst = (PcsTlMst)httpSession.getAttribute("PcsTlMst");
			
    		PcsTlMst newPcsTlMst = new PcsTlMst();
    		PcsTlDtl newPcsTlDtl = new PcsTlDtl();
    		PcsTlWorkorderlink newPcsTlWorkorderlink = new PcsTlWorkorderlink();
    		PcsTlLossreasonlink newPcsTlLossreasonlink = new PcsTlLossreasonlink();
    		
    		PcsTlMst oldPcsTlMst = new PcsTlMst();
    		
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			pcsEntryBean.setIsOpenMsr(isOpenMst);
				

			CommonMessage.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
			try{

				if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPtwokeyid(request.getParameter("Ptwokeyid"));
	    		
	    		CommonMessage.debugMsg("Plmasterid=="+request.getParameter("Plmasterid"));	    		 
	    		CommonMessage.debugMsg("Pldetailsid==="+request.getParameter("Pldetailsid"));
	    		
	    		newPcsTlMst =(PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);
	    		
	    		newPcsTlWorkorderlink =(PcsTlWorkorderlink)UIUtils.setBeanProperties((Object)newPcsTlWorkorderlink,request);
	    		
	    		CommonMessage.debugMsg("Second");
	    		newPcsTlDtl =(PcsTlDtl)UIUtils.setBeanProperties((Object)newPcsTlDtl,request);
	    		
	    		newPcsTlLossreasonlink =(PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		CommonMessage.debugMsg("third");
	    		
	    		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
	    		//newPcsTlMst.setPrlmEntryby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
	    		
	    		newPcsTlDtl.setCreatedby(user.getUsrm_ccno());
	    		newPcsTlWorkorderlink.setPtwoCreatedby(user.getUsrm_ccno());
	    		
	    		CommonMessage.debugMsg("entnjbjb n "+newPcsTlMst.getPrlmEntrydate());
	    		
	    	/*	if( newPcsTlDtl != null)
	    			newPcsTlMst.getPcsDetail().add(newPcsTlDtl);	    		
	    		if( newPcsTlLossreasonlink != null)
	    			newPcsTlMst.getPcslosslink().add(newPcsTlLossreasonlink);
	    	*/
	    		
	    		
	    		CommonMessage.debugMsg("before check");
	    		String prodQty = newPcsTlDtl.getProducedqty();
	    		String backlog = newPcsTlDtl.getBacklogqty();
	    		if (!UIUtils.isValidKeyId(prodQty)) prodQty="0";	    		
	    		CommonMessage.debugMsg("prodQty"+prodQty);
	    		if (!UIUtils.isValidKeyId(backlog)) backlog="0";	   
	    		CommonMessage.debugMsg("backlog.............."+backlog);
	    		
	    		String cavityUsed = newPcsTlDtl.getCavityused();
	    		if (!UIUtils.isValidKeyId(cavityUsed)) cavityUsed="0";
	    		CommonMessage.debugMsg("cavityUsed"+cavityUsed);
	    		String actTime = newPcsTlDtl.getActualcycletime();
	    		if (!UIUtils.isValidKeyId(actTime)) actTime="0";
	    		CommonMessage.debugMsg("actTime"+actTime);
	    		
	    		String availTimeStr   = pcsEntryService.getCalendarTime(newPcsTlDtl.getMachineid(), request.getParameter("Plmasterid"), newPcsTlMst.getPrlmSectionid(),newPcsTlMst.getPrlmEntrydate());
	    		CommonMessage.debugMsg("availTime"+availTimeStr);
	    		
	    		Float availTime = Float.parseFloat(availTimeStr);
	    		if (Float.parseFloat(availTimeStr) < 0)
	    			availTime= (float) 0.0;
	    		
	    		String oldTime=request.getParameter("oldTime");	    		
	    		CommonMessage.debugMsg("oldTime="+oldTime);
	    		
	    		if (request.getParameter("saveMode").equals("Update"))
	    			availTime = availTime + Float.parseFloat(oldTime);
	    		
	    		CommonMessage.debugMsg("availTime="+availTime);
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
	    		
	    		CommonMessage.debugMsg("planQty="+planQty);
	    		
	    		newPcsTlDtl.setPlannedqty(planQty.toString());
	    		newPcsTlDtl.setCalendartime(availTime.toString());
	    		
	    		Float prodTime = ( Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
	    		CommonMessage.debugMsg("prodTime"+prodTime);
	    			    		
	    		
	    		CommonMessage.debugMsg("Math.floor(prodTime)====="+Math.floor(prodTime));
	    		CommonMessage.debugMsg("Math.floor(availTime)===="+Math.floor(availTime));
	    			
	    		if (prodTime > availTime)
					throw new BusinessApplicationExceptions("productionTime_Greate");	    			
	    			
	    		
	    		if (Float.parseFloat(cavityUsed) > Float.parseFloat(cavityAvail))
	    			throw new Exception(" 'Cavity Used' Can not be greater than Cavity Available");
	    			
	    		if (Float.parseFloat(cavityUsed) < 1)
	    			throw new Exception(" 'Cavity Used' Should be atleast 1");
	    		
	    		String actTimePct = pcsEntryService.getActTimePct(); 
	    			//dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE", "ACTIMEPCT");
				int actPct = Integer.parseInt(actTimePct) ;
				CommonMessage.debugMsg("(actPct %"+actPct );
				
	    		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
	    		CommonMessage.debugMsg("(time25%"+timePct);
	    		Float timepctVal = (float)0;
	    		
	    		
	    		NumberFormat df = DecimalFormat.getInstance();
	    		df.setMinimumFractionDigits(2);
	    		df.setMaximumFractionDigits(2);
	    		df.setRoundingMode(RoundingMode.UP);
	    		
	    		
	    		timepctVal = (Float.parseFloat(theoTime) + timePct);
	    		CommonMessage.debugMsg("(timepctVal"+timepctVal);
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
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
			
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
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
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
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
			CommonMessage.debugMsg("isOpenMst : "+isOpenMst);
			CommonMessage.debugMsg("savemode : "+saveMode);
			
			PcsTlMst existPcsTlMst = (PcsTlMst)httpSession.getAttribute("PcsTlMst");
			
    		PcsTlMst newPcsTlMst = new PcsTlMst();
    		PcsTlDtl newPcsTlDtl = new PcsTlDtl();
    		PcsTlWorkorderlink newPcsTlWorkorderlink = new PcsTlWorkorderlink();
    		PcsTlLossreasonlink newPcsTlLossreasonlink = new PcsTlLossreasonlink();		
    		
    		
			if (saveMode.equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			pcsEntryBean.setIsOpenMsr(isOpenMst);
				

			CommonMessage.debugMsg("formBean Mode : "+pcsEntryBean.getFormMode());
			
			try{

				if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPtwokeyid(request.getParameter("Ptwokeyid"));
	    		
	    		CommonMessage.debugMsg("Plmasterid : "+request.getParameter("Plmasterid"));	    		 
	    		CommonMessage.debugMsg("Pldetailsid : "+request.getParameter("Pldetailsid"));
	    		
	    		newPcsTlMst =(PcsTlMst)UIUtils.setBeanProperties((Object)newPcsTlMst,request);	    		
	    		newPcsTlWorkorderlink =(PcsTlWorkorderlink)UIUtils.setBeanProperties((Object)newPcsTlWorkorderlink,request);	    		
	    		newPcsTlDtl =(PcsTlDtl)UIUtils.setBeanProperties((Object)newPcsTlDtl,request);	    		
	    		newPcsTlLossreasonlink =(PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		
	    		
	    		newPcsTlMst.setPrlmCreatedby(user.getUsrm_ccno());
	    		//newPcsTlMst.setPrlmEntryby(user.getUsrm_ccno());
	    		newPcsTlMst.setPrlmUpdatedby(user.getUsrm_ccno());
	    		
	    		newPcsTlDtl.setCreatedby(user.getUsrm_ccno());
	    		newPcsTlWorkorderlink.setPtwoCreatedby(user.getUsrm_ccno());
	    		
	    		CommonMessage.debugMsg("newPcsTlMst.getPrlmEntrydate() : "+newPcsTlMst.getPrlmEntrydate());
	    		
	    		
	    		
	    		/*String prodQty = newPcsTlDtl.getProducedqty();
	    		if (!UIUtils.isValidKeyId(prodQty)) prodQty="0";	    		
	    		CommonMessage.debugMsg("prodQty : "+prodQty);
	    		
	    		String cavityUsed = newPcsTlDtl.getCavityused();
	    		if (!UIUtils.isValidKeyId(cavityUsed)) cavityUsed="0";
	    		CommonMessage.debugMsg("cavityUsed : "+cavityUsed);
	    		
	    		String actTime = newPcsTlDtl.getActualcycletime();
	    		if (!UIUtils.isValidKeyId(actTime)) actTime="0";
	    		CommonMessage.debugMsg("actTime : "+actTime);
	    		
	    		String availTimeStr   = pcsEntryService.getCalendarTime(newPcsTlDtl.getMachineid(), request.getParameter("Plmasterid"), newPcsTlMst.getPrlmSectionid(),newPcsTlMst.getPrlmEntrydate());
	    		CommonMessage.debugMsg("availTime : "+availTimeStr);
	    		
	    		Float availTime = Float.parseFloat(availTimeStr);
	    		if (Float.parseFloat(availTimeStr) < 0)
	    			availTime= (float) 0.0;
	    		
	    		String oldTime=request.getParameter("oldTime");	    		
	    		CommonMessage.debugMsg("oldTime : "+oldTime);
	    		
	    		if (request.getParameter("saveMode").equals("Update"))
	    			availTime = availTime + Float.parseFloat(oldTime);
	    		
	    		CommonMessage.debugMsg("AvailTime After Mode Verifcn : "+availTime);
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
	    		
	    		CommonMessage.debugMsg("planQty : "+planQty);
	    		
	    		newPcsTlDtl.setPlannedqty(planQty.toString());
	    		newPcsTlDtl.setCalendartime(availTime.toString());
	    		
	    		Float prodTime;
	    		if (cavityUsed.equals("0"))
	    			prodTime =Float.parseFloat(prodQty)* Float.parseFloat(actTime);
	    		else
	    			prodTime = ( Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
	    		 
	    		CommonMessage.debugMsg("prodTime : "+prodTime);
	    			    		
	    			
	    		if (Math.floor(prodTime) > availTime)
					throw new BusinessApplicationExceptions("productionTime_Greate");	    			
	    			
	    		
	    		if (Float.parseFloat(cavityUsed) > Float.parseFloat(cavityAvail))
	    			throw new Exception(" 'Cavity Used' Can not be greater than Cavity Available");*/
	    			
	    		//if (Float.parseFloat(cavityUsed) < 1)
	    			//throw new Exception(" 'Cavity Used' Should be atleast 1");
	    		
	    		/*String actTimePct = pcsEntryService.getActTimePct(); 
	    			
				int actPct = Integer.parseInt(actTimePct) ;
				CommonMessage.debugMsg("actPct % : "+actPct );
				
	    		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
	    		CommonMessage.debugMsg("time25% : "+timePct);
	    		Float timepctVal = (float)0;
	    		
	    		
	    		NumberFormat df = DecimalFormat.getInstance();
	    		df.setMinimumFractionDigits(2);
	    		df.setMaximumFractionDigits(2);
	    		df.setRoundingMode(RoundingMode.UP);
	    		
	    		
	    		timepctVal = (Float.parseFloat(theoTime) + timePct);
	    		CommonMessage.debugMsg("(timepctVal : "+timepctVal);
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
				CommonMessage.debugMsg("Rejected Qty : "+newPcsTlDtl.getRejectedqty());
				existPcsTlMst = pcsEntryService.createNew(newPcsTlMst, newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
				
				String keyId =existPcsTlMst.getPrlmKeyid();
				CommonMessage.debugMsg(" KEYID :  " +  keyId);
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
				//CommonMessage.debugMsg("Length : "+pcsData.length);
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
				//CommonMessage.debugMsg("colValues : "+colValues);
				
				String PLDetailsID ="";
				if(UIUtils.isValidKeyId(existPcsTlMst.getPrlmActive()))
				{
					//if(existPcsTlMst.getPrlmActive().equals("U"))
						PLDetailsID = newPcsTlDtl.getPldetailsid();					
				}
				CommonMessage.debugMsg(" KEYID :  " +  keyId);
				
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
				CommonMessage.debugMsg(returnData.toString());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
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
		//CommonMessage.debugMsg("Length : "+pcsData.length);
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
		CommonMessage.debugMsg("pcsData.length=== "+pcsData.length);
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
		CommonMessage.debugMsg("Pcl MAIN");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String from=(String)httpSession.getAttribute("from");
    	CommonMessage.debugMsg("from view:"+from);
    	UIUtils.displayRequestParamsValue(request);
    	
    	if( httpSession != null && user != null)
    	{    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		//CommonMessage.debugMsg("pcsEntryBean.getFormActionMode()"+ pcsEntryBean.getFormActionMode());
    		
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			CommonMessage.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
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
			CommonMessage.debugMsg("mchId"+mchId);
			CommonMessage.debugMsg("date"+date);
			CommonMessage.debugMsg("shift"+shift);
			
			//CommonMessage.debugMsg("userid"+userid);
			
			try{
				if( from.equals("noPlanEntry"))		{
				}									
				else {				
					if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
						throw new BusinessApplicationExceptions("view_Mode"+",");
					
					String Plmasterid= request.getParameter("Plmasterid");
					String sectId = request.getParameter("sectId");
					
					String availTime   = pcsEntryService.getCalendarTime(mchId, Plmasterid, sectId,date);
					
					CommonMessage.debugMsg("availTime"+availTime);
					
					if (!availTime.equals("480"))
						throw new BusinessApplicationExceptions("prd_Exists"+",");
				}
				
				//pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));	    		
	    		String noPlanResult="";
				if( pcsEntryBean.getFormMode().equals("Save")) {									
					noPlanResult = pcsEntryService.insertNoPlan(mchId,date,shift,userid,type,duration,fromTme,toTime);							
				}
		    	
				String keyId =noPlanResult;
				CommonMessage.debugMsg("noPlanResult"+noPlanResult);
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
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
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
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

    private void savePcsLossEntry(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	CommonMessage.debugMsg("Pcs Loss Entry Save Servlete");		
    	CommonMessage.debugMsg("Pcs Loss Entry Save Servlet2222");		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			CommonMessage.debugMsg("formBean Mode"+pcsEntryBean.getFormMode());
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
				
				PcsTlLossreasonlink existPcsTlLossreasonlink = (PcsTlLossreasonlink)httpSession.getAttribute("PcsTlLossreasonlink");
	    		PcsTlLossreasonlink newPcsTlLossreasonlink = new PcsTlLossreasonlink();
	    		PcsTlLossreasonlink oldPcsTlLossreasonlink = new PcsTlLossreasonlink();
	    		
	    		newPcsTlLossreasonlink =(PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		CommonMessage.debugMsg("third");
	    		newPcsTlLossreasonlink.setPlrkCreatedby(user.getUsrm_ccno());	    		
	    		
				
				String msrGridVar = request.getParameter("MsrValues");	
	    		
	    		PcsTlAncilliarytime newPcsTlAncilliarytime = new PcsTlAncilliarytime();	    		
	    		newPcsTlAncilliarytime.setPtatCreatedby(user.getUsrm_ccno());	
	    		newPcsTlAncilliarytime =(PcsTlAncilliarytime)UIUtils.setBeanProperties((Object)newPcsTlAncilliarytime,request);
	    					
	    		if(!UIUtils.isValidKeyId(msrGridVar))	{
		    		CommonMessage.debugMsg("MmsrGridVargrid: "+msrGridVar);
	    		}
	    		else {
	    			CommonMessage.debugMsg("msrGridVargrid = "+msrGridVar);

		    		List<PcsTlAncilliarytime> AncilliaryList = null;
			    	JSONArray AncilliaryJson = null;
			    	if(UIUtils.isValidKeyId(msrGridVar))
			    	{
			    		AncilliaryJson = JSONArray.fromString(msrGridVar);
			    		CommonMessage.debugMsg("AncilliaryJson"+AncilliaryJson);
			    		AncilliaryList = (List<PcsTlAncilliarytime>)UIUtils.convertJSONArrToList(newPcsTlAncilliarytime,AncilliaryJson);
			    		if(AncilliaryList!= null){
			    			CommonMessage.debugMsg("AncilliaryList---"+AncilliaryList);
			    			newPcsTlLossreasonlink.setPcsTlAncilliarytimeList(AncilliaryList);
			    		}
	    		    }
	    		}
	    				    		
	    		pcsEntryBean.setLossId(request.getParameter("lossId"));
	    		pcsEntryBean.setLossValue(request.getParameter("lossValue"));
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		pcsEntryBean.setPlrkKeyid(request.getParameter("PlrkKeyid"));
	    		
	    		CommonMessage.debugMsg("LossId"+request.getParameter("lossId"));
	    		CommonMessage.debugMsg("lossValue"+request.getParameter("lossValue"));
	    		CommonMessage.debugMsg("Pldetailsid========"+request.getParameter("Pldetailsid"));
	    		CommonMessage.debugMsg("PlrkKeyid========"+request.getParameter("PlrkKeyid"));
	    		
	    		
				if( pcsEntryBean.getFormMode().equals("Save"))
					existPcsTlLossreasonlink = pcsEntryService.createLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				else
					existPcsTlLossreasonlink = pcsEntryService.updateLoss(newPcsTlLossreasonlink, oldPcsTlLossreasonlink, pcsEntryBean);
				
				String keyId =existPcsTlLossreasonlink.getPlrkKeyid();
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
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
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				CommonMessage.debugMsg("gete. " + e.getMessage());
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
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    		if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
    		if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");
				
	    		PcsTlLossreasonlink existPcsTlLossreasonlink = (PcsTlLossreasonlink)httpSession.getAttribute("PcsTlLossreasonlink");
	    		PcsTlLossreasonlink newPcsTlLossreasonlink = new PcsTlLossreasonlink();
	    		PcsTlLossreasonlink oldPcsTlLossreasonlink = new PcsTlLossreasonlink();
	    		
	    		newPcsTlLossreasonlink =(PcsTlLossreasonlink)UIUtils.setBeanProperties((Object)newPcsTlLossreasonlink,request);
	    		newPcsTlLossreasonlink.setPlrkCreatedby(user.getUsrm_ccno());	    		
	    		
	    		String msrGridVar = request.getParameter("MsrValues");	
	    		
	    		PcsTlAncilliarytime newPcsTlAncilliarytime = new PcsTlAncilliarytime();	    		
	    		newPcsTlAncilliarytime.setPtatCreatedby(user.getUsrm_ccno());	
	    		newPcsTlAncilliarytime =(PcsTlAncilliarytime)UIUtils.setBeanProperties((Object)newPcsTlAncilliarytime,request);
	    					
	    		/*if(!UIUtils.isValidKeyId(msrGridVar))	{
		    		CommonMessage.debugMsg("MmsrGridVargrid: "+msrGridVar);
	    		}
	    		else {
	    			CommonMessage.debugMsg("msrGridVargrid = "+msrGridVar);

		    		List<PcsTlAncilliarytime> AncilliaryList = null;
			    	JSONArray AncilliaryJson = null;
			    	if(UIUtils.isValidKeyId(msrGridVar))
			    	{
			    		AncilliaryJson = JSONArray.fromString(msrGridVar);
			    		CommonMessage.debugMsg("AncilliaryJson"+AncilliaryJson);
			    		AncilliaryList = (List<PcsTlAncilliarytime>)UIUtils.convertJSONArrToList(newPcsTlAncilliarytime,AncilliaryJson);
			    		if(AncilliaryList!= null){
			    			CommonMessage.debugMsg("AncilliaryList---"+AncilliaryList);
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
				CommonMessage.debugMsg(" KEYID() " +  keyId);					
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
				
				CommonMessage.debugMsg("returnData.toString() "+returnData.toString());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", e.getMessage());
				out.print(err.toString());
			}
    	}
	}
    
    
    private void savePcsLossCapture(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{

    	CommonMessage.debugMsg("savePcsLossCapture Servlete");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
			if (request.getParameter("saveMode").equals("update"))
				pcsEntryBean.setFormMode("update");
			else 
				pcsEntryBean.setFormMode("save");
		
			
			//CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");

	    		PcsTlLosscapture existPcsTlLosscapture = (PcsTlLosscapture)httpSession.getAttribute("PcsTlLosscapture");
	    		PcsTlLosscapture newPcsTlLosscapture = new PcsTlLosscapture();
	    		
	    		newPcsTlLosscapture =(PcsTlLosscapture)UIUtils.setBeanProperties((Object)newPcsTlLosscapture,request);
	    		
	    		//CommonMessage.debugMsg("third");
	    		newPcsTlLosscapture.setPlosCreatedby(user.getUsrm_ccno());
	    		
	    		pcsEntryBean.setPlosKeyid(request.getParameter("plosKeyid"));	    		
	    		pcsEntryBean.setPlrkKeyid (request.getParameter("plrkKeyid"));
	    		pcsEntryBean.setPlmasterid (request.getParameter("Plmasterid"));
	    		
	    		pcsEntryBean.setPldetailsid(request.getParameter("pldetailsId"));
	    		
	    		String pldetailsId = request.getParameter("pldetailsId");
	    		String detectedBy =request.getParameter("enteredBy");
	    		
	    		//CommonMessage.debugMsg("pldetailsId"+request.getParameter("pldetailsId"));
	    		newPcsTlLosscapture.setPlosTempfield3(detectedBy);
	    		newPcsTlLosscapture.setPlosPldetailsid(pldetailsId);
	    		newPcsTlLosscapture.setPlosFromtime(request.getParameter("fromdate") + " " + newPcsTlLosscapture.getPlosFromtime()  );
	    		newPcsTlLosscapture.setPlosTotime(request.getParameter("todate") + " " + newPcsTlLosscapture.getPlosTotime() );
	    		
	    		CommonMessage.debugMsg("newPcsTlLosscapture.getPlosFromtime()" + newPcsTlLosscapture.getPlosFromtime()+ "newPcsTlLosscapture.getPlosTotime()" + newPcsTlLosscapture.getPlosTotime());
	    		//CommonMessage.debugMsg("fliddd===="+newPcsTlLosscapture.getPlosFlid());
	    		//if( pcsEntryBean.getFormMode().equals("Save"))
				existPcsTlLosscapture = pcsEntryService.createLossCapture(newPcsTlLosscapture,pcsEntryBean);
				
				String keyId =existPcsTlLosscapture.getPlosKeyid();
				//CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
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
				returnData.put("pldetailsId", existPcsTlLosscapture.getPlospldetailsid());
				out.print(returnData.toString());				
			}
	    	catch(ValidationExceptions e)
			{
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
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
    
    private void savePcsEmployee(HttpServletRequest request, HttpServletResponse response ) throws IOException
	{
    	CommonMessage.debugMsg("savePcsEmployee Servlete");
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
    	if( httpSession != null && user != null)
    	{    		
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");
    	
			if (pcsEntryBean  == null )	
				pcsEntryBean  = new PcsEntryBean();
			
			if (request.getParameter("saveMode").equals("Update"))
				pcsEntryBean.setFormMode("Update");
			else 
				pcsEntryBean.setFormMode("Save");
		
			
			CommonMessage.debugMsg("savemode"+request.getParameter("saveMode"));	
			
			try{
				
	    		if (UIUtils.isValidKeyId(pcsEntryBean.getFormActionMode()) && ! pcsEntryBean.getFormActionMode().equals("create"))
					throw new BusinessApplicationExceptions("view_Mode"+",");

	    		PcsTlOperatordtl existPcsTlOperatordtl = (PcsTlOperatordtl)httpSession.getAttribute("PcsTlOperatordtl");
	    		PcsTlOperatordtl newPcsTlOperatordtl = new PcsTlOperatordtl();
	    		
	    		newPcsTlOperatordtl =(PcsTlOperatordtl)UIUtils.setBeanProperties((Object)newPcsTlOperatordtl,request);
	    		
	    		CommonMessage.debugMsg("third");
	    		newPcsTlOperatordtl.setPopdCreatedby(user.getUsrm_ccno());
	    		
	    		pcsEntryBean.setPlmasterid(request.getParameter("Plmasterid"));	    		
	    		pcsEntryBean.setPldetailsid(request.getParameter("Pldetailsid"));
	    		
	    		CommonMessage.debugMsg("Plmasterid"+request.getParameter("Plmasterid"));

	    		if( pcsEntryBean.getFormMode().equals("Save"))
					existPcsTlOperatordtl = pcsEntryService.createPcsEmployee(newPcsTlOperatordtl,pcsEntryBean);
				
				String keyId =existPcsTlOperatordtl.getPopdPlemployeeid();
				//CommonMessage.debugMsg(" KEYID() " +  keyId);					
				
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
	    		CommonMessage.debugMsg("validations exception");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"pcsEntryCreation");
				errMessage.put("fromMode",pcsEntryBean.getFormMode());
				CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
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

    private void deletePcsEmployee(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BusinessApplicationExceptions
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	String pldetailsId = request.getParameter("pldetailsId");
    	String empId = request.getParameter("empId");
    	
    	CommonMessage.debugMsg("pldetailsId=="+pldetailsId);
    	
    	if( httpSession != null && user != null)
    	{
    		PcsEntryBean pcsEntryBean = (PcsEntryBean)httpSession.getAttribute("PcsEntryServletPcsEntryBean");    		

	    	CommonMessage.debugMsg("delete query exe");
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
				CommonMessage.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "pcsEntryCreation");
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

    

	private JSONObject getTableModel(List<String[]> lossgrid) {
		
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = lossgrid.get(0);
		
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(500);
		
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(145);
			CommonMessage.debugMsg("inside ["+i+"]"+colHeader[i]);
			
		
			if(i==0 || i==1 || i==7  || i==9  || i==12 || i==24|| i==25|| i==26 ){
		    	jqGridColModel.setHidden(true);
			}
		
		    if (i==2 || i==4 ) 	    {
		    	jqGridColModel.setWidth(100);
		    }
		    
		    if (i==3 || i==5 ) 	    {
		    	jqGridColModel.setWidth(40);
		    }
		    
		    if (i==6 || i==8  ) {
		    	jqGridColModel.setWidth(50);				
			}
		    if (i==10 || i==11 )	{
		    	jqGridColModel.setWidth(140);				
			}
		    if (i==13 )	    {
		    	jqGridColModel.setWidth(80);				
			}
		    if (  i==14)	    {
		    	jqGridColModel.setWidth(60);
		    	jqGridColModel.setAlign("center");
			}
		    if (i==12)	    {
		    	jqGridColModel.setWidth(60);				
			}
		    
		    if (i==18) {
		    	jqGridColModel.setAlign("center");	
		    	jqGridColModel.setFormatter("whywhyBtn");
				jqGridColModel.setWidth(80);
			}
			
		    if (i==15 || i==16)	    {
		    	jqGridColModel.setWidth(100);				
			}
		   /* if(i==17){
		    	jqGridColModel.setWidth(100);		
		    }*/
		    //------vignesh 
		    if (i==20) {
		    	jqGridColModel.setFormatter("kaizenBtn");
		    	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(80);
			}
		    if (i==22) {
		    	jqGridColModel.setFormatter("actionplanBtn");
		    	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(80);
			}
		    if(i==23){
		    	
		    	jqGridColModel.setWidth(80);	
		    	
		    }
		    
	    jqGridTableModel.getColModel().add(jqGridColModel);
	}
	
	
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "65%%");
	tableModel.set("tableWidth", "110%%");
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

}
