package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_BdmTlNewphncausereqDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlNewphncausereqSql;
import com.akranta.tpm.model.BAL_BdmTlNewphncausereq;

/* dao implementation */
public class BAL_BdmTlNewphncausereqDaoImpl implements BAL_BdmTlNewphncausereqDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_BdmTlNewphncausereqDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_BdmTlNewphncausereq create(BAL_BdmTlNewphncausereq bdmTlNewphncausereq) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_BdmTlNewphncausereqSql bdmTlNewphncausereqSql = new BAL_BdmTlNewphncausereqSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			bdmTlNewphncausereq.setBnprKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlNewphncausereqSql.TBL_BDM_TL_NEWPHNCAUSEREQ)); // set the sequnce number 
			sqls.add(BAL_BdmTlNewphncausereqSql.getInsertSql(bdmTlNewphncausereqSql.getBnprDbFields(), bdmTlNewphncausereq.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return bdmTlNewphncausereq;
	}
	
	public BAL_BdmTlNewphncausereq update(BAL_BdmTlNewphncausereq bdmTlNewphncausereq)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlNewphncausereqSql bdmTlNewphncausereqSql = new BAL_BdmTlNewphncausereqSql();
		try {

			sqls.add(BAL_BdmTlNewphncausereqSql.getUpdateSql(bdmTlNewphncausereqSql.getBnprDbFields(), bdmTlNewphncausereq.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlNewphncausereq;
	}
	
	public BAL_BdmTlNewphncausereq delete(BAL_BdmTlNewphncausereq bdmTlNewphncausereq)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlNewphncausereqSql bdmTlNewphncausereqSql = new BAL_BdmTlNewphncausereqSql();
		try {
			
			sqls.add(BAL_BdmTlNewphncausereqSql.getDeleteSql(bdmTlNewphncausereqSql.getBnprDbFields(), bdmTlNewphncausereq.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlNewphncausereq;
	}
	
}

