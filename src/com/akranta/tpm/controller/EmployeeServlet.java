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
import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmpfunclocnlink;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlMomGroupdtl;
import com.akranta.tpm.model.GenTlMomGroupmst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.EmpgroupCreationService;
import com.akranta.tpm.service.EmployeeService;
import com.akranta.tpm.service.GenTlEmpfunclocnlinkService;
import com.akranta.tpm.service.impl.EmpgroupCreationServiceImpl;
import com.akranta.tpm.service.impl.EmployeeServiceImpl;
import com.akranta.tpm.service.impl.GenTlEmpfunclocnlinkServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;

	public class EmployeeServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private static int count;
		PrintWriter out;  
	    GenTlEmpfunclocnlinkService gentlempfunctionlinkservice;
	    EmployeeService employeeService;
	    CommonFilterService commonFilterService;
	    EmployeeGroupServiceApi employeegroupserviceapi;
	    private EmpgroupCreationService  empgroupCreationService= null;
	    public EmployeeServlet() {
        super();
	      
	        /*try {
				employeeService = new EmployeeServiceImpl();
			} catch (Exception e) {
				
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
			
			String action = UIUtils.getActionPart(request);
			HttpSession httpSession = request.getSession(false);
			
			
			ComboFilter currentFilter = new ComboFilter();
			
			response.setContentType("text/html");
			response.setContentType("text/json");
			String dispatchUrl = null;
			
			try {
				employeeService =  (EmployeeServiceImpl)UIUtils.getServiceObject(request,"EmployeeServiceImpl");
				gentlempfunctionlinkservice=(GenTlEmpfunclocnlinkServiceImpl)UIUtils.getServiceObject(request,"GenTlEmpfunclocnlinkServiceImpl");
				empgroupCreationService = (EmpgroupCreationServiceImpl)UIUtils.getServiceObject(request, "EmpgroupCreationServiceImpl");
				empgroupCreationService.EmpgroupCreationServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
				employeeService.EmployeeServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			
			if(action.startsWith("Empgroup"))
			{
				empgroupCreation(request,response);
			}
			if(action.equals("emp_input.emp")) 
			{
				CommonMessage.debugMsg("input the jsp");
				String keyid = request.getParameter("keyId");
				String userEvent = request.getParameter("userEvent");
				CommonMessage.debugMsg(" userEvent  " + userEvent);
				response.setContentType("text/html");
				
				CommonMessage.debugMsg("--------------------------------------------------");
			    CommonMessage.debugMsg(">>> [EmployeeServlet] Action = " + action);
			    CommonMessage.debugMsg(">>> [EmployeeServlet] keyId parameter = " + keyid);
			    CommonMessage.debugMsg(">>> [EmployeeServlet] userEvent parameter = " + userEvent);
			    CommonMessage.debugMsg("--------------------------------------------------");

				
				//if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
			    //sriram 22-oct-2025
			    if( (UIUtils.isValidKeyId(keyid) && userEvent == null) ||
			            (userEvent != null && !userEvent.equals("new"))){
					 
					GenTlEmployeemst genTlEmployeemst = employeeService.select(keyid);
					CommonMessage.debugMsg(genTlEmployeemst.getEmpmEmployeetype() +"testing");
					//GenTlEmployeedtl genTlEmployeeDtl= employeeService.getselect(keyid);
					//GenTlEmployeedtl genTlEmployeeDtl = genTlEmployeemst.getEmployeeDetail().get(0);
					GenTlEmployeedtl genTlEmployeeDtl = null;

					
String joinDate = genTlEmployeemst.getEmpmJoineddate();
					
					genTlEmployeemst.setEmpmJoineddate(CommonFunctions.pg_getDateFromPGTimeStamp(joinDate));						
					
					if (genTlEmployeemst != null
					        && genTlEmployeemst.getEmployeeDetail() != null
					        && !genTlEmployeemst.getEmployeeDetail().isEmpty()) {

					    genTlEmployeeDtl = genTlEmployeemst.getEmployeeDetail().get(0);
					}
					
					
					if(genTlEmployeeDtl != null)
					{						
				/*
				 * String joinDate =
				 * UIUtils.removeDefaultDate(genTlEmployeemst.getEmpmJoineddate()," ");
				 * genTlEmployeemst.setEmpmJoineddate(joinDate);
				 * 
				 * String birthDate =
				 * UIUtils.removeDefaultDate(genTlEmployeeDtl.getEmpdBirthdate()," ");
				 * genTlEmployeeDtl.setEmpdBirthdate(birthDate);
				 */
					
					//String birthDate = UIUtils.removeDefaultDate(genTlEmployeeDtl.getEmpdBirthdate()," ");
					String birthDate = genTlEmployeeDtl.getEmpdBirthdate();
					genTlEmployeeDtl.setEmpdBirthdate(CommonFunctions.pg_getDateFromPGTimeStamp(birthDate));
					}
					httpSession.setAttribute("genTlEmployeemst" + keyid, genTlEmployeemst);
					//sriram 22-oct-2025
					httpSession.setAttribute("genTlEmployeemstNew" , genTlEmployeemst);
					//end
					httpSession.setAttribute("genTlEmployeeDtl" + keyid, genTlEmployeeDtl);
					request.setAttribute("genTlEmployeemst", genTlEmployeemst);
					request.setAttribute("genTlEmployeeDtl", genTlEmployeeDtl);
				}
			
				
				dispatchUrl = "/pages/Employee.jsp";
								
			}
			
			else if(action.equals("combo_EmployeeCategory.emp")){
				try{
					 response.setContentType("text/html;charset=UTF-8");
					 response.setContentType("json");
				 	 PrintWriter out = response.getWriter();
				     out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.employeeCreation","EmployeeCategory"));
					 out.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
		
			else if( action.equals("functionalLoc.emp"))
			{
					CommonMessage.debugMsg("function location ");
					FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
					functLocFieldNameBean.setSection("cmbMgrmSectionid");
					functLocFieldNameBean.setCell("cmbMgrmCellid");
					functLocFieldNameBean.setMachine("cmbMgrmMachineid");
					functLocFieldNameBean.setFunctionalLocId("cmbMgrmFlid");
					functLocFieldNameBean.setSbu("cmbMgrmSbu");
					functLocFieldNameBean.setPbu("cmbMgrmPbu");
					functLocFieldNameBean.setLocnMandatory(true);
					functLocFieldNameBean.setFactMandatory(false);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
					CommonMessage.debugMsg("functionlocation null ");
	                FormModes formModes = FormModes.create;
	                UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
			 }
		
			else if(action.equals("fac_input.emp"))
			{   
				CommonMessage.debugMsg("fac_input.emp");
				String employid = request.getParameter("filterString");
				CommonMessage.debugMsg("fac_input.emp  "+employid);
				request.setAttribute("employid", employid);
				CommonMessage.debugMsg("fac_input.emp" +employid);
				
				String Funloclink = request.getParameter("funcLocType");
				request.setAttribute("Funloclink", Funloclink);
				CommonMessage.debugMsg("fac_input.emp" +Funloclink);
				
				String Funloclinks = request.getParameter("funcLocType");
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlEmpfunclocnlink.jsp");					
				rd.forward(request, response);
			}
			
			
			
			else if (action.equals("fac_getCol.emp"))
			{
				CommonMessage.debugMsg("fac_getCol.emp");
				PrintWriter out = response.getWriter();	
				String Funloclink = request.getParameter("funcLocType");
				request.setAttribute("Funloclink", Funloclink);
				CommonMessage.debugMsg("fac_getCol.emp" +Funloclink);
				
				
				String tableModel= null;
				
				if((Funloclink.equals("FACT"))){
				 tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.Factorymaster", "factorymaster");
				
				JSONObject colmodel = JSONObject.fromString(tableModel);
				out.println(colmodel);
				}
				else if((Funloclink.equals("SECT"))){
				tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.Sectionmaster", "sectionmaster");
					
				JSONObject colmodel = JSONObject.fromString(tableModel);
				out.println(colmodel);
					
				}
				else if((Funloclink.equals("CELL"))){
					tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.Linemaster", "linemaster");
						
					JSONObject colmodel = JSONObject.fromString(tableModel);
					out.println(colmodel);
						
					}
				else if((Funloclink.equals("MCHM"))){
					tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.EquipmentLink", "Equipmentmaster");
						
					JSONObject colmodel = JSONObject.fromString(tableModel);
					out.println(colmodel);
						
					}
			
			}
		
			else if (action.equals("fac_getData.emp"))
			{
				try
				{   
					
					String Employeeid = request.getParameter("filterString");
					CommonMessage.debugMsg("fac_getData.emp"+Employeeid);
					
					String Funloclink = request.getParameter("funcLocType");
					request.setAttribute("Funloclink", Funloclink);
					httpSession.setAttribute("FunloclinkData ",Funloclink);
					
					CommonMessage.debugMsg("httpSession"+Funloclink);
					
		     	if((Funloclink.equals("FACT"))){	
				   
					List<String []> getAllFactoryName  = employeeService.getAllFactoryname(Employeeid,Funloclink);
					CommonMessage.debugMsg("fac_getData.emp"+getAllFactoryName);
					PrintWriter out = response.getWriter(); 
					JSONObject Factorydata = UIUtils.convertToJqGridTableObject(getAllFactoryName,request,0,0); 
				    out.println(Factorydata);
					}	
		    	else if((Funloclink.equals("SECT"))){
					
					List<String []> getAllSectionName  = employeeService.getAllSectionName(Employeeid,Funloclink);
	                PrintWriter out = response.getWriter(); 
	  			    JSONObject Sectiondata = UIUtils.convertToJqGridTableObject(getAllSectionName,request,0,0); 
	  			    out.println(Sectiondata);
			}
            else if((Funloclink.equals("CELL"))){
            	    
	            	List<String []> getAllLineName  = employeeService.getAllLineName(Employeeid,Funloclink);
	                PrintWriter out = response.getWriter(); 
	  			    JSONObject Linedata = UIUtils.convertToJqGridTableObject(getAllLineName,request,0,0); 
	  			    out.println(Linedata);
			  }	
            else if((Funloclink.equals("MCHM"))){
        	    
            	List<String []> getAllLineName  = employeeService.getAllEquipmentName(Employeeid,Funloclink);
                PrintWriter out = response.getWriter(); 
  			    JSONObject Linedata = UIUtils.convertToJqGridTableObject(getAllLineName,request,0,0); 
  			    out.println(Linedata);
		  }	
			    }catch(Exception e)
				{
					CommonMessage.debugMsg(e.getMessage());
				}

			}
			
			else if( action.equals("fac_save.emp") )
			{
			    EmployeeBean employeeBean = new  EmployeeBean();
			    saveFactory(request,response,employeeBean);

			}
			else if( action.equals("saveImage.emp") )
			{

				PrintWriter out = response.getWriter();
				String imgPath = request.getServletContext().getRealPath("FunctionalLocnServlet");
				imgPath = imgPath.replace("FunctionalLocnServlet","images").replace("\\","/");
			    String finalImage = UIUtils.imageUpload(request,imgPath);
			    out.print(finalImage);
			}
			else if(action.equals("combo_company.emp"))
			{
				try 
				{
					currentFilter = UIUtils.fillComboFilter(request);				
					List<ComboBox>  companyid = employeeService.getcompanycombo(currentFilter);
					UIUtils.writeComboBox(response, companyid,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			else if(action.equals("combo_location.emp"))
			{
				try 
				{
					currentFilter = UIUtils.fillComboFilter(request);					
					List<ComboBox>  locationid = employeeService.getlocationcombo(currentFilter);
					UIUtils.writeComboBox(response, locationid,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			
			else if(action.equals("combo_department.emp"))
			{
				try 
				{
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Department"+action);
					//List<ComboBox>  departmentId = employeeService.getdepartmentcombo("");
					List<ComboBox>  departmentId = employeeService.getdepartmentcombo(currentFilter);
					UIUtils.writeComboBox(response, departmentId,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("combo_designation.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Designation"+action);
					List<ComboBox>  designation = employeeService.getEMPMdesignation(currentFilter);
					UIUtils.writeComboBox(response, designation,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			
			else if(action.equals("Combo_Tradeid.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("TradeID"+action);
					List<ComboBox>  tradeId = employeeService.getEMPMTrade("",currentFilter);
					UIUtils.writeComboBox(response, tradeId,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			else if(action.equals("Combo_Gradeid.emp"))
			{
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Grade"+action);
					List<ComboBox>  gridId = employeeService.getEMPMGrade("",currentFilter);
					UIUtils.writeComboBox(response, gridId,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Employee.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Employee"+action);
					List<ComboBox>  employee = employeeService.getEmpmKeyid("",currentFilter);
					UIUtils.writeComboBox(response, employee,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			
			else if( action.equals("emp_save.emp"))
			{	
			CommonMessage.debugMsg("Inside the Save");
			CommonMessage.debugMsg(request.getParameter("hdnEmpImgUrl"));
				EmployeeBean employeeBean = new  EmployeeBean();
				
				saveEmployee(request,response,employeeBean);
				
		    }
			
			else if( action.equals("emp_delete.emp"))
			{	
			    CommonMessage.debugMsg("Inside the DELETE");
				EmployeeBean employeeBean = new  EmployeeBean();				
				DeleteEmployee(request,response,employeeBean);
				
		    }
			else if(action.equals("employeeupdateImage_save.emp")){
				EmployeeBean employeeBean=new EmployeeBean();
				EmpImageUpdate(request,response,employeeBean);
			}
			
			else if(action.equals("employeeupdatedata_save.emp")){
				EmpDataUpdate(request,response);
			}
		
			else if(action.equals("emp_getCol.emp"))
			{
				CommonMessage.debugMsg(" form getCol");
				PrintWriter out = response.getWriter();
				String keyId = request.getParameter("keyId");
				CommonMessage.debugMsg(" keyId:"+keyId);
				JSONObject jsonObject = new JSONObject();
				List<String[]>roleMapGrid = null;
				CommonFilter commonFilter = new CommonFilter();			
				try {				
					commonFilter.setKey(keyId);
					FilterValues.getCommonFilters(request, commonFilter);
					roleMapGrid =  employeeService.getRoleEmpGrid(commonFilter);	
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
						
				 JSONObject roleMapGridData =UIUtils.convertToJqGridTableObject(roleMapGrid,request,0,0,commonFilter.getTotalRecordCnt());
				 CommonMessage.debugMsg("roleMapGridData:"+roleMapGridData);			 
				 JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				 GridColModel gridColModel = new GridColModel();			
				 gridColModel.setHeaderNum(1);	
				 //gridColModel.setFormatter("formatterChkRoleEmp");
				// gridColModel.setFormattorFromCol("2");
				// gridColModel.setFormattorToCol("2");
				 jqGridTableModel.setSortable(false);
				 jqGridTableModel.setTableButton(true);
				 jqGridTableModel.setEnableFilter(true);
				 jqGridTableModel.setRowNumbers(true);
				 
				 String [] colHeaderHead = roleMapGrid.get(0);
				 String [] colHeader = roleMapGrid.get(1);	
				 
				 List<String[]> headers = new ArrayList<String[]>();	
				 headers.add(colHeader);
				
				 jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				 jsonObject.put("data", roleMapGridData);	
		   	     jsonObject.set("tableWidth", "95%%");
		     	 jsonObject.set("tableHeight", "65%%");
		   	     httpSession.removeAttribute("roleteamempColModel");
				 httpSession.setAttribute("roleteamempColModel",jsonObject);	
				 CommonMessage.debugMsg("jsonObject " + jsonObject);
				 out.println(jsonObject);
					
			}
			else if (action.equals("emp_getData.emp")) {
				CommonMessage.debugMsg("emp_getData.emp:");		
				String keyId = request.getParameter("keyId");	
				CommonMessage.debugMsg(" keyId:"+keyId);
				JSONObject jsonObject = new JSONObject();
				List<String[]>roleMapGrid = null;
				CommonFilter commonFilter = new CommonFilter();			
				try {				
					commonFilter.setKey(keyId);
					FilterValues.getCommonFilters(request, commonFilter);
	    			roleMapGrid =  employeeService.getRoleEmpGrid(commonFilter);
				    PrintWriter out2 = response.getWriter();
					JSONObject studentreportgrid = UIUtils.convertToJqGridTableObject(roleMapGrid, request,2, 0);
					out2.println(studentreportgrid);
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("recall_select.emp"))
			{	
				CommonMessage.debugMsg("Recall function");
				PrintWriter out = response.getWriter();
				GenTlEmployeemst genTlEmployeemst = employeeService.select(request.getParameter("EmployeeKeyId"));
				
				CommonMessage.debugMsg(genTlEmployeemst.getEmpmJoineddate());
				if(genTlEmployeemst.getEmpmJoineddate() != null)
					genTlEmployeemst.setEmpmJoineddate(CommonFunctions.pg_getDateFromPGTimeStamp(genTlEmployeemst.getEmpmJoineddate()));
				if(genTlEmployeemst.getEmpmJoineddate()==Constants.passNullDate || genTlEmployeemst.getEmpmJoineddate()==Constants.futureNullDate)
					genTlEmployeemst.setEmpmJoineddate("{}");
				
				CommonMessage.debugMsg("After fetch the data");
				httpSession.setAttribute("newGenTlEmployeemst", genTlEmployeemst);
				
				CommonMessage.debugMsg("EmployeeMstData"+genTlEmployeemst);
				
				JSONObject employeeData = UIUtils.fromTpmModel(genTlEmployeemst);
				CommonMessage.debugMsg("Recall value:"+employeeData);			
				JSONObject employeeDtl = null;
				List<GenTlEmployeedtl> employeedtlList = genTlEmployeemst.getEmployeeDetail();
				
				if(employeedtlList != null  && employeedtlList.size() > 0){
					GenTlEmployeedtl newEmployeeDtl = employeedtlList.get(0);
					if(newEmployeeDtl.getEmpdBirthdate().length()>10)
						newEmployeeDtl.setEmpdBirthdate(CommonFunctions.pg_getDateFromPGTimeStamp(newEmployeeDtl.getEmpdBirthdate()));
					if(newEmployeeDtl.getEmpdBirthdate()==Constants.passNullDate || newEmployeeDtl.getEmpdBirthdate()==Constants.futureNullDate)
						newEmployeeDtl.setEmpdBirthdate("{}");
					
					employeeDtl = UIUtils.fromTpmModel(newEmployeeDtl);
					CommonMessage.debugMsg("employeeDtl"+employeeDtl);
					
				}
				List<GenTlEmployeeimg> employeeimgList = genTlEmployeemst.getEmployeeImg();
				JSONObject employeeimg = null;
				CommonMessage.debugMsg("employeeimgList:"+employeeimgList);
				if(employeeimgList != null  && employeeimgList.size() > 0){
					GenTlEmployeeimg newEmployeeimg = employeeimgList.get(0);
					employeeimg = UIUtils.fromTpmModel(newEmployeeimg);
					CommonMessage.debugMsg("employeeimg::"+employeeimg);
				}
				
				JSONObject returndata = new JSONObject();
				returndata.put("employeeData", employeeData);
				returndata.put("employeeDtl", employeeDtl);
				returndata.put("employeeimg", employeeimg);
				CommonMessage.debugMsg("Data:"+returndata);
				out.print(returndata);
				
		    }
			
			else if(action.equals("get_empImage.emp"))
			{
				//ServletOutputStream out = response.getOutputStream();
				String nodeId = request.getParameter("empId");
				try{
					GenTlEmployeeimg genTlEmployeeimg  = getEmpImage(request, nodeId);
					CommonMessage.debugMsg(genTlEmployeeimg .getEmpiFilename());
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
				if( genTlEmployeeimg != null ){
					net.sf.json.JSONObject  empJSONObj =  UIUtils.fromTpmModel(genTlEmployeeimg);				
					empJSONObj.put("imgToimBlobimage",genTlEmployeeimg .getEmpiFilename());
					empJSONObj.put("Data",true);
					JSONObject returndata = new JSONObject();
					returndata.put("empImg", empJSONObj);	
					CommonMessage.debugMsg(returndata.toString());
					out.print(returndata.toString());
				}	
				}
				catch(Exception e)
				{
					PrintWriter out = response.getWriter();
					CommonMessage.debugMsg("Image err to be display..."+e.toString());
					JSONObject empJSONObj = new JSONObject();
					empJSONObj.put("imgToimBlobimage","");
					empJSONObj.put("Data",false);
					JSONObject returndata = new JSONObject();
					returndata.put("empImg", empJSONObj);	
					CommonMessage.debugMsg(returndata.toString());
					out.print(returndata.toString());
				}
			}
			else if(action.equals("Combo_Cityid.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("City"+action);
					List<ComboBox>  employee = employeeService.getEmpmCity("",currentFilter);
					UIUtils.writeComboBox(response, employee,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Stateid.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("State"+action);
					List<ComboBox>  employee = employeeService.getEmpdStateid("",currentFilter);
					UIUtils.writeComboBox(response, employee,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
			
			else if(action.equals("Combo_Countryid.emp")){
				try {
					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Country"+action);
					List<ComboBox>  employee = employeeService.getEmpdCountryid("",currentFilter);
					UIUtils.writeComboBox(response, employee,currentFilter);
				} 
			catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}			
			
			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
			
		}
	    
   private void EmpImageUpdate(HttpServletRequest request, HttpServletResponse response,
				EmployeeBean employeeBean) throws ValidationExceptions, Exception {
			// TODO Auto-generated method stub
	HttpSession httpSession = request.getSession(false);
   	AdmTlUsermst user = UIUtils.getLoginUser(request);
    PrintWriter out=response.getWriter();
   	String imagePath = UIUtils.getImagePath(request);
   	String Imgurl=request.getParameter("Imgurl");
   	String userkeyid=request.getParameter("userkeyid");
   	CommonMessage.debugMsg("user"+userkeyid);
   	GenTlEmployeeimg newGenTlEmployeeimg = new GenTlEmployeeimg();
	GenTlEmployeemst newGenTlEmployeemst = new GenTlEmployeemst();
	newGenTlEmployeeimg.setEmpiBlobimage(UIUtils.getImagePath(request));
	newGenTlEmployeeimg.setEmpiFilename(Imgurl);
	newGenTlEmployeeimg.setEmpiEmployeeid(userkeyid);
	if(newGenTlEmployeeimg!=null)
		newGenTlEmployeemst.getEmployeeImg().add(newGenTlEmployeeimg);
	GenTlEmployeemst existGenTlEmployeemst = (GenTlEmployeemst)httpSession.getAttribute("newGenTlEmployeemst"); 

	          if( newGenTlEmployeeimg.getEmpiEmployeeid()  != null )
					{							
						CommonMessage.debugMsg("KeyId is Null");
						existGenTlEmployeemst =	employeeService.imagecreate(newGenTlEmployeemst,existGenTlEmployeemst,employeeBean,userkeyid);
					}	
	             boolean insert=true;
	    		JSONObject successData = new JSONObject();
				String msgPropertyIdnt;					 
				 if( insert){
					msgPropertyIdnt = "success-update";
				 }else
					msgPropertyIdnt = "success-update";
				 successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
		}


private void EmpDataUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception{
     PrintWriter out=response.getWriter();
     HttpSession httpSession=request.getSession(false);
     String empKeyid=request.getParameter("userkeyid");
     CommonMessage.debugMsg("The empKeyid"+empKeyid);
     String empName=request.getParameter("empName");
     CommonMessage.debugMsg("The empName"+empName);
     String empPhoneNo=request.getParameter("empPhoneno");
     CommonMessage.debugMsg("The empPhoneNo"+empPhoneNo);
     String empEmail=request.getParameter("empEmail");
     CommonMessage.debugMsg("The empEmail"+empEmail);
     String EmployeeList="";

	
     try{ 
    	    if(empKeyid!=null)  
    	    EmployeeList=employeeService.EmployeeData(empKeyid, empName, empPhoneNo,empEmail);
    	    boolean insert=true;
    		JSONObject successData = new JSONObject();
			String msgPropertyIdnt;					 
			 if( insert){
				msgPropertyIdnt = "success-update";
			 }else
				msgPropertyIdnt = "success-update";
			 
			 successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString()); 
 		    
     }
     catch(BusinessApplicationExceptions e){
    	    JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-update"));
			JSONObject returnData = new JSONObject();
			returnData.put("formClear",false);
			returnData.put("msg"," Record Can't be Deleted Reference Found");
			out.print(returnData.toString()); 
     }
   }
  
  private void saveFactory(HttpServletRequest request,HttpServletResponse response, EmployeeBean employeeBean) throws IOException {
			  
	        // TODO Auto-generated method stub
	       
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
    ServletOutputStream out = response.getOutputStream();
	if(httpSession != null && user != null)
	{
           
	       GenTlEmpfunclocnlink newGenTlEmpfunclocnlink = new GenTlEmpfunclocnlink();
	       newGenTlEmpfunclocnlink=(GenTlEmpfunclocnlink)UIUtils.setBeanProperties((Object)newGenTlEmpfunclocnlink,request); 
	       GenTlEmpfunclocnlink existGenTlEmpfunclocnlink = new GenTlEmpfunclocnlink();
		    
	  try{
			
			String Factoryvar = request.getParameter("factory");
			//newGenTlEmpfunclocnlink.setEfllfunclocn(Factoryvar);		
			CommonMessage.debugMsg("Factoryvar::10-10-13"+Factoryvar);
	        String Employeeid1 = request.getParameter("empid");
	        CommonMessage.debugMsg("Employeeid1::10-10-13"+Employeeid1);
	        String Funloclink = request.getParameter("hdnfunloclink");
	        //String  Funloclink= (String)httpSession.getAttribute("FunloclinkData");
	        CommonMessage.debugMsg("saveFactory"+Funloclink);
	        //httpSession.getAttribute("Funloclink");
			
	        //String Employeeid1 = request.getParameter("hdnEmpname");
			//newGenTlEmpfunclocnlink.setEfllemployeeid(Factoryvar1);
			//CommonMessage.debugMsg("Employeeid1"+Employeeid1);
			List<GenTlEmpfunclocnlink> FactorylinkList = null;
			employeeBean.setEmpIdForFuncLoc(Employeeid1);
			
			employeeBean.setFuncloctypeForFuncLoc(Funloclink);
			
			//httpSession.getAttribute("Funloclink");
			// employeeBean.setEmpIdForFuncLoc(Funloclink);
			
		    if(UIUtils.isValidKeyId(Factoryvar))
			{
				JSONArray FactorylinkJson = null;
		    	if(UIUtils.isValidKeyId(Factoryvar))
		    	{
		    		FactorylinkJson = JSONArray.fromString(Factoryvar);
                    //CommonMessage.debugMsg("FactorylinkJson"+FactorylinkJson);
		    		FactorylinkList = extracted(newGenTlEmpfunclocnlink, FactorylinkJson);
		    		CommonMessage.debugMsg("FactorylinkList"+FactorylinkList);
 		        }
 		     }
			gentlempfunctionlinkservice.create(newGenTlEmpfunclocnlink,existGenTlEmpfunclocnlink,employeeBean,FactorylinkList,Factoryvar);		
	        
			
			JSONObject successData= new JSONObject();
    		JSONObject Factorymst= new JSONObject();
    		String savemsg;
    	   
			savemsg= "Data saved succesfully";
				
			
    		successData.put("msg", savemsg);
    		Factorymst.put("formClear",false);
    		
    		Factorymst.put("successData", successData);
    		out.print(Factorymst.toString());
			 
	  
	  
	  } 
	  
	  
	      catch(Exception e)
			{				
				e.printStackTrace();
			}
	}
	
	}


private List<GenTlEmpfunclocnlink> extracted(
		GenTlEmpfunclocnlink newGenTlEmpfunclocnlink, JSONArray FactorylinkJson) {
	return (List<GenTlEmpfunclocnlink>)UIUtils.convertJSONArrToList(newGenTlEmpfunclocnlink,FactorylinkJson);
}
		
private GenTlEmployeeimg getEmpImage(HttpServletRequest request,String nodeId) throws Exception{
	    	
	  	  HttpSession httpSession = request.getSession(false);
	  	  CommonMessage.debugMsg("inside GenTlEmployeeimg getEmpImage........................");
	  	  if( UIUtils.isValidKeyId(nodeId) ){
	  		GenTlEmployeeimg genTlEmployeeimg = null;
	  		genTlEmployeeimg = employeeService.selectImg(nodeId);
	  		   
	  		   String filePath = UIUtils.getImagePath(request);
	  		   
	  		 genTlEmployeeimg.setEmpiBlobimage(filePath);
	  		genTlEmployeeimg.setEmpiFilename(UIUtils.TPM_TEMPIMG_DIR);
	  		
	  		CommonMessage.debugMsg("filename:."+genTlEmployeeimg.getEmpiFilename());
	  		 
	  		   try{
	  			 genTlEmployeeimg =employeeService.getLayoutImg(genTlEmployeeimg);
	  			   if( genTlEmployeeimg != null)
	  				   request.setAttribute("genTlLayoutfieldimg",genTlEmployeeimg);
	  		   }catch(Exception e){
	  			   
	  		   }
	  		   request.setAttribute("genTlLayoutfieldimg", genTlEmployeeimg);
	  		   
	  		  
	  		   return genTlEmployeeimg;
	  	  }	
	  	  return null;
}

		private void saveEmployee(HttpServletRequest request,
				HttpServletResponse response, EmployeeBean employeeBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	String imagePath = UIUtils.getImagePath(request);
	    	CommonMessage.debugMsg("Image Path Is>>>"+imagePath);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		// sriram change the attribute value genTlEmployeemst to genTlEmployeemstNew
	    		GenTlEmployeemst existGenTlEmployeemst = (GenTlEmployeemst)httpSession.getAttribute("genTlEmployeemstNew"); 
	    		//GenTlEmployeedtl existGenTlEmployeedtl = (GenTlEmployeedtl)httpSession.getAttribute("genTl"); 
	    		
	    		GenTlEmployeemst newGenTlEmployeemst = new GenTlEmployeemst();
	    		GenTlEmployeedtl newGenTlEmployeedtl = new GenTlEmployeedtl();
	    		GenTlEmployeeimg newGenTlEmployeeimg = new GenTlEmployeeimg();
	    		newGenTlEmployeemst.setEmpmCreatedby(user.getUsrm_ccno());
	    		newGenTlEmployeedtl.setEmpdCreatedby(user.getUsrm_ccno());
	    		newGenTlEmployeeimg.setEmpiBlobimage(UIUtils.getImagePath(request));
	    		newGenTlEmployeeimg.setEmpiFilename(request.getParameter("hdnEmpImgUrl"));
	    		CommonMessage.debugMsg("Employee Filename........."+newGenTlEmployeeimg.getEmpiFilename());
	    		CommonMessage.debugMsg("Employee File Name>>>"+newGenTlEmployeeimg.getEmpiFilename());
	    		CommonMessage.debugMsg("old id::::"+existGenTlEmployeemst);
	    		
	    		newGenTlEmployeemst =(GenTlEmployeemst)UIUtils.setBeanProperties((Object)newGenTlEmployeemst,request);
	    		newGenTlEmployeedtl =(GenTlEmployeedtl)UIUtils.setBeanProperties((Object)newGenTlEmployeedtl,request);
	    		//CommonMessage.debugMsg(" newGenTlEmployeedtl.bdate " + newGenTlEmployeedtl.getEmpdBirthdate());
	    		if( newGenTlEmployeedtl != null)
	    			newGenTlEmployeemst.getEmployeeDetail().add(newGenTlEmployeedtl);
	    		if( newGenTlEmployeeimg != null)
	    			newGenTlEmployeemst.getEmployeeImg().add(newGenTlEmployeeimg);
	    		
	    		employeeBean.setEmpdBirthdate(newGenTlEmployeedtl.getEmpdBirthdate());
	    		employeeBean.setEmpmJoineddate(newGenTlEmployeemst.getEmpmJoineddate());
	    		
	    		employeeBean =(EmployeeBean) UIUtils.setBeanProperties((Object)employeeBean,request);
	    	
	    		newGenTlEmployeemst.setEmpmCode(newGenTlEmployeemst.getEmpmCode().toUpperCase());
	    		newGenTlEmployeemst.setEmpmEmployeenumber(newGenTlEmployeemst.getEmpmCode().toUpperCase());
				CommonMessage.debugMsg("Date::"+newGenTlEmployeemst.getEmpmKeyid());
				try{
					boolean insert = true;
					CommonMessage.debugMsg("  newGenTlEmployeemst.getEmpmKeyid() " +  newGenTlEmployeemst.getEmpmKeyid());
					if( newGenTlEmployeemst.getEmpmKeyid() == null )
					{							
						CommonMessage.debugMsg("KeyId is Null");
						existGenTlEmployeemst =	employeeService.create(newGenTlEmployeemst,existGenTlEmployeemst,employeeBean);
					}	
					else
					{
						CommonMessage.debugMsg("Update function");						
						existGenTlEmployeemst = employeeService.update(newGenTlEmployeemst,existGenTlEmployeemst,employeeBean);
						insert = false;
					}					
					CommonMessage.debugMsg("After the IF Loop");
					CommonMessage.debugMsg("Ooooold KEyId"+existGenTlEmployeemst.getEmpmKeyid());
					httpSession.setAttribute(existGenTlEmployeemst.getEmpmKeyid(), existGenTlEmployeemst);
				
					httpSession.setAttribute("GenTlEmployeemst", existGenTlEmployeemst);
					
					CommonMessage.debugMsg("before formBeanIdentifier");
					String formBeanIdentifier = "EmployeeBean"+employeeBean.getFormActionMode();
					CommonMessage.debugMsg("After formBeanIdentifier");
					
					httpSession.setAttribute(formBeanIdentifier,employeeBean);
					
					
					
					JSONObject successData = new JSONObject();
					
					String msgPropertyIdnt;					 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						msgPropertyIdnt = "success-update";
					 
					 successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						
						
					 
					//successData.put("msg","Data Saved Successfully");
					successData.put("mode",employeeBean.getFormMode() );
					successData.put("keyId", existGenTlEmployeemst.getEmpmKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					returnData.put("formClear",true);
					out.print(returnData.toString());
					
					
										
				}catch(ValidationExceptions e)
				{
					
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "employeeCreation");
					errMessage.put("fromMode",employeeBean.getFormActionMode());
					out.print(errMessage.toString());
					CommonMessage.debugMsg(" e " + e.getMessage() );
				}catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("Error Servler e -"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "employeeCreation");
					out.print(errMessage.toString());
					CommonMessage.debugMsg(" e " + errMessage );
					
				}catch(Exception e)
				{
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
				
	    	}	
	    	
		}
			
		
		
	    	/* save form for detail table */
	    	
	    	
	    	
	    /*end of Detail table*/
	    	
	    	private void DeleteEmployee(HttpServletRequest request,
					HttpServletResponse response, EmployeeBean employeeBean) throws IOException
			{
				HttpSession httpSession = request.getSession(false);
		    	ServletOutputStream out = response.getOutputStream();
		    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		    	
		    	if( httpSession != null && user != null)
		    	{	
		    		GenTlEmployeemst existGenTlEmployeemst = (GenTlEmployeemst)httpSession.getAttribute("newSession"); 
		    		//GenTlEmployeedtl existGenTlEmployeedtl = (GenTlEmployeedtl)httpSession.getAttribute("genTl"); 
		    		
		    		GenTlEmployeemst newGenTlEmployeemst = new GenTlEmployeemst();
		    		GenTlEmployeedtl newGenTlEmployeedtl = new GenTlEmployeedtl();
		    		GenTlEmployeeimg newGenTlEmployeeimg = new GenTlEmployeeimg();
		    		
		    		newGenTlEmployeemst.setEmpmCreatedby(user.getUsrm_ccno());
		    		newGenTlEmployeedtl.setEmpdCreatedby(user.getUsrm_ccno());
		    	
		    		
		    		CommonMessage.debugMsg("old id::::"+existGenTlEmployeemst);
		    		newGenTlEmployeemst =(GenTlEmployeemst)UIUtils.setBeanProperties((Object)newGenTlEmployeemst,request);
		    		newGenTlEmployeedtl =(GenTlEmployeedtl)UIUtils.setBeanProperties((Object)newGenTlEmployeedtl,request);
		    		newGenTlEmployeeimg =(GenTlEmployeeimg)UIUtils.setBeanProperties((Object)newGenTlEmployeeimg,request);
		    		CommonMessage.debugMsg(" newGenTlEmployeedtl.bdate " + newGenTlEmployeedtl.getEmpdBirthdate());
		    		newGenTlEmployeemst.getEmployeeDetail().add(newGenTlEmployeedtl);
		    		newGenTlEmployeemst.getEmployeeImg().add(newGenTlEmployeeimg);
		    		
		    		employeeBean =(EmployeeBean) UIUtils.setBeanProperties((Object)employeeBean,request);
		    		
					CommonMessage.debugMsg("Date::"+newGenTlEmployeemst.getEmpmJoineddate());
					try{
						CommonMessage.debugMsg("  newGenTlEmployeemst.getEmpmKeyid() " +  newGenTlEmployeemst.getEmpmKeyid());
						
							CommonMessage.debugMsg("Delete function");						
							existGenTlEmployeemst = employeeService.delete(newGenTlEmployeemst);						
					
						
						CommonMessage.debugMsg("After the IF Loop");
						httpSession.setAttribute(existGenTlEmployeemst.getEmpmKeyid(), existGenTlEmployeemst);
						httpSession.setAttribute("GenTlEmployeemst", existGenTlEmployeemst);
						String formBeanIdentifier = "EmployeeBean"+employeeBean.getFormActionMode();
						httpSession.setAttribute(formBeanIdentifier,employeeBean);
								
						JSONObject mode = new JSONObject();
						mode.put("formMode",employeeBean.getFormActionMode());
						JSONObject persistentData = new JSONObject(); 
						persistentData.put("EmpmKeyid",existGenTlEmployeemst.getEmpmKeyid());
						persistentData.put("fromBean", formBeanIdentifier);
						
						JSONObject successData = new JSONObject();
						CommonMessage.debugMsg("SuccessData");
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
						successData.put("mode",employeeBean.getFormMode() );
						successData.put("keyId", existGenTlEmployeemst.getEmpmKeyid());
						JSONObject returnData = new JSONObject();						
						returnData.put("successData", successData);		
						CommonMessage.debugMsg("successData"+successData);
						
						out.print(returnData.toString());
						
						
					}catch(ValidationExceptions e)
					{
						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "employeeCreation");
						errMessage.put("fromMode",employeeBean.getFormActionMode());
						out.print(errMessage.toString());
						
					}catch(BusinessApplicationExceptions e)
					{
						CommonMessage.debugMsg("Business EXC "+e);
						JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "employeeCreation");
						out.print(errMessage.toString());
						
					}catch(Exception e)
					{
						CommonMessage.debugMsg("Error Msg:" + e.getMessage());
						JSONObject err = new JSONObject();
						err.put("tpmException", "Select Employee");
						out.print(err.toString());
					}
		    	}
	    	
		}
	    	
	
	private void empgroupCreation(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		empgroupCreationService=(EmpgroupCreationService) UIUtils.getServiceObject(request,"EmpgroupCreationServiceImpl");
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		CommonMessage.debugMsg("action  :  "+action);
		response.setContentType("text/html");
		response.setContentType("text/json");
	
		 if(action.equals("EmpgroupCreation_input.emp"))	
	  	 {
			String keyid = request.getParameter("keyid");
			request.setAttribute("keyid", keyid);
			GenTlMomGroupmst newgenTlMomGroupmst = new GenTlMomGroupmst();
			CommonMessage.debugMsg("keyid==" + keyid);
			if (UIUtils.isValidKeyId(keyid)) {
				newgenTlMomGroupmst = empgroupCreationService
						.getGridValues(keyid);

			}
			else {
				String loginFlid = (String) httpSession
						.getAttribute("loginFlid");
				newgenTlMomGroupmst.setMgrmFlid(loginFlid);
			}
			request.setAttribute("GenTlMomGroupmst", newgenTlMomGroupmst);
			UIUtils.forwardRequest(request, response,"/pages/EmployeeGroup/EmpgroupCreation.jsp");

		}
		 if(action.equals("EmpgroupView_input.emp"))	
	  	 {
			CommonMessage.debugMsg("action else  "+action);
		    UIUtils.forwardRequest(request, response, "/pages/EmployeeGroup/EmpgroupView.jsp"); 
		 }
		 else if(action.equals("EmpgroupCreation_getCol.emp"))	
	  	 {
			PrintWriter out= response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation","EmpGroupMstView"));
		  }
		 else if(action.equals("EmpgroupCreation_getData.emp")){
			    CommonParams commonparams=new CommonParams();
		  		CommonFilter commonFilter = populateCommonFilter(request,"EmpgroupCreationMstCommonFilter",true);
				FilterValues.getCommonFilters(request, commonFilter);
		  		PrintWriter out=response.getWriter();
		  	try{	   
				    List<String[]> addGrid;
				    String flid=request.getParameter("flid");
					String keyid=request.getParameter("keyid");
					if (UIUtils.isValidKeyId(flid))
						commonparams.setFlid(flid);
					if (UIUtils.isValidKeyId(keyid))
						commonparams.setKeyid(keyid);
			
				  FilterValues.populateGridParams(request, commonparams);
				  addGrid=empgroupCreationService.getempgroupCreationMstGridData(commonFilter,commonparams);
				  CommonMessage.debugMsg("ADDGrid"+addGrid);
				  JSONObject addGridData=UIUtils.convertToJqGridTableObject(addGrid, request, 1,1,commonparams.getTotalRecordCnt());//Change to function
				  out.println(addGridData);	
				}
			    catch(Exception e) {
				e.printStackTrace();
		 		}
			}
		
		 else if(action.equals("EmpgroupDetailCreation_getCol.emp"))	
	  	 {
			CommonMessage.debugMsg("In Detail Get Col Empgc");
			PrintWriter out= response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation","EmpGroupDetailView"));
	  
	  	 }
		 else if(action.equals("EmpgroupDetailCreation_getData.emp"))	
	  	 {
			 CommonMessage.debugMsg("In Get Data Detail creation Empgc");
			 empgroupDetail(request,response);
	  	 }
		 else if(action.equals("EmpgroupDetailCreation_getExcel.emp"))	
	  	 {
			empgroupDetailCreationExcel(request,response);
	  	 }		
		 else if(action.equals("EmpgroupView_getCol.emp"))	
	  	 {
			CommonMessage.debugMsg("In View Get Col Empgc");
			PrintWriter out= response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation","EmpgroupViewGrid"));
	  	 }
		 else if(action.equals("EmpgroupView_getData.emp"))	
	  	 {
			 CommonMessage.debugMsg("In View Data Detail creation Empgc");
			 empgroupDetailView(request,response);
	  	 }
		 else if(action.equals("EmpgroupView_getExcel.emp"))	
	  	 {
			employeegroupViewExcel(request,response);
		 }
		 else if(action.equals("EmpgroupCreation_save.emp"))	
	  	 {
			 empgroupDetailCreationSaveData(request,response);
	  	 }
		 else if(action.equals("EmpgroupCreation_delete.emp"))	
	  	 {
			    deleteEmployeegroup(request,response);
		 }
		 else if(action.equals("EmpgroupCreationMember_remove.emp"))	
	  	 {
			    removeEmployeegroupMember(request,response);
		  }
	}

	private void employeegroupViewExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject colModel = UIUtils.getXlColModel(request, response);
		colModel.put("title", "Group Details");
		String format = ExcelUtils.getFormat(request);
		GridParams gridparams = new GridParams();
		FilterValues.populateGridParams(request, gridparams);
		Workbook wb = empgroupCreationService.getempgroupViewExcel(gridparams,colModel, format);
		ExcelUtils.writeToResponse(response, wb, "GroupReport", format);
	}
	private void empgroupDetailCreationExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
	   	
		      String keyid=request.getParameter("keyid");
			  String jsonStr=UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation","EmpGroupDetailExcel");
			  JSONObject colModel=  JSONObject.fromString(jsonStr);
		      colModel.put("title","Group Member Details");
	          String format=ExcelUtils.getFormat(request);
	          GridParams gridparams=new GridParams();
			  FilterValues.populateGridParams(request, gridparams);
	          Workbook wb=empgroupCreationService.getempgroupDetailExcel(gridparams,colModel,format,keyid);
			  ExcelUtils.writeToResponse(response, wb,"GroupMemberReport",format);
		  } 

	private void removeEmployeegroupMember(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions,Exception {
		
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		try{
			if(UIUtils.isValidKeyId(keyid)){
				empgroupCreationService.DeleteGroupMemberRecord(keyid);
				String msgPropertyIdnt = "success-delete";
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
		}catch(Exception e)
			{
				CommonMessage.debugMsg("Exception: "+e);
				JSONObject err = new JSONObject();
				String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
	}

	private void deleteEmployeegroup(HttpServletRequest request,HttpServletResponse response) throws BusinessApplicationExceptions,Exception{

		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
	
       if( httpSession != null && user != null)
           {	
			
    	        GenTlMomGroupmst newGenTlMomGroupmst =new GenTlMomGroupmst();
    	        GenTlMomGroupmst existGenTlMomGroupmst =(GenTlMomGroupmst) httpSession.getAttribute("newSession");
    	        GenTlMomGroupdtl newGenTlMomGroupdtl=new GenTlMomGroupdtl();
    	        newGenTlMomGroupmst.setMgrmCreatedby(user.getUsrm_ccno());
    	        newGenTlMomGroupdtl.setMgrdCreatedby(user.getUsrm_ccno());
                newGenTlMomGroupmst=(GenTlMomGroupmst)UIUtils.setBeanProperties(newGenTlMomGroupmst, request);
                
    	    	try {				
    	    		existGenTlMomGroupmst = empgroupCreationService.DeleteGroupRecord(newGenTlMomGroupmst);
    	    		httpSession.setAttribute(existGenTlMomGroupmst.getMgrmKeyid(),existGenTlMomGroupmst);
    	    		httpSession.setAttribute("GenTlMomGroupdtl",existGenTlMomGroupmst);
    	    		JSONObject persistentData = new JSONObject();
    	    		persistentData.put("MgrmKeyid",existGenTlMomGroupmst.getMgrmKeyid());
    	    		JSONObject successData = new JSONObject();
    	    		successData.put("msg", UIUtils.getPropertyValue(
						"com.akranta.tpm.resources.CommonMessages",
						"success-delete"));
    	    		successData.put("keyId", existGenTlMomGroupmst.getMgrmKeyid());
    	    		JSONObject returnData = new JSONObject();
    	    		returnData.put("successData", successData);
    	    		returnData.put("formClear", true);
    	    		out.print(returnData.toString());
    	    	}
    	    	catch(Exception e)
    			{
    				e.printStackTrace();
    				JSONObject err = new JSONObject();
    				err.put("tpmException", " Data Not Delete ");
    				out.print(err.toString());
    			}
           }
	}


	private void empgroupDetailView(HttpServletRequest request,HttpServletResponse response) throws Exception {
		out = response.getWriter();
		CommonFilter commonFilter = populateCommonFilter(request,"empgroupCreationMstCommonFilter",true);
		FilterValues.getCommonFilters(request, commonFilter);
		List<String[]> addGrid;
		GridParams gridparams = new GridParams();
		FilterValues.populateGridParams(request, gridparams);
		addGrid = empgroupCreationService.getEmpgroupViewGridData(gridparams);
		JSONObject addGridData = UIUtils.convertToJqGridTableObject(addGrid, request, 1, 2);
		out.println(addGridData);

	}
	
	private void empgroupDetail(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		  
		CommonFilter commonFilter = populateCommonFilter(request,"EmpgroupCreationMstCommonFilter",true);
		FilterValues.getCommonFilters(request, commonFilter);
		PrintWriter out= response.getWriter();
		CommonParams commonparams=new CommonParams();
		try {
			FilterValues.populateGridParams(request, commonparams);
			String mstkeyid = request.getParameter("keyid");
			if (UIUtils.isValidKeyId(mstkeyid))
				commonparams.setKeyid(mstkeyid);
			request.setAttribute("keyid", mstkeyid);
			List<String[]> addGrid = empgroupCreationService.getGroupGridData(commonFilter,commonparams);
			JSONObject addGridData = UIUtils.convertToJqGridTableObject(
					addGrid, request, 1, 1);
			out.println(addGridData);
		}
		 catch(ServiceObjectCreationException e) {
			e.printStackTrace();
	 		}
	}

	private void empgroupDetailCreationSaveData(HttpServletRequest request,	HttpServletResponse response) throws BusinessApplicationExceptions,ValidationExceptions, Exception {
	
		UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
			try
			  {
				if( httpSession != null && user != null)
				{	
					GenTlMomGroupmst existgenTlMomGroupmst =null;
					GenTlMomGroupmst newgenTlMomGroupmst =new GenTlMomGroupmst();
					GenTlMomGroupdtl newgenTlMomGroupdtl=new GenTlMomGroupdtl();
					newgenTlMomGroupmst.setMgrmCreatedby(user.getUsrm_ccno());
					newgenTlMomGroupdtl.setMgrdCreatedby(user.getUsrm_ccno());
					String groupMemberDetail=request.getParameter("jSONArro");  

					newgenTlMomGroupmst=(GenTlMomGroupmst) UIUtils.setBeanProperties((Object)newgenTlMomGroupmst, request);
						/** save JsonARR conversion For Detail **/
						List<GenTlMomGroupdtl> GroupMemberList=new ArrayList<GenTlMomGroupdtl>();
					    JSONArray MemberGridjson = null;	    		
			    		if(UIUtils.isValidKeyId(groupMemberDetail) )
			    		{
			    			if( newgenTlMomGroupdtl != null)
			    			newgenTlMomGroupmst.getGroupMemberDetail().add(newgenTlMomGroupdtl);
			    			MemberGridjson = JSONArray.fromString(groupMemberDetail);
			    		    CommonMessage.debugMsg(" ParamGrid Checking Now :: Json Problem " + MemberGridjson);
			    		    GroupMemberList=(List<GenTlMomGroupdtl>) UIUtils.convertJSONArrToList(newgenTlMomGroupdtl, MemberGridjson);
		     	    		CommonMessage.debugMsg(" After Converting Json To List :: "+GroupMemberList);
		     	    		if(GroupMemberList!= null )
		     	    			newgenTlMomGroupmst.setGroupMemberDetail(GroupMemberList);
			    		}

			    	boolean insert = true;

				    	if( ! UIUtils.isValidKeyId(newgenTlMomGroupmst.getMgrmKeyid()))
					{
				    	existgenTlMomGroupmst=empgroupCreationService.createRecord(newgenTlMomGroupmst, existgenTlMomGroupmst);
				
					}
					else
					{
						existgenTlMomGroupmst=empgroupCreationService.updateRecord(newgenTlMomGroupmst, existgenTlMomGroupmst);
						insert=false;
					}
					JSONObject successData = new JSONObject();
					JSONObject returnData = new JSONObject();
					String msgPropertyIdnt;					 
					 if( insert)
					   {
						msgPropertyIdnt = "success-save";
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation",msgPropertyIdnt));
						successData.put("KeyId",existgenTlMomGroupmst.getMgrmKeyid());
						returnData.put("successData", successData);
						successData.put("formClear",false);
						returnData.put("formClear",false);
						returnData.put("KeyId",existgenTlMomGroupmst.getMgrmKeyid());
						out.print(returnData.toString());
					   }
					 else{
						msgPropertyIdnt = "success-update";
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.EmpgroupCreation",msgPropertyIdnt));
					    successData.put("KeyId",existgenTlMomGroupmst.getMgrmKeyid());
						successData.put("formClear",false);
						returnData.put("formClear",false);  
						returnData.put("successData", successData);
			     		returnData.put("KeyId",existgenTlMomGroupmst.getMgrmKeyid());
						out.print(returnData.toString());
					 }	
			      }
			  }
			  catch (ValidationExceptions e) 
			  {
				  	CommonMessage.debugMsg("ValidationExceptions "+e.getMessage());
					net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "EmpgroupCreation");
					out.print(errMessage.toString());
			  }
			  catch (BusinessApplicationExceptions b)
			  {
				    b.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Duplicate Group Name");
					out.print(err.toString());
				  
			  }
			 catch(Exception e)
			      {   
				 	e.printStackTrace();
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
			      }
			}
		 
	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
		
		HttpSession httpSession = request.getSession(false);
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if(commonFilter !=null && !createNew)
		{
			FilterValues.setPaginationParams(request,commonFilter);
		}
		else{
  			commonFilter =  new CommonFilter();
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
  		return commonFilter;
	}
}
