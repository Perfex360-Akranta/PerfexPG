package com.akranta.tpm.dao.sql;

public class EntTlFacultymstSql {

	public static final String TBL_ENT_TL_FACULTYMST = "ENT_TL_FACULTYMST";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, type, fact_keyid, empm_keyid, potential, address1
		, address2, address3, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlFacultymstSql()
	{
		ftymDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FTYM_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.code.ordinal() ].fieldName = "FTYM_CODE";
		ftymDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.name.ordinal() ].fieldName = "FTYM_NAME";
		ftymDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.type.ordinal() ].fieldName = "FTYM_TYPE";
		ftymDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "FTYM_FACT_KEYID";
		ftymDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "FTYM_EMPM_KEYID";
		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.potential.ordinal() ].fieldName = "FTYM_POTENTIAL";
		ftymDbFields[ tableFldConstants.potential.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.address1.ordinal() ].fieldName = "FTYM_ADDRESS1";
		ftymDbFields[ tableFldConstants.address1.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.address2.ordinal() ].fieldName = "FTYM_ADDRESS2";
		ftymDbFields[ tableFldConstants.address2.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.address3.ordinal() ].fieldName = "FTYM_ADDRESS3";
		ftymDbFields[ tableFldConstants.address3.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "FTYM_REMARKS";
		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FTYM_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FTYM_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FTYM_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FTYM_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FTYM_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FTYM_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FTYM_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FTYM_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FTYM_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FACULTYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FACULTYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FACULTYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

