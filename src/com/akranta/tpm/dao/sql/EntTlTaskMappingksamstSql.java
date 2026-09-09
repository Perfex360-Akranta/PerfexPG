package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskMappingksamstSql {

	public static final String TBL_ENT_TL_TASK_MAPPINGKSAMST = "ENT_TL_TASK_MAPPINGKSAMST";  

	TableFieldType [] tmkmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, skrm_keyid, sopke_keyid, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getTmkmDbFields() {
		return tmkmDbFields;
	}

	public EntTlTaskMappingksamstSql()
	{
		tmkmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			tmkmDbFields[ i ] = new TableFieldType();
		}
		tmkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMKM_KEYID";
		tmkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TMKM_FLID";
		tmkmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "TMKM_ROLE_KEYID";
		tmkmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TMKM_TEMPFIELD1";
		tmkmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMKM_TEMPFIELD2";
		tmkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMKM_TEMPFIELD3";
		tmkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMKM_TEMPFIELD4";
		tmkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMKM_TEMPFIELD5";
		tmkmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "TMKM_SKRM_KEYID";
		tmkmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.sopke_keyid.ordinal() ].fieldName = "TMKM_SOPKE_KEYID";
		tmkmDbFields[ tableFldConstants.sopke_keyid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMKM_ACTIVE";
		tmkmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMKM_CREATEDBY";
		tmkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMKM_CREATEDON";
		tmkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMKM_MODIFIEDON";
		tmkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASK_MAPPINGKSAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASK_MAPPINGKSAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASK_MAPPINGKSAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getTaksKsaSelectSql() {
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_TASK_MAPPINGKSAMST + " where TMKM_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public String RpDetailGrid(CommonFilter commonFilter, String mstkeyid) {
		// TODO Auto-generated method stub
		String sql="SELECT  TMKD_KEYID,'','',DECODE(TMKD_KSA,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE'),TMKD_TASK ,''  " ;
		       sql+="  From ENT_TL_TASK_MAPPINGKSAMST,ENT_TL_TASK_MAPPINGKSADTL where   TMKD_TMKM_KEYID=TMKM_KEYID  AND TMKM_KEYID='"+mstkeyid+"' ";
	return sql;

	}

}

