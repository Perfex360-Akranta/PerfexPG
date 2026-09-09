package com.akranta.tpm.dao.sql;

public class EntTlRoleSkillLinkSql {

	public static final String TBL_ENT_TL_ROLE_SKILL_LINK = "ENT_TL_ROLE_SKILL_LINK";  

	TableFieldType [] erslDbFields = null;

	public enum   tableFldConstants
	{
		keyid, erdl_keyid, skil_keyid, eff_from_date, eff_till_date, mandatory_type
		, target_rating, rating_type, rating_number, orderno, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getErslDbFields() {
		return erslDbFields;
	}

	public EntTlRoleSkillLinkSql()
	{
		erslDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			erslDbFields[ i ] = new TableFieldType();
		}
		erslDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ERSL_KEYID";
		erslDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		erslDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldName = "ERSL_ERDL_KEYID";
		erslDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldType = 'V';

		erslDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldName = "ERSL_SKIL_KEYID";
		erslDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldType = 'V';

		erslDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "ERSL_EFF_FROM_DATE";
		erslDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		erslDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "ERSL_EFF_TILL_DATE";
		erslDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		erslDbFields[ tableFldConstants.mandatory_type.ordinal() ].fieldName = "ERSL_MANDATORY_TYPE";
		erslDbFields[ tableFldConstants.mandatory_type.ordinal() ].fieldType = 'C';
		
		erslDbFields[ tableFldConstants.target_rating.ordinal() ].fieldName = "ERSL_TARGET_RATING";
		erslDbFields[ tableFldConstants.target_rating.ordinal() ].fieldType = 'C';
		
		erslDbFields[ tableFldConstants.rating_type.ordinal() ].fieldName = "ERSL_RATING_TYPE";
		erslDbFields[ tableFldConstants.rating_type.ordinal() ].fieldType = 'C';
		
		erslDbFields[ tableFldConstants.rating_number.ordinal() ].fieldName = "ERSL_RATING_NUMBER";
		erslDbFields[ tableFldConstants.rating_number.ordinal() ].fieldType = 'N';

		erslDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "ERSL_ORDERNO";
		erslDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		erslDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ERSL_TEMPFIELD1";
		erslDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ERSL_TEMPFIELD2";
		erslDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ERSL_TEMPFIELD3";
		erslDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ERSL_TEMPFIELD4";
		erslDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ERSL_TEMPFIELD5";
		erslDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ERSL_ACTIVE";
		erslDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		erslDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ERSL_CREATEDBY";
		erslDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		erslDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ERSL_CREATEDON";
		erslDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		erslDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ERSL_MODIFIEDON";
		erslDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_SKILL_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_SKILL_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_SKILL_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

