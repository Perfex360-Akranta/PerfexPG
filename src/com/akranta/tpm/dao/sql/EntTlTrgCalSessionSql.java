package com.akranta.tpm.dao.sql;

public class EntTlTrgCalSessionSql {

	public static final String TBL_ENT_TL_TRGCALSESSION = "ENT_TL_TRGCALSESSION";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid, etcm_keyid, etcm_flid, name, sessiondate, fromdate,tilldate,dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active
		, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTrgCalSessionSql()
	{
		ftymDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCS_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldName = "ETCS_ETCM_KEYID";
		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_flid.ordinal() ].fieldName = "ETCS_ETCM_FLID";
		ftymDbFields[ tableFldConstants.etcm_flid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.name.ordinal() ].fieldName = "ETCS_NAME";
		ftymDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.sessiondate.ordinal() ].fieldName = "ETCS_SESSIONDATE";
		ftymDbFields[ tableFldConstants.sessiondate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "ETCS_FROMDATE";
		ftymDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'T';

		ftymDbFields[ tableFldConstants.tilldate.ordinal() ].fieldName = "ETCS_TILLDATE";
		ftymDbFields[ tableFldConstants.tilldate.ordinal() ].fieldType = 'T';

		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldName = "ETCS_DATEADD";
		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCS_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCS_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCS_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCS_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCS_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCS_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCS_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCS_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALSESSION, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALSESSION, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALSESSION ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

