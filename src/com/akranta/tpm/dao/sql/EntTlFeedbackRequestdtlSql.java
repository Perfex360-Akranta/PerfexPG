package com.akranta.tpm.dao.sql;

public class EntTlFeedbackRequestdtlSql {

	public static final String TBL_ENT_TL_FEEDBACK_REQUESTDTL = "ENT_TL_FEEDBACK_REQUESTDTL";  

	TableFieldType [] frqdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, frqm_keyid, empm_keyid, ismailsent, status, feedback_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFrqdDbFields() {
		return frqdDbFields;
	}

	public EntTlFeedbackRequestdtlSql()
	{
		frqdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			frqdDbFields[ i ] = new TableFieldType();
		}
		frqdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FRQD_KEYID";
		frqdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		frqdDbFields[ tableFldConstants.frqm_keyid.ordinal() ].fieldName = "FRQD_FRQM_KEYID";
		frqdDbFields[ tableFldConstants.frqm_keyid.ordinal() ].fieldType = 'V';

		frqdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "FRQD_EMPM_KEYID";
		frqdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		frqdDbFields[ tableFldConstants.ismailsent.ordinal() ].fieldName = "FRQD_ISMAILSENT";
		frqdDbFields[ tableFldConstants.ismailsent.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "FRQD_STATUS";
		frqdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.feedback_date.ordinal() ].fieldName = "FRQD_FEEDBACK_DATE";
		frqdDbFields[ tableFldConstants.feedback_date.ordinal() ].fieldType = 'D';

		frqdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FRQD_TEMPFIELD1";
		frqdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FRQD_TEMPFIELD2";
		frqdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FRQD_TEMPFIELD3";
		frqdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FRQD_TEMPFIELD4";
		frqdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FRQD_TEMPFIELD5";
		frqdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FRQD_ACTIVE";
		frqdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		frqdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FRQD_CREATEDBY";
		frqdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		frqdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FRQD_CREATEDON";
		frqdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		frqdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FRQD_MODIFIEDON";
		frqdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACK_REQUESTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACK_REQUESTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACK_REQUESTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

