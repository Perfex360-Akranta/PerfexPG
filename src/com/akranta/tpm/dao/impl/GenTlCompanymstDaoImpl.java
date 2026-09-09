package com.akranta.tpm.dao.impl;
import com.akranta.tpm.utils.CommonMessage;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.dao.GenTlCompanymstDao;
import com.akranta.tpm.dao.sql.GenTlCompanymstSql;
import com.akranta.tpm.dao.sql.GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlCompanymst;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;

/* dao implementation */
public class GenTlCompanymstDaoImpl implements GenTlCompanymstDao {

	private FunctionalLocValidations  functionalLocValidations;
	private DBActionTemplate dbActionTemplate; 

	public GenTlCompanymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlCompanymst create(GenTlCompanymst genTlCompanymst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlCompanymstSql genTlCompanymstSql = new GenTlCompanymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		//try{
		CommonMessage.debugMsg("inside dao impl create");
		CommonMessage.debugMsg("inside dao impl  " + genTlCompanymst.getSaveArray());
			//genTlCompanymst.setCompKeyid(dbActionTemplate.getSequenceNumber(GenTlCompanymstSql.TBL_GEN_TL_COMPANYMST)); // set the sequnce number 
			/*getSequenceNo modified by manikandan for new functional Location*/
		genTlCompanymst.setCompKeyid(dbActionTemplate.getSequenceNumber(GenTlCompanymstSql.TBL_GEN_TL_COMPANYMST, 10, "CMP", "MMYY", "Y")); // set the sequnce number
			sqls.add(GenTlCompanymstSql.getInsertSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray())); // add insert sql for master table
			
			
			GenTlFunctionallocn newGenTlFunctionallocn = genTlCompanymst.getGenTlFunctionallocn();
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(genTlCompanymst.getCompName());
			newGenTlFunctionallocn.setFnlnDisplaycode(genTlCompanymst.getCompCode());
			newGenTlFunctionallocn.setFnlnElementid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnOriginalid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnParentid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnElementtype("CMP");
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	/*	}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlCompanymst;
	}
	
	public GenTlCompanymst update(GenTlCompanymst genTlCompanymst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlCompanymstSql genTlCompanymstSql = new GenTlCompanymstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try {
			CommonMessage.debugMsg("inside dao impl update for function " );
			
			sqls.add(GenTlCompanymstSql.getUpdateSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray()));
			
			GenTlFunctionallocn newGenTlFunctionallocn = genTlCompanymst.getGenTlFunctionallocn();
			CommonMessage.debugMsg("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
			//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
			
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlCompanymst;
	}
	
	public GenTlCompanymst delete(GenTlCompanymst genTlCompanymst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlCompanymstSql genTlCompanymstSql = new GenTlCompanymstSql();
		try {
			
			sqls.add(GenTlCompanymstSql.getDeleteSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlCompanymst;
	}

	@Override
	public List<String[]> getGenTlCompanymst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenTlCompanymst select(String keyid)throws  Exception 
	{
		GenTlCompanymst genTlCompanymst = new GenTlCompanymst();
		String sql = GenTlCompanymstSql.getCompanymstSql();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { keyid };
		genTlCompanymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlCompanymst;
	}
	
}

