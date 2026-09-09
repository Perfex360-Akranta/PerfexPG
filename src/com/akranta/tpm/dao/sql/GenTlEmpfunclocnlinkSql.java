package com.akranta.tpm.dao.sql;

public class GenTlEmpfunclocnlinkSql {

	public static final String TBL_GEN_TL_EMPFUNCLOCNLINK = "GEN_TL_EMPFUNCLOCNLINK";  

	TableFieldType [] efllDbFields = null;

	public enum   tableFldConstants
	{
		keyid, employeeid, funclocn, funclocntype, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getEfllDbFields() {
		return efllDbFields;
	}

	public GenTlEmpfunclocnlinkSql()
	{
		efllDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			efllDbFields[ i ] = new TableFieldType();
		}
		efllDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EFLL_KEYID";
		efllDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		efllDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "EFLL_EMPLOYEEID";
		efllDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		efllDbFields[ tableFldConstants.funclocn.ordinal() ].fieldName = "EFLL_FUNCLOCN";
		efllDbFields[ tableFldConstants.funclocn.ordinal() ].fieldType = 'V';

		efllDbFields[ tableFldConstants.funclocntype.ordinal() ].fieldName = "EFLL_FUNCLOCNTYPE";
		efllDbFields[ tableFldConstants.funclocntype.ordinal() ].fieldType = 'V';

		efllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EFLL_ACTIVE";
		efllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		efllDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EFLL_CREATEDBY";
		efllDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		efllDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EFLL_CREATEDON";
		efllDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		efllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EFLL_MODIFIEDON";
		efllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPFUNCLOCNLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPFUNCLOCNLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPFUNCLOCNLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	/*public void setEfll_keyid(String sequenceNumber) {
		// TODO Auto-generated method stub
		
	}*/

}

