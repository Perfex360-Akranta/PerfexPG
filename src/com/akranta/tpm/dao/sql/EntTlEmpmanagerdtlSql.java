package com.akranta.tpm.dao.sql;

import java.util.List;
import com.akranta.tpm.utils.CommonMessage;
public class EntTlEmpmanagerdtlSql {

	public static final String TBL_ENT_TL_EMPMANAGERDTL = "ENT_TL_EMPMANAGERDTL";  

	TableFieldType [] eemdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, eemm_keyid, empm_keyid, mailid, mobileno, tempfiled1, tempfiled2
		, tempfiled3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEemdDbFields() {
		return eemdDbFields;
	}

	public EntTlEmpmanagerdtlSql()
	{
		eemdDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			eemdDbFields[ i ] = new TableFieldType();
		}
		eemdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EEMD_KEYID";
		eemdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		eemdDbFields[ tableFldConstants.eemm_keyid.ordinal() ].fieldName = "EEMD_EEMM_KEYID";
		eemdDbFields[ tableFldConstants.eemm_keyid.ordinal() ].fieldType = 'V';

		eemdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "EEMD_EMPM_KEYID";
		eemdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		eemdDbFields[ tableFldConstants.mailid.ordinal() ].fieldName = "EEMD_MAILID";
		eemdDbFields[ tableFldConstants.mailid.ordinal() ].fieldType = 'V';

		eemdDbFields[ tableFldConstants.mobileno.ordinal() ].fieldName = "EEMD_MOBILENO";
		eemdDbFields[ tableFldConstants.mobileno.ordinal() ].fieldType = 'N';

		eemdDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "EEMD_TEMPFILED1";
		eemdDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'C';

		eemdDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "EEMD_TEMPFILED2";
		eemdDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		eemdDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "EEMD_TEMPFILED3";
		eemdDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		eemdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EEMD_ACTIVE";
		eemdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		eemdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EEMD_CREATEDBY";
		eemdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		eemdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EEMD_CREATEDON";
		eemdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		eemdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EEMD_MODIFIEDON";
		eemdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_EMPMANAGERDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_EMPMANAGERDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_EMPMANAGERDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectSql()
	{
		
		String sql = "SELECT * from " + TBL_ENT_TL_EMPMANAGERDTL + " where EEMD_EMPM_KEYID= ?";
		 
		CommonMessage.debugMsg(sql);
		return sql;
	}
	
	public static String getEmployeeDetsql(String ManIdField,String EmpIdField)
	{
		

		//String sql = "SELECT DISTINCT EEMD_EMPM_KEYID,EEMD_MAILID ,EEMM_MAILID from " + TBL_ENT_TL_EMPMANAGERDTL + " ,ENT_TL_EMPMANAGERMST where EEMD_EMPM_KEYID= '" + EmpIdField + "' OR EEMM_MANAGER_ID = '" + ManIdField + "'";
		//String sql = " SELECT DISTINCT (SELECT EMPM_NAME FROM GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID IN (SELECT DISTINCT EEMD_EMPM_KEYID FROM ENT_TL_EMPMANAGERMST WHERE EEMM_MANAGER_ID = '" + ManIdField + "') ) AS EMPM_NAME," ;
				//sql +=	" EEMD_MAILID ,EEMM_MAILID from ENT_TL_EMPMANAGERDTL ,ENT_TL_EMPMANAGERMST where EEMD_EMPM_KEYID= 'null' OR EEMM_MANAGER_ID = '" + ManIdField + "'";
		String sql = " SELECT empm_keyid  as EmpKeyId, empm_name as EmpName, eemd_mailid as DetMailId ,EEMM_MAILID as ManMailId,EEMM_KEYID as ManKeyId,EEMD_KEYID as DetKeyId" ;
		sql += " FROM ent_tl_empmanagerdtl,gen_tl_employeemst,ent_tl_empmanagermst "; 
		sql += " WHERE EEMD_EMPM_KEYID = empm_keyid and EEMD_EEMM_KEYID = EEMM_KEYID and EEMM_MANAGER_ID = '" + ManIdField + "'" ;
	if(EmpIdField != null)
		sql += " and eemd_empm_keyid = '" + EmpIdField + "'";
		CommonMessage.debugMsg(sql);
		return sql;
	}

	public static String getEmployeeDetViewsql()
	{
		String sql = " SELECT  empm_keyid as EmpKeyId, eemm_keyid as ManKeyId,empm_name as EmpName, eemm_mailid as ManMailId,  COUNT (EEMD_KEYID) ";
		sql += " FROM ent_tl_empmanagerdtl, ent_tl_empmanagermst, gen_tl_employeemst ";
		sql += " WHERE EEMD_EEMM_KEYID(+) = eemm_keyid AND  EEMM_MANAGER_ID = empm_keyid ";
		sql += " GROUP BY empm_keyid,eemm_keyid, empm_name, eemm_mailid ";
		CommonMessage.debugMsg(sql);
		return sql;
	}

}

