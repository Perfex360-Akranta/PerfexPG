package com.akranta.tpm.dao.sql;

import java.util.ArrayList;
import java.util.List;

public class BAL_PlmTlSparedtlSql {

	public static final String TBL_BAL_PLM_TL_SPAREDTL = "BAL_PLM_TL_SPAREDTL";  

	TableFieldType [] pspdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, standardid, spareid, quantity, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPspdDbFields() {
		return pspdDbFields;
	}

	public BAL_PlmTlSparedtlSql()
	{
		pspdDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			pspdDbFields[ i ] = new TableFieldType();
		}
		pspdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PSPD_KEYID";
		pspdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pspdDbFields[ tableFldConstants.standardid.ordinal() ].fieldName = "PSPD_STANDARDID";
		pspdDbFields[ tableFldConstants.standardid.ordinal() ].fieldType = 'V';

		pspdDbFields[ tableFldConstants.spareid.ordinal() ].fieldName = "PSPD_SPAREID";
		pspdDbFields[ tableFldConstants.spareid.ordinal() ].fieldType = 'V';

		pspdDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "PSPD_QUANTITY";
		pspdDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		pspdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSPD_CREATEDBY";
		pspdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pspdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSPD_CREATEDON";
		pspdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pspdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSPD_MODIFIEDON";
		pspdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_SPAREDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_SPAREDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_SPAREDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getMultiSelectSprSql()
	{
		String sql = "Select 'False' as \"select\",SPRM_KEYID,SPRM_PARTNO,SPRM_PARTNAME,'1' as \"QtyReq\" from " + TableNames.TBL_GEN_TL_SPARESMST ;
		
		return sql;
	}
	
	public static String getSprNameSql()
	{
		String sql = "Select SPRM_PARTNAME from " + TableNames.TBL_GEN_TL_SPARESMST + " where SPRM_KEYID = ?";
		
		return sql;
	}
	

	
	public static String getSprpkupSql()
	{
		String sql = "Select * from " + TableNames.TBL_GEN_TL_SPARESMST ;
		
		return sql;
	}

	public static String getSparesPickupTbl() 
	{
		String sql = "Select 'False' as \"select\",PSPD_SPAREID, PSPD_KEYID,'' as standardid , SPRM_PARTNO, SPRM_PARTNAME ,SPRM_MAKE,SPRM_MODEL," +
				" PSPD_QUANTITY, PSPD_MODIFIEDON, PSPD_CREATEDON from " + TableNames.TBL_GEN_TL_SPARESMST +"," + TableNames.TBL_BAL_PLM_TL_SPAREDTL +
				 " where SPRM_KEYID = PSPD_SPAREID and PSPD_STANDARDID = ?";
		
		return sql;
	}

	public static String getDeleteSprSql() 
	{
		String sql ="delete from "+ TableNames.TBL_BAL_PLM_TL_SPAREDTL + " where PSPD_KEYID = ?";
		
		return sql;
	}
	public static String getDeleteAllSql()
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_SPAREDTL +" where PSPD_STANDARDID = ? ";
		
		
		return sql;
	}
	


}

