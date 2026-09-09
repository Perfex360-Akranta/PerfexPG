package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlCompanymst {

	private  Object [] saveArray = null;  
	
	private GenTlFunctionallocn genTlFunctionallocn;
	
	public enum   tableFldConstants
	{
		keyid, name, code, address, active, createdby, createdon, modifiedon
	}

	public GenTlCompanymst()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getCompKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCompKeyid(String compKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = compKeyid;
	}

	public String getCompName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setCompName(String compName) {
		saveArray[ tableFldConstants.name.ordinal() ] = compName;
	}

	public String getCompCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setCompCode(String compCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = compCode;
	}

	public String getCompAddress() {
		return (String) saveArray[ tableFldConstants.address.ordinal() ];
	}

	public void setCompAddress(String compAddress) {
		saveArray[ tableFldConstants.address.ordinal() ] = compAddress;
	}

	public String getCompActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCompActive(String compActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = compActive;
	}

	public String getCompCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCompCreatedby(String compCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = compCreatedby;
	}

	public String getCompCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCompCreatedon(String compCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = compCreatedon;
	}

	public String getCompModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCompModifiedon(String compModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = compModifiedon;
	}

	public void setGenTlFunctionallocn(GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}
	
}

