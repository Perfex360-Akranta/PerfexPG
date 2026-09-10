package com.akranta.tpm.model;

public class BAL_PlmTlMultiplemethodsmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refdocid, refdoctype, methoddescription, duration, active
		, createdby, createdon, modifiedon
	}

	public BAL_PlmTlMultiplemethodsmst()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMlmmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMlmmKeyid(String mlmmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mlmmKeyid;
	}

	public String getMlmmRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setMlmmRefdocid(String mlmmRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = mlmmRefdocid;
	}

	public String getMlmmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setMlmmRefdoctype(String mlmmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = mlmmRefdoctype;
	}

	public String getMlmmMethoddescription() {
		return (String) saveArray[ tableFldConstants.methoddescription.ordinal() ];
	}

	public void setMlmmMethoddescription(String mlmmMethoddescription) {
		saveArray[ tableFldConstants.methoddescription.ordinal() ] = mlmmMethoddescription;
	}

	public String getMlmmDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setMlmmDuration(String mlmmDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = mlmmDuration;
	}

	public String getMlmmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMlmmActive(String mlmmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mlmmActive;
	}

	public String getMlmmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMlmmCreatedby(String mlmmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mlmmCreatedby;
	}

	public String getMlmmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMlmmCreatedon(String mlmmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mlmmCreatedon;
	}

	public String getMlmmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMlmmModifiedon(String mlmmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mlmmModifiedon;
	}

}

