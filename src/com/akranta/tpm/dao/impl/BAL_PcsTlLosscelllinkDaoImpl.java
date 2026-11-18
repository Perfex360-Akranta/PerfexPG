package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlLosscelllinkDao;
import com.akranta.tpm.dao.sql.BAL_PcsTlLosscelllinkSql;
import com.akranta.tpm.model.BAL_PcsTlLosscelllink;
import com.akranta.tpm.model.BAL_PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLosscelllink;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PcsTlLosscelllinkDaoImpl implements BAL_PcsTlLosscelllinkDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_PcsTlLosscelllinkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public BAL_PcsTlLosscelllink createJHLink(List<BAL_PcsTlLosscelllink> pcsTlLosscelllinkList) 	throws Exception {

		BAL_PcsTlLosscelllink pcsTlLosscelllink = new BAL_PcsTlLosscelllink();
		
		try { 
			
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 

			for( BAL_PcsTlLosscelllink pcsTlLosscelllink1 : pcsTlLosscelllinkList)
			{
				CommonFunctions.debugMsg("pcsTlLosscelllink1");
				
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
	
	public BAL_PcsTlLosscelllink create(BAL_PcsTlLosscelllink pcsTlLosscelllink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new BAL_PcsTlLosscelllinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			pcsTlLosscelllink.setPlflKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlLosscelllinkSql.TBL_PCS_TL_LOSSCELLLINK, 15, "PLFL", "MMYY", "Y")   ); // set the sequnce number 
			sqls.add(BAL_PcsTlLosscelllinkSql.getInsertSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscelllink;
	}
	
	public BAL_PcsTlLosscelllink update(BAL_PcsTlLosscelllink pcsTlLosscelllink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new BAL_PcsTlLosscelllinkSql();
		try {

			sqls.add(BAL_PcsTlLosscelllinkSql.getUpdateSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlLosscelllink;
	}
	
	public BAL_PcsTlLosscelllink delete(BAL_PcsTlLosscelllink pcsTlLosscelllink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlLosscelllinkSql pcsTlLosscelllinkSql = new BAL_PcsTlLosscelllinkSql();
		try {
			
			sqls.add(pcsTlLosscelllinkSql.getDeleteSql(pcsTlLosscelllinkSql.getPlflDbFields(), pcsTlLosscelllink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscelllink;
	}

	@Override
	public PcsTlLosscelllink create(PcsTlLosscelllink pcsTlLosscelllink) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PcsTlLosscelllink update(PcsTlLosscelllink pcsTlLosscelllink) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PcsTlLosscelllink delete(PcsTlLosscelllink pcsTlLosscelllink) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}

