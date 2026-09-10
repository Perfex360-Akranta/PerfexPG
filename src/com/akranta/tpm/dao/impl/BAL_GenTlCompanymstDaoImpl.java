package com.akranta.tpm.dao.impl;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.dao.BAL_GenTlCompanymstDao;
import com.akranta.tpm.dao.sql.BAL_GenTlCompanymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCompanymst;
import com.akranta.tpm.model.BAL_GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;

/* dao implementation */
public class BAL_GenTlCompanymstDaoImpl implements BAL_GenTlCompanymstDao {

	private FunctionalLocValidations  functionalLocValidations;
	private DBActionTemplate dbActionTemplate; 

	public BAL_GenTlCompanymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlCompanymst create(BAL_GenTlCompanymst genTlCompanymst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlCompanymstSql genTlCompanymstSql = new BAL_GenTlCompanymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		//try{
		System.out.println("inside dao impl create");
		System.out.println("inside dao impl  " + genTlCompanymst.getSaveArray());
			//genTlCompanymst.setCompKeyid(dbActionTemplate.getSequenceNumber(GenTlCompanymstSql.TBL_GEN_TL_COMPANYMST)); // set the sequnce number 
			/*getSequenceNo modified by manikandan for new functional Location*/
		genTlCompanymst.setCompKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlCompanymstSql.TBL_GEN_TL_COMPANYMST, 10, "CMP", "MMYY", "Y")); // set the sequnce number
			sqls.add(BAL_GenTlCompanymstSql.getInsertSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray())); // add insert sql for master table
			
			
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlCompanymst.getGenTlFunctionallocn();
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(genTlCompanymst.getCompName());
			newGenTlFunctionallocn.setFnlnDisplaycode(genTlCompanymst.getCompCode());
			newGenTlFunctionallocn.setFnlnElementid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnOriginalid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnParentid(genTlCompanymst.getCompKeyid());
			newGenTlFunctionallocn.setFnlnElementtype("CMP");
			sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	/*	}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlCompanymst;
	}
	
	public BAL_GenTlCompanymst update(BAL_GenTlCompanymst genTlCompanymst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlCompanymstSql genTlCompanymstSql = new BAL_GenTlCompanymstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		try {
			System.out.println("inside dao impl update for function " );
			
			sqls.add(BAL_GenTlCompanymstSql.getUpdateSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray()));
			
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlCompanymst.getGenTlFunctionallocn();
			System.out.println("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
			//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
			
			sqls.add(BAL_GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlCompanymst;
	}
	
	public BAL_GenTlCompanymst delete(BAL_GenTlCompanymst genTlCompanymst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlCompanymstSql genTlCompanymstSql = new BAL_GenTlCompanymstSql();
		try {
			
			sqls.add(BAL_GenTlCompanymstSql.getDeleteSql(genTlCompanymstSql.getCompDbFields(), genTlCompanymst.getSaveArray()));

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
	public BAL_GenTlCompanymst select(String keyid)throws  Exception 
	{
		BAL_GenTlCompanymst genTlCompanymst = new BAL_GenTlCompanymst();
		String sql = BAL_GenTlCompanymstSql.getCompanymstSql();
		System.out.println("in dao " );
		Object args [] = new Object [] { keyid };
		genTlCompanymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlCompanymst;
	}
	
}

