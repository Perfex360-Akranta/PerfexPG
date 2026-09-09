package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMessageboardSql {

	public static final String TBL_GEN_TL_MESSAGEBOARD = "GEN_TL_MESSAGEBOARD";  

	TableFieldType [] msgbDbFields = null;

	public enum   tableFldConstants
	{
		keyid, title, content, effectivefrom, effectiveto, shownfordays
		, type, showngrouptype, flid, roleid, employeeid, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMsgbDbFields() {
		return msgbDbFields;
	}

	public GenTlMessageboardSql()
	{
		msgbDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			msgbDbFields[ i ] = new TableFieldType();
		}
		msgbDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSGB_KEYID";
		msgbDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.title.ordinal() ].fieldName = "MSGB_TITLE";
		msgbDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.content.ordinal() ].fieldName = "MSGB_CONTENT";
		msgbDbFields[ tableFldConstants.content.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldName = "MSGB_EFFECTIVEFROM";
		msgbDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldType = 'D';

		msgbDbFields[ tableFldConstants.effectiveto.ordinal() ].fieldName = "MSGB_EFFECTIVETO";
		msgbDbFields[ tableFldConstants.effectiveto.ordinal() ].fieldType = 'D';

		msgbDbFields[ tableFldConstants.shownfordays.ordinal() ].fieldName = "MSGB_SHOWNFORDAYS";
		msgbDbFields[ tableFldConstants.shownfordays.ordinal() ].fieldType = 'N';

		msgbDbFields[ tableFldConstants.type.ordinal() ].fieldName = "MSGB_TYPE";
		msgbDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.showngrouptype.ordinal() ].fieldName = "MSGB_SHOWNGROUPTYPE";
		msgbDbFields[ tableFldConstants.showngrouptype.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MSGB_FLID";
		msgbDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "MSGB_ROLEID";
		msgbDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "MSGB_EMPLOYEEID";
		msgbDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MSGB_TEMPFIELD1";
		msgbDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSGB_TEMPFIELD2";
		msgbDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MSGB_TEMPFIELD3";
		msgbDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MSGB_TEMPFIELD4";
		msgbDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MSGB_TEMPFIELD5";
		msgbDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSGB_ACTIVE";
		msgbDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		msgbDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSGB_CREATEDBY";
		msgbDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		msgbDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSGB_CREATEDON";
		msgbDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		msgbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSGB_MODIFIEDON";
		msgbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MESSAGEBOARD, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MESSAGEBOARD, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MESSAGEBOARD ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getSelectSql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_GEN_TL_MESSAGEBOARD  + " where MSGB_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

}

