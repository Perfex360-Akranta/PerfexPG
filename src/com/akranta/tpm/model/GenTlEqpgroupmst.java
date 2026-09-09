package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlEqpgroupmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, remarks, factoryid, maingroupid, subgroupid
		, active, createdby, createdon, modifiedon
	}

	public GenTlEqpgroupmst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEqgmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEqgmKeyid(String eqgmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eqgmKeyid;
	}

	public String getEqgmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setEqgmName(String eqgmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = eqgmName;
	}

	public String getEqgmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setEqgmCode(String eqgmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = eqgmCode;
	}

	public String getEqgmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEqgmRemarks(String eqgmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = eqgmRemarks;
	}

	public String getEqgmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setEqgmFactoryid(String eqgmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = eqgmFactoryid;
	}

	public String getEqgmMaingroupid() {
		return (String) saveArray[ tableFldConstants.maingroupid.ordinal() ];
	}

	public void setEqgmMaingroupid(String eqgmMaingroupid) {
		saveArray[ tableFldConstants.maingroupid.ordinal() ] = eqgmMaingroupid;
	}

	public String getEqgmSubgroupid() {
		return (String) saveArray[ tableFldConstants.subgroupid.ordinal() ];
	}

	public void setEqgmSubgroupid(String eqgmSubgroupid) {
		saveArray[ tableFldConstants.subgroupid.ordinal() ] = eqgmSubgroupid;
	}

	public String getEqgmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEqgmActive(String eqgmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eqgmActive;
	}

	public String getEqgmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEqgmCreatedby(String eqgmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eqgmCreatedby;
	}

	public String getEqgmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEqgmCreatedon(String eqgmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eqgmCreatedon;
	}

	public String getEqgmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEqgmModifiedon(String eqgmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eqgmModifiedon;
	}



}

