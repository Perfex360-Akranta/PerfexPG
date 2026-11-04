package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;

public class GenTlHolidaymstSql {

	public static final String TBL_GEN_TL_HOLIDAYMST = "GEN_TL_HOLIDAYMST";

	private static final String TBL_GEN_TL_FACTORYMST = "GEN_TL_FACTORYMST";  
	
	private static final String TBL_GEN_TL_LOCATIONMST = "GEN_TL_LOCATIONMST";  

	TableFieldType [] holmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, date, description, factory, holidayflag, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getHolmDbFields() {
		return holmDbFields;
	}

	public GenTlHolidaymstSql()
	{
		holmDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			holmDbFields[ i ] = new TableFieldType();
		}
		holmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "HOLM_KEYID";
		holmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		holmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "HOLM_NAME";
		holmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		holmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "HOLM_DATE";
		holmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		holmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "HOLM_DESCRIPTION";
		holmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		holmDbFields[ tableFldConstants.factory.ordinal() ].fieldName = "HOLM_FACTORY";
		holmDbFields[ tableFldConstants.factory.ordinal() ].fieldType = 'V';

		holmDbFields[ tableFldConstants.holidayflag.ordinal() ].fieldName = "HOLM_HOLIDAYFLAG";
		holmDbFields[ tableFldConstants.holidayflag.ordinal() ].fieldType = 'C';

		holmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "HOLM_CREATEDBY";
		holmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		holmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "HOLM_CREATEDON";
		holmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		holmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "HOLM_MODIFIEDON";
		holmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_HOLIDAYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_HOLIDAYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_HOLIDAYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

public static String getHolidaymstSql(GridParams gridParams) {
		
		//return " SELECT * from " + TBL_GEN_TL_HOLIDAYMST + " where HOLM_FACTORY = ? AND HOLM_HOLIDAYFLAG = 'H'";
		//return "SELECT * FROM (SELECT ROWNUM AS SLNO, A.* FROM (SELECT HOLM_KEYID,FACT_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_FACTORYMST+" WHERE HOLM_FACTORY = FACT_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = ? AND HOLM_HOLIDAYFLAG = ? AND HOLM_FACTORY= ? ) A) WHERE 1=1 AND SLNO  >= "+gridParams.getFromRow()+" AND SLNO <= "+gridParams.getToRow();
	return "SELECT * FROM (" +
    "SELECT ROW_NUMBER() OVER (ORDER BY HOLM_DATE, HOLM_KEYID) AS SLNO, A.* " +
    "FROM (" +
    "SELECT HOLM_KEYID, LOCN_NAME, HOLM_NAME, " +
    "TO_CHAR(HOLM_DATE, 'DD-MON-YYYY') AS HOLM_DATE, " +
    "HOLM_DESCRIPTION " +
    "FROM " + TBL_GEN_TL_HOLIDAYMST + " H " +
    "JOIN " + TBL_GEN_TL_LOCATIONMST + " F " +
    "ON H.HOLM_FACTORY = F.LOCN_KEYID " +
    "WHERE TO_CHAR(HOLM_DATE, 'YYYY') = ? " +
    "AND HOLM_HOLIDAYFLAG = ? " +
    "AND HOLM_FACTORY = ? " +
    ") A" +
    ") B " +
    "WHERE SLNO >= " + gridParams.getFromRow() +
    " AND SLNO <= " + gridParams.getToRow();
	}




			public static String getAllHolidaymstSql() {
				return "SELECT HOLM_KEYID,LOCN_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM "+  TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND HOLM_HOLIDAYFLAG = 'H' ";
			}
			public static String getRecalHolidaymstSql() {
				return "SELECT * FROM "+  TBL_GEN_TL_HOLIDAYMST +", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND HOLM_KEYID = ?";
			}

	public static String deleteNew(String keyid) {
		return "DELETE FROM "+TBL_GEN_TL_HOLIDAYMST +" WHERE HOLM_KEYID = '"+keyid+"'";
	}

	public static String getDeleteHolidaymstSql(String year,String flag,String keyid,String desc) {
//		return "DELETE FROM " +TBL_GEN_TL_HOLIDAYMST+" WHERE TO_CHAR(HOLM_DATE,'YYYY') = '"+year+
//		"' AND HOLM_HOLIDAYFLAG = '"+flag+"'  AND HOLM_FACTORY= '"+keyid+"' AND HOLM_DESCRIPTION = '"+desc+"'";
		
		return "DELETE FROM GEN_TL_HOLIDAYMST WHERE TO_CHAR(HOLM_DATE,'YYYY') = '"+year+"' AND TRUNC(HOLM_DATE) >= SYSDATE " +
				"AND HOLM_HOLIDAYFLAG = '"+flag+"'  AND HOLM_FACTORY= '"+keyid+"' AND HOLM_DESCRIPTION = '<*"+desc+"*>'";
	}
	
	public static String getFlagHolidaymstSql() {
		return "SELECT HOLM_KEYID,LOCN_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM "+  TBL_GEN_TL_HOLIDAYMST +", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND HOLM_HOLIDAYFLAG = ?" ;
	}

	public static String getHolidaySql() {
		return "SELECT HOLM_KEYID,LOCN_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM "+  TBL_GEN_TL_HOLIDAYMST +", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND HOLM_HOLIDAYFLAG = 'H'" ;
	}

