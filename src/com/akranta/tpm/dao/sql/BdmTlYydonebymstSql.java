package com.akranta.tpm.dao.sql;

public class BdmTlYydonebymstSql {

	public static final String TBL_BDM_TL_YYDONEBYMST = "BDM_TL_YYDONEBYMST";  

	TableFieldType [] bdm_DbFields = null;

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, empm_keyid, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBdm_DbFields() {
		return bdm_DbFields;
	}

	public BdmTlYydonebymstSql()
	{
		bdm_DbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			bdm_DbFields[ i ] = new TableFieldType();
		}
		
		
		bdm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWDB_KEYID";
		bdm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldName = "WWDB_WWMS_KEYID";
		bdm_DbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "WWDB_EMPM_KEYID";
		bdm_DbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WWDB_TEMPFIELD1";
		bdm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WWDB_TEMPFIELD2";
		bdm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WWDB_TEMPFIELD3";
		bdm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "WWDB_ACTIVE";
		bdm_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bdm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWDB_CREATEDBY";
		bdm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bdm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWDB_CREATEDON";
		bdm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bdm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWDB_MODIFIEDON";
		bdm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_YYDONEBYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_YYDONEBYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYDONEBYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

