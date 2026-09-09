/*Created By : Siddharth.A*/
package com.akranta.tpm.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.akranta.tpm.dao.KznTlCategorymstDao;
import com.akranta.tpm.dao.sql.KznTlCategorymstSql;
import com.akranta.tpm.dao.sql.KznTlSubcategorymstSql;
import com.akranta.tpm.model.KznTlCategorymst;
import com.akranta.tpm.model.KznTlSubcategorymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlCategorymstDaoImpl implements KznTlCategorymstDao {


	private DBActionTemplate dbActionTemplate; 
	private KznTlSubcategorymstSql kznTlSubcategorymstSql;
	private KznTlCategorymstSql kznTlCategorymstSql ;

	public KznTlCategorymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		kznTlSubcategorymstSql =new KznTlSubcategorymstSql();
		kznTlCategorymstSql = new KznTlCategorymstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	/** ----- FOR CATEGORY---------------- **/	
	public KznTlCategorymst create(KznTlCategorymst kznTlCategorymst) 	throws Exception
	{

		List<String> sqls = new ArrayList<String>(); 
		try
		{
			CommonMessage.debugMsg("Inside dao impl");
			kznTlCategorymst.setKctmKeyid(dbActionTemplate.getSequenceNumber(KznTlCategorymstSql.TBL_KZN_TL_CATEGORYMST, 10, "KCT", "", "")); // set the sequnce number 
			sqls.add(KznTlCategorymstSql.getInsertSql(kznTlCategorymstSql.getKctmDbFields(), kznTlCategorymst.getSaveArray())); // add insert sql for master table
			CommonMessage.debugMsg("sqls in dao impl="+sqls);
			dbActionTemplate.executeStatements(sqls); 
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlCategorymst;
	}
	
	public KznTlCategorymst update(KznTlCategorymst kznTlCategorymst)	throws Exception 
	{ 
		CommonMessage.debugMsg("Inside update dao impl");
		List<String> sqls = new ArrayList<String>();
		
		try 
		{
			CommonMessage.debugMsg("kznTlCategorymst.getKctmKeyid()"+kznTlCategorymst.getKctmKeyid());
			CommonMessage.debugMsg("kznTlCategorymst.getKctmKeyid() trim="+kznTlCategorymst.getKctmKeyid().trim());
			
			
			sqls.add(KznTlCategorymstSql.getUpdateSql(kznTlCategorymstSql.getKctmDbFields(), kznTlCategorymst.getSaveArray()));
			CommonMessage.debugMsg("KctmKeyid  in dao iml="+sqls);
			dbActionTemplate.executeStatements(sqls);
			return kznTlCategorymst;
		} 
		catch (Exception e) 
		{
			throw new Exception(e.getMessage());
		}
	}
	
/** ----- FOR SUBCATEGORY---------------- **/	
	public KznTlSubcategorymst create(KznTlSubcategorymst kznTlSubcategorymst) 	throws Exception 
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try{
		
			kznTlSubcategorymst.setKscmKeyid(dbActionTemplate.getSequenceNumber(KznTlSubcategorymstSql.TBL_KZN_TL_SUBCATEGORYMST,10, "KSC", "", "")); // set the sequnce number 
			sqls.add(KznTlSubcategorymstSql.getInsertSql(kznTlSubcategorymstSql.getKscmDbFields(), kznTlSubcategorymst.getSaveArray())); // add insert sql for master table
		
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlSubcategorymst;
	}
	
	public KznTlSubcategorymst update(KznTlSubcategorymst kznTlSubcategorymst)	throws Exception 
	{ 
		List<String> sqls = new ArrayList<String>();
		try {

			sqls.add(KznTlSubcategorymstSql.getUpdateSql(kznTlSubcategorymstSql.getKscmDbFields(), kznTlSubcategorymst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlSubcategorymst;
	}
/**-------------------------------------- **/
	public List<String[]> getPillarNames( ) throws Exception 
	{
		List<String[]> pillarList=null;
			try
			{
				String sql = KznTlCategorymstSql.getKznPillarSql();
				CommonMessage.debugMsg("Pillar sql=" +sql);
				pillarList = dbActionTemplate.getDataList(sql);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Exception in getPillar dao impl"+e.getMessage());
			}
			return pillarList;
	}

	@Override
	public List<Object> getKznCategories(String categoryKeyid) throws Exception 
	{
		try
		{
			CommonMessage.debugMsg("categoryKeyid in dao impl"+categoryKeyid);
			String sql = KznTlCategorymstSql.getKznCategorySql(categoryKeyid);
			CommonMessage.debugMsg("sql ="+sql);
			
			Object [] args = new Object  []{ categoryKeyid};
			
			KznTlCategorymst kznTlCategorymst = new KznTlCategorymst();
			//categoryList = dbActionTemplate.getDataArr(sql, args) 
			List<Object> categoryList = (List<Object>) dbActionTemplate.getDataList(sql, kznTlCategorymst);
			return categoryList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg(e.getMessage());	
		}
		return null;
		
	}

	/** FOR DELETING CATEGORY **/
	@Override
	public KznTlCategorymst deleteCategoryNames(KznTlCategorymst kznTlCategorymst) throws Exception 
	{
		try
		{
			String sql = KznTlCategorymstSql.deleteKznCategorySql();
			CommonMessage.debugMsg("String sql = "+sql);
			CommonMessage.debugMsg("kznTlCategorymst.getKctmKeyid()="+kznTlCategorymst.getKctmKeyid());
			Object args [] = new Object [] { kznTlCategorymst.getKctmKeyid() };
			int  type [] = new int  [] { Types.VARCHAR };
			dbActionTemplate.executeStatement(sql, args,type);
		
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Delete Category dao impl: "+e.getMessage());
			e.getStackTrace();
		}
		return kznTlCategorymst;
	}

	/** ------------------------------ **/
	@Override
	public List<Object> getSubCategory(String ksmKeyId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlCategorymstSql.getKznSubCategorySql(ksmKeyId);
			CommonMessage.debugMsg("Sub Category sql=" +sql);
			CommonMessage.debugMsg("ksmKeyId ="+ksmKeyId);
	
			Object [] args = new Object  []{ ksmKeyId};
			
			KznTlSubcategorymst kznTlSubcategorymst = new KznTlSubcategorymst();
			//categoryList = dbActionTemplate.getDataArr(sql, args) 
			List<Object> subCatList = (List<Object>) dbActionTemplate.getDataList(sql, kznTlSubcategorymst);
			
			return subCatList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getSubCategory dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public KznTlSubcategorymst deleteSubcategoryNames(KznTlSubcategorymst kznTlSubcategorymst) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> sqls = new ArrayList<String>();
			
			sqls.add(KznTlSubcategorymstSql.getUpdateSubSql(kznTlSubcategorymstSql.getKscmDbFields(), kznTlSubcategorymst.getSaveArray()));
			sqls.add(KznTlSubcategorymstSql.getDeleteSql(kznTlSubcategorymstSql.getKscmDbFields(), kznTlSubcategorymst.getSaveArray()));
			
			
			dbActionTemplate.executeStatements(sqls);
	
			
		
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Delete Subcategory dao impl: "+e.getMessage());
			e.getStackTrace();
		}
		return kznTlSubcategorymst;
	}

		
}









