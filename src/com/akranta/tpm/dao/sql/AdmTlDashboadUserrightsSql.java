package com.akranta.tpm.dao.sql;

public class AdmTlDashboadUserrightsSql {

	public static final String TBL_ADM_TL_DASHBOAD_USERRIGHTS = "ADM_TL_DASHBOAD_USERRIGHTS";  

	TableFieldType [] dburDbFields = null;

	public enum   tableFldConstants
	{
		keyid, menuid, roleid, tempfield, tempfiled1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDburDbFields() {
		return dburDbFields;
	}

	public AdmTlDashboadUserrightsSql()
	{
		dburDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			dburDbFields[ i ] = new TableFieldType();
		}
		dburDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DBUR_KEYID";
		dburDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dburDbFields[ tableFldConstants.menuid.ordinal() ].fieldName = "DBUR_MENUID";
		dburDbFields[ tableFldConstants.menuid.ordinal() ].fieldType = 'V';

		dburDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "DBUR_ROLEID";
		dburDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		dburDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "DBUR_TEMPFIELD";
		dburDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'C';

		dburDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "DBUR_TEMPFILED1";
		dburDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'C';

		dburDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DBUR_TEMPFIELD2";
		dburDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		dburDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DBUR_TEMPFIELD3";
		dburDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		dburDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DBUR_ACTIVE";
		dburDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dburDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DBUR_CREATEDBY";
		dburDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dburDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DBUR_CREATEDON";
		dburDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dburDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DBUR_MODIFIEDON";
		dburDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_DASHBOAD_USERRIGHTS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_DASHBOAD_USERRIGHTS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_DASHBOAD_USERRIGHTS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = ' "+(String)dataArray[tableFldConstants.keyid.ordinal()]+"'";
		return sql;
	}
	public static String getRoleID(String roleId)
	{
		
		return "SELECT ROLE_KEYID FROM GEN_TL_ROLEMST";
	}

}

