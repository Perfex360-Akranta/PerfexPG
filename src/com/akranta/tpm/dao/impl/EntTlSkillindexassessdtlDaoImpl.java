package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlSkillindexassessdtlDao;
import com.akranta.tpm.dao.sql.EntTlSkillindexassessdtlSql;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;

/* dao implementation */
public class EntTlSkillindexassessdtlDaoImpl implements EntTlSkillindexassessdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlSkillindexassessdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlSkillindexassessdtl create(EntTlSkillindexassessdtl entTlSkillindexassessdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlSkillindexassessdtl.setSiadKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessdtlSql.TBL_ENT_TL_SKILLINDEXASSESSDTL)); // set the sequnce number 
			sqls.add(EntTlSkillindexassessdtlSql.getInsertSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSkillindexassessdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlSkillindexassessdtl;
	}
	
	public EntTlSkillindexassessdtl update(EntTlSkillindexassessdtl entTlSkillindexassessdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql();
		try {

			sqls.add(EntTlSkillindexassessdtlSql.getUpdateSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSkillindexassessdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlSkillindexassessdtl;
	}
	
	public EntTlSkillindexassessdtl delete(EntTlSkillindexassessdtl entTlSkillindexassessdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql();
		try {
			
			sqls.add(entTlSkillindexassessdtlSql.getDeleteSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSkillindexassessdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlSkillindexassessdtl;
	}
	
}

