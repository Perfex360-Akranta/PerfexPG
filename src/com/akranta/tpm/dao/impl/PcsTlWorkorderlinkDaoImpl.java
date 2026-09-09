package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.PcsTlWorkorderlinkDao;
import com.akranta.tpm.dao.sql.PcsTlWorkorderlinkSql;
import com.akranta.tpm.model.PcsTlWorkorderlink;

/* dao implementation */
public class PcsTlWorkorderlinkDaoImpl implements PcsTlWorkorderlinkDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlWorkorderlinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlWorkorderlink create(PcsTlWorkorderlink pcsTlWorkorderlink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlWorkorderlinkSql pcsTlWorkorderlinkSql = new PcsTlWorkorderlinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			pcsTlWorkorderlink.setPtwoKeyid(dbActionTemplate.getSequenceNumber(PcsTlWorkorderlinkSql.TBL_PCS_TL_WORKORDERLINK)); // set the sequnce number 
			sqls.add(PcsTlWorkorderlinkSql.getInsertSql(pcsTlWorkorderlinkSql.getPtwoDbFields(), pcsTlWorkorderlink.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlWorkorderlink;
	}
	
	public PcsTlWorkorderlink update(PcsTlWorkorderlink pcsTlWorkorderlink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlWorkorderlinkSql pcsTlWorkorderlinkSql = new PcsTlWorkorderlinkSql();
		try {

			sqls.add(PcsTlWorkorderlinkSql.getUpdateSql(pcsTlWorkorderlinkSql.getPtwoDbFields(), pcsTlWorkorderlink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlWorkorderlink;
	}
	
	public PcsTlWorkorderlink delete(PcsTlWorkorderlink pcsTlWorkorderlink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlWorkorderlinkSql pcsTlWorkorderlinkSql = new PcsTlWorkorderlinkSql();
		try {
			
			sqls.add(PcsTlWorkorderlinkSql.getDeleteSql(pcsTlWorkorderlinkSql.getPtwoDbFields(), pcsTlWorkorderlink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlWorkorderlink;
	}
	
}

