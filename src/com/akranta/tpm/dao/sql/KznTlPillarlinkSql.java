package com.akranta.tpm.dao.sql;


public class KznTlPillarlinkSql {

	public static final String TBL_KZN_TL_PILLARLINK = "KZN_TL_PILLARLINK";  

	TableFieldType [] kzplDbFields = null;

	public enum   tableFldConstants
	{
		kaizenid, tpmpillarid, kzncategoryid, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getKzplDbFields() {
		return kzplDbFields;
	}

	public KznTlPillarlinkSql()
	{
		kzplDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			kzplDbFields[ i ] = new TableFieldType();
		}
		kzplDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KZPL_KAIZENID";
		kzplDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		kzplDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "KZPL_TPMPILLARID";
		kzplDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		kzplDbFields[ tableFldConstants.kzncategoryid.ordinal() ].fieldName = "KZPL_KZNCATEGORYID";
		kzplDbFields[ tableFldConstants.kzncategoryid.ordinal() ].fieldType = 'V';

		kzplDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZPL_ACTIVE";
		kzplDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kzplDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZPL_CREATEDBY";
		kzplDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzplDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZPL_CREATEDON";
		kzplDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZPL_MODIFIEDON";
		kzplDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PILLARLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PILLARLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal() ] + "'" +
			  " and " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName   +  
		  	  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal() ] + "'" ;/*+
		  	" and " + fieldTypeArr[tableFldConstants.kzncategoryid.ordinal()].fieldName   +  
		  	  " = '" +  (String)dataArray[ tableFldConstants.kzncategoryid.ordinal() ] + "'";*/
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PILLARLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal()] + "'"  +
			  " AND " + fieldTypeArr[tableFldConstants.tpmpillarid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.tpmpillarid.ordinal()] + "' " ;
			  
		return sql;
	}
	
	public static String getKznDeleteSql(TableFieldType [] fieldTypeArr, String kzplId)
	{
		String sql = " DELETE from " + TBL_KZN_TL_PILLARLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  kzplId + "' " ;
		
		return sql;
	}	

}

