package com.akranta.tpm.dao.sql;

public class AdmTlUsersessionsSql {

	public static final String TBL_ADM_TL_USERSESSIONS = "ADM_TL_USERSESSIONS";  

	TableFieldType [] usseDbFields = null;

	public enum   tableFldConstants
	{
		userid, sessionno, sessiondate, logintime, logouttime, sessionstatus
		, pcname, ipaddress, sessionid, tempfield1, tempfield2, tempfield3
	}

	public TableFieldType[] getUsseDbFields() {
		return usseDbFields;
	}

	public AdmTlUsersessionsSql()
	{
		usseDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			usseDbFields[ i ] = new TableFieldType();
		}
		usseDbFields[ tableFldConstants.userid.ordinal() ].fieldName = "USSE_USERID";
		usseDbFields[ tableFldConstants.userid.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.sessionno.ordinal() ].fieldName = "USSE_SESSIONNO";
		usseDbFields[ tableFldConstants.sessionno.ordinal() ].fieldType = 'N';

		usseDbFields[ tableFldConstants.sessiondate.ordinal() ].fieldName = "USSE_SESSIONDATE";
		usseDbFields[ tableFldConstants.sessiondate.ordinal() ].fieldType = 'D';

		usseDbFields[ tableFldConstants.logintime.ordinal() ].fieldName = "USSE_LOGINTIME";
		usseDbFields[ tableFldConstants.logintime.ordinal() ].fieldType = 'D';

		usseDbFields[ tableFldConstants.logouttime.ordinal() ].fieldName = "USSE_LOGOUTTIME";
		usseDbFields[ tableFldConstants.logouttime.ordinal() ].fieldType = 'D';

		usseDbFields[ tableFldConstants.sessionstatus.ordinal() ].fieldName = "USSE_SESSIONSTATUS";
		usseDbFields[ tableFldConstants.sessionstatus.ordinal() ].fieldType = 'C';

		usseDbFields[ tableFldConstants.pcname.ordinal() ].fieldName = "USSE_PCNAME";
		usseDbFields[ tableFldConstants.pcname.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.ipaddress.ordinal() ].fieldName = "USSE_IPADDRESS";
		usseDbFields[ tableFldConstants.ipaddress.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.sessionid.ordinal() ].fieldName = "USSE_SESSIONID";
		usseDbFields[ tableFldConstants.sessionid.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "USSE_TEMPFIELD1";
		usseDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "USSE_TEMPFIELD2";
		usseDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		usseDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "USSE_TEMPFIELD3";
		usseDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_USERSESSIONS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_USERSESSIONS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.userid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.userid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_USERSESSIONS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.userid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.userid.ordinal()] + "'";
		return sql;
	}

}

