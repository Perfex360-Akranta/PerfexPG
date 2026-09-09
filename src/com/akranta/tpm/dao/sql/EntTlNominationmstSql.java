package com.akranta.tpm.dao.sql;

public class EntTlNominationmstSql {

	public static final String TBL_ENT_TL_NOMINATIONMST = "ENT_TL_NOMINATIONMST";  

	TableFieldType [] nommDbFields = null;

	public enum   tableFldConstants
	{
		keyid, type, prog_keyid, bach_keyid, prepared_by, prepared_date
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getNommDbFields() {
		return nommDbFields;
	}

	public EntTlNominationmstSql()
	{
		nommDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			nommDbFields[ i ] = new TableFieldType();
		}
		nommDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NOMM_KEYID";
		nommDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.type.ordinal() ].fieldName = "NOMM_TYPE";
		nommDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldName = "NOMM_PROG_KEYID";
		nommDbFields[ tableFldConstants.prog_keyid.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldName = "NOMM_BACH_KEYID";
		nommDbFields[ tableFldConstants.bach_keyid.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.prepared_by.ordinal() ].fieldName = "NOMM_PREPARED_BY";
		nommDbFields[ tableFldConstants.prepared_by.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.prepared_date.ordinal() ].fieldName = "NOMM_PREPARED_DATE";
		nommDbFields[ tableFldConstants.prepared_date.ordinal() ].fieldType = 'D';

		nommDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "NOMM_REMARKS";
		nommDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "NOMM_TEMPFIELD1";
		nommDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "NOMM_TEMPFIELD2";
		nommDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "NOMM_TEMPFIELD3";
		nommDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "NOMM_TEMPFIELD4";
		nommDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "NOMM_TEMPFIELD5";
		nommDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NOMM_ACTIVE";
		nommDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		nommDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NOMM_CREATEDBY";
		nommDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		nommDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NOMM_CREATEDON";
		nommDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		nommDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NOMM_MODIFIEDON";
		nommDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_NOMINATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_NOMINATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_NOMINATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDeleteDtlsSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from ENT_TL_NOMINATIONDTL " ;
		
		sql += " where  NODD_NOMM_KEYID= '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_ENT_TL_NOMINATIONMST +" where NOMM_KEYID=?";
		return sql;
	}

	public static String getBatchCapacitySql(String batchId) {
		// TODO Auto-generated method stub
		String sql = "SELECT BACH_MAXSIZE FROM ENT_TL_BATCHMST WHERE BACH_KEYID='"+batchId+"'";
		return sql;
	}

}

