package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DcmTlRevisionhistory {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, docid, revno, revby, revdate, changes, temp2, temp3, active
		, createdby, createdon, modifiedon
	}

	public DcmTlRevisionhistory()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	public String getDmrhKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmrhKeyid(String dmrhKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmrhKeyid;
	}

	public String getDmrhDocid() {
		return (String) saveArray[ tableFldConstants.docid.ordinal() ];
	}

	public void setDmrhDocid(String dmrhDocid) {
		saveArray[ tableFldConstants.docid.ordinal() ] = dmrhDocid;
	}

	public String getDmrhRevno() {
		return (String) saveArray[ tableFldConstants.revno.ordinal() ];
	}

	public void setDmrhRevno(String dmrhRevno) {
		saveArray[ tableFldConstants.revno.ordinal() ] = dmrhRevno;
	}

	public String getDmrhRevby() {
		return (String) saveArray[ tableFldConstants.revby.ordinal() ];
	}

	public void setDmrhRevby(String dmrhRevby) {
		saveArray[ tableFldConstants.revby.ordinal() ] = dmrhRevby;
	}

	public String getDmrhRevdate() {
		return (String) saveArray[ tableFldConstants.revdate.ordinal() ];
	}

	public void setDmrhRevdate(String dmrhRevdate) {
		saveArray[ tableFldConstants.revdate.ordinal() ] = dmrhRevdate;
	}

	public String getDmrhChanges() {
		return (String) saveArray[ tableFldConstants.changes.ordinal() ];
	}

	public void setDmrhChanges(String dmrhChanges) {
		saveArray[ tableFldConstants.changes.ordinal() ] = dmrhChanges;
	}

	public String getDmrhTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}

	public void setDmrhTemp2(String dmrhTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = dmrhTemp2;
	}

	public String getDmrhTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setDmrhTemp3(String dmrhTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = dmrhTemp3;
	}

	public String getDmrhActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmrhActive(String dmrhActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmrhActive;
	}

	public String getDmrhCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmrhCreatedby(String dmrhCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmrhCreatedby;
	}

	public String getDmrhCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmrhCreatedon(String dmrhCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmrhCreatedon;
	}

	public String getDmrhModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmrhModifiedon(String dmrhModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmrhModifiedon;
	}

}

