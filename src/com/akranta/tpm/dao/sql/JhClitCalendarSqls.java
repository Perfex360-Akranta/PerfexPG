package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class JhClitCalendarSqls {
	public static final String TBL_CLI_TL_CALENDAR = "CLI_TL_CALENDAR";  

	TableFieldType [] clcaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, assemblyid, planshiftid
		, actualshiftid, entrytype, clifrequency, clirefid, groupid, tradeid
		, monthyear, plandate, actualdate, rescheduledplan, rescheduledactual
		, allottedto, status, completedby, effplancompdate, duration
		, remarks, downtime, actualduration, starttime, endtime, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getClcaDbFields() {
		return clcaDbFields;
	}

	public JhClitCalendarSqls()
	{
		clcaDbFields = new TableFieldType[ 32 ];
		for(int i = 0;i < 32; i++)
		{	
			clcaDbFields[ i ] = new TableFieldType();
		}
		clcaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CLCA_KEYID";
		clcaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "CLCA_FACTORYID";
		clcaDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "CLCA_SECTIONID";
		clcaDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "CLCA_CELLID";
		clcaDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "CLCA_MACHINEID";
		clcaDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "CLCA_ASSEMBLYID";
		clcaDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.planshiftid.ordinal() ].fieldName = "CLCA_PLANSHIFTID";
		clcaDbFields[ tableFldConstants.planshiftid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.actualshiftid.ordinal() ].fieldName = "CLCA_ACTUALSHIFTID";
		clcaDbFields[ tableFldConstants.actualshiftid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.entrytype.ordinal() ].fieldName = "CLCA_ENTRYTYPE";
		clcaDbFields[ tableFldConstants.entrytype.ordinal() ].fieldType = 'C';

		clcaDbFields[ tableFldConstants.clifrequency.ordinal() ].fieldName = "CLCA_CLIFREQUENCY";
		clcaDbFields[ tableFldConstants.clifrequency.ordinal() ].fieldType = 'C';

		clcaDbFields[ tableFldConstants.clirefid.ordinal() ].fieldName = "CLCA_CLIREFID";
		clcaDbFields[ tableFldConstants.clirefid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.groupid.ordinal() ].fieldName = "CLCA_GROUPID";
		clcaDbFields[ tableFldConstants.groupid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "CLCA_TRADEID";
		clcaDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.monthyear.ordinal() ].fieldName = "CLCA_MONTHYEAR";
		clcaDbFields[ tableFldConstants.monthyear.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.plandate.ordinal() ].fieldName = "CLCA_PLANDATE";
		clcaDbFields[ tableFldConstants.plandate.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.actualdate.ordinal() ].fieldName = "CLCA_ACTUALDATE";
		clcaDbFields[ tableFldConstants.actualdate.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.rescheduledplan.ordinal() ].fieldName = "CLCA_RESCHEDULEDPLAN";
		clcaDbFields[ tableFldConstants.rescheduledplan.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.rescheduledactual.ordinal() ].fieldName = "CLCA_RESCHEDULEDACTUAL";
		clcaDbFields[ tableFldConstants.rescheduledactual.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.allottedto.ordinal() ].fieldName = "CLCA_ALLOTTEDTO";
		clcaDbFields[ tableFldConstants.allottedto.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.status.ordinal() ].fieldName = "CLCA_STATUS";
		clcaDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		clcaDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "CLCA_COMPLETEDBY";
		clcaDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.effplancompdate.ordinal() ].fieldName = "CLCA_EFFPLANCOMPDATE";
		clcaDbFields[ tableFldConstants.effplancompdate.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "CLCA_DURATION";
		clcaDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		clcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CLCA_REMARKS";
		clcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "CLCA_DOWNTIME";
		clcaDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		clcaDbFields[ tableFldConstants.actualduration.ordinal() ].fieldName = "CLCA_ACTUALDURATION";
		clcaDbFields[ tableFldConstants.actualduration.ordinal() ].fieldType = 'N';

		clcaDbFields[ tableFldConstants.starttime.ordinal() ].fieldName = "CLCA_STARTTIME";
		clcaDbFields[ tableFldConstants.starttime.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.endtime.ordinal() ].fieldName = "CLCA_ENDTIME";
		clcaDbFields[ tableFldConstants.endtime.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CLCA_ACTIVE";
		clcaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		clcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CLCA_CREATEDBY";
		clcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		clcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CLCA_CREATEDON";
		clcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		clcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CLCA_MODIFIEDON";
		clcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_CLI_TL_CALENDAR, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("inside update");
		String sql = SqlUtils.getUpdateSql(TBL_CLI_TL_CALENDAR, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_CLI_TL_CALENDAR ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}


	public static String getjhclitschedulearrSql() {
		// TODO Auto-generated method stub
		return "JHN_PC_CLISTANDARD.JHN_FN_GETSCHEDULEARRAY";
	}

	public static String getjhclitshiftarrSql() {
		// TODO Auto-generated method stub
		return "JHN_PC_CLISTANDARD.JHN_FN_GETSCHARRSHIFTWISE";
	}

	public static String getchkEqpmnt() {
	
			// TODO Auto-generated method stub
			return "SELECT DISTINCT  CLCA_MONTHYEAR  FROM "+  TBL_CLI_TL_CALENDAR ;
			/*" WHERE  TO_CHAR(CLCA_MONTHYEAR, 'MON-YYYY') = '"+commonFilter.getFromMonth()+"'  AND "+
			" CLCA_FACTORYID = '"+commonFilter.getFactory().getId()+"'  AND CLCA_SECTIONID = '"+commonFilter.getSection().getId()+"'  AND"+
			" CLCA_CELLID = '"+commonFilter.getCell().getId()+"'  AND CLCA_MACHINEID = '"+commonFilter.getMachine().getId()+"' "*/
		}

	/*public static List<String> updateCal(List<JhclitCalendarModel> jhclitCalendarModelList) {
		// TODO Auto-generated method stub
		
		List<String> retVal = new ArrayList<String>();
		for(JhclitCalendarModel jhcalmodel:jhclitCalendarModelList)
		{	
			CommonMessage.debugMsg("Actualduration  :"+jhcalmodel.getClcaActualduration());
			CommonMessage.debugMsg(("Plandate       :"+jhcalmodel.getClcaPlandate()));
			CommonMessage.debugMsg("Clirefid        :"+jhcalmodel.getClcaClirefid());
			CommonMessage.debugMsg("PlanDuration    :"+jhcalmodel.getClcaStatus());
			
			String sql =" update  " + TBL_CLI_TL_CALENDAR + " set ";
			sql +=" CLCA_ACTUALDURATION = '" + jhcalmodel.getClcaActualduration() + "', " ;
			sql +=" CLCA_STATUS = '"+ jhcalmodel.getClcaStatus()+"',CLCA_CREATEDBY =  '"+ jhcalmodel.getClcaCreatedby() + "', " ;
			sql +=" CLCA_MODIFIEDON = to_date('"+jhcalmodel.getClcaModifiedon()  +"', 'dd-mon-yyyy hh24:mi:ss')  WHERE  trunc(CLCA_PLANDATE )= '" + jhcalmodel.getClcaPlandate()+"' " ;
			sql +=" AND CLCA_CLIREFID = '" + jhcalmodel.getClcaClirefid() + "' ";
			CommonMessage.debugMsg(" sql  " + sql);
			retVal.add(sql);
			
			
		
		}
		return retVal;
	}
	*/
	public static String getUpdateActualCLITCalendarSql() {
		// TODO Auto-generated method stub
		
		String sql =" update  " + TBL_CLI_TL_CALENDAR + " set  CLCA_ACTUALDURATION = ?, CLCA_STATUS = ?,CLCA_CREATEDBY = ?, CLCA_MODIFIEDON = to_date(?, 'dd-mon-yyyy hh24:mi:ss') " +
					" WHERE  trunc(CLCA_PLANDATE )= ? AND CLCA_CLIREFID = ? ";
		
		return sql;
	}

	public static String getConfiglinkBtns() {
		// TODO Auto-generated method stub
		String sql = "SELECT CNFM_SETTINGVALUE  FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST +" WHERE CNFM_CODE LIKE 'JHCAL%'";
		return sql;
	}
}
