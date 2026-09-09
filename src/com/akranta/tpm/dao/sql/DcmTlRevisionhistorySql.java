package com.akranta.tpm.dao.sql;

public class DcmTlRevisionhistorySql {

	public static final String TBL_DCM_TL_REVISIONHISTORY = "DCM_TL_REVISIONHISTORY";  

	TableFieldType [] dmrhDbFields = null;

	public enum   tableFldConstants
	{
		keyid, docid, revno, revby, revdate, changes, temp2, temp3, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDmrhDbFields() {
		return dmrhDbFields;
	}

	public DcmTlRevisionhistorySql()
	{
		dmrhDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			dmrhDbFields[ i ] = new TableFieldType();
		}
		dmrhDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMRH_KEYID";
		dmrhDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.docid.ordinal() ].fieldName = "DMRH_DOCID";
		dmrhDbFields[ tableFldConstants.docid.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.revno.ordinal() ].fieldName = "DMRH_REVNO";
		dmrhDbFields[ tableFldConstants.revno.ordinal() ].fieldType = 'N';

		dmrhDbFields[ tableFldConstants.revby.ordinal() ].fieldName = "DMRH_REVBY";
		dmrhDbFields[ tableFldConstants.revby.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.revdate.ordinal() ].fieldName = "DMRH_REVDATE";
		dmrhDbFields[ tableFldConstants.revdate.ordinal() ].fieldType = 'D';

		dmrhDbFields[ tableFldConstants.changes.ordinal() ].fieldName = "DMRH_CHANGES";
		dmrhDbFields[ tableFldConstants.changes.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "DMRH_TEMP2";
		dmrhDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "DMRH_TEMP3";
		dmrhDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMRH_ACTIVE";
		dmrhDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmrhDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMRH_CREATEDBY";
		dmrhDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmrhDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMRH_CREATEDON";
		dmrhDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmrhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMRH_MODIFIEDON";
		dmrhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DCM_TL_REVISIONHISTORY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DCM_TL_REVISIONHISTORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DCM_TL_REVISIONHISTORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getRevNoSql(String id)
	{
		String sql = "SELECT DMRH_CREATEDON,DMRH_REVNO FROM "+TBL_DCM_TL_REVISIONHISTORY;
		sql += " WHERE DMRH_DOCID='"+id+"'";
		return sql;
	}
	public static String getRevHistorySql(String id)
	{
		String sql = "SELECT DMRH_KEYID,DMRH_REVNO,EMPM_NAME,to_char(DMRH_REVDATE,'DD-MON-YYYY'),DMRH_CHANGES";
			   sql += " FROM "+TBL_DCM_TL_REVISIONHISTORY+","+TableNames.TBL_GEN_TL_EMPLOYEEMST;
			   sql += " WHERE DMRH_REVBY=EMPM_KEYID AND DMRH_DOCID='"+id+"' ORDER BY DMRH_REVNO";
		return sql;
	}


}

