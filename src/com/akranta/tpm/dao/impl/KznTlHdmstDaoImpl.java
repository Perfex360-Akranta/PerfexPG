package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KznTlHdmstDao;
import com.akranta.tpm.dao.sql.KznTlHdmstSql;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


/* dao implementation */
public class KznTlHdmstDaoImpl implements KznTlHdmstDao {


	private DBActionTemplate dbActionTemplate; 
	private KznTlHdmstSql kznTlHdmstSql;
	String hdcellid;
	public KznTlHdmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		kznTlHdmstSql = new KznTlHdmstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public KznTlHdmst delete(KznTlHdmst kznTlHdmst)	throws Exception {
		List<String> sqls = new ArrayList<String>();
		try {
			CommonMessage.debugMsg(" Inside delete dao impl Top 1 :: ");
			sqls.add(KznTlHdmstSql.getDeleteSql(kznTlHdmstSql.getKhdmDbFields(), kznTlHdmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznTlHdmst;
	}
	
	@Override
	public List<KznTlHdmst> deletekzn(List<KznTlHdmst> kznHdmstList)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		KznTlHdmst kznTlHdmst = new KznTlHdmst();
		try {
			
				CommonMessage.debugMsg(" Inside delete dao impl Top 1 :: ");
				
				for(int i=0;i<kznHdmstList.size();i++){
					CommonMessage.debugMsg(" Inside delete dao impl Top 1 :: "+kznHdmstList.get(i).getKhdmKeyid());
					kznTlHdmst.setKhdmKeyid(kznHdmstList.get(i).getKhdmKeyid());
					sqls.add(KznTlHdmstSql.getDeleteSql(kznTlHdmstSql.getKhdmDbFields(), kznTlHdmst.getSaveArray()));
				}
			
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznHdmstList;
	}

	public List<String[]> delete(String kaizenId)	throws Exception 
	{
		try 
		{
		//	String sql = KznTlHdmstSql.getDeleteSql(kaizenId);
		//	CommonMessage.debugMsg(sql);
		//	return dbActionTemplate.getDataList(sql);
			
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return null;
	}
	public String getcellid(String cellid)throws Exception{
		 hdcellid=dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID","FNLN_ORIGINALID",cellid);
		CommonMessage.debugMsg("hdcellid::"+hdcellid);
		return hdcellid;
	}

	@Override
	public List<String[]> select(String khdmKaizenid) throws Exception
	{
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(khdmKaizenid);
			CommonMessage.debugMsg("khdmKaizenid"+khdmKaizenid);
			String sql = KznTlHdmstSql.selectSql();
			CommonMessage.debugMsg("String sql="+sql);
			List<String[]> selectValuesList = dbActionTemplate.getDataList(sql,paramValues);
			
			return selectValuesList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Error in Dao Impl "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String[]> getAllKznHdDtls(String kaizenId ,String sectId,String mchId,String cellId, String dmtid, String dmtlevel) throws Exception 
	{
		// TODO Auto-generated method stub
		List<String[]> kznHDScanList=null;
		List<String> paramValues = new ArrayList<String>();
		String sql ="";
		
		
		
		CommonMessage.debugMsg(" dmtid "+dmtid);
		CommonMessage.debugMsg(" DMT Level :: "+dmtlevel);
		CommonMessage.debugMsg(" mchId "+mchId);
		
		try
		{
			
		    
			if(UIUtils.isValidKeyId(mchId))
			{  
				CommonMessage.debugMsg("MCH id");
				paramValues.add(kaizenId);
		        paramValues.add(sectId);
				sql = KznTlHdmstSql.getHDMachineDtlsSql(sectId, mchId);
				paramValues.add(mchId);
				CommonMessage.debugMsg("String sql="+sql);
				kznHDScanList = dbActionTemplate.getDataList(sql,paramValues);
			}
			else if(UIUtils.isValidKeyId(dmtid))
			{ 
				CommonMessage.debugMsg("DMT id");
				paramValues.add(kaizenId);
			    paramValues.add(sectId);
				sql = KznTlHdmstSql.getHDMachineDtlsdmtSql(dmtid, mchId,kaizenId,cellId,hdcellid);
				//paramValues.add(mchId);
				CommonMessage.debugMsg("String sql="+sql);
				kznHDScanList = dbActionTemplate.getDataList(sql);
			}
			else
			{
				CommonMessage.debugMsg("INSIDE KAIZEN");
				paramValues.add(kaizenId);
			    paramValues.add(sectId);
			    sql = KznTlHdmstSql.getHDCellDtlsSql(sectId, cellId);
				paramValues.add(cellId);
				kznHDScanList = dbActionTemplate.getDataList(sql,paramValues);
			}
			
			
			//return kznHDScanList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in kznHDScan dao impl"+e.getMessage());
		}
		//return null;
		return kznHDScanList;
		
	}

	@Override
	public List<KznTlHdmst> save(List<KznTlHdmst> newKznTlHdmstList,String kaizenId)throws Exception 
	{
			List<String> sqls = new ArrayList<String>();
			try
			{	
				CommonMessage.debugMsg(" Inside 1 ");
				if(newKznTlHdmstList != null && newKznTlHdmstList.size()>0  )
				{	CommonMessage.debugMsg("newKznTlHdmstList="+newKznTlHdmstList.size());
				    CommonMessage.debugMsg(" Inside 2 ");
					//sqls.add(KznTlHdmstSql.getDeleteSqls(kaizenId));
					for(KznTlHdmst newKznTlHdmst:newKznTlHdmstList)
					{
						CommonMessage.debugMsg(" Inside 3 ");
						CommonMessage.debugMsg("Dao Impl newKznTlHdmst.getKhdmKeyid()----"+newKznTlHdmst.getKhdmKeyid());
					
						//if( newKznTlHdmst.getSelectionFlag().equals("INSERT"))
						if(!UIUtils.isValidKeyId(newKznTlHdmst.getKhdmKeyid()))
						{   CommonMessage.debugMsg(" Inside 4 ");
							String keyid = dbActionTemplate.getSequenceNumber(KznTlHdmstSql.TBL_KZN_TL_HDMST,15,"KH","YY","Y");
							newKznTlHdmst.setKhdmKeyid(keyid); // set the sequence number 
							sqls.add(KznTlHdmstSql.getInsertSql(kznTlHdmstSql.getKhdmDbFields(),newKznTlHdmst.getSaveArray()));
						}
						else{
							CommonMessage.debugMsg("Inside update the HDMST Table");
							sqls.add(KznTlHdmstSql.getUpdateSql(kznTlHdmstSql.getKhdmDbFields(),newKznTlHdmst.getSaveArray()));
						}
						
						/*else if(newKznTlHdmst.getSelectionFlag().equals("DELETE"))
						{
							sqls.add(KznTlHdmstSql.getDeleteSqls(kaizenId));
						}
						*/
					}
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				}
				CommonMessage.debugMsg("return newKznTlHdmstList"+newKznTlHdmstList);
				
			}
	
			catch (Exception e)
			{
				System.out.print(" validate n create exception"+e.getLocalizedMessage());
			}
			return newKznTlHdmstList;
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlHdmstSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	
}
	
	
	
	


