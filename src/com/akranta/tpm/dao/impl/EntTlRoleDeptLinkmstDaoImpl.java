/*package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlRoleDeptLinkmstDao;
import com.akranta.tpm.dao.sql.EntTlRoleDeptLinkmstSql;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;

 dao implementation 
public class EntTlRoleDeptLinkmstDaoImpl implements EntTlRoleDeptLinkmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlRoleDeptLinkmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst entTlRoleDeptLinkmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>();  sqls for execution  
		EntTlRoleDeptLinkmstSql entTlRoleDeptLinkmst = new EntTlRoleDeptLinkmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlRoleDeptLinkmst.setErdl_keyid(dbActionTemplate.getSequenceNumber(EntTlRoleDeptLinkmstSql.TBL_ENT_TL_ROLE_DEPT_LINKMST)); // set the sequnce number 
			sqls.add(EntTlRoleDeptLinkmstSql.getInsertSql(entTlRoleDeptLinkmstSql.getErdlDbFields(), entTlRoleDeptLinkmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlRoleDeptLinkmst;
	}
	
	public EntTlRoleDeptLinkmst update(EntTlRoleDeptLinkmst entTlRoleDeptLinkmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlRoleDeptLinkmstSql entTlRoleDeptLinkmstSql = new EntTlRoleDeptLinkmstSql();
		try {

			sqls.add(EntTlRoleDeptLinkmstSql.getUpdateSql(entTlRoleDeptLinkmstSql.getErdlDbFields(), entTlRoleDeptLinkmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlRoleDeptLinkmst;
	}
	
	public EntTlRoleDeptLinkmst delete(EntTlRoleDeptLinkmst entTlRoleDeptLinkmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		//EntTlRoleDeptLinkmstSql entTlRoleDeptLinkmst = new EntTlRoleDeptLinkmstSql();
		try {
			
			sqls.add(EntTlRoleDeptLinkmst.getDeleteSql(ntTlRoleDeptLinkmstSql.getErdlDbFields(), entTlRoleDeptLinkmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlRoleDeptLinkmst;
	}
	
}

*/