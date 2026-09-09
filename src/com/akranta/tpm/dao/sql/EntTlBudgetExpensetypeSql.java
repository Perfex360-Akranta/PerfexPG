package com.akranta.tpm.dao.sql;

public class EntTlBudgetExpensetypeSql {

	public static final String TBL_ENT_TL_BUDGET_EXPENSETYPE = "ENT_TL_BUDGET_EXPENSETYPE";  

	TableFieldType [] ebetDbFields = null;

	public enum   tableFldConstants
	{
		keyid, description, code, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEbetDbFields() {
		return ebetDbFields;
	}

	public EntTlBudgetExpensetypeSql()
	{
		ebetDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			ebetDbFields[ i ] = new TableFieldType();
		}
		ebetDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EBET_KEYID";
		ebetDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ebetDbFields[ tableFldConstants.description.ordinal() ].fieldName = "EBET_DESCRIPTION";
		ebetDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		ebetDbFields[ tableFldConstants.code.ordinal() ].fieldName = "EBET_CODE";
		ebetDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		ebetDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EBET_TEMPFIELD1";
		ebetDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ebetDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EBET_TEMPFIELD2";
		ebetDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ebetDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EBET_TEMPFIELD3";
		ebetDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ebetDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EBET_ACTIVE";
		ebetDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ebetDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EBET_CREATEDBY";
		ebetDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ebetDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EBET_CREATEDON";
		ebetDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ebetDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EBET_MODIFIEDON";
		ebetDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BUDGET_EXPENSETYPE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BUDGET_EXPENSETYPE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BUDGET_EXPENSETYPE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

