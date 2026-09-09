package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBatchcompletion {

	private  Object [] saveArray = null; 
	private EntTlFeedbackRequestmst entTlFeedbackRequestmst;
	

	public enum   tableFldConstants
	{
		keyid, prog_keyid, bach_keyid, start_date, end_date, status, entry_date
		, entry_by, remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlBatchcompletion()
	{
		saveArray = new  Object [ 18 ];
		setEntTlFeedbackRequestmst(new EntTlFeedbackRequestmst());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public EntTlFeedbackRequestmst getEntTlFeedbackRequestmst() {
		return entTlFeedbackRequestmst;
	}

	public void setEntTlFeedbackRequestmst(
			EntTlFeedbackRequestmst entTlFeedbackRequestmst) {
		this.entTlFeedbackRequestmst = entTlFeedbackRequestmst;
	}

	public String getBcomKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBcomKeyid(String bcomKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bcomKeyid;
	}

	public String getBcomProgKeyid() {
		return (String) saveArray[ tableFldConstants.prog_keyid.ordinal() ];
	}

	public void setBcomProgKeyid(String bcomProgKeyid) {
		saveArray[ tableFldConstants.prog_keyid.ordinal() ] = bcomProgKeyid;
	}

	public String getBcomBachKeyid() {
		return (String) saveArray[ tableFldConstants.bach_keyid.ordinal() ];
	}

	public void setBcomBachKeyid(String bcomBachKeyid) {
		saveArray[ tableFldConstants.bach_keyid.ordinal() ] = bcomBachKeyid;
	}

	public String getBcomStartDate() {
		return (String) saveArray[ tableFldConstants.start_date.ordinal() ];
	}

	public void setBcomStartDate(String bcomStartDate) {
		saveArray[ tableFldConstants.start_date.ordinal() ] = bcomStartDate;
	}

	public String getBcomEndDate() {
		return (String) saveArray[ tableFldConstants.end_date.ordinal() ];
	}

	public void setBcomEndDate(String bcomEndDate) {
		saveArray[ tableFldConstants.end_date.ordinal() ] = bcomEndDate;
	}

	public String getBcomStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setBcomStatus(String bcomStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = bcomStatus;
	}

	public String getBcomEntryDate() {
		return (String) saveArray[ tableFldConstants.entry_date.ordinal() ];
	}

	public void setBcomEntryDate(String bcomEntryDate) {
		saveArray[ tableFldConstants.entry_date.ordinal() ] = bcomEntryDate;
	}

	public String getBcomEntryBy() {
		return (String) saveArray[ tableFldConstants.entry_by.ordinal() ];
	}

	public void setBcomEntryBy(String bcomEntryBy) {
		saveArray[ tableFldConstants.entry_by.ordinal() ] = bcomEntryBy;
	}

	public String getBcomRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBcomRemarks(String bcomRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bcomRemarks;
	}

	public String getBcomTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setBcomTempfield1(String bcomTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = bcomTempfield1;
	}

	public String getBcomTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setBcomTempfield2(String bcomTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = bcomTempfield2;
	}

	public String getBcomTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setBcomTempfield3(String bcomTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = bcomTempfield3;
	}

	public String getBcomTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setBcomTempfield4(String bcomTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = bcomTempfield4;
	}

	public String getBcomTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setBcomTempfield5(String bcomTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = bcomTempfield5;
	}

	public String getBcomActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBcomActive(String bcomActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bcomActive;
	}

	public String getBcomCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBcomCreatedby(String bcomCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bcomCreatedby;
	}

	public String getBcomCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBcomCreatedon(String bcomCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bcomCreatedon;
	}

	public String getBcomModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBcomModifiedon(String bcomModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bcomModifiedon;
	}

}

