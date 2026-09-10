/*Author MANIKANDAN*/
package com.akranta.tpm.dao.sql;

import java.util.stream.Collectors;

import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.utils.CommonFunctions;


public class BAL_CliTlStandardsSql {

	public static final String TBL_BAL_CLI_TL_STANDARDS = "BAL_CLI_TL_STANDARDS";  
	public static final String TBL_BAL_CLI_TL_CALENDAR = "BAL_cli_tl_calendar";
	private static final String TBL_GEN_TL_MACHINEAREAMST = null;
	private static final String TBL_GEN_TL_MCHMACHINEAREALINK = null;
	private static final String TBL_PLM_TL_TOOLSDTL = "PLM_TL_TOOLSDTL";
	private static final String TBL_GEN_TL_TOOLSMST = "GEN_TL_TOOLSMST";
	private static final String TBL_GEN_TL_DOCUPDATES = "GEN_TL_DOCUPDATES";

	TableFieldType [] clisDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, assemblyid
		, phenomenaid, causeid, jhid, tradeid, shiftid, time, effectivedate
		, formatno, issueno, issuedate, refdoctype, refdocno, departmentmgr
		, sectionmgr, groupleader, groupno, activitytype, howmuchduration
		, correctiveaction, whatactivity, wherelocation, standard, whyifnotdone
		, frequencyunit, frequency, responsibilityid, responsibilitydesgid
		, istoolsreq, howmethod, startdate, startweekno, lastdonedate
		, lastweekno, nextduedate, nextdueweekno, monthweekno, preparedbyid
		, wogenflag, lastwoid, lastwogendate, lastfeedbackid, lastfeedbackdate
		, inactivateddate,elementid,flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getClisDbFields() {
		return clisDbFields;
	}

