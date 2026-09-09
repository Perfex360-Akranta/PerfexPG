package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.GenTlTeamtradelinkSql.tableFldConstants;

public class GenTlFnlnroleteamSql {

	public static final String TBL_GEN_TL_FNLNROLETEAM = "GEN_TL_FNLNROLETEAM";  

	TableFieldType [] frlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, frl_keyid,fnln_keyid, role_keyid, empm_keyid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFrlDbFields() {
		return frlDbFields;
	}

	public GenTlFnlnroleteamSql()
	{
		frlDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			frlDbFields[ i ] = new TableFieldType();
		}
		frlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FRT_KEYID";
		frlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.frl_keyid.ordinal() ].fieldName = "FRT_FRL_KEYID";
		frlDbFields[ tableFldConstants.frl_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.fnln_keyid.ordinal() ].fieldName = "FRT_FNLN_KEYID";
		frlDbFields[ tableFldConstants.fnln_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "FRT_ROLE_KEYID";
		frlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "FRT_EMPM_KEYID";
		frlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FRT_TEMPFIELD1";
		frlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FRT_TEMPFIELD2";
		frlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FRT_TEMPFIELD3";
		frlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FRT_TEMPFIELD4";
		frlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FRT_TEMPFIELD5";
		frlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FRT_ACTIVE";
		frlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		frlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FRT_CREATEDBY";
		frlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FRT_CREATEDON";
		frlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		frlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FRT_MODIFIEDON";
		frlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FNLNROLETEAM, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FNLNROLETEAM, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FNLNROLETEAM ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " IN ('" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "')";
		return sql;
	}
	public static String getDeleteTradeSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from GEN_TL_TEAMTRADELINK " ;
		
		sql += " where FRP_FRT_KEYID IN " +
			  "('" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "')";
		return sql;
	}
	
	public static String getUpdateEmployeeMstSql(String empId,String locationId,String sbuId)
	{
		String sql = " UPDATE GEN_TL_EMPLOYEEMST SET EMPM_LOCATION='"+locationId+"',EMPM_SBUID='"+sbuId+"' ";
		sql +=" Where EMPM_KEYID='"+empId+"' ";
		return sql;
	}
	public static String RemoveUniquePosition(String EmpKeyid){
		String sql="Update gen_tl_employeemst set EMPM_ROLEID ='{}' WHERE empm_keyid='"+EmpKeyid+"' ";
		return sql;
	}
}

