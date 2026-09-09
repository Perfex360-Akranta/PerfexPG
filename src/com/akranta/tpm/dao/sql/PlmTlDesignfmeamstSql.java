package com.akranta.tpm.dao.sql;

public class PlmTlDesignfmeamstSql {

	public static final String TBL_PLM_TL_DESIGNFMEAMST = "PLM_TL_DESIGNFMEAMST";  

	TableFieldType [] fmdmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, no, systemid, supsystemid, componentid, preparedby
		, coreteam, doctype, docmstid, docdtlsid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFmdmDbFields() {
		return fmdmDbFields;
	}

	public PlmTlDesignfmeamstSql()
	{
		fmdmDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			fmdmDbFields[ i ] = new TableFieldType();
		}
		fmdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMDM_KEYID";
		fmdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FMDM_FLID";
		fmdmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "FMDM_DATE";
		fmdmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		fmdmDbFields[ tableFldConstants.no.ordinal() ].fieldName = "FMDM_NO";
		fmdmDbFields[ tableFldConstants.no.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.systemid.ordinal() ].fieldName = "FMDM_SYSTEMID";
		fmdmDbFields[ tableFldConstants.systemid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.supsystemid.ordinal() ].fieldName = "FMDM_SUPSYSTEMID";
		fmdmDbFields[ tableFldConstants.supsystemid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.componentid.ordinal() ].fieldName = "FMDM_COMPONENTID";
		fmdmDbFields[ tableFldConstants.componentid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "FMDM_PREPAREDBY";
		fmdmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.coreteam.ordinal() ].fieldName = "FMDM_CORETEAM";
		fmdmDbFields[ tableFldConstants.coreteam.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "FMDM_DOCTYPE";
		fmdmDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.docmstid.ordinal() ].fieldName = "FMDM_DOCMSTID";
		fmdmDbFields[ tableFldConstants.docmstid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldName = "FMDM_DOCDTLSID";
		fmdmDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMDM_TEMPFIELD1";
		fmdmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMDM_TEMPFIELD2";
		fmdmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMDM_TEMPFIELD3";
		fmdmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMDM_TEMPFIELD4";
		fmdmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMDM_TEMPFIELD5";
		fmdmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMDM_ACTIVE";
		fmdmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMDM_CREATEDBY";
		fmdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMDM_CREATEDON";
		fmdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMDM_MODIFIEDON";
		fmdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_DESIGNFMEAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_DESIGNFMEAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_DESIGNFMEAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(String keyId) {
		// TODO Auto-generated method stub
		String sql = "SELECT FMDM_KEYID,FMDM_FLID,FMDM_DATE,FMDM_NO,FMDM_PREPAREDBY,FMDM_CORETEAM,FMDM_SYSTEMID,FMDM_SUPSYSTEMID,FMDM_COMPONENTID  from " + TBL_PLM_TL_DESIGNFMEAMST ;		
		sql += " where FMDM_KEYID= '" +  keyId + "'";
		return sql;
	}

}

