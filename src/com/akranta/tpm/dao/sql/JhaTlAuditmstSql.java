package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
//import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class JhaTlAuditmstSql {

	public static final String TBL_JHA_TL_AUDITMST = "JHA_TL_AUDITMST";  

	TableFieldType [] jhamDbFields = null;

	public enum   tableFldConstants
	{
		keyid, auditdate,auditpillar,audittype,auditflid,  auditteamid, auditorname, leadername
		, totalpoints, auditortype, nextauditdate, nextauditteam, jhstepid
		, status,auditupload,tempfield2,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJhamDbFields() {
		return jhamDbFields;
	}

	public JhaTlAuditmstSql()
	{
		jhamDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			jhamDbFields[ i ] = new TableFieldType();
		}
		jhamDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "JHAM_KEYID";
		jhamDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.auditdate.ordinal() ].fieldName = "JHAM_AUDITDATE";
		jhamDbFields[ tableFldConstants.auditdate.ordinal() ].fieldType = 'D';
		
		jhamDbFields[ tableFldConstants.auditpillar.ordinal() ].fieldName = "JHAM_AUDITPILLAR";
		jhamDbFields[ tableFldConstants.auditpillar.ordinal() ].fieldType = 'C';
		
		jhamDbFields[ tableFldConstants.audittype.ordinal() ].fieldName = "JHAM_AUDITTYPE";
		jhamDbFields[ tableFldConstants.audittype.ordinal() ].fieldType = 'C';
		
		jhamDbFields[ tableFldConstants.auditflid.ordinal() ].fieldName = "JHAM_FLID";
		jhamDbFields[ tableFldConstants.auditflid.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.auditteamid.ordinal() ].fieldName = "JHAM_AUDITTEAMID";
		jhamDbFields[ tableFldConstants.auditteamid.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.auditorname.ordinal() ].fieldName = "JHAM_AUDITORNAME";
		jhamDbFields[ tableFldConstants.auditorname.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.leadername.ordinal() ].fieldName = "JHAM_LEADERNAME";
		jhamDbFields[ tableFldConstants.leadername.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.totalpoints.ordinal() ].fieldName = "JHAM_TOTALPOINTS";
		jhamDbFields[ tableFldConstants.totalpoints.ordinal() ].fieldType = 'N';

		jhamDbFields[ tableFldConstants.auditortype.ordinal() ].fieldName = "JHAM_AUDITORTYPE";
		jhamDbFields[ tableFldConstants.auditortype.ordinal() ].fieldType = 'C';

		jhamDbFields[ tableFldConstants.nextauditdate.ordinal() ].fieldName = "JHAM_NEXTAUDITDATE";
		jhamDbFields[ tableFldConstants.nextauditdate.ordinal() ].fieldType = 'D';

		jhamDbFields[ tableFldConstants.nextauditteam.ordinal() ].fieldName = "JHAM_NEXTAUDITTEAM";
		jhamDbFields[ tableFldConstants.nextauditteam.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.jhstepid.ordinal() ].fieldName = "JHAM_JHSTEPID";
		jhamDbFields[ tableFldConstants.jhstepid.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.status.ordinal() ].fieldName = "JHAM_STATUS";
		jhamDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';
		
		jhamDbFields[ tableFldConstants.auditupload.ordinal() ].fieldName = "JHAM_AUDITUPLOAD";
		jhamDbFields[ tableFldConstants.auditupload.ordinal() ].fieldType = 'V';
		
		jhamDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "JHAM_TEMPFIELD2";
		jhamDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		jhamDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JHAM_TEMPFIELD3";
		jhamDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		jhamDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "JHAM_TEMPFIELD4";
		jhamDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		jhamDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JHAM_TEMPFIELD5";
		jhamDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JHAM_ACTIVE";
		jhamDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jhamDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JHAM_CREATEDBY";
		jhamDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jhamDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JHAM_CREATEDON";
		jhamDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jhamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JHAM_MODIFIEDON";
		jhamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getjhAuditGridSql(JhaTlAuditmst jhaTlAuditmst,String flId,String jhamKeyID)
	{
			String sql = "  SELECT  JAUT_KEYID, JAUT_PARAMETERNAME,  JAUT_PARAMETERDESCRIPTION, JAUT_MAXIMUMPOINTS ,  ";
			CommonMessage.debugMsg("jhamKeyID"+jhamKeyID);
			
			sql +=" JHAP_KEYID AS PARAMETERID, JHAP_KEYID AS PARAMETERID";
			if(CommonFunctions.isValidKeyId(jhamKeyID)){
				sql +=" ,JHAD_POINTSSCORED,'' as grade,JHAD_REMARKS,";
				sql +=" '',JHAD_KEYID";
			}	
			sql +=" FROM   JHA_TL_AUDITPARAMETER,JHA_TL_AUDITTEMPLATE,";
			//sql +=" JHA_TL_TEMPLATEMCHLINK,JHA_TL_TEMPLATESTEPLINK ";
			sql +=" JHA_TL_TEMPLATESTEPLINK ";
			if(CommonFunctions.isValidKeyId(jhamKeyID)){
				sql +=" ,JHA_TL_AUDITDTL ";
			}
			sql +="	WHERE   JAUT_ACTIVE = 'Y'  AND JHAP_KEYID = JTSL_TEMPLATEID "; 
			sql +=" AND JHAP_KEYID = JTML_TEMPLATEID  AND JHAP_KEYID = JAUT_MASTERID " ;
			if(CommonFunctions.isValidKeyId(jhamKeyID)){
				sql +=" AND JHAD_PARAMETERID=JAUT_KEYID and JHAD_JHAUDITMASTERID ='"+jhamKeyID +"'";
			}
			
			/*if(CommonFunctions.isValidKeyId(machineId)){
				sql += " and JTML_MACHINEID ='" + machineId + "'";
			}*/
			if(CommonFunctions.isValidKeyId(flId)){
				sql += " and JHAP_FLID ='" + flId + "'";
			}
			CommonMessage.debugMsg("sql"+sql);
		
		return sql;
	}
	public static String getgradeidsql(String point,String keyId)
	{
			String sql = "  SELECT  JHAG_KEYID AS KEYID , JHAG_NAME  AS NAME FROM  ";
			sql +=" JHA_TL_GRADEMST, JHA_TL_TEMPLATEGRADELINK ";
			sql +=" WHERE   JHAG_KEYID = JTGL_GRADEID " ;
			if(CommonFunctions.isValidKeyId(keyId)){
				sql +="	AND JTGL_TEMPLATEID = '"+keyId + "'"; 
			}	
			if(CommonFunctions.isValidKeyId(point)){
				sql += " and " + point + " BETWEEN JTGL_MINIMUMMARKS AND JTGL_MAXIMUMMARKS ";
			}
		return sql;
	}
	
	public static String getAUDITMSTSql(TableFieldType [] fieldTypeArr, Object [] dataArray ){//String keyId,String flId,String auditTeamId,String auditType,String auditPillar,String stepId,String auditDate,String auditorType) {
		
		StringBuffer sql = new StringBuffer();
		sql.append( " SELECT * from " + TBL_JHA_TL_AUDITMST + " where 1=1 " );
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.auditflid.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.auditflid.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.auditflid.ordinal()] + "'");
		}		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.auditteamid.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.auditteamid.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.auditteamid.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.audittype.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.audittype.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.audittype.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.auditpillar.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.auditpillar.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.auditpillar.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.jhstepid.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.jhstepid.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.jhstepid.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.auditdate.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.auditdate.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.auditdate.ordinal()] + "'");
		}
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.auditortype.ordinal()])){
			sql.append( "  and "+fieldTypeArr[tableFldConstants.auditortype.ordinal()].fieldName +"='" + (String)dataArray[ tableFldConstants.auditortype.ordinal()] + "'");
		}
		
		CommonMessage.debugMsg("sql===="+sql.toString());
		return sql.toString();		
	}
		
	public static String getminPointssql(String parameter,String auditTeam)
	{
			String sql = "  SELECT  JTLL_MINIMUMPOINTS AS MINPOINT  FROM   ";
			sql +=" JHA_TL_TEMPLATELEVELLINK WHERE ";
			sql +=" JTLL_TEMPLATEID='" + parameter +"'";
			sql +=" and  JTLL_AUDITLEVELID = '"+ auditTeam+"'";
			
		return sql;
	}
	public static String getjhStepsql(String flId)
	{
			/*String sql = "  SELECT  JHSM_KEYID AS KEYID  FROM  GEN_TL_JHSTEPMST ,JHA_TL_AUDITMST ,GEN_TL_MACHINEMST   ";
			sql +="  WHERE  JHSM_KEYID = JHAM_JHSTEPID (+)  AND JHSM_KEYID  = MCHM_JHSTEP(+) AND JHSM_ACTIVE = 'Y' AND MCHM_ACTIVE ='Y'";
		    sql +=" and MCHM_KEYID = '"+machineId+"'";*/
		    
		    String sql = "  SELECT  JHSM_KEYID AS KEYID  FROM  GEN_TL_JHSTEPMST ,JHA_TL_AUDITMST    ";
			sql +="  WHERE  JHSM_KEYID = JHAM_JHSTEPID (+)  AND JHSM_KEYID  = JHAM_FLID(+) AND JHSM_ACTIVE = 'Y' ";
			if(CommonFunctions.isValidKeyId(flId)){
				sql +=" and JHAM_FLID = '"+flId+"'";
			}		    
			CommonMessage.debugMsg("sql"+sql);
		return sql;
	}
	public static String getAuditLevelsql(String flId)
	{
			/*String sql = "  SELECT  JHSM_KEYID AS KEYID  FROM  GEN_TL_JHSTEPMST ,JHA_TL_AUDITMST ,GEN_TL_MACHINEMST   ";
			sql +="  WHERE  JHSM_KEYID = JHAM_JHSTEPID (+)  AND JHSM_KEYID  = MCHM_JHSTEP(+) AND JHSM_ACTIVE = 'Y' AND MCHM_ACTIVE ='Y'";
		    sql +=" and MCHM_KEYID = '"+machineId+"'";*/
		    
		    String sql = "  SELECT  JHSM_KEYID AS KEYID  FROM  GEN_TL_JHSTEPMST ,JHA_TL_AUDITMST    ";
			sql +="  WHERE  JHSM_KEYID = JHAM_JHSTEPID (+)  AND JHSM_KEYID  = JHAM_FLID(+) AND JHSM_ACTIVE = 'Y' ";
			if(CommonFunctions.isValidKeyId(flId)){
				sql +=" and JHAM_FLID = '"+flId+"'";
			}		    
			CommonMessage.debugMsg("sql"+sql);
		return sql;
	}
	public static String getAuditLevel(String templateId,String flId, String jhStepId)
	{
		 String sql =""; 
		/*String sql = " select distinct JHAT_KEYID from JHA_TL_AUDITTEAM where JHAT_KEYID NOT IN  ( ";
		sql+=" SELECT JHAM_AUDITTEAMID FROM jha_tl_auditmst WHERE  JHAM_MACHINEID= '"+machineId+"') order by JHAT_KEYID";
		
		 sql =	 " select  JTLL_AUDITLEVELID from JHA_TL_TEMPLATELEVELLINK where 1=1" +
 		" JTLL_TEMPLATEID NOT IN  (  ";
		sql += "SELECT JHAM_AUDITTEAMID FROM jha_tl_auditmst WHERE 1=1 ";
		if(CommonFunctions.isValidKeyId(flId)){
			sql += " and JHAM_FLID='" + flId + "'  ";
		}    		
		sql += ")  ";
		if(CommonFunctions.isValidKeyId(templateId)){
			sql += "and JTLL_TEMPLATEID='" + templateId + "'";
		}
		sql += " order by JTLL_AUDITLEVELID ";
		JTLL_AUDITLEVELID,JTLL_MINIMUMPOINTS
		*/
	    sql =  " select * from JHA_TL_TEMPLATELEVELLINK where 1=1 " ;	    	
		if(CommonFunctions.isValidKeyId(templateId)){
			sql += "and JTLL_TEMPLATEID='" + templateId + "'";
		}
		if(CommonFunctions.isValidKeyId(jhStepId)){
			sql += "and JTLL_AUDITLEVELID='" + jhStepId + "'";
		}
		
		sql += " order by JTLL_AUDITLEVELID ";
	    CommonMessage.debugMsg("getAuditLevel sql"+sql);
		return sql;
	}
	public static String getMinMarkssql(String auditLevel,String auditTemplate)
	{
		String sql = " select JTLL_MINIMUMPOINTS from JHA_TL_TEMPLATELEVELLINK " ;
		sql+=" where JTLL_TEMPLATEID=JTLL_TEMPLATEID and JTLL_AUDITLEVELID='"+ auditLevel +"' and JTLL_TEMPLATEID='" + auditTemplate + "'";
		return sql;
	}
	//changes by sriram 3
	
	/*
	 * public static String getcountjhStep(String flid) { String sql =
	 * " select count(*) from JHA_TL_AUDITTEAM where JHAT_KEYID NOT IN  ("; sql +=
	 * " SELECT JHAM_AUDITTEAMID FROM jha_tl_auditmst WHERE  JHAM_FLID='"
	 * +flid+"') order by JHAT_KEYID ";
	 * 
	 * CommonMessage.debugMsg("sql:::"+sql); return sql; }
	 */

	public static String getcountjhStep(String flid) {
	    String sql = " select count(*) from JHA_TL_AUDITTEAM where JHAT_KEYID NOT IN  (" +
	                 " SELECT JHAM_AUDITTEAMID FROM jha_tl_auditmst WHERE JHAM_FLID='" + flid + "')";
	    CommonMessage.debugMsg("sql:::" + sql);
	    return sql;
	}

	public static String getjhStepKeyIdsql(String machine)
	{
		String sql = " select JTSL_JHSTEPID  from JHA_TL_TEMPLATESTEPLINK ,JHA_TL_TEMPLATEMCHLINK";
			   sql +=	" where JTSL_TEMPLATEID=JTML_TEMPLATEID  and JTML_MACHINEID ='"+machine+"' " ;
		//if(CommonFunctions.isValidKeyId(machine))	   
		//	   sql += " and JTSL_TEMPLATEID= '"+ machine+"'";
		
		
		return sql;
	}
	public static String updateMachineMst(String jhStepId,String machineId)
	{
		String sql = " Update " + TableNames.TBL_GEN_TL_MACHINEMST + " SET MCHM_JHSTEP='"+jhStepId+"'";
			   sql +=" WHERE MCHM_KEYID='"+ machineId +"'";
		
		
		return sql;
	}

	public static String getjhAuditStepGrid(CommonFilter commonFilter) {
		StringBuffer sql  = new StringBuffer();
		sql.append(" SELECT DISTINCT JHSM_KEYID AS KEYID,JHSM_LEVELNO AS LEVELNO, JHSM_NAME AS NAME, ");
		sql.append(" DECODE (JHSM_KEYID,JTSL_JHSTEPID ,1,0) AS TICK, DECODE (JHSM_KEYID,JTSL_JHSTEPID ,1,0) AS SELECTVAL ");
		sql.append(" FROM GEN_TL_JHSTEPMST, JHA_TL_TEMPLATESTEPLINK "); 
		sql.append(" WHERE JHSM_KEYID = JTSL_JHSTEPID (+) AND JHSM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTSL_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"' "); 
		sql.append(" ORDER BY  SELECTVAL  DESC ");
		
		return sql.toString();
	}
 //changes by sriram 4
	public static String getjhAuditLevelGrid(CommonFilter commonFilter) {
	
		StringBuffer sql  = new StringBuffer();
		/*sql.append(" SELECT JHAT_KEYID AS KEYID, JHAT_CODE  AS CODE, JHAT_NAME  AS NAME, ");
		sql.append(" DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , SUM(JTLL_MINIMUMPOINTS) , '0'), "); 
		sql.append(" JHAT_LEVELNO AS LEVELNO	FROM JHA_TL_AUDITTEAM, JHA_TL_TEMPLATELEVELLINK ");
		sql.append(" WHERE JHAT_KEYID = JTLL_AUDITLEVELID (+) AND JHAT_ACTIVE = 'Y' "); 
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTLL_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"' ");
		sql.append(" GROUP BY JHAT_KEYID, JHAT_CODE, JHAT_NAME, JHAT_LEVELNO , JTLL_AUDITLEVELID "); 
		sql.append(" ORDER BY JHAT_LEVELNO ");*/
		/*
		 * if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId())){ sql.
		 * append(" SELECT JHAT_KEYID AS KEYID,DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , '1' , '') selectV,DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , '1' , '0') SelectValue, "
		 * ); sql.append(" JHAT_CODE  AS CODE, JHAT_NAME  AS NAME,  "); sql.
		 * append(" DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , SUM(JTLL_MINIMUMPOINTS) , '0'),   "
		 * ); sql.
		 * append(" JHAT_LEVELNO AS LEVELNO	FROM JHA_TL_AUDITTEAM, JHA_TL_TEMPLATELEVELLINK  "
		 * ); sql.
		 * append(" WHERE JHAT_KEYID = JTLL_AUDITLEVELID (+) AND JHAT_ACTIVE = 'Y' 	 ");
		 * if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
		 * sql.append(" AND JTLL_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"' "
		 * ); sql.
		 * append(" GROUP BY JHAT_KEYID, JHAT_CODE, JHAT_NAME, JHAT_LEVELNO , JTLL_AUDITLEVELID  "
		 * ); sql.append(" ORDER BY JHAT_LEVELNO  "); } else{ sql.
		 * append(" SELECT JHAT_KEYID AS KEYID,DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , '0' , '') selectV,DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , '0' , '0') SelectValue, "
		 * ); sql.append(" JHAT_CODE  AS CODE, JHAT_NAME  AS NAME,  "); //sql.
		 * append(" DECODE(JHAT_KEYID , JTLL_AUDITLEVELID , SUM(JTLL_MINIMUMPOINTS) , '0'),   "
		 * ); sql.append(" '0', "); sql.
		 * append(" JHAT_LEVELNO AS LEVELNO	FROM JHA_TL_AUDITTEAM, JHA_TL_TEMPLATELEVELLINK  "
		 * ); sql.
		 * append(" WHERE JHAT_KEYID = JTLL_AUDITLEVELID (+) AND JHAT_ACTIVE = 'Y' 	 ");
		 * sql.
		 * append(" GROUP BY JHAT_KEYID, JHAT_CODE, JHAT_NAME, JHAT_LEVELNO , JTLL_AUDITLEVELID  "
		 * ); sql.append(" ORDER BY JHAT_LEVELNO  "); }
		 */		
		if (UIUtils.isValidKeyId(commonFilter.getJhTemplateId())) {
		    sql.append(" SELECT ");
		    sql.append(" JHAT_KEYID AS KEYID, ");
		    sql.append(" CASE WHEN JHAT_KEYID = JTLL_AUDITLEVELID THEN '1' ELSE '' END AS selectV, ");
		    sql.append(" CASE WHEN JHAT_KEYID = JTLL_AUDITLEVELID THEN '1' ELSE '0' END AS SelectValue, ");
		    sql.append(" JHAT_CODE AS CODE, ");
		    sql.append(" JHAT_NAME AS NAME, ");
		    sql.append(" SUM(CASE WHEN JHAT_KEYID = JTLL_AUDITLEVELID THEN JTLL_MINIMUMPOINTS ELSE 0 END) AS MINPOINTS, ");
		    sql.append(" JHAT_LEVELNO AS LEVELNO ");
		    sql.append(" FROM JHA_TL_AUDITTEAM ");
		    sql.append(" LEFT JOIN JHA_TL_TEMPLATELEVELLINK ");
		    sql.append(" ON JHAT_KEYID = JTLL_AUDITLEVELID ");
		    sql.append(" AND JTLL_TEMPLATEID = '" + commonFilter.getJhTemplateId() + "' ");
		    sql.append(" WHERE JHAT_ACTIVE = 'Y' ");
		    sql.append(" GROUP BY JHAT_KEYID, JHAT_CODE, JHAT_NAME, JHAT_LEVELNO, JTLL_AUDITLEVELID ");
		    sql.append(" ORDER BY JHAT_LEVELNO ");
		} else {
		    sql.append(" SELECT ");
		    sql.append(" JHAT_KEYID AS KEYID, ");
		    sql.append(" CASE WHEN JHAT_KEYID = JTLL_AUDITLEVELID THEN '0' ELSE '' END AS selectV, ");
		    sql.append(" CASE WHEN JHAT_KEYID = JTLL_AUDITLEVELID THEN '0' ELSE '0' END AS SelectValue, ");
		    sql.append(" JHAT_CODE AS CODE, ");
		    sql.append(" JHAT_NAME AS NAME, ");
		    sql.append(" '0' AS MINPOINTS, ");
		    sql.append(" JHAT_LEVELNO AS LEVELNO ");
		    sql.append(" FROM JHA_TL_AUDITTEAM ");
		    sql.append(" LEFT JOIN JHA_TL_TEMPLATELEVELLINK ");
		    sql.append(" ON JHAT_KEYID = JTLL_AUDITLEVELID ");
		    sql.append(" WHERE JHAT_ACTIVE = 'Y' ");
		    sql.append(" GROUP BY JHAT_KEYID, JHAT_CODE, JHAT_NAME, JHAT_LEVELNO, JTLL_AUDITLEVELID ");
		    sql.append(" ORDER BY JHAT_LEVELNO ");
		}

		return sql.toString();
	}

	public static String recallValues(String templateId) {
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" SELECT * FROM "+TableNames.TBL_JHA_TL_AUDITPARAMETER+" WHERE 1=1 " );
		if(UIUtils.isValidKeyId(templateId))
			sql.append(" and JHAP_KEYID ='" + templateId + "'");
		
		return sql.toString();
	}

	public static String getjhAuditEquipmentGrid(CommonFilter commonFilter) {
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT DISTINCT MCHM_KEYID AS KEYID, FACT_NAME,  SECT_NAME, CELL_NAME,  ");
		sql.append(" MCHM_MACHINENO AS MACHINENO,MCHM_MACHINENAME AS MACHINENAME, ");  
		sql.append(" DECODE(JTML_MACHINEID, MCHM_KEYID , 1, 0) AS TICK, DECODE(JTML_MACHINEID, MCHM_KEYID , 1, 0) AS SELECTVAL  ");
		sql.append(" FROM GEN_TL_FACTORYMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, GEN_TL_MACHINEMST, ");
		if(UIUtils.isValidKeyId(commonFilter.getJtw()))
			sql.append("  GEN_TL_JHSTEPMST ,");
		sql.append(" JHA_TL_TEMPLATEMCHLINK WHERE MCHM_KEYID = JTML_MACHINEID(+) AND MCHM_CELLID = CELL_KEYID ");
		sql.append("AND CELL_SECTIONID = SECT_KEYID AND SECT_FACTORYID = FACT_KEYID AND MCHM_ACTIVE = 'Y' "); 
		//if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			//sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getFlid()+"'");
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"'");
		if(UIUtils.isValidKeyId(commonFilter.getJtw()))
			sql.append(" AND JHSM_KEYID (+) = MCHM_JHSTEP AND JHSM_LEVELNO <= '"+commonFilter.getJtw()+"' ");
		sql.append(" ORDER BY TICK DESC,MCHM_MACHINENO ");
		return sql.toString();
	}
	
	
	public static String getjhAuditEquipmentDefaultGrid(CommonFilter commonFilter) {
		
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT DISTINCT MCHM_KEYID AS KEYID, FACT_NAME,  SECT_NAME, CELL_NAME,  ");
		sql.append(" MCHM_MACHINENO AS MACHINENO,MCHM_MACHINENAME AS MACHINENAME, ");  
		sql.append(" DECODE(JTML_MACHINEID, MCHM_KEYID , 0, 0) AS TICK, DECODE(JTML_MACHINEID, MCHM_KEYID , 0, 0) AS SELECTVAL  ");
		sql.append(" FROM GEN_TL_FACTORYMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, GEN_TL_MACHINEMST, ");
		if(UIUtils.isValidKeyId(commonFilter.getJtw()))
			sql.append("  GEN_TL_JHSTEPMST ,");
		sql.append(" JHA_TL_TEMPLATEMCHLINK WHERE MCHM_KEYID = JTML_MACHINEID(+) AND MCHM_CELLID = CELL_KEYID ");
		sql.append("AND CELL_SECTIONID = SECT_KEYID AND SECT_FACTORYID = FACT_KEYID AND MCHM_ACTIVE = 'Y' "); 
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"'");
		if(UIUtils.isValidKeyId(commonFilter.getJtw()))
			sql.append(" AND JHSM_KEYID (+) = MCHM_JHSTEP AND JHSM_LEVELNO <= '"+commonFilter.getJtw()+"' ");
		sql.append(" ORDER BY TICK DESC,MCHM_MACHINENO ");
		return sql.toString();
	}
	
	public static String getjhAuditCellGrid(CommonFilter commonFilter) {
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT DISTINCT CELL_KEYID AS KEYID, FACT_NAME,  SECT_NAME, CELL_NAME,  ");
		sql.append(" '' AS MACHINENO,'' AS MACHINENAME, ");  
		sql.append(" DECODE(JTML_CELLID, CELL_KEYID , 1, 0) AS TICK, DECODE(JTML_CELLID, CELL_KEYID , 1, 0) AS SELECTVAL  ");
		sql.append(" FROM GEN_TL_FACTORYMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, ");		
		sql.append(" JHA_TL_TEMPLATEMCHLINK WHERE CELL_KEYID = JTML_CELLID(+) ");
		sql.append("AND CELL_SECTIONID = SECT_KEYID AND SECT_FACTORYID = FACT_KEYID AND CELL_ACTIVE = 'Y' "); 
		//if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			//sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getFlid()+"'");
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"'");
		
		sql.append(" ORDER BY TICK DESC,CELL_NAME ");
		return sql.toString();
	}
	
	public static String getjhAuditCellDefaultGrid(CommonFilter commonFilter) {
		
		StringBuffer sql  = new StringBuffer();
		
		sql.append("SELECT DISTINCT CELL_KEYID AS KEYID, FACT_NAME,  SECT_NAME, CELL_NAME,   ");
		sql.append(" '' AS MACHINENO,'' AS MACHINENAME, ");  
		sql.append(" DECODE(JTML_CELLID, CELL_KEYID , 0, 0) AS TICK, DECODE(JTML_CELLID, CELL_KEYID , 0, 0) AS SELECTVAL  ");
		sql.append(" FROM GEN_TL_FACTORYMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST,  ");
		sql.append(" JHA_TL_TEMPLATEMCHLINK WHERE CELL_KEYID = JTML_CELLID(+)  ");
		sql.append("AND CELL_SECTIONID = SECT_KEYID AND SECT_FACTORYID = FACT_KEYID AND CELL_ACTIVE = 'Y' "); 
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTML_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"'");
		
		sql.append(" ORDER BY TICK DESC,CELL_KEYID ");
		return sql.toString();
	}
	public static String getjhAuditStepDefaultGrid(CommonFilter commonFilter) {
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" SELECT DISTINCT JHSM_KEYID AS KEYID, JHSM_LEVELNO AS LEVELNO,JHSM_NAME AS NAME, ");
		sql.append(" DECODE (JHSM_KEYID,JTSL_JHSTEPID ,0,0) AS TICK, DECODE (JHSM_KEYID,JTSL_JHSTEPID ,0,0) AS SELECTVAL ");
		sql.append(" FROM GEN_TL_JHSTEPMST, JHA_TL_TEMPLATESTEPLINK "); 
		sql.append(" WHERE JHSM_KEYID = JTSL_JHSTEPID (+) AND JHSM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JTSL_TEMPLATEID (+) = '"+commonFilter.getJhTemplateId()+"' "); 
		sql.append(" ORDER BY  SELECTVAL  DESC ");
		
		return sql.toString();
	}

	public static String getPassSql(String jhamMachineid) {
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" SELECT COUNT(*) FROM  "+TableNames.TBL_JHA_TL_AUDITMST+","+TableNames.TBL_JHA_TL_AUDITTEAM );
		sql.append(" WHERE JHAM_AUDITTEAMID = JHAT_KEYID AND JHAM_MACHINEID='"+jhamMachineid+"' ");
		sql.append(" AND JHAM_TOTALPOINTS < JHAT_MINIMUMPOINTS ");
		return sql.toString();
	}
	public static String getjhAuditParamterGrid(CommonFilter commonFilter) {
		
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT JAUT_KEYID, JAUT_REVIEWPTSLNO ,JAUT_PARAMETERNAME AS PARAMNAME,JAUT_CRITERIASLNO, JAUT_PARAMETERDESCRIPTION AS PARAMDESC, JAUT_EVIDENCE AS EVIDENCE,JAUT_MAXIMUMPOINTS AS MAXPOINT,'' AS DELET");
		sql.append(" FROM ( ");
		sql.append(" SELECT JAUT_KEYID, JAUT_REVIEWPTSLNO, JAUT_PARAMETERNAME,JAUT_CRITERIASLNO,JAUT_PARAMETERDESCRIPTION,JAUT_EVIDENCE, JAUT_MAXIMUMPOINTS ");
		sql.append(" FROM JHA_TL_AUDITTEMPLATE ");
		sql.append(" WHERE 1=1  ");
		//if(UIUtils.isValidKeyId(commonFilter.getJhTemplateId()))
			sql.append(" AND JAUT_MASTERID = '"+commonFilter.getJhTemplateId()+"' "); 
		sql.append(" )  ");
		sql.append("  ORDER BY  JAUT_REVIEWPTSLNO, JAUT_CRITERIASLNO ");
		
		return sql.toString();
	}
	
	public static String getAuditLevelCurrentSql(CommonFilter commonFilter){
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT JHSM_KEYID FROM GEN_TL_JHSTEPMST WHERE JHSM_ACTIVE='Y' AND ");
		sql.append(" JHSM_CODE IN ( ");
		sql.append(" SELECT MIN(JHSM_CODE ) FROM GEN_TL_JHSTEPMST WHERE JHSM_ACTIVE='Y' ");
		sql.append(" AND JHSM_KEYID NOT IN ( ");
		sql.append(" SELECT JHAP_AUDITLEVEL FROM JHA_TL_AUDITMST,JHA_TL_AUDITPARAMETER  ");		
		sql.append(" WHERE JHAM_AUDITTEAMID=JHAP_KEYID AND JHAM_FLID='"+commonFilter.getJhTemplateId()+"'  AND JHAM_STATUS='P'  ");
		sql.append("  )) ");
		
		
		return sql.toString();
	}
	
	public static String getSelectCntSql(String flId){
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" SELECT Count(*) from  JHA_TL_AUDITMST  ");		
		sql.append(" WHERE  JHAM_FLID='"+flId+"' ");
		sql.append("  ");	
		
		return sql.toString();
	}

	public static String getJhLeader(String flid) {
		// TODO Auto-generated method stub
		StringBuffer sql  = new StringBuffer();
		
		sql.append(" select EMPM_NAME from gen_Tl_fnlnroleteam,gen_tl_employeemst   ");		
		sql.append(" where EMPM_KEYID=FRT_EMPM_KEYID and FRT_ROLE_KEYID in (select ROLE_KEYID from adm_tl_rolemst where role_name='JH LEADER') ");
		sql.append(" and FRT_FNLN_KEYID='"+flid+"' and rownum=1  ");	
		
		return sql.toString();
	}
	
}

