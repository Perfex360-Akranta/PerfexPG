package com.akranta.tpm.dao.sql;

public class AbnTlAbnhistorydtlSql {

	public static final String TBL_ABN_TL_ABNHISTORYDTL = "ABN_TL_ABNHISTORYDTL";  

	TableFieldType [] abnhDbFields = null;

	public enum   tableFldConstants
	{
		keyid, abnm_keyid, type, date, reasons, changeby, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAbnhDbFields() {
		return abnhDbFields;
	}

	public AbnTlAbnhistorydtlSql()
	{
		abnhDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			abnhDbFields[ i ] = new TableFieldType();
		}
		abnhDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ABNH_KEYID";
		abnhDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.abnm_keyid.ordinal() ].fieldName = "ABNH_ABNM_KEYID";
		abnhDbFields[ tableFldConstants.abnm_keyid.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.type.ordinal() ].fieldName = "ABNH_TYPE";
		abnhDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.date.ordinal() ].fieldName = "ABNH_DATE";
		abnhDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		abnhDbFields[ tableFldConstants.reasons.ordinal() ].fieldName = "ABNH_REASONS";
		abnhDbFields[ tableFldConstants.reasons.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.changeby.ordinal() ].fieldName = "ABNH_CHANGEBY";
		abnhDbFields[ tableFldConstants.changeby.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ABNH_TEMPFIELD1";
		abnhDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ABNH_TEMPFIELD2";
		abnhDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ABNH_TEMPFIELD3";
		abnhDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ABNH_TEMPFIELD4";
		abnhDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ABNH_TEMPFIELD5";
		abnhDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ABNH_TEMPFIELD6";
		abnhDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ABNH_ACTIVE";
		abnhDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		abnhDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ABNH_CREATEDBY";
		abnhDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		abnhDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ABNH_CREATEDON";
		abnhDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		abnhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ABNH_MODIFIEDON";
		abnhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ABN_TL_ABNHISTORYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ABN_TL_ABNHISTORYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ABN_TL_ABNHISTORYDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

