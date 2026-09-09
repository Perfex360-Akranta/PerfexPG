package com.akranta.tpm.dao.sql;

public class LOPCEntrymstsql {

	public static final String TBL_GEN_TL_LOPCENTRYMST = "GEN_TL_LOPCENTRYMST";

	TableFieldType [] LopcDbFields = null;

	public enum   tableFldConstants
	{
		keyid,lopccategoryid,occurrencedatetime,fnlid,employeeid,lopcdesc,identifiedby,
		prepareddatetime,tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,
        active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getLopcDbFields() {
		return LopcDbFields;
	}

	public LOPCEntrymstsql()
	{
		LopcDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			LopcDbFields[ i ] = new TableFieldType();
		}
		LopcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "LOEM_KEYID";
		LopcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.lopccategoryid.ordinal() ].fieldName = "LOEM_LOPCCATEGORYID";
		LopcDbFields[ tableFldConstants.lopccategoryid.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldName = "LOEM_OCCURRENCEDATETIME";
		LopcDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldType = 'D';
		
		LopcDbFields[ tableFldConstants.fnlid.ordinal() ].fieldName = "LOEM_FLNID";
		LopcDbFields[ tableFldConstants.fnlid.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "LOEM_EMPLOYEEID";
		LopcDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.lopcdesc.ordinal() ].fieldName = "LOEM_DESCNEARMISS";
		LopcDbFields[ tableFldConstants.lopcdesc.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldName = "LOEM_IDENTIFIEDBY";
		LopcDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldName = "LOEM_PREPAREDDATETIME";
		LopcDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldType = 'D';
	
		LopcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "LOEM_TEMPFIELD1";
		LopcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "LOEM_TEMPFIELD2";
		LopcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "LOEM_TEMPFIELD3";
		LopcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "LOEM_TEMPFIELD4";
		LopcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		LopcDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "LOEM_TEMPFIELD5";
		LopcDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "LOEM_ACTIVE";
		LopcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		LopcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "LOEM_CREATEDBY";
		LopcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		LopcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "LOEM_CREATEDON";
		LopcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		LopcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "LOEM_MODIFIEDON";
		LopcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_LOPCENTRYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_LOPCENTRYMST, fieldTypeArr, dataArray);
	}
	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_LOPCENTRYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
}

