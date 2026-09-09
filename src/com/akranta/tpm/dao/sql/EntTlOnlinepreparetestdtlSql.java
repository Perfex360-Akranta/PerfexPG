package com.akranta.tpm.dao.sql;

public class EntTlOnlinepreparetestdtlSql {

	public static final String TBL_ENT_TL_ONLINEPREPARETESTDTL = "ENT_TL_ONLINEPREPARETESTDTL";  

	TableFieldType [] olpdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, olpm_keyid, olqm_keyid,sortorderno, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getOlpdDbFields() {
		return olpdDbFields;
	}

	public EntTlOnlinepreparetestdtlSql()
	{
		olpdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			olpdDbFields[ i ] = new TableFieldType();
		}
		olpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OLPD_KEYID";
		olpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		olpdDbFields[ tableFldConstants.olpm_keyid.ordinal() ].fieldName = "OLPD_OLPM_KEYID";
		olpdDbFields[ tableFldConstants.olpm_keyid.ordinal() ].fieldType = 'V';

		olpdDbFields[ tableFldConstants.olqm_keyid.ordinal() ].fieldName = "OLPD_OLQM_KEYID";
		olpdDbFields[ tableFldConstants.olqm_keyid.ordinal() ].fieldType = 'V';
		
		olpdDbFields[ tableFldConstants.sortorderno.ordinal() ].fieldName = "OLPD_SORTORDERNO";
		olpdDbFields[ tableFldConstants.sortorderno.ordinal() ].fieldType = 'N';

		olpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OLPD_TEMPFIELD1";
		olpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OLPD_TEMPFIELD2";
		olpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OLPD_TEMPFIELD3";
		olpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OLPD_TEMPFIELD4";
		olpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OLPD_TEMPFIELD5";
		olpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OLPD_CREATEDBY";
		olpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		olpdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OLPD_ACTIVE";
		olpdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		olpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OLPD_CREATEDON";
		olpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		olpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OLPD_MODIFIEDON";
		olpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ONLINEPREPARETESTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ONLINEPREPARETESTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ONLINEPREPARETESTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

