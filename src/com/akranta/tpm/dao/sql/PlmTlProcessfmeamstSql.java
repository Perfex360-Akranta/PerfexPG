package com.akranta.tpm.dao.sql;

public class PlmTlProcessfmeamstSql {

	public static final String TBL_PLM_TL_PROCESSFMEAMST = "PLM_TL_PROCESSFMEAMST";  

	TableFieldType [] fmpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, no, processid, supprocessid, preparedby, coreteam
		, doctype, docmstid, docdtlsid
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFmpmDbFields() {
		return fmpmDbFields;
	}

	public PlmTlProcessfmeamstSql()
	{
		fmpmDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			fmpmDbFields[ i ] = new TableFieldType();
		}
		fmpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMPM_KEYID";
		fmpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FMPM_FLID";
		fmpmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "FMPM_DATE";
		fmpmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		fmpmDbFields[ tableFldConstants.no.ordinal() ].fieldName = "FMPM_NO";
		fmpmDbFields[ tableFldConstants.no.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "FMPM_PROCESSID";
		fmpmDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.supprocessid.ordinal() ].fieldName = "FMPM_SUPPROCESSID";
		fmpmDbFields[ tableFldConstants.supprocessid.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "FMPM_PREPAREDBY";
		fmpmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.coreteam.ordinal() ].fieldName = "FMPM_CORETEAM";
		fmpmDbFields[ tableFldConstants.coreteam.ordinal() ].fieldType = 'V';
		
		fmpmDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "FMPM_DOCTYPE";
		fmpmDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'C';
		
		fmpmDbFields[ tableFldConstants.docmstid.ordinal() ].fieldName = "FMPM_DOCMSTID";
		fmpmDbFields[ tableFldConstants.docmstid.ordinal() ].fieldType = 'V';
		
		fmpmDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldName = "FMPM_DOCDTLSID";
		fmpmDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMPM_TEMPFIELD1";
		fmpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMPM_TEMPFIELD2";
		fmpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMPM_TEMPFIELD3";
		fmpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMPM_TEMPFIELD4";
		fmpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMPM_TEMPFIELD5";
		fmpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMPM_ACTIVE";
		fmpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMPM_CREATEDBY";
		fmpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMPM_CREATEDON";
		fmpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMPM_MODIFIEDON";
		fmpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_PROCESSFMEAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_PROCESSFMEAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_PROCESSFMEAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(String keyId) {
		// TODO Auto-generated method stub
		String sql = "SELECT FMPM_KEYID,FMPM_FLID,FMPM_DATE,FMPM_NO,FMPM_PREPAREDBY,FMPM_CORETEAM,FMPM_PROCESSID,FMPM_SUPPROCESSID  from " + TBL_PLM_TL_PROCESSFMEAMST ;		
		sql += " where FMPM_KEYID= '" +  keyId + "'";
		return sql;
	}
}

