package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import com.akranta.tpm.utils.CommonMessage;
import java.util.List;

import com.akranta.tpm.dao.BdmTlDtlDao;
import com.akranta.tpm.dao.sql.BdmTlDtlSql;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;

/* dao implementation */
public class BdmTlDtlDaoImpl implements BdmTlDtlDao {


	private DBActionTemplate dbActionTemplate; 

	public BdmTlDtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BdmTlDtl create(BdmTlDtl bdmTlDtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BdmTlDtlSql bdmTlDtlSql = new BdmTlDtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			bdmTlDtl.setBdanKeyid(dbActionTemplate.getSequenceNumber(BdmTlDtlSql.TBL_BDM_TL_DTL)); // set the sequnce number 
			sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return bdmTlDtl;
	}
	
	public BdmTlDtl update(BdmTlDtl bdmTlDtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BdmTlDtlSql bdmTlDtlSql = new BdmTlDtlSql();
		try {

			sqls.add(BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlDtl;
	}
	
	public BdmTlDtl delete(BdmTlDtl bdmTlDtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BdmTlDtlSql bdmTlDtlSql = new BdmTlDtlSql();
		try {
			
			sqls.add(bdmTlDtlSql.getDeleteSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlDtl;
	}

	@Override
	public BdmTlDtl selectBd(String keyid) throws Exception {
		// TODO Auto-generated method stub
		
		BdmTlDtl bdmTlDtl = new BdmTlDtl();
			String sql = BdmTlDtlSql.select();
			CommonMessage.debugMsg(sql);
			Object args [] = new Object [] { keyid };
			bdmTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			return bdmTlDtl;
		}
	

	
}

