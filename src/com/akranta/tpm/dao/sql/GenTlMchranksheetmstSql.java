package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.CommonFilter;

public class GenTlMchranksheetmstSql {

	public static final String TBL_GEN_TL_MCHRANKSHEETMST = "GEN_TL_MCHRANKSHEETMST";  

	TableFieldType [] mrsmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, machineid, evaluationdate, totalscore, rankskill, remarks
		, evaluatedby, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMrsmDbFields() {
		return mrsmDbFields;
	}

	public GenTlMchranksheetmstSql()
	{
		mrsmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			mrsmDbFields[ i ] = new TableFieldType();
		}
		mrsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MRSM_KEYID";
		mrsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MRSM_MACHINEID";
		mrsmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.evaluationdate.ordinal() ].fieldName = "MRSM_EVALUATIONDATE";
		mrsmDbFields[ tableFldConstants.evaluationdate.ordinal() ].fieldType = 'D';

		mrsmDbFields[ tableFldConstants.totalscore.ordinal() ].fieldName = "MRSM_TOTALSCORE";
		mrsmDbFields[ tableFldConstants.totalscore.ordinal() ].fieldType = 'N';

		mrsmDbFields[ tableFldConstants.rankskill.ordinal() ].fieldName = "MRSM_RANKSKILL";
		mrsmDbFields[ tableFldConstants.rankskill.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MRSM_REMARKS";
		mrsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.evaluatedby.ordinal() ].fieldName = "MRSM_EVALUATEDBY";
		mrsmDbFields[ tableFldConstants.evaluatedby.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MRSM_ACTIVE";
		mrsmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mrsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MRSM_CREATEDBY";
		mrsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mrsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MRSM_CREATEDON";
		mrsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mrsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MRSM_MODIFIEDON";
		mrsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHRANKSHEETMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHRANKSHEETMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHRANKSHEETMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getMachineRankDetails(String machinekeyid) {
		 String  sql= "select MRSM_KEYID,MRSM_MACHINEID,TO_CHAR(MRSM_EVALUATIONDATE,'DD-MON-YYYY'),MRSM_TOTALSCORE,MRSK_NAME," +
		 		"MRSM_REMARKS,MRSM_EVALUATEDBY from GEN_TL_MCHRANKSHEETMST,gen_tl_mchrankskillmst WHERE mrsm_active = 'Y'  and MRSK_KEYID=MRSM_RANKSKILL and MRSM_KEYID = ?";
			return sql;
	}
	public String getMasterMainGrid(CommonFilter commonFilter, String keyId, String machine, String date) {
		String Machineid="";
		if(UIUtils.isValidKeyId(keyId))
			Machineid=keyId;
		else 
			Machineid="{}";
		String sql="SELECT MRKP_KEYID,MRSD_KEYID,MRKP_RESULTAREA,MRKP_PARAMETERNAME,MRSD_POINTSSCORED AS POINTSCORE,TO_CHAR(MRKP_MAXIMUMMARKS) "+
		"FROM GEN_TL_MCHRANKPARAMETER, GEN_TL_MCHRANKSHEETDTL WHERE   MRKP_KEYID=MRSD_PARAMETERID(+) AND MRSD_MASTERID(+) = '"+Machineid+"' " +
		"UNION ALL SELECT '' MRKP_KEYID,'' MRSD_KEYID,'' MRKP_RESULTAREA,'Total' MRKP_PARAMETERNAME,SUM(MRSD_POINTSSCORED) AS POINTSCORE, '' MRKP_MAXIMUMMARKS " +
		"FROM GEN_TL_MCHRANKPARAMETER, GEN_TL_MCHRANKSHEETDTL WHERE  MRKP_KEYID=MRSD_PARAMETERID(+) AND MRSD_MASTERID(+) = '"+Machineid+"' " +
		"ORDER BY MRKP_RESULTAREA";
		
		return sql;
	}

}

