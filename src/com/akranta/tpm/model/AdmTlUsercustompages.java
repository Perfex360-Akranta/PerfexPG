package com.akranta.tpm.model;

public class AdmTlUsercustompages {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, usrm_keyid, pageuri, params, formheader, displayorder
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public AdmTlUsercustompages()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		this.saveArray = saveArray;
	}

	public String getUscpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setUscpKeyid(String uscpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = uscpKeyid;
	}

	public String getUscpUsrmKeyid() {
		return (String) saveArray[ tableFldConstants.usrm_keyid.ordinal() ];
	}

	public void setUscpUsrmKeyid(String uscpUsrmKeyid) {
		saveArray[ tableFldConstants.usrm_keyid.ordinal() ] = uscpUsrmKeyid;
	}

	public String getUscpPageuri() {
		return (String) saveArray[ tableFldConstants.pageuri.ordinal() ];
	}

	public void setUscpPageuri(String uscpPageuri) {
		saveArray[ tableFldConstants.pageuri.ordinal() ] = uscpPageuri;
	}

	public String getUscpParams() {
		return (String) saveArray[ tableFldConstants.params.ordinal() ];
	}

	public void setUscpParams(String uscpParams) {
		saveArray[ tableFldConstants.params.ordinal() ] = uscpParams;
	}

	public String getUscpFormheader() {
		return (String) saveArray[ tableFldConstants.formheader.ordinal() ];
	}

	public void setUscpFormheader(String uscpFormheader) {
		saveArray[ tableFldConstants.formheader.ordinal() ] = uscpFormheader;
	}

	public String getUscpDisplayorder() {
		return (String) saveArray[ tableFldConstants.displayorder.ordinal() ];
	}

	public void setUscpDisplayorder(String uscpDisplayorder) {
		saveArray[ tableFldConstants.displayorder.ordinal() ] = uscpDisplayorder;
	}

	public String getUscpTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setUscpTempfield1(String uscpTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = uscpTempfield1;
	}

	public String getUscpTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setUscpTempfield2(String uscpTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = uscpTempfield2;
	}

	public String getUscpTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setUscpTempfield3(String uscpTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = uscpTempfield3;
	}

	public String getUscpTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setUscpTempfield4(String uscpTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = uscpTempfield4;
	}

	public String getUscpTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setUscpTempfield5(String uscpTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = uscpTempfield5;
	}

	public String getUscpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setUscpActive(String uscpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = uscpActive;
	}

	public String getUscpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setUscpCreatedby(String uscpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = uscpCreatedby;
	}

	public String getUscpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setUscpCreatedon(String uscpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = uscpCreatedon;
	}

	public String getUscpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setUscpModifiedon(String uscpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = uscpModifiedon;
	}

}

