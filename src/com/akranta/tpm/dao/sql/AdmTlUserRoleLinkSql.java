package com.akranta.tpm.dao.sql;

public class AdmTlUserRoleLinkSql {

	public static final String TBL_ADM_TL_USER_ROLE_LINK = "ADM_TL_USER_ROLE_LINK";  

	TableFieldType [] arulDbFields = null;

	public enum   tableFldConstants
	{
		userid, roleid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getArulDbFields() {
		return arulDbFields;
	}

	public AdmTlUserRoleLinkSql()
	{
		arulDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			arulDbFields[ i ] = new TableFieldType();
		}
		arulDbFields[ tableFldConstants.userid.ordinal() ].fieldName = "ARUL_USERID";
		arulDbFields[ tableFldConstants.userid.ordinal() ].fieldType = 'V';

		arulDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "ARUL_ROLEID";
		arulDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		arulDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ARUL_ACTIVE";
		arulDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		arulDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ARUL_CREATEDBY";
		arulDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		arulDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ARUL_CREATEDON";
		arulDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		arulDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ARUL_MODIFIEDON";
		arulDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_USER_ROLE_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_USER_ROLE_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.roleid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.roleid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_USER_ROLE_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.roleid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.roleid.ordinal()] + "'";
		return sql;
	}
	public static String getUserRoll(String userId)
	{
		return "SELECT ARUL_ROLEID,ROLE_NAME FROM ADM_TL_ROLEMST,ADM_TL_USER_ROLE_LINK where ARUL_ROLEID=ROLE_KEYID and ARUL_USERID='"+userId+"'";
	}
	public static String getUserRollS(String userId )
	{
		return "SELECT * FROM ADM_TL_USER_ROLE_LINK where  ARUL_USERID='"+userId+"'";
	}

}

