package com.akranta.tpm.dao.sql;

public class KznTlProjectKaizenLinkSql {

	public static final String TBL_KZN_TL_PROJECT_KAIZEN_LINK = "KZN_TL_PROJECT_KAIZEN_LINK";  

	TableFieldType [] kplkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, kznm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getKplkDbFields() {
		return kplkDbFields;
	}

	public KznTlProjectKaizenLinkSql()
	{
		kplkDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			kplkDbFields[ i ] = new TableFieldType();
		}
		kplkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPLK_KEYID";
		kplkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KPLK_KZPM_KEYID";
		kplkDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.kznm_keyid.ordinal() ].fieldName = "KPLK_KZNM_KEYID";
		kplkDbFields[ tableFldConstants.kznm_keyid.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPLK_TEMPFIELD1";
		kplkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPLK_TEMPFIELD2";
		kplkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPLK_TEMPFIELD3";
		kplkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPLK_TEMPFIELD4";
		kplkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPLK_TEMPFIELD5";
		kplkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPLK_CREATEDBY";
		kplkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kplkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPLK_ACTIVE";
		kplkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kplkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPLK_CREATEDON";
		kplkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kplkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPLK_MODIFIEDON";
		kplkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECT_KAIZEN_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECT_KAIZEN_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECT_KAIZEN_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteAllSql(String kplkKzpmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_KZN_TL_PROJECT_KAIZEN_LINK ;		
		sql += " where KPLK_KZPM_KEYID= '" +  kplkKzpmKeyid+ "'";
		return sql;

	}

}

