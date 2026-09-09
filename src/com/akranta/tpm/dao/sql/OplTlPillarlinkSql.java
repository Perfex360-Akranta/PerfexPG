package com.akranta.tpm.dao.sql;

public class OplTlPillarlinkSql {

	public static final String TBL_OPL_TL_PILLARLINK = "OPL_TL_PILLARLINK";  

	TableFieldType [] opplDbFields = null;

	public enum   tableFldConstants
	{
		oplid, tpmpillarid, oplcategoryid, createdby, createdon
	}

	public TableFieldType[] getOpplDbFields() {
		return opplDbFields;
	}

	public OplTlPillarlinkSql()
	{
		opplDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			opplDbFields[ i ] = new TableFieldType();
		}
		opplDbFields[ tableFldConstants.oplid.ordinal() ].fieldName = "OPPL_OPLID";
		opplDbFields[ tableFldConstants.oplid.ordinal() ].fieldType = 'V';

		opplDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "OPPL_TPMPILLARID";
		opplDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		opplDbFields[ tableFldConstants.oplcategoryid.ordinal() ].fieldName = "OPPL_OPLCATEGORYID";
		opplDbFields[ tableFldConstants.oplcategoryid.ordinal() ].fieldType = 'V';

		opplDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OPPL_CREATEDBY";
		opplDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		opplDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OPPL_CREATEDON";
		opplDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_OPL_TL_PILLARLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OPL_TL_PILLARLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.oplid.ordinal() ] + "'" +
			  " AND " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal()] + "' " ;
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = " DELETE from " + TBL_OPL_TL_PILLARLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.oplid.ordinal()] + "' " +
			  " AND " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal()] + "' " ;
		
		return sql;
	}
	
	public static String getOplDeleteSql(TableFieldType [] fieldTypeArr, String oplId)
	{
		String sql = " DELETE from " + TBL_OPL_TL_PILLARLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
			  " = '" +  oplId + "' " ;
		
		return sql;
	}	
}

