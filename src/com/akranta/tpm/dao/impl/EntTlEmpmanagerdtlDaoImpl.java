package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlEmpmanagerdtlDao;
import com.akranta.tpm.dao.sql.EntTlEmpmanagerdtlSql;
import com.akranta.tpm.dao.sql.EntTlEmpmanagermstSql;
//import com.akranta.tpm.dao.sql.GenTlMouldmstSql;
import com.akranta.tpm.model.EntTlEmpmanagerdtl;
import com.akranta.tpm.model.EntTlEmpmanagermstModel;
import com.akranta.tpm.utils.CommonMessage;

/* dao implementation */
public class EntTlEmpmanagerdtlDaoImpl implements EntTlEmpmanagerdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlEmpmanagerdtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlEmpmanagerdtl create(EntTlEmpmanagerdtl entTlEmpmanagerdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlEmpmanagerdtlSql entTlEmpmanagerdtlSql = new EntTlEmpmanagerdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlEmpmanagerdtl.setEemdKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpmanagerdtlSql.TBL_ENT_TL_EMPMANAGERDTL)); // set the sequnce number 
			sqls.add(EntTlEmpmanagerdtlSql.getInsertSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlEmpmanagerdtl;
	}
	
	public EntTlEmpmanagerdtl update(EntTlEmpmanagerdtl entTlEmpmanagerdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlEmpmanagerdtlSql entTlEmpmanagerdtlSql = new EntTlEmpmanagerdtlSql();
		try {

			sqls.add(EntTlEmpmanagerdtlSql.getUpdateSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlEmpmanagerdtl;
	}
	
	public EntTlEmpmanagerdtl delete(EntTlEmpmanagerdtl entTlEmpmanagerdtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlEmpmanagerdtlSql entTlEmpmanagerdtlSql = new EntTlEmpmanagerdtlSql();
		try {
			
			sqls.add(EntTlEmpmanagerdtlSql.getDeleteSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlEmpmanagerdtl;
	}
	public EntTlEmpmanagerdtl select(String manIdField) throws Exception
	{
		try
		{
			
			EntTlEmpmanagerdtl entTlEmpmanagerdtl = new EntTlEmpmanagerdtl();
			String sql = null;			
			sql = EntTlEmpmanagerdtlSql.selectSql();				
			CommonMessage.debugMsg("DAO SQL : "+sql);
			
			//List<String[]> Result =  dbActionTemplate.getDataList(sql);
			Object [] args =  new Object [] { manIdField };
			
			entTlEmpmanagerdtl.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			//CommonMessage.debugMsg("LOCN  :"+entTlEmpmanagerdtl.getEemdEmpmKeyid());
			//CommonMessage.debugMsg("LOCN  :"+entTlEmpmanagerdtl.getEemdMailid());
			return  entTlEmpmanagerdtl;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	public List<String[]> getEmployeeDet( String ManIdField,String EmpIdFeild)throws Exception
	{
		try
		{
			String sql = EntTlEmpmanagerdtlSql.getEmployeeDetsql(ManIdField,EmpIdFeild);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String[]> getEmployeeDetView( )throws Exception
	{
		try
		{
			String sql = EntTlEmpmanagerdtlSql.getEmployeeDetViewsql();
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}

