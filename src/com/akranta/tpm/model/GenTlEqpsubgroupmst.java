package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEqpsubgroupmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, makemodel, machinefunction, remarks, ismaingroup
		, active, createdby, createdon, modifiedon
	}

	public GenTlEqpsubgroupmst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEqsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEqsmKeyid(String eqsmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eqsmKeyid;
	}

	public String getEqsmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setEqsmName(String eqsmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = eqsmName;
	}

	public String getEqsmMakemodel() {
		return (String) saveArray[ tableFldConstants.makemodel.ordinal() ];
	}

	public void setEqsmMakemodel(String eqsmMakemodel) {
		saveArray[ tableFldConstants.makemodel.ordinal() ] = eqsmMakemodel;
	}

	public String getEqsmMachinefunction() {
		return (String) saveArray[ tableFldConstants.machinefunction.ordinal() ];
	}

	public void setEqsmMachinefunction(String eqsmMachinefunction) {
		saveArray[ tableFldConstants.machinefunction.ordinal() ] = eqsmMachinefunction;
	}

	public String getEqsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEqsmRemarks(String eqsmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = eqsmRemarks;
	}

	public String getEqsmIsmaingroup() {
		return (String) saveArray[ tableFldConstants.ismaingroup.ordinal() ];
	}

	public void setEqsmIsmaingroup(String eqsmIsmaingroup) {
		saveArray[ tableFldConstants.ismaingroup.ordinal() ] = eqsmIsmaingroup;
	}

	public String getEqsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEqsmActive(String eqsmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eqsmActive;
	}

	public String getEqsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEqsmCreatedby(String eqsmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eqsmCreatedby;
	}

	public String getEqsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEqsmCreatedon(String eqsmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eqsmCreatedon;
	}

	public String getEqsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEqsmModifiedon(String eqsmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eqsmModifiedon;
	}

}

