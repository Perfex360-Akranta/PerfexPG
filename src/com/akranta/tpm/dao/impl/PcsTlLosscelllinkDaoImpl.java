package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlLosscelllinkDao;
import com.akranta.tpm.dao.sql.PcsTlLosscelllinkSql;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class PcsTlLosscelllinkDaoImpl implements PcsTlLosscelllinkDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlLosscelllinkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public PcsTlLosscelllink createJHLink(List<PcsTlLosscelllink> pcsTlLosscelllinkList) 	throws Exception {

		PcsTlLosscelllink pcsTlLosscelllink = new PcsTlLosscelllink();
		
		try { 
			
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 

			for( PcsTlLosscelllink pcsTlLosscelllink1 : pcsTlLosscelllinkList)
			{
				CommonMessage.debugMsg("pcsTlLosscelllink1");
				
				if ( UIUtils.isValidKeyId( pcsTlLosscelllink1.getPlflKeyid()) )
					update(pcsTlLosscelllink1);
				else
					create(pcsTlLosscelllink1);
					
			}
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscelllink;		
	}
	
	public PcsTlLosscelllink create(PcsTlLosscelllink pcsTlLosscelllink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new PcsTlLosscelllinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			pcsTlLosscelllink.setPlflKeyid(dbActionTemplate.getSequenceNumber(PcsTlLosscelllinkSql.TBL_PCS_TL_LOSSCELLLINK, 15, "PLFL", "MMYY", "Y")   ); // set the sequnce number 
			sqls.add(PcsTlLosscelllinkSql.getInsertSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscelllink;
	}
	
	public PcsTlLosscelllink update(PcsTlLosscelllink pcsTlLosscelllink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new PcsTlLosscelllinkSql();
		try {

			sqls.add(PcsTlLosscelllinkSql.getUpdateSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlLosscelllink;
	}
	
	public PcsTlLosscelllink delete(PcsTlLosscelllink pcsTlLosscelllink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new PcsTlLosscelllinkSql();
		try {
			
			sqls.add(pcsTlLosscelllinkSql.getDeleteSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscelllink;
	}
	
}

