package com.akranta.tpm.dao.impl;



import java.util.ArrayList;
import com.akranta.tpm.utils.CommonMessage;
import java.util.List;

import com.akranta.tpm.dao.AdmTlUsersessionsDao;
import com.akranta.tpm.dao.sql.AdmTlUsersessionsSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AdmTlUsersessions;

/* dao implementation */
public class AdmTlUsersessionsDaoImpl implements AdmTlUsersessionsDao {


	private DBActionTemplate dbActionTemplate; 
	AdmTlUsersessionsSql admTlUsersessionsSql ;// contains dbtable,field names, Field types and related sqls  of master table
	
	public AdmTlUsersessionsDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		admTlUsersessionsSql = new AdmTlUsersessionsSql(); // contains dbtable,field names, Field types and related sqls  of master table
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlUsersessions create(AdmTlUsersessions admTlUsersessions) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
		
		try{
		
			String condSql = " DATE_TRUNC('day', USSE_SESSIONDATE)   = DATE  '" + admTlUsersessions.getUsseSessiondate() + "'"; // group by  USSE_USERID ";
			
			String maxVal = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_USERSESSIONS, "COALESCE(MAX(USSE_SESSIONNO), 0) ", "USSE_USERID", admTlUsersessions.getUsseUserid().trim(),condSql);
			
			maxVal = Integer.parseInt(maxVal) + 1 +"";
			admTlUsersessions.setUsseSessionno(maxVal); // set the sequnce number 
			CommonMessage.debugMsg(" maxVal " + maxVal);
			sqls.add(AdmTlUsersessionsSql.getInsertSql(admTlUsersessionsSql.getUsseDbFields(), admTlUsersessions.getSaveArray())); // add insert sql for master table

			CommonMessage.debugMsg(" sql " + sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return admTlUsersessions;
	}
	
	public AdmTlUsersessions update(AdmTlUsersessions admTlUsersessions)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		try {

			sqls.add(AdmTlUsersessionsSql.getUpdateSql(admTlUsersessionsSql.getUsseDbFields(), admTlUsersessions.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlUsersessions;
	}
	
	public AdmTlUsersessions delete(AdmTlUsersessions admTlUsersessions)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		try {
			
			sqls.add(AdmTlUsersessionsSql.getDeleteSql(admTlUsersessionsSql.getUsseDbFields(), admTlUsersessions.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlUsersessions;
	}
	
}

