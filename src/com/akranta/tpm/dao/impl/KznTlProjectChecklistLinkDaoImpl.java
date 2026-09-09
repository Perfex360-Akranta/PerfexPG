package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.KznTlProjectChecklistLinkDao;
import com.akranta.tpm.dao.sql.KznTlProjectChecklistLinkSql;
import com.akranta.tpm.model.KznTlProjectChecklistLink;

/* dao implementation */
public class KznTlProjectChecklistLinkDaoImpl implements KznTlProjectChecklistLinkDao {


	private DBActionTemplate dbActionTemplate; 

	public KznTlProjectChecklistLinkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void create(List<KznTlProjectChecklistLink> kznTlProjectChecklistLinkList) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlProjectChecklistLinkSql kznTlProjectChecklistLinkSql = new KznTlProjectChecklistLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		GenSequenceNumber seqNoGen = new  GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),KznTlProjectChecklistLinkSql.TBL_KZN_TL_PROJECT_CHECKLIST_LINK,10, "PCL",null,null);
		for(KznTlProjectChecklistLink kznTlProjectChecklistLink: kznTlProjectChecklistLinkList){
			String sql = null;
			if( ! CommonFunctions.isValidKeyId(kznTlProjectChecklistLink.getPcllKeyid())){
				kznTlProjectChecklistLink.setPcllKeyid(seqNoGen.getSequnceNumber()); // set the sequnce number
				sql = KznTlProjectChecklistLinkSql.getInsertSql(kznTlProjectChecklistLinkSql.getPcllDbFields(), kznTlProjectChecklistLink.getSaveArray());
			}	
			else{ 
				sql = KznTlProjectChecklistLinkSql.getUpdateSql(kznTlProjectChecklistLinkSql.getPcllDbFields(), kznTlProjectChecklistLink.getSaveArray());
			}
				
			sqls.add(sql); // add insert sql for master table

		}	
		seqNoGen.closeConnection();
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		
		
	}
	
	public KznTlProjectChecklistLink update(KznTlProjectChecklistLink kznTlProjectChecklistLink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KznTlProjectChecklistLinkSql kznTlProjectChecklistLinkSql = new KznTlProjectChecklistLinkSql();
		sqls.add(KznTlProjectChecklistLinkSql.getUpdateSql(kznTlProjectChecklistLinkSql.getPcllDbFields(), kznTlProjectChecklistLink.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
		
		return kznTlProjectChecklistLink;
	}
	
	
	
}

