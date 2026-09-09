package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.EntTlChecklistdtl;

public class EntTlChecklistmstSql {

	public static final String TBL_ENT_TL_CHECKLISTMST = "ENT_TL_CHECKLISTMST";  
	public static final String TBL_ENT_TL_SKILL_CHECKLIST = "ENT_TL_SKILL_CHECKLIST";
	public static final String TBL_ENT_TL_CHECKLISTDTL = "ENT_TL_CHECKLISTDTL";
	
	TableFieldType [] chkmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, topi_keyid, skrm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getChkmDbFields() {
		return chkmDbFields;
	}

	public EntTlChecklistmstSql()
	{
		chkmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			chkmDbFields[ i ] = new TableFieldType();
		}
		chkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CHKM_KEYID";
		chkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		chkmDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "CHKM_TOPI_KEYID";
		chkmDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		chkmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "CHKM_SKRM_KEYID";
		chkmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		chkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CHKM_TEMPFIELD1";
		chkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		chkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CHKM_TEMPFIELD2";
		chkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		chkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CHKM_TEMPFIELD3";
		chkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		chkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CHKM_TEMPFIELD4";
		chkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		chkmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CHKM_ACTIVE";
		chkmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		chkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CHKM_CREATEDBY";
		chkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		chkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CHKM_CREATEDON";
		chkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		chkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CHKM_MODIFIEDON";
		chkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_CHECKLISTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_CHECKLISTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_CHECKLISTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getAllSkillCheckListSql() {
		
		StringBuffer str = new StringBuffer();
		
		str.append(" SELECT ");
		str.append(" CHEK_KEYID, ");
		str.append(" '' AS CHEK_ACTIVE, ");
		str.append(" '' AS TICK, ");
		str.append(" CHEK_SKIL_KEYID, ");
		str.append(" SKIL_NAME, ");
		str.append(" CHEK_NAME, ");
		str.append(" CHEK_REMARKS, ");
		str.append(" CHEK_ORDERNO, ");
		str.append(" CASE ");
		str.append("    WHEN TO_CHAR(CHEK_EFFECTIVE_DATE, 'DD-MON-YYYY') = '01-JAN-1801' ");
		str.append("    THEN '' ");
		str.append("    ELSE TO_CHAR(CHEK_EFFECTIVE_DATE, 'DD-MON-YYYY') ");
		str.append(" END AS CHEK_EFFECTIVE_DATE ");
		str.append(" FROM ENT_TL_SKILL_CHECKLIST chk ");
		str.append(" JOIN ENT_TL_SKILLMST skil ");
		str.append(" ON chk.CHEK_SKIL_KEYID = skil.SKIL_KEYID ");
		str.append(" ORDER BY CHEK_ORDERNO ");

		return str.toString();
		
		/*return "SELECT CHEK_KEYID, '' AS CHEK_ACTIVE, '' AS TICK, CHEK_SKIL_KEYID, SKIL_NAME, CHEK_NAME," +
				" CHEK_REMARKS, CHEK_ORDERNO, DECODE(TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'))," +
				" FROM ENT_TL_SKILL_CHECKLIST,ent_tl_skillmst" +
				" where CHEK_SKIL_KEYID = SKIL_KEYID ORDER BY CHEK_ORDERNO";*/
	}
	
	public static String getSkillCheckListSql() {
		
		return "SELECT * FROM "+ TBL_ENT_TL_CHECKLISTMST +" WHERE CHKM_KEYID = ?";
	}

