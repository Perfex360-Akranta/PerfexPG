package com.akranta.tpm.dao.sql;

public class OplTlStudentSql {

	public static final String TBL_OPL_TL_STUDENT = "OPL_TL_STUDENT";  

	TableFieldType [] opllDbFields = null;

	public enum   tableFldConstants
	{
		keyid, oplm_keyid, date, teacher, student, mtrx_keyid, tempfield1
		, tempfield2, tempfield3, tempfield4, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getOpllDbFields() {
		return opllDbFields;
	}
	

	public OplTlStudentSql()
	{
		opllDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			opllDbFields[ i ] = new TableFieldType();
		}
		opllDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OPLL_KEYID";
		opllDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.oplm_keyid.ordinal() ].fieldName = "OPLL_OPLM_KEYID";
		opllDbFields[ tableFldConstants.oplm_keyid.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OPLL_DATE";
		opllDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		opllDbFields[ tableFldConstants.teacher.ordinal() ].fieldName = "OPLL_TEACHER";
		opllDbFields[ tableFldConstants.teacher.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.student.ordinal() ].fieldName = "OPLL_STUDENT";
		opllDbFields[ tableFldConstants.student.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.mtrx_keyid.ordinal() ].fieldName = "OPLL_MTRX_KEYID";
		opllDbFields[ tableFldConstants.mtrx_keyid.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OPLL_TEMPFIELD1";
		opllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		opllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OPLL_TEMPFIELD2";
		opllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		opllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OPLL_TEMPFIELD3";
		opllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		opllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OPLL_TEMPFIELD4";
		opllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		opllDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OPLL_CREATEDBY";
		opllDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OPLL_ACTIVE";
		opllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		opllDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OPLL_CREATEDON";
		opllDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		opllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OPLL_MODIFIEDON";
		opllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_OPL_TL_STUDENT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OPL_TL_STUDENT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String oplloplid)
	{
		String sql = "DELETE from " + TBL_OPL_TL_STUDENT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplm_keyid.ordinal()].fieldName  +
			  " = '" +  oplloplid + "'";
		return sql;
	}

}

