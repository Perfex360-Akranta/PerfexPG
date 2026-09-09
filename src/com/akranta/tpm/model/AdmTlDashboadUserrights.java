package com.akranta.tpm.model;

public class AdmTlDashboadUserrights {

	private  Object [] saveArray = null;  
	


	public enum   tableFldConstants
	{
		keyid, menuid, roleid, tempfield, tempfiled1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon,status
	}

	public AdmTlDashboadUserrights()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDburKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDburKeyid(String dburKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dburKeyid;
	}

	public String getDburMenuid() {
		return (String) saveArray[ tableFldConstants.menuid.ordinal() ];
	}

	public void setDburMenuid(String dburMenuid) {
		saveArray[ tableFldConstants.menuid.ordinal() ] = dburMenuid;
	}

	public String getDburRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setDburRoleid(String dburRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = dburRoleid;
	}

	public String getDburTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setDburTempfield(String dburTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = dburTempfield;
	}

	public String getDburTempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setDburTempfiled1(String dburTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = dburTempfiled1;
	}

	public String getDburTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDburTempfield2(String dburTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dburTempfield2;
	}

	public String getDburTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDburTempfield3(String dburTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dburTempfield3;
	}

	public String getDburActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDburActive(String dburActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dburActive;
	}

	public String getDburCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDburCreatedby(String dburCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dburCreatedby;
	}

	public String getDburCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDburCreatedon(String dburCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dburCreatedon;
	}

	public String getDburModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDburModifiedon(String dburModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dburModifiedon;
	}
	public String getDburstatus() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setDburstatus(String dburstatus) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = dburstatus;
	}

}

