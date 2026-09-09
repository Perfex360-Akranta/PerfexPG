package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlSubcategorymst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, kctm_keyid, subcatname, subcatcode, slno, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public KznTlSubcategorymst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKscmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKscmKeyid(String kscmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kscmKeyid;
	}

	public String getKscmKctmKeyid() {
		return (String) saveArray[ tableFldConstants.kctm_keyid.ordinal() ];
	}

	public void setKscmKctmKeyid(String kscmKctmKeyid) {
		saveArray[ tableFldConstants.kctm_keyid.ordinal() ] = kscmKctmKeyid;
	}

	public String getKscmSubcatname() {
		return (String) saveArray[ tableFldConstants.subcatname.ordinal() ];
	}

	public void setKscmSubcatname(String kscmSubcatname) {
		saveArray[ tableFldConstants.subcatname.ordinal() ] = kscmSubcatname;
	}

	public String getKscmSubcatcode() {
		return (String) saveArray[ tableFldConstants.subcatcode.ordinal() ];
	}

	public void setKscmSubcatcode(String kscmSubcatcode) {
		saveArray[ tableFldConstants.subcatcode.ordinal() ] = kscmSubcatcode;
	}

	public String getKscmSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setKscmSlno(String kscmSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = kscmSlno;
	}

	public String getKscmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKscmTempfield1(String kscmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kscmTempfield1;
	}

	public String getKscmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKscmTempfield2(String kscmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kscmTempfield2;
	}

	public String getKscmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKscmActive(String kscmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kscmActive;
	}

	public String getKscmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKscmCreatedby(String kscmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kscmCreatedby;
	}

	public String getKscmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKscmCreatedon(String kscmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kscmCreatedon;
	}

	public String getKscmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKscmModifiedon(String kscmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kscmModifiedon;
	}



}

