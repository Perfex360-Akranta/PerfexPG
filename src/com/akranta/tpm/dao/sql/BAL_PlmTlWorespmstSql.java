package com.akranta.tpm.dao.sql;

public class BAL_PlmTlWorespmstSql {

	public static final String TBL_BAL_PLM_TL_WORESPMST = "BAL_PLM_TL_WORESPMST";  

	TableFieldType [] pwrmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, level, tradewise
		, general, tempfield1, tempfield2, tempfield3, tempfield4, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPwrmDbFields() {
		return pwrmDbFields;
	}

	public BAL_PlmTlWorespmstSql()
	{
		pwrmDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			pwrmDbFields[ i ] = new TableFieldType();
		}
		pwrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PWRM_KEYID";
		pwrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PWRM_FACTORYID";
		pwrmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PWRM_SECTIONID";
		pwrmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PWRM_CELLID";
		pwrmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PWRM_MACHINEID";
		pwrmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.level.ordinal() ].fieldName = "PWRM_LEVEL";
		pwrmDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.tradewise.ordinal() ].fieldName = "PWRM_TRADEWISE";
		pwrmDbFields[ tableFldConstants.tradewise.ordinal() ].fieldType = 'C';

		pwrmDbFields[ tableFldConstants.general.ordinal() ].fieldName = "PWRM_GENERAL";
		pwrmDbFields[ tableFldConstants.general.ordinal() ].fieldType = 'C';

		pwrmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PWRM_TEMPFIELD1";
		pwrmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PWRM_TEMPFIELD2";
		pwrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PWRM_TEMPFIELD3";
		pwrmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PWRM_TEMPFIELD4";
		pwrmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PWRM_ACTIVE";
		pwrmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pwrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PWRM_CREATEDBY";
		pwrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pwrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PWRM_CREATEDON";
		pwrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pwrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PWRM_MODIFIEDON";
		pwrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_WORESPMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_WORESPMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_WORESPMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

