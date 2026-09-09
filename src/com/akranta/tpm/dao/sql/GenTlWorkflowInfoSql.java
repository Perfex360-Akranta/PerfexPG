package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlWorkflowInfoSql{
	private DBActionTemplate dbActionTemplate; 
	public GenTlWorkflowInfoSql(DBActionTemplate dbActionTemplate) {
		// TODO Auto-generated constructor stub
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public static final String TBL_GEN_TL_WORKFLOW_INFO = "GEN_TL_WORKFLOW_INFO";  

	TableFieldType [] wrinDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wrml_keyid, ref_id, ref_type, role_id, status, employee_id
		, date, remarks, wrkd_keyid, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWrinDbFields() {
		return wrinDbFields;
	}

	public GenTlWorkflowInfoSql()
	{
		wrinDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			wrinDbFields[ i ] = new TableFieldType();
		}
		wrinDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WRIN_KEYID";
		wrinDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.wrml_keyid.ordinal() ].fieldName = "WRIN_WRML_KEYID";
		wrinDbFields[ tableFldConstants.wrml_keyid.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.ref_id.ordinal() ].fieldName = "WRIN_REF_ID";
		wrinDbFields[ tableFldConstants.ref_id.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.ref_type.ordinal() ].fieldName = "WRIN_REF_TYPE";
		wrinDbFields[ tableFldConstants.ref_type.ordinal() ].fieldType = 'C';

		wrinDbFields[ tableFldConstants.role_id.ordinal() ].fieldName = "WRIN_ROLE_ID";
		wrinDbFields[ tableFldConstants.role_id.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WRIN_STATUS";
		wrinDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		wrinDbFields[ tableFldConstants.employee_id.ordinal() ].fieldName = "WRIN_EMPLOYEE_ID";
		wrinDbFields[ tableFldConstants.employee_id.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WRIN_DATE";
		wrinDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		wrinDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WRIN_REMARKS";
		wrinDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.wrkd_keyid.ordinal() ].fieldName = "WRIN_WRKD_KEYID";
		wrinDbFields[ tableFldConstants.wrkd_keyid.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WRIN_TEMPFIELD2";
		wrinDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WRIN_TEMPFIELD3";
		wrinDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "WRIN_TEMPFIELD4";
		wrinDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "WRIN_TEMPFIELD5";
		wrinDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WRIN_CREATEDBY";
		wrinDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wrinDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WRIN_CREATEDON";
		wrinDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wrinDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WRIN_MODIFIEDON";
		wrinDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_WORKFLOW_INFO, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_WORKFLOW_INFO, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_WORKFLOW_INFO ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getWorkFlowTransSql(String transtype,String kznkeyid,String transCode,String empId,Character enable,String status){
		StringBuilder sql = new StringBuilder();
		CommonMessage.debugMsg("The transCode:::"+transCode);
	
		String localTransCode;
		
		if(transCode.equals("PRODE") || transCode.equals("PROME")  || transCode.equals("PROAN")  
				|| transCode.equals("PROIM")  || transCode.equals("PROCO")  || transCode.equals("PROCL"))
		{
			localTransCode = "DMAIC";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}
		else
		{
			localTransCode = "OTHERS";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}	
			
		sql.append(" SELECT DISTINCT wrin_keyid, wrkd_keyid, wrml_keyid, empm_keyid :: varchar, ");
				

		if( enable != null && enable == 'N')
			sql.append(" 'N' as enabl, ");
		else if(localTransCode.equals("DMAIC")){		
			//sql.append(" decode(enabl,'Y',case when role_keyid = ? or role_keyid = 'AROL0059' then DECODE(empm_keyid,? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) else 'N' end , enabl) enabl,");
			String strQry = " DECODE(empm_keyid, '"+empId+"' ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			String strQry2 = " DECODE(empm_keyid, ? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			sql.append(" decode(enabl,'Y', decode( role_keyid , 'AROL0059', "+ strQry + ", ");
			sql.append(" 	CASE GREATEST(INSTR( ? ,role_keyid),0) WHEN 0 THEN 'N' else " + strQry2 + " end ) , enabl) enabl,"); //modified by babu
			
		}
		else {
			sql.append("  CASE ");
			sql.append(" WHEN enabl = 'Y' THEN ");
			sql.append("  CASE ");
			sql.append(" WHEN GREATEST(POSITION( trim(?) IN trim(role_keyid)), 0) = 0 THEN 'N' ");
			sql.append(" ELSE ");
			sql.append(" CASE ");
			sql.append(" WHEN trim(empm_keyid) = TRIM(?) THEN ");
			sql.append(" CASE  ");
			sql.append(" WHEN COALESCE(prevstat, 'A') IN ('A', 'Submitted') THEN 'Y' ");
			sql.append(" WHEN role_level = COALESCE(prevlevl, role_level) THEN ");
			sql.append(" CASE WHEN role_keyid = prevroleid THEN 'Y' ELSE 'N' END ");
			sql.append(" ELSE 'N' ");
			sql.append(" END ");
			sql.append(" ELSE 'N' ");
			sql.append(" END ");
			sql.append(" END ");
			sql.append(" ELSE enabl ");
			sql.append(" END :: varchar AS enabl,");
 
				}
		
		sql.append(" Role_keyid :: varchar, ");
		sql.append("  ''  :: varchar  AS editrow, ");
		sql.append("  ''  :: varchar  AS saverow, ");
		sql.append(" role_name :: varchar, ");
		sql.append(" empm_name :: varchar, ");
		sql.append(" CASE currentstat ");
		sql.append(" WHEN 'A' THEN 'Accepted' ");
		sql.append(" WHEN 'R' THEN 'Rejected' ");
		sql.append(" WHEN 'E' THEN 'Rework' ");
		sql.append(" ELSE currentstat ");
		sql.append(" END :: varchar AS currentstat, ");
		sql.append(" TO_CHAR(wrin_date, 'DD-Mon-YYYY') :: date AS wrin_date, ");
		sql.append(" wrin_remarks, ");
		sql.append(" dataorder :: numeric, ");
		sql.append(" role_level ");
		sql.append(" FROM ( ");
		sql.append("    SELECT ");
		sql.append(" wrin_keyid, ");
		sql.append(" wrkd_keyid, ");
		sql.append(" wrml_keyid, ");
		sql.append("  empm_keyid, ");
		sql.append("  enabl, ");
		sql.append("  role_keyid, ");
		sql.append(" LAG(COALESCE(currentstat, 'Pending'), 1) OVER (ORDER BY role_level DESC) AS prevstat, ");
		sql.append("  '' AS editrow, ");
		sql.append(" '' AS saverow, ");
		sql.append(" role_name, ");
		sql.append(" empm_name, ");
		sql.append(" currentstat, ");
		sql.append(" wrin_date, ");
		sql.append(" wrin_remarks, ");
		sql.append(" role_level, ");
		sql.append(" LAG(role_level, 1) OVER (ORDER BY role_level DESC) AS prevlevl, ");
		sql.append(" LAG(role_keyid, 1) OVER (ORDER BY role_level DESC) AS prevroleid, ");
		sql.append(" dataorder ");
		sql.append("    FROM ( ");
	
		if(localTransCode.equals("DMAIC")){	
			sql.append(" SELECT wrin_keyid,wrkd_keyid, wrml_keyid, empm_keyid,");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl ");
			else	
				sql.append(" DECODE (KZPM_CREATEDBY,'"+empId+"', decode(currentstat,'Pending', 'Y','E','Y','N'),'N') enabl ");
			//sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework') currentstat, ");
			sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework','Pending') currentstat, ");
			
			sql.append(" wrin_date,wrin_remarks,1 dataorder,role_level FROM ( ");
			sql.append(" SELECT   wrin_keyid, '' wrkd_keyid,'' wrml_keyid, empm_keyid,wrin_status, ");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl, ");
			else	
				sql.append(" DECODE (NVL (wrin_status, 'Pending'),'Pending','Y','E', 'Y', 'N') enabl,");
			//sql.append("        DECODE (empm_keyid, WRIN_EMPLOYEE_ID, decode(NVL (wrin_status, 'Pending'),'Pending', 'Y', 'N'),'N') enabl, ");
			sql.append("  role_keyid,lead(nvl(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat, ");
			sql.append("  '' editrow, '' saverow, role_name, empm_name, NVL (wrin_status, 'Pending') currentstat, wrin_date, ");
			sql.append("  wrin_remarks, role_level,KZPM_CREATEDBY ");
			sql.append(" FROM  gen_tl_workflow_info, ");
			sql.append(" (SELECT empm_keyid, role_keyid,role_name,frl_level role_level, empm_name,KZPM_KEYID,KZPM_CREATEDBY FROM ");
			sql.append(" KZN_TL_PROJECTCREATIONMST,gen_tl_employeemst,ADM_TL_ROLEMST,gen_tl_fnlnrolemap ");
			sql.append(" WHERE  FRL_ROLE_KEYID = ROLE_KEYID ");
			sql.append(" AND KZPM_CREATEDBY=empm_keyid ");
			 
			sql.append(" AND upper(replace(ROLE_NAME,' ')) = 'PROJECTLEAD' ");
			sql.append(" AND  KZPM_KEYID='"+kznkeyid+"') ");
			sql.append(" WHERE 1=1  ");
			sql.append(" AND WRIN_REF_ID(+)=KZPM_KEYID  ");
			sql.append(" AND WRIN_EMPLOYEE_ID(+)=empm_keyid ");
			sql.append(" AND  WRIN_REF_TYPE(+)='"+transCode+"' ");
			sql.append(" ) ");
			sql.append(" UNION ");
		}
		
		sql.append(" SELECT  ");
		sql.append("     wrin_keyid, ");
		sql.append("     wrkd_keyid,");
		sql.append("     wrml_keyid,");
		sql.append("     empm_keyid,");
		sql.append("     CASE ");
		sql.append("  WHEN empm_keyid = frt_empm_keyid THEN ");
		sql.append("      CASE  ");
		sql.append("   WHEN wrin_status IN ('Pending', 'E') THEN 'Y' ");
		sql.append("   ELSE 'N' ");
		sql.append("      END ");
		sql.append("  ELSE 'N' ");
		sql.append("     END AS enabl, ");
		sql.append("     role_keyid, ");
		sql.append("     '' AS editrow, ");
		sql.append("     '' AS saverow, ");
		sql.append("     role_name, ");
		sql.append("     CASE  ");
		sql.append("  WHEN wrin_keyid IS NULL OR wrin_keyid = ' ' THEN empm_name ");
		sql.append("  ELSE EMPMNAME ");
		sql.append("     END AS empm_name, ");
		sql.append("     CASE  ");
		sql.append("  WHEN wrin_status IN ('A', 'R', 'E') THEN wrin_status ");
		sql.append("  WHEN WRIN_EMPLOYEE_ID = empm_keyid THEN wrin_status ");
		sql.append("  ELSE 'Pending' ");
		sql.append("     END AS currentstat, ");
		sql.append("     wrin_date, ");
		sql.append("     wrin_remarks, ");
		sql.append("     2 AS dataorder, ");
		sql.append("     role_level ");
		sql.append(" FROM ( ");
		sql.append("     SELECT "); 
		sql.append("  FRT_FNLN_KEYID, ");
		sql.append("  wrin_keyid, ");
		sql.append("  wrkd_keyid, ");
		sql.append("  wrml_keyid, ");
		sql.append("  role_keyid, ");
		sql.append("  role_name, ");
		sql.append("  empm_keyid, ");
		sql.append("  empm_name, ");
		sql.append("  COALESCE(wrin_status, 'Pending') AS wrin_status, ");
		sql.append("  wrin_date, ");
		sql.append("  wrin_remarks, ");
		sql.append("  FRL_LEVEL AS role_level, ");
		sql.append("  WRIN_EMPLOYEE_ID, ");
		sql.append("  (SELECT C.EMPM_NAME ");
		sql.append("   FROM GEN_TL_EMPLOYEEMST C ");
		sql.append("   WHERE C.EMPM_KEYID = WRIN_EMPLOYEE_ID ");
		sql.append("  ) AS EMPMNAME  FROM (");
       // sql.append(" FROM  gen_tl_workflow_info, right JOIN ( ");
		
		if(transtype.equals("BTSNOSAVIN")){
            if(status!=null)
            	{
            	  if(Integer.parseInt(status)>1)
            	  {
            		  sql.append("     ");
            		  sql.append("  SELECT  ");
            		  sql.append("      wrkd_stage, ");
            		  sql.append("      wrkd_wrkm_keyid, ");
            		  sql.append("      wrml_keyid, ");
            		  sql.append("      wrkd_keyid ");
            		  sql.append("  FROM gen_tl_workflow_menu_link ");
            		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL_JUN2017 ");
            		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
            		  sql.append("  WHERE TRIM(wrml_trans_code) =TIM(?) ");
            		//  sql.append("  --Check This--  AND WRML_TEMPFIELD3 = '-' ");
            		  sql.append("     ) sub1 "); 
            		 
            		//  sql.append(" ( select wrkd_stage,wrkd_wrkm_keyid,wrml_keyid,wrkd_keyid from gen_tl_workflow_menu_link, ");
                    //  sql.append("  GEN_TL_WORKFLOWDTL_JUN2017 where WRML_WRKM_KEYID =  WRKD_WRKM_KEYID AND WRML_TEMPFIELD3='-' AND wrml_trans_code = ? ), ");  
            	  }
            	  else{
            		  sql.append("  SELECT  ");
            		  sql.append("      wrkd_stage, ");
            		  sql.append("      wrkd_wrkm_keyid, ");
            		  sql.append("      wrml_keyid, ");
            		  sql.append("      wrkd_keyid ");
            		  sql.append("  FROM gen_tl_workflow_menu_link ");
            		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
            		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
            		  sql.append("  WHERE TRIM(wrml_trans_code) = ? ");
            		  sql.append("  --Check This--  AND WRML_TEMPFIELD3 = '-' ");
            		  sql.append("     ) sub1 ");
            		  }
            	
            	}
            else{
            	
       		  sql.append("  SELECT  ");
       		  sql.append("      wrkd_stage, ");
       		  sql.append("      wrkd_wrkm_keyid, ");
       		  sql.append("      wrml_keyid, ");
       		  sql.append("      wrkd_keyid ");
       		  sql.append("  FROM gen_tl_workflow_menu_link ");
       		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
       		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
       		  sql.append("  WHERE trim(wrml_trans_code) = TRIM(?) ");
       		  sql.append("  --Check This--  AND WRML_TEMPFIELD3 = '-' ");
       		  sql.append("     ) sub1 ");
            
            }  
          
        }
		else{
   		  sql.append("  SELECT  ");
   		  sql.append("      wrkd_stage, ");
   		  sql.append("      wrkd_wrkm_keyid, ");
   		  sql.append("      wrml_keyid, ");
   		  sql.append("      wrkd_keyid ");
   		  sql.append("  FROM gen_tl_workflow_menu_link ");
   		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
   		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
   		  sql.append("  WHERE trim(wrml_trans_code) = TRIM(?) ");
   		//  sql.append("  --Check This--  AND WRML_TEMPFIELD3 = '-' ");
			
		}
 		  sql.append("     ) sub1 ");

		
		//sql.append(" ON wrin_wrml_keyid = wrml_keyid AND wrin_wrkd_keyid = wrkd_keyid ");
		sql.append("     INNER JOIN adm_tl_rolemst ON wrkd_stage = role_keyid ");
		sql.append("     INNER JOIN gen_tl_fnlnrolemap ON FRL_ROLE_KEYID = ROLE_KEYID ");
		sql.append("     INNER JOIN gen_tl_fnlnroleteam ON role_keyid = frt_role_keyid AND FRL_KEYID = FRT_FRL_KEYID ");
		sql.append("     INNER JOIN gen_tl_employeemst ON frt_empm_keyid = empm_keyid ");
		sql.append("     LEFT JOIN gen_tl_workflow_info ");
		sql.append("  ON wrin_wrml_keyid = wrml_keyid ");
		sql.append("  AND wrin_wrkd_keyid = wrkd_keyid ");
		sql.append("  AND trim(wrin_ref_id) = TRIM(?) ");
		sql.append("  AND trim(wrin_ref_type) = TRIM(?) ");
		sql.append(" ) a ");
		sql.append(" INNER JOIN ( ");
		sql.append("     SELECT  ");
		sql.append("  frt_empm_keyid , ");
		sql.append("  FRL_LEVEL AS selemprole, ");
		sql.append("  parentflids || '-' || flid AS parentflids ");
		sql.append("     FROM adm_tl_rolemst ");
		sql.append("     INNER JOIN gen_tl_fnlnrolemap ON FRL_ROLE_KEYID = ROLE_KEYID ");
		sql.append("     INNER JOIN gen_tl_fnlnroleteam ON role_keyid = frt_role_keyid AND FRL_KEYID = FRT_FRL_KEYID ");
		sql.append("     INNER JOIN gen_mv_flidhierarchy ON POSITION(frt_fnln_keyid IN parentflids || '-' || flid) > 0 ");
		sql.append("     WHERE TRIM(frt_empm_keyid) = TRIM(?)");
		sql.append("       AND POSITION('AROL0006' IN trim(role_keyid)) > 0 ");
		sql.append("       AND trim(flid) = 'FNL000000049' ");
		sql.append(" ) b ON POSITION(FRT_FNLN_KEYID IN parentflids) > 0 ");
		sql.append(" WHERE 1 = 1  ");
		sql.append(" ORDER BY role_level DESC ");
		sql.append("     ) subquery1 ");
		sql.append("     ORDER BY role_level DESC ");
		sql.append(" ) subquery2 ");
		//sql.append(" ORDER BY role_level DESC ");
		
			
		
		if(localTransCode.equals("OTHERS")){
			sql.append(" order by role_level DESC");			
		}
		else if(localTransCode.equals("DMAIC"))
		{
			sql.append(" order by dataorder, role_level DESC");
		}
		CommonMessage.debugMsg("sql="+sql);
		return sql.toString();
	}
	
	
	
	// Addded By Kiran for DHQ Location //
	
	public static String getWorkFlowTransSqlDHQ(String transtype,String kznkeyid,String transCode,String empId,Character enable,String status){
		StringBuilder sql = new StringBuilder();
		CommonMessage.debugMsg("The transCode:::"+transCode);
			
		
		String localTransCode;
		
		if(transCode.equals("PRODE") || transCode.equals("PROME")  || transCode.equals("PROAN")  
				|| transCode.equals("PROIM")  || transCode.equals("PROCO")  || transCode.equals("PROCL"))
		{
			localTransCode = "DMAIC";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}
		else
		{
			localTransCode = "OTHERS";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}	
			
		
		//sql.append(" select wrin_keyid,wrkd_keyid, wrml_keyid, empm_keyid, ");
		sql.append(" select distinct wrin_keyid,wrkd_keyid, wrml_keyid, empm_keyid, "); //added by babu
		//sql.append(" decode(enabl,'Y',DECODE( role_level,  Nvl(prevLevl,role_level) ,'Y', DECODE ( NVL (prevstat, 'A'), 'A', 'Y', 'N')), enabl) enabl, role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'A','Accepted','R','Rejected','E','Rework') currentstat, ");
		
		if( enable != null && enable == 'N')
			sql.append(" 'N' as enabl, ");
		else if(localTransCode.equals("DMAIC")){		
			//sql.append(" decode(enabl,'Y',case when role_keyid = ? or role_keyid = 'AROL0059' then DECODE(empm_keyid,? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) else 'N' end , enabl) enabl,");
			String strQry = " DECODE(empm_keyid, '"+empId+"' ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			String strQry2 = " DECODE(empm_keyid, ? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			CommonMessage.debugMsg("strQry="+strQry);
			CommonMessage.debugMsg("strQry2="+strQry2);
			
			sql.append(" decode(enabl,'Y', decode( role_keyid , 'AROL0059', "+ strQry + ", ");
			sql.append(" 	CASE GREATEST(INSTR( ? ,role_keyid),0) WHEN 0 THEN 'N' else " + strQry2 + " end ) , enabl) enabl,"); //modified by babu
			
		}else {
			sql.append(" DECODE(enabl,'Y', CASE GREATEST(INSTR( ? ,role_keyid),0) ");
			sql.append(" WHEN 0 THEN 'N' ELSE DECODE(empm_keyid, ? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), DECODE(role_keyid,prevroleid,'Y','N'),'N') ),'N' )  END ");
			sql.append(" , enabl) enabl, "); //ADDED BY BABU
			  
			//1sql.append(" decode(enabl,'Y',DECODE(role_keyid,?, DECODE(empm_keyid,? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ),'N'), enabl) enabl,");
			//2sql.append(" decode(enabl,'Y', DECODE(empm_keyid, ? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ),'N'), enabl) enabl,"); //modified by babu
		}
		
		sql.append(" role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'A','Accepted','R','Rejected','E','Rework',currentstat) currentstat, ");
		sql.append(" TO_CHAR(wrin_date,'dd-Mon-yyyy') wrin_date,wrin_remarks, dataorder ");
		
		//if(localTransCode.equals("OTHERS")){
			sql.append(",role_level");
		//}
		sql.append(" from ( ");
		sql.append(" SELECT wrin_keyid,wrkd_keyid,wrml_keyid,empm_keyid,enabl,role_keyid, ");
		sql.append(" LAG (NVL (currentstat, 'Pending'), 1) OVER (ORDER BY role_level desc) prevstat,");
		sql.append("  '' editrow, '' saverow, role_name, empm_name,currentstat,wrin_date, wrin_remarks, role_level, ");
		sql.append("  LAG (role_level, 1) OVER (ORDER BY role_level desc) prevlevl,LAG (role_keyid, 1) OVER (ORDER BY role_level desc) prevroleid, dataorder ");
		sql.append("  from ( ");
	
		if(localTransCode.equals("DMAIC")){	
			sql.append(" SELECT wrin_keyid,wrkd_keyid, wrml_keyid, empm_keyid,");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl ");
			else	
				sql.append(" DECODE (KZPM_CREATEDBY,'"+empId+"', decode(currentstat,'Pending', 'Y','E','Y','N'),'N') enabl ");
			//sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework') currentstat, ");
			sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework','Pending') currentstat, ");
			
			sql.append(" wrin_date,wrin_remarks,1 dataorder,role_level FROM ( ");
			sql.append(" SELECT   wrin_keyid, '' wrkd_keyid,'' wrml_keyid, empm_keyid,wrin_status, ");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl, ");
			else	
				sql.append(" DECODE (NVL (wrin_status, 'Pending'),'Pending','Y','E', 'Y', 'N') enabl,");
			//sql.append("        DECODE (empm_keyid, WRIN_EMPLOYEE_ID, decode(NVL (wrin_status, 'Pending'),'Pending', 'Y', 'N'),'N') enabl, ");
			sql.append("         role_keyid,lead(nvl(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat, ");
			sql.append("		'' editrow, '' saverow, role_name, empm_name, NVL (wrin_status, 'Pending') currentstat, wrin_date, ");
			sql.append("        wrin_remarks, role_level,KZPM_CREATEDBY ");
			sql.append(" FROM  gen_tl_workflow_info, ");
			sql.append(" (SELECT empm_keyid, role_keyid,role_name,frl_level role_level, empm_name,KZPM_KEYID,KZPM_CREATEDBY FROM ");
			sql.append(" KZN_TL_PROJECTCREATIONMST,gen_tl_employeemst,ADM_TL_ROLEMST,gen_tl_fnlnrolemap ");
			sql.append(" WHERE  FRL_ROLE_KEYID = ROLE_KEYID ");
			sql.append(" AND KZPM_CREATEDBY=empm_keyid ");
			 
			sql.append(" AND upper(replace(ROLE_NAME,' ')) = 'PROJECTLEAD' ");
			sql.append(" AND  KZPM_KEYID='"+kznkeyid+"') ");
			sql.append(" WHERE 1=1  ");
			sql.append(" AND WRIN_REF_ID(+)=KZPM_KEYID  ");
			sql.append(" AND WRIN_EMPLOYEE_ID(+)=empm_keyid ");
			sql.append(" AND  WRIN_REF_TYPE(+)='"+transCode+"' ");
			sql.append(" ) ");
			sql.append(" UNION ");
		}
		
		sql.append(" SELECT   wrin_keyid, wrkd_keyid, wrml_keyid, empm_keyid, ");
		sql.append(" DECODE (empm_keyid, frt_empm_keyid, decode(wrin_status,'Pending', 'Y','E','Y', 'N'),'N') enabl,  ");
		sql.append(" role_keyid,") ;//lead(nvl(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat, ");
		sql.append(" '' editrow, '' saverow, role_name,DECODE(wrin_keyid,null,empm_name,' ',empm_name,EMPMNAME) AS empm_name, ");
		//sql.append(" decode(WRIN_EMPLOYEE_ID,empm_keyid, wrin_status,'Pending') currentstat, ");
		sql.append(" decode(wrin_status, 'A',wrin_status, 'R',wrin_status,'E',wrin_status, decode(WRIN_EMPLOYEE_ID,empm_keyid, wrin_status,'Pending')) currentstat,");
		sql.append(" wrin_date, wrin_remarks,2 dataorder, role_level ") ;// ,LEAD (role_level, 1) OVER (ORDER BY role_level) prevLevl ");
		sql.append(" FROM (SELECT FRT_FNLN_KEYID,wrin_keyid, wrkd_keyid, wrml_keyid, role_keyid, role_name, ");
		sql.append(" empm_keyid, empm_name , ");
		sql.append("  NVL (wrin_status, 'Pending') wrin_status,  wrin_date, ");
		sql.append(" wrin_remarks, FRL_LEVEL role_level,WRIN_EMPLOYEE_ID,(SELECT C.EMPM_NAME FROM GEN_TL_EMPLOYEEMST C WHERE C.EMPM_KEYID=WRIN_EMPLOYEE_ID) AS EMPMNAME ");
		sql.append(" FROM   gen_tl_workflow_info, ");
		
		if(transtype.equals("BTSNOSAVIN")){
            if(status!=null)
            	{
            	  if(Integer.parseInt(status)>1)
            	  {
            		  
            		 
            		  sql.append(" ( select wrkd_stage,wrkd_wrkm_keyid,wrml_keyid,wrkd_keyid from gen_tl_workflow_menu_link, ");
                      sql.append("  GEN_TL_WORKFLOWDTL_JUN2017 where WRML_WRKM_KEYID =  WRKD_WRKM_KEYID AND wrml_trans_code = ? AND WRML_TEMPFIELD3='D'), ");  
            	  }
            	  else{
            		  sql.append(" ( select wrkd_stage,wrkd_wrkm_keyid,wrml_keyid,wrkd_keyid from gen_tl_workflow_menu_link, ");
                      sql.append("  GEN_TL_WORKFLOWDTL where WRML_WRKM_KEYID =  WRKD_WRKM_KEYID AND wrml_trans_code = ? AND WRML_TEMPFIELD3='D'), ");            	  
            	  }
            	
            	}
            else{
            	sql.append(" ( select wrkd_stage,wrkd_wrkm_keyid,wrml_keyid,wrkd_keyid from gen_tl_workflow_menu_link, ");
                sql.append("  GEN_TL_WORKFLOWDTL where WRML_WRKM_KEYID =  WRKD_WRKM_KEYID AND wrml_trans_code = ? AND WRML_TEMPFIELD3='D'), ");	
            }
	       
          
        }
		else{
			sql.append(" ( select wrkd_stage,wrkd_wrkm_keyid,wrml_keyid,wrkd_keyid from gen_tl_workflow_menu_link, ");
			sql.append("  gen_tl_workflowdtl where WRML_WRKM_KEYID =  WRKD_WRKM_KEYID AND wrml_trans_code = ? AND WRML_TEMPFIELD3='D'), ");
			
			
		}
		
		
		sql.append(" adm_tl_rolemst, gen_tl_fnlnrolemap,");
		sql.append("  gen_tl_fnlnroleteam, ");
		sql.append(" gen_tl_employeemst ");
		sql.append(" WHERE wrin_wrml_keyid(+) = wrml_keyid ");
		sql.append(" AND wrin_wrkd_keyid(+) = wrkd_keyid ");
		//sql.append(" AND wrml_wrkm_keyid = wrkd_wrkm_keyid ");
		sql.append(" AND wrkd_stage = role_keyid ");
		sql.append(" AND role_keyid = frt_role_keyid AND FRL_ROLE_KEYID = ROLE_KEYID and FRL_KEYID =  FRT_FRL_KEYID ");
		sql.append(" AND frt_empm_keyid = empm_keyid ");
		//sql.append(" AND wrml_trans_code = ? ");
		sql.append(" AND wrin_ref_id(+) = ? ");
		sql.append(" AND wrin_ref_type(+) = ?) a, ");
		sql.append(" (SELECT frt_empm_keyid, FRL_LEVEL selemprole, parentflids|| '-' || flid parentflids ");
		sql.append(" FROM adm_tl_rolemst,gen_tl_fnlnrolemap,  gen_tl_fnlnroleteam, gen_mv_flidhierarchy ");
		sql.append(" WHERE role_keyid = frt_role_keyid  AND FRL_ROLE_KEYID = ROLE_KEYID and FRL_KEYID =  FRT_FRL_KEYID");
		sql.append("  AND frt_empm_keyid = ? ");
		//sql.append("  and role_keyid= ? "); 
		sql.append("  AND INSTR (?,role_keyid) > 0 "); //ADDED BY BABU
	
		
		sql.append(" AND INSTR (parentflids || '-' || flid, frt_fnln_keyid) > 0 ");
		sql.append(" AND flid = ? ");
		
		sql.append(" ) b ");
		sql.append(" WHERE 1=1 ");
		/*sql.append(" and DECODE (a.role_level, ");
		sql.append(" selemprole, DECODE (empm_keyid, frt_empm_keyid, 'Y'), ");
		sql.append("   'Y' ");
		sql.append("  ) = 'Y' ");*/
		sql.append(" and instr(parentflids, FRT_FNLN_KEYID) > 0 ");
		sql.append(" ORDER BY role_level DESC)ORDER BY role_level desc )  " );
		
		
		
		
		if(localTransCode.equals("OTHERS")){
			sql.append(" order by role_level DESC");			
		}
		else if(localTransCode.equals("DMAIC"))
		{
			sql.append(" order by dataorder, role_level DESC");
		}
		CommonMessage.debugMsg("sql="+sql);
		return sql.toString();
	}
	
	// end of DHQ location Method
	
	public static String getdmcWorkFlowTransSql(String kznkeyid,String transCode,String empId,Character enable){
		StringBuilder sql = new StringBuilder();
		CommonMessage.debugMsg("In Sql File");
		CommonMessage.debugMsg("kznkeyid="+kznkeyid);
		CommonMessage.debugMsg("transCode="+transCode);
		CommonMessage.debugMsg("empId="+empId);
		CommonMessage.debugMsg("enable="+enable);
		
		/*sql.append("SELECT * from (select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH') as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid AS wrinwrmlkeyid,dfiw_financehead AS empid,'' as enablerow,'Finance Head'   AS roleid,'' AS edit,'' AS SAVE,'Finance Head' AS rolename,(SELECT empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno     ='"+kznkeyid+"' AND dfiw_financehead = empm_keyid) as employeename, (select DECODE(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH') as adate, ");
		sql.append("(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH') as remarks from gen_tl_dmcfipworkflow WHERE dfiw_fipno='"+kznkeyid+"' union SELECT (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK')AS wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,''as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (SELECT empm_name FROM gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno    ='"+kznkeyid+"' AND dfiw_kkchampion = empm_keyid ) as employeename, (select DECODE(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK') as status, ");
		sql.append("(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK') as remarks  from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"' union  SELECT (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid(+)='DMCPL')AS wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,'' as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, ");
		sql.append("(select DECODE(wrin_status,'A','Submitted','Created') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL') as remarks  from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"' UNION  select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH')      as wrinkeyid, dfiw_fipno   as wrinwrkdkeyid,dfiw_keyid   as wrinwrmlkeyid,dfiw_pbuhead as empid,'' as enablerow,'PBU Head' as roleid,'' as edit,'' as save,'PBU Head' as rolename, ");
		sql.append("(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow   where dfiw_fipno ='"+kznkeyid+"' and dfiw_pbuhead = empm_keyid ) as employeename,(select DECODE(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH') as remarks  from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"') ORDER BY rolename DESC  ");
	    CommonMessage.debugMsg("sql="+sql);*/
		



		if(transCode.equals("PRODE")){
//					sql.append("select * from (select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_financehead as empid,(select decode(wrin_status,'A','3Y','3N') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as enablerow,'Finance Head'   as roleid,'' as edit,'' as save,'Finance Head' as rolename,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno     ='"+kznkeyid+"' and dfiw_financehead = empm_keyid) as employeename, (select decode(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as remarks, 3 as Dataorder from gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' union SELECT  (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE')AS wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select decode(wrin_status,'A','4Y','4N') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (SELECT empm_name FROM gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno    ='"+kznkeyid+"' AND dfiw_kkchampion = empm_keyid ) as employeename, (select DECODE(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as status, (select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as remarks,  4 as Dataorder from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"'");
//					sql.append("union  select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid(+)='DMCPL' and wrin_ref_type='PRODE')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select decode(wrin_status,'A','1N','1Y') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select decode(wrin_status,'A','Submitted','Created') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' union  select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE')as wrinkeyid, dfiw_fipno   as wrinwrkdkeyid,dfiw_keyid   as wrinwrmlkeyid,dfiw_pbuhead as empid,(select decode(wrin_status,'A','2Y','2N') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as enablerow,'PBU Head' as roleid,'' as edit,'' as save,'PBU Head' as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow   where dfiw_fipno ='"+kznkeyid+"' and dfiw_pbuhead = empm_keyid ) as employeename,(select decode(wrin_status,'E','Rework','A','Accept','R','Reject','Pending') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder ");
					sql.append("select * from (select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_financehead as empid,(select case when WRIN_STATUS = 'A' then '3Y' ELSE '3N' END  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as enablerow,'Finance Head'   as roleid,'' as edit,'' as save,'Finance Head' as rolename,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno     ='"+kznkeyid+"' and dfiw_financehead = empm_keyid) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as remarks, 3 as Dataorder from gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' union SELECT  (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE')AS wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '4Y' else '4N' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PRODE') as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (SELECT empm_name FROM gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno    ='"+kznkeyid+"' AND dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as remarks,  4 as Dataorder from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"'");
					sql.append("union  select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' union  select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE')as wrinkeyid, dfiw_fipno   as wrinwrkdkeyid,dfiw_keyid   as wrinwrmlkeyid,dfiw_pbuhead as empid,(select case when wrin_status = 'A' then '2Y' else '2N' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PRODE') as enablerow,'PBU Head' as roleid,'' as edit,'' as save,'PBU Head' as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow   where dfiw_fipno ='"+kznkeyid+"' and dfiw_pbuhead = empm_keyid ) as employeename,(select  case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PRODE') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder ");
					
					CommonMessage.debugMsg("sql="+sql);
				}			
				else if(transCode.equals("PROME")){		  		
					sql.append("select * from  (select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROME')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y'  else '1N' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PRODE') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROME') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROME') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROME') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' union ");
					sql.append("SELECT (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROME')AS wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROME') as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (SELECT empm_name FROM gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno    ='"+kznkeyid+"' AND dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROME') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROME') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROME') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder ");
					CommonMessage.debugMsg("sql="+sql);
			   }
			   else if(transCode.equals("PROAN")){
					sql.append("select * from (select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROAN')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROME')  as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROAN') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROAN') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROAN') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' union ");
					sql.append("select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROAN')as wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROAN') as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno    ='"+kznkeyid+"' and dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROAN') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROAN') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROAN') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder");
					CommonMessage.debugMsg("sql="+sql);
			   }
			   else if(transCode.equals("PROIM")){
					sql.append("select * from (select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROIM')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROAN') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROIM') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROIM') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROIM') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' ");
					sql.append("union select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROIM')as wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROIM') as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno    ='"+kznkeyid+"' and dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROIM') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROIM') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROIM') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder ");
					CommonMessage.debugMsg("sql="+sql);

			   }
			   else if(transCode.equals("PROCO")){
					sql.append("select * from (select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCO')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROIM') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCO') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCO') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCO') as remarks,1 as Dataorder   from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' ");
					sql.append("union SELECT (SELECT wrin_keyid FROM gen_tl_workflow_info WHERE  wrin_REF_ID='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCO')AS wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCO')  as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (SELECT empm_name FROM gen_tl_employeemst,gen_tl_dmcfipworkflow WHERE dfiw_fipno    ='"+kznkeyid+"' AND dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  WHERE wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCO') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCO') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCO') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  WHERE dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder ");
					CommonMessage.debugMsg("sql="+sql);
			   }
			   else if(transCode.equals("PROCL")){
				   sql.append("select * from (select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PROCL') as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_financehead as empid,(select case when wrin_status = 'A' then '3Y' else '3N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PROCL') as enablerow,'Finance Head'   as roleid,'' as edit,'' as save,'Finance Head' as rolename,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno     ='"+kznkeyid+"' and dfiw_financehead = empm_keyid) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PROCL') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PROCL') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PROCL') as remarks,3 as Dataorder from gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' union  select  (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCL')as wrinkeyid, dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_kkchampion as empid,(select case when wrin_status = 'A' then '4Y' else '4N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFFH' and wrin_ref_type='PROCL')  as enablerow,'KK Champion'as roleid,'' as edit,'' as save,'KK Champion'  as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno    ='"+kznkeyid+"' and dfiw_kkchampion = empm_keyid ) as employeename, (select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCL') as status, (select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCL') as adate, (select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCL') as remarks,4 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' ");
				   sql.append("union  select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCL')as wrinkeyid,dfiw_fipno as wrinwrkdkeyid,dfiw_keyid as wrinwrmlkeyid,dfiw_projectleader as empid,(select case when wrin_status = 'A' then '1Y' else '1N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCFKK' and wrin_ref_type='PROCO') as enablerow,'Project Leader'  as roleid,''  as edit,'' as save,'Project Leader'   as rolename ,(select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow where dfiw_fipno='"+kznkeyid+"' and dfiw_projectleader = empm_keyid ) as employeename, (select case when wrin_status = 'A' then 'Submitted' else 'Created' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCL') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCL') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCL') as remarks,1 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"' union  select (select wrin_keyid from gen_tl_workflow_info where  wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PROCL') as wrinkeyid, dfiw_fipno   as wrinwrkdkeyid,dfiw_keyid   as wrinwrmlkeyid,dfiw_pbuhead as empid,(select case when wrin_status = 'A' then '2Y' else '2N' end from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPL' and wrin_ref_type='PROCL') as enablerow,'PBU Head' as roleid,'' as edit,'' as save,'PBU Head' as rolename, (select empm_name from gen_tl_employeemst,gen_tl_dmcfipworkflow   where dfiw_fipno ='"+kznkeyid+"' and dfiw_pbuhead = empm_keyid ) as employeename,(select case when WRIN_STATUS = 'E' then 'Rework' when WRIN_STATUS = 'A' then 'Accept' when WRIN_STATUS = 'R' then 'Rework' ELSE 'Pending' END from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PROCL') as status,(select to_char(wrin_date,'DD-MON-YYYY') from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PROCL') as adate,(select wrin_remarks  from gen_tl_workflow_info  where wrin_ref_id='"+kznkeyid+"' and wrin_wrkd_keyid='DMCPH' and wrin_ref_type='PROCL') as remarks,2 as Dataorder  from gen_tl_dmcfipworkflow  where dfiw_fipno='"+kznkeyid+"') ORDER BY Dataorder"); 
					CommonMessage.debugMsg("sql="+sql);
			   }	
				return sql.toString();
	}
	

	public static String getStatusUpdateSql(String refId,String empId)
	{
		
		StringBuilder sql = new StringBuilder(" UPDATE GEN_TL_WORKFLOW_INFO SET WRIN_STATUS = 'A',WRIN_DATE = CURRENT_DATE where WRIN_REF_ID = '");
		sql.append(refId);
		sql.append("' AND WRIN_STATUS IN ('C','E','-') AND WRIN_REF_TYPE='PRODE' AND WRIN_EMPLOYEE_ID ='");
		sql.append(empId);
		sql.append('\'');
		return sql.toString();
	}
	
	public static String getdmcStatusUpdateSql(String refId,String empId)
	{
		
		StringBuilder sql = new StringBuilder(" UPDATE GEN_TL_WORKFLOW_INFO SET WRIN_STATUS = 'A',WRIN_DATE = CURRENT_DATE where WRIN_REF_ID = '");
		sql.append(refId);
		sql.append("' AND WRIN_STATUS IN ('C','E') AND WRIN_REF_TYPE='PRODE' AND WRIN_EMPLOYEE_ID ='");
		sql.append(empId);
		sql.append('\'');
		return sql.toString();
	}
	
	public static String getReworkSatusUpdateSql(String refId, String wfMenuId,String refType)
	{
		
		StringBuilder sql = new StringBuilder(" UPDATE GEN_TL_WORKFLOW_INFO SET WRIN_STATUS = 'E' where WRIN_REF_ID = '");
		sql.append(refId);
		sql.append("' AND WRIN_STATUS ='A' AND WRIN_REF_TYPE = '" ); //AND WRIN_WRML_KEYID = '");
		sql.append(refType);
		sql.append('\'');
		//sql.append(wfMenuId);
		//sql.append('\'');
		return sql.toString();
	}

	
	
	
	public static String getWorkFlowTransSqlPG(String roleId,String transCode, String empId,String wrinRefId,String wrinRefType,String flId,Character enable,String status) {
		String localTransCode;
		CommonMessage.debugMsg("getWorkFlowTransSqlPG 32 "+enable);
		StringBuilder sql = new StringBuilder();
		if(transCode.equals("PRODE") || transCode.equals("PROME")  || transCode.equals("PROAN")  
				|| transCode.equals("PROIM")  || transCode.equals("PROCO")  || transCode.equals("PROCL"))
		{
			localTransCode = "DMAIC";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}
		else
		{
			localTransCode = "OTHERS";
			CommonMessage.debugMsg("localTransCode="+localTransCode);
		}	
			
		sql.append(" WITH menu_links AS (  SELECT ");
				

		if( enable != null && enable == 'N')
			//sql.append(" 'N' as enabl, ");
			sql.append(" ");
		else if(localTransCode.equals("DMAIC")){		
			//sql.append(" decode(enabl,'Y',case when role_keyid = ? or role_keyid = 'AROL0059' then DECODE(empm_keyid,? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) else 'N' end , enabl) enabl,");
			String strQry = " DECODE(empm_keyid, '"+empId+"' ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			String strQry2 = " DECODE(empm_keyid, ? ,DECODE (NVL (prevstat, 'A'), 'A', 'Y','Submitted','Y',DECODE (role_level,NVL (prevlevl, role_level), decode(role_keyid,prevroleid,'Y','N'),'N') ), 'N' ) ";
			sql.append(" decode(enabl,'Y', decode( role_keyid , 'AROL0059', "+ strQry + ", ");
			sql.append(" 	CASE GREATEST(INSTR( ? ,role_keyid),0) WHEN 0 THEN 'N' else " + strQry2 + " end ) , enabl) enabl,"); //modified by babu
			
		}
		else {
//			sql.append("  CASE ");
//			sql.append(" WHEN enabl = 'Y' THEN ");
//			sql.append("  CASE ");
//			sql.append(" WHEN GREATEST(POSITION( '"+roleId+"' IN trim(role_keyid)), 0) = 0 THEN 'N' ");
//			sql.append(" ELSE ");
//			sql.append(" CASE ");
//			sql.append(" WHEN trim(empm_keyid) = '"+empId+"' THEN ");
//			sql.append(" CASE  ");
//			sql.append(" WHEN COALESCE(prevstat, 'A') IN ('A', 'Submitted') THEN 'Y' ");
//			sql.append(" WHEN role_level = COALESCE(prevlevl, role_level) THEN ");
//			sql.append(" CASE WHEN role_keyid = prevroleid THEN 'Y' ELSE 'N' END ");
//			sql.append(" ELSE 'N' ");
//			sql.append(" END ");
//			sql.append(" ELSE 'N' ");
//			sql.append(" END ");
//			sql.append(" END ");
//			sql.append(" ELSE enabl ");
//			sql.append(" END :: varchar AS enabl,");
 
				}
		
		
	
		if(localTransCode.equals("DMAIC")){	
			sql.append(" SELECT wrin_keyid,wrkd_keyid, wrml_keyid, empm_keyid,");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl ");
			else	
				sql.append(" DECODE (KZPM_CREATEDBY,'"+empId+"', decode(currentstat,'Pending', 'Y','E','Y','N'),'N') enabl ");
			//sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework') currentstat, ");
			sql.append(" , role_keyid,'' editrow, '' saverow,role_name,empm_name,decode(currentstat,'C','Created', 'A','Submitted','R','Rejected','E','Rework','Pending') currentstat, ");
			
			sql.append(" wrin_date,wrin_remarks,1 dataorder,role_level FROM ( ");
			sql.append(" SELECT   wrin_keyid, '' wrkd_keyid,'' wrml_keyid, empm_keyid,wrin_status, ");
			if( enable != null && enable == 'N')
				sql.append(" 'N' as enabl, ");
			else	
				sql.append(" DECODE (NVL (wrin_status, 'Pending'),'Pending','Y','E', 'Y', 'N') enabl,");
			//sql.append("        DECODE (empm_keyid, WRIN_EMPLOYEE_ID, decode(NVL (wrin_status, 'Pending'),'Pending', 'Y', 'N'),'N') enabl, ");
			sql.append("  role_keyid,lead(nvl(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat, ");
			sql.append("  '' editrow, '' saverow, role_name, empm_name, NVL (wrin_status, 'Pending') currentstat, wrin_date, ");
			sql.append("  wrin_remarks, role_level,KZPM_CREATEDBY ");
			sql.append(" FROM  gen_tl_workflow_info, ");
			sql.append(" (SELECT empm_keyid, role_keyid,role_name,frl_level role_level, empm_name,KZPM_KEYID,KZPM_CREATEDBY FROM ");
			sql.append(" KZN_TL_PROJECTCREATIONMST,gen_tl_employeemst,ADM_TL_ROLEMST,gen_tl_fnlnrolemap ");
			sql.append(" WHERE  FRL_ROLE_KEYID = ROLE_KEYID ");
			sql.append(" AND KZPM_CREATEDBY=empm_keyid ");
			 
			sql.append(" AND upper(replace(ROLE_NAME,' ')) = 'PROJECTLEAD' ");
			sql.append(" AND  KZPM_KEYID='"+wrinRefId+"') ");
			sql.append(" WHERE 1=1  ");
			sql.append(" AND WRIN_REF_ID(+)=KZPM_KEYID  ");
			sql.append(" AND WRIN_EMPLOYEE_ID(+)=empm_keyid ");
			sql.append(" AND  WRIN_REF_TYPE(+)='"+transCode+"' ");
			sql.append(" ) ");
			sql.append(" UNION ");
		}
		
	/*	sql.append(" SELECT  ");
		sql.append("     wrin_keyid, ");
		sql.append("     wrkd_keyid,");
		sql.append("     wrml_keyid,");
		sql.append("     empm_keyid,");
		sql.append("     CASE ");
		sql.append("  WHEN empm_keyid = frt_empm_keyid THEN ");
		sql.append("      CASE  ");
		sql.append("   WHEN wrin_status IN ('Pending', 'E') THEN 'Y' ");
		sql.append("   ELSE 'N' ");
		sql.append("      END ");
		sql.append("  ELSE 'N' ");
		sql.append("     END AS enabl, ");
		sql.append("     role_keyid, ");
		sql.append("     '' AS editrow, ");
		sql.append("     '' AS saverow, ");
		sql.append("     role_name, ");
		sql.append("     CASE  ");
		sql.append("  WHEN wrin_keyid IS NULL OR wrin_keyid = ' ' THEN empm_name ");
		sql.append("  ELSE EMPMNAME ");
		sql.append("     END AS empm_name, ");
		sql.append("     CASE  ");
		sql.append("  WHEN wrin_status IN ('A', 'R', 'E') THEN wrin_status ");
		sql.append("  WHEN WRIN_EMPLOYEE_ID = empm_keyid THEN wrin_status ");
		sql.append("  ELSE 'Pending' ");
		sql.append("     END AS currentstat, ");
		sql.append("     wrin_date, ");
		sql.append("     wrin_remarks, ");
		sql.append("     2 AS dataorder, ");
		sql.append("     role_level ");
		sql.append(" FROM ( ");
		sql.append("     SELECT "); 
		sql.append("  FRT_FNLN_KEYID, ");
		sql.append("  wrin_keyid, ");
		sql.append("  wrkd_keyid, ");
		sql.append("  wrml_keyid, ");
		sql.append("  role_keyid, ");
		sql.append("  role_name, ");
		sql.append("  empm_keyid, ");
		sql.append("  empm_name, ");
		sql.append("  COALESCE(wrin_status, 'Pending') AS wrin_status, ");
		sql.append("  wrin_date, ");
		sql.append("  wrin_remarks, ");
		sql.append("  FRL_LEVEL AS role_level, ");
		sql.append("  WRIN_EMPLOYEE_ID, ");
		sql.append("  (SELECT C.EMPM_NAME ");
		sql.append("   FROM GEN_TL_EMPLOYEEMST C ");
		sql.append("   WHERE C.EMPM_KEYID = WRIN_EMPLOYEE_ID ");
		sql.append("  ) AS EMPMNAME  FROM ("); */
       // sql.append(" FROM  gen_tl_workflow_info, right JOIN ( ");
		
		if(transCode.equals("BTSNOSAVIN")){
            if(status!=null)
            	{
            	  if(Integer.parseInt(status)>1)
            	  {
            		  
            		  sql.append("     ");
            		  sql.append("    ");
            		  sql.append("      wrkd_stage, ");
            		  sql.append("      wrkd_wrkm_keyid, ");
            		  sql.append("      wrml_keyid, ");
            		  sql.append("      wrkd_keyid ");
            		  sql.append("  FROM gen_tl_workflow_menu_link ");
            		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL_JUN2017 ");
            		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
            		  sql.append("  WHERE TRIM(wrml_trans_code) ='"+transCode+"' AND WRML_TEMPFIELD3 ='-' ");
            		  
            		}
            	  else{
            		  sql.append("    ");
            		  sql.append("      wrkd_stage, ");
            		  sql.append("      wrkd_wrkm_keyid, ");
            		  sql.append("      wrml_keyid, ");
            		  sql.append("      wrkd_keyid ");
            		  sql.append("  FROM gen_tl_workflow_menu_link ");
            		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
            		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
            		  sql.append("  WHERE TRIM(wrml_trans_code) = '"+transCode+"' AND WRML_TEMPFIELD3 ='-' ");
            		  }
            	
            	}
            else{
            	
       		  sql.append("    ");
       		  sql.append("      wrkd_stage, ");
       		  sql.append("      wrkd_wrkm_keyid, ");
       		  sql.append("      wrml_keyid, ");
       		  sql.append("      wrkd_keyid ");
       		  sql.append("  FROM gen_tl_workflow_menu_link ");
       		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
       		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
       		  sql.append("  WHERE trim(wrml_trans_code) = '"+transCode+"' AND WRML_TEMPFIELD3 ='-' ");
            
            }  
         
        }
		else{
   		  sql.append("    ");
   		  sql.append("      wrkd_stage, ");
   		  sql.append("      wrkd_wrkm_keyid, ");
   		  sql.append("      wrml_keyid, ");
   		  sql.append("      wrkd_keyid ");
   		  sql.append("  FROM gen_tl_workflow_menu_link ");
   		  sql.append("  INNER JOIN GEN_TL_WORKFLOWDTL ");
   		  sql.append("      ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
   		  sql.append("  WHERE trim(wrml_trans_code) = '"+transCode+"' AND WRML_TEMPFIELD3 ='-' ");
   		//  sql.append("  --Check This--  AND WRML_TEMPFIELD3 = '-' ");
			
		}
		 sql.append(" ),");

		 sql.append(" wf_info AS ( ");
		 sql.append("	  SELECT wrin_wrml_keyid, wrin_wrkd_keyid, wrin_keyid, wrin_ref_id, wrin_ref_type, ");
		 sql.append(" wrin_status, wrin_date, wrin_remarks, wrin_employee_id ");
		 sql.append("		  FROM gen_tl_workflow_info ");
		 sql.append(" WHERE wrin_ref_id = '"+wrinRefId+"' ");    
		 sql.append("   AND wrin_ref_type = '"+wrinRefType+"' ), ");
		 sql.append(" emp_for_wrin AS ( ");
		 sql.append(" SELECT e.empm_keyid, e.empm_name ");
		 sql.append(" FROM gen_tl_employeemst e	), ");
		 sql.append(" role_hierarchy_filtered AS ( SELECT frt_empm_keyid, frl_level AS selemprole, ");
		 sql.append(" parentflids || '-' || flid AS parentflids  FROM adm_tl_rolemst ");
		 sql.append(" INNER JOIN gen_tl_fnlnrolemap ON frl_role_keyid = role_keyid ");
		 sql.append(" INNER JOIN gen_tl_fnlnroleteam ON role_keyid = frt_role_keyid AND frl_keyid = frt_frl_keyid ");
		 sql.append(" INNER JOIN gen_mv_flidhierarchy h ON POSITION(frt_fnln_keyid IN parentflids || '-' || flid) > 0 ");
		 sql.append(" WHERE frt_empm_keyid = '"+empId+"' ");
		 sql.append(" AND POSITION('"+roleId+"' IN role_keyid) > 0 ");
		 sql.append(" AND flid = '"+flId+"'	), ");
		 sql.append(" base_rows AS ( ");
		 sql.append(" SELECT ");
		 sql.append(" a.frt_fnln_keyid, ");
		 sql.append(" a.wrin_keyid, ");
		 sql.append(" a.wrkd_keyid, ");
		 sql.append(" a.wrml_keyid, ");
		 sql.append(" a.role_keyid, ");
		 sql.append(" a.role_name, ");
		 sql.append(" a.empm_keyid,");
		 sql.append(" a.empm_name, ");
		 sql.append(" COALESCE(a.wrin_status, 'Pending') AS wrin_status, ");
		 sql.append(" a.wrin_date, ");
		 sql.append(" a.wrin_remarks, ");
		 sql.append(" a.frl_level AS role_level, ");
		 sql.append(" a.wrin_employee_id, ");
		 sql.append(" emp2.empm_name AS wrin_emp_name ");
		 sql.append(" FROM ( ");
		 sql.append("	    SELECT ");
		 sql.append(" 	      frt.frt_fnln_keyid, ");
		 sql.append("		      wi.wrin_keyid, ");
		 sql.append("		      wrkd.wrkd_keyid, ");
		 sql.append("	          ml.wrml_keyid, ");
		 sql.append("		      adm.role_keyid, ");         
		 sql.append("		      adm.role_name, ");
		 sql.append("	      frt.frt_empm_keyid AS empm_keyid, ");
		 sql.append("	      em.empm_name, ");
		 sql.append("	      wi.wrin_status, ");
		 sql.append("		      wi.wrin_date, ");
		 sql.append("		      wi.wrin_remarks, ");
		 sql.append("		      frl.frl_level, ");
		 sql.append("		      wi.wrin_employee_id ");
		 sql.append("		    FROM menu_links ml ");
		 sql.append("		    INNER JOIN gen_tl_workflowdtl wrkd ON wrkd.wrkd_keyid = ml.wrkd_keyid ");
		 sql.append("		    INNER JOIN adm_tl_rolemst adm ON ml.wrkd_stage = adm.role_keyid ");
		 sql.append("		    INNER JOIN gen_tl_fnlnrolemap frl ON frl_role_keyid = adm.role_keyid ");
		 sql.append("		    INNER JOIN gen_tl_fnlnroleteam frt ON adm.role_keyid = frt.frt_role_keyid AND frl.frl_keyid = frt.frt_frl_keyid ");
		 sql.append("		    INNER JOIN gen_tl_employeemst em ON frt.frt_empm_keyid = em.empm_keyid ");
		 sql.append("		    LEFT JOIN wf_info wi ON wi.wrin_wrml_keyid = ml.wrml_keyid ");
		 sql.append("		                         AND wi.wrin_wrkd_keyid = wrkd.wrkd_keyid ) a ");
		 sql.append("  LEFT JOIN emp_for_wrin emp2 ON emp2.empm_keyid = a.wrin_employee_id ), ");
		 sql.append("		joined_with_hierarchy AS ( ");
		 sql.append("		  SELECT b.*, ");
		 sql.append("		         CASE WHEN b.empm_keyid = f.frt_empm_keyid ");
		 sql.append("		               THEN CASE WHEN b.wrin_status IN ('Pending','E') THEN 'Y' ELSE 'N' END ");
		 sql.append("		               ELSE 'N' END AS enabl, ");
		 sql.append("		         CASE WHEN b.wrin_keyid IS NULL OR b.wrin_keyid = ' ' THEN b.empm_name ELSE b.wrin_emp_name END AS empm_name_final, ");
		 sql.append("		         CASE WHEN b.wrin_status IN ('A','R','E') THEN b.wrin_status ");
		 sql.append("	              WHEN b.wrin_employee_id = b.empm_keyid THEN b.wrin_status ");
		 sql.append("             ELSE 'Pending' END AS currentstat, ");
		 sql.append("		         2::numeric AS dataorder ");
		 sql.append("		  FROM base_rows b ");
		 sql.append("		  INNER JOIN role_hierarchy_filtered f ");
		 sql.append("		    ON POSITION(b.frt_fnln_keyid IN f.parentflids) > 0 ), ");
		
		 sql.append("		final_rows AS ( ");
		 sql.append("		  SELECT *, ");
		 sql.append("		         LAG(COALESCE(currentstat,'Pending')) OVER (PARTITION BY wrml_keyid ORDER BY role_level DESC) AS prevstat, ");
		 sql.append("	         LAG(role_level) OVER (PARTITION BY wrml_keyid ORDER BY role_level DESC) AS prevlevl, ");
		 sql.append("		         LAG(role_keyid) OVER (PARTITION BY wrml_keyid ORDER BY role_level DESC) AS prevroleid ");
		 sql.append("		  FROM joined_with_hierarchy ) ");
		 sql.append("	SELECT DISTINCT ON (role_level) ");
		 sql.append("  wrin_keyid, ");
		 sql.append("  wrkd_keyid, ");
		sql.append("		  wrml_keyid, ");
		sql.append("				  empm_keyid::varchar, ");
		if( enable != null && enable == 'N') {
			sql.append(" 'N' as enabl, ");
		}else {
			sql.append("  CASE ");
			sql.append(" WHEN enabl = 'Y' THEN ");
			sql.append("  CASE ");
			sql.append(" WHEN GREATEST(POSITION( '"+roleId+"' IN trim(role_keyid)), 0) = 0 THEN 'N' ");
			sql.append(" ELSE ");
			sql.append(" CASE ");
			sql.append(" WHEN trim(empm_keyid) = '"+empId+"' THEN ");
			sql.append(" CASE  ");
			sql.append(" WHEN COALESCE(prevstat, 'A') IN ('A', 'Submitted') THEN 'Y' ");
			sql.append(" WHEN role_level = COALESCE(prevlevl, role_level) THEN ");
			sql.append(" CASE WHEN role_keyid = prevroleid THEN 'Y' ELSE 'N' END ");
			sql.append(" ELSE 'N' ");
			sql.append(" END ");
			sql.append(" ELSE 'N' ");
			sql.append(" END ");
			sql.append(" END ");
			sql.append(" ELSE enabl ");
			sql.append(" END :: varchar AS enabl,");
		}
		
		//sql.append(" 'N' AS enabl, ");
		sql.append("  role_keyid::varchar, ");
		sql.append("		  ''::varchar AS editrow, ");
		sql.append("		  ''::varchar AS saverow, ");
		sql.append("		  role_name::varchar, ");
		sql.append("		  empm_name_final::varchar AS empm_name, ");
		sql.append("		  CASE currentstat WHEN 'A' THEN 'Accepted' WHEN 'R' THEN 'Rejected' WHEN 'E' THEN 'Rework' ELSE currentstat END::varchar AS currentstat, ");
		sql.append("		  wrin_date::date AS wrin_date, ");
		sql.append("		  wrin_remarks, ");
		sql.append("		  dataorder::numeric, ");
		sql.append("		  role_level ");
		sql.append("		FROM final_rows ");
		//sql.append("		ORDER BY    role_level DESC ");
		 
		 
		 /*
		 
		 
		
		//sql.append(" ON wrin_wrml_keyid = wrml_keyid AND wrin_wrkd_keyid = wrkd_keyid ");
		sql.append("     INNER JOIN adm_tl_rolemst ON wrkd_stage = role_keyid ");
		sql.append("     INNER JOIN gen_tl_fnlnrolemap ON FRL_ROLE_KEYID = ROLE_KEYID ");
		sql.append("     INNER JOIN gen_tl_fnlnroleteam ON role_keyid = frt_role_keyid AND FRL_KEYID = FRT_FRL_KEYID ");
		sql.append("     INNER JOIN gen_tl_employeemst ON frt_empm_keyid = empm_keyid ");
		sql.append("     LEFT JOIN gen_tl_workflow_info ");
		sql.append("  ON wrin_wrml_keyid = wrml_keyid ");
		sql.append("  AND wrin_wrkd_keyid = wrkd_keyid ");
		sql.append("  AND trim(wrin_ref_id) = '"+wrinRefId+"' ");
		sql.append("  AND trim(wrin_ref_type) = '"+wrinRefType+"' ");
		sql.append(" ) a ");
		
		
		sql.append(" INNER JOIN ( ");
		sql.append("     SELECT  ");
		sql.append("  frt_empm_keyid , ");
		sql.append("  FRL_LEVEL AS selemprole, ");
		sql.append("  parentflids || '-' || flid AS parentflids ");
		sql.append("     FROM adm_tl_rolemst ");
		sql.append("     INNER JOIN gen_tl_fnlnrolemap ON FRL_ROLE_KEYID = ROLE_KEYID ");
		sql.append("     INNER JOIN gen_tl_fnlnroleteam ON role_keyid = frt_role_keyid AND FRL_KEYID = FRT_FRL_KEYID ");
		sql.append("     INNER JOIN gen_mv_flidhierarchy ON POSITION(frt_fnln_keyid IN parentflids || '-' || flid) > 0 ");
		sql.append("     WHERE TRIM(frt_empm_keyid) = '"+empId+"' ");
		sql.append("       AND POSITION('"+roleId+"' IN trim(role_keyid)) > 0 ");
		sql.append("       AND trim(flid) = '"+flId+"' ");
		sql.append(" ) b ON POSITION(FRT_FNLN_KEYID IN parentflids) > 0 ");
		sql.append(" WHERE 1 = 1  ");
		sql.append(" ORDER BY role_level DESC ");
		sql.append("     ) subquery1 ");
		sql.append("     ORDER BY role_level DESC ");
		sql.append(" ) subquery2 ");
		//sql.append(" ORDER BY role_level DESC ");
		
			*/
		
		if(localTransCode.equals("OTHERS")){
			sql.append(" order by role_level DESC");			
		}
		else if(localTransCode.equals("DMAIC"))
		{
			sql.append(" order by dataorder, role_level DESC");
		}
		CommonMessage.debugMsg("sql="+sql);
		return sql.toString();
	}
}