	public BAL_CliTlStandardsSql()
	{
		clisDbFields = new TableFieldType[ 56 ];
		for(int i = 0;i < 56; i++)
		{	
			clisDbFields[ i ] = new TableFieldType();
		}
		clisDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CLIS_KEYID";
		clisDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.date.ordinal() ].fieldName = "CLIS_DATE";
		clisDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "CLIS_FACTORYID";
		clisDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "CLIS_SECTIONID";
		clisDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "CLIS_CELLID";
		clisDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "CLIS_MACHINEID";
		clisDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "CLIS_ASSEMBLYID";
		clisDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "CLIS_PHENOMENAID";
		clisDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "CLIS_CAUSEID";
		clisDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.jhid.ordinal() ].fieldName = "CLIS_JHID";
		clisDbFields[ tableFldConstants.jhid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "CLIS_TRADEID";
		clisDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "CLIS_SHIFTID";
		clisDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.time.ordinal() ].fieldName = "CLIS_TIME";
		clisDbFields[ tableFldConstants.time.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "CLIS_EFFECTIVEDATE";
		clisDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.formatno.ordinal() ].fieldName = "CLIS_FORMATNO";
		clisDbFields[ tableFldConstants.formatno.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.issueno.ordinal() ].fieldName = "CLIS_ISSUENO";
		clisDbFields[ tableFldConstants.issueno.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.issuedate.ordinal() ].fieldName = "CLIS_ISSUEDATE";
		clisDbFields[ tableFldConstants.issuedate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "CLIS_REFDOCTYPE";
		clisDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "CLIS_REFDOCNO";
		clisDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.departmentmgr.ordinal() ].fieldName = "CLIS_DEPARTMENTMGR";
		clisDbFields[ tableFldConstants.departmentmgr.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.sectionmgr.ordinal() ].fieldName = "CLIS_SECTIONMGR";
		clisDbFields[ tableFldConstants.sectionmgr.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.groupleader.ordinal() ].fieldName = "CLIS_GROUPLEADER";
		clisDbFields[ tableFldConstants.groupleader.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.groupno.ordinal() ].fieldName = "CLIS_GROUPNO";
		clisDbFields[ tableFldConstants.groupno.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "CLIS_ACTIVITYTYPE";
		clisDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.howmuchduration.ordinal() ].fieldName = "CLIS_HOWMUCHDURATION";
		clisDbFields[ tableFldConstants.howmuchduration.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "CLIS_CORRECTIVEACTION";
		clisDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.whatactivity.ordinal() ].fieldName = "CLIS_WHATACTIVITY";
		clisDbFields[ tableFldConstants.whatactivity.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.wherelocation.ordinal() ].fieldName = "CLIS_WHERELOCATION";
		clisDbFields[ tableFldConstants.wherelocation.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.standard.ordinal() ].fieldName = "CLIS_STANDARD";
		clisDbFields[ tableFldConstants.standard.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.whyifnotdone.ordinal() ].fieldName = "CLIS_WHYIFNOTDONE";
		clisDbFields[ tableFldConstants.whyifnotdone.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.frequencyunit.ordinal() ].fieldName = "CLIS_FREQUENCYUNIT";
		clisDbFields[ tableFldConstants.frequencyunit.ordinal() ].fieldType = 'C';

		clisDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "CLIS_FREQUENCY";
		clisDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.responsibilityid.ordinal() ].fieldName = "CLIS_RESPONSIBILITYID";
		clisDbFields[ tableFldConstants.responsibilityid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.responsibilitydesgid.ordinal() ].fieldName = "CLIS_RESPONSIBILITYDESGID";
		clisDbFields[ tableFldConstants.responsibilitydesgid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.istoolsreq.ordinal() ].fieldName = "CLIS_ISTOOLSREQ";
		clisDbFields[ tableFldConstants.istoolsreq.ordinal() ].fieldType = 'C';

		clisDbFields[ tableFldConstants.howmethod.ordinal() ].fieldName = "CLIS_HOWMETHOD";
		clisDbFields[ tableFldConstants.howmethod.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "CLIS_STARTDATE";
		clisDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.startweekno.ordinal() ].fieldName = "CLIS_STARTWEEKNO";
		clisDbFields[ tableFldConstants.startweekno.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.lastdonedate.ordinal() ].fieldName = "CLIS_LASTDONEDATE";
		clisDbFields[ tableFldConstants.lastdonedate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.lastweekno.ordinal() ].fieldName = "CLIS_LASTWEEKNO";
		clisDbFields[ tableFldConstants.lastweekno.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.nextduedate.ordinal() ].fieldName = "CLIS_NEXTDUEDATE";
		clisDbFields[ tableFldConstants.nextduedate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.nextdueweekno.ordinal() ].fieldName = "CLIS_NEXTDUEWEEKNO";
		clisDbFields[ tableFldConstants.nextdueweekno.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.monthweekno.ordinal() ].fieldName = "CLIS_MONTHWEEKNO";
		clisDbFields[ tableFldConstants.monthweekno.ordinal() ].fieldType = 'N';

		clisDbFields[ tableFldConstants.preparedbyid.ordinal() ].fieldName = "CLIS_PREPAREDBYID";
		clisDbFields[ tableFldConstants.preparedbyid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldName = "CLIS_WOGENFLAG";
		clisDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldType = 'C';

		clisDbFields[ tableFldConstants.lastwoid.ordinal() ].fieldName = "CLIS_LASTWOID";
		clisDbFields[ tableFldConstants.lastwoid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.lastwogendate.ordinal() ].fieldName = "CLIS_LASTWOGENDATE";
		clisDbFields[ tableFldConstants.lastwogendate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.lastfeedbackid.ordinal() ].fieldName = "CLIS_LASTFEEDBACKID";
		clisDbFields[ tableFldConstants.lastfeedbackid.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.lastfeedbackdate.ordinal() ].fieldName = "CLIS_LASTFEEDBACKDATE";
		clisDbFields[ tableFldConstants.lastfeedbackdate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldName = "CLIS_INACTIVATEDDATE";
		clisDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CLIS_ELEMENTID";
		clisDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		clisDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CLIS_FLID";
		clisDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		clisDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CLIS_ACTIVE";
		clisDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		clisDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CLIS_CREATEDBY";
		clisDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		clisDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CLIS_CREATEDON";
		clisDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		clisDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CLIS_MODIFIEDON";
		clisDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_CLI_TL_STANDARDS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_CLI_TL_STANDARDS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_CLI_TL_STANDARDS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	//for del/inactive data
	public static String inactivatejhclitstddatasql(String keyId,String sysdate,String createdby,String inactivateDate) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_BAL_CLI_TL_STANDARDS +" SET CLIS_ACTIVE = 'N' , CLIS_INACTIVATEDDATE = '"+inactivateDate+"',CLIS_MODIFIEDON = TO_DATE('"+sysdate+"','DD-MON-YYYY HH24:MI:SS'),CLIS_CREATEDBY ='"+createdby+"' Where CLIS_KEYID= '"+keyId+"'" ;
		    
	}
	//for grid
	public static String getjhclitcountListSql() {
		// TODO Auto-generated method stub
		System.out.println("inside jhcsql");
//		return "JHN_PC_CLISTANDARD.JHN_FN_GETMCHWISECLICOUNT";
		//hari-12june-2026
		return "JHN_FN_GETMCHWISECLICOUNT";
		
	}
	public static String getjhclitmchareaListSql() {
		// TODO Auto-generated method stub
		System.out.println("inside jhmachineareaql");
		return "JHN_PC_CLISTANDARD.JHN_FN_GETMCHAREA";
		
	}

	public static String getjhclitstandardsSql() {
		// TODO Auto-generated method stub
		System.out.println("inside jhstandardSQL");
//		return "JHN_PC_CLISTANDARD.JHN_FN_GETCLISTANDARDS";
		//hari-12june-2026
		return "JHN_FN_GETCLISTANDARDS";

	}
	
	//for form
	public static String getjhclitfrmdatasql()
	{
		return "SELECT * from " + TBL_BAL_CLI_TL_STANDARDS + " where CLIS_KEYID= ?";
	}
	//for recall fact sect cell when select machine
	public static String getfillfscvalsql()
	{
		return "GEN_PC_COMMONFUNCTIONS.GEN_FN_GETMCHHIERARCHY";
	}

	public static String getCheckcalTblSql(String keyId) {
		// TODO Auto-generated method stub
		return "SELECT DISTINCT CLCA_CLIREFID FROM " + TBL_BAL_CLI_TL_CALENDAR + " where CLCA_CLIREFID= '"+keyId+"'";
	}

	public static String updatejhclitfrmdatasql(String keyId) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_BAL_CLI_TL_CALENDAR +" SET CLCA_ACTIVE = 'N' Where CLCA_CLIREFID= '"+keyId+"'" ;
	}

	public static String updatejhclitStddatasql(String keyId) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_BAL_CLI_TL_STANDARDS +" SET CLIS_ACTIVE = 'N' Where CLIS_KEYID= '"+keyId+"'" ;
	}

	public static String getjhclitcountSql() {
		// TODO Auto-generated method stub
		System.out.println("inside jhstandardSQL getin count");
		return "JHN_PC_CLISTANDARD.JHN_FN_GETMCHWISETOTALROWS";
	}

	public static String getAddMachine(String machineID, String getEquipmentId) {
		// TODO Auto-generated method stub
		System.out.println("inside addmachine SQL");
		return " SELECT DISTINCT  '0'  as Tick, MCAM_KEYID,MCAM_NAME ,MCAM_CODE FROM"+
		TBL_GEN_TL_MACHINEAREAMST +" where MCAM_ACTIVE ='Y'  AND MCAM_KEYID NOT IN "+
		"(SELECT MMAL_MACHINEAREAID FROM "+ TBL_GEN_TL_MCHMACHINEAREALINK +" " +
		" where MMAL_MACHINEID = ?)  AND MCAM_EQPGROUPID IN ( ? ,'{}')";
	}

	public static String getEqpGrp(String machineID) {
		// TODO Auto-generated method stub
		return "select MCHM_EQUIPMENTGROUP from gen_tl_machinemst where MCHM_KEYID = ?";
	}

	public static String getAddMachine(String toolclisid) {
		// TODO Auto-generated method stub
		String sql = "";
		CommonFunctions.debugMsg("sql tools    :"+sql);
		return sql;
	}

	public static String gettooldata(String toolclisid) {
		// TODO Auto-generated method stub
		String sql = "select PTLD_TOOLID,TOLM_NAME from "+TBL_PLM_TL_TOOLSDTL+","+
		TBL_GEN_TL_TOOLSMST +" where PTLD_TOOLID = TOLM_KEYID AND ptld_standardid = '"+toolclisid+"'";
		CommonFunctions.debugMsg("sql tools    :"+sql);
		return sql;
	}
//	for updating docupdates For BD FORM
	public static String updateDocUpdatesdatasql(String dkeyId,String cliKey) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+ TBL_GEN_TL_DOCUPDATES +" SET DCUP_DETAILID = '"+cliKey+"' Where DCUP_KEYID= '"+dkeyId+"'" ;
		CommonFunctions.debugMsg("UPDATE DOCUPDATES   :"+sql);
		return sql;
	}

	public static String getDesgIDsql(String empId) {
		// TODO Auto-generated method stub
		String sql = "select empm_designationid from "+ TableNames.TBL_GEN_TL_EMPLOYEEMST +" where empm_keyid ='"+empId+"'";
		CommonFunctions.debugMsg("DesgIDcmnFilter   :"+sql);
		return sql;
	}

	public static String updateDocId(String refDocId,String clitId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE " + TBL_BAL_CLI_TL_STANDARDS + " SET CLIS_REFDOCNO='"+refDocId+"' WHERE  CLIS_KEYID = '"+clitId+"'";
		return sql;
	}
	
}

