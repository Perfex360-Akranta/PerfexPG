package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlFunctionallocnDao;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.utils.CommonMessage;
/* dao implementation */
public class GenTlFunctionallocnDaoImpl implements GenTlFunctionallocnDao {


	private DBActionTemplate dbActionTemplate; 
	private GenTlFunctionallocnSql genTlFunctionallocnSql;

	public GenTlFunctionallocnDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlFunctionallocnSql = new GenTlFunctionallocnSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlFunctionallocn create(GenTlFunctionallocn genTlFunctionallocn) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
		//	genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN)); // set the sequnce number 
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFunctionallocn;
	}
	
	public GenTlFunctionallocn update(GenTlFunctionallocn genTlFunctionallocn)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql();
		try {

			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlFunctionallocn;
	}
	
	public GenTlFunctionallocn delete(GenTlFunctionallocn genTlFunctionallocn)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql();
		try {
			
			sqls.add(GenTlFunctionallocnSql.getDeleteSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlFunctionallocn;
	}

	@Override
	public List<GenTlFunctionallocn> save(List<GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception 
	{
		CommonMessage.debugMsg("In Save Funct Dao impl");
		List<String> sqls = new ArrayList<String>();
		
		CommonMessage.debugMsg("genTlFunclocnList="+existGenTlFunctionallocn);
		
		if(existGenTlFunctionallocn != null  )
		{	
			for(GenTlFunctionallocn genTlFunctionallocn:existGenTlFunctionallocn)
			{
					sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(),genTlFunctionallocn.getSaveArray()));
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
			}
			
		}
		return existGenTlFunctionallocn;
	
	}
	
}

