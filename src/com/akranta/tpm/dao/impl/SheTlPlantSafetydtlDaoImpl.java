package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.SheTlPlantSafetydtlDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.sql.SheTlDailyauditdtlSql;
import com.akranta.tpm.dao.sql.SheTlPlantSafetydtlSql;
//import com.akranta.tpm.dao.sql.SheTlRiskassessmentdtlSql;
import com.akranta.tpm.model.SheTlPlantSafetydtl;

public class SheTlPlantSafetydtlDaoImpl  implements SheTlPlantSafetydtlDao{

	
	private DBActionTemplate dbActionTemplate; 

	public SheTlPlantSafetydtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	
	@Override
	public SheTlPlantSafetydtl create(SheTlPlantSafetydtl sheTlPlantSafetydtl)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//SheTlDailyauditdtlSql sheTlDailyauditdtlSql=new SheTlDailyauditdtlSql();// contains dbtable,field names, Field types and related sqls  of master table
		SheTlPlantSafetydtlSql sheTlPlantSafetydtlSql=new SheTlPlantSafetydtlSql();
		try{
		
			sheTlPlantSafetydtl.setSpsdKeyid(dbActionTemplate.getSequenceNumber(sheTlPlantSafetydtlSql.TBL_SHE_TL_PLANTSAFETYDTL)); // set the sequnce number 
			sqls.add(SheTlPlantSafetydtlSql.getInsertSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlPlantSafetydtl;
	}

	@Override
	public SheTlPlantSafetydtl update(SheTlPlantSafetydtl sheTlPlantSafetydtl)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		SheTlPlantSafetydtlSql sheTlPlantSafetydtlSql=new SheTlPlantSafetydtlSql();
		try {

			sqls.add(sheTlPlantSafetydtlSql.getUpdateSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlPlantSafetydtl;

	}

	@Override
	public SheTlPlantSafetydtl delete(SheTlPlantSafetydtl sheTlPlantSafetydtl)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		SheTlPlantSafetydtlSql sheTlPlantSafetydtlSql=new SheTlPlantSafetydtlSql();
		try {
			
			sqls.add(sheTlPlantSafetydtlSql.getDeleteSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlPlantSafetydtl;
	}

}
