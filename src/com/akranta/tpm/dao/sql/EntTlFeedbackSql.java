package com.akranta.tpm.dao.sql;

public class EntTlFeedbackSql {

	public static final String TBL_ENT_TL_FEEDBACK = "ENT_TL_FEEDBACK";  

	TableFieldType [] feedDbFields = null;

	public enum   tableFldConstants
	{
		keyid, frqd_keyid, fbpm_keyid, ferm_keyid, answer, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getFeedDbFields() {
		return feedDbFields;
	}

	public EntTlFeedbackSql()
	{
		feedDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			feedDbFields[ i ] = new TableFieldType();
		}
		feedDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FEED_KEYID";
		feedDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.frqd_keyid.ordinal() ].fieldName = "FEED_FRQD_KEYID";
		feedDbFields[ tableFldConstants.frqd_keyid.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.fbpm_keyid.ordinal() ].fieldName = "FEED_FBPM_KEYID";
		feedDbFields[ tableFldConstants.fbpm_keyid.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.ferm_keyid.ordinal() ].fieldName = "FEED_FERM_KEYID";
		feedDbFields[ tableFldConstants.ferm_keyid.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.answer.ordinal() ].fieldName = "FEED_ANSWER";
		feedDbFields[ tableFldConstants.answer.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FEED_TEMPFIELD1";
		feedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FEED_TEMPFIELD2";
		feedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FEED_TEMPFIELD3";
		feedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FEED_TEMPFIELD4";
		feedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FEED_TEMPFIELD5";
		feedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FEED_ACTIVE";
		feedDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		feedDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FEED_CREATEDBY";
		feedDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		feedDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FEED_CREATEDON";
		feedDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		feedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FEED_MODIFIEDON";
		feedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

