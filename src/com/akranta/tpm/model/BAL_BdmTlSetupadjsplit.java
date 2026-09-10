package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlSetupadjsplit {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, machineid, refdocid, splitdate, splitshift, duration, lossid
		, active, createdby, createdon, modificeon
	}

	public BAL_BdmTlSetupadjsplit()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSupsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSupsKeyid(String supsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = supsKeyid;
	}

	public String getSupsMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setSupsMachineid(String supsMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = supsMachineid;
	}

	public String getSupsRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setSupsRefdocid(String supsRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = supsRefdocid;
	}

	public String getSupsSplitdate() {
		return (String) saveArray[ tableFldConstants.splitdate.ordinal() ];
	}

	public void setSupsSplitdate(String supsSplitdate) {
		saveArray[ tableFldConstants.splitdate.ordinal() ] = supsSplitdate;
	}

	public String getSupsSplitshift() {
		return (String) saveArray[ tableFldConstants.splitshift.ordinal() ];
	}

	public void setSupsSplitshift(String supsSplitshift) {
		saveArray[ tableFldConstants.splitshift.ordinal() ] = supsSplitshift;
	}

	public String getSupsDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setSupsDuration(String supsDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = supsDuration;
	}

	public String getSupsLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setSupsLossid(String supsLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = supsLossid;
	}

	public String getSupsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSupsActive(String supsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = supsActive;
	}

	public String getSupsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSupsCreatedby(String supsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = supsCreatedby;
	}

	public String getSupsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSupsCreatedon(String supsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = supsCreatedon;
	}

	public String getSupsModificeon() {
		return (String) saveArray[ tableFldConstants.modificeon.ordinal() ];
	}

	public void setSupsModificeon(String supsModificeon) {
		saveArray[ tableFldConstants.modificeon.ordinal() ] = supsModificeon;
	}

}

