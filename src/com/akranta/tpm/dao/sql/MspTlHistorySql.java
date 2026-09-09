package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlHistorySql {

	public static final String TBL_MSP_TL_HISTORY = "MSP_TL_HISTORY";  

	TableFieldType [] mphiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, mspd_keyid, targetdate, completedate, responsibility, completedby
		, remarks, status, assignedto, tempfield9, tempfield8, tempfield7
		, tempfield6, tempfield5, tempfield4, tempfield3, tempfield2
		, tempfield1, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMphiDbFields() {
		return mphiDbFields;
	}

	public MspTlHistorySql()
	{
		mphiDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			mphiDbFields[ i ] = new TableFieldType();
		}
		mphiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MPHI_KEYID";
		mphiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.mspd_keyid.ordinal() ].fieldName = "MPHI_MSPD_KEYID";
		mphiDbFields[ tableFldConstants.mspd_keyid.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "MPHI_TARGETDATE";
		mphiDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		mphiDbFields[ tableFldConstants.completedate.ordinal() ].fieldName = "MPHI_COMPLETEDATE";
		mphiDbFields[ tableFldConstants.completedate.ordinal() ].fieldType = 'D';

		mphiDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "MPHI_RESPONSIBILITY";
		mphiDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "MPHI_COMPLETEDBY";
		mphiDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MPHI_REMARKS";
		mphiDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MPHI_STATUS";
		mphiDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		mphiDbFields[ tableFldConstants.assignedto.ordinal() ].fieldName = "MPHI_ASSIGNEDTO";
		mphiDbFields[ tableFldConstants.assignedto.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "MPHI_TEMPFIELD9";
		mphiDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MPHI_TEMPFIELD8";
		mphiDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MPHI_TEMPFIELD7";
		mphiDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MPHI_TEMPFIELD6";
		mphiDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MPHI_TEMPFIELD5";
		mphiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MPHI_TEMPFIELD4";
		mphiDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPHI_TEMPFIELD3";
		mphiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPHI_TEMPFIELD2";
		mphiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPHI_TEMPFIELD1";
		mphiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPHI_ACTIVE";
		mphiDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mphiDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPHI_CREATEDBY";
		mphiDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mphiDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPHI_CREATEDON";
		mphiDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mphiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPHI_MODIFIEDON";
		mphiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("getInsertHstSql : "+SqlUtils.getInsertSql(TBL_MSP_TL_HISTORY, fieldTypeArr, dataArray));
		return SqlUtils.getInsertSql(TBL_MSP_TL_HISTORY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_HISTORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_HISTORY ;		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String updateStatusSql(String status,String keyId,String completedDate,String compBy)
	{
		String sql = "UPDATE "+TBL_MSP_TL_HISTORY+ " SET MPHI_STATUS = '"+status+"'";
			   if(CommonFunctions.isValidKeyId(status))
			   {
				   if(status.equals("C"))
				   {
					   if(CommonFunctions.isValidKeyId(completedDate) && CommonFunctions.isValidKeyId(compBy))
					   {
						   if(completedDate.indexOf(":")>0)
						   {
							   sql += ",MPHI_COMPLETEDATE =  to_date( '"+completedDate+"','dd-Mon-yyyy hh24:mi:ss'),";
						   }
						   else
						     sql += ",MPHI_COMPLETEDATE = '"+completedDate+"',";
						     sql +="MPHI_COMPLETEDBY='"+compBy+"'";
					   }
				   }
			   }
			   sql += " WHERE MPHI_KEYID = '"+keyId+"'";
		return sql; 
	}
	public static String selectHistorySql(String dtlId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MPHI_KEYID,MPHI_MSPD_KEYID,MSPD_MILESTONE,REPLACE(TO_CHAR(MPHI_TARGETDATE,'DD-MON-YYYY'),'01-JAN-1801') AS MPHI_TARGETDATE,");
		sb.append("REPLACE(TO_CHAR(MPHI_COMPLETEDATE,'DD-MON-YYYY'),'31-DEC-2100') AS MPHI_COMPLETEDATE,C.Empm_Name,A.Empm_Name,B.Empm_Name,");
		sb.append("MPHI_REMARKS,DECODE(MPHI_STATUS,'P','PENDING','C','COMPLETED','W','WORK IN PROGRESS')");
		sb.append(" FROM "+TBL_MSP_TL_HISTORY+ ","+MspTlDtlSql.TBL_MSP_TL_DTL+","+TableNames.TBL_GEN_TL_EMPLOYEEMST +" A"+","+TableNames.TBL_GEN_TL_EMPLOYEEMST +" B,");
		sb.append(TableNames.TBL_GEN_TL_EMPLOYEEMST +" C WHERE MPHI_MSPD_KEYID=MSPD_KEYID(+) AND Mphi_Responsibility=A.Empm_Keyid(+) AND MPHI_COMPLETEDBY=B.EMPM_Keyid(+) AND MPHI_ASSIGNEDTO=C.EMPM_Keyid(+) AND MPHI_MSPD_KEYID = '"+dtlId+"'");
		sb.append(" ORDER BY MPHI_KEYID");
		CommonMessage.debugMsg(sb.toString());
		return sb.toString();
	
	}
	public static String selectHistoryIdsSql(String dtlId)
	{
		String sql = "SELECT MPHI_KEYID FROM Msp_Tl_History WHERE MPHI_MSPD_KEYID='"+dtlId+"'";
		return sql;
	}
	public static String getHistoryCountSql(String dtlId)
	{
		String sql = "SELECT count(*) FROM Msp_Tl_History WHERE MPHI_MSPD_KEYID='"+dtlId+"'";
		return sql;
	}
	public static String delHistorySql(String dtlId)
	{
		String sql = "DELETE FROM Msp_Tl_History WHERE MPHI_MSPD_KEYID='"+dtlId+"'";
		return sql;
	}

}

