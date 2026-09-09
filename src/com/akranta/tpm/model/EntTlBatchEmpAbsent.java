package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBatchEmpAbsent {

	private  Object [] saveArray = null; 
	private List<EntTlBatchEmpAbsent> batchEmpAbsent;
	private String topicId;

	public enum   tableFldConstants
	{
		keyid, bach_keyid, bsdl_keyid, empm_keyid, reason, takenby
		, attendance, tempfield3, active, createdby, createdon, modifiedon
	}

	public EntTlBatchEmpAbsent()
	{
		saveArray = new  Object [ 12 ];
		setBatchEmpAbsent(new ArrayList<EntTlBatchEmpAbsent>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEbeaKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEbeaKeyid(String ebeaKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ebeaKeyid;
	}

	public String getEbeaBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setEbeaBachKeyid(String ebeaBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = ebeaBachKeyid;
	}

	public String getEbeaBsdlKeyid() {
		return (String) saveArray[ tableFldConstants.bsdl_keyid.ordinal() ];
	}

	public void setEbeaBsdlKeyid(String ebeaBsdlKeyid) {
		saveArray[ tableFldConstants.bsdl_keyid.ordinal() ] = ebeaBsdlKeyid;
	}

	public String getEbeaEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setEbeaEmpmKeyid(String ebeaEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = ebeaEmpmKeyid;
	}

	public String getEbeaReason() {
		return (String) saveArray[ tableFldConstants.reason.ordinal() ];
	}

	public void setEbeaReason(String ebeaReason) {
		saveArray[ tableFldConstants.reason.ordinal() ] = ebeaReason;
	}

	public String getEbeaTakenby() {
		return (String) saveArray[ tableFldConstants.takenby.ordinal() ];
	}

	public void setEbeaTakenby(String ebeaTakenby) {
		saveArray[ tableFldConstants.takenby.ordinal() ] = ebeaTakenby;
	}

	public String getEbeaAttendance() {
		return (String) saveArray[ tableFldConstants.attendance.ordinal() ];
	}

	public void setEbeaAttendance(String ebeaAttendance) {
		saveArray[ tableFldConstants.attendance.ordinal() ] = ebeaAttendance;
	}

	public String getEbeaTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEbeaTempfield3(String ebeaTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ebeaTempfield3;
	}

	public String getEbeaActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEbeaActive(String ebeaActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ebeaActive;
	}

	public String getEbeaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEbeaCreatedby(String ebeaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ebeaCreatedby;
	}

	public String getEbeaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEbeaCreatedon(String ebeaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ebeaCreatedon;
	}

	public String getEbeaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEbeaModifiedon(String ebeaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ebeaModifiedon;
	}

	public List<EntTlBatchEmpAbsent> getBatchEmpAbsent() {
		return batchEmpAbsent;
	}

	public void setBatchEmpAbsent(List<EntTlBatchEmpAbsent> batchEmpAbsent) {
		this.batchEmpAbsent = batchEmpAbsent;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}

