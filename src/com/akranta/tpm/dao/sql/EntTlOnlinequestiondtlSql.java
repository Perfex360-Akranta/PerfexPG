package com.akranta.tpm.dao.sql;

public class EntTlOnlinequestiondtlSql {

	public static final String TBL_ENT_TL_ONLINEQUESTIONDTL = "ENT_TL_ONLINEQUESTIONDTL";  

	TableFieldType [] olqdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, olqm_keyid, answer, correctanswer, sortorder, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public TableFieldType[] getOlqdDbFields() {
		return olqdDbFields;
	}

	public EntTlOnlinequestiondtlSql()
	{
		olqdDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			olqdDbFields[ i ] = new TableFieldType();
		}
		olqdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OLQD_KEYID";
		olqdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		olqdDbFields[ tableFldConstants.olqm_keyid.ordinal() ].fieldName = "OLQD_OLQM_KEYID";
		olqdDbFields[ tableFldConstants.olqm_keyid.ordinal() ].fieldType = 'V';

		olqdDbFields[ tableFldConstants.answer.ordinal() ].fieldName = "OLQD_ANSWER";
		olqdDbFields[ tableFldConstants.answer.ordinal() ].fieldType = 'V';

		olqdDbFields[ tableFldConstants.correctanswer.ordinal() ].fieldName = "OLQD_CORRECTANSWER";
		olqdDbFields[ tableFldConstants.correctanswer.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.sortorder.ordinal() ].fieldName = "OLQD_SORTORDER";
		olqdDbFields[ tableFldConstants.sortorder.ordinal() ].fieldType = 'N';

		olqdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OLQD_TEMPFIELD1";
		olqdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OLQD_TEMPFIELD2";
		olqdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OLQD_TEMPFIELD3";
		olqdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OLQD_TEMPFIELD4";
		olqdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OLQD_TEMPFIELD5";
		olqdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OLQD_CREATEDBY";
		olqdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		olqdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OLQD_ACTIVE";
		olqdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		olqdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OLQD_CREATEDON";
		olqdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		olqdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OLQD_MODIFIEDON";
		olqdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ONLINEQUESTIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ONLINEQUESTIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ONLINEQUESTIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String deleteAnswer(String keyid) {
		return " DELETE FROM " + TBL_ENT_TL_ONLINEQUESTIONDTL + " WHERE OLQD_KEYID ='"+keyid+"'";
	}
}

