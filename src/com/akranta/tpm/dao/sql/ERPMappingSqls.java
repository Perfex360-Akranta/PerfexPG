package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class ERPMappingSqls {
	
	public static String getSapTlPerfexMappingMstInsertSql(){
		return " insert into SAP_TL_PERFEXMAPPINGMST values(?,?,?,?,?,?,?,?,'-','-','-','-','Y',?,sysdate,sysdate)";
	}

	public static String getSapTlPerfexMappingMstUpdateSql(){
		StringBuilder sql = new StringBuilder();
		sql.append(" update SAP_TL_PERFEXMAPPINGMST set SPMM_PROCESSNAME =?,");
		sql.append(" SPMM_PROCESSCODE =?,SPMM_WEBSERVICENAME=?,SPMM_WEBSERVICEURI=?,SPMM_SCENARIOID=?," );
		sql.append(" SPMM_PROCESSTYPE=?,SPMM_PREFIX=?, SPMM_CREATEDBY=?,SPMM_MODIFIEDON=sysdate where SPMM_KEYID=?");
		return sql.toString();
	}
	
	public static String getSapTlPerfexMappingDtlInsertSql(){
		return " insert into SAP_TL_PERFEXMAPPINGDTL values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y','-','-','-','-','Y',?,sysdate,sysdate)";
	}

	public static String getSapTlPerfexMappingDtlUpdateSql(){
		StringBuilder sql = new StringBuilder();
		sql.append(" update SAP_TL_PERFEXMAPPINGDTL set SPMD_SPMM_KEYID =?,");
		sql.append(" SPMD_PERFEX_TABLE_NAME =?,SPMD_PERFEX_COLUMN_NAME=?,SPMD_PERFEX_COLUMN_TYPE=?,SPMD_PERFEX_COLUMN_WIDTH=?," );
		sql.append(" SPMD_PERFEX_REF_TABLE=?,SPMD_PERFEX_REF_COLUMN=?,SPMD_PERFEX_REF_MAPCOLUMN=?," );
		sql.append(" SPMD_SAP_TABLE_NAME=?,SPMD_SAP_COLUMN_NAME=?,SPMD_SAP_COLUMN_TYPE=?," );
		sql.append(" SPMD_SAP_COLUMN_WIDTH=?,SPMD_INCLUDEREQUEST=?,SPMD_INCLUDERESPONSE=?," );
		sql.append(" SPMD_INCLUDECONDITION=?,SPMD_CONDITIONVALUE=?,SPMD_ISFORALERT=?," );		
		sql.append(" SPMD_PARENTID =?,SPMD_ISFROMTABLE = 'Y', SPMD_CREATEDBY=?, SPMD_MODIFIEDON=sysdate where SPMD_KEYID=?");
		return sql.toString();
	}
	
	public static String getSapTlPerfexMappingDtlData(String masterId,String isRequestData,String isResponseData){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SPMD_KEYID,SPMD_PERFEX_TABLE_NAME,SPMD_PERFEX_COLUMN_NAME,SPMD_PERFEX_COLUMN_TYPE,SPMD_PERFEX_COLUMN_WIDTH,");
		sql.append(" SPMD_PERFEX_REF_TABLE,SPMD_PERFEX_REF_COLUMN,SPMD_PERFEX_REF_MAPCOLUMN,");
		sql.append(" SPMD_INCLUDECONDITION, SPMD_SAP_TABLE_NAME,SPMD_SAP_COLUMN_NAME,SPMD_SAP_COLUMN_TYPE,SPMD_SAP_COLUMN_WIDTH,'',SPMD_ISFROMTABLE FROM ");
		sql.append(" SAP_TL_PERFEXMAPPINGDTL where SPMD_SPMM_KEYID ='"+masterId +"'");
		if( "Y".equals(isRequestData))
			sql.append(" AND ( SPMD_INCLUDEREQUEST ='Y' OR SPMD_INCLUDECONDITION = 'Y' ) " );
		if( "Y".equals(isResponseData))
			sql.append(" AND ( SPMD_INCLUDERESPONSE ='Y' OR SPMD_INCLUDECONDITION = 'Y' ) " );
		
		sql.append(" ORDER BY SPMD_PERFEX_TABLE_NAME ,DECODE(SPMD_INCLUDEREQUEST ,'Y',1,2)" );
		
		CommonFunctions.debugMsg(" sql in ERP" + sql  );
		return sql.toString();
	}
	public static String selectMasterSql(String keyid){
		return " select * from  SAP_TL_PERFEXMAPPINGMST where SPMM_KEYID='"+keyid+"'";
	}
	public static String deleteDtlSql(){
		return " delete  from  SAP_TL_PERFEXMAPPINGDTL where SPMD_KEYID in (?) " ;
	}
}
