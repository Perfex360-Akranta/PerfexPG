package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlBudgetExpensetypeDao;
import com.akranta.tpm.dao.sql.EntTlBudgetExpensetypeSql;
import com.akranta.tpm.model.EntTlBudgetExpensetype;

/* dao implementation */
public class EntTlBudgetExpensetypeDaoImpl implements EntTlBudgetExpensetypeDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlBudgetExpensetypeDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlBudgetExpensetype create(EntTlBudgetExpensetype entTlBudgetExpensetype) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBudgetExpensetypeSql entTlBudgetExpensetypeSql = new EntTlBudgetExpensetypeSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlBudgetExpensetype.setEbetKeyid(dbActionTemplate.getSequenceNumber(EntTlBudgetExpensetypeSql.TBL_ENT_TL_BUDGET_EXPENSETYPE)); // set the sequnce number 
			sqls.add(EntTlBudgetExpensetypeSql.getInsertSql(entTlBudgetExpensetypeSql.getEbetDbFields(), entTlBudgetExpensetype.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBudgetExpensetype;
	}
	
	public EntTlBudgetExpensetype update(EntTlBudgetExpensetype entTlBudgetExpensetype)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlBudgetExpensetypeSql entTlBudgetExpensetypeSql = new EntTlBudgetExpensetypeSql();
		try {

			sqls.add(EntTlBudgetExpensetypeSql.getUpdateSql(entTlBudgetExpensetypeSql.getEbetDbFields(), entTlBudgetExpensetype.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlBudgetExpensetype;
	}
	
	public EntTlBudgetExpensetype delete(EntTlBudgetExpensetype entTlBudgetExpensetype)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlBudgetExpensetypeSql entTlBudgetExpensetypeSql = new EntTlBudgetExpensetypeSql();
		try {
			
			sqls.add(EntTlBudgetExpensetypeSql.getDeleteSql(entTlBudgetExpensetypeSql.getEbetDbFields(), entTlBudgetExpensetype.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBudgetExpensetype;
	}
	
}

