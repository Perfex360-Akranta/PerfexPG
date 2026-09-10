package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlSparecostplanDao;
import com.akranta.tpm.dao.sql.BAL_WomTlSparecostplanSql;
import com.akranta.tpm.model.BAL_WomTlSparecostplan;

/* dao implementation */
public class BAL_WomTlSparecostplanDaoImpl implements BAL_WomTlSparecostplanDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlSparecostplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlSparecostplan create(BAL_WomTlSparecostplan womTlSparecostplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlSparecostplanSql womTlSparecostplanSql = new BAL_WomTlSparecostplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		sqls.add(BAL_WomTlSparecostplanSql.getInsertSql(womTlSparecostplanSql.getWscpDbFields(), womTlSparecostplan.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlSparecostplan;
	}
	
	public BAL_WomTlSparecostplan update(BAL_WomTlSparecostplan womTlSparecostplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlSparecostplanSql womTlSparecostplanSql = new BAL_WomTlSparecostplanSql();
		
		sqls.add(BAL_WomTlSparecostplanSql.getUpdateSql(womTlSparecostplanSql.getWscpDbFields(), womTlSparecostplan.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlSparecostplan;
	}
	
	public BAL_WomTlSparecostplan delete(BAL_WomTlSparecostplan womTlSparecostplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlSparecostplanSql womTlSparecostplanSql = new BAL_WomTlSparecostplanSql();
		try {			
			sqls.add(BAL_WomTlSparecostplanSql.getDeleteSql(womTlSparecostplanSql.getWscpDbFields(), womTlSparecostplan.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlSparecostplan;
	}

	/*
	 * public List<String []> getSpareCost(String spareId) throws Exception { //
	 * TODO Auto-generated method stub
	 * System.out.println("Inside Contracotcost daoimpl"+spareId);
	 * 
	 * String sql=""; StringBuffer buffer = new StringBuffer();
	 * 
	 * buffer.append
	 * ("	SELECT  SPRM_PARTNO, SPRM_PARTNAME,NVL(ITCS_UNITRATE,0) AS RATE  ");
	 * buffer.append (" FROM GEN_TL_SPARESMST , MTM_TL_ITEMCURRENTSTOCK  ");
	 * buffer.append (" WHERE  ITCS_SPAREID(+)=SPRM_KEYID "); buffer.append
	 * (" AND SPRM_ACTIVE (+)= 'Y' AND SPRM_KEYID ='"+spareId+"' ");
	 * 
	 * sql = buffer.toString(); //String empCost
	 * =dbActionTemplate.getSingleValue(sql); List<String[]> spareDetails =
	 * dbActionTemplate.getDataList(sql);
	 * System.out.println("Inside empcost value"+spareDetails.size());
	 * 
	 * return spareDetails; }
	 */
	public List<String[]> getSpareCost(String spareId) throws Exception {
	    // TODO Auto-generated method stub
	    System.out.println("Inside Contracotcost daoimpl" + spareId);

	    String sql = "";
	    StringBuffer buffer = new StringBuffer();

	    buffer.append(" SELECT SPRM_PARTNO, SPRM_PARTNAME, COALESCE(ITCS_UNITRATE,0) AS RATE ");
	    buffer.append(" FROM GEN_TL_SPARESMST ");
	    buffer.append(" LEFT JOIN MTM_TL_ITEMCURRENTSTOCK ON ITCS_SPAREID = SPRM_KEYID ");
	    buffer.append(" WHERE SPRM_ACTIVE = 'Y' AND SPRM_KEYID = '" + spareId + "' ");

	    sql = buffer.toString();
	    //String empCost = dbActionTemplate.getSingleValue(sql);
	    List<String[]> spareDetails = dbActionTemplate.getDataList(sql);
	    System.out.println("Inside empcost value" + spareDetails.size());

	    return spareDetails;
	}
}

