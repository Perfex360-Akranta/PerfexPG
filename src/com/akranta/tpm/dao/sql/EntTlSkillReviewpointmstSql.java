package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlSkillReviewpointmstSql {

	public static final String TBL_ENT_TL_SKILL_REVIEWPOINTMST = "ENT_TL_SKILL_REVIEWPOINTMST";  

	TableFieldType [] sirmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, tempid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getSirmDbFields() {
		return sirmDbFields;
	}

	public EntTlSkillReviewpointmstSql()
	{
		sirmDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			sirmDbFields[ i ] = new TableFieldType();
		}
		sirmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SIRM_KEYID";
		sirmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SIRM_FLID";
		sirmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "SIRM_ROLE_KEYID";
		sirmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempid.ordinal() ].fieldName = "SIRM_TEMPID";
		sirmDbFields[ tableFldConstants.tempid.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SIRM_TEMPFIELD1";
		sirmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SIRM_TEMPFIELD2";
		sirmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SIRM_TEMPFIELD3";
		sirmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SIRM_TEMPFIELD4";
		sirmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SIRM_TEMPFIELD5";
		sirmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SIRM_TEMPFIELD6";
		sirmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "SIRM_TEMPFIELD7";
		sirmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SIRM_ACTIVE";
		sirmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sirmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SIRM_CREATEDBY";
		sirmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sirmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SIRM_CREATEDON";
		sirmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sirmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SIRM_MODIFIEDON";
		sirmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILL_REVIEWPOINTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILL_REVIEWPOINTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILL_REVIEWPOINTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String RpDetailGrid(CommonFilter commonFilter,String mstkeyid) {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT SIRD_KEYID,'', '', SIRD_SPOK_KEYID,SPOK_NAME, SIRD_REVIEWNO || DECODE(SIRD_SUBREVIEWNO,'-',NULL,'.' || SIRD_SUBREVIEWNO) ," );
		 sql.append("  SIRD_REVIEWTYPE,SIRD_REVIEWPOINT FROM ENT_TL_SKILL_REVIEWPOINTDET ,ENT_TL_SKILL_REVIEWPOINTMST, ENT_TL_SPOKEMST " );
		 sql.append(" WHERE SIRD_SIRM_KEYID = SIRM_KEYID AND SPOK_KEYID = SIRD_SPOK_KEYID AND SIRM_KEYID='"+mstkeyid+"' " ) ;
		 sql.append(" ORDER BY SIRD_SPOK_KEYID, SIRD_REVIEWNO,  SIRD_SUBREVIEWNO ");
		 
		return sql.toString();
	}

	public static String getSkillRpSelectSql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_SKILL_REVIEWPOINTMST+ " where SIRM_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

}

