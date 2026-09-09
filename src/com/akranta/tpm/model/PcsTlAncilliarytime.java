package com.akranta.tpm.model;

public class PcsTlAncilliarytime {

	private  Object [] saveArray = null;  

	public enum   tableFldConstants
	{
		keyid, pldeatilsid, lossid, womskeyid, occureddate, productionstartdate
		, losstime, status, remarks, machineid, temp2, temp3, active, createdby
		, createdon, modifiedon
	}

	public PcsTlAncilliarytime()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getPtatKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPtatKeyid(String ptatKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ptatKeyid;
	}

	public String getPtatPldeatilsid() {
		return (String) saveArray[ tableFldConstants.pldeatilsid.ordinal() ];
	}

	public void setPtatPldeatilsid(String ptatPldeatilsid) {
		saveArray[ tableFldConstants.pldeatilsid.ordinal() ] = ptatPldeatilsid;
	}

	public String getPtatLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setPtatLossid(String ptatLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = ptatLossid;
	}

	public String getPtatWomskeyid() {
		return (String) saveArray[ tableFldConstants.womskeyid.ordinal() ];
	}

	public void setPtatWomskeyid(String ptatWomskeyid) {
		saveArray[ tableFldConstants.womskeyid.ordinal() ] = ptatWomskeyid;
	}

	public String getPtatOccureddate() {
		return (String) saveArray[ tableFldConstants.occureddate.ordinal() ];
	}

	public void setPtatOccureddate(String ptatOccureddate) {
		saveArray[ tableFldConstants.occureddate.ordinal() ] = ptatOccureddate;
	}

	public String getPtatProductionstartdate() {
		return (String) saveArray[ tableFldConstants.productionstartdate.ordinal() ];
	}

	public void setPtatProductionstartdate(String ptatProductionstartdate) {
		saveArray[ tableFldConstants.productionstartdate.ordinal() ] = ptatProductionstartdate;
	}

	public String getPtatLosstime() {
		return (String) saveArray[ tableFldConstants.losstime.ordinal() ];
	}

	public void setPtatLosstime(String ptatLosstime) {
		saveArray[ tableFldConstants.losstime.ordinal() ] = ptatLosstime;
	}

	public String getPtatStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setPtatStatus(String ptatStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = ptatStatus;
	}

	public String getPtatRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPtatRemarks(String ptatRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = ptatRemarks;
	}

	public String getPtatMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setPtatMachineid(String ptatMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = ptatMachineid;
	}

	public String getPtatTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setPtatTemp2(String ptatTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = ptatTemp2;
	}

	public String getPtatTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setPtatTemp3(String ptatTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = ptatTemp3;
	}

	public String getPtatActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPtatActive(String ptatActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ptatActive;
	}

	public String getPtatCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPtatCreatedby(String ptatCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ptatCreatedby;
	}

	public String getPtatCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPtatCreatedon(String ptatCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ptatCreatedon;
	}

	public String getPtatModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPtatModifiedon(String ptatModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ptatModifiedon;
	}

}

