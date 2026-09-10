package com.akranta.tpm.dao.sql;

public class BAL_GenVwToolcategorySql {

	public static final String TBL_GEN_VW_TOOLCATEGORY = "GEN_VW_TOOLCATEGORY";  

	TableFieldType [] gen_DbFields = null;

	public enum   tableFldConstants
	{
		elementid, displaycode, parentid, elementtype
	}

	public TableFieldType[] getGen_DbFields() {
		return gen_DbFields;
	}

	public BAL_GenVwToolcategorySql()
	{
		gen_DbFields = new TableFieldType[ 4 ];
		for(int i = 0;i < 4; i++)
		{	
			gen_DbFields[ i ] = new TableFieldType();
		}
		gen_DbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "ELEMENTID";
		gen_DbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		gen_DbFields[ tableFldConstants.displaycode.ordinal() ].fieldName = "DISPLAYCODE";
		gen_DbFields[ tableFldConstants.displaycode.ordinal() ].fieldType = 'V';

		gen_DbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "PARENTID";
		gen_DbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		gen_DbFields[ tableFldConstants.elementtype.ordinal() ].fieldName = "ELEMENTTYPE";
		gen_DbFields[ tableFldConstants.elementtype.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_VW_TOOLCATEGORY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_VW_TOOLCATEGORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.elementid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.elementid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_VW_TOOLCATEGORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.elementid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.elementid.ordinal()] + "'";
		return sql;
	}

}

