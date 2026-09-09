package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.PcsTlLossreasonlinkDao;
import com.akranta.tpm.dao.sql.PcsTlLossreasonlinkSql;
import com.akranta.tpm.model.PcsTlLossreasonlink;

/* dao implementation */
public class PcsTlLossreasonlinkDaoImpl implements PcsTlLossreasonlinkDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlLossreasonlinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlLossreasonlink create(PcsTlLossreasonlink pcsTlLossreasonlink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlLossreasonlinkSql pcsTlLossreasonlinkSql = new PcsTlLossreasonlinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(PcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK)); // set the sequnce number 
			sqls.add(PcsTlLossreasonlinkSql.getInsertSql(pcsTlLossreasonlinkSql.getPlrkDbFields(), pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLossreasonlink;
	}
	
	public PcsTlLossreasonlink update(PcsTlLossreasonlink pcsTlLossreasonlink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlLossreasonlinkSql pcsTlLossreasonlinkSql = new PcsTlLossreasonlinkSql();
		try {

			sqls.add(PcsTlLossreasonlinkSql.getUpdateSql(pcsTlLossreasonlinkSql.getPlrkDbFields(), pcsTlLossreasonlink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlLossreasonlink;
	}
	
	public PcsTlLossreasonlink delete(PcsTlLossreasonlink pcsTlLossreasonlink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlLossreasonlinkSql pcsTlLossreasonlinkSql = new PcsTlLossreasonlinkSql();
		try {
			
			sqls.add(PcsTlLossreasonlinkSql.getDeleteSql(pcsTlLossreasonlinkSql.getPlrkDbFields(), pcsTlLossreasonlink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlLossreasonlink;
	}
	
}

