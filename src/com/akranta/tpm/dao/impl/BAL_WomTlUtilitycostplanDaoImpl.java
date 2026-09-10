package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_WomTlUtilitycostplanDao;
import com.akranta.tpm.dao.sql.BAL_WomTlUtilitycostplanSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlUtilitycostplan;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BAL_WomTlUtilitycostplanDaoImpl implements BAL_WomTlUtilitycostplanDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlUtilitycostplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlUtilitycostplan create(BAL_WomTlUtilitycostplan womTlUtilitycostplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlUtilitycostplanSql womTlUtilitycostplanSql = new BAL_WomTlUtilitycostplanSql(); // contains dbtable,field names, Field types and related sqls  of master table

		sqls.add(BAL_WomTlUtilitycostplanSql.getInsertSql(womTlUtilitycostplanSql.getUtcpDbFields(), womTlUtilitycostplan.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		return womTlUtilitycostplan;
	}
	
	public BAL_WomTlUtilitycostplan update(BAL_WomTlUtilitycostplan womTlUtilitycostplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlUtilitycostplanSql womTlUtilitycostplanSql = new BAL_WomTlUtilitycostplanSql();
		
		sqls.add(BAL_WomTlUtilitycostplanSql.getUpdateSql(womTlUtilitycostplanSql.getUtcpDbFields(), womTlUtilitycostplan.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlUtilitycostplan;
	}
	
	public BAL_WomTlUtilitycostplan delete(BAL_WomTlUtilitycostplan womTlUtilitycostplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlUtilitycostplanSql womTlUtilitycostplanSql = new BAL_WomTlUtilitycostplanSql();
		try {
			
			sqls.add(BAL_WomTlUtilitycostplanSql.getDeleteSql(womTlUtilitycostplanSql.getUtcpDbFields(), womTlUtilitycostplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlUtilitycostplan;
	}

	/*
	 * public List<String []> getUtilityCost(String utilityId, String effDate)
	 * throws Exception { // TODO Auto-generated method stub
	 * 
	 * String sql=""; StringBuffer buffer = new StringBuffer();
	 * 
	 * //buffer.append ("	SELECT  TOLM_CODE, TOLM_NAME,0 AS RATE  ");
	 * //buffer.append ( " FROM GEN_TL_TOOLSMST"); //buffer.append (
	 * " WHERE TOLM_ACTIVE (+)= 'Y'  AND TOLM_KEYID ='"+utilityId+"' ");
	 * 
	 * buffer.append (" SELECT TOLM_CODE, TOLM_NAME, SUM(RATE) AS RATE ");
	 * buffer.append ("		FROM ( "); buffer.append
	 * (" SELECT  TOLM_CODE, TOLM_NAME,0 AS RATE "); buffer.append
	 * (" FROM GEN_TL_TOOLSMST "); buffer.append (" WHERE TOLM_ACTIVE (+)= 'Y' ");
	 * buffer.append (" AND TOLM_KEYID='"+utilityId+"' "); buffer.append
	 * (" UNION ALL "); buffer.append
	 * (" select TOLM_CODE, TOLM_NAME,EMPC_COSTPERHOUR AS RATE "); buffer.append
	 * (" from GEN_TL_EMPLOYEECOST ,  GEN_TL_TOOLSMST "); buffer.append
	 * (" WHERE EMPC_TYPE = 'U'  AND EMPC_ACTIVE='Y' "); buffer.append (" AND '"
	 * +effDate+"' BETWEEN EMPC_EFFECTIVEFROMDATE AND EMPC_EFFECTIVETILLDATE ");
	 * buffer.append (" AND TOLM_KEYID='"+utilityId+"' "); buffer.append
	 * (" AND EMPC_EMPLOYEEID = TOLM_KEYID "); buffer.append
	 * (" ) GROUP BY TOLM_CODE, TOLM_NAME ");
	 * 
	 * sql = buffer.toString(); System.out.println("Inside empcostsql"+sql);
	 * List<String[]> utilityDetails = dbActionTemplate.getDataList(sql);
	 * System.out.println("Inside empcost value"+utilityDetails.size());
	 * 
	 * return utilityDetails; }
	 */
	
	//mano
	public List<String[]> getUtilityCost(String utilityId, String effDate) throws Exception {
	    // TODO Auto-generated method stub

	    String sql = "";
	    StringBuffer buffer = new StringBuffer();

	    //buffer.append ("	SELECT  TOLM_CODE, TOLM_NAME,0 AS RATE  ");
	    //buffer.append ( " FROM GEN_TL_TOOLSMST");
	    //buffer.append ( " WHERE TOLM_ACTIVE (+)= 'Y'  AND TOLM_KEYID ='"+utilityId+"' ");

	    buffer.append(" SELECT TOLM_CODE, TOLM_NAME, SUM(RATE) AS RATE ");
	    buffer.append(" FROM ( ");
	    buffer.append(" SELECT TOLM_CODE, TOLM_NAME, 0 AS RATE ");
	    buffer.append(" FROM GEN_TL_TOOLSMST ");
	    buffer.append(" WHERE TOLM_ACTIVE = 'Y' ");
	    buffer.append(" AND TOLM_KEYID = '" + utilityId + "' ");
	    buffer.append(" UNION ALL ");
	    buffer.append(" SELECT TOLM_CODE, TOLM_NAME, EMPC_COSTPERHOUR AS RATE ");
	    buffer.append(" FROM GEN_TL_EMPLOYEECOST, GEN_TL_TOOLSMST ");
	    buffer.append(" WHERE EMPC_TYPE = 'U' AND EMPC_ACTIVE = 'Y' ");
	    buffer.append(" AND '" + effDate + "'::date BETWEEN EMPC_EFFECTIVEFROMDATE AND EMPC_EFFECTIVETILLDATE ");
	    buffer.append(" AND TOLM_KEYID = '" + utilityId + "' ");
	    buffer.append(" AND EMPC_EMPLOYEEID = TOLM_KEYID ");
	    buffer.append(" ) t GROUP BY TOLM_CODE, TOLM_NAME ");

	    sql = buffer.toString();
	    System.out.println("Inside empcostsql" + sql);
	    List<String[]> utilityDetails = dbActionTemplate.getDataList(sql);
	    System.out.println("Inside empcost value" + utilityDetails.size());

	    return utilityDetails;
	}
}

