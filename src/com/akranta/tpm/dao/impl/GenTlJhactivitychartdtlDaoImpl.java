package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlJhactivitychartdtlDao;
import com.akranta.tpm.dao.sql.GenTlJhactivitychartdtlSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;

/* dao implementation */
public class GenTlJhactivitychartdtlDaoImpl implements GenTlJhactivitychartdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlJhactivitychartdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlJhactivitychartdtl create(GenTlJhactivitychartdtl genTlJhactivitychartdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlJhactivitychartdtl.setJacdKeyid(dbActionTemplate.getSequenceNumber(GenTlJhactivitychartdtlSql.TBL_GEN_TL_JHACTIVITYCHARTDTL)); // set the sequnce number 
			sqls.add(GenTlJhactivitychartdtlSql.getInsertSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlJhactivitychartdtl;
	}
	
	public GenTlJhactivitychartdtl update(GenTlJhactivitychartdtl genTlJhactivitychartdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql();
		try {

			sqls.add(GenTlJhactivitychartdtlSql.getUpdateSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlJhactivitychartdtl;
	}
	
	public GenTlJhactivitychartdtl delete(GenTlJhactivitychartdtl genTlJhactivitychartdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql();
		try {
			
			sqls.add(genTlJhactivitychartdtlSql.getDeleteSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlJhactivitychartdtl;
	}

	@Override
	public GenTlJhactivitychartdtl delete(
			List<GenTlJhactivitychartdtl> jhActDtlList) throws Exception {
		GenTlJhactivitychartdtl newgenTlJhactivitychartdtl =new GenTlJhactivitychartdtl();
		try{
		List<String> sqls = new ArrayList<String>();
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql();
		for(GenTlJhactivitychartdtl genTlJhactivitychartdtl:jhActDtlList){
			sqls.add(GenTlJhactivitychartdtlSql.getDeleteSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray())); // add insert sql for master table
		}
		dbActionTemplate.executeStatements(sqls);
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}
	return newgenTlJhactivitychartdtl;
	}
}

