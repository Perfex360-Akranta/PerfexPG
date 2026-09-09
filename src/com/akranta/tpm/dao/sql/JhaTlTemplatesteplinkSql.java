package com.akranta.tpm.dao.sql;

public class JhaTlTemplatesteplinkSql {

	public static final String TBL_JHA_TL_TEMPLATESTEPLINK = "JHA_TL_TEMPLATESTEPLINK";  

	TableFieldType [] jtslDbFields = null;

	public enum   tableFldConstants
	{
		templateid, jhstepid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJtslDbFields() {
		return jtslDbFields;
	}

	public JhaTlTemplatesteplinkSql()
	{
		jtslDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			jtslDbFields[ i ] = new TableFieldType();
		}
		jtslDbFields[ tableFldConstants.templateid.ordinal() ].fieldName = "JTSL_TEMPLATEID";
		jtslDbFields[ tableFldConstants.templateid.ordinal() ].fieldType = 'V';

		jtslDbFields[ tableFldConstants.jhstepid.ordinal() ].fieldName = "JTSL_JHSTEPID";
		jtslDbFields[ tableFldConstants.jhstepid.ordinal() ].fieldType = 'V';

		jtslDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JTSL_ACTIVE";
		jtslDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jtslDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JTSL_CREATEDBY";
		jtslDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jtslDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JTSL_CREATEDON";
		jtslDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jtslDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JTSL_MODIFIEDON";
		jtslDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_TEMPLATESTEPLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_TEMPLATESTEPLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATESTEPLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteSql(String templateId) {
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATESTEPLINK ;
		   sql += " where JTSL_TEMPLATEID = '" +  templateId + "'";
		   return sql;
	}

}

