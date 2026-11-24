package com.akranta.tpm.controller;
/**Created By : Siddharth . Anand  **/
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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.bean.PcsCycletimemstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.BAL_PcsTlCycletimemst;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;
import com.akranta.tpm.service.BAL_PcsTlCycletimemstService;
//import com.akranta.tpm.service.impl.BAL_CBMTrendServiceImpl;
import com.akranta.tpm.service.impl.BAL_PcsTlCycletimemstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

public class BAL_CellManningServlet  extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private BAL_PcsTlCycletimemstService pcsTlCycletimemstService;
	public BAL_CellManningServlet() throws Exception
	{
		//pcsTlCycletimemstService = new PcsTlCycletimemstServiceImpl();	
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		comboFilter=UIUtils.fillComboFilter(request);
		
		String dispatchUrl = null; 
		HttpSession httpSession = request.getSession(false);
		try {
			pcsTlCycletimemstService = (BAL_PcsTlCycletimemstServiceImpl)UIUtils.getServiceObject(request,"BAL_PcsTlCycletimemstServiceImpl");
			pcsTlCycletimemstService.BAL_PcsTlCycletimemstServiceImplJwt( 
					(String) (httpSession.getAttribute("tpmjwttoken") == null 
					? "" : httpSession.getAttribute("tpmjwttoken")) 
					);
		} catch (ServiceObjectCreationException e) {
			com.akranta.tpm.utils.CommonFunctions.debugMsg(e);
		}
		
		//jwt
		pcsTlCycletimemstService.BAL_PcsTlCycletimemstServiceImplJwt( 
				(String) (httpSession.getAttribute("tpmjwttoken") == null 
				? "" : httpSession.getAttribute("tpmjwttoken")) 
				);
		
		if (action.equals("MchWiseCycTim_input.balcllmng")) 
		{
			//dispatchUrl ="/pages/MchWiseCyc.jsp";
		dispatchUrl="/pages/BAL_MachineManning.jsp";
		}
		
		
		if (action.equals("CellMng_input.balcllmng")) 
		{
			dispatchUrl ="/pages/BAL_CellManning.jsp";
		}
		
		else if (action.equals("CellMng_getCol.balcllmng")) 
		{
			
			try
			{
				PrintWriter out = response.getWriter();
				String cellManningColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsCycletimeRelatedColModel", "pcsCycletimeMCHGrid");
				out.print(cellManningColM);
  		    }
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		
//		  else if (action.equals("CellMng_getData.balcllmng")) {
//		  CommonFunctions.debugMsg("Inside the getData"); try { PrintWriter out =
//		 response.getWriter(); List<
//		 String[]>cellManningList=pcsTlCycletimemstService.getPcsData(); JSONObject
//		  cellManningtData =
//		  UIUtils.convertToJqGridTableObject(cellManningList,request,0,0);
//		  CommonFunctions.debugMsg("CellManningData"+cellManningtData);
//		  out.println(cellManningtData); } catch(Exception e) {
//		  System.out.println(e.getMessage()); } }
		 
		
//		else if (action.equals("CellMng_getData.balcllmng")) {
//		    try {
//		        PrintWriter out = response.getWriter();
//		        String cellId = request.getParameter("cellId");
//		        List<String[]> cellManningList = pcsTlCycletimemstService.getPcsData(cellId);
//		        if (cellManningList == null) cellManningList = new ArrayList<String[]>();
//		        JSONObject cellManningtData = UIUtils.convertToJqGridTableObject(cellManningList, request, 0, 0);
//		        out.println(cellManningtData);
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//		}
		
		else if (action.equals("CellMng_getData.balcllmng")) {
		    try {
		        PrintWriter out = response.getWriter();
		        String cellId    = request.getParameter("cellId");
		        String machineId = request.getParameter("mchId");      // optional
		        String productId = request.getParameter("productId");  // optional

		        GridParams gridParams = new GridParams();
		        FilterValues.populateGridParams(request, gridParams);

		        List<String[]> cellManningList =
		            pcsTlCycletimemstService.getCellCycletimeData(cellId, machineId, productId, gridParams);
		        if (cellManningList == null) cellManningList = new ArrayList<String[]>();

		        JSONObject cellManningData = UIUtils.convertToJqGridTableObject(cellManningList, request, 1, 1);
		        out.println(cellManningData);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		/*
		 * else if (action.equals("CellMng_getData.balcllmng")) { try { PrintWriter out
		 * = response.getWriter(); String cellId = request.getParameter("cellId");
		 * String productId = request.getParameter("productId");
		 * 
		 * GridParams gridParams = (GridParams)
		 * httpSession.getAttribute("AreaManningGridParams"); if (gridParams == null)
		 * gridParams = new GridParams(); FilterValues.populateGridParams(request,
		 * gridParams);
		 * 
		 * List<String[]> cellManningList =
		 * pcsTlCycletimemstService.getPcsEntryData(cellId, productId, gridParams);
		 * 
		 * httpSession.removeAttribute("AreaManningGridParams");
		 * httpSession.setAttribute("AreaManningGridParams", gridParams);
		 * 
		 * JSONObject cellManningData =
		 * UIUtils.convertToJqGridTableObject(cellManningList, request, 1, 1);
		 * out.println(cellManningData); } catch(Exception e) {
		 * System.out.println(e.getMessage()); } }
		 */		
		else if(action.equals("PcsCycletimePrdWise_input.balcllmng"))
		{
			
		}
		else if(action.equals("PcsCycletimePrdWise_getCol.balcllmng"))
		{
			try
			{
				httpSession.removeAttribute("AreaManningGridParams");
				PrintWriter out = response.getWriter();
				String type = request.getParameter("type");
				CommonFunctions.debugMsg("type...."+type);
				String kznCompleteColM;
				kznCompleteColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsCycletimeRelatedColModel", "pcsCycletimeEntryModePRDGridWithDate");//pcsCycletimeEntryModePRDGrid
				out.print(kznCompleteColM);
  		    }
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		else if(action.equals("PcsCycletimePrdWise_getData.balcllmng"))
		{
			try 
			{
				PrintWriter out = response.getWriter();
				String cellId=request.getParameter("cellId");
				CommonFunctions.debugMsg("CellID is"+cellId);
				String machineId=request.getParameter("mchId");
				String productId=request.getParameter("productId");
				CommonFunctions.debugMsg("Productid"+productId);
				//String prodGroupId=request.getParameter("prodGrpId");
				String pcsMode=request.getParameter("pcsMode");
				CommonFunctions.debugMsg("pcsMode ="+pcsMode);
				httpSession.removeAttribute("CellManningFormMode");
				if(UIUtils.isValidKeyId(pcsMode) && pcsMode.equalsIgnoreCase("entry"))
				{	
					httpSession.setAttribute("CellManningFormMode", FormModes.view); 
				}
				GridParams gridParams =(GridParams)httpSession.getAttribute("AreaManningGridParams");
				if( gridParams == null )
					gridParams = new GridParams();
				FilterValues.populateGridParams(request,gridParams);
				//CommonFunctions.debugMsg("cellId ="+cellId+"mchId="+machineId+"prodGroupId ="+prodGroupId+"productId ="+productId);
				//List< String[]>pcsentryModeList=pcsTlCycletimemstService.getPcsPrdData(cellId,machineId,productId,prodGroupId,gridParams);
				List< String[]>pcsentryModeList=pcsTlCycletimemstService.getPcsPrdData(cellId, productId,gridParams);
				int totalCount = pcsTlCycletimemstService.getTotalCountSql(cellId, productId,gridParams);
				Long totalcnt = (long)totalCount;
				httpSession.removeAttribute("AreaManningGridParams");
				httpSession.setAttribute("AreaManningGridParams",gridParams);
				JSONObject pcsentryModeData = UIUtils.convertToJqGridTableObject(pcsentryModeList,request,0,0,totalcnt);
                CommonFunctions.debugMsg("EntryModeData"+pcsentryModeData);         				
				List<BAL_PcsTlCycletimemst> pcsTlCycletimemstList =	populatePcsList(pcsentryModeList);
				System.out.println("pcsTlCycletimemstList.size()"+pcsTlCycletimemstList.size());	
				out.println(pcsentryModeData);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		//elumalai
		else if(action.equals("PcsCycletimeEntryMode_getCol.balcllmng"))
		{
			try
			{
				httpSession.removeAttribute("AreaManningGridParams");
				PrintWriter out = response.getWriter();
				String type = request.getParameter("type");
				CommonFunctions.debugMsg("type...."+type);
				String kznCompleteColM;
				if("PRD".equals(type))
					kznCompleteColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsCycletimeRelatedColModel", "pcsCycletimeEntryModePRDGrid");
				else					
					kznCompleteColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsCycletimeRelatedColModel", "pcsCycletimeEntryModeMCHGrid");
				out.print(kznCompleteColM);
  		    }
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		//elumalai
		else if(action.equals("PcsCycletimeEntryMode_getData.balcllmng"))
		{
			try 
			{
				PrintWriter out = response.getWriter();
				String cellId=request.getParameter("cellId");
				CommonFunctions.debugMsg("The CellId"+cellId);
				//elumalai machine wise
			
			    String machineId=request.getParameter("mchId");
			    if (UIUtils.isValidKeyId(machineId)) {
			    	System.out.println("inside If");
			    	cellId=machineId;
			    }
			    else {
			    	cellId=cellId;
			    	System.out.println("inside else");
			    }
			
			    CommonFunctions.debugMsg("The MachineId" + machineId);
				String productId=request.getParameter("productId");
				CommonFunctions.debugMsg("The ProductId"+productId);
				//elumalai
				String type = request.getParameter("type"); 
			//	String prodGroupId=request.getParameter("prodGrpId");
				String pcsMode=request.getParameter("pcsMode");
				CommonFunctions.debugMsg("pcsMode ="+pcsMode);
				
				
				httpSession.removeAttribute("CellManningFormMode");
				if(UIUtils.isValidKeyId(pcsMode) && pcsMode.equalsIgnoreCase("entry"))
				{	
					httpSession.setAttribute("CellManningFormMode", FormModes.view); 
				}
				
				GridParams gridParams =(GridParams) httpSession.getAttribute("AreaManningGridParams");
				if( gridParams == null)
					gridParams = new GridParams();
				FilterValues.populateGridParams(request,gridParams );
				//CommonFunctions.debugMsg("cellId ="+cellId+"mchId="+productId +="+productId);
				
				//List< String[]>pcsentryModeList=pcsTlCycletimemstService.getPcsEntryData(cellId,machineId,productId,prodGroupId,gridParams);
				

                List< String[]>pcsentryModeList=pcsTlCycletimemstService.getPcsEntryData(cellId,productId,gridParams);			
			   //totalCount = pcsTlCycletimemstService.getTotalCountSql(cellId, machineId, productId, prodGroupId,gridParams);
             
				//int totalCount = pcsTlCycletimemstService.getTotalCountSql(cellId,productId,gridParams);
				//CommonFunctions.debugMsg("Total Count"+totalCount);
				httpSession.removeAttribute("AreaManningGridParams");
				httpSession.setAttribute("AreaManningGridParams" ,gridParams);
				JSONObject pcsentryModeData = UIUtils.convertToJqGridTableObject(pcsentryModeList,request,1,0);
				//List<BAL_PcsTlCycletimemst> pcsTlCycletimemstList =	populatePcsList(pcsentryModeList);
				//System.out.println("pcsTlCycletimemstList.size()"+pcsTlCycletimemstList.size());	
				out.println(pcsentryModeData);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else if(action.equals("MchWiseCycTim_save.balcllmng"))
		{
			String product = request.getParameter("product");
			String cycleTime = request.getParameter("cycleTime");
			String prdWiseCycleDatal = request.getParameter("gridData");
			
			//pcsTlCycletimemstService.getcycleTimeUpdate(product,cycleTime);
			
			PcsCycletimemstBean pcsCycletimemstBean = new PcsCycletimemstBean();
			saveCellManning(request, response, pcsCycletimemstBean);
			
		}
		else if(action.equals("PcsCycletimeEntryMode_getExcel.balcllmng"))
		{
			CommonFilter commonFilter = new CommonFilter();
			String tmpFromRow = commonFilter.getFromRow();
			
			
			commonFilter.setFromRow(null);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsCycletimeRelatedColModel", "pcsCycletimeMCHGrid");
			
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			tblJSONObj.put("title", "Area Manning Details");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = pcsTlCycletimemstService.cellMngExportExcel(commonFilter,tblJSONObj,format);
			commonFilter.setFromRow(tmpFromRow);
				
			ExcelUtils.writeToResponse(response, wb, "Area-Manning", format);
		}
		
	/*	else if(action.equals("cellCom.cllmng")){
			
			CommonFunctions.debugMsg("Inside cellCombo");
			ComboFilter Combofilter=UIUtils.fillComboFilter(request);
			List<ComboBox> cellKey=pcsTlCycletimemstService.getCellCombo(Combofilter);
			CommonFunctions.debugMsg("Combo CellKey"+cellKey);
			UIUtils.writeComboBox(response,cellKey,Combofilter);
		} */
		
		else if(action.equals("combo_subgroup.balcllmng"))
		{
			try {
				String conSql = request.getParameter("prmmKeyid");
				CommonFunctions.debugMsg(" conSql ::::::::::"+conSql);
				List<ComboBox>  psgmKeyid = pcsTlCycletimemstService.getSubGroupCombo(conSql);
				UIUtils.writeComboBox(response, psgmKeyid, comboFilter);
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		else if(action.equals("combo_product.balcllmng"))
		{
			try {
				String factId = request.getParameter("factId");
				/*String prdModelId = request.getParameter("prdModelId");*/
				String cellId = request.getParameter("cellId");
				CommonFunctions.debugMsg("factIdfactId comboproductfatcoryid"+factId);
				ComboFilter currentFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  assembly = pcsTlCycletimemstService.getProductCombo(currentFilter, factId, cellId);
				CommonFunctions.debugMsg("assembly.size"+assembly.size());
				if (assembly.size()>1)
					CommonFunctions.debugMsg("assembly.size"+assembly.get(0));
				UIUtils.writeComboBox(response, assembly,comboFilter);
				
			/*	List<ComboBox>  psgmKeyid = pcsTlCycletimemstService.getProductCombo("");
				UIUtils.writeComboBox(response, psgmKeyid, comboFilter);*/
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		else if(action.equals("functionalLoc.balcllmng"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbFactory");
			functLocFieldNameBean.setSection("cmbcytmSectionid");
			functLocFieldNameBean.setCell("cmbcytmCellid");
			functLocFieldNameBean.setMachine("cmbcytmMachineid1");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
            functLocFieldNameBean.setMachDisable(true);			
			FormModes formModes = (FormModes)httpSession.getAttribute("CellManningFormMode");
			
			if( formModes == FormModes.view)
				formModes = FormModes.view;
			else
				formModes = FormModes.create;
			
			CommonFunctions.debugMsg(" FORM MODES-----------DDDDD"+formModes);
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		
		
		
		else if(action.equals("functionalLocMch.balcllmng"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbFactory");
			functLocFieldNameBean.setSection("cmbcytmSectionid");
			functLocFieldNameBean.setCell("cmbcytmCellid");
			functLocFieldNameBean.setMachine("cmbcytmMachineid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
            functLocFieldNameBean.setMachMandatory(true);			
			FormModes formModes = (FormModes)httpSession.getAttribute("CellManningFormMode");
			
			if( formModes == FormModes.view)
				formModes = FormModes.view;
			else
				formModes = FormModes.create;
			
			CommonFunctions.debugMsg(" FORM MODES-----------DDDDD"+formModes);
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		
		/**    For Saving   **/
		else if(action.equals("CellMng_save.balcllmng"))
		{
			PcsCycletimemstBean pcsCycletimemstBean = new PcsCycletimemstBean();
			saveCellManning(request, response, pcsCycletimemstBean);
		}
		/**    For Deleting   **/
		else if(action.equals("CellMng_delete.balcllmng"))
		{
			String cytmKeyid = request.getParameter("cytmKeyid");
 	   		CommonFunctions.debugMsg("cytmKeyid pehle ="+cytmKeyid);
 	   		if(UIUtils.isValidKeyId(cytmKeyid))
 	   		  deleteCellManning(request, response);
		}
		System.out.println(" dispatchUrl " + dispatchUrl);
		 if (dispatchUrl != null)
		 {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			System.out.println(" response " + response); 
		 } 
	}
	
	private List<BAL_PcsTlCycletimemst> populatePcsList(List<String[]> pcsentryModeList) {
		// TODO Auto-generated method stub
		List<BAL_PcsTlCycletimemst> pcsTblList = new ArrayList<BAL_PcsTlCycletimemst>();
		 	for( String [] row : pcsentryModeList)
		 {
		 		BAL_PcsTlCycletimemst pcsTlCycletimemst = new BAL_PcsTlCycletimemst();
		 		pcsTlCycletimemst.setCytmCycletime(row[6]);
		 		pcsTlCycletimemst.setCytmKeyid(row[5]);
		 		pcsTlCycletimemst.setCytmCreatedon(row[2]);
		 		pcsTlCycletimemst.setCytmProductid(row[0]);//0
		 		pcsTblList.add(pcsTlCycletimemst);
		 }
		 		System.out.println(pcsTblList);
		 		return pcsTblList;
		}
		
	
	private void saveCellManning(HttpServletRequest request, HttpServletResponse response,PcsCycletimemstBean pcsCycletimemstBean  ) throws IOException
	{
		System.out.print("Inside Save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		UIUtils.displayRequestParamsValue(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		BAL_PcsTlCycletimemst newPcsTlCycletimemst= new BAL_PcsTlCycletimemst();
	    		
	    	//	newPcsTlCycletimemst =(PcsTlCycletimemst)UIUtils.setBeanProperties((Object)newPcsTlCycletimemst,request);
	    		pcsCycletimemstBean =(PcsCycletimemstBean) UIUtils.setBeanProperties((Object)pcsCycletimemstBean,request);
	    		List<BAL_PcsTlCycletimemst> pcsTlCycletimemstList =null;
	    		BAL_PcsTlCycletimemst pcsTlCycletimemst = new BAL_PcsTlCycletimemst();
	    		String pcsTlCycletimeStr = request.getParameter("pcsTlCycletime");
	            CommonFunctions.debugMsg("**PcsTlCycleTime**"+pcsTlCycletimeStr);
	    		JSONArray pcsTlCycletimeJSON =null;
	    		if( pcsTlCycletimeStr != null && ! pcsTlCycletimeStr.isEmpty())
	    		{	
	    			System.out.println("pcsTlCycletimeStr!=null....");
	    			pcsTlCycletimeJSON = JSONArray.fromString(pcsTlCycletimeStr);
	    			System.out.println("pcsTlCycletimeJSON="+pcsTlCycletimeJSON);
	    			pcsTlCycletimemstList=(List<BAL_PcsTlCycletimemst>)UIUtils.convertJSONArrToList(pcsTlCycletimemst, pcsTlCycletimeJSON);
	    			System.out.println("pcsTlCycletimemstList not null ="+pcsTlCycletimemstList.size());
	    		}

	    		List<BAL_PcsTlCycletimemst> existPcsTlCycletimemst  = (List<BAL_PcsTlCycletimemst>)httpSession.getAttribute("existPcsTlCycletimemst");
	    	 
	    		if( pcsTlCycletimemstList != null && pcsTlCycletimemstList.size() > 0)
				{		
					pcsCycletimemstBean.setCreatedBy(user.getUsrm_ccno());
					try
					{
						System.out.println("Before Save");
						existPcsTlCycletimemst = pcsTlCycletimemstService.save(pcsTlCycletimemstList,existPcsTlCycletimemst,pcsCycletimemstBean);
						httpSession.setAttribute("existPcsTlCycletimemst",existPcsTlCycletimemst);
						boolean clearVal=false;
						JSONObject successData = new JSONObject();
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
						JSONObject returnData = new JSONObject();
						returnData.put("formClear",clearVal);
						returnData.put("successData",successData);
						System.out.println("Mode :"+returnData.toString());
						out.print(returnData.toString());
						out.close();
		
					}
					
					catch(BusinessApplicationExceptions e)
					{
						String errMsg = e.getMessage();
						String msg =   UIUtils.getOracleConstraintMessages(errMsg);
						if( msg != null )
							msg = "Record " + " " + msg; 
						else
							msg = errMsg;
							
						CommonFunctions.debugMsg(" e " + e.getMessage());
						out.print(msg);
						out.close();
						
					}
					
					catch(Exception e)
					{
						System.out.println("Error Msg:" + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Data Not Saved");
						out.print(err.toString());
					}
				}
	    	
	       }
	  }
	
		private void deleteCellManning(HttpServletRequest request,HttpServletResponse response) throws Exception
		{
	
			 HttpSession httpSession = request.getSession(false);
		     ServletOutputStream out = response.getOutputStream();
		     AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		 	   	if( httpSession != null && user != null)
		 	   	{	
		 	   		BAL_PcsTlCycletimemst newPcsTlCycletimemst = new BAL_PcsTlCycletimemst();
		 	   		newPcsTlCycletimemst =(BAL_PcsTlCycletimemst)UIUtils.setBeanProperties((Object)newPcsTlCycletimemst,request);
		 	   	//	KaizenFormBean kaizenFormBean = (KaizenFormBean)httpSession.getAttribute("kaizenFormBean");
				
		 	   		BAL_PcsTlCycletimemst existPcsTlCycletimemst = (BAL_PcsTlCycletimemst)httpSession.getAttribute("kznTlMst"+newPcsTlCycletimemst.getCytmKeyid()); 
		 	   		
		 	   		String cytmKeyid = request.getParameter("cytmKeyid");
		 	        CommonFunctions.debugMsg("The CytmKeyId"+cytmKeyid);
		 	  	//	String frmMode=kaizenFormBean.getFormActionMode();
		 			try
		 			{		
		 				CommonFunctions.debugMsg("newPcsTlCycletimemst.getCytmKeyid() ="+newPcsTlCycletimemst.getCytmKeyid());
		 				if(UIUtils.isValidKeyId(cytmKeyid))//newPcsTlCycletimemst.getCytmKeyid()
		 				{
		 						newPcsTlCycletimemst.setCytmKeyid(cytmKeyid);
		 						newPcsTlCycletimemst =	pcsTlCycletimemstService.delete(newPcsTlCycletimemst);
		 					
		 					//httpSession.removeAttribute("kznTlMst"+newPcsTlCycletimemst.getCytmKeyid());
		 					Boolean clrVal=false;
		 					//String delMsg="success-delete";
		 					
		 					JSONObject successData = new JSONObject();
		 					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
		 					successData.put("keyid", newPcsTlCycletimemst.getCytmKeyid());
		 					JSONObject returnData = new JSONObject();
		 					returnData.put("formClear",clrVal);
		 					returnData.put("successData", successData);
		 					out.print(returnData.toString());
		 				}
		 			}
		 			catch(Exception e)
		 			{
		 				System.out.println("Error Msg:" + e.getMessage());
		 				JSONObject err = new JSONObject();
		 				err.put("tpmException", "Data Not Saved");
		 				out.print(err.toString());
		 			}
		 	   	}
		
		}
	
	}		