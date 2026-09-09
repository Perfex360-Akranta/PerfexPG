package com.akranta.tpm.dao.sql;

public class GenTlMchcirclelinkSql {

	public static final String TBL_GEN_TL_MCHCIRCLELINK = "GEN_TL_MCHCIRCLELINK";  

	TableFieldType [] mclkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, machineid, circleid, active, createdby, modifiedon, createdon
	}

	public TableFieldType[] getMclkDbFields() {
		return mclkDbFields;
	}

	public GenTlMchcirclelinkSql()
	{
		mclkDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			mclkDbFields[ i ] = new TableFieldType();
		}
		mclkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MCLK_KEYID";
		mclkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mclkDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MCLK_MACHINEID";
		mclkDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mclkDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "MCLK_CIRCLEID";
		mclkDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		mclkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MCLK_ACTIVE";
		mclkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mclkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MCLK_CREATEDBY";
		mclkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mclkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MCLK_MODIFIEDON";
		mclkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		mclkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MCLK_CREATEDON";
		mclkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHCIRCLELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHCIRCLELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHCIRCLELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getCircleDeleteSql(String mclkMachineid) {
		String sql = "DELETE from " + TBL_GEN_TL_MCHCIRCLELINK + " where MCLK_MACHINEID= '"+mclkMachineid+"'";
		return sql;
	}

}

