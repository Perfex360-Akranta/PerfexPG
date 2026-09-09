package com.akranta.tpm.dao.sql;

public class EntTlEmpnomenationmstSql {

	public static final String TBL_ENT_TL_EMPNOMENATIONMST = "ENT_TL_EMPNOMENATIONMST";  

	TableFieldType [] epnmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, programid, batchid, empid, nomenatedby, nomenateddate
		, approvedby, approveddate, remarks, status, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, modifiedby
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEpnmDbFields() {
		return epnmDbFields;
	}

	public EntTlEmpnomenationmstSql()
	{
		epnmDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			epnmDbFields[ i ] = new TableFieldType();
		}
		epnmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EPNM_KEYID";
		epnmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.programid.ordinal() ].fieldName = "EPNM_PROGRAMID";
		epnmDbFields[ tableFldConstants.programid.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.batchid.ordinal() ].fieldName = "EPNM_BATCHID";
		epnmDbFields[ tableFldConstants.batchid.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.empid.ordinal() ].fieldName = "EPNM_EMPID";
		epnmDbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.nomenatedby.ordinal() ].fieldName = "EPNM_NOMENATEDBY";
		epnmDbFields[ tableFldConstants.nomenatedby.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.nomenateddate.ordinal() ].fieldName = "EPNM_NOMENATEDDATE";
		epnmDbFields[ tableFldConstants.nomenateddate.ordinal() ].fieldType = 'D';

		epnmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "EPNM_APPROVEDBY";
		epnmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "EPNM_APPROVEDDATE";
		epnmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		epnmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "EPNM_REMARKS";
		epnmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "EPNM_STATUS";
		epnmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		epnmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EPNM_TEMPFIELD1";
		epnmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EPNM_TEMPFIELD2";
		epnmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EPNM_TEMPFIELD3";
		epnmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "EPNM_TEMPFIELD4";
		epnmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "EPNM_TEMPFIELD5";
		epnmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "EPNM_TEMPFIELD6";
		epnmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "EPNM_MODIFIEDBY";
		epnmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EPNM_ACTIVE";
		epnmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		epnmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EPNM_CREATEDBY";
		epnmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		epnmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EPNM_CREATEDON";
		epnmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		epnmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EPNM_MODIFIEDON";
		epnmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_EMPNOMENATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_EMPNOMENATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String Keyid)
	{
		String sql = "DELETE from " + TBL_ENT_TL_EMPNOMENATIONMST ;
		
		sql += " where EPNM_PROGRAMID = '"+ Keyid +"'";
		return sql;
	}

}

