package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlWorksummary {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wodetailid, wofeedbackid, spareflag, sparecost, manpowercost
		, contractorcost, othercost, accountedtime, unaccountedtime, createdby
		, createdon, modifiedon
	}

	public PlmTlWorksummary()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWksmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWksmKeyid(String wksmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wksmKeyid;
	}

	public String getWksmWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setWksmWodetailid(String wksmWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = wksmWodetailid;
	}

	public String getWksmWofeedbackid() {
		return (String) saveArray[ tableFldConstants.wofeedbackid.ordinal() ];
	}

	public void setWksmWofeedbackid(String wksmWofeedbackid) {
		saveArray[ tableFldConstants.wofeedbackid.ordinal() ] = wksmWofeedbackid;
	}

	public String getWksmSpareflag() {
		return (String) saveArray[ tableFldConstants.spareflag.ordinal() ];
	}

	public void setWksmSpareflag(String wksmSpareflag) {
		saveArray[ tableFldConstants.spareflag.ordinal() ] = wksmSpareflag;
	}

	public String getWksmSparecost() {
		return (String) saveArray[ tableFldConstants.sparecost.ordinal() ];
	}

	public void setWksmSparecost(String wksmSparecost) {
		saveArray[ tableFldConstants.sparecost.ordinal() ] = wksmSparecost;
	}

	public String getWksmManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setWksmManpowercost(String wksmManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = wksmManpowercost;
	}

	public String getWksmContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setWksmContractorcost(String wksmContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = wksmContractorcost;
	}

	public String getWksmOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setWksmOthercost(String wksmOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = wksmOthercost;
	}

	public String getWksmAccountedtime() {
		return (String) saveArray[ tableFldConstants.accountedtime.ordinal() ];
	}

	public void setWksmAccountedtime(String wksmAccountedtime) {
		saveArray[ tableFldConstants.accountedtime.ordinal() ] = wksmAccountedtime;
	}

	public String getWksmUnaccountedtime() {
		return (String) saveArray[ tableFldConstants.unaccountedtime.ordinal() ];
	}

	public void setWksmUnaccountedtime(String wksmUnaccountedtime) {
		saveArray[ tableFldConstants.unaccountedtime.ordinal() ] = wksmUnaccountedtime;
	}

	public String getWksmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWksmCreatedby(String wksmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wksmCreatedby;
	}

	public String getWksmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWksmCreatedon(String wksmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wksmCreatedon;
	}

	public String getWksmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWksmModifiedon(String wksmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wksmModifiedon;
	}

}

