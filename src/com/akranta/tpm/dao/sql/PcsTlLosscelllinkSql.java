package com.akranta.tpm.dao.sql;

public class PcsTlLosscelllinkSql {

	public static final String TBL_PCS_TL_LOSSCELLLINK = "PCS_TL_LOSSCELLLINK";  

	TableFieldType [] plflDbFields = null;

	public enum   tableFldConstants
	{
		keyid, cellid, parameterid, effectivedate, inactivedate, tempfield1
		, tempfield2, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPlflDbFields() {
		return plflDbFields;
	}

	public PcsTlLosscelllinkSql()
	{
		plflDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			plflDbFields[ i ] = new TableFieldType();
		}
		plflDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PLFL_KEYID";
		plflDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PLFL_CELLID";
		plflDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.parameterid.ordinal() ].fieldName = "PLFL_PARAMETERID";
		plflDbFields[ tableFldConstants.parameterid.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "PLFL_EFFECTIVEDATE";
		plflDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		plflDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldName = "PLFL_INACTIVEDATE";
		plflDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldType = 'D';

		plflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PLFL_TEMPFIELD1";
		plflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PLFL_TEMPFIELD2";
		plflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PLFL_ACTIVE";
		plflDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		plflDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PLFL_CREATEDBY";
		plflDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plflDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PLFL_CREATEDON";
		plflDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PLFL_MODIFIEDON";
		plflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSCELLLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSCELLLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSCELLLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

