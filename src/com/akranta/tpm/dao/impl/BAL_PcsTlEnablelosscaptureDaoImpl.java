
package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlEnablelosscaptureDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.OplTlMstSql;
import com.akranta.tpm.dao.sql.PcsTlEnablelosscaptureSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlSparedtlSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlUnscheduledactmstSql;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;
//import com.akranta.tpm.model.BAL_PlmTlUnscheduledactmst;
import com.akranta.tpm.utils.CommonFunctions;
//import com.sun.istack.internal.Pool.Impl;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.service.api.BAL_PcsTlEnablelosscaptureServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

/* dao implementation */
public class BAL_PcsTlEnablelosscaptureDaoImpl implements BAL_PcsTlEnablelosscaptureDao {


	private DBActionTemplate dbActionTemplate; 
	private PcsTlEnablelosscaptureSql pcsTlEnablelosscaptureSql;
	private BAL_PcsTlEnablelosscaptureServiceApi EnablelosscaptureServiceApi;
	FunctionCallApi fnCallApi;
	

	public BAL_PcsTlEnablelosscaptureDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlEnablelosscaptureSql = new PcsTlEnablelosscaptureSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//jwt
	public void BAL_PcsTlEnablelosscaptureDaoImplJwt(String jwtToken) { 
		 try { 
			 EnablelosscaptureServiceApi = new BAL_PcsTlEnablelosscaptureServiceApi(jwtToken); 
			 fnCallApi = new FunctionCallApi(jwtToken); 

			 
		 } 
		 catch (Exception e) { 
		 e.printStackTrace(); 
		 } 
		 }

