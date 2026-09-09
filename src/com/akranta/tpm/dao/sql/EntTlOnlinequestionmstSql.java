package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.controller.UIUtils;

public class EntTlOnlinequestionmstSql {

	public static final String TBL_ENT_TL_ONLINEQUESTIONMST = "ENT_TL_ONLINEQUESTIONMST";  

	TableFieldType [] olqmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, programid, topicid, type, question, imagename, hint, description
		, marks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getOlqmDbFields() {
		return olqmDbFields;
	}

	public EntTlOnlinequestionmstSql()
	{
		olqmDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			olqmDbFields[ i ] = new TableFieldType();
		}
		olqmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OLQM_KEYID";
		olqmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.programid.ordinal() ].fieldName = "OLQM_PROGRAMID";
		olqmDbFields[ tableFldConstants.programid.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "OLQM_TOPICID";
		olqmDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "OLQM_TYPE";
		olqmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.question.ordinal() ].fieldName = "OLQM_QUESTION";
		olqmDbFields[ tableFldConstants.question.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.imagename.ordinal() ].fieldName = "OLQM_IMAGENAME";
		olqmDbFields[ tableFldConstants.imagename.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.hint.ordinal() ].fieldName = "OLQM_HINT";
		olqmDbFields[ tableFldConstants.hint.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "OLQM_DESCRIPTION";
		olqmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.marks.ordinal() ].fieldName = "OLQM_MARKS";
		olqmDbFields[ tableFldConstants.marks.ordinal() ].fieldType = 'N';

		olqmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OLQM_TEMPFIELD1";
		olqmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OLQM_TEMPFIELD2";
		olqmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OLQM_TEMPFIELD3";
		olqmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OLQM_TEMPFIELD4";
		olqmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OLQM_TEMPFIELD5";
		olqmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OLQM_CREATEDBY";
		olqmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		olqmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OLQM_ACTIVE";
		olqmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		olqmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OLQM_CREATEDON";
		olqmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		olqmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OLQM_MODIFIEDON";
		olqmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ONLINEQUESTIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ONLINEQUESTIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ONLINEQUESTIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getRecall(String keyid) throws Exception{
		String sql = "SELECT *";
		sql+="FROM ENT_TL_ONLINEQUESTIONMST E where Olqm_keyid ='"+keyid+"'";
		return sql;
	}

	public static StringBuffer getAnswer(String keyId) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select 'Keyid','Answer','CorrectAns','Sort order','selectAnswer','Delete'  from dual");
		if(UIUtils.isValidKeyId(keyId)){
		sql.append(" union all");	
		sql.append(" select olqd_keyid,olqd_answer,'',to_char(olqd_sortorder),yesno,'' ");
		sql.append(" from ( select olqd_keyid,olqd_answer,'',olqd_sortorder ,to_char(decode(olqd_correctanswer,'Y',1,'N',0)) as yesno,'' ");
		sql.append(" from ent_tl_onlinequestiondtl where olqd_olqm_keyid = '"+keyId+"'");
		sql.append(" order by olqd_sortorder )" ); 
		}
		return sql;
	}

	public static String getAllKeyId(String keyid) {
		String sql="SELECT  OLPD_OLQM_KEYID FROM ent_tl_onlinepreparetestdtl where OLPD_OLPM_KEYID='"+keyid+"' order by OLPD_OLQM_KEYID asc";
		return sql;
	}

	public static StringBuffer getAllQusAns(String questKeyId) {
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT  OLQM_QUESTION AS QUESTIONS ,OLQD_KEYID,OLQD_ANSWER,OLQM_TOPICID,OLQM_PROGRAMID,OLQM_TYPE,OLQM_KEYID,OLQD_CORRECTANSWER,OLQM_IMAGENAME  FROM ent_tl_onlinequestionmst,ent_tl_onlinequestiondtl");//for answers
		sql.append(" where OLQM_KEYID(+)=OLQD_OLQM_KEYID  and OLQD_OLQM_KEYID='");
		sql.append(questKeyId);
		sql.append("' order by olqd_keyid asc");
		return sql;
	}

	public static StringBuffer getAllKeyIdFrmTemp(String tempKeyId) {
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT TEMT_KEYID,TEMT_EMPLOYEEID,TEMT_OLPM_KEYID,TEMT_PROGRAMID,TEMT_TOPICID,TEMT_QUESTIONID,TEMT_ANSWERID,TEMT_TESTSTARTTIME,TEMT_MARKFORREVIEW,TEMT_ISCORRECTANS");
		sql.append(" FROM Ent_Tl_Temponlinetest");
		sql.append(" WHERE TEMT_KEYID='");
		sql.append(tempKeyId);
		sql.append("' order by TEMT_QUESTIONID asc");
		return sql;
	}

	public static StringBuffer  getAllRviewQuestion() {
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT TEMT_KEYID,TEMT_EMPLOYEEID,TEMT_OLPM_KEYID,TEMT_PROGRAMID,TEMT_TOPICID,TEMT_QUESTIONID,TEMT_ANSWERID,TEMT_TESTSTARTTIME,TEMT_MARKFORREVIEW");
		sql.append(" FROM Ent_Tl_Temponlinetest");
		sql.append(" WHERE TEMT_MARKFORREVIEW='Y' order by TEMT_QUESTIONID asc");
		
		return sql;
	}

	public static StringBuffer getAllattendedQuest() {
		StringBuffer sql =new StringBuffer();
		sql.append("select count(TEMT_ANSWERID)from ENT_TL_TEMPONLINETEST where TEMT_ANSWERID not in '{}'");
		return sql;
	}
	

}

