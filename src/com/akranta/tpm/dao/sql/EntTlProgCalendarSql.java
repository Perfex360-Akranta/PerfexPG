package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlProgCalendarSql {
	
	TableFieldType [] ecalDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prog_id, plan_fromdate, plan_tilldate, plan_month, plan_week
		, plan_audiencecount, requestby, requestdate, requestremarks
		, approvedflag, approvedby, approveddate, approvedremarks, actual_fromdate
		, actual_tilldate, actual_audiencecount, status, monthwise,budget
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getEcalDbFields() {
		return ecalDbFields;
	}

	public EntTlProgCalendarSql()
	{
		ecalDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i < 28; i++)
		{	
			ecalDbFields[ i ] = new TableFieldType();
		}
		ecalDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ECAL_KEYID";
		ecalDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.prog_id.ordinal() ].fieldName = "ECAL_PROG_ID";
		ecalDbFields[ tableFldConstants.prog_id.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.plan_fromdate.ordinal() ].fieldName = "ECAL_PLAN_FROMDATE";
		ecalDbFields[ tableFldConstants.plan_fromdate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.plan_tilldate.ordinal() ].fieldName = "ECAL_PLAN_TILLDATE";
		ecalDbFields[ tableFldConstants.plan_tilldate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.plan_month.ordinal() ].fieldName = "ECAL_PLAN_MONTH";
		ecalDbFields[ tableFldConstants.plan_month.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.plan_week.ordinal() ].fieldName = "ECAL_PLAN_WEEK";
		ecalDbFields[ tableFldConstants.plan_week.ordinal() ].fieldType = 'N';

		ecalDbFields[ tableFldConstants.plan_audiencecount.ordinal() ].fieldName = "ECAL_PLAN_AUDIENCECOUNT";
		ecalDbFields[ tableFldConstants.plan_audiencecount.ordinal() ].fieldType = 'N';

		ecalDbFields[ tableFldConstants.requestby.ordinal() ].fieldName = "ECAL_REQUESTBY";
		ecalDbFields[ tableFldConstants.requestby.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.requestdate.ordinal() ].fieldName = "ECAL_REQUESTDATE";
		ecalDbFields[ tableFldConstants.requestdate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.requestremarks.ordinal() ].fieldName = "ECAL_REQUESTREMARKS";
		ecalDbFields[ tableFldConstants.requestremarks.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.approvedflag.ordinal() ].fieldName = "ECAL_APPROVEDFLAG";
		ecalDbFields[ tableFldConstants.approvedflag.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "ECAL_APPROVEDBY";
		ecalDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "ECAL_APPROVEDDATE";
		ecalDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.approvedremarks.ordinal() ].fieldName = "ECAL_APPROVEDREMARKS";
		ecalDbFields[ tableFldConstants.approvedremarks.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.actual_fromdate.ordinal() ].fieldName = "ECAL_ACTUAL_FROMDATE";
		ecalDbFields[ tableFldConstants.actual_fromdate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.actual_tilldate.ordinal() ].fieldName = "ECAL_ACTUAL_TILLDATE";
		ecalDbFields[ tableFldConstants.actual_tilldate.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.actual_audiencecount.ordinal() ].fieldName = "ECAL_ACTUAL_AUDIENCECOUNT";
		ecalDbFields[ tableFldConstants.actual_audiencecount.ordinal() ].fieldType = 'N';

		ecalDbFields[ tableFldConstants.status.ordinal() ].fieldName = "ECAL_STATUS";
		ecalDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.monthwise.ordinal() ].fieldName = "ECAL_MONTHWISE";
		ecalDbFields[ tableFldConstants.monthwise.ordinal() ].fieldType = 'C';		
		
		ecalDbFields[ tableFldConstants.budget.ordinal() ].fieldName = "ECAL_BUDGET";
		ecalDbFields[ tableFldConstants.budget.ordinal() ].fieldType = 'N';

		ecalDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ECAL_TEMPFIELD2";
		ecalDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ECAL_TEMPFIELD3";
		ecalDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ECAL_TEMPFIELD4";
		ecalDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ECAL_TEMPFIELD5";
		ecalDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ECAL_ACTIVE";
		ecalDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ecalDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ECAL_CREATEDBY";
		ecalDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ecalDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ECAL_CREATEDON";
		ecalDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ecalDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ECAL_MODIFIEDON";
		ecalDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_PROG_CALENDAR, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_PROG_CALENDAR, fieldTypeArr, dataArray);
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_PROG_CALENDAR ;
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getRefersSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "select * from " + TableNames.TBL_ENT_TL_BATCH_SCHEDULE;
		sql += " where BSDL_ECAL_KEYID" +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "SELECT * from " + TableNames.TBL_ENT_TL_PROG_CALENDAR ;		
		sql += " where 1=1 " ;
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()]))
				sql += " and " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.prog_id.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.prog_id.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.prog_id.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.plan_month.ordinal()]))
			sql += " and " + fieldTypeArr[tableFldConstants.plan_month.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.plan_month.ordinal()] + "'";
		return sql;
	}
	
	public static String getSelectStartMonthSql()
	{
		String sql = " SELECT CNFM_SETTINGVALUE FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST ;		
		sql += " WHERE CNFM_CODE='ENTPROGSTARTMONTH' " ;
		return sql;
	}
	
	public static String getSelectMonthSql(String month,int noOfMonths,String ProgId)
	{
		String sql=null;
		if (CommonFunctions.isValidKeyId(ProgId)){
			sql =" SELECT * FROM(SELECT  month, 'N' AS tick, ecal_plan_audiencecount AS noofemployee, ecal_budget AS budget, ecal_keyid, ecal_prog_id FROM ( ";
			
			sql = sql + " SELECT to_char( date_trunc('month', DATE '01-" + month + "' + (gs.n || ' month')::interval),'MON-YYYY') AS monthyear, " ;


			sql = sql + " to_char( date_trunc('month', DATE '01-" + month + "' + (gs.n || ' month')::interval), 'Mon-YYYY' ) AS month " ;
			
			sql = sql + " FROM generate_series(0, 11) gs(n) ) a LEFT JOIN ent_tl_prog_calendar  ON a.monthyear = ent_tl_prog_calendar.ecal_plan_month ";
			sql = sql + "  AND ent_tl_prog_calendar.ecal_prog_id = '" + ProgId + "' ORDER BY  to_date(month, 'Mon-YYYY') )";


			/*
			 * sql = sql + " (SELECT TO_CHAR(TO_DATE('01-' || UPPER(TO_CHAR(ADD_MONTHS('01-"
			 * + month +
			 * "',ROWNUM-1),'MON-YYYY')),'DD-MON-YYYY'),'MON-YYYY') as monthyear,TO_CHAR(TO_DATE('01-' || UPPER(TO_CHAR(ADD_MONTHS('01-"
			 * + month + "',ROWNUM-1),'MON-YYYY')),'DD-MON-YYYY'),'Mon-YYYY') as month ";
			 * sql = sql + "  FROM TAB WHERE ROWNUM < ="+ noOfMonths; sql = sql +
			 * " )) A left join ENT_TL_PROG_CALENDAR on monthyear=ECAL_PLAN_MONTH " ; sql =
			 * sql + " and ECAL_PROG_ID ='" + ProgId + "'" ; sql = sql + ") "; sql = sql +
			 * " ORDER BY TO_DATE(month,'Mon-YYYY') ";
			 */
		}
		else{
			
			sql = " SELECT  TO_CHAR( DATE_TRUNC( 'month', DATE '01-" + month + "' + (gs * INTERVAL '1 month') ), 'Mon-YYYY' ) AS month, ";
			sql = sql + " 'N' AS tick, 0 AS noofemployee,   0 AS budget,  '' AS ecal_keyid FROM generate_series(0, 11) AS gs ";

//			sql = " SELECT to_char( date_trunc('month', DATE '01-" + month + "' + (gs.n || ' month')::interval), 'Mon-YYYY' ) AS month,'N' as tick,'0' as noOfEmployee,'0' as budget,'' as ECAL_KEYID ";
//			sql = sql + " FROM TAB WHERE ROWNUM < =" + noOfMonths;
		}		
		return sql;
	}
	
}

