package com.akranta.tpm.model;

public class BAL_GenTlFactorymst {

	private  Object [] saveArray = null;
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  
	

	public enum   tableFldConstants
	{
		keyid, companyid, name, code, address, locationid,flid, active, createdby
		, createdon, modifiedon
	}

	public BAL_GenTlFactorymst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}


	public String getFactKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFactKeyid(String factKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = factKeyid;
	}

	public String getFactCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setFactCompanyid(String factCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = factCompanyid;
	}

	public String getFactName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setFactName(String factName) {
		saveArray[ tableFldConstants.name.ordinal() ] = factName;
	}

	public String getFactCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setFactCode(String factCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = factCode;
	}

	public String getFactAddress() {
		return (String) saveArray[ tableFldConstants.address.ordinal() ];
	}

	public void setFactAddress(String factAddress) {
		saveArray[ tableFldConstants.address.ordinal() ] = factAddress;
	}

	public String getFactLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setFactLocationid(String factLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = factLocationid;
	}

	public String getFactFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setFactFlid(String factFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = factFlid;
	}

	
	public String getFactActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFactActive(String factActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = factActive;
	}

	public String getFactCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFactCreatedby(String factCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = factCreatedby;
	}

	public String getFactCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFactCreatedon(String factCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = factCreatedon;
	}

	public String getFactModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFactModifiedon(String factModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = factModifiedon;
	}
	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}

}

