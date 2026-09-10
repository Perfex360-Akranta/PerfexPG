package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlYydonebymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, empm_keyid, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public BAL_BdmTlYydonebymst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}


	public String getWwdbKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwdbKeyid(String wwdbKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwdbKeyid;
	}

	public String getWwdbWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwms_keyid.ordinal() ];
	}

	public void setWwdbWwmsKeyid(String wwdbWwmsKeyid) {
		saveArray[ tableFldConstants.wwms_keyid.ordinal() ] = wwdbWwmsKeyid;
	}

	public String getWwdbEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setWwdbEmpmKeyid(String wwdbEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = wwdbEmpmKeyid;
	}

	public String getWwdbTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWwdbTempfield1(String wwdbTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wwdbTempfield1;
	}

	public String getWwdbTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWwdbTempfield2(String wwdbTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wwdbTempfield2;
	}

	public String getWwdbTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWwdbTempfield3(String wwdbTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wwdbTempfield3;
	}

	public String getWwdbActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWwdbActive(String wwdbActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wwdbActive;
	}

	public String getWwdbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwdbCreatedby(String wwdbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwdbCreatedby;
	}

	public String getWwdbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwdbCreatedon(String wwdbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwdbCreatedon;
	}

	public String getWwdbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwdbModifiedon(String wwdbModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwdbModifiedon;
	}

}

