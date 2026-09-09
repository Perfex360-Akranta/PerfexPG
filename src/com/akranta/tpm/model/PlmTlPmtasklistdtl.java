package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlPmtasklistdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, pmtmkeyid, task, standard, method, frequency, jobtypeid
		, inactiveby, inactivedate, reason, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public PlmTlPmtasklistdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPmtdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmtdKeyid(String pmtdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmtdKeyid;
	}

	public String getPmtdPmtmkeyid() {
		return (String) saveArray[ tableFldConstants.pmtmkeyid.ordinal() ];
	}

	public void setPmtdPmtmkeyid(String pmtdPmtmkeyid) {
		saveArray[ tableFldConstants.pmtmkeyid.ordinal() ] = pmtdPmtmkeyid;
	}

	public String getPmtdTask() {
		return (String) saveArray[ tableFldConstants.task.ordinal() ];
	}

	public void setPmtdTask(String pmtdTask) {
		saveArray[ tableFldConstants.task.ordinal() ] = pmtdTask;
	}

	public String getPmtdStandard() {
		return (String) saveArray[ tableFldConstants.standard.ordinal() ];
	}

	public void setPmtdStandard(String pmtdStandard) {
		saveArray[ tableFldConstants.standard.ordinal() ] = pmtdStandard;
	}

	public String getPmtdMethod() {
		return (String) saveArray[ tableFldConstants.method.ordinal() ];
	}

	public void setPmtdMethod(String pmtdMethod) {
		saveArray[ tableFldConstants.method.ordinal() ] = pmtdMethod;
	}

	public String getPmtdFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setPmtdFrequency(String pmtdFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = pmtdFrequency;
	}

	public String getPmtdJobtypeid() {
		return (String) saveArray[ tableFldConstants.jobtypeid.ordinal() ];
	}

	public void setPmtdJobtypeid(String pmtdJobtypeid) {
		saveArray[ tableFldConstants.jobtypeid.ordinal() ] = pmtdJobtypeid;
	}

	public String getPmtdInactiveby() {
		return (String) saveArray[ tableFldConstants.inactiveby.ordinal() ];
	}

	public void setPmtdInactiveby(String pmtdInactiveby) {
		saveArray[ tableFldConstants.inactiveby.ordinal() ] = pmtdInactiveby;
	}

	public String getPmtdInactivedate() {
		return (String) saveArray[ tableFldConstants.inactivedate.ordinal() ];
	}

	public void setPmtdInactivedate(String pmtdInactivedate) {
		saveArray[ tableFldConstants.inactivedate.ordinal() ] = pmtdInactivedate;
	}

	public String getPmtdReason() {
		return (String) saveArray[ tableFldConstants.reason.ordinal() ];
	}

	public void setPmtdReason(String pmtdReason) {
		saveArray[ tableFldConstants.reason.ordinal() ] = pmtdReason;
	}

	public String getPmtdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPmtdTempfield4(String pmtdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = pmtdTempfield4;
	}

	public String getPmtdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmtdActive(String pmtdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmtdActive;
	}

	public String getPmtdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPmtdCreatedby(String pmtdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pmtdCreatedby;
	}

	public String getPmtdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPmtdCreatedon(String pmtdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pmtdCreatedon;
	}

	public String getPmtdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPmtdModifiedon(String pmtdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pmtdModifiedon;
	}

}

