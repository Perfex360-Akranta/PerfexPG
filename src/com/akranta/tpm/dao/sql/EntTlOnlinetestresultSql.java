package com.akranta.tpm.dao.sql;

public class EntTlOnlinetestresultSql {

	public static final String TBL_ENT_TL_ONLINETESTRESULT = "ENT_TL_ONLINETESTRESULT";  

	TableFieldType [] otrsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, empm_keyid, testdate, testid, programid, topicid, noofquestion
		, questionanswered, correctanswer, totalmark, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, tempfield8, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getOtrsDbFields() {
		return otrsDbFields;
	}

	public EntTlOnlinetestresultSql()
	{
		otrsDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			otrsDbFields[ i ] = new TableFieldType();
		}
		otrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OTRS_KEYID";
		otrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "OTRS_EMPM_KEYID";
		otrsDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.testdate.ordinal() ].fieldName = "OTRS_TESTDATE";
		otrsDbFields[ tableFldConstants.testdate.ordinal() ].fieldType = 'D';

		otrsDbFields[ tableFldConstants.testid.ordinal() ].fieldName = "OTRS_TESTID";
		otrsDbFields[ tableFldConstants.testid.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.programid.ordinal() ].fieldName = "OTRS_PROGRAMID";
		otrsDbFields[ tableFldConstants.programid.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "OTRS_TOPICID";
		otrsDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.noofquestion.ordinal() ].fieldName = "OTRS_NOOFQUESTION";
		otrsDbFields[ tableFldConstants.noofquestion.ordinal() ].fieldType = 'N';

		otrsDbFields[ tableFldConstants.questionanswered.ordinal() ].fieldName = "OTRS_QUESTIONANSWERED";
		otrsDbFields[ tableFldConstants.questionanswered.ordinal() ].fieldType = 'N';

		otrsDbFields[ tableFldConstants.correctanswer.ordinal() ].fieldName = "OTRS_CORRECTANSWER";
		otrsDbFields[ tableFldConstants.correctanswer.ordinal() ].fieldType = 'N';

		otrsDbFields[ tableFldConstants.totalmark.ordinal() ].fieldName = "OTRS_TOTALMARK";
		otrsDbFields[ tableFldConstants.totalmark.ordinal() ].fieldType = 'N';

		otrsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OTRS_TEMPFIELD1";
		otrsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OTRS_TEMPFIELD2";
		otrsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OTRS_TEMPFIELD3";
		otrsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OTRS_TEMPFIELD4";
		otrsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OTRS_TEMPFIELD5";
		otrsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "OTRS_TEMPFIELD6";
		otrsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "OTRS_TEMPFIELD7";
		otrsDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "OTRS_TEMPFIELD8";
		otrsDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OTRS_ACTIVE";
		otrsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		otrsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OTRS_CREATEDBY";
		otrsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		otrsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OTRS_CREATEDON";
		otrsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		otrsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OTRS_MODIFIEDON";
		otrsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ONLINETESTRESULT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ONLINETESTRESULT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ONLINETESTRESULT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

