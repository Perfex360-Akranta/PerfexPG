package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTaskMappingksamst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, skrm_keyid, sopke_keyid, active, createdby, createdon
		, modifiedon
	}

	public EntTlTaskMappingksamst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmkmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmkmKeyid(String tmkmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmkmKeyid;
	}

	public String getTmkmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTmkmFlid(String tmkmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tmkmFlid;
	}

	public String getTmkmRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setTmkmRoleKeyid(String tmkmRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = tmkmRoleKeyid;
	}

	public String getTmkmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTmkmTempfield1(String tmkmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tmkmTempfield1;
	}

	public String getTmkmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmkmTempfield2(String tmkmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmkmTempfield2;
	}

	public String getTmkmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmkmTempfield3(String tmkmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmkmTempfield3;
	}

	public String getTmkmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmkmTempfield4(String tmkmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmkmTempfield4;
	}

	public String getTmkmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmkmTempfield5(String tmkmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmkmTempfield5;
	}

	public String getTmkmSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setTmkmSkrmKeyid(String tmkmSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = tmkmSkrmKeyid;
	}

	public String getTmkmSopkeKeyid() {
		return (String) saveArray[ tableFldConstants.sopke_keyid.ordinal() ];
	}

	public void setTmkmSopkeKeyid(String tmkmSopkeKeyid) {
		saveArray[ tableFldConstants.sopke_keyid.ordinal() ] = tmkmSopkeKeyid;
	}

	public String getTmkmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmkmActive(String tmkmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmkmActive;
	}

	public String getTmkmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmkmCreatedby(String tmkmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmkmCreatedby;
	}

	public String getTmkmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmkmCreatedon(String tmkmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmkmCreatedon;
	}

	public String getTmkmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmkmModifiedon(String tmkmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmkmModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}

}

