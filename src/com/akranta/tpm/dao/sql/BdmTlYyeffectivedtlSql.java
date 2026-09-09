package com.akranta.tpm.dao.sql;

public class BdmTlYyeffectivedtlSql {

	public static final String TBL_BDM_TL_YYEFFECTIVEDTL = "BDM_TL_YYEFFECTIVEDTL";  

	TableFieldType [] yyedDbFields = null;

	public enum   tableFldConstants
	{
		keyid, yyef_keyid, countermesid, countermestype, empm_keyid, countermesdate
		, effectiveid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getYyedDbFields() {
		return yyedDbFields;
	}

	public BdmTlYyeffectivedtlSql()
	{
		yyedDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			yyedDbFields[ i ] = new TableFieldType();
		}
		yyedDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "YYED_KEYID";
		yyedDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.yyef_keyid.ordinal() ].fieldName = "YYED_YYEF_KEYID";
		yyedDbFields[ tableFldConstants.yyef_keyid.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.countermesid.ordinal() ].fieldName = "YYED_COUNTERMESID";
		yyedDbFields[ tableFldConstants.countermesid.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.countermestype.ordinal() ].fieldName = "YYED_COUNTERMESTYPE";
		yyedDbFields[ tableFldConstants.countermestype.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "YYED_EMPM_KEYID";
		yyedDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.countermesdate.ordinal() ].fieldName = "YYED_COUNTERMESDATE";
		yyedDbFields[ tableFldConstants.countermesdate.ordinal() ].fieldType = 'D';

		yyedDbFields[ tableFldConstants.effectiveid.ordinal() ].fieldName = "YYED_EFFECTIVEID";
		yyedDbFields[ tableFldConstants.effectiveid.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "YYED_TEMPFIELD1";
		yyedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "YYED_TEMPFIELD2";
		yyedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "YYED_TEMPFIELD3";
		yyedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "YYED_TEMPFIELD4";
		yyedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "YYED_TEMPFIELD5";
		yyedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "YYED_TEMPFIELD6";
		yyedDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "YYED_TEMPFIELD7";
		yyedDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.active.ordinal() ].fieldName = "YYED_ACTIVE";
		yyedDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		yyedDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "YYED_CREATEDBY";
		yyedDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		yyedDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "YYED_CREATEDON";
		yyedDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		yyedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "YYED_MODIFIEDON";
		yyedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_YYEFFECTIVEDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_YYEFFECTIVEDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYEFFECTIVEDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

