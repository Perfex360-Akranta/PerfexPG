package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlServicecostplanDao;
import com.akranta.tpm.dao.sql.BAL_WomTlServicecostplanSql;
import com.akranta.tpm.model.BAL_WomTlServicecostplan;

/* dao implementation */
public class BAL_WomTlServicecostplanDaoImpl implements BAL_WomTlServicecostplanDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlServicecostplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlServicecostplan create(BAL_WomTlServicecostplan womTlServicecostplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlServicecostplanSql womTlServicecostplanSql = new BAL_WomTlServicecostplanSql(); // contains dbtable,field names, Field types and related sqls  of master table

		sqls.add(BAL_WomTlServicecostplanSql.getInsertSql(womTlServicecostplanSql.getSvcpDbFields(), womTlServicecostplan.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		return womTlServicecostplan;
	}
	
	public BAL_WomTlServicecostplan update(BAL_WomTlServicecostplan womTlServicecostplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlServicecostplanSql womTlServicecostplanSql = new BAL_WomTlServicecostplanSql();
		
		sqls.add(BAL_WomTlServicecostplanSql.getUpdateSql(womTlServicecostplanSql.getSvcpDbFields(), womTlServicecostplan.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlServicecostplan;
	}
	
	public BAL_WomTlServicecostplan delete(BAL_WomTlServicecostplan womTlServicecostplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlServicecostplanSql womTlServicecostplanSql = new BAL_WomTlServicecostplanSql();
		try {
			
			sqls.add(BAL_WomTlServicecostplanSql.getDeleteSql(womTlServicecostplanSql.getSvcpDbFields(), womTlServicecostplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlServicecostplan;
	}
	
	/*
	 * public List<String []> getServiceCost(String serviceId) throws Exception { //
	 * TODO Auto-generated method stub
	 * 
	 * String sql=""; StringBuffer buffer = new StringBuffer();
	 * 
	 * buffer.append ("	SELECT  AMVM_CODE, AMVM_NAME,0 AS RATE "); buffer.append (
	 * " FROM GEN_TL_AMCVENDORMST "); buffer.append (
	 * " WHERE AMVM_ACTIVE (+)= 'Y'  AND AMVM_KEYID ='"+serviceId+"'");
	 * 
	 * sql = buffer.toString(); //String empCost
	 * =dbActionTemplate.getSingleValue(sql); List<String[]> serviceDetails =
	 * dbActionTemplate.getDataList(sql);
	 * System.out.println("Inside empcost value"+serviceDetails.size());
	 * 
	 * return serviceDetails; }
	 */	
	public List<String[]> getServiceCost(String serviceId) throws Exception {
	    // TODO Auto-generated method stub

	    String sql = "";
	    StringBuffer buffer = new StringBuffer();

	    buffer.append(" SELECT AMVM_CODE, AMVM_NAME, 0 AS RATE ");
	    buffer.append(" FROM GEN_TL_AMCVENDORMST ");
	    buffer.append(" WHERE AMVM_ACTIVE = 'Y' AND AMVM_KEYID = '" + serviceId + "'");

	    sql = buffer.toString();
	    //String empCost = dbActionTemplate.getSingleValue(sql);
	    List<String[]> serviceDetails = dbActionTemplate.getDataList(sql);
	    System.out.println("Inside empcost value" + serviceDetails.size());

	    return serviceDetails;
	}
}

