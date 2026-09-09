package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEqpalarmlink {

	private  Object [] saveArray = null;  
	private String selectedRow;

	public enum   tableFldConstants
	{
		keyid, eqpgroupid, alarmid, active, createdby, createdon, modifiedon
	}

	public GenTlEqpalarmlink()
	{
		saveArray = new  Object [ 7 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEqalKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEqalKeyid(String eqalKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eqalKeyid;
	}

	public String getEqalEqpgroupid() {
		return (String) saveArray[ tableFldConstants.eqpgroupid.ordinal() ];
	}

	public void setEqalEqpgroupid(String eqalEqpgroupid) {
		saveArray[ tableFldConstants.eqpgroupid.ordinal() ] = eqalEqpgroupid;
	}

	public String getEqalAlarmid() {
		return (String) saveArray[ tableFldConstants.alarmid.ordinal() ];
	}

	public void setEqalAlarmid(String eqalAlarmid) {
		saveArray[ tableFldConstants.alarmid.ordinal() ] = eqalAlarmid;
	}

	public String getEqalActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEqalActive(String eqalActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eqalActive;
	}

	public String getEqalCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEqalCreatedby(String eqalCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eqalCreatedby;
	}

	public String getEqalCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEqalCreatedon(String eqalCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eqalCreatedon;
	}

	public String getEqalModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEqalModifiedon(String eqalModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eqalModifiedon;
	}

	public void setSelectedRow(String selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getSelectedRow() {
		return selectedRow;
	}

}

