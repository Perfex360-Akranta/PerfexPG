package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlCycletimemstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlCycletimehistorySql;
import com.akranta.tpm.dao.sql.BAL_PcsTlCycletimemstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlCycletimehistory;
import com.akranta.tpm.model.BAL_PcsTlCycletimemst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;

import com.akranta.tpm.service.api.BAL_PcsTlCycletimeServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi; 

/* dao implementation */
public  class BAL_PcsTlCycletimemstDaoImpl implements BAL_PcsTlCycletimemstDao {
	
	 
	
	//Jwt
	private BAL_PcsTlCycletimeServiceApi balPcsTlCycletimeServiceApi; 


	private DBActionTemplate dbActionTemplate; 
	private BAL_PcsTlCycletimemstSql pcsTlCycletimemstSql;
	private BAL_PcsTlCycletimehistorySql pcsTlCycletimehistorySql;
	
	FunctionCallApi fnCallApi;
	//private PcsTlCycletimemst newpcsTlCycletimemst;

	public BAL_PcsTlCycletimemstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlCycletimemstSql = new BAL_PcsTlCycletimemstSql();
		pcsTlCycletimehistorySql = new BAL_PcsTlCycletimehistorySql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//Jwt
	public void BAL_PcsTlCycletimemstDaoImplJwt(String jwtToken) { 
		 try { 
			 balPcsTlCycletimeServiceApi = new BAL_PcsTlCycletimeServiceApi(jwtToken); 
			 fnCallApi = new FunctionCallApi(jwtToken);
		 } 
		 catch (Exception e) { 
		 e.printStackTrace(); 
		 } 
		 }


//elumalai
	@Override
	public List<BAL_PcsTlCycletimemst> save(List<BAL_PcsTlCycletimemst> newPcsTlCycletimemstList)throws Exception,BusinessApplicationExceptions {
		// TODO Auto-generated method stub
			List<String> sqls = new ArrayList<String>();
              CommonFunctions.debugMsg("Inside the DaoImpl Save");
			String [] cycleTimeKeyid = new String[ 1 ] ;
			if(newPcsTlCycletimemstList != null && newPcsTlCycletimemstList.size() > 0 )
			{
				for(BAL_PcsTlCycletimemst newpcsTlCycletimemst : newPcsTlCycletimemstList)
				{
						 cycleTimeKeyid[ 0 ] = null;
						 BAL_PcsTlCycletimemst oldPcsTlCycletimemst = null;
						 oldPcsTlCycletimemst = new BAL_PcsTlCycletimemst();
						 int retval = 0;
						 String sql = null;
						 Object [] args = null;
						 if( UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid())){
							 sql = BAL_PcsTlCycletimemstSql.selectPcsCycleTimeMstSql();
							 args = new Object[ 1 ];
							 args[ 0 ] = newpcsTlCycletimemst.getCytmKeyid();
						 }
						 else{
							 sql = BAL_PcsTlCycletimemstSql.selectPcsCycleTimeMstMCHProdCavitySql();
							 args = new Object[ 3 ];
							 args[ 0 ] = newpcsTlCycletimemst.getCytmMachineid();
							 args[ 1 ] = newpcsTlCycletimemst.getCytmProductid();
							 args[ 2 ] = newpcsTlCycletimemst.getCytmCavity();
							 
						 }
						
						 Object [] saveArr = null;
						 try{
							 saveArr =	 dbActionTemplate.getDataArr(sql, args);
						 }catch(Exception e){
							CommonFunctions.debugMsg(" retval " + retval  + " -- " + e.getMessage());  
						 }
						 if( saveArr != null )
						 {	 
							 
							 oldPcsTlCycletimemst.setSaveArray(saveArr);
							 retval = checkCycleTimeInPcs(newpcsTlCycletimemst,cycleTimeKeyid,oldPcsTlCycletimemst.getCytmCavity());
							 CommonFunctions.debugMsg(" retval -- " + retval  + " -- " );
						 } 
						 if(retval != 1 &&  ! UIUtils.isValidKeyId(cycleTimeKeyid[0])) 
						 {
							 //String keyid = dbActionTemplate.getSequenceNumber(BAL_PcsTlCycletimemstSql.TBL_PCS_TL_CYCLETIMEMST,10,"CMG",null,null);
							 String keyid = dbActionTemplate.getSequenceNumber(BAL_PcsTlCycletimemstSql.BAL_PCS_TL_CYCLETIMEMST,10,"CMG",null,null);
							 CommonFunctions.debugMsg("The Keyid"+keyid);
							 newpcsTlCycletimemst.setCytmKeyid(keyid); // set the sequence number 
							 sqls.add(BAL_PcsTlCycletimemstSql.getInsertSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }
						 else if(retval != 1 && (  UIUtils.isValidKeyId(cycleTimeKeyid[0]) || UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid() ))){
							 if( ! UIUtils.isValidKeyId(newpcsTlCycletimemst.getCytmKeyid()) ||(  UIUtils.isValidKeyId(cycleTimeKeyid[0]) && ! cycleTimeKeyid[0].equals(newpcsTlCycletimemst.getCytmKeyid()) ))
								 newpcsTlCycletimemst.setCytmKeyid(cycleTimeKeyid[0]);
							 	 
							 BAL_PcsTlCycletimehistory  pcsTlCycletimehistory = new BAL_PcsTlCycletimehistory();
						 		pcsTlCycletimehistory.setSaveArray(oldPcsTlCycletimemst.getSaveArray());
						 		pcsTlCycletimehistory.setCythTilldate( CommonFunctions.addDay(newpcsTlCycletimemst.getCytmFromdate(), -1));
						 		pcsTlCycletimehistory.setCythProdgroupid(UIUtils.isValidKeyId(pcsTlCycletimehistory.getCythProdgroupid() ) ? pcsTlCycletimehistory.getCythProdgroupid() :"{}");
						 		sqls.add(BAL_PcsTlCycletimehistorySql.getUpdateSql(pcsTlCycletimehistorySql.getCythDbFields(),pcsTlCycletimehistory.getSaveArray()));
								
								
							 CommonFunctions.debugMsg("Update CycleTime");
							 sqls.add(BAL_PcsTlCycletimemstSql.getUpdateSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }	 
						 else if( retval == 1 ){ // entry exist in pcs for the selected machine,product,cavity. Then
							    
							    
						 		BAL_PcsTlCycletimehistory  pcsTlCycletimehistory = new BAL_PcsTlCycletimehistory();
						 		pcsTlCycletimehistory.setSaveArray(oldPcsTlCycletimemst.getSaveArray());
						 		pcsTlCycletimehistory.setCythTilldate( CommonFunctions.addDay(newpcsTlCycletimemst.getCytmFromdate(), -1));
						 		pcsTlCycletimehistory.setCythProdgroupid(UIUtils.isValidKeyId(pcsTlCycletimehistory.getCythProdgroupid() ) ? pcsTlCycletimehistory.getCythProdgroupid() :"{}");
								//String cythKeyid = dbActionTemplate.getSequenceNumber(PcsTlCycletimehistorySql.TBL_PCS_TL_CYCLETIMEHISTORY,10,"CMH",null,null);
						 		String cythKeyid = dbActionTemplate.getSequenceNumber(BAL_PcsTlCycletimehistorySql.BAL_PCS_TL_CYCLETIMEHISTORY,10,"CMH",null,null);
								pcsTlCycletimehistory.setCythKeyid(cythKeyid);
								sqls.add(BAL_PcsTlCycletimehistorySql.getInsertSql(pcsTlCycletimehistorySql.getCythDbFields(),pcsTlCycletimehistory.getSaveArray()));
								sqls.add(BAL_PcsTlCycletimemstSql.getUpdateSql(pcsTlCycletimemstSql.getCytmDbFields(),newpcsTlCycletimemst.getSaveArray()));
						 }
						 
				}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				
			}
			return newPcsTlCycletimemstList;
		}
	
