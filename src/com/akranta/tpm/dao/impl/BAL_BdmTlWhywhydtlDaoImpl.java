package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_BdmTlWhywhydtlDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhydtlSql;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;

/* dao implementation */
public class BAL_BdmTlWhywhydtlDaoImpl implements BAL_BdmTlWhywhydtlDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_BdmTlWhywhydtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_BdmTlWhywhydtl create(BAL_BdmTlWhywhydtl bdmTlWhywhydtl) 	throws Exception {

		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_BdmTlWhywhydtlSql bdmTlWhywhydtlSql = new BAL_BdmTlWhywhydtlSql(); // contains dbtable,field names, Field types and related sqls  of master table  
		try{
		
			bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL)); // set the sequnce number 
			sqls.add(BAL_BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return bdmTlWhywhydtl;
	}
	
	public BAL_BdmTlWhywhydtl update(BAL_BdmTlWhywhydtl bdmTlWhywhydtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlWhywhydtlSql bdmTlWhywhydtlSql = new BAL_BdmTlWhywhydtlSql();
		try {

			sqls.add(BAL_BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			System.out.println("save array in dtldaoimple"+ bdmTlWhywhydtl.getSaveArray());
			System.out.println("query in dtldaoimple"+sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlWhywhydtl;
	}
	
	public BAL_BdmTlWhywhydtl delete(BAL_BdmTlWhywhydtl bdmTlWhywhydtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlWhywhydtlSql bdmTlWhywhydtlSql = new BAL_BdmTlWhywhydtlSql();
		try {
			sqls.add(BAL_BdmTlWhywhydtlSql.getDeleteSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlWhywhydtl;
	}
	
}

