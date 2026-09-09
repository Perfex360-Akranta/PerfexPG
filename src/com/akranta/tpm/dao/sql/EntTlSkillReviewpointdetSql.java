package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlSkillReviewpointdetSql {

	public static final String TBL_ENT_TL_SKILL_REVIEWPOINTDET = "ENT_TL_SKILL_REVIEWPOINTDET";  

	TableFieldType [] sirdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, sirm_keyid, reviewpoint, reviewno, reviewtype, spok_keyid
		, subreviewno, tempfield4, tempfield5, tempfield6, tempfield7
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSirdDbFields() {
		return sirdDbFields;
	}

	public EntTlSkillReviewpointdetSql()
	{
		sirdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			sirdDbFields[ i ] = new TableFieldType();
		}
		sirdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SIRD_KEYID";
		sirdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.sirm_keyid.ordinal() ].fieldName = "SIRD_SIRM_KEYID";
		sirdDbFields[ tableFldConstants.sirm_keyid.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.reviewpoint.ordinal() ].fieldName = "SIRD_REVIEWPOINT";
		sirdDbFields[ tableFldConstants.reviewpoint.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.reviewno.ordinal() ].fieldName = "SIRD_REVIEWNO";
		sirdDbFields[ tableFldConstants.reviewno.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.reviewtype.ordinal() ].fieldName = "SIRD_REVIEWTYPE";
		sirdDbFields[ tableFldConstants.reviewtype.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.spok_keyid.ordinal() ].fieldName = "SIRD_SPOK_KEYID";
		sirdDbFields[ tableFldConstants.spok_keyid.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.subreviewno.ordinal() ].fieldName = "SIRD_SUBREVIEWNO";
		sirdDbFields[ tableFldConstants.subreviewno.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SIRD_TEMPFIELD4";
		sirdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SIRD_TEMPFIELD5";
		sirdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SIRD_TEMPFIELD6";
		sirdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "SIRD_TEMPFIELD7";
		sirdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SIRD_ACTIVE";
		sirdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sirdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SIRD_CREATEDBY";
		sirdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sirdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SIRD_CREATEDON";
		sirdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sirdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SIRD_MODIFIEDON";
		sirdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILL_REVIEWPOINTDET, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILL_REVIEWPOINTDET, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILL_REVIEWPOINTDET ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeleteRplist(String keyid) {
		String sql=" DELETE FROM " + TBL_ENT_TL_SKILL_REVIEWPOINTDET+ " WHERE SIRD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

}

