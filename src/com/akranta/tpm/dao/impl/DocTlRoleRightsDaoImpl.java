package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.DocTlRoleRightsDao;
import com.akranta.tpm.dao.sql.DocTlRoleRightsSql;
import com.akranta.tpm.model.DocTlRoleRights;

/* dao implementation */
public class DocTlRoleRightsDaoImpl implements DocTlRoleRightsDao {


	private DBActionTemplate dbActionTemplate; 

	public DocTlRoleRightsDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public DocTlRoleRights create(DocTlRoleRights docTlRoleRights) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		DocTlRoleRightsSql docTlRoleRightsSql = new DocTlRoleRightsSql(); // contains dbtable,field names, Field types and related sqls  of master table
		docTlRoleRights.setRlriKeyid(dbActionTemplate.getSequenceNumber(DocTlRoleRightsSql.TBL_DOC_TL_ROLE_RIGHTS, 10, "RLR", "YYMM", "Y")); // set the sequnce number 
		sqls.add(DocTlRoleRightsSql.getInsertSql(docTlRoleRightsSql.getRlriDbFields(), docTlRoleRights.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	
		return docTlRoleRights;
	}
	
	public DocTlRoleRights update(DocTlRoleRights docTlRoleRights)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		DocTlRoleRightsSql docTlRoleRightsSql = new DocTlRoleRightsSql();
		try {

			sqls.add(DocTlRoleRightsSql.getUpdateSql(docTlRoleRightsSql.getRlriDbFields(), docTlRoleRights.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return docTlRoleRights;
	}
	
	public DocTlRoleRights delete(DocTlRoleRights docTlRoleRights)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		DocTlRoleRightsSql docTlRoleRightsSql = new DocTlRoleRightsSql();
		try {
			
			sqls.add(DocTlRoleRightsSql.getDeleteSql(docTlRoleRightsSql.getRlriDbFields(), docTlRoleRights.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return docTlRoleRights;
	}
	
}

