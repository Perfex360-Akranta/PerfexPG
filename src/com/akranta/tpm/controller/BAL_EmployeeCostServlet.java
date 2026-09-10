
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmployeeCostBean;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlEmployeecost;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.WomTlContractormst;
import com.akranta.tpm.service.EmployeeCostService;
//import com.akranta.tpm.service.impl.EmployeeAreaMappingServiceImpl;
import com.akranta.tpm.service.impl.EmployeeCostServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
//import com.akranta.model.dao.bdm.WrkOrderSheetDao;
public class BAL_EmployeeCostServlet  extends HttpServlet  {

	EmployeeCostService employeeCostService;	
	String formTypeIdentifier [];
	
	private static final long serialVersionUID = 1L;

	public BAL_EmployeeCostServlet() throws Exception
	{
		//Constructor
		//employeeCostService = new EmployeeCostServiceImpl();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		process(request, response); 
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		 
		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		comboFilter=UIUtils.fillComboFilter(request);
		

		
		//if (action.equals("EmpCost_input.ec")) 
		try {
			employeeCostService =  (EmployeeCostServiceImpl)UIUtils.getServiceObject(request,"EmployeeCostServiceImpl");
		
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		if (action.equals("employeeCostReport_getCol.ec"))
		{ 	
			System.out.println("inside getcol.d...");
			
			PrintWriter out = response.getWriter();
			String type = request.getParameter("type");
			
			if (type.equals("Employee"))	{
				System.out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "employeeCost"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "employeeCost"));			
			}
			else if (type.equals("Utility"))	{
				System.out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "utilityCost"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "utilityCost"));			
			}
			else if (type.equals("Contractor"))	{
				System.out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "contractorCost"));
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EmployeeCostColModel", "contractorCost"));			
			}			
		}
		
		else if( action.equals("employeeCostReport_getData.ec") )
		{		
			try
			{	
				System.out.println("inside getdata ss...");
				
				System.out.println(request.getParameter("type"));
				
		      	response.setContentType("text/html");	
				PrintWriter out = response.getWriter();			

				List<String []> employeeCostList ;				
				String type = request.getParameter("type");				
				
				if (type.equals("Employee"))	{
					String deptId = request.getParameter("deptId");
					String desId = request.getParameter("desId");
					String empId = request.getParameter("empId");					
					employeeCostList  = employeeCostService.getEmployeeCostGrid(deptId, desId, empId);
				} 
				else if (type.equals("Utility"))	{
					String utilityId = request.getParameter("utilityId");
					employeeCostList = employeeCostService.getUtilityCostGrid(utilityId);
				}
				//else if (type.equals("Contractor"))	{
				else { 
					String vendorId = request.getParameter("vendorId");
					employeeCostList = employeeCostService.getContractorCostGrid(vendorId);
				}					
				
				System.out.println(employeeCostList.size());
  			 	JSONObject employeeCostData = UIUtils.convertToJqGridTableObject(employeeCostList,request,0,0);
  			 	out.println(employeeCostData);
  			 	
			}catch(Exception e)
				{ System.out.println(e.getMessage()); }			
		}
	
		else if( action.equals("combo_getEmployee.ec"))
		{				
			try {
				System.out.println("inside comb get empl");
				String deptId = request.getParameter("deptId"); 
				String desId = request.getParameter("desId");
				System.out.println("dept"+deptId);
				System.out.println("des"+desId);
				String condSql ="";
				
				if (! deptId.equals("") )
					condSql+= " AND EMPM_DEPARTMENTID = '" + deptId + "' ";
				if (! desId.equals("") )
					condSql+= " AND EMPM_DESIGNATIONID = '" + desId + "' ";
				
				condSql+= " AND EMPM_ACTIVE = 'Y' ";
				
				List<ComboBox>  department = employeeCostService.getEmployee(condSql);				
				UIUtils.writeComboBox(response, department, comboFilter);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		else if( action.equals("combo_department.ec"))
		{				
			try {
				List<ComboBox>  department = employeeCostService.getDepartment("");				
				UIUtils.writeComboBox(response, department, comboFilter);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
		else if( action.equals("combo_designation.ec"))
		{				
			try {
				List<ComboBox>  desingation = employeeCostService.getDesignation("");				
				UIUtils.writeComboBox(response, desingation, comboFilter);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}	
		
		else if( action.equals("combo_utilities.ec"))
		{				
			try {
				List<ComboBox>  contractor = employeeCostService.getUtilities("");				
				UIUtils.writeComboBox(response, contractor, comboFilter);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}			

		else if( action.equals("combo_vendor.ec"))
		{				
			try {				
				List<ComboBox>  vendor = employeeCostService.getVendor("");				
				UIUtils.writeComboBox(response, vendor, comboFilter);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		

		else if( action.equals("employeeCost_getEmpDeptDes.ec"))
		{	
			try {
				List<String []> empDetils = employeeCostService.getEmpDeptDes(request.getParameter("empId"));
				writeEmpDetails(response,empDetils);
			}
			catch (Exception e) {				
				e.printStackTrace();
			}
	    }		
		else if( action.equals("employeeCost_save.ec"))
		{
			System.out.println(" inside save " );
				saveEmployeeCost(request,response);			
		}		
		
		String dispatchUrl = null; 
		
		if (action.equals("EmployeeCost_input.ec")) 
		{			
			System.out.println("inside empo");
			System.out.println("input the jsp");
			
			String keyid = request.getParameter("keyId");
			String userEvent = request.getParameter("userEvent");
			System.out.println(" userEvent  " + userEvent);
			response.setContentType("text/html");
			
			if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new")))								
				request.setAttribute("keyid", keyid);			
			else
				request.setAttribute("keyid", "");
						
			dispatchUrl ="/pages/EmployeeCost.jsp";
			request.setAttribute("Type", "Employee");
		}
		else if (action.equals("UtilityCost_input.ec")) 
		{
			System.out.println("input the jsp");
			String keyid = request.getParameter("keyId");
			String userEvent = request.getParameter("userEvent");
			System.out.println(" userEvent  " + userEvent);
			response.setContentType("text/html");
			
			if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new")))								
				request.setAttribute("keyid", keyid);			
			else
				request.setAttribute("keyid", "");
			
			dispatchUrl ="/pages/EmployeeCost.jsp";
			request.setAttribute("Type", "Utility");
		}
		else if (action.equals("ContractorCost_input.ec")) 
		{
			System.out.println("input the jsp");
			String keyid = request.getParameter("keyId");
			String userEvent = request.getParameter("userEvent");
			System.out.println(" userEvent  " + userEvent);
			response.setContentType("text/html");
			
			if( (keyid != null && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new")))								
				request.setAttribute("keyid", keyid);			
			else
				request.setAttribute("keyid", "");
			
			dispatchUrl ="/pages/EmployeeCost.jsp";
			request.setAttribute("Type", "Contractor");
		}		
		
		else if (action.equals("EmpCost_view.ec")) 
		{
			//do nothing
	    }
		
		System.out.println(" dispatchUrl " + dispatchUrl);
		 if (dispatchUrl != null)
		 {
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
			System.out.println(" response " + response); 
		 }
	}

	
private void saveEmployeeCost(HttpServletRequest request, HttpServletResponse response ) throws IOException
{
	System.out.println("Pcl MAIN");		
	HttpSession httpSession = request.getSession(false);
	ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	if( httpSession != null && user != null)
	{   
		EmployeeCostBean employeeCostBean = (EmployeeCostBean)httpSession.getAttribute("EmployeeCostServletEmployeeCostBean");
	
		if (employeeCostBean  == null )	
			employeeCostBean  = new EmployeeCostBean();
		
		System.out.println("savemode"+request.getParameter("saveMode"));	
		System.out.println("empm"+request.getParameter("empmId"));
		System.out.println("normalcost"+request.getParameter("normalCost"));
		
		if (request.getParameter("saveMode").equals("Update"))
			employeeCostBean.setFormActionMode("Update");
		else 
			employeeCostBean.setFormActionMode("Save");
				
		System.out.println("formBean Mode"+employeeCostBean.getFormActionMode());
		
		try{
			String keyId="";
			String type = request.getParameter("type"); 
			
			if (type.equals("Employee") || type.equals("Utility")) {
				
				GenTlEmployeecost existGenTlEmployeecost = (GenTlEmployeecost)httpSession.getAttribute("GenTlEmployeecost");				
	    		GenTlEmployeecost newGenTlEmployeecost = new GenTlEmployeecost();
	    		GenTlEmployeecost oldGenTlEmployeecost = new GenTlEmployeecost();
	    		newGenTlEmployeecost.setEmpcCreatedby(user.getUsrm_ccno());	
	    		newGenTlEmployeecost =(GenTlEmployeecost)UIUtils.setBeanProperties((Object)newGenTlEmployeecost,request);	
				System.out.println(request.getParameter("cmbEmpcManpoweridcc"));
				
				if (type.equals("Employee"))
					newGenTlEmployeecost.setEmpcType("E");
				else
					newGenTlEmployeecost.setEmpcType("U");
				
				newGenTlEmployeecost.setEmpcKeyid(request.getParameter("empcId"));
				newGenTlEmployeecost.setEmpcEmployeeid(request.getParameter("empmId"));				
				newGenTlEmployeecost.setEmpcCostperhour(request.getParameter("normalCost"));
				newGenTlEmployeecost.setEmpcOtcostperhour(request.getParameter("otCost"));
				newGenTlEmployeecost.setEmpcCalloutcostperhour(request.getParameter("callOutCost"));
				newGenTlEmployeecost.setEmpcEffectivefromdate(request.getParameter("effectiveFrom"));				
				System.out.println(newGenTlEmployeecost.getEmpcOtcostperhour());
				
				if( employeeCostBean.getFormActionMode().equals("Save")) {	
					existGenTlEmployeecost =employeeCostService.create(newGenTlEmployeecost, oldGenTlEmployeecost);
				}	
				else{
					existGenTlEmployeecost = employeeCostService.update(newGenTlEmployeecost, oldGenTlEmployeecost);
				}			
				System.out.println(" Keyid () " +  keyId);					
				httpSession.setAttribute(existGenTlEmployeecost.getEmpcKeyid(), existGenTlEmployeecost);				
			}
			else if (type.equals("Contractor"))
			{
				System.out.println(" inside contractor cost sdjkdkdf");	
				WomTlContractormst existWomTlContractormst = (WomTlContractormst)httpSession.getAttribute("WomTlContractormst");				
	    		WomTlContractormst newWomTlContractormst = new WomTlContractormst();
	    		WomTlContractormst oldWomTlContractormst = new WomTlContractormst();
	    		newWomTlContractormst.setCncsCreatedby(user.getUsrm_ccno());	
	    		newWomTlContractormst =(WomTlContractormst)UIUtils.setBeanProperties((Object)newWomTlContractormst,request);	
				System.out.println(request.getParameter("cmbEmpcManpoweridcc"));
				
				newWomTlContractormst.setCncsKeyid(request.getParameter("empcId"));
				newWomTlContractormst.setCncsVendorid(request.getParameter("vendorId"));				
				newWomTlContractormst.setCncsUnskilledgradeid(request.getParameter("empmId"));
				newWomTlContractormst.setCncsCostperhour(request.getParameter("normalCost"));
				newWomTlContractormst.setCncsOtcost(request.getParameter("otCost"));
				newWomTlContractormst.setCncsHolidaycost(request.getParameter("callOutCost"));
				newWomTlContractormst.setCncsEffectivefromdate(request.getParameter("effectiveFrom"));			
				
				newWomTlContractormst.setCncsType("C");
				
				System.out.println(newWomTlContractormst.getCncsOtcost());
				
				if( employeeCostBean.getFormActionMode().equals("Save")) {	
					existWomTlContractormst =employeeCostService.createContractor(newWomTlContractormst, oldWomTlContractormst);
				}	
				else{
					existWomTlContractormst = employeeCostService.updateContractor(newWomTlContractormst, oldWomTlContractormst);
				}			
				System.out.println(" Keyid () " +  keyId);					
				httpSession.setAttribute(existWomTlContractormst.getCncsKeyid(), existWomTlContractormst);
			}
				
			
			String formBeanIdentifier = "employeeCostBean"+employeeCostBean.getFormActionMode();
			httpSession.setAttribute(formBeanIdentifier,employeeCostBean);
			
			JSONObject mode = new JSONObject();
			mode.put("formMode",employeeCostBean.getFormActionMode());
			JSONObject persistentData = new JSONObject(); 
			persistentData.put("bphmKeyid",keyId);
			persistentData.put("fromBean", formTypeIdentifier);
			
			JSONObject successData = new JSONObject();
			successData.put("msg","Data Saved Successfully");
			successData.put("mode",employeeCostBean.getFormMode() );
			successData.put("keyId", keyId);
			JSONObject returnData = new JSONObject();					
			returnData.put("successData", successData);
			returnData.put("formClear", false);
			out.print(returnData.toString());
			
		}catch(ValidationExceptions e)
		{
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "employeeCostCreation");
			errMessage.put("fromMode",employeeCostBean.getFormActionMode());
			out.print(errMessage.toString());
			
		}catch(BusinessApplicationExceptions e)
		{
			System.out.println("DNFKHDJFDK"+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "employeeCostCreation");
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

public static void writeEmpDetails(HttpServletResponse response,List<String[]> empDetails ) throws IOException
{
	 response.setContentType("text/html;charset=UTF-8");
	 PrintWriter out = response.getWriter(); 	
	 System.out.println("inside write empdet");
     //JSONArray jsonObject = JSONArray.fromObject(machineHirerachy);
	 JSONObject jsonObject = new  JSONObject();
	 jsonObject.put("deptId", empDetails.get(0)[0]);
	 jsonObject.put("desId",empDetails.get(0)[1]);
	 JSONObject empdetails = new  JSONObject();
	 empdetails.put("empDetails", jsonObject);
     System.out.println("completed");
     out.print(empdetails);
}

}
