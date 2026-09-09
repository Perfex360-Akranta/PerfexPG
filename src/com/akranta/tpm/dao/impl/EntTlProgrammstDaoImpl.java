package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.EntTlProgrammstDao;
import com.akranta.tpm.dao.sql.EntTlProgrammstSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.PlmTlGenmaintenanceSql;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlProgrammstDaoImpl implements EntTlProgrammstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlProgrammstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlProgrammst create(EntTlProgrammst entTlProgrammst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlProgrammstSql entTlProgrammstSql = new EntTlProgrammstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlProgrammst.setProgKeyid(dbActionTemplate.getSequenceNumber(EntTlProgrammstSql.TBL_ENT_TL_PROGRAMMST)); // set the sequnce number 
			sqls.add(EntTlProgrammstSql.getInsertSql(entTlProgrammstSql.getProgDbFields(), entTlProgrammst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlProgrammst;
	}
	
	public EntTlProgrammst update(EntTlProgrammst entTlProgrammst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlProgrammstSql entTlProgrammstSql = new EntTlProgrammstSql();
		try {

			sqls.add(EntTlProgrammstSql.getUpdateSql(entTlProgrammstSql.getProgDbFields(), entTlProgrammst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlProgrammst;
	}
	
	public EntTlProgrammst delete(EntTlProgrammst entTlProgrammst)
			throws BusinessApplicationExceptions,Exception {
        CommonMessage.debugMsg("inside Delete of Programs");
		List<String> sqls = new ArrayList<String>();
		EntTlProgrammstSql entTlProgrammstSql = new EntTlProgrammstSql();
		try {
			
			sqls.add(EntTlProgrammstSql.getDeleteSql(entTlProgrammstSql.getProgDbFields(), entTlProgrammst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch(BusinessApplicationExceptions e)
		{
			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlProgrammst;
	}

	@Override
	public List<String[]> getgridData() throws Exception {
		// TODO Auto-generated method stub
		String sql = EntTlProgrammstSql.masterGrid();
		
		List<String[]> grdData = dbActionTemplate.getDataList(sql);

	return grdData;
	}

	@Override
	public EntTlProgrammst fillFormvalues(String progKey) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		EntTlProgrammst entTlProgrammst = new EntTlProgrammst();
		String sql = EntTlProgrammstSql.getFormData();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { progKey };
		entTlProgrammst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return entTlProgrammst;
	}

	@Override
	public String getevalTypeId(String topickeyId) throws Exception {
		// TODO Auto-generated method stub
		String evalId = dbActionTemplate.getSingleValue("SELECT TOPI_EVALUATIONTYPEID FROM ENT_TL_TOPICMST WHERE TOPI_KEYID = '"+topickeyId+"'");
		CommonMessage.debugMsg("SELECT TOPI_EVALUATIONTYPEID FROM ENT_TL_TOPICMST WHERE TOPI_KEYID = '"+topickeyId+"'");
		return evalId;
	}
	
}

