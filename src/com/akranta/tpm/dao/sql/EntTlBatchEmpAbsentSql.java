package com.akranta.tpm.dao.sql;

public class EntTlBatchEmpAbsentSql {

	public static final String TBL_ENT_TL_BATCH_EMP_ATTENDANCE = "ENT_TL_BATCH_EMP_ATTENDANCE";  

	TableFieldType [] ebeaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, bach_keyid, bsdl_keyid, empm_keyid, reason, takenby
		, attendance, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEbeaDbFields() {
		return ebeaDbFields;
	}

	public EntTlBatchEmpAbsentSql()
	{
		ebeaDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			ebeaDbFields[ i ] = new TableFieldType();
		}
		ebeaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EBEA_KEYID";
		ebeaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "EBEA_BACH_KEYID";
		ebeaDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.bsdl_keyid.ordinal() ].fieldName = "EBEA_BSDL_KEYID";
		ebeaDbFields[ tableFldConstants.bsdl_keyid.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "EBEA_EMPM_KEYID";
		ebeaDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.reason.ordinal() ].fieldName = "EBEA_REASON";
		ebeaDbFields[ tableFldConstants.reason.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.takenby.ordinal() ].fieldName = "EBEA_TAKENBY";
		ebeaDbFields[ tableFldConstants.takenby.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.attendance.ordinal() ].fieldName = "EBEA_ATTENDANCE";
		ebeaDbFields[ tableFldConstants.attendance.ordinal() ].fieldType = 'C';

		ebeaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EBEA_TEMPFIELD3";
		ebeaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ebeaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EBEA_ACTIVE";
		ebeaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ebeaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EBEA_CREATEDBY";
		ebeaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ebeaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EBEA_CREATEDON";
		ebeaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ebeaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EBEA_MODIFIEDON";
		ebeaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCH_EMP_ATTENDANCE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCH_EMP_ATTENDANCE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCH_EMP_ATTENDANCE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String deleteSql(String scheduleId,String empId)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCH_EMP_ATTENDANCE ;
		sql += " where EBEA_BSDL_KEYID ='"+scheduleId+"' AND EBEA_EMPM_KEYID = '"+empId+"'";
		return sql;
	}

}

