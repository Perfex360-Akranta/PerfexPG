package com.akranta.tpm.controller;

/** Created By:Siddharth .A**/
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.dao.PcsTlLossphenomenamstDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.PcsTlLossphenomenamstDaoImpl;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.service.PcsEnableDisableService;
import com.akranta.tpm.service.impl.PcsEnableDisableServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.dao.impl.DBActionTemplate;


public class PCSEnableDisableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int List = 0;
	
	private PcsEnableDisableService pcsEnableDisableService;
    public PCSEnableDisableServlet() {
    
        super();
    	/*try {
			pcsEnableDisableService =new PcsEnableDisableServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
    }
  

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			process(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try {
			process(request, response);
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
	}
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	 

    	HttpSession httpSession = request.getSession(false);  
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action:"+action);
		
		ComboFilter comboFilter = new ComboFilter();
		
		try {
			pcsEnableDisableService =(PcsEnableDisableServiceImpl)UIUtils.getServiceObject(request,"PcsEnableDisableServiceImpl");
		
		
			CommonMessage.debugMsg("  pcsEnableDisableService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			pcsEnableDisableService.PcsEnableDisableServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		if (action.equals("pcsEnableDisable_input.pcsEnable")&& user != null) 
		{
			
			CommonMessage.debugMsg("input the jsp");
			// there is nothing to be done
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			request.setAttribute("pcsEnableDisableFormBean", pcsEnableDisableFormBean);
		}else if(action.equals("keyPheLink_validate.pcsEnable"))
		{
			PrintWriter out1 = response.getWriter();
			validatePheLink(request,response,out1);			
		}
		else if(action.equals("pcsEnableDisableMCH_input.pcsEnable"))
		{
			
		}
		
		else if(action.equals("pcsEnableDisableMCH_getCol.pcsEnable"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				String pcsEbDbMCHColModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbMCHGrid");
		        CommonMessage.debugMsg("pcsEbDbMCHColModel="+pcsEbDbMCHColModel);
				out.print(pcsEbDbMCHColModel);
		
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable MCH getCol Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("pcsEnableDisableMCH_getData.pcsEnable"))
		{
			try
			{	
				CommonMessage.debugMsg("Inside Mch get Dtaa...........");
				PrintWriter out = response.getWriter();
				String factId= request.getParameter("factId");
				String sectId= request.getParameter("sectId");
				String cellId=request.getParameter("cellId");
				String flid = request.getParameter("flid");
				
				CommonMessage.debugMsg("factId="+factId+"sectId="+sectId+"cellId="+cellId);
				//String peclKeyId = request.getParameter("peclKeyId");
			
				List<String[]> pcsEnableList= pcsEnableDisableService.getAllPcsEnblDsblMCH(factId,sectId,cellId, flid);
				CommonMessage.debugMsg("pcsEnableList="+pcsEnableList);

				List<PcsTlEnablelosscapture> pcsTlEnablelosscaptureList = populatePcsEnbDsbList(pcsEnableList);
				
				CommonMessage.debugMsg("pcsTlEnablelosscaptureList.size()"+pcsTlEnablelosscaptureList.size());	
				
				httpSession.setAttribute("pcsTlEnablelosscaptureList",pcsTlEnablelosscaptureList);	
		
				JSONObject pcsEnableData = UIUtils.convertToJqGridTableObject(pcsEnableList,request,0,0);
				CommonMessage.debugMsg("pcsEnableData="+pcsEnableData);
				out.println(pcsEnableData);
			
			
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable getData Exception"+e.getMessage());
			}
			
		}
		
		else if(action.equals("pcsEnableDisableCELL_input.pcsEnable"))
		{
			
		}
		
		else if(action.equals("pcsEnableDisableCELL_getCol.pcsEnable"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
			    out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbCELLGrid"));
				CommonMessage.debugMsg((UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbCELLGrid")));
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable CELL getCol Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("pcsEnableDisableCELL_getData.pcsEnable"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				
				String factId= request.getParameter("factId");
				String sectId= request.getParameter("sectId");
				String cellId=request.getParameter("cellId");
				CommonMessage.debugMsg("Inside Cell Servlete"+factId+"sect="+sectId+"cellId="+cellId);
				
				List<String []> pcsEnableList  = pcsEnableDisableService.getAllPcsEnblDsblCELL(factId,sectId,cellId);
				JSONObject pcsEnableCELLData = UIUtils.convertToJqGridTableObject(pcsEnableList,request,0,0);
				CommonMessage.debugMsg("pcsEnableCELLData"+pcsEnableCELLData);
				out.println(pcsEnableCELLData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable getData Exception"+e.getMessage());
			}
			
		}
		
		else if(action.equals("pcsEnableDisableSECT_input.pcsEnable"))
		{
			
		}
		
		else if(action.equals("pcsEnableDisableSECT_getCol.pcsEnable"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbSECTGrid"));
				CommonMessage.debugMsg("SECTION"+UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbSECTGrid"));
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable MCH getCol Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("pcsEnableDisableSECT_getData.pcsEnable"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				
				String factId= request.getParameter("factId");
				String sectId= request.getParameter("sectId");
				List<String []> pcsEnableList  = pcsEnableDisableService.getAllPcsEnblDsblSECT(factId,sectId);
				JSONObject pcsEnableSECTData = UIUtils.convertToJqGridTableObject(pcsEnableList,request,0,0);
				out.println(pcsEnableSECTData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Pcs Enable Disable getData Exception"+e.getMessage());
			}
			
		}
		else if(action.equals("phenomena_combo.pcsEnable"))
		{
			try 
			{
				comboFilter=UIUtils.fillComboFilter(request);
				//CommonFilter commonFilter = new CommonFilter();
				List<ComboBox> DetectedBy = pcsEnableDisableService.getPhenomenaComboList(comboFilter);
				UIUtils.writeComboBox(response, DetectedBy, comboFilter);
			} 
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		else if(action.equals("mainLoss_combo.pcsEnable"))
		{
			try 
			{
				comboFilter=UIUtils.fillComboFilter(request);
				//CommonFilter commonFilter = new CommonFilter();
				List<ComboBox> DetectedBy = pcsEnableDisableService.getMainLossComboList(comboFilter);
				UIUtils.writeComboBox(response, DetectedBy, comboFilter);
			} 
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		
		else if(action.equals("phenomenaFactory_getCol.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "factoryTable");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			httpSession.removeAttribute("FactColmodel");
			httpSession.setAttribute("FactColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		else if(action.equals("phenomenaFactory_getData.pcsEnable"))
		{
			
			PrintWriter out = response.getWriter();
			String lossId=request.getParameter("lossID");
			String phenID=request.getParameter("phenId");
			
			GridParams gridParams = (GridParams) httpSession.getAttribute("phenomenaFactorygridParams");
		
			httpSession.setAttribute("phenomenaFactorygridParams", gridParams);
			
			if (gridParams == null)
				gridParams = new GridParams();
		
			FilterValues.populateGridParams(request, gridParams);
			List<String[]> FactoryList;
			try 
			{
				FactoryList = pcsEnableDisableService.getFactory(gridParams,lossId,phenID);
				JSONObject FactoryData = UIUtils.convertToJqGridTableObject(FactoryList, request, 0, 1,gridParams.getTotalRecordCnt());
				out.println(FactoryData);
				CommonMessage.debugMsg("phenomen11111111");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		

		else if(action.equals("lossJH_getCol.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "lossJHGrid");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			httpSession.removeAttribute("JHColmodel");
			httpSession.setAttribute("JHColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		else if(action.equals("lossJH_getData.pcsEnable"))
		{
			
			PrintWriter out = response.getWriter();
			String lossId=request.getParameter("lossID");
			String jhID=request.getParameter("jhId");
			GridParams gridParams = (GridParams) httpSession.getAttribute("lossJHgridParams");
			httpSession.setAttribute("lossJHgridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request, gridParams);
			List<String[]> jhList;
			try 
			{
				jhList = pcsEnableDisableService.getJH(gridParams,lossId);
				JSONObject FactoryData = UIUtils.convertToJqGridTableObject(jhList, request, 0, 1,gridParams.getTotalRecordCnt());
				out.println(FactoryData);
			}
			catch (Exception e)
			{
				
				e.printStackTrace();
			}
		}
		else if(action.equals("functionalLoc.pcsEnable"))
		{
			CommonMessage.debugMsg("sfdfdfoui");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setSectMandatory(false);
	

			UIUtils.setFunctionalLocationPopupVal(request, response,
					functLocFieldNameBean, null);
			
			
		}
		
		else if(action.equals("PhenomenaFunctionalLocationMapping_input.pcsEnable"))
		{
			//  String DMT = request.getParameter("DMT");
			 // request.setAttribute("DMT", DMT);
			UIUtils.forwardRequest(request, response, "/pages/KK/PFLocationMapping.jsp");
			
		}
		
		else if(action.equals("PhenomenaFunctionalLocationMapping_getCol.pcsEnable"))
		{
			/*CommonMessage.debugMsg("--------------------------getcol-------------");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "PFLMappingGrid");
			JSONObject colmodel = JSONObject.fromString(tableModel);
		//	httpSession.removeAttribute("PhenColmodel");
		//	httpSession.setAttribute("PhenColmodel", colmodel);
			out.println(colmodel);
			out.close();*/
			getColAction(request,response,httpSession);
		}
	
		else if(action.equals("PhenomenaFunctionalLocationMapping_getData.pcsEnable"))
		{
			/*PrintWriter out = response.getWriter();
			String flid=request.getParameter("flid");
			
			CommonMessage.debugMsg("------------------------flid is------------" +flid);
			GridParams gridParams = (GridParams) httpSession.getAttribute("phenomenaLossgridParams");
			httpSession.setAttribute("phenomenaLossgridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			List<String[]> PhenomenaList;
			CommonFilter commonFilter=new CommonFilter();
			
			commonFilter = populateCommonFilter(request,"phenomenalossgridCommonFilter",true);
			commonFilter.setFlid(flid);
			
			try 
			{ 
			
				PhenomenaList = pcsEnableDisableService.getLossPhenMst(gridParams, commonFilter);
			
				JSONObject FactoryData = UIUtils.convertToJqGridTableObject(PhenomenaList, request, 0, 1,gridParams.getTotalRecordCnt());
				
				out.println(FactoryData);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}*/
			
			getDataAction(request,response,httpSession);

			
		}
		// ----- Vignesh 
		
		
		
	    	    
	    	else if(action.equals("PhenomenaFunctionalLocationMapping_getExcel.pcsEnable")){
		    	CommonMessage.debugMsg("Excel Servlet");
		    	String format = ExcelUtils.getFormat(request);
		    	
		    	String CompId=request.getParameter("compId");
				String factId=request.getParameter("factId");
				//String indicatorId=request.getParameter("indicatorId");
				String pillCode=request.getParameter("pillCode");
				String sectId=request.getParameter("sectId");
				String cellId=request.getParameter("cellId");
				String drillLevel=request.getParameter("drillLevel");
				String flid=request.getParameter("flid");
				String type = request.getParameter("type");
				String drillLevel1=(String)httpSession.getAttribute("drillLevel");
				CommonMessage.debugMsg("Excel Servlet == === " + CompId + factId + " values from get parameters " + drillLevel + drillLevel1 + "type " + type );
				response.setContentType("text/html");
				response.setContentType("text/json");
				
				GridParams gridParams = (GridParams) httpSession.getAttribute("PFLProdgridParams");
				httpSession.setAttribute("PFLProdgridParams", gridParams);
				
				CommonMessage.debugMsg("Excel Servlet +++ grid params " + gridParams);
				//  GridParams gridParams = (GridParams) httpSession.getAttribute("PFLProdColModel");
				//httpSession.setAttribute("PFLProdColModel", gridParams);
				//PFLProdColModel
				
				if (gridParams == null)
				gridParams = new GridParams();
				//FilterValues.populateGridParams(request, gridParams);
				//CommonMessage.debugMsg("CompId" + CompId + "factId" +  factId + "sectId" +  sectId + "cellId" + cellId + "drillLevel" + drillLevel);
				FilterValues.populateGridParams(request, gridParams);
				CommonFilter commonFilter=new CommonFilter();
				
				commonFilter = populateCommonFilter(request,"PFLCommonFilter",false);
				commonFilter.setFlid(flid);
				commonFilter.setComp(CompId);
				commonFilter.setFactoryId(factId);
				commonFilter.setSectionId(sectId);
				commonFilter.setCellId(cellId);
				commonFilter.setDrillLevel(drillLevel);
				//commonFilter.setIndicator(indicatorId);
				commonFilter.setPillarWise(pillCode);
				commonFilter.setType(type);
				String tmpFromRow = commonFilter.getFromRow();
				commonFilter.setFromRow(null);
				
				CommonMessage.debugMsg("Excel Servlet +++ commonfilter  " + commonFilter);
				
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("PFLProdColModel");
				CommonMessage.debugMsg("Excel Servlet +++ jsonbject   " + tblJSONObj);
				
				Workbook wb;
					wb = pcsEnableDisableService.getExcelreport(commonFilter,tblJSONObj,format,gridParams);
				
				commonFilter.setFromRow(tmpFromRow);
			
				ExcelUtils.writeToResponse(response, wb, "FUNCMAPPINGLOSS", format);
			
					
	    	 
				}
        
				 
			
		
		// ----- Vignesh 
		
		else if(action.equals("PhenomenaFunctionalLocationMapping_save.pcsEnable"))
		{
			CommonMessage.debugMsg("save is calling");
			savePhenomenaFactMaping(request,response);
		}
		
		
		
		else if(action.equals("phenomenaLoss_input.pcsEnable")||action.equals("phenomenaFactoryLossLink_input.pcsEnable"))
		{
			UIUtils.forwardRequest(request, response, "/pages/PhenomenaLossLink.jsp");
			request.setAttribute("from", "LossPhen");
			
		}
		
		else if(action.equals("lossJhLink_input.pcsEnable"))
		{
			UIUtils.forwardRequest(request, response, "/pages/pcs/LossJHLink.jsp");
			request.setAttribute("from", "LossJH");
		}
		
		else if(action.equals("phenomenaLoss_getCol.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "phenomenaTable");
		
			JSONObject colmodel = JSONObject.fromString(tableModel);
			httpSession.removeAttribute("PhenColmodel");
			httpSession.setAttribute("PhenColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		else if(action.equals("phenomenaLoss_getData.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			String phenId=request.getParameter("phenId");
			String lossId=request.getParameter("lossID");
			CommonMessage.debugMsg("lossId:"+lossId);
			GridParams gridParams = (GridParams) httpSession.getAttribute("phenomenaLossgridParams");
			httpSession.setAttribute("phenomenaLossgridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			List<String[]> PhenomenaList;
			try 
			{
				PhenomenaList = pcsEnableDisableService.getPhenomena(gridParams,phenId,lossId);
				JSONObject FactoryData = UIUtils.convertToJqGridTableObject(PhenomenaList, request, 0, 1,gridParams.getTotalRecordCnt());
				
				out.println(FactoryData);
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if(action.equals("lossParameters_getCol.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "lossParametersGrid");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			httpSession.removeAttribute("lossColmodel");
			httpSession.setAttribute("lossColmodel", colmodel);
			out.println(colmodel);
			out.close();
		}
		else if(action.equals("lossParameters_getData.pcsEnable"))
		{
			PrintWriter out = response.getWriter();
			//String phenId=request.getParameter("phenId");
			String jhId=request.getParameter("jhId");
			
			CommonMessage.debugMsg("jhId:"+jhId);
			GridParams gridParams = (GridParams) httpSession.getAttribute("lossParametersgridParams");
			CommonMessage.debugMsg("bbbbbbbbbbbbbbb");
			httpSession.setAttribute("lossParametersgridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request,gridParams);
			List<String[]> PhenomenaList;
			try 
			{
				PhenomenaList = pcsEnableDisableService.getLossNames(gridParams,jhId);
				JSONObject FactoryData = UIUtils.convertToJqGridTableObject(PhenomenaList, request, 0, 1,gridParams.getTotalRecordCnt());
				out.println(FactoryData);
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		else if(action.equals("PhenomenaFactoryLossLink_save.pcsEnable"))
		{
			savePhenomenaFactLoss(request,response);
		}
		else if(action.equals("phenomenaLoss_save.pcsEnable"))
		{
			savePhenomenaFactLoss(request,response);
		}
		
		// ------------- VIGNESH DELETE PHENOMENA----------------------//
	//	else if ("phenomenaLoss_delete.pcsEnable".equalsIgnoreCase(action != null ? action.trim() : ""))
			else if (action.equals("phenomenaLoss_delete.pcsEnable")) {
		    deletePhenomenaLoss(request, response);
		}


		// ------------- VIGNESH DELETE PHENOMENA----------------------//
		
		else if(action.equals("lossJhLink_save.pcsEnable"))
		{
			saveLossJH(request,response);
		}
		
		else if(action.equals("getComboFillContent.pcsEnable"))
		{
			String keyId=request.getParameter("phenId");
			String fromParam=request.getParameter("from");
			String type="";
			if(UIUtils.isValidKeyId(keyId))
				type="PHENOMENA";
			else
			{
				keyId=request.getParameter("lossID");
				type="LOSS";
			}
			PrintWriter out = response.getWriter();	
			List<String[]> comboText= pcsEnableDisableService.getComboTextContent(keyId,type);
			CommonMessage.debugMsg("Size...."+comboText.size());
			JSONObject comboTextData =new JSONObject();
			if (comboText.size()>0)
			{
				comboTextData.put("phenID", comboText.get(0)[0]);
				comboTextData.put("lossID", comboText.get(0)[1]);
				comboTextData.put("phenName", comboText.get(0)[2]);
			}
			//httpSession.removeAttribute("lossID");
			//httpSession.setAttribute("lossID", comboText.get(0)[1]);
			comboTextData.put("from",fromParam);
			out.print(comboTextData);		
		}
		else if(action.equals("pcsEnableDisableSECT_getCount.pcsEnable"))
		{
			try
			{
				String sectId= request.getParameter("sectId");
				String msg=null;
				PrintWriter out = response.getWriter();
				List<String[]> getSectCountList = pcsEnableDisableService.getAllSectCount(sectId);
				CommonMessage.debugMsg("getSectCountList="+getSectCountList.size());
				CommonMessage.debugMsg("str"+getSectCountList.toString());
				//String[] cnt=getSectCountList.get(0);
				String sectCnt= getSectCountList.toString();
				if(getSectCountList.size()>0)
				{ 
						CommonMessage.debugMsg("Exists");
						msg="exists";
				}
				else 
				{
					CommonMessage.debugMsg("NEWWW"); 
					msg="new";
				}
				JSONObject returnData= new JSONObject();
			    returnData.put("jsonObject", msg);				
				out.print(returnData.toString());
				
			}
			
			catch(Exception e)
			{
				
			}
		}
		
		else if(action.equals("pcsEnableDisable_OptionsCombo.pcsEnable"))
		{
			 try 
			 {
			   PrintWriter out = response.getWriter();
			   String mchCondCombo= UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnblDsblOptionCombo");
			   out.print(mchCondCombo);
			 } 
			 catch (Exception e) 
			 {
				e.printStackTrace();
			 }
			
		}
		
		else if(action.equals("functLoc.pcsEnable"))
		{
			/*
			String drillLevel=request.getParameter("drillLevel");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbpelcFactoryid");
			functLocFieldNameBean.setSection("cmbpelcLineid");
			functLocFieldNameBean.setCell("cmbpelcCellid");
			functLocFieldNameBean.setMachine("cmbpelcMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbpelcFlid");
			functLocFieldNameBean.setSectMandatory(true);
			
			functLocFieldNameBean.setFactMandatory(true);
		
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			FormModes formModes = FormModes.create;//
			if( formModes == FormModes.completion || formModes == FormModes.removal)
				formModes = FormModes.view;
			
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
				functLocFieldNameBean.setSectMandatory(true);
			}
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );*/
			
			
			String drillLevel=request.getParameter("drillLevel");
			//CommonMessage.debugMsg("functionalLoc.keyPerInd drillLevel" + drillLevel);
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setLocation("cmbpelcLocationid");
			functLocFieldNameBean.setFactory("cmbpelcFactoryid");
			functLocFieldNameBean.setSection("cmbpelcSectionid");
			functLocFieldNameBean.setCell("cmbpelcCellid");
			functLocFieldNameBean.setMachine("cmbpelcMachine");
			functLocFieldNameBean.setSectMandatory(true);
			//functLocFieldNameBean.setCompMandatory(true);
			/*if(UIUtils.isValidKeyId(drillLevel)&& drillLevel.equals("FCT"))
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
		*/
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, FormModes.create  );
		
			
			
			
		}
		
		else if (action.equals("pcsEnableDisable_save.pcsEnable")) 
		{
			CommonMessage.debugMsg("Save Called");
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			savePcsEnble(request,response, pcsEnableDisableFormBean);
		}
		else if (action.equals("pcsEnableDisable_delete.pcsEnable")) 
		{
			CommonMessage.debugMsg("delete Called");
			//deletepcsEnble(request,response, null);
		}
		
		
		if (action.equals("pcsEnableDisable_input.pcsEnable")) 
		{
			CommonMessage.debugMsg("Out the jsp");
			UIUtils.forwardRequest(request, response, "/pages/PCSEnableDisable.jsp") ;
		}
		
		
	}	
    private List<PcsTlEnablelosscapture> populatePcsEnbDsbList(List<String[]> pcsEnableList)
	{
		List<PcsTlEnablelosscapture> pelcTblList = new ArrayList<PcsTlEnablelosscapture>();
	 	CommonMessage.debugMsg("Inside populatePillarLinkList"+pcsEnableList.size());
	 	for( String [] row : pcsEnableList)
	 {
	 		PcsTlEnablelosscapture pcsTlEnablelosscapture = new PcsTlEnablelosscapture();
	 		pcsTlEnablelosscapture.setPelcIspcsenabled(row[6]);//1
	 		pcsTlEnablelosscapture.setPelcIsgroupbased(row[5]);
	 		pcsTlEnablelosscapture.setPelcIshtlog(row[2]);
	 		pcsTlEnablelosscapture.setPelcQtyortimebased(row[0]);//0
	 		pcsTlEnablelosscapture.setPelcKeyid(row[1]);//2
	 		pelcTblList.add(pcsTlEnablelosscapture);
	 }
	 		CommonMessage.debugMsg(pelcTblList);
	 		return pelcTblList;
	}
    
    
		private void savePcsEnble(HttpServletRequest request, HttpServletResponse response,PcsEnableDisableFormBean pcsEnableDisableFormBean  ) throws IOException
		{
			System.out.print("Inside Save");
	    	HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	UIUtils.displayRequestParamsValue(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		PcsTlEnablelosscapture newPcsTlEnablelosscapture= new PcsTlEnablelosscapture();
		    		
		    	//	newPcsTlEnablelosscapture =(PcsTlEnablelosscapture)UIUtils.setBeanProperties((Object)newPcsTlEnablelosscapture,request);
		    		pcsEnableDisableFormBean =(PcsEnableDisableFormBean) UIUtils.setBeanProperties((Object)pcsEnableDisableFormBean,request);
		    		
		    		List<PcsTlEnablelosscapture> pcsEblDsblList =null;
		    		PcsTlEnablelosscapture pcsTlEnablelosscapture = new PcsTlEnablelosscapture();
		    		String pcsEnableDisableStr =  request.getParameter("PcsEnableDisable");
		    		String forCell =  request.getParameter("forCell");
		    		String flid =  request.getParameter("flid");
		    		CommonMessage.debugMsg("pcsEnableDisableStr="+pcsEnableDisableStr);
		    		
		    		JSONArray pcsEnableDisableJSON =null;
		    		if( (pcsEnableDisableStr != null && ! pcsEnableDisableStr.isEmpty()))
		    		{	
		    			CommonMessage.debugMsg("pcsEnableDisableStr!=null....");
		    			pcsEnableDisableJSON = JSONArray.fromString(pcsEnableDisableStr);
		    			CommonMessage.debugMsg("pcsEnableDisableJSON="+pcsEnableDisableJSON);
		    			pcsEblDsblList=(List<PcsTlEnablelosscapture>)UIUtils.convertJSONArrToList(pcsTlEnablelosscapture, pcsEnableDisableJSON);
		    			CommonMessage.debugMsg("pcsEblDsblList not null ="+pcsEblDsblList.size());
		    		}
		    		
		    		List<PcsTlEnablelosscapture> existPcsTlEnablelosscapture  = (List<PcsTlEnablelosscapture>)httpSession.getAttribute("pcsTlEnablelosscaptureList");
		    		CommonMessage.debugMsg("existPcsTlEnablelosscapture.size()"+existPcsTlEnablelosscapture.size());
		    		
					if( (UIUtils.isValidKeyId(forCell) && forCell.equals("Y")) ||(pcsEblDsblList != null && pcsEblDsblList.size() > 0))
					{		
						try
						{
							pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
							CommonMessage.debugMsg("Before Save");
							if (UIUtils.isValidKeyId(forCell) &&   forCell.equals("Y"))
								pcsEnableDisableFormBean.setForCell(forCell);
							else
								pcsEnableDisableFormBean.setForCell("N");
							
							pcsEnableDisableFormBean.setFlid(flid);
							
							existPcsTlEnablelosscapture = pcsEnableDisableService.save(pcsEblDsblList,existPcsTlEnablelosscapture,pcsEnableDisableFormBean);
										
							httpSession.setAttribute("existPcsTlEnablelosscaptureServlet", existPcsTlEnablelosscapture);
							JSONObject successData = new JSONObject();
							boolean clearVal=false;
							successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
							JSONObject returnData = new JSONObject();
							returnData.put("formClear",clearVal);
							returnData.put("successData",successData);
							CommonMessage.debugMsg("Mode :"+returnData.toString());
							out.print(returnData.toString());
							out.close();
			
						}
						catch(ValidationExceptions e)
						{
							JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "SprPickupCreation");
							CommonMessage.debugMsg("---------errMessage--------------------"+e.toString());
							out.print(errMessage.toString());
						}
						
						catch(Exception e)
						{
							CommonMessage.debugMsg("Error Msg:" + e.getMessage());
							JSONObject tpmException = new JSONObject();
							JSONObject err = new JSONObject();
							tpmException.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
							//tpmException.put("msg", "Data Not Saved");
							tpmException.put("errMsg" , e.getMessage());
							err.put("tpmException",tpmException);
							out.print(err.toString());
						}
					}
		    	
		       }
		  }
		@SuppressWarnings("unchecked")
		private void savePhenomenaFactLoss(HttpServletRequest request,HttpServletResponse response) throws Exception 
		{
			CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING");
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	if( httpSession != null && user != null)
	    	{	
	    		try 
				{
	    			CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING123");
		    		 PcsTlLossphenomenamst  pcsTlLossphenomenamst = new  PcsTlLossphenomenamst(); 
		    		 String msgPropertyIdnt;			
		    		 pcsTlLossphenomenamst =(PcsTlLossphenomenamst)UIUtils.setBeanProperties((Object)pcsTlLossphenomenamst,request);
		    		 String mstKeyid=pcsTlLossphenomenamst.getPlpmKeyid();
		    		 CommonMessage.debugMsg("the key id = "  + mstKeyid );
		    		 CommonMessage.debugMsg(" mstKeyid "+mstKeyid);
		    		 PcsTlLossphenfactorylink  pcsTlLossphenfactorylink =new PcsTlLossphenfactorylink();
		    		 String factorylist = request.getParameter("factoryList");
					 List<PcsTlLossphenfactorylink> pcsTlLossphenomenamstList = null;
					 JSONArray phenomenalinkJson = null;
					 CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING456");
					 /*if (UIUtils.isValidKeyId(factorylist))
					 {
						phenomenalinkJson = JSONArray.fromString(factorylist);
						pcsTlLossphenomenamstList = (List<PcsTlLossphenfactorylink>) UIUtils.convertJSONArrToList(pcsTlLossphenfactorylink,phenomenalinkJson);
						if (pcsTlLossphenomenamstList != null)
						{
							pcsTlLossphenomenamst.setPcsTlLossphenfactorylink(pcsTlLossphenomenamstList);
						}*/
						if(!UIUtils.isValidKeyId(mstKeyid))
						{
							pcsTlLossphenomenamst=pcsEnableDisableService.createPhenomenaLossFactoryLink(user.getUsrm_ccno(),pcsTlLossphenomenamst);
							msgPropertyIdnt = "success-save";
						}
						else
						{
							pcsTlLossphenomenamst=pcsEnableDisableService.updatePhenomenaLossFactoryLink(user.getUsrm_ccno(),pcsTlLossphenomenamst);
							msgPropertyIdnt = "success-update";
						}
						CommonMessage.debugMsg("pcsTlLossphenomenamst.getPlpmKeyid():"+pcsTlLossphenomenamst.getPlpmKeyid());
						
						JSONObject successData = new JSONObject(); 
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mstKeyid", pcsTlLossphenomenamst.getPlpmKeyid());
						JSONObject returnData = new JSONObject();	
						returnData.put("formClear",false);
						returnData.put("successData", successData);
						out.print(returnData.toString());
						out.close();	
					 }
					 /*else 
					 {
						 throw new BusinessApplicationExceptions("data not saved");
						// throw new BusinessApplicationExceptions("Select Factory To Save");
					 }
	    	}*/
	    		catch(ValidationExceptions e)
				{
					CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"PhenomenaLossReasonLink");
					out.print(errMessage.toString());
				}
				catch(BusinessApplicationExceptions b)
				{
					JSONObject result = new JSONObject();
					result.put("tpmException",b.getMessage());
					out.print(result.toString());
					out.close();

				}
				}
	    	
		}
		
		// --- Add inside PCSEnableDisableServlet.java ---

		// --- Add inside PCSEnableDisableServlet.java ---
		
//		private void deletePhenomenaLoss(HttpServletRequest request, HttpServletResponse response) throws Exception {
//			  CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING");
//				HttpSession httpSession = request.getSession(false);
//		    	ServletOutputStream out = response.getOutputStream();
//		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
//		    	if( httpSession != null && user != null)
//		    	{	
//		    		try 
//					{
//
//
//				 PcsTlLossphenomenamst  pcsTlLossphenomenamst = new  PcsTlLossphenomenamst(); 
//				 String msgPropertyIdnt = null;		
//	    		 pcsTlLossphenomenamst =(PcsTlLossphenomenamst)UIUtils.setBeanProperties((Object)pcsTlLossphenomenamst,request);
//	    		 String mstKeyid=pcsTlLossphenomenamst.getPlpmKeyid();
//	    		 CommonMessage.debugMsg("the key id = "  + mstKeyid );
//	    		 CommonMessage.debugMsg(" mstKeyid "+mstKeyid);
//	    		 PcsTlLossphenfactorylink  pcsTlLossphenfactorylink =new PcsTlLossphenfactorylink();
//	    		 String factorylist = request.getParameter("factoryList");
//				 List<PcsTlLossphenfactorylink> pcsTlLossphenomenamstList = null;
//				 JSONArray phenomenalinkJson = null;
//				 CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING456");
//				
//				 if(UIUtils.isValidKeyId(mstKeyid))
//					{
//						pcsTlLossphenomenamst=pcsEnableDisableService.deletePhenomenaLoss(user.getUsrm_ccno(),pcsTlLossphenomenamst,mstKeyid);
//						msgPropertyIdnt = "success-delete";
//					}
//           
//				 CommonMessage.debugMsg("pcsTlLossphenomenamst.getPlpmKeyid():"+pcsTlLossphenomenamst.getPlpmKeyid());
//						
//						JSONObject successData = new JSONObject(); 
//							
//						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
//						successData.put("mstKeyid", pcsTlLossphenomenamst.getPlpmKeyid());
//						JSONObject returnData = new JSONObject();	
//						returnData.put("formClear",true);
//						returnData.put("successData", successData);
//						out.print(returnData.toString());
//						out.close();
//						
//	  }
//		    			catch(ValidationExceptions e)
//				{
//					CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
//					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"PhenomenaLossReasonLink");
//					out.print(errMessage.toString());
//				}
//				catch(BusinessApplicationExceptions b)
//				{
//					JSONObject result = new JSONObject();
//					result.put("tpmException",b.getMessage());
//					out.print(result.toString());
//					out.close();
//
//				}
//				}
//	    	
//		}
   // ------------ Vignesh -------------------//
		// inside PCSEnableDisableServlet.deletePhenomenaLoss(...)
		private void deletePhenomenaLoss(HttpServletRequest request, HttpServletResponse response) throws Exception {
		    CommonMessage.debugMsg("savePhenomenaFactLoss IS CALLING");
		    HttpSession httpSession = request.getSession(false);
		    response.setContentType("application/json; charset=UTF-8");   // ensure JSON
		    ServletOutputStream out = response.getOutputStream();
		    AdmTlUsermst user = UIUtils.getLoginUser(request);

		    if (httpSession != null && user != null) {
		        try {
		            PcsTlLossphenomenamst pcsTlLossphenomenamst = new PcsTlLossphenomenamst();
		            String msgPropertyIdnt = null;

		            pcsTlLossphenomenamst = (PcsTlLossphenomenamst) UIUtils.setBeanProperties(pcsTlLossphenomenamst, request);
		            String mstKeyid = pcsTlLossphenomenamst.getPlpmKeyid();
		            CommonMessage.debugMsg("the key id = " + mstKeyid);
		            CommonMessage.debugMsg(" mstKeyid " + mstKeyid);

		            if (UIUtils.isValidKeyId(mstKeyid)) {
		                pcsTlLossphenomenamst = pcsEnableDisableService.deletePhenomenaLoss(
		                        user.getUsrm_ccno(), pcsTlLossphenomenamst, mstKeyid);
		                msgPropertyIdnt = "success-delete";
		            }

		            CommonMessage.debugMsg("pcsTlLossphenomenamst.getPlpmKeyid():" + pcsTlLossphenomenamst.getPlpmKeyid());

		            JSONObject successData = new JSONObject();
		            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
		            successData.put("mstKeyid", pcsTlLossphenomenamst.getPlpmKeyid());

		            JSONObject returnData = new JSONObject();
		            returnData.put("formClear", true);
		            returnData.put("successData", successData);

		            out.print(returnData.toString());
		            out.close();

		        } catch (ValidationExceptions e) {
		            CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
		            JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "PhenomenaLossReasonLink");
		            out.print(errMessage.toString());
		            out.close();

		        } catch (BusinessApplicationExceptions b) {
		            // keep existing shape for pages that expect string
		            JSONObject result = new JSONObject();
		            result.put("tpmException", b.getMessage());
		            // also add `.confirm` for pages that expect nested.confirm
		            result.put("tpmExceptionObj", new JSONObject().put("confirm", b.getMessage()));
		            out.print(result.toString());
		            out.close();

		        } catch (Exception e) { // ✅ NEW: catch generic Exceptions from DAO/service
		            // Send a JSON payload the UI can alert
		            String msg = e.getMessage() == null ? "Delete failed." : e.getMessage();
		            JSONObject result = new JSONObject();
		            // string shape (legacy)
		            result.put("tpmException", msg);
		            // object shape (confirm) for handlers that expect it
		            result.put("tpmExceptionObj", new JSONObject().put("confirm", msg));
		            out.print(result.toString());
		            out.close();
		        }
		    }
		}

		
//		
//		private void deletePhenomenaLoss(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		    response.setContentType("application/json;charset=UTF-8");
//
//		    try (java.io.PrintWriter out = response.getWriter()) {
//		        CommonMessage.debugMsg("delete PhenomenaFactLoss IS CALLING");
//
//		        HttpSession httpSession = request.getSession(false);
//		        AdmTlUsermst user = UIUtils.getLoginUser(request);
//		        if (httpSession == null || user == null) {
//		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		            out.print("{\"status\":\"ERROR\",\"message\":\"Session expired. Please login again.\"}");
//		            return;
//		        }
//
//		        // Map bean
//		        PcsTlLossphenomenamst bean = new PcsTlLossphenomenamst();
//		        UIUtils.setBeanProperties(bean, request); // populates plpmKeyid if param name matches
//
//		        // Be tolerant to both plpmKeyid & plpm_keyid
//		        String mstKeyid = bean.getPlpmKeyid();
//		        if (mstKeyid == null || mstKeyid.trim().isEmpty()) {
//		            String alt = request.getParameter("plpm_keyid");
//		            if (alt != null && !alt.trim().isEmpty()) {
//		                bean.setPlpmKeyid(alt.trim());
//		                mstKeyid = alt.trim();
//		            }
//		        }
//
//		        CommonMessage.debugMsg("the key id = " + mstKeyid);
//		        CommonMessage.debugMsg(" mstKeyid " + mstKeyid);
//
//		        if (!UIUtils.isValidKeyId(mstKeyid)) {
//		            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		            out.print("{\"status\":\"ERROR\",\"message\":\"Missing plpmKeyid\"}");
//		            return;
//		        }
//
//		        try {
//		            bean = pcsEnableDisableService.deletePhenomenaLoss(user.getUsrm_ccno(), bean);
//
//		            JSONObject successData = new JSONObject();
//		            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
//		            successData.put("mstKeyid", bean.getPlpmKeyid());
//
//		            JSONObject ret = new JSONObject();
//		            ret.put("formClear", true);
//		            ret.put("successData", successData);
//
//		            out.print(ret.toString());
//		        }
//		        catch (ValidationExceptions ve) {
//		            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		            JSONObject err = UIUtils.validationExceptions(ve.toString(), "PhenomenaLossReasonLink");
//		            out.print(err.toString());
//		        }
//		        catch (BusinessApplicationExceptions be) {
//		            response.setStatus(HttpServletResponse.SC_CONFLICT);
//		            JSONObject err = new JSONObject();
//		            err.put("status", "ERROR");
//		            err.put("message", be.getMessage());
//		            out.print(err.toString());
//		        }
//		        catch (Exception e) { // <— critical: convert your generic Exception to JSON
//		            response.setStatus(HttpServletResponse.SC_CONFLICT);
//		            JSONObject err = new JSONObject();
//		            err.put("status", "ERROR");
//		            err.put("message", e.getMessage()); // e.g., “Cannot delete. This Phenomena is mapped…”
//		            out.print(err.toString());
//		        }
//		    }
//		}

		
		@SuppressWarnings("unchecked")
		private void saveLossJH(HttpServletRequest request,HttpServletResponse response) throws Exception 
		{
			
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	if( httpSession != null && user != null)
	    	{	
	    		try 
				{
	    			PcsTlLosscelllink  pcsTlLosscelllink = new PcsTlLosscelllink();
	    			 String msgPropertyIdnt;			
	    			 pcsTlLosscelllink =(PcsTlLosscelllink)UIUtils.setBeanProperties((Object)pcsTlLosscelllink,request);
		    		 
		    		 String jhLossList = request.getParameter("jhLossList");
					 List<PcsTlLosscelllink> pcsTlLosscelllinkList = null;
					 JSONArray jhLossJson = null;
					 
					 if (UIUtils.isValidKeyId(jhLossList))
					 {
						CommonMessage.debugMsg("jhLossList"+jhLossList);
						 
						jhLossJson = JSONArray.fromString(jhLossList);
						CommonMessage.debugMsg("jhLossList"+jhLossJson.length());
						pcsTlLosscelllinkList = (List<PcsTlLosscelllink>) UIUtils.convertJSONArrToList(pcsTlLosscelllink,jhLossJson);	
						
						CommonMessage.debugMsg("pcsTlLosscelllinkList-Servlet.size"+pcsTlLosscelllinkList.size());
						
						pcsTlLosscelllink=pcsEnableDisableService.createLossJHLink(user.getUsrm_ccno(),pcsTlLosscelllinkList);
						msgPropertyIdnt = "success-save";
					
						CommonMessage.debugMsg("pcsTlLossphenomenamst.getPlflkeyid():"+pcsTlLosscelllink.getPlflKeyid());
						JSONObject successData = new JSONObject(); 
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mstKeyid", pcsTlLosscelllink.getPlflKeyid());
						JSONObject returnData = new JSONObject();	
						returnData.put("formClear",false);
						returnData.put("successData", successData);
						out.print(returnData.toString());
						out.close();	
					 }
					 else 
					 {
						 throw new BusinessApplicationExceptions("Select Factory To Save");
					 }
	    	}
	    		catch(ValidationExceptions e)
				{
					CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"PhenomenaLossReasonLink");
					out.print(errMessage.toString());
				}
				catch(BusinessApplicationExceptions b)
				{
					JSONObject result = new JSONObject();
					result.put("tpmException",b.getMessage());
					out.print(result.toString());
					out.close();

				}
				}
	    	
		}
		
		private void savePhenomenaFactMaping(HttpServletRequest request,HttpServletResponse response) throws Exception 
		{
			
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			UIUtils.displayRequestParamsValue(request);
			if (httpSession != null && user != null) 
				{
					try 
					{
						CommonMessage.debugMsg("2");
						
						PcsTlLossphenfactorylink pcsTlLossphenfactorylink =new PcsTlLossphenfactorylink();
						pcsTlLossphenfactorylink = (PcsTlLossphenfactorylink) UIUtils.setBeanProperties((Object) pcsTlLossphenfactorylink,request);
						String pillFactlist = request.getParameter("selectedPillFactIDs");
						String deleteLink = request.getParameter("deleteLinkList");
						String factId=request.getParameter("cmbFactoryid");
						String sectId=request.getParameter("cmbSectionid");
						String cellId=request.getParameter("cmbCellid");
						String deptId="";
						if(UIUtils.isValidDate(factId)&&!UIUtils.isValidKeyId(sectId)&&!UIUtils.isValidKeyId(cellId))
							deptId=factId;
						else if(UIUtils.isValidKeyId(sectId)&&!UIUtils.isValidKeyId(cellId))
							deptId=sectId;
						else if(UIUtils.isValidKeyId(cellId))
							deptId=cellId;
						CommonMessage.debugMsg("3");	
						List<PcsTlLossphenfactorylink> pillarFactLinkList = null;					
						JSONArray pillFactlinkJson = null;
						CommonMessage.debugMsg("pillFactlist" + pillFactlist);
						//Ok
						if (UIUtils.isValidKeyId(pillFactlist)) 
						{
							CommonMessage.debugMsg("4");
							pillFactlinkJson = JSONArray.fromString(pillFactlist);
							pillarFactLinkList = (List<PcsTlLossphenfactorylink>) UIUtils.convertJSONArrToList(pcsTlLossphenfactorylink,pillFactlinkJson);
							CommonMessage.debugMsg( "size:" +pillarFactLinkList.size());
							if (pillarFactLinkList != null)
							{
								for( PcsTlLossphenfactorylink phenomenalink : pillarFactLinkList)
								{
									phenomenalink.setIsDelete("N");
									CommonMessage.debugMsg( "save PhenomenaFactoryId:" +phenomenalink.getPpflFactoryid());
									CommonMessage.debugMsg( "save PhenomenaKeyId:" +phenomenalink.getPpflPlpmKeyid());
								}
							}
						}
						//ok
						List<PcsTlLossphenfactorylink> deleteFactLinkList = null;
						JSONArray deleteFactLinkJson = null;
						CommonMessage.debugMsg( "deleteLink"+deleteLink);
						if (UIUtils.isValidKeyId(deleteLink)) 
						{
							deleteFactLinkJson = JSONArray.fromString(deleteLink);
							deleteFactLinkList = (List<PcsTlLossphenfactorylink>) UIUtils.convertJSONArrToList(pcsTlLossphenfactorylink,deleteFactLinkJson);
							if (deleteFactLinkList != null)
							{							
								if(pillarFactLinkList==null){
									//CommonMessage.debugMsg( "fdfd resize pillarFactLinkList");
									pillarFactLinkList=new ArrayList<PcsTlLossphenfactorylink>();
								}
								for( PcsTlLossphenfactorylink genKpiTlIndicatorDeptLink : deleteFactLinkList)
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
							pcsTlLossphenfactorylink.setmethodPillarFactlink(pillarFactLinkList);
						}	
						String pillCode = request.getParameter("pillarcode");
						String drillLevel=request.getParameter("drillLevel");
						String flid  = request.getParameter("flid");
						String elemtype =request.getParameter("elemtype");
						deptId = flid;
						drillLevel = elemtype;
						
						CommonMessage.debugMsg( "flidelemtype" + flid + elemtype);
						CommonMessage.debugMsg("JSP param isIndicatorFactory=" + request.getParameter("isIndicatorFactory"));
						CommonMessage.debugMsg("JSP param usrm_ccno=" + user.getUsrm_ccno()); // whatever you use
						CommonMessage.debugMsg("JSP param pillCode=" + request.getParameter("pillCode"));
						CommonMessage.debugMsg("JSP param deptId=" + request.getParameter("deptId"));
						CommonMessage.debugMsg("JSP param drillLevel=" + request.getParameter("drillLevel"));

						/*PcsTlLossphenfactorylink.setDeptid(flid);
						PcsTlLossphenfactorylink.setDepttype(elemtype);*/
						
						String isIndicatorFactory="";
						if(UIUtils.isValidKeyId(pillFactlist))
							isIndicatorFactory="true";
						else
							isIndicatorFactory="false";
						pcsTlLossphenfactorylink = pcsEnableDisableService.savemultiple1(pcsTlLossphenfactorylink, user.getUsrm_ccno(), pillCode, drillLevel, deptId,isIndicatorFactory);
			              // pcsEnableDisableService.savemultiple1(pcsTlLossphenfactorylink);
			            CommonMessage.debugMsg("6");
						String msgPropertyIdnt;
						msgPropertyIdnt = "success-save";
						JSONObject successData = new JSONObject();
						successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					//	successData.put("pillCode",pillCode);
						JSONObject result = new JSONObject();
						result.put("formClear", false);
						result.put("displyMsg", true);
						result.put("successData", successData);
						out.print(result.toString());
						out.close();
					}
					catch (BusinessApplicationExceptions e) 
					{
						CommonMessage.debugMsg("gete2. " + e);
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", e.getMessage());//"Data Not Saved");
						out.print(err.toString());
					}
					catch (Exception e) 
					{
						CommonMessage.debugMsg("gete1. " + e);
						CommonMessage.debugMsg("gete. " + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException","Data Not Saved");
						out.print(err.toString());
					}
				}
	    }

		
		
		
		private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
		{
			HttpSession httpSession = request.getSession(false);
			
			
			CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
			if( commonFilter != null && ! createNew ){
				// -- vignesh added 
				commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
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
		private void getColAction(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession ) throws Exception
		{
			PrintWriter out1 = response.getWriter();
			List<String[]> PFLList= getDataforColmodel(request,response,httpSession);
			httpSession.removeAttribute("PFLProdgridParams");
			String fromExcel="false";
			net.sf.json.JSONObject jsonObject=null;
			jsonObject=getTableModel(PFLList,fromExcel);
			jsonObject.set("tableHeight", "65%%");
			jsonObject.set("tableWidth", "95%%");
			jsonObject.set("rowNumbers", true);
			httpSession.removeAttribute("PFLProdColModel");
			httpSession.setAttribute("PFLProdColModel", jsonObject);
			out1.println(jsonObject);
			out1.close();
		}
		private net.sf.json.JSONObject getTableModel(List<String[]> headers, String fromExcel)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeaderTemp=headers.get(1); // -- changing 1 to 0 vignesh
			int size=0;
			/*if(fromExcel.equals("false"))
			size=(colHeaderTemp.length-3)*2;
			else if(fromExcel.equals("true"))*/
			size=colHeaderTemp.length-1;  // -- changing -1 to 0 vignesh
			String[] colHeader= new String[size];
			String [] colHeader2=new String[size];
			CommonMessage.debugMsg("size:"+size);
			
			for(int i=0;i<size;i++)
			{
				
				if(i==0)
				{colHeader[i]="phenomenaID";
				if(fromExcel.equals("false"))
				colHeader2[i]="phenomenaID";
				}
				else if(i==1)
				{
					colHeader[i]="Phenomena Name";
					if(fromExcel.equals("false"))
					colHeader2[i]="Phenomena Name";
				}
				
				else
				{
					colHeader[i] = headers.get(0)[i]; // chnaging i to i+1
					if(fromExcel.equals("false"))
					colHeader2[i] =headers.get(1)[i]; // chnaging i to i+1
				//	j++;	
				//	k++;
				}
				// changing the else block vignesh
//				else
//				{
//				    // We have an extra technical column at index 1,
//				    // so from i >= 2 we read from source index i+1
//				    int srcIndex = i;
//
//				    if (i >= 2 && i < size - 1) {          // 2 .. size-2 => shift by +1
//				        srcIndex = i + 1;
//				    }
//
//				    colHeader[i] = headers.get(0)[srcIndex];
//
//				    if (fromExcel.equals("false")) {
//				        colHeader2[i] = headers.get(1)[srcIndex];
//				    }
//				}

				
			}
			jqGridTableModel.getRowHeaders().add(colHeader);
			if(fromExcel.equals("false"))
			jqGridTableModel.getRowHeaders().add(colHeader2); 
			
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			for(int i=0;i<size;i++) // changing size to size + 1
			{
				CommonMessage.debugMsg("inside for colHeader["+i+"] = "+colHeader[i]); 
				
				if(i==0)
				{
					
					jqGridTableModel.getColModel().add(getColModel("phenomenaID", 148,"left",true,false,false));
				}
				else if(i==1)
					jqGridTableModel.getColModel().add(getColModel("Name", 220,"left",false,false,false));
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
			if(!(colIndex.equals("Name")||colIndex.equals("phenomenaID"))
					  &&!(colIndex.contains("checkPillarvalue")||colIndex.contains("isDelete")||colIndex.contains("isKeyid")) )
			{
				jqGridColModel.setFormatter("chkbox_factoryPillar");
			}
			else
				jqGridColModel.setEditable(false);
			return jqGridColModel;
		}
		
		
		
		private  List<String[]> getDataforColmodel(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession) throws Exception
		{
			String CompId=request.getParameter("compId");
			String factId=request.getParameter("factId");
			//String indicatorId=request.getParameter("indicatorId");
			String pillCode=request.getParameter("pillCode");
			String sectId=request.getParameter("sectId");
			String cellId=request.getParameter("cellId");
			String drillLevel=request.getParameter("drillLevel");
			String flid=request.getParameter("flid");
			String type = request.getParameter("type");
			String drillLevel1=(String)httpSession.getAttribute("drillLevel");
			response.setContentType("text/html");
			response.setContentType("text/json");
			GridParams gridParams = (GridParams) httpSession.getAttribute("PFLProdgridParams");
			httpSession.setAttribute("PFLProdgridParams", gridParams);
			if (gridParams == null)
			gridParams = new GridParams();
			//FilterValues.populateGridParams(request, gridParams);
			//CommonMessage.debugMsg("CompId" + CompId + "factId" +  factId + "sectId" +  sectId + "cellId" + cellId + "drillLevel" + drillLevel);
			FilterValues.populateGridParams(request, gridParams);
			CommonFilter commonFilter=new CommonFilter();
			commonFilter = populateCommonFilter(request,"PFLCommonFilter",true);
			commonFilter.setFlid(flid);
			commonFilter.setComp(CompId);
			commonFilter.setFactoryId(factId);
			commonFilter.setSectionId(sectId);
			commonFilter.setCellId(cellId);
			commonFilter.setDrillLevel(drillLevel);
			//commonFilter.setIndicator(indicatorId);
			commonFilter.setPillarWise(pillCode);
			commonFilter.setType(type);
			commonFilter.setIsGetCol("Y");
			List<String[]> PFLList  = pcsEnableDisableService.getPFLProd(gridParams,commonFilter);
			String totlarowcount = String.valueOf(PFLList.size());
			
			long totalRwCnt= Long.parseLong(totlarowcount);
			gridParams.setTotalRecordCnt(totalRwCnt);
			httpSession.removeAttribute("PFLProdgridParams");
			//CommonMessage.debugMsg("gridParams3=>"+gridParams);
			httpSession.setAttribute("PFLProdgridParams" ,gridParams);
			return PFLList;		
		}
		
		private void getDataAction(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) throws Exception
		{
			PrintWriter out1 = response.getWriter();
		//	String indicatorId=request.getParameter("indicatorId");
			String pillCode=request.getParameter("pillCode");
			String pillarId=request.getParameter("pillarId");
			String CompId=request.getParameter("compId");
			String factId=request.getParameter("factId");
			String sectId=request.getParameter("sectId");
			String cellId=request.getParameter("cellId");
			String drillLevel=request.getParameter("drillLevel");
			String flid=request.getParameter("flid");
			String type = request.getParameter("type");
			//String drillLevel=(String)httpSession.getAttribute("drillLevel");
			//CommonMessage.debugMsg("drillLevel" + drillLevel);
		//	httpSession.removeAttribute("indicatorId");
		//	httpSession.setAttribute("indicatorId", indicatorId);
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
		//	CommonMessage.debugMsg("indicatorId:"+indicatorId);
			GridParams gridParams = (GridParams) httpSession.getAttribute("PFLProdgridParams");
			httpSession.setAttribute("PFLProdgridParams", gridParams);
			if (gridParams == null)
				gridParams = new GridParams();
			FilterValues.populateGridParams(request, gridParams);
			CommonFilter commonFilter=new CommonFilter();
			commonFilter = populateCommonFilter(request,"PFLCommonFilter",true);
			commonFilter.setFlid(flid);
			commonFilter.setComp(CompId);
			commonFilter.setFactoryId(factId);
			commonFilter.setSectionId(sectId);
			commonFilter.setCellId(cellId);
			commonFilter.setDrillLevel(drillLevel);
		//	commonFilter.setIndicator(indicatorId);
			commonFilter.setPillarWise(pillarId);
			commonFilter.setDrillLevel(drillLevel);
			commonFilter.setType(type);
			commonFilter.setIsGetCol("N");
			List<String[]> pcsComplianceList  = pcsEnableDisableService.getPFLProd(gridParams,commonFilter);
			JSONObject listToJsonObject = new JSONObject();
			long  recrdCnt=gridParams.getTotalRecordCnt();
			if(recrdCnt>1)
				 recrdCnt+=2; // changed col start to 1 from 0 vignesh
			listToJsonObject =convertToJqGridTableObject(pcsComplianceList,request,2,0,0,commonFilter.getTotalRecordCnt());
			httpSession.removeAttribute("PFLProdgridParams");
			out1.println(listToJsonObject);
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
		 //my area
		 private void validatePheLink(HttpServletRequest request, HttpServletResponse response,PrintWriter out ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
				String phenomenaID = request.getParameter("phenomenaID");	
				String flId=request.getParameter("flId");
				String rowNo=request.getParameter("rowNo");
				String position=request.getParameter("position");
				Boolean isValidate=true;
				String msg="";
				CommonMessage.debugMsg("phenomenaID: "+phenomenaID+"flId"+flId+"rowNo"+rowNo+"position"+position);	
				PcsTlLossphenfactorylink pcsTlLossphenfactorylink = new PcsTlLossphenfactorylink();
				if(CommonFunctions.isValidKeyId(phenomenaID))
					pcsTlLossphenfactorylink.setPpflPlpmKeyid(phenomenaID);
				if(CommonFunctions.isValidKeyId(flId))
					pcsTlLossphenfactorylink.setPpflFactoryid(flId);
				try {
					msg =pcsEnableDisableService.validatePhenomenaLink(pcsTlLossphenfactorylink);
					CommonMessage.debugMsg("isValidate : "+isValidate);	
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

}
