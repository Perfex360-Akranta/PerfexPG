/*Created By : Siddharth.A*/
package com.akranta.tpm.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.OplTlCategorymstDao;
import com.akranta.tpm.dao.sql.OplTlCategorymstSql;
import com.akranta.tpm.dao.sql.OplTlMstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlCategorymst;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.utils.CommonMessage;
/* dao implementation */
public class OplTlCategorymstDaoImpl implements OplTlCategorymstDao {


	private DBActionTemplate dbActionTemplate; 

	public OplTlCategorymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public OplTlCategorymst create(OplTlCategorymst oplTlCategorymst) 	throws Exception
	{

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		OplTlCategorymstSql oplTlCategorymstSql = new OplTlCategorymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try
		{CommonMessage.debugMsg("Inside dao impl");
		
			oplTlCategorymst.setOplcKeyid(dbActionTemplate.getSequenceNumber(OplTlCategorymstSql.TBL_OPL_TL_CATEGORYMST)); // set the sequnce number 
			sqls.add(OplTlCategorymstSql.getInsertSql(oplTlCategorymstSql.getOplcDbFields(), oplTlCategorymst.getSaveArray())); // add insert sql for master table
			CommonMessage.debugMsg("sqls in dao impl="+sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return oplTlCategorymst;
	}
	
	public OplTlCategorymst update(OplTlCategorymst oplTlCategorymst)	throws Exception 
	{ 
		
		List<String> sqls = new ArrayList<String>();
		OplTlCategorymstSql oplTlCategorymstSql = new OplTlCategorymstSql();
		try 
		{
			sqls.add(OplTlCategorymstSql.getUpdateSql(oplTlCategorymstSql.getOplcDbFields(), oplTlCategorymst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} 
		catch (Exception e) 
		{
			throw new Exception(e.getMessage());
		}
		
		return oplTlCategorymst;
	}
	

	public List<String[]> getPillarNames(CommonFilter commonfilter) throws Exception {
		List<String[]> pillarList=null;
			try
			{
				String sql = OplTlCategorymstSql.getOplPillarSql();
				pillarList = dbActionTemplate.getDataList(sql);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Exception in getPillar dao impl"+e.getMessage());
			}
			return pillarList;
		}

	@Override
	public List<Object> getOplCategories(String categoryKeyid) throws Exception 
	{
			CommonMessage.debugMsg("categoryKeyid in dao impl"+categoryKeyid);
			String sql = OplTlCategorymstSql.getOplCategorySql(categoryKeyid);
			Object [] args = new Object  []{ categoryKeyid};
			
			OplTlCategorymst oplTlCategorymst = new OplTlCategorymst();
			//categoryList = dbActionTemplate.getDataArr(sql, args) 
			List<Object> categoryList = (List<Object>) dbActionTemplate.getDataList(sql, oplTlCategorymst);
			return categoryList;
		
		
	}

	@Override
	public OplTlCategorymst deleteCategoryNames(OplTlCategorymst oplTlCategorymst) throws Exception 
	{
	
		try
		{
			//CommonMessage.debugMsg("categoryKeyid in dao impl="+categoryKeyid);
			
			String sql = OplTlCategorymstSql.deleteOplCategorySql();
			CommonMessage.debugMsg("String sql = "+sql);
			CommonMessage.debugMsg("oplTlCategorymst.getOplcKeyid()="+oplTlCategorymst.getOplcKeyid());
			Object args [] = new Object [] { oplTlCategorymst.getOplcKeyid() };
			int  type [] = new int  [] { Types.VARCHAR };
			dbActionTemplate.executeStatement(sql, args,type);
		
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Delete Category dao impl: "+e.getMessage());
			e.getStackTrace();
		}
		return oplTlCategorymst;
	}
	
}

