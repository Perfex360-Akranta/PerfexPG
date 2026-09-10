package com.akranta.tpm.dao.sql;

public class BAL_PlmTlShutdowncalSql {

	public static final String TBL_PLM_TL_SHUTDOWNCAL = "PLM_TL_SHUTDOWNCAL";  

	TableFieldType [] sdclDbFields = null;

	public enum   tableFldConstants
	{
		keyid, companyid, locationid, factoryid, sectionid, cellid, machineid
		, assemblyid, frequnit, fromdate, tilldate, relatedto, mouldid
		, flid,elementid,tempfield3,active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getSdclDbFields() {
		return sdclDbFields;
	}

	public BAL_PlmTlShutdowncalSql()
	{
		sdclDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			sdclDbFields[ i ] = new TableFieldType();
		}
		sdclDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SDCL_KEYID";
		sdclDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "SDCL_COMPANYID";
		sdclDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "SDCL_LOCATIONID";
		sdclDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "SDCL_FACTORYID";
		sdclDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "SDCL_SECTIONID";
		sdclDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "SDCL_CELLID";
		sdclDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "SDCL_MACHINEID";
		sdclDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "SDCL_ASSEMBLYID";
		sdclDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.frequnit.ordinal() ].fieldName = "SDCL_FREQUNIT";
		sdclDbFields[ tableFldConstants.frequnit.ordinal() ].fieldType = 'C';

		sdclDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "SDCL_FROMDATE";
		sdclDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		sdclDbFields[ tableFldConstants.tilldate.ordinal() ].fieldName = "SDCL_TILLDATE";
		sdclDbFields[ tableFldConstants.tilldate.ordinal() ].fieldType = 'D';

		sdclDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "SDCL_RELATEDTO";
		sdclDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		sdclDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "SDCL_MOULDID";
		sdclDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SDCL_FLID";
		sdclDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		sdclDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "SDCL_ELEMENTID";
		sdclDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SDCL_TEMPFIELD3";
		sdclDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		sdclDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SDCL_ACTIVE";
		sdclDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sdclDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SDCL_CREATEDBY";
		sdclDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sdclDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SDCL_CREATEDON";
		sdclDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sdclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SDCL_MODIFIEDON";
		sdclDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_SHUTDOWNCAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_SHUTDOWNCAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_SHUTDOWNCAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

