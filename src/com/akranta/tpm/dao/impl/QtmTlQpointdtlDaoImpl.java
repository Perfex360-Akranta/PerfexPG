package com.akranta.tpm.dao.impl;





import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.QtmTlQpointdtlDao;
import com.akranta.tpm.dao.sql.QtmTlQpointdtlSql;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.utils.CommonMessage;

/* dao implementation */
public class QtmTlQpointdtlDaoImpl implements QtmTlQpointdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public QtmTlQpointdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public QtmTlQpointdtl create(QtmTlQpointdtl qtmTlQpointdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlQpointdtlSql qtmTlQpointdtlSql = new QtmTlQpointdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		         CommonMessage.debugMsg("save");
			qtmTlQpointdtl.setQpdKeyid(dbActionTemplate.getSequenceNumber(QtmTlQpointdtlSql.TBL_QTM_TL_QPOINTDTL )); // set the sequnce number 
			sqls.add(QtmTlQpointdtlSql.getInsertSql(qtmTlQpointdtlSql.getQpdDbFields(), qtmTlQpointdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlQpointdtl;
	}
	
	public QtmTlQpointdtl update(QtmTlQpointdtl qtmTlQpointdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlQpointdtlSql Sql = new QtmTlQpointdtlSql();
		try {

			sqls.add(QtmTlQpointdtlSql.getUpdateSql(Sql .getQpdDbFields(), qtmTlQpointdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlQpointdtl;
	}
	
	public QtmTlQpointdtl delete(QtmTlQpointdtl qtmTlQpointdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		QtmTlQpointdtlSql qtmTlQpointdtlSql = new QtmTlQpointdtlSql();
		try {
			
			sqls.add(QtmTlQpointdtlSql.getDeleteSql(qtmTlQpointdtlSql.getQpdDbFields(), qtmTlQpointdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlQpointdtl;
	}
	
}

