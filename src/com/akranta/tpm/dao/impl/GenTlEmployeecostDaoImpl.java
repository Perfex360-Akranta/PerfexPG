package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlEmployeecostDao;
import com.akranta.tpm.dao.sql.GenTlEmployeecostSql;
import com.akranta.tpm.model.GenTlEmployeecost;

/* dao implementation */
public class GenTlEmployeecostDaoImpl implements GenTlEmployeecostDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlEmployeecostDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlEmployeecost create(GenTlEmployeecost genTlEmployeecost) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlEmployeecostSql genTlEmployeecostSql = new GenTlEmployeecostSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlEmployeecost.setEmpcKeyid(dbActionTemplate.getSequenceNumber(GenTlEmployeecostSql.TBL_GEN_TL_EMPLOYEECOST)); // set the sequnce number 
			sqls.add(GenTlEmployeecostSql.getInsertSql(genTlEmployeecostSql.getEmpcDbFields(), genTlEmployeecost.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlEmployeecost;
	}
	
	public GenTlEmployeecost update(GenTlEmployeecost genTlEmployeecost)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlEmployeecostSql genTlEmployeecostSql = new GenTlEmployeecostSql();
		try {

			sqls.add(GenTlEmployeecostSql.getUpdateSql(genTlEmployeecostSql.getEmpcDbFields(), genTlEmployeecost.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlEmployeecost;
	}
	
	public GenTlEmployeecost delete(GenTlEmployeecost genTlEmployeecost)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlEmployeecostSql genTlEmployeecostSql = new GenTlEmployeecostSql();
		try {
			
			sqls.add(GenTlEmployeecostSql.getDeleteSql(genTlEmployeecostSql.getEmpcDbFields(), genTlEmployeecost.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlEmployeecost;
	}
	
}

