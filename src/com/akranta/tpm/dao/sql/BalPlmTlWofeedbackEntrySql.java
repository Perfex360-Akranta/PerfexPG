package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BalPlmTlWofeedbackEntrySql {

	public static final String TBL_PLM_TL_WOFEEDBACK_ENTRY = "BAL_PLM_TL_WOFEEDBACK_ENTRY";  

	TableFieldType [] wofbDbFields = null;

	public enum   tableFldConstants
	{
		feedbackid, wodetailid, feedbackdate, machineid, status, action
		, startdate, enddate, completeddate, duration, isprodstopped
		, prodstartdate, currentreading, adjustedreading, uom, whywhyflag
		, whywhyid, amcflag, amcdetailid, spareflag, sparecost, manpowercost
		, contractorcost, othercost, observation, feedback, completedby
		, rescheduleflag, reschedulereason, nextinspectiondate, remarks
		, rootcause, countermeasure, mchcondition,machinetakeovertime, createdby, modifiedon
		, createdon
	}

	public TableFieldType[] getWofbDbFields() {
		return wofbDbFields;
	}

	public BalPlmTlWofeedbackEntrySql()
	{
		wofbDbFields = new TableFieldType[ 38 ];
		for(int i = 0;i < 38; i++)
		{	
			wofbDbFields[ i ] = new TableFieldType();
		}
		///
				
		wofbDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldName = "WOFBE_FEEDBACKID";
		wofbDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldName = "WOFBE_WODETAILID";
		wofbDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldName = "WOFBE_FEEDBACKDATE";
		wofbDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "WOFBE_MACHINEID";
		wofbDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WOFBE_STATUS";
		wofbDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.action.ordinal() ].fieldName = "WOFBE_ACTION";
		wofbDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "WOFBE_STARTDATE";
		wofbDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "WOFBE_ENDDATE";
		wofbDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "WOFBE_COMPLETEDDATE";
		wofbDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "WOFBE_DURATION";
		wofbDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.isprodstopped.ordinal() ].fieldName = "WOFBE_ISPRODSTOPPED";
		wofbDbFields[ tableFldConstants.isprodstopped.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.prodstartdate.ordinal() ].fieldName = "WOFBE_PRODSTARTDATE";
		wofbDbFields[ tableFldConstants.prodstartdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.currentreading.ordinal() ].fieldName = "WOFBE_CURRENTREADING";
		wofbDbFields[ tableFldConstants.currentreading.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldName = "WOFBE_ADJUSTEDREADING";
		wofbDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "WOFBE_UOM";
		wofbDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldName = "WOFBE_WHYWHYFLAG";
		wofbDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.whywhyid.ordinal() ].fieldName = "WOFBE_WHYWHYID";
		wofbDbFields[ tableFldConstants.whywhyid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.amcflag.ordinal() ].fieldName = "WOFBE_AMCFLAG";
		wofbDbFields[ tableFldConstants.amcflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.amcdetailid.ordinal() ].fieldName = "WOFBE_AMCDETAILID";
		wofbDbFields[ tableFldConstants.amcdetailid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.spareflag.ordinal() ].fieldName = "WOFBE_SPAREFLAG";
		wofbDbFields[ tableFldConstants.spareflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.sparecost.ordinal() ].fieldName = "WOFBE_SPARECOST";
		wofbDbFields[ tableFldConstants.sparecost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "WOFBE_MANPOWERCOST";
		wofbDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "WOFBE_CONTRACTORCOST";
		wofbDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "WOFBE_OTHERCOST";
		wofbDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.observation.ordinal() ].fieldName = "WOFBE_OBSERVATION";
		wofbDbFields[ tableFldConstants.observation.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.feedback.ordinal() ].fieldName = "WOFBE_FEEDBACK";
		wofbDbFields[ tableFldConstants.feedback.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "WOFBE_COMPLETEDBY";
		wofbDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldName = "WOFBE_RESCHEDULEFLAG";
		wofbDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.reschedulereason.ordinal() ].fieldName = "WOFBE_RESCHEDULEREASON";
		wofbDbFields[ tableFldConstants.reschedulereason.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.nextinspectiondate.ordinal() ].fieldName = "WOFBE_NEXTINSPECTIONDATE";
		wofbDbFields[ tableFldConstants.nextinspectiondate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WOFBE_REMARKS";
		wofbDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "WOFBE_ROOTCAUSE";
		wofbDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "WOFBE_COUNTERMEASURE";
		wofbDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldName = "WOFBE_MCHCONDITION";
		wofbDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldType = 'C';


		wofbDbFields[ tableFldConstants.machinetakeovertime.ordinal() ].fieldName = "WOFBE_MACHINETAKEOVERTIME";
		wofbDbFields[ tableFldConstants.machinetakeovertime.ordinal() ].fieldType = 'D';
		
		wofbDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WOFBE_CREATEDBY";
		wofbDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WOFBE_MODIFIEDON";
		wofbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WOFBE_CREATEDON";
		wofbDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_WOFEEDBACK_ENTRY, fieldTypeArr, dataArray);
	}
	
