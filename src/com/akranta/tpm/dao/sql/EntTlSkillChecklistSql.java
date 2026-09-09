package com.akranta.tpm.dao.sql;

public class EntTlSkillChecklistSql {

	public static final String TBL_ENT_TL_SKILL_CHECKLIST = "ENT_TL_SKILL_CHECKLIST";  

	TableFieldType [] chekDbFields = null;

	public enum   tableFldConstants
	{
		keyid, skil_keyid, name, orderno, remarks, effective_date, inactive_date
		, skrm_keyid,  tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getChekDbFields() {
		return chekDbFields;
	}

	public EntTlSkillChecklistSql()
	{
		chekDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			chekDbFields[ i ] = new TableFieldType();
		}
		chekDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CHEK_KEYID";
		chekDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldName = "CHEK_SKIL_KEYID";
		chekDbFields[ tableFldConstants.skil_keyid.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.name.ordinal() ].fieldName = "CHEK_NAME";
		chekDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "CHEK_ORDERNO";
		chekDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		chekDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CHEK_REMARKS";
		chekDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.effective_date.ordinal() ].fieldName = "CHEK_EFFECTIVE_DATE";
		chekDbFields[ tableFldConstants.effective_date.ordinal() ].fieldType = 'D';

		chekDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldName = "CHEK_INACTIVE_DATE";
		chekDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldType = 'D';	
		
		chekDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "CHEK_SKRM_KEYID";
		chekDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CHEK_TEMPFIELD2";
		chekDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		chekDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CHEK_TEMPFIELD3";
		chekDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		chekDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CHEK_TEMPFIELD4";
		chekDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		chekDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CHEK_TEMPFIELD5";
		chekDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		chekDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CHEK_ACTIVE";
		chekDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		chekDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CHEK_CREATEDBY";
		chekDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		chekDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CHEK_CREATEDON";
		chekDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		chekDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CHEK_MODIFIEDON";
		chekDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_SKILL_CHECKLIST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_SKILL_CHECKLIST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_SKILL_CHECKLIST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getAllSkillCheckListSql() {
		/*return "SELECT CHEK_KEYID,''AS CHEK_ACTIVE,'' AS TICK,CHEK_SKIL_KEYID,CHEK_NAME,CHEK_REMARKS,CHEK_ORDERNO,CHEK_EFFECTIVE_DATE" +
				" FROM "+  TBL_ENT_TL_SKILL_CHECKLIST ;*/
		return  " SELECT  CHEK_KEYID, '' AS CHEK_ACTIVE,  '' AS TICK,  CHEK_SKIL_KEYID,  SKIL_NAME,  CHEK_NAME,  CHEK_REMARKS, " +
				" CHEK_ORDERNO,  CASE WHEN TO_CHAR(CHEK_EFFECTIVE_DATE, 'DD-MON-YYYY') = '01-JAN-1801'  THEN ''  " +
				" ELSE TO_CHAR(CHEK_EFFECTIVE_DATE, 'DD-MON-YYYY') END AS CHEK_EFFECTIVE_DATE  FROM ENT_TL_SKILL_CHECKLIST chk "+
				" JOIN ENT_TL_SKILLMST skil  ON chk.CHEK_SKIL_KEYID = skil.SKIL_KEYID  ORDER BY CHEK_ORDERNO ";
	
	}

	public static String getSkillCheckListSql() {
		
		return "SELECT * FROM "+ TBL_ENT_TL_SKILL_CHECKLIST +" WHERE CHEK_KEYID = ?";
	}

	public static String selectCheckListMainGrid() {
		return "SELECT "
				+ "    skil.skil_keyid, "
				+ "    skil.skil_name, "
				+ "    COUNT(chk.chek_skil_keyid) AS checklist_count "
				+ " FROM ent_tl_skillmst skil "
				+ " LEFT JOIN ent_tl_skill_checklist chk "
				+ "    ON chk.chek_skil_keyid = skil.skil_keyid "
				+ "GROUP BY "
				+ "    skil.skil_keyid, "
				+ "    skil.skil_name ";
	}

	public static String getCheckListBasedOnSkill() {
		return "SELECT CHEK_KEYID, '' AS CHEK_ACTIVE, '' AS TICK, CHEK_SKIL_KEYID, SKIL_NAME, CHEK_NAME," +
		" CHEK_REMARKS, CHEK_ORDERNO, DECODE(TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'))" +
		" FROM ENT_TL_SKILL_CHECKLIST,ent_tl_skillmst" +
		" where CHEK_SKIL_KEYID = SKIL_KEYID AND CHEK_SKIL_KEYID = ? ORDER BY CHEK_ORDERNO";
	}

}

