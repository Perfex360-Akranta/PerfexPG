package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlTargetgroupmstDao;
import com.akranta.tpm.dao.sql.EntTlTargetgroupmstSql;
import com.akranta.tpm.model.EntTlTargetgroupmst;

/* dao implementation */
public class EntTlTargetgroupmstDaoImpl implements EntTlTargetgroupmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTargetgroupmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTargetgroupmst create(EntTlTargetgroupmst entTlTargetgroupmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTargetgroupmstSql entTlTargetgroupmstSql = new EntTlTargetgroupmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTargetgroupmst.setTgtmKeyid(dbActionTemplate.getSequenceNumber(EntTlTargetgroupmstSql.TBL_ENT_TL_TARGETGROUPMST)); // set the sequnce number 
			sqls.add(EntTlTargetgroupmstSql.getInsertSql(entTlTargetgroupmstSql.getTgtmDbFields(), entTlTargetgroupmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTargetgroupmst;
	}
	
	public EntTlTargetgroupmst update(EntTlTargetgroupmst entTlTargetgroupmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTargetgroupmstSql entTlTargetgroupmstSql = new EntTlTargetgroupmstSql();
		try {

			sqls.add(EntTlTargetgroupmstSql.getUpdateSql(entTlTargetgroupmstSql.getTgtmDbFields(), entTlTargetgroupmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTargetgroupmst;
	}
	
	public EntTlTargetgroupmst delete(EntTlTargetgroupmst entTlTargetgroupmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTargetgroupmstSql entTlTargetgroupmstsql = new EntTlTargetgroupmstSql();
		try {
			
			sqls.add(EntTlTargetgroupmstSql.getDeleteSql(entTlTargetgroupmstsql.getTgtmDbFields(), entTlTargetgroupmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTargetgroupmst;
	}
	
}

