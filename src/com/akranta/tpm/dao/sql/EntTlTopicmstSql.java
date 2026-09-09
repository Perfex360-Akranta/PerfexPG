package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
 
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTopicmstSql {

	public static final String TBL_ENT_TL_TOPICMST = "ENT_TL_TOPICMST";  

	TableFieldType [] topiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, locationid, code, name, parentid, ischild, evaluationtypeid
		, type, remarks, effective_date, inactive_date,spokeid, relatedto
		, trainingmode, category, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getTopiDbFields() {
		return topiDbFields;
	}

	public EntTlTopicmstSql()
	{
		topiDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			topiDbFields[ i ] = new TableFieldType();
		}
		topiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TOPI_KEYID";
		topiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "TOPI_LOCATIONID";
		topiDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.code.ordinal() ].fieldName = "TOPI_CODE";
		topiDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.name.ordinal() ].fieldName = "TOPI_NAME";
		topiDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "TOPI_PARENTID";
		topiDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.ischild.ordinal() ].fieldName = "TOPI_ISCHILD";
		topiDbFields[ tableFldConstants.ischild.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.evaluationtypeid.ordinal() ].fieldName = "TOPI_EVALUATIONTYPEID";
		topiDbFields[ tableFldConstants.evaluationtypeid.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.type.ordinal() ].fieldName = "TOPI_TYPE";
		topiDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "TOPI_REMARKS";
		topiDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.effective_date.ordinal() ].fieldName = "TOPI_EFFECTIVE_DATE";
		topiDbFields[ tableFldConstants.effective_date.ordinal() ].fieldType = 'D';

		topiDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldName = "TOPI_INACTIVE_DATE";
		topiDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldType = 'D';

		topiDbFields[ tableFldConstants.spokeid.ordinal() ].fieldName = "TOPI_SPOKEID";
		topiDbFields[ tableFldConstants.spokeid.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "TOPI_RELATEDTO";
		topiDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.trainingmode.ordinal() ].fieldName = "TOPI_TRAININGMODE";
		topiDbFields[ tableFldConstants.trainingmode.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.category.ordinal() ].fieldName = "TOPI_CATEGORY";
		topiDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TOPI_TEMPFIELD5";
		topiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TOPI_ACTIVE";
		topiDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		topiDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TOPI_CREATEDBY";
		topiDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		topiDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TOPI_CREATEDON";
		topiDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		topiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TOPI_MODIFIEDON";
		topiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TOPICMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TOPICMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TOPICMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Select * from " + TableNames.TBL_ENT_TL_TOPICMST ;
		
		sql += " where 1=1 " ;
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.name.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.name.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.name.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
			sql += " and " +  fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "'";
		
		return sql;
	}
	
	public static String getSkillLevelSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT LEVEL ");		//, SKIL_NAME 
		sql.append(" FROM " + TableNames.TBL_ENT_TL_SKILLMST  );
		sql.append( " WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'");
		sql.append(" START WITH SKIL_KEYID = SKIL_PARENTID  " );
		sql.append(" CONNECT BY nocycle prior SKIL_KEYID = SKIL_PARENTID " );
		
		return sql.toString();
	}

	public static String TopicDetailGrid(CommonFilter commonFilter,String TopicKeyid) {
		
	    //String sql=" SELECT * FROM ( SELECT TOPI_KEYID as topicKeyid,TOPI_NAME as TTXTOPICNAME,DECODE(TOPI_TYPE,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE') as KSA,TOPI_REMARKS ";
	    //sql+="  FROM ENT_TL_TOPICMST ) A WHERE 1=1  ";
	    //sql+= FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter());
		
		StringBuffer sf = new StringBuffer();
		
		/*
		 * sf.
		 * append(" SELECT * FROM ( SELECT TOPI_KEYID as topicKeyid,TOPI_NAME as TTXTOPICNAME,DECODE(TOPI_TYPE,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE') as KSA, "
		 * ); sf.append(" locn_name as LocationName,TOPI_REMARKS ");
		 * sf.append(" FROM ENT_TL_TOPICMST ,gen_vw_fnln ");
		 */
		
		sf.append(" SELECT * FROM ( SELECT topi.topi_keyid   AS topickeyid, topi.topi_name    AS ttxtopicname,  CASE topi.topi_type ");
		sf.append(" WHEN 'S' THEN 'SKILL'  WHEN 'K' THEN 'KNOWLEDGE'  WHEN 'A' THEN 'ATTITUDE'  END AS ksa, ");
		sf.append(" fnln.locn_name    AS locationname, topi.topi_remarks FROM ent_tl_topicmst topi JOIN gen_vw_fnln fnln ");
		sf.append(" ON fnln.fnln_keyid = topi.topi_locationid ");
		
		String flid = commonFilter.getFlid();
		if (UIUtils.isValidKeyId(flid)) {
			sf.append(" WHERE topi.topi_locationid IN ( SELECT fna.fnln_keyid FROM gen_tl_functionallocn fna WHERE fna.fnln_originalid IN ( ");
			sf.append(" SELECT SUBSTRING(gvf.fnln_elementid FROM 12 FOR 10) FROM gen_vw_fnln gvf JOIN gen_mv_flidhierarchy flh  ON flh.flid = gvf.fnln_keyid ");
			sf.append("  WHERE POSITION( '"+flid+"' IN (flh.parentflids || '/' || flh.flid) ) > 0 ) ) ");

		}
		sf.append(" AND fnln_keyid=TOPI_LOCATIONID) A WHERE 1=1 " );		
		sf.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
	    CommonMessage.debugMsg("commonFilter.getFlid=="+commonFilter.getFlid());
	    CommonMessage.debugMsg("The TopicDetailInfo"+sf.toString());   
		return sf.toString();
	}
	public String getRecall(String keyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT *  ";
		sql+=" FROM ENT_TL_TOPICMST where TOPI_KEYID ='"+keyid+"'";
		return sql;
	}

	public String getCountAll(CommonFilter commonFilter) {
		String sql=" SELECT COUNT(*) FROM(SELECT TOPI_KEYID,TOPI_NAME,CASE t.TOPI_TYPE  WHEN 'S' THEN 'SKILL' ";
				 sql+="     WHEN 'K' THEN 'KNOWLEDGE' ";
				 sql+= "    WHEN 'A' THEN 'ATTITUDE' ";
				 sql+= "    END AS TOPI_TYPE_NAME, ";
	    sql+=" SPOK_NAME FROM  ENT_TL_TOPICMST t LEFT JOIN ENT_TL_SPOKEMST s ON s.SPOK_KEYID = t.TOPI_SPOKEID)  ";
	    
	    CommonMessage.debugMsg(" count sql  "+sql);
		return sql;
	}
	
	

	

}

