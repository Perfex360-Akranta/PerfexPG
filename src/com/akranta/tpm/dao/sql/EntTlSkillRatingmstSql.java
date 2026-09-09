package com.akranta.tpm.dao.sql;

public class EntTlSkillRatingmstSql {

	public static final String TBL_ENT_TL_SKILL_RATINGMST = "ENT_TL_SKILL_RATINGMST";  

	TableFieldType [] skrmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, sklm_keyid, orderno, description, minpoints, maxpoints
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSkrmDbFields() {
		return skrmDbFields;
	}

	public EntTlSkillRatingmstSql()
	{
		skrmDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			skrmDbFields[ i ] = new TableFieldType();
		}
		skrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SKRM_KEYID";
		skrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		skrmDbFields[ tableFldConstants.sklm_keyid.ordinal() ].fieldName = "SKRM_SKLM_KEYID";
		skrmDbFields[ tableFldConstants.sklm_keyid.ordinal() ].fieldType = 'V';

		skrmDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "SKRM_ORDERNO";
		skrmDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		skrmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "SKRM_DESCRIPTION";
		skrmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		skrmDbFields[ tableFldConstants.minpoints.ordinal() ].fieldName = "SKRM_MINPOINTS";
		skrmDbFields[ tableFldConstants.minpoints.ordinal() ].fieldType = 'N';

		skrmDbFields[ tableFldConstants.maxpoints.ordinal() ].fieldName = "SKRM_MAXPOINTS";
		skrmDbFields[ tableFldConstants.maxpoints.ordinal() ].fieldType = 'N';

		skrmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SKRM_TEMPFIELD1";
		skrmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SKRM_TEMPFIELD2";
		skrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SKRM_TEMPFIELD3";
		skrmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SKRM_TEMPFIELD4";
		skrmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SKRM_TEMPFIELD5";
		skrmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SKRM_ACTIVE";
		skrmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		skrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SKRM_CREATEDBY";
		skrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		skrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SKRM_CREATEDON";
		skrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		skrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SKRM_MODIFIEDON";
		skrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILL_RATINGMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILL_RATINGMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILL_RATINGMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

