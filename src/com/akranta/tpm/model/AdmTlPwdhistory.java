package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlPwdhistory {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, userid, passwordno, password, lastpwdchangedon, active
		, createdby, createdon, modifiedon
	}

	public AdmTlPwdhistory()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPwdhKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPwdhKeyid(String pwdhKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pwdhKeyid;
	}

	public String getPwdhUserid() {
		return (String) saveArray[ tableFldConstants.userid.ordinal() ];
	}

	public void setPwdhUserid(String pwdhUserid) {
		saveArray[ tableFldConstants.userid.ordinal() ] = pwdhUserid;
	}

	public String getPwdhPasswordno() {
		return (String) saveArray[ tableFldConstants.passwordno.ordinal() ];
	}

	public void setPwdhPasswordno(String pwdhPasswordno) {
		saveArray[ tableFldConstants.passwordno.ordinal() ] = pwdhPasswordno;
	}

	public String getPwdhPassword() {
		return (String) saveArray[ tableFldConstants.password.ordinal() ];
	}

	public void setPwdhPassword(String pwdhPassword) {
		saveArray[ tableFldConstants.password.ordinal() ] = pwdhPassword;
	}

	public String getPwdhLastpwdchangedon() {
		return (String) saveArray[ tableFldConstants.lastpwdchangedon.ordinal() ];
	}

	public void setPwdhLastpwdchangedon(String pwdhLastpwdchangedon) {
		saveArray[ tableFldConstants.lastpwdchangedon.ordinal() ] = pwdhLastpwdchangedon;
	}

	public String getPwdhActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPwdhActive(String pwdhActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pwdhActive;
	}

	public String getPwdhCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPwdhCreatedby(String pwdhCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pwdhCreatedby;
	}

	public String getPwdhCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPwdhCreatedon(String pwdhCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pwdhCreatedon;
	}

	public String getPwdhModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPwdhModifiedon(String pwdhModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pwdhModifiedon;
	}

}

