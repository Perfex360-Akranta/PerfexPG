package com.akranta.tpm.dao.sql;

public class BAL_PcsTlLosscaptureSql {

	public static final String BAL_TBL_PCS_TL_LOSSCAPTURE = "BAL_PCS_TL_LOSSCAPTURE";  

	TableFieldType [] plosDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, fromtime, totime, losstime, lossreason
		, lossid, tradeid, prod_impact, prod_imp_qty, lossdescription, pldetailsid
		, losshrno, lossremarks, machinid, source,sourceName,lubTime,cleanTime,tightime,inspectTime,toolId,tempField2,tempField3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPlosDbFields() {
		return plosDbFields;
	}

	public BAL_PcsTlLosscaptureSql()
	{
		plosDbFields = new TableFieldType[ 30 ];
		for(int i = 0;i < 30; i++)
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

		plosDbFields[ tableFldConstants.fromtime.ordinal() ].fieldName = "PLOS_FROMTIME";
		plosDbFields[ tableFldConstants.fromtime.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.totime.ordinal() ].fieldName = "PLOS_TOTIME";
		plosDbFields[ tableFldConstants.totime.ordinal() ].fieldType = 'D';

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

		plosDbFields[ tableFldConstants.losshrno.ordinal() ].fieldName = "PLOS_LOSSHRNO";
		plosDbFields[ tableFldConstants.losshrno.ordinal() ].fieldType = 'C';

		plosDbFields[ tableFldConstants.lossremarks.ordinal() ].fieldName = "PLOS_REMARKS";
		plosDbFields[ tableFldConstants.lossremarks.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.machinid.ordinal() ].fieldName = "PLOS_MACHINEID";
		plosDbFields[ tableFldConstants.machinid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.source.ordinal() ].fieldName = "PLOS_SOURCE";
		plosDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.sourceName.ordinal() ].fieldName = "PLOS_SOURCENAME";
		plosDbFields[ tableFldConstants.sourceName.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.cleanTime.ordinal() ].fieldName = "PLOS_CLEANTIME";
		plosDbFields[ tableFldConstants.cleanTime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.lubTime.ordinal() ].fieldName = "PLOS_LUBTIME";
		plosDbFields[ tableFldConstants.lubTime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.tightime.ordinal() ].fieldName = "PLOS_TIGHTTIME";
		plosDbFields[ tableFldConstants.tightime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.inspectTime.ordinal() ].fieldName = "PLOS_INSPECTTIME";
		plosDbFields[ tableFldConstants.inspectTime.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PLOS_ACTIVE";
		plosDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PLOS_CREATEDBY";
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PLOS_CREATEDON";
		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PLOS_MODIFIEDON";
		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		plosDbFields[ tableFldConstants.toolId.ordinal() ].fieldName = "PLOS_TOOLID";
		plosDbFields[ tableFldConstants.toolId.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.tempField2.ordinal() ].fieldName = "PLOS_TEMPFIELD2";
		plosDbFields[ tableFldConstants.tempField2.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.tempField3.ordinal() ].fieldName = "PLOS_TEMPFIELD3";
		plosDbFields[ tableFldConstants.tempField3.ordinal() ].fieldType = 'V';


	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(BAL_TBL_PCS_TL_LOSSCAPTURE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(BAL_TBL_PCS_TL_LOSSCAPTURE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + BAL_TBL_PCS_TL_LOSSCAPTURE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	/*public static String getPlossMinutes(TableFieldType [] fieldTypeArr, Object [] dataArray){
			String sql="SELECT PLOS_LOSSTIME FROM PCS_TL_LOSSCAPTURE";
			sql += "where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
			" = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}*/

}

