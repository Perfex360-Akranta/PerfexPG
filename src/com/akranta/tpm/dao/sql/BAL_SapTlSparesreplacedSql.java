package com.akranta.tpm.dao.sql;

public class BAL_SapTlSparesreplacedSql {

	public static final String TBL_SAP_TL_SPARESREPLACED = "SAP_TL_SPARESREPLACED";  

	TableFieldType [] sspmDbFields = null;

	public enum   tableFldConstants
	{
		keyId,spareno, docnumber, quantity, date, storagelocation, rate, value
		, sparename, stockavailable,tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSspmDbFields() {
		return sspmDbFields;
	}

	public BAL_SapTlSparesreplacedSql()
	{
		sspmDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			sspmDbFields[ i ] = new TableFieldType();
		}
		sspmDbFields[ tableFldConstants.keyId.ordinal() ].fieldName = "SSPM_KEYID";
		sspmDbFields[ tableFldConstants.keyId.ordinal() ].fieldType = 'V';
		
		sspmDbFields[ tableFldConstants.spareno.ordinal() ].fieldName = "SSPM_SPARENO";
		sspmDbFields[ tableFldConstants.spareno.ordinal() ].fieldType = 'N';

		sspmDbFields[ tableFldConstants.docnumber.ordinal() ].fieldName = "SSPM_DOCNUMBER";
		sspmDbFields[ tableFldConstants.docnumber.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "SSPM_QUANTITY";
		sspmDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		sspmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "SSPM_DATE";
		sspmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		sspmDbFields[ tableFldConstants.storagelocation.ordinal() ].fieldName = "SSPM_STORAGELOCATION";
		sspmDbFields[ tableFldConstants.storagelocation.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.rate.ordinal() ].fieldName = "SSPM_RATE";
		sspmDbFields[ tableFldConstants.rate.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.value.ordinal() ].fieldName = "SSPM_VALUE";
		sspmDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.sparename.ordinal() ].fieldName = "SSPM_SPARENAME";
		sspmDbFields[ tableFldConstants.sparename.ordinal() ].fieldType = 'V';		

		sspmDbFields[ tableFldConstants.stockavailable.ordinal() ].fieldName = "SSPM_AVAILABLESTOCK";
		sspmDbFields[ tableFldConstants.stockavailable.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SSPM_TEMPFIELD4";
		sspmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		sspmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SSPM_TEMPFIELD5";
		sspmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		sspmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SSPM_ACTIVE";
		sspmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sspmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SSPM_CREATEDBY";
		sspmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sspmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SSPM_CREATEDON";
		sspmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sspmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SSPM_MODIFIEDON";
		sspmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_TL_SPARESREPLACED, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_TL_SPARESREPLACED, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.spareno.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.spareno.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_TL_SPARESREPLACED ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.spareno.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.spareno.ordinal()] + "'";
		return sql;
	}
}

