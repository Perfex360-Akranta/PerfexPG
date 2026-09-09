package com.akranta.tpm.dao.sql;

public class GenTlRolemstSql {

	public static final String TBL_GEN_TL_ROLEMST = "GEN_TL_ROLEMST";  

	TableFieldType [] roleDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, description, remarks, fact_keyid, flid
		, elementid, tempfield3, level, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getRoleDbFields() {
		return roleDbFields;
	}

	public GenTlRolemstSql()
	{
		roleDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			roleDbFields[ i ] = new TableFieldType();
		}
		roleDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ROLE_KEYID";
		roleDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.code.ordinal() ].fieldName = "ROLE_CODE";
		roleDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.name.ordinal() ].fieldName = "ROLE_NAME";
		roleDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.description.ordinal() ].fieldName = "ROLE_DESCRIPTION";
		roleDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ROLE_REMARKS";
		roleDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "ROLE_FACT_KEYID";
		roleDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "ROLE_FLID";
		roleDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "ROLE_ELEMENTID";
		roleDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ROLE_TEMPFIELD3";
		roleDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		roleDbFields[ tableFldConstants.level.ordinal() ].fieldName = "ROLE_LEVEL";
		roleDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'N';

		roleDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ROLE_ACTIVE";
		roleDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		roleDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ROLE_CREATEDBY";
		roleDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		roleDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ROLE_CREATEDON";
		roleDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		roleDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ROLE_MODIFIEDON";
		roleDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_ROLEMST, fieldTypeArr, dataArray);
		
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_ROLEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_ROLEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM GEN_TL_ROLEMST WHERE ROLE_KEYID = ?";
		return sql;
	}

}

