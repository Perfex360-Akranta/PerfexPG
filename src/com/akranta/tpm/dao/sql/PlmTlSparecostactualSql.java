package com.akranta.tpm.dao.sql;

public class PlmTlSparecostactualSql {

	public static final String TBL_PLM_TL_SPARECOSTACTUAL = "PLM_TL_SPARECOSTACTUAL";  

	TableFieldType [] pscaDbFields = null;

	public enum   tableFldConstants
	{
		pmcalendarid, sitreference, sparesid, quantity, rate, value, refdocno
		, doctype, requestedby, date, tempfield1, tempfield2, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPscaDbFields() {
		return pscaDbFields;
	}

	public PlmTlSparecostactualSql()
	{
		pscaDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			pscaDbFields[ i ] = new TableFieldType();
		}
		pscaDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldName = "PSCA_PMCALENDARID";
		pscaDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.sitreference.ordinal() ].fieldName = "PSCA_SITREFERENCE";
		pscaDbFields[ tableFldConstants.sitreference.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.sparesid.ordinal() ].fieldName = "PSCA_SPARESID";
		pscaDbFields[ tableFldConstants.sparesid.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "PSCA_QUANTITY";
		pscaDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		pscaDbFields[ tableFldConstants.rate.ordinal() ].fieldName = "PSCA_RATE";
		pscaDbFields[ tableFldConstants.rate.ordinal() ].fieldType = 'N';

		pscaDbFields[ tableFldConstants.value.ordinal() ].fieldName = "PSCA_VALUE";
		pscaDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		pscaDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "PSCA_REFDOCNO";
		pscaDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "PSCA_DOCTYPE";
		pscaDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "PSCA_REQUESTEDBY";
		pscaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PSCA_DATE";
		pscaDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		pscaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PSCA_TEMPFIELD1";
		pscaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PSCA_TEMPFIELD2";
		pscaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSCA_CREATEDBY";
		pscaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pscaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSCA_CREATEDON";
		pscaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pscaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSCA_MODIFIEDON";
		pscaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_SPARECOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_SPARECOSTACTUAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.pmcalendarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pmcalendarid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_SPARECOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.pmcalendarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pmcalendarid.ordinal()] + "'";
		return sql;
	}

}

