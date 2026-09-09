package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlRescheduledtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pmcalendarid, pmdetailsid, rescheduletype, calendarstatus
		, pmactivitytype, prestartdate, preenddate, currentstartdate
		, currentenddate, responsibilityid, reschedulereason, rescheduleremarks
		, revisedbyid, reason, remarks, status, active, createdby, createdon
		, modifiedon
	}

	public PlmTlRescheduledtl()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPrsrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPrsrKeyid(String prsrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = prsrKeyid;
	}

	public String getPrsrPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}

	public void setPrsrPmcalendarid(String prsrPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = prsrPmcalendarid;
	}

	public String getPrsrPmdetailsid() {
		return (String) saveArray[ tableFldConstants.pmdetailsid.ordinal() ];
	}

	public void setPrsrPmdetailsid(String prsrPmdetailsid) {
		saveArray[ tableFldConstants.pmdetailsid.ordinal() ] = prsrPmdetailsid;
	}

	public String getPrsrRescheduletype() {
		return (String) saveArray[ tableFldConstants.rescheduletype.ordinal() ];
	}

	public void setPrsrRescheduletype(String prsrRescheduletype) {
		saveArray[ tableFldConstants.rescheduletype.ordinal() ] = prsrRescheduletype;
	}

	public String getPrsrCalendarstatus() {
		return (String) saveArray[ tableFldConstants.calendarstatus.ordinal() ];
	}

	public void setPrsrCalendarstatus(String prsrCalendarstatus) {
		saveArray[ tableFldConstants.calendarstatus.ordinal() ] = prsrCalendarstatus;
	}

	public String getPrsrPmactivitytype() {
		return (String) saveArray[ tableFldConstants.pmactivitytype.ordinal() ];
	}

	public void setPrsrPmactivitytype(String prsrPmactivitytype) {
		saveArray[ tableFldConstants.pmactivitytype.ordinal() ] = prsrPmactivitytype;
	}

	public String getPrsrPrestartdate() {
		return (String) saveArray[ tableFldConstants.prestartdate.ordinal() ];
	}

	public void setPrsrPrestartdate(String prsrPrestartdate) {
		saveArray[ tableFldConstants.prestartdate.ordinal() ] = prsrPrestartdate;
	}

	public String getPrsrPreenddate() {
		return (String) saveArray[ tableFldConstants.preenddate.ordinal() ];
	}

	public void setPrsrPreenddate(String prsrPreenddate) {
		saveArray[ tableFldConstants.preenddate.ordinal() ] = prsrPreenddate;
	}

	public String getPrsrCurrentstartdate() {
		return (String) saveArray[ tableFldConstants.currentstartdate.ordinal() ];
	}

	public void setPrsrCurrentstartdate(String prsrCurrentstartdate) {
		saveArray[ tableFldConstants.currentstartdate.ordinal() ] = prsrCurrentstartdate;
	}

	public String getPrsrCurrentenddate() {
		return (String) saveArray[ tableFldConstants.currentenddate.ordinal() ];
	}

	public void setPrsrCurrentenddate(String prsrCurrentenddate) {
		saveArray[ tableFldConstants.currentenddate.ordinal() ] = prsrCurrentenddate;
	}

	public String getPrsrResponsibilityid() {
		return (String) saveArray[ tableFldConstants.responsibilityid.ordinal() ];
	}

	public void setPrsrResponsibilityid(String prsrResponsibilityid) {
		saveArray[ tableFldConstants.responsibilityid.ordinal() ] = prsrResponsibilityid;
	}

	public String getPrsrReschedulereason() {
		return (String) saveArray[ tableFldConstants.reschedulereason.ordinal() ];
	}

	public void setPrsrReschedulereason(String prsrReschedulereason) {
		saveArray[ tableFldConstants.reschedulereason.ordinal() ] = prsrReschedulereason;
	}

	public String getPrsrRescheduleremarks() {
		return (String) saveArray[ tableFldConstants.rescheduleremarks.ordinal() ];
	}

	public void setPrsrRescheduleremarks(String prsrRescheduleremarks) {
		saveArray[ tableFldConstants.rescheduleremarks.ordinal() ] = prsrRescheduleremarks;
	}

	public String getPrsrRevisedbyid() {
		return (String) saveArray[ tableFldConstants.revisedbyid.ordinal() ];
	}

	public void setPrsrRevisedbyid(String prsrRevisedbyid) {
		saveArray[ tableFldConstants.revisedbyid.ordinal() ] = prsrRevisedbyid;
	}

	public String getPrsrReason() {
		return (String) saveArray[ tableFldConstants.reason.ordinal() ];
	}

	public void setPrsrReason(String prsrReason) {
		saveArray[ tableFldConstants.reason.ordinal() ] = prsrReason;
	}

	public String getPrsrRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPrsrRemarks(String prsrRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = prsrRemarks;
	}

	public String getPrsrStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setPrsrStatus(String prsrStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = prsrStatus;
	}

	public String getPrsrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPrsrActive(String prsrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = prsrActive;
	}

	public String getPrsrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPrsrCreatedby(String prsrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = prsrCreatedby;
	}

	public String getPrsrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPrsrCreatedon(String prsrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = prsrCreatedon;
	}

	public String getPrsrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPrsrModifiedon(String prsrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = prsrModifiedon;
	}

}

