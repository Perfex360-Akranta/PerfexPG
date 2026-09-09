package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlProjectmaicMileDtlSql {

	public static final String TBL_KZN_TL_PROJECTMAIC_MILE_DTL = "KZN_TL_PROJ_MILESTONE_DTL";  

	TableFieldType [] kmmdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kmmm_keyid, milestone, description, targetdate, empm_keyid
		, status, remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon,kzpm_keyid
	}

	public TableFieldType[] getKmmdDbFields() {
		return kmmdDbFields;
	}

	public KznTlProjectmaicMileDtlSql()
	{
		kmmdDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			kmmdDbFields[ i ] = new TableFieldType();
		}
		kmmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KMMD_KEYID";
		kmmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.kmmm_keyid.ordinal() ].fieldName = "KMMD_KMMM_KEYID";
		kmmdDbFields[ tableFldConstants.kmmm_keyid.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.milestone.ordinal() ].fieldName = "KMMD_MILESTONE";
		kmmdDbFields[ tableFldConstants.milestone.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.description.ordinal() ].fieldName = "KMMD_DESCRIPTION";
		kmmdDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "KMMD_TARGETDATE";
		kmmdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		kmmdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "KMMD_EMPM_KEYID";
		kmmdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KMMD_STATUS";
		kmmdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KMMD_REMARKS";
		kmmdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KMMD_TEMPFIELD1";
		kmmdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KMMD_TEMPFIELD2";
		kmmdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KMMD_TEMPFIELD3";
		kmmdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KMMD_TEMPFIELD4";
		kmmdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KMMD_TEMPFIELD5";
		kmmdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KMMD_CREATEDBY";
		kmmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kmmdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KMMD_ACTIVE";
		kmmdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kmmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KMMD_CREATEDON";
		kmmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kmmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KMMD_MODIFIEDON";
		kmmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		kmmdDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KMMD_KZPM_KEYID";
		kmmdDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECTMAIC_MILE_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECTMAIC_MILE_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECTMAIC_MILE_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String deleteMilestone(String keyid) {
		// TODO Auto-generated method stub
		return "Delete from Kzn_Tl_PROJ_MILESTONE_DTL where kmmd_keyid ='"+keyid+"'";
	}
	public static String getUpdateMaster(String mpmsKeyid) {
		
		String sql=" UPDATE KZN_TL_PROJ_MILESTONE_MST "
				+ "SET KMMM_STATUS = ( "
				+ "    SELECT  "
				+ "        CASE  "
				+ "            WHEN COUNT(*) = SUM(CASE WHEN KMMD_STATUS = 'S' THEN 1 ELSE 0 END)  "
				+ "                THEN 'S' "
				+ "            WHEN SUM(CASE WHEN KMMD_STATUS IN ('C','S') THEN 1 ELSE 0 END) = COUNT(*)  "
				+ "                THEN 'C' "
				+ "            WHEN SUM(CASE WHEN KMMD_STATUS IN ('P','W') THEN 1 ELSE 0 END) = COUNT(*)  "
				+ "                THEN 'P' "
				+ "            WHEN SUM(CASE WHEN KMMD_STATUS IN ('W','C') THEN 1 ELSE 0 END) = COUNT(*)  "
				+ "                THEN 'W' "
				+ "            ELSE 'P' "
				+ "        END AS STATUS "
				+ "    FROM KZN_TL_PROJ_MILESTONE_DTL "
				+ "    WHERE KMMD_KMMM_KEYID = '"+mpmsKeyid+"' "
				+ ") WHERE KMMM_KEYID = '"+mpmsKeyid+"' ";
//			String sql= "update KZN_TL_PROJ_MILESTONE_MST SET KMMM_STATUS=(SELECT DECODE(COUNT(*)," +
//				" SUM(DECODE(KMMD_STATUS,'S',1,0)),'S',   " +
//				" SUM(DECODE(KMMD_STATUS,'C',1,'S',1,0)),'C',"+ 
//				" SUM(DECODE(KMMD_STATUS,'P',1,'W',1,0)),'P',"+ 
//				" SUM(DECODE(KMMD_STATUS,'P',1,'W',1,'W',1,0)),'P',"+ 
//                 " SUM(DECODE(KMMD_STATUS,'W',1,'C',1,0)),'W',"+
//                 "'P') AS STATUS FROM Kzn_Tl_PROJ_MILESTONE_DTL WHERE KMMD_KMMM_KEYID ='"+mpmsKeyid+"')";
		return sql;
	}
	public static String selectHistorySql(String dtlId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT KMMD_KEYID,KMMD_KMMM_KEYID,KMMD_MILESTONE,REPLACE(TO_CHAR(KMMD_TARGETDATE,'DD-MON-YYYY'),'01-JAN-1801') AS KMMD_TARGETDATE,");
		sb.append("A.Empm_Name,");
		sb.append("KMMD_REMARKS,DECODE(KMMD_STATUS,'P','PENDING','C','COMPLETED','W','WORK IN PROGRESS')");
		sb.append(" FROM Kzn_Tl_PROJ_MILESTONE_DTL_HIS,"+TableNames.TBL_GEN_TL_EMPLOYEEMST +" A,Kzn_Tl_PROJ_MILESTONE_MST WHERE  KMMD_KMMM_KEYID= KMMM_KEYID AND KMMD_EMPM_KEYID=A.Empm_Keyid AND KMMD_KEYID = '"+dtlId+"'");
		sb.append(" ORDER BY KMMD_KEYID");
		CommonMessage.debugMsg(sb.toString());
		return sb.toString();
	
	}	
}

