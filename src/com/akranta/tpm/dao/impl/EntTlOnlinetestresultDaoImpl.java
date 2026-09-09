package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlOnlinetestresultDao;
import com.akranta.tpm.dao.sql.EntTlOnlinetestresultSql;
import com.akranta.tpm.model.EntTlOnlinetestresult;

/* dao implementation */
public class EntTlOnlinetestresultDaoImpl implements EntTlOnlinetestresultDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlOnlinetestresultDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlOnlinetestresult create(EntTlOnlinetestresult entTlOnlinetestresult) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlOnlinetestresultSql entTlOnlinetestresultSql = new EntTlOnlinetestresultSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlOnlinetestresult.setOtrsKeyid(dbActionTemplate.getSequenceNumber(EntTlOnlinetestresultSql.TBL_ENT_TL_ONLINETESTRESULT,10,"OTRS","","Y")); // set the sequnce number 
			sqls.add(EntTlOnlinetestresultSql.getInsertSql(entTlOnlinetestresultSql.getOtrsDbFields(), entTlOnlinetestresult.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlOnlinetestresult;
	}
	
	public EntTlOnlinetestresult update(EntTlOnlinetestresult entTlOnlinetestresult)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlOnlinetestresultSql entTlOnlinetestresultSql = new EntTlOnlinetestresultSql();
		try {

			sqls.add(EntTlOnlinetestresultSql.getUpdateSql(entTlOnlinetestresultSql.getOtrsDbFields(), entTlOnlinetestresult.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlOnlinetestresult;
	}
	
	public EntTlOnlinetestresult delete(EntTlOnlinetestresult entTlOnlinetestresult)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlOnlinetestresultSql entTlOnlinetestresultSql = new EntTlOnlinetestresultSql();
		try {
			
			sqls.add(entTlOnlinetestresultSql.getDeleteSql(entTlOnlinetestresultSql.getOtrsDbFields(), entTlOnlinetestresult.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlOnlinetestresult;
	}
	
}

