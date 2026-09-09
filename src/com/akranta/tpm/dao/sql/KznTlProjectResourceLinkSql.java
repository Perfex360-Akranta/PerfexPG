package com.akranta.tpm.dao.sql;

public class KznTlProjectResourceLinkSql {

	public static final String TBL_KZN_TL_PROJECT_RESOURCE_LINK = "KZN_TL_PROJECT_RESOURCE_LINK";  

	TableFieldType [] kprlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, lead_memb, empm_keyid, role_keyid,hrsestimate, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, active
		, createdon, modifiedon
	}

	public TableFieldType[] getKprlDbFields() {
		return kprlDbFields;
	}

	public KznTlProjectResourceLinkSql()
	{
		kprlDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			kprlDbFields[ i ] = new TableFieldType();
		}
		kprlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPRL_KEYID";
		kprlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KPRL_KZPM_KEYID";
		kprlDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.lead_memb.ordinal() ].fieldName = "KPRL_LEAD_MEMB";
		kprlDbFields[ tableFldConstants.lead_memb.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "KPRL_EMPM_KEYID";
		kprlDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "KPRL_ROLE_KEYID";
		kprlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';
		
		kprlDbFields[ tableFldConstants.hrsestimate.ordinal() ].fieldName = "KPRL_HRSESTIMATE";
		kprlDbFields[ tableFldConstants.hrsestimate.ordinal() ].fieldType = 'N';

		kprlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPRL_TEMPFIELD1";
		kprlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPRL_TEMPFIELD2";
		kprlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPRL_TEMPFIELD3";
		kprlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPRL_TEMPFIELD4";
		kprlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPRL_TEMPFIELD5";
		kprlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPRL_CREATEDBY";
		kprlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kprlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPRL_ACTIVE";
		kprlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kprlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPRL_CREATEDON";
		kprlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kprlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPRL_MODIFIEDON";
		kprlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECT_RESOURCE_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECT_RESOURCE_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECT_RESOURCE_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " in (" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + ")";
		return sql;
	}
	public static String getUpdateResource(String leadmemb,String empmkeyid,String rolekeyid,String hrsestimate,String modifiedon,String keyid) {
		
		String sql =" Update KZN_TL_PROJECT_RESOURCE_LINK set KPRL_LEAD_MEMB = '"+leadmemb+"',KPRL_EMPM_KEYID ='"+empmkeyid+"',KPRL_ROLE_KEYID='";
		sql += rolekeyid+"',KPRL_HRSESTIMATE='"+hrsestimate+"',KPRL_MODIFIEDON=to_date('"+modifiedon+"','dd-Mon-yyyy hh24:mi:ss') where KPRL_KEYID ='"+keyid+"'";
		return sql;
	}

	public String getRecall() {
		// TODO Auto-generated method stub
		return "SELECT  K.KPRL_KEYID, K.KPRL_KZPM_KEYID, K.KPRL_LEAD_MEMB, K.KPRL_EMPM_KEYID, K.KPRL_ROLE_KEYID, K.KPRL_HRSESTIMATE, K.KPRL_TEMPFIELD1, K.KPRL_TEMPFIELD2, K.KPRL_TEMPFIELD3,K.KPRL_TEMPFIELD4, K.KPRL_TEMPFIELD5, K.KPRL_CREATEDBY,K.KPRL_ACTIVE, K.KPRL_CREATEDON, K.KPRL_MODIFIEDON FROM KZN_TL_PROJECT_RESOURCE_LINK K where k.Kprl_keyid  = ?";
	}

}

