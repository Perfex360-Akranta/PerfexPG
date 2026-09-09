package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlTemponlinetestDao;
import com.akranta.tpm.dao.sql.EntTlTemponlinetestSql;
import com.akranta.tpm.model.EntTlTemponlinetest;

/* dao implementation */
public class EntTlTemponlinetestDaoImpl implements EntTlTemponlinetestDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTemponlinetestDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTemponlinetest create(EntTlTemponlinetest entTlTemponlinetest) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTemponlinetestSql entTlTemponlinetestSql = new EntTlTemponlinetestSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTemponlinetest.setTemtKeyid(dbActionTemplate.getSequenceNumber(EntTlTemponlinetestSql.TBL_ENT_TL_TEMPONLINETEST,10,"TEMT","","Y")); // set the sequnce number 
			sqls.add(EntTlTemponlinetestSql.getInsertSql(entTlTemponlinetestSql.getTemtDbFields(), entTlTemponlinetest.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTemponlinetest;
	}
	
	public EntTlTemponlinetest update(EntTlTemponlinetest entTlTemponlinetest)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTemponlinetestSql entTlTemponlinetestSql = new EntTlTemponlinetestSql();
		try {

			sqls.add(EntTlTemponlinetestSql.getUpdateSql(entTlTemponlinetestSql.getTemtDbFields(), entTlTemponlinetest.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTemponlinetest;
	}
	
	public EntTlTemponlinetest delete(EntTlTemponlinetest entTlTemponlinetest)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTemponlinetestSql entTlTemponlinetestSql = new EntTlTemponlinetestSql();
		try {
			
			sqls.add(entTlTemponlinetestSql.getDeleteSql(entTlTemponlinetestSql.getTemtDbFields(), entTlTemponlinetest.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTemponlinetest;
	}
	
}

