package com.akranta.tpm.dao.sql;

public class GenTlMchranksheetdtlSql {

	public static final String TBL_GEN_TL_MCHRANKSHEETDTL = "GEN_TL_MCHRANKSHEETDTL";  

	TableFieldType [] mrsdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterid, parameterid, pointsscored, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMrsdDbFields() {
		return mrsdDbFields;
	}

	public GenTlMchranksheetdtlSql()
	{
		mrsdDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			mrsdDbFields[ i ] = new TableFieldType();
		}
		mrsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MRSD_KEYID";
		mrsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mrsdDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "MRSD_MASTERID";
		mrsdDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		mrsdDbFields[ tableFldConstants.parameterid.ordinal() ].fieldName = "MRSD_PARAMETERID";
		mrsdDbFields[ tableFldConstants.parameterid.ordinal() ].fieldType = 'V';

		mrsdDbFields[ tableFldConstants.pointsscored.ordinal() ].fieldName = "MRSD_POINTSSCORED";
		mrsdDbFields[ tableFldConstants.pointsscored.ordinal() ].fieldType = 'N';

		mrsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MRSD_ACTIVE";
		mrsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mrsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MRSD_CREATEDBY";
		mrsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mrsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MRSD_CREATEDON";
		mrsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mrsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MRSD_MODIFIEDON";
		mrsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHRANKSHEETDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHRANKSHEETDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHRANKSHEETDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

