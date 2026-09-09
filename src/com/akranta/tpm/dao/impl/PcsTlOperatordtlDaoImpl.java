package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.PcsTlOperatordtlDao;
import com.akranta.tpm.dao.sql.PcsTlOperatordtlSql;
import com.akranta.tpm.model.PcsTlOperatordtl;

/* dao implementation */
public class PcsTlOperatordtlDaoImpl implements PcsTlOperatordtlDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlOperatordtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlOperatordtl create(PcsTlOperatordtl pcsTlOperatordtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlOperatordtlSql pcsTlOperatordtlSql = new PcsTlOperatordtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			//pcsTlOperatordtl.setPopdKeyid(dbActionTemplate.getSequenceNumber(PcsTlOperatordtlSql.TBL_PCS_TL_OPERATORDTL)); // set the sequnce number 
			sqls.add(PcsTlOperatordtlSql.getInsertSql(pcsTlOperatordtlSql.getPopdDbFields(), pcsTlOperatordtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlOperatordtl;
	}
	
	public PcsTlOperatordtl update(PcsTlOperatordtl pcsTlOperatordtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlOperatordtlSql pcsTlOperatordtlSql = new PcsTlOperatordtlSql();
		try {

			sqls.add(PcsTlOperatordtlSql.getUpdateSql(pcsTlOperatordtlSql.getPopdDbFields(), pcsTlOperatordtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlOperatordtl;
	}
	
	public PcsTlOperatordtl delete(PcsTlOperatordtl pcsTlOperatordtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlOperatordtlSql pcsTlOperatordtlSql = new PcsTlOperatordtlSql();
		try {
			
			sqls.add(PcsTlOperatordtlSql.getDeleteSql(pcsTlOperatordtlSql.getPopdDbFields(), pcsTlOperatordtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlOperatordtl;
	}
	
}

