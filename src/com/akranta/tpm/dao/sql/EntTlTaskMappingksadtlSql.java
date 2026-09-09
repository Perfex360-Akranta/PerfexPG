package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskMappingksadtlSql {

	public static final String TBL_ENT_TL_TASK_MAPPINGKSADTL = "ENT_TL_TASK_MAPPINGKSADTL";  

	TableFieldType [] tmkdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tmkm_keyid, task, ksa, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTmkdDbFields() {
		return tmkdDbFields;
	}

	public EntTlTaskMappingksadtlSql()
	{
		tmkdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			tmkdDbFields[ i ] = new TableFieldType();
		}
		tmkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMKD_KEYID";
		tmkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tmkm_keyid.ordinal() ].fieldName = "TMKD_TMKM_KEYID";
		tmkdDbFields[ tableFldConstants.tmkm_keyid.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.task.ordinal() ].fieldName = "TMKD_TASK";
		tmkdDbFields[ tableFldConstants.task.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.ksa.ordinal() ].fieldName = "TMKD_KSA";
		tmkdDbFields[ tableFldConstants.ksa.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TMKD_TEMPFIELD1";
		tmkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMKD_TEMPFIELD2";
		tmkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMKD_TEMPFIELD3";
		tmkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMKD_TEMPFIELD4";
		tmkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMKD_TEMPFIELD5";
		tmkdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TMKD_TEMPFIELD6";
		tmkdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "TMKD_TEMPFIELD7";
		tmkdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMKD_ACTIVE";
		tmkdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMKD_CREATEDBY";
		tmkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMKD_CREATEDON";
		tmkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMKD_MODIFIEDON";
		tmkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASK_MAPPINGKSADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASK_MAPPINGKSADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASK_MAPPINGKSADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String RpDetailGrid(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		
		String sqls="SELECT  TMKD_KEYID,'','',TMKD_KSA,TMKD_TASK ,'' " ;
			   sqls+=" From ENT_TL_TASK_MAPPINGKSAMST,ENT_TL_TASK_MAPPINGKSADTL where   TMKD_TMKM_KEYID(+)=TMKM_KEYID";
		return sqls;
	}

	public static String DeleteRplist(String keyid) {
		CommonMessage.debugMsg("Keyid 3333:::::"+keyid);
		String sql=" DELETE FROM " + TBL_ENT_TL_TASK_MAPPINGKSADTL+ " WHERE TMKD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public static String DeleteRplistMst(String master, String details) {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg("Keyid 3333:::::"+string);
		
		String sql=" DELETE FROM " + TBL_ENT_TL_TASK_MAPPINGKSADTL+ " WHERE TMKD_TMKM_KEYID ='"+master+"' AND TMKD_KEYID='"+details+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
		
		
	}

	

}

