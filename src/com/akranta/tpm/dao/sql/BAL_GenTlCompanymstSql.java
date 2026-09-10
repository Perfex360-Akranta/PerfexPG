package com.akranta.tpm.dao.sql;

public class BAL_GenTlCompanymstSql {

	public static final String TBL_GEN_TL_COMPANYMST = "GEN_TL_COMPANYMST";  

	TableFieldType [] compDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, address, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCompDbFields() {
		return compDbFields;
	}

	public BAL_GenTlCompanymstSql()
	{
		compDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			compDbFields[ i ] = new TableFieldType();
		}
		compDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "COMP_KEYID";
		compDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		compDbFields[ tableFldConstants.name.ordinal() ].fieldName = "COMP_NAME";
		compDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		compDbFields[ tableFldConstants.code.ordinal() ].fieldName = "COMP_CODE";
		compDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		compDbFields[ tableFldConstants.address.ordinal() ].fieldName = "COMP_ADDRESS";
		compDbFields[ tableFldConstants.address.ordinal() ].fieldType = 'V';

		compDbFields[ tableFldConstants.active.ordinal() ].fieldName = "COMP_ACTIVE";
		compDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		compDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "COMP_CREATEDBY";
		compDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		compDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "COMP_CREATEDON";
		compDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		compDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "COMP_MODIFIEDON";
		compDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_COMPANYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_COMPANYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_COMPANYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getCompanymstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_COMPANYMST + " where COMP_KEYID = ?  ";
	}

}

