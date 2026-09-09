package com.akranta.tpm.dao.impl;





import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlEnergyconsumptionmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlEmployeedtlSql;
import com.akranta.tpm.dao.sql.PcsTlEnergyconsumptiondtlSql;
import com.akranta.tpm.dao.sql.PcsTlEnergyconsumptionmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.PcsTlEnergyconsumptiondtl;
import com.akranta.tpm.model.PcsTlEnergyconsumptionmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class PcsTlEnergyconsumptionmstDaoImpl implements PcsTlEnergyconsumptionmstDao {


	private DBActionTemplate dbActionTemplate; 

	public PcsTlEnergyconsumptionmstDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate =  dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlEnergyconsumptionmst create(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlEnergyconsumptionmstSql pcsTlEnergyconsumptionmstSql = new PcsTlEnergyconsumptionmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PcsTlEnergyconsumptiondtlSql pcsTlEnergyconsumptiondtlSql = new PcsTlEnergyconsumptiondtlSql();
		
		try{
			String sql = PcsTlEnergyconsumptionmstSql.checkDate(pcsTlEnergyconsumptionmst.getEncmDate());
			String keyid = dbActionTemplate.getSingleValue(sql);
			
			List<PcsTlEnergyconsumptiondtl> newPcsTlEnergyconsumptiondtl = pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl();
			if(!UIUtils.isValidKeyId(keyid))
			{
				CommonMessage.debugMsg("master table seq gen");
				pcsTlEnergyconsumptionmst.setEncmKeyid(dbActionTemplate.getSequenceNumber(PcsTlEnergyconsumptionmstSql.TBL_PCS_TL_ENERGYCONSUMPTIONMST, 9, "ENC", "", "Y")); // set the sequnce number 
				sqls.add(PcsTlEnergyconsumptionmstSql.getInsertSql(pcsTlEnergyconsumptionmstSql.getEncmDbFields(), pcsTlEnergyconsumptionmst.getSaveArray())); // add insert sql for master table
			}
			else
				pcsTlEnergyconsumptionmst.setEncmKeyid(keyid);
			
			
			GenSequenceNumber seq = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), PcsTlEnergyconsumptiondtlSql.TBL_PCS_TL_ENERGYCONSUMPTIONDTL, 9, "END", "", "Y");
			//CommonMessage.debugMsg("detail table seq gen.."+seq.getSequnceNumber());
			for( PcsTlEnergyconsumptiondtl pcsTlEnergyconsumptiondtl :newPcsTlEnergyconsumptiondtl)
			{
				if(!UIUtils.isValidKeyId(pcsTlEnergyconsumptiondtl.getEncdEncmKeyid()))
				{	
					if(pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl()!= null && pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl().size()>0) // check for detail table data
					{	
						//CommonMessage.debugMsg("detail table seq gen.."+seq.getSequnceNumber());
						
						//PcsTlEnergyconsumptiondtl pcsTlEnergyconsumptiondtl = (PcsTlEnergyconsumptiondtl)pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl().get(0); // get detail info from list in empployee object				
						pcsTlEnergyconsumptiondtl.setEncdKeyid(seq.getSequnceNumber()); 
						pcsTlEnergyconsumptiondtl.setEncdEncmKeyid(pcsTlEnergyconsumptionmst.getEncmKeyid());
						sqls.add(PcsTlEnergyconsumptiondtlSql.getInsertSql(pcsTlEnergyconsumptiondtlSql.getEncdDbFields(), pcsTlEnergyconsumptiondtl.getSaveArray())); // add insert sql for master table
					}
				}
				else
				{
					sqls.add(PcsTlEnergyconsumptiondtlSql.getUpdateSql(pcsTlEnergyconsumptiondtlSql.getEncdDbFields(), pcsTlEnergyconsumptiondtl.getSaveArray()));
				}
			}
			CommonMessage.debugMsg("sqls...."+sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlEnergyconsumptionmst;
	}
	
	public PcsTlEnergyconsumptionmst update(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlEnergyconsumptionmstSql pcsTlEnergyconsumptionmstSql = new PcsTlEnergyconsumptionmstSql();
		try {

			sqls.add(PcsTlEnergyconsumptionmstSql.getUpdateSql(pcsTlEnergyconsumptionmstSql.getEncmDbFields(), pcsTlEnergyconsumptionmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlEnergyconsumptionmst;
	}
	
	public PcsTlEnergyconsumptionmst delete(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		List<PcsTlEnergyconsumptiondtl> newPcsTlEnergyconsumptiondtl = pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl();
		PcsTlEnergyconsumptionmstSql pcsTlEnergyconsumptionSql = new PcsTlEnergyconsumptionmstSql();
		PcsTlEnergyconsumptiondtlSql pcsTlEnergyconsumptiondtlSql = new PcsTlEnergyconsumptiondtlSql();
		try {
			for( PcsTlEnergyconsumptiondtl pcsTlEnergyconsumptiondtl :newPcsTlEnergyconsumptiondtl)
			{
				if(UIUtils.isValidKeyId(pcsTlEnergyconsumptiondtl.getEncdEncmKeyid()))
				{	
					if(pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl()!= null && pcsTlEnergyconsumptionmst.getPcsTlEnergyconsumptiondtl().size()>0) // check for detail table data
					{
						sqls.add(PcsTlEnergyconsumptiondtlSql.getDeleteSql(pcsTlEnergyconsumptiondtlSql.getEncdDbFields(), pcsTlEnergyconsumptiondtl.getSaveArray()));
					}
				}
			}
			//sqls.add(PcsTlEnergyconsumptionmstSql.getDeleteSql(pcsTlEnergyconsumptionSql.getEncmDbFields(), pcsTlEnergyconsumptionmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlEnergyconsumptionmst;
	}

public List<String[]> getEnergyConsumptionDetails(CommonFilter commonFilter) throws Exception {
		
		try
		{
			CommonMessage.debugMsg("inside daoimpl.....");
			List<String> paramValues = new ArrayList<String>();
			
			
			String condParams = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			paramValues.add(condParams);			
			paramValues.add(commonParams);
			//CommonMessage.debugMsg("Param Values....."+paramValues);
			CommonMessage.debugMsg("Param Values....."+paramValues);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ENERGYCONSUMPTION", paramValues);
			CommonMessage.debugMsg("dataList Size....."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
	}
		catch (Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Error...."+e.getMessage());
			throw new Exception(e.getMessage()); 
			
		}
	
	}

@Override
public Workbook getEnergyConsumptionExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
	
	ResultSet rs = null;
	   try{
		
		rs =   getEnergyConsumptionResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colModel);
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs,rptFormat,1,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }  
}

private ResultSet getEnergyConsumptionResultSet(CommonFilter commonFilter) throws Exception {
	
	List<String> paramValues = getFilterParamValues(commonFilter);	
	return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_ENERGYCONSUMPTION", paramValues);
}

private List<String> getFilterParamValues(CommonFilter commonFilter) {
	List<String> paramValues = new ArrayList<String>();	
	
	String condParams = FilterCondSql.getPCSRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	
	paramValues.add(condParams);			
	paramValues.add(commonParams);
	return paramValues;
}

@Override
public List<String[]> getEnergyConsumptionEntryRpt(CommonFilter commonFilter)throws Exception {
	
	try
	{
		CommonMessage.debugMsg("inside daoimpl.....");
		List<String> paramValues = getFilterParamValues(commonFilter);	
		
		CommonMessage.debugMsg("Param Values.....");
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ENERGYCONSUENTRYREPORT", paramValues);
		CommonMessage.debugMsg("dataList Size....."+dataList.size());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
}
	catch (Exception e)
	{
		e.printStackTrace();
		CommonMessage.debugMsg("Error...."+e.getMessage());
		throw new Exception(e.getMessage()); 
		
	}
}

@Override
public Workbook getEnergyConsumptionEntryRptExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
	
	ResultSet rs = null;
	   try{
		
		rs =   getEnergyConsumptionEntryRptResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colModel);
		return excelUtils.writeToExcel(rs,rptFormat,1,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }  
}

private ResultSet getEnergyConsumptionEntryRptResultSet(CommonFilter commonFilter) throws Exception {
	List<String> paramValues = getFilterParamValues(commonFilter);	
	return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_ENERGYCONSUENTRYREPORT", paramValues);
}
	
}

