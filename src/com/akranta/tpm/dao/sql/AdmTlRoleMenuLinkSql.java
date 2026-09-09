package com.akranta.tpm.dao.sql;

public class AdmTlRoleMenuLinkSql {

	public static final String TBL_ADM_TL_ROLE_MENU_LINK = "ADM_TL_ROLE_MENU_LINK";  

	TableFieldType [] armlDbFields = null;

	public enum   tableFldConstants
	{
		roleid, menuid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getArmlDbFields() {
		return armlDbFields;
	}

	public AdmTlRoleMenuLinkSql()
	{
		armlDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			armlDbFields[ i ] = new TableFieldType();
		}
		armlDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "ARML_ROLEID";
		armlDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		armlDbFields[ tableFldConstants.menuid.ordinal() ].fieldName = "ARML_MENUID";
		armlDbFields[ tableFldConstants.menuid.ordinal() ].fieldType = 'N';

		armlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ARML_ACTIVE";
		armlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		armlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ARML_CREATEDBY";
		armlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		armlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ARML_CREATEDON";
		armlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		armlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ARML_MODIFIEDON";
		armlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_ROLE_MENU_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_ROLE_MENU_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.menuid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.menuid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_ROLE_MENU_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.menuid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.menuid.ordinal()] + "'";
		return sql;
	}
	public static String getUserFromRole(String roleId)
	{
		String sql = "SELECT ARML_MENUID FROM "+TBL_ADM_TL_ROLE_MENU_LINK+" WHERE ARML_ROLEID = '"+roleId+"'";
		return sql;
	}
	public static String deleteMenuRole(String roleId,String menu)
	{
		String sql = "Delete "+TBL_ADM_TL_ROLE_MENU_LINK+"  Where Arml_Roleid = '"+roleId+"' And  Arml_Menuid="+menu;
		return sql;
	}
	public static String deleteMenuRoleLink(String roleId,String menu)
	{
		String sql = "Delete "+TBL_ADM_TL_ROLE_MENU_LINK+"  Where Arml_Roleid = '"+roleId+"' And  Arml_Menuid NOT IN("+menu+")";
		return sql;
	}
	public static String deleteMenuRoles(String roleId)
	{
		String sql = "Delete "+TBL_ADM_TL_ROLE_MENU_LINK+"  Where Arml_Roleid = '"+roleId+"'";
		return sql;
	}
	public static String checkMenuRole(String roleId,String menu)
	{
		String sql = "Select Count(*) From "+TBL_ADM_TL_ROLE_MENU_LINK+"  Where Arml_Roleid = '"+roleId+"' And  Arml_Menuid="+menu;
		return sql;
	}

}

