package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlCycletimemstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PcsTlCycletimehistorySql;
import com.akranta.tpm.dao.sql.PcsTlCycletimemstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlCycletimehistory;
import com.akranta.tpm.model.PcsTlCycletimemst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public  class PcsTlCycletimemstDaoImpl implements PcsTlCycletimemstDao {


	private DBActionTemplate dbActionTemplate; 
	private PcsTlCycletimemstSql pcsTlCycletimemstSql;
	private PcsTlCycletimehistorySql pcsTlCycletimehistorySql;
	//private PcsTlCycletimemst newpcsTlCycletimemst;

	public PcsTlCycletimemstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlCycletimemstSql = new PcsTlCycletimemstSql();
		pcsTlCycletimehistorySql = new PcsTlCycletimehistorySql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}



	@Override
	public List<PcsTlCycletimemst> save(List<PcsTlCycletimemst> newPcsTlCycletimemstList)throws Exception,BusinessApplicationExceptions {
		// TODO Auto-generated method stub
			List<String> sqls = new ArrayList<String>();

			String [] cycleTimeKeyid = new String[ 1 ] ;
			if(newPcsTlCycletimemstList != null && newPcsTlCycletimemstList.size() > 0 )
			{
				for(PcsTlCycletimemst newpcsTlCycletimemst : newPcsTlCycletimemstList)
				{
						 cycleTimeKeyid[ 0 ] = null;
						 PcsTlCycletimemst oldPcsTlCycletimemst = null;
						 oldPcsTlCycletimemst = new PcsTlCycletimemst();
						 int retval = 0;
						 String sql = null;
						 Object [] args = null;
						 if( UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid())){
							 sql = PcsTlCycletimemstSql.selectPcsCycleTimeMstSql();
							 args = new Object[ 1 ];
							 args[ 0 ] = newpcsTlCycletimemst.getCytmKeyid();
						 }
						 else{
							 sql = PcsTlCycletimemstSql.selectPcsCycleTimeMstMCHProdCavitySql();
							 args = new Object[ 3 ];
							 args[ 0 ] = newpcsTlCycletimemst.getCytmMachineid();
							 args[ 1 ] = newpcsTlCycletimemst.getCytmProductid();
							 args[ 2 ] = newpcsTlCycletimemst.getCytmCavity();
							 
						 }
						
						 Object [] saveArr = null;
						 try{
							 saveArr =	 dbActionTemplate.getDataArr(sql, args);
						 }catch(Exception e){
							CommonMessage.debugMsg(" retval " + retval  + " -- " + e.getMessage());  
						 }
						 if( saveArr != null )
						 {	 
							 
							 oldPcsTlCycletimemst.setSaveArray(saveArr);
							 retval = checkCycleTimeInPcs(newpcsTlCycletimemst,cycleTimeKeyid,oldPcsTlCycletimemst.getCytmCavity());
							 CommonMessage.debugMsg(" retval -- " + retval  + " -- " );
						 } 
						 if(retval != 1 &&  ! UIUtils.isValidKeyId(cycleTimeKeyid[0])) 
						 {
							 String keyid = dbActionTemplate.getSequenceNumber(PcsTlCycletimemstSql.TBL_PCS_TL_CYCLETIMEMST,10,"CMG",null,null);
							 newpcsTlCycletimemst.setCytmKeyid(keyid); // set the sequence number 
							 sqls.add(PcsTlCycletimemstSql.getInsertSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }
						 else if(retval != 1 && (  UIUtils.isValidKeyId(cycleTimeKeyid[0]) || UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid() ))){
							 if( ! UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid()) ||(  UIUtils.isValidKeyId(cycleTimeKeyid[0]) && ! cycleTimeKeyid[0].equals(newpcsTlCycletimemst.getCytmKeyid()) ))
								 newpcsTlCycletimemst.setCytmKeyid(cycleTimeKeyid[0]);
							 	 
							 PcsTlCycletimehistory  pcsTlCycletimehistory = new PcsTlCycletimehistory();
						 		pcsTlCycletimehistory.setSaveArray(oldPcsTlCycletimemst.getSaveArray());
						 		pcsTlCycletimehistory.setCythTilldate( CommonFunctions.addDay(newpcsTlCycletimemst.getCytmFromdate(), -1));
						 		pcsTlCycletimehistory.setCythProdgroupid(UIUtils.isValidKeyId(pcsTlCycletimehistory.getCythProdgroupid() ) ? pcsTlCycletimehistory.getCythProdgroupid() :"{}");
						 		sqls.add(PcsTlCycletimehistorySql.getUpdateSql(pcsTlCycletimehistorySql.getCythDbFields(),pcsTlCycletimehistory.getSaveArray()));
								
								
							 CommonMessage.debugMsg("Update CycleTime");
							 sqls.add(PcsTlCycletimemstSql.getUpdateSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }	 
						 else if( retval == 1 ){ // entry exist in pcs for the selected machine,product,cavity. Then
							    
							    
						 		PcsTlCycletimehistory  pcsTlCycletimehistory = new PcsTlCycletimehistory();
						 		pcsTlCycletimehistory.setSaveArray(oldPcsTlCycletimemst.getSaveArray());
						 		pcsTlCycletimehistory.setCythTilldate( CommonFunctions.addDay(newpcsTlCycletimemst.getCytmFromdate(), -1));
						 		pcsTlCycletimehistory.setCythProdgroupid(UIUtils.isValidKeyId(pcsTlCycletimehistory.getCythProdgroupid() ) ? pcsTlCycletimehistory.getCythProdgroupid() :"{}");
								String cythKeyid = dbActionTemplate.getSequenceNumber(PcsTlCycletimehistorySql.TBL_PCS_TL_CYCLETIMEHISTORY,10,"CMH",null,null);
								pcsTlCycletimehistory.setCythKeyid(cythKeyid);
								sqls.add(PcsTlCycletimehistorySql.getInsertSql(pcsTlCycletimehistorySql.getCythDbFields(),pcsTlCycletimehistory.getSaveArray()));
								sqls.add(PcsTlCycletimemstSql.getUpdateSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }
						 
				}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				
			}
			return newPcsTlCycletimemstList;
		}
	
	@Override
	public PcsTlCycletimemst create(PcsTlCycletimemst pcsTlCycletimemst)throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PcsTlCycletimemst update(PcsTlCycletimemst pcsTlCycletimemst)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PcsTlCycletimemst delete(PcsTlCycletimemst pcsTlCycletimemst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		try {
			CommonMessage.debugMsg("inside dao impl delte");
			sqls.add(PcsTlCycletimemstSql.getDeleteSql(pcsTlCycletimemstSql.getCytmDbFields(),pcsTlCycletimemst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlCycletimemst;
	}
	
	private int checkCycleTimeInPcs(PcsTlCycletimemst newpcsTlCycletimemst,String [] cycleTimeKeyid,String noCavity) throws Exception {
		try
		{
		
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(newpcsTlCycletimemst.getCytmMachineid());
			paramValues.add(newpcsTlCycletimemst.getCytmProductid());
			paramValues.add(noCavity);
			List<String[]> cycleTime = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_CheckCycleTimeInPCS", paramValues);
			if( cycleTime.size() > 0 )
				cycleTimeKeyid[0] = cycleTime.get(0)[0]; 
			CommonMessage.debugMsg("Datd"+paramValues.get(0));
		
			return Integer.parseInt(paramValues.get(0));
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("error="+e.getMessage());
		}
		return 0;

	}

	private ResultSet getPcsResultSet(PcsTlCycletimemst newpcsTlCycletimemst) throws Exception
	{
		try
		{
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(newpcsTlCycletimemst.getCytmMachineid());
		paramValues.add(newpcsTlCycletimemst.getCytmProductid());
		paramValues.add(newpcsTlCycletimemst.getCytmCavity());
		return  dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_CheckCycleTimeInPCS", paramValues);
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("eeee="+e.getMessage());
		}
		return null;
		
	}
	@Override
	public List<String[]> getPcsData() throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			String sql = PcsTlCycletimemstSql.getPcsCycletimeSql();
			CommonMessage.debugMsg("String sql="+sql);
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql);
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
			sql.append(PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId,gridParams));
			sql.append( " ) a ) where rnum between " );
			sql.append( gridParams.getFromRow());
			sql.append(" and ");
			sql.append(gridParams.getToRow());
			//String sqls = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);//getPcsCycletimeEntrySql();
			
			
			CommonMessage.debugMsg("String sql="+sql.toString());
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}
	public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) FROM (  ");
		sql.append(PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId,gridParams));
		sql.append( " ) " );
		com.akranta.tpm.utils.CommonMessage.debugMsg( " sql " + sql);
		String countStr;
		try {
			countStr = dbActionTemplate.getSingleValue(sql.toString());
			return Integer.parseInt(countStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	@Override
	public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception 
	{
		// TODO Auto-generated method stub
		  ResultSet rs = null;
		   try
		   {
			   	rs =   getPcsResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,  0, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }
	}

	private ResultSet getPcsResultSet(CommonFilter commonFilter) throws Exception
	{
		return null;
	//	String sql = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);
	//	return sql;
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter)
	{
		List<String> paramValues = new ArrayList<String>();
		
		String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		return paramValues;
		
	}
	
	private PcsTlCycletimehistory fillpcsTlCycletimehistory(PcsTlCycletimemst newpcsTlCycletimemst) 
	{
		PcsTlCycletimehistory pcsTlCycletimehistory =new PcsTlCycletimehistory();
		
		pcsTlCycletimehistory.setCythActive("Y");
		pcsTlCycletimehistory.setCythCellid(newpcsTlCycletimemst.getCytmCellid());
		pcsTlCycletimehistory.setCythCavity(newpcsTlCycletimemst.getCytmCavity());
		pcsTlCycletimehistory.setCythCreatedby(newpcsTlCycletimemst.getCytmCreatedby());
		pcsTlCycletimehistory.setCythCreatedon(newpcsTlCycletimemst.getCytmCreatedon());
		pcsTlCycletimehistory.setCythCycletime(newpcsTlCycletimemst.getCytmCycletime());
		pcsTlCycletimehistory.setCythFactoryid(newpcsTlCycletimemst.getCytmFactoryid());
		//pcsTlCycletimehistory.setCythFromdate(newpcsTlCycletimemst.getCytmFromdate());
		pcsTlCycletimehistory.setCythMandrels(newpcsTlCycletimemst.getCytmMandrels());
		pcsTlCycletimehistory.setCythMachineid(newpcsTlCycletimemst.getCytmMachineid());
		pcsTlCycletimehistory.setCythManpower(newpcsTlCycletimemst.getCytmManpower());
		pcsTlCycletimehistory.setCythModifiedon(newpcsTlCycletimemst.getCytmModifiedon());
		pcsTlCycletimehistory.setCythProductid(newpcsTlCycletimemst.getCytmProductid());
		pcsTlCycletimehistory.setCythSectionid(newpcsTlCycletimemst.getCytmSectionid());
		pcsTlCycletimehistory.setCythProdgroupid(newpcsTlCycletimemst.getCytmProdgroupid());
		pcsTlCycletimehistory.setCythTempfield1(newpcsTlCycletimemst.getCytmTempfield1());
		pcsTlCycletimehistory.setCythTempfield2(newpcsTlCycletimemst.getCytmTempfield2());
		pcsTlCycletimehistory.setCythTempfield3(newpcsTlCycletimemst.getCytmTempfield3());
		pcsTlCycletimehistory.setCythTilldate(CommonFunctions.dateTimeNow());
		
		return pcsTlCycletimehistory;
	}

	@Override
	public void getcycleTimeUpdate(String product, String cycleTime)	throws Exception {
		
		try{
			String sql = PcsTlCycletimemstSql.getcycleTimeUpdate(product, cycleTime);
			CommonMessage.debugMsg("sql....."+sql);
			dbActionTemplate.executeStatement(sql);
			CommonMessage.debugMsg("sql..5151414515..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception {
		
		List<String[]> ImprvCategoryList=null;
		try
		{
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
			sql.append(PcsTlCycletimemstSql.RecallCompSqlForProd(cellId, machineId, productId, prodGroupId,gridParams));
			sql.append( " ) a ) where rnum between " );
			sql.append( gridParams.getFromRow());
			sql.append(" and ");
			sql.append(gridParams.getToRow());
			//String sqls = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);//getPcsCycletimeEntrySql();
			
			
			CommonMessage.debugMsg("String sql="+sql.toString());
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}
	
}