/*
	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_WOFEEDBACK_ENTRY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.feedbackid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.feedbackid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_WOFEEDBACK_ENTRY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.feedbackid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.feedbackid.ordinal()] + "'";
		return sql;
	}

	public String updatePmCalendar() {
		// TODO Auto-generated method stub
		String sqlCalendar = "UPDATE PLM_TL_CALENDAR SET PMCL_FEEDBACKID = ?,"+
		"PMCL_STATUS = 'Y' ,PMCL_FEEDBACKDATE =  ?,"+
		"PMCL_COMPLETEDBY = ?,  PMCL_WORKSUMMARYID = ?,"+
		"PMCL_EFFPLANCOMPDATE = ? , PMCL_DOWNTIME =?  WHERE  PMCL_KEYID = ?";
		CommonFunctions.debugMsg("sqlCalendar   "+sqlCalendar);
		return sqlCalendar;
	}

	public String getUpdateStd() {
		// TODO Auto-generated method stub
		String sqlStd = " UPDATE PLM_TL_STANDARDS SET PMSD_LASTDONEDATE = ?,  PMSD_LASTDONEWEEKNO = 4 ,PMSD_NEXTDUEDATE = ? ,PMSD_NEXTDUEWEEKNO = 1 , WHERE PMSD_KEYID = ?";
		return sqlStd;
	}

	public String getUpdatPlanConfig() {
		// TODO Auto-generated method stub
		String planConfigSql ="UPDATE PLM_TL_PLANCONFIGURATION SET PPLC_MODIFIEDON = ?,"+
		"PPLC_MONTHLY = ? WHERE  ( PPLC_MACHINEID || PPLC_ASSEMBLYID = ? || "+
		"? or PPLC_MACHINEID || PPLC_ASSEMBLYID =? || '{}'   ) ";
		return planConfigSql;
	}

	public String getwoDetail() {
		// TODO Auto-generated method stub
		String wodetailsql = "UPDATE PLM_TL_WODTL SET PWDD_FEEDBACKID = ?,"+
		"PWDD_COMPLETEDBY = ?,"+
		"PWDD_COMPLETEDDATE = ?,"+
		"PWDD_STATUS = 'C'  WHERE PWDD_WODETAILID = ?";
		return wodetailsql;
	}

	public String getupdtWomst() {
		// TODO Auto-generated method stub
		String womstsql = "UPDATE PLM_TL_WOMST SET PWDM_MODIFIEDON= ? , PWDM_WOSTATUS=  (  SELECT MAX(PWDD_STATUS)  FROM PLM_TL_WODTL"+
		" WHERE PWDD_WOMASTERID = PWDM_WORKORDERNO  )  WHERE PWDM_WORKORDERNO =?";
		CommonFunctions.debugMsg("womstsql  "+womstsql);
		return womstsql ;
	}

	public String updateWofeed() {
		// TODO Auto-generated method stub
		String sqlWofeed =  "UPDATE PLM_TL_WOFEEDBACK SET  WOFBE_MODIFIEDON  = ? WHERE   WOFBE_WODETAILID = ?";
		return sqlWofeed;
	}

	public String getwosum() {
		// TODO Auto-generated method stub
		String sumSql = " UPDATE PLM_TL_WORKSUMMARY SET WKSM_MODIFIEDON = ? WHERE   WKSM_WODETAILID = ?";
		return sumSql;
	}

	public String getUpdtRelease() {
		// TODO Auto-generated method stub
		String sqlRelease = "UPDATE PLM_TL_WORKRELEASE SET  PMWR_COMPLETEDBY =? , PMWR_COMPLETEDFLAG ='Y' ,  PMWR_COMPLETEDDATE=? ,PMWR_STATUS ='W'  WHERE PMWR_PMWORKORDERNO =?";
		return sqlRelease;
	}

	public String delfeedBck() {
		// TODO Auto-generated method stub
		String delSql =  "DELETE FROM "+TableNames.TBL_PLM_TL_WOFEEDBACK +" WHERE WOFBE_WODETAILID = ?"; 
		return delSql ;
	}

	public String delwosumry() {
		// TODO Auto-generated method stub
		String delSumSql = "DELETE FROM "+TableNames.TBL_PLM_TL_WORKSUMMARY +" WHERE WKSM_WODETAILID = ?"; 
		return delSumSql ;
	}
*/
}


