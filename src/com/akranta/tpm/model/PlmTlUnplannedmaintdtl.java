package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlUnplannedmaintdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, upmm_keyid, finalphenomena, finalcause, finalaction, tradeid
		, countermeasure, wwrequired, wwno, rootcause, preventivemeasure
		, rootcauseid, countermeasureid, preventivemeasureid, breakdowntime
		, worktime, classificationid, categoryid, issparesreplaced, alarmno
		, manpowercost, contractorcost, sparescost, othercost, status
		, remarks, actiontakenby, completedby, costcentre, erppoststatus
		, problemseverity, failuretype, isapproved, approverdby, tempfield1
		, active, createdby, createdon, modifiedon
	}

	public PlmTlUnplannedmaintdtl()
	{
		saveArray = new  Object [ 39 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getUpmdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUpmdKeyid(String upmdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = upmdKeyid;
	}

	public String getUpmdUpmmKeyid() {
		return (String) saveArray[ tableFldConstants.upmm_keyid.ordinal() ];
	}

	public void setUpmdUpmmKeyid(String upmdUpmmKeyid) {
		saveArray[ tableFldConstants.upmm_keyid.ordinal() ] = upmdUpmmKeyid;
	}

	public String getUpmdFinalphenomena() {
		return (String) saveArray[ tableFldConstants.finalphenomena.ordinal() ];
	}

	public void setUpmdFinalphenomena(String upmdFinalphenomena) {
		saveArray[ tableFldConstants.finalphenomena.ordinal() ] = upmdFinalphenomena;
	}

	public String getUpmdFinalcause() {
		return (String) saveArray[ tableFldConstants.finalcause.ordinal() ];
	}

	public void setUpmdFinalcause(String upmdFinalcause) {
		saveArray[ tableFldConstants.finalcause.ordinal() ] = upmdFinalcause;
	}

	public String getUpmdFinalaction() {
		return (String) saveArray[ tableFldConstants.finalaction.ordinal() ];
	}

	public void setUpmdFinalaction(String upmdFinalaction) {
		saveArray[ tableFldConstants.finalaction.ordinal() ] = upmdFinalaction;
	}

	public String getUpmdTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setUpmdTradeid(String upmdTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = upmdTradeid;
	}

	public String getUpmdCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setUpmdCountermeasure(String upmdCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = upmdCountermeasure;
	}

	public String getUpmdWwrequired() {
		return (String) saveArray[ tableFldConstants.wwrequired.ordinal() ];
	}

	public void setUpmdWwrequired(String upmdWwrequired) {
		saveArray[ tableFldConstants.wwrequired.ordinal() ] = upmdWwrequired;
	}

	public String getUpmdWwno() {
		return (String) saveArray[ tableFldConstants.wwno.ordinal() ];
	}

	public void setUpmdWwno(String upmdWwno) {
		saveArray[ tableFldConstants.wwno.ordinal() ] = upmdWwno;
	}

	public String getUpmdRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setUpmdRootcause(String upmdRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = upmdRootcause;
	}

	public String getUpmdPreventivemeasure() {
		return (String) saveArray[ tableFldConstants.preventivemeasure.ordinal() ];
	}

	public void setUpmdPreventivemeasure(String upmdPreventivemeasure) {
		saveArray[ tableFldConstants.preventivemeasure.ordinal() ] = upmdPreventivemeasure;
	}

	public String getUpmdRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setUpmdRootcauseid(String upmdRootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = upmdRootcauseid;
	}

	public String getUpmdCountermeasureid() {
		return (String) saveArray[ tableFldConstants.countermeasureid.ordinal() ];
	}

	public void setUpmdCountermeasureid(String upmdCountermeasureid) {
		saveArray[ tableFldConstants.countermeasureid.ordinal() ] = upmdCountermeasureid;
	}

	public String getUpmdPreventivemeasureid() {
		return (String) saveArray[ tableFldConstants.preventivemeasureid.ordinal() ];
	}

	public void setUpmdPreventivemeasureid(String upmdPreventivemeasureid) {
		saveArray[ tableFldConstants.preventivemeasureid.ordinal() ] = upmdPreventivemeasureid;
	}

	public String getUpmdBreakdowntime() {
		return (String) saveArray[ tableFldConstants.breakdowntime.ordinal() ];
	}

	public void setUpmdBreakdowntime(String upmdBreakdowntime) {
		saveArray[ tableFldConstants.breakdowntime.ordinal() ] = upmdBreakdowntime;
	}

	public String getUpmdWorktime() {
		return (String) saveArray[ tableFldConstants.worktime.ordinal() ];
	}

	public void setUpmdWorktime(String upmdWorktime) {
		saveArray[ tableFldConstants.worktime.ordinal() ] = upmdWorktime;
	}

	public String getUpmdClassificationid() {
		return (String) saveArray[ tableFldConstants.classificationid.ordinal() ];
	}

	public void setUpmdClassificationid(String upmdClassificationid) {
		saveArray[ tableFldConstants.classificationid.ordinal() ] = upmdClassificationid;
	}

	public String getUpmdCategoryid() {
		return (String) saveArray[ tableFldConstants.categoryid.ordinal() ];
	}

	public void setUpmdCategoryid(String upmdCategoryid) {
		saveArray[ tableFldConstants.categoryid.ordinal() ] = upmdCategoryid;
	}

	public String getUpmdIssparesreplaced() {
		return (String) saveArray[ tableFldConstants.issparesreplaced.ordinal() ];
	}

	public void setUpmdIssparesreplaced(String upmdIssparesreplaced) {
		saveArray[ tableFldConstants.issparesreplaced.ordinal() ] = upmdIssparesreplaced;
	}

	public String getUpmdAlarmno() {
		return (String) saveArray[ tableFldConstants.alarmno.ordinal() ];
	}

	public void setUpmdAlarmno(String upmdAlarmno) {
		saveArray[ tableFldConstants.alarmno.ordinal() ] = upmdAlarmno;
	}

	public String getUpmdManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setUpmdManpowercost(String upmdManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = upmdManpowercost;
	}

	public String getUpmdContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setUpmdContractorcost(String upmdContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = upmdContractorcost;
	}

	public String getUpmdSparescost() {
		return (String) saveArray[ tableFldConstants.sparescost.ordinal() ];
	}

	public void setUpmdSparescost(String upmdSparescost) {
		saveArray[ tableFldConstants.sparescost.ordinal() ] = upmdSparescost;
	}

	public String getUpmdOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setUpmdOthercost(String upmdOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = upmdOthercost;
	}

	public String getUpmdStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setUpmdStatus(String upmdStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = upmdStatus;
	}

	public String getUpmdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setUpmdRemarks(String upmdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = upmdRemarks;
	}

	public String getUpmdActiontakenby() {
		return (String) saveArray[ tableFldConstants.actiontakenby.ordinal() ];
	}

	public void setUpmdActiontakenby(String upmdActiontakenby) {
		saveArray[ tableFldConstants.actiontakenby.ordinal() ] = upmdActiontakenby;
	}

	public String getUpmdCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setUpmdCompletedby(String upmdCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = upmdCompletedby;
	}

	public String getUpmdCostcentre() {
		return (String) saveArray[ tableFldConstants.costcentre.ordinal() ];
	}

	public void setUpmdCostcentre(String upmdCostcentre) {
		saveArray[ tableFldConstants.costcentre.ordinal() ] = upmdCostcentre;
	}

	public String getUpmdErppoststatus() {
		return (String) saveArray[ tableFldConstants.erppoststatus.ordinal() ];
	}

	public void setUpmdErppoststatus(String upmdErppoststatus) {
		saveArray[ tableFldConstants.erppoststatus.ordinal() ] = upmdErppoststatus;
	}

	public String getUpmdProblemseverity() {
		return (String) saveArray[ tableFldConstants.problemseverity.ordinal() ];
	}

	public void setUpmdProblemseverity(String upmdProblemseverity) {
		saveArray[ tableFldConstants.problemseverity.ordinal() ] = upmdProblemseverity;
	}

	public String getUpmdFailuretype() {
		return (String) saveArray[ tableFldConstants.failuretype.ordinal() ];
	}

	public void setUpmdFailuretype(String upmdFailuretype) {
		saveArray[ tableFldConstants.failuretype.ordinal() ] = upmdFailuretype;
	}

	public String getUpmdIsapproved() {
		return (String) saveArray[ tableFldConstants.isapproved.ordinal() ];
	}

	public void setUpmdIsapproved(String upmdIsapproved) {
		saveArray[ tableFldConstants.isapproved.ordinal() ] = upmdIsapproved;
	}

	public String getUpmdApproverdby() {
		return (String) saveArray[ tableFldConstants.approverdby.ordinal() ];
	}

	public void setUpmdApproverdby(String upmdApproverdby) {
		saveArray[ tableFldConstants.approverdby.ordinal() ] = upmdApproverdby;
	}

	public String getUpmdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUpmdTempfield1(String upmdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = upmdTempfield1;
	}

	public String getUpmdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUpmdActive(String upmdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = upmdActive;
	}

	public String getUpmdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUpmdCreatedby(String upmdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = upmdCreatedby;
	}

	public String getUpmdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUpmdCreatedon(String upmdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = upmdCreatedon;
	}

	public String getUpmdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUpmdModifiedon(String upmdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = upmdModifiedon;
	}

}