	public PcsTlEnablelosscapture create(PcsTlEnablelosscapture pcsTlEnablelosscapture) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try
		{
			pcsTlEnablelosscapture.setPelcKeyid(dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE)); // set the sequnce number 
			sqls.add(PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(), pcsTlEnablelosscapture.getSaveArray())); // add insert sql for master table

			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlEnablelosscapture;
	}
	
	public PcsTlEnablelosscapture update(PcsTlEnablelosscapture pcsTlEnablelosscapture)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlEnablelosscaptureSql pcsTlEnablelosscaptureSql = new PcsTlEnablelosscaptureSql();
		try 
		{
			sqls.add(PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(), pcsTlEnablelosscapture.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		}
		
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlEnablelosscapture;
	}
	
	public PcsTlEnablelosscapture delete(PcsTlEnablelosscapture pcsTlEnablelosscapture)	throws Exception 
	{
		List<String> sqls = new ArrayList<String>();
		PcsTlEnablelosscaptureSql pcsTlEnablelosscaptureSql = new PcsTlEnablelosscaptureSql();
		try 
		{
			sqls.add(PcsTlEnablelosscaptureSql.getDeleteSql(pcsTlEnablelosscaptureSql.getPelcDbFields(), pcsTlEnablelosscapture.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}
		
		catch( Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlEnablelosscapture;
	}

//	@Override
//	public List<String[]> getAllPcsEnblDsblMCH(String factId,String sectId,String cellId,String flid) throws Exception  
//	{
//		System.out.println("Inside getAllPcsEnblDsblMCH  Dao Impl..........");
//		try
//		{
//			List<String > paramValues = new ArrayList<String>();	
//		   //paramValues.add(factId);
//			// paramValues.add(sectId);
//			//paramValues.add(cellId);
//			//paramValues.add(cellId);
//			//paramValues.add(cellId);
//			 System.out.println("opllOplid"+factId+"dffdf="+sectId+"cell="+cellId);
//		     CommonFunctions.debugMsg("The SectionId"+sectId);
//		     String sql = PcsTlEnablelosscaptureSql.getPcsEbDbMCHSql(sectId);	
//			 System.out.println("String sql="+sql);
//		 	 List<String[]> studentList =dbActionTemplate.getDataList(sql,paramValues);
//			 return studentList;
//		}
//		catch(Exception e)
//		{
//			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
//		}
//		return null;
//	}
	
	//elumalai
	@Override
	public List<String[]> getAllPcsEnblDsblMCH(String factId, String sectId, String cellId, String flid) throws Exception
	{
	    System.out.println("Inside getAllPcsEnblDsblMCH Dao Impl..........");
	    try
	    {
	        List<String> paramValues = new ArrayList<String>();

	        // old code passed sectId into getPcsEbDbMCHSql(), so sectId is the FLID
	        String condParms = "";
	        if (UIUtils.isValidKeyId(sectId))
	            condParms += "FLID=" + sectId + ";";
	        String commonParams = "";

	        paramValues.add(condParms);
	        paramValues.add(commonParams);
	        CommonMessage.debugMsg("The Param Values Are:::" + paramValues);

	        // returns 3 header rows + data rows, same as GEN_FN_NEWMOMATTENDANCERPT
	      // return dbActionTemplate.processFunctionCallsWithColHeaders("PCS_FN_ENBDSBMCHGRID", paramValues);
	       return fnCallApi.callFunction("pcs_fn_enbdsbcellgrid_sb", paramValues,2,true);
	    }
	    catch (Exception e)
	    {
	        System.out.println("Exception in getAllPcsEnblDsblMCH dao impl " + e.getMessage());
	    }
	    return null;
	}
	
	//elumalai
	@Override
	public List<String[]> getPcsEnblDsblMCHWithHeaders(String sectId) throws Exception
	{
	    List<String> paramValues = new ArrayList<String>();
	    String condParms = "";
	    if (UIUtils.isValidKeyId(sectId))
	        condParms += "FLID=" + sectId + ";";
	    String commonParams = "";

	    paramValues.add(condParms);
	    paramValues.add(commonParams);

	    return dbActionTemplate.processFunctionCallsWithColHeaders("PCS_FN_ENBDSBMCHGRID", paramValues);
	}

	@Override
	public List<String[]> getAllPcsEnblDsblCELL(String factId,String sectId,String cellId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside getAllPcsEnblDsblCELL  Dao Impl..........");
		
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(factId);
			paramValues.add(sectId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			System.out.println("opllOplid"+factId+"dffdf="+sectId+"cell="+cellId);
			String sql = PcsTlEnablelosscaptureSql.getPcsEbDbCELLSql();
			System.out.println("String sql="+sql);
			List<String[]> studentList = dbActionTemplate.getDataList(sql,paramValues);
			return studentList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getAllPcsEnblDsblSECT(String factId,String sectId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside getAllPcsEnblDsblSECT  Dao Impl..........");
		
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(factId);
			paramValues.add(sectId);
			System.out.println("Parameters"+factId+"dffdf="+sectId);
			String sql = PcsTlEnablelosscaptureSql.getPcsEbDbSECTSql();
			System.out.println("String sql="+sql);
			List<String[]> studentList = dbActionTemplate.getDataList(sql,paramValues);
			return studentList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getAllSectCount(String sectId) {
		// TODO Auto-generated method stub
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(sectId);
	
			System.out.println("Parameters"+sectId);
			String sql = PcsTlEnablelosscaptureSql.getSectCnt();
			System.out.println("String sql="+sql);
			List<String[]> sectCntList = dbActionTemplate.getDataList(sql,paramValues);
			System.out.println("sectCntList="+sectCntList);
			return sectCntList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblSectCnt dao impl"+e.getMessage());
		}
		return null;
	}
	

	public List<BAL_PcsTlEnablelosscapture> save(List<BAL_PcsTlEnablelosscapture> pcsTlEnablelosscaptureList) throws Exception
	{

		System.out.println("In Save Funct");
		List<String> sqls = new ArrayList<String>();

		if(pcsTlEnablelosscaptureList != null && pcsTlEnablelosscaptureList.size() > 0  )
		{	System.out.println("sparesPkupList="+pcsTlEnablelosscaptureList.size());
			for(BAL_PcsTlEnablelosscapture newPcsTlEnablelosscapture:pcsTlEnablelosscaptureList)
			{
				System.out.println("Dao Impl newplmTlSparedtl.getPspdKeyid()----"+newPcsTlEnablelosscapture.getPelcKeyid());
				System.out.println("newPcsTlEnablelosscapture.getPelcIspcsenabled() before---"+newPcsTlEnablelosscapture.getPelcIspcsenabled());

				if(newPcsTlEnablelosscapture.getPelcIspcsenabled() != null && newPcsTlEnablelosscapture.getPelcIspcsenabled()!="")
				{	
					System.out.println("newPcsTlEnablelosscapture.getPelcIspcsenabled()---"+newPcsTlEnablelosscapture.getPelcIspcsenabled());
					if( newPcsTlEnablelosscapture.getPelcKeyid() == null )
					{
						String keyid = dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE);
						System.out.println("generated keyid=[" + keyid + "]");
						newPcsTlEnablelosscapture.setPelcKeyid(keyid); // set the sequence number 
						sqls.add(PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
					}	
					else
					{
						sqls.add(PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
					}
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}
		System.out.println("return sparesPkupList"+pcsTlEnablelosscaptureList);
		return pcsTlEnablelosscaptureList;

	}
	
	/*public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> pcsTlEnablelosscaptureList,
			PcsEnableDisableFormBean pcsEnableDisableFormBean) throws Exception
	{

		System.out.println("In Save Funct");
		List<String> sqls = new ArrayList<String>();

		if(pcsTlEnablelosscaptureList != null && pcsTlEnablelosscaptureList.size() > 0  )
		{	System.out.println("sparesPkupList="+pcsTlEnablelosscaptureList.size());
		
		//	if (pcsEnableDisableFormBean.getForCell().equals("Y")) {
				//for(PcsTlEnablelosscapture newPcsTlEnablelosscapture:pcsTlEnablelosscaptureList)
				for(int i =0;i<pcsTlEnablelosscaptureList.size();i++)
				{
					PcsTlEnablelosscapture pcsTlEnablelosscapture = (PcsTlEnablelosscapture)pcsTlEnablelosscaptureList.get(i);
			        System.out.println("In Save Funct"+pcsTlEnablelosscapture.getPelcFlid());
					StringBuilder sb1= new StringBuilder();
					sb1.append("  UPDATE PCS_TL_ENABLELOSSCAPTURE  SET PELC_ACTIVE='Y' ");
					sb1.append("	WHERE PELC_TYPE='S' AND PELC_FLID='" +pcsTlEnablelosscapture.getPelcFlid()+"' "); 
					sqls.add(sb1.toString());
					CommonFunctions.debugMsg("Update function called"+sb1.toString());
					StringBuilder sb= new StringBuilder();
					sb.append("  SELECT MAX(PELC_KEYID) AS PELC_KEYID FROM PCS_TL_ENABLELOSSCAPTURE ");
					sb.append("	WHERE PELC_TYPE='S' "); 
					sb.append("	AND PELC_FLID='" +pcsTlEnablelosscaptureList.get(i).getPelcFlid() +"'");
					//sb.append("	AND PELC_FLID='" +pcsTlEnablelosscaptureList.getPelcFlid()+"'");
					CommonFunctions.debugMsg("sb.sql===="+sb.toString());
					String keyId = dbActionTemplate.getSingleValue(sb.toString());
					CommonFunctions.debugMsg("keyId===="+keyId);
					if (!UIUtils.isValidKeyId(keyId)) {
						keyId = dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE);
						CommonFunctions.debugMsg("****KeyID******"+keyId);
						pcsTlEnablelosscapture.setPelcKeyid(keyId); // set the sequence number 
						CommonFunctions.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),pcsTlEnablelosscapture.getSaveArray()));
						sqls.add(PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),pcsTlEnablelosscapture.getSaveArray()));
					}
					else {
						pcsTlEnablelosscapture.setPelcKeyid(keyId); // set the sequence number 
						CommonFunctions.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),pcsTlEnablelosscapture.getSaveArray()));
						sqls.add(PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),pcsTlEnablelosscapture.getSaveArray()));
						
					}	
				}
				
			}
			else {
				
				StringBuilder sb= new StringBuilder();
				/*
				sb.append("  UPDATE PCS_TL_ENABLELOSSCAPTURE  SET PELC_ACTIVE='N' ");
				sb.append("	WHERE PELC_TYPE='C' AND PELC_CELLID='" +pcsTlEnablelosscaptureList.get(0).getPelcCellid() +"' "); 
				sb.append("	AND PELC_LINEID ='" +pcsTlEnablelosscaptureList.get(0).getPelcLineid() +"' AND PELC_FACTORYID='" +pcsTlEnablelosscaptureList.get(0).getPelcFactoryid() +"' ");
				*/
				
			/*	sb.append("  UPDATE PCS_TL_ENABLELOSSCAPTURE  SET PELC_ACTIVE='Y' ");
				sb.append("	WHERE PELC_TYPE='S' AND PELC_FLID='" +pcsTlEnablelosscaptureList.get(0).getPelcFlid() +"' "); 
				
				
				sqls.add(sb.toString());
				
				for(PcsTlEnablelosscapture newPcsTlEnablelosscapture:pcsTlEnablelosscaptureList)
				{
					
					System.out.println("Dao Impl newplmTlSparedtl.getPspdKeyid()----"+newPcsTlEnablelosscapture.getPelcKeyid());
					if(newPcsTlEnablelosscapture.getPelcIspcsenabled() != null && newPcsTlEnablelosscapture.getPelcIspcsenabled()!="")
					{	
						System.out.println("newPcsTlEnablelosscapture.getPelcIspcsenabled()---"+newPcsTlEnablelosscapture.getPelcIspcsenabled());
						if( newPcsTlEnablelosscapture.getPelcKeyid() == null )
						{
							String keyid = dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE);
							newPcsTlEnablelosscapture.setPelcKeyid(keyid); // set the sequence number 
							CommonFunctions.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
							sqls.add(PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
						}	
						else
						{
							sqls.add(PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
						}
					}
				}
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		System.out.println("return sparesPkupList"+pcsTlEnablelosscaptureList);
		return pcsTlEnablelosscaptureList;

	}  */
	
	

	@Override
	public List<String[]> getFactory(GridParams gridParams,String lossId,String phenID) throws Exception 
	{
		try
		{
			String sql=PcsTlEnablelosscaptureSql.getFactorySql(lossId,phenID);
			String count="select count(*) from("+sql+")";
			String rowCount=dbActionTemplate.getSingleValue(count);
			long counts=Long.parseLong(rowCount);
			gridParams.setTotalRecordCnt(counts);
			List<String> params= new ArrayList<String>();
			params.add(gridParams.getFromRow());
			params.add(gridParams.getToRow());
			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
			return result;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}


	@Override
	public List<String[]> getJH(GridParams gridParams,String lossId) throws Exception 
	{
		try
		{
			String sql=PcsTlEnablelosscaptureSql.getJHSql(lossId);
			String count="select count(*) from("+sql+")";
			String rowCount=dbActionTemplate.getSingleValue(count);
			long counts=Long.parseLong(rowCount);
			gridParams.setTotalRecordCnt(counts);
			List<String> params= new ArrayList<String>();
			params.add(gridParams.getFromRow());
			params.add(gridParams.getToRow());
			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
			return result;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getPhenomena(GridParams gridParams,String phenId,String lossId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql=PcsTlEnablelosscaptureSql.gePhenomenaSql(phenId,lossId);
			String count="select count(*) from("+sql+")";
			String rowCount=dbActionTemplate.getSingleValue(count);
			long counts=Long.parseLong(rowCount);
			gridParams.setTotalRecordCnt(counts);
			List<String> params= new ArrayList<String>();
			params.add(gridParams.getFromRow());
			params.add(gridParams.getToRow());
			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
			return result;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String[]> getLossNames(GridParams gridParams,String jhId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql=PcsTlEnablelosscaptureSql.geLossSql(jhId);
			String count="select count(*) from("+sql+")";
			String rowCount=dbActionTemplate.getSingleValue(count);
			long counts=Long.parseLong(rowCount);
			gridParams.setTotalRecordCnt(counts);
			List<String> params= new ArrayList<String>();
			params.add(gridParams.getFromRow());
			params.add(gridParams.getToRow());
			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
			return result;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public PcsTlLossphenomenamst getFactoryLossPhenomenaLink(PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

