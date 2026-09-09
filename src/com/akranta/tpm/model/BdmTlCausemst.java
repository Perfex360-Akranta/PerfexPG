package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BdmTlCausemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, phenomenaid, remarks, iscausedefined, active
		, createdby, createdon, modifiedon
	}

	public BdmTlCausemst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getBcsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBcsmKeyid(String bcsmkeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bcsmkeyid;
	}

	public String getBcsmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setBcsmName(String bcsmname) {
		saveArray[ tableFldConstants.name.ordinal() ] = bcsmname;
	}

	public String getBcsmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setBcsmCode(String bcsmcode) {
		saveArray[ tableFldConstants.code.ordinal() ] = bcsmcode;
	}

	public String getBcsmPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setBcsmPhenomenaid(String bcsmphenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = bcsmphenomenaid;
	}

	public String getBcsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setBcsmRemarks(String bcsmremarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = bcsmremarks;
	}

	public String getBcsmIscausedefined() {
		return (String) saveArray[ tableFldConstants.iscausedefined.ordinal() ];
	}

	public void setBcsmIscausedefined(String bcsmiscausedefined) {
		saveArray[ tableFldConstants.iscausedefined.ordinal() ] = bcsmiscausedefined;
	}

	public String getBcsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBcsmActive(String bcsmactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bcsmactive;
	}

	public String getBcsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBcsmCreatedby(String bcsmcreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bcsmcreatedby;
	}

	public String getBcsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBcsmCreatedon(String bcsmcreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bcsmcreatedon;
	}

	public String getBcsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBcsmModifiedon(String bcsmmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bcsmmodifiedon;
	}

}

