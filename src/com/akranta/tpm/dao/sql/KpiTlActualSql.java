package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.KpiTlActualKkSql.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KpiTlActualSql {

	public static final String TBL_KPI_TL_ACTUAL = "KPI_TL_ACTUAL";  

	TableFieldType [] kaukDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorid, deptid, depttype, pillarid, calendaryear
		, monthyear, excellencevalue, benchmarkvalue, value, isactual
		, freqtype, status, tempfield1, tempfield2, tempfield3, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKaukDbFields() {
		return kaukDbFields;
	}

	public KpiTlActualSql()
	{
		kaukDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			kaukDbFields[ i ] = new TableFieldType();
		}
		kaukDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KAUK_KEYID";
		kaukDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldName = "KAUK_INDICATORID";
		kaukDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.deptid.ordinal() ].fieldName = "KAUK_DEPTID";
		kaukDbFields[ tableFldConstants.deptid.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.depttype.ordinal() ].fieldName = "KAUK_DEPTTYPE";
		kaukDbFields[ tableFldConstants.depttype.ordinal() ].fieldType = 'V';

		/*kaukDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "KAUK_TEMPFIELD";
		kaukDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'V';*/

		kaukDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "KAUK_PILLARID";
		kaukDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.calendaryear.ordinal() ].fieldName = "KAUK_CALENDARYEAR";
		kaukDbFields[ tableFldConstants.calendaryear.ordinal() ].fieldType = 'N';

		kaukDbFields[ tableFldConstants.monthyear.ordinal() ].fieldName = "KAUK_MONTHYEAR";
		kaukDbFields[ tableFldConstants.monthyear.ordinal() ].fieldType = 'D';

		kaukDbFields[ tableFldConstants.excellencevalue.ordinal() ].fieldName = "KAUK_EXCELLENCEVALUE";
		kaukDbFields[ tableFldConstants.excellencevalue.ordinal() ].fieldType = 'N';

		kaukDbFields[ tableFldConstants.benchmarkvalue.ordinal() ].fieldName = "KAUK_BENCHMARKVALUE";
		kaukDbFields[ tableFldConstants.benchmarkvalue.ordinal() ].fieldType = 'N';

		kaukDbFields[ tableFldConstants.value.ordinal() ].fieldName = "KAUK_VALUE";
		kaukDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		kaukDbFields[ tableFldConstants.isactual.ordinal() ].fieldName = "KAUK_ISACTUAL";
		kaukDbFields[ tableFldConstants.isactual.ordinal() ].fieldType = 'C';

		kaukDbFields[ tableFldConstants.freqtype.ordinal() ].fieldName = "KAUK_FREQTYPE";
		kaukDbFields[ tableFldConstants.freqtype.ordinal() ].fieldType = 'C';

		kaukDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KAUK_STATUS";
		kaukDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		kaukDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KAUK_TEMPFIELD1";
		kaukDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KAUK_TEMPFIELD2";
		kaukDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KAUK_TEMPFIELD3";
		kaukDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KAUK_ACTIVE";
		kaukDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kaukDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KAUK_CREATEDBY";
		kaukDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kaukDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KAUK_CREATEDON";
		kaukDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kaukDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KAUK_MODIFIEDON";
		kaukDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getPillarKeyIdSql(String code)
	{
		String sql = " SELECT TPMP_KEYID FROM " + TableNames.TBL_GEN_TL_TPMPILLARMST ;		
		sql += " WHERE TPMP_CODE='"+code+"' " ;
		return sql;
	}
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "SELECT * from " + TableNames.TBL_KPI_TL_ACTUAL ;		
		sql += " where 1=1 " ;
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()]))
				sql += " and " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.indicatorid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.indicatorid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.indicatorid.ordinal()] + "'";
		
		return sql;
	}
	public static String getSelectDateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql=null;
		sql ="SELECT   * ";
		sql = sql + " FROM (SELECT TO_char (monthyear, 'DD-MON-YYYY') as monthyear, KAUK_KEYID,KAUK_VALUE,KAUK_INDICATORID,KAUK_MONTHYEAR,KAUK_FREQTYPE ";
		sql = sql + " FROM ((SELECT TO_DATE('"+ (String)dataArray[ tableFldConstants.monthyear.ordinal()]+"') + ROWNUM-1 AS monthyear  FROM TAB " ;
		sql = sql + " WHERE   ROWNUM < = TO_NUMBER(TO_CHAR(LAST_DAY('"+ (String)dataArray[ tableFldConstants.monthyear.ordinal()]+"'),'DD')))) a ";
		sql = sql + " LEFT JOIN  " + TableNames.TBL_KPI_TL_ACTUAL;
		sql = sql + " ON monthyear = KAUK_MONTHYEAR " ;
		sql = sql + " and KAUK_FREQTYPE='D'" ;
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.indicatorid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.indicatorid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.indicatorid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.deptid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.deptid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.deptid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.pillarid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.pillarid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.pillarid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.calendaryear.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.calendaryear.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.calendaryear.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.isactual.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.isactual.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.isactual.ordinal()] + "'";
		
		
		sql += " ) ";
		
		sql = sql + " ORDER BY TO_DATE (monthyear, 'DD-MON-YYYY') ";
		return sql;
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KPI_TL_ACTUAL, fieldTypeArr, dataArray);
	}
	public static String getSelectStartMonthSql()
	{
		String sql = " SELECT CNFM_SETTINGVALUE FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST ;		
		sql += " WHERE CNFM_CODE='ENTPROGSTARTMONTH' " ;
		return sql;
	}
	

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KPI_TL_ACTUAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KPI_TL_ACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public String getSelectWeekSql(TableFieldType[] fieldTypeArr,Object[] dataArray) {
		// TODO Auto-generated method stub
		String sql=null;
		sql = "SELECT  max(kauk_indicatorid), max(kauk_freqtype), TO_CHAR(MAX(DECODE(week, 'W1', kauk_keyid))) AS W1ID, TO_CHAR(SUM(DECODE(week, 'W1', kauk_value, 0))) AS W1VAL, ";
		sql = sql + " TO_CHAR(MAX(DECODE(week, 'W2', kauk_keyid))) AS W2ID, TO_CHAR(SUM(DECODE(week, 'W2', kauk_value, 0))) AS W2VAL, ";
		sql = sql + " TO_CHAR(MAX(DECODE(week, 'W3', kauk_keyid))) AS W3ID, TO_CHAR(SUM(DECODE(week, 'W3', kauk_value, 0))) AS W3VAL, ";
		sql = sql + " TO_CHAR(MAX(DECODE(week, 'W4', kauk_keyid))) AS W4ID, TO_CHAR(SUM(DECODE(week, 'W4', kauk_value, 0))) AS W4VAL FROM ( ";
		sql = sql + " SELECT TO_char (monthyear, 'DD-MON-YYYY') as monthyear, WEEK, KAUK_KEYID,KAUK_VALUE,KAUK_INDICATORID,KAUK_MONTHYEAR,KAUK_FREQTYPE ";
		sql = sql + " FROM (SELECT TO_DATE('01-"+ (String)dataArray[ tableFldConstants.monthyear.ordinal()]+"') + ((ROWNUM - 1)*7) AS monthyear " ;
		sql = sql + " , 'W' || ROWNUM AS WEEK FROM tab WHERE ROWNUM <= 4) a";
		sql = sql + " LEFT JOIN  " + TableNames.TBL_KPI_TL_ACTUAL;
		sql = sql + " ON monthyear = KAUK_MONTHYEAR " ;
		sql = sql + " and KAUK_FREQTYPE='W'" ;
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.indicatorid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.indicatorid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.indicatorid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.deptid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.deptid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.deptid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.depttype.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.depttype.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.depttype.ordinal()] + "'";
		
		/*if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.tempfield.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.tempfield.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.tempfield.ordinal()] + "'";
		*/
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.pillarid.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.pillarid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.pillarid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.calendaryear.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.calendaryear.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.calendaryear.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.isactual.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.isactual.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.isactual.ordinal()] + "'";
		
		
		
		
		sql = sql + " )";
				//"WHERE kauk_indicatorid IS NOT NULL  GROUP BY kauk_indicatorid, kauk_freqtype ";
		return sql;
	}

	public static String getDelete1Sql(String kaukMonthyear, String kaukKeyid) {
		// TODO Auto-generated method stub
		return "DELETE FROM "+TableNames.TBL_KPI_TL_ACTUAL+"WHERE KAUK_MONTHYEAR='"+kaukMonthyear+"' AND KAUK_KEYID='"+kaukKeyid+"'" ;
	}
}

