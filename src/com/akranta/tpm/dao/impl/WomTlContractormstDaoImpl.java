package com.akranta.tpm.dao.impl;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.WomTlContractormstDao;
import com.akranta.tpm.dao.sql.WomTlContractormstSql;
import com.akranta.tpm.model.WomTlContractormst;

/* dao implementation */
public class WomTlContractormstDaoImpl implements WomTlContractormstDao {


	private DBActionTemplate dbActionTemplate; 

	public WomTlContractormstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public WomTlContractormst create(WomTlContractormst womTlContractormst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		WomTlContractormstSql womTlContractormstSql = new WomTlContractormstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			womTlContractormst.setCncsKeyid(dbActionTemplate.getSequenceNumber(WomTlContractormstSql.TBL_WOM_TL_CONTRACTORMST)); // set the sequnce number 
			sqls.add(WomTlContractormstSql.getInsertSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return womTlContractormst;
	}
	
	public WomTlContractormst update(WomTlContractormst womTlContractormst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		WomTlContractormstSql womTlContractormstSql = new WomTlContractormstSql();
		try {

			sqls.add(WomTlContractormstSql.getUpdateSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return womTlContractormst;
	}
	
	public WomTlContractormst delete(WomTlContractormst womTlContractormst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		WomTlContractormstSql womTlContractormstSql = new WomTlContractormstSql();
		try {
			
			sqls.add(WomTlContractormstSql.getDeleteSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlContractormst;
	}
	
}

