package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlMchrankskillmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, maximumpoints, active, createdby, createdon
		, modifiedon
	}

	public GenTlMchrankskillmst()
	{
		saveArray = new  Object [ 8 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMrskKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMrskKeyid(String mrskKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mrskKeyid;
	}

	public String getMrskName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setMrskName(String mrskName) {
		saveArray[ tableFldConstants.name.ordinal() ] = mrskName;
	}

	public String getMrskCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setMrskCode(String mrskCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = mrskCode;
	}

	public String getMrskMaximumpoints() {
		return (String) saveArray[ tableFldConstants.maximumpoints.ordinal() ];
	}

	public void setMrskMaximumpoints(String mrskMaximumpoints) {
		saveArray[ tableFldConstants.maximumpoints.ordinal() ] = mrskMaximumpoints;
	}

	public String getMrskActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMrskActive(String mrskActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mrskActive;
	}

	public String getMrskCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMrskCreatedby(String mrskCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mrskCreatedby;
	}

	public String getMrskCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMrskCreatedon(String mrskCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mrskCreatedon;
	}

	public String getMrskModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMrskModifiedon(String mrskModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mrskModifiedon;
	}

}

