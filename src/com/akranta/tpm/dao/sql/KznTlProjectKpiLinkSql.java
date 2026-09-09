package com.akranta.tpm.dao.sql;

public class KznTlProjectKpiLinkSql {

	public static final String TBL_KZN_TL_PROJECT_KPI_LINK = "KZN_TL_PROJECT_KPI_LINK";  

	TableFieldType [] kpklDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, kink_keyid, baseval, targetval, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public TableFieldType[] getKpklDbFields() {
		return kpklDbFields;
	}

	public KznTlProjectKpiLinkSql()
	{
		kpklDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			kpklDbFields[ i ] = new TableFieldType();
		}
		kpklDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPKL_KEYID";
		kpklDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KPKL_KZPM_KEYID";
		kpklDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.kink_keyid.ordinal() ].fieldName = "KPKL_KINK_KEYID";
		kpklDbFields[ tableFldConstants.kink_keyid.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.baseval.ordinal() ].fieldName = "KPKL_BASEVAL";
		kpklDbFields[ tableFldConstants.baseval.ordinal() ].fieldType = 'N';

		kpklDbFields[ tableFldConstants.targetval.ordinal() ].fieldName = "KPKL_TARGETVAL";
		kpklDbFields[ tableFldConstants.targetval.ordinal() ].fieldType = 'N';

		kpklDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPKL_TEMPFIELD1";
		kpklDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPKL_TEMPFIELD2";
		kpklDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPKL_TEMPFIELD3";
		kpklDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPKL_TEMPFIELD4";
		kpklDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPKL_TEMPFIELD5";
		kpklDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPKL_CREATEDBY";
		kpklDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kpklDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPKL_ACTIVE";
		kpklDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kpklDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPKL_CREATEDON";
		kpklDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kpklDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPKL_MODIFIEDON";
		kpklDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECT_KPI_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECT_KPI_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECT_KPI_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getDeleteAllSql(String kpklKzpmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_KZN_TL_PROJECT_KPI_LINK ;		
		sql += " where KPKL_KZPM_KEYID= '" +  kpklKzpmKeyid+ "'";
		return sql;
	}

}

