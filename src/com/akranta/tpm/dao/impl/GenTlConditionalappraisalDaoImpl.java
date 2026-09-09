package com.akranta.tpm.dao.impl;

import com.akranta.tpm.utils.CommonMessage;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.GenTlConditionalappraisalDao;
import com.akranta.tpm.dao.sql.GenTlConditionalappraisalSql;
import com.akranta.tpm.model.GenTlConditionalappraisal;

/* dao implementation */
public class GenTlConditionalappraisalDaoImpl implements GenTlConditionalappraisalDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlConditionalappraisalDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlConditionalappraisal create(GenTlConditionalappraisal genTlConditionalappraisal) 	throws Exception {
		CommonMessage.debugMsg(" Inside DaoIMPL::22nd Oct try ");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlConditionalappraisalSql genTlConditionalappraisalSql = new GenTlConditionalappraisalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			CommonMessage.debugMsg(" Inside Try DaoIMPL::22nd Oct try ");;
			genTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber("GEN_TL_CONDITIONALAPPRAISAL",10,"CDAP","","Y")); // set the sequnce number 
			sqls.add(GenTlConditionalappraisalSql.getInsertSql(genTlConditionalappraisalSql.getCdapDbFields(), genTlConditionalappraisal.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlConditionalappraisal;
	}
	
	public GenTlConditionalappraisal update(GenTlConditionalappraisal genTlConditionalappraisal)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlConditionalappraisalSql genTlConditionalappraisalSql = new GenTlConditionalappraisalSql();
		try {

			sqls.add(GenTlConditionalappraisalSql.getUpdateSql(genTlConditionalappraisalSql.getCdapDbFields(), genTlConditionalappraisal.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlConditionalappraisal;
	}
	
	public GenTlConditionalappraisal delete(GenTlConditionalappraisal genTlConditionalappraisal)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlConditionalappraisalSql genTlConditionalappraisalSql = new GenTlConditionalappraisalSql();
		try {
			
			sqls.add(genTlConditionalappraisalSql.getDeleteSql(genTlConditionalappraisalSql.getCdapDbFields(), genTlConditionalappraisal.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlConditionalappraisal;
	}

	@Override
	public List<String[]> getConditionalAppraisalGrid() throws Exception {
		
	StringBuffer sql =new StringBuffer();
	List<String> params = null;
	sql.append("SELECT CDAP_KEYID,CDAP_COMPONENTID AS \"Component\",CDAP_DIMENSION AS \"Dimension\",CDAP_CHECKINGTOOL AS \"Checking Tools\",");
	sql.append("CDAP_TYPEOFCHECK AS \"Type Of Check\",CDAP_ACTUALCONDITIN AS \"Actual Condition\",CDAP_ACTIONREQUIRED AS \"Action Required\",");
	sql.append("EMPM_NAME AS \"Responsible\",CDAP_TARGETDATE AS \"Target Date\",CDAP_REMARKS AS \"Remarks\""); 
	sql.append(" FROM "); 
	sql.append("GEN_TL_CONDITIONALAPPRAISAL,GEN_TL_EMPLOYEEMST");	
	sql.append(" where EMPM_KEYID=CDAP_RESPONSIBILITY");	
	CommonMessage.debugMsg("sql...."+sql.toString());
	List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
    return gridData;
	}

	@Override
	public GenTlConditionalappraisal getAllFillControl(String keyId) throws Exception {
		GenTlConditionalappraisal genTlConditionalappraisal = new GenTlConditionalappraisal();
		String sql = GenTlConditionalappraisalSql.getSingledata();
		CommonMessage.debugMsg("key- in impl"+keyId);
		CommonMessage.debugMsg("sql in impl"+sql);
		Object args[] = new Object[] {keyId};
		genTlConditionalappraisal.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlConditionalappraisal;
		
		
	}

	@Override
	public List<String[]> getfillgriddata() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select 'Component','Dimension','Checking Tools','Type of Check','Ideal Condition','Actual Condition','Action Required','Responsible','Target','Remarks'"
				+ " From Dual " );
		
		
		CommonMessage.debugMsg("sql 1234..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
}

