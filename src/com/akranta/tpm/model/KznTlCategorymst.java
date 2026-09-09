package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlCategorymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tpmpillarid, name, code, description, remarks, active
		, createdby, createdon, modifiedon
	}

	public KznTlCategorymst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKctmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKctmKeyid(String kctmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kctmKeyid;
	}

	public String getKctmTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setKctmTpmpillarid(String kctmTpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = kctmTpmpillarid;
	}

	public String getKctmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setKctmName(String kctmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = kctmName;
	}

	public String getKctmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setKctmCode(String kctmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = kctmCode;
	}

	public String getKctmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setKctmDescription(String kctmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = kctmDescription;
	}

	public String getKctmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setKctmRemarks(String kctmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = kctmRemarks;
	}

	public String getKctmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKctmActive(String kctmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kctmActive;
	}

	public String getKctmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKctmCreatedby(String kctmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kctmCreatedby;
	}

	public String getKctmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKctmCreatedon(String kctmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kctmCreatedon;
	}

	public String getKctmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKctmModifiedon(String kctmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kctmModifiedon;
	}

}

