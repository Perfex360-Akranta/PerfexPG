package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_EmployeeCostDao;

/*Employee dao implementation */
public class BAL_EmployeeCostDaoImpl implements BAL_EmployeeCostDao {
	
	private DBActionTemplate dbActionTemplate; 
	
	public BAL_EmployeeCostDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String []> getEmpDeptDes(String empId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside empcost daoimpl"+empId);		
		String sql="";		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append (" SELECT EMPM_DEPARTMENTID, EMPM_DESIGNATIONID ");
		buffer.append (" FROM GEN_TL_EMPLOYEEMST");
		buffer.append (" WHERE EMPM_KEYID ='"+empId+"' ");
		
		sql = buffer.toString();
		//String empCost =dbActionTemplate.getSingleValue(sql);
		List<String[]> empDetails = dbActionTemplate.getDataList(sql);
		System.out.println("Inside empcost value"+empDetails.size());
		
		return empDetails;
	}	
	
	public List<String[]> getEmployeeCostGridQry(String deptId, String desId, String empId) throws Exception {
		try
		{
			String sql;
			System.out.println("inside ec dao impl");
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append (" SELECT EMPC_KEYID, EMPM_KEYID, EMPM_CODE, EMPM_NAME,  ");
			buffer.append ("   EMPC_COSTPERHOUR, EMPC_OTCOSTPERHOUR, EMPC_CALLOUTCOSTPERHOUR,TO_CHAR(EMPC_EFFECTIVEFROMDATE,'DD-MON-YYYY') ");
			buffer.append (" FROM  GEN_TL_EMPLOYEECOST , GEN_TL_EMPLOYEEMST ");
			buffer.append (" WHERE EMPC_EMPLOYEEID (+)= EMPM_KEYID  ");
			
			if (! deptId.equals(""))
				buffer.append (" AND EMPM_DEPARTMENTID = '" + deptId + "'  ");
			if (! desId.equals(""))
				buffer.append (" AND EMPM_DESIGNATIONID = '" + desId + "'  ");
			if (! empId.equals(""))
				buffer.append (" AND EMPM_KEYID = '" + empId + "' ");
			
			sql = buffer.toString();
			System.out.println("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public List<String[]> getUtilityCostGridQry(String utilityId) throws Exception {
		try
		{
			String sql;
			System.out.println("inside ec dao impl");			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append (" SELECT EMPC_KEYID, TOLM_KEYID, TOLM_CODE, TOLM_NAME,  ");
			buffer.append ("   EMPC_COSTPERHOUR, NVL(EMPC_OTCOSTPERHOUR,0), NVL(EMPC_CALLOUTCOSTPERHOUR,0),TO_CHAR(EMPC_EFFECTIVEFROMDATE,'DD-MON-YYYY') ");
			buffer.append (" FROM  GEN_TL_EMPLOYEECOST , GEN_TL_TOOLSMST ");
			buffer.append (" WHERE EMPC_EMPLOYEEID (+)= TOLM_KEYID  ");
			
			if (! utilityId.equals(""))
				buffer.append (" AND TOLM_KEYID = '" + utilityId + "'  ");
			
			sql = buffer.toString();
			System.out.println("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public List<String[]> getContractorCostGridQry(String vendorId) throws Exception {
		try
		{
			String sql;
			System.out.println("inside ec dao impl");			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append (" SELECT CNCS_KEYID, UNGM_KEYID, UNGM_CODE,UNGM_NAME,CNCS_COSTPERHOUR,CNCS_OTCOST,  CNCS_HOLIDAYCOST,  ");
			buffer.append ("   TO_CHAR(CNCS_EFFECTIVEFROMDATE,'DD-MON-YYYY') FROM WOM_TL_CONTRACTORMST , PLM_TL_UNSKILLEDGRADEMST_I  ");
			buffer.append (" WHERE UNGM_KEYID=CNCS_UNSKILLEDGRADEID(+) AND CNCS_ACTIVE(+)='Y' ");
			
			if (UIUtils.isValidKeyId(vendorId))
				buffer.append (" AND CNCS_VENDORID(+) = '" + vendorId + "'  ");
			
			sql = buffer.toString();
			System.out.println("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
}