package com.akranta.tpm.dao.sql;

public class KznTlEvaluationmstSql {

	public static final String TBL_KZN_TL_EVALUATIONMST = "KZN_TL_EVALUATIONMST";  

	TableFieldType [] kevaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kaizenid, flid, employeeid, date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getKevaDbFields() {
		return kevaDbFields;
	}

	public KznTlEvaluationmstSql()
	{
		kevaDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			kevaDbFields[ i ] = new TableFieldType();
		}
		kevaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KEVA_KEYID";
		kevaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kevaDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KEVA_KAIZENID";
		kevaDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		kevaDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KEVA_FLID";
		kevaDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kevaDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "KEVA_EMPLOYEEID";
		kevaDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		kevaDbFields[ tableFldConstants.date.ordinal() ].fieldName = "KEVA_DATE";
		kevaDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		kevaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KEVA_TEMPFIELD1";
		kevaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KEVA_TEMPFIELD2";
		kevaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KEVA_TEMPFIELD3";
		kevaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KEVA_TEMPFIELD4";
		kevaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KEVA_TEMPFIELD5";
		kevaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "KEVA_TEMPFIELD6";
		kevaDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KEVA_ACTIVE";
		kevaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kevaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KEVA_CREATEDBY";
		kevaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kevaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KEVA_CREATEDON";
		kevaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kevaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KEVA_MODIFIEDON";
		kevaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_EVALUATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_EVALUATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_EVALUATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectmst(String flid) {
		return "SELECT * FROM "+TBL_KZN_TL_EVALUATIONMST+" WHERE KEVA_FLID = ?";
	}

}

