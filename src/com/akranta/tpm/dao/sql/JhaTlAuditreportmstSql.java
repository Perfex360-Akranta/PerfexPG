package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.JhaTlAuditreportmst;

public class JhaTlAuditreportmstSql {

	public static final String TBL_JHA_TL_AUDITREPORTMST = "JHA_TL_AUDITREPORTMST";  

	TableFieldType [] aurmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, preparedby, uploadedby, uploadedfile
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getAurmDbFields() {
		return aurmDbFields;
	}

	public JhaTlAuditreportmstSql()
	{
		aurmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			aurmDbFields[ i ] = new TableFieldType();
		}
		aurmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "AURM_KEYID";
		aurmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "AURM_FLID";
		aurmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "AURM_ELEMENTID";
		aurmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "AURM_DATE";
		aurmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		aurmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "AURM_PREPAREDBY";
		aurmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.uploadedby.ordinal() ].fieldName = "AURM_UPLOADEDBY";
		aurmDbFields[ tableFldConstants.uploadedby.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.uploadedfile.ordinal() ].fieldName = "AURM_UPLOADEDFILE";
		aurmDbFields[ tableFldConstants.uploadedfile.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "AURM_TEMPFIELD1";
		aurmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "AURM_TEMPFIELD2";
		aurmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "AURM_TEMPFIELD3";
		aurmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "AURM_ACTIVE";
		aurmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		aurmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "AURM_CREATEDBY";
		aurmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		aurmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "AURM_CREATEDON";
		aurmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		aurmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "AURM_MODIFIEDON";
		aurmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITREPORTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITREPORTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITREPORTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getInsertSql(JhaTlAuditreportmst jhaTlAuditreportmst) {
		String sql=" INSERT INTO JHA_TL_AUDITREPORTMST VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE) ";
		return sql;
	}
	public static String getUpdateSql(JhaTlAuditreportmst jhaTlAuditreportmst) {
		String sql=" UPDATE JHA_TL_AUDITREPORTMST SET AURM_KEYID=?,AURM_FLID=?,AURM_ELEMENTID=?,AURM_DATE=?,AURM_PREPAREDBY=?,AURM_UPLOADEDBY=?,AURM_UPLOADEDFILE=?,AURM_TEMPFIELD1=?,AURM_TEMPFIELD2=?,AURM_TEMPFIELD3=?,AURM_ACTIVE=?,AURM_CREATEDBY=?,AURM_CREATEDON=SYSDATE,AURM_MODIFIEDON=SYSDATE WHERE AURM_KEYID=? ";
		return sql;
	}

	public static String select() {
		String sql=" SELECT * FROM JHA_TL_AUDITREPORTMST WHERE AURM_KEYID=?";
		return sql;
	}

}

