package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class DocTlRoleRights {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, role_keyid, docid, rights, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public DocTlRoleRights()
	{
		saveArray = new  Object [ 12 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getRlriKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRlriKeyid(String rlriKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rlriKeyid;
	}

	public String getRlriRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setRlriRoleKeyid(String rlriRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = rlriRoleKeyid;
	}

	public String getRlriDocid() {
		return (String) saveArray[ tableFldConstants.docid.ordinal() ];
	}

	public void setRlriDocid(String rlriDocid) {
		saveArray[ tableFldConstants.docid.ordinal() ] = rlriDocid;
	}

	public String getRlriRights() {
		return (String) saveArray[ tableFldConstants.rights.ordinal() ];
	}

	public void setRlriRights(String rlriRights) {
		saveArray[ tableFldConstants.rights.ordinal() ] = rlriRights;
	}

	public String getRlriTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRlriTempfield1(String rlriTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rlriTempfield1;
	}

	public String getRlriTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRlriTempfield2(String rlriTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rlriTempfield2;
	}

	public String getRlriTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRlriTempfield3(String rlriTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rlriTempfield3;
	}

	public String getRlriTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRlriTempfield4(String rlriTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rlriTempfield4;
	}

	public String getRlriActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRlriActive(String rlriActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rlriActive;
	}

	public String getRlriCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRlriCreatedby(String rlriCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rlriCreatedby;
	}

	public String getRlriCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRlriCreatedon(String rlriCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rlriCreatedon;
	}

	public String getRlriModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRlriModifiedon(String rlriModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rlriModifiedon;
	}

}

