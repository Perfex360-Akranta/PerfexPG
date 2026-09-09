package com.akranta.tpm.dao.sql;

public class EntTlSkillindexassessdtlSql {

	public static final String TBL_ENT_TL_SKILLINDEXASSESSDTL = "ENT_TL_SKILLINDEXASSESSDTL";  

	TableFieldType [] siadDbFields = null;

	public enum   tableFldConstants
	{
		keyid, siam_keyid, empm_keyid, reviewid, score, criteriaid, total
		, tempfiled3, tempfiled4, tempfiled5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getSiadDbFields() {
		return siadDbFields;
	}

	public EntTlSkillindexassessdtlSql()
	{
		siadDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			siadDbFields[ i ] = new TableFieldType();
		}
		siadDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SIAD_KEYID";
		siadDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		siadDbFields[ tableFldConstants.siam_keyid.ordinal() ].fieldName = "SIAD_SIAM_KEYID";
		siadDbFields[ tableFldConstants.siam_keyid.ordinal() ].fieldType = 'V';

		siadDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "SIAD_EMPM_KEYID";
		siadDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		siadDbFields[ tableFldConstants.reviewid.ordinal() ].fieldName = "SIAD_REVIEWID";
		siadDbFields[ tableFldConstants.reviewid.ordinal() ].fieldType = 'V';

		siadDbFields[ tableFldConstants.score.ordinal() ].fieldName = "SIAD_SCORE";
		siadDbFields[ tableFldConstants.score.ordinal() ].fieldType = 'N';

		siadDbFields[ tableFldConstants.criteriaid.ordinal() ].fieldName = "SIAD_CRITERIAID";
		siadDbFields[ tableFldConstants.criteriaid.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.total.ordinal() ].fieldName = "SIAD_TOTAL";
		siadDbFields[ tableFldConstants.total.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "SIAD_TEMPFILED3";
		siadDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldName = "SIAD_TEMPFILED4";
		siadDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldName = "SIAD_TEMPFILED5";
		siadDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SIAD_ACTIVE";
		siadDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		siadDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SIAD_CREATEDBY";
		siadDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		siadDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SIAD_CREATEDON";
		siadDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		siadDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SIAD_MODIFIEDON";
		siadDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILLINDEXASSESSDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILLINDEXASSESSDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILLINDEXASSESSDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	

}

