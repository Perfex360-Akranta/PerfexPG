package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlBudgetmstDao;
import com.akranta.tpm.dao.sql.BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.EntTlBatchcompletionSql;
import com.akranta.tpm.dao.sql.EntTlBudgetmstSql;
import com.akranta.tpm.model.EntTlBudgetmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlBudgetmstDaoImpl implements EntTlBudgetmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlBudgetmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlBudgetmst create(EntTlBudgetmst entTlBudgetmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBudgetmstSql entTlBudgetmstSql = new EntTlBudgetmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlBudgetmst.setBudgKeyid(dbActionTemplate.getSequenceNumber(EntTlBudgetmstSql.TBL_ENT_TL_BUDGETMST,9,"BGT","YYMM" ,"Y")); // set the sequnce number 
			sqls.add(EntTlBudgetmstSql.getInsertSql(entTlBudgetmstSql.getBudgDbFields(), entTlBudgetmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBudgetmst;
	}
	
	public EntTlBudgetmst update(EntTlBudgetmst entTlBudgetmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlBudgetmstSql entTlBudgetmstSql = new EntTlBudgetmstSql();
		try {

			sqls.add(EntTlBudgetmstSql.getUpdateSql(entTlBudgetmstSql.getBudgDbFields(), entTlBudgetmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlBudgetmst;
	}
	
	public EntTlBudgetmst delete(EntTlBudgetmst entTlBudgetmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlBudgetmstSql entTlBudgetmstSql = new EntTlBudgetmstSql();
		try {
			
			sqls.add(EntTlBudgetmstSql.getDeleteSql(entTlBudgetmstSql.getBudgDbFields(), entTlBudgetmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBudgetmst;
	}
	public List<String[]> getBudget(String batchId) throws Exception
	{
		String sql = EntTlBudgetmstSql.getBudgetSql(batchId);
		CommonMessage.debugMsg(sql);
		List<String[]> budgetList = dbActionTemplate.getDataList(sql);
		return budgetList;	
	}
	public EntTlBudgetmst getBudgetFromKey(String budgetId) throws Exception
	{
		EntTlBudgetmst entTlBudgetmst = new EntTlBudgetmst();
		String sql = EntTlBudgetmstSql.getBudgetFromKeySql();		
		Object args [] = new Object [] { budgetId };
		entTlBudgetmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return entTlBudgetmst;		
	}
	
}

