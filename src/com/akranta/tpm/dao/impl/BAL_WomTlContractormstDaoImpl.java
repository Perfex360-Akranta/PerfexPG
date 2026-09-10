package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlContractormstDao;
import com.akranta.tpm.dao.sql.BAL_WomTlContractormstSql;
import com.akranta.tpm.model.BAL_WomTlContractormst;

/* dao implementation */
public class BAL_WomTlContractormstDaoImpl implements BAL_WomTlContractormstDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlContractormstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlContractormst create(BAL_WomTlContractormst womTlContractormst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlContractormstSql womTlContractormstSql = new BAL_WomTlContractormstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			womTlContractormst.setCncsKeyid(dbActionTemplate.getSequenceNumber(BAL_WomTlContractormstSql.TBL_WOM_TL_CONTRACTORMST)); // set the sequnce number 
			sqls.add(BAL_WomTlContractormstSql.getInsertSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return womTlContractormst;
	}
	
	public BAL_WomTlContractormst update(BAL_WomTlContractormst womTlContractormst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlContractormstSql womTlContractormstSql = new BAL_WomTlContractormstSql();
		try {

			sqls.add(BAL_WomTlContractormstSql.getUpdateSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return womTlContractormst;
	}
	
	public BAL_WomTlContractormst delete(BAL_WomTlContractormst womTlContractormst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlContractormstSql womTlContractormstSql = new BAL_WomTlContractormstSql();
		try {
			
			sqls.add(BAL_WomTlContractormstSql.getDeleteSql(womTlContractormstSql.getCncsDbFields(), womTlContractormst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlContractormst;
	}
	
}

