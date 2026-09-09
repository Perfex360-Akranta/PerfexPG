package com.akranta.tpm.dao.sql;

public class KznTlGraphdataSql {

	public static final String TBL_KZN_TL_GRAPHDATA = "KZN_TL_GRAPHDATA";  

	TableFieldType [] kzgdDbFields = null;

	public enum   tableFldConstants
	{
		kaizenid, datemonthyear, beforedata, afterdata, charttype, createdon
	}

	public TableFieldType[] getKzgdDbFields() {
		return kzgdDbFields;
	}

	public KznTlGraphdataSql()
	{
		kzgdDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			kzgdDbFields[ i ] = new TableFieldType();
		}
		kzgdDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KZGD_KAIZENID";
		kzgdDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		kzgdDbFields[ tableFldConstants.datemonthyear.ordinal() ].fieldName = "KZGD_DATEMONTHYEAR";
		kzgdDbFields[ tableFldConstants.datemonthyear.ordinal() ].fieldType = 'D';

		kzgdDbFields[ tableFldConstants.beforedata.ordinal() ].fieldName = "KZGD_BEFOREDATA";
		kzgdDbFields[ tableFldConstants.beforedata.ordinal() ].fieldType = 'N';

		kzgdDbFields[ tableFldConstants.afterdata.ordinal() ].fieldName = "KZGD_AFTERDATA";
		kzgdDbFields[ tableFldConstants.afterdata.ordinal() ].fieldType = 'N';

		kzgdDbFields[ tableFldConstants.charttype.ordinal() ].fieldName = "KZGD_CHARTTYPE";
		kzgdDbFields[ tableFldConstants.charttype.ordinal() ].fieldType = 'C';

		kzgdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZGD_CREATEDON";
		kzgdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_GRAPHDATA, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_GRAPHDATA, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_GRAPHDATA ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kaizenid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDeleteKznSql(TableFieldType [] fieldTypeArr, String kzgdKaizenId)
	{
		String sql = "DELETE from " + TBL_KZN_TL_GRAPHDATA ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" + kzgdKaizenId +"'";
		return sql;
	}

}

