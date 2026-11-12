package com.akranta.tpm.dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.SheTlAccinvestigationdtlDao;
import com.akranta.tpm.dao.sql.SheTlAccinvestigationdtlSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class SheTlAccinvestigationdtlDaoImpl implements SheTlAccinvestigationdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public SheTlAccinvestigationdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlAccinvestigationdtl create(SheTlAccinvestigationdtl sheTlAccinvestigationdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlAccinvestigationdtlSql sheTlAccinvestigationdtlSql = new SheTlAccinvestigationdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			sheTlAccinvestigationdtl.setAindKeyid(dbActionTemplate.getSequenceNumber(SheTlAccinvestigationdtlSql.TBL_SHE_TL_ACCINVESTIGATIONDTL)); // set the sequnce number 
			sqls.add(SheTlAccinvestigationdtlSql.getInsertSql(sheTlAccinvestigationdtlSql.getAindDbFields(), sheTlAccinvestigationdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlAccinvestigationdtl;
	}
	
	public SheTlAccinvestigationdtl update(SheTlAccinvestigationdtl sheTlAccinvestigationdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlAccinvestigationdtlSql sheTlAccinvestigationdtlSql = new SheTlAccinvestigationdtlSql();
		try {

			sqls.add(SheTlAccinvestigationdtlSql.getUpdateSql(sheTlAccinvestigationdtlSql.getAindDbFields(), sheTlAccinvestigationdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlAccinvestigationdtl;
	}
	
	public List<SheTlAccinvestigationdtl> delete(List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlAccinvestigationdtlSql sheTlAccinvestigationdtlSql = new SheTlAccinvestigationdtlSql();
		try {
			for(SheTlAccinvestigationdtl sheTlAccinvestigationdtl:listSheTlAccinvestigationdtl){
				sqls.add(sheTlAccinvestigationdtlSql.getDeleteSql(sheTlAccinvestigationdtlSql.getAindDbFields(), sheTlAccinvestigationdtl.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return listSheTlAccinvestigationdtl;
	}

	
}

