package com.akranta.tpm.dao.sql;

public class EntTlFeedbackRequestmstSql {

	public static final String TBL_ENT_TL_FEEDBACK_REQUESTMST = "ENT_TL_FEEDBACK_REQUESTMST";  

	TableFieldType [] frqmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prog_keyid, bach_keyid, request_date, request_by, request_remarks
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFrqmDbFields() {
		return frqmDbFields;
	}

	public EntTlFeedbackRequestmstSql()
	{
		frqmDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			frqmDbFields[ i ] = new TableFieldType();
		}
		frqmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FRQM_KEYID";
		frqmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "FRQM_PROG_KEYID";
		frqmDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "FRQM_BACH_KEYID";
		frqmDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.request_date.ordinal() ].fieldName = "FRQM_REQUEST_DATE";
		frqmDbFields[ tableFldConstants.request_date.ordinal() ].fieldType = 'D';

		frqmDbFields[ tableFldConstants.request_by.ordinal() ].fieldName = "FRQM_REQUEST_BY";
		frqmDbFields[ tableFldConstants.request_by.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.request_remarks.ordinal() ].fieldName = "FRQM_REQUEST_REMARKS";
		frqmDbFields[ tableFldConstants.request_remarks.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FRQM_TEMPFIELD1";
		frqmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FRQM_TEMPFIELD2";
		frqmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FRQM_TEMPFIELD3";
		frqmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FRQM_TEMPFIELD4";
		frqmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FRQM_TEMPFIELD5";
		frqmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FRQM_ACTIVE";
		frqmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		frqmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FRQM_CREATEDBY";
		frqmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		frqmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FRQM_CREATEDON";
		frqmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		frqmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FRQM_MODIFIEDON";
		frqmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACK_REQUESTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACK_REQUESTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACK_REQUESTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getEmpSql(String batchId)
	{
		String sql = "SELECT BSTD_EMPM_KEYID FROM ENT_TL_BATCH_EMPLOYEE_LINK ";
			   sql += "WHERE BSTD_BACH_KEYID='"+batchId+"'";
		return sql;
	}
	public static String checkEmp(String empId)
	{
		String sql = "SELECT count(*) FROM ENT_TL_EMPMANAGERDTL ";
			   sql += "WHERE EEMD_EMPM_KEYID='"+empId+"'";
		return sql;
	}
}

