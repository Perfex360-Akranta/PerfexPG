package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.KznTlPillarlinkSql.tableFldConstants;

public class KznTlLosslinkSql {

	public static final String TBL_KZN_TL_LOSSLINK = "KZN_TL_LOSSLINK";  

	TableFieldType [] kzllDbFields = null;

	public enum   tableFldConstants
	{
		kaizenid, tpmpillarid, lossid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKzllDbFields() {
		return kzllDbFields;
	}

	public KznTlLosslinkSql()
	{
		kzllDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			kzllDbFields[ i ] = new TableFieldType();
		}
		kzllDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KZLL_KAIZENID";
		kzllDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		kzllDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "KZLL_TPMPILLARID";
		kzllDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		kzllDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "KZLL_LOSSID";
		kzllDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		kzllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZLL_ACTIVE";
		kzllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kzllDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZLL_CREATEDBY";
		kzllDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzllDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZLL_CREATEDON";
		kzllDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZLL_MODIFIEDON";
		kzllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_LOSSLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_LOSSLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal() ] + "'" +
			  " AND " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_LOSSLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal()] + "'"  +
			  " AND " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal() ] + "'";
		return sql;
	}

	public static String getKznDeleteSql(TableFieldType [] fieldTypeArr, 	String kznplkaizenid) {
		String sql = " DELETE from " + TBL_KZN_TL_LOSSLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  kznplkaizenid + "' " ;
		
		return sql;
	}

}

