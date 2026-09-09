package com.akranta.tpm.model;

import java.util.List;

public class GenTlMchcirclelink {

	private  Object [] saveArray = null;  
	private List <GenTlMchcirclelink> methodGenTlMchcirclelink;
	private List<GenTlMchcirclelink> circlegrid;  

	public enum   tableFldConstants
	{
		keyid, machineid, circleid, active, createdby, modifiedon, createdon
	}
	public List<GenTlMchcirclelink> getmethodGenTlMchcirclelink() 
	{
		return methodGenTlMchcirclelink;
	}
	public void setmethodGenTlMchcirclelink(List <GenTlMchcirclelink> methodGenTlMchcirclelink) {
		this.methodGenTlMchcirclelink=methodGenTlMchcirclelink;
	}
	public GenTlMchcirclelink()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMclkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMclkKeyid(String mclkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mclkKeyid;
	}

	public String getMclkMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMclkMachineid(String mclkMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = mclkMachineid;
	}

	public String getMclkCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setMclkCircleid(String mclkCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = mclkCircleid;
	}

	public String getMclkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMclkActive(String mclkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mclkActive;
	}

	public String getMclkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMclkCreatedby(String mclkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mclkCreatedby;
	}

	public String getMclkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMclkModifiedon(String mclkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mclkModifiedon;
	}

	public String getMclkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMclkCreatedon(String mclkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mclkCreatedon;
	}
	public void setCirclegrid(List<GenTlMchcirclelink> circlegrid) {
		this.circlegrid = circlegrid;
	}
	public List<GenTlMchcirclelink> getCirclegrid() {
		return circlegrid;
	}

}

