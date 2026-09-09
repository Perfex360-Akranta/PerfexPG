package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskMappingTopicdtlSql {

	public static final String TBL_ENT_TL_TASK_MAPPING_TOPICDTL = "ENT_TL_TASK_MAPPING_TOPICDTL";  

	TableFieldType [] tmtdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tmtm_keyid, tmkm_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTmtdDbFields() {
		return tmtdDbFields;
	}

	public EntTlTaskMappingTopicdtlSql()
	{
		tmtdDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			tmtdDbFields[ i ] = new TableFieldType();
		}
		tmtdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMTD_KEYID";
		tmtdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tmtm_keyid.ordinal() ].fieldName = "TMTD_TMTM_KEYID";
		tmtdDbFields[ tableFldConstants.tmtm_keyid.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tmkm_keyid.ordinal() ].fieldName = "TMTD_TMKM_KEYID";
		tmtdDbFields[ tableFldConstants.tmkm_keyid.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TMTD_TEMPFIELD1";
		tmtdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMTD_TEMPFIELD2";
		tmtdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMTD_TEMPFIELD3";
		tmtdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMTD_TEMPFIELD4";
		tmtdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMTD_TEMPFIELD5";
		tmtdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TMTD_TEMPFIELD6";
		tmtdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "TMTD_TEMPFIELD7";
		tmtdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMTD_ACTIVE";
		tmtdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmtdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMTD_CREATEDBY";
		tmtdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmtdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMTD_CREATEDON";
		tmtdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmtdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMTD_MODIFIEDON";
		tmtdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASK_MAPPING_TOPICDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASK_MAPPING_TOPICDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASK_MAPPING_TOPICDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.tmtm_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tmtm_keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeleteTasklist(String keyid) {
		CommonMessage.debugMsg("Keyid 3333:::::"+keyid);
		String sql=" DELETE FROM " + TBL_ENT_TL_TASK_MAPPING_TOPICDTL + " WHERE TMTD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}
	public static String DeleteTopiclist(String keyid) {
		CommonMessage.debugMsg("Keyid 3333:::::"+keyid);
		String sql=" DELETE FROM " + TBL_ENT_TL_TASK_MAPPING_TOPICDTL + " WHERE TMTD_TMKM_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public static String getDeleteDtlSql(String tmtmKeyid) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Keyid 3333:::::"+tmtmKeyid);
		String sql=" DELETE FROM " + TBL_ENT_TL_TASK_MAPPING_TOPICDTL + " WHERE TMTD_TMTM_KEYID ='"+tmtmKeyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

}

