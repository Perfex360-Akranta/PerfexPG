package com.akranta.tpm.dao.sql;

public class EntTlBatchcompletionSql {

	public static final String TBL_ENT_TL_BATCHCOMPLETION = "ENT_TL_BATCHCOMPLETION";  

	TableFieldType [] bcomDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prog_keyid, bach_keyid, start_date, end_date, status, entry_date
		, entry_by, remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBcomDbFields() {
		return bcomDbFields;
	}

	public EntTlBatchcompletionSql()
	{
		bcomDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			bcomDbFields[ i ] = new TableFieldType();
		}
		bcomDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BCOM_KEYID";
		bcomDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "BCOM_PROG_KEYID";
		bcomDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "BCOM_BACH_KEYID";
		bcomDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.start_date.ordinal() ].fieldName = "BCOM_START_DATE";
		bcomDbFields[ tableFldConstants.start_date.ordinal() ].fieldType = 'D';

		bcomDbFields[ tableFldConstants.end_date.ordinal() ].fieldName = "BCOM_END_DATE";
		bcomDbFields[ tableFldConstants.end_date.ordinal() ].fieldType = 'D';

		bcomDbFields[ tableFldConstants.status.ordinal() ].fieldName = "BCOM_STATUS";
		bcomDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.entry_date.ordinal() ].fieldName = "BCOM_ENTRY_DATE";
		bcomDbFields[ tableFldConstants.entry_date.ordinal() ].fieldType = 'D';

		bcomDbFields[ tableFldConstants.entry_by.ordinal() ].fieldName = "BCOM_ENTRY_BY";
		bcomDbFields[ tableFldConstants.entry_by.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BCOM_REMARKS";
		bcomDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "BCOM_TEMPFIELD1";
		bcomDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "BCOM_TEMPFIELD2";
		bcomDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BCOM_TEMPFIELD3";
		bcomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BCOM_TEMPFIELD4";
		bcomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BCOM_TEMPFIELD5";
		bcomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BCOM_ACTIVE";
		bcomDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bcomDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BCOM_CREATEDBY";
		bcomDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bcomDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BCOM_CREATEDON";
		bcomDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bcomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BCOM_MODIFIEDON";
		bcomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCHCOMPLETION, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCHCOMPLETION, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCHCOMPLETION ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getEmployeeCount(String batchId)
	{
		String sql = "SELECT COUNT(*) FROM ENT_TL_BATCH_EMPLOYEE_LINK";
		sql += " where BSTD_BACH_KEYID='"+batchId+"'";
		return sql;
	}
	public static String updateProgCal(String progId)
	{
		String sql = "update ENT_TL_PROG_CALENDAR set ECAL_STATUS = 'C' WHERE ECAL_PROG_ID = '"+progId +"'"; 
		return sql;
	}
	public static String updateProgCalendar(String progId,String toDate,String audienceCount)
	{
		String sql = "update ENT_TL_PROG_CALENDAR set ECAL_STATUS = 'C',ECAL_ACTUAL_AUDIENCECOUNT='"+audienceCount+"'";
			    sql += " WHERE ECAL_PROG_ID = '"+progId +"' AND To_Date('"+toDate+"','DD-MON-YYYY') Between Ecal_Plan_Fromdate And Ecal_Plan_Tilldate"; 
		return sql;
	}
	public static String updateBatchMst(String status,String progId, String completeDate)
	{
		String sql = "update ENT_TL_BATCHMST set BACH_STATUS = '"+status+"' ";
		sql += " , BACH_COMPLETEDATE ='"+completeDate+"'  ";
		sql += " WHERE BACH_KEYID = '"+progId +"'";
		
		return sql;
	}
	public static String updateBatchSchedule(String batchId)
	{
		String sql = "update ENT_TL_BATCH_SCHEDULE  set BSDL_STATUS = 'C' WHERE BSDL_BACH_KEYID = '"+batchId +"'"; 
		return sql;
	}
	public static String updateMailSent(String keyId)
	{
		String sql = "update ENT_TL_FEEDBACK_REQUESTDTL set FRQD_ISMAILSENT = 'Y' WHERE FRQD_KEYID IN( "+keyId +")"; 
		return sql;
	}
	
	/*public static String getMailSql(String batchId)
	{
		StringBuffer sb =new StringBuffer();
		sb.append("SELECT DISTINCT EEMD_EMPM_KEYID,EEMD_MAILID,EMPM_NAME,EMPM_CODE,FRQD_KEYID ");
		sb.append("FROM GEN_TL_EMPLOYEEMST,ENT_TL_EMPMANAGERDTL,ENT_TL_BATCH_EMPLOYEE_LINK,ENT_TL_FEEDBACK_REQUESTMST,");
		sb.append("ENT_TL_FEEDBACK_REQUESTDTL,ENT_TL_BATCHMST WHERE BSTD_BACH_KEYID = FRQM_BACH_KEYID ");
		sb.append("AND BSTD_EMPM_KEYID   = EEMD_EMPM_KEYID AND EEMD_EMPM_KEYID   = EMPM_KEYID ");
		sb.append("AND FRQM_BACH_KEYID   = BACH_KEYID AND FRQM_KEYID = FRQD_FRQM_KEYID ");
		sb.append("AND FRQM_BACH_KEYID   = '"+batchId+"'");
		return sb.toString();
	}*/
	public static String getMailSql(String batchId)
	{
		StringBuffer sb =new StringBuffer();
		sb.append("SELECT DISTINCT EEMD_EMPM_KEYID,EEMD_MAILID,EMPM_NAME,EMPM_CODE,FRQD_KEYID,BACH_NAME,PROG_NAME ");
		sb.append("FROM ENT_TL_FEEDBACK_REQUESTMST,ENT_TL_FEEDBACK_REQUESTDTL,ENT_TL_EMPMANAGERDTL,ENT_TL_BATCHMST,ENT_TL_PROGRAMMST,GEN_TL_EMPLOYEEMST");
		sb.append(" WHERE FRQM_KEYID = FRQD_FRQM_KEYID AND FRQD_EMPM_KEYID = EEMD_EMPM_KEYID");		
		sb.append(" AND EEMD_EMPM_KEYID = EMPM_KEYID");
		sb.append(" AND FRQM_BACH_KEYID = BACH_KEYID");
		sb.append(" AND BACH_PROG_KEYID = PROG_KEYID");
		sb.append(" AND FRQM_BACH_KEYID   = '"+batchId+"'");
		return sb.toString();
	}

	public static String getManagerMailSql(String batchId) {
		StringBuffer sb =new StringBuffer();
		sb.append(" SELECT A.EMPM_CODE EMPCODE, A.EMPM_NAME EMPNAME, B.EMPM_KEYID MANAGERID,  ");
		sb.append(" B.EMPM_NAME MANAGERNAME, EEMM_MAILID AS MANAGERMAILID,BACH_NAME,PROG_NAME ");
		sb.append(" FROM ENT_TL_BATCH_EMPLOYEE_LINK,ENT_TL_EMPMANAGERDTL,ENT_TL_EMPMANAGERMST,GEN_TL_EMPLOYEEMST A, ");
		sb.append(" GEN_TL_EMPLOYEEMST B, ENT_TL_BATCHMST,ENT_TL_PROGRAMMST WHERE ");
		sb.append(" BSTD_EMPM_KEYID = EEMD_EMPM_KEYID  AND EEMD_EEMM_KEYID = EEMM_KEYID ");
		sb.append(" AND EEMD_EMPM_KEYID = A.EMPM_KEYID  AND EEMM_MANAGER_ID = B.EMPM_KEYID  ");
    	sb.append(" AND BACH_PROG_KEYID = PROG_KEYID  AND BACH_KEYID = BSTD_BACH_KEYID ");
    	sb.append(" AND BSTD_BACH_KEYID = '"+batchId+"'");
		return sb.toString();
	}
	


}

