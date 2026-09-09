package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTopicLinkRoledtlSql {

	public static final String TBL_ENT_TL_TOPIC_LINK_ROLEDTL = "ENT_TL_TOPIC_LINK_ROLEDTL";  

	TableFieldType [] toprDbFields = null;

	public enum   tableFldConstants
	{
		keyid, topi_keyid, role_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getToprDbFields() {
		return toprDbFields;
	}

	public EntTlTopicLinkRoledtlSql()
	{
		toprDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			toprDbFields[ i ] = new TableFieldType();
		}
		toprDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TOPR_KEYID";
		toprDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		toprDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "TOPR_TOPI_KEYID";
		toprDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		toprDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "TOPR_ROLE_KEYID";
		toprDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		toprDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TOPR_TEMPFIELD1";
		toprDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TOPR_TEMPFIELD2";
		toprDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TOPR_TEMPFIELD3";
		toprDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TOPR_TEMPFIELD4";
		toprDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TOPR_TEMPFIELD5";
		toprDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TOPR_ACTIVE";
		toprDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		toprDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TOPR_CREATEDBY";
		toprDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		toprDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TOPR_CREATEDON";
		toprDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		toprDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TOPR_MODIFIEDON";
		toprDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TOPIC_LINK_ROLEDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TOPIC_LINK_ROLEDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TOPIC_LINK_ROLEDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeleteTopiclist(String keyid) {
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_ENT_TL_TOPIC_LINK_ROLEDTL + " WHERE TOPR_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}


}