	@Override
	public BAL_PcsTlCycletimemst create(BAL_PcsTlCycletimemst pcsTlCycletimemst)throws Exception {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_PcsTlCycletimemst update(BAL_PcsTlCycletimemst pcsTlCycletimemst)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_PcsTlCycletimemst delete(BAL_PcsTlCycletimemst pcsTlCycletimemst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		try {
			CommonFunctions.debugMsg("inside dao impl delte");
			sqls.add(BAL_PcsTlCycletimemstSql.getDeleteSql(pcsTlCycletimemstSql.getCytmDbFields(),pcsTlCycletimemst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlCycletimemst;
	}
	
	private int checkCycleTimeInPcs(BAL_PcsTlCycletimemst newpcsTlCycletimemst,String [] cycleTimeKeyid,String noCavity) throws Exception {
		try
		{
		
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(newpcsTlCycletimemst.getCytmMachineid());
			paramValues.add(newpcsTlCycletimemst.getCytmProductid());
			paramValues.add(noCavity);
			//List<String[]> cycleTime = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_CheckCycleTimeInPCS", paramValues);
			List<String[]> cycleTime = dbActionTemplate.processFunctionCalls("bal_pcs_fn_checkcycletimeinpcs", paramValues);
			if( cycleTime.size() > 0 )
				cycleTimeKeyid[0] = cycleTime.get(0)[0]; 
			CommonFunctions.debugMsg("Datd"+paramValues.get(0));
		
			return Integer.parseInt(paramValues.get(0));
		}
		catch(Exception e)
		{
			CommonFunctions.debugMsg("error="+e.getMessage());
		}
		return 0;

	}

	private ResultSet getPcsResultSet(BAL_PcsTlCycletimemst newpcsTlCycletimemst) throws Exception
	{
		try
		{
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(newpcsTlCycletimemst.getCytmMachineid());
		paramValues.add(newpcsTlCycletimemst.getCytmProductid());
		paramValues.add(newpcsTlCycletimemst.getCytmCavity());
		//return  dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_CheckCycleTimeInPCS", paramValues);
		return  dbActionTemplate.dbFunctionCall("bal_pcs_fn_checkcycletimeinpcs", paramValues);
		}
		catch(Exception e)
		{
			CommonFunctions.debugMsg("eeee="+e.getMessage());
		}
		return null;
		
	}
	//elumalai cell
	@Override
	public List<String[]> getPcsData(String cellId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			String sql = BAL_PcsTlCycletimemstSql.getPcsCycletimeSql(cellId);
			System.out.println("String sql="+sql);
			ImprvCategoryList = dbActionTemplate.getDataList(sql);
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}	

	public List<String[]> getCellCycletimeData(String cellId, String machineId,
	        String productId, GridParams gridParams) throws Exception
	{
	    try {
	        String condParms = "";
	        if (UIUtils.isValidKeyId(cellId))    condParms += "CELLID=" + cellId + ";";
	        if (UIUtils.isValidKeyId(machineId)) condParms += "MACHINEID=" + machineId + ";";
	        if (UIUtils.isValidKeyId(productId)) condParms += "PRODUCTID=" + productId + ";";

	        String commonParams = "";
	        String toRow = gridParams.getToRow();
	        if (toRow != null && toRow.trim().length() > 0 && !toRow.trim().equals("0"))
	            commonParams += "FROMROW=" + gridParams.getFromRow() + ";"
	                          + "TOROW=" + toRow + ";";
	        String gridFilter = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	        if (gridFilter != null && gridFilter.trim().length() > 0)
	            commonParams += "GRIDFILTER=" + gridFilter + ";";

	        List<String> paramValues = new ArrayList<String>();
	        paramValues.add(condParms);
	        paramValues.add(commonParams);
	        return fnCallApi.callFunction("PCS_FN_CELLCYCLETIME_SB", paramValues, 2, true);
	    } catch (Exception e) {
	        System.out.println("Exception in getCellCycletimeData " + e.getMessage());
	    }
	    return null;
	}
/*	@Override
	  public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
			sql.append(balPcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId,gridParams));
			sql.append( " ) a ) where rnum between " );
			sql.append( gridParams.getFromRow());
			sql.append(" and ");
			sql.append(gridParams.getToRow());
			//String sqls = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);//getPcsCycletimeEntrySql();
			System.out.println("String sql="+sql.toString());
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	} */
	
	//elumalai
	public List<String[]> getPcsEntryData(String cellId, String productId, GridParams gridParams) throws Exception
	{
	    try
	    {
	        List<String> paramValues = new ArrayList<String>();

	        String machineId = "";
	        String cellOnly = "";
	        if (cellId != null && cellId.startsWith("MCH"))
	            machineId = cellId;
	        else if (UIUtils.isValidKeyId(cellId))
	            cellOnly = cellId;

	        String condParms = "";
	        if (UIUtils.isValidKeyId(cellOnly))
	            condParms += "CELLID=" + cellOnly + ";";
	        if (UIUtils.isValidKeyId(machineId))
	            condParms += "MACHINEID=" + machineId + ";";
	        if (UIUtils.isValidKeyId(productId))
	            condParms += "PRODUCTID=" + productId + ";";

	        String commonParams = "FROMROW=" + gridParams.getFromRow() + ";"
	                            + "TOROW=" + gridParams.getToRow() + ";";
	        String gridFilter = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	        if (gridFilter != null && gridFilter.trim().length() > 0)
	            commonParams += "GRIDFILTER=" + gridFilter + ";";

	        paramValues.add(condParms);
	        paramValues.add(commonParams);
	        CommonMessage.debugMsg("The Param Values Are:::" + paramValues);

	       // return dbActionTemplate.processFunctionCallsWithColHeaders("PCS_FN_CYCLETIMEENTRY", paramValues);
	        return fnCallApi.callFunction("PCS_FN_CYCLETIMEENTRY_SB", paramValues,2,true);
	    }
	    catch (Exception e)
	    {
	        System.out.println("Exception in getPcsEntryData dao impl " + e.getMessage());
	    }
	    return null;
	}
	
	
//	public List<String[]> getPcsEntryData(String cellId,String productId,GridParams gridParams) throws Exception {
//		// TODO Auto-generated method stub
//		List<String[]> ImprvCategoryList=null;
//		try
//		{
//			
//			StringBuffer sql = new StringBuffer();
//			//sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
//			//elumalai
//			sql.append(" select * from (select ROW_NUMBER() OVER () AS rnum,  a.* FROM ( ");
//			//sql.append(PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId,gridParams));
//			sql.append(BAL_PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId,productId,gridParams));	
//			sql.append( " ) a ) where rnum between ");
//			sql.append( gridParams.getFromRow());
//			sql.append(" and ");
//			sql.append(gridParams.getToRow());
//			//String sqls = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);//getPcsCycletimeEntrySql();
//			System.out.println("String sql="+sql.toString());
//			//List<String> params=pillarId;
//			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
//			return ImprvCategoryList;
//		}
//	
//		catch(Exception e)
//		{
//			System.out.println("Exception in Pcs dao impl"+e.getMessage());
//		}
//		return null;
//	}
	
	
	
	
	/*public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) FROM (  ");
		sql.append(PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId,gridParams));
		sql.append( " ) " );
		com.akranta.tpm.utils.CommonFunctions.debugMsg( " sql " + sql);
		String countStr;
		try {
			countStr = dbActionTemplate.getSingleValue(sql.toString());
			return Integer.parseInt(countStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}*/
	

	//elumalai
	public int getTotalCountSql(String cellId, String productId,GridParams gridParams){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) FROM (  ");
		sql.append(BAL_PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, productId,gridParams));
		sql.append( " ) " );
		com.akranta.tpm.utils.CommonFunctions.debugMsg( " sql " + sql);
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
	
	private BAL_PcsTlCycletimehistory fillpcsTlCycletimehistory(BAL_PcsTlCycletimemst newpcsTlCycletimemst) 
	{
		BAL_PcsTlCycletimehistory pcsTlCycletimehistory =new BAL_PcsTlCycletimehistory();
		
		pcsTlCycletimehistory.setCythActive("Y");
		pcsTlCycletimehistory.setCythCellid(newpcsTlCycletimemst.getCytmCellid());
		//pcsTlCycletimehistory.setCythCavity(newpcsTlCycletimemst.getCytmCavity());
		pcsTlCycletimehistory.setCythCreatedby(newpcsTlCycletimemst.getCytmCreatedby());
		pcsTlCycletimehistory.setCythCreatedon(newpcsTlCycletimemst.getCytmCreatedon());
		pcsTlCycletimehistory.setCythCycletime(newpcsTlCycletimemst.getCytmCycletime());
		pcsTlCycletimehistory.setCythFactoryid(newpcsTlCycletimemst.getCytmFactoryid());
		//pcsTlCycletimehistory.setCythFromdate(newpcsTlCycletimemst.getCytmFromdate());
		//pcsTlCycletimehistory.setCythMandrels(newpcsTlCycletimemst.getCytmMandrels());
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
			String sql = BAL_PcsTlCycletimemstSql.getcycleTimeUpdate(product, cycleTime);
			CommonFunctions.debugMsg("sql....."+sql);
			dbActionTemplate.executeStatement(sql);
			CommonFunctions.debugMsg("sql..5151414515..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


/*	public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception {
		
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
			System.out.println("String sql="+sql.toString());
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}
	
} */
	
public List<String[]> getPcsPrdData(String cellId,String productId, GridParams gridParams)throws Exception {
		
		List<String[]> ImprvCategoryList=null;
		try
		{
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
			sql.append(BAL_PcsTlCycletimemstSql.RecallCompSqlForProd(cellId, productId,gridParams));
			sql.append( " ) a ) where rnum between " );
			sql.append( gridParams.getFromRow());
			sql.append(" and ");
			sql.append(gridParams.getToRow());
			//String sqls = PcsTlCycletimemstSql.RecallCompSqlForMachine(cellId, machineId, productId, prodGroupId);//getPcsCycletimeEntrySql();
			System.out.println("String sql="+sql.toString());
			//List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql.toString());
			return ImprvCategoryList;
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in Pcs dao impl"+e.getMessage());
		}
		return null;
	}
	
}
	
	

