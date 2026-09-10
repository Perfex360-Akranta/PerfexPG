package com.akranta.tpm.dao.sql;

public class BAL_PlmTlMethodtasklistSql {

	public static final String TBL_PLM_TL_METHODTASKLIST = "PLM_TL_METHODTASKLIST";  

	TableFieldType [] mtskDbFields = null;

	public enum   tableFldConstants
	{
		keyid, machineid, operation, checkingtool, idealcondition, typeofcheck
		, actualcondition
	}

	public TableFieldType[] getMtskDbFields() {
		return mtskDbFields;
	}

	public BAL_PlmTlMethodtasklistSql()
	{
		mtskDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			mtskDbFields[ i ] = new TableFieldType();
		}
		mtskDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MTSK_KEYID";
		mtskDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mtskDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MTSK_MACHINEID";
		mtskDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mtskDbFields[ tableFldConstants.operation.ordinal() ].fieldName = "MTSK_OPERATION";
		mtskDbFields[ tableFldConstants.operation.ordinal() ].fieldType = 'V';

		mtskDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldName = "MTSK_CHECKINGTOOL";
		mtskDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldType = 'V';

		mtskDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldName = "MTSK_IDEALCONDITION";
		mtskDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldType = 'V';

		mtskDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldName = "MTSK_TYPEOFCHECK";
		mtskDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldType = 'C';

		mtskDbFields[ tableFldConstants.actualcondition.ordinal() ].fieldName = "MTSK_ACTUALCONDITION";
		mtskDbFields[ tableFldConstants.actualcondition.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_METHODTASKLIST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_METHODTASKLIST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_METHODTASKLIST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

