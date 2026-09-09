package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.JhaTlAudittemplateSql.tableFldConstants;

public class JhaTlTemplatelevellinkSql {

	public static final String TBL_JHA_TL_TEMPLATELEVELLINK = "JHA_TL_TEMPLATELEVELLINK";  

	TableFieldType [] jtllDbFields = null;

	public enum   tableFldConstants
	{
		templateid, auditlevelid, minimumpoints
		,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJtllDbFields() {
		return jtllDbFields;
	}

	public JhaTlTemplatelevellinkSql()
	{
		jtllDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			jtllDbFields[ i ] = new TableFieldType();
		}
		jtllDbFields[ tableFldConstants.templateid.ordinal() ].fieldName = "JTLL_TEMPLATEID";
		jtllDbFields[ tableFldConstants.templateid.ordinal() ].fieldType = 'V';

		jtllDbFields[ tableFldConstants.auditlevelid.ordinal() ].fieldName = "JTLL_AUDITLEVELID";
		jtllDbFields[ tableFldConstants.auditlevelid.ordinal() ].fieldType = 'V';

		jtllDbFields[ tableFldConstants.minimumpoints.ordinal() ].fieldName = "JTLL_MINIMUMPOINTS";
		jtllDbFields[ tableFldConstants.minimumpoints.ordinal() ].fieldType = 'N';
		
		jtllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "JTLL_TEMPFIELD1";
		jtllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		jtllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "JTLL_TEMPFIELD2";
		jtllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		jtllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JTLL_TEMPFIELD3";
		jtllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		jtllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "JTLL_TEMPFIELD4";
		jtllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		jtllDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JTLL_TEMPFIELD5";
		jtllDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		jtllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JTLL_ACTIVE";
		jtllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jtllDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JTLL_CREATEDBY";
		jtllDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jtllDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JTLL_CREATEDON";
		jtllDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jtllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JTLL_MODIFIEDON";
		jtllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_TEMPLATELEVELLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_TEMPLATELEVELLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal() ] + "'";
		return sql;
	}

	/*public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATELEVELLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}*/

	public static String getDeleteSql(String templateId) {
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATELEVELLINK ;
		   sql += " where JTLL_TEMPLATEID = '" +  templateId + "'";
		   return sql;
	}
}

