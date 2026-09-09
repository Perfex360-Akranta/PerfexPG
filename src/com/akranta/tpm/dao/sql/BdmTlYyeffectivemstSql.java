package com.akranta.tpm.dao.sql;

public class BdmTlYyeffectivemstSql {

	public static final String TBL_BDM_TL_YYEFFECTIVEMST = "BDM_TL_YYEFFECTIVEMST";  

	TableFieldType [] yyefDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, refdocid, refdoctype, wwms_keyid, effectivedate
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getYyefDbFields() {
		return yyefDbFields;
	}

	public BdmTlYyeffectivemstSql()
	{
		yyefDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			yyefDbFields[ i ] = new TableFieldType();
		}
		yyefDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "YYEF_KEYID";
		yyefDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "YYEF_FLID";
		yyefDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "YYEF_REFDOCID";
		yyefDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "YYEF_REFDOCTYPE";
		yyefDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldName = "YYEF_WWMS_KEYID";
		yyefDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "YYEF_EFFECTIVEDATE";
		yyefDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		yyefDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "YYEF_TEMPFIELD1";
		yyefDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "YYEF_TEMPFIELD2";
		yyefDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "YYEF_TEMPFIELD3";
		yyefDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "YYEF_TEMPFIELD4";
		yyefDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "YYEF_TEMPFIELD5";
		yyefDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "YYEF_TEMPFIELD6";
		yyefDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "YYEF_TEMPFIELD7";
		yyefDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.active.ordinal() ].fieldName = "YYEF_ACTIVE";
		yyefDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		yyefDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "YYEF_CREATEDBY";
		yyefDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		yyefDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "YYEF_CREATEDON";
		yyefDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		yyefDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "YYEF_MODIFIEDON";
		yyefDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_YYEFFECTIVEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_YYEFFECTIVEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYEFFECTIVEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

