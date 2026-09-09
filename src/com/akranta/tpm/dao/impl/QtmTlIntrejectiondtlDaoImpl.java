package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.QtmTlIntrejectiondtlDao;
import com.akranta.tpm.dao.sql.QtmTlIntrejectiondtlSql;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;

/* dao implementation */
public class QtmTlIntrejectiondtlDaoImpl implements QtmTlIntrejectiondtlDao {


	private DBActionTemplate dbActionTemplate; 

	public QtmTlIntrejectiondtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public QtmTlIntrejectiondtl create(QtmTlIntrejectiondtl qtmTlIntrejectiondtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql = new QtmTlIntrejectiondtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			qtmTlIntrejectiondtl.setQirdKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL)); // set the sequnce number 
			sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlIntrejectiondtl;
	}
	
	public QtmTlIntrejectiondtl update(QtmTlIntrejectiondtl qtmTlIntrejectiondtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql = new QtmTlIntrejectiondtlSql();
		try {

			sqls.add(QtmTlIntrejectiondtlSql.getUpdateSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlIntrejectiondtl;
	}
	
	public QtmTlIntrejectiondtl delete(QtmTlIntrejectiondtl qtmTlIntrejectiondtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql = new QtmTlIntrejectiondtlSql();
		try {
			
			sqls.add(QtmTlIntrejectiondtlSql.getDeleteSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlIntrejectiondtl;
	}
	
}

