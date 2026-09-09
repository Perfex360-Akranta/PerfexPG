package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EmployeeCostDao;
import com.akranta.tpm.utils.CommonMessage;
/*Employee dao implementation */
public class EmployeeCostDaoImpl implements EmployeeCostDao {
	
	private DBActionTemplate dbActionTemplate; 
	
	public EmployeeCostDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String []> getEmpDeptDes(String empId) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside empcost daoimpl"+empId);		
		String sql="";		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append (" SELECT EMPM_DEPARTMENTID, EMPM_DESIGNATIONID ");
		buffer.append (" FROM GEN_TL_EMPLOYEEMST");
		buffer.append (" WHERE EMPM_KEYID ='"+empId+"' ");
		
		sql = buffer.toString();
		//String empCost =dbActionTemplate.getSingleValue(sql);
		List<String[]> empDetails = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("Inside empcost value"+empDetails.size());
		
		return empDetails;
	}	
	
	public List<String[]> getEmployeeCostGridQry(String deptId, String desId, String empId) throws Exception {
		try
		{
			String sql;
			CommonMessage.debugMsg("inside ec dao impl");
			
			StringBuffer buffer = new StringBuffer();
			
		
			
			buffer.append (" SELECT  C.EMPC_KEYID, E.EMPM_KEYID, E.EMPM_CODE, E.EMPM_NAME, C.EMPC_COSTPERHOUR, C.EMPC_OTCOSTPERHOUR, C.EMPC_CALLOUTCOSTPERHOUR, ");
			buffer.append (" TO_CHAR(C.EMPC_EFFECTIVEFROMDATE, 'DD-MON-YYYY') AS EMPC_EFFECTIVEFROMDATE FROM GEN_TL_EMPLOYEEMST E LEFT JOIN GEN_TL_EMPLOYEECOST C ");
			buffer.append (" ON C.EMPC_EMPLOYEEID = E.EMPM_KEYID WHERE 1=1 ");
			
			if (! deptId.equals(""))
				buffer.append (" AND EMPM_DEPARTMENTID = '" + deptId + "'  ");
			if (! desId.equals(""))
				buffer.append (" AND EMPM_DESIGNATIONID = '" + desId + "'  ");
			if (! empId.equals(""))
				buffer.append (" AND EMPM_KEYID = '" + empId + "' ");
			
			sql = buffer.toString();
			CommonMessage.debugMsg("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public List<String[]> getUtilityCostGridQry(String utilityId) throws Exception {
		try
		{
			String sql;
			CommonMessage.debugMsg("inside ec dao impl");			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append (" SELECT EMPC_KEYID, TOLM_KEYID, TOLM_CODE, TOLM_NAME,  ");
			buffer.append ("   EMPC_COSTPERHOUR, NVL(EMPC_OTCOSTPERHOUR,0), NVL(EMPC_CALLOUTCOSTPERHOUR,0),TO_CHAR(EMPC_EFFECTIVEFROMDATE,'DD-MON-YYYY') ");
			buffer.append (" FROM  GEN_TL_EMPLOYEECOST LEFT JOIN  GEN_TL_TOOLSMST  ON EMPC_EMPLOYEEID= TOLM_KEYID WHERE 1=1 ");
		//	buffer.append (" WHERE EMPC_EMPLOYEEID (+)= TOLM_KEYID  ");
			
			if (! utilityId.equals(""))
				buffer.append (" AND TOLM_KEYID = '" + utilityId + "'  ");
			
			sql = buffer.toString();
			CommonMessage.debugMsg("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public List<String[]> getContractorCostGridQry(String vendorId) throws Exception {
		try
		{
			String sql;
			CommonMessage.debugMsg("inside ec dao impl");			
			StringBuffer buffer = new StringBuffer();
			/*
			buffer.append (" SELECT CNCS_KEYID, UNGM_KEYID, UNGM_CODE,UNGM_NAME,CNCS_COSTPERHOUR,CNCS_OTCOST,  CNCS_HOLIDAYCOST,  ");
			buffer.append ("   TO_CHAR(CNCS_EFFECTIVEFROMDATE,'DD-MON-YYYY') FROM WOM_TL_CONTRACTORMST , PLM_TL_UNSKILLEDGRADEMST_I  ");
			buffer.append (" WHERE UNGM_KEYID=CNCS_UNSKILLEDGRADEID(+) AND CNCS_ACTIVE(+)='Y' ");
			*/
			buffer.append (" SELECT C.CNCS_KEYID, U.UNGM_KEYID, U.UNGM_CODE, U.UNGM_NAME, C.CNCS_COSTPERHOUR, ");
			buffer.append ("    C.CNCS_OTCOST, C.CNCS_HOLIDAYCOST, TO_CHAR(C.CNCS_EFFECTIVEFROMDATE, 'DD-MON-YYYY') AS CNCS_EFFECTIVEFROMDATE ");
			buffer.append ("	FROM PLM_TL_UNSKILLEDGRADEMST_I U LEFT JOIN WOM_TL_CONTRACTORMST C ");
			buffer.append ("    ON U.UNGM_KEYID = C.CNCS_UNSKILLEDGRADEID  AND C.CNCS_ACTIVE = 'Y'   ");

			
			if (UIUtils.isValidKeyId(vendorId))
				buffer.append (" AND C.CNCS_VENDORID ='" + vendorId + "'  ");
			
			sql = buffer.toString();
			CommonMessage.debugMsg("sql: "+sql);			
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }
		return null;	
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
}