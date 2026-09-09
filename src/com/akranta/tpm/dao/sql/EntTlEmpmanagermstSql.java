package com.akranta.tpm.dao.sql;

public class EntTlEmpmanagermstSql {

	public static final String TBL_ENT_TL_EMPMANAGERMST = "ENT_TL_EMPMANAGERMST";  

	TableFieldType [] eemmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, manager_id, mailid, mobileno, tempfiled1, tempfiled2, tempfiled3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEemmDbFields() {
		return eemmDbFields;
	}

	public EntTlEmpmanagermstSql()
	{
		eemmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			eemmDbFields[ i ] = new TableFieldType();
		}
		eemmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EEMM_KEYID";
		eemmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		eemmDbFields[ tableFldConstants.manager_id.ordinal() ].fieldName = "EEMM_MANAGER_ID";
		eemmDbFields[ tableFldConstants.manager_id.ordinal() ].fieldType = 'V';

		eemmDbFields[ tableFldConstants.mailid.ordinal() ].fieldName = "EEMM_MAILID";
		eemmDbFields[ tableFldConstants.mailid.ordinal() ].fieldType = 'V';

		eemmDbFields[ tableFldConstants.mobileno.ordinal() ].fieldName = "EEMM_MOBILENO";
		eemmDbFields[ tableFldConstants.mobileno.ordinal() ].fieldType = 'N';

		eemmDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "EEMM_TEMPFILED1";
		eemmDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'C';

		eemmDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "EEMM_TEMPFILED2";
		eemmDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		eemmDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "EEMM_TEMPFILED3";
		eemmDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		eemmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EEMM_ACTIVE";
		eemmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		eemmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EEMM_CREATEDBY";
		eemmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		eemmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EEMM_CREATEDON";
		eemmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		eemmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EEMM_MODIFIEDON";
		eemmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_EMPMANAGERMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_EMPMANAGERMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_EMPMANAGERMST ;		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	

}

