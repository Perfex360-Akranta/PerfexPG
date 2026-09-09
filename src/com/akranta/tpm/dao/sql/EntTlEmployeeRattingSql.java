package com.akranta.tpm.dao.sql;

public class EntTlEmployeeRattingSql {

	public static final String TBL_ENT_TL_EMPLOYEE_RATTING = "ENT_TL_EMPLOYEE_RATTING";  

	TableFieldType [] emraDbFields = null;

	public enum   tableFldConstants
	{
		keyid, empkeyid, ratting, date, remarks, rattingby, active, createdby
		, tempfield1, tempfield2, tempfield3, tempfield4, createdon, modifiedon
	}

	public TableFieldType[] getEmraDbFields() {
		return emraDbFields;
	}

	public EntTlEmployeeRattingSql()
	{
		emraDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			emraDbFields[ i ] = new TableFieldType();
		}
		emraDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EMRA_KEYID";
		emraDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.empkeyid.ordinal() ].fieldName = "EMRA_EMPKEYID";
		emraDbFields[ tableFldConstants.empkeyid.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.ratting.ordinal() ].fieldName = "EMRA_RATTING";
		emraDbFields[ tableFldConstants.ratting.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.date.ordinal() ].fieldName = "EMRA_DATE";
		emraDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		emraDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "EMRA_REMARKS";
		emraDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.rattingby.ordinal() ].fieldName = "EMRA_RATTINGBY";
		emraDbFields[ tableFldConstants.rattingby.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EMRA_ACTIVE";
		emraDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		emraDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EMRA_CREATEDBY";
		emraDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		emraDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EMRA_TEMPFIELD1";
		emraDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		emraDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EMRA_TEMPFIELD2";
		emraDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		emraDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EMRA_TEMPFIELD3";
		emraDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		emraDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "EMRA_TEMPFIELD4";
		emraDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		emraDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EMRA_CREATEDON";
		emraDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		emraDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMRA_MODIFIEDON";
		emraDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_EMPLOYEE_RATTING, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_EMPLOYEE_RATTING, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_EMPLOYEE_RATTING ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

