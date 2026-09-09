package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.utils.CommonMessage;
public class AdmTlUsermstSql {

	public static final String TBL_ADM_TL_USERMST = "ADM_TL_USERMST";

	TableFieldType[] usrmDbFields = null;

	public enum tableFldConstants {
		keyid, userpin, username, ccno, loginid, password, defaultpassword, securitypolicyid, designationid,
		departmentid, extensionphone, lastpwdchanged, lastlogindate, isuserlocked, isactive, isadministartor,
		ispwdlockenabled, istemplateuser, remarks, loginatempt, isvalidityreq, validfrom, validtill, createdby,
		createdon, modifiedon
	}

	public TableFieldType[] getUsrmDbFields() {
		return usrmDbFields;
	}

	public AdmTlUsermstSql() {
		usrmDbFields = new TableFieldType[26];
		for (int i = 0; i < 26; i++) {
			usrmDbFields[i] = new TableFieldType();
		}
		usrmDbFields[tableFldConstants.keyid.ordinal()].fieldName = "USRM_KEYID";
		usrmDbFields[tableFldConstants.keyid.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.userpin.ordinal()].fieldName = "USRM_USERPIN";
		usrmDbFields[tableFldConstants.userpin.ordinal()].fieldType = 'N';

		usrmDbFields[tableFldConstants.username.ordinal()].fieldName = "USRM_USERNAME";
		usrmDbFields[tableFldConstants.username.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.ccno.ordinal()].fieldName = "USRM_CCNO";
		usrmDbFields[tableFldConstants.ccno.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.loginid.ordinal()].fieldName = "USRM_LOGINID";
		usrmDbFields[tableFldConstants.loginid.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.password.ordinal()].fieldName = "USRM_PASSWORD";
		usrmDbFields[tableFldConstants.password.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.defaultpassword.ordinal()].fieldName = "USRM_DEFAULTPASSWORD";
		usrmDbFields[tableFldConstants.defaultpassword.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.securitypolicyid.ordinal()].fieldName = "USRM_SECURITYPOLICYID";
		usrmDbFields[tableFldConstants.securitypolicyid.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.designationid.ordinal()].fieldName = "USRM_DESIGNATIONID";
		usrmDbFields[tableFldConstants.designationid.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.departmentid.ordinal()].fieldName = "USRM_DEPARTMENTID";
		usrmDbFields[tableFldConstants.departmentid.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.extensionphone.ordinal()].fieldName = "USRM_EXTENSIONPHONE";
		usrmDbFields[tableFldConstants.extensionphone.ordinal()].fieldType = 'N';

		usrmDbFields[tableFldConstants.lastpwdchanged.ordinal()].fieldName = "USRM_LASTPWDCHANGED";
		usrmDbFields[tableFldConstants.lastpwdchanged.ordinal()].fieldType = 'D';

		usrmDbFields[tableFldConstants.lastlogindate.ordinal()].fieldName = "USRM_LASTLOGINDATE";
		usrmDbFields[tableFldConstants.lastlogindate.ordinal()].fieldType = 'D';

		usrmDbFields[tableFldConstants.isuserlocked.ordinal()].fieldName = "USRM_ISUSERLOCKED";
		usrmDbFields[tableFldConstants.isuserlocked.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.isactive.ordinal()].fieldName = "USRM_ISACTIVE";
		usrmDbFields[tableFldConstants.isactive.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.isadministartor.ordinal()].fieldName = "USRM_ISADMINISTARTOR";
		usrmDbFields[tableFldConstants.isadministartor.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.ispwdlockenabled.ordinal()].fieldName = "USRM_ISPWDLOCKENABLED";
		usrmDbFields[tableFldConstants.ispwdlockenabled.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.istemplateuser.ordinal()].fieldName = "USRM_ISTEMPLATEUSER";
		usrmDbFields[tableFldConstants.istemplateuser.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.remarks.ordinal()].fieldName = "USRM_REMARKS";
		usrmDbFields[tableFldConstants.remarks.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.loginatempt.ordinal()].fieldName = "USRM_LOGINATTEMPT";
		usrmDbFields[tableFldConstants.loginatempt.ordinal()].fieldType = 'N';

		usrmDbFields[tableFldConstants.isvalidityreq.ordinal()].fieldName = "USRM_ISVALIDITYREQ";
		usrmDbFields[tableFldConstants.isvalidityreq.ordinal()].fieldType = 'C';

		usrmDbFields[tableFldConstants.validfrom.ordinal()].fieldName = "USRM_VALIDFROM";
		usrmDbFields[tableFldConstants.validfrom.ordinal()].fieldType = 'D';

		usrmDbFields[tableFldConstants.validtill.ordinal()].fieldName = "USRM_VALIDTILL";
		usrmDbFields[tableFldConstants.validtill.ordinal()].fieldType = 'D';

		usrmDbFields[tableFldConstants.createdby.ordinal()].fieldName = "USRM_CREATEDBY";
		usrmDbFields[tableFldConstants.createdby.ordinal()].fieldType = 'V';

		usrmDbFields[tableFldConstants.createdon.ordinal()].fieldName = "USRM_CREATEDON";
		usrmDbFields[tableFldConstants.createdon.ordinal()].fieldType = 'D';

		usrmDbFields[tableFldConstants.modifiedon.ordinal()].fieldName = "USRM_MODIFIEDON";
		usrmDbFields[tableFldConstants.modifiedon.ordinal()].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
		return SqlUtils.getInsertSql(TBL_ADM_TL_USERMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_USERMST, fieldTypeArr, dataArray);

		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
		String sql = "DELETE from " + TBL_ADM_TL_USERMST;

		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getUserSqlByLoginId() {
		return "Select * from " + TBL_ADM_TL_USERMST + " Where upper(USRM_LOGINID) = upper( ? ) ";
	}

	public static String getUserSqlBykeyid() {
		return "Select * from " + TBL_ADM_TL_USERMST + " Where upper(USRM_KEYID) = upper( ? ) ";
	}

	public static String getUserLoginDetails() {
		StringBuffer sql = new StringBuffer();

		/*
		 * sql.
		 * append(" select USRM_LOGINID login_Id, USRM_USERNAME user_Name, EMPM_NAME as employee_Name,"
		 * ); sql.
		 * append(" DEPT_NAME as dept_Name,DESG_NAME as designation,USRM_PASSWORD as password,LGFR_DEFSYSPASSWORD as defaultpwd"
		 * ); sql.append(" from " ); sql.append( TBL_ADM_TL_USERMST +"," +
		 * TableNames.TBL_GEN_TL_EMPLOYEEMST + ","); sql.append(
		 * TableNames.TBL_GEN_TL_DEPARTMENTMST + "," +
		 * TableNames.TBL_GEN_TL_DESIGNATIONMST
		 * +","+TableNames.TBL_ADM_TL_LOGINFRAMEWORK); sql.
		 * append(" where EMPM_KEYID = USRM_CCNO and EMPM_DEPARTMENTID = DEPT_KEYID(+) and EMPM_DESIGNATIONID = DESG_KEYID(+) and usrm_keyid = ? "
		 * );
		 */
		sql.append(" SELECT ");
		sql.append("    USRM.USRM_LOGINID AS login_id, ");
		sql.append("    USRM.USRM_USERNAME AS user_name, ");
		sql.append("    EMPM.EMPM_NAME AS employee_name, ");
		sql.append("    DEPT.DEPT_NAME AS dept_name, ");
		sql.append("    DESG.DESG_NAME AS designation, ");
		sql.append("    USRM.USRM_PASSWORD AS password, ");
		sql.append("    LGFR.LGFR_DEFSYSPASSWORD AS defaultpwd ");
		sql.append(" FROM ADM_TL_USERMST USRM ");
		sql.append(" JOIN ");
		sql.append("    GEN_TL_EMPLOYEEMST EMPM ON EMPM.EMPM_KEYID = USRM.USRM_CCNO ");
		sql.append(" LEFT JOIN ");
		sql.append("    GEN_TL_DEPARTMENTMST DEPT ON EMPM.EMPM_DEPARTMENTID = DEPT.DEPT_KEYID ");
		sql.append(" LEFT JOIN ");
		sql.append("    GEN_TL_DESIGNATIONMST DESG ON EMPM.EMPM_DESIGNATIONID = DESG.DESG_KEYID ");
		sql.append(" JOIN ");
		sql.append("    ADM_TL_LOGINFRAMEWORK LGFR ON TRUE  ");
		sql.append(" WHERE  USRM.USRM_KEYID = ? ");
		return sql.toString();
	}

	public static String getUser() {
		return "Select USRM_KEYID,USRM_USERNAME,EMPM_CODE,USRM_LOGINID,USRM_PASSWORD,USRM_DEFAULTPASSWORD,DEPT_NAME,DESG_NAME,REPLACE(USRM_EXTENSIONPHONE,'-99','') from ADM_TL_USERMST,GEN_TL_EMPLOYEEMST,GEN_TL_DEPARTMENTMST,GEN_TL_DESIGNATIONMST WHERE ADM_TL_USERMST.USRM_CCNO= GEN_TL_EMPLOYEEMST.EMPM_KEYID AND ADM_TL_USERMST.USRM_DEPARTMENTID=GEN_TL_DEPARTMENTMST.DEPT_KEYID(+) AND ADM_TL_USERMST.USRM_DESIGNATIONID=GEN_TL_DESIGNATIONMST.DESG_KEYID(+) order by USRM_USERNAME ";
	}

	public static String getUserByKeyid() {
		return "Select * from " + TBL_ADM_TL_USERMST + " Where USRM_KEYID=?";
	}

	public static String checkpasswordhistory() {
		return " select COUNT(PWDH_PASSWORD) from  ADM_TL_PWDHISTORY where PWDH_USERID = ? AND pwdh_password = ? ";
	}

	public static String getUserByCcno(String ccNo) {
		// Query Change By Swetha - User Master
		return "SELECT EMPM_NAME, EMPM_DEPARTMENTID, EMPM_DESIGNATIONID, EMPM_CODE " + "FROM GEN_TL_EMPLOYEEMST e "
				+ "LEFT JOIN ADM_TL_USERMST u ON u.USRM_CCNO = e.EMPM_KEYID " + "WHERE e.EMPM_KEYID = '" + ccNo + "'";
		// return "Select EMPM_NAME,EMPM_DEPARTMENTID,EMPM_DESIGNATIONID,EMPM_CODE from
		// GEN_TL_EMPLOYEEMST ,ADM_TL_USERMST WHERE USRM_CCNO(+)=EMPM_KEYID AND
		// EMPM_KEYID='"+ccNo+"'";
	}

	public static String getUserNameByCCNO(String ccNo) {
		// TODO Auto-generated method stub
		return "Select USRM_USERNAME from ADM_TL_USERMST WHERE usrm_ccno='" + ccNo + "'";
	}

	/*
	 * public static String getUpdateSqlforpwdchange() {
	 * 
	 * StringBuffer sql =new StringBuffer();
	 * 
	 * sql.append(" Update ADM_TL_USERMST set USRM_PASSWORD = '" +
	 * admTlUsermst.getUsrm_password() + "',");
	 * sql.append(" USRM_DEFAULTPASSWORD = '"+
	 * admTlUsermst.getUsrm_defaultpassword() + "' , " );
	 * sql.append(" USRM_LASTPWDCHANGED =  to_date( '" +
	 * admTlUsermst.getUsrm_lastpwdchanged() + "','dd-Mon-yyyy hh24:mi:ss'),"); sql.
	 * append(" USRM_ISUSERLOCKED = 'N',USRM_ISACTIVE = 'Y', USRM_LOGINATTEMPT = 0 "
	 * ); sql.append(" where USRM_KEYID = '"+ admTlUsermst.getUsrm_keyid() + "'");
	 * 
	 * sql.append(" Update ADM_TL_USERMST set USRM_PASSWORD = ? ,");
	 * sql.append(" USRM_DEFAULTPASSWORD = ? , " );
	 * sql.append(" USRM_LASTPWDCHANGED =  to_date( ? ,'dd-MM-yyyy hh24:mi:ss'),");
	 * sql.
	 * append(" USRM_ISUSERLOCKED = 'N',USRM_ISACTIVE = 'Y', USRM_LOGINATTEMPT = 0 "
	 * ); sql.append(" where USRM_KEYID = ? "); return sql.toString(); }
	 */
	
public static String getUpdateSqlforpwdchange() {
		
		StringBuffer sql =new StringBuffer();
		
	 /*   sql.append(" Update ADM_TL_USERMST set USRM_PASSWORD = '" + admTlUsermst.getUsrm_password() + "',");
	    sql.append(" USRM_DEFAULTPASSWORD = '"+ admTlUsermst.getUsrm_defaultpassword() + "' , " );
	    sql.append(" USRM_LASTPWDCHANGED =  to_date( '" + admTlUsermst.getUsrm_lastpwdchanged() + "','dd-Mon-yyyy hh24:mi:ss'),");
	    sql.append(" USRM_ISUSERLOCKED = 'N',USRM_ISACTIVE = 'Y', USRM_LOGINATTEMPT = 0 " );
	    sql.append(" where USRM_KEYID = '"+ admTlUsermst.getUsrm_keyid() + "'");*/
	    
//	    sql.append(" Update ADM_TL_USERMST set USRM_PASSWORD = ? ,");
//	    sql.append(" USRM_DEFAULTPASSWORD = ? , " );
//	    sql.append(" USRM_LASTPWDCHANGED =  ?,");
//	    sql.append(" USRM_ISUSERLOCKED = 'N',USRM_ISACTIVE = 'Y', USRM_LOGINATTEMPT = 0 " );
//	    sql.append(" where USRM_KEYID = ? ");    		
		
		sql.append(" Update ADM_TL_USERMST set USRM_PASSWORD = ? ,");
	    sql.append(" USRM_DEFAULTPASSWORD = ? , " );
	    //sql.append(" USRM_LASTPWDCHANGED =  to_date( ? ,'dd-MM-yyyy hh24:mi:ss'),");
	    sql.append(" USRM_LASTPWDCHANGED =  ?,");
	    sql.append(" USRM_ISUSERLOCKED = 'N',USRM_ISACTIVE = 'Y', USRM_LOGINATTEMPT = 0 " );
	    sql.append(" where USRM_KEYID = ? ");
		return sql.toString();
	}

	public static String UpdateUserData(String LoginId) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE='N',USRM_ISVALIDITYREQ='Y' WHERE USRM_CCNO='" + LoginId
				+ "' ");
		CommonMessage.debugMsg("The User Master Sql:" + sql);
		return sql.toString();
	}

}
