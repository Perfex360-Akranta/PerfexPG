package com.akranta.tpm.dao.sql;

public class BdmTlShiftwisesplitSql {

	public static final String TBL_BDM_TL_SHIFTWISESPLIT = "BDM_TL_SHIFTWISESPLIT";  

	TableFieldType [] bdssDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, assemblyid, phenomenaid
		, causeid, bdentrydate, bdsplitdate, shiftid, bdno, downtime
		, noplantime, relatedto, activityno, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getBdssDbFields() {
		return bdssDbFields;
	}

	public BdmTlShiftwisesplitSql()
	{
		bdssDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			bdssDbFields[ i ] = new TableFieldType();
		}
		bdssDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BDSS_KEYID";
		bdssDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "BDSS_FACTORYID";
		bdssDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "BDSS_SECTIONID";
		bdssDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "BDSS_CELLID";
		bdssDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "BDSS_MACHINEID";
		bdssDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "BDSS_ASSEMBLYID";
		bdssDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "BDSS_PHENOMENAID";
		bdssDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "BDSS_CAUSEID";
		bdssDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.bdentrydate.ordinal() ].fieldName = "BDSS_BDENTRYDATE";
		bdssDbFields[ tableFldConstants.bdentrydate.ordinal() ].fieldType = 'D';

		bdssDbFields[ tableFldConstants.bdsplitdate.ordinal() ].fieldName = "BDSS_BDSPLITDATE";
		bdssDbFields[ tableFldConstants.bdsplitdate.ordinal() ].fieldType = 'D';

		bdssDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "BDSS_SHIFTID";
		bdssDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.bdno.ordinal() ].fieldName = "BDSS_BDNO";
		bdssDbFields[ tableFldConstants.bdno.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "BDSS_DOWNTIME";
		bdssDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		bdssDbFields[ tableFldConstants.noplantime.ordinal() ].fieldName = "BDSS_NOPLANTIME";
		bdssDbFields[ tableFldConstants.noplantime.ordinal() ].fieldType = 'N';

		bdssDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "BDSS_RELATEDTO";
		bdssDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		bdssDbFields[ tableFldConstants.activityno.ordinal() ].fieldName = "BDSS_ACTIVITYNO";
		bdssDbFields[ tableFldConstants.activityno.ordinal() ].fieldType = 'C';

		bdssDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BDSS_ACTIVE";
		bdssDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bdssDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BDSS_CREATEDBY";
		bdssDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bdssDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BDSS_CREATEDON";
		bdssDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bdssDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BDSS_MODIFIEDON";
		bdssDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_SHIFTWISESPLIT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_SHIFTWISESPLIT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String bdNo)
	{
		String sql = "DELETE from " + TBL_BDM_TL_SHIFTWISESPLIT ;
			   sql += " where bdss_bdno = '" +  bdNo + "'";
		return sql;
	}

}

