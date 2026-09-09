package com.akranta.tpm.dao;

import java.util.List;

public interface EmployeeCostDao {
	
	public abstract List<String []> getEmpDeptDes(String EmpId) throws Exception;
	
	public List<String []> getEmployeeCostGridQry(String deptId, String desId, String empId) throws Exception;
	public List<String []> getUtilityCostGridQry(String vendorId) throws Exception;
	public List<String []> getContractorCostGridQry(String contractorId) throws Exception;

	
}
