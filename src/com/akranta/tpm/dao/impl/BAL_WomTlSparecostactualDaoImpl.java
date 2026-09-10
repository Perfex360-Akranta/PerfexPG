package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlSparecostactualDao;
import com.akranta.tpm.dao.sql.BAL_WomTlSparecostactualSql;
import com.akranta.tpm.model.BAL_WomTlSparecostactual;

/* dao implementation */
public class BAL_WomTlSparecostactualDaoImpl implements BAL_WomTlSparecostactualDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlSparecostactualDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlSparecostactual create(BAL_WomTlSparecostactual womTlSparecostactual) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlSparecostactualSql womTlSparecostactualSql = new BAL_WomTlSparecostactualSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
		sqls.add(BAL_WomTlSparecostactualSql.getInsertSql(womTlSparecostactualSql.getWscaDbFields(), womTlSparecostactual.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlSparecostactual;
	}
	
	public BAL_WomTlSparecostactual update(BAL_WomTlSparecostactual womTlSparecostactual)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlSparecostactualSql womTlSparecostactualSql = new BAL_WomTlSparecostactualSql();
		try {

			sqls.add(BAL_WomTlSparecostactualSql.getUpdateSql(womTlSparecostactualSql.getWscaDbFields(), womTlSparecostactual.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return womTlSparecostactual;
	}
	
	public BAL_WomTlSparecostactual delete(BAL_WomTlSparecostactual womTlSparecostactual)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlSparecostactualSql womTlSparecostactualSql = new BAL_WomTlSparecostactualSql();
		try {
			
			sqls.add(BAL_WomTlSparecostactualSql.getDeleteSql(womTlSparecostactualSql.getWscaDbFields(), womTlSparecostactual.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlSparecostactual;
	}
	
}

