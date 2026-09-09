package com.akranta.tpm.dao.impl;



import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlActplnNonemployeeDao;
import com.akranta.tpm.dao.sql.GenTlActplnNonemployeeSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActplnNonemployee;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlActplnNonemployeeDaoImpl implements GenTlActplnNonemployeeDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlActplnNonemployeeDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlActplnNonemployee create(GenTlActplnNonemployee genTlActplnNonemployee) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlActplnNonemployeeSql genTlActplnNonemployeeSql = new GenTlActplnNonemployeeSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlActplnNonemployee.setNactKeyid(dbActionTemplate.getSequenceNumber(GenTlActplnNonemployeeSql.TBL_GEN_TL_ACTPLN_NONEMPLOYEE,10,"NACT","","")); // set the sequnce number 
			sqls.add(GenTlActplnNonemployeeSql.getInsertSql(genTlActplnNonemployeeSql.getNactDbFields(), genTlActplnNonemployee.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlActplnNonemployee;
	}
	
	public GenTlActplnNonemployee update(GenTlActplnNonemployee genTlActplnNonemployee)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlActplnNonemployeeSql genTlActplnNonemployeeSql = new GenTlActplnNonemployeeSql();
		try {

			sqls.add(GenTlActplnNonemployeeSql.getUpdateSql(genTlActplnNonemployeeSql.getNactDbFields(), genTlActplnNonemployee.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlActplnNonemployee;
	}
	
	public GenTlActplnNonemployee delete(GenTlActplnNonemployee genTlActplnNonemployee)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlActplnNonemployeeSql genTlActplnNonemployeeSql = new GenTlActplnNonemployeeSql();
		try {
			
			sqls.add(genTlActplnNonemployeeSql.getDeleteSql(genTlActplnNonemployeeSql.getNactDbFields(), genTlActplnNonemployee.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlActplnNonemployee;
	}

	@Override
	public List<String[]> getOthers(CommonFilter commonFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg("commonFilter.getActionKeyId()::::::::"+commonFilter.getActionKeyId());
		if(UIUtils.isValidKeyId(commonFilter.getActionKeyId())){
		sql.append(" select 'NACT_KEYID' as NACT_KEYID,'Name' as nactname,'NACT_REFDOCID' as NACT_REFDOCID from dual union all");
		sql.append(" select  NACT_KEYID,NACT_NAME as nactname,NACT_REFDOCID  from gen_tl_actPln_Nonemployee where NACT_REFDOCID='" );
		sql.append(commonFilter.getActionKeyId());
		sql.append("'");
		}else{
			sql.append(" select 'NACT_KEYID' as NACT_KEYID,'Name' as nactname,'NACT_REFDOCID' as NACT_REFDOCID from dual union all");
			sql.append(" select  NACT_KEYID,NACT_NAME as nactname,NACT_REFDOCID  from gen_tl_actPln_Nonemployee" );
			}
		//if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
		CommonMessage.debugMsg(sql.toString());
		return dbActionTemplate.getDataList(sql.toString());
	}
	
}

