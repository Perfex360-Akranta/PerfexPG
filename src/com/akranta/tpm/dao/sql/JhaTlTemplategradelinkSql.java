package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class JhaTlTemplategradelinkSql {

	public static final String TBL_JHA_TL_TEMPLATEGRADELINK = "JHA_TL_TEMPLATEGRADELINK";  

	TableFieldType [] jtglDbFields = null;

	public enum   tableFldConstants
	{
		templateid, gradeid, minimummarks, maximummarks, auditmasterid
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJtglDbFields() {
		return jtglDbFields;
	}

	public JhaTlTemplategradelinkSql()
	{
		jtglDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			jtglDbFields[ i ] = new TableFieldType();
		}
		jtglDbFields[ tableFldConstants.templateid.ordinal() ].fieldName = "JTGL_TEMPLATEID";
		jtglDbFields[ tableFldConstants.templateid.ordinal() ].fieldType = 'V';

		jtglDbFields[ tableFldConstants.gradeid.ordinal() ].fieldName = "JTGL_GRADEID";
		jtglDbFields[ tableFldConstants.gradeid.ordinal() ].fieldType = 'V';

		jtglDbFields[ tableFldConstants.minimummarks.ordinal() ].fieldName = "JTGL_MINIMUMMARKS";
		jtglDbFields[ tableFldConstants.minimummarks.ordinal() ].fieldType = 'N';

		jtglDbFields[ tableFldConstants.maximummarks.ordinal() ].fieldName = "JTGL_MAXIMUMMARKS";
		jtglDbFields[ tableFldConstants.maximummarks.ordinal() ].fieldType = 'N';

		jtglDbFields[ tableFldConstants.auditmasterid.ordinal() ].fieldName = "JTGL_AUDITMASTERID";
		jtglDbFields[ tableFldConstants.auditmasterid.ordinal() ].fieldType = 'V';

		jtglDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JTGL_ACTIVE";
		jtglDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jtglDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JTGL_CREATEDBY";
		jtglDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jtglDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JTGL_CREATEDON";
		jtglDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jtglDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JTGL_MODIFIEDON";
		jtglDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_TEMPLATEGRADELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_TEMPLATEGRADELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.templateid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.templateid.ordinal() ] + "'";
		sql += " AND " + fieldTypeArr[tableFldConstants.gradeid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.gradeid.ordinal() ] + "'";
		CommonMessage.debugMsg("sql...."+sql);
		return sql;
	}

	public static String getDeleteSql(String templateId) {
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEGRADELINK ;
		   sql += " where JTGL_AUDITMASTERID = '" +  templateId + "'";
		   return sql;
	}

	/*public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEGRADELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}*/

}

