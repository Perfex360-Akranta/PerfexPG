package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
//import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlBatchEmployeeLinkSql {

	public static final String TBL_ENT_TL_BATCH_EMPLOYEE_LINK = "ENT_TL_BATCH_EMPLOYEE_LINK";  

	TableFieldType [] ent_DbFields = null;

	public enum   tableFldConstants
	{
		bstdkeyid, bstdbachkeyid, bstdempmkeyid, bstdefffromdate, bstdefftilldate
		, bstdtempfield1, bstdtempfield2, bstdtempfield3, bstdtempfield4
		, bstdtempfield5, bstdactive, bstdcreatedby, bstdcreatedon, bstdmodifiedon
	}

	public TableFieldType[] getEnt_DbFields() {
		return ent_DbFields;
	}	

	public EntTlBatchEmployeeLinkSql()
	{
		ent_DbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			ent_DbFields[ i ] = new TableFieldType();
		}
		ent_DbFields[ tableFldConstants.bstdkeyid.ordinal() ].fieldName = "Bstd_Keyid";
		ent_DbFields[ tableFldConstants.bstdkeyid.ordinal() ].fieldType = 'V';

		ent_DbFields[ tableFldConstants.bstdbachkeyid.ordinal() ].fieldName = "Bstd_Bach_Keyid";
		ent_DbFields[ tableFldConstants.bstdbachkeyid.ordinal() ].fieldType = 'V';

		ent_DbFields[ tableFldConstants.bstdempmkeyid.ordinal() ].fieldName = "Bstd_Empm_Keyid";
		ent_DbFields[ tableFldConstants.bstdempmkeyid.ordinal() ].fieldType = 'V';

		ent_DbFields[ tableFldConstants.bstdefffromdate.ordinal() ].fieldName = "Bstd_Eff_Fromdate";
		ent_DbFields[ tableFldConstants.bstdefffromdate.ordinal() ].fieldType = 'D';

		ent_DbFields[ tableFldConstants.bstdefftilldate.ordinal() ].fieldName = "Bstd_Eff_Tilldate";
		ent_DbFields[ tableFldConstants.bstdefftilldate.ordinal() ].fieldType = 'D';

		ent_DbFields[ tableFldConstants.bstdtempfield1.ordinal() ].fieldName = "Bstd_Tempfield1";
		ent_DbFields[ tableFldConstants.bstdtempfield1.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdtempfield2.ordinal() ].fieldName = "Bstd_Tempfield2";
		ent_DbFields[ tableFldConstants.bstdtempfield2.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdtempfield3.ordinal() ].fieldName = "Bstd_Tempfield3";
		ent_DbFields[ tableFldConstants.bstdtempfield3.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdtempfield4.ordinal() ].fieldName = "Bstd_Tempfield4";
		ent_DbFields[ tableFldConstants.bstdtempfield4.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdtempfield5.ordinal() ].fieldName = "Bstd_Tempfield5";
		ent_DbFields[ tableFldConstants.bstdtempfield5.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdactive.ordinal() ].fieldName = "Bstd_Active";
		ent_DbFields[ tableFldConstants.bstdactive.ordinal() ].fieldType = 'C';

		ent_DbFields[ tableFldConstants.bstdcreatedby.ordinal() ].fieldName = "Bstd_Createdby";
		ent_DbFields[ tableFldConstants.bstdcreatedby.ordinal() ].fieldType = 'V';

		ent_DbFields[ tableFldConstants.bstdcreatedon.ordinal() ].fieldName = "Bstd_Createdon";
		ent_DbFields[ tableFldConstants.bstdcreatedon.ordinal() ].fieldType = 'D';

		ent_DbFields[ tableFldConstants.bstdmodifiedon.ordinal() ].fieldName = "Bstd_Modifiedon";
		ent_DbFields[ tableFldConstants.bstdmodifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCH_EMPLOYEE_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCH_EMPLOYEE_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.bstdkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.bstdkeyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr,String batchId)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCH_EMPLOYEE_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.bstdbachkeyid.ordinal()].fieldName  +
			  " = '" +  batchId+ "'";
		return sql;
	}
	public static String getBatchEmployeeViewsql(EntTlBatchEmployeeLink entTlBatchEmployeeLink,String batchId,String deptId,String empFilter)
	{
			/*String sql = " SELECT DISTINCT DECODE(EMPM_KEYID, BSTD_EMPM_KEYID, '1', '0') AS TICK,BSTD_KEYID, EMPM_KEYID,'',EMPM_NAME,EMPM_CODE ";
			sql += " FROM " + TableNames.TBL_ENT_TL_BATCHMST + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST + "," + TableNames.TBL_ENT_TL_PROG_TARGET_SKILLS  ;
			sql += "," + TableNames.TBL_ENT_TL_ROLE_SKILL_LINK + "," +  TableNames.TBL_ENT_TL_ROLE_EMP_LINK + "," + TableNames.TBL_ENT_TL_ROLE_DEPT_LINKMST ;
			sql += "," +TBL_ENT_TL_BATCH_EMPLOYEE_LINK;
			sql += " WHERE EMPM_KEYID=EREL_EMPM_KEYID AND  ERSL_SKIL_KEYID =PRTS_SKIL_KEYID ";
			sql += " AND ERDL_KEYID=ERSL_ERDL_KEYID and EREL_ERDL_KEYID=ERDL_KEYID And BACH_PROG_KEYID=PRTS_PROG_KEYID and BSTD_EMPM_KEYID=EMPM_KEYID(+) and " ;
			sql += " BSTD_BACH_KEYID=BACH_KEYID(+) ";*/
			String[] empFltr = empFilter.split("~");
			//CommonMessage.debugMsg(empFltr[0]+" ~~~~ "+empFltr[1]+" ~~~~ "+empFltr[2]);
			String role =  null; 
			role = empFltr[0];
			String deptmnt = null; 
			deptmnt = empFltr[1] ;
			String manager = null; 
			manager = empFltr[2];
			CommonMessage.debugMsg(role+" ~~~~ "+deptmnt+" ~~~~ "+manager);
			String mgrSql =" AND  EMPM_KEYID in (select  EEMD_EMPM_KEYID from ENT_TL_EMPMANAGERDTL,ENT_TL_EMPMANAGERMST ";
			mgrSql += " where EEMD_EEMM_KEYID = EEMM_KEYID AND EEMM_MANAGER_ID= '" + manager + "') ";
		    /**for bring selected employess in front page*/
			//String[] deptBatch = deptId.split("/");
			//CommonMessage.debugMsg(deptBatch[0]+" -- -"+deptBatch[1]);
			String sql  = "  SELECT DECODE(BSTD_KEYID, NULL, 0, 1) AS TICK,BSTD_KEYID,EMPM_KEYID,EMPM_NAME,EMPM_CODE FROM ";
			sql += " (SELECT BSTD_KEYID, BSTD_BACH_KEYID || BSTD_EMPM_KEYID AS BB ";
			sql += " FROM ENT_TL_BATCH_EMPLOYEE_LINK) A, ";
			sql += " (SELECT DISTINCT BACH_KEYID,EMPM_KEYID,EMPM_NAME,EMPM_CODE, BACH_KEYID || EMPM_KEYID AS BE";
			sql += " FROM ENT_TL_BATCHMST,ENT_TL_PROG_TARGET_SKILLS,ENT_TL_ROLE_TOPIC_LINK,ENT_TL_ROLE_EMP_LINK, GEN_TL_EMPLOYEEMST ";
			sql += " WHERE BACH_PROG_KEYID=PRTS_PROG_KEYID ";
			sql += " AND PRTS_TOPI_KEYID  =RTLK_TOPI_KEYID ";
			sql += " AND EREL_EMPM_KEYID  =EMPM_KEYID ";
			sql += "  AND RTLK_RTAL_KEYID = EREL_RTAL_KEYID  ";
			sql += " AND BACH_ACTIVE = 'Y' AND PRTS_ACTIVE = 'Y'";
			sql += " AND RTLK_ACTIVE = 'Y' AND EREL_ACTIVE = 'Y'" ;
			
			if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(manager) && UIUtils.isValidKeyId(deptmnt))
				sql +=  mgrSql + " AND  EMPM_ROLEID = '" + role + "'  AND EMPM_DEPARTMENTID = '" + deptmnt + "'" ; 
			else if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(manager))
				sql +=  mgrSql + " AND  EMPM_ROLEID = '" + role + "'" ; 
			else if(UIUtils.isValidKeyId(deptmnt) && UIUtils.isValidKeyId(manager))
				sql +=  mgrSql + " AND EMPM_DEPARTMENTID = '" + deptmnt + "'" ; 
			else if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(deptmnt))
				sql += " AND EMPM_DEPARTMENTID = '" + deptmnt + "' AND  EMPM_ROLEID = '" + role + "'" ; 
			else if(UIUtils.isValidKeyId(role))
				sql += " AND  EMPM_ROLEID = '" + role + "'";
			else if(CommonFunctions.isValidKeyId(deptmnt)){
				sql += " AND EMPM_DEPARTMENTID= '" + deptmnt + "'";
			}	
			else if(UIUtils.isValidKeyId(manager))
				sql += mgrSql ;
			sql += ") B ";
			sql += " WHERE A.BB (+) = B.BE ";
			
			
		if(CommonFunctions.isValidKeyId(batchId)){
			sql += " AND BACH_KEYID= '" + batchId + "'";
		}
		if(deptId.equals("true")){
			sql += " AND  BSTD_KEYID <> '{}'";
		}	
		sql += " ORDER BY BSTD_KEYID ,BACH_KEYID,EMPM_KEYID";
		CommonMessage.debugMsg("sql"+sql);
		return sql;
	}
	
	public static String getBatchEmpDeleteSql(String batchId,String empId){
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ENT_TL_BATCH_EMPLOYEE_LINK_ WHERE Bstd_Bach_Keyid = '");
		sql.append(batchId);
		sql.append("' AND Bstd_Empm_Keyid = '");
		sql.append(empId);
		sql.append('\'');
		return sql.toString();
	}
	
	

}

