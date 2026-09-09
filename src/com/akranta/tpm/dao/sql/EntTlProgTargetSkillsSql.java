package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlProgTargetSkillsSql {

	public static final String TBL_ENT_TL_PROG_TARGET_SKILLS = "ENT_TL_PROG_TARGET_SKILLS";  

	TableFieldType [] prtsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prog_keyid, topi_keyid, skil_evaluvationtype, skil_deliverymode
		, eff_from_date, eff_till_date, impact_skillrate, rating_type
		, orderno, spoke_keyid, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPrtsDbFields() {
		return prtsDbFields;
	}

	public EntTlProgTargetSkillsSql()
	{
		prtsDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			prtsDbFields[ i ] = new TableFieldType();
		}
		prtsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PRTS_KEYID";
		prtsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "PRTS_PROG_KEYID";
		prtsDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "PRTS_TOPI_KEYID";
		prtsDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.skil_evaluvationtype.ordinal() ].fieldName = "PRTS_SKIL_EVALUVATIONTYPE";
		prtsDbFields[ tableFldConstants.skil_evaluvationtype.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.skil_deliverymode.ordinal() ].fieldName = "PRTS_SKIL_DELIVERYMODE";
		prtsDbFields[ tableFldConstants.skil_deliverymode.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "PRTS_EFF_FROM_DATE";
		prtsDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		prtsDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "PRTS_EFF_TILL_DATE";
		prtsDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		prtsDbFields[ tableFldConstants.impact_skillrate.ordinal() ].fieldName = "PRTS_IMPACT_SKILLRATE";
		prtsDbFields[ tableFldConstants.impact_skillrate.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.rating_type.ordinal() ].fieldName = "PRTS_RATING_TYPE";
		prtsDbFields[ tableFldConstants.rating_type.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "PRTS_ORDERNO";
		prtsDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		prtsDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldName = "PRTS_SPOKE_KEYID";
		prtsDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PRTS_TEMPFIELD2";
		prtsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PRTS_TEMPFIELD3";
		prtsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PRTS_TEMPFIELD4";
		prtsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PRTS_TEMPFIELD5";
		prtsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PRTS_ACTIVE";
		prtsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		prtsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PRTS_CREATEDBY";
		prtsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		prtsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PRTS_CREATEDON";
		prtsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		prtsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PRTS_MODIFIEDON";
		prtsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_PROG_TARGET_SKILLS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_PROG_TARGET_SKILLS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_PROG_TARGET_SKILLS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getFormData() {
		// TODO Auto-generated method stub
		return "select * from " +TableNames.TBL_ENT_TL_PROG_TARGET_SKILLS +" where PRTS_KEYID = ? ";
	}

	public static String skillGrid(String string) {
		// TODO Auto-generated method stub
		String sql = "select PRTS_KEYID,SKIL_NAME, EVAL_NAME ,TMOD_NAME,PRTS_SKIL_KEYID ,PRTS_SKIL_EVALUVATIONTYPE,PRTS_SKIL_DELIVERYMODE from "+
					 TBL_ENT_TL_PROG_TARGET_SKILLS+","+TableNames.TBL_ENT_TL_SKILLMST+","+TableNames.TBL_ENT_TL_EVALUATIONTYPEMST +","+TableNames.TBL_ENT_TL_DELIVERYMODEMST+
					 " WHERE PRTS_SKIL_KEYID=SKIL_KEYID"+
					 " AND PRTS_SKIL_EVALUVATIONTYPE=EVAL_KEYID AND PRTS_SKIL_DELIVERYMODE=TMOD_KEYID AND PRTS_PROG_KEYID='"+string+"'";
		CommonMessage.debugMsg("Skill tabel   :"+sql);
		return sql;
	}

	public static String getDeleteSkillSql(String prtsKeyid) {
		// TODO Auto-generated method stub
		return "Delete from "+TBL_ENT_TL_PROG_TARGET_SKILLS+" Where PRTS_KEYID = '"+prtsKeyid+"'";
	}

	public static String getDeleteSkillDtlSql(String prtsKeyid) {
		// TODO Auto-generated method stub
		return "Delete from "+TableNames.TBL_ENT_TL_PROG_IMPACT_SKILLS+" Where PIMS_PRTS_KEYID = '"+prtsKeyid+"'";
	}

}

