package com.akranta.tpm.service;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.GenTlEmployeecost;
import com.akranta.tpm.model.WomTlContractormst;

public interface BAL_EmployeeCostService {

	public List<ComboBox> getEmployee(String condSql) throws Exception;
	public List<ComboBox> getDepartment(String condSql) throws Exception;
	public List<ComboBox> getDesignation(String condSql) throws Exception;
	public List<ComboBox> getUtilities(String condSql) throws Exception;
	public List<ComboBox> getVendor(String condSql) throws Exception;
	
	public List<String []> getEmpDeptDes (String EmpId) throws Exception;
	
	public List<String[]> getEmployeeCostGrid(String deptId, String desId, String empId) throws Exception;
	public List<String[]> getUtilityCostGrid(String utilityId) throws Exception;
	public List<String[]> getContractorCostGrid(String utilityId) throws Exception;
	
	public GenTlEmployeecost create(GenTlEmployeecost newGenTlEmployeecost,GenTlEmployeecost oldGenTlEmployeecost ) throws ValidationExceptions, Exception;
	public GenTlEmployeecost update(GenTlEmployeecost newGenTlEmployeecost,GenTlEmployeecost oldGenTlEmployeecost )  throws Exception;

	public WomTlContractormst createContractor(WomTlContractormst newWomTlContractormst,WomTlContractormst oldWomTlContractormst ) throws ValidationExceptions, Exception;
	public WomTlContractormst updateContractor(WomTlContractormst newWomTlContractormst,WomTlContractormst oldWomTlContractormst )  throws Exception;

}



