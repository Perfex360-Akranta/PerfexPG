package com.akranta.tpm.dao.sql;

public class GenTlMchmaintteamlinkSql {

	public static final String TBL_GEN_TL_MCHMAINTTEAMLINK = "GEN_TL_MCHMAINTTEAMLINK";  

	TableFieldType [] mcmtDbFields = null;

	public enum   tableFldConstants
	{
		machineid, maintenanceteamid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMcmtDbFields() {
		return mcmtDbFields;
	}

	public GenTlMchmaintteamlinkSql()
	{
		mcmtDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			mcmtDbFields[ i ] = new TableFieldType();
		}
		mcmtDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MCMT_MACHINEID";
		mcmtDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mcmtDbFields[ tableFldConstants.maintenanceteamid.ordinal() ].fieldName = "MCMT_MAINTENANCETEAMID";
		mcmtDbFields[ tableFldConstants.maintenanceteamid.ordinal() ].fieldType = 'V';

		mcmtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MCMT_TEMPFIELD1";
		mcmtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mcmtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MCMT_TEMPFIELD2";
		mcmtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mcmtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MCMT_ACTIVE";
		mcmtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mcmtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MCMT_CREATEDBY";
		mcmtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mcmtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MCMT_CREATEDON";
		mcmtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mcmtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MCMT_MODIFIEDON";
		mcmtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHMAINTTEAMLINK, fieldTypeArr, dataArray);
	}





	public static String getDeleteSql(String mchmKeyid) {

		String sql = "DELETE from " + TBL_GEN_TL_MCHMAINTTEAMLINK +" where mcmt_machineid = '" +  mchmKeyid + "'";
		return sql;
	}

}

