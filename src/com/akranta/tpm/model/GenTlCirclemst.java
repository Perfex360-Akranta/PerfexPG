package com.akranta.tpm.model;

public class GenTlCirclemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, code, name, description,owner ,active, createdby
		, createdon, modifiedon
	}

	public GenTlCirclemst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		this.saveArray = saveArray;
	}
	public String getCrcmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCrcmKeyid(String crcmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = crcmKeyid;
	}

	public String getCrcmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setCrcmFactoryid(String crcmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = crcmFactoryid;
	}

	public String getCrcmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setCrcmCode(String crcmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = crcmCode;
	}

	public String getCrcmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setCrcmName(String crcmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = crcmName;
	}

	public String getCrcmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setCrcmDescription(String crcmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = crcmDescription;
	}

	public String getCrcmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCrcmActive(String crcmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = crcmActive;
	}

	public String getCrcmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCrcmCreatedby(String crcmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = crcmCreatedby;
	}

	public String getCrcmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCrcmCreatedon(String crcmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = crcmCreatedon;
	}

	public String getCrcmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCrcmModifiedon(String crcmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = crcmModifiedon;
	}

	public String getCrcmOwner() {
		return (String) saveArray[ tableFldConstants.owner.ordinal() ];
	}

	public void setCrcmOwner(String crcmOwner) {
		saveArray[ tableFldConstants.owner.ordinal() ] = crcmOwner;
	}

}

