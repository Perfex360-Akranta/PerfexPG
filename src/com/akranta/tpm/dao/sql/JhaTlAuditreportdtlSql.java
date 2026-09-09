package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.JhaTlAuditreportmst;

public class JhaTlAuditreportdtlSql {

	public static final String TBL_JHA_TL_AUDITREPORTDTL = "JHA_TL_AUDITREPORTDTL";  

	TableFieldType [] aurdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, aurm_keyid, slno, orderno,sheetno,sheetname, auditelement, mm, ma, observations
		, remarks, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getAurdDbFields() {
		return aurdDbFields;
	}

	public JhaTlAuditreportdtlSql()
	{
		aurdDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			aurdDbFields[ i ] = new TableFieldType();
		}
		aurdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "AURD_KEYID";
		aurdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.aurm_keyid.ordinal() ].fieldName = "AURD_AURM_KEYID";
		aurdDbFields[ tableFldConstants.aurm_keyid.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "AURD_SLNO";
		aurdDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "AURD_ORDERNO";
		aurdDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		aurdDbFields[ tableFldConstants.sheetno.ordinal() ].fieldName = "AURD_SHEETNUMBER";
		aurdDbFields[ tableFldConstants.sheetno.ordinal() ].fieldType = 'N';
		
		aurdDbFields[ tableFldConstants.sheetname.ordinal() ].fieldName = "AURD_SHEETNAME";
		aurdDbFields[ tableFldConstants.sheetname.ordinal() ].fieldType = 'V';
		
		aurdDbFields[ tableFldConstants.auditelement.ordinal() ].fieldName = "AURD_AUDITELEMENT";
		aurdDbFields[ tableFldConstants.auditelement.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.mm.ordinal() ].fieldName = "AURD_MM";
		aurdDbFields[ tableFldConstants.mm.ordinal() ].fieldType = 'N';

		aurdDbFields[ tableFldConstants.ma.ordinal() ].fieldName = "AURD_MA";
		aurdDbFields[ tableFldConstants.ma.ordinal() ].fieldType = 'N';

		aurdDbFields[ tableFldConstants.observations.ordinal() ].fieldName = "AURD_OBSERVATIONS";
		aurdDbFields[ tableFldConstants.observations.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "AURD_REMARKS";
		aurdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "AURD_TEMPFIELD1";
		aurdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "AURD_TEMPFIELD2";
		aurdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "AURD_TEMPFIELD3";
		aurdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "AURD_ACTIVE";
		aurdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		aurdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "AURD_CREATEDBY";
		aurdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		aurdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "AURD_CREATEDON";
		aurdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		aurdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "AURD_MODIFIEDON";
		aurdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITREPORTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITREPORTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITREPORTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeletedtlSql(JhaTlAuditreportmst jhaTlAuditreportmst) {
		String sql = "DELETE FROM " + TBL_JHA_TL_AUDITREPORTDTL ;
		sql += " WHERE AURD_AURM_KEYID='"+jhaTlAuditreportmst.getAurmKeyid()+"'";
		return sql;
	}

}

