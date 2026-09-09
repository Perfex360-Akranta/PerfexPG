package com.akranta.tpm.model;
import java.util.List;

public class GenTlEmpcirclelink {

	private  Object [] saveArray = null;  
	private List <GenTlEmpcirclelink> methodGenTlEmpcirclelink;

	public enum   tableFldConstants
	{
		keyid, empm_keyid, circleid, active, createdby, createdon, modifiedon
	}
	public List<GenTlEmpcirclelink> getmethodGenTlEmpcirclelink() 
	{
		return methodGenTlEmpcirclelink;
	}
	public void setmethodGenTlEmpcirclelink(List <GenTlEmpcirclelink> methodGenTlEmpcirclelink) {
		this.methodGenTlEmpcirclelink=methodGenTlEmpcirclelink;
	}
	public GenTlEmpcirclelink()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEcrlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEcrlKeyid(String ecrlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ecrlKeyid;
	}

	public String getEcrlEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setEcrlEmpmKeyid(String ecrlEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = ecrlEmpmKeyid;
	}

	public String getEcrlCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setEcrlCircleid(String ecrlCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = ecrlCircleid;
	}

	public String getEcrlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEcrlActive(String ecrlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ecrlActive;
	}

	public String getEcrlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEcrlCreatedby(String ecrlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ecrlCreatedby;
	}

	public String getEcrlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEcrlCreatedon(String ecrlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ecrlCreatedon;
	}

	public String getEcrlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEcrlModifiedon(String ecrlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ecrlModifiedon;
	}

}

