package com.akranta.tpm.dao.sql;

import javax.swing.text.Utilities;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.utils.CommonMessage;

public class EntTlBatchFacultyLinkSql {

	public static final String TBL_ENT_TL_BATCH_FACULTY_LINK = "ENT_TL_BATCH_FACULTY_LINK";  

	TableFieldType [] bflkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, bach_keyid, ftym_keyid, eff_fromdate, eff_tilldate, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getBflkDbFields() {
		return bflkDbFields;
	}

	public EntTlBatchFacultyLinkSql()
	{
		bflkDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			bflkDbFields[ i ] = new TableFieldType();
		}
		bflkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BFLK_KEYID";
		bflkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bflkDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "BFLK_BACH_KEYID";
		bflkDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		bflkDbFields[ tableFldConstants.ftym_keyid.ordinal() ].fieldName = "BFLK_FTYM_KEYID";
		bflkDbFields[ tableFldConstants.ftym_keyid.ordinal() ].fieldType = 'V';

		bflkDbFields[ tableFldConstants.eff_fromdate.ordinal() ].fieldName = "BFLK_EFF_FROMDATE";
		bflkDbFields[ tableFldConstants.eff_fromdate.ordinal() ].fieldType = 'D';

		bflkDbFields[ tableFldConstants.eff_tilldate.ordinal() ].fieldName = "BFLK_EFF_TILLDATE";
		bflkDbFields[ tableFldConstants.eff_tilldate.ordinal() ].fieldType = 'D';

		bflkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "BFLK_TEMPFIELD1";
		bflkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "BFLK_TEMPFIELD2";
		bflkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BFLK_TEMPFIELD3";
		bflkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BFLK_TEMPFIELD4";
		bflkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BFLK_TEMPFIELD5";
		bflkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BFLK_ACTIVE";
		bflkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bflkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BFLK_CREATEDBY";
		bflkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bflkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BFLK_CREATEDON";
		bflkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bflkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BFLK_MODIFIEDON";
		bflkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BATCH_FACULTY_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BATCH_FACULTY_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BATCH_FACULTY_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getBatchFacultyViewsql(EntTlBatchFacultyLink entTlBatchFacultyLink)
	{
			String sql = " SELECT BFLK_KEYID,BFLK_FTYM_KEYID ,FTYM_CODE ||'-'|| FTYM_NAME ";
			sql += " FROM " + TBL_ENT_TL_BATCH_FACULTY_LINK + "," + TableNames.TBL_ENT_TL_BATCHMST + "," + TableNames.TBL_ENT_TL_FACULTYMST  ;
			sql += " WHERE BFLK_BACH_KEYID = BACH_KEYID AND  BFLK_FTYM_KEYID = FTYM_KEYID ";
			
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(entTlBatchFacultyLink.getBflkBachKeyid())){
			sql += " AND BACH_KEYID= '" + entTlBatchFacultyLink.getBflkBachKeyid() + "'";
		}
			sql += " GROUP BY BFLK_KEYID,BFLK_FTYM_KEYID, FTYM_CODE ||'-'|| FTYM_NAME ";
			com.akranta.tpm.utils.CommonMessage.debugMsg(sql);
		return sql;
	}
	public static String getBatchFacultyCountsql(EntTlBatchFacultyLink entTlBatchFacultyLink)
	{CommonMessage.debugMsg("inside sqls of batchfaculty");
			String sql = " SELECT  count(*) ";
			sql += " FROM " + TBL_ENT_TL_BATCH_FACULTY_LINK  ;
			
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(entTlBatchFacultyLink.getBflkFtymKeyid())){
			sql += " where BFLK_FTYM_KEYID= '" + entTlBatchFacultyLink.getBflkFtymKeyid() + "'";
		}
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(entTlBatchFacultyLink.getBflkBachKeyid())){
			sql += " AND BFLK_BACH_KEYID= '" + entTlBatchFacultyLink.getBflkBachKeyid() + "'";
		}
		CommonMessage.debugMsg(sql);
		if(UIUtils.isValidKeyId(entTlBatchFacultyLink.getFrmGrid())){
			
		}
		return sql;
	}

	public static String getFacultyViewsql(String programKeyId) {
		// TODO Auto-generated method stub
		String sql =  "SELECT DISTINCT ASMD_TOPI_KEYID,EMPM_KEYID ,EMPM_CODE ,'' as chkhdn ,'' as selectd,TOPI_NAME ,EMPM_NAME FROM  GEN_TL_EMPLOYEEMST,ENT_TL_ASSESSMENTMST, ENT_TL_ASSESSMENTDTL,ENT_TL_PROG_TARGET_SKILLS ,ENT_TL_SKILL_RATINGMST ,ENT_TL_TOPICMST  WHERE  ASMM_EMPM_KEYID =  EMPM_KEYID AND ASMD_ASMM_KEYID = ASMM_KEYID AND PRTS_TOPI_KEYID =ASMD_TOPI_KEYID AND TOPI_KEYID=ASMD_TOPI_KEYID"; 
			   sql += " AND SKRM_KEYID = ASMD_CURRENT_RATE AND EMPM_CODE not in (select FTYM_CODE FROM ENT_TL_FACULTYMST ) AND SKRM_ORDERNO = 3  AND PRTS_PROG_KEYID ='"+programKeyId +"' ORDER BY EMPM_KEYID ";		
			   
			   com.akranta.tpm.utils.CommonMessage.debugMsg("fac Distinict  :"+sql );
		return sql ;
	}
	
	

}

