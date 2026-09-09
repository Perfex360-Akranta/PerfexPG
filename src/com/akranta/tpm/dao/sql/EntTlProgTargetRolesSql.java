package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlProgTargetRolesSql {

	public static final String TBL_ENT_TL_PROG_TARGET_ROLES = "ENT_TL_PROG_TARGET_ROLES";  

	TableFieldType [] prtrDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prog_keyid, trar_keyid, eff_from_date, eff_till_date, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPrtrDbFields() {
		return prtrDbFields;
	}

	public EntTlProgTargetRolesSql()
	{
		prtrDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			prtrDbFields[ i ] = new TableFieldType();
		}
		prtrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PRTR_KEYID";
		prtrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		prtrDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "PRTR_PROG_KEYID";
		prtrDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		prtrDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldName = "PRTR_TRAR_KEYID";
		prtrDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldType = 'V';

		prtrDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldName = "PRTR_EFF_FROM_DATE";
		prtrDbFields[ tableFldConstants.eff_from_date.ordinal() ].fieldType = 'D';

		prtrDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldName = "PRTR_EFF_TILL_DATE";
		prtrDbFields[ tableFldConstants.eff_till_date.ordinal() ].fieldType = 'D';

		prtrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PRTR_TEMPFIELD1";
		prtrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PRTR_TEMPFIELD2";
		prtrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PRTR_TEMPFIELD3";
		prtrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PRTR_TEMPFIELD4";
		prtrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PRTR_TEMPFIELD5";
		prtrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PRTR_ACTIVE";
		prtrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		prtrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PRTR_CREATEDBY";
		prtrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		prtrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PRTR_CREATEDON";
		prtrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		prtrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PRTR_MODIFIEDON";
		prtrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_PROG_TARGET_ROLES, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_PROG_TARGET_ROLES, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_PROG_TARGET_ROLES ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String roleGrid(String progKeyId) {
		// TODO Auto-generated method stub
		String sql ="SELECT PRTR_KEYID,KEYID,RTAL_KEYID,PARENTNAMES,ROLENAME "
			+"FROM  "+TableNames.TBL_ENT_TL_PROG_TARGET_ROLES+","+TableNames.TBL_ENT_VW_TRAININGAREAROLE+","+TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH 
			+" WHERE   PRTR_TRAR_KEYID =RTAL_KEYID  AND RTAL_TRAR_KEYID = KEYID and PRTR_PROG_KEYID ='"+progKeyId+"'";
			
				
			
			
			
			/*"SELECT  DISTINCT PRTR_KEYID , trar_KEYID ,ERDL_DEPT_KEYID,DEPT_NAME,DEPT_ROLE_NAME  from "+TableNames.TBL_ENT_VW_DEPTROLEVW+
					","+TBL_ENT_TL_PROG_TARGET_ROLES+","+TableNames.TBL_GEN_TL_DEPARTMENTMST
					+" WHERE ERDL_KEYID = PRTR_ERDL_KEYID AND PRTR_PROG_KEYID='"+progKeyId+"' AND  ERDL_DEPT_KEYID= DEPT_KEYID";*/
		CommonMessage.debugMsg("Role Grid Data   :"+sql);
		return sql;
	}

	public static String getFormData() {
		// TODO Auto-generated method stub
		return "select * from " +TBL_ENT_TL_PROG_TARGET_ROLES +" where PRTR_PROG_KEYID = ? ";
	}

	public static String getDeleteRoleSql(String prtrKeyid) {
		// TODO Auto-generated method stub
		return "Delete from "+TBL_ENT_TL_PROG_TARGET_ROLES+" Where PRTR_KEYID = '"+prtrKeyid+"'";
	}

	public static String getChkRoleExist(String progKeyId) {
		// TODO Auto-generated method stub
		/*String sql = "SELECT  role_keyid FROM gen_tl_rolemst WHERE role_keyid IN ("+
          			 " SELECT ERDL_ROLE_KEYID FROM ent_tl_role_skill_link, "+
          			 " ENT_TL_ROLE_DEPT_LINKMST,ent_tl_prog_target_skills "+
          			 " WHERE ERSL_ERDL_KEYID = ERDL_KEYID  AND ersl_skil_keyid = prts_topi_keyid "+
                     " AND prts_prog_keyid = '"+progKeyId +"' AND ERDL_ACTIVE ='Y')";*/
		String sql = " SELECT  ROLE_KEYID FROM GEN_TL_ROLEMST WHERE ROLE_KEYID IN "+ 
					 "(SELECT RTAL_ROLE_KEYID FROM ENT_TL_ROLE_TOPIC_LINK,  ENT_TL_ROLE_TRAININGAREA_LINK,ENT_TL_PROG_TARGET_SKILLS"+
					 " WHERE RTLK_RTAL_KEYID = RTAL_KEYID  AND RTLK_TOPI_KEYID = PRTS_TOPI_KEYID  AND PRTS_PROG_KEYID = '"+progKeyId +"'"+
					 "AND RTAL_ACTIVE ='Y')";
                     /*"select ROLE_KEYID from GEN_TL_ROLEMST where ROLE_KEYID in ( "+
					 "select ERSL_ERDL_KEYID from  ENT_TL_ROLE_SKILL_LINK, ENT_TL_PROG_TARGET_SKILLS"+
					 " where ERSL_SKIL_KEYID = PRTS_SKIL_KEYID and PRTS_PROG_KEYID = '"+progKeyId +"' )";*/
		CommonMessage.debugMsg("ChkRoleExist   :"+sql);
		return sql;
	}

}

