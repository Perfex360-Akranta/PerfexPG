package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlServicecostactualDao;
import com.akranta.tpm.dao.sql.BAL_WomTlServicecostactualSql;
import com.akranta.tpm.model.BAL_WomTlServicecostactual;

/* dao implementation */
public class BAL_WomTlServicecostactualDaoImpl implements BAL_WomTlServicecostactualDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlServicecostactualDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlServicecostactual create(BAL_WomTlServicecostactual womTlServicecostactual) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlServicecostactualSql womTlServicecostactualSql = new BAL_WomTlServicecostactualSql(); // contains dbtable,field names, Field types and related sqls  of master table
		sqls.add(BAL_WomTlServicecostactualSql.getInsertSql(womTlServicecostactualSql.getSvcaDbFields(), womTlServicecostactual.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return womTlServicecostactual;
	}
	
	public BAL_WomTlServicecostactual update(BAL_WomTlServicecostactual womTlServicecostactual)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlServicecostactualSql womTlServicecostactualSql = new BAL_WomTlServicecostactualSql();

		sqls.add(BAL_WomTlServicecostactualSql.getUpdateSql(womTlServicecostactualSql.getSvcaDbFields(), womTlServicecostactual.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
		return womTlServicecostactual;
	}
	
	public BAL_WomTlServicecostactual delete(BAL_WomTlServicecostactual womTlServicecostactual)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlServicecostactualSql womTlServicecostactualSql = new BAL_WomTlServicecostactualSql();
		try {
			
			sqls.add(BAL_WomTlServicecostactualSql.getDeleteSql(womTlServicecostactualSql.getSvcaDbFields(), womTlServicecostactual.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlServicecostactual;
	}
	
}

