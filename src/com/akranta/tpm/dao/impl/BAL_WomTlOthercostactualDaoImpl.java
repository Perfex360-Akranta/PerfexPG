package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlOthercostactualDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_WomTlOthercostactualSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlOthercostactual;

/* dao implementation */
public class BAL_WomTlOthercostactualDaoImpl implements BAL_WomTlOthercostactualDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlOthercostactualDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlOthercostactual create(BAL_WomTlOthercostactual womTlOthercostactual) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlOthercostactualSql womTlOthercostactualSql = new BAL_WomTlOthercostactualSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
			sqls.add(BAL_WomTlOthercostactualSql.getInsertSql(womTlOthercostactualSql.getOtcdDbFields(), womTlOthercostactual.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlOthercostactual;
	}
	
	public BAL_WomTlOthercostactual update(BAL_WomTlOthercostactual womTlOthercostactual)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlOthercostactualSql womTlOthercostactualSql = new BAL_WomTlOthercostactualSql();

		sqls.add(BAL_WomTlOthercostactualSql.getUpdateSql(womTlOthercostactualSql.getOtcdDbFields(), womTlOthercostactual.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlOthercostactual;
	}
	
	public BAL_WomTlOthercostactual delete(BAL_WomTlOthercostactual womTlOthercostactual)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlOthercostactualSql womTlOthercostactualSql = new BAL_WomTlOthercostactualSql();
		try {
			
			sqls.add(BAL_WomTlOthercostactualSql.getDeleteSql(womTlOthercostactualSql.getOtcdDbFields(), womTlOthercostactual.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlOthercostactual;
	}
	

}

