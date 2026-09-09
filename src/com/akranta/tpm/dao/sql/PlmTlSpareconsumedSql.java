package com.akranta.tpm.dao.sql;

public class PlmTlSpareconsumedSql {

	public static final String TBL_PLM_TL_SPARECONSUMED = "PLM_TL_SPARECONSUMED";  

	TableFieldType [] pspcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wodetailid, pmcalendarid, spareid, quantity, cost, isactivitydone
		, remarks, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPspcDbFields() {
		return pspcDbFields;
	}

	public PlmTlSpareconsumedSql()
	{
		pspcDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			pspcDbFields[ i ] = new TableFieldType();
		}
		pspcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PSPC_KEYID";
		pspcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldName = "PSPC_WODETAILID";
		pspcDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldName = "PSPC_PMCALENDARID";
		pspcDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.spareid.ordinal() ].fieldName = "PSPC_SPAREID";
		pspcDbFields[ tableFldConstants.spareid.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "PSPC_QUANTITY";
		pspcDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		pspcDbFields[ tableFldConstants.cost.ordinal() ].fieldName = "PSPC_COST";
		pspcDbFields[ tableFldConstants.cost.ordinal() ].fieldType = 'N';

		pspcDbFields[ tableFldConstants.isactivitydone.ordinal() ].fieldName = "PSPC_ISACTIVITYDONE";
		pspcDbFields[ tableFldConstants.isactivitydone.ordinal() ].fieldType = 'C';

		pspcDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "PSPC_REMARKS";
		pspcDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSPC_CREATEDBY";
		pspcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pspcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSPC_CREATEDON";
		pspcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pspcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSPC_MODIFIEDON";
		pspcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_SPARECONSUMED, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_SPARECONSUMED, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_SPARECONSUMED ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

