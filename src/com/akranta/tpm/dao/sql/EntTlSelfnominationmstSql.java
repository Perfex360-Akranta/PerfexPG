package com.akranta.tpm.dao.sql;

public class EntTlSelfnominationmstSql {

	public static final String TBL_ENT_TL_SELFNOMINATIONMST = "ENT_TL_SELFNOMINATIONMST";  

	TableFieldType [] snomDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, empid, progid, nominationdate, batchid, remarks
		, status, approvedby, approveddate, approvedremarks, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getSnomDbFields() {
		return snomDbFields;
	}

	public EntTlSelfnominationmstSql()
	{
		snomDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			snomDbFields[ i ] = new TableFieldType();
		}
		snomDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SNOM_KEYID";
		snomDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SNOM_FLID";
		snomDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.empid.ordinal() ].fieldName = "SNOM_EMPID";
		snomDbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.progid.ordinal() ].fieldName = "SNOM_PROGID";
		snomDbFields[ tableFldConstants.progid.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.nominationdate.ordinal() ].fieldName = "SNOM_NOMINATIONDATE";
		snomDbFields[ tableFldConstants.nominationdate.ordinal() ].fieldType = 'D';

		snomDbFields[ tableFldConstants.batchid.ordinal() ].fieldName = "SNOM_BATCHID";
		snomDbFields[ tableFldConstants.batchid.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SNOM_REMARKS";
		snomDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SNOM_STATUS";
		snomDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "SNOM_APPROVEDBY";
		snomDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "SNOM_APPROVEDDATE";
		snomDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		snomDbFields[ tableFldConstants.approvedremarks.ordinal() ].fieldName = "SNOM_APPROVEDREMARKS";
		snomDbFields[ tableFldConstants.approvedremarks.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SNOM_TEMPFIELD1";
		snomDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SNOM_TEMPFIELD2";
		snomDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SNOM_TEMPFIELD3";
		snomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SNOM_TEMPFIELD4";
		snomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SNOM_ACTIVE";
		snomDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		snomDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SNOM_CREATEDBY";
		snomDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		snomDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SNOM_CREATEDON";
		snomDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		snomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SNOM_MODIFIEDON";
		snomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SELFNOMINATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SELFNOMINATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SELFNOMINATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

