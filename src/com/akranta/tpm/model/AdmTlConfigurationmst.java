package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlConfigurationmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, settingvalue, fromdate, tilldate, active, createdby
		, createdon, modifiedon
	}

	public AdmTlConfigurationmst()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getCnfmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCnfmKeyid(String cnfmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cnfmKeyid;
	}

	public String getCnfmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setCnfmCode(String cnfmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = cnfmCode;
	}

	public String getCnfmSettingvalue() {
		return (String) saveArray[ tableFldConstants.settingvalue.ordinal() ];
	}

	public void setCnfmSettingvalue(String cnfmSettingvalue) {
		saveArray[ tableFldConstants.settingvalue.ordinal() ] = cnfmSettingvalue;
	}

	public String getCnfmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setCnfmFromdate(String cnfmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = cnfmFromdate;
	}

	public String getCnfmTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setCnfmTilldate(String cnfmTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = cnfmTilldate;
	}

	public String getCnfmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCnfmActive(String cnfmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cnfmActive;
	}

	public String getCnfmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCnfmCreatedby(String cnfmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cnfmCreatedby;
	}

	public String getCnfmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCnfmCreatedon(String cnfmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cnfmCreatedon;
	}

	public String getCnfmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCnfmModifiedon(String cnfmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cnfmModifiedon;
	}

}

