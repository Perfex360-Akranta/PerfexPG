package com.akranta.tpm.dao.sql;

public class GenTlEmpcirclelinkSql {

	public static final String TBL_GEN_TL_EMPCIRCLELINK = "GEN_TL_EMPCIRCLELINK";  

	TableFieldType [] ecrlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, empm_keyid, circleid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEcrlDbFields() {
		return ecrlDbFields;
	}

	public GenTlEmpcirclelinkSql()
	{
		ecrlDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			ecrlDbFields[ i ] = new TableFieldType();
		}
		ecrlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ECRL_KEYID";
		ecrlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ecrlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "ECRL_EMPM_KEYID";
		ecrlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		ecrlDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "ECRL_CIRCLEID";
		ecrlDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		ecrlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ECRL_ACTIVE";
		ecrlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ecrlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ECRL_CREATEDBY";
		ecrlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ecrlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ECRL_CREATEDON";
		ecrlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ecrlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ECRL_MODIFIEDON";
		ecrlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPCIRCLELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPCIRCLELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPCIRCLELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

