package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PlmTlPlanconfigurationSql {

	public static final String TBL_BAL_PLM_TL_PLANCONFIGURATION = "BAL_PLM_TL_PLANCONFIGURATION";

	private static final String TBL_BAL_PLM_TL_WORESPDTL = "BAL_PLM_TL_WORESPDTL";

	private static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";

	private static final String TBL_GEN_TL_TRADEMST = "GEN_TL_TRADEMST" ;

	private static final String TBL_GEN_TL_MACHINEMST = "GEN_TL_MACHINEMST";  

	TableFieldType [] pplcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, level, weekno
		, monthly, quarterly, halfyearly, yearly, yearly2, yearly3, yearly4
		, yearly5, yearly6, yearly7, yearly8, yearly9, yearly10, frequency
		, assemblyid, tempfield1, tempfield2, tempfield3, tempfield4
		, flid,elementid,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPplcDbFields() {
		return pplcDbFields;
	}

	public BAL_PlmTlPlanconfigurationSql()
	{
		pplcDbFields = new TableFieldType[ 32 ];
		for(int i = 0;i < 32; i++)
		{	
			pplcDbFields[ i ] = new TableFieldType();
		}
		pplcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PPLC_KEYID";
		pplcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PPLC_FACTORYID";
		pplcDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PPLC_SECTIONID";
		pplcDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PPLC_CELLID";
		pplcDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PPLC_MACHINEID";
		pplcDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.level.ordinal() ].fieldName = "PPLC_LEVEL";
		pplcDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.weekno.ordinal() ].fieldName = "PPLC_WEEKNO";
		pplcDbFields[ tableFldConstants.weekno.ordinal() ].fieldType = 'C';

		pplcDbFields[ tableFldConstants.monthly.ordinal() ].fieldName = "PPLC_MONTHLY";
		pplcDbFields[ tableFldConstants.monthly.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.quarterly.ordinal() ].fieldName = "PPLC_QUARTERLY";
		pplcDbFields[ tableFldConstants.quarterly.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.halfyearly.ordinal() ].fieldName = "PPLC_HALFYEARLY";
		pplcDbFields[ tableFldConstants.halfyearly.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly.ordinal() ].fieldName = "PPLC_YEARLY";
		pplcDbFields[ tableFldConstants.yearly.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly2.ordinal() ].fieldName = "PPLC_YEARLY2";
		pplcDbFields[ tableFldConstants.yearly2.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly3.ordinal() ].fieldName = "PPLC_YEARLY3";
		pplcDbFields[ tableFldConstants.yearly3.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly4.ordinal() ].fieldName = "PPLC_YEARLY4";
		pplcDbFields[ tableFldConstants.yearly4.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly5.ordinal() ].fieldName = "PPLC_YEARLY5";
		pplcDbFields[ tableFldConstants.yearly5.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly6.ordinal() ].fieldName = "PPLC_YEARLY6";
		pplcDbFields[ tableFldConstants.yearly6.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly7.ordinal() ].fieldName = "PPLC_YEARLY7";
		pplcDbFields[ tableFldConstants.yearly7.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly8.ordinal() ].fieldName = "PPLC_YEARLY8";
		pplcDbFields[ tableFldConstants.yearly8.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly9.ordinal() ].fieldName = "PPLC_YEARLY9";
		pplcDbFields[ tableFldConstants.yearly9.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.yearly10.ordinal() ].fieldName = "PPLC_YEARLY10";
		pplcDbFields[ tableFldConstants.yearly10.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "PPLC_FREQUENCY";
		pplcDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "PPLC_ASSEMBLYID";
		pplcDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PPLC_TEMPFIELD1";
		pplcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PPLC_TEMPFIELD2";
		pplcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PPLC_TEMPFIELD3";
		pplcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PPLC_TEMPFIELD4";
		pplcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PPLC_FLID";
		pplcDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		pplcDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PPLC_ELEMENTID";
		pplcDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		pplcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PPLC_ACTIVE";
		pplcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pplcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PPLC_CREATEDBY";
		pplcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pplcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PPLC_CREATEDON";
		pplcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pplcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PPLC_MODIFIEDON";
		pplcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_PLANCONFIGURATION, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_PLANCONFIGURATION, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		CommonFunctions.debugMsg("SQL   :"+sql); 
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_PLANCONFIGURATION ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getPlanConfigListSql() {
		// TODO Auto-generated method stub
		return "PLM_PC_PLANNEDMAINT.PLM_FN_GETPMPLANCONFIG";
	}

	/*public static String getyearhirerachy(String selYear) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder( "SELECT FREQ,TO_CHAR(MIN(NEXTDUEDATE),'DD-MON-YYYY') AS NEXTDUEDATE ");
		sql.append(" FROM ( SELECT FREQ, DECODE(FREQ ,'Y',ADD_MONTHS(EDATE,12 * (ANTB_WEEKNAME+1)),");
		sql.append(" 'H', ADD_MONTHS(EDATE,6 * ANTB_WEEKNAME),'Q',ADD_MONTHS (EDATE,3 * ANTB_WEEKNAME),");
		sql.append(" ADD_MONTHS(EDATE,1 * ANTB_WEEKNAME)) AS NEXTDUEDATE FROM GEN_TL_ANNUALTABLE," );
		sql.append(" (select 'Q' AS FREQ FROM DUAL  Union  select 'Y' AS FREQ FROM DUAL  Union  " );
		sql.append(" select 'M' AS FREQ FROM DUAL  Union  select 'H' AS FREQ FROM DUAL) ,");
		sql.append(" (  SELECT add_months('01-").append(selYear).append("',-24) AS EDATE FROM dual WHERE 1=1 ) WHERE ANTB_WEEKNAME > = 0 )");
		sql.append(" WHERE NEXTDUEDATE >=  '01-' || to_char(sysdate,'Mon-yyyy') GROUP BY FREQ ORDER BY FREQ  ");
		CommonFunctions.debugMsg("----------------------------..  :"+sql);
		return sql.toString();
	}*/
	//sriram
	public static String getyearhirerachy(String selYear) {
	    // Defensive: extract trailing 4-digit year from whatever format is passed in (e.g. "Sep-2026" -> "2026")
	    String yearOnly = selYear.replaceAll(".*?(\\d{4})\\s*$", "$1");

	    StringBuilder sql = new StringBuilder(
	        "SELECT FREQ, TO_CHAR(MIN(NEXTDUEDATE),'DD-MON-YYYY') AS NEXTDUEDATE ");
	    sql.append(" FROM ( SELECT FREQ, ");
	    sql.append("        CASE FREQ ");
	    sql.append("            WHEN 'Y' THEN EDATE + ((12 * (ANTB_WEEKNAME + 1)) * INTERVAL '1 month') ");
	    sql.append("            WHEN 'H' THEN EDATE + ((6 * ANTB_WEEKNAME) * INTERVAL '1 month') ");
	    sql.append("            WHEN 'Q' THEN EDATE + ((3 * ANTB_WEEKNAME) * INTERVAL '1 month') ");
	    sql.append("            ELSE EDATE + ((1 * ANTB_WEEKNAME) * INTERVAL '1 month') ");
	    sql.append("        END AS NEXTDUEDATE ");
	    sql.append("   FROM BAL_GEN_TL_ANNUALTABLE, ");
	    sql.append("        (SELECT 'Q' AS FREQ UNION SELECT 'Y' AS FREQ UNION SELECT 'M' AS FREQ UNION SELECT 'H' AS FREQ) f, ");
	    sql.append("        (SELECT (TO_DATE('01-01-").append(yearOnly).append("','DD-MM-YYYY') - INTERVAL '24 months') AS EDATE) e ");
	    sql.append("  WHERE ANTB_WEEKNAME >= 0 ");
	    sql.append(" ) sub ");
	    sql.append(" WHERE NEXTDUEDATE >= DATE_TRUNC('month', CURRENT_DATE) ");
	    sql.append(" GROUP BY FREQ ORDER BY FREQ ");

	    CommonFunctions.debugMsg("----------------------------..  :" + sql);
	    return sql.toString();
	}
	public static String getWORespListSql(String workrepmstkeyid) {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("workDtl..inside  :");
		String sql ="select PWRD_MASTERID ,TRDM_NAME ,EMPM_NAME from "+ TBL_BAL_PLM_TL_WORESPDTL+","+ TBL_GEN_TL_TRADEMST+","+
		TBL_GEN_TL_EMPLOYEEMST+" where PWRD_TRADEID = TRDM_KEYID AND PWRD_EMPID = EMPM_KEYID AND PWRD_MASTERID ='"+workrepmstkeyid+"'";
			
		CommonFunctions.debugMsg("workDtl..  :"+sql);
		return sql ;
		/*return "PLM_PC_PLANNEDMAINT.PLM_FN_FILLMCHFORPMWORESP";*/
	}

	public static String getplanconfigListSql(String planConfigKey) {
		// TODO Auto-generated method stub
		String sql = "select * from "+ TBL_BAL_PLM_TL_PLANCONFIGURATION +" where pplc_keyid =?";
		CommonFunctions.debugMsg("addPlanConfig..  :"+sql);
		return sql;
	}

	public static String getMachdata(String machIdpln) {
		// TODO Auto-generated method stub
      String  sql = "SELECT DISTINCT MCHM_MACHINENO ,MCHM_KEYID,MCHM_MACHINENAME   FROM " + TBL_BAL_PLM_TL_PLANCONFIGURATION + 
      "," + TBL_GEN_TL_MACHINEMST +" WHERE PPLC_MACHINEID = MCHM_KEYID " +
			" AND PPLC_LEVEL='M' AND PPLC_ASSEMBLYID ='{}' " +
			" AND PPLC_MACHINEID IN ('" + machIdpln + "') ";
      CommonFunctions.debugMsg("machineData..  :"+sql);
	return sql;

	}

}

