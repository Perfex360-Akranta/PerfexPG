package com.akranta.tpm.dao.sql;

public class EntTlRoleTopicRatingSql {

	public static final String TBL_ENT_TL_ROLE_TOPIC_RATING = "ENT_TL_ROLE_TOPIC_RATING";  

	TableFieldType [] rtrlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, rtlk_keyid, skrm_keyid, cutoff, criteriadesc, orderno
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getRtrlDbFields() {
		return rtrlDbFields;
	}

	public EntTlRoleTopicRatingSql()
	{
		rtrlDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			rtrlDbFields[ i ] = new TableFieldType();
		}
		rtrlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RTRL_KEYID";
		rtrlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rtrlDbFields[ tableFldConstants.rtlk_keyid.ordinal() ].fieldName = "RTRL_RTLK_KEYID";
		rtrlDbFields[ tableFldConstants.rtlk_keyid.ordinal() ].fieldType = 'V';

		rtrlDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "RTRL_SKRM_KEYID";
		rtrlDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		rtrlDbFields[ tableFldConstants.cutoff.ordinal() ].fieldName = "RTRL_CUTOFF";
		rtrlDbFields[ tableFldConstants.cutoff.ordinal() ].fieldType = 'N';

		rtrlDbFields[ tableFldConstants.criteriadesc.ordinal() ].fieldName = "RTRL_CRITERIADESC";
		rtrlDbFields[ tableFldConstants.criteriadesc.ordinal() ].fieldType = 'V';

		rtrlDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "RTRL_ORDERNO";
		rtrlDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		rtrlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RTRL_TEMPFIELD1";
		rtrlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RTRL_TEMPFIELD2";
		rtrlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RTRL_TEMPFIELD3";
		rtrlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RTRL_TEMPFIELD4";
		rtrlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "RTRL_TEMPFIELD5";
		rtrlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RTRL_ACTIVE";
		rtrlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rtrlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RTRL_CREATEDBY";
		rtrlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rtrlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RTRL_CREATEDON";
		rtrlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rtrlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RTRL_MODIFIEDON";
		rtrlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_TOPIC_RATING, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_TOPIC_RATING, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_TOPIC_RATING ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDeleteWithRtrlKeyIdSql(String RtlkkeyId)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_TOPIC_RATING ;
		
		sql += " where  RTRL_RTLK_KEYID = '" +  RtlkkeyId+ "'"; 
			 
		return sql;
	}
	public String getTopicRatingData(String rtalKey) {
		// TODO Auto-generated method stub
		return "select * from Ent_Tl_Role_Topic_Rating  where RTRL_RTLK_KEYID = '"+rtalKey+"'";
	}

}

