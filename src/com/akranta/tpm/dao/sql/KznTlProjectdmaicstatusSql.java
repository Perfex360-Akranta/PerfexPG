package com.akranta.tpm.dao.sql;

public class KznTlProjectdmaicstatusSql {

	public static final String TBL_KZN_TL_PROJECTDMAICSTATUS = "KZN_TL_PROJECTDMAICSTATUS";  

	TableFieldType [] kpdsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, stage, verifieddate, verifiedby, remarks, status
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getKpdsDbFields() {
		return kpdsDbFields;
	}

	public KznTlProjectdmaicstatusSql()
	{
		kpdsDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			kpdsDbFields[ i ] = new TableFieldType();
		}
		kpdsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPDS_KEYID";
		kpdsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KPDS_KZPM_KEYID";
		kpdsDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.stage.ordinal() ].fieldName = "KPDS_STAGE";
		kpdsDbFields[ tableFldConstants.stage.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.verifieddate.ordinal() ].fieldName = "KPDS_VERIFIEDDATE";
		kpdsDbFields[ tableFldConstants.verifieddate.ordinal() ].fieldType = 'D';

		kpdsDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "KPDS_VERIFIEDBY";
		kpdsDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KPDS_REMARKS";
		kpdsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KPDS_STATUS";
		kpdsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPDS_TEMPFIELD1";
		kpdsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPDS_TEMPFIELD2";
		kpdsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPDS_TEMPFIELD3";
		kpdsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPDS_TEMPFIELD4";
		kpdsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPDS_TEMPFIELD5";
		kpdsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPDS_CREATEDBY";
		kpdsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kpdsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPDS_ACTIVE";
		kpdsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kpdsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPDS_CREATEDON";
		kpdsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kpdsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPDS_MODIFIEDON";
		kpdsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECTDMAICSTATUS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECTDMAICSTATUS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECTDMAICSTATUS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " IN (" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + ")";
		return sql;
	}
	public static String getMaicStageSql(String kznKeyId)
	{
		String sql =   " SELECT LISTAGG(STAGE) WITHIN GROUP (ORDER BY STAGE)  FROM (";
		sql += " 	SELECT KPDS_KZPM_KEYID,KPDS_STAGE||MAX(KPDS_STATUS) STAGE FROM KZN_TL_PROJECTDMAICSTATUS WHERE  KPDS_STAGE='M' GROUP BY KPDS_KZPM_KEYID,KPDS_STAGE";
		sql += " UNION ";
		sql += " SELECT KPDS_KZPM_KEYID,KPDS_STAGE||MAX(KPDS_STATUS) STAGE FROM KZN_TL_PROJECTDMAICSTATUS WHERE  KPDS_STAGE='A' GROUP BY KPDS_KZPM_KEYID,KPDS_STAGE ";
		sql += " UNION ";
		sql += " SELECT KPDS_KZPM_KEYID,KPDS_STAGE||MAX(KPDS_STATUS) STAGE FROM KZN_TL_PROJECTDMAICSTATUS WHERE  KPDS_STAGE='I' GROUP BY KPDS_KZPM_KEYID,KPDS_STAGE ";
		sql += " UNION ";
		sql += " SELECT KPDS_KZPM_KEYID,KPDS_STAGE||MAX(KPDS_STATUS) STAGE FROM KZN_TL_PROJECTDMAICSTATUS WHERE  KPDS_STAGE='C' GROUP BY KPDS_KZPM_KEYID,KPDS_STAGE ";
		sql += " )WHERE KPDS_KZPM_KEYID='"+kznKeyId+"' ";
		return sql;
	}
	public static String getMaicStageSql(String kznKeyId,String stage)
	{
		String sql = " SELECT REPLACE(MAX(KPDS_STATUS),'C','W') STAGE FROM KZN_TL_PROJECTDMAICSTATUS";
		sql += " WHERE  KPDS_STAGE='"+stage+"' AND KPDS_KZPM_KEYID='"+kznKeyId+"' ";
		sql += " GROUP BY KPDS_STAGE ";
		return sql;
	}
	public static String getDefineStageSql(String kznKeyId)
	{
		String sql =   " SELECT LISTAGG(STAGE) WITHIN GROUP (ORDER BY STAGE)  FROM (";
		sql += " 	SELECT KPDS_KZPM_KEYID,MAX(KPDS_STATUS) STAGE FROM KZN_TL_PROJECTDMAICSTATUS WHERE  KPDS_STAGE='D' GROUP BY KPDS_KZPM_KEYID,KPDS_STAGE";
		sql += " )WHERE KPDS_KZPM_KEYID='"+kznKeyId+"' ";
		return sql;
	}
	
	public static String getdmcWorkFlowStatus(String refId,String refType,String transCode) {
		// TODO Auto-generated method stub
		String Sql="";

		Sql += "SELECT COUNT(*) "
			+ "FROM GEN_TL_WORKFLOW_INFO "
				+ "WHERE 1 = 1  "
				+ "  AND WRIN_REF_TYPE = '"+refType+"' "
				+ "  AND WRIN_REF_ID = '"+refId+"'  "
				+ "  and wrin_employee_id in(select dfiw_kkchampion from GEN_TL_DMCFIPWORKFLOW "
				+ " where dfiw_fipno =  '"+refId+"'  ) "
				+ " AND WRIN_ROLE_ID = 'AROL0060' "
				+ "  AND WRIN_STATUS = 'A'; ";
		return Sql;		
	}	
	public static String getWorkFlowStatus(String refId,String refType,String transCode) {
		// TODO Auto-generated method stub
		String Sql="";
//		Sql+="SELECT COUNT(*) FROM GEN_TL_WORKFLOW_INFO WHERE 1=1 ";
//		Sql+=" AND WRIN_REF_TYPE='"+refType+"' ";
//		Sql+=" AND WRIN_REF_ID='"+refId+"'    ";
//		Sql+=" AND WRIN_ROLE_ID IN ( ";
//		Sql+=" SELECT ROLE_KEYID FROM ( ";
//		Sql+=" Select ROLE_KEYID, ROLE_NAME,WRKD_KEYID, ";
//		Sql+=" nvl(WRIN_STATUS,'Pending') WRIN_STATUS,WRIN_DATE,WRIN_REMARKS,ROLE_LEVEL from ";
//		Sql+=" GEN_TL_WORKFLOW_INFO,GEN_TL_WORKFLOW_MENU_LINK,GEN_TL_WORKFLOWdtl,adm_tl_ROLEMST ";
//		Sql+=" where WRIN_WRML_KEYID (+)= WRML_KEYID  AND WRML_WRKM_KEYID = WRKD_WRKM_KEYID  AND WRKD_STAGE = ROLE_KEYID ";  
//		Sql+=" and WRML_TRANS_CODE = '"+transCode+"'   ";
//		Sql+=" and WRIN_REF_ID(+) = '"+refId+"' and WRIN_REF_TYPE(+) = '"+refType+"'  ";
//		Sql+=" ORDER BY WRKD_KEYID DESC ) WHERE ROWNUM=1) ";
//		Sql+=" AND WRIN_STATUS='A' ";	
		Sql += "SELECT COUNT(*) "
			+ "FROM GEN_TL_WORKFLOW_INFO "
				+ "WHERE 1 = 1  "
				+ "  AND WRIN_REF_TYPE = '"+refType+"' "
				+ "  AND WRIN_REF_ID = '"+refId+"'  "
				+ "  AND WRIN_ROLE_ID IN ( "
				+ "        SELECT ROLE_KEYID "
				+ "        FROM ( "
				+ "            SELECT  "
				+ "                ROLE_KEYID, "
				+ "                ROLE_NAME, "
				+ "                WRKD_KEYID, "
				+ "                COALESCE(WRIN_STATUS, 'Pending') AS WRIN_STATUS, "
				+ "                WRIN_DATE, "
				+ "                WRIN_REMARKS, "
				+ "                ROLE_LEVEL "
				+ "            FROM GEN_TL_WORKFLOW_MENU_LINK AS ML "
				+ "            JOIN GEN_TL_WORKFLOWDTL AS D "
				+ "                ON ML.WRML_WRKM_KEYID = D.WRKD_WRKM_KEYID "
				+ "            JOIN ADM_TL_ROLEMST AS R "
				+ "                ON D.WRKD_STAGE = R.ROLE_KEYID "
				+ "            LEFT JOIN GEN_TL_WORKFLOW_INFO AS I "
				+ "                ON I.WRIN_WRML_KEYID = ML.WRML_KEYID "
				+ "               AND I.WRIN_REF_ID = '"+refId+"' "
				+ "               AND I.WRIN_REF_TYPE = '"+refType+"' "
				+ "            WHERE ML.WRML_TRANS_CODE = '"+transCode+"' "
				+ "            ORDER BY D.WRKD_KEYID DESC "
				+ "            LIMIT 1 "
				+ "        ) AS X "
				+ "    ) "
				+ "  AND WRIN_STATUS = 'A'; ";
		return Sql;		
	}	  

}

