package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.dao.ERPDao;
import com.akranta.tpm.dao.sql.ERPMappingSqls;
import com.akranta.tpm.model.SapTlPerfexMappingDtl;
import com.akranta.tpm.model.SapTlPerfexMappingMst;
import com.akranta.tpm.utils.SAPMappingMst;

public class ERPDaoImpl implements ERPDao {
	
	private DBActionTemplate dbActionTemplate;
	public ERPDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate =  dbActionTemplate;
	}
	
	public List<String[]> getTableDetails(String tableName) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COLUMN_NAME,DATA_TYPE dataType, data_length ");
		sql.append(" FROM user_tab_cols where table_name = '"+tableName +"' order by column_id " );
		com.akranta.tpm.utils.CommonFunctions.debugMsg(sql);
		return dbActionTemplate.getDataList(sql.toString());
	}

	@Override
	public List<String[]> getAllProcess() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SPMM_KEYID, SPMM_PROCESSNAME,SPMM_WEBSERVICENAME, SPMM_WEBSERVICEURI,SPMM_SCENARIOID ");
		sql.append(" FROM SAP_TL_PERFEXMAPPINGMST where SPMM_ACTIVE = 'Y' order by SPMM_PROCESSNAME " );
		
		return dbActionTemplate.getDataList(sql.toString());
	}

	@Override
	public void save(SapTlPerfexMappingMst sapTlPerfexMappingMst) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		Map<Integer,List<Object[]>> saveData = new HashMap<Integer,List<Object[]>>();
		
		List<Object[]> masterDataList = new ArrayList<Object[]>();
		List<int[]> dataTypesList = new ArrayList<int[]>();
		
		if( ! CommonFunctions.isValidKeyId(sapTlPerfexMappingMst.getSpmmKeyid()) ){
			sqls.add(ERPMappingSqls.getSapTlPerfexMappingMstInsertSql());
			sapTlPerfexMappingMst.setSpmmKeyid(dbActionTemplate.getSequenceNumber("SAP_TL_PERFEXMAPPINGMST", 7, "SPM", null, null));
			Object[] masterData =   {   sapTlPerfexMappingMst.getSpmmKeyid(),
											sapTlPerfexMappingMst.getSpmmProcessname(),
											sapTlPerfexMappingMst.getSpmmProcesscode(),
											sapTlPerfexMappingMst.getSpmmWebservicename(),
											sapTlPerfexMappingMst.getSpmmWebserviceuri(),
											sapTlPerfexMappingMst.getSpmmScenarioid(),
											sapTlPerfexMappingMst.getSpmmProcesstype(),
											sapTlPerfexMappingMst.getSpmmPrefix(),
											sapTlPerfexMappingMst.getSpmmCreatedby()
										};
			masterDataList.add(masterData);
			int [] masterDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
			dataTypesList.add(masterDataType);
		}	
		else{
			sqls.add(ERPMappingSqls.getSapTlPerfexMappingMstUpdateSql());
			Object[] masterData  =   { 
											sapTlPerfexMappingMst.getSpmmProcessname(),
											sapTlPerfexMappingMst.getSpmmProcesscode(),
											sapTlPerfexMappingMst.getSpmmWebservicename(),
											sapTlPerfexMappingMst.getSpmmWebserviceuri(),
											sapTlPerfexMappingMst.getSpmmScenarioid(),
											sapTlPerfexMappingMst.getSpmmProcesstype(),
											sapTlPerfexMappingMst.getSpmmPrefix(),
											sapTlPerfexMappingMst.getSpmmCreatedby(),
											sapTlPerfexMappingMst.getSpmmKeyid()
										};

			masterDataList.add(masterData);
			int [] masterDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
			dataTypesList.add(masterDataType);

		}
		
		saveData.put(0, masterDataList);
		populateDtlData(sapTlPerfexMappingMst,sqls,saveData,dataTypesList);
		
		dbActionTemplate.executeBatch(sqls, saveData, dataTypesList);	
			
	}
	
	private void populateDtlData(SapTlPerfexMappingMst sapTlPerfexMappingMst,List<String> sqls, Map<Integer,List<Object[]>> dataList,List<int[]>dataTypes) throws Exception{
		
		List<SapTlPerfexMappingDtl> sapTlPerfexMappingDtlList =sapTlPerfexMappingMst.getSapTlPerfexMappingDtlList();
		List<Object[]> sapInsDtlList = new ArrayList<Object[]>();
		List<Object[]> sapUpdateDtlList = new ArrayList<Object[]>();
		int index = 1; 
		for(SapTlPerfexMappingDtl sapTlPerfexMappingDtl:sapTlPerfexMappingDtlList){
			
			if( ! CommonFunctions.isValidKeyId(sapTlPerfexMappingDtl.getSpmdKeyid()) ){
				
				
				
				sapTlPerfexMappingDtl.setSpmdKeyid(dbActionTemplate.getSequenceNumber("SAP_TL_PERFEXMAPPINGDTL", 7, "SPD", null, null));
				Object[] dtlDatatmp =   {   sapTlPerfexMappingDtl.getSpmdKeyid(),
											    sapTlPerfexMappingMst.getSpmmKeyid(),
											    sapTlPerfexMappingDtl.getSpmdPerfexTableName(),
											    sapTlPerfexMappingDtl.getSpmdPerfexColumnName(),
											    sapTlPerfexMappingDtl.getSpmdPerfexColumnType(),
											    sapTlPerfexMappingDtl.getSpmdPerfexColumnWidth(),
											    sapTlPerfexMappingDtl.getSpmdPerfexRefTable(),
											    sapTlPerfexMappingDtl.getSpmdPerfexRefColumn(),
											    sapTlPerfexMappingDtl.getSpmdPerfexRefMapcolumn(),
											    sapTlPerfexMappingDtl.getSpmdSapTableName(),
											    sapTlPerfexMappingDtl.getSpmdSapColumnName(),
											    sapTlPerfexMappingDtl.getSpmdSapColumnType(),
											    sapTlPerfexMappingDtl.getSpmdSapColumnWidth(),
											    sapTlPerfexMappingDtl.getSpmdIncluderequest(),
											    sapTlPerfexMappingDtl.getSpmdIncluderesponse(),
											    sapTlPerfexMappingDtl.getSpmdIncludecondition(),
											    sapTlPerfexMappingDtl.getSpmdConditionvalue(),
											    sapTlPerfexMappingDtl.getSpmdIsforalert(),
											    sapTlPerfexMappingDtl.getSpmdParentid(),
											    //sapTlPerfexMappingDtl.getSpmdIsfromtable(),
											    sapTlPerfexMappingDtl.getSpmdCreatedby()
											};
				sapInsDtlList.add(dtlDatatmp);
			}	
			else{
				
				Object[] dtlDatatmp =   {   
										    sapTlPerfexMappingMst.getSpmmKeyid(),
										    sapTlPerfexMappingDtl.getSpmdPerfexTableName(),
										    sapTlPerfexMappingDtl.getSpmdPerfexColumnName(),
										    sapTlPerfexMappingDtl.getSpmdPerfexColumnType(),
										    sapTlPerfexMappingDtl.getSpmdPerfexColumnWidth(),
										    sapTlPerfexMappingDtl.getSpmdPerfexRefTable(),
										    sapTlPerfexMappingDtl.getSpmdPerfexRefColumn(),
										    sapTlPerfexMappingDtl.getSpmdPerfexRefMapcolumn(),
										    sapTlPerfexMappingDtl.getSpmdSapTableName(),
										    sapTlPerfexMappingDtl.getSpmdSapColumnName(),
										    sapTlPerfexMappingDtl.getSpmdSapColumnType(),
										    sapTlPerfexMappingDtl.getSpmdSapColumnWidth(),
										    sapTlPerfexMappingDtl.getSpmdIncluderequest(),
										    sapTlPerfexMappingDtl.getSpmdIncluderesponse(),
										    sapTlPerfexMappingDtl.getSpmdIncludecondition(),
										    sapTlPerfexMappingDtl.getSpmdConditionvalue(),
										    sapTlPerfexMappingDtl.getSpmdIsforalert(),
										    sapTlPerfexMappingDtl.getSpmdParentid(),
										    //sapTlPerfexMappingDtl.getSpmdIsfromtable(),
										    sapTlPerfexMappingDtl.getSpmdCreatedby(),
										    sapTlPerfexMappingDtl.getSpmdKeyid()
										};
				com.akranta.tpm.utils.CommonFunctions.debugMsg(dtlDatatmp);
				sapUpdateDtlList.add(dtlDatatmp);
			}
		}
		
		
		if( sapInsDtlList.size() > 0 ){
			sqls.add(ERPMappingSqls.getSapTlPerfexMappingDtlInsertSql());
			dataList.put(index++,sapInsDtlList);
			int [] insDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR }; //Types.VARCHAR,
			dataTypes.add(insDataType);
		}	
		if( sapUpdateDtlList.size() > 0 ){
			sqls.add(ERPMappingSqls.getSapTlPerfexMappingDtlUpdateSql());
			dataList.put(index++,sapUpdateDtlList);
			int [] updDataType = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR ,Types.VARCHAR,Types.VARCHAR, Types.VARCHAR};//Types.VARCHAR,
			dataTypes.add(updDataType);
		}	

		
	}

	@Override
	public List<String[]> getDetailsData(String masterId, String isRequestData,
			String isResponseData) throws Exception {
		// TODO Auto-generated method stub
		String sql = ERPMappingSqls.getSapTlPerfexMappingDtlData(masterId,isRequestData,isResponseData);
		
		return dbActionTemplate.getDataList(sql) ;
	}
	
	
	public SapTlPerfexMappingMst getSapPerfexMappingMst(String keyid) throws Exception{
		String sql = ERPMappingSqls.selectMasterSql(keyid);
		ResultSet rs = null;
		try{
			System.out.println(sql+ " sql in erp dao impl");
			rs = dbActionTemplate.getData(sql);
			SapTlPerfexMappingMst sapTlPerfexMappingMst = new SapTlPerfexMappingMst(); 
			while(rs.next()){
				sapTlPerfexMappingMst.setSpmmKeyid(rs.getString("SPMM_KEYID"));
				sapTlPerfexMappingMst.setSpmmProcessname(rs.getString("SPMM_PROCESSNAME"));
				sapTlPerfexMappingMst.setSpmmProcesscode(rs.getString("SPMM_PROCESSCODE"));
				sapTlPerfexMappingMst.setSpmmWebservicename(rs.getString("SPMM_WEBSERVICENAME"));
				sapTlPerfexMappingMst.setSpmmWebserviceuri(rs.getString("SPMM_WEBSERVICEURI"));
				sapTlPerfexMappingMst.setSpmmScenarioid(rs.getString("SPMM_SCENARIOID"));
				sapTlPerfexMappingMst.setSpmmProcesstype(rs.getString("SPMM_PROCESSTYPE"));
				sapTlPerfexMappingMst.setSpmmPrefix(rs.getString("SPMM_PREFIX"));
			}
			return sapTlPerfexMappingMst;
		}finally{
			dbActionTemplate.closeConnection(rs, rs.getStatement(), null,null, rs.getStatement().getConnection());
		}
	}
	
	public String getPrimaryKeyField(String tableName) throws Exception{
		StringBuilder sql = new StringBuilder("select Tc.column_name,C.CONSTRAINT_TYPE from user_constraints c,user_tab_columns tc where");
		sql.append(" c.TABLE_NAME = tc.TABLE_NAME and c.TABLE_NAME = upper('"+tableName+"')");
		sql.append(" and c.CONSTRAINT_TYPE = 'P'  and column_id = 1");
		
		return dbActionTemplate.getSingleValue(sql.toString());
	}
	
	public void deleteSapPerfexMappingDtl(String dtlIds) throws Exception{
		String sql = ERPMappingSqls.deleteDtlSql();
		Object[] values =   { dtlIds };
		int [] dataTypes = {Types.VARCHAR };
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}
	
	
	public List<SAPMappingMst> getSapPerfexMappingConfig() throws Exception{
		try{
			StringBuilder sql = new StringBuilder("SELECT SPMM_PROCESSNAME,SPMM_PROCESSCODE,SPMM_WEBSERVICENAME,SPMM_WEBSERVICEURI, ");
			sql.append(" SPMM_SCENARIOID,SPMM_PROCESSTYPE,SPMM_PREFIX from SAP_TL_PERFEXMAPPINGMST where SPMM_ACTIVE ='Y' ");
			SAPMappingMst sapMappingMst = new SAPMappingMst();
			
			return  ( List<SAPMappingMst>) dbActionTemplate.getDataList(sql.toString(), sapMappingMst);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
