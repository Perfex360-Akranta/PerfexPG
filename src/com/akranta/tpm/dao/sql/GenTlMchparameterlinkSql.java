package com.akranta.tpm.dao.sql;

public class GenTlMchparameterlinkSql {

	public static final String TBL_GEN_TL_MCHPARAMETERLINK = "GEN_TL_MCHPARAMETERLINK";  

	TableFieldType [] mplkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, parameterid, description, date, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMplkDbFields() {
		return mplkDbFields;
	}

	public GenTlMchparameterlinkSql()
	{
		mplkDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			mplkDbFields[ i ] = new TableFieldType();
		}
		mplkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MPLK_KEYID";
		mplkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.parameterid.ordinal() ].fieldName = "MPLK_PARAMETERID";
		mplkDbFields[ tableFldConstants.parameterid.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.description.ordinal() ].fieldName = "MPLK_DESCRIPTION";
		mplkDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MPLK_DATE";
		mplkDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		mplkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPLK_TEMPFIELD1";
		mplkDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPLK_TEMPFIELD2";
		mplkDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPLK_ACTIVE";
		mplkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mplkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPLK_CREATEDBY";
		mplkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mplkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPLK_CREATEDON";
		mplkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mplkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPLK_MODIFIEDON";
		mplkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHPARAMETERLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHPARAMETERLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	
	
	public static String getDeleteSql(String MachineId)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHPARAMETERLINK ;
			   sql += " where MPLK_KEYID = '" +  MachineId + "'";
		return sql;
	}

}

