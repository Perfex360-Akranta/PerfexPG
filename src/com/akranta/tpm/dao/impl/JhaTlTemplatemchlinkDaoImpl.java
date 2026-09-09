package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.JhaTlTemplatemchlinkDao;
import com.akranta.tpm.dao.sql.JhaTlTemplatemchlinkSql;
import com.akranta.tpm.model.JhaTlTemplatemchlink;

/* dao implementation */
public class JhaTlTemplatemchlinkDaoImpl implements JhaTlTemplatemchlinkDao {


	private DBActionTemplate dbActionTemplate; 

	public JhaTlTemplatemchlinkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlTemplatemchlink create(JhaTlTemplatemchlink jhaTlTemplatemchlink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlTemplatemchlinkSql jhaTlTemplatemchlinkSql = new JhaTlTemplatemchlinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			//jhaTlTemplatemchlink.setJtmlKeyid(dbActionTemplate.getSequenceNumber(JhaTlTemplatemchlinkSql.TBL_JHA_TL_TEMPLATEMCHLINK)); // set the sequnce number 
			sqls.add(JhaTlTemplatemchlinkSql.getInsertSql(jhaTlTemplatemchlinkSql.getJtmlDbFields(), jhaTlTemplatemchlink.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlTemplatemchlink;
	}
	
	public JhaTlTemplatemchlink update(JhaTlTemplatemchlink jhaTlTemplatemchlink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlTemplatemchlinkSql jhaTlTemplatemchlinkSql = new JhaTlTemplatemchlinkSql();
		try {

			sqls.add(JhaTlTemplatemchlinkSql.getUpdateSql(jhaTlTemplatemchlinkSql.getJtmlDbFields(), jhaTlTemplatemchlink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlTemplatemchlink;
	}
	
	public JhaTlTemplatemchlink delete(JhaTlTemplatemchlink jhaTlTemplatemchlink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlTemplatemchlinkSql jhaTlTemplatemchlinkSql = new JhaTlTemplatemchlinkSql();
		try {
			
			sqls.add(jhaTlTemplatemchlinkSql.getDeleteSql(jhaTlTemplatemchlinkSql.getJtmlDbFields(), jhaTlTemplatemchlink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlTemplatemchlink;
	}
	
}

