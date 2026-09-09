package com.akranta.tpm.dao.sql;

public class PcsTlLosscausemstSql {

	public static final String TBL_PCS_TL_LOSSCAUSEMST = "PCS_TL_LOSSCAUSEMST";  

	TableFieldType [] plcsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, phenomenaid, description, tempfield2, tempfield3
	}

	public TableFieldType[] getPlcsDbFields() {
		return plcsDbFields;
	}

	public PcsTlLosscausemstSql()
	{
		plcsDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			plcsDbFields[ i ] = new TableFieldType();
		}
		plcsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PLCS_KEYID";
		plcsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plcsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "PLCS_PHENOMENAID";
		plcsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		plcsDbFields[ tableFldConstants.description.ordinal() ].fieldName = "PLCS_DESCRIPTION";
		plcsDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		plcsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PLCS_TEMPFIELD2";
		plcsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		plcsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PLCS_TEMPFIELD3";
		plcsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSCAUSEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSCAUSEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSCAUSEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

