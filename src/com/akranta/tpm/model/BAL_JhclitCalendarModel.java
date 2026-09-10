package com.akranta.tpm.model;

public class BAL_JhclitCalendarModel {
	
private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, assemblyid, planshiftid
		, actualshiftid, entrytype, clifrequency, clirefid, groupid, tradeid
		, monthyear, plandate, actualdate, rescheduledplan, rescheduledactual
		, allottedto, status, completedby, effplancompdate, duration
		, remarks, downtime, actualduration, starttime, endtime, active
		, createdby, createdon, modifiedon
	}

	public BAL_JhclitCalendarModel()
	{
		saveArray = new  Object [ 32 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getClcaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setClcaKeyid(String clcaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = clcaKeyid;
	}

	public String getClcaFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setClcaFactoryid(String clcaFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = clcaFactoryid;
	}

	public String getClcaSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setClcaSectionid(String clcaSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = clcaSectionid;
	}

	public String getClcaCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setClcaCellid(String clcaCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = clcaCellid;
	}

	public String getClcaMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setClcaMachineid(String clcaMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = clcaMachineid;
	}

	public String getClcaAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setClcaAssemblyid(String clcaAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = clcaAssemblyid;
	}

	public String getClcaPlanshiftid() {
		return (String) saveArray[ tableFldConstants.planshiftid.ordinal() ];
	}

	public void setClcaPlanshiftid(String clcaPlanshiftid) {
		saveArray[ tableFldConstants.planshiftid.ordinal() ] = clcaPlanshiftid;
	}

	public String getClcaActualshiftid() {
		return (String) saveArray[ tableFldConstants.actualshiftid.ordinal() ];
	}

	public void setClcaActualshiftid(String clcaActualshiftid) {
		saveArray[ tableFldConstants.actualshiftid.ordinal() ] = clcaActualshiftid;
	}

	public String getClcaEntrytype() {
		return (String) saveArray[ tableFldConstants.entrytype.ordinal() ];
	}

	public void setClcaEntrytype(String clcaEntrytype) {
		saveArray[ tableFldConstants.entrytype.ordinal() ] = clcaEntrytype;
	}

	public String getClcaClifrequency() {
		return (String) saveArray[ tableFldConstants.clifrequency.ordinal() ];
	}

	public void setClcaClifrequency(String clcaClifrequency) {
		saveArray[ tableFldConstants.clifrequency.ordinal() ] = clcaClifrequency;
	}

	public String getClcaClirefid() {
		return (String) saveArray[ tableFldConstants.clirefid.ordinal() ];
	}

	public void setClcaClirefid(String clcaClirefid) {
		saveArray[ tableFldConstants.clirefid.ordinal() ] = clcaClirefid;
	}

	public String getClcaGroupid() {
		return (String) saveArray[ tableFldConstants.groupid.ordinal() ];
	}

	public void setClcaGroupid(String clcaGroupid) {
		saveArray[ tableFldConstants.groupid.ordinal() ] = clcaGroupid;
	}

	public String getClcaTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setClcaTradeid(String clcaTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = clcaTradeid;
	}

	public String getClcaMonthyear() {
		return (String) saveArray[ tableFldConstants.monthyear.ordinal() ];
	}

	public void setClcaMonthyear(String clcaMonthyear) {
		saveArray[ tableFldConstants.monthyear.ordinal() ] = clcaMonthyear;
	}

	public String getClcaPlandate() {
		return (String) saveArray[ tableFldConstants.plandate.ordinal() ];
	}

	public void setClcaPlandate(String clcaPlandate) {
		saveArray[ tableFldConstants.plandate.ordinal() ] = clcaPlandate;
	}

	public String getClcaActualdate() {
		return (String) saveArray[ tableFldConstants.actualdate.ordinal() ];
	}

	public void setClcaActualdate(String clcaActualdate) {
		saveArray[ tableFldConstants.actualdate.ordinal() ] = clcaActualdate;
	}

	public String getClcaRescheduledplan() {
		return (String) saveArray[ tableFldConstants.rescheduledplan.ordinal() ];
	}

	public void setClcaRescheduledplan(String clcaRescheduledplan) {
		saveArray[ tableFldConstants.rescheduledplan.ordinal() ] = clcaRescheduledplan;
	}

	public String getClcaRescheduledactual() {
		return (String) saveArray[ tableFldConstants.rescheduledactual.ordinal() ];
	}

	public void setClcaRescheduledactual(String clcaRescheduledactual) {
		saveArray[ tableFldConstants.rescheduledactual.ordinal() ] = clcaRescheduledactual;
	}

	public String getClcaAllottedto() {
		return (String) saveArray[ tableFldConstants.allottedto.ordinal() ];
	}

	public void setClcaAllottedto(String clcaAllottedto) {
		saveArray[ tableFldConstants.allottedto.ordinal() ] = clcaAllottedto;
	}

	public String getClcaStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setClcaStatus(String clcaStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = clcaStatus;
	}

	public String getClcaCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setClcaCompletedby(String clcaCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = clcaCompletedby;
	}

	public String getClcaEffplancompdate() {
		return (String) saveArray[ tableFldConstants.effplancompdate.ordinal() ];
	}

	public void setClcaEffplancompdate(String clcaEffplancompdate) {
		saveArray[ tableFldConstants.effplancompdate.ordinal() ] = clcaEffplancompdate;
	}

	public String getClcaDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setClcaDuration(String clcaDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = clcaDuration;
	}

	public String getClcaRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setClcaRemarks(String clcaRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = clcaRemarks;
	}

	public String getClcaDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setClcaDowntime(String clcaDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = clcaDowntime;
	}

	public String getClcaActualduration() {
		return (String) saveArray[ tableFldConstants.actualduration.ordinal() ];
	}

	public void setClcaActualduration(String clcaActualduration) {
		saveArray[ tableFldConstants.actualduration.ordinal() ] = clcaActualduration;
	}

	public String getClcaStarttime() {
		return (String) saveArray[ tableFldConstants.starttime.ordinal() ];
	}

	public void setClcaStarttime(String clcaStarttime) {
		saveArray[ tableFldConstants.starttime.ordinal() ] = clcaStarttime;
	}

	public String getClcaEndtime() {
		return (String) saveArray[ tableFldConstants.endtime.ordinal() ];
	}

	public void setClcaEndtime(String clcaEndtime) {
		saveArray[ tableFldConstants.endtime.ordinal() ] = clcaEndtime;
	}

	public String getClcaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setClcaActive(String clcaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = clcaActive;
	}

	public String getClcaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setClcaCreatedby(String clcaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = clcaCreatedby;
	}

	public String getClcaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setClcaCreatedon(String clcaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = clcaCreatedon;
	}

	public String getClcaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setClcaModifiedon(String clcaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = clcaModifiedon;
	}

}

