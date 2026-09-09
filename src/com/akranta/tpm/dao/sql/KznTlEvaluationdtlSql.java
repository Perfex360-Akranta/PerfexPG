package com.akranta.tpm.dao.sql;

public class KznTlEvaluationdtlSql {

	public static final String TBL_KZN_TL_EVALUATIONDTL = "KZN_TL_EVALUATIONDTL";  

	TableFieldType [] kedlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, keva_keyid, kzncretriaid, kzncriteriaval, kznm_keyid, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getKedlDbFields() {
		return kedlDbFields;
	}

	public KznTlEvaluationdtlSql()
	{
		kedlDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			kedlDbFields[ i ] = new TableFieldType();
		}
		kedlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KEDL_KEYID";
		kedlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kedlDbFields[ tableFldConstants.keva_keyid.ordinal() ].fieldName = "KEDL_KEVA_KEYID";
		kedlDbFields[ tableFldConstants.keva_keyid.ordinal() ].fieldType = 'V';

		kedlDbFields[ tableFldConstants.kzncretriaid.ordinal() ].fieldName = "KEDL_KZNCRETRIAID";
		kedlDbFields[ tableFldConstants.kzncretriaid.ordinal() ].fieldType = 'V';

		kedlDbFields[ tableFldConstants.kzncriteriaval.ordinal() ].fieldName = "KEDL_KZNCRITERIAVAL";
		kedlDbFields[ tableFldConstants.kzncriteriaval.ordinal() ].fieldType = 'N';

		kedlDbFields[ tableFldConstants.kznm_keyid.ordinal() ].fieldName = "KEDL_KZNM_KEYID";
		kedlDbFields[ tableFldConstants.kznm_keyid.ordinal() ].fieldType = 'V';

		kedlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KEDL_TEMPFIELD2";
		kedlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KEDL_TEMPFIELD3";
		kedlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KEDL_TEMPFIELD4";
		kedlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KEDL_TEMPFIELD5";
		kedlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "KEDL_TEMPFIELD6";
		kedlDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KEDL_ACTIVE";
		kedlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kedlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KEDL_CREATEDBY";
		kedlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kedlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KEDL_CREATEDON";
		kedlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kedlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KEDL_MODIFIEDON";
		kedlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_EVALUATIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_EVALUATIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_EVALUATIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

