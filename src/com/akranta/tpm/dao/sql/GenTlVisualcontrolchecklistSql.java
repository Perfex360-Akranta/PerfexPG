package com.akranta.tpm.dao.sql;

public class GenTlVisualcontrolchecklistSql {

	public static final String TBL_GEN_TL_VISUALCONTROLCHECKLIST = "GEN_TL_VISUALCONTROLCHECKLIST";  

	TableFieldType [] vcclDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, employeeid, date, title, approvedby, tempfield3
		, tempfield4, tempfield5, tempfield6, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getVcclDbFields() {
		return vcclDbFields;
	}

	public GenTlVisualcontrolchecklistSql()
	{
		vcclDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			vcclDbFields[ i ] = new TableFieldType();
		}
		vcclDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCCL_KEYID";
		vcclDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vcclDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "VCCL_FLID";
		vcclDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		vcclDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "VCCL_EMPLOYEEID";
		vcclDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		vcclDbFields[ tableFldConstants.date.ordinal() ].fieldName = "VCCL_DATE";
		vcclDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		vcclDbFields[ tableFldConstants.title.ordinal() ].fieldName = "VCCL_TITLE";
		vcclDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "VCCL_APPROVEDBY";
		vcclDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCCL_TEMPFIELD3";
		vcclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VCCL_TEMPFIELD4";
		vcclDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VCCL_TEMPFIELD5";
		vcclDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "VCCL_TEMPFIELD6";
		vcclDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCCL_ACTIVE";
		vcclDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vcclDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCCL_CREATEDBY";
		vcclDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vcclDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCCL_CREATEDON";
		vcclDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vcclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCCL_MODIFIEDON";
		vcclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_VISUALCONTROLCHECKLIST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_VISUALCONTROLCHECKLIST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_VISUALCONTROLCHECKLIST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		String sql = "SELECT G.VCCL_KEYID, G.VCCL_FLID, G.VCCL_EMPLOYEEID,to_char(G.VCCL_DATE,'dd-MON-yyyy'), G.VCCL_TITLE, G.VCCL_APPROVEDBY,";
		sql+= "   G.VCCL_TEMPFIELD3, G.VCCL_TEMPFIELD4, G.VCCL_TEMPFIELD5,G.VCCL_TEMPFIELD6, G.VCCL_ACTIVE, G.VCCL_CREATEDBY, ";
		sql+="G.VCCL_CREATEDON, G.VCCL_MODIFIEDON FROM GEN_TL_VISUALCONTROLCHECKLIST G where VCCL_KEYID =? ";
		return sql;
	}

}

