package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_GenVwToolcategoryDao;
import com.akranta.tpm.dao.sql.BAL_GenVwToolcategorySql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.BAL_GenVwToolcategory;

/* dao implementation */
public class BAL_GenVwToolcategoryDaoImpl implements BAL_GenVwToolcategoryDao {


	
	private DBActionTemplate dbActionTemplate; 

	public BAL_GenVwToolcategoryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_GenVwToolcategory create(BAL_GenVwToolcategory genVwToolcategory) 	throws Exception {
		return genVwToolcategory;

	/*	List<String> sqls = new ArrayList<String>();  sqls for execution  
		GenVwToolcategorySql genVwToolcategory = new GenVwToolcategorySql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genVwToolcategory.setGen__keyid(dbActionTemplate.getSequenceNumber(GenVwToolcategorySql.TBL_GEN_VW_TOOLCATEGORY)); // set the sequnce number 
			sqls.add(GenVwToolcategorySql.getInsertSql(genVwToolcategorySql.getGen_DbFields(), genVwToolcategory.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genVwToolcategory;*/
	}
	
	public BAL_GenVwToolcategory update(BAL_GenVwToolcategory genVwToolcategory)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenVwToolcategorySql genVwToolcategorySql = new BAL_GenVwToolcategorySql();
		try {

			//sqls.add(GenVwToolcategorySql.getUpdateSql(genVwToolcategory.getWwmsDbFields(), genVwToolcategory.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genVwToolcategory;
	}
	
	public BAL_GenVwToolcategory delete(BAL_GenVwToolcategory genVwToolcategory)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		/*GenVwToolcategorySql genVwToolcategory = new GenVwToolcategorySql();
		try {
			
			sqls.add(GenVwToolcategory.getDeleteSql(genVwToolcategory.getGen_DbFields(), genVwToolcategory.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}*/
		return genVwToolcategory;
	}

	@Override
	public List<BAL_GenVwToolcategory> getAllTool(BAL_GenVwToolcategory genVwToolcategory) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select elementid,parentid,displaycode,elementtype from "+ TableNames.TBL_GEN_VW_TOOLCATEGORY;
				   sql +=	"  where elementtype ='"+genVwToolcategory.getElementtype() +"'";
				  if(genVwToolcategory.getParentid()!= null){
					  sql += "AND parentid ='"+ genVwToolcategory.getParentid()+"'";
				  }
			System.out.println(" sql " + sql);
			List resultList = dbActionTemplate.getDataList(sql);
			System.out.println(" resultList size " + resultList.size());
			
			return fillTool(resultList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	private List<BAL_GenVwToolcategory> fillTool(List<String []> resultList) throws SQLException
	{
		
		   List<BAL_GenVwToolcategory> menus = new ArrayList<BAL_GenVwToolcategory>();
		   for( String [] row : resultList )
		   {
			   BAL_GenVwToolcategory tool = new BAL_GenVwToolcategory();
			   
			   tool.setElementid(row[0]);
			   tool.setParentid(row[1]);
			   tool.setDisplaycode(row[2]);
			   tool.setElementtype(row[3]);			
				
				menus.add(tool);
	
			   
		   }
  		  return menus;
	 }

}

