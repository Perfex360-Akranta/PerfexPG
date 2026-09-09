package com.akranta.tpm.dao.sql;

public class PcsTlCycletimehistorySql {

	public static final String TBL_PCS_TL_CYCLETIMEHISTORY = "PCS_TL_CYCLETIMEHISTORY";  

	TableFieldType [] cythDbFields = null;

	public enum   tableFldConstants
	{
		keyid, cellid, productid, cycletime, manpower, fromdate, tilldate
		, prodgroupid, machineid, sectionid, factoryid, cavity, mandrels
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getCythDbFields() {
		return cythDbFields;
	}

	public PcsTlCycletimehistorySql()
	{
		cythDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			cythDbFields[ i ] = new TableFieldType();
		}
		cythDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CYTH_KEYID";
		cythDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "CYTH_CELLID";
		cythDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "CYTH_PRODUCTID";
		cythDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.cycletime.ordinal() ].fieldName = "CYTH_CYCLETIME";
		cythDbFields[ tableFldConstants.cycletime.ordinal() ].fieldType = 'N';

		cythDbFields[ tableFldConstants.manpower.ordinal() ].fieldName = "CYTH_MANPOWER";
		cythDbFields[ tableFldConstants.manpower.ordinal() ].fieldType = 'N';

		cythDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "CYTH_FROMDATE";
		cythDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		cythDbFields[ tableFldConstants.tilldate.ordinal() ].fieldName = "CYTH_TILLDATE";
		cythDbFields[ tableFldConstants.tilldate.ordinal() ].fieldType = 'D';

		cythDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldName = "CYTH_PRODGROUPID";
		cythDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "CYTH_MACHINEID";
		cythDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "CYTH_SECTIONID";
		cythDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "CYTH_FACTORYID";
		cythDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.cavity.ordinal() ].fieldName = "CYTH_CAVITY";
		cythDbFields[ tableFldConstants.cavity.ordinal() ].fieldType = 'N';

		cythDbFields[ tableFldConstants.mandrels.ordinal() ].fieldName = "CYTH_MANDRELS";
		cythDbFields[ tableFldConstants.mandrels.ordinal() ].fieldType = 'N';

		cythDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CYTH_TEMPFIELD1";
		cythDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		cythDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CYTH_TEMPFIELD2";
		cythDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		cythDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CYTH_TEMPFIELD3";
		cythDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		cythDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CYTH_ACTIVE";
		cythDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cythDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CYTH_CREATEDBY";
		cythDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cythDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CYTH_CREATEDON";
		cythDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cythDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CYTH_MODIFIEDON";
		cythDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_CYCLETIMEHISTORY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_CYCLETIMEHISTORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_CYCLETIMEHISTORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

