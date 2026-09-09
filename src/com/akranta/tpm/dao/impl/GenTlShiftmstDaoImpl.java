package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlShiftmstDao;

import com.akranta.tpm.dao.sql.GenTlShiftmstSql;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.model.GenTlShiftmst;
import com.akranta.tpm.utils.CommonMessage;
/* dao implementation */
public class GenTlShiftmstDaoImpl implements GenTlShiftmstDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlShiftmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlShiftmst create(GenTlShiftmst genTlShiftmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlShiftmstSql genTlShiftmstSql = new GenTlShiftmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		CommonMessage.debugMsg("dao impl");
	//	try{
		
			genTlShiftmst.setSftmKeyid(dbActionTemplate.getSequenceNumber(GenTlShiftmstSql.TBL_GEN_TL_SHIFTMST)); // set the sequnce number 
			sqls.add(GenTlShiftmstSql.getInsertSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray())); // add insert sql for master table

			CommonMessage.debugMsg("dao before return impl");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	/*	}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}*/
		return genTlShiftmst;
	}
	
	public GenTlShiftmst update(GenTlShiftmst genTlShiftmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlShiftmstSql genTlShiftmstSql = new GenTlShiftmstSql();
		try {

			sqls.add(GenTlShiftmstSql.getUpdateSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlShiftmst;
	}
	
	public GenTlShiftmst delete(GenTlShiftmst genTlShiftmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlShiftmstSql genTlShiftmstSql = new GenTlShiftmstSql();
		try {
			
			sqls.add(GenTlShiftmstSql.getDeleteSql(genTlShiftmstSql.getSftmDbFields(), genTlShiftmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlShiftmst;
	}

	@Override
	public GenTlShiftmst select(String keyid) throws Exception {
		GenTlShiftmst genTlShiftmst = new GenTlShiftmst();
		String sql = GenTlShiftmstSql.getGenTlShiftmstSql();
		
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

