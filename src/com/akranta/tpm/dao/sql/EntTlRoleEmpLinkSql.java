package com.akranta.tpm.dao.sql;

public class EntTlRoleEmpLinkSql {

	public static final String TBL_ENT_TL_ROLE_EMP_LINK = "ENT_TL_ROLE_EMP_LINK";  

	TableFieldType [] erelDbFields = null;

	public enum   tableFldConstants
	{
		keyid, erdl_keyid, empm_keyid, current_skillratingid, target_skillratingid
		, eff_from_date, eff_till_date, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getErelDbFields() {
		return erelDbFields;
	}

	public EntTlRoleEmpLinkSql()
	{
		erelDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			erelDbFields[ i ] = new TableFieldType();
		}
		erelDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EREL_KEYID";
		erelDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldName = "EREL_RTAL_KEYID";
		erelDbFields[ tableFldConstants.erdl_keyid.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "EREL_EMPM_KEYID";
		erelDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.current_skillratingid.ordinal() ].fieldName = "EREL_CURRENT_SKILLRATINGID";
		erelDbFields[ tableFldConstants.current_skillratingid.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.target_skillratingid.ordinal() ].fieldName = "EREL_TARGET_SKILLRATINGID";
		erelDbFields[ tableFldConstants.target_skillratingid.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "EREL_EFF_FROM_DATE";
		erelDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		erelDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "EREL_EFF_TILL_DATE";
		erelDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		erelDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EREL_TEMPFIELD1";
		erelDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EREL_TEMPFIELD2";
		erelDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EREL_TEMPFIELD3";
		erelDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "EREL_TEMPFIELD4";
		erelDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "EREL_TEMPFIELD5";
		erelDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EREL_ACTIVE";
		erelDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		erelDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EREL_CREATEDBY";
		erelDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		erelDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EREL_CREATEDON";
		erelDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		erelDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EREL_MODIFIEDON";
		erelDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_EMP_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_EMP_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_EMP_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

