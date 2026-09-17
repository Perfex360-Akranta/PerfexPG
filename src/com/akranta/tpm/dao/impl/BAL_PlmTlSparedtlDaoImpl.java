package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.BAL_SprPckupFormBean;
import com.akranta.tpm.dao.BAL_PlmTlSparedtlDao;
//import com.akranta.tpm.dao.sql.OplTlMstSql;
//import com.akranta.tpm.dao.sql.OplTlPillarlinkSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlSparedtlSql;
//import com.akranta.tpm.model.OplTlMst;
//import com.akranta.tpm.model.OplTlPillarlink;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;
import com.akranta.tpm.service.api.BAL_PlmTlSparedtlServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;


/* dao implementation */
public class BAL_PlmTlSparedtlDaoImpl implements BAL_PlmTlSparedtlDao {


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	private BAL_PlmTlSparedtlServiceApi sparesapi;
	BAL_PlmTlSparedtlSql plmTlSparedtlSql =null;
	public BAL_PlmTlSparedtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		plmTlSparedtlSql = new BAL_PlmTlSparedtlSql();

	}
	public void BAL_PlmTlSparedtlDaoImplJwt(String JwtToken) 
	{
		try{
			sparesapi = new BAL_PlmTlSparedtlServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public BAL_PlmTlSparedtl delete(BAL_PlmTlSparedtl plmTlSparedtl)throws Exception {

		List<String> sqls = new ArrayList<String>();
		try 
		{
		//	String pspdKeyid=plmTlSparedtl.getPspdKeyid();
			sqls.add(BAL_PlmTlSparedtlSql.getDeleteSql(plmTlSparedtlSql.getPspdDbFields(), plmTlSparedtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}
		catch( Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlSparedtl;
	}
	

	public  List<BAL_PlmTlSparedtl> save(List<BAL_PlmTlSparedtl> sparesPkupList) throws Exception 
	{

		System.out.println("In Save Funct");
		List<String> sqls = new ArrayList<String>();

		if(sparesPkupList != null  )
		{	System.out.println("sparesPkupList="+sparesPkupList.size());
			for(BAL_PlmTlSparedtl newplmTlSparedtl:sparesPkupList)
			{
				System.out.println("Dao Impl newplmTlSparedtl.getPspdKeyid()----"+newplmTlSparedtl.getPspdKeyid());
				if( newplmTlSparedtl.getPspdKeyid() == null )
				{
					String keyid = dbActionTemplate.getSequenceNumber(BAL_PlmTlSparedtlSql.TBL_BAL_PLM_TL_SPAREDTL);
					newplmTlSparedtl.setPspdKeyid(keyid); // set the sequence number 
					sqls.add(BAL_PlmTlSparedtlSql.getInsertSql(plmTlSparedtlSql.getPspdDbFields(),newplmTlSparedtl.getSaveArray()));
				}	
				else
				{
					sqls.add(BAL_PlmTlSparedtlSql.getUpdateSql(plmTlSparedtlSql.getPspdDbFields(),newplmTlSparedtl.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//getSprCount();
			
		}
		System.out.println("return sparesPkupList"+sparesPkupList);
		return sparesPkupList;

	}
	
	public List<String[]> getSprPickup(String standardId) throws Exception 
	{
		try
		{
			System.out.println("standardId="+standardId);
	
			List<String> paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlSparedtlSql.getSparesPickupTbl();
			paramValues.add(standardId);
			System.out.println("String sql="+sql);
			List<String[]>SprPkupList = dbActionTemplate.getDataList(sql,paramValues);
			return SprPkupList;
			
		//    return dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETMULTISELECTSPARE", paramValues);
		}
		
		catch(Exception e)
		{
			System.out.println("Exception in get SprPkup dao impl"+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String[]> getSprNameSelectSpr(String sprmKeyid) {
	
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(sprmKeyid);
			String sql = BAL_PlmTlSparedtlSql.getSprNameSql();
			System.out.println("String sql="+sql);
			List<String[]> sprNameList = dbActionTemplate.getDataList(sql,paramValues);
			return sprNameList;
		}

		catch(Exception e)
		{
			System.out.println("Exception in Select NAme Spr dao impl"+e.getMessage());
		}
	
		return null;
	}


	@Override
	public List<String[]> getMultiSelectSpr() throws Exception
	{
		try
		{
			List<String > paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlSparedtlSql.getMultiSelectSprSql();
			System.out.println("String sql="+sql);
			List<String[]> multiSelectSprList = dbActionTemplate.getDataList(sql,paramValues);
			return multiSelectSprList;
		}

		catch(Exception e)
		{
			System.out.println("Exception in getMUltiSelect Spr dao impl"+e.getMessage());
		}
	
		return null;
	}

	@Override
	public List<String[]> getDeleteSpr(String pspdKeyId) throws Exception 
	{
		try
		{
			List<String > paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlSparedtlSql.getDeleteSprSql();
			System.out.println("String sql="+sql);
			paramValues.add(pspdKeyId);
			List<String[]> multiSelectSprList = dbActionTemplate.getDataList(sql,paramValues);
			return multiSelectSprList;
		}

		catch(Exception e)
		{
			System.out.println("Exception in getDeleteSpr Spr dao impl"+e.getMessage());
		}
		return null;
	}
	
	public  List<String[]> getDeleteAll(String StandardId)throws Exception
	{

		try
		{
			List<String > paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlSparedtlSql.getDeleteAllSql();
			System.out.println("String sql="+sql);
			System.out.println("StandardId"+StandardId);
			paramValues.add(StandardId);
			List<String[]> deleteAllSprList = dbActionTemplate.getDataList(sql,paramValues);
			return deleteAllSprList;
		}

		catch(Exception e)
		{
			System.out.println("Exception in getDeleteSpr Spr dao impl"+e.getMessage());
		}
		return null;
	}
	

	@Override	
	public List<String[]> getAllSprCount(List<BAL_PlmTlSparedtl> spareCountList,String fnlnParentid) throws Exception 
	{
		try
		{
			System.out.println("spareCountList="+spareCountList.size());
			System.out.println("fnlnParentid="+fnlnParentid);
			
		//	System.out.println("fnlnParentid in dao impl="+fnlnParentid);
		//	System.out.println("sprPckupFormBean.getFnLnOriginalId()"+sprPckupFormBean.getFnLnOriginalId());
			List<String > paramValues = new ArrayList<String>();
			
			paramValues.add("SPR");//Element Type
			String sparesIDs = "";
			for( BAL_PlmTlSparedtl plmSpareCount: spareCountList)
			{
				sparesIDs += "'"+ plmSpareCount.getPspdSpareid() +"',"; 
			}
			sparesIDs = sparesIDs.substring(0,sparesIDs.length()-1);
			System.out.println("sparesIDs"+sparesIDs);
			paramValues.add(sparesIDs);
			paramValues.add(fnlnParentid);//vPARENTID
			return dbActionTemplate.processFunctionCalls("GEN_PC_COMMONFUNCTIONS.GEN_FN_GETCOUNTFROMLAYOUT", paramValues);
	}
		
		catch(Exception e)
		{
			System.out.println("Exception in get SprPkup dao impl"+e.getMessage());
		}
		return null;
	}

}
	
	


