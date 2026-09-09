package com.akranta.tpm.dao.sql;

public class EntTlFacultytopiclinkSql {

	public static final String TBL_ENT_TL_FACULTYTOPICLINK = "ENT_TL_FACULTYTOPICLINK";  

	TableFieldType [] ftlkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, facultyid, topicid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFtlkDbFields() {
		return ftlkDbFields;
	}

	public EntTlFacultytopiclinkSql()
	{
		ftlkDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			ftlkDbFields[ i ] = new TableFieldType();
		}
		ftlkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FTLK_KEYID";
		ftlkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftlkDbFields[ tableFldConstants.facultyid.ordinal() ].fieldName = "FTLK_FACULTYID";
		ftlkDbFields[ tableFldConstants.facultyid.ordinal() ].fieldType = 'V';

		ftlkDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "FTLK_TOPICID";
		ftlkDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		ftlkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FTLK_TEMPFIELD1";
		ftlkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FTLK_TEMPFIELD2";
		ftlkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FTLK_TEMPFIELD3";
		ftlkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FTLK_TEMPFIELD4";
		ftlkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FTLK_TEMPFIELD5";
		ftlkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "FTLK_TEMPFIELD6";
		ftlkDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FTLK_ACTIVE";
		ftlkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftlkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FTLK_CREATEDBY";
		ftlkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ftlkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FTLK_CREATEDON";
		ftlkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftlkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FTLK_MODIFIEDON";
		ftlkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FACULTYTOPICLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FACULTYTOPICLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FACULTYTOPICLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

