package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlChecklistdtl {

	private  Object [] saveArray = null;  
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid, chkm_keyid, name, orderno, remarks, effective_date, inactive_date
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public EntTlChecklistdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getChkdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setChkdKeyid(String chkdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = chkdKeyid;
	}

	public String getChkdChkmKeyid() {
		return (String) saveArray[ tableFldConstants.chkm_keyid.ordinal() ];
	}

	public void setChkdChkmKeyid(String chkdChkmKeyid) {
		saveArray[ tableFldConstants.chkm_keyid.ordinal() ] = chkdChkmKeyid;
	}

	public String getChkdName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setChkdName(String chkdName) {
		saveArray[ tableFldConstants.name.ordinal() ] = chkdName;
	}

	public String getChkdOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setChkdOrderno(String chkdOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = chkdOrderno;
	}

	public String getChkdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setChkdRemarks(String chkdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = chkdRemarks;
	}

	public String getChkdEffectiveDate() {
		return (String) saveArray[ tableFldConstants.effective_date.ordinal() ];
	}

	public void setChkdEffectiveDate(String chkdEffectiveDate) {
		saveArray[ tableFldConstants.effective_date.ordinal() ] = chkdEffectiveDate;
	}

	public String getChkdInactiveDate() {
		return (String) saveArray[ tableFldConstants.inactive_date.ordinal() ];
	}

	public void setChkdInactiveDate(String chkdInactiveDate) {
		saveArray[ tableFldConstants.inactive_date.ordinal() ] = chkdInactiveDate;
	}

	public String getChkdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setChkdTempfield1(String chkdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = chkdTempfield1;
	}

	public String getChkdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setChkdTempfield2(String chkdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = chkdTempfield2;
	}

	public String getChkdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setChkdTempfield3(String chkdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = chkdTempfield3;
	}

	public String getChkdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setChkdTempfield4(String chkdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = chkdTempfield4;
	}

	public String getChkdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setChkdActive(String chkdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = chkdActive;
	}

	public String getChkdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setChkdCreatedby(String chkdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = chkdCreatedby;
	}

	public String getChkdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setChkdCreatedon(String chkdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = chkdCreatedon;
	}

	public String getChkdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setChkdModifiedon(String chkdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = chkdModifiedon;
	}

	

}

