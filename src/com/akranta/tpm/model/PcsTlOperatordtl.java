package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlOperatordtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		plmasterid, pldetailsid, plemployeeid, active, createdby, createdon
		, modifiedon
	}

	public PcsTlOperatordtl()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPopdPlmasterid() {
		return (String) saveArray[ tableFldConstants.plmasterid.ordinal() ];
	}

	public void setPopdPlmasterid(String popdPlmasterid) {
		saveArray[ tableFldConstants.plmasterid.ordinal() ] = popdPlmasterid;
	}

	public String getPopdPldetailsid() {
		return (String) saveArray[ tableFldConstants.pldetailsid.ordinal() ];
	}

	public void setPopdPldetailsid(String popdPldetailsid) {
		saveArray[ tableFldConstants.pldetailsid.ordinal() ] = popdPldetailsid;
	}

	public String getPopdPlemployeeid() {
		return (String) saveArray[ tableFldConstants.plemployeeid.ordinal() ];
	}

	public void setPopdPlemployeeid(String popdPlemployeeid) {
		saveArray[ tableFldConstants.plemployeeid.ordinal() ] = popdPlemployeeid;
	}

	public String getPopdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPopdActive(String popdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = popdActive;
	}

	public String getPopdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPopdCreatedby(String popdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = popdCreatedby;
	}

	public String getPopdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPopdCreatedon(String popdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = popdCreatedon;
	}

	public String getPopdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPopdModifiedon(String popdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = popdModifiedon;
	}

}

