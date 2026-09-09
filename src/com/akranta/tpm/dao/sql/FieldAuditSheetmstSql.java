package com.akranta.tpm.dao.sql;


import com.akranta.tpm.controller.UIUtils;

public class FieldAuditSheetmstSql {

	public static final String TBL_JHA_TL_FIELDAUDITSHEETMST = "JHA_TL_FIELDAUDITSHEETMST";   

	TableFieldType [] fasmDbFields = null;

	public enum   tableFldConstants
	{
		keyid,flid,date,jobdesc,shift,serprovider,violations,
		evaluatedby,noofesp,donedmt,donejh,tradeid,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getfasmDbFields() {
		return fasmDbFields;
	}

	public FieldAuditSheetmstSql()
	{
		fasmDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i <21; i++)
		{	
			fasmDbFields[ i ] = new TableFieldType();
		}
		fasmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FASM_KEYID";
		fasmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FASM_FLID";
		fasmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "FASM_DATE";
		fasmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';
		
		fasmDbFields[ tableFldConstants.jobdesc.ordinal() ].fieldName = "FASM_JOBDESC";
		fasmDbFields[ tableFldConstants.jobdesc.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.shift.ordinal() ].fieldName = "FASM_SHIFT";
		fasmDbFields[ tableFldConstants.shift.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.serprovider.ordinal() ].fieldName = "FASM_SERPROVIDER";
		fasmDbFields[ tableFldConstants.serprovider.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.violations.ordinal() ].fieldName = "FASM_VIOLATIONS";
		fasmDbFields[ tableFldConstants.violations.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.evaluatedby.ordinal() ].fieldName = "FASM_EVALUATEDBY";
		fasmDbFields[ tableFldConstants.evaluatedby.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.noofesp.ordinal() ].fieldName = "FASM_NOOFESP";
		fasmDbFields[ tableFldConstants.noofesp.ordinal() ].fieldType = 'N';
		
		fasmDbFields[ tableFldConstants.donedmt.ordinal() ].fieldName = "FASM_DONEDMT";
		fasmDbFields[ tableFldConstants.donedmt.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.donejh.ordinal() ].fieldName = "FASM_DONEJH";
		fasmDbFields[ tableFldConstants.donejh.ordinal() ].fieldType = 'V';	
		
		fasmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "FASM_TRADEID";
		fasmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';	  
		
		fasmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FASM_TEMPFIELD1";
		fasmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FASM_TEMPFIELD2";
		fasmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FASM_TEMPFIELD3";
		fasmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FASM_TEMPFIELD4";
		fasmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		fasmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FASM_TEMPFIELD5";
		fasmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FASM_ACTIVE";
		fasmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fasmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FASM_CREATEDBY";
		fasmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fasmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FASM_CREATEDON";
		fasmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fasmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FASM_MODIFIEDON";
		fasmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_FIELDAUDITSHEETMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_FIELDAUDITSHEETMST, fieldTypeArr, dataArray);
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{		
		String sql = "DELETE from " + TBL_JHA_TL_FIELDAUDITSHEETMST ;		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;		
		
	}
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_JHA_TL_FIELDAUDITSHEETMST +" where FASM_KEYID=?";
		return sql;
	}
}

