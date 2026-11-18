package com.akranta.tpm.controller;

/** Created By:Siddharth .A**/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
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
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.BAL_PcsTlLosscelllink;
import com.akranta.tpm.model.BAL_PcsTlLossphenfactorylink;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.service.BAL_PcsEnableDisableService;
import com.akranta.tpm.service.impl.BAL_PcsEnableDisableServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.BAL_PcsTlEnablelosscaptureServiceApi;

public class BAL_PCSEnableDisableServlet extends HttpServlet {
	BAL_PcsTlEnablelosscaptureServiceApi EnablelosscaptureServiceApi;
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private BAL_PcsEnableDisableService pcsEnableDisableService;
    public BAL_PCSEnableDisableServlet() {
    
        super();
    	/*try {
			pcsEnableDisableService =new PcsEnableDisableServiceImpl();
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
		CommonFunctions.debugMsg("action:"+action);
		
		ComboFilter comboFilter = new ComboFilter();
		
		try {
			pcsEnableDisableService =(BAL_PcsEnableDisableServiceImpl)UIUtils.getServiceObject(request,"BAL_PcsEnableDisableServiceImpl");
			
			pcsEnableDisableService.BAL_PcsEnableDisableServiceImplJwt(
					(String) (httpSession.getAttribute("tpmjwttoken") == null 
					? "" : httpSession.getAttribute("tpmjwttoken")) 
					);
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		if (action.equals("pcsEnableDisable_input.balpcsEnable")&& user != null) 
		{
			
			System.out.println("input the jsp");
			// there is nothing to be done
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			request.setAttribute("pcsEnableDisableFormBean", pcsEnableDisableFormBean);
		}
		else if(action.equals("pcsEnableDisableMCH_input.balpcsEnable"))
		{
		   
		}
		
		//elumalai
		
//		else if (action.equals("pcsEnableDisableMCH_getCol.balpcsEnable"))
//		{
//		    try
//		    {
//		        PrintWriter out = response.getWriter();
//		        String sectId = request.getParameter("flid");
//
//		       // List<String[]> list = pcsEnableDisableService.getPcsEnblDsblMCHWithHeaders(sectId);
//		        List<String[]> list = pcsEnableDisableService.getAllPcsEnblDsblMCH(null, sectId, null, null);
//
//		        String[] colHeader     = list.get(2);
//		        String[] colHeaderCond = list.get(1);
//
//		        JqGridTableModel jqGridTableModel = new JqGridTableModel();
//		        GridColModel gridColModel = new GridColModel();
//		        jqGridTableModel.setTableButton(false);
//		        gridColModel.setHeaderNum(1);
//
//		        List<String[]> headers = new ArrayList<String[]>();
//		        headers.add(colHeader);
//
//		        JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
//		        jsonObject.put("tableWidth", 1000);
//		        jsonObject.put("tableHeight", "62%%");
//		        jsonObject.put("loadOnce", true);
//		        out.println(jsonObject);
//		    }
//		    catch (Exception e)
//		    {
//		        System.out.println("Pcs Enable Disable MCH getCol Exception " + e.getMessage());
//		    }
//		}
		
		
//		else if (action.equals("pcsEnableDisableMCH_getCol.balpcsEnable"))
//		{
//		    try
//		    {
//		        PrintWriter out = response.getWriter();
//		        String sectId = request.getParameter("flid");
//
//		        List<String[]> list = pcsEnableDisableService.getAllPcsEnblDsblMCH(null, sectId, null, null);
//
//		        String[] colHeader     = list.get(2);   // titles from the function
//		        String[] colHeaderCond = list.get(1);   // H = hidden, N = visible, from the function
//
//		        JqGridTableModel jqGridTableModel = new JqGridTableModel();
//		        GridColModel gridColModel = new GridColModel();
//		        jqGridTableModel.setTableButton(false);
//		        gridColModel.setHeaderNum(1);
//
//		        List<String[]> headers = new ArrayList<String[]>();
//		        headers.add(colHeader);
//
//		        JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
//
//		        // rebuild colModel from the function's rows
//		        JSONArray cm = new JSONArray();
//		        for (int i = 0; i < colHeader.length; i++)
//		        {
//		            String name = "c" + i + "_" + colHeader[i].replaceAll("[^A-Za-z0-9]", "");
//		            JSONObject c = new JSONObject();
//		            c.put("name", name);
//		            c.put("index", name);
//		            c.put("hidden", "H".equalsIgnoreCase(colHeaderCond[i]));
//		            c.put("width", 120);
//		            c.put("editable", false);
//		            c.put("align", "left");
//		            cm.put(c);
//		        }
//		        jsonObject.put("colModel", cm);
//
//		        jsonObject.put("tableWidth", 1000);
//		        jsonObject.put("tableHeight", "62%%");
//		        jsonObject.put("loadOnce", true);
//		        out.println(jsonObject);
//		    }
//		    catch (Exception e)
//		    {
//		        System.out.println("Pcs Enable Disable MCH getCol Exception " + e.getMessage());
//		    }
//		}
		
		else if(action.equals("pcsEnableDisableMCH_getCol.balpcsEnable"))
	{
		try
		{	
			PrintWriter out = response.getWriter();
			String pcsEbDbMCHColModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.BAL_PcsEnableDisableColModel", "pcsEnbDsbMCHGrid");
	        System.out.println("pcsEbDbMCHColModel="+pcsEbDbMCHColModel);
			out.print(pcsEbDbMCHColModel);
	
		}
			catch(Exception e)
		{
			System.out.println("Pcs Enable Disable MCH getCol Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("pcsEnableDisableMCH_getData.balpcsEnable"))
		{
			try
			{	
				System.out.println("Inside Mch get Dtaa...........");
				PrintWriter out = response.getWriter();
				String factId= request.getParameter("factId");
				String sectId= request.getParameter("sectId");
				String cellId=request.getParameter("cellId");
				String flid = request.getParameter("flid");
				
				System.out.println("factId="+factId+"sectId="+sectId+"cellId="+cellId);
				//String peclKeyId = request.getParameter("peclKeyId");
			
				List<String[]> pcsEnableList= pcsEnableDisableService.getAllPcsEnblDsblMCH(factId,sectId,cellId, flid);
				System.out.println("pcsEnableList="+pcsEnableList);
				List<BAL_PcsTlEnablelosscapture> pcsTlEnablelosscaptureList = populatePcsEnbDsbList(pcsEnableList);
				System.out.println("pcsTlEnablelosscaptureList.size()"+pcsTlEnablelosscaptureList.size());	
				httpSession.setAttribute("pcsTlEnablelosscaptureList",pcsTlEnablelosscaptureList);	
				JSONObject pcsEnableData = UIUtils.convertToJqGridTableObject(pcsEnableList,request,1,1);
				System.out.println("pcsEnableData="+pcsEnableData);
				out.println(pcsEnableData);
			}
			catch(Exception e)
			{
				System.out.println("Pcs Enable Disable getData Exception"+e.getMessage());
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
				System.out.println((UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbCELLGrid")));
			}
			catch(Exception e)
			{
				System.out.println("Pcs Enable Disable CELL getCol Exception"+e.getMessage());
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
				System.out.println("Inside Cell Servlete"+factId+"sect="+sectId+"cellId="+cellId);
				
				List<String []> pcsEnableList  = pcsEnableDisableService.getAllPcsEnblDsblCELL(factId,sectId,cellId);
				JSONObject pcsEnableCELLData = UIUtils.convertToJqGridTableObject(pcsEnableList,request,0,0);
				System.out.println("pcsEnableCELLData"+pcsEnableCELLData);
				out.println(pcsEnableCELLData);
			}
			catch(Exception e)
			{
				System.out.println("Pcs Enable Disable getData Exception"+e.getMessage());
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
				System.out.println("SECTION"+UIUtils.getPropertyValue("com.akranta.tpm.resources.PcsEnableDisableColModel", "pcsEnbDsbSECTGrid"));
			}
			catch(Exception e)
			{
				System.out.println("Pcs Enable Disable MCH getCol Exception"+e.getMessage());
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
				System.out.println("Pcs Enable Disable getData Exception"+e.getMessage());
			}
			
		}
		else if(action.equals("phenomena_combo.balpcsEnable"))
		{
			try 
			{
				comboFilter=UIUtils.fillComboFilter(request);
				ComboFilter commonFilter = new ComboFilter();
				List<ComboBox> DetectedBy = pcsEnableDisableService.getPhenomenaComboList(commonFilter);
				UIUtils.writeComboBox(response, DetectedBy, comboFilter);
			} 
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		else if(action.equals("mainLoss_combo.balpcsEnable"))
		{
			try 
			{
				comboFilter=UIUtils.fillComboFilter(request);
				ComboFilter commonFilter = new ComboFilter();
				List<ComboBox> DetectedBy = pcsEnableDisableService.getMainLossComboList(commonFilter);
				UIUtils.writeComboBox(response, DetectedBy, comboFilter);
			}
			catch (Exception e)
			{

				e.printStackTrace();
			}
		}
		
		else if(action.equals("phenomenaFactory_getCol.balpcsEnable"))
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
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if(action.equals("phenomenaLoss_input.balpcsEnable"))
		{
			UIUtils.forwardRequest(request, response, "/pages/BAL_PhenomenaLossLink.jsp");
			request.setAttribute("from", "LossPhen");
			
		}
		
		else if(action.equals("lossJhLink_input.balpcsEnable"))
		{
			UIUtils.forwardRequest(request, response, "/pages/pcs/LossJHLink.jsp");
			request.setAttribute("from", "LossJH");
		}
		
		else if(action.equals("phenomenaLoss_getCol.balpcsEnable"))
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
			CommonFunctions.debugMsg("lossId:"+lossId);
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
			CommonFunctions.debugMsg("jhId:"+jhId);
			GridParams gridParams = (GridParams) httpSession.getAttribute("lossParametersgridParams");
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

		
		else if(action.equals("PhenomenaFactoryLossLink_save.balpcsEnable"))
		{
			savePhenomenaFactLoss(request,response);
		}
		else if(action.equals("phenomenaLoss_save.pcsEnable"))
		{
			savePhenomenaFactLoss(request,response);
		}
		
		
		else if(action.equals("lossJhLink_save.pcsEnable"))
		{
			saveLossJH(request,response);
		}
		
		else if(action.equals("getComboFillContent.balpcsEnable"))
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
			CommonFunctions.debugMsg("Size...."+comboText.size());
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
				System.out.println("getSectCountList="+getSectCountList.size());
				System.out.println("str"+getSectCountList.toString());
				//String[] cnt=getSectCountList.get(0);
				String sectCnt= getSectCountList.toString();
				if(getSectCountList.size()>0)
				{ 
						System.out.println("Exists");
						msg="exists";
				}
				else 
				{
					System.out.println("NEWWW"); 
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
		
		else if(action.equals("functionalLoc.balpcsEnable"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbpelcFactoryid");
			functLocFieldNameBean.setSection("cmbpelcLineid");
			functLocFieldNameBean.setCell("cmbpelcCellid");
			functLocFieldNameBean.setMachine("cmbpelcMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmbpelcFlid");
			functLocFieldNameBean.setFactMandatory(true);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setCellDisable(true);
			FormModes formModes = FormModes.create;//
			if( formModes == FormModes.completion || formModes == FormModes.removal)
				formModes = FormModes.view;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );			
		}
		
		else if (action.equals("pcsEnableDisable_save.balpcsEnable")) 
		{
			PcsEnableDisableFormBean pcsEnableDisableFormBean = new PcsEnableDisableFormBean();
			pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
			savePcsEnble(request,response, pcsEnableDisableFormBean);
		}
		else if (action.equals("pcsEnableDisable_delete.balpcsEnable")) 
		{
			System.out.println("delete Called");
			//deletepcsEnble(request,response, null);
		}
		
		
		if (action.equals("pcsEnableDisable_input.balpcsEnable")) 
		{
			System.out.println("Out the jsp");
			UIUtils.forwardRequest(request, response, "/pages/BAL_PCSEnableDisable.jsp") ;
		}
		
		
	}	
    private List<BAL_PcsTlEnablelosscapture> populatePcsEnbDsbList(List<String[]> pcsEnableList)
	{
		List<BAL_PcsTlEnablelosscapture> pelcTblList = new ArrayList<BAL_PcsTlEnablelosscapture>();
	 	System.out.println("Inside populatePillarLinkList"+pcsEnableList.size());
	 	for( String [] row : pcsEnableList)
	 {
	 		BAL_PcsTlEnablelosscapture pcsTlEnablelosscapture = new BAL_PcsTlEnablelosscapture();
	 		pcsTlEnablelosscapture.setPelcIspcsenabled(row[6]);//1
	 		pcsTlEnablelosscapture.setPelcIsgroupbased(row[5]);
	 		pcsTlEnablelosscapture.setPelcIshtlog(row[2]);
	 		pcsTlEnablelosscapture.setPelcQtyortimebased(row[0]);//0
	 		pcsTlEnablelosscapture.setPelcKeyid(row[1]);//2
	 		pelcTblList.add(pcsTlEnablelosscapture);
	 }
	 		System.out.println(pelcTblList);
	 		return pelcTblList;
	}
  
	private void savePcsEnble(HttpServletRequest request, HttpServletResponse response,PcsEnableDisableFormBean pcsEnableDisableFormBean  ) throws IOException
	{
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	UIUtils.displayRequestParamsValue(request);
	    	if( httpSession != null && user != null)
	    	{	
	    		BAL_PcsTlEnablelosscapture newPcsTlEnablelosscapture= new BAL_PcsTlEnablelosscapture();
	    	    pcsEnableDisableFormBean =(PcsEnableDisableFormBean) UIUtils.setBeanProperties((Object)pcsEnableDisableFormBean,request);
	    		List<BAL_PcsTlEnablelosscapture> pcsEblDsblList =null;
	    		BAL_PcsTlEnablelosscapture pcsTlEnablelosscapture = new BAL_PcsTlEnablelosscapture();
	    		String pcsEnableDisableStr =request.getParameter("PcsEnableDisable");
	    	//	String forCell="Y";
	    		CommonFunctions.debugMsg("pcsEnableDisableStr= "+pcsEnableDisableStr);
	    		JSONArray pcsEnableDisableJSON =null;
	    		if( pcsEnableDisableStr != null && ! pcsEnableDisableStr.isEmpty())
	    		{	
	    			pcsEnableDisableJSON = JSONArray.fromString(pcsEnableDisableStr);
                    CommonFunctions.debugMsg("Json"+pcsEnableDisableJSON);
	    			pcsEblDsblList=(List<BAL_PcsTlEnablelosscapture>)UIUtils.convertJSONArrToList(pcsTlEnablelosscapture,pcsEnableDisableJSON);
	    		//	CommonFunctions.debugMsg("PcsEnableDisableList"+pcsEblDsblList.size());
	    			CommonFunctions.debugMsg("PcsEDList"+pcsEblDsblList);
	    		}
	    		List<BAL_PcsTlEnablelosscapture> existPcsTlEnablelosscapture  =(List<BAL_PcsTlEnablelosscapture>)httpSession.getAttribute("pcsTlEnablelosscaptureList");
		CommonFunctions.debugMsg("ExistPcstlosscaptures"+existPcsTlEnablelosscapture);
				
	    		if( pcsEblDsblList != null && pcsEblDsblList.size() > 0)
				{		
					try
					{	
						pcsEnableDisableFormBean.setPelcCreatedBy(user.getUsrm_ccno());
						//System.out.println("Before Save");
						//existPcsTlEnablelosscapture = pcsEnableDisableService.save(pcsEblDsblList,existPcsTlEnablelosscapture,pcsEnableDisableFormBean);
						existPcsTlEnablelosscapture = pcsEnableDisableService.save(pcsEblDsblList, user.getUsrm_ccno());

						System.out.println("The Save existpcs"+existPcsTlEnablelosscapture);		
						httpSession.setAttribute("existPcsTlEnablelosscaptureServlet", existPcsTlEnablelosscapture);
						JSONObject successData = new JSONObject();
						boolean clearVal=false;
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
						JSONObject returnData = new JSONObject();
						returnData.put("formClear",clearVal);
						returnData.put("successData",successData);
						System.out.println("Mode :"+returnData.toString());
						out.print(returnData.toString());
						out.close();
					}
					catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "SprPickupCreation");
						System.out.println("---------errMessage--------------------"+e.toString());
						out.print(errMessage.toString());
					}
					catch(Exception e)
					{
						System.out.println("Error Msg:" + e.getMessage());
						JSONObject tpmException = new JSONObject();
						JSONObject err = new JSONObject();
						tpmException.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
						//tpmException.put("msg", "Data Not Saved");
						tpmException.put("errMsg" , e.getMessage());
						err.put("tpmException",tpmException);
						out.print(err.toString());
                        e.printStackTrace();
					}
				}
	    		
	       }
	  }
		
		@SuppressWarnings("unchecked")
		private void savePhenomenaFactLoss(HttpServletRequest request,HttpServletResponse response) throws Exception 
		{
			
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	if( httpSession != null && user != null)
	    	{	
	    		try 
				{
		    		 BAL_PcsTlLossphenomenamst  pcsTlLossphenomenamst = new  BAL_PcsTlLossphenomenamst(); 
		    		 String msgPropertyIdnt;			
		    		 pcsTlLossphenomenamst =(BAL_PcsTlLossphenomenamst)UIUtils.setBeanProperties((Object)pcsTlLossphenomenamst,request);
		    		 String mstKeyid=pcsTlLossphenomenamst.getPlpmKeyid();
		    		 BAL_PcsTlLossphenfactorylink  pcsTlLossphenfactorylink =new BAL_PcsTlLossphenfactorylink();
		    		 String factorylist = request.getParameter("factoryList");
					 List<BAL_PcsTlLossphenfactorylink> pcsTlLossphenomenamstList = null;
					 JSONArray phenomenalinkJson = null;
					 if (UIUtils.isValidKeyId(factorylist))
					 {
						phenomenalinkJson = JSONArray.fromString(factorylist);
						pcsTlLossphenomenamstList = (List<BAL_PcsTlLossphenfactorylink>) UIUtils.convertJSONArrToList(pcsTlLossphenfactorylink,phenomenalinkJson);
						if (pcsTlLossphenomenamstList != null)
						{
							pcsTlLossphenomenamst.setPcsTlLossphenfactorylink(pcsTlLossphenomenamstList);
						}
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
						CommonFunctions.debugMsg("pcsTlLossphenomenamst.getPlpmKeyid():"+pcsTlLossphenomenamst.getPlpmKeyid());
						JSONObject successData = new JSONObject(); 
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mstKeyid", pcsTlLossphenomenamst.getPlpmKeyid());
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
					System.out.println("e.yguiyg " + e.getMessage());
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
	    			BAL_PcsTlLosscelllink  pcsTlLosscelllink = new BAL_PcsTlLosscelllink();
	    			 String msgPropertyIdnt;			
	    			 pcsTlLosscelllink =(BAL_PcsTlLosscelllink)UIUtils.setBeanProperties((Object)pcsTlLosscelllink,request);
		    		 
		    		 String jhLossList = request.getParameter("jhLossList");
					 List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList = null;
					 JSONArray jhLossJson = null;
					 
					 if (UIUtils.isValidKeyId(jhLossList))
					 {
						CommonFunctions.debugMsg("jhLossList"+jhLossList);
						 
						jhLossJson = JSONArray.fromString(jhLossList);
						CommonFunctions.debugMsg("jhLossList"+jhLossJson.length());
						pcsTlLosscelllinkList = (List<BAL_PcsTlLosscelllink>) UIUtils.convertJSONArrToList(pcsTlLosscelllink,jhLossJson);	
						
						CommonFunctions.debugMsg("pcsTlLosscelllinkList-Servlet.size"+pcsTlLosscelllinkList.size());
						
						pcsTlLosscelllink=pcsEnableDisableService.createLossJHLink(user.getUsrm_ccno(),pcsTlLosscelllinkList);
						msgPropertyIdnt = "success-save";
					
						CommonFunctions.debugMsg("pcsTlLossphenomenamst.getPlflkeyid():"+pcsTlLosscelllink.getPlflKeyid());
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
					System.out.println("e.yguiyg " + e.getMessage());
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
}
