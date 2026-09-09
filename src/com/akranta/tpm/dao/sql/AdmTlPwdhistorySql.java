package com.akranta.tpm.dao.sql;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import net.sf.json.JSONObject;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class AdmTlPwdhistorySql {

	public static final String TBL_ADM_TL_PWDHISTORY = "ADM_TL_PWDHISTORY";  

	TableFieldType [] pwdhDbFields = null;

	public enum   tableFldConstants
	{
		keyid, userid, passwordno, password, lastpwdchangedon, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPwdhDbFields() {
		return pwdhDbFields;
	}

	public AdmTlPwdhistorySql()
	{
		pwdhDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			pwdhDbFields[ i ] = new TableFieldType();
		}
		pwdhDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PWDH_KEYID";
		pwdhDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pwdhDbFields[ tableFldConstants.userid.ordinal() ].fieldName = "PWDH_USERID";
		pwdhDbFields[ tableFldConstants.userid.ordinal() ].fieldType = 'V';

		pwdhDbFields[ tableFldConstants.passwordno.ordinal() ].fieldName = "PWDH_PASSWORDNO";
		pwdhDbFields[ tableFldConstants.passwordno.ordinal() ].fieldType = 'N';

		pwdhDbFields[ tableFldConstants.password.ordinal() ].fieldName = "PWDH_PASSWORD";
		pwdhDbFields[ tableFldConstants.password.ordinal() ].fieldType = 'V';

		pwdhDbFields[ tableFldConstants.lastpwdchangedon.ordinal() ].fieldName = "PWDH_LASTPWDCHANGEDON";
		pwdhDbFields[ tableFldConstants.lastpwdchangedon.ordinal() ].fieldType = 'D';

		pwdhDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PWDH_ACTIVE";
		pwdhDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pwdhDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PWDH_CREATEDBY";
		pwdhDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pwdhDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PWDH_CREATEDON";
		pwdhDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pwdhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PWDH_MODIFIEDON";
		pwdhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_PWDHISTORY, fieldTypeArr, dataArray);
		
	
	}
	
	/*
	 * public static String getInsertSql() { String dates =
	 * CommonFunctions.getDate(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("dd-MM-YYYY"); Calendar now = Calendar.getInstance(); String
	 * dateTime=sdf.format(now.getTime());
	 * 
	 * CommonMessage.debugMsg("Date ime in sid ethe sql"+dateTime);
	 * 
	 * // return SqlUtils.getInsertSql(TBL_ADM_TL_PWDHISTORY, fieldTypeArr,
	 * dataArray); return
	 * " insert into ADM_TL_PWDHISTORY values(?,?,?,?,'"+dateTime+"','Y',?,'"+
	 * dateTime+"','"+dateTime+"')";
	 * 
	 * }
	 */
	
	public static String getInsertSql()
	{
		String dates = CommonFunctions.getDate();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY",Locale.ENGLISH);
			Calendar now = Calendar.getInstance();
		    //String dateTime=sdf.format(now.getTime());
			
			String dateTime=CommonFunctions.pg_dateTimeNow();
		
		CommonMessage.debugMsg("Date ime in sid ethe sql"+dateTime);

	//	return SqlUtils.getInsertSql(TBL_ADM_TL_PWDHISTORY, fieldTypeArr, dataArray);
		return " insert into ADM_TL_PWDHISTORY values(?,?,?,?,'"+dateTime+"','Y',?,'"+dateTime+"','"+dateTime+"')";
	
	}


	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_PWDHISTORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_PWDHISTORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	//Change By Swetha - For User Master - 17th October//
	public static String getPwdNo(String usrm_keyid) {
		 
		String sql = "SELECT COALESCE(MAX(PWDH_PASSWORDNO), 0) + 1 " +
	             "FROM " + TBL_ADM_TL_PWDHISTORY + " " +
	             "WHERE PWDH_USERID = '" + usrm_keyid + "'";
		return sql;
	}
	//Change By Swetha - For User Master - 17th October//

	public static String getUpdateHistorySql(String usrm_keyid) {
		
		String sql = " UPDATE "+TBL_ADM_TL_PWDHISTORY +" SET PWDH_PASSWORDNO = PWDH_PASSWORDNO+1 WHERE PWDH_USERID = '"+usrm_keyid+"'";
		//String sql = " UPDATE "+TBL_ADM_TL_PWDHISTORY +" SET PWDH_PASSWORDNO = PWDH_PASSWORDNO+1 WHERE PWDH_USERID = ? ";
		return sql;
		
	}

	public static String getUpdateHistorySql() {
		
		//String sql = " UPDATE "+TBL_ADM_TL_PWDHISTORY +" SET PWDH_PASSWORDNO = PWDH_PASSWORDNO+1 WHERE PWDH_USERID = '"+usrm_keyid+"'";
		String sql = " UPDATE "+TBL_ADM_TL_PWDHISTORY +" SET PWDH_PASSWORDNO = PWDH_PASSWORDNO+1 WHERE PWDH_USERID = ? ";
		return sql;
		
	}

	public static String getPwdHistoryRemember() {
		return " SELECT LGFR_PASSHISTORYREMEMBER FROM "+TableNames.TBL_ADM_TL_LOGINFRAMEWORK + " WHERE LGFR_ACTIVE ='Y' ";
	}

	public static String getDeleteHistorySql(String usrm_keyid, String remVal) {
		
		return "DELETE FROM ADM_TL_PWDHISTORY WHERE PWDH_PASSWORDNO > "+remVal+" AND PWDH_USERID = '"+usrm_keyid+"'";
		
		//return "DELETE FROM ADM_TL_PWDHISTORY WHERE PWDH_PASSWORDNO > ? AND PWDH_USERID = ? ";
		
		
	}
	
	public static String getDeleteHistorySql() {
		
		//return "DELETE FROM ADM_TL_PWDHISTORY WHERE PWDH_PASSWORDNO > "+remVal+" AND PWDH_USERID = '"+usrm_keyid+"'";
		
		//return "DELETE FROM ADM_TL_PWDHISTORY WHERE PWDH_PASSWORDNO > ? AND PWDH_USERID = ? ";	
		return "DELETE FROM ADM_TL_PWDHISTORY WHERE PWDH_PASSWORDNO > ?::INTEGER AND PWDH_USERID = ? ";
		
	}
	

	public static String getPwdExpires(String user) {
		
		/*sql.append( " SELECT DATEDIFF,DECODE(LGFR_PASSNEVEREXPIRES, 'N', GRACE+EXPIRES, GRACE+PENDING) AS GRACE, " );
		sql.append(" DECODE(LGFR_PASSNEVEREXPIRES, 'N', LIMITPRIOD+EXPIRES, PENDING) AS LIMITPRIOD,EXPIRES,LIMITPRIOD AS ACTlIMIT,GRACE AS ACTGRACE FROM( ");
		sql.append(" SELECT  TO_DATE(SYSDATE,'DD-MON-YYYY')-TO_DATE(USRM_LASTPWDCHANGED,'DD-MON-YYYY') AS DATEDIFF, ");
		sql.append(" LGFR_PASSGRACEPERIOD AS GRACE,LGFR_PASSINTIMATION AS LIMITPRIOD,LGFR_PASSCHANGEGAP AS EXPIRES, ");
		sql.append(" LGFR_PASSCHANGEGAP-(TO_DATE(SYSDATE,'DD-MON-YYYY')-TO_DATE(USRM_LASTPWDCHANGED,'DD-MON-YYYY')) AS PENDING,LGFR_PASSNEVEREXPIRES "); 
		sql.append(" FROM ADM_TL_USERMST,ADM_TL_LOGINFRAMEWORK WHERE USRM_LOGINID = '"+user+"') ");
*/
		StringBuffer sql = new StringBuffer();
	
	/*	sql.append( " SELECT DECODE (LGFR_PASSNEVEREXPIRES,'Y', 'N',  LOCK1 ) AS GRACE, "); 
		sql.append( " DECODE (LGFR_PASSNEVEREXPIRES,'Y', 'N',  INTIMA ) AS INTIMATION,USERLOCK,INTIMATIONPENDING ");
		sql.append( " FROM ( SELECT  LGFR_PASSNEVEREXPIRES," );
		sql.append( " DECODE(INTIMATIONPENDING,INTIMATIONPRIOD,'Y',DECODE(GREATEST(INTIMATIONPENDING,INTIMATIONPRIOD),INTIMATIONPENDING,'N',INTIMATIONPRIOD,'Y')) AS INTIMA, ");
		sql.append( " CASE GRACE+INTIMATIONPENDING WHEN LEAST(GRACE+INTIMATIONPENDING,-1) THEN 'Y' ELSE 'N' END AS LOCK1, INTIMATIONPENDING, GRACE+INTIMATIONPENDING AS USERLOCK,GRACE,INTIMATIONPRIOD ");
		sql.append( " FROM ( SELECT TO_DATE (SYSDATE, 'DD-MON-YYYY') - TO_DATE (USRM_LASTPWDCHANGED, 'DD-MON-YYYY') AS pwdChangeDATEDIFF, ");
		sql.append( " LGFR_PASSGRACEPERIOD AS GRACE, LGFR_PASSINTIMATION AS INTIMATIONPRIOD, LGFR_PASSCHANGEGAP AS EXPIRES, "); 
		sql.append( " LGFR_PASSCHANGEGAP - (  TO_DATE (SYSDATE, 'DD-MON-YYYY') - TO_DATE (USRM_LASTPWDCHANGED, 'DD-MON-YYYY')) AS INTIMATIONPENDING, ");    
		sql.append( " LGFR_PASSNEVEREXPIRES  FROM  ADM_TL_USERMST, ADM_TL_LOGINFRAMEWORK WHERE LGFR_ACTIVE='Y' AND  upper(USRM_LOGINID) = upper('"+user+"')))");
		*/
		sql.append( " SELECT ");
		sql.append( "    CASE ");
		sql.append( "        WHEN LGFR_PASSNEVEREXPIRES = 'Y' THEN 'N' ");
		sql.append( "        ELSE LOCK1 ");
		sql.append( "    END AS GRACE, ");
		sql.append( "  CASE ");
		sql.append( "        WHEN LGFR_PASSNEVEREXPIRES = 'Y' THEN 'N'  ");
		sql.append( "        ELSE INTIMA ");
		sql.append( "    END AS INTIMATION, ");
		sql.append( "   USERLOCK, ");
		sql.append( "    INTIMATIONPENDING ");
		sql.append( " FROM (  SELECT   LGFR_PASSNEVEREXPIRES, ");
		sql.append( "        CASE ");
		sql.append( "            WHEN INTIMATIONPENDING = INTIMATIONPRIOD THEN 'Y' ");
		sql.append( "            WHEN GREATEST(INTIMATIONPENDING, INTIMATIONPRIOD) = INTIMATIONPENDING THEN 'N' ");
		sql.append( "            WHEN INTIMATIONPRIOD = INTIMATIONPRIOD THEN 'Y' ");
		sql.append( "            ELSE NULL ");
		sql.append( "        END AS INTIMA, ");
		sql.append( "        CASE ");
		sql.append( "            WHEN (GRACE + INTIMATIONPENDING) = LEAST(GRACE + INTIMATIONPENDING, -1) THEN 'Y' ");
		sql.append( "            ELSE 'N' ");
		sql.append( "        END AS LOCK1,       INTIMATIONPENDING, ");
		sql.append( "        GRACE + INTIMATIONPENDING AS USERLOCK, ");
		sql.append( "        GRACE, ");
		sql.append( "        INTIMATIONPRIOD ");
		sql.append( "    FROM ( ");
		sql.append( "        SELECT  CURRENT_DATE - USRM_LASTPWDCHANGED::date AS pwdChangeDATEDIFF, ");
		sql.append( "            LGFR_PASSGRACEPERIOD AS GRACE, LGFR_PASSINTIMATION AS INTIMATIONPRIOD, LGFR_PASSCHANGEGAP AS EXPIRES, LGFR_PASSCHANGEGAP - (CURRENT_DATE - USRM_LASTPWDCHANGED::date) AS INTIMATIONPENDING, ");
		sql.append( "            LGFR_PASSNEVEREXPIRES FROM ADM_TL_USERMST  JOIN ADM_TL_LOGINFRAMEWORK ON 1=1 ");
		sql.append( "        WHERE LGFR_ACTIVE = 'Y' AND upper(USRM_LOGINID) = upper('"+user.trim()+"') ");
		sql.append( "   ) inner_sub ) outer_sub "); 
		CommonMessage.debugMsg("qll :::;"+sql.toString());     
		return sql.toString();
	}

	public static String lockUserAcc(String user) {
		
		return "UPDATE ADM_TL_USERMST SET  USRM_ISACTIVE = 'N' WHERE USRM_LOGINID = '"+user+"'";
	}

}

