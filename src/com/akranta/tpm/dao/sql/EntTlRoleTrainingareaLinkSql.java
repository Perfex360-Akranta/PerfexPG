package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlRoleTrainingareaLinkSql {

	public static final String TBL_ENT_TL_ROLE_TRAININGAREA_LINK = "ENT_TL_ROLE_TRAININGAREA_LINK";  

	TableFieldType [] rtalDbFields = null;

	public enum   tableFldConstants
	{
		keyid, trar_keyid, role_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getRtalDbFields() {
		return rtalDbFields;
	}

	public EntTlRoleTrainingareaLinkSql()
	{
		rtalDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			rtalDbFields[ i ] = new TableFieldType();
		}
		rtalDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RTAL_KEYID";
		rtalDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rtalDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldName = "RTAL_TRAR_KEYID";
		rtalDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldType = 'V';

		rtalDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "RTAL_ROLE_KEYID";
		rtalDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		rtalDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RTAL_TEMPFIELD1";
		rtalDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RTAL_TEMPFIELD2";
		rtalDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RTAL_TEMPFIELD3";
		rtalDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RTAL_TEMPFIELD4";
		rtalDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "RTAL_TEMPFIELD5";
		rtalDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RTAL_ACTIVE";
		rtalDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rtalDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RTAL_CREATEDBY";
		rtalDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rtalDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RTAL_CREATEDON";
		rtalDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rtalDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RTAL_MODIFIEDON";
		rtalDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_TRAININGAREA_LINK, fieldTypeArr, dataArray);
	}
	public static String getSelectSql()
	{
		return " Select * from " + TBL_ENT_TL_ROLE_TRAININGAREA_LINK + " where RTAL_KEYID = ? ";
	}
	
	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_TRAININGAREA_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_TRAININGAREA_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getRoEvalDat(String trainAreaId, String topicId, String roleId, String spokId) {
		// TODO Auto-generated method stub
		StringBuffer sb =new StringBuffer();
		sb.append("SELECT RTLK_TARGETSKRM_KEYID,TOPI_TYPE,TOPI_EVALUATIONTYPEID,RTRL_KEYID,RATINGID,RATING,RATINGDESC,");
		sb.append("RTRL_CUTOFF,RTRL_CRITERIADESC,CHECKLISTID FROM (SELECT A.* , SKRM_ORDERNO RATING,SKRM_KEYID RATINGID,");
		sb.append("SKRM_DESCRIPTION RATINGDESC  FROM (SELECT RTRL_SKRM_KEYID, RTRL_KEYID,RTLK_RTAL_KEYID, "); 
		sb.append("RTRL_CUTOFF,RTRL_CRITERIADESC,RTRL_RTLK_KEYID, RTLK_TOPI_KEYID ,RTLK_SPOK_ID,RTLK_TARGETSKRM_KEYID,RTAL_ROLE_KEYID ,RTAL_TRAR_KEYID FROM ");
		sb.append(" ENT_TL_ROLE_TOPIC_RATING, ENT_TL_ROLE_TOPIC_LINK, ENT_TL_TOPICMST ,ENT_TL_ROLE_TRAININGAREA_LINK ");
		sb.append(" WHERE RTRL_RTLK_KEYID = RTLK_KEYID AND TOPI_KEYID = RTLK_TOPI_KEYID AND RTLK_RTAL_KEYID = RTAL_KEYID ) A,");
		sb.append(" ENT_TL_SKILL_RATINGMST WHERE RTRL_SKRM_KEYID(+) = SKRM_KEYID AND ");
		sb.append(" RTAL_TRAR_KEYID(+)='"+trainAreaId+"' and  RTAL_ROLE_KEYID(+) ='"+roleId+"' AND ");
		sb.append(" RTLK_TOPI_KEYID(+) ='"+topicId+"' ");
		sb.append(" AND RTLK_SPOK_ID (+) ='"+spokId+"' ");
		//sb.append(" AND RTLK_RTAL_KEYID(+) = '"+trainAreaId+"' " );
		sb.append(" ) B, ( ");
		sb.append(" SELECT TOPI_KEYID,TOPI_TYPE,TOPI_EVALUATIONTYPEID,DECODE(CHKM_SKRM_KEYID,SKRM_KEYID, CHKM_KEYID,NULL) CHECKLISTID, SKRM_KEYID  FROM (");    
		sb.append(" SELECT TOPI_KEYID,TOPI_TYPE,TOPI_EVALUATIONTYPEID,CHKM_KEYID,CHKM_SKRM_KEYID FROM ENT_TL_TOPICMST, ENT_TL_CHECKLISTMST ");
		sb.append(" WHERE  CHKM_TOPI_KEYID(+) = TOPI_KEYID) C,ENT_TL_SKILL_RATINGMST WHERE nvl(chkm_skrm_keyid,skrm_keyid) = SKRM_KEYID AND TOPI_KEYID(+) ='"+topicId+"') D ");
		//sb.append(" WHERE D.SKRM_KEYID(+) = B.RATINGID");
		sb.append(" WHERE nvl(d.skrm_keyid(+),b.ratingid) = b.ratingid ");
		sb.append("	ORDER BY RATING");
		CommonMessage.debugMsg("ddd   :;"+sb.toString());
		return sb.toString();
	}

}

