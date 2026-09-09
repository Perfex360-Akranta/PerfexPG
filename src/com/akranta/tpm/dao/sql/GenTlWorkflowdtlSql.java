package com.akranta.tpm.dao.sql;

public class GenTlWorkflowdtlSql {

	public static final String TBL_GEN_TL_WORKFLOWDTL = "GEN_TL_WORKFLOWDTL";  

	TableFieldType [] wrkdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wrkm_keyid, stage, type, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWrkdDbFields() {
		return wrkdDbFields;
	}

	public GenTlWorkflowdtlSql()
	{
		wrkdDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			wrkdDbFields[ i ] = new TableFieldType();
		}
		wrkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WRKD_KEYID";
		wrkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wrkdDbFields[ tableFldConstants.wrkm_keyid.ordinal() ].fieldName = "WRKD_WRKM_KEYID";
		wrkdDbFields[ tableFldConstants.wrkm_keyid.ordinal() ].fieldType = 'V';

		wrkdDbFields[ tableFldConstants.stage.ordinal() ].fieldName = "WRKD_STAGE";
		wrkdDbFields[ tableFldConstants.stage.ordinal() ].fieldType = 'V';

		wrkdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "WRKD_TYPE";
		wrkdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		wrkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WRKD_TEMPFIELD1";
		wrkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		wrkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WRKD_TEMPFIELD2";
		wrkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		wrkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WRKD_TEMPFIELD3";
		wrkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		wrkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "WRKD_TEMPFIELD4";
		wrkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		wrkdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WRKD_ACTIVE";
		wrkdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wrkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WRKD_CREATEDBY";
		wrkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wrkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WRKD_CREATEDON";
		wrkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wrkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WRKD_MODIFIEDON";
		wrkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_WORKFLOWDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_WORKFLOWDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_WORKFLOWDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String Delete(String keyId)
	{
		String sql = "DELETE from " + TBL_GEN_TL_WORKFLOWDTL ;
		
		sql += " where  WRKD_KEYID in (" +  keyId + ")";
		return sql;
	}

	public String getDeleteAllSql(String keyId){
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_GEN_TL_WORKFLOWDTL ;
		
		sql += " where WRKD_WRKM_KEYID  = '" +  keyId + "'";
		return sql;
	}

}

