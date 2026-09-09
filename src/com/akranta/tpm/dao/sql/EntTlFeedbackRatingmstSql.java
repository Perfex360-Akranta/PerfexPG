package com.akranta.tpm.dao.sql;

public class EntTlFeedbackRatingmstSql {

	public static final String TBL_ENT_TL_FEEDBACK_RATINGMST = "ENT_TL_FEEDBACK_RATINGMST";  

	TableFieldType [] fermDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, description, order, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFermDbFields() {
		return fermDbFields;
	}

	public EntTlFeedbackRatingmstSql()
	{
		fermDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			fermDbFields[ i ] = new TableFieldType();
		}
		fermDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FERM_KEYID";
		fermDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fermDbFields[ tableFldConstants.code.ordinal() ].fieldName = "FERM_CODE";
		fermDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		fermDbFields[ tableFldConstants.description.ordinal() ].fieldName = "FERM_DESCRIPTION";
		fermDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		fermDbFields[ tableFldConstants.order.ordinal() ].fieldName = "FERM_ORDER";
		fermDbFields[ tableFldConstants.order.ordinal() ].fieldType = 'N';

		fermDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FERM_TEMPFIELD1";
		fermDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fermDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FERM_TEMPFIELD2";
		fermDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fermDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FERM_TEMPFIELD3";
		fermDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fermDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FERM_ACTIVE";
		fermDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fermDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FERM_CREATEDBY";
		fermDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fermDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FERM_CREATEDON";
		fermDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fermDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FERM_MODIFIEDON";
		fermDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACK_RATINGMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACK_RATINGMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACK_RATINGMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

