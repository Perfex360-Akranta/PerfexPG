package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMommstSql {

	public static final String TBL_GEN_TL_MOMMST = "GEN_TL_MOMMST";  
	public static final String TBL_GEN_TL_MOMDTL = "GEN_TL_MOMDTL";
	public static final String TBL_GEN_TL_MOMATTENDANCE = "GEN_TL_MOMATTENDANCE"; 

	TableFieldType [] momsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, ismeetinghappen, safetytalk, remarks
		, meetingno, meetingtitle, meetingtype, pillarid, ismessageboard, agenda, others, momsPillargroup
		,refdocid, refdoctype,ismail,tempefield1,tempefield2,tempefield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMomsDbFields() {
		return momsDbFields;
	}

	public GenTlMommstSql()
	{
		momsDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i < 25; i++)
		{	
			momsDbFields[ i ] = new TableFieldType();
		}
		momsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOMS_KEYID";
		momsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MOMS_FLID";
		momsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MOMS_DATE";
		momsDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		momsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "MOMS_SHIFTID";
		momsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.ismeetinghappen.ordinal() ].fieldName = "MOMS_ISMEETINGHAPPEN";
		momsDbFields[ tableFldConstants.ismeetinghappen.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.safetytalk.ordinal() ].fieldName = "MOMS_SAFETYTALK";
		momsDbFields[ tableFldConstants.safetytalk.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MOMS_REMARKS";
		momsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		momsDbFields[ tableFldConstants.meetingno.ordinal() ].fieldName = "MOMS_MEETINGNO";
		momsDbFields[ tableFldConstants.meetingno.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.meetingtitle.ordinal() ].fieldName = "MOMS_MEETINGTITLE";
		momsDbFields[ tableFldConstants.meetingtitle.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.meetingtype.ordinal() ].fieldName = "MOMS_MEETINGTYPE";
		momsDbFields[ tableFldConstants.meetingtype.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "MOMS_PILLARID";
		momsDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.ismessageboard.ordinal() ].fieldName = "MOMS_ISMESSAGEBOARD";
		momsDbFields[ tableFldConstants.ismessageboard.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.agenda.ordinal() ].fieldName = "MOMS_AGENDA";
		momsDbFields[ tableFldConstants.agenda.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.others.ordinal() ].fieldName = "MOMS_OTHERS";
		momsDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.momsPillargroup.ordinal() ].fieldName = "MOMS_PILLARGROUP";
		momsDbFields[ tableFldConstants.momsPillargroup.ordinal() ].fieldType = 'V';
		
		momsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "MOMS_REFDOCID";
		momsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "MOMS_REFDOCTYPE";
		momsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';
		
		momsDbFields[ tableFldConstants.ismail.ordinal() ].fieldName = "MOMS_ISMAILTRIG";
		momsDbFields[ tableFldConstants.ismail.ordinal() ].fieldType = 'C';
		
		momsDbFields[ tableFldConstants.tempefield1.ordinal() ].fieldName = "MOMS_TEMPFIELD1";
		momsDbFields[ tableFldConstants.tempefield1.ordinal() ].fieldType = 'C';
		
		momsDbFields[ tableFldConstants.tempefield2.ordinal() ].fieldName = "MOMS_TEMPFIELD2";
		momsDbFields[ tableFldConstants.tempefield2.ordinal() ].fieldType = 'C';
		
		momsDbFields[ tableFldConstants.tempefield3.ordinal() ].fieldName = "MOMS_TEMPFIELD3";
		momsDbFields[ tableFldConstants.tempefield3.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOMS_ACTIVE";
		momsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOMS_CREATEDBY";
		momsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOMS_CREATEDON";
		momsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		momsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOMS_MODIFIEDON";
		momsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MOMMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MOMMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MOMMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getMomsFrmDataSql() 
	{
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_GEN_TL_MOMMST + " where MOMS_KEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public static String momRecallGrid() {
		// TODO Auto-generated method stub String shift, String mstDate, String flid, String type
		//CommonMessage.debugMsg(" shift :: "+shift+" mstDate "+mstDate+" flid "+flid+" type "+type);
		String sql;
		//sql="SELECT * from " + TBL_GEN_TL_MOMMST + " where moms_shiftid= ? and moms_date=? and moms_flid=? and moms_meetingtype=?";
		sql="SELECT * from " + TBL_GEN_TL_MOMMST + " where moms_date= ?";
		CommonMessage.debugMsg("Sql: Checking for Recall Now :: "+sql);
		return sql;
	}

	public static String getMomsFrmDataSql1() {
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from "+TBL_GEN_TL_MOMDTL+" where MOMD_MOMS_KEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public static String mommstrecalngGrid(String shift, String mstDate,
			String flid, String type, String pillarid) {
		
		CommonMessage.debugMsg("Entered into mommstrecalngGrid");
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("  select moms_meetingno,moms_ismeetinghappen,moms_safetytalk,moms_remarks,moms_meetingtitle,moms_meetingtype,moms_agenda,moms_pillarid,moms_pillargroup");
		sql.append("  from gen_tl_mommst where moms_date='"+mstDate+"' and moms_flid='"+flid+"' ");
		
		CommonMessage.debugMsg(shift+" mommstrecalngGrid "+type);
		
		if(type.equals("JH"))
			sql.append(" and moms_shiftid='"+shift+"' and moms_meetingtype='J' ");
		else if(type.equals("Dmt"))
			sql.append(" and moms_meetingtype='D' ");
		else if(type.equals("Production"))
			sql.append(" and moms_meetingtype='PD' ");
		else if(type.equals("Others"))
			sql.append(" and moms_meetingtype='O' ");
		else if (type.equals("Pillar"))
			//sql.append(" and moms_meetingtype='P' AND moms_pillarid = '"+pillarid +"'");
			sql.append(" and moms_meetingtype='P' ");
		      
		else
			sql.append(" and moms_meetingtype='"+type+"' ");
		CommonMessage.debugMsg("Entered Query "+sql.toString());
		
		CommonMessage.debugMsg(" Inside Sql File :: 1234 :: Recalling "+sql);
			   
	    return sql.toString();
	}
//SWETHA CHANGED
	public static String momGrid(CommonFilter commonFilter, String KeyId, String flid, String momdate, String shift, String type, String pillarid) {
	    CommonMessage.debugMsg(" In Dao Impl " + momdate + " shift :: " + shift + " flid :: " + flid);

	    StringBuilder sql = new StringBuilder();
	    sql.append(" SELECT momd_keyid, ");
	    sql.append(" CASE ");
	    sql.append("   WHEN momtype = 'OPL' THEN 'OPL' ");
	    sql.append("   WHEN momtype = 'PM' THEN 'PM' ");
	    sql.append("   WHEN momtype = 'OTH' THEN 'OTHERS' ");
	    sql.append("   WHEN momtype = 'MOD' THEN 'MODIFICATION' ");
	    sql.append("   WHEN momtype = 'CLT' THEN 'CLTI' ");
	    sql.append("   ELSE momtype ");
	    sql.append(" END, ");
	    sql.append(" TPMP_CODE, momdetails, kink_keyid, kink_indicatorname, '', MOMD_ACTIONPLAN_ID, '', momremarks, mompillar, momtype, momdetails, momremarks ");
	    sql.append(" FROM GEN_TL_TPMPILLARMST ");
	    sql.append(" RIGHT JOIN (");
	    sql.append("   SELECT momd_keyid, momd_discussion_type as momtype, momd_pillar as mompillar, momd_discussion_details as momdetails, ");
	    sql.append("          kink_keyid, KINK_INDICATORNAME, '', APLM_KEYID as MOMD_ACTIONPLAN_ID, '', momd_remarks as momremarks ");
	    sql.append("   FROM gen_tl_mommst ");
	    sql.append("   INNER JOIN gen_tl_momdtl ON moms_keyid = momd_moms_keyid ");
	    sql.append("   LEFT JOIN gen_tl_actionplanmst ON MomS_Keyid = aplm_masterrefid AND Momd_Keyid = aplm_detailrefid ");
	    sql.append("   LEFT JOIN ( ");
	    sql.append("     SELECT STRING_AGG(kink_keyid::text, ',' ORDER BY kink_keyid) as kink_keyid, ");
	    sql.append("            STRING_AGG(kink_indicatorname, ',' ORDER BY kink_indicatorname) as kink_indicatorname, ");
	    sql.append("            MAX(MOKP_MOMD_KEYID) as MOKP_MOMD_KEYID ");
	    sql.append("     FROM gen_tl_mom_kpi_link ");
	    sql.append("     LEFT JOIN kpi_tl_indicator ON mokp_kink_keyid = kink_keyid ");
	    sql.append("     GROUP BY MOKP_MOMD_KEYID ");
	    sql.append("   ) kpi_agg ON momd_keyid = MOKP_MOMD_KEYID ");

	    if (UIUtils.isValidKeyId(flid) && UIUtils.isValidKeyId(momdate)) {
	        sql.append("   WHERE MOMD_MOMS_KEYID IN ( ");
	        sql.append("     SELECT moms_keyid FROM gen_tl_mommst ");
	        sql.append("     WHERE moms_flid = '" + flid + "' ");
	        sql.append("       AND moms_date = '" + momdate + "' ");
	        
	        if (type.equals("JH")) {
	            sql.append("       AND moms_shiftid = '" + shift + "' AND moms_meetingtype = 'J' ");
	        } else if (type.equals("Dmt")) {
	            sql.append("       AND moms_meetingtype = 'D' ");
	        } else if (type.equals("Production")) {
	            sql.append("       AND moms_meetingtype = 'PD' ");
	        } else if (type.equals("Others")) {
	            sql.append("       AND moms_meetingtype = 'O' ");
	        } else if (type.equals("Pillar")) {
	            //sql.append("AND moms_pillarid = '" + pillarid + "' AND moms_meetingtype = 'P' ");
	        	sql.append("AND moms_meetingtype = 'P' ");
	        } else {
	            sql.append("       AND moms_meetingtype = '" + type + "' ");
	        }
	        sql.append("   ) ");
	    } else {
	        sql.append("   WHERE MOMD_MOMS_KEYID = '" + KeyId + "' ");
	    }

	    sql.append("   ORDER BY MOMD_KEYID ASC ");
	    sql.append(" ) sub ON TPMP_KEYID = mompillar ");

	    CommonMessage.debugMsg(" Inside Sql File :: 1234 " + sql);
	    CommonMessage.debugMsg(" In GenTlMommstSql :: Inside :: Sql Files " + sql);
	    
	    return sql.toString();
	}

	public static String getMomsFrmDataSql12() 
	{
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from "+TBL_GEN_TL_MOMATTENDANCE+" where MOMA_MOMS_KEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public static String momGridVidtor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type, String pillarid, String recall) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT VISI_KEYID,VISI_VISITORNAME,VISI_PURPOSE,'' FROM GEN_TL_VISITORS ,GEN_TL_MOMMST WHERE  VISI_MOMS_KEYID = MOMS_KEYID ");
		if(UIUtils.isValidKeyId(MasterKeyid) && ! UIUtils.isValidKeyId(recall))
		      sql.append("AND VISI_MOMS_KEYID = '"+MasterKeyid+"'");
		else{
			  sql.append("AND VISI_MOMS_KEYID in (select moms_keyid from gen_tl_mommst where moms_flid='"+flid+"' "); 
			  sql.append("and moms_date='"+date+"' ");
			  
			  if(type.equals("JH"))
					sql.append(" and moms_shiftid='"+shift+"' and moms_meetingtype='J' ");
			  else if(type.equals("Dmt"))
				sql.append(" and moms_meetingtype='D' ");
			  else if(type.equals("Production"))
				sql.append(" and moms_meetingtype='PD' ");
			  else if(type.equals("Others"))
				sql.append(" and moms_meetingtype='O' ");
			  else if (type.equals("Pillar"))
				sql.append(" and moms_pillarid='"+pillarid+"' and moms_meetingtype='P' ");
			  else
				sql.append(" and moms_meetingtype='"+type+"' ");

			  
			  sql.append(" ) ");
		}
			
		return sql.toString();
		
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		//select ROLE_NAME from adm_tl_rolemst where ROLE_KEYID=(select frt_role_keyid from gen_tl_fnlnroleteam where frt_empm_keyid='EMP03337');
		String sql= " select ROLE_NAME from adm_tl_rolemst ";
		sql+= "  where ROLE_KEYID in (select frt_role_keyid from gen_tl_fnlnroleteam where frt_empm_keyid='"+keyid+"') " ;
		//sql+= " where crpp_KEYID='"+keyid+"'";
		CommonMessage.debugMsg(" Inside 1234567890"+sql);
		return sql;	
	    
	    
	}

	public static String selectRoleData(String keyid) {  // keyid
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		/*
		 * sql.
		 * append("  select LISTAGG(role_keyid,',') within group(order by frt_role_keyid) ROLE,LISTAGG(role_name,',') within group(order by frt_role_keyid) ROLE "
		 * ); sql.append("  from gen_tl_fnlnroleteam , adm_tl_rolemst ");
		 * sql.append("  where role_keyid = frt_role_keyid and frt_empm_keyid='"
		 * +keyid+"' ");
		 */
		//Swetha Changes
		sql.append("SELECT ");
		sql.append("STRING_AGG(role_keyid, ',' ORDER BY frt_role_keyid) AS role, ");
		sql.append("STRING_AGG(role_name, ',' ORDER BY frt_role_keyid) AS role_name ");
		sql.append("FROM gen_tl_fnlnroleteam frt ");
		sql.append("JOIN adm_tl_rolemst r ON r.role_keyid = frt.frt_role_keyid ");
		sql.append("WHERE frt.frt_empm_keyid = '" + keyid + "' ");
		
		return sql.toString();
	
	}

	public static String getMomAttendanceEmpMailIdSql() {  // keyid
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		/*
		sql.append("  select EMPM_NAME, EMPM_EMAIL from gen_tl_employeemst, ADM_tl_rolemst,gen_mv_flidhierarchy,gen_tl_momattendance ,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST ");
		sql.append("  WHERE empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID = ROLE_KEYID(+) AND moma_employeeid (+) = empm_keyid ");
		sql.append(" AND FLID = FRT_FNLN_KEYID AND MOMA_MOMS_KEYID = MOMS_KEYID (+) AND ( INSTR (parentflids || '-' || flid, ? ) >0  ) AND MOMA_MOMS_KEYID(+)= ?  ");
		 */
		//MODIFY ON 31-OCT-14 DB
		
//		sql.append(" select EMPM_NAME, EMPM_EMAIL FROM gen_tl_employeemst, gen_tl_momattendance ,GEN_TL_MOMMST ");
//		sql.append(" WHERE moma_employeeid (+) = empm_keyid AND MOMA_MOMS_KEYID = MOMS_KEYID (+)  ");
//		sql.append(" AND MOMA_MOMS_KEYID = ? ");
//		// Modified on 10-Feb-15 for mail to be send based on configuration
//		sql.append("  and empm_keyid in ( select empm_keyid from gen_tl_employeemst where empm_enableemail='Y') ");
//		
		
		sql.append(" SELECT empm_name, empm_email FROM gen_tl_employeemst ");
		sql.append(" LEFT JOIN gen_tl_momattendance ON moma_employeeid = empm_keyid ");
		sql.append(" LEFT JOIN gen_tl_mommst ON moma_moms_keyid = moms_keyid ");
		sql.append(" WHERE moma_moms_keyid = ?");
		sql.append(" AND empm_keyid IN (SELECT empm_keyid FROM gen_tl_employeemst WHERE empm_enableemail = 'Y') ");
		CommonMessage.debugMsg("getMomAttendanceEmpMailIdSql=="+sql.toString());
		CommonMessage.debugMsg("GET MOM MAIL "+sql.toString());
		
		return sql.toString();
	
	}

	/*
	 * public static String selectAgendaData(String flid, String momdate) { // TODO
	 * Auto-generated method stub StringBuilder sql = new StringBuilder(); sql.
	 * append("  SELECT DISTINCT ACHM_ACTIVITY Activity   FROM  GEN_TL_JHACTIVITYCHARTMST,GEN_TL_JHACTIVITYCHARTDTL , GEN_MV_FLIDHIERARCHY"
	 * ); //sql.
	 * append("  WHERE  ACHM_KEYID = JACD_ACHM_KEYID(+) AND ACHM_FREQUENCY = DECODE(IS_NUMERIC (ACHM_FREQUENCY) , 'Y', TO_CHAR (to_date('"
	 * +momdate+"'), 'D') ,ACHM_FREQUENCY ) "); sql.
	 * append("  WHERE  ACHM_KEYID = JACD_ACHM_KEYID(+)AND ACHM_FLID=FLID AND ACHM_FREQUENCY = DECODE(IS_NUMERIC (ACHM_FREQUENCY) , 'Y', TO_CHAR (to_date('"
	 * +momdate+"'), 'D') ,ACHM_FREQUENCY ) "); //sql.
	 * append("  AND ACHM_FREQUENCY <> 'D' AND INSTR(PARENTFLIDS ||'/'||FLID,'"
	 * +flid+"')>0 AND INSTR(PARENTFLIDS ||'/'||FLID,ACHM_FLID)>0 and \"LEVEL\" <7 "
	 * );//AND JACD_FLID='"+locationId+"'
	 * sql.append("  AND ACHM_FREQUENCY <> 'D' AND FLID='"
	 * +flid+"' and \"LEVEL\" <7 ");//AND JACD_FLID='"+locationId+"'
	 * 
	 * CommonMessage.debugMsg(" Checking Aganda Data "+sql.toString()); return
	 * sql.toString(); }
	 */
	//QUERY CHANGE BY SWETHA TO POSTGRE
	public static String selectAgendaData(String flid, String momdate) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT DISTINCT ACHM_ACTIVITY Activity   FROM  GEN_TL_JHACTIVITYCHARTMST,GEN_TL_JHACTIVITYCHARTDTL , GEN_MV_FLIDHIERARCHY");
		//sql.append("  WHERE  ACHM_KEYID = JACD_ACHM_KEYID(+) AND ACHM_FREQUENCY = DECODE(IS_NUMERIC (ACHM_FREQUENCY) , 'Y', TO_CHAR (to_date('"+momdate+"'), 'D') ,ACHM_FREQUENCY ) ");
		sql.append("  WHERE  ACHM_KEYID = JACD_ACHM_KEYID(+)AND ACHM_FLID=FLID AND ACHM_FREQUENCY = DECODE(IS_NUMERIC (ACHM_FREQUENCY) , 'Y', TO_CHAR (to_date('"+momdate+"'), 'D') ,ACHM_FREQUENCY ) ");
		//sql.append("  AND ACHM_FREQUENCY <> 'D' AND INSTR(PARENTFLIDS ||'/'||FLID,'"+flid+"')>0 AND INSTR(PARENTFLIDS ||'/'||FLID,ACHM_FLID)>0 and \"LEVEL\" <7 ");//AND JACD_FLID='"+locationId+"'
		sql.append("  AND ACHM_FREQUENCY <> 'D' AND FLID='"+flid+"' and \"LEVEL\" <7 ");//AND JACD_FLID='"+locationId+"'
	
		CommonMessage.debugMsg(" Checking Aganda Data "+sql.toString());
	    return sql.toString();
	} 

	public static String selectgetMomReleatedFileManager(String momKeyId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select dmdm_filename,dmdm_path from DCM_TL_DOCUMENTMANAGER where dmdm_refdocno='"+momKeyId+"' ");
		
		CommonMessage.debugMsg(" Checking FileManager Data "+sql.toString());
		return sql.toString();
	}
	//Swetha Changed Query for Simplified MOM - 17 October
	public static String NewmomGrid(CommonFilter commonFilter,String KeyId, String flid, String momdate, String shift, String type, String pillarid)
	{
		CommonMessage.debugMsg(" In Dao Impl "+momdate+" shift :: "+shift+" flid :: "+flid);
		StringBuilder sql = new StringBuilder();
		  sql.append(" SELECT momd_keyid, ");
		    sql.append(" CASE momtype WHEN 'OPL' THEN 'OPL' WHEN 'PM' THEN 'PM' WHEN 'OTH' THEN 'OTHERS' WHEN 'MOD' THEN 'MODIFICATION' WHEN 'CLT' THEN 'CLTI' END,");
		    sql.append(" TPMP_CODE, momdetails, kink_keyid, kink_indicatorname, '', ApldActionplan, ApldStatus, '', ApldTargetdate,");
		    sql.append(" cmbSdadResponsibility, Responsibiltyby, MOMD_ACTIONPLAN_ID, APLD_KEYID, '', momremarks, mompillar, momtype");
		    sql.append(" FROM (");
		    sql.append(" SELECT momd_keyid, momd_discussion_type momtype, momd_pillar mompillar,");
		    sql.append(" momd_discussion_details momdetails, kink_keyid, KINK_INDICATORNAME,");
		    sql.append(" APLD_ACTIONPLAN ApldActionplan, CASE APLD_STATUS WHEN 'P' THEN 'PENDING' WHEN 'C' THEN 'COMPLETED' END ApldStatus,");
		    sql.append(" TO_CHAR(APLD_TARGETDATE, 'DD-MON-YYYY') ApldTargetdate,");
		    sql.append(" EMPM_NAME || '-' || EMPM_CODE cmbSdadResponsibility,");
		    sql.append(" APLD_RESPONSIBILITY Responsibiltyby,");
		    sql.append(" APLM_KEYID MOMD_ACTIONPLAN_ID, APLD_KEYID APLD_KEYID, momd_remarks momremarks");
		    sql.append(" FROM gen_tl_mommst");
		    sql.append(" INNER JOIN gen_tl_momdtl ON moms_keyid = momd_moms_keyid");
		    sql.append(" LEFT JOIN gen_tl_actionplanmst ON MomS_Keyid = aplm_masterrefid AND Momd_Keyid = aplm_detailrefid");
		    sql.append(" LEFT JOIN gen_tl_actionplandtl ON apld_aplm_keyid = aplm_keyid");
		    sql.append(" LEFT JOIN gen_tl_employeemst ON EMPM_KEYID = APLD_RESPONSIBILITY");
		    sql.append(" LEFT JOIN (");
		    sql.append(" SELECT STRING_AGG(kink_keyid::text, ',' ORDER BY kink_keyid) kink_keyid,");
		    sql.append(" STRING_AGG(kink_indicatorname, ',' ORDER BY kink_indicatorname) kink_indicatorname,");
		    sql.append(" MAX(MOKP_MOMD_KEYID) MOKP_MOMD_KEYID");
		    sql.append(" FROM gen_tl_mom_kpi_link");
		    sql.append(" LEFT JOIN kpi_tl_indicator ON mokp_kink_keyid = kink_keyid");
		    sql.append(" GROUP BY MOKP_MOMD_KEYID");
		    sql.append(" ) AS kpi_agg ON momd_keyid = MOKP_MOMD_KEYID");
		    sql.append(" WHERE 1 = 1");

		    if(UIUtils.isValidKeyId(flid) && UIUtils.isValidKeyId(momdate)) {
		        sql.append(" AND MOMD_MOMS_KEYID IN (SELECT moms_keyid FROM gen_tl_mommst WHERE moms_flid = '" + flid + "'");
		        sql.append(" AND moms_date = '" + momdate + "'");
		        
		        if(type.equals("JH"))
		            sql.append(" AND moms_shiftid = '" + shift + "' AND moms_meetingtype = 'J'");
		        else if(type.equals("Dmt"))
		            sql.append(" AND moms_meetingtype = 'D'");
		        else if(type.equals("Production"))
		            sql.append(" AND moms_meetingtype = 'PD'");
		        else if(type.equals("Others"))
		            sql.append(" AND moms_meetingtype = 'O'");
		        else if(type.equals("Pillar"))
		            sql.append(" AND moms_meetingtype = 'P'");
		        else
		            sql.append(" AND moms_meetingtype = '" + type + "'");
		        
		        sql.append(" )");
		    }
		    else {
		        sql.append(" AND MOMD_MOMS_KEYID = '" + KeyId + "'");
		    }
		    
		    sql.append(" ORDER BY momd_keyid");
		    sql.append(" ) AS subquery");
		    sql.append(" LEFT JOIN GEN_TL_TPMPILLARMST ON TPMP_KEYID = mompillar");
		    
	    CommonMessage.debugMsg(" Inside Sql File :: 1234 "+sql);   
	    CommonMessage.debugMsg(" In GenTlMommstSql :: Inside :: Sql FIles "+sql);
	    return sql.toString();
		
	}

}

