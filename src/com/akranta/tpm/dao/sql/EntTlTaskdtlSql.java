package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskdtlSql {

	public static final String TBL_ENT_TL_TASKDTL = "ENT_TL_TASKDTL";  

	TableFieldType [] tmkdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tmkm_keyid, task, ksa, ksadesc, basicmnt, tempfield3
		, tempfield4, tempfield5, skrm_keyid, spoke_keyid, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTmkdDbFields() {
		return tmkdDbFields;
	}

	public EntTlTaskdtlSql()
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

		tmkdDbFields[ tableFldConstants.ksadesc.ordinal() ].fieldName = "TMKD_KSADESC";
		tmkdDbFields[ tableFldConstants.ksadesc.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.basicmnt.ordinal() ].fieldName = "TMKD_BASICEMNT";
		tmkdDbFields[ tableFldConstants.basicmnt.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMKD_TEMPFIELD3";
		tmkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMKD_TEMPFIELD4";
		tmkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMKD_TEMPFIELD5";
		tmkdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "TMKD_SKRM_KEYID";
		tmkdDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		tmkdDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldName = "TMKD_SPOKE_KEYID";
		tmkdDbFields[ tableFldConstants.spoke_keyid.ordinal() ].fieldType = 'V';

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
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASKDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASKDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASKDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}


	public String DeleteRplist(String keyid) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Keyid 3333:::::"+keyid);
		String sql=" DELETE FROM " + TBL_ENT_TL_TASKDTL+ " WHERE TMKD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
		
	}

	public static String DeleteRplistMst(String master) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			//CommonMessage.debugMsg("Keyid 3333:::::"+string);
			
			String sql=" DELETE FROM " + TBL_ENT_TL_TASKDTL+ " WHERE TMKD_TMKM_KEYID ='"+master+"' ";
			CommonMessage.debugMsg("Delete Row Sql: "+sql);
			return sql;
	}



}

