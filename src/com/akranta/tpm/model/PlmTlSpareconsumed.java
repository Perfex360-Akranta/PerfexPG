package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlSpareconsumed {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wodetailid, pmcalendarid, spareid, quantity, cost, isactivitydone
		, remarks, createdby, createdon, modifiedon
	}

	public PlmTlSpareconsumed()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPspcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPspcKeyid(String pspcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pspcKeyid;
	}

	public String getPspcWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setPspcWodetailid(String pspcWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = pspcWodetailid;
	}

	public String getPspcPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}

	public void setPspcPmcalendarid(String pspcPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = pspcPmcalendarid;
	}

	public String getPspcSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setPspcSpareid(String pspcSpareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = pspcSpareid;
	}

	public String getPspcQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPspcQuantity(String pspcQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = pspcQuantity;
	}

	public String getPspcCost() {
		return (String) saveArray[ tableFldConstants.cost.ordinal() ];
	}

	public void setPspcCost(String pspcCost) {
		saveArray[ tableFldConstants.cost.ordinal() ] = pspcCost;
	}

	public String getPspcIsactivitydone() {
		return (String) saveArray[ tableFldConstants.isactivitydone.ordinal() ];
	}

	public void setPspcIsactivitydone(String pspcIsactivitydone) {
		saveArray[ tableFldConstants.isactivitydone.ordinal() ] = pspcIsactivitydone;
	}

	public String getPspcRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setPspcRemarks(String pspcRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = pspcRemarks;
	}

	public String getPspcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPspcCreatedby(String pspcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pspcCreatedby;
	}

	public String getPspcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPspcCreatedon(String pspcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pspcCreatedon;
	}

	public String getPspcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPspcModifiedon(String pspcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pspcModifiedon;
	}

}

