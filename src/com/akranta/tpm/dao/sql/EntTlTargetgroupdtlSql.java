package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTargetgroupdtlSql {

	public static final String TBL_ENT_TL_TARGETGROUPDTL = "ENT_TL_TARGETGROUPDTL";  

	TableFieldType [] tgtdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tgtm_keyid, empid, uniquepositionid, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTgtdDbFields() {
		return tgtdDbFields;
	}

	public EntTlTargetgroupdtlSql()
	{
		tgtdDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			tgtdDbFields[ i ] = new TableFieldType();
		}
		tgtdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TGTD_KEYID";
		tgtdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tgtdDbFields[ tableFldConstants.tgtm_keyid.ordinal() ].fieldName = "TGTD_TGTM_KEYID";
		tgtdDbFields[ tableFldConstants.tgtm_keyid.ordinal() ].fieldType = 'V';

		tgtdDbFields[ tableFldConstants.empid.ordinal() ].fieldName = "TGTD_EMPID";
		tgtdDbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';

		tgtdDbFields[ tableFldConstants.uniquepositionid.ordinal() ].fieldName = "TGTD_UNIQUEPOSITIONID";
		tgtdDbFields[ tableFldConstants.uniquepositionid.ordinal() ].fieldType = 'V';

		tgtdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TGTD_TEMPFIELD1";
		tgtdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		tgtdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TGTD_TEMPFIELD2";
		tgtdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		tgtdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TGTD_TEMPFIELD3";
		tgtdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		tgtdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TGTD_ACTIVE";
		tgtdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tgtdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TGTD_CREATEDBY";
		tgtdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tgtdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TGTD_CREATEDON";
		tgtdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tgtdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TGTD_MODIFIEDON";
		tgtdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TARGETGROUPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TARGETGROUPDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TARGETGROUPDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDelete(String tgtmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_ENT_TL_TARGETGROUPDTL +" WHERE TGTD_TGTM_KEYID = '"+tgtmKeyid+"'";
		CommonMessage.debugMsg(sql);
		return sql;
	}

	public static String getDeletedtl(String tgtdKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_ENT_TL_TARGETGROUPDTL +" WHERE TGTD_KEYID = '"+tgtdKeyid+"'";
		CommonMessage.debugMsg(sql);
		return sql;
	}

}

