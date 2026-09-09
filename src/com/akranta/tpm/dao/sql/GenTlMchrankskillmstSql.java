package com.akranta.tpm.dao.sql;

public class GenTlMchrankskillmstSql {

	public static final String TBL_GEN_TL_MCHRANKSKILLMST = "GEN_TL_MCHRANKSKILLMST";  

	TableFieldType [] mrskDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, maximumpoints, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMrskDbFields() {
		return mrskDbFields;
	}

	public GenTlMchrankskillmstSql()
	{
		mrskDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			mrskDbFields[ i ] = new TableFieldType();
		}
		mrskDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MRSK_KEYID";
		mrskDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mrskDbFields[ tableFldConstants.name.ordinal() ].fieldName = "MRSK_NAME";
		mrskDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		mrskDbFields[ tableFldConstants.code.ordinal() ].fieldName = "MRSK_CODE";
		mrskDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		mrskDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldName = "MRSK_MAXIMUMPOINTS";
		mrskDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldType = 'N';

		mrskDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MRSK_ACTIVE";
		mrskDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mrskDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MRSK_CREATEDBY";
		mrskDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mrskDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MRSK_CREATEDON";
		mrskDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mrskDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MRSK_MODIFIEDON";
		mrskDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHRANKSKILLMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHRANKSKILLMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHRANKSKILLMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

