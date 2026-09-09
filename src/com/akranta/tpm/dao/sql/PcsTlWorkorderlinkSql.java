package com.akranta.tpm.dao.sql;

public class PcsTlWorkorderlinkSql {

	public static final String TBL_PCS_TL_WORKORDERLINK = "PCS_TL_WORKORDERLINK";  

	TableFieldType [] ptwoDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pldetailid, masterid, wno, theoriticalcycletime, actualcycletime
		, calendartime, noplaninmins, plannedqty, producedqty, rejectionqty
		, unaccountedtime, productiontime, temp1, temp2, temp3, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPtwoDbFields() {
		return ptwoDbFields;
	}

	public PcsTlWorkorderlinkSql()
	{
		ptwoDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			ptwoDbFields[ i ] = new TableFieldType();
		}
		ptwoDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PTWO_KEYID";
		ptwoDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldName = "PTWO_PLDETAILID";
		ptwoDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "PTWO_MASTERID";
		ptwoDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.wno.ordinal() ].fieldName = "PTWO_WNO";
		ptwoDbFields[ tableFldConstants.wno.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.theoriticalcycletime.ordinal() ].fieldName = "PTWO_THEORITICALCYCLETIME";
		ptwoDbFields[ tableFldConstants.theoriticalcycletime.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.actualcycletime.ordinal() ].fieldName = "PTWO_ACTUALCYCLETIME";
		ptwoDbFields[ tableFldConstants.actualcycletime.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.calendartime.ordinal() ].fieldName = "PTWO_CALENDARTIME";
		ptwoDbFields[ tableFldConstants.calendartime.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.noplaninmins.ordinal() ].fieldName = "PTWO_NOPLANINMINS";
		ptwoDbFields[ tableFldConstants.noplaninmins.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.plannedqty.ordinal() ].fieldName = "PTWO_PLANNEDQTY";
		ptwoDbFields[ tableFldConstants.plannedqty.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.producedqty.ordinal() ].fieldName = "PTWO_PRODUCEDQTY";
		ptwoDbFields[ tableFldConstants.producedqty.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.rejectionqty.ordinal() ].fieldName = "PTWO_REJECTIONQTY";
		ptwoDbFields[ tableFldConstants.rejectionqty.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldName = "PTWO_UNACCOUNTEDTIME";
		ptwoDbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.productiontime.ordinal() ].fieldName = "PTWO_PRODUCTIONTIME";
		ptwoDbFields[ tableFldConstants.productiontime.ordinal() ].fieldType = 'N';

		ptwoDbFields[ tableFldConstants.temp1.ordinal() ].fieldName = "PTWO_TEMP1";
		ptwoDbFields[ tableFldConstants.temp1.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "PTWO_TEMP2";
		ptwoDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "PTWO_TEMP3";
		ptwoDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PTWO_ACTIVE";
		ptwoDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ptwoDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PTWO_CREATEDBY";
		ptwoDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ptwoDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PTWO_CREATEDON";
		ptwoDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ptwoDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PTWO_MODIFIEDON";
		ptwoDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_WORKORDERLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_WORKORDERLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_WORKORDERLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

