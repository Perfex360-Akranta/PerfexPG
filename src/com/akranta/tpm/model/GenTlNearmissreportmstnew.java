
package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlNearmissreportmstnew{
	private  Object [] saveArray=null; 
	private String elementid;

	public enum   tableFldConstants{
		keyid, severitypotentialid, occurrencedatetime, flnid, employeeid
		,descnearmiss, identifiedby, deptid, prepareddatetime, investigation
		,probablerecrate, chkothersusa, chkothersusc, othersusa, othersusc
		,remarks, actionplanno, whywhyno, investigationby, investigationdate
		, actionrecommended,invesremarks, responsibility, status, targetdate,responseremarks
		,revisedtarget, actiontaken, completedby, datecompleted
		,actionclsremarks, verifiedstatus, verifiedby
		, dateverified,verifiedremarks, closed, closedby, closeddate,closedbyremarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, approvedby, active, createdby
		, createdon, modifiedby, modifiedon	
	}
	
	public GenTlNearmissreportmstnew()
	{
		saveArray = new  Object [ 50 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray=saveArray;
	}
	public String getNmrnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNmrnKeyid(String nmrnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = nmrnKeyid;
	}

	public String getNmrnSeveritypotentialid() {
		return (String) saveArray[ tableFldConstants.severitypotentialid.ordinal() ];
	}

	public void setNmrnSeveritypotentialid(String nmrnSeveritypotentialid) {
		saveArray[ tableFldConstants.severitypotentialid.ordinal() ] = nmrnSeveritypotentialid;
	}

	public String getNmrnOccurrencedatetime() {
		return (String) saveArray[ tableFldConstants.occurrencedatetime.ordinal() ];
	}

	public void setNmrnOccurrencedatetime(String nmrnOccurrencedatetime) {
		saveArray[ tableFldConstants.occurrencedatetime.ordinal() ] = nmrnOccurrencedatetime;
	}

	public String getNmrnFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setNmrnFlnid(String nmrnFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = nmrnFlnid;
	}

	public String getNmrnEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setNmrnEmployeeid(String nmrnEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = nmrnEmployeeid;
	}

	public String getNmrnDescnearmiss() {
		return (String) saveArray[ tableFldConstants.descnearmiss.ordinal() ];
	}

	public void setNmrnDescnearmiss(String nmrnDescnearmiss) {
		saveArray[ tableFldConstants.descnearmiss.ordinal() ] = nmrnDescnearmiss;
	}

	public String getNmrnIdentifiedby() {
		return (String) saveArray[ tableFldConstants.identifiedby.ordinal() ];
	}

	public void setNmrnIdentifiedby(String nmrnIdentifiedby) {
		saveArray[ tableFldConstants.identifiedby.ordinal() ] = nmrnIdentifiedby;
	}

	public String getNmrnDeptid() {
		return (String) saveArray[ tableFldConstants.deptid.ordinal() ];
	}

	public void setNmrnDeptid(String nmrnDeptid) {
		saveArray[ tableFldConstants.deptid.ordinal() ] = nmrnDeptid;
	}

	public String getNmrnPrepareddatetime() {
		return (String) saveArray[ tableFldConstants.prepareddatetime.ordinal() ];
	}

	public void setNmrnPrepareddatetime(String nmrnPrepareddatetime) {
		saveArray[ tableFldConstants.prepareddatetime.ordinal() ] = nmrnPrepareddatetime;
	}

	public String getNmrnInvestigation() {
		return (String) saveArray[ tableFldConstants.investigation.ordinal() ];
	}

	public void setNmrnInvestigation(String nmrnInvestigation) {
		saveArray[ tableFldConstants.investigation.ordinal() ] = nmrnInvestigation;
	}

	public String getNmrnProbablerecrate() {
		return (String) saveArray[ tableFldConstants.probablerecrate.ordinal() ];
	}

	public void setNmrnProbablerecrate(String nmrnProbablerecrate) {
		saveArray[ tableFldConstants.probablerecrate.ordinal() ] = nmrnProbablerecrate;
	}

	public String getNmrnChkothersusa() {
		return (String) saveArray[ tableFldConstants.chkothersusa.ordinal() ];
	}

	public void setNmrnChkothersusa(String nmrnChkothersusa) {
		saveArray[ tableFldConstants.chkothersusa.ordinal() ] = nmrnChkothersusa;
	}

	public String getNmrnChkothersusc() {
		return (String) saveArray[ tableFldConstants.chkothersusc.ordinal() ];
	}

	public void setNmrnChkothersusc(String nmrnChkothersusc) {
		saveArray[ tableFldConstants.chkothersusc.ordinal() ] = nmrnChkothersusc;
	}

	public String getNmrnOthersusa() {
		return (String) saveArray[ tableFldConstants.othersusa.ordinal() ];
	}

	public void setNmrnOthersusa(String nmrnOthersusa) {
		saveArray[ tableFldConstants.othersusa.ordinal() ] = nmrnOthersusa;
	}

	public String getNmrnOthersusc() {
		return (String) saveArray[ tableFldConstants.othersusc.ordinal() ];
	}

	public void setNmrnOthersusc(String nmrnOthersusc) {
		saveArray[ tableFldConstants.othersusc.ordinal() ] = nmrnOthersusc;
	}

	public String getNmrnRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setNmrnRemarks(String nmrnRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = nmrnRemarks;
	}

	public String getNmrnActionplanno() {
		return (String) saveArray[ tableFldConstants.actionplanno.ordinal() ];
	}

	public void setNmrnActionplanno(String nmrnActionplanno) {
		saveArray[ tableFldConstants.actionplanno.ordinal() ] = nmrnActionplanno;
	}

	public String getNmrnWhywhyno() {
		return (String) saveArray[ tableFldConstants.whywhyno.ordinal() ];
	}

	public void setNmrnWhywhyno(String nmrnWhywhyno) {
		saveArray[ tableFldConstants.whywhyno.ordinal() ] = nmrnWhywhyno;
	}

	public String getNmrnInvestigationby() {
		return (String) saveArray[ tableFldConstants.investigationby.ordinal() ];
	}

	public void setNmrnInvestigationby(String nmrnInvestigationby) {
		saveArray[ tableFldConstants.investigationby.ordinal() ] = nmrnInvestigationby;
	}

	public String getNmrnInvestigationdate() {
		return (String) saveArray[ tableFldConstants.investigationdate.ordinal() ];
	}

	public void setNmrnInvestigationdate(String nmrnInvestigationdate) {
		saveArray[ tableFldConstants.investigationdate.ordinal() ] = nmrnInvestigationdate;
	}

	public String getNmrnActionrecommended() {
		return (String) saveArray[ tableFldConstants.actionrecommended.ordinal() ];
	}

	public void setNmrnActionrecommended(String nmrnActionrecommended) {
		saveArray[ tableFldConstants.actionrecommended.ordinal() ] = nmrnActionrecommended;
	}

	public String getNmrnResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setNmrnResponsibility(String nmrnResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = nmrnResponsibility;
	}

	public String getNmrnStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setNmrnStatus(String nmrnStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = nmrnStatus;
	}

	public String getNmrnTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setNmrnTargetdate(String nmrnTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = nmrnTargetdate;
	}

	public String getNmrnRevisedtarget() {
		return (String) saveArray[ tableFldConstants.revisedtarget.ordinal() ];
	}

	public void setNmrnRevisedtarget(String nmrnRevisedtarget) {
		saveArray[ tableFldConstants.revisedtarget.ordinal() ] = nmrnRevisedtarget;
	}

	public String getNmrnActiontaken() {
		return (String) saveArray[ tableFldConstants.actiontaken.ordinal() ];
	}

	public void setNmrnActiontaken(String nmrnActiontaken) {
		saveArray[ tableFldConstants.actiontaken.ordinal() ] = nmrnActiontaken;
	}

	public String getNmrnCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setNmrnCompletedby(String nmrnCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = nmrnCompletedby;
	}

	public String getNmrnDatecompleted() {
		return (String) saveArray[ tableFldConstants.datecompleted.ordinal() ];
	}

	public void setNmrnDatecompleted(String nmrnDatecompleted) {
		saveArray[ tableFldConstants.datecompleted.ordinal() ] = nmrnDatecompleted;
	}

	public String getNmrnVerifiedstatus() {
		return (String) saveArray[ tableFldConstants.verifiedstatus.ordinal() ];
	}

	public void setNmrnVerifiedstatus(String nmrnVerifiedstatus) {
		saveArray[ tableFldConstants.verifiedstatus.ordinal() ] = nmrnVerifiedstatus;
	}

	public String getNmrnVerifiedby() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setNmrnVerifiedby(String nmrnVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = nmrnVerifiedby;
	}

	public String getNmrnDateverified() {
		return (String) saveArray[ tableFldConstants.dateverified.ordinal() ];
	}

	public void setNmrnDateverified(String nmrnDateverified) {
		saveArray[ tableFldConstants.dateverified.ordinal() ] = nmrnDateverified;
	}

	public String getNmrnClosed() {
		return (String) saveArray[ tableFldConstants.closed.ordinal() ];
	}

	public void setNmrnClosed(String nmrnClosed) {
		saveArray[ tableFldConstants.closed.ordinal() ] = nmrnClosed;
	}

	public String getNmrnClosedby() {
		return (String) saveArray[ tableFldConstants.closedby.ordinal() ];
	}

	public void setNmrnClosedby(String nmrnClosedby) {
		saveArray[ tableFldConstants.closedby.ordinal() ] = nmrnClosedby;
	}

	public String getNmrnCloseddate() {
		return (String) saveArray[ tableFldConstants.closeddate.ordinal() ];
	}

	public void setNmrnCloseddate(String nmrnCloseddate) {
		saveArray[ tableFldConstants.closeddate.ordinal() ] = nmrnCloseddate;
	}

	
	public String getNmrnClosedRemarks() {
		return (String) saveArray[ tableFldConstants.closedbyremarks.ordinal() ];
	}

	public void setNmrnClosedRemarks(String nmrnClosedRemarks) {
		saveArray[ tableFldConstants.closedbyremarks.ordinal() ] = nmrnClosedRemarks;
	}

	public String getNmrnVerifiedRemarks() {
		return (String) saveArray[ tableFldConstants.verifiedremarks.ordinal() ];
	}

	public void setNmrnVerifiedRemarks(String nmrnVerifiedRemarks) {
		saveArray[ tableFldConstants.verifiedremarks.ordinal() ] = nmrnVerifiedRemarks;
	}
	
	
	public String getNmrnActnCloseRemarks() {
		return (String) saveArray[ tableFldConstants.actionclsremarks.ordinal() ];
	}

	public void setNmrnActnCloseRemarks(String nmrnActnCloseRemarks) {
		saveArray[ tableFldConstants.actionclsremarks.ordinal() ] = nmrnActnCloseRemarks;
	}
	
	
	
	public String getNmrnInveRemarks() {
		return (String) saveArray[ tableFldConstants.invesremarks.ordinal() ];
	}

	public void setNmrnInveRemarks(String nmrnInveRemarks) {
		saveArray[ tableFldConstants.invesremarks.ordinal() ] = nmrnInveRemarks;
	}
	
	public String getNmrnResponseRemarks() {
		return (String) saveArray[ tableFldConstants.responseremarks.ordinal() ];
	}

	public void setNmrnResponseRemarks(String nmrnResponseRemarks) {
		saveArray[ tableFldConstants.responseremarks.ordinal() ] = nmrnResponseRemarks;
	}
	
	
	public String getNmrnTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setNmrnTempfield1(String nmrnTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = nmrnTempfield1;
	}

	public String getNmrnTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setNmrnTempfield2(String nmrnTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = nmrnTempfield2;
	}

	public String getNmrnTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setNmrnTempfield3(String nmrnTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = nmrnTempfield3;
	}

	public String getNmrnTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setNmrnTempfield4(String nmrnTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = nmrnTempfield4;
	}

	public String getNmrnTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setNmrnTempfield5(String nmrnTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = nmrnTempfield5;
	}

	public String getNmrnApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setNmrnApprovedby(String nmrnApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = nmrnApprovedby;
	}

	public String getNmrnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNmrnActive(String nmrnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = nmrnActive;
	}

	public String getNmrnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNmrnCreatedby(String nmrnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = nmrnCreatedby;
	}

	public String getNmrnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNmrnCreatedon(String nmrnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = nmrnCreatedon;
	}

	public String getNmrnModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setNmrnModifiedby(String nmrnModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = nmrnModifiedby;
	}

	public String getNmrnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNmrnModifiedon(String nmrnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = nmrnModifiedon;
	}
	
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}

}

