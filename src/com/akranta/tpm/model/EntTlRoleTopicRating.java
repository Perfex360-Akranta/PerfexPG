package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlRoleTopicRating {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, rtlk_keyid, skrm_keyid, cutoff, criteriadesc, orderno
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlRoleTopicRating()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getRtrlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRtrlKeyid(String rtrlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rtrlKeyid;
	}

	public String getRtrlRtlkKeyid() {
		return (String) saveArray[ tableFldConstants.rtlk_keyid.ordinal() ];
	}

	public void setRtrlRtlkKeyid(String rtrlRtlkKeyid) {
		saveArray[ tableFldConstants.rtlk_keyid.ordinal() ] = rtrlRtlkKeyid;
	}

	public String getRtrlSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setRtrlSkrmKeyid(String rtrlSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = rtrlSkrmKeyid;
	}

	public String getRtrlCutoff() {
		return (String) saveArray[ tableFldConstants.cutoff.ordinal() ];
	}

	public void setRtrlCutoff(String rtrlCutoff) {
		saveArray[ tableFldConstants.cutoff.ordinal() ] = rtrlCutoff;
	}

	public String getRtrlCriteriadesc() {
		return (String) saveArray[ tableFldConstants.criteriadesc.ordinal() ];
	}

	public void setRtrlCriteriadesc(String rtrlCriteriadesc) {
		saveArray[ tableFldConstants.criteriadesc.ordinal() ] = rtrlCriteriadesc;
	}

	public String getRtrlOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setRtrlOrderno(String rtrlOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = rtrlOrderno;
	}

	public String getRtrlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRtrlTempfield1(String rtrlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rtrlTempfield1;
	}

	public String getRtrlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRtrlTempfield2(String rtrlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rtrlTempfield2;
	}

	public String getRtrlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRtrlTempfield3(String rtrlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rtrlTempfield3;
	}

	public String getRtrlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRtrlTempfield4(String rtrlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rtrlTempfield4;
	}

	public String getRtrlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setRtrlTempfield5(String rtrlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rtrlTempfield5;
	}

	public String getRtrlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRtrlActive(String rtrlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rtrlActive;
	}

	public String getRtrlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRtrlCreatedby(String rtrlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rtrlCreatedby;
	}

	public String getRtrlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRtrlCreatedon(String rtrlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rtrlCreatedon;
	}

	public String getRtrlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRtrlModifiedon(String rtrlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rtrlModifiedon;
	}

}

