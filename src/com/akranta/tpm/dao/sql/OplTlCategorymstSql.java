package com.akranta.tpm.dao.sql;

public class OplTlCategorymstSql {

	public static final String TBL_OPL_TL_CATEGORYMST = "OPL_TL_CATEGORYMST";  

	TableFieldType [] oplcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, tpmpillarid, name, code, description, remarks, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getOplcDbFields() {
		return oplcDbFields;
	}

	public OplTlCategorymstSql()
	{
		oplcDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			oplcDbFields[ i ] = new TableFieldType();
		}
		oplcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OPLC_KEYID";
		oplcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "OPLC_TPMPILLARID";
		oplcDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.name.ordinal() ].fieldName = "OPLC_NAME";
		oplcDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.code.ordinal() ].fieldName = "OPLC_CODE";
		oplcDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.description.ordinal() ].fieldName = "OPLC_DESCRIPTION";
		oplcDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "OPLC_REMARKS";
		oplcDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OPLC_ACTIVE";
		oplcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		oplcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OPLC_CREATEDBY";
		oplcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		oplcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OPLC_CREATEDON";
		oplcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		oplcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OPLC_MODIFIEDON";
		oplcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_OPL_TL_CATEGORYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OPL_TL_CATEGORYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_OPL_TL_CATEGORYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getOplPillarSql(){
		return  "select  TPMP_KEYID,TPMP_NAME,TPMP_CODE,'False' as \"select\" from " + TableNames.TBL_OPL_TL_PLR ;
	}
	
	public static String getOplCategorySql(String categoryKeyid){
		
		return  "select * from " + TableNames.TBL_OPL_TL_CAT + " where OPLC_TPMPILLARID = '" + categoryKeyid + "'";

	}
	
	public static String deleteOplCategorySql()
	{
		return  "delete from " + TableNames.TBL_OPL_TL_CAT + " where OPLC_KEYID = ? ";
	}
	

}

