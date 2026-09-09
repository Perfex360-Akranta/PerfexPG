package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlNearmissreportmst { 

	private  Object [] saveArray = null;  
	private List<GenTlNearmissreportdtl> nearmissreportDtl ;
	private String elementid;
	public enum   tableFldConstants
	{
		keyid, flnid, employeeid, deptid, severitypotentialid, descnearmiss
		, actionrecommended, probablerecrate, responsibility, identifiedby
		, targetdate, occurrencedatetime, remarks, status,completeddate,completedby,others ,othersuc, prepateddatetime
		, investigation, approvedby, tempfield1, tempfield2,tempfield3, active, createdby, createdon, modifiedon
		
		
	}

	public GenTlNearmissreportmst()
	{
		saveArray = new  Object [ 28 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray=saveArray;
	}
	public List<GenTlNearmissreportdtl> getnearmissreportDtl() {
		return nearmissreportDtl;
	}
	public void setnearmissreportDtl(List<GenTlNearmissreportdtl> nearmissreportDtl) {
		this.nearmissreportDtl = nearmissreportDtl;
	}
	public String getNmrtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNmrtKeyid(String nmrtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = nmrtKeyid;
	}

	public String getNmrtFlnid() {
		return (String) saveArray[ tableFldConstants.flnid.ordinal() ];
	}

	public void setNmrtFlnid(String nmrtFlnid) {
		saveArray[ tableFldConstants.flnid.ordinal() ] = nmrtFlnid;
	}

	public String getNmrtEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setNmrtEmployeeid(String nmrtEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = nmrtEmployeeid;
	}

	public String getNmrtDeptid() {
		return (String) saveArray[ tableFldConstants.deptid.ordinal() ];
	}

	public void setNmrtDeptid(String nmrtDeptid) {
		saveArray[ tableFldConstants.deptid.ordinal() ] = nmrtDeptid;
	}

	public String getNmrtSeveritypotentialid() {
		return (String) saveArray[ tableFldConstants.severitypotentialid.ordinal() ];
	}

	public void setNmrtSeveritypotentialid(String nmrtSeveritypotentialid) {
		saveArray[ tableFldConstants.severitypotentialid.ordinal() ] = nmrtSeveritypotentialid;
	}

	public String getNmrtDescnearmiss() {
		return (String) saveArray[ tableFldConstants.descnearmiss.ordinal() ];
	}

	public void setNmrtDescnearmiss(String nmrtDescnearmiss) {
		saveArray[ tableFldConstants.descnearmiss.ordinal() ] = nmrtDescnearmiss;
	}

	public String getNmrtActionrecommended() {
		return (String) saveArray[ tableFldConstants.actionrecommended.ordinal() ];
	}

	public void setNmrtActionrecommended(String nmrtActionrecommended) {
		saveArray[ tableFldConstants.actionrecommended.ordinal() ] = nmrtActionrecommended;
	}

	public String getNmrtProbablerecrate() {
		return (String) saveArray[ tableFldConstants.probablerecrate.ordinal() ];
	}

	public void setNmrtProbablerecrate(String nmrtProbablerecrate) {
		saveArray[ tableFldConstants.probablerecrate.ordinal() ] = nmrtProbablerecrate;
	}

	public String getNmrtResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setNmrtResponsibility(String nmrtResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = nmrtResponsibility;
	}

	public String getNmrtIdentifiedby() {
		return (String) saveArray[ tableFldConstants.identifiedby.ordinal() ];
	}

	public void setNmrtIdentifiedby(String nmrtIdentifiedby) {
		saveArray[ tableFldConstants.identifiedby.ordinal() ] = nmrtIdentifiedby;
	}

	public String getNmrtTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setNmrtTargetdate(String nmrtTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = nmrtTargetdate;
	}

	public String getNmrtOccurrencedatetime() {
		return (String) saveArray[ tableFldConstants.occurrencedatetime.ordinal() ];
	}

	public void setNmrtOccurrencedatetime(String nmrtOccurrencedatetime) {
		saveArray[ tableFldConstants.occurrencedatetime.ordinal() ] = nmrtOccurrencedatetime;
	}

	public String getNmrtRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setNmrtRemarks(String nmrtremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = nmrtremarks;
	}
	
	
	
	public String getNmrtStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setNmrtStatus(String nmrtStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = nmrtStatus;
	}
	
	
	public String getNmrtCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setNmrtCompleteddate(String nmrtCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = nmrtCompleteddate;
	}
	
	public String getNmrtCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setNmrtCompletedby(String nmrtCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = nmrtCompletedby;
	}
	
	
	public String getNmrtOthers() {
		return (String) saveArray[ tableFldConstants.others.ordinal() ];
	}

	public void setNmrtOthers(String nmrtOthers) {
		saveArray[ tableFldConstants.others.ordinal() ] = nmrtOthers;
	}
		

	public String getNmrtOthersuc() {
		return (String) saveArray[ tableFldConstants.othersuc.ordinal() ];
	}

	public void setNmrtOthersuc(String nmrtCthersuc) {
		saveArray[ tableFldConstants.othersuc.ordinal() ] = nmrtCthersuc;
	}

	public String getNmrtPreparedDatetime() {
		return (String) saveArray[ tableFldConstants.prepateddatetime.ordinal() ];
	}

	public void setNmrtPreparedDatetime(String nmrtPrepareddatetime) {
		saveArray[ tableFldConstants.prepateddatetime.ordinal() ] = nmrtPrepareddatetime;
	}

	public String getNmrtInvestigation() {
		return (String) saveArray[ tableFldConstants.investigation.ordinal() ];
	}

	public void setNmrtInvestigation(String nmrtInvestigation) {
		saveArray[ tableFldConstants.investigation.ordinal() ] = nmrtInvestigation;
	}

	public String getNmrtApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setNmrtApprovedby(String nmrtApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = nmrtApprovedby;
	}

	public String getNmrtTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setNmrtTempfield1(String nmrtTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = nmrtTempfield1;
	}

	public String getNmrtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setNmrtTempfield2(String nmrtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = nmrtTempfield2;
	}

	public String getNmrtTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setNmrtTempfield3(String nmrtTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = nmrtTempfield3;
	}

	public String getNmrtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNmrtActive(String nmrtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = nmrtActive;
	}

	public String getNmrtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNmrtCreatedby(String nmrtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = nmrtCreatedby;
	}

	public String getNmrtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNmrtCreatedon(String nmrtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = nmrtCreatedon;
	}

	public String getNmrtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNmrtModifiedon(String nmrtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = nmrtModifiedon;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}	
}

