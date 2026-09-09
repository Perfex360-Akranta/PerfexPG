package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlNominationdtl {

	private  Object [] saveArray = null;  
	private String isDelete="";
	public enum   tableFldConstants
	{
		keyid, nomm_keyid, empm_keyid, cur_keyid, tar_keyid, lasttrn_date
		, mailsent, mailsent_date, isapproved, approved_by, approved_date
		, approved_remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public EntTlNominationdtl()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getNoddKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setNoddKeyid(String noddKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = noddKeyid;
	}

	public String getNoddNommKeyid() {
		return (String) saveArray[ tableFldConstants.nomm_keyid.ordinal() ];
	}

	public void setNoddNommKeyid(String noddNommKeyid) {
		saveArray[ tableFldConstants.nomm_keyid.ordinal() ] = noddNommKeyid;
	}

	public String getNoddEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setNoddEmpmKeyid(String noddEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = noddEmpmKeyid;
	}

	public String getNoddCurKeyid() {
		return (String) saveArray[ tableFldConstants.cur_keyid.ordinal() ];
	}

	public void setNoddCurKeyid(String noddCurKeyid) {
		saveArray[ tableFldConstants.cur_keyid.ordinal() ] = noddCurKeyid;
	}

	public String getNoddTarKeyid() {
		return (String) saveArray[ tableFldConstants.tar_keyid.ordinal() ];
	}

	public void setNoddTarKeyid(String noddTarKeyid) {
		saveArray[ tableFldConstants.tar_keyid.ordinal() ] = noddTarKeyid;
	}

	public String getNoddLasttrnDate() {
		return (String) saveArray[ tableFldConstants.lasttrn_date.ordinal() ];
	}

	public void setNoddLasttrnDate(String noddLasttrnDate) {
		saveArray[ tableFldConstants.lasttrn_date.ordinal() ] = noddLasttrnDate;
	}

	public String getNoddMailsent() {
		return (String) saveArray[ tableFldConstants.mailsent.ordinal() ];
	}

	public void setNoddMailsent(String noddMailsent) {
		saveArray[ tableFldConstants.mailsent.ordinal() ] = noddMailsent;
	}

	public String getNoddMailsentDate() {
		return (String) saveArray[ tableFldConstants.mailsent_date.ordinal() ];
	}

	public void setNoddMailsentDate(String noddMailsentDate) {
		saveArray[ tableFldConstants.mailsent_date.ordinal() ] = noddMailsentDate;
	}

	public String getNoddIsapproved() {
		return (String) saveArray[ tableFldConstants.isapproved.ordinal() ];
	}

	public void setNoddIsapproved(String noddIsapproved) {
		saveArray[ tableFldConstants.isapproved.ordinal() ] = noddIsapproved;
	}

	public String getNoddApprovedBy() {
		return (String) saveArray[ tableFldConstants.approved_by.ordinal() ];
	}

	public void setNoddApprovedBy(String noddApprovedBy) {
		saveArray[ tableFldConstants.approved_by.ordinal() ] = noddApprovedBy;
	}

	public String getNoddApprovedDate() {
		return (String) saveArray[ tableFldConstants.approved_date.ordinal() ];
	}

	public void setNoddApprovedDate(String noddApprovedDate) {
		saveArray[ tableFldConstants.approved_date.ordinal() ] = noddApprovedDate;
	}

	public String getNoddApprovedRemarks() {
		return (String) saveArray[ tableFldConstants.approved_remarks.ordinal() ];
	}

	public void setNoddApprovedRemarks(String noddApprovedRemarks) {
		saveArray[ tableFldConstants.approved_remarks.ordinal() ] = noddApprovedRemarks;
	}

	public String getNoddTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setNoddTempfield1(String noddTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = noddTempfield1;
	}

	public String getNoddTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setNoddTempfield2(String noddTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = noddTempfield2;
	}

	public String getNoddTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setNoddTempfield3(String noddTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = noddTempfield3;
	}

	public String getNoddTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setNoddTempfield4(String noddTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = noddTempfield4;
	}

	public String getNoddTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setNoddTempfield5(String noddTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = noddTempfield5;
	}

	public String getNoddActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setNoddActive(String noddActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = noddActive;
	}

	public String getNoddCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setNoddCreatedby(String noddCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = noddCreatedby;
	}

	public String getNoddCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setNoddCreatedon(String noddCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = noddCreatedon;
	}

	public String getNoddModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setNoddModifiedon(String noddModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = noddModifiedon;
	}

	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	

}

