package com.akranta.tpm.dao.sql;

public class EntTlSkillindexassessmstSql {

	public static final String TBL_ENT_TL_SKILLINDEXASSESSMST = "ENT_TL_SKILLINDEXASSESSMST";  

	TableFieldType [] siamDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, uniqueposid, tempfiled1, reviewdate, tempfiled2
		, tempfiled3, tempfiled4, tempfiled5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getSiamDbFields() {
		return siamDbFields;
	}

	public EntTlSkillindexassessmstSql()
	{
		siamDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			siamDbFields[ i ] = new TableFieldType();
		}
		siamDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SIAM_KEYID";
		siamDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		siamDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SIAM_FLID";
		siamDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		siamDbFields[ tableFldConstants.uniqueposid.ordinal() ].fieldName = "SIAM_UNIQUEPOSID";
		siamDbFields[ tableFldConstants.uniqueposid.ordinal() ].fieldType = 'V';

		siamDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "SIAM_TEMPFILED1";
		siamDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'V';

		siamDbFields[ tableFldConstants.reviewdate.ordinal() ].fieldName = "SIAM_REVIEWDATE";
		siamDbFields[ tableFldConstants.reviewdate.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "SIAM_TEMPFILED2";
		siamDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "SIAM_TEMPFILED3";
		siamDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldName = "SIAM_TEMPFILED4";
		siamDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldName = "SIAM_TEMPFILED5";
		siamDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SIAM_ACTIVE";
		siamDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		siamDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SIAM_CREATEDBY";
		siamDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		siamDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SIAM_CREATEDON";
		siamDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		siamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SIAM_MODIFIEDON";
		siamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILLINDEXASSESSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILLINDEXASSESSMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILLINDEXASSESSMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

