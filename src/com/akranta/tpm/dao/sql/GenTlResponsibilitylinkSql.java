package com.akranta.tpm.dao.sql;

public class GenTlResponsibilitylinkSql {

	public static final String TBL_GEN_TL_RESPONSIBILITYLINK = "GEN_TL_RESPONSIBILITYLINK";  

	TableFieldType [] rsplDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refdocid, refdoctype, employeeid, targetdate, status, completedon
		, tempfiled1, tempfiled2, tempfiled3, tempfiled4, tempfiled5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getRsplDbFields() {
		return rsplDbFields;
	}

	public GenTlResponsibilitylinkSql()
	{
		rsplDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			rsplDbFields[ i ] = new TableFieldType();
		}
		rsplDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RSPL_KEYID";
		rsplDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rsplDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "RSPL_REFDOCID";
		rsplDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		rsplDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "RSPL_REFDOCTYPE";
		rsplDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		rsplDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "RSPL_EMPLOYEEID";
		rsplDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		rsplDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "RSPL_TARGETDATE";
		rsplDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		rsplDbFields[ tableFldConstants.status.ordinal() ].fieldName = "RSPL_STATUS";
		rsplDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.completedon.ordinal() ].fieldName = "RSPL_COMPLETEDON";
		rsplDbFields[ tableFldConstants.completedon.ordinal() ].fieldType = 'D';

		rsplDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "RSPL_TEMPFILED1";
		rsplDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "RSPL_TEMPFILED2";
		rsplDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "RSPL_TEMPFILED3";
		rsplDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldName = "RSPL_TEMPFILED4";
		rsplDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldName = "RSPL_TEMPFILED5";
		rsplDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RSPL_ACTIVE";
		rsplDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rsplDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RSPL_CREATEDBY";
		rsplDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rsplDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RSPL_CREATEDON";
		rsplDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rsplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RSPL_MODIFIEDON";
		rsplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_RESPONSIBILITYLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_RESPONSIBILITYLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_RESPONSIBILITYLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteSqlAll(String keyId)
	{
		String sql = "DELETE from  GEN_TL_RESPONSIBILITYLINK where RSPL_REFDOCID='"+keyId+"'";
		return sql;
	}

}

