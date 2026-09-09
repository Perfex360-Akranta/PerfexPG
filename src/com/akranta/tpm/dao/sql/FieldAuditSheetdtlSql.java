package com.akranta.tpm.dao.sql;


public class FieldAuditSheetdtlSql {
	public static final String TBL_JHA_TL_FIELDAUDITSHEETDTL = "JHA_TL_FIELDAUDITSHEETDTL";  

	TableFieldType [] fasdDbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterid,espid,ppeid,ppecondition,tools,workpermitsafety,knowledge,remarks,espothers,tempfield2,
		tempfield3,tempfield4,tempfield5,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getfasdDbFields() {
		return fasdDbFields;
	}

	public FieldAuditSheetdtlSql()
	{
		fasdDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			fasdDbFields[ i ] = new TableFieldType();
		}
		fasdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FASD_KEYID";
		fasdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "FASD_FASM_KEYID";
		fasdDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.espid.ordinal() ].fieldName = "FASD_ESPID";
		fasdDbFields[ tableFldConstants.espid.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.ppeid.ordinal() ].fieldName = "FASD_PPEID";
		fasdDbFields[ tableFldConstants.ppeid.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.ppecondition.ordinal() ].fieldName = "FASD_PPECONDITION";
		fasdDbFields[ tableFldConstants.ppecondition.ordinal() ].fieldType = 'V';
		

		fasdDbFields[ tableFldConstants.tools.ordinal() ].fieldName = "FASD_TOOLS";
		fasdDbFields[ tableFldConstants.tools.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.workpermitsafety.ordinal() ].fieldName = "FASD_WORKPERMITSAFETY";
		fasdDbFields[ tableFldConstants.workpermitsafety.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.knowledge.ordinal() ].fieldName = "FASD_KNOWLEDGE";
		fasdDbFields[ tableFldConstants.knowledge.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "FASD_REMARKS";
		fasdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		
		fasdDbFields[ tableFldConstants.espothers.ordinal() ].fieldName = "FASD_TEMPFIELD1";
		fasdDbFields[ tableFldConstants.espothers.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FASD_TEMPFIELD2";
		fasdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FASD_TEMPFIELD3";
		fasdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FASD_TEMPFIELD4";
		fasdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		fasdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FASD_TEMPFIELD5";
		fasdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FASD_ACTIVE";
		fasdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fasdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FASD_CREATEDBY";
		fasdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fasdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FASD_CREATEDON";
		fasdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fasdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FASD_MODIFIEDON";
		fasdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_FIELDAUDITSHEETDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_FIELDAUDITSHEETDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_FIELDAUDITSHEETDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.masterid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.masterid.ordinal()] + "'";
		return sql;
	}

}
