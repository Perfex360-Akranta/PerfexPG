package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlUtilitycostactualDao;
import com.akranta.tpm.dao.sql.BAL_WomTlUtilitycostactualSql;
import com.akranta.tpm.model.BAL_WomTlUtilitycostactual;

/* dao implementation */
public class BAL_WomTlUtilitycostactualDaoImpl implements BAL_WomTlUtilitycostactualDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlUtilitycostactualDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlUtilitycostactual create(BAL_WomTlUtilitycostactual womTlUtilitycostactual) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlUtilitycostactualSql womTlUtilitycostactualSql = new BAL_WomTlUtilitycostactualSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
		sqls.add(BAL_WomTlUtilitycostactualSql.getInsertSql(womTlUtilitycostactualSql.getUtcaDbFields(), womTlUtilitycostactual.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlUtilitycostactual;
	}
	
	public BAL_WomTlUtilitycostactual update(BAL_WomTlUtilitycostactual womTlUtilitycostactual)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlUtilitycostactualSql womTlUtilitycostactualSql = new BAL_WomTlUtilitycostactualSql();
	
		sqls.add(BAL_WomTlUtilitycostactualSql.getUpdateSql(womTlUtilitycostactualSql.getUtcaDbFields(), womTlUtilitycostactual.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlUtilitycostactual;
	}
	
	public BAL_WomTlUtilitycostactual delete(BAL_WomTlUtilitycostactual womTlUtilitycostactual)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlUtilitycostactualSql womTlUtilitycostactualSql = new BAL_WomTlUtilitycostactualSql();
		try {
			
			sqls.add(BAL_WomTlUtilitycostactualSql.getDeleteSql(womTlUtilitycostactualSql.getUtcaDbFields(), womTlUtilitycostactual.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlUtilitycostactual;
	}
	
}

