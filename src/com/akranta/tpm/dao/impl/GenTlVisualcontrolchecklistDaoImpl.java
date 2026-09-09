package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlVisualcontrolchecklistDao;
import com.akranta.tpm.dao.sql.GenTlVisualcontrolchecklistSql;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;

/* dao implementation */
public class GenTlVisualcontrolchecklistDaoImpl implements GenTlVisualcontrolchecklistDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlVisualcontrolchecklistDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlVisualcontrolchecklist create(GenTlVisualcontrolchecklist genTlVisualcontrolchecklist) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlVisualcontrolchecklist.setVcclKeyid(dbActionTemplate.getSequenceNumber(GenTlVisualcontrolchecklistSql.TBL_GEN_TL_VISUALCONTROLCHECKLIST)); // set the sequnce number 
			sqls.add(GenTlVisualcontrolchecklistSql.getInsertSql(genTlVisualcontrolchecklistSql.getVcclDbFields(), genTlVisualcontrolchecklist.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlVisualcontrolchecklist;
	}
	
	public GenTlVisualcontrolchecklist update(GenTlVisualcontrolchecklist genTlVisualcontrolchecklist)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql();
		try {

			sqls.add(GenTlVisualcontrolchecklistSql.getUpdateSql(genTlVisualcontrolchecklistSql.getVcclDbFields(), genTlVisualcontrolchecklist.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlVisualcontrolchecklist;
	}
	
	public GenTlVisualcontrolchecklist delete(GenTlVisualcontrolchecklist genTlVisualcontrolchecklist)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql();
		try {
			
			sqls.add(genTlVisualcontrolchecklistSql.getDeleteSql(genTlVisualcontrolchecklistSql.getVcclDbFields(), genTlVisualcontrolchecklist.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlVisualcontrolchecklist;
	}
	
}

