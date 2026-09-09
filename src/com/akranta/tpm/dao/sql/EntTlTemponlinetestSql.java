package com.akranta.tpm.dao.sql;

public class EntTlTemponlinetestSql {

	public static final String TBL_ENT_TL_TEMPONLINETEST = "ENT_TL_TEMPONLINETEST";  

	TableFieldType [] temtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, employeeid, olpm_keyid, programid, topicid, questionid
		, answerid, teststarttime, markforreview, iscorrectans, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTemtDbFields() {
		return temtDbFields;
	}

	public EntTlTemponlinetestSql()
	{
		temtDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			temtDbFields[ i ] = new TableFieldType();
		}
		temtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TEMT_KEYID";
		temtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "TEMT_EMPLOYEEID";
		temtDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.olpm_keyid.ordinal() ].fieldName = "TEMT_OLPM_KEYID";
		temtDbFields[ tableFldConstants.olpm_keyid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.programid.ordinal() ].fieldName = "TEMT_PROGRAMID";
		temtDbFields[ tableFldConstants.programid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "TEMT_TOPICID";
		temtDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.questionid.ordinal() ].fieldName = "TEMT_QUESTIONID";
		temtDbFields[ tableFldConstants.questionid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.answerid.ordinal() ].fieldName = "TEMT_ANSWERID";
		temtDbFields[ tableFldConstants.answerid.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.teststarttime.ordinal() ].fieldName = "TEMT_TESTSTARTTIME";
		temtDbFields[ tableFldConstants.teststarttime.ordinal() ].fieldType = 'D';

		temtDbFields[ tableFldConstants.markforreview.ordinal() ].fieldName = "TEMT_MARKFORREVIEW";
		temtDbFields[ tableFldConstants.markforreview.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.iscorrectans.ordinal() ].fieldName = "TEMT_ISCORRECTANS";
		temtDbFields[ tableFldConstants.iscorrectans.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TEMT_TEMPFIELD2";
		temtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TEMT_TEMPFIELD3";
		temtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TEMT_TEMPFIELD4";
		temtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TEMT_ACTIVE";
		temtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		temtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TEMT_CREATEDBY";
		temtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		temtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TEMT_CREATEDON";
		temtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		temtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TEMT_MODIFIEDON";
		temtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TEMPONLINETEST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TEMPONLINETEST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TEMPONLINETEST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

