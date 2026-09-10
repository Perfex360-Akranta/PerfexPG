package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PlmTlGenmaintenanceSql {

	public static final String TBL_PLM_TL_GENMAINTENANCE = "PLM_TL_GENMAINTENANCE";  

	TableFieldType [] gmntDbFields = null;

	public enum   tableFldConstants
	{keyid, occureddate, shiftdate, bookeddate, receiveddate, allocateddate
		, wostartdate, woenddate, responsetime, workhours, downtime, refdoctype
		, refdocid, factoryid, sectionid, lineid, machineid, stationid
		, phenid, rootcauseid, shift, trade, partlocation, activitytype
		, mchcondition, manpowercost, contractorcost, othercost, sparecost
		, problem, rootcause, countermeasure, action, reportedby, targetdate
		, status, isyy, yyno, completedby, completeddate, remarks, pctrmeasure
		, relatedto, mouldid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, tempfield8, tempfield9
		, tempfield10, elementid,flid,active, createdby, createdon, modifiedon, ordertype,errppostStatus,errppostNo,  isSpares
	}

	public TableFieldType[] getGmntDbFields() {
		return gmntDbFields;
	}

	public BAL_PlmTlGenmaintenanceSql()
	{
		gmntDbFields = new TableFieldType[ 64 ];
		for(int i = 0;i < 64; i++)
		{	
			gmntDbFields[ i ] = new TableFieldType();
		}
		gmntDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "GMNT_KEYID";
		gmntDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.occureddate.ordinal() ].fieldName = "GMNT_OCCUREDDATE";
		gmntDbFields[ tableFldConstants.occureddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldName = "GMNT_SHIFTDATE";
		gmntDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.bookeddate.ordinal() ].fieldName = "GMNT_BOOKEDDATE";
		gmntDbFields[ tableFldConstants.bookeddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldName = "GMNT_RECEIVEDDATE";
		gmntDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.allocateddate.ordinal() ].fieldName = "GMNT_ALLOCATEDDATE";
		gmntDbFields[ tableFldConstants.allocateddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.wostartdate.ordinal() ].fieldName = "GMNT_WOSTARTDATE";
		gmntDbFields[ tableFldConstants.wostartdate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.woenddate.ordinal() ].fieldName = "GMNT_WOENDDATE";
		gmntDbFields[ tableFldConstants.woenddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.responsetime.ordinal() ].fieldName = "GMNT_RESPONSETIME";
		gmntDbFields[ tableFldConstants.responsetime.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.workhours.ordinal() ].fieldName = "GMNT_WORKHOURS";
		gmntDbFields[ tableFldConstants.workhours.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "GMNT_DOWNTIME";
		gmntDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "GMNT_REFDOCTYPE";
		gmntDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "GMNT_REFDOCID";
		gmntDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "GMNT_FACTORYID";
		gmntDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "GMNT_SECTIONID";
		gmntDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.lineid.ordinal() ].fieldName = "GMNT_LINEID";
		gmntDbFields[ tableFldConstants.lineid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "GMNT_MACHINEID";
		gmntDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.stationid.ordinal() ].fieldName = "GMNT_STATIONID";
		gmntDbFields[ tableFldConstants.stationid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.phenid.ordinal() ].fieldName = "GMNT_PHENID";
		gmntDbFields[ tableFldConstants.phenid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "GMNT_ROOTCAUSEID";
		gmntDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.shift.ordinal() ].fieldName = "GMNT_SHIFT";
		gmntDbFields[ tableFldConstants.shift.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.trade.ordinal() ].fieldName = "GMNT_TRADE";
		gmntDbFields[ tableFldConstants.trade.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.partlocation.ordinal() ].fieldName = "GMNT_PARTLOCATION";
		gmntDbFields[ tableFldConstants.partlocation.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "GMNT_ACTIVITYTYPE";
		gmntDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldName = "GMNT_MCHCONDITION";
		gmntDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "GMNT_MANPOWERCOST";
		gmntDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "GMNT_CONTRACTORCOST";
		gmntDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "GMNT_OTHERCOST";
		gmntDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		gmntDbFields[ tableFldConstants.sparecost.ordinal() ].fieldName = "GMNT_SPARECOST";
		gmntDbFields[ tableFldConstants.sparecost.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "GMNT_PROBLEM";
		gmntDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "GMNT_ROOTCAUSE";
		gmntDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "GMNT_COUNTERMEASURE";
		gmntDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.action.ordinal() ].fieldName = "GMNT_ACTION";
		gmntDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.reportedby.ordinal() ].fieldName = "GMNT_REPORTEDBY";
		gmntDbFields[ tableFldConstants.reportedby.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "GMNT_TARGETDATE";
		gmntDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.status.ordinal() ].fieldName = "GMNT_STATUS";
		gmntDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.isyy.ordinal() ].fieldName = "GMNT_ISYY";
		gmntDbFields[ tableFldConstants.isyy.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.yyno.ordinal() ].fieldName = "GMNT_YYNO";
		gmntDbFields[ tableFldConstants.yyno.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "GMNT_COMPLETEDBY";
		gmntDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "GMNT_COMPLETEDDATE";
		gmntDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "GMNT_REMARKS";
		gmntDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.pctrmeasure.ordinal() ].fieldName = "GMNT_PCTRMEASURE";
		gmntDbFields[ tableFldConstants.pctrmeasure.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "GMNT_RELATEDTO";
		gmntDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "GMNT_MOULDID";
		gmntDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.ordertype.ordinal() ].fieldName = "GMNT_ORDERTYPE";
		gmntDbFields[ tableFldConstants.ordertype.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.errppostNo.ordinal() ].fieldName = "GMNT_ERPNUMBER";
		gmntDbFields[ tableFldConstants.errppostNo.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.errppostStatus.ordinal() ].fieldName = "GMNT_ERPPOSTSTATUS";
		gmntDbFields[ tableFldConstants.errppostStatus.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.isSpares.ordinal() ].fieldName = "GMNT_SPARESREPLACED"; 
		gmntDbFields[ tableFldConstants.isSpares.ordinal() ].fieldType = 'V';
		
		gmntDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "GMNT_TEMPFIELD1";
		gmntDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';
		
		gmntDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "GMNT_TEMPFIELD2";
		gmntDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';
		
		gmntDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "GMNT_TEMPFIELD3";
		gmntDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		gmntDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "GMNT_TEMPFIELD4";
		gmntDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "GMNT_TEMPFIELD5";
		gmntDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "GMNT_TEMPFIELD6";
		gmntDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "GMNT_TEMPFIELD7";
		gmntDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "GMNT_TEMPFIELD8";
		gmntDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "GMNT_TEMPFIELD9";
		gmntDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "GMNT_TEMPFIELD10";
		gmntDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';
		
		gmntDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "GMNT_ELEMENTID";
		gmntDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		gmntDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "GMNT_FLID";
		gmntDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.active.ordinal() ].fieldName = "GMNT_ACTIVE";
		gmntDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		gmntDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "GMNT_CREATEDBY";
		gmntDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		gmntDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "GMNT_CREATEDON";
		gmntDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		gmntDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "GMNT_MODIFIEDON";
		gmntDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_GENMAINTENANCE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_GENMAINTENANCE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_GENMAINTENANCE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getGenMainListSql() {
		// TODO Auto-generated method stubTST_PC_GenMain.TST_FN_GenMain
		//return "PLM_PC_PLANNEDMAINT.PLM_FN_GeneralMaintModify";
		return "PLM_FN_GENERALMAINTMODIFY";
	}

	public static String getGenMainSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_PLM_TL_GENMAINTENANCE + " where GMNT_KEYID = ?  ";
	}
	
	public static String inactiveGenMainSql(String keyId) {
		// TODO Auto-generated method stub
		return " UPDATE " + TBL_PLM_TL_GENMAINTENANCE + " SET GMNT_ACTIVE = 'N' where GMNT_KEYID = '"+keyId+"'";
	}

	public static String getShiftFunction() {
		// TODO Auto-generated method stub
		return "BDM_PC_BREAKDOWN.BDM_FN_GetshiftForTime";		
	}
	
	public static String checkExist() {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("SELECT COUNT(*) FROM "+TableNames.TBL_PCS_TL_LOGCONFIGURATION + " WHERE PLCM_PARAMETERCODE = 'SETUPANDADJ'");
		return "SELECT COUNT(*) FROM "+TableNames.TBL_PCS_TL_LOGCONFIGURATION + " WHERE PLCM_PARAMETERCODE = 'SETUPANDADJ'";		
	}
	public static String getSubLoss() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT PLCM_KEYID,PLCM_MAPFIELD,PLCM_PARAMETERNAME,' ',' ',' '");
		sb.append(" From "+TableNames.TBL_PCS_TL_LOGCONFIGURATION +" Where Plcm_Parentid In");
		sb.append("(Select Plcm_Parentid From "+TableNames.TBL_PCS_TL_LOGCONFIGURATION + " WHERE PLCM_PARAMETERCODE = 'SETUPANDADJ')");
		sb.append(" AND PLCM_PARAMETERCODE <> 'SETUPANDADJ'");
		CommonFunctions.debugMsg("SQL : "+sb.toString());
		return sb.toString();
		
	}
	
	public static String getSubLossGrid(String refDocId) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT Sups_Lossid,SUPS_KEYID,PLCM_PARAMETERNAME,To_Char(Sups_Splitdate,'DD-MON-YYYY'),Sftm_Code,Sups_Splitshift,Sups_Duration");
		sb.append(" From "+TableNames.TBL_BDM_TL_SETUPADJSPLIT+","+TableNames.TBL_PCS_TL_LOGCONFIGURATION +","+TableNames.TBL_GEN_TL_SHIFTMST); 
		sb.append(" Where SUPS_LOSSID = PLCM_KEYID AND Sups_Splitshift = SFTM_KEYID");		
		sb.append(" AND SUPS_REFDOCID = '"+refDocId+"'");
		CommonFunctions.debugMsg("SQL : "+sb.toString());
		return sb.toString();
		
	}
	 
//	public static String checkSetupAndAdjQuery() {
//		String sql = "Select BITAND(CNFM_SETTINGVALUE,16) FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST;
//		sql += " Where Cnfm_Code='MSRACTIVITY'";		
//		return sql;
//	}
	public static String checkSetupAndAdjQuery() {
	    String sql = "Select (CNFM_SETTINGVALUE::integer & 16)::text FROM "
	                 +TableNames.TBL_ADM_TL_CONFIGURATIONMST;
	    sql += " Where Cnfm_Code='MSRACTIVITY'";		
	    return sql;
	}
	


}