	public static String getCheckListBasedOnSkill() {
		
		StringBuffer str = new StringBuffer();
		
		   str.append(" SELECT chkd.chkd_keyid,chkm.chkm_keyid, '' AS chkm_active,'' AS tick,chkm.chkm_topi_keyid,topi.topi_name, ");
		   str.append(" chkd.chkd_name,chkd.chkd_remarks,chkd.chkd_orderno, ");
		   str.append(" CASE WHEN chkd.chkd_effective_date = DATE '1801-01-01'  THEN '' ELSE TO_CHAR(chkd.chkd_effective_date, 'DD-MON-YYYY') ");
		   str.append(" END AS chkd_effective_date FROM ent_tl_topicmst topi LEFT JOIN ent_tl_checklistmst chkm ON chkm.chkm_topi_keyid = topi.topi_keyid ");
		   str.append(" JOIN ent_tl_checklistdtl chkd  ON chkm.chkm_keyid = chkd.chkd_chkm_keyid WHERE chkm.chkm_topi_keyid = ? ORDER BY chkd.chkd_orderno ");
		return str.toString();
		
		/*return "SELECT CHEK_KEYID, '' AS CHEK_ACTIVE, '' AS TICK, CHEK_SKIL_KEYID, SKIL_NAME, CHEK_NAME," +
		" CHEK_REMARKS, CHEK_ORDERNO, DECODE(TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(CHEK_EFFECTIVE_DATE,'DD-MON-YYYY'))" +
		" FROM ENT_TL_SKILL_CHECKLIST,ent_tl_skillmst" +
		" where CHEK_SKIL_KEYID = SKIL_KEYID AND CHEK_SKIL_KEYID = ? ORDER BY CHEK_ORDERNO";*/
	}
	
	public static String selectCheckListMainGrid() {
		StringBuffer str = new StringBuffer();
		
		str.append(" SELECT  skil_keyid, skil_name,COUNT (CHEK_SKIL_KEYID) FROM");
		str.append(" ent_tl_skill_checklist LEFT JOIN ent_tl_skillmst ON  CHEK_SKIL_KEYID = SKIL_KEYID");
		str.append(" GROUP BY SKIL_KEYID,SKIL_name");
		
		return str.toString();
		
		/*return "SELECT  skil_keyid, skil_name,COUNT (CHEK_SKIL_KEYID) FROM" +
				" ent_tl_skill_checklist,ent_tl_skillmst WHERE CHEK_SKIL_KEYID(+) = SKIL_KEYID" +
				" GROUP BY SKIL_KEYID,SKIL_name";*/
	}

public static String getselectRank() {
		
		return "SELECT * FROM "+ TBL_ENT_TL_CHECKLISTMST +" WHERE CHKM_TOPI_KEYID = ? AND CHKM_SKRM_KEYID = ?";
	}

public static String selectDtl() {
	return "SELECT * FROM "+ TBL_ENT_TL_CHECKLISTDTL +" WHERE CHKD_CHKM_KEYID = ?";
}

public static String deleteDetail(EntTlChecklistdtl entTlChecklistdtl) {
	return "DELETE FROM "+TBL_ENT_TL_CHECKLISTDTL+" WHERE  CHKD_KEYID='"+entTlChecklistdtl.getChkdKeyid()+"'";
}

public static String getCheckListBasedTopicRankSql(String topicId,String rattingId) {
	
	StringBuffer str = new StringBuffer();
	str.append(" SELECT CHKD_KEYID,CHKM_KEYID, '' AS CHKM_ACTIVE, '' AS TICK, CHKM_TOPI_KEYID, TOPI_NAME, CHKD_NAME, CHKD_REMARKS, CHKD_ORDERNO, ");
	str.append(" DECODE(TO_CHAR(CHKD_EFFECTIVE_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(CHKD_EFFECTIVE_DATE,'DD-MON-YYYY'))");  
	str.append(" FROM ENT_TL_CHECKLISTMST,ENT_TL_CHECKLISTDTL,ENT_TL_TOPICMST where CHKM_TOPI_KEYID(+) = TOPI_KEYID"); 
	str.append(" AND CHKM_KEYID = CHKD_CHKM_KEYID AND CHKM_TOPI_KEYID = '"+topicId+"' and CHKM_SKRM_KEYID = '"+rattingId+"' ORDER BY CHKD_ORDERNO");
	return str.toString();
}
	
}

