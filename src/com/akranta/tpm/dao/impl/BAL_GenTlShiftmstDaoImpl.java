package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_GenTlShiftmstDao;

import com.akranta.tpm.dao.sql.BAL_GenTlShiftmstSql;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.model.BAL_GenTlShiftmst;

/* dao implementation */
public class BAL_GenTlShiftmstDaoImpl implements BAL_GenTlShiftmstDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_GenTlShiftmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenTlShiftmst create(BAL_GenTlShiftmst genTlShiftmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlShiftmstSql genTlShiftmstSql = new BAL_GenTlShiftmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		System.out.println("dao impl");
	//	try{
		
			genTlShiftmst.setSftmKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlShiftmstSql.TBL_GEN_TL_SHIFTMST)); // set the sequnce number 
			sqls.add(BAL_GenTlShiftmstSql.getInsertSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray())); // add insert sql for master table

			System.out.println("dao before return impl");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	/*	}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlShiftmst;
	}
	
	public BAL_GenTlShiftmst update(BAL_GenTlShiftmst genTlShiftmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlShiftmstSql genTlShiftmstSql = new BAL_GenTlShiftmstSql();
		try {

			sqls.add(BAL_GenTlShiftmstSql.getUpdateSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlShiftmst;
	}
	
	public BAL_GenTlShiftmst delete(BAL_GenTlShiftmst genTlShiftmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlShiftmstSql genTlShiftmstSql = new BAL_GenTlShiftmstSql();
		try {
			
			sqls.add(BAL_GenTlShiftmstSql.getDeleteSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlShiftmst;
	}

	@Override
	public BAL_GenTlShiftmst select(String keyid) throws Exception {
		BAL_GenTlShiftmst genTlShiftmst = new BAL_GenTlShiftmst();
		String sql = BAL_GenTlShiftmstSql.getGenTlShiftmstSql();
		
		Object args [] = new Object [] { keyid };
		genTlShiftmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlShiftmst;
	}

	@Override
	public List<String[]> getGenTlShiftmstShiftorder(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getGenTlShiftmstShiftorder(ComboFilter comboFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

