package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.dao.BAL_GenTlFunctionallocnDao;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
//import com.akranta.tpm.dao.sql.PlmTlSparedtlSql;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
//import com.akranta.tpm.model.PlmTlSparedtl;

/* dao implementation */
public class BAL_GenTlFunctionallocnDaoImpl implements BAL_GenTlFunctionallocnDao {


	private DBActionTemplate dbActionTemplate; 
	private BAL_GenTlFunctionallocnSql genTlFunctionallocnSql;

	public BAL_GenTlFunctionallocnDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlFunctionallocn create(BAL_GenTlFunctionallocn genTlFunctionallocn) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
		//	genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN)); // set the sequnce number 
			sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlFunctionallocn;
	}
	
	public BAL_GenTlFunctionallocn update(BAL_GenTlFunctionallocn genTlFunctionallocn)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();
		try {

			sqls.add(BAL_GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlFunctionallocn;
	}
	
	public BAL_GenTlFunctionallocn delete(BAL_GenTlFunctionallocn genTlFunctionallocn)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();
		try {
			
			sqls.add(BAL_GenTlFunctionallocnSql.getDeleteSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlFunctionallocn;
	}

	@Override
	public List<BAL_GenTlFunctionallocn> save(List<BAL_GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception 
	{
		System.out.println("In Save Funct Dao impl");
		List<String> sqls = new ArrayList<String>();
		
		System.out.println("genTlFunclocnList="+existGenTlFunctionallocn);
		
		if(existGenTlFunctionallocn != null  )
		{	
			for(BAL_GenTlFunctionallocn genTlFunctionallocn:existGenTlFunctionallocn)
			{
					sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(),genTlFunctionallocn.getSaveArray()));
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
			}
			
		}
		return existGenTlFunctionallocn;
	
	}
	
}

