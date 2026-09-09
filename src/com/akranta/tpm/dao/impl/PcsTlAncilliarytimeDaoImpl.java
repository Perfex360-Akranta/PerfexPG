package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.PcsTlAncilliarytimeDao;
import com.akranta.tpm.dao.sql.PcsTlAncilliarytimeSql;
import com.akranta.tpm.model.PcsTlAncilliarytime;

/* dao implementation */
public class PcsTlAncilliarytimeDaoImpl implements PcsTlAncilliarytimeDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlAncilliarytimeDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlAncilliarytime create(PcsTlAncilliarytime pcsTlAncilliarytime) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlAncilliarytimeSql pcsTlAncilliarytimeSql = new PcsTlAncilliarytimeSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			pcsTlAncilliarytime.setPtatKeyid(dbActionTemplate.getSequenceNumber(PcsTlAncilliarytimeSql.TBL_PCS_TL_ANCILLIARYTIME)); // set the sequnce number 
			sqls.add(PcsTlAncilliarytimeSql.getInsertSql(pcsTlAncilliarytimeSql.getPtatDbFields(), pcsTlAncilliarytime.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlAncilliarytime;
	}
	
	public PcsTlAncilliarytime update(PcsTlAncilliarytime pcsTlAncilliarytime)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlAncilliarytimeSql pcsTlAncilliarytimeSql = new PcsTlAncilliarytimeSql();
		try {

			sqls.add(PcsTlAncilliarytimeSql.getUpdateSql(pcsTlAncilliarytimeSql.getPtatDbFields(), pcsTlAncilliarytime.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlAncilliarytime;
	}
	
	public PcsTlAncilliarytime delete(PcsTlAncilliarytime pcsTlAncilliarytime)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlAncilliarytimeSql pcsTlAncilliarytimeSql = new PcsTlAncilliarytimeSql();
		try {
			
			sqls.add(PcsTlAncilliarytimeSql.getDeleteSql(pcsTlAncilliarytimeSql.getPtatDbFields(), pcsTlAncilliarytime.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlAncilliarytime;
	}
	
}