	public static String getHolidayYearFlagSql(GridParams gridParams) {
		//return "SELECT * FROM (SELECT ROWNUM AS SLNO, A.* FROM (SELECT HOLM_KEYID,FACT_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_FACTORYMST+" WHERE HOLM_FACTORY = FACT_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = ? AND HOLM_HOLIDAYFLAG = ? ) A) WHERE 1=1 AND SLNO  >= "+gridParams.getFromRow()+" AND SLNO <= "+gridParams.getToRow();
		return  "SELECT * FROM (" +
	       "SELECT ROW_NUMBER() OVER (ORDER BY HOLM_DATE, HOLM_KEYID) AS SLNO, " +
	       "       HOLM_KEYID, LOCN_NAME, HOLM_NAME, " +
	       "       TO_CHAR(HOLM_DATE, 'DD-MON-YYYY') AS HOLM_DATE, " +
	       "       HOLM_DESCRIPTION " +
	       "FROM " + TBL_GEN_TL_HOLIDAYMST + " H " +
	       "JOIN " + TBL_GEN_TL_LOCATIONMST + " F " +
	       "  ON H.HOLM_FACTORY = F.LOCN_KEYID " +
	       "WHERE EXTRACT(YEAR FROM H.HOLM_DATE) = ? " +
	       "  AND H.HOLM_HOLIDAYFLAG = ? " +
	       ") A " +
	       "WHERE SLNO >= " + gridParams.getFromRow() +
	       " AND SLNO <= " + gridParams.getToRow();
	}

	public static String getHolidayYearFlagSqlCount() {
		return "SELECT HOLM_KEYID,LOCN_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = ? AND HOLM_HOLIDAYFLAG = ? ";
	}

	public static String getHolidaymstSqlCnt() {
		return "SELECT HOLM_KEYID,LOCN_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_LOCATIONMST+" WHERE HOLM_FACTORY = LOCN_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = ? AND HOLM_HOLIDAYFLAG = ? AND HOLM_FACTORY= ?";
	}

	public static String getHolidaymstSqlWithFact(String keyId, String flag,String year, GridParams gridParams) {
		
		//return "SELECT * FROM (SELECT ROWNUM AS SLNO, A.* FROM (SELECT HOLM_KEYID,FACT_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_FACTORYMST+" WHERE HOLM_FACTORY = FACT_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = '"+ year +"' AND HOLM_HOLIDAYFLAG = '"+flag+"' AND HOLM_FACTORY= '"+keyId+"' ) A) WHERE 1=1 AND SLNO  >= "+gridParams.getFromRow()+" AND SLNO <= "+gridParams.getToRow();
		
		return "SELECT * FROM (" +
	       "SELECT ROW_NUMBER() OVER (ORDER BY HOLM_DATE, HOLM_KEYID) AS SLNO, " +
	       "HOLM_KEYID, LOCN_NAME, HOLM_NAME, " +
	       "TO_CHAR(HOLM_DATE, 'DD-MON-YYYY') AS HOLM_DATE, " +
	       "HOLM_DESCRIPTION " +
	       "FROM " + TBL_GEN_TL_HOLIDAYMST + " H " +
	       "JOIN " + TBL_GEN_TL_LOCATIONMST + " F " +
	       "ON H.HOLM_FACTORY = F.LOCN_KEYID " +
	       "WHERE EXTRACT(YEAR FROM H.HOLM_DATE) = " + year +
	       " AND H.HOLM_HOLIDAYFLAG = '" + flag + "'" +
	       " AND H.HOLM_FACTORY = '" + keyId + "'" +
	       ") A " +
	       "WHERE SLNO >= " + gridParams.getFromRow() +
	       " AND SLNO <= " + gridParams.getToRow();
	}

	public static String getHolidaymstSqlExcel(String flag, String year,GridParams gridParams) {
		//return "SELECT * FROM (SELECT ROWNUM AS SLNO, A.* FROM (SELECT HOLM_KEYID,FACT_NAME, HOLM_NAME,TO_CHAR(HOLM_DATE,'DD-MON-YYYY'),HOLM_DESCRIPTION FROM " +TBL_GEN_TL_HOLIDAYMST+", "+TBL_GEN_TL_FACTORYMST+" WHERE HOLM_FACTORY = FACT_KEYID AND TO_CHAR(HOLM_DATE,'YYYY') = '"+ year +"' AND HOLM_HOLIDAYFLAG = '"+flag+"') A) WHERE 1=1 AND SLNO  >= "+gridParams.getFromRow()+" AND SLNO <= "+gridParams.getToRow();
		return "SELECT * FROM (" +
	       "SELECT ROW_NUMBER() OVER (ORDER BY HOLM_DATE, HOLM_KEYID) AS SLNO, A.* " +
	       "FROM (" +
	       "SELECT HOLM_KEYID, LOCN_NAME, HOLM_NAME, " +
	       "TO_CHAR(HOLM_DATE, 'DD-MON-YYYY') AS HOLM_DATE, " +
	       "HOLM_DESCRIPTION " +
	       "FROM " + TBL_GEN_TL_HOLIDAYMST + " H " +
	       "JOIN " + TBL_GEN_TL_LOCATIONMST + " F " +
	       "ON H.HOLM_FACTORY = F.LOCN_KEYID " +
	       "WHERE TO_CHAR(HOLM_DATE, 'YYYY') = '" + year + "' " +
	       "AND HOLM_HOLIDAYFLAG = '" + flag + "'" +
	       ") A" +
	       ") B " +
	       "WHERE SLNO >= " + gridParams.getFromRow() +
	       " AND SLNO <= " + gridParams.getToRow();
	}

	
}