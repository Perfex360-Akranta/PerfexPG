package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSelfnominationmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, empid, progid, nominationdate, batchid, remarks
		, status, approvedby, approveddate, approvedremarks, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public EntTlSelfnominationmst()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSnomKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSnomKeyid(String snomKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = snomKeyid;
	}

	public String getSnomFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setSnomFlid(String snomFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = snomFlid;
	}

	public String getSnomEmpid() {
		return (String) saveArray[ tableFldConstants.empid.ordinal() ];
	}

	public void setSnomEmpid(String snomEmpid) {
		saveArray[ tableFldConstants.empid.ordinal() ] = snomEmpid;
	}

	public String getSnomProgid() {
		return (String) saveArray[ tableFldConstants.progid.ordinal() ];
	}

	public void setSnomProgid(String snomProgid) {
		saveArray[ tableFldConstants.progid.ordinal() ] = snomProgid;
	}

	public String getSnomNominationdate() {
		return (String) saveArray[ tableFldConstants.nominationdate.ordinal() ];
	}

	public void setSnomNominationdate(String snomNominationdate) {
		saveArray[ tableFldConstants.nominationdate.ordinal() ] = snomNominationdate;
	}

	public String getSnomBatchid() {
		return (String) saveArray[ tableFldConstants.batchid.ordinal() ];
	}

	public void setSnomBatchid(String snomBatchid) {
		saveArray[ tableFldConstants.batchid.ordinal() ] = snomBatchid;
	}

	public String getSnomRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setSnomRemarks(String snomRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = snomRemarks;
	}

	public String getSnomStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setSnomStatus(String snomStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = snomStatus;
	}

	public String getSnomApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setSnomApprovedby(String snomApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = snomApprovedby;
	}

	public String getSnomApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setSnomApproveddate(String snomApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = snomApproveddate;
	}

	public String getSnomApprovedremarks() {
		return (String) saveArray[ tableFldConstants.approvedremarks.ordinal() ];
	}

	public void setSnomApprovedremarks(String snomApprovedremarks) {
		saveArray[ tableFldConstants.approvedremarks.ordinal() ] = snomApprovedremarks;
	}

	public String getSnomTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSnomTempfield1(String snomTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = snomTempfield1;
	}

	public String getSnomTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSnomTempfield2(String snomTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = snomTempfield2;
	}

	public String getSnomTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSnomTempfield3(String snomTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = snomTempfield3;
	}

	public String getSnomTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setSnomTempfield4(String snomTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = snomTempfield4;
	}

	public String getSnomActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSnomActive(String snomActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = snomActive;
	}

	public String getSnomCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSnomCreatedby(String snomCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = snomCreatedby;
	}

	public String getSnomCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSnomCreatedon(String snomCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = snomCreatedon;
	}

	public String getSnomModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSnomModifiedon(String snomModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = snomModifiedon;
	}

}

