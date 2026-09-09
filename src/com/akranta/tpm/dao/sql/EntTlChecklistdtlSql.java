package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.EntTlChecklistmst;

public class EntTlChecklistdtlSql {

	public static final String TBL_ENT_TL_CHECKLISTDTL = "ENT_TL_CHECKLISTDTL";  

	TableFieldType [] chkdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, chkm_keyid, name, orderno, remarks, effective_date, inactive_date
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getChkdDbFields() {
		return chkdDbFields;
	}

	public EntTlChecklistdtlSql()
	{
		chkdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			chkdDbFields[ i ] = new TableFieldType();
		}
		chkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CHKD_KEYID";
		chkdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		chkdDbFields[ tableFldConstants.chkm_keyid.ordinal() ].fieldName = "CHKD_CHKM_KEYID";
		chkdDbFields[ tableFldConstants.chkm_keyid.ordinal() ].fieldType = 'V';

		chkdDbFields[ tableFldConstants.name.ordinal() ].fieldName = "CHKD_NAME";
		chkdDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		chkdDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "CHKD_ORDERNO";
		chkdDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		chkdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CHKD_REMARKS";
		chkdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		chkdDbFields[ tableFldConstants.effective_date.ordinal() ].fieldName = "CHKD_EFFECTIVE_DATE";
		chkdDbFields[ tableFldConstants.effective_date.ordinal() ].fieldType = 'D';

		chkdDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldName = "CHKD_INACTIVE_DATE";
		chkdDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldType = 'D';

		chkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CHKD_TEMPFIELD1";
		chkdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		chkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CHKD_TEMPFIELD2";
		chkdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		chkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CHKD_TEMPFIELD3";
		chkdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		chkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CHKD_TEMPFIELD4";
		chkdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		chkdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CHKD_ACTIVE";
		chkdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		chkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CHKD_CREATEDBY";
		chkdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		chkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CHKD_CREATEDON";
		chkdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		chkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CHKD_MODIFIEDON";
		chkdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_CHECKLISTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_CHECKLISTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_CHECKLISTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.chkm_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.chkm_keyid.ordinal()] + "'";
		return sql;
	}

	public static String getCheckListDetailSql() {
		return "SELECT * FROM "+ TBL_ENT_TL_CHECKLISTDTL +" WHERE CHKD_CHKM_KEYID = ? AND CHKD_KEYID = ?";
	}

	public static String getAllDetailCount(EntTlChecklistmst entTlChecklistmst) {
		return "select count(*) from ENT_TL_CHECKLISTDTL where CHKD_CHKM_KEYID = '"+entTlChecklistmst.getChkmKeyid()+"'";
	}

}

