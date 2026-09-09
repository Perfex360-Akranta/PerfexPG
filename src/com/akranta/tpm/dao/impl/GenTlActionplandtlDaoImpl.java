package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlActionplandtlDao;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlResponsibilitylinkSql;
import com.akranta.tpm.model.GenTlActionplandtl;

/* dao implementation */
public class GenTlActionplandtlDaoImpl implements GenTlActionplandtlDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlActionplandtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlActionplandtl create(GenTlActionplandtl genTlActionplandtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlActionplandtl.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL)); // set the sequnce number 
			sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(), genTlActionplandtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlActionplandtl;
	}
	
	public GenTlActionplandtl update(GenTlActionplandtl genTlActionplandtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		try {

			sqls.add(GenTlActionplandtlSql.getUpdateSql(genTlActionplandtlSql.getApldDbFields(), genTlActionplandtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlActionplandtl;
	}
	
	public GenTlActionplandtl delete(GenTlActionplandtl genTlActionplandtl)
	throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();

		sqls.add(GenTlResponsibilitylinkSql.getDeleteSqlAll(genTlActionplandtl.getApldKeyid()));

		sqls.add(GenTlActionplandtlSql.getDeleteSql(genTlActionplandtlSql.getApldDbFields(), genTlActionplandtl.getSaveArray()));
		sqls.add(GenTlActionplandtlSql.getDeleteMasterSql(genTlActionplandtl.getApldAplmKeyid()));
		dbActionTemplate.executeStatements(sqls);

		return genTlActionplandtl;
		}
	
}

