package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.SheTlRiskassessmentdtlDao;
import com.akranta.tpm.dao.sql.SheTlRiskassessmentdtlSql;
import com.akranta.tpm.model.SheTlRiskassessmentdtl;

/* dao implementation */
public class SheTlRiskassessmentdtlDaoImpl implements SheTlRiskassessmentdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public SheTlRiskassessmentdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlRiskassessmentdtl create(SheTlRiskassessmentdtl sheTlRiskassessmentdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlRiskassessmentdtlSql sheTlRiskassessmentdtlSql = new SheTlRiskassessmentdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			sheTlRiskassessmentdtl.setRasdKeyid(dbActionTemplate.getSequenceNumber(SheTlRiskassessmentdtlSql.TBL_SHE_TL_RISKASSESSMENTDTL)); // set the sequnce number 
			sqls.add(SheTlRiskassessmentdtlSql.getInsertSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlRiskassessmentdtl;
	}
	
	public SheTlRiskassessmentdtl update(SheTlRiskassessmentdtl sheTlRiskassessmentdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlRiskassessmentdtlSql sheTlRiskassessmentdtlSql = new SheTlRiskassessmentdtlSql();
		try {

			sqls.add(SheTlRiskassessmentdtlSql.getUpdateSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlRiskassessmentdtl;
	}
	
	public SheTlRiskassessmentdtl delete(SheTlRiskassessmentdtl sheTlRiskassessmentdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlRiskassessmentdtlSql sheTlRiskassessmentdtlSql = new SheTlRiskassessmentdtlSql();
		try {
			
			sqls.add(sheTlRiskassessmentdtlSql.getDeleteSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlRiskassessmentdtl;
	}
	
}

