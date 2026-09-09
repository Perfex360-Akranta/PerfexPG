package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PlmTlWofeedbackSql {

	public static final String TBL_PLM_TL_WOFEEDBACK = "PLM_TL_WOFEEDBACK";  

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

	public PlmTlWofeedbackSql()
	{
		wofbDbFields = new TableFieldType[ 38 ];
		for(int i = 0;i < 38; i++)
		{	
			wofbDbFields[ i ] = new TableFieldType();
		}
		wofbDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldName = "WOFB_FEEDBACKID";
		wofbDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldName = "WOFB_WODETAILID";
		wofbDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldName = "WOFB_FEEDBACKDATE";
		wofbDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "WOFB_MACHINEID";
		wofbDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WOFB_STATUS";
		wofbDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.action.ordinal() ].fieldName = "WOFB_ACTION";
		wofbDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "WOFB_STARTDATE";
		wofbDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "WOFB_ENDDATE";
		wofbDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "WOFB_COMPLETEDDATE";
		wofbDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "WOFB_DURATION";
		wofbDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.isprodstopped.ordinal() ].fieldName = "WOFB_ISPRODSTOPPED";
		wofbDbFields[ tableFldConstants.isprodstopped.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.prodstartdate.ordinal() ].fieldName = "WOFB_PRODSTARTDATE";
		wofbDbFields[ tableFldConstants.prodstartdate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.currentreading.ordinal() ].fieldName = "WOFB_CURRENTREADING";
		wofbDbFields[ tableFldConstants.currentreading.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldName = "WOFB_ADJUSTEDREADING";
		wofbDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "WOFB_UOM";
		wofbDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldName = "WOFB_WHYWHYFLAG";
		wofbDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.whywhyid.ordinal() ].fieldName = "WOFB_WHYWHYID";
		wofbDbFields[ tableFldConstants.whywhyid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.amcflag.ordinal() ].fieldName = "WOFB_AMCFLAG";
		wofbDbFields[ tableFldConstants.amcflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.amcdetailid.ordinal() ].fieldName = "WOFB_AMCDETAILID";
		wofbDbFields[ tableFldConstants.amcdetailid.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.spareflag.ordinal() ].fieldName = "WOFB_SPAREFLAG";
		wofbDbFields[ tableFldConstants.spareflag.ordinal() ].fieldType = 'C';

		wofbDbFields[ tableFldConstants.sparecost.ordinal() ].fieldName = "WOFB_SPARECOST";
		wofbDbFields[ tableFldConstants.sparecost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "WOFB_MANPOWERCOST";
		wofbDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "WOFB_CONTRACTORCOST";
		wofbDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "WOFB_OTHERCOST";
		wofbDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		wofbDbFields[ tableFldConstants.observation.ordinal() ].fieldName = "WOFB_OBSERVATION";
		wofbDbFields[ tableFldConstants.observation.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.feedback.ordinal() ].fieldName = "WOFB_FEEDBACK";
		wofbDbFields[ tableFldConstants.feedback.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "WOFB_COMPLETEDBY";
		wofbDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldName = "WOFB_RESCHEDULEFLAG";
		wofbDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.reschedulereason.ordinal() ].fieldName = "WOFB_RESCHEDULEREASON";
		wofbDbFields[ tableFldConstants.reschedulereason.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.nextinspectiondate.ordinal() ].fieldName = "WOFB_NEXTINSPECTIONDATE";
		wofbDbFields[ tableFldConstants.nextinspectiondate.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WOFB_REMARKS";
		wofbDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "WOFB_ROOTCAUSE";
		wofbDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "WOFB_COUNTERMEASURE";
		wofbDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldName = "WOFB_MCHCONDITION";
		wofbDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldType = 'C';


		wofbDbFields[ tableFldConstants.machinetakeovertime.ordinal() ].fieldName = "WOFB_MACHINETAKEOVERTIME";
		wofbDbFields[ tableFldConstants.machinetakeovertime.ordinal() ].fieldType = 'D';
		
		wofbDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WOFB_CREATEDBY";
		wofbDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wofbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WOFB_MODIFIEDON";
		wofbDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		wofbDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WOFB_CREATEDON";
		wofbDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_WOFEEDBACK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_WOFEEDBACK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.feedbackid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.feedbackid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_WOFEEDBACK ;
		
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
		CommonMessage.debugMsg("sqlCalendar   "+sqlCalendar);
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
		CommonMessage.debugMsg("womstsql  "+womstsql);
		return womstsql ;
	}

	public String updateWofeed() {
		// TODO Auto-generated method stub
		String sqlWofeed =  "UPDATE PLM_TL_WOFEEDBACK SET  WOFB_MODIFIEDON  = ? WHERE   WOFB_WODETAILID = ?";
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
		String delSql =  "DELETE FROM "+TableNames.TBL_PLM_TL_WOFEEDBACK +" WHERE WOFB_WODETAILID = ?"; 
		return delSql ;
	}

	public String delwosumry() {
		// TODO Auto-generated method stub
		String delSumSql = "DELETE FROM "+TableNames.TBL_PLM_TL_WORKSUMMARY +" WHERE WKSM_WODETAILID = ?"; 
		return delSumSql ;
	}

}

