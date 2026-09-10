package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlManpowercostplanDao;
import com.akranta.tpm.dao.sql.BAL_WomTlManpowercostplanSql;
import com.akranta.tpm.dao.sql.BAL_WomTlOthercostactualSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlManpowercostplan;

/* dao implementation */
public class BAL_WomTlManpowercostplanDaoImpl implements BAL_WomTlManpowercostplanDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlManpowercostplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlManpowercostplan create(BAL_WomTlManpowercostplan womTlManpowercostplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlManpowercostplanSql womTlManpowercostplanSql = new BAL_WomTlManpowercostplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		sqls.add(BAL_WomTlManpowercostplanSql.getInsertSql(womTlManpowercostplanSql.getMpcpDbFields(), womTlManpowercostplan.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlManpowercostplan;
	}
	
	public BAL_WomTlManpowercostplan update(BAL_WomTlManpowercostplan womTlManpowercostplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlManpowercostplanSql womTlManpowercostplanSql = new BAL_WomTlManpowercostplanSql();
		
		sqls.add(BAL_WomTlManpowercostplanSql.getUpdateSql(womTlManpowercostplanSql.getMpcpDbFields(), womTlManpowercostplan.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
		return womTlManpowercostplan;
	}
	
	public BAL_WomTlManpowercostplan delete(BAL_WomTlManpowercostplan womTlManpowercostplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlManpowercostplanSql womTlManpowercostplanSql = new BAL_WomTlManpowercostplanSql();
		try {
			
			sqls.add(BAL_WomTlManpowercostplanSql.getDeleteSql(womTlManpowercostplanSql.getMpcpDbFields(), womTlManpowercostplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlManpowercostplan;
	}

	@Override
	public BAL_WomTlManpowercostplan select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//mano has commented 
	/*
	 * public List<String []> getEmpCost(String empId) throws Exception { // TODO
	 * Auto-generated method stub
	 * System.out.println("Inside empcost daoimpl"+empId);
	 * 
	 * String sql=""; StringBuffer buffer = new StringBuffer();
	 * 
	 * buffer.append
	 * (" SELECT EMPM_EMPLOYEENUMBER, EMPM_NAME , EMPC_COSTPERHOUR ,EMPC_OTCOSTPERHOUR ,EMPC_CALLOUTCOSTPERHOUR "
	 * ); buffer.append (" FROM GEN_TL_EMPLOYEECOST, GEN_TL_EMPLOYEEMST");
	 * buffer.append (" WHERE  EMPC_EMPLOYEEID (+) = EMPM_KEYID "); buffer.append
	 * (" AND EMPC_ACTIVE(+) ='Y' AND EMPM_KEYID ='"+empId+"' ");
	 * 
	 * sql = buffer.toString(); //String empCost
	 * =dbActionTemplate.getSingleValue(sql); List<String[]> empDetails =
	 * dbActionTemplate.getDataList(sql);
	 * System.out.println("Inside empcost value"+empDetails.size());
	 * 
	 * return empDetails; }
	 */
	public List<String[]> getEmpCost(String empId) throws Exception {
	    // TODO Auto-generated method stub
	    System.out.println("Inside empcost daoimpl" + empId);

	    String sql = "";
	    StringBuffer buffer = new StringBuffer();

	    buffer.append(" SELECT EMPM_EMPLOYEENUMBER, EMPM_NAME, EMPC_COSTPERHOUR, EMPC_OTCOSTPERHOUR, EMPC_CALLOUTCOSTPERHOUR ");
	    buffer.append(" FROM GEN_TL_EMPLOYEEMST ");
	    buffer.append(" LEFT JOIN GEN_TL_EMPLOYEECOST ON EMPC_EMPLOYEEID = EMPM_KEYID AND EMPC_ACTIVE = 'Y' ");
	    buffer.append(" WHERE EMPM_KEYID = '" + empId + "' ");

	    sql = buffer.toString();
	    //String empCost = dbActionTemplate.getSingleValue(sql);
	    List<String[]> empDetails = dbActionTemplate.getDataList(sql);
	    System.out.println("Inside empcost value" + empDetails.size());

	    return empDetails;
	}
	
	/*
	 * public List<String []> getContractorCost(String empId) throws Exception { //
	 * TODO Auto-generated method stub
	 * System.out.println("Inside Contracotcost daoimpl"+empId);
	 * 
	 * String sql=""; StringBuffer buffer = new StringBuffer();
	 * 
	 * buffer.append
	 * (" SELECT UNGM_CODE,UNGM_NAME, CNCS_COSTPERHOUR, CNCS_OTCOST, CNCS_HOLIDAYCOST  "
	 * ); buffer.append (" From WOM_TL_CONTRACTORMST, PLM_TL_UNSKILLEDGRADEMST_I ");
	 * buffer.append (" WHERE CNCS_UNSKILLEDGRADEID (+)= UNGM_KEYID" );
	 * buffer.append (" AND CNCS_ACTIVE (+)= 'Y'   AND UNGM_KEYID ='"+empId+"' ");
	 * 
	 * //String empCost =dbActionTemplate.getSingleValue(sql); sql =
	 * buffer.toString(); List<String[]> empDetails =
	 * dbActionTemplate.getDataList(sql);
	 * System.out.println("Inside empcost value"+empDetails.size());
	 * 
	 * return empDetails; }
	 */
	
	public List<String []> getContractorCost(String empId) throws Exception {
	    System.out.println("Inside Contracotcost daoimpl" + empId);

	    String sql = "";
	    StringBuffer buffer = new StringBuffer();

	    buffer.append(" SELECT UNGM_CODE, UNGM_NAME, CNCS_COSTPERHOUR, CNCS_OTCOST, CNCS_HOLIDAYCOST ");
	    buffer.append(" FROM PLM_TL_UNSKILLEDGRADEMST_I ");
	    buffer.append(" LEFT JOIN WOM_TL_CONTRACTORMST ");
	    buffer.append("   ON CNCS_UNSKILLEDGRADEID = UNGM_KEYID ");
	    buffer.append("   AND CNCS_ACTIVE = 'Y' ");
	    buffer.append(" WHERE UNGM_KEYID = '" + empId + "' ");

	    sql = buffer.toString();
	    List<String[]> empDetails = dbActionTemplate.getDataList(sql);
	    System.out.println("Inside empcost value" + empDetails.size());

	    return empDetails;
	}
	
}

