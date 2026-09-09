package com.akranta.tpm.dao.sql;

public class EntTlProgImpactSkillsSql {

	public static final String TBL_ENT_TL_PROG_IMPACT_SKILLS = "ENT_TL_PROG_IMPACT_SKILLS";  

	TableFieldType [] pimsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prts_keyid, skrm_keyid, minpoints, eff_from_date, eff_till_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPimsDbFields() {
		return pimsDbFields;
	}

	public EntTlProgImpactSkillsSql()
	{
		pimsDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			pimsDbFields[ i ] = new TableFieldType();
		}
		pimsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PIMS_KEYID";
		pimsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pimsDbFields[ tableFldConstants.prts_keyid.ordinal() ].fieldName = "PIMS_PRTS_KEYID";
		pimsDbFields[ tableFldConstants.prts_keyid.ordinal() ].fieldType = 'V';

		pimsDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "PIMS_SKRM_KEYID";
		pimsDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		pimsDbFields[ tableFldConstants.minpoints.ordinal() ].fieldName = "PIMS_MINPOINTS";
		pimsDbFields[ tableFldConstants.minpoints.ordinal() ].fieldType = 'N';

		pimsDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "PIMS_EFF_FROM_DATE";
		pimsDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		pimsDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "PIMS_EFF_TILL_DATE";
		pimsDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		pimsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PIMS_TEMPFIELD1";
		pimsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PIMS_TEMPFIELD2";
		pimsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PIMS_TEMPFIELD3";
		pimsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PIMS_TEMPFIELD4";
		pimsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PIMS_TEMPFIELD5";
		pimsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PIMS_ACTIVE";
		pimsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pimsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PIMS_CREATEDBY";
		pimsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pimsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PIMS_CREATEDON";
		pimsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pimsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PIMS_MODIFIEDON";
		pimsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_PROG_IMPACT_SKILLS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_PROG_IMPACT_SKILLS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_PROG_IMPACT_SKILLS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

