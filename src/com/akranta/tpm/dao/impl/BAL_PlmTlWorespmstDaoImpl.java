package com.akranta.tpm.dao.impl;

import com.akranta.tpm.service.api.BalWorespServiceApi;  

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlWorespmstDao;
//import com.akranta.tpm.dao.sql.CliTlStandardsSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlWorespdtlSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlWorespmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_PlmTlWorespdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PlmTlWorespmstDaoImpl implements BAL_PlmTlWorespmstDao {


	private DBActionTemplate dbActionTemplate; 
	private BAL_PlmTlWorespmstSql plmTlWorespmstSql;
	private BAL_PlmTlWorespdtlSql plmTlWorespdtlSql;
	private BalWorespServiceApi balWorespServiceApi; 
	public BAL_PlmTlWorespmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		plmTlWorespmstSql = new BAL_PlmTlWorespmstSql();
		plmTlWorespdtlSql = new BAL_PlmTlWorespdtlSql();
	}
	
	//JWT Token
	public void balPlmTlWorespmstDaoImplJwt(String jwtToken) {
        try {
            balWorespServiceApi = new BalWorespServiceApi(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_PlmTlWorespmst create(BAL_PlmTlWorespmst plmTlWorespmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		// contains dbtable,field names, Field types and related sqls  of master table
		List<BAL_PlmTlWorespdtl>  workRespdtlList = plmTlWorespmst.getWoRespDetail();
		try{
		
			 /*detail*/
			CommonFunctions.debugMsg(" Size  List : "+workRespdtlList.size());
			if(workRespdtlList != null && workRespdtlList.size()>0)
			{
				CommonFunctions.debugMsg("worresp Detail in Dao Impl Create");
				for(BAL_PlmTlWorespdtl workRespDtl:workRespdtlList)
				{
					CommonFunctions.debugMsg("master id bfr  "+workRespDtl.getPwrdmasterid());
					workRespDtl.setPwrdkeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlWorespdtlSql.TBL_BAL_PLM_TL_WORESPDTL, 15, "PWR", "MMYY", null));
					if(!UIUtils.isValidKeyId(workRespDtl.getPwrdmasterid())){
						
						plmTlWorespmst.setPwrmkeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlWorespmstSql.TBL_BAL_PLM_TL_WORESPMST, 15, "PRM", "MMYY", null)); // set the sequnce number
						workRespDtl.setPwrdmasterid(plmTlWorespmst.getPwrmkeyid());
						sqls.add(BAL_PlmTlWorespmstSql.getInsertSql(plmTlWorespmstSql.getPwrmDbFields(), plmTlWorespmst.getSaveArray())); // add insert sql for master table
					}
					else{
						
						sqls.add(BAL_PlmTlWorespmstSql.getUpdateSql(plmTlWorespmstSql.getPwrmDbFields(), plmTlWorespmst.getSaveArray()));
					}
					CommonFunctions.debugMsg("master id"+workRespDtl.getPwrdmasterid());
					String sql = "SELECT CELL_FACTORYID ,CELL_SECTIONID ,MCHM_CELLID,  MCHM_KEYID FROM  "
						+TableNames.TBL_GEN_TL_MACHINEMST +","+TableNames.TBL_GEN_TL_CELLMST +" WHERE MCHM_ACTIVE = 'Y' "
						+"AND MCHM_CELLID = CELL_KEYID AND MCHM_KEYID = '"+ plmTlWorespmst.getPwrmmachineid()+"' EXCEPT "
						+"SELECT PWRM_FACTORYID,PWRM_SECTIONID,PWRM_CELLID,PWRM_MACHINEID  FROM  BAL_PLM_TL_WORESPMST WHERE 1 = 1"
						+" AND PWRM_MACHINEID = '"+ plmTlWorespmst.getPwrmmachineid()+"'";
					List<String[]> getMachExists = dbActionTemplate.getDataList(sql);
					CommonFunctions.debugMsg("1sql   "+sql);
					String sqlUpdtPm = null;
					String sqlUpdtCal = null;
					if(getMachExists.size()>0){
						 String sqlc ="SELECT PWRM_FACTORYID,PWRM_SECTIONID,PWRM_CELLID,PWRM_MACHINEID,PWRM_KEYID  "
							 +"FROM  BAL_PLM_TL_WORESPMST WHERE 1 = 1  AND PWRM_MACHINEID = '"+ plmTlWorespmst.getPwrmmachineid()+"' AND PWRM_LEVEL = 'M' ";
						 CommonFunctions.debugMsg("2ndsql   "+sqlc);	
						 List<String[]> getMachavail = dbActionTemplate.getDataList(sqlc);
							if(getMachavail.size()>0){
								if(plmTlWorespmst.getPwrmtradewise().equals("N")){
								 sqlUpdtPm = "UPDATE BAL_PLM_TL_STANDARDS SET PMSD_PREPAREDBYID = '"+workRespDtl.getPwrdempid()+"' WHERE 1 = 1  AND PMSD_MACHINEID = '"+plmTlWorespmst.getPwrmmachineid()+"'";
								 sqlUpdtCal = "UPDATE BAL_PLM_TL_CALENDAR SET PMCL_RESPONSIBILITY = '"+workRespDtl.getPwrdempid()+"'   WHERE 1 = 1   AND PMCL_MACHINEID = '"+plmTlWorespmst.getPwrmmachineid()+"' AND PMCL_STATUS = 'X'";
								}
								else
								{
									sqlUpdtPm = "UPDATE BAL_PLM_TL_STANDARDS SET PMSD_PREPAREDBYID = '"+workRespDtl.getPwrdempid()+"'  WHERE PMSD_TRADEID = '"+workRespDtl.getPwrdtradeid()+"' AND PMSD_MACHINEID = '"+plmTlWorespmst.getPwrmmachineid()+"'";
									sqlUpdtCal = "UPDATE BAL_PLM_TL_CALENDAR SET PMCL_RESPONSIBILITY =  '"+workRespDtl.getPwrdempid()+"'  WHERE PMCL_TRADEID = '"+workRespDtl.getPwrdtradeid()+"' AND PMCL_MACHINEID = '"+plmTlWorespmst.getPwrmmachineid()+"' AND PMCL_STATUS = 'X'";	
								}
								sqls.add(sqlUpdtPm);
								sqls.add(sqlUpdtCal);
							}
							
					}
					
					sqls.add(BAL_PlmTlWorespdtlSql.getInsertSql(plmTlWorespdtlSql.getPwrdDbFields(),workRespDtl.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlWorespmst;
	}
	
	public BAL_PlmTlWorespmst update(BAL_PlmTlWorespmst plmTlWorespmst)	throws Exception { 
		CommonFunctions.debugMsg("worresp  Dao Impl update");
		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlWorespmstSql plmTlWorespmstSql = new BAL_PlmTlWorespmstSql();
		List<BAL_PlmTlWorespdtl>  workRespdtlList = plmTlWorespmst.getWoRespDetail();
		try {

			
			/*detail*/
			if(workRespdtlList != null && workRespdtlList.size()>0)
			{
				CommonFunctions.debugMsg("worresp Detail in Dao Impl update  :"+workRespdtlList.size());
				for(BAL_PlmTlWorespdtl workRespDtl:workRespdtlList)
				{
					workRespDtl.setPwrdmasterid(plmTlWorespmst.getPwrmkeyid());
					String sqlDtl = "select PWRD_KEYID from BAL_PLM_TL_WORESPDTL where PWRD_MASTERID = '"+workRespDtl.getPwrdmasterid()+"'";
					String sqltrade="select PWRD_TRADEID from BAL_PLM_TL_WORESPDTL where PWRD_MASTERID = '"+workRespDtl.getPwrdmasterid()+"'";
					List<String[]> pmWorkDetailId = dbActionTemplate.getDataList(sqlDtl);
					List<String[]>pmWorkTradeId=dbActionTemplate.getDataList(sqltrade);
					String tradeId = null;
					String dtlId = null;
					CommonFunctions.debugMsg("pmWorkDetailId.size() "+pmWorkDetailId.size());
				//	CommonFunctions.debugMsg("trade lIst   "+	pmWorkDetailId.get(0)[0]);
					//CommonFunctions.debugMsg("trade lIst   "+	pmWorkDetailId.get(1)[0]);
					for(int i=0;i<pmWorkDetailId.size();i++){
						CommonFunctions.debugMsg("trade lIst   "+	pmWorkDetailId.get(0));
						if(!pmWorkDetailId.get(i).equals("{}"))
						dtlId =pmWorkDetailId.get(i)[0];
						CommonFunctions.debugMsg("dtlId "+i +" "+dtlId);
					}
					for(int i=0;i<pmWorkTradeId.size();i++){
						String sql = null;
						CommonFunctions.debugMsg("tradeID  "+pmWorkTradeId.get(0));
						if(!pmWorkTradeId.get(i).equals("{}"))
							tradeId=pmWorkTradeId.get(i)[0];
							
							String Keyid=plmTlWorespmst.getPwrmkeyid();
							String id=workRespDtl.getPwrdtradeid();
						CommonFunctions.debugMsg("tradeId "+i +" "+tradeId+"-----"+id+"---"+plmTlWorespmst.getPwrmkeyid());
						if(tradeId.equals(id) || id.equals("{}")){
						sql = "DELETE  FROM BAL_PLM_TL_WORESPDTL WHERE PWRD_MASTERID = '"+Keyid+"' AND PWRD_TRADEID = '"+id+"'";
						sqls.add(sql);
						}
						
							
							}
					workRespDtl.setPwrdkeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlWorespdtlSql.TBL_BAL_PLM_TL_WORESPDTL, 15, "PWR", "MMYY", null));
					sqls.add(BAL_PlmTlWorespdtlSql.getInsertSql(plmTlWorespdtlSql.getPwrdDbFields(),workRespDtl.getSaveArray()));
					/*tradeId = dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_WORESPDTL, "PWRD_TRADEID", "PWRD_KEYID", dtlId);
					String empId   = dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_WORESPDTL, "PWRD_EMPID", "PWRD_MASTERID", workRespDtl.getPwrdmasterid());
					
					String getTradeId =workRespDtl.getPwrdtradeid();
					if(!UIUtils.isValidKeyId(tradeId))
						tradeId = "";
					CommonFunctions.debugMsg("tradeId --- :"+tradeId+"========="+workRespDtl.getPwrdtradeid()+"`````````````````"+getTradeId);
					if( !tradeId.equals(workRespDtl.getPwrdtradeid()) ){
						CommonFunctions.debugMsg("tradeId --- :"+tradeId +"``````````"+workRespDtl.getPwrdmasterid());
						CommonFunctions.debugMsg("getTradeId  :"+getTradeId);
						
						//if(!tradeId.equals("{}")  && getTradeId.equals("{}")){
							CommonFunctions.debugMsg("tradeId --- :"+tradeId +"55555555"+workRespDtl.getPwrdmasterid());
							//create(plmTlWorespmst);
					//	}
					}
					else{
						CommonFunctions.debugMsg("inside update");
						sqls.add(PlmTlWorespmstSql.getUpdateSql(plmTlWorespmstSql.getPwrmDbFields(), plmTlWorespmst.getSaveArray()));
						String sql = null;
						
						if(getTradeId.equals("{}") || tradeId.equals("{}"))
							 sql = "DELETE FROM PLM_TL_WORESPDTL WHERE PWRD_MASTERID = '"+plmTlWorespmst.getPwrmkeyid()+"'";
						else
						 sql = "DELETE FROM PLM_TL_WORESPDTL WHERE PWRD_MASTERID = '"+plmTlWorespmst.getPwrmkeyid()+"' AND PWRD_TRADEID = '"+workRespDtl.getPwrdtradeid()+"'";
						sqls.add(sql);
						String woDtlkeyId=dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_WORESPDTL, "PWRD_KEYID", "PWRD_MASTERID", plmTlWorespmst.getPwrmkeyid());
						if(UIUtils.isValidKeyId(woDtlkeyId)) {
						workRespDtl.setPwrdkeyid(woDtlkeyId);
						 
						}
						else{
							workRespDtl.setPwrdkeyid(dbActionTemplate.getSequenceNumber(PlmTlWorespdtlSql.TBL_PLM_TL_WORESPDTL, 15, "PWRD", "MMYY", null));
					  	 
						}
						CommonFunctions.debugMsg("keyid    :"+workRespDtl.getPwrdkeyid());
						//workRespDtl.setPwrdmasterid(plmTlWorespmst.getPwrmkeyid());
					   //CommonFunctions.debugMsg("keyid 2   :"+workRespDtl.getPwrdmasterid());
						
						
					 }*/
				
				dbActionTemplate.executeStatements(sqls);
					}
					
			}	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return plmTlWorespmst;
	}
	
	public BAL_PlmTlWorespmst delete(BAL_PlmTlWorespmst plmTlWorespmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlWorespmstSql plmTlWorespmstSql = new BAL_PlmTlWorespmstSql();
		try {
			
			sqls.add(BAL_PlmTlWorespmstSql.getDeleteSql(plmTlWorespmstSql.getPwrmDbFields(), plmTlWorespmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlWorespmst;
	}
	@Override
	public String delSelectedTrade(String worespdtlId) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM BAL_PLM_TL_WORESPDTL WHERE PWRD_KEYID = '"+worespdtlId+"'";
		CommonFunctions.debugMsg(sql);
		dbActionTemplate.executeStatement(sql);
		return sql;
		
	}
}

