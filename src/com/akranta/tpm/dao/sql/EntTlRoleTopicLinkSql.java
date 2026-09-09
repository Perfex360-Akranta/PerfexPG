package com.akranta.tpm.dao.sql;

public class EntTlRoleTopicLinkSql {

	public static final String TBL_ENT_TL_ROLE_TOPIC_LINK = "ENT_TL_ROLE_TOPIC_LINK";  

	TableFieldType [] rtlkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, rtal_keyid, spok_id, topi_keyid, targetskrm_keyid, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getRtlkDbFields() {
		return rtlkDbFields;
	}

	public EntTlRoleTopicLinkSql()
	{
		rtlkDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			rtlkDbFields[ i ] = new TableFieldType();
		}
		rtlkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RTLK_KEYID";
		rtlkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.rtal_keyid.ordinal() ].fieldName = "RTLK_RTAL_KEYID";
		rtlkDbFields[ tableFldConstants.rtal_keyid.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.spok_id.ordinal() ].fieldName = "RTLK_SPOK_ID";
		rtlkDbFields[ tableFldConstants.spok_id.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "RTLK_TOPI_KEYID";
		rtlkDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.targetskrm_keyid.ordinal() ].fieldName = "RTLK_TARGETSKRM_KEYID";
		rtlkDbFields[ tableFldConstants.targetskrm_keyid.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RTLK_TEMPFIELD1";
		rtlkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RTLK_TEMPFIELD2";
		rtlkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RTLK_TEMPFIELD3";
		rtlkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RTLK_TEMPFIELD4";
		rtlkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "RTLK_TEMPFIELD5";
		rtlkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RTLK_ACTIVE";
		rtlkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rtlkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RTLK_CREATEDBY";
		rtlkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rtlkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RTLK_CREATEDON";
		rtlkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rtlkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RTLK_MODIFIEDON";
		rtlkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_TOPIC_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_TOPIC_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_TOPIC_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getTopicLinkData(String trarKeyid) {
		// TODO Auto-generated method stub
		//return  "select * from Ent_Tl_Role_Topic_Link where RTLK_RTAL_KEYID = ?";
	//	return  "select * from Ent_Tl_Role_Topic_Link where RTLK_RTAL_KEYID = '"+trarKeyid+"' AND RTLK_TOPI_KEYID = '"+topiId+"'";
		return  "select * from Ent_Tl_Role_Topic_Link where RTLK_RTAL_KEYID = ? AND RTLK_TOPI_KEYID = ?";
	}

	public String getTopicLinkDatawithoutTopic(String trarKeyid) {
		// TODO Auto-generated method stub
		return  "select * from Ent_Tl_Role_Topic_Link where RTLK_RTAL_KEYID = '"+trarKeyid+"'";
	}

}

