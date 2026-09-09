package com.akranta.tpm.dao.sql;

public class EntTlBatchScheduleSql {

	public static final String TBL_ENT_TL_BATCH_SCHEDULE = "ENT_TL_BATCH_SCHEDULE";  

	TableFieldType [] bsdlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, bach_keyid, prog_keyid, ecal_keyid, schedule_date, duration
		, fromtime, tilltime, status, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBsdlDbFields() {
		return bsdlDbFields;
	}

	public EntTlBatchScheduleSql()
	{
		bsdlDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			bsdlDbFields[ i ] = new TableFieldType();
		}
		bsdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BSDL_KEYID";
		bsdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bsdlDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "BSDL_BACH_KEYID";
		bsdlDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		bsdlDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "BSDL_PROG_KEYID";
		bsdlDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		bsdlDbFields[ tableFldConstants.ecal_keyid.ordinal() ].fieldName = "BSDL_ECAL_KEYID";
		bsdlDbFields[ tableFldConstants.ecal_keyid.ordinal() ].fieldType = 'V';

		bsdlDbFields[ tableFldConstants.schedule_date.ordinal() ].fieldName = "BSDL_SCHEDULE_DATE";
		bsdlDbFields[ tableFldConstants.schedule_date.ordinal() ].fieldType = 'D';

		bsdlDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "BSDL_DURATION";
		bsdlDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		bsdlDbFields[ tableFldConstants.fromtime.ordinal() ].fieldName = "BSDL_FROMTIME";
		bsdlDbFields[ tableFldConstants.fromtime.ordinal() ].fieldType = 'D';

		bsdlDbFields[ tableFldConstants.tilltime.ordinal() ].fieldName = "BSDL_TILLTIME";
		bsdlDbFields[ tableFldConstants.tilltime.ordinal() ].fieldType = 'D';

		bsdlDbFields[ tableFldConstants.status.ordinal() ].fieldName = "BSDL_STATUS";
		bsdlDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "BSDL_TEMPFIELD1";
		bsdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "BSDL_TEMPFIELD2";
		bsdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BSDL_TEMPFIELD3";
		bsdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BSDL_TEMPFIELD4";
		bsdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BSDL_TEMPFIELD5";
		bsdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BSDL_ACTIVE";
		bsdlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bsdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BSDL_CREATEDBY";
		bsdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bsdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BSDL_CREATEDON";
		bsdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bsdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BSDL_MODIFIEDON";
		bsdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCH_SCHEDULE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCH_SCHEDULE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCH_SCHEDULE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

