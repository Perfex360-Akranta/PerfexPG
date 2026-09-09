package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.PcsTlDtlDao;
import com.akranta.tpm.dao.sql.PcsTlDtlSql;
import com.akranta.tpm.model.PcsTlDtl;

/* dao implementation */
public class PcsTlDtlDaoImpl implements PcsTlDtlDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlDtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlDtl create(PcsTlDtl pcsTlDtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlDtlSql pcsTlDtlSql = new PcsTlDtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
		//	pcsTlDtl.setPcs_Keyid(dbActionTemplate.getSequenceNumber(PcsTlDtlSql.TBL_PCS_TL_DTL)); // set the sequnce number 
		//	sqls.add(PcsTlDtlSql.getInsertSql(pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlDtl;
	}
	
	public PcsTlDtl update(PcsTlDtl pcsTlDtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlDtlSql pcsTlDtlSql = new PcsTlDtlSql();
		try {

		//	sqls.add(PcsTlDtlSql.getUpdateSql(pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlDtl;
	}
	
	public PcsTlDtl delete(PcsTlDtl pcsTlDtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlDtlSql pcsTlDtlSql = new PcsTlDtlSql();
		try {
			
			sqls.add(PcsTlDtlSql.getDeleteSql(pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlDtl;
	}
	
}

