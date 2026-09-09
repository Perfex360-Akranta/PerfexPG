package com.akranta.tpm.dao.sql;

public class JhaTlTemplatemchlinkSql {

	public static final String TBL_JHA_TL_TEMPLATEMCHLINK = "JHA_TL_TEMPLATEMCHLINK";  

	TableFieldType [] jtmlDbFields = null;

	public enum   tableFldConstants
	{
		templateid,  flid, active, createdby, createdon, modtimestamp
	}

	public TableFieldType[] getJtmlDbFields() {
		return jtmlDbFields;
	}

	public JhaTlTemplatemchlinkSql()
	{
		jtmlDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			jtmlDbFields[ i ] = new TableFieldType();
		}
		jtmlDbFields[ tableFldConstants.templateid.ordinal() ].fieldName = "JTML_TEMPLATEID";
		jtmlDbFields[ tableFldConstants.templateid.ordinal() ].fieldType = 'V';

		jtmlDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "JTML_FLID";
		jtmlDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		jtmlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JTML_ACTIVE";
		jtmlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jtmlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JTML_CREATEDBY";
		jtmlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jtmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JTML_CREATEDON";
		jtmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jtmlDbFields[ tableFldConstants.modtimestamp.ordinal() ].fieldName = "JTML_MODTIMESTAMP";
		jtmlDbFields[ tableFldConstants.modtimestamp.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_TEMPLATEMCHLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_TEMPLATEMCHLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEMCHLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteSql(String templateId)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEMCHLINK ;
			   sql += " where JTML_TEMPLATEID = '" +  templateId + "'";
		return sql;
	}
}

