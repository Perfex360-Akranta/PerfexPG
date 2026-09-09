package com.akranta.tpm.dao.sql;

public class EntTlFeedbackformdtlSql {

	public static final String TBL_ENT_TL_FEEDBACKFORMDTL = "ENT_TL_FEEDBACKFORMDTL";  

	TableFieldType [] fbfdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tmst_keyid, fbpm_keyid, ferm_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFbfdDbFields() {
		return fbfdDbFields;
	}

	public EntTlFeedbackformdtlSql()
	{
		fbfdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			fbfdDbFields[ i ] = new TableFieldType();
		}
		fbfdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FBFD_KEYID";
		fbfdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fbfdDbFields[ tableFldConstants.tmst_keyid.ordinal() ].fieldName = "FBFD_TMST_KEYID";
		fbfdDbFields[ tableFldConstants.tmst_keyid.ordinal() ].fieldType = 'V';

		fbfdDbFields[ tableFldConstants.fbpm_keyid.ordinal() ].fieldName = "FBFD_FBPM_KEYID";
		fbfdDbFields[ tableFldConstants.fbpm_keyid.ordinal() ].fieldType = 'V';

		fbfdDbFields[ tableFldConstants.ferm_keyid.ordinal() ].fieldName = "FBFD_FERM_KEYID";
		fbfdDbFields[ tableFldConstants.ferm_keyid.ordinal() ].fieldType = 'V';

		fbfdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FBFD_TEMPFIELD1";
		fbfdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FBFD_TEMPFIELD2";
		fbfdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FBFD_TEMPFIELD3";
		fbfdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FBFD_TEMPFIELD4";
		fbfdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FBFD_TEMPFIELD5";
		fbfdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FBFD_ACTIVE";
		fbfdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fbfdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FBFD_CREATEDBY";
		fbfdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fbfdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FBFD_CREATEDON";
		fbfdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fbfdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FBFD_MODIFIEDON";
		fbfdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACKFORMDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACKFORMDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACKFORMDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

