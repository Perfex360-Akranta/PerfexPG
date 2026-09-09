package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
//import com.akranta.tpm.bean.SheActionPlanBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.GenTlMachineskillmst;
import com.akranta.tpm.model.GenTlMchcirclelink;
import com.akranta.tpm.model.GenTlMchemplink;
import com.akranta.tpm.model.GenTlMchmaintteamlink;
import com.akranta.tpm.model.GenTlMchparameterlink;
import com.akranta.tpm.model.GenTlMchsubmchlink;
//import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.EquipmentFormService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.EquipmentFormServiceImpl;
//import com.akranta.tpm.service.impl.GenTlEqpgroupmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.controller.MasterTableConfigServlet;
import com.akranta.tpm.service.api.MachineMasterServiceApi;
public class EquipmentMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	EquipmentFormService equipmentService;
	CommonFilterService commonFilterService;
	CommonFilter  commonFilter;
	MachineMasterServiceApi machinemasterserviceapi;

	public EquipmentMasterServlet() {
		super();
	/*	
		try {
			equipmentService = new EquipmentFormServiceImpl();
			//commonFilterService = new CommonFilterServiceImpl();
		} catch (Exception e) {

		}
	*/	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initilizeInputMode(String mode, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dispatchUrl = null;
		
		CommonMessage.debugMsg("Mode:" + mode);
		//AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		String equipmenyKeyId = request.getParameter("EquipmentKeyId");
		EquipmentBean equipmentBean = new EquipmentBean(mode);
		if (equipmenyKeyId != null && equipmenyKeyId.trim().length() > 0) {
			GenTlMachinemst genTlMachinemst = equipmentService.select(equipmenyKeyId);
			List<String[]> mchHierachy = commonFilterService.getMachineHierarchy(genTlMachinemst.getMchmKeyid());

			equipmentBean.setFormActionMode(mode);
			equipmentBean.setMchmfact(mchHierachy.get(0)[0]);
			HttpSession httpSession = request.getSession(false);
			httpSession.setAttribute("genTlMachinemst" + equipmenyKeyId,genTlMachinemst);
			request.setAttribute("genTlMachinemst", genTlMachinemst);
			request.setAttribute("equipmentBean", equipmentBean);
			
			request.setAttribute("inactMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/EquipmentMasterForm.jsp"); 
			CommonMessage.debugMsg("dsd...."+UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
			UIUtils.forwardRequest(request, response, "/pages/EquipmentMasterForm.jsp");
			rd.forward(request, response);
		}
		dispatchUrl = "/pages/EquipmentMasterForm.jsp";

	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FormModes mode = null;
		HttpSession httpSession = request.getSession(false);
		response.setContentType("text/html");
		response.setContentType("text/json");
		
		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		
		try {
			equipmentService = (EquipmentFormServiceImpl)UIUtils.getServiceObject(request,"EquipmentFormServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			equipmentService.EquipmentFormServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		   
			CommonFilter  commonFilter = new CommonFilter();
		
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		String dispatchUrl = null;
		
		
		
		 if (action.equals("PMAdherenceGrid_input.eqp")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/PM AdherenceReportGrid.jsp"); 
				rd.forward(request, response); 
			}
		 /*commented for prototype*/
		/* if (action.equals("equipment_input.eqp")) {
				
				
				//request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.PMAdherenceReport","PMAdherenceGrid"));
				//RequestDispatcher rd = request.getRequestDispatcher("/pages/PMAdherence.jsp"); 
				RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/EquipmentCatgForm.jsp"); 
				rd.forward(request, response); 
			}*/
		
		 if (action.equals("PMAdherenceForm_input.eqp")) {
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/PMAdherence.jsp"); 
				rd.forward(request, response); 
			}
		 
		 
			else if (action.equals("PMAdherenceGrid_getCol.eqp")) {
						 
						 PrintWriter out = response.getWriter();
						// out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PMAdherenceReport","PMAdherenceGrid"));
						 CommonFilter commonFilter = populateCommonFilter(request,"AdherenceCommonFilter",true);
							List<String[]> PMAdherence  =equipmentService .getAllPMgrid(commonFilter);
							JSONObject colModel = getTableModel(PMAdherence ,commonFilter);
							httpSession.setAttribute("ColModel", colModel);
							colModel.set("tableHeight", "80%%");
							colModel.set("tableWidth", "110%%");
							out.println(colModel);
						 
						 
						 //out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PMAdherenceMainGrid","PMAdherenceMainGrid"));
					 }
					 
		 
		 else if(action.equals("PMAdherenceGrid_getData.eqp")){
			 CommonFilter commonFilter = populateCommonFilter(request,"EntTaskCommonFilter",false);
					 List<String[]> Student = equipmentService.getAllPMgrid(commonFilter);
						PrintWriter out = response.getWriter();
		
						JSONObject student = UIUtils.convertToJqGridTableObject(Student, request, 3, 0);
						CommonMessage.debugMsg("grid"+Student.size());
						out.println(student);
				 }
				
			
			else if (action.equals("PMAdherenceForm_getCol.eqp")) {
						 
						 PrintWriter out = response.getWriter();
						 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PMAdherenceReport","PMAdherenceGrid"));
					 }
		 
		 else if(action.equals("PMAdherenceForm_getData.eqp")){
			 
			 List<String[]> StudentGrid = equipmentService.getAllPM();
				PrintWriter out = response.getWriter();

				JSONObject studentgrid = UIUtils.convertToJqGridTableObject(StudentGrid, request, 0, 0);
				out.println(studentgrid);
		 }
		
			
		 else if (action.equals("equipment_view.eqp")) {
						
			request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));

			RequestDispatcher rd = request.getRequestDispatcher("/pages/EquipmentFormMainGrid.jsp"); 
			//RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/EquipmentCatgForm.jsp"); 
			rd.forward(request, response); 
		}
		else if( action.equals("getModeequipment_view.eqp")){
			response.setContentType("text/html");
			//response.setContentType("text/json");
			
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			
			result.put("mode","create");
			result.put("url","equipment_input.eqp");
			result.put("formHeader","Equipment Master");
			
			out.println(result);
		}
		else if (action.equals("equipment_input.eqp")) {

			
			String userEvent = request.getParameter("userEvent");

			String keyid = request.getParameter(ReqtParamNameConst.KEYID);			
			String lockFields  = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
			String section = request.getParameter(ReqtParamNameConst.SECTID);
			String factory = request.getParameter(ReqtParamNameConst.FACTID);
			String cell = request.getParameter(ReqtParamNameConst.CELLID);
			
			/*
			 * String sbu =request.getParameter(ReqtParamNameConst.SBUID); String pbu
			 * =request.getParameter(ReqtParamNameConst.PBUID); String location =
			 * request.getParameter(ReqtParamNameConst.LOCNID); String jh
			 * =request.getParameter(ReqtParamNameConst.JHID); String epq
			 * =request.getParameter(ReqtParamNameConst.EQUIPMENTID); String mach
			 * =request.getParameter(ReqtParamNameConst.MACHID); String cmy =
			 * request.getParameter(ReqtParamNameConst.COMPID);
			 */
			//  CommonMessage.debugMsg(sbu + " ----------" + pbu + "--------------"+location);
			 
			
			
			CommonMessage.debugMsg(factory + " ----------" + section + "--------------"+cell);
			response.setContentType("text/html");
			String eqpMode = request.getParameter("eqpMode");
			CommonMessage.debugMsg("KEYID................."+eqpMode);
			String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
			mode=FormModes.create;
			//mode=FormModes.view;
			
			if(formMode !=null && formMode.equals( FormModeConsts.modify) )
			{
				mode =  FormModes.modify;
			}else if(formMode != null  &&  formMode.equals( FormModeConsts.view)){
				mode=FormModes.view;
			}
			
			EquipmentBean equipmentBean =new EquipmentBean(mode,lockFields);
			
			CommonMessage.debugMsg("Equipment Key id...................................."+mode);
			GenTlMachinemst genTlMachinemst  = null;
			if( (UIUtils.isValidKeyId(keyid) && userEvent == null )|| (userEvent != null && ! userEvent.equals("new")))
			{
				CommonMessage.debugMsg("Equipment Key"+keyid);
				request.setAttribute("equipmentkey",keyid);
				genTlMachinemst = equipmentService.select(keyid);
				
				
				String date0 = genTlMachinemst.getMchmAmcrenewaldate();
				genTlMachinemst.setMchmAmcrenewaldate(CommonFunctions.pg_getDateFromPGTimeStamp(date0));
				
				
				  String date1 = genTlMachinemst.getMchmEffectivedate();
				  
				  genTlMachinemst.setMchmEffectivedate(CommonFunctions.
				  pg_getDateFromPGTimeStamp(date1));
				  
				  
				  String date2 = genTlMachinemst.getMchmInactivateddate();
				  
				  genTlMachinemst.setMchmInactivateddate(CommonFunctions.
				  pg_getDateFromPGTimeStamp(date2));
				  
				  
				  String date3 = genTlMachinemst.getMchmInstalleddate();
				  
				  genTlMachinemst.setMchmInstalleddate(CommonFunctions.
				  pg_getDateFromPGTimeStamp(date3));
				  
				  String date = genTlMachinemst.getMchmAmcdate();
				  genTlMachinemst.setMchmAmcdate(CommonFunctions.pg_getDateFromPGTimeStamp(date
				  ));
				  
				  
				  String date4 = genTlMachinemst.getMchmJhstepdate();
				  genTlMachinemst.setMchmJhstepdate(CommonFunctions.pg_getDateFromPGTimeStamp(
				  date4));
				  
				  
				  String date6 = genTlMachinemst.getMchmManufactureddate();
				  genTlMachinemst.setMchmManufactureddate(CommonFunctions.
				  pg_getDateFromPGTimeStamp(date6));
				  
				  
				  String date7 = genTlMachinemst.getMchmPodate();
				  genTlMachinemst.setMchmPodate(CommonFunctions.pg_getDateFromPGTimeStamp(date7
				  ));
				  
				  
				  String date8 = genTlMachinemst.getMchmPurchasedate();
				  genTlMachinemst.setMchmPurchasedate(CommonFunctions.pg_getDateFromPGTimeStamp
				  (date8));
				  
				  String date9 = genTlMachinemst.getMchmWarrantydate();
				  genTlMachinemst.setMchmWarrantydate(CommonFunctions.pg_getDateFromPGTimeStamp
				  (date9));
				 








		 		
	
		 		
				
				CommonMessage.debugMsg("Cavity..."+genTlMachinemst.getMchmIscavityormandrel());
			}		
			else{
				
				genTlMachinemst = new GenTlMachinemst();
				genTlMachinemst = (GenTlMachinemst)UIUtils.setBeanProperties(genTlMachinemst, request);
			}
			CommonMessage.debugMsg(factory + "2 ----------" + section + "--------------"+cell);
			if(UIUtils.isValidKeyId(section )&& UIUtils.isValidKeyId(factory )&& UIUtils.isValidKeyId(cell ))
			{
				CommonMessage.debugMsg("Inside Condn " +factory+ "----------" + section + "--------------"+cell);
				genTlMachinemst = new GenTlMachinemst();
				genTlMachinemst.setMchmCellid(cell);
				equipmentBean.setSection(section);
				equipmentBean.setFactory(factory);
				
				CommonMessage.debugMsg(genTlMachinemst.getMchmCellid());
				CommonMessage.debugMsg(equipmentBean.getSection()+"========="+equipmentBean.getFactory());
				//request.setAttribute("genTlMachinemst", genTlMachinemst);
				//request.setAttribute("EquipmentBean", equipmentBean);
			}
			
			setDateValues(genTlMachinemst);			
			
			request.setAttribute("genTlMachinemst", genTlMachinemst);
			request.setAttribute("EquipmentBean", equipmentBean);
			
			httpSession.removeAttribute("genTlMachinemstServlet");
			httpSession.removeAttribute("EquipmentFormBean");
			
			httpSession.setAttribute("genTlMachinemstServlet", genTlMachinemst);
			httpSession.setAttribute("EquipmentFormBean", equipmentBean);
			CommonMessage.debugMsg("Mandrel..."+genTlMachinemst.getMchmIscavityormandrel());
			request.setAttribute(ReqtParamNameConst.FORM_MODE,mode);
			
			httpSession.removeAttribute("equipmentFormMode");
			httpSession.setAttribute("equipmentFormMode",mode);
			request.setAttribute("eqpMode", eqpMode);
			request.setAttribute("inactMsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
			RequestDispatcher rd = request.getRequestDispatcher("/pages/EquipmentMasterForm.jsp"); 
			rd.forward(request, response); 		
		}
		
		else if (action.equals("equipment_getCol.eqp")){
			try {
				
				
				
				/*int rowCount = equipmentService.selectCount();
				CommonMessage.debugMsg("count...."+rowCount);
				Long totalCnt = (long)rowCount;*/

				 
				CommonFilter commonFilter =  populateCommonFilter(request,"EquipmentCommonFilter",true);
			//	commonFilter.setTotalRecordCnt(totalCnt);
				//CommonMessage.debugMsg("count1...."+totalCnt);
				
				//httpSession.removeAttribute("totalCnt");
				//httpSession.setAttribute("totalCnt" , totalCnt);
				
				PrintWriter out = response.getWriter();		
				String active = "Y"; 
				 active = request.getParameter("active");
				CommonMessage.debugMsg("active....."+active);
				
				if(active.equals("N")){
					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MainGridBtnClick"));					
				}				
				else{
					//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MainGrid"));
					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MainGridnew"));
					}
				
				httpSession.removeAttribute("EquipmentCommonFilter");
				httpSession.setAttribute("EquipmentCommonFilter" , commonFilter);
				//colModel.set("tableHeight", "85%%");
			} catch (Exception e) {
				CommonMessage.debugMsg("operator recall getCol Exception"+ e.getMessage());
			}
		}
		else if (action.equals("equipment_getData.eqp")){
			try {
				CommonMessage.debugMsg("Enter the getData");				
				
				CommonFilter commonFilter = populateCommonFilter(request,"EquipmentCommonFilter",false);
				
				String location = CommonFunctions.getLoginLocaton(request);
				if (commonFilter.getLocation() != null)
				{
					commonFilter.getLocation().setId(location);
				}else
				{  
					ComboFilter  locationCmb = new ComboFilter();
					locationCmb.setId(location);
					commonFilter.setLocation(locationCmb);
				}
				CommonMessage.debugMsg("Location" + location);
				
				//Long tot = (Long)httpSession.getAttribute("totalCnt");				
				PrintWriter out = response.getWriter();
				commonFilter.setActive('S');
				
				String active = request.getParameter("active");
				String search = request.getParameter("_search");
				//String flid = request.getParameter("flid");
				CommonMessage.debugMsg("Search......."+search);
				if(active.equals("N"))		
					commonFilter.setActive('N');
				else if(active.equals("Y"))
					commonFilter.setActive('Y');
				CommonMessage.debugMsg("flid automatic ="+commonFilter.getFlid());
								
				int rowCount = equipmentService.selectCount(commonFilter);
				CommonMessage.debugMsg("count...."+rowCount);
				Long totalCnt = (long)rowCount;
				commonFilter.setTotalRecordCnt(totalCnt);
				
				
				List<String[]> MasterGrid = equipmentService.getMasterGrid(commonFilter);
				CommonMessage.debugMsg("Size to be considered....."+MasterGrid.size());
				if(search.equals("true"))
				{
					rowCount = MasterGrid.size();
					totalCnt = (long)rowCount;
					commonFilter.setTotalRecordCnt(totalCnt);
				}
				JSONObject masterGrid = UIUtils.convertToJqGridTableObject(MasterGrid, request, 0, 0,totalCnt);
				CommonMessage.debugMsg("Length...."+masterGrid.toString());
				
				masterGrid.put("msg","Success");
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", masterGrid);
				returnData.put("rows", masterGrid);
				
				httpSession.removeAttribute("EquipmentCommonFilter");
				httpSession.setAttribute("EquipmentCommonFilter" , commonFilter);
				
				httpSession.removeAttribute("totalCnt");
				httpSession.setAttribute("totalCnt" , rowCount);
				
				out.println(masterGrid);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
				
			}
		}
		 
		else if( action.equals("equipment_getExcel.eqp")){
			
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"EquipmentCommonFilter",false);
			//CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("EquipmentCommonFilter");
			CommonMessage.debugMsg("check for excel......."+commonFilter.getActive());
			//CommonMessage.debugMsg("check for excel coun......."+commonFilter.getTotalRecordCnt());
			String tmpFromRow = commonFilter.getFromRow();
			
			int count = (Integer) httpSession.getAttribute("totalCnt");
			String cnt = Integer.toString(count);
			
			commonFilter.setFromRow(null);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MainGrid");
			JSONObject tblJSONObj = JSONObject.fromString(tableModel);
			
			tblJSONObj.put("title", "Equipment Form");
			String format = ExcelUtils.getFormat(request);
			commonFilter.setFromRow(tmpFromRow);
			commonFilter.setToRow(cnt);
			Workbook wb = equipmentService.EquipmentFormExportExcel(commonFilter,tblJSONObj,format);
			
			CommonMessage.debugMsg("check for excel1......."+commonFilter.getFromRow());
			ExcelUtils.writeToResponse(response, wb, "EquipmentReport", format);
			
		}
		
		else if( action.equals("equipment_getAll.eqp")){
				
			CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("EquipmentCommonFilter");
			PrintWriter out = response.getWriter();
			 List<String[]> getAllval = equipmentService.getAll(commonFilter);
			 JSONObject masterGrid = UIUtils.convertToJqGridTableObject(getAllval, request, 0, 0);
			 CommonMessage.debugMsg(masterGrid);
			out.println(masterGrid);
		}
		 
		else if( action.equals("equipment_makeActive.eqp")){
			
			try
			{
				PrintWriter out = response.getWriter();		
				String keyId = request.getParameter("keyIds");	
				if(UIUtils.isValidKeyId(keyId))
				{
					CommonMessage.debugMsg("keyId.."+keyId);	
					 equipmentService.getInactive(keyId);
					//JSONObject masterGrid = UIUtils.convertToJqGridTableObject(convertActive, request, 0, 0);
					 JSONObject successData = new JSONObject();
					 successData.put("msg","Record is Activated Successfully");								
					 JSONObject returnData = new JSONObject();						
					 returnData.put("successData", successData);				
					 out.print(returnData.toString());
				}
				else{
					CommonMessage.debugMsg("Key id is null......");
					JSONObject err = new JSONObject();
					JSONObject returnData = new JSONObject();
					err.put("Data", "No Data Selected");
					err.put("keyId", keyId);
					returnData.put("successData", err);
					out.print(returnData);
				}
			}
			catch(Exception e){
				JSONObject err = new JSONObject();
				err.put("EquioInact", "Null");
			}
				
		}
		else if(action.equals("equipmentform_getCol.eqp"))
		{
			
		}
		else if(action.equals("subEquipmentRecall_input.eqp")){
			
		}
		else if (action.equals("subEquipmentRecall_getCol.eqp")) {
			try {
				
				
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","SubEquipment"));

			} catch (Exception e) {
				CommonMessage.debugMsg("Sub Equipment  recall getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("subEquipmentRecall_getData.eqp")) {
			try {
				String sect = request.getParameter("sect");
				String keyId = request.getParameter("eqpId");

				PrintWriter out = response.getWriter();
				List<String[]> SubEquipmentRecallList = equipmentService.getSubEquipmentRecallData(sect);
				JSONObject subEquipmentRecalData = UIUtils.convertToJqGridTableObject(SubEquipmentRecallList, request, 0, 0);
				out.println(subEquipmentRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Sub Equipment  Form Exception" + e.getMessage());
			}
		}
		else if(action.equals("EquipmentParameterRecall_input.eqp")){
			
		}
		else if (action.equals("EquipmentParameterRecall_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.println( UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentParameter"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Parameter recall getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("EquipmentParameterRecall_getData.eqp")) {
			try {

				String recall = request.getParameter("recall");
				
						
				PrintWriter out = response.getWriter();
				List<String[]> EquipmentParameterRecallList = equipmentService.getEquipmentParameterRecallData(recall);			
				JSONObject equipmentParameterRecalData = UIUtils.convertToJqGridTableObject(EquipmentParameterRecallList, request, 0, 0);
				out.println(equipmentParameterRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Parameter Form Exception" + e.getMessage());
			}
		}
		
		else if(action.equals("maintainceSkillRecall_input.eqp")){
			
		}
		else if (action.equals("maintainceSkillRecall_getCol.eqp")) {
			try {

					PrintWriter out = response.getWriter();
					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentMaintainceSkill"));
					
				} catch (Exception e) {
					CommonMessage.debugMsg("operator recall getCol Exception"+ e.getMessage());
				}
		} else if (action.equals("maintainceSkillRecall_getData.eqp")) {
			try {
				String recall = request.getParameter("recall");
				PrintWriter out = response.getWriter();
				List<String[]> OperatorRecallList = equipmentService.getMaintainceSkillRecallData(recall);
				JSONObject operatorRecalData = UIUtils.convertToJqGridTableObject(OperatorRecallList, request, 0, 0);
				out.println(operatorRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}
		
		else if(action.equals("maintainceRecall_input.eqp")){
			
		}		
		else if (action.equals("maintainceRecall_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentMaintaince"));

			} catch (Exception e) {
				CommonMessage.debugMsg("operator recall getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("maintainceRecall_getData.eqp")) {
			try {
				
				String machineId = request.getParameter("recall");
				
				PrintWriter out = response.getWriter();
				List<String[]> OperatorRecallList = equipmentService.getMaintainceRecallData(machineId);
				JSONObject operatorRecalData = UIUtils.convertToJqGridTableObject(OperatorRecallList, request, 0, 0);
				out.println(operatorRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}
		
		
		else if(action.equals("operatorSkill_remove.eqp")){
			deleteOperatorSkill(request, response);
		}
		else if(action.equals("operator_remove.eqp")){
			deleteOperatorMachineLink(request, response);
		}
		else if(action.equals("maintaince_remove.eqp")){
			deleteMaintTeamMachineLink(request, response);
		}
		else if(action.equals("maintainceSkill_remove.eqp")){
			deleteMaintTeamSkill(request, response);
		}
		else if (action.equals("operatorSkillRecall_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentOperatorSkill"));
				CommonMessage.debugMsg("After the getcol");
			} catch (Exception e) {
				CommonMessage.debugMsg("operator recall getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("operatorSkillRecall_getData.eqp")) {
			try {
				String recall = request.getParameter("recall");				// String SectionId = request.getParameter("Section");

				PrintWriter out = response.getWriter();
				List<String[]> OperatorRecallList = equipmentService.getOperatorSkillRecallData(recall);
				JSONObject operatorRecalData = UIUtils.convertToJqGridTableObject(OperatorRecallList, request, 0, 0);
				out.println(operatorRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}
		
		
		else if(action.equals("operrecall_input.eqp")){}
	
		else if (action.equals("operrecall_getCol.eqp")) {
			try {
				
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentOperator"));

			} catch (Exception e) {
				CommonMessage.debugMsg("operator recall getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("operrecall_getData.eqp")) {
			try {

				String operatorRecall = request.getParameter("oprRecall");
				PrintWriter out = response.getWriter();
				List<String[]> OperatorRecallList = equipmentService.getOperatorRecallData(operatorRecall);
				JSONObject operatorRecalData = UIUtils.convertToJqGridTableObject(OperatorRecallList, request, 0, 0);
				out.println(operatorRecalData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}
		
		
		else if (action.equals("SubEquipment_input.eqp")) {

		}

		else if (action.equals("SubEquipment_getCol.eqp")) {
			try {
				httpSession.setAttribute("sectionId",request.getParameter("sect"));
				httpSession.setAttribute("EqpMstServletEqpId",request.getParameter("eqpId"));
				
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","SubEquipment"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Maintaince getCol Exception"+ e.getMessage());
			}
		} 
		else if (action.equals("SubEquipment_getData.eqp")) {
			try {
				
				String SectionId = (String) httpSession.getAttribute("sectionId");
				String eqpId = (String) httpSession.getAttribute("EqpMstServletEqpId");
				PrintWriter out = response.getWriter();
				
				List<String[]> equipmentOperatorList = equipmentService.getAllSubEquipDataForEqp(SectionId,eqpId);
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(oplStudentData);
			} catch (Exception e) {
				//CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("EquipmentParameter_input.eqp")) {

		}

		else if (action.equals("EquipmentParameter_getCol.eqp")) {
			
			try {
				PrintWriter out = response.getWriter();
				CommonMessage.debugMsg("EquipmentParameter_getCol : "+request.getParameter("eqpId"));
				httpSession.setAttribute("eqpIdEqpMasterServlet", request.getParameter("eqpId"));
				String eqpParamModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentParameter");
				CommonMessage.debugMsg(" mpdel " + eqpParamModel);
				out.print(eqpParamModel);
				out.close();

			} catch (Exception e) {
				CommonMessage.debugMsg("EquipmentParameter getCol Exception"+ e.getMessage());
			}
			
		} else if (action.equals("EquipmentParameter_getData.eqp")) {
			
			try {
				CommonMessage.debugMsg("test");
				PrintWriter out = response.getWriter();

				String machineId = (String) httpSession.getAttribute("eqpIdEqpMasterServlet");
				CommonMessage.debugMsg("EquipmentKeyId:" + machineId);
				List<String[]> equipmentOperatorList = equipmentService.getAllEquipmentParm(machineId);
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(oplStudentData);
			} catch (Exception e) {
				CommonMessage.debugMsg("EquipmentParameter Form Exception" + e.getMessage());
			}
		}
		else if(action.equals("functionalLocMaingrid.eqp")){
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbMchmFlitfact");
			functLocFieldNameBean.setSection("cmbFlitSection");
			functLocFieldNameBean.setCell("cmbMchmFlitCellid");
			functLocFieldNameBean.setMachine("cmbMchmKeyid");
			functLocFieldNameBean.setFunctionalLocId("cmbMchFiltFlid"); 
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			
			//functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("equipmentFormMode");
			
			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;			
			if( FormModes.modify.equals(formModes))
				formModes = FormModes.view;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
		}

		
		else if(action.equals("functionalLoc.eqp")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbMchmfact");
			functLocFieldNameBean.setSection("cmbSection");
			functLocFieldNameBean.setCell("cmbMchmCellid");
			functLocFieldNameBean.setMachine("cmbMchmKeyid");
			functLocFieldNameBean.setFunctionalLocId("cmbMchmFlid"); 
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(true);
			
			//functLocFieldNameBean.setMachMandatory(true);
			
			FormModes formModes = (FormModes)httpSession.getAttribute("equipmentFormMode");
			CommonMessage.debugMsg("formModes..........."+formModes);
			
			if( formModes == FormModes.completion)
				formModes = FormModes.view;			
			if( formModes.equals(FormModes.modify))
				formModes = FormModes.view;
	
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		
		
		
		else if (action.equals("maintainceSkill_input.eqp")) {

		}

		else if (action.equals("maintainceSkill_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentMaintainceSkill"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Maintaince getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("maintainceSkill_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				String eqpId = request.getParameter("eqpId");
				
				List<String[]> equipmentOperatorList = equipmentService.getAllOperator(eqpId);
				CommonMessage.debugMsg(" eqpId " + eqpId);
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(oplStudentData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("maintaince_input.eqp")) {

		}

		else if (action.equals("maintaince_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentMaintaince"));
			} catch (Exception e) {CommonMessage.debugMsg("Maintaince getCol Exception"+ e.getMessage());
			}
		} else if (action.equals("maintaince_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				String eqpId = request.getParameter("eqpId");
				List<String[]> equipmentOperatorList = equipmentService.getAllOperator(eqpId);
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(oplStudentData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

			

		else if (action.equals("MaintSkillData_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MaintSkillData"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Operator getCol Exception" + e.getMessage());
			}
		} else if (action.equals("MaintSkillData_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				List<String[]> equipmentOperatorList = equipmentService.getAllMaintSkillData();
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(oplStudentData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("MaintainceData_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","MaintainceDataPopUp"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Operator getCol Exception" + e.getMessage());
			}
		} else if (action.equals("MaintainceData_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				List<String[]> equipmentOperatorList = equipmentService.getAllMaintainceData();
				JSONObject maintainceData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(maintainceData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("SkillData_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","OperatorSkillDataPopup"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Operator getCol Exception" + e.getMessage());
			}
		} else if (action.equals("SkillData_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				List<String[]> equipmentOperatorList = equipmentService.getAllSkillData();
				JSONObject skillData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(skillData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("operatorData_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","operatorDataPopUp"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Operator getCol Exception" + e.getMessage());
			}
		} else if (action.equals("operatorData_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				String factId = request.getParameter(ReqtParamNameConst.FACTID);
				factId = UIUtils.isValidKeyId(factId) == true ? factId:null;
				
				List<String[]> equipmentOperatorList = equipmentService.getAllOperatorData(factId);
				JSONObject operatorData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(operatorData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}
		
		
		else if (action.equals("operator_input.eqp")) {

		}

		else if (action.equals("operator_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentOperator"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Student getCol Exception" + e.getMessage());
			}
		} else if (action.equals("operator_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				String eqpId = request.getParameter("eqpId");
				List<String[]> equipmentOperatorList = equipmentService.getAllOperator(eqpId);
				JSONObject operatorData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				out.println(operatorData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		} else if (action.equals("operatorSkill_input.eqp")) {

		}

		else if (action.equals("operatorSkill_getCol.eqp")) {
			try {
				PrintWriter out = response.getWriter();
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentOperatorSkill"));
			} catch (Exception e) {
				CommonMessage.debugMsg("Student getCol Exception" + e.getMessage());
			}
		} else if (action.equals("operatorSkill_getData.eqp")) {
			try {
				PrintWriter out = response.getWriter();

				String eqpId = request.getParameter("eqpId");
				List<String[]> equipmentOperatorList = equipmentService.getAllOperator(eqpId);
				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
				CommonMessage.debugMsg(oplStudentData);
				out.println(oplStudentData);
			} catch (Exception e) {
				CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
			}
		}

		else if (action.equals("equipment_save.eqp")) {
			CommonMessage.debugMsg("Inside the Save");
			EquipmentBean equipmentBean =  (EquipmentBean)httpSession.getAttribute("EquipmentFormBean");
			
			saveEquipment(request, response, equipmentBean);

		}

		else if (action.equals("equipment_delete.eqp")) {
			
			EquipmentBean equipmentBean =  (EquipmentBean)httpSession.getAttribute("EquipmentFormBean");
			DeleteEquipment(request, response, equipmentBean);

		}
		else if(action.equals("costCentre.eqp"))
		{
			try{
				PrintWriter out = response.getWriter();
				String costId = request.getParameter("costCenter");
				CommonMessage.debugMsg("Costcenter" + action);
				List<ComboBox> costCentre = equipmentService.getCostCentre(costId);
				httpSession.setAttribute("costCentre", costCentre);
				JSONObject costData = UIUtils.fromTpmModel(costCentre);
				UIUtils.writeComboBox(response, costCentre,comboFilter);
				JSONObject returndata = new JSONObject();
				returndata.put("equipmentData", costData);
				CommonMessage.debugMsg("Data:" + returndata);
				out.print(returndata);
			}
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		

		else if (action.equals("recall_select.eqp")) {

			PrintWriter out = response.getWriter();
			String keyId = request.getParameter("keyId");
			if( UIUtils.isValidKeyId(keyId) ){
				
				GenTlMachinemst genTlMachinemst = equipmentService.select(keyId);
	
				setDateValues(genTlMachinemst);
				
				EquipmentBean equipmentBean =new EquipmentBean(FormModes.modify);
				if(genTlMachinemst.getMchmPurchasedate() == Constants.passNullDate)
					genTlMachinemst.setMchmPurchasedate("");
				httpSession.removeAttribute("genTlMachinemstServlet");
				httpSession.removeAttribute("EquipmentFormBean");
				
				httpSession.setAttribute("EquipmentFormBean", equipmentBean);
				httpSession.setAttribute("genTlMachinemstServlet", genTlMachinemst);
				CommonMessage.debugMsg("Printing "+genTlMachinemst.getMchmPurchaseprice());
				CommonMessage.debugMsg("Printing Two"+genTlMachinemst.getMchmMachineorder());
				JSONObject equipmentData = UIUtils.fromTpmModel(genTlMachinemst);
				JSONObject returndata = new JSONObject();
				returndata.put("equipmentData", equipmentData);
				out.print(returndata);
			}	
		}

		else if (action.equals("equipment_view.eqp")) {

		}

		else if (action.equals("Combo_EqpGroup.eqp")) {
			try {

				
				List<ComboBox> designation = equipmentService.getEquipmentGroup("");
				UIUtils.writeComboBox(response, designation,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		else if (action.equals("Combo_SubSect.eqp")) {
			try {
				CommonMessage.debugMsg("SubSection" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				//eqpComboFilter.setEqpGroup(eqpComboFilter);
				List<ComboBox> SubSection = equipmentService.getSubSection(comboFilter);
				UIUtils.writeComboBox(response, SubSection,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		
		else if (action.equals("Combo_workCenter.eqp")) {
			try {
				CommonMessage.debugMsg("WorkCenter" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> WorkCenter = equipmentService.getWorkCenter(comboFilter);
				UIUtils.writeComboBox(response, WorkCenter,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_MachineRank.eqp")) {
			try {
				CommonMessage.debugMsg("MachineRank" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> MachineRank = equipmentService.getMachineRank(comboFilter);
				UIUtils.writeComboBox(response, MachineRank,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_machineKeyId.eqp")) {
			try {
				CommonMessage.debugMsg("machineKeyId" + action);
				String keyid = request.getParameter("keyid"); 
				List<ComboBox> machineKeyId = equipmentService
						.getMachineKeyId(keyid);
				UIUtils.writeComboBox(response, machineKeyId,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Circle.eqp")) {
			try {
				CommonMessage.debugMsg("Circle" + action);
				List<ComboBox> Circle = equipmentService.getCircle("");
				UIUtils.writeComboBox(response, Circle,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Purpose.eqp")) {
			try {
				CommonMessage.debugMsg("Purpose" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Purpose = equipmentService.getPurpose(comboFilter);
				UIUtils.writeComboBox(response, Purpose,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_JhStep.eqp")) {
			try {
				 CommonMessage.debugMsg("JHstep" + action);
				 comboFilter=UIUtils.fillComboFilter(request);
				 List<ComboBox> JHstep = equipmentService.getJHstep(comboFilter);
				 UIUtils.writeComboBox(response, JHstep,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_SubCategory.eqp")) {
			try {
				
				String category = request.getParameter("catagory");
				comboFilter=UIUtils.fillComboFilter(request);
				
                List<ComboBox> SubCategory = equipmentService.getSubCategory(category,comboFilter);
				UIUtils.writeComboBox(response, SubCategory,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_MachineName.eqp")) {
			try {
				CommonMessage.debugMsg("MachineName" + action);
				List<ComboBox> MachineName = equipmentService.getMachineName("");
				
				UIUtils.writeComboBox(response, MachineName,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_JhStep.eqp")) {
			try {
				CommonMessage.debugMsg("JHstep" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> JHstep = equipmentService.getJHstep(comboFilter);
				UIUtils.writeComboBox(response, JHstep,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_PowerSupply.eqp")) {
			try {
				CommonMessage.debugMsg("PowerSupply" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> PowerSupply = equipmentService.getPowerSupply(comboFilter);
				UIUtils.writeComboBox(response, PowerSupply,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_ConnectedLoad.eqp")) {
			try {
				CommonMessage.debugMsg("ConnectedLoad" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> ConnectedLoad = equipmentService.getConnectedLoad(comboFilter);
				UIUtils.writeComboBox(response, ConnectedLoad,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_DBNo.eqp")) {
			try {
				CommonMessage.debugMsg("DBNo" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> DBNo = equipmentService.getDBNo(comboFilter);
				UIUtils.writeComboBox(response, DBNo,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		else if (action.equals("Combo_Category.eqp")) {
			try {
				CommonMessage.debugMsg("Category" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				//commonFilter.setCategory(CatComboFilter);
				//UIUtils.writeComboBox(response, SubSection,SubSectComboFilter);
				List<ComboBox> Category = equipmentService.getCategory(comboFilter);
				UIUtils.writeComboBox(response, Category,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		else if (action.equals("Combo_SBNo.eqp")) {
			try {
				CommonMessage.debugMsg("SBNo" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> SBNo = equipmentService.getSBNo(comboFilter);
				UIUtils.writeComboBox(response, SBNo,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Manufact.eqp")) {
			try {
				CommonMessage.debugMsg("Manufacture" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Manufacture = equipmentService.getManufacture(comboFilter);
				UIUtils.writeComboBox(response, Manufacture,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Make.eqp")) {
			try {
				CommonMessage.debugMsg("Make" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Make = equipmentService.getMake(comboFilter);
				UIUtils.writeComboBox(response, Make,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Model.eqp")) {
			try {
				CommonMessage.debugMsg("Model" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Model = equipmentService.getModel(comboFilter);
				UIUtils.writeComboBox(response, Model,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Supplier.eqp")) {
			try {
				CommonMessage.debugMsg("Supplier" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Supplier = equipmentService.getSupplier(comboFilter);
				UIUtils.writeComboBox(response, Supplier,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Unit.eqp")) {
			try {
				CommonMessage.debugMsg("Unit" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Unit = equipmentService.getUnit(comboFilter);
				UIUtils.writeComboBox(response, Unit,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		else if (action.equals("Combo_Provider.eqp")) {
			try {
				CommonMessage.debugMsg("Provider" + action);
				comboFilter=UIUtils.fillComboFilter(request);
				List<ComboBox> Provider = equipmentService.getProvider(comboFilter);
				UIUtils.writeComboBox(response, Provider,comboFilter);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		else if(action.equals("circleGrid_input.eqp"))
		{
			String machineId = request.getParameter("keyid");
			CommonMessage.debugMsg("Key : "+machineId);
		    if(UIUtils.isValidKeyId(machineId))
		    	request.setAttribute("machineId", machineId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/EquipmentCircle.jsp"); 
			rd.forward(request, response); 
		}
		else if(action.equals("circleGrid_getCol.eqp")){
			PrintWriter out = response.getWriter();
			out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","CircleData"));
		}
		else if(action.equals("circleGrid_getData.eqp")){
			
			PrintWriter out = response.getWriter();
			String mchId = request.getParameter("keyid");
			List<String[]> equipmentCircleList = equipmentService.getAllCircle(mchId);
			JSONObject circleData = UIUtils.convertToJqGridTableObject(equipmentCircleList, request, 0, 0);
			CommonMessage.debugMsg(circleData);
			out.println(circleData);
		}

		else if(action.equals("circleFormGrid_input.eqp"))
		{
			
		}
		else if(action.equals("circleFormGrid_getCol.eqp")){
			PrintWriter out = response.getWriter();
			out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","CircleFormData"));
		}
		else if(action.equals("circleFormGrid_getData.eqp")){
			
			PrintWriter out = response.getWriter();	
			String mchId = request.getParameter("keyid");
			List<String[]> equipmentCatagoryList = equipmentService.getFormCircle(mchId);
			JSONObject catagoryData = UIUtils.convertToJqGridTableObject(equipmentCatagoryList, request, 0, 0);
			CommonMessage.debugMsg(catagoryData);
			out.println(catagoryData);
		}
		 
		  else if(action.equals("Circle_save.eqp")){
			  EquipmentBean equipmentBean =  (EquipmentBean)httpSession.getAttribute("EquipmentFormBean");
			  	   
			   saveCircle(request,response,equipmentBean);
		   }
		 
		  else if(action.equals("equipmentDetails_input.eqp")){
	    	   String eqpId = request.getParameter("eqpId");
	    	   request.setAttribute("eqpId", eqpId);
	    	   UIUtils.forwardRequest(request, response, "/pages/equipmentDetails.jsp");
	    	   CommonMessage.debugMsg(" eqpId " + eqpId);
	    	   
	       }
	       else if(action.equals("equipmentDetails_getCol.eqp")){
	    	
	    		try {
					PrintWriter out = response.getWriter();
					CommonMessage.debugMsg("equipmentDetails_getCol : "+request.getParameter("eqpId"));
					String eqpParamModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentParameter");
					CommonMessage.debugMsg(" mpdel " + eqpParamModel);
					out.print(eqpParamModel);
					out.close();

				} catch (Exception e) {
					CommonMessage.debugMsg("equipmentDetails_getCol Exception"+ e.getMessage());
				}
	    	}
	       else if(action.equals("equipmentDetails_getData.eqp")){
	    	   
	    	   try {
					PrintWriter out = response.getWriter();
					String machineId = request.getParameter("eqpId");
					CommonMessage.debugMsg("EquipmentKeyId:" + machineId);
					List<String[]> equipmentOperatorList = equipmentService.getAllEquipmentParm(machineId);
					JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
					out.println(oplStudentData);
				} catch (Exception e) {
					CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
				}
	    	
	       }

			  else if(action.equals("equipmentOtherDetails_input.eqp")){
		    	   String eqpId = request.getParameter("eqpId");
		    	   request.setAttribute("eqpId", eqpId);
		    	   UIUtils.forwardRequest(request, response, "/pages/equipmentDetails.jsp");
		    	   CommonMessage.debugMsg(" eqpId " + eqpId);
		    	   
		       }
		       else if(action.equals("equipmentOtherDetails_getCol.eqp")){
		    	
		    		try {
						PrintWriter out = response.getWriter();
						CommonMessage.debugMsg("equipmentDetails_getCol : "+request.getParameter("eqpId"));
						String eqpParamModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentForm","EquipmentOtherDetails");
						CommonMessage.debugMsg(" mpdel " + eqpParamModel);
						out.print(eqpParamModel);
						out.close();

					} catch (Exception e) {
						CommonMessage.debugMsg("equipmentDetails_getCol Exception"+ e.getMessage());
					}
		    	}
		       else if(action.equals("equipmentOtherDetails_getData.eqp")){
		    	   
		    	   try {
						PrintWriter out = response.getWriter();
						String machineId = request.getParameter("eqpId");
						CommonMessage.debugMsg("EquipmentKeyId:" + machineId);
						List<String[]> equipmentOperatorList = equipmentService.getEquipOtherDetails(machineId);
						JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(equipmentOperatorList, request, 0, 0);
						out.println(oplStudentData);
					} catch (Exception e) {
						CommonMessage.debugMsg("Equipment Form Exception" + e.getMessage());
					}
		    	
		       }
		 
	}

	
	private JSONObject getTableModel(List<String[]> pMAdherence,
			CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
		String [] colHeader = pMAdherence.get(0);	
		String [] colHeader1 = pMAdherence.get(1);	
		String [] colHeader2 = pMAdherence.get(2);	
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader1);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setTableButton(false);
		jqGridTableModel.setRowNumbers(true);	
		//jqGridTableModel.setEnableFilter(true);
		for(int i =0; i < colHeader.length; i++)
		{			
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
					
			jqGridColModel.setWidth( 100);				
			jqGridColModel.setAlign("right");
			jqGridColModel.setEditable(false);
			
			if(i==0)
			{
				jqGridColModel.setHidden(true);
							
			}
			else if(i==1)
			{
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("left");
			}
			else if(i==2)
			{
				jqGridColModel.setWidth(100);				
				jqGridColModel.setAlign("right");
			}	
			else if(i==3)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
			}	
			else if(i==4)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
			}	
			else if(i==5)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
			}	
			else if(i==6)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
			}	
			else if(i==7)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==8)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==9)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==10)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==11)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==12)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==13)
			{
				jqGridColModel.setWidth(70);				
				jqGridColModel.setAlign("right");
				
			}	
			else if(i==14)
			{
				jqGridColModel.setWidth(80);				
				jqGridColModel.setAlign("right");
				
			}	
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			
		}
		
	 JSONObject tableModel1 = UIUtils.getJqGridTableModel(jqGridTableModel);
	 return tableModel1;
	}

	private void saveCircle(HttpServletRequest request,HttpServletResponse response, EquipmentBean equipmentBean) throws IOException {

	 	HttpSession httpSession = request.getSession(false);
	 	ServletOutputStream out = response.getOutputStream();
	 	AdmTlUsermst user = UIUtils.getLoginUser(request);
	 	String mchId=request.getParameter("mchId");
	 	CommonMessage.debugMsg("mchId"+mchId);
	 	
	 	
	 	if( httpSession != null && user != null)
	 	{	
	 		GenTlMchcirclelink newGenTlMchcirclelink =new GenTlMchcirclelink();
	 		
	 		newGenTlMchcirclelink.setMclkCreatedby(user.getUsrm_ccno());
	 		if(UIUtils.isValidKeyId(mchId))
	 			newGenTlMchcirclelink.setMclkMachineid(mchId);
	 		newGenTlMchcirclelink =(GenTlMchcirclelink)UIUtils.setBeanProperties((Object)newGenTlMchcirclelink,request);
	 		equipmentBean =(EquipmentBean) UIUtils.setBeanProperties((Object)equipmentBean,request);
	 		GenTlMchcirclelink existGenTlMchcirclelink = (GenTlMchcirclelink)httpSession.getAttribute("equipmentCircle"); 
	 		equipmentBean.setMchId(mchId);
	 		equipmentBean.setCreatedby(user.getUsrm_ccno());
	 		String circle = request.getParameter("eqpCircle");		
	 		CommonMessage.debugMsg("circle............."+circle);
			if(UIUtils.isValidKeyId(circle))
			{
				JSONArray jsonCircle = JSONArray.fromString(circle);
				CommonMessage.debugMsg("jsonCircle............."+jsonCircle);
				GenTlMchcirclelink genTlMchcirclelink = new GenTlMchcirclelink();
				List<GenTlMchcirclelink> circlegrid =  (List<GenTlMchcirclelink>) UIUtils.convertJSONArrToList(genTlMchcirclelink, jsonCircle);
				CommonMessage.debugMsg("circlegrid.."+circlegrid.size());
				if (genTlMchcirclelink != null)
					newGenTlMchcirclelink.setCirclegrid(circlegrid);
				//CommonMessage.debugMsg("circle..."+newGenTlMchcirclelink.getCirclegrid());
			}
	 		
	 		try{
	 			String circledata = null;
	 				boolean insert = true;
	 				if(UIUtils.isValidKeyId(circle)){
	 				if( newGenTlMchcirclelink.getMclkKeyid() == null )
	 				{
	 					
	 					circledata = equipmentService.createCircle(newGenTlMchcirclelink,existGenTlMchcirclelink,equipmentBean);
	 					CommonMessage.debugMsg("circledata....."+circledata);
	 				}}
	 				else{
	 					circledata = equipmentService.deleteCircle(newGenTlMchcirclelink,existGenTlMchcirclelink,equipmentBean);
	 					
	 				}
	 				//new JSONObject();
	 			
	 				//JSONObject persistentData = new JSONObject(); 
	 				//persistentData.put("equipmentcircle",existGenTlMchcirclelink.getMclkKeyid());
	 				
	 				
	 			    String msgPropertyIdnt;				
	 			 
	 				 if( "Success".equals(circledata)){
	 					CommonMessage.debugMsg("circledata.....");
	 					msgPropertyIdnt = "success-save";
	 				 }
	 				 if("DeleteSuccess".equals(circledata))
	 					msgPropertyIdnt = "success-delete";
	 				 else
	 					 msgPropertyIdnt = "success-update";
	 			
	 				 JSONObject successData = new JSONObject(); 
	 			    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
	 				//successData.put("mode",equipmentBean.getFormActionMode() );
	 				//successData.put("keyId", existGenTlMchcirclelink.getMclkKeyid());
	 				JSONObject returnData = new JSONObject();	
	 				//returnData.put("formClear",false);
	 				returnData.put("successData", successData);
	 					
	 			
	 				//httpSession.setAttribute( "equipmentCircle", existGenTlMchcirclelink);
	 				
	 				CommonMessage.debugMsg(" after save  3");
	 				out.print(returnData.toString());
	 				out.close();
	 			
	 		}catch(Exception e)
	 		{
	 			//CommonMessage.debugMsg("gete. " + e.getMessage());
	 			JSONObject err = new JSONObject();
	 			
	 				err.put("tpmException", "Data Not Saved");
	 			
	 			out.print(err.toString());
	 		}
	 		
	     }	
		
	}

	private void saveEquipment(HttpServletRequest request,HttpServletResponse response, EquipmentBean equipmentBean)
																					throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream outt = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);


		if (httpSession != null && user != null) {
			GenTlMachinemst existGenTlMachinemst = (GenTlMachinemst) httpSession.getAttribute("genTlMachinemstServlet");
			//CommonMessage.debugMsg(existGenTlMachinemst.getGenTlFunctionallocn().getFnlnElementid()+"Element ID");
			if( equipmentBean == null )
				equipmentBean = new EquipmentBean(FormModes.create);
			
			GenTlMachinemst newgenTlMachinemst = new GenTlMachinemst();
			GenTlMchemplink newgenTlMchemplink = new GenTlMchemplink();
			GenTlMchmaintteamlink newgenTlMchmaintteamlink = new GenTlMchmaintteamlink();
			GenTlMachineskillmst newgenTlMachineskillmst = new GenTlMachineskillmst();
			GenTlMachineskillmst newmaintainceskillmst = new GenTlMachineskillmst();
			GenTlMchparameterlink newgenTlMchparameterlink = new GenTlMchparameterlink();
			GenTlFunctionallocn newgenTlFunctionallocn = new GenTlFunctionallocn();
			//GenTlMchsubmchlink newgenTlMchsubmchlink = new GenTlMchsubmchlink();

			
			newgenTlMachinemst.setMchmCreatedby(user.getUsrm_ccno());
			newgenTlMchmaintteamlink.setMcmtCreatedby(user.getUsrm_ccno());
			newgenTlMachineskillmst.setMskmCreatedby(user.getUsrm_ccno());
			newmaintainceskillmst.setMskmCreatedby(user.getUsrm_ccno());
			newgenTlMchparameterlink.setMplkCreatedby(user.getUsrm_ccno());
			newgenTlMchemplink.setMcemCreatedby(user.getUsrm_ccno());
			

			String operator = request.getParameter("mchOperators");
			String maintaince = request.getParameter("maintaince");
			String operatorSkillStr = request.getParameter("operatorSkill");
			String maintanceSkillStr = request.getParameter("maintainceSkill");
			String equipmentParameter = request.getParameter("equipmentParameter");
			String subEquipments = request.getParameter("subEquipment");
			
			
			String elementId = request.getParameter("hdnelementId");
			newgenTlFunctionallocn.setFnlnElementid(elementId);
			newgenTlMachinemst.setGenTlFunctionallocn(newgenTlFunctionallocn);
			
			
			if(UIUtils.isValidKeyId(operator))
			{
				JSONArray jsonOperator = JSONArray.fromString(operator);
				GenTlMchemplink genTlMchemplink = new GenTlMchemplink();
				List<GenTlMchemplink> operatorgrid = (List<GenTlMchemplink>) UIUtils.convertJSONArrToList(genTlMchemplink, jsonOperator);
				if (genTlMchemplink != null)
					newgenTlMachinemst.setOperatorgrid(operatorgrid);
				
				CommonMessage.debugMsg(newgenTlMachinemst.getOperatorgrid());
			}
			
			if(UIUtils.isValidKeyId(maintaince))
			{
				JSONArray jsonMaintaince = JSONArray.fromString(maintaince);

				GenTlMchmaintteamlink genTlMchmaintteamlink = new GenTlMchmaintteamlink();
				List<GenTlMchmaintteamlink> maintainceGrid = (List<GenTlMchmaintteamlink>) UIUtils.convertJSONArrToList(genTlMchmaintteamlink, jsonMaintaince);
				
				if (genTlMchmaintteamlink != null)
					newgenTlMachinemst.setmaintainceGrid(maintainceGrid);
			}
			
			if(UIUtils.isValidKeyId(operatorSkillStr))
			{
				JSONArray jsonOperatorSkill = JSONArray.fromString(operatorSkillStr);
				CommonMessage.debugMsg("jsonOperatorSkill:" + jsonOperatorSkill);
				GenTlMachineskillmst genTlMachineskillmst = new GenTlMachineskillmst();
				List<GenTlMachineskillmst> operatorSkillGrid = (List<GenTlMachineskillmst>) UIUtils.convertJSONArrToList(genTlMachineskillmst,jsonOperatorSkill);
				CommonMessage.debugMsg("Size: " + operatorSkillGrid.size());
				CommonMessage.debugMsg("operatorSkillGrid:" + operatorSkillGrid);
				if (genTlMachineskillmst != null)
					newgenTlMachinemst.setOperatorSkillGrid(operatorSkillGrid);
			}
			
			if(UIUtils.isValidKeyId(maintanceSkillStr))
			{
				JSONArray jsonMaintainceSkill = JSONArray.fromString(maintanceSkillStr);
				CommonMessage.debugMsg("jsonMaintainceSkill:" + jsonMaintainceSkill);
				GenTlMachineskillmst maintGenTlMachineskillmst = new GenTlMachineskillmst();
				List<GenTlMachineskillmst> maintainceSkillGrid = (List<GenTlMachineskillmst>) UIUtils.convertJSONArrToList(maintGenTlMachineskillmst,jsonMaintainceSkill);
				CommonMessage.debugMsg("maintainceSkillGrid:" + maintainceSkillGrid);
				CommonMessage.debugMsg("Size: " + maintainceSkillGrid.size());
				if (maintGenTlMachineskillmst != null)
					newgenTlMachinemst.setmaintainceSkillGrid(maintainceSkillGrid);
			
			
			}
			
			if(UIUtils.isValidKeyId(equipmentParameter))
			{
				 JSONArray jsonEquipmentParameter = JSONArray.fromString(equipmentParameter);
				 
				 GenTlMchparameterlink genTlMchparameterlink = new GenTlMchparameterlink();
				 List<GenTlMchparameterlink> equipmentParameterGrid = (List<GenTlMchparameterlink>)UIUtils.convertJSONArrToList(genTlMchparameterlink,jsonEquipmentParameter);
				 CommonMessage.debugMsg("jsonEquipmentParameter size:"+equipmentParameterGrid.size());
				 if(genTlMchparameterlink != null){
					 newgenTlMachinemst.setEquipmentParameterGrid(equipmentParameterGrid);
				 }	 
			}
			 
			if(UIUtils.isValidKeyId(subEquipments))
			{
				 JSONArray jsonSubEquipments = JSONArray.fromString(subEquipments);
				 CommonMessage.debugMsg("jsonSubEquipments:"+jsonSubEquipments);
				 GenTlMchsubmchlink genTlMchsubmchlink = new GenTlMchsubmchlink();
				 List<GenTlMchsubmchlink> subEquipmentGrid =(List<GenTlMchsubmchlink>)UIUtils.convertJSONArrToList(genTlMchsubmchlink,jsonSubEquipments);
				 
				 if(genTlMchsubmchlink != null)
					 newgenTlMachinemst.setSubEquipmentGrid(subEquipmentGrid);
			}
			
			newgenTlMachinemst = (GenTlMachinemst) UIUtils.setBeanProperties((Object) newgenTlMachinemst, request);
			
			equipmentBean = (EquipmentBean) UIUtils.setBeanProperties((Object) equipmentBean, request);
			

			try {
				boolean insert = true;
				if (newgenTlMachinemst.getMchmKeyid() == null) {

					existGenTlMachinemst = equipmentService.create(newgenTlMachinemst, existGenTlMachinemst,equipmentBean);
				} else {
					
					equipmentBean.setFormMode(FormModes.modify);
					CommonMessage.debugMsg("Equipemnt Mode "+equipmentBean.getFormMode());
					
					existGenTlMachinemst = equipmentService.update(newgenTlMachinemst, existGenTlMachinemst,equipmentBean);
					insert = false;
				}

				
				JSONObject successData = new JSONObject();				
				String msgPropertyIdnt;					 
				if( insert){
					msgPropertyIdnt = "success-save";
				}else
					msgPropertyIdnt = "success-update";
				
				
				httpSession.removeAttribute("genTlMachinemstServlet");
				httpSession.removeAttribute("EquipmentFormBean");
				
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				
			    successData.put("mode",equipmentBean.getFormActionMode() );
				successData.put("keyId", existGenTlMachinemst.getMchmKeyid());
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);	
				outt.print(returnData.toString());
				

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "EquipmentCreation");
				errMessage.put("fromMode", equipmentBean.getFormActionMode());
				outt.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("Error Servler e -" + e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "EquipmentCreation");
				outt.print(errMessage.toString());
				CommonMessage.debugMsg(" e " + errMessage);

			} catch (Exception e) {
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				outt.print(err.toString());
			}
		}
	}
	
	private void DeleteEquipment(HttpServletRequest request,HttpServletResponse response, EquipmentBean equipmentBean)
			throws IOException {

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			GenTlMachinemst existGenTlMachinemst = (GenTlMachinemst) httpSession.getAttribute("genTlMachinemstServlet");
			
			if( equipmentBean == null )
				equipmentBean = new EquipmentBean(FormModes.create);
			
			GenTlMachinemst newGenTlMachinemst = new GenTlMachinemst();
			newGenTlMachinemst.setMchmCreatedby(user.getUsrm_ccno());

			newGenTlMachinemst = (GenTlMachinemst) UIUtils.setBeanProperties((Object) newGenTlMachinemst, request);

			equipmentBean = (EquipmentBean) UIUtils.setBeanProperties((Object) equipmentBean, request);

			try {
				String inactMode =request.getParameter("hdnInactive");
				
				if ( inactMode.equals("Inactive") )
					existGenTlMachinemst = equipmentService.delete("I",newGenTlMachinemst);				
				else
					existGenTlMachinemst = equipmentService.delete("D",newGenTlMachinemst);
				
				
				//httpSession.setAttribute(existGenTlMachinemst.getMchmKeyid(),existGenTlMachinemst);
				//httpSession.setAttribute("GenTlMachinemst",existGenTlMachinemst);
				//String formBeanIdentifier = "equipmentBean"+ equipmentBean.getFormActionMode();
				//httpSession.setAttribute(formBeanIdentifier, equipmentBean);
				existGenTlMachinemst.setMchmActive("N");
				
				JSONObject mode = new JSONObject();
				mode.put("formMode", equipmentBean.getFormActionMode());
				JSONObject persistentData = new JSONObject();
				persistentData.put("MchmKeyid",	existGenTlMachinemst.getMchmKeyid());
				//persistentData.put("fromBean", formBeanIdentifier);
				JSONObject forwardData = new JSONObject();
				forwardData.put("MchmKeyid", existGenTlMachinemst.getMchmKeyid());
				
				mode.put("forwardData", forwardData);
				mode.put("persistentData", persistentData);
				JSONObject successData = new JSONObject();
				successData.put("msg",existGenTlMachinemst.getMchmMachineno()+"  "+UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-inactivate"));
				successData.put("mode",equipmentBean.getFormActionMode() );
				successData.put("keyId", existGenTlMachinemst.getMchmKeyid());
				
				
				JSONObject returnData = new JSONObject();
				
				
				returnData.put("successData", successData);				
				
				out.print(returnData.toString());
				httpSession.removeAttribute("genTlMachinemstServlet");
				httpSession.removeAttribute("EquipmentFormBean");

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(
						e.toString(), "EquipmentCreation");
				errMessage.put("fromMode", equipmentBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e){
				
				CommonMessage.debugMsg(" existGenTlMachinemst " +existGenTlMachinemst);
				if( existGenTlMachinemst != null)
					CommonMessage.debugMsg(" existGenTlMachinemst " + existGenTlMachinemst.getMchmKeyid()); 
				JSONObject successData = UIUtils.businessValidationExceptions(e.toString(), "EquipmentCreation");
				successData.put("msg", "OriginalIdExists");	
				successData.put("Errmsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
				JSONObject returnData = new JSONObject();	
				returnData.put("formClear",false);
				returnData.put("ErrData", successData);	
				returnData.put("successData", successData);	
				//out.print(successData.toString());
				returnData.put("mchno", existGenTlMachinemst.getMchmMachineno());
			
				
				out.print(returnData.toString());
				
				
				
			}catch (Exception e) {
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
			
		}

	}
	
	private void deleteOperatorSkill(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String machineId = request.getParameter(ReqtParamNameConst.MACHID);
		String skillName = request.getParameter("skillName");
		String rowId = request.getParameter("rowId");
		PrintWriter out = response.getWriter();
		try{	
			equipmentService.deleteOperatorSkill(machineId, skillName);
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg", skillName +" is deleted ");
			successData.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.println(returnData);
			
			out.close();
		}catch(Exception e){
		
			JSONObject exception = new JSONObject();
			
			exception.put("msg", skillName +" not is deleted ");
			exception.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("tpmException", exception);
			out.println(returnData);
			
			out.close();
		}

	}
	
	private void deleteOperatorMachineLink(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String machineId = request.getParameter(ReqtParamNameConst.MACHID);
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String rowId = request.getParameter("rowId");
		PrintWriter out = response.getWriter();
		try{	
			equipmentService.deleteOperatorMachineLink(machineId, empId) ;
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg", empName +" is deleted ");
			successData.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.println(returnData);
			
			out.close();
		}catch(Exception e){
		
			JSONObject exception = new JSONObject();
			
			exception.put("msg", empName +" not is deleted ");
			exception.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("tpmException", exception);
			out.println(returnData);
			
			out.close();
		}

	}
	
	private void deleteMaintTeamMachineLink(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String machineId = request.getParameter(ReqtParamNameConst.MACHID);
		String empId = request.getParameter("maintTeamId");
		String maintTeam = request.getParameter("maintTeam");
		String rowId = request.getParameter("rowId");
		PrintWriter out = response.getWriter();
		try{	
			equipmentService.deleteMaintTeamMachineLink(machineId, empId);
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg", maintTeam +" is deleted ");
			successData.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.println(returnData);
			
			out.close();
		}catch(Exception e){
		
			JSONObject exception = new JSONObject();
			
			exception.put("msg", maintTeam +" not is deleted ");
			exception.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("tpmException", exception);
			out.println(returnData);
			
			out.close();
		}
	}
	
	private void deleteMaintTeamSkill(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		String machineId = request.getParameter(ReqtParamNameConst.MACHID);
		String maintTeamSkill = request.getParameter("maintTeamSkill");
		String rowId = request.getParameter("rowId");
		PrintWriter out = response.getWriter();
		try{	
			equipmentService.deleteMainTeamSkill(machineId, maintTeamSkill);
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg", maintTeamSkill +" is deleted ");
			successData.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			out.println(returnData);
			
			out.close();
		}catch(Exception e){
		
			JSONObject exception = new JSONObject();
			
			exception.put("msg", maintTeamSkill +" not is deleted ");
			exception.put("rowId", rowId);
			JSONObject returnData = new JSONObject();
			returnData.put("tpmException", exception);
			out.println(returnData);
			
			out.close();
		}
	}

	private void setDateValues(GenTlMachinemst genTlMachinemst){
		
		genTlMachinemst.setMchmAmcdate(UIUtils.getActualDateForm(genTlMachinemst.getMchmAmcdate()));
		genTlMachinemst.setMchmAmcrenewaldate(UIUtils.getActualDateForm(genTlMachinemst.getMchmAmcrenewaldate()));
		genTlMachinemst.setMchmPurchasedate(UIUtils.getActualDateForm(genTlMachinemst.getMchmPurchasedate()));
		genTlMachinemst.setMchmEffectivedate(UIUtils.getActualDateForm(genTlMachinemst.getMchmEffectivedate()));
		genTlMachinemst.setMchmInactivateddate(UIUtils.getActualDateForm(genTlMachinemst.getMchmInactivateddate()));
		genTlMachinemst.setMchmInstalleddate(UIUtils.getActualDateForm(genTlMachinemst.getMchmInstalleddate()));
		genTlMachinemst.setMchmJhstepdate(UIUtils.getActualDateForm(genTlMachinemst.getMchmJhstepdate()));
		genTlMachinemst.setMchmManufactureddate(UIUtils.getActualDateForm(genTlMachinemst.getMchmManufactureddate()));
		genTlMachinemst.setMchmPodate(UIUtils.getActualDateForm(genTlMachinemst.getMchmPodate()));
		genTlMachinemst.setMchmWarrantydate(UIUtils.getActualDateForm(genTlMachinemst.getMchmWarrantydate()));

		genTlMachinemst.setMchmPodate(UIUtils.getActualDateForm(genTlMachinemst.getMchmPodate()));
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
			commonFilter = 	FilterValues.getEquipmentRelated(request, commonFilter);
		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		
		return commonFilter;
	}
	
	
}
