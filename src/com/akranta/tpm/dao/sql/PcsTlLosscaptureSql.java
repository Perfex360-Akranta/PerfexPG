package com.akranta.tpm.dao.sql;

public class PcsTlLosscaptureSql {

	public static final String TBL_PCS_TL_LOSSCAPTURE = "PCS_TL_LOSSCAPTURE";  

	TableFieldType [] plosDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, fromtime, totime, losstime, lossreason
		, lossid, tradeid, prod_impact, prod_imp_qty, lossdescription, pldetailsid
		, Equipment, detecteedby, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPlosDbFields() {
		return plosDbFields;
	}

	public PcsTlLosscaptureSql()
	{
		plosDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			plosDbFields[ i ] = new TableFieldType();
		}
		plosDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PLOS_KEYID";
		plosDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PLOS_FLID";
		plosDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PLOS_DATE";
		plosDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "PLOS_SHIFTID";
		plosDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';
 // -------------- aLTERED BY VIGNESH FOR TIMESTAMP ------------------------------------------//
		plosDbFields[ tableFldConstants.fromtime.ordinal() ].fieldName = "PLOS_FROMTIME";
		plosDbFields[ tableFldConstants.fromtime.ordinal() ].fieldType = 'T';

		plosDbFields[ tableFldConstants.totime.ordinal() ].fieldName = "PLOS_TOTIME";
		plosDbFields[ tableFldConstants.totime.ordinal() ].fieldType = 'T';
		 // -------------- aLTERED BY VIGNESH FOR TIMESTAMP ------------------------------------------//
		plosDbFields[ tableFldConstants.losstime.ordinal() ].fieldName = "PLOS_LOSSTIME";
		plosDbFields[ tableFldConstants.losstime.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.lossreason.ordinal() ].fieldName = "PLOS_LOSSREASON";
		plosDbFields[ tableFldConstants.lossreason.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "PLOS_LOSSID";
		plosDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		
		plosDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "PLOS_TRADEID";
		plosDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.prod_impact.ordinal() ].fieldName = "PLOS_PROD_IMPACT";
		plosDbFields[ tableFldConstants.prod_impact.ordinal() ].fieldType = 'C';

		plosDbFields[ tableFldConstants.prod_imp_qty.ordinal() ].fieldName = "PLOS_PROD_IMP_QTY";
		plosDbFields[ tableFldConstants.prod_imp_qty.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.lossdescription.ordinal() ].fieldName = "PLOS_LOSSDESCRIPTION";
		plosDbFields[ tableFldConstants.lossdescription.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldName = "PLOS_PLDETAILSID";
		plosDbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.Equipment.ordinal() ].fieldName = "PLOS_EQUIPMENT";
		plosDbFields[ tableFldConstants.Equipment.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.detecteedby.ordinal() ].fieldName = "PLOS_DETECTEDBY";
		plosDbFields[ tableFldConstants.detecteedby.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PLOS_TEMPFIELD4";
		plosDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PLOS_TEMPFIELD5";
		plosDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PLOS_ACTIVE";
		plosDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PLOS_CREATEDBY";
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PLOS_CREATEDON";
		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PLOS_MODIFIEDON";
		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSCAPTURE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSCAPTURE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSCAPTURE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

