
package com.akranta.tpm.dao.impl;


import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEnableDisableFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlEnablelosscaptureDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
import com.akranta.tpm.dao.sql.PcsTlEnablelosscaptureSql;
import com.akranta.tpm.dao.sql.PcsTlLossphenfactorylinkSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


/* dao implementation */
public class PcsTlEnablelosscaptureDaoImpl implements PcsTlEnablelosscaptureDao {


	private DBActionTemplate dbActionTemplate; 
	private PcsTlEnablelosscaptureSql pcsTlEnablelosscaptureSql;
	FunctionCallApi fnCallApi;

	public PcsTlEnablelosscaptureDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlEnablelosscaptureSql = new PcsTlEnablelosscaptureSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	//-- added by vignesh -- //
	public void PcsTlEnablelosscaptureDaoImplJwt(String JwtToken) {
	    try {
	    //    oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
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

	@Override
	public List<String[]> getAllPcsEnblDsblMCH(String factId,String sectId,String cellId,String flid) throws Exception  
	{
		CommonMessage.debugMsg("Inside getAllPcsEnblDsblMCH  Dao Impl..........");
		try
		{
			List<String > paramValues = new ArrayList<String>();
			
			/*paramValues.add(factId);
			paramValues.add(sectId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			*/
			//CommonMessage.debugMsg("opllOplid"+factId+"dffdf="+sectId+"cell="+cellId);
			String sql = PcsTlEnablelosscaptureSql.getPcsEbDbMCHSql(flid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String[]> studentList = dbActionTemplate.getDataList(sql,paramValues);
			return studentList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getAllPcsEnblDsblCELL(String factId,String sectId,String cellId) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside getAllPcsEnblDsblCELL  Dao Impl..........");
		
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(factId);
			paramValues.add(sectId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			paramValues.add(cellId);
			CommonMessage.debugMsg("opllOplid"+factId+"dffdf="+sectId+"cell="+cellId);
			String sql = PcsTlEnablelosscaptureSql.getPcsEbDbCELLSql();
			CommonMessage.debugMsg("String sql="+sql);
			List<String[]> studentList = dbActionTemplate.getDataList(sql,paramValues);
			return studentList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getAllPcsEnblDsblSECT(String factId,String sectId) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside getAllPcsEnblDsblSECT  Dao Impl..........");
		
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(factId);
			paramValues.add(sectId);
	
			CommonMessage.debugMsg("Parameters"+factId+"dffdf="+sectId);
			String sql = PcsTlEnablelosscaptureSql.getPcsEbDbSECTSql();
			CommonMessage.debugMsg("String sql="+sql);
			List<String[]> studentList = dbActionTemplate.getDataList(sql,paramValues);
			return studentList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
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
	
			CommonMessage.debugMsg("Parameters"+sectId);
			String sql = PcsTlEnablelosscaptureSql.getSectCnt();
			CommonMessage.debugMsg("String sql="+sql);
			List<String[]> sectCntList = dbActionTemplate.getDataList(sql,paramValues);
			CommonMessage.debugMsg("sectCntList="+sectCntList);
			return sectCntList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getPcsEnblDsblSectCnt dao impl"+e.getMessage());
		}
		return null;
	}
	
	
	public List<PcsTlEnablelosscapture> save(List<PcsTlEnablelosscapture> pcsTlEnablelosscaptureList,
			PcsEnableDisableFormBean pcsEnableDisableFormBean) throws Exception
	{

		CommonMessage.debugMsg("In Save Funct");
		List<String> sqls = new ArrayList<String>();

		if(pcsTlEnablelosscaptureList != null && pcsTlEnablelosscaptureList.size() > 0  )
		{	
			CommonMessage.debugMsg("sparesPkupList="+pcsTlEnablelosscaptureList.size());
		
			if (pcsEnableDisableFormBean.getForCell().equals("Y")) {
				for(PcsTlEnablelosscapture newPcsTlEnablelosscapture:pcsTlEnablelosscaptureList)
				{
					StringBuilder sb1= new StringBuilder();
					sb1.append("  UPDATE PCS_TL_ENABLELOSSCAPTURE  SET PELC_ACTIVE='N' ");
					sb1.append("	WHERE PELC_TYPE='M' AND PELC_FLID='" +pcsTlEnablelosscaptureList.get(0).getPelcFlid() +"' "); 
					sqls.add(sb1.toString());
					
					StringBuilder sb= new StringBuilder();
					sb.append("  SELECT MAX(PELC_KEYID) AS PELC_KEYID FROM PCS_TL_ENABLELOSSCAPTURE ");
					sb.append("	WHERE PELC_TYPE='C' "); 
					sb.append("	AND PELC_FLID='" +pcsTlEnablelosscaptureList.get(0).getPelcFlid() +"' ");

					CommonMessage.debugMsg("sb.sql===="+sb.toString());
					String keyId = dbActionTemplate.getSingleValue(sb.toString());
					CommonMessage.debugMsg("keyId===="+keyId);
					if (! UIUtils.isValidKeyId(keyId)) {
						keyId = dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE);
						newPcsTlEnablelosscapture.setPelcKeyid(keyId); // set the sequence number 
						CommonMessage.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
						sqls.add(PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
					}
					else {
						newPcsTlEnablelosscapture.setPelcKeyid(keyId); // set the sequence number 
						CommonMessage.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
						sqls.add(PcsTlEnablelosscaptureSql.getUpdateSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
						
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
				
				sb.append("  UPDATE PCS_TL_ENABLELOSSCAPTURE  SET PELC_ACTIVE='N' ");
				sb.append("	WHERE PELC_TYPE='C' AND PELC_FLID='" +pcsTlEnablelosscaptureList.get(0).getPelcFlid() +"' "); 
				
				
				sqls.add(sb.toString());
				
				for(PcsTlEnablelosscapture newPcsTlEnablelosscapture:pcsTlEnablelosscaptureList)
				{
					
					CommonMessage.debugMsg("Dao Impl newplmTlSparedtl.getPspdKeyid()----"+newPcsTlEnablelosscapture.getPelcKeyid());
					if(newPcsTlEnablelosscapture.getPelcIspcsenabled() != null && newPcsTlEnablelosscapture.getPelcIspcsenabled()!="")
					{	
						CommonMessage.debugMsg("newPcsTlEnablelosscapture.getPelcIspcsenabled()---"+newPcsTlEnablelosscapture.getPelcIspcsenabled());
						if( newPcsTlEnablelosscapture.getPelcKeyid() == null )
						{
							String keyid = dbActionTemplate.getSequenceNumber(PcsTlEnablelosscaptureSql.TBL_PCS_TL_ENABLELOSSCAPTURE);
							newPcsTlEnablelosscapture.setPelcKeyid(keyid); // set the sequence number 
							CommonMessage.debugMsg("Query===="+PcsTlEnablelosscaptureSql.getInsertSql(pcsTlEnablelosscaptureSql.getPelcDbFields(),newPcsTlEnablelosscapture.getSaveArray()));
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
		}
		CommonMessage.debugMsg("return sparesPkupList"+pcsTlEnablelosscaptureList);
		return pcsTlEnablelosscaptureList;

	}

	// -------------------------- Vignesh 12Nov2025 altered for pg  ------------------//
//	@Override
//	public List<String[]> getFactory(GridParams gridParams,String lossId,String phenID) throws Exception 
//	{
//		try
//		{
//			String sql=PcsTlEnablelosscaptureSql.getFactorySql(lossId,phenID);
//			String count="select count(*) from("+sql+")";
//			String rowCount=dbActionTemplate.getSingleValue(count); 
//			long counts=Long.parseLong(rowCount);
//			gridParams.setTotalRecordCnt(counts);
//			List<String> params= new ArrayList<String>();
//			params.add(gridParams.getFromRow());
//			params.add(gridParams.getToRow());
//			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
//			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
//			return result;
//		}
//		catch(Exception e)
//		{
//			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
//		}
//		return null;
//	}
	
	
//	@Override
//	public List<String[]> getPhenomena(GridParams gridParams,String phenId,String lossId) throws Exception {
//	
//		try
//		{
//			String sql=PcsTlEnablelosscaptureSql.gePhenomenaSql(phenId,lossId);
//			String count="select count(*) from("+sql+")";
//			String rowCount=dbActionTemplate.getSingleValue(count);
//			long counts=Long.parseLong(rowCount);
//			gridParams.setTotalRecordCnt(counts);
//			List<String> params= new ArrayList<String>();
//			params.add(gridParams.getFromRow());
//			params.add(gridParams.getToRow());
//			String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+sql+") a ) where slno >= ? and slno <= ?";
//			List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
//			return result;
//		}
//		catch(Exception e)
//		{
//			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
//		}
//		return null;
//	}

	@Override
	public List<String[]> getPhenomena(GridParams gridParams, String phenId, String lossId) throws Exception {
	    try {
	        String sql = PcsTlEnablelosscaptureSql.gePhenomenaSql(phenId, lossId);

	        // Postgres requires an alias for subselects
	        String count = "SELECT COUNT(*) FROM (" + sql + ") x";
	        String rowCount = dbActionTemplate.getSingleValue(count);
	        long counts = Long.parseLong(rowCount);
	        gridParams.setTotalRecordCnt(counts);

	        // Parse grid bounds safely (Oracle 1-based semantics preserved)
	        int from = 1;
	        int to   = (int) counts; // fallback if toRow is missing

	        String fromStr = gridParams.getFromRow();
	        String toStr   = gridParams.getToRow();

	        try { if (fromStr != null && !fromStr.trim().isEmpty()) from = Integer.parseInt(fromStr.trim()); } catch (NumberFormatException ignore) {}
	        try { if (toStr   != null && !toStr.trim().isEmpty())   to   = Integer.parseInt(toStr.trim());   } catch (NumberFormatException ignore) {}

	        if (from < 1) from = 1;
	        if (to < from) to = from;

	        // Replace Oracle ROWNUM with Postgres row_number(); keep the same paging semantics.
	        // Note: no placeholders here—embed sanitized ints to avoid the '?::int' parser issue seen in logs.
	        String finalSql =
	            "SELECT * FROM ( " +
	            "  SELECT row_number() OVER () AS slno, a.* " +
	            "  FROM ( " + sql + " ) a " +
	            ") s " +
	            "WHERE slno BETWEEN " + from + " AND " + to;

	        // Execute (no bind params needed now)
	        List<String[]> result = dbActionTemplate.getDataList(finalSql);
	        return result;
	    } catch (Exception e) {
	        CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl" + e.getMessage());
	    }
	    return null;
	}

	
	
	
	@Override
	public List<String[]> getFactory(GridParams gridParams, String lossId, String phenID) throws Exception 
	{
	    try
	    {
	        String sql = PcsTlEnablelosscaptureSql.getFactorySql(lossId, phenID);

	        // Postgres requires an alias for subselects
	        String count = "SELECT COUNT(*) FROM (" + sql + ") x";
	        String rowCount = dbActionTemplate.getSingleValue(count);
	        long counts = Long.parseLong(rowCount);
	        gridParams.setTotalRecordCnt(counts);

	        List<String> params = new ArrayList<String>();
	        params.add(gridParams.getFromRow());  // start row (1-based)
	        params.add(gridParams.getToRow());    // end row (1-based)

	        // Replace Oracle ROWNUM with Postgres row_number(); keep BETWEEN style
	        // Cast binds to ::int so setString(...) works with numeric comparison.
	        String finalSql =
	            "SELECT * FROM ( " +
	            "  SELECT row_number() OVER () AS slno, a.* " +
	            "  FROM ( " + sql + " ) a " +
	            ") s " +
	            "WHERE slno >= ?::int AND slno <= ?::int";

	        List<String[]> result = dbActionTemplate.getDataList(finalSql, params);
	        return result;
	    }
	    catch (Exception e)
	    {
	        CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl" + e.getMessage());
	    }
	    return null;
	}

	
	
	
	
	
	
	
	// -------------------------- Vignesh 12Nov2025 altered for pg  ------------------//

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
			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}


	public List<String[]> getLossPhenMst(GridParams gridParams ,CommonFilter commonFilter) throws Exception
	{
		try
			{
				CommonMessage.debugMsg("dao impl is calling getLossPhenMst");
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
				CommonMessage.debugMsg("condParms isss"+condParms);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				//condParms=condParms+"FLID="+commonFilter.getFlid()+";";
				 
				
			//	CommonMessage.debugMsg("----------condParms-----------"+condParms);
				//CommonMessage.debugMsg("commonParams isss"+commonParams);
				paramValues.add(condParms);
				paramValues.add(commonParams);
				
				
			/*String sql=PcsTlLossphenfactorylinkSql.getPillarID(commonFilter.getPillarWise());
				String keyid=dbActionTemplate.getSingleValue(sql);		
				
				String phenlosssql=PcsTlLossphenfactorylinkSql.getlossphenCnt(keyid);
				//String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
				
				if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
					phenlosssql+="  and PLPM_KEYID='"+commonFilter.getFactoryId()+"'";
				
				String indicatorCount=dbActionTemplate.getSingleValue(phenlosssql);*/
				List<String[]> topFailList=null;
				
				topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_LOSSPHENFACTORYLINK", paramValues);
		//		List<String[]>	topFailList = fnCallApi.callFunction("PCS_FN_LOSSPHENFACTORYLINK_SB", paramValues,3,true);
				
			
				/*long totalCnt1 = Long.parseLong(indicatorCount);
			 	gridParams.setTotalRecordCnt(totalCnt1);
			  	if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}*/
			  	//CommonMessage.debugMsg(" topFailList size " + topFailList.size());
				return topFailList;
			}
			catch (Exception e)
			{
				CommonMessage.debugMsg("dao impl exception is calling getLossPhenMst");
				throw new Exception(e.getMessage()); 
			}
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
			CommonMessage.debugMsg("Exception in getPcsEnblDsblMCH dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public PcsTlLossphenomenamst getFactoryLossPhenomenaLink(PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception {
		
		return null;
	}
	public void savemultiple(List<PcsTlLossphenfactorylink> pcsTlLossphenomenamstmap)  throws Exception
	 {

List<String> sql = new ArrayList<String>();
Map<Integer,List<Object[]>> saveData = new HashMap<Integer,List<Object[]>>();
List<Object[]> savelist = new ArrayList<Object[]>();
List<Object[]> updateList = new ArrayList<Object[]>();
List<int[]> dataTypesList = new ArrayList<int[]>();
//PcsTlLossphenfactorylink pcsTlLossphenfactorylink=null;

//GenSequenceNumber genSequenceNumber=new GenSequenceNumber(dbActionTemplate.getDataSource(), "PCS_TL_LOSSPHENFACTORYLINK", 7, "PPL", null, null);

sql.add(PcsTlLossphenfactorylinkSql.getPhenomenaLossFactoryInsertSql());

for(PcsTlLossphenfactorylink pcsTlLossphenfactorylink:pcsTlLossphenomenamstmap){
	
	if( ! CommonFunctions.isValidKeyId(pcsTlLossphenfactorylink.getPpflKeyid()) ){
		
		pcsTlLossphenfactorylink.setPpflKeyid(dbActionTemplate.getSequenceNumber("PCS_TL_LOSSPHENFACTORYLINK", 7, "PPL", null, null));
	// 	pcsTlLossphenfactorylink.setPpflKeyid(genSequenceNumber);
		Object[] ptlDatatmp =   {  
				                      pcsTlLossphenfactorylink.getPpflKeyid(),
				                      pcsTlLossphenfactorylink.getPpflPlpmKeyid(),
				                      pcsTlLossphenfactorylink.getPpflActive(),
				                      pcsTlLossphenfactorylink.getPpflCreatedby(),
				                      pcsTlLossphenfactorylink.getPpflCreatedon(),
				                      pcsTlLossphenfactorylink.getPpflModifiedon(),
				                      pcsTlLossphenfactorylink.getPpflTempfield1(),
				                      pcsTlLossphenfactorylink.getPpflTempfield2(),
				                      pcsTlLossphenfactorylink.getPpflFactoryid()
			
		                         };
		 savelist.add(ptlDatatmp);
		int [] masterDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
		dataTypesList.add(masterDataType);
		//savelist.add(ptlDatatmp);
	}
	/*else
	{
		Object[] ptlDatatmp =   {  
				pcsTlLossphenfactorylink.getPpflKeyid(),
			//	pcsTlLossphenomenamstmap.getPlpmKeyid(),
                pcsTlLossphenfactorylink.getPpflPlpmKeyid(),
                pcsTlLossphenfactorylink.getPpflActive(),
                pcsTlLossphenfactorylink.getPpflCreatedby(),
                pcsTlLossphenfactorylink.getPpflCreatedon(),
                pcsTlLossphenfactorylink.getPpflModifiedon(),
                pcsTlLossphenfactorylink.getPpflTempfield1(),
                pcsTlLossphenfactorylink.getPpflTempfield2(),
                pcsTlLossphenfactorylink.getPpflFactoryid()
                
	};
		
		updateList.add(ptlDatatmp);	
		int [] masterDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
		dataTypesList.add(masterDataType);
	}	
}*/

}
saveData.put(0, savelist);

dbActionTemplate.executeBatch(sql, saveData, dataTypesList);	

	 }
	
	public List<String[]> getPFLProd(GridParams gridParams ,CommonFilter commonFilter) throws Exception
	{
			
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			if (commonFilter.getDrillLevel().equals("LIN"))
			{
				if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
					condParms+="SECTIONID="+commonFilter.getSectionId()+";";
			}else if (commonFilter.getDrillLevel().equals("FCT"))
			{
				if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
					condParms+="FACTORYID="+commonFilter.getFactoryId()+";";
			}else if (commonFilter.getDrillLevel().equals("CEL"))
			{
				if(UIUtils.isValidKeyId(commonFilter.getCellId()))
					condParms+="CELLID="+commonFilter.getCellId()+";";
			}
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
				condParms+="flid="+commonFilter.getFlid()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
				condParms+="INDICATORID="+commonFilter.getIndicator()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
				condParms+="PILLARID="+commonFilter.getPillarWise()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
				condParms+="DRILLLEVEL="+commonFilter.getDrillLevel()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
				condParms+="TYPE="+commonFilter.getType()+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			String sql=KpiTlIndicatorDeptLinkSql.getPillarID(commonFilter.getPillarWise());
			String pillId=dbActionTemplate.getSingleValue(sql);				
			/*String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
			
			if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
				indicatorSql+="  and KINK_KEYID='"+commonFilter.getIndicator()+"'";
			
			String indicatorCount=dbActionTemplate.getSingleValue(indicatorSql);*/
		//	List<String[]> topFailList=null;
			//			topFailList = dbActionTemplate.processFunctionCalls("PCS_FN_LOSSPHENFACTORYLINK", paramValues);
						
			List<String[]> topFailList = fnCallApi.callFunction("PCS_FN_LOSSPHENFACTORYLINK_SB", paramValues,3,true);
			
			
		//	long totalCnt1 = Long.parseLong(indicatorCount);
		  //	gridParams.setTotalRecordCnt(totalCnt1);
		  	if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				//CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		  	//CommonMessage.debugMsg(" topFailList size " + topFailList.size());
			return topFailList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}
	
	public PcsTlLossphenfactorylink savemultiple1(PcsTlLossphenfactorylink pcsTlLossphenfactorylink,String pillCode,String deptId,String drillLevel) throws Exception 
	{
		try{
			CommonMessage.debugMsg("U R in DAO----------------------");
			List<String> sqls = new ArrayList<String>(); 		
			String sql="";			
			PcsTlLossphenfactorylinkSql pcsTlLossphenfactorylinkSql=new PcsTlLossphenfactorylinkSql();
			
			List <PcsTlLossphenfactorylink> methodslist = pcsTlLossphenfactorylink.getmethodPillarFactlink();
			
	     	String exceptionStr="";
						if(methodslist != null && methodslist.size()>0)
			{
				for(PcsTlLossphenfactorylink pcsTlLossphenfactorylink1:methodslist)
				{
			
					if (pcsTlLossphenfactorylink1.getIsDelete().equals("Y")){
						
						try{
							
							sql=pcsTlLossphenfactorylinkSql.getDeleteSql(pcsTlLossphenfactorylinkSql.getPpflDbFields(),pcsTlLossphenfactorylink1.getPpflPlpmKeyid()); // .getPpflPlpmKeyid()
							CommonMessage.debugMsg("Delete Sql:" +sql);
							dbActionTemplate.executeStatement(sql); 
						}
						catch(BusinessApplicationExceptions e)
						{			
							
							String exceptionSql="SELECT PLPM_NAME FROM PCS_TL_LOSSPHENOMENAMST WHERE PLPM_KEYID='"+pcsTlLossphenfactorylink.getPpflPlpmKeyid()+"'";
							String indicatorName=dbActionTemplate.getSingleValue(exceptionSql);
							exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+pcsTlLossphenfactorylink.getPpflFactoryid()+"'";
							String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
							
							if(UIUtils.isValidKeyId(exceptionStr)){
								exceptionStr+=",";
							}
					
							CommonMessage.debugMsg("BusinessApplicationExceptions"+e.getMessage()+exceptionStr);
						}
					}
					else{	
						pcsTlLossphenfactorylink1.setPpflKeyid(dbActionTemplate.getSequenceNumber("PCS_TL_LOSSPHENFACTORYLINK", 7, "PPL", null, null));
						sqls.add(pcsTlLossphenfactorylinkSql.getInsertSql(pcsTlLossphenfactorylinkSql.getPpflDbFields(), pcsTlLossphenfactorylink1.getSaveArray()));		
						
					}	
					
				}
			}
			dbActionTemplate.executeStatements(sqls); 
			if(UIUtils.isValidKeyId(exceptionStr)){
				throw new BusinessApplicationExceptions(exceptionStr +" Already Referred In Loss Entry"); 
			}
			return pcsTlLossphenfactorylink;
		}
		catch(BusinessApplicationExceptions e)
		{			
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			throw new Exception(e.getMessage()); 
		}
	}
	
	public String validatePhenomenaLink(PcsTlLossphenfactorylink pcsTlLossphenfactorylink) throws Exception {
		CommonMessage.debugMsg("U R in DAO PcsTlLossphenfactorylink");
		String isValidate="";
		String phenomenaId=pcsTlLossphenfactorylink.getPpflPlpmKeyid();
		String flid=pcsTlLossphenfactorylink.getPpflFactoryid(); 
		CommonMessage.debugMsg(" phenomenaId: " + phenomenaId +" flid:" +flid);
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT COUNT(*) FROM PCS_TL_LOSSCAPTURE WHERE 1=1 ");
		sql.append(" AND PLOS_LOSSREASON=(SELECT PPFL_PLPM_KEYID FROM PCS_TL_LOSSPHENFACTORYLINK WHERE PPFL_PLPM_KEYID='");
		sql.append(phenomenaId).append("' AND PPFL_FACTORYID='").append(flid).append("') AND PLOS_FLID='");
		sql.append(flid).append("'");
		CommonMessage.debugMsg(" sql: " + sql);
		String cnt = dbActionTemplate.getSingleValue(sql.toString());
		if(Integer.parseInt(cnt)==0){
			CommonMessage.debugMsg(" in cnt: " + cnt);
			isValidate="";
		}else{
			String exceptionSql="SELECT PLPM_NAME FROM PCS_TL_LOSSPHENOMENAMST WHERE PLPM_KEYID='"+pcsTlLossphenfactorylink.getPpflPlpmKeyid()+"'";
			String phenomenaName=dbActionTemplate.getSingleValue(exceptionSql);
			exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+flid+"'";
			String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
			isValidate=phenomenaName+ " Phenomena In " +fnlnName+" Already Referred In Loss Entry";
						
		}	
		return isValidate;
	}


}

