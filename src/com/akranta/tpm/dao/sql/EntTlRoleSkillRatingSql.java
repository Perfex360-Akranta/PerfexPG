package com.akranta.tpm.dao.sql;

public class EntTlRoleSkillRatingSql {

	public static final String TBL_ENT_TL_ROLE_SKILL_RATING = "ENT_TL_ROLE_SKILL_RATING";  

	TableFieldType [] ersrDbFields = null;

	public enum   tableFldConstants
	{
		keyid, ersl_keyid, erdl_keyid, skil_keyid, skrm_keyid, sklm_keyid
		, minpoints, eff_from_date, eff_till_date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getErsrDbFields() {
		return ersrDbFields;
	}

	public EntTlRoleSkillRatingSql()
	{
		ersrDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			ersrDbFields[ i ] = new TableFieldType();
		}
		ersrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ERSR_KEYID";
		ersrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.ersl_keyid.ordinal() ].fieldName = "ERSR_ERSL_KEYID";
		ersrDbFields[ tableFldConstants.ersl_keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldName = "ERSR_ERDL_KEYID";
		ersrDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldName = "ERSR_SKIL_KEYID";
		ersrDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "ERSR_SKRM_KEYID";
		ersrDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.sklm_keyid.ordinal() ].fieldName = "ERSR_SKLM_KEYID";
		ersrDbFields[ tableFldConstants.sklm_keyid.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.minpoints.ordinal() ].fieldName = "ERSR_MINPOINTS";
		ersrDbFields[ tableFldConstants.minpoints.ordinal() ].fieldType = 'N';

		ersrDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "ERSR_EFF_FROM_DATE";
		ersrDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		ersrDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "ERSR_EFF_TILL_DATE";
		ersrDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		ersrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ERSR_TEMPFIELD1";
		ersrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ERSR_TEMPFIELD2";
		ersrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ERSR_TEMPFIELD3";
		ersrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ERSR_TEMPFIELD4";
		ersrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ERSR_TEMPFIELD5";
		ersrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ERSR_ACTIVE";
		ersrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ersrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ERSR_CREATEDBY";
		ersrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ersrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ERSR_CREATEDON";
		ersrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ersrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ERSR_MODIFIEDON";
		ersrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_SKILL_RATING, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_SKILL_RATING, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_SKILL_RATING ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

